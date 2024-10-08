package joachim.lejeune.peditrack.model.patient;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

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
    private Patient patient;

    @Column(name = "health_group")
    private int health_group;
    @Column(name = "diabetes")
    private int diabetes;
    @Column(name = "date_consultation")
    private Date date_consultation;

    @Column(name = "is_with_heart_problems")
    private boolean is_with_heart_problems;
    @Column(name = "is_with_bleeding_disorder")
    private boolean is_with_bleeding_disorder;
    @Column(name = "is_with_thyroid_disorder")
    private boolean is_with_thyroid_disorder;
    @Column(name = "has_knee_prothesis")
    private boolean has_knee_prothesis;
    @Column(name = "has_hip_prothesis")
    private boolean has_hip_prothesis;
    @Column(name = "has_recent_diseases")
    private boolean has_recent_diseases;
    @Column(name = "has_recent_operation")
    private boolean has_recent_operation;

    @ElementCollection
    @Column(name = "allergies")
    private List<String> allergies;

    @ElementCollection
    @Column(name = "medicines")
    private List<String> medicines;

    @Column(name = "skin")
    private String skin;
    @Column(name = "feet")
    private String feet;
    @Column(name = "sweating")
    private String sweating;
    @Column(name = "footnotes")
    private String footnotes;
    @Column(name = "circulation")
    private String circulation;
    @Column(name = "dermatosis")
    private String dermatosis;
    @Column(name = "foot_deformity")
    private String foot_deformity;
    @Column(name = "nail_disease")
    private String nail_disease;
    @Column(name = "shoes_condition")
    private String shoes_condition;

    @Column(name = "cares")
    private String cares;
    @Column(name = "products_used")
    private String products_used;
    @Column(name = "materials_used")
    private String materials_used;
    @Column(name = "possible_injuries")
    private String possible_injuries;
    @Column(name = "advice_given")
    private String advice_given;

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

    public int getHealth_group() {
        return health_group;
    }

    public void setHealth_group(int health_group) {
        this.health_group = health_group;
    }

    public int getDiabetes() {
        return diabetes;
    }

    public void setDiabetes(int diabetes) {
        this.diabetes = diabetes;
    }

    public Date getDate_consultation() {
        return date_consultation;
    }

    public void setDate_consultation(Date date_consultation) {
        this.date_consultation = date_consultation;
    }

    public boolean isIs_with_heart_problems() {
        return is_with_heart_problems;
    }

    public void setIs_with_heart_problems(boolean is_with_heart_problems) {
        this.is_with_heart_problems = is_with_heart_problems;
    }

    public boolean isIs_with_bleeding_disorder() {
        return is_with_bleeding_disorder;
    }

    public void setIs_with_bleeding_disorder(boolean is_with_bleeding_disorder) {
        this.is_with_bleeding_disorder = is_with_bleeding_disorder;
    }

    public boolean isIs_with_thyroid_disorder() {
        return is_with_thyroid_disorder;
    }

    public void setIs_with_thyroid_disorder(boolean is_with_thyroid_disorder) {
        this.is_with_thyroid_disorder = is_with_thyroid_disorder;
    }

    public boolean isHas_knee_prothesis() {
        return has_knee_prothesis;
    }

    public void setHas_knee_prothesis(boolean has_knee_prothesis) {
        this.has_knee_prothesis = has_knee_prothesis;
    }

    public boolean isHas_hip_prothesis() {
        return has_hip_prothesis;
    }

    public void setHas_hip_prothesis(boolean has_hip_prothesis) {
        this.has_hip_prothesis = has_hip_prothesis;
    }

    public boolean isHas_recent_diseases() {
        return has_recent_diseases;
    }

    public void setHas_recent_diseases(boolean has_recent_diseases) {
        this.has_recent_diseases = has_recent_diseases;
    }

    public boolean isHas_recent_operation() {
        return has_recent_operation;
    }

    public void setHas_recent_operation(boolean has_recent_operation) {
        this.has_recent_operation = has_recent_operation;
    }

    public List<String> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<String> allergies) {
        this.allergies = allergies;
    }

    public List<String> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<String> medicines) {
        this.medicines = medicines;
    }

    public String getSkin() {
        return skin;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }

    public String getFeet() {
        return feet;
    }

    public void setFeet(String feet) {
        this.feet = feet;
    }

    public String getSweating() {
        return sweating;
    }

    public void setSweating(String sweating) {
        this.sweating = sweating;
    }

    public String getFootnotes() {
        return footnotes;
    }

    public void setFootnotes(String footnotes) {
        this.footnotes = footnotes;
    }

    public String getCirculation() {
        return circulation;
    }

    public void setCirculation(String circulation) {
        this.circulation = circulation;
    }

    public String getDermatosis() {
        return dermatosis;
    }

    public void setDermatosis(String dermatosis) {
        this.dermatosis = dermatosis;
    }

    public String getFoot_deformity() {
        return foot_deformity;
    }

    public void setFoot_deformity(String foot_deformity) {
        this.foot_deformity = foot_deformity;
    }

    public String getNail_disease() {
        return nail_disease;
    }

    public void setNail_disease(String nail_disease) {
        this.nail_disease = nail_disease;
    }

    public String getShoes_condition() {
        return shoes_condition;
    }

    public void setShoes_condition(String shoes_condition) {
        this.shoes_condition = shoes_condition;
    }

    public String getCares() {
        return cares;
    }

    public void setCares(String cares) {
        this.cares = cares;
    }

    public String getProducts_used() {
        return products_used;
    }

    public void setProducts_used(String products_used) {
        this.products_used = products_used;
    }

    public String getMaterials_used() {
        return materials_used;
    }

    public void setMaterials_used(String materials_used) {
        this.materials_used = materials_used;
    }

    public String getPossible_injuries() {
        return possible_injuries;
    }

    public void setPossible_injuries(String possible_injuries) {
        this.possible_injuries = possible_injuries;
    }

    public String getAdvice_given() {
        return advice_given;
    }

    public void setAdvice_given(String advice_given) {
        this.advice_given = advice_given;
    }
}
