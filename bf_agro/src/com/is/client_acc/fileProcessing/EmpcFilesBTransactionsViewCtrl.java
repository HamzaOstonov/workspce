package com.is.client_acc.fileProcessing;

import java.text.SimpleDateFormat;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.event.PagingEvent;

public class EmpcFilesBTransactionsViewCtrl extends GenericForwardComposer{
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
    private Toolbar tb;
    private Textbox id,rec_num,empc_file_id,mtid,rec_centr,send_centr,iss_cmi,send_cmi,settl_cmi,acq_bank,acq_branch,member,clearing_group,merchant_accept,batch_nr,slip_nr,card,exp_date,e_date,e_time,tran_type,appr_code,appr_src,stan,ref_number,amount,cash_back,fee,currency,ccy_exp,sb_amount,sb_cshback,sb_fee,sbnk_ccy,sb_ccyexp,sb_cnvrate,sb_cnvdate,i_amount,i_cshback,i_fee,ibnk_ccy,i_ccyexp,i_cnvrate,i_cnvdate,abvr_name,city,country,point_code,mcc_code,terminal,batch_id,settl_nr,settl_date,acqref_nr,file_id,ms_number,file_date,source_algorithm,term_nr,ecmc_fee,tran_info,pr_amount,pr_cshback,pr_fee,prnk_ccy,pr_ccyexp,pr_cnvrate,pr_cnvdate,e_region,card_type,proc_class,card_seq_nr,msg_type,org_msg_type,proc_code,msg_category,merchant,moto_ind,susp_status,transact_row,authoriz_row,fld_043,fld_098,fld_102,fld_103,fld_104,fld_039,fld_sh6,batch_date,tr_fee,fld_040,fld_123_1,epi_42_48,fld_003,msc,account_nr,epi_42_48_full,other_code,fld_015,fld_095,audit_date,other_fee1,other_fee2,other_fee3,other_fee4,other_fee5,state_id;
    private Textbox aid,arec_num,aempc_file_id,amtid,arec_centr,asend_centr,aiss_cmi,asend_cmi,asettl_cmi,aacq_bank,aacq_branch,amember,aclearing_group,amerchant_accept,abatch_nr,aslip_nr,acard,aexp_date,ae_date,ae_time,atran_type,aappr_code,aappr_src,astan,aref_number,aamount,acash_back,afee,acurrency,accy_exp,asb_amount,asb_cshback,asb_fee,asbnk_ccy,asb_ccyexp,asb_cnvrate,asb_cnvdate,ai_amount,ai_cshback,ai_fee,aibnk_ccy,ai_ccyexp,ai_cnvrate,ai_cnvdate,aabvr_name,acity,acountry,apoint_code,amcc_code,aterminal,abatch_id,asettl_nr,asettl_date,aacqref_nr,afile_id,ams_number,afile_date,asource_algorithm,aterm_nr,aecmc_fee,atran_info,apr_amount,apr_cshback,apr_fee,aprnk_ccy,apr_ccyexp,apr_cnvrate,apr_cnvdate,ae_region,acard_type,aproc_class,acard_seq_nr,amsg_type,aorg_msg_type,aproc_code,amsg_category,amerchant,amoto_ind,asusp_status,atransact_row,aauthoriz_row,afld_043,afld_098,afld_102,afld_103,afld_104,afld_039,afld_sh6,abatch_date,atr_fee,afld_040,afld_123_1,aepi_42_48,afld_003,amsc,aaccount_nr,aepi_42_48_full,aother_code,afld_015,afld_095,aaudit_date,aother_fee1,aother_fee2,aother_fee3,aother_fee4,aother_fee5,astate_id ;
    private Textbox fid,frec_num,fempc_file_id,fmtid,frec_centr,fsend_centr,fiss_cmi,fsend_cmi,fsettl_cmi,facq_bank,facq_branch,fmember,fclearing_group,fmerchant_accept,fbatch_nr,fslip_nr,fcard,fexp_date,fe_date,fe_time,ftran_type,fappr_code,fappr_src,fstan,fref_number,famount,fcash_back,ffee,fcurrency,fccy_exp,fsb_amount,fsb_cshback,fsb_fee,fsbnk_ccy,fsb_ccyexp,fsb_cnvrate,fsb_cnvdate,fi_amount,fi_cshback,fi_fee,fibnk_ccy,fi_ccyexp,fi_cnvrate,fi_cnvdate,fabvr_name,fcity,fcountry,fpoint_code,fmcc_code,fterminal,fbatch_id,fsettl_nr,fsettl_date,facqref_nr,ffile_id,fms_number,ffile_date,fsource_algorithm,fterm_nr,fecmc_fee,ftran_info,fpr_amount,fpr_cshback,fpr_fee,fprnk_ccy,fpr_ccyexp,fpr_cnvrate,fpr_cnvdate,fe_region,fcard_type,fproc_class,fcard_seq_nr,fmsg_type,forg_msg_type,fproc_code,fmsg_category,fmerchant,fmoto_ind,fsusp_status,ftransact_row,fauthoriz_row,ffld_043,ffld_098,ffld_102,ffld_103,ffld_104,ffld_039,ffld_sh6,fbatch_date,ftr_fee,ffld_040,ffld_123_1,fepi_42_48,ffld_003,fmsc,faccount_nr,fepi_42_48_full,fother_code,ffld_015,ffld_095,faudit_date,fother_fee1,fother_fee2,fother_fee3,fother_fee4,fother_fee5,fstate_id ;
    private Paging empcfilesbtransactionsPaging;
    private  int _pageSize = 15;
    private int _startPageNumber = 0;
    private int _totalSize = 0;
    private boolean _needsTotalSizeUpdate = true;

    
    public EmpcFilesBTransactionsFilter filter;

