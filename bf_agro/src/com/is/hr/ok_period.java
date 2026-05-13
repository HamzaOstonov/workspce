package com.is.hr;

import java.util.Date;

public class ok_period {

	private int id;
    private String branch;
    private int personal_code;
    private Date in_office_date;
    private Date out_office_date;
    private String office_name;
    private String office_address;
    private String established_post;
    private String motive_out;
    private int type_period_code;
    private int article_code;
    private String basis_num;
    private Date basis_date;
    private int emp_code;
    private Date ins_date;
    private int base_move_code;
    private String established_department;
    private String priz_system;
    private String cod_bank;
    private String cod_type_prn;
    private String cod_pr_off;
    private String pr_off;
    private Date date_utv_km;
    private String numb_utv_km;
    private Date date_pr_kvl;
    private String numb_pr_kvl;
    private Date date_attest;
    private String resh_attest;
    private String doljn_id;
    private String num_pr_off;
    private Date date_pr_off;
    private int post_code;
    private int department_code;
    private String department;
    private int stag_code;
    private String emp_code_name;

    public ok_period() {

    }

    public ok_period( int id, 
    		String branch,
    		int personal_code,
    		Date in_office_date,
    		Date out_office_date,
    		String office_name,
    		String office_address, 
    		String established_post,
    		String motive_out, 
    		int type_period_code, int article_code, String basis_num,
    		Date basis_date, int emp_code, Date ins_date, int base_move_code,
    		String established_department, String priz_system, String cod_bank, String cod_type_prn, 
    		String cod_pr_off, String pr_off, Date date_utv_km, String numb_utv_km, Date date_pr_kvl, String numb_pr_kvl, 
    		Date date_attest, String resh_attest, String doljn_id, String num_pr_off, Date date_pr_off, int post_code,
    		int department_code, String department, int stag_code, String emp_code_name) {

                this.id = id;
                this.branch = branch;
                this.personal_code = personal_code;
                this.in_office_date = in_office_date;
                this.out_office_date = out_office_date;
                this.office_name = office_name;
                this.office_address = office_address;
                this.established_post = established_post;
                this.motive_out = motive_out;
                this.type_period_code = type_period_code;
                this.article_code = article_code;
                this.basis_num = basis_num;
                this.basis_date = basis_date;
                this.emp_code = emp_code;
                this.ins_date = ins_date;
                this.base_move_code = base_move_code;
                this.established_department = established_department;
                this.priz_system = priz_system;
                this.cod_bank = cod_bank;
                this.cod_type_prn = cod_type_prn;
                this.cod_pr_off = cod_pr_off;
                this.pr_off = pr_off;
                this.date_utv_km = date_utv_km;
                this.numb_utv_km = numb_utv_km;
                this.date_pr_kvl = date_pr_kvl;
                this.numb_pr_kvl = numb_pr_kvl;
                this.date_attest = date_attest;
                this.resh_attest = resh_attest;
                this.doljn_id = doljn_id;
                this.num_pr_off = num_pr_off;
                this.date_pr_off = date_pr_off;
                this.post_code = post_code;
                this.department_code = department_code;
                this.department = department;
                this.stag_code = stag_code;
                this.emp_code_name = emp_code_name;
        }

	
		// TODO Auto-generated constructor stub
	

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

	public Date getIn_office_date() {
		return in_office_date;
	}

	public void setIn_office_date(Date in_office_date) {
		this.in_office_date = in_office_date;
	}

	public Date getOut_office_date() {
		return out_office_date;
	}

	public void setOut_office_date(Date out_office_date) {
		this.out_office_date = out_office_date;
	}

	public String getOffice_name() {
		return office_name;
	}

	public void setOffice_name(String office_name) {
		this.office_name = office_name;
	}

	public String getOffice_address() {
		return office_address;
	}

	public void setOffice_address(String office_address) {
		this.office_address = office_address;
	}

	public String getEstablished_post() {
		return established_post;
	}

	public void setEstablished_post(String established_post) {
		this.established_post = established_post;
	}

