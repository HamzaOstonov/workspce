package com.is.bpri.template_c;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Cell;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Div;
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

import com.is.ISLogger;
import com.is.bpri.utils.MyCombobox;
import com.is.bpri.utils.Utils;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.Res;


public class Template extends GenericForwardComposer{

	private static final long serialVersionUID = 1L;
	private Div divgrd,divSetting;
	private Listbox templateList;
	private TemplateModel current = new TemplateModel();
	private List<TemplateFields> fields = new ArrayList<TemplateFields>();
	private Textbox main_name, file_name;
//	private Textbox banner_text;
	private Rows dRows;
	private String typeModel = "select name_uz,name_ru from bpr_ss_type_fields";
	private String ss_ref = "select id,name from bpr_ss_reference order by name";
	private String action = "";
	private RefCBox ss_reference;
	private Toolbarbutton btn_saving,btn_save;
	private Button btn_addField;	
	private Long bpr_id;
	private Toolbarbutton btn_add,btn_del,btn_cancel;
	private String target_bpr = "";
//	private String branch = "";
	
	public Template() {
		super('$',false,false);
	}	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		try {
			if (this.param.get("bpr_id") != null) {
				bpr_id = Long.parseLong(((String[]) this.param.get("bpr_id"))[0]);
			} if(this.param.get("target") != null){
				target_bpr = ((String[]) this.param.get("target"))[0];
			}
//			branch = (String) session.getAttribute("branch");
			templateList.setItemRenderer(new ListitemRenderer() {

				@Override
				public void render(Listitem row, Object data)
						throws Exception {
					TemplateModel pModel = (TemplateModel) data;
					row.setValue(pModel);
					Listcell lc = new Listcell(pModel.getId()+"");
					lc.setVisible(false);
					row.appendChild(lc);
					row.appendChild(new Listcell(pModel.getName()));
					Listcell lfile_name = new Listcell(pModel.getFile_name());
					lfile_name.setVisible(false);
					row.appendChild(lfile_name);

					row.addEventListener(Events.ON_CLICK, new EventListener() {

						@SuppressWarnings("unchecked")
						@Override
						public void onEvent(Event evt) throws Exception {
							Listitem li = (Listitem) evt.getTarget();
							List<Listcell> list = li.getChildren();
							action = "on_click";
							selectedIndex(list);
						}
					});
					row.addEventListener(Events.ON_DOUBLE_CLICK, new EventListener() {

						@Override
						public void onEvent(Event evt) throws Exception {
							try {
								doubleClick();
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				}
			});
			ss_reference.setModel(new ListModelList(Utils.getRefData(ss_ref,"")));
//			process_name.setModel(new ListModelList(TemplateService.getRefDatas("select id,name from BPMNPRC order by name")));
			refreshModel();
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
	}
	
	private void selectedIndex(List<Listcell> list){
		current.setId(list.get(0).getLabel().equals("")?bpr_id:Long.parseLong(list.get(0).getLabel()));
		if(!action.equals("refresh")){
			current.setName(list.get(1).getLabel());
			current.setFile_name(list.get(2).getLabel());
		}
	}
	
	private void doubleClick(){
		templateList.setVisible(false);
		dRowsClear();
		divgrd.setVisible(true);
		main_name.setValue(current.getName());
//		banner_text.setValue(current.getBanner_text());
		file_name.setValue(current.getFile_name());
		fields = TemplateService.getFields(current.getId());
		for (int i = 0; i < fields.size(); i++) {
			dRows.appendChild(getdRow(fields.get(i).getLabel_name(),fields.get(i).getType_field(),fields.get(i).getSid(),fields.get(i).getOid()+"",fields.get(i).getLabel_name_en(),fields.get(i).getConformity_id()+"",fields.get(i).getRequired_field(),fields.get(i).getModel(),false));
		}
		Long size = TemplateService.usedTemplate(current);
		if(size>0){
			btn_save.setVisible(false);
			btn_del.setVisible(false);
			btn_addField.setVisible(true);
		} else {
			btn_addField.setVisible(true);
			btn_save.setVisible(true);
		}
		current.setId(bpr_id);
		action = "double";
	}
	
	@SuppressWarnings("unchecked")
	private void refreshModel(){
		templateList.setModel(new ListModelList(TemplateService.getModel(bpr_id)));
		if(templateList.getModel().getSize()>0){
			btn_add.setVisible(false);
			templateList.setSelectedIndex(0);
			Listitem li = templateList.getSelectedItem();
			List<Listcell> list = li.getChildren();
			action = "refresh";
			selectedIndex(list);
			doubleClick();
			btn_cancel.setVisible(false);
		} else {
			btn_cancel.setVisible(false);
			btn_del.setVisible(false);
			btn_add.setVisible(true);
			onClick$btn_add();
		}
	}
	
	public void onClick$btn_add(){
		try {
			templateList.setVisible(false);
			dRowsClear();
			current = new TemplateModel();
			divgrd.setVisible(true);
			main_name.setValue(null);
//			banner_text.setValue(null);
			file_name.setValue(null);
//			process_name.setValue(null);
			//process_name.setValue(null);
			btn_save.setVisible(true);
			btn_addField.setVisible(true);
			getSystemRequiredFields();
			action = "add";
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
	}
	
	private void getSystemRequiredFields(){
		List<Ss_bpr_scoring_anket> required_fields = TemplateService.getSystemRequiredId(target_bpr);
		for (int i = 0; i < required_fields.size(); i++) {
			dRows.appendChild(getdRow(required_fields.get(i).getLabel(),required_fields.get(i).getType_field(),null,null,null,required_fields.get(i).getId(),1,"scoring",true));
		}
	}
	
	public void onClick$btn_cancel(){
		templateList.setVisible(true);
		dRowsClear();
		divgrd.setVisible(false);
	}
	
	public void onClick$btn_canceling(){
		divSetting.setVisible(false);
//		vRowsClear();
		divgrd.setVisible(true);
	}
	
	public void onClick$btn_saving(){
		if(btn_saving.getAttribute("rbox")!=null){
			RefCBox rbox = (RefCBox) btn_saving.getAttribute("rbox");
			rbox.setAttribute("sid", ss_reference.getValue());
			alert("Сохранено");
			divSetting.setVisible(false);
			divgrd.setVisible(true);
		}
	}
	
	public void onClick$btn_save(){
		save(1);
	}
	
	@SuppressWarnings("unchecked")
	private void save(int btn_click){
		try {
			if(current==null){
				current = new TemplateModel();
			}
			if(current.getId()==null||current.getId()<=0){
				current.setId(0L);
			}
			current.setId(bpr_id);
			current.setName(main_name.getValue());
			current.setFile_name(file_name.getValue());
			List<Row> rowlist = dRows.getChildren();
			Res res = new Res();
			System.out.println("4");
			List<Ss_bpr_scoring_anket> required_data = TemplateService.getSystemRequiredId(target_bpr);
			outer: for (int i = 0; i < required_data.size(); i++) {
				for (int j = 0; j < rowlist.size(); j++) {
					MyCombobox conformity = (MyCombobox) rowlist.get(j).getChildren().get(3);
					if(conformity.getValue()!=null&&!conformity.getValue().equals("")){ 
						if(required_data.get(i).getId().equals(conformity.getValue())){
							continue outer;
						} else if (!required_data.get(i).getId().equals(conformity.getValue())&&j==rowlist.size()-1){
							alert("Не указаны обязательные поля");
							return;
						}
					} else if(j==rowlist.size()-1) {
						alert("Не указаны обязательные поля");
						return;
					}
				}
			}
			for (int i = 0; i < rowlist.size(); i++) {
				Textbox txt = (Textbox) rowlist.get(i).getChildren().get(1);
				MyCombobox conformity = (MyCombobox) rowlist.get(i).getChildren().get(3);
				RefCBox rbox = (RefCBox) rowlist.get(i).getChildren().get(5);
				Checkbox checkbox = (Checkbox) rowlist.get(i).getChildren().get(6);
				Long conformity_id = 0L;
				int required_field = 0;
				String model = "";
				if(conformity.getValue()!=null&&!conformity.getValue().equals("")){ 
					conformity_id = Long.parseLong(conformity.getValue()); 
					Comboitem item = conformity.getSelectedItem();
					model = (String) item.getAttribute("model");
				}
				if(checkbox.isChecked()){ required_field = 1; }
				if(txt.getAttribute("oid")==null){
					fields.add(new TemplateFields(null, null, txt.getValue(), rbox.getValue(),rbox.getAttribute("sid")+"",txt.getValue(),conformity_id,required_field,model));
				} else {
					for (int j = 0; j < fields.size(); j++) {
						if(fields.get(j).getOid()!=null&&fields.get(j).getOid()==Long.parseLong(txt.getAttribute("oid")+"")){
							fields.get(j).setLabel_name(txt.getValue());
							fields.get(j).setLabel_name_en(txt.getValue());
							fields.get(j).setType_field(rbox.getValue());
							fields.get(j).setConformity_id(conformity_id);
							fields.get(j).setRequired_field(required_field);
							fields.get(j).setModel(model);
						}
					}
				}
			}
			if(action.equals("add")){
				TemplateService.save(current, fields, btn_click, res);
			} else if(action.equals("double")){
				TemplateService.update(current, fields, res);
			}
			if(res.getCode()==-1){
				alert(res.getName());
				fields = new ArrayList<TemplateFields>();
			} else {
				alert("Успешно");
				refreshModel();
				fields = new ArrayList<TemplateFields>();
//				onClick$btn_cancel();
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
	}
	
	public void onClick$btn_addField(){
		try {
			dRows.appendChild(getdRow(null,null,null,null,null,null,0,null,false));
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
	}
	
	public void onClick$btn_addValues(){
		try {
//			vRows.appendChild(getvRow(null));
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
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
	
	private Row getdRow(String lName,String tName,String sid,String oid,String lName_en,String conformity_id,int required_field,String model,boolean system){
		Row row = new Row();
		try {
			if(!system){
//				if(!target_bpr.equals("J")&&conformity_id!=null&&!conformity_id.equals("4")&&!conformity_id.equals("5")&&!conformity_id.equals("6")){
					system = TemplateService.isSystemRequired(conformity_id, bpr_id+"");
//				}
			}
			final RefCBox rcbox = new RefCBox();
			row.setAttribute("OID", oid);
			Button delete_row = new Button();
			Label labelName = new Label("Название поля");
			Textbox txtName = new Textbox();
			txtName.setAttribute("oid", oid);
			txtName.setValue(lName);
			txtName.setWidth("100%");
			Label labelType = new Label("Тип поля");
			final Button btn = new Button();
			btn.setVisible(false);
			btn.setImage("/images/settings.png");
			btn.setAttribute("id", sid);
			btn.addEventListener(Events.ON_CLICK, new EventListener() {

				@Override
				public void onEvent(Event evt) throws Exception {
					divSetting.setVisible(true);
					divgrd.setVisible(false);
					if(rcbox.getAttribute("sid")!=null){
						ss_reference.setSelecteditem(rcbox.getAttribute("sid")+"");
					}
					if(btn.getAttribute("id")!=null){
						ss_reference.setSelecteditem(btn.getAttribute("id")+"");
					}
					btn_saving.setAttribute("rbox", rcbox);
				}
			});
			rcbox.setReadonly(system);
			rcbox.setWidth("100%");
//			rcbox.setDisabled(system);
			rcbox.setButtonVisible(!system);
			rcbox.setModel(new ListModelList(Utils.getRefData(typeModel,"")));
			rcbox.setAttribute("tname", tName);
//			rcbox.addEventListener(Events.ON_SELECT, new EventListener() {
//
//				@Override
//				public void onEvent(Event evt) throws Exception {
//					RefCBox rbox = (RefCBox) evt.getTarget();
//					if(rbox.getValue().equals("list")){
//						btn.setVisible(true);
//					} else {
//						btn.setVisible(false);
//					}
//				}
//			});
			rcbox.addEventListener("onInitRenderLater", new EventListener() {

				@Override
				public void onEvent(Event evt) throws Exception {
					RefCBox rbox = (RefCBox) evt.getTarget();
					rbox.setSelecteditem(rbox.getAttribute("tname")+"");
//					if(rbox.getValue().equals("list")){
//						btn.setVisible(true);
//					} else {
//						btn.setVisible(false);
//					}
				}
			});
			MyCombobox conformity = new MyCombobox();
			conformity.setWidth("100%");
//			List<MyComboModel> model = new ArrayList<MyComboModel>();
//			RefCBox conformity2 = new RefCBox();
			conformity.setReadonly(system);
//			conformity.setDisabled(system);
			conformity.setButtonVisible(!system);
			conformity.setAttribute("rbox", rcbox);
			conformity.setAttribute("model", model);
			conformity.setAttribute("conformity_id", conformity_id);
			conformity.setAttribute("txtName", txtName);
			conformity.setModel(new ListModelList(TemplateService.getConformity()));
			conformity.addEventListener("onInitRenderLater", new EventListener() {

				@Override
				public void onEvent(Event evt) throws Exception {
					MyCombobox rbox = (MyCombobox) evt.getTarget();
					rbox.setSelectedItemFromModel(rbox.getAttribute("conformity_id")+"", rbox.getAttribute("model")+"");
					if(rbox.getAttribute("model")!=null){
						RefCBox box = (RefCBox) rbox.getAttribute("rbox");
						if(rbox.getAttribute("model").equals("employee")){
							box.setSelecteditem("text");
							box.setButtonVisible(false);
						} else {
							box.setButtonVisible(rbox.isButtonVisible());
							box.setSelecteditem(Utils.getData("select type_field from ss_bpr_scoring_anket where id="+rbox.getValue(),""));
						}
					}
				}
			});
			conformity.addEventListener(Events.ON_SELECT, new EventListener() {
				
				@Override
				public void onEvent(Event evt) throws Exception {
					MyCombobox mycombo = (MyCombobox) evt.getTarget();
					Comboitem item = mycombo.getSelectedItem();
					String model = (String) item.getAttribute("model");
					RefCBox box = null;
					if(mycombo.getAttribute("rbox")!=null){
						box = (RefCBox) mycombo.getAttribute("rbox");
					}
					if(model.equals("employee")){
						box.setSelecteditem("text");
						box.setButtonVisible(false);
					} else {
						box.setButtonVisible(false);
						box.setSelecteditem(Utils.getData("select type_field from ss_bpr_scoring_anket where id="+mycombo.getValue(),""));
					}
				}
			});
			conformity.setAttribute("del_row", delete_row);
			Label conformity_label = new Label("Соответствие поля");
			Checkbox checkbox = new Checkbox();
			if(required_field==1) checkbox.setChecked(true);
			else checkbox.setChecked(false);
			checkbox.setLabel("Обязательность поля");
			checkbox.setDisabled(system);
//			row.appendChild(label_en);
//			row.appendChild(txtName_en);
			delete_row.setLabel("Удалить поле");
			delete_row.setAttribute("row", row);
			delete_row.addEventListener(Events.ON_CLICK, new EventListener() {
				
				@Override
				public void onEvent(Event evt) throws Exception {
					Row row = ((Row)((Button) evt.getTarget()).getAttribute("row"));
					if(row.getAttribute("OID")!=null){
						String oid = (String) row.getAttribute("OID");
						TemplateService.removeField(bpr_id, oid);
					}
					dRows.removeChild(row);
				}
			});
//			if(model!=null&&model.equalsIgnoreCase("scoring")&&conformity_id!=null&&!conformity_id.equals("")){
//				if(!TemplateService.isSystemRequired(conformity_id, bpr_id+"")){
//					delete_row.setVisible(false);
//				}
//			}
			row.appendChild(labelName);
			row.appendChild(txtName);
			row.appendChild(conformity_label);
			row.appendChild(conformity);
			row.appendChild(labelType);
			row.appendChild(rcbox);
			row.appendChild(checkbox);
			Cell cell = new Cell();
			cell.setColspan(1);
			cell.appendChild(btn);
//			!target_bpr.equals("J")&&conformity_id!=null&&!conformity_id.equals("4")&&!conformity_id.equals("5")&&!conformity_id.equals("6")
			if(system){
				if(target_bpr.equals("J")&&conformity_id!=null&&(conformity_id.equals("4")||conformity_id.equals("5")||conformity_id.equals("6")))
					system = false;
				delete_row.setVisible(!system);
			}
			cell.appendChild(delete_row);
			row.appendChild(cell);
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
		return row;
	}
	
	public void onClick$btn_del(){		
		try {
		  if (!CheckNull.isEmpty(current.getId())){
			Messagebox.show("Вы хотити удалить шаблон анкеты?","Вопрос", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION,
					new org.zkoss.zk.ui.event.EventListener(){
				public void onEvent(Event e){
					if(Messagebox.ON_OK.equals(e.getName())){						
						TemplateService.remove(current.getId());
						refreshModel();
					}
				}
			}
			);
		  }
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
	}	
}
