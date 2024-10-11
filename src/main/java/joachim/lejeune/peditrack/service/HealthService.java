package joachim.lejeune.peditrack.service;

import joachim.lejeune.peditrack.bodyDto.PatientBodyDto;
import joachim.lejeune.peditrack.model.patient.Health;
import joachim.lejeune.peditrack.model.patient.Patient;
import joachim.lejeune.peditrack.repository.HealthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HealthService {
    @Autowired
    private HealthRepository healthRepository;

    public Health createNewHealth(Patient patient, PatientBodyDto patientBodyDto) {
        Health health = new Health();
        health.setPatient(patient);
        health.setGroupType(patientBodyDto.getGroupType());
        health.setDiabeteType(patientBodyDto.getDiabeteType());

        return healthRepository.save(health);
    }
}
