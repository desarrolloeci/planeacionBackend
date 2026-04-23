package com.escuela.planeacion.backend.controller;

import org.springframework.web.bind.annotation.*;

import com.escuela.planeacion.backend.entity.Snies;
import com.escuela.planeacion.backend.service.SniesService;

import java.util.List;

@RestController
@RequestMapping("/api/snies")
@CrossOrigin(origins = "*")
public class SniesController {

    private final SniesService service;

    public SniesController(SniesService service) {
        this.service = service;
    }

    @GetMapping
    public List<Snies> getAll() {
        return service.getAll();
    }
}