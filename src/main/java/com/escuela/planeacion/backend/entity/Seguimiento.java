package com.escuela.planeacion.backend.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Seguimiento", schema = "odi" , catalog= "Planeacion")
public class Seguimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idseguimiento;

    private LocalDateTime fechaseg;
    private Double prcntavanceproyseg;
    private String estadoproyseg;
    private String estadosistemaseg;
    private String descripavanceseg;
    private String accionesseg;
    private Integer idproyecto;
    private String estadoseg;
    private String dificultadesavance;
    private LocalDateTime fechaenvioseg;

    // Getters y Setters
    public Integer getIdseguimiento() { return idseguimiento; }
    public void setIdseguimiento(Integer idseguimiento) { this.idseguimiento = idseguimiento; }
    public LocalDateTime getFechaseg() { return fechaseg; }
    public void setFechaseg(LocalDateTime fechaseg) { this.fechaseg = fechaseg; }
    public Double getPrcntavanceproyseg() { return prcntavanceproyseg; }
    public void setPrcntavanceproyseg(Double prcntavanceproyseg) { this.prcntavanceproyseg = prcntavanceproyseg; }
    public String getEstadoproyseg() { return estadoproyseg; }
    public void setEstadoproyseg(String estadoproyseg) { this.estadoproyseg = estadoproyseg; }
    public String getEstadosistemaseg() { return estadosistemaseg; }
    public void setEstadosistemaseg(String estadosistemaseg) { this.estadosistemaseg = estadosistemaseg; }
    public String getDescripavanceseg() { return descripavanceseg; }
    public void setDescripavanceseg(String descripavanceseg) { this.descripavanceseg = descripavanceseg; }
    public String getAccionesseg() { return accionesseg; }
    public void setAccionesseg(String accionesseg) { this.accionesseg = accionesseg; }
    public Integer getIdproyecto() { return idproyecto; }
    public void setIdproyecto(Integer idproyecto) { this.idproyecto = idproyecto; }
    public String getEstadoseg() { return estadoseg; }
    public void setEstadoseg(String estadoseg) { this.estadoseg = estadoseg; }
    public String getDificultadesavance() { return dificultadesavance; }
    public void setDificultadesavance(String dificultadesavance) { this.dificultadesavance = dificultadesavance; }
    public LocalDateTime getFechaenvioseg() { return fechaenvioseg; }
    public void setFechaenvioseg(LocalDateTime fechaenvioseg) { this.fechaenvioseg = fechaenvioseg; }
}
