package com.is.creditanket.grids;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

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
import org.zkoss.zul.Textbox;

import com.is.ISLogger;
import com.is.bpri.utils.Utils;
import com.is.creditanket.CreditService;
import com.is.creditanket.grids_models.GuarrGrid;
import com.is.creditanket.table_models.Ld_guarr;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;

public class GridForGuars {

	private static final long serialVersionUID = 1L;
	private HashMap<String, Component> map = new HashMap<String, Component>();
	private RefCBox region_id = null,org_region_id = null,org_distr_id = null,distr_id = null,car_model = null,car_type = null, car_marka = null;
	private Ld_guarr guarr;
	
	public GridForGuars(Ld_guarr guarr) {
		this.guarr = guarr;
	}
	
	public Groupbox getBox(){
		Groupbox groupbox = new Groupbox();
		map.put("groupbox", groupbox);
		groupbox.appendChild(getCaption());
		groupbox.appendChild(getGrid());
		if(guarr!=null){
			groupbox.setAttribute("PK", guarr.getPk());
			groupbox.setAttribute("guarr_id", guarr.getGuar_id());
		}
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
	
	private Grid getGrid(){
		Grid grid = new Grid();
		grid.appendChild(getRows());
		return grid;
	}
	
	private Rows getRows(){
		Rows rows = new Rows();
		rows.appendChild(getRow());
		return rows;
	}
	
	private Row getRow(){
		Row row = new Row();
		row.setAlign("center");
		row.appendChild(getCombobox());
		return row;
	}
	
	private RefCBox getCombobox(){
		RefCBox rbox = new RefCBox();
		rbox.setAttribute("guarr_id", rbox.getValue());
		rbox.addEventListener(Events.ON_SELECT, new EventListener() {
			
			@Override
			public void onEvent(Event evt) throws Exception {
				try {
					RefCBox rbox = (RefCBox) evt.getTarget();
					setLabelFromGroupBox(rbox);
					rbox.setAttribute("guarr_id", rbox.getValue());
				} catch (Exception e) {
					e.printStackTrace();
					ISLogger.getLogger().error(CheckNull.getPstr(e));
				}
			}
		});
		rbox.addEventListener("onInitRenderLater", new EventListener() {
			
			@Override
			public void onEvent(Event evt) throws Exception {
				RefCBox rbox = (RefCBox) evt.getTarget();
				if(guarr!=null){
					rbox.setSelecteditem(guarr.getGuar_id());
					setLabelFromGroupBox(rbox);
				}
			}
		});
		rbox.setWidth("20%");
		rbox.setModel(new ListModelList(CreditService.getTypes_obesp()));
		return rbox;
	}
	
	@SuppressWarnings("unchecked")
	private void setLabelFromGroupBox(RefCBox rbox){
		Groupbox groupbox = (Groupbox) map.get("groupbox");
		Caption caption = (Caption) map.get("caption");
		List<Component> arr = groupbox.getChildren();
		if(arr.size()>2){
			groupbox.removeChild(arr.get(2));
		}
		groupbox.setAttribute("guarr_id", rbox.getValue());
		caption.setLabel(rbox.getText());
		List<GuarrGrid> guarrGrid = CreditService.getGuarrGrid(rbox.getValue());
		Grid grid = null;
		if(!guarrGrid.isEmpty()){
				grid = getGridFromGuGrid(guarrGrid,guarr);
				grid.setAttribute("isGrid", true);
		}
		if(grid!=null){
			groupbox.appendChild(grid);
		} 
	}
	
	private Row getRowFromGuarrId(GuarrGrid left_guarr,GuarrGrid right_guarr) throws Exception{
		Row row = new Row();
		Label left_label = new Label();
		left_label.setValue(left_guarr.getName_field());
		row.appendChild(left_label);
		if(left_guarr.getType_field().trim().equalsIgnoreCase("string")){
			Textbox txt = new Textbox();
			txt.setMaxlength(Integer.parseInt(left_guarr.getMax_lenght_field().toString()));
			txt.setWidth("100%");
			txt.setAttribute("param", left_guarr.getTable_name_field());
			txt.setAttribute("table", left_guarr.getTable_name());
			if(guarr!=null)
			txt.setValue(CreditService.getGuarrValue(left_guarr.getType_field(),left_guarr.getTable_name(),left_guarr.getTable_name_field(),guarr.getId(),guarr.getBranch(),guarr.getPk()));
			row.appendChild(txt);
		} else if(left_guarr.getType_field().trim().equalsIgnoreCase("date")){
			Datebox date = new Datebox();
			date.setFormat("dd.MM.yyyy");
			date.setWidth("100%");
			date.setAttribute("param", left_guarr.getTable_name_field());
			date.setAttribute("table", left_guarr.getTable_name());
			if(guarr!=null)
			date.setText(CreditService.getGuarrValue(left_guarr.getType_field(),left_guarr.getTable_name(),left_guarr.getTable_name_field(),guarr.getId(),guarr.getBranch(),guarr.getPk()));
			row.appendChild(date);
		} else if(left_guarr.getType_field().trim().equalsIgnoreCase("number")){
			Decimalbox dbox = new Decimalbox();
			dbox.setMaxlength(Integer.parseInt(left_guarr.getMax_lenght_field().toString()));
			dbox.setWidth("100%");
			dbox.setAttribute("param", left_guarr.getTable_name_field());
			dbox.setAttribute("table", left_guarr.getTable_name());
			if(guarr!=null){
				String value = CreditService.getGuarrValue(left_guarr.getType_field(),left_guarr.getTable_name(),left_guarr.getTable_name_field(),guarr.getId(),guarr.getBranch(),guarr.getPk());
				BigDecimal bd = null;
				if(value==null){
					bd = BigDecimal.ZERO;
				} else {
					bd = new BigDecimal(value);
				}
				dbox.setValue(bd);
			}
			row.appendChild(dbox);
		} else if(left_guarr.getType_field().trim().equalsIgnoreCase("list")){
			RefCBox rbox = new RefCBox();
			if(!left_guarr.getTable_name_field().equals("DISTR_ID"))rbox.setModel(new ListModelList(Utils.getRefData(left_guarr.getModel_field(), "")));
			rbox.setWidth("100%");
			if(guarr!=null)
			rbox.setAttribute("value", CreditService.getGuarrValue(left_guarr.getType_field(),left_guarr.getTable_name(),left_guarr.getTable_name_field(),guarr.getId(),guarr.getBranch(),guarr.getPk()));
			setModelsCtrl(rbox, left_guarr.getTable_name_field(),left_guarr.getModel_field());
			rbox.setAttribute("param", left_guarr.getTable_name_field());
			rbox.setAttribute("table", left_guarr.getTable_name());
			row.appendChild(rbox);
		} if(right_guarr!=null){
			Label right_label = new Label();
			right_label.setValue(right_guarr.getName_field());
			row.appendChild(right_label);
//			System.out.println("right_label = "+right_label.getValue());
			if(right_guarr.getType_field().trim().equalsIgnoreCase("string")){
				Textbox txt = new Textbox();
				txt.setMaxlength(Integer.parseInt(right_guarr.getMax_lenght_field().toString()));
				txt.setWidth("100%");
				txt.setAttribute("param", right_guarr.getTable_name_field());
				txt.setAttribute("table", right_guarr.getTable_name());
				if(guarr!=null)
				txt.setValue(CreditService.getGuarrValue(right_guarr.getType_field(),right_guarr.getTable_name(),right_guarr.getTable_name_field(),guarr.getId(),guarr.getBranch(),guarr.getPk()));
				row.appendChild(txt);
			} else if(right_guarr.getType_field().trim().equalsIgnoreCase("date")){
				Datebox date = new Datebox();
				date.setFormat("dd.MM.yyyy");
				date.setWidth("100%");
				date.setAttribute("param", right_guarr.getTable_name_field());
				date.setAttribute("table", right_guarr.getTable_name());
				if(guarr!=null)
				date.setText(CreditService.getGuarrValue(right_guarr.getType_field(),right_guarr.getTable_name(),right_guarr.getTable_name_field(),guarr.getId(),guarr.getBranch(),guarr.getPk()));
				row.appendChild(date);
			} else if(right_guarr.getType_field().trim().equalsIgnoreCase("number")){
				Decimalbox dbox = new Decimalbox();
				dbox.setMaxlength(Integer.parseInt(right_guarr.getMax_lenght_field().toString()));
				dbox.setWidth("100%");
				dbox.setAttribute("param", right_guarr.getTable_name_field());
				dbox.setAttribute("table", right_guarr.getTable_name());
				if(guarr!=null){
					String value = CreditService.getGuarrValue(right_guarr.getType_field(),right_guarr.getTable_name(),right_guarr.getTable_name_field(),guarr.getId(),guarr.getBranch(),guarr.getPk());
					BigDecimal bd = null;
					if(value==null){
						bd = BigDecimal.ZERO;
					} else {
						bd = new BigDecimal(value);
					}
					dbox.setValue(bd);
				}
				row.appendChild(dbox);
			} else if(right_guarr.getType_field().trim().equalsIgnoreCase("list")){
				RefCBox rbox = new RefCBox();
				ISLogger.getLogger().error("fielnd ="+right_guarr.getTable_name_field()+" table = "+right_guarr.getTable_name()+" ID = "+right_guarr.getGuarr_id()+" MODEL = "+right_guarr.getModel_field());
				if(!right_guarr.getTable_name_field().equals("DISTR_ID"))rbox.setModel(new ListModelList(Utils.getRefData(right_guarr.getModel_field(), "")));
				rbox.setWidth("100%");
				if(guarr!=null)
				rbox.setAttribute("value", CreditService.getGuarrValue(right_guarr.getType_field(),right_guarr.getTable_name(),right_guarr.getTable_name_field(),guarr.getId(),guarr.getBranch(),guarr.getPk()));
				setModelsCtrl(rbox, right_guarr.getTable_name_field(),right_guarr.getModel_field());
				rbox.setAttribute("param", right_guarr.getTable_name_field());
				rbox.setAttribute("table", right_guarr.getTable_name());
				row.appendChild(rbox);
			}
		}
		return row;
	}
	
	private void setModelsCtrl(RefCBox rbox,String table_name,String model){
		rbox.addEventListener("onInitRenderLater", new EventListener() {
			
			@Override
			public void onEvent(Event evt) throws Exception {
				RefCBox rbox = (RefCBox) evt.getTarget();
				if(rbox.getAttribute("value")!=null){
					String value = (String) rbox.getAttribute("value");
					rbox.setSelecteditem(value);
				}
			}
		});
		if(table_name.equalsIgnoreCase("CAR_TYPE")){
			car_type = rbox;
			car_type.addEventListener(Events.ON_SELECT, new EventListener() {
				
				@Override
				public void onEvent(Event evt) throws Exception {
					RefCBox rbox = (RefCBox) evt.getTarget();
					if(car_marka!=null&&car_model!=null){
						car_marka.setText("");
						car_model.setText("");
						car_marka.setModel(new ListModelList(Utils.getRefData(car_marka.getAttribute("model")+rbox.getValue(), "")));
					}
				}
			});
		} else if(table_name.equalsIgnoreCase("CAR_MARKA")){
			car_marka = rbox;
			car_marka.setAttribute("model", model);
			car_marka.addEventListener(Events.ON_SELECT, new EventListener() {
				
				@Override
				public void onEvent(Event evt) throws Exception {
					if(car_model!=null){
						String model = car_model.getAttribute("model")+"";
						model = model.replace("<car_type>", car_type.getValue());
						model = model.replace("<car_marka>", car_marka.getValue());
						car_model.setText("");
						car_model.setModel(new ListModelList(Utils.getRefData(model, "")));
					}
				}
			});
		} else if(table_name.equalsIgnoreCase("CAR_MODEL")){
			car_model = rbox;
			car_model.setAttribute("model", model);
		} else if(table_name.equalsIgnoreCase("region_id")){
			setRegionAndDistrCtrlModel(rbox);
			region_id = rbox;
		} else if (table_name.equalsIgnoreCase("distr_id")){
			distr_id = rbox;
			distr_id.setAttribute("model", model);
		} else if(table_name.equalsIgnoreCase("CADASTR_ORG_REGION")){
			setRegionAndDistrCtrlModel(rbox);
			org_region_id = rbox;
		} else if (table_name.equalsIgnoreCase("CADASTR_ORG_DISTR")){
			org_distr_id = rbox;
			org_distr_id.setAttribute("model", model);
		}
		if(guarr!=null){
			if(distr_id!=null&&region_id!=null){
				distr_id.setModel(new ListModelList(Utils.getRefData(distr_id.getAttribute("model")+""+region_id.getAttribute("value")+"", "")));
			} if(org_distr_id!=null&&org_region_id!=null){
				org_distr_id.setModel(new ListModelList(Utils.getRefData(distr_id.getAttribute("model")+""+org_region_id.getAttribute("value")+"", "")));
			} if(car_marka!=null&&car_type!=null){
				car_marka.setModel(new ListModelList(Utils.getRefData(car_marka.getAttribute("model")+car_type.getValue(), "")));
			} if(car_marka!=null&&car_type!=null&&car_model!=null){
				String car_model_str = car_model.getAttribute("model")+"";
				car_model_str = model.replace("<car_type>", car_type.getValue());
				car_model_str = model.replace("<car_marka>", car_marka.getValue());
				car_model.setText("");
				car_model.setModel(new ListModelList(Utils.getRefData(car_model_str, "")));
			}
		}
	}
	
	private Rows getRowsFromGuarr(List<GuarrGrid> guarrs,Ld_guarr guarr) throws Exception{
		Rows rows = new Rows();
		GuarrGrid left_guarr = null;
		GuarrGrid right_guarr = null;
		System.out.println("size = "+guarrs.size());
		for (int i = 0; i < guarrs.size(); i++) {
			if((i+1)%2!=0){
				left_guarr = guarrs.get(i);
				if((guarrs.size()-1)==i){
					rows.appendChild(getRowFromGuarrId(left_guarr,null));
				}
			} else {
				right_guarr = guarrs.get(i);
				rows.appendChild(getRowFromGuarrId(left_guarr,right_guarr));
			}
		}
		return rows;
	}
	
	private Grid getGridFromGuGrid(List<GuarrGrid> guarrs,Ld_guarr guarr){
		Grid grid = new Grid();
		try {
			grid.appendChild(getRowsFromGuarr(guarrs,guarr));
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
		return grid;
	}
	
	private void setRegionAndDistrCtrlModel(RefCBox rbox){
		rbox.addEventListener(Events.ON_SELECT, new EventListener() {
			
			@Override
			public void onEvent(Event evt) throws Exception {
				RefCBox rbox = (RefCBox) evt.getTarget();
				if(distr_id!=null){
					distr_id.setModel(new ListModelList(Utils.getRefData(distr_id.getAttribute("model")+""+rbox.getValue(), "")));
				} 
			}
		});
	}
	
}
