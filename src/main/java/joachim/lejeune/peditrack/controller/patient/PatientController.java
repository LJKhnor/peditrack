package joachim.lejeune.peditrack.controller.patient;

import joachim.lejeune.peditrack.dto.PatientDto;
import joachim.lejeune.peditrack.dto.factory.PatientDtoFactory;
import joachim.lejeune.peditrack.exceptions.PatientNotFoundException;
import joachim.lejeune.peditrack.model.patient.Patient;
import joachim.lejeune.peditrack.service.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PatientController {
    private static final Logger LOG = LoggerFactory.getLogger(PatientController.class);
    private final PatientService patientService;
    private final PatientDtoFactory patientDtoFactory;

    public PatientController(PatientService patientService, PatientDtoFactory patientDtoFactory) {
        this.patientService = patientService;
        this.patientDtoFactory = patientDtoFactory;
    }

    @GetMapping("/patients")
    public List<Patient> getAllPatients() throws Exception {
        LOG.info("Enter method getAllPatients");

        return patientService.getAllPatients();
    }

    @GetMapping("/patients/{patientId}")
    public ResponseEntity<PatientDto> getPatient(@PathVariable(value = "patientId") Long patientId) throws PatientNotFoundException {
        LOG.info("Enter method getPatient");

        return patientService.getPatientById(patientId)
                .map(patient -> new ResponseEntity<>(patientDtoFactory.convert(patient), HttpStatus.ACCEPTED))
                .orElseThrow(() -> new PatientNotFoundException("No patient found for id" + patientId));
    }
}
