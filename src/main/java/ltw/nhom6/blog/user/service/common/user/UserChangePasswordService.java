package ltw.nhom6.blog.user.service.common.user;

import ltw.nhom6.blog.user.dto.request.UserChangePasswordRequestDto;

public interface UserChangePasswordService {

    void execute(UserChangePasswordRequestDto requestDto);
}
