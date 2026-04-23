package com.escuela.planeacion.backend.controller;

import com.escuela.planeacion.backend.entity.Role;
import com.escuela.planeacion.backend.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/role")
@CrossOrigin(origins = "*")
public class RoleController {

    @Autowired
    private RoleService service;

    @GetMapping
    public ResponseEntity<List<Role>> getTodosRoles() {
        return ResponseEntity.ok(service.getTodosRoles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> getRolePorId(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getRolePorId(id));
    }

    @PostMapping
    public ResponseEntity<Role> crearRole(@RequestBody Role role) {
        return ResponseEntity.ok(service.crearRole(role));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Role> actualizarRole(@PathVariable Integer id, @RequestBody Role roleActualizado) {
        return ResponseEntity.ok(service.actualizarRole(id, roleActualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRole(@PathVariable Integer id) {
        service.eliminarRole(id);
        return ResponseEntity.noContent().build();
    }
}
