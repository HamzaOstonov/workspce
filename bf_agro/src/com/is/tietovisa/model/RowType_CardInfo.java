package com.is.tietovisa.model;

public class RowType_CardInfo  implements java.io.Serializable {
    private RowType_CardInfo_LogicalCard logicalCard;

    private RowType_CardInfo_PhysicalCard physicalCard;

    private RowType_CardInfo_EMV_Data EMV_Data;

    private RowType_AddServData[] addServInfo;

    private RowType_CardInfo_TSM_Data TSM_Data;

    public RowType_CardInfo() {
    }

    public RowType_CardInfo(
           RowType_CardInfo_LogicalCard logicalCard,
           RowType_CardInfo_PhysicalCard physicalCard,
           RowType_CardInfo_EMV_Data EMV_Data,
           RowType_AddServData[] addServInfo,
           RowType_CardInfo_TSM_Data TSM_Data) {
           this.logicalCard = logicalCard;
           this.physicalCard = physicalCard;
           this.EMV_Data = EMV_Data;
           this.addServInfo = addServInfo;
           this.TSM_Data = TSM_Data;
    }


    /**
     * Gets the logicalCard value for this RowType_CardInfo.
     * 
     * @return logicalCard
     */
    public RowType_CardInfo_LogicalCard getLogicalCard() {
        return logicalCard;
    }


    /**
     * Sets the logicalCard value for this RowType_CardInfo.
     * 
     * @param logicalCard
     */
    public void setLogicalCard(RowType_CardInfo_LogicalCard logicalCard) {
        this.logicalCard = logicalCard;
    }


    /**
     * Gets the physicalCard value for this RowType_CardInfo.
     * 
     * @return physicalCard
     */
    public RowType_CardInfo_PhysicalCard getPhysicalCard() {
        return physicalCard;
    }


    /**
     * Sets the physicalCard value for this RowType_CardInfo.
     * 
     * @param physicalCard
     */
    public void setPhysicalCard(RowType_CardInfo_PhysicalCard physicalCard) {
        this.physicalCard = physicalCard;
    }


    /**
     * Gets the EMV_Data value for this RowType_CardInfo.
     * 
     * @return EMV_Data
     */
    public RowType_CardInfo_EMV_Data getEMV_Data() {
        return EMV_Data;
    }


    /**
     * Sets the EMV_Data value for this RowType_CardInfo.
     * 
     * @param EMV_Data
     */
    public void setEMV_Data(RowType_CardInfo_EMV_Data EMV_Data) {
        this.EMV_Data = EMV_Data;
    }


    /**
     * Gets the addServInfo value for this RowType_CardInfo.
     * 
     * @return addServInfo
     */
    public RowType_AddServData[] getAddServInfo() {
        return addServInfo;
    }


    /**
     * Sets the addServInfo value for this RowType_CardInfo.
     * 
     * @param addServInfo
     */
    public void setAddServInfo(RowType_AddServData[] addServInfo) {
        this.addServInfo = addServInfo;
    }


    /**
     * Gets the TSM_Data value for this RowType_CardInfo.
     * 
     * @return TSM_Data
     */
    public RowType_CardInfo_TSM_Data getTSM_Data() {
        return TSM_Data;
    }


    /**
     * Sets the TSM_Data value for this RowType_CardInfo.
     * 
     * @param TSM_Data
     */
    public void setTSM_Data(RowType_CardInfo_TSM_Data TSM_Data) {
        this.TSM_Data = TSM_Data;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RowType_CardInfo)) return false;
        RowType_CardInfo other = (RowType_CardInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.logicalCard==null && other.getLogicalCard()==null) || 
             (this.logicalCard!=null &&
              this.logicalCard.equals(other.getLogicalCard()))) &&
            ((this.physicalCard==null && other.getPhysicalCard()==null) || 
             (this.physicalCard!=null &&
              this.physicalCard.equals(other.getPhysicalCard()))) &&
            ((this.EMV_Data==null && other.getEMV_Data()==null) || 
             (this.EMV_Data!=null &&
              this.EMV_Data.equals(other.getEMV_Data()))) &&
            ((this.addServInfo==null && other.getAddServInfo()==null) || 
             (this.addServInfo!=null &&
              java.util.Arrays.equals(this.addServInfo, other.getAddServInfo()))) &&
            ((this.TSM_Data==null && other.getTSM_Data()==null) || 
             (this.TSM_Data!=null &&
              this.TSM_Data.equals(other.getTSM_Data())));
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
        if (getLogicalCard() != null) {
            _hashCode += getLogicalCard().hashCode();
        }
        if (getPhysicalCard() != null) {
            _hashCode += getPhysicalCard().hashCode();
        }
        if (getEMV_Data() != null) {
            _hashCode += getEMV_Data().hashCode();
        }
        if (getAddServInfo() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAddServInfo());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAddServInfo(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getTSM_Data() != null) {
            _hashCode += getTSM_Data().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

   
}
