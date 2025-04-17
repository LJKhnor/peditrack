package joachim.lejeune.peditrack.model.patient;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import joachim.lejeune.peditrack.model.user.User;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.awt.geom.Point2D;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "Patient",
        indexes = {
                @Index(name = "idx_patient_id", columnList = "id")
        }
)
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "firstname", nullable = false)
    private String firstName;

    @Column(name = "num_tel")
    private String phoneNum;

    @Column(name = "birthdate")
    private OffsetDateTime birthdate;

    @Column(name = "mail")
    private String email;

    @Column(name = "person_of_contact")
    private String personOfContact;

    @Column(name = "person_of_contact_phone_number")
    private String personOfContactPhoneNumber;

    @Column(name = "referenced_by")
    private String referenceBy;

    @Column(name = "doctor")
    private String doctor;
    @Column(name = "comments")
    private String comments;
    @Column(name = "address")
    private String address;
    @Column(name = "locality")
    private String locality;
    @Column(name = "postal_code")
    private String postal_code;
    @Column(name = "city")
    private String city;
    @Column(name = "mutual")
    private String mutual;
    // Relation OneToMany avec Health
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Health> healthRecords;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false) // La clé étrangère vers User
    private User user;

    @Column(name = "pointX")
    private Double pointX;

    @Column(name = "pointY")
    private Double pointY;

    public Patient() {
    }

    public List<Health> getHealthRecords() {
        return healthRecords;
    }

    public void setHealthRecords(List<Health> healthRecords) {
        this.healthRecords = healthRecords;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMutual() {
        return mutual;
    }

    public void setMutual(String mutual) {
        this.mutual = mutual;
    }


    public void addHealthRecord(Health health) {
        if(this.healthRecords != null){
            this.healthRecords.add(health);
        } else {
            this.healthRecords = Arrays.asList(health);
        }
    }
    public Health getLastHealth(){
        if(getHealthRecords().size() == 0){
            return new Health();
        }
        return getHealthRecords().get(getHealthRecords().size() - 1);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Point2D getPoint2D() {
        return new Point2D.Double(this.pointX, this.pointY);
    }
    public void setPoint(Double pointXValue, Double pointYValue){
        this.pointX = pointXValue;
        this.pointY = pointYValue;
    }

    public boolean hasCoordinates() {
        return this.pointX != null && this.pointY != null;
    }

    public boolean hasBeenGeoloc() {
        return this.pointX != null && this.pointY != null;
    }
}
