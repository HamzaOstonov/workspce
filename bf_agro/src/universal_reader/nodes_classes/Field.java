package universal_reader.nodes_classes;

public class Field
{
	private String name;
	private String expression;
	public Field(String name, String expression)
	{
		super();
		this.name = name;
		this.expression = expression;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getExpression()
	{
		return expression;
	}
	public void setExpression(String expression)
	{
		this.expression = expression;
	}
}
