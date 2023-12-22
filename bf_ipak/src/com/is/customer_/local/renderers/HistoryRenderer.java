package com.is.customer_.local.renderers;

import com.is.customer_.core.utils.CustomerUtils;
import com.is.customer_.core.model.SessionAttributes;
import com.is.customer_.local.model.History;
import com.is.customer_.local.service.HistoryService;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

import java.text.SimpleDateFormat;
import java.util.Date;


public class HistoryRenderer implements ListitemRenderer{
    private final SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
	private SessionAttributes sessionAttributes;

	public HistoryRenderer(SessionAttributes sessionAttributes){
		this.sessionAttributes = sessionAttributes;
	}

	@Override
	public void render(Listitem item, Object data) throws Exception {
		History history = (History) data;

		Listcell cell = null;

		int action = history.getAction_id();

		switch (action) {
		case 1:
			cell = new Listcell(CustomerUtils.dateToString(history.getDate_correct()));
			break;
		case 2:
			cell = new Listcell(CustomerUtils.dateToString(history.getDate_correct()));
			break;
		case 3:
			cell = new Listcell(CustomerUtils.dateToString(history.getDate_close()));
			break;
		case 19:
			cell = new Listcell(CustomerUtils.dateToString(history.getDate_correct()));
			break;
		case 20:
			cell = new Listcell(CustomerUtils.dateToString(history.getDate_correct()));
			break;
		case 21:
			cell = new Listcell(CustomerUtils.dateToString(history.getDate_correct()));
			break;
		}

		item.appendChild(cell);
		item.appendChild(new Listcell(HistoryService.getActionName(action)));
		item.appendChild(
				new Listcell(HistoryService.getEmpName(history.getEmp_id(), sessionAttributes.getSchema())));
		item.appendChild(new Listcell(dateToString(history.getDate_time())));
	}

	private String dateToString(Date date){
        DateTime dateTime = new DateTime(date.getTime());
        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd.MM.YYYY HH:mm:ss");
        String dateStr = dateTime.toString(formatter);
        return dateStr;
        //return date == null ? "" : date.toString();
    }
}
