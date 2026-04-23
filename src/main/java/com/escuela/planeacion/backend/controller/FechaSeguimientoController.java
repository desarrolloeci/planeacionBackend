package com.escuela.planeacion.backend.controller;

import com.escuela.planeacion.backend.entity.FechaSeguimiento;
import com.escuela.planeacion.backend.service.FechaSeguimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/fechaseguimiento")
public class FechaSeguimientoController {

    @Autowired
    private FechaSeguimientoService service;

    @GetMapping
    public ResponseEntity<List<FechaSeguimiento>> getTodasFechas() {
        return ResponseEntity.ok(service.getTodasFechas());
    }

    @PostMapping
    public ResponseEntity<FechaSeguimiento> crearFecha(@RequestBody FechaSeguimiento fecha) {
        return ResponseEntity.ok(service.crearFecha(fecha));
    }

    @PutMapping
    public ResponseEntity<FechaSeguimiento> actualizarFecha(@RequestBody FechaSeguimiento fechaActualizado) {
        return ResponseEntity.ok(service.crearFecha(fechaActualizado));
    }
    
    @PutMapping("/cambiar-flag/{feciniseg}")
    public ResponseEntity<String> cambiarFlag(
            @PathVariable String feciniseg,
            @RequestParam Integer nuevoFlag) {

        boolean exito = service.cambiarFlag(feciniseg, nuevoFlag);
        if (exito) return ResponseEntity.ok("Flag actualizado");
        else return ResponseEntity.status(404).body("Fecha no encontrada");
    }
}
