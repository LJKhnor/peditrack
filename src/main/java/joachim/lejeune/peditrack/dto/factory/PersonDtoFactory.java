package joachim.lejeune.peditrack.dto.factory;

import joachim.lejeune.peditrack.dto.PersonDto;
import joachim.lejeune.peditrack.model.person.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonDtoFactory {
    public PersonDtoFactory() {
    }

    public PersonDto convert(Person person) {
        PersonDto dto = new PersonDto();
        dto.setName(person.getName());
        dto.setFirstName(person.getFirstName());
        return dto;
    }
}
