package com.escuela.planeacion.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "SegPersonal", schema = "odi" , catalog= "Planeacion")
public class SegPersonal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idsegpersonal")
    private Integer idsegpersonal;

    @Column(name = "idseguimiento")
    private Integer idseguimiento;

    @Column(name = "idpersonal")
    private Integer idpersonal;

    @Column(name = "idactividad")
    private Integer idactividad;

    @Column(name = "horaseg")
    private Double horaseg;

    @Column(name = "agno")
    private Integer agno;

    @Column(name = "tipo")
    private Integer tipo;

    // Getters y setters
    public Integer getIdsegpersonal() { return idsegpersonal; }
    public void setIdsegpersonal(Integer idsegpersonal) { this.idsegpersonal = idsegpersonal; }

    public Integer getIdseguimiento() { return idseguimiento; }
    public void setIdseguimiento(Integer idseguimiento) { this.idseguimiento = idseguimiento; }

    public Integer getIdpersonal() { return idpersonal; }
    public void setIdpersonal(Integer idpersonal) { this.idpersonal = idpersonal; }

    public Integer getIdactividad() { return idactividad; }
    public void setIdactividad(Integer idactividad) { this.idactividad = idactividad; }

    public Double getHoraseg() { return horaseg; }
    public void setHoraseg(Double horaseg) { this.horaseg = horaseg; }

    public Integer getAgno() { return agno; }
    public void setAgno(Integer agno) { this.agno = agno; }

    public Integer getTipo() { return tipo; }
    public void setTipo(Integer tipo) { this.tipo = tipo; }
}
