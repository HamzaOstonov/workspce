package com.is.client_spec;

import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

public class SpecharRenderer implements ListitemRenderer {
	
	@Override
	public void render(Listitem row, Object data) throws Exception {
		SpecClt pSpechar = (SpecClt) data;

		row.setValue(pSpechar);

		row.appendChild(new Listcell(pSpechar.getId_special()));
		row.appendChild(new Listcell(pSpechar.getId_client()));
		row.appendChild(new Listcell(pSpechar.getName()));
		row.appendChild(new Listcell(pSpechar.getValue()));
		row.appendChild(new Listcell(pSpechar.getPrim()));

	}


}
