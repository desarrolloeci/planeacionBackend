package com.escuela.planeacion.backend.controller;

import com.escuela.planeacion.backend.entity.SegActividad;
import com.escuela.planeacion.backend.service.SegActividadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seg-actividad")
@CrossOrigin(origins = "*")
public class SegActividadController {

    @Autowired
    private SegActividadService service;

    @GetMapping
    public ResponseEntity<List<SegActividad>> getTodasSegActividades() {
        return ResponseEntity.ok(service.getTodasSegActividades());
    }

    @GetMapping("/{idactividadseg}")
    public ResponseEntity<SegActividad> getSegActividadPorId(@PathVariable Integer idactividadseg) {
        return ResponseEntity.ok(service.getSegActividadPorId(idactividadseg));
    }

    @GetMapping("/seguimiento/{idseguimiento}")
    public ResponseEntity<List<SegActividad>> getSegActividadesPorIdseguimiento(@PathVariable Integer idseguimiento) {
        return ResponseEntity.ok(service.getSegActividadesPorIdseguimiento(idseguimiento));
    }

    @GetMapping("/actividad/{idactividad}")
    public ResponseEntity<List<SegActividad>> getSegActividadesPorIdactividad(@PathVariable Integer idactividad) {
        return ResponseEntity.ok(service.getSegActividadesPorIdactividad(idactividad));
    }

    @PostMapping
    public ResponseEntity<SegActividad> crearSegActividad(@RequestBody SegActividad segActividad) {
        return ResponseEntity.ok(service.crearSegActividad(segActividad));
    }

    @PutMapping("/{idactividadseg}")
    public ResponseEntity<SegActividad> actualizarSegActividad(@PathVariable Integer idactividadseg,
                                                               @RequestBody SegActividad segActividadActualizado) {
        return ResponseEntity.ok(service.actualizarSegActividad(idactividadseg, segActividadActualizado));
    }

    @DeleteMapping("/{idactividadseg}")
    public ResponseEntity<Void> eliminarSegActividad(@PathVariable Integer idactividadseg) {
        service.eliminarSegActividad(idactividadseg);
        return ResponseEntity.noContent().build();
    }
}
