// 
// Decompiled by Procyon v0.5.36
// 

package com.is.humo.service;

import java.io.IOException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.core.JsonParseException;
import java.io.InputStream;
import org.zkoss.util.media.Media;
import org.zkoss.zul.Filedownload;
import org.zkoss.util.media.AMedia;
import java.io.ByteArrayInputStream;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.ListModel;
import java.util.Collection;
import com.is.utils.CheckNull;
import org.zkoss.zul.event.PagingEvent;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.Component;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Div;
import org.zkoss.zk.ui.util.GenericForwardComposer;

public class HumoServiceViewCtrl extends GenericForwardComposer
{
    private static final long serialVersionUID = 1L;
    private Div frm;
    private Listbox cardHistoryDataGrid;
    private Div grd;
    private Grid addgrd;
    private Grid frmgrd;
    private Grid fgrd;
    private Toolbar tb;
    private Label cardBalance;
    private Button getCardHistory;
    private Button historyToExcel;
    private Datebox dateFrom;
    private Datebox dateTo;
    private Textbox cardNumber;
    private Textbox id;
    private Textbox prots_name;
    private Textbox descripsion;
    private Textbox state;
    private Textbox aid;
    private Textbox aprots_name;
    private Textbox adescripsion;
    private Textbox astate;
    private Textbox fid;
    private Textbox fprots_name;
    private Textbox fdescripsion;
    private Textbox fstate;
    private int _pageSize;
    private int _startPageNumber;
    private int _totalSize;
    private boolean _needsTotalSizeUpdate;
    private String un;
    public HumoServiceModelFilter filter;
    HumoService humoService;
    PagingListModel model;
    ListModelList lmodel;
    private AnnotateDataBinder binder;
    SimpleDateFormat sdf;
    DecimalFormat df;
    private HumoServiceModel current;
    List<RowsItem> items;
    String cardString;
    Date periodFrom;
    Date periodEnd;
    int i;
    
    public HumoServiceViewCtrl() {
        super('$', false, false);
        this._pageSize = 15;
        this._startPageNumber = 0;
        this._totalSize = 0;
        this._needsTotalSizeUpdate = true;
        this.humoService = new HumoService();
        this.model = null;
        this.lmodel = null;
        this.sdf = new SimpleDateFormat("yyyy-MM-dd");
        this.df = new DecimalFormat("### ### ###.##");
        this.current = new HumoServiceModel();
        this.items = new ArrayList<RowsItem>();
        this.cardString = null;
        this.i = 0;
    }
    
    public void doAfterCompose(final Component comp) throws Exception {
        super.doAfterCompose(comp);
        (this.binder = new AnnotateDataBinder(comp)).bindBean("current", (Object)this.current);
        this.binder.loadAll();
        this.un = (String)this.session.getAttribute("un");
        final String[] parameter = this.param.get("ht");
        if (parameter != null) {
            this._pageSize = Integer.parseInt(parameter[0]) / 36;
        }
        this.refreshModel(this._startPageNumber);
    }
    
    public void onPaging$humoservicePaging(final ForwardEvent event) {
        final PagingEvent pe = (PagingEvent)event.getOrigin();
        this.refreshModel(this._startPageNumber = pe.getActivePage());
    }
    
    private void refreshModel(final int activePage) {
        this.model = new PagingListModel(activePage, this._pageSize, this.filter, "");
        if (this._needsTotalSizeUpdate) {
            this._totalSize = this.model.getTotalSize();
            this._needsTotalSizeUpdate = false;
        }
        if (this.model.getSize() > 0) {
            this.current = (HumoServiceModel)this.model.getElementAt(0);
            this.sendSelEvt();
        }
    }
    
    public HumoServiceModel getCurrent() {
        return this.current;
    }
    
    public void setCurrent(final HumoServiceModel current) {
        this.current = current;
    }
    
    public void onDoubleClick$dataGrid$grd() {
        this.grd.setVisible(false);
        this.frm.setVisible(true);
        this.frmgrd.setVisible(true);
        this.addgrd.setVisible(false);
        this.fgrd.setVisible(false);
    }
    
    public void onClick$btn_back() {
        if (this.frm.isVisible()) {
            this.frm.setVisible(false);
            this.grd.setVisible(true);
        }
        else {
            this.onDoubleClick$dataGrid$grd();
        }
    }
    
    public void onClick$btn_first() {
        this.sendSelEvt();
    }
    
    public void onClick$btn_last() {
        this.sendSelEvt();
    }
    
    public void onClick$btn_prev() {
    }
    
    public void onClick$btn_next() {
    }
    
