// 
// Decompiled by Procyon v0.5.36
// 

package com.is.tieto_globuz.customer;

import java.util.List;
import org.zkoss.zkplus.databind.BindingListModelList;
import com.is.LtLogger;
import globus.issuing_v_01_02_xsd.OperationResponseInfo;
import java.rmi.RemoteException;
import globus.IssuingWS.IssuingPortProxy;
import com.is.tieto_globuz.Utils;
import globus.issuing_v_01_02_xsd.holders.ListType_CustomerCustomInfoHolder;
import globus.issuing_v_01_02_xsd.holders.RowType_CustomerHolder;
import java.util.Calendar;
import globus.issuing_v_01_02_xsd.RowType_Customer;
import globus.issuing_v_01_02_xsd.OperationConnectionInfo;
import com.is.utils.Res;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.util.resource.Labels;
import com.is.utils.CheckNull;
import org.zkoss.zul.event.PagingEvent;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zul.ListModel;
import java.util.Collection;
import com.is.utils.RefDataService;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zk.ui.Component;
import com.is.tieto_globuz.tieto.Tclient;
import java.text.SimpleDateFormat;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Datebox;
import com.is.utils.RefCBox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Window;
import org.zkoss.zk.ui.util.GenericForwardComposer;

public class CustomerViewCtrl extends GenericForwardComposer
{
    private static final long serialVersionUID = 1L;
    private Window tmpcl;
    private Div frm;
    private Listbox dataGrid;
    private Listbox tmpcl$tmpgrid;
    private Listbox tmpcl$tmptgrid;
    private Div grd;
    private Grid fgrdl;
    private Grid fgrdr;
    private Grid addgrdl;
    private Grid addgrdr;
    private Hbox addgrd;
    private Hbox frmgrd;
    private Hbox fgrd;
    private Toolbarbutton btn_last;
    private Toolbarbutton btn_next;
    private Toolbarbutton btn_prev;
    private Toolbarbutton btn_first;
    private Toolbarbutton btn_back;
    private Toolbarbutton btn_card;
    private Textbox p_birth_place;
    private Textbox id;
    private Textbox branch;
    private Textbox id_client;
    private Textbox name;
    private Textbox code_country;
    private Textbox code_type;
    private Textbox code_resident;
    private Textbox code_subject;
    private Textbox sign_registr;
    private Textbox code_form;
    private Textbox date_open;
    private Textbox date_close;
    private Textbox state;
    private Textbox p_birthday;
    private Textbox p_post_address;
    private Textbox p_passport_serial;
    private Textbox p_passport_number;
    private Textbox p_passport_place_registration;
    private Textbox p_passport_date_registration;
    private Textbox p_number_tax_registration;
    private Textbox p_capacity_status_date;
    private Textbox p_capacity_status_place;
    private Textbox p_num_certif_capacity;
    private Textbox p_phone_home;
    private Textbox p_phone_mobile;
    private Textbox p_email_address;
    private Textbox p_pension_sertif_serial;
    private Textbox p_passport_date_expiration;
    private Textbox p_inps;
    private Textbox p_family;
    private Textbox p_first_name;
    private Textbox p_patronymic;
    private Textbox ap_birth_place;
    private Textbox aid;
    private Textbox abranch;
    private Textbox aid_client;
    private Textbox aname;
    private Textbox acode_subject;
    private Textbox asign_registr;
    private Textbox acode_form;
    private Textbox astate;
    private Textbox ap_post_address;
    private Textbox ap_passport_serial;
    private Textbox ap_passport_number;
    private Textbox ap_passport_place_registration;
    private Textbox ap_number_tax_registration;
    private Textbox ap_capacity_status_date;
    private Textbox ap_capacity_status_place;
    private Textbox ap_num_certif_capacity;
    private Textbox ap_phone_home;
    private Textbox ap_phone_mobile;
    private Textbox ap_email_address;
    private Textbox ap_pension_sertif_serial;
    private Textbox ap_inps;
    private Textbox ap_family;
    private Textbox ap_first_name;
    private Textbox ap_patronymic;
    private Textbox fid;
    private Textbox fbranch;
    private Textbox fid_client;
    private Textbox fname;
    private Textbox fcode_country;
    private Textbox fcode_type;
    private Textbox fcode_resident;
    private Textbox fcode_subject;
    private Textbox fsign_registr;
    private Textbox fcode_form;
    private Textbox fdate_open;
    private Textbox fdate_close;
    private Textbox fstate;
    private Textbox fp_post_address;
    private Textbox fp_passport_serial;
    private Textbox fp_passport_number;
    private Textbox fp_passport_place_registration;
    private Textbox fp_passport_date_registration;
    private Textbox fp_number_tax_registration;
    private Textbox fp_birth_place;
    private Textbox fp_capacity_status_date;
    private Textbox fp_capacity_status_place;
    private Textbox fp_num_certif_capacity;
    private Textbox fp_phone_home;
    private Textbox fp_phone_mobile;
    private Textbox fp_email_address;
    private Textbox fp_pension_sertif_serial;
    private Textbox fp_passport_date_expiration;
    private Textbox fp_inps;
    private Textbox fp_family;
    private Textbox fp_first_name;
    private Textbox fp_patronymic;
    private Paging customerPaging;
    private RefCBox p_passport_type;
    private RefCBox p_code_tax_org;
    private RefCBox p_code_bank;
    private RefCBox p_code_class_credit;
    private RefCBox p_code_citizenship;
    private RefCBox p_code_capacity;
    private RefCBox p_code_gender;
    private RefCBox p_code_nation;
    private RefCBox p_code_birth_region;
    private RefCBox p_code_birth_distr;
    private RefCBox p_type_document;
    private RefCBox p_code_adr_region;
    private RefCBox p_code_adr_distr;
    private RefCBox acode_resident;
    private RefCBox acode_type;
    private RefCBox acode_country;
    private RefCBox ap_passport_type;
    private RefCBox ap_code_tax_org;
    private RefCBox ap_code_bank;
    private RefCBox ap_code_class_credit;
    private RefCBox ap_code_citizenship;
    private RefCBox ap_code_capacity;
    private RefCBox ap_code_gender;
    private RefCBox ap_code_nation;
    private RefCBox ap_code_birth_region;
    private RefCBox ap_code_birth_distr;
    private RefCBox ap_type_document;
    private RefCBox ap_code_adr_region;
    private RefCBox ap_code_adr_distr;
    private RefCBox fp_passport_type;
    private RefCBox fp_code_tax_org;
    private RefCBox fp_code_bank;
    private RefCBox fp_code_class_credit;
    private RefCBox fp_code_citizenship;
    private RefCBox fp_code_capacity;
    private RefCBox fp_code_gender;
    private RefCBox fp_code_nation;
    private RefCBox fp_code_birth_region;
    private RefCBox fp_code_birth_distr;
    private RefCBox fp_type_document;
    private RefCBox fp_code_adr_region;
    private RefCBox fp_code_adr_distr;
    private Datebox adate_open;
    private Datebox adate_close;
    private Datebox ap_passport_date_registration;
    private Datebox ap_passport_date_expiration;
    private Datebox ap_birthday;
    private int _pageSize;
    private Datebox fp_birthday;
    private int _startPageNumber;
    private int _totalSize;
    private boolean _needsTotalSizeUpdate;
    public CustomerFilter filter;
    PagingListModel model;
    ListModelList lmodel;
    private AnnotateDataBinder binder;
    SimpleDateFormat df;
    private Customer current;
    private Customer tcustomer;
    private Tclient tietocl;
    private String alias;
    
