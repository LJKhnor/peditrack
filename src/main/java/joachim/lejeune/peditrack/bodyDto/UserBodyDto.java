package joachim.lejeune.peditrack.bodyDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import joachim.lejeune.peditrack.model.role.Role;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class UserBodyDto {
    @NotBlank
    private String username;
    @Email
    @NotBlank
    private String mail;
    @NotBlank
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;
    private List<String> roles;
    private String address;
    private String locality;
    private String postalCode;
    private String activationCode;

    public UserBodyDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Optional<String> getPassword() {
        return Optional.of(password);
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Optional<String> getAddress() {
        return Optional.of(address);
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Optional<String> getLocality() {
        return Optional.of(locality);
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public Optional<String> getPostalCode() {
        return Optional.of(postalCode);
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }
}
