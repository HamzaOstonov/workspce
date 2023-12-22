/**
 * BusinessOrganizationContentLicense_setLicense.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package client.NCI.com.ipakyulibank.cj;

public class BusinessOrganizationContentLicense_setLicense  implements java.io.Serializable {
    private java.lang.String license_type_id;

    private java.lang.String license_id;

    private java.util.Date license_valid_to;

    private java.util.Date license_issue_date;

    private java.lang.String license_issued_by;

    private java.lang.String license_type;

    private java.lang.String license_type_other;

    private java.lang.String license_issued_by_other;

    public BusinessOrganizationContentLicense_setLicense() {
    }

    public BusinessOrganizationContentLicense_setLicense(
           java.lang.String license_type_id,
           java.lang.String license_id,
           java.util.Date license_valid_to,
           java.util.Date license_issue_date,
           java.lang.String license_issued_by,
           java.lang.String license_type,
           java.lang.String license_type_other,
           java.lang.String license_issued_by_other) {
           this.license_type_id = license_type_id;
           this.license_id = license_id;
           this.license_valid_to = license_valid_to;
           this.license_issue_date = license_issue_date;
           this.license_issued_by = license_issued_by;
           this.license_type = license_type;
           this.license_type_other = license_type_other;
           this.license_issued_by_other = license_issued_by_other;
    }


    /**
     * Gets the license_type_id value for this BusinessOrganizationContentLicense_setLicense.
     * 
     */
    public java.lang.String getLicense_type_id() {
        return license_type_id;
    }


    /**
     * Sets the license_type_id value for this BusinessOrganizationContentLicense_setLicense.
     * 
     */
    public void setLicense_type_id(java.lang.String license_type_id) {
        this.license_type_id = license_type_id;
    }


    /**
     * Gets the license_id value for this BusinessOrganizationContentLicense_setLicense.
     * 
     */
    public java.lang.String getLicense_id() {
        return license_id;
    }


    /**
     * Sets the license_id value for this BusinessOrganizationContentLicense_setLicense.
     * 
     */
    public void setLicense_id(java.lang.String license_id) {
        this.license_id = license_id;
    }


    /**
     * Gets the license_valid_to value for this BusinessOrganizationContentLicense_setLicense.
     * 
     */
    public java.util.Date getLicense_valid_to() {
        return license_valid_to;
    }


    /**
     * Sets the license_valid_to value for this BusinessOrganizationContentLicense_setLicense.
     * 
     */
    public void setLicense_valid_to(java.util.Date license_valid_to) {
        this.license_valid_to = license_valid_to;
    }


    /**
     * Gets the license_issue_date value for this BusinessOrganizationContentLicense_setLicense.
     * 
     */
    public java.util.Date getLicense_issue_date() {
        return license_issue_date;
    }


    /**
     * Sets the license_issue_date value for this BusinessOrganizationContentLicense_setLicense.
     * 
     */
    public void setLicense_issue_date(java.util.Date license_issue_date) {
        this.license_issue_date = license_issue_date;
    }


    /**
     * Gets the license_issued_by value for this BusinessOrganizationContentLicense_setLicense.
     * 
     */
    public java.lang.String getLicense_issued_by() {
        return license_issued_by;
    }


    /**
     * Sets the license_issued_by value for this BusinessOrganizationContentLicense_setLicense.
     * 
     */
    public void setLicense_issued_by(java.lang.String license_issued_by) {
        this.license_issued_by = license_issued_by;
    }


    /**
     * Gets the license_type value for this BusinessOrganizationContentLicense_setLicense.
     * 
     */
    public java.lang.String getLicense_type() {
        return license_type;
    }


    /**
     * Sets the license_type value for this BusinessOrganizationContentLicense_setLicense.
     * 
     */
    public void setLicense_type(java.lang.String license_type) {
        this.license_type = license_type;
    }


    /**
     * Gets the license_type_other value for this BusinessOrganizationContentLicense_setLicense.
     * 
     */
    public java.lang.String getLicense_type_other() {
        return license_type_other;
    }


    /**
     * Sets the license_type_other value for this BusinessOrganizationContentLicense_setLicense.
     * 
     */
    public void setLicense_type_other(java.lang.String license_type_other) {
        this.license_type_other = license_type_other;
    }


    /**
     * Gets the license_issued_by_other value for this BusinessOrganizationContentLicense_setLicense.
     * 
     */
    public java.lang.String getLicense_issued_by_other() {
        return license_issued_by_other;
    }


    /**
     * Sets the license_issued_by_other value for this BusinessOrganizationContentLicense_setLicense.
     * 
     */
    public void setLicense_issued_by_other(java.lang.String license_issued_by_other) {
        this.license_issued_by_other = license_issued_by_other;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BusinessOrganizationContentLicense_setLicense)) return false;
        BusinessOrganizationContentLicense_setLicense other = (BusinessOrganizationContentLicense_setLicense) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.license_type_id==null && other.getLicense_type_id()==null) || 
             (this.license_type_id!=null &&
              this.license_type_id.equals(other.getLicense_type_id()))) &&
            ((this.license_id==null && other.getLicense_id()==null) || 
             (this.license_id!=null &&
              this.license_id.equals(other.getLicense_id()))) &&
            ((this.license_valid_to==null && other.getLicense_valid_to()==null) || 
             (this.license_valid_to!=null &&
              this.license_valid_to.equals(other.getLicense_valid_to()))) &&
            ((this.license_issue_date==null && other.getLicense_issue_date()==null) || 
             (this.license_issue_date!=null &&
              this.license_issue_date.equals(other.getLicense_issue_date()))) &&
            ((this.license_issued_by==null && other.getLicense_issued_by()==null) || 
             (this.license_issued_by!=null &&
              this.license_issued_by.equals(other.getLicense_issued_by()))) &&
            ((this.license_type==null && other.getLicense_type()==null) || 
             (this.license_type!=null &&
              this.license_type.equals(other.getLicense_type()))) &&
            ((this.license_type_other==null && other.getLicense_type_other()==null) || 
             (this.license_type_other!=null &&
              this.license_type_other.equals(other.getLicense_type_other()))) &&
            ((this.license_issued_by_other==null && other.getLicense_issued_by_other()==null) || 
             (this.license_issued_by_other!=null &&
              this.license_issued_by_other.equals(other.getLicense_issued_by_other())));
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
        if (getLicense_type_id() != null) {
            _hashCode += getLicense_type_id().hashCode();
        }
        if (getLicense_id() != null) {
            _hashCode += getLicense_id().hashCode();
        }
        if (getLicense_valid_to() != null) {
            _hashCode += getLicense_valid_to().hashCode();
        }
        if (getLicense_issue_date() != null) {
            _hashCode += getLicense_issue_date().hashCode();
        }
        if (getLicense_issued_by() != null) {
            _hashCode += getLicense_issued_by().hashCode();
        }
        if (getLicense_type() != null) {
            _hashCode += getLicense_type().hashCode();
        }
        if (getLicense_type_other() != null) {
            _hashCode += getLicense_type_other().hashCode();
        }
        if (getLicense_issued_by_other() != null) {
            _hashCode += getLicense_issued_by_other().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BusinessOrganizationContentLicense_setLicense.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:client", ">>BusinessOrganizationContent>license_set>license"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("license_type_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "license_type_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("license_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "license_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("license_valid_to");
        elemField.setXmlName(new javax.xml.namespace.QName("", "license_valid_to"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("license_issue_date");
        elemField.setXmlName(new javax.xml.namespace.QName("", "license_issue_date"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("license_issued_by");
        elemField.setXmlName(new javax.xml.namespace.QName("", "license_issued_by"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("license_type");
        elemField.setXmlName(new javax.xml.namespace.QName("", "license_type"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("license_type_other");
        elemField.setXmlName(new javax.xml.namespace.QName("", "license_type_other"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("license_issued_by_other");
        elemField.setXmlName(new javax.xml.namespace.QName("", "license_issued_by_other"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
