/**
 * RowType_CardInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package globus.issuing_v_01_02_xsd;

public class RowType_CardInfo  implements java.io.Serializable {
    private globus.issuing_v_01_02_xsd.RowType_CardInfo_LogicalCard logicalCard;

    private globus.issuing_v_01_02_xsd.RowType_CardInfo_PhysicalCard physicalCard;

    private globus.issuing_v_01_02_xsd.RowType_CardInfo_EMV_Data EMV_Data;

    private globus.issuing_v_01_02_xsd.ListType_AddServInfo addServInfo;

    private globus.issuing_v_01_02_xsd.RowType_CardInfo_TSM_Data TSM_Data;

    public RowType_CardInfo() {
    }

    public RowType_CardInfo(
           globus.issuing_v_01_02_xsd.RowType_CardInfo_LogicalCard logicalCard,
           globus.issuing_v_01_02_xsd.RowType_CardInfo_PhysicalCard physicalCard,
           globus.issuing_v_01_02_xsd.RowType_CardInfo_EMV_Data EMV_Data,
           globus.issuing_v_01_02_xsd.ListType_AddServInfo addServInfo,
           globus.issuing_v_01_02_xsd.RowType_CardInfo_TSM_Data TSM_Data) {
           this.logicalCard = logicalCard;
           this.physicalCard = physicalCard;
           this.EMV_Data = EMV_Data;
           this.addServInfo = addServInfo;
           this.TSM_Data = TSM_Data;
    }


    /**
     * Gets the logicalCard value for this RowType_CardInfo.
     * 
     * @return logicalCard
     */
    public globus.issuing_v_01_02_xsd.RowType_CardInfo_LogicalCard getLogicalCard() {
        return logicalCard;
    }


    /**
     * Sets the logicalCard value for this RowType_CardInfo.
     * 
     * @param logicalCard
     */
    public void setLogicalCard(globus.issuing_v_01_02_xsd.RowType_CardInfo_LogicalCard logicalCard) {
        this.logicalCard = logicalCard;
    }


    /**
     * Gets the physicalCard value for this RowType_CardInfo.
     * 
     * @return physicalCard
     */
    public globus.issuing_v_01_02_xsd.RowType_CardInfo_PhysicalCard getPhysicalCard() {
        return physicalCard;
    }


    /**
     * Sets the physicalCard value for this RowType_CardInfo.
     * 
     * @param physicalCard
     */
    public void setPhysicalCard(globus.issuing_v_01_02_xsd.RowType_CardInfo_PhysicalCard physicalCard) {
        this.physicalCard = physicalCard;
    }


    /**
     * Gets the EMV_Data value for this RowType_CardInfo.
     * 
     * @return EMV_Data
     */
    public globus.issuing_v_01_02_xsd.RowType_CardInfo_EMV_Data getEMV_Data() {
        return EMV_Data;
    }


    /**
     * Sets the EMV_Data value for this RowType_CardInfo.
     * 
     * @param EMV_Data
     */
    public void setEMV_Data(globus.issuing_v_01_02_xsd.RowType_CardInfo_EMV_Data EMV_Data) {
        this.EMV_Data = EMV_Data;
    }


    /**
     * Gets the addServInfo value for this RowType_CardInfo.
     * 
     * @return addServInfo
     */
    public globus.issuing_v_01_02_xsd.ListType_AddServInfo getAddServInfo() {
        return addServInfo;
    }


    /**
     * Sets the addServInfo value for this RowType_CardInfo.
     * 
     * @param addServInfo
     */
    public void setAddServInfo(globus.issuing_v_01_02_xsd.ListType_AddServInfo addServInfo) {
        this.addServInfo = addServInfo;
    }


    /**
     * Gets the TSM_Data value for this RowType_CardInfo.
     * 
     * @return TSM_Data
     */
    public globus.issuing_v_01_02_xsd.RowType_CardInfo_TSM_Data getTSM_Data() {
        return TSM_Data;
    }


    /**
     * Sets the TSM_Data value for this RowType_CardInfo.
     * 
     * @param TSM_Data
     */
    public void setTSM_Data(globus.issuing_v_01_02_xsd.RowType_CardInfo_TSM_Data TSM_Data) {
        this.TSM_Data = TSM_Data;
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
              this.EMV_Data.equals(other.getEMV_Data()))) &&
            ((this.addServInfo==null && other.getAddServInfo()==null) || 
             (this.addServInfo!=null &&
              this.addServInfo.equals(other.getAddServInfo()))) &&
            ((this.TSM_Data==null && other.getTSM_Data()==null) || 
             (this.TSM_Data!=null &&
              this.TSM_Data.equals(other.getTSM_Data())));
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
        if (getAddServInfo() != null) {
            _hashCode += getAddServInfo().hashCode();
        }
        if (getTSM_Data() != null) {
            _hashCode += getTSM_Data().hashCode();
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addServInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AddServInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "ListType_AddServInfo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TSM_Data");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TSM_Data"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_CardInfo_TSM_Data"));
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
