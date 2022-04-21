package ltw.nhom6.blog.user.service.role;

import ltw.nhom6.blog.user.model.Role;
import ltw.nhom6.blog.user.model.User;
import ltw.nhom6.blog.user.repository.RoleRepository;
import ltw.nhom6.blog.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

//    @Bean
//    private void runner() {
//        roleRepository.save(new Role(1,"ADMIN"));
//        roleRepository.save(new Role(2,"USER"));
//        User user = new User((long)1, "duc", "do",(byte)21, "male", "duke", "dominhduc12022001@gmail.com", "$10$OqaMRuyr2k.JifbNHJc6ZOGR5yB8OTcQXYgZi44C5ccTWUM5k2NO.", true, false);
//        userRepository.save(user);
//    }
}
