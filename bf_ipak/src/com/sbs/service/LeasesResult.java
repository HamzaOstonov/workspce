/**
 * LeasesResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sbs.service;

public class LeasesResult  extends com.sbs.service.Result  implements java.io.Serializable {
    private com.sbs.service.Lease[] leases;

    public LeasesResult() {
    }

    public LeasesResult(
           java.lang.String errorMsg,
           int gtkId,
           int status,
           java.util.Calendar timeStamp,
           com.sbs.service.Lease[] leases) {
        super(
            errorMsg,
            gtkId,
            status,
            timeStamp);
        this.leases = leases;
    }


    /**
     * Gets the leases value for this LeasesResult.
     * 
     * @return leases
     */
    public com.sbs.service.Lease[] getLeases() {
        return leases;
    }


    /**
     * Sets the leases value for this LeasesResult.
     * 
     * @param leases
     */
    public void setLeases(com.sbs.service.Lease[] leases) {
        this.leases = leases;
    }

    public com.sbs.service.Lease getLeases(int i) {
        return this.leases[i];
    }

    public void setLeases(int i, com.sbs.service.Lease _value) {
        this.leases[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LeasesResult)) return false;
        LeasesResult other = (LeasesResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.leases==null && other.getLeases()==null) || 
             (this.leases!=null &&
              java.util.Arrays.equals(this.leases, other.getLeases())));
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
        if (getLeases() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getLeases());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getLeases(), i);
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
        new org.apache.axis.description.TypeDesc(LeasesResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "leasesResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("leases");
        elemField.setXmlName(new javax.xml.namespace.QName("", "leases"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "lease"));
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
