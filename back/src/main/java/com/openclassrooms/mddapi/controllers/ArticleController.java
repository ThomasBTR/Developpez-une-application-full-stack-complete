package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.api.ArticlesApi;
import com.openclassrooms.mddapi.models.ArticleDto;
import com.openclassrooms.mddapi.models.ArticlePostRequest;
import com.openclassrooms.mddapi.models.CommentDto;
import com.openclassrooms.mddapi.services.ArticleServices;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * The type Article controller.
 */
@RestController
public class ArticleController implements ArticlesApi {

    private final ArticleServices articleServices;

    /**
     * Instantiates a new Article controller.
     *
     * @param articleServices the article services
     */
    public ArticleController(ArticleServices articleServices) {
        this.articleServices = articleServices;
    }

    @Override
    @GetMapping(
            value = "/articles",
            produces = {"application/json"}
    )
    public ResponseEntity<List<ArticleDto>> articlesGet() {
        return ResponseEntity.ok(articleServices.getArticles());
    }


    @Override
    @GetMapping(
            value = "/articles/{article_id}",
            produces = {"application/json"}
    )
    public ResponseEntity<ArticleDto> articlesArticleIdGet(
            @Parameter(name = "article_id", description = "ID of the article to retrieve", required = true, in = ParameterIn.PATH) @PathVariable("article_id") Long articleId
    ) {
        return ResponseEntity.ok(articleServices.getArticle(articleId));
    }

    @Override
    @PostMapping(
            value = "/articles/{id}/comment",
            produces = {"application/json"},
            consumes = {"application/json"}
    )
    public ResponseEntity<CommentDto> articlesIdCommentPost(
            @Parameter(name = "id", description = "ID of the article to comment on", required = true, in = ParameterIn.PATH) @PathVariable("id") Long id,
            @Parameter(name = "CommentDto", description = "", required = true) @Valid @RequestBody CommentDto commentDto
    ) {
        return ResponseEntity.ok(articleServices.addComment(id, commentDto));
    }


    @Override
    @PostMapping(
            value = "/articles",
            produces = {"application/json"},
            consumes = {"application/json"}
    )
    public ResponseEntity<ArticleDto> createArticle(
            @Parameter(name = "ArticlePostRequest", description = "", required = true) @Valid @RequestBody ArticlePostRequest articlePostRequest
    ) {
        return ResponseEntity.ok(articleServices.createArticle(articlePostRequest));
    }

}
