/**
 * RowType_CustomerStatusChange_Request.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package visa.issuing_v_01_02_xsd;

public class RowType_CustomerStatusChange_Request  implements java.io.Serializable {
    private java.lang.String CLIENT;

    private java.lang.String STATUS;

    private java.lang.String COMENT;

    private java.lang.String BANK_C;

    private java.lang.String GROUPC;

    public RowType_CustomerStatusChange_Request() {
    }

    public RowType_CustomerStatusChange_Request(
           java.lang.String CLIENT,
           java.lang.String STATUS,
           java.lang.String COMENT,
           java.lang.String BANK_C,
           java.lang.String GROUPC) {
           this.CLIENT = CLIENT;
           this.STATUS = STATUS;
           this.COMENT = COMENT;
           this.BANK_C = BANK_C;
           this.GROUPC = GROUPC;
    }


    /**
     * Gets the CLIENT value for this RowType_CustomerStatusChange_Request.
     * 
     * @return CLIENT
     */
    public java.lang.String getCLIENT() {
        return CLIENT;
    }


    /**
     * Sets the CLIENT value for this RowType_CustomerStatusChange_Request.
     * 
     * @param CLIENT
     */
    public void setCLIENT(java.lang.String CLIENT) {
        this.CLIENT = CLIENT;
    }


    /**
     * Gets the STATUS value for this RowType_CustomerStatusChange_Request.
     * 
     * @return STATUS
     */
    public java.lang.String getSTATUS() {
        return STATUS;
    }


    /**
     * Sets the STATUS value for this RowType_CustomerStatusChange_Request.
     * 
     * @param STATUS
     */
    public void setSTATUS(java.lang.String STATUS) {
        this.STATUS = STATUS;
    }


    /**
     * Gets the COMENT value for this RowType_CustomerStatusChange_Request.
     * 
     * @return COMENT
     */
    public java.lang.String getCOMENT() {
        return COMENT;
    }


    /**
     * Sets the COMENT value for this RowType_CustomerStatusChange_Request.
     * 
     * @param COMENT
     */
    public void setCOMENT(java.lang.String COMENT) {
        this.COMENT = COMENT;
    }


    /**
     * Gets the BANK_C value for this RowType_CustomerStatusChange_Request.
     * 
     * @return BANK_C
     */
    public java.lang.String getBANK_C() {
        return BANK_C;
    }


    /**
     * Sets the BANK_C value for this RowType_CustomerStatusChange_Request.
     * 
     * @param BANK_C
     */
    public void setBANK_C(java.lang.String BANK_C) {
        this.BANK_C = BANK_C;
    }


    /**
     * Gets the GROUPC value for this RowType_CustomerStatusChange_Request.
     * 
     * @return GROUPC
     */
    public java.lang.String getGROUPC() {
        return GROUPC;
    }


    /**
     * Sets the GROUPC value for this RowType_CustomerStatusChange_Request.
     * 
     * @param GROUPC
     */
    public void setGROUPC(java.lang.String GROUPC) {
        this.GROUPC = GROUPC;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RowType_CustomerStatusChange_Request)) return false;
        RowType_CustomerStatusChange_Request other = (RowType_CustomerStatusChange_Request) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.CLIENT==null && other.getCLIENT()==null) || 
             (this.CLIENT!=null &&
              this.CLIENT.equals(other.getCLIENT()))) &&
            ((this.STATUS==null && other.getSTATUS()==null) || 
             (this.STATUS!=null &&
              this.STATUS.equals(other.getSTATUS()))) &&
            ((this.COMENT==null && other.getCOMENT()==null) || 
             (this.COMENT!=null &&
              this.COMENT.equals(other.getCOMENT()))) &&
            ((this.BANK_C==null && other.getBANK_C()==null) || 
             (this.BANK_C!=null &&
              this.BANK_C.equals(other.getBANK_C()))) &&
            ((this.GROUPC==null && other.getGROUPC()==null) || 
             (this.GROUPC!=null &&
              this.GROUPC.equals(other.getGROUPC())));
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
        if (getCLIENT() != null) {
            _hashCode += getCLIENT().hashCode();
        }
        if (getSTATUS() != null) {
            _hashCode += getSTATUS().hashCode();
        }
        if (getCOMENT() != null) {
            _hashCode += getCOMENT().hashCode();
        }
        if (getBANK_C() != null) {
            _hashCode += getBANK_C().hashCode();
        }
        if (getGROUPC() != null) {
            _hashCode += getGROUPC().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RowType_CustomerStatusChange_Request.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_CustomerStatusChange_Request"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CLIENT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CLIENT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("STATUS");
        elemField.setXmlName(new javax.xml.namespace.QName("", "STATUS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("COMENT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "COMENT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("BANK_C");
        elemField.setXmlName(new javax.xml.namespace.QName("", "BANK_C"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("GROUPC");
        elemField.setXmlName(new javax.xml.namespace.QName("", "GROUPC"));
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
