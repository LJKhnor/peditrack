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
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.junit.Assert.assertNotNull;

@RestController
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

        List<PatientDto> patientDtos = patientService.findByUser(userDetails.getUser())
                .stream()
                .map(patient -> patientDtoFactory.convert(patient))
                .toList();
        return new ResponseEntity<>(patientDtos, HttpStatus.OK);
    }

    @GetMapping("/patients/{patientId}")
    public ResponseEntity<PatientRecordDto> getPatient(@PathVariable(value = "patientId") Long patientId) throws PatientNotFoundException {
        LOG.info("Enter method getPatient");

        Patient patient = patientService.getPatientById(patientId);
        Health lastHealth = patient.getLastHealth();
        PatientRecordDto patientRecordDto = new PatientRecordDto();
        patientRecordDto.setPatientDto(patientDtoFactory.convert(patient));
        patientRecordDto.setHealthDto(healthDtoFactory.convert(lastHealth));

        return new ResponseEntity<>(patientRecordDto, HttpStatus.OK);
    }

    @PostMapping("/patients")
    public ResponseEntity<PatientDto> createPatient(@RequestBody PatientBodyDto patientBodyDto) {
        LOG.info("Enter method createPatient");
        assertNotNull("Patient name must be not null",patientBodyDto.getName());
        assertNotNull("Patient firstname must be not null", patientBodyDto.getFirstname());

        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Appel au service pour créer le patient en db
        Patient newPatient = patientService.createPatient(patientBodyDto, user);
        Health newHealth = healthService.createNewHealth(newPatient, patientBodyDto);
        newPatient.addHealthRecord(newHealth);

        PatientDto patientDto = patientDtoFactory.convert(newPatient);

        return new ResponseEntity<>(patientDto,HttpStatus.CREATED);
    }

    @PutMapping("/patients/{patientId}")
    public ResponseEntity<PatientDto> updatePatient(
            @PathVariable(value = "patientId") Long patientId,
            @RequestBody PatientBodyDto patientBodyDto) {

        LOG.info("Enter method updatePatient");

        // Sauvegarder les modifications via le service
        Patient updatedPatient = patientService.updatePatient(patientId, patientBodyDto);
        LOG.info("Patient inofs update for patient :", updatedPatient);
        Health health = healthService.createNewHealth(updatedPatient, patientBodyDto);
        LOG.info("New health created : ", health);
        updatedPatient.addHealthRecord(health);
        LOG.info("Health add to list of healths to patient : ", updatedPatient.getId());

        // Conversion du patient mis à jour en DTO pour la réponse
        PatientDto responseDto = patientDtoFactory.convert(updatedPatient);

        // Retourner une réponse avec le statut 200 (OK) et le patient mis à jour
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }
}
