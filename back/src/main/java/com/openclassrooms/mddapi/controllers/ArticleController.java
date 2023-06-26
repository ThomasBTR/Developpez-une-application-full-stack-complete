package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.api.ArticlesApi;
import com.openclassrooms.mddapi.models.ArticleDto;
import com.openclassrooms.mddapi.services.ArticleServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArticleController implements ArticlesApi {

    private ArticleServices articleServices;

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


}
