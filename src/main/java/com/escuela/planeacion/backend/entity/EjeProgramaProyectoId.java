package com.escuela.planeacion.backend.entity;

import java.io.Serializable;
import java.util.Objects;

public class EjeProgramaProyectoId implements Serializable {
    private Integer idproyecto;
    private Integer idejeprograma;

    public EjeProgramaProyectoId() {}

    public EjeProgramaProyectoId(Integer idproyecto, Integer idejeprograma) {
        this.idproyecto = idproyecto;
        this.idejeprograma = idejeprograma;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EjeProgramaProyectoId)) return false;
        EjeProgramaProyectoId that = (EjeProgramaProyectoId) o;
        return Objects.equals(idproyecto, that.idproyecto) &&
               Objects.equals(idejeprograma, that.idejeprograma);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idproyecto, idejeprograma);
    }
}
