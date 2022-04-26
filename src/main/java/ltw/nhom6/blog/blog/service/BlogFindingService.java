package ltw.nhom6.blog.blog.service;

import ltw.nhom6.blog.blog.dto.request.BlogFindingReqDto;
import ltw.nhom6.blog.blog.dto.response.BlogResponse;

public interface BlogFindingService {

    BlogResponse findAllBlog(BlogFindingReqDto reqDto);
}
