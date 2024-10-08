package joachim.lejeune.peditrack.bodyDto;

import java.time.OffsetDateTime;
import java.util.Optional;

public class PatientBodyDto {
    private String name;
    private String firstname;
    private String numTel;
    private OffsetDateTime birthdate;
    private String personOfcontact;
    private String personOfcontactNumTel;
    private String referenceBy;
    private String doctor;

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

    public Optional<String> getNumTel() {
        return Optional.ofNullable(numTel);
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public Optional<OffsetDateTime> getBirthdate() {
        return Optional.ofNullable(birthdate);
    }

    public void setBirthdate(OffsetDateTime birthdate) {
        this.birthdate = birthdate;
    }

    public Optional<String> getPersonOfcontact() {
        return Optional.ofNullable(personOfcontact);
    }

    public void setPersonOfcontact(String personOfcontact) {
        this.personOfcontact = personOfcontact;
    }

    public Optional<String> getPersonOfcontactNumTel() {
        return Optional.ofNullable(personOfcontactNumTel);
    }

    public void setPersonOfcontactNumTel(String personOfcontactNumTel) {
        this.personOfcontactNumTel = personOfcontactNumTel;
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
}
