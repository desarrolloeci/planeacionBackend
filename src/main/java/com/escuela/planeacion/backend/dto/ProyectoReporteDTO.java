package com.escuela.planeacion.backend.dto;

public interface ProyectoReporteDTO {
    Long getIdProyecto();
    String getNombreProyecto();
    String getEstado();
    String getVigencia();
    String getUnidadEjecutora();
    String getObjetivo();
    String getActividad();
    String getMeta();
    String getIndicador();
}