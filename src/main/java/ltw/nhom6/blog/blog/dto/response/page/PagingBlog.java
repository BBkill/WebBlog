package ltw.nhom6.blog.blog.dto.response.page;

import lombok.*;
import ltw.nhom6.blog.blog.dto.response.BlogResponse;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PagingBlog {

    private int pageNumber;

    private int pageSize;

    private List<BlogResponse> blogResponses;

}
