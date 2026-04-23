package com.escuela.planeacion.backend.controller;

import com.escuela.planeacion.backend.entity.EjeProgramaProyecto;
import com.escuela.planeacion.backend.service.EjeProgramaProyectoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(
		origins = "*",
	    allowedHeaders = "*",
	    methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE }
	)
@RequestMapping("/api/eje-programa-proyecto")
public class EjeProgramaProyectoController {

    private final EjeProgramaProyectoService service;

    public EjeProgramaProyectoController(EjeProgramaProyectoService service) {
        this.service = service;
    }

    // =========================
    // GET
    // =========================

    @GetMapping
    public List<EjeProgramaProyecto> getAll() {
        return service.getAll();
    }

    @GetMapping("/proyecto/{idproyecto}")
    public List<EjeProgramaProyecto> getByProyecto(
            @PathVariable Integer idproyecto
    ) {
        return service.getByIdProyecto(idproyecto);
    }

    @GetMapping("/{idproyecto}/{idejeprograma}")
    public ResponseEntity<EjeProgramaProyecto> getById(
            @PathVariable Integer idproyecto,
            @PathVariable Integer idejeprograma
    ) {
        return service.getById(idproyecto, idejeprograma)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // =========================
    // POST
    // =========================

    @PostMapping
    public ResponseEntity<EjeProgramaProyecto> create(
            @RequestBody EjeProgramaProyecto entity
    ) {
        return ResponseEntity.ok(service.save(entity));
    }

    // =========================
    // PUT
    // =========================

    @PutMapping("/{idproyecto}/{idejeprograma}")
    public ResponseEntity<EjeProgramaProyecto> update(
            @PathVariable Integer idproyecto,
            @PathVariable Integer idejeprograma,
            @RequestBody EjeProgramaProyecto entity
    ) {
        return ResponseEntity.ok(
                service.update(idproyecto, idejeprograma, entity)
        );
    }

    // =========================
    // DELETE
    // =========================

    @DeleteMapping("/{idproyecto}/{idejeprograma}")
    public ResponseEntity<Void> delete(
            @PathVariable Integer idproyecto,
            @PathVariable Integer idejeprograma
    ) {
        service.delete(idproyecto, idejeprograma);
        return ResponseEntity.noContent().build();
    }
    
    @PutMapping("/principal/{idproyecto}/{idejeprograma}")
    public ResponseEntity<Void> marcarEjePrincipal(
            @PathVariable Integer idproyecto,
            @PathVariable Integer idejeprograma
    ) {
        service.marcarEjePrincipal(idproyecto, idejeprograma);
        return ResponseEntity.noContent().build();
    }
}
