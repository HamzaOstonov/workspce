package com.is.customer_.local.reports;

import com.is.customer_.core.model.Customer;
import com.is.customer_.core.utils.CustomerUtils;
import com.is.customer_.core.utils.ReferenceDecoder;
import com.is.utils.CheckNull;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.zkoss.zul.Filedownload;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

public class WordOfficeReport extends OfficeReport {
    public WordOfficeReport(Map<String, Object> params) {
        super(params);
    }

    @Override
    public void downloadFile() {
        Customer businessPartner = (Customer) params.get("customer");
        byte[] bytes = getBytes(businessPartner);
        Filedownload.save(bytes, "application/msword", params.get("outfileName") + ".doc");
    }

    private byte[] getBytes(Customer customer) {
        byte[] bytes = null;
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(params.get("templatePath").toString());

            POIFSFileSystem fs = new POIFSFileSystem(fileInputStream);

            HWPFDocument doc = new HWPFDocument(fs);

            Range range = doc.getRange();

            replaceText("<ID>", customer.getLongId(), range);

            replaceText("<BRANCH>", customer.getBranch(), range);

            replaceText("<ID_CLIENT>", customer.getId(), range);

            replaceText("<NAME>", customer.getName(), range);

            replaceText("<CODE_COUNTRY>", customer.getCode_country(), range);

            replaceText("<NAME_CODE_COUNTRY>", ReferenceDecoder.getCountryName(customer.getCode_country()), range);

            replaceText("<CODE_TYPE>", customer.getCode_type(), range);

            // Code Type name
            replaceText("<NAME_CODE_TYPE>", "", range);

            replaceText("<CODE_RESIDENT>", customer.getCode_resident(), range);

            replaceText("<CODE_SUBJECT>", customer.getCode_subject(), range);

            replaceText("<SIGN_REGISTR>", String.valueOf(customer.getSign_registr()), range);

            replaceText("<DATE_OPEN>", CustomerUtils.dateToString(customer.getDate_open()), range);

            replaceText("<DATE_CLOSE>", CustomerUtils.dateToString(customer.getDate_close()), range);

            replaceText("<STATE>", customer.getState(), range);

            replaceText("<STATE_NAME>", ReferenceDecoder.getStateName(customer.getState()), range);

            replaceText("<BANKDATE>", "", range);

            replaceText("<KOD_ERR>", String.valueOf(customer.getKod_err()), range);

            replaceText("<FILE_NAME>", customer.getFile_name(), range);

            replaceText("<P_BIRTHDAY>", CustomerUtils.dateToString(customer.getP_birthday()), range);

            replaceText("<P_POST_ADDRESS>", customer.getP_post_address(), range);

            replaceText("<P_PASSPORT_TYPE>", customer.getP_passport_type(), range);

            replaceText("<P_PASSPORT_SERIAL>", customer.getP_passport_serial(), range);

            replaceText("<P_PASSPORT_NUMBER>", customer.getP_passport_number(), range);

            replaceText("<P_PASSPORT_PLACE_REGISTRATION>", customer.getP_passport_place_registration(), range);

            replaceText("<P_PASSPORT_DATE_REGISTRATION>", CustomerUtils.dateToString(
                    customer.getP_passport_date_registration()), range);

            replaceText("<P_CODE_TAX_ORG>", customer.getP_code_tax_org(), range);

            replaceText("<NAME_P_CODE_TAX_ORG>", ReferenceDecoder.getTaxOrgName(customer.getP_code_tax_org()), range);

            replaceText("<P_NUMBER_TAX_REGISTRATION>", customer.getP_number_tax_registration(), range);

            replaceText("<P_CODE_BANK>", customer.getP_code_bank(), range);

            replaceText("<P_CODE_CLASS_CREDIT>", customer.getP_code_class_credit(), range);

            replaceText("<P_CODE_CITIZENSHIP>", customer.getP_code_citizenship(), range);

            replaceText("<NAME_P_CODE_CITIZENSHIP>", ReferenceDecoder.getCountryName(customer.getP_code_citizenship()), range);

            replaceText("<P_BIRTH_PLACE>", customer.getP_birth_place(), range);

            replaceText("<P_CODE_CAPACITY>", customer.getP_code_capacity(), range);

            replaceText("<NAME_P_CODE_CAPACITY>", ReferenceDecoder.getCapacityName(customer.getP_code_capacity()), range);

            replaceText("<P_CAPACITY_STATUS_DATE>", CustomerUtils.dateToString(customer.getP_capacity_status_date()), range);

            replaceText("<P_CAPACITY_STATUS_PLACE>", customer.getP_capacity_status_place(), range);

            replaceText("<P_NUM_CERTIF_CAPACITY>", customer.getP_num_certif_capacity(), range);

            replaceText("<P_PHONE_HOME>", customer.getP_phone_home(), range);

            replaceText("<P_PHONE_MOBILE>", customer.getP_phone_mobile(), range);

            replaceText("<P_EMAIL_ADDRESS>", customer.getP_email_address(), range);

            replaceText("<P_PENSION_SERTIF_SERIAL>", customer.getP_pension_sertif_serial(), range);

            replaceText("<P_CODE_GENDER>", customer.getP_code_gender(), range);

            replaceText("<NAME_P_CODE_GENDER>", ReferenceDecoder.getGenderName(customer.getP_code_gender()), range);

            replaceText("<P_CODE_NATION>", customer.getP_code_nation(), range);

            replaceText("<NAME_P_CODE_NATION>", ReferenceDecoder.getNationName(customer.getP_code_nation()), range);

            replaceText("<P_CODE_BIRTH_REGION>", customer.getP_code_birth_region(), range);

            replaceText("<NAME_P_CODE_BIRTH_REGION>", ReferenceDecoder.getRegionName(customer.getP_code_birth_region()), range);

            replaceText("<P_CODE_BIRTH_DISTR>", customer.getP_code_birth_distr(), range);

            replaceText("<NAME_P_CODE_BIRTH_DISTR>", ReferenceDecoder.getDistrictName(
                    customer.getP_code_birth_region()
                    , customer.getP_code_adr_distr()), range);

            replaceText("<P_TYPE_DOCUMENT>", customer.getP_type_document(), range);

            replaceText("<NAME_P_TYPE_DOCUMENT>", ReferenceDecoder.getDocumentName(customer.getP_type_document()), range);

            replaceText("<P_PASSPORT_DATE_EXPIRATION>", CustomerUtils.dateToString(
                    customer.getP_passport_date_expiration()), range);

            replaceText("<P_CODE_ADR_REGION>", customer.getP_code_adr_region(), range);

            replaceText("<NAME_P_CODE_ADR_REGION>", ReferenceDecoder.getRegionName(customer.getP_code_adr_region()), range);

            replaceText("<P_CODE_ADR_DISTR>", customer.getP_code_adr_distr(), range);

            replaceText("<NAME_P_CODE_ADR_DISTR>", ReferenceDecoder.getDistrictName(
                    customer.getP_code_adr_region(), customer.getP_code_adr_distr()), range);

            replaceText("<P_INPS>", customer.getP_inps(), range);

            replaceText("<P_CODE_BANK_INPS>","",range);

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            doc.write(out);
            bytes = out.toByteArray();
            out.close();
        } catch (Exception e) {
            logger.error(CheckNull.getPstr(e));
        } finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                logger.error(CheckNull.getPstr(e));
            }
        }

        return bytes;
    }

    public static void replaceText(String arg0, String arg1, Range range) {
        if (arg1 != null)
            range.replaceText(arg0, arg1);
        else
            range.replaceText(arg0,"" +
                    "");
    }

    public static void replaceText(String arg0, Long arg1, Range range) {
        if (arg1 != null)
            range.replaceText(arg0, String.valueOf(arg1));
    }
}

