/**
 * RowType_QueryBillInfo_Request.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package capitalBank.issuing_v_01_02_xsd;

public class RowType_QueryBillInfo_Request  implements java.io.Serializable {
    private java.lang.String CLIENT;

    private java.lang.String CLIENT_B;

    private java.math.BigInteger ACCOUNT_NO;

    private java.math.BigInteger BACCOUNT_NO;

    private java.math.BigInteger INTERNAL_NO;

    private java.util.Calendar BILL_DATE;

    private java.lang.String BANK_C;

    public RowType_QueryBillInfo_Request() {
    }

    public RowType_QueryBillInfo_Request(
           java.lang.String CLIENT,
           java.lang.String CLIENT_B,
           java.math.BigInteger ACCOUNT_NO,
           java.math.BigInteger BACCOUNT_NO,
           java.math.BigInteger INTERNAL_NO,
           java.util.Calendar BILL_DATE,
           java.lang.String BANK_C) {
           this.CLIENT = CLIENT;
           this.CLIENT_B = CLIENT_B;
           this.ACCOUNT_NO = ACCOUNT_NO;
           this.BACCOUNT_NO = BACCOUNT_NO;
           this.INTERNAL_NO = INTERNAL_NO;
           this.BILL_DATE = BILL_DATE;
           this.BANK_C = BANK_C;
    }


    /**
     * Gets the CLIENT value for this RowType_QueryBillInfo_Request.
     * 
     * @return CLIENT
     */
    public java.lang.String getCLIENT() {
        return CLIENT;
    }


    /**
     * Sets the CLIENT value for this RowType_QueryBillInfo_Request.
     * 
     * @param CLIENT
     */
    public void setCLIENT(java.lang.String CLIENT) {
        this.CLIENT = CLIENT;
    }


    /**
     * Gets the CLIENT_B value for this RowType_QueryBillInfo_Request.
     * 
     * @return CLIENT_B
     */
    public java.lang.String getCLIENT_B() {
        return CLIENT_B;
    }


    /**
     * Sets the CLIENT_B value for this RowType_QueryBillInfo_Request.
     * 
     * @param CLIENT_B
     */
    public void setCLIENT_B(java.lang.String CLIENT_B) {
        this.CLIENT_B = CLIENT_B;
    }


    /**
     * Gets the ACCOUNT_NO value for this RowType_QueryBillInfo_Request.
     * 
     * @return ACCOUNT_NO
     */
    public java.math.BigInteger getACCOUNT_NO() {
        return ACCOUNT_NO;
    }


    /**
     * Sets the ACCOUNT_NO value for this RowType_QueryBillInfo_Request.
     * 
     * @param ACCOUNT_NO
     */
    public void setACCOUNT_NO(java.math.BigInteger ACCOUNT_NO) {
        this.ACCOUNT_NO = ACCOUNT_NO;
    }


    /**
     * Gets the BACCOUNT_NO value for this RowType_QueryBillInfo_Request.
     * 
     * @return BACCOUNT_NO
     */
    public java.math.BigInteger getBACCOUNT_NO() {
        return BACCOUNT_NO;
    }


    /**
     * Sets the BACCOUNT_NO value for this RowType_QueryBillInfo_Request.
     * 
     * @param BACCOUNT_NO
     */
    public void setBACCOUNT_NO(java.math.BigInteger BACCOUNT_NO) {
        this.BACCOUNT_NO = BACCOUNT_NO;
    }


    /**
     * Gets the INTERNAL_NO value for this RowType_QueryBillInfo_Request.
     * 
     * @return INTERNAL_NO
     */
    public java.math.BigInteger getINTERNAL_NO() {
        return INTERNAL_NO;
    }


    /**
     * Sets the INTERNAL_NO value for this RowType_QueryBillInfo_Request.
     * 
     * @param INTERNAL_NO
     */
    public void setINTERNAL_NO(java.math.BigInteger INTERNAL_NO) {
        this.INTERNAL_NO = INTERNAL_NO;
    }


    /**
     * Gets the BILL_DATE value for this RowType_QueryBillInfo_Request.
     * 
     * @return BILL_DATE
     */
    public java.util.Calendar getBILL_DATE() {
        return BILL_DATE;
    }


    /**
     * Sets the BILL_DATE value for this RowType_QueryBillInfo_Request.
     * 
     * @param BILL_DATE
     */
    public void setBILL_DATE(java.util.Calendar BILL_DATE) {
        this.BILL_DATE = BILL_DATE;
    }


    /**
     * Gets the BANK_C value for this RowType_QueryBillInfo_Request.
     * 
     * @return BANK_C
     */
    public java.lang.String getBANK_C() {
        return BANK_C;
    }


    /**
     * Sets the BANK_C value for this RowType_QueryBillInfo_Request.
     * 
     * @param BANK_C
     */
    public void setBANK_C(java.lang.String BANK_C) {
        this.BANK_C = BANK_C;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RowType_QueryBillInfo_Request)) return false;
        RowType_QueryBillInfo_Request other = (RowType_QueryBillInfo_Request) obj;
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
            ((this.CLIENT_B==null && other.getCLIENT_B()==null) || 
             (this.CLIENT_B!=null &&
              this.CLIENT_B.equals(other.getCLIENT_B()))) &&
            ((this.ACCOUNT_NO==null && other.getACCOUNT_NO()==null) || 
             (this.ACCOUNT_NO!=null &&
              this.ACCOUNT_NO.equals(other.getACCOUNT_NO()))) &&
            ((this.BACCOUNT_NO==null && other.getBACCOUNT_NO()==null) || 
             (this.BACCOUNT_NO!=null &&
              this.BACCOUNT_NO.equals(other.getBACCOUNT_NO()))) &&
            ((this.INTERNAL_NO==null && other.getINTERNAL_NO()==null) || 
             (this.INTERNAL_NO!=null &&
              this.INTERNAL_NO.equals(other.getINTERNAL_NO()))) &&
            ((this.BILL_DATE==null && other.getBILL_DATE()==null) || 
             (this.BILL_DATE!=null &&
              this.BILL_DATE.equals(other.getBILL_DATE()))) &&
            ((this.BANK_C==null && other.getBANK_C()==null) || 
             (this.BANK_C!=null &&
              this.BANK_C.equals(other.getBANK_C())));
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
        if (getCLIENT_B() != null) {
            _hashCode += getCLIENT_B().hashCode();
        }
        if (getACCOUNT_NO() != null) {
            _hashCode += getACCOUNT_NO().hashCode();
        }
        if (getBACCOUNT_NO() != null) {
            _hashCode += getBACCOUNT_NO().hashCode();
        }
        if (getINTERNAL_NO() != null) {
            _hashCode += getINTERNAL_NO().hashCode();
        }
        if (getBILL_DATE() != null) {
            _hashCode += getBILL_DATE().hashCode();
        }
        if (getBANK_C() != null) {
            _hashCode += getBANK_C().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RowType_QueryBillInfo_Request.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_QueryBillInfo_Request"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ACCOUNT_NO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ACCOUNT_NO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("BACCOUNT_NO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "BACCOUNT_NO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("INTERNAL_NO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "INTERNAL_NO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("BILL_DATE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "BILL_DATE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("BANK_C");
        elemField.setXmlName(new javax.xml.namespace.QName("", "BANK_C"));
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
