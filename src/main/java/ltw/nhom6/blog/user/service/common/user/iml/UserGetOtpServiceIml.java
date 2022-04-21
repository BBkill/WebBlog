package ltw.nhom6.blog.user.service.common.user.iml;

import com.google.common.cache.LoadingCache;
import ltw.nhom6.blog.exception.common.BusinessException;
import ltw.nhom6.blog.mail.MailService;
import ltw.nhom6.blog.mail.dto.MailRequestDto;
import ltw.nhom6.blog.otp.OtpHelper;
import ltw.nhom6.blog.user.dto.request.UserGetOtpRequestDto;
import ltw.nhom6.blog.user.repository.UserRepository;
import ltw.nhom6.blog.user.service.common.user.UserGetOtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
@Service
public class UserGetOtpServiceIml implements UserGetOtpService {

    private final LoadingCache<String, String> otpCache;
    private final UserRepository userRepository;
    private final MailService mailService;

    @Autowired
    public UserGetOtpServiceIml(LoadingCache<String, String> otpCache,
                                UserRepository userRepository,
                                MailService mailService) {
        this.otpCache = otpCache;
        this.userRepository = userRepository;
        this.mailService = mailService;
    }

    @Override
    public void execute(UserGetOtpRequestDto requestDto) {
        HashMap<String, String> error = new HashMap<>();
        userRepository.getUserByEmail(requestDto.getEmail()).ifPresentOrElse(user -> {
            if (user.isDeleted()) {
                error.put("DELETED", "user is deleted");
            }
            if (user.isActivated()) {
                error.put("ACTIVATED", "user is activated");
            }
        }, ()-> error.put("NOT FOUND", "user is not registered"));

        if (!error.isEmpty()) {
            throw new BusinessException(error, HttpStatus.BAD_REQUEST);
        }

        String email = requestDto.getEmail();
        String otp = OtpHelper.generateOTP();
        otpCache.put(email, otp);
        this.sendMail(email, otp);
    }

    private void sendMail(String email, String otp) {
        MailRequestDto mailRequestDto = new MailRequestDto();
        mailRequestDto.setMessage("Your otp active code is: " + otp);
        mailRequestDto.setReceiver(email);
        mailRequestDto.setSubject("Active code");
        mailService.sendMail(mailRequestDto);
    }
}
