package com.is.customer_.local.reports;

import org.apache.log4j.Logger;

import java.util.Map;

/**
 * Created by root on 05.05.2017.
 * 12:02
 */
public abstract class OfficeReport {
    protected Logger logger = Logger.getLogger(OfficeReport.class);
    protected Map<String,Object> params;

    public OfficeReport(Map<String, Object> params) {
        this.params = params;
    }

    public abstract void downloadFile();
}
