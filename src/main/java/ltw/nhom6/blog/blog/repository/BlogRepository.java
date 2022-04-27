package ltw.nhom6.blog.blog.repository;

import ltw.nhom6.blog.blog.model.Blog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BlogRepository extends MongoRepository<Blog, String> {

    List<Blog> findAllByAuthor(String author);

    Optional<Blog> findBlogByIdAndIsDeleted(String id, Boolean isDeleted);

    List<Blog> findAllByAuthorAndIsDeleted(String author, Boolean isDeleted);
}
