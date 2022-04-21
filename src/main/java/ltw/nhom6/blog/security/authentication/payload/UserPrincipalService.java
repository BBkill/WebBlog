package ltw.nhom6.blog.security.authentication.payload;

import ltw.nhom6.blog.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserPrincipalService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserPrincipalService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(email)
                .map(user -> new UserPrincipal(user.getEmail(),
                        user.getPassword(),
                        userRepository.getUserRole(user.getId())))
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException(email);
                });
    }
}
