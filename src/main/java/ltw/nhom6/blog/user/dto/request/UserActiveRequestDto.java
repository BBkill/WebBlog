package ltw.nhom6.blog.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserActiveRequestDto {

    @NotBlank(message = "email is required")
    private String email;
    @NotBlank(message = "otp is required")
    private String Otp;

}
