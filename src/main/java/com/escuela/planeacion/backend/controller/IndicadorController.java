package com.escuela.planeacion.backend.controller;

import com.escuela.planeacion.backend.entity.Indicador;
import com.escuela.planeacion.backend.service.IndicadorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/indicadores")
@CrossOrigin(origins = "*")
public class IndicadorController {

    private final IndicadorService service;

    public IndicadorController(IndicadorService service) {
        this.service = service;
    }

    @GetMapping
    public List<Indicador> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Indicador> getById(@PathVariable Integer id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Indicador create(@RequestBody Indicador indicador) {
        return service.save(indicador);
    }

    @PutMapping("/{id}")
    public Indicador update(@PathVariable Integer id, @RequestBody Indicador indicador) {
        return service.update(id, indicador);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

    // Métodos adicionales
    @GetMapping("/proyecto/{idproyecto}")
    public List<Indicador> getByProyecto(@PathVariable Integer idproyecto) {
        return service.getByIdProyecto(idproyecto);
    }

    @GetMapping("/actividad/{idactividad}")
    public List<Indicador> getByActividad(@PathVariable Integer idactividad) {
        return service.getByIdActividad(idactividad);
    }
}
