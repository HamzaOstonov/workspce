/**
 * SavePaymentRefSumChange.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sbs.service;

public class SavePaymentRefSumChange  implements java.io.Serializable {
    private java.lang.String username;

    private java.lang.String password;

    private java.lang.String p1T1;

    private java.lang.String p2T37;

    private com.sbs.service.PaymentRefSumChange paymentRefSumChange;

    public SavePaymentRefSumChange() {
    }

    public SavePaymentRefSumChange(
           java.lang.String username,
           java.lang.String password,
           java.lang.String p1T1,
           java.lang.String p2T37,
           com.sbs.service.PaymentRefSumChange paymentRefSumChange) {
           this.username = username;
           this.password = password;
           this.p1T1 = p1T1;
           this.p2T37 = p2T37;
           this.paymentRefSumChange = paymentRefSumChange;
    }


    /**
     * Gets the username value for this SavePaymentRefSumChange.
     * 
     * @return username
     */
    public java.lang.String getUsername() {
        return username;
    }


    /**
     * Sets the username value for this SavePaymentRefSumChange.
     * 
     * @param username
     */
    public void setUsername(java.lang.String username) {
        this.username = username;
    }


    /**
     * Gets the password value for this SavePaymentRefSumChange.
     * 
     * @return password
     */
    public java.lang.String getPassword() {
        return password;
    }


    /**
     * Sets the password value for this SavePaymentRefSumChange.
     * 
     * @param password
     */
    public void setPassword(java.lang.String password) {
        this.password = password;
    }


    /**
     * Gets the p1T1 value for this SavePaymentRefSumChange.
     * 
     * @return p1T1
     */
    public java.lang.String getP1T1() {
        return p1T1;
    }


    /**
     * Sets the p1T1 value for this SavePaymentRefSumChange.
     * 
     * @param p1T1
     */
    public void setP1T1(java.lang.String p1T1) {
        this.p1T1 = p1T1;
    }


    /**
     * Gets the p2T37 value for this SavePaymentRefSumChange.
     * 
     * @return p2T37
     */
    public java.lang.String getP2T37() {
        return p2T37;
    }


    /**
     * Sets the p2T37 value for this SavePaymentRefSumChange.
     * 
     * @param p2T37
     */
    public void setP2T37(java.lang.String p2T37) {
        this.p2T37 = p2T37;
    }


    /**
     * Gets the paymentRefSumChange value for this SavePaymentRefSumChange.
     * 
     * @return paymentRefSumChange
     */
    public com.sbs.service.PaymentRefSumChange getPaymentRefSumChange() {
        return paymentRefSumChange;
    }


    /**
     * Sets the paymentRefSumChange value for this SavePaymentRefSumChange.
     * 
     * @param paymentRefSumChange
     */
    public void setPaymentRefSumChange(com.sbs.service.PaymentRefSumChange paymentRefSumChange) {
        this.paymentRefSumChange = paymentRefSumChange;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SavePaymentRefSumChange)) return false;
        SavePaymentRefSumChange other = (SavePaymentRefSumChange) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.username==null && other.getUsername()==null) || 
             (this.username!=null &&
              this.username.equals(other.getUsername()))) &&
            ((this.password==null && other.getPassword()==null) || 
             (this.password!=null &&
              this.password.equals(other.getPassword()))) &&
            ((this.p1T1==null && other.getP1T1()==null) || 
             (this.p1T1!=null &&
              this.p1T1.equals(other.getP1T1()))) &&
            ((this.p2T37==null && other.getP2T37()==null) || 
             (this.p2T37!=null &&
              this.p2T37.equals(other.getP2T37()))) &&
            ((this.paymentRefSumChange==null && other.getPaymentRefSumChange()==null) || 
             (this.paymentRefSumChange!=null &&
              this.paymentRefSumChange.equals(other.getPaymentRefSumChange())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getUsername() != null) {
            _hashCode += getUsername().hashCode();
        }
        if (getPassword() != null) {
            _hashCode += getPassword().hashCode();
        }
        if (getP1T1() != null) {
            _hashCode += getP1T1().hashCode();
        }
        if (getP2T37() != null) {
            _hashCode += getP2T37().hashCode();
        }
        if (getPaymentRefSumChange() != null) {
            _hashCode += getPaymentRefSumChange().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SavePaymentRefSumChange.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "savePaymentRefSumChange"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("username");
        elemField.setXmlName(new javax.xml.namespace.QName("", "username"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("password");
        elemField.setXmlName(new javax.xml.namespace.QName("", "password"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p1T1");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p1T1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p2T37");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p2T37"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paymentRefSumChange");
        elemField.setXmlName(new javax.xml.namespace.QName("", "paymentRefSumChange"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "paymentRefSumChange"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
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
