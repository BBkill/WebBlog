package ltw.nhom6.blog.blog.repository;

import ltw.nhom6.blog.blog.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BlogRepository extends MongoRepository<Blog, String> {

    List<Blog> findAllByAuthor(String author, Pageable pageable);

    Optional<Blog> findBlogByIdAndIsDeleted(String id, Boolean isDeleted);

    List<Blog> findAllByAuthorAndIsDeleted(String author, Boolean isDeleted); // author is email => unique

    List<Blog> findAllByIsDeleted(Boolean isDeleted, Pageable pageable);

    List<Blog> findAllByTitleLike(String keyWord, Pageable pageable);

    List<Blog> findAllByContentLike(String keyWord, Pageable pageable);

    List<Blog> findAllByAuthorLike(String keyWord, Pageable pageable);

    List<Blog> findAllByAuthorAndIsDeleted(String userId, Boolean isDeleted, Pageable pageable);

    List<Blog> findAllByIdAndIsDeleted(String userId, Boolean isDeleted, Pageable pageable);
}
