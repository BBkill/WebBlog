package ltw.nhom6.blog.blog.service.iml;

import ltw.nhom6.blog.blog.dto.request.BlogFindingReqDto;
import ltw.nhom6.blog.blog.dto.response.BlogResponse;
import ltw.nhom6.blog.blog.model.Blog;
import ltw.nhom6.blog.blog.repository.BlogRepository;
import ltw.nhom6.blog.blog.service.BlogFindingService;
import ltw.nhom6.blog.exception.common.BusinessException;
import ltw.nhom6.blog.security.authentication.provider.JwtProvider;
import ltw.nhom6.blog.user.model.User;
import ltw.nhom6.blog.user.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class BlogFindingServiceIml implements BlogFindingService {

    private final BlogRepository blogRepository;
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    public BlogFindingServiceIml(BlogRepository blogRepository,
                                 UserRepository userRepository,
                                 JwtProvider jwtProvider) {
        this.blogRepository = blogRepository;
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public BlogResponse findAllBlog(BlogFindingReqDto reqDto) {
        validateInput(reqDto);
        String email = jwtProvider.getUsernameFromToken(reqDto.getAccessToken());
        List<Blog> blogs = blogRepository.findAllByAuthor(email);
        return new BlogResponse(blogs);
    }

    private void validateInput(BlogFindingReqDto reqDto) {
        HashMap<String, String> error = new HashMap<>();
        String email = jwtProvider.getUsernameFromToken(reqDto.getAccessToken());
        userRepository.findUserByEmail(email).ifPresentOrElse(user -> {
        }, () -> error.put("NOT FOUND", "user not found"));

        if (!error.isEmpty()) {
            throw new BusinessException(error, HttpStatus.BAD_REQUEST);
        }
    }
}
