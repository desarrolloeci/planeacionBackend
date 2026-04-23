package com.escuela.planeacion.backend.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Actividad", schema = "odi" , catalog= "Planeacion")
public class Actividad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idactividad;

    private String nombreact;
    private String descripcionact;
    private LocalDate fechainiact;
    private LocalDate fechafinact;
    private Double porcejecucionact;
    private Double porcproyectoact;
    private String tipoact;
    private Integer consecutivoact;
    private Integer idproyecto;
    private String responsableact;
    private Integer consecutivo;

    // Getters y Setters
    public Integer getIdactividad() { return idactividad; }
    public void setIdactividad(Integer idactividad) { this.idactividad = idactividad; }

    public String getNombreact() { return nombreact; }
    public void setNombreact(String nombreact) { this.nombreact = nombreact; }

    public String getDescripcionact() { return descripcionact; }
    public void setDescripcionact(String descripcionact) { this.descripcionact = descripcionact; }

    public LocalDate getFechainiact() { return fechainiact; }
    public void setFechainiact(LocalDate fechainiact) { this.fechainiact = fechainiact; }

    public LocalDate getFechafinact() { return fechafinact; }
    public void setFechafinact(LocalDate fechafinact) { this.fechafinact = fechafinact; }

    public Double getPorcejecucionact() { return porcejecucionact; }
    public void setPorcejecucionact(Double porcejecucionact) { this.porcejecucionact = porcejecucionact; }

    public Double getPorcproyectoact() { return porcproyectoact; }
    public void setPorcproyectoact(Double porcproyectoact) { this.porcproyectoact = porcproyectoact; }

    public String getTipoact() { return tipoact; }
    public void setTipoact(String tipoact) { this.tipoact = tipoact; }

    public Integer getConsecutivoact() { return consecutivoact; }
    public void setConsecutivoact(Integer consecutivoact) { this.consecutivoact = consecutivoact; }

    public Integer getIdproyecto() { return idproyecto; }
    public void setIdproyecto(Integer idproyecto) { this.idproyecto = idproyecto; }

    public String getResponsableact() { return responsableact; }
    public void setResponsableact(String responsableact) { this.responsableact = responsableact; }

    public Integer getConsecutivo() { return consecutivo; }
    public void setConsecutivo(Integer consecutivo) { this.consecutivo = consecutivo; }
}