    public CustomerViewCtrl() {
        super('$', false, false);
        this._pageSize = 20;
        this._startPageNumber = 0;
        this._totalSize = 0;
        this._needsTotalSizeUpdate = true;
        this.filter = new CustomerFilter();
        this.model = null;
        this.lmodel = null;
        this.df = new SimpleDateFormat("dd.MM.yyyy");
        this.current = new Customer();
        this.tcustomer = new Customer();
        this.tietocl = new Tclient();
    }
    
    public void doAfterCompose(final Component comp) throws Exception {
        super.doAfterCompose(comp);
        (this.binder = new AnnotateDataBinder(comp)).bindBean("current", (Object)this.current);
        this.binder.loadAll();
        final String[] parameter = (String[]) this.param.get("ht");
        this.alias = (String)this.session.getAttribute("alias");
        if (parameter != null) {
            this._pageSize = Integer.parseInt(parameter[0]) / 36;
            this.dataGrid.setRows(Integer.parseInt(parameter[0]) / 36);
        }
        this.dataGrid.setItemRenderer((ListitemRenderer)new ListitemRenderer() {
            public void render(final Listitem row, final Object data) throws Exception {
                final Customer pCustomer = (Customer)data;
                row.setValue((Object)pCustomer);
                row.appendChild((Component)new Listcell(pCustomer.getId_client()));
                row.appendChild((Component)new Listcell(pCustomer.getName()));
                row.appendChild((Component)new Listcell(pCustomer.getP_post_address()));
            }
        });
        this.tmpcl$tmpgrid.setItemRenderer((ListitemRenderer)new ListitemRenderer() {
            public void render(final Listitem row, final Object data) throws Exception {
                final Customer pCustomer = (Customer)data;
                row.setValue((Object)pCustomer);
                row.appendChild((Component)new Listcell(pCustomer.getId_client()));
                row.appendChild((Component)new Listcell(pCustomer.getName()));
            }
        });
        this.tmpcl$tmptgrid.setItemRenderer((ListitemRenderer)new ListitemRenderer() {
            public void render(final Listitem row, final Object data) throws Exception {
                final Tclient pTclient = (Tclient)data;
                row.setValue((Object)pTclient);
                row.appendChild((Component)new Listcell((pTclient.getClient_b() != null) ? pTclient.getClient_b() : "--//--"));
                row.appendChild((Component)new Listcell(pTclient.getF_names()));
                row.appendChild((Component)new Listcell(pTclient.getSurname()));
            }
        });
        this.p_code_tax_org.setModel((ListModel)new ListModelList((Collection)RefDataService.getTax(this.alias)));
        this.p_code_bank.setModel((ListModel)new ListModelList((Collection)CustomerService.getMfo(this.alias)));
        this.p_code_class_credit.setModel((ListModel)new ListModelList((Collection)RefDataService.getClassCR(this.alias)));
        this.p_code_citizenship.setModel((ListModel)new ListModelList((Collection)RefDataService.getCountry(this.alias)));
        this.p_code_gender.setModel((ListModel)new ListModelList((Collection)RefDataService.getGender(this.alias)));
        this.p_code_nation.setModel((ListModel)new ListModelList((Collection)RefDataService.getNation(this.alias)));
        this.p_code_birth_region.setModel((ListModel)new ListModelList((Collection)RefDataService.getRegion(this.alias)));
        this.p_code_birth_distr.setModel((ListModel)new ListModelList((Collection)RefDataService.getDistr(this.alias)));
        this.p_type_document.setModel((ListModel)new ListModelList((Collection)RefDataService.getType_document(this.alias)));
        this.p_code_adr_region.setModel((ListModel)new ListModelList((Collection)RefDataService.getRegion(this.alias)));
        this.p_code_adr_distr.setModel((ListModel)new ListModelList((Collection)RefDataService.getDistr(this.alias)));
        this.ap_type_document.setModel((ListModel)new ListModelList((Collection)RefDataService.getType_document(this.alias)));
        this.ap_code_tax_org.setModel((ListModel)new ListModelList((Collection)RefDataService.getTax(this.alias)));
        this.ap_code_citizenship.setModel((ListModel)new ListModelList((Collection)RefDataService.getCountry(this.alias)));
        this.acode_country.setModel((ListModel)new ListModelList((Collection)RefDataService.getCountry(this.alias)));
        this.ap_code_gender.setModel((ListModel)new ListModelList((Collection)RefDataService.getGender(this.alias)));
        this.ap_code_nation.setModel((ListModel)new ListModelList((Collection)RefDataService.getNation(this.alias)));
        this.ap_code_birth_region.setModel((ListModel)new ListModelList((Collection)RefDataService.getRegion(this.alias)));
        this.ap_code_birth_distr.setModel((ListModel)new ListModelList((Collection)RefDataService.getDistr(this.alias)));
        this.ap_code_adr_region.setModel((ListModel)new ListModelList((Collection)RefDataService.getRegion(this.alias)));
        this.ap_code_adr_distr.setModel((ListModel)new ListModelList((Collection)RefDataService.getDistr(this.alias)));
        this.acode_resident.setModel((ListModel)new ListModelList((Collection)RefDataService.getRezCl(this.alias)));
        this.fp_code_tax_org.setModel((ListModel)new ListModelList((Collection)RefDataService.getTax(this.alias)));
        this.fp_code_citizenship.setModel((ListModel)new ListModelList((Collection)RefDataService.getCountry(this.alias)));
        this.fp_code_gender.setModel((ListModel)new ListModelList((Collection)RefDataService.getGender(this.alias)));
        this.fp_code_nation.setModel((ListModel)new ListModelList((Collection)RefDataService.getNation(this.alias)));
        this.fp_code_birth_region.setModel((ListModel)new ListModelList((Collection)RefDataService.getRegion(this.alias)));
        this.fp_code_birth_distr.setModel((ListModel)new ListModelList((Collection)RefDataService.getDistr(this.alias)));
        this.fp_type_document.setModel((ListModel)new ListModelList((Collection)RefDataService.getType_document(this.alias)));
        this.fp_code_adr_region.setModel((ListModel)new ListModelList((Collection)RefDataService.getRegion(this.alias)));
        this.fp_code_adr_distr.setModel((ListModel)new ListModelList((Collection)RefDataService.getDistr(this.alias)));
    }
    
