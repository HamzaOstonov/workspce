/**
 * RowType_QueryRealTimeStatement_Request.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package issuing_v_01_02_xsd;

public class RowType_QueryRealTimeStatement_Request  implements java.io.Serializable {
    private java.lang.String CARD;

    private java.util.Calendar BEGIN_DATE;

    private java.util.Calendar END_DATE;

    private java.lang.String INCL_UNPROC_TR;

    private java.lang.String INCL_LOCKS;

    public RowType_QueryRealTimeStatement_Request() {
    }

    public RowType_QueryRealTimeStatement_Request(
           java.lang.String CARD,
           java.util.Calendar BEGIN_DATE,
           java.util.Calendar END_DATE,
           java.lang.String INCL_UNPROC_TR,
           java.lang.String INCL_LOCKS) {
           this.CARD = CARD;
           this.BEGIN_DATE = BEGIN_DATE;
           this.END_DATE = END_DATE;
           this.INCL_UNPROC_TR = INCL_UNPROC_TR;
           this.INCL_LOCKS = INCL_LOCKS;
    }


    /**
     * Gets the CARD value for this RowType_QueryRealTimeStatement_Request.
     * 
     * @return CARD
     */
    public java.lang.String getCARD() {
        return CARD;
    }


    /**
     * Sets the CARD value for this RowType_QueryRealTimeStatement_Request.
     * 
     * @param CARD
     */
    public void setCARD(java.lang.String CARD) {
        this.CARD = CARD;
    }


    /**
     * Gets the BEGIN_DATE value for this RowType_QueryRealTimeStatement_Request.
     * 
     * @return BEGIN_DATE
     */
    public java.util.Calendar getBEGIN_DATE() {
        return BEGIN_DATE;
    }


    /**
     * Sets the BEGIN_DATE value for this RowType_QueryRealTimeStatement_Request.
     * 
     * @param BEGIN_DATE
     */
    public void setBEGIN_DATE(java.util.Calendar BEGIN_DATE) {
        this.BEGIN_DATE = BEGIN_DATE;
    }


    /**
     * Gets the END_DATE value for this RowType_QueryRealTimeStatement_Request.
     * 
     * @return END_DATE
     */
    public java.util.Calendar getEND_DATE() {
        return END_DATE;
    }


    /**
     * Sets the END_DATE value for this RowType_QueryRealTimeStatement_Request.
     * 
     * @param END_DATE
     */
    public void setEND_DATE(java.util.Calendar END_DATE) {
        this.END_DATE = END_DATE;
    }


    /**
     * Gets the INCL_UNPROC_TR value for this RowType_QueryRealTimeStatement_Request.
     * 
     * @return INCL_UNPROC_TR
     */
    public java.lang.String getINCL_UNPROC_TR() {
        return INCL_UNPROC_TR;
    }


    /**
     * Sets the INCL_UNPROC_TR value for this RowType_QueryRealTimeStatement_Request.
     * 
     * @param INCL_UNPROC_TR
     */
    public void setINCL_UNPROC_TR(java.lang.String INCL_UNPROC_TR) {
        this.INCL_UNPROC_TR = INCL_UNPROC_TR;
    }


    /**
     * Gets the INCL_LOCKS value for this RowType_QueryRealTimeStatement_Request.
     * 
     * @return INCL_LOCKS
     */
    public java.lang.String getINCL_LOCKS() {
        return INCL_LOCKS;
    }


    /**
     * Sets the INCL_LOCKS value for this RowType_QueryRealTimeStatement_Request.
     * 
     * @param INCL_LOCKS
     */
    public void setINCL_LOCKS(java.lang.String INCL_LOCKS) {
        this.INCL_LOCKS = INCL_LOCKS;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RowType_QueryRealTimeStatement_Request)) return false;
        RowType_QueryRealTimeStatement_Request other = (RowType_QueryRealTimeStatement_Request) obj;
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
            ((this.BEGIN_DATE==null && other.getBEGIN_DATE()==null) || 
             (this.BEGIN_DATE!=null &&
              this.BEGIN_DATE.equals(other.getBEGIN_DATE()))) &&
            ((this.END_DATE==null && other.getEND_DATE()==null) || 
             (this.END_DATE!=null &&
              this.END_DATE.equals(other.getEND_DATE()))) &&
            ((this.INCL_UNPROC_TR==null && other.getINCL_UNPROC_TR()==null) || 
             (this.INCL_UNPROC_TR!=null &&
              this.INCL_UNPROC_TR.equals(other.getINCL_UNPROC_TR()))) &&
            ((this.INCL_LOCKS==null && other.getINCL_LOCKS()==null) || 
             (this.INCL_LOCKS!=null &&
              this.INCL_LOCKS.equals(other.getINCL_LOCKS())));
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
        if (getBEGIN_DATE() != null) {
            _hashCode += getBEGIN_DATE().hashCode();
        }
        if (getEND_DATE() != null) {
            _hashCode += getEND_DATE().hashCode();
        }
        if (getINCL_UNPROC_TR() != null) {
            _hashCode += getINCL_UNPROC_TR().hashCode();
        }
        if (getINCL_LOCKS() != null) {
            _hashCode += getINCL_LOCKS().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RowType_QueryRealTimeStatement_Request.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_QueryRealTimeStatement_Request"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CARD");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CARD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("BEGIN_DATE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "BEGIN_DATE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("END_DATE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "END_DATE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("INCL_UNPROC_TR");
        elemField.setXmlName(new javax.xml.namespace.QName("", "INCL_UNPROC_TR"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("INCL_LOCKS");
        elemField.setXmlName(new javax.xml.namespace.QName("", "INCL_LOCKS"));
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
