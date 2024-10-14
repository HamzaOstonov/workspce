package com.is.user;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.zkoss.util.resource.Labels;
import org.zkoss.web.Attributes;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.DropEvent;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Caption;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Column;
import org.zkoss.zul.Columns;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Decimalbox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.Window;
import org.zkoss.zul.event.PagingEvent;

import com.is.sign.keys.Key;
import com.is.sign.keys.KeyService;
import com.is.sign.keys.KeyWndViewCtrl;
import com.is.user.addinfo.Parameter;
import com.is.user.addinfo.ParameterGroup;
import com.is.user.addinfo.ParameterService;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.RefData;
import com.is.utils.Res;

@SuppressWarnings("serial")
public class UserViewCtrl extends GenericForwardComposer{
        private Window usermain;
		private Div frm;
        private Paging contactPaging;
        private Div grd, addinfo;
        private Listbox dataGrid;
        private Paging userPaging;
        private  int _pageSize = 15;
        private int _startPageNumber = 0;
        private int _totalSize = 0;
        private boolean _needsTotalSizeUpdate = true;
        private Grid addgrd,frmgrd,fgrd,pwdwnd$pwdgrd;
        private Toolbarbutton btn_last, btn_save;
        private Toolbarbutton btn_next;
        private Toolbarbutton btn_prev;
        private Toolbarbutton btn_first;
        private Toolbarbutton btn_add, btn_role;
        private Toolbarbutton btn_search;
        private Toolbarbutton btn_back, btn_pwd;
        private Toolbar tb;
        private Textbox id,branchtext,/*username*/password,salt,firstname,secondname,surname,pseries,pnumber,pauthority,state;
        private Textbox aid,ausername,apassword,asalt,afirstname,asecondname,asurname,apseries,apnumber,apauthority,astate;
        private Textbox fid,fbranchtext,fusername,fpassword,fsalt,ffirstname,fsecondname,fsurname,fpseries,fpnumber,fpauthority,fstate;
        private Window rlwnd,pwdwnd;
        private Listbox rlwnd$left,rlwnd$right;
        private RefCBox branch,subbranch,abranch,asubbranch,fbranch,fsubbranch,ptype,aptype,fptype;
        private Textbox pwdwnd$oldpwd,pwdwnd$newpwd,pwdwnd$newpwd1;
        private List<RefData> branches = new ArrayList<RefData>();
    	private List<RefData> ss_dblink_branches = new ArrayList<RefData>();
    	private Boolean branchLock = true;
    	private List<ParameterGroup> parametergroups = new ArrayList<ParameterGroup>();
    	private List<Parameter> parameters = new ArrayList<Parameter>();
    	
        public User current= new User();
        public UserFilter filter = new UserFilter();

        PagingListModel model = null;
        ListModelList lmodel =null;
        private AnnotateDataBinder binder;
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        private String branch1,alias,_alias,lang;

