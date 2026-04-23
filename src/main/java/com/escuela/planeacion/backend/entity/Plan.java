package com.escuela.planeacion.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Planes", schema = "odi" , catalog= "Planeacion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idplan")
    private Integer idplan;

    @Column(name = "nombrepl", length = 80)
    private String nombrepl;

    @Column(name = "estadopl")
    private Integer estadopl;
}
