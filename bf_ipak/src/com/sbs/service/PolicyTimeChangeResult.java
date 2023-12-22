/**
 * PolicyTimeChangeResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sbs.service;

public class PolicyTimeChangeResult  extends com.sbs.service.Result  implements java.io.Serializable {
    private com.sbs.service.PolicyTimeChange[] policyTimeChanges;

    public PolicyTimeChangeResult() {
    }

    public PolicyTimeChangeResult(
           java.lang.String errorMsg,
           int gtkId,
           int status,
           java.util.Calendar timeStamp,
           com.sbs.service.PolicyTimeChange[] policyTimeChanges) {
        super(
            errorMsg,
            gtkId,
            status,
            timeStamp);
        this.policyTimeChanges = policyTimeChanges;
    }


    /**
     * Gets the policyTimeChanges value for this PolicyTimeChangeResult.
     * 
     * @return policyTimeChanges
     */
    public com.sbs.service.PolicyTimeChange[] getPolicyTimeChanges() {
        return policyTimeChanges;
    }


    /**
     * Sets the policyTimeChanges value for this PolicyTimeChangeResult.
     * 
     * @param policyTimeChanges
     */
    public void setPolicyTimeChanges(com.sbs.service.PolicyTimeChange[] policyTimeChanges) {
        this.policyTimeChanges = policyTimeChanges;
    }

    public com.sbs.service.PolicyTimeChange getPolicyTimeChanges(int i) {
        return this.policyTimeChanges[i];
    }

    public void setPolicyTimeChanges(int i, com.sbs.service.PolicyTimeChange _value) {
        this.policyTimeChanges[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PolicyTimeChangeResult)) return false;
        PolicyTimeChangeResult other = (PolicyTimeChangeResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.policyTimeChanges==null && other.getPolicyTimeChanges()==null) || 
             (this.policyTimeChanges!=null &&
              java.util.Arrays.equals(this.policyTimeChanges, other.getPolicyTimeChanges())));
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
        if (getPolicyTimeChanges() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPolicyTimeChanges());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPolicyTimeChanges(), i);
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
        new org.apache.axis.description.TypeDesc(PolicyTimeChangeResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "policyTimeChangeResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("policyTimeChanges");
        elemField.setXmlName(new javax.xml.namespace.QName("", "policyTimeChanges"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "policyTimeChange"));
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
