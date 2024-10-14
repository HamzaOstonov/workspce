package com.is.clientcrm.addinfo;

public class CheckListRefData {
    private Boolean isChecked;
	private String data;
    private String label;
    private Object obj;
	
    public CheckListRefData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CheckListRefData(Boolean isChecked, String data, String label) {
		super();
		this.isChecked = isChecked;
		this.data = data;
		this.label = label;
	}

	public CheckListRefData(Boolean isChecked, String data, String label, Object obj) {
		super();
		this.isChecked = isChecked;
		this.data = data;
		this.label = label;
		this.obj = obj;
	}

	public Boolean getIsChecked() {
		return isChecked;
	}

	public void setIsChecked(Boolean isChecked) {
		this.isChecked = isChecked;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

}