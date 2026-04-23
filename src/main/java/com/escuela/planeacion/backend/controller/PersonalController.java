package com.escuela.planeacion.backend.controller;

import com.escuela.planeacion.backend.entity.Personal;
import com.escuela.planeacion.backend.service.PersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personal")
@CrossOrigin(origins = "*")
public class PersonalController {

    @Autowired
    private PersonalService personalService;

    @GetMapping
    public ResponseEntity<List<Personal>> getTodosPersonales() {
        return ResponseEntity.ok(personalService.getTodosPersonales());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Personal> getPersonalPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(personalService.getPersonalPorId(id));
    }

    @GetMapping("/actividad/{idActividad}")
    public ResponseEntity<List<Personal>> getPersonalesPorActividad(@PathVariable Integer idActividad) {
        return ResponseEntity.ok(personalService.getPersonalesPorActividad(idActividad));
    }

    @PostMapping
    public ResponseEntity<Personal> crearPersonal(@RequestBody Personal personal) {
        return ResponseEntity.ok(personalService.crearPersonal(personal));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Personal> actualizarPersonal(@PathVariable Integer id,
                                                      @RequestBody Personal personalActualizado) {
        return ResponseEntity.ok(personalService.actualizarPersonal(id, personalActualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPersonal(@PathVariable Integer id) {
        personalService.eliminarPersonal(id);
        return ResponseEntity.noContent().build();
    }
}
