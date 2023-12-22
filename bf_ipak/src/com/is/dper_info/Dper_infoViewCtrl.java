package com.is.dper_info;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import javax.jws.soap.SOAPBinding.Style;
import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zkoss.json.JSONObject;
import org.zkoss.util.media.AMedia;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.EventQueue;
import org.zkoss.zk.ui.event.EventQueues;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.KeyEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Constraint;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Decimalbox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;
import org.zkoss.zul.event.PagingEvent;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.LtLogger;
import com.is.bpri.utils.Utils;
import com.is.clientcrm.ClientA;
import com.is.clientcrm.ClientAService;
import com.is.clientcrm.ClientAViewCtrl;

import com.is.dper_info.model.Account;
import com.is.dper_info.model.Oper_info;
import com.is.dper_info.model.PagingListModel;
import com.is.dper_info.model.dper_info;
import com.is.dper_info.model.dper_infoFilter;
import com.is.dper_info.render.DperBookRenderer;
import com.is.dper_info.render.DperInfoRenderer;
import com.is.dper_info.reports.ReportsController;
import com.is.dper_info.service.AccountService;
import com.is.dper_info.service.SprService;
import com.is.dper_info.service.SumsService;
import com.is.dper_info.service.TransactionsService;
import com.is.dper_info.service.dper_infoService;
import com.is.korona_pay.HandleOperationRequest;
import com.is.korona_pay.KoronaPayDBHelper;
import com.is.korona_pay.KoronaPayService;
import com.is.korona_pay.PrintOrders;
import com.is.userreport.RepTempl;
import com.is.userreport.RepTempl2;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.RefData;
import com.is.utils.Res;

//@SuppressWarnings("unused")
public class Dper_infoViewCtrl extends GenericForwardComposer {
	
	
	
	
	// ---------------------------
	// private Window cl;
	
	private Window cust_infoWindow,dper_infomain,wzoloto,fzoloto,wunion;
	private Div grd, dp_books,kontra;// cl,crm, 
	private Div dp_info, new_dp, filter_;
	private Listbox dataGrid, dper_booksInfo, ClientsToAddLBox;
	private Paging contactPaging;
	private Grid addgrd, infogrd, fgrd;
	// ----------------------------
	private Menuitem new_dper, show_dpers;
	// ----------------------------
	private Toolbar tb;
	private Toolbarbutton btn_last, btn_next, btn_prev, btn_first;
	private Toolbarbutton btn_last_doc, btn_next_doc, btn_prev_doc, btn_first_doc;
	private Toolbarbutton btn_details,btn_delete; // btn_filter,btn_adddp, btn_search;
	private Button set_data_crow;
	// addgrd dobavlenie----------------------------------------
	private Combobox orderbox;
	private Textbox kurs_val, akurs_val, abranch,  amtcn, aregion_offshor, aclient_name1,
			aclient_name2, aclient_name3, adoc_series, adoc_number, adoc_issue,
			aclient_i, apost_address, aclient_i2, aclient_i3, aclient_i4,
			aclient_i5, aclient_i6, aclient_i7, aclient_i8, aclient_i9,
			aclient_i10, aclient_i12, purpose_1, purpose_2, purpose_3,
			purpose_4, purpose_5, purpose_6, atotalsum, totalsum;
	//private Combobox aclient_i9;
	private Decimalbox asumma, asumma2, asumma3, asumma4, asumma5, aprofit, out_tmp;
	private Decimalbox acentsumma, acentsumma2,fcentsumma,centsumma;
	private RefCBox aveoper, akind, acurrency, astrs, astrr, adistr, arezident,
			astate, aclient_grstr, adoc_id, agroup_str, au1f2,
			aclient_i13code_str, aeval, acc_dep;
	private Datebox av_date, aclient_i11date;//, abirthday, adoc_date_issue, aclient_i11date,
			//adoc_date_exp;
	private Textbox  name_acc;
	private String clientId, aid;
	private Label sum2Label, sum3Label;
	// frmgrd prosmotr--------------------------------------
	private Textbox summa, profit, mtcn, region_offshor, acc_dep1,
			summa2, summa3, summa4, summa5, client_name1,
			client_name2, client_name3, doc_series, doc_number, doc_issue,
			client_i, post_address, client_i2, client_i3, client_i4, client_i5,
			client_i6, client_i7, client_i8, client_i9, client_i10, client_i12;
	private RefCBox veoper, mbranch, kind, currency, strs, strr, distr, eval,
			state;
	private RefCBox client_grstr, doc_id, group_str, u1f2, client_i13code_str;
	//private Datebox v_date, birthday, doc_date_issue, client_i11date;
	private RefCBox rezident;
	// fgrd filter--------------------------------------
	private Textbox fid, fregion_offshor, fclient, fclient_name1,
			fclient_name2, fclient_name3, fdoc_series, fdoc_number, fdoc_issue,
			fpost_address, fmtcn, fclient_i, fclient_i2, fclient_i3,
			fclient_i4, fclient_i5, fclient_i6, fclient_i7, fclient_i8,
			fclient_i9, fclient_i10, fclient_i12, fsumma, fsumma2, fsumma3,
			fsumma4, fsumma5, fprofit;

	private RefCBox fmbranch, fveoper, fkind, fstrs, fstrr, fdistr, fcurrency,
			fdoc_id, frezident, fstate, fu1f2, fclient_i13code_str,
			fclient_grstr;
	private Datebox fdoc_date_issue, fclient_i11date;
	//private Datebox fdate_begin, fdate_end, fbirthday;

	// -------------------------------
	private Label branchLabel;
	private Label mbranchLabel;
	private RefCBox actions, actionsPackage;

	private Paging dper_infoPaging;
	private int _pageSize = 15;
	private int _startPageNumber = 0;
	private int _totalSize = 0;
	//private PagingListModel<dper_info> model = null;
	private PagingListModel model = null;
	ListModelList lmodel = null;
	/////////////////////
	private ReportsController reportsController; 
	private Window reports_page;
	/////////////////////
	private String alias;
	public static String un;
	public static String pw;
	private String acc;
	private String mbranchCode;
	private String distrCode;
	private String ses_branch;
	private dper_infoFilter filter = new dper_infoFilter();
	private Account account;
	private dper_info current = new dper_info();
	private dper_info newdper = new dper_info();
	private ClientA client = new ClientA();
	private EventQueue eq;
	private AnnotateDataBinder binder;
	
	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");

	
	private String accDepSelected;
	private List<Account> accDepList;
	private List<RefData> countries = null;
	private List<RefData> dperKinds = null;
	
	public Dper_infoViewCtrl() {
		super('$', false, false);
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		
		super.doAfterCompose(comp);
		binder = new AnnotateDataBinder(comp);
		//self.setAttribute("binder", binder);
		binder.bindBean("current", this.current);
		binder.bindBean("newdper", this.newdper);
		binder.bindBean("filter", this.filter);
		
		//binder.loadAll(); // смотреть строку ~1292 метод onClick$aveoper()
		
		String[] parameter = (String[]) param.get("ht");
		if (parameter != null) {
			_pageSize = Integer.parseInt(parameter[0]) / 36;
			dataGrid.setRows(Integer.parseInt(parameter[0]) / 36);
		}
		parameter = (String[]) param.get("search_clients");
		if (parameter != null) {
			clientId = parameter[0];
		}
		//clientId="99009495";
		ses_branch = (String) session.getAttribute("branch");
		alias = (String) session.getAttribute("alias");
		un = (String) session.getAttribute("un");
		pw = (String) session.getAttribute("pwd");
//		branchLabel.setValue(ses_branch);
		mbranchCode = dper_infoService.getMbranch_code(alias);
//		mbranchLabel.setValue(mbranch_code);
		
		dataGrid.setItemRenderer(new DperInfoRenderer());
		
		dper_booksInfo.setItemRenderer(new DperBookRenderer());
		
		setModels();
		
		ISLogger.getLogger().error("dper_info:::clientId = "+clientId);
		if(clientId != null){
			//client = ClientDao.getInstance(alias).getItem(clientId);
			client = ClientAService.getItemByStringId(ses_branch, clientId, un, pw, alias);
			filter = new dper_infoFilter();
			filter.setClient(clientId);
			filter.setKind(null);
		}
		
		
		refreshModel(0);
		
	}
	
	public dper_info getNewdper() {
		return newdper;
	}

	public void setNewdper(dper_info newdper) {
		this.newdper = newdper;
	}

	public Menuitem getNew_dper() {
		return new_dper;
	}

	public void setNew_dper(Menuitem new_dper) {
		this.new_dper = new_dper;
	}

	public dper_infoFilter getFilter() {
		return filter;
	}

	public void setFilter(dper_infoFilter filter) {
		this.filter = filter;
	}

	public dper_info getCurrent() {
		return current;
	}

	public void setCurrent(dper_info current) {
		this.current = current;
	}

