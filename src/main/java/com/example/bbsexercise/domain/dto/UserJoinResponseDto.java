package com.example.bbsexercise.domain.dto;

import com.example.bbsexercise.domain.entitiy.User;
import com.example.bbsexercise.domain.enums.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserJoinResponseDto {
    private Long id;
    private String username;
    private Role role;

    @Builder
    public UserJoinResponseDto(Long id, String username, Role role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }

    public static UserJoinResponseDto of(User user) {
        return UserJoinResponseDto.builder()
                .id(user.getId())
                .username(user.getUserName())
                .role(user.getRole())
                .build();
    }

}
