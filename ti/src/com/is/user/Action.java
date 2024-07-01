package com.is.user;

public class Action {
    private int id;
    private int mid;
    private String name;
    private String icon;
    private int deal_id;
    private int rep_type_id;

    public Action() {
    	super();
    }
    
    public Action(int id, int mid, String name, String icon, int rep_type_id) {
		super();
		this.id = id;
		this.mid = mid;
		this.name = name;
		this.icon = icon;
		this.rep_type_id = rep_type_id;
	}

	public Action(int id, int mid, String name, String icon, int deal_id, int rep_type_id) {
		super();
		this.id = id;
		this.mid = mid;
		this.name = name;
		this.icon = icon;
		this.deal_id = deal_id;
		this.rep_type_id = rep_type_id;
	}

	public int getRep_type_id() {
		return rep_type_id;
	}

	public void setRep_type_id(int rep_type_id) {
		this.rep_type_id = rep_type_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public int getDeal_id() {
		return deal_id;
	}

	public void setDeal_id(int deal_id) {
		this.deal_id = deal_id;
	}
	
    
    
    
}
