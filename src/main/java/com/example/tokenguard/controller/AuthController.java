package com.example.tokenguard.controller;

import com.example.tokenguard.domain.User;
import com.example.tokenguard.dto.LoginRequest;
import com.example.tokenguard.service.UserService;
import com.example.tokenguard.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;


    @PostMapping("/sign-in")
    public ResponseEntity<Map<String, String>> authenticateUser(@RequestBody LoginRequest loginRequest,
                                                                HttpServletResponse response) {
        User user = userService
                .findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());

        if (user == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return null;
        }

        Map<String, Object> claims = new HashMap<>();
        claims.put("email", user.getEmail());
        // add more claims as needed

        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("access_token", jwtUtil.generateToken(claims));
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<User> signUp(@RequestBody User user) {
        try {
            userService.saveUser(user);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/validate-token")
    public ResponseEntity<String> validateToken(@RequestHeader("Authorization") String token) {
        try {
            // Removing Bearer prefix if it exists
            if (token != null && token.startsWith("Bearer ")) {
                token = token.substring(7);
            }

            Claims claims = jwtUtil.validateToken(token);
            return ResponseEntity.ok("Token is valid. Subject: " + claims.getSubject());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
