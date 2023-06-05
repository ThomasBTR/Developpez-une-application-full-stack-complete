package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.api.SubscriptionsApi;
import com.openclassrooms.mddapi.models.ThemeListResponse;
import com.openclassrooms.mddapi.services.ThemeServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ThemeController implements SubscriptionsApi {


    private final ThemeServices themeService;

    public ThemeController(ThemeServices themeService) {
        this.themeService = themeService;
    }

    @GetMapping(
            value = "/subscriptions",
            produces = {"application/json"}
    )
    public ResponseEntity<ThemeListResponse> subscriptionsGet() {
        return ResponseEntity.ok(themeService.getThemeList());
    }
}
