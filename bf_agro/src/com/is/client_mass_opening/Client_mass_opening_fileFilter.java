package com.is.client_mass_opening;


public class Client_mass_opening_fileFilter {
	private int id;
    private String file_name;
    private String sender;
    private String v_date;
    private String status;
    


    public Client_mass_opening_fileFilter() {

    }
    

	public Client_mass_opening_fileFilter(int id, String file_name, String sender, String v_date, String status) {
		super();
		this.id = id;
		this.file_name = file_name;
		this.sender = sender;
		this.status = status;
		this.v_date = v_date;
	}





	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getV_date() {
		return v_date;
	}

	public void setV_date(String v_date) {
		this.v_date = v_date;
	}

	
    
    
}
