package ltw.nhom6.blog.user.service.common.user.iml;

import ltw.nhom6.blog.exception.common.BusinessException;
import ltw.nhom6.blog.user.dto.request.UserChangePasswordRequestDto;
import ltw.nhom6.blog.user.repository.UserRepository;
import ltw.nhom6.blog.user.service.common.user.UserChangePasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UserChangePasswordServiceIml implements UserChangePasswordService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserChangePasswordServiceIml(UserRepository userRepository,
                                        PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void execute(UserChangePasswordRequestDto requestDto) {
        this.validateInput(requestDto);
        userRepository.findUserByEmail(requestDto.getEmail()).ifPresent(user -> {
                String password = passwordEncoder.encode(requestDto.getNewPassword());
                user.setPassword(password);
                userRepository.save(user);
            }
        );
    }

    private void validateInput(UserChangePasswordRequestDto requestDto) {
        HashMap<String, String> error = new HashMap<>();
        String oldPassword = requestDto.getOldPassword();
        String newPassword = requestDto.getNewPassword();
        String confirmPassword = requestDto.getConfirmPassword();
        String email = requestDto.getEmail();
        userRepository.findUserByEmail(email).ifPresentOrElse(user -> {
            if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
                error.put("password", " not match");
            }
        }, () -> error.put("user", " is not registered"));

        if (!newPassword.equals(confirmPassword)) {
            error.put("new password", " not match");
        }

        if(newPassword.equals(oldPassword)) {
            error.put("password", "old and new password is the same");
        }

        if (!error.isEmpty()) {
            throw new BusinessException(error, HttpStatus.BAD_REQUEST);
        }
    }
}
