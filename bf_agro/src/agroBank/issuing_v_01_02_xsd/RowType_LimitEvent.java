/**
 * RowType_LimitEvent.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package agroBank.issuing_v_01_02_xsd;

public class RowType_LimitEvent  implements java.io.Serializable {
    private java.lang.String KEY_TYPE;

    private java.lang.String CODE_OWNER;

    private java.lang.String KEY_VALUE;

    private agroBank.issuing_v_01_02_xsd.RowType_LimitEvent_Limit[] limit;

    public RowType_LimitEvent() {
    }

    public RowType_LimitEvent(
           java.lang.String KEY_TYPE,
           java.lang.String CODE_OWNER,
           java.lang.String KEY_VALUE,
           agroBank.issuing_v_01_02_xsd.RowType_LimitEvent_Limit[] limit) {
           this.KEY_TYPE = KEY_TYPE;
           this.CODE_OWNER = CODE_OWNER;
           this.KEY_VALUE = KEY_VALUE;
           this.limit = limit;
    }


    /**
     * Gets the KEY_TYPE value for this RowType_LimitEvent.
     * 
     * @return KEY_TYPE
     */
    public java.lang.String getKEY_TYPE() {
        return KEY_TYPE;
    }


    /**
     * Sets the KEY_TYPE value for this RowType_LimitEvent.
     * 
     * @param KEY_TYPE
     */
    public void setKEY_TYPE(java.lang.String KEY_TYPE) {
        this.KEY_TYPE = KEY_TYPE;
    }


    /**
     * Gets the CODE_OWNER value for this RowType_LimitEvent.
     * 
     * @return CODE_OWNER
     */
    public java.lang.String getCODE_OWNER() {
        return CODE_OWNER;
    }


    /**
     * Sets the CODE_OWNER value for this RowType_LimitEvent.
     * 
     * @param CODE_OWNER
     */
    public void setCODE_OWNER(java.lang.String CODE_OWNER) {
        this.CODE_OWNER = CODE_OWNER;
    }


    /**
     * Gets the KEY_VALUE value for this RowType_LimitEvent.
     * 
     * @return KEY_VALUE
     */
    public java.lang.String getKEY_VALUE() {
        return KEY_VALUE;
    }


    /**
     * Sets the KEY_VALUE value for this RowType_LimitEvent.
     * 
     * @param KEY_VALUE
     */
    public void setKEY_VALUE(java.lang.String KEY_VALUE) {
        this.KEY_VALUE = KEY_VALUE;
    }


    /**
     * Gets the limit value for this RowType_LimitEvent.
     * 
     * @return limit
     */
    public agroBank.issuing_v_01_02_xsd.RowType_LimitEvent_Limit[] getLimit() {
        return limit;
    }


    /**
     * Sets the limit value for this RowType_LimitEvent.
     * 
     * @param limit
     */
    public void setLimit(agroBank.issuing_v_01_02_xsd.RowType_LimitEvent_Limit[] limit) {
        this.limit = limit;
    }

    public agroBank.issuing_v_01_02_xsd.RowType_LimitEvent_Limit getLimit(int i) {
        return this.limit[i];
    }

    public void setLimit(int i, agroBank.issuing_v_01_02_xsd.RowType_LimitEvent_Limit _value) {
        this.limit[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RowType_LimitEvent)) return false;
        RowType_LimitEvent other = (RowType_LimitEvent) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.KEY_TYPE==null && other.getKEY_TYPE()==null) || 
             (this.KEY_TYPE!=null &&
              this.KEY_TYPE.equals(other.getKEY_TYPE()))) &&
            ((this.CODE_OWNER==null && other.getCODE_OWNER()==null) || 
             (this.CODE_OWNER!=null &&
              this.CODE_OWNER.equals(other.getCODE_OWNER()))) &&
            ((this.KEY_VALUE==null && other.getKEY_VALUE()==null) || 
             (this.KEY_VALUE!=null &&
              this.KEY_VALUE.equals(other.getKEY_VALUE()))) &&
            ((this.limit==null && other.getLimit()==null) || 
             (this.limit!=null &&
              java.util.Arrays.equals(this.limit, other.getLimit())));
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
        if (getKEY_TYPE() != null) {
            _hashCode += getKEY_TYPE().hashCode();
        }
        if (getCODE_OWNER() != null) {
            _hashCode += getCODE_OWNER().hashCode();
        }
        if (getKEY_VALUE() != null) {
            _hashCode += getKEY_VALUE().hashCode();
        }
        if (getLimit() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getLimit());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getLimit(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RowType_LimitEvent.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_LimitEvent"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("KEY_TYPE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "KEY_TYPE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CODE_OWNER");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CODE_OWNER"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("KEY_VALUE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "KEY_VALUE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("limit");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Limit"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_LimitEvent_Limit"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
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
