package universal_reader.nodes_classes;

public class Res
{
	private String value;
	private String action;
	private String message;
	
	public Res(String value, String action, String message)
	{
		super();
		this.value = value;
		this.action = action;
		this.message = message;
	}
	public String getValue()
	{
		return value;
	}
	public void setValue(String value)
	{
		this.value = value;
	}
	public String getAction()
	{
		return action;
	}
	public void setAction(String action)
	{
		this.action = action;
	}
	public String getMessage()
	{
		return message;
	}
	public void setMessage(String message)
	{
		this.message = message;
	}
}
