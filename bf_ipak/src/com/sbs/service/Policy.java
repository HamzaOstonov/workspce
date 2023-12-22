/**
 * Policy.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sbs.service;

public class Policy  implements java.io.Serializable {
    private java.lang.String idCol;

    private java.lang.Integer p0T32;

    private java.lang.Short p100T32;

    private com.sbs.service.Signer[] p101T32;

    private java.lang.Double p10T32;

    private java.util.Calendar p11T32;

    private java.lang.Integer p12T32;

    private java.lang.String p13T32;

    private java.lang.Short p16T32;

    private java.util.Calendar p17T32;

    private java.lang.String p2T32;

    private java.lang.String p3T32;

    private java.lang.String p4T32;

    private java.util.Calendar p5T32;

    private java.lang.String p6T32;

    private java.lang.Double p7T32;

    private java.lang.Double p8T32;

    private java.lang.Double p9T32;

    private com.sbs.service.PolicySumChange[] policySumChanges;

    private com.sbs.service.PolicyTimeChange[] policyTimeChanges;

    public Policy() {
    }

    public Policy(
           java.lang.String idCol,
           java.lang.Integer p0T32,
           java.lang.Short p100T32,
           com.sbs.service.Signer[] p101T32,
           java.lang.Double p10T32,
           java.util.Calendar p11T32,
           java.lang.Integer p12T32,
           java.lang.String p13T32,
           java.lang.Short p16T32,
           java.util.Calendar p17T32,
           java.lang.String p2T32,
           java.lang.String p3T32,
           java.lang.String p4T32,
           java.util.Calendar p5T32,
           java.lang.String p6T32,
           java.lang.Double p7T32,
           java.lang.Double p8T32,
           java.lang.Double p9T32,
           com.sbs.service.PolicySumChange[] policySumChanges,
           com.sbs.service.PolicyTimeChange[] policyTimeChanges) {
           this.idCol = idCol;
           this.p0T32 = p0T32;
           this.p100T32 = p100T32;
           this.p101T32 = p101T32;
           this.p10T32 = p10T32;
           this.p11T32 = p11T32;
           this.p12T32 = p12T32;
           this.p13T32 = p13T32;
           this.p16T32 = p16T32;
           this.p17T32 = p17T32;
           this.p2T32 = p2T32;
           this.p3T32 = p3T32;
           this.p4T32 = p4T32;
           this.p5T32 = p5T32;
           this.p6T32 = p6T32;
           this.p7T32 = p7T32;
           this.p8T32 = p8T32;
           this.p9T32 = p9T32;
           this.policySumChanges = policySumChanges;
           this.policyTimeChanges = policyTimeChanges;
    }


    /**
     * Gets the idCol value for this Policy.
     * 
     * @return idCol
     */
    public java.lang.String getIdCol() {
        return idCol;
    }


    /**
     * Sets the idCol value for this Policy.
     * 
     * @param idCol
     */
    public void setIdCol(java.lang.String idCol) {
        this.idCol = idCol;
    }


    /**
     * Gets the p0T32 value for this Policy.
     * 
     * @return p0T32
     */
    public java.lang.Integer getP0T32() {
        return p0T32;
    }


    /**
     * Sets the p0T32 value for this Policy.
     * 
     * @param p0T32
     */
    public void setP0T32(java.lang.Integer p0T32) {
        this.p0T32 = p0T32;
    }


    /**
     * Gets the p100T32 value for this Policy.
     * 
     * @return p100T32
     */
    public java.lang.Short getP100T32() {
        return p100T32;
    }


    /**
     * Sets the p100T32 value for this Policy.
     * 
     * @param p100T32
     */
    public void setP100T32(java.lang.Short p100T32) {
        this.p100T32 = p100T32;
    }


    /**
     * Gets the p101T32 value for this Policy.
     * 
     * @return p101T32
     */
    public com.sbs.service.Signer[] getP101T32() {
        return p101T32;
    }


    /**
     * Sets the p101T32 value for this Policy.
     * 
     * @param p101T32
     */
    public void setP101T32(com.sbs.service.Signer[] p101T32) {
        this.p101T32 = p101T32;
    }

    public com.sbs.service.Signer getP101T32(int i) {
        return this.p101T32[i];
    }

    public void setP101T32(int i, com.sbs.service.Signer _value) {
        this.p101T32[i] = _value;
    }


    /**
     * Gets the p10T32 value for this Policy.
     * 
     * @return p10T32
     */
    public java.lang.Double getP10T32() {
        return p10T32;
    }


    /**
     * Sets the p10T32 value for this Policy.
     * 
     * @param p10T32
     */
    public void setP10T32(java.lang.Double p10T32) {
        this.p10T32 = p10T32;
    }


    /**
     * Gets the p11T32 value for this Policy.
     * 
     * @return p11T32
     */
    public java.util.Calendar getP11T32() {
        return p11T32;
    }


    /**
     * Sets the p11T32 value for this Policy.
     * 
     * @param p11T32
     */
    public void setP11T32(java.util.Calendar p11T32) {
        this.p11T32 = p11T32;
    }


    /**
     * Gets the p12T32 value for this Policy.
     * 
     * @return p12T32
     */
    public java.lang.Integer getP12T32() {
        return p12T32;
    }


    /**
     * Sets the p12T32 value for this Policy.
     * 
     * @param p12T32
     */
    public void setP12T32(java.lang.Integer p12T32) {
        this.p12T32 = p12T32;
    }


    /**
     * Gets the p13T32 value for this Policy.
     * 
     * @return p13T32
     */
    public java.lang.String getP13T32() {
        return p13T32;
    }


    /**
     * Sets the p13T32 value for this Policy.
     * 
     * @param p13T32
     */
    public void setP13T32(java.lang.String p13T32) {
        this.p13T32 = p13T32;
    }


    /**
     * Gets the p16T32 value for this Policy.
     * 
     * @return p16T32
     */
    public java.lang.Short getP16T32() {
        return p16T32;
    }


    /**
     * Sets the p16T32 value for this Policy.
     * 
     * @param p16T32
     */
    public void setP16T32(java.lang.Short p16T32) {
        this.p16T32 = p16T32;
    }


    /**
     * Gets the p17T32 value for this Policy.
     * 
     * @return p17T32
     */
    public java.util.Calendar getP17T32() {
        return p17T32;
    }


    /**
     * Sets the p17T32 value for this Policy.
     * 
     * @param p17T32
     */
    public void setP17T32(java.util.Calendar p17T32) {
        this.p17T32 = p17T32;
    }


    /**
     * Gets the p2T32 value for this Policy.
     * 
     * @return p2T32
     */
    public java.lang.String getP2T32() {
        return p2T32;
    }


    /**
     * Sets the p2T32 value for this Policy.
     * 
     * @param p2T32
     */
    public void setP2T32(java.lang.String p2T32) {
        this.p2T32 = p2T32;
    }


    /**
     * Gets the p3T32 value for this Policy.
     * 
     * @return p3T32
     */
    public java.lang.String getP3T32() {
        return p3T32;
    }


    /**
     * Sets the p3T32 value for this Policy.
     * 
     * @param p3T32
     */
    public void setP3T32(java.lang.String p3T32) {
        this.p3T32 = p3T32;
    }


    /**
     * Gets the p4T32 value for this Policy.
     * 
     * @return p4T32
     */
    public java.lang.String getP4T32() {
        return p4T32;
    }


    /**
     * Sets the p4T32 value for this Policy.
     * 
     * @param p4T32
     */
    public void setP4T32(java.lang.String p4T32) {
        this.p4T32 = p4T32;
    }


    /**
     * Gets the p5T32 value for this Policy.
     * 
     * @return p5T32
     */
    public java.util.Calendar getP5T32() {
        return p5T32;
    }


    /**
     * Sets the p5T32 value for this Policy.
     * 
     * @param p5T32
     */
    public void setP5T32(java.util.Calendar p5T32) {
        this.p5T32 = p5T32;
    }


    /**
     * Gets the p6T32 value for this Policy.
     * 
     * @return p6T32
     */
    public java.lang.String getP6T32() {
        return p6T32;
    }


    /**
     * Sets the p6T32 value for this Policy.
     * 
     * @param p6T32
     */
    public void setP6T32(java.lang.String p6T32) {
        this.p6T32 = p6T32;
    }


    /**
     * Gets the p7T32 value for this Policy.
     * 
     * @return p7T32
     */
    public java.lang.Double getP7T32() {
        return p7T32;
    }


    /**
     * Sets the p7T32 value for this Policy.
     * 
     * @param p7T32
     */
    public void setP7T32(java.lang.Double p7T32) {
        this.p7T32 = p7T32;
    }


    /**
     * Gets the p8T32 value for this Policy.
     * 
     * @return p8T32
     */
    public java.lang.Double getP8T32() {
        return p8T32;
    }


    /**
     * Sets the p8T32 value for this Policy.
     * 
     * @param p8T32
     */
    public void setP8T32(java.lang.Double p8T32) {
        this.p8T32 = p8T32;
    }


    /**
     * Gets the p9T32 value for this Policy.
     * 
     * @return p9T32
     */
    public java.lang.Double getP9T32() {
        return p9T32;
    }


    /**
     * Sets the p9T32 value for this Policy.
     * 
     * @param p9T32
     */
    public void setP9T32(java.lang.Double p9T32) {
        this.p9T32 = p9T32;
    }


    /**
     * Gets the policySumChanges value for this Policy.
     * 
     * @return policySumChanges
     */
    public com.sbs.service.PolicySumChange[] getPolicySumChanges() {
        return policySumChanges;
    }


    /**
     * Sets the policySumChanges value for this Policy.
     * 
     * @param policySumChanges
     */
    public void setPolicySumChanges(com.sbs.service.PolicySumChange[] policySumChanges) {
        this.policySumChanges = policySumChanges;
    }

    public com.sbs.service.PolicySumChange getPolicySumChanges(int i) {
        return this.policySumChanges[i];
    }

    public void setPolicySumChanges(int i, com.sbs.service.PolicySumChange _value) {
        this.policySumChanges[i] = _value;
    }


    /**
     * Gets the policyTimeChanges value for this Policy.
     * 
     * @return policyTimeChanges
     */
    public com.sbs.service.PolicyTimeChange[] getPolicyTimeChanges() {
        return policyTimeChanges;
    }


    /**
     * Sets the policyTimeChanges value for this Policy.
     * 
     * @param policyTimeChanges
     */
    public void setPolicyTimeChanges(com.sbs.service.PolicyTimeChange[] policyTimeChanges) {
        this.policyTimeChanges = policyTimeChanges;
    }

    public com.sbs.service.PolicyTimeChange getPolicyTimeChanges(int i) {
        return this.policyTimeChanges[i];
    }

    public void setPolicyTimeChanges(int i, com.sbs.service.PolicyTimeChange _value) {
        this.policyTimeChanges[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Policy)) return false;
        Policy other = (Policy) obj;
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
            ((this.p0T32==null && other.getP0T32()==null) || 
             (this.p0T32!=null &&
              this.p0T32.equals(other.getP0T32()))) &&
            ((this.p100T32==null && other.getP100T32()==null) || 
             (this.p100T32!=null &&
              this.p100T32.equals(other.getP100T32()))) &&
            ((this.p101T32==null && other.getP101T32()==null) || 
             (this.p101T32!=null &&
              java.util.Arrays.equals(this.p101T32, other.getP101T32()))) &&
            ((this.p10T32==null && other.getP10T32()==null) || 
             (this.p10T32!=null &&
              this.p10T32.equals(other.getP10T32()))) &&
            ((this.p11T32==null && other.getP11T32()==null) || 
             (this.p11T32!=null &&
              this.p11T32.equals(other.getP11T32()))) &&
            ((this.p12T32==null && other.getP12T32()==null) || 
             (this.p12T32!=null &&
              this.p12T32.equals(other.getP12T32()))) &&
            ((this.p13T32==null && other.getP13T32()==null) || 
             (this.p13T32!=null &&
              this.p13T32.equals(other.getP13T32()))) &&
            ((this.p16T32==null && other.getP16T32()==null) || 
             (this.p16T32!=null &&
              this.p16T32.equals(other.getP16T32()))) &&
            ((this.p17T32==null && other.getP17T32()==null) || 
             (this.p17T32!=null &&
              this.p17T32.equals(other.getP17T32()))) &&
            ((this.p2T32==null && other.getP2T32()==null) || 
             (this.p2T32!=null &&
              this.p2T32.equals(other.getP2T32()))) &&
            ((this.p3T32==null && other.getP3T32()==null) || 
             (this.p3T32!=null &&
              this.p3T32.equals(other.getP3T32()))) &&
            ((this.p4T32==null && other.getP4T32()==null) || 
             (this.p4T32!=null &&
              this.p4T32.equals(other.getP4T32()))) &&
            ((this.p5T32==null && other.getP5T32()==null) || 
             (this.p5T32!=null &&
              this.p5T32.equals(other.getP5T32()))) &&
            ((this.p6T32==null && other.getP6T32()==null) || 
             (this.p6T32!=null &&
              this.p6T32.equals(other.getP6T32()))) &&
            ((this.p7T32==null && other.getP7T32()==null) || 
             (this.p7T32!=null &&
              this.p7T32.equals(other.getP7T32()))) &&
            ((this.p8T32==null && other.getP8T32()==null) || 
             (this.p8T32!=null &&
              this.p8T32.equals(other.getP8T32()))) &&
            ((this.p9T32==null && other.getP9T32()==null) || 
             (this.p9T32!=null &&
              this.p9T32.equals(other.getP9T32()))) &&
            ((this.policySumChanges==null && other.getPolicySumChanges()==null) || 
             (this.policySumChanges!=null &&
              java.util.Arrays.equals(this.policySumChanges, other.getPolicySumChanges()))) &&
            ((this.policyTimeChanges==null && other.getPolicyTimeChanges()==null) || 
             (this.policyTimeChanges!=null &&
              java.util.Arrays.equals(this.policyTimeChanges, other.getPolicyTimeChanges())));
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
        if (getP0T32() != null) {
            _hashCode += getP0T32().hashCode();
        }
        if (getP100T32() != null) {
            _hashCode += getP100T32().hashCode();
        }
        if (getP101T32() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getP101T32());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getP101T32(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getP10T32() != null) {
            _hashCode += getP10T32().hashCode();
        }
        if (getP11T32() != null) {
            _hashCode += getP11T32().hashCode();
        }
        if (getP12T32() != null) {
            _hashCode += getP12T32().hashCode();
        }
        if (getP13T32() != null) {
            _hashCode += getP13T32().hashCode();
        }
        if (getP16T32() != null) {
            _hashCode += getP16T32().hashCode();
        }
        if (getP17T32() != null) {
            _hashCode += getP17T32().hashCode();
        }
        if (getP2T32() != null) {
            _hashCode += getP2T32().hashCode();
        }
        if (getP3T32() != null) {
            _hashCode += getP3T32().hashCode();
        }
        if (getP4T32() != null) {
            _hashCode += getP4T32().hashCode();
        }
        if (getP5T32() != null) {
            _hashCode += getP5T32().hashCode();
        }
        if (getP6T32() != null) {
            _hashCode += getP6T32().hashCode();
        }
        if (getP7T32() != null) {
            _hashCode += getP7T32().hashCode();
        }
        if (getP8T32() != null) {
            _hashCode += getP8T32().hashCode();
        }
        if (getP9T32() != null) {
            _hashCode += getP9T32().hashCode();
        }
        if (getPolicySumChanges() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPolicySumChanges());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPolicySumChanges(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getPolicyTimeChanges() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPolicyTimeChanges());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPolicyTimeChanges(), i);
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
        new org.apache.axis.description.TypeDesc(Policy.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "policy"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idCol");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idCol"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p0T32");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p0T32"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p100T32");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p100T32"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p101T32");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p101T32"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "signer"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p10T32");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p10T32"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p11T32");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p11T32"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p12T32");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p12T32"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p13T32");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p13T32"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p16T32");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p16T32"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p17T32");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p17T32"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p2T32");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p2T32"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p3T32");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p3T32"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p4T32");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p4T32"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p5T32");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p5T32"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p6T32");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p6T32"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p7T32");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p7T32"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p8T32");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p8T32"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p9T32");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p9T32"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("policySumChanges");
        elemField.setXmlName(new javax.xml.namespace.QName("", "policySumChanges"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "policySumChange"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("policyTimeChanges");
        elemField.setXmlName(new javax.xml.namespace.QName("", "policyTimeChanges"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "policyTimeChange"));
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
