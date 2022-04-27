package ltw.nhom6.blog.blog.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "rate")
public class Rate {

    private Long id;
    private String blogId;
    private Long userId;
    private String username;
    private Integer rate;
}
