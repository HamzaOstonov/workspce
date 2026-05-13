/**
 * RowType_EventBalanceRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package visa.issuing_v_01_02_xsd;

public class RowType_EventBalanceRequest  implements java.io.Serializable {
    private java.lang.String code_owner;

    private java.lang.String limit_type;

    private java.lang.String limit_key_type;

    private java.lang.String limit_key_value;

    public RowType_EventBalanceRequest() {
    }

    public RowType_EventBalanceRequest(
           java.lang.String code_owner,
           java.lang.String limit_type,
           java.lang.String limit_key_type,
           java.lang.String limit_key_value) {
           this.code_owner = code_owner;
           this.limit_type = limit_type;
           this.limit_key_type = limit_key_type;
           this.limit_key_value = limit_key_value;
    }


    /**
     * Gets the code_owner value for this RowType_EventBalanceRequest.
     * 
     * @return code_owner
     */
    public java.lang.String getCode_owner() {
        return code_owner;
    }


    /**
     * Sets the code_owner value for this RowType_EventBalanceRequest.
     * 
     * @param code_owner
     */
    public void setCode_owner(java.lang.String code_owner) {
        this.code_owner = code_owner;
    }


    /**
     * Gets the limit_type value for this RowType_EventBalanceRequest.
     * 
     * @return limit_type
     */
    public java.lang.String getLimit_type() {
        return limit_type;
    }


    /**
     * Sets the limit_type value for this RowType_EventBalanceRequest.
     * 
     * @param limit_type
     */
    public void setLimit_type(java.lang.String limit_type) {
        this.limit_type = limit_type;
    }


    /**
     * Gets the limit_key_type value for this RowType_EventBalanceRequest.
     * 
     * @return limit_key_type
     */
    public java.lang.String getLimit_key_type() {
        return limit_key_type;
    }


    /**
     * Sets the limit_key_type value for this RowType_EventBalanceRequest.
     * 
     * @param limit_key_type
     */
    public void setLimit_key_type(java.lang.String limit_key_type) {
        this.limit_key_type = limit_key_type;
    }


    /**
     * Gets the limit_key_value value for this RowType_EventBalanceRequest.
     * 
     * @return limit_key_value
     */
    public java.lang.String getLimit_key_value() {
        return limit_key_value;
    }


    /**
     * Sets the limit_key_value value for this RowType_EventBalanceRequest.
     * 
     * @param limit_key_value
     */
    public void setLimit_key_value(java.lang.String limit_key_value) {
        this.limit_key_value = limit_key_value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RowType_EventBalanceRequest)) return false;
        RowType_EventBalanceRequest other = (RowType_EventBalanceRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.code_owner==null && other.getCode_owner()==null) || 
             (this.code_owner!=null &&
              this.code_owner.equals(other.getCode_owner()))) &&
            ((this.limit_type==null && other.getLimit_type()==null) || 
             (this.limit_type!=null &&
              this.limit_type.equals(other.getLimit_type()))) &&
            ((this.limit_key_type==null && other.getLimit_key_type()==null) || 
             (this.limit_key_type!=null &&
              this.limit_key_type.equals(other.getLimit_key_type()))) &&
            ((this.limit_key_value==null && other.getLimit_key_value()==null) || 
             (this.limit_key_value!=null &&
              this.limit_key_value.equals(other.getLimit_key_value())));
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
        if (getCode_owner() != null) {
            _hashCode += getCode_owner().hashCode();
        }
        if (getLimit_type() != null) {
            _hashCode += getLimit_type().hashCode();
        }
        if (getLimit_key_type() != null) {
            _hashCode += getLimit_key_type().hashCode();
        }
        if (getLimit_key_value() != null) {
            _hashCode += getLimit_key_value().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RowType_EventBalanceRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_EventBalanceRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("code_owner");
        elemField.setXmlName(new javax.xml.namespace.QName("", "code_owner"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("limit_type");
        elemField.setXmlName(new javax.xml.namespace.QName("", "limit_type"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("limit_key_type");
        elemField.setXmlName(new javax.xml.namespace.QName("", "limit_key_type"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("limit_key_value");
        elemField.setXmlName(new javax.xml.namespace.QName("", "limit_key_value"));
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
