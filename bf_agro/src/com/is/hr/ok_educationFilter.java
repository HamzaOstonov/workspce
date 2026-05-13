package com.is.hr;

import java.util.Date;

public class ok_educationFilter {
	
	private int id;
    private String branch;
    private int personal_code;
    private int education_code;
    private int basis_code;
    private int institution_code;
    private int begin_date;
    private int end_date;
    private String profession_personal;
    private int qualification_code;
    private String diplom_num;
    private int emp_code;
    private Date ins_date;
    private String cod_vuz_prim;
    private String curs;
    private String fakultet;
    private String nostra;
    private String nostra_series;
    private String nostra_number;
    private Date nostra_date;
    private Date diplom_date;
    private int begin_date_mm;
    private int begin_date_dd;
    private int end_date_mm;
    private int end_date_dd;
    private int education_end;
    private String education_city;
    private int education_count_code;
    private int vid_education_code;
    private String emp_code_name;


    public ok_educationFilter() {

    }


	public ok_educationFilter(int id, String branch, int personal_code, int education_code, int basis_code,
			int institution_code, int begin_date, int end_date, String profession_personal, int qualification_code,
			String diplom_num, int emp_code, Date ins_date, String cod_vuz_prim, String curs, String fakultet,
			String nostra, String nostra_series, String nostra_number, Date nostra_date, Date diplom_date,
			int begin_date_mm, int begin_date_dd, int end_date_mm, int end_date_dd, int education_end,
			String education_city, int education_count_code, int vid_education_code, String emp_code_name) {
		super();
		this.id = id;
		this.branch = branch;
		this.personal_code = personal_code;
		this.education_code = education_code;
		this.basis_code = basis_code;
		this.institution_code = institution_code;
		this.begin_date = begin_date;
		this.end_date = end_date;
		this.profession_personal = profession_personal;
		this.qualification_code = qualification_code;
		this.diplom_num = diplom_num;
		this.emp_code = emp_code;
		this.ins_date = ins_date;
		this.cod_vuz_prim = cod_vuz_prim;
		this.curs = curs;
		this.fakultet = fakultet;
		this.nostra = nostra;
		this.nostra_series = nostra_series;
		this.nostra_number = nostra_number;
		this.nostra_date = nostra_date;
		this.diplom_date = diplom_date;
		this.begin_date_mm = begin_date_mm;
		this.begin_date_dd = begin_date_dd;
		this.end_date_mm = end_date_mm;
		this.end_date_dd = end_date_dd;
		this.education_end = education_end;
		this.education_city = education_city;
		this.education_count_code = education_count_code;
		this.vid_education_code = vid_education_code;
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


	public int getEducation_code() {
		return education_code;
	}


	public void setEducation_code(int education_code) {
		this.education_code = education_code;
	}


	public int getBasis_code() {
		return basis_code;
	}


	public void setBasis_code(int basis_code) {
		this.basis_code = basis_code;
	}


	public int getInstitution_code() {
		return institution_code;
	}


	public void setInstitution_code(int institution_code) {
		this.institution_code = institution_code;
	}


	public int getBegin_date() {
		return begin_date;
	}


	public void setBegin_date(int begin_date) {
		this.begin_date = begin_date;
	}


	public int getEnd_date() {
		return end_date;
	}


	public void setEnd_date(int end_date) {
		this.end_date = end_date;
	}


	public String getProfession_personal() {
		return profession_personal;
	}


	public void setProfession_personal(String profession_personal) {
		this.profession_personal = profession_personal;
	}


	public int getQualification_code() {
		return qualification_code;
	}


	public void setQualification_code(int qualification_code) {
		this.qualification_code = qualification_code;
	}


	public String getDiplom_num() {
		return diplom_num;
	}


	public void setDiplom_num(String diplom_num) {
		this.diplom_num = diplom_num;
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


	public String getCod_vuz_prim() {
		return cod_vuz_prim;
	}


	public void setCod_vuz_prim(String cod_vuz_prim) {
		this.cod_vuz_prim = cod_vuz_prim;
	}


	public String getCurs() {
		return curs;
	}


	public void setCurs(String curs) {
		this.curs = curs;
	}


	public String getFakultet() {
		return fakultet;
	}


	public void setFakultet(String fakultet) {
		this.fakultet = fakultet;
	}


	public String getNostra() {
		return nostra;
	}


	public void setNostra(String nostra) {
		this.nostra = nostra;
	}


	public String getNostra_series() {
		return nostra_series;
	}


	public void setNostra_series(String nostra_series) {
		this.nostra_series = nostra_series;
	}


	public String getNostra_number() {
		return nostra_number;
	}


	public void setNostra_number(String nostra_number) {
		this.nostra_number = nostra_number;
	}


	public Date getNostra_date() {
		return nostra_date;
	}


	public void setNostra_date(Date nostra_date) {
		this.nostra_date = nostra_date;
	}


	public Date getDiplom_date() {
		return diplom_date;
	}


	public void setDiplom_date(Date diplom_date) {
		this.diplom_date = diplom_date;
	}


	public int getBegin_date_mm() {
		return begin_date_mm;
	}


	public void setBegin_date_mm(int begin_date_mm) {
		this.begin_date_mm = begin_date_mm;
	}


	public int getBegin_date_dd() {
		return begin_date_dd;
	}


	public void setBegin_date_dd(int begin_date_dd) {
		this.begin_date_dd = begin_date_dd;
	}


	public int getEnd_date_mm() {
		return end_date_mm;
	}


	public void setEnd_date_mm(int end_date_mm) {
		this.end_date_mm = end_date_mm;
	}


	public int getEnd_date_dd() {
		return end_date_dd;
	}


	public void setEnd_date_dd(int end_date_dd) {
		this.end_date_dd = end_date_dd;
	}


	public int getEducation_end() {
		return education_end;
	}


	public void setEducation_end(int education_end) {
		this.education_end = education_end;
	}


	public String getEducation_city() {
		return education_city;
	}


	public void setEducation_city(String education_city) {
		this.education_city = education_city;
	}


	public int getEducation_count_code() {
		return education_count_code;
	}


	public void setEducation_count_code(int education_count_code) {
		this.education_count_code = education_count_code;
	}


	public int getVid_education_code() {
		return vid_education_code;
	}


	public void setVid_education_code(int vid_education_code) {
		this.vid_education_code = vid_education_code;
	}


	public String getEmp_code_name() {
		return emp_code_name;
	}


	public void setEmp_code_name(String emp_code_name) {
		this.emp_code_name = emp_code_name;
	}

    

}
