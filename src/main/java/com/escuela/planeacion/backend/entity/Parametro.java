package com.escuela.planeacion.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Parametros", schema = "odi" , catalog= "Planeacion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Parametro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idparametro")
    private Integer idparametro;

    @Column(name = "tipo", length = 100)
    private String tipo;

    @Column(name = "valor", length = 200)
    private String valor;

    @Column(name = "descripcion", columnDefinition = "nvarchar(max)")
    private String descripcion;

    @Column(name = "secuencial")
    private Integer secuencial;

    // Nuevo campo mapeado al BIT de SQL Server
    @Column(name = "state")
    private Boolean state; 
}