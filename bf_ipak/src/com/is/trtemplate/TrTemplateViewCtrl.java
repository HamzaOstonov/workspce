package com.is.trtemplate;

import com.is.ConnectionPool;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.RefData;
import com.is.utils.RefDataService;
import com.is.utils.Res;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.util.resource.Labels;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Label;
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

public class TrTemplateViewCtrl extends GenericForwardComposer {
    private Div frm;
    private Listbox dataGrid;
    private Paging contactPaging;
    private Div grd;
    private Hbox frmgrd;
    private Hbox addgrd;
    private Grid addgrdl;
    private Grid addgrdr;
    private Grid frmgrdl;
    private Grid frmgrdr;
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
    private Textbox cash_code;
    private Textbox purpose;
    private Textbox ord;
    private Textbox id_transh_purp;
    private Textbox perc_for_trans;
    private Textbox aperc_for_trans;
    private Textbox aid;
    private Textbox aoperation_id;
    private Textbox acash_code;
    private Textbox apurpose;
    private Textbox aord;
    private Textbox aid_transh_purp;
    private Textbox fid;
    private Textbox foperation_id;
    private Textbox facc_dt;
    private Textbox facc_ct;
    private Textbox fcurrency;
    private Textbox fdoc_type;
    private Textbox fcash_code;
    private Textbox fpurpose;
    private Textbox fpurpose_code;
    private Textbox ford;
    private Textbox fid_transh_purp, aamount, amount;
    private Paging trtemplatePaging;
    private RefCBox moperation_id;
    private RefCBox acc_dt;
    private RefCBox acc_ct;
    private RefCBox doc_type;
    private RefCBox currency;
    private RefCBox purpose_code;
    private RefCBox aoperation_type;
    private RefCBox asuboperation_type;
    private RefCBox pdc;
    private RefCBox amoperation_id;
    private RefCBox aacc_dt;
    private RefCBox aacc_ct;
    private RefCBox adoc_type;
    private RefCBox acurrency;
    private RefCBox apurpose_code;
    private RefCBox operation_type;
    private RefCBox suboperation_type;
    private RefCBox apdc;
    private int _pageSize = 15;
    private int _startPageNumber = 0;
    private int _totalSize = 0;
    private boolean _needsTotalSizeUpdate = true;
    private String alias;
    private Toolbarbutton bt = null;
    private int curroperation_id;
    private Label operation_id, operation_id1;
    public TrTemplateFilter filter = new TrTemplateFilter();
    private Long current_operation_id = null;
    private Long current_template_id = null;
    private String amount_parameter = null;
    private boolean single_record = false;
    private HashMap<String, String> cacheacc = new HashMap();

    PagingListModel model = null;
    ListModelList lmodel = null;
    private AnnotateDataBinder binder;
    SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");

    private TrTemplate current = new TrTemplate();

    public TrTemplateViewCtrl() {
        super('$', false, false);
    }

