package com.escuela.planeacion.backend.controller;

import com.escuela.planeacion.backend.entity.RubroPlaneacion;
import com.escuela.planeacion.backend.service.RubroPlaneacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rubrosplaneacion")
@CrossOrigin(origins = "*")
@Tag(name = "Rubros de Planeación", description = "Gestión de Rubros de Planeación")
public class RubroPlaneacionController {

    private final RubroPlaneacionService rubroPlaneacionService;

    public RubroPlaneacionController(RubroPlaneacionService rubroPlaneacionService) {
        this.rubroPlaneacionService = rubroPlaneacionService;
    }

    @GetMapping
    @Operation(summary = "Listar todos los rubros de planeación")
    public List<RubroPlaneacion> obtenerRubros() {
        return rubroPlaneacionService.listarTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un rubro de planeación por ID")
    public ResponseEntity<RubroPlaneacion> obtenerRubroPorId(@PathVariable Integer id) {
        return rubroPlaneacionService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo rubro de planeación")
    public RubroPlaneacion crearRubro(@RequestBody RubroPlaneacion rubroPlaneacion) {
        return rubroPlaneacionService.crear(rubroPlaneacion);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un rubro de planeación existente")
    public ResponseEntity<RubroPlaneacion> actualizarRubro(@PathVariable Integer id, @RequestBody RubroPlaneacion rubroPlaneacion) {
        try {
            return ResponseEntity.ok(rubroPlaneacionService.actualizar(id, rubroPlaneacion));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un rubro de planeación")
    public ResponseEntity<Void> eliminarRubro(@PathVariable Integer id) {
        rubroPlaneacionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
