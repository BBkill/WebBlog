package ltw.nhom6.blog.blog.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Table(name = "rate")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Rate {

    @Id
    private Long id;
    @Column(name = "blog_id")
    private Long blogId;
    @Column(name = "users_id")
    private Long userId;
}
