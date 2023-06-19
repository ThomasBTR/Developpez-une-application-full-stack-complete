package com.openclassrooms.mddapi.mappers;

import com.openclassrooms.mddapi.models.Subscription;
import com.openclassrooms.mddapi.models.ThemeUserInfo;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.models.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IUserToUserProfileMapper {

    IUserToUserProfileMapper INSTANCE = Mappers.getMapper(IUserToUserProfileMapper.class);

    @Mapping(target = "subscriptions", ignore = true)
    UserProfile userToUserProfile(User user);

    @Mapping(target = "subscriptions", source = "themes", qualifiedByName = "userToUserProfileForThemes")
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "email", source = "email")
    @Mapping(target = "username", source = "username")
    @Mapping(target = "id", source = "id")
    UserProfile userToUserProfileForThemes(User user);

    @Named("userToUserProfileForThemes")
    default ThemeUserInfo userToUserProfileForThemes(Subscription subscriptions) {
        return IUserToUserThemeInfoMapper.INSTANCE.subscriptionToThemeUserInfo(subscriptions.getTheme());
    }


}
