package joachim.lejeune.peditrack.repository;

import joachim.lejeune.peditrack.model.patient.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient save(Patient patient);

    List<Patient> findByUserId(Long userId);
}
