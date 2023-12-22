package com.is.client_spec;

import java.text.SimpleDateFormat;
import java.util.List;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Div;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;
import org.zkoss.zul.event.PagingEvent;

import com.is.base.Dao;
import com.is.base.paging.PagingListModel;
import com.is.clients.controllers.renderers.HistoryRenderer;
import com.is.clients.models.ClientJ;
import com.is.clients.services.DictionaryKeeper;
import com.is.clients.services.ServiceFactory;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.RefData;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
public class SpecCltViewCtrl extends GenericForwardComposer {
	private Window spec_history;
	
	private Div frm,grd,fgrd,newgrd;
	private Listbox dataGrid,
					spec_history$history;
	@SuppressWarnings("unused")
	private Paging contactPaging;
	private Toolbarbutton btn_last;
	private Toolbarbutton btn_next;
	private Toolbarbutton btn_prev;
	private Toolbarbutton btn_first;
	private Toolbarbutton btn_back;
	private Toolbarbutton btn_save;
	@SuppressWarnings("unused")
	private Toolbar tb;
	@SuppressWarnings("unused")
	private Textbox branch, 
					id_client, 
					value, 
					prim,
					name,
					type_name_text,
					id_specialText;
	private RefCBox id_special, code_type;
	@SuppressWarnings("unused")
	private Textbox abranch, 
					aid_client, 
					avalue, 
					aprim,
					aname,
					atype_name_text,
					aid_specialText;;
	private RefCBox aid_special, acode_type;
	@SuppressWarnings("unused")
	private Textbox fbranch, 
					fid_client, 
					fvalue, 
					fprim,
					fname,
					ftype_name_text,
					fid_specialText;
	private RefCBox fid_special, fcode_type;
	private Paging specharPaging;
	private int _pageSize = 15;
	private int _startPageNumber = 0;
	private int _totalSize = 0;
	private boolean _needsTotalSizeUpdate = true;


	private Dao<SpecClt> specharDao;
	PagingListModel<SpecClt> model = null;
	ListModelList lmodel = null;
	private AnnotateDataBinder specCltBinder;
	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	SimpleDateFormat dfWithTime = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

//	private ServiceFactory serviceFactory;
	@Getter @Setter private SpecClt current = new SpecClt();
	@Getter @Setter private SpecCltFilter filter = new SpecCltFilter();
	@Getter @Setter private SpecClt newSpecclt = new SpecClt();
//	private List<SsSpecial> ssSpecialList;
	private DictionaryKeeper dictionaryKeeper;
	
	private String alias;
	private String branch1;
	private String clientId;
	private String clientName;
	private String clientType;
	
	public SpecCltViewCtrl() {
		super('$', false, false);
	}
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		specCltBinder = new AnnotateDataBinder(comp);
		specCltBinder.bindBean("current", this.current);
		specCltBinder.bindBean("filter", this.filter);
		specCltBinder.bindBean("newSpecclt", this.newSpecclt);
		specCltBinder.loadAll();
		String[] parameter = (String[]) param.get("ht");
		if (parameter != null) {
			_pageSize = Integer.parseInt(parameter[0]) / 36;
			dataGrid.setRows(Integer.parseInt(parameter[0]) / 36);
		}
		alias = (String) session.getAttribute("alias");
		branch1 = (String) session.getAttribute("branch");
		
		specharDao = SpecCltDao.getInstance(alias);
		dataGrid.setItemRenderer(new SpecharRenderer());
		spec_history$history.setItemRenderer(new HistoryRenderer());
		
		btn_save.setAttribute("action", "none");

		String[] code_type = (String[])param.get("code_type");
		String[] clientId = (String[])param.get("clientId");
		String[] name = (String[])param.get("name");
		if(code_type != null && clientId != null && code_type !=null && code_type[0].equals("08")){
		    ServiceFactory serviceFactory = ServiceFactory.getInstance(alias,null,null);
		    ClientJ clientJ = new ClientJ();
		    clientJ.setCode_type(code_type[0]);
		    clientJ.setId_client(clientId[0]);
		    clientJ.setName(name[0]);
		    clientJ.setBranch(branch1);
            this.clientId = clientId[0];
            this.clientName = name[0];
            this.clientType = code_type[0];
            //Messagebox.show((serviceFactory.getDictionaryKeeper().getClientTypes() == null) + "");
		    init(clientJ,serviceFactory);
        }
	}
	
