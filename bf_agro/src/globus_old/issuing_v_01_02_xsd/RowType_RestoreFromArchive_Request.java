/**
 * RowType_RestoreFromArchive_Request.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package globus.issuing_v_01_02_xsd;

public class RowType_RestoreFromArchive_Request  implements java.io.Serializable {
    private java.lang.String BANK_C;

    private java.lang.String GROUPC;

    private java.lang.String CLIENT;

    private java.lang.String CLIENT_B;

    private java.math.BigDecimal AGRENOM;

    private java.lang.String CONTRACT;

    private java.math.BigDecimal ACCOUNT_NO;

    private java.lang.String CARD_ACCT;

    private java.lang.String TRANZ_ACCT;

    private java.lang.String FLAG_RESTORE_ALL;

    private java.lang.String FLAG_ACTIVATE_ALL;

    public RowType_RestoreFromArchive_Request() {
    }

    public RowType_RestoreFromArchive_Request(
           java.lang.String BANK_C,
           java.lang.String GROUPC,
           java.lang.String CLIENT,
           java.lang.String CLIENT_B,
           java.math.BigDecimal AGRENOM,
           java.lang.String CONTRACT,
           java.math.BigDecimal ACCOUNT_NO,
           java.lang.String CARD_ACCT,
           java.lang.String TRANZ_ACCT,
           java.lang.String FLAG_RESTORE_ALL,
           java.lang.String FLAG_ACTIVATE_ALL) {
           this.BANK_C = BANK_C;
           this.GROUPC = GROUPC;
           this.CLIENT = CLIENT;
           this.CLIENT_B = CLIENT_B;
           this.AGRENOM = AGRENOM;
           this.CONTRACT = CONTRACT;
           this.ACCOUNT_NO = ACCOUNT_NO;
           this.CARD_ACCT = CARD_ACCT;
           this.TRANZ_ACCT = TRANZ_ACCT;
           this.FLAG_RESTORE_ALL = FLAG_RESTORE_ALL;
           this.FLAG_ACTIVATE_ALL = FLAG_ACTIVATE_ALL;
    }


    /**
     * Gets the BANK_C value for this RowType_RestoreFromArchive_Request.
     * 
     * @return BANK_C
     */
    public java.lang.String getBANK_C() {
        return BANK_C;
    }


    /**
     * Sets the BANK_C value for this RowType_RestoreFromArchive_Request.
     * 
     * @param BANK_C
     */
    public void setBANK_C(java.lang.String BANK_C) {
        this.BANK_C = BANK_C;
    }


    /**
     * Gets the GROUPC value for this RowType_RestoreFromArchive_Request.
     * 
     * @return GROUPC
     */
    public java.lang.String getGROUPC() {
        return GROUPC;
    }


    /**
     * Sets the GROUPC value for this RowType_RestoreFromArchive_Request.
     * 
     * @param GROUPC
     */
    public void setGROUPC(java.lang.String GROUPC) {
        this.GROUPC = GROUPC;
    }


    /**
     * Gets the CLIENT value for this RowType_RestoreFromArchive_Request.
     * 
     * @return CLIENT
     */
    public java.lang.String getCLIENT() {
        return CLIENT;
    }


    /**
     * Sets the CLIENT value for this RowType_RestoreFromArchive_Request.
     * 
     * @param CLIENT
     */
    public void setCLIENT(java.lang.String CLIENT) {
        this.CLIENT = CLIENT;
    }


    /**
     * Gets the CLIENT_B value for this RowType_RestoreFromArchive_Request.
     * 
     * @return CLIENT_B
     */
    public java.lang.String getCLIENT_B() {
        return CLIENT_B;
    }


    /**
     * Sets the CLIENT_B value for this RowType_RestoreFromArchive_Request.
     * 
     * @param CLIENT_B
     */
    public void setCLIENT_B(java.lang.String CLIENT_B) {
        this.CLIENT_B = CLIENT_B;
    }


    /**
     * Gets the AGRENOM value for this RowType_RestoreFromArchive_Request.
     * 
     * @return AGRENOM
     */
    public java.math.BigDecimal getAGRENOM() {
        return AGRENOM;
    }


    /**
     * Sets the AGRENOM value for this RowType_RestoreFromArchive_Request.
     * 
     * @param AGRENOM
     */
    public void setAGRENOM(java.math.BigDecimal AGRENOM) {
        this.AGRENOM = AGRENOM;
    }


    /**
     * Gets the CONTRACT value for this RowType_RestoreFromArchive_Request.
     * 
     * @return CONTRACT
     */
    public java.lang.String getCONTRACT() {
        return CONTRACT;
    }


    /**
     * Sets the CONTRACT value for this RowType_RestoreFromArchive_Request.
     * 
     * @param CONTRACT
     */
    public void setCONTRACT(java.lang.String CONTRACT) {
        this.CONTRACT = CONTRACT;
    }


    /**
     * Gets the ACCOUNT_NO value for this RowType_RestoreFromArchive_Request.
     * 
     * @return ACCOUNT_NO
     */
    public java.math.BigDecimal getACCOUNT_NO() {
        return ACCOUNT_NO;
    }


    /**
     * Sets the ACCOUNT_NO value for this RowType_RestoreFromArchive_Request.
     * 
     * @param ACCOUNT_NO
     */
    public void setACCOUNT_NO(java.math.BigDecimal ACCOUNT_NO) {
        this.ACCOUNT_NO = ACCOUNT_NO;
    }


    /**
     * Gets the CARD_ACCT value for this RowType_RestoreFromArchive_Request.
     * 
     * @return CARD_ACCT
     */
    public java.lang.String getCARD_ACCT() {
        return CARD_ACCT;
    }


    /**
     * Sets the CARD_ACCT value for this RowType_RestoreFromArchive_Request.
     * 
     * @param CARD_ACCT
     */
    public void setCARD_ACCT(java.lang.String CARD_ACCT) {
        this.CARD_ACCT = CARD_ACCT;
    }


    /**
     * Gets the TRANZ_ACCT value for this RowType_RestoreFromArchive_Request.
     * 
     * @return TRANZ_ACCT
     */
    public java.lang.String getTRANZ_ACCT() {
        return TRANZ_ACCT;
    }


    /**
     * Sets the TRANZ_ACCT value for this RowType_RestoreFromArchive_Request.
     * 
     * @param TRANZ_ACCT
     */
    public void setTRANZ_ACCT(java.lang.String TRANZ_ACCT) {
        this.TRANZ_ACCT = TRANZ_ACCT;
    }


    /**
     * Gets the FLAG_RESTORE_ALL value for this RowType_RestoreFromArchive_Request.
     * 
     * @return FLAG_RESTORE_ALL
     */
    public java.lang.String getFLAG_RESTORE_ALL() {
        return FLAG_RESTORE_ALL;
    }


    /**
     * Sets the FLAG_RESTORE_ALL value for this RowType_RestoreFromArchive_Request.
     * 
     * @param FLAG_RESTORE_ALL
     */
    public void setFLAG_RESTORE_ALL(java.lang.String FLAG_RESTORE_ALL) {
        this.FLAG_RESTORE_ALL = FLAG_RESTORE_ALL;
    }


    /**
     * Gets the FLAG_ACTIVATE_ALL value for this RowType_RestoreFromArchive_Request.
     * 
     * @return FLAG_ACTIVATE_ALL
     */
    public java.lang.String getFLAG_ACTIVATE_ALL() {
        return FLAG_ACTIVATE_ALL;
    }


    /**
     * Sets the FLAG_ACTIVATE_ALL value for this RowType_RestoreFromArchive_Request.
     * 
     * @param FLAG_ACTIVATE_ALL
     */
    public void setFLAG_ACTIVATE_ALL(java.lang.String FLAG_ACTIVATE_ALL) {
        this.FLAG_ACTIVATE_ALL = FLAG_ACTIVATE_ALL;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RowType_RestoreFromArchive_Request)) return false;
        RowType_RestoreFromArchive_Request other = (RowType_RestoreFromArchive_Request) obj;
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
            ((this.CLIENT==null && other.getCLIENT()==null) || 
             (this.CLIENT!=null &&
              this.CLIENT.equals(other.getCLIENT()))) &&
            ((this.CLIENT_B==null && other.getCLIENT_B()==null) || 
             (this.CLIENT_B!=null &&
              this.CLIENT_B.equals(other.getCLIENT_B()))) &&
            ((this.AGRENOM==null && other.getAGRENOM()==null) || 
             (this.AGRENOM!=null &&
              this.AGRENOM.equals(other.getAGRENOM()))) &&
            ((this.CONTRACT==null && other.getCONTRACT()==null) || 
             (this.CONTRACT!=null &&
              this.CONTRACT.equals(other.getCONTRACT()))) &&
            ((this.ACCOUNT_NO==null && other.getACCOUNT_NO()==null) || 
             (this.ACCOUNT_NO!=null &&
              this.ACCOUNT_NO.equals(other.getACCOUNT_NO()))) &&
            ((this.CARD_ACCT==null && other.getCARD_ACCT()==null) || 
             (this.CARD_ACCT!=null &&
              this.CARD_ACCT.equals(other.getCARD_ACCT()))) &&
            ((this.TRANZ_ACCT==null && other.getTRANZ_ACCT()==null) || 
             (this.TRANZ_ACCT!=null &&
              this.TRANZ_ACCT.equals(other.getTRANZ_ACCT()))) &&
            ((this.FLAG_RESTORE_ALL==null && other.getFLAG_RESTORE_ALL()==null) || 
             (this.FLAG_RESTORE_ALL!=null &&
              this.FLAG_RESTORE_ALL.equals(other.getFLAG_RESTORE_ALL()))) &&
            ((this.FLAG_ACTIVATE_ALL==null && other.getFLAG_ACTIVATE_ALL()==null) || 
             (this.FLAG_ACTIVATE_ALL!=null &&
              this.FLAG_ACTIVATE_ALL.equals(other.getFLAG_ACTIVATE_ALL())));
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
        if (getCLIENT() != null) {
            _hashCode += getCLIENT().hashCode();
        }
        if (getCLIENT_B() != null) {
            _hashCode += getCLIENT_B().hashCode();
        }
        if (getAGRENOM() != null) {
            _hashCode += getAGRENOM().hashCode();
        }
        if (getCONTRACT() != null) {
            _hashCode += getCONTRACT().hashCode();
        }
        if (getACCOUNT_NO() != null) {
            _hashCode += getACCOUNT_NO().hashCode();
        }
        if (getCARD_ACCT() != null) {
            _hashCode += getCARD_ACCT().hashCode();
        }
        if (getTRANZ_ACCT() != null) {
            _hashCode += getTRANZ_ACCT().hashCode();
        }
        if (getFLAG_RESTORE_ALL() != null) {
            _hashCode += getFLAG_RESTORE_ALL().hashCode();
        }
        if (getFLAG_ACTIVATE_ALL() != null) {
            _hashCode += getFLAG_ACTIVATE_ALL().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RowType_RestoreFromArchive_Request.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_RestoreFromArchive_Request"));
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
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CLIENT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CLIENT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CLIENT_B");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CLIENT_B"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("AGRENOM");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AGRENOM"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CONTRACT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CONTRACT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ACCOUNT_NO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ACCOUNT_NO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CARD_ACCT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CARD_ACCT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TRANZ_ACCT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TRANZ_ACCT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FLAG_RESTORE_ALL");
        elemField.setXmlName(new javax.xml.namespace.QName("", "FLAG_RESTORE_ALL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FLAG_ACTIVATE_ALL");
        elemField.setXmlName(new javax.xml.namespace.QName("", "FLAG_ACTIVATE_ALL"));
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
