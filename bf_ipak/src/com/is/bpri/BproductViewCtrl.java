package com.is.bpri;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Section;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.zkoss.text.DateFormats;
import org.zkoss.util.media.AMedia;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Caption;
import org.zkoss.zul.Columns;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Decimalbox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Html;
import org.zkoss.zul.Include;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Longbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;
import org.zkoss.zul.event.PagingEvent;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.LtLogger;
import com.is.bpri.asoki.Asoki;
import com.is.bpri.asoki.AsokiService;
import com.is.bpri.ball_scoring.BallScoringModel;
import com.is.bpri.ball_scoring.BallScoringViewModel;
import com.is.bpri.ball_scoring.SavedAnswer;
import com.is.bpri.bpr_change_limit.BprChangeLimit;
import com.is.bpri.bpr_change_limit.BprChangeLimitService;
import com.is.bpri.bproductAddInf.Parameter;
import com.is.bpri.bproductAddInf.bproductAddInfService;
import com.is.bpri.creategrids.CreateService;
import com.is.bpri.creategrids.CreateValues;
import com.is.bpri.main_scoring.ScoringType;
import com.is.bpri.models.Ld_sv_gate;
import com.is.bpri.product_info.ProductInfo;
import com.is.bpri.scoring.Scoring;
import com.is.bpri.template_c.TemplateFields;
import com.is.bpri.utils.Utils;
import com.is.clientcrm.ClientA;
import com.is.clientcrm.ClientAViewCtrl;
import com.is.clientcrm.ClientAService;
import com.is.userreport.RepTempl;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.RefData;
import com.is.utils.Res;
import com.is.bpri.reports.CreditReportFields;

@SuppressWarnings("serial")
public class BproductViewCtrl extends GenericForwardComposer {
	
	private Textbox card_scet,btn_score_proc;
	private Groupbox g_new, scoreGroup, creditGroup, scoreRes, client_acc, g_cards, asokiNireq;
	private Div frm, bproductmain;
	private Div grd;
	private Listbox dataGrid;
	private Tabbox tbox;
	private Tab bpr_els1, bpr_els4, bpr_limits, dep_tab, credittab, creditankettab;
	private Div addgrd;
	private Button btn_changeCard, btn_add_card_row, btn_save_card_row, btn_scoring_akmal;
	private Grid fgrdl, frmgrdl, card_grid;
	private Rows r_guarr, scoreRows, scroreResRow, cl_acc_rows, cRows, r_exp, card_rows, asokiRows;
	// private Hbox frmgrd;
	private Row row,
			// hiderow1,
			// hiderow2,
			cardRow, rfilial;
	private Toolbarbutton btn_last, btn_giveBankProduct,
			// btn_issuance_down,btn_issuance_up,
			btn_giveBankProductCreditApp, btn_giveBankProductCreditAnket,btn_print_KatmInfo;
	private Toolbarbutton btn_next, btn_savinganket;
	private Toolbarbutton btn_prev;
	private Toolbarbutton btn_first;
	private Toolbarbutton btn_back, btn_add;
	private Textbox customer;
	// , inpc,passport,tax,home_tel,mob_tel,email,phone,fax
	private Textbox aamountProvision, aname, inn_org, name_org;
	// eq_num,
	// ld_num;
	// crc_num;
	// private Textbox acustomer;
	private Textbox fcustomer,adate_limit,
			// reg_num,
			fbranch, ld_char_eq_num;
	// ni_req_eq_num;
	private Intbox fid;
	// private Datebox avdate;
	private Datebox
	// avdate_begin,
	eq_date, ld_date, fvdate,
			// eq_end_date,
			dbox_date_close;
	private Doublebox famount;
	// private Decimalbox asumm;
	private Decimalbox db_ld_amount, db_amount, ni_req_amount;
	private RefCBox aprodid, cl_type,
			// bank_inps,
			fprodid, fcurrency, fstate, cardSelect;
	private Paging bproductPaging;
	private int _pageSize = 20;
	private int _startPageNumber = 0;
	private int _totalSize = 0;
	private String alias, branch1, un, pw;
	
	private Include bprAddInfo;
	private Include bprDesc;
	private Include scoringappInc;
	private Include bprLim;
	private Include creditapp, creditanket;
	private String[] parameter = null;
	private String trans_idTemp;
	public BproductFilter filter;
	private PagingListModel model = null;
	private AnnotateDataBinder binder;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
	private DecimalFormat df = new DecimalFormat("###,###,###.##");
	private Bproduct current = new Bproduct();
	private CreditReportFields credRepField = new CreditReportFields();
	private NikiAsokiReq nikiAsokiReq = new NikiAsokiReq();
	private Integer bpr_type_value = null;
	private String btn = "";
	private String client = "";
	private Toolbar tb_search;
	private String ord = "";
	private String acc = "";
	private Listheader branch_lh;
	// private Label reg_num_label;
	private Double scoringSumma = null;
	private ClientA client_obj = null;
	private Window client_wnd = null;
	
	// private Div operation_div = null;
	private Groupbox g_guarr, product_info, g_client, g_exp;
	// ,g_operations;
	private Caption cap_client;
	private ClientAViewCtrl clientAViewCtrl;
	// private OperationViewCntrl operationViewCntrl;
	private Label product_info_currency, product_info_amount, product_info_shifr, product_info_kred_cb,
			product_info_method, product_info_speed, product_info_kred, product_info_typeZM;
	private String bank_type;
	private Listhead lh_main;
	private EventListener datebox_change_event = null;
	private EventListener datebox_select_event = null;
	private HashMap<Long, List<Row>> subQuestionRows = new HashMap<Long, List<Row>>();
	private Hbox amount_scoring_box;
	private Columns columns_scoring;
	private Grid total_grd;
	private Label total_ball, result_name;

	private String CRM_client = null;

	public BproductViewCtrl() {
		super('$', false, false);
	}

	private List<Parameter> parameters = new ArrayList<Parameter>();
	private List<BprChangeLimit> limit = new ArrayList<BprChangeLimit>();
	private List<RefData> accountList = new LinkedList<RefData>();

	// private String dateContractComp = "";
	private String bpr_id = "";
	private String scoring_id_clienta;
	private Textbox id_clienta_scoring,summa_scoring;

	@Override
	public void doAfterCompose(Component comp) throws Exception { // Банковские
																	// продукты
																	// ввод
		super.doAfterCompose(comp);
		try {
			// System.out.println("8600500439663390".substring(0, 3));
			binder = new AnnotateDataBinder(comp);
			binder.bindBean("current", this.current);
			binder.loadAll();
			alias = (String) session.getAttribute("alias");
			System.out.println("alias---RRRRRR" + alias);
			branch1 = (String) session.getAttribute("branch");
			bank_type = BprTypeService.getBank_type(branch1);
			un = (String) session.getAttribute("un");
			
			System.out.println("un---" + un);
			pw = (String) session.getAttribute("pwd");
			System.out.println("pw---" + pw);
			int uid = (Integer) session.getAttribute("uid");
			filter = new BproductFilter();
			parameter = (String[]) param.get("search_clients");
			if (parameter != null) {
				System.out.println("Client_id1=" + parameter[0]);
				filter.setCustomer(parameter[0]);
				System.out.println(filter.getCustomer());
				CRM_client = parameter[0];
			}

			if (bank_type.equals("004")) {
				lh_main.appendChild(new Listheader("Кредитная заявка"));
				lh_main.appendChild(new Listheader("Кредитный договор"));
			} else if (bank_type.equals("051")) {
				lh_main.appendChild(new Listheader("Кредитный договор"));
			}
			// TemplateService.templateMethod(application.getRealPath("book.xlsx"));
			dataGrid.setItemRenderer(new ListitemRenderer() {
				public void render(Listitem row, Object data) throws Exception {
					Bproduct pBproduct = (Bproduct) data;
					try {
						row.setValue(pBproduct);
						row.appendChild(new Listcell(pBproduct.getId() + ""));
						row.appendChild(new Listcell(pBproduct.getBranch()));
						row.appendChild(new Listcell(pBproduct.getCustomer() + ""));
						row.appendChild(new Listcell(BproductService.getCustomerName(pBproduct.getBranch(),
								Utils.getAlias(pBproduct.getBranch()), pBproduct.getCustomer() + "")));
						row.appendChild(new Listcell(pBproduct.getProdid() + ""));
						row.appendChild(new Listcell(pBproduct.getBpr_name()));
						row.appendChild(new Listcell(pBproduct.getVdate() + ""));
						row.appendChild(new Listcell(pBproduct.getCurrency()));
						Double amount = Double.parseDouble(pBproduct.getAmount());
						row.appendChild(new Listcell(df.format(amount)));
						row.appendChild(new Listcell(pBproduct.getFull_name()));
						row.appendChild(new Listcell(pBproduct.getState_name()));
						if (bank_type.equals("004")) {
							Button btn1 = new Button();
							btn1.setAttribute("current", pBproduct);
							btn1.addEventListener(Events.ON_CLICK, new EventListener() {

								@Override
								public void onEvent(Event evt) throws Exception {
									Button btn = (Button) evt.getTarget();
									Bproduct bproduct = (Bproduct) btn.getAttribute("current");
									getCreditapp(bproduct);
								}
							});
							Button btn2 = new Button();
							btn2.setAttribute("current", pBproduct);
							btn2.addEventListener(Events.ON_CLICK, new EventListener() {

								@Override
								public void onEvent(Event evt) throws Exception {
									Button btn = (Button) evt.getTarget();
									Bproduct bproduct = (Bproduct) btn.getAttribute("current");
									getCreditContract(bproduct);
								}
							});
							btn1.setImage("/images/word.png");
							btn2.setImage("/images/word.png");
							Listcell lc1 = new Listcell();
							lc1.appendChild(btn1);
							Listcell lc2 = new Listcell();
							lc2.appendChild(btn2);
							row.appendChild(lc1);
							row.appendChild(lc2);
						} else if (bank_type.equals("051")) {
							Button btn = new Button();
							btn.setAttribute("current", pBproduct);
							btn.addEventListener(Events.ON_CLICK, new EventListener() {

								@Override
								public void onEvent(Event evt) throws Exception {
									Button btn = (Button) evt.getTarget();
									Bproduct bproduct = (Bproduct) btn.getAttribute("current");
									getCreditContract051(bproduct);
								}
							});
							btn.setImage("/images/excel.png");
							Listcell lc = new Listcell();
							lc.appendChild(btn);
							row.appendChild(lc);
						}
					} catch (Exception e) {
						e.printStackTrace();
						ISLogger.getLogger().error("AMOUNT = " + pBproduct.getAmount());
						ISLogger.getLogger().error(CheckNull.getPstr(e));
					}
				}
			});
			// fizikVisible(false);
			// jurikVisible(false);
			btn_giveBankProduct.setVisible(false);
			btn_giveBankProductCreditAnket.setVisible(false);
			btn_giveBankProductCreditApp.setVisible(false);
			fcurrency.setModel(new ListModelList(com.is.bpri.BprTypeService.getCurrency(alias)));
			fstate.setModel(new ListModelList(com.is.bpri.BproductService.getStateAnket(alias)));
			row.setVisible(false);
			initDateboxEvents();
			refreshModel(_startPageNumber);
			System.out.println("337 str param " +parameter);
			getClientWnd();
			fprodid.setModel(new ListModelList(com.is.bpri.BproductService.getProdId("", "", "", alias)));
			// cl_type.setModel(new
			// ListModelList(BproductService.getTypeClientCb(alias)));
			// addEventForCustomer();
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			alert(CheckNull.getPstr(e));
		}
	}

	private void createAsokiRows(String state) {
		Row row = new Row();
		Label label_state = new Label("Состояние запроса");
		Label stateName = new Label(state);
		row.appendChild(label_state);
		row.appendChild(stateName);
		asokiRows.appendChild(row);
	}

	private void initDateboxEvents() {
		datebox_change_event = new EventListener() {

			@Override
			public void onEvent(Event evt) throws Exception {
				Datebox dbox = (Datebox) evt.getTarget();
				if (!Utils.getData(
						"select day_status from off_days where off_day=to_date('" + dbox.getText() + "','dd.MM.yyyy')",
						"").equals("1")) {
					dbox.setValue(null);
					alert("Не рабочий день");
				}
			}
		};
		datebox_select_event = new EventListener() {

			@Override
			public void onEvent(Event evt) throws Exception {
				Datebox dbox = (Datebox) evt.getTarget();
				if (!Utils.getData(
						"select day_status from off_days where off_day=to_date('" + dbox.getText() + "','dd.MM.yyyy')",
						"").equals("1")) {
					dbox.setValue(null);
					alert("Не рабочий день");
				}
			}
		};
		// eq_date.addEventListener(Events.ON_CHANGE, datebox_change_event);
		// eq_date.addEventListener(Events.ON_SELECT, datebox_select_event);
		// ld_date.addEventListener(Events.ON_CHANGE, datebox_change_event);
		// ld_date.addEventListener(Events.ON_SELECT, datebox_select_event);
		fvdate.addEventListener(Events.ON_CHANGE, datebox_change_event);
		fvdate.addEventListener(Events.ON_SELECT, datebox_select_event);
		// dbox_date_close.addEventListener(Events.ON_CHANGE,
		// datebox_change_event);
		// dbox_date_close.addEventListener(Events.ON_SELECT,
		// datebox_select_event);
	}

	public void onClick$btn_score_print() {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("1");
		List<SavedAnswer> answers = BproductService.getSavedAnswers(current.getId());
		int rowNum = 0;
		org.apache.poi.ss.usermodel.Row row = sheet.createRow(rowNum);
		row.createCell(0).setCellValue("МФО: " + current.getBranch());
		row.createCell(1).setCellValue("Клиент: " + current.getCustomer() + " ("
				+ BproductService.getCustomerName(current.getBranch(), alias, current.getCustomer()) + ")");
		row.createCell(2).setCellValue("Шаблон №: " + current.getProdid() + " ("
				+ BproductService.getBproductName(alias, current.getProdid()) + ")");
		++rowNum;
		row = sheet.createRow(rowNum);
		row.createCell(0).setCellValue("Вопрос");
		row.createCell(1).setCellValue("Ответ");
		row.createCell(2).setCellValue("Начислено баллов");
		for (SavedAnswer savedAnswers : answers) {
			createSheetHeader(sheet, ++rowNum, savedAnswers);
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			workbook.write(out);
			out.close();
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] arr = out.toByteArray();
		AMedia b = new AMedia("Template.xlsx", "xlsx", "application/vnd.ms-excel", arr);
		Filedownload.save(b);
	}

	private static void createSheetHeader(XSSFSheet sheet, int rowNum, SavedAnswer savedAnswers) {
		org.apache.poi.ss.usermodel.Row row = sheet.createRow(rowNum);

		row.createCell(0).setCellValue(savedAnswers.getQuestion());
		row.createCell(1).setCellValue(savedAnswers.getSelected_answer());
		row.createCell(2).setCellValue(savedAnswers.getBall());
	}

	public void getClientWnd() {

		// CRM bilan kirganda
		if (CRM_client != null) {
			System.out.println("CRM_client = " + CRM_client);
			initClientWnd();
		}

		else {

			if (client_wnd == null) {
				client_wnd = (Window) Executions.createComponents("clienta.zul", g_client, null);
			}
			ISLogger.getLogger().error(" client_wnd.setHeight(\"400px\"); " + client_wnd+" ");
			client_wnd.setHeight("800px");
			clientAViewCtrl = (ClientAViewCtrl) client_wnd.getAttribute("clientmain$composer");
			clientAViewCtrl.initFromBpr(null, null, alias);
			Listbox dd = (Listbox) client_wnd.getFellow("dataGrid");
			dd.setHeight("600px");
			dd.addEventListener(Events.ON_CLICK, new EventListener() {

				@Override
				public void onEvent(Event evt) throws Exception {
					initClientWnd();
					// client_obj = clientAViewCtrl.getCurrent();
					// cap_client.setLabel("Выбран клиент -
					// "+client_obj.getId_client());
					// cardSelect.setModel(new
					// ListModelList(BproductService.getClientcard(client_obj.getId_client(),
					// branch1, null, alias)));
					// aprodid.setModel(new
					// ListModelList(com.is.bpri.BproductService.getProdId(branch1,BproductService.getRegionFromBranch(branch1),client_obj.getCode_subject(),alias)));
					// fprodid.setModel(new
					// ListModelList(com.is.bpri.BproductService.getProdId(branch1,BproductService.getRegionFromBranch(branch1),client_obj.getCode_subject(),alias)));
				}
			});
		}
	}
	// private void initOperationsWnd(){
	// if(operation_div==null){
	// operation_div = (Div) Executions.createComponents("bpr/operations.zul",
	// g_operations, null);
	// }
	// operation_div.setHeight("400px");
	// operationViewCntrl = (OperationViewCntrl)
	// operation_div.getAttribute("divmain$composer");
	// operationViewCntrl.initFromBpr(BproductService.getForm_id(current.getId(),
	// current.getBranch()));
	//// Listbox dd = (Listbox) client_wnd.getFellow("dataGrid");
	// }

