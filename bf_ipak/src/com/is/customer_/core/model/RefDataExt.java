package com.is.customer_.core.model;

public class RefDataExt {
    private String data;
    private String label;
	private String prop_1;


public RefDataExt(String data, String label, String prop_1) {
            super();
            this.data = data;
            this.label = label;
			this.prop_1 = prop_1;
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

    public String getProp_1() {
            return prop_1;
    }


    public void setProp_1(String prop_1) {
            this.prop_1 = prop_1;
    }

}
