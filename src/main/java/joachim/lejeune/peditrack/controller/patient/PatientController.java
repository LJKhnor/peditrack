package joachim.lejeune.peditrack.controller.patient;

import joachim.lejeune.peditrack.model.patient.Patient;
import joachim.lejeune.peditrack.service.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PatientController {
    private static final Logger LOG = LoggerFactory.getLogger(PatientController.class);
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/patients")
    public List<Patient> getAllPatients() throws Exception {
        LOG.info("Enter method getAllPatients");

        return patientService.findAll();
    }

    @GetMapping("/patients/{patientId}")
    public Patient getPatient(@PathVariable(value = "patientId") Long patientId) throws Exception {
        LOG.info("Enter method getPatient");

        return patientService.findOnePatientById(patientId);
    }
}
