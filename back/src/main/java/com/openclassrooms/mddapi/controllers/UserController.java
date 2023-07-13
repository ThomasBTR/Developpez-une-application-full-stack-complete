package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.api.UserApi;
import com.openclassrooms.mddapi.api.UsersApi;
import com.openclassrooms.mddapi.models.UserProfile;
import com.openclassrooms.mddapi.models.UserUpdate;
import com.openclassrooms.mddapi.services.UserServices;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * The type User controller.
 */
@RestController
public class UserController implements UsersApi, UserApi {

    /**
     * The User services.
     */
    UserServices userServices;

    /**
     * Instantiates a new User controller.
     *
     * @param userServices the user services
     */
    UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return UsersApi.super.getRequest();
    }

    @GetMapping(
            value = "/users",
            produces = { "application/json" }
    )
    @Override
    public ResponseEntity<List<UserProfile>> usersGet() {
        return ResponseEntity.ok(userServices.usersGet());
    }

    @PutMapping(
            value = "/user/{id}",
            produces = { "application/json" },
            consumes = { "application/json" }
    )
    @Override
    public ResponseEntity<UserProfile> userIdPut(
            @Parameter(name = "id", description = "ID of the user to retrieve", required = true, in = ParameterIn.PATH) @PathVariable("id") Long id,
            @Parameter(name = "UserUpdate", description = "", required = true) @Valid @RequestBody UserUpdate userUpdate
    ) {
        return ResponseEntity.ok(userServices.usersPut(userUpdate, id));
    }

    @Override
    @GetMapping(
            value = "/user/{id}",
            produces = { "application/json" }
    )
    public ResponseEntity<UserProfile> userIdGet(
            @Parameter(name = "id", description = "ID of the user to retrieve", required = true, in = ParameterIn.PATH) @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok(userServices.userIdGet(id));
    }
}
