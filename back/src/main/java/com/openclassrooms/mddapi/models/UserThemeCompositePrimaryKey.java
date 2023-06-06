package com.openclassrooms.mddapi.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class UserThemeCompositePrimaryKey implements Serializable {
    private static final long serialVersionUID = -2323495044588823410L;

    @Column(name = "user_id")
    Long userId;


    @Column(name = "theme_id")
    Long themeId;
}
