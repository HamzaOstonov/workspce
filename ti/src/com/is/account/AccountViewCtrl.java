package com.is.account;

import java.sql.Date;
import java.text.SimpleDateFormat;


import com.is.utils.CheckNull;

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
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;
import org.zkoss.zul.event.PagingEvent;


import com.is.account.PagingListModel;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.RefData;
import com.is.utils.RefRenderer;

@SuppressWarnings("serial")
public class AccountViewCtrl extends GenericForwardComposer{
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
        private Textbox branch,id,acc_bal,currency,client,id_order,name,sgn,bal,sign_registr,s_in,s_out,dt,ct,s_in_tmp,s_out_tmp,dt_tmp,ct_tmp,l_date,date_open,date_close,acc_group_id,state;
        private Textbox abranch,aid,aacc_bal,acurrency,aclient,aid_order,aname,asgn,abal,asign_registr,as_in,as_out,adt,act,as_in_tmp,as_out_tmp,adt_tmp,act_tmp,al_date,adate_open,adate_close,aacc_group_id,astate ;
        private Textbox fbranch,fid,facc_bal,fcurrency,fclient,fid_order,fname,fsgn,fbal,fsign_registr,fs_in,fs_out,fdt,fct,fs_in_tmp,fs_out_tmp,fdt_tmp,fct_tmp,fl_date,fdate_open,fdate_close,facc_group_id,fstate ;
        private Paging accountPaging;
        private  int _pageSize = 15;
        private int _startPageNumber = 0;
        private int _totalSize = 0;
        private boolean _needsTotalSizeUpdate = true;

        
        public AccountFilter filter = null;

        PagingListModel model = null;
        ListModelList lmodel =null;
        private AnnotateDataBinder binder;
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        private String alias, branch1;


    public Account current = new Account();

