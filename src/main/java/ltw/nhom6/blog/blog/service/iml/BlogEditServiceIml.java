package ltw.nhom6.blog.blog.service.iml;

import ltw.nhom6.blog.blog.dto.request.BlogEditReqDto;
import ltw.nhom6.blog.blog.dto.response.BlogEditResponse;
import ltw.nhom6.blog.blog.model.Blog;
import ltw.nhom6.blog.blog.repository.BlogRepository;
import ltw.nhom6.blog.blog.service.BlogEditService;
import ltw.nhom6.blog.exception.common.BusinessException;
import ltw.nhom6.blog.security.authentication.provider.JwtProvider;
import ltw.nhom6.blog.user.model.User;
import ltw.nhom6.blog.user.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BlogEditServiceIml implements BlogEditService {

    private final JwtProvider jwtProvider;
    private final BlogRepository blogRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public BlogEditServiceIml(JwtProvider jwtProvider,
                              BlogRepository blogRepository,
                              ModelMapper modelMapper) {
        this.jwtProvider = jwtProvider;
        this.blogRepository = blogRepository;
        this.modelMapper = modelMapper;
    }


    private void validateInput(BlogEditReqDto reqDto) {
        Map<String, String> error = new HashMap<>();
        String email = jwtProvider.getUsernameFromToken(reqDto.getAccessToken());
        blogRepository.findBlogByIdAndIsDeleted(reqDto.getBlogId(), false).ifPresentOrElse(blog -> {
            if (!blog.getAuthor().equals(email)) {
                error.put("NO AUTHORIZE", "YOU DON'T HAVE AUTHORIZE");
            }
        }, () -> error.put("BLOG NOT FOUND", "blog not found"));
        if (!error.isEmpty()) {
            throw new BusinessException(error, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public BlogEditResponse execute(BlogEditReqDto reqDto) {
        validateInput(reqDto);
        String email = jwtProvider.getUsernameFromToken(reqDto.getAccessToken());
        Blog blog = blogRepository.findBlogByIdAndIsDeleted(reqDto.getBlogId(), false).orElse(new Blog());
        if (reqDto.getNewCategory().length != 0) {
            blog.setCategories(Arrays.stream(reqDto.getNewCategory()).collect(Collectors.toSet()));
        }
        if (!reqDto.getNewContent().equals("")) {
            blog.setContent(reqDto.getNewContent());
        }
        blog.setLastUpdatedAt(new Date());
        BlogEditResponse response = modelMapper.map(blogRepository.save(blog), BlogEditResponse.class);
        response.setCategories(blog.getCategories());
        return response;
    }
}
