package ltw.nhom6.blog.blog.service;

import ltw.nhom6.blog.blog.dto.request.BlogFindingReqDto;
import ltw.nhom6.blog.blog.dto.response.BlogResponse;
import ltw.nhom6.blog.blog.dto.response.page.PagingBlog;

public interface BlogFindingService {

    PagingBlog findBlogByPageSizeAndPageNumber(int pageSize, int pageNumber);

    PagingBlog findBlogByAuthorAndPageSizeAndPageNumber(String userId, int pageSize, int pageNumber);

    PagingBlog findBlogByKeyWordAndPageSizeAndPageNumber(String keyWord, int pageSize, int pageNumber);
}
