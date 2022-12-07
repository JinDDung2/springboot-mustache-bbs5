package com.example.bbsexercise.controller;

import com.example.bbsexercise.domain.dto.UserJoinRequestDto;
import com.example.bbsexercise.domain.dto.UserJoinResponseDto;
import com.example.bbsexercise.domain.dto.UserLoginRequestDto;
import com.example.bbsexercise.domain.entitiy.User;
import com.example.bbsexercise.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserApiController {

    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<UserJoinResponseDto> join(@RequestBody UserJoinRequestDto request) {
        return ResponseEntity.ok().body(userService.join(request));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginRequestDto request) {
        return ResponseEntity.ok().body(userService.login(request));
    }

}
