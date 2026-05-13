package com.is.tietovisa.model;

public class ListType_CardInfo  implements java.io.Serializable {
    private RowType_CardInfo[] row;

    public ListType_CardInfo() {
    }

    public ListType_CardInfo(
    		 RowType_CardInfo[] row) {
           this.row = row;
    }


    /**
     * Gets the row value for this ListType_CardInfo.
     * 
     * @return row
     */
    public  RowType_CardInfo[] getRow() {
        return row;
    }


    /**
     * Sets the row value for this ListType_CardInfo.
     * 
     * @param row
     */
    public void setRow( RowType_CardInfo[] row) {
        this.row = row;
    }

    public  RowType_CardInfo getRow(int i) {
        return this.row[i];
    }

    public void setRow(int i,  RowType_CardInfo _value) {
        this.row[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ListType_CardInfo)) return false;
        ListType_CardInfo other = (ListType_CardInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.row==null && other.getRow()==null) || 
             (this.row!=null &&
              java.util.Arrays.equals(this.row, other.getRow())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getRow() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getRow());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getRow(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }


}
