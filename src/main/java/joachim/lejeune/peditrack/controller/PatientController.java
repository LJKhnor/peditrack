package joachim.lejeune.peditrack.controller;

import joachim.lejeune.peditrack.dto.PatientDto;
import joachim.lejeune.peditrack.service.PatientService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping(path = "/")
    @ResponseBody
    public PatientDto getPatient(@PathVariable Long id) {
        return patientService.findOnePatientById(id);
    }
}
