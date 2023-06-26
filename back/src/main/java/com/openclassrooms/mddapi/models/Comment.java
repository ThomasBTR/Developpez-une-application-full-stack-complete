package com.openclassrooms.mddapi.models;

import lombok.*;

import javax.persistence.*;

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

    @ManyToOne
    @JoinColumn(name = "article_id", insertable = false)
    private ArticleEntity articleEntity;
}