//	private void intFromInclude(String clientId) {
//		this.dictionaryKeeper = ServiceFactory.getInstance(alias, null, null).getDictionaryKeeper();
//		this.clientId = clientId;
//		setModels();
//		if(clientId != null){
//			filter.setId_client(clientId); 
//		}
//		refreshModel(_startPageNumber);
//	}
	
	public void init(ClientJ client, ServiceFactory factory) {
		this.dictionaryKeeper = factory.getDictionaryKeeper();
		this.dictionaryKeeper.initLists();
		this.clientId = client.getId_client();
		this.clientName =  client.getName();
		this.clientType = client.getCode_type();
		setModels();
		if(clientId != null){
			filter.setId_client(clientId); 
		}
		refreshModel(_startPageNumber);
	}
	
	private void setModels(){
		List<RefData> spechars = SpecCltService.getSpechars(alias);
//		ssSpecialList = SpecCltService.getSsSpecial(alias);
		
		id_special.setModel(new ListModelList(spechars));
		aid_special.setModel(new ListModelList(spechars));
		fid_special.setModel(new ListModelList(spechars));
		
		code_type.setModel(new ListModelList(DictionaryKeeper.getClientTypes()));
		acode_type.setModel(new ListModelList(DictionaryKeeper.getClientTypes()));
		fcode_type.setModel(new ListModelList(DictionaryKeeper.getClientTypes()));
		
	}
	public void onPaging$specharPaging(ForwardEvent event) {
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModel(_startPageNumber);
	}

	private void refreshModel(int activePage) {
		specharPaging.setPageSize(_pageSize);
		filter.setBranch(branch1);
		specharDao.setFilter(filter);
		model = new PagingListModel<SpecClt>(activePage, _pageSize,specharDao);

		if (_needsTotalSizeUpdate) {
			_totalSize = model.getTotalSize();
			_needsTotalSizeUpdate = false;
		}

		specharPaging.setTotalSize(_totalSize);

		dataGrid.setModel((ListModel) model);
		if (model.getSize() > 0) {
			this.current = (SpecClt) model.getElementAt(0);
			sendSelEvt();
		}
	}

	public void onDoubleClick$dataGrid$grd() {
		hideAll();
		frm.setVisible(true);
		if(current != null && clientId != null
				&& current.getId_client() != null
				&& current.getId_client().equals(clientId)) {
			setClientInfo();
		}
		btn_save.setAttribute("action", "update");
		btn_save.setVisible(true);
		btn_back.setImage("/images/folder.png");
		btn_back.setLabel(Labels.getLabel("grid"));
	}
	
	public void onClick$btn_back() {
		if (!grd.isVisible()) {
			hideAll();
			grd.setVisible(true);
			btn_back.setImage("/images/file.png");
			btn_back.setLabel(Labels.getLabel("back"));
		} else
			onDoubleClick$dataGrid$grd();
	}

	public void onClick$btn_first() {
		dataGrid.setSelectedIndex(0);
		sendSelEvt();
	}

	public void onClick$btn_last() {
		dataGrid.setSelectedIndex(model.getSize() - 1);
		sendSelEvt();
	}

	public void onClick$btn_prev() {
		if (dataGrid.getSelectedIndex() != 0) {
			dataGrid.setSelectedIndex(dataGrid.getSelectedIndex() - 1);
			sendSelEvt();
		}
	}

	public void onClick$btn_next() {
		if (dataGrid.getSelectedIndex() != (model.getSize() - 1)) {
			dataGrid.setSelectedIndex(dataGrid.getSelectedIndex() + 1);
			sendSelEvt();
		}
	}

	private void sendSelEvt() {
		if (dataGrid.getSelectedIndex() == 0) {
			btn_first.setDisabled(true);
			btn_prev.setDisabled(true);
		} else {
			btn_first.setDisabled(false);
			btn_prev.setDisabled(false);
		}
		if (dataGrid.getSelectedIndex() == (model.getSize() - 1)) {
			btn_next.setDisabled(true);
			btn_last.setDisabled(true);
		} else {
			btn_next.setDisabled(false);
			btn_last.setDisabled(false);
		}
		SelectEvent evt = new SelectEvent("onSelect", dataGrid,
				dataGrid.getSelectedItems());
		Events.sendEvent(evt);
	}

	public void onClick$btn_add() {
		newSpecclt = new SpecClt();
		newSpecclt.setId_client(clientId);
		newSpecclt.setName(clientName);
		newSpecclt.setCode_type(clientType);
		newSpecclt.setBranch(branch1);
		specCltBinder.loadAll();
//		aid_client.setValue(clientId);
//		aname.setValue(clientName);
//		atype_name.setSelecteditem(clientType);
		hideAll();
		btn_save.setAttribute("action", "new");
		btn_save.setImage("/images/save.png");
		btn_save.setLabel(Labels.getLabel("save"));
		btn_save.setVisible(true);
		newgrd.setVisible(true);
	}

	public void onClick$btn_search() {
		filter = new SpecCltFilter();
		hideAll();
		btn_save.setAttribute("action", "filter");
		btn_save.setImage("/images/search.png");
		btn_save.setLabel(Labels.getLabel("search"));
		btn_save.setVisible(true);
		fgrd.setVisible(true);
	}

	public void onClick$btn_save() throws Exception {
		String buttonAction = (String) btn_save.getAttribute("action");
		
		if(buttonAction.equals("new")) {
			if(CheckNull.isEmpty(newSpecclt.getId_special())
					||CheckNull.isEmpty(newSpecclt.getValue())){
				alert("«аполните пол€: тип характеристики, значение");
				return;
			}
			specharDao.create(newSpecclt);
			refreshModel(_startPageNumber);
			onClick$btn_back();
		} else if(buttonAction.equals("update")) {
			specharDao.update(current);
		} else if(buttonAction.equals("filter")) {
			filter();
		}
		
		
	}

	public void onClick$btn_cancel() {
		if (fgrd.isVisible()) {
			filter = new SpecCltFilter();
		}
		onClick$btn_back();
		fgrd.setVisible(false);
		refreshModel(_startPageNumber);
	}
	public void filter(){
//		filter = new SpecCltFilter();
//
//		if(!CheckNull.isEmpty(filter.getBranch())){
//			filter.setBranch(fbranch.getValue());
//		}
//		if(!CheckNull.isEmpty(fid_special.getValue())){
//			filter.setId_special(Long.parseLong(fid_special.getValue()));
//		}
//		if(!CheckNull.isEmpty(fid_client.getValue())){
//			filter.setId_client(fid_client.getValue());
//		}
//		if(!CheckNull.isEmpty(fvalue.getValue())){
//			filter.setValue(fvalue.getValue());
//		}
//		if(!CheckNull.isEmpty(fprim.getValue())){
//			filter.setPrim(fprim.getValue());
//		}
//		if(!CheckNull.isEmpty(fname.getValue())){
//			filter.setPrim(fname.getValue());
//		}
//		if(!CheckNull.isEmpty(ftype_name.getValue())){
//			filter.setPrim(ftype_name.getValue());
//		}
		onClick$btn_back();
		refreshModel(_startPageNumber);
		SelectEvent evt = new SelectEvent("onSelect", dataGrid,
				dataGrid.getSelectedItems());
		Events.sendEvent(evt);
	}
	public void onClick$btn_cancel_filter(){
		onClick$btn_back();
	}
	public void onClick$btn_history(){
		spec_history$history.setModel(new ListModelList(SpecCltService.getHistory(clientId, branch1, alias)));
		spec_history.setVisible(true);
	}
	
	public void onFocus$avalue() {
		
	}

	
	private void hideAll(){
		grd.setVisible(false);
		frm.setVisible(false);
		fgrd.setVisible(false);
		newgrd.setVisible(false);
		btn_save.setVisible(false);
	}
	
	private void setClientInfo() {
		current.setName(clientName);
		current.setCode_type(clientType);
	}
}
