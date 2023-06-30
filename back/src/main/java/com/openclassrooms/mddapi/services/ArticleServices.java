package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.mappers.IArticlePostRequestToArticleMapper;
import com.openclassrooms.mddapi.mappers.IArticleToArticleDtoMapper;
import com.openclassrooms.mddapi.mappers.ICommentToDtoMapper;
import com.openclassrooms.mddapi.models.*;
import com.openclassrooms.mddapi.repositories.IArticleRepository;
import com.openclassrooms.mddapi.repositories.ICommentRepository;
import com.openclassrooms.mddapi.repositories.IThemeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArticleServices {

    private final IArticleRepository articleRepository;
    private ICommentRepository commentRepository;
    private final IThemeRepository themeRepository;

    public ArticleServices(IArticleRepository articleRepository, ICommentRepository commentRepository,
                           IThemeRepository iThemeRepository) {
        this.articleRepository = articleRepository;
        this.commentRepository = commentRepository;
        this.themeRepository = iThemeRepository;
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
        }
        return response;
    }

    @Transactional
    public ArticleDto getArticle(Long articleId) {
        ArticleDto articleDto = new ArticleDto();
        try {
            ArticleEntity articleEntity = articleRepository.findById(articleId).orElse(null);
            if (articleEntity != null) {
                articleDto = IArticleToArticleDtoMapper.INSTANCE.articleEntityToArticleResponse(articleEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return articleDto;
    }

    public CommentDto addComment(Long articleId, CommentDto commentDto) {
        CommentDto response = new CommentDto();
        try {
            ArticleEntity articleEntity = articleRepository.findById(articleId).orElse(null);
            if (articleEntity != null) {
                Comment comment = ICommentToDtoMapper.INSTANCE.commentDtoToCommentEntity(commentDto);
                comment.setArticleEntity(articleEntity);
                comment = commentRepository.save(comment);
                articleEntity.getComments().add(comment);
                articleEntity = articleRepository.save(articleEntity);
                response = ICommentToDtoMapper.INSTANCE.commentToCommentDto(articleEntity.getComments().get(articleEntity.getComments().size() - 1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    public ArticleDto createArticle(ArticlePostRequest articlePostRequest) {
        ArticleDto response = new ArticleDto();
        try {
            response = createAndSaveArticle(articlePostRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    private ArticleDto createAndSaveArticle(ArticlePostRequest articlePostRequest) {
        ArticleEntity articleEntity = getArticleEntity(articlePostRequest);
        ThemeEntity themeEntity = handleTheme(articlePostRequest, articleEntity);
        return saveArticleAndReturnResponse(articleEntity, themeEntity);
    }

    private ArticleEntity getArticleEntity(ArticlePostRequest articlePostRequest) {
        ArticleEntity articleEntity = IArticlePostRequestToArticleMapper.INSTANCE.articlePostRequestToArticleEntity(articlePostRequest);
        articleEntity.setDate(Instant.now());
        return articleEntity;
    }

    private ThemeEntity handleTheme(ArticlePostRequest articlePostRequest, ArticleEntity articleEntity) {
        ThemeEntity themeEntity = null;
        if (articlePostRequest.getTheme() != null) {
            String themeTitle = articlePostRequest.getTheme();
            List<String> themeTitleList = themeRepository.findAll().stream().map(ThemeEntity::getTitle).collect(Collectors.toList());
            for (String theme : themeTitleList) {
                if (theme.equalsIgnoreCase(themeTitle)) {
                    themeEntity = themeRepository.findByTitle(theme).orElse(null);
                    articleEntity.setTheme(themeEntity);
                }
            }
        }
        return themeEntity;
    }

    private ArticleDto saveArticleAndReturnResponse(ArticleEntity articleEntity, ThemeEntity themeEntity) {
        articleEntity = articleRepository.save(articleEntity);
        themeEntity.getArticleEntities().add(articleEntity);
        themeRepository.save(themeEntity);
        return IArticleToArticleDtoMapper.INSTANCE.articleEntityToArticleResponse(articleEntity);
    }

}
