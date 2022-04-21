package ltw.nhom6.blog.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ltw.nhom6.blog.validation.OtpConstraint;
import ltw.nhom6.blog.validation.PasswordConstraint;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResetPasswordRequestDto extends UserGetOtpResetPasswordRequestDto {

    @OtpConstraint
    @NotBlank(message = "Otp is required")
    private String otp;

    @NotBlank(message = "New password is required")
    //@PasswordConstraint
    private String newPassword;

    @NotBlank(message = "Confirm password is required")
    //@PasswordConstraint
    private String confirmPassword;
}
