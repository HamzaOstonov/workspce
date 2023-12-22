/**
 * RolesByEventReqest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package roles.NCI.com.ipakyulibank;

public class RolesByEventReqest  implements java.io.Serializable {
    private relationships.NCI.com.ipakyulibank.BPContent bp_01;

    private java.lang.String[] events;

    private roles.NCI.com.ipakyulibank.RoleAccountsItem[] accounts;

    public RolesByEventReqest() {
    }

    public RolesByEventReqest(
           relationships.NCI.com.ipakyulibank.BPContent bp_01,
           java.lang.String[] events,
           roles.NCI.com.ipakyulibank.RoleAccountsItem[] accounts) {
           this.bp_01 = bp_01;
           this.events = events;
           this.accounts = accounts;
    }


    /**
     * Gets the bp_01 value for this RolesByEventReqest.
     * 
     * @return bp_01
     */
    public relationships.NCI.com.ipakyulibank.BPContent getBp_01() {
        return bp_01;
    }


    /**
     * Sets the bp_01 value for this RolesByEventReqest.
     * 
     * @param bp_01
     */
    public void setBp_01(relationships.NCI.com.ipakyulibank.BPContent bp_01) {
        this.bp_01 = bp_01;
    }


    /**
     * Gets the events value for this RolesByEventReqest.
     * 
     * @return events
     */
    public java.lang.String[] getEvents() {
        return events;
    }


    /**
     * Sets the events value for this RolesByEventReqest.
     * 
     * @param events
     */
    public void setEvents(java.lang.String[] events) {
        this.events = events;
    }


    /**
     * Gets the accounts value for this RolesByEventReqest.
     * 
     * @return accounts
     */
    public roles.NCI.com.ipakyulibank.RoleAccountsItem[] getAccounts() {
        return accounts;
    }


    /**
     * Sets the accounts value for this RolesByEventReqest.
     * 
     * @param accounts
     */
    public void setAccounts(roles.NCI.com.ipakyulibank.RoleAccountsItem[] accounts) {
        this.accounts = accounts;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RolesByEventReqest)) return false;
        RolesByEventReqest other = (RolesByEventReqest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.bp_01==null && other.getBp_01()==null) || 
             (this.bp_01!=null &&
              this.bp_01.equals(other.getBp_01()))) &&
            ((this.events==null && other.getEvents()==null) || 
             (this.events!=null &&
              java.util.Arrays.equals(this.events, other.getEvents()))) &&
            ((this.accounts==null && other.getAccounts()==null) || 
             (this.accounts!=null &&
              java.util.Arrays.equals(this.accounts, other.getAccounts())));
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
        if (getBp_01() != null) {
            _hashCode += getBp_01().hashCode();
        }
        if (getEvents() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getEvents());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getEvents(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getAccounts() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAccounts());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAccounts(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RolesByEventReqest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:roles", "RolesByEventReqest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bp_01");
        elemField.setXmlName(new javax.xml.namespace.QName("", "bp_01"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:relationships", "BPContent"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("events");
        elemField.setXmlName(new javax.xml.namespace.QName("", "events"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "event_id"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accounts");
        elemField.setXmlName(new javax.xml.namespace.QName("", "accounts"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:roles", ">RoleAccounts>item"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "item"));
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
