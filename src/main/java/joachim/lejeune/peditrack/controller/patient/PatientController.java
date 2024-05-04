package joachim.lejeune.peditrack.controller.patient;

import joachim.lejeune.peditrack.model.patient.Patient;
import joachim.lejeune.peditrack.service.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {
    private static final Logger LOG = LoggerFactory.getLogger(PatientController.class);
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public List<Patient> getAllPatients() {
        LOG.info("Enter method getAllPatients");

        return patientService.findAll();
    }

    @GetMapping("/{patientId}")
    @ResponseBody
    public Patient getPatient(@PathVariable(value = "patientId") Long patientId) {
        LOG.info("Enter method getPatient");

        return patientService.findOnePatientById(patientId);
    }
}
