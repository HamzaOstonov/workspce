package com.is.clients.addinfo;
import java.io.Serializable;
import java.util.Date;

public class Clientmap implements Serializable {

    static final long serialVersionUID = 2L;

	private Long id;
	private String branch;
	private String id_client;
	private String name;
	private String code_country;
	private String code_type;
	private String code_resident;
	private String code_subject;
	private String code_form;
	private Date date_open;
	private Date date_close;
	private Long state;
	private Long kod_err;
	private String file_name;
	private Long sign_registr;
	private String alias;

    public Clientmap() {
		super();
    }

    public Clientmap(Long id, String branch, String id_client, String name, String code_country, String code_type, String code_resident, String code_subject, String code_form, Date date_open, Date date_close, Long state, Long kod_err, String file_name, Long sign_registr, String alias) {
		super();
		this.id = id;
		this.branch = branch;
		this.id_client = id_client;
		this.name = name;
		this.code_country = code_country;
		this.code_type = code_type;
		this.code_resident = code_resident;
		this.code_subject = code_subject;
		this.code_form = code_form;
		this.date_open = date_open;
		this.date_close = date_close;
		this.state = state;
		this.kod_err = kod_err;
		this.file_name = file_name;
		this.sign_registr = sign_registr;
		this.alias = alias;
    }

	public Long getId() { 
		return id;
	} 

	public void setId(Long id) { 
		this.id = id;
	} 

	public String getBranch() { 
		return branch;
	} 

	public void setBranch(String branch) { 
		this.branch = branch;
	} 

	public String getId_client() { 
		return id_client;
	} 

	public void setId_client(String id_client) { 
		this.id_client = id_client;
	} 

	public String getName() { 
		return name;
	} 

	public void setName(String name) { 
		this.name = name;
	} 

	public String getCode_country() { 
		return code_country;
	} 

	public void setCode_country(String code_country) { 
		this.code_country = code_country;
	} 

	public String getCode_type() { 
		return code_type;
	} 

	public void setCode_type(String code_type) { 
		this.code_type = code_type;
	} 

	public String getCode_resident() { 
		return code_resident;
	} 

	public void setCode_resident(String code_resident) { 
		this.code_resident = code_resident;
	} 

	public String getCode_subject() { 
		return code_subject;
	} 

	public void setCode_subject(String code_subject) { 
		this.code_subject = code_subject;
	} 

	public String getCode_form() { 
		return code_form;
	} 

	public void setCode_form(String code_form) { 
		this.code_form = code_form;
	} 

	public Date getDate_open() { 
		return date_open;
	} 

	public void setDate_open(Date date_open) { 
		this.date_open = date_open;
	} 

	public Date getDate_close() { 
		return date_close;
	} 

	public void setDate_close(Date date_close) { 
		this.date_close = date_close;
	} 

	public Long getState() { 
		return state;
	} 

	public void setState(Long state) { 
		this.state = state;
	} 

	public Long getKod_err() { 
		return kod_err;
	} 

	public void setKod_err(Long kod_err) { 
		this.kod_err = kod_err;
	} 

	public String getFile_name() { 
		return file_name;
	} 

	public void setFile_name(String file_name) { 
		this.file_name = file_name;
	} 

	public Long getSign_registr() { 
		return sign_registr;
	} 

	public void setSign_registr(Long sign_registr) { 
		this.sign_registr = sign_registr;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	@Override
	public String toString() {
		return "Clientmap [id=" + id + ", branch=" + branch + ", id_client=" + id_client + ", name=" + name
				+ ", code_country=" + code_country + ", code_type=" + code_type + ", code_resident=" + code_resident
				+ ", code_subject=" + code_subject + ", code_form=" + code_form + ", date_open=" + date_open
				+ ", date_close=" + date_close + ", state=" + state + ", kod_err=" + kod_err + ", file_name="
				+ file_name + ", sign_registr=" + sign_registr + ", alias=" + alias + "]";
	}     

}
