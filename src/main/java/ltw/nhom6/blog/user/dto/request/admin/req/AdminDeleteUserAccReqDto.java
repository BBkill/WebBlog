package ltw.nhom6.blog.user.dto.request.admin.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminDeleteUserAccReqDto {

    @NotBlank(message = "access token is required")
    private String accessToken;

    @NotBlank(message = "username is required")
    private String username;
}
