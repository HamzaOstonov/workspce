package universal_reader.nodes_classes;

public class Rec
{
	private String name; 
	private String expression; 
	private int match_start; 
	private int match_end;
	private Function[] functions;
	private Script[] scripts;
	
	public Rec(String name, String expression, int match_start, int match_end,
			Function[] functions, Script[] scripts)
	{
		super();
		this.name = name;
		this.expression = expression;
		this.match_start = match_start;
		this.match_end = match_end;
		this.functions = functions;
		this.scripts = scripts;
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
	public int getMatch_start()
	{
		return match_start;
	}
	public void setMatch_start(int match_start)
	{
		this.match_start = match_start;
	}
	public int getMatch_end()
	{
		return match_end;
	}
	public void setMatch_end(int match_end)
	{
		this.match_end = match_end;
	}
	public Function[] getFunctions()
	{
		return functions;
	}
	public void setFunctions(Function[] functions)
	{
		this.functions = functions;
	}
	public Script[] getScripts()
	{
		return scripts;
	}
	public void setScripts(Script[] scripts)
	{
		this.scripts = scripts;
	}
}
