package joachim.lejeune.peditrack.repository;

import joachim.lejeune.peditrack.model.auth.LoginAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginAuditRepository extends JpaRepository<LoginAudit, Long> {
}
