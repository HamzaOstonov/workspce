package com.is.clientcrm.addinfo;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.zkoss.util.resource.Labels;
import org.zkoss.web.Attributes;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zkplus.databind.BindingListModelList;
import org.zkoss.zul.Button;
import org.zkoss.zul.Caption;
import org.zkoss.zul.Cell;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Column;
import org.zkoss.zul.Columns;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Decimalbox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Panel;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Separator;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.is.clientcrm.addinfo.list.ParameterList;
import com.is.clientcrm.addinfo.list.ParameterListArray;
import com.is.clientcrm.addinfo.list.ParameterListService;
import com.is.clientcrm.addinfo.list.ParameterListTemplate;
//import com.is.clientcrm.persons.ClientPersonCardViewCtrl;
//import com.is.clientcrm.persons.PersonMap;
//import com.is.clientcrm.persons.PersonMapService;
import com.is.user.User;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.RefData;
import com.is.utils.RefDataService;
import com.is.utils.Res;

public class AddinfoViewController extends GenericForwardComposer {
	private Div addinfo, addinfomaindiv;
	private Panel clientPmain;
	Window personInfoDivDialog = null;
	private Toolbarbutton btn_refresh, btn_save_top, btn_save_bottom, btn_approve, btn_noapprove, btn_delete;
	
   	public List<ParameterGroup> parametergroups = new ArrayList<ParameterGroup>();
   	public List<Parameter> parameters = new ArrayList<Parameter>();
   	public List<Parameter> oldparameters = new ArrayList<Parameter>();
	private String branch1,alias,lang;
	public User usr;
	private AnnotateDataBinder binder;
	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	DecimalFormat dcf = new DecimalFormat("999,999,999,999,999,990.00 ");
	
	private Clientmap current = new Clientmap();
	private ClientAddinfoParameter currentaddinfoparameters = new ClientAddinfoParameter();
	
	private List<RefData> client_types = new ArrayList<RefData>();
	private List<RefData> states = new ArrayList<RefData>();
	private List<RefData> countries = new ArrayList<RefData>();
	private List<RefData> resident_types = new ArrayList<RefData>();
	private List<RefData> client_kinds = new ArrayList<RefData>();
	private List<RefData> vsbs = new ArrayList<RefData>();
	private List<RefData> branches = new ArrayList<RefData>();
	private List<RefData> person_kinds = new ArrayList<RefData>();
	private List<RefData> doctypes = new ArrayList<RefData>();
	private List<RefData> nations = new ArrayList<RefData>();
	private List<RefData> ptypes = new ArrayList<RefData>();
	private List<RefData> gendertypes = new ArrayList<RefData>();
	private List<RefData> regions = new ArrayList<RefData>();
	private List<RefData> districts = new ArrayList<RefData>();
	private List<RefData> capacity = new ArrayList<RefData>();
	private List<RefData> creditklass = new ArrayList<RefData>();
	private List<RefData> gni = new ArrayList<RefData>();
	private List<RefData> ss_dblink_branches = new ArrayList<RefData>();
	private Label lblstate;
	private String client_branch, client_id, client_alias;///*client_code_subject*/
	/*
	ListitemRenderer personrenderer= new ListitemRenderer(){
	@SuppressWarnings("unchecked")
	public void render(Listitem row, Object data) throws Exception {
		PersonMap pPersonMap = (PersonMap) data;
		row.setValue(pPersonMap);
		//row.appendChild(new Listcell(pPersonMap.getId()+""));
		//row.appendChild(new Listcell(pPersonMap.getBranch()));
		//row.appendChild(new Listcell(pPersonMap.getClient_id()));
		row.appendChild(new Listcell(pPersonMap.getPrson_name()+""));
		row.appendChild(new Listcell(ParameterService.lvalue(pPersonMap.getPerson_type(), client_types)));
		//row.appendChild(new Listcell(pPersonMap.getPerson_id()+""));
		row.appendChild(new Listcell(ParameterService.lvalue(pPersonMap.getPerson_kind()+"", person_kinds)));
	}};
	*/
	ListitemRenderer listrenderer= new ListitemRenderer(){
	@SuppressWarnings("unchecked")
	public void render(Listitem row, Object data) throws Exception {
		ParameterList pParameterList = (ParameterList) data;
		row.setValue(pParameterList);
		if (pParameterList.getState() <= 0) row.setVisible(false);
		for (int i = 0; i < pParameterList.getParameters().size(); i++) {
			if (!CheckNull.isEmpty(pParameterList.getParameters().get(i).getParam_visible_t()) && pParameterList.getParameters().get(i).getParam_visible_t()== 1) {
				row.appendChild(new Listcell(getListToolbar(pParameterList.getParameters().get(i))));
			}
		}
	}};
	
	private Long list_id = 0L;
		
	//-----------------------------------
	private Window checklistwnd;
	private Listbox checklistwnd$checklist;
	private List<RefData> checklistref = new ArrayList<RefData>();
	public CheckListRefData currentchecklist = new CheckListRefData();
	private Button checklistwnd$btn_save;
	private Checkbox checklistwnd$checkall;   
	
	private Window listwnd;
	private Div listwnd$listdiv;
	private Grid listwnd$listgrd;
	private Rows listwnd$listgrdrows;
	private Toolbarbutton listwnd$btn_save;
	
	private HashMap<String, String> tegNames = new HashMap<String, String>(){};
	private HashMap<String, List<RefData>> reflist = new HashMap<String, List<RefData>>(){};
	private HashMap<String, List<ParameterListTemplate>> paramlisttemplates = new HashMap<String, List<ParameterListTemplate>>(){};
	private HashMap<String, List<ParameterList>> paramlistparams = new HashMap<String, List<ParameterList>>(){};
	
	DynamicObject addinfoObj = new DynamicObject();
	
	public AddinfoViewController() {
		super('$', false, false);
	}
	
	@Override
    public void doAfterCompose(Component comp) throws Exception {
    	super.doAfterCompose(comp);
    	// TODO Auto-generated method stub
    	binder = new AnnotateDataBinder(comp);
    	binder.bindBean("current", current);
    	binder.bindBean("currentchecklist", currentchecklist);
    	binder.bindBean("parametergroups", parametergroups);
    	binder.bindBean("parameters", parameters);
    	binder.loadAll();
        String[] parameter = (String[]) param.get("branch");
        if (parameter!=null){
        	client_branch = parameter[0];
        } else {
        	alert("Отсутствует параметр 'Филиал клиента'!"); return;
        }
        
        parameter = (String[]) param.get("client_id");
        if (parameter!=null){
        	client_id = parameter[0];
        } else {
        	alert("Отсутствует параметр 'Ид клиента'!"); return;
        }
        /*
        parameter = (String[]) param.get("code_subject");
        if (parameter!=null){
        	client_code_subject = parameter[0];
        } else {
        	alert("Отсутствует параметр 'Тип клиента'!"); return;
        }
        */
        parameter = (String[]) param.get("alias");
        if (parameter!=null){
        	client_alias = parameter[0];
        } else {
        	alert("Отсутствует параметр 'Схема филиала клиента'!"); return;
        }
        current = ParameterService.getClientmap(client_branch, client_id);///*, client_code_subject*/, client_alias);
        if (CheckNull.isEmpty(current.getId())) {
        	alert("По заданным параметрам клиент не найден (Филиал: "+client_branch+"; Ид: "+client_id/*+"; Тип: "+client_code_subject*/+"; Схема:"+client_alias+")!"); return;
        }
        
        checklistwnd$checkall.setAttribute("checkvalue", false);
        
        checklistwnd$checklist.setItemRenderer(new ListitemRenderer(){
        @SuppressWarnings("unchecked")
        public void render(Listitem row, Object data) throws Exception {
        	CheckListRefData pRefData = (CheckListRefData) data;
        	row.setValue(pRefData);
        	row.setTooltiptext(
        			Labels.getLabel("data")+": "+pRefData.getData()+"; \r\n"+
        			Labels.getLabel("label")+": "+pRefData.getLabel()+";");
        	Listcell lc = new Listcell();
        	Checkbox ch = new Checkbox();
        	ch.setId("chlist_"+row.getIndex());
        	ch.setAttribute("rowIndex", row.getIndex());
        	ch.setChecked(pRefData.getIsChecked());
        	ch.addEventListener(Events.ON_CHECK, new EventListener() {
        		public void onEvent(Event event) throws Exception {
        			Checkbox ch = (Checkbox) event.getTarget();
        			checklistwnd$checklist.setSelectedIndex((Integer)ch.getAttribute("rowIndex"));
        			currentchecklist = (CheckListRefData) checklistwnd$checklist.getItemAtIndex((Integer)ch.getAttribute("rowIndex")).getValue();
        			if (currentchecklist.getIsChecked()) {
        				currentchecklist.setIsChecked(false);
        			} else {
        				currentchecklist.setIsChecked(true);
        			}
        		}
        	});
        	lc.appendChild(ch);
        	row.appendChild(lc);
        	row.appendChild(new Listcell(pRefData.getData()));
        	row.appendChild(new Listcell(pRefData.getLabel()));
        }});
        
        lang = (((Locale) session.getAttribute(Attributes.PREFERRED_LOCALE)).getLanguage());
        branch1 = (String) session.getAttribute("branch");
		alias = (String) session.getAttribute("alias");
		usr = (User) session.getAttribute("current_user"); 
		tegNames = ParameterService.getTegNames(lang, alias);
        
		//Execution ex = execution.getDesktop().getFirstPage().getFellow("");
		
		//org.zkoss.zk.ui.metainfo.ZScript zscript = new org.zkoss.zk.ui.metainfo.ZScript(null, "java",
		//		"public void runP1() { alert('message'); }", null);
		//alert(zscript.toString());
		//addinfomaindiv.appendChild(zscript);
		//Page p = execution.getDesktop().getPage("claddinfo");
		//String cmd = " void runP1() { alert('message'); } ";
		//Executions.createComponentsDirectly("<zscript><![CDATA["+cmd+"]]></zscript>", null, addinfomaindiv, null);
		//addinfomaindiv.invalidate();
		//addinfomaindiv.invalidate();
		//Executions.createComponentsDirectly("<zscript>public void runP2() {alert(\"123413254361345\");}</zscript>", null, addinfomaindiv, null);
		//addinfomaindiv.invalidate();
		new String("dasdasd");
		addinfoObj.addToSource(
			"import org.zkoss.zul.*;",
			"import java.text.*;",
			"import java.lang.*;",
			"import java.util.*;",
			"import com.is.utils.*;",
			"import org.zkoss.zk.ui.*;",
			"SimpleDateFormat df = new SimpleDateFormat(\"dd.MM.yyyy\");",
			"",
		    "public void setVisibleLabel ( java.lang.String lbl, java.lang.Boolean bool, org.zkoss.zul.Div addinfo)",
		    "{",
			    "org.zkoss.zul.Label l = (org.zkoss.zul.Label)addinfo.getFellow(lbl);",
			    "l.setVisible(bool);",
		    "}",
		    "public void setMandatory ( java.lang.String cmp, java.lang.Integer bool, org.zkoss.zul.Div addinfo)",
		    "{",
			    "try {",
			    	"org.zkoss.zk.ui.Component comp = (org.zkoss.zk.ui.Component)addinfo.getFellow(cmp);",
			    	"com.is.clientcrm.addinfo.Parameter p = (com.is.clientcrm.addinfo.Parameter)comp.getAttribute(\"parameter\");",
				    "p.setParam_mandatory(bool);",
				    "comp.setAttribute(\"parameter\", p);",
			    "} catch (java.lang.Exception e) { e.printStackTrace(); }",
		    "}",
		    "public void setCompValue ( java.lang.String cmp, java.lang.String value, org.zkoss.zul.Div addinfo)",
		    "{",
			    "try { ",
			    "	SimpleDateFormat df = new SimpleDateFormat(\"dd.MM.yyyy\"); ",
			    "	org.zkoss.zk.ui.Component comp = (org.zkoss.zk.ui.Component)addinfo.getFellow(cmp); ",	
			    //"	System.out.println(comp); ",
			    "	if (comp instanceof org.zkoss.zul.Datebox) { ",
				"		org.zkoss.zul.Datebox dtb = (org.zkoss.zul.Datebox) comp; ",
				"		dtb.setValue(com.is.utils.CheckNull.isEmpty(value)?null:df.parse(value)); ",
			    "	} else if (comp instanceof com.is.utils.RefCBox) { ",
				"		com.is.utils.RefCBox c = (com.is.utils.RefCBox) comp; ",
				"		c.setSelecteditem(value); ",
				"	} else if (comp instanceof org.zkoss.zul.Textbox) { ",
				"		org.zkoss.zul.Textbox t = (org.zkoss.zul.Textbox) comp; ",
				"		t.setValue(value); ",
				"	} ",
		    	"} catch (Exception e) {e.printStackTrace();}",
		    "}",
		    "public void setVisibleLabelList ( java.lang.String lbl, java.lang.Boolean bool, org.zkoss.zul.Window listwnd)",
		    "{",
			    "org.zkoss.zul.Label l = (org.zkoss.zul.Label)listwnd.getFellow(lbl);",
			    "l.setVisible(bool);",
		    "}",
		    "public void setMandatoryList ( java.lang.String cmp, java.lang.Integer bool, org.zkoss.zul.Window listwnd)",
		    "{",
			    "try {",
			    	"org.zkoss.zk.ui.Component comp = (org.zkoss.zk.ui.Component)listwnd.getFellow(cmp);",
			    	"com.is.clientcrm.addinfo.list.ParameterListArray p = (com.is.clientcrm.addinfo.list.ParameterListArray)comp.getAttribute(\"parameter\");",
				    "p.setParam_mandatory(bool);",
				    "comp.setAttribute(\"parameter\", p);",
			    "} catch (java.lang.Exception e) { e.printStackTrace(); }",
		    "}",
		    "public void setCompValueList ( java.lang.String cmp, java.lang.String value, org.zkoss.zul.Window listwnd)",
		    "{",
			    "try { ",
			    "	SimpleDateFormat df = new SimpleDateFormat(\"dd.MM.yyyy\"); ",
			    "	org.zkoss.zk.ui.Component comp = (org.zkoss.zk.ui.Component)listwnd.getFellow(cmp); ",	
			    //"	System.out.println(comp); ",
			    "	if (comp instanceof org.zkoss.zul.Datebox) { ",
				"		org.zkoss.zul.Datebox dtb = (org.zkoss.zul.Datebox) comp; ",
				"		dtb.setValue(com.is.utils.CheckNull.isEmpty(value)?null:df.parse(value)); ",
			    "	} else if (comp instanceof com.is.utils.RefCBox) { ",
				"		com.is.utils.RefCBox c = (com.is.utils.RefCBox) comp; ",
				"		c.setSelecteditem(value); ",
				"	} else if (comp instanceof org.zkoss.zul.Textbox) { ",
				"		org.zkoss.zul.Textbox t = (org.zkoss.zul.Textbox) comp; ",
				"		t.setValue(value); ",
				"	} ",
		    	"} catch (Exception e) {e.printStackTrace();}",
		    "}"/*,
		    	
		    /*,
		    "public java.util.List<com.is.clientcrm.addinfo.Parameter> parameters setParameterMandatory ( java.lang.Long paramid, java.lang.Integer m, org.zkoss.zul.Div addinfo, java.util.List<com.is.clientcrm.addinfo.Parameter> parameters)",
		    "{",
			    "for (int i = 0; i < parameters.size(); i++) { ",
					"if (parameters.get(i).getParam_id() == paramid) { ",
					"	parameters.get(i).setParam_mandatory(m); ",
					"   setVisibleLabel ( \"lbl_\"+paramid, ((m == 1)? true: false), addinfo); ",
					"} ",
				"} ",
				" return parameters; ",
			"}"*/
		);
		//int res = Integer.parseInt(.toString());
		//alert("added = "+res);
		
		init();
		
	}

