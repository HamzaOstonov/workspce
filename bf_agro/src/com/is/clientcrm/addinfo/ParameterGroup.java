package com.is.clientcrm.addinfo;

public class ParameterGroup {
    static final long serialVersionUID = 3L;

	private String id;
	private int is_open;
	private int ord;

    public ParameterGroup() {
		super();
    }

	public ParameterGroup(String id, int is_open, int ord) {
		super();
		this.id = id;
		this.is_open = is_open;
		this.ord = ord;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getIs_open() {
		return is_open;
	}

	public void setIs_open(int is_open) {
		this.is_open = is_open;
	}

	public int getOrd() {
		return ord;
	}

	public void setOrd(int ord) {
		this.ord = ord;
	}
	
}
