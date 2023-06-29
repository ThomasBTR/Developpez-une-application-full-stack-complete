package com.openclassrooms.mddapi.models;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

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

    @CreatedDate
    @Column(name = "date", updatable = false)
    private Instant date;

    @ManyToOne
    @JoinColumn(name = "theme_id", insertable = false)
    private ThemeEntity theme;


    @OneToMany(
            mappedBy = "articleEntity"
    )
    private List<Comment> comments;
}
