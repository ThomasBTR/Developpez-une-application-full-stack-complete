package com.openclassrooms.mddapi.models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "THEMES")
public class ThemeEntity {

    @Id
    private long id;

    @ManyToOne(targetEntity = User.class)
    private long userId;

    @Column(name = "name")
    private String name;
}
