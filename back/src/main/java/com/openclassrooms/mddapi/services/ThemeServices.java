package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.mappers.IThemeEntityToThemeMapper;
import com.openclassrooms.mddapi.models.*;
import com.openclassrooms.mddapi.repositories.ISubscriptionRepository;
import com.openclassrooms.mddapi.repositories.IThemeRepository;
import com.openclassrooms.mddapi.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ThemeServices {
    private final IThemeRepository themeRepository;
    private final UserRepository userRepository;
    private final ISubscriptionRepository subscriptionRepository;

    public ThemeServices(IThemeRepository themeRepository,
                         UserRepository userRepository,
                         ISubscriptionRepository subscriptionRepository) {
        this.themeRepository = themeRepository;
        this.userRepository = userRepository;
        this.subscriptionRepository = subscriptionRepository;
    }

    @Transactional
    public List<Theme> getThemeList() {
        List<Theme> themesDTO = null;
        List<ThemeEntity> themesEntity = null;
        try {
            themesEntity = themeRepository.findAll();
            themesDTO = themesEntity.stream().map(IThemeEntityToThemeMapper.INSTANCE::themeEntityToTheme).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return themesDTO;
    }


    @Transactional
    public void subscribeToTheme(Long themeId, Long userId) {
        User user = null;
        try {
            ThemeEntity themeEntity = themeRepository.findById(themeId).orElse(null);
            if (themeEntity != null) {
                user = userRepository.findById(userId).orElse(null);
                if (user != null) {
                    Subscription subscription = new Subscription();
                    subscription.setTheme(themeEntity);
                    subscription.setUser(user);
                    SubscriptionPrimaryKey subscriptionPrimaryKey = new SubscriptionPrimaryKey();
                    subscriptionPrimaryKey.setThemeId(themeId);
                    subscriptionPrimaryKey.setUserId(userId);
                    subscription.setId(subscriptionPrimaryKey);
                    user.getThemes().add(subscription);
                    userRepository.save(user);
                    themeEntity.getUserList().add(subscription);
                    themeRepository.save(themeEntity);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
