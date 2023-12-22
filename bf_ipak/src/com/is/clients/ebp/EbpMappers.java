package com.is.clients.ebp;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.is.ISLogger;
import com.is.base.utils.DbUtils;
import com.is.client_personmap.PersonMapUtil;
import com.is.client_personmap.model.FounderCapital;
import com.is.client_personmap.model.LegalEntity;
import com.is.client_personmap.model.Person;
import com.is.client_personmap.model.PersonMap;
import com.is.clients.ebp.models.individualdetails.IndividualDetails;
import com.is.clients.ebp.models.legalentitydetails.Client_founders;
import com.is.clients.ebp.models.legalentitydetails.LegalEntityDetails;
import com.is.clients.models.ClientJ;
import com.is.utils.CheckNull;

public class EbpMappers {
    private final static Logger logger = Logger.getLogger(EbpMappers.class);

    private static SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");

    public static ClientJ mapLegalEntityDetails(LegalEntityDetails entityDetails) {
        if (entityDetails == null) {
            return null;
        }
        ClientJ client = new ClientJ();


        if (entityDetails.getClient() != null) {
            client.setJ_number_tax_registration(entityDetails.getClient().getTin());
            client.setCode_subject(entityDetails.getClient().getSubject_type());
            client.setCode_subject("J");
            client.setCode_resident(entityDetails.getClient().getResidency_code());
            client.setCode_country(entityDetails.getClient().getCitizenship_code());
        }
        if (entityDetails.getClient_name() != null) {
            client.setName(entityDetails.getClient_name().getName());
            client.setJ_short_name(entityDetails.getClient_name().getName_short());
        }
        if (entityDetails.getClient_address() != null) {
            client.setJ_region(entityDetails.getClient_address().getAddress_region());
            client.setJ_distr(entityDetails.getClient_address().getAddress_subregion());
            client.setJ_soato(entityDetails.getClient_address().getAddress_code());
            client.setJ_post_address(entityDetails.getClient_address().getAddress());
            client.setJ_email(entityDetails.getClient_address().getEmail());
            client.setJ_phone(entityDetails.getClient_address().getPhone());
            client.setJ_fax(entityDetails.getClient_address().getFax());
        }
        if (entityDetails.getClient_registration() != null) {
            client.setJ_code_tax_org(entityDetails.getClient_registration().getTax_organization_code());
            client.setJ_number_registration_doc(entityDetails.getClient_registration().getRegistration_document());
            client.setJ_place_regist_name(entityDetails.getClient_registration().getRegistration_issuer());
            try {
                client.setJ_date_registration(df.parse(entityDetails.getClient_registration().getRegistration_date()));
            } catch (ParseException e) {
            }
        }
        if (entityDetails.getClient_static() != null) {
            client.setCode_form(entityDetails.getClient_static().getProperty_form());
            client.setJ_opf(entityDetails.getClient_static().getLegal_form());
            client.setJ_code_sector(entityDetails.getClient_static().getOked());
            //ISLogger.getLogger().error("****************************  mapLegalEntityDetails entityDetails.getClient_static().getOked() = " + entityDetails.getClient_static().getOked());
            //ISLogger.getLogger().error("****************************  mapLegalEntityDetails code_sector = " + client.getJ_code_sector());

            client.setJ_okpo(entityDetails.getClient_static().getJuridical_form());
            client.setJ_small_business(entityDetails.getClient_static().getBusiness_sign());
            client.setJ_inn_head_organization(entityDetails.getClient_static().getHeader_tin());
        }
        if (entityDetails.getClient_passport() != null) {
            client.setJ_director_address(entityDetails.getClient_passport().getAddress_director());
            client.setJ_director_birth_place(entityDetails.getClient_passport().getBirth_place_director());
            client.setJ_director_code_citizenship(entityDetails.getClient_passport().getCitizen_director());
            client.setJ_director_name(entityDetails.getClient_passport().getName_director());
            try {
                client.setJ_director_birthday(df.parse(entityDetails.getClient_passport().getBirth_date_director()));
                client.setJ_director_passp_date_end(df.parse(entityDetails.getClient_passport().getDoc_date_expire_director()));
                client.setJ_director_passp_date_reg(df.parse(entityDetails.getClient_passport().getDoc_date_issue_director()));
            } catch (ParseException e) {

            }
            client.setJ_director_passp_number(entityDetails.getClient_passport().getDoc_number_director());
            client.setJ_director_passp_place_reg(entityDetails.getClient_passport().getDoc_issuer_director());
            client.setJ_director_passp_serial(entityDetails.getClient_passport().getDoc_series_director());
            client.setJ_director_type_document(entityDetails.getClient_passport().getDoc_type_director());
            client.getDirector().setPost_address(entityDetails.getClient_passport().getAddress_director());
            client.getDirector().setBirth_place(entityDetails.getClient_passport().getBirth_place_director());
            client.getDirector().setCode_citizenship(entityDetails.getClient_passport().getCitizen_director());
            client.getDirector().setName(entityDetails.getClient_passport().getName_director());
            client.getDirector().setBirthday(client.getJ_director_birthday());
            client.getDirector().setPassport_date_expiration(client.getJ_director_passp_date_end());
            client.getDirector().setPassport_date_registration(client.getJ_director_passp_date_reg());
            client.getDirector().setPassport_number(entityDetails.getClient_passport().getDoc_number_director());
            client.getDirector().setPassport_place_registration(entityDetails.getClient_passport().getDoc_issuer_director());
            client.getDirector().setPassport_serial(entityDetails.getClient_passport().getDoc_series_director());
            client.getDirector().setType_document(entityDetails.getClient_passport().getDoc_type_director());
        }
        if (entityDetails.getClient_accountant() != null) {
            client.setJ_accountant_address(entityDetails.getClient_accountant().getAddress_accountant());

            client.setJ_accountant_birth_place(entityDetails.getClient_accountant().getBirth_place_accountant());
            client.setJ_accountant_code_citizenship(entityDetails.getClient_accountant().getCitizen_accountant());
            client.setJ_chief_accounter_name(entityDetails.getClient_accountant().getName_accountant());
            try {
                client.setJ_accountant_birthday(df.parse(entityDetails.getClient_accountant().getBirth_date_accountant()));
                client.setJ_accountant_passp_date_end(df.parse(entityDetails.getClient_accountant().getDoc_date_expire_accountant()));
                client.setJ_accountant_passp_date_reg(df.parse(entityDetails.getClient_accountant().getDoc_date_issue_accountant()));
            } catch (ParseException e) {
            }
            client.setJ_accountant_passp_number(entityDetails.getClient_accountant().getDoc_number_accountant());
            client.setJ_accountant_passp_place_reg(entityDetails.getClient_accountant().getDoc_issuer_accountant());
            client.setJ_accountant_passp_serial(entityDetails.getClient_accountant().getDoc_series_accountant());
            client.setJ_accountant_type_document(entityDetails.getClient_accountant().getDoc_type_accountant());

            client.getAccountant().setPost_address(entityDetails.getClient_accountant().getAddress_accountant());
            client.getAccountant().setBirth_place(entityDetails.getClient_accountant().getBirth_place_accountant());
            client.getAccountant().setCode_citizenship(entityDetails.getClient_accountant().getCitizen_accountant());
            client.getAccountant().setName(entityDetails.getClient_accountant().getName_accountant());
            client.getAccountant().setBirthday(client.getJ_director_birthday());
            client.getAccountant().setPassport_date_expiration(client.getJ_director_passp_date_end());
            client.getAccountant().setPassport_date_registration(client.getJ_director_passp_date_reg());
            client.getAccountant().setPassport_number(entityDetails.getClient_accountant().getDoc_number_accountant());
            client.getAccountant().setPassport_place_registration(entityDetails.getClient_accountant().getDoc_issuer_accountant());
            client.getAccountant().setPassport_serial(entityDetails.getClient_accountant().getDoc_series_accountant());
            client.getAccountant().setType_document(entityDetails.getClient_accountant().getDoc_type_accountant());
        }
        if (entityDetails.getClient_capital() != null) {
            String[] capitals = entityDetails.getClient_capital()
                    .getCapital_info().split(" ");
            String currency = capitals[capitals.length - 1];
            BigDecimal totalCapital = new BigDecimal(
                    entityDetails.getClient_capital().getCapital_info().
                            replace(" ", "").
                            replace(currency, ""));
            String currencyCode =
                    DbUtils.getDesc(currency,
                            "select kod from s_val where act='A' and kod_b = ?");
            client.setCapital_currency(currencyCode);
            client.setCapital_inform(totalCapital.toPlainString());
            //ISLogger.getLogger().error("EbpService mapLegalEntityDetails currency = " + client.getCapital_currency() + ", orginal = " + currency);
            //ISLogger.getLogger().error("EbpService mapLegalEntityDetails capital_currency = " + client.getCapital_inform() + ", original=" + totalCapital.toPlainString());
        }

        return client;
    }

