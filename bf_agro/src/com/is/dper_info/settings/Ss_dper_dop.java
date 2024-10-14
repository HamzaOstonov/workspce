package com.is.dper_info.settings;

public class Ss_dper_dop {
	private int id_dper;
	private int id;
	private String value1;
	private String value2;
	private String value3;
	private String value4;
	private String coment;
	private String state;
	private String rowid;
	

	public Ss_dper_dop() {
	}
	
	

	public Ss_dper_dop(int id_dper, int id, String value1, String value2,
			String value3, String value4, String coment, String state,
			String rowid) {
		super();
		this.id_dper = id_dper;
		this.id = id;
		this.value1 = value1;
		this.value2 = value2;
		this.value3 = value3;
		this.value4 = value4;
		this.coment = coment;
		this.state = state;
		this.rowid = rowid;
	}



	public int getId_dper() {
		return id_dper;
	}
	public void setId_dper(int id_dper) {
		this.id_dper = id_dper;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getValue1() {
		return value1;
	}

	public void setValue1(String value1) {
		this.value1 = value1;
	}

	public String getValue2() {
		return value2;
	}

	public void setValue2(String value2) {
		this.value2 = value2;
	}
	
	public String getValue3() {
		return value3;
	}


	public void setValue3(String value3) {
		this.value3 = value3;
	}


	public String getValue4() {
		return value4;
	}


	public void setValue4(String value4) {
		this.value4 = value4;
	}


	public String getComent() {
		return coment;
	}

	public void setComent(String coment) {
		this.coment = coment;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getRowid() {
		return rowid;
	}

	public void setRowid(String rowid) {
		this.rowid = rowid;
	}

	
}
