package ltw.nhom6.blog.user.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Table(name = "role")
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "role")
public class Role {

    @Id
    private Integer id;
    @Column(name = "name")
    private String name;

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
