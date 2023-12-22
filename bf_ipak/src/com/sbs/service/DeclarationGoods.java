/**
 * DeclarationGoods.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sbs.service;

public class DeclarationGoods  implements java.io.Serializable {
    private java.lang.String p10T49;

    private java.lang.Double p11T49;

    private java.lang.String p12T49;

    private java.lang.Integer p1T49;

    private java.lang.String p2T49;

    private java.lang.Double p5T49;

    private java.lang.String p7T49;

    private java.lang.String p8T49;

    private java.lang.Double p9T49;

    public DeclarationGoods() {
    }

    public DeclarationGoods(
           java.lang.String p10T49,
           java.lang.Double p11T49,
           java.lang.String p12T49,
           java.lang.Integer p1T49,
           java.lang.String p2T49,
           java.lang.Double p5T49,
           java.lang.String p7T49,
           java.lang.String p8T49,
           java.lang.Double p9T49) {
           this.p10T49 = p10T49;
           this.p11T49 = p11T49;
           this.p12T49 = p12T49;
           this.p1T49 = p1T49;
           this.p2T49 = p2T49;
           this.p5T49 = p5T49;
           this.p7T49 = p7T49;
           this.p8T49 = p8T49;
           this.p9T49 = p9T49;
    }


    /**
     * Gets the p10T49 value for this DeclarationGoods.
     * 
     * @return p10T49
     */
    public java.lang.String getP10T49() {
        return p10T49;
    }


    /**
     * Sets the p10T49 value for this DeclarationGoods.
     * 
     * @param p10T49
     */
    public void setP10T49(java.lang.String p10T49) {
        this.p10T49 = p10T49;
    }


    /**
     * Gets the p11T49 value for this DeclarationGoods.
     * 
     * @return p11T49
     */
    public java.lang.Double getP11T49() {
        return p11T49;
    }


    /**
     * Sets the p11T49 value for this DeclarationGoods.
     * 
     * @param p11T49
     */
    public void setP11T49(java.lang.Double p11T49) {
        this.p11T49 = p11T49;
    }


    /**
     * Gets the p12T49 value for this DeclarationGoods.
     * 
     * @return p12T49
     */
    public java.lang.String getP12T49() {
        return p12T49;
    }


    /**
     * Sets the p12T49 value for this DeclarationGoods.
     * 
     * @param p12T49
     */
    public void setP12T49(java.lang.String p12T49) {
        this.p12T49 = p12T49;
    }


    /**
     * Gets the p1T49 value for this DeclarationGoods.
     * 
     * @return p1T49
     */
    public java.lang.Integer getP1T49() {
        return p1T49;
    }


    /**
     * Sets the p1T49 value for this DeclarationGoods.
     * 
     * @param p1T49
     */
    public void setP1T49(java.lang.Integer p1T49) {
        this.p1T49 = p1T49;
    }


    /**
     * Gets the p2T49 value for this DeclarationGoods.
     * 
     * @return p2T49
     */
    public java.lang.String getP2T49() {
        return p2T49;
    }


    /**
     * Sets the p2T49 value for this DeclarationGoods.
     * 
     * @param p2T49
     */
    public void setP2T49(java.lang.String p2T49) {
        this.p2T49 = p2T49;
    }


    /**
     * Gets the p5T49 value for this DeclarationGoods.
     * 
     * @return p5T49
     */
    public java.lang.Double getP5T49() {
        return p5T49;
    }


    /**
     * Sets the p5T49 value for this DeclarationGoods.
     * 
     * @param p5T49
     */
    public void setP5T49(java.lang.Double p5T49) {
        this.p5T49 = p5T49;
    }


    /**
     * Gets the p7T49 value for this DeclarationGoods.
     * 
     * @return p7T49
     */
    public java.lang.String getP7T49() {
        return p7T49;
    }


    /**
     * Sets the p7T49 value for this DeclarationGoods.
     * 
     * @param p7T49
     */
    public void setP7T49(java.lang.String p7T49) {
        this.p7T49 = p7T49;
    }


    /**
     * Gets the p8T49 value for this DeclarationGoods.
     * 
     * @return p8T49
     */
    public java.lang.String getP8T49() {
        return p8T49;
    }


    /**
     * Sets the p8T49 value for this DeclarationGoods.
     * 
     * @param p8T49
     */
    public void setP8T49(java.lang.String p8T49) {
        this.p8T49 = p8T49;
    }


    /**
     * Gets the p9T49 value for this DeclarationGoods.
     * 
     * @return p9T49
     */
    public java.lang.Double getP9T49() {
        return p9T49;
    }


    /**
     * Sets the p9T49 value for this DeclarationGoods.
     * 
     * @param p9T49
     */
    public void setP9T49(java.lang.Double p9T49) {
        this.p9T49 = p9T49;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DeclarationGoods)) return false;
        DeclarationGoods other = (DeclarationGoods) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.p10T49==null && other.getP10T49()==null) || 
             (this.p10T49!=null &&
              this.p10T49.equals(other.getP10T49()))) &&
            ((this.p11T49==null && other.getP11T49()==null) || 
             (this.p11T49!=null &&
              this.p11T49.equals(other.getP11T49()))) &&
            ((this.p12T49==null && other.getP12T49()==null) || 
             (this.p12T49!=null &&
              this.p12T49.equals(other.getP12T49()))) &&
            ((this.p1T49==null && other.getP1T49()==null) || 
             (this.p1T49!=null &&
              this.p1T49.equals(other.getP1T49()))) &&
            ((this.p2T49==null && other.getP2T49()==null) || 
             (this.p2T49!=null &&
              this.p2T49.equals(other.getP2T49()))) &&
            ((this.p5T49==null && other.getP5T49()==null) || 
             (this.p5T49!=null &&
              this.p5T49.equals(other.getP5T49()))) &&
            ((this.p7T49==null && other.getP7T49()==null) || 
             (this.p7T49!=null &&
              this.p7T49.equals(other.getP7T49()))) &&
            ((this.p8T49==null && other.getP8T49()==null) || 
             (this.p8T49!=null &&
              this.p8T49.equals(other.getP8T49()))) &&
            ((this.p9T49==null && other.getP9T49()==null) || 
             (this.p9T49!=null &&
              this.p9T49.equals(other.getP9T49())));
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
        if (getP10T49() != null) {
            _hashCode += getP10T49().hashCode();
        }
        if (getP11T49() != null) {
            _hashCode += getP11T49().hashCode();
        }
        if (getP12T49() != null) {
            _hashCode += getP12T49().hashCode();
        }
        if (getP1T49() != null) {
            _hashCode += getP1T49().hashCode();
        }
        if (getP2T49() != null) {
            _hashCode += getP2T49().hashCode();
        }
        if (getP5T49() != null) {
            _hashCode += getP5T49().hashCode();
        }
        if (getP7T49() != null) {
            _hashCode += getP7T49().hashCode();
        }
        if (getP8T49() != null) {
            _hashCode += getP8T49().hashCode();
        }
        if (getP9T49() != null) {
            _hashCode += getP9T49().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DeclarationGoods.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "declarationGoods"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p10T49");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p10T49"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p11T49");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p11T49"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p12T49");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p12T49"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p1T49");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p1T49"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p2T49");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p2T49"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p5T49");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p5T49"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p7T49");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p7T49"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p8T49");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p8T49"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p9T49");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p9T49"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
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
