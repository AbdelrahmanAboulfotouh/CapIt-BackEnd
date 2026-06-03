package org.example.capitbackend.controllers;

import jakarta.validation.Valid;
import org.example.capitbackend.model.SignupRequest;
import org.example.capitbackend.model.User;
import org.example.capitbackend.services.SignupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class UserController
{
    private final SignupService signupService;

    public UserController(SignupService signupService) {
        this.signupService = signupService;
    }

    @PostMapping("/sign-up")
    public void signupNewUser(@RequestBody SignupRequest signupRequest)
    {
        signupService.signup(signupRequest);

    }


}