    PagingListModel model = null;
    ListModelList lmodel =null;
    private AnnotateDataBinder binder;
    SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");


private EmpcFilesBTransactions current = new EmpcFilesBTransactions();

    public EmpcFilesBTransactionsViewCtrl() {
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
    if (parameter!=null){
            _pageSize = Integer.parseInt( parameter[0])/36;
            dataGrid.setRows(Integer.parseInt( parameter[0])/36);
    }



        this.dataGrid.setItemRenderer(new ListitemRenderer(){
        	
    @SuppressWarnings("unchecked")
    public void render(Listitem row, Object data) throws Exception {
                EmpcFilesBTransactions pEmpcFilesBTransactions = (EmpcFilesBTransactions) data;

                row.setValue(pEmpcFilesBTransactions);
                
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getId().toString()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getRec_num()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getEmpc_file_id()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getMtid()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getRec_centr()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getSend_centr()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getIss_cmi()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getSend_cmi()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getSettl_cmi()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getAcq_bank()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getAcq_branch()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getMember()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getClearing_group()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getMerchant_accept()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getBatch_nr()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getSlip_nr()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getCard()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getExp_date()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getE_date()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getE_time()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getTran_type()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getAppr_code()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getAppr_src()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getStan()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getRef_number()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getAmount()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getCash_back()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getFee()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getCurrency()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getCcy_exp()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getSb_amount()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getSb_cshback()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getSb_fee()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getSbnk_ccy()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getSb_ccyexp()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getSb_cnvrate()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getSb_cnvdate()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getI_amount()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getI_cshback()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getI_fee()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getIbnk_ccy()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getI_ccyexp()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getI_cnvrate()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getI_cnvdate()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getAbvr_name()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getCity()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getCountry()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getPoint_code()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getMcc_code()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getTerminal()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getBatch_id()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getSettl_nr()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getSettl_date()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getAcqref_nr()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getFile_id()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getMs_number()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getFile_date()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getSource_algorithm()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getTerm_nr()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getEcmc_fee()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getTran_info()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getPr_amount()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getPr_cshback()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getPr_fee()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getPrnk_ccy()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getPr_ccyexp()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getPr_cnvrate()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getPr_cnvdate()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getE_region()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getCard_type()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getProc_class()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getCard_seq_nr()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getMsg_type()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getOrg_msg_type()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getProc_code()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getMsg_category()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getMerchant()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getMoto_ind()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getSusp_status()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getTransact_row()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getAuthoriz_row()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getFld_043()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getFld_098()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getFld_102()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getFld_103()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getFld_104()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getFld_039()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getFld_sh6()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getBatch_date()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getTr_fee()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getFld_040()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getFld_123_1()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getEpi_42_48()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getFld_003()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getMsc()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getAccount_nr()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getEpi_42_48_full()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getOther_code()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getFld_015()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getFld_095()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getAudit_date()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getOther_fee1()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getOther_fee2()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getOther_fee3()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getOther_fee4()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getOther_fee5()));
                row.appendChild(new Listcell(pEmpcFilesBTransactions.getState_id()));


    }});

    refreshModel(_startPageNumber);

}

