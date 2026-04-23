package com.escuela.planeacion.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "gen_ccosto", schema = "dbo", catalog = "Novasoft")
public class Snies {

    @Id
    @Column(name = "cod_cco")
    private String cod_cco;

    @Column(name = "nom_cco")
    private String nom_cco;

    @Column(name = "est_cco")
    private String est_cco;

    @Column(name = "cod_cco_Extr")
    private String cod_cco_Extr;

    // Getters y Setters
    public String getCod_cco() { return cod_cco; }
    public void setCod_cco(String cod_cco) { this.cod_cco = cod_cco; }

    public String getNom_cco() { return nom_cco; }
    public void setNom_cco(String nom_cco) { this.nom_cco = nom_cco; }

    public String getEst_cco() { return est_cco; }
    public void setEst_cco(String est_cco) { this.est_cco = est_cco; }

    public String getCod_cco_Extr() { return cod_cco_Extr; }
    public void setCod_cco_Extr(String cod_cco_Extr) { this.cod_cco_Extr = cod_cco_Extr; }
}
