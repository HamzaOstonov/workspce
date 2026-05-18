package com.is.humoJobLog;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.ConnectException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zkplus.databind.BindingListModelList;
import org.zkoss.zul.Button;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Row;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.is.ConnectionPool;
import com.is.ISLogger;

import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.RefData;
import com.is.utils.Res;

import java.io.InputStream;
import org.zkoss.util.media.Media;
import org.zkoss.zul.Filedownload;
import org.zkoss.util.media.AMedia;
import java.io.ByteArrayInputStream;


public class JobLogViewCtrl extends GenericForwardComposer {
	private static final long serialVersionUID = 1L;
	public CustomerFilter filter = new CustomerFilter();
	public JobLog current = new JobLog();
	public JobLog curr = new JobLog();
	public JobLog copyOfCurrent;
	public JobLog curr_tieto = new JobLog();

	private AnnotateDataBinder binder;

	private int _pageSize;
	private int _startPageNumber = 0;
	private int _totalSize;
	private String un;
	private String pwd;
	private String branch;
	private String alias;
	private String curip;
	private int uid;
	static String listCustomerEndpoint;
	private static HashMap<String, String> statesNciAccount = null;
	private static HashMap<String, String> statesTieto = null;
	private static HashMap<String, String> stopCausesTieto = null;
	//List<RowsItem> items;
	private String cardString, clientName;
	private java.util.Date periodFrom;
	private java.util.Date periodEnd;
	private int i;

	private boolean add_tie;
	private boolean add_bnk;
	private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	//private static SimpleDateFormat bdf = new SimpleDateFormat("dd.mm.yyyy");
	private static SimpleDateFormat lsdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    SimpleDateFormat sdf;
    

    
	private Window add_everywhere, add_agree_acc_card, add_card_wnd, lock_card_wnd, reissue_card_wnd, printwnd;
	private Datebox add_everywhere$ap_birthday;
	// private Datebox add_everywhere$ap_passport_date_expiration;
	private Datebox add_everywhere$ap_passport_date_registration;
	private Datebox add_everywhere$ao_address_fact_date;
	private Grid add_everywhere$addgrdl;
	private Grid add_everywhere$addgrdr;
	private Grid tfgrd, add_agree_acc_card$addgrdl;
	private Grid add_card_wnd$addgrdl;

	private RefCBox add_everywhere$acode_country;
	private RefCBox add_everywhere$acode_resident;
	private RefCBox add_everywhere$ap_code_adr_distr;
	private RefCBox add_everywhere$ap_code_adr_region;
	private RefCBox add_everywhere$ap_code_citizenship;
	private RefCBox add_everywhere$ap_code_gender;
	private RefCBox add_everywhere$ap_code_nation;
	private RefCBox add_everywhere$ap_code_tax_org;
	private RefCBox add_everywhere$ap_type_document;
	private RefCBox rcb_file_status;
	
	private RefCBox lock_card_wnd$rcb_stop_cause;
	private Row lock_card_wnd$row_stop_cause;
	private Textbox lock_card_wnd$txt_comment_text, file_id_from, file_id_to;
	private Toolbarbutton lock_card_wnd$lock_card_btn, lock_card_wnd$unlock_card_btn;
	
	private RefCBox reissue_card_wnd$card_risk_level, reissue_card_wnd$card_design;
	private Datebox reissue_card_wnd$card_expiry;
	
	private RefCBox add_agree_acc_card$agree_product,
			add_agree_acc_card$agree_bincod, add_agree_acc_card$agree_branch,
			add_agree_acc_card$acc_bal;
	private RefCBox add_agree_acc_card$acc_cond_set,
			add_agree_acc_card$card_risk_level,
			add_agree_acc_card$card_cond_set, add_agree_acc_card$card_design;
	private RefCBox add_agree_acc_card$card_chip;

