/**
 * RowType_ListCustomerCards_Request.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package issuing_v_01_02_xsd;

public class RowType_ListCustomerCards_Request  implements java.io.Serializable {
    private java.lang.String BANK_C;

    private java.lang.String CLIENT;

    private java.lang.String CLIENT_B;

    public RowType_ListCustomerCards_Request() {
    }

    public RowType_ListCustomerCards_Request(
           java.lang.String BANK_C,
           java.lang.String CLIENT,
           java.lang.String CLIENT_B) {
           this.BANK_C = BANK_C;
           this.CLIENT = CLIENT;
           this.CLIENT_B = CLIENT_B;
    }


    /**
     * Gets the BANK_C value for this RowType_ListCustomerCards_Request.
     * 
     * @return BANK_C
     */
    public java.lang.String getBANK_C() {
        return BANK_C;
    }


    /**
     * Sets the BANK_C value for this RowType_ListCustomerCards_Request.
     * 
     * @param BANK_C
     */
    public void setBANK_C(java.lang.String BANK_C) {
        this.BANK_C = BANK_C;
    }


    /**
     * Gets the CLIENT value for this RowType_ListCustomerCards_Request.
     * 
     * @return CLIENT
     */
    public java.lang.String getCLIENT() {
        return CLIENT;
    }


    /**
     * Sets the CLIENT value for this RowType_ListCustomerCards_Request.
     * 
     * @param CLIENT
     */
    public void setCLIENT(java.lang.String CLIENT) {
        this.CLIENT = CLIENT;
    }


    /**
     * Gets the CLIENT_B value for this RowType_ListCustomerCards_Request.
     * 
     * @return CLIENT_B
     */
    public java.lang.String getCLIENT_B() {
        return CLIENT_B;
    }


    /**
     * Sets the CLIENT_B value for this RowType_ListCustomerCards_Request.
     * 
     * @param CLIENT_B
     */
    public void setCLIENT_B(java.lang.String CLIENT_B) {
        this.CLIENT_B = CLIENT_B;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RowType_ListCustomerCards_Request)) return false;
        RowType_ListCustomerCards_Request other = (RowType_ListCustomerCards_Request) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.BANK_C==null && other.getBANK_C()==null) || 
             (this.BANK_C!=null &&
              this.BANK_C.equals(other.getBANK_C()))) &&
            ((this.CLIENT==null && other.getCLIENT()==null) || 
             (this.CLIENT!=null &&
              this.CLIENT.equals(other.getCLIENT()))) &&
            ((this.CLIENT_B==null && other.getCLIENT_B()==null) || 
             (this.CLIENT_B!=null &&
              this.CLIENT_B.equals(other.getCLIENT_B())));
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
        if (getBANK_C() != null) {
            _hashCode += getBANK_C().hashCode();
        }
        if (getCLIENT() != null) {
            _hashCode += getCLIENT().hashCode();
        }
        if (getCLIENT_B() != null) {
            _hashCode += getCLIENT_B().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RowType_ListCustomerCards_Request.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_ListCustomerCards_Request"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("BANK_C");
        elemField.setXmlName(new javax.xml.namespace.QName("", "BANK_C"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CLIENT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CLIENT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CLIENT_B");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CLIENT_B"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
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
