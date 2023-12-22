package com.is.bpri.ldguarrgrids;

import java.util.Date;

public class RealestateCadastr {
	
	private String bpr_id;
	private int id_blocks;
	private int id_cadastre;
	private String cadastre_type;
	private String certificate_num;
	private Date certificate_date;
	private String cadastre_num;
	private String reyestr_num;
	private String square;
	private String ovnership;
	private String id_nn;
	private String block_name;
	private String description;
	private String ld_square;
	private String cost;
	private String pk;
	private boolean bool = false;
	
	public RealestateCadastr(){

	}

	public RealestateCadastr(int id_blocks, int id_cadastre,
			String cadastre_type, String certificate_num,
			Date certificate_date, String cadastre_num, String reyestr_num,
			String square, String ovnership, String id_nn, String block_name,
			String description, String ld_square, String cost,String pk) {
		super();
		this.id_blocks = id_blocks;
		this.id_cadastre = id_cadastre;
		this.cadastre_type = cadastre_type;
		this.certificate_num = certificate_num;
		this.certificate_date = certificate_date;
		this.cadastre_num = cadastre_num;
		this.reyestr_num = reyestr_num;
		this.square = square;
		this.ovnership = ovnership;
		this.id_nn = id_nn;
		this.block_name = block_name;
		this.description = description;
		this.ld_square = ld_square;
		this.cost = cost;
		this.pk = pk;
	}

	public int getId_blocks() {
		return id_blocks;
	}

	public void setId_blocks(int id_blocks) {
		this.id_blocks = id_blocks;
	}

	public int getId_cadastre() {
		return id_cadastre;
	}

	public void setId_cadastre(int id_cadastre) {
		this.id_cadastre = id_cadastre;
	}

	public String getCadastre_type() {
		return cadastre_type;
	}

	public void setCadastre_type(String cadastre_type) {
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

	public String getSquare() {
		return square;
	}

	public void setSquare(String square) {
		this.square = square;
	}

	public String getOvnership() {
		return ovnership;
	}

	public void setOvnership(String ovnership) {
		this.ovnership = ovnership;
	}

	public String getId_nn() {
		return id_nn;
	}

	public void setId_nn(String id_nn) {
		this.id_nn = id_nn;
	}

	public String getBlock_name() {
		return block_name;
	}

	public void setBlock_name(String block_name) {
		this.block_name = block_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLd_square() {
		return ld_square;
	}

	public void setLd_square(String ld_square) {
		this.ld_square = ld_square;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public String getBpr_id() {
		return bpr_id;
	}

	public void setBpr_id(String bpr_id) {
		this.bpr_id = bpr_id;
	}

	public String getPk() {
		return pk;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}
	
	public boolean getBool() {
		return bool;
	}

	public void setBool(boolean bool) {
		this.bool = bool;
	}
	
}
