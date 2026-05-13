package com.is.tieto_visae.tieto;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Decimalbox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Include;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;

import com.is.tieto_visae.customer.Customer;
import com.is.tieto_visae.customer.CustomerFilter;
import com.is.utils.CheckNull;
import com.is.utils.NilProvider;
import com.is.utils.RefCBox;

public class TclientViewCtrl extends GenericForwardComposer {
	private visa.IssuingWS.IssuingPortProxy issuingPortProxy;
	private static NilProvider np = null;
	private GeneralInfo generalInfo = new GeneralInfo();
	private Window paywnd, blockwnd, printwnd, addwnd;
	private Iframe printwnd$rpframe;
	private Decimalbox paywnd$amnt;
	private Div frm;
	private Listbox dataGrid, accGrid, paywnd$payGrid;
	private Label paywnd$add_currency_type;
	private Div grd;
	private Grid addgrd, frmgrd, fgrd;
	private Grid lfrmgrd, mfrmgrd, rfrmgrd;
	private Toolbarbutton paywnd$btn_block, paywnd$btn_unblock;
	private Toolbarbutton paywnd$fbt, paywnd$lock, paywnd$application;
	private Toolbar paywnd$pay_tlb;
	private Textbox client, bank_c, client_b, cl_type, cln_cat, rec_date, f_names, surname, title, m_name, b_date,
			r_street, r_city, r_cntry, usrid, ctime, status, search_name, sex, serial_no, doc_since, issued_by,
			status_change_date, blockwnd$txtstopcauses, paywnd$search_name, paywnd$address;
	private Textbox aclient, abank_c, aclient_b, acl_type, acln_cat, arec_date, af_names, asurname, atitle, am_name,
			ab_date, ar_street, ar_city, ar_cntry, ausrid, actime, astatus, asearch_name, asex, aserial_no, adoc_since,
			aissued_by, astatus_change_date, paywnd$curacc;
	private Textbox fclient, fbank_c, fclient_b, fcl_type, fcln_cat, ff_names, fsurname, ftitle, fm_name, fr_street,
			fr_city, fr_cntry, fusrid, fctime, fstatus, fsearch_name, fsex, fserial_no, fdoc_since, fissued_by,
			fstatus_change_date, fcard, paywnd$inc_ord_num;
	private Paging tclientPaging;
	private Datebox fb_date, frec_date;
	private RefCBox paywnd$scurracc, blockwnd$sstopcauses, addwnd$sproduct, paywnd$list_amnt;
	private int _pageSize = 10;
	private int _startPageNumber = 0;
	private int _totalSize = 0;
	private boolean _needsTotalSizeUpdate = true;
	private String un, pwd, alias;
	private int uid;
	private Tclient current = new Tclient();
	private Customer customer = new Customer();
	private CardInfo cardinfo = new CardInfo();
	private CustomerFilter customerFilter = new CustomerFilter();
	private AccInfo accinfo = new AccInfo();
	
    private Tabbox mainTabbox;
    private Tab mainTab, confirmTab;

	private com.is.tieto_visae.customer.PagingListModel CustPagingListModel;

	PaginListModel model = null;
	ListModelList lmodel = null;
	private AnnotateDataBinder binder;
	private SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy", new Locale("ru"));
	private SimpleDateFormat expdf = new SimpleDateFormat("yyyy-MM-dd", new Locale("ru"));

	private DecimalFormat dmf = new DecimalFormat("0.00##");
	private visa.IssuingWS.IssuingPortProxy pp = null;
	private static HashMap<String, String> _tstopCauses = new HashMap<String, String>();

	private int pay_card_doc_id;
	private String curip, cur_act, CRM_client = null;
	private NumberFormat nf = NumberFormat.getInstance();
	private String VISA_BANK_C, VISA_GROUPC, VISA_BINCOD;

	private Tabbox resultBox;
	private Tabpanel customersTab;
    private Window confirmWnd, visaAddWnd;
    private Div confirmDiv;
    private Include includeVisaAdd;
	private Tabpanel accountsTab;

