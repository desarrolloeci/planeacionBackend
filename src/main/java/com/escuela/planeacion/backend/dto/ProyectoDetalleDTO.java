package com.escuela.planeacion.backend.dto;

import com.escuela.planeacion.backend.entity.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import java.util.List;

public class ProyectoDetalleDTO {

	@JsonUnwrapped
    private Proyecto proyecto;  // Contiene toda la info de Proyecto

    private List<Actividad> actividades;
    private List<Archivos> archivos;
    private List<EjeProgramaProyecto> ejes;
    private List<Indicador> indicadores;
    private List<Objetivo> objetivos;
    private List<FinFactorCaractProyecto> factores;

    // Getters y Setters
    public Proyecto getProyecto() { return proyecto; }
    public void setProyecto(Proyecto proyecto) { this.proyecto = proyecto; }

    public List<Actividad> getActividades() { return actividades; }
    public void setActividades(List<Actividad> actividades) { this.actividades = actividades; }

    public List<Archivos> getArchivos() { return archivos; }
    public void setArchivos(List<Archivos> archivos) { this.archivos = archivos; }

    @JsonProperty("ejes")
    public List<EjeProgramaProyecto> getEjeProgramaProyectos() { return ejes; }
    public void setEjeProgramaProyectos(List<EjeProgramaProyecto> ejeProgramaProyectos) { this.ejes = ejeProgramaProyectos; }

    public List<Indicador> getIndicadores() { return indicadores; }
    public void setIndicadores(List<Indicador> indicadores) { this.indicadores = indicadores; }

    public List<Objetivo> getObjetivos() { return objetivos; }
    public void setObjetivos(List<Objetivo> objetivos) { this.objetivos = objetivos; }

    @JsonProperty("factores")
    public List<FinFactorCaractProyecto> getFinFactorCaractProyectos() { return factores; }
    public void setFinFactorCaractProyectos(List<FinFactorCaractProyecto> factores) { this.factores = factores; }
}
