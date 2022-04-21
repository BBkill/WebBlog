package ltw.nhom6.blog.user.service.admin.iml;

import ltw.nhom6.blog.exception.common.BusinessException;
import ltw.nhom6.blog.user.dto.request.admin.req.AdminDeleteUserAccReqDto;
import ltw.nhom6.blog.user.dto.request.admin.req.AdminGrandAuthorityReqDto;
import ltw.nhom6.blog.user.dto.response.admin.response.AdminDeleteUserAccResDto;
import ltw.nhom6.blog.user.model.Role;
import ltw.nhom6.blog.user.model.User;
import ltw.nhom6.blog.user.repository.RoleRepository;
import ltw.nhom6.blog.user.repository.UserRepository;
import ltw.nhom6.blog.user.service.admin.AdminService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Set;

@Service
public class AdminServiceIml implements AdminService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;

    @Autowired
    public AdminServiceIml(UserRepository userRepository,
                           ModelMapper modelMapper,
                           RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;
    }

    @Override
    public AdminDeleteUserAccResDto deleteUserAcc(AdminDeleteUserAccReqDto reqDto) {
        validDeleteInputReq(reqDto);
        User user = userRepository.findUserByUsername(reqDto.getUsername()).get();
        user.setDeleted(true);
        AdminDeleteUserAccResDto resDto = modelMapper.map(user, AdminDeleteUserAccResDto.class);
        resDto.setRoles(user.getRoles());
        userRepository.save(user);
        return resDto;
    }

    @Override
    public void grandAuthority(AdminGrandAuthorityReqDto reqDto) {
        validGrandAuthorReq(reqDto);
        userRepository.findUserByUsername(reqDto.getUsername()).ifPresent(user -> {
            Set<Role> roles = user.getRoles();
            roles.add(roleRepository.getById(1));
            user.setRoles(roles);
            userRepository.save(user);
        });
    }

    private void validDeleteInputReq(AdminDeleteUserAccReqDto reqDto) {
        HashMap<String, String> error = new HashMap<>();
        userRepository.findUserByUsername(reqDto.getUsername()).ifPresentOrElse(user -> {
                    user.getRoles().forEach(role -> {
                        if (role.getName().equals("ADMIN")) {
                            error.put("NO AUTHORITY", "user has role admin");
                        }
                    });
                    if (!error.isEmpty()) {
                        throw new BusinessException(error, HttpStatus.BAD_REQUEST);
                    }
                },
                () -> error.put("USER NOT FOUND", "user not found"));
        if (!error.isEmpty()) {
            throw new BusinessException(error, HttpStatus.BAD_REQUEST);
        }
    }

    private void validGrandAuthorReq(AdminGrandAuthorityReqDto reqDto) {

        HashMap<String, String> error = new HashMap<>();
        userRepository.findUserByUsername(reqDto.getUsername()).ifPresentOrElse(user -> {
            user.getRoles().forEach(role -> {
                if (role.getName().equals("ADMIN")) {
                    error.put("AUTHORITY", "user has role admin already");
                }
            });
        }, () -> error.put("NOT FOUND", "user not found"));
        if (!error.isEmpty()) {
            throw new BusinessException(error, HttpStatus.BAD_REQUEST);
        }
    }
}
