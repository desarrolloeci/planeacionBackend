package com.escuela.planeacion.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "SegMetas", schema = "odi" , catalog= "Planeacion")
public class SegMetas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idmetasseg")
    private Integer idmetasseg;

    @Column(name = "idmetaobj")
    private Integer idmetaobj;

    @Column(name = "idseguimeinto") // así está en la BD
    private Integer idseguimeinto;

    @Column(name = "descripavancemetobj")
    private String descripavancemetobj;
}
