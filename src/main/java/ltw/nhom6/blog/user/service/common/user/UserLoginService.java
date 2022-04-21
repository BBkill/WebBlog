package ltw.nhom6.blog.user.service.common.user;

import ltw.nhom6.blog.user.dto.request.UserLoginRequestDto;
import ltw.nhom6.blog.user.dto.response.UserLoginResponseDto;

public interface UserLoginService {

    UserLoginResponseDto execute(UserLoginRequestDto requestDto);
}