    public static LegalEntity mapLegalEntityDetailsToLocalLE(LegalEntityDetails entityDetails) {
        if (entityDetails == null) {
            return null;
        }
        LegalEntity client = new LegalEntity();

        if (entityDetails.getClient() != null) {
            client.setNumber_tax_registration(entityDetails.getClient().getTin());
//			client.setCode_subject(entityDetails.getClient().getSubject_type());
//			client.setCode_subject("J");
            client.setCode_resident(entityDetails.getClient().getResidency_code());
            client.setCode_country(entityDetails.getClient().getCitizenship_code());
        }
        if (entityDetails.getClient_name() != null) {
            client.setName(entityDetails.getClient_name().getName());
            client.setShort_name(entityDetails.getClient_name().getName_short());
        }
        if (entityDetails.getClient_address() != null) {
            client.setPost_address_region(entityDetails.getClient_address().getAddress_region());
            client.setPost_address_distr(entityDetails.getClient_address().getAddress_subregion());
            client.setSoato(entityDetails.getClient_address().getAddress_code());
            client.setPost_address(entityDetails.getClient_address().getAddress());
            client.setEmail(entityDetails.getClient_address().getEmail());
            client.setPhone(entityDetails.getClient_address().getPhone());
            client.setFax(entityDetails.getClient_address().getFax());
        }
        if (entityDetails.getClient_registration() != null) {
            client.setCode_tax_org(entityDetails.getClient_registration().getTax_organization_code());
            client.setNumber_registration_doc(entityDetails.getClient_registration().getRegistration_document());
            client.setPlace_regist_name(entityDetails.getClient_registration().getRegistration_issuer());
            try {
                client.setDate_registration(df.parse(entityDetails.getClient_registration().getRegistration_date()));
            } catch (Exception e) {
                ISLogger.getLogger().error(CheckNull.getPstr(e));
                e.printStackTrace();
            }
        }
        if (entityDetails.getClient_static() != null) {
            client.setCode_form(entityDetails.getClient_static().getProperty_form());
            client.setOpf(entityDetails.getClient_static().getLegal_form());
            client.setCode_sector(entityDetails.getClient_static().getOked());

            client.setOkpo(entityDetails.getClient_static().getJuridical_form());
//			client.setSmall_business(entityDetails.getClient_static().getBusiness_sign());
            client.setInn_head_organization(entityDetails.getClient_static().getHeader_tin());
        }
        return client;
    }

