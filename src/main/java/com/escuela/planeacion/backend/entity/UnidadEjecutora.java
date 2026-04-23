package com.escuela.planeacion.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "UnidadEjecutora", schema = "odi" , catalog = "Planeacion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UnidadEjecutora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idunidadej")
    private Integer idunidadej;

    @Column(name = "nombreunidad", length = 200)
    private String nombreunidad;

    @Column(name = "estadounidadej")
    private Integer estadounidadej;
}
