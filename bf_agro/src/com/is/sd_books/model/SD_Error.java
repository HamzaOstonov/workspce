package com.is.sd_books.model;

import java.util.Date;

public class SD_Error {
	private Date v_date;
	private String note;
	
	public SD_Error(){
		
	}
	
	public SD_Error(Date v_date, String note){
		this.v_date = v_date;
		this.note = note;
	}

	public Date getV_date() {
		return v_date;
	}

	public void setV_date(Date v_date) {
		this.v_date = v_date;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "SD_Error [" + (v_date != null ? "v_date=" + v_date + ", " : "")
				+ (note != null ? "note=" + note : "") + "]";
	}
}
