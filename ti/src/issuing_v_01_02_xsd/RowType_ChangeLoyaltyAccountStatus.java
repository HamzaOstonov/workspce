/**
 * RowType_ChangeLoyaltyAccountStatus.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package issuing_v_01_02_xsd;

public class RowType_ChangeLoyaltyAccountStatus  implements java.io.Serializable {
    private java.lang.String LS_CODE;

    private java.math.BigDecimal KEY_TYPE;

    private java.lang.String KEY_VALUE;

    private java.lang.String STATUS;

    public RowType_ChangeLoyaltyAccountStatus() {
    }

    public RowType_ChangeLoyaltyAccountStatus(
           java.lang.String LS_CODE,
           java.math.BigDecimal KEY_TYPE,
           java.lang.String KEY_VALUE,
           java.lang.String STATUS) {
           this.LS_CODE = LS_CODE;
           this.KEY_TYPE = KEY_TYPE;
           this.KEY_VALUE = KEY_VALUE;
           this.STATUS = STATUS;
    }


    /**
     * Gets the LS_CODE value for this RowType_ChangeLoyaltyAccountStatus.
     * 
     * @return LS_CODE
     */
    public java.lang.String getLS_CODE() {
        return LS_CODE;
    }


    /**
     * Sets the LS_CODE value for this RowType_ChangeLoyaltyAccountStatus.
     * 
     * @param LS_CODE
     */
    public void setLS_CODE(java.lang.String LS_CODE) {
        this.LS_CODE = LS_CODE;
    }


    /**
     * Gets the KEY_TYPE value for this RowType_ChangeLoyaltyAccountStatus.
     * 
     * @return KEY_TYPE
     */
    public java.math.BigDecimal getKEY_TYPE() {
        return KEY_TYPE;
    }


    /**
     * Sets the KEY_TYPE value for this RowType_ChangeLoyaltyAccountStatus.
     * 
     * @param KEY_TYPE
     */
    public void setKEY_TYPE(java.math.BigDecimal KEY_TYPE) {
        this.KEY_TYPE = KEY_TYPE;
    }


    /**
     * Gets the KEY_VALUE value for this RowType_ChangeLoyaltyAccountStatus.
     * 
     * @return KEY_VALUE
     */
    public java.lang.String getKEY_VALUE() {
        return KEY_VALUE;
    }


    /**
     * Sets the KEY_VALUE value for this RowType_ChangeLoyaltyAccountStatus.
     * 
     * @param KEY_VALUE
     */
    public void setKEY_VALUE(java.lang.String KEY_VALUE) {
        this.KEY_VALUE = KEY_VALUE;
    }


    /**
     * Gets the STATUS value for this RowType_ChangeLoyaltyAccountStatus.
     * 
     * @return STATUS
     */
    public java.lang.String getSTATUS() {
        return STATUS;
    }


    /**
     * Sets the STATUS value for this RowType_ChangeLoyaltyAccountStatus.
     * 
     * @param STATUS
     */
    public void setSTATUS(java.lang.String STATUS) {
        this.STATUS = STATUS;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RowType_ChangeLoyaltyAccountStatus)) return false;
        RowType_ChangeLoyaltyAccountStatus other = (RowType_ChangeLoyaltyAccountStatus) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.LS_CODE==null && other.getLS_CODE()==null) || 
             (this.LS_CODE!=null &&
              this.LS_CODE.equals(other.getLS_CODE()))) &&
            ((this.KEY_TYPE==null && other.getKEY_TYPE()==null) || 
             (this.KEY_TYPE!=null &&
              this.KEY_TYPE.equals(other.getKEY_TYPE()))) &&
            ((this.KEY_VALUE==null && other.getKEY_VALUE()==null) || 
             (this.KEY_VALUE!=null &&
              this.KEY_VALUE.equals(other.getKEY_VALUE()))) &&
            ((this.STATUS==null && other.getSTATUS()==null) || 
             (this.STATUS!=null &&
              this.STATUS.equals(other.getSTATUS())));
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
        if (getLS_CODE() != null) {
            _hashCode += getLS_CODE().hashCode();
        }
        if (getKEY_TYPE() != null) {
            _hashCode += getKEY_TYPE().hashCode();
        }
        if (getKEY_VALUE() != null) {
            _hashCode += getKEY_VALUE().hashCode();
        }
        if (getSTATUS() != null) {
            _hashCode += getSTATUS().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RowType_ChangeLoyaltyAccountStatus.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_ChangeLoyaltyAccountStatus"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("LS_CODE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "LS_CODE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("KEY_TYPE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "KEY_TYPE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("KEY_VALUE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "KEY_VALUE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("STATUS");
        elemField.setXmlName(new javax.xml.namespace.QName("", "STATUS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
