package joachim.lejeune.peditrack.model.auth;

import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "login_audit")
public class LoginAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String ip;

    @Column(nullable = false)
    private boolean success;

    @Column(name = "failure_reason")
    private String failureReason;

    @Column(name = "attempted_at", nullable = false)
    private OffsetDateTime attemptedAt;

    public LoginAudit() {}

    public LoginAudit(String username, String ip, boolean success, String failureReason) {
        this.username = username;
        this.ip = ip;
        this.success = success;
        this.failureReason = failureReason;
        this.attemptedAt = OffsetDateTime.now();
    }

    public Long getId() { return id; }
    public String getUsername() { return username; }
    public String getIp() { return ip; }
    public boolean isSuccess() { return success; }
    public String getFailureReason() { return failureReason; }
    public OffsetDateTime getAttemptedAt() { return attemptedAt; }
}
