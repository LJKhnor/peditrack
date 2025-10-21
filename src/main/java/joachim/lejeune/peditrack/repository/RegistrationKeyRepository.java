package joachim.lejeune.peditrack.repository;

import joachim.lejeune.peditrack.model.user.RegistrationKey;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegistrationKeyRepository extends JpaRepository<RegistrationKey, Long> {
    @Override
    @NotNull
    Optional<RegistrationKey> findById(@NotNull Long aLong);

    Optional<RegistrationKey> findByKey(String registrationKey);

}
