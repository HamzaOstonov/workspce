package com.is.file_reciever_srv.ext_utils;

import java.util.Date;

public class ExtFile {
	private int ext_type;
	private Long id;
    private String branch;
    private String file_name;
    private Date date_in;
    private long file_num;
    private String last_file_num;
    private int state;
    private int doc_cnt;
    private long doc_amount;
    private long file_type;
    private long fr_id;

    public ExtFile() {
    	super();
    }

	public ExtFile(int ext_type, Long id, String branch, 
			String file_name, Date date_in,	long file_num, 
			int doc_cnt, long doc_amount, String last_file_num, 
			int state, long file_type, long fr_id) {
		super();
		this.ext_type = ext_type;
		this.id = id;
		this.branch = branch;
		this.file_name = file_name;
		this.date_in = date_in;
		this.file_num = file_num;
		this.last_file_num = last_file_num;
		this.state = state;
		this.doc_cnt = doc_cnt;
		this.doc_amount = doc_amount;
		this.file_type = file_type;
		this.fr_id = fr_id;
	}

	public long getFile_type()
	{
		return file_type;
	}

	public void setFile_type(long file_type)
	{
		this.file_type = file_type;
	}

	public long getFr_id()
	{
		return fr_id;
	}

	public void setFr_id(long fr_id)
	{
		this.fr_id = fr_id;
	}

	public int getExt_type()
	{
		return ext_type;
	}

	public void setExt_type(int ext_type)
	{
		this.ext_type = ext_type;
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

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public Date getDate_in() {
		return date_in;
	}

	public void setDate_in(Date date_in) {
		this.date_in = date_in;
	}

	public long getFile_num() {
		return file_num;
	}

	public void setFile_num(long file_num) {
		this.file_num = file_num;
	}

	public String getLast_file_num() {
		return last_file_num;
	}

	public void setLast_file_num(String last_file_num) {
		this.last_file_num = last_file_num;
	}
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getDoc_cnt()
	{
		return doc_cnt;
	}

	public void setDoc_cnt(int doc_cnt)
	{
		this.doc_cnt = doc_cnt;
	}

	public long getDoc_amount()
	{
		return doc_amount;
	}

	public void setDoc_amount(long doc_amount)
	{
		this.doc_amount = doc_amount;
	}

	@Override
	public String toString()
	{
		return "ExtFile [ext_type=" + ext_type + ", id=" + id + ", branch="
				+ branch + ", file_name=" + file_name + ", date_in=" + date_in
				+ ", file_num=" + file_num + ", last_file_num=" + last_file_num
				+ ", state=" + state + ", doc_cnt=" + doc_cnt + ", doc_amount="
				+ doc_amount + ", file_type=" + file_type + ", fr_id=" + fr_id
				+ "]";
	}
}
