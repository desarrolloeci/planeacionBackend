package com.escuela.planeacion.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ObjetivosEJE", schema = "odi" , catalog= "Planeacion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ObjetivoEje {

    @EmbeddedId
    private ObjetivoEjeId id;

    @Column(name = "descripcion", columnDefinition = "nvarchar(max)")
    private String descripcion;
}