    public void doAfterCompose(Component comp)
            throws Exception {
        super.doAfterCompose(comp);

        this.binder = new AnnotateDataBinder(comp);
        this.binder.bindBean("current", this.current);
        this.binder.loadAll();
        String[] parameter = (String[]) this.param.get("ht");
        String[] group_id = (String[]) this.param.get("group_id");

        if (this.param.get("operation_id") != null) {
            current_operation_id = Long.parseLong(((String[]) this.param.get("operation_id"))[0]);
        }

        if (this.param.get("template_id") != null) {
            current_template_id = Long.parseLong(((String[]) this.param.get("template_id"))[0]);
        }

        if (this.param.get("single_record") != null) {
            single_record = (((String[]) this.param.get("single_record"))[0]).equals("1");
        }

        if (this.param.get("amount_parameter") != null) {
            amount_parameter = ((String[]) this.param.get("amount_parameter"))[0];
        }

        this.alias = ((String) this.session.getAttribute("alias"));


        if (parameter != null) {
            this._pageSize = (Integer.parseInt(parameter[0]) / 36);
            this.dataGrid.setRows(Integer.parseInt(parameter[0]) / 36);
        }

        List rrr = RefDataService.getAccTmpl(this.alias);
        for (int i = 0; i < rrr.size(); ++i) {
            this.cacheacc.put(((RefData) rrr.get(i)).getData(), ((RefData) rrr.get(i)).getLabel());
        }

        this.dataGrid.setItemRenderer(new ListitemRenderer() {
            public void render(Listitem row, Object data) throws Exception {
                TrTemplate pTrTemplate = (TrTemplate) data;
                Listcell lc = new Listcell();

                TrTemplateViewCtrl.this.bt = new Toolbarbutton();
                TrTemplateViewCtrl.this.bt.setLabel("удалить");
                TrTemplateViewCtrl.this.bt.setImage("/images/delete.png");
                TrTemplateViewCtrl.this.bt.setAttribute("template_id", Integer.valueOf(pTrTemplate.getId()));
                TrTemplateViewCtrl.this.bt.addEventListener("onClick", new EventListener() {
                    public void onEvent(Event event)
                            throws Exception {
                        Connection c = null;
                        try {
                            c = ConnectionPool.getConnection(TrTemplateViewCtrl.this.alias);
                            PreparedStatement ps = c.prepareStatement("DELETE FROM BF_TR_TEMPLATE WHERE id=?");
                            ps.setInt(1, ((Integer) event.getTarget().getAttribute("template_id")).intValue());
                            ps.executeQuery();
                            c.commit();
                        } catch (Exception e) {
                            com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
                        } finally {
                            ConnectionPool.close(c);
                        }
                        TrTemplateViewCtrl.this.refreshModel(TrTemplateViewCtrl.this._startPageNumber);
                    }
                });
                lc.appendChild(TrTemplateViewCtrl.this.bt);
                row.setValue(pTrTemplate);
                row.appendChild(new Listcell(pTrTemplate.getOrd() + ""));
                row.appendChild(new Listcell((String) TrTemplateViewCtrl.this.cacheacc.get(pTrTemplate.getAcc_dt() + "")));
                row.appendChild(new Listcell((String) TrTemplateViewCtrl.this.cacheacc.get(pTrTemplate.getAcc_ct() + "")));
                row.appendChild(new Listcell(pTrTemplate.getPurpose()));
                row.appendChild(lc);
            }
        });
        this.moperation_id.setModel(new ListModelList(RefDataService.getOperation(Integer.parseInt(group_id[0]), this.alias)));

        this.acc_dt.setModel(new ListModelList(RefDataService.getAccTmpl(this.alias)));
        this.acc_ct.setModel(new ListModelList(RefDataService.getAccTmpl(this.alias)));

        this.aacc_dt.setModel(new ListModelList(RefDataService.getAccTmpl(this.alias)));
        this.aacc_ct.setModel(new ListModelList(RefDataService.getAccTmpl(this.alias)));

        this.doc_type.setModel(new ListModelList(RefDataService.getTypeDoc(this.alias)));
        this.currency.setModel(new ListModelList(RefDataService.getCurrency(this.alias)));
        this.purpose_code.setModel(new ListModelList(RefDataService.getPurposeCode(this.alias)));

        this.adoc_type.setModel(new ListModelList(RefDataService.getTypeDoc(this.alias)));
        this.acurrency.setModel(new ListModelList(RefDataService.getCurrency(this.alias)));
        this.apurpose_code.setModel(new ListModelList(RefDataService.getPurposeCode(this.alias)));

        this.operation_type.setModel(new ListModelList(RefDataService.get_operation_type(this.alias)));
        this.suboperation_type.setModel(new ListModelList(RefDataService.get_sub_operation_type(this.alias)));
        this.aoperation_type.setModel(new ListModelList(RefDataService.get_operation_type(this.alias)));
        this.asuboperation_type.setModel(new ListModelList(RefDataService.get_sub_operation_type(this.alias)));

        this.pdc.setModel(new ListModelList(RefDataService.getPdc(this.alias)));
        this.apdc.setModel(new ListModelList(RefDataService.getPdc(this.alias)));

        if (single_record) {
            tb.setVisible(false);
            //onDoubleClick$dataGrid$grd();
            this.curroperation_id = Integer.parseInt(current_operation_id.toString());
            this.filter.setOperation_id(this.curroperation_id);
            this.moperation_id.setValue(Long.toString(current_operation_id));
            this.moperation_id.setSelecteditem(Long.toString(current_operation_id));
            this.filter.setOperation_id(this.curroperation_id);
            this.aamount.setValue(amount_parameter);
            this.amount.setValue(amount_parameter);
            this.aperc_for_trans.setValue("100");
            this.aperc_for_trans.setReadonly(true);
            this.aperc_for_trans.setReadonly(true);
            this.perc_for_trans.setReadonly(true);
            this.perc_for_trans.setReadonly(true);
            this.operation_id.setValue(this.moperation_id.getText());
            this.perc_for_trans.setValue("100");

            refreshModel(this._startPageNumber);

            if (!TrTemplateService.template_exists(current_template_id))
                onClick$btn_add();
            else {
                current = TrTemplateService.getTrTemplate(
                        Integer.parseInt(current_template_id.toString()), alias);

                onDoubleClick$dataGrid$grd();
                this.amount.setValue(current.getAmount());
            }

            this.aamount.setValue(amount_parameter);
            this.amount.setValue(amount_parameter);
            this.aperc_for_trans.setValue("100");
            this.aperc_for_trans.setReadonly(true);
            this.aperc_for_trans.setReadonly(true);
            this.perc_for_trans.setReadonly(true);
            this.perc_for_trans.setReadonly(true);
            this.operation_id.setValue(this.moperation_id.getText());
            this.perc_for_trans.setValue("100");
            this.curroperation_id = Integer.parseInt(current_operation_id.toString());
            this.filter.setOperation_id(this.curroperation_id);
            this.moperation_id.setValue(Long.toString(current_operation_id));
            this.moperation_id.setSelecteditem(Long.toString(current_operation_id));
            this.filter.setOperation_id(this.curroperation_id);
            //moperation_id.setDisabled(false);
            //alert(moperation_id.getValue());
        }
    }

