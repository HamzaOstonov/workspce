/**
 * BusinessPartnerResponce.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package client.NCI.com.ipakyulibank.cj;

public class BusinessPartnerResponce  implements java.io.Serializable {
    private client.NCI.com.ipakyulibank.cj.BusinessPartnerResponceHeader header;

    private client.NCI.com.ipakyulibank.cj.BAPIRET2[] messages;

    public BusinessPartnerResponce() {
    }

    public BusinessPartnerResponce(
           client.NCI.com.ipakyulibank.cj.BusinessPartnerResponceHeader header,
           client.NCI.com.ipakyulibank.cj.BAPIRET2[] messages) {
           this.header = header;
           this.messages = messages;
    }


    /**
     * Gets the header value for this BusinessPartnerResponce.
     * 
     * @return header
     */
    public client.NCI.com.ipakyulibank.cj.BusinessPartnerResponceHeader getHeader() {
        return header;
    }


    /**
     * Sets the header value for this BusinessPartnerResponce.
     * 
     * @param header
     */
    public void setHeader(client.NCI.com.ipakyulibank.cj.BusinessPartnerResponceHeader header) {
        this.header = header;
    }


    /**
     * Gets the messages value for this BusinessPartnerResponce.
     * 
     * @return messages
     */
    public client.NCI.com.ipakyulibank.cj.BAPIRET2[] getMessages() {
        return messages;
    }


    /**
     * Sets the messages value for this BusinessPartnerResponce.
     * 
     * @param messages
     */
    public void setMessages(client.NCI.com.ipakyulibank.cj.BAPIRET2[] messages) {
        this.messages = messages;
    }

    public client.NCI.com.ipakyulibank.cj.BAPIRET2 getMessages(int i) {
        return this.messages[i];
    }

    public void setMessages(int i, client.NCI.com.ipakyulibank.cj.BAPIRET2 _value) {
        this.messages[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BusinessPartnerResponce)) return false;
        BusinessPartnerResponce other = (BusinessPartnerResponce) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.header==null && other.getHeader()==null) || 
             (this.header!=null &&
              this.header.equals(other.getHeader()))) &&
            ((this.messages==null && other.getMessages()==null) || 
             (this.messages!=null &&
              java.util.Arrays.equals(this.messages, other.getMessages())));
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
        if (getHeader() != null) {
            _hashCode += getHeader().hashCode();
        }
        if (getMessages() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getMessages());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getMessages(), i);
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
        new org.apache.axis.description.TypeDesc(BusinessPartnerResponce.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:client", "BusinessPartnerResponce"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("header");
        elemField.setXmlName(new javax.xml.namespace.QName("", "header"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:client", "BusinessPartnerResponceHeader"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("messages");
        elemField.setXmlName(new javax.xml.namespace.QName("", "messages"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:client", "BAPIRET2"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
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
