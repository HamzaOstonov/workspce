package com.is.tietovisa.model;

public class RowType_AddServData  implements java.io.Serializable {
    private java.lang.String SERV_TYPE_ID;

    private java.lang.String SERV_NUMBER;

    private java.util.Calendar EXPIRY;

    private java.lang.String RENEW;

    public RowType_AddServData() {
    }

    public RowType_AddServData(
           java.lang.String SERV_TYPE_ID,
           java.lang.String SERV_NUMBER,
           java.util.Calendar EXPIRY,
           java.lang.String RENEW) {
           this.SERV_TYPE_ID = SERV_TYPE_ID;
           this.SERV_NUMBER = SERV_NUMBER;
           this.EXPIRY = EXPIRY;
           this.RENEW = RENEW;
    }


    /**
     * Gets the SERV_TYPE_ID value for this RowType_AddServData.
     * 
     * @return SERV_TYPE_ID
     */
    public java.lang.String getSERV_TYPE_ID() {
        return SERV_TYPE_ID;
    }


    /**
     * Sets the SERV_TYPE_ID value for this RowType_AddServData.
     * 
     * @param SERV_TYPE_ID
     */
    public void setSERV_TYPE_ID(java.lang.String SERV_TYPE_ID) {
        this.SERV_TYPE_ID = SERV_TYPE_ID;
    }


    /**
     * Gets the SERV_NUMBER value for this RowType_AddServData.
     * 
     * @return SERV_NUMBER
     */
    public java.lang.String getSERV_NUMBER() {
        return SERV_NUMBER;
    }


    /**
     * Sets the SERV_NUMBER value for this RowType_AddServData.
     * 
     * @param SERV_NUMBER
     */
    public void setSERV_NUMBER(java.lang.String SERV_NUMBER) {
        this.SERV_NUMBER = SERV_NUMBER;
    }


    /**
     * Gets the EXPIRY value for this RowType_AddServData.
     * 
     * @return EXPIRY
     */
    public java.util.Calendar getEXPIRY() {
        return EXPIRY;
    }


    /**
     * Sets the EXPIRY value for this RowType_AddServData.
     * 
     * @param EXPIRY
     */
    public void setEXPIRY(java.util.Calendar EXPIRY) {
        this.EXPIRY = EXPIRY;
    }


    /**
     * Gets the RENEW value for this RowType_AddServData.
     * 
     * @return RENEW
     */
    public java.lang.String getRENEW() {
        return RENEW;
    }


    /**
     * Sets the RENEW value for this RowType_AddServData.
     * 
     * @param RENEW
     */
    public void setRENEW(java.lang.String RENEW) {
        this.RENEW = RENEW;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RowType_AddServData)) return false;
        RowType_AddServData other = (RowType_AddServData) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.SERV_TYPE_ID==null && other.getSERV_TYPE_ID()==null) || 
             (this.SERV_TYPE_ID!=null &&
              this.SERV_TYPE_ID.equals(other.getSERV_TYPE_ID()))) &&
            ((this.SERV_NUMBER==null && other.getSERV_NUMBER()==null) || 
             (this.SERV_NUMBER!=null &&
              this.SERV_NUMBER.equals(other.getSERV_NUMBER()))) &&
            ((this.EXPIRY==null && other.getEXPIRY()==null) || 
             (this.EXPIRY!=null &&
              this.EXPIRY.equals(other.getEXPIRY()))) &&
            ((this.RENEW==null && other.getRENEW()==null) || 
             (this.RENEW!=null &&
              this.RENEW.equals(other.getRENEW())));
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
        if (getSERV_TYPE_ID() != null) {
            _hashCode += getSERV_TYPE_ID().hashCode();
        }
        if (getSERV_NUMBER() != null) {
            _hashCode += getSERV_NUMBER().hashCode();
        }
        if (getEXPIRY() != null) {
            _hashCode += getEXPIRY().hashCode();
        }
        if (getRENEW() != null) {
            _hashCode += getRENEW().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }


}
