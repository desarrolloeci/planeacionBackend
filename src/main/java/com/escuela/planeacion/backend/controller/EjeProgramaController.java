package com.escuela.planeacion.backend.controller;

import com.escuela.planeacion.backend.entity.EjePrograma;
import com.escuela.planeacion.backend.service.EjeProgramaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ejeprograma")
@CrossOrigin(origins = "*")
@Tag(name = "Ejes Programa", description = "Gestión de Ejes Programa")
public class EjeProgramaController {

    private final EjeProgramaService ejeProgramaService;

    public EjeProgramaController(EjeProgramaService ejeProgramaService) {
        this.ejeProgramaService = ejeProgramaService;
    }

    @GetMapping
    @Operation(summary = "Listar todos los ejes programa")
    public List<EjePrograma> obtenerEjes() {
        return ejeProgramaService.listarTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un eje programa por ID")
    public ResponseEntity<EjePrograma> obtenerEjePorId(@PathVariable Integer id) {
        return ejeProgramaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo eje programa")
    public EjePrograma crearEje(@RequestBody EjePrograma ejePrograma) {
        return ejeProgramaService.crear(ejePrograma);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un eje programa existente")
    public ResponseEntity<EjePrograma> actualizarEje(@PathVariable Integer id, @RequestBody EjePrograma ejePrograma) {
        try {
            return ResponseEntity.ok(ejeProgramaService.actualizar(id, ejePrograma));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un eje programa")
    public ResponseEntity<Void> eliminarEje(@PathVariable Integer id) {
        ejeProgramaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
