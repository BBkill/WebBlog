package ltw.nhom6.blog.blog.model.composit;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Entity(name = "category_blog")
@Table(name = "Category_blog")
public class CategoryBlog {
    @Id
    private Long id;

    @Column(name = "category_id")
    private Long categoryId;

    @Column(name =  "blog_id")
    private Long blogId;
}