	public void onCtrlKey(KeyEvent e){
		
	}
	
	private void init(){
		
	}
	
//	private void setClientInfo() {
//		if(client != null){
//			aclient_name1.setValue(client.getP_family());
//			aclient_name2.setValue(client.getP_first_name());
//			aclient_name3.setValue(client.getP_patronymic());
//			abirthday.setValue(client.getP_birthday());
//			adoc_date_issue.setValue(client.getP_passport_date_registration());
//			adoc_date_exp.setValue(client.getP_passport_date_expiration());
//			adoc_id.setSelecteditem(client.getP_type_document());
//			adoc_number.setValue(client.getP_passport_number());
//			adoc_series.setValue(client.getP_passport_serial());
//			adoc_issue.setValue(client.getP_passport_place_registration());
//			apost_address.setValue(client.getP_post_address());
//			arezident.setSelecteditem(client.getCode_resident());
//			clientId = client.getId_client();
//			atotalsum.setValue(SumsService.getTotalsum(clientId, alias));
//		}
//	}
	/****************************************
	 * paging and refreshmodel for dperr_info
	 * 
	 ****************************************** */
	public void onPaging$dper_infoPaging(ForwardEvent event) {
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModel(_startPageNumber);
	}

	private void refreshModel(int activePage) {
		dper_infoPaging.setPageSize(_pageSize);
		DperInfoDao	dperInfoDao = DperInfoDao.getInstance(alias);
		dperInfoDao.setFilter(filter);
		model = new PagingListModel(activePage, _pageSize, filter, alias);
		dataGrid.setModel((ListModel) model);
		_totalSize = model.getTotalSize();
		dper_infoPaging.setTotalSize(_totalSize);
		if (model.getSize() > 0) {
			dataGrid.setSelectedIndex(0);
			sendSelEvt();
		}
	}

