package ltw.nhom6.blog.user.model;

import lombok.*;
import ltw.nhom6.blog.blog.model.Blog;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "age")
    private Byte age;
    @Column(name = "gender")
    private String gender;
    @Column(name = "username")
    private String username;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "is_activated")
    private boolean isActivated;
    @Column(name = "is_deleted")
    private boolean isDeleted;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "role_users",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private Set<Role> roles;

    @OneToMany()
    private List<Blog> blogList;

    public User(Long id, String firstName, String lastName, Byte age, String gender, String username, String email, String password, boolean isActivated, boolean isDeleted) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.username = username;
        this.email = email;
        this.password = password;
        this.isActivated = isActivated;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", isActivated=" + isActivated +
                ", roles=" + roles +
                ", blogList=" + blogList +
                '}';
    }
}
