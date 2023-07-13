package com.openclassrooms.mddapi.repositories;

import com.openclassrooms.mddapi.models.ThemeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * The interface Theme repository.
 */
public interface IThemeRepository extends JpaRepository<ThemeEntity, Long> {

    /**
     * Find by title optional.
     *
     * @param theme the theme
     * @return the optional
     */
    Optional<ThemeEntity> findByTitle(String theme);
}
