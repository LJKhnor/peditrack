package joachim.lejeune.peditrack.scheduler;

import joachim.lejeune.peditrack.NominatimUtility;
import joachim.lejeune.peditrack.model.patient.Patient;
import joachim.lejeune.peditrack.model.user.User;
import joachim.lejeune.peditrack.service.PatientService;
import joachim.lejeune.peditrack.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.List;

@Component
public class NominatimScheduler {
    private static final Logger LOG = LoggerFactory.getLogger(NominatimScheduler.class);

    private final PatientService patientService;
    private final UserService userService;


    public NominatimScheduler(PatientService patientService, UserService userService) {
        this.patientService = patientService;
        this.userService = userService;
    }

    @Scheduled(cron = "0 0 0/4 * * *")
    public void fetchGeolocPositionForPatientAddress() {
        LOG.info("Enter method fetchGeolocPositionForPatientAddress in NominatimScheduler class");

        List<Patient> patients = patientService.getAllPatients();

        for (Patient patient : patients) {
            if (!patient.hasCoordinates()) {
                try {
                    Point2D point2D = NominatimUtility.AddressConverter(patient.getAddress());
                    patient.setPoint(point2D.getX(), point2D.getY());
                    patientService.save(patient);

                    // ⏱ Pause de 1 seconde pour respecter les demandes de nominatim
                    Thread.sleep(1000);
                } catch (IOException e) {
                    LOG.error("Erreur lors de la géolocalisation de l'adresse : " + patient.getAddress(), e);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // bonne pratique
                    LOG.warn("Thread interrompu pendant le sleep", e);
                    return;
                }
            }
        }
    }
    @Scheduled(cron = "0 0 0/4 * * *")
    public void fetchGeolocPositionForUserAddress() {
        LOG.info("Enter method fetchGeolocPositionForUserAddress in NominatimScheduler class");

        List<User> users = userService.getAllUsers();
        for (User user : users) {
            if(!user.hasCoordinates()){
                try {
                    Point2D point2D = NominatimUtility.AddressConverter(user.getAddress());
                    user.setPoint(point2D.getX(), point2D.getY());
                    userService.save(user);

                    // ⏱ Pause de 1 seconde pour respecter les demandes de nominatim
                    Thread.sleep(1000);
                } catch (IOException e) {
                    LOG.error("Erreur lors de la géolocalisation de l'adresse : " + user.getAddress(), e);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // bonne pratique
                    LOG.warn("Thread interrompu pendant le sleep", e);
                    return;
                }
            }
        }
    }
}
