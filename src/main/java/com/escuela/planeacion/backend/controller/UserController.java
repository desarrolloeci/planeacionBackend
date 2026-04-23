package com.escuela.planeacion.backend.controller;

import com.escuela.planeacion.backend.entity.User;
import com.escuela.planeacion.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<User>> getTodosUsers() {
        return ResponseEntity.ok(service.getTodosUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getUserPorId(id));
    }

    @GetMapping("/mail/{mail}")
    public ResponseEntity<User> getUserPorMail(@PathVariable String mail) {
        return ResponseEntity.ok(service.getUserPorMail(mail));
    }

    @PostMapping
    public ResponseEntity<User> crearUser(@RequestBody User user) {
        return ResponseEntity.ok(service.crearUser(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> actualizarUser(@PathVariable Integer id, @RequestBody User userActualizado) {
        return ResponseEntity.ok(service.actualizarUser(id, userActualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUser(@PathVariable Integer id) {
        service.eliminarUser(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/{id}/roles")
    public ResponseEntity<List<String>> getRolesPorUsuario(@PathVariable Integer id) {
        List<String> roles = service.getRolesPorUsuario(id);
        return ResponseEntity.ok(roles);
    }
}
