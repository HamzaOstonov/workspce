package com.is.nibbd_liquidation;

import com.is.ConnectionPool;


import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.RefData;
import com.is.utils.RefDataService;
import com.is.utils.Res;

import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.zkoss.util.media.AMedia;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Filedownload;
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
import org.zkoss.zul.Window;
import org.zkoss.zul.event.PagingEvent;

public class LiquidationVC extends GenericForwardComposer {
	private Window reportWindow, closeClWindow;

	private Listbox dataGrid, dataGridDtl;
	private Div grd;
	private Div filterdiv;
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
	private Toolbarbutton btn_run;
	private Toolbarbutton btn_del;
	private Toolbarbutton btn_del_all;
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
	private Textbox fid_transh_purp;
	private Textbox faccount, fbranch, fid_client, finn, fname;
	private Datebox fcur_date;
	private Textbox reportWindow$rep_mfo;
	private Datebox reportWindow$datefrom;
	private Datebox reportWindow$dateto;
    private Label closeClWindow$closeClientLabel;
    private RefCBox closeClWindow$close_type;
    private Textbox closeClWindow$close_doc_n;
    private Datebox closeClWindow$close_doc_d;
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
	private String alias, ses_branch, un, pw, branch;

	private Toolbarbutton bt = null;
	private int curroperation_id;
	private Label operation_id, operation_id1;

	// private HashMap<String, String> cacheacc = new HashMap();
	PagingListModel model = null;
	ListModelList lmodel = null;
	private AnnotateDataBinder binder;
	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	public FilterRecord filter = new FilterRecord();
	private GeneralRecord current = new GeneralRecord();

