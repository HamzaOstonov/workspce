/**
 * BusinessOrganizationContentLicense_set.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package client.NCI.com.ipakyulibank.cj;

public class BusinessOrganizationContentLicense_set  implements java.io.Serializable {
    private client.NCI.com.ipakyulibank.cj.BusinessOrganizationContentLicense_setLicense license;

    public BusinessOrganizationContentLicense_set() {
    }

    public BusinessOrganizationContentLicense_set(
           client.NCI.com.ipakyulibank.cj.BusinessOrganizationContentLicense_setLicense license) {
           this.license = license;
    }


    /**
     * Gets the license value for this BusinessOrganizationContentLicense_set.
     * 
     * @return license
     */
    public client.NCI.com.ipakyulibank.cj.BusinessOrganizationContentLicense_setLicense getLicense() {
        return license;
    }


    /**
     * Sets the license value for this BusinessOrganizationContentLicense_set.
     * 
     * @param license
     */
    public void setLicense(client.NCI.com.ipakyulibank.cj.BusinessOrganizationContentLicense_setLicense license) {
        this.license = license;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BusinessOrganizationContentLicense_set)) return false;
        BusinessOrganizationContentLicense_set other = (BusinessOrganizationContentLicense_set) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.license==null && other.getLicense()==null) || 
             (this.license!=null &&
              this.license.equals(other.getLicense())));
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
        if (getLicense() != null) {
            _hashCode += getLicense().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BusinessOrganizationContentLicense_set.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:client", ">BusinessOrganizationContent>license_set"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("license");
        elemField.setXmlName(new javax.xml.namespace.QName("", "license"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:client", ">>BusinessOrganizationContent>license_set>license"));
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
