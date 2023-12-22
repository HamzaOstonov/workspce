package com.is.creditanket.table_models;

public class Ld_guar_gold {

	private String branch;
	private Long id;
	private Long pk;
	private Long pk_ld_guar;
	private Long unit_id;
	private Double weight;
	private String remarks;
	
	public Ld_guar_gold() {
	
	}

	public Ld_guar_gold(String branch, Long id, Long pk, Long pk_ld_guar,
			Long unit_id, Double weight, String remarks) {
		super();
		this.branch = branch;
		this.id = id;
		this.pk = pk;
		this.pk_ld_guar = pk_ld_guar;
		this.unit_id = unit_id;
		this.weight = weight;
		this.remarks = remarks;
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

	public Long getUnit_id() {
		return unit_id;
	}

	public void setUnit_id(Long unit_id) {
		this.unit_id = unit_id;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}
