package com.is.openwayutils.utils;

public class RefData {
        private String data;
    private String label;


    public RefData(String data, String label) {
                super();
                this.data = data;
                this.label = label;
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


}