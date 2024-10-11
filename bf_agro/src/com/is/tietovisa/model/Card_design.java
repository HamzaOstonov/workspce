package com.is.tietovisa.model;

public class Card_design {
	private int design_id;
	private String design_type;
	private String design_type_name;
	private String cardname;
	
	 public Card_design() {
			super();
		}

	public int getDesign_id() {
		return design_id;
	}

	public String getDesign_type() {
		return design_type;
	}

	public String getDesign_type_name() {
		return design_type_name;
	}

	public String getCardname() {
		return cardname;
	}

	public void setDesign_id(int design_id) {
		this.design_id = design_id;
	}

	public void setDesign_type(String design_type) {
		this.design_type = design_type;
	}

	public void setDesign_type_name(String design_type_name) {
		this.design_type_name = design_type_name;
	}

	public void setCardname(String cardname) {
		this.cardname = cardname;
	}
	 


}
