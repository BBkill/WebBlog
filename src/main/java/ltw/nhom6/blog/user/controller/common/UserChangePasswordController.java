package ltw.nhom6.blog.user.controller.common;

import ltw.nhom6.blog.core.Result;
import ltw.nhom6.blog.user.dto.request.UserChangePasswordRequestDto;
import ltw.nhom6.blog.user.service.common.user.UserChangePasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/user/change-password")
public class UserChangePasswordController {
    private final UserChangePasswordService service;

    @Autowired
    public UserChangePasswordController(UserChangePasswordService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Result<Void> execute(@RequestBody @Valid UserChangePasswordRequestDto requestDto) {
        service.execute(requestDto);
        return Result.success();
    }
}
