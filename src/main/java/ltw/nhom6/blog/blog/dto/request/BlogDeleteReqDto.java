package ltw.nhom6.blog.blog.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogDeleteReqDto {

    @NotBlank(message = "Blog title is required")
    private String blogTitle;
    @NotBlank(message = "Access token is required")
    private String accessToken;
}
