package com.is.creditanket.table_models;

public class Ld_guar_blocks {

	private String branch;
	private Long id;
	private Long pk;
	private Long pk_ld_guar;
	private Long id_nn;
	private String block_name;
	private String description;
	private Long cost;
	private Double square;
	
	public Ld_guar_blocks() {
		
	}

	public Ld_guar_blocks(String branch, Long id, Long pk, Long pk_ld_guar,
			Long id_nn, String block_name, String description, Long cost,
			Double square) {
		super();
		this.branch = branch;
		this.id = id;
		this.pk = pk;
		this.pk_ld_guar = pk_ld_guar;
		this.id_nn = id_nn;
		this.block_name = block_name;
		this.description = description;
		this.cost = cost;
		this.square = square;
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

	public Long getId_nn() {
		return id_nn;
	}

	public void setId_nn(Long id_nn) {
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

	public Long getCost() {
		return cost;
	}

	public void setCost(Long cost) {
		this.cost = cost;
	}

	public Double getSquare() {
		return square;
	}

	public void setSquare(Double square) {
		this.square = square;
	}
	
}
