package com.openclassrooms.mddapi.mappers;

import com.openclassrooms.mddapi.models.Theme;
import com.openclassrooms.mddapi.models.ThemeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IThemeEntityToThemeMapper {

    IThemeEntityToThemeMapper INSTANCE = Mappers.getMapper(IThemeEntityToThemeMapper.class);

    Theme themeEntityToTheme(ThemeEntity themeEntity);
}
