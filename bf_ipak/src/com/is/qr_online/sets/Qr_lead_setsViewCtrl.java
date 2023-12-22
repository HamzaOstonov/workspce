package com.is.qr_online.sets;

import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;
import org.zkoss.zul.event.PagingEvent;

import com.is.qr_online.merchant.MerchantFilter;
import com.is.utils.CheckNull;

public class Qr_lead_setsViewCtrl extends GenericForwardComposer{
	
        /**
	 * 
	 */
	    private static final long serialVersionUID = -7007290898996568844L;
	    private Window qr_lead_setsmain;
	    private Label payee_inn;
		private Div frm;
        private Listbox dataGrid;
        private Paging contactPaging;
        private Div grd;
        private Grid addgrd,frmgrd,fgrd;
        private Toolbarbutton btn_last;
        private Toolbarbutton btn_next;
        private Toolbarbutton btn_prev;
        private Toolbarbutton btn_first;
        private Toolbarbutton btn_add;
        private Toolbarbutton btn_search;
        private Toolbarbutton btn_back;
        private Toolbarbutton btn_update;
        private Toolbar tb;
        private Textbox branch_dt,account_dt,inn_dt,branch_ct,account_ct,inn_ct,purpose_code,purpose;
        private Textbox abranch_dt,aaccount_dt,ainn_dt,abranch_ct,aaccount_ct,ainn_ct,apurpose_code,apurpose,aid;
        private Textbox fbranch_dt,faccount_dt,finn_dt,fbranch_ct,faccount_ct,finn_ct,fpurpose_code,fpurpose;
        private Paging qr_lead_setsPaging;
        private  int _pageSize = 15;
        private int _startPageNumber = 0;
        private int _totalSize = 0;
        private boolean _needsTotalSizeUpdate = true;
        private String alias;

        
        public Qr_lead_setsFilter filter;

        PagingListModel model = null;
        ListModelList lmodel =null;
        private AnnotateDataBinder binder;
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");


    private Qr_lead_sets current = new Qr_lead_sets();

