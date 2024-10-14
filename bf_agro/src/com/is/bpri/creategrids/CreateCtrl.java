package com.is.bpri.creategrids;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.zkoss.util.media.AMedia;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Longbox;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbarbutton;

import com.is.bpri.template_c.TemplateFields;
import com.is.bpri.utils.Utils;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.Res;

public class CreateCtrl extends GenericForwardComposer{

	private static final long serialVersionUID = 1L;
	private Listbox creationList;
	private Div divgrd, divsearch;
	private Toolbarbutton btn_add, btn_save, btn_del, btn_app;
	private RefCBox template, ftype_temp;
	private Datebox fdate_bank;
	private Grid frmgrd;
	private Rows cRows;
	private Textbox fbranch;
	private Create current = new Create();
	private Create filter = null;
	private String uname = "",action = "";
	private Long extparam = null;
	private Row template_row;
	private String alias, branch, Cur_day;
	private Date Curdate;
	private List<Create> LCreate =null;
	static SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	static SimpleDateFormat df_t = new SimpleDateFormat("yyyy-MM-dd");
	private int type_role = 0;
	private long state_t = 0;
	private String state_name = "Введен";
	private String temp_file = "";
	private long fstate=1;
	public CreateCtrl() {
		super('$',false,false);
	}
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		try{super.doAfterCompose(comp);
		alias = (String) session.getAttribute("alias");
		branch = session.getAttribute("branch").toString();
		Cur_day=CreateService.getDay(alias);
		Curdate = df.parse(Cur_day); 		
		String[] param_role = (String[]) param.get("type_role");
		if ((param_role != null)) 
		{
		   type_role = Integer.parseInt(param_role[0]);
		}
		else
		{
		   type_role = 0;
		}
		if (type_role==2){
		   fbranch.setDisabled(true);
		   fbranch.setValue(branch);		   
		}else if(type_role==1){
		   btn_add.setDisabled(true);
		   btn_save.setDisabled(true);
		   btn_del.setDisabled(true);
		   btn_app.setDisabled(true);
		}		
		fdate_bank.setValue(Curdate);		
		creationList.setItemRenderer(new ListitemRenderer() {		
			@Override
			public void render(Listitem row, Object data)
					throws Exception {
				Create create = (Create) data;
				row.setValue(create);
				row.appendChild(new Listcell(create.getId()+""));
				row.appendChild(new Listcell(create.getBranch()));
				row.appendChild(new Listcell(df.format(create.getDate_bank())));
				row.appendChild(new Listcell(create.getName()));
				row.appendChild(new Listcell(create.getTid()+""));
				row.appendChild(new Listcell(create.getTname()));				
				row.appendChild(new Listcell(create.getProcess()));
				row.appendChild(new Listcell(create.getProcess_name()));
				row.appendChild(new Listcell(create.getState_name()));
				row.appendChild(new Listcell(create.getState()+""));
				Listcell lc = new Listcell(create.getUname());
				lc.setVisible(false);
				row.appendChild(lc);
				row.addEventListener(Events.ON_CLICK, new EventListener() {
					@Override
					public void onEvent(Event evt) throws Exception {
						Listitem li = (Listitem) evt.getTarget();
						@SuppressWarnings("unchecked")
						List<Listcell> list = li.getChildren();
						current.setId(Long.parseLong(list.get(0).getLabel()));
						current.setName(list.get(3).getLabel());
						current.setTid(Long.parseLong(list.get(4).getLabel()));
						current.setTname(list.get(5).getLabel());						
						current.setProcess(list.get(6).getLabel());
						current.setProcess_name(list.get(7).getLabel());
						current.setState_name(list.get(8).getLabel());
						current.setState(Long.parseLong(list.get(9).getLabel()));
						current.setUname(list.get(10).getLabel());																						
					}
				});
				row.addEventListener(Events.ON_DOUBLE_CLICK, new EventListener() {
					@Override
					public void onEvent(Event evt) throws Exception {
						creationList.setVisible(false);
						cRowsClear();
						action = "double";
						divgrd.setVisible(true);
						template.setSelecteditem(current.getTid().toString());
						onSelect$template();
						if (current.getState()==1){
							btn_save.setVisible(false);
						}else{
							btn_save.setVisible(true);
						}
					}
				});
			}
		});
		template.setModel(new ListModelList(CreateService.getTemplates()));
		ftype_temp.setModel(new ListModelList(CreateService.getTemplates()));
		String ext[] = (String[]) param.get("exppar");
		filter = new Create();		
		if (type_role==2){
			filter.setBranch(branch);
		}else{
			filter.setState(fstate);
		}
		filter.setDate_bank(Curdate);
		if(ext!=null){
			extparam = Long.parseLong(ext[0]);
			filter.setTid(extparam);
			template_row.setVisible(false);
			template.addEventListener("onInitRenderLater", new EventListener() {

				@Override
				public void onEvent(Event evt) throws Exception {
					template.setSelecteditem(extparam+"");
					onSelect$template();
				}
			});
		} 
		LCreate = CreateService.getModel(filter);
		creationList.setModel(new ListModelList(LCreate));		
		//creationList.setModel(new ListModelList(CreateService.getModel(filter)));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void onClick$btn_add(){
		creationList.setVisible(false);
		divsearch.setVisible(false);
		cRowsClear();
		if(extparam==null){
			template.setValue(null);
		} else {
			template.setSelecteditem(extparam+"");
			onSelect$template();
		}
		divgrd.setVisible(true);
		action = "add";
	}
	
