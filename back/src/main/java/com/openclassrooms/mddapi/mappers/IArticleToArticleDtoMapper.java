package com.openclassrooms.mddapi.mappers;

import com.openclassrooms.mddapi.models.ArticleDto;
import com.openclassrooms.mddapi.models.ArticleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface IArticleToArticleDtoMapper {

    IArticleToArticleDtoMapper INSTANCE = Mappers.getMapper(IArticleToArticleDtoMapper.class);

    @Mapping(source = "title", target = "title")
    @Mapping(source = "content", target = "content")
    @Mapping(source = "id", target = "id")
    @Mapping(source = "theme.title", target = "theme")
    @Mapping(source = "date", target = "date")
    @Mapping(source = "author", target = "author")
    @Mapping(source = "comments", target = "comments")
    ArticleDto articleEntityToArticleResponse(ArticleEntity article);



}
