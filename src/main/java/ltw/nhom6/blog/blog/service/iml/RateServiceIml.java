package ltw.nhom6.blog.blog.service.iml;

import ltw.nhom6.blog.blog.dto.request.RateBlogReqDto;
import ltw.nhom6.blog.blog.dto.response.RateBlogResDto;
import ltw.nhom6.blog.blog.model.Blog;
import ltw.nhom6.blog.blog.model.Rate;
import ltw.nhom6.blog.blog.repository.BlogRepository;
import ltw.nhom6.blog.blog.repository.RateRepository;
import ltw.nhom6.blog.blog.service.RateService;
import ltw.nhom6.blog.security.authentication.provider.JwtProvider;
import ltw.nhom6.blog.user.model.User;
import ltw.nhom6.blog.user.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RateServiceIml implements RateService {

    private final UserRepository userRepository;
    private final BlogRepository blogRepository;
    private final RateRepository rateRepository;
    private final ModelMapper modelMapper;
    private final JwtProvider jwtProvider;

    public RateServiceIml(UserRepository userRepository,
                          BlogRepository blogRepository,
                          RateRepository rateRepository,
                          ModelMapper modelMapper,
                          JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.blogRepository = blogRepository;
        this.rateRepository = rateRepository;
        this.modelMapper = modelMapper;
        this.jwtProvider = jwtProvider;
    }



    @Override
    public RateBlogResDto rate(RateBlogReqDto reqDto) {

        String email = jwtProvider.getUsernameFromToken(reqDto.getAccessToken());
        User user = userRepository.findUserByEmail(email).orElse(new User());
        Blog blog = blogRepository.findBlogByIdAndIsDeleted(reqDto.getBlogId(), false).orElse(new Blog());

        Rate rate = new Rate();
        rate.setRate(reqDto.getRate());
        rate.setBlogId(reqDto.getBlogId());
        rate.setBlogName(blog.getTitle());
        rate.setUserId(user.getId());
        rate.setUsername(user.getUsername());

        Set<Rate> rateSet = blog.getRates();
        rateSet.add(rate);
        blog.setRates(rateSet);
        blogRepository.save(blog);

        return modelMapper.map(rateRepository.save(rate), RateBlogResDto.class);
    }
}
