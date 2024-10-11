/**
 * DebetResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sbs.service;

public class DebetResult  extends com.sbs.service.Result  implements java.io.Serializable {
    private com.sbs.service.Debet[] debets;

    public DebetResult() {
    }

    public DebetResult(
           java.lang.String errorMsg,
           int gtkId,
           int status,
           java.util.Calendar timeStamp,
           com.sbs.service.Debet[] debets) {
        super(
            errorMsg,
            gtkId,
            status,
            timeStamp);
        this.debets = debets;
    }


    /**
     * Gets the debets value for this DebetResult.
     * 
     * @return debets
     */
    public com.sbs.service.Debet[] getDebets() {
        return debets;
    }


    /**
     * Sets the debets value for this DebetResult.
     * 
     * @param debets
     */
    public void setDebets(com.sbs.service.Debet[] debets) {
        this.debets = debets;
    }

    public com.sbs.service.Debet getDebets(int i) {
        return this.debets[i];
    }

    public void setDebets(int i, com.sbs.service.Debet _value) {
        this.debets[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DebetResult)) return false;
        DebetResult other = (DebetResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.debets==null && other.getDebets()==null) || 
             (this.debets!=null &&
              java.util.Arrays.equals(this.debets, other.getDebets())));
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
        if (getDebets() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDebets());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDebets(), i);
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
        new org.apache.axis.description.TypeDesc(DebetResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "debetResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("debets");
        elemField.setXmlName(new javax.xml.namespace.QName("", "debets"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "debet"));
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
