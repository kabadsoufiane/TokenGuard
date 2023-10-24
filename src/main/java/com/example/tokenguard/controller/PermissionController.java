package com.example.tokenguard.controller;

import com.example.tokenguard.domain.Permission;
import com.example.tokenguard.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permissions")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @PostMapping("/add-permission")
    public ResponseEntity<Permission> addPermission(@RequestBody Permission permission) {
        try {
            permissionService.savePermission(permission);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(permission, HttpStatus.CREATED);
    }

    @GetMapping("/get-permissions")
    public ResponseEntity<List<Permission>> getAllPermission() {
        List<Permission> permissions = permissionService.getAllPermissions();
        return new ResponseEntity<>(permissions, HttpStatus.OK);
    }

    @GetMapping("/get-permissions/id/{id_permission}")
    public ResponseEntity<Permission> getPermissionById(@PathVariable Long id_permission) {
        Permission permission = permissionService.getPermissionById(id_permission);
        return new ResponseEntity<>(permission, HttpStatus.OK);
    }

    @GetMapping("/get-permissions/name/{permission_name}")
    public ResponseEntity<Permission> getPermissionByName(@PathVariable String permission_name) {
        Permission permission = permissionService.getPermissionByName(permission_name);
        return new ResponseEntity<>(permission, HttpStatus.OK);
    }

    @DeleteMapping("/delete-permission/{id_permission}")
    public ResponseEntity<Void> deletePermissionById(@PathVariable Long id_permission) {
        permissionService.deletePermissionById(id_permission);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete-permission/{permission_name}")
    public ResponseEntity<Void> deletePermissionByName(@PathVariable String permission_name) {
        permissionService.deletePermissionByname(permission_name);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
