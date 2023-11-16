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
}
