package ltw.nhom6.blog.blog.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ltw.nhom6.blog.blog.model.Category;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogEditReqDto {

    private String blogId;
    @NotBlank(message = "title is required")
    private String oldTitle;
    private String newContent;
    private Category[] newCategory;
//    @NotBlank(message = "Access token is required")
//    private String accessToken;
}
