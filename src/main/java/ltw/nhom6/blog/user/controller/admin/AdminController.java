package ltw.nhom6.blog.user.controller.admin;

import ltw.nhom6.blog.user.dto.request.admin.req.AdminDeleteUserAccReqDto;
import ltw.nhom6.blog.user.dto.request.admin.req.AdminGrandAuthorityReqDto;
import ltw.nhom6.blog.user.dto.response.admin.response.AdminDeleteUserAccResDto;
import ltw.nhom6.blog.user.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/delete-account")
    @ResponseStatus(HttpStatus.GONE)
    public AdminDeleteUserAccResDto deleteUserAccount(AdminDeleteUserAccReqDto reqDto) {
        return adminService.deleteUserAcc(reqDto);
    }

    @PostMapping("/grand-authority")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void grandAuthorityUserAccount(AdminGrandAuthorityReqDto reqDto) {
        adminService.grandAuthority(reqDto);
    }
}
