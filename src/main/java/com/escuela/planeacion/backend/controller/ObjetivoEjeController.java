package com.escuela.planeacion.backend.controller;

import com.escuela.planeacion.backend.entity.ObjetivoEje;
import com.escuela.planeacion.backend.entity.ObjetivoEjeId;
import com.escuela.planeacion.backend.service.ObjetivoEjeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/objetivos-eje")
@CrossOrigin(origins = "*")
@Tag(name = "Objetivos EJE", description = "Gestión de Objetivos EJE")
public class ObjetivoEjeController {

    private final ObjetivoEjeService objetivoEjeService;

    public ObjetivoEjeController(ObjetivoEjeService objetivoEjeService) {
        this.objetivoEjeService = objetivoEjeService;
    }

    @GetMapping
    @Operation(summary = "Listar todos los objetivos EJE")
    public List<ObjetivoEje> obtenerTodos() {
        return objetivoEjeService.listarTodos();
    }

    @GetMapping("/eje/{idEjePrograma}")
    @Operation(summary = "Listar objetivos EJE por ID de Eje Programa")
    public List<ObjetivoEje> listarPorEje(@PathVariable Integer idEjePrograma) {
        return objetivoEjeService.listarPorEjePrograma(idEjePrograma);
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo objetivo EJE")
    public ObjetivoEje crear(@RequestBody ObjetivoEje objetivoEje) {
        return objetivoEjeService.crear(objetivoEje);
    }

    @PutMapping("/{numeroObj}/{idEjePrograma}")
    @Operation(summary = "Actualizar un objetivo EJE")
    public ResponseEntity<ObjetivoEje> actualizar(
            @PathVariable Integer numeroObj,
            @PathVariable Integer idEjePrograma,
            @RequestBody ObjetivoEje objetivoEje) {
        ObjetivoEjeId id = new ObjetivoEjeId(numeroObj, idEjePrograma);
        try {
            return ResponseEntity.ok(objetivoEjeService.actualizar(id, objetivoEje));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{numeroObj}/{idEjePrograma}")
    @Operation(summary = "Eliminar un objetivo EJE")
    public ResponseEntity<Void> eliminar(
            @PathVariable Integer numeroObj,
            @PathVariable Integer idEjePrograma) {
        objetivoEjeService.eliminar(new ObjetivoEjeId(numeroObj, idEjePrograma));
        return ResponseEntity.noContent().build();
    }
}
