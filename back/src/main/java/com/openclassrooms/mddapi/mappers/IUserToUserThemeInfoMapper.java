package com.openclassrooms.mddapi.mappers;

import com.openclassrooms.mddapi.models.ThemeEntity;
import com.openclassrooms.mddapi.models.ThemeUserInfo;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.models.UserThemeInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * The interface User to user theme info mapper.
 */
@Mapper
public interface IUserToUserThemeInfoMapper {

    /**
     * The constant INSTANCE.
     */
    IUserToUserThemeInfoMapper INSTANCE = Mappers.getMapper(IUserToUserThemeInfoMapper.class);

    /**
     * User to user theme info mapper user theme info.
     *
     * @param user the user
     * @return the user theme info
     */
    @Mapping(target = "email", source = "email")
    @Mapping(target = "username", source = "username")
    @Mapping(target = "id", source = "id")
    UserThemeInfo userToUserThemeInfoMapper(User user);

    /**
     * Subscription to theme user info theme user info.
     *
     * @param theme the theme
     * @return the theme user info
     */
    @Mapping(target = "title", source = "title")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "id", source = "id")
    ThemeUserInfo subscriptionToThemeUserInfo(ThemeEntity theme);
}
