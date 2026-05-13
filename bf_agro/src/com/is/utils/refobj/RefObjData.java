package com.is.utils.refobj;

public class RefObjData {
    private String data;
    private String label;
    private Object object;
	
    public RefObjData(String data, String label, Object object) {
		super();
		this.data = data;
		this.label = label;
		this.object = object;
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

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}
    
    
}