package com.is.payments.spr.s_kaznacc;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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
import org.zkoss.zul.Window;
import org.zkoss.zul.event.PagingEvent;

import com.is.utils.CheckNull;
import com.is.payments.spr.CookieUtil;
import com.is.utils.RefCBox;
import com.is.utils.RefData;

@SuppressWarnings("serial")
public class KaznaccViewCtrl extends GenericForwardComposer{
	private Window kaznaccwnd;
	private Div kaznaccwnd$frm;
   	private Div kaznaccwnd$grd;
   	private Grid kaznaccwnd$frmgrd,kaznaccwnd$fgrd;
   	private Toolbarbutton kaznaccwnd$btn_last;
   	private Toolbarbutton kaznaccwnd$btn_next;
   	private Toolbarbutton kaznaccwnd$btn_prev;
   	private Toolbarbutton kaznaccwnd$btn_first;
   	private Toolbarbutton kaznaccwnd$btn_add;
   	private Toolbarbutton kaznaccwnd$btn_search;
   	private Toolbarbutton kaznaccwnd$btn_back;
   	private Toolbar tb;
   	private Textbox kaznaccwnd$nci_id, kaznaccwnd$budget, kaznaccwnd$kod_doh, kaznaccwnd$kod_soato, kaznaccwnd$kod_acc, kaznaccwnd$kod_uns, kaznaccwnd$namebudget;
	private Datebox kaznaccwnd$date_open, kaznaccwnd$date_close;
	private Textbox kaznaccwnd$act;
	private Textbox kaznaccwnd$anci_id, kaznaccwnd$abudget, kaznaccwnd$akod_doh, kaznaccwnd$akod_soato, kaznaccwnd$akod_acc, kaznaccwnd$akod_uns, kaznaccwnd$anamebudget;
	private Datebox kaznaccwnd$adate_open, kaznaccwnd$adate_close;
	private Textbox kaznaccwnd$aact ;
	private Textbox kaznaccwnd$fnci_id, kaznaccwnd$fbudget, kaznaccwnd$fkod_doh, kaznaccwnd$fkod_soato, kaznaccwnd$fkod_acc, kaznaccwnd$fkod_uns, kaznaccwnd$fnamebudget;
	private Datebox kaznaccwnd$fdate_open, kaznaccwnd$fdate_close;
	private Textbox kaznaccwnd$fact ;
	private Paging kaznaccwnd$kaznaccPaging;
   	private int _pageSize = 15;
	private int _startPageNumber = 0;
	private int _oldSelectedIndex = 0;
	private int _totalSize = 0;
	private boolean _needsTotalSizeUpdate = true;
	private List<RefData> pagesizes = new ArrayList<RefData>();
	private RefCBox kaznaccwnd$pagesize;
	private Listbox kaznaccwnd$dataGrid;
	
	public Kaznacc bcurrent = new Kaznacc();
	public KaznaccFilter bfilter = new KaznaccFilter();

	PagingListModel model = null;
	ListModelList lmodel =null;
	private AnnotateDataBinder binder;
	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");

	public KaznaccViewCtrl() {
		super('$', false, false);
	}
    
    @Override
    public void doAfterCompose(Component comp) throws Exception {
    	super.doAfterCompose(comp);
    	// TODO Auto-generated method stub
    	binder = new AnnotateDataBinder(comp);
    	binder.bindBean("bcurrent", this.bcurrent);
    	binder.bindBean("bfilter", this.bfilter);
    	binder.loadAll();
        String[] parameter = (String[]) param.get("ht");
        if (parameter!=null){
        	_pageSize = Integer.parseInt( parameter[0])/36;
        	kaznaccwnd$dataGrid.setRows(Integer.parseInt( parameter[0])/36);
        }
        init();
        kaznaccwnd$dataGrid.setItemRenderer(new ListitemRenderer(){
        @SuppressWarnings("unchecked")
        public void render(Listitem row, Object data) throws Exception {
        	Kaznacc pKaznacc = (Kaznacc) data;
        	row.setValue(pKaznacc);
        	//row.appendChild(new Listcell(pKaznacc.getNci_id()));
            row.appendChild(new Listcell(pKaznacc.getBudget()));
            row.appendChild(new Listcell(pKaznacc.getKod_doh()));
            row.appendChild(new Listcell(pKaznacc.getKod_soato()));
            row.appendChild(new Listcell(pKaznacc.getKod_acc()));
            row.appendChild(new Listcell(pKaznacc.getKod_uns()));
            row.appendChild(new Listcell(pKaznacc.getNamebudget()));
            //row.appendChild(new Listcell(df.format(pKaznacc.getDate_open())));
            //row.appendChild(new Listcell(df.format(pKaznacc.getDate_close())));
            //row.appendChild(new Listcell(pKaznacc.getAct()));
        }});

        refreshModel(_startPageNumber);
        kaznaccwnd$dataGrid.focus();
    }
    
