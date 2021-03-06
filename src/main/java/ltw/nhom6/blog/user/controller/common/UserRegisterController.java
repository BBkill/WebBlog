package ltw.nhom6.blog.user.controller.common;

import ltw.nhom6.blog.core.Result;
import ltw.nhom6.blog.user.dto.request.UserRegisterRequestDto;
import ltw.nhom6.blog.user.dto.response.UserRegisterResponseDto;
import ltw.nhom6.blog.user.service.common.user.UserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/user/register")
@ResponseStatus(HttpStatus.CREATED)
public class UserRegisterController {

    private final UserRegisterService service;

    @Autowired
    public UserRegisterController(UserRegisterService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Result<UserRegisterResponseDto> execute(@Valid @RequestBody UserRegisterRequestDto requestDto) {
        return Result.creatUserSuccess(service.execute(requestDto));
    }
}
