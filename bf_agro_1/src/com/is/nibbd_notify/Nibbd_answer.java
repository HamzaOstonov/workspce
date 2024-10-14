package com.is.nibbd_notify;

public class Nibbd_answer {

	private Long id;
	private Long ans_file_id;
	private String req_id;
	private String code_answer;
	private String text_answer;
	public Long getId() {
		return id;
	}
	public Long getAns_file_id() {
		return ans_file_id;
	}
	public String getReq_id() {
		return req_id;
	}
	public String getCode_answer() {
		return code_answer;
	}
	public String getText_answer() {
		return text_answer;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setAns_file_id(Long ans_file_id) {
		this.ans_file_id = ans_file_id;
	}
	public void setReq_id(String req_id) {
		this.req_id = req_id;
	}
	public void setCode_answer(String code_answer) {
		this.code_answer = code_answer;
	}
	public void setText_answer(String text_answer) {
		this.text_answer = text_answer;
	}
	
	
	
}
