/**
 * RowType_CustomDataOperationMerch.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package issuing_v_01_02_xsd;

public class RowType_CustomDataOperationMerch  implements java.io.Serializable {
    private java.lang.String OPERATION;

    private java.lang.String MERCHANT;

    private java.lang.String MCC;

    private java.lang.String PROC_CODE;

    public RowType_CustomDataOperationMerch() {
    }

    public RowType_CustomDataOperationMerch(
           java.lang.String OPERATION,
           java.lang.String MERCHANT,
           java.lang.String MCC,
           java.lang.String PROC_CODE) {
           this.OPERATION = OPERATION;
           this.MERCHANT = MERCHANT;
           this.MCC = MCC;
           this.PROC_CODE = PROC_CODE;
    }


    /**
     * Gets the OPERATION value for this RowType_CustomDataOperationMerch.
     * 
     * @return OPERATION
     */
    public java.lang.String getOPERATION() {
        return OPERATION;
    }


    /**
     * Sets the OPERATION value for this RowType_CustomDataOperationMerch.
     * 
     * @param OPERATION
     */
    public void setOPERATION(java.lang.String OPERATION) {
        this.OPERATION = OPERATION;
    }


    /**
     * Gets the MERCHANT value for this RowType_CustomDataOperationMerch.
     * 
     * @return MERCHANT
     */
    public java.lang.String getMERCHANT() {
        return MERCHANT;
    }


    /**
     * Sets the MERCHANT value for this RowType_CustomDataOperationMerch.
     * 
     * @param MERCHANT
     */
    public void setMERCHANT(java.lang.String MERCHANT) {
        this.MERCHANT = MERCHANT;
    }


    /**
     * Gets the MCC value for this RowType_CustomDataOperationMerch.
     * 
     * @return MCC
     */
    public java.lang.String getMCC() {
        return MCC;
    }


    /**
     * Sets the MCC value for this RowType_CustomDataOperationMerch.
     * 
     * @param MCC
     */
    public void setMCC(java.lang.String MCC) {
        this.MCC = MCC;
    }


    /**
     * Gets the PROC_CODE value for this RowType_CustomDataOperationMerch.
     * 
     * @return PROC_CODE
     */
    public java.lang.String getPROC_CODE() {
        return PROC_CODE;
    }


    /**
     * Sets the PROC_CODE value for this RowType_CustomDataOperationMerch.
     * 
     * @param PROC_CODE
     */
    public void setPROC_CODE(java.lang.String PROC_CODE) {
        this.PROC_CODE = PROC_CODE;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RowType_CustomDataOperationMerch)) return false;
        RowType_CustomDataOperationMerch other = (RowType_CustomDataOperationMerch) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.OPERATION==null && other.getOPERATION()==null) || 
             (this.OPERATION!=null &&
              this.OPERATION.equals(other.getOPERATION()))) &&
            ((this.MERCHANT==null && other.getMERCHANT()==null) || 
             (this.MERCHANT!=null &&
              this.MERCHANT.equals(other.getMERCHANT()))) &&
            ((this.MCC==null && other.getMCC()==null) || 
             (this.MCC!=null &&
              this.MCC.equals(other.getMCC()))) &&
            ((this.PROC_CODE==null && other.getPROC_CODE()==null) || 
             (this.PROC_CODE!=null &&
              this.PROC_CODE.equals(other.getPROC_CODE())));
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
        if (getOPERATION() != null) {
            _hashCode += getOPERATION().hashCode();
        }
        if (getMERCHANT() != null) {
            _hashCode += getMERCHANT().hashCode();
        }
        if (getMCC() != null) {
            _hashCode += getMCC().hashCode();
        }
        if (getPROC_CODE() != null) {
            _hashCode += getPROC_CODE().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RowType_CustomDataOperationMerch.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_CustomDataOperationMerch"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("OPERATION");
        elemField.setXmlName(new javax.xml.namespace.QName("", "OPERATION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MERCHANT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MERCHANT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MCC");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MCC"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PROC_CODE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PROC_CODE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
