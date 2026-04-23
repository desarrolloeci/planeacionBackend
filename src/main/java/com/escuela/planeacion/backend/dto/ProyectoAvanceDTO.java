package com.escuela.planeacion.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Proyecto con su información de avance y lista de actividades de seguimiento.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProyectoAvanceDTO {
    private Long idProyecto;
    private String nombreProyecto;
    private String estadoProyecto;
    private String estadoEjecucion;

    private List<ActividadAvanceDTO> actividades = new ArrayList<>();
}
