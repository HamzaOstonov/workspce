/**
 * Garant.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sbs.service;

public class Garant  implements java.io.Serializable {
    private com.sbs.service.GarantSumChange[] garantSumChanges;

    private com.sbs.service.GarantTimeChange[] garantTimeChanges;

    private java.lang.String idCol;

    private java.lang.Integer p0T18;

    private java.lang.Short p100T18;

    private com.sbs.service.Signer[] p101T18;

    private java.util.Calendar p10T18;

    private java.lang.Integer p11T18;

    private java.lang.String p12T18;

    private java.lang.Short p15T18;

    private java.util.Calendar p16T18;

    private java.lang.String p2T18;

    private java.lang.String p3T18;

    private java.util.Calendar p4T18;

    private java.lang.String p5T18;

    private java.lang.Double p6T18;

    private java.lang.Double p7T18;

    private java.lang.Double p8T18;

    private java.lang.Double p9T18;

    public Garant() {
    }

    public Garant(
           com.sbs.service.GarantSumChange[] garantSumChanges,
           com.sbs.service.GarantTimeChange[] garantTimeChanges,
           java.lang.String idCol,
           java.lang.Integer p0T18,
           java.lang.Short p100T18,
           com.sbs.service.Signer[] p101T18,
           java.util.Calendar p10T18,
           java.lang.Integer p11T18,
           java.lang.String p12T18,
           java.lang.Short p15T18,
           java.util.Calendar p16T18,
           java.lang.String p2T18,
           java.lang.String p3T18,
           java.util.Calendar p4T18,
           java.lang.String p5T18,
           java.lang.Double p6T18,
           java.lang.Double p7T18,
           java.lang.Double p8T18,
           java.lang.Double p9T18) {
           this.garantSumChanges = garantSumChanges;
           this.garantTimeChanges = garantTimeChanges;
           this.idCol = idCol;
           this.p0T18 = p0T18;
           this.p100T18 = p100T18;
           this.p101T18 = p101T18;
           this.p10T18 = p10T18;
           this.p11T18 = p11T18;
           this.p12T18 = p12T18;
           this.p15T18 = p15T18;
           this.p16T18 = p16T18;
           this.p2T18 = p2T18;
           this.p3T18 = p3T18;
           this.p4T18 = p4T18;
           this.p5T18 = p5T18;
           this.p6T18 = p6T18;
           this.p7T18 = p7T18;
           this.p8T18 = p8T18;
           this.p9T18 = p9T18;
    }


    /**
     * Gets the garantSumChanges value for this Garant.
     * 
     * @return garantSumChanges
     */
    public com.sbs.service.GarantSumChange[] getGarantSumChanges() {
        return garantSumChanges;
    }


    /**
     * Sets the garantSumChanges value for this Garant.
     * 
     * @param garantSumChanges
     */
    public void setGarantSumChanges(com.sbs.service.GarantSumChange[] garantSumChanges) {
        this.garantSumChanges = garantSumChanges;
    }

    public com.sbs.service.GarantSumChange getGarantSumChanges(int i) {
        return this.garantSumChanges[i];
    }

    public void setGarantSumChanges(int i, com.sbs.service.GarantSumChange _value) {
        this.garantSumChanges[i] = _value;
    }


    /**
     * Gets the garantTimeChanges value for this Garant.
     * 
     * @return garantTimeChanges
     */
    public com.sbs.service.GarantTimeChange[] getGarantTimeChanges() {
        return garantTimeChanges;
    }


    /**
     * Sets the garantTimeChanges value for this Garant.
     * 
     * @param garantTimeChanges
     */
    public void setGarantTimeChanges(com.sbs.service.GarantTimeChange[] garantTimeChanges) {
        this.garantTimeChanges = garantTimeChanges;
    }

    public com.sbs.service.GarantTimeChange getGarantTimeChanges(int i) {
        return this.garantTimeChanges[i];
    }

    public void setGarantTimeChanges(int i, com.sbs.service.GarantTimeChange _value) {
        this.garantTimeChanges[i] = _value;
    }


    /**
     * Gets the idCol value for this Garant.
     * 
     * @return idCol
     */
    public java.lang.String getIdCol() {
        return idCol;
    }


    /**
     * Sets the idCol value for this Garant.
     * 
     * @param idCol
     */
    public void setIdCol(java.lang.String idCol) {
        this.idCol = idCol;
    }


    /**
     * Gets the p0T18 value for this Garant.
     * 
     * @return p0T18
     */
    public java.lang.Integer getP0T18() {
        return p0T18;
    }


    /**
     * Sets the p0T18 value for this Garant.
     * 
     * @param p0T18
     */
    public void setP0T18(java.lang.Integer p0T18) {
        this.p0T18 = p0T18;
    }


    /**
     * Gets the p100T18 value for this Garant.
     * 
     * @return p100T18
     */
    public java.lang.Short getP100T18() {
        return p100T18;
    }


    /**
     * Sets the p100T18 value for this Garant.
     * 
     * @param p100T18
     */
    public void setP100T18(java.lang.Short p100T18) {
        this.p100T18 = p100T18;
    }


    /**
     * Gets the p101T18 value for this Garant.
     * 
     * @return p101T18
     */
    public com.sbs.service.Signer[] getP101T18() {
        return p101T18;
    }


    /**
     * Sets the p101T18 value for this Garant.
     * 
     * @param p101T18
     */
    public void setP101T18(com.sbs.service.Signer[] p101T18) {
        this.p101T18 = p101T18;
    }

    public com.sbs.service.Signer getP101T18(int i) {
        return this.p101T18[i];
    }

    public void setP101T18(int i, com.sbs.service.Signer _value) {
        this.p101T18[i] = _value;
    }


    /**
     * Gets the p10T18 value for this Garant.
     * 
     * @return p10T18
     */
    public java.util.Calendar getP10T18() {
        return p10T18;
    }


    /**
     * Sets the p10T18 value for this Garant.
     * 
     * @param p10T18
     */
    public void setP10T18(java.util.Calendar p10T18) {
        this.p10T18 = p10T18;
    }


    /**
     * Gets the p11T18 value for this Garant.
     * 
     * @return p11T18
     */
    public java.lang.Integer getP11T18() {
        return p11T18;
    }


    /**
     * Sets the p11T18 value for this Garant.
     * 
     * @param p11T18
     */
    public void setP11T18(java.lang.Integer p11T18) {
        this.p11T18 = p11T18;
    }


    /**
     * Gets the p12T18 value for this Garant.
     * 
     * @return p12T18
     */
    public java.lang.String getP12T18() {
        return p12T18;
    }


    /**
     * Sets the p12T18 value for this Garant.
     * 
     * @param p12T18
     */
    public void setP12T18(java.lang.String p12T18) {
        this.p12T18 = p12T18;
    }


    /**
     * Gets the p15T18 value for this Garant.
     * 
     * @return p15T18
     */
    public java.lang.Short getP15T18() {
        return p15T18;
    }


    /**
     * Sets the p15T18 value for this Garant.
     * 
     * @param p15T18
     */
    public void setP15T18(java.lang.Short p15T18) {
        this.p15T18 = p15T18;
    }


    /**
     * Gets the p16T18 value for this Garant.
     * 
     * @return p16T18
     */
    public java.util.Calendar getP16T18() {
        return p16T18;
    }


    /**
     * Sets the p16T18 value for this Garant.
     * 
     * @param p16T18
     */
    public void setP16T18(java.util.Calendar p16T18) {
        this.p16T18 = p16T18;
    }


    /**
     * Gets the p2T18 value for this Garant.
     * 
     * @return p2T18
     */
    public java.lang.String getP2T18() {
        return p2T18;
    }


    /**
     * Sets the p2T18 value for this Garant.
     * 
     * @param p2T18
     */
    public void setP2T18(java.lang.String p2T18) {
        this.p2T18 = p2T18;
    }


    /**
     * Gets the p3T18 value for this Garant.
     * 
     * @return p3T18
     */
    public java.lang.String getP3T18() {
        return p3T18;
    }


    /**
     * Sets the p3T18 value for this Garant.
     * 
     * @param p3T18
     */
    public void setP3T18(java.lang.String p3T18) {
        this.p3T18 = p3T18;
    }


    /**
     * Gets the p4T18 value for this Garant.
     * 
     * @return p4T18
     */
    public java.util.Calendar getP4T18() {
        return p4T18;
    }


    /**
     * Sets the p4T18 value for this Garant.
     * 
     * @param p4T18
     */
    public void setP4T18(java.util.Calendar p4T18) {
        this.p4T18 = p4T18;
    }


    /**
     * Gets the p5T18 value for this Garant.
     * 
     * @return p5T18
     */
    public java.lang.String getP5T18() {
        return p5T18;
    }


    /**
     * Sets the p5T18 value for this Garant.
     * 
     * @param p5T18
     */
    public void setP5T18(java.lang.String p5T18) {
        this.p5T18 = p5T18;
    }


    /**
     * Gets the p6T18 value for this Garant.
     * 
     * @return p6T18
     */
    public java.lang.Double getP6T18() {
        return p6T18;
    }


    /**
     * Sets the p6T18 value for this Garant.
     * 
     * @param p6T18
     */
    public void setP6T18(java.lang.Double p6T18) {
        this.p6T18 = p6T18;
    }


    /**
     * Gets the p7T18 value for this Garant.
     * 
     * @return p7T18
     */
    public java.lang.Double getP7T18() {
        return p7T18;
    }


    /**
     * Sets the p7T18 value for this Garant.
     * 
     * @param p7T18
     */
    public void setP7T18(java.lang.Double p7T18) {
        this.p7T18 = p7T18;
    }


    /**
     * Gets the p8T18 value for this Garant.
     * 
     * @return p8T18
     */
    public java.lang.Double getP8T18() {
        return p8T18;
    }


    /**
     * Sets the p8T18 value for this Garant.
     * 
     * @param p8T18
     */
    public void setP8T18(java.lang.Double p8T18) {
        this.p8T18 = p8T18;
    }


    /**
     * Gets the p9T18 value for this Garant.
     * 
     * @return p9T18
     */
    public java.lang.Double getP9T18() {
        return p9T18;
    }


    /**
     * Sets the p9T18 value for this Garant.
     * 
     * @param p9T18
     */
    public void setP9T18(java.lang.Double p9T18) {
        this.p9T18 = p9T18;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Garant)) return false;
        Garant other = (Garant) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.garantSumChanges==null && other.getGarantSumChanges()==null) || 
             (this.garantSumChanges!=null &&
              java.util.Arrays.equals(this.garantSumChanges, other.getGarantSumChanges()))) &&
            ((this.garantTimeChanges==null && other.getGarantTimeChanges()==null) || 
             (this.garantTimeChanges!=null &&
              java.util.Arrays.equals(this.garantTimeChanges, other.getGarantTimeChanges()))) &&
            ((this.idCol==null && other.getIdCol()==null) || 
             (this.idCol!=null &&
              this.idCol.equals(other.getIdCol()))) &&
            ((this.p0T18==null && other.getP0T18()==null) || 
             (this.p0T18!=null &&
              this.p0T18.equals(other.getP0T18()))) &&
            ((this.p100T18==null && other.getP100T18()==null) || 
             (this.p100T18!=null &&
              this.p100T18.equals(other.getP100T18()))) &&
            ((this.p101T18==null && other.getP101T18()==null) || 
             (this.p101T18!=null &&
              java.util.Arrays.equals(this.p101T18, other.getP101T18()))) &&
            ((this.p10T18==null && other.getP10T18()==null) || 
             (this.p10T18!=null &&
              this.p10T18.equals(other.getP10T18()))) &&
            ((this.p11T18==null && other.getP11T18()==null) || 
             (this.p11T18!=null &&
              this.p11T18.equals(other.getP11T18()))) &&
            ((this.p12T18==null && other.getP12T18()==null) || 
             (this.p12T18!=null &&
              this.p12T18.equals(other.getP12T18()))) &&
            ((this.p15T18==null && other.getP15T18()==null) || 
             (this.p15T18!=null &&
              this.p15T18.equals(other.getP15T18()))) &&
            ((this.p16T18==null && other.getP16T18()==null) || 
             (this.p16T18!=null &&
              this.p16T18.equals(other.getP16T18()))) &&
            ((this.p2T18==null && other.getP2T18()==null) || 
             (this.p2T18!=null &&
              this.p2T18.equals(other.getP2T18()))) &&
            ((this.p3T18==null && other.getP3T18()==null) || 
             (this.p3T18!=null &&
              this.p3T18.equals(other.getP3T18()))) &&
            ((this.p4T18==null && other.getP4T18()==null) || 
             (this.p4T18!=null &&
              this.p4T18.equals(other.getP4T18()))) &&
            ((this.p5T18==null && other.getP5T18()==null) || 
             (this.p5T18!=null &&
              this.p5T18.equals(other.getP5T18()))) &&
            ((this.p6T18==null && other.getP6T18()==null) || 
             (this.p6T18!=null &&
              this.p6T18.equals(other.getP6T18()))) &&
            ((this.p7T18==null && other.getP7T18()==null) || 
             (this.p7T18!=null &&
              this.p7T18.equals(other.getP7T18()))) &&
            ((this.p8T18==null && other.getP8T18()==null) || 
             (this.p8T18!=null &&
              this.p8T18.equals(other.getP8T18()))) &&
            ((this.p9T18==null && other.getP9T18()==null) || 
             (this.p9T18!=null &&
              this.p9T18.equals(other.getP9T18())));
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
        if (getGarantSumChanges() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getGarantSumChanges());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getGarantSumChanges(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getGarantTimeChanges() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getGarantTimeChanges());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getGarantTimeChanges(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getIdCol() != null) {
            _hashCode += getIdCol().hashCode();
        }
        if (getP0T18() != null) {
            _hashCode += getP0T18().hashCode();
        }
        if (getP100T18() != null) {
            _hashCode += getP100T18().hashCode();
        }
        if (getP101T18() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getP101T18());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getP101T18(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getP10T18() != null) {
            _hashCode += getP10T18().hashCode();
        }
        if (getP11T18() != null) {
            _hashCode += getP11T18().hashCode();
        }
        if (getP12T18() != null) {
            _hashCode += getP12T18().hashCode();
        }
        if (getP15T18() != null) {
            _hashCode += getP15T18().hashCode();
        }
        if (getP16T18() != null) {
            _hashCode += getP16T18().hashCode();
        }
        if (getP2T18() != null) {
            _hashCode += getP2T18().hashCode();
        }
        if (getP3T18() != null) {
            _hashCode += getP3T18().hashCode();
        }
        if (getP4T18() != null) {
            _hashCode += getP4T18().hashCode();
        }
        if (getP5T18() != null) {
            _hashCode += getP5T18().hashCode();
        }
        if (getP6T18() != null) {
            _hashCode += getP6T18().hashCode();
        }
        if (getP7T18() != null) {
            _hashCode += getP7T18().hashCode();
        }
        if (getP8T18() != null) {
            _hashCode += getP8T18().hashCode();
        }
        if (getP9T18() != null) {
            _hashCode += getP9T18().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Garant.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "garant"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("garantSumChanges");
        elemField.setXmlName(new javax.xml.namespace.QName("", "garantSumChanges"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "garantSumChange"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("garantTimeChanges");
        elemField.setXmlName(new javax.xml.namespace.QName("", "garantTimeChanges"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "garantTimeChange"));
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
        elemField.setFieldName("p0T18");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p0T18"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p100T18");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p100T18"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p101T18");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p101T18"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "signer"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p10T18");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p10T18"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p11T18");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p11T18"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p12T18");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p12T18"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p15T18");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p15T18"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p16T18");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p16T18"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p2T18");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p2T18"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p3T18");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p3T18"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p4T18");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p4T18"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p5T18");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p5T18"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p6T18");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p6T18"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p7T18");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p7T18"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p8T18");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p8T18"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p9T18");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p9T18"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
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
