package com.is.payments.spr.s_budspr;

import java.text.SimpleDateFormat;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.event.PagingEvent;

import com.is.utils.CheckNull;

@SuppressWarnings("serial")
public class S_budsprViewCtrl extends GenericForwardComposer{
	private Div frm;
  	private Paging contactPaging;
   	private Div grd;
   	private Listbox dataGrid;
   	private Grid addgrd,frmgrd,fgrd;
   	private Toolbarbutton btn_last;
   	private Toolbarbutton btn_next;
   	private Toolbarbutton btn_prev;
   	private Toolbarbutton btn_first;
   	private Toolbarbutton btn_add;
   	private Toolbarbutton btn_search;
   	private Toolbarbutton btn_back;
   	private Toolbar tb;
	private Textbox nci_id, treasure_id, account, inn, name;
	private Datebox date_open, date_close;
	private Textbox act, bank_id, bankacc, bankinn, bankaccname;
	private Textbox anci_id, atreasure_id, aaccount, ainn, aname;
	private Datebox adate_open, adate_close;
	private Textbox aact, abank_id, abankacc, abankinn, abankaccname ;
	private Textbox fnci_id, ftreasure_id, faccount, finn, fname;
	private Datebox fdate_open, fdate_close;
	private Textbox fact, fbank_id, fbankacc, fbankinn, fbankaccname ;
	private Paging s_budsprPaging;
	private  int _pageSize = 15;
	private int _startPageNumber = 0;
	private int _totalSize = 0;
	private boolean _needsTotalSizeUpdate = true;

	public S_budspr current = new S_budspr();
	public S_budsprFilter filter = new S_budsprFilter();

	PagingListModel model = null;
	ListModelList lmodel =null;
	private AnnotateDataBinder binder;
	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");

	public S_budsprViewCtrl() {
		super('$', false, false);
	}
    
    @Override
    public void doAfterCompose(Component comp) throws Exception {
    	super.doAfterCompose(comp);
    	// TODO Auto-generated method stub
    	binder = new AnnotateDataBinder(comp);
    	binder.bindBean("current", this.current);
    	binder.loadAll();
        String[] parameter = (String[]) param.get("ht");
        if (parameter!=null){
        	_pageSize = Integer.parseInt( parameter[0])/36;
        	dataGrid.setRows(Integer.parseInt( parameter[0])/36);
        }

        dataGrid.setItemRenderer(new ListitemRenderer(){
        @SuppressWarnings("unchecked")
        public void render(Listitem row, Object data) throws Exception {
        	S_budspr pS_budspr = (S_budspr) data;
        	row.setValue(pS_budspr);
        	
			                    row.appendChild(new Listcell(pS_budspr.getNci_id()));
			                    row.appendChild(new Listcell(pS_budspr.getTreasure_id()));
			                    row.appendChild(new Listcell(pS_budspr.getAccount()));
			                    row.appendChild(new Listcell(pS_budspr.getInn()));
			                    row.appendChild(new Listcell(pS_budspr.getName()));
			                    row.appendChild(new Listcell(df.format(pS_budspr.getDate_open())));
			                    row.appendChild(new Listcell(df.format(pS_budspr.getDate_close())));
			                    row.appendChild(new Listcell(pS_budspr.getAct()));
			                    row.appendChild(new Listcell(pS_budspr.getBank_id()));
			                    row.appendChild(new Listcell(pS_budspr.getBankacc()));
			                    row.appendChild(new Listcell(pS_budspr.getBankinn()));
			                    row.appendChild(new Listcell(pS_budspr.getBankaccname()));
        }});

        refreshModel(_startPageNumber);
    }

	public void onPaging$s_budsprPaging(ForwardEvent event){
	    final PagingEvent pe = (PagingEvent) event.getOrigin();
	    _startPageNumber = pe.getActivePage();
	    refreshModel(_startPageNumber);
	}

	private void refreshModel(int activePage){
        s_budsprPaging.setPageSize(_pageSize);
        model = new PagingListModel(activePage, _pageSize, filter);
	    if(_needsTotalSizeUpdate) {
	    	_totalSize = model.getTotalSize(filter);
	    	//_needsTotalSizeUpdate = false;
	    }
	    s_budsprPaging.setTotalSize(_totalSize);
	    dataGrid.setModel((ListModel) model);
	    sort();
	    if (model.getSize()>0){
	    	dataGrid.setSelectedIndex(0);
	    	this.current =(S_budspr) model.getElementAt(0);
		    sendSelEvt();
	    }
	}
	
	public void sort() {
		for (int i = 0; i < dataGrid.getListhead().getChildren().size(); i++) {
			Listheader listheader = (Listheader)dataGrid.getListhead().getChildren().get(i);
			if (!listheader.getSortDirection().equalsIgnoreCase("natural")) {
				listheader.sort(listheader.getSortDirection().equalsIgnoreCase("ascending"), true);
				return;
			}
			
		}
	}

	// Omitted...
	public S_budspr getCurrent() {
	    return current;
	}
	
	public void setCurrent(S_budspr current) {
	    this.current = current;
	}
	
	public void onDoubleClick$dataGrid$grd() {
    	grd.setVisible(false);
        frm.setVisible(true);
        frmgrd.setVisible(true);
        addgrd.setVisible(false);
        fgrd.setVisible(false);
        btn_back.setImage("/images/folder.png");
        btn_back.setLabel(Labels.getLabel("grid"));
	}