    public void onPaging$userPaging(ForwardEvent event) {
        PagingEvent pe = (PagingEvent) event.getOrigin();
        this._startPageNumber = pe.getActivePage();
        refreshModel(this._startPageNumber);
    }

    private void refreshModel(int activePage) {
        this.trtemplatePaging.setPageSize(this._pageSize);
        this.model = new PagingListModel(activePage, this._pageSize, this.filter, this.alias);

        if (this._needsTotalSizeUpdate) {
            this._totalSize = this.model.getTotalSize(this.filter, this.alias);
        }

        this.trtemplatePaging.setTotalSize(this._totalSize);

        this.dataGrid.setModel(this.model);
        if (this.model.getSize() > 0) {
            this.current = ((TrTemplate) this.model.getElementAt(0));
            sendSelEvt();
        }
    }

 /* public void onClick$doc_type()
  {
    this.doc_type.select();
  }

  public void onClick$currency() {
    this.currency.select();
  }

  public void onClick$purpose_code() {
    this.purpose_code.select();
  }

  public void onClick$adoc_type() {
    this.adoc_type.select();
  }

  public void onClick$apurpose_code() {
    this.apurpose_code.select();
  }*/

    public TrTemplate getCurrent() {
        return this.current;
    }

    public void setCurrent(TrTemplate current) {
        this.current = current;
    }

