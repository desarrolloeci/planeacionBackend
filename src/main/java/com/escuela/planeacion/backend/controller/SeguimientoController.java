package com.escuela.planeacion.backend.controller;

import com.escuela.planeacion.backend.entity.Seguimiento;
import com.escuela.planeacion.backend.service.SeguimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/seguimientos")
public class SeguimientoController {

    @Autowired
    private SeguimientoService seguimientoService;

    // GET - lista todos
    @GetMapping
    public List<Seguimiento> getTodos() {
        return seguimientoService.getAll();
    }

    // GET - uno por ID
    @GetMapping("/{id}")
    public Seguimiento getPorId(@PathVariable Integer id) {
        return seguimientoService.getById(id);
    }

    // POST - llama al procedimiento almacenado
    @PostMapping
    public String crear(@RequestParam String proy) {
        seguimientoService.crearSeguimiento(proy);
        return "Seguimiento creado para proyecto " + proy + " usando el procedimiento ODI";
    }

    // PUT - actualización directa en la tabla
    @PutMapping("/{id}")
    public Seguimiento actualizar(@PathVariable Integer id, @RequestBody Seguimiento datos) {
        return seguimientoService.actualizar(id, datos);
    }

    // DELETE - eliminar seguimiento
    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Integer id) {
        seguimientoService.eliminar(id);
        return "Seguimiento eliminado con id " + id;
    }
    
    @GetMapping("/proyecto/{idproyecto}")
    public List<Seguimiento> getPorIdProyecto(@PathVariable Integer idproyecto) {
        return seguimientoService.getByIdProyecto(idproyecto);
    }
}
