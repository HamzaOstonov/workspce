package com.is.dper_info.render;

import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

import com.is.dper_info.model.dper_books;

public class DperBookRenderer implements ListitemRenderer {

	@Override
	public void render(Listitem row, Object data) throws Exception {
		dper_books pdper_books = (dper_books) data;
		row.setValue(pdper_books);
		row.appendChild(new Listcell(Integer.toString(pdper_books
				.getInfo_id())));
		row.appendChild(new Listcell(Integer.toString(pdper_books
				.getGeneral_id())));
		row.appendChild(new Listcell(pdper_books.getTypeoper()));
		row.appendChild(new Listcell(pdper_books.getAcc_d()));
		row.appendChild(new Listcell(pdper_books.getAcc_c()));
		row.appendChild(new Listcell(Integer.toString(pdper_books
				.getSumma())));
		row.appendChild(new Listcell(pdper_books.getComments()));
	}

}
