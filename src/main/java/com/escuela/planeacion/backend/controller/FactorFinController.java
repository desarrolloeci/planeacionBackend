package com.escuela.planeacion.backend.controller;

import com.escuela.planeacion.backend.entity.FactorFin;
import com.escuela.planeacion.backend.service.FactorFinService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/factoresfines")
@CrossOrigin(origins = "*")
@Tag(name = "Factores Fines", description = "Gestión de Factores Fines")
public class FactorFinController {

    private final FactorFinService factorFinService;

    public FactorFinController(FactorFinService factorFinService) {
        this.factorFinService = factorFinService;
    }

    @GetMapping
    @Operation(summary = "Listar todos los factores fines")
    public List<FactorFin> listarTodos() {
        return factorFinService.listarTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un factor fin por ID")
    public ResponseEntity<FactorFin> obtenerPorId(@PathVariable Integer id) {
        return factorFinService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo factor fin")
    public FactorFin crear(@RequestBody FactorFin factorFin) {
        return factorFinService.crear(factorFin);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un factor fin")
    public ResponseEntity<FactorFin> actualizar(@PathVariable Integer id, @RequestBody FactorFin factorFin) {
        try {
            return ResponseEntity.ok(factorFinService.actualizar(id, factorFin));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un factor fin")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        factorFinService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/filtrar")
    @Operation(summary = "Filtrar factores fines por cualquier campo")
    public List<FactorFin> filtrar(@RequestBody FactorFin filtro) {
        return factorFinService.filtrar(filtro);
    }
}
