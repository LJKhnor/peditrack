package joachim.lejeune.peditrack.model.patient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import joachim.lejeune.peditrack.model.enums.*;

import java.time.OffsetDateTime;
import java.util.Date;

@Entity
@Table(name = "Health",
        indexes = {
                @Index(name = "idx_health_patient", columnList = "id_patient"),
                @Index(name = "idx_health_date", columnList = "date_consultation")
        }
)
public class Health {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_patient", nullable = false)
    @JsonBackReference
    private Patient patient;
    @Enumerated(EnumType.STRING)
    @Column(name = "group_type")
    private GroupType groupType;
    @Enumerated(EnumType.STRING)
    @Column(name = "diabete_type")
    private DiabeteType diabeteType;
    @Column(name = "date_consultation")
    private OffsetDateTime dateConsultation;

    @Column(name = "is_with_heart_disorder")
    private boolean isWithHeartDisorder;
    @Column(name = "is_with_bleeding_disorder")
    private boolean isWithBleedingDisorder;
    @Column(name = "is_with_thyroid_disorder")
    private boolean isWithThyroidDisorder;
    @Column(name = "has_hip_prothesis")
    private boolean hasHipProthesis;
    @Column(name = "has_knee_prothesis")
    private boolean hasKneeProthesis;
    @Column(name = "has_recent_diseases")
    private boolean hasRecentDiseases;
    @Column(name = "has_recent_operation")
    private boolean hasRecentOperation;

    @Column(name = "allergies")
    private String allergies;

    @Column(name = "drugs")
    private String drugs;
    @Enumerated(EnumType.STRING)
    @Column(name = "skin")
    private SkinType skinType;
    @Enumerated(EnumType.STRING)
    @Column(name = "feet")
    private FootType footType;
    @Enumerated(EnumType.STRING)
    @Column(name = "sweating")
    private SweatType sweatType;
    @Enumerated(EnumType.STRING)
    @Column(name = "footnotes")
    private RemarkType remarkType;
    @Enumerated(EnumType.STRING)
    @Column(name = "circulation")
    private CirculationType circulationType;
    @Enumerated(EnumType.STRING)
    @Column(name = "dermatosis")
    private DermatosisType dermatosisType;
    @Enumerated(EnumType.STRING)
    @Column(name = "foot_deformity")
    private FootDeformityType footDeformityType;
    @Enumerated(EnumType.STRING)
    @Column(name = "nail_disease")
    private NailConditionType nailConditionType;
    @Column(name = "shoes_condition")
    private String shoesCondition;

    @Column(name = "cares")
    private String cares;
    @Column(name = "products_used")
    private String productsUsed;
    @Column(name = "materials_used")
    private String materialsUsed;
    @Column(name = "possible_injuries")
    private String possibleInjuries;
    @Column(name = "advice_given")
    private String advice;

    public Health() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
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

    public OffsetDateTime getDateConsultation() {
        return dateConsultation;
    }

    public void setDateConsultation(OffsetDateTime dateConsultation) {
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

    public boolean isHasHipProthesis() {
        return hasHipProthesis;
    }

    public void setHasHipProthesis(boolean hasHipProthesis) {
        this.hasHipProthesis = hasHipProthesis;
    }

    public boolean isHasKneeProthesis() {
        return hasKneeProthesis;
    }

    public void setHasKneeProthesis(boolean hasKneeProthesis) {
        this.hasKneeProthesis = hasKneeProthesis;
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

    public String getShoesCondition() {
        return shoesCondition;
    }

    public void setShoesCondition(String shoesCondition) {
        this.shoesCondition = shoesCondition;
    }

    public String getCares() {
        return cares;
    }

    public void setCares(String cares) {
        this.cares = cares;
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

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }
}
