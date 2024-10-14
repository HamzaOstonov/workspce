package com.is.bfreport;

public class RepData {
	private String data;
    private String label;
    private int type;

    public RepData() {
    	super();
    }
    
    public RepData(String data, String label) {
                super();
                this.data = data;
                this.label = label;
        }

    
    public RepData(String data, String label, int type) {
		super();
		this.data = data;
		this.label = label;
		this.type = type;
	}


		public String getData() {
                return data;
        }


        public void setData(String data) {
                this.data = data;
        }


        public String getLabel() {
                return label;
        }


        public void setLabel(String label) {
                this.label = label;
        }


		public int getType() {
			return type;
		}


		public void setType(int type) {
			this.type = type;
		}
    
}
