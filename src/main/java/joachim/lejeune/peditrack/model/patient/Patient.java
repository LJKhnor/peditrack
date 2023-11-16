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
    @Column(name = "first_name")
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
}
