package ltw.nhom6.blog.user.service.common.user.iml;

import com.google.common.cache.LoadingCache;
import ltw.nhom6.blog.exception.common.BusinessException;
import ltw.nhom6.blog.mail.MailService;
import ltw.nhom6.blog.mail.dto.MailRequestDto;
import ltw.nhom6.blog.otp.OtpHelper;
import ltw.nhom6.blog.user.dto.request.UserGetOtpResetPasswordRequestDto;
import ltw.nhom6.blog.user.dto.request.UserResetPasswordRequestDto;
import ltw.nhom6.blog.user.repository.UserRepository;
import ltw.nhom6.blog.user.service.common.user.UserResetPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UserForgetPasswordIml implements UserResetPasswordService {

    private final UserRepository userRepository;
    private final MailService mailService;
    private final LoadingCache<String, String> otpCache;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserForgetPasswordIml(UserRepository userRepository,
                                 MailService mailService,
                                 LoadingCache<String, String> otpCache,
                                 PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.mailService = mailService;
        this.otpCache = otpCache;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void forgetPassword(UserGetOtpResetPasswordRequestDto requestDto) {
        validateInputForgetPassword(requestDto);
        sendingMail(requestDto);
    }

    @Override
    public void resetPassword(UserResetPasswordRequestDto requestDto) {
        validateInputResetPassword(requestDto);
        userRepository.findUserByEmail(requestDto.getEmail()).ifPresent(user -> {
            String password = passwordEncoder.encode(requestDto.getNewPassword());
            user.setPassword(password);
            userRepository.save(user);
        });
    }

    /**
     * validate user's info
     * @param requestDto UserGetMissingPasswordRequestDto
     */
    private void validateInputForgetPassword(UserGetOtpResetPasswordRequestDto requestDto) {

        HashMap<String, String> error = new HashMap<>();
        userRepository.findUserByEmail(requestDto.getEmail()).ifPresentOrElse(user -> {
            if (user.isDeleted()) {
                error.put("DELETED", "user is deleted");
            }
        }, () -> error.put("NOT FOUND", "user not found"));

        if (!error.isEmpty()) {
            throw new BusinessException(error, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * send otp code to user's email
     * @param requestDto UserGetMissingPasswordRequestDto
     */
    private void sendingMail(UserGetOtpResetPasswordRequestDto requestDto){
        String otp =  OtpHelper.generateOTP();
        otpCache.put(requestDto.getEmail(), otp);
        MailRequestDto mailRequestDto = new MailRequestDto();
        mailRequestDto.setReceiver(requestDto.getEmail());
        mailRequestDto.setSubject("Otp code reset password");
        mailRequestDto.setMessage("Your otp code is: "+ otp);
        mailService.sendMail(mailRequestDto);
    }

    /**
     * validate input for reset password
     * @param requestDto UserResetPasswordRequestDto
     */
    private void validateInputResetPassword(UserResetPasswordRequestDto requestDto) {
        HashMap<String, String> error = new HashMap<>();
        String email = requestDto.getEmail();
        String otpCode = requestDto.getOtp();
        userRepository.findUserByEmail(email).ifPresentOrElse(user -> {
            if (!requestDto.getNewPassword().equals(requestDto.getConfirmPassword())) {
                error.put("WRONG PASSWORD","password not match");
            }
        }, () -> error.put("NOT REGISTERED","user is not registered"));

        try {
            if (!otpCache.get(email).equals(otpCode)) {
                error.put("WRONG OTP", "otp is not match");
            }
        } catch (Exception e) {
            error.put("EXPIRED OTP", "otp is expired");
        }

        if (!error.isEmpty()) {
            throw new BusinessException(error, HttpStatus.BAD_REQUEST);
        }
    }
}
