package joachim.lejeune.peditrack.dto;

import joachim.lejeune.peditrack.model.enums.*;
import joachim.lejeune.peditrack.model.patient.Health;

import java.time.OffsetDateTime;

public class PatientDto {
    private Long id;
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
    private String heartDiseases;
    private String coagulationDisorder;
    private String thyroideDisorder;
    private String prosthesis;
    private String diseases;
    private String operations;
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
    private String usedProducts;
    private String UsedTools;
    private String PossibleWounds;
    private String advice;

    public PatientDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public OffsetDateTime getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(OffsetDateTime birthdate) {
        this.birthdate = birthdate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPersonOfContact() {
        return personOfContact;
    }

    public void setPersonOfContact(String personOfContact) {
        this.personOfContact = personOfContact;
    }

    public String getPersonOfContactPhoneNumber() {
        return personOfContactPhoneNumber;
    }

    public void setPersonOfContactPhoneNumber(String personOfContactPhoneNumber) {
        this.personOfContactPhoneNumber = personOfContactPhoneNumber;
    }

    public String getReferenceBy() {
        return referenceBy;
    }

    public void setReferenceBy(String referenceBy) {
        this.referenceBy = referenceBy;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getMutual() {
        return mutual;
    }

    public void setMutual(String mutual) {
        this.mutual = mutual;
    }

    public String getComments() {
        return comments;
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

    public String getHeartDiseases() {
        return heartDiseases;
    }

    public void setHeartDiseases(String heartDiseases) {
        this.heartDiseases = heartDiseases;
    }

    public String getCoagulationDisorder() {
        return coagulationDisorder;
    }

    public void setCoagulationDisorder(String coagulationDisorder) {
        this.coagulationDisorder = coagulationDisorder;
    }

    public String getThyroideDisorder() {
        return thyroideDisorder;
    }

    public void setThyroideDisorder(String thyroideDisorder) {
        this.thyroideDisorder = thyroideDisorder;
    }

    public String getProsthesis() {
        return prosthesis;
    }

    public void setProsthesis(String prosthesis) {
        this.prosthesis = prosthesis;
    }

    public String getDiseases() {
        return diseases;
    }

    public void setDiseases(String diseases) {
        this.diseases = diseases;
    }

    public String getOperations() {
        return operations;
    }

    public void setOperations(String operations) {
        this.operations = operations;
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
}