	/****************************************
	 * set models for listbox
	 * 
	 **************************************** */
	private void renderDper_books(String id_info_par) {
		System.out.println("id_info_par == " + id_info_par);
		try {
		if (current == null) {
			return;
		}
		String id_info = id_info_par;
		if(id_info_par == null){
			account = AccountService.getAccount(ses_branch, current.getClient(), alias);
			System.out.println("account == " + account);
			System.out.println("current.getId() == " + current.getId());
			id_info = Long.toString(current.getId());
			System.out.println("current.getId() == " + current.getId());
			System.out.println("Long.toString(current.getId()) == " + (Long.toString(current.getId())));
			System.out.println("id_info == " + id_info);
			acc = account.getAcc_dep();
			System.out.println("acc == " + acc);
			current.setAcc_dep(acc);
		}
		ListModelList modelBooks = new ListModelList(dper_infoService.getDper_books(
				id_info, alias));
		dper_booksInfo.setModel(modelBooks);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

//	private void render_ClientsToAdd() {
//		if (customer == null) {
//			return;
//		}
//		hideAll();
//		cl.setVisible(true);
//		ListModelList modelCl = new ListModelList(Service.showNameClient(
//				customer.getP_passport_number(), alias));
//		ClientsToAddLBox.setModel(modelCl);
//	}

	/***********************************************************************************************
	 * 
	 *********************************************************************************************** */

	private void hideAll() {
		grd.setVisible(false);
		new_dp.setVisible(false);
		dp_info.setVisible(false);
		filter_.setVisible(false);
		dp_books.setVisible(false);
	}

	/*****************************************************
	 * actions on navigation buttons
	 * 
	 ******************************************************* */
	public void onDoubleClick$dataGrid$grd() {
		hideAll();
		dp_info.setVisible(true);
		//clientInfo.setOpen(false);
		System.out.println("START:");
		distr.setValue(current.getDistr());
		veoper.setValue(current.getVeoper());
		kind.setValue(current.getKind());
		strs.setValue(current.getStrs());
		strr.setValue(current.getStrr());
		region_offshor.setValue(current.getRegion_offshor());
		client_grstr.setValue(current.getClient_grstr());
		currency.setValue(current.getCurrency());	
		eval.setValue(current.getEval());
		
		//u1f2.setValue(current.getU1f2());
		
		mtcn.setValue(current.getMtcn());
        System.out.println("mtcn - " + current.getMtcn());
        client_i.setValue(current.getClient_i());
        System.out.println("clienti1 - " + current.getClient_i());
        client_i2.setValue(current.getClient_i2());
        System.out.println("clienti2 - " + current.getClient_i2());
        client_i3.setValue(current.getClient_i3());
        System.out.println("clienti3 - " + current.getClient_i3());
        
        
        acc_dep1.setValue(current.getAcc_dep());
        //state.setValue(current.getState());
        
        summa.setValue((current.getSumma().setScale(2, RoundingMode.HALF_EVEN).divide(BigDecimal.valueOf(100)).toString()));       
        summa2.setValue((current.getSumma2().setScale(2, RoundingMode.HALF_EVEN).divide(BigDecimal.valueOf(100)).toString()));       
        summa4.setValue((current.getSumma4().setScale(2, RoundingMode.HALF_EVEN).divide(BigDecimal.valueOf(100)).toString()));      
        //centsumma.setValue(current.getCentsumma().toString());
        summa5.setValue((current.getSumma5().setScale(2, RoundingMode.HALF_EVEN).divide(BigDecimal.valueOf(100)).toString()));     
        summa3.setValue((current.getSumma3().setScale(2, RoundingMode.HALF_EVEN).divide(BigDecimal.valueOf(100)).toString()));     
        profit.setValue((current.getProfit().setScale(2, RoundingMode.HALF_EVEN).divide(BigDecimal.valueOf(100)).toString()));
        
        
		btn_details.setImage("/images/folder.png");
		btn_details.setLabel(Labels.getLabel("grid"));
		dp_books.setVisible(true);
		renderDper_books(null);
	}

	public void onClick$btn_details() {
		if (!grd.isVisible()) {
			hideAll();
			grd.setVisible(true);
			btn_details.setImage("/images/file.png");
			btn_details.setLabel(Labels.getLabel("back"));
		} else
			onDoubleClick$dataGrid$grd();
	}

//	public void onClick$btn_crm() {
//		hideAll();
//		customer = null;
//		cust.setValue("");
//		crm.setVisible(true);
//		
//	}

	public void onClick$btn_first() {
		dataGrid.setSelectedIndex(0);
		sendSelEvt();
	}

	public void onClick$btn_last() {
		dataGrid.setSelectedIndex(model.getSize() - 1);
		sendSelEvt();
	}

	public void onClick$btn_prev() {
		if (dataGrid.getSelectedIndex() != 0) {
			dataGrid.setSelectedIndex(dataGrid.getSelectedIndex() - 1);
			sendSelEvt();
		}
	}

	public void onClick$btn_next() {
		if (dataGrid.getSelectedIndex() != (model.getSize() - 1)) {
			dataGrid.setSelectedIndex(dataGrid.getSelectedIndex() + 1);
			sendSelEvt();
		}
	}

	private void sendSelEvt() {
		if (dataGrid.getSelectedIndex() == 0) {
			btn_first.setDisabled(true);
			btn_prev.setDisabled(true);
		} else {
			btn_first.setDisabled(false);
			btn_prev.setDisabled(false);
		}
		if (dataGrid.getSelectedIndex() == (model.getSize() - 1)) {
			btn_next.setDisabled(true);
			btn_last.setDisabled(true);
		} else {
			btn_next.setDisabled(false);
			btn_last.setDisabled(false);
		}
		SelectEvent evt = new SelectEvent("onSelect", dataGrid,
				dataGrid.getSelectedItems());
		Events.sendEvent(evt);
	}

	/****************************************************************
	 * set models on comboboxes
	 **************************************************************** */
	private void setModels() { 
		
		List<RefData> currencies = null;
		
		List<RefData> groups = null;
		List<RefData> districts = null;
		List<RefData> docTypes = null;
		
		List<RefData> veopers = null;
		List<RefData> rezid = null;
		List<RefData> faces = null;
		List<RefData> states = null;
		List<RefData> mbranches = null;
		
		Connection c = null;
		try {
			c = ConnectionPool.getConnection(alias);
			currencies = SprService.getCurrency(alias);
			countries = SprService.getCountries(alias);
			groups = SprService.getGroups(alias);
			districts = SprService.getDistr(alias);
			docTypes = SprService.getType_document(alias);
			dperKinds = SprService.getKind(alias);
			veopers = SprService.getVeoper(alias);
			rezid = SprService.getRezid(alias);
			faces = SprService.getUorF(alias);
			states = SprService.getState(alias);
			mbranches = SprService.getMbranch(alias);
			distrCode = dper_infoService.getDistrByBranch(c,ses_branch);
			adistr.setValue(distrCode);
		} catch (SQLException e) {
			ISLogger.getLogger().error(e.getStackTrace());
		} finally {
			ConnectionPool.close(c);
		}
		actions.setModel(new ListModelList(SprService.getActions()));
		
		newdper.setV_date(new Date());
		acurrency.setModel(new ListModelList(currencies));
		astrr.setModel(new ListModelList(countries));
		astrs.setModel(new ListModelList(countries));
		aclient_grstr.setModel(new ListModelList(groups));
		adistr.setModel(new ListModelList(districts));
//		adoc_id.setModel(new ListModelList(docTypes));
		//aclient_i13code_str.setModel(new ListModelList(countries));
		akind.setModel(new ListModelList(dperKinds));
		aveoper.setModel(new ListModelList(veopers));
//		arezident.setModel(new ListModelList(rezid));
		aeval.setModel(new ListModelList(currencies));
		au1f2.setModel(new ListModelList(faces));
		// dp_info
		state.setModel(new ListModelList(states));
		u1f2.setModel(new ListModelList(faces));
		currency.setModel(new ListModelList(currencies));
		strr.setModel(new ListModelList(countries));
		strs.setModel(new ListModelList(countries));
		distr.setModel(new ListModelList(districts));
		doc_id.setModel(new ListModelList(docTypes));
		client_grstr.setModel(new ListModelList(groups));
		kind.setModel(new ListModelList(dperKinds));
		veoper.setModel(new ListModelList(veopers));
		rezident.setModel(new ListModelList(rezid));
		//client_i13code_str.setModel(new ListModelList(countries));
		// filter
//		fmbranch.setModel(new ListModelList(mbranches));
		fcurrency.setModel(new ListModelList(currencies));
		fstrr.setModel(new ListModelList(countries));
		fstrs.setModel(new ListModelList(countries));
		fdistr.setModel(new ListModelList(districts));
		fdoc_id.setModel(new ListModelList(docTypes));
		fkind.setModel(new ListModelList(dperKinds));
		fveoper.setModel(new ListModelList(veopers));
		frezident.setModel(new ListModelList(rezid));
		fu1f2.setModel(new ListModelList(faces));
		fstate.setModel(new ListModelList(states));
	}

	/**********************
	 * 
	 * Метод onChange$aclient_i9() будет проверять есть ли в базе данных указанный номер паспорта
	 * Если есть то он должен возвращать 
	 * 1.Имя 2.Фамилию 3.Отчество 4.Гражданство 
	 * 5.Адресс 6.Место Рождения 7.Дата рождения
	 * 
	 * *******************/
	
	
	/*public void onChange$aclient_i9() {
		dper_infoClient dperClient = dper_infoService.canClient(aclient_i8.getValue(), aclient_i9.getValue(), alias);
		
		aclient_i2.setValue(dperClient.getClient_i2());
		aclient_i3.setValue(dperClient.getClient_i3());
		
		aclient_i10.setValue(dperClient.getClient_i10());
		aclient_i11date.setValue(dperClient.getClient_i11date());
		aclient_i12.setValue(dperClient.getClient_i12());
		rezident.setValue(dperClient.getRezident());
		
	}*/
	
	/*******************************************
	 * 
	 * Actions onClick window ZolotayaKorona wzoloto 
	 * 
	 * ***************************************/
	
	
	
	
	
	/*******************************************
	 * 
	 * Actions onSelect combobox
	 * 
	 * ***************************************/

	public void onChange$akind() {
		int kind = Integer.parseInt(akind.getValue());
		if (kind == 0) {// вид перевода выбран - отправить
			astrs.setSelecteditem("860");
			astrs.setDisabled(true);
			asumma3.setDisabled(true);
			asumma5.setDisabled(true);
			asumma2.setDisabled(false);
			sum2Label.setValue("Сумма к переводу");
			sum3Label.setValue("Сумма отправки");
			astrr.setDisabled(false);
			astrr.setSelecteditem(null);
			newdper.setStrr(null);
		}
		if (kind == 1) {// вид перевода выбран - получить
			astrr.setSelecteditem("860");
			astrr.setDisabled(true);
			asumma2.setDisabled(true);
			asumma3.setDisabled(false);
			asumma5.setDisabled(true);
			sum2Label.setValue("Сумма конвертации");
			sum3Label.setValue("Сумма получения");
			astrs.setDisabled(false);
			astrs.setSelecteditem(null);
			newdper.setStrs(null);
			asumma4.setValue("0.00");
		    aprofit.setValue("0.00");
		}	
		
	}

	/*******************************************************************************
	 * actions onChange sums
	 * 
	 ******************************************************************************* 88
	 */
	public void onChange$asumma() {
		if (CheckNull.isEmpty(akind.getValue())
				|| CheckNull.isEmpty(aveoper.getValue())
				|| CheckNull.isEmpty(acurrency.getValue())
				|| CheckNull.isEmpty(aeval.getValue())
				|| CheckNull.isEmpty(aclient_grstr.getValue())) {
			return;
		}
		int kind = Integer.parseInt(akind.getValue());
		long percent = 0;
		BigDecimal sum = null;
		BigDecimal hundred = new BigDecimal(100);
		BigDecimal summa2 = SumsService.dper_getSum(acurrency.getValue(), aeval.getValue(), asumma.getValue().toPlainString(), alias);
		System.out.println("this is summa2:>>  " + summa2);
		BigDecimal s_out = out_tmp.getValue()!=null?out_tmp.getValue():BigDecimal.ZERO;
		BigDecimal centsum = asumma.getValue().remainder(BigDecimal.ONE)
							.add(s_out.remainder(BigDecimal.ONE))
							.multiply(hundred);
		if (summa2.longValue() == 0) {
			alert("Ошибка при конвертации.");
			return;
		}
		
		
		// добавил - for percent
		newdper.setVeoper(aveoper.getValue());
		newdper.setEval(aeval.getValue());
		newdper.setKind(akind.getValue());
		newdper.setCurrency(acurrency.getValue()); // ? 
		newdper.setClient_grstr(aclient_grstr.getValue());
		newdper.setSumma2(summa2);
		
		
		percent = SumsService.calcPercent(newdper, alias);
		
		
		if (kind == 0) {// send
			
			acentsumma.setValue(BigDecimal.ZERO);
			sum = summa2.add(new BigDecimal(percent));
			asumma2.setValue(asumma.getValue().add(s_out));
			
			asumma3.setValue(summa2);
		} else {
			acentsumma.setValue(centsum);
			
			sum =  new BigDecimal(summa2.longValue() - percent);
			asumma3.setValue(sum.add(s_out));
			
		}
		asumma4.setValue(new BigDecimal(percent));
		aprofit.setValue(new BigDecimal(percent)); // почему доход банка это сумма комиссии???
		asumma5.setValue(sum);
	}

	public void onChange$asumma2() {
		BigDecimal summ = null;
		long percent = SumsService.calcPercent(newdper, alias);
		if (akind.getValue().equals("0")) {
			summ = asumma2.getValue().add(new BigDecimal(percent));
			asumma3.setValue(asumma2.getValue());
		} else {
			summ =  asumma2.getValue().add(new BigDecimal(percent));
			asumma3.setValue(summ);
		}
		asumma2.setValue(asumma2.getValue());
		aprofit.setValue(new BigDecimal(percent));
		asumma4.setValue((new BigDecimal(percent)));
		asumma5.setValue(summ);
	}

	public void onChange$asumma4() {
		BigDecimal percent = null;
		BigDecimal sum = null;
		BigDecimal tmp = null;
		percent = asumma4.getValue();
		if (akind.getValue().equals("0")) {
			sum = asumma2.getValue().add(percent);
			tmp = asumma2.getValue().add(acentsumma.getValue());
			asumma3.setValue(tmp);
		} else {
			sum = asumma2.getValue().add(percent);
			tmp = asumma2.getValue()
			.add(out_tmp.getValue())
			.subtract(asumma4.getValue())
			.add(acentsumma.getValue());
			asumma3.setValue(sum);
		}
		asumma4.setValue(asumma4.getValue());
		asumma5.setValue(sum);
	}
	
	
	
	// Работа с полем Центсумма / форма Новый денежный перевод
	public void onChange$acentsumma(){
		
		String aKursVal = akurs_val.getValue().substring(4);
		
		BigDecimal exchangeRates = new BigDecimal(aKursVal);
		BigDecimal centSumma = acentsumma.getValue();
		BigDecimal result = centSumma.multiply(exchangeRates);
		
		acentsumma2.setValue(result);
		acentsumma2.setDisabled(true);
	}

	/***********************************************************************
	 * 
	 * set code country onSelet combobox
	 ************************************************************************* */
	
	public void onChange$astrs() {
		if (astrs.getValue() != null) {
			setGroup_id(astrs.getValue());
		}
		newdper.setStrs(astrs.getValue()); // добавил
	}
	
	public void onChange$astrr() {
		if (astrr.getValue() != null) {
			setGroup_id(astrr.getValue());
		}
		newdper.setStrr(astrr.getValue()); // добавил
	}

	private void setGroup_id(String str) {
		String group_id = dper_infoService.getGrCode(str, alias);
		if (group_id != null) {
			aclient_grstr.setSelecteditem(group_id);
		}
	}

	/****************************************************************************
	 * 
	 * actions with acurrency and aeval comboboxes
	 * 
	 **************************************************************************** */
	

	public void onChange$aeval() {
		setAcc_dep();
		setCourse(aeval.getValue());
	}

	public void onChange$acurrency() {
		if (CheckNull.isEmpty(acurrency.getValue())) {
			return;
		}
		checkVeoper(acurrency.getValue());
	}

	private void checkVeoper(String code) {
		if (!dper_infoService.canVeoper(aveoper.getValue(), code, akind.getValue(),
				alias)) {
			alert("По этому переводу нет операции!");
			return;
		}
	}

	private void setCourse(String code) {
		if (!dper_infoService.convertValidation(acurrency.getValue(), aeval.getValue(),
				alias)) {
			alert("Не настроена конвертация. Справочник №9\n"
					+ acurrency.getValue() + "->" + aeval.getValue());
			return;
		}
		if (!dper_infoService.veoperConvertValid(aveoper.getValue(), akind.getValue(),
				aeval.getValue(), alias)) {
			alert("(7226)По этим денежному переводу ии валюте нет данных по шкалам.");
			return;
		}
		if (!CheckNull.isEmpty(av_date.getValue()) && !CheckNull.isEmpty(code)) {
//			String tmp = df.format(av_date.getValue());
			String course = SumsService.getCourse(code, CheckNull.d2sql(av_date.getValue()), alias);
			kurs_val.setValue(course);
			akurs_val.setValue(course); // for form new dper
		}
		
	}
	/*************************************************************************************
	 * 
	 * 
	 * 
	 ************************************************************************************* */
	public void onBlur$aprofit(){
		newdper.setVeoper(aveoper.getValue());
		newdper.setKind(akind.getValue());
		newdper.setCurrency(acurrency.getValue());
		newdper.setEval(aeval.getValue());
		onClick$btn_gen();
	}
	/**************************************************************************
	 * acc_dep 88
	 */
	
	private void setDepBox(List<Account> list){
		
		List<RefData> refDataList = new ArrayList<RefData>();
		
		for(int i = 0; i < list.size(); i++){
			
			refDataList.add(new RefData(Integer.toString(i), list.get(i).getAcc_dep()));
		}

		acc_dep.setModel(new ListModelList(refDataList));
	}
	
	
	
	private void setAcc_dep() {
		String accBal = AccountService.getAccBal(aveoper.getValue(), aeval.getValue(),
				ses_branch, alias);
		List<Account> list = AccountService.getListAccount(ses_branch, aveoper.getValue(),
				 aeval.getValue(), clientId, accBal, alias);
		if (list.isEmpty()) {
			openAcc(AccountService.getAccBal(aveoper.getValue(), aeval.getValue(),
					ses_branch, alias));
		}else{
			setDepBox(list);
			acc_dep.setValue(list.get(0).getAcc_dep());
			setS_out(list.get(0).getS_out_tmp());
			name_acc.setValue(list.get(0).getName());
			
			
			//***
			accDepList = list;
			accDepSelected = list.get(0).getAcc_dep();
			
		}
	}
	

	public void onSelect$acc_dep() {
		if (acc_dep.getItems().size() > 1) {
			String accBal = AccountService.getAccBal(aveoper.getValue(), aeval.getValue(),
					ses_branch, alias);
			List<Account> list = AccountService.getListAccount(ses_branch, aveoper.getValue(),
					 aeval.getValue(), clientId, accBal, alias);
			int index = acc_dep.getSelectedIndex();
			setS_out(list.get(index).getS_out_tmp());
			name_acc.setValue(list.get(index).getName());
		}
		
	}
	
	private void setS_out(long sout){
		double tmp = (double)sout/100;
		out_tmp.setValue(new BigDecimal(tmp));
	}
	/******************************************************************************
	 * 
	 * Open new acc_dep for customer
	 * 
	 ******************************************************************************* */
	private void openAcc(String accBal) {
		String code = dper_infoService.user_mbranchCode(alias);
		int count = dper_infoService.user_mbranchCount(alias);
		String eval = aeval.getValue();
		String msg = "";
		if (CheckNull.isEmpty(accBal) || accBal.equals("0")
				|| CheckNull.isEmpty(clientId) || CheckNull.isEmpty(eval)) {
			if (count == 0) {
				alert("(3440) Пользователь не найден в модуле Сберкассы-Центры-Банки\n или не все данные введены в Настройке счето\n"
						+ "eAccBal="
						+ accBal
						+ "  eAccVal="
						+ eval
						+ "  eClientCode=" + clientId);
				return;
			} else {
				if (count == 1)
					msg = " найден в " + code;
				else
					msg = " найден в двух минибанках";
				alert("(3440) Пользователь в модуле Сберкассы-Центры-Банки\n"
						+ msg
						+ " или не все данные введены в Настройке счето\n"
						+ "eAccBal=" + accBal + "  eAccVal=" + eval
						+ "  eClientCode=" + clientId);
				return;
			}
		} else {
			StringBuilder sb = new StringBuilder();
			sb.append("Денежные переводы- ").append((client!=null&&client!=null&&client.getP_family()!=null)?client.getP_family():"NEW")
			.append(" ").append((client!=null&&client.getP_first_name()!=null)?client.getP_first_name():"NEW")
			.append(" ").append((client!=null&&client.getP_patronymic()!=null)?client.getP_patronymic():"NEW");
			
			ISLogger.getLogger().error("dper account ::: "+accBal + eval + "0" + clientId);
			ISLogger.getLogger().error("dper accountName ::: "+sb.toString());
			Res res = AccountService.dper_openAcc(accBal + eval + "0" + clientId,
					sb.toString(),un,pw, alias);
			if(res.getCode() == 1){
				alert(res.getName());
				return;
			}
			List<Account> list = AccountService.getListAccount(ses_branch,
					aveoper.getValue(), aeval.getValue(), accBal, clientId,
					alias);
			if (!list.isEmpty()) {
				setAcc_dep();
			}
		}
	}

	/***************************************************
	 * 
	 * confirm,approve,conduct and etc.
	 * 
	 ***************************************************** */
	private void doSubmit(int actionNum, String action, String id_data) {
		if (current == null) {
			return;
		}
		mbranchCode = dper_infoService.getMbranch_code(alias);
		final int[] ans = new int[1];
		final String[] alertMes = new String[1];
		alertMes[0] = action;

		if (TransactionsService.count_in_general(id_data, alias) == 0) {
			alert("(11230)Нельзя для денежного перевода " + id_data
					+ ". Не найдены документы");
			return;
		}
		if (!mbranchCode.equals(current.getMbranch())) {
			alert("Этот перевод не из вашего отделения!(" + mbranchCode + ")("
					+ current.getMbranch() + ")");
			return;
		}
		String err = TransactionsService.getErr_msg(id_data, Integer.toString(actionNum),
				ses_branch, alias);
		if (err.trim().length() != 0) {
			alert("Можно " + alertMes[0] + " только введенный документ " + err);
			return;
		}
		try {
			Messagebox.show(alertMes + " перевод №" + client.getId() + "?",
					"Question", Messagebox.OK | Messagebox.CANCEL,
					Messagebox.QUESTION, new EventListener() {
						@Override
						public void onEvent(Event e) throws Exception {
							if (Messagebox.ON_OK.equals(e.getName())) {
								ans[0] = Messagebox.OK;
							} else {
								return;
							}
						}
					});
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (ans[0] == Messagebox.OK) {
		}
		String message = "";

			message = TransactionsService.dper_provodka(actionNum, alertMes[0],
					Long.toString(current.getId()), alias);

		if (message.trim().length() != 0) {
			alert(message);
		}
	}

	public void onSelect$actions() {
		if (current == null) {
			alert("Выберите dper");
			return;
		}
		int code = Integer.parseInt(actions.getValue());
		String id_data = Long.toString(current.getId());
		doSubmit(code, actions.getText(), id_data);
	}

	public void onSelect$actionsPackage() {
		Set<Listitem> selItems = dataGrid.getSelectedItems();
		if (selItems.isEmpty()) {
			alert("no selected items");
			return;
		}
		dper_info dp;
		for (Listitem item : selItems) {
			dp = (dper_info) item.getValue();
			doSubmit(Integer.parseInt(actionsPackage.getValue()),
					actionsPackage.getText(), Long.toString(dp.getId()));
		}
	}

	/********************************************************
	 * 
	 * generate Purposes
	 * 
	 ******************************************************** */
	private String checkForGen() {
		String err = "";
		if (CheckNull.isEmpty(newdper.getVeoper())) {
			err += "\nДенежный перевод";
		}
		if (CheckNull.isEmpty(newdper.getKind())) {
			err += "\nВид перевода";
		}
		if (CheckNull.isEmpty(newdper.getCurrency())) {
			err += "\nВалюта";
		}
		if (CheckNull.isEmpty(newdper.getEval())) {
			err += "\nКонверт. валюта";
		}
		return err;
	}

	private String getPurpose(String p) {
		StringBuilder sb = new StringBuilder();
		StringBuilder new_ = new StringBuilder();
		String old_;
		
		if (p.trim().length() == 0) {
			return "";
		}
		sb.append(p);
		
		new_.setLength(0);
		old_ = "#SNP";
		new_.append(newdper.getDoc_series()).append(newdper.getDoc_number());
		if (sb.indexOf(old_) > 0) {
			sb.replace(sb.indexOf(old_), sb.indexOf(old_) + old_.length(),
					new_.toString());
		}
		new_.setLength(0);
		old_ = "#DP";
		new_.append(newdper.getVeoper());
		if (sb.indexOf(old_) > 0) {
			sb.replace(sb.indexOf(old_), sb.indexOf(old_) + old_.length(),
					new_.toString());
		}
		new_.setLength(0);
		old_ = "#IO";
		new_.append(newdper.getKind());
		if (sb.indexOf(old_) > 0) {
			sb.replace(sb.indexOf(old_), sb.indexOf(old_) + old_.length(),
					new_.toString());
		}
		new_.setLength(0);
		old_ = "#DATE";
		new_.append(newdper.getV_date());
		if (sb.indexOf(old_) > 0) {
			sb.replace(sb.indexOf(old_), sb.indexOf(old_) + old_.length(),
					new_.toString());
		}
		new_.setLength(0);
		old_ = "#FIO";
		new_.append(newdper.getClient_name1()).append(" ")
				.append(newdper.getClient_name2()).append(" ")
				.append(newdper.getClient_name3());
		if (sb.indexOf(old_) > 0) {
			sb.replace(sb.indexOf(old_), sb.indexOf(old_) + old_.length(),
					new_.toString());
		}
		new_.setLength(0);
		old_ = "#KO";
		new_.append(newdper.getClient_i());
		if (sb.indexOf(old_) > 0) {
			sb.replace(sb.indexOf(old_), sb.indexOf(old_) + old_.length(),
					new_.toString());
		}
		new_.setLength(0);
		old_ = "#MTCN";
		new_.append(newdper.getMtcn());
		if (sb.indexOf(old_) > 0) {
			sb.replace(sb.indexOf(old_), sb.indexOf(old_) + old_.length(),
					new_.toString());
		}
		
		return sb.toString();
	}

	public void onClick$btn_gen() {
		//System.out.println("onClick$btn_gen");		
		String nazn = "", purpose = "";
		String kurs = "";
		StringBuilder valuta = new StringBuilder();
		int purposeIndex = 1;
		purpose_1.setValue(null);
		purpose_2.setValue(null);
		purpose_3.setValue(null);
		purpose_4.setValue(null);
		purpose_5.setValue(null);
		purpose_6.setValue(null);
		String err = checkForGen();
		if (err.trim().length() != 0) {
			alert("Заполните поля " + err);
		}
		List<Oper_info> opers = dper_infoService
				.getPurposes(newdper, alias);
		for (Oper_info oper: opers) {
			if (!CheckNull.isEmpty(oper.getNazn())) {
				nazn = oper.getNazn();
			} else if (!CheckNull.isEmpty(oper.getCashsymd())) {
				nazn = oper.getCashsymd();
			}
			if (!CheckNull.isEmpty(oper.getVal_c())
					&& !CheckNull.isEmpty(oper.getVal_d())
					&& !(oper.getVal_d()).equals((oper.getVal_c()))) {
				if (!(oper.getVal_d()).equals("000")) {
					kurs = SumsService.getCourse(oper.getVal_d(),
							CheckNull.d2sql(newdper.getV_date()), alias);
					valuta.append("Курс").append(oper.getVal_d())
							.append(kurs);
					switch (oper.getTypeover()) {
					case (1):
						valuta.insert(0, "S=" + newdper.getSumma());
						break;
					case (2):
						valuta.insert(0, "S=" + newdper.getSumma3());
						break;
					case (3):
						valuta.insert(0, "S=" + newdper.getSumma2());
						break;
					case (4):
						valuta.insert(0, "S=" + newdper.getSumma2());
						break;
					case (5):
						valuta.insert(0, "S=" + newdper.getSumma4());
						break;
					case (6):
						valuta.insert(0, "S=" + newdper.getProfit());
						break;
					case (7):
						valuta.insert(0, "S=" + newdper.getCentsumma());
						break;
					}
				}
				if (!(oper.getVal_c()).equals("000")) {
					kurs = SumsService.getCourse(oper.getVal_c(),
							CheckNull.d2sql(av_date.getValue()), alias);
					valuta.append("Курс").append(oper.getVal_c())
							.append(kurs);
					switch (oper.getTypeover()) {
					case (1):
						valuta.insert(0, "S=" + newdper.getSumma());
						break;
					case (2):
						valuta.insert(0, "S=" + newdper.getSumma3());
						break;
					case (3):
						valuta.insert(0, "S=" + newdper.getSumma2());
						break;
					case (4):
						valuta.insert(0, "S=" + newdper.getSumma2());
						break;
					case (5):
						valuta.insert(0, "S=" + newdper.getSumma4());
						break;
					case (6):
						valuta.insert(0, "S=" + newdper.getProfit());
						break;
					case (7):
						valuta.insert(0, "S=" + newdper.getCentsumma());
						break;
					}
				}
			}
			mbranchCode = dper_infoService.getMbranch_code(alias);
			purpose = getPurpose(oper.getPurpose());
			switch (purposeIndex) {
			case (1):
				purpose_1.setValue(nazn + " " + mbranchCode + " " + purpose
						+ " " + valuta);
				break;
			case (2):
				purpose_2.setValue(nazn + " " + mbranchCode + " " + purpose
						+ " " + valuta);
				break;
			case (3):
				purpose_3.setValue(nazn + " " + mbranchCode + " " + purpose
						+ " " + valuta);
				break;
			case (4):
				purpose_4.setValue(nazn + " " + mbranchCode + " " + purpose
						+ " " + valuta);
				break;
			case (5):
				purpose_5.setValue(nazn + " " + mbranchCode + " " + purpose
						+ " " + valuta);
				break;
			case (6):
				purpose_6.setValue(nazn + " " + mbranchCode + " " + purpose
						+ " " + valuta);
				break;
			}
			purposeIndex++;
		}
	}

	/********************************************************
	 * 
	 * Filter
	 ******************************************************** */
	public void onClick$btn_clear(){
		filter = new dper_infoFilter();
		//CheckNull.clearForm(filter_);
		try {
			// aclient_i9.setValue("");
			aveoper.setValue("");
			akind.setValue("");
			astrs.setValue("");
			astrr.setValue("");
			aregion_offshor.setValue("");
			aclient_grstr.setValue("");
			acurrency.setValue("");
			aeval.setValue("");

			aclient_i.setConstraint("");
			aclient_i.setValue("");
			aclient_i2.setConstraint("");
			aclient_i2.setValue("");
			aclient_i3.setConstraint("");
			aclient_i3.setValue("");
			// aclient_i13code_str.setValue("");
			// aclient_i8.setConstraint("");aclient_i8.setValue("");
			// aclient_i9.setConstraint("");aclient_i9.setValue("");
			// aclient_i10.setConstraint("");aclient_i10.setValue("");
			// aclient_i12.setConstraint("");aclient_i12.setValue("");
			// aclient_i11date.setConstraint("");aclient_i11date.setValue(null);
			amtcn.setConstraint("");
			amtcn.setValue("");
			akurs_val.setValue("");
			//acc_dep.setValue("");
			//out_tmp.setValue("");
			//name_acc.setValue("");
			//atotalsum.setValue("");
			asumma.setValue(new BigDecimal(0));
			asumma2.setValue(new BigDecimal(0));
			asumma4.setValue(new BigDecimal(0));
			acentsumma.setValue(new BigDecimal(0));
			acentsumma2.setValue(new BigDecimal(0));
			
			asumma3.setValue(new BigDecimal(0));
			asumma5.setValue(new BigDecimal(0));
			aprofit.setValue(new BigDecimal(0));
			
			purpose_1.setValue("");
			purpose_2.setValue("");
			purpose_3.setValue("");
			purpose_4.setValue("");
			purpose_5.setValue("");
			purpose_6.setValue("");
			// newdper = new dper_info();
			//CheckNull.clearForm(new_dp);
		} catch (Exception e) {

		}
	}
	
	public void onClick$btn_search() {
		hideAll();
		filter_.setVisible(true);
		fclient_i.setWidth("99%");
		
	}

	public void onClick$btn_filter() {
		
		refreshModel(0);
		onClick$btn_details();
	}

	public void onClick$btn_fcancel() {
		hideAll();
//		crm.setVisible(true);
	}

	/****************************************************
	 * Save data about new transaction
	 * 
	 **************************************************** */
	public void onClick$btn_cleardper(){
		
		try {
			// aclient_i9.setValue("");
			aveoper.setValue("");
			akind.setValue("");
			astrs.setValue("");
			astrr.setValue("");
			aregion_offshor.setValue("");
			aclient_grstr.setValue("");
			acurrency.setValue("");
			aeval.setValue("");

			aclient_i.setConstraint("");
			aclient_i.setValue("");
			aclient_i2.setConstraint("");
			aclient_i2.setValue("");
			aclient_i3.setConstraint("");
			aclient_i3.setValue("");
			// aclient_i13code_str.setValue("");
			// aclient_i8.setConstraint("");aclient_i8.setValue("");
			// aclient_i9.setConstraint("");aclient_i9.setValue("");
			// aclient_i10.setConstraint("");aclient_i10.setValue("");
			// aclient_i12.setConstraint("");aclient_i12.setValue("");
			// aclient_i11date.setConstraint("");aclient_i11date.setValue(null);
			amtcn.setConstraint("");
			amtcn.setValue("");
			akurs_val.setValue("");
			//acc_dep.setValue("");
			//out_tmp.setValue("");
			//name_acc.setValue("");
			//atotalsum.setValue("");
			asumma.setValue(new BigDecimal(0));
			asumma2.setValue(new BigDecimal(0));
			asumma4.setValue(new BigDecimal(0));
			acentsumma.setValue(new BigDecimal(0));
			acentsumma2.setValue(new BigDecimal(0));
			
			asumma3.setValue(new BigDecimal(0));
			asumma5.setValue(new BigDecimal(0));
			aprofit.setValue(new BigDecimal(0));
			
			purpose_1.setValue("");
			purpose_2.setValue("");
			purpose_3.setValue("");
			purpose_4.setValue("");
			purpose_5.setValue("");
			purpose_6.setValue("");
			// newdper = new dper_info();
			//CheckNull.clearForm(new_dp);
		} catch (Exception e) {

		}
		//newdper = new dper_info();
		//CheckNull.clearForm(new_dp);
	}
	
	public void onClick$btn_addp() {
	//	aclientInfo.setOpen(false);
	//	if(clientId == null){
	//		alert("Нет клиента");
	//		return;
	//	}
		
		dper_booksInfo.getItems().clear();
		dper_booksInfo.clearSelection();
		defaultDper();
		
		hideAll();
		new_dp.setVisible(true);
		dp_books.setVisible(true);
		
		try {
			// aclient_i9.setValue("");
			aveoper.setValue("");
			akind.setValue("");
			astrs.setValue("");
			astrr.setValue("");
			aregion_offshor.setValue("");
			aclient_grstr.setValue("");
			acurrency.setValue("");
			aeval.setValue("");

			aclient_i.setConstraint("");
			aclient_i.setValue("");
			aclient_i2.setConstraint("");
			aclient_i2.setValue("");
			aclient_i3.setConstraint("");
			aclient_i3.setValue("");
			// aclient_i13code_str.setValue("");
			// aclient_i8.setConstraint("");aclient_i8.setValue("");
			// aclient_i9.setConstraint("");aclient_i9.setValue("");
			// aclient_i10.setConstraint("");aclient_i10.setValue("");
			// aclient_i12.setConstraint("");aclient_i12.setValue("");
			// aclient_i11date.setConstraint("");aclient_i11date.setValue(null);
			amtcn.setConstraint("");
			amtcn.setValue("");
			akurs_val.setValue("");
			//acc_dep.setValue("");
			//out_tmp.setValue("");
			//name_acc.setValue("");
			//atotalsum.setValue("");
			asumma.setValue(new BigDecimal(0));
			asumma2.setValue(new BigDecimal(0));
			asumma4.setValue(new BigDecimal(0));
			acentsumma.setValue(new BigDecimal(0));
			acentsumma2.setValue(new BigDecimal(0));
			
			asumma3.setValue(new BigDecimal(0));
			asumma5.setValue(new BigDecimal(0));
			aprofit.setValue(new BigDecimal(0));
			
			purpose_1.setValue("");
			purpose_2.setValue("");
			purpose_3.setValue("");
			purpose_4.setValue("");
			purpose_5.setValue("");
			purpose_6.setValue("");
			// newdper = new dper_info();
			//CheckNull.clearForm(new_dp);
		} catch (Exception e) {

		}
		
	}

	private void defaultDper() {
		
		newdper = new dper_info();
		newdper.setV_date(dper_infoService.info_getday(alias));
		newdper.setDistr(distrCode);
		newdper.setSumma(BigDecimal.ZERO);
		newdper.setSumma2(BigDecimal.ZERO);
		newdper.setSumma3(BigDecimal.ZERO);
		newdper.setSumma4(BigDecimal.ZERO);
		newdper.setSumma5(BigDecimal.ZERO);
		newdper.setCentsumma(BigDecimal.ZERO);
		newdper.setProfit(BigDecimal.ZERO);
		newdper.setClientData(client);
	}
	
	public void onClick$btn_save() {		
		
		newdper.setStrs(astrs.getValue());
		newdper.setStrr(astrr.getValue());
		System.out.println(newdper.toString());
		//newdper.setMtcn(amtcn.getValue());
		//newdper.setOut_tmp(out_tmp.getValue());		
		//newdper.setName_acc(name_acc.getValue());
		
		onClick$btn_gen();
		final boolean[] do_it = new boolean[1];
		do_it[0] = true;
		double totalsum = 0;
		double summa3_ = 0;
		String err = "";
		String veoper_cb = "";		
		err = DperValidator.getInstance(newdper, alias).check();
		
		System.out.println("err = "+err);
		if(err != null && err.length() != 0) {
			alert(err);
			return;
		}
		
		if(atotalsum.getValue().length()!=0){
			totalsum = Double.parseDouble(atotalsum.getValue());
		}
		
		summa3_ = SumsService.forTotalSum(acurrency.getValue(),aeval.getValue(), 
				asumma3.getValue().toPlainString(), alias);
		System.out.println("summa3_ = "+summa3_);
		if(aclient_i.getValue().equals("") && aclient_i2.getValue().equals("")) {alert("Фамилия или Имя не заполнены");}
		if (akind.getValue().equals("1")) { // receive
			if (amtcn.getValue().equals("")) {
				alert("Заполните код перевода(MTCN)");
				
			}
			totalsum = 0 - summa3_;
		}
		if (totalsum + summa3_ > 5000) {
			try {
				Messagebox
						.show("По данному клиенту уже отправлено переводов за день в эквиваленте "
								+ totalsum
								+ ". "
								+ "Вы уверены, что хотите ввести еще в эквиваленте "
								+ summa3_ + "?", "", Messagebox.OK
								| Messagebox.CANCEL, Messagebox.QUESTION,
								new EventListener() {
									@Override
									public void onEvent(Event event)
											throws Exception {
										if(Messagebox.ON_CANCEL.equals(event.getName())){
											do_it[0]=false;
										}
									}
								});
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			do_it[0] = true;
		}
		if (!do_it[0]) {
			return;
		}
		veoper_cb = aveoper.getValue();
		System.out.println("1 veoper_cb == " + veoper_cb);
		if (aveoper.getValue().trim().length() == 6) {
			veoper_cb = (aveoper.getValue()).substring(2);
			System.out.println("2 veoper_cb == " + veoper_cb);
		}
//		aid = dper_infoService.getId(alias);
//		dper_info dp = getDper();
//		dp.setId(Long.parseLong(aid));
		newdper.setMbranch(mbranchCode);
//		String eval = aeval.getSelectedItem().getValue().toString();
		
		
		setDataInNewdper();
		System.out.println("setDataInNewdper();");
		ObjectMapper objectMapper = new ObjectMapper();

		try {
			LtLogger.getLogger().error(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(newdper));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Res res = TransactionsService.insertNewData(newdper, alias);
		
		//String un = (String) session.getAttribute("un");
		//String pw = (String) session.getAttribute("pw");
		System.out.println("res.getcode = "+res.getCode());
		
		System.out.println("UN: " + un + " PW: " + pw + " ALIAS: " + alias);
		
		String info_id = dper_infoService.getId(un, pw, alias);
		System.out.println("info_id = "+info_id);
		renderDper_books(info_id);
		
		if(res.getCode() == 1){
			alert(res.getName());
			return;
		}
		
	}

	public void onClick$btn_cancel() {
		if (filter_.isVisible()) {
			filter = new dper_infoFilter();
		}
		hideAll();
		client = null;
		CheckNull.clearForm(addgrd);
		CheckNull.clearForm(fgrd);
		refreshModel(0);
		onClick$btn_details();
	}
	
	private String kontrField(){
		//String[] arr = aclient_i13code_str.getText().split(" ");
		StringBuilder tmp = new StringBuilder();
		tmp.append(aclient_i.getText()).append(" ").
			append(aclient_i2.getText()).append(" ").
			append(aclient_i3.getText()).append("(");
		/*for(int i = 0; i < arr.length; i++){
			if(i != 0 ){
				if(tmp.charAt(tmp.length()-1)!='('){
					tmp.append(" ");
				}
				tmp.append(arr[i]);
			}
		}*/
		tmp.append(")");
		return tmp.toString();
	}
	
	private String defaultValue(String str){
		return CheckNull.isEmpty(str)?"NEW":str;
	}
	
	public void onClick$dataGrid$grd(){
        System.out.println("-----Click------");
        current = (dper_info) ((Listitem) dataGrid.getSelectedItem()).getValue();
        amtcn.setValue(current.getMtcn());
        System.out.println("mtcn - " + current.getMtcn());
        aclient_i.setValue(current.getClient_i());
        System.out.println("clienti1 - " + current.getClient_i());
        aclient_i2.setValue(current.getClient_i2());
        System.out.println("clienti2 - " + current.getClient_i2());
        aclient_i3.setValue(current.getClient_i3());
        System.out.println("clienti3 - " + current.getClient_i3());
        
        System.out.println(current.getId() + " ----------- Id  -----------");
        
    }
	
	public void onClick$btn_delete(){
		if(current == null){
			alert("Выберите денежный перевод");
			return;
		}
		    
		    onClick$dataGrid$grd();
		    onDoubleClick$dataGrid$grd();
		
			int countInGeneral = TransactionsService.count_in_general(current.getId().toString(), alias);
			System.out.println("current.getId().toString() = " + current.getId().toString());
			if(countInGeneral == 0){
				alert("(5030)Нельзя удалить - для денежного перевода "+current.getId()+" Не найдены документы!");
				return;
			}
			/*if(mbranchCode!=null&&mbranchCode.equals(current.getMbranch())){
				alert("Этот перевод не из вашего отделения!");
				return;
			}*/
			String err_msg = TransactionsService.getErr_msg(current.getId().toString(), "2", ses_branch, alias);
			if(err_msg.length() != 0){
				alert("Можно удалять только введенный документ\n"+err_msg);
				return;
			}
			
			Res res = TransactionsService.deleteDper(current.getId(), alias);
			if(res.getCode() != 0){
				alert("Проверьте состояние документов, не удалось удалить \n"+res.getName());
			}
		
		
	}
	
	public void onClick$btn_open_reports(){
		try{
			if(reports_page == null){
				reports_page = (Window)Executions.createComponents("dper_reports.zul", dper_infomain, null);
  			}
				reportsController = (ReportsController)reports_page.getAttribute("dper_reports$composer");
				
				reportsController.init();
    	} catch (Exception e) {
  			e.printStackTrace();
  		}
		
	}
	
	
	
	// незнаю из-за чего работает не корректно binder.loadAll() строка ~168 в методе doAfterCompose() 
	// при переходе на некоторые поля очищаются все заполненные и заполняются два первых поля Пункт обслуживания и Дата авторизации
	// закоментировал binder.loadAll() и заполняю эти два поля при изменении в поле Денежный перевод
	public void onChange$aveoper(){
		
		System.out.println("onChange$aveoper m");
		
		// Пункт обслуживания
		adistr.setSelecteditem(distrCode);
		
		// Дата авторизации
		av_date.setValue(newdper.getV_date());
		
		
		
		// получаем общую сумму
		clientId = client.getId_client();
		String totalSum = SumsService.getTotalsum(clientId, alias) != null ? SumsService.getTotalsum(clientId, alias) : "0";
		
		// Общая сумма за текущий день
		atotalsum.setValue(totalSum);
	}
	
	
	protected void setDataInNewdper(){
		
		newdper.setBranch(alias);
		System.out.println("ALIAS:::: " + alias);
		newdper.setSumma(asumma.getValue());
		System.out.println("asumma:::: " + asumma.getValue());
		newdper.setClient_i(aclient_i.getValue());
		newdper.setProfit(aprofit.getValue());
		newdper.setU1f2(Integer.parseInt(au1f2.getValue())); 
		newdper.setCentsumma(acentsumma.getValue());
		newdper.setClient_i2(aclient_i2.getValue());
		newdper.setClient_i3(aclient_i3.getValue());
		//newdper.setClient_i4(aclient_i4.getValue());
		//newdper.setClient_i5(aclient_i5.getValue());
		//System.out.println("aclient_i5 === " + aclient_i5.getValue());
		// 6, 7 ???
		//newdper.setClient_i8(aclient_i8.getValue());
		//System.out.println("aclient_i9 === " + aclient_i9.getValue());
		//newdper.setClient_i9(aclient_i9.getValue());
		//newdper.setClient_i10(aclient_i10.getValue());
		//newdper.setClient_i11date(aclient_i11date.getValue());
		//newdper.setClient_i12(aclient_i12.getValue());		
		//newdper.setClient_i13code_str(aclient_i13code_str.getValue());
		newdper.setSumma2(asumma2.getValue());	
		newdper.setSumma3(asumma3.getValue());	
		newdper.setSumma4(asumma4.getValue());	
		newdper.setSumma5(asumma5.getValue());	
		newdper.setRegion_offshor(aregion_offshor.getValue());
		newdper.setClient_grstr(aclient_grstr.getValue());
		
		newdper.setKurs_val(akurs_val.getValue());	// - ?? в каком виде / тип
		
		newdper.setPurpose_1(purpose_1.getValue());	
		newdper.setPurpose_2(purpose_2.getValue());	
		newdper.setPurpose_3(purpose_3.getValue());	
		newdper.setPurpose_4(purpose_4.getValue());	
		newdper.setPurpose_5(purpose_5.getValue());	
		newdper.setPurpose_6(purpose_6.getValue());	
		
		newdper.setStrs(astrs.getValue());
		
		if(acc_dep.getSelectedIndex() != -1){
			
			accDepSelected = accDepList.get(acc_dep.getSelectedIndex()).getAcc_dep();
			
			System.out.println("accDepSelected - "+accDepSelected);
		}
		else{
			
			System.out.println("accDepSelected - "+accDepSelected);
		}
		
		newdper.setAcc_dep(accDepSelected);
		newdper.setOut_tmp(out_tmp.getValue());		
		newdper.setName_acc(name_acc.getValue());
		
		//
		//newdper.setId(0L);
		//newdper.setState(0L);
	}
	
     public void onClick$btn_userreport() {
		
		
	 }
     
     public void onSelect$orderbox(Event event) {
    	 
    	 String uin = amtcn.getValue();
    	 PrintOrders print = getPrints();
    	 
    	 Map<String, Object> params =  new HashMap<String, Object>();
		  try {
		    params = RepTempl.objToMap(print, params);
		  } catch (Exception e) {
		    
		    e.printStackTrace();
		  }
		  
		  System.out.println("param === " + params);
   	/*
   	if(orderbox.getValue().equals("Ariza Otpravka"))    		
		{
			AMedia amedia = RepTempl2.getRepmdAriza2(params, Executions.getCurrent().getDesktop().getWebApp().getRealPath("/reports/ARIZA_OTPR.docx"), "test1"+params.get("p_number"));
		System.out.println("amedia === " + amedia);
		  if (amedia!=null){
		    Filedownload.save(amedia);
		  }else{
		    alert("Файл не сформирован");
		  }
		} 
   	else if(orderbox.getValue().equals("Ariza Poluchenie"))    		
		{
			AMedia amedia = RepTempl2.getRepmdAriza2(params, Executions.getCurrent().getDesktop().getWebApp().getRealPath("/reports/ARIZA_POLUCH.docx"), "test1"+params.get("p_number"));
		System.out.println("amedia === " + amedia);
		  if (amedia!=null){
		    Filedownload.save(amedia);
		  }else{
		    alert("Файл не сформирован");
		  }
		}
   	else if(orderbox.getValue().equals("Ariza Uz"))    		
		{
			AMedia amedia = RepTempl2.getRepmdAriza(params, Executions.getCurrent().getDesktop().getWebApp().getRealPath("/reports/ARIZA_UZ.docx"), "test1"+params.get("p_number"));
		System.out.println("amedia === " + amedia);
		  if (amedia!=null){
		    Filedownload.save(amedia);
		  }else{
		    alert("Файл не сформирован");
		  }
		}
		else*/ if(orderbox.getValue().equals("Order")) 
		{
			AMedia amedia = RepTempl2.getRepmdKoroni(params, Executions.getCurrent().getDesktop().getWebApp().getRealPath("/reports/koroni.docx"), "test1"+params.get("p_number"));
			System.out.println("amedia === " + amedia);
			  if (amedia!=null){
			    Filedownload.save(amedia);
			  }else{
			    alert("Файл не сформирован");
			  }
		}   	
    	 
    	 
     }
	
     public PrintOrders getPrints() {
    	 
    	PrintOrders printOrders = new PrintOrders();
    	dper_info dper_info = new dper_info();
    	 
    	Connection c = null;
 		PreparedStatement ps = null;
 		ResultSet rs = null;

 		try {

 			c = ConnectionPool.getConnection();
 			ps = c.prepareStatement(
 					"SELECT a.*, b.* FROM dper_info a, dper_books b where a.id = b.info_id and typeoper in('2', '3') and mtcn = ?");
 			ps.setString(1, amtcn.getValue());
 			System.out.println("UIN == " + amtcn.getValue());
 			rs = ps.executeQuery();
 			if (rs.next()) {
 				dper_info = new dper_info();
 				printOrders = new PrintOrders();

 				dper_info.setBranch(rs.getString("branch"));
 				printOrders.setBranch(rs.getString("branch"));

 				dper_info.setMbranch(rs.getString("mbranch"));
 				dper_info.setId(rs.getLong("id"));
 				dper_info.setVeoper(rs.getString("veoper"));
 				dper_info.setKind(rs.getString("kind"));
 				dper_info.setStrs(rs.getString("strs"));
 				dper_info.setStrr(rs.getString("strr"));
 				dper_info.setDistr(rs.getString("distr"));

 				dper_info.setSumma(rs.getBigDecimal("summa"));

 				printOrders.setSummaoper1(rs.getBigDecimal("summa").divide(new BigDecimal(100)));

 				dper_info.setCurrency(rs.getString("currency"));

 				if (rs.getString("CURRENCY").equals("840")) {
 					printOrders.setCur("Доллар");
 					System.out.println("USD");
 				} else if (rs.getString("CURRENCY").equals("643")) {
 					printOrders.setCur("Рубль");
 				} else if (rs.getString("CURRENCY").equals("000") || rs.getString("currency").equals("860")) {
 					printOrders.setCur("Сум");
 				} else {
 					System.out.println("Ошибка Валюты");
 				}

 				if (rs.getString("KIND").equals("0")) {
 					printOrders.setType_order("ПРИХОДНЫЙ");
 					printOrders.setPurpose1("Золотая Корона оркали жунатилган маблаг");
 				} else if (rs.getString("KIND").equals("1") || rs.getString("KIND").equals("2")) {
 					printOrders.setType_order("РАСХОДНЫЙ");
 					printOrders.setPurpose1("Золотая Корона оркали келган маблаг");
 				} else {
 					System.out.println("Выберите операцию");
 				}

 				dper_info.setV_date(rs.getDate("v_date"));
 				printOrders.setV_date(rs.getDate("v_date"));

 				dper_info.setClient(rs.getString("client"));

 				dper_info.setClient_name1(rs.getString("client_name1"));
 				printOrders.setClient_name1(rs.getString("client_name1"));

 				dper_info.setClient_name2(rs.getString("client_name2"));
 				printOrders.setClient_name2(rs.getString("client_name2"));

 				dper_info.setClient_name3(rs.getString("client_name3"));
 				printOrders.setClient_name3(rs.getString("client_name3"));
 				

 				dper_info.setRezident(rs.getString("rezident"));
 				dper_info.setDoc_id(rs.getString("doc_id"));
 				
 				dper_info.setDoc_series(rs.getString("doc_series"));
 				printOrders.setDoc_series(rs.getString("doc_series"));
 				
 				dper_info.setDoc_number(rs.getString("doc_number"));
 				printOrders.setDoc_number(rs.getString("doc_number"));
 				
 				dper_info.setDoc_issue(rs.getString("doc_issue"));
 				printOrders.setDoc_issue(rs.getString("doc_issue"));
 				
 				dper_info.setDoc_date_issue(rs.getDate("doc_date_issue"));
 				printOrders.setDoc_date_issue(rs.getString("doc_date_issue"));
 				
 				dper_info.setClient_i(rs.getString("client_i"));
 				dper_info.setState(rs.getLong("state"));
 				dper_info.setPost_address(rs.getString("post_address"));
 				dper_info.setBirthday(rs.getDate("birthday"));
 				dper_info.setProfit(rs.getBigDecimal("profit"));
 				printOrders.setComission(rs.getBigDecimal("profit").divide(new BigDecimal(100)));

 				dper_info.setMtcn(rs.getString("mtcn"));
 				dper_info.setU1f2(rs.getInt("u1f2"));
 				dper_info.setCentsumma(rs.getBigDecimal("centsumma"));
 				dper_info.setClient_i2(rs.getString("client_i2"));
 				dper_info.setClient_i3(rs.getString("client_i3"));
 				//dper_info.setClient_i4(rs.getString("client_i4"));
 				//dper_info.setClient_i5(rs.getString("client_i5"));
 				dper_info.setClient_i6(rs.getString("client_i6"));
 				dper_info.setClient_i7(rs.getString("client_i7"));
 				//dper_info.setClient_i8(rs.getString("client_i8"));
 				//dper_info.setClient_i9(rs.getString("client_i9"));
 				//dper_info.setClient_i10(rs.getString("client_i10"));
 				//dper_info.setClient_i11date(rs.getDate("client_i11date"));
 				//dper_info.setClient_i12(rs.getString("client_i12"));
 				dper_info.setSumma2(rs.getBigDecimal("summa2"));
 				dper_info.setSumma3(rs.getBigDecimal("summa3"));
 				dper_info.setSumma4(rs.getBigDecimal("summa4"));
 				dper_info.setSumma5(rs.getBigDecimal("summa5"));
 				
 				
 				double summa = new BigDecimal(rs.getInt("SUMMA") + rs.getInt("PROFIT")).divide(new BigDecimal(100)).doubleValue();
 				System.out.println("SUMMAAAA: " + summa);
 				printOrders.setSumma5(String.valueOf(summa));
 				
 				String psumma5 = CheckNull.F2Money(summa);
 				printOrders.setPsumma5(psumma5);
 				
 				int forex_course = KoronaPayDBHelper.centSumma(rs.getString("MTCN"));
 				double sumkurs = new BigDecimal(summa).multiply(new BigDecimal(forex_course)).doubleValue();
 				
 				System.out.println("FXCOURSE  " + forex_course);
 				System.out.println("SUMKURS   " + sumkurs);
 				printOrders.setKursvalfxsumma(String.valueOf(sumkurs));
 				
 				//printOrders.setPkursvalfxsumma();
 		
 				String pkurs = CheckNull.F2Money(sumkurs, "", "");
 				printOrders.setPkursvalfxsumma(pkurs);
 				System.out.println("PKURS  "+pkurs);
 				
 				printOrders.setTveoper("Золотая Корона");
 				
 				//dper_info.setClient_i13code_str(rs.getString("client_i13code_str"));
 				dper_info.setRegion_offshor(rs.getString("region_offshor"));
 				dper_info.setClient_grstr(rs.getString("client_grstr"));

 				printOrders.setAccdoper1(rs.getString("ACC_D"));  //rs.getString("ACC_D")
 				printOrders.setOpendoper(rs.getString("ACC_C"));
 				double b2 = rs.getBigDecimal("summa").divide(new BigDecimal(100)).doubleValue();

 				System.out.println("b2 == " + b2);

 				String b1 = CheckNull.F2Money(b2);

 				System.out.println("b1 == " + b1);

 				String b4 = rs.getString("CURRENCY");

 				System.out.println("b4 == " + b4);

 				String b3 = CheckNull.F2Money(b2, b4, b4);

 				System.out.println("b3 == " + b3);
 				printOrders.setPsummaoper1(b3);

 			}

 		} catch (Exception e) {
 			e.printStackTrace();
 		} finally {
 			try {
 				rs.close();
 			} catch (SQLException e) {
 				e.printStackTrace();
 			}
 			try {
 				ps.close();
 			} catch (SQLException e) {
 				e.printStackTrace();
 			}
 			ConnectionPool.close(c);
 		}
    	 
    	 return printOrders;
     }
     
	
	
	
	
//	private Toolbarbutton btn_open_sets;
//	private Div sets;
//	public void onClick$btn_open_sets(){
//		if((btn_open_sets.getLabel()).equals("Закрыть настройки")){
//			sets.setVisible(true);
//		} else {
//			btn_open_sets.setLabel("Открыть настройки");
//			sets.setVisible(false);
//		}
//	}
//private Groupbox clientInfo,aclientInfo;
//	public void onClick$toggleClient(){
//		clientInfo.setOpen(!clientInfo.isOpen());
////		clientInfo.setVisible(!clientInfo.isVisible());
//	}
//	public void onClick$atoggleClient(){
//		aclientInfo.setOpen(!aclientInfo.isOpen());
//	}
}
