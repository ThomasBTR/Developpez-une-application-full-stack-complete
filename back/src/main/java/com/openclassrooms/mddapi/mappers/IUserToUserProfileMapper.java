package com.openclassrooms.mddapi.mappers;

import com.openclassrooms.mddapi.models.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

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