    public void first() {
        this.onClick$btn_search();
        (this.filter = new CustomerFilter()).setName("");
        this.filter.setBranch((String)this.session.getAttribute("branch_filter"));
        if (this.session.getAttribute("alias_filter") != null) {
            this.alias = (String)this.session.getAttribute("alias_filter");
        }
        this.filter.setP_post_address(this.fp_post_address.getValue());
        this.filter.setP_passport_serial(this.fp_passport_serial.getValue());
        this.filter.setP_passport_number(this.fp_passport_number.getValue());
        this.filter.setP_phone_mobile(this.fp_phone_mobile.getValue());
        this.filter.setP_email_address(this.fp_email_address.getValue());
        this.filter.setP_family(this.fp_family.getValue());
        this.filter.setP_first_name(this.fp_first_name.getValue());
        this.filter.setP_patronymic(this.fp_patronymic.getValue());
        this.onClick$btn_save();
        this.refreshModel(this._startPageNumber);
    }
    
    public void onPaging$customerPaging(final ForwardEvent event) {
        final PagingEvent pe = (PagingEvent)event.getOrigin();
        this.refreshModel(this._startPageNumber = pe.getActivePage());
    }
    
    private void refreshModel(final int activePage) {
        this.customerPaging.setPageSize(this._pageSize);
        this.filter.setBranch((String)this.session.getAttribute("branch_filter"));
        if (this.session.getAttribute("alias_filter") != null) {
            if (this.alias.compareTo((String)this.session.getAttribute("alias_filter")) != 0) {
                CheckNull.clearForm(this.fgrdl);
                CheckNull.clearForm(this.fgrdr);
                (this.filter = new CustomerFilter()).setBranch((String)this.session.getAttribute("branch_filter"));
            }
            this.alias = (String)this.session.getAttribute("alias_filter");
        }
        this.model = new PagingListModel(activePage, this._pageSize, this.filter, this.alias);
        if (this._needsTotalSizeUpdate) {
            this._totalSize = this.model.getTotalSize(this.filter, this.alias);
        }
        this.customerPaging.setTotalSize(this._totalSize);
        this.dataGrid.setModel((ListModel)this.model);
        if (this.model.getSize() > 0) {
            this.dataGrid.setSelectedIndex(0);
            this.sendSelEvt();
        }
    }
    