	// private Textbox add_everywhere$aid_client;
	private Textbox add_everywhere$ao_city;
	private Textbox add_everywhere$ap_zip_code;
	private Textbox add_everywhere$ap_birth_place;
	private Textbox add_everywhere$ap_email_address;
	private Textbox add_everywhere$ap_family;
	private Textbox add_everywhere$ap_first_name;
	// private Textbox add_everywhere$ap_inps;
	private Textbox add_everywhere$ap_number_tax_registration;
	private Textbox add_everywhere$ap_passport_number;
	private Textbox add_everywhere$ap_passport_place_registration;
	private Textbox add_everywhere$ap_passport_serial;
	private Textbox add_everywhere$ap_patronymic;
	// private Textbox add_everywhere$ap_phone_home;
	private Textbox add_everywhere$ap_phone_mobile;
	private Textbox add_everywhere$ap_post_address;
	private Textbox add_everywhere$ap_pinfl;
	// private Textbox add_everywhere$customerId;
	private Textbox add_everywhere$ao_security_name;
	private Textbox add_everywhere$ao_post_address_fact;
	private Textbox add_agree_acc_card$id_order, add_agree_acc_card$tranz_acct, add_agree_acc_card$acc_non_reduce_balance;
	//private Textbox add_agree_acc_card$o_comment_text;
	private Textbox add_card_wnd$o_rbs_number;
	//private Textbox add_card_wnd$o_comment_text;

	// private Paging bankdataPaging;
	private PagingListModel model;
	private Listbox cardHistoryDataGrid, branch_customers, tietoGrid, accGrid, cardGrid;
    

	private Iframe printwnd$rpframe;
	
	private Textbox txbId_client;
	private Textbox txbPinfl;

	private Textbox txbPassportSerial;
	private Textbox txbName;
	private Datebox dbxB_date;
	private Textbox txbId_tieto, txbCardNumber, txbMobilePhone, txbLastName,
			txbClient_b;

    private Button getCardHistory;
    private Button historyToExcel, historyToPDF;
    private Datebox dateFrom;
    private Datebox dateTo;
    private Textbox cardNumber;

	
	public JobLogViewCtrl() {
		super('$', false, false);
		this._pageSize = 50;

		this.sdf = new SimpleDateFormat("yyyy-MM-dd");

		add_tie = false;
		add_bnk = false;

	}

	public void doAfterCompose(final Component comp) throws Exception,
			ConnectException, SQLException {
		super.doAfterCompose(comp);

		binder = new AnnotateDataBinder(comp);
		this.self.setAttribute("binder", (Object) this.binder);

		binder.bindBean("filter", this.filter);
		binder.bindBean("current", this.current);
		binder.bindBean("curr", this.curr);
		binder.bindBean("curr_tieto", this.curr_tieto);
		
		this.uid = (Integer) this.session.getAttribute("uid");
		this.un = (String) this.session.getAttribute("un");
		this.pwd = (String) this.session.getAttribute("pwd");
		this.branch = (String) this.session.getAttribute("branch");
		this.alias = (String) this.session.getAttribute("alias");
		this.curip = (String) session.getAttribute("curip");
		//com.is.tietovisa.customer.CustomerService.initConst(alias);

		//listCustomerEndpoint = HistoryService.mapConst
		//		.get(Cons.url_listcustomers);

		if (rcb_file_status.getItems().size() == 0)
			rcb_file_status.setModel(new ListModelList(
					JobLogService.getFileStates(this.alias)));
		
		binder.loadAll();
	}

	

