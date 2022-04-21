package ltw.nhom6.blog.user.model.composite;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "role_users")
@Getter
@Setter
@Table(name = "role_users")
@AllArgsConstructor
@NoArgsConstructor
public class RoleUsers {
    @Id
    Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "role_id")
    private byte roleId;
}
