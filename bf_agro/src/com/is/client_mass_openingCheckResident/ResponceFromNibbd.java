package com.is.client_mass_openingCheckResident;

public class ResponceFromNibbd {
	
	private String resultCode;
	private String resultDesc;
	private String result_sql;
	
	public ResponceFromNibbd () {
		
	}
	public ResponceFromNibbd(String resultCode, String resultDesc, String result_sql) {
		super();
		this.resultCode = resultCode;
		this.resultDesc = resultDesc;
		this.result_sql = result_sql;
	}
	
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getResultDesc() {
		return resultDesc;
	}
	public void setResultDesc(String resultDesc) {
		this.resultDesc = resultDesc;
	}
	
	
	public String getResult_sql() {
		return result_sql;
	}
	public void setResult_sql(String result_sql) {
		this.result_sql = result_sql;
	}
	@Override
	public String toString() {
		return "ResponceFromNibbd [resultCode=" + resultCode + ", resultDesc=" + resultDesc + ", result_sql="
				+ result_sql + "]";
	}
	
}
