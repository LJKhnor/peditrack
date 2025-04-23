package joachim.lejeune.peditrack.controller.data;

import joachim.lejeune.peditrack.controller.auth.UserDetailsImpl;
import joachim.lejeune.peditrack.model.patient.Patient;
import joachim.lejeune.peditrack.service.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/data")
public class DataController {
    private static final Logger LOG = LoggerFactory.getLogger(DataController.class);
    private final PatientService patientService;

    public DataController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/map")
    public ResponseEntity<List<Point2D>> getAllGeolocalistionDataMapForUser() {
        LOG.info("Enter method getAllGeolocalistionDataMapForUser");
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Patient> allPatients = patientService.findByUser(userDetails.getUser());
        List<Point2D> points2DSDto = new ArrayList<>();

        for(Patient patient: allPatients){
            if(patient.hasBeenGeoloc())
            points2DSDto.add(patient.getPoint2D());
        }

        return new ResponseEntity<>(points2DSDto, HttpStatus.OK);
    }
}
