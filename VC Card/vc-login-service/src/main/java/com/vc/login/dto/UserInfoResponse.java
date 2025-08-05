package com.vc.login.dto;

public class UserInfoResponse {
    private String username;
    private String role;

    public UserInfoResponse(String username, String role) {
        this.username = username;
        this.role = role;
    }

    // Getters
    public String getUsername() { return username; }
    public String getRole() { return role; }
}
