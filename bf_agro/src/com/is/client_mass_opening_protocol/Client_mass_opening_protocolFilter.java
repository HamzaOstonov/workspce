package com.is.client_mass_opening_protocol;

public class Client_mass_opening_protocolFilter {
    private int id;
    private String  client_id;
    private String score_res;
    private String opening_res;
    private String opening_uzcard;
    private String opening_humo;
    private int status_score;
    private int status_opening;
    private int status_uzcard;
    private int status_humo;
    

	public Client_mass_opening_protocolFilter(int id, String client_id, String score_res, String opening_res,
			String opening_uzcard, String opening_humo, int status_score, int status_opening, int status_uzcard,
			int status_humo) {
		super();
		this.id = id;
		this.client_id = client_id;
		this.score_res = score_res;
		this.opening_res = opening_res;
		this.opening_uzcard = opening_uzcard;
		this.opening_humo = opening_humo;
		this.status_score = status_score;
		this.status_opening = status_opening;
		this.status_uzcard = status_uzcard;
		this.status_humo = status_humo;
	}
	public Client_mass_opening_protocolFilter() {
	
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getClient_id() {
		return client_id;
	}
	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}
	public String getScore_res() {
		return score_res;
	}
	public void setScore_res(String score_res) {
		this.score_res = score_res;
	}
	public String getOpening_res() {
		return opening_res;
	}
	public void setOpening_res(String opening_res) {
		this.opening_res = opening_res;
	}
	public String getOpening_uzcard() {
		return opening_uzcard;
	}
	public void setOpening_uzcard(String opening_uzcard) {
		this.opening_uzcard = opening_uzcard;
	}
	public String getOpening_humo() {
		return opening_humo;
	}
	public void setOpening_humo(String opening_humo) {
		this.opening_humo = opening_humo;
	}
	public int getStatus_score() {
		return status_score;
	}
	public void setStatus_score(int status_score) {
		this.status_score = status_score;
	}
	public int getStatus_opening() {
		return status_opening;
	}
	public void setStatus_opening(int status_opening) {
		this.status_opening = status_opening;
	}
	public int getStatus_uzcard() {
		return status_uzcard;
	}
	public void setStatus_uzcard(int status_uzcard) {
		this.status_uzcard = status_uzcard;
	}
	public int getStatus_humo() {
		return status_humo;
	}
	public void setStatus_humo(int status_humo) {
		this.status_humo = status_humo;
	}
    
    
    
}
