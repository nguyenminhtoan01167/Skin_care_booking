package ut.edu.skincarebooking.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ChangePasswordRequest {

    @NotBlank(message = "Current password must not be blank")
    private String currentPassword;

    @NotBlank(message = "New password must not be blank")
    private String newPassword;

    @NotBlank(message = "Confirm password must not be blank")
    private String confirmPassword;

    @NotBlank(message = "Email must not be blank")
    private String email;
}