package com.escuela.planeacion.backend.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "ErogacionOF", schema = "odi" , catalog= "Planeacion")
public class ErogacionOF {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iderogacionof")
    private Integer iderogacionof;

    @Column(name = "ccosto")
    private Integer ccosto;

    @Column(name = "rubro")
    private Integer rubro;

    @Column(name = "tiporub")
    private Integer tiporub;

    @Column(name = "idproyecto")
    private Integer idproyecto;

    @Column(name = "valor")
    private Double valor;

    @Column(name = "fecharub")
    @Temporal(TemporalType.DATE)
    private Date fecharub;

    // Getters y setters
    public Integer getIderogacionof() { return iderogacionof; }
    public void setIderogacionof(Integer iderogacionof) { this.iderogacionof = iderogacionof; }

    public Integer getCcosto() { return ccosto; }
    public void setCcosto(Integer ccosto) { this.ccosto = ccosto; }

    public Integer getRubro() { return rubro; }
    public void setRubro(Integer rubro) { this.rubro = rubro; }

    public Integer getTiporub() { return tiporub; }
    public void setTiporub(Integer tiporub) { this.tiporub = tiporub; }

    public Integer getIdproyecto() { return idproyecto; }
    public void setIdproyecto(Integer idproyecto) { this.idproyecto = idproyecto; }

    public Double getValor() { return valor; }
    public void setValor(Double valor) { this.valor = valor; }

    public Date getFecharub() { return fecharub; }
    public void setFecharub(Date fecharub) { this.fecharub = fecharub; }
}
