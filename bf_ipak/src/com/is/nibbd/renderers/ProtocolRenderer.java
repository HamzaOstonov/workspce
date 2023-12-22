package com.is.nibbd.renderers;

import java.text.SimpleDateFormat;

import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

import com.is.nibbd.models.Protocol;

public class ProtocolRenderer implements ListitemRenderer {
	private SimpleDateFormat df;
	
	public ProtocolRenderer(SimpleDateFormat df) {
		this.df = df;
	}
	
	@Override
	public void render(Listitem arg0, Object arg1) throws Exception {
		Protocol prot = (Protocol)arg1;
		
		arg0.appendChild(new Listcell(prot.getBranch()));
		arg0.appendChild(new Listcell(prot.getProtocol()));
		arg0.appendChild(new Listcell(prot.getDate_oper() != null ? df.format(prot.getDate_oper()): null));
	}

}
