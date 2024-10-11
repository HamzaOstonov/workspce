package accounting_transactions_new;

public class Bf_tr_paytype
{
	private long id;
	private String name;
	private long deal_group_id;
	
	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public long getDeal_group_id()
	{
		return deal_group_id;
	}
	public void setDeal_group_id(long deal_group_id)
	{
		this.deal_group_id = deal_group_id;
	}
	
	public Bf_tr_paytype(long id, String name, long deal_group_id)
	{
		super();
		this.id = id;
		this.name = name;
		this.deal_group_id = deal_group_id;
	}
	
	public Bf_tr_paytype()
	{
		super();
	}
}
