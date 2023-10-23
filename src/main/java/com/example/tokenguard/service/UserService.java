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

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public User findByEmailAndPassword(String email, String password) {
        User user = userRepository.findByEmail(email);
        /*System.out.println(user.getPassword());
        System.out.println(bCryptPasswordEncoder.encode(password));*/
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

    public User getUserById(@NonNull long id_user) {
        return userRepository.findById(id_user).orElse(null);
    }

    public void deleteUserById(@NonNull long id_user) {
        userRepository.deleteById(id_user);
    }
}
