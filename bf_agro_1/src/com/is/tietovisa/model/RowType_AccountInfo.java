package com.is.tietovisa.model;

public class RowType_AccountInfo  implements java.io.Serializable {
    private RowType_AccountInfo_Base base_info;

    private RowType_AccountInfo_Additional additional_info;

    public RowType_AccountInfo() {
    }

    public RowType_AccountInfo(
           RowType_AccountInfo_Base base_info,
           RowType_AccountInfo_Additional additional_info) {
           this.base_info = base_info;
           this.additional_info = additional_info;
    }


    /**
     * Gets the base_info value for this RowType_AccountInfo.
     * 
     * @return base_info
     */
    public RowType_AccountInfo_Base getBase_info() {
        return base_info;
    }


    /**
     * Sets the base_info value for this RowType_AccountInfo.
     * 
     * @param base_info
     */
    public void setBase_info(RowType_AccountInfo_Base base_info) {
        this.base_info = base_info;
    }


    /**
     * Gets the additional_info value for this RowType_AccountInfo.
     * 
     * @return additional_info
     */
    public RowType_AccountInfo_Additional getAdditional_info() {
        return additional_info;
    }


    /**
     * Sets the additional_info value for this RowType_AccountInfo.
     * 
     * @param additional_info
     */
    public void setAdditional_info(RowType_AccountInfo_Additional additional_info) {
        this.additional_info = additional_info;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RowType_AccountInfo)) return false;
        RowType_AccountInfo other = (RowType_AccountInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.base_info==null && other.getBase_info()==null) || 
             (this.base_info!=null &&
              this.base_info.equals(other.getBase_info()))) &&
            ((this.additional_info==null && other.getAdditional_info()==null) || 
             (this.additional_info!=null &&
              this.additional_info.equals(other.getAdditional_info())));
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
        if (getBase_info() != null) {
            _hashCode += getBase_info().hashCode();
        }
        if (getAdditional_info() != null) {
            _hashCode += getAdditional_info().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    
}
