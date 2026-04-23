package com.escuela.planeacion.backend.controller;

import com.escuela.planeacion.backend.entity.SegPersonal;
import com.escuela.planeacion.backend.service.SegPersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/segpersonal")
@CrossOrigin(origins = "*")
public class SegPersonalController {

    @Autowired
    private SegPersonalService service;

    @GetMapping
    public ResponseEntity<List<SegPersonal>> getTodosSegPersonal() {
        return ResponseEntity.ok(service.getTodosSegPersonal());
    }

    @GetMapping("/{idsegpersonal}")
    public ResponseEntity<SegPersonal> getSegPersonalPorId(@PathVariable Integer idsegpersonal) {
        return ResponseEntity.ok(service.getSegPersonalPorId(idsegpersonal));
    }

    @GetMapping("/seguimiento/{idseguimiento}")
    public ResponseEntity<List<SegPersonal>> getSegPersonalPorIdseguimiento(@PathVariable Integer idseguimiento) {
        return ResponseEntity.ok(service.getSegPersonalPorIdseguimiento(idseguimiento));
    }

    @GetMapping("/actividad/{idactividad}")
    public ResponseEntity<List<SegPersonal>> getSegPersonalPorIdactividad(@PathVariable Integer idactividad) {
        return ResponseEntity.ok(service.getSegPersonalPorIdactividad(idactividad));
    }

    @GetMapping("/personal/{idpersonal}")
    public ResponseEntity<List<SegPersonal>> getSegPersonalPorIdpersonal(@PathVariable Integer idpersonal) {
        return ResponseEntity.ok(service.getSegPersonalPorIdpersonal(idpersonal));
    }

    @PostMapping
    public ResponseEntity<SegPersonal> crearSegPersonal(@RequestBody SegPersonal segPersonal) {
        return ResponseEntity.ok(service.crearSegPersonal(segPersonal));
    }

    @PutMapping("/{idsegpersonal}")
    public ResponseEntity<SegPersonal> actualizarSegPersonal(@PathVariable Integer idsegpersonal,
                                                             @RequestBody SegPersonal segPersonalActualizado) {
        return ResponseEntity.ok(service.actualizarSegPersonal(idsegpersonal, segPersonalActualizado));
    }

    @DeleteMapping("/{idsegpersonal}")
    public ResponseEntity<Void> eliminarSegPersonal(@PathVariable Integer idsegpersonal) {
        service.eliminarSegPersonal(idsegpersonal);
        return ResponseEntity.noContent().build();
    }
}
