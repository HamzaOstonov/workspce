package com.is.sd_books.model;

import java.math.BigDecimal;
import java.sql.Date;

public class Circulate {

	private String branch;
	private Double id;
	private long book_id;
	private Date oper_date;
	private Double turn_code;
	private Double circulate;
	private BigDecimal saldo;
	private BigDecimal prc_circulate;
	private BigDecimal prc_saldo;
	private Double pc;
	private Long general_id;
	private int emp;
	private String type_calc;
	private Date bank_date;
	private Double purpose_id;
	private String decision_id;
	private Double group_id;
	private int fx_code;
	private Double schedule_id;
	private String note;
	private String lead_filial;
	private Double user_id;
	private Double out_id;
	private int is_print_shah;
	private int is_print_omon;
	private Double id_sd_clerk;
	private Double id_clerk_real;
	private BigDecimal circulate_cr;
	private BigDecimal circulate_db;
	private BigDecimal prc_circulate_cr;
	private BigDecimal prc_circulate_db;
	private String clerk_name;

	public Circulate() {

	}

	public Circulate(String branch, Double id, long book_id,
			Date oper_date, Double turn_code, Double circulate,
			BigDecimal saldo, BigDecimal prc_circulate, BigDecimal prc_saldo,
			Double pc, Long general_id, int emp, String type_calc,
			Date bank_date, Double purpose_id, String decision_id,
			Double group_id, int fx_code, Double schedule_id, String note,
			String lead_filial, Double user_id, Double out_id,
			int is_print_shah, int is_print_omon, Double id_sd_clerk,
			Double id_clerk_real) {

		this.branch = branch;
		this.id = id;
		this.book_id = book_id;
		this.oper_date = oper_date;
		this.turn_code = turn_code;
		this.circulate = circulate;
		this.saldo = saldo;
		this.prc_circulate = prc_circulate;
		this.prc_saldo = prc_saldo;
		this.pc = pc;
		this.general_id = general_id;
		this.emp = emp;
		this.type_calc = type_calc;
		this.bank_date = bank_date;
		this.purpose_id = purpose_id;
		this.decision_id = decision_id;
		this.group_id = group_id;
		this.fx_code = fx_code;
		this.schedule_id = schedule_id;
		this.note = note;
		this.lead_filial = lead_filial;
		this.user_id = user_id;
		this.out_id = out_id;
		this.is_print_shah = is_print_shah;
		this.is_print_omon = is_print_omon;
		this.id_sd_clerk = id_sd_clerk;
		this.id_clerk_real = id_clerk_real;
	}

	public Circulate(String branch, Double id, long book_id,
			Date oper_date, Double pc, BigDecimal circulate_cr,
			BigDecimal circulate_db, BigDecimal saldo,
			BigDecimal prc_circulate_cr, BigDecimal prc_circulate_db,
			BigDecimal prc_saldo, Long general_id, int emp, String type_calc,
			Date bank_date, String decision_id, Double group_id, String note,
			int fx_code, String lead_filial, Double user_id, String clerk_name) {

		this.branch = branch;
		this.id = id;
		this.book_id = book_id;
		this.oper_date = oper_date;
		this.pc = pc;
		this.circulate_cr = circulate_cr;
		this.circulate_db = circulate_db;
		this.saldo = saldo;
		this.prc_circulate_cr = prc_circulate_cr;
		this.prc_circulate_db = prc_circulate_db;
		this.prc_saldo = prc_saldo;
		this.general_id = general_id;
		this.emp = emp;
		this.type_calc = type_calc;
		this.bank_date = bank_date;
		this.decision_id = decision_id;
		this.group_id = group_id;
		this.note = note;
		this.fx_code = fx_code;
		this.lead_filial = lead_filial;
		this.user_id = user_id;
		this.clerk_name = clerk_name;
	}

	public BigDecimal getCirculate_cr() {
		return circulate_cr;
	}

	public void setCirculate_cr(BigDecimal circulate_cr) {
		this.circulate_cr = circulate_cr;
	}

	public BigDecimal getCirculate_db() {
		return circulate_db;
	}

	public void setCirculate_db(BigDecimal circulate_db) {
		this.circulate_db = circulate_db;
	}

