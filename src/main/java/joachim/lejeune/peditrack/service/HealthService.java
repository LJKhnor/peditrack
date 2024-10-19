package joachim.lejeune.peditrack.service;

import joachim.lejeune.peditrack.bodyDto.PatientBodyDto;
import joachim.lejeune.peditrack.model.enums.*;
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
        health.setFootType(FootType.valueForLabel(Optional.ofNullable(patientBodyDto.getFootType()).orElse("")));
        health.setSweatType(SweatType.valueForLabel(Optional.ofNullable(patientBodyDto.getSweatType()).orElse("")));
        health.setRemarkType(RemarkType.valueForLabel(Optional.ofNullable(patientBodyDto.getRemarkType()).orElse("")));

        return healthRepository.save(health);
    }
}
