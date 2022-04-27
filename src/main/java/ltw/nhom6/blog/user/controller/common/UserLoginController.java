package ltw.nhom6.blog.user.controller.common;

import ltw.nhom6.blog.core.Result;
import ltw.nhom6.blog.user.dto.request.UserLoginRequestDto;
import ltw.nhom6.blog.user.dto.response.UserLoginResponseDto;
import ltw.nhom6.blog.user.service.common.user.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/user/login")
public class UserLoginController {

    private final UserLoginService service;

    @Autowired
    public UserLoginController(UserLoginService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Result<UserLoginResponseDto> execute(@Valid @RequestBody UserLoginRequestDto loginRequestDto) {
        return Result.loginSuccess(service.execute(loginRequestDto));
    }
}