	public Tabpanel getAccountsTab() {
		return accountsTab;
	}

	public void setAccountsTab(Tabpanel accountsTab) {
		this.accountsTab = accountsTab;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public Tabpanel getCardsTab() {
		return cardsTab;
	}

	public void setCardsTab(Tabpanel cardsTab) {
		this.cardsTab = cardsTab;
	}

	private String branch = null;
	private Tabpanel cardsTab;

	private Grid searchGrid;
	private Window customerWindow;

	public ListCustomersFilter getFilter() {
		return filter;
	}

	public void setFilter(ListCustomersFilter filter) {
		this.filter = filter;
	}

	public ListCustomersItem getCurrentCustomer() {
		return currentCustomer;
	}

	public void setCurrentCustomer(ListCustomersItem currentCustomer) {
		this.currentCustomer = currentCustomer;
	}

	public ListAccountsItem getCurrentAccount() {
		return currentAccount;
	}

	public void setCurrentAccount(ListAccountsItem currentAccount) {
		this.currentAccount = currentAccount;
	}

	public CardInfo getCurrentCard() {
		return currentCard;
	}

	public void setCurrentCard(CardInfo currentCard) {
		this.currentCard = currentCard;
	}

	private ListCustomersFilter filter = new ListCustomersFilter();

	private ListCustomersItem currentCustomer = new ListCustomersItem();

	private ListAccountsItem currentAccount = new ListAccountsItem();

	private CardInfo currentCard = new CardInfo();

	public TclientViewCtrl() {
		super('$', false, false);
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);

		branch = (String) session.getAttribute("branch");

		String[] parameter = (String[]) param.get("search_clients");
		if (parameter != null) {
			System.out.println("CRM_client=" + parameter[0]);
			filter.setClient_b(parameter[0]);
			System.out.println(filter.getClient_b());
			CRM_client = parameter[0];
		}
		
		AnnotateDataBinder binder = new AnnotateDataBinder(comp);
		binder.bindBean("filter", this.filter);
		binder.loadAll();
	}

	public void onClick$btnSearch() {
		if (customerWindow == null) {
			customerWindow = (Window) Executions.createComponents(Constants.CUSTOMERS_ZUL, customersTab, null);
		}
		CustomirViewCtrl customerVC = (CustomirViewCtrl) customerWindow.getAttribute("customermain$composer");
		customerVC.setFilter(filter);
		customerVC.setMain(this);
		customerVC.search();

		resultBox.setSelectedIndex(0);
	}

	public void onClick$btnCancel() {
		CheckNull.clearForm(searchGrid);
	}

	public void setTab(int i) {
		resultBox.setSelectedIndex(i);
	}
	
    public void onSelect$mainTabbox(SelectEvent event) throws InterruptedException {
        Tab component = (Tab) event.getTarget();
        if (component.getId().equalsIgnoreCase("mainTab")) {
            //internalControlInclude.setVisible(true);
            //internalControlInclude.setSrc(null);
            //internalControlInclude.setSrc(
            //        "clientaddinfo.zul?branch=" + composer.getCustomer().getBranch() +
            //                "&client_id=" + composer.getCustomer().getId() +
            //                "&code_subject=P&alias=" + sessionAttributes.getSchema());
        } else if (component.getId().equalsIgnoreCase("confirmTab")) {
            //onClick$btn_getFile();
            confirmDiv.getChildren().clear();
            confirmWnd =
                    (Window) Executions.createComponents("VisaConfirmRefille.zul",
                            confirmDiv, null);
            //confirmWnd.setClosable(true);
            confirmWnd.setVisible(true);
            //Events.sendEvent("onUploadApps", confirmWnd, composer.getCustomer());
        } else if (component.getId().equalsIgnoreCase("visaAddTab")) {
            includeVisaAdd.setSrc("VisaAddCustomere.zul?search_clients=" + CRM_client + "&alias=" + alias);			
        }
    }

}
