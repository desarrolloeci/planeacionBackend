package com.escuela.planeacion.backend.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Embeddable
public class FechaSeguimientoId implements Serializable {

	@Column(name = "feciniseg")
    private LocalDate feciniseg;

    @Column(name = "flag")
    private Integer flag;

    // Constructor vacío
    public FechaSeguimientoId() {}

    // Constructor
    public FechaSeguimientoId(LocalDate feciniseg, Integer flag) {
        this.feciniseg = feciniseg;
        this.flag = flag;
    }

    // Getters y setters
    public LocalDate getFeciniseg() { return feciniseg; }
    public void setFeciniseg(LocalDate feciniseg) { this.feciniseg = feciniseg; }
    public Integer getFlag() { return flag; }
    public void setFlag(Integer flag) { this.flag = flag; }

    // equals y hashCode obligatorios
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FechaSeguimientoId)) return false;
        FechaSeguimientoId that = (FechaSeguimientoId) o;
        return feciniseg.equals(that.feciniseg) && flag.equals(that.flag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(feciniseg, flag);
    }
}

