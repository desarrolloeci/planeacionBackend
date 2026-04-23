package com.escuela.planeacion.backend.controller;

import com.escuela.planeacion.backend.dto.ProyectoDetalleDTO;
import com.escuela.planeacion.backend.entity.Proyecto;
import com.escuela.planeacion.backend.service.ProyectoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proyectos")
@CrossOrigin(origins = "*")
@Tag(name = "Proyectos", description = "Gestión de Proyectos")
public class ProyectoController {

    private final ProyectoService proyectoService;

    public ProyectoController(ProyectoService proyectoService) {
        this.proyectoService = proyectoService;
    }

    @GetMapping
    @Operation(summary = "Listar todos los proyectos")
    public List<Proyecto> obtenerProyectos() {
        return proyectoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProyectoDetalleDTO> getProyectoDetalle(@PathVariable Integer id) {
        return proyectoService.getProyectoDetalle(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo proyecto")
    public Proyecto crearProyecto(@RequestBody Proyecto proyecto) {
        return proyectoService.crear(proyecto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un proyecto existente")
    public ResponseEntity<Proyecto> actualizarProyecto(
            @PathVariable Integer id,
            @RequestBody Proyecto proyecto) {
        try {
            return ResponseEntity.ok(proyectoService.actualizar(id, proyecto));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un proyecto")
    public ResponseEntity<Void> eliminarProyecto(@PathVariable Integer id) {
        proyectoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/estado-observacion")
    @Operation(summary = "Actualizar solo el estado y observación de un proyecto")
    public ResponseEntity<Proyecto> actualizarEstadoYObservacion(
            @PathVariable Integer idproyecto,
            @RequestParam Integer estadopr,
            @RequestParam String observacionpr) {
        try {
            return ResponseEntity.ok(proyectoService.actualizarEstadoYObservacion(idproyecto, estadopr, observacionpr));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/filtrar-estados")
    @Operation(summary = "Filtrar proyectos por un listado de estados")
    public List<Proyecto> filtrarPorEstados(@RequestBody List<Integer> estados) {
        return proyectoService.filtrarPorEstados(estados);
    }
}
