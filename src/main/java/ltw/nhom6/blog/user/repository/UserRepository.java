package ltw.nhom6.blog.user.repository;

import ltw.nhom6.blog.user.model.Role;
import ltw.nhom6.blog.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> getUserByEmail(@Param(value = "email") String email);

    Optional<User> findUserByUsername(String username);

    Optional<User> findUserByEmail(String email);

    @Query("SELECT r.name FROM role r LEFT JOIN role_users ru ON r.id = ru.roleId WHERE  ru.userId = :id")
    Set<String> getUserRole(@Param(value = "id") Long id);
}
