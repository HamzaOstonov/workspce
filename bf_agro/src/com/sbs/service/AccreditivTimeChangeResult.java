/**
 * AccreditivTimeChangeResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sbs.service;

public class AccreditivTimeChangeResult  extends com.sbs.service.Result  implements java.io.Serializable {
    private com.sbs.service.AccreditivTimeChange[] accreditivTimeChanges;

    public AccreditivTimeChangeResult() {
    }

    public AccreditivTimeChangeResult(
           java.lang.String errorMsg,
           int gtkId,
           int status,
           java.util.Calendar timeStamp,
           com.sbs.service.AccreditivTimeChange[] accreditivTimeChanges) {
        super(
            errorMsg,
            gtkId,
            status,
            timeStamp);
        this.accreditivTimeChanges = accreditivTimeChanges;
    }


    /**
     * Gets the accreditivTimeChanges value for this AccreditivTimeChangeResult.
     * 
     * @return accreditivTimeChanges
     */
    public com.sbs.service.AccreditivTimeChange[] getAccreditivTimeChanges() {
        return accreditivTimeChanges;
    }


    /**
     * Sets the accreditivTimeChanges value for this AccreditivTimeChangeResult.
     * 
     * @param accreditivTimeChanges
     */
    public void setAccreditivTimeChanges(com.sbs.service.AccreditivTimeChange[] accreditivTimeChanges) {
        this.accreditivTimeChanges = accreditivTimeChanges;
    }

    public com.sbs.service.AccreditivTimeChange getAccreditivTimeChanges(int i) {
        return this.accreditivTimeChanges[i];
    }

    public void setAccreditivTimeChanges(int i, com.sbs.service.AccreditivTimeChange _value) {
        this.accreditivTimeChanges[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AccreditivTimeChangeResult)) return false;
        AccreditivTimeChangeResult other = (AccreditivTimeChangeResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.accreditivTimeChanges==null && other.getAccreditivTimeChanges()==null) || 
             (this.accreditivTimeChanges!=null &&
              java.util.Arrays.equals(this.accreditivTimeChanges, other.getAccreditivTimeChanges())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        if (getAccreditivTimeChanges() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAccreditivTimeChanges());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAccreditivTimeChanges(), i);
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
        new org.apache.axis.description.TypeDesc(AccreditivTimeChangeResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "accreditivTimeChangeResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accreditivTimeChanges");
        elemField.setXmlName(new javax.xml.namespace.QName("", "accreditivTimeChanges"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "accreditivTimeChange"));
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
