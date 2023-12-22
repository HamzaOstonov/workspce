package com.is.customer;

import java.util.Date;

/**
 * Created by root on 09.06.2017.
 * 11:23
 */
public class InternalControl {
    private String riskLevel;
    private Date initDate;
    private String reason;
    private Date validDateFrom;
    private Date validDateTo;
    private Date risk_date;
    private String s_notes;
    private String p_notes;

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public Date getInitDate() {
        return initDate;
    }

    public void setInitDate(Date initDate) {
        this.initDate = initDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getValidDateFrom() {
        return validDateFrom;
    }

    public void setValidDateFrom(Date validDateFrom) {
        this.validDateFrom = validDateFrom;
    }

    public Date getValidDateTo() {
        return validDateTo;
    }

    public void setValidDateTo(Date validDateTo) {
        this.validDateTo = validDateTo;
    }

    public String getS_notes() {
        return s_notes;
    }

    public void setS_notes(String s_notes) {
        this.s_notes = s_notes;
    }

    public String getP_notes() {
        return p_notes;
    }

    public void setP_notes(String p_notes) {
        this.p_notes = p_notes;
    }

    public Date getRisk_date() {
        return risk_date;
    }

    public void setRisk_date(Date risk_date) {
        this.risk_date = risk_date;
    }

    @Override
    public String toString() {
        return "InternalControl{" +
                "riskLevel='" + riskLevel + '\'' +
                ", initDate=" + initDate +
                ", reason='" + reason + '\'' +
                ", validDateFrom=" + validDateFrom +
                ", validDateTo=" + validDateTo +
                ", risk_date=" + risk_date +
                ", s_notes='" + s_notes + '\'' +
                ", p_notes='" + p_notes + '\'' +
                '}';
    }
}
