package com.example.bbsexercise.domain.entitiy;

import com.example.bbsexercise.domain.enums.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    private String userName;
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Review> reviews = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public User(Long id, String userName, String password, Role role) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.role = role;
    }
}
