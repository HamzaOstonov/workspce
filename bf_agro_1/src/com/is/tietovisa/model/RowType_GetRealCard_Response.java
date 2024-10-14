
package com.is.tietovisa.model;

public class RowType_GetRealCard_Response  implements java.io.Serializable {
    private java.lang.String RCARD;

    public RowType_GetRealCard_Response() {
    }

    public RowType_GetRealCard_Response(
           java.lang.String RCARD) {
           this.RCARD = RCARD;
    }


    /**
     * Gets the RCARD value for this RowType_GetRealCard_Response.
     * 
     * @return RCARD
     */
    public java.lang.String getRCARD() {
        return RCARD;
    }


    /**
     * Sets the RCARD value for this RowType_GetRealCard_Response.
     * 
     * @param RCARD
     */
    public void setRCARD(java.lang.String RCARD) {
        this.RCARD = RCARD;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RowType_GetRealCard_Response)) return false;
        RowType_GetRealCard_Response other = (RowType_GetRealCard_Response) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.RCARD==null && other.getRCARD()==null) || 
             (this.RCARD!=null &&
              this.RCARD.equals(other.getRCARD())));
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
        if (getRCARD() != null) {
            _hashCode += getRCARD().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }
}
