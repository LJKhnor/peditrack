package joachim.lejeune.peditrack.controller.person;

import joachim.lejeune.peditrack.dto.factory.PersonDtoFactory;
import joachim.lejeune.peditrack.model.person.Person;
import joachim.lejeune.peditrack.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
public class PersonController {
    private static final Logger LOG = LoggerFactory.getLogger(PersonController.class);
    private final PersonService personService;
    private final PersonDtoFactory personDtoFactory;

    public PersonController(PersonService personService, PersonDtoFactory personDtoFactory) {
        this.personService = personService;
        this.personDtoFactory = personDtoFactory;
    }
    // CREATE: Add a new person
    @PostMapping
    public Person createPerson(@RequestBody Person person) {
        return personService.save(person);
    }

    // READ: Get all persons
    @GetMapping
    public List<Person> getAllPersons() {
        return personService.findAll();
    }

    // READ: Get person by ID
    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
        Optional<Person> person = personService.findById(id);
        if (person.isPresent()) {
            return ResponseEntity.ok(person.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // UPDATE: Update a person
    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody Person personDetails) {
        Optional<Person> optionalPerson = personService.findById(id);
        if (optionalPerson.isPresent()) {
            Person person = optionalPerson.get();
            person.setName(personDetails.getName());
            person.setFirstName(personDetails.getFirstName());
            person.setNumTel(personDetails.getNumTel());
            Person updatedPerson = personService.save(person);
            return ResponseEntity.ok(updatedPerson);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE: Delete a person
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePerson(@PathVariable Long id) {
        Optional<Person> person = personService.findById(id);
        return  personService.findById(id)
                .map(p -> {
                    personService.delete(p.getId());
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
