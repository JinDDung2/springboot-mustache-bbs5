package com.example.bbsexercise.service;

import com.example.bbsexercise.domain.dto.UserJoinRequestDto;
import com.example.bbsexercise.domain.dto.UserJoinResponseDto;
import com.example.bbsexercise.domain.dto.UserLoginRequestDto;
import com.example.bbsexercise.domain.entitiy.User;
import com.example.bbsexercise.domain.enums.Role;
import com.example.bbsexercise.exception.ErrorCode;
import com.example.bbsexercise.exception.SpringBootAppException;
import com.example.bbsexercise.repository.UserRepository;
import com.example.bbsexercise.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Value("${jwt.token.secret}")
    private String secretKey;
    private long expireTimeMs = 1000 * 60 * 60;

    public User findByUserName(String userName) {
        User user = userRepository.findByUserName(userName).orElseThrow(() -> {
            throw new SpringBootAppException(ErrorCode.NOT_FOUND, userName + "을 찾을 수 없습니다.");
        });
        return user;
    }

    public UserJoinResponseDto join(UserJoinRequestDto request) {

        // 중복체크
        userRepository.findByUserName(request.getUserName()).ifPresent(user -> {
            throw new SpringBootAppException(ErrorCode.DUPLICATED_USER_NAME, String.format("%s는 이미 존재하는 회원입니다.", request.getUserName()));
        });

        // 회원가입
        User saveUser = User.builder()
                .userName(request.getUserName())
                .password(encoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(saveUser);

        return UserJoinResponseDto.of(saveUser);
    }

    public String login(UserLoginRequestDto request) {

        // username 일치 확인
        User findUser = userRepository.findByUserName(request.getUserName()).orElseThrow(() -> {
            throw new SpringBootAppException(ErrorCode.NOT_FOUND, String.format("%s는 이미 존재하지 않는 아이디 입니다.", request.getUserName()));
        });

        // password 일치 확인
        if(!encoder.matches(request.getPassword(), findUser.getPassword())) {
            throw new SpringBootAppException(ErrorCode.INVALID_PASSWORD, "아이디와 비밀번호를 확인해 주세요");
        }

        return JwtTokenUtil.createToken(request.getUserName(), secretKey, expireTimeMs);
    }

}
