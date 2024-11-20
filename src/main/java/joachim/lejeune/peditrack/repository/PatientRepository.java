package joachim.lejeune.peditrack.repository;

import joachim.lejeune.peditrack.controller.auth.UserDetailsImpl;
import joachim.lejeune.peditrack.dto.PatientDto;
import joachim.lejeune.peditrack.model.patient.Patient;
import joachim.lejeune.peditrack.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient save(Patient patient);

    List<Patient> findByUser(User user);
}
