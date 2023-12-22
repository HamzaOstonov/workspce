package com.is.dper_info.render;

import java.math.BigDecimal;
import java.math.RoundingMode;

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
		
		BigDecimal modelVal = new BigDecimal(pdper_books.getSumma());
        BigDecimal getSumma = modelVal.setScale(2, RoundingMode.HALF_EVEN).divide(BigDecimal.valueOf(100));
		row.appendChild(new Listcell(getSumma.toString()));	
		row.appendChild(new Listcell(pdper_books.getComments()));
	}

}
