package accounting_transaction_4;

public class Bf_tr_acc implements Cloneable
{
	private Long id;
	private String branch;
	private Long acc_template_id;
	private String acc_mfo;
	private String account;
	private String acc_name;
	private String acc_name_and_inn;
	
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
	public Long getAcc_template_id()
	{
		return acc_template_id;
	}
	public void setAcc_template_id(Long acc_template_id)
	{
		this.acc_template_id = acc_template_id;
	}
	public String getAcc_mfo()
	{
		return acc_mfo;
	}
	public void setAcc_mfo(String acc_mfo)
	{
		this.acc_mfo = acc_mfo;
	}
	public String getAccount()
	{
		return account;
	}
	public void setAccount(String account)
	{
		this.account = account;
	}
	public String getAcc_name()
	{
		return acc_name;
	}
	public void setAcc_name(String acc_name)
	{
		this.acc_name = acc_name;
	}
	public String getAcc_name_and_inn()
	{
		return acc_name_and_inn;
	}
	public void setAcc_name_and_inn(String acc_name_and_inn)
	{
		this.acc_name_and_inn = acc_name_and_inn;
	}
	
	public Bf_tr_acc(Long id, String branch, Long acc_template_id,
			String acc_mfo, String account, String acc_name,
			String acc_name_and_inn)
	{
		super();
		this.id = id;
		this.branch = branch;
		this.acc_template_id = acc_template_id;
		this.acc_mfo = acc_mfo;
		this.account = account;
		this.acc_name = acc_name;
		this.acc_name_and_inn = acc_name_and_inn;
	}
	
	public Bf_tr_acc()
	{
		super();
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException
	{
		return super.clone();
	}
}
