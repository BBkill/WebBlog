package ltw.nhom6.blog.blog.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentBlogResDto {

    private String blogTitle;
    private String cmtAuthor;
    private String blogAuthor;
    private String comment;
}