        public Qr_lead_setsViewCtrl() {
                super('$', false, false);
        }
    /**
     *
     *
     */
    @Override
    public void doAfterCompose(Component comp) throws Exception {
    	filter = new Qr_lead_setsFilter();
            super.doAfterCompose(comp);
            // TODO Auto-generated method stub
                binder = new AnnotateDataBinder(comp);
                binder.bindBean("current", this.current);
                binder.loadAll();
                alias = (String) session.getAttribute("alias");
                System.out.println("payee_inn: "+payee_inn.getValue());
                filter.setPayee_id(payee_inn.getValue());
            //    System.out.println("Payee Id_SETS ==  " + filter.getPayee_id());
        String[] parameter = (String[]) param.get("ht");
        if (parameter!=null){
                _pageSize = Integer.parseInt( parameter[0])/36;
                dataGrid.setRows(Integer.parseInt( parameter[0])/36);
        }



            dataGrid.setItemRenderer(new ListitemRenderer(){
        public void render(Listitem row, Object data) throws Exception {
                    Qr_lead_sets pqr_lead_sets = (Qr_lead_sets) data;

                    row.appendChild(new Listcell(pqr_lead_sets.getPayee_id()));
                    row.appendChild(new Listcell(pqr_lead_sets.getBranch_dt()));
                    row.appendChild(new Listcell(pqr_lead_sets.getAccount_dt()));
                    row.appendChild(new Listcell(pqr_lead_sets.getBranch_ct()));
                    row.appendChild(new Listcell(pqr_lead_sets.getAccount_ct()));
                    row.appendChild(new Listcell(pqr_lead_sets.getInn_ct()));
                    row.appendChild(new Listcell(pqr_lead_sets.getPurpose_code()));
                    row.appendChild(new Listcell(pqr_lead_sets.getPurpose()));


        }});
         btn_back.setVisible(false);
        refreshModel(_startPageNumber);

    }

public void onPaging$qr_lead_setsPaging(ForwardEvent event){
    final PagingEvent pe = (PagingEvent) event.getOrigin();
    _startPageNumber = pe.getActivePage();
    refreshModel(_startPageNumber);
}


private void refreshModel(int activePage){
        qr_lead_setsPaging.setPageSize(_pageSize);
    model = new PagingListModel(activePage, _pageSize, filter, "");

    if(_needsTotalSizeUpdate) {
            _totalSize = model.getTotalSize();
            _needsTotalSizeUpdate = false;
    }

    qr_lead_setsPaging.setTotalSize(_totalSize);

    dataGrid.setModel((ListModel) model);
    if (model.getSize()>0){
    this.current =(Qr_lead_sets) model.getElementAt(0);
    sendSelEvt();
    }
}

public void onClick$btn_close() {
	qr_lead_setsmain.detach();
}


// Omitted...
public Qr_lead_sets getCurrent() {
    return current;
}

public void setCurrent(Qr_lead_sets current) {
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

public void onClick$btn_update() {

	if (dataGrid.getSelectedItem() != null) {
		grd.setVisible(false);
		frm.setVisible(true);
	//	frmgrd.setVisible(true);
		addgrd.setVisible(false);
		fgrd.setVisible(false);
		btn_back.setVisible(true);
		btn_update.setVisible(false);

	//	inn_dt.setValue(payee_inn.getValue());
	//	System.out.println(inn_dt.getValue());
	}
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


public void onClick$btn_add() throws SQLException {
        onDoubleClick$dataGrid$grd();
        frmgrd.setVisible(false);
        addgrd.setVisible(true);
        fgrd.setVisible(false);
        String br_dt = Qr_LeadService.getAccAndBr(payee_inn.getValue().toString(), "branch_dt");
        System.out.println("bt_dt :"+br_dt);
        String acc_dt = Qr_LeadService.getAccAndBr(payee_inn.getValue().toString(), "acc_dt");
        System.out.println("acc_dt :"+acc_dt);
        abranch_dt.setValue(""+br_dt);
        aaccount_dt.setValue(""+acc_dt);
        
       }

public void onClick$btn_search() {
        onDoubleClick$dataGrid$grd();
        frmgrd.setVisible(false);
        addgrd.setVisible(false);
        fgrd.setVisible(true);
}


public void onClick$btn_save() {
    try{
    	boolean ifTrue = true;
        if(addgrd.isVisible()){
        	boolean isEmpty = false;
			isEmpty = isEmpty(abranch_dt.getValue(), aaccount_dt.getValue(), abranch_ct.getValue(),aaccount_ct.getValue(),ainn_ct.getValue(),
					apurpose_code.getValue(),apurpose.getValue());
			if (!isEmpty) {
				alert("Не заполнены необходимые параметры");
				ifTrue = false;
				return;
			} else {
                Qr_LeadService.create(new Qr_lead_sets(
                payee_inn.getValue(),		
                abranch_dt.getValue(),
                aaccount_dt.getValue(),
                abranch_ct.getValue(),
                aaccount_ct.getValue(),
                ainn_ct.getValue(),
                apurpose_code.getValue(),
                apurpose.getValue()
                ));
            CheckNull.clearForm(addgrd);
            frmgrd.setVisible(true);
            addgrd.setVisible(false);
            fgrd.setVisible(false);
            ifTrue = true;
        }
          }else if(fgrd.isVisible()){
            filter = new Qr_lead_setsFilter();
              filter.setPayee_id(payee_inn.getValue());
              filter.setBranch_dt(fbranch_dt.getValue());
              filter.setAccount_dt(faccount_dt.getValue());
              filter.setBranch_ct(fbranch_ct.getValue());
              filter.setAccount_ct(faccount_ct.getValue());
              filter.setInn_ct(finn_ct.getValue());
              filter.setPurpose_code(fpurpose_code.getValue());
              filter.setPurpose(fpurpose.getValue());

        }else{
        	boolean isEmpty = false;
			isEmpty = isEmpty(branch_dt.getValue(), account_dt.getValue(),branch_ct.getValue(),account_ct.getValue(),
					inn_ct.getValue(),purpose_code.getValue(),purpose.getValue());
			if (!isEmpty) {
				alert("Не заполнены необходимые параметры");
				ifTrue = false;
				return;
			}
			  current.setPayee_id(payee_inn.getValue());
			  current.setBranch_dt(branch_dt.getValue());
              current.setAccount_dt(account_dt.getValue());
              current.setBranch_ct(branch_ct.getValue());
              current.setAccount_ct(account_ct.getValue());
              current.setInn_ct(inn_ct.getValue());
              current.setPurpose_code(purpose_code.getValue());
              current.setPurpose(purpose.getValue());
            Qr_LeadService.update(current);
            ifTrue = true;
        }
        if(ifTrue) {
    onClick$btn_back();
    refreshModel(_startPageNumber);
    SelectEvent evt = new SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
    Events.sendEvent(evt);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

}
public void onClick$btn_cancel() {
        if(fgrd.isVisible()){
                filter = new Qr_lead_setsFilter();
        }
    onClick$btn_back();
    frmgrd.setVisible(true);
    addgrd.setVisible(false);
    fgrd.setVisible(false);
    CheckNull.clearForm(addgrd);
    CheckNull.clearForm(fgrd);
    refreshModel(_startPageNumber);
}

public static boolean isEmpty(String... args) {
	boolean isEmpty = true;
	for (String str : args) {
		if (str == null || str.length() == 0) {
			isEmpty = false;
		}
	}
	return isEmpty;
}



}


