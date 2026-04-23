package com.escuela.planeacion.backend.dto;

public class HomeParameterDTO {
    private Integer id;
    private String contenidoBase64;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getContenidoBase64() { return contenidoBase64; }
    public void setContenidoBase64(String contenidoBase64) { this.contenidoBase64 = contenidoBase64; }
}
