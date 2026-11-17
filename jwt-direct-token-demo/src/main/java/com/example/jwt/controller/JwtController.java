package com.example.jwt.controller;

import com.example.jwt.model.User;
import com.example.jwt.service.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class JwtController {

    private final JwtService jwtService;

    public JwtController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/token")
    public Map<String, String> generateToken(@RequestBody User user) {
        if (user.getUsername() == null || user.getUsername().isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "username is required");
        }

        String token = jwtService.generateToken(
                user.getUsername(), user.getEmail());

        return Map.of(
                "token", token,
                "tokenType", "Bearer"
        );
    }
}
