package accounting_transaction_2;

import java.util.Date;

public class Bf_tr_pay
{
	private Long id;
	private String branch;
	private Long operation_id;
	private Long amount;
	private String card_acc;
	private String cur_acc;
	private Date date_created;
	private Long parent_group_id;
	private Long state;
	private String account_no;
	private String cl_name;
	private Long emp_id;
	private Long tieto_type;
	private String pan;
	private Long deal_group;
	private Long deal_id;
	private String doc_num;
	private Long eqv_amount;
	private Long sub_id;
	private Long amount_t;
	private String subbranch_id;
	
	public Long getId()
	{
		return id;
	}
	public void setId(Long id)
	{
		this.id = id;
	}
	public String getBranch()
	{
		return branch;
	}
	public void setBranch(String branch)
	{
		this.branch = branch;
	}
	public Long getOperation_id()
	{
		return operation_id;
	}
	public void setOperation_id(Long operation_id)
	{
		this.operation_id = operation_id;
	}
	public Long getAmount()
	{
		return amount;
	}
	public void setAmount(Long amount)
	{
		this.amount = amount;
	}
	public String getCard_acc()
	{
		return card_acc;
	}
	public void setCard_acc(String card_acc)
	{
		this.card_acc = card_acc;
	}
	public String getCur_acc()
	{
		return cur_acc;
	}
	public void setCur_acc(String cur_acc)
	{
		this.cur_acc = cur_acc;
	}
	public Date getDate_created()
	{
		return date_created;
	}
	public void setDate_created(Date date_created)
	{
		this.date_created = date_created;
	}
	public Long getParent_group_id()
	{
		return parent_group_id;
	}
	public void setParent_group_id(Long parent_group_id)
	{
		this.parent_group_id = parent_group_id;
	}
	public Long getState()
	{
		return state;
	}
	public void setState(Long state)
	{
		this.state = state;
	}
	public String getAccount_no()
	{
		return account_no;
	}
	public void setAccount_no(String account_no)
	{
		this.account_no = account_no;
	}
	public String getCl_name()
	{
		return cl_name;
	}
	public void setCl_name(String cl_name)
	{
		this.cl_name = cl_name;
	}
	public Long getEmp_id()
	{
		return emp_id;
	}
	public void setEmp_id(Long emp_id)
	{
		this.emp_id = emp_id;
	}
	public Long getTieto_type()
	{
		return tieto_type;
	}
	public void setTieto_type(Long tieto_type)
	{
		this.tieto_type = tieto_type;
	}
	public String getPan()
	{
		return pan;
	}
	public void setPan(String pan)
	{
		this.pan = pan;
	}
	public Long getDeal_group()
	{
		return deal_group;
	}
	public void setDeal_group(Long deal_group)
	{
		this.deal_group = deal_group;
	}
	public Long getDeal_id()
	{
		return deal_id;
	}
	public void setDeal_id(Long deal_id)
	{
		this.deal_id = deal_id;
	}
	public String getDoc_num()
	{
		return doc_num;
	}
	public void setDoc_num(String doc_num)
	{
		this.doc_num = doc_num;
	}
	public Long getEqv_amount()
	{
		return eqv_amount;
	}
	public void setEqv_amount(Long eqv_amount)
	{
		this.eqv_amount = eqv_amount;
	}
	public Long getSub_id()
	{
		return sub_id;
	}
	public void setSub_id(Long sub_id)
	{
		this.sub_id = sub_id;
	}
	public Long getAmount_t()
	{
		return amount_t;
	}
	public void setAmount_t(Long amount_t)
	{
		this.amount_t = amount_t;
	}
	public String getSubbranch_id()
	{
		return subbranch_id;
	}
	public void setSubbranch_id(String subbranch_id)
	{
		this.subbranch_id = subbranch_id;
	}
	
	public Bf_tr_pay(Long id, String branch, Long operation_id, Long amount,
			String card_acc, String cur_acc, Date date_created,
			Long parent_group_id, Long state, String account_no,
			String cl_name, Long emp_id, Long tieto_type, String pan,
			Long deal_group, Long deal_id, String doc_num, Long eqv_amount,
			Long sub_id, Long amount_t, String subbranch_id)
	{
		super();
		this.id = id;
		this.branch = branch;
		this.operation_id = operation_id;
		this.amount = amount;
		this.card_acc = card_acc;
		this.cur_acc = cur_acc;
		this.date_created = date_created;
		this.parent_group_id = parent_group_id;
		this.state = state;
		this.account_no = account_no;
		this.cl_name = cl_name;
		this.emp_id = emp_id;
		this.tieto_type = tieto_type;
		this.pan = pan;
		this.deal_group = deal_group;
		this.deal_id = deal_id;
		this.doc_num = doc_num;
		this.eqv_amount = eqv_amount;
		this.sub_id = sub_id;
		this.amount_t = amount_t;
		this.subbranch_id = subbranch_id;
	}
	
	public Bf_tr_pay()
	{
		super();
	}
}
