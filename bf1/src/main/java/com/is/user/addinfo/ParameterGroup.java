package com.is.user.addinfo;

public class ParameterGroup {
    static final long serialVersionUID = 3L;

	private Long id;
	private String name_en;
	private String name_ru;
	private String name_uz;
	private int is_open;
	private int ord;

    public ParameterGroup() {
		super();
    }

	public ParameterGroup(Long id, String name_en, String name_ru,
			String name_uz, int is_open, int ord) {
		super();
		this.id = id;
		this.name_en = name_en;
		this.name_ru = name_ru;
		this.name_uz = name_uz;
		this.is_open = is_open;
		this.ord = ord;
	}

	public Long getId() { 
		return id;
	} 

	public void setId(Long id) { 
		this.id = id;
	} 

	public String getName_en() { 
		return name_en;
	} 

	public void setName_en(String name_en) { 
		this.name_en = name_en;
	} 

	public String getName_ru() { 
		return name_ru;
	} 

	public void setName_ru(String name_ru) { 
		this.name_ru = name_ru;
	} 

	public String getName_uz() { 
		return name_uz;
	} 

	public void setName_uz(String name_uz) { 
		this.name_uz = name_uz;
	} 

	public int getOrd() { 
		return ord;
	} 

	public void setOrd(int ord) { 
		this.ord = ord;
	}

	public int getIs_open() {
		return is_open;
	}

	public void setIs_open(int is_open) {
		this.is_open = is_open;
	} 
    
}
