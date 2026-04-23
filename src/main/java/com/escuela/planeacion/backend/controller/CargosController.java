package com.escuela.planeacion.backend.controller;

import com.escuela.planeacion.backend.dto.CargosDTO;
import com.escuela.planeacion.backend.service.CargosService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cargos")
@CrossOrigin(origins = "*")
public class CargosController {

    private final CargosService service;

    public CargosController(CargosService service) {
        this.service = service;
    }

    @GetMapping
    public List<CargosDTO> getCargos() {
        return service.getCargos();
    }
}
