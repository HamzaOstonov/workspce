package com.is.bpri.reference;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbarbutton;

import com.is.utils.CheckNull;
import com.is.utils.Res;

public class ReferenceCtrl extends GenericForwardComposer{

	private static final long serialVersionUID = 1L;
	private Reference current = new Reference();
	private List<ReferenceFields> fields = new ArrayList<ReferenceFields>();
	private String uname,action = "";
	private Listbox referenceList;
	private Div divgrd;
	private Toolbarbutton btn_add,btn_del;
	private Textbox main_name;
	private Rows dRows;
	private Grid main_grd;
	private String label_name;
	private String main_value = null;
	private String module_id = null;
	
	public ReferenceCtrl() {
		super('$',false,false);
	}
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		try {
			super.doAfterCompose(comp);
			uname = (String) session.getAttribute("un");
			String parametr[] = (String[]) param.get("module_type");
			if(parametr!=null&&parametr[0]!=null&&parametr[0].equals("44")){
				label_name = "Тип сотрудника";
				main_grd.setVisible(false);
				main_value = "Типы сотрудников";
				module_id = parametr[0];
			} else {
				label_name = "Значение";
				main_grd.setVisible(true);
			}
			referenceList.setItemRenderer(new ListitemRenderer() {

				@Override
				public void render(Listitem row, Object data) throws Exception {
					Reference ref = (Reference) data;
					row.setValue(ref);
					Listcell lc = new Listcell(ref.getId()+"");
					lc.setVisible(false);
					row.appendChild(lc);
					row.appendChild(new Listcell(ref.getName()));
					Listcell lcc = new Listcell(ref.getUname());
					lcc.setVisible(false);
					row.appendChild(lcc);
					row.addEventListener(Events.ON_CLICK, new EventListener() {

						@Override
						public void onEvent(Event evt) throws Exception {
							Listitem li = (Listitem) evt.getTarget();
							@SuppressWarnings("unchecked")
							List<Listcell> list = li.getChildren();
							current.setId(Long.parseLong(list.get(0).getLabel()));
							current.setName(list.get(1).getLabel());
							current.setUname(list.get(2).getLabel());
						}
					});
					row.addEventListener(Events.ON_DOUBLE_CLICK, new EventListener() {

						@Override
						public void onEvent(Event evt) throws Exception {
							referenceList.setVisible(false);
							dRowsClear();
							divgrd.setVisible(true);
							main_name.setValue(current.getName());
							fields = ReferenceSerivce.getFields(current.getId());
							for (int i = 0; i < fields.size(); i++) {
								dRows.appendChild(getdRow(fields.get(i).getId(),fields.get(i).getValue()));							
							}
							action = "double";
						}
					});
				}
			});
			refreshModel();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void refreshModel(){
		referenceList.setModel(new ListModelList(ReferenceSerivce.getModel(module_id)));
		if(referenceList.getModel().getSize()>0){
			btn_add.setVisible(false);
			btn_del.setVisible(true);
		} else {
			btn_add.setVisible(true);
			btn_del.setVisible(false);
		}
	}
	
	public void onClick$btn_add(){
		referenceList.setVisible(false);
		dRowsClear();
		current = new Reference();
		divgrd.setVisible(true);
		main_name.setValue(main_value);
		action = "add";
	}
	
	public void onClick$btn_cancel(){
		referenceList.setVisible(true);
		dRowsClear();
		divgrd.setVisible(false);
	}
	
	public void onClick$btn_addField(){
		try {
			dRows.appendChild(getdRow(null,null));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void onClick$btn_save(){
		try {
			if(current==null){
				current = new Reference();
			}
			if(current.getId()==null||current.getId()<=0){
				current.setId(0L);
			}
			List<Row> rowlist = dRows.getChildren();
			Res res = new Res(); 
			current.setName(main_name.getValue());
			current.setUname(uname);
			current.setModule_id(module_id);
			for (int i = 0; i < rowlist.size(); i++) {
				Textbox txt = (Textbox) rowlist.get(i).getChildren().get(1);
				if(txt.getAttribute("id")==null){
					fields.add(new ReferenceFields("","",txt.getValue()));
				} else {
					for (int j = 0; j < fields.size(); j++) {
						if(fields.get(j).getId()!=null&&fields.get(j).getId().equals(txt.getAttribute("id")+"")){
							fields.get(j).setValue(txt.getValue());
						}
					}
				}
			}
			if(action.equals("add")){
				ReferenceSerivce.save(current, fields, res);
			} else if(action.equals("double")){
				ReferenceSerivce.update(current, fields, res);
			}
			if(res.getCode()==-1){
				alert(res.getName());
			} else {
				alert("Успешно");
				referenceList.setVisible(true);
				dRowsClear();
				divgrd.setVisible(false);
				refreshModel();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	private void dRowsClear(){
		List<Row> listrow = dRows.getChildren();
		int size = listrow.size()-1;
		for (int i = size; i >= 0; i--) {
			dRows.removeChild(listrow.get(i));
		}
	}
	
	private Row getdRow(String id,String tName){
		Row row = new Row();
		try {
			Label labelName = new Label(label_name);
			Textbox txtName = new Textbox();
			txtName.setAttribute("id", id);
			txtName.setValue(tName);
			txtName.setWidth("80%");
			row.appendChild(labelName);
			row.appendChild(txtName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return row;
	}
	public void onClick$btn_del(){		
		try {
		  if (!CheckNull.isEmpty(current.getName())){
			Messagebox.show("Вы хотити удалить справочник "+current.getName()+"?","Вопрос", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION,
					new org.zkoss.zk.ui.event.EventListener(){
				public void onEvent(Event e){
					if(Messagebox.ON_OK.equals(e.getName())){						
						Long count_temp=ReferenceSerivce.usedReference(current);
						if (count_temp>0){
							alert("Ест данный по справочник "+current.getName()+"!");
						}else{
							ReferenceSerivce.remove(current.getId());
							refreshModel();
						}
					}
				}
			}
			);
		  }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
}
