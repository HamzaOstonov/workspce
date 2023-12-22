package com.is.documents;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Document implements Serializable {

    static final long serialVersionUID = 1234567895L;

	private Long id;
	private String branch;
	private String doc_num;
	private Date d_date;
	private String bank_cl;
	private String acc_cl;
	private String name_cl;
	private String inn_cl;
	private String bank_co;
	private String acc_co;
	private String name_co;
	private String inn_co;
	private String purpose_code;
	private String purpose;
	private BigDecimal summa;
	private String currency;
	private String type_doc;
	private Date v_date;
	private String pdc;
	private int state;
	private Long parent_group_id;
	private Long parent_id;
	private int s_deal_id;
	private String cash_code;
	private Long child_id;
	private Long kod_err;
	private String file_name;
	private Long err_general;
	private Long emp_id;
	private Long id_transh;
	private Long id_transh_purp;
	private Date val_date;
	private String state_desc;
	private int is_checked;
	private Long client_id;
	private String username;
	private String sign;
	private String signBody;
	private String delimeter = "@#$^";
	private Long general_id;
	private String error_message;
	private int parent_id_type;
	private String budget_account;
	private String budget_inn;
	private String budget_name;
	private int edit;
	private int inout = 0;
	private int docbank = 0;
	private int copy = 0;
	private int template = 0;
	
    public Document() {
		super();
    }
    
    public Document(Long id, String branch, String doc_num, Date d_date,
			String bank_cl, String acc_cl, String name_cl, String inn_cl,
			String bank_co, String acc_co, String name_co, String inn_co,
			String purpose_code, String purpose, BigDecimal summa, String currency,
			String type_doc, Date v_date, String pdc, int state,
			Long parent_id, Long client_id, String username, String sign,
			Long general_id, String error_message, int parent_id_type,
			String budget_account, String budget_inn, String budget_name, int edit) {
		super();
		this.id = id;
		this.branch = branch;
		this.doc_num = doc_num;
		this.d_date = d_date;
		this.bank_cl = bank_cl;
		this.acc_cl = acc_cl;
		this.name_cl = name_cl;
		this.inn_cl = inn_cl;
		this.bank_co = bank_co;
		this.acc_co = acc_co;
		this.name_co = name_co;
		this.inn_co = inn_co;
		this.purpose_code = purpose_code;
		this.purpose = purpose;
		this.summa = summa;
		this.currency = currency;
		this.type_doc = type_doc;
		this.v_date = v_date;
		this.pdc = pdc;
		this.state = state;
		this.parent_id = parent_id;
		this.client_id = client_id;
		this.username = username;
		this.sign = sign;
		this.general_id = general_id;
		this.error_message = error_message;
		this.is_checked = 0;
		this.parent_id_type = parent_id_type;
		this.budget_account = budget_account;
		this.budget_inn = budget_inn;
		this.budget_name = budget_name;
		this.edit = edit;
	}
    
    public Document(Long id, String branch, String doc_num, Date d_date,
			String bank_cl, String acc_cl, String name_cl, String inn_cl,
			String bank_co, String acc_co, String name_co, String inn_co,
			String purpose_code, String purpose, BigDecimal summa, String currency,
			String type_doc, Date v_date, String pdc, int state,
			Long parent_id, Long client_id, String username, String sign,
			Long general_id, String error_message, int parent_id_type,
			String budget_account, String budget_inn, String budget_name, int edit, String file_name) {
		super();
		this.id = id;
		this.branch = branch;
		this.doc_num = doc_num;
		this.d_date = d_date;
		this.bank_cl = bank_cl;
		this.acc_cl = acc_cl;
		this.name_cl = name_cl;
		this.inn_cl = inn_cl;
		this.bank_co = bank_co;
		this.acc_co = acc_co;
		this.name_co = name_co;
		this.inn_co = inn_co;
		this.purpose_code = purpose_code;
		this.purpose = purpose;
		this.summa = summa;
		this.currency = currency;
		this.type_doc = type_doc;
		this.v_date = v_date;
		this.pdc = pdc;
		this.state = state;
		this.parent_id = parent_id;
		this.client_id = client_id;
		this.username = username;
		this.sign = sign;
		this.general_id = general_id;
		this.error_message = error_message;
		this.is_checked = 0;
		this.parent_id_type = parent_id_type;
		this.budget_account = budget_account;
		this.budget_inn = budget_inn;
		this.budget_name = budget_name;
		this.edit = edit;
		this.file_name = file_name;
	}

	public Document(Long id, String branch, String doc_num, Date d_date,
			String bank_cl, String acc_cl, String name_cl, String inn_cl,
			String bank_co, String acc_co, String name_co, String inn_co,
			String purpose_code, String purpose, BigDecimal summa, String currency,
			String type_doc, Date v_date, String pdc, int state, String state_desc,
			Long parent_group_id, Long parent_id, int s_deal_id,
			String cash_code, Long child_id, Long kod_err, String file_name,
			Long err_general, Long emp_id, Long id_transh, Long id_transh_purp,
			Date val_date, Long client_id, String username, String sign,
			Long general_id, String error_message, int parent_id_type,
			String budget_account, String budget_inn, String budget_name, int edit) {
		super();
		this.id = id;
		this.branch = branch;
		this.doc_num = doc_num;
		this.d_date = d_date;
		this.bank_cl = bank_cl;
		this.acc_cl = acc_cl;
		this.name_cl = name_cl;
		this.inn_cl = inn_cl;
		this.bank_co = bank_co;
		this.acc_co = acc_co;
		this.name_co = name_co;
		this.inn_co = inn_co;
		this.purpose_code = purpose_code;
		this.purpose = purpose;
		this.summa = summa;
		this.currency = currency;
		this.type_doc = type_doc;
		this.v_date = v_date;
		this.pdc = pdc;
		this.state = state;
		this.state_desc = state_desc;
		this.parent_group_id = parent_group_id;
		this.parent_id = parent_id;
		this.s_deal_id = s_deal_id;
		this.cash_code = cash_code;
		this.child_id = child_id;
		this.kod_err = kod_err;
		this.file_name = file_name;
		this.err_general = err_general;
		this.emp_id = emp_id;
		this.id_transh = id_transh;
		this.id_transh_purp = id_transh_purp;
		this.val_date = val_date;
		this.client_id = client_id;
		this.username = username;
		this.sign = sign;
		this.general_id = general_id;
		this.error_message = error_message;
		this.is_checked = 0;
		this.parent_id_type = parent_id_type;
		this.budget_account = budget_account;
		this.budget_inn = budget_inn;
		this.budget_name = budget_name;
		this.edit = edit;
	}
	
	public Document(Long id, String branch, String doc_num, Date d_date,
			String bank_cl, String acc_cl, String name_cl, String inn_cl,
			String bank_co, String acc_co, String name_co, String inn_co,
			String purpose_code, String purpose, BigDecimal summa, String currency,
			String type_doc, Date v_date, String pdc, int state, String state_desc,
			Long parent_group_id, Long parent_id, int s_deal_id,
			String cash_code, Long child_id, Long kod_err, String file_name,
			Long err_general, Long emp_id, Long id_transh, Long id_transh_purp,
			Date val_date, Long client_id, String username, String sign,
			Long general_id, String error_message, int parent_id_type,
			String budget_account, String budget_inn, String budget_name, int edit, int docbank, int inout, int copy, int template) {
		super();
		this.id = id;
		this.branch = branch;
		this.doc_num = doc_num;
		this.d_date = d_date;
		this.bank_cl = bank_cl;
		this.acc_cl = acc_cl;
		this.name_cl = name_cl;
		this.inn_cl = inn_cl;
		this.bank_co = bank_co;
		this.acc_co = acc_co;
		this.name_co = name_co;
		this.inn_co = inn_co;
		this.purpose_code = purpose_code;
		this.purpose = purpose;
		this.summa = summa;
		this.currency = currency;
		this.type_doc = type_doc;
		this.v_date = v_date;
		this.pdc = pdc;
		this.state = state;
		this.state_desc = state_desc;
		this.parent_group_id = parent_group_id;
		this.parent_id = parent_id;
		this.s_deal_id = s_deal_id;
		this.cash_code = cash_code;
		this.child_id = child_id;
		this.kod_err = kod_err;
		this.file_name = file_name;
		this.err_general = err_general;
		this.emp_id = emp_id;
		this.id_transh = id_transh;
		this.id_transh_purp = id_transh_purp;
		this.val_date = val_date;
		this.client_id = client_id;
		this.username = username;
		this.sign = sign;
		this.general_id = general_id;
		this.error_message = error_message;
		this.is_checked = 0;
		this.parent_id_type = parent_id_type;
		this.budget_account = budget_account;
		this.budget_inn = budget_inn;
		this.budget_name = budget_name;
		this.edit = edit;
		this.docbank = docbank;
		this.inout = inout;
		this.copy = copy;
		this.template = template;
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

	public String getInn_cl() {
		return inn_cl;
	}

	public void setInn_cl(String inn_cl) {
		this.inn_cl = inn_cl;
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

	public String getInn_co() {
		return inn_co;
	}

	public void setInn_co(String inn_co) {
		this.inn_co = inn_co;
	}

	public String getPurpose_code() {
		return purpose_code;
	}

	public void setPurpose_code(String purpose_code) {
		this.purpose_code = purpose_code;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public BigDecimal getSumma() {
		return summa;
	}

	public void setSumma(BigDecimal summa) {
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

	public Date getV_date() {
		return v_date;
	}

	public void setV_date(Date v_date) {
		this.v_date = v_date;
	}

	public String getPdc() {
		return pdc;
	}

	public void setPdc(String pdc) {
		this.pdc = pdc;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
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

	public int getS_deal_id() {
		return s_deal_id;
	}

	public void setS_deal_id(int s_deal_id) {
		this.s_deal_id = s_deal_id;
	}

	public String getCash_code() {
		return cash_code;
	}

	public void setCash_code(String cash_code) {
		this.cash_code = cash_code;
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

	public int getIs_checked() {
		return is_checked;
	}

	public void setIs_checked(int is_checked) {
		this.is_checked = is_checked;
	}

	public Long getClient_id() {
		return client_id;
	}

	public void setClient_id(Long client_id) {
		this.client_id = client_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getDelimeter() {
		return delimeter;
	}

	public void setDelimeter(String delimeter) {
		this.delimeter = delimeter;
	}

	public Long getGeneral_id() {
		return general_id;
	}

	public void setGeneral_id(Long general_id) {
		this.general_id = general_id;
	}

	public String getError_message() {
		return error_message;
	}

	public void setError_message(String error_message) {
		this.error_message = error_message;
	}

	public int getParent_id_type() {
		return parent_id_type;
	}

	public void setParent_id_type(int parent_id_type) {
		this.parent_id_type = parent_id_type;
	}

	public String getBudget_account() {
		return budget_account;
	}

	public void setBudget_account(String budget_account) {
		this.budget_account = budget_account;
	}

	public String getBudget_inn() {
		return budget_inn;
	}

	public void setBudget_inn(String budget_inn) {
		this.budget_inn = budget_inn;
	}

	public String getBudget_name() {
		return budget_name;
	}

	public void setBudget_name(String budget_name) {
		this.budget_name = budget_name;
	}

	public int getInout() {
		return inout;
	}

	public void setInout(int inout) {
		this.inout = inout;
	}

	public int getDocbank() {
		return docbank;
	}

	public void setDocbank(int docbank) {
		this.docbank = docbank;
	}

	public int getCopy() {
		return copy;
	}

	public void setCopy(int copy) {
		this.copy = copy;
	}

	public int getTemplate() {
		return template;
	}

	public void setTemplate(int template) {
		this.template = template;
	}

	public String getSignBody() {
		String signBody = 
		doc_num+delimeter+
		d_date+delimeter+
		acc_cl+delimeter+
		bank_co+delimeter+
		acc_co+delimeter+
		name_co+delimeter+
		summa+delimeter+
		purpose_code+purpose+delimeter+
		type_doc;//+delimeter+
		//cash_code+delimeter+
		//str=documentForm.docNum.value+del+documentForm.docDate.value;
        //str+=del+documentForm.clientAccount.value+del+documentForm.corrBank.value;
        //str+=del+documentForm.corrAccount.value+del+documentForm.corrName.value;
        //str+=del+documentForm.summa.value*100+del+documentForm.purpose.value;
        //str+=del+documentForm.docType.value+del+documentForm.cashCode.value;
        //str+=del+documentForm.cashDesc.value;
		return signBody;
	}

	public int getEdit() {
		return edit;
	}

	public void setEdit(int edit) {
		this.edit = edit;
	} 
	
}
