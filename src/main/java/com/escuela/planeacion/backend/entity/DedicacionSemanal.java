package com.escuela.planeacion.backend.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalDate; // Importante agregar esta importación
@Entity
@Table(name = "dedicacion_semanal", schema = "dbo", catalog = "Planeacion")
public class DedicacionSemanal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "idactividad", nullable = false)
    private Integer idactividad;

    @Column(name = "idpersonal")
    private Integer idpersonal;

    @Column(name = "idcargo", length = 10)
    private String idcargo;

    @Column(name = "agno", nullable = false)
    private Integer agno;

    @Column(name = "horas_por_semana", nullable = false)
    private Double horasPorSemana;

    @Column(name = "semanas_calculadas", nullable = false)
    private Integer semanasCalculadas;

    @Column(name = "horas_totales", nullable = false)
    private Double horasTotales;

    @Column(name = "tipo", length = 10, nullable = false)
    private String tipo; // persona o cargo

    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_by", length = 50)
    private String updatedBy;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "idpersona")
private Integer idpersona;

 // Nuevos campos agregados
    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    // ==============================
    // Getters y Setters de los nuevos campos
    // ==============================

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdactividad() {
        return idactividad;
    }

    public void setIdactividad(Integer idactividad) {
        this.idactividad = idactividad;
    }

    public Integer getIdpersonal() {
        return idpersonal;
    }

    public void setIdpersonal(Integer idpersonal) {
        this.idpersonal = idpersonal;
    }

    public String getIdcargo() {
        return idcargo;
    }

    public void setIdcargo(String idcargo) {
        this.idcargo = idcargo;
    }

    public Integer getAgno() {
        return agno;
    }

    public void setAgno(Integer agno) {
        this.agno = agno;
    }

    public Double getHorasPorSemana() {
        return horasPorSemana;
    }

    public void setHorasPorSemana(Double horasPorSemana) {
        this.horasPorSemana = horasPorSemana;
    }

    public Integer getSemanasCalculadas() {
        return semanasCalculadas;
    }

    public void setSemanasCalculadas(Integer semanasCalculadas) {
        this.semanasCalculadas = semanasCalculadas;
    }

    public Double getHorasTotales() {
        return horasTotales;
    }

    public void setHorasTotales(Double horasTotales) {
        this.horasTotales = horasTotales;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getIdpersona() {
    return idpersona;
}

public void setIdpersona(Integer idpersona) {
    this.idpersona = idpersona;
}
}