	public void onClick$nbtn_search() {

		/*
		 * if ( (filter.getId_client()==null ||filter.getId_client().equals(""))
		 * && (filter.getP_pinfl()==null ||filter.getP_pinfl().equals("")) &&
		 * (filter.getP_first_name()==null
		 * ||filter.getP_first_name().equals("")) && (filter.getP_family
		 * ()==null ||filter.getP_family().equals("")) && (filter.getP_birthday
		 * ()==null ||filter.getP_birthday().equals("")) &&
		 * (filter.getP_phone_mobile ()==null
		 * ||filter.getP_phone_mobile().equals("")) &&
		 * (filter.getId_tieto()==null ||filter.getId_tieto().equals("")) &&
		 * (filter.getClient_b()==null ||filter.getClient_b().equals("")) ) {
		 * alert("Введите хотябы один параметр поиска!"); return; }
		 */

		
		//ISLogger.getLogger().error(	"onClick$nbtn_search start! ");

        ISLogger.getLogger().error(
		  "onClick$nbtn_search filter : "
				+ txbId_client.getValue()+"-"+filter.getId_client()+ " !!! " 
				+ txbName.getValue()+"-"+filter.getP_first_name()+ " !!! " 
				+ txbLastName.getValue()+"-"+filter.getP_family()+ " !!! " 
				+ dbxB_date.getValue()+"-"+filter.getP_birthday()+ " !!! " 
				+ txbPinfl.getValue()+"-"+filter.getP_pinfl()+ " !!! "	);

		if (filter.getBranch() == null)
			filter.setBranch(branch);
		// this.filter.setId_client("60000001");
		// this.filter.setP_pinfl("56789012340078");
		// this.bfilter = this.filter;
		filter.setFilter_type("N");
		this.filter.setEndpoint(listCustomerEndpoint);
		this.refreshModel(/* this._startPageNumber */0);

		ISLogger.getLogger().error(
				"onClick$nbtn_search end! ");
	}
	
	
	public void onClick$btn_clear_filter() {
		filter = new CustomerFilter();
		binder.loadComponent(tfgrd);
	}

	public void onClick$abtn_search() {

		/*
		 * if ( (filter.getId_client()==null ||filter.getId_client().equals(""))
		 * && (filter.getP_pinfl()==null ||filter.getP_pinfl().equals("")) &&
		 * (filter.getP_first_name()==null
		 * ||filter.getP_first_name().equals("")) && (filter.getP_family
		 * ()==null ||filter.getP_family().equals("")) && (filter.getP_birthday
		 * ()==null ||filter.getP_birthday().equals("")) &&
		 * (filter.getP_phone_mobile ()==null
		 * ||filter.getP_phone_mobile().equals("")) &&
		 * (filter.getId_tieto()==null ||filter.getId_tieto().equals("")) &&
		 * (filter.getClient_b()==null ||filter.getClient_b().equals("")) ) {
		 * alert("Введите хотябы один параметр поиска!"); return; }
		 */

		ISLogger.getLogger().error(
		"onClick$abtn_search start! ");

		ISLogger.getLogger().error(
		"onClick$abtn_search filter : "
				+ txbId_client.getValue()+"-"+filter.getId_client()+ " !!! " 
				+ txbName.getValue()+"-"+filter.getP_first_name()+ " !!! " 
				+ txbLastName.getValue()+"-"+filter.getP_family()+ " !!! " 
				+ dbxB_date.getValue()+"-"+filter.getP_birthday()+ " !!! " 
				+ txbPinfl.getValue()+"-"+filter.getP_pinfl()+ " !!! "	);

		if (filter.getBranch() == null)
			filter.setBranch(branch);
		filter.setFilter_type("A");
		this.filter.setEndpoint(listCustomerEndpoint);
		this.refreshModel(/* this._startPageNumber */0);
		ISLogger.getLogger().error(
				"onClick$abtn_search end! ");

	}

	public void onClick$tbtn_search() {

		if ((filter.getP_first_name() == null || filter.getP_first_name()
				.equals(""))
				&& (filter.getP_family() == null || filter.getP_family()
						.equals(""))
				&& (filter.getP_birthday() == null || filter.getP_birthday()
						.equals(""))
				&& (filter.getP_phone_mobile() == null || filter
						.getP_phone_mobile().equals(""))
				&& (filter.getId_tieto() == null || filter.getId_tieto()
						.equals(""))
				&& (filter.getT_client_b() == null || filter.getT_client_b()
						.equals(""))) {
			alert("Введите хотябы один поле по Tieto!");
			return;
		}

		if (filter.getBranch() == null)
			filter.setBranch(branch);
		filter.setFilter_type("T");
		// this.filter.setId_client("60000001");
		// this.filter.setP_pinfl("56789012340078");
		// this.bfilter = this.filter;
		this.filter.setEndpoint(listCustomerEndpoint);
		this.refreshModel(/* this._startPageNumber */0);
	}

