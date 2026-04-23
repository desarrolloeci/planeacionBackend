package com.escuela.planeacion.backend.service;

import com.escuela.planeacion.backend.dto.ReportePresupuestoDTO;
import com.escuela.planeacion.backend.repository.ReportePresupuestoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportePresupuestoService {

    private final ReportePresupuestoRepository repository;

    public ReportePresupuestoService(ReportePresupuestoRepository repository) {
        this.repository = repository;
    }

    public List<ReportePresupuestoDTO> obtenerReportePorProyecto(Integer idProyecto) {
        List<Object[]> rawResults = repository.obtenerReportePresupuestoRaw(idProyecto);

        return rawResults.stream().map(r -> new ReportePresupuestoDTO(
                (Integer) r[0],
                (String) r[1],
                (Integer) r[2],
                r[3] != null ? ((Number) r[3]).longValue() : 0L,
                r[4] != null ? ((Number) r[4]).longValue() : 0L,
                r[5] != null ? ((Number) r[5]).longValue() : 0L
        )).toList();
    }
}
