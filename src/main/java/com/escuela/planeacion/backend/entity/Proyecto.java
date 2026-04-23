package com.escuela.planeacion.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Table(name = "Proyecto", schema = "odi" , catalog= "Planeacion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idproyecto")
    private Integer idproyecto;

    @Column(name = "nombrepr", length = 1000)
    private String nombrepr;

    @Column(name = "tipopr", length = 1000)
    private String tipopr;

    @Column(name = "ccdirectorpr", length = 20)
    private String ccdirectorpr;

    @Column(name = "ccresponsablepr", length = 20)
    private String ccresponsablepr;

    @Column(name = "metapr", length = 5000)
    private String metapr;

    @Column(name = "justificacionpr", length = 8000)
    private String justificacionpr;

    @Column(name = "Prioridadpr")
    private Integer Prioridadpr;

    @Column(name = "fechainipr")
    private LocalDateTime fechainipr;

    @Column(name = "fechafinpr")
    private LocalDateTime fechafinpr;

    @Column(name = "estadopr")
    private Integer estadopr;

    @Column(name = "valorproyectadopr")
    private BigInteger valorproyectadopr;

    @Column(name = "valorejecutadopr")
    private BigInteger valorejecutadopr;

    @Column(name = "porcejecucionsispr")
    private Integer porcejecucionsispr;

    @Column(name = "porcejecuciondirpr")
    private Integer porcejecuciondirpr;

    @Column(name = "SNIESpr", length = 80)
    private String SNIESpr;

    @Column(name = "observacionpr", length = 8000)
    private String observacionpr;

    @Column(name = "ccusucreapr", length = 20)
    private String ccusucreapr;

    @Column(name = "idplan")
    private Integer idplan;

    @Column(name = "fechacrea")
    private LocalDateTime fechacrea;

    @Column(name = "estadoejecucion")
    private Integer estadoejecucion;

    @Column(name = "unidadejecutora", length = 100)
    private String unidadejecutora;

    @Column(name = "fin")
    private Integer fin;

    @Column(name = "factor")
    private Integer factor;

    @Column(name = "observacionadmin", length = 8000)
    private String observacionadmin;

    @Column(name = "nivelalerta")
    private Integer nivelalerta;

    @Column(name = "valorejero")
    private BigInteger valorejero;

    @Column(name = "valorejper")
    private BigInteger valorejper;

    @Column(name = "valorplero")
    private BigInteger valorplero;

    @Column(name = "valorplper")
    private BigInteger valorplper;

    @Column(name = "megapro", length = 1)
    private String megapro;
}
