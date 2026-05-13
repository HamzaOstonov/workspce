package com.is.dper_info.render;

import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

import com.is.dper_info.model.dper_info;

public class DperInfoRenderer implements ListitemRenderer {

	@Override
	public void render(Listitem row, Object data) throws Exception {
		dper_info pdper_info = (dper_info) data;
		row.setValue(pdper_info);
		row.appendChild(new Listcell(pdper_info.getBranch()));
		row.appendChild(new Listcell(pdper_info.getVeoper()));
		row.appendChild(new Listcell(pdper_info.getKind() + ""));
		row.appendChild(new Listcell(pdper_info.getStrs()));
		row.appendChild(new Listcell(pdper_info.getStrr()));
		row.appendChild(new Listcell(pdper_info.getSumma() + ""));
		row.appendChild(new Listcell(pdper_info.getCurrency()));
		row.appendChild(new Listcell(pdper_info.getV_date() + ""));
		row.appendChild(new Listcell(pdper_info.getClient_name1()));
		row.appendChild(new Listcell(pdper_info.getClient_name2()));
		row.appendChild(new Listcell(pdper_info.getClient_name3()));

	}

}
