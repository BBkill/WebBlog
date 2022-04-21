package ltw.nhom6.blog.blog.model;

import lombok.*;
import ltw.nhom6.blog.user.model.User;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Table(name = "blog")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Blog {

    @Id
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
    @Column(name = "is_deleted")
    private Boolean isDeleted;
    @Column(name = "last_updated_at")
    private Timestamp lastUpdatedAt;

    @JoinTable(name = "category_blog",
            joinColumns = {@JoinColumn(name = "blog_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")})
    @ManyToMany
    private Set<Category> categories;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private List<Comment> commentList;

    @OneToMany
    private List<Rate> rateList;
}
