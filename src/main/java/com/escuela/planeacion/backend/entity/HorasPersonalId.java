package com.escuela.planeacion.backend.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class HorasPersonalId implements Serializable {

    @Column(name = "idpersonal")
    private Integer idPersonal;

    @Column(name = "agno")
    private Integer agno;

    public HorasPersonalId() {}

    public HorasPersonalId(Integer idPersonal, Integer agno) {
        this.idPersonal = idPersonal;
        this.agno = agno;
    }

    public Integer getIdPersonal() { return idPersonal; }
    public void setIdPersonal(Integer idPersonal) { this.idPersonal = idPersonal; }

    public Integer getAgno() { return agno; }
    public void setAgno(Integer agno) { this.agno = agno; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HorasPersonalId)) return false;
        HorasPersonalId that = (HorasPersonalId) o;
        return Objects.equals(getIdPersonal(), that.getIdPersonal()) &&
               Objects.equals(getAgno(), that.getAgno());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdPersonal(), getAgno());
    }
}
