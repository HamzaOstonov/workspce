/**
 * BusinessPartnerContentReqest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package client.NCI.com.ipakyulibank.client_p;

public class BusinessPartnerContentReqest  implements java.io.Serializable {
    private java.lang.String id;

    private java.lang.String branch;

    private java.lang.String id_client_sap;

    public BusinessPartnerContentReqest() {
    }

    public BusinessPartnerContentReqest(
           java.lang.String id,
           java.lang.String branch,
           java.lang.String id_client_sap) {
           this.id = id;
           this.branch = branch;
           this.id_client_sap = id_client_sap;
    }


    /**
     * Gets the id value for this BusinessPartnerContentReqest.
     * 
     * @return id
     */
    public java.lang.String getId() {
        return id;
    }


    /**
     * Sets the id value for this BusinessPartnerContentReqest.
     * 
     * @param id
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }


    /**
     * Gets the branch value for this BusinessPartnerContentReqest.
     * 
     * @return branch
     */
    public java.lang.String getBranch() {
        return branch;
    }


    /**
     * Sets the branch value for this BusinessPartnerContentReqest.
     * 
     * @param branch
     */
    public void setBranch(java.lang.String branch) {
        this.branch = branch;
    }


    /**
     * Gets the id_client_sap value for this BusinessPartnerContentReqest.
     * 
     * @return id_client_sap
     */
    public java.lang.String getId_client_sap() {
        return id_client_sap;
    }


    /**
     * Sets the id_client_sap value for this BusinessPartnerContentReqest.
     * 
     * @param id_client_sap
     */
    public void setId_client_sap(java.lang.String id_client_sap) {
        this.id_client_sap = id_client_sap;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BusinessPartnerContentReqest)) return false;
        BusinessPartnerContentReqest other = (BusinessPartnerContentReqest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.branch==null && other.getBranch()==null) || 
             (this.branch!=null &&
              this.branch.equals(other.getBranch()))) &&
            ((this.id_client_sap==null && other.getId_client_sap()==null) || 
             (this.id_client_sap!=null &&
              this.id_client_sap.equals(other.getId_client_sap())));
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
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getBranch() != null) {
            _hashCode += getBranch().hashCode();
        }
        if (getId_client_sap() != null) {
            _hashCode += getId_client_sap().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BusinessPartnerContentReqest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:client", "BusinessPartnerContentReqest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("branch");
        elemField.setXmlName(new javax.xml.namespace.QName("", "branch"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id_client_sap");
        elemField.setXmlName(new javax.xml.namespace.QName("", "id_client_sap"));
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
