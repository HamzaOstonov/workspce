/**
 * MovesToExResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sbs.service;

public class MovesToExResult  extends com.sbs.service.Result  implements java.io.Serializable {
    private com.sbs.service.MoveToEx[] movesToEx;

    public MovesToExResult() {
    }

    public MovesToExResult(
           java.lang.String errorMsg,
           int gtkId,
           int status,
           java.util.Calendar timeStamp,
           com.sbs.service.MoveToEx[] movesToEx) {
        super(
            errorMsg,
            gtkId,
            status,
            timeStamp);
        this.movesToEx = movesToEx;
    }


    /**
     * Gets the movesToEx value for this MovesToExResult.
     * 
     * @return movesToEx
     */
    public com.sbs.service.MoveToEx[] getMovesToEx() {
        return movesToEx;
    }


    /**
     * Sets the movesToEx value for this MovesToExResult.
     * 
     * @param movesToEx
     */
    public void setMovesToEx(com.sbs.service.MoveToEx[] movesToEx) {
        this.movesToEx = movesToEx;
    }

    public com.sbs.service.MoveToEx getMovesToEx(int i) {
        return this.movesToEx[i];
    }

    public void setMovesToEx(int i, com.sbs.service.MoveToEx _value) {
        this.movesToEx[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MovesToExResult)) return false;
        MovesToExResult other = (MovesToExResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.movesToEx==null && other.getMovesToEx()==null) || 
             (this.movesToEx!=null &&
              java.util.Arrays.equals(this.movesToEx, other.getMovesToEx())));
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
        if (getMovesToEx() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getMovesToEx());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getMovesToEx(), i);
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
        new org.apache.axis.description.TypeDesc(MovesToExResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "movesToExResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("movesToEx");
        elemField.setXmlName(new javax.xml.namespace.QName("", "movesToEx"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "moveToEx"));
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
