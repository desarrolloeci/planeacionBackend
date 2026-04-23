package com.escuela.planeacion.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportePresupuestoDTO {
    private Integer idActividad;
    private String actividad;
    private Integer anio;
    private Long dedicacionPersonal;
    private Long presupuestoErogacion;
    private Long totalActividad;
}
