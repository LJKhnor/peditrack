package joachim.lejeune.peditrack.dto.factory;

import joachim.lejeune.peditrack.dto.HealthDto;
import joachim.lejeune.peditrack.model.enums.*;
import joachim.lejeune.peditrack.model.patient.Health;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class HealthDtoFactory {
    public HealthDto convert(Health newHealth) {
        HealthDto dto = new HealthDto();

        dto.setGroupType((Optional.ofNullable(newHealth.getGroupType()).orElse(GroupType.NONE).getValue()));
        dto.setDiabeteType((Optional.ofNullable(newHealth.getDiabeteType()).orElse(DiabeteType.NONE)).getValue());
        dto.setWithHeartDisorder(newHealth.isWithHeartDisorder());
        dto.setWithBleedingDisorder(newHealth.isWithBleedingDisorder());
        dto.setWithThyroideDisorder(newHealth.isWithThyroidDisorder());
        dto.setHasHipOrKneeProsthesis(newHealth.isHasHipOrKneeProthesis());
        dto.setHasRecentDiseases(newHealth.isHasRecentDiseases());
        dto.setHasRecentOperations(newHealth.isHasRecentOperation());
        dto.setAllergies(newHealth.getAllergies());
        dto.setDrugs(newHealth.getDrugs());
        dto.setSkinType(Optional.ofNullable(newHealth.getSkinType()).orElse(SkinType.NORMAL).getLabel());
        dto.setFootType((Optional.ofNullable(newHealth.getFootType()).orElse(FootType.NORMAL)).getLabel());
        dto.setSweatType((Optional.ofNullable(newHealth.getSweatType()).orElse(SweatType.NONE)).getLabel());
        dto.setRemarkType((Optional.ofNullable(newHealth.getRemarkType()).orElse(RemarkType.NONE)).getLabel());
        dto.setCirculationType((Optional.ofNullable(newHealth.getCirculationType()).orElse(CirculationType.NONE)).getLabel());
        dto.setDermatosisType((Optional.ofNullable(newHealth.getDermatosisType()).orElse(DermatosisType.NONE)).getLabel());
        dto.setFootDeformityType((Optional.ofNullable(newHealth.getFootDeformityType()).orElse(FootDeformityType.NONE)).getLabel());
        dto.setNailConditionType((Optional.ofNullable(newHealth.getNailConditionType()).orElse(NailConditionType.NONE)).getLabel());
        dto.setCareDate(newHealth.getCareDate());
        dto.setCare(newHealth.getAdvice());
        dto.setUsedProducts(newHealth.getProductsUsed());
        dto.setUsedTools(newHealth.getMaterialsUsed());
        dto.setPossibleWounds(newHealth.getPossibleInjuries());
        dto.setAdvice(newHealth.getAdvice());

        return dto;
    }
}
