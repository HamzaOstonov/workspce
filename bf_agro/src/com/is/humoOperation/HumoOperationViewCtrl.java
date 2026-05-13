package com.is.humoOperation;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.zkoss.util.media.AMedia;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zkplus.databind.BindingListModelList;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Timebox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;
import org.zkoss.zul.event.PagingEvent;

//import com.is.tieto_visa.fileProcessing.FileService;
//import com.is.tieto_visa.fileProcessing.HumoFileRecords;
import com.is.nibbd_notify.NibbdNotify_vc;
import com.is.nibbd_notify.NibbdService;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.Res;

public class HumoOperationViewCtrl extends GenericForwardComposer {
	private Div frm;
	private Paging contactPaging;
	private Div grd;
	private Listbox dataGrid, timeGrid;
	private Hbox frmgrd;
	private Grid addgrd, fgrd;
	private Toolbarbutton btn_last, btn_save;
	private Toolbarbutton btn_next;
	private Toolbarbutton btn_prev;
	private Toolbarbutton btn_first;
	private Toolbarbutton btn_add;
	private Toolbarbutton btn_search;
	private Toolbarbutton btn_back;
	private Toolbar tb;
	private RefCBox id, oper, term_type, card_branch, term_branch, mcc,
			tcard_branch, tterm_branch, rcb_operation_id, rcb_descripption,
			rcb_dt, rcb_kt, rcb_term_type, rcb_oper, rcb_card_branch,
			rcb_term_branch, rcb_tr_type_expt;
	private RefCBox rcb_tr_type_b, rcb_mcc, rcb_komis, rcb_terminal,
			rcb_acc_term, rcb_accnt_ccy, rcb_tran_ccy, rcb_deb_cred,
			rcb_country, rcb_in_file, rcb_tr_type2_expt, rcb_msc;
	private Textbox tr_type_expt, tr_type_b, komis, terminal, acc_term,
			accnt_ccy, tran_ccy, deb_cred, country, txb_file_id,
			txb_file_id_clir;
	private Textbox aid, aterm_type, aoper, acard_branch, aterm_branch,
			atr_type_expt, atr_type_b, amcc, akomis, aterminal, aacc_term,
			aaccnt_ccy, atran_ccy, adeb_cred, acountry, ain_file,
			atr_type2_expt, amsc;
	private Textbox fid, fterm_type, foper, fcard_branch, fterm_branch,
			ftr_type_expt, ftr_type_b, fmcc, fkomis, fterminal, facc_term,
			faccnt_ccy, ftran_ccy, fdeb_cred, fcountry;
	protected Checkbox chb_one, chb_two, chb_three;
	private Paging humooperationPaging;
	private int _pageSize = 15;
	private int _startPageNumber = 0;
	private int _totalSize = 0;
	private boolean _needsTotalSizeUpdate = true;
	private Radio ra1;

	public HumoOperationFilter filter = new HumoOperationFilter();

	PagingListModel model = null;
	ListModelList lmodel = null;
	private AnnotateDataBinder binder;
	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	private String alias, branch1;
	private HashMap hOperType = new HashMap();
	private HashMap hTerminalKind = new HashMap();
	private HashMap hTermTorg = new HashMap();

	private HumoOperation current = new HumoOperation();
	private TimeTable currentTimeItem = new TimeTable();

