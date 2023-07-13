package com.openclassrooms.mddapi.models;

import lombok.*;

import javax.persistence.*;

/**
 * The type Comment.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "COMMENTS")
public class Comment {

    @Id
    private long id;

    @Column(name = "content")
    private String content;

    @Column(name = "author")
    private String username;

    @ManyToOne
    @JoinColumn(name = "article_id", nullable = false)
    private ArticleEntity articleEntity;
}
