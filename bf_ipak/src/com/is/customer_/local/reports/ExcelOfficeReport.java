package com.is.customer_.local.reports;

import com.is.customer_.core.model.Customer;
import com.is.customer_.core.utils.CustomerUtils;
import com.is.customer_.core.utils.ReferenceDecoder;
import com.is.utils.CheckNull;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.zkoss.zul.Filedownload;
/*import sun.misc.IOUtils;*/

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by root on 05.05.2017.
 * 12:08
 */
public class ExcelOfficeReport extends OfficeReport {
    private Logger logger = Logger.getLogger(ExcelOfficeReport.class);
    public ExcelOfficeReport(Map<String,Object> params) {
        super(params);
    }

    @Override
    public void downloadFile() {
        Customer businessPartner = (Customer) params.get("customer");
        byte[] bytes = getBytes(businessPartner);
        Filedownload.save(bytes,"application/vnd.ms-excel",params.get("outfileName") + ".xls");
    }

    private byte[] getBytes(Customer businessPartner) {
        byte[] bytes = null;
        FileInputStream fileInputStream = null;
        POIFSFileSystem fs = null;
        HSSFWorkbook workbook = null;
        try{
            fileInputStream = new FileInputStream(params.get("templatePath").toString());
            fs = new POIFSFileSystem(fileInputStream);
            workbook = new HSSFWorkbook(fs);
            HSSFSheet sheet = workbook.getSheetAt(0);

            int rowNum = sheet.getLastRowNum() + 1;
            for (int i = 0; i < rowNum; i++){
                Row row = sheet.getRow(i);
                if (row == null)
                    continue;

                Cell cell = row.getCell(3);

                if (cell==null)
                    continue;

                fillCellValue(cell,businessPartner);
            }
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(out);
            bytes = out.toByteArray();
            out.close();
            workbook.close();
        }
        catch (Exception e){
            logger.error(CheckNull.getPstr(e));
        }
        finally {
            org.apache.commons.io.IOUtils.closeQuietly(fileInputStream);
        }
        return bytes;
    }

