package ltw.nhom6.blog.user.service.common.user.iml;

import com.google.common.cache.LoadingCache;
import ltw.nhom6.blog.exception.common.BusinessException;
import ltw.nhom6.blog.mail.MailService;
import ltw.nhom6.blog.mail.dto.MailRequestDto;
import ltw.nhom6.blog.otp.OtpHelper;
import ltw.nhom6.blog.user.dto.request.UserRegisterRequestDto;
import ltw.nhom6.blog.user.dto.response.UserRegisterResponseDto;
import ltw.nhom6.blog.user.model.Role;
import ltw.nhom6.blog.user.model.User;
import ltw.nhom6.blog.user.repository.RoleRepository;
import ltw.nhom6.blog.user.repository.UserRepository;
import ltw.nhom6.blog.user.service.common.user.UserRegisterService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserRegisterServiceIml implements UserRegisterService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final MailService mailService;
    private final RoleRepository roleRepository;
    private final LoadingCache<String, String> otpCache;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserRegisterServiceIml(UserRepository userRepository,
                                  ModelMapper modelMapper,
                                  MailService mailService,
                                  RoleRepository roleRepository,
                                  LoadingCache<String, String> otpCache,
                                  PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.mailService = mailService;
        this.roleRepository = roleRepository;
        this.otpCache = otpCache;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserRegisterResponseDto execute(UserRegisterRequestDto registerRequestDto) {

        validateInput(registerRequestDto);
        User user = modelMapper.map(registerRequestDto, User.class);
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findById(2).get());

        //set attributes before save
        user.setActivated(false);
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(registerRequestDto.getPassword()));

        //set attribute before response
        UserRegisterResponseDto responseDto = modelMapper.map(userRepository.save(user), UserRegisterResponseDto.class);
        responseDto.setRoles(roles);

        //sending mail
        this.sendMail(registerRequestDto.getEmail());
        return responseDto;
    }

    /**
     * validate user register form
     * @param registerRequestDto UserRegisterRequestDto
     */
    private void validateInput(UserRegisterRequestDto registerRequestDto) {

        HashMap<String, String> error = new HashMap<>();

        //check duplicate username
        userRepository.findUserByUsername(registerRequestDto.getUsername()).ifPresent(
                user -> error.put("username", " already existed"));

        //check duplicate email
        userRepository.findUserByEmail(registerRequestDto.getEmail()).ifPresent(
                user -> error.put("email", "already existed"));

        if (!registerRequestDto.getPassword().equals(registerRequestDto.getPasswordConfirm())) {
            error.put("password ", "not match");
        }

        if (!error.isEmpty()) {
            throw new BusinessException(error, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * send active code to user's email
     * save active code to cache, expired time is 3 minutes
     * @param email String
     */
    private void sendMail(String email) {
        String otpCode = OtpHelper.generateOTP();
        MailRequestDto mailRequestDto = new MailRequestDto();
        mailRequestDto.setMessage("your active code is: " + otpCode);
        mailRequestDto.setReceiver(email);
        mailRequestDto.setSubject("Activation mail");
        otpCache.put(email, otpCode);
        mailService.sendMail(mailRequestDto);
    }

}
