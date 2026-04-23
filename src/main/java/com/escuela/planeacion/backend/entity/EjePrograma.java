package com.escuela.planeacion.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Eje_Programa", schema = "odi" , catalog= "Planeacion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EjePrograma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idejeprograma")
    private Integer idejeprograma;

    @Column(name = "nombreep", length = 200)
    private String nombreep;

    @Column(name = "objgeneralep", columnDefinition = "nvarchar(max)")
    private String objgeneralep;

    @Column(name = "tipoep")
    private Integer tipoep;

    @Column(name = "estadoep")
    private Integer estadoep;
}
