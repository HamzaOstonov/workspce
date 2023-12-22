package com.is.userti;

public class Role {
    private int id;
    private String name;
    private int dataaccess;

    public Role() {

    }

	public Role(int id, String name, int dataaccess) {
		super();
		this.id = id;
		this.name = name;
		this.dataaccess = dataaccess;
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

	public int getDataaccess() {
		return dataaccess;
	}

	public void setDataaccess(int dataaccess) {
		this.dataaccess = dataaccess;
	}
    
    
    
}
