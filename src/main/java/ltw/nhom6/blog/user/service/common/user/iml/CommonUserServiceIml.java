package ltw.nhom6.blog.user.service.common.user.iml;

import ltw.nhom6.blog.blog.repository.BlogRepository;
import ltw.nhom6.blog.blog.repository.CommentRepository;
import ltw.nhom6.blog.user.repository.UserRepository;
import ltw.nhom6.blog.user.service.common.user.CommonUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonUserServiceIml implements CommonUserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final BlogRepository blogRepository;
    private final CommentRepository commentRepository;
    @Autowired
    public CommonUserServiceIml(ModelMapper modelMapper,
                                UserRepository userRepository,
                                BlogRepository blogRepository,
                                CommentRepository commentRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.blogRepository = blogRepository;
        this.commentRepository = commentRepository;
    }
}
