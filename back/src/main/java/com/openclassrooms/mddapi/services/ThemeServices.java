package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.mappers.IThemeEntityToThemeMapper;
import com.openclassrooms.mddapi.models.Theme;
import com.openclassrooms.mddapi.models.ThemeEntity;
import com.openclassrooms.mddapi.models.ThemeList;
import com.openclassrooms.mddapi.repositories.IThemeRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ThemeServices {


    private final IThemeRepository themeRepository;

    public ThemeServices(IThemeRepository themeRepository) {
        this.themeRepository = themeRepository;
    }

    @Transactional
    public ThemeList getThemeList() {
        List<Theme> themesDTO = null;
        List<ThemeEntity> themesEntity = null;
        ThemeList themeList = new ThemeList();
        try {
            themesEntity = themeRepository.findAll();
            themesDTO = themesEntity.stream().map(IThemeEntityToThemeMapper.INSTANCE::themeEntityToTheme).collect(Collectors.toList());
            themeList.setThemes(themesDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return themeList;
    }


}
