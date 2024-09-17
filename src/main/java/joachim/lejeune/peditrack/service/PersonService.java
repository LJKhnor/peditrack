package joachim.lejeune.peditrack.service;

import joachim.lejeune.peditrack.model.person.Person;
import joachim.lejeune.peditrack.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for handling business logic related to Person operations.
 */
@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    /**
     * Updates an existing person's details.
     *
     * @param id The ID of the person to update.
     * @param updatedPerson The person entity containing the updated data.
     * @return The updated person entity.
     * @throws RuntimeException If no person with the given ID is found.
     */
    public Person updatePerson(Long id, Person updatedPerson) {
        return personRepository.findById(id)
                .map(person -> {
                    person.setName(updatedPerson.getName());
                    person.setFirstName(updatedPerson.getFirstName());
                    person.setNumTel(updatedPerson.getNumTel());
                    return personRepository.save(person);
                }).orElseThrow(() -> new RuntimeException("Person not found with id " + id));
    }

    /**
     * Deletes a person from the system by their ID.
     *
     * @param id The ID of the person to delete.
     * @throws RuntimeException If no person with the given ID is found.
     */
    public void delete(Long id) {
        if (personRepository.existsById(id)) {
            personRepository.deleteById(id);
        } else {
            throw new RuntimeException("Person not found with id " + id);
        }
    }
    /**
     * Creates a new person in the system.
     *
     * @param person The person entity to create.
     * @return The saved person entity.
     */
    public Person save(Person person) {
        return personRepository.save(person);
    }
    /**
     * Retrieves all persons from the system.
     *
     * @return A list of all persons.
     */
    public List<Person> findAll() {
        return personRepository.findAll();
    }
    /**
     * Retrieves a person by their ID.
     *
     * @param id The ID of the person to retrieve.
     * @return An Optional containing the person if found, or empty if not found.
     */
    public Optional<Person> findById(Long id) {
        return personRepository.findById(id);
    }
}
