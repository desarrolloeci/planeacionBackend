package com.escuela.planeacion.backend.controller;

import com.escuela.planeacion.backend.entity.Actividad;
import com.escuela.planeacion.backend.service.ActividadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/actividades")
@CrossOrigin(origins = "*")
public class ActividadController {

    private final ActividadService actividadService;

    public ActividadController(ActividadService actividadService) {
        this.actividadService = actividadService;
    }

    @GetMapping
    public List<Actividad> getAll() {
        return actividadService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Actividad> getById(@PathVariable Integer id) {
        return actividadService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Actividad create(@RequestBody Actividad actividad) {
        return actividadService.save(actividad);
    }

    @PutMapping("/{id}")
    public Actividad update(@PathVariable Integer id, @RequestBody Actividad actividad) {
        return actividadService.update(id, actividad);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        actividadService.delete(id);
    }
    
    @GetMapping("/proyecto/{idproyecto}")
    public List<Actividad> getByProyecto(@PathVariable Integer idproyecto) {
        return actividadService.getByProyectoId(idproyecto);
    }
}