        public UserViewCtrl() {
                super('$', false, false);
        }
    /**
     *
     *
     */
    @Override
    public void doAfterCompose(Component comp) throws Exception {
    	super.doAfterCompose(comp);
    	// TODO Auto-generated method stub
    	binder = new AnnotateDataBinder(comp);
    	binder.bindBean("current", this.current);
    	binder.loadAll();
        String[] parameter = (String[]) param.get("ht");
        //System.out.println("loadUserMd " + session.getAttribute("un")+"   "+session.getAttribute("pwd"));
        if (parameter!=null){
        	_pageSize = (Integer.parseInt( parameter[0])-120)/36;
        	dataGrid.setRows((Integer.parseInt( parameter[0])-120)/36);
        	//_pageSize = Integer.parseInt( parameter[0]);
        	// dataGrid.setRows(Integer.parseInt( parameter[0]));
        }
        parameter = (String[]) param.get("branch");
        if (parameter!=null){
        	if (parameter[0].equalsIgnoreCase("unlock")) {
        		branchLock = false;
        	} else {
        		branchLock = true;
        	}
	    }
        lang = (((Locale) session.getAttribute(Attributes.PREFERRED_LOCALE)).getLanguage());
        branch1 = (String) session.getAttribute("branch");
        alias = (String) session.getAttribute("alias");
        _alias = alias;
        ss_dblink_branches = UserService.getSSDBLinkBranch(alias);
        branches =com.is.utils.RefDataService.get_cur_bank_Mfo(alias);
        branch.setModel((new ListModelList(branches)));
        fbranch.setModel((new ListModelList(branches)));
        
        filter.setBranch(branch1);
    	branchtext.setValue(branch1);
        branch.setSelecteditem(branch1);
    	fbranch.setSelecteditem(branch1);
    	fbranchtext.setValue(branch1);
        if (branchLock) {
        	branchtext.setDisabled(true);
        	branch.setDisabled(true);
        	fbranch.setDisabled(true);
        	fbranchtext.setDisabled(true);
        } 
        
        dataGrid.setItemRenderer(new ListitemRenderer(){
        @SuppressWarnings("unchecked")
        public void render(Listitem row, Object data) throws Exception {
        	User pUser = (User) data;
        	row.setValue(pUser);
        	row.appendChild(new Listcell(pUser.getId()+""));
        	row.appendChild(new Listcell(pUser.getBranch()));
        	row.appendChild(new Listcell(pUser.getUser_name()));
        	row.appendChild(new Listcell(pUser.getFull_name()));
        	row.appendChild(new Listcell(pUser.getTitle()));
        	//row.appendChild(new Listcell(pUser.getTitle()));
        	//row.appendChild(new Listcell(pUser.getTitle()));
        	row.appendChild(new Listcell(CheckNull.isEmpty(pUser.getDate_open())?"":df.format(pUser.getDate_open())));
        	row.appendChild(new Listcell(CheckNull.isEmpty(pUser.getPwd_expired())?"":df.format(pUser.getPwd_expired())));
        }});
        
        rlwnd$left.setItemRenderer(new ListitemRenderer(){
            @SuppressWarnings("unchecked")
            public void render(Listitem row, Object data) throws Exception {
            	Role pRole = (Role) data;
                        row.setValue(pRole);
                        row.setDraggable("true");
                        row.setDroppable("true");
                        Listcell lc = new Listcell(pRole.getName());
                        row.appendChild(lc);
            }});
        
        
        rlwnd$right.setItemRenderer(new ListitemRenderer(){
            @SuppressWarnings("unchecked")
            public void render(Listitem row, Object data) throws Exception {
            	Role pRole = (Role) data;
                        row.setValue(pRole);
                        row.setDraggable("true");
                        row.setDroppable("true");
                        Listcell lc = new Listcell(pRole.getName());
                        row.appendChild(lc);
            }});
        
        refreshModel(_startPageNumber);
    }

	public void onPaging$userPaging(ForwardEvent event){
	    final PagingEvent pe = (PagingEvent) event.getOrigin();
	    _startPageNumber = pe.getActivePage();
	    refreshModel(_startPageNumber);
	}
	
	
	private void refreshModel(int activePage){
		userPaging.setPageSize(_pageSize);
	    model = new PagingListModel(activePage, _pageSize,filter,alias);
	    if(_needsTotalSizeUpdate) {
	    	_totalSize = model.getTotalSize(filter,alias);
	    }
	    userPaging.setTotalSize(_totalSize);
	    dataGrid.setModel((ListModel) model);
	    if (model.getSize()>0){
	    	dataGrid.setSelectedIndex(0);
	    	sendSelEvt(true);
	    	this.current =(User) model.getElementAt(0);
	    	sendSelEvt(true);
	    }
	}
	
	public void onSelect$dataGrid$grd() {
		sendSelEvt(false);
	}
	
	// Omitted...
	public User getCurrent() {
	    return current;
	}
	
	public void setCurrent(User current) {
	    this.current = current;
	}
	
	public void onDoubleClick$dataGrid$grd() {
		grd.setVisible(false);
		frm.setVisible(true);
		frmgrd.setVisible(true);
		fgrd.setVisible(false);
		btn_back.setImage("/images/folder.png");
		btn_back.setLabel(Labels.getLabel("grid"));
		btn_pwd.setVisible(true);
		btn_save.setVisible(true);
		btn_save.setLabel(Labels.getLabel("save"));
		btn_save.setImage("/images/save.png");
		btn_role.setVisible(true);
		addinfo.setVisible(true);
		createAddInfo();
		showUserKeys();
	}
	
