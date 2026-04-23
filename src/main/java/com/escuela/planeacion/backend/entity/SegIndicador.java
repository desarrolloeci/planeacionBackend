package com.escuela.planeacion.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "SegIndicador", schema = "odi" , catalog= "Planeacion")
public class SegIndicador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idindicadorseg")
    private Integer idindicadorseg;

    @Column(name = "resultado")
    private String resultado;

    @Column(name = "descripresult")
    private String descripresult;

    @Column(name = "idindicador")
    private Integer idindicador;

    @Column(name = "idseguimiento")
    private Integer idseguimiento;
}
