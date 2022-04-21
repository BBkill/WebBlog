package ltw.nhom6.blog.user.service.role;

import ltw.nhom6.blog.user.model.Role;
import ltw.nhom6.blog.user.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Bean
    private void runner() {
        roleRepository.save(new Role(1,"ADMIN"));
        roleRepository.save(new Role(2,"USER"));
    }
}
