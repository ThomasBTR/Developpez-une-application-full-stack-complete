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

/**
 * The type User services.
 */
@Service
public class UserServices {

    /**
     * The User repository.
     */
    UserRepository userRepository;

    /**
     * Instantiates a new User services.
     *
     * @param userRepository the user repository
     */
    UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Users get list.
     *
     * @return the list
     */
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

    /**
     * Users put user profile.
     *
     * @param userUpdate the user update
     * @param id         the id
     * @return the user profile
     */
    @Transactional
    public UserProfile usersPut(UserUpdate userUpdate, Long id) {
        UserProfile userProfile = null;
        User user = null;
        try {
            user = userRepository.findById(id).orElse(null);
            if (user != null) {
                user = User.builder().id(user.getId())
                        .email(userUpdate.getEmail())
                        .username(userUpdate.getUsername())
                        .password(user.getPassword())
                        .createdAt(user.getCreatedAt())
                        .updatedAt(LocalDateTime.now())
                        .themes(user.getThemes())
                        .build();
                user = userRepository.save(user);
                userProfile = IUserToUserProfileMapper.INSTANCE.userToUserProfileForThemes(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userProfile;
    }

    /**
     * User id get user profile.
     *
     * @param id the id
     * @return the user profile
     */
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
