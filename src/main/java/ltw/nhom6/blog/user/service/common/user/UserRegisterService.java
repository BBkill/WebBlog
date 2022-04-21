package ltw.nhom6.blog.user.service.common.user;

import ltw.nhom6.blog.user.dto.request.UserRegisterRequestDto;
import ltw.nhom6.blog.user.dto.response.UserRegisterResponseDto;

public interface UserRegisterService {
    UserRegisterResponseDto execute(UserRegisterRequestDto registerRequestDto);
}
