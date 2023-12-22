/**
 * CompensationsResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sbs.service;

public class CompensationsResult  extends com.sbs.service.Result  implements java.io.Serializable {
    private com.sbs.service.Compensation[] compensations;

    public CompensationsResult() {
    }

    public CompensationsResult(
           java.lang.String errorMsg,
           int gtkId,
           int status,
           java.util.Calendar timeStamp,
           com.sbs.service.Compensation[] compensations) {
        super(
            errorMsg,
            gtkId,
            status,
            timeStamp);
        this.compensations = compensations;
    }


    /**
     * Gets the compensations value for this CompensationsResult.
     * 
     * @return compensations
     */
    public com.sbs.service.Compensation[] getCompensations() {
        return compensations;
    }


    /**
     * Sets the compensations value for this CompensationsResult.
     * 
     * @param compensations
     */
    public void setCompensations(com.sbs.service.Compensation[] compensations) {
        this.compensations = compensations;
    }

    public com.sbs.service.Compensation getCompensations(int i) {
        return this.compensations[i];
    }

    public void setCompensations(int i, com.sbs.service.Compensation _value) {
        this.compensations[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CompensationsResult)) return false;
        CompensationsResult other = (CompensationsResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.compensations==null && other.getCompensations()==null) || 
             (this.compensations!=null &&
              java.util.Arrays.equals(this.compensations, other.getCompensations())));
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
        if (getCompensations() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCompensations());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCompensations(), i);
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
        new org.apache.axis.description.TypeDesc(CompensationsResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "compensationsResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("compensations");
        elemField.setXmlName(new javax.xml.namespace.QName("", "compensations"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "compensation"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return fileType metadata object
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
