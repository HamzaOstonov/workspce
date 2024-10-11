package com.is.tietovisa.model;

public class RowType_CardInfo_TSM_Data  implements java.io.Serializable {
    private java.lang.String TSM_CLIENT_ID;

    private java.lang.String TSM_AUTH_CODE;

    private java.util.Calendar TSM_AUTH_EXPIRY;

    public RowType_CardInfo_TSM_Data() {
    }

    public RowType_CardInfo_TSM_Data(
           java.lang.String TSM_CLIENT_ID,
           java.lang.String TSM_AUTH_CODE,
           java.util.Calendar TSM_AUTH_EXPIRY) {
           this.TSM_CLIENT_ID = TSM_CLIENT_ID;
           this.TSM_AUTH_CODE = TSM_AUTH_CODE;
           this.TSM_AUTH_EXPIRY = TSM_AUTH_EXPIRY;
    }


    /**
     * Gets the TSM_CLIENT_ID value for this RowType_CardInfo_TSM_Data.
     * 
     * @return TSM_CLIENT_ID
     */
    public java.lang.String getTSM_CLIENT_ID() {
        return TSM_CLIENT_ID;
    }


    /**
     * Sets the TSM_CLIENT_ID value for this RowType_CardInfo_TSM_Data.
     * 
     * @param TSM_CLIENT_ID
     */
    public void setTSM_CLIENT_ID(java.lang.String TSM_CLIENT_ID) {
        this.TSM_CLIENT_ID = TSM_CLIENT_ID;
    }


    /**
     * Gets the TSM_AUTH_CODE value for this RowType_CardInfo_TSM_Data.
     * 
     * @return TSM_AUTH_CODE
     */
    public java.lang.String getTSM_AUTH_CODE() {
        return TSM_AUTH_CODE;
    }


    /**
     * Sets the TSM_AUTH_CODE value for this RowType_CardInfo_TSM_Data.
     * 
     * @param TSM_AUTH_CODE
     */
    public void setTSM_AUTH_CODE(java.lang.String TSM_AUTH_CODE) {
        this.TSM_AUTH_CODE = TSM_AUTH_CODE;
    }


    /**
     * Gets the TSM_AUTH_EXPIRY value for this RowType_CardInfo_TSM_Data.
     * 
     * @return TSM_AUTH_EXPIRY
     */
    public java.util.Calendar getTSM_AUTH_EXPIRY() {
        return TSM_AUTH_EXPIRY;
    }


    /**
     * Sets the TSM_AUTH_EXPIRY value for this RowType_CardInfo_TSM_Data.
     * 
     * @param TSM_AUTH_EXPIRY
     */
    public void setTSM_AUTH_EXPIRY(java.util.Calendar TSM_AUTH_EXPIRY) {
        this.TSM_AUTH_EXPIRY = TSM_AUTH_EXPIRY;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RowType_CardInfo_TSM_Data)) return false;
        RowType_CardInfo_TSM_Data other = (RowType_CardInfo_TSM_Data) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.TSM_CLIENT_ID==null && other.getTSM_CLIENT_ID()==null) || 
             (this.TSM_CLIENT_ID!=null &&
              this.TSM_CLIENT_ID.equals(other.getTSM_CLIENT_ID()))) &&
            ((this.TSM_AUTH_CODE==null && other.getTSM_AUTH_CODE()==null) || 
             (this.TSM_AUTH_CODE!=null &&
              this.TSM_AUTH_CODE.equals(other.getTSM_AUTH_CODE()))) &&
            ((this.TSM_AUTH_EXPIRY==null && other.getTSM_AUTH_EXPIRY()==null) || 
             (this.TSM_AUTH_EXPIRY!=null &&
              this.TSM_AUTH_EXPIRY.equals(other.getTSM_AUTH_EXPIRY())));
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
        if (getTSM_CLIENT_ID() != null) {
            _hashCode += getTSM_CLIENT_ID().hashCode();
        }
        if (getTSM_AUTH_CODE() != null) {
            _hashCode += getTSM_AUTH_CODE().hashCode();
        }
        if (getTSM_AUTH_EXPIRY() != null) {
            _hashCode += getTSM_AUTH_EXPIRY().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

}
