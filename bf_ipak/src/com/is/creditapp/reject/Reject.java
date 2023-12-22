package com.is.creditapp.reject;

import java.util.Date;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Include;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Row;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbarbutton;
import com.is.bpri.BproductService;
import com.is.bpri.utils.Utils;
import com.is.creditapp.CreditApp;
import com.is.creditapp.CreditAppFilter;
import com.is.creditapp.CreditAppService;
import com.is.creditapp.PagingListModel;
import com.is.utils.RefCBox;
import com.is.utils.Res;
//import com.is.creditapp.CreditAppViewController;

@SuppressWarnings("serial")
public class Reject extends GenericForwardComposer{
	
	private Div frm = null,grd = null;
	private Include include = null;
	private Textbox rdoc,rtxt,mfo;
	private Datebox datecancel;
	private RefCBox preason,ragency;
	private Grid maingrd;
	private Row inforow;
	private String branch,un,pw;
	private String alias;
	private String id;
	private Date date;
	private Label idlabel;
	private String btn = "";
	private CreditApp current = null;
	private Toolbarbutton btn_reject,btn_rejectdata,btn_back,btn_cl_refresh;
	private Paging ni_reqPaging;
	private PagingListModel model;
	private int _pageSize,_totalSize,_startPageNumber;
	private CreditAppFilter filter;
	private Listbox dataGrid;
	 
	public Reject() {
		super('$',false,false);
	}
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		branch = ((String[]) this.param.get("branch"))[0];
		alias = ((String[]) this.param.get("alias"))[0];
		un = ((String[]) this.param.get("un"))[0];
		pw = ((String[]) this.param.get("pwd"))[0];
		id = ((String[]) this.param.get("id"))[0];
		btn = ((String[]) this.param.get("btn"))[0];
		idlabel.setValue("Регистрационный номер - "+id);
		refcboxModel();
		getSessionObject();
		if(btn.equals("2")){
			inputDatas();
			Utils.ReadOnly(maingrd, true);
		} else {
			inforow.setVisible(false);
			btn_reject.setVisible(true);
			Utils.ReadOnly(maingrd, false);
		}
	}
	
	private void getSessionObject(){
		frm = (Div) session.getAttribute("frm");
		include = (Include) session.getAttribute("include");
		current = (CreditApp) session.getAttribute("current");
		grd = (Div) session.getAttribute("grd");
		btn_back = (Toolbarbutton) session.getAttribute("btn_back");
		btn_rejectdata = (Toolbarbutton) session.getAttribute("btn_rejectdata");
		btn_cl_refresh = (Toolbarbutton) session.getAttribute("btn_cl_refresh");
		ni_reqPaging = (Paging) session.getAttribute("ni_reqPaging");
		model = (PagingListModel) session.getAttribute("model");
		_pageSize = (Integer) session.getAttribute("_pageSize");
		filter = (CreditAppFilter) session.getAttribute("filter");
		_totalSize = (Integer) session.getAttribute("_totalSize");
		dataGrid = (Listbox) session.getAttribute("dataGrid");
		_startPageNumber = (Integer) session.getAttribute("_startPageNumber");
	}
	
	public void onInitRenderLater$preason(){
		if(btn.equals("2")){
			preason.setSelecteditem(current.getRreason().trim());
		}
	}
	
	public void onInitRenderLater$ragency(){
		if(btn.equals("2")){
			ragency.setSelecteditem(current.getRagency().trim());
		}
	}
	
	private void refreshModel(int activePage){
    	ni_reqPaging.setPageSize(_pageSize);
    	model = new PagingListModel(activePage, _pageSize, filter, alias);
    	_totalSize = model.getTotalSize(filter,alias);
    	ni_reqPaging.setTotalSize(_totalSize);
    	dataGrid.setModel((ListModel) model);
    	filter.setBranch(branch);
    	if (model.getSize()>0){
    		this.current =(CreditApp) model.getElementAt(0);
    		SelectEvent evt = new SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
        	Events.sendEvent(evt);
    	}
    }
	
	private void inputDatas(){
		inforow.setVisible(true);
		datecancel.setValue(current.getRdate());
		rdoc.setValue(current.getRdoc());
		rtxt.setValue(current.getRtxt());
		mfo.setValue(current.getBranch());
		btn_reject.setVisible(false);
	}
	
	public void onClick$btn_reject(){
		Res res = new Res();
		date = Utils.getInfoDate(alias, res);
//		CreditAppService.updateState(rdoc.getValue(), rtxt.getValue(), preason.getValue(), ragency.getValue(), date, "6", id, branch,alias, res);
		current.setRdoc(rdoc.getValue());
		current.setRtxt(rtxt.getValue());
		current.setRreason(preason.getValue());
		current.setRagency(ragency.getValue());
		current.setRdate(date);
		CreditAppService.doActionForCreditApp(current, 6, alias, res);
		if(res.getCode()!=1){
			alert("Заявка успешно откланена");
			onClick$btn_cancel();
			refreshModel(_startPageNumber);
		} else {
			alert(res.getName());
		}
	}
	
	public void onClick$btn_cancel(){
		include.setVisible(false);
		include.setSrc(null);
		frm.setVisible(false);
		btn_rejectdata.setVisible(false);
		grd.setVisible(true);
		btn_back.setImage("/images/file.png");
		btn_back.setLabel(Labels.getLabel("back"));
		btn_cl_refresh.setVisible(false);
	}
	
	private void refcboxModel(){
		ragency.setModel(new ListModelList(CreditAppService.getAgency(alias)));
		preason.setModel(new ListModelList(CreditAppService.getReason(alias)));
	}
}
