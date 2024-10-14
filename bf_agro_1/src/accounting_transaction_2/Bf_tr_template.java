package accounting_transaction_2;

public class Bf_tr_template
{
	private Long id;
	private Long operation_id;
	private Long acc_dt;
	private Long acc_ct;
	private String currency;
	private String doc_type;
	private String cash_code;
	private String purpose;
	private String purpose_code;
	private Integer ord;
	private String id_transh_purp;
	private Integer pay_type;
	private Long trans_type;
	private Long perc_for_tr;
	private String pdc;
	private Integer rounding_type;
	private String amount;
	
	public Long getId()
	{
		return id;
	}
	public void setId(Long id)
	{
		this.id = id;
	}
	public Long getOperation_id()
	{
		return operation_id;
	}
	public void setOperation_id(Long operation_id)
	{
		this.operation_id = operation_id;
	}
	public Long getAcc_dt()
	{
		return acc_dt;
	}
	public void setAcc_dt(Long acc_dt)
	{
		this.acc_dt = acc_dt;
	}
	public Long getAcc_ct()
	{
		return acc_ct;
	}
	public void setAcc_ct(Long acc_ct)
	{
		this.acc_ct = acc_ct;
	}
	public String getCurrency()
	{
		return currency;
	}
	public void setCurrency(String currency)
	{
		this.currency = currency;
	}
	public String getDoc_type()
	{
		return doc_type;
	}
	public void setDoc_type(String doc_type)
	{
		this.doc_type = doc_type;
	}
	public String getCash_code()
	{
		return cash_code;
	}
	public void setCash_code(String cash_code)
	{
		this.cash_code = cash_code;
	}
	public String getPurpose()
	{
		return purpose;
	}
	public void setPurpose(String purpose)
	{
		this.purpose = purpose;
	}
	public String getPurpose_code()
	{
		return purpose_code;
	}
	public void setPurpose_code(String purpose_code)
	{
		this.purpose_code = purpose_code;
	}
	public Integer getOrd()
	{
		return ord;
	}
	public void setOrd(Integer ord)
	{
		this.ord = ord;
	}
	public String getId_transh_purp()
	{
		return id_transh_purp;
	}
	public void setId_transh_purp(String id_transh_purp)
	{
		this.id_transh_purp = id_transh_purp;
	}
	public Integer getPay_type()
	{
		return pay_type;
	}
	public void setPay_type(Integer pay_type)
	{
		this.pay_type = pay_type;
	}
	public Long getTrans_type()
	{
		return trans_type;
	}
	public void setTrans_type(Long trans_type)
	{
		this.trans_type = trans_type;
	}
	public Long getPerc_for_tr()
	{
		return perc_for_tr;
	}
	public void setPerc_for_tr(Long perc_for_tr)
	{
		this.perc_for_tr = perc_for_tr;
	}
	public String getPdc()
	{
		return pdc;
	}
	public void setPdc(String pdc)
	{
		this.pdc = pdc;
	}
	public Integer getRounding_type()
	{
		return rounding_type;
	}
	public void setRounding_type(Integer rounding_type)
	{
		this.rounding_type = rounding_type;
	}
	public String getAmount()
	{
		return amount;
	}
	public void setAmount(String amount)
	{
		this.amount = amount;
	}
	
	public Bf_tr_template(Long id, Long operation_id, Long acc_dt, Long acc_ct,
			String currency, String doc_type, String cash_code, String purpose,
			String purpose_code, Integer ord, String id_transh_purp,
			Integer pay_type, Long trans_type, Long perc_for_tr, String pdc,
			Integer rounding_type, String amount)
	{
		super();
		this.id = id;
		this.operation_id = operation_id;
		this.acc_dt = acc_dt;
		this.acc_ct = acc_ct;
		this.currency = currency;
		this.doc_type = doc_type;
		this.cash_code = cash_code;
		this.purpose = purpose;
		this.purpose_code = purpose_code;
		this.ord = ord;
		this.id_transh_purp = id_transh_purp;
		this.pay_type = pay_type;
		this.trans_type = trans_type;
		this.perc_for_tr = perc_for_tr;
		this.pdc = pdc;
		this.rounding_type = rounding_type;
		this.amount = amount;
	}
	
	public Bf_tr_template()
	{
		super();
	}
}
