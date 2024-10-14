package universal_reader.nodes_classes;

public class Script
{
	private int ord;
	private String value;
	private Parameter[] params;
	
	public Script(int ord, String value, Parameter[] params)
	{
		super();
		this.ord = ord;
		this.value = value;
		this.params = params;
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
	public Parameter[] getParams()
	{
		return params;
	}
	public void setParams(Parameter[] params)
	{
		this.params = params;
	}
}
