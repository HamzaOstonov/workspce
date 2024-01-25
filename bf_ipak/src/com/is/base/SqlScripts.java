package com.is.base;

public enum SqlScripts {
//	CLIENT_TYPES("select id data, name_ru label from SS_CLIENT_TYPES"),
	SS_DBLINK_BRANCH(	"select branch data, user_name label from ss_dblink_branch order by branch"),
	
	CLIENT_ACTION(		"select distinct id data, name label from action_client where manual=1"),
	
	CLIENT_TYPE(		"select kod_k data, decode((select value from ss_const where id=1002 and rownum=1), 'Y',kod_k||'-', null)||name_k2 label from S_TYPEKL where kod_k <> '00' and kod_k <> '08' and act <> 'Z' order by kod_k"),
	
	CLIENT_STATE(		"select distinct id data, name label from STATE_CLIENT t where id not in (0, 9) order by id "),
	
	CLIENT_CODE_LETTER(		"select kod_k data,name_k1 label from s_typekl"),
	
	CLIENT_LETTER_CODE(		"select name_k1 data,kod_k label from s_typekl"),
	
	COUNTRIES(			"select code_str data, decode((select value from ss_const where id=1002 and rownum=1), 'Y',code_str||'-', null)||name label from s_str where act <> 'Z' order by code_str"),	
	
	RESIDENT(			"select kod_rez data, type_rez label from S_REZKL"),
	
	VSBS(				"select kod data, decode((select value from ss_const where id=1002 and rownum=1), 'Y',kod||'-', null)||name label from s_vsbs where act <> 'Z' order by kod"),
	
	PASSPORT_TYPE(		"select code_cert data, decode((select value from ss_const where id=1002 and rownum=1), 'Y',code_cert||'-', null)||name_cert label from s_certificate where act <> 'Z' order by code_cert"),
	
	PASSPORT_IS_NEW(	"select 'O' data, 'не биометрический' label from dual union all select 'N' data, 'биометрический' label from dual "),
	
	NATION(				"select nat_id data, decode((select value from ss_const where id=1002 and rownum=1), 'Y',nat_id||'-', null)||nat_name label from s_nation where act <> 'Z' order by nat_id"),
	
	GENDER(				"select CODE_SEX data, NAME_SEX label from S_SEX where act<>'Z' order by CODE_SEX "),
	
	REGION(				"select region_id data, decode((select value from ss_const where id=1002 and rownum=1), 'Y',region_id||'-', null)||region_nam label from s_region where act <> 'Z' order by region_id"),
	//REGION("select 0 data, const.GetConst(1002) label from dual union all select 1 data, const.GetConst(1002,'00444') label from dual union all select 2 data, info.Getbranch label from dual order by data"),
		
	DISTRICT(			"select distr data, decode((select value from ss_const where id=1002 and rownum=1), 'Y',distr||'-', null)||distr_name label, region_id from s_distr order by distr"),
	
	DISTRICT_BY_REGION(	"select distr data, decode((select value from ss_const where id=1002 and rownum=1), 'Y',distr||'-', null)||distr_name label, region_id from s_distr where act <> 'Z' and region_id=? order by distr"),
	
	CREDIT_CLASS(		"select klass_id data,decode((select value from ss_const where id=1002 and rownum=1), 'Y',klass_id||'-', null)||klass_name label from s_klass where act <> 'Z' order by klass_id"),
	
	GNI(				"select gni_id data,decode((select value from ss_const where id=1002 and rownum=1), 'Y',gni_id||'-', null)||name_gni label from s_gni  order by gni_id"),
	
	//OPF(				"select opf_id data,opf_name label from S_OPF t where t.act <> 'Z' union all select opf_id data, name_ru label from S_SPR_OPF1 where act <> 'Z' order by data"),
    OPF(                "select opf_id data, decode((select value from ss_const where id=1002 and rownum=1), 'Y',opf_id||'-', null)||name_ru label from S_SPR_OPF1 where act <> 'Z' order by data"),
	
	SECTOR_OLD(			"select branch_id data, decode((select value from ss_const where id=1002 and rownum=1), 'Y',branch_id||'-', null)||branch_nam label from s_branch where branch_id >= '100' and act <> 'Z' order by data"),
	
	OKED(				"select code data, decode((select value from ss_const where id=1002 and rownum=1), 'Y',code||'-', null)||NAME_RU label from S_SPR_OKED"),
	
	OKED_GROUP(			"select distinct section_gr data, SG_NAME_RU label from S_SPR_OKED"),
	
	SOOGUN(				"select soogu data,decode((select value from ss_const where id=1002 and rownum=1), 'Y',soogu||'-', null)||soogu1 label from s_soogun where act <> 'Z' order by soogu"),

	SUBSIDIARIES(		"select code data, code||'-'||name label, branch prop_1 from SS_SUBSIDIARY where STATE='A' order by branch,code"),
	
	SUBBRANCHS(		"select bank_id data, bank_id||'-'||bank_name label, tcc_id prop_1 from s_mfo where act <> 'Z' and bank_type=info.getbanktype and bank_statu in ('10') order by tcc_id, bank_id"),
	
