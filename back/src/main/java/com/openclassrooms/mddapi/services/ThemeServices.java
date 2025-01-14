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

/**
 * The type Theme services.
 */
@Service
public class ThemeServices {
    private final IThemeRepository themeRepository;
    private final UserRepository userRepository;
    private final ISubscriptionRepository subscriptionRepository;

    /**
     * Instantiates a new Theme services.
     *
     * @param themeRepository        the theme repository
     * @param userRepository         the user repository
     * @param subscriptionRepository the subscription repository
     */
    public ThemeServices(IThemeRepository themeRepository,
                         UserRepository userRepository,
                         ISubscriptionRepository subscriptionRepository) {
        this.themeRepository = themeRepository;
        this.userRepository = userRepository;
        this.subscriptionRepository = subscriptionRepository;
    }


    /**
     * Save theme theme entity.
     *
     * @param themeEntity the theme entity
     * @return the theme entity
     */
    @Transactional
    public ThemeEntity saveTheme(ThemeEntity themeEntity) {
        return themeRepository.save(themeEntity);
    }

    /**
     * Gets theme list.
     *
     * @return the theme list
     */
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


    /**
     * Subscribe to theme.
     *
     * @param themeId the theme id
     * @param userId  the user id
     */
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
                    saveTheme(themeEntity);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Unsubscribe from theme.
     *
     * @param themeId the theme id
     * @param userId  the user id
     */
    @Transactional
    public void unsubscribeFromTheme(Long themeId, Long userId) {
        User user;
        ThemeEntity themeEntity;
        try {
            themeEntity = themeRepository.findById(themeId).orElse(null);
            if (themeEntity != null) {
                themeEntity.getUserList().removeIf(subscription -> subscription.getId().getUserId().equals(userId));
                themeRepository.save(themeEntity);
            }
            user = userRepository.findById(userId).orElse(null);
            if (user != null) {
                user.getThemes().removeIf(subscription -> subscription.getId().getThemeId().equals(themeId));
                userRepository.save(user);
            }
            SubscriptionPrimaryKey subscriptionPrimaryKey = new SubscriptionPrimaryKey();
            subscriptionPrimaryKey.setThemeId(themeId);
            subscriptionPrimaryKey.setUserId(userId);
            subscriptionRepository.deleteById(subscriptionPrimaryKey);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Handle theme theme entity.
     *
     * @param articlePostRequest the article post request
     * @param articleEntity      the article entity
     * @return the theme entity
     */
    @Transactional
    public ThemeEntity handleTheme(ArticlePostRequest articlePostRequest, ArticleEntity articleEntity) {
        ThemeEntity themeEntity = null;
        if (articlePostRequest.getTheme() != null) {
            String themeTitle = articlePostRequest.getTheme();
            List<String> themeTitleList = themeRepository.findAll().stream().map(ThemeEntity::getTitle).collect(Collectors.toList());
            for (String theme : themeTitleList) {
                if (theme.equalsIgnoreCase(themeTitle)) {
                    themeEntity = themeRepository.findByTitle(theme).orElse(null);
                    articleEntity.setTheme(themeEntity);
                    break;
                }
            }
        }
        return themeEntity;
    }
}