    private void sendSelEvt() {
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
                CheckNull.clearForm(this.addgrd);
                this.frmgrd.setVisible(true);
                this.addgrd.setVisible(false);
                this.fgrd.setVisible(false);
            }
            else {
                this.fgrd.isVisible();
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
            this.filter = new HumoServiceModelFilter();
        }
        this.onClick$btn_back();
        this.frmgrd.setVisible(true);
        this.addgrd.setVisible(false);
        this.fgrd.setVisible(false);
        CheckNull.clearForm(this.addgrd);
        CheckNull.clearForm(this.fgrd);
        this.refreshModel(this._startPageNumber);
    }
    
    public void onClick$getCardHistory() throws JsonParseException, JsonMappingException, IOException {
        this.cardString = this.cardNumber.getValue();
        this.periodFrom = this.dateFrom.getValue();
        this.periodEnd = this.dateTo.getValue();
        if (this.humoService.checkCurrentCard(this.cardString) == 0) {
            this.alert("\u041a\u0430\u0440\u0442\u0430 \u043e\u0442\u0441\u0443\u0442\u0441\u0442\u0432\u0443\u0435\u0442 \u0432 \u0431\u0430\u0437\u0435");
            return;
        }
        this.items = this.humoService.getCardHistory(this.cardString, this.sdf.format(this.periodFrom), this.sdf.format(this.periodEnd));
        if (this.items == null && this.items.size() < 0) {
            this.historyToExcel.setDisabled(true);
            this.cardHistoryDataGrid.setEmptyMessage("\u041d\u0435 \u0443\u0434\u0430\u043b\u043e\u0441\u044c \u043f\u043e\u043b\u0443\u0447\u0438\u0442\u044c \u0434\u0430\u043d\u043d\u044b\u0435");
            return;
        }
        this.historyToExcel.setDisabled(false);
        final ListModelList historyModelList = new ListModelList();
        historyModelList.addAll((Collection)this.items);
        final String balanceString = this.humoService.getCardBalance(this.cardString);
        if (balanceString != null) {
            this.cardBalance.setValue(String.format("\u0411\u0430\u043b\u0430\u043d\u0441: %,.2f", Double.parseDouble(balanceString) / 100.0));
        }
        else {
            this.cardBalance.setValue(String.format("\u0411\u0430\u043b\u0430\u043d\u0441: %s", "\u041e\u0448\u0438\u0431\u043a\u0430 \u043f\u043e\u043b\u0443\u0447\u0435\u043d\u0438\u044f \u0431\u0430\u043b\u0430\u043d\u0441\u0430"));
        }
        this.cardHistoryDataGrid.setModel((ListModel)historyModelList);
        this.cardHistoryDataGrid.setItemRenderer((ListitemRenderer)new ListitemRenderer() {
            public void render(final Listitem row, final Object data) throws Exception {
                final RowsItem history = (RowsItem)data;
                row.appendChild((Component)new Listcell(String.valueOf(++HumoServiceViewCtrl.this.i)));
                row.appendChild((Component)new Listcell((history.getTranType() == null) ? "" : history.getTranType()));
                row.appendChild((Component)new Listcell((history.getAccntCcy() == null) ? "" : history.getAccntCcy()));
                row.appendChild((Component)new Listcell((history.getTranAmt() == null) ? "" : String.format("%,.2f", Double.parseDouble(history.getTranAmt()))));
                row.appendChild((Component)new Listcell((history.getTranDateTime() == null) ? "" : history.getTranDateTime()));
                row.appendChild((Component)new Listcell((history.getTermId() == null) ? "" : history.getTermId().toString()));
                row.appendChild((Component)new Listcell((history.getAbvrName() == null) ? "" : history.getAbvrName().toString()));
                row.appendChild((Component)new Listcell((history.getRefNumber() == null) ? "" : history.getRefNumber().toString()));
            }
        });
        this.historyToExcel.addEventListener("onClick", (EventListener)new EventListener() {
            public void onEvent(final Event event) throws Exception {
                final InputStream mediais = new ByteArrayInputStream(HumoServiceViewCtrl.this.humoService.getHistoryExcel(HumoServiceViewCtrl.this.items, HumoServiceViewCtrl.this.cardNumber.getValue(), HumoServiceViewCtrl.this.sdf.format(HumoServiceViewCtrl.this.dateFrom.getValue()), HumoServiceViewCtrl.this.sdf.format(HumoServiceViewCtrl.this.dateTo.getValue())));
                final AMedia b = new AMedia(String.valueOf(String.format("%s_%s_%s", HumoServiceViewCtrl.this.cardString, HumoServiceViewCtrl.this.sdf.format(HumoServiceViewCtrl.this.periodFrom), HumoServiceViewCtrl.this.sdf.format(HumoServiceViewCtrl.this.periodEnd))) + ".xlsx", "xlsx", "application/xlsx", mediais);
                Filedownload.save((Media)b);
            }
        });
    }
}
