package com.escuela.planeacion.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "\"Eje_Programa-Proyecto\"", schema = "odi" , catalog= "Planeacion")
@IdClass(EjeProgramaProyectoId.class)
public class EjeProgramaProyecto {

    @Id
    private Integer idproyecto;

    @Id
    private Integer idejeprograma;

    private String ejeppal;

    @Override
    public String toString() {
        return "EjeProgramaProyecto{" +
                "idproyecto=" + idproyecto +
                ", idejeprograma=" + idejeprograma +
                ", ejeppal='" + ejeppal + '\'' +
                '}';
    }

    // Getters y Setters
    public Integer getIdproyecto() { return idproyecto; }
    public void setIdproyecto(Integer idproyecto) { this.idproyecto = idproyecto; }

    public Integer getIdejeprograma() { return idejeprograma; }
    public void setIdejeprograma(Integer idejeprograma) { this.idejeprograma = idejeprograma; }

    public String getEjeppal() { return ejeppal; }
    public void setEjeppal(String ejeppal) { this.ejeppal = ejeppal; }
}
