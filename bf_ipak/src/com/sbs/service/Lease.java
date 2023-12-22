/**
 * Lease.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sbs.service;

public class Lease  implements java.io.Serializable {
    private java.lang.String idCol;

    private java.lang.Integer p0T50;

    private java.lang.Short p100T50;

    private com.sbs.service.Signer[] p101T50;

    private java.util.Calendar p2T50;

    private java.lang.String p3T50;

    private java.lang.Double p4T50;

    private java.lang.Integer p5T50;

    private java.lang.String p6T50;

    private java.util.Calendar p9T50;

    public Lease() {
    }

    public Lease(
           java.lang.String idCol,
           java.lang.Integer p0T50,
           java.lang.Short p100T50,
           com.sbs.service.Signer[] p101T50,
           java.util.Calendar p2T50,
           java.lang.String p3T50,
           java.lang.Double p4T50,
           java.lang.Integer p5T50,
           java.lang.String p6T50,
           java.util.Calendar p9T50) {
           this.idCol = idCol;
           this.p0T50 = p0T50;
           this.p100T50 = p100T50;
           this.p101T50 = p101T50;
           this.p2T50 = p2T50;
           this.p3T50 = p3T50;
           this.p4T50 = p4T50;
           this.p5T50 = p5T50;
           this.p6T50 = p6T50;
           this.p9T50 = p9T50;
    }


    /**
     * Gets the idCol value for this Lease.
     * 
     * @return idCol
     */
    public java.lang.String getIdCol() {
        return idCol;
    }


    /**
     * Sets the idCol value for this Lease.
     * 
     * @param idCol
     */
    public void setIdCol(java.lang.String idCol) {
        this.idCol = idCol;
    }


    /**
     * Gets the p0T50 value for this Lease.
     * 
     * @return p0T50
     */
    public java.lang.Integer getP0T50() {
        return p0T50;
    }


    /**
     * Sets the p0T50 value for this Lease.
     * 
     * @param p0T50
     */
    public void setP0T50(java.lang.Integer p0T50) {
        this.p0T50 = p0T50;
    }


    /**
     * Gets the p100T50 value for this Lease.
     * 
     * @return p100T50
     */
    public java.lang.Short getP100T50() {
        return p100T50;
    }


    /**
     * Sets the p100T50 value for this Lease.
     * 
     * @param p100T50
     */
    public void setP100T50(java.lang.Short p100T50) {
        this.p100T50 = p100T50;
    }


    /**
     * Gets the p101T50 value for this Lease.
     * 
     * @return p101T50
     */
    public com.sbs.service.Signer[] getP101T50() {
        return p101T50;
    }


    /**
     * Sets the p101T50 value for this Lease.
     * 
     * @param p101T50
     */
    public void setP101T50(com.sbs.service.Signer[] p101T50) {
        this.p101T50 = p101T50;
    }

    public com.sbs.service.Signer getP101T50(int i) {
        return this.p101T50[i];
    }

    public void setP101T50(int i, com.sbs.service.Signer _value) {
        this.p101T50[i] = _value;
    }


    /**
     * Gets the p2T50 value for this Lease.
     * 
     * @return p2T50
     */
    public java.util.Calendar getP2T50() {
        return p2T50;
    }


    /**
     * Sets the p2T50 value for this Lease.
     * 
     * @param p2T50
     */
    public void setP2T50(java.util.Calendar p2T50) {
        this.p2T50 = p2T50;
    }


    /**
     * Gets the p3T50 value for this Lease.
     * 
     * @return p3T50
     */
    public java.lang.String getP3T50() {
        return p3T50;
    }


    /**
     * Sets the p3T50 value for this Lease.
     * 
     * @param p3T50
     */
    public void setP3T50(java.lang.String p3T50) {
        this.p3T50 = p3T50;
    }


    /**
     * Gets the p4T50 value for this Lease.
     * 
     * @return p4T50
     */
    public java.lang.Double getP4T50() {
        return p4T50;
    }


    /**
     * Sets the p4T50 value for this Lease.
     * 
     * @param p4T50
     */
    public void setP4T50(java.lang.Double p4T50) {
        this.p4T50 = p4T50;
    }


    /**
     * Gets the p5T50 value for this Lease.
     * 
     * @return p5T50
     */
    public java.lang.Integer getP5T50() {
        return p5T50;
    }


    /**
     * Sets the p5T50 value for this Lease.
     * 
     * @param p5T50
     */
    public void setP5T50(java.lang.Integer p5T50) {
        this.p5T50 = p5T50;
    }


    /**
     * Gets the p6T50 value for this Lease.
     * 
     * @return p6T50
     */
    public java.lang.String getP6T50() {
        return p6T50;
    }


    /**
     * Sets the p6T50 value for this Lease.
     * 
     * @param p6T50
     */
    public void setP6T50(java.lang.String p6T50) {
        this.p6T50 = p6T50;
    }


    /**
     * Gets the p9T50 value for this Lease.
     * 
     * @return p9T50
     */
    public java.util.Calendar getP9T50() {
        return p9T50;
    }


    /**
     * Sets the p9T50 value for this Lease.
     * 
     * @param p9T50
     */
    public void setP9T50(java.util.Calendar p9T50) {
        this.p9T50 = p9T50;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Lease)) return false;
        Lease other = (Lease) obj;
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
            ((this.p0T50==null && other.getP0T50()==null) || 
             (this.p0T50!=null &&
              this.p0T50.equals(other.getP0T50()))) &&
            ((this.p100T50==null && other.getP100T50()==null) || 
             (this.p100T50!=null &&
              this.p100T50.equals(other.getP100T50()))) &&
            ((this.p101T50==null && other.getP101T50()==null) || 
             (this.p101T50!=null &&
              java.util.Arrays.equals(this.p101T50, other.getP101T50()))) &&
            ((this.p2T50==null && other.getP2T50()==null) || 
             (this.p2T50!=null &&
              this.p2T50.equals(other.getP2T50()))) &&
            ((this.p3T50==null && other.getP3T50()==null) || 
             (this.p3T50!=null &&
              this.p3T50.equals(other.getP3T50()))) &&
            ((this.p4T50==null && other.getP4T50()==null) || 
             (this.p4T50!=null &&
              this.p4T50.equals(other.getP4T50()))) &&
            ((this.p5T50==null && other.getP5T50()==null) || 
             (this.p5T50!=null &&
              this.p5T50.equals(other.getP5T50()))) &&
            ((this.p6T50==null && other.getP6T50()==null) || 
             (this.p6T50!=null &&
              this.p6T50.equals(other.getP6T50()))) &&
            ((this.p9T50==null && other.getP9T50()==null) || 
             (this.p9T50!=null &&
              this.p9T50.equals(other.getP9T50())));
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
        if (getP0T50() != null) {
            _hashCode += getP0T50().hashCode();
        }
        if (getP100T50() != null) {
            _hashCode += getP100T50().hashCode();
        }
        if (getP101T50() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getP101T50());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getP101T50(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getP2T50() != null) {
            _hashCode += getP2T50().hashCode();
        }
        if (getP3T50() != null) {
            _hashCode += getP3T50().hashCode();
        }
        if (getP4T50() != null) {
            _hashCode += getP4T50().hashCode();
        }
        if (getP5T50() != null) {
            _hashCode += getP5T50().hashCode();
        }
        if (getP6T50() != null) {
            _hashCode += getP6T50().hashCode();
        }
        if (getP9T50() != null) {
            _hashCode += getP9T50().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Lease.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "lease"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idCol");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idCol"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p0T50");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p0T50"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p100T50");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p100T50"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p101T50");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p101T50"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "signer"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p2T50");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p2T50"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p3T50");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p3T50"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p4T50");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p4T50"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p5T50");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p5T50"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p6T50");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p6T50"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p9T50");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p9T50"));
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
