/**
 * BPContent.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package relationships.NCI.com.ipakyulibank;

public class BPContent  implements java.io.Serializable {

    private java.lang.String bp_type;

    private java.lang.String bp_extnum;

    private java.lang.String branch;

    private java.lang.String profile_author;

    private relationships.NCI.com.ipakyulibank.IdentNums[] bp_ident_nums;

    public BPContent() {
    }

    public BPContent(
           java.lang.String bp_type,
           java.lang.String bp_extnum,
           java.lang.String branch,
           java.lang.String profile_author,
           relationships.NCI.com.ipakyulibank.IdentNums[] bp_ident_nums) {
           this.bp_type = bp_type;
           this.bp_extnum = bp_extnum;
           this.branch = branch;
           this.profile_author = profile_author;
           this.bp_ident_nums = bp_ident_nums;
    }


    public java.lang.String getBp_type() {
        return bp_type;
    }



    public void setBp_type(java.lang.String bp_type) {
        this.bp_type = bp_type;
    }



    public java.lang.String getBp_extnum() {
        return bp_extnum;
    }


    public void setBp_extnum(java.lang.String bp_extnum) {
        this.bp_extnum = bp_extnum;
    }


    public java.lang.String getBranch() {
        return branch;
    }


    public void setBranch(java.lang.String branch) {
        this.branch = branch;
    }


    public java.lang.String getProfile_author() {
        return profile_author;
    }


    public void setProfile_author(java.lang.String profile_author) {
        this.profile_author = profile_author;
    }


    public relationships.NCI.com.ipakyulibank.IdentNums[] getBp_ident_nums() {
        return bp_ident_nums;
    }

    public void setBp_ident_nums(relationships.NCI.com.ipakyulibank.IdentNums[] bp_ident_nums) {
        this.bp_ident_nums = bp_ident_nums;
    }

    public relationships.NCI.com.ipakyulibank.IdentNums getBp_ident_nums(int i) {
        return this.bp_ident_nums[i];
    }

    public void setBp_ident_nums(int i, relationships.NCI.com.ipakyulibank.IdentNums _value) {
        this.bp_ident_nums[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BPContent)) return false;
        BPContent other = (BPContent) obj;
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
            ((this.profile_author==null && other.getProfile_author()==null) || 
             (this.profile_author!=null &&
              this.profile_author.equals(other.getProfile_author()))) &&
            ((this.bp_ident_nums==null && other.getBp_ident_nums()==null) || 
             (this.bp_ident_nums!=null &&
              java.util.Arrays.equals(this.bp_ident_nums, other.getBp_ident_nums())));
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
        if (getProfile_author() != null) {
            _hashCode += getProfile_author().hashCode();
        }
        if (getBp_ident_nums() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getBp_ident_nums());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getBp_ident_nums(), i);
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
        new org.apache.axis.description.TypeDesc(BPContent.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:relationships", "BPContent"));
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
        elemField.setFieldName("profile_author");
        elemField.setXmlName(new javax.xml.namespace.QName("", "profile_author"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bp_ident_nums");
        elemField.setXmlName(new javax.xml.namespace.QName("", "bp_ident_nums"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:relationships", "IdentNums"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
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