    public void onDoubleClick$dataGrid$grd() {
        this.grd.setVisible(false);
        this.frm.setVisible(true);
        this.frmgrd.setVisible(true);
        this.addgrd.setVisible(false);
        this.fgrd.setVisible(false);
        this.btn_back.setImage("/images/folder.png");
        this.btn_back.setLabel(Labels.getLabel("grid"));
        this.suboperation_type.setDisabled(true);
        this.asuboperation_type.setDisabled(true);
        moperation_id.setDisabled(true);
        //alert("pdc >"+current.getPdc()+"<");
        this.pdc.setSelecteditem(current.getPdc());

//    amount.setValue(current.getAmount());
//   aamount.setValue(current.getAmount());

//    onSelect$operation_type();
    }

    public void onSelect$operation_type() {
        List d = RefDataService.get_sub_operation_type(this.alias, Integer.parseInt(this.operation_type.getValue()));
        this.suboperation_type.setDisabled(false);
        if (d.isEmpty()) {
            d.add(new RefData("-1", ""));
            this.suboperation_type.setDisabled(true);
            this.suboperation_type.setModel(new ListModelList(d));
            this.suboperation_type.setSelecteditem("-1");
            return;
        }
        this.suboperation_type.setModel(new ListModelList(d));
    }

    public void onSelect$aoperation_type() {
        List d = RefDataService.get_sub_operation_type(this.alias, Integer.parseInt(this.aoperation_type.getValue()));
        this.asuboperation_type.setDisabled(false);
        if (d.isEmpty()) {
            d.add(new RefData("-1", ""));
            this.asuboperation_type.setDisabled(true);
            this.asuboperation_type.setModel(new ListModelList(d));
            this.asuboperation_type.setSelecteditem("-1");
        }
        this.asuboperation_type.setModel(new ListModelList(d));
    }

    public void onClick$btn_back() {
        if (this.frm.isVisible()) {
            this.frm.setVisible(false);
            this.grd.setVisible(true);
            this.btn_back.setImage("/images/file.png");
            this.btn_back.setLabel(Labels.getLabel("back"));
        } else {
            onDoubleClick$dataGrid$grd();
            moperation_id.setDisabled(true);
        }
        moperation_id.setDisabled(false);
    }

    public void onClick$btn_first() {
        this.dataGrid.setSelectedIndex(0);
        sendSelEvt();
    }

    public void onClick$btn_last() {
        this.dataGrid.setSelectedIndex(this.model.getSize() - 1);
        sendSelEvt();
    }

    public void onClick$btn_prev() {
        if (this.dataGrid.getSelectedIndex() != 0) {
            this.dataGrid.setSelectedIndex(this.dataGrid.getSelectedIndex() - 1);
            sendSelEvt();
        }
    }

    public void onClick$btn_next() {
        if (this.dataGrid.getSelectedIndex() != this.model.getSize() - 1) {
            this.dataGrid.setSelectedIndex(this.dataGrid.getSelectedIndex() + 1);
            sendSelEvt();
        }
    }

    private void sendSelEvt() {
        if (this.dataGrid.getSelectedIndex() == 0) {
            this.btn_first.setDisabled(true);
            this.btn_prev.setDisabled(true);
        } else {
            this.btn_first.setDisabled(false);
            this.btn_prev.setDisabled(false);
        }
        if (this.dataGrid.getSelectedIndex() == this.model.getSize() - 1) {
            this.btn_next.setDisabled(true);
            this.btn_last.setDisabled(true);
        } else {
            this.btn_next.setDisabled(false);
            this.btn_last.setDisabled(false);
        }
        SelectEvent evt = new SelectEvent("onSelect", this.dataGrid, this.dataGrid.getSelectedItems());
        Events.sendEvent(evt);
    }

