package com.is.bfreport;

public class Parameter {
	private int id;
	private String name;
	private String par_name;
	private String par_select;
	private String par_type;
	private String par_value;
	private String def_par_value;
	private int ord;
	
	public Parameter(int id, String name, String par_name, String par_select,
			String par_type, String par_value, String def_par_value, int ord) {
		super();
		this.id = id;
		this.name = name;
		this.par_name = par_name;
		this.par_select = par_select;
		this.par_type = par_type;
		this.par_value = par_value;
		this.def_par_value = def_par_value;
		this.ord = ord;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPar_name() {
		return par_name;
	}

	public void setPar_name(String par_name) {
		this.par_name = par_name;
	}

	public String getPar_select() {
		return par_select;
	}

	public void setPar_select(String par_select) {
		this.par_select = par_select;
	}

	public String getPar_type() {
		return par_type;
	}

	public void setPar_type(String par_type) {
		this.par_type = par_type;
	}

	public String getPar_value() {
		return par_value;
	}

	public void setPar_value(String par_value) {
		this.par_value = par_value;
	}

	public String getDef_par_value() {
		return def_par_value;
	}

	public void setDef_par_value(String def_par_value) {
		this.def_par_value = def_par_value;
	}

	public int getOrd() {
		return ord;
	}

	public void setOrd(int ord) {
		this.ord = ord;
	}
	
	   
}