    private void init() {
    	client_types = ParameterService.getClientTypes(alias);
		ss_dblink_branches = ParameterService.getSSDBLinkBranch(alias);
		states = ParameterService.getClientStates(1, alias);
		countries = ParameterService.getCountries(alias);
		resident_types = ParameterService.getRezKl(alias);
		client_kinds = ParameterService.getTypeKl(alias);
		vsbs = ParameterService.getVSBS(alias);
		branches = RefDataService.get_cur_bank_Mfo(alias);
		person_kinds = ParameterService.getPersonKind(lang, alias);
		doctypes = ParameterService.getDocumentTypes(alias);
		nations = ParameterService.getNations(alias);
		ptypes = ParameterService.getPasportTypes(alias);
		gendertypes = ParameterService.getGenderTypes(alias);
		regions = ParameterService.getRegions(alias);
		districts = ParameterService.getDistricts(alias);
		capacity = ParameterService.getCapacity(alias);
		creditklass = ParameterService.getSKlass(alias);
		gni = ParameterService.getGNI(alias);
		
		refreshModel();
    }
    
    private void refreshModel() {
    	Res resl = ParameterService.loadParameters(current.getBranch(), current.getId_client(), current.getAlias());
		
    	if (resl.getCode() != 0) {
			alert(resl.getName());
			return;
		}
    	
		currentaddinfoparameters = ParameterService.getClientAddinfoParameters(current);		
		if (CheckNull.isEmpty(currentaddinfoparameters.getClient_type())) {
        	alert("По заданным параметрам данные клиента не синхронизированы (Филиал: "+client_branch+"; Ид: "+client_id+"; Схема:"+client_alias+")!"); return;
        }
		
		btn_refresh.setVisible(true);
		btn_refresh.setDisabled(false);
		if (currentaddinfoparameters.getState() == 0 || currentaddinfoparameters.getState() == 1) {//Введен, Неопределен
			//btn_save_top.setVisible(true);
			btn_save_top.setDisabled(false);
			//btn_save_bottom.setVisible(true);
			btn_save_bottom.setDisabled(false);
			//btn_approve.setVisible(true);
			btn_approve.setDisabled(false);
			//btn_approve.setVisible(false);
			btn_noapprove.setDisabled(true);
			btn_delete.setDisabled(false);
			lblstate.setValue("Состояние: "+(currentaddinfoparameters.getState() == 0?"Неопределена":"Введена"));
			
		} else if (currentaddinfoparameters.getState() == 2) {//Утвержден
			//btn_save_top.setVisible(false);
			btn_save_top.setDisabled(true);
			//btn_save_bottom.setVisible(false);
			btn_save_bottom.setDisabled(true);
			//btn_approve.setVisible(false);
			btn_approve.setDisabled(true);
			//btn_approve.setVisible(true);
			btn_noapprove.setDisabled(false);
			btn_delete.setDisabled(true);
			lblstate.setValue("Состояние: Утверждена");
			
		} else {//Закрыт
			//btn_save_top.setVisible(false);
			btn_save_top.setDisabled(true);
			//btn_save_bottom.setVisible(false);
			btn_save_bottom.setDisabled(true);
			//btn_approve.setVisible(false);
			btn_approve.setDisabled(true);
			//btn_approve.setVisible(false);
			btn_noapprove.setDisabled(true);
			btn_delete.setDisabled(true);
			lblstate.setValue("Состояние: Удалена");
			
		}
		createAddInfo();
    }
    
    public void onClick$btn_refresh() {
    	refreshModel();
    }
    
    public void onClick$btn_approve() {
    	if (saveAddInfo(1)) {
    		currentaddinfoparameters.setState(2L);//Утвержден
    		Res res = ParameterService.setApprove(currentaddinfoparameters, usr.getId(), 2);
    		if (res.getCode() == 0) {
    			refreshModel();
    			alert("Данные по доп. реквезитам утверждены!");
    		} else {
    			alert("При утверждении возникла ошибка: "+res.getName());
    		}
    	}
    }
    
    public void onClick$btn_noapprove() {
    	currentaddinfoparameters.setState(1L);//Введен
		Res res = ParameterService.setApprove(currentaddinfoparameters, usr.getId(), 3);
		if (res.getCode() == 0) {
			refreshModel();
			alert("Данные по доп. реквезитам сняты с утверждения!");
		} else {
			alert("При снятии с утверждения возникла ошибка: "+res.getName());
		}
    }
    
    public void onClick$btn_delete() {
    	currentaddinfoparameters.setState(3L);//Удален
		Res res = ParameterService.delete(currentaddinfoparameters, usr.getId(), 4);
		if (res.getCode() == 0) {
			refreshModel();
			alert("Данные по доп. реквезитам переведены в состояние 'Удален'!");
		} else {
			alert("При удалении возникла ошибка: "+res.getName());
		}
    }
    
    public void onClick$btn_save_top() {
    	onClick$btn_save_bottom();
    }
    
    public void onClick$btn_save_bottom() {
    	if (saveAddInfo(0)) {
    		alert("Сохранение прошло успешно");
    	}
    }
    
    private Boolean isFirstInRow(List<Parameter> params, int id){
    	Parameter firstParameter = null;
    	try {
    		if (params.get(id).getParam_type().equalsIgnoreCase("LIST")) return true;
    		if (params.get(id).getParam_align() == 0) return true;
    		if (params.get(id).getParam_align() == 1) return true;
    		if (params.get(id).getParam_align() == 2) {
    			try {
    				firstParameter = params.get(id-1);
    			} catch (Exception e) {
    				firstParameter = null;
				}
    			if (firstParameter == null) return true;
    			if (firstParameter.getParam_align() == 0 || firstParameter.getParam_align() == 2) return true;
    		}
    	} catch (Exception e) {
			e.printStackTrace();
		}
    	return false;
    }
    
    private Boolean isFirstInRowList(List<ParameterListArray> params, int id){
    	ParameterListArray firstParameter = null;
    	try {
    		if (params.get(id).getParam_type().equalsIgnoreCase("LIST")) return true;
    		if (params.get(id).getParam_align() == 0) return true;
    		if (params.get(id).getParam_align() == 1) return true;
    		if (params.get(id).getParam_align() == 2) {
    			try {
    				firstParameter = params.get(id-1);
    			} catch (Exception e) {
    				firstParameter = null;
				}
    			if (firstParameter == null) return true;
    			if (firstParameter.getParam_align() == 0 || firstParameter.getParam_align() == 2) return true;
    		}
    	} catch (Exception e) {
			e.printStackTrace();
		}
    	return false;
    }
    
    private Boolean haveNextInRow(List<Parameter> params, int id){
    	Parameter nextParameter = null;
    	try {
    		nextParameter = params.get(id+1);
		} catch (Exception e) {
			nextParameter = null;
		}
    	if (nextParameter == null) return false;
    	if (nextParameter.getParam_align() == 2) return true;
    	return false;
    }
    
    private Boolean haveNextInRowList(List<ParameterListArray> params, int id){
    	ParameterListArray nextParameter = null;
    	try {
    		nextParameter = params.get(id+1);
		} catch (Exception e) {
			nextParameter = null;
		}
    	if (nextParameter == null) return false;
    	if (nextParameter.getParam_align() == 2) return true;
    	return false;
    }
    