	public void onClick$btn_back() {
		if (frm.isVisible()){
			frm.setVisible(false);
			grd.setVisible(true);
			btn_back.setImage("/images/file.png");
			btn_back.setLabel(Labels.getLabel("back"));
		}else onDoubleClick$dataGrid$grd();
		btn_role.setVisible(true);
	}
	
	
	public void onClick$btn_first() {
		dataGrid.setSelectedIndex(0);
		sendSelEvt(true);
		createAddInfo();
		showUserKeys();
	}
	public void onClick$btn_last() {
		dataGrid.setSelectedIndex(model.getSize()-1);
		sendSelEvt(true);
		createAddInfo();
		showUserKeys();
	}
	public void onClick$btn_prev() {
		if (dataGrid.getSelectedIndex()!=0){
	        dataGrid.setSelectedIndex(dataGrid.getSelectedIndex()-1);
	        sendSelEvt(true);
	        createAddInfo();
	        showUserKeys();
		}
	}
	public void onClick$btn_next() {
		if (dataGrid.getSelectedIndex()!=(model.getSize()-1)){
	        dataGrid.setSelectedIndex(dataGrid.getSelectedIndex()+1);
	        sendSelEvt(true);
	        createAddInfo();
	        showUserKeys();
		}
	}
	
	private void sendSelEvt(Boolean sendEvt){
		if (dataGrid.getSelectedIndex()==0){
			btn_first.setDisabled(true);
			btn_prev.setDisabled(true);
		}else{
			btn_first.setDisabled(false);
			btn_prev.setDisabled(false);
		}
		if(dataGrid.getSelectedIndex()==(model.getSize()-1)){
			btn_next.setDisabled(true);
			btn_last.setDisabled(true);
		}else{
			btn_next.setDisabled(false);
			btn_last.setDisabled(false);
		}
		if (sendEvt) {
			SelectEvent evt = new SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
			Events.sendEvent(evt);
		}
	}
	
	
	public void onClick$btn_add() {
		onDoubleClick$dataGrid$grd();
		frmgrd.setVisible(false);
		fgrd.setVisible(false);
		btn_role.setVisible(true);
		btn_pwd.setVisible(false);
		addinfo.setVisible(true);
		createAddInfo();
		showUserKeys();
	}
	
	public void onClick$btn_search() {
		onDoubleClick$dataGrid$grd();
		frmgrd.setVisible(false);
		fgrd.setVisible(true);
		btn_role.setVisible(false);
		btn_pwd.setVisible(false);
		addinfo.setVisible(false);
		btn_save.setVisible(true);
		btn_save.setLabel(Labels.getLabel("find"));
		btn_save.setImage("/images/search.png");
		if (branchLock) {
			filter.setBranch(branch1);
	    	fbranch.setSelecteditem(branch1);
	    	fbranchtext.setValue(branch1);
        	fbranch.setDisabled(true);
	    	fbranchtext.setDisabled(true);
        } 
		showUserKeys();
	}
	
	
	public void onClick$btn_save() {
		try{
			if(fgrd.isVisible()){
				if (CheckNull.isEmpty(fbranch.getValue())) {
					alert("Филиал не может быть пустым!");
					return;
            	}
				filter = new UserFilter();
	            filter.setUser_name(fusername.getValue().toUpperCase());
	            filter.setFull_name(ffirstname.getValue().toUpperCase());
	            if (branchLock) {
	    			filter.setBranch(branch1);
	    	    	fbranch.setSelecteditem(branch1);
	    	    	fbranchtext.setValue(branch1);
	            	fbranch.setDisabled(true);
	            	fbranchtext.setDisabled(true);
	            } else {
	            	filter.setBranch(fbranch.getValue());
		            _alias = UserService.lvalue(fbranch.getValue(), ss_dblink_branches);
		        }
			} else if (frmgrd.isVisible()) {
				if (!saveAddInfo()) {
					return;
				}
				if (!ParameterService.saveAddInfo(parameters)) {
		        	alert("При сохранении дополнительных данных возникла ошибка!");
		        }
			}
		    onClick$btn_back();
		    refreshModel(_startPageNumber);
		
		    SelectEvent evt = new SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
		    Events.sendEvent(evt);
	    } catch (Exception e) {
	        com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
	    }
	
	}
	public void onClick$btn_cancel() {
		CheckNull.clearForm(fgrd);
		if(fgrd.isVisible()){
			filter = new UserFilter();
			filter.setBranch(branch1);
	    	fbranch.setSelecteditem(branch1);
        	fbranchtext.setValue(branch1);
        	_alias = alias;
			if (branchLock) {
				fbranch.setDisabled(true);
	        	fbranchtext.setDisabled(true);
	        }
		}
	    onClick$btn_back();
	    frmgrd.setVisible(true);
	    fgrd.setVisible(false);
	    refreshModel(_startPageNumber);
	}
	
