package com.is.bpri.bproductDesc;



public class bproduct_descFilter
{
   

	 	private String id;
	    private int detail_group;
	    private Long detail_id;
	    private String branch;

	    public bproduct_descFilter(){}

	    public bproduct_descFilter( String id, int detail_group, Long detail_id, String branch){
	    	this.id = id;
	        this.detail_group = detail_group;
	        this.detail_id = detail_id;
	        this.branch = branch;
	    }
	    
	    public String getId(){
			return id;
		}
	    
		public void setId(String id){
			this.id = id;
		}
		
		public int getDetail_group(){
			return detail_group;
		}
		
		public void setDetail_group(int detail_group){
			this.detail_group = detail_group;
		}
		
		public Long getDetail_id(){
			return detail_id;
		}
		public void setDetail_id(Long detail_id){
			this.detail_id = detail_id;
		}
		
		public String getBranch() {
			return branch;
		}

		public void setBranch(String branch) {
			this.branch = branch;
		}


}