package com.is.uzcard;

import java.lang.String;

public final class Constants {
	public final static String BTRT01 = "BTRT01", BTRT02 = "BTRT02", BTRT03 = "BTRT03", BTRT05 = "BTRT05",
			BTRT06 = "BTRT06", BTRT07 = "BTRT07", BTRT20 = "BTRT20", BTRT25 = "BTRT25", BTRT30 = "BTRT30",
			BTRT35 = "BTRT35", BTRT51 = "BTRT51", BTRT52 = "BTRT52", BTRT54 = "BTRT54", BTRT56 = "BTRT56";

	public enum FILTER_FIELD_TYPE {
		NOT_STATE_EMP, STATE_EMP
	};

	public final class KeyCode {
		public final static int F9 = 120;
	}

	public final class RefDataType {
		public final static String VIP_FLAG = "1", ADDRESS_TYPE = "2", P_ID_TYPE = "3", P_SEX = "5", REZIDENCE = "6",
				LEGAL_TYPE = "11", TERMINAL_TYPE = "13", TERMINAL_CATERGORY = "16", CARD_CAPTURE_CAPABILITY = "19",
				OPERATING_ENVIRONMENT = "20", POSITION = "24", MCC_CODE = "25", REISSUE = "27", SS_Uzcard_28 = "28",
				SS_Uzcard_29 = "29", SS_Uzcard_30 = "30", SS_Uzcard_31 = "31", SS_Uzcard_33 = "33";
	}

	public final class SQL {
		public final static String viewClient = "select a.ID   id ,  a.BRANCH   branch , "
				+ " a.ID_CLIENT   id_client ,  a.NAME   name ,  a.CODE_COUNTRY   code_country , "
				+ " a.CODE_TYPE   code_type ,  a.CODE_RESIDENT   code_resident , "
				+ " a.CODE_SUBJECT   code_subject , a.SIGN_REGISTR   sign_registr , "
				+ " a.CODE_FORM   code_form ,  a.DATE_OPEN   date_open ,  a.DATE_CLOSE   date_close , "
				+ " a.STATE   state ,  a.KOD_ERR   kod_err ,  a.FILE_NAME   file_name , "
				+ "  b.SHORT_NAME   j_short_name ,   b.PLACE_REGIST_NAME   j_place_regist_name , "
				+ "  b.DATE_REGISTRATION   j_date_registration , "
				+ "  b.NUMBER_REGISTRATION_DOC   j_number_registration_doc ,   b.CODE_TAX_ORG   j_code_tax_org , "
				+ "  b.NUMBER_TAX_REGISTRATION   j_number_tax_registration ,  b.CODE_SECTOR   j_code_sector , "
				+ "  b.CODE_ORGAN_DIRECT   j_code_organ_direct , "
				+ "  b.CODE_HEAD_ORGANIZATION   j_code_head_organization , "
				+ "  b.CODE_CLASS_CREDIT   j_code_class_credit ,   b.DIRECTOR_NAME   j_director_name , "
				+ "  b.DIRECTOR_PASSPORT   j_director_passport , "
				+ "  b.CHIEF_ACCOUNTER_NAME   j_chief_accounter_name , "
				+ "  b.CHIEF_ACCOUNTER_PASSPORT   j_chief_accounter_passport ,   b.CODE_BANK   j_code_bank , "
				+ "  b.ACCOUNT 	j_account ,   b.POST_ADDRESS 	j_post_address ,   b.PHONE 	j_phone , "
				+ "  b.FAX 	j_fax ,   b.EMAIL 	j_email ,  b.SIGN_TRADE   j_sign_trade , "
				+ "  b.OPF   j_opf ,   b.SOATO   j_soato ,   b.OKPO   j_okpo , "
				+ "  b.INN_HEAD_ORGANIZATION   j_inn_head_organization ,  b.REGION   j_region , "
				+ "  b.DISTR   j_distr ,  c.BIRTHDAY   p_birthday ,  c.POST_ADDRESS   p_post_address , "
				+ "  c.PASSPORT_TYPE   p_passport_type ,  c.PASSPORT_SERIAL   p_passport_serial , "
				+ "  c.PASSPORT_NUMBER   p_passport_number , "
				+ "  c.PASSPORT_PLACE_REGISTRATION   p_passport_place_registration , "
				+ "  c.PASSPORT_DATE_REGISTRATION   p_passport_date_registration , "
				+ "  c.CODE_TAX_ORG   p_code_tax_org ,  c.NUMBER_TAX_REGISTRATION   p_number_tax_registration , "
				+ "  c.CODE_BANK   p_code_bank ,  c.CODE_CLASS_CREDIT   p_code_class_credit , "
				+ "  c.CODE_CITIZENSHIP 	p_code_citizenship ,  c.BIRTH_PLACE 	p_birth_place , "
				+ "  c.code_capacity 	p_code_capacity ,  c.capacity_status_date 	p_capacity_status_date , "
				+ "  c.capacity_status_place 	p_capacity_status_place , "
				+ "  c.num_certif_capacity 	p_num_certif_capacity ,  c.phone_home 	p_phone_home , "
				+ "  c.phone_mobile 	p_phone_mobile ,  c.email_address 	p_email_address , "
				+ "  c.pension_sertif_serial 	p_pension_sertif_serial , c.code_gender 	p_code_gender , "
				+ "  c.code_nation 	p_code_nation ,  c.code_birth_region 	p_code_birth_region , "
				+ "  c.code_birth_distr 	p_code_birth_distr ,  c.type_document 	p_type_document , "
				+ "  c.passport_date_expiration 	p_passport_date_expiration , "
				+ "  c.code_adr_region 	p_code_adr_region , "
				+ "  c.code_adr_distr 	p_code_adr_distr , c.family, c.first_name, c.patronymic, "
				+ "  c.inps 	p_inps   from client a, client_j b, client_p c "
				+ "  where  a.BRANCH = b.BRANCH(+)   and a.BRANCH = c.BRANCH(+) "
				+ "    and a.ID_CLIENT = b.ID(+)   and a.ID_CLIENT = c.ID(+) ";

