package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.api.ArticlesApi;
import com.openclassrooms.mddapi.models.ArticleDto;
import com.openclassrooms.mddapi.services.ArticleServices;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArticleController implements ArticlesApi {

    private final ArticleServices articleServices;

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
            produces = { "application/json" }
    )
    public ResponseEntity<ArticleDto> articlesArticleIdGet(
            @Parameter(name = "article_id", description = "ID of the article to retrieve", required = true, in = ParameterIn.PATH) @PathVariable("article_id") Long articleId
    ) {
        return ResponseEntity.ok(articleServices.getArticle(articleId));
    }


}
