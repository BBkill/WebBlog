package ltw.nhom6.blog.blog.service;

import ltw.nhom6.blog.blog.dto.request.BlogEditReqDto;
import ltw.nhom6.blog.blog.dto.response.BlogEditResponse;

public interface BlogEditService {

    BlogEditResponse execute(BlogEditReqDto reqDto);
}
