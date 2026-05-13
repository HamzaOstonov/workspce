/**
 * Work.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sbs.service;

public class Work  implements java.io.Serializable {
    private java.lang.String idCol;

    private java.lang.Short p100T6;

    private java.lang.Short p10T6;

    private java.lang.String p2T6;

    private java.lang.String p3T6;

    private java.lang.String p4T6;

    private java.lang.String p5T6;

    private java.lang.String p6T6;

    private java.lang.String p7T6;

    private java.lang.Double p8T6;

    private java.util.Calendar p99T6;

    public Work() {
    }

    public Work(
           java.lang.String idCol,
           java.lang.Short p100T6,
           java.lang.Short p10T6,
           java.lang.String p2T6,
           java.lang.String p3T6,
           java.lang.String p4T6,
           java.lang.String p5T6,
           java.lang.String p6T6,
           java.lang.String p7T6,
           java.lang.Double p8T6,
           java.util.Calendar p99T6) {
           this.idCol = idCol;
           this.p100T6 = p100T6;
           this.p10T6 = p10T6;
           this.p2T6 = p2T6;
           this.p3T6 = p3T6;
           this.p4T6 = p4T6;
           this.p5T6 = p5T6;
           this.p6T6 = p6T6;
           this.p7T6 = p7T6;
           this.p8T6 = p8T6;
           this.p99T6 = p99T6;
    }


    /**
     * Gets the idCol value for this Work.
     * 
     * @return idCol
     */
    public java.lang.String getIdCol() {
        return idCol;
    }


    /**
     * Sets the idCol value for this Work.
     * 
     * @param idCol
     */
    public void setIdCol(java.lang.String idCol) {
        this.idCol = idCol;
    }


    /**
     * Gets the p100T6 value for this Work.
     * 
     * @return p100T6
     */
    public java.lang.Short getP100T6() {
        return p100T6;
    }


    /**
     * Sets the p100T6 value for this Work.
     * 
     * @param p100T6
     */
    public void setP100T6(java.lang.Short p100T6) {
        this.p100T6 = p100T6;
    }


    /**
     * Gets the p10T6 value for this Work.
     * 
     * @return p10T6
     */
    public java.lang.Short getP10T6() {
        return p10T6;
    }


    /**
     * Sets the p10T6 value for this Work.
     * 
     * @param p10T6
     */
    public void setP10T6(java.lang.Short p10T6) {
        this.p10T6 = p10T6;
    }


    /**
     * Gets the p2T6 value for this Work.
     * 
     * @return p2T6
     */
    public java.lang.String getP2T6() {
        return p2T6;
    }


    /**
     * Sets the p2T6 value for this Work.
     * 
     * @param p2T6
     */
    public void setP2T6(java.lang.String p2T6) {
        this.p2T6 = p2T6;
    }


    /**
     * Gets the p3T6 value for this Work.
     * 
     * @return p3T6
     */
    public java.lang.String getP3T6() {
        return p3T6;
    }


    /**
     * Sets the p3T6 value for this Work.
     * 
     * @param p3T6
     */
    public void setP3T6(java.lang.String p3T6) {
        this.p3T6 = p3T6;
    }


    /**
     * Gets the p4T6 value for this Work.
     * 
     * @return p4T6
     */
    public java.lang.String getP4T6() {
        return p4T6;
    }


    /**
     * Sets the p4T6 value for this Work.
     * 
     * @param p4T6
     */
    public void setP4T6(java.lang.String p4T6) {
        this.p4T6 = p4T6;
    }


    /**
     * Gets the p5T6 value for this Work.
     * 
     * @return p5T6
     */
    public java.lang.String getP5T6() {
        return p5T6;
    }


    /**
     * Sets the p5T6 value for this Work.
     * 
     * @param p5T6
     */
    public void setP5T6(java.lang.String p5T6) {
        this.p5T6 = p5T6;
    }


    /**
     * Gets the p6T6 value for this Work.
     * 
     * @return p6T6
     */
    public java.lang.String getP6T6() {
        return p6T6;
    }


    /**
     * Sets the p6T6 value for this Work.
     * 
     * @param p6T6
     */
    public void setP6T6(java.lang.String p6T6) {
        this.p6T6 = p6T6;
    }


    /**
     * Gets the p7T6 value for this Work.
     * 
     * @return p7T6
     */
    public java.lang.String getP7T6() {
        return p7T6;
    }


    /**
     * Sets the p7T6 value for this Work.
     * 
     * @param p7T6
     */
    public void setP7T6(java.lang.String p7T6) {
        this.p7T6 = p7T6;
    }


    /**
     * Gets the p8T6 value for this Work.
     * 
     * @return p8T6
     */
    public java.lang.Double getP8T6() {
        return p8T6;
    }


    /**
     * Sets the p8T6 value for this Work.
     * 
     * @param p8T6
     */
    public void setP8T6(java.lang.Double p8T6) {
        this.p8T6 = p8T6;
    }


    /**
     * Gets the p99T6 value for this Work.
     * 
     * @return p99T6
     */
    public java.util.Calendar getP99T6() {
        return p99T6;
    }


    /**
     * Sets the p99T6 value for this Work.
     * 
     * @param p99T6
     */
    public void setP99T6(java.util.Calendar p99T6) {
        this.p99T6 = p99T6;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Work)) return false;
        Work other = (Work) obj;
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
            ((this.p100T6==null && other.getP100T6()==null) || 
             (this.p100T6!=null &&
              this.p100T6.equals(other.getP100T6()))) &&
            ((this.p10T6==null && other.getP10T6()==null) || 
             (this.p10T6!=null &&
              this.p10T6.equals(other.getP10T6()))) &&
            ((this.p2T6==null && other.getP2T6()==null) || 
             (this.p2T6!=null &&
              this.p2T6.equals(other.getP2T6()))) &&
            ((this.p3T6==null && other.getP3T6()==null) || 
             (this.p3T6!=null &&
              this.p3T6.equals(other.getP3T6()))) &&
            ((this.p4T6==null && other.getP4T6()==null) || 
             (this.p4T6!=null &&
              this.p4T6.equals(other.getP4T6()))) &&
            ((this.p5T6==null && other.getP5T6()==null) || 
             (this.p5T6!=null &&
              this.p5T6.equals(other.getP5T6()))) &&
            ((this.p6T6==null && other.getP6T6()==null) || 
             (this.p6T6!=null &&
              this.p6T6.equals(other.getP6T6()))) &&
            ((this.p7T6==null && other.getP7T6()==null) || 
             (this.p7T6!=null &&
              this.p7T6.equals(other.getP7T6()))) &&
            ((this.p8T6==null && other.getP8T6()==null) || 
             (this.p8T6!=null &&
              this.p8T6.equals(other.getP8T6()))) &&
            ((this.p99T6==null && other.getP99T6()==null) || 
             (this.p99T6!=null &&
              this.p99T6.equals(other.getP99T6())));
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
        if (getP100T6() != null) {
            _hashCode += getP100T6().hashCode();
        }
        if (getP10T6() != null) {
            _hashCode += getP10T6().hashCode();
        }
        if (getP2T6() != null) {
            _hashCode += getP2T6().hashCode();
        }
        if (getP3T6() != null) {
            _hashCode += getP3T6().hashCode();
        }
        if (getP4T6() != null) {
            _hashCode += getP4T6().hashCode();
        }
        if (getP5T6() != null) {
            _hashCode += getP5T6().hashCode();
        }
        if (getP6T6() != null) {
            _hashCode += getP6T6().hashCode();
        }
        if (getP7T6() != null) {
            _hashCode += getP7T6().hashCode();
        }
        if (getP8T6() != null) {
            _hashCode += getP8T6().hashCode();
        }
        if (getP99T6() != null) {
            _hashCode += getP99T6().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Work.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "work"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idCol");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idCol"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p100T6");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p100T6"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p10T6");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p10T6"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p2T6");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p2T6"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p3T6");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p3T6"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p4T6");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p4T6"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p5T6");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p5T6"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p6T6");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p6T6"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p7T6");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p7T6"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p8T6");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p8T6"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p99T6");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p99T6"));
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
