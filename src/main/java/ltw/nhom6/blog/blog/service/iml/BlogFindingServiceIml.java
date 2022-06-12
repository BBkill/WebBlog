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

    public BlogFindingServiceIml(BlogRepository blogRepository,
                                 UserRepository userRepository,
                                 JwtProvider jwtProvider) {
        this.blogRepository = blogRepository;
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
    }

    //users find their own blog

    @Override
    public PagingBlog findBlogByPageSizeAndPageNumber(int pageSize, int pageNumber) {
        List<Blog> blogs = getAllBlogs(pageSize, pageNumber);
        return returnResult(blogs, pageSize, pageNumber);
    }

    private BlogResponse setBlogResponse(List<Blog> blogs) {
        return new BlogResponse(blogs);
    }

    private PagingBlog returnResult(List<Blog> blogs, int pageSize, int pageNumber) {
        List<BlogResponse> list = new ArrayList<>();
        list.add(setBlogResponse(blogs));
        PagingBlog pagingBlog = new PagingBlog();
        pagingBlog.setPageNumber(pageNumber);
        pagingBlog.setPageSize(Math.min(pageSize, blogs.size()));
        pagingBlog.setBlogResponses(list);
        return pagingBlog;
    }

    @Override
    public PagingBlog findBlogByAuthorAndPageSizeAndPageNumber(String author, int pageSize, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize, Sort.by("lastUpdatedAt").descending());
        List<Blog> blogs = blogRepository.findAllByAuthorAndIsDeleted(author, false, pageable);
        return returnResult(blogs, pageSize, pageNumber);
    }

    @Override
    public PagingBlog findBlogByKeyWordAndPageSizeAndPageNumber(String keyWord, int pageSize, int pageNumber) {
        List<Blog> list = new ArrayList<>();

        if (keyWord.equals("all")) {
            list.addAll(getAllBlogs(pageSize, pageNumber));
        } else {
            list.addAll(getBlogsLikeAuthor(keyWord, pageSize, pageNumber));
            list.addAll(getBlogsLikeContent(keyWord, pageSize, pageNumber));
            list.addAll(getBlogsLikeTitle(keyWord, pageSize, pageNumber));
        }
        list = list.stream().distinct().collect(Collectors.toList());
        return returnResult(list, pageSize, pageNumber);
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

    private List<Blog> getBlogsLikeAuthor(String author, int pageSize, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize, Sort.by("lastUpdatedAt").descending());
        return  blogRepository.findAllByAuthorOrAuthorIsLike(author, author, pageable);
    }

    private List<Blog> getBlogsLikeContent(String content, int pageSize, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize, Sort.by("lastUpdatedAt").descending());
        return  blogRepository.findAllByContentOrContentLike(content, content, pageable);
    }

    private List<Blog> getAllBlogs(int pageSize, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize, Sort.by("lastUpdatedAt").descending());
        return blogRepository.findAllByIsDeleted(false, pageable);
    }

    private List<Blog> getBlogsLikeTitle(String title, int pageSize, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize, Sort.by("lastUpdatedAt").descending());
        return  blogRepository.findAllByTitleOrTitleLike(title, title, pageable);
    }
}
