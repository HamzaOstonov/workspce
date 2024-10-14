/**
 * DebetInfoResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sbs.service;

public class DebetInfoResult  extends com.sbs.service.Result  implements java.io.Serializable {
    private com.sbs.service.DebetInfo[] debetsInfo;

    public DebetInfoResult() {
    }

    public DebetInfoResult(
           java.lang.String errorMsg,
           int gtkId,
           int status,
           java.util.Calendar timeStamp,
           com.sbs.service.DebetInfo[] debetsInfo) {
        super(
            errorMsg,
            gtkId,
            status,
            timeStamp);
        this.debetsInfo = debetsInfo;
    }


    /**
     * Gets the debetsInfo value for this DebetInfoResult.
     * 
     * @return debetsInfo
     */
    public com.sbs.service.DebetInfo[] getDebetsInfo() {
        return debetsInfo;
    }


    /**
     * Sets the debetsInfo value for this DebetInfoResult.
     * 
     * @param debetsInfo
     */
    public void setDebetsInfo(com.sbs.service.DebetInfo[] debetsInfo) {
        this.debetsInfo = debetsInfo;
    }

    public com.sbs.service.DebetInfo getDebetsInfo(int i) {
        return this.debetsInfo[i];
    }

    public void setDebetsInfo(int i, com.sbs.service.DebetInfo _value) {
        this.debetsInfo[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DebetInfoResult)) return false;
        DebetInfoResult other = (DebetInfoResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.debetsInfo==null && other.getDebetsInfo()==null) || 
             (this.debetsInfo!=null &&
              java.util.Arrays.equals(this.debetsInfo, other.getDebetsInfo())));
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
        if (getDebetsInfo() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDebetsInfo());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDebetsInfo(), i);
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
        new org.apache.axis.description.TypeDesc(DebetInfoResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "debetInfoResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("debetsInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "debetsInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "debetInfo"));
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
