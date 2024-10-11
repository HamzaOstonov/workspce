/**
 * RowType_CardInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package agroBank.issuing_v_01_02_xsd;

public class RowType_CardInfo  implements java.io.Serializable {
    private agroBank.issuing_v_01_02_xsd.RowType_CardInfo_LogicalCard logicalCard;

    private agroBank.issuing_v_01_02_xsd.RowType_CardInfo_PhysicalCard physicalCard;

    private agroBank.issuing_v_01_02_xsd.RowType_CardInfo_EMV_Data EMV_Data;

    public RowType_CardInfo() {
    }

    public RowType_CardInfo(
           agroBank.issuing_v_01_02_xsd.RowType_CardInfo_LogicalCard logicalCard,
           agroBank.issuing_v_01_02_xsd.RowType_CardInfo_PhysicalCard physicalCard,
           agroBank.issuing_v_01_02_xsd.RowType_CardInfo_EMV_Data EMV_Data) {
           this.logicalCard = logicalCard;
           this.physicalCard = physicalCard;
           this.EMV_Data = EMV_Data;
    }


    /**
     * Gets the logicalCard value for this RowType_CardInfo.
     * 
     * @return logicalCard
     */
    public agroBank.issuing_v_01_02_xsd.RowType_CardInfo_LogicalCard getLogicalCard() {
        return logicalCard;
    }


    /**
     * Sets the logicalCard value for this RowType_CardInfo.
     * 
     * @param logicalCard
     */
    public void setLogicalCard(agroBank.issuing_v_01_02_xsd.RowType_CardInfo_LogicalCard logicalCard) {
        this.logicalCard = logicalCard;
    }


    /**
     * Gets the physicalCard value for this RowType_CardInfo.
     * 
     * @return physicalCard
     */
    public agroBank.issuing_v_01_02_xsd.RowType_CardInfo_PhysicalCard getPhysicalCard() {
        return physicalCard;
    }


    /**
     * Sets the physicalCard value for this RowType_CardInfo.
     * 
     * @param physicalCard
     */
    public void setPhysicalCard(agroBank.issuing_v_01_02_xsd.RowType_CardInfo_PhysicalCard physicalCard) {
        this.physicalCard = physicalCard;
    }


    /**
     * Gets the EMV_Data value for this RowType_CardInfo.
     * 
     * @return EMV_Data
     */
    public agroBank.issuing_v_01_02_xsd.RowType_CardInfo_EMV_Data getEMV_Data() {
        return EMV_Data;
    }


    /**
     * Sets the EMV_Data value for this RowType_CardInfo.
     * 
     * @param EMV_Data
     */
    public void setEMV_Data(agroBank.issuing_v_01_02_xsd.RowType_CardInfo_EMV_Data EMV_Data) {
        this.EMV_Data = EMV_Data;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RowType_CardInfo)) return false;
        RowType_CardInfo other = (RowType_CardInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.logicalCard==null && other.getLogicalCard()==null) || 
             (this.logicalCard!=null &&
              this.logicalCard.equals(other.getLogicalCard()))) &&
            ((this.physicalCard==null && other.getPhysicalCard()==null) || 
             (this.physicalCard!=null &&
              this.physicalCard.equals(other.getPhysicalCard()))) &&
            ((this.EMV_Data==null && other.getEMV_Data()==null) || 
             (this.EMV_Data!=null &&
              this.EMV_Data.equals(other.getEMV_Data())));
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
        if (getLogicalCard() != null) {
            _hashCode += getLogicalCard().hashCode();
        }
        if (getPhysicalCard() != null) {
            _hashCode += getPhysicalCard().hashCode();
        }
        if (getEMV_Data() != null) {
            _hashCode += getEMV_Data().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RowType_CardInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_CardInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("logicalCard");
        elemField.setXmlName(new javax.xml.namespace.QName("", "LogicalCard"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_CardInfo_LogicalCard"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("physicalCard");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PhysicalCard"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_CardInfo_PhysicalCard"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("EMV_Data");
        elemField.setXmlName(new javax.xml.namespace.QName("", "EMV_Data"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_CardInfo_EMV_Data"));
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
