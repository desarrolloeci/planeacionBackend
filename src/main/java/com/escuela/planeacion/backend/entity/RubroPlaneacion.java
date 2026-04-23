package com.escuela.planeacion.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "RubrosPlaneacion", schema = "odi" , catalog= "Planeacion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RubroPlaneacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idrubropl")
    private Integer idrubropl;

    @Column(name = "nombre", length = 200)
    private String nombre;

    @Column(name = "tipo")
    private Integer tipo;

    @Column(name = "estado")
    private Integer estado;
}
