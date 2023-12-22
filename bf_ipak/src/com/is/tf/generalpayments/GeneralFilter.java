package com.is.tf.generalpayments;
import java.io.Serializable;
import java.util.Date;

public class GeneralFilter implements Serializable {

    static final long serialVersionUID = 3145245851474522L;

	private Long id;
	private String branch;
	private String doc_num;
	private Date d_date;
	private String bank_cl;
	private String acc_cl;
	private String name_cl;
	private String bank_co;
	private String acc_co;
	private String name_co;
	private String purpose;
	private Long summa;
	private String currency;
	private String type_doc;
	private int s_deal_id;
	private Date v_date_from;
	private Date v_date_to;
	private String pdc;
	private String cash_code;
	private Long state;
	private Long parent_group_id;
	private Long parent_id;
	private Long child_id;
	private Long kod_err;
	private String file_name;
	private Long err_general;
	private Long emp_id;
	private Long id_transh;
	private Long id_transh_purp;
	private Date val_date;
	private String state_desc;

    public GeneralFilter() {
		super();
    }
    
	public GeneralFilter(Long id, String branch, String doc_num, Date d_date,
			String bank_cl, String acc_cl, String name_cl, String bank_co,
			String acc_co, String name_co, String purpose, Long summa,
			String currency, String type_doc, int s_deal_id, Date v_date_from,
			Date v_date_to, String pdc, String cash_code, Long state,
			Long parent_group_id, Long parent_id, Long child_id, Long kod_err,
			String file_name, Long err_general, Long emp_id, Long id_transh,
			Long id_transh_purp, Date val_date, String state_desc) {
		super();
		this.id = id;
		this.branch = branch;
		this.doc_num = doc_num;
		this.d_date = d_date;
		this.bank_cl = bank_cl;
		this.acc_cl = acc_cl;
		this.name_cl = name_cl;
		this.bank_co = bank_co;
		this.acc_co = acc_co;
		this.name_co = name_co;
		this.purpose = purpose;
		this.summa = summa;
		this.currency = currency;
		this.type_doc = type_doc;
		this.s_deal_id = s_deal_id;
		this.v_date_from = v_date_from;
		this.v_date_to = v_date_to;
		this.pdc = pdc;
		this.cash_code = cash_code;
		this.state = state;
		this.parent_group_id = parent_group_id;
		this.parent_id = parent_id;
		this.child_id = child_id;
		this.kod_err = kod_err;
		this.file_name = file_name;
		this.err_general = err_general;
		this.emp_id = emp_id;
		this.id_transh = id_transh;
		this.id_transh_purp = id_transh_purp;
		this.val_date = val_date;
		this.state_desc = state_desc;
	}

	public Long getId() { 
		return id;
	} 

	public void setId(Long id) { 
		this.id = id;
	} 

	public String getBranch() { 
		return branch;
	} 

	public void setBranch(String branch) { 
		this.branch = branch;
	} 

	public String getDoc_num() { 
		return doc_num;
	} 

	public void setDoc_num(String doc_num) { 
		this.doc_num = doc_num;
	} 

	public Date getD_date() { 
		return d_date;
	} 

	public void setD_date(Date d_date) { 
		this.d_date = d_date;
	} 

	public String getBank_cl() { 
		return bank_cl;
	} 

	public void setBank_cl(String bank_cl) { 
		this.bank_cl = bank_cl;
	} 

	public String getAcc_cl() { 
		return acc_cl;
	} 

	public void setAcc_cl(String acc_cl) { 
		this.acc_cl = acc_cl;
	} 

	public String getName_cl() { 
		return name_cl;
	} 

	public void setName_cl(String name_cl) { 
		this.name_cl = name_cl;
	} 

	public String getBank_co() { 
		return bank_co;
	} 

	public void setBank_co(String bank_co) { 
		this.bank_co = bank_co;
	} 

	public String getAcc_co() { 
		return acc_co;
	} 

	public void setAcc_co(String acc_co) { 
		this.acc_co = acc_co;
	} 

	public String getName_co() { 
		return name_co;
	} 

	public void setName_co(String name_co) { 
		this.name_co = name_co;
	} 

	public String getPurpose() { 
		return purpose;
	} 

	public void setPurpose(String purpose) { 
		this.purpose = purpose;
	} 

	public Long getSumma() { 
		return summa;
	} 

	public void setSumma(Long summa) { 
		this.summa = summa;
	} 

	public String getCurrency() { 
		return currency;
	} 

	public void setCurrency(String currency) { 
		this.currency = currency;
	} 

	public String getType_doc() { 
		return type_doc;
	} 

	public void setType_doc(String type_doc) { 
		this.type_doc = type_doc;
	} 

	public int getS_deal_id() { 
		return s_deal_id;
	} 

	public void setS_deal_id(int s_deal_id) { 
		this.s_deal_id = s_deal_id;
	} 

	public Date getV_date_from() {
		return v_date_from;
	}

	public void setV_date_from(Date v_date_from) {
		this.v_date_from = v_date_from;
	}

	public Date getV_date_to() {
		return v_date_to;
	}

	public void setV_date_to(Date v_date_to) {
		this.v_date_to = v_date_to;
	}

	public String getPdc() { 
		return pdc;
	} 

	public void setPdc(String pdc) { 
		this.pdc = pdc;
	} 

	public String getCash_code() { 
		return cash_code;
	} 

	public void setCash_code(String cash_code) { 
		this.cash_code = cash_code;
	} 

	public Long getState() { 
		return state;
	} 

	public void setState(Long state) { 
		this.state = state;
	} 

	public Long getParent_group_id() { 
		return parent_group_id;
	} 

	public void setParent_group_id(Long parent_group_id) { 
		this.parent_group_id = parent_group_id;
	} 

	public Long getParent_id() { 
		return parent_id;
	} 

	public void setParent_id(Long parent_id) { 
		this.parent_id = parent_id;
	} 

	public Long getChild_id() { 
		return child_id;
	} 

	public void setChild_id(Long child_id) { 
		this.child_id = child_id;
	} 

	public Long getKod_err() { 
		return kod_err;
	} 

	public void setKod_err(Long kod_err) { 
		this.kod_err = kod_err;
	} 

	public String getFile_name() { 
		return file_name;
	} 

	public void setFile_name(String file_name) { 
		this.file_name = file_name;
	} 

	public Long getErr_general() { 
		return err_general;
	} 

	public void setErr_general(Long err_general) { 
		this.err_general = err_general;
	} 

	public Long getEmp_id() { 
		return emp_id;
	} 

	public void setEmp_id(Long emp_id) { 
		this.emp_id = emp_id;
	} 

	public Long getId_transh() { 
		return id_transh;
	} 

	public void setId_transh(Long id_transh) { 
		this.id_transh = id_transh;
	} 

	public Long getId_transh_purp() { 
		return id_transh_purp;
	} 

	public void setId_transh_purp(Long id_transh_purp) { 
		this.id_transh_purp = id_transh_purp;
	} 

	public Date getVal_date() { 
		return val_date;
	} 

	public void setVal_date(Date val_date) { 
		this.val_date = val_date;
	} 

	public String getState_desc() { 
		return state_desc;
	} 

	public void setState_desc(String state_desc) { 
		this.state_desc = state_desc;
	}

}
