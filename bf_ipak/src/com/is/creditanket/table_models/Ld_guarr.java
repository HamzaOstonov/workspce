package com.is.creditanket.table_models;

import java.util.Date;

public class Ld_guarr {

	private Long pk;
    private String branch;
    private Long id;
    private String guar_id;
    private String name;
    private String address;
    private String doc_num;
    private Date doc_date;
    private String currency;
    private Double amount;
    private String klass_o;
    private Long canbuy;
    private Integer hasdepo;
    private String bank_name;
    private Integer hasdoc;
    private String code_subject;
    private String notarial_doc_numb;
    private Date start_date;
    private Date end_date;
    private String inn;
    private String mfo;
    private String name2;
    private String account;
    private Long stock_nominal_value;
    private Long stock_count;
    private Long stock_diskont;
    private String stock_name;
    private String sign_depo;
    private String insc_name;
    private String insc_inn;
    private String insc_num;
    private Date insc_date;
    private Date insc_date_cl;
    private String niki_res1;
    private String niki_res2;
    private String niki_gr_branch;
    private String niki_gr_code_type;
    private String niki_inn;
    private String niki_soogun;
    private String acomp_name;
    private Date acomp_date;
    private String acomp_curr;
    private Double acomp_summa;
    private String niki_owner;
    private String polis_num;
    private Date polis_date;
    private String region_id;
    private String distr_id;
    private String massiv;
    private String street;
    private String home;
    private String home_num;
    private String economical_zone;
    private String cadastr_org_region;
    private String cadastr_org_distr;
    private String notarial_office_num;
    private String ser_eval_company;
    private String lis_num;
    private Date lis_date;
    private String eval_report_num;
    private String id_client;
    private Date date_operation;
    private String depositary;
    private String depositary_account;
    private Integer sowing_area;
    private Double massa;
    private String sertificate_num;
    private String sertificate_ser;
    private Double sertificate_rate;
    private String inn_reestr;
    private String cadastr_place_region;
    private Integer cadastr_place_town;
    private String cadastr_place_distr;
    private String cadastr_place_adres;
    private String cadastr_place_x;
    private String cadastr_place_y;
    private Integer building_type;
    private Integer building_year;
    private Integer building_square;
    private Integer build_constr_type;
    private Integer building_kind;
    private Integer building_num;
    private Integer zr_term;
	private Ld_guar_blocks ld_guar_block;
	private Ld_guar_cadastre ld_guar_cadastre;
	private Ld_guar_car ld_guar_car;
	private Ld_guar_equipment ld_guar_equipment;
	private Ld_guar_gold ld_guar_gold;
	
    public Ld_guarr() {
	
    }

