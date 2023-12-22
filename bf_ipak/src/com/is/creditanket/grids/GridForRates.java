package com.is.creditanket.grids;

import java.math.BigDecimal;
import java.util.HashMap;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Caption;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Decimalbox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;

import com.is.bpri.ldhisrate.LdHisRateService;
import com.is.creditanket.CreditService;
import com.is.creditanket.table_models.Ld_rate;
import com.is.utils.RefCBox;

public class GridForRates {
	
	private HashMap<String, Component> map = new HashMap<String, Component>();
	
	public Groupbox getBox(Ld_rate rate){
		Groupbox groupbox = new Groupbox();
		groupbox.appendChild(getCaption());
		groupbox.appendChild(getGrid(rate));
		groupbox.setOpen(true);
		map.put("groupbox", groupbox);
		return groupbox;
	}
	
	private Caption getCaption(){
		Caption caption = new Caption();
		caption.setSclass("customIcon");
		caption.setLabel("");
		map.put("caption", caption);
		return caption;
	}
	
	private Grid getGrid(Ld_rate rate){
		Grid grid = new Grid();
		grid.appendChild(getRows(rate));
		return grid;
	}
	
	private Rows getRows(Ld_rate ld_rate){
		Rows rows = new Rows();
		Label exp_id_label = new Label("Вид ставки");
		RefCBox exp_id = new RefCBox();
		exp_id.setWidth("100%");
		if(ld_rate!=null){exp_id.setAttribute("value", ld_rate.getExp_id());}
		exp_id.addEventListener("onInitRenderLater", new EventListener() {
			
			@Override
			public void onEvent(Event evt) throws Exception {
				RefCBox rbox = (RefCBox) evt.getTarget();
				if(rbox.getAttribute("value")!=null){
					String value = rbox.getAttribute("value")+"";
					rbox.setSelecteditem(value);
					Caption cap = (Caption) map.get("caption");
					cap.setLabel(rbox.getText());
				}
			}
		});
		exp_id.addEventListener(Events.ON_SELECT, new EventListener() {
			
			@Override
			public void onEvent(Event evt) throws Exception {
				Caption cap = (Caption) map.get("caption");
				RefCBox rbox = (RefCBox) evt.getTarget();
				cap.setLabel(rbox.getText());
			}
		});
		exp_id.setModel(new ListModelList(CreditService.getLdExpType()));
		Label rate_id_label = new Label("Наименование ставки");
		RefCBox rate_id = new RefCBox();
		rate_id.setWidth("100%");
		rate_id.setModel(new ListModelList(LdHisRateService.getRate_id("")));
		if(ld_rate!=null){rate_id.setAttribute("value", ld_rate.getRate_id());}
		rate_id.addEventListener("onInitRenderLater", new EventListener() {
			
			@Override
			public void onEvent(Event evt) throws Exception {
				RefCBox rbox = (RefCBox) evt.getTarget();
				if(rbox.getAttribute("value")!=null){
					String value = rbox.getAttribute("value")+"";
					rbox.setSelecteditem(value);
				}
			}
		});
		Row exp_row = new Row();
		exp_row.appendChild(exp_id_label);
		exp_row.appendChild(exp_id);
		exp_row.appendChild(rate_id_label);
		exp_row.appendChild(rate_id);
		rows.appendChild(exp_row);
		Label rate_label = new Label("Процент/Маржа");
		Decimalbox rate = new Decimalbox();
		rate.setFormat("#0.#######");
		rate.setWidth("100%");
		rate.setMaxlength(10);
		if(ld_rate!=null) rate.setValue(new BigDecimal(ld_rate.getRate()));
		Label coeff_label = new Label("Коэффициент");
		Decimalbox coeef = new Decimalbox();
		coeef.setFormat("#0.00");
		coeef.setWidth("100%");
		coeef.setMaxlength(5);
		if(ld_rate!=null) coeef.setValue(new BigDecimal(ld_rate.getCoeff()));
		Row rate_row = new Row();
		rate_row.appendChild(rate_label);
		rate_row.appendChild(rate);
		rate_row.appendChild(coeff_label);
		rate_row.appendChild(coeef);
		rows.appendChild(rate_row);
		Label date_open_label = new Label("Дата документа");
		Datebox date_open = new Datebox();
		date_open.setWidth("100%");
		date_open.setFormat("dd.MM.yyyy");
		if(ld_rate!=null) date_open.setValue(ld_rate.getDate_open());
		Label date_close_label = new Label();
		Datebox date_close = new Datebox();
		date_close.setWidth("100%");
		date_close.setFormat("dd.MM.yyyy");
		if(ld_rate!=null) date_close.setValue(ld_rate.getDate_close());
		Row date_row = new Row();
		date_row.appendChild(date_open_label);
		date_row.appendChild(date_open);
		date_row.appendChild(date_close_label);
		date_row.appendChild(date_close);
		rows.appendChild(date_row);
		return rows;
	}
	
}