    public static ClientJ mapIndividualDetails(IndividualDetails individualDetails) {
        if (individualDetails == null) {
            return null;
        }
        ClientJ client = new ClientJ();

        if (individualDetails.getClient() != null) {
            client.setJ_number_tax_registration(individualDetails.getClient().getTin());
            client.setCode_subject(individualDetails.getClient().getSubject_type());
            client.setCode_resident(individualDetails.getClient().getResident_code());
            client.setCode_country(individualDetails.getClient().getCountry_code());
            client.setP_number_tax_registration(individualDetails.getClient().getTin());
            client.setP_code_gender(individualDetails.getClient().getGender());
            try {
                client.setP_birthday(df.parse(individualDetails.getClient().getBirth_date()));
            } catch (Exception e) {
                ISLogger.getLogger().error(CheckNull.getPstr(e));
                e.printStackTrace();
            }
            client.setP_birth_place(individualDetails.getClient().getBirth_place());
            client.setP_inps(individualDetails.getClient().getPinfl());
        }
        if (individualDetails.getClient_name() != null) {
            client.setP_last_name_cyr(individualDetails.getClient_name().getLastname());
            client.setP_first_name_cyr(individualDetails.getClient_name().getFirstname());
            client.setP_patronymic_cyr(individualDetails.getClient_name().getMiddlename());
            client.setP_family(individualDetails.getClient_name().getLastname());
            client.setP_first_name(individualDetails.getClient_name().getFirstname());
            client.setP_patronymic(individualDetails.getClient_name().getMiddlename());
            client.setName(String.format("YATT '%s %s %s'", client.getP_family(), client.getP_first_name(), client.getP_patronymic()));
        }
        if (individualDetails.getClient_address() != null) {
            client.setJ_region(individualDetails.getClient_address().getAddress_region());
            client.setJ_distr(individualDetails.getClient_address().getAddress_subregion());
            client.setJ_soato(individualDetails.getClient_address().getAddress_soato());
//			client.setJ_post_address(individualDetails.getClient_address().getAddress());
            client.setJ_email(individualDetails.getClient_address().getEmail());
            client.setJ_phone(individualDetails.getClient_address().getPhone());
            client.setJ_fax(individualDetails.getClient_address().getFax());

            client.setP_code_adr_region(individualDetails.getClient_address().getAddress_region());
            client.setP_code_adr_distr(individualDetails.getClient_address().getAddress_subregion());
            client.setP_post_address(individualDetails.getClient_address().getAddress());

        }
        if (individualDetails.getClient_registration() != null) {
            client.setJ_code_tax_org(individualDetails.getClient_registration().getTax_organization_code());
            client.setJ_number_registration_doc(individualDetails.getClient_registration().getRegistration_doc_number());
            client.setJ_place_regist_name(individualDetails.getClient_registration().getRegistration_issuer());

            try {
                client.setJ_date_registration(df.parse(individualDetails.getClient_registration().getRegistration_date()));
            } catch (Exception e) {
                ISLogger.getLogger().error(CheckNull.getPstr(e));
                e.printStackTrace();
            }
        }
        if (individualDetails.getClient_static() != null) {
            client.setCode_form(individualDetails.getClient_static().getProperty_form());
            client.setJ_opf(individualDetails.getClient_static().getLegal_form());
            client.setJ_post_address(individualDetails.getClient_static().getActivity_address());
            if (individualDetails.getClient_static().getActivity_type() != null) {
                try {
                    String activityType = String.format("%03d",
                            Integer.parseInt(individualDetails.getClient_static().getActivity_type()));
                    //logger.error("********************* mapIndividualDetails activityType = " + activityType);
                    client.setJ_type_activity(activityType);
                } catch (NumberFormatException e) {
                    logger.error(CheckNull.getPstr(e));
                }
            }
        }

        return client;
    }

