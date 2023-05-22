package com.openclassrooms.mddapi.mappers;

import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.models.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IUserToUserProfileMapper {

    IUserToUserProfileMapper INSTANCE = Mappers.getMapper(IUserToUserProfileMapper.class);

    @Mapping(target = "subscriptions", source = "subscriptions")
    UserProfile userToUserProfile(User user);

    User userProfileToUser(UserProfile userProfile);

}