public void onPaging$empcfilesbtransactionsPaging(ForwardEvent event){
final PagingEvent pe = (PagingEvent) event.getOrigin();
_startPageNumber = pe.getActivePage();
refreshModel(_startPageNumber);
}


private void refreshModel(int activePage){
    empcfilesbtransactionsPaging.setPageSize(_pageSize);
model = new PagingListModel(activePage, _pageSize, filter, "");

if(_needsTotalSizeUpdate) {
        _totalSize = model.getTotalSize();
        _needsTotalSizeUpdate = false;
}

empcfilesbtransactionsPaging.setTotalSize(_totalSize);

dataGrid.setModel( model);
if (model.getSize()>0){
this.current =(EmpcFilesBTransactions) model.getElementAt(0);
sendSelEvt();
}
}



//Omitted...
public EmpcFilesBTransactions getCurrent() {
return current;
}

public void setCurrent(EmpcFilesBTransactions current) {
this.current = current;
}

public void onDoubleClick$dataGrid$grd() {
            grd.setVisible(false);
            frm.setVisible(true);
            frmgrd.setVisible(true);
            addgrd.setVisible(false);
            fgrd.setVisible(false);
            btn_back.setImage("/images/folder.png");
            //btn_back.setLabel(Labels.getLabel("grid"));
}




