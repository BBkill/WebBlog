package ltw.nhom6.blog.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginResponseDto {

    private UserRegisterResponseDto userRegisterResponseDto;
    private String accessToken;
    private final static String HEADER = "Bearer";
}
