package joachim.lejeune.peditrack.bodyDto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import joachim.lejeune.peditrack.model.enums.*;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;
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
    private Date dateConsultation;
    private boolean isWithHeartDisorder;
    private boolean isWithBleedingDisorder;
    private boolean isWithThyroidDisorder;
    private boolean hasHipOrKneeProthesis;
    private boolean hasRecentDiseases;
    private boolean hasRecentOperation;
    private String allergies;
    private String drugs;

    private String skinType;
    private String footType;
    private String sweatType;
    private String remarkType;
//    private CirculationType circulationType;
//    private DermatosisType dermatosisType;
//    private FootDeformityType footDeformityType;
//    private NailConditionType nailConditionType;
    // soins prodigués
    @JsonProperty("careDate")
    private OffsetDateTime careDate;
    private String care;
    private String productsUsed;
    private String materialsUsed;
    private String possibleInjuries;
    private String advice;

    public PatientBodyDto() {
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

//    public String getSkinType() {
//        return skinType;
//    }
//
//    public void setSkinType(String skinType) {
//        this.skinType = skinType;
//    }

//    public Optional<String> getFootType() {
//        if(footType == null){
//            return Optional.ofNullable(FootType.NONE.getLabel());
//        }
//        return Optional.ofNullable(footType.getLabel());
//    }
//
//    public void setFootType(String footType) {
//        this.footType = FootType.valueForCode(footType);
//    }
//
//    public SweatType getSweatType() {
//        return sweatType;
//    }
//
//    public void setSweatType(String sweatType) {
//        this.sweatType = SweatType.valueForCode(sweatType);
//    }
//
//    public RemarkType getRemarkType() {
//        return remarkType;
//    }
//
//    public void setRemarkType(String remarkType) {
//        this.remarkType = RemarkType.valueForCode(remarkType);
//    }
//
//    public CirculationType getCirculationType() {
//        return circulationType;
//    }
//
//    public void setCirculationType(String circulationType) {
//        this.circulationType = CirculationType.valueForCode(circulationType);
//    }
//
//    public DermatosisType getDermatosisType() {
//        return dermatosisType;
//    }
//
//    public void setDermatosisType(String dermatosisType) {
//        this.dermatosisType = DermatosisType.valueForCode(dermatosisType);
//    }
//
//    public FootDeformityType getFootDeformityType() {
//        return footDeformityType;
//    }
//
//    public void setFootDeformityType(String footDeformityType) {
//        this.footDeformityType = FootDeformityType.valueForCode(footDeformityType);
//    }
//
//    public NailConditionType getNailConditionType() {
//        return nailConditionType;
//    }
//
//    public void setNailConditionType(String nailConditionType) {
//        this.nailConditionType = NailConditionType.valueForCode(nailConditionType);
//    }

    public OffsetDateTime getCareDate() {
        return careDate;
    }

    public void setCareDate(String careDate) {
        this.careDate = LocalDate.parse(careDate).atStartOfDay().atOffset(ZoneOffset.UTC);
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

    public Date getDateConsultation() {
        return dateConsultation;
    }

    public void setDateConsultation(Date dateConsultation) {
        this.dateConsultation = dateConsultation;
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

    public boolean isWithThyroidDisorder() {
        return isWithThyroidDisorder;
    }

    public void setWithThyroidDisorder(boolean withThyroidDisorder) {
        isWithThyroidDisorder = withThyroidDisorder;
    }

    public boolean isHasHipOrKneeProthesis() {
        return hasHipOrKneeProthesis;
    }

    public void setHasHipOrKneeProthesis(boolean hasHipOrKneeProthesis) {
        this.hasHipOrKneeProthesis = hasHipOrKneeProthesis;
    }

    public boolean isHasRecentDiseases() {
        return hasRecentDiseases;
    }

    public void setHasRecentDiseases(boolean hasRecentDiseases) {
        this.hasRecentDiseases = hasRecentDiseases;
    }

    public boolean isHasRecentOperation() {
        return hasRecentOperation;
    }

    public void setHasRecentOperation(boolean hasRecentOperation) {
        this.hasRecentOperation = hasRecentOperation;
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
