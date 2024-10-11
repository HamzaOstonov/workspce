/**
 * RowType_AgreementReLinkRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package agroBank.issuing_v_01_02_xsd;

public class RowType_AgreementReLinkRequest  implements java.io.Serializable {
    private java.lang.String BANK_C;

    private java.lang.String GROUPC;

    private java.lang.String TARGET_CLIENT;

    private java.lang.String TARGET_CLIENT_B;

    private java.lang.String AGRE_NOM;

    private java.lang.String CONTRACT;

    private java.lang.String MOMENT;

    public RowType_AgreementReLinkRequest() {
    }

    public RowType_AgreementReLinkRequest(
           java.lang.String BANK_C,
           java.lang.String GROUPC,
           java.lang.String TARGET_CLIENT,
           java.lang.String TARGET_CLIENT_B,
           java.lang.String AGRE_NOM,
           java.lang.String CONTRACT,
           java.lang.String MOMENT) {
           this.BANK_C = BANK_C;
           this.GROUPC = GROUPC;
           this.TARGET_CLIENT = TARGET_CLIENT;
           this.TARGET_CLIENT_B = TARGET_CLIENT_B;
           this.AGRE_NOM = AGRE_NOM;
           this.CONTRACT = CONTRACT;
           this.MOMENT = MOMENT;
    }


    /**
     * Gets the BANK_C value for this RowType_AgreementReLinkRequest.
     * 
     * @return BANK_C
     */
    public java.lang.String getBANK_C() {
        return BANK_C;
    }


    /**
     * Sets the BANK_C value for this RowType_AgreementReLinkRequest.
     * 
     * @param BANK_C
     */
    public void setBANK_C(java.lang.String BANK_C) {
        this.BANK_C = BANK_C;
    }


    /**
     * Gets the GROUPC value for this RowType_AgreementReLinkRequest.
     * 
     * @return GROUPC
     */
    public java.lang.String getGROUPC() {
        return GROUPC;
    }


    /**
     * Sets the GROUPC value for this RowType_AgreementReLinkRequest.
     * 
     * @param GROUPC
     */
    public void setGROUPC(java.lang.String GROUPC) {
        this.GROUPC = GROUPC;
    }


    /**
     * Gets the TARGET_CLIENT value for this RowType_AgreementReLinkRequest.
     * 
     * @return TARGET_CLIENT
     */
    public java.lang.String getTARGET_CLIENT() {
        return TARGET_CLIENT;
    }


    /**
     * Sets the TARGET_CLIENT value for this RowType_AgreementReLinkRequest.
     * 
     * @param TARGET_CLIENT
     */
    public void setTARGET_CLIENT(java.lang.String TARGET_CLIENT) {
        this.TARGET_CLIENT = TARGET_CLIENT;
    }


    /**
     * Gets the TARGET_CLIENT_B value for this RowType_AgreementReLinkRequest.
     * 
     * @return TARGET_CLIENT_B
     */
    public java.lang.String getTARGET_CLIENT_B() {
        return TARGET_CLIENT_B;
    }


    /**
     * Sets the TARGET_CLIENT_B value for this RowType_AgreementReLinkRequest.
     * 
     * @param TARGET_CLIENT_B
     */
    public void setTARGET_CLIENT_B(java.lang.String TARGET_CLIENT_B) {
        this.TARGET_CLIENT_B = TARGET_CLIENT_B;
    }


    /**
     * Gets the AGRE_NOM value for this RowType_AgreementReLinkRequest.
     * 
     * @return AGRE_NOM
     */
    public java.lang.String getAGRE_NOM() {
        return AGRE_NOM;
    }


    /**
     * Sets the AGRE_NOM value for this RowType_AgreementReLinkRequest.
     * 
     * @param AGRE_NOM
     */
    public void setAGRE_NOM(java.lang.String AGRE_NOM) {
        this.AGRE_NOM = AGRE_NOM;
    }


    /**
     * Gets the CONTRACT value for this RowType_AgreementReLinkRequest.
     * 
     * @return CONTRACT
     */
    public java.lang.String getCONTRACT() {
        return CONTRACT;
    }


    /**
     * Sets the CONTRACT value for this RowType_AgreementReLinkRequest.
     * 
     * @param CONTRACT
     */
    public void setCONTRACT(java.lang.String CONTRACT) {
        this.CONTRACT = CONTRACT;
    }


    /**
     * Gets the MOMENT value for this RowType_AgreementReLinkRequest.
     * 
     * @return MOMENT
     */
    public java.lang.String getMOMENT() {
        return MOMENT;
    }


    /**
     * Sets the MOMENT value for this RowType_AgreementReLinkRequest.
     * 
     * @param MOMENT
     */
    public void setMOMENT(java.lang.String MOMENT) {
        this.MOMENT = MOMENT;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RowType_AgreementReLinkRequest)) return false;
        RowType_AgreementReLinkRequest other = (RowType_AgreementReLinkRequest) obj;
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
            ((this.GROUPC==null && other.getGROUPC()==null) || 
             (this.GROUPC!=null &&
              this.GROUPC.equals(other.getGROUPC()))) &&
            ((this.TARGET_CLIENT==null && other.getTARGET_CLIENT()==null) || 
             (this.TARGET_CLIENT!=null &&
              this.TARGET_CLIENT.equals(other.getTARGET_CLIENT()))) &&
            ((this.TARGET_CLIENT_B==null && other.getTARGET_CLIENT_B()==null) || 
             (this.TARGET_CLIENT_B!=null &&
              this.TARGET_CLIENT_B.equals(other.getTARGET_CLIENT_B()))) &&
            ((this.AGRE_NOM==null && other.getAGRE_NOM()==null) || 
             (this.AGRE_NOM!=null &&
              this.AGRE_NOM.equals(other.getAGRE_NOM()))) &&
            ((this.CONTRACT==null && other.getCONTRACT()==null) || 
             (this.CONTRACT!=null &&
              this.CONTRACT.equals(other.getCONTRACT()))) &&
            ((this.MOMENT==null && other.getMOMENT()==null) || 
             (this.MOMENT!=null &&
              this.MOMENT.equals(other.getMOMENT())));
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
        if (getGROUPC() != null) {
            _hashCode += getGROUPC().hashCode();
        }
        if (getTARGET_CLIENT() != null) {
            _hashCode += getTARGET_CLIENT().hashCode();
        }
        if (getTARGET_CLIENT_B() != null) {
            _hashCode += getTARGET_CLIENT_B().hashCode();
        }
        if (getAGRE_NOM() != null) {
            _hashCode += getAGRE_NOM().hashCode();
        }
        if (getCONTRACT() != null) {
            _hashCode += getCONTRACT().hashCode();
        }
        if (getMOMENT() != null) {
            _hashCode += getMOMENT().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RowType_AgreementReLinkRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_AgreementReLinkRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TARGET_CLIENT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TARGET_CLIENT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TARGET_CLIENT_B");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TARGET_CLIENT_B"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("AGRE_NOM");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AGRE_NOM"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CONTRACT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CONTRACT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MOMENT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MOMENT"));
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
