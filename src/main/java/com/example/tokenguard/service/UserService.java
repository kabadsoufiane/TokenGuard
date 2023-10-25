package com.example.tokenguard.service;

import com.example.tokenguard.domain.User;
import com.example.tokenguard.repository.UserRepository;
import jakarta.annotation.Nonnull;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    // test

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public User findByEmailAndPassword(String email, String password) {
        User user = userRepository.findByEmail(email);

        if (user != null &&  bCryptPasswordEncoder.matches(password, user.getPassword())) {
            return user;
        }
        return null;
    }
    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    public User getUserByEmail(@NonNull String email) {
        return userRepository.findByEmail(email);
    }

    public User getUserByUsername(@NonNull String userName) {
        return userRepository.findByUsername(userName).orElse(null);
    }
    public User getUserById(@NonNull long id_user) {
        return userRepository.findById(id_user).orElse(null);
    }

    public void deleteUserById(@NonNull long id_user) {
        userRepository.deleteById(id_user);
    }

    public void deleteUserByUsername(@NonNull String username) {
        userRepository.deleteByUsername(username);
    }

    public void deleteUserByEmail(@NonNull String email) {
        userRepository.deleteByEmail(email);
    }
}
