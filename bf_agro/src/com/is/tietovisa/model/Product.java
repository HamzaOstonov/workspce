package com.is.tietovisa.model;

public class Product {

	    
	    private String cond_set;		
	    private String ccy;		
	    private String name;			
	    private String name2;		
	    private String act;	
	    private int acc_id_order;		
	    private int non_reduce_bal;	
	    private String risk_level;
	    private String bin_code;	    

	    public Product() {
			super();
		}

		public String getCond_set() {
			return cond_set;
		}

		public String getCcy() {
			return ccy;
		}

		public String getName() {
			return name;
		}

		public String getName2() {
			return name2;
		}

		public String getAct() {
			return act;
		}

		public int getAcc_id_order() {
			return acc_id_order;
		}

		public int getNon_reduce_bal() {
			return non_reduce_bal;
		}

		public void setCond_set(String cond_set) {
			this.cond_set = cond_set;
		}

		public void setCcy(String ccy) {
			this.ccy = ccy;
		}

		public void setName(String name) {
			this.name = name;
		}

		public void setName2(String name2) {
			this.name2 = name2;
		}

		public void setAct(String act) {
			this.act = act;
		}

		public void setAcc_id_order(int acc_id_order) {
			this.acc_id_order = acc_id_order;
		}

		public void setNon_reduce_bal(int non_reduce_bal) {
			this.non_reduce_bal = non_reduce_bal;
		}

		public void setRisk_level(String risk_level) {
			this.risk_level = risk_level;
		}

		public String getRisk_level() {
			return risk_level;
		}

		public void setBin_code(String bin_code) {
			this.bin_code = bin_code;
		}

		public String getBin_code() {
			return bin_code;
		}

		 public String toString(){//overriding the toString() method  
			  return 
		        "cond_set		=" + cond_set		+
			    "ccy		        =" + ccy		+
			    "name			="  +name			+
			    "name2		    ="  +name2		    +
			    "act	            =" + act	    +
			    "acc_id_order	="	+ acc_id_order	+
			    "non_reduce_bal	="  + non_reduce_bal+	
			    "risk_level      =" + risk_level    +
			    "bin_code	    =" + bin_code	    ;  
			 }  
}
