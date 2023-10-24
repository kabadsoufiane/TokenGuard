package com.example.tokenguard.service;

import com.example.tokenguard.domain.Permission;
import com.example.tokenguard.repository.PermissionRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    public Permission savePermission(Permission permission) {
        return permissionRepository.save(permission);
    }

    public List<Permission> getAllPermissions() {
        return (List<Permission>) permissionRepository.findAll();
    }

    public Permission getPermissionById(@NonNull Long id_permission) {
        return permissionRepository.findById(id_permission).orElse(null);
    }

    public Permission getPermissionByName(@NonNull String permission_name) {
        return permissionRepository.findByName(permission_name).orElse(null);
    }

    public void deletePermissionById(@NonNull Long id_permission) {
        permissionRepository.deleteById(id_permission);
    }

    public void deletePermissionByname(@NonNull String permission_name) {
        permissionRepository.deleteByName(permission_name);
    }

}
