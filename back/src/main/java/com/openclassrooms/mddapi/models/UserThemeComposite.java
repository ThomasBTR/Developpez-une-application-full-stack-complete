package com.openclassrooms.mddapi.models;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "USER_THEME")
public class UserThemeComposite {

    @Id
    private long id;

    private long userId;

    private long themeId;

}
