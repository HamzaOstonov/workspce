/**
 * RowType_manageTSMWallet_Request.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package capitalBank.issuing_v_01_02_xsd;

public class RowType_manageTSMWallet_Request  implements java.io.Serializable {
    private java.lang.String OPERATION;

    private java.lang.String WALLET_ID;

    private java.lang.String CARD;

    private java.lang.String TSM_SERVICE_ID;

    public RowType_manageTSMWallet_Request() {
    }

    public RowType_manageTSMWallet_Request(
           java.lang.String OPERATION,
           java.lang.String WALLET_ID,
           java.lang.String CARD,
           java.lang.String TSM_SERVICE_ID) {
           this.OPERATION = OPERATION;
           this.WALLET_ID = WALLET_ID;
           this.CARD = CARD;
           this.TSM_SERVICE_ID = TSM_SERVICE_ID;
    }


    /**
     * Gets the OPERATION value for this RowType_manageTSMWallet_Request.
     * 
     * @return OPERATION
     */
    public java.lang.String getOPERATION() {
        return OPERATION;
    }


    /**
     * Sets the OPERATION value for this RowType_manageTSMWallet_Request.
     * 
     * @param OPERATION
     */
    public void setOPERATION(java.lang.String OPERATION) {
        this.OPERATION = OPERATION;
    }


    /**
     * Gets the WALLET_ID value for this RowType_manageTSMWallet_Request.
     * 
     * @return WALLET_ID
     */
    public java.lang.String getWALLET_ID() {
        return WALLET_ID;
    }


    /**
     * Sets the WALLET_ID value for this RowType_manageTSMWallet_Request.
     * 
     * @param WALLET_ID
     */
    public void setWALLET_ID(java.lang.String WALLET_ID) {
        this.WALLET_ID = WALLET_ID;
    }


    /**
     * Gets the CARD value for this RowType_manageTSMWallet_Request.
     * 
     * @return CARD
     */
    public java.lang.String getCARD() {
        return CARD;
    }


    /**
     * Sets the CARD value for this RowType_manageTSMWallet_Request.
     * 
     * @param CARD
     */
    public void setCARD(java.lang.String CARD) {
        this.CARD = CARD;
    }


    /**
     * Gets the TSM_SERVICE_ID value for this RowType_manageTSMWallet_Request.
     * 
     * @return TSM_SERVICE_ID
     */
    public java.lang.String getTSM_SERVICE_ID() {
        return TSM_SERVICE_ID;
    }


    /**
     * Sets the TSM_SERVICE_ID value for this RowType_manageTSMWallet_Request.
     * 
     * @param TSM_SERVICE_ID
     */
    public void setTSM_SERVICE_ID(java.lang.String TSM_SERVICE_ID) {
        this.TSM_SERVICE_ID = TSM_SERVICE_ID;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RowType_manageTSMWallet_Request)) return false;
        RowType_manageTSMWallet_Request other = (RowType_manageTSMWallet_Request) obj;
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
            ((this.WALLET_ID==null && other.getWALLET_ID()==null) || 
             (this.WALLET_ID!=null &&
              this.WALLET_ID.equals(other.getWALLET_ID()))) &&
            ((this.CARD==null && other.getCARD()==null) || 
             (this.CARD!=null &&
              this.CARD.equals(other.getCARD()))) &&
            ((this.TSM_SERVICE_ID==null && other.getTSM_SERVICE_ID()==null) || 
             (this.TSM_SERVICE_ID!=null &&
              this.TSM_SERVICE_ID.equals(other.getTSM_SERVICE_ID())));
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
        if (getWALLET_ID() != null) {
            _hashCode += getWALLET_ID().hashCode();
        }
        if (getCARD() != null) {
            _hashCode += getCARD().hashCode();
        }
        if (getTSM_SERVICE_ID() != null) {
            _hashCode += getTSM_SERVICE_ID().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RowType_manageTSMWallet_Request.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_manageTSMWallet_Request"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("OPERATION");
        elemField.setXmlName(new javax.xml.namespace.QName("", "OPERATION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("WALLET_ID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "WALLET_ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CARD");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CARD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TSM_SERVICE_ID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TSM_SERVICE_ID"));
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
