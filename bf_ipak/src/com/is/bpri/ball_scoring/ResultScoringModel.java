package com.is.bpri.ball_scoring;

public class ResultScoringModel {

	private int bpr_id;
	private Long interval_from;
	private Long interval_before;
	private String name_result;
	private int is_close_credit;
	
	public ResultScoringModel() {
		
	}

	public ResultScoringModel(int bpr_id, Long interval_from,
			Long interval_before, String name_result, int is_close_credit) {
		super();
		this.bpr_id = bpr_id;
		this.interval_from = interval_from;
		this.interval_before = interval_before;
		this.name_result = name_result;
		this.is_close_credit = is_close_credit;
	}

	public int getBpr_id() {
		return bpr_id;
	}

	public void setBpr_id(int bpr_id) {
		this.bpr_id = bpr_id;
	}

	public Long getInterval_from() {
		return interval_from;
	}

	public void setInterval_from(Long interval_from) {
		this.interval_from = interval_from;
	}

	public Long getInterval_before() {
		return interval_before;
	}

	public void setInterval_before(Long interval_before) {
		this.interval_before = interval_before;
	}

	public String getName_result() {
		return name_result;
	}

	public void setName_result(String name_result) {
		this.name_result = name_result;
	}

	public int getIs_close_credit() {
		return is_close_credit;
	}

	public void setIs_close_credit(int is_close_credit) {
		this.is_close_credit = is_close_credit;
	}
	
}
