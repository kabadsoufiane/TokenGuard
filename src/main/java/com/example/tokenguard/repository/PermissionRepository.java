package com.example.tokenguard.repository;

import com.example.tokenguard.domain.Permission;
import com.example.tokenguard.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PermissionRepository extends CrudRepository<Permission, Long> {

    Permission save(Permission permission);
    Iterable<Permission> findAll();
    Optional<Permission> findById(Long id);
    void deleteById(Long id);

}