	public void onClick$btn_role() {
		rlwnd$right.setModel((new ListModelList(UserService.getUserInRole(current.getId(), current.getBranch(),alias))));
		rlwnd$left.setModel((new ListModelList(UserService.getUserNotInRole(current.getId(), current.getBranch(),alias))));
		rlwnd.setVisible(true);
	}
	
	public void onDrop$right$rlwnd(DropEvent e){
	    if (e.getDragged() instanceof Listitem) {
	    	Listitem        li =(Listitem)e.getDragged();
	    	Role role= (Role) li.getValue();
	    	UserService.addRole(current.getId(), role.getId(), current.getBranch(),alias);
	    	rlwnd$right.setModel((new ListModelList(UserService.getUserInRole(current.getId(), current.getBranch(),alias))));
	    	rlwnd$left.setModel((new ListModelList(UserService.getUserNotInRole(current.getId(), current.getBranch(),alias))));
	    }
	}	
	public void onDrop$left$rlwnd(DropEvent e){
	    if (e.getDragged() instanceof Listitem) {
	    	Listitem        li =(Listitem)e.getDragged();
	    	Role role= (Role) li.getValue();
	    	UserService.removeRole(current.getId(), role.getId(), current.getBranch(),alias);
	    	rlwnd$right.setModel((new ListModelList(UserService.getUserInRole(current.getId(), current.getBranch(),alias))));
	    	rlwnd$left.setModel((new ListModelList(UserService.getUserNotInRole(current.getId(), current.getBranch(),alias))));
	    }
	}	
	
	public void onClick$btn_pwd() {
		pwdwnd.setVisible(true);
	}
	public void onClick$btn_cancel$pwdwnd() {
		CheckNull.clearForm(pwdwnd$pwdgrd);
		pwdwnd.setVisible(false);
	}
	public void onClick$btn_save$pwdwnd() {
		Res rs = UserService.chPwdToUser(current.getUser_name(), pwdwnd$newpwd.getValue(), pwdwnd$newpwd1.getValue(),_alias);
		if (rs.getCode()==0){
			CheckNull.clearForm(pwdwnd$pwdgrd);
			pwdwnd.setVisible(false);
		}else{
			alert(rs.getName());
		}
	}
	
	public void onInitRenderLater$branch(){
		branchtext.setValue(branch1);
		branch.setSelecteditem(branch1);
	}
	
	public void onInitRenderLater$fbranch(){
		fbranchtext.setValue(branch1);
		fbranch.setSelecteditem(branch1);
	}
	
