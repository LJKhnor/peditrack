package joachim.lejeune.peditrack.model.user;

import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Entity
@Table(name="registration_key")
public class RegistrationKey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "key_value")
    private String key;
    @Column(name="is_used")
    private boolean isUsed;
    @Column(name="is_active")
    private boolean isActive;

    @Column(name = "expires_at")
    private OffsetDateTime expiresAt;

    public RegistrationKey() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public OffsetDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(OffsetDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public boolean isExpired() {
        return expiresAt != null && expiresAt.isBefore(OffsetDateTime.now());
    }
}
