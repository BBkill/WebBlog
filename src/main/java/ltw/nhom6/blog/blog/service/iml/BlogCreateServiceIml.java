package ltw.nhom6.blog.blog.service.iml;

import ltw.nhom6.blog.blog.dto.request.BlogCreateReqDto;
import ltw.nhom6.blog.blog.dto.response.BlogCreateResDto;
import ltw.nhom6.blog.blog.model.Blog;
import ltw.nhom6.blog.blog.model.Category;
import ltw.nhom6.blog.blog.repository.BlogRepository;
import ltw.nhom6.blog.blog.service.BlogCreateService;
import ltw.nhom6.blog.exception.common.BusinessException;
import ltw.nhom6.blog.security.authentication.provider.JwtProvider;
import ltw.nhom6.blog.user.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.stream.Collectors;

@Service
public class BlogCreateServiceIml implements BlogCreateService {

    private final UserRepository userRepository;
    private final BlogRepository blogRepository;
    private final ModelMapper modelMapper;
    private final JwtProvider jwtProvider;

    @Autowired
    public BlogCreateServiceIml(UserRepository userRepository,
                                BlogRepository blogRepository,
                                ModelMapper modelMapper,
                                JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.blogRepository = blogRepository;
        this.modelMapper = modelMapper;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public BlogCreateResDto execute(BlogCreateReqDto reqDto) {
        validateInput(reqDto);
        Blog blog = modelMapper.map(reqDto, Blog.class);
        BlogCreateResDto response = new BlogCreateResDto();
        blog.setIsDeleted(false);
        blog.setLastUpdatedAt(Date.from(Instant.now()));
        blog.setCategories(Arrays.stream(reqDto.getCategories()).collect(Collectors.toSet()));
        blog.setComments(null);
        blog.setRates(null);
        blog.setAuthor(jwtProvider.getUsernameFromToken(reqDto.getAccessToken()));
        response.setId(blogRepository.save(blog).getId());
        response.setContent(reqDto.getContent());
        response.setCategories(blog.getCategories());
        response.setAuthor(blog.getAuthor());
        response.setLastUpdatedAt(blog.getLastUpdatedAt());
        response.setTitle(blog.getTitle());
        return response;
    }

    private void validateInput(BlogCreateReqDto reqDto) {
        HashMap<String, String> error = new HashMap<>();
        String email = jwtProvider.getUsernameFromToken(reqDto.getAccessToken());
        userRepository.findUserByEmail(email).ifPresentOrElse(user -> {},
                () -> error.put("NOT FOUND", "User not found"));
        if (!error.isEmpty()) {
            throw new BusinessException(error, HttpStatus.BAD_REQUEST);
        }
    }
}
