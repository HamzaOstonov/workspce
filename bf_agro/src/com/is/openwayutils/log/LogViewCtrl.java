package com.is.openwayutils.log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.event.PagingEvent;

import IssuingWS.IssuingPortProxy;

import com.is.ConnectionPool;
import com.is.openwayutils.log.PagingListModel;
import com.is.openwayutils.utils.CheckNull;
import com.is.openwayutils.utils.RefCBox;



public class LogViewCtrl extends GenericForwardComposer 
{
	private String alias;
	private int _pageSize = 15;
	private int _totalSize, _startPageNumber = 0;
	private Listbox dataGrid;
	private RefCBox branch;
	private Textbox username, search;
	private PagingListModel model = null;
	private ListModelList lmodel =null;
    private Paging paging1;
    private LogFilter filter = new LogFilter();
    private Datebox from_dt, to_dt;
    private SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
	
	@Override
    public void doAfterCompose(Component comp) throws Exception {
            super.doAfterCompose(comp);
     
           //     binder = new AnnotateDataBinder(comp);
           //     binder.bindBean("current", this.current);
           //     binder.loadAll();
        String[] parameter = (String[]) param.get("ht");
        alias = (String) session.getAttribute("alias");
      
     //   states = TrPayService.getStates(alias);
        
        dataGrid.setItemRenderer(new ListitemRenderer(){
            @SuppressWarnings("unchecked")
            public void render(Listitem row, Object data) throws Exception {
            			Log log = (Log) data;
            			
                        row.setValue(log);
                        
                        row.appendChild(new Listcell(log.getBranch()));
                        row.appendChild(new Listcell(df.format(log.getTime_ex())));
                        row.appendChild(new Listcell(log.getUname()));
                        row.appendChild(new Listcell(log.getIp_address()));
                        row.appendChild(new Listcell(log.getEntity_id()));
                        
            }});
        refreshModel(0);
        branch.setModel((new ListModelList(com.is.openwayutils.utils.RefDataService.get_ipak_Mfo(alias))));
    }
    
	private void refreshModel(int activePage){
       // trpayPaging.setPageSize(_pageSize);
		//Date.parse("10.10.2010");//.getCurrentDate();
		//Date dt = Date.UTC(2010, 3, 3, 2, 3, 4);
    model = new PagingListModel(activePage, _pageSize, filter,alias);
    //model = new ListModelList(LogService.get_logs("", "", "", 1, "", dt.getValue(), dt.getValue(), alias));

            _totalSize = model.getTotalSize(filter,alias);


    paging1.setTotalSize(_totalSize);

    dataGrid.setModel((ListModel) model);
    
    if (model.getSize()>0){
    	dataGrid.setSelectedIndex(0);
    //sendSelEvt();
    }
    
}
	public void onClick$tbtn_search()
	{
		filter = new LogFilter();
		if (!CheckNull.isEmpty(username.getValue())) filter.setUname(username.getValue());
		if (!CheckNull.isEmpty(branch.getValue())) filter.setBranch(branch.getValue());
		if (!CheckNull.isEmpty(search.getValue())) filter.setEntity_id(search.getValue());
		if (!CheckNull.isEmpty(from_dt.getValue())) filter.setFrom_date(from_dt.getValue());
		if (!CheckNull.isEmpty(to_dt.getValue())) filter.setTo_date(to_dt.getValue());
		refreshModel(0);
	}
	
	public void onPaging$paging1(ForwardEvent event){
	    final PagingEvent pe = (PagingEvent) event.getOrigin();
	    _startPageNumber = pe.getActivePage();
	    refreshModel(_startPageNumber);
	}
	
}
