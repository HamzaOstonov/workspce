/**
 * CommissionsResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sbs.service;

public class CommissionsResult  extends com.sbs.service.Result  implements java.io.Serializable {
    private com.sbs.service.Commission[] commissions;

    public CommissionsResult() {
    }

    public CommissionsResult(
           java.lang.String errorMsg,
           int gtkId,
           int status,
           java.util.Calendar timeStamp,
           com.sbs.service.Commission[] commissions) {
        super(
            errorMsg,
            gtkId,
            status,
            timeStamp);
        this.commissions = commissions;
    }


    /**
     * Gets the commissions value for this CommissionsResult.
     * 
     * @return commissions
     */
    public com.sbs.service.Commission[] getCommissions() {
        return commissions;
    }


    /**
     * Sets the commissions value for this CommissionsResult.
     * 
     * @param commissions
     */
    public void setCommissions(com.sbs.service.Commission[] commissions) {
        this.commissions = commissions;
    }

    public com.sbs.service.Commission getCommissions(int i) {
        return this.commissions[i];
    }

    public void setCommissions(int i, com.sbs.service.Commission _value) {
        this.commissions[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CommissionsResult)) return false;
        CommissionsResult other = (CommissionsResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.commissions==null && other.getCommissions()==null) || 
             (this.commissions!=null &&
              java.util.Arrays.equals(this.commissions, other.getCommissions())));
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
        if (getCommissions() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCommissions());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCommissions(), i);
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
        new org.apache.axis.description.TypeDesc(CommissionsResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "commissionsResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("commissions");
        elemField.setXmlName(new javax.xml.namespace.QName("", "commissions"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "commission"));
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
