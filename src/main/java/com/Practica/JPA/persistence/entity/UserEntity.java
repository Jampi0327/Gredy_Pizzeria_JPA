package com.Practica.JPA.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
public class UserEntity {
    @Id
    @Column(nullable = false,length = 20)
    private String username;

    @Column(nullable = false,length = 200)
    private String password;

    @Column(nullable = false,length = 50)
    private String email;

    @Column(nullable = false,columnDefinition = "TINYINT")
    private boolean locked;

    @Column(nullable = false,columnDefinition = "TINYINT")
    private boolean disable;

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private List<UserRoleEntity> roles;



}
