package joachim.lejeune.peditrack.dto;

import joachim.lejeune.peditrack.model.person.Person;

import java.time.OffsetDateTime;

public class PatientDto {
    private Long id;
    private String name;
    private String firstName;
    private String numTel;
    private OffsetDateTime birthdate;
    private Person personOfContact;
    private Person referenceBy;
    private Person doctorId;

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
