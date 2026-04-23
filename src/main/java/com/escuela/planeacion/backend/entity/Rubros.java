package com.escuela.planeacion.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "usr_rubros_planeacion", schema = "dbo", catalog = "Novasoft")
public class Rubros {

    @Id
    @Column(name = "cod_rub")
    private String cod_rub;

    @Column(name = "ano_acu")
    private Integer ano_acu;

    @Column(name = "nom_rub")
    private String nom_rub;

    @Column(name = "cod_cl1")
    private String cod_cl1;

    @Column(name = "saldo")
    private Double saldo;

    @Column(name = "apropiacion")
    private Double apropiacion;

    @Column(name = "ADICION")
    private Double ADICION;

    @Column(name = "apr_final")
    private Double apr_final;

    // Getters y Setters
    public String getCod_rub() { return cod_rub; }
    public void setCod_rub(String cod_rub) { this.cod_rub = cod_rub; }

    public Integer getAno_acu() { return ano_acu; }
    public void setAno_acu(Integer ano_acu) { this.ano_acu = ano_acu; }

    public String getNom_rub() { return nom_rub; }
    public void setNom_rub(String nom_rub) { this.nom_rub = nom_rub; }

    public String getCod_cl1() { return cod_cl1; }
    public void setCod_cl1(String cod_cl1) { this.cod_cl1 = cod_cl1; }

    public Double getSaldo() { return saldo; }
    public void setSaldo(Double saldo) { this.saldo = saldo; }

    public Double getApropiacion() { return apropiacion; }
    public void setApropiacion(Double apropiacion) { this.apropiacion = apropiacion; }

    public Double getADICION() { return ADICION; }
    public void setADICION(Double ADICION) { this.ADICION = ADICION; }

    public Double getApr_final() { return apr_final; }
    public void setApr_final(Double apr_final) { this.apr_final = apr_final; }
}
