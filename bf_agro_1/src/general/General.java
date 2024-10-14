package general;

import java.sql.Date;

public class General
{
	private long id; //number(20)
	private String branch; //char(5)
	private String doc_num; //varchar2(10)
	private Date d_date; //date
	private String bank_cl; //char(5)
	private String acc_cl; //char(20)
	private String name_cl; //varchar2(80)
	private String bank_co; //char(5)
	private String acc_co; //char(20)
	private String name_co; //varchar2(80)
	private String purpose; //varchar2(175)
	private long summa; //number(20)
	private String currency; //char(3)
	private String type_doc; //char(2)
	private long s_deal_id; //number(6)
	private Date v_date; //date
	private String pdc; //char(1)
	private String cash_code; //varchar2(3)
	private long state; //number(6)
	private long parent_group_id; //number(6)
	private long parent_id; //number(10)
	private long child_id; //number(10)
	private long kod_err; //number(6)
	private String file_name; //varchar2(20)
	private long err_general; //number(6)
	private long emp_id; //number(6)
	private long id_transh; //number(10)
	private long id_transh_purp; //number(10)
	private Date val_date; //date
	private long deal_group_id; //number(10)
	public long getId()
	{
		return id;
	}
	public void setId(long id)
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
	public String getDoc_num()
	{
		return doc_num;
	}
	public void setDoc_num(String doc_num)
	{
		this.doc_num = doc_num;
	}
	public Date getD_date()
	{
		return d_date;
	}
	public void setD_date(Date d_date)
	{
		this.d_date = d_date;
	}
	public String getBank_cl()
	{
		return bank_cl;
	}
	public void setBank_cl(String bank_cl)
	{
		this.bank_cl = bank_cl;
	}
	public String getAcc_cl()
	{
		return acc_cl;
	}
	public void setAcc_cl(String acc_cl)
	{
		this.acc_cl = acc_cl;
	}
	public String getName_cl()
	{
		return name_cl;
	}
	public void setName_cl(String name_cl)
	{
		this.name_cl = name_cl;
	}
	public String getBank_co()
	{
		return bank_co;
	}
	public void setBank_co(String bank_co)
	{
		this.bank_co = bank_co;
	}
	public String getAcc_co()
	{
		return acc_co;
	}
	public void setAcc_co(String acc_co)
	{
		this.acc_co = acc_co;
	}
	public String getName_co()
	{
		return name_co;
	}
	public void setName_co(String name_co)
	{
		this.name_co = name_co;
	}
	public String getPurpose()
	{
		return purpose;
	}
	public void setPurpose(String purpose)
	{
		this.purpose = purpose;
	}
	public long getSumma()
	{
		return summa;
	}
	public void setSumma(long summa)
	{
		this.summa = summa;
	}
	public String getCurrency()
	{
		return currency;
	}
	public void setCurrency(String currency)
	{
		this.currency = currency;
	}
	public String getType_doc()
	{
		return type_doc;
	}
	public void setType_doc(String type_doc)
	{
		this.type_doc = type_doc;
	}
	public long getS_deal_id()
	{
		return s_deal_id;
	}
	public void setS_deal_id(long s_deal_id)
	{
		this.s_deal_id = s_deal_id;
	}
	public Date getV_date()
	{
		return v_date;
	}
	public void setV_date(Date v_date)
	{
		this.v_date = v_date;
	}
	public String getPdc()
	{
		return pdc;
	}
	public void setPdc(String pdc)
	{
		this.pdc = pdc;
	}
	public String getCash_code()
	{
		return cash_code;
	}
	public void setCash_code(String cash_code)
	{
		this.cash_code = cash_code;
	}
	public long getState()
	{
		return state;
	}
	public void setState(long state)
	{
		this.state = state;
	}
	public long getParent_group_id()
	{
		return parent_group_id;
	}
	public void setParent_group_id(long parent_group_id)
	{
		this.parent_group_id = parent_group_id;
	}
	public long getParent_id()
	{
		return parent_id;
	}
	public void setParent_id(long parent_id)
	{
		this.parent_id = parent_id;
	}
	public long getChild_id()
	{
		return child_id;
	}
	public void setChild_id(long child_id)
	{
		this.child_id = child_id;
	}
	public long getKod_err()
	{
		return kod_err;
	}
	public void setKod_err(long kod_err)
	{
		this.kod_err = kod_err;
	}
	public String getFile_name()
	{
		return file_name;
	}
	public void setFile_name(String file_name)
	{
		this.file_name = file_name;
	}
	public long getErr_general()
	{
		return err_general;
	}
	public void setErr_general(long err_general)
	{
		this.err_general = err_general;
	}
	public long getEmp_id()
	{
		return emp_id;
	}
	public void setEmp_id(long emp_id)
	{
		this.emp_id = emp_id;
	}
	public long getId_transh()
	{
		return id_transh;
	}
	public void setId_transh(long id_transh)
	{
		this.id_transh = id_transh;
	}
	public long getId_transh_purp()
	{
		return id_transh_purp;
	}
	public void setId_transh_purp(long id_transh_purp)
	{
		this.id_transh_purp = id_transh_purp;
	}
	public Date getVal_date()
	{
		return val_date;
	}
	public void setVal_date(Date val_date)
	{
		this.val_date = val_date;
	}
	public long getDeal_group_id()
	{
		return deal_group_id;
	}
	public void setDeal_group_id(long deal_group_id)
	{
		this.deal_group_id = deal_group_id;
	}
	public General(long id, String branch, String doc_num, Date d_date,
			String bank_cl, String acc_cl, String name_cl, String bank_co,
			String acc_co, String name_co, String purpose, long summa,
			String currency, String type_doc, long s_deal_id, Date v_date,
			String pdc, String cash_code, long state, long parent_group_id,
			long parent_id, long child_id, long kod_err, String file_name,
			long err_general, long emp_id, long id_transh, long id_transh_purp,
			Date val_date, Long deal_group_id)
	{
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
		this.v_date = v_date;
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
		this.deal_group_id = deal_group_id;
	}
	public General()
	{
		super();
	}
}