	public void createAddInfo() {
		if (current != null) {
			DecimalFormat dcf = new DecimalFormat("999,999,999,999,999,990.00 ");
			addinfo.getChildren().clear();
			parametergroups = ParameterService.getParametergroup();
			parameters = ParameterService.getParameters(current.getBranch(), current.getId());
			//System.out.println(parametergroups.size()+" ... "+parameters.size());
			try {
				for (ParameterGroup parametergroup : parametergroups) {
					List<Parameter> params = ParameterService.getParametersByGroup(parameters, parametergroup);
					System.out.println("select: "+parametergroups.size()+" ... "+parameters.size()+" ... "+params.size());
					if (params.size() > 0) {
						Groupbox g = new Groupbox();
						g.setMold("3d");
						g.setOpen(parametergroup.getIs_open() == 1); 
						g.setClosable(true);
						g.setWidth("100%");
						Caption caption = new Caption(lang.equalsIgnoreCase("en")?parametergroup.getName_en():(lang.equalsIgnoreCase("uz")?parametergroup.getName_uz():parametergroup.getName_ru()));
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
						column.setWidth("30%");
						grid.getColumns().appendChild(column);  
						column = new Column();
						column.setAlign("left");   
						column.setWidth("70%");
						grid.getColumns().appendChild(column);
						
						Vbox vb = new Vbox();
						Label lbl = new Label();
						Datebox dtb = new Datebox();
						Combobox c = new Combobox();
						Checkbox ch = new Checkbox();
						Textbox n = new Textbox();
						Textbox t = new Textbox();
						for (int i = 0; i < params.size(); i++) {
							Row row = new Row();
							row.setAttribute("parameter", params.get(i));
							row.setAttribute("group", parametergroup);
							if (!params.get(i).getParam_type().equalsIgnoreCase("PERSONGRID")) {
								row.appendChild(new Label(lang.equalsIgnoreCase("en")?params.get(i).getParam_name_en():(lang.equalsIgnoreCase("uz")?params.get(i).getParam_name_uz():params.get(i).getParam_name_ru())));
								vb = new Vbox();
								vb.setWidth("100%");
								if (params.get(i).getParam_mandatory() == 1) {
									lbl = new Label("* Обязательный параметр");
									lbl.setStyle("font-size: 12px; color: grey;");
									vb.appendChild(lbl);
								}
							}
							if (params.get(i).getParam_type().equals("DATE")){
								dtb = new Datebox();
								dtb.setId("p_"+params.get(i).getParam_id());
								dtb.setWidth("600px");
								dtb.setAttribute("parameter", params.get(i));
								if (params.get(i).getParam_mandatory() == 1) {
									dtb.setConstraint("no empty, no future:  Заполните обязательный параметр! ");
								}
								if(params.get(i).getParam_value()!=null) {
									dtb.setValue(df.parse(params.get(i).getParam_value()));
								}
								dtb.addEventListener(Events.ON_BLUR, new EventListener() {
									public void onEvent(org.zkoss.zk.ui.event.Event event) throws Exception {
										Datebox dtbox = (Datebox) event.getTarget();
									}
								});
								
								vb.appendChild(dtb);
							} else if (params.get(i).getParam_type().equals("COMBOBOX")){
								String select = params.get(i).getParam_select().toUpperCase();
								/*if (select.indexOf("INFO.GETBRANCH") != -1){
									select = select.replaceAll("INFO.GETBRANCH","'01066'") ;
								}*/
								select = ParameterService.setParamsInSQL(parameters, select);
								//System.out.println(rowData.getId()+") "+select);
								c = new RefCBox();
								c.setId("p_"+params.get(i).getParam_id());
								c.setAttribute("parameter", params.get(i));
								c.setAttribute("group", parametergroup);
								c.setWidth("600px");
								c.setMold("rounded");
								c.setReadonly(true);
								if (!CheckNull.isEmpty(select)){
									//c.setModel(new ListModelList(ReportService.getListForCombobox(rowData.getPar_select())));
									//c.setModel(null);
									ListModelList list = new ListModelList(ParameterService.getListForCombobox(select,alias));
									c.setModel(list);
									//System.out.println("*** c.getModel().getSize() = "+c.getModel().getSize()+"---"+c.getItemCount()+"---"+c.getItems().isEmpty());
									c.addEventListener("onInitRenderLater", new EventListener() {
											public void onEvent(org.zkoss.zk.ui.event.Event event) throws Exception {
												RefCBox cc = (RefCBox) event.getTarget();
												if (cc.getAttribute("parameter") != null){
													cc.setSelecteditem(((Parameter)cc.getAttribute("parameter")).getParam_value().toString());
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
									public void onEvent(org.zkoss.zk.ui.event.Event event) throws Exception {
										RefCBox cc = (RefCBox) event.getTarget();
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
								vb.appendChild(c);
								//row.appendChild(c);
								
							} else if (params.get(i).getParam_type().equals("CHECKBOX")){
								ch = new Checkbox();
								ch.setId("p_"+params.get(i).getParam_id());
								if (!CheckNull.isEmpty(params.get(i).getParam_value())) {
									ch.setChecked(params.get(i).getParam_value().equalsIgnoreCase("1"));
								} else {
									ch.setChecked(false);
								}
							} else if (params.get(i).getParam_type().equals("NUMBER")){
								n = new Textbox();//new Intbox();
								n.setId("p_"+params.get(i).getParam_id());
								n.setWidth("600px");
								n.setConstraint("/^[\\.\\%\\_0-9]+$/:Must be a number");// /%_[0-9]*/
								if(params.get(i).getParam_value()!=null) {
									//n.setValue(Integer.parseInt(rowData.getPar_value()));
									n.setValue(params.get(i).getParam_value());
								}
								vb.appendChild(n);
								//row.appendChild(n);
							} else if (params.get(i).getParam_type().equals("DECIMAL")){
								n = new Textbox();//new Intbox();
								n.setId("p_"+params.get(i).getParam_id());
								n.setWidth("600px");
								n.setConstraint("/^[\\.\\%\\_0-9]+$/:Must be a number");// /%_[0-9]*/
								if(params.get(i).getParam_value()!=null) {
									//n.setValue(Integer.parseInt(rowData.getPar_value()));
									n.setValue(dcf.format(params.get(i).getParam_value()));
								}
								vb.appendChild(n);
								//row.appendChild(n);
							} else {//STRING
								t = new Textbox();
								t.setId("p_"+params.get(i).getParam_id());
								t.setWidth("600px");
								if(params.get(i).getParam_value()!=null) {
									t.setValue(params.get(i).getParam_value());
								}
								vb.appendChild(t);
								//row.appendChild(t);
							}
							if (!params.get(i).getParam_type().equals("PERSONGRID")){
								row.appendChild(vb);
							}
							grid.getRows().appendChild(row);
						}
						g.appendChild(grid);
						addinfo.appendChild(g);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public Boolean saveAddInfo() {
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
				for (int i = 0; i < params.size(); i++) {
					if (params.get(i).getParam_type().equals("DATE")){
						dtb = (Datebox) addinfo.getFellow("p_"+params.get(i).getParam_id());
						if (params.get(i).getParam_mandatory() == 1 && CheckNull.isEmpty(dtb.getValue())) {
							alert("Не заполнен обязательный параметр: "+(lang.equalsIgnoreCase("en")?params.get(i).getParam_name_en():(lang.equalsIgnoreCase("uz")?params.get(i).getParam_name_uz():params.get(i).getParam_name_ru())));
							return false;
						}
						params.get(i).setParam_value(CheckNull.isEmpty(dtb.getValue())?null:df.format(dtb.getValue()));
					} else if (params.get(i).getParam_type().equals("COMBOBOX")){
						c = (RefCBox) addinfo.getFellow("p_"+params.get(i).getParam_id());
						if (params.get(i).getParam_mandatory() == 1 && CheckNull.isEmpty(c.getValue())) {
							alert("Не заполнен обязательный параметр: "+(lang.equalsIgnoreCase("en")?params.get(i).getParam_name_en():(lang.equalsIgnoreCase("uz")?params.get(i).getParam_name_uz():params.get(i).getParam_name_ru())));
							return false;
						}
						params.get(i).setParam_value(CheckNull.isEmpty(c.getValue())?null:c.getValue());
					} else if (params.get(i).getParam_type().equals("CHECKBOX")){
						ch = (Checkbox) addinfo.getFellow("p_"+params.get(i).getParam_id());
						params.get(i).setParam_value(ch.isChecked()?"1":"0");
					} else if (params.get(i).getParam_type().equals("NUMBER")){
						n = (Textbox) addinfo.getFellow("p_"+params.get(i).getParam_id());
						if (params.get(i).getParam_mandatory() == 1 && CheckNull.isEmpty(n.getValue())) {
							alert("Не заполнен обязательный параметр: "+(lang.equalsIgnoreCase("en")?params.get(i).getParam_name_en():(lang.equalsIgnoreCase("uz")?params.get(i).getParam_name_uz():params.get(i).getParam_name_ru())));
							return false;
						}
						params.get(i).setParam_value(CheckNull.isEmpty(n.getValue())?null:n.getValue());
					} else if (params.get(i).getParam_type().equals("DECIMAL")){
						n = (Textbox) addinfo.getFellow("p_"+params.get(i).getParam_id());
						if (params.get(i).getParam_mandatory() == 1 && CheckNull.isEmpty(n.getValue())) {
							alert("Не заполнен обязательный параметр: "+(lang.equalsIgnoreCase("en")?params.get(i).getParam_name_en():(lang.equalsIgnoreCase("uz")?params.get(i).getParam_name_uz():params.get(i).getParam_name_ru())));
							return false;
						}
						params.get(i).setParam_value(CheckNull.isEmpty(n.getValue())?null:n.getValue());
					} else if (params.get(i).getParam_type().equals("PERSONGRID")){
						Listbox listbox = (Listbox) addinfo.getFellow("p_"+params.get(i).getParam_id());
						if (params.get(i).getParam_mandatory() == 1 && listbox.getModel().getSize()>0) {
							alert("Не заполнен обязательный параметр: "+(lang.equalsIgnoreCase("en")?params.get(i).getParam_name_en():(lang.equalsIgnoreCase("uz")?params.get(i).getParam_name_uz():params.get(i).getParam_name_ru())));
							return false;
						}
					} else {//STRING
						t = (Textbox) addinfo.getFellow("p_"+params.get(i).getParam_id());
						if (params.get(i).getParam_mandatory() == 1 && CheckNull.isEmpty(t.getValue())) {
							alert("Не заполнен обязательный параметр: "+(lang.equalsIgnoreCase("en")?params.get(i).getParam_name_en():(lang.equalsIgnoreCase("uz")?params.get(i).getParam_name_uz():params.get(i).getParam_name_ru())));
							return false;
						}
						params.get(i).setParam_value(CheckNull.isEmpty(t.getValue())?null:t.getValue());
					}
				}
			}
			parameters = params;
			res = true;
		} catch (Exception e) {
			e.printStackTrace();
			res = false;
		}
		return res;
	}
	
	public void onClick$btn_dwld() {
		try {
			Filedownload.save(new File(application.getRealPath("/files/IS_Informer_setup.exe")), "application/exe");
		} catch (Exception e) {
			alert("Файл IS_Informer_setup.exe не найден!");
		}
	}
	
	// -----------------------------USER_KEYS------------------------------------
	private Div userkeys;
	private Window user_keywnd = null;
	private KeyWndViewCtrl ukw = null;
	private Key currentkey = null;
	private Textbox key_code, key_sn;
	private Datebox key_expired;
	
	public void showUserKeys() {
		if (frmgrd.isVisible()) {
			userkeys.setVisible(true);
			refreshUserKey();
		} else {
			userkeys.setVisible(false);
		}
	}
	
	public void onClick$btn_addkey() {
		add_key(current);
	}
	
	public void onClick$btn_removekey() {
		if (current != null && currentkey != null) {
			remove_key(current, currentkey);
		} else {
			alert("Отсутствуют данные для открепления!");
		}
	}
	
	private void refreshUserKey() {
		if (current != null) {
			currentkey = KeyService.getUser_key(current);
			if (currentkey != null) {
				key_code.setValue(currentkey.getKey_code());
				key_sn.setValue(currentkey.getKey_sn());
				key_expired.setValue(currentkey.getKey_expired());
			} else {
				key_code.setValue("");
				key_sn.setValue("");
				key_expired.setValue(null);
			}
		}
	}
	
	private void remove_key(User user, Key key){
		com.is.utils.Res res = KeyService.removeUserKey(user, key);
		if (res.getCode() == 0) {
			refreshUserKey();
		} else {
			alert(res.getName());
		}
	}
	
	private void add_key(User user){
		try{
			if(user_keywnd == null){
				user_keywnd = (Window)Executions.createComponents("/sign/sign_keywnd.zul", usermain, null);
				user_keywnd.addEventListener("onAddKey", this);
  				ukw = (KeyWndViewCtrl)user_keywnd.getAttribute("user_keymain$composer");
  			}
  			if (ukw == null) {
  				ukw = (KeyWndViewCtrl)user_keywnd.getAttribute("user_keymain$composer");
  			}
  			ukw.init(user);
  		} catch (Exception e) {
  			e.printStackTrace();
  		}
	}
	
	public void onAddKey() {
		refreshUserKey();
	}
	
}


