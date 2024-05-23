package com.example.menuw.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Refrigerator {
    @Id @GeneratedValue
    @Column(name = "refrigeratorId")
    private Integer id;

    private String menu;

    @OneToOne
    @JoinColumn(name = "id")
    private User user;
}
