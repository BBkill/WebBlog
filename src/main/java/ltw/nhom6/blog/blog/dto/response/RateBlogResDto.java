package ltw.nhom6.blog.blog.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RateBlogResDto {

    private String blogId;
    private String blogName;
    private String username;
    private float rate;
}
