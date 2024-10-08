package joachim.lejeune.peditrack.dto;

import java.time.OffsetDateTime;

public class PatientDto {
    private Long id;
    private String name;
    private String firstname;
    private String phoneNum;
    private OffsetDateTime birthdate;
    private String personOfContact;
    private String personOfContactPhoneNumber;
    private String referenceBy;
    private String doctor;

    public PatientDto() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setBirthdate(OffsetDateTime birthdate) {
        this.birthdate = birthdate;
    }

    public void setPersonOfContact(String personOfContact) {
        this.personOfContact = personOfContact;
    }

    public void setPersonOfContactPhoneNumber(String personOfContactPhoneNumber) {
        this.personOfContactPhoneNumber = personOfContactPhoneNumber;
    }

    public void setReferenceBy(String referenceBy) {
        this.referenceBy = referenceBy;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public OffsetDateTime getBirthdate() {
        return birthdate;
    }

    public String getPersonOfContact() {
        return personOfContact;
    }

    public String getPersonOfContactPhoneNumber() {
        return personOfContactPhoneNumber;
    }

    public String getReferenceBy() {
        return referenceBy;
    }

    public String getDoctor() {
        return doctor;
    }
}
