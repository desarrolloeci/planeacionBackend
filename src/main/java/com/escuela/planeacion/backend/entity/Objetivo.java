package com.escuela.planeacion.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Objetivo", schema = "odi" , catalog= "Planeacion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Objetivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idobjetivo")
    private Integer idobjetivo;

    @Column(name = "descripcionob", columnDefinition = "nvarchar(max)")
    private String descripcionob;

    @Column(name = "tipoob")
    private Integer tipoob;

    @Column(name = "idproyecto")
    private Integer idproyecto;
}
