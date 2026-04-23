package com.escuela.planeacion.backend.controller;

import com.escuela.planeacion.backend.entity.Parametro;
import com.escuela.planeacion.backend.service.ParametroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parametros")
@CrossOrigin(origins = "*")
@Tag(name = "Parámetros", description = "Gestión de Parámetros")
public class ParametroController {

    private final ParametroService parametroService;

    public ParametroController(ParametroService parametroService) {
        this.parametroService = parametroService;
    }

    @GetMapping
    @Operation(summary = "Listar todos los parámetros")
    public List<Parametro> obtenerParametros() {
        return parametroService.listarTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un parámetro por ID")
    public ResponseEntity<Parametro> obtenerParametroPorId(@PathVariable Integer id) {
        return parametroService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/tipo/{tipo}")
    @Operation(summary = "Listar parámetros por tipo")
    public List<Parametro> listarPorTipo(@PathVariable String tipo) {
        return parametroService.listarPorTipo(tipo);
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo parámetro")
    public Parametro crearParametro(@RequestBody Parametro parametro) {
        return parametroService.crear(parametro);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un parámetro existente")
    public ResponseEntity<Parametro> actualizarParametro(@PathVariable Integer id, @RequestBody Parametro parametro) {
        try {
            return ResponseEntity.ok(parametroService.actualizar(id, parametro));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un parámetro")
    public ResponseEntity<Void> eliminarParametro(@PathVariable Integer id) {
        parametroService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
