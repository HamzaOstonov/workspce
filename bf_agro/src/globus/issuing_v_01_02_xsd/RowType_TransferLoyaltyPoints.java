/**
 * RowType_TransferLoyaltyPoints.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package globus.issuing_v_01_02_xsd;

public class RowType_TransferLoyaltyPoints  implements java.io.Serializable {
    private java.lang.String LS_CODE;

    private java.math.BigDecimal KEY_TYPE_SOURCE;

    private java.lang.String KEY_VALUE_SOURCE;

    private java.math.BigDecimal KEY_TYPE_TARGET;

    private java.lang.String KEY_VALUE_TARGET;

    private java.math.BigDecimal AMOUNT;

    public RowType_TransferLoyaltyPoints() {
    }

    public RowType_TransferLoyaltyPoints(
           java.lang.String LS_CODE,
           java.math.BigDecimal KEY_TYPE_SOURCE,
           java.lang.String KEY_VALUE_SOURCE,
           java.math.BigDecimal KEY_TYPE_TARGET,
           java.lang.String KEY_VALUE_TARGET,
           java.math.BigDecimal AMOUNT) {
           this.LS_CODE = LS_CODE;
           this.KEY_TYPE_SOURCE = KEY_TYPE_SOURCE;
           this.KEY_VALUE_SOURCE = KEY_VALUE_SOURCE;
           this.KEY_TYPE_TARGET = KEY_TYPE_TARGET;
           this.KEY_VALUE_TARGET = KEY_VALUE_TARGET;
           this.AMOUNT = AMOUNT;
    }


    /**
     * Gets the LS_CODE value for this RowType_TransferLoyaltyPoints.
     * 
     * @return LS_CODE
     */
    public java.lang.String getLS_CODE() {
        return LS_CODE;
    }


    /**
     * Sets the LS_CODE value for this RowType_TransferLoyaltyPoints.
     * 
     * @param LS_CODE
     */
    public void setLS_CODE(java.lang.String LS_CODE) {
        this.LS_CODE = LS_CODE;
    }


    /**
     * Gets the KEY_TYPE_SOURCE value for this RowType_TransferLoyaltyPoints.
     * 
     * @return KEY_TYPE_SOURCE
     */
    public java.math.BigDecimal getKEY_TYPE_SOURCE() {
        return KEY_TYPE_SOURCE;
    }


    /**
     * Sets the KEY_TYPE_SOURCE value for this RowType_TransferLoyaltyPoints.
     * 
     * @param KEY_TYPE_SOURCE
     */
    public void setKEY_TYPE_SOURCE(java.math.BigDecimal KEY_TYPE_SOURCE) {
        this.KEY_TYPE_SOURCE = KEY_TYPE_SOURCE;
    }


    /**
     * Gets the KEY_VALUE_SOURCE value for this RowType_TransferLoyaltyPoints.
     * 
     * @return KEY_VALUE_SOURCE
     */
    public java.lang.String getKEY_VALUE_SOURCE() {
        return KEY_VALUE_SOURCE;
    }


    /**
     * Sets the KEY_VALUE_SOURCE value for this RowType_TransferLoyaltyPoints.
     * 
     * @param KEY_VALUE_SOURCE
     */
    public void setKEY_VALUE_SOURCE(java.lang.String KEY_VALUE_SOURCE) {
        this.KEY_VALUE_SOURCE = KEY_VALUE_SOURCE;
    }


    /**
     * Gets the KEY_TYPE_TARGET value for this RowType_TransferLoyaltyPoints.
     * 
     * @return KEY_TYPE_TARGET
     */
    public java.math.BigDecimal getKEY_TYPE_TARGET() {
        return KEY_TYPE_TARGET;
    }


    /**
     * Sets the KEY_TYPE_TARGET value for this RowType_TransferLoyaltyPoints.
     * 
     * @param KEY_TYPE_TARGET
     */
    public void setKEY_TYPE_TARGET(java.math.BigDecimal KEY_TYPE_TARGET) {
        this.KEY_TYPE_TARGET = KEY_TYPE_TARGET;
    }


    /**
     * Gets the KEY_VALUE_TARGET value for this RowType_TransferLoyaltyPoints.
     * 
     * @return KEY_VALUE_TARGET
     */
    public java.lang.String getKEY_VALUE_TARGET() {
        return KEY_VALUE_TARGET;
    }


    /**
     * Sets the KEY_VALUE_TARGET value for this RowType_TransferLoyaltyPoints.
     * 
     * @param KEY_VALUE_TARGET
     */
    public void setKEY_VALUE_TARGET(java.lang.String KEY_VALUE_TARGET) {
        this.KEY_VALUE_TARGET = KEY_VALUE_TARGET;
    }


    /**
     * Gets the AMOUNT value for this RowType_TransferLoyaltyPoints.
     * 
     * @return AMOUNT
     */
    public java.math.BigDecimal getAMOUNT() {
        return AMOUNT;
    }


    /**
     * Sets the AMOUNT value for this RowType_TransferLoyaltyPoints.
     * 
     * @param AMOUNT
     */
    public void setAMOUNT(java.math.BigDecimal AMOUNT) {
        this.AMOUNT = AMOUNT;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RowType_TransferLoyaltyPoints)) return false;
        RowType_TransferLoyaltyPoints other = (RowType_TransferLoyaltyPoints) obj;
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
            ((this.KEY_TYPE_SOURCE==null && other.getKEY_TYPE_SOURCE()==null) || 
             (this.KEY_TYPE_SOURCE!=null &&
              this.KEY_TYPE_SOURCE.equals(other.getKEY_TYPE_SOURCE()))) &&
            ((this.KEY_VALUE_SOURCE==null && other.getKEY_VALUE_SOURCE()==null) || 
             (this.KEY_VALUE_SOURCE!=null &&
              this.KEY_VALUE_SOURCE.equals(other.getKEY_VALUE_SOURCE()))) &&
            ((this.KEY_TYPE_TARGET==null && other.getKEY_TYPE_TARGET()==null) || 
             (this.KEY_TYPE_TARGET!=null &&
              this.KEY_TYPE_TARGET.equals(other.getKEY_TYPE_TARGET()))) &&
            ((this.KEY_VALUE_TARGET==null && other.getKEY_VALUE_TARGET()==null) || 
             (this.KEY_VALUE_TARGET!=null &&
              this.KEY_VALUE_TARGET.equals(other.getKEY_VALUE_TARGET()))) &&
            ((this.AMOUNT==null && other.getAMOUNT()==null) || 
             (this.AMOUNT!=null &&
              this.AMOUNT.equals(other.getAMOUNT())));
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
        if (getKEY_TYPE_SOURCE() != null) {
            _hashCode += getKEY_TYPE_SOURCE().hashCode();
        }
        if (getKEY_VALUE_SOURCE() != null) {
            _hashCode += getKEY_VALUE_SOURCE().hashCode();
        }
        if (getKEY_TYPE_TARGET() != null) {
            _hashCode += getKEY_TYPE_TARGET().hashCode();
        }
        if (getKEY_VALUE_TARGET() != null) {
            _hashCode += getKEY_VALUE_TARGET().hashCode();
        }
        if (getAMOUNT() != null) {
            _hashCode += getAMOUNT().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RowType_TransferLoyaltyPoints.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_TransferLoyaltyPoints"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("LS_CODE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "LS_CODE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("KEY_TYPE_SOURCE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "KEY_TYPE_SOURCE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("KEY_VALUE_SOURCE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "KEY_VALUE_SOURCE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("KEY_TYPE_TARGET");
        elemField.setXmlName(new javax.xml.namespace.QName("", "KEY_TYPE_TARGET"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("KEY_VALUE_TARGET");
        elemField.setXmlName(new javax.xml.namespace.QName("", "KEY_VALUE_TARGET"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("AMOUNT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AMOUNT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
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
