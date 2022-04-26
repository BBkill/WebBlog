package ltw.nhom6.blog.blog.model;

import lombok.*;
import ltw.nhom6.blog.user.model.User;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    private String id;
    private Boolean isDeleted;
    private String content;
    private Timestamp lastUpDatedAt;
}