	public Ld_guarr(Long pk, String branch, Long id, String guar_id,
			String name, String address, String doc_num, Date doc_date,
			String currency, Double amount, String klass_o, Long canbuy,
			Integer hasdepo, String bank_name, Integer hasdoc,
			String code_subject, String notarial_doc_numb, Date start_date,
			Date end_date, String inn, String mfo, String name2,
			String account, Long stock_nominal_value, Long stock_count,
			Long stock_diskont, String stock_name, String sign_depo,
			String insc_name, String insc_inn, String insc_num, Date insc_date,
			Date insc_date_cl, String niki_res1, String niki_res2,
			String niki_gr_branch, String niki_gr_code_type, String niki_inn,
			String niki_soogun, String acomp_name, Date acomp_date,
			String acomp_curr, Double acomp_summa, String niki_owner,
			String polis_num, Date polis_date, String region_id,
			String distr_id, String massiv, String street, String home,
			String home_num, String economical_zone,
			String cadastr_org_region, String cadastr_org_distr,
			String notarial_office_num, String ser_eval_company,
			String lis_num, Date lis_date, String eval_report_num,
			String id_client, Date date_operation, String depositary,
			String depositary_account, Integer sowing_area, Double massa,
			String sertificate_num, String sertificate_ser,
			Double sertificate_rate, String inn_reestr,
			String cadastr_place_region, Integer cadastr_place_town,
			String cadastr_place_distr, String cadastr_place_adres,
			String cadastr_place_x, String cadastr_place_y,
			Integer building_type, Integer building_year,
			Integer building_square, Integer build_constr_type,
			Integer building_kind, Integer building_num, Integer zr_term,
			Ld_guar_blocks ld_guar_block,Ld_guar_cadastre ld_guar_cadastre,
			Ld_guar_car ld_guar_car,Ld_guar_equipment ld_guar_equipment,Ld_guar_gold ld_guar_gold) {
		super();
		this.pk = pk;
		this.branch = branch;
		this.id = id;
		this.guar_id = guar_id;
		this.name = name;
		this.address = address;
		this.doc_num = doc_num;
		this.doc_date = doc_date;
		this.currency = currency;
		this.amount = amount;
		this.klass_o = klass_o;
		this.canbuy = canbuy;
		this.hasdepo = hasdepo;
		this.bank_name = bank_name;
		this.hasdoc = hasdoc;
		this.code_subject = code_subject;
		this.notarial_doc_numb = notarial_doc_numb;
		this.start_date = start_date;
		this.end_date = end_date;
		this.inn = inn;
		this.mfo = mfo;
		this.name2 = name2;
		this.account = account;
		this.stock_nominal_value = stock_nominal_value;
		this.stock_count = stock_count;
		this.stock_diskont = stock_diskont;
		this.stock_name = stock_name;
		this.sign_depo = sign_depo;
		this.insc_name = insc_name;
		this.insc_inn = insc_inn;
		this.insc_num = insc_num;
		this.insc_date = insc_date;
		this.insc_date_cl = insc_date_cl;
		this.niki_res1 = niki_res1;
		this.niki_res2 = niki_res2;
		this.niki_gr_branch = niki_gr_branch;
		this.niki_gr_code_type = niki_gr_code_type;
		this.niki_inn = niki_inn;
		this.niki_soogun = niki_soogun;
		this.acomp_name = acomp_name;
		this.acomp_date = acomp_date;
		this.acomp_curr = acomp_curr;
		this.acomp_summa = acomp_summa;
		this.niki_owner = niki_owner;
		this.polis_num = polis_num;
		this.polis_date = polis_date;
		this.region_id = region_id;
		this.distr_id = distr_id;
		this.massiv = massiv;
		this.street = street;
		this.home = home;
		this.home_num = home_num;
		this.economical_zone = economical_zone;
		this.cadastr_org_region = cadastr_org_region;
		this.cadastr_org_distr = cadastr_org_distr;
		this.notarial_office_num = notarial_office_num;
		this.ser_eval_company = ser_eval_company;
		this.lis_num = lis_num;
		this.lis_date = lis_date;
		this.eval_report_num = eval_report_num;
		this.id_client = id_client;
		this.date_operation = date_operation;
		this.depositary = depositary;
		this.depositary_account = depositary_account;
		this.sowing_area = sowing_area;
		this.massa = massa;
		this.sertificate_num = sertificate_num;
		this.sertificate_ser = sertificate_ser;
		this.sertificate_rate = sertificate_rate;
		this.inn_reestr = inn_reestr;
		this.cadastr_place_region = cadastr_place_region;
		this.cadastr_place_town = cadastr_place_town;
		this.cadastr_place_distr = cadastr_place_distr;
		this.cadastr_place_adres = cadastr_place_adres;
		this.cadastr_place_x = cadastr_place_x;
		this.cadastr_place_y = cadastr_place_y;
		this.building_type = building_type;
		this.building_year = building_year;
		this.building_square = building_square;
		this.build_constr_type = build_constr_type;
		this.building_kind = building_kind;
		this.building_num = building_num;
		this.zr_term = zr_term;
		this.ld_guar_block = ld_guar_block;
		this.ld_guar_cadastre = ld_guar_cadastre;
		this.ld_guar_car = ld_guar_car;
		this.ld_guar_equipment = ld_guar_equipment;
		this.ld_guar_gold = ld_guar_gold;
	}

	public Long getPk() {
		return pk;
	}

