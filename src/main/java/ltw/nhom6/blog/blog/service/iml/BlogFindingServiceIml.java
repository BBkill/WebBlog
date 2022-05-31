package ltw.nhom6.blog.blog.service.iml;

import ltw.nhom6.blog.blog.dto.request.BlogFindingReqDto;
import ltw.nhom6.blog.blog.dto.response.BlogResponse;
import ltw.nhom6.blog.blog.dto.response.page.PagingBlog;
import ltw.nhom6.blog.blog.model.Blog;
import ltw.nhom6.blog.blog.repository.BlogRepository;
import ltw.nhom6.blog.blog.service.BlogFindingService;
import ltw.nhom6.blog.exception.common.BusinessException;
import ltw.nhom6.blog.security.authentication.provider.JwtProvider;
import ltw.nhom6.blog.user.model.User;
import ltw.nhom6.blog.user.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BlogFindingServiceIml implements BlogFindingService {

    private final BlogRepository blogRepository;
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final ModelMapper modelMapper;

    public BlogFindingServiceIml(BlogRepository blogRepository,
                                 UserRepository userRepository,
                                 JwtProvider jwtProvider,
                                 ModelMapper modelMapper) {
        this.blogRepository = blogRepository;
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
        this.modelMapper = modelMapper;
    }

    //users find their own blo

    @Override
    public PagingBlog findBlogByPageSizeAndPageNumber(int pageSize, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize, Sort.by("lastUpdatedAt").descending());
        List<BlogResponse> list = blogRepository.findAllByIsDeleted(false, pageable)
                        .stream().map(blog -> modelMapper.map(blog, BlogResponse.class))
                        .collect(Collectors.toList());

        PagingBlog pagingBlog = new PagingBlog();
        pagingBlog.setPageNumber(pageNumber);
        pagingBlog.setPageSize(pageSize);
        pagingBlog.setBlogResponses(list);
        return pagingBlog;
    }

    @Override
    public PagingBlog findBlogByAuthorAndPageSizeAndPageNumber(String author, int pageSize, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize, Sort.by("lastUpdatedAt").descending());
        List<BlogResponse> list = blogRepository.findAllByAuthorAndIsDeleted(author, false, pageable)
                .stream().map(blog -> modelMapper.map(blog, BlogResponse.class))
                .collect(Collectors.toList());

        PagingBlog pagingBlog = new PagingBlog();
        pagingBlog.setPageNumber(pageNumber);
        pagingBlog.setPageSize(pageSize);
        pagingBlog.setBlogResponses(list);
        return pagingBlog;
    }

    @Override
    public PagingBlog findBlogByKeyWordAndPageSizeAndPageNumber(String keyWord, int pageSize, int pageNumber) {
        List<BlogResponse> list = getBlogsLikeAuthor(keyWord, pageSize, pageNumber);
        list.addAll(getBlogsLikeContent(keyWord, pageSize, pageNumber));
        list.addAll(getBlogsLikeTitle(keyWord, pageSize, pageNumber));
        Set<BlogResponse> set = new HashSet<>(list);
        PagingBlog pagingBlog = new PagingBlog();
        pagingBlog.setPageNumber(pageNumber);
        pagingBlog.setPageSize(set.size());
        pagingBlog.setBlogResponses(new ArrayList<>(set));
        return pagingBlog;
    }

    // validate users find their own blog
    private void validateInput(BlogFindingReqDto reqDto) {
        HashMap<String, String> error = new HashMap<>();
        String email = jwtProvider.getUsernameFromToken(reqDto.getAccessToken());
        userRepository.findUserByEmail(email).ifPresentOrElse(user -> {
        }, () -> error.put("NOT FOUND", "user not found"));

        if (!error.isEmpty()) {
            throw new BusinessException(error, HttpStatus.BAD_REQUEST);
        }
    }

    private List<BlogResponse> getBlogsLikeAuthor(String author, int pageSize, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize, Sort.by("lastUpdatedAt").descending());
        return  blogRepository.findAllByAuthorLike(author, pageable)
                .stream().map(blog -> modelMapper.map(blog, BlogResponse.class))
                .collect(Collectors.toList());
    }

    private List<BlogResponse> getBlogsLikeContent(String content, int pageSize, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize, Sort.by("lastUpdatedAt").descending());
        return  blogRepository.findAllByContentLike(content, pageable)
                .stream().map(blog -> modelMapper.map(blog, BlogResponse.class))
                .collect(Collectors.toList());
    }

    private List<BlogResponse> getBlogsLikeTitle(String title, int pageSize, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize, Sort.by("lastUpdatedAt").descending());
        return  blogRepository.findAllByTitleLike(title, pageable)
                .stream().map(blog -> modelMapper.map(blog, BlogResponse.class))
                .collect(Collectors.toList());
    }
}
