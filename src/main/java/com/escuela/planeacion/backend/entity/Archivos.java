package com.escuela.planeacion.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Archivos", schema = "odi" , catalog= "Planeacion")
public class Archivos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idarchivo;

    private String nombrearc;
    private String nombreorig;
    private String estado;
    private Integer idproyecto;
    private Integer idactividad;
    private String tipocargue;
    private String observacion;
    private String seguimiento;

    // Getters y Setters
    public Integer getIdarchivo() { return idarchivo; }
    public void setIdarchivo(Integer idarchivo) { this.idarchivo = idarchivo; }

    public String getNombrearc() { return nombrearc; }
    public void setNombrearc(String nombrearc) { this.nombrearc = nombrearc; }

    public String getNombreorig() { return nombreorig; }
    public void setNombreorig(String nombreorig) { this.nombreorig = nombreorig; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Integer getIdproyecto() { return idproyecto; }
    public void setIdproyecto(Integer idproyecto) { this.idproyecto = idproyecto; }

    public Integer getIdactividad() { return idactividad; }
    public void setIdactividad(Integer idactividad) { this.idactividad = idactividad; }

    public String getTipocargue() { return tipocargue; }
    public void setTipocargue(String tipocargue) { this.tipocargue = tipocargue; }

    public String getObservacion() { return observacion; }
    public void setObservacion(String observacion) { this.observacion = observacion; }

    public String getSeguimiento() { return seguimiento; }
    public void setSeguimiento(String seguimiento) { this.seguimiento = seguimiento; }
}
