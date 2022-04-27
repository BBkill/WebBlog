package ltw.nhom6.blog.blog.dto.request;

import liquibase.datatype.DataTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RateBlogReqDto {

    @NotBlank(message = "Blog name is required")
    private String blogId;

    @Min(message = "max is 10", value = 10)
    @Min(message = "min is 0", value = 0)
    private Integer rate;
    private String accessToken;
}
