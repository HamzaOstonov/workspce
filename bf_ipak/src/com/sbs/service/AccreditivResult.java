/**
 * AccreditivResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sbs.service;

public class AccreditivResult  extends com.sbs.service.Result  implements java.io.Serializable {
    private com.sbs.service.Accreditiv[] accreditives;

    public AccreditivResult() {
    }

    public AccreditivResult(
           java.lang.String errorMsg,
           int gtkId,
           int status,
           java.util.Calendar timeStamp,
           com.sbs.service.Accreditiv[] accreditives) {
        super(
            errorMsg,
            gtkId,
            status,
            timeStamp);
        this.accreditives = accreditives;
    }


    /**
     * Gets the accreditives value for this AccreditivResult.
     * 
     * @return accreditives
     */
    public com.sbs.service.Accreditiv[] getAccreditives() {
        return accreditives;
    }


    /**
     * Sets the accreditives value for this AccreditivResult.
     * 
     * @param accreditives
     */
    public void setAccreditives(com.sbs.service.Accreditiv[] accreditives) {
        this.accreditives = accreditives;
    }

    public com.sbs.service.Accreditiv getAccreditives(int i) {
        return this.accreditives[i];
    }

    public void setAccreditives(int i, com.sbs.service.Accreditiv _value) {
        this.accreditives[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AccreditivResult)) return false;
        AccreditivResult other = (AccreditivResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.accreditives==null && other.getAccreditives()==null) || 
             (this.accreditives!=null &&
              java.util.Arrays.equals(this.accreditives, other.getAccreditives())));
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
        if (getAccreditives() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAccreditives());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAccreditives(), i);
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
        new org.apache.axis.description.TypeDesc(AccreditivResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "accreditivResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accreditives");
        elemField.setXmlName(new javax.xml.namespace.QName("", "accreditives"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "accreditiv"));
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