    public void onClick$btn_add() {
        //  onDoubleClick$dataGrid$grd();
        this.grd.setVisible(false);
        this.frm.setVisible(true);
        this.frmgrd.setVisible(true);
        this.addgrd.setVisible(false);
        this.fgrd.setVisible(false);
        this.btn_back.setImage("/images/folder.png");
        this.btn_back.setLabel(Labels.getLabel("grid"));
        this.suboperation_type.setDisabled(true);
        this.asuboperation_type.setDisabled(true);
        moperation_id.setDisabled(true);
        //  this.current = new TrTemplate();

        this.frmgrd.setVisible(false);
        this.addgrd.setVisible(true);
        this.fgrd.setVisible(false);

        CheckNull.clearForm(addgrdl);
        CheckNull.clearForm(addgrdr);

        moperation_id.setDisabled(true);
        this.operation_id1.setValue(this.moperation_id.getText());

        aord.setValue(null);
        aacc_dt.setValue(null);
        apdc.setValue(null);
        aacc_ct.setValue(null);
        acurrency.setValue(null);
        adoc_type.setValue(null);

        acash_code.setValue(null);
        apurpose.setValue(null);
        apurpose_code.setValue(null);
        aid_transh_purp.setValue(null);
        aoperation_type.setValue(null);
        asuboperation_type.setValue(null);
        aperc_for_trans.setValue(null);
    }

    public void onClick$btn_search() {
        onDoubleClick$dataGrid$grd();
        this.frmgrd.setVisible(false);
        this.addgrd.setVisible(false);
        this.fgrd.setVisible(true);
    }

