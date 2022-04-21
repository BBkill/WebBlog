package ltw.nhom6.blog.user.dto.response.admin.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ltw.nhom6.blog.user.model.Role;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AdminDeleteUserAccResDto {

    private final static String message = "DELETE SUCCESSFUL";
    private String firstName;
    private String lastName;
    private Byte age;
    private String gender;
    private String username;
    private String email;
    private Set<Role> roles;
}
