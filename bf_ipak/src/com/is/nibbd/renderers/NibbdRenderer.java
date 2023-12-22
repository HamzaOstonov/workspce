package com.is.nibbd.renderers;

import java.text.SimpleDateFormat;
import java.util.Map;

import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

import com.is.nibbd.models.Nibbd;

public class NibbdRenderer implements ListitemRenderer{
	private Map<String, String> nibbdStates;
	private SimpleDateFormat df; 
	
	public NibbdRenderer(Map<String,String> nibbdStates, SimpleDateFormat df) {
		this.nibbdStates = nibbdStates;
		this.df = df;
	}
	
	@Override
	public void render(Listitem arg0, Object arg1) throws Exception {
		Nibbd nibbd = (Nibbd) arg1;
		
		arg0.setValue(arg1);
		
		arg0.appendChild(new Listcell(Long.toString(nibbd.getStr_num())));
		arg0.appendChild(new Listcell(Integer.toString(nibbd.getQuery_num())));
		arg0.appendChild(new Listcell(nibbd.getReis_num()));
		arg0.appendChild(new Listcell(nibbdStates.get(Integer.toString(nibbd.getState()))));
		arg0.appendChild(new Listcell(nibbd.getName()));
		arg0.appendChild(new Listcell(nibbd.getQuery_date()!=null?df.format(nibbd.getQuery_date()):null));
		
	}
}
