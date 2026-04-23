package com.escuela.planeacion.backend.controller;

import com.escuela.planeacion.backend.entity.ProfileRole;
import com.escuela.planeacion.backend.service.ProfileRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profile-role")
@CrossOrigin(origins = "*")
public class ProfileRoleController {

    @Autowired
    private ProfileRoleService service;

    @GetMapping
    public ResponseEntity<List<ProfileRole>> getTodosProfileRoles() {
        return ResponseEntity.ok(service.getTodosProfileRoles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileRole> getProfileRolePorId(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getProfileRolePorId(id));
    }

    @GetMapping("/profile/{profileId}")
    public ResponseEntity<List<ProfileRole>> getProfileRolesPorProfileId(@PathVariable Integer profileId) {
        return ResponseEntity.ok(service.getProfileRolesPorProfileId(profileId));
    }

    @GetMapping("/role/{roleId}")
    public ResponseEntity<List<ProfileRole>> getProfileRolesPorRoleId(@PathVariable Integer roleId) {
        return ResponseEntity.ok(service.getProfileRolesPorRoleId(roleId));
    }

    @PostMapping
    public ResponseEntity<ProfileRole> crearProfileRole(@RequestBody ProfileRole profileRole) {
        return ResponseEntity.ok(service.crearProfileRole(profileRole));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfileRole> actualizarProfileRole(@PathVariable Integer id, @RequestBody ProfileRole profileRoleActualizado) {
        return ResponseEntity.ok(service.actualizarProfileRole(id, profileRoleActualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProfileRole(@PathVariable Integer id) {
        service.eliminarProfileRole(id);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/profile/{profileId}")
    public ResponseEntity<Void> eliminarRolesPorProfileId(@PathVariable Integer profileId) {
        service.eliminarRolesPorProfileId(profileId);
        return ResponseEntity.noContent().build();
    }
}
