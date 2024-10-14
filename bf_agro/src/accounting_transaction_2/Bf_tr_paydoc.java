package accounting_transaction_2;

import java.util.Date;

public class Bf_tr_paydoc
{
	private Long id;
	private Long pay_id;
	private String branch;
	private Date d_date;
	private String bank_cl;
	private String acc_cl;
	private String name_cl;
	private String bank_co;
	private String acc_co;
	private String name_co;
	private Long summa;
	private String purpose;
	private String type_doc;
	private String pdc;
	private Long parent_group_id;
	private Long parent_id;
	private String cash_code;
	private Long id_transh_purp;
	private String schema_name;
	private Long ord;
	private String g_branch;
	private Long g_docid;
	private String purp_code;
	private Long pay_type;
	private Long trans_type;
	private Long acc_dt_id;
	private Long acc_ct_id;
	private Long deal_group_id;
	private Long deal_id;
	private Integer parent_deal_id;
	
	public Long getId()
	{
		return id;
	}
	public void setId(Long id)
	{
		this.id = id;
	}
	public Long getPay_id()
	{
		return pay_id;
	}
	public void setPay_id(Long pay_id)
	{
		this.pay_id = pay_id;
	}
	public String getBranch()
	{
		return branch;
	}
	public void setBranch(String branch)
	{
		this.branch = branch;
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
	public Long getSumma()
	{
		return summa;
	}
	public void setSumma(Long summa)
	{
		this.summa = summa;
	}
	public String getPurpose()
	{
		return purpose;
	}
	public void setPurpose(String purpose)
	{
		this.purpose = purpose;
	}
	public String getType_doc()
	{
		return type_doc;
	}
	public void setType_doc(String type_doc)
	{
		this.type_doc = type_doc;
	}
	public String getPdc()
	{
		return pdc;
	}
	public void setPdc(String pdc)
	{
		this.pdc = pdc;
	}
	public Long getParent_group_id()
	{
		return parent_group_id;
	}
	public void setParent_group_id(Long parent_group_id)
	{
		this.parent_group_id = parent_group_id;
	}
	public Long getParent_id()
	{
		return parent_id;
	}
	public void setParent_id(Long parent_id)
	{
		this.parent_id = parent_id;
	}
	public String getCash_code()
	{
		return cash_code;
	}
	public void setCash_code(String cash_code)
	{
		this.cash_code = cash_code;
	}
	public Long getId_transh_purp()
	{
		return id_transh_purp;
	}
	public void setId_transh_purp(Long id_transh_purp)
	{
		this.id_transh_purp = id_transh_purp;
	}
	public String getSchema_name()
	{
		return schema_name;
	}
	public void setSchema_name(String schema_name)
	{
		this.schema_name = schema_name;
	}
	public Long getOrd()
	{
		return ord;
	}
	public void setOrd(Long ord)
	{
		this.ord = ord;
	}
	public String getG_branch()
	{
		return g_branch;
	}
	public void setG_branch(String g_branch)
	{
		this.g_branch = g_branch;
	}
	public Long getG_docid()
	{
		return g_docid;
	}
	public void setG_docid(Long g_docid)
	{
		this.g_docid = g_docid;
	}
	public String getPurp_code()
	{
		return purp_code;
	}
	public void setPurp_code(String purp_code)
	{
		this.purp_code = purp_code;
	}
	public Long getPay_type()
	{
		return pay_type;
	}
	public void setPay_type(Long pay_type)
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
	public Long getAcc_dt_id()
	{
		return acc_dt_id;
	}
	public void setAcc_dt_id(Long acc_dt_id)
	{
		this.acc_dt_id = acc_dt_id;
	}
	public Long getAcc_ct_id()
	{
		return acc_ct_id;
	}
	public void setAcc_ct_id(Long acc_ct_id)
	{
		this.acc_ct_id = acc_ct_id;
	}
	public Long getDeal_group_id()
	{
		return deal_group_id;
	}
	public void setDeal_group_id(Long deal_group_id)
	{
		this.deal_group_id = deal_group_id;
	}
	public Long getDeal_id()
	{
		return deal_id;
	}
	public void setDeal_id(Long deal_id)
	{
		this.deal_id = deal_id;
	}
	public Integer getParent_deal_id()
	{
		return parent_deal_id;
	}
	public void setParent_deal_id(Integer parent_deal_id)
	{
		this.parent_deal_id = parent_deal_id;
	}
	
	public Bf_tr_paydoc(Long id, Long pay_id, String branch, Date d_date,
			String bank_cl, String acc_cl, String name_cl, String bank_co,
			String acc_co, String name_co, Long summa, String purpose,
			String type_doc, String pdc, Long parent_group_id, Long parent_id,
			String cash_code, Long id_transh_purp, String schema_name,
			Long ord, String g_branch, Long g_docid, String purp_code,
			Long pay_type, Long trans_type, Long acc_dt_id, Long acc_ct_id,
			Long deal_group_id, Long deal_id, Integer parent_deal_id)
	{
		super();
		this.id = id;
		this.pay_id = pay_id;
		this.branch = branch;
		this.d_date = d_date;
		this.bank_cl = bank_cl;
		this.acc_cl = acc_cl;
		this.name_cl = name_cl;
		this.bank_co = bank_co;
		this.acc_co = acc_co;
		this.name_co = name_co;
		this.summa = summa;
		this.purpose = purpose;
		this.type_doc = type_doc;
		this.pdc = pdc;
		this.parent_group_id = parent_group_id;
		this.parent_id = parent_id;
		this.cash_code = cash_code;
		this.id_transh_purp = id_transh_purp;
		this.schema_name = schema_name;
		this.ord = ord;
		this.g_branch = g_branch;
		this.g_docid = g_docid;
		this.purp_code = purp_code;
		this.pay_type = pay_type;
		this.trans_type = trans_type;
		this.acc_dt_id = acc_dt_id;
		this.acc_ct_id = acc_ct_id;
		this.deal_group_id = deal_group_id;
		this.deal_id = deal_id;
		this.parent_deal_id = parent_deal_id;
	}
	
	public Bf_tr_paydoc()
	{
		super();
	}
	@Override
	public String toString() {
		return "Bf_tr_paydoc [\nid=" + id + ", pay_id=" + pay_id + ", branch=" + branch + ", d_date=" + d_date
				+ ", bank_cl=" + bank_cl + ", acc_cl=" + acc_cl + ", name_cl=" + name_cl + ", bank_co=" + bank_co
				+ ", acc_co=" + acc_co + ", name_co=" + name_co + ", summa=" + summa + ", purpose=" + purpose
				+ ", type_doc=" + type_doc + ", pdc=" + pdc + ", parent_group_id=" + parent_group_id + ", parent_id="
				+ parent_id + ", cash_code=" + cash_code + ", id_transh_purp=" + id_transh_purp + ", schema_name="
				+ schema_name + ", ord=" + ord + ", g_branch=" + g_branch + ", g_docid=" + g_docid + ", purp_code="
				+ purp_code + ", pay_type=" + pay_type + ", trans_type=" + trans_type + ", acc_dt_id=" + acc_dt_id
				+ ", acc_ct_id=" + acc_ct_id + ", deal_group_id=" + deal_group_id + ", deal_id=" + deal_id
				+ ", parent_deal_id=" + parent_deal_id + "\n]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((acc_cl == null) ? 0 : acc_cl.hashCode());
		result = prime * result + ((acc_co == null) ? 0 : acc_co.hashCode());
		result = prime * result + ((acc_ct_id == null) ? 0 : acc_ct_id.hashCode());
		result = prime * result + ((acc_dt_id == null) ? 0 : acc_dt_id.hashCode());
		result = prime * result + ((bank_cl == null) ? 0 : bank_cl.hashCode());
		result = prime * result + ((bank_co == null) ? 0 : bank_co.hashCode());
		result = prime * result + ((branch == null) ? 0 : branch.hashCode());
		result = prime * result + ((cash_code == null) ? 0 : cash_code.hashCode());
		result = prime * result + ((d_date == null) ? 0 : d_date.hashCode());
		result = prime * result + ((deal_group_id == null) ? 0 : deal_group_id.hashCode());
		result = prime * result + ((deal_id == null) ? 0 : deal_id.hashCode());
		result = prime * result + ((g_branch == null) ? 0 : g_branch.hashCode());
		result = prime * result + ((g_docid == null) ? 0 : g_docid.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((id_transh_purp == null) ? 0 : id_transh_purp.hashCode());
		result = prime * result + ((name_cl == null) ? 0 : name_cl.hashCode());
		result = prime * result + ((name_co == null) ? 0 : name_co.hashCode());
		result = prime * result + ((ord == null) ? 0 : ord.hashCode());
		result = prime * result + ((parent_deal_id == null) ? 0 : parent_deal_id.hashCode());
		result = prime * result + ((parent_group_id == null) ? 0 : parent_group_id.hashCode());
		result = prime * result + ((parent_id == null) ? 0 : parent_id.hashCode());
		result = prime * result + ((pay_id == null) ? 0 : pay_id.hashCode());
		result = prime * result + ((pay_type == null) ? 0 : pay_type.hashCode());
		result = prime * result + ((pdc == null) ? 0 : pdc.hashCode());
		result = prime * result + ((purp_code == null) ? 0 : purp_code.hashCode());
		result = prime * result + ((purpose == null) ? 0 : purpose.hashCode());
		result = prime * result + ((schema_name == null) ? 0 : schema_name.hashCode());
		result = prime * result + ((summa == null) ? 0 : summa.hashCode());
		result = prime * result + ((trans_type == null) ? 0 : trans_type.hashCode());
		result = prime * result + ((type_doc == null) ? 0 : type_doc.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bf_tr_paydoc other = (Bf_tr_paydoc) obj;
		if (acc_cl == null) {
			if (other.acc_cl != null)
				return false;
		} else if (!acc_cl.equals(other.acc_cl))
			return false;
		if (acc_co == null) {
			if (other.acc_co != null)
				return false;
		} else if (!acc_co.equals(other.acc_co))
			return false;
		if (acc_ct_id == null) {
			if (other.acc_ct_id != null)
				return false;
		} else if (!acc_ct_id.equals(other.acc_ct_id))
			return false;
		if (acc_dt_id == null) {
			if (other.acc_dt_id != null)
				return false;
		} else if (!acc_dt_id.equals(other.acc_dt_id))
			return false;
		if (bank_cl == null) {
			if (other.bank_cl != null)
				return false;
		} else if (!bank_cl.equals(other.bank_cl))
			return false;
		if (bank_co == null) {
			if (other.bank_co != null)
				return false;
		} else if (!bank_co.equals(other.bank_co))
			return false;
		if (branch == null) {
			if (other.branch != null)
				return false;
		} else if (!branch.equals(other.branch))
			return false;
		if (cash_code == null) {
			if (other.cash_code != null)
				return false;
		} else if (!cash_code.equals(other.cash_code))
			return false;
		if (d_date == null) {
			if (other.d_date != null)
				return false;
		} else if (!d_date.equals(other.d_date))
			return false;
		if (deal_group_id == null) {
			if (other.deal_group_id != null)
				return false;
		} else if (!deal_group_id.equals(other.deal_group_id))
			return false;
		if (deal_id == null) {
			if (other.deal_id != null)
				return false;
		} else if (!deal_id.equals(other.deal_id))
			return false;
		if (g_branch == null) {
			if (other.g_branch != null)
				return false;
		} else if (!g_branch.equals(other.g_branch))
			return false;
		if (g_docid == null) {
			if (other.g_docid != null)
				return false;
		} else if (!g_docid.equals(other.g_docid))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (id_transh_purp == null) {
			if (other.id_transh_purp != null)
				return false;
		} else if (!id_transh_purp.equals(other.id_transh_purp))
			return false;
		if (name_cl == null) {
			if (other.name_cl != null)
				return false;
		} else if (!name_cl.equals(other.name_cl))
			return false;
		if (name_co == null) {
			if (other.name_co != null)
				return false;
		} else if (!name_co.equals(other.name_co))
			return false;
		if (ord == null) {
			if (other.ord != null)
				return false;
		} else if (!ord.equals(other.ord))
			return false;
		if (parent_deal_id == null) {
			if (other.parent_deal_id != null)
				return false;
		} else if (!parent_deal_id.equals(other.parent_deal_id))
			return false;
		if (parent_group_id == null) {
			if (other.parent_group_id != null)
				return false;
		} else if (!parent_group_id.equals(other.parent_group_id))
			return false;
		if (parent_id == null) {
			if (other.parent_id != null)
				return false;
		} else if (!parent_id.equals(other.parent_id))
			return false;
		if (pay_id == null) {
			if (other.pay_id != null)
				return false;
		} else if (!pay_id.equals(other.pay_id))
			return false;
		if (pay_type == null) {
			if (other.pay_type != null)
				return false;
		} else if (!pay_type.equals(other.pay_type))
			return false;
		if (pdc == null) {
			if (other.pdc != null)
				return false;
		} else if (!pdc.equals(other.pdc))
			return false;
		if (purp_code == null) {
			if (other.purp_code != null)
				return false;
		} else if (!purp_code.equals(other.purp_code))
			return false;
		if (purpose == null) {
			if (other.purpose != null)
				return false;
		} else if (!purpose.equals(other.purpose))
			return false;
		if (schema_name == null) {
			if (other.schema_name != null)
				return false;
		} else if (!schema_name.equals(other.schema_name))
			return false;
		if (summa == null) {
			if (other.summa != null)
				return false;
		} else if (!summa.equals(other.summa))
			return false;
		if (trans_type == null) {
			if (other.trans_type != null)
				return false;
		} else if (!trans_type.equals(other.trans_type))
			return false;
		if (type_doc == null) {
			if (other.type_doc != null)
				return false;
		} else if (!type_doc.equals(other.type_doc))
			return false;
		return true;
	}
	
	
}
