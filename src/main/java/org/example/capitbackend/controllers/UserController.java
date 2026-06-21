package org.example.capitbackend.controllers;

import jakarta.validation.Valid;
import org.example.capitbackend.model.SignupRequest;
import org.example.capitbackend.model.SignupResponse;
import org.example.capitbackend.services.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class UserController
{
    private final AuthService authService;

    public UserController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/sign-up")
    public SignupResponse signupNewUser(@Valid @RequestBody SignupRequest signupRequest)
    {
        return authService.signup(signupRequest);

    }


}
