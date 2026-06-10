package com.escuela.planeacion.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "home_parameter", schema = "odi" , catalog= "Planeacion")
public class HomeParameter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Lob
    @Column(name = "contenido")
    private byte[] contenido;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public byte[] getContenido() { return contenido; }
    public void setContenido(byte[] contenido) { this.contenido = contenido; }
}
