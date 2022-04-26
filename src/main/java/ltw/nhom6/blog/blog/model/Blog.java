package ltw.nhom6.blog.blog.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Document(collection = "blog")
@NoArgsConstructor
@AllArgsConstructor
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String title;
    private String content;
    private String author;
    private Boolean isDeleted;
    private Date lastUpdatedAt;
    private Set<Category> categories;
    private List<Comment> comments;
    private Set<Rate> rates;
}
