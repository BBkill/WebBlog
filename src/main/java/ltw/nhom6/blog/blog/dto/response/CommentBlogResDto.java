package ltw.nhom6.blog.blog.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentBlogResDto {

    private String blogId;
    private String blogTitle;
    private String commentAuthor;
    private String blogAuthor;
    private String comment;
}
