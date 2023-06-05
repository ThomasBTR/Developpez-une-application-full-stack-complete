package com.openclassrooms.mddapi.mappers;

import com.openclassrooms.mddapi.models.Theme;
import com.openclassrooms.mddapi.models.ThemeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IThemeEntityToThemeMapper {

    IThemeEntityToThemeMapper INSTANCE = Mappers.getMapper(IThemeEntityToThemeMapper.class);

    @Mapping(target = "userId", ignore = true)
    @Mapping(source = "title", target = "title")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "id", target = "id")
    Theme themeEntityToTheme(ThemeEntity themeEntity);
}
