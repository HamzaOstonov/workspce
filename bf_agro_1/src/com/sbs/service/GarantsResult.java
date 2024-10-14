/**
 * GarantsResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sbs.service;

public class GarantsResult  extends com.sbs.service.Result  implements java.io.Serializable {
    private com.sbs.service.Garant[] garants;

    public GarantsResult() {
    }

    public GarantsResult(
           java.lang.String errorMsg,
           int gtkId,
           int status,
           java.util.Calendar timeStamp,
           com.sbs.service.Garant[] garants) {
        super(
            errorMsg,
            gtkId,
            status,
            timeStamp);
        this.garants = garants;
    }


    /**
     * Gets the garants value for this GarantsResult.
     * 
     * @return garants
     */
    public com.sbs.service.Garant[] getGarants() {
        return garants;
    }


    /**
     * Sets the garants value for this GarantsResult.
     * 
     * @param garants
     */
    public void setGarants(com.sbs.service.Garant[] garants) {
        this.garants = garants;
    }

    public com.sbs.service.Garant getGarants(int i) {
        return this.garants[i];
    }

    public void setGarants(int i, com.sbs.service.Garant _value) {
        this.garants[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GarantsResult)) return false;
        GarantsResult other = (GarantsResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.garants==null && other.getGarants()==null) || 
             (this.garants!=null &&
              java.util.Arrays.equals(this.garants, other.getGarants())));
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
        if (getGarants() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getGarants());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getGarants(), i);
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
        new org.apache.axis.description.TypeDesc(GarantsResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "garantsResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("garants");
        elemField.setXmlName(new javax.xml.namespace.QName("", "garants"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "garant"));
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
