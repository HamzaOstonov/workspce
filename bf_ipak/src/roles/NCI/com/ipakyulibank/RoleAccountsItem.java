/**
 * RoleAccountsItem.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package roles.NCI.com.ipakyulibank;

public class RoleAccountsItem  implements java.io.Serializable {
    private java.lang.String action;

    private java.lang.String account_number;

    private java.lang.String mfo;

    private java.lang.String country;

    public RoleAccountsItem() {
    }

    public RoleAccountsItem(
           java.lang.String action,
           java.lang.String account_number,
           java.lang.String mfo,
           java.lang.String country) {
           this.action = action;
           this.account_number = account_number;
           this.mfo = mfo;
           this.country = country;
    }


    /**
     * Gets the action value for this RoleAccountsItem.
     * 
     * @return action
     */
    public java.lang.String getAction() {
        return action;
    }


    /**
     * Sets the action value for this RoleAccountsItem.
     * 
     * @param action
     */
    public void setAction(java.lang.String action) {
        this.action = action;
    }


    /**
     * Gets the account_number value for this RoleAccountsItem.
     * 
     * @return account_number
     */
    public java.lang.String getAccount_number() {
        return account_number;
    }


    /**
     * Sets the account_number value for this RoleAccountsItem.
     * 
     * @param account_number
     */
    public void setAccount_number(java.lang.String account_number) {
        this.account_number = account_number;
    }


    /**
     * Gets the mfo value for this RoleAccountsItem.
     * 
     * @return mfo
     */
    public java.lang.String getMfo() {
        return mfo;
    }


    /**
     * Sets the mfo value for this RoleAccountsItem.
     * 
     * @param mfo
     */
    public void setMfo(java.lang.String mfo) {
        this.mfo = mfo;
    }


    /**
     * Gets the country value for this RoleAccountsItem.
     * 
     * @return country
     */
    public java.lang.String getCountry() {
        return country;
    }


    /**
     * Sets the country value for this RoleAccountsItem.
     * 
     * @param country
     */
    public void setCountry(java.lang.String country) {
        this.country = country;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RoleAccountsItem)) return false;
        RoleAccountsItem other = (RoleAccountsItem) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.action==null && other.getAction()==null) || 
             (this.action!=null &&
              this.action.equals(other.getAction()))) &&
            ((this.account_number==null && other.getAccount_number()==null) || 
             (this.account_number!=null &&
              this.account_number.equals(other.getAccount_number()))) &&
            ((this.mfo==null && other.getMfo()==null) || 
             (this.mfo!=null &&
              this.mfo.equals(other.getMfo()))) &&
            ((this.country==null && other.getCountry()==null) || 
             (this.country!=null &&
              this.country.equals(other.getCountry())));
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
        if (getAction() != null) {
            _hashCode += getAction().hashCode();
        }
        if (getAccount_number() != null) {
            _hashCode += getAccount_number().hashCode();
        }
        if (getMfo() != null) {
            _hashCode += getMfo().hashCode();
        }
        if (getCountry() != null) {
            _hashCode += getCountry().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RoleAccountsItem.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:roles", ">RoleAccounts>item"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("action");
        elemField.setXmlName(new javax.xml.namespace.QName("", "action"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("account_number");
        elemField.setXmlName(new javax.xml.namespace.QName("", "account_number"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mfo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("country");
        elemField.setXmlName(new javax.xml.namespace.QName("", "country"));
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
