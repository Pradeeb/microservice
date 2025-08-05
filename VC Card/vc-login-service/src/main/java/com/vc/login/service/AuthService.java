package com.vc.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.vc.login.compoent.JwtUtils;
import com.vc.login.entity.UserLogin;
import com.vc.login.repository.UserLoginRepository;

@Service
public class AuthService {

    private final UserLoginRepository repo;
    private final JwtUtils jwtUtils;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public AuthService(UserLoginRepository repo, JwtUtils jwtUtils) {
        this.repo = repo;
        this.jwtUtils = jwtUtils;
    }

    public void signup(String username, String rawPassword, String role) {
        if (repo.existsByUsername(username)) {
            throw new RuntimeException("Username already exists");
        }
        UserLogin user = new UserLogin();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(rawPassword));
        user.setRole(role);
        user.setStatus("ACTIVE");
        repo.save(user);
    }

    public String signin(String username, String rawPassword) {
        UserLogin user = repo.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("Invalid credentials");
        }
        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        if (!"ACTIVE".equalsIgnoreCase(user.getStatus())) {
            throw new RuntimeException("User not active");
        }
        return jwtUtils.generateToken(username, user.getRole());
    }
    
    public boolean validateToken(String token) {
        return jwtUtils.validate(token);
    }

    public String getUsername(String token) {
        return jwtUtils.getUsername(token);
    }

    public String getRole(String token) {
        return jwtUtils.getRole(token);
    }
}