package com.escuela.planeacion.backend.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "empnomina", schema = "odi" , catalog= "Planeacion")
public class Responsable {

    @Id
    @Column(name = "cod_emp")
    private String cod_emp;

    @Column(name = "nombres")
    private String nombres;

    @Column(name = "cod_ccosto")
    private String codCCosto;

    @Column(name = "nom_ccosto")
    private String nomCCosto;

    @Column(name = "cod_cl1")
    private String codCl1;

    @Column(name = "nom_cl1")
    private String nomCl1;

    @Column(name = "unidad")
    private String unidad;

    @Column(name = "est_lab")
    private String estLab;

    @Column(name = "eciv")
    private String eciv;

    @Column(name = "est_civ")
    private String estCiv;

    @Column(name = "fec_nac")
    @Temporal(TemporalType.DATE)
    private Date fecNac;

    @Column(name = "gru_san")
    private String gruSan;

    @Column(name = "fac_rhh")
    private String facRhh;

    @Column(name = "nac_emp")
    private String nacEmp;

    @Column(name = "dir_res")
    private String dirRes;

    @Column(name = "tel_res")
    private String telRes;

    @Column(name = "ciu_res")
    private String ciuRes;

    @Column(name = "num_lib")
    private String numLib;

    @Column(name = "cla_lib")
    private String claLib;

    @Column(name = "dim_lib")
    private String dimLib;

    @Column(name = "tel_cel")
    private String telCel;

    @Column(name = "gen")
    private String gen;

    @Column(name = "e_mail")
    private String eMail;

    @Column(name = "categoria")
    private String categoria;

    @Column(name = "deicacion_horas")
    private String dedicacionHoras;

    @Column(name = "Expr1")
    private String expr1;

    @Column(name = "Expr2")
    private String expr2;

    @Column(name = "val_hora")
    private Double valHora;

    @Column(name = "tip_con")
    private String tipCon;

    @Column(name = "nom_con")
    private String nomCon;

    @Column(name = "ap1_emp")
    private String ap1Emp;

    @Column(name = "ap2_emp")
    private String ap2Emp;

    @Column(name = "nom_emp")
    private String nomEmp;

    @Column(name = "sex_emp")
    private String sexEmp;

    @Column(name = "cod_car")
    private String codCar;

    @Column(name = "usr_val_hora")
    private String usrValHora;

    @Column(name = "cod_pai")
    private String codPai;

	public String getCod_emp() {
		return cod_emp;
	}

	public void setCod_emp(String cod_emp) {
		this.cod_emp = cod_emp;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getCodCCosto() {
		return codCCosto;
	}

	public void setCodCCosto(String codCCosto) {
		this.codCCosto = codCCosto;
	}

	public String getNomCCosto() {
		return nomCCosto;
	}

	public void setNomCCosto(String nomCCosto) {
		this.nomCCosto = nomCCosto;
	}

	public String getCodCl1() {
		return codCl1;
	}

	public void setCodCl1(String codCl1) {
		this.codCl1 = codCl1;
	}

	public String getNomCl1() {
		return nomCl1;
	}

	public void setNomCl1(String nomCl1) {
		this.nomCl1 = nomCl1;
	}

	public String getUnidad() {
		return unidad;
	}

	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}

	public String getEstLab() {
		return estLab;
	}

	public void setEstLab(String estLab) {
		this.estLab = estLab;
	}

	public String getEciv() {
		return eciv;
	}

	public void setEciv(String eciv) {
		this.eciv = eciv;
	}

	public String getEstCiv() {
		return estCiv;
	}

	public void setEstCiv(String estCiv) {
		this.estCiv = estCiv;
	}

	public Date getFecNac() {
		return fecNac;
	}

	public void setFecNac(Date fecNac) {
		this.fecNac = fecNac;
	}

	public String getGruSan() {
		return gruSan;
	}

	public void setGruSan(String gruSan) {
		this.gruSan = gruSan;
	}

	public String getFacRhh() {
		return facRhh;
	}

	public void setFacRhh(String facRhh) {
		this.facRhh = facRhh;
	}

	public String getNacEmp() {
		return nacEmp;
	}

	public void setNacEmp(String nacEmp) {
		this.nacEmp = nacEmp;
	}

	public String getDirRes() {
		return dirRes;
	}

	public void setDirRes(String dirRes) {
		this.dirRes = dirRes;
	}

	public String getTelRes() {
		return telRes;
	}

	public void setTelRes(String telRes) {
		this.telRes = telRes;
	}

	public String getCiuRes() {
		return ciuRes;
	}

	public void setCiuRes(String ciuRes) {
		this.ciuRes = ciuRes;
	}

	public String getNumLib() {
		return numLib;
	}

	public void setNumLib(String numLib) {
		this.numLib = numLib;
	}

	public String getClaLib() {
		return claLib;
	}

	public void setClaLib(String claLib) {
		this.claLib = claLib;
	}

	public String getDimLib() {
		return dimLib;
	}

	public void setDimLib(String dimLib) {
		this.dimLib = dimLib;
	}

	public String getTelCel() {
		return telCel;
	}

	public void setTelCel(String telCel) {
		this.telCel = telCel;
	}

	public String getGen() {
		return gen;
	}

	public void setGen(String gen) {
		this.gen = gen;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getDedicacionHoras() {
		return dedicacionHoras;
	}

	public void setDedicacionHoras(String dedicacionHoras) {
		this.dedicacionHoras = dedicacionHoras;
	}

	public String getExpr1() {
		return expr1;
	}

	public void setExpr1(String expr1) {
		this.expr1 = expr1;
	}

	public String getExpr2() {
		return expr2;
	}

	public void setExpr2(String expr2) {
		this.expr2 = expr2;
	}

	public Double getValHora() {
		return valHora;
	}

	public void setValHora(Double valHora) {
		this.valHora = valHora;
	}

	public String getTipCon() {
		return tipCon;
	}

	public void setTipCon(String tipCon) {
		this.tipCon = tipCon;
	}

	public String getNomCon() {
		return nomCon;
	}

	public void setNomCon(String nomCon) {
		this.nomCon = nomCon;
	}

	public String getAp1Emp() {
		return ap1Emp;
	}

	public void setAp1Emp(String ap1Emp) {
		this.ap1Emp = ap1Emp;
	}

	public String getAp2Emp() {
		return ap2Emp;
	}

	public void setAp2Emp(String ap2Emp) {
		this.ap2Emp = ap2Emp;
	}

	public String getNomEmp() {
		return nomEmp;
	}

	public void setNomEmp(String nomEmp) {
		this.nomEmp = nomEmp;
	}

	public String getSexEmp() {
		return sexEmp;
	}

	public void setSexEmp(String sexEmp) {
		this.sexEmp = sexEmp;
	}

	public String getCodCar() {
		return codCar;
	}

	public void setCodCar(String codCar) {
		this.codCar = codCar;
	}

	public String getUsrValHora() {
		return usrValHora;
	}

	public void setUsrValHora(String usrValHora) {
		this.usrValHora = usrValHora;
	}

	public String getCodPai() {
		return codPai;
	}

	public void setCodPai(String codPai) {
		this.codPai = codPai;
	}

    // getters y setters
    
    
}
