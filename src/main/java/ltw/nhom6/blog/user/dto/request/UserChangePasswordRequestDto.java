package ltw.nhom6.blog.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ltw.nhom6.blog.validation.PasswordConstraint;

import javax.validation.constraints.NotBlank;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserChangePasswordRequestDto {

    private String username;

    @NotBlank(message = "email is required")
    private String email;

    @NotBlank(message = "old password is required")
    //@PasswordConstraint
    private String oldPassword;

    @NotBlank(message = "new password is required")
    //@PasswordConstraint
    private String newPassword;

    @NotBlank(message = "confirm password is required")
    //@PasswordConstraint
    private String confirmPassword;
}
