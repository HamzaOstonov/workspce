package com.is.sd_books.model;

import java.util.Date;

public class SD_BookFilter {

	private String branch;
	private Long id;
	private String open_date;
	private String name;
	private Long client_id;
	private String client_code;
	private String resident_code;
	private String pass_type;
	private String pass_ser;
	private String pass_num;
	private String pass_reg;
	private Date pass_date;
	private Date born_date;
	private String address;
	private Date ins_date;
	private Double emp_code;
	private Double out_id;
	private String filial;
	private String dep;
	private Double num;
	private Double b_id;
	private String type_calc;
	private Date date_open;
	private Date date_close;
	private Double saldo;
	private Long state;
	private int state_id;
	private String account;
	private String type_calc_show;
	private int regime;
	private String code_citizenship;
	private String birth_place;
	private String code_adr_region;
	private String code_adr_distr;
	private String phone_home;
	private String phone_mobile;
	private String dog_num;
	private Date dog_dat;
	private String type_document;
	private String name_pas;
	private Date passport_date_expiration;
	private Date deadline;
	private String is_need_book;
	private String p_ser;
	private String p_num;
	private String p_st_name;
	private String first_code;
	private String last_code;

	public SD_BookFilter() {

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

	public String getOpen_date() {
		return open_date;
	}

	public void setOpen_date(String open_date) {
		this.open_date = open_date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getClient_id() {
		return client_id;
	}

	public void setClient_id(Long client_id) {
		this.client_id = client_id;
	}

	public String getClient_code() {
		return client_code;
	}

	public void setClient_code(String client_code) {
		this.client_code = client_code;
	}

	public String getResident_code() {
		return resident_code;
	}

	public void setResident_code(String resident_code) {
		this.resident_code = resident_code;
	}

	public String getPass_type() {
		return pass_type;
	}
	
	public void clearFilterFields(){
		this.branch= null;
		this.id = null;
		this.open_date = null;
		this.name = null;
		this.client_id = null;
		this.resident_code = null;
		this.filial = null;
		this.dep = null;
		this.num = null;
		this.account = null;
		this.date_open = null;
		this.date_close = null;
		this.state = null;		
		
	}

	public void setPass_type(String pass_type) {
		this.pass_type = pass_type;
	}

	public String getPass_ser() {
		return pass_ser;
	}

	public void setPass_ser(String pass_ser) {
		this.pass_ser = pass_ser;
	}

	public String getPass_num() {
		return pass_num;
	}

	public void setPass_num(String pass_num) {
		this.pass_num = pass_num;
	}

	public String getPass_reg() {
		return pass_reg;
	}

	public void setPass_reg(String pass_reg) {
		this.pass_reg = pass_reg;
	}

	public Date getPass_date() {
		return pass_date;
	}

	public void setPass_date(Date pass_date) {
		this.pass_date = pass_date;
	}

	public Date getBorn_date() {
		return born_date;
	}

	public void setBorn_date(Date born_date) {
		this.born_date = born_date;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getIns_date() {
		return ins_date;
	}

	public void setIns_date(Date ins_date) {
		this.ins_date = ins_date;
	}

	public Double getEmp_code() {
		return emp_code;
	}

	public void setEmp_code(Double emp_code) {
		this.emp_code = emp_code;
	}

	public Double getOut_id() {
		return out_id;
	}

	public void setOut_id(Double out_id) {
		this.out_id = out_id;
	}

	public String getFilial() {
		return filial;
	}

	public void setFilial(String filial) {
		this.filial = filial;
	}

	public String getDep() {
		return dep;
	}

	public void setDep(String dep) {
		this.dep = dep;
	}

	public Double getNum() {
		return num;
	}

	public void setNum(Double num) {
		this.num = num;
	}

	public Double getB_id() {
		return b_id;
	}

	public void setB_id(Double b_id) {
		this.b_id = b_id;
	}

	public String getType_calc() {
		return type_calc;
	}

	public void setType_calc(String type_calc) {
		this.type_calc = type_calc;
	}

	public Date getDate_open() {
		return date_open;
	}

	public void setDate_open(Date date_open) {
		this.date_open = date_open;
	}

	public Date getDate_close() {
		return date_close;
	}

	public void setDate_close(Date date_close) {
		this.date_close = date_close;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public Long getState() {
		return state;
	}

	public void setState(Long state) {
		this.state = state;
	}

	public int getState_id() {
		return state_id;
	}

	public void setState_id(int state_id) {
		this.state_id = state_id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getType_calc_show() {
		return type_calc_show;
	}

	public void setType_calc_show(String type_calc_show) {
		this.type_calc_show = type_calc_show;
	}

	public int getRegime() {
		return regime;
	}

	public void setRegime(int regime) {
		this.regime = regime;
	}

	public String getCode_citizenship() {
		return code_citizenship;
	}

	public void setCode_citizenship(String code_citizenship) {
		this.code_citizenship = code_citizenship;
	}

	public String getBirth_place() {
		return birth_place;
	}

	public void setBirth_place(String birth_place) {
		this.birth_place = birth_place;
	}

	public String getCode_adr_region() {
		return code_adr_region;
	}

	public void setCode_adr_region(String code_adr_region) {
		this.code_adr_region = code_adr_region;
	}

	public String getCode_adr_distr() {
		return code_adr_distr;
	}

	public void setCode_adr_distr(String code_adr_distr) {
		this.code_adr_distr = code_adr_distr;
	}

	public String getPhone_home() {
		return phone_home;
	}

	public void setPhone_home(String phone_home) {
		this.phone_home = phone_home;
	}

	public String getPhone_mobile() {
		return phone_mobile;
	}

	public void setPhone_mobile(String phone_mobile) {
		this.phone_mobile = phone_mobile;
	}

	public String getDog_num() {
		return dog_num;
	}

	public void setDog_num(String dog_num) {
		this.dog_num = dog_num;
	}

	public Date getDog_dat() {
		return dog_dat;
	}

	public void setDog_dat(Date dog_dat) {
		this.dog_dat = dog_dat;
	}

	public String getType_document() {
		return type_document;
	}

	public void setType_document(String type_document) {
		this.type_document = type_document;
	}

	public String getName_pas() {
		return name_pas;
	}

	public void setName_pas(String name_pas) {
		this.name_pas = name_pas;
	}

	public Date getPassport_date_expiration() {
		return passport_date_expiration;
	}

	public void setPassport_date_expiration(Date passport_date_expiration) {
		this.passport_date_expiration = passport_date_expiration;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public String getIs_need_book() {
		return is_need_book;
	}

	public void setIs_need_book(String is_need_book) {
		this.is_need_book = is_need_book;
	}

	public String getP_ser() {
		return p_ser;
	}

	public void setP_ser(String p_ser) {
		this.p_ser = p_ser;
	}

	public String getP_num() {
		return p_num;
	}

	public void setP_num(String p_num) {
		this.p_num = p_num;
	}

	public String getP_st_name() {
		return p_st_name;
	}

	public void setP_st_name(String p_st_name) {
		this.p_st_name = p_st_name;
	}

	public String getFirst_code() {
		return first_code;
	}

	public void setFirst_code(String first_code) {
		this.first_code = first_code;
	}

	public String getLast_code() {
		return last_code;
	}

	public void setLast_code(String last_code) {
		this.last_code = last_code;
	}

	@Override
	public String toString() {
		return "BookFilter ["
				+ (branch != null ? "branch=" + branch + ", " : "")
				+ (id != null ? "id=" + id + ", " : "")
				+ (open_date != null ? "open_date=" + open_date + ", " : "")
				+ (name != null ? "name=" + name + ", " : "")
				+ (client_id != null ? "client_id=" + client_id + ", " : "")
				+ (client_code != null ? "client_code=" + client_code + ", "
						: "")
				+ (resident_code != null ? "resident_code=" + resident_code
						+ ", " : "")
				+ (pass_type != null ? "pass_type=" + pass_type + ", " : "")
				+ (pass_ser != null ? "pass_ser=" + pass_ser + ", " : "")
				+ (pass_num != null ? "pass_num=" + pass_num + ", " : "")
				+ (pass_reg != null ? "pass_reg=" + pass_reg + ", " : "")
				+ (pass_date != null ? "pass_date=" + pass_date + ", " : "")
				+ (born_date != null ? "born_date=" + born_date + ", " : "")
				+ (address != null ? "address=" + address + ", " : "")
				+ (ins_date != null ? "ins_date=" + ins_date + ", " : "")
				+ (emp_code != null ? "emp_code=" + emp_code + ", " : "")
				+ (out_id != null ? "out_id=" + out_id + ", " : "")
				+ (filial != null ? "filial=" + filial + ", " : "")
				+ (dep != null ? "dep=" + dep + ", " : "")
				+ (num != null ? "num=" + num + ", " : "")
				+ (b_id != null ? "b_id=" + b_id + ", " : "")
				+ (type_calc != null ? "type_calc=" + type_calc + ", " : "")
				+ (date_open != null ? "date_open=" + date_open + ", " : "")
				+ (date_close != null ? "date_close=" + date_close + ", " : "")
				+ (saldo != null ? "saldo=" + saldo + ", " : "")
				+ (state != null ? "state=" + state + ", " : "")
				+ "state_id="
				+ state_id
				+ ", "
				+ (account != null ? "account=" + account + ", " : "")
				+ (type_calc_show != null ? "type_calc_show=" + type_calc_show
						+ ", " : "")
				+ "regime="
				+ regime
				+ ", "
				+ (code_citizenship != null ? "code_citizenship="
						+ code_citizenship + ", " : "")
				+ (birth_place != null ? "birth_place=" + birth_place + ", "
						: "")
				+ (code_adr_region != null ? "code_adr_region="
						+ code_adr_region + ", " : "")
				+ (code_adr_distr != null ? "code_adr_distr=" + code_adr_distr
						+ ", " : "")
				+ (phone_home != null ? "phone_home=" + phone_home + ", " : "")
				+ (phone_mobile != null ? "phone_mobile=" + phone_mobile + ", "
						: "")
				+ (dog_num != null ? "dog_num=" + dog_num + ", " : "")
				+ (dog_dat != null ? "dog_dat=" + dog_dat + ", " : "")
				+ (type_document != null ? "type_document=" + type_document
						+ ", " : "")
				+ (name_pas != null ? "name_pas=" + name_pas + ", " : "")
				+ (passport_date_expiration != null ? "passport_date_expiration="
						+ passport_date_expiration + ", "
						: "")
				+ (deadline != null ? "deadline=" + deadline + ", " : "")
				+ (is_need_book != null ? "is_need_book=" + is_need_book + ", "
						: "") + (p_ser != null ? "p_ser=" + p_ser + ", " : "")
				+ (p_num != null ? "p_num=" + p_num + ", " : "")
				+ (p_st_name != null ? "p_st_name=" + p_st_name + ", " : "")
				+ (first_code != null ? "first_code=" + first_code + ", " : "")
				+ (last_code != null ? "last_code=" + last_code : "") + "]";
	}

}
