package com.escuela.planeacion.backend.controller;

import com.escuela.planeacion.backend.service.ReporteExcelProyectoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/reportes/excel")
@CrossOrigin(origins = "*")
public class ReporteExcelProyectoController {

    private final ReporteExcelProyectoService service;

    @GetMapping("/proyecto/{idProyecto}")
    public ResponseEntity<byte[]> descargar(@PathVariable Long idProyecto) {
        try {
            byte[] xlsx = service.generarExcelProyecto(idProyecto);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=reporte_proyecto_" + idProyecto + ".xlsx")
                    .contentType(MediaType.parseMediaType(
                            "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                    .body(xlsx);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError()
                    .contentType(MediaType.TEXT_PLAIN)
                    .body(("No se pudo generar el Excel: " + ex.getMessage()).getBytes());
        }
    }
}