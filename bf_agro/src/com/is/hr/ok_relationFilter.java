package com.is.hr;

import java.util.Date;

public class ok_relationFilter {
	
	private int id;
    private String branch;
    private int personal_code;
    private int relation_code;
    private String relation_family;
    private String relation_name;
    private String relation_patronymic;
    private int relation_birthday;
    private String relation_birthplace;
    private String relation_office;
    private String relation_post;
    private String relation_home_address;
    private int emp_code;
    private Date   ins_date;
    private int relation_deathday;
    private String cod_str_live;
    private String cod_obl_live;
    private String cod_obl_live_prim;
    private String cod_city;
    private String cod_city_prim;
    private String cod_str_live_prim;
    private String cod_str_birth;
    private String cod_str_birth_prim;
    private String cod_obl_birth;
    private String cod_obl_birth_prim;
    private String cod_city_birth;
    private String cod_city_birth_prim;
    private int dd;
    private int mm;
    private int dd_death;
    private int mm_death;
    private String emp_code_name;
    
    public ok_relationFilter() {

    }

	public ok_relationFilter(int id, String branch, int personal_code, int relation_code, String relation_family,
			String relation_name, String relation_patronymic, int relation_birthday, String relation_birthplace,
			String relation_office, String relation_post, String relation_home_address, int emp_code, Date ins_date,
			int relation_deathday, String cod_str_live, String cod_obl_live, String cod_obl_live_prim, String cod_city,
			String cod_city_prim, String cod_str_live_prim, String cod_str_birth, String cod_str_birth_prim,
			String cod_obl_birth, String cod_obl_birth_prim, String cod_city_birth, String cod_city_birth_prim, int dd,
			int mm, int dd_death, int mm_death, String emp_code_name) {
		super();
		this.id = id;
		this.branch = branch;
		this.personal_code = personal_code;
		this.relation_code = relation_code;
		this.relation_family = relation_family;
		this.relation_name = relation_name;
		this.relation_patronymic = relation_patronymic;
		this.relation_birthday = relation_birthday;
		this.relation_birthplace = relation_birthplace;
		this.relation_office = relation_office;
		this.relation_post = relation_post;
		this.relation_home_address = relation_home_address;
		this.emp_code = emp_code;
		this.ins_date = ins_date;
		this.relation_deathday = relation_deathday;
		this.cod_str_live = cod_str_live;
		this.cod_obl_live = cod_obl_live;
		this.cod_obl_live_prim = cod_obl_live_prim;
		this.cod_city = cod_city;
		this.cod_city_prim = cod_city_prim;
		this.cod_str_live_prim = cod_str_live_prim;
		this.cod_str_birth = cod_str_birth;
		this.cod_str_birth_prim = cod_str_birth_prim;
		this.cod_obl_birth = cod_obl_birth;
		this.cod_obl_birth_prim = cod_obl_birth_prim;
		this.cod_city_birth = cod_city_birth;
		this.cod_city_birth_prim = cod_city_birth_prim;
		this.dd = dd;
		this.mm = mm;
		this.dd_death = dd_death;
		this.mm_death = mm_death;
		this.emp_code_name = emp_code_name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public int getPersonal_code() {
		return personal_code;
	}

	public void setPersonal_code(int personal_code) {
		this.personal_code = personal_code;
	}

	public int getRelation_code() {
		return relation_code;
	}

	public void setRelation_code(int relation_code) {
		this.relation_code = relation_code;
	}

	public String getRelation_family() {
		return relation_family;
	}

	public void setRelation_family(String relation_family) {
		this.relation_family = relation_family;
	}

	public String getRelation_name() {
		return relation_name;
	}

	public void setRelation_name(String relation_name) {
		this.relation_name = relation_name;
	}

	public String getRelation_patronymic() {
		return relation_patronymic;
	}

	public void setRelation_patronymic(String relation_patronymic) {
		this.relation_patronymic = relation_patronymic;
	}

	public int getRelation_birthday() {
		return relation_birthday;
	}

	public void setRelation_birthday(int relation_birthday) {
		this.relation_birthday = relation_birthday;
	}

	public String getRelation_birthplace() {
		return relation_birthplace;
	}

	public void setRelation_birthplace(String relation_birthplace) {
		this.relation_birthplace = relation_birthplace;
	}

	public String getRelation_office() {
		return relation_office;
	}

	public void setRelation_office(String relation_office) {
		this.relation_office = relation_office;
	}

	public String getRelation_post() {
		return relation_post;
	}

	public void setRelation_post(String relation_post) {
		this.relation_post = relation_post;
	}

	public String getRelation_home_address() {
		return relation_home_address;
	}

	public void setRelation_home_address(String relation_home_address) {
		this.relation_home_address = relation_home_address;
	}

	public int getEmp_code() {
		return emp_code;
	}

	public void setEmp_code(int emp_code) {
		this.emp_code = emp_code;
	}

	public Date getIns_date() {
		return ins_date;
	}

	public void setIns_date(Date ins_date) {
		this.ins_date = ins_date;
	}

	public int getRelation_deathday() {
		return relation_deathday;
	}

	public void setRelation_deathday(int relation_deathday) {
		this.relation_deathday = relation_deathday;
	}

	public String getCod_str_live() {
		return cod_str_live;
	}

	public void setCod_str_live(String cod_str_live) {
		this.cod_str_live = cod_str_live;
	}

	public String getCod_obl_live() {
		return cod_obl_live;
	}

	public void setCod_obl_live(String cod_obl_live) {
		this.cod_obl_live = cod_obl_live;
	}

	public String getCod_obl_live_prim() {
		return cod_obl_live_prim;
	}

	public void setCod_obl_live_prim(String cod_obl_live_prim) {
		this.cod_obl_live_prim = cod_obl_live_prim;
	}

	public String getCod_city() {
		return cod_city;
	}

	public void setCod_city(String cod_city) {
		this.cod_city = cod_city;
	}

	public String getCod_city_prim() {
		return cod_city_prim;
	}

	public void setCod_city_prim(String cod_city_prim) {
		this.cod_city_prim = cod_city_prim;
	}

	public String getCod_str_live_prim() {
		return cod_str_live_prim;
	}

	public void setCod_str_live_prim(String cod_str_live_prim) {
		this.cod_str_live_prim = cod_str_live_prim;
	}

	public String getCod_str_birth() {
		return cod_str_birth;
	}

	public void setCod_str_birth(String cod_str_birth) {
		this.cod_str_birth = cod_str_birth;
	}

	public String getCod_str_birth_prim() {
		return cod_str_birth_prim;
	}

	public void setCod_str_birth_prim(String cod_str_birth_prim) {
		this.cod_str_birth_prim = cod_str_birth_prim;
	}

	public String getCod_obl_birth() {
		return cod_obl_birth;
	}

	public void setCod_obl_birth(String cod_obl_birth) {
		this.cod_obl_birth = cod_obl_birth;
	}

	public String getCod_obl_birth_prim() {
		return cod_obl_birth_prim;
	}

	public void setCod_obl_birth_prim(String cod_obl_birth_prim) {
		this.cod_obl_birth_prim = cod_obl_birth_prim;
	}

	public String getCod_city_birth() {
		return cod_city_birth;
	}

	public void setCod_city_birth(String cod_city_birth) {
		this.cod_city_birth = cod_city_birth;
	}

	public String getCod_city_birth_prim() {
		return cod_city_birth_prim;
	}

	public void setCod_city_birth_prim(String cod_city_birth_prim) {
		this.cod_city_birth_prim = cod_city_birth_prim;
	}

	public int getDd() {
		return dd;
	}

	public void setDd(int dd) {
		this.dd = dd;
	}

	public int getMm() {
		return mm;
	}

	public void setMm(int mm) {
		this.mm = mm;
	}

	public int getDd_death() {
		return dd_death;
	}

	public void setDd_death(int dd_death) {
		this.dd_death = dd_death;
	}

	public int getMm_death() {
		return mm_death;
	}

	public void setMm_death(int mm_death) {
		this.mm_death = mm_death;
	}

	public String getEmp_code_name() {
		return emp_code_name;
	}

	public void setEmp_code_name(String emp_code_name) {
		this.emp_code_name = emp_code_name;
	}

	
    
    
    

}
