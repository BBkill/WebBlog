package ltw.nhom6.blog.user.dto.request.admin.req;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AdminGrandAuthorityReqDto extends AdminDeleteUserAccReqDto{

    @NotBlank(message = "authority is required ")
    private String authorityName;
}
