package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.mappers.IUserToUserProfileMapper;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.models.UserProfile;
import com.openclassrooms.mddapi.models.UserUpdate;
import com.openclassrooms.mddapi.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServices {

    UserRepository userRepository;

    UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public List<UserProfile> usersGet() {
        List<User> users = null;
        List<UserProfile> userProfiles = null;
        try {
            users = userRepository.findAll();
            userProfiles = users.stream().map(IUserToUserProfileMapper.INSTANCE::userToUserProfile).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userProfiles;
    }

    @Transactional
    public UserProfile usersPut(UserUpdate userUpdate) {
        UserProfile userProfile = null;
        User user = null;
        try {
            user = userRepository.findByEmail(userUpdate.getEmail()).orElse(null);
            if (user != null) {
                user = User.builder().id(user.getId())
                        .email(userUpdate.getEmail())
                        .username(userUpdate.getUsername())
                        .password(userUpdate.getPassword())
                        .createdAt(user.getCreatedAt())
                        .updatedAt(LocalDateTime.now())
                        .build();
                user = userRepository.save(user);
                userProfile = IUserToUserProfileMapper.INSTANCE.userToUserProfile(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userProfile;
    }

    @Transactional
    public UserProfile userIdGet(Long id) {
        User user = null;
        UserProfile userProfile = null;
        try {
            user = userRepository.findById(id).orElse(null);
            if (user != null) {
                userProfile = IUserToUserProfileMapper.INSTANCE.userToUserProfileForThemes(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userProfile;
    }
}
