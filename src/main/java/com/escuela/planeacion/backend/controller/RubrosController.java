package com.escuela.planeacion.backend.controller;

import org.springframework.web.bind.annotation.*;

import com.escuela.planeacion.backend.entity.Rubros;
import com.escuela.planeacion.backend.service.RubrosService;

import java.util.List;

@RestController
@RequestMapping("/api/rubros")
@CrossOrigin(origins = "*")
public class RubrosController {

    private final RubrosService service;

    public RubrosController(RubrosService service) {
        this.service = service;
    }

    @GetMapping
    public List<Rubros> getRubros() {
        return service.getRubros();
    }
    
    @GetMapping("/cl1/{codigo}")
    public List<Rubros> getByCodCl1(@PathVariable("codigo") String codigo) {
        return service.getRubrosByCodCl1(codigo);
    }
}