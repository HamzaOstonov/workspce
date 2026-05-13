package com.is.bpri.template_c;

public class Ss_bpr_scoring_anket {

	private String id;
	private String type_field;
	private String label;
	
	public Ss_bpr_scoring_anket() {
	
	}

	public Ss_bpr_scoring_anket(String id, String type_field, String label) {
		super();
		this.id = id;
		this.type_field = type_field;
		this.label = label;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType_field() {
		return type_field;
	}

	public void setType_field(String type_field) {
		this.type_field = type_field;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
}
