package com.escuela.planeacion.backend.controller;

import com.escuela.planeacion.backend.entity.HorasPersonal;
import com.escuela.planeacion.backend.service.HorasPersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/horas-personal")
@CrossOrigin(origins = "*")
public class HorasPersonalController {

    @Autowired
    private HorasPersonalService horasPersonalService;

    @GetMapping
    public ResponseEntity<List<HorasPersonal>> getTodasHoras() {
        return ResponseEntity.ok(horasPersonalService.getTodasHoras());
    }

    @GetMapping("/{idPersonal}/{agno}")
    public ResponseEntity<HorasPersonal> getHorasPorId(@PathVariable Integer idPersonal,
                                                       @PathVariable Integer agno) {
        return ResponseEntity.ok(horasPersonalService.getHorasPorId(idPersonal, agno));
    }

    @GetMapping("/personal/{idPersonal}")
    public ResponseEntity<List<HorasPersonal>> getHorasPorIdPersonal(@PathVariable Integer idPersonal) {
        return ResponseEntity.ok(horasPersonalService.getHorasPorIdPersonal(idPersonal));
    }

    @PostMapping
    public ResponseEntity<HorasPersonal> crearHoras(@RequestBody HorasPersonal horasPersonal) {
        return ResponseEntity.ok(horasPersonalService.crearHoras(horasPersonal));
    }

    @PutMapping("/{idPersonal}/{agno}")
public ResponseEntity<HorasPersonal> actualizarHoras(
        @PathVariable Integer idPersonal,
        @PathVariable Integer agno,
        @RequestBody HorasPersonal horasActualizado) {

    System.out.println("📩 Recibido PUT /horas-personal/" + idPersonal + "/" + agno);
    System.out.println("🧾 Cuerpo recibido: " + horasActualizado);

    return ResponseEntity.ok(horasPersonalService.actualizarHoras(idPersonal, agno, horasActualizado));
}

    @DeleteMapping("/{idPersonal}/{agno}")
    public ResponseEntity<Void> eliminarHoras(@PathVariable Integer idPersonal,
                                              @PathVariable Integer agno) {
        horasPersonalService.eliminarHoras(idPersonal, agno);
        return ResponseEntity.noContent().build();
    }
}
