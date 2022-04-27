package ltw.nhom6.blog.blog.service.iml;

import ltw.nhom6.blog.blog.dto.request.CommentBlogReqDto;
import ltw.nhom6.blog.blog.dto.response.CommentBlogResDto;
import ltw.nhom6.blog.blog.model.Blog;
import ltw.nhom6.blog.blog.model.Comment;
import ltw.nhom6.blog.blog.repository.BlogRepository;
import ltw.nhom6.blog.blog.repository.CommentRepository;
import ltw.nhom6.blog.blog.service.CommentService;
import ltw.nhom6.blog.exception.common.BusinessException;
import ltw.nhom6.blog.security.authentication.provider.JwtProvider;
import ltw.nhom6.blog.user.model.User;
import ltw.nhom6.blog.user.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CommentServiceIml implements CommentService {

    private final BlogRepository blogRepository;
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final ModelMapper modelMapper;
    private final CommentRepository commentRepository;

    @Autowired
    public CommentServiceIml(BlogRepository blogRepository,
                             UserRepository userRepository,
                             JwtProvider jwtProvider,
                             ModelMapper modelMapper,
                             CommentRepository commentRepository) {
        this.blogRepository = blogRepository;
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
        this.modelMapper = modelMapper;
        this.commentRepository = commentRepository;
    }

    @Override
    public CommentBlogResDto comment(CommentBlogReqDto reqDto) {

        validateInput(reqDto);
        String author = jwtProvider.getUsernameFromToken(reqDto.getAccessToken());
        User userComment = userRepository.findUserByEmail(author).orElse(new User());
        Blog blog = blogRepository.findBlogByIdAndIsDeleted(reqDto.getBlogId(), false).orElse(new Blog());
        ltw.nhom6.blog.blog.model.Comment comment = new Comment();
        comment.setComment(reqDto.getComment());
        comment.setBlogAuthor(blog.getAuthor());
        comment.setBlogTitle(blog.getTitle());
        comment.setIsDeleted(false);
        comment.setLastUpDatedAt(new Date());
        comment.setCommentAuthor(author);
        comment.setUserId(userComment.getId());
        comment.setBlogId(blog.getId());
        List<Comment> list = blog.getComments();
        if (list == null) {
            list = new ArrayList<>();
        }
        Comment newComment = commentRepository.save(comment);
        list.add(newComment);
        blog.setComments(list);
        blogRepository.save(blog);
        return modelMapper.map(newComment, CommentBlogResDto.class);
    }

    private void validateInput(CommentBlogReqDto reqDto) {
        HashMap<String,String> error = new HashMap<>();
        String author = jwtProvider.getUsernameFromToken(reqDto.getAccessToken());
        userRepository.findUserByEmail(author).ifPresent(user -> {
            if (user.isDeleted()) {
                error.put("USER", "user is banned");
            }
        });
        blogRepository.findBlogByIdAndIsDeleted(reqDto.getBlogId(), false).ifPresentOrElse(blog -> {
            if (blog.getIsDeleted()) {
                error.put("BLOG", "IS DELETED");
            }
        }, () -> error.put("NOT FOUND", "blog not found"));

        if (!error.isEmpty()) {
            throw new BusinessException(error, HttpStatus.BAD_REQUEST);
        }
    }
}
