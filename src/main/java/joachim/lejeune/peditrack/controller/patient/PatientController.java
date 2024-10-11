package joachim.lejeune.peditrack.controller.patient;

import joachim.lejeune.peditrack.bodyDto.PatientBodyDto;
import joachim.lejeune.peditrack.dto.PatientDto;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.junit.Assert.assertNotNull;

@RestController
public class PatientController {
    private static final Logger LOG = LoggerFactory.getLogger(PatientController.class);
    private final PatientService patientService;
    private final HealthService healthService;
    private final PatientDtoFactory patientDtoFactory;

    public PatientController(PatientService patientService, HealthService healthService, PatientDtoFactory patientDtoFactory) {
        this.patientService = patientService;
        this.healthService = healthService;
        this.patientDtoFactory = patientDtoFactory;
    }

    @GetMapping("/patients")
    public ResponseEntity<List<PatientDto>> getAllPatients() throws Exception {
        LOG.info("Enter method getAllPatients");

        List<PatientDto> patientDtos = patientService.getAllPatients()
                .stream()
                .map(patient -> patientDtoFactory.convert(patient))
                .toList();
        return new ResponseEntity<>(patientDtos, HttpStatus.OK);
    }

    @GetMapping("/patients/{patientId}")
    public ResponseEntity<PatientDto> getPatient(@PathVariable(value = "patientId") Long patientId) throws PatientNotFoundException {
        LOG.info("Enter method getPatient");

        return patientService.getPatientById(patientId)
                .map(patient -> new ResponseEntity<>(patientDtoFactory.convert(patient), HttpStatus.OK))
                .orElseThrow(() -> new PatientNotFoundException("No patient found for id" + patientId));
    }

    @PostMapping("/patients")
    public ResponseEntity<PatientDto> createPatient(@RequestBody PatientBodyDto patientBodyDto) {
        LOG.info("Enter method createPatient");
        assertNotNull("Patient name must be not null",patientBodyDto.getName());
        assertNotNull("Patient firstname must be not null", patientBodyDto.getFirstname());

        // Appel au service pour créer le patient en db
        Patient newPatient = patientService.createPatient(patientBodyDto);
        Health newHealth = healthService.createNewHealth(newPatient, patientBodyDto);

        // Retourner une réponse avec le statut 201 (Created) et le patient créé
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/patients/{patientId}")
    public ResponseEntity<PatientDto> updatePatient(
            @PathVariable(value = "patientId") Long patientId,
            @RequestBody PatientBodyDto patientBodyDto) throws PatientNotFoundException {

        LOG.info("Enter method updatePatient");

        // Sauvegarder les modifications via le service
        Patient updatedPatient = patientService.updatePatient(patientId, patientBodyDto);

        Health health = healthService.createNewHealth(updatedPatient, patientBodyDto);
        updatedPatient.addHealthRecord(health);

        // Conversion du patient mis à jour en DTO pour la réponse
        PatientDto responseDto = patientDtoFactory.convert(updatedPatient);

        // Retourner une réponse avec le statut 200 (OK) et le patient mis à jour
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