    private void init() {
   	 	pagesizes = KaznaccService.getPageSizes();
   	 	kaznaccwnd$pagesize.setModel(new ListModelList(pagesizes));
        if (CheckNull.isEmpty(CookieUtil.getCookie("pagesizebudget"))) {
        	CookieUtil.setCookie("pagesizebudget", ""+_pageSize);
        } else {
        	_pageSize = Integer.parseInt(CookieUtil.getCookie("pagesizebudget"));
        }
        kaznaccwnd$pagesize.setSelecteditem(""+_pageSize);
        kaznaccwnd$pagesize.setValue(""+_pageSize);
        kaznaccwnd$dataGrid.setRows(_pageSize+1);
    }

	public void onPaging$kaznaccPaging(ForwardEvent event){
	    final PagingEvent pe = (PagingEvent) event.getOrigin();
	    _startPageNumber = pe.getActivePage();
	    refreshModel(_startPageNumber);
	}

	private void refreshModel(int activePage){
		kaznaccwnd$kaznaccPaging.setPageSize(_pageSize);
        model = new PagingListModel(activePage, _pageSize, bfilter);
	    if(_needsTotalSizeUpdate) {
	    	_totalSize = model.getTotalSize(bfilter);
	    	//_needsTotalSizeUpdate = false;
	    }
	    kaznaccwnd$kaznaccPaging.setTotalSize(_totalSize);
	    kaznaccwnd$dataGrid.setModel((ListModel) model);
	    sort();
	    if (model.getSize()>0){
	    	kaznaccwnd$dataGrid.setSelectedIndex(_oldSelectedIndex);
	    	sendSelEvt();
	    	this.bcurrent =(Kaznacc) model.getElementAt(_oldSelectedIndex);
		    sendSelEvt();
	    }
	    kaznaccwnd$dataGrid.focus();
	}
	
	public void sort() {
		for (int i = 0; i < kaznaccwnd$dataGrid.getListhead().getChildren().size(); i++) {
			Listheader listheader = (Listheader)kaznaccwnd$dataGrid.getListhead().getChildren().get(i);
			if (!listheader.getSortDirection().equalsIgnoreCase("natural")) {
				listheader.sort(listheader.getSortDirection().equalsIgnoreCase("ascending"), true);
				return;
			}
			
		}
	}

	// Omitted...
	public Kaznacc getBcurrent() {
	    return bcurrent;
	}
	
	public void setBcurrent(Kaznacc bcurrent) {
	    this.bcurrent = bcurrent;
	}
	
	public void onOK$dataGrid$grd$kaznaccwnd() {
		onDoubleClick$dataGrid$grd$kaznaccwnd();
	}
	
	public void onDoubleClick$dataGrid$grd$kaznaccwnd() {
		kaznaccwnd.setVisible(false);
		if (bcurrent != null && !CheckNull.isEmpty(bcurrent.getBudget())) {
			//System.out.println("sendEvent");
			Events.sendEvent("onAddKaznacc", self, bcurrent);
		}
	}
	/*
	public void onClick$btn_back() {
        if (frm.isVisible()){
            frm.setVisible(false);
            grd.setVisible(true);
            btn_back.setImage("/images/file.png");
            btn_back.setLabel(Labels.getLabel("back"));
        }else onDoubleClick$dataGrid$grd();
	}
	*/
	public void onClick$btn_first$kaznaccwnd() {
		kaznaccwnd$dataGrid.setSelectedIndex(0);
        sendSelEvt();
	}
	