	public LiquidationVC() {
		super('$', false, false);
	}

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);

		this.binder = new AnnotateDataBinder(comp);
		this.binder.bindBean("current", this.current);
		this.binder.bindBean("filter", this.filter);
		this.binder.loadAll();
		// String[] parameter = (String[]) this.param.get("ht");
		// String[] rows = (String[]) this.param.get("rows");
		// String[] lines = (String[]) this.param.get("lines");

		this.alias = ((String) this.session.getAttribute("alias"));
		// if (parameter != null) {
		// this._pageSize = (Integer.parseInt(parameter[0]) / 36);
		// this.dataGrid.setRows(Integer.parseInt(parameter[0]) / 36);
		// }
		// if (rows != null) {
		// this._pageSize = (Integer.parseInt(rows[0]));
		// this.dataGrid.setRows(Integer.parseInt(rows[0]) );
		// alert(rows[0]);
		// }

		un = (String) session.getAttribute("un");
		pw = (String) session.getAttribute("pwd");
        branch = (String) this.session.getAttribute("branch");
        
		this.dataGrid.setItemRenderer(new ListitemRenderer() {
			public void render(Listitem row, Object data) throws Exception {
				GeneralRecord rec = (GeneralRecord) data;
				// Listcell lc = new Listcell();

				row.setValue(rec);

				// row.appendChild(new Listcell(StringUtils.secureNull(rec
				// .getId_fr_file())));
				row.appendChild(new Listcell(secureNull(rec
						.getName())));
				row.appendChild(new Listcell(secureNull(rec
						.getDate_time())));

				// row.appendChild(new Listcell(StringUtils.secureNull(rec
				// .getId_fl())));
				// row.appendChild(new Listcell(StringUtils.secureNull(rec
				// .getFr_file_id())));
				row.appendChild(new Listcell(secureNull(rec
						.getBranch())));

				// row.appendChild(new Listcell(StringUtils.secureNull(rec
				// .getId_line())));
				// row.appendChild(new Listcell(StringUtils.secureNull(rec
				// .getNotify_file_id())));
				// row.appendChild(new Listcell(StringUtils.secureNull(rec
				// .getLine_id())));
				row.appendChild(new Listcell(secureNull(rec
						.getCode_notification()+"-"+ getNotifyText(rec.getCode_notification()) )));
				row.appendChild(new Listcell(secureNull(rec
						.getText_notification())));
				row.appendChild(new Listcell(secureNull(rec
						.getTime_notification())));

				// row.appendChild(new Listcell(StringUtils.secureNull(rec
				// .getId_dtl())));
				// row.appendChild(new Listcell(StringUtils.secureNull(rec
				// .getNotify_line_id())));
				row.appendChild(new Listcell(secureNull(rec
						.getId_notification())));
				row.appendChild(new Listcell(secureNull(rec
						.getDate_reason())));
				row.appendChild(new Listcell(secureNull(rec
						.getReason())));
				row.appendChild(new Listcell(secureNull(rec
						.getInn_liquidator_jur())));
				row.appendChild(new Listcell(secureNull(rec
						.getInn_subject())));
				row.appendChild(new Listcell(secureNull(rec
						.getCode_country())));
				row.appendChild(new Listcell(secureNull(rec
						.getPinfl_liquidator_phys())));
				row.appendChild(new Listcell(secureNull(rec
						.getPasport_ser_num())));
				row.appendChild(new Listcell(secureNull(rec
						.getSsilka())));
				row.appendChild(new Listcell(secureNull(rec
						.getFio())));

			}
		});

		//2024.05.14
		fbranch.setReadonly(true);
		fbranch.setDisabled(true);
		filter.setBranch(branch);
		//filter.setBranch("00929");
		
		
		reportWindow$rep_mfo.setReadonly(true);
		reportWindow$rep_mfo.setDisabled(true);
		reportWindow$rep_mfo.setValue(branch);
		
		if (filter.getDate_time() == null)
			filter.setDate_time(DateUtils.addMonths(new Date(), -1));
		if (filter.getDate_time1() == null)
			filter.setDate_time1(new Date());
		if (filter.getBranch() == null)
			filter.setBranch("%");
		refreshModel(this._startPageNumber);
	}

	private void refreshModel(int activePage) {
		this.trtemplatePaging.setPageSize(this._pageSize);
		this.model = new PagingListModel(activePage, this._pageSize,
				this.filter, this.alias);

		if (this._needsTotalSizeUpdate) {
			this._totalSize = this.model.getTotalSize(this.filter, this.alias);
		}

		this.trtemplatePaging.setTotalSize(this._totalSize);

		this.dataGrid.setModel(this.model);
		if (this.model.getSize() > 0) {
			this.setCurrent(((GeneralRecord) this.model.getElementAt(0)));
			sendSelEvt();
		}
	}

	public void onClick$btn_report() {
		if (reportWindow$datefrom.getValue() == null)
			reportWindow$datefrom.setValue(new Date());
		if (reportWindow$dateto.getValue() == null)
			reportWindow$dateto.setValue(new Date());

		this.reportWindow.setVisible(true);

	}

	public void onClick$btn_close_cl() {
		if (current==null || current.getInn_subject()==null) {
			alert("Не выбран клиент для закрытия!");
			return;
		}
		//klient yopiqmi yuqmi tekshiramiz. yopiq bulmasa xabar beramiz.
		Customer cc = LiqService.getClientInfo(current.getBranch(), current.getInn_subject(), alias);
		//Customer cc = LiqService.getClientInfo("00394", "200846040", alias);
		//Customer cc = LiqService.getClientInfo("00394", "203096482", alias);
		
		if (cc==null || cc.getName()==null) {
			alert("Не найден клиент по ИНН!");
			return;
		}
		
		if (cc.getState().equals("3")) {
			alert("Статус клиента (ИНН="+ current.getInn_subject()+ ", уникалный код="+cc.getId()+") закрыт. Процедура закрытия прерван!");
			return;
		} 
		
		closeClWindow.setAttribute("client", cc);
		//closeClWindow.setTitle(cc.getName());
		closeClWindow$closeClientLabel.setValue(cc.getName());

		if (closeClWindow$close_type.getItems().size()==0)
			closeClWindow$close_type.setModel((new ListModelList(LiqService.getClose_types(alias))));
		
		
		this.closeClWindow.setVisible(true);

	}
	

	public void onClick$btnCloseClient$closeClWindow() {
		
		if (closeClWindow$close_type.getValue()==null || closeClWindow$close_type.getValue().trim().equals("")  ) {
			alert("Ошибка: Вид закрытия клиента - не выбрано");
			return;
		}
      
		if (closeClWindow$close_doc_n.getValue()==null || closeClWindow$close_doc_n.getValue().trim().equals("")) {
			alert("Ошибка: Поле номер документа основания для закрытия - пусто");
			return;
		}
		
		if (closeClWindow$close_doc_d.getValue()==null) {
			alert("Ошибка: Поле дата документа основания для закрытия - пусто");
			return;
		} 
		
		Customer cc = (Customer)closeClWindow.getAttribute("client"); 
      
		
		//alert(cc.getName());
		//дуакшин киламиз
		//final String un, final String pw, final String branch, final String id, String close_type, String closed_doc_n, String closed_doc_d, 		final String alias
      
		Res res = LiqService.doAction_close(un, pw, branch, cc.getId(), closeClWindow$close_type.getValue(), closeClWindow$close_doc_n.getValue(), df.format(closeClWindow$close_doc_d.getValue()), alias);
        if (res.getCode()==0) {
        	alert("Клиент закрылся успешно!");
        	closeClWindow.setVisible(false);	
        } else {
        	alert("Ошибка при закрытии клиента: \n"+res.getName());
        	
        } 

	}

	
	public void onClick$btnReportExcel$reportWindow() {
		to_excel_();
		reportWindow.setVisible(false);
		// send_soap_request();
	}

	public void to_excel_() {

		List<GeneralRecord> list = LiqService.getReport1(
				reportWindow$datefrom.getValue(),
				reportWindow$dateto.getValue(),
				reportWindow$rep_mfo.getValue() != null ? reportWindow$rep_mfo
						.getValue() : "%");

		Workbook workbook = new HSSFWorkbook();
		Sheet listSheet = workbook.createSheet("list_1");
		Row row = null;
		CellStyle style;
		style = workbook.createCellStyle();
		style.setDataFormat((short) 0x4); // built-in number format
		// Cell cell;
		// Double vSumma = 0D;
		int rowIndex = 0;
		int cellIndex = 0;
		// row = listSheet.createRow(rowIndex++);
		// row.createCell(0).setCellValue("МФО:00444");
		// row = listSheet.createRow(rowIndex++);
		// row.createCell(0).setCellValue("Бош офис");
		// rowIndex++;
		row = listSheet.createRow(rowIndex++);
		row.createCell(0).setCellValue("Отчетность");
		rowIndex++;
		row = listSheet.createRow(rowIndex++);
		row.createCell(0).setCellValue(
				"c : " + df.format(reportWindow$datefrom.getValue()));
		row = listSheet.createRow(rowIndex++);
		row.createCell(0).setCellValue(
				"по : " + df.format(reportWindow$dateto.getValue()));
		row = listSheet.createRow(rowIndex++);
		row.createCell(0).setCellValue(
				"МФО : " + reportWindow$rep_mfo.getValue());

		rowIndex++;
		// rowIndex = 9;
		row = listSheet.createRow(rowIndex++);
		cellIndex = 0;

		/*
		 * row.createCell(cellIndex++).setCellValue("ID_FR_FILE");
		 * row.createCell(cellIndex++).setCellValue("NAME");
		 * row.createCell(cellIndex++).setCellValue("DATE_TIME");
		 * 
		 * row.createCell(cellIndex++).setCellValue("ID_FL");
		 * row.createCell(cellIndex++).setCellValue("FR_FILE_ID");
		 * row.createCell(cellIndex++).setCellValue("BRANCH");
		 * 
		 * row.createCell(cellIndex++).setCellValue("ID_LINE");
		 * row.createCell(cellIndex++).setCellValue("NOTIFY_FILE_ID");
		 * row.createCell(cellIndex++).setCellValue("LINE_ID");
		 * row.createCell(cellIndex++).setCellValue("CODE_NOTIFICATION");
		 * row.createCell(cellIndex++).setCellValue("TEXT_NOTIFICATION");
		 * row.createCell(cellIndex++).setCellValue("TIME_NOTIFICATION");
		 * 
		 * row.createCell(cellIndex++).setCellValue("ID_DTL");
		 * row.createCell(cellIndex++).setCellValue("NOTIFY_LINE_ID");
		 * row.createCell(cellIndex++).setCellValue("ID_NOTIFICATION");
		 * row.createCell(cellIndex++).setCellValue("DATE_REASON");
		 * row.createCell(cellIndex++).setCellValue("REASON");
		 * row.createCell(cellIndex++).setCellValue("INN_LIQUIDATOR_JUR");
		 * row.createCell(cellIndex++).setCellValue("INN_SUBJECT");
		 * row.createCell(cellIndex++).setCellValue("CODE_COUNTRY");
		 * row.createCell(cellIndex++).setCellValue("PINFL_LIQUIDATOR_PHYS");
		 * row.createCell(cellIndex++).setCellValue("PASPORT_SER_NUM");
		 * row.createCell(cellIndex++).setCellValue("SSILKA");
		 * row.createCell(cellIndex++).setCellValue("FIO");
		 */

		row.createCell(cellIndex++).setCellValue("Ид файла");
		row.createCell(cellIndex++).setCellValue("Имя файла");
		row.createCell(cellIndex++).setCellValue("Дата приема файла");

		row.createCell(cellIndex++).setCellValue("Ид файла уведомления");
		// row.createCell(cellIndex++).setCellValue("FR_FILE_ID");
		row.createCell(cellIndex++).setCellValue("Филиал");

		row.createCell(cellIndex++).setCellValue("Ид файла уведомления2");
		// row.createCell(cellIndex++).setCellValue("NOTIFY_FILE_ID");
		row.createCell(cellIndex++).setCellValue("Порядковый номер строки");
		row.createCell(cellIndex++).setCellValue("Код вида уведомления");
		row.createCell(cellIndex++).setCellValue("Текст уведомление");
		row.createCell(cellIndex++).setCellValue(
				"Время формирования уведомления");

		row.createCell(cellIndex++).setCellValue("Ид файла уведомления3");
		row.createCell(cellIndex++).setCellValue("NOTIFY_LINE_ID");
		row.createCell(cellIndex++)
				.setCellValue("Уникальный номер уведомления");
		row.createCell(cellIndex++).setCellValue("Дата основания");
		row.createCell(cellIndex++).setCellValue("Основание");
		row.createCell(cellIndex++).setCellValue(
				"ИНН ликвидатора юридического лица");
		row.createCell(cellIndex++).setCellValue("ИНН субъекта");
		row.createCell(cellIndex++).setCellValue("Код страны гражданства ");
		row.createCell(cellIndex++).setCellValue(
				"ПИНФЛ ликвидатора физического лица");
		row.createCell(cellIndex++).setCellValue("Серия и номер паспорта ");
		row.createCell(cellIndex++).setCellValue("Ссылка для файла решения");
		row.createCell(cellIndex++).setCellValue("Ф.И.О ликвидатора ");

		for (int i = 0; i < list.size(); i++) {
			row = listSheet.createRow(rowIndex++);
			cellIndex = 0;

			row.createCell(cellIndex++).setCellValue(
					list.get(i).getId_fr_file());
			row.createCell(cellIndex++).setCellValue(list.get(i).getName());
			row.createCell(cellIndex++)
					.setCellValue(list.get(i).getDate_time());

			row.createCell(cellIndex++).setCellValue(list.get(i).getId_fl());
			// row.createCell(cellIndex++).setCellValue(
			// list.get(i).getFr_file_id());
			row.createCell(cellIndex++).setCellValue(list.get(i).getBranch());

			row.createCell(cellIndex++).setCellValue(list.get(i).getId_line());
			// row.createCell(cellIndex++).setCellValue(
			// list.get(i).getNotify_file_id());
			row.createCell(cellIndex++).setCellValue(list.get(i).getLine_id());
			row.createCell(cellIndex++).setCellValue(
					list.get(i).getCode_notification()+"-"+getNotifyText(list.get(i).getCode_notification()));
			row.createCell(cellIndex++).setCellValue(
					list.get(i).getText_notification());
			row.createCell(cellIndex++).setCellValue(
					list.get(i).getTime_notification());

			row.createCell(cellIndex++).setCellValue(list.get(i).getId_dtl());
			row.createCell(cellIndex++).setCellValue(
					list.get(i).getNotify_line_id());
			row.createCell(cellIndex++).setCellValue(
					list.get(i).getId_notification());
			row.createCell(cellIndex++).setCellValue(
					list.get(i).getDate_reason());
			row.createCell(cellIndex++).setCellValue(list.get(i).getReason());
			row.createCell(cellIndex++).setCellValue(
					list.get(i).getInn_liquidator_jur());
			row.createCell(cellIndex++).setCellValue(
					list.get(i).getInn_subject());
			row.createCell(cellIndex++).setCellValue(
					list.get(i).getCode_country());
			row.createCell(cellIndex++).setCellValue(
					list.get(i).getPinfl_liquidator_phys());
			row.createCell(cellIndex++).setCellValue(
					list.get(i).getPasport_ser_num());
			row.createCell(cellIndex++).setCellValue(list.get(i).getSsilka());
			row.createCell(cellIndex++).setCellValue(list.get(i).getFio());

		}
		// itogo

		listSheet.autoSizeColumn(0);
		listSheet.autoSizeColumn(1);
		listSheet.autoSizeColumn(2);
		listSheet.autoSizeColumn(3);
		listSheet.autoSizeColumn(4);
		listSheet.autoSizeColumn(5);
		listSheet.autoSizeColumn(6);
		listSheet.autoSizeColumn(7);
		listSheet.autoSizeColumn(8);
		listSheet.autoSizeColumn(9);
		listSheet.autoSizeColumn(10);
		listSheet.autoSizeColumn(11);
		listSheet.autoSizeColumn(12);
		listSheet.autoSizeColumn(13);
		listSheet.autoSizeColumn(14);
		listSheet.autoSizeColumn(15);
		listSheet.autoSizeColumn(16);
		listSheet.autoSizeColumn(17);
		listSheet.autoSizeColumn(18);
		listSheet.autoSizeColumn(19);
		listSheet.autoSizeColumn(20);
		listSheet.autoSizeColumn(21);
		listSheet.autoSizeColumn(22);
		listSheet.autoSizeColumn(23);
		listSheet.autoSizeColumn(24);
		listSheet.autoSizeColumn(25);
		listSheet.autoSizeColumn(26);
		listSheet.autoSizeColumn(27);

		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			workbook.write(baos);
			AMedia amedia = new AMedia("nibbd_likvidation.xls", "xls",
					"application/file", baos.toByteArray());
			Filedownload.save(amedia);
			baos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getNotifyText(String typeNotify) {
		String v_res = "";
		try {
			List<RefData> tmp = LiqService.getTypesNotify();

			for (int i = 0; i < tmp.size(); i++) {
				if (tmp.get(i).getData() == typeNotify
						|| tmp.get(i).getData().equals(typeNotify)) {
					v_res = tmp.get(i).getLabel();
					break;
				}
			}
		} catch (Exception e) {
			com.is.LtLogger.getLogger()
					.error(com.is.utils.CheckNull.getPstr(e));

		}
		return v_res;
	}

	public void onDoubleClick$dataGrid$grd() {

	}

	public void onClick$btn_back() {
		/*
		 * if (this.frm.isVisible()) { this.frm.setVisible(false);
		 * this.grd.setVisible(true);
		 * this.btn_back.setImage("/images/file.png");
		 * this.btn_back.setLabel(Labels.getLabel("back")); } else {
		 * onDoubleClick$dataGrid$grd(); moperation_id.setDisabled(true); }
		 */

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
			this.dataGrid
					.setSelectedIndex(this.dataGrid.getSelectedIndex() - 1);
			sendSelEvt();
		}
	}

	public void onClick$btn_next() {
		if (this.dataGrid.getSelectedIndex() != this.model.getSize() - 1) {
			this.dataGrid
					.setSelectedIndex(this.dataGrid.getSelectedIndex() + 1);
			sendSelEvt();
		}
	}

	public void onPaging$trtemplatePaging(ForwardEvent event) {
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModel(_startPageNumber);
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
		SelectEvent evt = new SelectEvent("onSelect", this.dataGrid,
				this.dataGrid.getSelectedItems());
		Events.sendEvent(evt);
	}

	public void onClick$btn_search() {
		// onDoubleClick$dataGrid$grd();
		this.grd.setVisible(false);
		// this.frm.setVisible(false);
		this.filterdiv.setVisible(true);
		this.fgrd.setVisible(true);
	}

	public void onClick$btn_flt_apply() {
		try {
			refreshModel(0);

			this.filterdiv.setVisible(false);
			this.fgrd.setVisible(false);
			this.grd.setVisible(true);
		} catch (Exception e) {
			com.is.LtLogger.getLogger()
					.error(com.is.utils.CheckNull.getPstr(e));
		}
	}

	public void onClick$btn_flt_cancel() {
		this.filterdiv.setVisible(false);
		this.fgrd.setVisible(false);
		this.grd.setVisible(true);
	}

	public void onClick$btn_cancel() {

	}

	public void setCurrent(GeneralRecord current) {
		this.current = current;
	}

	public GeneralRecord getCurrent() {
		return current;
	}
	
    public static String secureNull(String inputString){
        if(inputString != null){
            return inputString;
        }else{
            return "";
        }
    }

    public static String secureNull(Object input){
        if(input != null){
            return input.toString();
        }else{
            return "";
        }
    }


}