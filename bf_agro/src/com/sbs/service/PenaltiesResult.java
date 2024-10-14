/**
 * PenaltiesResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sbs.service;

public class PenaltiesResult  extends com.sbs.service.Result  implements java.io.Serializable {
    private com.sbs.service.Penalty[] penalties;

    public PenaltiesResult() {
    }

    public PenaltiesResult(
           java.lang.String errorMsg,
           int gtkId,
           int status,
           java.util.Calendar timeStamp,
           com.sbs.service.Penalty[] penalties) {
        super(
            errorMsg,
            gtkId,
            status,
            timeStamp);
        this.penalties = penalties;
    }


    /**
     * Gets the penalties value for this PenaltiesResult.
     * 
     * @return penalties
     */
    public com.sbs.service.Penalty[] getPenalties() {
        return penalties;
    }


    /**
     * Sets the penalties value for this PenaltiesResult.
     * 
     * @param penalties
     */
    public void setPenalties(com.sbs.service.Penalty[] penalties) {
        this.penalties = penalties;
    }

    public com.sbs.service.Penalty getPenalties(int i) {
        return this.penalties[i];
    }

    public void setPenalties(int i, com.sbs.service.Penalty _value) {
        this.penalties[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PenaltiesResult)) return false;
        PenaltiesResult other = (PenaltiesResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.penalties==null && other.getPenalties()==null) || 
             (this.penalties!=null &&
              java.util.Arrays.equals(this.penalties, other.getPenalties())));
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
        if (getPenalties() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPenalties());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPenalties(), i);
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
        new org.apache.axis.description.TypeDesc(PenaltiesResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "penaltiesResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("penalties");
        elemField.setXmlName(new javax.xml.namespace.QName("", "penalties"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "penalty"));
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