    public static Person getDirectorFromLegalEntityDetails(LegalEntityDetails entityDetails) {
        Person person = new Person();
        if (entityDetails.getClient_passport() != null) {
            person.setPost_address(entityDetails.getClient_passport().getAddress_director());
            person.setBirth_place(entityDetails.getClient_passport().getBirth_place_director());
            person.setCode_citizenship(entityDetails.getClient_passport().getCitizen_director());
            person.setName(entityDetails.getClient_passport().getName_director());
            try {
                person.setBirthday(df.parse(entityDetails.getClient_passport().getBirth_date_director()));
                person.setPassport_date_expiration(df.parse(entityDetails.getClient_passport().getDoc_date_expire_director()));
                person.setPassport_date_registration(df.parse(entityDetails.getClient_passport().getDoc_date_issue_director()));
            } catch (Exception e) {
                logger.error(CheckNull.getPstr(e));
            }
            person.setPassport_number(entityDetails.getClient_passport().getDoc_number_director());
            person.setPassport_place_registration(entityDetails.getClient_passport().getDoc_issuer_director());
            person.setPassport_serial(entityDetails.getClient_passport().getDoc_series_director());
            person.setType_document(entityDetails.getClient_passport().getDoc_type_director());
        }
        return person;
    }

    public static Person getAccountantFromLegalEntityDetails(LegalEntityDetails entityDetails) {
        Person person = new Person();
        if (entityDetails.getClient_accountant() != null) {
            person.setPost_address(entityDetails.getClient_accountant().getAddress_accountant());
            person.setBirth_place(entityDetails.getClient_accountant().getBirth_place_accountant());
            person.setCode_citizenship(entityDetails.getClient_accountant().getCitizen_accountant());
            person.setName(entityDetails.getClient_accountant().getName_accountant());
            try {
                person.setBirthday(df.parse(entityDetails.getClient_accountant().getBirth_date_accountant()));
                person.setPassport_date_expiration(df.parse(entityDetails.getClient_accountant().getDoc_date_expire_accountant()));
                person.setPassport_date_registration(df.parse(entityDetails.getClient_accountant().getDoc_date_issue_accountant()));
            } catch (Exception e) {
                ISLogger.getLogger().error(CheckNull.getPstr(e));
            }
            person.setPassport_number(entityDetails.getClient_accountant().getDoc_number_accountant());
            person.setPassport_place_registration(entityDetails.getClient_accountant().getDoc_issuer_accountant());
            person.setPassport_serial(entityDetails.getClient_accountant().getDoc_series_accountant());
            person.setType_document(entityDetails.getClient_accountant().getDoc_type_accountant());
        }
        return person;
    }