		public final static String BTRT03SQL = "Select a.branch,a.app_id, a.state, a.contract_id "
				+ "       ,d.COMPANY_NAME ,d.CARD_NUMBER ,d.IS_PRIMARY ,d.CARD_TYPE ,d.DEF_ATM_ACCOUNT ,d.DEF_POS_ACCOUNT ,d.EMBOSSED_CH_NAME ,d.EXPIRATION_DATE "
				+ "       ,f.ACCOUNT_NUMBER ,substr(f.ACCOUNT_NUMBER,-20) acc,f.CURRENCY ,f.ACCOUNT_TYPE "
				+ "       ,e.ADDRESS_ID ,e.ADDRESS_TYPE ,e.ADDRESS_LINE1 ,e.ADDRESS_LINE2 ,e.REGION ,e.COUNTRY ,e.PRIMARY_PHONE ,e.MOBILE_PHONE ,e.EMAIL ,e.A_PROC_MODE "
				+ "       ,c.PERSON_ID ,nvl(c.NEW_FIRST_NAME,c.FIRST_NAME) FIRST_NAME ,c.SECOND_NAME ,nvl(c.NEW_SURNAME,c.SURNAME) SURNAME ,c.BIRTH_DATE ,c.P_ID_TYPE ,c.P_ID_NUMBER ,c.P_PROC_MODE ,c.P_ID_SERIES "
				+ "       ,b.CUSTOMER_ID,substr(b.CUSTOMER_ID,-8) CustomerId "
				+ "       ,a.DEAL_ID, a.APP_TYPE ,a.REC_NUMBER ,a.CONTRACT_ID ,a.EMP_ID ,a.APP_TAG,h.name "
				+ "  from CARD_APPLICATIONS       a, " + "       APP_CARD_CUSTOMER       b, "
				+ "       APP_CARD_CH_PERSON_INFO c, " + "       APP_CARD                d, "
				+ "       APP_CARD_CH_ADDRESS     e, " + "       APP_CARD_ACCOUNT        f, "
				+ "       STATE_CARD        h " + " where a.branch = ? " + "   and a.branch = b.branch "
				+ "   and a.branch = c.branch " + "   and a.branch = d.branch " + "   and a.branch = e.branch "
				+ "   and a.branch = f.branch " + "   and a.app_id = b.app_id " + "   and a.app_id = c.app_id "
				+ "   and a.app_id = d.app_id " + "   and a.app_id = e.app_id " + "   and a.app_id = f.app_id ";

		public final static String BTRT20SQL = "Select a.branch,     a.app_id,   a.rec_number, "
				+ "       d.contract_id,   a.emp_id,       c.customer_id,   c.vip_flag, "
				+ "       c.okpo,      c.inn,  t.person_id,      t.surname, "
				+ "       t.first_name,       t.second_name,  t.birth_date,       t.p_id_type, "
				+ " t.p_id_number,       t.p_id_series, " + "  cc.card_number,       d.card_type, "
				+ "  d.embossed_ch_name,      t.surname newsurname,       t.first_name newfirst_name, "
				+ "       t.second_name newsecond_name,       t.birth_date newbirth_date, "
				+ "       t.p_id_type newp_id_type,       t.p_id_number newp_id_number, "
				+ "       t.p_id_series newp_id_series,      cc.card_number newcard_number, "
				+ "       d.embossed_ch_name newembossed_ch_name,      '3' P_PROC_MODE"
				+ "  from card_applications     a,      app_card_customer     c,"
				+ "       card_person           t,      card_cardholder_cards cc,"
				+ "       card                  d where a.branch=c.branch    and c.branch=t.branch "
				+ "   and t.branch=cc.branch    and cc.branch=d.branch " + "   and a.branch=? "
				+ "   and a.app_id = c.app_id    and c.customer_id = t.customer_id "
				+ "   and t.person_id = cc.person_id    and cc.card_number = d.card_number  and a.state = 4"
				+ " and substr(c.customer_id,-8)=?";
		public final static String BTRT25SQL = "select ca.branch,ca.account_number,substr(ca.account_number,-20) acc,ca.currency,ca.account_type, "
				+ "        cca.card_number, c.contract_id, c.expiration_date,"
				+ "        p.customer_id, substr(p.customer_id,-8) client ,p.person_id,p.surname,p.first_name,p.second_name "
				+ "from      card_person p, 		card_cardholder_cards ch, 	card_account ca, "
				+ "		card_card_account cca, 		card c where p.branch=? "
				+ "and    p.branch=ch.branch and    ch.branch=ca.branch and    ca.branch=cca.branch "
				+ "and    cca.branch=c.branch and    ca.account_number=cca.account_number "
				+ "and    c.card_number=cca.card_number and    c.card_status='CRST0' "
				+ "and    ch.person_id=p.person_id and    ch.card_number=c.card_number"
				+ " and substr(p.customer_id,-8)=?";

