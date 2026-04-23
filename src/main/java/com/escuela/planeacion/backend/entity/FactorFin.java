package com.escuela.planeacion.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Factores_Fines", schema = "odi" , catalog= "Planeacion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FactorFin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idfactor")
    private Integer idfactor;

    @Column(name = "nombrefacfin", length = 200)
    private String nombrefacfin;

    @Column(name = "idplan")
    private Integer idplan;
    
    @Column(name = "secuencial")
    private Integer secuencial;

    @Column(name = "relacionfin")
    private String relacionfin;

    @Column(name = "relacionfactor")
    private String relacionfactor;

    @Column(name = "facintegral")
    private String facintegral;

    @Column(name = "eje")
    private String eje;

    @Column(name = "tipofac")
    private String tipofac;
}
