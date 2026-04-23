package com.escuela.planeacion.backend.controller;

import com.escuela.planeacion.backend.entity.DedicacionSemanal;
import com.escuela.planeacion.backend.service.DedicacionSemanalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dedicacion-semanal")
@CrossOrigin(origins = "*")
public class DedicacionSemanalController {

    @Autowired
    private DedicacionSemanalService service;

    // Crear dedicación
    @PostMapping
    public DedicacionSemanal crear(@RequestBody DedicacionSemanal dedicacion) {
        return service.crear(dedicacion);
    }

    // Listar todas
    @GetMapping
    public List<DedicacionSemanal> listar() {
        return service.listar();
    }

    // Listar por actividad
    @GetMapping("/actividad/{idactividad}")
    public List<DedicacionSemanal> listarPorActividad(@PathVariable Integer idactividad) {
        return service.listarPorActividad(idactividad);
    }

    // Listar por persona
    @GetMapping("/personal/{idpersonal}")
    public List<DedicacionSemanal> listarPorPersonal(@PathVariable Integer idpersonal) {
        return service.listarPorPersonal(idpersonal);
    }

    // Listar por cargo
    @GetMapping("/cargo/{idcargo}")
    public List<DedicacionSemanal> listarPorCargo(@PathVariable String idcargo) {
        return service.listarPorCargo(idcargo);
    }

    // Actualizar dedicación existente
    @PutMapping("/{id}")
    public DedicacionSemanal actualizar(@PathVariable Integer id, @RequestBody DedicacionSemanal dedicacion) {
        return service.actualizar(id, dedicacion);
    }

    // Eliminar (opcional)
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        service.eliminar(id);
    }

    @GetMapping("/actividad/{idactividad}/personal/{idpersonal}")
public List<DedicacionSemanal> listarPorActividadYPersonal(
        @PathVariable Integer idactividad,
        @PathVariable Integer idpersonal) {
    return service.listarPorActividadYPersonal(idactividad, idpersonal);
}

// 🔹 Buscar dedicaciones por actividad y cargo
@GetMapping("/actividad/{idactividad}/cargo/{idcargo}")
public List<DedicacionSemanal> listarPorActividadYCargo(
        @PathVariable Integer idactividad,
        @PathVariable String idcargo) {
    return service.listarPorActividadYCargo(idactividad, idcargo);
}
}
