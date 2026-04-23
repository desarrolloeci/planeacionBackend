package com.escuela.planeacion.backend.controller;

import com.escuela.planeacion.backend.entity.FinFactorCaractProyecto;
import com.escuela.planeacion.backend.service.FinFactorCaractProyectoService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/fin-factor")
public class FinFactorCaractProyectoController {

    private final FinFactorCaractProyectoService service;

    public FinFactorCaractProyectoController(FinFactorCaractProyectoService service) {
        this.service = service;
    }

    @GetMapping
    public List<FinFactorCaractProyecto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FinFactorCaractProyecto> getById(@PathVariable Integer id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody FinFactorCaractProyecto entity) {
        try {
            String json = new ObjectMapper().writeValueAsString(entity);
            System.out.println("JSON recibido: " + json);

            FinFactorCaractProyecto creado = service.insert(entity);
            return ResponseEntity.ok(creado);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al insertar registro: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public FinFactorCaractProyecto update(@PathVariable Integer id, @RequestBody FinFactorCaractProyecto entity) {
        return service.update(id, entity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

    // Método adicional
    @GetMapping("/proyecto/{idproyecto}")
    public List<FinFactorCaractProyecto> getByProyecto(@PathVariable Integer idproyecto) {
        return service.getByIdProyecto(idproyecto);
    }
}