    public void createAddInfo() {
    	String act = "";
		addinfo.getChildren().clear();
		reflist = new HashMap<String, List<RefData>>(){};
		paramlisttemplates = new HashMap<String, List<ParameterListTemplate>>(){};
		paramlistparams = new HashMap<String, List<ParameterList>>(){};
		parametergroups = ParameterService.getParametergroup(current.getCode_subject());
		parameters = ParameterService.getParameters(current.getBranch(), current.getId_client(), current.getCode_subject());
		oldparameters = ParameterService.getParameters(current.getBranch(), current.getId_client(), current.getCode_subject());
		//System.out.println(parametergroups.size()+" ... "+parameters.size());
    	    	
		try {
			for (ParameterGroup parametergroup : parametergroups) {
				List<Parameter> params = ParameterService.getParametersByGroup(parameters, parametergroup);
				//System.out.println("select: "+parametergroups.size()+" ... "+parameters.size()+" ... "+params.size());
				if (params.size() > 0) {
					Groupbox g = new Groupbox();
					g.setMold("3d");
					g.setOpen(parametergroup.getIs_open() == 1); 
					g.setClosable(true);
					g.setWidth("100%");
					Caption caption = new Caption(tegNames.get(parametergroup.getId()));
					g.appendChild(caption);
					Grid grid = new Grid();
					grid.setId("grid_"+parametergroup.getId());
					grid.setWidth("100%");
					Columns columns = new Columns();
					grid.appendChild(columns);
					//grid.getColumns().getChildren().clear();
					Rows rows = new Rows();
					grid.appendChild(rows);
					Column column = new Column();
					column.setAlign("left");   
					column.setWidth("15%");
					grid.getColumns().appendChild(column);  
					column = new Column();
					column.setAlign("left");   
					column.setWidth("35%");
					grid.getColumns().appendChild(column);
					column = new Column();
					column.setAlign("left");   
					column.setWidth("15%");
					grid.getColumns().appendChild(column);
					column = new Column();
					column.setAlign("left");   
					column.setWidth("35%");
					grid.getColumns().appendChild(column);
					
					Div vb = new Div();
					Label lbl = new Label();
					Datebox dtb = new Datebox();
					Combobox c = new Combobox();
					Checkbox ch = new Checkbox();
					Textbox n = new Textbox();
					Textbox t = new Textbox();
					Row row = new Row();
					Cell cell = new Cell();
					List<Parameter> rowparams = new ArrayList<Parameter>();
					for (int i = 0; i < params.size(); i++) {
						if (isFirstInRow(params, i)) {
							rowparams = new ArrayList<Parameter>();
							rowparams.add(params.get(i));
							row = new Row();
							row.setAttribute("group", parametergroup);
							row.setAttribute("parametr_count", 1);
							row.setAttribute("parameterlist", rowparams);
							if (params.get(i).getParam_align() == 2) {
								cell = new Cell();
								cell.setColspan(2);
								row.appendChild(cell);
							}
						} else {
							row.setAttribute("parametr_count", 2);
							rowparams = (List<Parameter>)row.getAttribute("parameterlist");
							rowparams.add(params.get(i));
							row.setAttribute("parameterlist", rowparams);
						}
						if (!params.get(i).getParam_type().equalsIgnoreCase("PERSONGRID")) {
							act = params.get(i).getParam_actions();
							//if (!CheckNull.isEmpty(act)) alert("<zscript><![CDATA[public void runp_"+params.get(i).getParam_id()+"() { "+(CheckNull.isEmpty(act)?"":act)+" }]]></zscript>");
							//Executions.createComponentsDirectly("<zscript><![CDATA[public void runp_"+params.get(i).getParam_id()+"() { "+(CheckNull.isEmpty(act)?"":act)+" }]]></zscript>", null, addinfomaindiv, null);
							cell = new Cell();
							if (params.get(i).getParam_type().equalsIgnoreCase("LIST")) {
								cell.setColspan(4);
							} else {
								cell.setColspan(1);
								cell.setAlign((params.get(i).getParam_align() != 2)?"left":"right");
								cell.appendChild(new Label(tegNames.get(params.get(i).getParam_id())));
								row.appendChild(cell);
								cell = new Cell();
								if (params.get(i).getParam_align() == 0 ) {
									cell.setColspan(3);
								} else {
									cell.setColspan(1);
								}
							}
							vb = new Div();
							vb.setWidth("100%");
							
							lbl = new Label("* Обязательный параметр");
							lbl.setId("lbl_"+params.get(i).getParam_id());
							lbl.setStyle("font-size: 12px; color: grey;");
							if (params.get(i).getParam_mandatory() == 1) {
								lbl.setVisible(true);
							} else {
								lbl.setVisible(false);
							}
							vb.appendChild(lbl);
						}
						if (params.get(i).getParam_type().equals("DATE")){
							vb.appendChild(getDATE(parametergroup, params.get(i)));
						} else if (params.get(i).getParam_type().equals("COMBOBOX")){
							vb.appendChild(getCOMBOBOX(parametergroup, params.get(i)));
							//row.appendChild(c);
						} else if (params.get(i).getParam_type().equals("CHECKBOX")){
							vb.appendChild(getCHECKBOX(parametergroup, params.get(i)));
						} else if (params.get(i).getParam_type().equals("NUMBER")){
							vb.appendChild(getNUMBER(parametergroup, params.get(i)));
							//row.appendChild(n);
						} else if (params.get(i).getParam_type().equals("DECIMAL")){
							vb.appendChild(getDECIMAL(parametergroup, params.get(i)));
							//row.appendChild(n);
						} else if (params.get(i).getParam_type().equals("CHECKLIST")){
							vb.appendChild(getCHECKLIST(parametergroup, params.get(i)));
							//row.appendChild(t);
						} else if (params.get(i).getParam_type().equals("LIST")){
							vb.appendChild(getLIST(parametergroup, params.get(i)));
						/*} else if (params.get(i).getParam_type().equals("PERSONGRID")){
							Div d = new Div();
							d.setWidth("100%");
							if (params.get(i).getParam_mandatory() == 1) {
								lbl = new Label("* Обязательный параметр");
								lbl.setStyle("font-size: 12px; color: grey;");
								d.appendChild(lbl);
							}
							Listbox listbox = new Listbox();
							listbox.setId("p_"+params.get(i).getParam_id());
							listbox.setWidth("100%");
							
							Listhead lh = new Listhead();
							lh.setSizable(true);
							Listheader lhr = new Listheader();
							/*
							lhr.setLabel(Labels.getLabel("mailer.client.branch"));
							lhr.setWidth("100px");
							lh.appendChild(lhr);
							lhr = new Listheader();
							lhr.setLabel(Labels.getLabel("mailer.client.id_client"));
							lhr.setWidth("150px");
							lh.appendChild(lhr);
							lhr = new Listheader();
							*//*
							lhr.setLabel(Labels.getLabel("mailer.client.name"));
							lhr.setStyle("min-width: 300px;");
							lh.appendChild(lhr);
							lhr = new Listheader();
							lhr.setLabel(Labels.getLabel("mailer.client.code_subject"));
							lhr.setWidth("300px");
							lh.appendChild(lhr);
							lhr = new Listheader();
							lhr.setLabel(Labels.getLabel("mailer.client.code_type"));
							lhr.setWidth("300px");
							lh.appendChild(lhr);
							listbox.appendChild(lh);
							listbox.setItemRenderer(personrenderer);
							listbox.setModel(new ListModelList(PersonMapService.getPersonMapList(current.getBranch(), current.getId_client(), params.get(i).getParam_def_value(), alias)));
							listbox.addEventListener(Events.ON_DOUBLE_CLICK, new EventListener() {
								public void onEvent(org.zkoss.zk.ui.event.Event event) throws Exception {
									Listbox l = (Listbox) event.getTarget();
									PersonMap person = (PersonMap) l.getSelectedItem().getValue();
									if (person.getId() <=0) alert("person is null!");
									createPersonInfo(person);
								}
							});
							
							d.appendChild(listbox);
							
							row.setStyle("background: none;");
							row.setSpans("2");
							row.appendChild(d);
						} else if (params.get(i).getParam_type().equals("PERSONLIST")){
							Div d = new Div();
							d.setWidth("100%");
							Listbox listbox = new Listbox();
							listbox.setId("p_"+params.get(i).getParam_id());
							listbox.setWidth("100%");
							
							Listhead lh = new Listhead();
							lh.setSizable(true);
							Listheader lhr = new Listheader();
							/*
							lhr.setLabel(Labels.getLabel("mailer.client.branch"));
							lhr.setWidth("100px");
							lh.appendChild(lhr);
							lhr = new Listheader();
							lhr.setLabel(Labels.getLabel("mailer.client.id_client"));
							lhr.setWidth("150px");
							lh.appendChild(lhr);
							lhr = new Listheader();
							*//*
							lhr.setLabel(Labels.getLabel("mailer.client.name"));
							lhr.setStyle("min-width: 300px;");
							lh.appendChild(lhr);
							lhr = new Listheader();
							lhr.setLabel(Labels.getLabel("mailer.client.code_subject"));
							lhr.setWidth("300px");
							lh.appendChild(lhr);
							lhr = new Listheader();
							lhr.setLabel(Labels.getLabel("mailer.client.code_type"));
							lhr.setWidth("300px");
							lh.appendChild(lhr);
							listbox.appendChild(lh);
							listbox.setItemRenderer(personrenderer);
							listbox.setModel(new ListModelList(PersonMapService.getPersonMapList(current.getBranch(), current.getId_client(), params.get(i).getParam_def_value(), alias)));
							listbox.addEventListener(Events.ON_DOUBLE_CLICK, new EventListener() {
								public void onEvent(org.zkoss.zk.ui.event.Event event) throws Exception {
									Listbox l = (Listbox) event.getTarget();
									PersonMap person = (PersonMap) l.getSelectedItem().getValue();
									if (person.getId() <=0) alert("person is null!");
									createPersonInfo(person);
								}
							});
							
							d.appendChild(listbox);
							
							row.setStyle("background: none;");
							row.setSpans("2");
							row.appendChild(d);*/
						} else {//STRING
							vb.appendChild(getSTRING(parametergroup, params.get(i)));
							//row.appendChild(t);
						}
						if (!params.get(i).getParam_type().equals("PERSONGRID")){
							cell.appendChild(vb);
							row.appendChild(cell);
							if (params.get(i).getParam_align() == 1 && !haveNextInRow(params, i)) {
								cell = new Cell();
								cell.setColspan(2);
								row.appendChild(cell);
							}
						}
						grid.getRows().appendChild(row);
					}
					g.appendChild(grid);
					addinfo.appendChild(g);
				}
			}
			runAtStartAddInfo();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
    public void runAtStartAddInfo() {
    	try {
			List<Parameter> params = parameters;
			if (parameters.size() > 0) {
				Datebox dtb = new Datebox();
				Combobox c = new Combobox();
				Checkbox ch = new Checkbox();
				Textbox n = new Textbox();
				Decimalbox d = new Decimalbox();
				Textbox t = new Textbox();
				Parameter p = null;
				for (int i = 0; i < params.size(); i++) {
					if (params.get(i).getParam_type().equals("DATE")){
						dtb = (Datebox) addinfo.getFellow("p_"+params.get(i).getParam_id());
						p = (Parameter) dtb.getAttribute("parameter");
						if (p.getParam_act_runatstart() == 1) addinfoObj.invoke( "run"+dtb.getId(), dtb, addinfo, usr, 1);
					} else if (params.get(i).getParam_type().equals("COMBOBOX")){
						c = (RefCBox) addinfo.getFellow("p_"+params.get(i).getParam_id());
						p = (Parameter) c.getAttribute("parameter");
						if (p.getParam_act_runatstart() == 1) {
							String select = params.get(i).getParam_select().toUpperCase();
							select = ParameterService.setParamsInSQL(parameters, select, current);
							if (!CheckNull.isEmpty(select)){
								ListModelList list = new ListModelList(ParameterService.getListForCombobox(select,current.getAlias()));
								c.setModel(list);
								c.addEventListener("onInitRenderLater", new EventListener() {
										public void onEvent(org.zkoss.zk.ui.event.Event event) throws Exception {
											RefCBox cc = (RefCBox) event.getTarget();
											Parameter p = (Parameter) cc.getAttribute("parameter");
											if (cc.getAttribute("parameter") != null){
												cc.setSelecteditem(((Parameter)cc.getAttribute("parameter")).getParam_value());
											} else {
												for(int i=0;i<cc.getModel().getSize();i++){
													RefData rd = (RefData) cc.getModel().getElementAt(i);
													if (rd.getData()==null){
														cc.setSelectedIndex(i);
													}
												}
											}
											if (p.getParam_act_runatstart() == 1) addinfoObj.invoke( "run"+cc.getId(), cc, addinfo, usr, 1);
										}
								});
							}
						}
					} else if (params.get(i).getParam_type().equals("CHECKBOX")){
						ch = (Checkbox) addinfo.getFellow("p_"+params.get(i).getParam_id());
						p = (Parameter) ch.getAttribute("parameter");
						if (p.getParam_act_runatstart() == 1) addinfoObj.invoke( "run"+c.getId(), c, addinfo, usr, 1);
					} else if (params.get(i).getParam_type().equals("NUMBER")){
						n = (Textbox) addinfo.getFellow("p_"+params.get(i).getParam_id());
						p = (Parameter) n.getAttribute("parameter");
						if (p.getParam_act_runatstart() == 1) addinfoObj.invoke( "run"+dtb.getId(), dtb, addinfo, usr, 1);
					} else if (params.get(i).getParam_type().equals("DECIMAL")){
						n = (Textbox) addinfo.getFellow("p_"+params.get(i).getParam_id());
						p = (Parameter) n.getAttribute("parameter");
						if (p.getParam_act_runatstart() == 1) addinfoObj.invoke( "run"+n.getId(), n, addinfo, usr, 1);
					} else if (params.get(i).getParam_type().equals("CHECKLIST")){
						t = (Textbox) addinfo.getFellow("p_"+params.get(i).getParam_id());
						p = (Parameter) t.getAttribute("parameter");
						if (p.getParam_act_runatstart() == 1) addinfoObj.invoke( "run"+t.getId(), t, addinfo, usr, 1);
					} else if (params.get(i).getParam_type().equals("PERSONGRID")){
						Listbox listbox = (Listbox) addinfo.getFellow("p_"+params.get(i).getParam_id());
						
					} else if (params.get(i).getParam_type().equals("LIST")){
						Listbox listbox = (Listbox) addinfo.getFellow("p_"+params.get(i).getParam_id());
						p = (Parameter) listbox.getAttribute("parameter");
						if (!CheckNull.isEmpty(p.getParam_act_runatstart()) && p.getParam_act_runatstart() == 1) addinfoObj.invoke( "run"+listbox.getId(), listbox, addinfo, usr, 1);
					} else {//STRING
						t = (Textbox) addinfo.getFellow("p_"+params.get(i).getParam_id());
						p = (Parameter) t.getAttribute("parameter");
						if (p.getParam_act_runatstart() == 1) addinfoObj.invoke( "run"+t.getId(), t, addinfo, usr, 1);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
	public Boolean saveAddInfo(int needcorrect) {
		Boolean res = false;
		try {
			List<Parameter> params = parameters;
			if (parameters.size() > 0) {
				Datebox dtb = new Datebox();
				Combobox c = new Combobox();
				Checkbox ch = new Checkbox();
				Textbox n = new Textbox();
				Decimalbox d = new Decimalbox();
				Textbox t = new Textbox();
				Parameter p = null;
				for (int i = 0; i < params.size(); i++) {
					if (params.get(i).getParam_type().equals("DATE")){ 
						dtb = (Datebox) addinfo.getFellow("p_"+params.get(i).getParam_id());
						p = (Parameter) dtb.getAttribute("parameter");
						if (p.getParam_mandatory() == 1 && CheckNull.isEmpty(dtb.getValue())) {
							alert("Не заполнен обязательный параметр: "+(tegNames.get(params.get(i).getParam_id())));
							return false;
						}
						params.get(i).setParam_value(CheckNull.isEmpty(dtb.getValue())?null:df.format(dtb.getValue()));
					} else if (params.get(i).getParam_type().equals("COMBOBOX")){ 
						c = (RefCBox) addinfo.getFellow("p_"+params.get(i).getParam_id());
						p = (Parameter) c.getAttribute("parameter");
						if (p.getParam_mandatory() == 1 && CheckNull.isEmpty(c.getValue())) {
							alert("Не заполнен обязательный параметр: "+(tegNames.get(params.get(i).getParam_id())));
							return false;
						}
						params.get(i).setParam_value(CheckNull.isEmpty(c.getValue())?null:c.getValue());
					} else if (params.get(i).getParam_type().equals("CHECKBOX")){ 
						ch = (Checkbox) addinfo.getFellow("p_"+params.get(i).getParam_id());
						p = (Parameter) ch.getAttribute("parameter");
						params.get(i).setParam_value(ch.isChecked()?"1":"0");
					} else if (params.get(i).getParam_type().equals("NUMBER")){ 
						n = (Textbox) addinfo.getFellow("p_"+params.get(i).getParam_id());
						p = (Parameter) n.getAttribute("parameter");
						if (p.getParam_mandatory() == 1 && CheckNull.isEmpty(n.getValue())) {
							alert("Не заполнен обязательный параметр: "+(tegNames.get(params.get(i).getParam_id())));
							return false;
						}
						params.get(i).setParam_value(CheckNull.isEmpty(n.getValue())?null:n.getValue());
					} else if (params.get(i).getParam_type().equals("DECIMAL")){ 
						n = (Textbox) addinfo.getFellow("p_"+params.get(i).getParam_id());
						p = (Parameter) n.getAttribute("parameter");
						if (p.getParam_mandatory() == 1 && CheckNull.isEmpty(n.getValue())) {
							alert("Не заполнен обязательный параметр: "+(tegNames.get(params.get(i).getParam_id())));
							return false;
						}
						params.get(i).setParam_value(CheckNull.isEmpty(n.getValue())?null:n.getValue());
					} else if (params.get(i).getParam_type().equals("CHECKLIST")){ 
						t = (Textbox) addinfo.getFellow("p_"+params.get(i).getParam_id());
						p = (Parameter) t.getAttribute("parameter");
						if (p.getParam_mandatory() == 1 && CheckNull.isEmpty(t.getValue())) {
							alert("Не заполнен обязательный параметр: "+(tegNames.get(params.get(i).getParam_id())));
							return false;
						}
						params.get(i).setParam_value(CheckNull.isEmpty(t.getValue())?null:t.getValue());
						//alert("p_"+params.get(i).getParam_id() +" = "+t.getValue()+ " = "+ params.get(i).getParam_value());
					} else if (params.get(i).getParam_type().equals("PERSONGRID")){ 
						Listbox listbox = (Listbox) addinfo.getFellow("p_"+params.get(i).getParam_id());
						if (params.get(i).getParam_mandatory() == 1 && listbox.getModel().getSize()>0) {
							alert("Не заполнен обязательный параметр: "+(tegNames.get(params.get(i).getParam_id())));
							return false;
						}
					} else if (params.get(i).getParam_type().equals("LIST")){ 
						Listbox listbox = (Listbox) addinfo.getFellow("p_"+params.get(i).getParam_id());
						if (params.get(i).getParam_mandatory() == 1 && listbox.getModel().getSize()>0) {
							alert("Не заполнен обязательный параметр: "+(tegNames.get(params.get(i).getParam_id())));
							return false;
						}
					} else {//STRING
						t = (Textbox) addinfo.getFellow("p_"+params.get(i).getParam_id());  
						p = (Parameter) t.getAttribute("parameter");
						if (p.getParam_mandatory() == 1 && CheckNull.isEmpty(t.getValue())) {
							alert("Не заполнен обязательный параметр: "+(tegNames.get(params.get(i).getParam_id())));
							return false;
						}
						params.get(i).setParam_value(CheckNull.isEmpty(t.getValue())?null:t.getValue());
					}
				}
			}
			Res rs = ParameterService.saveAddInfo(params, current, oldparameters, needcorrect, paramlistparams);
    		if (rs.getCode() != 0) {
    			alert(rs.getName());
    			res = false;
    		}  else {
    			parameters = params;
    			res = true;
    		}
    	} catch (Exception e) {
			e.printStackTrace();
			alert("При сохранении произошла ошибка: "+e.getMessage());
			res = false;
		}
		return res;
	}
	
	/*private void createPersonInfo(PersonMap person){
		try{
			if (current.getId()>0) {
				if(personInfoDivDialog == null){
					personInfoDivDialog = (Window)Executions.createComponents("clientpersoncard.zul", clientPmain, null);
			    }
				if (personInfoDivDialog == null) System.out.println("personInfoDivDialog = null!");
				personInfoDivDialog.setVisible(true);
				ClientPersonCardViewCtrl cpvc =(ClientPersonCardViewCtrl)personInfoDivDialog.getAttribute("clientpersoncard$composer");
			    if (cpvc == null) System.out.println("cpvc = null!");
			    cpvc.prepareDiv(person, false);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}*/
	
	public void onClick$btn_save$checklistwnd() {
		String paramvalue = "";
		Parameter p = (Parameter)checklistwnd$btn_save.getAttribute("parameter");
		for (int i = 0; i < checklistwnd$checklist.getModel().getSize(); i++) {
			CheckListRefData chd = (CheckListRefData)checklistwnd$checklist.getModel().getElementAt(i);
			if (chd.getIsChecked()) {
				if (!CheckNull.isEmpty(paramvalue)) paramvalue += ",";
				paramvalue += chd.getData();
				System.out.println(chd.getIsChecked()+" - "+chd.getData()+" - "+chd.getLabel());
			}
		}
		Textbox t = (Textbox) addinfo.getFellow("p_"+p.getParam_id());
		t.setValue(paramvalue);
		addinfoObj.invoke( "run"+t.getId(), t, addinfo, usr, 0);
		checklistwnd.setVisible(false);
	}
	
	public void onClick$btn_cancel$checklistwnd() {
		checklistwnd.setVisible(false);
	}
	
	public void onCheck$checkall$checklistwnd() {
		checklistwnd$checkall.setAttribute("checkvalue", !((Boolean)checklistwnd$checkall.getAttribute("checkvalue")));
		checklistwnd$checkall.setChecked(((Boolean)checklistwnd$checkall.getAttribute("checkvalue")));
		for (int i = 0; i < checklistwnd$checklist.getModel().getSize(); i++) {
			currentchecklist = (CheckListRefData)checklistwnd$checklist.getModel().getElementAt(i);
			currentchecklist.setIsChecked((Boolean)checklistwnd$checkall.getAttribute("checkvalue"));
			Checkbox t = (Checkbox) checklistwnd$checklist.getFellow("chlist_"+i);
			t.setChecked(currentchecklist.getIsChecked());
		}
		
	}
	
	public String getParamValue(String paramid) {
		String res = "";
		for (int i = 0; i < parameters.size(); i++) {
			if (parameters.get(i).getParam_id() == paramid) {
				res = parameters.get(i).getParam_value();
			}
		}
		return res;
	}
	
	public Boolean setParamValue(String paramid, String parvalue) {
		Boolean res = false;
		for (int i = 0; i < parameters.size(); i++) {
			if (parameters.get(i).getParam_id() == paramid) {
				parameters.get(i).setParam_value(parvalue);
				res = true;
			}
		}
		return res;
	}
	//-----------------------------GET COMPONENTS-----------------------------
	private RefCBox getCOMBOBOX(ParameterGroup parametergroup, Parameter p) {
		RefCBox c = null;
		String select = p.getParam_select().toUpperCase();
		/*if (select.indexOf("INFO.GETBRANCH") != -1){
			select = select.replaceAll("INFO.GETBRANCH","'01066'") ;
		}*/
		select = ParameterService.setParamsInSQL(parameters, select, current);
		//System.out.println(p.getParam_id()+": "+select);
		c = new RefCBox();
		c.setId("p_"+p.getParam_id());
		c.setAttribute("parameter", p);
		c.setAttribute("group", parametergroup);
		c.setWidth("100%");
		c.setMold("rounded");
		c.setReadonly(true);
		
		addinfoObj.addToSource(
				"public void run"+c.getId()+"(com.is.utils.RefCBox comp, org.zkoss.zul.Div addinfo, com.is.user.User user, java.lang.Integer afterCompose) ",
			    "{",
			    	(CheckNull.isEmpty(p.getParam_actions())?("System.out.println(\"run"+c.getId()+"\");"):p.getParam_actions()),
			    "}"
			);
		
		if (!CheckNull.isEmpty(select)){
			//c.setModel(new ListModelList(ReportService.getListForCombobox(rowData.getPar_select())));
			//c.setModel(null);
			ListModelList list = new ListModelList(ParameterService.getListForCombobox(select,current.getAlias()));
			c.setModel(list);
			//System.out.println("*** c.getModel().getSize() = "+c.getModel().getSize()+"---"+c.getItemCount()+"---"+c.getItems().isEmpty());
			c.addEventListener("onInitRenderLater", new EventListener() {
					public void onEvent(org.zkoss.zk.ui.event.Event event) throws Exception {
						RefCBox cc = (RefCBox) event.getTarget();
						if (cc.getAttribute("parameter") != null){
							cc.setSelecteditem(((Parameter)cc.getAttribute("parameter")).getParam_value());
						} else {
							for(int i=0;i<cc.getModel().getSize();i++){
								RefData rd = (RefData) cc.getModel().getElementAt(i);
								if (rd.getData()==null){
									cc.setSelectedIndex(i);
								}
							}
						}
					}
			});
		} else {
			c.setModel(null);
		}
		
		c.addEventListener(Events.ON_SELECT, new EventListener() {
			@Override
			public void onEvent(org.zkoss.zk.ui.event.Event event) throws Exception {
				RefCBox cc = (RefCBox) event.getTarget();
				//org.zkoss.xel.Function fn = cc.getPage().getZScriptFunction(addinfomaindiv, "run"+cc.getId(), null);//"claddinfo"
				//fn.invoke(null, null);
				addinfoObj.invoke( "run"+cc.getId(), cc, addinfo, usr, 0);
				/*
				Grid paramsGrid = (Grid)Path.getComponent("//clientmain/grid_"+((ParameterGroup)cc.getAttribute("group")).getId());
				Row row;
				Boolean bool = true;
				List<Parameter> params = ParameterService.getParametersByGroup(parameters, (ParameterGroup)cc.getAttribute("group"));
				for (int i = rowid-1; i < params.size(); i++){
					row = (Row) paramsGrid.getRows().getChildren().get(i);
					List<Row> rws = paramsGrid.getRows().getChildren();
					if(!params.get(i).getPar_name().equalsIgnoreCase("REP_START") && !params.get(i).getPar_name().equalsIgnoreCase("REP_EXEC")){
						if (params.get(i).getPar_type().equals("DATE")){
							/*Datebox dtb1 = (Datebox) row.getChildren().get(1);
							if (dtb1 == null) System.out.println(dtb1.toString());
							//System.out.println(params.get(i).getPar_name()+" = "+dtb1.getValue().toString());
							params.get(i).setPar_value(df.format(new java.sql.Date(dtb1.getValue().getTime())).toString());*/
						/*} else if (params.get(i).getPar_type().equals("COMBOBOX")){
							RefCBox c1 = (RefCBox) row.getChildren().get(1);
							//System.out.println(c1.getId()+".getSelectedIndex() = "+c1.getSelectedIndex()+"==="+c1.getValue());
							if (c1.getId().equals(event.getTarget().getId())){
								bool = false;
								//System.out.println(params.get(i).getPar_name()+" = "+c1.getValue());
								params.get(i).setPar_value(c1.getValue());
							} else {
								if (bool){
									params.get(i).setPar_value(c1.getValue());
								} else {
									params.get(i).setPar_value(null);
									ListModelList list = new ListModelList(ReportService.getListForCombobox(ReportService.setParamsInSQL(params, params.get(i).getPar_select().toUpperCase()),alias));
									c1.setModel(list);
								}
							}
						} else if (params.get(i).getPar_type().equals("NUMBER")){
							//Intbox n1 = (Intbox) row.getChildren().get(1);
							/*Textbox n1 = (Textbox) row.getChildren().get(1);
							//System.out.println(params.get(i).getPar_name()+" = "+n1.getValue().toString());
							params.get(i).setPar_value(n1.getValue().toString());*/
						/*} else {//STRING
							/*Textbox t1 = (Textbox) row.getChildren().get(1);
							//System.out.println(params.get(i).getPar_name()+" = "+t1.getValue().toString());
							params.get(i).setPar_value(t1.getValue().toString());*/
						/*}
					}
				}
				//repParamsGrid.setModel(new ListModelList());
				//repParamsGrid.setModel(new ListModelList(params));
				*/
			
			}
		});
		if (!CheckNull.isEmpty(p.getParam_enable()) && p.getParam_enable().equals("0")) {
			c.setDisabled(true);
		} else {
			c.setDisabled(false);
		}
		return c;
	}
	
	private Datebox getDATE(ParameterGroup parametergroup, Parameter p) {
		Datebox dtb = new Datebox();
		dtb.setWidth("100%");
		dtb.setId("p_"+p.getParam_id());
		dtb.setAttribute("parameter", p);
		if(p.getParam_value()!=null) {
			//n.setValue(Integer.parseInt(rowData.getPar_value()));
			try {
				dtb.setValue(df.parse(p.getParam_value()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		addinfoObj.addToSource(
				"public void run"+dtb.getId()+"(org.zkoss.zul.Datebox comp, org.zkoss.zul.Div addinfo, com.is.user.User user, java.lang.Integer afterCompose) ",
			    "{",
			    	(CheckNull.isEmpty(p.getParam_actions())?("System.out.println(\"run"+dtb.getId()+"\");"):p.getParam_actions()),
			    "}"
			);
		dtb.addEventListener(Events.ON_CHANGE, new EventListener() {
			public void onEvent(org.zkoss.zk.ui.event.Event event) throws Exception {
				Datebox dtbox = (Datebox) event.getTarget();
				addinfoObj.invoke( "run"+dtbox.getId(), dtbox, addinfo, usr, 0);
				//org.zkoss.xel.Function fn = dtbox.getPage().getZScriptFunction(addinfomaindiv, "run"+dtbox.getId(), null);//"claddinfo"
				//fn.invoke(null, null);
			}
		});
		//dtb.addForward("onOK", dtb, "alert('asdasdasd');");
		if (!CheckNull.isEmpty(p.getParam_enable()) && p.getParam_enable().equals("0")) {
			dtb.setReadonly(true);
			dtb.setButtonVisible(false);
		} else {
			dtb.setReadonly(false);
			dtb.setButtonVisible(true);
		}
		return dtb;
	}
	
	private Checkbox getCHECKBOX(ParameterGroup parametergroup, Parameter p) {
		Checkbox ch = new Checkbox();
		ch.setAttribute("parameter", p);
		ch.setId("p_"+p.getParam_id());
		if (!CheckNull.isEmpty(p.getParam_value())) {
			ch.setChecked(p.getParam_value().equalsIgnoreCase("1"));
		} else {
			ch.setChecked(false);
		}
		if (!CheckNull.isEmpty(p.getParam_enable()) && p.getParam_enable().equals("0")) {
			ch.setDisabled(true);
		} else {
			ch.setDisabled(false);
		}
		/*
		addinfoObj.addToSource(
				"public java.util.List<com.is.clientcrm.addinfo.Parameter> run"+c.getId()+"(org.zkoss.zul.Checkbox comp, org.zkoss.zul.Div addinfo, java.util.List<com.is.clientcrm.addinfo.Parameter> parameters) ",
			    "{",
			    	(CheckNull.isEmpty(act)?("System.out.println(\"run"+c.getId()+"\");"):act),
			    	"return parameters; ",
			    "}"
			);*/
		return ch;
	}
	
	private Textbox getNUMBER(ParameterGroup parametergroup, Parameter p) {
		Textbox n = new Textbox();//new Intbox();
		n.setAttribute("parameter", p);
		n.setId("p_"+p.getParam_id());
		n.setWidth("100%");
		n.setConstraint("/^[\\.\\%\\_0-9]+$/:Must be a number");// /%_[0-9]*/
		if(p.getParam_value()!=null) {
			//n.setValue(Integer.parseInt(rowData.getPar_value()));
			n.setValue(p.getParam_value());
		}
		if (!CheckNull.isEmpty(p.getParam_enable()) && p.getParam_enable().equals("0")) {
			n.setReadonly(true);
		} else {
			n.setReadonly(false);
		}
		/*
		addinfoObj.addToSource(
				"public java.util.List<com.is.clientcrm.addinfo.Parameter> run"+n.getId()+"(org.zkoss.zul.Textbox comp, org.zkoss.zul.Div addinfo, java.util.List<com.is.clientcrm.addinfo.Parameter> parameters) ",
			    "{",
			    	(CheckNull.isEmpty(act)?("System.out.println(\"run"+n.getId()+"\");"):act),
			    	"return parameters; ",
			    "}"
			);
			*/
		return n;
	}
	
	private Textbox getDECIMAL(ParameterGroup parametergroup, Parameter p) {
		Textbox n = new Textbox();//new Intbox();
		n.setAttribute("parameter", p);
		n.setId("p_"+p.getParam_id());
		n.setWidth("100%");
		n.setConstraint("/^[\\.\\%\\_0-9]+$/:Must be a number");// /%_[0-9]*/
		if(p.getParam_value()!=null) {
			//n.setValue(Integer.parseInt(rowData.getPar_value()));
			n.setValue(dcf.format(p.getParam_value()));
		}
		if (!CheckNull.isEmpty(p.getParam_enable()) && p.getParam_enable().equals("0")) {
			n.setReadonly(true);
		} else {
			n.setReadonly(false);
		}
		
		/*
		addinfoObj.addToSource(
			"public java.util.List<com.is.clientcrm.addinfo.Parameter> run"+n.getId()+"(org.zkoss.zul.Textbox comp, org.zkoss.zul.Div addinfo, java.util.List<com.is.clientcrm.addinfo.Parameter> parameters) ",
		    "{",
		    	(CheckNull.isEmpty(act)?("System.out.println(\"run"+n.getId()+"\");"):act),
		    	"return parameters; ",
		    "}"
		);*/
		return n;
	}
	
	private Hbox getCHECKLIST(ParameterGroup parametergroup, Parameter p) {
		Hbox h = new Hbox();
		h.setWidth("100%");
		h.setWidths("100%,50px");
		
		Textbox t = new Textbox();
		t.setAttribute("parameter", p);
		t.setId("p_"+p.getParam_id());	
		t.setWidth("100%");
		t.setReadonly(true);
		if(p.getParam_value()!=null) {
			t.setValue(p.getParam_value());
		}
		addinfoObj.addToSource(
				"public void run"+t.getId()+"(org.zkoss.zul.Textbox comp, org.zkoss.zul.Div addinfo, com.is.user.User user, java.lang.Integer afterCompose) ",
			    "{",
			    	(CheckNull.isEmpty(p.getParam_actions())?("System.out.println(\"run"+t.getId()+"\");"):p.getParam_actions()),
			    "}"
			);
		t.addEventListener(Events.ON_CHANGE, new EventListener() {
			public void onEvent(org.zkoss.zk.ui.event.Event event) throws Exception {
				Textbox tbox = (Textbox) event.getTarget();
				addinfoObj.invoke( "run"+tbox.getId(), tbox, addinfo, usr, 0);
				//org.zkoss.xel.Function fn = dtbox.getPage().getZScriptFunction(addinfomaindiv, "run"+dtbox.getId(), null);//"claddinfo"
				//fn.invoke(null, null);
			}
		});
		h.appendChild(t);
		
		Button b = new Button();
		b.setId("b_"+p.getParam_id());
		b.setAttribute("parameter", p);
		b.setAttribute("group", parametergroup);
		b.setWidth("50px");
		b.setImage("/images/edit.png");
		b.setTooltip("Редактирование списка");
		b.addEventListener("onClick", new EventListener() {
			public void onEvent(org.zkoss.zk.ui.event.Event event) throws Exception {
				Button b = (Button) event.getTarget();
				Parameter p = (Parameter)b.getAttribute("parameter");
				Textbox t = (Textbox) addinfo.getFellow("p_"+p.getParam_id());
				String select = p.getParam_select().toUpperCase();
				select = ParameterService.setParamsInSQL(parameters, select, current);
				checklistwnd$checklist.setModel(new BindingListModelList(ParameterService.getListForCheckList(select, alias, t.getValue()), true));
				for (int i = 0; i < checklistwnd$checklist.getModel().getSize(); i++) {
					CheckListRefData chd = (CheckListRefData)checklistwnd$checklist.getModel().getElementAt(i);
					if (chd.getIsChecked()) {
						System.out.println(chd.getIsChecked()+" - "+chd.getData()+" - "+chd.getLabel());
					}
				}
				checklistwnd$checkall.setAttribute("checkvalue", false);
				checklistwnd$checkall.setChecked(false);
				checklistwnd.setVisible(true);
				checklistwnd$btn_save.setAttribute("parameter", p);
			}
		});
		if (!CheckNull.isEmpty(p.getParam_enable()) && p.getParam_enable().equals("0")) {
			b.setDisabled(true);
		} else {
			b.setDisabled(false);
		}
		h.appendChild(b);
		return h;
	}
	
	private Hbox getSN(ParameterGroup parametergroup, Parameter p) {
		Hbox hb = new Hbox();
		hb.setWidth("100%");
		hb.setWidths("40px,100%");
		
		Textbox tsn = new Textbox();
		//tsn.setAttribute("parameter", p);
		tsn.setId("p_s_"+p.getParam_id());	
		tsn.setWidth("40px");
		tsn.addEventListener(Events.ON_OK, new EventListener() {
			public void onEvent(org.zkoss.zk.ui.event.Event event) throws Exception {
				Textbox tsn = (Textbox) event.getTarget();
				Textbox tn = (Textbox)addinfo.getFellow(tsn.getId().replace("p_s_", "p_"));
				tn.focus();
				//addinfoObj.invoke( "run"+tbox1.getId(), tbox, addinfo, usr, 0);
			}
		});
		
		Textbox tn = new Textbox();
		tn.setAttribute("parameter", p);
		tn.setId("p_"+p.getParam_id());	
		tn.setWidth("100%");
		addinfoObj.addToSource(
				"public void run"+tn.getId()+"(org.zkoss.zul.Textbox comp, org.zkoss.zul.Div addinfo, com.is.user.User user, java.lang.Integer afterCompose) ",
			    "{",
			    	(CheckNull.isEmpty(p.getParam_actions())?("System.out.println(\"run"+tn.getId()+"\");"):p.getParam_actions()),
			    "}"
			);
		
		tn.addEventListener(Events.ON_CHANGE, new EventListener() {
			public void onEvent(org.zkoss.zk.ui.event.Event event) throws Exception {
				Textbox tsn = (Textbox) event.getTarget();
				addinfoObj.invoke( "run"+tsn.getId(), tsn, addinfo, usr, 0);
			}
		});
		
		if(p.getParam_value()!=null) {
			String[] prms = p.getParam_value().split("-");
			if (prms.length > 1) {
				tsn.setValue(prms[0]);
				String nnn = "";
				for (int i = 1; i < prms.length; i++) {
					nnn+=prms[i];
				}
				tn.setValue(nnn);
			} else {
				tsn.setValue("");
				tn.setValue(p.getParam_value());
			}
		}
		hb.appendChild(tsn);
		hb.appendChild(tn);
		return hb;
	}
	
	private Textbox getSTRING(ParameterGroup parametergroup, Parameter p) {
		Textbox t = new Textbox();
		t.setAttribute("parameter", p);
		t.setId("p_"+p.getParam_id());	
		t.setWidth("100%");
		if (p.getParam_type().startsWith("STRING(")) {
			try {
				int rownum = Integer.parseInt(p.getParam_type().replace("STRING(", "").replace(")", ""));
				t.setRows(rownum);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		addinfoObj.addToSource(
				"public void run"+t.getId()+"(org.zkoss.zul.Textbox comp, org.zkoss.zul.Div addinfo, com.is.user.User user, java.lang.Integer afterCompose) ",
			    "{",
			    	(CheckNull.isEmpty(p.getParam_actions())?("System.out.println(\"run"+t.getId()+"\");"):p.getParam_actions()),
			    "}"
			);
		
		t.addEventListener(Events.ON_CHANGE, new EventListener() {
			public void onEvent(org.zkoss.zk.ui.event.Event event) throws Exception {
				Textbox tbox = (Textbox) event.getTarget();
				addinfoObj.invoke( "run"+tbox.getId(), tbox, addinfo, usr, 0);
				//org.zkoss.xel.Function fn = dtbox.getPage().getZScriptFunction(addinfomaindiv, "run"+dtbox.getId(), null);//"claddinfo"
				//fn.invoke(null, null);
			}
		});
		
		if (!CheckNull.isEmpty(p.getParam_enable()) && p.getParam_enable().equals("0")) {
			t.setReadonly(true);
		} else {
			t.setReadonly(false);
		}
		if(p.getParam_value()!=null) {
			t.setValue(p.getParam_value());
		}
		return t;
	}
	
	public Div getLIST(ParameterGroup parametergroup, Parameter p) {
		List<ParameterListTemplate> listtemplates = null;
		if (paramlisttemplates.containsKey(p.getParam_id())) {
			listtemplates = paramlisttemplates.get(p.getParam_id());
		} else {
			listtemplates = ParameterListService.getParameterListTemplates(p); 
			paramlisttemplates.put(p.getParam_id(), listtemplates);
		}
		List<ParameterList> listparams = null;
		listparams = ParameterListService.getParameterlists(p);
		paramlistparams.remove(p.getParam_id());
		paramlistparams.put(p.getParam_id(), listparams);
		/*
		if (paramlistparams.containsKey(p.getParam_id())) {
			listparams = paramlistparams.get(p.getParam_id());
		} else {
			listparams = ParameterListService.getParameterlists(p);
			paramlistparams.put(p.getParam_id(), listparams);
		}
		*/
		Div d = new Div();
		d.setWidth("100%");
		d.appendChild(getListToolbar(parametergroup, p));
		
		Listbox listbox = new Listbox();
		listbox.setId("p_"+p.getParam_id());
		listbox.setWidth("100%");
		listbox.setAttribute("parameter", p);
		listbox.setAttribute("group", parametergroup);
		listbox.setAttribute("listtemplates", listtemplates);
		listbox.setAttribute("listparams", listparams);
		listbox.setRows(listparams.size() > 3 ? listparams.size() : 3);
		
		Listhead lh = new Listhead();
		lh.setSizable(true);
		for (int j = 0; j < listtemplates.size(); j++) {
			if (!CheckNull.isEmpty(listtemplates.get(j).getParam_visible_t()) && listtemplates.get(j).getParam_visible_t()== 1) {
				Listheader lhr = new Listheader();
				lhr.setLabel(tegNames.get(listtemplates.get(j).getParam_list_teg()));
				if (!CheckNull.isEmpty(listtemplates.get(j).getParam_width_t())) lhr.setWidth(listtemplates.get(j).getParam_width_t());
				lh.appendChild(lhr);
			}
		}
		listbox.appendChild(lh);
		
		listbox.setItemRenderer(listrenderer);
		listbox.setModel(new BindingListModelList(listparams, true));
		listbox.addEventListener(Events.ON_DOUBLE_CLICK, new EventListener() {
			public void onEvent(org.zkoss.zk.ui.event.Event event) throws Exception {
				Listbox l = (Listbox) event.getTarget();
				Parameter p = (Parameter)event.getTarget().getAttribute("parameter");
	        	ParameterGroup pg = (ParameterGroup)event.getTarget().getAttribute("group");
	        	if (l == null) {
	        		alert("Список p_"+p.getParam_id()+" не найден!");
	        		return;
	        	}
	        	Listitem item = l.getSelectedItem();
	        	if (item == null) {
	        		alert("Не выбран объект для редактирования!");
	        		return;
	        	}
	        	ParameterList pl = (ParameterList)item.getValue();
	        	List<ParameterList> listparams = (List<ParameterList>)l.getAttribute("listparams");
	        	showListWindow(pg, p, pl, listparams);
			}
		});
		addinfoObj.addToSource(
				"public void run"+listbox.getId()+"(org.zkoss.zul.Listbox comp, org.zkoss.zul.Div addinfo, com.is.user.User user, java.lang.Integer afterCompose) ",
			    "{",
			    	(CheckNull.isEmpty(p.getParam_actions())?("System.out.println(\"run"+listbox.getId()+"\");"):p.getParam_actions()),
			    "}"
			);
		d.appendChild(listbox);
		
		return d;
	}
	
	public Toolbar getListToolbar(ParameterGroup parametergroup, Parameter p) {
		Toolbar toolbar = new Toolbar();
		Hbox hb = new Hbox();
		hb.setHeight("30px");
		hb.setAlign("center");
		hb.setWidth("100%");
		hb.setWidths("10px,100%,100px");
		
		Hbox hb1 = new Hbox();
		hb1.setHeight("30px");
		hb1.setAlign("center");
		hb1.setWidth("100px");
		
		
		Separator s = new Separator();
		s.setOrient("vertical");
		s.setBar(true);
		s.setSpacing("20px");
		hb1.appendChild(s);
		
		Toolbarbutton tbbtn = new Toolbarbutton();
		tbbtn.setId("b_add_"+p.getParam_id());
		tbbtn.setImage("images/+.png");
		tbbtn.setTooltiptext("Добавить");
		tbbtn.setAttribute("parameter", p);
		tbbtn.setAttribute("group", parametergroup);
		tbbtn.addEventListener(Events.ON_CLICK, new EventListener() {
	        public void onEvent(Event event) throws Exception {
	        	Parameter p = (Parameter)event.getTarget().getAttribute("parameter");
	        	ParameterGroup pg = (ParameterGroup)event.getTarget().getAttribute("group");
	        	Listbox l = (Listbox)addinfo.getFellow("p_"+p.getParam_id());
	        	if (l == null) {
	        		alert("Список p_"+p.getParam_id()+" не найден!");
	        		return;
	        	}
	        	list_id = list_id - 1L;
	        	List<ParameterListTemplate> listtemplates = paramlisttemplates.get(p.getParam_id());
	    		ParameterList pl = new ParameterList(p.getClient_type(), p.getBranch(), p.getClient_id(), p.getParam_id(), list_id, 1);
	        	List<ParameterListArray> pars = new ArrayList<ParameterListArray>();
	        	for (int i = 0; i < listtemplates.size(); i++) {
	        		pars.add(new ParameterListArray(
	        				listtemplates.get(i).getClient_type(), 
	        				listtemplates.get(i).getParam_id(), 
	        				listtemplates.get(i).getParam_list_teg(), 
	        				listtemplates.get(i).getParam_select(), 
	        				listtemplates.get(i).getParam_type(), 
	        				listtemplates.get(i).getParam_mask(), 
	        				listtemplates.get(i).getParam_def_value(), 
	        				listtemplates.get(i).getParam_ord(), 
	        				listtemplates.get(i).getParam_mandatory(), 
	        				listtemplates.get(i).getParam_align(), 
	        				listtemplates.get(i).getParam_constraints(), 
	        				listtemplates.get(i).getParam_enable(), 
	        				listtemplates.get(i).getParam_visible(), 
	        				listtemplates.get(i).getParam_visible_t(), 
	        				listtemplates.get(i).getParam_actions(), 
	        				listtemplates.get(i).getParam_act_runatstart(), 
	        				listtemplates.get(i).getParam_def_value(), 
	        				1));
				}
	        	pl.setParameters(pars);
	        	List<ParameterList> listparams = (List<ParameterList>)l.getAttribute("listparams");
	        	showListWindow(pg, p, pl, listparams);
	        }
	    });
		tbbtn.setDisabled(currentaddinfoparameters.getState() == 2  || currentaddinfoparameters.getState() == 3);
		hb1.appendChild(tbbtn);
		
		tbbtn = new Toolbarbutton();
		tbbtn.setId("b_remove_"+p.getParam_id());
		tbbtn.setImage("images/-.png");
		tbbtn.setTooltiptext("Удалить");
		tbbtn.setAttribute("parameter", p);
		tbbtn.setAttribute("group", parametergroup);
		tbbtn.addEventListener(Events.ON_CLICK, new EventListener() {
	        public void onEvent(Event event) throws Exception {
	        	Parameter p = (Parameter)event.getTarget().getAttribute("parameter");
	        	ParameterGroup pg = (ParameterGroup)event.getTarget().getAttribute("group");
	        	Listbox l = (Listbox)addinfo.getFellow("p_"+p.getParam_id());
	        	if (l == null) {
	        		alert("Список p_"+p.getParam_id()+" не найден!");
	        		return;
	        	}
	        	Listitem item = l.getSelectedItem();
	        	if (item == null) {
	        		alert("Не выбран объект для удаления!");
	        		return;
	        	}
	        	ParameterList pl = (ParameterList)item.getValue();
	        	List<ParameterList> listparams = (List<ParameterList>)l.getAttribute("listparams");
	        	if (pl.getList_id() <= 0) {
	        		listparams.remove(pl);
	        		l.setModel(new BindingListModelList(listparams, true));
	        		alert("Запись удалена.");
	        	} else {
	        		for (int j = 0; j < listparams.size(); j++) {
						if (listparams.get(j).getList_id() == pl.getList_id()) {
							listparams.get(j).setState(0);
							l.setModel(new BindingListModelList(listparams, true));
							alert("Запись удалена.");
							return;
						}
					}
	        	}
	        	
	        }
	    });
		tbbtn.setDisabled(currentaddinfoparameters.getState() == 2  || currentaddinfoparameters.getState() == 3);
		hb1.appendChild(tbbtn);
		
		tbbtn = new Toolbarbutton();
		tbbtn.setId("b_edit_"+p.getParam_id());
		tbbtn.setImage("images/edit.png");
		tbbtn.setTooltiptext("Редактировать");
		tbbtn.setAttribute("parameter", p);
		tbbtn.setAttribute("group", parametergroup);
		tbbtn.addEventListener(Events.ON_CLICK, new EventListener() {
	        public void onEvent(Event event) throws Exception {
	        	Parameter p = (Parameter)event.getTarget().getAttribute("parameter");
	        	ParameterGroup pg = (ParameterGroup)event.getTarget().getAttribute("group");
	        	Listbox l = (Listbox)addinfo.getFellow("p_"+p.getParam_id());
	        	if (l == null) {
	        		alert("Список p_"+p.getParam_id()+" не найден!");
	        		return;
	        	}
	        	Listitem item = l.getSelectedItem();
	        	if (item == null) {
	        		alert("Не выбран объект для редактирования!");
	        		return;
	        	}
	        	ParameterList pl = (ParameterList)item.getValue();
	        	List<ParameterList> listparams = (List<ParameterList>)l.getAttribute("listparams");
	        	showListWindow(pg, p, pl, listparams);
	        }
	    });
		tbbtn.setDisabled(currentaddinfoparameters.getState() == 2  || currentaddinfoparameters.getState() == 3);
		hb1.appendChild(tbbtn);
		
		s = new Separator();
		s.setOrient("vertical");
		s.setBar(false);
		s.setSpacing("10px");
		hb.appendChild(s);
		Label l = new Label(tegNames.get(p.getParam_id()));
		l.setStyle("font-size: 16px; font-weight: bold");
		hb.appendChild(l);
		
		hb.appendChild(hb1);
		toolbar.appendChild(hb);
		
		return toolbar;
	}
	
	public void showListWindow(ParameterGroup parametergroup, Parameter p, ParameterList pl, List<ParameterList> listparams) {
		listwnd$btn_save.setAttribute("group", parametergroup);
		listwnd$btn_save.setAttribute("parameter", p);
		listwnd$btn_save.setAttribute("parameterlist", pl);
		listwnd$btn_save.setAttribute("listparams", listparams);
		listwnd$listgrdrows.getChildren().clear();
		Div vb = new Div();
		Label lbl = new Label();
		Row row = new Row();
		Cell cell = new Cell();
		List<ParameterListArray> rowparams = new ArrayList<ParameterListArray>();
		for (int i = 0; i < pl.getParameters().size(); i++) {
			if (isFirstInRowList(pl.getParameters(), i)) {
				rowparams = new ArrayList<ParameterListArray>();
				rowparams.add(pl.getParameters().get(i));
				row = new Row();
				row.setAttribute("group", parametergroup);
				row.setAttribute("parametr_count", 1);
				row.setAttribute("parameterlist", rowparams);
				if (pl.getParameters().get(i).getParam_align() == 2) {
					cell = new Cell();
					cell.setColspan(2);
					row.appendChild(cell);
				}
			} else {
				row.setAttribute("parametr_count", 2);
				rowparams = (List<ParameterListArray>)row.getAttribute("parameterlist");
				rowparams.add(pl.getParameters().get(i));
				row.setAttribute("parameterlist", rowparams);
			}
			cell = new Cell();
			cell.setColspan(1);
			cell.setAlign((pl.getParameters().get(i).getParam_align() != 2)?"left":"right");
			cell.appendChild(new Label(tegNames.get(pl.getParameters().get(i).getParam_list_teg())));
			row.appendChild(cell);
			cell = new Cell();
			if (pl.getParameters().get(i).getParam_align() == 0 ) {
				cell.setColspan(3);
			} else {
				cell.setColspan(1);
			}
			vb = new Div();
			vb.setWidth("100%");
			
			lbl = new Label("* Обязательный параметр");
			lbl.setId("lbl_"+pl.getParameters().get(i).getParam_list_teg());
			lbl.setStyle("font-size: 12px; color: grey;");
			if (pl.getParameters().get(i).getParam_mandatory() == 1) {
				lbl.setVisible(true);
			} else {
				lbl.setVisible(false);
			}
			vb.appendChild(lbl);
			if (pl.getParameters().get(i).getParam_type().equals("DATE")){
				vb.appendChild(getDATE(parametergroup, pl.getParameters().get(i)));
			} else if (pl.getParameters().get(i).getParam_type().equals("COMBOBOX")){
				vb.appendChild(getCOMBOBOX(parametergroup, pl.getParameters().get(i)));
				//row.appendChild(c);
			} else if (pl.getParameters().get(i).getParam_type().equals("CHECKBOX")){
				vb.appendChild(getCHECKBOX(parametergroup, pl.getParameters().get(i)));
			} else if (pl.getParameters().get(i).getParam_type().equals("NUMBER")){
				vb.appendChild(getNUMBER(parametergroup, pl.getParameters().get(i)));
				//row.appendChild(n);
			} else if (pl.getParameters().get(i).getParam_type().equals("DECIMAL")){
				vb.appendChild(getDECIMAL(parametergroup, pl.getParameters().get(i)));
				//row.appendChild(n);
			} else if (pl.getParameters().get(i).getParam_type().equals("CHECKLIST")){
				vb.appendChild(getCHECKLIST(parametergroup, pl.getParameters().get(i)));
				//row.appendChild(t);
			} else {//STRING
				vb.appendChild(getSTRING(parametergroup, pl.getParameters().get(i)));
				//row.appendChild(t);
			}
			
			cell.appendChild(vb);
			row.appendChild(cell);
			if (p.getParam_align() == 1 && !haveNextInRowList(pl.getParameters(), i)) {
				cell = new Cell();
				cell.setColspan(2);
				row.appendChild(cell);
			}
			listwnd$listgrdrows.appendChild(row);
			
			
		}
		listwnd$btn_save.setDisabled(currentaddinfoparameters.getState() == 2 || currentaddinfoparameters.getState() == 3);
		listwnd.setVisible(true);
	}
	
	public String getListToolbar(ParameterListArray p) {
		String res = "";
		if (p.getParam_type().equalsIgnoreCase("COMBOBOX")) {
			List<RefData> spr = null;
			if (reflist.containsKey(p.getParam_id()+"_"+p.getParam_list_teg())) {
				spr = reflist.get(p.getParam_id()+"_"+p.getParam_list_teg());
			} else {
				String select = p.getParam_select().toUpperCase();
				select = ParameterService.setParamsInSQL(parameters, select, current);
				spr = ParameterService.getListForCombobox(select,alias);
				reflist.put(p.getParam_id()+"_"+p.getParam_list_teg(), spr);
			}
			res = lvalue(p.getParam_value(), spr);
		} else {
			res = p.getParam_value();
		}
		
		return res;
	}
	
	public static String  lvalue(String val, List<RefData> dp) {
	    String res="";
	    if ( dp != null ) {
	        for (int i = 0; i < dp.size(); i++) {
	            if ( val.equals(dp.get(i).getData())) {
	                res = dp.get(i).getLabel();
	    }    }    }
	    return res;
	}
	
	private RefCBox getCOMBOBOX(ParameterGroup parametergroup, ParameterListArray p) {
		RefCBox c = null;
		String select = p.getParam_select().toUpperCase();
		select = ParameterService.setParamsInSQL(parameters, select, current);
		//System.out.println(p.getParam_id()+": "+select);
		c = new RefCBox();
		c.setId("p_"+p.getParam_list_teg());
		c.setAttribute("parameter", p);
		c.setAttribute("group", parametergroup);
		c.setWidth("100%");
		c.setMold("rounded");
		c.setReadonly(true);
		addinfoObj.addToSource(
			"public void run"+c.getId()+"(com.is.utils.RefCBox comp, org.zkoss.zul.Div addinfo, com.is.user.User user, java.lang.Integer afterCompose, org.zkoss.zul.Window listwnd) ",
		    "{",
		    	(CheckNull.isEmpty(p.getParam_actions())?("System.out.println(\"run"+c.getId()+"\");"):p.getParam_actions()),
		    "}"
		);
		if (!CheckNull.isEmpty(select)){
			ListModelList list = new ListModelList(ParameterService.getListForCombobox(select,current.getAlias()));
			c.setModel(list);
			c.addEventListener("onInitRenderLater", new EventListener() {
					public void onEvent(org.zkoss.zk.ui.event.Event event) throws Exception {
						RefCBox cc = (RefCBox) event.getTarget();
						if (cc.getAttribute("parameter") != null){
							cc.setSelecteditem(((ParameterListArray)cc.getAttribute("parameter")).getParam_value());
						} else {
							for(int i=0;i<cc.getModel().getSize();i++){
								RefData rd = (RefData) cc.getModel().getElementAt(i);
								if (rd.getData()==null){
									cc.setSelectedIndex(i);
								}
							}
						}
					}
			});
		} else {
			c.setModel(null);
		}
		
		c.addEventListener(Events.ON_SELECT, new EventListener() {
			
			@Override
			public void onEvent(Event event) throws Exception {
				RefCBox cc = (RefCBox) event.getTarget();
				addinfoObj.invoke( "run"+cc.getId(), cc, addinfo, usr, 0, listwnd);
			}
		});
		
		return c;
	}
	
	private Datebox getDATE(ParameterGroup parametergroup, ParameterListArray p) {
		Datebox dtb = new Datebox();
		dtb.setWidth("100%");
		dtb.setId("p_"+p.getParam_list_teg());
		dtb.setAttribute("parameter", p);
		if(p.getParam_value()!=null) {
			//n.setValue(Integer.parseInt(rowData.getPar_value()));
			try {
				dtb.setValue(df.parse(p.getParam_value()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		addinfoObj.addToSource(
				"public void run"+dtb.getId()+"(org.zkoss.zul.Datebox comp, org.zkoss.zul.Div addinfo, com.is.user.User user, java.lang.Integer afterCompose, org.zkoss.zul.Window listwnd) ",
			    "{",
			    	(CheckNull.isEmpty(p.getParam_actions())?("System.out.println(\"run"+dtb.getId()+"\");"):p.getParam_actions()),
			    "}"
			);
		dtb.addEventListener(Events.ON_CHANGE, new EventListener() {
			public void onEvent(org.zkoss.zk.ui.event.Event event) throws Exception {
				Datebox dtbox = (Datebox) event.getTarget();
				addinfoObj.invoke( "run"+dtbox.getId(), dtbox, addinfo, usr, 0, listwnd);
				//org.zkoss.xel.Function fn = dtbox.getPage().getZScriptFunction(addinfomaindiv, "run"+dtbox.getId(), null);//"claddinfo"
				//fn.invoke(null, null);
			}
		});
		//dtb.addForward("onOK", dtb, "alert('asdasdasd');");
		return dtb;
	}
	
	private Checkbox getCHECKBOX(ParameterGroup parametergroup, ParameterListArray p) {
		Checkbox ch = new Checkbox();
		ch.setAttribute("parameter", p);
		ch.setId("p_"+p.getParam_list_teg());
		if (!CheckNull.isEmpty(p.getParam_value())) {
			ch.setChecked(p.getParam_value().equalsIgnoreCase("1"));
		} else {
			ch.setChecked(false);
		}
		/*
		addinfoObj.addToSource(
				"public java.util.List<com.is.clientcrm.addinfo.Parameter> run"+c.getId()+"(org.zkoss.zul.Checkbox comp, org.zkoss.zul.Div addinfo, java.util.List<com.is.clientcrm.addinfo.Parameter> parameters) ",
			    "{",
			    	(CheckNull.isEmpty(act)?("System.out.println(\"run"+c.getId()+"\");"):act),
			    	"return parameters; ",
			    "}"
			);*/
		return ch;
	}
	
	private Textbox getNUMBER(ParameterGroup parametergroup, ParameterListArray p) {
		Textbox n = new Textbox();//new Intbox();
		n.setAttribute("parameter", p);
		n.setId("p_"+p.getParam_list_teg());
		n.setWidth("100%");
		n.setConstraint("/^[\\.\\%\\_0-9]+$/:Must be a number");// /%_[0-9]*/
		if(p.getParam_value()!=null) {
			//n.setValue(Integer.parseInt(rowData.getPar_value()));
			n.setValue(p.getParam_value());
		}
		/*
		addinfoObj.addToSource(
				"public java.util.List<com.is.clientcrm.addinfo.Parameter> run"+n.getId()+"(org.zkoss.zul.Textbox comp, org.zkoss.zul.Div addinfo, java.util.List<com.is.clientcrm.addinfo.Parameter> parameters) ",
			    "{",
			    	(CheckNull.isEmpty(act)?("System.out.println(\"run"+n.getId()+"\");"):act),
			    	"return parameters; ",
			    "}"
			);
			*/
		return n;
	}
	
	private Textbox getDECIMAL(ParameterGroup parametergroup, ParameterListArray p) {
		Textbox n = new Textbox();//new Intbox();
		n.setAttribute("parameter", p);
		n.setId("p_"+p.getParam_list_teg());
		n.setWidth("100%");
		n.setConstraint("/^[\\.\\%\\_0-9]+$/:Must be a number");// /%_[0-9]*/
		if(p.getParam_value()!=null) {
			//n.setValue(Integer.parseInt(rowData.getPar_value()));
			n.setValue(dcf.format(p.getParam_value()));
		}
		/*
		addinfoObj.addToSource(
			"public java.util.List<com.is.clientcrm.addinfo.Parameter> run"+n.getId()+"(org.zkoss.zul.Textbox comp, org.zkoss.zul.Div addinfo, java.util.List<com.is.clientcrm.addinfo.Parameter> parameters) ",
		    "{",
		    	(CheckNull.isEmpty(act)?("System.out.println(\"run"+n.getId()+"\");"):act),
		    	"return parameters; ",
		    "}"
		);*/
		return n;
	}
	
	private Hbox getCHECKLIST(ParameterGroup parametergroup, ParameterListArray p) {
		Hbox h = new Hbox();
		h.setWidth("100%");
		h.setWidths("100%,50px");
		
		Textbox t = new Textbox();
		t.setAttribute("parameter", p);
		t.setId("p_"+p.getParam_list_teg());	
		t.setWidth("100%");
		t.setReadonly(true);
		if(p.getParam_value()!=null) {
			t.setValue(p.getParam_value());
		}
		addinfoObj.addToSource(
				"public void run"+t.getId()+"(org.zkoss.zul.Textbox comp, org.zkoss.zul.Div addinfo, com.is.user.User user, java.lang.Integer afterCompose, org.zkoss.zul.Window listwnd) ",
			    "{",
			    	(CheckNull.isEmpty(p.getParam_actions())?("System.out.println(\"run"+t.getId()+"\");"):p.getParam_actions()),
			    "}"
			);
		t.addEventListener(Events.ON_CHANGE, new EventListener() {
			public void onEvent(org.zkoss.zk.ui.event.Event event) throws Exception {
				Textbox tbox = (Textbox) event.getTarget();
				addinfoObj.invoke( "run"+tbox.getId(), tbox, addinfo, usr, 0, listwnd);
				//org.zkoss.xel.Function fn = dtbox.getPage().getZScriptFunction(addinfomaindiv, "run"+dtbox.getId(), null);//"claddinfo"
				//fn.invoke(null, null);
			}
		});
		h.appendChild(t);
		
		Button b = new Button();
		b.setId("b_"+p.getParam_id());
		b.setAttribute("parameter", p);
		b.setAttribute("group", parametergroup);
		b.setWidth("50px");
		b.setImage("/images/edit.png");
		b.setTooltip("Редактирование списка");
		b.addEventListener("onClick", new EventListener() {
			public void onEvent(org.zkoss.zk.ui.event.Event event) throws Exception {
				Button b = (Button) event.getTarget();
				Parameter p = (Parameter)b.getAttribute("parameter");
				Textbox t = (Textbox) addinfo.getFellow("p_"+p.getParam_id());
				String select = p.getParam_select().toUpperCase();
				select = ParameterService.setParamsInSQL(parameters, select, current);
				checklistwnd$checklist.setModel(new BindingListModelList(ParameterService.getListForCheckList(select, alias, t.getValue()), true));
				for (int i = 0; i < checklistwnd$checklist.getModel().getSize(); i++) {
					CheckListRefData chd = (CheckListRefData)checklistwnd$checklist.getModel().getElementAt(i);
					if (chd.getIsChecked()) {
						System.out.println(chd.getIsChecked()+" - "+chd.getData()+" - "+chd.getLabel());
					}
				}
				checklistwnd$checkall.setAttribute("checkvalue", false);
				checklistwnd$checkall.setChecked(false);
				checklistwnd.setVisible(true);
				checklistwnd$btn_save.setAttribute("parameter", p);
			}
		});
		h.appendChild(b);
		return h;
	}
	
	private Textbox getSTRING(ParameterGroup parametergroup, ParameterListArray p) {
		Textbox t = new Textbox();
		t.setAttribute("parameter", p);
		t.setId("p_"+p.getParam_list_teg());	
		t.setWidth("100%");
		if (p.getParam_type().startsWith("STRING(")) {
			try {
				int rownum = Integer.parseInt(p.getParam_type().replace("STRING(", "").replace(")", ""));
				t.setRows(rownum);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		addinfoObj.addToSource(
				"public void run"+t.getId()+"(org.zkoss.zul.Textbox comp, org.zkoss.zul.Div addinfo, com.is.user.User user, java.lang.Integer afterCompose, org.zkoss.zul.Window listwnd) ",
			    "{",
			    	(CheckNull.isEmpty(p.getParam_actions())?("System.out.println(\"run"+t.getId()+"\");"):p.getParam_actions()),
			    "}"
			);
		
		t.addEventListener(Events.ON_CHANGE, new EventListener() {
			public void onEvent(org.zkoss.zk.ui.event.Event event) throws Exception {
				Textbox tbox = (Textbox) event.getTarget();
				addinfoObj.invoke( "run"+tbox.getId(), tbox, addinfo, usr, 0, listwnd);
				//org.zkoss.xel.Function fn = dtbox.getPage().getZScriptFunction(addinfomaindiv, "run"+dtbox.getId(), null);//"claddinfo"
				//fn.invoke(null, null);
			}
		});
		
		if (!CheckNull.isEmpty(p.getParam_enable()) && p.getParam_enable().equals("0")) {
			t.setReadonly(true);
		} else {
			t.setReadonly(false);
		}
		if(p.getParam_value()!=null) {
			t.setValue(p.getParam_value());
		}
		return t;
	}
	
	public void onClick$btn_save$listwnd() {
		ParameterGroup pg = (ParameterGroup)listwnd$btn_save.getAttribute("group");
		Parameter par = (Parameter)listwnd$btn_save.getAttribute("parameter");
		ParameterList pl = (ParameterList)listwnd$btn_save.getAttribute("parameterlist");
		List<ParameterList> listparams = (List<ParameterList> )listwnd$btn_save.getAttribute("listparams");
		Datebox dtb = new Datebox();
		Combobox c = new Combobox();
		Checkbox ch = new Checkbox();
		Textbox n = new Textbox();
		Decimalbox d = new Decimalbox();
		Textbox t = new Textbox();
		ParameterListArray p = null;
		for (int i = 0; i < pl.getParameters().size(); i++) {
			if (pl.getParameters().get(i).getParam_type().equals("DATE")){
				dtb = (Datebox) listwnd.getFellow("p_"+pl.getParameters().get(i).getParam_list_teg());
				p = (ParameterListArray) dtb.getAttribute("parameter");
				if (p.getParam_mandatory() == 1 && CheckNull.isEmpty(dtb.getValue())) {
					alert("Не заполнен обязательный параметр: "+(tegNames.get(pl.getParameters().get(i).getParam_list_teg())));
					return;
				}
				pl.getParameters().get(i).setParam_value(CheckNull.isEmpty(dtb.getValue())?null:df.format(dtb.getValue()));
			} else if (pl.getParameters().get(i).getParam_type().equals("COMBOBOX")){
				c = (RefCBox) listwnd.getFellow("p_"+pl.getParameters().get(i).getParam_list_teg());
				p = (ParameterListArray) c.getAttribute("parameter");
				if (p.getParam_mandatory() == 1 && CheckNull.isEmpty(c.getValue())) {
					alert("Не заполнен обязательный параметр: "+(tegNames.get(pl.getParameters().get(i).getParam_list_teg())));
					return;
				}
				pl.getParameters().get(i).setParam_value(CheckNull.isEmpty(c.getValue())?null:c.getValue());
			} else if (pl.getParameters().get(i).getParam_type().equals("CHECKBOX")){
				ch = (Checkbox) listwnd.getFellow("p_"+pl.getParameters().get(i).getParam_list_teg());
				p = (ParameterListArray) ch.getAttribute("parameter");
				pl.getParameters().get(i).setParam_value(ch.isChecked()?"1":"0");
			} else if (pl.getParameters().get(i).getParam_type().equals("NUMBER")){
				n = (Textbox) listwnd.getFellow("p_"+pl.getParameters().get(i).getParam_list_teg());
				p = (ParameterListArray) n.getAttribute("parameter");
				if (p.getParam_mandatory() == 1 && CheckNull.isEmpty(n.getValue())) {
					alert("Не заполнен обязательный параметр: "+(tegNames.get(pl.getParameters().get(i).getParam_list_teg())));
					return;
				}
				pl.getParameters().get(i).setParam_value(CheckNull.isEmpty(n.getValue())?null:n.getValue());
			} else if (pl.getParameters().get(i).getParam_type().equals("DECIMAL")){
				n = (Textbox) listwnd.getFellow("p_"+pl.getParameters().get(i).getParam_list_teg());
				p = (ParameterListArray) n.getAttribute("parameter");
				if (p.getParam_mandatory() == 1 && CheckNull.isEmpty(n.getValue())) {
					alert("Не заполнен обязательный параметр: "+(tegNames.get(pl.getParameters().get(i).getParam_list_teg())));
					return;
				}
				pl.getParameters().get(i).setParam_value(CheckNull.isEmpty(n.getValue())?null:n.getValue());
			} else if (pl.getParameters().get(i).getParam_type().equals("CHECKLIST")){
				t = (Textbox) listwnd.getFellow("p_"+pl.getParameters().get(i).getParam_list_teg());
				p = (ParameterListArray) t.getAttribute("parameter");
				if (p.getParam_mandatory() == 1 && CheckNull.isEmpty(t.getValue())) {
					alert("Не заполнен обязательный параметр: "+(tegNames.get(pl.getParameters().get(i).getParam_list_teg())));
					return;
				}
				pl.getParameters().get(i).setParam_value(CheckNull.isEmpty(t.getValue())?null:t.getValue());
				//alert("p_"+params.get(i).getParam_id() +" = "+t.getValue()+ " = "+ params.get(i).getParam_value());
			} else if (pl.getParameters().get(i).getParam_type().equals("PERSONGRID")){
				Listbox listbox = (Listbox) listwnd.getFellow("p_"+pl.getParameters().get(i).getParam_list_teg());
				if (pl.getParameters().get(i).getParam_mandatory() == 1 && listbox.getModel().getSize()>0) {
					alert("Не заполнен обязательный параметр: "+(tegNames.get(pl.getParameters().get(i).getParam_list_teg())));
					return;
				}
			} else {//STRING
				t = (Textbox) listwnd.getFellow("p_"+pl.getParameters().get(i).getParam_list_teg());
				p = (ParameterListArray) t.getAttribute("parameter");
				if (p.getParam_mandatory() == 1 && CheckNull.isEmpty(t.getValue())) {
					alert("Не заполнен обязательный параметр: "+(tegNames.get(pl.getParameters().get(i).getParam_list_teg())));
					return;
				}
				pl.getParameters().get(i).setParam_value(CheckNull.isEmpty(t.getValue())?null:t.getValue());
			}
    	}
		Boolean bool = false;
		for (int j = 0; j < listparams.size(); j++) {
			if (listparams.get(j).getList_id() == pl.getList_id()) {
				listparams.set(j, pl);
				bool = true;
			}
		}
		if (!bool) {
			listparams.add(pl);
		}
		Listbox l = (Listbox)addinfo.getFellow("p_"+p.getParam_id());
    	if (l == null) {
    		alert("Список p_"+p.getParam_id()+" не найден!");
    		return;
    	}
    	l.setModel(new BindingListModelList(listparams, true));
    	paramlistparams.remove(par.getParam_id());
    	paramlistparams.put(par.getParam_id(), listparams);
    	listwnd.setVisible(false);
	}
	
	public void onClick$btn_cancel$listwnd() {
		listwnd.setVisible(false);
	}
	
	public Boolean saveAddInfoList() {
		Boolean res = false;
		/*try {
			List<ParameterL> params = parameters;
			if (parameters.size() > 0) {
				Datebox dtb = new Datebox();
				Combobox c = new Combobox();
				Checkbox ch = new Checkbox();
				Textbox n = new Textbox();
				Decimalbox d = new Decimalbox();
				Textbox t = new Textbox();
				Parameter p = null;
				for (int i = 0; i < params.size(); i++) {
					if (params.get(i).getParam_type().equals("DATE")){
						dtb = (Datebox) addinfo.getFellow("p_"+params.get(i).getParam_id());
						p = (Parameter) dtb.getAttribute("parameter");
						if (p.getParam_mandatory() == 1 && CheckNull.isEmpty(dtb.getValue())) {
							alert("Не заполнен обязательный параметр: "+(tegNames.get(params.get(i).getParam_id())));
							return false;
						}
						params.get(i).setParam_value(CheckNull.isEmpty(dtb.getValue())?null:df.format(dtb.getValue()));
					} else if (params.get(i).getParam_type().equals("COMBOBOX")){
						c = (RefCBox) addinfo.getFellow("p_"+params.get(i).getParam_id());
						p = (Parameter) c.getAttribute("parameter");
						if (p.getParam_mandatory() == 1 && CheckNull.isEmpty(c.getValue())) {
							alert("Не заполнен обязательный параметр: "+(tegNames.get(params.get(i).getParam_id())));
							return false;
						}
						params.get(i).setParam_value(CheckNull.isEmpty(c.getValue())?null:c.getValue());
					} else if (params.get(i).getParam_type().equals("CHECKBOX")){
						ch = (Checkbox) addinfo.getFellow("p_"+params.get(i).getParam_id());
						p = (Parameter) ch.getAttribute("parameter");
						params.get(i).setParam_value(ch.isChecked()?"1":"0");
					} else if (params.get(i).getParam_type().equals("NUMBER")){
						n = (Textbox) addinfo.getFellow("p_"+params.get(i).getParam_id());
						p = (Parameter) n.getAttribute("parameter");
						if (p.getParam_mandatory() == 1 && CheckNull.isEmpty(n.getValue())) {
							alert("Не заполнен обязательный параметр: "+(tegNames.get(params.get(i).getParam_id())));
							return false;
						}
						params.get(i).setParam_value(CheckNull.isEmpty(n.getValue())?null:n.getValue());
					} else if (params.get(i).getParam_type().equals("DECIMAL")){
						n = (Textbox) addinfo.getFellow("p_"+params.get(i).getParam_id());
						p = (Parameter) n.getAttribute("parameter");
						if (p.getParam_mandatory() == 1 && CheckNull.isEmpty(n.getValue())) {
							alert("Не заполнен обязательный параметр: "+(tegNames.get(params.get(i).getParam_id())));
							return false;
						}
						params.get(i).setParam_value(CheckNull.isEmpty(n.getValue())?null:n.getValue());
					} else if (params.get(i).getParam_type().equals("CHECKLIST")){
						t = (Textbox) addinfo.getFellow("p_"+params.get(i).getParam_id());
						p = (Parameter) t.getAttribute("parameter");
						if (p.getParam_mandatory() == 1 && CheckNull.isEmpty(t.getValue())) {
							alert("Не заполнен обязательный параметр: "+(tegNames.get(params.get(i).getParam_id())));
							return false;
						}
						params.get(i).setParam_value(CheckNull.isEmpty(t.getValue())?null:t.getValue());
						//alert("p_"+params.get(i).getParam_id() +" = "+t.getValue()+ " = "+ params.get(i).getParam_value());
					} else if (params.get(i).getParam_type().equals("PERSONGRID")){
						Listbox listbox = (Listbox) addinfo.getFellow("p_"+params.get(i).getParam_id());
						if (params.get(i).getParam_mandatory() == 1 && listbox.getModel().getSize()>0) {
							alert("Не заполнен обязательный параметр: "+(tegNames.get(params.get(i).getParam_id())));
							return false;
						}
					} else {//STRING
						t = (Textbox) addinfo.getFellow("p_"+params.get(i).getParam_id());
						p = (Parameter) t.getAttribute("parameter");
						if (p.getParam_mandatory() == 1 && CheckNull.isEmpty(t.getValue())) {
							alert("Не заполнен обязательный параметр: "+(tegNames.get(params.get(i).getParam_id())));
							return false;
						}
						params.get(i).setParam_value(CheckNull.isEmpty(t.getValue())?null:t.getValue());
					}
				}
			}
			Res rs = ParameterService.saveAddInfo(params);
    		if (rs.getCode() != 0) {
    			alert(rs.getName());
    			res = false;
    		}  else {
    			parameters = params;
    			res = true;
    		}
    	} catch (Exception e) {
			e.printStackTrace();
			res = false;
		}*/
		return res;
	}
	
	/*
	private void createClientCardFrm(){
		try{
			if (current.getId()>0) {
				if(clientCardDivDialog == null){
					clientCardDivDialog = (Div)Executions.createComponents("clientcard.zul", divForClientCard, null);
			    }
				if (clientCardDivDialog == null) System.out.println("personInfoDivDialog = null!");
				clientCardDivDialog.setVisible(true);
				ClientCardViewCtrl cvc =(ClientCardViewCtrl)clientCardDivDialog.getAttribute("clientcardmain$composer");
			    if (cvc == null) System.out.println("cvc = null!");
			    cvc.prepareClientCard(current, "frm");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	*/
}