	public void onClick$btn_last$kaznaccwnd() {
		kaznaccwnd$dataGrid.setSelectedIndex(model.getSize()-1);
        sendSelEvt();
	}
	
	public void onClick$btn_prev$kaznaccwnd() {
        if (kaznaccwnd$dataGrid.getSelectedIndex()!=0){
        	kaznaccwnd$dataGrid.setSelectedIndex(kaznaccwnd$dataGrid.getSelectedIndex()-1);
	        sendSelEvt();
        }
	}
	
	public void onClick$btn_next$kaznaccwnd() {
        if (kaznaccwnd$dataGrid.getSelectedIndex()!=(model.getSize()-1)){
        	kaznaccwnd$dataGrid.setSelectedIndex(kaznaccwnd$dataGrid.getSelectedIndex()+1);
	        sendSelEvt();
        }
	}

	private void sendSelEvt(){
        if (kaznaccwnd$dataGrid.getSelectedIndex()==0){
        	kaznaccwnd$btn_first.setDisabled(true);
        	kaznaccwnd$btn_prev.setDisabled(true);
        }else{
        	kaznaccwnd$btn_first.setDisabled(false);
        	kaznaccwnd$btn_prev.setDisabled(false);
        }
        if(kaznaccwnd$dataGrid.getSelectedIndex()==(model.getSize()-1)){
        	kaznaccwnd$btn_next.setDisabled(true);
        	kaznaccwnd$btn_last.setDisabled(true);
        }else{
        	kaznaccwnd$btn_next.setDisabled(false);
        	kaznaccwnd$btn_last.setDisabled(false);
        }
        SelectEvent evt = new SelectEvent("onSelect", kaznaccwnd$dataGrid,kaznaccwnd$dataGrid.getSelectedItems());
        Events.sendEvent(evt);
	}
	/*
	public void onClick$btn_add() {
        onDoubleClick$kaznaccwnd$dataGrid$grd();
        frmgrd.setVisible(false);
        addgrd.setVisible(true);
        fgrd.setVisible(false);
	}

	public void onClick$btn_search() {
        onDoubleClick$kaznaccwnd$dataGrid$grd();
        frmgrd.setVisible(false);
        addgrd.setVisible(false);
        fgrd.setVisible(true);
	}
	*/

	/*public void onClick$btn_save() {
	    try{
	        if(addgrd.isVisible()){
	        	
	        	KaznaccService.create(new Kaznacc(
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
	        	
	            CheckNull.clearForm(addgrd);
	            frmgrd.setVisible(true);
	            addgrd.setVisible(false);
	            fgrd.setVisible(false);
	        }else if(fgrd.isVisible()){
	            bfilter = new KaznaccFilter();
	            if (!CheckNull.isEmpty(fnci_id.getValue())) {
					bfilter.setNci_id(fnci_id.getValue());
				}
				if (!CheckNull.isEmpty(fbudget.getValue())) {
					bfilter.setBudget(fbudget.getValue());
				}
				if (!CheckNull.isEmpty(fkod_doh.getValue())) {
					bfilter.setKod_doh(fkod_doh.getValue());
				}
				if (!CheckNull.isEmpty(fkod_soato.getValue())) {
					bfilter.setKod_soato(fkod_soato.getValue());
				}
				if (!CheckNull.isEmpty(fkod_acc.getValue())) {
					bfilter.setKod_acc(fkod_acc.getValue());
				}
				if (!CheckNull.isEmpty(fkod_uns.getValue())) {
					bfilter.setKod_uns(fkod_uns.getValue());
				}
				if (!CheckNull.isEmpty(fnamebudget.getValue())) {
					bfilter.setNamebudget(fnamebudget.getValue());
				}
				if (!CheckNull.isEmpty(fdate_open.getValue())) {
					bfilter.setDate_open(fdate_open.getValue());
				}
				if (!CheckNull.isEmpty(fdate_close.getValue())) {
					bfilter.setDate_close(fdate_close.getValue());
				}
				if (!CheckNull.isEmpty(fact.getValue())) {
					bfilter.setAct(fact.getValue());
				}
	        }else{
		       
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

	            KaznaccService.update(current);
	            
	        }
		    //onClick$btn_back();
		    refreshModel(_startPageNumber);
		    SelectEvent evt = new SelectEvent("onSelect", kaznaccwnd$dataGrid,kaznaccwnd$dataGrid.getSelectedItems());
		    Events.sendEvent(evt);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}*/
	
