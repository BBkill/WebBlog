package ltw.nhom6.blog.user.controller.common;

import ltw.nhom6.blog.user.dto.request.UserActiveRequestDto;
import ltw.nhom6.blog.user.dto.request.UserGetOtpRequestDto;
import ltw.nhom6.blog.user.service.common.user.UserGetOtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/user/get-otp")
public class UserGetOtpActiveController {

    private final UserGetOtpService service;

    @Autowired
    public UserGetOtpActiveController(UserGetOtpService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void execute(@Valid @RequestBody UserGetOtpRequestDto requestDto) {
        service.execute(requestDto);
    }
}