	private void getCreditContract(Bproduct bpr) {
		InputStream is = null;
		try {
			is = new FileInputStream(application.getRealPath("/reports/CreditContract.doc"));
			HWPFDocument doc = new HWPFDocument(is);
			String adress = BproductService.getInfoClient("post_address", bpr.getCustomer(), alias);
			String inn = BproductService.getInfoClient("number_tax_registration", bpr.getCustomer(), alias);
			String bname = BproductService.getBankName(session.getAttribute("un").toString(),
					session.getAttribute("pwd").toString(),branch1, alias);
			String wsumma = BproductService.getWsumm(bpr.getAmount(), bpr.getCurrency(), alias);
			String HOMEPHONE = BproductService.getInfoClient("HOMEPHONE", bpr.getCustomer(), alias);
			String MOBPHONE = BproductService.getInfoClient("MOBPHONE", bpr.getCustomer(), alias);
			String PASSPORT = BproductService.getInfoClient("PASSPORT", bpr.getCustomer(), alias);
			String PSINFO = BproductService.getInfoClient("PSINFO", bpr.getCustomer(), alias);
			Range range = doc.getRange();
			for (int i = 0; i < range.numSections(); i++) {
				Section s = range.getSection(i);
				s.replaceText("FIO", BproductService.getCustomerName(bpr.getBranch(), alias, bpr.getCustomer()));
				s.replaceText("ADRESS", adress == null ? "" : adress);
				s.replaceText("TAX", inn == null ? "" : inn);
				s.replaceText("MFO", bpr.getBranch());
				s.replaceText("BNAME", bname);
				s.replaceText("SUMMA", bpr.getAmount());
				s.replaceText("WSUMM", wsumma);
				s.replaceText("HOMEPHONE", HOMEPHONE == null ? " " : HOMEPHONE);
				s.replaceText("MOBPHONE", MOBPHONE == null ? " " : MOBPHONE);
				s.replaceText("PASSPORT", PASSPORT == null ? "" : PASSPORT);
				s.replaceText("PSINFO", PSINFO == null ? "" : PSINFO);
			}
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			doc.write(out);
			out.close();
			byte[] arr = out.toByteArray();
			AMedia b = new AMedia("CredContract.doc", "doc", "application/msword", arr);
			Filedownload.save(b);
		} catch (FileNotFoundException e) {

		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void getCreditContract051(Bproduct bpr) {
		InputStream is = null;
		XSSFWorkbook wb;
		String form_id = BproductService.getForm_id(bpr.getId(), bpr.getBranch(), null);
		String type_client = Utils.getData("select code_subject from client where id_client='" + bpr.getCustomer()
				+ "' and branch=" + bpr.getBranch(), "");
		try {
			is = new FileInputStream(application.getRealPath("/reports/Davr.xlsx"));
			wb = new XSSFWorkbook(is);
			wb.setForceFormulaRecalculation(true);
			FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
			if (type_client.equals("P")) {
				XSSFSheet sheet = wb.getSheetAt(0);
				XSSFRow row = sheet.getRow(1);
				XSSFCell cell = row.getCell(0);
				cell = row.getCell(1);
				cell.setCellValue(Utils.getData("select to_char(ld_date,'dd.MM.yyyy') from ld_char where id = "
						+ form_id + " and branch = '" + bpr.getBranch() + "'", ""));
				row = sheet.getRow(2);
				cell = row.getCell(1);
				cell.setCellValue(Utils.getData("select to_char(ld_date,'dd.MM.yyyy') from ld_char where id = "
						+ form_id + " and branch = '" + bpr.getBranch() + "'", ""));
				row = sheet.getRow(3);
				cell = row.getCell(1);
				cell.setCellValue(Utils.getData(
						"select ld_num from ld_char where id=" + form_id + " and branch = '" + bpr.getBranch() + "'",
						""));
				row = sheet.getRow(4);
				cell = row.getCell(1);
				cell.setCellValue(Utils.getData(
						"select crc_num from ld_char where id=" + form_id + " and branch = '" + bpr.getBranch() + "'",
						""));
				row = sheet.getRow(5);
				cell = row.getCell(1);
				cell.setCellValue(Utils.getData("select name from client_p where id = '" + bpr.getCustomer()
						+ "' and branch = '" + bpr.getBranch() + "'", ""));
				row = sheet.getRow(6);
				cell = row.getCell(1);
				cell.setCellValue(Utils.getData("select to_char(birthday,'dd.MM.yyyy') from client_p where id = '"
						+ bpr.getCustomer() + "' and branch = '" + bpr.getBranch() + "'", ""));
				row = sheet.getRow(7);
				cell = row.getCell(1);
				cell.setCellValue(Utils.getData("select birth_place from client_p where id = '" + bpr.getCustomer()
						+ "' and branch = '" + bpr.getBranch() + "'", ""));
				row = sheet.getRow(8);
				cell = row.getCell(1);
				cell.setCellValue(
						Utils.getData("select passport_serial||' '||passport_number from client_p where id = '"
								+ bpr.getCustomer() + "' and branch = '" + bpr.getBranch() + "'", ""));
				row = sheet.getRow(9);
				cell = row.getCell(1);
				cell.setCellValue(Utils
						.getData("select to_char(passport_date_registration,'dd.MM.yyyy') from client_p where id = '"
								+ bpr.getCustomer() + "' and branch = '" + bpr.getBranch() + "'", ""));
				row = sheet.getRow(10);
				cell = row.getCell(1);
				cell.setCellValue(Utils.getData("select passport_place_registration from client_p where id = '"
						+ bpr.getCustomer() + "' and branch = '" + bpr.getBranch() + "'", ""));
				row = sheet.getRow(11);
				cell = row.getCell(1);
				cell.setCellValue(Utils.getData("select post_address from client_p where id = '" + bpr.getCustomer()
						+ "' and branch = '" + bpr.getBranch() + "'", ""));
				row = sheet.getRow(12);
				cell = row.getCell(1);
				cell.setCellValue(Utils.getData(
						"select name_org from ni_req where id = (select niki_id from ld_char where id = " + form_id
								+ " and branch = '" + bpr.getBranch() + "') and branch = '" + bpr.getBranch() + "'",
						""));
				row = sheet.getRow(13);
				cell = row.getCell(1);
				cell.setCellValue(Utils.getData("select ld_amount/100 from ld_char where id=" + form_id
						+ " and branch = '" + bpr.getBranch() + "'", ""));
				row = sheet.getRow(14);
				cell = row.getCell(1);
				cell.setCellValue(Utils.getData("select rate from ld_rate where id = " + form_id + " and branch = '"
						+ bpr.getBranch() + "' and exp_id=1", ""));
				row = sheet.getRow(15);
				cell = row.getCell(1);
				cell.setCellValue(Utils.getData("select account from ld_account where id = " + form_id
						+ " and branch = '" + bpr.getBranch() + "' and acc_type_id = 8", ""));
				row = sheet.getRow(16);
				cell = row.getCell(1);
				cell.setCellValue(bpr.getCustomer());
				row = sheet.getRow(17);
				cell = row.getCell(1);
				cell.setCellValue(Utils.getData("select to_char(v_date,'dd.MM.yyyy') from ld_graf where id = " + form_id
						+ " and branch = '" + bpr.getBranch() + "' and oper_id=1", ""));
			}
			try {
				evaluator.evaluateAll();
			} catch (RuntimeException e) {
			}
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			wb.write(out);
			out.close();
			byte[] arr = out.toByteArray();
			AMedia b = new AMedia("Template.xlsx", "xlsx", "application/vnd.ms-excel", arr);
			Filedownload.save(b);
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void getCreditapp(Bproduct bpr) {
		InputStream is = null;
		try {
			is = new FileInputStream(application.getRealPath("/reports/CreditApp.docx"));
			XWPFDocument doc = new XWPFDocument(is);
			String adress = BproductService.getInfoClient("post_address", bpr.getCustomer(), alias);
			String phone = BproductService.getInfoClient("PHONE", bpr.getCustomer(), alias);
			String inn = BproductService.getInfoClient("number_tax_registration", bpr.getCustomer(), alias);
			String bname = BproductService.getBankName(session.getAttribute("un").toString(),
					session.getAttribute("pwd").toString(),branch1, alias);
			String cardacc = BproductService.getCardAcc(session.getAttribute("un").toString(),
					session.getAttribute("pwd").toString(),BproductService.getCardReg(bpr.getId(), alias), alias);
			String wsumma = BproductService.getWsumm(bpr.getAmount(), bpr.getCurrency(), alias);
			for (XWPFParagraph p : doc.getParagraphs()) {
				List<XWPFRun> runs = p.getRuns();
				if (runs != null) {
					for (XWPFRun r : runs) {
						String str = r.getText(0);
						if (str != null) {
							str = str.replace("FIO",
									BproductService.getCustomerName(bpr.getBranch(), alias, bpr.getCustomer()));
							str = str.replace("ADRESS", adress == null ? "" : adress);
							str = str.replace("PHONE", phone == null ? "" : phone);
							str = str.replace("TAX", inn == null ? "" : inn);
							str = str.replace("MFO", bpr.getBranch());
							str = str.replace("BNAME", bname);
							if (cardacc != null && !cardacc.equals("")) {
								str = str.replace("CARDACC", cardacc);
							}
							str = str.replace("SUMMA", bpr.getAmount());
							str = str.replace("WSUMM", wsumma);
							r.setText(str, 0);
						}
					}
				}
			}
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			doc.write(out);
			out.close();
			byte[] arr = out.toByteArray();
			AMedia b = new AMedia("CredApp.docx", "docx",
					"application/vnd.openxmlformats-officedocument.wordprocessingml.document", arr);
			Filedownload.save(b);
		} catch (FileNotFoundException e) {

		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			try {
				if (is != null) {
					is.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private Row getRow(Component label, Component input) {
		Row row = new Row();
		if (label != null) {
			row.appendChild(label);
		}
		if (input != null) {
			row.appendChild(input);
		}
		return row;
	}

	private Row getRow(Component label, Component input, Component label1, Component input1) {
		Row row = new Row();
		if (label != null) {
			row.appendChild(label);
		}
		if (input != null) {
			row.appendChild(input);
		}
		if (label1 != null) {
			row.appendChild(label1);
		}
		if (input1 != null) {
			row.appendChild(input1);
		}
		return row;
	}

	private Label getLabel(String value) {
		Label label = new Label();
		label.setValue(value);
		return label;
	}

	private void addEvent(RefCBox rbox) {
		rbox.addEventListener(Events.ON_SELECT, new EventListener() {

			@Override
			public void onEvent(Event evt) throws Exception {
				RefCBox refbox = (RefCBox) evt.getTarget();
				acc = refbox.getValue();
				ord = refbox.getAttribute("order") + "";
			}
		});
	}

	private void createBallScoringGrd() {
		if (btn.equals("add")) {
			List<BallScoringModel> list = BproductService
					.getBallModel(aprodid.getValue().equals("") ? current.getProdid() + "" : aprodid.getValue());
			List<BallScoringViewModel> balls = new ArrayList<BallScoringViewModel>();
			for (int i = 0; i < list.size(); i++) {
				BallScoringViewModel model = new BallScoringViewModel();
				scoreRows.appendChild(getRow(list.get(i), model));
				balls.add(model);
				scoreRows.setAttribute("model", balls);
			}
		} else {
			List<SavedAnswer> answers = BproductService.getSavedAnswers(current.getId());
			for (int i = 0; i < answers.size(); i++) {
				scoreRows.appendChild(getRow(answers.get(i)));
			}
		}
	}

	private Row getRow(SavedAnswer answers) {
		Row row = new Row();
		Label question = new Label(answers.getQuestion());
		Label answer = new Label(answers.getSelected_answer());
		Label ball = new Label(answers.getBall().toString());
		scoringResult(ball, current.getProdid() + "");
		row.appendChild(question);
		row.appendChild(answer);
		row.appendChild(ball);
		return row;
	}

	private Row getRow(BallScoringModel ball, BallScoringViewModel model) {
		Row row = new Row();
		row.setVisible(ball.isVisible());
		row.setAttribute("model", model);
		Label label = new Label(ball.getQuestion_name());
		model.setQuestion(ball.getQuestion_name());
		Label ball_label = new Label();
		RefCBox rbox = new RefCBox();
		rbox.setAttribute("ball_label", ball_label);
		List<RefData> models = new ArrayList<RefData>();
		for (int i = 0; i < ball.getAnswers().size(); i++) {
			rbox.setAttribute(ball.getAnswers().get(i).getA_id() + "", ball.getAnswers().get(i).getA_ball());
			models.add(new RefData(ball.getAnswers().get(i).getA_id() + "", ball.getAnswers().get(i).getA_name()));
		}
		rbox.setModel(new ListModelList(models));
		rbox.setAttribute("model", model);
		rbox.addEventListener(Events.ON_CHANGE, new EventListener() {

			@Override
			public void onEvent(Event evt) throws Exception {
				try {
					RefCBox rbox = (RefCBox) evt.getTarget();
					if (!rbox.getValue().equals("")) {
						onChangeRefCBox(rbox);
						BallScoringViewModel model = (BallScoringViewModel) rbox.getAttribute("model");
						model.setSelectedAnswer(rbox.getText());
						Label label = (Label) rbox.getAttribute("ball_label");
						label.setValue(rbox.getAttribute(rbox.getValue()) + "");
						model.setResult_ball(Long.parseLong(label.getValue()));
						List<?> temp_row = scoreRows.getChildren();
						total_ball.setValue("");
						for (int i = 0; i < temp_row.size(); i++) {
							if (temp_row.get(i) instanceof Row) {
								Row t_row = (Row) temp_row.get(i);
								BallScoringViewModel bsvm = (BallScoringViewModel) t_row.getAttribute("model");
								bsvm.setVisible(t_row.isVisible());
								ISLogger.getLogger().error("ROW SIZE = " + temp_row.size());
								if (!t_row.isVisible()) {
									ISLogger.getLogger().error("not visible ");
									List<?> objects = t_row.getChildren();
									for (int j = 0; j < objects.size(); j++) {
										if (objects.get(j) instanceof RefCBox) {
											RefCBox refbox = (RefCBox) objects.get(j);
											refbox.setSelecteditem("-1");
											onChangeRefCBox(refbox);
										}
									}
								} else {
									ISLogger.getLogger().error("visible");
									List<?> objects = t_row.getChildren();
									if (objects.get(2) instanceof Label) {
										Label t_label = (Label) objects.get(2);
										System.out.println("LABEL VALUE = " + t_label.getValue());
										ISLogger.getLogger().error("LABEL VALUE = " + t_label.getValue());
										scoringResult(t_label, aprodid.getValue());
									}
								}
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		row.appendChild(label);
		row.appendChild(rbox);
		row.appendChild(ball_label);
		row.setAttribute("name", ball.getQuestion_name());
		if (ball.getMap_id() != null) {
			List<Row> list = subQuestionRows.get(ball.getMap_id());
			if (list == null)
				list = new ArrayList<Row>();
			list.add(row);
			subQuestionRows.put(ball.getMap_id(), list);
		}
		return row;
	}

	private void scoringResult(Label t_label, String bpr_id) {
		Long total = total_ball.getValue() == null || total_ball.getValue().equals("") ? 0
				: Long.parseLong(total_ball.getValue());
		System.out.println("TOTAL BALL = " + total_ball.getValue());
		System.out.println("total = " + total);
		ISLogger.getLogger().error("TOTAL BALL = " + total_ball.getValue());
		ISLogger.getLogger().error("total = " + total);
		total += t_label.getValue() == null || t_label.getValue().equals("") ? 0 : Long.parseLong(t_label.getValue());
		total_ball.setValue(total + "");
		System.out.println("next total = " + total);
		ISLogger.getLogger().error("next total = " + total);
		String[] array = BproductService.getScoringResult(bpr_id, total).split("#@");
		result_name.setValue("Результат: " + array[0]);
		if (array.length > 1 && !array[0].equals("") && array[1].equals("2")) {
			btn_giveBankProduct.setDisabled(true);
			btn_giveBankProductCreditApp.setDisabled(true);
			btn_giveBankProductCreditAnket.setDisabled(true);
			btn_savinganket.setDisabled(true);
		} else {
			btn_giveBankProduct.setDisabled(false);
			btn_giveBankProductCreditApp.setDisabled(false);
			btn_giveBankProductCreditAnket.setDisabled(false);
			btn_savinganket.setDisabled(false);
		}
	}

	@SuppressWarnings("unchecked")
	private void onChangeRefCBox(RefCBox rbox) {
		List<Row> row = (List<Row>) rbox.getAttribute("row");
		if (row != null) {
			for (int i = 0; i < row.size(); i++) {
				Row temp = (Row) row.get(i);
				BallScoringViewModel model = (BallScoringViewModel) temp.getAttribute("model");
				model.setVisible(false);
				temp.setVisible(false);
			}
		} else
			row = new ArrayList<Row>();
		if (!rbox.getValue().equals("")) {
			List<Row> list = subQuestionRows.get(Long.parseLong(rbox.getValue()));
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					list.get(i).setVisible(true);
					BallScoringViewModel ball = (BallScoringViewModel) list.get(i).getAttribute("model");
					ball.setVisible(true);
					if (!row.contains(list.get(i)))
						row.add(list.get(i));
					rbox.setAttribute("row", row);
				}
			}
		}
	}

	private void createScoringGrd(String customer, int bpr_type) {
		RefCBox rbox1 = null;
		RefCBox rbox2 = null;
		Decimalbox d1 = null;
		Decimalbox d2 = null;
		Textbox t1 = null;
		Textbox t2 = null;
		String lr1 = null;
		String lr2 = null;
		String ld1 = null;
		String ld2 = null;
		String lt1 = null;
		String lt2 = null;
		try {
			List<BproductScoring> list = BproductService.getParams(1, bpr_type, alias);
			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getParam_type().equals("INPUT")) {
						if (list.get(i).getType().equalsIgnoreCase("rbox")) {
							String temp = BproductService.getAccMask(list.get(i).getName(), aprodid.getValue(), alias);
							if (rbox1 == null) {
								rbox1 = new RefCBox();
								rbox1.setWidth("100%");
								if (list.get(i).getMandatory().equals("0")) {
									rbox1.setReadonly(true);
									rbox1.setButtonVisible(false);
									rbox1.setAttribute("formula", list.get(i).getSelects());
								}
								rbox1.setAttribute("order", list.get(i).getOrd());
								addEvent(rbox1);
								rbox1.setModel(new ListModelList(BproductService
										.getList(list.get(i).getSelects() + temp, customer, branch1, alias)));
								lr1 = list.get(i).getName_ru();
							} else {
								rbox2 = new RefCBox();
								if (list.get(i).getMandatory().equals("0")) {
									rbox2.setReadonly(true);
									rbox2.setButtonVisible(false);
									rbox2.setAttribute("formula", list.get(i).getSelects());
								}
								rbox2.setAttribute("order", list.get(i).getOrd());
								addEvent(rbox2);
								rbox2.setWidth("100%");
								rbox2.setModel(new ListModelList(BproductService
										.getList(list.get(i).getSelects() + temp, customer, branch1, alias)));
								lr2 = list.get(i).getName_ru();
							}
						} else {
							if (list.get(i).getType().equalsIgnoreCase("number")) {
								if (d1 == null) {
									d1 = new Decimalbox();
									if (list.get(i).getMandatory().equals("0")) {
										d1.setReadonly(true);
										d1.setAttribute("formula", list.get(i).getSelects());
									}
									d1.setAttribute("order", list.get(i).getOrd());
									d1.setWidth("100%");
									ld1 = list.get(i).getName_ru();
								} else {
									d2 = new Decimalbox();
									if (list.get(i).getMandatory().equals("0")) {
										d2.setReadonly(true);
										d2.setAttribute("formula", list.get(i).getSelects());
									}
									d2.setAttribute("order", list.get(i).getOrd());
									d2.setWidth("100%");
									ld2 = list.get(i).getName_ru();
								}
							} else if (list.get(i).getType().equalsIgnoreCase("string")) {
								if (t1 == null) {
									t1 = new Textbox();
									if (list.get(i).getMandatory().equals("0")) {
										t1.setReadonly(true);
										t1.setAttribute("formula", list.get(i).getSelects());
									}
									t1.setAttribute("order", list.get(i).getOrd());
									t1.setWidth("100%");
									lt1 = list.get(i).getName_ru();
								} else {
									t2 = new Textbox();
									if (list.get(i).getMandatory().equals("0")) {
										t2.setReadonly(true);
										t2.setAttribute("formula", list.get(i).getSelects());
									}
									t2.setAttribute("order", list.get(i).getOrd());
									t2.setWidth("100%");
									lt2 = list.get(i).getName_ru();
								}
							}
						}
						if (i > 0) {
							int index = i + 1;
							if (index % 2 == 0 || list.size() - 1 == i) {
								String label1 = null;
								String label2 = null;
								Component comp1 = null;
								Component comp2 = null;
								if (lr1 != null) {
									label1 = lr1;
									if (comp1 == null) {
										comp1 = rbox1;
									}
								}
								if (ld1 != null) {
									if (comp1 == null) {
										comp1 = d1;
										label1 = ld1;
									} else {
										comp2 = d1;
										label2 = ld1;
									}
								}
								if (lt1 != null) {
									if (comp1 == null) {
										comp1 = t1;
										label1 = lt1;
									} else {
										comp2 = t1;
										label2 = lt1;
									}
								}
								if (lr2 != null) {
									if (comp1 == null) {
										comp1 = rbox2;
										label1 = lr2;
									} else {
										comp2 = rbox2;
										label2 = lr2;
									}
								}
								if (ld2 != null) {
									if (comp1 == null) {
										comp1 = d2;
										label1 = ld2;
									} else {
										comp2 = d2;
										label2 = ld2;
									}
								}
								if (lt2 != null) {
									if (comp1 == null) {
										comp1 = t2;
										label1 = lt2;
									} else {
										comp2 = t2;
										label2 = lt2;
									}
								}
							//	scoreRows.appendChild(getRow(getLabel(label1), comp1, getLabel(label2), comp2));
								if (rbox1 != null) {
									rbox1 = null;
									lr1 = null;
								}
								if (rbox2 != null) {
									rbox2 = null;
									lr2 = null;
								}
								if (d1 != null) {
									d1 = null;
									ld1 = null;
								}
								if (d2 != null) {
									d2 = null;
									ld2 = null;
								}
								if (t1 != null) {
									t1 = null;
									lt1 = null;
								}
								if (t2 != null) {
									t2 = null;
									lt2 = null;
								}
							}
						}
					} else if (list.get(i).getParam_type().equals("PARAM")) {
						if (list.get(i).getType().equalsIgnoreCase("DATE")) {
							Res res = new Res();
							scoreRows.setAttribute("dateValue", sdf.format(Utils.getInfoDate(alias, res)));
							scoreRows.setAttribute("dateOrder", list.get(i).getOrd());
						}
					}
				}
			} else {
				alert("Не найдены параметры для Скоринга");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onClick$btn_score_proc() throws SQLException {
		card_scet.getValue();
		System.out.println("card: "+card_scet.getValue());
		
		//System.out.println("branch1 " +branch1+" proc_name "+client_obj.getId_client());
		String proc_name = BproductService.getScroringType_Proc(aprodid.getValue());
		System.out.println("proc_name: "+proc_name+" user_id: "+scoring_id_clienta);
		
		if(!id_clienta_scoring.getValue().isEmpty() ) {
			if((!card_scet.getValue().isEmpty()) ){
				if((!summa_scoring.getValue().isEmpty())){
					String ssInfo = BproductService.execute_transaction(branch1, id_clienta_scoring.getValue(), card_scet.getValue(),summa_scoring.getValue(), proc_name);
					System.out.println("ssInfo: "+ssInfo);
					alert(ssInfo);
				}else {
					alert("Пожалуйста укажите Сумму");
				}
				
			}else {
				alert("Пожалуйста укажите счет карты");
			}
			
		}else {
			alert("Пожалуйста укажите id клиента");
		}
		
		
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public void onClick$btn_score() {
		try {
			List<Scoring> scoring = BproductService.getScoring(aprodid.getValue(), alias);
			if (scoring.isEmpty()) {
				alert("В шаблоне не указанны параметры для скоринга");
				return;
			} else {
				String formula = null;
				int arrSize = 0;
				List<String> paramsWithOrder = new ArrayList<String>();
				List<String> paramsWithOutOrder = new ArrayList<String>();
				List<Row> list = scoreRows.getChildren();
				String[] datas = null;
				String subSelect = "";
				String acc_mask = BproductService.getAcc_mask(aprodid.getValue(), alias);
				for (int i = 0; i < list.size(); i++) {
					List<?> tempcomp = list.get(i).getChildren();
					for (int j = 0; j < tempcomp.size(); j++) {
						Component comp = (Component) tempcomp.get(j);
						if (comp.getAttribute("formula") != null) {
							formula = comp.getAttribute("formula") + "";
							char[] array = formula.toCharArray();
							for (int k = 0; k < array.length; k++) {
								if (array[k] == '?') {
									arrSize++;
								}
							}
						}
					}
				}
				datas = new String[arrSize];
				String order = null;
				for (int i = 0; i < scoring.size(); i++) {
					order = scoring.get(i).getOrd();
					if (order != null && !order.equals("") && !order.equals("7")) {
						if (order.contains(" ")) {
							String[] tempstr = order.split(" ");
							for (int k = 0; k < tempstr.length; k++) {
								int ind = Integer.parseInt(tempstr[k]) - 1;
								datas[ind] = scoring.get(i).getDef_value();
							}
						} else {
							int ind = Integer.parseInt(order) - 1;
							datas[ind] = scoring.get(i).getDef_value();
						}
					}
				}
				if (acc_mask != null && !acc_mask.equals("") && !acc_mask.equals("%")) {
					String acc[] = acc_mask.split(",");
					for (int i = 0; i < acc.length; i++) {
						if (acc[i].contains("-")) {
							String withOrder = "'" + acc[i].split("-")[0] + acc[i].split("-")[1] + "'";
							paramsWithOrder.add(withOrder);
						} else {
							String withOutOrder = "'" + acc[i] + "'";
							paramsWithOutOrder.add(withOutOrder);
						}
					}
					subSelect = BproductService.getSubSelectParam(paramsWithOrder, paramsWithOutOrder, alias);
				}
				formula += subSelect;
				formula = formula.replace("<branch>", "'" + branch1 + "'");
				order = ord;
				if (order.contains(" ")) {
					String[] tempstr = order.split(" ");
					for (int k = 0; k < tempstr.length; k++) {
						int ind = Integer.parseInt(tempstr[k]) - 1;
						datas[ind] = acc;
					}
				} else {
					int ind = Integer.parseInt(order) - 1;
					datas[ind] = acc;
				}
				if (scoreRows.getAttribute("dateOrder") != null) {
					order = scoreRows.getAttribute("dateOrder") + "";
					if (order != null && !order.equals("")) {
						if (order.contains(" ")) {
							String[] tempstr = order.split(" ");
							for (int k = 0; k < tempstr.length; k++) {
								int ind = Integer.parseInt(tempstr[k]) - 1;
								datas[ind] = scoreRows.getAttribute("dateValue") + "";
							}
						} else {
							int ind = Integer.parseInt(order) - 1;
							datas[ind] = scoreRows.getAttribute("dateValue") + "";
						}
					}
				}
				for (int i = 0; i < list.size(); i++) {
					List<?> tempcomp = list.get(i).getChildren();
					for (int k = 0; k < tempcomp.size(); k++) {
						if (tempcomp.get(k) instanceof Decimalbox) {
							Decimalbox d = (Decimalbox) tempcomp.get(k);
							if (d.isReadonly()) {
								Res res = new Res();
								d.setFormat("#0.00");
								BigDecimal bd = BproductService.getScoreResult(datas, formula, alias, res);
								if (bd != null) {
									bd = bd.divide(new BigDecimal(100));
								}
								boolean readSumm = BproductService.getReadSumm(aprodid.getValue(), alias) == 1;
								Date v_date = BproductService.getV_date(acc, alias);
								Date current_date = Utils.getInfoDate(alias, res);
								Integer mounth_ctrl = BproductService.getMounth_value(aprodid.getValue(), alias);
								if (mounth_ctrl != null && mounth_ctrl != 0) {
									Integer newmounth = (current_date.getYear() * 12 + current_date.getMonth())
											- (v_date.getYear() * 12 + v_date.getMonth());
									if (newmounth >= mounth_ctrl) {
										bd = bd.subtract(bd);
										alert("Выдача кредита по результату скоринга не возможна! На счет ПК нет поступления денежных средств "
												+ newmounth + " месяца(ев)!");
										return;
									}
								}
								Double amount = BproductService.getAmount(aprodid.getValue(), alias);
								if (readSumm) {
									if (amount != null && amount > 0.1) {
										if (bd.doubleValue() > amount) {
											d.setValue(bd);
											DecimalFormat df = new DecimalFormat();
											df.setMaximumFractionDigits(2);
											df.setMinimumFractionDigits(0);
											df.setGroupingUsed(false);
											BigDecimal temp_bd = new BigDecimal(amount);
											if (db_ld_amount != null) {
												db_ld_amount.setValue(temp_bd);
												if (db_amount != null)
													db_amount.setValue(temp_bd);
												if (ni_req_amount != null)
													ni_req_amount.setValue(temp_bd);
												System.out.println(temp_bd + " temp_bd");
											} else {
												alert("Непредвиденная ошибка!");
												return;
											}
											alert("Сумма скоринга (" + df.format(bd)
													+ ") превышает максимальную сумму договора (" + df.format(amount)
													+ ") по шаблону");
											return;
										}
									}
									scoringSumma = bd.doubleValue();
									// cardSelect.setModel(new
									// ListModelList(BproductService.getCardNumberFromAcc(acc,branch1
									// , alias)));
								}

								d.setValue(bd);
								if (db_ld_amount != null) {
									db_ld_amount.setValue(bd);
									if (db_amount != null)
										db_amount.setValue(bd);
									if (ni_req_amount != null)
										ni_req_amount.setValue(bd);
									System.out.println(bd + " bd");
								} else {
									alert("Непредвиденная ошибка!");
									return;
								}
							}
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
	}

	private Row getExpRow(String exp_value, String rate_value) {
		Row row = new Row();
		Label exp_type = new Label("Вид ставки");
		Label rate = new Label("Процент");
		Label exp_value_label = new Label(exp_value);
		Label rate_value_label = new Label(rate_value);
		row.appendChild(exp_type);
		row.appendChild(exp_value_label);
		row.appendChild(rate);
		row.appendChild(rate_value_label);
		return row;
	}

	private Row getGuarrRow(String guarr) {
		Row row = new Row();
		Label label = new Label(guarr);
		row.appendChild(label);
		return row;
	}

	public void onSelect$aprodid() {
		try {
			if (client_obj != null) {//вход 395
				if(aprodid.getValue().equals("28")) {
				//	btn_scoring_akmal.setVisible(true);
				}
				
				System.out.println("curr_prodid: "+current.getProdid()+"apr_id : "+aprodid.getValue());
				bpr_type_value = BproductService.getBpr_type_value(alias, Integer
						.parseInt(aprodid.getValue().equals("") ? current.getProdid() + "" : aprodid.getValue()));
				Res res = new Res();
				createCRowsGrid();
				product_info.setVisible(true);
				scoreGroup.setVisible(false);
				if (db_ld_amount != null && !btn.equals("double")) {
					if (bpr_type_value == 1) {
						// asumm.setReadonly(readSumm);
						db_ld_amount.setValue(BigDecimal.ZERO);
					} else {
						db_ld_amount.setReadonly(false);
						db_ld_amount.setValue(BigDecimal.ZERO);
					}
				}
				Utils.clearRows(scoreRows);
				String prod_id = "";
				if (btn.equals("add")) {
					prod_id = aprodid.getValue();
				} else if (btn.equals("double")) {
					prod_id = current.getProdid() + "";
				}
				ProductInfo info = BproductService.getProductInfo(prod_id, alias);
				Double amount = null;
				String currency = "";
				String kred = "";
				String kred_cb = "";
				String method = "";
				String shifr = "";
				String speed = "";
				String type_zm = "";
				if (info != null && info.getLd_char() != null) {
					if (info.getLd_char().getMethod() != null) {
						method = info.getLd_char().getMethod();
					}
					if (info.getLd_char().getKred_cb() != null) {
						kred_cb = info.getLd_char().getKred_cb();
					}
					if (info.getLd_char().getKred() != null) {
						kred = info.getLd_char().getKred();
					}
					if (info.getLd_char().getCurrency() != null) {
						currency = info.getLd_char().getCurrency();
					}
					if (info.getLd_char().getAmount() != null) {
						amount = Double.parseDouble(info.getLd_char().getAmount());
					}
					if (info.getLd_char().getShifr() != null) {
						shifr = info.getLd_char().getShifr();
					}
					if (info.getLd_char().getSpeed() != null) {
						speed = info.getLd_char().getSpeed();
					}
					if (info.getLd_char().getTypeZM() != null) {
						type_zm = info.getLd_char().getTypeZM();
					}
				}
				product_info_amount.setValue(amount == null || amount == 0 ? "Не ограничена" : df.format(amount));
				product_info_currency.setValue(currency.equals("") ? "Поле не заполнено в шаблоне" : currency);
				product_info_kred.setValue(kred.equals("") ? "Поле не заполнено в шаблоне" : kred);
				product_info_kred_cb.setValue(kred_cb.equals("") ? "Поле не заполнено в шаблоне" : kred_cb);
				product_info_method.setValue(method.equals("") ? "Поле не заполнено в шаблоне" : method);
				product_info_shifr.setValue(shifr.equals("") ? "Поле не заполнено в шаблоне" : shifr);
				product_info_speed.setValue(speed.equals("") ? "Поле не заполнено в шаблоне" : speed);
				product_info_typeZM.setValue(type_zm.equals("") ? "Поле не заполнено в шаблоне" : type_zm);
				Utils.clearRows(r_exp);
				if (info.getLd_exps() != null) {
					for (int i = 0; i < info.getLd_exps().length; i++) {
						r_exp.appendChild(getExpRow(info.getLd_exps()[i].getExp_id(), info.getLd_exps()[i].getRate()));
					}
				}
				Utils.clearRows(r_guarr);
				if (info.getLd_guarr_type() != null) {
					for (int i = 0; i < info.getLd_guarr_type().length; i++) {
						r_guarr.appendChild(getGuarrRow(info.getLd_guarr_type()[i]));
					}
				} else {
					r_guarr.appendChild(getGuarrRow("Не заполнено обеспечение в шаблоне!"));
				}
				g_exp.setVisible(true);
				g_guarr.setVisible(true);
				onChange$acustomer();
				if (!btn.equals("double"))
					g_cards.setVisible(true);
				System.out.println("BPR TYPE = " + bpr_type_value);
				if (bpr_type_value == 2) { // мимо 395
					row.setVisible(true);
					bpr_els4.setVisible(true);
					bpr_limits.setVisible(false);
					// eq_end_date.setValue(null);
					cardRow.setVisible(false);
					card_grid.setVisible(false);
				} else {
					bpr_els4.setVisible(false);
					// creditGroup.setVisible(true);
					if (bpr_type_value == 3) {
						bpr_limits.setVisible(false);
						cardRow.setVisible(false);
						card_grid.setVisible(false);
						// eq_end_date.setValue(null);
					} else { // вход 395
						cardSelect.setModel(new ListModelList(
								BproductService.getClientcard(client_obj.getId_client(), branch1, null, alias)));
						Date date = Utils.getInfoDate(alias, res);
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(date);
						calendar.add(Calendar.YEAR, 1);
						date = calendar.getTime();
						cardRow.setVisible(true);
						card_grid.setVisible(true);
					}
					if (client_obj.getId_client() != null && !client_obj.getId_client().equals("")) { //вход 395
						ScoringType st = BproductService.getScroringType(Integer.parseInt(
								aprodid.getValue().equals("") ? current.getProdid() + "" : aprodid.getValue()));
						List<Scoring> scoring = BproductService.getScoring(aprodid.getValue(), alias);
						scoreGroup.setVisible(!scoring.isEmpty());
						if (bpr_type_value == 1 && st != null && st.getId() == 1) { //вход 395
							createScoringGrd(client_obj.getId_client(), bpr_type_value);
							amount_scoring_box.setVisible(true);
							columns_scoring.setVisible(false);
							total_grd.setVisible(false);
						} else if (st != null && st.getId() == 2) {
							createBallScoringGrd();
							amount_scoring_box.setVisible(false);
							columns_scoring.setVisible(true);
							total_grd.setVisible(true);
						} else {
							scoreGroup.setVisible(false);
						}
					}
					row.setVisible(false);
				}
				g_new.setVisible(true);
				List<RefData> list = BproductService.getWorkFromClient(client_obj.getId_client(), branch1, alias);
				if (inn_org != null && !list.isEmpty()) {
					inn_org.setValue(list.get(0).getData());
				}
				if (name_org != null && !list.isEmpty()) { //вход 395
					name_org.setValue(list.get(0).getLabel());
				}
			} else {
				alert("Клиент не выбран");
				aprodid.setValue(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onSelect$cardSelect() {
		List<RefData> list = bproductAddInfService.getCardNumber(cardSelect.getValue(), alias);
		// List<RefData> work =
		// BproductService.getWorkFromCard(cardSelect.getValue(),branch1
		// ,alias);
		// if(!work.isEmpty()){
		// inn_org.setValue(work.get(0).getData());
		// name_org.setValue(work.get(0).getLabel());
		// }
		if (!list.isEmpty()) {
			if (cardSelect.getValue().equals(list.get(0).getLabel()) && list.get(0).getData().equals("2")) {
				alert("На эту карточку уже оформлен овердрафт " + list.get(0).getLabel());
				btn_giveBankProduct.setDisabled(true);
			} else {
				btn_giveBankProduct.setDisabled(false);
			}
		}
	}

	// private void fizikVisible(boolean bool){
	// p1.setVisible(bool);
	// p2.setVisible(bool);
	// obs1.setVisible(bool);
	// obs.setVisible(bool);
	// cl_type.setButtonVisible(false);
	// clinfo.setVisible(bool);
	// }
	//
	// private void jurikVisible(boolean bool){
	// j1.setVisible(bool);
	// obs1.setVisible(bool);
	// obs.setVisible(bool);
	// clinfo.setVisible(bool);
	// }

	public void onInitRenderLater$cl_type() {
		if (client_obj != null) {
			List<RefData> list = BproductService.getTypeClient(client_obj.getId_client(), branch1, alias);
			if (!list.isEmpty()) {
				cl_type.setSelecteditem(list.get(0).getData());
			}
		}
	}

	public void onInitRenderLater$aprodid() {
		if (btn.equals("double")) {
			aprodid.setSelecteditem(current.getProdid() + "");
		}
	}

	public void onInitRenderLater$cardSelect() {
		if (btn.equals("double")) {
			System.out.println("CARD = " + current.getCard_number());
			cardSelect.setSelecteditem(current.getCard_number());
		}
	}

	// public void onOK$acustomer(){
	// onChange$acustomer();
	// }

	public void onPaging$bproductPaging(ForwardEvent event) {
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModel(_startPageNumber);
	}

	private void accessLvl() {
		parameter = (String[]) param.get("gtype");
		if (parameter != null && parameter[0].equals("2")) {
			if (filter == null) {
				filter = new BproductFilter();
			}
			btn_add.setVisible(false);
			btn_changeCard.setVisible(false);
			System.out.println("type = " + 2);
			branch_lh.setVisible(true);
			btn_add.setVisible(false);
			btn_add.setDisabled(true);
			rfilial.setVisible(true);
		} else if (parameter != null && parameter[0].equals("3")) {
			if (filter == null) {
				filter = new BproductFilter();
			}
			System.out.println("type = " + 3);
			rfilial.setVisible(false);
			filter.setBranch(branch1);
			branch_lh.setVisible(false);
			btn_changeCard.setVisible(true);
			btn_add.setVisible(false);
			btn_add.setDisabled(true);
		} else {
			if (filter == null) {
				filter = new BproductFilter();
			}
			rfilial.setVisible(false);
			branch_lh.setVisible(false);
			filter.setBranch(branch1);
			btn_add.setVisible(true);
			btn_changeCard.setVisible(false);
			btn_add.setVisible(true);
			System.out.println("type = else");
			btn_add.setDisabled(false);
		}
		// System.out.println("gtype = ? "+parameter[0]);
	}

	private void refreshModel(int activePage) {
		try {
			
			accessLvl();
			if (this.param.get("client_id") != null) {
				String[] parameter = (String[]) this.param.get("client_id");
				filter.setCustomer(parameter[0]);
			}
			if (this.param.get("id_client") != null) {
				String[] parameter = (String[]) this.param.get("client_id");
				filter.setCustomer(parameter[0]);
			}
			bproductPaging.setPageSize(_pageSize);
			model = new PagingListModel(activePage, _pageSize, filter, alias);
			_totalSize = model.getTotalSize(filter, alias);
			bproductPaging.setTotalSize(_totalSize);
			dataGrid.setModel((ListModel) model);
			if (model.getSize() > 0) {
				sendSelEvt();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Bproduct getCurrent() {
		return current;
	}

	public void setCurrent(Bproduct current) {
		this.current = current;
	}

	public void onChange$acustomer() {
		try {
			Utils.clearRows(scoreRows);
			String temp_branch = "";
			if (!btn.equals("double")) { //кирмади
				temp_branch = branch1;
				if (db_ld_amount != null) {
					if (bpr_type_value != null && bpr_type_value == 1) {
						// boolean readSumm =
						// BproductService.getReadSumm(aprodid.getValue(),
						// alias)==1;
						// asumm.setReadonly(readSumm);
						db_ld_amount.setValue(BigDecimal.ZERO);
					} else {
						db_ld_amount.setReadonly(false);
						db_ld_amount.setValue(BigDecimal.ZERO);
					}
				}
			} else {
				temp_branch = current.getBranch();
			}
			// Res res = new Res();
			// BproductClientsModel model = null;
			List<RefData> list = BproductService.getTypeClient(client_obj.getId_client(), temp_branch,
					Utils.getAlias(temp_branch));
			// bank_inps.setModel(new
			// ListModelList(BproductService.getSMFO(alias)));
			// bank_inps.setButtonVisible(false);
			if (list.isEmpty()) {
				alert("Клиент не найден");
				aname.setValue(null);
				// infoGroup.setVisible(false);
				scoreGroup.setVisible(false);
				// creditGroup.setVisible(false);
				// fizikVisible(false);
				return;
			} else {
				client_acc.setVisible(true);
				List<RefData> cl_acc_info = BproductService.getAccInfo(client_obj.getId_client(), branch1, alias);
				for (int i = 0; i < cl_acc_info.size(); i++) {
					cl_acc_rows.appendChild(getRow(new Label("Счет"), new Label(cl_acc_info.get(i).getData()),
							new Label("Остаток"), new Label(cl_acc_info.get(i).getLabel())));
				}
				// if(list.get(0).getData().equals("08")||list.get(0).getData().equals("11")){
				// hiderow1.setVisible(true);
				// hiderow2.setVisible(true);
				// } else {
				// hiderow1.setVisible(false);
				// hiderow2.setVisible(false);
				// }
				// if(list.get(0).getLabel().equals("P")){
				// model =
				// BproductService.getDatasClientP(client_obj.getId_client(),branch1,alias,
				// res);
				// if(res.getCode()!=1){
				// jurikVisible(false);
				// aname.setValue(model.getName());
				// cl_type.setSelecteditem(list.get(0).getData());
				// email.setValue(model.getEmail_address());
				// inpc.setValue(model.getInps());
				// passport.setValue(model.getPassport());
				// tax.setValue(model.getNumber_tax_registration());
				// home_tel.setValue(model.getPhone_home());
				// mob_tel.setValue(model.getPhone_mobile());
				// fizikVisible(true);
				// } else {
				// alert(res.getName());
				// }
				// } else if(list.get(0).getLabel().equals("J")){
				// fizikVisible(false);
				// model =
				// BproductService.getDatasClientJ(client_obj.getId_client(),
				// alias, res);
				// if(res.getCode()!=1){
				// cl_type.setSelecteditem(list.get(0).getData());
				// tax.setValue(model.getNumber_tax_registration());
				// phone.setValue(model.getPhone_home());
				// fax.setValue(model.getFax());
				// email.setValue(model.getEmail_address());
				// jurikVisible(true);
				// } else {
				// alert(res.getName());
				// }
				// }
				// infoGroup.setVisible(true);
				if (bpr_type_value != null && bpr_type_value > 0) {
					// creditGroup.setVisible(true);
					if (bpr_type_value == 1) {
						List<Scoring> scoring = BproductService.getScoring(aprodid.getValue(), alias);
						scoreGroup.setOpen(!scoring.isEmpty());
						scoreGroup.setVisible(!scoring.isEmpty());
					} else {
						scoreGroup.setVisible(false);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onClick$btn_score_report() {
		Utils.clearRows(scroreResRow);
		List<Scoring> scoring = BproductService.getScoring(aprodid.getValue(), alias);
		String month = "";
		String acc_cl = "";
		for (int i = 0; i < scoring.size(); i++) {
			if (scoring.get(i).getId() == 2) {
				month = scoring.get(i).getDef_value();
			} else if (scoring.get(i).getId() == 4) {
				if (scoring.get(i).getDef_value() != null && !scoring.get(i).getDef_value().equals("")) {
					acc_cl = scoring.get(i).getDef_value();
				} else {
					acc_cl = null;
				}
			}
		}
		Res res = new Res();
		List<RefData> list = BproductService.getScoringInfo(acc, sdf.format(Utils.getInfoDate(alias, res)), month,
				acc_cl, alias);
		for (int i = 0; i < list.size(); i++) {
			Label label1 = new Label(list.get(i).getData());
			Label label2 = new Label(list.get(i).getLabel());
			Row row = getRow(label1, label2);
			row.setAlign("center");
			scroreResRow.appendChild(row);
		}
		scoreRes.setVisible(true);
		scoreRes.setOpen(true);
	}

	private void initClientWnd() {

		// CRM bilan kirganda
		if (CRM_client != null) {
			System.out.println("initClientWnd: CRM_client = " + CRM_client);
			System.out.println("initClientWnd: parametr = " + parameter);
			String branch = btn.equals("double") ? current.getBranch() : branch1;
			client_obj = ClientAService.getClientAByStringId(branch, CRM_client, un, pw, alias);
			System.out.println("client_obj.getId_client()" + client_obj.getId_client());

			if (cap_client == null) {
				ISLogger.getLogger().error("CAP CLIENT IS NULL"+CRM_client);
			}
			cap_client.setLabel("Выбран клиент - " + client_obj.getId_client());
			aprodid.setModel(new ListModelList(com.is.bpri.BproductService.getProdId(branch,
					BproductService.getRegionFromBranch(session.getAttribute("un").toString(),
							session.getAttribute("pwd").toString(),branch,alias), client_obj.getCode_subject(), alias)));

		}

		else {
			ISLogger.getLogger().error("BTN = " + btn);
			String branch = btn.equals("double") ? current.getBranch() : branch1;
			if (current != null) {
				//scoring_id_clienta = client_obj.getId_client();
				ISLogger.getLogger().error("current customer = " + current.getCustomer());
				ISLogger.getLogger().error("current branch = " + branch);
				ISLogger.getLogger().error("current alias = " + Utils.getAlias(branch));
			}
			if (btn.equals("double"))
				clientAViewCtrl.initFromBpr(current.getCustomer(), branch, Utils.getAlias(branch));
			client_obj = clientAViewCtrl.getCurrent();
			if (cap_client == null) {
				ISLogger.getLogger().error("CAP CLIENT IS NULL");
			}
			if (client_obj == null) {
				ISLogger.getLogger().error("CLIENT IS NULL");
			}
			ISLogger.getLogger().error("строка 1758 client_obj.getId_client(): ");
			//cap_client.setLabel("Выбран клиент - " + client_obj.getId_client()); //26.12.2019
			ISLogger.getLogger().error("после client_obj.getId_client() + 1768 str");
			ISLogger.getLogger().error("BproductService.getProdId 1772 str"+"un: "+session.getAttribute("un").toString()+
					" pwd: "+session.getAttribute("pwd").toString()+ " branch: "+branch+" alias: "+alias);
			aprodid.setModel(new ListModelList(com.is.bpri.BproductService.getProdId(branch,
					BproductService.getRegionFromBranch(session.getAttribute("un").toString(),
							session.getAttribute("pwd").toString(), branch,alias), client_obj.getCode_subject(), alias)));
			ISLogger.getLogger().error("после aprodid.setModel"+branch+" subject "+client_obj.getCode_subject());
			// fprodid.setModel(new
			// ListModelList(com.is.bpri.BproductService.getProdId("","","",alias)));
		}

	}

	// private void beginIssuance(){
	// Res res = new Res();
	// String form_id = BproductService.getForm_id(current.getId(),
	// current.getBranch());
	//// if(bank_co.getValue()==null||bank_co.getValue().equals("")) {
	// alert("bank_co пустой"); return;}
	// if(account_co.getValue()==null||account_co.getValue().equals("")) {
	// alert("account_co пустой"); return;}
	// if(acc_name_co.getValue()==null||acc_name_co.getValue().equals("")) {
	// alert("acc_name_co пустой"); return;}
	// if(summa.getValue()==null||summa.getValue().equals("")) { alert("summa
	// пустой"); return;}
	//// BproductService.beginIssuance(branch1, form_id, bank_co.getValue(),
	// account_co.getValue(), acc_name_co.getValue(),
	// summa.getValue().doubleValue(), alias, res);
	// if(res.getCode()==1){
	// alert(res.getName());
	// } else {
	// alert("Успешно");
	// }
	// }

	// public void onClick$btn_issuance_down(){
	// beginIssuance();
	// }
	//
	// public void onClick$btn_issuance_up(){
	// beginIssuance();
	// }

	public void onDoubleClick$dataGrid$grd() {
		try {
			System.out.println("param " + parameter);
			if (session.getAttribute("ClickAction") != null) {
				tbox.setSelectedTab(bpr_els1);
				session.setAttribute("ClickAction", null);
			} else {
				frmgrdl.setVisible(true);
				addgrd.setVisible(false);
				tbox.setSelectedTab(bpr_els1);
			}
			total_ball.setValue("");
			if (parameter != null && parameter[0].equals("3")) {
				btn_changeCard.setVisible(true);
			} else {
				btn_changeCard.setVisible(false);
			}
			boolean search = btn.equals("search");
			btn = "double";
			if (client_wnd != null) {
				Utils.clearForm(client_wnd);
			}
			Utils.clearRows(cRows);
			createCRowsGrid();
			Utils.clearRows(asokiRows);
			Utils.clearRows(card_rows);
			if (!search) {
				List<Asoki> asokiList = AsokiService.getAsokiModel(
						BproductService.getNiki_id(BproductService.getForm_id(current.getId(), branch1, null),
								current.getId(), branch1, null, alias),
						branch1, alias);
				for (int i = 0; i < asokiList.size(); i++) {
					createAsokiRows(asokiList.get(i).getState_name());
				}
				asokiNireq.setVisible(true);
				asokiNireq.setOpen(false);
			}
			bpr_limits.setVisible(true);
			dep_tab.setVisible(true);
			ISLogger.getLogger().error("проверка current " );
			if (current != null && !search) {
				ISLogger.getLogger().error("Запуск метода initClientWnd(); " );
				initClientWnd();
				ISLogger.getLogger().error("Выход с метода initClientWnd(); " );
			}
			grd.setVisible(false);
			frm.setVisible(true);
			frmgrdl.setVisible(false);
			fgrdl.setVisible(false);
			btn_giveBankProduct.setVisible(false);
			btn_giveBankProductCreditApp.setVisible(false);
			addgrd.setVisible(true);
			//adate_limit.setValue("  asdasd  ");
			if (current != null) {  ///вход
				String form_id = BproductService.getForm_id(current.getId(), branch1);
				List<Ld_sv_gate> sv_gate = BproductService.getLdSvGate(form_id, branch1, alias);
				g_cards.setVisible(!sv_gate.isEmpty());
				System.out.println(!sv_gate.isEmpty());
				System.out.println("sv_gate.size() = " + sv_gate.size());
				for (int i = 0; i < sv_gate.size(); i++) {
					addCardRow(sv_gate.get(i));
				}
				if (current.getState() == 4) {
					btn_giveBankProductCreditAnket.setVisible(true);
					btn_savinganket.setVisible(true);
				} else { //вход 395
					btn_giveBankProductCreditAnket.setVisible(false);
					btn_savinganket.setVisible(false);
					current.setCard_number(BproductService.getCardReg(current.getId(), alias));
				}
				parameter = (String[]) param.get("gtype");
				btn_add_card_row.setVisible(parameter != null && parameter[0].equals("3"));
				btn_save_card_row.setVisible(parameter != null && parameter[0].equals("3"));

				if (parameter != null && (parameter[0].equals("2") || parameter[0].equals("3"))) {
					btn_giveBankProductCreditApp.setVisible(false);  //вход
					btn_giveBankProductCreditApp.setDisabled(true);
					btn_giveBankProductCreditAnket.setVisible(false);
					btn_giveBankProductCreditAnket.setDisabled(true);
					btn_savinganket.setVisible(false);
					btn_savinganket.setDisabled(true);
				} else if (parameter == null) { //395
					btn_giveBankProductCreditApp.setVisible(true);
					btn_giveBankProductCreditApp.setDisabled(false);
					btn_giveBankProductCreditAnket.setVisible(true);
					btn_giveBankProductCreditAnket.setDisabled(false);
					btn_savinganket.setVisible(true);
					btn_savinganket.setDisabled(false);
				}
				String br = current.getBranch();
				bpr_type_value = BproductService.getBpr_type_value(alias, Integer
						.parseInt(aprodid.getValue().equals("") ? current.getProdid() + "" : aprodid.getValue()));
				if (!search)
					onChange$acustomer(); //вход
				scoreGroup.setVisible(false);
				if (bpr_type_value == 1) { //вход
					bpr_limits.setVisible(true);
					bpr_els4.setVisible(false);
					card_grid.setVisible(true);
				} else if (bpr_type_value == 2) {
					customer.setValue(BproductService.getCustomerNameForDeposit(alias, br, current.getCustomer()));
					bpr_limits.setVisible(false);
					card_grid.setVisible(false);
					bpr_els4.setVisible(true);
				} else if (bpr_type_value == 3) {
					card_grid.setVisible(false);
					bpr_limits.setVisible(false);
					bpr_els4.setVisible(true);
				} else if (bpr_type_value == 4) {
					bpr_limits.setVisible(true);
					bpr_els4.setVisible(false);
					card_grid.setVisible(true);
				} else if (bpr_type_value == 5) {
					bpr_limits.setVisible(true);
					bpr_els4.setVisible(false);
					card_grid.setVisible(true);
				}
				// initOperationsWnd();
			}
			// g_cards.setVisible(true);
			btn_back.setImage("/images/folder.png");
			btn_back.setLabel(Labels.getLabel("grid"));
			credittab.setVisible(true);
			creditankettab.setVisible(true);
			tb_search.setVisible(false);
			scoreRes.setVisible(false);
			scoreRes.setOpen(false);
			// reg_num_label.setVisible(true);
			// reg_num.setVisible(true);
			creditGroup.setVisible(true);
			if (!search && current != null) {
				onSelect$aprodid();
				getCurrentValue();
				if (current.getState() != 4) { //вход 395
					Utils.ReadOnly(addgrd, true);
				} else {
					Utils.ReadOnly(g_new, false);
				}
				Utils.ReadOnly(g_cards, !(parameter != null && parameter[0].equals("3")));
				System.out.println("state = " + current.getState());
				if (current.getState() == 4) {
					btn_savinganket.setVisible(true);
					btn_giveBankProduct.setVisible(false);
					btn_giveBankProductCreditAnket.setVisible(true);
					btn_giveBankProductCreditApp.setVisible(false);
					// g_operations.setVisible(false);
					// btn_issuance_up.setVisible(false);
					// btn_issuance_down.setVisible(false);
				} else { //вход 395
					btn_savinganket.setVisible(false);
					btn_giveBankProduct.setVisible(false);
					btn_giveBankProductCreditAnket.setVisible(false);
					btn_giveBankProductCreditApp.setVisible(false);
					// if(current.getState()==2) {
					// Utils.ReadOnly(g_operations, false);
					// btn_issuance_up.setVisible(true);
					// btn_issuance_down.setVisible(true);
					// g_operations.setVisible(true);
					// } else {
					// btn_issuance_up.setVisible(false);
					// btn_issuance_down.setVisible(false);
					// g_operations.setVisible(false);
					// }
				}
			}
			if (search) {  //мимо 395
				bpr_els1.setVisible(!search);
				bpr_els4.setVisible(!search);
				bpr_limits.setVisible(!search);
				dep_tab.setVisible(!search);
				credittab.setVisible(!search);
				creditankettab.setVisible(!search);
			}
		} catch (Exception e) {
			e.printStackTrace();
			alert(CheckNull.getPstr(e));
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
	}

	public void onClick$dep_tab() {
		bprDesc.setSrc(null);
		String param = "";
		if (parameter != null) {
			param = parameter[0];
		}
		bprDesc.setSrc("bproduct_desc.zul?template=" + (current.getId() + "") + "&gtype=" + param);
	}

	public void onClick$scoringapp() {
		scoringappInc.setSrc(null);
		// String param = "";
		if (parameter != null) {
			// param = parameter[0];
		}
	}

	public void onClick$credittab() {
		creditapp.setSrc(null);
		String cl_id = "";
		if (btn.equals("add")) {
			cl_id = client;
		} else if (btn.equals("double")) {
			cl_id = current.getCustomer() + "";
		}
		String param = "";
		if (parameter != null) {
			param = parameter[0];
		}
		creditapp.setSrc("creditapp/creditapp.zul?customer=" + cl_id + "&gtype=" + param);
	}

	public void onClick$creditankettab() {
		creditanket.setSrc(null);
		if (btn.equals("double") || btn.equals("add")) {
			String form_id = BproductService.getForm_id(current.getId(), current.getBranch(), null);
			if (form_id != null && !form_id.equals("")) {
				String param = "";
				if (parameter != null) {
					param = parameter[0];
				}
				creditanket.setSrc("creditanket/creditanket.zul?form_id=" + form_id + "&gtype=" + param);
			}
		}
	}

	public void onClick$bpr_limits() {
		bprLim.setSrc(null);
		String param = "";
		if (parameter != null) {
			param = parameter[0];
		}
		bprLim.setSrc("bprLimits.zul?template=" + current.getId() + "&bpr_type=" + bpr_type_value + "&gtype=" + param
				+ "&client=" + current.getCustomer());
	}

	public void onClick$btn_back() {
		if (frm.isVisible()) {
			btn_giveBankProduct.setVisible(false);
			btn_giveBankProductCreditAnket.setVisible(false);
			btn_giveBankProductCreditApp.setVisible(false);
			frm.setVisible(false);
			grd.setVisible(true);
			btn_back.setImage("/images/file.png");
			btn_back.setLabel(Labels.getLabel("back"));
			dataGrid.setSelectedIndex(-1);
		} else
			onDoubleClick$dataGrid$grd();
	}

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
		SelectEvent evt = new SelectEvent("onSelect", dataGrid, dataGrid.getSelectedItems());
		Events.sendEvent(evt);
	}

	public void onClick$btn_add() {
		try {
			Utils.clearForm(addgrd);
			if (client_wnd != null) {
				ISLogger.getLogger().error(" Запуск метода Utils.clearForm(client_wnd) " + client_wnd);
				Utils.clearForm(client_wnd);
				ISLogger.getLogger().error(" Конец метода Utils.clearForm(client_wnd) " + client_wnd);
			}
			// Res res = new Res();
			// Date date = BproductService.getInfoDate(alias, res);
			// btn_issuance_down.setVisible(false);
			// btn_issuance_up.setVisible(false);
			ISLogger.getLogger().error(" begin g_cards g_cards.setVisible(false)");
			g_cards.setVisible(false);
			ISLogger.getLogger().error(" Скрыть g_cards g_cards.setVisible(false)");
			Utils.clearRows(cRows);
			btn_add_card_row.setVisible(true);
			grd.setVisible(false);
			product_info.setVisible(false);
			asokiNireq.setVisible(false);
			asokiNireq.setOpen(false);
			// eq_date.setValue(date);
			current = new Bproduct();
			session.setAttribute("ClickAction", "add");
			parameters = new ArrayList<Parameter>();
			Utils.clearRows(r_guarr);
			Utils.clearRows(r_exp);
			session.setAttribute("cl_id", null);
			// dateContractComp = null;
			cap_client.setLabel("Клиенты");

			if (CRM_client != null) {
				cap_client.setLabel("Выбран клиент - " + CRM_client);
			}

			bpr_els4.setVisible(false);
			bpr_id = null;
			parameter = (String[]) param.get("gtype");
			if (parameter == null) {
				btn_giveBankProduct.setVisible(true);
				btn_giveBankProductCreditApp.setVisible(true);
				btn_giveBankProductCreditApp.setDisabled(false);
				btn_savinganket.setVisible(true);
				btn_savinganket.setDisabled(false);
			} else if (parameter != null && (parameter[0].equals("2") || parameter[0].equals("3"))) {
				btn_giveBankProduct.setVisible(false);
				btn_giveBankProductCreditApp.setVisible(false);
				btn_giveBankProductCreditApp.setDisabled(true);
				btn_savinganket.setVisible(false);
				btn_savinganket.setDisabled(true);
			}
			bproductmain.setHeight("100%");
			frmgrdl.setVisible(false);
			addgrd.setVisible(true);
			frm.setVisible(true);
			fgrdl.setVisible(false);
			bpr_limits.setVisible(false);
			dep_tab.setVisible(false);
			btn_giveBankProduct.setDisabled(false);
		//	btn_print_KatmInfo.setVisible(true);
			credittab.setVisible(false);
			creditankettab.setVisible(false);
			btn = "add";
			// infoGroup.setVisible(false);
			Utils.clearRows(scoreRows);
			Utils.clearRows(cl_acc_rows);
			client_acc.setVisible(false);
			client_acc.setOpen(false);
			scoreGroup.setOpen(false);
			// infoGroup.setOpen(false);
			creditGroup.setOpen(false);
			scoreGroup.setVisible(false);
			creditGroup.setVisible(false);
			// reg_num_label.setVisible(false);
			// reg_num.setVisible(false);
			tb_search.setVisible(false);
			scoreRes.setVisible(false);
			scoreRes.setOpen(false);
			btn_changeCard.setVisible(false);
			btn_savinganket.setVisible(false);
			Utils.ReadOnly(addgrd, false);
			Utils.ReadOnly(client_wnd, false);
			scoringSumma = null;
			g_new.setVisible(false);
		} catch (Exception e) {
			e.printStackTrace();
			alert(CheckNull.getPstr(e));
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
	}

	public void onClick$btn_search() {
		btn = "search";
		onDoubleClick$dataGrid$grd();
		frmgrdl.setVisible(false);
		addgrd.setVisible(false);
		fgrdl.setVisible(true);
		getFilterValues();
		tb_search.setVisible(true);
		btn = "search";
	}

	// распечатка документов ;

//************** Nacalo Sanjar aka
	public void onClick$btn_print_credApp() {
		if (dataGrid.getSelectedItem() == null) {
			alert("клиент не выбран");
		} else {
			if (CRM_client != null) {
                ISLogger.getLogger().error("Entered_Cred_App: "+branch1+"  ,username : "+un);
				Map<String, Object> params = new HashMap<String, Object>();
				String bname = BproductService.getBankName( un, pw,branch1, alias);
				ISLogger.getLogger().error("Cred_App_Bname: "+bname);
				System.out.println("clientCRM-------" + CRM_client);
				StringBuffer sb=new StringBuffer();
				System.out.println("Bprod_ID : "+current.getProdid());
				ISLogger.getLogger().error("Entered_Cred_APP_Prod_ID: "+current.getProdid());
				String name_doc1=BproductService.getNameDoc(current.getProdid(),alias,"A");
				ISLogger.getLogger().error("Entered_Cred_App_nameDoc: "+name_doc1);
				String par_name1="";
				String str1="/reports/";
                sb.append(str1);
                sb.append(name_doc1);
                par_name1=sb.toString();
                System.out.println("par_name: "+par_name1);

				credRepField.setBname(BproductService.getBankName( un, pw,branch1, alias));
				ISLogger.getLogger().error("Cred_App_Bankname: "+credRepField.getBname());
				credRepField.setAdress(
						(BproductService.getInfoClientbyBranch("post_address", CRM_client, alias, branch1)) == null ? ""
								: (BproductService.getInfoClientbyBranch("post_address", CRM_client, alias, branch1)));
				ISLogger.getLogger().error("Cred_App_Adress: "+credRepField.getAdress());
				credRepField.setFIO((BproductService.getCustomerName(branch1, alias, CRM_client)) == null ? ""
						: (BproductService.getCustomerName(branch1, alias, CRM_client)));
				ISLogger.getLogger().error("Cred_App_FIO: "+credRepField.getFIO());
				credRepField.setHOMEPHONE(
						(BproductService.getInfoClientbyBranch("HOMEPHONE", CRM_client, alias, branch1)) == null ? ""
								: (BproductService.getInfoClientbyBranch("HOMEPHONE", CRM_client, alias, branch1)));
				ISLogger.getLogger().error("Cred_App_HomePhone: "+credRepField.getHOMEPHONE());
				credRepField.setPASSPORT(
						(BproductService.getInfoClientbyBranch("PASSPORT", CRM_client, alias, branch1)) == null ? ""
								: (BproductService.getInfoClientbyBranch("PASSPORT", CRM_client, alias, branch1)));
				ISLogger.getLogger().error("Cred_App_Passport: "+credRepField.getHOMEPHONE());
				credRepField.setInn((BproductService.getInfoClientbyBranch("number_tax_registration", CRM_client, alias,
						branch1)) == null ? ""
								: (BproductService.getInfoClientbyBranch("number_tax_registration", CRM_client, alias,
										branch1)));
				ISLogger.getLogger().error("Cred_App_Inn: "+credRepField.getInn());
				credRepField.setPSINFO(
						(BproductService.getInfoClientbyBranch("PSINFO", CRM_client, alias, branch1)) == null ? ""
								: (BproductService.getInfoClientbyBranch("PSINFO", CRM_client, alias, branch1)));
				ISLogger.getLogger().error("Cred_App_PSINFO: "+credRepField.getPSINFO());
				credRepField.setWsumma(
						(BproductService.getWsumm(current.getAmount(), current.getCurrency(), alias)) == null ? ""
								: (BproductService.getWsumm(current.getAmount(), current.getCurrency(), alias)));
				ISLogger.getLogger().error("Cred_App_WSUMMA: "+credRepField.getWsumma());
				credRepField.setCardacc(
			            (BproductService.getCardAcc(session.getAttribute("un").toString(),
								session.getAttribute("pwd").toString(),BproductService.getCardReg(current.getId(), alias), alias)) == null
			                ? ""
			                : (BproductService.getCardAcc(session.getAttribute("un").toString(),
									session.getAttribute("pwd").toString(),BproductService.getCardReg(current.getId(), alias),
			                    alias)));
				ISLogger.getLogger().error("Cred_App_Card_acc: "+credRepField.getCardacc());
				credRepField.setMOBPHONE(
						(BproductService.getInfoClientbyBranch("MOBPHONE", CRM_client, alias, branch1)) == null ? ""
								: (BproductService.getInfoClientbyBranch("MOBPHONE", CRM_client, alias, branch1)));
				ISLogger.getLogger().error("Cred_App_MOBPHONE: "+credRepField.getMOBPHONE());
				String form_id = BproductService.getForm_id(current.getId(), branch1);
				ISLogger.getLogger().error("Cred_App_FORM_ID: "+form_id);
				credRepField.setTerm_contract((BproductService.getCreditTimeByMonth(branch1, form_id, alias)) == null
						? "" : (BproductService.getCreditTimeByMonth(branch1, form_id, alias)));
				ISLogger.getLogger().error("Cred_App_TERM_CONTRACT: "+credRepField.getTerm_contract());
				credRepField
						.setLd_date((BproductService.getInfoCreditbyBranch("LD_DATE", form_id, alias, branch1)) == null
								? "" : (BproductService.getInfoCreditbyBranch("LD_DATE", form_id, alias, branch1)));
				ISLogger.getLogger().error("Cred_App_LD_Date: "+credRepField.getLd_date());
				credRepField.setClose_date(
						(BproductService.getInfoCreditbyBranch("DATE_CLOSE", form_id, alias, branch1)) == null ? ""
								: (BproductService.getInfoCreditbyBranch("DATE_CLOSE", form_id, alias, branch1)));
				ISLogger.getLogger().error("Cred_App_Close_Date: "+credRepField.getClose_date());

			/*	System.out.println("Ld_date---rrrrrr:"
						+ BproductService.getInfoCreditbyBranch("LD_DATE", form_id, alias, branch1));
				System.out.println("CLose_date-----xxx:"
						+ BproductService.getInfoCreditbyBranch("DATE_CLOSE", form_id, alias, branch1));
				System.out
						.println("term_contract-----:" + BproductService.getCreditTimeByMonth(branch1, form_id, alias));
				System.out.println("Form_id--------RRRRRR:" + form_id);

				System.out.println("b_name " + bname); */

				try {
					params = RepTempl.objToMap(current, params);
					params = RepTempl.objToMap(credRepField, params);

				} catch (Exception e) {

					e.printStackTrace();
					ISLogger.getLogger().error("Cred_App_Exception: "+e.getMessage()+" ,"+e.getStackTrace());
				}

				// alert(Executions.getCurrent().getDesktop().getWebApp().getRealPath("/reports/test.doc"));
				AMedia amedia = RepTempl.getRepmd(params,
						Executions.getCurrent().getDesktop().getWebApp().getRealPath(par_name1),
						"CredApp" + form_id);
				// AMedia amedia1 =RepTempl.getRepmd(params,
				// Executions.getCurrent().getDesktop().getWebApp().getRealPath("/reports/TestDog.doc"),
				// "CredDog"+form_id);
				if (amedia != null) {
					Filedownload.save(amedia);

				} else {
					alert("Файл не сформирован");
				}
			}
		}
	}

	public void onClick$btn_print_credDog() {
		if (dataGrid.getSelectedItem() == null) {
			alert("клиент не выбран");

		} else {

			if (CRM_client != null) {
				ISLogger.getLogger().error("Entered_Cred_Dog: "+branch1+" ,username : "+un);
				Map<String, Object> params = new HashMap<String, Object>();
				String bname = BproductService.getBankName(un, pw,branch1, alias);
				ISLogger.getLogger().error("Entered_Cred_Dog_bname: "+bname);
				System.out.println("clientCRM-------" + CRM_client);
				StringBuffer sb=new StringBuffer();
				System.out.println("Bprod_ID : "+current.getProdid());
				ISLogger.getLogger().error("Entered_Cred_Dog_PROD_ID: "+current.getProdid());
				String name_doc=BproductService.getNameDoc(current.getProdid(),alias,"D");
				ISLogger.getLogger().error("Entered_Cred_Dog_NAME_DOC: "+name_doc);
				String par_name="";
				String str="/reports/";
                sb.append(str);
                sb.append(name_doc);
                par_name=sb.toString();
                System.out.println("par_name: "+par_name);
				credRepField.setBname(BproductService.getBankName(un, pw,branch1, alias));
				ISLogger.getLogger().error("Entered_Cred_Dog_Bname: "+credRepField.getBname());
				credRepField
						.setAdress(BproductService.getInfoClientbyBranch("post_address", CRM_client, alias, branch1));
				ISLogger.getLogger().error("Entered_Cred_Dog_Adress: "+credRepField.getAdress());
				credRepField.setFIO(BproductService.getCustomerName(branch1, alias, CRM_client));
				ISLogger.getLogger().error("Entered_Cred_Dog_FIO: "+credRepField.getFIO());
				credRepField.setHOMEPHONE(
						(BproductService.getInfoClientbyBranch("HOMEPHONE", CRM_client, alias, branch1)) == null ? " "
								: (BproductService.getInfoClientbyBranch("HOMEPHONE", CRM_client, alias, branch1)));
				ISLogger.getLogger().error("Entered_Cred_Dog_HOMEPHONE: "+credRepField.getHOMEPHONE());
				credRepField.setPASSPORT(BproductService.getInfoClientbyBranch("PASSPORT", CRM_client, alias, branch1));
				ISLogger.getLogger().error("Entered_Cred_Dog_PASSPORT: "+credRepField.getPASSPORT());
				credRepField.setInn(
						BproductService.getInfoClientbyBranch("number_tax_registration", CRM_client, alias, branch1));
				ISLogger.getLogger().error("Entered_Cred_Dog_INN: "+credRepField.getInn());
				credRepField.setPSINFO(BproductService.getInfoClientbyBranch("PSINFO", CRM_client, alias, branch1));
				ISLogger.getLogger().error("Entered_Cred_Dog_PSINFO: "+credRepField.getPSINFO());
				credRepField.setWsumma(BproductService.getWsumm(current.getAmount(), current.getCurrency(), alias));
				ISLogger.getLogger().error("Entered_Cred_Dog_WSUMMA: "+credRepField.getWsumma());
				 credRepField.setCardacc(
				            (BproductService.getCardAcc(session.getAttribute("un").toString(),
									session.getAttribute("pwd").toString(),BproductService.getCardReg(current.getId(), alias), alias)) == null
				                ? ""
				                : (BproductService.getCardAcc(session.getAttribute("un").toString(),
										session.getAttribute("pwd").toString(),BproductService.getCardReg(current.getId(), alias),
				                    alias)));
				ISLogger.getLogger().error("Entered_Cred_Dog_CARD_ACC: "+credRepField.getCardacc());
				credRepField.setMOBPHONE(
						(BproductService.getInfoClientbyBranch("MOBPHONE", CRM_client, alias, branch1)) == null ? " "
								: (BproductService.getInfoClientbyBranch("MOBPHONE", CRM_client, alias, branch1)));
				ISLogger.getLogger().error("Entered_Cred_Dog_CARD_MOBPHONE: "+credRepField.getMOBPHONE());
				String form_id = BproductService.getForm_id(current.getId(), branch1);
				ISLogger.getLogger().error("Entered_Cred_Dog_FORM_ID: "+form_id);
				System.out.println("cur_ID :"+current.getId());
				credRepField.setTerm_contract(BproductService.getCreditTimeByMonth(branch1, form_id, alias));
				ISLogger.getLogger().error("Entered_Cred_Dog_TERM_CONTRACT: "+credRepField.getTerm_contract());
				credRepField.setLd_date(BproductService.getInfoCreditbyBranch("LD_DATE", form_id, alias, branch1));
				ISLogger.getLogger().error("Entered_Cred_Dog_LD_DATE: "+credRepField.getLd_date());
				credRepField
						.setClose_date(BproductService.getInfoCreditbyBranch("DATE_CLOSE", form_id, alias, branch1));
				ISLogger.getLogger().error("Entered_Cred_Dog_Close_date: "+credRepField.getClose_date());
				credRepField.setLd_rate(BproductService.getLdrateByClient(form_id, alias, branch1));
				ISLogger.getLogger().error("Entered_Cred_Dog_LD_RATE: "+credRepField.getLd_rate());
				credRepField.setProp(
						BproductService.numberToWord(BproductService.getLdrateByClient(form_id, alias, branch1)));
				ISLogger.getLogger().error("Entered_Cred_Dog_PROP: "+credRepField.getProp());
				current.setCard_number(current.getCard_number() == null ? "" : current.getCard_number());
				ISLogger.getLogger().error("Entered_Cred_Dog_CARD_NUMBER: "+current.getCard_number());
				credRepField
						.setHeadOfBank(BproductService.getInfoFilialbyBranch("DIRECTOR_NAME", alias, branch1) == null
								? "" : BproductService.getInfoFilialbyBranch("DIRECTOR_NAME", alias, branch1));
				ISLogger.getLogger().error("Entered_Cred_Dog_HEAD_BANK: "+credRepField.getHeadOfBank());
				credRepField.setChiefAccountant_name(
						BproductService.getInfoFilialbyBranch("CHIEF_ACCOUNTER_NAME", alias, branch1) == null ? ""
								: BproductService.getInfoFilialbyBranch("CHIEF_ACCOUNTER_NAME", alias, branch1));
				ISLogger.getLogger().error("Entered_Cred_Dog_Accountant_name: "+credRepField.getChiefAccountant_name());
				credRepField.setBank_Post_Adress(
						BproductService.getInfoFilialbyBranch("POST_ADDRESS", alias, branch1) == null ? ""
								: BproductService.getInfoFilialbyBranch("POST_ADDRESS", alias, branch1));
				ISLogger.getLogger().error("Entered_Cred_Dog_POST_ADRESS: "+credRepField.getBank_Post_Adress());
				credRepField.setBank_Tax_Number(
						BproductService.getInfoFilialbyBranch("NUMBER_TAX_REGISTRATIO", alias, branch1) == null ? ""
								: BproductService.getInfoFilialbyBranch("NUMBER_TAX_REGISTRATIO", alias, branch1));
				ISLogger.getLogger().error("Entered_Cred_Dog_BANKTAXNUMBER: "+credRepField.getBank_Tax_Number());
				credRepField.setBank_Phone(BproductService.getInfoFilialbyBranch("PHONE", alias, branch1) == null ? ""
						: BproductService.getInfoFilialbyBranch("PHONE", alias, branch1));
				ISLogger.getLogger().error("Entered_Cred_Dog_BANKPHONE: "+credRepField.getBank_Phone());
				credRepField.setAccount(BproductService.getInfoFilialbyBranch("ACCOUNT", alias, branch1) == null ? ""
						: BproductService.getInfoFilialbyBranch("ACCOUNT", alias, branch1));
				ISLogger.getLogger().error("Entered_Cred_Dog_ACCOUNT: "+credRepField.getAccount());
				// System.out.println("HeadofBank-----:"+BproductService.getInfoFilialbyBranch("DIRECTOR_NAME",
				// alias, branch1));
				// System.out.println("ChiefAccountant-----:"+BproductService.getInfoFilialbyBranch("CHIEF_ACCOUNTER_NAME",
				// alias, branch1));
		/*		System.out.println("Summa propis:"
						+ BproductService.numberToWord(BproductService.getLdrateByClient(form_id, alias, branch1)));

				System.out.println("ld_rate----r:" + BproductService.getLdrateByClient(form_id, alias, branch1));
				System.out.println("Ld_date---rrrrrr:"
						+ BproductService.getInfoCreditbyBranch("LD_DATE", form_id, alias, branch1));
				System.out.println("CLose_date-----xxx:"
						+ BproductService.getInfoCreditbyBranch("DATE_CLOSE", form_id, alias, branch1));
				System.out
						.println("term_contract-----:" + BproductService.getCreditTimeByMonth(branch1, form_id, alias));
				System.out.println("Form_id--------RRRRRR:" + form_id);

				System.out.println("b_name " + bname);   */

				try {
					params = RepTempl.objToMap(current, params);
					params = RepTempl.objToMap(credRepField, params);

				} catch (Exception e) {
					e.printStackTrace();
					ISLogger.getLogger().error("Cred_DOG_Exception: "+e.getMessage()+" ,"+e.getStackTrace());
				}
				try{
				// alert(Executions.getCurrent().getDesktop().getWebApp().getRealPath("/reports/test.doc"));
				AMedia amedia = RepTempl.getRepmd(params,
						Executions.getCurrent().getDesktop().getWebApp().getRealPath(par_name),
						"CredDog" + form_id);
				// AMedia amedia1 =RepTempl.getRepmd(params,
				// Executions.getCurrent().getDesktop().getWebApp().getRealPath("/reports/TestDog.doc"),
				// "CredDog"+form_id);
				
				if (amedia != null) {
					Filedownload.save(amedia);

				}
				else{
					alert("Файл не сформирован");
					}
				}
				catch(Exception e1){
				e1.printStackTrace();
				ISLogger.getLogger().error("Fayl ne Sformirovan : "+e1.getStackTrace());
				ISLogger.getLogger().error("Fayl ne Sformirovan : "+e1.getMessage());
				}
			
			}
		}
}
  
  
  //***************************************************konets Sabjar aka

	private void getFilterValues() {
		try {
			if (filter != null) {
				fid.setValue(filter.getId());
				fcustomer.setValue(filter.getCustomer());
				fprodid.setSelecteditem(filter.getProdid() + "");
				fvdate.setValue(filter.getVdate());
				fcurrency.setSelecteditem(filter.getCurrency());
				if (filter.getAmount() != null) {
					famount.setValue(filter.getAmount() / 100);
				}
				fstate.setSelecteditem(filter.getState() + "");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onClick$btn_save() {
		try {
			if (filter == null) {
				filter = new BproductFilter();
			}
			if (fid.getValue() != null && !fid.getValue().equals("")) {
				filter.setId(fid.getValue());
			}
			filter.setCustomer(fcustomer.getValue());
			if (fprodid.getValue() != null && !fprodid.getValue().equals("")) {
				filter.setProdid(Integer.parseInt(fprodid.getValue()));
			}
			filter.setVdate(fvdate.getValue());
			filter.setCurrency(fcurrency.getValue());
			if (famount.getValue() != null) {
				filter.setAmount(famount.getValue() * 100);
			}
			if (fstate.getValue() != null && !fstate.getValue().equals("")) {
				filter.setState(Integer.parseInt(fstate.getValue()));
			}
			if (fbranch.getValue() != null && !fbranch.getValue().equals("")) {
				filter.setBranch(fbranch.getValue());
			}
			refreshModel(_startPageNumber);
			onClick$btn_back();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onClick$btn_cancel() {
		if (fgrdl.isVisible()) {
			filter = new BproductFilter();
		}
		onClick$btn_back();
		frmgrdl.setVisible(true);
		addgrd.setVisible(false);
		fgrdl.setVisible(false);
		Utils.clearForm(fgrdl);
		refreshModel(_startPageNumber);
	}

	public void onClick$bpr_els4() {
		try {
			String cl_id;
			bprAddInfo.setSrc(null);
			if (client_obj.getId_client().equals("") || client_obj.getId_client() == null) {
				cl_id = current.getCustomer() + "";
			} else {
				cl_id = client_obj.getId_client();
			}
			session.setAttribute("cl_id", cl_id);
			bprAddInfo.setSrc("bproductAddInf.zul?template=" + bpr_type_value + "&branch=" + branch1 + "&cl_id=" + cl_id
					+ "&id=" + current.getId());
		} catch (Exception e) {
			e.printStackTrace();
			alert(CheckNull.getPstr(e));
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
	}

	public void onClick$btn_changeCard() {
		if (btn_changeCard.getLabel().equals("Изменить")) {
			cardSelect.setModel(new ListModelList(
					BproductService.getClientcard(current.getCustomer() + "", current.getBranch(), null, alias)));
			cardSelect.setButtonVisible(true);
			cardSelect.setReadonly(true);
			btn_changeCard.setLabel("Сохранить");
		} else if (btn_changeCard.getLabel().equals("Сохранить")) {
			Res res = new Res();
			if (!cardSelect.getValue().equals(BproductService.getCardReg(current.getId(), alias))) {
				int uid = (Integer) session.getAttribute("uid");
				String str = (String) session.getAttribute("un");
				
				BproductService.changeCard(uid, str, cardSelect.getValue(), current.getCard_number(),
						current.getBranch(), current.getId() + "", current.getCustomer(), alias, res);
			} else {
				alert("Текущая карта уже зарегистрированна в системе");
			}
			if (res.getCode() != 1) {
				cardSelect.setButtonVisible(false);
				cardSelect.setReadonly(true);
				btn_changeCard.setLabel("Изменить");
			} else {
				alert(res.getName());
			}
		}
	}

	public void onClick$btn_add_card_row() {
		addCardRow(null);
	}

	public void onClick$btn_save_card_row() {
		List<Ld_sv_gate> cards;
		try {
			cards = getCards();
			Res res = new Res();
			BproductService.saveCards(current.getId(), branch1, current.getCustomer(), cards, res);
			if (res.getCode() == 1) {
				alert(res.getName());
			} else {
				alert("Успешно сохранено");
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			alert(CheckNull.getPstr(e));
		}
	}

	private void addCardRow(Ld_sv_gate sv_gate) {
		Row row = new Row();
		Longbox card_num = new Longbox();
		card_num.setMaxlength(16);
		card_num.setWidth("30%");
		if (sv_gate != null)
			card_num.setValue(Long.parseLong(sv_gate.getCard_number()));
		Button del = new Button();
		if (parameter == null)
			parameter = (String[]) param.get("gtype");
		if (btn.equals("double"))
			del.setVisible(parameter != null && parameter[0].equals("3"));
		del.setAttribute("row", row);
		del.setImage("/images/delete.png");
		del.addEventListener(Events.ON_CLICK, new EventListener() {

			@Override
			public void onEvent(Event evt) throws Exception {
				Button btn = (Button) evt.getTarget();
				Row row = (Row) btn.getAttribute("row");
				card_rows.removeChild(row);
			}
		});
		Datebox exp_date = new Datebox();
		exp_date.setFormat("MMyy");
		if (sv_gate != null)
			exp_date.setText(sv_gate.getExpiry_date());
		RefCBox rbox = new RefCBox();
		rbox.setModel(new ListModelList(BproductService.getTypeCardOwner()));
		if (sv_gate != null)
			rbox.setAttribute("value", sv_gate.getSign_client());
		rbox.addEventListener("onInitRenderLater", new EventListener() {

			@Override
			public void onEvent(Event event) throws Exception {
				RefCBox rbox = (RefCBox) event.getTarget();
				if (rbox.getAttribute("value") != null)
					rbox.setSelecteditem(rbox.getAttribute("value") + "");
			}
		});
		row.appendChild(card_num);
		row.appendChild(exp_date);
		row.appendChild(rbox);
		row.appendChild(del);
		card_rows.appendChild(row);
	}

	private List<Ld_sv_gate> getCards() throws Exception {
		List<Ld_sv_gate> list = new ArrayList<Ld_sv_gate>();
		List<?> rows = card_rows.getChildren();
		ISLogger.getLogger().error("15.01.2020 стр 2584 = " + client_obj.getId_client());
		if (rows != null) {
			for (int i = 0; i < rows.size(); i++) {
				if (rows.get(i) instanceof Row) {
					Row row = (Row) rows.get(i);
					Longbox card_num = (Longbox) row.getChildren().get(0);
					Datebox expiry_date = (Datebox) row.getChildren().get(1);
					RefCBox rbox = (RefCBox) row.getChildren().get(2);
					if (card_num.getValue() == null)
						continue;
					Ld_sv_gate info = new Ld_sv_gate();
					info.setBranch(branch1);
					info.setCard_number(card_num.getValue().toString());
					info.setExpiry_date(expiry_date.getText());
					info.setSign_client(rbox.getValue() != null && !rbox.getValue().equals("")
							? Integer.parseInt(rbox.getValue()) : -1);
					list.add(info);
				}
			}
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	private void saving(int btn_click, int trans_id, String head_of_bank) {
		
		ISLogger.getLogger().error("15.01.2020 стр 2609 = " + client_obj.getId_client());
		try {
			Res res = new Res();
			List<CreateValues> list = getValues(btn_click);
			if (list == null) {
				return;
			}
			ISLogger.getLogger().error("15.01.2020 стр 2617 = aprodid.getValue(): " + aprodid.getValue());
			ScoringType st = BproductService.getScroringType(Integer.parseInt(aprodid.getValue()));
			ISLogger.getLogger().error("15.01.2020 стр 2619 = st: " + st);
			if (st != null && st.getId() == 2) {
				List<?> rows = scoreRows.getChildren();
				for (int i = 0; i < rows.size(); i++) {
					if (rows.get(i) instanceof Row) {
						Row row = (Row) rows.get(i);
						if (row.isVisible() && ((RefCBox) row.getChildren().get(1)).getValue().equals("")) {
							// if(row.getChildren()){
							//
							// }
							alert("Не заполнено поле - " + ((Label) row.getChildren().get(0)).getValue());
							return;
						}
					}
				}
			}
			List<Ld_sv_gate> cards = getCards();
			List<BallScoringViewModel> balls = (List<BallScoringViewModel>) scoreRows.getAttribute("model");
			System.out.println("ALIAS = " + alias);
			ISLogger.getLogger().error("client_obj.getId_client() = " + client_obj.getId_client());
			System.out.println("amount---------" + current.getAmount());
			// current.setAmount("5323,00");
			BproductService.doActionForDepositGiving(session.getAttribute("un").toString(),
					session.getAttribute("pwd").toString(), current, parameters, balls,
					session.getAttribute("branch").toString(), client_obj.getId_client(),
					// avdate_begin.getText(),
					null, bpr_id, trans_id, head_of_bank, accountList, limit,
					// eq_num.getValue(),
					// eq_date.getText(),
					// eq_end_date.getText(),
					// crc_num.getValue(),
					// ld_num.getValue(),
					// inn_org.getValue(),
					// bank_inps.getValue(),
					// name_org.getValue(),
					bpr_type_value + "", cardSelect.getValue(), list, cards, btn_click, alias, btn, res);
			if (res.getCode() == 1) {
				LtLogger.getLogger().error("product details:" + current.toString() + "\n" + res.getName());
				alert(res.getName());
				return;
			} else {
				client = client_obj.getId_client();
				Utils.clearForm(addgrd);
				refreshModel(_startPageNumber);
				alert("Кредит оформлен!");
				session.setAttribute("click_btn_save", 0);
				refreshModel(0);
				btn_giveBankProduct.setDisabled(true);
				if (current == null) {
					current = new Bproduct();
				}
				if (btn_click == 3) {
					btn_giveBankProductCreditApp.setVisible(false);
				}
				current.setId(Integer.parseInt(Utils.getData(
						"select id from (select * from bproduct_desc order by id desc) where detail_group=10 and rownum=1",
						"")));
				current.setBranch(branch1);
				credittab.setVisible(true);
				creditankettab.setVisible(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			alert(CheckNull.getPstr(e));
		}
	}

	@SuppressWarnings("unchecked")
	private List<CreateValues> getValues(int click) {
		List<CreateValues> list = new ArrayList<CreateValues>();
		List<Row> row = cRows.getChildren();
		for (int i = 0; i < row.size(); i++) {
			CreateValues val = new CreateValues();
			val.setTid(Long.parseLong(aprodid.getValue()));
			if (click == 4) {
				val.setCid(current.getId());
			}
			String param = "";
			if (row.get(i).getChildren().get(1) instanceof Textbox) {
				Textbox txt = (Textbox) row.get(i).getChildren().get(1);
				param = (String) txt.getAttribute("param");
				if (txt.getAttribute("required_field") != null) {
					int required = (Integer) txt.getAttribute("required_field");
					if (click != 3 && required == 1 && (txt.getValue() == null || txt.getValue().equals(""))) {
						Label label = (Label) row.get(i).getChildren().get(0);
						alert("Не заполнено обязательное поле! (" + label.getValue() + ")");
						txt.setFocus(true);
						return null;
					} else if (click == 3 && required == 1 && (txt.getValue() == null || txt.getValue().equals(""))
							&& param != null && param.contains("NI_REQ")) {
						Label label = (Label) row.get(i).getChildren().get(0);
						alert("Не заполнено обязательное поле! (" + label.getValue() + ")");
						txt.setFocus(true);
						return null;
					}
				}
				System.out.println(txt.getAttribute("param"));
				val.setValue(txt.getValue());
				val.setEn_name(txt.getAttribute("en_name") + "");
				val.setOid(Long.parseLong(txt.getAttribute("oid").toString()));
				val.setParam(txt.getAttribute("param") == null ? null : txt.getAttribute("param") + "");
			} else if (row.get(i).getChildren().get(1) instanceof RefCBox) {
				RefCBox rbox = (RefCBox) row.get(i).getChildren().get(1);
				param = (String) rbox.getAttribute("param");
				if (rbox.getAttribute("required_field") != null) {
					int required = (Integer) rbox.getAttribute("required_field");
					if (click != 3 && required == 1 && (rbox.getValue() == null || rbox.getValue().equals(""))) {
						Label label = (Label) row.get(i).getChildren().get(0);
						alert("Не заполнено обязательное поле! (" + label.getValue() + ")");
						rbox.setFocus(true);
						return null;
					} else if (click == 3 && required == 1 && (rbox.getValue() == null || rbox.getValue().equals(""))
							&& param != null && param.contains("NI_REQ")) {
						Label label = (Label) row.get(i).getChildren().get(0);
						alert("Не заполнено обязательное поле! (" + label.getValue() + ")");
						rbox.setFocus(true);
						return null;
					}
				}
				val.setValue(rbox.getValue());
				val.setEn_name(rbox.getAttribute("en_name") + "");
				val.setOid(Long.parseLong(rbox.getAttribute("oid").toString()));
				val.setParam(rbox.getAttribute("param") == null ? null : rbox.getAttribute("param") + "");
			} else if (row.get(i).getChildren().get(1) instanceof Datebox) {
				Datebox date = (Datebox) row.get(i).getChildren().get(1);

				String pattern = "dd.MM.yyyy";
				date.setFormat(pattern);

				System.out.println("date----RRR:" + date.getText());

				/*
				 * SimpleDateFormat sdm = new SimpleDateFormat(pattern); try {
				 * Date date1 = sdm.parse(date.getText());
				 * System.out.println("Time--rrrrrr----:"+date1.toString());
				 * System.out.println("Time--rrrrrr1111----:"+date1.getTime());
				 * } catch (WrongValueException e) { // TODO Auto-generated
				 * catch block e.printStackTrace(); } catch (ParseException e) {
				 * // TODO Auto-generated catch block e.printStackTrace(); }
				 */

				param = (String) date.getAttribute("param");
				if (date.getAttribute("required_field") != null) {
					int required = (Integer) date.getAttribute("required_field");
					if (click != 3 && required == 1 && (date.getValue() == null || date.getValue().equals(""))) {
						Label label = (Label) row.get(i).getChildren().get(0);
						alert("Не заполнено обязательное поле! (" + label.getValue() + ")");
						date.setFocus(true);
						return null;
					} else if (click == 3 && required == 1 && (date.getValue() == null || date.getValue().equals(""))
							&& param != null && param.contains("NI_REQ")) {
						Label label = (Label) row.get(i).getChildren().get(0);
						alert("Не заполнено обязательное поле! (" + label.getValue() + ")");
						date.setFocus(true);
						return null;
					}
				}
				val.setValue(date.getText());
				val.setEn_name(date.getAttribute("en_name") + "");
				val.setOid(Long.parseLong(date.getAttribute("oid").toString()));
				val.setParam(date.getAttribute("param") == null ? null : date.getAttribute("param") + "");
			} else if (row.get(i).getChildren().get(1) instanceof Decimalbox) {
				Decimalbox lbox = (Decimalbox) row.get(i).getChildren().get(1);
				DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols(Locale.getDefault());
				formatSymbols.setDecimalSeparator('.');
				formatSymbols.setGroupingSeparator(' ');
				lbox.setFormat("### ### ###.##");

				param = (String) lbox.getAttribute("param");
				if (lbox.getAttribute("required_field") != null) {
					int required = (Integer) lbox.getAttribute("required_field");
					if (click != 3 && required == 1 && (lbox.getValue() == null || lbox.getValue().equals(""))) {
						Label label = (Label) row.get(i).getChildren().get(0);
						alert("Не заполнено обязательное поле! (" + label.getValue() + ")");
						lbox.setFocus(true);
						return null;
					} else if (click == 3 && required == 1 && (lbox.getValue() == null || lbox.getValue().equals(""))
							&& param != null && param.contains("NI_REQ")) {
						Label label = (Label) row.get(i).getChildren().get(0);
						alert("Не заполнено обязательное поле! (" + label.getValue() + ")");
						lbox.setFocus(true);
						return null;
					}
				}
				val.setValue(lbox.getText());
				val.setEn_name(lbox.getAttribute("en_name") + "");
				val.setOid(Long.parseLong(lbox.getAttribute("oid").toString()));
				val.setParam(lbox.getAttribute("param") == null ? null
						: new StringBuilder().append(lbox.getAttribute("param")).toString());
				System.out.println("decimbox:" + new StringBuilder().append(lbox.getAttribute("param")).toString());

			}
			list.add(val);
		}
		return list;
	}
	public void NikiAsokiReq1(){
		try{
		String form_id1 = BproductService.getForm_id(current.getId(), branch1);
		nikiAsokiReq.setCredit_bureau("1");
		nikiAsokiReq.setApplication_id(form_id1);
		nikiAsokiReq.setBranch(current.getBranch());
		nikiAsokiReq.setRequest_type("1");
		
		NikiAsokiReq nikAsokReq = new NikiAsokiReq(nikiAsokiReq.getCredit_bureau().toString(),nikiAsokiReq.getApplication_id().toString(),
				nikiAsokiReq.getBranch().toString(),nikiAsokiReq.getRequest_type().toString());
		ISLogger.getLogger().error("ASOKI_NIKI_Req_01_Object: "+nikAsokReq.toString());
		NikiAsokiAnswer1 res = NikiAsokiAnswerRes.getAnswerNeq(nikAsokReq);
		String claim_id = res.getBureau().getResponse().getClaimId();
		ISLogger.getLogger().error("ASOKI_NIKI_Req_01_Claim_id: "+claim_id);
		if(res.getBureau().getResult().getCode().equals("05000")){
			
			alert("message:"+res.getBureau().getResult().getMessage());
		}
		else{
			alert("oshibka: "+res.getBureau().getResult().getMessage());
		}
		 } catch(Exception e){
			 ISLogger.getLogger().error(CheckNull.getPstr(e));
				alert(CheckNull.getPstr(e));
				e.printStackTrace();			 
		 }
		
        	
	}

	public void NikiAsokiReq21(){
						
	}
		
	public void onClick$btn_giveBankProduct() {
		try {
			Messagebox.show("Вы уверены что хотите оформить кредит?", "Предупреждение", Messagebox.YES | Messagebox.NO,
					Messagebox.EXCLAMATION, new EventListener() {

						@Override
						public void onEvent(Event event) throws Exception {
							if (event.getName().equals("onYes")) {
								try {
									// ------------------- Дополнительные
									// переменные для сравнения сумм
									// вклада и кредита-------------------------
									int trans_id = 0;
									ISLogger.getLogger().error("Нажата кнопка Оформить кредит");
									Long summProvision = 0L;
									Double amountOfCredit = db_ld_amount.doubleValue();
									Double amountOfCreditProcent = 0D;
									Double amountOfCreditProcentTemp = 0D;
									String bpr_type = "";
									String head_of_bank = "";
									Long provisionVal = 0L;
									String previousCl_id = BproductService.getPreviousClient_code();
									String currentCl_id = client_obj.getId_client();

									// -------------------------- ФИО
									// управляющего выдавшего кредит
									// ----------------------
									if (!CheckNull.isEmpty(aprodid.getValue())) {
										head_of_bank = BproductService.getHeadOfBankValue(alias,
												Integer.parseInt(aprodid.getValue()));
									}
									// ---------------------- Ввод даты
									// окончания договора кредита
									// -------------------
									// if
									// (!CheckNull.isEmpty(avdate.getValue())) {
									// dateContractComp =
									// sdf.format(avdate.getValue());//
									// avdate.getValue().toString();
									// }
									// ---------------------- Основная сумма
									// кредита, которая выдается
									// клиенту без процентов -------------------
									// ----------------------- Ввод банковского
									// продукта
									// ---------------------------
									if (!CheckNull.isEmpty(aprodid.getValue())) {
										bpr_type = Integer.toString(BproductService.getBpr_type_value(alias,
												Integer.parseInt(aprodid.getValue())));
										bpr_id = aprodid.getValue();
										accountList = BproductService.getBpr_ld_account(alias,
												Integer.parseInt(bpr_id));
									}
									provisionVal = BproductService.getProvisionVal(alias, Integer.parseInt(bpr_id));
									// ---------------------- Сумма обеспечения
									// -----------------------------
									if (bpr_type.equals("2") && !CheckNull.isEmpty(getAmountProvision())) {
										summProvision = getAmountProvision();
									}
									// ------------- Сумма кредита с процентами
									// -------------------
									if (!CheckNull.isEmpty(amountOfCredit)) {
										if (bpr_type.equals("2")) {
											double temp = provisionVal.doubleValue();
											double temp2 = ((temp / 100.0) - 1.0);
											amountOfCreditProcentTemp = amountOfCredit * temp2;
											amountOfCreditProcent = (Double) amountOfCreditProcentTemp + amountOfCredit;
										}
									}
									Double d = BproductService.getAmount(bpr_id, alias);
									if (d > 0.1) {
										if (db_ld_amount.doubleValue() > d) {
											ISLogger.getLogger()
													.error("asumm = " + db_ld_amount.getValue() + " Double = " + d);
											alert("Сумма кредита превышает максимально допустимую по данному шаблону!");
											return;
										}
									}
									if ((bpr_type.equals("1") || bpr_type.equals("4") || bpr_type.equals("5"))
											&& scoringSumma != null) {
										if (db_ld_amount.doubleValue() > scoringSumma) {
											DecimalFormat df = new DecimalFormat();
											df.setMaximumFractionDigits(2);
											df.setMinimumFractionDigits(0);
											df.setGroupingUsed(false);
											ISLogger.getLogger().error("scoring summa = " + scoringSumma + " asumm = "
													+ db_ld_amount.doubleValue());
											alert("Указанная сумма договора (" + df.format(db_ld_amount.doubleValue())
													+ ") превышает сумму скоринга (" + df.format(scoringSumma) + ")");
											return;
										}
									}
									// ------------- </Сумма кредита с
									// процентами> ------------------
									limit = BprChangeLimitService.getLimitValues(Integer.parseInt(bpr_id));
									parameters = bproductAddInfService.getParameters(bpr_type_value);
									trans_idTemp = BproductService.getTrans_id(alias, Integer.parseInt(bpr_id)); //проверка вид продукт если null = запрет
									trans_id = Integer.parseInt(trans_idTemp);
									ISLogger.getLogger().error("acustomer.getValue() = " + client_obj.getId_client());
									// ISLogger.getLogger().error("dateContractComp
									// = "+dateContractComp);
									ISLogger.getLogger().error("amountParam = " + amountOfCredit);
									ISLogger.getLogger().error("aprodid.getValue() = " + aprodid.getValue());
									if (client_obj.getId_client().equals("") || amountOfCredit == null
											|| amountOfCredit == 0 || aprodid.getValue().equals("")) {
										alert("Не все параметры заполнены!");
										return;
									}
									if (!previousCl_id.equals(currentCl_id) && bpr_type.equals("2")) {
										alert("Заполните и сохраните вклады и суммы вкладов во вкладке дополнительные характеристики!");
										return;
									}
									if (summProvision < amountOfCreditProcent && bpr_type.equals("2")) {
										alert("Недостаточная сумма обеспечения " + summProvision
												+ " для открытия кредита!");
										return;
									}
									if (bpr_type.equals("1") || bpr_type.equals("4") || bpr_type.equals("5")) {
										if (cardSelect.getValue() == null || cardSelect.getValue().equals("")) {
											alert("Не выбрана карта клиента");
											return;
										}
									}
									cardSelect.setAttribute("trans_id", trans_id);
									cardSelect.setAttribute("head_of_bank", head_of_bank);
									ISLogger.getLogger().error("Before saving! " + client_obj.getId_client());
									if (bpr_type_value != 2 && bpr_type_value != 3 && bpr_type_value != 6) {
										String str[] = BproductService.getMouths(dbox_date_close.getText(),
												cardSelect.getValue(), alias);
										System.out.println(alias+"  : "+dbox_date_close.getText());
										if (Double.parseDouble(str[0]) < 0) {
											Messagebox.show(
													"Срок действия ПК (" + str[1]
															+ ") меньше чем дата закрытия договора, Вы уверены что хотите продолжить?",
													"Предупреждение", Messagebox.YES | Messagebox.NO,
													Messagebox.EXCLAMATION, new EventListener() {

														@Override
														public void onEvent(Event evt) throws Exception {
															if (evt.getName().equals("onYes")) {
																Integer trans_id = (Integer) cardSelect
																		.getAttribute("trans_id");
																String head_of_bank = (String) cardSelect
																		.getAttribute("head_of_bank");
																saving(1, trans_id, head_of_bank);
															}
														}
													});
										} else {
											saving(1, trans_id, head_of_bank);
										}
									} else {
										saving(1, trans_id, head_of_bank);
									}
								} catch (Exception e) {
									ISLogger.getLogger().error(CheckNull.getPstr(e));
									alert(CheckNull.getPstr(e));
									e.printStackTrace();
								}
							}
						}
					});
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void onClick$btn_giveBankProductCreditApp() {
		try {
			Messagebox.show("Вы уверены что хотите оформить кредит?", "Предупреждение", Messagebox.YES | Messagebox.NO,
					Messagebox.EXCLAMATION, new EventListener() {

						@Override
						public void onEvent(Event event) throws Exception {
							if (event.getName().equals("onYes")) {
								try {
									// ------------------- Дополнительные
									// переменные для сравнения сумм
									// вклада и кредита-------------------------
									int trans_id = 0;
									ISLogger.getLogger().error("Нажата кнопка Оформить кредитную заявку");
									String summProvTemp = "";
									Long summProvision = 0L;
									Double amountOfCredit = db_ld_amount.doubleValue();
									Double amountOfCreditProcent = 0D;
									double amountOfCreditProcentTemp = 0;
									String bpr_type = "";
									String head_of_bank = "";
									Long provisionVal = 0L;
									String previousCl_id = BproductService.getPreviousClient_code();
									String currentCl_id = client_obj.getId_client();
									System.out.println("summa-----RRRR:" + db_ld_amount.getValue());

									// -------------------------- ФИО
									// управляющего выдавшего кредит
									// ----------------------
									if (!CheckNull.isEmpty(aprodid.getValue())) {
										head_of_bank = BproductService.getHeadOfBankValue(alias,
												Integer.parseInt(aprodid.getValue()));
									}
									// ---------------------- Ввод даты
									// окончания договора кредита
									// -------------------
									// if
									// (!CheckNull.isEmpty(avdate.getValue())) {
									// dateContractComp =
									// sdf.format(avdate.getValue());//
									// avdate.getValue().toString();
									// }
									// ---------------------- Основная сумма
									// кредита, которая выдается
									// клиенту без процентов -------------------
									// ----------------------- Ввод банковского
									// продукта
									// ---------------------------
									if (!CheckNull.isEmpty(aprodid.getValue())) {
										bpr_type = Integer.toString(BproductService.getBpr_type_value(alias,
												Integer.parseInt(aprodid.getValue())));
										bpr_id = aprodid.getValue();
										accountList = BproductService.getBpr_ld_account(alias,
												Integer.parseInt(bpr_id));
									}
									provisionVal = BproductService.getProvisionVal(alias, Integer.parseInt(bpr_id));
									// ---------------------- Сумма обеспечения
									// -----------------------------
									if (!CheckNull.isEmpty(aamountProvision.getValue())) {
										summProvTemp = aamountProvision.getValue();
										summProvision = Long.parseLong(summProvTemp);
									}
									// ------------- Сумма кредита с процентами
									// -------------------
									if (!CheckNull.isEmpty(amountOfCredit)) {
										if (bpr_type.equals("2")) {
											double temp = provisionVal.doubleValue();
											double temp2 = ((temp / 100.0) - 1.0);
											amountOfCreditProcentTemp = amountOfCredit * temp2;
											amountOfCreditProcent = (long) amountOfCreditProcentTemp + amountOfCredit;
										}
									}
									Double d = BproductService.getAmount(bpr_id, alias);
									System.out.println("dddd-----RRRR:" + d);
									if (d > 0.1) {
										if (db_ld_amount.doubleValue() > d) {
											ISLogger.getLogger()
													.error("asumm = " + db_ld_amount.getValue() + " Double = " + d);
											alert("Сумма кредита превышает максимально допустимую по данному шаблону!");
											return;
										}
									}
									// ------------- </Сумма кредита с
									// процентами> ------------------
									limit = BprChangeLimitService.getLimitValues(Integer.parseInt(bpr_id));
									parameters = bproductAddInfService.getParameters(bpr_type_value);
									trans_idTemp = BproductService.getTrans_id(alias, Integer.parseInt(bpr_id));
									trans_id = Integer.parseInt(trans_idTemp);
									System.out.println("client: "+client_obj.getId_client()+" amount: "+amountOfCredit == null+" mcr: "+amountOfCredit+
											" aprodid: "+aprodid.getValue());
									if (client_obj.getId_client().equals("") || amountOfCredit == null
											|| amountOfCredit == 0 || aprodid.getValue().equals("")) {
										alert("Не все параметры заполнены!");
										return;
									}
									if (!previousCl_id.equals(currentCl_id) && bpr_type.equals("2")) {
										alert("Заполните и сохраните вклады и суммы вкладов во вкладке дополнительные характеристики!");
										return;
									}
									if (summProvision < amountOfCreditProcent && bpr_type.equals("2")) {
										alert("Недостаточная сумма обеспечения для открытия кредита!");
										return;
									}
									if ((bpr_type.equals("1") || bpr_type.equals("4") || bpr_type.equals("5"))
											&& scoringSumma != null) {
										if (db_ld_amount.doubleValue() > scoringSumma) {
											DecimalFormat df = new DecimalFormat();
											df.setMaximumFractionDigits(2);
											df.setMinimumFractionDigits(0);
											df.setGroupingUsed(false);
											ISLogger.getLogger().error("scoring summa = " + scoringSumma + " asumm = "
													+ db_ld_amount.doubleValue());
											alert("Указанная сумма договора (" + df.format(db_ld_amount.doubleValue())
													+ ") превышает сумму скоринга (" + df.format(scoringSumma) + ")");
											return;
										}
									}
									// if(bpr_type.equals("1")||bpr_type.equals("4")||bpr_type.equals("5")){
									// if(cardSelect.getValue()==null||cardSelect.getValue().equals("")){
									// alert("Не выбрана карта клиента");
									// return;
									// }
									// }
									cardSelect.setAttribute("trans_id", trans_id);
									cardSelect.setAttribute("head_of_bank", head_of_bank);
									// if(bpr_type_value!=2&&bpr_type_value!=3){
									// String str[] =
									// BproductService.getMouths(avdate.getText(),
									// cardSelect.getValue(), alias);
									// if(Double.parseDouble(str[0])<12){
									// Messagebox.show("Срок действия ПК
									// ("+str[1]+") меньше чем дата закрытия
									// договора, Вы уверены что хотите
									// продолжить?", "Предупреждение",
									// Messagebox.YES|Messagebox.NO,
									// Messagebox.EXCLAMATION, new
									// EventListener() {
									//
									// @Override
									// public void onEvent(Event evt) throws
									// Exception {
									// if(evt.getName().equals("onYes")){
									// String amountParam = (String)
									// cardSelect.getAttribute("amountParam");
									// Integer trans_id = (Integer)
									// cardSelect.getAttribute("trans_id");
									// String head_of_bank = (String)
									// cardSelect.getAttribute("head_of_bank");
									// saving(3,amountParam,trans_id,head_of_bank);
									// }
									// }
									// });
									// }
									// } else {
									saving(3, trans_id, head_of_bank);
									// }
								} catch (Exception e) {
									ISLogger.getLogger().error(CheckNull.getPstr(e));
									alert(CheckNull.getPstr(e));
									e.printStackTrace();
								}
							}
						}
					});
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void onClick$btn_giveBankProductCreditAnket() {
		try {
			Messagebox.show("Вы уверены что хотите оформить кредит?", "Предупреждение", Messagebox.YES | Messagebox.NO,
					Messagebox.EXCLAMATION, new EventListener() {

						@Override
						public void onEvent(Event event) throws Exception {
							if (event.getName().equals("onYes")) {
								try {
									// ------------------- Дополнительные
									// переменные для сравнения сумм
									// вклада и кредита-------------------------
									int trans_id = 0;
									ISLogger.getLogger().error("Нажата кнопка Оформить кредитную анкету");
									String summProvTemp = "";
									Double summProvision = 0D;
									Double amountOfCredit = db_ld_amount.doubleValue();
									Double amountOfCreditProcent = 0D;
									double amountOfCreditProcentTemp = 0;
									String bpr_type = "";
									String head_of_bank = "";
									Long provisionVal = 0L;
									String previousCl_id = BproductService.getPreviousClient_code();
									String currentCl_id = client_obj.getId_client();
									// new update//
									String form_id = BproductService.getForm_id(current.getId(), branch1);
									String claim_id = BproductService.getNiki_id(form_id, current.getId(), branch1, null, alias);
									String loan_per = BproductService.getCreditTimeByMonthNew(branch1, claim_id, alias);
									double loan_rate = BproductService.getLdrateByClientNew(aprodid.getValue(), alias);
									ISLogger.getLogger().error("claim_id = " +claim_id+ " ,ld_amount: "+db_ld_amount.getValue()+" , form_id: "+form_id+
									" , aprod_id: "+aprodid.getValue()+" ,loan_per: "+loan_per+" , loan_rate:"+loan_rate);
									Res_Scoring res_score = null;
									double averageSum_loan = 0.00;
									double available_sumLoan = 0.00;									
									int loan_ind = 1200;
									if(aprodid.getValue()!=null && Integer.valueOf(aprodid.getValue())==96){
									
									try {										
									res_score = BproductService.getScoringByClaimId(branch1, Long.valueOf(claim_id),Integer.parseInt(aprodid.getValue()),alias);
									averageSum_loan = BproductService.getAvrLoan(amountOfCredit, Integer.valueOf(loan_per), loan_rate, alias);
									ISLogger.getLogger().error("scoring_summa_result :"+res_score.getCode()+" , res_message_result: "+res_score.getName()+" , averSumLoan: "+averageSum_loan);
									}
									catch(Exception e){
										ISLogger.getLogger().error("new_Loan_Scoring :"+CheckNull.getPstr(e));
										alert(CheckNull.getPstr(e));
										e.printStackTrace();		
									}
									
									if(res_score.getCode()>0.00 && res_score.getName().equals("Ok")){
									ISLogger.getLogger().error("scoring_summa :"+res_score.getCode()+" , res_message: "+res_score.getName());										
									}
									else{
										alert(res_score.getName());
										return;										
									}
									double income_half = res_score.getCode()/2;
									if(income_half < averageSum_loan){
										available_sumLoan = (income_half * Integer.valueOf(loan_per)*loan_ind)/(loan_ind + Integer.valueOf(loan_per)*loan_rate);
										 BigDecimal avail_loan = BproductService.truncateDecimal(available_sumLoan, 2);
										alert("Олинаётган кредит ойлик тулови мижоз ойлик уртача даромадининг ярмидан юкори !"
												+"\n"+"уртача ойлик даромад="+res_score.getCode());
										ISLogger.getLogger().error("available_sumLoan :"+available_sumLoan+" , income_half: "+income_half+", averSumLoan: "+avail_loan);
										return;
									}					
									  }
									if (!CheckNull.isEmpty(aprodid.getValue())) {
										head_of_bank = BproductService.getHeadOfBankValue(alias,
												Integer.parseInt(aprodid.getValue()));
									}
									// ---------------------- Ввод даты
									// окончания договора кредита
									// -------------------
									// if
									// (!CheckNull.isEmpty(avdate.getValue())) {
									// dateContractComp =
									// sdf.format(avdate.getValue());//
									// avdate.getValue().toString();
									// }
									// ---------------------- Основная сумма
									// кредита, которая выдается
									// клиенту без процентов -------------------
									// ----------------------- Ввод банковского
									// продукта
									// ---------------------------
									if (!CheckNull.isEmpty(aprodid.getValue())) {
										bpr_type = Integer.toString(BproductService.getBpr_type_value(alias,
												Integer.parseInt(aprodid.getValue())));
										bpr_id = aprodid.getValue();
										accountList = BproductService.getBpr_ld_account(alias,
												Integer.parseInt(bpr_id));
									}
									provisionVal = BproductService.getProvisionVal(alias, Integer.parseInt(bpr_id));
									// ---------------------- Сумма обеспечения
									// -----------------------------
									if (!CheckNull.isEmpty(aamountProvision.getValue())) {
										summProvTemp = aamountProvision.getValue();
										summProvision = Double.parseDouble(summProvTemp);
									}
									// ------------- Сумма кредита с процентами
									// -------------------
									if (!CheckNull.isEmpty(amountOfCredit)) {
										if (bpr_type.equals("2")) {
											double temp = provisionVal.doubleValue();
											double temp2 = ((temp / 100.0) - 1.0);
											amountOfCreditProcentTemp = amountOfCredit * temp2;
											amountOfCreditProcent = (long) amountOfCreditProcentTemp + amountOfCredit;
										}
									}
									Double d = BproductService.getAmount(bpr_id, alias);
									if (d > 0.1) {
										if (db_ld_amount.doubleValue() > d) {
											ISLogger.getLogger()
													.error("asumm = " + db_ld_amount.getValue() + " Double = " + d);
											alert("Сумма кредита превышает максимально допустимую по данному шаблону!");
											return;
										}
									}
									// ------------- </Сумма кредита с
									// процентами> ------------------
									limit = BprChangeLimitService.getLimitValues(Integer.parseInt(bpr_id));
									parameters = bproductAddInfService.getParameters(bpr_type_value);
									trans_idTemp = BproductService.getTrans_id(alias, Integer.parseInt(bpr_id));
									System.out.println("client: "+client_obj.getId_client()+" amoint: "+amountOfCredit+" amcrt: "+amountOfCredit+
											" aprodid: "+ aprodid.getValue());
									trans_id = Integer.parseInt(trans_idTemp);
									if (client_obj.getId_client().toString().equals("")
											// || dateContractComp==null
											// || dateContractComp.equals("")
											|| amountOfCredit == null    ///|| amountOfCredit == 0
											|| aprodid.getValue().equals("")) {
										alert("Не все параметры заполнены!");
										return;
									}
									if(amountOfCredit==0.00){
										alert("Сумма договора (Кредитная анкета) должень быть больше нуля!");
										return;										
									}
									if (!previousCl_id.equals(currentCl_id) && bpr_type.equals("2")) {
										alert("Заполните и сохраните вклады и суммы вкладов во вкладке дополнительные характеристики!");
										return;
									}
									if (summProvision < amountOfCreditProcent && bpr_type.equals("2")) {
										alert("Недостаточная сумма обеспечения для открытия кредита!");
										return;
									}
									if (bpr_type.equals("1") || bpr_type.equals("4") || bpr_type.equals("5")) {
										if (cardSelect.getValue() == null || cardSelect.getValue().equals("")) {
											alert("Не выбрана карта клиента");
											return;
										}
										if (scoringSumma != null) {
											if (db_ld_amount.doubleValue() > scoringSumma) {
												DecimalFormat df = new DecimalFormat();
												df.setMaximumFractionDigits(2);
												df.setMinimumFractionDigits(0);
												df.setGroupingUsed(false);
												ISLogger.getLogger().error("scoring summa = " + scoringSumma
														+ " asumm = " + db_ld_amount.doubleValue());
												alert("Указанная сумма договора ("
														+ df.format(db_ld_amount.doubleValue())
														+ ") превышает сумму скоринга (" + df.format(scoringSumma)
														+ ")");
												return;
											}
										}
									}
									cardSelect.setAttribute("trans_id", trans_id);
									cardSelect.setAttribute("head_of_bank", head_of_bank);
									if (bpr_type_value != 2 && bpr_type_value != 3 && bpr_type_value != 6) {
										String str[] = BproductService.getMouths(dbox_date_close.getText(),
												cardSelect.getValue(), alias);
										if (Double.parseDouble(str[0]) < 0) {
											Messagebox.show(
													"Срок действия ПК (" + str[1]
															+ ") меньше чем дата закрытия договора, Вы уверены что хотите продолжить?",
													"Предупреждение", Messagebox.YES | Messagebox.NO,
													Messagebox.EXCLAMATION, new EventListener() {

														@Override
														public void onEvent(Event evt) throws Exception {
															if (evt.getName().equals("onYes")) {
																Integer trans_id = (Integer) cardSelect
																		.getAttribute("trans_id");
																String head_of_bank = (String) cardSelect
																		.getAttribute("head_of_bank");
																saving(4, trans_id, head_of_bank);
															}
														}
													});
										} else {
											saving(4, trans_id, head_of_bank);
										}
									} else {
										saving(4, trans_id, head_of_bank);
									}
								} catch (Exception e) {
									ISLogger.getLogger().error(CheckNull.getPstr(e));
									alert(CheckNull.getPstr(e));
									e.printStackTrace();
								}
							}
						}
					});
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void onClick$btn_savingcredit() {
		onClick$btn_giveBankProductCreditAnket();
	}

	public void onClick$btn_canceling() {
		onClick$btn_back();
	}

	public void onClick$btn_savinganket() {
		onClick$btn_giveBankProductCreditAnket();
	}

	public void onClick$btn_show_provision_amount() {
		try {
			Double provisionAmountTemp = (double) 0;
			Long tempAmount = 0L;
			if (!CheckNull.isEmpty(aprodid.getValue())) {
				bpr_type_value = BproductService.getBpr_type_value(alias, Integer.parseInt(aprodid.getValue()));
			}
			if (bpr_type_value == 2) {
				parameters = bproductAddInfService.getParameters(bpr_type_value);
			}

			for (int i = 1; i < parameters.size(); i += 2) {
				if (!CheckNull.isEmpty(parameters.get(i).getParam_value())) {
					provisionAmountTemp += Double.parseDouble(parameters.get(i).getParam_value());
					tempAmount = provisionAmountTemp.longValue();
				}
			}

			if (!CheckNull.isEmpty(tempAmount)) {
				Long provisionAmount = tempAmount; // + tempAmount2;
				aamountProvision.setValue(provisionAmount.toString());
			} else if (tempAmount == 0L) {
				alert("Не выбрано ни одной суммы вклада!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			alert(CheckNull.getPstr(e));
		}
	}

	private Long getAmountProvision() {
		Long amount = 0L;
		try {
			Double provisionAmountTemp = (double) 0;
			Long tempAmount = 0L;
			if (!CheckNull.isEmpty(aprodid.getValue())) {
				bpr_type_value = BproductService.getBpr_type_value(alias, Integer.parseInt(aprodid.getValue()));
			}
			if (bpr_type_value == 2) {
				parameters = bproductAddInfService.getParameters(bpr_type_value);
			}

			for (int i = 1; i < parameters.size(); i += 2) {
				if (!CheckNull.isEmpty(parameters.get(i).getParam_value())) {
					provisionAmountTemp += Double.parseDouble(parameters.get(i).getParam_value());
					tempAmount = provisionAmountTemp.longValue();
				}
			}

			if (!CheckNull.isEmpty(tempAmount)) {
				amount = tempAmount; // + tempAmount2;
			} else if (tempAmount == 0L) {
				alert("Не выбрано ни одной суммы вклада!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			alert(CheckNull.getPstr(e));
		}
		return amount;
	}

	private void createCRowsGrid() {
		try {
			Utils.clearRows(cRows);
			if (current != null) {
				String bpr_id;
				if (btn.equals("double")) {
					bpr_id = current.getProdid() + "";
				} else {
					bpr_id = aprodid.getValue();
				}
				List<TemplateFields> fields = CreateService.getFields(bpr_id);
				for (int i = 0; i < fields.size(); i++) {
					cRows.appendChild(getRow(fields.get(i)));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
	}

	private Row getRow(TemplateFields tmp) {
		Row row = new Row();
		Label label = new Label(tmp.getLabel_name());
		String param = null;
		if (tmp.getModel() != null && tmp.getModel().equals("scoring")
				&& (tmp.getConformity_id() != null && tmp.getConformity_id() != 0)) {
			param = BproductService.getParam(tmp.getTid(), tmp.getOid());
		}
		row.appendChild(label);
		String value = null;
		if (btn.equals("double")) {
			List<CreateValues> list = CreateService.getValues(current.getId() + "");
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getOid().equals(tmp.getOid())) {
					value = list.get(i).getValue();
				}
			}
		}
		if (btn.equals("add") && tmp.getModel() != null && tmp.getModel().equals("employee")) {
			Long id = tmp.getConformity_id();
			value = Utils.getData(
					"select emp.value from ss_bpr_employees emp where emp.branch='" + branch1 + "' and emp.label=" + id,
					"");
		}
		if (tmp.getType_field().equals("text")) {
			Textbox txt = new Textbox();
			txt.setAttribute("oid", tmp.getOid().toString());
			txt.setValue(value);
			txt.setWidth("80%");
			if (tmp.getModel() != null && tmp.getModel().equals("scoring") && tmp.getConformity_id() != null
					&& tmp.getConformity_id() != 0) {
				txt.setMaxlength(Integer.parseInt(BproductService.getMaxLenght(tmp.getConformity_id())));
			}
			txt.setAttribute("en_name", tmp.getLabel_name_en());
			txt.setAttribute("required_field", tmp.getRequired_field());
			if (param != null) {
				addEvents(param, txt);
			}
			if (value != null && !value.equals("")) {
				txt.setReadonly(true);
			}
			txt.setAttribute("param", param);
			if (btn.equals("double")) {
				txt.setReadonly(true);
			}
			row.appendChild(txt);
		} else if (tmp.getType_field().equals("list")) {
			RefCBox rbox = new RefCBox();
			rbox.setAttribute("oid", tmp.getOid().toString());
			if (tmp.getModel() != null && tmp.getModel().equals("scoring")) {
				rbox.setModel(new ListModelList(Utils.getRefData(Utils.getData(
						"select model from ss_bpr_scoring_anket where id=(select conformity_id from bpr_template_fields where oid="
								+ tmp.getOid() + ")",
						""), "")));
			} else {
				rbox.setModel(new ListModelList(CreateService.getReferences(tmp.getSid())));
			}
			rbox.setAttribute("value", value);
			rbox.setWidth("80%");
			rbox.setAttribute("param", param);
			if (btn.equals("double")) {
				rbox.setReadonly(true);
				rbox.setButtonVisible(false);
			}
			rbox.setAttribute("required_field", tmp.getRequired_field());
			rbox.addEventListener("onInitRenderLater", new EventListener() {

				@Override
				public void onEvent(Event evt) throws Exception {
					RefCBox rbox = (RefCBox) evt.getTarget();
					String value = (String) rbox.getAttribute("value");
					rbox.setSelecteditem(value);
				}
			});
			rbox.setAttribute("en_name", tmp.getLabel_name_en());
			row.appendChild(rbox);
		} else if (tmp.getType_field().equals("date")) {
			Datebox date = new Datebox();
			date.setAttribute("oid", tmp.getOid().toString());
			date.setFormat("dd.MM.yyyy");
			date.addEventListener(Events.ON_CHANGE, datebox_change_event);
			date.addEventListener(Events.ON_SELECT, datebox_select_event);
			if (tmp.getModel() != null && tmp.getModel().equals("scoring") && tmp.getConformity_id() != null
					&& (tmp.getConformity_id() == 9 || tmp.getConformity_id() == 36 || tmp.getConformity_id() == 48)) {
				if (btn.equals("double")) {
					Res res = new Res();
					date.setValue(Utils.getInfoDate(alias, res));
				} else if (btn.equals("add")) {
					date.setText(current.getVdate());
				}
			}
			date.setText(value);
			date.setWidth("40%");
			if (btn.equals("double")) {
				date.setReadonly(true);
				date.setButtonVisible(false);
			}
			date.setAttribute("param", param);
			date.setAttribute("en_name", tmp.getLabel_name_en());
			date.setAttribute("required_field", tmp.getRequired_field());
			if (param != null) {
				addEvents(param, date);
			}
			row.appendChild(date);
		} else if (tmp.getType_field().equals("number")) {
			Decimalbox lbox = new Decimalbox();
			lbox.setAttribute("oid", tmp.getOid().toString());
			lbox.setAttribute("en_name", tmp.getLabel_name_en());
			lbox.setText(value);
			if (btn.equals("double")) {
				lbox.setReadonly(true);
			}
			lbox.setWidth("40%");
			if (tmp.getModel() != null && tmp.getModel().equals("scoring") && tmp.getConformity_id() != null
					&& tmp.getConformity_id() != 0) {
				lbox.setMaxlength(Integer.parseInt(BproductService.getMaxLenght(tmp.getConformity_id())));
			}
			lbox.setAttribute("param", param);
			lbox.setAttribute("required_field", tmp.getRequired_field());
			if (param != null) {
				addEvents(param, lbox);
			}
			row.appendChild(lbox);
		}
		return row;
	}

	private void getCurrentValue() {
		if (current != null) {
			// acustomer.setValue(current.getCustomer());
			client = current.getCustomer();
			// eq_date.setText(current.getVdate());
			aprodid.setSelecteditem(current.getProdid() + "");
			if (current.getAmount() != null) {
				char[] array = current.getAmount().toCharArray();
				String tempSumma = "";
				for (int i = 0; i < array.length; i++) {
					if (array[i] != ' ') {
						tempSumma += array[i];
					}
				}
				db_ld_amount.setValue(tempSumma);
			}
			// reg_num.setValue(current.getNi_req_id()==0?"":current.getNi_req_id()+"");
			System.out.println("CARD CARD = " + current.getCard_number());
			cardSelect.setSelecteditem(current.getCard_number());
		}
	}

	private void addEvents(String param, Component comp) {
		if (comp instanceof Decimalbox) {
			if (param.equals("P-LD_CHAR-LD_AMOUNT")) {
				db_ld_amount = (Decimalbox) comp;
				db_ld_amount.setFormat("#0.00");
			}
			if (param.equals("P-LD_CHAR-AMOUNT")) {
				db_amount = (Decimalbox) comp;
				db_amount.setFormat("#0.00");
			}
			if (param.equals("P-NI_REQ-AMOUNT")) {
				ni_req_amount = (Decimalbox) comp;
				ni_req_amount.setFormat("#0.00");
				ni_req_amount.addEventListener(Events.ON_CHANGE, new EventListener() {

					@Override
					public void onEvent(Event evt) throws Exception {
						Decimalbox dbox = (Decimalbox) evt.getTarget();
						if (db_amount != null) {
							db_amount.setValue(dbox.getValue());
						}
						if (db_ld_amount != null) {
							db_ld_amount.setValue(dbox.getValue());
						}
					}
				});
			}
		} else if (comp instanceof Textbox) {
			if (param.equals("P-NI_REQ-REQNUM")) {
				((Textbox) comp).addEventListener(Events.ON_CHANGE, new EventListener() {

					@Override
					public void onEvent(Event evt) throws Exception {
						Textbox txt = (Textbox) evt.getTarget();
						if (ld_char_eq_num != null) {
							ld_char_eq_num.setValue(txt.getValue());
						}
					}
				});
			} else if (param.equals("P-LD_CHAR-EQ_NUM")) {
				ld_char_eq_num = (Textbox) comp;
			} else if (param.equals("P-NI_REQ-INN_ORG")) {
				inn_org = (Textbox) comp;
			} else if (param.equals("P-NI_REQ-NAME_ORG")) {
				name_org = (Textbox) comp;
			}
		} else if (comp instanceof Datebox) {
			if (param.equals("P-NI_REQ-V_DATE")) {
				((Datebox) comp).addEventListener(Events.ON_CHANGE, new EventListener() {

					@Override
					public void onEvent(Event evt) throws Exception {
						Datebox dbox = (Datebox) evt.getTarget();
						if (eq_date != null) {
							eq_date.setValue(dbox.getValue());
						}
						if (ld_date != null) {
							ld_date.setValue(dbox.getValue());
						}
					}
				});
			} else if (param.equals("P-LD_CHAR-LD_DATE")) {
				ld_date = (Datebox) comp;
			} else if (param.equals("P-LD_CHAR-EQ_DATE")) {
				eq_date = (Datebox) comp;
			} else if (param.equals("P-LD_CHAR-DATE_CLOSE")) {
				dbox_date_close = (Datebox) comp;
			} else if (param.equals("P-NI_REQ-END_DATE")) {
				((Datebox) comp).addEventListener(Events.ON_CHANGE, new EventListener() {

					@Override
					public void onEvent(Event evt) throws Exception {
						Datebox dbox = (Datebox) evt.getTarget();
						if (dbox_date_close != null) {
							dbox_date_close.setValue(dbox.getValue());
						}
					}
				});
			}
		}
	}

}
