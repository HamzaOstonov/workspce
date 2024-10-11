/**
 * RowType_LimitBalance.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package globus.issuing_v_01_02_xsd;

public class RowType_LimitBalance  implements java.io.Serializable {
    private java.math.BigDecimal limit_value;

    private java.math.BigDecimal limit_balance_value;

    private java.lang.String limit_name;

    private java.lang.String limit_date_from;

    private java.lang.String limit_date_to;

    public RowType_LimitBalance() {
    }

    public RowType_LimitBalance(
           java.math.BigDecimal limit_value,
           java.math.BigDecimal limit_balance_value,
           java.lang.String limit_name,
           java.lang.String limit_date_from,
           java.lang.String limit_date_to) {
           this.limit_value = limit_value;
           this.limit_balance_value = limit_balance_value;
           this.limit_name = limit_name;
           this.limit_date_from = limit_date_from;
           this.limit_date_to = limit_date_to;
    }


    /**
     * Gets the limit_value value for this RowType_LimitBalance.
     * 
     * @return limit_value
     */
    public java.math.BigDecimal getLimit_value() {
        return limit_value;
    }


    /**
     * Sets the limit_value value for this RowType_LimitBalance.
     * 
     * @param limit_value
     */
    public void setLimit_value(java.math.BigDecimal limit_value) {
        this.limit_value = limit_value;
    }


    /**
     * Gets the limit_balance_value value for this RowType_LimitBalance.
     * 
     * @return limit_balance_value
     */
    public java.math.BigDecimal getLimit_balance_value() {
        return limit_balance_value;
    }


    /**
     * Sets the limit_balance_value value for this RowType_LimitBalance.
     * 
     * @param limit_balance_value
     */
    public void setLimit_balance_value(java.math.BigDecimal limit_balance_value) {
        this.limit_balance_value = limit_balance_value;
    }


    /**
     * Gets the limit_name value for this RowType_LimitBalance.
     * 
     * @return limit_name
     */
    public java.lang.String getLimit_name() {
        return limit_name;
    }


    /**
     * Sets the limit_name value for this RowType_LimitBalance.
     * 
     * @param limit_name
     */
    public void setLimit_name(java.lang.String limit_name) {
        this.limit_name = limit_name;
    }


    /**
     * Gets the limit_date_from value for this RowType_LimitBalance.
     * 
     * @return limit_date_from
     */
    public java.lang.String getLimit_date_from() {
        return limit_date_from;
    }


    /**
     * Sets the limit_date_from value for this RowType_LimitBalance.
     * 
     * @param limit_date_from
     */
    public void setLimit_date_from(java.lang.String limit_date_from) {
        this.limit_date_from = limit_date_from;
    }


    /**
     * Gets the limit_date_to value for this RowType_LimitBalance.
     * 
     * @return limit_date_to
     */
    public java.lang.String getLimit_date_to() {
        return limit_date_to;
    }


    /**
     * Sets the limit_date_to value for this RowType_LimitBalance.
     * 
     * @param limit_date_to
     */
    public void setLimit_date_to(java.lang.String limit_date_to) {
        this.limit_date_to = limit_date_to;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RowType_LimitBalance)) return false;
        RowType_LimitBalance other = (RowType_LimitBalance) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.limit_value==null && other.getLimit_value()==null) || 
             (this.limit_value!=null &&
              this.limit_value.equals(other.getLimit_value()))) &&
            ((this.limit_balance_value==null && other.getLimit_balance_value()==null) || 
             (this.limit_balance_value!=null &&
              this.limit_balance_value.equals(other.getLimit_balance_value()))) &&
            ((this.limit_name==null && other.getLimit_name()==null) || 
             (this.limit_name!=null &&
              this.limit_name.equals(other.getLimit_name()))) &&
            ((this.limit_date_from==null && other.getLimit_date_from()==null) || 
             (this.limit_date_from!=null &&
              this.limit_date_from.equals(other.getLimit_date_from()))) &&
            ((this.limit_date_to==null && other.getLimit_date_to()==null) || 
             (this.limit_date_to!=null &&
              this.limit_date_to.equals(other.getLimit_date_to())));
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
        if (getLimit_value() != null) {
            _hashCode += getLimit_value().hashCode();
        }
        if (getLimit_balance_value() != null) {
            _hashCode += getLimit_balance_value().hashCode();
        }
        if (getLimit_name() != null) {
            _hashCode += getLimit_name().hashCode();
        }
        if (getLimit_date_from() != null) {
            _hashCode += getLimit_date_from().hashCode();
        }
        if (getLimit_date_to() != null) {
            _hashCode += getLimit_date_to().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RowType_LimitBalance.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_LimitBalance"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("limit_value");
        elemField.setXmlName(new javax.xml.namespace.QName("", "limit_value"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("limit_balance_value");
        elemField.setXmlName(new javax.xml.namespace.QName("", "limit_balance_value"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("limit_name");
        elemField.setXmlName(new javax.xml.namespace.QName("", "limit_name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("limit_date_from");
        elemField.setXmlName(new javax.xml.namespace.QName("", "limit_date_from"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("limit_date_to");
        elemField.setXmlName(new javax.xml.namespace.QName("", "limit_date_to"));
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