	public HumoOperationViewCtrl() {
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
		// String[] parameter = (String[]) param.get("ht");
		// if (parameter != null) {
		// _pageSize = Integer.parseInt(parameter[0]) / 36;
		// dataGrid.setRows(Integer.parseInt(parameter[0]) / 36);
		// }
		_pageSize = 15;
		dataGrid.setRows(15);
		alias = (String) session.getAttribute("alias");
		branch1 = (String) session.getAttribute("branch");

		dataGrid.setItemRenderer(new ListitemRenderer() {
			public void render(Listitem row, Object data) throws Exception {
				HumoOperation pHumoOperation = (HumoOperation) data;

				row.setValue(pHumoOperation);

				row.appendChild(new Listcell(pHumoOperation.getOperation_id()));
				row.appendChild(new Listcell(pHumoOperation.getDescripption()));
				row.appendChild(new Listcell(pHumoOperation.getDt()));
				row.appendChild(new Listcell(pHumoOperation.getKt()));
				row.appendChild(new Listcell(pHumoOperation.getTerm_type()));
				row.appendChild(new Listcell(pHumoOperation.getOper()));
				row.appendChild(new Listcell(pHumoOperation.getCard_branch()));
				row.appendChild(new Listcell(pHumoOperation.getTerm_branch()));
				row.appendChild(new Listcell(pHumoOperation.getTr_type_expt()));
				row.appendChild(new Listcell(pHumoOperation.getTr_type_b()));
				row.appendChild(new Listcell(pHumoOperation.getMcc()));
				row.appendChild(new Listcell(pHumoOperation.getKomis()));
				row.appendChild(new Listcell(pHumoOperation.getTerminal()));
				row.appendChild(new Listcell(pHumoOperation.getAcc_term()));
				row.appendChild(new Listcell(pHumoOperation.getAccnt_ccy()));
				row.appendChild(new Listcell(pHumoOperation.getTran_ccy()));
				row.appendChild(new Listcell(pHumoOperation.getDeb_cred()));
				row.appendChild(new Listcell(pHumoOperation.getCountry()));
				row.appendChild(new Listcell(pHumoOperation.getIn_file()));
				row.appendChild(new Listcell(pHumoOperation.getTr_type2_expt()));
				row.appendChild(new Listcell(pHumoOperation.getMsc()));

				Listcell lc = new Listcell();

				Toolbarbutton bt = new Toolbarbutton();
				bt.setLabel("удалить");
				bt.setImage("/images/delete.png");
				bt.setAttribute("rid", pHumoOperation.getRid());
				bt.addEventListener("onClick",
						new EventListener() {
							public void onEvent(Event event) throws Exception {
								//String s1=((String) event.getTarget()
								//.getAttribute("rid")).toString();
								Res res1 = HumoOperationService.del_rec(((String) event.getTarget()
										.getAttribute("rid"))
										, alias);
								if (res1.getCode() != 0) {
									alert(res1.getName());
								} else {
									refreshModel(_startPageNumber);
									alert("Удалено!");
								}
							}
						});
				lc.appendChild(bt);	
				row.appendChild(lc);
			}
		});

		timeGrid.setItemRenderer(new ListitemRenderer() {
			public void render(Listitem row, Object data) throws Exception {
				TimeTable pHumoOperation = (TimeTable) data;

				row.setValue(pHumoOperation);

				row.appendChild(new Listcell(pHumoOperation.getId()));
				row.appendChild(new Listcell(pHumoOperation.getName()));
				String sign_work_day = "Не задан";
				if (pHumoOperation.getSign_work_day() == null)
					sign_work_day = "Не задан";
				else if (pHumoOperation.getSign_work_day().equals("1"))
					sign_work_day = "Рабочый";
				else if (pHumoOperation.getSign_work_day().equals("0"))
					sign_work_day = "Нерабочый";
				row.appendChild(new Listcell(sign_work_day));

				if (isTime(pHumoOperation.getStart_time())) {

					Listcell start_time_cell = new Listcell();
					start_time_cell.setStyle("padding:2px;");
					final Timebox start_timebox = new Timebox();
					// start_timebox.setStyle("width: 80%;");
					start_timebox.setFormat("HH:mm");
					start_timebox.setHflex("1");
					start_timebox.setWidth("100%");
					Calendar calendar = Calendar.getInstance();
					calendar.set(Calendar.HOUR_OF_DAY, Integer
							.parseInt(pHumoOperation.getStart_time().substring(
									0, 2)));
					calendar.set(Calendar.MINUTE, Integer
							.parseInt(pHumoOperation.getStart_time().substring(
									3, 5)));
					Date date1 = calendar.getTime();
					start_timebox.setValue(date1);
					start_timebox.addEventListener("onChange",
							new EventListener() {
								public void onEvent(Event event)
										throws Exception {
									String s = "";
									java.util.Date d1 = ((Timebox) event
											.getTarget()).getValue();
									Calendar calendar = Calendar.getInstance(); // Creates
																				// a
																				// new
																				// Calendar
																				// instance
									calendar.setTime(d1); // Assigns the
															// Calendar to the
															// given date
									int hours = calendar
											.get(Calendar.HOUR_OF_DAY);
									if (hours < 10)
										s = "0" + hours + ":";
									else
										s = hours + ":";
									int minutes = calendar.get(Calendar.MINUTE); // Gets
																					// the
																					// minute
																					// of
																					// the
																					// hour
																					// (0-59)
									if (minutes < 10)
										s = s + "0" + minutes;
									else
										s = s + minutes;
									currentTimeItem.setStart_time(s);
									((TimeTable) ((Listitem) event.getTarget()
											.getParent().getParent())
											.getValue()).setStart_time(s);
								}
							});
					start_timebox.addEventListener("onFocus",
							new EventListener() {
								public void onEvent(Event event)
										throws Exception {
									// event.getTarget().getParent();
									timeGrid.setSelectedItem((Listitem) event
											.getTarget().getParent()
											.getParent());
								}
							});
					start_time_cell.appendChild((Component) start_timebox);
					row.appendChild(start_time_cell);
				} else {
					row.appendChild(new Listcell(pHumoOperation.getStart_time()));
				}

				if (isTime(pHumoOperation.getEnd_time())) {
					Listcell end_time_cell = new Listcell();
					end_time_cell.setStyle("padding:2px;");
					final Timebox end_timebox = new Timebox();
					end_timebox.setFormat("HH:mm");
					end_timebox.setHflex("1");
					end_timebox.setWidth("100%");
					Calendar calendar = Calendar.getInstance();
					calendar.set(Calendar.HOUR_OF_DAY, Integer
							.parseInt(pHumoOperation.getEnd_time().substring(0,
									2)));
					calendar.set(Calendar.MINUTE, Integer
							.parseInt(pHumoOperation.getEnd_time().substring(3,
									5)));
					Date date1 = calendar.getTime();
					end_timebox.setValue(date1);
					end_timebox.addEventListener("onChange",
							new EventListener() {
								public void onEvent(Event event)
										throws Exception {
									String s = "";
									java.util.Date d1 = ((Timebox) event
											.getTarget()).getValue();
									Calendar calendar = Calendar.getInstance(); // Creates
																				// a
																				// new
																				// Calendar
																				// instance
									calendar.setTime(d1); // Assigns the
															// Calendar to the
															// given date
									int hours = calendar
											.get(Calendar.HOUR_OF_DAY);
									if (hours < 10)
										s = "0" + hours + ":";
									else
										s = hours + ":";
									int minutes = calendar.get(Calendar.MINUTE); // Gets
																					// the
																					// minute
																					// of
																					// the
																					// hour
																					// (0-59)
									if (minutes < 10)
										s = s + "0" + minutes;
									else
										s = s + minutes;
									currentTimeItem.setEnd_time(s);
									((TimeTable) ((Listitem) event.getTarget()
											.getParent().getParent())
											.getValue()).setEnd_time(s);
								}
							});
					end_timebox.addEventListener("onFocus",
							new EventListener() {
								public void onEvent(Event event)
										throws Exception {
									timeGrid.setSelectedItem((Listitem) event
											.getTarget().getParent()
											.getParent());
									// event.getTarget().getParent().getParent();
								}
							});
					end_time_cell.appendChild((Component) end_timebox);
					row.appendChild(end_time_cell);
				} else {
					row.appendChild(new Listcell(pHumoOperation.getEnd_time()));
				}

				// row.appendChild(new Listcell(pHumoOperation.getStatus()));
				Listcell status_cell = new Listcell();
				status_cell
						.setStyle("white-space: normal; word-wrap: normal; width: 100%;");

				final RefCBox status_combobox = new RefCBox();
				status_combobox.setStyle("width: 100%;");
				Comboitem item = new Comboitem();
				item.setValue("1");
				item.setLabel("Актив");
				status_combobox.appendChild(item);
				if (pHumoOperation.getStatus() != null
						&& pHumoOperation.getStatus().equals("1"))
					status_combobox.setSelectedItem(item);

				item = new Comboitem();
				item.setValue("0");
				item.setLabel("Неактив");
				status_combobox.appendChild(item);
				if (pHumoOperation.getStatus() != null
						&& pHumoOperation.getStatus().equals("0"))
					status_combobox.setSelectedItem(item);
				status_combobox.addEventListener("onSelect",
						new EventListener() {
							public void onEvent(Event event) throws Exception {
								currentTimeItem
										.setStatus(((com.is.utils.RefCBox) event
												.getTarget()).getValue());
								((TimeTable) ((Listitem) event.getTarget()
										.getParent().getParent()).getValue())
										.setStatus(((com.is.utils.RefCBox) event
												.getTarget()).getValue());
							}
						});
				status_combobox.addEventListener("onFocus",
						new EventListener() {
							public void onEvent(Event event) throws Exception {
								timeGrid.setSelectedItem((Listitem) event
										.getTarget().getParent().getParent()); // listboxda
																				// qatorni
																				// belgilash.
																				// currentTimeItem
																				// uchun
																				// kerak
							}
						});
				status_cell.appendChild((Component) status_combobox);
				row.appendChild(status_cell);

				// сохранить деган тугма
				Listcell lc = new Listcell();

				Toolbarbutton bt = new Toolbarbutton();
				bt.setLabel("Сохранить");
				// bt.setImage("/images/delete.png");
				bt.setHflex("1");
				bt.setWidth("100%");
				bt.setAttribute("r_id", pHumoOperation.getId());
				bt.addEventListener("onClick", new EventListener() {
					public void onEvent(Event event) throws Exception {
						timeGrid.setSelectedItem((Listitem) event.getTarget()
								.getParent().getParent()); // listboxda qatorni
															// belgilash.
															// currentTimeItem
															// uchun kerak
						currentTimeItem = (TimeTable) ((Listitem) event
								.getTarget().getParent().getParent())
								.getValue();
						Res res = HumoOperationService
								.updateTimeTable(currentTimeItem);
						if (res.getCode() == 0) {
							alert("Успешно сохранено!");
						} else {
							alert(res.getName());
						}
					}
				});
				lc.appendChild(bt);
				row.appendChild(lc);
			}
		});

		/*
		 * id.setModel(new
		 * ListModelList(HumoOperationService.getOperType(alias)));
		 * term_type.setModel(new ListModelList(HumoOperationService
		 * .getTerminalKind(alias))); card_branch.setModel(new
		 * ListModelList(HumoOperationService .getBranchType(alias)));
		 * term_branch.setModel(new ListModelList(HumoOperationService
		 * .getBranchType(alias))); mcc.setModel(new
		 * ListModelList(HumoOperationService.getMccCode(alias)));
		 * oper.setModel(new
		 * ListModelList(HumoOperationService.getTermTorg(alias)));
		 * tcard_branch.setModel(new ListModelList(HumoOperationService
		 * .getBranchType(alias))); tterm_branch.setModel(new
		 * ListModelList(HumoOperationService .getBranchType(alias)));
		 * 
		 * hOperType = HumoOperationService.getHOperType(alias); hTerminalKind =
		 * HumoOperationService.getHTerminalKind(alias); hTermTorg =
		 * HumoOperationService.getHTermTorg(alias);
		 * 
		 * refreshModel(_startPageNumber);
		 * refreshFilterComboBoxes(_startPageNumber);
		 */

		List<TimeTable> timeList = HumoOperationService.getTimeTableList(alias);
		timeGrid.setModel(new BindingListModelList(timeList, true));

	}

