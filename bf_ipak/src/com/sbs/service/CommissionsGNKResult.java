/**
 * CommissionsGNKResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sbs.service;

public class CommissionsGNKResult  extends com.sbs.service.Result  implements java.io.Serializable {
    private com.sbs.service.CommissionGNK[] commissionsGNK;

    public CommissionsGNKResult() {
    }

    public CommissionsGNKResult(
           java.lang.String errorMsg,
           int gtkId,
           int status,
           java.util.Calendar timeStamp,
           com.sbs.service.CommissionGNK[] commissionsGNK) {
        super(
            errorMsg,
            gtkId,
            status,
            timeStamp);
        this.commissionsGNK = commissionsGNK;
    }


    /**
     * Gets the commissionsGNK value for this CommissionsGNKResult.
     * 
     * @return commissionsGNK
     */
    public com.sbs.service.CommissionGNK[] getCommissionsGNK() {
        return commissionsGNK;
    }


    /**
     * Sets the commissionsGNK value for this CommissionsGNKResult.
     * 
     * @param commissionsGNK
     */
    public void setCommissionsGNK(com.sbs.service.CommissionGNK[] commissionsGNK) {
        this.commissionsGNK = commissionsGNK;
    }

    public com.sbs.service.CommissionGNK getCommissionsGNK(int i) {
        return this.commissionsGNK[i];
    }

    public void setCommissionsGNK(int i, com.sbs.service.CommissionGNK _value) {
        this.commissionsGNK[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CommissionsGNKResult)) return false;
        CommissionsGNKResult other = (CommissionsGNKResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.commissionsGNK==null && other.getCommissionsGNK()==null) || 
             (this.commissionsGNK!=null &&
              java.util.Arrays.equals(this.commissionsGNK, other.getCommissionsGNK())));
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
        if (getCommissionsGNK() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCommissionsGNK());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCommissionsGNK(), i);
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
        new org.apache.axis.description.TypeDesc(CommissionsGNKResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "commissionsGNKResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("commissionsGNK");
        elemField.setXmlName(new javax.xml.namespace.QName("", "commissionsGNK"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "commissionGNK"));
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
