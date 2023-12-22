/**
 * ContentServerReqest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package content_server.NCI.com.ipakyulibank;

public class ContentServerReqest  implements java.io.Serializable {
    /* ID документа в SAP Content Server, который присваивается при
     * загрузке документа в сервер */
    private java.lang.String doc_id;

    public ContentServerReqest() {
    }

    public ContentServerReqest(
           java.lang.String doc_id) {
           this.doc_id = doc_id;
    }


    /**
     * Gets the doc_id value for this ContentServerReqest.
     * 
     * @return doc_id   * ID документа в SAP Content Server, который присваивается при
     * загрузке документа в сервер
     */
    public java.lang.String getDoc_id() {
        return doc_id;
    }


    /**
     * Sets the doc_id value for this ContentServerReqest.
     * 
     * @param doc_id   * ID документа в SAP Content Server, который присваивается при
     * загрузке документа в сервер
     */
    public void setDoc_id(java.lang.String doc_id) {
        this.doc_id = doc_id;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ContentServerReqest)) return false;
        ContentServerReqest other = (ContentServerReqest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.doc_id==null && other.getDoc_id()==null) || 
             (this.doc_id!=null &&
              this.doc_id.equals(other.getDoc_id())));
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
        if (getDoc_id() != null) {
            _hashCode += getDoc_id().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ContentServerReqest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:content_server", "ContentServerReqest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("doc_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "doc_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
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
