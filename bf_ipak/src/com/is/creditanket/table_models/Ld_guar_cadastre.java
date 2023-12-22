package com.is.creditanket.table_models;

import java.util.Date;

public class Ld_guar_cadastre {

	private String branch;
	private Long id;
	private Long pk;
	private Long pk_ld_guar;
	private Integer cadastre_type;
	private String certificate_num;
	private Date certificate_date;
	private String cadastre_num;
	private String reyestr_num;
	private Double square;
	private String ovnership;
	
	public Ld_guar_cadastre() {
	
	}

	public Ld_guar_cadastre(String branch, Long id, Long pk, Long pk_ld_guar,
			Integer cadastre_type, String certificate_num,
			Date certificate_date, String cadastre_num, String reyestr_num,
			Double square, String ovnership) {
		super();
		this.branch = branch;
		this.id = id;
		this.pk = pk;
		this.pk_ld_guar = pk_ld_guar;
		this.cadastre_type = cadastre_type;
		this.certificate_num = certificate_num;
		this.certificate_date = certificate_date;
		this.cadastre_num = cadastre_num;
		this.reyestr_num = reyestr_num;
		this.square = square;
		this.ovnership = ovnership;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPk() {
		return pk;
	}

	public void setPk(Long pk) {
		this.pk = pk;
	}

	public Long getPk_ld_guar() {
		return pk_ld_guar;
	}

	public void setPk_ld_guar(Long pk_ld_guar) {
		this.pk_ld_guar = pk_ld_guar;
	}

	public Integer getCadastre_type() {
		return cadastre_type;
	}

	public void setCadastre_type(Integer cadastre_type) {
		this.cadastre_type = cadastre_type;
	}

	public String getCertificate_num() {
		return certificate_num;
	}

	public void setCertificate_num(String certificate_num) {
		this.certificate_num = certificate_num;
	}

	public Date getCertificate_date() {
		return certificate_date;
	}

	public void setCertificate_date(Date certificate_date) {
		this.certificate_date = certificate_date;
	}

	public String getCadastre_num() {
		return cadastre_num;
	}

	public void setCadastre_num(String cadastre_num) {
		this.cadastre_num = cadastre_num;
	}

	public String getReyestr_num() {
		return reyestr_num;
	}

	public void setReyestr_num(String reyestr_num) {
		this.reyestr_num = reyestr_num;
	}

	public Double getSquare() {
		return square;
	}

	public void setSquare(Double square) {
		this.square = square;
	}

	public String getOvnership() {
		return ovnership;
	}

	public void setOvnership(String ovnership) {
		this.ovnership = ovnership;
	}
	
}
