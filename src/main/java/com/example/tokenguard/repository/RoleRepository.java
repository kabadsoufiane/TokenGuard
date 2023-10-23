package com.example.tokenguard.repository;

import com.example.tokenguard.domain.Role;
import com.example.tokenguard.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Long> {

    Role save(Role role);
    Iterable<Role> findAll();
    Optional<Role> findById(Long id);
    void deleteById(Long id);
}
