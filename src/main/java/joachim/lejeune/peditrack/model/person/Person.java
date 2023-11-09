package joachim.lejeune.peditrack.model.person;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Person {
    @Id
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name="first_name")
    private String firstName;
    @Column(name="num_tel")
    private String numTel;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) && Objects.equals(name, person.name) && Objects.equals(firstName, person.firstName) && Objects.equals(numTel, person.numTel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, firstName, numTel);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", firstName='" + firstName + '\'' +
                ", numTel='" + numTel + '\'' +
                '}';
    }
}
