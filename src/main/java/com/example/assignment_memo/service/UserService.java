package com.example.assignment_memo.service;

import com.example.assignment_memo.dto.LoginRequestDto;
import com.example.assignment_memo.dto.MessageDto;
import com.example.assignment_memo.dto.SignupRequestDto;
import com.example.assignment_memo.dto.StatusEnum;
import com.example.assignment_memo.entity.User;
import com.example.assignment_memo.entity.UserRoleEnum;
import com.example.assignment_memo.repository.UserRepository;
import com.example.assignment_memo.util.ApiResponse.CustomException;
import com.example.assignment_memo.util.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

import static com.example.assignment_memo.util.ApiResponse.CodeError.*;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    private static final String ADMIN_TOKEN = "admin";

    private final PasswordEncoder passwordEncoder;

    public MessageDto<?> signup(SignupRequestDto dto){
        String username = dto.getUsername();
        String password = passwordEncoder.encode(dto.getPassword()); // μνΈν
        UserRoleEnum role = ADMIN_TOKEN.equals(dto.getAdminToken()) ? UserRoleEnum.ADMIN : UserRoleEnum.USER ;

        if(userRepository.findByUsername(username).isPresent()){
            throw new CustomException(EXIST_USER);
        };

        User user = new User(username, password, role);
        userRepository.save(user);

        return new MessageDto<>(StatusEnum.OK);
    }

    public MessageDto<?> login(LoginRequestDto dto, HttpServletResponse response){
        String username = dto.getUsername();
        String password = dto.getPassword();

        User user = userRepository.findByUsername(username).orElseThrow(
                ()-> new CustomException(LOGIN_MATCH_FAIL)
        );

        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new CustomException(INVALID_PASSWORD);
        }

        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getUsername(),  user.getRole()));  // λ©μλμ¬μ©νλ €λ©΄ μμ‘΄μ±μ£Όμ λ¨Όμ 

        return new MessageDto<>(StatusEnum.OK);
    }
}