package com.escuela.planeacion.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ObjetivoEjeId implements Serializable {

    @Column(name = "numeroobj")
    private Integer numeroObj;

    @Column(name = "idejeprograma")
    private Integer idEjePrograma;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ObjetivoEjeId that)) return false;
        return Objects.equals(numeroObj, that.numeroObj) &&
               Objects.equals(idEjePrograma, that.idEjePrograma);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroObj, idEjePrograma);
    }
}