	PERSON_KIND(		"select id data, name_RU label from SS_PERSON_KINDS order by id"),
	
	CLIENT_CAPACITY(	"select kod_kr data, decode((select value from ss_const where id=1002 and rownum=1), 'Y',kod_kr||'-', null)||name_kr label from s_krfl where act <> 'Z' order by kod_kr"),
	
	CURRENCY(			"select kod data, decode((select value from ss_const where id=1002 and rownum=1), 'Y',kod||'-', null)||namev label from s_val where allow <> 0 and act <> 'Z' order by kod"),
	
	MFO(				"select bank_id data, decode((select value from ss_const where id=1002 and rownum=1), 'Y',bank_id||'-', null)||bank_name label from s_mfo where act <> 'Z' order by bank_id"),
	
	CUR_BANK_MFO(		"select smf.bank_id data, decode((select value from ss_const where id=1002 and rownum=1), 'Y',smf.bank_id||'-', null)||smf.bank_name label from s_mfo smf where smf.bank_type = (select smf1.bank_type from s_mfo smf1 where smf1.bank_id =  (select VALUE from bf_sets bs where bs.id ='HO'))"),
	
	ALL_BANKS(			"select bank_id data, decode((select value from ss_const where id=1002 and rownum=1), 'Y',bank_id||'-', null)||bank_name label from s_mfo"),
	
	NIBBD_STATE(		"select id data,name label from nibbd_state"),
	
	ATTACHMENTS_FOR_J(	"select data, label from ss_apps_types where type='J'"),
	
	ACCOUNT_GROUP(		"select a.id data, decode((select value from ss_const where id=1002 and rownum=1), 'Y',a.id||'-', null)||b.full_name label from account_group a, users b where a.user_id = b.id order by a.id"),
	
	ACCOUNT_STATE(		"select id data, name label from state_account where id <> 0 and deal_id=1 order by id"),
	
	SWIFT(				"select swift_id data, decode((select value from ss_const where id=1002 and rownum=1), 'Y',swift_id||'-', null)||bank_name label from s_spr_47"),
	
	ACTIVITY_TYPE(		"select trim(to_char(id, '000')) data, decode((select value from ss_const where id=1002 and rownum=1), 'Y',id||'-', null)||name label from ss_client_type_activity"),
	
	CLOSE_TYPES(		"select close_type_id data, close_type_name label from ss_spr_204 order by 1"),
	
	LOCK_TYPES(		"select lock_type_id data, lock_type_name label from ss_spr_203 order by 1"),
	
	LOCK_SOURCES(		"select lock_source_id data, lock_source_name label from ss_spr_207 order by 1"),
	
	ACC_CLOSE_TYPES(		"select close_type_acc data, close_type_name label from ss_spr_205 order by 1"),
	
	ACC_BAL(			"select code_b data, name_s label from s_account " +
    	    				"where not code_b like '_0000' and not code_b like '___00' " +
	    					"and destin = 3 and act <> 'Z' order by code_b"),
	
	CLIENT_HISTORY(		"select /*+ index(a xk_client_j_history) index(c xpk_users) */ " +
						    "a.date_correct, b.name, c.full_name, a.date_time " +
						    "from client_j_history a, action_client b, users c " +
						    "where a.branch = ? and a.id = ? " +
						    "and a.sign_registr = b.deal_id " +
						    "and a.action_id = b.id and a.emp_id = c.id"),
	
    SPECHAR_HISTORY(	"select a.date_correct, (b.name || '    ' || a.act) as name, " +
							"a.initiator as full_name, a.date_time " +
							"from SPECIALCLT_HISTORY a, ss_specialclt b " +
								"where a.branch =? and a.id_client =? and a.id_special=b.id"),
	
	SPECACC_HISTORY(	"select /*+ index(a xk_account_history) index(c xpk_users) */ " +
							"a.date_correct, b.name || '    ' || a.act as name, c.full_name, a.date_time from " +
								"specialacc_history a, ss_specialacc b, users c where " +
								"a.branch =? and a.acc =? " +
			                    "and a.id_special = b.id and a.initiator = c.user_name"),
	
    ACCOUNT_HISTORY(	"select /*+ index(a xk_account_history) index(c xpk_users) */ " +
						    "a.date_correct, b.name, c.full_name, a.date_time from " +
						    "account_history a, action_account b, users c where " +
						    "a.branch = ? and a.id = ? " +
						    "and a.sign_registr = b.deal_id and a.action_id = b.id and a.emp_id = c.id"),
						    
	SIGN_REGISTR_J(	"select '1' data, 'Юридический' label from dual union all select '3' data, 'Юридический, имеющий основной счет в другом банке' label from dual"),
    
    INFO_INIT("{ call info.init()}"),
    
    OPER_DAY("select info.getday from dual"),
    
    SET_PARAM("{ call Param.SetParam(?,?) }"),
    
    CLEAR_PARAM("{ call Param.clearparam() }"),
   
    GET_PARAM_ID("{? = call Param.getparam('ID') }"),
    
    DO_ACTION("{ call kernel.doAction(?,?,?) }");
	
	
	private String sql;
	private SqlScripts(String sql) {
		this.sql = sql;
	}
	public String getSql() {
		return sql;
	}
}
