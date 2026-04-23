package com.escuela.planeacion.backend.controller;

import com.escuela.planeacion.backend.entity.ErogacionPL;
import com.escuela.planeacion.backend.service.ErogacionPLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/erogacion-pl")
@CrossOrigin(origins = "*")
public class ErogacionPLController {

    @Autowired
    private ErogacionPLService service;

    @GetMapping
    public ResponseEntity<List<ErogacionPL>> getTodasErogaciones() {
        return ResponseEntity.ok(service.getTodasErogaciones());
    }

    @GetMapping("/{iderogacionpl}")
    public ResponseEntity<ErogacionPL> getErogacionPorId(@PathVariable Integer iderogacionpl) {
        return ResponseEntity.ok(service.getErogacionPorId(iderogacionpl));
    }

    @GetMapping("/proyecto/{idproyecto}")
    public ResponseEntity<List<ErogacionPL>> getErogacionesPorIdproyecto(@PathVariable Integer idproyecto) {
        return ResponseEntity.ok(service.getErogacionesPorIdproyecto(idproyecto));
    }

    @GetMapping("/actividad/{idactividad}")
    public ResponseEntity<List<ErogacionPL>> getErogacionesPorIdactividad(@PathVariable Integer idactividad) {
        return ResponseEntity.ok(service.getErogacionesPorIdactividad(idactividad));
    }

    @PostMapping
    public ResponseEntity<ErogacionPL> crearErogacion(@RequestBody ErogacionPL erogacion) {
        return ResponseEntity.ok(service.crearErogacion(erogacion));
    }

    @PutMapping("/{iderogacionpl}")
    public ResponseEntity<ErogacionPL> actualizarErogacion(@PathVariable Integer iderogacionpl,
                                                           @RequestBody ErogacionPL erogacionActualizado) {
        return ResponseEntity.ok(service.actualizarErogacion(iderogacionpl, erogacionActualizado));
    }

    @DeleteMapping("/{iderogacionpl}")
    public ResponseEntity<Void> eliminarErogacion(@PathVariable Integer iderogacionpl) {
        service.eliminarErogacion(iderogacionpl);
        return ResponseEntity.noContent().build();
    }
}
