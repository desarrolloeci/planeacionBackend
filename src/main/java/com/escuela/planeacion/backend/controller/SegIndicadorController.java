package com.escuela.planeacion.backend.controller;

import com.escuela.planeacion.backend.entity.SegIndicador;
import com.escuela.planeacion.backend.service.SegIndicadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/segindicador")
public class SegIndicadorController {

    @Autowired
    private SegIndicadorService segIndicadorService;

    @GetMapping
    public List<SegIndicador> getTodos() {
        return segIndicadorService.getAll();
    }

    @GetMapping("/{id}")
    public SegIndicador getPorId(@PathVariable Integer id) {
        return segIndicadorService.getById(id);
    }

    @GetMapping("/seguimiento/{idseguimiento}")
    public List<SegIndicador> getPorIdSeguimiento(@PathVariable Integer idseguimiento) {
        return segIndicadorService.getByIdSeguimiento(idseguimiento);
    }

    @PostMapping
    public SegIndicador crear(@RequestBody SegIndicador segIndicador) {
        return segIndicadorService.save(segIndicador);
    }

    @PutMapping("/{id}")
    public SegIndicador actualizar(@PathVariable Integer id, @RequestBody SegIndicador segIndicador) {
        return segIndicadorService.update(id, segIndicador);
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Integer id) {
        segIndicadorService.delete(id);
        return "SegIndicador eliminado con id " + id;
    }
}
