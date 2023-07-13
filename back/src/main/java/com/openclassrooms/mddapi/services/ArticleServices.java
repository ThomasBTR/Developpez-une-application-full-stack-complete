package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.mappers.IArticlePostRequestToArticleMapper;
import com.openclassrooms.mddapi.mappers.IArticleToArticleDtoMapper;
import com.openclassrooms.mddapi.mappers.ICommentToDtoMapper;
import com.openclassrooms.mddapi.models.*;
import com.openclassrooms.mddapi.repositories.IArticleRepository;
import com.openclassrooms.mddapi.repositories.ICommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Article services.
 */
@Service
public class ArticleServices {

    private final IArticleRepository articleRepository;
    private final ICommentRepository commentRepository;

    private final ThemeServices themeServices;

    /**
     * Instantiates a new Article services.
     *
     * @param articleRepository the article repository
     * @param commentRepository the comment repository
     * @param themeServices     the theme services
     */
    public ArticleServices(IArticleRepository articleRepository, ICommentRepository commentRepository, ThemeServices themeServices) {
        this.articleRepository = articleRepository;
        this.commentRepository = commentRepository;
        this.themeServices = themeServices;
    }

    /**
     * Gets articles.
     *
     * @return the articles
     */
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

    /**
     * Gets article.
     *
     * @param articleId the article id
     * @return the article
     */
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

    /**
     * Add comment comment dto.
     *
     * @param articleId  the article id
     * @param commentDto the comment dto
     * @return the comment dto
     */
    @Transactional
    public CommentDto addComment(Long articleId, CommentDto commentDto) {
        CommentDto response = new CommentDto();
        try {
            ArticleEntity articleEntity = articleRepository.findById(articleId).orElse(null);
            if (articleEntity != null) {
                Comment comment = ICommentToDtoMapper.INSTANCE.commentDtoToCommentEntity(commentDto);
                comment.setArticleEntity(articleEntity);
                articleEntity.getComments().add(comment);
                articleEntity = saveArticle(articleEntity);
                response = ICommentToDtoMapper.INSTANCE.commentToCommentDto(articleEntity.getComments().get(articleEntity.getComments().size() - 1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * Create article article dto.
     *
     * @param articlePostRequest the article post request
     * @return the article dto
     */
    @Transactional
    public ArticleDto createArticle(ArticlePostRequest articlePostRequest) {
        ArticleDto response = new ArticleDto();
        try {
            response = createAndSaveArticle(articlePostRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * Save article article entity.
     *
     * @param articleEntity the article entity
     * @return the article entity
     */
    @Transactional
    public ArticleEntity saveArticle(ArticleEntity articleEntity) {
        return articleRepository.save(articleEntity);
    }

    private ArticleDto createAndSaveArticle(ArticlePostRequest articlePostRequest) {
        ArticleEntity articleEntity = getArticleEntity(articlePostRequest);
        ThemeEntity themeEntity = themeServices.handleTheme(articlePostRequest, articleEntity);
        return saveArticleAndReturnResponse(articleEntity, themeEntity);
    }

    private ArticleEntity getArticleEntity(ArticlePostRequest articlePostRequest) {
        ArticleEntity articleEntity = IArticlePostRequestToArticleMapper.INSTANCE.articlePostRequestToArticleEntity(articlePostRequest);
        articleEntity.setDate(Instant.now());
        return articleEntity;
    }

    private ArticleDto saveArticleAndReturnResponse(ArticleEntity articleEntity, ThemeEntity themeEntity) {
        articleEntity = saveArticle(articleEntity);
        return IArticleToArticleDtoMapper.INSTANCE.articleEntityToArticleResponse(articleEntity);
    }
}
