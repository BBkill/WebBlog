package ltw.nhom6.blog.user.service.common.user.iml;

import ltw.nhom6.blog.exception.common.BusinessException;
import ltw.nhom6.blog.security.authentication.provider.JwtProvider;
import ltw.nhom6.blog.user.dto.request.UserLoginRequestDto;
import ltw.nhom6.blog.user.dto.response.UserLoginResponseDto;
import ltw.nhom6.blog.user.dto.response.UserRegisterResponseDto;
import ltw.nhom6.blog.user.model.User;
import ltw.nhom6.blog.user.repository.UserRepository;
import ltw.nhom6.blog.user.service.common.user.UserLoginService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UserLoginServiceIml implements UserLoginService {

    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public UserLoginServiceIml(JwtProvider jwtProvider,
                               UserRepository userRepository,
                               PasswordEncoder passwordEncoder,
                               ModelMapper modelMapper) {
        this.jwtProvider = jwtProvider;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserLoginResponseDto execute(UserLoginRequestDto requestDto) {
        validateInput(requestDto);
        UserLoginResponseDto responseDto = new UserLoginResponseDto();
        User user = userRepository.findUserByEmail(requestDto.getEmail()).get();
        UserRegisterResponseDto responseDto1 = modelMapper.map(user, UserRegisterResponseDto.class);
        responseDto.setAccessToken(jwtProvider.generateToken(requestDto.getEmail()));
        responseDto.setUserRegisterResponseDto(responseDto1);
        return responseDto;
    }

    private void validateInput(UserLoginRequestDto requestDto) {
        HashMap<String, String> error = new HashMap<>();
        userRepository.findUserByEmail(requestDto.getEmail()).ifPresentOrElse(user -> {
            if (!user.isActivated()) {
                error.put("ACTIVATED", "user is not activated");
            }
            if (user.isDeleted()) {
                error.put("BANNED", "user is banned");
            }
            if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
                error.put("WRONG PASSWORD", "invalid password");
            }
        }, () -> error.put("NOT FOUND", "email not found"));
        if (!error.isEmpty()) {
            throw new BusinessException(error, HttpStatus.BAD_REQUEST);
        }
    }
}
