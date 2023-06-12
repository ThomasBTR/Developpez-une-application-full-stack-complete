package com.openclassrooms.mddapi.mappers;

import com.openclassrooms.mddapi.models.*;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = {IUserToUserThemeInfoMapper.class})
public interface IThemeEntityToThemeMapper {

    IThemeEntityToThemeMapper INSTANCE = Mappers.getMapper(IThemeEntityToThemeMapper.class);

    @Mapping(source = "title", target = "title")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "id", target = "id")
    @Mapping(target = "users", source = "userList", qualifiedByName = "map UserThemeComposite to UserThemeInfo")
    Theme themeEntityToTheme(ThemeEntity themeEntity);

    @Named("map UserThemeComposite to UserThemeInfo")
    default UserThemeInfo map(Subscription subscription) {
        return IUserToUserThemeInfoMapper.INSTANCE.userToUserThemeInfoMapper(subscription.getUser());
    }

}
