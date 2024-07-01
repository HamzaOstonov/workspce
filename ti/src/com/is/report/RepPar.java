package com.is.report;

public class RepPar {
    private Long repid;
    private Long parid;
    private String partype;
    private String pname;
    private String psel;

    public RepPar() {

    }

        public RepPar(Long repid, Long parid, String partype, String pname,
                        String psel) {
                super();
                this.repid = repid;
                this.parid = parid;
                this.partype = partype;
                this.pname = pname;
                this.psel = psel;
        }

        public Long getRepid() {
                return repid;
        }

        public void setRepid(Long repid) {
                this.repid = repid;
        }

        public Long getParid() {
                return parid;
        }

        public void setParid(Long parid) {
                this.parid = parid;
        }

        public String getPartype() {
                return partype;
        }

        public void setPartype(String partype) {
                this.partype = partype;
        }

        public String getPname() {
                return pname;
        }

        public void setPname(String pname) {
                this.pname = pname;
        }

        public String getPsel() {
                return psel;
        }

        public void setPsel(String psel) {
                this.psel = psel;
        }

}