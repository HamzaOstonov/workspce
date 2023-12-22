/**
 * ContentServerResponce.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package content_server.NCI.com.ipakyulibank;

public class ContentServerResponce  implements java.io.Serializable {
    private byte[] data;

    private java.lang.String doc_id;

    private java.lang.String doc_name;

    private java.lang.String doc_type;

    private java.lang.String err_status;

    public ContentServerResponce() {
    }

    public ContentServerResponce(
           byte[] data,
           java.lang.String doc_id,
           java.lang.String doc_name,
           java.lang.String doc_type,
           java.lang.String err_status) {
           this.data = data;
           this.doc_id = doc_id;
           this.doc_name = doc_name;
           this.doc_type = doc_type;
           this.err_status = err_status;
    }


    /**
     * Gets the data value for this ContentServerResponce.
     * 
     * @return data
     */
    public byte[] getData() {
        return data;
    }


    /**
     * Sets the data value for this ContentServerResponce.
     * 
     * @param data
     */
    public void setData(byte[] data) {
        this.data = data;
    }


    /**
     * Gets the doc_id value for this ContentServerResponce.
     * 
     * @return doc_id
     */
    public java.lang.String getDoc_id() {
        return doc_id;
    }


    /**
     * Sets the doc_id value for this ContentServerResponce.
     * 
     * @param doc_id
     */
    public void setDoc_id(java.lang.String doc_id) {
        this.doc_id = doc_id;
    }


    /**
     * Gets the doc_name value for this ContentServerResponce.
     * 
     * @return doc_name
     */
    public java.lang.String getDoc_name() {
        return doc_name;
    }


    /**
     * Sets the doc_name value for this ContentServerResponce.
     * 
     * @param doc_name
     */
    public void setDoc_name(java.lang.String doc_name) {
        this.doc_name = doc_name;
    }


    /**
     * Gets the doc_type value for this ContentServerResponce.
     * 
     * @return doc_type
     */
    public java.lang.String getDoc_type() {
        return doc_type;
    }


    /**
     * Sets the doc_type value for this ContentServerResponce.
     * 
     * @param doc_type
     */
    public void setDoc_type(java.lang.String doc_type) {
        this.doc_type = doc_type;
    }


    /**
     * Gets the err_status value for this ContentServerResponce.
     * 
     * @return err_status
     */
    public java.lang.String getErr_status() {
        return err_status;
    }


    /**
     * Sets the err_status value for this ContentServerResponce.
     * 
     * @param err_status
     */
    public void setErr_status(java.lang.String err_status) {
        this.err_status = err_status;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ContentServerResponce)) return false;
        ContentServerResponce other = (ContentServerResponce) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.data==null && other.getData()==null) || 
             (this.data!=null &&
              java.util.Arrays.equals(this.data, other.getData()))) &&
            ((this.doc_id==null && other.getDoc_id()==null) || 
             (this.doc_id!=null &&
              this.doc_id.equals(other.getDoc_id()))) &&
            ((this.doc_name==null && other.getDoc_name()==null) || 
             (this.doc_name!=null &&
              this.doc_name.equals(other.getDoc_name()))) &&
            ((this.doc_type==null && other.getDoc_type()==null) || 
             (this.doc_type!=null &&
              this.doc_type.equals(other.getDoc_type()))) &&
            ((this.err_status==null && other.getErr_status()==null) || 
             (this.err_status!=null &&
              this.err_status.equals(other.getErr_status())));
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
        if (getData() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getData());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getData(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getDoc_id() != null) {
            _hashCode += getDoc_id().hashCode();
        }
        if (getDoc_name() != null) {
            _hashCode += getDoc_name().hashCode();
        }
        if (getDoc_type() != null) {
            _hashCode += getDoc_type().hashCode();
        }
        if (getErr_status() != null) {
            _hashCode += getErr_status().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ContentServerResponce.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:content_server", "ContentServerResponce"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("data");
        elemField.setXmlName(new javax.xml.namespace.QName("", "data"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("doc_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "doc_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("doc_name");
        elemField.setXmlName(new javax.xml.namespace.QName("", "doc_name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("doc_type");
        elemField.setXmlName(new javax.xml.namespace.QName("", "doc_type"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("err_status");
        elemField.setXmlName(new javax.xml.namespace.QName("", "err_status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
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