        public AccountViewCtrl() {
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
        alias = (String) session.getAttribute("alias");
        branch1 = (String) session.getAttribute("branch");
        if (parameter!=null){
                _pageSize = Integer.parseInt( parameter[0])/36;
                dataGrid.setRows(Integer.parseInt( parameter[0])/36);
        }

        filter = new AccountFilter();

            dataGrid.setItemRenderer(new ListitemRenderer(){
        @SuppressWarnings("unchecked")
        public void render(Listitem row, Object data) throws Exception {
                    Account pAccount = (Account) data;

                    row.setValue(pAccount);
                    
                    row.appendChild(new Listcell(pAccount.getBranch()));
                    row.appendChild(new Listcell(pAccount.getId()));
                    /*
                    row.appendChild(new Listcell(pAccount.getAcc_bal()));
                    row.appendChild(new Listcell(pAccount.getCurrency()));
                    row.appendChild(new Listcell(pAccount.getClient()));
                    row.appendChild(new Listcell(pAccount.getId_order()));
                    */
                    row.appendChild(new Listcell(pAccount.getName()));
                    /*
                    row.appendChild(new Listcell(pAccount.getSgn()));
                    row.appendChild(new Listcell(pAccount.getBal()));
                    row.appendChild(new Listcell(pAccount.getSign_registr()));
                    row.appendChild(new Listcell(pAccount.getS_in()));
                    */
                    row.appendChild(new Listcell(pAccount.getS_out()/100+""));
                    /*
                    row.appendChild(new Listcell(pAccount.getDt()));
                    row.appendChild(new Listcell(pAccount.getCt()));
                    row.appendChild(new Listcell(pAccount.getS_in_tmp()));
                    row.appendChild(new Listcell(pAccount.getS_out_tmp()));
                    row.appendChild(new Listcell(pAccount.getDt_tmp()));
                    row.appendChild(new Listcell(pAccount.getCt_tmp()));
                    row.appendChild(new Listcell(pAccount.getL_date()));
                    row.appendChild(new Listcell(pAccount.getDate_open()));
                    row.appendChild(new Listcell(pAccount.getDate_close()));
                    row.appendChild(new Listcell(pAccount.getAcc_group_id()));
                    */
                    row.appendChild(new Listcell(pAccount.getState()+""));


        }});

        refreshModel(_startPageNumber);

    }

public void onPaging$accountPaging(ForwardEvent event){
    final PagingEvent pe = (PagingEvent) event.getOrigin();
    _startPageNumber = pe.getActivePage();
    refreshModel(_startPageNumber);
}


private void refreshModel(int activePage){
        accountPaging.setPageSize(_pageSize);
    model = new PagingListModel(activePage, _pageSize,filter,alias);

    if(_needsTotalSizeUpdate) {
            _totalSize = model.getTotalSize(filter, alias);
           // _needsTotalSizeUpdate = false;
    }

    accountPaging.setTotalSize(_totalSize);

    dataGrid.setModel((ListModel) model);
    if (model.getSize()>0){
    this.current =(Account) model.getElementAt(0);
    sendSelEvt();
    }
}



// Omitted...
public Account getCurrent() {
    return current;
}

public void setCurrent(Account current) {
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
    try{/*
        if(addgrd.isVisible()){
                AccountService.createAccount(new Account(
                
                abranch.getValue(),
                aid.getValue(),
                aacc_bal.getValue(),
                acurrency.getValue(),
                aclient.getValue(),
                aid_order.getValue(),
                aname.getValue(),
                asgn.getValue(),
                abal.getValue(),
                asign_registr.getValue(),
                as_in.getValue(),
                as_out.getValue(),
                adt.getValue(),
                act.getValue(),
                as_in_tmp.getValue(),
                as_out_tmp.getValue(),
                adt_tmp.getValue(),
                act_tmp.getValue(),
                al_date.getValue(),
                adate_open.getValue(),
                adate_close.getValue(),
                aacc_group_id.getValue(),
                astate.getValue(),
                ));
            CheckNull.clearForm(addgrd);
            frmgrd.setVisible(true);
            addgrd.setVisible(false);
            fgrd.setVisible(false);
        }else if(fgrd.isVisible()){
        */
            filter = new AccountFilter();
            
              filter.setBranch(fbranch.getValue());
              filter.setId(fid.getValue());
              filter.setAcc_bal(facc_bal.getValue());
              filter.setCurrency(fcurrency.getValue());
              filter.setClient(fclient.getValue());
              filter.setId_order(fid_order.getValue());
              filter.setName(fname.getValue());
              /*
              filter.setSgn(fsgn.getValue());
              filter.setBal(fbal.getValue());
             
              filter.setSign_registr(fsign_registr.getValue());
              filter.setS_in(fs_in.getValue());
              filter.setS_out(fs_out.getValue());
              filter.setDt(fdt.getValue());
              filter.setCt(fct.getValue());
              filter.setS_in_tmp(fs_in_tmp.getValue());
              filter.setS_out_tmp(fs_out_tmp.getValue());
              filter.setDt_tmp(fdt_tmp.getValue());
              filter.setCt_tmp(fct_tmp.getValue());
              filter.setL_date(fl_date.getValue());
              filter.setDate_open(fdate_open.getValue());
              filter.setDate_close(fdate_close.getValue());
              filter.setAcc_group_id(facc_group_id.getValue());
              filter.setState(fstate.getValue());
              */
/*
        }else{
            
              current.setBranch(branch.getValue());
              current.setId(id.getValue());
              current.setAcc_bal(acc_bal.getValue());
              current.setCurrency(currency.getValue());
              current.setClient(client.getValue());
              current.setId_order(id_order.getValue());
              current.setName(name.getValue());
              current.setSgn(sgn.getValue());
              current.setBal(bal.getValue());
              current.setSign_registr(sign_registr.getValue());
              current.setS_in(s_in.getValue());
              current.setS_out(s_out.getValue());
              current.setDt(dt.getValue());
              current.setCt(ct.getValue());
              current.setS_in_tmp(s_in_tmp.getValue());
              current.setS_out_tmp(s_out_tmp.getValue());
              current.setDt_tmp(dt_tmp.getValue());
              current.setCt_tmp(ct_tmp.getValue());
              current.setL_date(l_date.getValue());
              current.setDate_open(date_open.getValue());
              current.setDate_close(date_close.getValue());
              current.setAcc_group_id(acc_group_id.getValue());
              current.setState(state.getValue());
            AccountService.updateAccount(current);
        }
        */
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
                filter = new AccountFilter();
        }
    onClick$btn_back();
    frmgrd.setVisible(true);
    addgrd.setVisible(false);
    fgrd.setVisible(false);
    CheckNull.clearForm(addgrd);
    CheckNull.clearForm(fgrd);
    refreshModel(_startPageNumber);
}

public void onClick$btn_blk() {
	String res =
	AccountService.doAction(session.getAttribute("un").toString(), session.getAttribute("pwd").toString(), current.getBranch(), current.getId(), 4, alias,  branch1.compareTo("00444")==0);
	if(!res.equals("")){
		alert(res);
	}else
	refreshModel(_startPageNumber);
}
public void onClick$btn_ublk() {
	String res =
	AccountService.doAction(session.getAttribute("un").toString(), session.getAttribute("pwd").toString(), current.getBranch(), current.getId(), 5, alias, branch1.compareTo("00444")==0);
	if(!res.equals("")){
		alert(res);
	}else
	refreshModel(_startPageNumber);
}
}

