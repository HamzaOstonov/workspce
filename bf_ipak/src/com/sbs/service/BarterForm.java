/**
 * BarterForm.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sbs.service;

public class BarterForm  implements java.io.Serializable {
    private java.lang.String idCol;

    private java.lang.Short p100T17;

    private com.sbs.service.Signer[] p101T17;

    private java.lang.String p1T17;

    private java.util.Calendar p2T17;

    private java.lang.Integer p3T17;

    private java.lang.Integer p4T17;

    private java.lang.Integer p5T17;

    private java.lang.String p8T17;

    private java.util.Calendar p9T17;

    public BarterForm() {
    }

    public BarterForm(
           java.lang.String idCol,
           java.lang.Short p100T17,
           com.sbs.service.Signer[] p101T17,
           java.lang.String p1T17,
           java.util.Calendar p2T17,
           java.lang.Integer p3T17,
           java.lang.Integer p4T17,
           java.lang.Integer p5T17,
           java.lang.String p8T17,
           java.util.Calendar p9T17) {
           this.idCol = idCol;
           this.p100T17 = p100T17;
           this.p101T17 = p101T17;
           this.p1T17 = p1T17;
           this.p2T17 = p2T17;
           this.p3T17 = p3T17;
           this.p4T17 = p4T17;
           this.p5T17 = p5T17;
           this.p8T17 = p8T17;
           this.p9T17 = p9T17;
    }


    /**
     * Gets the idCol value for this BarterForm.
     * 
     * @return idCol
     */
    public java.lang.String getIdCol() {
        return idCol;
    }


    /**
     * Sets the idCol value for this BarterForm.
     * 
     * @param idCol
     */
    public void setIdCol(java.lang.String idCol) {
        this.idCol = idCol;
    }


    /**
     * Gets the p100T17 value for this BarterForm.
     * 
     * @return p100T17
     */
    public java.lang.Short getP100T17() {
        return p100T17;
    }


    /**
     * Sets the p100T17 value for this BarterForm.
     * 
     * @param p100T17
     */
    public void setP100T17(java.lang.Short p100T17) {
        this.p100T17 = p100T17;
    }


    /**
     * Gets the p101T17 value for this BarterForm.
     * 
     * @return p101T17
     */
    public com.sbs.service.Signer[] getP101T17() {
        return p101T17;
    }


    /**
     * Sets the p101T17 value for this BarterForm.
     * 
     * @param p101T17
     */
    public void setP101T17(com.sbs.service.Signer[] p101T17) {
        this.p101T17 = p101T17;
    }

    public com.sbs.service.Signer getP101T17(int i) {
        return this.p101T17[i];
    }

    public void setP101T17(int i, com.sbs.service.Signer _value) {
        this.p101T17[i] = _value;
    }


    /**
     * Gets the p1T17 value for this BarterForm.
     * 
     * @return p1T17
     */
    public java.lang.String getP1T17() {
        return p1T17;
    }


    /**
     * Sets the p1T17 value for this BarterForm.
     * 
     * @param p1T17
     */
    public void setP1T17(java.lang.String p1T17) {
        this.p1T17 = p1T17;
    }


    /**
     * Gets the p2T17 value for this BarterForm.
     * 
     * @return p2T17
     */
    public java.util.Calendar getP2T17() {
        return p2T17;
    }


    /**
     * Sets the p2T17 value for this BarterForm.
     * 
     * @param p2T17
     */
    public void setP2T17(java.util.Calendar p2T17) {
        this.p2T17 = p2T17;
    }


    /**
     * Gets the p3T17 value for this BarterForm.
     * 
     * @return p3T17
     */
    public java.lang.Integer getP3T17() {
        return p3T17;
    }


    /**
     * Sets the p3T17 value for this BarterForm.
     * 
     * @param p3T17
     */
    public void setP3T17(java.lang.Integer p3T17) {
        this.p3T17 = p3T17;
    }


    /**
     * Gets the p4T17 value for this BarterForm.
     * 
     * @return p4T17
     */
    public java.lang.Integer getP4T17() {
        return p4T17;
    }


    /**
     * Sets the p4T17 value for this BarterForm.
     * 
     * @param p4T17
     */
    public void setP4T17(java.lang.Integer p4T17) {
        this.p4T17 = p4T17;
    }


    /**
     * Gets the p5T17 value for this BarterForm.
     * 
     * @return p5T17
     */
    public java.lang.Integer getP5T17() {
        return p5T17;
    }


    /**
     * Sets the p5T17 value for this BarterForm.
     * 
     * @param p5T17
     */
    public void setP5T17(java.lang.Integer p5T17) {
        this.p5T17 = p5T17;
    }


    /**
     * Gets the p8T17 value for this BarterForm.
     * 
     * @return p8T17
     */
    public java.lang.String getP8T17() {
        return p8T17;
    }


    /**
     * Sets the p8T17 value for this BarterForm.
     * 
     * @param p8T17
     */
    public void setP8T17(java.lang.String p8T17) {
        this.p8T17 = p8T17;
    }


    /**
     * Gets the p9T17 value for this BarterForm.
     * 
     * @return p9T17
     */
    public java.util.Calendar getP9T17() {
        return p9T17;
    }


    /**
     * Sets the p9T17 value for this BarterForm.
     * 
     * @param p9T17
     */
    public void setP9T17(java.util.Calendar p9T17) {
        this.p9T17 = p9T17;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BarterForm)) return false;
        BarterForm other = (BarterForm) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.idCol==null && other.getIdCol()==null) || 
             (this.idCol!=null &&
              this.idCol.equals(other.getIdCol()))) &&
            ((this.p100T17==null && other.getP100T17()==null) || 
             (this.p100T17!=null &&
              this.p100T17.equals(other.getP100T17()))) &&
            ((this.p101T17==null && other.getP101T17()==null) || 
             (this.p101T17!=null &&
              java.util.Arrays.equals(this.p101T17, other.getP101T17()))) &&
            ((this.p1T17==null && other.getP1T17()==null) || 
             (this.p1T17!=null &&
              this.p1T17.equals(other.getP1T17()))) &&
            ((this.p2T17==null && other.getP2T17()==null) || 
             (this.p2T17!=null &&
              this.p2T17.equals(other.getP2T17()))) &&
            ((this.p3T17==null && other.getP3T17()==null) || 
             (this.p3T17!=null &&
              this.p3T17.equals(other.getP3T17()))) &&
            ((this.p4T17==null && other.getP4T17()==null) || 
             (this.p4T17!=null &&
              this.p4T17.equals(other.getP4T17()))) &&
            ((this.p5T17==null && other.getP5T17()==null) || 
             (this.p5T17!=null &&
              this.p5T17.equals(other.getP5T17()))) &&
            ((this.p8T17==null && other.getP8T17()==null) || 
             (this.p8T17!=null &&
              this.p8T17.equals(other.getP8T17()))) &&
            ((this.p9T17==null && other.getP9T17()==null) || 
             (this.p9T17!=null &&
              this.p9T17.equals(other.getP9T17())));
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
        if (getIdCol() != null) {
            _hashCode += getIdCol().hashCode();
        }
        if (getP100T17() != null) {
            _hashCode += getP100T17().hashCode();
        }
        if (getP101T17() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getP101T17());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getP101T17(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getP1T17() != null) {
            _hashCode += getP1T17().hashCode();
        }
        if (getP2T17() != null) {
            _hashCode += getP2T17().hashCode();
        }
        if (getP3T17() != null) {
            _hashCode += getP3T17().hashCode();
        }
        if (getP4T17() != null) {
            _hashCode += getP4T17().hashCode();
        }
        if (getP5T17() != null) {
            _hashCode += getP5T17().hashCode();
        }
        if (getP8T17() != null) {
            _hashCode += getP8T17().hashCode();
        }
        if (getP9T17() != null) {
            _hashCode += getP9T17().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BarterForm.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "barterForm"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idCol");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idCol"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p100T17");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p100T17"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p101T17");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p101T17"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "signer"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p1T17");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p1T17"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p2T17");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p2T17"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p3T17");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p3T17"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p4T17");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p4T17"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p5T17");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p5T17"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p8T17");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p8T17"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p9T17");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p9T17"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
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
