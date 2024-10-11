package joachim.lejeune.peditrack.service;

import joachim.lejeune.peditrack.bodyDto.PatientBodyDto;
import joachim.lejeune.peditrack.model.patient.Health;
import joachim.lejeune.peditrack.model.patient.Patient;
import joachim.lejeune.peditrack.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Service class for handling business logic related to Patient operations.
 */
@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    /**
     * Creates a new patient in the system.
     *
     * @param patientBodyDto The patient entity to create.
     * @return The saved patient entity.
     */
    public Patient createPatient(PatientBodyDto patientBodyDto) {
        Patient patient = new Patient();
        patient.setName(patientBodyDto.getName());
        patient.setFirstName(patientBodyDto.getFirstname());
        patientBodyDto.getPhoneNum().ifPresent(patient::setPhoneNum);
        patientBodyDto.getBirthdate().ifPresent(patient::setBirthdate);
        patientBodyDto.getEmail().ifPresent(patient::setEmail);
        patientBodyDto.getPersonOfContact().ifPresent(patient::setPersonOfContact);
        patientBodyDto.getPersonOfContactPhoneNumber().ifPresent(patient::setPersonOfContactPhoneNumber);
        patientBodyDto.getReferenceBy().ifPresent(patient::setReferenceBy);
        patientBodyDto.getDoctor().ifPresent(patient::setDoctor);
        patientBodyDto.getComments().ifPresent(patient::setComments);
        patientBodyDto.getAddress().ifPresent(patient::setAddress);
        patientBodyDto.getMutual().ifPresent(patient::setMutual);

        Health health = new Health();
        // todo continuer ici pour svg la santé à la création du patient


        return patientRepository.save(patient);
    }

    /**
     * Retrieves all patients from the system.
     *
     * @return A list of all patients.
     */
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    /**
     * Retrieves a patient by their ID.
     *
     * @param id The ID of the patient to retrieve.
     * @return An Optional containing the patient if found, or empty if not found.
     */
    public Optional<Patient> getPatientById(Long id) {
        return patientRepository.findById(id);
    }

    /**
     * Updates an existing patient's details.
     *
     * @param id The ID of the patient to update.
     * @param updatedPatient The patient entity containing the updated data.
     * @return The updated patient entity.
     * @throws RuntimeException If no patient with the given ID is found.
     */
    public Patient updatePatient(Long id, PatientBodyDto updatedPatient) {
        return patientRepository.findById(id)
                .map(patient -> {
                    patient.setName(updatedPatient.getName());
                    patient.setFirstName(updatedPatient.getFirstname());
                    updatedPatient.getPhoneNum().ifPresent(patient::setPhoneNum);
                    updatedPatient.getBirthdate().ifPresent(patient::setBirthdate);
                    updatedPatient.getAddress().ifPresent(patient::setAddress);
                    updatedPatient.getEmail().ifPresent(patient::setEmail);
                    updatedPatient.getPersonOfContact().ifPresent(patient::setPersonOfContact);
                    updatedPatient.getPersonOfContactPhoneNumber().ifPresent(patient::setPersonOfContactPhoneNumber);
                    updatedPatient.getReferenceBy().ifPresent(patient::setReferenceBy);
                    updatedPatient.getDoctor().ifPresent(patient::setDoctor);
                    updatedPatient.getMutual().ifPresent(patient::setMutual);
                    updatedPatient.getComments().ifPresent(patient::setComments);

                    Health health = new Health();
                    health.setPatient(patient);
                    Optional.ofNullable(updatedPatient.getGroupType()).ifPresent(health::setGroupType);
                    Optional.ofNullable(updatedPatient.getDiabeteType()).ifPresent(health::setDiabeteType);
                    Optional.ofNullable(updatedPatient.getCareDate()).ifPresent(h -> health.setDateConsultation(new Date(h.toEpochSecond())));

                    return patientRepository.save(patient);
                }).orElseThrow(() -> new RuntimeException("Patient not found with id " + id));
    }

    /**
     * Deletes a patient from the system by their ID.
     *
     * @param id The ID of the patient to delete.
     * @throws RuntimeException If no patient with the given ID is found.
     */
    public void deletePatient(Long id) {
        if (patientRepository.existsById(id)) {
            patientRepository.deleteById(id);
        } else {
            throw new RuntimeException("Patient not found with id " + id);
        }
    }
}

