package ltw.nhom6.blog.user.service.admin;

import ltw.nhom6.blog.user.dto.request.admin.req.AdminDeleteUserAccReqDto;
import ltw.nhom6.blog.user.dto.request.admin.req.AdminGrandAuthorityReqDto;
import ltw.nhom6.blog.user.dto.response.admin.response.AdminDeleteUserAccResDto;

public interface AdminService {

    AdminDeleteUserAccResDto deleteUserAcc(AdminDeleteUserAccReqDto reqDto);

    void grandAuthority(AdminGrandAuthorityReqDto reqDto);
}