	public BigDecimal getPrc_circulate_cr() {
		return prc_circulate_cr;
	}

	public void setPrc_circulate_cr(BigDecimal prc_circulate_cr) {
		this.prc_circulate_cr = prc_circulate_cr;
	}

	public BigDecimal getPrc_circulate_db() {
		return prc_circulate_db;
	}

	public void setPrc_circulate_db(BigDecimal prc_circulate_db) {
		this.prc_circulate_db = prc_circulate_db;
	}

	public String getClerk_name() {
		return clerk_name;
	}

	public void setClerk_name(String clerk_name) {
		this.clerk_name = clerk_name;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public Double getId() {
		return id;
	}

	public void setId(Double id) {
		this.id = id;
	}

	public long getBook_id() {
		return book_id;
	}

	public void setBook_id(long book_id) {
		this.book_id = book_id;
	}

	public Date getOper_date() {
		return oper_date;
	}

	public void setOper_date(Date oper_date) {
		this.oper_date = oper_date;
	}

	public Double getTurn_code() {
		return turn_code;
	}

	public void setTurn_code(Double turn_code) {
		this.turn_code = turn_code;
	}

	public Double getCirculate() {
		return circulate;
	}

	public void setCirculate(Double circulate) {
		this.circulate = circulate;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public BigDecimal getPrc_circulate() {
		return prc_circulate;
	}

	public void setPrc_circulate(BigDecimal prc_circulate) {
		this.prc_circulate = prc_circulate;
	}

	public BigDecimal getPrc_saldo() {
		return prc_saldo;
	}

	public void setPrc_saldo(BigDecimal prc_saldo) {
		this.prc_saldo = prc_saldo;
	}

	public Double getPc() {
		return pc;
	}

	public void setPc(Double pc) {
		this.pc = pc;
	}

	public Long getGeneral_id() {
		return general_id;
	}

	public void setGeneral_id(Long general_id) {
		this.general_id = general_id;
	}

	public int getEmp() {
		return emp;
	}

	public void setEmp(int emp) {
		this.emp = emp;
	}

	public String getType_calc() {
		return type_calc;
	}

	public void setType_calc(String type_calc) {
		this.type_calc = type_calc;
	}

	public Date getBank_date() {
		return bank_date;
	}

	public void setBank_date(Date bank_date) {
		this.bank_date = bank_date;
	}

	public Double getPurpose_id() {
		return purpose_id;
	}

	public void setPurpose_id(Double purpose_id) {
		this.purpose_id = purpose_id;
	}

	public String getDecision_id() {
		return decision_id;
	}

	public void setDecision_id(String decision_id) {
		this.decision_id = decision_id;
	}

	public Double getGroup_id() {
		return group_id;
	}

	public void setGroup_id(Double group_id) {
		this.group_id = group_id;
	}

	public int getFx_code() {
		return fx_code;
	}

	public void setFx_code(int fx_code) {
		this.fx_code = fx_code;
	}

	public Double getSchedule_id() {
		return schedule_id;
	}

	public void setSchedule_id(Double schedule_id) {
		this.schedule_id = schedule_id;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getLead_filial() {
		return lead_filial;
	}

	public void setLead_filial(String lead_filial) {
		this.lead_filial = lead_filial;
	}

	public Double getUser_id() {
		return user_id;
	}

	public void setUser_id(Double user_id) {
		this.user_id = user_id;
	}

	public Double getOut_id() {
		return out_id;
	}

	public void setOut_id(Double out_id) {
		this.out_id = out_id;
	}

	public int getIs_print_shah() {
		return is_print_shah;
	}

	public void setIs_print_shah(int is_print_shah) {
		this.is_print_shah = is_print_shah;
	}

	public int getIs_print_omon() {
		return is_print_omon;
	}

	public void setIs_print_omon(int is_print_omon) {
		this.is_print_omon = is_print_omon;
	}

	public Double getId_sd_clerk() {
		return id_sd_clerk;
	}

	public void setId_sd_clerk(Double id_sd_clerk) {
		this.id_sd_clerk = id_sd_clerk;
	}

	public Double getId_clerk_real() {
		return id_clerk_real;
	}

	public void setId_clerk_real(Double id_clerk_real) {
		this.id_clerk_real = id_clerk_real;
	}

}
