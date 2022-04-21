package ltw.nhom6.blog.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserGetOtpResetPasswordRequestDto {

    private String username;

    @NotBlank(message = "email is required")
    @Email
    private String email;
}
