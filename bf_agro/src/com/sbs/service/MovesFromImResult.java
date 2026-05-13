/**
 * MovesFromImResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sbs.service;

public class MovesFromImResult  extends com.sbs.service.Result  implements java.io.Serializable {
    private com.sbs.service.MoveFromIm[] movesFromIm;

    public MovesFromImResult() {
    }

    public MovesFromImResult(
           java.lang.String errorMsg,
           int gtkId,
           int status,
           java.util.Calendar timeStamp,
           com.sbs.service.MoveFromIm[] movesFromIm) {
        super(
            errorMsg,
            gtkId,
            status,
            timeStamp);
        this.movesFromIm = movesFromIm;
    }


    /**
     * Gets the movesFromIm value for this MovesFromImResult.
     * 
     * @return movesFromIm
     */
    public com.sbs.service.MoveFromIm[] getMovesFromIm() {
        return movesFromIm;
    }


    /**
     * Sets the movesFromIm value for this MovesFromImResult.
     * 
     * @param movesFromIm
     */
    public void setMovesFromIm(com.sbs.service.MoveFromIm[] movesFromIm) {
        this.movesFromIm = movesFromIm;
    }

    public com.sbs.service.MoveFromIm getMovesFromIm(int i) {
        return this.movesFromIm[i];
    }

    public void setMovesFromIm(int i, com.sbs.service.MoveFromIm _value) {
        this.movesFromIm[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MovesFromImResult)) return false;
        MovesFromImResult other = (MovesFromImResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.movesFromIm==null && other.getMovesFromIm()==null) || 
             (this.movesFromIm!=null &&
              java.util.Arrays.equals(this.movesFromIm, other.getMovesFromIm())));
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
        if (getMovesFromIm() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getMovesFromIm());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getMovesFromIm(), i);
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
        new org.apache.axis.description.TypeDesc(MovesFromImResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "movesFromImResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("movesFromIm");
        elemField.setXmlName(new javax.xml.namespace.QName("", "movesFromIm"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "moveFromIm"));
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