	public void onClick$btn_back() {
        if (frm.isVisible()){
            frm.setVisible(false);
            grd.setVisible(true);
            btn_back.setImage("/images/file.png");
            btn_back.setLabel(Labels.getLabel("back"));
        }else onDoubleClick$dataGrid$grd();
	}

	public void onClick$btn_first() {
        dataGrid.setSelectedIndex(0);
        sendSelEvt();
	}
	
	public void onClick$btn_last() {
        dataGrid.setSelectedIndex(model.getSize()-1);
        sendSelEvt();
	}
	
	public void onClick$btn_prev() {
        if (dataGrid.getSelectedIndex()!=0){
	        dataGrid.setSelectedIndex(dataGrid.getSelectedIndex()-1);
	        sendSelEvt();
        }
	}
	
	public void onClick$btn_next() {
        if (dataGrid.getSelectedIndex()!=(model.getSize()-1)){
	        dataGrid.setSelectedIndex(dataGrid.getSelectedIndex()+1);
	        sendSelEvt();
        }
	}

	private void sendSelEvt(){
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
        SelectEvent evt = new SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
        Events.sendEvent(evt);
	}

	public void onClick$btn_add() {
        onDoubleClick$dataGrid$grd();
        frmgrd.setVisible(false);
        addgrd.setVisible(true);
        fgrd.setVisible(false);
	}

	public void onClick$btn_search() {
        onDoubleClick$dataGrid$grd();
        frmgrd.setVisible(false);
        addgrd.setVisible(false);
        fgrd.setVisible(true);
	}


	public void onClick$btn_save() {
	    try{
	        if(addgrd.isVisible()){
	        	/*
	        	S_budsprService.create(new S_budspr(
		                anci_id.getValue(),
		                atreasure_id.getValue(),
		                aaccount.getValue(),
		                ainn.getValue(),
		                aname.getValue(),
		                adate_open.getValue(),
		                adate_close.getValue(),
		                aact.getValue(),
		                abank_id.getValue(),
		                abankacc.getValue(),
		                abankinn.getValue(),
		                abankaccname.getValue()
	        	));
	        	*/
	            CheckNull.clearForm(addgrd);
	            frmgrd.setVisible(true);
	            addgrd.setVisible(false);
	            fgrd.setVisible(false);
	        }else if(fgrd.isVisible()){
	            filter = new S_budsprFilter();
				if (CheckNull.isEmpty(fnci_id.getValue())) {
					filter.setNci_id(fnci_id.getValue());
				}
				if (CheckNull.isEmpty(ftreasure_id.getValue())) {
					filter.setTreasure_id(ftreasure_id.getValue());
				}
				if (CheckNull.isEmpty(faccount.getValue())) {
					filter.setAccount(faccount.getValue());
				}
				if (CheckNull.isEmpty(finn.getValue())) {
					filter.setInn(finn.getValue());
				}
				if (CheckNull.isEmpty(fname.getValue())) {
					filter.setName(fname.getValue());
				}
				if (CheckNull.isEmpty(fdate_open.getValue())) {
					filter.setDate_open(fdate_open.getValue());
				}
				if (CheckNull.isEmpty(fdate_close.getValue())) {
					filter.setDate_close(fdate_close.getValue());
				}
				if (CheckNull.isEmpty(fact.getValue())) {
					filter.setAct(fact.getValue());
				}
				if (CheckNull.isEmpty(fbank_id.getValue())) {
					filter.setBank_id(fbank_id.getValue());
				}
				if (CheckNull.isEmpty(fbankacc.getValue())) {
					filter.setBankacc(fbankacc.getValue());
				}
				if (CheckNull.isEmpty(fbankinn.getValue())) {
					filter.setBankinn(fbankinn.getValue());
				}
				if (CheckNull.isEmpty(fbankaccname.getValue())) {
					filter.setBankaccname(fbankaccname.getValue());
				}

	        }else{
		       /*
	        	current.setNci_id(nci_id.getValue());
		        	  current.setTreasure_id(treasure_id.getValue());
		        	  current.setAccount(account.getValue());
		        	  current.setInn(inn.getValue());
		        	  current.setName(name.getValue());
		        	  current.setDate_open(date_open.getValue());
		        	  current.setDate_close(date_close.getValue());
		        	  current.setAct(act.getValue());
		        	  current.setBank_id(bank_id.getValue());
		        	  current.setBankacc(bankacc.getValue());
		        	  current.setBankinn(bankinn.getValue());
		        	  current.setBankaccname(bankaccname.getValue());

	            S_budsprService.update(current);
	            */
	        }
		    onClick$btn_back();
		    refreshModel(_startPageNumber);
		    SelectEvent evt = new SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
		    Events.sendEvent(evt);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public void onClick$btn_cancel() {
        if(fgrd.isVisible()){
        	filter = new S_budsprFilter();
        }
	    onClick$btn_back();
	    frmgrd.setVisible(true);
	    addgrd.setVisible(false);
	    fgrd.setVisible(false);
	    CheckNull.clearForm(addgrd);
	    CheckNull.clearForm(fgrd);
	    refreshModel(_startPageNumber);
	}

    public void onClick$btn_send$budgetwnd(){
        self.setVisible(true);
    }
}
