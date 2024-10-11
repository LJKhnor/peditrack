package joachim.lejeune.peditrack.bodyDto;

import joachim.lejeune.peditrack.model.enums.*;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.Optional;

public class PatientBodyDto {
    // informations personnelles
    private String name;
    private String firstname;
    private String phoneNum;
    private OffsetDateTime birthdate;
    private String address;
    private String email;
    private String personOfContact;
    private String personOfContactPhoneNumber;
    private String referenceBy;
    private String doctor;
    private String mutual;
    private String comments;
    // informations médicales
    private GroupType groupType;
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
    private SkinType skinType;
    private FootType footType;
    private SweatType sweatType;
    private RemarkType remarkType;
    private CirculationType circulationType;
    private DermatosisType dermatosisType;
    private FootDeformityType footDeformityType;
    private NailConditionType nailConditionType;
    // soins prodigués
    private OffsetDateTime careDate;
    private String care;
    private String productsUsed;
    private String materialsUsed;
    private String possibleInjuries;
    private String advice;

    public PatientBodyDto() {
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

    public Optional<OffsetDateTime> getBirthdate() {
        return Optional.ofNullable(birthdate);
    }

    public void setBirthdate(OffsetDateTime birthdate) {
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

    public GroupType getGroupType() {
        return groupType;
    }

    public void setGroupType(GroupType groupType) {
        this.groupType = groupType;
    }

    public DiabeteType getDiabeteType() {
        return diabeteType;
    }

    public void setDiabeteType(DiabeteType diabeteType) {
        this.diabeteType = diabeteType;
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

    public SkinType getSkinType() {
        return skinType;
    }

    public void setSkinType(SkinType skinType) {
        this.skinType = skinType;
    }

    public FootType getFootType() {
        return footType;
    }

    public void setFootType(FootType footType) {
        this.footType = footType;
    }

    public SweatType getSweatType() {
        return sweatType;
    }

    public void setSweatType(SweatType sweatType) {
        this.sweatType = sweatType;
    }

    public RemarkType getRemarkType() {
        return remarkType;
    }

    public void setRemarkType(RemarkType remarkType) {
        this.remarkType = remarkType;
    }

    public CirculationType getCirculationType() {
        return circulationType;
    }

    public void setCirculationType(CirculationType circulationType) {
        this.circulationType = circulationType;
    }

    public DermatosisType getDermatosisType() {
        return dermatosisType;
    }

    public void setDermatosisType(DermatosisType dermatosisType) {
        this.dermatosisType = dermatosisType;
    }

    public FootDeformityType getFootDeformityType() {
        return footDeformityType;
    }

    public void setFootDeformityType(FootDeformityType footDeformityType) {
        this.footDeformityType = footDeformityType;
    }

    public NailConditionType getNailConditionType() {
        return nailConditionType;
    }

    public void setNailConditionType(NailConditionType nailConditionType) {
        this.nailConditionType = nailConditionType;
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
