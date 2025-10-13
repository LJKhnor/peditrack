package joachim.lejeune.peditrack.bodyDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

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
    private String registrationKey;


    public UserBodyDto(String name, String password, String email, String activationKey) {
        this.username = name;
        this.password = password;
        this.mail = email;
        this.registrationKey = activationKey;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
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

    public String getRegistrationKey() {
        return registrationKey;
    }
}
