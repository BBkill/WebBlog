package ltw.nhom6.blog.blog.service;

import ltw.nhom6.blog.blog.dto.request.BlogCreateReqDto;
import ltw.nhom6.blog.blog.dto.response.BlogCreateResDto;

public interface BlogCreateService {

    BlogCreateResDto execute(BlogCreateReqDto reqDto);
}
