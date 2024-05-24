package com.example.menuw.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Menu {
    @Id @GeneratedValue
    private Integer menuId;
    private Integer menuLike;
    private String menuName;
    private String menuImageURL;
    private String ingredients;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    public void updateMenuLike(Integer menuLike) {
        this.menuLike = menuLike;
    }
}
