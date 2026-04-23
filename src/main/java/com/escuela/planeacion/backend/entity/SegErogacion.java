package com.escuela.planeacion.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "SegErogacion", schema = "odi" , catalog= "Planeacion")
public class SegErogacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iderogacionseg")
    private Integer iderogacionseg;

    @Column(name = "agno")
    private Integer agno;

    @Column(name = "saldo")
    private Double saldo;

    @Column(name = "idseguimiento")
    private Integer idseguimiento;

    @Column(name = "apropiacion")
    private Double apropiacion;

    @Column(name = "adicioncambioagno")
    private Double adicioncambioagno;

    @Column(name = "aprfinal")
    private Double aprfinal;

    @Column(name = "rubro")
    private Integer rubro;

    @Column(name = "ccosto")
    private Integer ccosto;

    // Getters y setters
    public Integer getIderogacionseg() { return iderogacionseg; }
    public void setIderogacionseg(Integer iderogacionseg) { this.iderogacionseg = iderogacionseg; }

    public Integer getAgno() { return agno; }
    public void setAgno(Integer agno) { this.agno = agno; }

    public Double getSaldo() { return saldo; }
    public void setSaldo(Double saldo) { this.saldo = saldo; }

    public Integer getIdseguimiento() { return idseguimiento; }
    public void setIdseguimiento(Integer idseguimiento) { this.idseguimiento = idseguimiento; }

    public Double getApropiacion() { return apropiacion; }
    public void setApropiacion(Double apropiacion) { this.apropiacion = apropiacion; }

    public Double getAdicioncambioagno() { return adicioncambioagno; }
    public void setAdicioncambioagno(Double adicioncambioagno) { this.adicioncambioagno = adicioncambioagno; }

    public Double getAprfinal() { return aprfinal; }
    public void setAprfinal(Double aprfinal) { this.aprfinal = aprfinal; }

    public Integer getRubro() { return rubro; }
    public void setRubro(Integer rubro) { this.rubro = rubro; }

    public Integer getCcosto() { return ccosto; }
    public void setCcosto(Integer ccosto) { this.ccosto = ccosto; }
}
