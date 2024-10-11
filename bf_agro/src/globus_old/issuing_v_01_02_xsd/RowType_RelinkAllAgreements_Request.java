/**
 * RowType_RelinkAllAgreements_Request.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package globus.issuing_v_01_02_xsd;

public class RowType_RelinkAllAgreements_Request  implements java.io.Serializable {
    private java.lang.String BANK_C;

    private java.lang.String GROUPC;

    private java.lang.String TARGET_CLIENT;

    private java.lang.String TARGET_CLIENT_B;

    private java.lang.String SOURCE_CLIENT;

    private java.lang.String SOURCE_CLIENT_B;

    private java.lang.String MOMENT;

    private java.lang.String REMOVE_CLIENT;

    public RowType_RelinkAllAgreements_Request() {
    }

    public RowType_RelinkAllAgreements_Request(
           java.lang.String BANK_C,
           java.lang.String GROUPC,
           java.lang.String TARGET_CLIENT,
           java.lang.String TARGET_CLIENT_B,
           java.lang.String SOURCE_CLIENT,
           java.lang.String SOURCE_CLIENT_B,
           java.lang.String MOMENT,
           java.lang.String REMOVE_CLIENT) {
           this.BANK_C = BANK_C;
           this.GROUPC = GROUPC;
           this.TARGET_CLIENT = TARGET_CLIENT;
           this.TARGET_CLIENT_B = TARGET_CLIENT_B;
           this.SOURCE_CLIENT = SOURCE_CLIENT;
           this.SOURCE_CLIENT_B = SOURCE_CLIENT_B;
           this.MOMENT = MOMENT;
           this.REMOVE_CLIENT = REMOVE_CLIENT;
    }


    /**
     * Gets the BANK_C value for this RowType_RelinkAllAgreements_Request.
     * 
     * @return BANK_C
     */
    public java.lang.String getBANK_C() {
        return BANK_C;
    }


    /**
     * Sets the BANK_C value for this RowType_RelinkAllAgreements_Request.
     * 
     * @param BANK_C
     */
    public void setBANK_C(java.lang.String BANK_C) {
        this.BANK_C = BANK_C;
    }


    /**
     * Gets the GROUPC value for this RowType_RelinkAllAgreements_Request.
     * 
     * @return GROUPC
     */
    public java.lang.String getGROUPC() {
        return GROUPC;
    }


    /**
     * Sets the GROUPC value for this RowType_RelinkAllAgreements_Request.
     * 
     * @param GROUPC
     */
    public void setGROUPC(java.lang.String GROUPC) {
        this.GROUPC = GROUPC;
    }


    /**
     * Gets the TARGET_CLIENT value for this RowType_RelinkAllAgreements_Request.
     * 
     * @return TARGET_CLIENT
     */
    public java.lang.String getTARGET_CLIENT() {
        return TARGET_CLIENT;
    }


    /**
     * Sets the TARGET_CLIENT value for this RowType_RelinkAllAgreements_Request.
     * 
     * @param TARGET_CLIENT
     */
    public void setTARGET_CLIENT(java.lang.String TARGET_CLIENT) {
        this.TARGET_CLIENT = TARGET_CLIENT;
    }


    /**
     * Gets the TARGET_CLIENT_B value for this RowType_RelinkAllAgreements_Request.
     * 
     * @return TARGET_CLIENT_B
     */
    public java.lang.String getTARGET_CLIENT_B() {
        return TARGET_CLIENT_B;
    }


    /**
     * Sets the TARGET_CLIENT_B value for this RowType_RelinkAllAgreements_Request.
     * 
     * @param TARGET_CLIENT_B
     */
    public void setTARGET_CLIENT_B(java.lang.String TARGET_CLIENT_B) {
        this.TARGET_CLIENT_B = TARGET_CLIENT_B;
    }


    /**
     * Gets the SOURCE_CLIENT value for this RowType_RelinkAllAgreements_Request.
     * 
     * @return SOURCE_CLIENT
     */
    public java.lang.String getSOURCE_CLIENT() {
        return SOURCE_CLIENT;
    }


    /**
     * Sets the SOURCE_CLIENT value for this RowType_RelinkAllAgreements_Request.
     * 
     * @param SOURCE_CLIENT
     */
    public void setSOURCE_CLIENT(java.lang.String SOURCE_CLIENT) {
        this.SOURCE_CLIENT = SOURCE_CLIENT;
    }


    /**
     * Gets the SOURCE_CLIENT_B value for this RowType_RelinkAllAgreements_Request.
     * 
     * @return SOURCE_CLIENT_B
     */
    public java.lang.String getSOURCE_CLIENT_B() {
        return SOURCE_CLIENT_B;
    }


    /**
     * Sets the SOURCE_CLIENT_B value for this RowType_RelinkAllAgreements_Request.
     * 
     * @param SOURCE_CLIENT_B
     */
    public void setSOURCE_CLIENT_B(java.lang.String SOURCE_CLIENT_B) {
        this.SOURCE_CLIENT_B = SOURCE_CLIENT_B;
    }


    /**
     * Gets the MOMENT value for this RowType_RelinkAllAgreements_Request.
     * 
     * @return MOMENT
     */
    public java.lang.String getMOMENT() {
        return MOMENT;
    }


    /**
     * Sets the MOMENT value for this RowType_RelinkAllAgreements_Request.
     * 
     * @param MOMENT
     */
    public void setMOMENT(java.lang.String MOMENT) {
        this.MOMENT = MOMENT;
    }


    /**
     * Gets the REMOVE_CLIENT value for this RowType_RelinkAllAgreements_Request.
     * 
     * @return REMOVE_CLIENT
     */
    public java.lang.String getREMOVE_CLIENT() {
        return REMOVE_CLIENT;
    }


    /**
     * Sets the REMOVE_CLIENT value for this RowType_RelinkAllAgreements_Request.
     * 
     * @param REMOVE_CLIENT
     */
    public void setREMOVE_CLIENT(java.lang.String REMOVE_CLIENT) {
        this.REMOVE_CLIENT = REMOVE_CLIENT;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RowType_RelinkAllAgreements_Request)) return false;
        RowType_RelinkAllAgreements_Request other = (RowType_RelinkAllAgreements_Request) obj;
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
            ((this.SOURCE_CLIENT==null && other.getSOURCE_CLIENT()==null) || 
             (this.SOURCE_CLIENT!=null &&
              this.SOURCE_CLIENT.equals(other.getSOURCE_CLIENT()))) &&
            ((this.SOURCE_CLIENT_B==null && other.getSOURCE_CLIENT_B()==null) || 
             (this.SOURCE_CLIENT_B!=null &&
              this.SOURCE_CLIENT_B.equals(other.getSOURCE_CLIENT_B()))) &&
            ((this.MOMENT==null && other.getMOMENT()==null) || 
             (this.MOMENT!=null &&
              this.MOMENT.equals(other.getMOMENT()))) &&
            ((this.REMOVE_CLIENT==null && other.getREMOVE_CLIENT()==null) || 
             (this.REMOVE_CLIENT!=null &&
              this.REMOVE_CLIENT.equals(other.getREMOVE_CLIENT())));
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
        if (getSOURCE_CLIENT() != null) {
            _hashCode += getSOURCE_CLIENT().hashCode();
        }
        if (getSOURCE_CLIENT_B() != null) {
            _hashCode += getSOURCE_CLIENT_B().hashCode();
        }
        if (getMOMENT() != null) {
            _hashCode += getMOMENT().hashCode();
        }
        if (getREMOVE_CLIENT() != null) {
            _hashCode += getREMOVE_CLIENT().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RowType_RelinkAllAgreements_Request.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_RelinkAllAgreements_Request"));
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
        elemField.setFieldName("SOURCE_CLIENT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SOURCE_CLIENT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SOURCE_CLIENT_B");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SOURCE_CLIENT_B"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MOMENT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MOMENT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("REMOVE_CLIENT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "REMOVE_CLIENT"));
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
