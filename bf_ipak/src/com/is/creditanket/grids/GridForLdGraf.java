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
import org.zkoss.zul.Longbox;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;

import com.is.creditanket.CreditService;
import com.is.creditanket.table_models.Ld_graf;
import com.is.utils.RefCBox;

public class GridForLdGraf {
	
	private HashMap<String, Component> map = new HashMap<String, Component>();

	public Groupbox getBox(Ld_graf graf){
		Groupbox groupbox = new Groupbox();
		map.put("groupbox", groupbox);
		groupbox.appendChild(getCaption());
		groupbox.appendChild(getGrid(graf));
		groupbox.setOpen(true);
		return groupbox;
	}
	
	private Caption getCaption(){
		Caption caption = new Caption();
		caption.setSclass("customIcon");
		caption.setLabel("");
		map.put("caption", caption);
		return caption;
	}
	
	private Grid getGrid(Ld_graf graf){
		Grid grid = new Grid();
		grid.appendChild(getRows(graf));
		return grid;
	}
	
	private Rows getRows(Ld_graf graf){
		Rows rows = new Rows();
		Label oper_id_label = new Label("Виды графика");
		RefCBox oper_id = new RefCBox();
		oper_id.setModel(new ListModelList(CreditService.getOper_id()));
		oper_id.setWidth("100%");
		if(graf!=null){ oper_id.setAttribute("value", graf.getOper_id()); }
		oper_id.addEventListener("onInitRenderLater", new EventListener() {
			
			@Override
			public void onEvent(Event evt) throws Exception {
				RefCBox rbox = (RefCBox) evt.getTarget();
				if(rbox.getAttribute("value")!=null){
					rbox.setSelecteditem(rbox.getAttribute("value")+"");
					Caption cap = (Caption) map.get("caption");
					cap.setLabel(rbox.getText());
				}
			}
		});
		oper_id.addEventListener(Events.ON_SELECT, new EventListener() {
			
			@Override
			public void onEvent(Event evt) throws Exception {
				Caption cap = (Caption) map.get("caption");
				RefCBox rbox = (RefCBox) evt.getTarget();
				cap.setLabel(rbox.getText());
			}
		});
		Label exp_id_label = new Label("Описание графика");
		RefCBox exp_id = new RefCBox();
		exp_id.setModel(new ListModelList(CreditService.getLdExpType()));
		exp_id.setWidth("100%");
		if(graf!=null){ exp_id.setAttribute("value", graf.getExp_id()); }
		exp_id.addEventListener("onInitRenderLater", new EventListener() {
			
			@Override
			public void onEvent(Event evt) throws Exception {
				RefCBox rbox = (RefCBox) evt.getTarget();
				if(rbox.getAttribute("value")!=null){
					rbox.setSelecteditem(rbox.getAttribute("value")+"");
				}
			}
		});
		Row gr_row = new Row();
		gr_row.appendChild(oper_id_label);
		gr_row.appendChild(oper_id);
		gr_row.appendChild(exp_id_label);
		gr_row.appendChild(exp_id);
		rows.appendChild(gr_row);
		Label date_label = new Label("Дата выплаты/погашения ");
		Datebox v_date = new Datebox();
		v_date.setFormat("dd.MM.yyyy");
		v_date.setWidth("100%");
		if(graf!=null){ v_date.setValue(graf.getV_date()); }
		Label summa_label = new Label("Сумма");
		Decimalbox summa = new Decimalbox();
		summa.setMaxlength(20);
		summa.setWidth("100%");
		summa.setFormat("#0.00");
		if(graf!=null){ summa.setValue(new BigDecimal(graf.getSumma())); }
		Row date_row = new Row();
		date_row.appendChild(date_label);
		date_row.appendChild(v_date);
		date_row.appendChild(summa_label);
		date_row.appendChild(summa);
		rows.appendChild(date_row);
		Label num_label = new Label("Порядковый номер выплаты");
		Longbox num = new Longbox();
		num.setMaxlength(6);
		num.setWidth("100%");
		if(graf!=null){ num.setValue(graf.getNum()); }
		Label repay_label = new Label("Признак досрочное погашения");
		RefCBox repay = new RefCBox();
		repay.setModel(new ListModelList(CreditService.getRepay()));
		repay.setWidth("100%");
		if(graf!=null){ repay.setAttribute("value", graf.getRepay()); }
		repay.addEventListener("onInitRenderLater", new EventListener() {
			
			@Override
			public void onEvent(Event evt) throws Exception {
				RefCBox rbox = (RefCBox) evt.getTarget();
				if(rbox.getAttribute("value")!=null){
					rbox.setSelecteditem(rbox.getAttribute("value")+"");
				}
			}
		});
		Row row_repay = new Row();
		row_repay.appendChild(num_label);
		row_repay.appendChild(num);
		row_repay.appendChild(repay_label);
		row_repay.appendChild(repay);
		rows.appendChild(row_repay);
		return rows;
	}
	
}
