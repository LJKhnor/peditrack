package joachim.lejeune.peditrack.model.patient;

import jakarta.persistence.*;
import joachim.lejeune.peditrack.model.person.Person;

import java.time.OffsetDateTime;

@Entity
@Table(name = "patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "num_tel")
    private String numTel;

    @Column(name = "birthdate")
    private OffsetDateTime birthdate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_of_contact_id", referencedColumnName = "id")
    private Person personOfContact;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "referenced_by_id", referencedColumnName = "id")
    private Person referenceBy;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    private Person doctorId;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getNumTel() {
        return numTel;
    }

    public OffsetDateTime getBirthdate() {
        return birthdate;
    }

    public Person getPersonOfContact() {
        return personOfContact;
    }

    public Person getReferenceBy() {
        return referenceBy;
    }

    public Person getDoctorId() {
        return doctorId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public void setBirthdate(OffsetDateTime birthdate) {
        this.birthdate = birthdate;
    }

    public void setPersonOfContact(Person personOfContact) {
        this.personOfContact = personOfContact;
    }

    public void setReferenceBy(Person referenceBy) {
        this.referenceBy = referenceBy;
    }

    public void setDoctorId(Person doctorId) {
        this.doctorId = doctorId;
    }
}
