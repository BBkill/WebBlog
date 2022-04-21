package ltw.nhom6.blog.user.controller;

import ltw.nhom6.blog.user.dto.request.UserGetOtpResetPasswordRequestDto;
import ltw.nhom6.blog.user.dto.request.UserResetPasswordRequestDto;
import ltw.nhom6.blog.user.service.common.user.UserResetPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/user/forget-password")
public class UserResetPasswordController {

    private final UserResetPasswordService service;

    @Autowired
    public UserResetPasswordController(UserResetPasswordService service) {
        this.service = service;
    }

    @PostMapping("/get-otp")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void getOtpResetPassword(@RequestBody @Valid UserGetOtpResetPasswordRequestDto requestDto) {
        service.forgetPassword(requestDto);
    }

    @PostMapping("/reset-password")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void resetPassword(@RequestBody @Valid UserResetPasswordRequestDto requestDto) {
        service.resetPassword(requestDto);
    }
}
