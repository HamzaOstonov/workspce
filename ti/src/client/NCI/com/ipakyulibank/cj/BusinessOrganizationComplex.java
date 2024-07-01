/**
 * BusinessOrganizationComplex.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package client.NCI.com.ipakyulibank.cj;

public class BusinessOrganizationComplex  implements java.io.Serializable {
    private client.NCI.com.ipakyulibank.cj.BusinessPartnerContent general;

    private client.NCI.com.ipakyulibank.cj.BusinessOrganizationContent organization;

    private client.NCI.com.ipakyulibank.cj.BPAttachmentsAttachment[] attachments;

    private client.NCI.com.ipakyulibank.cj.BusinessPartnerNCI[] bp_list;

    public BusinessOrganizationComplex() {
    }

    public BusinessOrganizationComplex(
           client.NCI.com.ipakyulibank.cj.BusinessPartnerContent general,
           client.NCI.com.ipakyulibank.cj.BusinessOrganizationContent organization,
           client.NCI.com.ipakyulibank.cj.BPAttachmentsAttachment[] attachments,
           client.NCI.com.ipakyulibank.cj.BusinessPartnerNCI[] bp_list) {
           this.general = general;
           this.organization = organization;
           this.attachments = attachments;
           this.bp_list = bp_list;
    }


    /**
     * Gets the general value for this BusinessOrganizationComplex.
     * 
     * @return general
     */
    public client.NCI.com.ipakyulibank.cj.BusinessPartnerContent getGeneral() {
        return general;
    }


    /**
     * Sets the general value for this BusinessOrganizationComplex.
     * 
     * @param general
     */
    public void setGeneral(client.NCI.com.ipakyulibank.cj.BusinessPartnerContent general) {
        this.general = general;
    }


    /**
     * Gets the organization value for this BusinessOrganizationComplex.
     * 
     * @return organization
     */
    public client.NCI.com.ipakyulibank.cj.BusinessOrganizationContent getOrganization() {
        return organization;
    }


    /**
     * Sets the organization value for this BusinessOrganizationComplex.
     * 
     * @param organization
     */
    public void setOrganization(client.NCI.com.ipakyulibank.cj.BusinessOrganizationContent organization) {
        this.organization = organization;
    }


    /**
     * Gets the attachments value for this BusinessOrganizationComplex.
     * 
     * @return attachments
     */
    public client.NCI.com.ipakyulibank.cj.BPAttachmentsAttachment[] getAttachments() {
        return attachments;
    }


    /**
     * Sets the attachments value for this BusinessOrganizationComplex.
     * 
     * @param attachments
     */
    public void setAttachments(client.NCI.com.ipakyulibank.cj.BPAttachmentsAttachment[] attachments) {
        this.attachments = attachments;
    }


    /**
     * Gets the bp_list value for this BusinessOrganizationComplex.
     * 
     * @return bp_list
     */
    public client.NCI.com.ipakyulibank.cj.BusinessPartnerNCI[] getBp_list() {
        return bp_list;
    }


    /**
     * Sets the bp_list value for this BusinessOrganizationComplex.
     * 
     * @param bp_list
     */
    public void setBp_list(client.NCI.com.ipakyulibank.cj.BusinessPartnerNCI[] bp_list) {
        this.bp_list = bp_list;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BusinessOrganizationComplex)) return false;
        BusinessOrganizationComplex other = (BusinessOrganizationComplex) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.general==null && other.getGeneral()==null) || 
             (this.general!=null &&
              this.general.equals(other.getGeneral()))) &&
            ((this.organization==null && other.getOrganization()==null) || 
             (this.organization!=null &&
              this.organization.equals(other.getOrganization()))) &&
            ((this.attachments==null && other.getAttachments()==null) || 
             (this.attachments!=null &&
              java.util.Arrays.equals(this.attachments, other.getAttachments()))) &&
            ((this.bp_list==null && other.getBp_list()==null) || 
             (this.bp_list!=null &&
              java.util.Arrays.equals(this.bp_list, other.getBp_list())));
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
        if (getGeneral() != null) {
            _hashCode += getGeneral().hashCode();
        }
        if (getOrganization() != null) {
            _hashCode += getOrganization().hashCode();
        }
        if (getAttachments() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAttachments());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAttachments(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getBp_list() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getBp_list());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getBp_list(), i);
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
        new org.apache.axis.description.TypeDesc(BusinessOrganizationComplex.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:client", "BusinessOrganizationComplex"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("general");
        elemField.setXmlName(new javax.xml.namespace.QName("", "general"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:client", "BusinessPartnerContent"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("organization");
        elemField.setXmlName(new javax.xml.namespace.QName("", "organization"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:client", "BusinessOrganizationContent"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("attachments");
        elemField.setXmlName(new javax.xml.namespace.QName("", "attachments"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:client", ">BPAttachments>attachment"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "attachment"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bp_list");
        elemField.setXmlName(new javax.xml.namespace.QName("", "bp_list"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:client", "BusinessPartnerNCI"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "BusinessPartner"));
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
