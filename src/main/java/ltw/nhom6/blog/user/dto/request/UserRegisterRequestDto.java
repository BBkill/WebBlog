package ltw.nhom6.blog.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ltw.nhom6.blog.validation.PasswordConstraint;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterRequestDto {

    @NotBlank(message = "firstname is required")
    private String firstName;

    @NotBlank(message = "lastname is required")
    private String lastName;

    @NotBlank(message = "age is required")
    @Max(message = "age must be in range 4 to 150", value = 150)
    private Byte age;

    @NotBlank(message = "gender is required")
    @Length(max = 6)
    private String gender;

    @NotBlank(message = "email is required")
    @Email(message = "invalid email")
    private String email;

    @NotBlank(message = "username is required")
    @Length(max = 256)
    private String username;

    @NotBlank(message = "password is required")
    //@PasswordConstraint
    /**
     * Password must contain at least one digit [0-9].
     * Password must contain at least one lowercase Latin character [a-z].
     * Password must contain at least one uppercase Latin character [A-Z].
     * Password must contain at least one special character like ! @ # & ( ).
     * Password must contain a length of at least 8 characters and a maximum of 20 characters.
     */
    private String password;

    @NotBlank(message = "password confirm is required")
    private String passwordConfirm;
}
