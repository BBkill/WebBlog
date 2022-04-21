package ltw.nhom6.blog.user.service.common.user;

import ltw.nhom6.blog.user.dto.request.UserActiveRequestDto;
import ltw.nhom6.blog.user.dto.request.UserGetOtpRequestDto;

public interface UserGetOtpService {

    void execute(UserGetOtpRequestDto requestDto);
}
