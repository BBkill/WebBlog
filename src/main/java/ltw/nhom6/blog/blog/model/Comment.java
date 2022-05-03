package ltw.nhom6.blog.blog.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Comment")
public class Comment {

    private String id;
    private String blogId;
    private String blogTitle;
    private String blogAuthor;
    private Long userId;
    private String commentAuthor;
    private String comment;
    private Boolean isDeleted;
    private Date lastUpDatedAt;
}
