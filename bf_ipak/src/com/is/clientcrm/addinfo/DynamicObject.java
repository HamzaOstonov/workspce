package com.is.clientcrm.addinfo;

import java.util.List;

import org.zkoss.zk.ui.util.GenericForwardComposer;

public class DynamicObject {
	bsh.Interpreter interpreter = null;

    public DynamicObject()
    {
        interpreter = new bsh.Interpreter();
    }

    public void addToSource(String... lines)
    {
        try
        {
            String main = "";
            for (int i=0; i<lines.length; i++){
                main += lines[i] + "\n";
            }
            interpreter.eval(main);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void addToSource(List<String> lines)
    {
        try
        {
            String main = "";
            for (int i=0; i<lines.size(); i++){
                main += lines.get(i) + "\n";
            }
            interpreter.eval(main);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public Object invoke(String methodname, Object... args)
    {
        try
        {
            return interpreter.getNameSpace().invokeMethod(methodname, args, interpreter);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public Object invoke(String methodname)
    {
        return invoke(methodname, (Object[])null);
    }

}
