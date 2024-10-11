/**
 * MovesFromExResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sbs.service;

public class MovesFromExResult  extends com.sbs.service.Result  implements java.io.Serializable {
    private com.sbs.service.MoveFromEx[] movesFromEx;

    public MovesFromExResult() {
    }

    public MovesFromExResult(
           java.lang.String errorMsg,
           int gtkId,
           int status,
           java.util.Calendar timeStamp,
           com.sbs.service.MoveFromEx[] movesFromEx) {
        super(
            errorMsg,
            gtkId,
            status,
            timeStamp);
        this.movesFromEx = movesFromEx;
    }


    /**
     * Gets the movesFromEx value for this MovesFromExResult.
     * 
     * @return movesFromEx
     */
    public com.sbs.service.MoveFromEx[] getMovesFromEx() {
        return movesFromEx;
    }


    /**
     * Sets the movesFromEx value for this MovesFromExResult.
     * 
     * @param movesFromEx
     */
    public void setMovesFromEx(com.sbs.service.MoveFromEx[] movesFromEx) {
        this.movesFromEx = movesFromEx;
    }

    public com.sbs.service.MoveFromEx getMovesFromEx(int i) {
        return this.movesFromEx[i];
    }

    public void setMovesFromEx(int i, com.sbs.service.MoveFromEx _value) {
        this.movesFromEx[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MovesFromExResult)) return false;
        MovesFromExResult other = (MovesFromExResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.movesFromEx==null && other.getMovesFromEx()==null) || 
             (this.movesFromEx!=null &&
              java.util.Arrays.equals(this.movesFromEx, other.getMovesFromEx())));
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
        if (getMovesFromEx() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getMovesFromEx());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getMovesFromEx(), i);
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
        new org.apache.axis.description.TypeDesc(MovesFromExResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "movesFromExResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("movesFromEx");
        elemField.setXmlName(new javax.xml.namespace.QName("", "movesFromEx"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "moveFromEx"));
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
