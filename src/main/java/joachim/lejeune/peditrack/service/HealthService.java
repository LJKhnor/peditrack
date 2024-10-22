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

        health.setWithHeartDisorder(patientBodyDto.isWithHeartDisorder());
        health.setWithBleedingDisorder(patientBodyDto.isWithBleedingDisorder());
        health.setWithThyroidDisorder(patientBodyDto.isWithThyroidDisorder());
        health.setHasHipProthesis(patientBodyDto.isHasHipProthesis());
        health.setHasKneeProthesis(patientBodyDto.isHasKneeProthesis());
        health.setHasRecentDiseases(patientBodyDto.isHasRecentDiseases());
        health.setHasRecentOperation(patientBodyDto.isHasRecentOperation());
        health.setAllergies(patientBodyDto.getAllergies());
        health.setDrugs(patientBodyDto.getDrugs());

        health.setSkinType(SkinType.valueForLabel(Optional.ofNullable(patientBodyDto.getSkinType()).orElse("")));
        health.setFootType(FootType.valueForLabel(Optional.ofNullable(patientBodyDto.getFootType()).orElse("")));
        health.setSweatType(SweatType.valueForLabel(Optional.ofNullable(patientBodyDto.getSweatType()).orElse("")));
        health.setRemarkType(RemarkType.valueForLabel(Optional.ofNullable(patientBodyDto.getRemarkType()).orElse("")));
        health.setCirculationType(CirculationType.valueForLabel(Optional.ofNullable(patientBodyDto.getCirculationType()).orElse("")));
        health.setDermatosisType(DermatosisType.valueForLabel(Optional.ofNullable(patientBodyDto.getDermatosisType()).orElse("")));

        health.setNailConditionType(NailConditionType.valueForLabel(Optional.ofNullable(patientBodyDto.getNailConditionType()).orElse("")));
        health.setFootDeformityType(FootDeformityType.valueForLabel(Optional.ofNullable(patientBodyDto.getFootDeformityType()).orElse("")));

        return healthRepository.save(health);
    }
}
