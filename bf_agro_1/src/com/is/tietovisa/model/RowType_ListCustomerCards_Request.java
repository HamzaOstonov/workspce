package com.is.tietovisa.model;

public class RowType_ListCustomerCards_Request  implements java.io.Serializable {
    private java.lang.String BANK_C;

    private java.lang.String CLIENT;

    private java.lang.String CLIENT_B;

    public RowType_ListCustomerCards_Request() {
    }

    public RowType_ListCustomerCards_Request(
           java.lang.String BANK_C,
           java.lang.String CLIENT,
           java.lang.String CLIENT_B) {
           this.BANK_C = BANK_C;
           this.CLIENT = CLIENT;
           this.CLIENT_B = CLIENT_B;
    }


    /**
     * Gets the BANK_C value for this RowType_ListCustomerCards_Request.
     * 
     * @return BANK_C
     */
    public java.lang.String getBANK_C() {
        return BANK_C;
    }


    /**
     * Sets the BANK_C value for this RowType_ListCustomerCards_Request.
     * 
     * @param BANK_C
     */
    public void setBANK_C(java.lang.String BANK_C) {
        this.BANK_C = BANK_C;
    }


    /**
     * Gets the CLIENT value for this RowType_ListCustomerCards_Request.
     * 
     * @return CLIENT
     */
    public java.lang.String getCLIENT() {
        return CLIENT;
    }


    /**
     * Sets the CLIENT value for this RowType_ListCustomerCards_Request.
     * 
     * @param CLIENT
     */
    public void setCLIENT(java.lang.String CLIENT) {
        this.CLIENT = CLIENT;
    }


    /**
     * Gets the CLIENT_B value for this RowType_ListCustomerCards_Request.
     * 
     * @return CLIENT_B
     */
    public java.lang.String getCLIENT_B() {
        return CLIENT_B;
    }


    /**
     * Sets the CLIENT_B value for this RowType_ListCustomerCards_Request.
     * 
     * @param CLIENT_B
     */
    public void setCLIENT_B(java.lang.String CLIENT_B) {
        this.CLIENT_B = CLIENT_B;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RowType_ListCustomerCards_Request)) return false;
        RowType_ListCustomerCards_Request other = (RowType_ListCustomerCards_Request) obj;
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
            ((this.CLIENT==null && other.getCLIENT()==null) || 
             (this.CLIENT!=null &&
              this.CLIENT.equals(other.getCLIENT()))) &&
            ((this.CLIENT_B==null && other.getCLIENT_B()==null) || 
             (this.CLIENT_B!=null &&
              this.CLIENT_B.equals(other.getCLIENT_B())));
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
        if (getCLIENT() != null) {
            _hashCode += getCLIENT().hashCode();
        }
        if (getCLIENT_B() != null) {
            _hashCode += getCLIENT_B().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

  
}
