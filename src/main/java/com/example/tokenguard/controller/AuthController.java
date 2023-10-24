package com.example.tokenguard.controller;

import com.example.tokenguard.domain.User;
import com.example.tokenguard.dto.LoginRequest;
import com.example.tokenguard.service.UserService;
import com.example.tokenguard.utils.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(){
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/users/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username){
        User user = userService.getUserByUsername(username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/users/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email){
        User user = userService.getUserByEmail(email);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/users/{id_user}")
    public ResponseEntity<User> getUserById(@PathVariable int id_user){
        User user = userService.getUserById(id_user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @PostMapping("/sign-in")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        User user = userService.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());

        if (user == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return null;
        }

        return new ResponseEntity<>(jwtUtil.generateToken(user.getUsername()), HttpStatus.OK);
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

    @DeleteMapping("/deleteUser")
    public ResponseEntity<Void> deleteUser(@PathVariable int id_user) {
        userService.deleteUserById(id_user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
