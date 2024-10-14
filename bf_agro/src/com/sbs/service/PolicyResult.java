/**
 * PolicyResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sbs.service;

public class PolicyResult  extends com.sbs.service.Result  implements java.io.Serializable {
    private com.sbs.service.Policy[] policies;

    public PolicyResult() {
    }

    public PolicyResult(
           java.lang.String errorMsg,
           int gtkId,
           int status,
           java.util.Calendar timeStamp,
           com.sbs.service.Policy[] policies) {
        super(
            errorMsg,
            gtkId,
            status,
            timeStamp);
        this.policies = policies;
    }


    /**
     * Gets the policies value for this PolicyResult.
     * 
     * @return policies
     */
    public com.sbs.service.Policy[] getPolicies() {
        return policies;
    }


    /**
     * Sets the policies value for this PolicyResult.
     * 
     * @param policies
     */
    public void setPolicies(com.sbs.service.Policy[] policies) {
        this.policies = policies;
    }

    public com.sbs.service.Policy getPolicies(int i) {
        return this.policies[i];
    }

    public void setPolicies(int i, com.sbs.service.Policy _value) {
        this.policies[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PolicyResult)) return false;
        PolicyResult other = (PolicyResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.policies==null && other.getPolicies()==null) || 
             (this.policies!=null &&
              java.util.Arrays.equals(this.policies, other.getPolicies())));
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
        if (getPolicies() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPolicies());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPolicies(), i);
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
        new org.apache.axis.description.TypeDesc(PolicyResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "policyResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("policies");
        elemField.setXmlName(new javax.xml.namespace.QName("", "policies"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "policy"));
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
