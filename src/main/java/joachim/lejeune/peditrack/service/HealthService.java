package joachim.lejeune.peditrack.service;

import joachim.lejeune.peditrack.bodyDto.PatientBodyDto;
import joachim.lejeune.peditrack.model.enums.*;
import joachim.lejeune.peditrack.model.patient.Health;
import joachim.lejeune.peditrack.model.patient.Patient;
import joachim.lejeune.peditrack.repository.HealthRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Optional;

@Service
public class HealthService {
    private static final Logger LOG = LoggerFactory.getLogger(HealthService.class);
    @Autowired
    private HealthRepository healthRepository;

    public Health createNewHealth(Patient patient, PatientBodyDto patientBodyDto) {
        LOG.info("Enter method createNewHealth for patient id :", patient.getId());
        Health health = new Health();
        health.setPatient(patient);
        health.setGroupType(GroupType.valueForCode(patientBodyDto.getGroupType()));
        health.setDiabeteType(DiabeteType.valueForCode(patientBodyDto.getDiabeteType()));

        health.setWithHeartDisorder(patientBodyDto.isWithHeartDisorder());
        health.setWithBleedingDisorder(patientBodyDto.isWithBleedingDisorder());
        health.setWithThyroidDisorder(patientBodyDto.isWithThyroideDisorder());
        health.setHasHipProthesis(patientBodyDto.isHasHipProsthesis());
        health.setHasKneeProthesis(patientBodyDto.isHasKneeProsthesis());
        health.setHasRecentDiseases(patientBodyDto.isHasRecentDiseases());
        health.setHasRecentOperation(patientBodyDto.isHasRecentOperations());
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

        patientBodyDto.getCareDate().ifPresent(s -> health.setDateConsultation(OffsetDateTime.parse(s)));
        health.setCares(patientBodyDto.getCare());
        health.setProductsUsed(patientBodyDto.getProductsUsed());
        health.setMaterialsUsed(patientBodyDto.getMaterialsUsed());
        health.setPossibleInjuries(patientBodyDto.getPossibleInjuries());
        health.setAdvice(patientBodyDto.getAdvice());

        return healthRepository.save(health);
    }
}
