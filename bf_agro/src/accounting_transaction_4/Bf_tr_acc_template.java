package accounting_transaction_4;

public class Bf_tr_acc_template
{
	private Long id;
	private String acc_name;
	private String acc_mask;
	private Long acc_type;
	
	public Long getId()
	{
		return id;
	}
	public void setId(Long id)
	{
		this.id = id;
	}
	public String getAcc_name()
	{
		return acc_name;
	}
	public void setAcc_name(String acc_name)
	{
		this.acc_name = acc_name;
	}
	public String getAcc_mask()
	{
		return acc_mask;
	}
	public void setAcc_mask(String acc_mask)
	{
		this.acc_mask = acc_mask;
	}
	public Long getAcc_type()
	{
		return acc_type;
	}
	public void setAcc_type(Long acc_type)
	{
		this.acc_type = acc_type;
	}
	
	public Bf_tr_acc_template(Long id, String acc_name, String acc_mask,
			Long acc_type)
	{
		super();
		this.id = id;
		this.acc_name = acc_name;
		this.acc_mask = acc_mask;
		this.acc_type = acc_type;
	}
	
	public Bf_tr_acc_template()
	{
		super();
	}
}
