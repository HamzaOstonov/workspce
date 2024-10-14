/**
 * RowType_ChangeAdditionalService_Request.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package globus.issuing_v_01_02_xsd;

public class RowType_ChangeAdditionalService_Request  implements java.io.Serializable {
    private java.lang.String OPERATION;

    private java.lang.String CARD;

    private java.lang.String SERV_TYPE_ID;

    private java.lang.String SERV_NUMBER;

    public RowType_ChangeAdditionalService_Request() {
    }

    public RowType_ChangeAdditionalService_Request(
           java.lang.String OPERATION,
           java.lang.String CARD,
           java.lang.String SERV_TYPE_ID,
           java.lang.String SERV_NUMBER) {
           this.OPERATION = OPERATION;
           this.CARD = CARD;
           this.SERV_TYPE_ID = SERV_TYPE_ID;
           this.SERV_NUMBER = SERV_NUMBER;
    }


    /**
     * Gets the OPERATION value for this RowType_ChangeAdditionalService_Request.
     * 
     * @return OPERATION
     */
    public java.lang.String getOPERATION() {
        return OPERATION;
    }


    /**
     * Sets the OPERATION value for this RowType_ChangeAdditionalService_Request.
     * 
     * @param OPERATION
     */
    public void setOPERATION(java.lang.String OPERATION) {
        this.OPERATION = OPERATION;
    }


    /**
     * Gets the CARD value for this RowType_ChangeAdditionalService_Request.
     * 
     * @return CARD
     */
    public java.lang.String getCARD() {
        return CARD;
    }


    /**
     * Sets the CARD value for this RowType_ChangeAdditionalService_Request.
     * 
     * @param CARD
     */
    public void setCARD(java.lang.String CARD) {
        this.CARD = CARD;
    }


    /**
     * Gets the SERV_TYPE_ID value for this RowType_ChangeAdditionalService_Request.
     * 
     * @return SERV_TYPE_ID
     */
    public java.lang.String getSERV_TYPE_ID() {
        return SERV_TYPE_ID;
    }


    /**
     * Sets the SERV_TYPE_ID value for this RowType_ChangeAdditionalService_Request.
     * 
     * @param SERV_TYPE_ID
     */
    public void setSERV_TYPE_ID(java.lang.String SERV_TYPE_ID) {
        this.SERV_TYPE_ID = SERV_TYPE_ID;
    }


    /**
     * Gets the SERV_NUMBER value for this RowType_ChangeAdditionalService_Request.
     * 
     * @return SERV_NUMBER
     */
    public java.lang.String getSERV_NUMBER() {
        return SERV_NUMBER;
    }


    /**
     * Sets the SERV_NUMBER value for this RowType_ChangeAdditionalService_Request.
     * 
     * @param SERV_NUMBER
     */
    public void setSERV_NUMBER(java.lang.String SERV_NUMBER) {
        this.SERV_NUMBER = SERV_NUMBER;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RowType_ChangeAdditionalService_Request)) return false;
        RowType_ChangeAdditionalService_Request other = (RowType_ChangeAdditionalService_Request) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.OPERATION==null && other.getOPERATION()==null) || 
             (this.OPERATION!=null &&
              this.OPERATION.equals(other.getOPERATION()))) &&
            ((this.CARD==null && other.getCARD()==null) || 
             (this.CARD!=null &&
              this.CARD.equals(other.getCARD()))) &&
            ((this.SERV_TYPE_ID==null && other.getSERV_TYPE_ID()==null) || 
             (this.SERV_TYPE_ID!=null &&
              this.SERV_TYPE_ID.equals(other.getSERV_TYPE_ID()))) &&
            ((this.SERV_NUMBER==null && other.getSERV_NUMBER()==null) || 
             (this.SERV_NUMBER!=null &&
              this.SERV_NUMBER.equals(other.getSERV_NUMBER())));
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
        if (getOPERATION() != null) {
            _hashCode += getOPERATION().hashCode();
        }
        if (getCARD() != null) {
            _hashCode += getCARD().hashCode();
        }
        if (getSERV_TYPE_ID() != null) {
            _hashCode += getSERV_TYPE_ID().hashCode();
        }
        if (getSERV_NUMBER() != null) {
            _hashCode += getSERV_NUMBER().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RowType_ChangeAdditionalService_Request.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_ChangeAdditionalService_Request"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("OPERATION");
        elemField.setXmlName(new javax.xml.namespace.QName("", "OPERATION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CARD");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CARD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SERV_TYPE_ID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SERV_TYPE_ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SERV_NUMBER");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SERV_NUMBER"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
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
