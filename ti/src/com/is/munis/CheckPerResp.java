/**
 * CheckPerResp.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.is.munis;

public class CheckPerResp  implements java.io.Serializable {
    private com.is.munis.Res res;

    private com.is.munis.ResR rr;

    public CheckPerResp() {
    }

    public CheckPerResp(
           com.is.munis.Res res,
           com.is.munis.ResR rr) {
           this.res = res;
           this.rr = rr;
    }


    /**
     * Gets the res value for this CheckPerResp.
     * 
     * @return res
     */
    public com.is.munis.Res getRes() {
        return res;
    }


    /**
     * Sets the res value for this CheckPerResp.
     * 
     * @param res
     */
    public void setRes(com.is.munis.Res res) {
        this.res = res;
    }


    /**
     * Gets the rr value for this CheckPerResp.
     * 
     * @return rr
     */
    public com.is.munis.ResR getRr() {
        return rr;
    }


    /**
     * Sets the rr value for this CheckPerResp.
     * 
     * @param rr
     */
    public void setRr(com.is.munis.ResR rr) {
        this.rr = rr;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CheckPerResp)) return false;
        CheckPerResp other = (CheckPerResp) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.res==null && other.getRes()==null) || 
             (this.res!=null &&
              this.res.equals(other.getRes()))) &&
            ((this.rr==null && other.getRr()==null) || 
             (this.rr!=null &&
              this.rr.equals(other.getRr())));
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
        if (getRes() != null) {
            _hashCode += getRes().hashCode();
        }
        if (getRr() != null) {
            _hashCode += getRr().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CheckPerResp.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://munis.is.com/", "checkPerResp"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("res");
        elemField.setXmlName(new javax.xml.namespace.QName("", "res"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://munis.is.com/", "res"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "rr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://munis.is.com/", "resR"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
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
