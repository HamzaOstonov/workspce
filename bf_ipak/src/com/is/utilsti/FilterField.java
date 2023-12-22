package com.is.utilsti;

public class FilterField {
    private String sqlwhere;
    private Object colobject;

public FilterField() {

}
public FilterField(String sqlwhere,Object colobject ) {
      this.sqlwhere = sqlwhere;
      this.colobject = colobject;
}



    public String getSqlwhere() {
            return sqlwhere;
    }

    public void setSqlwhere(String sqlwhere) {
            this.sqlwhere = sqlwhere;
    }

    public Object getColobject() {
            return colobject;
    }

    public void setColobject(Object colobject) {
            this.colobject = colobject;
    }
}