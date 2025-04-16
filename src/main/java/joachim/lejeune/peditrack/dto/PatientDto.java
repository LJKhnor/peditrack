package joachim.lejeune.peditrack.dto;

import joachim.lejeune.peditrack.model.enums.*;
import joachim.lejeune.peditrack.model.patient.Health;

import java.awt.geom.Point2D;
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
    private Point2D point;


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

    public Point2D getPoint() {
        return point;
    }

    public void setPoint(Point2D point) {
        this.point = point;
    }

    public void setPosition(Point2D point2D) {
        this.point = point2D;
    }
}
