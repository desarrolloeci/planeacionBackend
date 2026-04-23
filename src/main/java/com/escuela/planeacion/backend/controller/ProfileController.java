package com.escuela.planeacion.backend.controller;

import com.escuela.planeacion.backend.entity.Profile;
import com.escuela.planeacion.backend.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profile")
@CrossOrigin(origins = "*")
public class ProfileController {

    @Autowired
    private ProfileService service;

    @GetMapping
    public ResponseEntity<List<Profile>> getTodosProfiles() {
        return ResponseEntity.ok(service.getTodosProfiles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profile> getProfilePorId(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getProfilePorId(id));
    }

    @PostMapping
    public ResponseEntity<Profile> crearProfile(@RequestBody Profile profile) {
        return ResponseEntity.ok(service.crearProfile(profile));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Profile> actualizarProfile(@PathVariable Integer id, @RequestBody Profile profileActualizado) {
        return ResponseEntity.ok(service.actualizarProfile(id, profileActualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProfile(@PathVariable Integer id) {
        service.eliminarProfile(id);
        return ResponseEntity.noContent().build();
    }
}
