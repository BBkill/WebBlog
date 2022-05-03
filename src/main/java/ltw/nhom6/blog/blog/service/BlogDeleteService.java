package ltw.nhom6.blog.blog.service;

import ltw.nhom6.blog.blog.dto.request.BlogDeleteReqDto;

public interface BlogDeleteService {

    void execute(BlogDeleteReqDto reqDto, String token);
}
