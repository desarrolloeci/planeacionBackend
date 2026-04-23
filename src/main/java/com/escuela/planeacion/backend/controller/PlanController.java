package com.escuela.planeacion.backend.controller;

import com.escuela.planeacion.backend.entity.Plan;
import com.escuela.planeacion.backend.service.PlanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/planes")
@CrossOrigin(origins = "*")
@Tag(name = "Planes", description = "Gestión de Planes")
public class PlanController {

    private final PlanService planService;

    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    @GetMapping
    @Operation(summary = "Listar todos los planes")
    public List<Plan> obtenerPlanes() {
        return planService.listarTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un plan por ID")
    public ResponseEntity<Plan> obtenerPlanPorId(@PathVariable Integer id) {
        return planService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo plan")
    public Plan crearPlan(@RequestBody Plan plan) {
        return planService.crear(plan);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un plan existente")
    public ResponseEntity<Plan> actualizarPlan(@PathVariable Integer id, @RequestBody Plan plan) {
        try {
            return ResponseEntity.ok(planService.actualizar(id, plan));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un plan")
    public ResponseEntity<Void> eliminarPlan(@PathVariable Integer id) {
        planService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
