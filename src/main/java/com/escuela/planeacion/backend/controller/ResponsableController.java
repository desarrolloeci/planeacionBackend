package com.escuela.planeacion.backend.controller;

import com.escuela.planeacion.backend.entity.Responsable;
import com.escuela.planeacion.backend.service.ResponsableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/responsables")
@CrossOrigin(origins = "*")
public class ResponsableController {

    @Autowired
    private ResponsableService responsableService;

    @GetMapping
    public ResponseEntity<List<Responsable>> getTodosResponsables() {
        List<Responsable> responsables = responsableService.getTodosResponsables();
        return ResponseEntity.ok(responsables);
    }

    @GetMapping("/{codEmp}")
    public ResponseEntity<Responsable> getResponsablePorId(@PathVariable String codEmp) {
        Responsable responsable = responsableService.getResponsablePorId(codEmp);
        return ResponseEntity.ok(responsable);
    }
}
