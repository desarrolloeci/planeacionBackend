package com.escuela.planeacion.backend.controller;

import com.escuela.planeacion.backend.entity.SegMetas;
import com.escuela.planeacion.backend.service.SegMetasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/segmetas")
@CrossOrigin(origins = "*")
public class SegMetasController {

    @Autowired
    private SegMetasService segMetasService;

    @GetMapping
    public List<SegMetas> getTodos() {
        return segMetasService.getAll();
    }

    @GetMapping("/{id}")
    public SegMetas getPorId(@PathVariable Integer id) {
        return segMetasService.getById(id);
    }

    @GetMapping("/seguimiento/{idseguimeinto}")
    public List<SegMetas> getPorIdSeguimiento(@PathVariable Integer idseguimeinto) {
        return segMetasService.getByIdSeguimiento(idseguimeinto);
    }

    @PostMapping
    public SegMetas crear(@RequestBody SegMetas segMetas) {
        return segMetasService.save(segMetas);
    }

    @PutMapping("/{id}")
    public SegMetas actualizar(@PathVariable Integer id, @RequestBody SegMetas segMetas) {
        return segMetasService.update(id, segMetas);
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Integer id) {
        segMetasService.delete(id);
        return "SegMetas eliminado con id " + id;
    }
}
