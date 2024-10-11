package joachim.lejeune.peditrack.repository;

import joachim.lejeune.peditrack.model.patient.Health;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HealthRepository extends JpaRepository<Health, Long> {
    Health save(Health health);
}
