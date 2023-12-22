/**
 * BusinessPartnerSearchResponce.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package client.NCI.com.ipakyulibank.client_p;

public class BusinessPartnerSearchResponce  implements java.io.Serializable {
    private client.NCI.com.ipakyulibank.client_p.BusinessPartnerResponceHeader[] partners;

    private java.math.BigInteger partners_count;

    public BusinessPartnerSearchResponce() {
    }

    public BusinessPartnerSearchResponce(
           client.NCI.com.ipakyulibank.client_p.BusinessPartnerResponceHeader[] partners,
           java.math.BigInteger partners_count) {
           this.partners = partners;
           this.partners_count = partners_count;
    }


    /**
     * Gets the partners value for this BusinessPartnerSearchResponce.
     * 
     * @return partners
     */
    public client.NCI.com.ipakyulibank.client_p.BusinessPartnerResponceHeader[] getPartners() {
        return partners;
    }


    /**
     * Sets the partners value for this BusinessPartnerSearchResponce.
     * 
     * @param partners
     */
    public void setPartners(client.NCI.com.ipakyulibank.client_p.BusinessPartnerResponceHeader[] partners) {
        this.partners = partners;
    }

    public client.NCI.com.ipakyulibank.client_p.BusinessPartnerResponceHeader getPartners(int i) {
        return this.partners[i];
    }

    public void setPartners(int i, client.NCI.com.ipakyulibank.client_p.BusinessPartnerResponceHeader _value) {
        this.partners[i] = _value;
    }


    /**
     * Gets the partners_count value for this BusinessPartnerSearchResponce.
     * 
     * @return partners_count
     */
    public java.math.BigInteger getPartners_count() {
        return partners_count;
    }


    /**
     * Sets the partners_count value for this BusinessPartnerSearchResponce.
     * 
     * @param partners_count
     */
    public void setPartners_count(java.math.BigInteger partners_count) {
        this.partners_count = partners_count;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BusinessPartnerSearchResponce)) return false;
        BusinessPartnerSearchResponce other = (BusinessPartnerSearchResponce) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.partners==null && other.getPartners()==null) || 
             (this.partners!=null &&
              java.util.Arrays.equals(this.partners, other.getPartners()))) &&
            ((this.partners_count==null && other.getPartners_count()==null) || 
             (this.partners_count!=null &&
              this.partners_count.equals(other.getPartners_count())));
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
        if (getPartners() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPartners());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPartners(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getPartners_count() != null) {
            _hashCode += getPartners_count().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BusinessPartnerSearchResponce.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:client", "BusinessPartnerSearchResponce"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("partners");
        elemField.setXmlName(new javax.xml.namespace.QName("", "partners"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:client", "BusinessPartnerResponceHeader"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("partners_count");
        elemField.setXmlName(new javax.xml.namespace.QName("", "partners_count"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
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
