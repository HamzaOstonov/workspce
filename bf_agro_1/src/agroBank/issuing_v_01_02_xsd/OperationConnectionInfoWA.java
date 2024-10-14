/**
 * OperationConnectionInfoWA.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package agroBank.issuing_v_01_02_xsd;

public class OperationConnectionInfoWA  implements java.io.Serializable {
    private java.lang.String BANK_C;

    private java.lang.String GROUPC;

    private java.math.BigDecimal FAULT_MODE;

    private java.lang.String EXTERNAL_SESSION_ID;

    public OperationConnectionInfoWA() {
    }

    public OperationConnectionInfoWA(
           java.lang.String BANK_C,
           java.lang.String GROUPC,
           java.math.BigDecimal FAULT_MODE,
           java.lang.String EXTERNAL_SESSION_ID) {
           this.BANK_C = BANK_C;
           this.GROUPC = GROUPC;
           this.FAULT_MODE = FAULT_MODE;
           this.EXTERNAL_SESSION_ID = EXTERNAL_SESSION_ID;
    }


    /**
     * Gets the BANK_C value for this OperationConnectionInfoWA.
     * 
     * @return BANK_C
     */
    public java.lang.String getBANK_C() {
        return BANK_C;
    }


    /**
     * Sets the BANK_C value for this OperationConnectionInfoWA.
     * 
     * @param BANK_C
     */
    public void setBANK_C(java.lang.String BANK_C) {
        this.BANK_C = BANK_C;
    }


    /**
     * Gets the GROUPC value for this OperationConnectionInfoWA.
     * 
     * @return GROUPC
     */
    public java.lang.String getGROUPC() {
        return GROUPC;
    }


    /**
     * Sets the GROUPC value for this OperationConnectionInfoWA.
     * 
     * @param GROUPC
     */
    public void setGROUPC(java.lang.String GROUPC) {
        this.GROUPC = GROUPC;
    }


    /**
     * Gets the FAULT_MODE value for this OperationConnectionInfoWA.
     * 
     * @return FAULT_MODE
     */
    public java.math.BigDecimal getFAULT_MODE() {
        return FAULT_MODE;
    }


    /**
     * Sets the FAULT_MODE value for this OperationConnectionInfoWA.
     * 
     * @param FAULT_MODE
     */
    public void setFAULT_MODE(java.math.BigDecimal FAULT_MODE) {
        this.FAULT_MODE = FAULT_MODE;
    }


    /**
     * Gets the EXTERNAL_SESSION_ID value for this OperationConnectionInfoWA.
     * 
     * @return EXTERNAL_SESSION_ID
     */
    public java.lang.String getEXTERNAL_SESSION_ID() {
        return EXTERNAL_SESSION_ID;
    }


    /**
     * Sets the EXTERNAL_SESSION_ID value for this OperationConnectionInfoWA.
     * 
     * @param EXTERNAL_SESSION_ID
     */
    public void setEXTERNAL_SESSION_ID(java.lang.String EXTERNAL_SESSION_ID) {
        this.EXTERNAL_SESSION_ID = EXTERNAL_SESSION_ID;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof OperationConnectionInfoWA)) return false;
        OperationConnectionInfoWA other = (OperationConnectionInfoWA) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.BANK_C==null && other.getBANK_C()==null) || 
             (this.BANK_C!=null &&
              this.BANK_C.equals(other.getBANK_C()))) &&
            ((this.GROUPC==null && other.getGROUPC()==null) || 
             (this.GROUPC!=null &&
              this.GROUPC.equals(other.getGROUPC()))) &&
            ((this.FAULT_MODE==null && other.getFAULT_MODE()==null) || 
             (this.FAULT_MODE!=null &&
              this.FAULT_MODE.equals(other.getFAULT_MODE()))) &&
            ((this.EXTERNAL_SESSION_ID==null && other.getEXTERNAL_SESSION_ID()==null) || 
             (this.EXTERNAL_SESSION_ID!=null &&
              this.EXTERNAL_SESSION_ID.equals(other.getEXTERNAL_SESSION_ID())));
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
        if (getBANK_C() != null) {
            _hashCode += getBANK_C().hashCode();
        }
        if (getGROUPC() != null) {
            _hashCode += getGROUPC().hashCode();
        }
        if (getFAULT_MODE() != null) {
            _hashCode += getFAULT_MODE().hashCode();
        }
        if (getEXTERNAL_SESSION_ID() != null) {
            _hashCode += getEXTERNAL_SESSION_ID().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(OperationConnectionInfoWA.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfoWA"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("BANK_C");
        elemField.setXmlName(new javax.xml.namespace.QName("", "BANK_C"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("GROUPC");
        elemField.setXmlName(new javax.xml.namespace.QName("", "GROUPC"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FAULT_MODE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "FAULT_MODE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("EXTERNAL_SESSION_ID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "EXTERNAL_SESSION_ID"));
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
