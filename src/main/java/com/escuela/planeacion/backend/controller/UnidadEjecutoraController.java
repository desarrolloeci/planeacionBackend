package com.escuela.planeacion.backend.controller;

import com.escuela.planeacion.backend.entity.UnidadEjecutora;
import com.escuela.planeacion.backend.service.UnidadEjecutoraService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/unidadejecutora")
@CrossOrigin(origins = "*")
@Tag(name = "Unidades Ejecutoras", description = "Gestión de Unidades Ejecutoras")
public class UnidadEjecutoraController {

    private final UnidadEjecutoraService unidadEjecutoraService;

    public UnidadEjecutoraController(UnidadEjecutoraService unidadEjecutoraService) {
        this.unidadEjecutoraService = unidadEjecutoraService;
    }

    @GetMapping
    @Operation(summary = "Listar todas las unidades ejecutoras")
    public List<UnidadEjecutora> obtenerUnidades() {
        return unidadEjecutoraService.listarTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una unidad ejecutora por ID")
    public ResponseEntity<UnidadEjecutora> obtenerUnidadPorId(@PathVariable Integer id) {
        return unidadEjecutoraService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crear una nueva unidad ejecutora")
    public UnidadEjecutora crearUnidad(@RequestBody UnidadEjecutora unidadEjecutora) {
        return unidadEjecutoraService.crear(unidadEjecutora);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una unidad ejecutora existente")
    public ResponseEntity<UnidadEjecutora> actualizarUnidad(@PathVariable Integer id, @RequestBody UnidadEjecutora unidadEjecutora) {
        try {
            return ResponseEntity.ok(unidadEjecutoraService.actualizar(id, unidadEjecutora));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una unidad ejecutora")
    public ResponseEntity<Void> eliminarUnidad(@PathVariable Integer id) {
        unidadEjecutoraService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
