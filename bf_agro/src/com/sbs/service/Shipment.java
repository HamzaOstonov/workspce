/**
 * Shipment.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sbs.service;

public class Shipment  implements java.io.Serializable {
    private java.lang.String idCol;

    private java.lang.Short p100T14;

    private com.sbs.service.Signer[] p101T14;

    private java.util.Calendar p1T14;

    private java.lang.String p2T14;

    private java.lang.String p3T14;

    private java.lang.Double p4T14;

    private java.lang.Integer p7T14;

    private java.util.Calendar p8T14;

    public Shipment() {
    }

    public Shipment(
           java.lang.String idCol,
           java.lang.Short p100T14,
           com.sbs.service.Signer[] p101T14,
           java.util.Calendar p1T14,
           java.lang.String p2T14,
           java.lang.String p3T14,
           java.lang.Double p4T14,
           java.lang.Integer p7T14,
           java.util.Calendar p8T14) {
           this.idCol = idCol;
           this.p100T14 = p100T14;
           this.p101T14 = p101T14;
           this.p1T14 = p1T14;
           this.p2T14 = p2T14;
           this.p3T14 = p3T14;
           this.p4T14 = p4T14;
           this.p7T14 = p7T14;
           this.p8T14 = p8T14;
    }


    /**
     * Gets the idCol value for this Shipment.
     * 
     * @return idCol
     */
    public java.lang.String getIdCol() {
        return idCol;
    }


    /**
     * Sets the idCol value for this Shipment.
     * 
     * @param idCol
     */
    public void setIdCol(java.lang.String idCol) {
        this.idCol = idCol;
    }


    /**
     * Gets the p100T14 value for this Shipment.
     * 
     * @return p100T14
     */
    public java.lang.Short getP100T14() {
        return p100T14;
    }


    /**
     * Sets the p100T14 value for this Shipment.
     * 
     * @param p100T14
     */
    public void setP100T14(java.lang.Short p100T14) {
        this.p100T14 = p100T14;
    }


    /**
     * Gets the p101T14 value for this Shipment.
     * 
     * @return p101T14
     */
    public com.sbs.service.Signer[] getP101T14() {
        return p101T14;
    }


    /**
     * Sets the p101T14 value for this Shipment.
     * 
     * @param p101T14
     */
    public void setP101T14(com.sbs.service.Signer[] p101T14) {
        this.p101T14 = p101T14;
    }

    public com.sbs.service.Signer getP101T14(int i) {
        return this.p101T14[i];
    }

    public void setP101T14(int i, com.sbs.service.Signer _value) {
        this.p101T14[i] = _value;
    }


    /**
     * Gets the p1T14 value for this Shipment.
     * 
     * @return p1T14
     */
    public java.util.Calendar getP1T14() {
        return p1T14;
    }


    /**
     * Sets the p1T14 value for this Shipment.
     * 
     * @param p1T14
     */
    public void setP1T14(java.util.Calendar p1T14) {
        this.p1T14 = p1T14;
    }


    /**
     * Gets the p2T14 value for this Shipment.
     * 
     * @return p2T14
     */
    public java.lang.String getP2T14() {
        return p2T14;
    }


    /**
     * Sets the p2T14 value for this Shipment.
     * 
     * @param p2T14
     */
    public void setP2T14(java.lang.String p2T14) {
        this.p2T14 = p2T14;
    }


    /**
     * Gets the p3T14 value for this Shipment.
     * 
     * @return p3T14
     */
    public java.lang.String getP3T14() {
        return p3T14;
    }


    /**
     * Sets the p3T14 value for this Shipment.
     * 
     * @param p3T14
     */
    public void setP3T14(java.lang.String p3T14) {
        this.p3T14 = p3T14;
    }


    /**
     * Gets the p4T14 value for this Shipment.
     * 
     * @return p4T14
     */
    public java.lang.Double getP4T14() {
        return p4T14;
    }


    /**
     * Sets the p4T14 value for this Shipment.
     * 
     * @param p4T14
     */
    public void setP4T14(java.lang.Double p4T14) {
        this.p4T14 = p4T14;
    }


    /**
     * Gets the p7T14 value for this Shipment.
     * 
     * @return p7T14
     */
    public java.lang.Integer getP7T14() {
        return p7T14;
    }


    /**
     * Sets the p7T14 value for this Shipment.
     * 
     * @param p7T14
     */
    public void setP7T14(java.lang.Integer p7T14) {
        this.p7T14 = p7T14;
    }


    /**
     * Gets the p8T14 value for this Shipment.
     * 
     * @return p8T14
     */
    public java.util.Calendar getP8T14() {
        return p8T14;
    }


    /**
     * Sets the p8T14 value for this Shipment.
     * 
     * @param p8T14
     */
    public void setP8T14(java.util.Calendar p8T14) {
        this.p8T14 = p8T14;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Shipment)) return false;
        Shipment other = (Shipment) obj;
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
            ((this.p100T14==null && other.getP100T14()==null) || 
             (this.p100T14!=null &&
              this.p100T14.equals(other.getP100T14()))) &&
            ((this.p101T14==null && other.getP101T14()==null) || 
             (this.p101T14!=null &&
              java.util.Arrays.equals(this.p101T14, other.getP101T14()))) &&
            ((this.p1T14==null && other.getP1T14()==null) || 
             (this.p1T14!=null &&
              this.p1T14.equals(other.getP1T14()))) &&
            ((this.p2T14==null && other.getP2T14()==null) || 
             (this.p2T14!=null &&
              this.p2T14.equals(other.getP2T14()))) &&
            ((this.p3T14==null && other.getP3T14()==null) || 
             (this.p3T14!=null &&
              this.p3T14.equals(other.getP3T14()))) &&
            ((this.p4T14==null && other.getP4T14()==null) || 
             (this.p4T14!=null &&
              this.p4T14.equals(other.getP4T14()))) &&
            ((this.p7T14==null && other.getP7T14()==null) || 
             (this.p7T14!=null &&
              this.p7T14.equals(other.getP7T14()))) &&
            ((this.p8T14==null && other.getP8T14()==null) || 
             (this.p8T14!=null &&
              this.p8T14.equals(other.getP8T14())));
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
        if (getP100T14() != null) {
            _hashCode += getP100T14().hashCode();
        }
        if (getP101T14() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getP101T14());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getP101T14(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getP1T14() != null) {
            _hashCode += getP1T14().hashCode();
        }
        if (getP2T14() != null) {
            _hashCode += getP2T14().hashCode();
        }
        if (getP3T14() != null) {
            _hashCode += getP3T14().hashCode();
        }
        if (getP4T14() != null) {
            _hashCode += getP4T14().hashCode();
        }
        if (getP7T14() != null) {
            _hashCode += getP7T14().hashCode();
        }
        if (getP8T14() != null) {
            _hashCode += getP8T14().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Shipment.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "shipment"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idCol");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idCol"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p100T14");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p100T14"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p101T14");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p101T14"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "signer"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p1T14");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p1T14"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p2T14");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p2T14"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p3T14");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p3T14"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p4T14");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p4T14"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p7T14");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p7T14"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p8T14");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p8T14"));
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
