package ltw.nhom6.blog.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ltw.nhom6.blog.user.model.Role;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterResponseDto {
    private String firstName;
    private String lastName;
    private Byte age;
    private String gender;
    private String username;
    private String email;
    private boolean isActivated;
    private Set<Role> roles;
}