	private void refreshModel(final int activePage) {
		// nci bankdan va tietodan klientlar ruyxatini olish
		this.model = new PagingListModel(activePage, this._pageSize,
				this.filter, this.alias);
		//System.out.println(this.model);
		// tietodan ham olamiz va ikkala natijani birlashtirib kursatamiz
		// List<Customer> tieto_customes =
		// CustomerService.getCustomers_tieto(this.filter, this.alias,
		// openwayEndpoint,
		// true);
		// System.out.println(tieto_customes);
		this.branch_customers.setModel((ListModel) this.model);
		if (model.getSize() > 0) {
			branch_customers.setSelectedIndex(0);
			sendSelEvt();
		}
		ISLogger.getLogger().error(
		"refreshModel end! ");
				

	}

	public void onOkToFilter(Event event) {
		onClick$nbtn_search();
	}

	public void onClick$btn_add_everywhere() {
		curr = new JobLog();
		this.add_tie = true;
		this.add_bnk = true;

		CheckNull.clearForm(this.add_everywhere$addgrdr);
		CheckNull.clearForm(this.add_everywhere$addgrdl);

		this.add_everywhere.setTitle("Создание клиента [NCIBANK] - [TIETO]");

		// loaddata qilamiz shu yerda
		
		//
		// current.setO_client_type("PR");
		curr.setCode_resident("1");
		curr.setCode_country("860");
		curr.setP_code_citizenship("860");
		// CustomerService.prepareFakeValues(current);

		this.add_everywhere.setVisible(true);
		binder.loadComponent(add_everywhere);

	}

	

	public void loadRefAccData() {

		/*
		 * if (add_card_wnd$agree_product.getItems().size() == 0)
		 * add_card_wnd$agree_product.setModel(new ListModelList(
		 * CustomerService.getSubProduct_code1_way4(alias)));
		 */

	}

	

	public void onClick$close_btn$add_everywhere() {
		this.add_everywhere.setVisible(false);
		// this.fl_edit = false;

		binder.loadComponent(add_everywhere);

	}

	public void onFocus$ao_category_client$add_everywhere() {
		// bu narsa klient qushish tugmasini modulga kirgandan sung faqatgina
		// birinchi marta
		// bosganda kerak buladi. chunki malumotlar rcombobox ga yuklanmagan
		// buladi ushanda
		// agar spravochnik malumotlar rcombobox ga hali yuklanmagan bulsa
		// binder.loadcomponent qilamiz bir marta
		/*
		 * if (add_everywhere$acode_resident.getItems().size() == 0 ||
		 * (add_everywhere$acode_resident.getValue() != current
		 * .getCode_resident())) { binder.loadComponent(add_everywhere); }
		 */
		/*
		 * System.out.println("jj "+add_everywhere$acode_resident.getItems().size
		 * ()); while (add_everywhere$acode_resident.getItems().size()==0) { try
		 * { TimeUnit.SECONDS.sleep(1); } catch(InterruptedException ex) {
		 * //Thread.currentThread().interrupt();
		 * System.out.println("err openway while delay : " + ex.getMessage()); }
		 * }
		 * System.out.println("kones "+add_everywhere$acode_resident.getItems()
		 * .size());
		 */
	}

	public void onFocus$ap_type_document$add_everywhere() {
		
		// bu narsa klient qushish tugmasini modulga kirgandan sung faqatgina
		// birinchi marta
		// bosganda kerak buladi. chunki malumotlar rcombobox ga yuklanmagan
		// buladi ushanda
		// agar spravochnik malumotlar rcombobox ga hali yuklanmagan bulsa
		// binder.loadcomponent qilamiz bir marta
		
		if (add_everywhere$ap_type_document.getItems().size() == 0 ||
		  (add_everywhere$ap_type_document.getValue() != current
		  .getP_type_document() )) { 
			binder.loadComponent(add_everywhere); 
			}
		 
		/*
		 * System.out.println("jj "+add_everywhere$acode_resident.getItems().size
		 * ()); while (add_everywhere$acode_resident.getItems().size()==0) { try
		 * { TimeUnit.SECONDS.sleep(1); } catch(InterruptedException ex) {
		 * //Thread.currentThread().interrupt();
		 * System.out.println("err openway while delay : " + ex.getMessage()); }
		 * }
		 * System.out.println("kones "+add_everywhere$acode_resident.getItems()
		 * .size());
		 */
	}
	
	

