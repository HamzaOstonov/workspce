/**
 * AccreditivSumChangeResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sbs.service;

public class AccreditivSumChangeResult  extends com.sbs.service.Result  implements java.io.Serializable {
    private com.sbs.service.AccreditivSumChange[] accreditivSumChanges;

    public AccreditivSumChangeResult() {
    }

    public AccreditivSumChangeResult(
           java.lang.String errorMsg,
           int gtkId,
           int status,
           java.util.Calendar timeStamp,
           com.sbs.service.AccreditivSumChange[] accreditivSumChanges) {
        super(
            errorMsg,
            gtkId,
            status,
            timeStamp);
        this.accreditivSumChanges = accreditivSumChanges;
    }


    /**
     * Gets the accreditivSumChanges value for this AccreditivSumChangeResult.
     * 
     * @return accreditivSumChanges
     */
    public com.sbs.service.AccreditivSumChange[] getAccreditivSumChanges() {
        return accreditivSumChanges;
    }


    /**
     * Sets the accreditivSumChanges value for this AccreditivSumChangeResult.
     * 
     * @param accreditivSumChanges
     */
    public void setAccreditivSumChanges(com.sbs.service.AccreditivSumChange[] accreditivSumChanges) {
        this.accreditivSumChanges = accreditivSumChanges;
    }

    public com.sbs.service.AccreditivSumChange getAccreditivSumChanges(int i) {
        return this.accreditivSumChanges[i];
    }

    public void setAccreditivSumChanges(int i, com.sbs.service.AccreditivSumChange _value) {
        this.accreditivSumChanges[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AccreditivSumChangeResult)) return false;
        AccreditivSumChangeResult other = (AccreditivSumChangeResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.accreditivSumChanges==null && other.getAccreditivSumChanges()==null) || 
             (this.accreditivSumChanges!=null &&
              java.util.Arrays.equals(this.accreditivSumChanges, other.getAccreditivSumChanges())));
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
        if (getAccreditivSumChanges() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAccreditivSumChanges());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAccreditivSumChanges(), i);
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
        new org.apache.axis.description.TypeDesc(AccreditivSumChangeResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "accreditivSumChangeResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accreditivSumChanges");
        elemField.setXmlName(new javax.xml.namespace.QName("", "accreditivSumChanges"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "accreditivSumChange"));
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
