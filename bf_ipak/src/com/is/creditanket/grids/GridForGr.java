package com.is.creditanket.grids;

import java.util.HashMap;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Button;
import org.zkoss.zul.Caption;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;

import com.is.bpri.BprLdGr.BprLdGrService;
import com.is.creditanket.CreditService;
import com.is.creditanket.table_models.Ld_gr;
import com.is.creditanket.table_models.Ld_graf;
import com.is.utils.RefCBox;

public class GridForGr {

	private HashMap<String, Component> map = new HashMap<String, Component>();
	
	public Groupbox getBox(Ld_gr gr){
		Groupbox groupbox = new Groupbox();
		groupbox.appendChild(getCaption());
		Div div = getDiv();
		map.put("div", div);
		groupbox.appendChild(getGrid(gr));
		groupbox.appendChild(div);
		groupbox.appendChild(getButton());
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
	
	private Grid getGrid(Ld_gr gr){
		Grid grid = new Grid();
		grid.appendChild(getRows(gr));
		return grid;
	}
	
	private Rows getRows(Ld_gr gr){
		Rows rows = new Rows();
		Label oper_id_label = new Label("Виды графика");
		RefCBox oper_id = new RefCBox();
		oper_id.setModel(new ListModelList(CreditService.getOper_id()));
		oper_id.setWidth("100%");
		if(gr!=null){ oper_id.setAttribute("value", gr.getOper_id()); }
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
		if(gr!=null){ exp_id.setAttribute("value", gr.getExp_id()); }
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
		Label graf_method_label = new Label("Метод формирования");
		RefCBox graf_method = new RefCBox();
		graf_method.setModel(new ListModelList(BprLdGrService.getGrafMethod("")));
		graf_method.setWidth("100%");
		if(gr!=null){ graf_method.setAttribute("value", gr.getGraf_method()); }
		graf_method.addEventListener("onInitRenderLater", new EventListener() {
			
			@Override
			public void onEvent(Event evt) throws Exception {
				RefCBox rbox = (RefCBox) evt.getTarget();
				if(rbox.getAttribute("value")!=null){
					rbox.setSelecteditem(rbox.getAttribute("value")+"");
				}
			}
		});
		Label num_label = new Label("Количество выплат");
		Intbox num = new Intbox();
		num.setMaxlength(2);
		num.setWidth("100%");
		if(gr!=null){num.setValue(gr.getNum());}
		Row graf_row = new Row();
		graf_row.appendChild(graf_method_label);
		graf_row.appendChild(graf_method);
		graf_row.appendChild(num_label);
		graf_row.appendChild(num);
		rows.appendChild(graf_row);
		Label pay_label = new Label("Периодичность выплат");
		RefCBox pay_period = new RefCBox();
		pay_period.setModel(new ListModelList(BprLdGrService.getPayPeriod("")));
		pay_period.setWidth("100%");
		if(gr!=null){ pay_period.setAttribute("value", gr.getPay_period()); }
		pay_period.addEventListener("onInitRenderLater", new EventListener() {
			
			@Override
			public void onEvent(Event evt) throws Exception {
				RefCBox rbox = (RefCBox) evt.getTarget();
				if(rbox.getAttribute("value")!=null){
					rbox.setSelecteditem(rbox.getAttribute("value")+"");
				}
			}
		});
		Label day_label = new Label("День");
		Intbox day = new Intbox();
		day.setMaxlength(2);
		day.setWidth("100%");
		if(gr!=null){day.setValue(gr.getDay());}
		Row pay_row = new Row();
		pay_row.appendChild(pay_label);
		pay_row.appendChild(pay_period);
		pay_row.appendChild(day_label);
		pay_row.appendChild(day);
		rows.appendChild(pay_row);
		Label from_label = new Label("Дата начала расчета");
		Datebox date_from = new Datebox();
		date_from.setFormat("dd.MM.yyyy");
		date_from.setWidth("100%");
		if(gr!=null){date_from.setValue(gr.getDate_from());}
		Label to_label = new Label("Дата завершение расчета");
		Datebox date_to = new Datebox();
		date_to.setFormat("100%");
		date_to.setWidth("100%");
		if(gr!=null){date_to.setValue(gr.getDate_from());}
		Row date_row = new Row();
		date_row.appendChild(from_label);
		date_row.appendChild(date_from);
		date_row.appendChild(to_label);
		date_row.appendChild(date_to);
		rows.appendChild(date_row);
		if(gr!=null&&gr.getLdgrafs()!=null){
			for (int i = 0; i < gr.getLdgrafs().length; i++) {
				pressBtn(gr.getLdgrafs()[i]);
			}
		}
		return rows;
	}
	
	private Grid getButton(){
		Grid grid = new Grid();
		Rows rows = new Rows();
		Row row = new Row();
		row.setAlign("center");
		Button btn = new Button();
		btn.setLabel("Graf");
		btn.addEventListener(Events.ON_CLICK, new EventListener() {
			
			@Override
			public void onEvent(Event evt) throws Exception {
				pressBtn(null);
			}
		});
		row.appendChild(btn);
		rows.appendChild(row);
		grid.appendChild(rows);
		return grid;
	}
	
	private Div getDiv(){
		Div div = new Div();
		return div;
	}
	
	private void pressBtn(Ld_graf graf){
		Div div = (Div) map.get("div");
		GridForLdGraf grid = new GridForLdGraf();
		div.appendChild(grid.getBox(graf));
	}
	
}
