/**
 * RelationshipsResponceRelationshipsResponce.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package relationships.NCI.com.ipakyulibank;

public class RelationshipsResponceRelationshipsResponce  implements java.io.Serializable {

    private java.lang.String bp_extnum_1;

    private java.lang.String bp_extnum_2;

    private java.lang.String bp_rel_type;

    private java.lang.String status;

    private java.lang.String status_details;

    public RelationshipsResponceRelationshipsResponce() {
    }

    public RelationshipsResponceRelationshipsResponce(
           java.lang.String bp_extnum_1,
           java.lang.String bp_extnum_2,
           java.lang.String bp_rel_type,
           java.lang.String status,
           java.lang.String status_details) {
           this.bp_extnum_1 = bp_extnum_1;
           this.bp_extnum_2 = bp_extnum_2;
           this.bp_rel_type = bp_rel_type;
           this.status = status;
           this.status_details = status_details;
    }


    public java.lang.String getBp_extnum_1() {
        return bp_extnum_1;
    }



    public void setBp_extnum_1(java.lang.String bp_extnum_1) {
        this.bp_extnum_1 = bp_extnum_1;
    }



    public java.lang.String getBp_extnum_2() {
        return bp_extnum_2;
    }



    public void setBp_extnum_2(java.lang.String bp_extnum_2) {
        this.bp_extnum_2 = bp_extnum_2;
    }


    public java.lang.String getBp_rel_type() {
        return bp_rel_type;
    }



    public void setBp_rel_type(java.lang.String bp_rel_type) {
        this.bp_rel_type = bp_rel_type;
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
        if (!(obj instanceof RelationshipsResponceRelationshipsResponce)) return false;
        RelationshipsResponceRelationshipsResponce other = (RelationshipsResponceRelationshipsResponce) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.bp_extnum_1==null && other.getBp_extnum_1()==null) || 
             (this.bp_extnum_1!=null &&
              this.bp_extnum_1.equals(other.getBp_extnum_1()))) &&
            ((this.bp_extnum_2==null && other.getBp_extnum_2()==null) || 
             (this.bp_extnum_2!=null &&
              this.bp_extnum_2.equals(other.getBp_extnum_2()))) &&
            ((this.bp_rel_type==null && other.getBp_rel_type()==null) || 
             (this.bp_rel_type!=null &&
              this.bp_rel_type.equals(other.getBp_rel_type()))) &&
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
        if (getBp_extnum_1() != null) {
            _hashCode += getBp_extnum_1().hashCode();
        }
        if (getBp_extnum_2() != null) {
            _hashCode += getBp_extnum_2().hashCode();
        }
        if (getBp_rel_type() != null) {
            _hashCode += getBp_rel_type().hashCode();
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
        new org.apache.axis.description.TypeDesc(RelationshipsResponceRelationshipsResponce.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:relationships", ">RelationshipsResponce>RelationshipsResponce"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bp_extnum_1");
        elemField.setXmlName(new javax.xml.namespace.QName("", "bp_extnum_1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bp_extnum_2");
        elemField.setXmlName(new javax.xml.namespace.QName("", "bp_extnum_2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bp_rel_type");
        elemField.setXmlName(new javax.xml.namespace.QName("", "bp_rel_type"));
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