    private void fillCellValue(Cell cell, Customer businessPartner) throws SQLException {
        String cellValue = cell.getStringCellValue();
        if (cellValue.equalsIgnoreCase("<id>")) {
            cell.setCellValue(businessPartner.getLongId());
            return;
        }
        if (cellValue.equalsIgnoreCase("<branch>")) {
            cell.setCellValue(businessPartner.getBranch());
            return;
        }
        if (cellValue.equalsIgnoreCase("<id_client>"))
            cell.setCellValue(businessPartner.getId());
        if (cellValue.equalsIgnoreCase("<name>"))
            cell.setCellValue(businessPartner.getName());
        if (cellValue.equalsIgnoreCase("<code_country>"))
            cell.setCellValue(businessPartner.getCode_country());
        if (cellValue.equalsIgnoreCase("<name_code_country>"))
            cell.setCellValue(ReferenceDecoder.getCountryName(businessPartner.getCode_country()));
        if (cellValue.equalsIgnoreCase("<code_type>"))
            cell.setCellValue(businessPartner.getCode_type());
        if (cellValue.equalsIgnoreCase("<code_resident>"))
            cell.setCellValue(businessPartner.getCode_resident());
        if (cellValue.equalsIgnoreCase("<code_subject>"))
            cell.setCellValue(businessPartner.getCode_subject());
        if (cellValue.equalsIgnoreCase("<sign_registr>"))
            cell.setCellValue(businessPartner.getSign_registr());
        if (cellValue.equalsIgnoreCase("<date_open>"))
            cell.setCellValue(CustomerUtils.dateToString(businessPartner.getDate_open()));
        if (cellValue.equalsIgnoreCase("<date_close>"))
            cell.setCellValue(CustomerUtils.dateToString(businessPartner.getDate_close()));
        if (cellValue.equalsIgnoreCase("<state>"))
            cell.setCellValue(businessPartner.getState());
        if (cellValue.equalsIgnoreCase("<state_name>"))
            cell.setCellValue(ReferenceDecoder.getStateName(businessPartner.getState()));
        if (cellValue.equalsIgnoreCase("<bankdate>"))
            cell.setCellValue("");
        if (cellValue.equalsIgnoreCase("<KOD_ERR>"))
            cell.setCellValue(businessPartner.getKod_err());
        if (cellValue.equalsIgnoreCase("<FILE_NAME>"))
            cell.setCellValue(businessPartner.getFile_name());
        if (cellValue.equalsIgnoreCase("<P_BIRTHDAY>"))
            cell.setCellValue(CustomerUtils.dateToString(businessPartner.getP_birthday()));
        if (cellValue.equalsIgnoreCase("<P_POST_ADDRESS>"))
            cell.setCellValue(businessPartner.getP_post_address());
        if (cellValue.equalsIgnoreCase("<P_PASSPORT_TYPE>"))
            cell.setCellValue(businessPartner.getP_passport_type());
        if (cellValue.equalsIgnoreCase("<P_PASSPORT_SERIAL>"))
            cell.setCellValue(businessPartner.getP_passport_serial());
        if (cellValue.equalsIgnoreCase("<P_PASSPORT_NUMBER>"))
            cell.setCellValue(businessPartner.getP_passport_number());
        if (cellValue.equalsIgnoreCase("<P_PASSPORT_PLACE_REGISTRATION>"))
            cell.setCellValue(businessPartner.getP_passport_place_registration());
        if (cellValue.equalsIgnoreCase("<P_PASSPORT_DATE_REGISTRATION>"))
            cell.setCellValue(CustomerUtils.dateToString(businessPartner.getP_passport_date_registration()));
        if (cellValue.equalsIgnoreCase("<P_CODE_TAX_ORG>"))
            cell.setCellValue(businessPartner.getP_code_tax_org());
        if (cellValue.equalsIgnoreCase("<NAME_P_CODE_TAX_ORG>"))
            cell.setCellValue(ReferenceDecoder.getTaxOrgName(businessPartner.getP_code_tax_org()));
        if (cellValue.equalsIgnoreCase("<P_NUMBER_TAX_REGISTRATION>"))
            cell.setCellValue(businessPartner.getP_number_tax_registration());
        if (cellValue.equalsIgnoreCase("<P_CODE_BANK>"))
            cell.setCellValue(businessPartner.getP_code_bank());
        if (cellValue.equalsIgnoreCase("<P_CODE_CLASS_CREDIT>"))
            cell.setCellValue(businessPartner.getP_code_class_credit());
        if (cellValue.equalsIgnoreCase("<P_CODE_CITIZENSHIP>"))
            cell.setCellValue(businessPartner.getP_code_citizenship());
        if (cellValue.equalsIgnoreCase("<NAME_P_CODE_CITIZENSHIP>"))
            cell.setCellValue(ReferenceDecoder.getCountryName(businessPartner.getP_code_citizenship()));
        if (cellValue.equalsIgnoreCase("<P_BIRTH_PLACE>"))
            cell.setCellValue(businessPartner.getP_birth_place());
        if (cellValue.equalsIgnoreCase("<P_CODE_CAPACITY>"))
            cell.setCellValue(businessPartner.getP_code_capacity());
        if (cellValue.equalsIgnoreCase("<NAME_P_CODE_CAPACITY>"))
            cell.setCellValue(ReferenceDecoder.getCapacityName(businessPartner.getP_code_capacity()));
        if (cellValue.equalsIgnoreCase("<P_CAPACITY_STATUS_DATE>"))
            cell.setCellValue(CustomerUtils.dateToString(businessPartner.getP_capacity_status_date()));
        if (cellValue.equalsIgnoreCase("<P_CAPACITY_STATUS_PLACE>"))
            cell.setCellValue(businessPartner.getP_capacity_status_place());
        if (cellValue.equalsIgnoreCase("<P_NUM_CERTIF_CAPACITY>"))
            cell.setCellValue(businessPartner.getP_num_certif_capacity());
        if (cellValue.equalsIgnoreCase("<P_PHONE_HOME>"))
            cell.setCellValue(businessPartner.getP_phone_home());
        if (cellValue.equalsIgnoreCase("<P_PHONE_MOBILE>"))
            cell.setCellValue(businessPartner.getP_phone_mobile());
        if (cellValue.equalsIgnoreCase("<P_EMAIL_ADDRESS>"))
            cell.setCellValue(businessPartner.getP_email_address());
        if (cellValue.equalsIgnoreCase("<P_PENSION_SERTIF_SERIAL>"))
            cell.setCellValue(businessPartner.getP_pension_sertif_serial());
        if (cellValue.equalsIgnoreCase("<P_CODE_GENDER>"))
            cell.setCellValue(businessPartner.getP_code_gender());
        if (cellValue.equalsIgnoreCase("<NAME_P_CODE_GENDER>"))
            cell.setCellValue(ReferenceDecoder.getGenderName(businessPartner.getP_code_gender()));
        if (cellValue.equalsIgnoreCase("<P_CODE_NATION>"))
            cell.setCellValue(businessPartner.getP_code_nation());
        if (cellValue.equalsIgnoreCase("<NAME_P_CODE_NATION>"))
            cell.setCellValue(ReferenceDecoder.getNationName(businessPartner.getP_code_nation()));
        if (cellValue.equalsIgnoreCase("<P_CODE_BIRTH_REGION>"))
            cell.setCellValue(businessPartner.getP_code_birth_region());
        if (cellValue.equalsIgnoreCase("<NAME_P_CODE_BIRTH_REGION>"))
            cell.setCellValue(ReferenceDecoder.getRegionName(businessPartner.getP_code_birth_region()));
        if (cellValue.equalsIgnoreCase("<P_CODE_BIRTH_DISTR>"))
            cell.setCellValue(businessPartner.getP_code_birth_distr());
        if (cellValue.equalsIgnoreCase("<NAME_P_CODE_BIRTH_DISTR>"))
            cell.setCellValue(ReferenceDecoder.getDistrictName(businessPartner.getP_code_birth_region(),businessPartner.getP_code_birth_distr()));
        if (cellValue.equalsIgnoreCase("<P_TYPE_DOCUMENT>"))
            cell.setCellValue(businessPartner.getP_type_document());
        if (cellValue.equalsIgnoreCase("<NAME_P_TYPE_DOCUMENT>"))
            cell.setCellValue(ReferenceDecoder.getDocumentName(businessPartner.getP_type_document()));
        if (cellValue.equalsIgnoreCase("<P_PASSPORT_DATE_EXPIRATION>"))
            cell.setCellValue(CustomerUtils.dateToString(businessPartner.getP_passport_date_expiration()));
        if (cellValue.equalsIgnoreCase("<P_CODE_ADR_REGION>"))
            cell.setCellValue(businessPartner.getP_code_adr_region());
        if (cellValue.equalsIgnoreCase("<NAME_P_CODE_ADR_REGION>"))
            cell.setCellValue(ReferenceDecoder.getRegionName(businessPartner.getP_code_adr_region()));
        if (cellValue.equalsIgnoreCase("<P_CODE_ADR_DISTR>"))
            cell.setCellValue(businessPartner.getP_code_adr_distr());
        if (cellValue.equalsIgnoreCase("<NAME_P_CODE_ADR_DISTR>"))
            cell.setCellValue(ReferenceDecoder.getDistrictName(businessPartner.getP_code_adr_region(),
                    businessPartner.getP_code_adr_distr()));
        if (cellValue.equalsIgnoreCase("<P_INPS>"))
            cell.setCellValue(businessPartner.getP_inps());
        if (cellValue.equalsIgnoreCase("<P_CODE_BANK_INPS>"))
            cell.setCellValue("");
    }
}
