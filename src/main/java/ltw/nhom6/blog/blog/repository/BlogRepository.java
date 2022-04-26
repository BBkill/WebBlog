package ltw.nhom6.blog.blog.repository;

import ltw.nhom6.blog.blog.model.Blog;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface BlogRepository extends MongoRepository<Blog, String> {

    List<Blog> findAllByAuthor(String author);

    Optional<Blog> findBlogByAuthorAndTitle(String author, String title);
}
