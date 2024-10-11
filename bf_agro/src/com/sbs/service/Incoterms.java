/**
 * Incoterms.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sbs.service;

public class Incoterms  implements java.io.Serializable {
    private java.lang.String idCol;

    private java.lang.Short p100T9;

    private com.sbs.service.Signer[] p101T9;

    private java.lang.String p2T9;

    private java.lang.String p3T9;

    private java.lang.String p7T9;

    private java.lang.Integer p8T9;

    private java.util.Calendar p9T9;

    public Incoterms() {
    }

    public Incoterms(
           java.lang.String idCol,
           java.lang.Short p100T9,
           com.sbs.service.Signer[] p101T9,
           java.lang.String p2T9,
           java.lang.String p3T9,
           java.lang.String p7T9,
           java.lang.Integer p8T9,
           java.util.Calendar p9T9) {
           this.idCol = idCol;
           this.p100T9 = p100T9;
           this.p101T9 = p101T9;
           this.p2T9 = p2T9;
           this.p3T9 = p3T9;
           this.p7T9 = p7T9;
           this.p8T9 = p8T9;
           this.p9T9 = p9T9;
    }


    /**
     * Gets the idCol value for this Incoterms.
     * 
     * @return idCol
     */
    public java.lang.String getIdCol() {
        return idCol;
    }


    /**
     * Sets the idCol value for this Incoterms.
     * 
     * @param idCol
     */
    public void setIdCol(java.lang.String idCol) {
        this.idCol = idCol;
    }


    /**
     * Gets the p100T9 value for this Incoterms.
     * 
     * @return p100T9
     */
    public java.lang.Short getP100T9() {
        return p100T9;
    }


    /**
     * Sets the p100T9 value for this Incoterms.
     * 
     * @param p100T9
     */
    public void setP100T9(java.lang.Short p100T9) {
        this.p100T9 = p100T9;
    }


    /**
     * Gets the p101T9 value for this Incoterms.
     * 
     * @return p101T9
     */
    public com.sbs.service.Signer[] getP101T9() {
        return p101T9;
    }


    /**
     * Sets the p101T9 value for this Incoterms.
     * 
     * @param p101T9
     */
    public void setP101T9(com.sbs.service.Signer[] p101T9) {
        this.p101T9 = p101T9;
    }

    public com.sbs.service.Signer getP101T9(int i) {
        return this.p101T9[i];
    }

    public void setP101T9(int i, com.sbs.service.Signer _value) {
        this.p101T9[i] = _value;
    }


    /**
     * Gets the p2T9 value for this Incoterms.
     * 
     * @return p2T9
     */
    public java.lang.String getP2T9() {
        return p2T9;
    }


    /**
     * Sets the p2T9 value for this Incoterms.
     * 
     * @param p2T9
     */
    public void setP2T9(java.lang.String p2T9) {
        this.p2T9 = p2T9;
    }


    /**
     * Gets the p3T9 value for this Incoterms.
     * 
     * @return p3T9
     */
    public java.lang.String getP3T9() {
        return p3T9;
    }


    /**
     * Sets the p3T9 value for this Incoterms.
     * 
     * @param p3T9
     */
    public void setP3T9(java.lang.String p3T9) {
        this.p3T9 = p3T9;
    }


    /**
     * Gets the p7T9 value for this Incoterms.
     * 
     * @return p7T9
     */
    public java.lang.String getP7T9() {
        return p7T9;
    }


    /**
     * Sets the p7T9 value for this Incoterms.
     * 
     * @param p7T9
     */
    public void setP7T9(java.lang.String p7T9) {
        this.p7T9 = p7T9;
    }


    /**
     * Gets the p8T9 value for this Incoterms.
     * 
     * @return p8T9
     */
    public java.lang.Integer getP8T9() {
        return p8T9;
    }


    /**
     * Sets the p8T9 value for this Incoterms.
     * 
     * @param p8T9
     */
    public void setP8T9(java.lang.Integer p8T9) {
        this.p8T9 = p8T9;
    }


    /**
     * Gets the p9T9 value for this Incoterms.
     * 
     * @return p9T9
     */
    public java.util.Calendar getP9T9() {
        return p9T9;
    }


    /**
     * Sets the p9T9 value for this Incoterms.
     * 
     * @param p9T9
     */
    public void setP9T9(java.util.Calendar p9T9) {
        this.p9T9 = p9T9;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Incoterms)) return false;
        Incoterms other = (Incoterms) obj;
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
            ((this.p100T9==null && other.getP100T9()==null) || 
             (this.p100T9!=null &&
              this.p100T9.equals(other.getP100T9()))) &&
            ((this.p101T9==null && other.getP101T9()==null) || 
             (this.p101T9!=null &&
              java.util.Arrays.equals(this.p101T9, other.getP101T9()))) &&
            ((this.p2T9==null && other.getP2T9()==null) || 
             (this.p2T9!=null &&
              this.p2T9.equals(other.getP2T9()))) &&
            ((this.p3T9==null && other.getP3T9()==null) || 
             (this.p3T9!=null &&
              this.p3T9.equals(other.getP3T9()))) &&
            ((this.p7T9==null && other.getP7T9()==null) || 
             (this.p7T9!=null &&
              this.p7T9.equals(other.getP7T9()))) &&
            ((this.p8T9==null && other.getP8T9()==null) || 
             (this.p8T9!=null &&
              this.p8T9.equals(other.getP8T9()))) &&
            ((this.p9T9==null && other.getP9T9()==null) || 
             (this.p9T9!=null &&
              this.p9T9.equals(other.getP9T9())));
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
        if (getP100T9() != null) {
            _hashCode += getP100T9().hashCode();
        }
        if (getP101T9() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getP101T9());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getP101T9(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getP2T9() != null) {
            _hashCode += getP2T9().hashCode();
        }
        if (getP3T9() != null) {
            _hashCode += getP3T9().hashCode();
        }
        if (getP7T9() != null) {
            _hashCode += getP7T9().hashCode();
        }
        if (getP8T9() != null) {
            _hashCode += getP8T9().hashCode();
        }
        if (getP9T9() != null) {
            _hashCode += getP9T9().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Incoterms.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "incoterms"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idCol");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idCol"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p100T9");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p100T9"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p101T9");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p101T9"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "signer"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p2T9");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p2T9"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p3T9");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p3T9"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p7T9");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p7T9"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p8T9");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p8T9"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p9T9");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p9T9"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
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
