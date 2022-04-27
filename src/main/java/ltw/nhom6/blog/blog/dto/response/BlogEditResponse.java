package ltw.nhom6.blog.blog.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ltw.nhom6.blog.blog.model.Category;
import ltw.nhom6.blog.blog.model.Comment;
import ltw.nhom6.blog.blog.model.Rate;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogEditResponse {

    private String id;
    private String title;
    private String content;
    private String author;
    private Date lastUpdatedAt;
    private Set<Category> categories;
    private List<Comment> comments;
    private Set<Rate> rates;
}
