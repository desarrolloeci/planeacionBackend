package com.escuela.planeacion.backend.entity;

import java.time.LocalDate;

import jakarta.persistence.*;


@Entity
@Table(name = "FechaSeguimiento", schema = "odi", catalog = "Planeacion")
public class FechaSeguimiento {

    @EmbeddedId
    private FechaSeguimientoId id;

    @Column(name = "fecfinseg")
    private LocalDate fecfinseg;

    @Column(name = "fechainiodi")
    private LocalDate fechainiodi;

    @Column(name = "fechafinodi")
    private LocalDate fechafinodi;

    // Getters y setters
    public FechaSeguimientoId getId() { return id; }
    public void setId(FechaSeguimientoId id) { this.id = id; }

    public LocalDate getFecfinseg() { return fecfinseg; }
    public void setFecfinseg(LocalDate fecfinseg) { this.fecfinseg = fecfinseg; }

    public LocalDate getFechainiodi() { return fechainiodi; }
    public void setFechainiodi(LocalDate fechainiodi) { this.fechainiodi = fechainiodi; }

    public LocalDate getFechafinodi() { return fechafinodi; }
    public void setFechafinodi(LocalDate fechafinodi) { this.fechafinodi = fechafinodi; }
}
