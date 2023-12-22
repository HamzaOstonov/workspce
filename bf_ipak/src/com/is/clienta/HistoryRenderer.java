package com.is.clienta;

import java.text.SimpleDateFormat;

import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

public class HistoryRenderer implements ListitemRenderer{
	private SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	private SimpleDateFormat dfWithTime = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
	@Override
	public void render(Listitem row, Object data) throws Exception {
		History ch = (History) data;
		row.setValue(ch);
		row.appendChild(new Listcell(ch.getDate_correct()!=null?
										df.format(ch.getDate_correct())
										:null));
		row.appendChild(new Listcell(ch.getName()));
		row.appendChild(new Listcell(ch.getFull_name()));
		row.appendChild(new Listcell(ch.getDate_time()!=null?
										dfWithTime.format(ch.getDate_time())
										:null));
	}
}
