package ltw.nhom6.blog.blog.service;

import ltw.nhom6.blog.blog.dto.request.CommentBlogReqDto;
import ltw.nhom6.blog.blog.dto.response.CommentBlogResDto;

public interface CommentService {

    CommentBlogResDto comment(CommentBlogReqDto reqDto);
}
