package joachim.lejeune.peditrack.bodyDto;

import jakarta.validation.constraints.NotBlank;
import joachim.lejeune.peditrack.validation.ValidPassword;

public class PasswordChangeDto {

    @NotBlank
    private String currentPassword;

    @NotBlank
    @ValidPassword
    private String newPassword;

    public String getCurrentPassword() { return currentPassword; }
    public void setCurrentPassword(String currentPassword) { this.currentPassword = currentPassword; }

    public String getNewPassword() { return newPassword; }
    public void setNewPassword(String newPassword) { this.newPassword = newPassword; }
}
