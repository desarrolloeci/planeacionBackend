package com.escuela.planeacion.backend.controller;

import com.escuela.planeacion.backend.entity.SegErogacion;
import com.escuela.planeacion.backend.service.SegErogacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seg-erogacion")
@CrossOrigin(origins = "*")
public class SegErogacionController {

    @Autowired
    private SegErogacionService service;

    @GetMapping
    public ResponseEntity<List<SegErogacion>> getTodasErogaciones() {
        return ResponseEntity.ok(service.getTodasErogaciones());
    }

    @GetMapping("/{iderogacionseg}")
    public ResponseEntity<SegErogacion> getErogacionPorId(@PathVariable Integer iderogacionseg) {
        return ResponseEntity.ok(service.getErogacionPorId(iderogacionseg));
    }

    @GetMapping("/seguimiento/{idseguimiento}")
    public ResponseEntity<List<SegErogacion>> getErogacionesPorIdseguimiento(@PathVariable Integer idseguimiento) {
        return ResponseEntity.ok(service.getErogacionesPorIdseguimiento(idseguimiento));
    }

    @GetMapping("/agno/{agno}")
    public ResponseEntity<List<SegErogacion>> getErogacionesPorAgno(@PathVariable Integer agno) {
        return ResponseEntity.ok(service.getErogacionesPorAgno(agno));
    }

    @PostMapping
    public ResponseEntity<SegErogacion> crearErogacion(@RequestBody SegErogacion erogacion) {
        return ResponseEntity.ok(service.crearErogacion(erogacion));
    }

    @PutMapping("/{iderogacionseg}")
    public ResponseEntity<SegErogacion> actualizarErogacion(@PathVariable Integer iderogacionseg,
                                                           @RequestBody SegErogacion erogacionActualizado) {
        return ResponseEntity.ok(service.actualizarErogacion(iderogacionseg, erogacionActualizado));
    }

    @DeleteMapping("/{iderogacionseg}")
    public ResponseEntity<Void> eliminarErogacion(@PathVariable Integer iderogacionseg) {
        service.eliminarErogacion(iderogacionseg);
        return ResponseEntity.noContent().build();
    }
}