	public void onClick$btn_send$kaznaccwnd() {
        onDoubleClick$dataGrid$grd$kaznaccwnd();
	}

    public void onClick$btn_send(){
        System.out.println("onCLick btn send");
    }
	
	/*public void onClick$btn_cancel() {
        if(fgrd.isVisible()){
        	bfilter = new KaznaccFilter();
        }
	    //onClick$btn_back();
	    frmgrd.setVisible(true);
	    addgrd.setVisible(false);
	    fgrd.setVisible(false);
	    CheckNull.clearForm(addgrd);
	    CheckNull.clearForm(fgrd);
	    refreshModel(_startPageNumber);
	}*/
	
	public void onClick$btn_refresh$kaznaccwnd() {
		refreshModel(_startPageNumber);
	}
	
	public void onClick$btn_find$kaznaccwnd() {
		bfilter = new KaznaccFilter();
        /*if (!CheckNull.isEmpty(fnci_id.getValue())) {
			bfilter.setNci_id(fnci_id.getValue());
		}*/
		if (!CheckNull.isEmpty(kaznaccwnd$fbudget.getValue())) {
			bfilter.setBudget(kaznaccwnd$fbudget.getValue());
		}
		/*if (!CheckNull.isEmpty(fkod_doh.getValue())) {
			bfilter.setKod_doh(fkod_doh.getValue());
		}
		if (!CheckNull.isEmpty(fkod_soato.getValue())) {
			bfilter.setKod_soato(fkod_soato.getValue());
		}*/
		if (!CheckNull.isEmpty(kaznaccwnd$fkod_acc.getValue())) {
			bfilter.setKod_acc(kaznaccwnd$fkod_acc.getValue());
		}
		if (!CheckNull.isEmpty(kaznaccwnd$fkod_uns.getValue())) {
			bfilter.setKod_uns(kaznaccwnd$fkod_uns.getValue());
		}
		if (!CheckNull.isEmpty(kaznaccwnd$fnamebudget.getValue())) {
			bfilter.setNamebudget(kaznaccwnd$fnamebudget.getValue());
		}
		/*if (!CheckNull.isEmpty(fdate_open.getValue())) {
			bfilter.setDate_open(fdate_open.getValue());
		}
		if (!CheckNull.isEmpty(fdate_close.getValue())) {
			bfilter.setDate_close(fdate_close.getValue());
		}
		if (!CheckNull.isEmpty(fact.getValue())) {
			bfilter.setAct(fact.getValue());
		}*/
	    _startPageNumber = 0;
		_oldSelectedIndex = 0;
		kaznaccwnd$kaznaccPaging.setActivePage(0);
		refreshModel(_startPageNumber);
	}
	
	public void onClick$btn_find_clear$kaznaccwnd() {
		//currclientfilter = new ClientFilter();
	    CheckNull.clearForm(kaznaccwnd$fgrd);
	}
	
	public void onClick$btn_find_cancel$kaznaccwnd() {
		bfilter = new KaznaccFilter();
		//tfilter.setBranch(current.getBranch());
		//tfilter.setClient(current.getClient_id());
		CheckNull.clearForm(kaznaccwnd$fgrd);
		//aliasacc = ss_dblink_branches.get(0).getLabel();
		_startPageNumber = 0;
		_oldSelectedIndex = 0;
		kaznaccwnd$kaznaccPaging.setActivePage(0);
		refreshModel(_startPageNumber);
	}
	
	public void onSelect$pagesize$kaznaccwnd() {
		_pageSize = Integer.parseInt(kaznaccwnd$pagesize.getValue());
		CookieUtil.setCookie("pagesizebudget", ""+_pageSize);
		kaznaccwnd$dataGrid.setRows(_pageSize+1);
        _oldSelectedIndex = 0;
        _startPageNumber = 0;
        kaznaccwnd$kaznaccPaging.setActivePage(_startPageNumber);
        refreshModel(_startPageNumber);
	}
	
}
