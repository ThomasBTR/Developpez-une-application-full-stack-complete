package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.mappers.IArticleToArticleDtoMapper;
import com.openclassrooms.mddapi.models.ArticleDto;
import com.openclassrooms.mddapi.models.ArticleEntity;
import com.openclassrooms.mddapi.repositories.IArticleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleServices {

    private final IArticleRepository articleRepository;

    public ArticleServices(IArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Transactional
    public List<ArticleDto> getArticles() {
        List<ArticleDto> response = new ArrayList<>();
        List<ArticleEntity> articles = new ArrayList<>();
        try {
            articles = articleRepository.findAll();
            response = articles.stream().map(IArticleToArticleDtoMapper.INSTANCE::articleEntityToArticleResponse).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return response;
        }
    }
}
