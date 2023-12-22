/**
 * RolesByEventResponceRelationshipsResponce.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package roles.NCI.com.ipakyulibank;

public class RolesByEventResponceRelationshipsResponce  implements java.io.Serializable {
    private java.lang.String bp_extnum;


    private java.lang.String bp_event;


    private java.lang.String status;

    private java.lang.String status_details;

    public RolesByEventResponceRelationshipsResponce() {
    }

    public RolesByEventResponceRelationshipsResponce(
           java.lang.String bp_extnum,
           java.lang.String bp_event,
           java.lang.String status,
           java.lang.String status_details) {
           this.bp_extnum = bp_extnum;
           this.bp_event = bp_event;
           this.status = status;
           this.status_details = status_details;
    }



    public java.lang.String getBp_extnum() {
        return bp_extnum;
    }


    public void setBp_extnum(java.lang.String bp_extnum) {
        this.bp_extnum = bp_extnum;
    }



    public java.lang.String getBp_event() {
        return bp_event;
    }



    public void setBp_event(java.lang.String bp_event) {
        this.bp_event = bp_event;
    }


    public java.lang.String getStatus() {
        return status;
    }



    public void setStatus(java.lang.String status) {
        this.status = status;
    }



    public java.lang.String getStatus_details() {
        return status_details;
    }


    public void setStatus_details(java.lang.String status_details) {
        this.status_details = status_details;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RolesByEventResponceRelationshipsResponce)) return false;
        RolesByEventResponceRelationshipsResponce other = (RolesByEventResponceRelationshipsResponce) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.bp_extnum==null && other.getBp_extnum()==null) || 
             (this.bp_extnum!=null &&
              this.bp_extnum.equals(other.getBp_extnum()))) &&
            ((this.bp_event==null && other.getBp_event()==null) || 
             (this.bp_event!=null &&
              this.bp_event.equals(other.getBp_event()))) &&
            ((this.status==null && other.getStatus()==null) || 
             (this.status!=null &&
              this.status.equals(other.getStatus()))) &&
            ((this.status_details==null && other.getStatus_details()==null) || 
             (this.status_details!=null &&
              this.status_details.equals(other.getStatus_details())));
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
        if (getBp_extnum() != null) {
            _hashCode += getBp_extnum().hashCode();
        }
        if (getBp_event() != null) {
            _hashCode += getBp_event().hashCode();
        }
        if (getStatus() != null) {
            _hashCode += getStatus().hashCode();
        }
        if (getStatus_details() != null) {
            _hashCode += getStatus_details().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RolesByEventResponceRelationshipsResponce.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:roles", ">RolesByEventResponce>RelationshipsResponce"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bp_extnum");
        elemField.setXmlName(new javax.xml.namespace.QName("", "bp_extnum"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bp_event");
        elemField.setXmlName(new javax.xml.namespace.QName("", "bp_event"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("", "status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status_details");
        elemField.setXmlName(new javax.xml.namespace.QName("", "status_details"));
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
