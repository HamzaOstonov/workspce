package com.is.tieto_globuz.fileProcessing;

import java.io.Serializable;
import java.util.Date;

public class FileFilter implements Serializable {

    static final long serialVersionUID = 103844514947365244L;

    private Long id;
    private Long fr_file_id;
    private int file_type_id;
    private int state_id;
    private String file_name;
    private Date file_date;
    private String file_state_name;
    
	public FileFilter() {
		super();
	}

	public FileFilter(Long id, Long fr_file_id, int file_type_id, int state_id, String file_name, Date file_date,
			String file_state_name) {
		super();
		this.id = id;
		this.fr_file_id = fr_file_id;
		this.file_type_id = file_type_id;
		this.state_id = state_id;
		this.file_name = file_name;
		this.file_date = file_date;
		this.file_state_name = file_state_name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getFr_file_id() {
		return fr_file_id;
	}

	public void setFr_file_id(Long fr_file_id) {
		this.fr_file_id = fr_file_id;
	}

	public int getFile_type_id() {
		return file_type_id;
	}

	public void setFile_type_id(int file_type_id) {
		this.file_type_id = file_type_id;
	}

	public int getState_id() {
		return state_id;
	}

	public void setState_id(int state_id) {
		this.state_id = state_id;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public Date getFile_date() {
		return file_date;
	}

	public void setFile_date(Date file_date) {
		this.file_date = file_date;
	}

	public String getFile_state_name() {
		return file_state_name;
	}

	public void setFile_state_name(String file_state_name) {
		this.file_state_name = file_state_name;
	}

}
