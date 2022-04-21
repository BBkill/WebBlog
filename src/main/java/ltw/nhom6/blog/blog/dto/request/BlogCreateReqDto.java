package ltw.nhom6.blog.blog.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ltw.nhom6.blog.blog.model.Category;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogCreateReqDto {
    private String title;
    private String content;
    private Set<Category> categories;
}
