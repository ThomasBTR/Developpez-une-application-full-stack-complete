package com.openclassrooms.mddapi.mappers;

import com.openclassrooms.mddapi.models.Subscription;
import com.openclassrooms.mddapi.models.ThemeUserInfo;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.models.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

/**
 * The interface User to user profile mapper.
 */
@Mapper
public interface IUserToUserProfileMapper {

    /**
     * The constant INSTANCE.
     */
    IUserToUserProfileMapper INSTANCE = Mappers.getMapper(IUserToUserProfileMapper.class);

    /**
     * User to user profile user profile.
     *
     * @param user the user
     * @return the user profile
     */
    @Mapping(target = "subscriptions", ignore = true)
    UserProfile userToUserProfile(User user);

    /**
     * User to user profile for themes user profile.
     *
     * @param user the user
     * @return the user profile
     */
    @Mapping(target = "subscriptions", source = "themes", qualifiedByName = "userToUserProfileForThemes")
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "email", source = "email")
    @Mapping(target = "username", source = "username")
    @Mapping(target = "id", source = "id")
    UserProfile userToUserProfileForThemes(User user);

    /**
     * User to user profile for themes theme user info.
     *
     * @param subscriptions the subscriptions
     * @return the theme user info
     */
    @Named("userToUserProfileForThemes")
    default ThemeUserInfo userToUserProfileForThemes(Subscription subscriptions) {
        return IUserToUserThemeInfoMapper.INSTANCE.subscriptionToThemeUserInfo(subscriptions.getTheme());
    }


}
