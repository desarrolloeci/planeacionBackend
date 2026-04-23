package com.escuela.planeacion.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ErogacionPL", schema = "odi" , catalog= "Planeacion")
public class ErogacionPL {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iderogacionpl")
    private Integer iderogacionpl;

    @Column(name = "tiporubpl")
    private Integer tiporubpl;

    @Column(name = "rubropl")
    private Integer rubropl;

    @Column(name = "observacionpl")
    private String observacionpl;

    @Column(name = "idproyecto")
    private Integer idproyecto;

    @Column(name = "idactividad")
    private Integer idactividad;

    @Column(name = "valor")
    private Double valor;

    @Column(name = "agno")
    private Integer agno;

    // Getters y setters
    public Integer getIderogacionpl() { return iderogacionpl; }
    public void setIderogacionpl(Integer iderogacionpl) { this.iderogacionpl = iderogacionpl; }

    public Integer getTiporubpl() { return tiporubpl; }
    public void setTiporubpl(Integer tiporubpl) { this.tiporubpl = tiporubpl; }

    public Integer getRubropl() { return rubropl; }
    public void setRubropl(Integer rubropl) { this.rubropl = rubropl; }

    public String getObservacionpl() { return observacionpl; }
    public void setObservacionpl(String observacionpl) { this.observacionpl = observacionpl; }

    public Integer getIdproyecto() { return idproyecto; }
    public void setIdproyecto(Integer idproyecto) { this.idproyecto = idproyecto; }

    public Integer getIdactividad() { return idactividad; }
    public void setIdactividad(Integer idactividad) { this.idactividad = idactividad; }

    public Double getValor() { return valor; }
    public void setValor(Double valor) { this.valor = valor; }

    public Integer getAgno() { return agno; }
    public void setAgno(Integer agno) { this.agno = agno; }
}
