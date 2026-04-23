package com.escuela.planeacion.backend.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Avance de una actividad específica.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActividadAvanceDTO {
    private Long idActividad;
    private String nombreActividad;
    private String descripcionActividad;
    private Long idSeg;
    private String descripAvance;
    private String acciones;
    private String porcAvance;
    private String estadoActividad;
}