    public void onClick$btn_save() throws InterruptedException {
        Res res1;
        try {
            if (this.addgrd.isVisible()) {
                if (!single_record) {


                    res1 = TrTemplateService.create(new TrTemplate(

                            0,//aid.getValue(),
                            Integer.parseInt(moperation_id.getValue()),
                            Integer.parseInt(aacc_dt.getValue()),
                            Integer.parseInt(aacc_ct.getValue()),
                            acurrency.getValue(),
                            adoc_type.getValue(),
                            ((!CheckNull.isEmpty(acash_code.getValue())) ? acash_code.getValue() : "0"),
                            apurpose.getValue(),
                            apurpose_code.getValue(),
                            Integer.parseInt(aord.getValue()),
                            ((!CheckNull.isEmpty(aid_transh_purp.getValue())) ? Integer.parseInt(aid_transh_purp.getValue()) : 123456),
                            Integer.parseInt(aoperation_type.getValue()),
                            Integer.parseInt(
                                    CheckNull.isEmpty(asuboperation_type.getValue()) ? "-1" : asuboperation_type.getValue()
                            ),
                            Double.parseDouble(aperc_for_trans.getValue()),
                            apdc.getValue(),
                            single_record ? amount_parameter : aamount.getValue()
                    ), alias);

                } else {
                    TrTemplate tmp = new TrTemplate(

                            0,//aid.getValue(),
                            Integer.parseInt(current_operation_id.toString()),
                            Integer.parseInt(aacc_dt.getValue()),
                            Integer.parseInt(aacc_ct.getValue()),
                            acurrency.getValue(),
                            adoc_type.getValue(),
                            ((!CheckNull.isEmpty(acash_code.getValue())) ? acash_code.getValue() : "0"),
                            apurpose.getValue(),
                            apurpose_code.getValue(),
                            Integer.parseInt(aord.getValue()),
                            ((!CheckNull.isEmpty(aid_transh_purp.getValue())) ? Integer.parseInt(aid_transh_purp.getValue()) : 123456),
                            Integer.parseInt(aoperation_type.getValue()),
                            Integer.parseInt(
                                    CheckNull.isEmpty(asuboperation_type.getValue()) ? "-1" : asuboperation_type.getValue()
                            ),
                            Double.parseDouble(aperc_for_trans.getValue()),
                            apdc.getValue(),
                            single_record ? amount_parameter : aamount.getValue());

                    tmp.setAmount(amount_parameter);
                    res1 = TrTemplateService.create(tmp,
                            alias, current_operation_id, current_template_id);
                }
                if (res1.getCode() != 0) {
                    alert(res1.getName());
                } else {
                    if (!single_record) {
                        CheckNull.clearForm(this.addgrdl);
                        CheckNull.clearForm(this.addgrdr);
                        this.frmgrd.setVisible(true);
                        this.addgrd.setVisible(false);
                        this.fgrd.setVisible(false);
                        moperation_id.setDisabled(false);
                    } else {
                        alert("Настройка сохранена.");
                    }
                }
            } else if (this.fgrd.isVisible()) {
                this.filter = new TrTemplateFilter();

                this.filter.setId(Integer.parseInt(this.fid.getValue()));
                this.filter.setOperation_id(Integer.parseInt(this.foperation_id.getValue()));
                this.filter.setAcc_dt(Integer.parseInt(this.facc_dt.getValue()));
                this.filter.setAcc_ct(Integer.parseInt(this.facc_ct.getValue()));
                this.filter.setCurrency(this.fcurrency.getValue());
                this.filter.setDoc_type(this.fdoc_type.getValue());
                this.filter.setCash_code(this.fcash_code.getValue());
                this.filter.setPurpose(this.fpurpose.getValue());
                this.filter.setPurpose_code(this.fpurpose_code.getValue());
                this.filter.setOrd(Integer.parseInt(this.ford.getValue()));
            } else {
                this.current.setAcc_dt(Integer.parseInt(this.acc_dt.getValue()));
                this.current.setAcc_ct(Integer.parseInt(this.acc_ct.getValue()));
                this.current.setCurrency(this.currency.getValue());
                this.current.setDoc_type(this.doc_type.getValue());
                this.current.setCash_code((!CheckNull.isEmpty(cash_code.getValue())) ? cash_code.getValue() : "0");
                this.current.setPurpose(this.purpose.getValue());
                this.current.setPurpose_code(this.purpose_code.getValue());
                this.current.setOrd(Integer.parseInt(this.ord.getValue()));
                this.current.setId_transh_purp((!(CheckNull.isEmpty(id_transh_purp.getValue()))) ? Integer.parseInt(this.id_transh_purp.getValue()) : 0);

                this.current.setPay_type(Integer.parseInt(this.operation_type.getValue()));
                this.current.setTrans_type(Integer.parseInt(
                        (CheckNull.isEmpty(this.suboperation_type.getValue())) ? "-1" : this.suboperation_type.getValue()));

                this.current.setPerc_for_tr(Double.parseDouble(this.perc_for_trans.getValue()));
                this.current.setPdc(this.pdc.getValue());
                if (!single_record)
                    this.current.setAmount(amount.getValue());
                res1 = TrTemplateService.update(this.current, this.alias);

                if (single_record) {
                    alert("Настройка сохранена.");
                }

                if (res1.getCode() != 0) {
                    alert(res1.getName());
                } else {
                    moperation_id.setDisabled(false);
                }

            }

            refreshModel(this._startPageNumber);
            if (!single_record) {
                onClick$btn_cancel();
            }
        } catch (Exception e) {
            com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
            Messagebox.show(CheckNull.getPstr(e));
        }
    }

    public void onClick$btn_cancel() {
        if (this.fgrd.isVisible()) {
            this.filter = new TrTemplateFilter();
        }

        CheckNull.clearForm(this.addgrdl);
        CheckNull.clearForm(this.addgrdr);
        CheckNull.clearForm(this.fgrd);

        //CheckNull.clearForm(frmgrdl);
        //CheckNull.clearForm(frmgrdr);


        this.frm.setVisible(false);
        this.addgrd.setVisible(false);
        this.fgrd.setVisible(false);
        this.grd.setVisible(true);
        int curidx = dataGrid.getSelectedIndex();
        refreshModel(this._startPageNumber);
        dataGrid.setSelectedIndex(curidx);
        sendSelEvt();
    }

    public void onSelect$moperation_id(Event evt) {
        this.curroperation_id = Integer.parseInt(this.moperation_id.getValue());
        this.operation_id.setValue(this.moperation_id.getText());
        this.filter.setOperation_id(this.curroperation_id);
        refreshModel(this._startPageNumber);
    }
}