package com.escuela.planeacion.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Indicador", schema = "odi" , catalog= "Planeacion")
public class Indicador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idindicador;

    private String nombreind;
    private String calculoind;
    private String periodicidadind;
    private String tipoind;
    private Integer idproyecto;
    private Integer idactividad;
    private String descripcioncal;

    // Getters y Setters
    public Integer getIdindicador() { return idindicador; }
    public void setIdindicador(Integer idindicador) { this.idindicador = idindicador; }

    public String getNombreind() { return nombreind; }
    public void setNombreind(String nombreind) { this.nombreind = nombreind; }

    public String getCalculoind() { return calculoind; }
    public void setCalculoind(String calculoind) { this.calculoind = calculoind; }

    public String getPeriodicidadind() { return periodicidadind; }
    public void setPeriodicidadind(String periodicidadind) { this.periodicidadind = periodicidadind; }

    public String getTipoind() { return tipoind; }
    public void setTipoind(String tipoind) { this.tipoind = tipoind; }

    public Integer getIdproyecto() { return idproyecto; }
    public void setIdproyecto(Integer idproyecto) { this.idproyecto = idproyecto; }

    public Integer getIdactividad() { return idactividad; }
    public void setIdactividad(Integer idactividad) { this.idactividad = idactividad; }

    public String getDescripcioncal() { return descripcioncal; }
    public void setDescripcioncal(String descripcioncal) { this.descripcioncal = descripcioncal; }
}