	public void onClick$btn_add_card() {
		alert("www");

	}

	

	
	private void sendSelEvt() {
		/*
		 * if (dataGrid.getSelectedIndex()==0){ btn_first.setDisabled(true);
		 * btn_prev.setDisabled(true); }else{ btn_first.setDisabled(false);
		 * btn_prev.setDisabled(false); }
		 * if(dataGrid.getSelectedIndex()==(model.getSize()-1)){
		 * btn_next.setDisabled(true); btn_last.setDisabled(true); }else{
		 * btn_next.setDisabled(false); btn_last.setDisabled(false); }
		 */
		SelectEvent evt = new SelectEvent("onSelect", branch_customers,
				branch_customers.getSelectedItems());
		Events.sendEvent(evt);
	}

	

	public void onClick$btn_excel() {
		//to_excel_(0L, 200L, 1);
		to_excel_(Long.parseLong(file_id_from.getValue()), Long.parseLong(file_id_to.getValue()), Integer.parseInt(rcb_file_status.getValue()));
	}
	
	public void to_excel_(Long pId_from, Long pId_to, int pStatus) {

		List<ExptLog> list = JobLogService.getExptLogs(pId_from, pId_to, pStatus);
		
		//List<ExptLog> list = new ArrayList<ExptLog>();
		

		Workbook workbook = new XSSFWorkbook();
		Sheet listSheet = workbook.createSheet("list_1");
		org.apache.poi.ss.usermodel.Row row = null;
		CellStyle style;
		style = workbook.createCellStyle();
		style.setDataFormat((short) 0x4); // built-in number format
		Cell cell;
		Double vSumma = 0D;
		int rowIndex = 1;
		int cellIndex = 0;
	

		row = listSheet.createRow(rowIndex++);
		cellIndex = 0;		row.createCell(cellIndex).setCellValue("Err_");
		cellIndex++;		row.createCell(cellIndex).setCellValue("Empc_file_id");
		cellIndex++;		row.createCell(cellIndex).setCellValue("Id");
		cellIndex++;		row.createCell(cellIndex).setCellValue("Card");
		cellIndex++;		row.createCell(cellIndex).setCellValue("Merchant");
		cellIndex++;		row.createCell(cellIndex).setCellValue("Term_id");
		cellIndex++;		row.createCell(cellIndex).setCellValue("Term_type");
		cellIndex++;		row.createCell(cellIndex).setCellValue("Oper");
		cellIndex++;		row.createCell(cellIndex).setCellValue("Terminal_branch");
		cellIndex++;		row.createCell(cellIndex).setCellValue("Card_branch");
		cellIndex++;		row.createCell(cellIndex).setCellValue("Tran_type");
		cellIndex++;		row.createCell(cellIndex).setCellValue("Tran_amt");
		cellIndex++;		row.createCell(cellIndex).setCellValue("Tran_type2");
		cellIndex++;		row.createCell(cellIndex).setCellValue("Tran_amt2");
		cellIndex++;		row.createCell(cellIndex).setCellValue("In_file");
		cellIndex++;		row.createCell(cellIndex).setCellValue("Mcc_code");
		cellIndex++;		row.createCell(cellIndex).setCellValue("Accnt_ccy");
		cellIndex++;		row.createCell(cellIndex).setCellValue("Tran_ccy");
		cellIndex++;		row.createCell(cellIndex).setCellValue("Country");
		cellIndex++;		row.createCell(cellIndex).setCellValue("State_id");
		cellIndex++;		row.createCell(cellIndex).setCellValue("Stat_");
		cellIndex++;		row.createCell(cellIndex).setCellValue("File_");
		cellIndex++;		row.createCell(cellIndex).setCellValue("Record_type");
		cellIndex++;		row.createCell(cellIndex).setCellValue("Line_number");
		cellIndex++;		row.createCell(cellIndex).setCellValue("Client");
		cellIndex++;		row.createCell(cellIndex).setCellValue("Card_acct");
		cellIndex++;		row.createCell(cellIndex).setCellValue("Slip_nr");
		cellIndex++;		row.createCell(cellIndex).setCellValue("Ref_number");
		cellIndex++;		row.createCell(cellIndex).setCellValue("Tran_date_time");
		cellIndex++;		row.createCell(cellIndex).setCellValue("Rec_date");
		cellIndex++;		row.createCell(cellIndex).setCellValue("Post_date");
		cellIndex++;		row.createCell(cellIndex).setCellValue("Deal_desc");
		cellIndex++;		row.createCell(cellIndex).setCellValue("Deb_cred");
		cellIndex++;		row.createCell(cellIndex).setCellValue("Accnt_amt");
		cellIndex++;		row.createCell(cellIndex).setCellValue("Terminal");
		cellIndex++;		row.createCell(cellIndex).setCellValue("Abvr_name");
		cellIndex++;		row.createCell(cellIndex).setCellValue("City");
		cellIndex++;		row.createCell(cellIndex).setCellValue("Proc_id");
		cellIndex++;		row.createCell(cellIndex).setCellValue("Internal_no");
		cellIndex++;		row.createCell(cellIndex).setCellValue("Product");
		cellIndex++;		row.createCell(cellIndex).setCellValue("Iss_mfo");
		cellIndex++;		row.createCell(cellIndex).setCellValue("Tranz_acct");
		cellIndex++;		row.createCell(cellIndex).setCellValue("Point_code");
		cellIndex++;		row.createCell(cellIndex).setCellValue("Settl_cmi");
		
		
		rowIndex = 2;
	
		for (int i = 0; i < list.size(); i++) {
			row = listSheet.createRow(rowIndex++);
			cellIndex = 0;	 row.createCell(cellIndex).setCellValue(list.get(i).getErr_());
			cellIndex++;	 row.createCell(cellIndex).setCellValue(list.get(i).getEmpc_file_id());
			cellIndex++;     row.createCell(cellIndex).setCellValue(list.get(i).getId());
            cellIndex++;	 row.createCell(cellIndex).setCellValue(list.get(i).getCard());
            cellIndex++;	 row.createCell(cellIndex).setCellValue(list.get(i).getMerchant());
            cellIndex++;	 row.createCell(cellIndex).setCellValue(list.get(i).getTerm_id());
            cellIndex++;	 row.createCell(cellIndex).setCellValue(list.get(i).getTerm_type());
            cellIndex++;	 row.createCell(cellIndex).setCellValue(list.get(i).getOper());
            cellIndex++;	 row.createCell(cellIndex).setCellValue(list.get(i).getTerminal_branch());
            cellIndex++;	 row.createCell(cellIndex).setCellValue(list.get(i).getCard_branch());
            cellIndex++;	 row.createCell(cellIndex).setCellValue(list.get(i).getTran_type());
            cellIndex++;	 row.createCell(cellIndex).setCellValue(list.get(i).getTran_amt());
            cellIndex++;	 row.createCell(cellIndex).setCellValue(list.get(i).getTran_type2());
            cellIndex++;	 row.createCell(cellIndex).setCellValue(list.get(i).getTran_amt2());
            cellIndex++;	 row.createCell(cellIndex).setCellValue(list.get(i).getIn_file());
            cellIndex++;	 row.createCell(cellIndex).setCellValue(list.get(i).getMcc_code());
            cellIndex++;	 row.createCell(cellIndex).setCellValue(list.get(i).getAccnt_ccy());
            cellIndex++;	 row.createCell(cellIndex).setCellValue(list.get(i).getTran_ccy());
            cellIndex++;	 row.createCell(cellIndex).setCellValue(list.get(i).getCountry());
            cellIndex++;	 row.createCell(cellIndex).setCellValue(list.get(i).getState_id());
            cellIndex++;	 row.createCell(cellIndex).setCellValue(list.get(i).getStat_());
            cellIndex++;	 row.createCell(cellIndex).setCellValue(list.get(i).getFile_());
            cellIndex++;	 row.createCell(cellIndex).setCellValue(list.get(i).getRecord_type());
            cellIndex++;	 row.createCell(cellIndex).setCellValue(list.get(i).getLine_number());
            cellIndex++;	 row.createCell(cellIndex).setCellValue(list.get(i).getClient());
            cellIndex++;	 row.createCell(cellIndex).setCellValue(list.get(i).getCard_acct());
            cellIndex++;	 row.createCell(cellIndex).setCellValue(list.get(i).getSlip_nr());
            cellIndex++;	 row.createCell(cellIndex).setCellValue(list.get(i).getRef_number());
            cellIndex++;	 row.createCell(cellIndex).setCellValue(list.get(i).getTran_date_time());
            cellIndex++;	 row.createCell(cellIndex).setCellValue(list.get(i).getRec_date());
            cellIndex++;	 row.createCell(cellIndex).setCellValue(list.get(i).getPost_date());
            cellIndex++;	 row.createCell(cellIndex).setCellValue(list.get(i).getDeal_desc());
            cellIndex++;	 row.createCell(cellIndex).setCellValue(list.get(i).getDeb_cred());
            cellIndex++;	 row.createCell(cellIndex).setCellValue(list.get(i).getAccnt_amt());
            cellIndex++;	 row.createCell(cellIndex).setCellValue(list.get(i).getTerminal());
            cellIndex++;	 row.createCell(cellIndex).setCellValue(list.get(i).getAbvr_name());
            cellIndex++;	 row.createCell(cellIndex).setCellValue(list.get(i).getCity());
            cellIndex++;	 row.createCell(cellIndex).setCellValue(list.get(i).getProc_id());
            cellIndex++;	 row.createCell(cellIndex).setCellValue(list.get(i).getInternal_no());
            cellIndex++;	 row.createCell(cellIndex).setCellValue(list.get(i).getProduct());
            cellIndex++;	 row.createCell(cellIndex).setCellValue(list.get(i).getIss_mfo());
            cellIndex++;	 row.createCell(cellIndex).setCellValue(list.get(i).getTranz_acct());
            cellIndex++;	 row.createCell(cellIndex).setCellValue(list.get(i).getPoint_code());
            cellIndex++;	 row.createCell(cellIndex).setCellValue(list.get(i).getSettl_cmi());

		}
		// itogo
		/*
		 * row = listSheet.createRow(rowIndex++); cellIndex = 0;
		 * row.createCell(cellIndex++).setCellValue("Итого");
		 * row.createCell(cellIndex++).setCellValue(""); cell =
		 * row.createCell(cellIndex++); cell.setCellValue(vSumma);
		 * cell.setCellStyle(style);
		 * row.createCell(cellIndex++).setCellValue("");
		 */

		listSheet.autoSizeColumn(0);
		listSheet.autoSizeColumn(1);
		listSheet.autoSizeColumn(2);
		listSheet.autoSizeColumn(3);
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			workbook.write(baos);
			//AMedia amedia = new AMedia("expt_log.xls", "xls", "application/file", baos.toByteArray());
			AMedia amedia = new AMedia("expt_log.xlsx", "xlsx", "application/file", baos.toByteArray());
			Filedownload.save(amedia);
			baos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public CustomerFilter getFilter() {
		return filter;
	}

	public void setFilter(CustomerFilter filter) {
		this.filter = filter;
	}

	public JobLog getCurrent() {
		return current;
	}

	public void setCurrent(JobLog current) {
		this.current = current;
	}

	public JobLog getCurr_tieto() {
		return curr_tieto;
	}

	public void setCurr_tieto(JobLog curr_tieto) {
		this.curr_tieto = curr_tieto;
	}
	


	public JobLog getCurr() {
		return curr;
	}

	public void setCurr(JobLog curr) {
		this.curr = curr;
	}

}
