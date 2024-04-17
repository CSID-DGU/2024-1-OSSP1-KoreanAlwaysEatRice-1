package com.example.menuw.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class User {
    @Id @GeneratedValue
    @Column(name = "userId")
    private Integer id;

    private String userName;
    private String password;
    private String userNickname;
    private String userImageUrl;
    private String userPhone;
}
