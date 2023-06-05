package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.api.UsersApi;
import com.openclassrooms.mddapi.models.UserProfile;
import com.openclassrooms.mddapi.models.UserUpdate;
import com.openclassrooms.mddapi.services.UserServices;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController implements UsersApi {

    UserServices userServices;

    UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping(
            value = "/users",
            produces = { "application/json" }
    )
    public ResponseEntity<List<UserProfile>> usersGet() {
        return ResponseEntity.ok(userServices.usersGet());
    }

    @PutMapping(
            value = "/users",
            produces = { "application/json" },
            consumes = { "application/json" }
    )
    public ResponseEntity<UserProfile> usersPut(
            @Parameter(name = "UserUpdate", description = "", required = true) @Valid @RequestBody UserUpdate userUpdate
    ) {
        return ResponseEntity.ok(userServices.usersPut(userUpdate));
    }

}
