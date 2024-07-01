package com.is.doc;

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
import org.zkoss.zul.Hbox;
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


import com.is.doc.PagingListModel;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.RefData;
import com.is.utils.RefRenderer;

@SuppressWarnings("serial")
public class DocViewCtrl extends GenericForwardComposer{
        private Div frm;
        private Paging contactPaging;
        private Div grd;
        private Listbox dataGrid;
        private Grid addgrd,frmgrdl,frmgrdr,fgrd;
        private Toolbarbutton btn_last;
        private Toolbarbutton btn_next;
        private Toolbarbutton btn_prev;
        private Toolbarbutton btn_first;
        private Toolbarbutton btn_add;
        private Toolbarbutton btn_search;
        private Toolbarbutton btn_back;
        private Toolbar tb;
        private Textbox id,branch,doc_num,d_date,bank_cl,acc_cl,name_cl,bank_co,acc_co,name_co,purpose,summa,currency,type_doc,s_deal_id,v_date,pdc,cash_code,state,parentgroupid,parentid,child_id,kod_err,file_name,err_general,emp_id,id_transh,id_transh_purp,val_date;
        private Textbox aid,abranch,adoc_num,ad_date,abank_cl,aacc_cl,aname_cl,abank_co,aacc_co,aname_co,apurpose,asumma,acurrency,atype_doc,as_deal_id,av_date,apdc,acash_code,astate,aparentgroupid,aparentid,achild_id,akod_err,afile_name,aerr_general,aemp_id,aid_transh,aid_transh_purp,aval_date ;
        private Textbox fid,fbranch,fdoc_num,fd_date,fbank_cl,facc_cl,fname_cl,fbank_co,facc_co,fname_co,fpurpose,fsumma,fcurrency,ftype_doc,fs_deal_id,fv_date,fpdc,fcash_code,fstate,fparentgroupid,fparentid,fchild_id,fkod_err,ffile_name,ferr_general,femp_id,fid_transh,fid_transh_purp,fval_date ;
        private Paging docPaging;
        private  int _pageSize = 15;
        private int _startPageNumber = 0;
        private int _totalSize = 0;
        private boolean _needsTotalSizeUpdate = true;
        private Hbox frmgrd;
        //public Doc current;
        public DocFilter filter;

        PagingListModel model = null;
        ListModelList lmodel =null;
        private AnnotateDataBinder binder;
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        private String alias;


    private Doc current = new Doc();

