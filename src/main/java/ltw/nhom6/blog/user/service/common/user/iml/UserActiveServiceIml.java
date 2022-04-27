package ltw.nhom6.blog.user.service.common.user.iml;

import com.google.common.cache.LoadingCache;
import ltw.nhom6.blog.exception.common.BusinessException;
import ltw.nhom6.blog.user.dto.request.UserActiveRequestDto;
import ltw.nhom6.blog.user.model.User;
import ltw.nhom6.blog.user.repository.UserRepository;
import ltw.nhom6.blog.user.service.common.user.UserActiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class UserActiveServiceIml implements UserActiveService {

    private final UserRepository userRepository;
    private final LoadingCache<String, String> otpCache;
    @Autowired
    public UserActiveServiceIml(UserRepository userRepository, LoadingCache<String, String> otpCache) {
        this.userRepository = userRepository;
        this.otpCache = otpCache;
    }

    @Override
    public void execute(UserActiveRequestDto activeRequestDto) {
        this.validateInput(activeRequestDto);
        User activeUser = userRepository.findUserByEmail(activeRequestDto.getEmail()).get();
        activeUser.setActivated(true);
        userRepository.save(activeUser);
    }

    private void validateInput(UserActiveRequestDto activeRequestDto) {
        Map<String, String> error = new HashMap<>();
        String email = activeRequestDto.getEmail();
        String otp = activeRequestDto.getOtp();

        userRepository.findUserByEmail(email).ifPresentOrElse(user -> {
            if (user.isActivated()) {
                error.put("ACTIVATED", "user is activated");
            }

            if (user.isDeleted()) {
                error.put("DELETED", "user is banned");
            }
        }, () -> {
            error.put("NOT FOUND", "user not found");
        });

        try {
            if (!otpCache.get(email).equals(otp)) {
                error.put("NOT MATCH", "otp not match");
            }
        } catch (ExecutionException e) {
            error.put("EXPIRED","otp is expired");
        }
        if (!error.isEmpty()) {
            throw new BusinessException(error, HttpStatus.BAD_REQUEST);
        }

    }
}
