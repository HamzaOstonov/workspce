/**
 * RowType_ReplaceTokenCard_Request.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package globus.issuing_v_01_02_xsd;

public class RowType_ReplaceTokenCard_Request  implements java.io.Serializable {
    private java.lang.String CARD;

    private java.lang.String NEW_CARD;

    private java.lang.String ACTIVATE_TOKENS;

    public RowType_ReplaceTokenCard_Request() {
    }

    public RowType_ReplaceTokenCard_Request(
           java.lang.String CARD,
           java.lang.String NEW_CARD,
           java.lang.String ACTIVATE_TOKENS) {
           this.CARD = CARD;
           this.NEW_CARD = NEW_CARD;
           this.ACTIVATE_TOKENS = ACTIVATE_TOKENS;
    }


    /**
     * Gets the CARD value for this RowType_ReplaceTokenCard_Request.
     * 
     * @return CARD
     */
    public java.lang.String getCARD() {
        return CARD;
    }


    /**
     * Sets the CARD value for this RowType_ReplaceTokenCard_Request.
     * 
     * @param CARD
     */
    public void setCARD(java.lang.String CARD) {
        this.CARD = CARD;
    }


    /**
     * Gets the NEW_CARD value for this RowType_ReplaceTokenCard_Request.
     * 
     * @return NEW_CARD
     */
    public java.lang.String getNEW_CARD() {
        return NEW_CARD;
    }


    /**
     * Sets the NEW_CARD value for this RowType_ReplaceTokenCard_Request.
     * 
     * @param NEW_CARD
     */
    public void setNEW_CARD(java.lang.String NEW_CARD) {
        this.NEW_CARD = NEW_CARD;
    }


    /**
     * Gets the ACTIVATE_TOKENS value for this RowType_ReplaceTokenCard_Request.
     * 
     * @return ACTIVATE_TOKENS
     */
    public java.lang.String getACTIVATE_TOKENS() {
        return ACTIVATE_TOKENS;
    }


    /**
     * Sets the ACTIVATE_TOKENS value for this RowType_ReplaceTokenCard_Request.
     * 
     * @param ACTIVATE_TOKENS
     */
    public void setACTIVATE_TOKENS(java.lang.String ACTIVATE_TOKENS) {
        this.ACTIVATE_TOKENS = ACTIVATE_TOKENS;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RowType_ReplaceTokenCard_Request)) return false;
        RowType_ReplaceTokenCard_Request other = (RowType_ReplaceTokenCard_Request) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.CARD==null && other.getCARD()==null) || 
             (this.CARD!=null &&
              this.CARD.equals(other.getCARD()))) &&
            ((this.NEW_CARD==null && other.getNEW_CARD()==null) || 
             (this.NEW_CARD!=null &&
              this.NEW_CARD.equals(other.getNEW_CARD()))) &&
            ((this.ACTIVATE_TOKENS==null && other.getACTIVATE_TOKENS()==null) || 
             (this.ACTIVATE_TOKENS!=null &&
              this.ACTIVATE_TOKENS.equals(other.getACTIVATE_TOKENS())));
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
        if (getCARD() != null) {
            _hashCode += getCARD().hashCode();
        }
        if (getNEW_CARD() != null) {
            _hashCode += getNEW_CARD().hashCode();
        }
        if (getACTIVATE_TOKENS() != null) {
            _hashCode += getACTIVATE_TOKENS().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RowType_ReplaceTokenCard_Request.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_ReplaceTokenCard_Request"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CARD");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CARD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NEW_CARD");
        elemField.setXmlName(new javax.xml.namespace.QName("", "NEW_CARD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ACTIVATE_TOKENS");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ACTIVATE_TOKENS"));
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