    public Customer getCurrent() {
        return this.current;
    }
    
    public void setCurrent(final Customer current) {
        this.current = current;
    }
    
    public Customer getTcustomer() {
        return this.tcustomer;
    }
    
    public void setTcustomer(final Customer tcustomer) {
        this.tcustomer = tcustomer;
    }
    
    public Tclient getTietocl() {
        return this.tietocl;
    }
    
    public void setTietocl(final Tclient tietocl) {
        this.tietocl = tietocl;
    }
    
    public void onDoubleClick$dataGrid$grd() {
        this.grd.setVisible(false);
        this.frm.setVisible(true);
        this.frmgrd.setVisible(true);
        this.addgrd.setVisible(false);
        this.fgrd.setVisible(false);
        this.btn_back.setImage("/images/folder.png");
        this.btn_back.setLabel(Labels.getLabel("grid"));
        this.btn_card.setVisible(this.frmgrd.isVisible());
    }
    
    public void onClick$btn_back() {
        if (this.frm.isVisible()) {
            this.frm.setVisible(false);
            this.grd.setVisible(true);
            this.btn_back.setImage("/images/file.png");
            this.btn_back.setLabel(Labels.getLabel("back"));
            this.btn_card.setVisible(this.frm.isVisible());
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
        final SelectEvent evt = new SelectEvent("onSelect", (Component)this.dataGrid, this.dataGrid.getSelectedItems());
        Events.sendEvent((Event)evt);
    }
    
    public void onClick$btn_add() {
        this.tmpcl.setVisible(false);
        this.onDoubleClick$dataGrid$grd();
        this.frmgrd.setVisible(false);
        this.fgrd.setVisible(false);
        this.addgrd.setVisible(true);
        this.tcustomer = new Customer();
        this.btn_card.setVisible(this.frmgrd.isVisible());
    }
    
    public void onClick$btn_search() {
        this.onDoubleClick$dataGrid$grd();
        this.frmgrd.setVisible(false);
        this.addgrd.setVisible(false);
        this.fgrd.setVisible(true);
        this.btn_card.setVisible(this.frmgrd.isVisible());
    }
    
    public void onClick$btn_save() {
        try {
            if (this.addgrd.isVisible()) {
                this.tcustomer.setName(String.valueOf(this.ap_family.getValue()) + " " + this.ap_first_name.getValue() + " " + this.ap_patronymic.getValue());
                boolean fl_err = false;
                String err = "";
                if (!this.ap_passport_number.getValue().matches("[a-zA-Z0-9]+") || this.ap_passport_number.getValue().length() > 9) {
                    fl_err = true;
                    err = String.valueOf(err) + "\n\u041d\u043e\u043c\u0435\u0440 \u043f\u0430\u0441\u043f\u043e\u0440\u0442\u0430";
                }
                if (!this.ap_passport_serial.getValue().matches("[a-zA-Z0-9]+") || this.ap_passport_serial.getValue().length() > 9) {
                    fl_err = true;
                    err = String.valueOf(err) + "\n\u0421\u0435\u0440\u0438\u044f \u043f\u0430\u0441\u043f\u043e\u0440\u0442\u0430";
                }
                if (!this.ap_passport_place_registration.getValue().matches("[a-zA-Z0-9\\s\\.\\,_\\/-]+") || this.ap_passport_place_registration.getValue().length() > 200) {
                    fl_err = true;
                    err = String.valueOf(err) + "\n\u041c\u0435\u0441\u0442\u043e \u0440\u0435\u0433\u0438\u0441\u0442\u0440\u0430\u0446\u0438\u0438 \u043f\u0430\u0441\u043f\u043e\u0440\u0442\u0430";
                }
                if (!this.ap_family.getValue().matches("[a-zA-Z0-9]+") || this.ap_family.getValue().length() > 34) {
                    fl_err = true;
                    err = String.valueOf(err) + "\n\u0424\u0430\u043c\u0438\u043b\u0438\u044f";
                }
                if (!this.ap_first_name.getValue().matches("[a-zA-Z0-9]+") || this.ap_first_name.getValue().length() > 20) {
                    fl_err = true;
                    err = String.valueOf(err) + "\n\u0418\u043c\u044f";
                }
                if (!this.ap_patronymic.getValue().matches("[a-zA-Z0-9]*") || this.ap_patronymic.getValue().length() > 20) {
                    fl_err = true;
                    err = String.valueOf(err) + "\n\u041e\u0442\u0447\u0435\u0441\u0442\u0432\u043e";
                }
                if (CheckNull.isEmpty(this.ap_type_document.getValue())) {
                    fl_err = true;
                    err = String.valueOf(err) + "\n\u0422\u0438\u043f \u0434\u043e\u043a\u0443\u043c\u0435\u043d\u0442\u0430";
                }
                if (!this.ap_number_tax_registration.getValue().matches("[0-9]*") || this.ap_number_tax_registration.getValue().length() > 9) {
                    fl_err = true;
                    err = String.valueOf(err) + "\n\u0418\u041d\u041d";
                }
                if (CheckNull.isEmpty(this.ap_code_citizenship.getValue())) {
                    fl_err = true;
                    err = String.valueOf(err) + "\n\u0413\u0440\u0430\u0436\u0434\u0430\u043d\u0441\u0442\u0432\u043e";
                }
                if (CheckNull.isEmpty(this.acode_country.getValue())) {
                    fl_err = true;
                    err = String.valueOf(err) + "\n\u0421\u0442\u0440\u0430\u043d\u0430";
                }
                if (CheckNull.isEmpty(this.acode_resident.getValue())) {
                    fl_err = true;
                    err = String.valueOf(err) + "\n\u0420\u0435\u0437\u0438\u0434\u0435\u043d\u0442";
                }
                if (CheckNull.isEmpty(this.ap_passport_date_registration.getValue())) {
                    fl_err = true;
                    err = String.valueOf(err) + "\n\u0414\u0430\u0442\u0430 \u0440\u0435\u0433\u0438\u0441\u0442\u0440\u0430\u0446\u0438\u0438 \u043f\u0430\u0441\u043f\u043e\u0440\u0442\u0430";
                }
                if (CheckNull.isEmpty(this.ap_birthday.getValue())) {
                    fl_err = true;
                    err = String.valueOf(err) + "\n\u0414\u0430\u0442\u0430 \u0440\u043e\u0436\u0434\u0435\u043d\u0438\u044f";
                }
                if (!this.ap_post_address.getValue().matches("[a-zA-Z0-9\\s\\.\\,_\\/-]+") || this.ap_post_address.getValue().length() > 95) {
                    fl_err = true;
                    err = String.valueOf(err) + "\n\u041f\u043e\u0447\u0442\u043e\u0432\u044b\u0439 \u0430\u0434\u0440\u0435\u0441";
                }
                if (!this.ap_birth_place.getValue().matches("[a-zA-Z0-9\\s\\.\\,_\\/-]*") || this.ap_birth_place.getValue().length() > 200) {
                    fl_err = true;
                    err = String.valueOf(err) + "\n\u041c\u0435\u0441\u0442\u043e \u0440\u043e\u0436\u0434\u0435\u043d\u0438\u044f";
                }
                if (fl_err) {
                    this.alert("\u041e\u0448\u0438\u0431\u043a\u0430 \u0437\u0430\u043f\u043e\u043b\u043d\u0435\u043d\u0438\u044f \u0444\u043e\u0440\u043c\u044b:\n\u043d\u0435\u0432\u0435\u0440\u043d\u043e \u0437\u0430\u043f\u043e\u043b\u043d\u0435\u043d\u043e \u043f\u043e\u043b\u0435 " + err);
                    return;
                }
                final Customer new_customer = new Customer();
                new_customer.setP_passport_number(this.ap_passport_number.getValue());
                new_customer.setP_passport_type(this.ap_type_document.getValue());
                new_customer.setP_type_document(this.ap_type_document.getValue());
                new_customer.setP_passport_serial(this.ap_passport_serial.getValue());
                new_customer.setP_passport_place_registration(this.ap_passport_place_registration.getValue());
                new_customer.setP_family(this.ap_family.getValue());
                new_customer.setP_first_name(this.ap_first_name.getValue());
                new_customer.setName(String.valueOf(this.ap_family.getValue()) + " " + this.ap_first_name.getValue() + " " + this.ap_patronymic.getValue());
                new_customer.setP_birthday(this.ap_birthday.getValue());
                new_customer.setCode_country(this.acode_country.getValue());
                new_customer.setCode_resident(this.acode_resident.getValue());
                new_customer.setP_post_address(this.ap_post_address.getValue());
                new_customer.setP_patronymic(this.ap_patronymic.getValue());
                new_customer.setP_passport_date_expiration(this.ap_passport_date_expiration.getValue());
                new_customer.setP_passport_date_registration(this.ap_passport_date_registration.getValue());
                new_customer.setP_code_birth_region(CheckNull.isEmpty(this.ap_code_birth_region.getValue()) ? null : this.ap_code_birth_region.getValue());
                new_customer.setP_code_birth_distr(this.ap_code_birth_distr.getValue());
                new_customer.setP_birth_place(this.ap_birth_place.getValue());
                new_customer.setP_code_gender(this.ap_code_gender.getValue());
                new_customer.setP_code_nation(this.ap_code_nation.getValue());
                new_customer.setP_code_adr_region(this.ap_code_adr_region.getValue());
                new_customer.setP_code_adr_distr(this.ap_code_adr_distr.getValue());
                new_customer.setP_code_tax_org(this.ap_code_tax_org.getValue());
                new_customer.setP_number_tax_registration(this.ap_number_tax_registration.getValue());
                new_customer.setP_code_citizenship(this.ap_code_citizenship.getValue());
                new_customer.setP_phone_mobile(this.ap_phone_mobile.getValue());
                new_customer.setP_email_address(this.ap_email_address.getValue());
                new_customer.setP_phone_home(this.ap_phone_home.getValue());
                new_customer.setP_inps(this.ap_inps.getValue());
                this.filter.setBranch((String)this.session.getAttribute("branch_filter"));
                new_customer.setBranch((String)this.session.getAttribute("branch_filter"));
                new_customer.setP_code_bank((String)this.session.getAttribute("branch_filter"));
                new_customer.setP_code_class_credit("1");
                new_customer.setP_passport_type("N");
                new_customer.setCode_subject("P");
                new_customer.setSign_registr(2);
                new_customer.setCode_form("");
                new_customer.setCode_type("08");
                final Res res = CustomerService.doAction(this.session.getAttribute("un").toString(), this.session.getAttribute("pwd").toString(), new_customer, 1, 2, (String)this.session.getAttribute("alias_filter"), ((String)this.session.getAttribute("branch_filter")).compareTo((String)this.session.getAttribute("branch")) == 0);
                if (res.getCode() != 0) {
                    this.alert(res.getName());
                }
                else {
                    this.refreshModel(this._startPageNumber);
                    this.onClick$btn_back();
                }
            }
            else if (this.fgrd.isVisible()) {
                (this.filter = new CustomerFilter()).setBranch((String)this.session.getAttribute("branch_filter"));
                if (this.session.getAttribute("alias_filter") != null) {
                    this.alias = (String)this.session.getAttribute("alias_filter");
                }
                this.filter.setP_birthday(this.fp_birthday.getValue());
                this.filter.setP_passport_serial(this.fp_passport_serial.getValue());
                this.filter.setP_passport_number(this.fp_passport_number.getValue());
                this.filter.setP_family(this.fp_family.getValue());
                this.filter.setP_first_name(this.fp_first_name.getValue());
                this.filter.setP_patronymic(this.fp_patronymic.getValue());
            }
            this.onClick$btn_back();
            this.refreshModel(this._startPageNumber);
            final SelectEvent evt = new SelectEvent("onSelect", (Component)this.dataGrid, this.dataGrid.getSelectedItems());
            Events.sendEvent((Event)evt);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void onClick$btn_cancel() {
        if (this.fgrd.isVisible()) {
            (this.filter = new CustomerFilter()).setBranch((String)this.session.getAttribute("branch_filter"));
            if (this.session.getAttribute("alias_filter") != null) {
                this.alias = (String)this.session.getAttribute("alias_filter");
            }
            System.out.println("alias:" + this.alias);
        }
        this.onClick$btn_back();
        this.frmgrd.setVisible(true);
        this.addgrd.setVisible(false);
        this.fgrd.setVisible(false);
        CheckNull.clearForm(this.addgrdl);
        CheckNull.clearForm(this.addgrdr);
        CheckNull.clearForm(this.fgrdl);
        CheckNull.clearForm(this.fgrdr);
        this.refreshModel(this._startPageNumber);
    }
    
    public void onClick$btn_tst() {
        final OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
        OperationResponseInfo orInfo = null;
        final RowType_Customer rtc = new RowType_Customer();
        rtc.setF_NAMES(this.current.getP_first_name());
        rtc.setCL_TYPE("1");
        rtc.setCLIENT_B(this.current.getId_client());
        rtc.setSURNAME(this.current.getP_family());
        rtc.setM_NAME(this.current.getP_patronymic());
        final Calendar cal = Calendar.getInstance();
        cal.setTime(this.current.getP_passport_date_registration());
        rtc.setDOC_SINCE(cal);
        cal.setTime(this.current.getP_birthday());
        rtc.setB_DATE(cal);
        rtc.setRESIDENT("1");
        rtc.setSTATUS("10");
        rtc.setSEX("1");
        final RowType_CustomerHolder customerInfo = new RowType_CustomerHolder(rtc);
        final ListType_CustomerCustomInfoHolder customListInfo = new ListType_CustomerCustomInfoHolder();
        final IssuingPortProxy pp = new IssuingPortProxy(Utils.getValue("EMPC_TIETO_HOST"));
        try {
            connectionInfo.setBANK_C("01");
            connectionInfo.setGROUPC("01");
            orInfo = pp.newCustomer(connectionInfo, customerInfo, customListInfo);
            this.alert(String.valueOf(orInfo.getError_action()) + "\r\n" + orInfo.getError_description());
            System.out.println("resp " + orInfo.getResponse_code() + "  client " + customerInfo.value.getCLIENT());
        }
        catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    
    public void onSelect$ap_code_gender(final Event evt) {
    }
    
    public void onSelect$ap_code_birth_region(final Event evt) {
        this.ap_code_birth_distr.setSelectedIndex(-1);
        this.ap_code_birth_distr.setModel((ListModel)new ListModelList((Collection)RefDataService.getDistr(this.ap_code_birth_region.getValue())));
    }
    
    public void onSelect$ap_code_adr_region(final Event evt) {
        this.ap_code_adr_distr.setSelectedIndex(-1);
        this.ap_code_adr_distr.setModel((ListModel)new ListModelList((Collection)RefDataService.getDistr(this.ap_code_adr_region.getValue())));
    }
    
    public void onChange$ap_passport_number(final Event evt) {
        LtLogger.getLogger().info((Object)this.ap_passport_number.getValue());
        final List<Customer> clist = CustomerService.getCustomer(this.ap_passport_number.getValue(), this.alias);
        if (clist.size() > 0) {
            try {
                this.tmpcl$tmpgrid.setModel((ListModel)new BindingListModelList((List)clist, false));
            }
            catch (Exception ex) {}
        }
        this.tmpcl.setVisible(true);
    }
    
    public void onDoubleClick$tmpgrid$tmpcl() {
        this.tmpcl.setVisible(false);
    }
    
    public void onFocus$customermain() {
        this.refreshModel(this._startPageNumber);
    }
}
