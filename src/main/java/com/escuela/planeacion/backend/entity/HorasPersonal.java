package com.escuela.planeacion.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Horas_Personal", schema = "odi" , catalog= "Planeacion")
public class HorasPersonal {

    @EmbeddedId
    private HorasPersonalId id;

    @Column(name = "horas")
    private Double horas;

    @Column(name = "CantPer")
    private Integer cantPer;

    public HorasPersonal() {}

    public HorasPersonalId getId() { return id; }
    public void setId(HorasPersonalId id) { this.id = id; }

    public Double getHoras() { return horas; }
    public void setHoras(Double horas) { this.horas = horas; }

    public Integer getCantPer() { return cantPer; }
    public void setCantPer(Integer cantPer) { this.cantPer = cantPer; }
}
