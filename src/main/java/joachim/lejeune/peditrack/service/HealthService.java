package joachim.lejeune.peditrack.service;

import joachim.lejeune.peditrack.bodyDto.PatientBodyDto;
import joachim.lejeune.peditrack.model.enums.DiabeteType;
import joachim.lejeune.peditrack.model.enums.FootType;
import joachim.lejeune.peditrack.model.enums.GroupType;
import joachim.lejeune.peditrack.model.enums.SkinType;
import joachim.lejeune.peditrack.model.patient.Health;
import joachim.lejeune.peditrack.model.patient.Patient;
import joachim.lejeune.peditrack.repository.HealthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HealthService {
    @Autowired
    private HealthRepository healthRepository;

    public Health createNewHealth(Patient patient, PatientBodyDto patientBodyDto) {
        Health health = new Health();
        health.setPatient(patient);
        health.setGroupType(GroupType.valueForCode(patientBodyDto.getGroupType()));
        health.setDiabeteType(DiabeteType.valueForCode(patientBodyDto.getDiabeteType()));

        health.setSkinType(SkinType.valueForLabel(Optional.ofNullable(patientBodyDto.getSkinType()).orElse("")));
        Optional.ofNullable(patientBodyDto.getFootType()).ifPresent(health::setFootType);

        return healthRepository.save(health);
    }
}
