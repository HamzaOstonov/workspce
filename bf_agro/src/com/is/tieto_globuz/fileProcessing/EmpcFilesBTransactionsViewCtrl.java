// 
// Decompiled by Procyon v0.5.36
// 

package com.is.tieto_globuz.fileProcessing;

import org.zkoss.zul.ListModel;
import org.zkoss.zul.event.PagingEvent;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zk.ui.Component;
import java.text.SimpleDateFormat;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Div;
import org.zkoss.zk.ui.util.GenericForwardComposer;

public class EmpcFilesBTransactionsViewCtrl extends GenericForwardComposer
{
    private Div frm;
    private Listbox dataGrid;
    private Paging contactPaging;
    private Div grd;
    private Grid addgrd;
    private Grid frmgrd;
    private Grid fgrd;
    private Toolbarbutton btn_last;
    private Toolbarbutton btn_next;
    private Toolbarbutton btn_prev;
    private Toolbarbutton btn_first;
    private Toolbarbutton btn_add;
    private Toolbarbutton btn_search;
    private Toolbarbutton btn_back;
    private Toolbar tb;
    private Textbox id;
    private Textbox rec_num;
    private Textbox empc_file_id;
    private Textbox mtid;
    private Textbox rec_centr;
    private Textbox send_centr;
    private Textbox iss_cmi;
    private Textbox send_cmi;
    private Textbox settl_cmi;
    private Textbox acq_bank;
    private Textbox acq_branch;
    private Textbox member;
    private Textbox clearing_group;
    private Textbox merchant_accept;
    private Textbox batch_nr;
    private Textbox slip_nr;
    private Textbox card;
    private Textbox exp_date;
    private Textbox e_date;
    private Textbox e_time;
    private Textbox tran_type;
    private Textbox appr_code;
    private Textbox appr_src;
    private Textbox stan;
    private Textbox ref_number;
    private Textbox amount;
    private Textbox cash_back;
    private Textbox fee;
    private Textbox currency;
    private Textbox ccy_exp;
    private Textbox sb_amount;
    private Textbox sb_cshback;
    private Textbox sb_fee;
    private Textbox sbnk_ccy;
    private Textbox sb_ccyexp;
    private Textbox sb_cnvrate;
    private Textbox sb_cnvdate;
    private Textbox i_amount;
    private Textbox i_cshback;
    private Textbox i_fee;
    private Textbox ibnk_ccy;
    private Textbox i_ccyexp;
    private Textbox i_cnvrate;
    private Textbox i_cnvdate;
    private Textbox abvr_name;
    private Textbox city;
    private Textbox country;
    private Textbox point_code;
    private Textbox mcc_code;
    private Textbox terminal;
    private Textbox batch_id;
    private Textbox settl_nr;
    private Textbox settl_date;
    private Textbox acqref_nr;
    private Textbox file_id;
    private Textbox ms_number;
    private Textbox file_date;
    private Textbox source_algorithm;
    private Textbox term_nr;
    private Textbox ecmc_fee;
    private Textbox tran_info;
    private Textbox pr_amount;
    private Textbox pr_cshback;
    private Textbox pr_fee;
    private Textbox prnk_ccy;
    private Textbox pr_ccyexp;
    private Textbox pr_cnvrate;
    private Textbox pr_cnvdate;
    private Textbox e_region;
    private Textbox card_type;
    private Textbox proc_class;
    private Textbox card_seq_nr;
    private Textbox msg_type;
    private Textbox org_msg_type;
    private Textbox proc_code;
    private Textbox msg_category;
    private Textbox merchant;
    private Textbox moto_ind;
    private Textbox susp_status;
    private Textbox transact_row;
    private Textbox authoriz_row;
    private Textbox fld_043;
    private Textbox fld_098;
    private Textbox fld_102;
    private Textbox fld_103;
    private Textbox fld_104;
    private Textbox fld_039;
    private Textbox fld_sh6;
    private Textbox batch_date;
    private Textbox tr_fee;
    private Textbox fld_040;
    private Textbox fld_123_1;
    private Textbox epi_42_48;
    private Textbox fld_003;
    private Textbox msc;
    private Textbox account_nr;
    private Textbox epi_42_48_full;
    private Textbox other_code;
    private Textbox fld_015;
    private Textbox fld_095;
    private Textbox audit_date;
    private Textbox other_fee1;
    private Textbox other_fee2;
    private Textbox other_fee3;
    private Textbox other_fee4;
    private Textbox other_fee5;
    private Textbox state_id;
    private Textbox aid;
    private Textbox arec_num;
    private Textbox aempc_file_id;
    private Textbox amtid;
    private Textbox arec_centr;
    private Textbox asend_centr;
    private Textbox aiss_cmi;
    private Textbox asend_cmi;
    private Textbox asettl_cmi;
    private Textbox aacq_bank;
    private Textbox aacq_branch;
    private Textbox amember;
    private Textbox aclearing_group;
    private Textbox amerchant_accept;
    private Textbox abatch_nr;
    private Textbox aslip_nr;
    private Textbox acard;
    private Textbox aexp_date;
    private Textbox ae_date;
    private Textbox ae_time;
    private Textbox atran_type;
    private Textbox aappr_code;
    private Textbox aappr_src;
    private Textbox astan;
    private Textbox aref_number;
    private Textbox aamount;
    private Textbox acash_back;
    private Textbox afee;
    private Textbox acurrency;
    private Textbox accy_exp;
    private Textbox asb_amount;
    private Textbox asb_cshback;
    private Textbox asb_fee;
    private Textbox asbnk_ccy;
    private Textbox asb_ccyexp;
    private Textbox asb_cnvrate;
    private Textbox asb_cnvdate;
    private Textbox ai_amount;
    private Textbox ai_cshback;
    private Textbox ai_fee;
    private Textbox aibnk_ccy;
    private Textbox ai_ccyexp;
    private Textbox ai_cnvrate;
    private Textbox ai_cnvdate;
    private Textbox aabvr_name;
    private Textbox acity;
    private Textbox acountry;
    private Textbox apoint_code;
    private Textbox amcc_code;
    private Textbox aterminal;
    private Textbox abatch_id;
    private Textbox asettl_nr;
    private Textbox asettl_date;
    private Textbox aacqref_nr;
    private Textbox afile_id;
    private Textbox ams_number;
    private Textbox afile_date;
    private Textbox asource_algorithm;
    private Textbox aterm_nr;
    private Textbox aecmc_fee;
    private Textbox atran_info;
    private Textbox apr_amount;
    private Textbox apr_cshback;
    private Textbox apr_fee;
    private Textbox aprnk_ccy;
    private Textbox apr_ccyexp;
    private Textbox apr_cnvrate;
    private Textbox apr_cnvdate;
    private Textbox ae_region;
    private Textbox acard_type;
    private Textbox aproc_class;
    private Textbox acard_seq_nr;
    private Textbox amsg_type;
    private Textbox aorg_msg_type;
    private Textbox aproc_code;
    private Textbox amsg_category;
    private Textbox amerchant;
    private Textbox amoto_ind;
    private Textbox asusp_status;
    private Textbox atransact_row;
    private Textbox aauthoriz_row;
    private Textbox afld_043;
    private Textbox afld_098;
    private Textbox afld_102;
    private Textbox afld_103;
    private Textbox afld_104;
    private Textbox afld_039;
    private Textbox afld_sh6;
    private Textbox abatch_date;
    private Textbox atr_fee;
    private Textbox afld_040;
    private Textbox afld_123_1;
    private Textbox aepi_42_48;
    private Textbox afld_003;
    private Textbox amsc;
    private Textbox aaccount_nr;
    private Textbox aepi_42_48_full;
    private Textbox aother_code;
    private Textbox afld_015;
    private Textbox afld_095;
    private Textbox aaudit_date;
    private Textbox aother_fee1;
    private Textbox aother_fee2;
    private Textbox aother_fee3;
    private Textbox aother_fee4;
    private Textbox aother_fee5;
    private Textbox astate_id;
    private Textbox fid;
    private Textbox frec_num;
    private Textbox fempc_file_id;
    private Textbox fmtid;
    private Textbox frec_centr;
    private Textbox fsend_centr;
    private Textbox fiss_cmi;
    private Textbox fsend_cmi;
    private Textbox fsettl_cmi;
    private Textbox facq_bank;
    private Textbox facq_branch;
    private Textbox fmember;
    private Textbox fclearing_group;
    private Textbox fmerchant_accept;
    private Textbox fbatch_nr;
    private Textbox fslip_nr;
    private Textbox fcard;
    private Textbox fexp_date;
    private Textbox fe_date;
    private Textbox fe_time;
    private Textbox ftran_type;
    private Textbox fappr_code;
    private Textbox fappr_src;
    private Textbox fstan;
    private Textbox fref_number;
    private Textbox famount;
    private Textbox fcash_back;
    private Textbox ffee;
    private Textbox fcurrency;
    private Textbox fccy_exp;
    private Textbox fsb_amount;
    private Textbox fsb_cshback;
    private Textbox fsb_fee;
    private Textbox fsbnk_ccy;
    private Textbox fsb_ccyexp;
    private Textbox fsb_cnvrate;
    private Textbox fsb_cnvdate;
    private Textbox fi_amount;
    private Textbox fi_cshback;
    private Textbox fi_fee;
    private Textbox fibnk_ccy;
    private Textbox fi_ccyexp;
    private Textbox fi_cnvrate;
    private Textbox fi_cnvdate;
    private Textbox fabvr_name;
    private Textbox fcity;
    private Textbox fcountry;
    private Textbox fpoint_code;
    private Textbox fmcc_code;
    private Textbox fterminal;
    private Textbox fbatch_id;
    private Textbox fsettl_nr;
    private Textbox fsettl_date;
    private Textbox facqref_nr;
    private Textbox ffile_id;
    private Textbox fms_number;
    private Textbox ffile_date;
    private Textbox fsource_algorithm;
    private Textbox fterm_nr;
    private Textbox fecmc_fee;
    private Textbox ftran_info;
    private Textbox fpr_amount;
    private Textbox fpr_cshback;
    private Textbox fpr_fee;
    private Textbox fprnk_ccy;
    private Textbox fpr_ccyexp;
    private Textbox fpr_cnvrate;
    private Textbox fpr_cnvdate;
    private Textbox fe_region;
    private Textbox fcard_type;
    private Textbox fproc_class;
    private Textbox fcard_seq_nr;
    private Textbox fmsg_type;
    private Textbox forg_msg_type;
    private Textbox fproc_code;
    private Textbox fmsg_category;
    private Textbox fmerchant;
    private Textbox fmoto_ind;
    private Textbox fsusp_status;
    private Textbox ftransact_row;
    private Textbox fauthoriz_row;
    private Textbox ffld_043;
    private Textbox ffld_098;
    private Textbox ffld_102;
    private Textbox ffld_103;
    private Textbox ffld_104;
    private Textbox ffld_039;
    private Textbox ffld_sh6;
    private Textbox fbatch_date;
    private Textbox ftr_fee;
    private Textbox ffld_040;
    private Textbox ffld_123_1;
    private Textbox fepi_42_48;
    private Textbox ffld_003;
    private Textbox fmsc;
    private Textbox faccount_nr;
    private Textbox fepi_42_48_full;
    private Textbox fother_code;
    private Textbox ffld_015;
    private Textbox ffld_095;
    private Textbox faudit_date;
    private Textbox fother_fee1;
    private Textbox fother_fee2;
    private Textbox fother_fee3;
    private Textbox fother_fee4;
    private Textbox fother_fee5;
    private Textbox fstate_id;
    private Paging empcfilesbtransactionsPaging;
    private int _pageSize;
    private int _startPageNumber;
    private int _totalSize;
    private boolean _needsTotalSizeUpdate;
    public EmpcFilesBTransactionsFilter filter;
    PagingListModel model;
    ListModelList lmodel;
    private AnnotateDataBinder binder;
    SimpleDateFormat df;
    private EmpcFilesBTransactions current;
    