	public void setPk(Long pk) {
		this.pk = pk;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGuar_id() {
		return guar_id;
	}

	public void setGuar_id(String guar_id) {
		this.guar_id = guar_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDoc_num() {
		return doc_num;
	}

	public void setDoc_num(String doc_num) {
		this.doc_num = doc_num;
	}

	public Date getDoc_date() {
		return doc_date;
	}

	public void setDoc_date(Date doc_date) {
		this.doc_date = doc_date;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getKlass_o() {
		return klass_o;
	}

	public void setKlass_o(String klass_o) {
		this.klass_o = klass_o;
	}

	public Long getCanbuy() {
		return canbuy;
	}

	public void setCanbuy(Long canbuy) {
		this.canbuy = canbuy;
	}

	public Integer getHasdepo() {
		return hasdepo;
	}

	public void setHasdepo(Integer hasdepo) {
		this.hasdepo = hasdepo;
	}

	public String getBank_name() {
		return bank_name;
	}

	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}

	public Integer getHasdoc() {
		return hasdoc;
	}

	public void setHasdoc(Integer hasdoc) {
		this.hasdoc = hasdoc;
	}

	public String getCode_subject() {
		return code_subject;
	}

	public void setCode_subject(String code_subject) {
		this.code_subject = code_subject;
	}

	public String getNotarial_doc_numb() {
		return notarial_doc_numb;
	}

	public void setNotarial_doc_numb(String notarial_doc_numb) {
		this.notarial_doc_numb = notarial_doc_numb;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public String getInn() {
		return inn;
	}

	public void setInn(String inn) {
		this.inn = inn;
	}

	public String getMfo() {
		return mfo;
	}

	public void setMfo(String mfo) {
		this.mfo = mfo;
	}

	public String getName2() {
		return name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Long getStock_nominal_value() {
		return stock_nominal_value;
	}

	public void setStock_nominal_value(Long stock_nominal_value) {
		this.stock_nominal_value = stock_nominal_value;
	}

	public Long getStock_count() {
		return stock_count;
	}

	public void setStock_count(Long stock_count) {
		this.stock_count = stock_count;
	}

	public Long getStock_diskont() {
		return stock_diskont;
	}

	public void setStock_diskont(Long stock_diskont) {
		this.stock_diskont = stock_diskont;
	}

	public String getStock_name() {
		return stock_name;
	}

	public void setStock_name(String stock_name) {
		this.stock_name = stock_name;
	}

	public String getSign_depo() {
		return sign_depo;
	}

	public void setSign_depo(String sign_depo) {
		this.sign_depo = sign_depo;
	}

	public String getInsc_name() {
		return insc_name;
	}

	public void setInsc_name(String insc_name) {
		this.insc_name = insc_name;
	}

	public String getInsc_inn() {
		return insc_inn;
	}

	public void setInsc_inn(String insc_inn) {
		this.insc_inn = insc_inn;
	}

	public String getInsc_num() {
		return insc_num;
	}

	public void setInsc_num(String insc_num) {
		this.insc_num = insc_num;
	}

	public Date getInsc_date() {
		return insc_date;
	}

	public void setInsc_date(Date insc_date) {
		this.insc_date = insc_date;
	}

	public Date getInsc_date_cl() {
		return insc_date_cl;
	}

	public void setInsc_date_cl(Date insc_date_cl) {
		this.insc_date_cl = insc_date_cl;
	}

	public String getNiki_res1() {
		return niki_res1;
	}

	public void setNiki_res1(String niki_res1) {
		this.niki_res1 = niki_res1;
	}

	public String getNiki_res2() {
		return niki_res2;
	}

	public void setNiki_res2(String niki_res2) {
		this.niki_res2 = niki_res2;
	}

	public String getNiki_gr_branch() {
		return niki_gr_branch;
	}

	public void setNiki_gr_branch(String niki_gr_branch) {
		this.niki_gr_branch = niki_gr_branch;
	}

	public String getNiki_gr_code_type() {
		return niki_gr_code_type;
	}

	public void setNiki_gr_code_type(String niki_gr_code_type) {
		this.niki_gr_code_type = niki_gr_code_type;
	}

	public String getNiki_inn() {
		return niki_inn;
	}

	public void setNiki_inn(String niki_inn) {
		this.niki_inn = niki_inn;
	}

	public String getNiki_soogun() {
		return niki_soogun;
	}

	public void setNiki_soogun(String niki_soogun) {
		this.niki_soogun = niki_soogun;
	}

	public String getAcomp_name() {
		return acomp_name;
	}

	public void setAcomp_name(String acomp_name) {
		this.acomp_name = acomp_name;
	}

	public Date getAcomp_date() {
		return acomp_date;
	}

	public void setAcomp_date(Date acomp_date) {
		this.acomp_date = acomp_date;
	}

	public String getAcomp_curr() {
		return acomp_curr;
	}

	public void setAcomp_curr(String acomp_curr) {
		this.acomp_curr = acomp_curr;
	}

	public Double getAcomp_summa() {
		return acomp_summa;
	}

	public void setAcomp_summa(Double acomp_summa) {
		this.acomp_summa = acomp_summa;
	}

	public String getNiki_owner() {
		return niki_owner;
	}

	public void setNiki_owner(String niki_owner) {
		this.niki_owner = niki_owner;
	}

	public String getPolis_num() {
		return polis_num;
	}

	public void setPolis_num(String polis_num) {
		this.polis_num = polis_num;
	}

	public Date getPolis_date() {
		return polis_date;
	}

	public void setPolis_date(Date polis_date) {
		this.polis_date = polis_date;
	}

	public String getRegion_id() {
		return region_id;
	}

	public void setRegion_id(String region_id) {
		this.region_id = region_id;
	}

	public String getDistr_id() {
		return distr_id;
	}

	public void setDistr_id(String distr_id) {
		this.distr_id = distr_id;
	}

	public String getMassiv() {
		return massiv;
	}

	public void setMassiv(String massiv) {
		this.massiv = massiv;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHome() {
		return home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	public String getHome_num() {
		return home_num;
	}

	public void setHome_num(String home_num) {
		this.home_num = home_num;
	}

	public String getEconomical_zone() {
		return economical_zone;
	}

	public void setEconomical_zone(String economical_zone) {
		this.economical_zone = economical_zone;
	}

	public String getCadastr_org_region() {
		return cadastr_org_region;
	}

	public void setCadastr_org_region(String cadastr_org_region) {
		this.cadastr_org_region = cadastr_org_region;
	}

	public String getCadastr_org_distr() {
		return cadastr_org_distr;
	}

	public void setCadastr_org_distr(String cadastr_org_distr) {
		this.cadastr_org_distr = cadastr_org_distr;
	}

	public String getNotarial_office_num() {
		return notarial_office_num;
	}

	public void setNotarial_office_num(String notarial_office_num) {
		this.notarial_office_num = notarial_office_num;
	}

	public String getSer_eval_company() {
		return ser_eval_company;
	}

	public void setSer_eval_company(String ser_eval_company) {
		this.ser_eval_company = ser_eval_company;
	}

	public String getLis_num() {
		return lis_num;
	}

	public void setLis_num(String lis_num) {
		this.lis_num = lis_num;
	}

	public Date getLis_date() {
		return lis_date;
	}

	public void setLis_date(Date lis_date) {
		this.lis_date = lis_date;
	}

	public String getEval_report_num() {
		return eval_report_num;
	}

	public void setEval_report_num(String eval_report_num) {
		this.eval_report_num = eval_report_num;
	}

	public String getId_client() {
		return id_client;
	}

	public void setId_client(String id_client) {
		this.id_client = id_client;
	}

	public Date getDate_operation() {
		return date_operation;
	}

	public void setDate_operation(Date date_operation) {
		this.date_operation = date_operation;
	}

	public String getDepositary() {
		return depositary;
	}

	public void setDepositary(String depositary) {
		this.depositary = depositary;
	}

	public String getDepositary_account() {
		return depositary_account;
	}

	public void setDepositary_account(String depositary_account) {
		this.depositary_account = depositary_account;
	}

	public Integer getSowing_area() {
		return sowing_area;
	}

	public void setSowing_area(Integer sowing_area) {
		this.sowing_area = sowing_area;
	}

	public Double getMassa() {
		return massa;
	}

	public void setMassa(Double massa) {
		this.massa = massa;
	}

	public String getSertificate_num() {
		return sertificate_num;
	}

	public void setSertificate_num(String sertificate_num) {
		this.sertificate_num = sertificate_num;
	}

	public String getSertificate_ser() {
		return sertificate_ser;
	}

	public void setSertificate_ser(String sertificate_ser) {
		this.sertificate_ser = sertificate_ser;
	}

	public Double getSertificate_rate() {
		return sertificate_rate;
	}

	public void setSertificate_rate(Double sertificate_rate) {
		this.sertificate_rate = sertificate_rate;
	}

	public String getInn_reestr() {
		return inn_reestr;
	}

	public void setInn_reestr(String inn_reestr) {
		this.inn_reestr = inn_reestr;
	}

	public String getCadastr_place_region() {
		return cadastr_place_region;
	}

	public void setCadastr_place_region(String cadastr_place_region) {
		this.cadastr_place_region = cadastr_place_region;
	}

	public Integer getCadastr_place_town() {
		return cadastr_place_town;
	}

	public void setCadastr_place_town(Integer cadastr_place_town) {
		this.cadastr_place_town = cadastr_place_town;
	}

	public String getCadastr_place_distr() {
		return cadastr_place_distr;
	}

	public void setCadastr_place_distr(String cadastr_place_distr) {
		this.cadastr_place_distr = cadastr_place_distr;
	}

	public String getCadastr_place_adres() {
		return cadastr_place_adres;
	}

	public void setCadastr_place_adres(String cadastr_place_adres) {
		this.cadastr_place_adres = cadastr_place_adres;
	}

	public String getCadastr_place_x() {
		return cadastr_place_x;
	}

	public void setCadastr_place_x(String cadastr_place_x) {
		this.cadastr_place_x = cadastr_place_x;
	}

	public String getCadastr_place_y() {
		return cadastr_place_y;
	}

	public void setCadastr_place_y(String cadastr_place_y) {
		this.cadastr_place_y = cadastr_place_y;
	}

	public Integer getBuilding_type() {
		return building_type;
	}

	public void setBuilding_type(Integer building_type) {
		this.building_type = building_type;
	}

	public Integer getBuilding_year() {
		return building_year;
	}

	public void setBuilding_year(Integer building_year) {
		this.building_year = building_year;
	}

	public Integer getBuilding_square() {
		return building_square;
	}

	public void setBuilding_square(Integer building_square) {
		this.building_square = building_square;
	}

	public Integer getBuild_constr_type() {
		return build_constr_type;
	}

	public void setBuild_constr_type(Integer build_constr_type) {
		this.build_constr_type = build_constr_type;
	}

	public Integer getBuilding_kind() {
		return building_kind;
	}

	public void setBuilding_kind(Integer building_kind) {
		this.building_kind = building_kind;
	}

	public Integer getBuilding_num() {
		return building_num;
	}

	public void setBuilding_num(Integer building_num) {
		this.building_num = building_num;
	}

	public Integer getZr_term() {
		return zr_term;
	}

	public void setZr_term(Integer zr_term) {
		this.zr_term = zr_term;
	}

	public Ld_guar_blocks getLd_guar_blocks() {
		return ld_guar_block;
	}

	public void setLd_guar_blocks(Ld_guar_blocks ld_guar_block) {
		this.ld_guar_block = ld_guar_block;
	}

	public Ld_guar_cadastre getLd_guar_cadastre() {
		return ld_guar_cadastre;
	}

	public void setLd_guar_cadastre(Ld_guar_cadastre ld_guar_cadastre) {
		this.ld_guar_cadastre = ld_guar_cadastre;
	}

	public Ld_guar_car getLd_guar_car() {
		return ld_guar_car;
	}

	public void setLd_guar_car(Ld_guar_car ld_guar_car) {
		this.ld_guar_car = ld_guar_car;
	}

	public Ld_guar_equipment getLd_guar_equipment() {
		return ld_guar_equipment;
	}

	public void setLd_guar_equipment(Ld_guar_equipment ld_guar_equipment) {
		this.ld_guar_equipment = ld_guar_equipment;
	}

	public Ld_guar_gold getLd_guar_gold() {
		return ld_guar_gold;
	}

	public void setLd_guar_gold(Ld_guar_gold ld_guar_gold) {
		this.ld_guar_gold = ld_guar_gold;
	}
    
}