        public DocViewCtrl() {
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
        if (parameter!=null){
                _pageSize = Integer.parseInt( parameter[0])/36;
                dataGrid.setRows(Integer.parseInt( parameter[0])/36);
        }



            dataGrid.setItemRenderer(new ListitemRenderer(){
        @SuppressWarnings("unchecked")
        public void render(Listitem row, Object data) throws Exception {
                    Doc pDoc = (Doc) data;

                    row.setValue(pDoc);
                    
                   // row.appendChild(new Listcell(pDoc.getId()));
                    row.appendChild(new Listcell(pDoc.getBranch()));
                    row.appendChild(new Listcell(pDoc.getDoc_num()));
                    row.appendChild(new Listcell(df.format(pDoc.getD_date())));
                    row.appendChild(new Listcell(pDoc.getBank_cl()));
                    row.appendChild(new Listcell(pDoc.getAcc_cl()));
                   // row.appendChild(new Listcell(pDoc.getName_cl()));
                    row.appendChild(new Listcell(pDoc.getBank_co()));
                    row.appendChild(new Listcell(pDoc.getAcc_co()));
                    //row.appendChild(new Listcell(pDoc.getName_co()));
                    //row.appendChild(new Listcell(pDoc.getPurpose()));
                    row.appendChild(new Listcell(pDoc.getSumma()/100+""));
                    //row.appendChild(new Listcell(pDoc.getCurrency()));
                    //row.appendChild(new Listcell(pDoc.getType_doc()));
                    /*
                    row.appendChild(new Listcell(pDoc.getS_deal_id()));
                    row.appendChild(new Listcell(pDoc.getV_date()));
                    row.appendChild(new Listcell(pDoc.getPdc()));
                    row.appendChild(new Listcell(pDoc.getCash_code()));
                    row.appendChild(new Listcell(pDoc.getState()));
                    row.appendChild(new Listcell(pDoc.getParentGroupId()));
                    row.appendChild(new Listcell(pDoc.getParentId()));
                    row.appendChild(new Listcell(pDoc.getChild_id()));
                    row.appendChild(new Listcell(pDoc.getKod_err()));
                    row.appendChild(new Listcell(pDoc.getFile_name()));
                    row.appendChild(new Listcell(pDoc.getErr_general()));
                    row.appendChild(new Listcell(pDoc.getEmp_id()));
                    row.appendChild(new Listcell(pDoc.getId_transh()));
                    row.appendChild(new Listcell(pDoc.getId_transh_purp()));
                    row.appendChild(new Listcell(pDoc.getVal_date()));
*/

        }});

        refreshModel(_startPageNumber);

    }

public void onPaging$docPaging(ForwardEvent event){
    final PagingEvent pe = (PagingEvent) event.getOrigin();
    _startPageNumber = pe.getActivePage();
    refreshModel(_startPageNumber);
}


private void refreshModel(int activePage){
        docPaging.setPageSize(_pageSize);
    model = new PagingListModel(activePage, _pageSize, filter,alias);

    if(_needsTotalSizeUpdate) {
            _totalSize = model.getTotalSize(filter,alias);
           // _needsTotalSizeUpdate = false;
    }

    docPaging.setTotalSize(_totalSize);

    dataGrid.setModel((ListModel) model);
    if (model.getSize()>0){
    this.current =(Doc) model.getElementAt(0);
    sendSelEvt();
    }
}



// Omitted...
public Doc getCurrent() {
    return current;
}

public void setCurrent(Doc current) {
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
    	/*
    	if(addgrd.isVisible()){

        	DocService.createDoc(new Doc(
                
                aid.getValue(),
                abranch.getValue(),
                adoc_num.getValue(),
                ad_date.getValue(),
                abank_cl.getValue(),
                aacc_cl.getValue(),
                aname_cl.getValue(),
                abank_co.getValue(),
                aacc_co.getValue(),
                aname_co.getValue(),
                apurpose.getValue(),
                asumma.getValue(),
                acurrency.getValue(),
                atype_doc.getValue(),
                as_deal_id.getValue(),
                av_date.getValue(),
                apdc.getValue(),
                acash_code.getValue(),
                astate.getValue(),
                aparentgroupid.getValue(),
                aparentid.getValue(),
                achild_id.getValue(),
                akod_err.getValue(),
                afile_name.getValue(),
                aerr_general.getValue(),
                aemp_id.getValue(),
                aid_transh.getValue(),
                aid_transh_purp.getValue(),
                aval_date.getValue(),
                ));
            CheckNull.clearForm(addgrd);
            frmgrd.setVisible(true);
            addgrd.setVisible(false);
            fgrd.setVisible(false);
            
        }else if(fgrd.isVisible()){
            filter = new DocFilter();
            
              filter.setId(fid.getValue());
              filter.setBranch(fbranch.getValue());
              filter.setDoc_num(fdoc_num.getValue());
              filter.setD_date(fd_date.getValue());
              filter.setBank_cl(fbank_cl.getValue());
              filter.setAcc_cl(facc_cl.getValue());
              filter.setName_cl(fname_cl.getValue());
              filter.setBank_co(fbank_co.getValue());
              filter.setAcc_co(facc_co.getValue());
              filter.setName_co(fname_co.getValue());
              filter.setPurpose(fpurpose.getValue());
              filter.setSumma(fsumma.getValue());
              filter.setCurrency(fcurrency.getValue());
              filter.setType_doc(ftype_doc.getValue());
              filter.setS_deal_id(fs_deal_id.getValue());
              filter.setV_date(fv_date.getValue());
              filter.setPdc(fpdc.getValue());
              filter.setCash_code(fcash_code.getValue());
              filter.setState(fstate.getValue());
              filter.setParentGroupId(fparentgroupid.getValue());
              filter.setParentId(fparentid.getValue());
              filter.setChild_id(fchild_id.getValue());
              filter.setKod_err(fkod_err.getValue());
              filter.setFile_name(ffile_name.getValue());
              filter.setErr_general(ferr_general.getValue());
              filter.setEmp_id(femp_id.getValue());
              filter.setId_transh(fid_transh.getValue());
              filter.setId_transh_purp(fid_transh_purp.getValue());
              filter.setVal_date(fval_date.getValue());

        }else{
            
              current.setId(id.getValue());
              current.setBranch(branch.getValue());
              current.setDoc_num(doc_num.getValue());
              current.setD_date(d_date.getValue());
              current.setBank_cl(bank_cl.getValue());
              current.setAcc_cl(acc_cl.getValue());
              current.setName_cl(name_cl.getValue());
              current.setBank_co(bank_co.getValue());
              current.setAcc_co(acc_co.getValue());
              current.setName_co(name_co.getValue());
              current.setPurpose(purpose.getValue());
              current.setSumma(summa.getValue());
              current.setCurrency(currency.getValue());
              current.setType_doc(type_doc.getValue());
              current.setS_deal_id(s_deal_id.getValue());
              current.setV_date(v_date.getValue());
              current.setPdc(pdc.getValue());
              current.setCash_code(cash_code.getValue());
              current.setState(state.getValue());
              current.setParentGroupId(parentgroupid.getValue());
              current.setParentId(parentid.getValue());
              current.setChild_id(child_id.getValue());
              current.setKod_err(kod_err.getValue());
              current.setFile_name(file_name.getValue());
              current.setErr_general(err_general.getValue());
              current.setEmp_id(emp_id.getValue());
              current.setId_transh(id_transh.getValue());
              current.setId_transh_purp(id_transh_purp.getValue());
              current.setVal_date(val_date.getValue());
            DocService.updateDoc(current);
        }
    onClick$btn_back();
    refreshModel(_startPageNumber);
    SelectEvent evt = new SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
    Events.sendEvent(evt);
    */
    } catch (Exception e) {
        e.printStackTrace();
    }

}
public void onClick$btn_cancel() {
        if(fgrd.isVisible()){
                filter = new DocFilter();
        }
    onClick$btn_back();
    frmgrd.setVisible(true);
    addgrd.setVisible(false);
    fgrd.setVisible(false);
    CheckNull.clearForm(addgrd);
    CheckNull.clearForm(fgrd);
    refreshModel(_startPageNumber);
}



}
