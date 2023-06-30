package com.openclassrooms.mddapi.repositories;

import com.openclassrooms.mddapi.models.ThemeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IThemeRepository extends JpaRepository<ThemeEntity, Long> {

    Optional<ThemeEntity> findByTitle(String theme);
}
