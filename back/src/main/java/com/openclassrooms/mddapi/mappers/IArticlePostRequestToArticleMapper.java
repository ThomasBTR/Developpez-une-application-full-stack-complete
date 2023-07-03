package com.openclassrooms.mddapi.mappers;

import com.openclassrooms.mddapi.models.ArticleEntity;
import com.openclassrooms.mddapi.models.ArticlePostRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IArticlePostRequestToArticleMapper {

    IArticlePostRequestToArticleMapper INSTANCE = Mappers.getMapper(IArticlePostRequestToArticleMapper.class);

    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "theme.title", source = "theme")
    @Mapping(target = "date", ignore = true)
    @Mapping(target = "author", source = "author")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "content", source = "content")
    @Mapping(target = "title", source = "title")
    ArticleEntity articlePostRequestToArticleEntity(ArticlePostRequest articlePostRequest);


}
