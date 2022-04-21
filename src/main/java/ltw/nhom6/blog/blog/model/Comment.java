package ltw.nhom6.blog.blog.model;

import lombok.*;
import ltw.nhom6.blog.user.model.User;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Table(name = "comment")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Comment {

    @Id
    private Long id;
    @Column(name = "is_deleted")
    private Boolean isDeleted;
    @Column(name = "content")
    private String content;
    @Column(name = "last_updated_at")
    private Timestamp lastUpDatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blog_id")
    private Blog blog;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
