package com.openclassrooms.mddapi.repositories;

import com.openclassrooms.mddapi.models.ThemeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IThemeRepository extends JpaRepository<ThemeEntity, Long> {

}
