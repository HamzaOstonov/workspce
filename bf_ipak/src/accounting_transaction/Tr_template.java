package accounting_transaction;

public class Tr_template
{
	long id;
	long operation_id;
	long acc_dt;
	long acc_ct;
	String currency;
	String doc_type;
	String cash_code;
	String purpose;
	String purpose_code;
	long ord;
	String id_transh_purp;
	long pay_type;
	long trans_type;
	double perc_for_tr;
	String pdc;
	String amount;
	
	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	public long getOperation_id()
	{
		return operation_id;
	}
	public void setOperation_id(long operation_id)
	{
		this.operation_id = operation_id;
	}
	public long getAcc_dt()
	{
		return acc_dt;
	}
	public void setAcc_dt(long acc_dt)
	{
		this.acc_dt = acc_dt;
	}
	public long getAcc_ct()
	{
		return acc_ct;
	}
	public void setAcc_ct(long acc_ct)
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
	public long getOrd()
	{
		return ord;
	}
	public void setOrd(long ord)
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
	public long getPay_type()
	{
		return pay_type;
	}
	public void setPay_type(long pay_type)
	{
		this.pay_type = pay_type;
	}
	public long getTrans_type()
	{
		return trans_type;
	}
	public void setTrans_type(long trans_type)
	{
		this.trans_type = trans_type;
	}
	public double getPerc_for_tr()
	{
		return perc_for_tr;
	}
	public void setPerc_for_tr(double perc_for_tr)
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
	public String getAmount()
	{
		return amount;
	}
	public void setAmount(String amount)
	{
		this.amount = amount;
	}
	
	public Tr_template(long id, long operation_id, long acc_dt, long acc_ct,
			String currency, String doc_type, String cash_code, String purpose,
			String purpose_code, long ord, String id_transh_purp, long pay_type,
			long trans_type, double perc_for_tr, String pdc, String amount)
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
		this.amount = amount;
	}
	
	public Tr_template()
	{
		super();
	}

    @Override
    public String toString() {
        return "Tr_template{" +
                "id=" + id +
                ", operation_id=" + operation_id +
                ", acc_dt=" + acc_dt +
                ", acc_ct=" + acc_ct +
                ", currency='" + currency + '\'' +
                ", doc_type='" + doc_type + '\'' +
                ", cash_code='" + cash_code + '\'' +
                ", purpose='" + purpose + '\'' +
                ", purpose_code='" + purpose_code + '\'' +
                ", ord=" + ord +
                ", id_transh_purp='" + id_transh_purp + '\'' +
                ", pay_type=" + pay_type +
                ", trans_type=" + trans_type +
                ", perc_for_tr=" + perc_for_tr +
                ", pdc='" + pdc + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }
}
