package org.example.capitbackend.model;

import java.util.UUID;

public class SignupResponse {

    private   UUID userId;
    private   String email;
    private   String token;

    public SignupResponse(){}
    public SignupResponse(UUID userId, String email, String token)
    {
        this.userId = userId;
        this.email = email;
        this.token = token;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
