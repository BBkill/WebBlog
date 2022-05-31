package ltw.nhom6.blog.blog.repository;

import ltw.nhom6.blog.blog.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {
}