public void onClick$btn_back() {
    if (frm.isVisible()){
        frm.setVisible(false);
        grd.setVisible(true);
        btn_back.setImage("/images/file.png");
        //btn_back.setLabel(Labels.getLabel("back"));
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
//    SelectEvent evt = new SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
//    Events.sendEvent(evt);
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
            EmpcFilesBTransactionsService.create(new EmpcFilesBTransactions(
            
            Long.valueOf(aid.getValue()),
            arec_num.getValue(),
            aempc_file_id.getValue(),
            amtid.getValue(),
            arec_centr.getValue(),
            asend_centr.getValue(),
            aiss_cmi.getValue(),
            asend_cmi.getValue(),
            asettl_cmi.getValue(),
            aacq_bank.getValue(),
            aacq_branch.getValue(),
            amember.getValue(),
            aclearing_group.getValue(),
            amerchant_accept.getValue(),
            abatch_nr.getValue(),
            aslip_nr.getValue(),
            acard.getValue(),
            aexp_date.getValue(),
            ae_date.getValue(),
            ae_time.getValue(),
            atran_type.getValue(),
            aappr_code.getValue(),
            aappr_src.getValue(),
            astan.getValue(),
            aref_number.getValue(),
            aamount.getValue(),
            acash_back.getValue(),
            afee.getValue(),
            acurrency.getValue(),
            accy_exp.getValue(),
            asb_amount.getValue(),
            asb_cshback.getValue(),
            asb_fee.getValue(),
            asbnk_ccy.getValue(),
            asb_ccyexp.getValue(),
            asb_cnvrate.getValue(),
            asb_cnvdate.getValue(),
            ai_amount.getValue(),
            ai_cshback.getValue(),
            ai_fee.getValue(),
            aibnk_ccy.getValue(),
            ai_ccyexp.getValue(),
            ai_cnvrate.getValue(),
            ai_cnvdate.getValue(),
            aabvr_name.getValue(),
            acity.getValue(),
            acountry.getValue(),
            apoint_code.getValue(),
            amcc_code.getValue(),
            aterminal.getValue(),
            abatch_id.getValue(),
            asettl_nr.getValue(),
            asettl_date.getValue(),
            aacqref_nr.getValue(),
            afile_id.getValue(),
            ams_number.getValue(),
            afile_date.getValue(),
            asource_algorithm.getValue(),
            aterm_nr.getValue(),
            aecmc_fee.getValue(),
            atran_info.getValue(),
            apr_amount.getValue(),
            apr_cshback.getValue(),
            apr_fee.getValue(),
            aprnk_ccy.getValue(),
            apr_ccyexp.getValue(),
            apr_cnvrate.getValue(),
            apr_cnvdate.getValue(),
            ae_region.getValue(),
            acard_type.getValue(),
            aproc_class.getValue(),
            acard_seq_nr.getValue(),
            amsg_type.getValue(),
            aorg_msg_type.getValue(),
            aproc_code.getValue(),
            amsg_category.getValue(),
            amerchant.getValue(),
            amoto_ind.getValue(),
            asusp_status.getValue(),
            atransact_row.getValue(),
            aauthoriz_row.getValue(),
            afld_043.getValue(),
            afld_098.getValue(),
            afld_102.getValue(),
            afld_103.getValue(),
            afld_104.getValue(),
            afld_039.getValue(),
            afld_sh6.getValue(),
            abatch_date.getValue(),
            atr_fee.getValue(),
            afld_040.getValue(),
            afld_123_1.getValue(),
            aepi_42_48.getValue(),
            afld_003.getValue(),
            amsc.getValue(),
            aaccount_nr.getValue(),
            aepi_42_48_full.getValue(),
            aother_code.getValue(),
            afld_015.getValue(),
            afld_095.getValue(),
            aaudit_date.getValue(),
            aother_fee1.getValue(),
            aother_fee2.getValue(),
            aother_fee3.getValue(),
            aother_fee4.getValue(),
            aother_fee5.getValue(),
            astate_id.getValue()
            ));
       // CheckNull.clearForm(addgrd);
        frmgrd.setVisible(true);
        addgrd.setVisible(false);
        fgrd.setVisible(false);
    }else if(fgrd.isVisible()){
        filter = new EmpcFilesBTransactionsFilter();
        
          filter.setId(Long.valueOf(fid.getValue()));
          filter.setRec_num(frec_num.getValue());
          filter.setEmpc_file_id(fempc_file_id.getValue());
          filter.setMtid(fmtid.getValue());
          filter.setRec_centr(frec_centr.getValue());
          filter.setSend_centr(fsend_centr.getValue());
          filter.setIss_cmi(fiss_cmi.getValue());
          filter.setSend_cmi(fsend_cmi.getValue());
          filter.setSettl_cmi(fsettl_cmi.getValue());
          filter.setAcq_bank(facq_bank.getValue());
          filter.setAcq_branch(facq_branch.getValue());
          filter.setMember(fmember.getValue());
          filter.setClearing_group(fclearing_group.getValue());
          filter.setMerchant_accept(fmerchant_accept.getValue());
          filter.setBatch_nr(fbatch_nr.getValue());
          filter.setSlip_nr(fslip_nr.getValue());
          filter.setCard(fcard.getValue());
          filter.setExp_date(fexp_date.getValue());
          filter.setE_date(fe_date.getValue());
          filter.setE_time(fe_time.getValue());
          filter.setTran_type(ftran_type.getValue());
          filter.setAppr_code(fappr_code.getValue());
          filter.setAppr_src(fappr_src.getValue());
          filter.setStan(fstan.getValue());
          filter.setRef_number(fref_number.getValue());
          filter.setAmount(famount.getValue());
          filter.setCash_back(fcash_back.getValue());
          filter.setFee(ffee.getValue());
          filter.setCurrency(fcurrency.getValue());
          filter.setCcy_exp(fccy_exp.getValue());
          filter.setSb_amount(fsb_amount.getValue());
          filter.setSb_cshback(fsb_cshback.getValue());
          filter.setSb_fee(fsb_fee.getValue());
          filter.setSbnk_ccy(fsbnk_ccy.getValue());
          filter.setSb_ccyexp(fsb_ccyexp.getValue());
          filter.setSb_cnvrate(fsb_cnvrate.getValue());
          filter.setSb_cnvdate(fsb_cnvdate.getValue());
          filter.setI_amount(fi_amount.getValue());
          filter.setI_cshback(fi_cshback.getValue());
          filter.setI_fee(fi_fee.getValue());
          filter.setIbnk_ccy(fibnk_ccy.getValue());
          filter.setI_ccyexp(fi_ccyexp.getValue());
          filter.setI_cnvrate(fi_cnvrate.getValue());
          filter.setI_cnvdate(fi_cnvdate.getValue());
          filter.setAbvr_name(fabvr_name.getValue());
          filter.setCity(fcity.getValue());
          filter.setCountry(fcountry.getValue());
          filter.setPoint_code(fpoint_code.getValue());
          filter.setMcc_code(fmcc_code.getValue());
          filter.setTerminal(fterminal.getValue());
          filter.setBatch_id(fbatch_id.getValue());
          filter.setSettl_nr(fsettl_nr.getValue());
          filter.setSettl_date(fsettl_date.getValue());
          filter.setAcqref_nr(facqref_nr.getValue());
          filter.setFile_id(ffile_id.getValue());
          filter.setMs_number(fms_number.getValue());
          filter.setFile_date(ffile_date.getValue());
          filter.setSource_algorithm(fsource_algorithm.getValue());
          filter.setTerm_nr(fterm_nr.getValue());
          filter.setEcmc_fee(fecmc_fee.getValue());
          filter.setTran_info(ftran_info.getValue());
          filter.setPr_amount(fpr_amount.getValue());
          filter.setPr_cshback(fpr_cshback.getValue());
          filter.setPr_fee(fpr_fee.getValue());
          filter.setPrnk_ccy(fprnk_ccy.getValue());
          filter.setPr_ccyexp(fpr_ccyexp.getValue());
          filter.setPr_cnvrate(fpr_cnvrate.getValue());
          filter.setPr_cnvdate(fpr_cnvdate.getValue());
          filter.setE_region(fe_region.getValue());
          filter.setCard_type(fcard_type.getValue());
          filter.setProc_class(fproc_class.getValue());
          filter.setCard_seq_nr(fcard_seq_nr.getValue());
          filter.setMsg_type(fmsg_type.getValue());
          filter.setOrg_msg_type(forg_msg_type.getValue());
          filter.setProc_code(fproc_code.getValue());
          filter.setMsg_category(fmsg_category.getValue());
          filter.setMerchant(fmerchant.getValue());
          filter.setMoto_ind(fmoto_ind.getValue());
          filter.setSusp_status(fsusp_status.getValue());
          filter.setTransact_row(ftransact_row.getValue());
          filter.setAuthoriz_row(fauthoriz_row.getValue());
          filter.setFld_043(ffld_043.getValue());
          filter.setFld_098(ffld_098.getValue());
          filter.setFld_102(ffld_102.getValue());
          filter.setFld_103(ffld_103.getValue());
          filter.setFld_104(ffld_104.getValue());
          filter.setFld_039(ffld_039.getValue());
          filter.setFld_sh6(ffld_sh6.getValue());
          filter.setBatch_date(fbatch_date.getValue());
          filter.setTr_fee(ftr_fee.getValue());
          filter.setFld_040(ffld_040.getValue());
          filter.setFld_123_1(ffld_123_1.getValue());
          filter.setEpi_42_48(fepi_42_48.getValue());
          filter.setFld_003(ffld_003.getValue());
          filter.setMsc(fmsc.getValue());
          filter.setAccount_nr(faccount_nr.getValue());
          filter.setEpi_42_48_full(fepi_42_48_full.getValue());
          filter.setOther_code(fother_code.getValue());
          filter.setFld_015(ffld_015.getValue());
          filter.setFld_095(ffld_095.getValue());
          filter.setAudit_date(faudit_date.getValue());
          filter.setOther_fee1(fother_fee1.getValue());
          filter.setOther_fee2(fother_fee2.getValue());
          filter.setOther_fee3(fother_fee3.getValue());
          filter.setOther_fee4(fother_fee4.getValue());
          filter.setOther_fee5(fother_fee5.getValue());
          filter.setState_id(fstate_id.getValue());

    }else{
        
          current.setId(Long.valueOf(id.getValue()));
          current.setRec_num(rec_num.getValue());
          current.setEmpc_file_id(empc_file_id.getValue());
          current.setMtid(mtid.getValue());
          current.setRec_centr(rec_centr.getValue());
          current.setSend_centr(send_centr.getValue());
          current.setIss_cmi(iss_cmi.getValue());
          current.setSend_cmi(send_cmi.getValue());
          current.setSettl_cmi(settl_cmi.getValue());
          current.setAcq_bank(acq_bank.getValue());
          current.setAcq_branch(acq_branch.getValue());
          current.setMember(member.getValue());
          current.setClearing_group(clearing_group.getValue());
          current.setMerchant_accept(merchant_accept.getValue());
          current.setBatch_nr(batch_nr.getValue());
          current.setSlip_nr(slip_nr.getValue());
          current.setCard(card.getValue());
          current.setExp_date(exp_date.getValue());
          current.setE_date(e_date.getValue());
          current.setE_time(e_time.getValue());
          current.setTran_type(tran_type.getValue());
          current.setAppr_code(appr_code.getValue());
          current.setAppr_src(appr_src.getValue());
          current.setStan(stan.getValue());
          current.setRef_number(ref_number.getValue());
          current.setAmount(amount.getValue());
          current.setCash_back(cash_back.getValue());
          current.setFee(fee.getValue());
          current.setCurrency(currency.getValue());
          current.setCcy_exp(ccy_exp.getValue());
          current.setSb_amount(sb_amount.getValue());
          current.setSb_cshback(sb_cshback.getValue());
          current.setSb_fee(sb_fee.getValue());
          current.setSbnk_ccy(sbnk_ccy.getValue());
          current.setSb_ccyexp(sb_ccyexp.getValue());
          current.setSb_cnvrate(sb_cnvrate.getValue());
          current.setSb_cnvdate(sb_cnvdate.getValue());
          current.setI_amount(i_amount.getValue());
          current.setI_cshback(i_cshback.getValue());
          current.setI_fee(i_fee.getValue());
          current.setIbnk_ccy(ibnk_ccy.getValue());
          current.setI_ccyexp(i_ccyexp.getValue());
          current.setI_cnvrate(i_cnvrate.getValue());
          current.setI_cnvdate(i_cnvdate.getValue());
          current.setAbvr_name(abvr_name.getValue());
          current.setCity(city.getValue());
          current.setCountry(country.getValue());
          current.setPoint_code(point_code.getValue());
          current.setMcc_code(mcc_code.getValue());
          current.setTerminal(terminal.getValue());
          current.setBatch_id(batch_id.getValue());
          current.setSettl_nr(settl_nr.getValue());
          current.setSettl_date(settl_date.getValue());
          current.setAcqref_nr(acqref_nr.getValue());
          current.setFile_id(file_id.getValue());
          current.setMs_number(ms_number.getValue());
          current.setFile_date(file_date.getValue());
          current.setSource_algorithm(source_algorithm.getValue());
          current.setTerm_nr(term_nr.getValue());
          current.setEcmc_fee(ecmc_fee.getValue());
          current.setTran_info(tran_info.getValue());
          current.setPr_amount(pr_amount.getValue());
          current.setPr_cshback(pr_cshback.getValue());
          current.setPr_fee(pr_fee.getValue());
          current.setPrnk_ccy(prnk_ccy.getValue());
          current.setPr_ccyexp(pr_ccyexp.getValue());
          current.setPr_cnvrate(pr_cnvrate.getValue());
          current.setPr_cnvdate(pr_cnvdate.getValue());
          current.setE_region(e_region.getValue());
          current.setCard_type(card_type.getValue());
          current.setProc_class(proc_class.getValue());
          current.setCard_seq_nr(card_seq_nr.getValue());
          current.setMsg_type(msg_type.getValue());
          current.setOrg_msg_type(org_msg_type.getValue());
          current.setProc_code(proc_code.getValue());
          current.setMsg_category(msg_category.getValue());
          current.setMerchant(merchant.getValue());
          current.setMoto_ind(moto_ind.getValue());
          current.setSusp_status(susp_status.getValue());
          current.setTransact_row(transact_row.getValue());
          current.setAuthoriz_row(authoriz_row.getValue());
          current.setFld_043(fld_043.getValue());
          current.setFld_098(fld_098.getValue());
          current.setFld_102(fld_102.getValue());
          current.setFld_103(fld_103.getValue());
          current.setFld_104(fld_104.getValue());
          current.setFld_039(fld_039.getValue());
          current.setFld_sh6(fld_sh6.getValue());
          current.setBatch_date(batch_date.getValue());
          current.setTr_fee(tr_fee.getValue());
          current.setFld_040(fld_040.getValue());
          current.setFld_123_1(fld_123_1.getValue());
          current.setEpi_42_48(epi_42_48.getValue());
          current.setFld_003(fld_003.getValue());
          current.setMsc(msc.getValue());
          current.setAccount_nr(account_nr.getValue());
          current.setEpi_42_48_full(epi_42_48_full.getValue());
          current.setOther_code(other_code.getValue());
          current.setFld_015(fld_015.getValue());
          current.setFld_095(fld_095.getValue());
          current.setAudit_date(audit_date.getValue());
          current.setOther_fee1(other_fee1.getValue());
          current.setOther_fee2(other_fee2.getValue());
          current.setOther_fee3(other_fee3.getValue());
          current.setOther_fee4(other_fee4.getValue());
          current.setOther_fee5(other_fee5.getValue());
          current.setState_id(state_id.getValue());
        EmpcFilesBTransactionsService.update(current);
    }
onClick$btn_back();
refreshModel(_startPageNumber);
//SelectEvent evt = new SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
//Events.sendEvent(evt);
} catch (Exception e) {
    e.printStackTrace();
}

}
public void onClick$btn_cancel() {
    if(fgrd.isVisible()){
            filter = new EmpcFilesBTransactionsFilter();
    }
onClick$btn_back();
frmgrd.setVisible(true);
addgrd.setVisible(false);
fgrd.setVisible(false);
//CheckNull.clearForm(addgrd);
//CheckNull.clearForm(fgrd);
refreshModel(_startPageNumber);
}



}


