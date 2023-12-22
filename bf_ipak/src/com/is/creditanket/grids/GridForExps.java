package com.is.creditanket.grids;

import java.util.HashMap;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Caption;
//import org.zkoss.zul.Decimalbox;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;

import com.is.bpri.ldhisrate.LdHisRateService;
import com.is.creditanket.CreditService;
import com.is.creditanket.table_models.Ld_exp;
import com.is.creditanket.table_models.Ld_rate;
import com.is.utils.RefCBox;

public class GridForExps {
	
	private HashMap<String, Component> map = new HashMap<String, Component>();
	
	public Groupbox getBox(Ld_exp exp){
		Groupbox groupbox = new Groupbox();
		Div div = getDiv();
		map.put("div", div);
		groupbox.appendChild(getCaption());
		groupbox.appendChild(getGrid(exp));
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
	
	private Grid getGrid(Ld_exp exp){
		Grid grid = new Grid();
		grid.appendChild(getRows(exp));
		return grid;
	}
	
	private Rows getRows(Ld_exp exp){
		Rows rows = new Rows();
		Label exp_label = new Label("Вид ставки");
		RefCBox exp_id = new RefCBox();
		exp_id.setWidth("100%");
		if(exp!=null){exp_id.setAttribute("value", exp.getExp_id());}
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
		Row exp_row = new Row();
		exp_row.appendChild(exp_label);
		exp_row.appendChild(exp_id);
		Label exp_type_label = new Label("Тип ставки");
		RefCBox exp_type = new RefCBox();
		exp_type.setWidth("100%");
		exp_type.setModel(new ListModelList(LdHisRateService.getRate_type("")));
		if(exp!=null){exp_type.setAttribute("value", exp.getRate_type());}
		exp_type.addEventListener("onInitRenderLater", new EventListener() {
			
			@Override
			public void onEvent(Event evt) throws Exception {
				RefCBox rbox = (RefCBox) evt.getTarget();
				if(rbox.getAttribute("value")!=null){
					String value = rbox.getAttribute("value")+"";
					rbox.setSelecteditem(value);
				}
			}
		});
		exp_row.appendChild(exp_type_label);
		exp_row.appendChild(exp_type);
		rows.appendChild(exp_row);
		Label rate_method_label = new Label("Метод расчета");
		RefCBox rate_method = new RefCBox();
		rate_method.setWidth("100%");
		rate_method.setModel(new ListModelList(LdHisRateService.getRate_method("")));
		if(exp!=null){rate_method.setAttribute("value", exp.getRate_method());}
		rate_method.addEventListener("onInitRenderLater", new EventListener() {
			
			@Override
			public void onEvent(Event evt) throws Exception {
				RefCBox rbox = (RefCBox) evt.getTarget();
				if(rbox.getAttribute("value")!=null){
					String value = rbox.getAttribute("value")+"";
					rbox.setSelecteditem(value);
				}
			}
		});
		Row method_row = new Row();
		method_row.appendChild(rate_method_label);
		method_row.appendChild(rate_method);
		Label pay_method_label = new Label("Метод платежа");
		RefCBox pay_method = new RefCBox();
		pay_method.setWidth("100%");
		pay_method.setModel(new ListModelList(LdHisRateService.getPay_method("")));
		if(exp!=null){pay_method.setAttribute("value", exp.getPay_method());}
		pay_method.addEventListener("onInitRenderLater", new EventListener() {
			
			@Override
			public void onEvent(Event evt) throws Exception {
				RefCBox rbox = (RefCBox) evt.getTarget();
				if(rbox.getAttribute("value")!=null){
					String value = rbox.getAttribute("value")+"";
					rbox.setSelecteditem(value);
				}
			}
		});
		method_row.appendChild(pay_method_label);
		method_row.appendChild(pay_method);
		rows.appendChild(method_row);
		if(exp!=null&&exp.getLd_rates()!=null){
			for (int i = 0; i < exp.getLd_rates().length; i++) {
				createGridRates(exp.getLd_rates()[i]);
			}
		}
//		Decimalbox ext_day = new Decimalbox();
//		Label ext_label = new Label("Ext_days");
//		ext_day.setMaxlength(6);
//		Row ext_row = new Row();
//		ext_row.appendChild(ext_label);
//		ext_row.appendChild(ext_day);
//		rows.appendChild(ext_row);
		return rows;
	}
	
	private Grid getButton(){
		Grid grid = new Grid();
		Rows rows = new Rows();
		Row row = new Row();
		row.setAlign("center");
		Button btn = new Button();
		btn.setLabel("Ставка");
		btn.addEventListener(Events.ON_CLICK, new EventListener() {
			
			@Override
			public void onEvent(Event evt) throws Exception {
				createGridRates(null);
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
	
	private void createGridRates(Ld_rate rate){
		Div div = (Div) map.get("div");
		GridForRates grid = new GridForRates();
		div.appendChild(grid.getBox(rate));
	}
	
}
