package ltw.nhom6.blog.user.controller.common;

import ltw.nhom6.blog.user.dto.request.UserActiveRequestDto;
import ltw.nhom6.blog.user.service.common.user.UserActiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/user/active")
public class UserActiveController {

    private final UserActiveService activeService;
    @Autowired
    public UserActiveController(UserActiveService activeService) {
        this.activeService = activeService;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping
    public void execute(@RequestBody @Valid UserActiveRequestDto activeRequestDto) {
        activeService.execute(activeRequestDto);
    }
}
