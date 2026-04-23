package com.escuela.planeacion.backend.controller;

import org.springframework.web.bind.annotation.*;

import com.escuela.planeacion.backend.dto.CentroCostoDTO;
import com.escuela.planeacion.backend.service.CentroCostoService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/centros-costo")
public class CentroCostoController {

    private final CentroCostoService service;

    public CentroCostoController(CentroCostoService service) {
        this.service = service;
    }

    @GetMapping
    public List<CentroCostoDTO> getCentroCostos() {
        return service.getCentroCostosActuales();
    }
}

