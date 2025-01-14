package com.openclassrooms.mddapi.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * The type Theme entity.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "THEMES")
public class ThemeEntity {

    @Id
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @OneToMany(
            mappedBy = "theme",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Subscription> userList;

    @OneToMany(
            mappedBy = "theme",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ArticleEntity> articleEntities;
}
