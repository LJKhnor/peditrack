package joachim.lejeune.peditrack.controller.auth;

import joachim.lejeune.peditrack.model.auth.LoginAttempt;
import joachim.lejeune.peditrack.repository.LoginAttemptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.Optional;

@Component
public class LoginRateLimiter {

    private static final int MAX_ATTEMPTS = 5;
    private static final Duration WINDOW = Duration.ofMinutes(15);

    @Autowired
    private LoginAttemptRepository loginAttemptRepository;

    @Transactional
    public boolean isBlocked(String key) {
        Optional<LoginAttempt> opt = loginAttemptRepository.findByAttemptKey(key);
        if (opt.isEmpty()) return false;
        LoginAttempt info = opt.get();
        if (isExpired(info.getWindowStart())) {
            loginAttemptRepository.deleteByAttemptKey(key);
            return false;
        }
        return info.getAttempts() >= MAX_ATTEMPTS;
    }

    @Transactional
    public void recordFailure(String key) {
        Optional<LoginAttempt> opt = loginAttemptRepository.findByAttemptKey(key);
        if (opt.isEmpty() || isExpired(opt.get().getWindowStart())) {
            LoginAttempt attempt = opt.orElse(new LoginAttempt());
            attempt.setAttemptKey(key);
            attempt.setAttempts(1);
            attempt.setWindowStart(OffsetDateTime.now());
            loginAttemptRepository.save(attempt);
        } else {
            LoginAttempt info = opt.get();
            info.setAttempts(info.getAttempts() + 1);
            loginAttemptRepository.save(info);
        }
    }

    @Transactional
    public void recordSuccess(String key) {
        loginAttemptRepository.deleteByAttemptKey(key);
    }

    private boolean isExpired(OffsetDateTime windowStart) {
        return windowStart.plus(WINDOW).isBefore(OffsetDateTime.now());
    }
}
