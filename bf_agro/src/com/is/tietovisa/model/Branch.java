package com.is.tietovisa.model;

public class Branch {
	 private String branch;		
	    private String branch_name;		
	    private String city;
	    private String mfo;
	    private String b_br_id;
	    
	    public Branch() {
			super();
		}
		public String getBranch() {
			return branch;
		}
		public String getBranch_name() {
			return branch_name;
		}
		public String getCity() {
			return city;
		}
		public String getMfo() {
			return mfo;
		}
		public String getB_br_id() {
			return b_br_id;
		}
		public void setBranch(String branch) {
			this.branch = branch;
		}
		public void setBranch_name(String branch_name) {
			this.branch_name = branch_name;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public void setMfo(String mfo) {
			this.mfo = mfo;
		}
		public void setB_br_id(String b_br_id) {
			this.b_br_id = b_br_id;
		}
	    
	    
}
