package ltw.nhom6.blog.blog.service.iml;

import ltw.nhom6.blog.blog.dto.request.BlogDeleteReqDto;
import ltw.nhom6.blog.blog.model.Blog;
import ltw.nhom6.blog.blog.repository.BlogRepository;
import ltw.nhom6.blog.blog.service.BlogDeleteService;
import ltw.nhom6.blog.exception.common.BusinessException;
import ltw.nhom6.blog.security.authentication.provider.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class BlogDeleteServiceIml implements BlogDeleteService {

    private final JwtProvider jwtProvider;
    private final BlogRepository blogRepository;

    @Autowired
    public BlogDeleteServiceIml(JwtProvider jwtProvider,
                                BlogRepository blogRepository) {
        this.jwtProvider = jwtProvider;
        this.blogRepository = blogRepository;
    }

    @Override
    public void execute(BlogDeleteReqDto reqDto) {
        validateInput(reqDto);
        Blog blog = blogRepository.findBlogByIdAndIsDeleted(reqDto.getBlogId(), false).orElse(new Blog());
        blog.setIsDeleted(true);
        blogRepository.save(blog);
    }

    private void validateInput(BlogDeleteReqDto reqDto) {
        HashMap<String, String> error = new HashMap<>();
        String email = jwtProvider.getUsernameFromToken(reqDto.getAccessToken());
        blogRepository.findBlogByIdAndIsDeleted(reqDto.getBlogId(), false).ifPresentOrElse(blog -> {
            if (!blog.getAuthor().equals(email)) {
                error.put("UN_AUTHORIZE", "YOU DON'T HAVE PERMIT");
            }
        }, () -> error.put("BLOG NOT FOUND", "BLOG NOT FOUND"));

        if (!error.isEmpty()) {
            throw new BusinessException(error, HttpStatus.BAD_REQUEST);
        }
    }
}