		public final static String BTRT30SQL_CARD = "select substr(cc.customer_id, -8) client,       cc.*,'#',  "
				+ "       cp.*,        ccc.*,       cca.*, "
				+ "       substr(cca.account_number, -20) acc,        cpa.*,     ca.* "
				+ "  from card_customer         cc,    card_person           cp,  "
				+ "       card_cardholder_cards ccc,      card_card_account     cca,  "
				+ "       card_person_address   cpa,       card_address          ca   where cc.branch = ?  "
				+ "   and cc.branch = cp.branch    and cc.branch = ccc.branch   "
				+ "   and cc.branch = cca.branch   and cc.branch = cpa.branch  "
				+ "   and cc.branch = ca.branch   and substr(cc.customer_id, -8) = substr(cp.customer_id, -8) "
				+ "   and cp.person_id = ccc.person_id    and cca.card_number = ccc.card_number   "
				+ "   and cp.person_id = cpa.person_id   "
				+ "   and cpa.address_id = ca.address_id   and substr(cc.customer_id,-8)=?";

		public final static String BTRT30SQL_CLIENT = "select p.branch,    p.id customer_id, "
				+ "        p.name CC_name, "
				+ "        p.code_gender sex, p.first_name FIRST_NAME, p.family SECOND_NAME, p.patronymic PATRONYMIC,"
				+ "        c.code_resident residence,  p.birthday birth_date, "
				+ "        p.passport_type NEW_P_ID_TYPE,  p.passport_serial p_id_series, "
				+ "        p.passport_number p_id_number, p.passport_place_registration p_id_authority, "
				+ "        p.passport_date_registration p_id_issue_date, p.post_address p_post_address, "
				+ "        p.code_adr_region p_code_adr_region, c.code_country code_country, "
				+ "        p.phone_home p_phone_home, p.phone_mobile p_phone_mobile, "
				+ "        p.email_address p_email_address from client_p p, client c "
				+ "  where p.branch = c.branch and p.id = c.id_client    and C.branch=? and c.id_client=?";

		public final static String comboBoxDataSQL = "select t.ID_SPR,t.ID,trim(t.SNAME) data,t.NAME||' ('||substr(t.SNAME,1,10)||')' label from ss_uzcard_spr t where t.id_spr=";
		public final static String VIEW_03 = " and a.state = h.id and h.deal_id = 1 and a.state  = 4 and a.app_type='BTRT01'";

		public final static String clientBTRT2 = "select  count(*) cnt "
				+ " from card_person c, card_person_address p, card_address a, client cc, card_customer cr  "
				+ " where c.branch = p.branch " + "  and p.branch = a.branch " + " and a.branch = cc.branch "
				+ " and cc.branch = cr.branch " + " and c.person_id = p.person_id "
				+ " and p.address_id = a.address_id " + " and substr(c.customer_id, -8) = cc.id_client "
				+ " and c.customer_id = cr.customer_id and cc.branch=? and cc.id_client=?";

		public final static String btrt01appList = "select a.*, " + "   c.branch c_branch, c.app_id c_app_id, "
				+ " c.customer_id,  c.customer_desc, " + " c.new_customer_name,  c.vip_flag, "
				+ "  c.okpo,  c.inn,  c.deal_id, " + " c.state c_state,  t.id t_id,  t.app_type t_app_type, "
				+ " t.name t_name,  s.deal_id s_deal_id, " + " s.id s_id,  s.name s_name "
				+ " from card_applications a, " + "     app_card_customer c,   card_tlv_app  t,  state_card   s "
				+ "  where a.branch=?   and c.branch = a.branch  and a.app_id = c.app_id "
				+ "  and a.app_type = t.app_type   and t.id = s.deal_id "
				+ "  and s.id=a.state   and a.app_type ='BTRT01'  and substr(c.customer_id,-8)=?";
	}
}
