package joachim.lejeune.peditrack.repository;

import joachim.lejeune.peditrack.dto.PatientDto;
import joachim.lejeune.peditrack.model.patient.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    PatientDto findOnePatientById(Long id);
}
