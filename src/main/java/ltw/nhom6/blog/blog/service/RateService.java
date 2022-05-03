package ltw.nhom6.blog.blog.service;

import ltw.nhom6.blog.blog.dto.request.RateBlogReqDto;
import ltw.nhom6.blog.blog.dto.response.RateBlogResDto;

public interface RateService {

    RateBlogResDto rate(RateBlogReqDto reqDto, String token);
}
