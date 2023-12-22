package com.is.userti;

public class Module {
    private int id;
    private int parentid;
    private String mtype;
    private String name;
    private String mname;
    private String icon;

    public Module() {
    	super();
    }

	public Module(int id, int parentid, String mtype, String name,
			String mname, String icon) {
		super();
		this.id = id;
		this.parentid = parentid;
		this.mtype = mtype;
		this.name = name;
		this.mname = mname;
		this.icon = icon;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getParentid() {
		return parentid;
	}

	public void setParentid(int parentid) {
		this.parentid = parentid;
	}

	public String getMtype() {
		return mtype;
	}

	public void setMtype(String mtype) {
		this.mtype = mtype;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
    

}