    public EmpcFilesBTransactionsViewCtrl() {
        super('$', false, false);
        this._pageSize = 15;
        this._startPageNumber = 0;
        this._totalSize = 0;
        this._needsTotalSizeUpdate = true;
        this.model = null;
        this.lmodel = null;
        this.df = new SimpleDateFormat("dd.MM.yyyy");
        this.current = new EmpcFilesBTransactions();
    }
    
    public void doAfterCompose(final Component comp) throws Exception {
        super.doAfterCompose(comp);
        (this.binder = new AnnotateDataBinder(comp)).bindBean("current", (Object)this.current);
        this.binder.loadAll();
        final String[] parameter = (String[])this.param.get("ht");
        if (parameter != null) {
            this._pageSize = Integer.parseInt(parameter[0]) / 36;
            this.dataGrid.setRows(Integer.parseInt(parameter[0]) / 36);
        }
        this.dataGrid.setItemRenderer((ListitemRenderer)new ListitemRenderer() {
            public void render(final Listitem row, final Object data) throws Exception {
                final EmpcFilesBTransactions pEmpcFilesBTransactions = (EmpcFilesBTransactions)data;
                row.setValue((Object)pEmpcFilesBTransactions);
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getId().toString()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getRec_num()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getEmpc_file_id()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getMtid()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getRec_centr()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getSend_centr()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getIss_cmi()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getSend_cmi()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getSettl_cmi()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getAcq_bank()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getAcq_branch()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getMember()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getClearing_group()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getMerchant_accept()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getBatch_nr()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getSlip_nr()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getCard()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getExp_date()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getE_date()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getE_time()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getTran_type()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getAppr_code()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getAppr_src()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getStan()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getRef_number()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getAmount()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getCash_back()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getFee()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getCurrency()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getCcy_exp()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getSb_amount()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getSb_cshback()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getSb_fee()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getSbnk_ccy()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getSb_ccyexp()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getSb_cnvrate()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getSb_cnvdate()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getI_amount()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getI_cshback()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getI_fee()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getIbnk_ccy()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getI_ccyexp()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getI_cnvrate()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getI_cnvdate()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getAbvr_name()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getCity()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getCountry()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getPoint_code()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getMcc_code()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getTerminal()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getBatch_id()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getSettl_nr()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getSettl_date()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getAcqref_nr()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getFile_id()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getMs_number()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getFile_date()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getSource_algorithm()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getTerm_nr()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getEcmc_fee()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getTran_info()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getPr_amount()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getPr_cshback()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getPr_fee()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getPrnk_ccy()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getPr_ccyexp()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getPr_cnvrate()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getPr_cnvdate()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getE_region()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getCard_type()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getProc_class()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getCard_seq_nr()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getMsg_type()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getOrg_msg_type()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getProc_code()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getMsg_category()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getMerchant()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getMoto_ind()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getSusp_status()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getTransact_row()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getAuthoriz_row()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getFld_043()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getFld_098()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getFld_102()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getFld_103()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getFld_104()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getFld_039()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getFld_sh6()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getBatch_date()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getTr_fee()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getFld_040()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getFld_123_1()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getEpi_42_48()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getFld_003()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getMsc()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getAccount_nr()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getEpi_42_48_full()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getOther_code()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getFld_015()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getFld_095()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getAudit_date()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getOther_fee1()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getOther_fee2()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getOther_fee3()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getOther_fee4()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getOther_fee5()));
                row.appendChild((Component)new Listcell(pEmpcFilesBTransactions.getState_id()));
            }
        });
        this.refreshModel(this._startPageNumber);
    }
    
    public void onPaging$empcfilesbtransactionsPaging(final ForwardEvent event) {
        final PagingEvent pe = (PagingEvent)event.getOrigin();
        this.refreshModel(this._startPageNumber = pe.getActivePage());
    }
    
    private void refreshModel(final int activePage) {
        this.empcfilesbtransactionsPaging.setPageSize(this._pageSize);
        this.model = new PagingListModel(activePage, this._pageSize, this.filter, "");
        if (this._needsTotalSizeUpdate) {
            this._totalSize = this.model.getTotalSize();
            this._needsTotalSizeUpdate = false;
        }
        this.empcfilesbtransactionsPaging.setTotalSize(this._totalSize);
        this.dataGrid.setModel((ListModel)this.model);
        if (this.model.getSize() > 0) {
            this.current = (EmpcFilesBTransactions)this.model.getElementAt(0);
            this.sendSelEvt();
        }
    }
    
    public EmpcFilesBTransactions getCurrent() {
        return this.current;
    }
    
    public void setCurrent(final EmpcFilesBTransactions current) {
        this.current = current;
    }
    
    public void onDoubleClick$dataGrid$grd() {
        this.grd.setVisible(false);
        this.frm.setVisible(true);
        this.frmgrd.setVisible(true);
        this.addgrd.setVisible(false);
        this.fgrd.setVisible(false);
        this.btn_back.setImage("/images/folder.png");
    }
    
    public void onClick$btn_back() {
        if (this.frm.isVisible()) {
            this.frm.setVisible(false);
            this.grd.setVisible(true);
            this.btn_back.setImage("/images/file.png");
        }
        else {
            this.onDoubleClick$dataGrid$grd();
        }
    }
    
    public void onClick$btn_first() {
        this.dataGrid.setSelectedIndex(0);
        this.sendSelEvt();
    }
    
    public void onClick$btn_last() {
        this.dataGrid.setSelectedIndex(this.model.getSize() - 1);
        this.sendSelEvt();
    }
    
    public void onClick$btn_prev() {
        if (this.dataGrid.getSelectedIndex() != 0) {
            this.dataGrid.setSelectedIndex(this.dataGrid.getSelectedIndex() - 1);
            this.sendSelEvt();
        }
    }
    
    public void onClick$btn_next() {
        if (this.dataGrid.getSelectedIndex() != this.model.getSize() - 1) {
            this.dataGrid.setSelectedIndex(this.dataGrid.getSelectedIndex() + 1);
            this.sendSelEvt();
        }
    }
    
    private void sendSelEvt() {
        if (this.dataGrid.getSelectedIndex() == 0) {
            this.btn_first.setDisabled(true);
            this.btn_prev.setDisabled(true);
        }
        else {
            this.btn_first.setDisabled(false);
            this.btn_prev.setDisabled(false);
        }
        if (this.dataGrid.getSelectedIndex() == this.model.getSize() - 1) {
            this.btn_next.setDisabled(true);
            this.btn_last.setDisabled(true);
        }
        else {
            this.btn_next.setDisabled(false);
            this.btn_last.setDisabled(false);
        }
    }
    
    public void onClick$btn_add() {
        this.onDoubleClick$dataGrid$grd();
        this.frmgrd.setVisible(false);
        this.addgrd.setVisible(true);
        this.fgrd.setVisible(false);
    }
    
    public void onClick$btn_search() {
        this.onDoubleClick$dataGrid$grd();
        this.frmgrd.setVisible(false);
        this.addgrd.setVisible(false);
        this.fgrd.setVisible(true);
    }
    
    public void onClick$btn_save() {
        try {
            if (this.addgrd.isVisible()) {
                EmpcFilesBTransactionsService.create(new EmpcFilesBTransactions(Long.valueOf(this.aid.getValue()), this.arec_num.getValue(), this.aempc_file_id.getValue(), this.amtid.getValue(), this.arec_centr.getValue(), this.asend_centr.getValue(), this.aiss_cmi.getValue(), this.asend_cmi.getValue(), this.asettl_cmi.getValue(), this.aacq_bank.getValue(), this.aacq_branch.getValue(), this.amember.getValue(), this.aclearing_group.getValue(), this.amerchant_accept.getValue(), this.abatch_nr.getValue(), this.aslip_nr.getValue(), this.acard.getValue(), this.aexp_date.getValue(), this.ae_date.getValue(), this.ae_time.getValue(), this.atran_type.getValue(), this.aappr_code.getValue(), this.aappr_src.getValue(), this.astan.getValue(), this.aref_number.getValue(), this.aamount.getValue(), this.acash_back.getValue(), this.afee.getValue(), this.acurrency.getValue(), this.accy_exp.getValue(), this.asb_amount.getValue(), this.asb_cshback.getValue(), this.asb_fee.getValue(), this.asbnk_ccy.getValue(), this.asb_ccyexp.getValue(), this.asb_cnvrate.getValue(), this.asb_cnvdate.getValue(), this.ai_amount.getValue(), this.ai_cshback.getValue(), this.ai_fee.getValue(), this.aibnk_ccy.getValue(), this.ai_ccyexp.getValue(), this.ai_cnvrate.getValue(), this.ai_cnvdate.getValue(), this.aabvr_name.getValue(), this.acity.getValue(), this.acountry.getValue(), this.apoint_code.getValue(), this.amcc_code.getValue(), this.aterminal.getValue(), this.abatch_id.getValue(), this.asettl_nr.getValue(), this.asettl_date.getValue(), this.aacqref_nr.getValue(), this.afile_id.getValue(), this.ams_number.getValue(), this.afile_date.getValue(), this.asource_algorithm.getValue(), this.aterm_nr.getValue(), this.aecmc_fee.getValue(), this.atran_info.getValue(), this.apr_amount.getValue(), this.apr_cshback.getValue(), this.apr_fee.getValue(), this.aprnk_ccy.getValue(), this.apr_ccyexp.getValue(), this.apr_cnvrate.getValue(), this.apr_cnvdate.getValue(), this.ae_region.getValue(), this.acard_type.getValue(), this.aproc_class.getValue(), this.acard_seq_nr.getValue(), this.amsg_type.getValue(), this.aorg_msg_type.getValue(), this.aproc_code.getValue(), this.amsg_category.getValue(), this.amerchant.getValue(), this.amoto_ind.getValue(), this.asusp_status.getValue(), this.atransact_row.getValue(), this.aauthoriz_row.getValue(), this.afld_043.getValue(), this.afld_098.getValue(), this.afld_102.getValue(), this.afld_103.getValue(), this.afld_104.getValue(), this.afld_039.getValue(), this.afld_sh6.getValue(), this.abatch_date.getValue(), this.atr_fee.getValue(), this.afld_040.getValue(), this.afld_123_1.getValue(), this.aepi_42_48.getValue(), this.afld_003.getValue(), this.amsc.getValue(), this.aaccount_nr.getValue(), this.aepi_42_48_full.getValue(), this.aother_code.getValue(), this.afld_015.getValue(), this.afld_095.getValue(), this.aaudit_date.getValue(), this.aother_fee1.getValue(), this.aother_fee2.getValue(), this.aother_fee3.getValue(), this.aother_fee4.getValue(), this.aother_fee5.getValue(), this.astate_id.getValue()));
                this.frmgrd.setVisible(true);
                this.addgrd.setVisible(false);
                this.fgrd.setVisible(false);
            }
            else if (this.fgrd.isVisible()) {
                (this.filter = new EmpcFilesBTransactionsFilter()).setId(Long.valueOf(this.fid.getValue()));
                this.filter.setRec_num(this.frec_num.getValue());
                this.filter.setEmpc_file_id(this.fempc_file_id.getValue());
                this.filter.setMtid(this.fmtid.getValue());
                this.filter.setRec_centr(this.frec_centr.getValue());
                this.filter.setSend_centr(this.fsend_centr.getValue());
                this.filter.setIss_cmi(this.fiss_cmi.getValue());
                this.filter.setSend_cmi(this.fsend_cmi.getValue());
                this.filter.setSettl_cmi(this.fsettl_cmi.getValue());
                this.filter.setAcq_bank(this.facq_bank.getValue());
                this.filter.setAcq_branch(this.facq_branch.getValue());
                this.filter.setMember(this.fmember.getValue());
                this.filter.setClearing_group(this.fclearing_group.getValue());
                this.filter.setMerchant_accept(this.fmerchant_accept.getValue());
                this.filter.setBatch_nr(this.fbatch_nr.getValue());
                this.filter.setSlip_nr(this.fslip_nr.getValue());
                this.filter.setCard(this.fcard.getValue());
                this.filter.setExp_date(this.fexp_date.getValue());
                this.filter.setE_date(this.fe_date.getValue());
                this.filter.setE_time(this.fe_time.getValue());
                this.filter.setTran_type(this.ftran_type.getValue());
                this.filter.setAppr_code(this.fappr_code.getValue());
                this.filter.setAppr_src(this.fappr_src.getValue());
                this.filter.setStan(this.fstan.getValue());
                this.filter.setRef_number(this.fref_number.getValue());
                this.filter.setAmount(this.famount.getValue());
                this.filter.setCash_back(this.fcash_back.getValue());
                this.filter.setFee(this.ffee.getValue());
                this.filter.setCurrency(this.fcurrency.getValue());
                this.filter.setCcy_exp(this.fccy_exp.getValue());
                this.filter.setSb_amount(this.fsb_amount.getValue());
                this.filter.setSb_cshback(this.fsb_cshback.getValue());
                this.filter.setSb_fee(this.fsb_fee.getValue());
                this.filter.setSbnk_ccy(this.fsbnk_ccy.getValue());
                this.filter.setSb_ccyexp(this.fsb_ccyexp.getValue());
                this.filter.setSb_cnvrate(this.fsb_cnvrate.getValue());
                this.filter.setSb_cnvdate(this.fsb_cnvdate.getValue());
                this.filter.setI_amount(this.fi_amount.getValue());
                this.filter.setI_cshback(this.fi_cshback.getValue());
                this.filter.setI_fee(this.fi_fee.getValue());
                this.filter.setIbnk_ccy(this.fibnk_ccy.getValue());
                this.filter.setI_ccyexp(this.fi_ccyexp.getValue());
                this.filter.setI_cnvrate(this.fi_cnvrate.getValue());
                this.filter.setI_cnvdate(this.fi_cnvdate.getValue());
                this.filter.setAbvr_name(this.fabvr_name.getValue());
                this.filter.setCity(this.fcity.getValue());
                this.filter.setCountry(this.fcountry.getValue());
                this.filter.setPoint_code(this.fpoint_code.getValue());
                this.filter.setMcc_code(this.fmcc_code.getValue());
                this.filter.setTerminal(this.fterminal.getValue());
                this.filter.setBatch_id(this.fbatch_id.getValue());
                this.filter.setSettl_nr(this.fsettl_nr.getValue());
                this.filter.setSettl_date(this.fsettl_date.getValue());
                this.filter.setAcqref_nr(this.facqref_nr.getValue());
                this.filter.setFile_id(this.ffile_id.getValue());
                this.filter.setMs_number(this.fms_number.getValue());
                this.filter.setFile_date(this.ffile_date.getValue());
                this.filter.setSource_algorithm(this.fsource_algorithm.getValue());
                this.filter.setTerm_nr(this.fterm_nr.getValue());
                this.filter.setEcmc_fee(this.fecmc_fee.getValue());
                this.filter.setTran_info(this.ftran_info.getValue());
                this.filter.setPr_amount(this.fpr_amount.getValue());
                this.filter.setPr_cshback(this.fpr_cshback.getValue());
                this.filter.setPr_fee(this.fpr_fee.getValue());
                this.filter.setPrnk_ccy(this.fprnk_ccy.getValue());
                this.filter.setPr_ccyexp(this.fpr_ccyexp.getValue());
                this.filter.setPr_cnvrate(this.fpr_cnvrate.getValue());
                this.filter.setPr_cnvdate(this.fpr_cnvdate.getValue());
                this.filter.setE_region(this.fe_region.getValue());
                this.filter.setCard_type(this.fcard_type.getValue());
                this.filter.setProc_class(this.fproc_class.getValue());
                this.filter.setCard_seq_nr(this.fcard_seq_nr.getValue());
                this.filter.setMsg_type(this.fmsg_type.getValue());
                this.filter.setOrg_msg_type(this.forg_msg_type.getValue());
                this.filter.setProc_code(this.fproc_code.getValue());
                this.filter.setMsg_category(this.fmsg_category.getValue());
                this.filter.setMerchant(this.fmerchant.getValue());
                this.filter.setMoto_ind(this.fmoto_ind.getValue());
                this.filter.setSusp_status(this.fsusp_status.getValue());
                this.filter.setTransact_row(this.ftransact_row.getValue());
                this.filter.setAuthoriz_row(this.fauthoriz_row.getValue());
                this.filter.setFld_043(this.ffld_043.getValue());
                this.filter.setFld_098(this.ffld_098.getValue());
                this.filter.setFld_102(this.ffld_102.getValue());
                this.filter.setFld_103(this.ffld_103.getValue());
                this.filter.setFld_104(this.ffld_104.getValue());
                this.filter.setFld_039(this.ffld_039.getValue());
                this.filter.setFld_sh6(this.ffld_sh6.getValue());
                this.filter.setBatch_date(this.fbatch_date.getValue());
                this.filter.setTr_fee(this.ftr_fee.getValue());
                this.filter.setFld_040(this.ffld_040.getValue());
                this.filter.setFld_123_1(this.ffld_123_1.getValue());
                this.filter.setEpi_42_48(this.fepi_42_48.getValue());
                this.filter.setFld_003(this.ffld_003.getValue());
                this.filter.setMsc(this.fmsc.getValue());
                this.filter.setAccount_nr(this.faccount_nr.getValue());
                this.filter.setEpi_42_48_full(this.fepi_42_48_full.getValue());
                this.filter.setOther_code(this.fother_code.getValue());
                this.filter.setFld_015(this.ffld_015.getValue());
                this.filter.setFld_095(this.ffld_095.getValue());
                this.filter.setAudit_date(this.faudit_date.getValue());
                this.filter.setOther_fee1(this.fother_fee1.getValue());
                this.filter.setOther_fee2(this.fother_fee2.getValue());
                this.filter.setOther_fee3(this.fother_fee3.getValue());
                this.filter.setOther_fee4(this.fother_fee4.getValue());
                this.filter.setOther_fee5(this.fother_fee5.getValue());
                this.filter.setState_id(this.fstate_id.getValue());
            }
            else {
                this.current.setId(Long.valueOf(this.id.getValue()));
                this.current.setRec_num(this.rec_num.getValue());
                this.current.setEmpc_file_id(this.empc_file_id.getValue());
                this.current.setMtid(this.mtid.getValue());
                this.current.setRec_centr(this.rec_centr.getValue());
                this.current.setSend_centr(this.send_centr.getValue());
                this.current.setIss_cmi(this.iss_cmi.getValue());
                this.current.setSend_cmi(this.send_cmi.getValue());
                this.current.setSettl_cmi(this.settl_cmi.getValue());
                this.current.setAcq_bank(this.acq_bank.getValue());
                this.current.setAcq_branch(this.acq_branch.getValue());
                this.current.setMember(this.member.getValue());
                this.current.setClearing_group(this.clearing_group.getValue());
                this.current.setMerchant_accept(this.merchant_accept.getValue());
                this.current.setBatch_nr(this.batch_nr.getValue());
                this.current.setSlip_nr(this.slip_nr.getValue());
                this.current.setCard(this.card.getValue());
                this.current.setExp_date(this.exp_date.getValue());
                this.current.setE_date(this.e_date.getValue());
                this.current.setE_time(this.e_time.getValue());
                this.current.setTran_type(this.tran_type.getValue());
                this.current.setAppr_code(this.appr_code.getValue());
                this.current.setAppr_src(this.appr_src.getValue());
                this.current.setStan(this.stan.getValue());
                this.current.setRef_number(this.ref_number.getValue());
                this.current.setAmount(this.amount.getValue());
                this.current.setCash_back(this.cash_back.getValue());
                this.current.setFee(this.fee.getValue());
                this.current.setCurrency(this.currency.getValue());
                this.current.setCcy_exp(this.ccy_exp.getValue());
                this.current.setSb_amount(this.sb_amount.getValue());
                this.current.setSb_cshback(this.sb_cshback.getValue());
                this.current.setSb_fee(this.sb_fee.getValue());
                this.current.setSbnk_ccy(this.sbnk_ccy.getValue());
                this.current.setSb_ccyexp(this.sb_ccyexp.getValue());
                this.current.setSb_cnvrate(this.sb_cnvrate.getValue());
                this.current.setSb_cnvdate(this.sb_cnvdate.getValue());
                this.current.setI_amount(this.i_amount.getValue());
                this.current.setI_cshback(this.i_cshback.getValue());
                this.current.setI_fee(this.i_fee.getValue());
                this.current.setIbnk_ccy(this.ibnk_ccy.getValue());
                this.current.setI_ccyexp(this.i_ccyexp.getValue());
                this.current.setI_cnvrate(this.i_cnvrate.getValue());
                this.current.setI_cnvdate(this.i_cnvdate.getValue());
                this.current.setAbvr_name(this.abvr_name.getValue());
                this.current.setCity(this.city.getValue());
                this.current.setCountry(this.country.getValue());
                this.current.setPoint_code(this.point_code.getValue());
                this.current.setMcc_code(this.mcc_code.getValue());
                this.current.setTerminal(this.terminal.getValue());
                this.current.setBatch_id(this.batch_id.getValue());
                this.current.setSettl_nr(this.settl_nr.getValue());
                this.current.setSettl_date(this.settl_date.getValue());
                this.current.setAcqref_nr(this.acqref_nr.getValue());
                this.current.setFile_id(this.file_id.getValue());
                this.current.setMs_number(this.ms_number.getValue());
                this.current.setFile_date(this.file_date.getValue());
                this.current.setSource_algorithm(this.source_algorithm.getValue());
                this.current.setTerm_nr(this.term_nr.getValue());
                this.current.setEcmc_fee(this.ecmc_fee.getValue());
                this.current.setTran_info(this.tran_info.getValue());
                this.current.setPr_amount(this.pr_amount.getValue());
                this.current.setPr_cshback(this.pr_cshback.getValue());
                this.current.setPr_fee(this.pr_fee.getValue());
                this.current.setPrnk_ccy(this.prnk_ccy.getValue());
                this.current.setPr_ccyexp(this.pr_ccyexp.getValue());
                this.current.setPr_cnvrate(this.pr_cnvrate.getValue());
                this.current.setPr_cnvdate(this.pr_cnvdate.getValue());
                this.current.setE_region(this.e_region.getValue());
                this.current.setCard_type(this.card_type.getValue());
                this.current.setProc_class(this.proc_class.getValue());
                this.current.setCard_seq_nr(this.card_seq_nr.getValue());
                this.current.setMsg_type(this.msg_type.getValue());
                this.current.setOrg_msg_type(this.org_msg_type.getValue());
                this.current.setProc_code(this.proc_code.getValue());
                this.current.setMsg_category(this.msg_category.getValue());
                this.current.setMerchant(this.merchant.getValue());
                this.current.setMoto_ind(this.moto_ind.getValue());
                this.current.setSusp_status(this.susp_status.getValue());
                this.current.setTransact_row(this.transact_row.getValue());
                this.current.setAuthoriz_row(this.authoriz_row.getValue());
                this.current.setFld_043(this.fld_043.getValue());
                this.current.setFld_098(this.fld_098.getValue());
                this.current.setFld_102(this.fld_102.getValue());
                this.current.setFld_103(this.fld_103.getValue());
                this.current.setFld_104(this.fld_104.getValue());
                this.current.setFld_039(this.fld_039.getValue());
                this.current.setFld_sh6(this.fld_sh6.getValue());
                this.current.setBatch_date(this.batch_date.getValue());
                this.current.setTr_fee(this.tr_fee.getValue());
                this.current.setFld_040(this.fld_040.getValue());
                this.current.setFld_123_1(this.fld_123_1.getValue());
                this.current.setEpi_42_48(this.epi_42_48.getValue());
                this.current.setFld_003(this.fld_003.getValue());
                this.current.setMsc(this.msc.getValue());
                this.current.setAccount_nr(this.account_nr.getValue());
                this.current.setEpi_42_48_full(this.epi_42_48_full.getValue());
                this.current.setOther_code(this.other_code.getValue());
                this.current.setFld_015(this.fld_015.getValue());
                this.current.setFld_095(this.fld_095.getValue());
                this.current.setAudit_date(this.audit_date.getValue());
                this.current.setOther_fee1(this.other_fee1.getValue());
                this.current.setOther_fee2(this.other_fee2.getValue());
                this.current.setOther_fee3(this.other_fee3.getValue());
                this.current.setOther_fee4(this.other_fee4.getValue());
                this.current.setOther_fee5(this.other_fee5.getValue());
                this.current.setState_id(this.state_id.getValue());
                EmpcFilesBTransactionsService.update(this.current);
            }
            this.onClick$btn_back();
            this.refreshModel(this._startPageNumber);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void onClick$btn_cancel() {
        if (this.fgrd.isVisible()) {
            this.filter = new EmpcFilesBTransactionsFilter();
        }
        this.onClick$btn_back();
        this.frmgrd.setVisible(true);
        this.addgrd.setVisible(false);
        this.fgrd.setVisible(false);
        this.refreshModel(this._startPageNumber);
    }
}
