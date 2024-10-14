/**
 * RowType_CalculateInstallmentPlanNew_Response.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package globus.issuing_v_01_02_xsd;

public class RowType_CalculateInstallmentPlanNew_Response  implements java.io.Serializable {
    private java.lang.String OPEN_FEE_CCY;

    private java.math.BigDecimal OPEN_FEE_AMOUNT;

    private globus.issuing_v_01_02_xsd.ListType_Generic plan;

    public RowType_CalculateInstallmentPlanNew_Response() {
    }

    public RowType_CalculateInstallmentPlanNew_Response(
           java.lang.String OPEN_FEE_CCY,
           java.math.BigDecimal OPEN_FEE_AMOUNT,
           globus.issuing_v_01_02_xsd.ListType_Generic plan) {
           this.OPEN_FEE_CCY = OPEN_FEE_CCY;
           this.OPEN_FEE_AMOUNT = OPEN_FEE_AMOUNT;
           this.plan = plan;
    }


    /**
     * Gets the OPEN_FEE_CCY value for this RowType_CalculateInstallmentPlanNew_Response.
     * 
     * @return OPEN_FEE_CCY
     */
    public java.lang.String getOPEN_FEE_CCY() {
        return OPEN_FEE_CCY;
    }


    /**
     * Sets the OPEN_FEE_CCY value for this RowType_CalculateInstallmentPlanNew_Response.
     * 
     * @param OPEN_FEE_CCY
     */
    public void setOPEN_FEE_CCY(java.lang.String OPEN_FEE_CCY) {
        this.OPEN_FEE_CCY = OPEN_FEE_CCY;
    }


    /**
     * Gets the OPEN_FEE_AMOUNT value for this RowType_CalculateInstallmentPlanNew_Response.
     * 
     * @return OPEN_FEE_AMOUNT
     */
    public java.math.BigDecimal getOPEN_FEE_AMOUNT() {
        return OPEN_FEE_AMOUNT;
    }


    /**
     * Sets the OPEN_FEE_AMOUNT value for this RowType_CalculateInstallmentPlanNew_Response.
     * 
     * @param OPEN_FEE_AMOUNT
     */
    public void setOPEN_FEE_AMOUNT(java.math.BigDecimal OPEN_FEE_AMOUNT) {
        this.OPEN_FEE_AMOUNT = OPEN_FEE_AMOUNT;
    }


    /**
     * Gets the plan value for this RowType_CalculateInstallmentPlanNew_Response.
     * 
     * @return plan
     */
    public globus.issuing_v_01_02_xsd.ListType_Generic getPlan() {
        return plan;
    }


    /**
     * Sets the plan value for this RowType_CalculateInstallmentPlanNew_Response.
     * 
     * @param plan
     */
    public void setPlan(globus.issuing_v_01_02_xsd.ListType_Generic plan) {
        this.plan = plan;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RowType_CalculateInstallmentPlanNew_Response)) return false;
        RowType_CalculateInstallmentPlanNew_Response other = (RowType_CalculateInstallmentPlanNew_Response) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.OPEN_FEE_CCY==null && other.getOPEN_FEE_CCY()==null) || 
             (this.OPEN_FEE_CCY!=null &&
              this.OPEN_FEE_CCY.equals(other.getOPEN_FEE_CCY()))) &&
            ((this.OPEN_FEE_AMOUNT==null && other.getOPEN_FEE_AMOUNT()==null) || 
             (this.OPEN_FEE_AMOUNT!=null &&
              this.OPEN_FEE_AMOUNT.equals(other.getOPEN_FEE_AMOUNT()))) &&
            ((this.plan==null && other.getPlan()==null) || 
             (this.plan!=null &&
              this.plan.equals(other.getPlan())));
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
        if (getOPEN_FEE_CCY() != null) {
            _hashCode += getOPEN_FEE_CCY().hashCode();
        }
        if (getOPEN_FEE_AMOUNT() != null) {
            _hashCode += getOPEN_FEE_AMOUNT().hashCode();
        }
        if (getPlan() != null) {
            _hashCode += getPlan().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RowType_CalculateInstallmentPlanNew_Response.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_CalculateInstallmentPlanNew_Response"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("OPEN_FEE_CCY");
        elemField.setXmlName(new javax.xml.namespace.QName("", "OPEN_FEE_CCY"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("OPEN_FEE_AMOUNT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "OPEN_FEE_AMOUNT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("plan");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Plan"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "ListType_Generic"));
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
