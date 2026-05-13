package com.is.report;

public class Report {
    private Long id;
    private String rclass;
    private String rfname;
    private String rname;

    public Report() {

    }
    public Report(Long id, String rclass, String rfname, String rname) {
                super();
                this.id = id;
                this.rclass = rclass;
                this.rfname = rfname;
                this.rname = rname;
        }
        public Long getId() {
                return id;
        }
        public void setId(Long id) {
                this.id = id;
        }
        public String getRclass() {
                return rclass;
        }
        public void setRclass(String rclass) {
                this.rclass = rclass;
        }
        public String getRfname() {
                return rfname;
        }
        public void setRfname(String rfname) {
                this.rfname = rfname;
        }
        public String getRname() {
                return rname;
        }
        public void setRname(String rname) {
                this.rname = rname;
        }
}
