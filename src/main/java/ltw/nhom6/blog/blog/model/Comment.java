package ltw.nhom6.blog.blog.model;

import lombok.*;

import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    private String id;
    private String blogTitle;
    private String blogAuthor;
    private String commentAuthor;
    private String comment;
    private Boolean isDeleted;
    private Date lastUpDatedAt;
}
