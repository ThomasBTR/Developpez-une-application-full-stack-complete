package com.openclassrooms.mddapi.mappers;

import com.openclassrooms.mddapi.models.ArticleDto;
import com.openclassrooms.mddapi.models.ArticleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * The interface Article to article dto mapper.
 */
@Mapper
public interface IArticleToArticleDtoMapper {

    /**
     * The constant INSTANCE.
     */
    IArticleToArticleDtoMapper INSTANCE = Mappers.getMapper(IArticleToArticleDtoMapper.class);

    /**
     * Article entity to article response article dto.
     *
     * @param article the article
     * @return the article dto
     */
    @Mapping(source = "title", target = "title")
    @Mapping(source = "content", target = "content")
    @Mapping(source = "id", target = "id")
    @Mapping(source = "theme.title", target = "theme")
    @Mapping(source = "date", target = "date")
    @Mapping(source = "author", target = "author")
    @Mapping(source = "comments", target = "comments")
    ArticleDto articleEntityToArticleResponse(ArticleEntity article);

    /**
     * Article dto to article entity article entity.
     *
     * @param articleDto the article dto
     * @return the article entity
     */
    @Mapping(source = "title", target = "title")
    @Mapping(source = "content", target = "content")
    @Mapping(source = "id", target = "id")
    @Mapping(source = "theme", target = "theme.title")
    @Mapping(source = "date", target = "date")
    @Mapping(source = "author", target = "author")
    @Mapping(source = "comments", target = "comments")
    ArticleEntity articleDtoToArticleEntity(ArticleDto articleDto);


}
