package joachim.lejeune.peditrack.bodyDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import joachim.lejeune.peditrack.model.enums.*;

import java.util.Optional;

//@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatientBodyDto {
    // informations personnelles
    private String name;
    private String firstname;
    private String phoneNum;
    private String birthdate;
    private String address;
    private String email;
    private String personOfContact;
    private String personOfContactPhoneNumber;
    private String referenceBy;
    private String doctor;
    private String mutual;
    private String comments;

    // informations médicales
    @JsonProperty("groupType")
    private GroupType groupType;
    @JsonProperty("diabeteType")
    private DiabeteType diabeteType;
    @JsonProperty("isWithBleedingDisorder")
    private boolean isWithBleedingDisorder;
    @JsonProperty("isWithHeartDisorder")
    private boolean isWithHeartDisorder;
    @JsonProperty("isWithThyroideDisorder")
    private boolean isWithThyroideDisorder;
    @JsonProperty("hasHipProsthesis")
    private boolean hasHipProsthesis;
    @JsonProperty("hasKneeProsthesis")
    private boolean hasKneeProsthesis;
    @JsonProperty("hasRecentDiseases")
    private boolean hasRecentDiseases;
    @JsonProperty("hasRecentOperations")
    private boolean hasRecentOperations;
    private String allergies;
    private String drugs;

    private String skinType;
    private String footType;
    @JsonProperty("sweatType")
    private String sweatType;
    @JsonProperty("remarkType")
    private String remarkType;
    @JsonProperty("circulationType")
    private String circulationType;
    private String dermatosisType;
    private String footDeformityType;
    private String nailConditionType;

    // soins prodigués
    @JsonProperty("date")
    private String careDate;
    private String care;
    @JsonProperty("usedProducts")
    private String productsUsed;
    @JsonProperty("usedTools")
    private String materialsUsed;
    @JsonProperty("possibleWounds")
    private String possibleInjuries;
    @JsonProperty("advice")
    private String advice;

    public PatientBodyDto() {
    }

    public String getCirculationType() {
        return circulationType;
    }

    public String getDermatosisType() {
        return dermatosisType;
    }

    public String getFootDeformityType() {
        return footDeformityType;
    }

    public String getNailConditionType() {
        return nailConditionType;
    }

    public String getSweatType() {
        return sweatType;
    }

    public String getRemarkType() {
        return remarkType;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public Optional<String> getPhoneNum() {
        return Optional.ofNullable(phoneNum);
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Optional<String> getBirthdate() {
        return Optional.ofNullable(birthdate);
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public Optional<String> getPersonOfContact() {
        return Optional.ofNullable(personOfContact);
    }

    public void setPersonOfContact(String personOfContact) {
        this.personOfContact = personOfContact;
    }

    public Optional<String> getPersonOfContactPhoneNumber() {
        return Optional.ofNullable(personOfContactPhoneNumber);
    }

    public void setPersonOfContactPhoneNumber(String personOfContactPhoneNumber) {
        this.personOfContactPhoneNumber = personOfContactPhoneNumber;
    }

    public Optional<String> getReferenceBy() {
        return Optional.ofNullable(referenceBy);
    }

    public void setReferenceBy(String referenceBy) {
        this.referenceBy = referenceBy;
    }

    public Optional<String> getDoctor() {
        return Optional.ofNullable(doctor);
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public Optional<String> getEmail() {
        return Optional.ofNullable(email);
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Optional<String> getMutual() {
        return Optional.ofNullable(mutual);
    }

    public void setMutual(String mutual) {
        this.mutual = mutual;
    }

    public Optional<String> getComments() {
        return Optional.ofNullable(comments);
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getGroupType() {
        if(groupType == null){
            return 0;
        }
        return groupType.getValue();
    }

    public void setGroupType(int groupType) {
        this.groupType = GroupType.valueForCode(groupType);
    }

    public int getDiabeteType() {
        if(diabeteType == null){
            return 0;
        }
        return diabeteType.getValue();
    }

    public void setDiabeteType(int diabeteType) {
        this.diabeteType = DiabeteType.valueForCode(diabeteType);
    }

    public Optional<String> getAddress() {
        return Optional.ofNullable(address);
    }

    public void setAddress(String address) {
        this.address = address;
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

    public Optional<String> getCareDate() {
        return Optional.ofNullable(careDate);
    }

    public void setCareDate(String careDate) {
        this.careDate = careDate;
    }

    public String getCare() {
        return care;
    }

    public void setCare(String care) {
        this.care = care;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
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

    public boolean isHasHipProsthesis() {
        return hasHipProsthesis;
    }

    public boolean isHasKneeProsthesis() {
        return hasKneeProsthesis;
    }

    public void setGroupType(GroupType groupType) {
        this.groupType = groupType;
    }

    public void setDiabeteType(DiabeteType diabeteType) {
        this.diabeteType = diabeteType;
    }

    public void setHasHipProsthesis(boolean hasHipProsthesis) {
        this.hasHipProsthesis = hasHipProsthesis;
    }

    public void setHasKneeProsthesis(boolean hasKneeProsthesis) {
        this.hasKneeProsthesis = hasKneeProsthesis;
    }

    public void setSweatType(String sweatType) {
        this.sweatType = sweatType;
    }

    public void setRemarkType(String remarkType) {
        this.remarkType = remarkType;
    }

    public void setCirculationType(String circulationType) {
        this.circulationType = circulationType;
    }

    public void setDermatosisType(String dermatosisType) {
        this.dermatosisType = dermatosisType;
    }

    public void setFootDeformityType(String footDeformityType) {
        this.footDeformityType = footDeformityType;
    }

    public void setNailConditionType(String nailConditionType) {
        this.nailConditionType = nailConditionType;
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

    public String getProductsUsed() {
        return productsUsed;
    }

    public void setProductsUsed(String productsUsed) {
        this.productsUsed = productsUsed;
    }

    public String getMaterialsUsed() {
        return materialsUsed;
    }

    public void setMaterialsUsed(String materialsUsed) {
        this.materialsUsed = materialsUsed;
    }

    public String getPossibleInjuries() {
        return possibleInjuries;
    }

    public void setPossibleInjuries(String possibleInjuries) {
        this.possibleInjuries = possibleInjuries;
    }
}