	public String getMotive_out() {
		return motive_out;
	}

	public void setMotive_out(String motive_out) {
		this.motive_out = motive_out;
	}

	public int getType_period_code() {
		return type_period_code;
	}

	public void setType_period_code(int type_period_code) {
		this.type_period_code = type_period_code;
	}

	public int getArticle_code() {
		return article_code;
	}

	public void setArticle_code(int article_code) {
		this.article_code = article_code;
	}

	public String getBasis_num() {
		return basis_num;
	}

	public void setBasis_num(String basis_num) {
		this.basis_num = basis_num;
	}

	public Date getBasis_date() {
		return basis_date;
	}

	public void setBasis_date(Date basis_date) {
		this.basis_date = basis_date;
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

	public int getBase_move_code() {
		return base_move_code;
	}

	public void setBase_move_code(int base_move_code) {
		this.base_move_code = base_move_code;
	}

	public String getEstablished_department() {
		return established_department;
	}

	public void setEstablished_department(String established_department) {
		this.established_department = established_department;
	}

	public String getPriz_system() {
		return priz_system;
	}

	public void setPriz_system(String priz_system) {
		this.priz_system = priz_system;
	}

	public String getCod_bank() {
		return cod_bank;
	}

	public void setCod_bank(String cod_bank) {
		this.cod_bank = cod_bank;
	}

	public String getCod_type_prn() {
		return cod_type_prn;
	}

	public void setCod_type_prn(String cod_type_prn) {
		this.cod_type_prn = cod_type_prn;
	}

	public String getCod_pr_off() {
		return cod_pr_off;
	}

	public void setCod_pr_off(String cod_pr_off) {
		this.cod_pr_off = cod_pr_off;
	}

	public String getPr_off() {
		return pr_off;
	}

	public void setPr_off(String pr_off) {
		this.pr_off = pr_off;
	}

	public Date getDate_utv_km() {
		return date_utv_km;
	}

	public void setDate_utv_km(Date date_utv_km) {
		this.date_utv_km = date_utv_km;
	}

	public String getNumb_utv_km() {
		return numb_utv_km;
	}

	public void setNumb_utv_km(String numb_utv_km) {
		this.numb_utv_km = numb_utv_km;
	}

	public Date getDate_pr_kvl() {
		return date_pr_kvl;
	}

	public void setDate_pr_kvl(Date date_pr_kvl) {
		this.date_pr_kvl = date_pr_kvl;
	}

	public String getNumb_pr_kvl() {
		return numb_pr_kvl;
	}

	public void setNumb_pr_kvl(String numb_pr_kvl) {
		this.numb_pr_kvl = numb_pr_kvl;
	}

	public Date getDate_attest() {
		return date_attest;
	}

	public void setDate_attest(Date date_attest) {
		this.date_attest = date_attest;
	}

	public String getResh_attest() {
		return resh_attest;
	}

	public void setResh_attest(String resh_attest) {
		this.resh_attest = resh_attest;
	}

	public String getDoljn_id() {
		return doljn_id;
	}

	public void setDoljn_id(String doljn_id) {
		this.doljn_id = doljn_id;
	}

	public String getNum_pr_off() {
		return num_pr_off;
	}

	public void setNum_pr_off(String num_pr_off) {
		this.num_pr_off = num_pr_off;
	}

	public Date getDate_pr_off() {
		return date_pr_off;
	}

	public void setDate_pr_off(Date date_pr_off) {
		this.date_pr_off = date_pr_off;
	}

	public int getPost_code() {
		return post_code;
	}

	public void setPost_code(int post_code) {
		this.post_code = post_code;
	}

	public int getDepartment_code() {
		return department_code;
	}

	public void setDepartment_code(int department_code) {
		this.department_code = department_code;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getStag_code() {
		return stag_code;
	}

	public void setStag_code(int stag_code) {
		this.stag_code = stag_code;
	}

	public String getEmp_code_name() {
		return emp_code_name;
	}

	public void setEmp_code_name(String emp_code_name) {
		this.emp_code_name = emp_code_name;
	}
    


}

