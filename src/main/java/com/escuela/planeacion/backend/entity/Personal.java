package com.escuela.planeacion.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Personal", schema = "odi" , catalog= "Planeacion")
public class Personal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpersonal")
    private Integer idpersonal;

    @Column(name = "nombreparticpprs")
    private String nombreparticpprs;

    @Column(name = "cargoparticprs")
    private String cargoparticprs;

    @Column(name = "valorprs")
    private Double valorprs;

    @Column(name = "idactividad")
    private Integer idactividad;

    @Column(name = "estado")
    private Integer estado;

	public Integer getIdpersonal() {
		return idpersonal;
	}

	public void setIdpersonal(Integer idpersonal) {
		this.idpersonal = idpersonal;
	}

	public String getNombreparticpprs() {
		return nombreparticpprs;
	}

	public void setNombreparticpprs(String nombreparticpprs) {
		this.nombreparticpprs = nombreparticpprs;
	}

	public String getCargoparticprs() {
		return cargoparticprs;
	}

	public void setCargoparticprs(String cargoparticprs) {
		this.cargoparticprs = cargoparticprs;
	}

	public Double getValorprs() {
		return valorprs;
	}

	public void setValorprs(Double valorprs) {
		this.valorprs = valorprs;
	}

	public Integer getIdactividad() {
		return idactividad;
	}

	public void setIdactividad(Integer idactividad) {
		this.idactividad = idactividad;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

    
}
