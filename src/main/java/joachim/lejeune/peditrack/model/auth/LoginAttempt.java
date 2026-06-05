package joachim.lejeune.peditrack.model.auth;

import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "login_attempt")
public class LoginAttempt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "attempt_key", unique = true, nullable = false)
    private String attemptKey;

    @Column(nullable = false)
    private int attempts;

    @Column(name = "window_start", nullable = false)
    private OffsetDateTime windowStart;

    public Long getId() { return id; }

    public String getAttemptKey() { return attemptKey; }
    public void setAttemptKey(String attemptKey) { this.attemptKey = attemptKey; }

    public int getAttempts() { return attempts; }
    public void setAttempts(int attempts) { this.attempts = attempts; }

    public OffsetDateTime getWindowStart() { return windowStart; }
    public void setWindowStart(OffsetDateTime windowStart) { this.windowStart = windowStart; }
}
