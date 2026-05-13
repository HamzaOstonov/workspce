/**
 * Specification.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sbs.service;

public class Specification  implements java.io.Serializable {
    private com.sbs.service.Goods[] goods;

    private java.lang.String idCol;

    private java.lang.Short p100T3;

    private com.sbs.service.Signer[] p101T3;

    private java.lang.String p11T3;

    private java.lang.Short p12T3;

    private java.lang.Integer p13T3;

    private java.util.Calendar p14T3;

    private java.util.Calendar p3T3;

    private java.lang.Double p4T3;

    private java.lang.String p5T3;

    private java.lang.String p6T3;

    private java.util.Calendar p7T3;

    private java.lang.Short p8T3;

    private com.sbs.service.TransCost transCosts;

    private com.sbs.service.Work[] works;

    public Specification() {
    }

    public Specification(
           com.sbs.service.Goods[] goods,
           java.lang.String idCol,
           java.lang.Short p100T3,
           com.sbs.service.Signer[] p101T3,
           java.lang.String p11T3,
           java.lang.Short p12T3,
           java.lang.Integer p13T3,
           java.util.Calendar p14T3,
           java.util.Calendar p3T3,
           java.lang.Double p4T3,
           java.lang.String p5T3,
           java.lang.String p6T3,
           java.util.Calendar p7T3,
           java.lang.Short p8T3,
           com.sbs.service.TransCost transCosts,
           com.sbs.service.Work[] works) {
           this.goods = goods;
           this.idCol = idCol;
           this.p100T3 = p100T3;
           this.p101T3 = p101T3;
           this.p11T3 = p11T3;
           this.p12T3 = p12T3;
           this.p13T3 = p13T3;
           this.p14T3 = p14T3;
           this.p3T3 = p3T3;
           this.p4T3 = p4T3;
           this.p5T3 = p5T3;
           this.p6T3 = p6T3;
           this.p7T3 = p7T3;
           this.p8T3 = p8T3;
           this.transCosts = transCosts;
           this.works = works;
    }


    /**
     * Gets the goods value for this Specification.
     * 
     * @return goods
     */
    public com.sbs.service.Goods[] getGoods() {
        return goods;
    }


    /**
     * Sets the goods value for this Specification.
     * 
     * @param goods
     */
    public void setGoods(com.sbs.service.Goods[] goods) {
        this.goods = goods;
    }

    public com.sbs.service.Goods getGoods(int i) {
        return this.goods[i];
    }

    public void setGoods(int i, com.sbs.service.Goods _value) {
        this.goods[i] = _value;
    }


    /**
     * Gets the idCol value for this Specification.
     * 
     * @return idCol
     */
    public java.lang.String getIdCol() {
        return idCol;
    }


    /**
     * Sets the idCol value for this Specification.
     * 
     * @param idCol
     */
    public void setIdCol(java.lang.String idCol) {
        this.idCol = idCol;
    }


    /**
     * Gets the p100T3 value for this Specification.
     * 
     * @return p100T3
     */
    public java.lang.Short getP100T3() {
        return p100T3;
    }


    /**
     * Sets the p100T3 value for this Specification.
     * 
     * @param p100T3
     */
    public void setP100T3(java.lang.Short p100T3) {
        this.p100T3 = p100T3;
    }


    /**
     * Gets the p101T3 value for this Specification.
     * 
     * @return p101T3
     */
    public com.sbs.service.Signer[] getP101T3() {
        return p101T3;
    }


    /**
     * Sets the p101T3 value for this Specification.
     * 
     * @param p101T3
     */
    public void setP101T3(com.sbs.service.Signer[] p101T3) {
        this.p101T3 = p101T3;
    }

    public com.sbs.service.Signer getP101T3(int i) {
        return this.p101T3[i];
    }

    public void setP101T3(int i, com.sbs.service.Signer _value) {
        this.p101T3[i] = _value;
    }


    /**
     * Gets the p11T3 value for this Specification.
     * 
     * @return p11T3
     */
    public java.lang.String getP11T3() {
        return p11T3;
    }


    /**
     * Sets the p11T3 value for this Specification.
     * 
     * @param p11T3
     */
    public void setP11T3(java.lang.String p11T3) {
        this.p11T3 = p11T3;
    }


    /**
     * Gets the p12T3 value for this Specification.
     * 
     * @return p12T3
     */
    public java.lang.Short getP12T3() {
        return p12T3;
    }


    /**
     * Sets the p12T3 value for this Specification.
     * 
     * @param p12T3
     */
    public void setP12T3(java.lang.Short p12T3) {
        this.p12T3 = p12T3;
    }


    /**
     * Gets the p13T3 value for this Specification.
     * 
     * @return p13T3
     */
    public java.lang.Integer getP13T3() {
        return p13T3;
    }


    /**
     * Sets the p13T3 value for this Specification.
     * 
     * @param p13T3
     */
    public void setP13T3(java.lang.Integer p13T3) {
        this.p13T3 = p13T3;
    }


    /**
     * Gets the p14T3 value for this Specification.
     * 
     * @return p14T3
     */
    public java.util.Calendar getP14T3() {
        return p14T3;
    }


    /**
     * Sets the p14T3 value for this Specification.
     * 
     * @param p14T3
     */
    public void setP14T3(java.util.Calendar p14T3) {
        this.p14T3 = p14T3;
    }


    /**
     * Gets the p3T3 value for this Specification.
     * 
     * @return p3T3
     */
    public java.util.Calendar getP3T3() {
        return p3T3;
    }


    /**
     * Sets the p3T3 value for this Specification.
     * 
     * @param p3T3
     */
    public void setP3T3(java.util.Calendar p3T3) {
        this.p3T3 = p3T3;
    }


    /**
     * Gets the p4T3 value for this Specification.
     * 
     * @return p4T3
     */
    public java.lang.Double getP4T3() {
        return p4T3;
    }


    /**
     * Sets the p4T3 value for this Specification.
     * 
     * @param p4T3
     */
    public void setP4T3(java.lang.Double p4T3) {
        this.p4T3 = p4T3;
    }


    /**
     * Gets the p5T3 value for this Specification.
     * 
     * @return p5T3
     */
    public java.lang.String getP5T3() {
        return p5T3;
    }


    /**
     * Sets the p5T3 value for this Specification.
     * 
     * @param p5T3
     */
    public void setP5T3(java.lang.String p5T3) {
        this.p5T3 = p5T3;
    }


    /**
     * Gets the p6T3 value for this Specification.
     * 
     * @return p6T3
     */
    public java.lang.String getP6T3() {
        return p6T3;
    }


    /**
     * Sets the p6T3 value for this Specification.
     * 
     * @param p6T3
     */
    public void setP6T3(java.lang.String p6T3) {
        this.p6T3 = p6T3;
    }


    /**
     * Gets the p7T3 value for this Specification.
     * 
     * @return p7T3
     */
    public java.util.Calendar getP7T3() {
        return p7T3;
    }


    /**
     * Sets the p7T3 value for this Specification.
     * 
     * @param p7T3
     */
    public void setP7T3(java.util.Calendar p7T3) {
        this.p7T3 = p7T3;
    }


    /**
     * Gets the p8T3 value for this Specification.
     * 
     * @return p8T3
     */
    public java.lang.Short getP8T3() {
        return p8T3;
    }


    /**
     * Sets the p8T3 value for this Specification.
     * 
     * @param p8T3
     */
    public void setP8T3(java.lang.Short p8T3) {
        this.p8T3 = p8T3;
    }


    /**
     * Gets the transCosts value for this Specification.
     * 
     * @return transCosts
     */
    public com.sbs.service.TransCost getTransCosts() {
        return transCosts;
    }


    /**
     * Sets the transCosts value for this Specification.
     * 
     * @param transCosts
     */
    public void setTransCosts(com.sbs.service.TransCost transCosts) {
        this.transCosts = transCosts;
    }


    /**
     * Gets the works value for this Specification.
     * 
     * @return works
     */
    public com.sbs.service.Work[] getWorks() {
        return works;
    }


    /**
     * Sets the works value for this Specification.
     * 
     * @param works
     */
    public void setWorks(com.sbs.service.Work[] works) {
        this.works = works;
    }

    public com.sbs.service.Work getWorks(int i) {
        return this.works[i];
    }

    public void setWorks(int i, com.sbs.service.Work _value) {
        this.works[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Specification)) return false;
        Specification other = (Specification) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.goods==null && other.getGoods()==null) || 
             (this.goods!=null &&
              java.util.Arrays.equals(this.goods, other.getGoods()))) &&
            ((this.idCol==null && other.getIdCol()==null) || 
             (this.idCol!=null &&
              this.idCol.equals(other.getIdCol()))) &&
            ((this.p100T3==null && other.getP100T3()==null) || 
             (this.p100T3!=null &&
              this.p100T3.equals(other.getP100T3()))) &&
            ((this.p101T3==null && other.getP101T3()==null) || 
             (this.p101T3!=null &&
              java.util.Arrays.equals(this.p101T3, other.getP101T3()))) &&
            ((this.p11T3==null && other.getP11T3()==null) || 
             (this.p11T3!=null &&
              this.p11T3.equals(other.getP11T3()))) &&
            ((this.p12T3==null && other.getP12T3()==null) || 
             (this.p12T3!=null &&
              this.p12T3.equals(other.getP12T3()))) &&
            ((this.p13T3==null && other.getP13T3()==null) || 
             (this.p13T3!=null &&
              this.p13T3.equals(other.getP13T3()))) &&
            ((this.p14T3==null && other.getP14T3()==null) || 
             (this.p14T3!=null &&
              this.p14T3.equals(other.getP14T3()))) &&
            ((this.p3T3==null && other.getP3T3()==null) || 
             (this.p3T3!=null &&
              this.p3T3.equals(other.getP3T3()))) &&
            ((this.p4T3==null && other.getP4T3()==null) || 
             (this.p4T3!=null &&
              this.p4T3.equals(other.getP4T3()))) &&
            ((this.p5T3==null && other.getP5T3()==null) || 
             (this.p5T3!=null &&
              this.p5T3.equals(other.getP5T3()))) &&
            ((this.p6T3==null && other.getP6T3()==null) || 
             (this.p6T3!=null &&
              this.p6T3.equals(other.getP6T3()))) &&
            ((this.p7T3==null && other.getP7T3()==null) || 
             (this.p7T3!=null &&
              this.p7T3.equals(other.getP7T3()))) &&
            ((this.p8T3==null && other.getP8T3()==null) || 
             (this.p8T3!=null &&
              this.p8T3.equals(other.getP8T3()))) &&
            ((this.transCosts==null && other.getTransCosts()==null) || 
             (this.transCosts!=null &&
              this.transCosts.equals(other.getTransCosts()))) &&
            ((this.works==null && other.getWorks()==null) || 
             (this.works!=null &&
              java.util.Arrays.equals(this.works, other.getWorks())));
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
        if (getGoods() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getGoods());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getGoods(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getIdCol() != null) {
            _hashCode += getIdCol().hashCode();
        }
        if (getP100T3() != null) {
            _hashCode += getP100T3().hashCode();
        }
        if (getP101T3() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getP101T3());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getP101T3(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getP11T3() != null) {
            _hashCode += getP11T3().hashCode();
        }
        if (getP12T3() != null) {
            _hashCode += getP12T3().hashCode();
        }
        if (getP13T3() != null) {
            _hashCode += getP13T3().hashCode();
        }
        if (getP14T3() != null) {
            _hashCode += getP14T3().hashCode();
        }
        if (getP3T3() != null) {
            _hashCode += getP3T3().hashCode();
        }
        if (getP4T3() != null) {
            _hashCode += getP4T3().hashCode();
        }
        if (getP5T3() != null) {
            _hashCode += getP5T3().hashCode();
        }
        if (getP6T3() != null) {
            _hashCode += getP6T3().hashCode();
        }
        if (getP7T3() != null) {
            _hashCode += getP7T3().hashCode();
        }
        if (getP8T3() != null) {
            _hashCode += getP8T3().hashCode();
        }
        if (getTransCosts() != null) {
            _hashCode += getTransCosts().hashCode();
        }
        if (getWorks() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getWorks());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getWorks(), i);
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
        new org.apache.axis.description.TypeDesc(Specification.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "specification"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("goods");
        elemField.setXmlName(new javax.xml.namespace.QName("", "goods"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "goods"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idCol");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idCol"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p100T3");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p100T3"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p101T3");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p101T3"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "signer"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p11T3");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p11T3"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p12T3");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p12T3"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p13T3");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p13T3"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p14T3");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p14T3"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p3T3");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p3T3"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p4T3");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p4T3"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p5T3");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p5T3"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p6T3");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p6T3"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p7T3");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p7T3"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p8T3");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p8T3"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transCosts");
        elemField.setXmlName(new javax.xml.namespace.QName("", "transCosts"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "transCost"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("works");
        elemField.setXmlName(new javax.xml.namespace.QName("", "works"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "work"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
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
