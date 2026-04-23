package com.escuela.planeacion.backend.dto;

public class CentroCostoDTO {

    private String cod_cl1;
    private String NCcosto;

    public CentroCostoDTO(String cod_cl1, String NCcosto) {
        this.cod_cl1 = cod_cl1;
        this.NCcosto = NCcosto;
    }

    public String getCod_cl1() { return cod_cl1; }
    public void setCod_cl1(String cod_cl1) { this.cod_cl1 = cod_cl1; }

    public String getNCcosto() { return NCcosto; }
    public void setNCcosto(String NCcosto) { this.NCcosto = NCcosto; }
}