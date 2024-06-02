package com.example.menuw.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Menu {
    @Id @Column
    private Integer menuId;
    @Column
    private Integer menuLike;
    @Column
    private String menuName;
    @Column
    private String menuImageURL;
    @Column
    private String ingredients;
    @Column
    private String recipe;
    @Column
    private String menuType;
    @Column
    private double cal;
    @Column
    private double na;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
}
