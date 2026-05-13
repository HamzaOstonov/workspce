package universal_reader.nodes_classes;

public class Parameter
{
	private int ord;
	private String value;
	public Parameter(int ord, String value)
	{
		super();
		this.ord = ord;
		this.value = value;
	}
	public int getOrd()
	{
		return ord;
	}
	public void setOrd(int ord)
	{
		this.ord = ord;
	}
	public String getValue()
	{
		return value;
	}
	public void setValue(String value)
	{
		this.value = value;
	}
	
}