	public void onPaging$humooperationPaging(ForwardEvent event) {
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModel(_startPageNumber);
		// refreshFilterComboBoxes(_startPageNumber);
	}

	private void refreshModel(int activePage) {

		humooperationPaging.setPageSize(_pageSize);
		model = new PagingListModel(activePage, _pageSize, filter, alias);

		_totalSize = model.getTotalSize(filter, alias);

		humooperationPaging.setTotalSize(_totalSize);

		dataGrid.setModel((ListModel) model);
		if (model.getSize() > 0) {
			dataGrid.setSelectedIndex(0);
			sendSelEvt();
		}
	}

	private Boolean isTime(String s) {
		if (s == null)
			return false;
		if (s.equals(""))
			return false;
		if (s.length() != 5)
			return false;
		if (!s.substring(2, 3).equals(":"))
			return false;
		try {
			int number = Integer.parseInt(s.substring(0, 2));
			if (number < 0 || number > 23)
				return false;
		} catch (NumberFormatException e) {
			return false;
		}
		try {
			int number = Integer.parseInt(s.substring(3, 5));
			if (number < 0 || number > 59)
				return false;
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	private void refreshFilterComboBoxes(int activePage) {

		rcb_operation_id.setModel(new ListModelList(HumoOperationService
				.getOperation_id(alias, filter)));
		rcb_descripption.setModel(new ListModelList(HumoOperationService
				.getDescripption(alias, filter)));
		rcb_dt.setModel(new ListModelList(HumoOperationService.getDt(alias,
				filter)));
		rcb_kt.setModel(new ListModelList(HumoOperationService.getKt(alias,
				filter)));
		rcb_term_type.setModel(new ListModelList(HumoOperationService
				.getTerm_type(alias, filter)));
		rcb_oper.setModel(new ListModelList(HumoOperationService.getOper(alias,
				filter)));
		rcb_card_branch.setModel(new ListModelList(HumoOperationService
				.getCard_branch(alias, filter)));
		rcb_term_branch.setModel(new ListModelList(HumoOperationService
				.getTerm_branch(alias, filter)));
		rcb_tr_type_expt.setModel(new ListModelList(HumoOperationService
				.getTr_type_expt(alias, filter)));
		rcb_tr_type_b.setModel(new ListModelList(HumoOperationService
				.getTr_type_b(alias, filter)));
		rcb_mcc.setModel(new ListModelList(HumoOperationService.getMcc(alias,
				filter)));
		rcb_komis.setModel(new ListModelList(HumoOperationService.getKomis(
				alias, filter)));
		rcb_terminal.setModel(new ListModelList(HumoOperationService
				.getTerminal(alias, filter)));
		rcb_acc_term.setModel(new ListModelList(HumoOperationService
				.getAcc_term(alias, filter)));
		rcb_accnt_ccy.setModel(new ListModelList(HumoOperationService
				.getAccnt_ccy(alias, filter)));
		rcb_tran_ccy.setModel(new ListModelList(HumoOperationService
				.getTran_ccy(alias, filter)));
		rcb_deb_cred.setModel(new ListModelList(HumoOperationService
				.getDeb_cred(alias, filter)));
		rcb_country.setModel(new ListModelList(HumoOperationService.getCountry(
				alias, filter)));
		rcb_in_file.setModel(new ListModelList(HumoOperationService.getIn_file(
				alias, filter)));
		rcb_tr_type2_expt.setModel(new ListModelList(HumoOperationService
				.getTr_type2_expt(alias, filter)));
		rcb_msc.setModel(new ListModelList(HumoOperationService.getMsc(alias,
				filter)));

	}

	public void onChange$rcb_operation_id() {
		filter.setOperation_id(rcb_operation_id.getValue());
		refreshModel(_startPageNumber);
		refreshFilterComboBoxes(_startPageNumber);
	}

	public void onChange$rcb_descripption() {
		filter.setDescripption(rcb_descripption.getValue());
		refreshModel(_startPageNumber);
		refreshFilterComboBoxes(_startPageNumber);
	}

	public void onChange$rcb_dt() {
		filter.setDt(rcb_dt.getValue());
		refreshModel(_startPageNumber);
		refreshFilterComboBoxes(_startPageNumber);
	}

	public void onChange$rcb_kt() {
		filter.setKt(rcb_kt.getValue());
		refreshModel(_startPageNumber);
		refreshFilterComboBoxes(_startPageNumber);
	}

	public void onChange$rcb_term_type() {
		filter.setTerm_type(rcb_term_type.getValue());
		refreshModel(_startPageNumber);
		refreshFilterComboBoxes(_startPageNumber);
	}

	public void onChange$rcb_oper() {
		filter.setOper(rcb_oper.getValue());
		refreshModel(_startPageNumber);
		refreshFilterComboBoxes(_startPageNumber);
	}

	public void onChange$rcb_card_branch() {
		filter.setCard_branch(rcb_card_branch.getValue());
		refreshModel(_startPageNumber);
		refreshFilterComboBoxes(_startPageNumber);
	}

	public void onChange$rcb_term_branch() {
		filter.setTerm_branch(rcb_term_branch.getValue());
		refreshModel(_startPageNumber);
		refreshFilterComboBoxes(_startPageNumber);
	}

	public void onChange$rcb_tr_type_expt() {
		filter.setTr_type_expt(rcb_tr_type_expt.getValue());
		refreshModel(_startPageNumber);
		refreshFilterComboBoxes(_startPageNumber);
	}

	public void onChange$rcb_tr_type_b() {
		filter.setTr_type_b(rcb_tr_type_b.getValue());
		refreshModel(_startPageNumber);
		refreshFilterComboBoxes(_startPageNumber);
	}

	public void onChange$rcb_mcc() {
		filter.setMcc(rcb_mcc.getValue());
		refreshModel(_startPageNumber);
		refreshFilterComboBoxes(_startPageNumber);
	}

	public void onChange$rcb_komis() {
		filter.setKomis(rcb_komis.getValue());
		refreshModel(_startPageNumber);
		refreshFilterComboBoxes(_startPageNumber);
	}

	public void onChange$rcb_terminal() {
		filter.setTerminal(rcb_terminal.getValue());
		refreshModel(_startPageNumber);
		refreshFilterComboBoxes(_startPageNumber);
	}

	public void onChange$rcb_acc_term() {
		filter.setAcc_term(rcb_acc_term.getValue());
		refreshModel(_startPageNumber);
		refreshFilterComboBoxes(_startPageNumber);
	}

	public void onChange$rcb_accnt_ccy() {
		filter.setAccnt_ccy(rcb_accnt_ccy.getValue());
		refreshModel(_startPageNumber);
		refreshFilterComboBoxes(_startPageNumber);
	}

	public void onChange$rcb_tran_ccy() {
		filter.setTran_ccy(rcb_tran_ccy.getValue());
		refreshModel(_startPageNumber);
		refreshFilterComboBoxes(_startPageNumber);
	}

	public void onChange$rcb_deb_cred() {
		filter.setDeb_cred(rcb_deb_cred.getValue());
		refreshModel(_startPageNumber);
		refreshFilterComboBoxes(_startPageNumber);
	}

	public void onChange$rcb_country() {
		filter.setCountry(rcb_country.getValue());
		refreshModel(_startPageNumber);
		refreshFilterComboBoxes(_startPageNumber);
	}

	public void onChange$rcb_in_file() {
		filter.setIn_file(rcb_in_file.getValue());
		refreshModel(_startPageNumber);
		refreshFilterComboBoxes(_startPageNumber);
	}

	public void onChange$rcb_tr_type2_expt() {
		filter.setTr_type2_expt(rcb_tr_type2_expt.getValue());
		refreshModel(_startPageNumber);
		refreshFilterComboBoxes(_startPageNumber);
	}

	public void onChange$rcb_msc() {
		filter.setMsc(rcb_msc.getValue());
		refreshModel(_startPageNumber);
		refreshFilterComboBoxes(_startPageNumber);
	}

	// Omitted...
	public HumoOperation getCurrent() {
		return current;
	}

	public void setCurrent(HumoOperation current) {
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
		if (frm.isVisible()) {
			frm.setVisible(false);
			grd.setVisible(true);
			btn_back.setImage("/images/file.png");
			btn_back.setLabel(Labels.getLabel("back"));
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
		SelectEvent evt = new SelectEvent("onSelect", dataGrid,
				dataGrid.getSelectedItems());
		Events.sendEvent(evt);
	}

	public void onClick$btn_add() {
		//current=new HumoOperation();
		onDoubleClick$dataGrid$grd();
		frmgrd.setVisible(false);
		CheckNull.clearForm(addgrd);
		addgrd.setVisible(true);
		fgrd.setVisible(false);
		btn_save.setAttribute("rid", "");
		btn_save.setDisabled(false);
	}

	public void onClick$btn_edit() {
		onDoubleClick$dataGrid$grd();
		frmgrd.setVisible(false);
		//polyalarni qiymatini currentdan quyib chiqishim kerak
		//todo
		aid.setValue(current.getId());
		aterm_type.setValue(current.getTerm_type());
		aoper.setValue(current.getOper());
		acard_branch.setValue(current.getCard_branch());
		aterm_branch.setValue(current.getTerm_branch());
		atr_type_expt.setValue(current.getTr_type_expt());
		atr_type_b.setValue(current.getTr_type_b());
		amcc.setValue(current.getMcc());
		akomis.setValue(current.getKomis());
		aterminal.setValue(current.getTerminal());
		aacc_term.setValue(current.getAcc_term());
		aaccnt_ccy.setValue(current.getAccnt_ccy());
		atran_ccy.setValue(current.getTran_ccy());
		adeb_cred.setValue(current.getDeb_cred());
		acountry.setValue(current.getCountry());
		ain_file.setValue(current.getIn_file());
		atr_type2_expt.setValue(current.getTr_type2_expt());
		amsc.setValue(current.getMsc());
		addgrd.setVisible(true);
		fgrd.setVisible(false);
		btn_save.setAttribute("rid", current.getRid());
		btn_save.setDisabled(false);
	}

	public void onClick$btn_search() {
		onDoubleClick$dataGrid$grd();
		frmgrd.setVisible(false);
		addgrd.setVisible(false);
		fgrd.setVisible(true);
	}

	public void onClick$btn_save() {
		try {
			if (addgrd.isVisible()) {
				
				String rowid = (String) btn_save.getAttribute("rid");
				if (rowid==null || rowid.equals(""))
				{
					//insert uchun 
					HumoOperationService.create(new HumoOperation(
					aid.getValue(), aterm_type.getValue(), aoper.getValue(),
							acard_branch.getValue(), aterm_branch.getValue(),
							atr_type_expt.getValue(), atr_type_b.getValue(), amcc
									.getValue(), akomis.getValue(), aterminal
									.getValue(), aacc_term.getValue(), aaccnt_ccy
									.getValue(), atran_ccy.getValue(), adeb_cred
									.getValue(), acountry.getValue(), ain_file
									.getValue(), atr_type2_expt.getValue(), amsc
									.getValue(), ""));
				} else
				{
					//update uchun	
					HumoOperationService.update(new HumoOperation(
					aid.getValue(), aterm_type.getValue(), aoper.getValue(),
							acard_branch.getValue(), aterm_branch.getValue(),
							atr_type_expt.getValue(), atr_type_b.getValue(), amcc
									.getValue(), akomis.getValue(), aterminal
									.getValue(), aacc_term.getValue(), aaccnt_ccy
									.getValue(), atran_ccy.getValue(), adeb_cred
									.getValue(), acountry.getValue(), ain_file
									.getValue(), atr_type2_expt.getValue(), amsc
									.getValue(), rowid));
				}
				CheckNull.clearForm(addgrd);
				frmgrd.setVisible(true);
				addgrd.setVisible(false);
				fgrd.setVisible(false);
				btn_save.setDisabled(true);
				// alert("Добавлено");
				onClick$btn_back();
				refreshModel(_startPageNumber);
				SelectEvent evt = new SelectEvent("onSelect", dataGrid,
						dataGrid.getSelectedItems());
				Events.sendEvent(evt);


			} else if (fgrd.isVisible()) {
				filter = new HumoOperationFilter();

				filter.setId(fid.getValue());
				filter.setTerm_type(fterm_type.getValue());
				filter.setOper(foper.getValue());
				filter.setCard_branch(fcard_branch.getValue());
				filter.setTerm_branch(fterm_branch.getValue());
				filter.setTr_type_expt(ftr_type_expt.getValue());
				filter.setTr_type_b(ftr_type_b.getValue());
				filter.setMcc(fmcc.getValue());
				filter.setKomis(fkomis.getValue());
				filter.setTerminal(fterminal.getValue());
				filter.setAcc_term(facc_term.getValue());
				filter.setAccnt_ccy(faccnt_ccy.getValue());
				filter.setTran_ccy(ftran_ccy.getValue());
				filter.setDeb_cred(fdeb_cred.getValue());
				filter.setCountry(fcountry.getValue());

				onClick$btn_back();
				refreshModel(_startPageNumber);
				// refreshFilterComboBoxes(_startPageNumber);
				SelectEvent evt = new SelectEvent("onSelect", dataGrid,
						dataGrid.getSelectedItems());
				Events.sendEvent(evt);

			} else {

				current.setId(id.getValue());
				current.setTerm_type(term_type.getValue());
				current.setOper(oper.getValue());
				current.setCard_branch(card_branch.getValue());
				current.setTerm_branch(term_branch.getValue());
				current.setTr_type_expt(tr_type_expt.getValue());
				current.setTr_type_b(tr_type_b.getValue());
				current.setMcc(mcc.getValue());
				current.setKomis(komis.getValue());
				current.setTerminal(terminal.getValue());
				current.setAcc_term(acc_term.getValue());
				current.setAccnt_ccy(accnt_ccy.getValue());
				current.setTran_ccy(tran_ccy.getValue());
				current.setDeb_cred(deb_cred.getValue());
				current.setCountry(country.getValue());
				HumoOperationService.update(current);

				onClick$btn_back();
				refreshModel(_startPageNumber);
				SelectEvent evt = new SelectEvent("onSelect", dataGrid,
						dataGrid.getSelectedItems());
				Events.sendEvent(evt);
				// refreshFilterComboBoxes(_startPageNumber);

			}
			// onClick$btn_back();
			// refreshModel(_startPageNumber);
			// SelectEvent evt = new SelectEvent("onSelect", dataGrid,
			// dataGrid.getSelectedItems());
			// Events.sendEvent(evt);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void onSelect$dataGrid$grd() {
		return;
	}

	public void onClick$btn_cancel() {
		if (fgrd.isVisible()) {
			filter = new HumoOperationFilter();
		}
		onClick$btn_back();
		frmgrd.setVisible(true);
		addgrd.setVisible(false);
		fgrd.setVisible(false);
		CheckNull.clearForm(addgrd);
		CheckNull.clearForm(fgrd);
		refreshModel(_startPageNumber);
		// refreshFilterComboBoxes(_startPageNumber);
	}

	public void onClick$btn_tfilter() {
		// filter.setTerm_type(fterm_type.getValue());
		// filter.setOper(foper.getValue());
		filter.setCard_branch(tcard_branch.getValue());
		filter.setTerm_branch(tterm_branch.getValue());
		filter.setFiletype(ra1.isChecked() ? "B" : "EXPT");
		refreshModel(_startPageNumber);
		SelectEvent evt = new SelectEvent("onSelect", dataGrid,
				dataGrid.getSelectedItems());
		Events.sendEvent(evt);
		// refreshFilterComboBoxes(_startPageNumber);
	}

	public void onClick$btn_cancel_filter() {
		filter = new HumoOperationFilter();

		refreshModel(_startPageNumber);
		SelectEvent evt = new SelectEvent("onSelect", dataGrid,
				dataGrid.getSelectedItems());
		Events.sendEvent(evt);
		refreshFilterComboBoxes(_startPageNumber);

		rcb_operation_id.setValue(null);
		rcb_descripption.setValue(null);
		rcb_dt.setValue(null);
		rcb_kt.setValue(null);
		rcb_term_type.setValue(null);
		rcb_oper.setValue(null);
		rcb_card_branch.setValue(null);
		rcb_term_branch.setValue(null);
		rcb_tr_type_expt.setValue(null);
		rcb_tr_type_b.setValue(null);
		rcb_mcc.setValue(null);
		rcb_komis.setValue(null);
		rcb_terminal.setValue(null);
		rcb_acc_term.setValue(null);
		rcb_accnt_ccy.setValue(null);
		rcb_tran_ccy.setValue(null);
		rcb_deb_cred.setValue(null);
		rcb_country.setValue(null);
		rcb_in_file.setValue(null);
		rcb_tr_type2_expt.setValue(null);
		rcb_msc.setValue(null);
	}

	public void onClick$btn_excel() {
		to_excel_();
	}

	public void to_excel_() {

		List<HumoOperation> list = HumoOperationService.getHumoOperationsFl(
				_startPageNumber, _pageSize, filter);

		Workbook workbook = new HSSFWorkbook();
		Sheet listSheet = workbook.createSheet("list_1");
		Row row = null;
		CellStyle style;
		style = workbook.createCellStyle();
		style.setDataFormat((short) 0x4); // built-in number format
		Cell cell;
		Double vSumma = 0D;
		int rowIndex = 1;
		int cellIndex = 0;
		/*
		 * row = listSheet.createRow(rowIndex++);
		 * row.createCell(0).setCellValue("МФО:00444"); row =
		 * listSheet.createRow(rowIndex++);
		 * row.createCell(0).setCellValue("Бош офис"); rowIndex++; row =
		 * listSheet.createRow(rowIndex++);
		 * row.createCell(0).setCellValue("САЛЬДОВАЯ ВЕДОМОСТЬ"); row =
		 * listSheet.createRow(rowIndex++);
		 * row.createCell(0).setCellValue("за "+
		 * df.format(reportWindow$in_date.getValue())); rowIndex++; row =
		 * listSheet.createRow(rowIndex++);
		 * row.createCell(0).setCellValue("Ед.Изм.:");
		 */

		row = listSheet.createRow(rowIndex++);
		cellIndex = 0;
		row.createCell(cellIndex).setCellValue("Operation_id");
		cellIndex++;
		row.createCell(cellIndex).setCellValue("Descripption");
		cellIndex++;
		row.createCell(cellIndex).setCellValue("Dt");
		cellIndex++;
		row.createCell(cellIndex).setCellValue("Kt");
		cellIndex++;
		row.createCell(cellIndex).setCellValue("Term_type");
		cellIndex++;
		row.createCell(cellIndex).setCellValue("Oper");
		cellIndex++;
		row.createCell(cellIndex).setCellValue("Card_branch");
		cellIndex++;
		row.createCell(cellIndex).setCellValue("Term_branch");
		cellIndex++;
		row.createCell(cellIndex).setCellValue("Tr_type_expt");
		cellIndex++;
		row.createCell(cellIndex).setCellValue("Tr_type_b");
		cellIndex++;
		row.createCell(cellIndex).setCellValue("Mcc");
		cellIndex++;
		row.createCell(cellIndex).setCellValue("Komis");
		cellIndex++;
		row.createCell(cellIndex).setCellValue("Terminal");
		cellIndex++;
		row.createCell(cellIndex).setCellValue("Acc_term");
		cellIndex++;
		row.createCell(cellIndex).setCellValue("Accnt_ccy");
		cellIndex++;
		row.createCell(cellIndex).setCellValue("Tran_ccy");
		cellIndex++;
		row.createCell(cellIndex).setCellValue("Deb_cred");
		cellIndex++;
		row.createCell(cellIndex).setCellValue("Country");
		cellIndex++;
		row.createCell(cellIndex).setCellValue("In_file");
		cellIndex++;
		row.createCell(cellIndex).setCellValue("Tr_type2_expt");
		cellIndex++;
		row.createCell(cellIndex).setCellValue("Msc");

		rowIndex = 2;
		/*
		 * row = listSheet.createRow(rowIndex++); cellIndex = 0;
		 * row.createCell(cellIndex++).setCellValue("СЧЕТ");
		 * row.createCell(cellIndex++).setCellValue("Номер карты");
		 * row.createCell(cellIndex++).setCellValue("Сумма овердарфта");
		 * row.createCell(cellIndex++).setCellValue("Филиал выпускающий карту");
		 */

		for (int i = 0; i < list.size(); i++) {
			row = listSheet.createRow(rowIndex++);
			cellIndex = 0;
			row.createCell(cellIndex).setCellValue(
					list.get(i).getOperation_id());
			cellIndex++;
			row.createCell(cellIndex).setCellValue(
					list.get(i).getDescripption());
			cellIndex++;
			row.createCell(cellIndex).setCellValue(list.get(i).getDt());
			cellIndex++;
			row.createCell(cellIndex).setCellValue(list.get(i).getKt());
			cellIndex++;
			row.createCell(cellIndex).setCellValue(list.get(i).getTerm_type());
			cellIndex++;
			row.createCell(cellIndex).setCellValue(list.get(i).getOper());
			cellIndex++;
			row.createCell(cellIndex)
					.setCellValue(list.get(i).getCard_branch());
			cellIndex++;
			row.createCell(cellIndex)
					.setCellValue(list.get(i).getTerm_branch());
			cellIndex++;
			row.createCell(cellIndex).setCellValue(
					list.get(i).getTr_type_expt());
			cellIndex++;
			row.createCell(cellIndex).setCellValue(list.get(i).getTr_type_b());
			cellIndex++;
			row.createCell(cellIndex).setCellValue(list.get(i).getMcc());
			cellIndex++;
			row.createCell(cellIndex).setCellValue(list.get(i).getKomis());
			cellIndex++;
			row.createCell(cellIndex).setCellValue(list.get(i).getTerminal());
			cellIndex++;
			row.createCell(cellIndex).setCellValue(list.get(i).getAcc_term());
			cellIndex++;
			row.createCell(cellIndex).setCellValue(list.get(i).getAccnt_ccy());
			cellIndex++;
			row.createCell(cellIndex).setCellValue(list.get(i).getTran_ccy());
			cellIndex++;
			row.createCell(cellIndex).setCellValue(list.get(i).getDeb_cred());
			cellIndex++;
			row.createCell(cellIndex).setCellValue(list.get(i).getCountry());
			cellIndex++;
			row.createCell(cellIndex).setCellValue(list.get(i).getIn_file());
			cellIndex++;
			row.createCell(cellIndex).setCellValue(
					list.get(i).getTr_type2_expt());
			cellIndex++;
			row.createCell(cellIndex).setCellValue(list.get(i).getMsc());

			/*
			 * cell = row.createCell(cellIndex); try {
			 * cell.setCellValue(Double.valueOf(list.get(i).getAccnt_amt()));
			 * vSumma=vSumma+Double.valueOf(list.get(i).getAccnt_amt()); } catch
			 * (Exception e) { try {
			 * cell.setCellValue(Double.valueOf(list.get(i).getAccnt_amt()));
			 * vSumma=vSumma+Double.valueOf(list.get(i).getAccnt_amt()); } catch
			 * (Exception e1) {
			 * cell.setCellValue(Double.valueOf(list.get(i).getAccnt_amt()));
			 * vSumma=vSumma+Double.valueOf(list.get(i).getAccnt_amt()); } }
			 */
			// cell.setCellStyle(style);

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
			AMedia amedia = new AMedia("humo_oper_type.xls", "xls",
					"application/file", baos.toByteArray());
			Filedownload.save(amedia);
			baos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onSelect$mainTabbox(SelectEvent event)
			throws InterruptedException {
		// Tab component = (Tab) event.getTarget();
		Tab selectedTab = (Tab) event.getReference();
		if (selectedTab.getId().equalsIgnoreCase("templateTab")) {

			// internalControlInclude.setVisible(true);
			// internalControlInclude.setSrc(null);
			// internalControlInclude.setSrc(
			// "clientaddio.zul?branch=" + composer.getCustomer().getBranch() +
			// "&client_id=" + composer.getCustomer().getId() +
			// "&code_subject=P&alias=" + sessionAttributes.getSchema());

			id.setModel(new ListModelList(HumoOperationService
					.getOperType(alias)));
			term_type.setModel(new ListModelList(HumoOperationService
					.getTerminalKind(alias)));
			card_branch.setModel(new ListModelList(HumoOperationService
					.getBranchType(alias)));
			term_branch.setModel(new ListModelList(HumoOperationService
					.getBranchType(alias)));
			mcc.setModel(new ListModelList(HumoOperationService
					.getMccCode(alias)));
			oper.setModel(new ListModelList(HumoOperationService
					.getTermTorg(alias)));
			tcard_branch.setModel(new ListModelList(HumoOperationService
					.getBranchType(alias)));
			tterm_branch.setModel(new ListModelList(HumoOperationService
					.getBranchType(alias)));

			hOperType = HumoOperationService.getHOperType(alias);
			hTerminalKind = HumoOperationService.getHTerminalKind(alias);
			hTermTorg = HumoOperationService.getHTermTorg(alias);

			refreshModel(_startPageNumber);
			refreshFilterComboBoxes(_startPageNumber);

		} else if (selectedTab.getId().equalsIgnoreCase("timerTab")) {
			// coirmDiv.getChildren().clear();
			// coirmWnd =(Window)
			// Executions.createComponents("TietoVisa_trpay.zul", coirmDiv,
			// null);
			// coirmWnd.setVisible(true);
			List<TimeTable> timeList = HumoOperationService
					.getTimeTableList(alias);
			timeGrid.setModel(new BindingListModelList(timeList, true));
		}
	}

	public void setCurrentTimeItem(TimeTable currentTimeItem) {
		this.currentTimeItem = currentTimeItem;
	}

	public TimeTable getCurrentTimeItem() {
		return currentTimeItem;
	}

	public void onClick$btn_delete() {

		if (CheckNull.isEmpty(txb_file_id.getValue())) {
			alert("Введите ИД файла");
			txb_file_id.setFocus(true);
			return;
		}

		try {
			long value = Long.parseLong(txb_file_id.getValue()); 
																
																
		} catch (NumberFormatException e) {
			// System.out.println("Error: Invalid number format in string.");
			alert("Неправильное число в поле ИД файла");
			txb_file_id.setFocus(true);
			return;
		}
		if (!chb_one.isChecked() && !chb_two.isChecked()
				&& !chb_three.isChecked()) {
			alert("Не выбрано тип обработки файла");
			return;
		}
		Res res = null;
		String s = "";
		if (chb_one.isChecked()) {
			res = HumoOperationService.deleteHUMO_PROCESSING_CURR_FILES("1",
					txb_file_id.getValue());
			if (res.getCode() == 0) {
				s = "Первичная обработка - Удалено!";
			} else {
				s = "Первичная обработка - Ошибка : " + res.getName();
			}
		}
		if (chb_two.isChecked()) {
			res = HumoOperationService.deleteHUMO_PROCESSING_CURR_FILES("2",
					txb_file_id.getValue());
			if (res.getCode() == 0) {
				s = s + "\n" + "Группировка - Удалено!";
			} else {
				s = s + "\n" + "Группировка - Ошибка : " + res.getName();
			}
		}
		if (chb_three.isChecked()) {
			res = HumoOperationService.deleteHUMO_PROCESSING_CURR_FILES("3",
					txb_file_id.getValue());
			if (res.getCode() == 0) {
				s = s + "\n" + "Баланс - Удалено!";
			} else {
				s = s + "\n" + "Баланс - Ошибка : " + res.getName();
			}
		}
		alert(s);
	}

	public void onClick$btn_delete_clir() {

		if (CheckNull.isEmpty(txb_file_id_clir.getValue())) {
			alert("Введите ИД файла");
			txb_file_id_clir.setFocus(true);
			return;
		}
		try {
			long value = Long.parseLong(txb_file_id_clir.getValue()); 
		} catch (NumberFormatException e) {
			alert("Неправильное число в поле ИД файла");
			txb_file_id_clir.setFocus(true);
			return;
		}

		Res res = null;
		res = HumoOperationService.deleteHUMO_FILE_CLEARING(txb_file_id_clir.getValue());
		if (res.getCode() == 0) {
			alert("Удалено!");
		} else {
			alert(res.getName());
		}

	}

}
