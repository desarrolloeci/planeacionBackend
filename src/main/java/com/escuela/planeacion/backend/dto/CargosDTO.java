package com.escuela.planeacion.backend.dto;

public class CargosDTO {

    private String cod_car;
    private String nom_car;
    private String usr_val_hora;

    public CargosDTO(String cod_car, String nom_car, String usr_val_hora) {
        this.cod_car = cod_car;
        this.nom_car = nom_car;
        this.usr_val_hora = usr_val_hora;
    }

    public String getCod_car() { return cod_car; }
    public void setCod_car(String cod_car) { this.cod_car = cod_car; }

    public String getNom_car() { return nom_car; }
    public void setNom_car(String nom_car) { this.nom_car = nom_car; }

    public String getUsr_val_hora() { return usr_val_hora; }
    public void setUsr_val_hora(String usr_val_hora) { this.usr_val_hora = usr_val_hora; }
}
