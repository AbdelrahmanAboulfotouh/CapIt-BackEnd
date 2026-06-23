package org.example.capitbackend.controllers;

import jakarta.validation.Valid;
import org.example.capitbackend.model.LoginRequest;
import org.example.capitbackend.model.SignupRequest;
import org.example.capitbackend.model.SignupResponse;
import org.example.capitbackend.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class UserController
{
    private final AuthService authService;

    public UserController(AuthService authService) {
        this.authService = authService;
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/sign-up")
    public SignupResponse signupNewUser(@Valid @RequestBody SignupRequest signupRequest)
    {
        return authService.signup(signupRequest);

    }

    @PostMapping("/sign-in")
    public SignupResponse login(@Valid @RequestBody LoginRequest loginRequest)
    {
        return authService.login(loginRequest);
    }



}
