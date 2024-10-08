package joachim.lejeune.peditrack.model.patient;

import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "Patient",
        indexes = {
                @Index(name = "idx_patient_name", columnList = "name"),
                @Index(name = "idx_patient_firstname", columnList = "firstname"),
                @Index(name = "idx_patient_city", columnList = "city")
        },
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "mail"),
                @UniqueConstraint(columnNames = "num_tel")
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
    private String numTel;

    @Column(name = "birthdate")
    private OffsetDateTime birthdate;

    @Column(name = "person_of_contact")
    private String personOfContact;

    @Column(name = "person_of_contact_phone_number")
    private String personOfContactPhoneNumber;
    private String mail;

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

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
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
}
