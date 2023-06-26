package com.openclassrooms.mddapi.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "ARTICLES")
public class ArticleEntity {

    @Id
    private long id;

    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
    @Column(name = "author")
    String author;
    @Column(name = "date")
    private LocalDateTime date;

    @ManyToOne
    @MapsId("theme_id")
    private ThemeEntity theme;
}
