package joachim.lejeune.peditrack.controller.patient;

import joachim.lejeune.peditrack.bodyDto.PatientBodyDto;
import joachim.lejeune.peditrack.controller.auth.UserDetailsImpl;
import joachim.lejeune.peditrack.dto.PatientDto;
import joachim.lejeune.peditrack.dto.PatientRecordDto;
import joachim.lejeune.peditrack.dto.factory.HealthDtoFactory;
import joachim.lejeune.peditrack.dto.factory.PatientDtoFactory;
import joachim.lejeune.peditrack.exceptions.PatientNotFoundException;
import joachim.lejeune.peditrack.model.patient.Health;
import joachim.lejeune.peditrack.model.patient.Patient;
import joachim.lejeune.peditrack.service.HealthService;
import joachim.lejeune.peditrack.service.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PatientController {
    private static final Logger LOG = LoggerFactory.getLogger(PatientController.class);
    private final PatientService patientService;
    private final HealthService healthService;
    private final PatientDtoFactory patientDtoFactory;
    private final HealthDtoFactory healthDtoFactory;

    public PatientController(PatientService patientService, HealthService healthService, PatientDtoFactory patientDtoFactory, HealthDtoFactory healthDtoFactory) {
        this.patientService = patientService;
        this.healthService = healthService;
        this.patientDtoFactory = patientDtoFactory;
        this.healthDtoFactory = healthDtoFactory;
    }

    @GetMapping("/patients")
    public ResponseEntity<List<PatientDto>> getAllPatients() throws Exception {
        LOG.info("Enter method getAllPatients");
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<PatientDto> patientDtos = patientService.findByUserId(userDetails.getId())
                .stream()
                .map(patient -> {
                    try {
                        return patientDtoFactory.convert(patient);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .toList();
        return new ResponseEntity<>(patientDtos, HttpStatus.OK);
    }

    @GetMapping("/patients/{patientId}")
    public ResponseEntity<PatientRecordDto> getPatient(@PathVariable(value = "patientId") Long patientId) throws PatientNotFoundException, IOException {
        LOG.info("Enter method getPatient");

        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Patient patient = patientService.getPatientById(patientId);

        if (!patient.getUser().getId().equals(userDetails.getId())) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        Health lastHealth = patient.getLastHealth();
        PatientRecordDto patientRecordDto = new PatientRecordDto();
        patientRecordDto.setPatientDto(patientDtoFactory.convert(patient));
        patientRecordDto.setHealthDto(healthDtoFactory.convert(lastHealth));

        return new ResponseEntity<>(patientRecordDto, HttpStatus.OK);
    }

    @PostMapping("/patients")
    public ResponseEntity<PatientDto> createPatient(@RequestBody PatientBodyDto patientBodyDto) throws IOException {
        LOG.info("Enter method createPatient");

        if (patientBodyDto.getName() == null || patientBodyDto.getName().isBlank()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (patientBodyDto.getFirstname() == null || patientBodyDto.getFirstname().isBlank()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Patient newPatient = patientService.createPatient(patientBodyDto, user);
        Health newHealth = healthService.createNewHealth(newPatient, patientBodyDto);
        newPatient.addHealthRecord(newHealth);

        PatientDto patientDto = patientDtoFactory.convert(newPatient);

        return new ResponseEntity<>(patientDto, HttpStatus.CREATED);
    }

    @PutMapping("/patients/{patientId}")
    public ResponseEntity<PatientDto> updatePatient(
            @PathVariable(value = "patientId") Long patientId,
            @RequestBody PatientBodyDto patientBodyDto) throws IOException {

        LOG.info("Enter method updatePatient");

        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Patient existing = patientService.getPatientById(patientId);

        if (!existing.getUser().getId().equals(userDetails.getId())) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        Patient updatedPatient = patientService.updatePatient(patientId, patientBodyDto);
        Health health = healthService.createNewHealth(updatedPatient, patientBodyDto);
        updatedPatient.addHealthRecord(health);

        PatientDto responseDto = patientDtoFactory.convert(updatedPatient);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
