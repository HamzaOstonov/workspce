/**
 * BPRelReqest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package relationships.NCI.com.ipakyulibank;

public class BPRelReqest  implements java.io.Serializable {
    /* Вид клиента:
     * 1 – ФЛ
     * 2 – ЮЛ */
    private java.lang.String bp_type;

    /* Номер клиента в NCI */
    private java.lang.String bp_extnum;

    /* филиал */
    private java.lang.String branch;

    /* BP number SAP */
    private java.lang.String id_client_sap;

    public BPRelReqest() {
    }

    public BPRelReqest(
           java.lang.String bp_type,
           java.lang.String bp_extnum,
           java.lang.String branch,
           java.lang.String id_client_sap) {
           this.bp_type = bp_type;
           this.bp_extnum = bp_extnum;
           this.branch = branch;
           this.id_client_sap = id_client_sap;
    }


    /**
     * Gets the bp_type value for this BPRelReqest.
     * 
     * @return bp_type   * Вид клиента:
     * 1 – ФЛ
     * 2 – ЮЛ
     */
    public java.lang.String getBp_type() {
        return bp_type;
    }


    /**
     * Sets the bp_type value for this BPRelReqest.
     * 
     * @param bp_type   * Вид клиента:
     * 1 – ФЛ
     * 2 – ЮЛ
     */
    public void setBp_type(java.lang.String bp_type) {
        this.bp_type = bp_type;
    }


    /**
     * Gets the bp_extnum value for this BPRelReqest.
     * 
     * @return bp_extnum   * Номер клиента в NCI
     */
    public java.lang.String getBp_extnum() {
        return bp_extnum;
    }


    /**
     * Sets the bp_extnum value for this BPRelReqest.
     * 
     * @param bp_extnum   * Номер клиента в NCI
     */
    public void setBp_extnum(java.lang.String bp_extnum) {
        this.bp_extnum = bp_extnum;
    }


    /**
     * Gets the branch value for this BPRelReqest.
     * 
     * @return branch   * филиал
     */
    public java.lang.String getBranch() {
        return branch;
    }


    /**
     * Sets the branch value for this BPRelReqest.
     * 
     * @param branch   * филиал
     */
    public void setBranch(java.lang.String branch) {
        this.branch = branch;
    }


    /**
     * Gets the id_client_sap value for this BPRelReqest.
     * 
     * @return id_client_sap   * BP number SAP
     */
    public java.lang.String getId_client_sap() {
        return id_client_sap;
    }


    /**
     * Sets the id_client_sap value for this BPRelReqest.
     * 
     * @param id_client_sap   * BP number SAP
     */
    public void setId_client_sap(java.lang.String id_client_sap) {
        this.id_client_sap = id_client_sap;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BPRelReqest)) return false;
        BPRelReqest other = (BPRelReqest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.bp_type==null && other.getBp_type()==null) || 
             (this.bp_type!=null &&
              this.bp_type.equals(other.getBp_type()))) &&
            ((this.bp_extnum==null && other.getBp_extnum()==null) || 
             (this.bp_extnum!=null &&
              this.bp_extnum.equals(other.getBp_extnum()))) &&
            ((this.branch==null && other.getBranch()==null) || 
             (this.branch!=null &&
              this.branch.equals(other.getBranch()))) &&
            ((this.id_client_sap==null && other.getId_client_sap()==null) || 
             (this.id_client_sap!=null &&
              this.id_client_sap.equals(other.getId_client_sap())));
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
        if (getBp_type() != null) {
            _hashCode += getBp_type().hashCode();
        }
        if (getBp_extnum() != null) {
            _hashCode += getBp_extnum().hashCode();
        }
        if (getBranch() != null) {
            _hashCode += getBranch().hashCode();
        }
        if (getId_client_sap() != null) {
            _hashCode += getId_client_sap().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BPRelReqest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:relationships", "BPRelReqest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bp_type");
        elemField.setXmlName(new javax.xml.namespace.QName("", "bp_type"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bp_extnum");
        elemField.setXmlName(new javax.xml.namespace.QName("", "bp_extnum"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("branch");
        elemField.setXmlName(new javax.xml.namespace.QName("", "branch"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id_client_sap");
        elemField.setXmlName(new javax.xml.namespace.QName("", "id_client_sap"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
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
