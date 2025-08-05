package com.vc.login.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vc.login.dto.UserInfoResponse;
import com.vc.login.service.AuthService;

record SignupRequest(String username, String password, String role) {}
record SigninRequest(String username, String password) {}
record JwtResponse(String token) {}
record Validate(String validateToken) {}

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest req) {
        authService.signup(req.username(), req.password(), req.role() == null ? "USER" : req.role());
        return ResponseEntity.ok("User registered");
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody SigninRequest req) {
        String jwt = authService.signin(req.username(), req.password());
        return ResponseEntity.ok(new JwtResponse(jwt));
    }
    
    @PostMapping("/validate")
    public ResponseEntity<?> validateToken(@RequestBody Validate authHeader) {
        if (authHeader == null || !authHeader.validateToken().startsWith("Bearer ")) {
            return ResponseEntity.badRequest().body("Missing or invalid Authorization header");
        }

        String token = authHeader.validateToken().substring(7); // Remove "Bearer "

        if (!authService.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }

        // Extract username and role
        String username = authService.getUsername(token);
        String role = authService.getRole(token);

        return ResponseEntity.ok(new UserInfoResponse(username, role));
    }
}