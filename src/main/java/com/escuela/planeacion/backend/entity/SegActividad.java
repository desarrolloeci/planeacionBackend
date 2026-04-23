package com.escuela.planeacion.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "SegActividad", schema = "odi" , catalog= "Planeacion")
public class SegActividad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idactividadseg")
    private Integer idactividadseg;

    @Column(name = "idactividad")
    private Integer idactividad;

    @Column(name = "idseguimiento")
    private Integer idseguimiento;

    @Column(name = "estadoejecactividad")
    private Integer estadoejecactividad;

    @Column(name = "descripavance")
    private String descripavance;

    @Column(name = "accionesact")
    private String accionesact;

    @Column(name = "porcavanact")
    private Integer porcavanact;

    // Getters y setters
    public Integer getIdactividadseg() { return idactividadseg; }
    public void setIdactividadseg(Integer idactividadseg) { this.idactividadseg = idactividadseg; }

    public Integer getIdactividad() { return idactividad; }
    public void setIdactividad(Integer idactividad) { this.idactividad = idactividad; }

    public Integer getIdseguimiento() { return idseguimiento; }
    public void setIdseguimiento(Integer idseguimiento) { this.idseguimiento = idseguimiento; }

    public Integer getEstadoejecactividad() { return estadoejecactividad; }
    public void setEstadoejecactividad(Integer estadoejecactividad) { this.estadoejecactividad = estadoejecactividad; }

    public String getDescripavance() { return descripavance; }
    public void setDescripavance(String descripavance) { this.descripavance = descripavance; }

    public String getAccionesact() { return accionesact; }
    public void setAccionesact(String accionesact) { this.accionesact = accionesact; }

    public Integer getPorcavanact() { return porcavanact; }
    public void setPorcavanact(Integer porcavanact) { this.porcavanact = porcavanact; }
}
