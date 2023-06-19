package com.openclassrooms.mddapi.mappers;

import com.openclassrooms.mddapi.models.ThemeEntity;
import com.openclassrooms.mddapi.models.ThemeUserInfo;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.models.UserThemeInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IUserToUserThemeInfoMapper {

    IUserToUserThemeInfoMapper INSTANCE = Mappers.getMapper(IUserToUserThemeInfoMapper.class);

    @Mapping(target = "email", source = "email")
    @Mapping(target = "username", source = "username")
    @Mapping(target = "id", source = "id")
    UserThemeInfo userToUserThemeInfoMapper(User user);

    @Mapping(target = "title", source = "title")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "id", source = "id")
    ThemeUserInfo subscriptionToThemeUserInfo(ThemeEntity theme);
}
