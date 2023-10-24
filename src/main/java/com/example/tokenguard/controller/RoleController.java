package com.example.tokenguard.controller;

import com.example.tokenguard.domain.Role;
import com.example.tokenguard.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;


    @PostMapping("/add-role")
    public ResponseEntity<Role> addRole(@RequestBody Role role){
        try {
            roleService.saveRole(role);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(role, HttpStatus.CREATED);
    }

    @GetMapping("/get-roles")
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = roleService.getAllRoles();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @GetMapping("/roles/id/{id_role}")
    public ResponseEntity<Role> getRoleById(@PathVariable Long id_role) {
        Role role = roleService.getRoleById(id_role);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @GetMapping("/roles/name/{role_name}")
    public ResponseEntity<Role> getRoleById(@PathVariable String role_name) {
        Role role = roleService.getRoleByName(role_name);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @DeleteMapping("/roles/{id_role}")
    public ResponseEntity<Void> deleteRoleById(@PathVariable Long id_role) {
        roleService.deleteRoleById(id_role);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/roles/{role_name}")
    public ResponseEntity<Void> deleteRoleByName(@PathVariable String role_name) {
        roleService.deleteRoleByName(role_name);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
