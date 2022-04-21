package ltw.nhom6.blog.user.service.common.user;

import ltw.nhom6.blog.user.dto.request.UserGetOtpResetPasswordRequestDto;
import ltw.nhom6.blog.user.dto.request.UserResetPasswordRequestDto;

public interface UserResetPasswordService {
    void forgetPassword(UserGetOtpResetPasswordRequestDto requestDto);

    void resetPassword(UserResetPasswordRequestDto requestDto);
}
