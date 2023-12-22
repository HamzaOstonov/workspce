package com.is.bpri;

public class NikiAsokiReq {

	String credit_bureau;
	String application_id;
	String branch;
	String request_type;

	public NikiAsokiReq() {

	}

	public NikiAsokiReq(String credit_bureau, String application_id, String branch, String request_type) {
		super();
		this.credit_bureau = credit_bureau;
		this.application_id = application_id;
		this.branch = branch;
		this.request_type = request_type;
	}

	public String getCredit_bureau() {
		return credit_bureau;
	}

	public void setCredit_bureau(String credit_bureau) {
		this.credit_bureau = credit_bureau;
	}

	public String getApplication_id() {
		return application_id;
	}

	public void setApplication_id(String application_id) {
		this.application_id = application_id;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getRequest_type() {
		return request_type;
	}

	public void setRequest_type(String request_type) {
		this.request_type = request_type;
	}

/*	@Override
	public String toString() {
		return "NikiAsokiReq [credit_bureau=" + credit_bureau + ", application_id=" + application_id + ", branch="
				+ branch + ", request_type=" + request_type + "]";
	}
	*/
	public String toString(){
		return "{"+ "\"credit_bureau\":  " + credit_bureau + ", "
				+ "\"application_id\":  " + application_id + ", "
				+ "\"branch\":  " + branch + ", "
				+ "\"request_type\":  \"" + request_type + "\" "
		        + "}";
		
	}
	
}
