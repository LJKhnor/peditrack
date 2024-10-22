package joachim.lejeune.peditrack.dto;

import joachim.lejeune.peditrack.model.enums.*;

import java.time.OffsetDateTime;

public class HealthDto {
    // informations médicales
    private int groupType;
    private int diabeteType;
    private boolean isWithHeartDisorder;
    private boolean isWithBleedingDisorder;
    private boolean isWithThyroideDisorder;
    private boolean hasHipProsthesis;
    private boolean hasKneeProsthesis;
    private boolean hasRecentDiseases;
    private boolean hasRecentOperations;
    private String allergies;
    private String drugs;
    private String skinType;
    private String footType;
    private String sweatType;
    private String remarkType;
    private String circulationType;
    private String dermatosisType;
    private String footDeformityType;
    private String nailConditionType;
    // soins prodigués
    private OffsetDateTime careDate;
    private String care;
    private String usedProducts;
    private String UsedTools;
    private String PossibleWounds;
    private String advice;

    public HealthDto() {
    }

    public boolean isHasHipProsthesis() {
        return hasHipProsthesis;
    }

    public void setHasHipProsthesis(boolean hasHipProsthesis) {
        this.hasHipProsthesis = hasHipProsthesis;
    }

    public boolean isHasKneeProsthesis() {
        return hasKneeProsthesis;
    }

    public void setHasKneeProsthesis(boolean hasKneeProsthesis) {
        this.hasKneeProsthesis = hasKneeProsthesis;
    }

    public boolean isWithHeartDisorder() {
        return isWithHeartDisorder;
    }

    public void setWithHeartDisorder(boolean withHeartDisorder) {
        isWithHeartDisorder = withHeartDisorder;
    }

    public boolean isWithBleedingDisorder() {
        return isWithBleedingDisorder;
    }

    public void setWithBleedingDisorder(boolean withBleedingDisorder) {
        isWithBleedingDisorder = withBleedingDisorder;
    }

    public boolean isWithThyroideDisorder() {
        return isWithThyroideDisorder;
    }

    public void setWithThyroideDisorder(boolean withThyroideDisorder) {
        isWithThyroideDisorder = withThyroideDisorder;
    }

    public boolean isHasRecentDiseases() {
        return hasRecentDiseases;
    }

    public void setHasRecentDiseases(boolean hasRecentDiseases) {
        this.hasRecentDiseases = hasRecentDiseases;
    }

    public boolean isHasRecentOperations() {
        return hasRecentOperations;
    }

    public void setHasRecentOperations(boolean hasRecentOperations) {
        this.hasRecentOperations = hasRecentOperations;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getDrugs() {
        return drugs;
    }

    public void setDrugs(String drugs) {
        this.drugs = drugs;
    }

    public OffsetDateTime getCareDate() {
        return careDate;
    }

    public void setCareDate(OffsetDateTime careDate) {
        this.careDate = careDate;
    }

    public String getCare() {
        return care;
    }

    public void setCare(String care) {
        this.care = care;
    }

    public String getUsedProducts() {
        return usedProducts;
    }

    public void setUsedProducts(String usedProducts) {
        this.usedProducts = usedProducts;
    }

    public String getUsedTools() {
        return UsedTools;
    }

    public void setUsedTools(String usedTools) {
        UsedTools = usedTools;
    }

    public String getPossibleWounds() {
        return PossibleWounds;
    }

    public void setPossibleWounds(String possibleWounds) {
        PossibleWounds = possibleWounds;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public int getGroupType() {
        return groupType;
    }

    public void setGroupType(int groupType) {
        this.groupType = groupType;
    }

    public int getDiabeteType() {
        return diabeteType;
    }

    public void setDiabeteType(int diabeteType) {
        this.diabeteType = diabeteType;
    }

    public String getSkinType() {
        return skinType;
    }

    public void setSkinType(String skinType) {
        this.skinType = skinType;
    }

    public String getFootType() {
        return footType;
    }

    public void setFootType(String footType) {
        this.footType = footType;
    }

    public String getSweatType() {
        return sweatType;
    }

    public void setSweatType(String sweatType) {
        this.sweatType = sweatType;
    }

    public String getRemarkType() {
        return remarkType;
    }

    public void setRemarkType(String remarkType) {
        this.remarkType = remarkType;
    }

    public String getCirculationType() {
        return circulationType;
    }

    public void setCirculationType(String circulationType) {
        this.circulationType = circulationType;
    }

    public String getDermatosisType() {
        return dermatosisType;
    }

    public void setDermatosisType(String dermatosisType) {
        this.dermatosisType = dermatosisType;
    }

    public String getFootDeformityType() {
        return footDeformityType;
    }

    public void setFootDeformityType(String footDeformityType) {
        this.footDeformityType = footDeformityType;
    }

    public String getNailConditionType() {
        return nailConditionType;
    }

    public void setNailConditionType(String nailConditionType) {
        this.nailConditionType = nailConditionType;
    }
}
