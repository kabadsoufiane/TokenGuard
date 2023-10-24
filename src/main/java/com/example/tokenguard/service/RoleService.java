package com.example.tokenguard.service;

import com.example.tokenguard.domain.Role;
import com.example.tokenguard.repository.RoleRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    public List<Role> getAllRoles() {
        return (List<Role>) roleRepository.findAll();
    }

    public Role getRoleById(@NonNull Long id_role) {
        return roleRepository.findById(id_role).orElse(null);
    }

    public Role getRoleByName(@NonNull String name) {
        return roleRepository.findByName(name).orElse(null);
    }

    public void deleteRoleById(@NonNull Long id_role) {
        roleRepository.deleteById(id_role);
    }

    public void deleteRoleByName(@NonNull String name) {
        roleRepository.deleteByName(name);
    }

}