    public static Person makePersonFounder(Client_founders f) throws ParseException {
        Person person = new Person();

        person.setName(f.getName_founder());
        person.setPost_address(f.getAddress_founder());
        person.setCode_citizenship(f.getCitizen_founder());
        person.setCode_country(f.getCitizen_founder());
        person.setNumber_tax_registration(f.getTin_founder());
        person.setType_document(f.getDoc_type_accountant());
        person.setPassport_number(f.getDoc_number_founder());
        person.setPassport_serial(f.getDoc_series_founder());
        try {
            person.setPassport_date_expiration(
                    StringUtils.isEmpty(f.getDoc_date_expire_accountant()) ?
                            null :
                            df.parse(f.getDoc_date_expire_accountant()));
            person.setPassport_date_registration(StringUtils.isEmpty(f.getDoc_date_issue_founder()) ?
                    null :
                    df.parse(f.getDoc_date_issue_founder()));
        } catch (ParseException e) {
            logger.info("Unparseable date");
        }
        person.setPassport_place_registration(f.getDoc_issuer_founder());

        return person;
    }

    public static LegalEntity makeLegalEntityFounder(Client_founders f) {
        LegalEntity entity = new LegalEntity();

        entity.setName(f.getName_founder());
        entity.setPost_address(f.getAddress_founder());
        entity.setNumber_tax_registration(f.getTin_founder());
        try {
            entity.setDate_registration(df.parse(f.getDate_registration_founder()));
        } catch (ParseException e) {
            ISLogger.getLogger().error(CheckNull.getPstr(e));
            e.printStackTrace();
        }
        entity.setOkpo(f.getJuridical_form_founder());
        entity.setCode_country(f.getCitizen_founder());

        return entity;
    }


    public static List<PersonMap> getFoundersFromLegalEntityDetails(LegalEntityDetails entityDetails) throws ParseException {
        List<PersonMap> list = new ArrayList<PersonMap>();
        PersonMap personMap = null;
        BigDecimal hundred = new BigDecimal(100);
        BigDecimal percent = null;
        BigDecimal capital = null;
        if (entityDetails.getClient_founders() != null) {
            String[] capitals = entityDetails.getClient_capital().getCapital_info().split(" ");
            String currency = capitals[capitals.length - 1];
            BigDecimal totalCapital = new BigDecimal(
                    entityDetails.getClient_capital().getCapital_info().
                            replace(" ", "").
                            replace(currency, ""));
            String currencyCode = DbUtils.getDesc(currency, "select kod from s_val where act='A' and kod_b = ?");
            for (Client_founders f : entityDetails.getClient_founders()) {
                personMap = new PersonMap();
                personMap.setPrson_name(f.getName_founder());
                if (f.getType_founder().equals("1") || f.getType_founder().equals("3")) { //hamza 2017.11.08
                    personMap.setPerson_kind(PersonMapUtil.PERSONKIND_FOUNDER);
                    personMap.setPerson_type(PersonMapUtil.PERSONTYPE_P);
                    personMap.setPerson(makePersonFounder(f));
                } else {
                    personMap.setPerson_kind(PersonMapUtil.PERSONKIND_FOUNDER);
                    personMap.setPerson_type(PersonMapUtil.PERSONTYPE_J);
                    personMap.setLegalEntity(makeLegalEntityFounder(f));
                }
                percent = new BigDecimal(f.getCapital_founder());
                capital = totalCapital.divide(hundred, RoundingMode.HALF_EVEN).multiply(percent);
                personMap.setCapital(new FounderCapital(currencyCode, percent, capital));

                list.add(personMap);
            }
        }
        return list;
    }

    public static void main(String[] args) {
//		BigDecimal hundred = new BigDecimal(100);
//		String stringCapital = "4 000 000.00 UZS";
//		String[] capitals = stringCapital.split(" ");
//		String currency =capitals[capitals.length - 1];
//		BigDecimal totalCapital = new BigDecimal(
//				stringCapital.
//				replace(" ", "").
//				replace(currency, ""));
//		
//		BigDecimal percent = new BigDecimal("44.50");
//		BigDecimal capital = totalCapital.divide(hundred, RoundingMode.HALF_EVEN).multiply(percent);
//		System.out.println("percent = "+percent +", part = " + capital);
//		percent = new BigDecimal("45.50");
//		capital = totalCapital.divide(hundred, RoundingMode.HALF_EVEN).multiply(percent);
//		System.out.println("percent = "+percent +", part = " + capital);
        String activityType = String.format("%03d", Integer.parseInt("6"));

        System.out.println(activityType);

    }
}
