package com.escuela.planeacion.backend.controller;

import com.escuela.planeacion.backend.entity.ErogacionOF;
import com.escuela.planeacion.backend.service.ErogacionOFService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/erogacion-of")
@CrossOrigin(origins = "*")
public class ErogacionOFController {

    @Autowired
    private ErogacionOFService service;

    @GetMapping
    public ResponseEntity<List<ErogacionOF>> getTodasErogaciones() {
        return ResponseEntity.ok(service.getTodasErogaciones());
    }

    @GetMapping("/{iderogacionof}")
    public ResponseEntity<ErogacionOF> getErogacionPorId(@PathVariable Integer iderogacionof) {
        return ResponseEntity.ok(service.getErogacionPorId(iderogacionof));
    }

    @GetMapping("/proyecto/{idproyecto}")
    public ResponseEntity<List<ErogacionOF>> getErogacionesPorIdproyecto(@PathVariable Integer idproyecto) {
        return ResponseEntity.ok(service.getErogacionesPorIdproyecto(idproyecto));
    }

    @PostMapping
    public ResponseEntity<ErogacionOF> crearErogacion(@RequestBody ErogacionOF erogacion) {
        return ResponseEntity.ok(service.crearErogacion(erogacion));
    }

    @PutMapping("/{iderogacionof}")
    public ResponseEntity<ErogacionOF> actualizarErogacion(@PathVariable Integer iderogacionof,
                                                           @RequestBody ErogacionOF erogacionActualizado) {
        return ResponseEntity.ok(service.actualizarErogacion(iderogacionof, erogacionActualizado));
    }

    @DeleteMapping("/{iderogacionof}")
    public ResponseEntity<Void> eliminarErogacion(@PathVariable Integer iderogacionof) {
        service.eliminarErogacion(iderogacionof);
        return ResponseEntity.noContent().build();
    }
}
