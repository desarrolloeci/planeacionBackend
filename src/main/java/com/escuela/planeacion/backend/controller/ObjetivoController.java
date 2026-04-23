package com.escuela.planeacion.backend.controller;

import com.escuela.planeacion.backend.entity.Objetivo;
import com.escuela.planeacion.backend.service.ObjetivoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/objetivos")
@CrossOrigin(origins = "*")
@Tag(name = "Objetivos", description = "Gestión de Objetivos")
public class ObjetivoController {

    private final ObjetivoService objetivoService;

    public ObjetivoController(ObjetivoService objetivoService) {
        this.objetivoService = objetivoService;
    }

    @GetMapping
    @Operation(summary = "Listar todos los objetivos")
    public List<Objetivo> obtenerObjetivos() {
        return objetivoService.listarTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un objetivo por ID")
    public ResponseEntity<Objetivo> obtenerObjetivoPorId(@PathVariable Integer id) {
        return objetivoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/proyecto/{idproyecto}")
    @Operation(summary = "Listar objetivos por ID de proyecto")
    public List<Objetivo> listarPorProyecto(@PathVariable Integer idproyecto) {
        return objetivoService.listarPorProyecto(idproyecto);
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo objetivo")
    public Objetivo crearObjetivo(@RequestBody Objetivo objetivo) {
        return objetivoService.crear(objetivo);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un objetivo existente")
    public ResponseEntity<Objetivo> actualizarObjetivo(@PathVariable Integer id, @RequestBody Objetivo objetivo) {
        try {
            return ResponseEntity.ok(objetivoService.actualizar(id, objetivo));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un objetivo")
    public ResponseEntity<Void> eliminarObjetivo(@PathVariable Integer id) {
        objetivoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
