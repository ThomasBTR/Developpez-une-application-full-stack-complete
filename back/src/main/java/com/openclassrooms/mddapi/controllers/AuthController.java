package com.openclassrooms.mddapi.controllers;


import com.openclassrooms.mddapi.api.AuthApi;
import com.openclassrooms.mddapi.models.*;
import com.openclassrooms.mddapi.repositories.UserRepository;
import com.openclassrooms.mddapi.security.jwt.JwtUtils;
import com.openclassrooms.mddapi.security.services.UserDetailsImpl;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class AuthController implements AuthApi {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    AuthController(AuthenticationManager authenticationManager,
                   PasswordEncoder passwordEncoder,
                   JwtUtils jwtUtils,
                   UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }


    @Override
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/auth/login",
            produces = {"application/json"},
            consumes = {"application/json"}
    )

    public ResponseEntity<JwtResponse> loginUser(
            @Parameter(name = "LoginRequest", description = "", required = true) @Valid @RequestBody LoginRequest loginRequest
    ) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        User user = this.userRepository.findByEmail(userDetails.getUsername()).orElse(null);

        return ResponseEntity.ok(new JwtResponse()
                .jwt(jwt)
                .type("Bearer")
                .id(userDetails.getId())
                .email(userDetails.getUsername())
                .username(null != user ? user.getUsername() : userDetails.getUsername()));
    }

    @RequestMapping(
            method = RequestMethod.POST,
            value = "/auth/register",
            produces = {"application/json"},
            consumes = {"application/json"}
    )
    @Override
    public ResponseEntity<SignUpResponse> registerUser(
            @Parameter(name = "SignUpRequest", description = "", required = true) @Valid @RequestBody SignUpRequest signUpRequest
    ) {
        if (Boolean.TRUE.equals(userRepository.existsByEmail(signUpRequest.getEmail()))) {
            return ResponseEntity.badRequest().body(new SignUpResponse().message("Error: Email is already in use!"));
        }
        // Create new user's account
        User user = User.builder()
                .username(signUpRequest.getUsername())
                .email(signUpRequest.getEmail())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .build();

        userRepository.save(user);

        return ResponseEntity.ok(new SignUpResponse().message("User registered successfully!"));
    }
}

