package ltw.nhom6.blog.blog.repository;

import ltw.nhom6.blog.blog.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
