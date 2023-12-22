package com.is.customer_.local.reports;

import java.util.Map;

public class OfficeReportBuilder {
    private Map<String, Object> params;
    private REPORT_ENUM report_type;

    public OfficeReportBuilder setParams(Map<String, Object> params) {
        this.params = params;
        return this;
    }

    public OfficeReportBuilder setReport_type(REPORT_ENUM report_type) {
        this.report_type = report_type;
        return this;
    }

    public OfficeReport createOfficeReport() throws InstantiationException {
        switch (report_type){
            case WORD:
                return new WordOfficeReport(params);
            case EXCEL:
                return  new ExcelOfficeReport(params);
        }
        //return new OfficeReport(templatePath, outfilePath, params, report_type);
        throw new InstantiationException();
    }
}