package com.escuela.planeacion.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "FinFactorCaractProyecto", schema = "odi" , catalog= "Planeacion")
public class FinFactorCaractProyecto {

    @Id
    private Integer idproyecto; 

    private Integer idfin;
    private Integer idfactor;
    private String nombrecaract;
    private Integer idfactintegral;
    private String eje;
    
    @Override
    public String toString() {
        return "FinFactorCaractProyecto{" +
                "idproyecto=" + idproyecto +
                '}';
    }

    // Getters y Setters
    public Integer getIdproyecto() { return idproyecto; }
    public void setIdproyecto(Integer idproyecto) { this.idproyecto = idproyecto; }

    public Integer getIdfin() { return idfin; }
    public void setIdfin(Integer idfin) { this.idfin = idfin; }

    public Integer getIdfactor() { return idfactor; }
    public void setIdfactor(Integer idfactor) { this.idfactor = idfactor; }

    public String getNombrecaract() { return nombrecaract; }
    public void setNombrecaract(String nombrecaract) { this.nombrecaract = nombrecaract; }

    public Integer getIdfactintegral() { return idfactintegral; }
    public void setIdfactintegral(Integer idfactintegral) { this.idfactintegral = idfactintegral; }

    public String getEje() { return eje; }
    public void setEje(String eje) { this.eje = eje; }
}
