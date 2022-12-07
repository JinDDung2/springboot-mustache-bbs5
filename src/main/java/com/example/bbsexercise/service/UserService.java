package com.example.bbsexercise.service;

import com.example.bbsexercise.domain.dto.UserJoinRequestDto;
import com.example.bbsexercise.domain.dto.UserJoinResponseDto;
import com.example.bbsexercise.domain.dto.UserLoginRequestDto;
import com.example.bbsexercise.domain.entitiy.User;
import com.example.bbsexercise.domain.enums.Role;
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
            throw new RuntimeException("userName이 없습니다.");
        });
        return user;
    }

    public UserJoinResponseDto join(UserJoinRequestDto request) {

        // 중복체크
        userRepository.findByUserName(request.getUserName()).orElseThrow(() -> {
            throw new RuntimeException("유저이름 중복");
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
            throw new RuntimeException("유저네임을 가입한 적이 없습니다.");
        });

        // password 일치 확인
        if(!encoder.matches(request.getPassword(), findUser.getPassword())) {
            throw new RuntimeException("유저 패스워드가 틀립니다.");
        }

        return JwtTokenUtil.createToken(request.getUserName(), secretKey, expireTimeMs);
    }



}
