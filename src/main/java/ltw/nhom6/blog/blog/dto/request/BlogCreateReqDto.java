package ltw.nhom6.blog.blog.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ltw.nhom6.blog.blog.model.Category;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogCreateReqDto {

    @NotBlank(message = "title is required")
    private String title;
    @NotBlank(message = "Content is required")
    private String content;
//    @NotBlank(message = "Access token is required")
//    private String accessToken;
    private Category[] categories;
}
