package ltw.nhom6.blog.blog.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rate {

    private Long id;
    private Long blogId;
    private Long userId;
    private Integer rate;
}