	public void onClick$btn_cancel(){
		creationList.setVisible(true);
		cRowsClear();
		divgrd.setVisible(false);
		divsearch.setVisible(false);
	}
	public void onClick$btn_del(){
		try {
			  if (!CheckNull.isEmpty(current.getName())){
				  if (current.getState()==1){
					  alert("Невозможно удалить состояние Утвержден!");
				  }else{
					  Messagebox.show("Вы хотити удалить "+current.getName()+"?","Вопрос", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION,
							  new org.zkoss.zk.ui.event.EventListener(){
						  public void onEvent(Event e){
							  if(Messagebox.ON_OK.equals(e.getName())){						
								  Res res = new Res();
								  CreateService.delete(current.getId(), current.getTid(), res);
								  if(res.getCode()==-1){
									 alert(res.getName());
								  } else {
									 onClick$btn_cancel();
									 alert("Успешно удалил");
									 LCreate = CreateService.getModel(filter);
									 creationList.setModel(new ListModelList(LCreate));
								  }
							  }
						  }
					  }				
					  );					  
				  }
			  }
			} catch (Exception e) {
				e.printStackTrace();
			}		
	}
	public void onClick$btn_app(){
		try {
			  if (!CheckNull.isEmpty(current.getName())){
				  if (current.getState()==0){  
					  Messagebox.show("Вы хотити утвердить "+current.getName()+"?","Вопрос", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION,
							  new org.zkoss.zk.ui.event.EventListener(){
						  public void onEvent(Event e){
							  if(Messagebox.ON_OK.equals(e.getName())){						
								  Res res = new Res();
								  CreateService.confirm(current.getId(), current.getTid(), res);
								  if(res.getCode()==-1){
									 alert(res.getName());
								  } else {
									 onClick$btn_cancel();
									 alert("Успешно утвердил");
									 LCreate = CreateService.getModel(filter);
									 creationList.setModel(new ListModelList(LCreate));
								  }
							  }						    
						  }
					  }
					  );
				  }
			  }
			} catch (Exception e) {
				e.printStackTrace();
			}		
	}	
	public void onSelect$template(){
		try {
			cRowsClear();
			List<TemplateFields> fields = CreateService.getFields(template.getValue());
			for (int i = 0; i < fields.size(); i++) {
				cRows.appendChild(getRow(fields.get(i)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void onClick$btn_save(){
		if(current==null){
			current = new Create();
		}		
		current.setName("0");
		current.setTid(Long.parseLong(template.getValue()));
		current.setTname(template.getText());
//		String[] str = CreateService.getProcess(Long.parseLong(template.getValue()));
//		current.setProcess(str[0]);
//		current.setProcess_name(str[1]);
		current.setUname(uname);
		current.setBranch(branch);
		current.setState(state_t);
		current.setState_name(state_name);
		current.setDate_bank(Curdate);
		List<CreateValues> list = getValues();
		Res res = new Res();
		if(action.equals("add")){
//			CreateService.save(current,list,res);
		} else if (action.equals("double")) {
			CreateService.update(current, list, res);
		}
		if(res.getCode()==-1){
			alert(res.getName());
		} else {
			onClick$btn_cancel();
			alert("Успешно");
			LCreate = CreateService.getModel(filter);
			creationList.setModel(new ListModelList(LCreate));
			//creationList.setModel(new ListModelList(CreateService.getModel(filter)));
		}
	}
	
	@SuppressWarnings("unchecked")
	private List<CreateValues> getValues(){
		List<CreateValues> list = new ArrayList<CreateValues>();
		List<Row> row = cRows.getChildren();
		for (int i = 0; i < row.size(); i++) {
			CreateValues val = new CreateValues();
			val.setTid(Long.parseLong(template.getValue()));
			if(row.get(i).getChildren().get(1) instanceof Textbox){
				Textbox txt = (Textbox) row.get(i).getChildren().get(1);
				val.setValue(txt.getValue());
				val.setEn_name(txt.getAttribute("en_name")+"");
				val.setOid(Long.parseLong(txt.getAttribute("oid").toString()));
			} else if (row.get(i).getChildren().get(1) instanceof RefCBox){
				RefCBox rbox = (RefCBox) row.get(i).getChildren().get(1);
				val.setValue(rbox.getValue());
				val.setEn_name(rbox.getAttribute("en_name")+"");
				val.setOid(Long.parseLong(rbox.getAttribute("oid").toString()));
			} else if (row.get(i).getChildren().get(1) instanceof Datebox){
				Datebox date = (Datebox) row.get(i).getChildren().get(1);
				val.setValue(date.getText());
				val.setEn_name(date.getAttribute("en_name")+"");
				val.setOid(Long.parseLong(date.getAttribute("oid").toString()));
			} else if (row.get(i).getChildren().get(1) instanceof Longbox){
				Longbox lbox = (Longbox) row.get(i).getChildren().get(1);
				val.setValue(lbox.getText());
				val.setEn_name(lbox.getAttribute("en_name")+"");
				val.setOid(Long.parseLong(lbox.getAttribute("oid").toString()));
			}
			list.add(val);
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	private void cRowsClear(){
		List<Row> listrow = cRows.getChildren();
		int size = listrow.size()-1;
		for (int i = size; i >= 0; i--) {
			cRows.removeChild(listrow.get(i));
		}
	}
	
	private Row getRow(TemplateFields tmp){
		Row row = new Row();
		Label label = new Label(tmp.getLabel_name());
		row.appendChild(label);
		String value = null;
		if(action.equals("double")){
			List<CreateValues> list = CreateService.getValues(current.getId().toString());
			for (int i = 0; i < list.size(); i++) {
				if(list.get(i).getOid().equals(tmp.getOid())){
					value = list.get(i).getValue();
				}
			}
		}
		if(action.equals("add")&&tmp.getModel().equals("employee")){
			System.out.println("Вошли куда надо");
			Long id = tmp.getConformity_id();
			System.out.println("ID = "+id);
			value = Utils.getData("select value from ss_bpr_employees where id="+id+" and branch='"+branch+"'","");
			System.out.println("value = "+value);
		}
		if(tmp.getType_field().equals("text")){
			Textbox txt = new Textbox();
			txt.setAttribute("oid", tmp.getOid().toString());
			txt.setValue(value);
			txt.setWidth("80%");
			txt.setAttribute("en_name", tmp.getLabel_name_en());
			row.appendChild(txt);
		} else if (tmp.getType_field().equals("list")){
			RefCBox rbox = new RefCBox();
			rbox.setAttribute("oid", tmp.getOid().toString());
			rbox.setModel(new ListModelList(CreateService.getReferences(tmp.getSid())));
			rbox.setAttribute("value", value);
			rbox.setWidth("80%");
			rbox.addEventListener("onInitRenderLater", new EventListener() {

				@Override
				public void onEvent(Event evt) throws Exception {
					RefCBox rbox = (RefCBox) evt.getTarget();
					String value = (String) rbox.getAttribute("value");
					rbox.setSelecteditem(value);
				}
			});
			rbox.setAttribute("en_name", tmp.getLabel_name_en());
			row.appendChild(rbox);
		} else if (tmp.getType_field().equals("date")){
			Datebox date = new Datebox();
			date.setAttribute("oid", tmp.getOid().toString());
			date.setFormat("dd.MM.yyyy");
			date.setText(value);
			date.setWidth("40%");
			date.setAttribute("en_name", tmp.getLabel_name_en());
			row.appendChild(date);
		} else if (tmp.getType_field().equals("number")) {
			Longbox lbox = new Longbox();
			lbox.setAttribute("oid", tmp.getOid().toString());
			lbox.setAttribute("en_name", tmp.getLabel_name_en());
			lbox.setText(value);
			lbox.setWidth("40%");
			row.appendChild(lbox);
		}
		return row;
	}
	
	public static String GetStrTodate(String pdate){
		String res="";
		res=pdate.toString();
		if (pdate.toString().length()==10){
			try{
		     	Date cdate = df_t.parse(pdate);				
				res=df.format(cdate);	
			} catch (Exception e) {
				e.printStackTrace();
				res="";
			}						
		}else{
			res="";
		}
		return res;
	}
	public void onClick$btn_search(){
		creationList.setVisible(false);
		divgrd.setVisible(false);
		divsearch.setVisible(true);
	}
	public void onClick$btn_searchCan(){
		creationList.setVisible(true);
		divgrd.setVisible(false);
		divsearch.setVisible(false);
	}
	public void onClick$btn_searchCl(){
		CheckNull.clearForm(frmgrd);
		if (type_role==2){
    	   fbranch.setValue(branch);
		}		
	}	
	public void onClick$btn_searchOK(){
		filter = new Create();		
		if (!CheckNull.isEmpty(fbranch.getValue()))
		{
	       filter.setBranch(fbranch.getValue());		   
		}
		if (!CheckNull.isEmpty(fdate_bank.getValue()))
		{		   
	       filter.setDate_bank(fdate_bank.getValue());
		}
		if (!CheckNull.isEmpty(ftype_temp.getValue()))
		{		   
	       filter.setTid(Long.parseLong(ftype_temp.getValue()));
	       temp_file=CreateService.getBanAndFile(Long.parseLong(ftype_temp.getValue()), 2);
		}else{
		   temp_file="";	
		}
		if(type_role==1){
		   filter.setState(fstate); 	
		}
		creationList.setVisible(true);
		LCreate = CreateService.getModel(filter);
		creationList.setModel(new ListModelList(LCreate));
		//creationList.setModel(new ListModelList(CreateService.getModel(filter)));				
		divgrd.setVisible(false);
		divsearch.setVisible(false);		
	}
	public void onClick$btn_imp_exl(){
	   	Boolean chfile = true;
		if (!temp_file.equals("")){
	   		File f = new File(application.getRealPath("/templates/"+temp_file));
	   		if (!f.exists()){
	   			alert("Нет шаблон в "+application.getRealPath("/templates/"+temp_file));
	   			chfile=false;
	   		}
	   	}else{
	   		alert("Не выбран шаблон в Поиск!");
	   		chfile=false;
	   	}
		if (chfile){ 
			int SizeList = LCreate.size();
			if (SizeList>0){
				InputStream is = null;
				int tid = 0;				
				int row_=CreateService.row_start;
				int col_=0;
				List<GridCols> LCols =null;
				try{
					is = new FileInputStream(application.getRealPath("/templates/"+temp_file));
					HSSFWorkbook myExel;
					myExel = new HSSFWorkbook(is);
					HSSFSheet sheet = myExel.getSheetAt(0);
					HSSFRow row;
					HSSFCellStyle style = myExel.createCellStyle();
					style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
					style.setBorderTop(HSSFCellStyle.BORDER_THIN);
					style.setBorderRight(HSSFCellStyle.BORDER_THIN);
					style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
					Cell cell = null;	
				    row=sheet.getRow(CreateService.row_par);	
					cell = row.getCell(0);					
					String temp = cell.getStringCellValue();	
					temp = temp.replaceAll("<date_rep>", df.format(fdate_bank.getValue()));
					cell.setCellValue(temp);						
					row = sheet.createRow(row_);					
					for (int i = 0; i < SizeList; i++) {
						LCols=CreateService.getGridCols(LCreate.get(i).getId()+"");
						if (tid!=LCreate.get(i).getTid()){
							//row = sheet.createRow(row_);
							//row_++;   				
							tid = LCreate.get(i).getTid().intValue();
							//col_=1;
							//cell = row.createCell(col_);
							//cell.setCellStyle(style);
							//cell.setCellValue("Шаблон - "+LCreate.get(i).getTname());
							//row = sheet.createRow(row_);
							//row_++;
							
							/*cell = row.createCell(col_);
							cell.setCellStyle(style);
							cell.setCellValue("МФО");
							col_++;
							cell = row.createCell(col_);
							cell.setCellStyle(style);
							cell.setCellValue("Дата отчета");
							col_++;
							for (int j = 0; j < LCols.size(); j++) {
								cell = row.createCell(col_);
								cell.setCellStyle(style);
								cell.setCellValue(LCols.get(j).getLabelName());
								System.out.println("lvalue - "+LCols.get(j).getLabelName());
								col_++;
							}*/					
						}
						row = sheet.createRow(row_);
						row_++;
						col_=0;
						/*cell = row.createCell(col_);
						cell.setCellStyle(style);
						cell.setCellValue(LCreate.get(i).getBranch());
						col_++;
						cell = row.createCell(col_);
						cell.setCellStyle(style);
						cell.setCellValue(df.format(LCreate.get(i).getDate_bank()));
						col_++;*/	          	    
						for (int j = 0; j < LCols.size(); j++) {
							cell = row.createCell(col_);
							cell.setCellStyle(style);
							cell.setCellValue(LCols.get(j).getSvalue());
							col_++;
						}								
					}
					ByteArrayOutputStream out = new ByteArrayOutputStream();
					myExel.write(out);
					out.close();
					byte[] arr = out.toByteArray();
					AMedia b = new AMedia(temp_file, "xls", "application/vnd.ms-excel", arr);		        
					Filedownload.save(b);	   			
				} catch (Exception e) {
					e.printStackTrace();
					alert("Ошибка - "+e.getMessage());
				} finally {
					try {
						if(is!=null){
							is.close();
						}
					} catch (IOException e) {
						e.printStackTrace();			
						alert("1. Ошибка - "+e.getMessage());
					}
				}
			}
		}
	}		
}
