package com.escuela.planeacion.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.escuela.planeacion.backend.service.ReporteService;

import java.io.IOException;

@RestController
@RequestMapping("/api/reportes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ReporteController {

    private final ReporteService reporteService;

    @GetMapping("/proyectos")
    public ResponseEntity<byte[]> generarReportePlaneacion(
            @RequestParam(required = false) String fechaInicio,
            @RequestParam(required = false) String fechaFin,
            @RequestParam(required = false) Long idPlan,
            @RequestParam(required = false) Long idEje,
            @RequestParam(required = false) Long idEstadoProyecto,
            @RequestParam(required = false) Long idEstadoEjecucion,
            @RequestParam(required = false) Long idUnidad,
            @RequestParam(required = false) Long idProyecto
    ) throws IOException {

        byte[] data = reporteService.generarReportePlaneacion(
                fechaInicio, fechaFin, idPlan, idEje, idEstadoProyecto, idEstadoEjecucion, idUnidad, idProyecto);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "informe_planeacion.pdf");

        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }

    @GetMapping("/avance")
    public ResponseEntity<byte[]> generarReporteAvance(
            @RequestParam(required = false) String fechaInicio,
            @RequestParam(required = false) String fechaFin,
            @RequestParam(required = false) Long idPlan,
            @RequestParam(required = false) Long idEje,
            @RequestParam(required = false) Long idEstadoProyecto,
            @RequestParam(required = false) Long idEstadoEjecucion,
            @RequestParam(required = false) Long idUnidad,
            @RequestParam(required = false) Long idProyecto
    ) throws IOException {

        byte[] data = reporteService.generarReporteAvance(
                fechaInicio, fechaFin, idPlan, idEje, idEstadoProyecto, idEstadoEjecucion, idUnidad, idProyecto);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "informe_avance.pdf");

        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }
}