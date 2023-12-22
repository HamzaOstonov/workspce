/**
 * SaleResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sbs.service;

public class SaleResult  extends com.sbs.service.Result  implements java.io.Serializable {
    private com.sbs.service.Sale[] sales;

    public SaleResult() {
    }

    public SaleResult(
           java.lang.String errorMsg,
           int gtkId,
           int status,
           java.util.Calendar timeStamp,
           com.sbs.service.Sale[] sales) {
        super(
            errorMsg,
            gtkId,
            status,
            timeStamp);
        this.sales = sales;
    }


    /**
     * Gets the sales value for this SaleResult.
     * 
     * @return sales
     */
    public com.sbs.service.Sale[] getSales() {
        return sales;
    }


    /**
     * Sets the sales value for this SaleResult.
     * 
     * @param sales
     */
    public void setSales(com.sbs.service.Sale[] sales) {
        this.sales = sales;
    }

    public com.sbs.service.Sale getSales(int i) {
        return this.sales[i];
    }

    public void setSales(int i, com.sbs.service.Sale _value) {
        this.sales[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SaleResult)) return false;
        SaleResult other = (SaleResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.sales==null && other.getSales()==null) || 
             (this.sales!=null &&
              java.util.Arrays.equals(this.sales, other.getSales())));
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
        if (getSales() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSales());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSales(), i);
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
        new org.apache.axis.description.TypeDesc(SaleResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "saleResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sales");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sales"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "sale"));
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
