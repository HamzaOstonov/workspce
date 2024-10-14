/**
 * RowType_EventBalanceResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package visa.issuing_v_01_02_xsd;

public class RowType_EventBalanceResponse  implements java.io.Serializable {
    private java.lang.String code_owner;

    private java.lang.String limit_type;

    private java.lang.String limit_key_type;

    private java.lang.String limit_key_value;

    private visa.issuing_v_01_02_xsd.RowType_LimitBalance limit_balance;

    public RowType_EventBalanceResponse() {
    }

    public RowType_EventBalanceResponse(
           java.lang.String code_owner,
           java.lang.String limit_type,
           java.lang.String limit_key_type,
           java.lang.String limit_key_value,
           visa.issuing_v_01_02_xsd.RowType_LimitBalance limit_balance) {
           this.code_owner = code_owner;
           this.limit_type = limit_type;
           this.limit_key_type = limit_key_type;
           this.limit_key_value = limit_key_value;
           this.limit_balance = limit_balance;
    }


    /**
     * Gets the code_owner value for this RowType_EventBalanceResponse.
     * 
     * @return code_owner
     */
    public java.lang.String getCode_owner() {
        return code_owner;
    }


    /**
     * Sets the code_owner value for this RowType_EventBalanceResponse.
     * 
     * @param code_owner
     */
    public void setCode_owner(java.lang.String code_owner) {
        this.code_owner = code_owner;
    }


    /**
     * Gets the limit_type value for this RowType_EventBalanceResponse.
     * 
     * @return limit_type
     */
    public java.lang.String getLimit_type() {
        return limit_type;
    }


    /**
     * Sets the limit_type value for this RowType_EventBalanceResponse.
     * 
     * @param limit_type
     */
    public void setLimit_type(java.lang.String limit_type) {
        this.limit_type = limit_type;
    }


    /**
     * Gets the limit_key_type value for this RowType_EventBalanceResponse.
     * 
     * @return limit_key_type
     */
    public java.lang.String getLimit_key_type() {
        return limit_key_type;
    }


    /**
     * Sets the limit_key_type value for this RowType_EventBalanceResponse.
     * 
     * @param limit_key_type
     */
    public void setLimit_key_type(java.lang.String limit_key_type) {
        this.limit_key_type = limit_key_type;
    }


    /**
     * Gets the limit_key_value value for this RowType_EventBalanceResponse.
     * 
     * @return limit_key_value
     */
    public java.lang.String getLimit_key_value() {
        return limit_key_value;
    }


    /**
     * Sets the limit_key_value value for this RowType_EventBalanceResponse.
     * 
     * @param limit_key_value
     */
    public void setLimit_key_value(java.lang.String limit_key_value) {
        this.limit_key_value = limit_key_value;
    }


    /**
     * Gets the limit_balance value for this RowType_EventBalanceResponse.
     * 
     * @return limit_balance
     */
    public visa.issuing_v_01_02_xsd.RowType_LimitBalance getLimit_balance() {
        return limit_balance;
    }


    /**
     * Sets the limit_balance value for this RowType_EventBalanceResponse.
     * 
     * @param limit_balance
     */
    public void setLimit_balance(visa.issuing_v_01_02_xsd.RowType_LimitBalance limit_balance) {
        this.limit_balance = limit_balance;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RowType_EventBalanceResponse)) return false;
        RowType_EventBalanceResponse other = (RowType_EventBalanceResponse) obj;
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
              this.limit_key_value.equals(other.getLimit_key_value()))) &&
            ((this.limit_balance==null && other.getLimit_balance()==null) || 
             (this.limit_balance!=null &&
              this.limit_balance.equals(other.getLimit_balance())));
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
        if (getLimit_balance() != null) {
            _hashCode += getLimit_balance().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RowType_EventBalanceResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_EventBalanceResponse"));
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("limit_balance");
        elemField.setXmlName(new javax.xml.namespace.QName("", "limit_balance"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_LimitBalance"));
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
