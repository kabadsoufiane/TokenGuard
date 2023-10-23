package com.example.tokenguard.repository;

import com.example.tokenguard.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    User save(User user);
    Iterable<User> findAll();
    Optional<User> findById(Long id);
    void deleteById(Long id);

    User findByEmail(String email);
}
