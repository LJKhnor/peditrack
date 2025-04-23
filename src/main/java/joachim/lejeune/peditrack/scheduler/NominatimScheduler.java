package joachim.lejeune.peditrack.scheduler;

import joachim.lejeune.peditrack.NominatimUtility;
import joachim.lejeune.peditrack.model.patient.Patient;
import joachim.lejeune.peditrack.service.PatientService;
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


    public NominatimScheduler(PatientService patientService) {
        this.patientService = patientService;
    }
    @Scheduled(cron = "0 * * * * *")
    public void fetchGeolocPositionForPatientAddress() {
        LOG.info("Enter method fetchGeolocPositionForPatientAddress in NominatimScheduler class");

        List<Patient> patients = patientService.getAllPatients();

        for (Patient patient : patients) {
            if (!patient.hasCoordinates()) {
                try {
                    Point2D point2D = NominatimUtility.AdresseConverter(patient.getAddress());
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
}
