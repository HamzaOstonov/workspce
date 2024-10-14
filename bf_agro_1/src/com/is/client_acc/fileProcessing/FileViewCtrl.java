package com.is.client_acc.fileProcessing;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zkplus.databind.BindingListModelList;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Timer;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;
import org.zkoss.zul.event.PagingEvent;
import com.is.file_reciever_srv.recievers.visa.onus.Onus_records_processing;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import com.is.ConnectionPool;
import com.is.ISLogger;
//import com.is.file_reciever_srv.recievers.EMPC.EXPT.EXPT_record;
//import com.is.file_reciever_srv.recievers.EMPC.EXPT.Expt_records_processing;
//import com.is.file_reciever_srv.recievers.EMPC.b_file.B_file_processing;

import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.RefDataService;
import com.is.utils.Res;

import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.util.resource.Labels;
import org.zkoss.zul.Button;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

import sun.security.action.GetLongAction;

public class FileViewCtrl extends GenericForwardComposer {

	private Listbox dataGrid, accDataGrid;
	private Listbox generalDataGrid;
	private Listbox fileRecords$fileRecordsListbox;
	private Div grd;
	private Div divFilterAccounts;
	private Div fileRecords$record;
	private Toolbarbutton btn_last;
	private Toolbarbutton btn_next;
	private Toolbarbutton btn_prev;
	private Toolbarbutton btn_first;
	private Toolbarbutton btn_back;
	private Toolbarbutton btn_show_acc_filter;
	private Button btnAccClearSearch, btnAddForClosing, btnAccSearch;

	private Paging filePaging, accPaging;
	private Window fileRecords;
	private Window bfileRecordFields;
	private Window fileRecordFields;
	private Window empcfilesbtransactionsmain;
	private Textbox searchId, asearchBranch, asearchDay, 
			asearchNumDoc, asearchPurpose;
	private Textbox searchFileName;
	private Datebox searchDate, asearchDateDoc;
	private Label fileRecordFields$id, bfileRecordFields$id;
	private Label fileRecordFields$empc_file_id;
	private Label fileRecordFields$state_id;
	private Label fileRecordFields$record_type;
	private Label fileRecordFields$line_number;
	private Label fileRecordFields$client;
	private Label fileRecordFields$card_acct;
	private Label fileRecordFields$accnt_ccy;
	private Label fileRecordFields$card;
	private Label fileRecordFields$slip_nr;
	private Label fileRecordFields$ref_number;
	private Label fileRecordFields$tran_date_time;
	private Label fileRecordFields$rec_date;
	private Label fileRecordFields$post_date;
	private Label fileRecordFields$deal_desc;
	private Label fileRecordFields$tran_type;
	private Label fileRecordFields$tranz_acct, bfileRecordFields$tranz_acct;
	private Label fileRecordFields$term_id;
	private Label fileRecordFields$iss_mfo;
	private Label fileRecordFields$product;
	private Label fileRecordFields$internal_no;
	private Label fileRecordFields$proc_id;
	private Label fileRecordFields$city;
	private Label fileRecordFields$country;
	private Label fileRecordFields$abvr_name;
	private Label fileRecordFields$merchant;
	private Label fileRecordFields$mcc_code;
	private Label fileRecordFields$terminal;
	private Label fileRecordFields$accnt_amt;
	private Label fileRecordFields$tran_amt;
	private Label fileRecordFields$tran_ccy;
	private Label fileRecordFields$deb_cred;
	private Listheader fileRecords$listheader_tran_amt;
	private Grid fileRecordFields$fieldsGrid;
	private String branch, un, pwd, alias;
	private int uid;
	private int _pageSize = 15;
	private int _startPageNumber = 0;
	private int _totalSize = 0;
	private boolean _needsTotalSizeUpdate = true;
	private int _pageSizeAcc = 10;
	private int _startPageNumberAcc = 0;
	private int _totalSizeAcc = 0;

	public FileFilter filter;
	PagingListModel model = null;
	PagingListModel modelAcc = null;
	ListModelList lmodel = null;
	private AnnotateDataBinder binder;
	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	DecimalFormat dcf;
	DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols(
			Locale.getDefault());

	private File current = new File();
	private EmpcExptRecord currentRecord = new EmpcExptRecord();
	private String fileDirectory;
	private Timer timer;
	private Textbox filemain$common;
	private Textbox filemain$success;
	private Textbox filemain$error;
	private Textbox filemain$fileId;
	private Textbox filemain$fileName;
	Long fileId = null;
	String fileName = null;
	private RefCBox humoFileId, asearchAccBal;

	private EventListener fileButtonHandler = new EventListener() {

		public void onEvent(Event event) throws Exception {
			Connection c = null;
			String err = null;
			try {
				fileId = (Long) event.getTarget().getAttribute("fileId");
				fileName = (String) event.getTarget().getAttribute("fileName");

				// ISLogger.getLogger().error("HUMO: un:"+un+" pwd:"+pwd
				// +" alias:"+alias);
				// System.out.println("HUMO: un:"+un+" pwd:"+pwd
				// +" alias:"+alias);
				c = ConnectionPool.getConnection(un, pwd, alias);

				// if (!FileService.isProcessingFile(c, fileId.toString())){
				err = FileService.HUMO_PROCESSING(c, fileId.toString());

				if (err == null) {
					// c.commit();
					// timer.start();
					alert("Обработка для файла " + fileName + " начался!");
					// alert("Проводка для файла " +fileName+" закончелся");

				}

				else {

					// c.rollback();
					// alert(err);
					// timer.start();
					alert("Обработка для файла " + fileName + " начался! ");

				}
				// }

				// else {
				//
				// alert("Файл "+fileName
				// +" (id: "+fileId.toString()+") уже запушен");
				// }

			} catch (Exception e) {
				e.printStackTrace();
				ISLogger.getLogger().error(CheckNull.getPstr(e));

			}

			finally {
				if (c != null)
					try {
						c.close();
					} catch (SQLException e) {
						ISLogger.getLogger().error(e);
					}

			}

			// // File file = null;
			// Listitem row = (Listitem)event.getTarget().getAttribute("row");
			// System.out.println("fileID  ==   " + fileId);
			//
			// if (FileService.getFileType(fileId)==1L){
			// //Eski
			// // B_file_processing bFileProcessing = new B_file_processing();
			// // Connection c = ConnectionPool.getConnection(un,pwd,alias);
			// // bFileProcessing.init(c);
			// // bFileProcessing.load_config_map(un,pwd,alias,c);
			// // bFileProcessing.load_branch(c);
			// //
			// // bFileProcessing.process_file(fileName,fileId,un,pwd,alias,c);
			// //
			// // ConnectionPool.close(c);
			// // bFileProcessing = null;
			//
			// //Yangi
			// B_file_processing bFileProcessing = new B_file_processing();
			// Connection c = ConnectionPool.getConnection(un,pwd,alias);
			// err=bFileProcessing.Processing(c, fileId.toString(), "2");
			// bFileProcessing = null;
			// ConnectionPool.close(c);
			// }
			// else{
			// //Eski
			// // Expt_records_processing processing = new
			// Expt_records_processing();
			// // Connection c = ConnectionPool.getConnection(un,pwd,alias);
			// // processing.init(c);
			// // processing.load_config_map(un,pwd,alias, c);
			// // processing.load_branch(c);
			// // processing.process_file(fileName,fileId,un,pwd,alias, c);
			// // ConnectionPool.close(c);
			// // processing=null;
			//
			//
			// //Yangi
			// Expt_records_processing processing = new
			// Expt_records_processing();
			// Connection c = ConnectionPool.getConnection(un,pwd,alias);
			// err=processing.Processing(c,fileId.toString(),"1");
			// processing=null;
			// ConnectionPool.close(c);
			// }
			// row.getChildren().clear();

			// file = FileService.getFile(fileId);
			// row.setValue(file);
			// row.appendChild(new Listcell(""));
			// refreshModel(_startPageNumber);

		}

	};

	private EventListener createOutFileButtonHandler = new EventListener() {

		public void onEvent(Event event) throws Exception {
			Connection c = null;
			String err = null;
			try {
				fileId = (Long) event.getTarget().getAttribute("fileId");
				fileName = (String) event.getTarget().getAttribute("fileName");

				// ISLogger.getLogger().error("HUMO: un:"+un+" pwd:"+pwd
				// +" alias:"+alias);
				// System.out.println("HUMO: un:"+un+" pwd:"+pwd
				// +" alias:"+alias);
				c = ConnectionPool.getConnection(un, pwd, alias);
				// if (!FileService.isProcessingFile(c, fileId.toString())){
				err = FileService.clientacc_prepare_file(c, fileId.toString());
				if (err == null) {
					c.commit();
					// timer.start();
					alert("Для файла "
							+ fileName
							+ " начался формирование выходной файл. Следите приложение file-receiver!");
					// alert("Проводка для файла " +fileName+" закончелся");
				} else {
					c.rollback();
					alert(err);
					// timer.start();
					// alert("Для файла " + fileName +
					// " начался формирование выходной файл. Следите приложение file-receiver!");
				}
				// }

				// else {
				//
				// alert("Файл "+fileName
				// +" (id: "+fileId.toString()+") уже запушен");
				// }

			} catch (Exception e) {
				e.printStackTrace();
				ISLogger.getLogger().error(CheckNull.getPstr(e));

			}

			finally {
				if (c != null)
					try {
						c.close();
					} catch (SQLException e) {
						ISLogger.getLogger().error(e);
					}
			}
		}
	};

	private EventListener fileButtonExcel = new EventListener() {

		public void onEvent(Event event) throws Exception {

			// org.apache.poi.ss.usermodel.Workbook book = new HSSFWorkbook();
			// org.apache.poi.ss.usermodel.Sheet sheet =
			// book.createSheet("Birthdays");
			//
			// // Нумерация начинается с нуля
			// org.apache.poi.ss.usermodel.Row row = sheet.createRow(0);
			//
			// // Мы запишем имя и дату в два столбца
			// // имя будет String, а дата рождения --- Date,
			// // формата dd.mm.yyyy
			// org.apache.poi.ss.usermodel.Cell name = row.createCell(0);
			// name.setCellValue("John");
			//
			// org.apache.poi.ss.usermodel.Cell birthdate = row.createCell(1);
			//
			// org.apache.poi.ss.usermodel.DataFormat format =
			// book.createDataFormat();
			// org.apache.poi.ss.usermodel.CellStyle dateStyle =
			// book.createCellStyle();
			// dateStyle.setDataFormat(format.getFormat("dd.mm.yyyy"));
			// birthdate.setCellStyle(dateStyle);
			//
			//
			// // Нумерация лет начинается с 1900-го
			// birthdate.setCellValue(new Date(110, 10, 10));
			//
			// // Меняем размер столбца
			// sheet.autoSizeColumn(1);
			//
			// // Записываем всё в файл
			//
			//
			// book.close();
			// timer=new Timer();
			// timer.setDelay(3000);
			// timer.setRepeats(true);
			// timer.setId("timer");
			timer.start();
			// timer.set

		}

	};
	private EventListener recordButtonHandler = new EventListener() {

		public void onEvent(Event event) throws Exception {
			Connection c = null;
			// EXPT_record record = null;
			EmpcExptRecord rec = null;
			Long recordId = (Long) event.getTarget().getAttribute("recordId");
			// record = FileService.getEXPT_record(recordId);
			Onus_records_processing processing = new Onus_records_processing();
			c = ConnectionPool.getConnection(un, pwd, alias);
			// processing.load_config_map(un,pwd,alias, c);
			// processing.load_branch(c);
			// Expt_records_processing.alter_session_init(c, branch);
			// processing.init(c);
			// processing.process_record(c,record,un,pwd,alias);
			processing.close();
			// Expt_records_processing.updateFileState(record.getEmpc_file_id());
			((Listitem) event.getTarget().getAttribute("item")).getChildren()
					.clear();
			// rec = FileService.getEmpcExptRecord(record.getId());
			((Listitem) event.getTarget().getAttribute("item")).setValue(rec);
			((Listitem) event.getTarget().getAttribute("item"))
					.appendChild(new Listcell(""));
			((Listitem) event.getTarget().getAttribute("item"))
					.setStyle("background-color:#99CC33; height:40px;");
		}
	};

	public FileViewCtrl() {
		super('$', false, false);
	}

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		this.binder = new AnnotateDataBinder(comp);
		this.binder.bindBean("current", this.current);
		this.binder.bindBean("currentRecord", this.currentRecord);
		
		String[] parameter = (String[]) this.param.get("ht");
		this.branch = (String) this.session.getAttribute("branch");
		un = (String) session.getAttribute("un");
		pwd = (String) session.getAttribute("pwd");
		alias = (String) session.getAttribute("alias");
		uid = (Integer) session.getAttribute("uid");
		this.fileDirectory = FileService
				.getTietoDirectory(Constants.ID_TIETO_DIRECTORY);
		if (parameter != null) {
			this._pageSize = Integer.parseInt(parameter[0]) / 44;
			this.dataGrid.setRows(Integer.parseInt(parameter[0]) / 44);
		}
		
		asearchBranch.setValue(branch);
		asearchAccBal.setModel((ListModel) new ListModelList(
				(Collection) FileService.getCodeBalBank(this.alias)));
		asearchAccBal.setSelecteditem("20206");
		this.dataGrid.setItemRenderer(new ListitemRenderer() {

			public void render(Listitem row, Object data) throws Exception {
				File pFile = (File) data;
				row.setValue(pFile);
				fillRow(row, pFile);
				long fileState = FileService.getFileState(pFile.getId());
				if (fileState == Constants.FILE_ACCEPTED
						|| fileState == Constants.FILE_PROCESSED_WITH_ERRORS) {
					Listcell listcell = new Listcell();
					Button button = new Button();
					button.setImage("/images/file.png");
					// button.setLabel(Labels.getLabel("file.fileHandle"));
					button.setLabel("Обработка");
					button.setAttribute("fileId", pFile.getId());
					button.setAttribute(
							"fileName",
							pFile.getFile_name().substring(
									pFile.getFile_name().lastIndexOf("\\") + 1));
					button.setAttribute("row", row);
					button.addEventListener("onClick", fileButtonHandler);
					listcell.appendChild(button);
					row.appendChild(listcell);
				} /*
				 * else if (fileState == Constants.FILE_PROCESSED) { Listcell
				 * listcell = new Listcell(); Button button = new Button();
				 * button.setImage("/images/file.png");
				 * button.setLabel("Клиринг"); button.setAttribute("fileId",
				 * pFile.getId()); button.setAttribute("row", row);
				 * button.addEventListener("onClick", new EventListener() {
				 * 
				 * @Override public void onEvent(Event event) throws Exception {
				 * Button btn = (Button) event.getTarget(); Long id =
				 * Long.parseLong(btn.getAttribute("fileId") + ""); String
				 * fileName = (String) event.getTarget()
				 * .getAttribute("fileName"); // String error = //
				 * FileService.getAmountAgro(id,un,pwd,alias,fileName); // //
				 * AGRO //String error = FileService.getAmount(id, un, pwd, //
				 * alias, fileName); // Boshqa bankla
				 * 
				 * //alert(error); alert(fileName);
				 * System.out.println("fileName: " + fileName);
				 * 
				 * refreshModel(_startPageNumber); } });
				 * listcell.appendChild(button); row.appendChild(listcell); }
				 */else {
					row.appendChild(new Listcell(""));
				}

				// send button
				if (fileState == Constants.FILE_PROCESSED
						|| fileState == Constants.FILE_PROCESSED_WITH_ERRORS) {
					Listcell listcell = new Listcell();
					Button button = new Button();
					button.setImage("/images/file.png");
					button.setLabel("Формировать выходной файл");
					button.setAttribute("fileId", pFile.getId());
					button.setAttribute(
							"fileName",
							pFile.getFile_name().substring(
									pFile.getFile_name().lastIndexOf("\\") + 1));
					button.setAttribute("row", row);
					button.addEventListener("onClick",
							createOutFileButtonHandler);
					listcell.appendChild(button);
					row.appendChild(listcell);
				}
			}
		});

		java.util.List<String> str = new ListModelList();
		str.add("");
		this.humoFileId.setModel((ListModel) str);
		this.humoFileId.setModel((ListModel) new ListModelList(
				(Collection) FileService.getState(this.alias)));

		this.refreshModel(this._startPageNumber);

		accDataGrid.setItemRenderer(new ListitemRenderer() {
			@SuppressWarnings("unchecked")
			public void render(Listitem row, Object data) throws Exception {
				AccInfo pAccInfo = (AccInfo) data;

				row.setValue(pAccInfo);
				// row.appendChild(new Listcell(pAccInfo.getSurname()));
				row.appendChild(new Listcell(pAccInfo.getNciid()));
				row.appendChild(new Listcell(pAccInfo.getBank_account()));
				row.appendChild(new Listcell(pAccInfo.getFio()));
				row.appendChild(new Listcell(pAccInfo.getPinfl()));
				row.appendChild(new Listcell(pAccInfo.getBranch()));
			}
		});
		
		this.binder.loadAll();
		
	}

	private void fillRow(Listitem row, File pFile) {
		String fileName = pFile.getFile_name();
		fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
		row.setValue(pFile);
		row.setStyle("height:40px;");
		row.appendChild(new Listcell(pFile.getId().toString()));
		row.appendChild(new Listcell(fileName));
		row.appendChild(new Listcell(this.df.format(pFile.getFile_date())));
		row.appendChild(new Listcell(pFile.getFile_state_name()));
		row.appendChild(new Listcell(""));
	}

	public void onPaging$filePaging(ForwardEvent event) {
		PagingEvent pe = (PagingEvent) event.getOrigin();
		this._startPageNumber = pe.getActivePage();
		this.refreshModel(this._startPageNumber);
	}

	private void refreshModel(int activePage) {
		this.filePaging.setPageSize(this._pageSize);
		this.model = new PagingListModel(activePage, this._pageSize,
				this.filter, "");
		// if (this._needsTotalSizeUpdate) {
		this._totalSize = this.model.getTotalSize(this.filter, this.session
				.getAttribute("alias").toString());
		// this._needsTotalSizeUpdate = false;
		// }

		filePaging.setTotalSize(this._totalSize);
		dataGrid.setModel(model);
		if (this.model.getSize() > 0) {
			this.current = (File) this.model.getElementAt(0);
			this.sendSelEvt();
		}
	}

	private void refreshModelAcc(int activePageAcc) {
		int vDays = 0;
		try {
			vDays = Integer.parseInt(asearchDay.getValue());
		} catch (NumberFormatException ex) {
			alert(ex.getMessage());
			return;
		}
        String vCurrDate=df.format(FileService.getInfoDate(alias));
        String vBranch=asearchBranch.getValue();
        String vCodeBal=asearchAccBal.getValue();
        if (vCodeBal==null || vCodeBal.equals("") || vCodeBal==""){
        	alert("Код баланса не выбран");
		    return;
        }
        if ( !FileService.isEnabledAccBal(vCodeBal, alias) )
        {
        	alert("Код баланса не соответствует физическим лицам по справочкнику S_account.");
		    return;
        }
		this.accPaging.setPageSize(this._pageSizeAcc);
		this._totalSizeAcc = FileService.getAccCount(vBranch, vCodeBal, vDays, vCurrDate, alias);
		accPaging.setTotalSize(this._totalSizeAcc);

		List<AccInfo> accList = FileService.getAccList(activePageAcc,
				this._pageSizeAcc, vBranch, vCodeBal, vDays, vCurrDate, alias);

		accDataGrid.setModel(new BindingListModelList(accList, true));

	}

	public void onPaging$accPaging(ForwardEvent event) {
		PagingEvent pe = (PagingEvent) event.getOrigin();
		this._startPageNumberAcc = pe.getActivePage();
		this.refreshModelAcc(this._startPageNumberAcc);
	}

	public File getCurrent() {
		return this.current;
	}

	public void setCurrent(File current) {
		this.current = current;
	}

	public EmpcExptRecord getCurrentRecord() {
		return this.currentRecord;
	}

	public void setCurrentRecord(EmpcExptRecord currentRecord) {
		this.currentRecord = currentRecord;
	}

	public void onDoubleClick$dataGrid$grd() {
		this.fileRecords.setVisible(true);
		final Long empcId = this.current.getId();
		// ISLogger.getLogger().error("not err 001!");
		ISLogger.getLogger().error("not err empcId,  =" + empcId);
		ISLogger.getLogger().error("not err branch,  =" + branch);

		final Long fileType1 = FileService.getFileType(empcId);
		ISLogger.getLogger().error("not err fileType1,  =" + fileType1);
		if (branch.equals("00444") && (fileType1 != 4L || fileType1 != 4)) {
			fileRecords$listheader_tran_amt.setVisible(false);
		}
		ISLogger.getLogger().error("not err 222");

		this.fileRecords$fileRecordsListbox.setModel(new BindingListModelList(
				FileService.getFileRecords(empcId,
				// FileService.getFileType(empcId)), true));
						fileType1), true));
		this.fileRecords$fileRecordsListbox
				.setItemRenderer(new ListitemRenderer() {

					public void render(Listitem item, Object data)
							throws Exception {
						// EmpcExptRecord record = (EmpcExptRecord)data;
						ISLogger.getLogger().error("not err render 001!");

						HumoFileRecords record = (HumoFileRecords) data;

						long recordState = 1L;
						item.setValue(record);
						recordState = FileService.getRecordState(record.getId());
						fillItemHumo(item, record, empcId);
						if (recordState != Constants.RECORD_PROCESSED) {
							// Listcell listcell = new Listcell();
							// Button button = new Button();
							// button.setImage("/images/file.png");
							// button.setLabel(Labels.getLabel("file.recordHandle"));
							// button.setAttribute("item", item);
							// button.setAttribute("recordId", record.getId());
							// button.addEventListener("onClick",
							// recordButtonHandler);
							// listcell.appendChild(button);
							// item.appendChild(listcell);
						} else {
							item.appendChild(new Listcell(""));
						}
						ISLogger.getLogger().error("not err render 001 end!");

					}
				});
		ISLogger.getLogger().error("not err 333");
	}

	public void onDoubleClick$fileRecordsListbox$fileRecords() {

		// if (FileService.getFileType(this.current.getId())==1L){
		// // EmpcFilesBTransactions record=null;
		// //
		// //
		// record=EmpcFilesBTransactionsService.getEmpcFilesBTransactions(this.currentRecord.getId());
		// //
		//
		//
		// //this.empcfilesbtransactionsmain.setVisible(true);
		// EmpcExptRecord record = null;
		// record = FileService.getEmpcExptRecord(this.currentRecord.getId());
		// String accntAmt = FileService.customFormat("###,###,###,###,###.00",
		// record.getAccnt_amt().doubleValue() / 100.0D);
		// String tranAmount =
		// FileService.customFormat("###,###,###,###,###.00",
		// record.getTran_amt().doubleValue() / 100.0D);
		//
		// this.bfileRecordFields.setVisible(true);
		// this.bfileRecordFields$id.setValue(record.getId().toString());
		// this.bfileRecordFields$tranz_acct.setValue(record.getTranz_acct());
		//
		//
		// }
		//
		// else{

		ISLogger.getLogger().error(
				"not err onDoubleClick$fileRecordsListbox$fileRecords 001!");

		EmpcExptRecord record = null;
		record = FileService.getEmpcExptRecord(this.currentRecord.getId());
		String accntAmt = FileService.customFormat("###,###,###,###,###.00",
				record.getAccnt_amt().doubleValue() / 100.0D);
		String tranAmount = FileService.customFormat("###,###,###,###,###.00",
				record.getTran_amt().doubleValue() / 100.0D);
		this.fileRecordFields.setVisible(true);
		this.fileRecordFields$id.setValue(record.getId().toString());
		this.fileRecordFields$empc_file_id.setValue(record.getEmpc_file_id()
				.toString());
		this.fileRecordFields$state_id
				.setValue(record.getState_id().toString());
		this.fileRecordFields$record_type.setValue(record.getRecord_type());
		this.fileRecordFields$line_number.setValue(record.getLine_number()
				.toString());
		this.fileRecordFields$client.setValue(record.getClient());
		this.fileRecordFields$card_acct.setValue(record.getCard_acct());
		this.fileRecordFields$accnt_ccy.setValue(record.getAccnt_ccy());
		this.fileRecordFields$card.setValue(record.getCard());
		this.fileRecordFields$slip_nr.setValue(record.getSlip_nr());
		this.fileRecordFields$ref_number.setValue(record.getRef_number());
		this.fileRecordFields$tran_date_time.setValue(record
				.getTran_date_time().toString());
		this.fileRecordFields$rec_date
				.setValue(record.getRec_date().toString());
		this.fileRecordFields$post_date.setValue(record.getPost_date()
				.toString());
		this.fileRecordFields$deal_desc.setValue(record.getDeal_desc());
		this.fileRecordFields$tran_type.setValue(record.getTran_type());
		this.fileRecordFields$tranz_acct.setValue(record.getTranz_acct());
		this.fileRecordFields$term_id.setValue(record.getTerm_id());
		this.fileRecordFields$iss_mfo.setValue(record.getIss_mfo());
		this.fileRecordFields$product.setValue(record.getProduct());
		this.fileRecordFields$internal_no.setValue(record.getInternal_no()
				.toString());
		this.fileRecordFields$proc_id.setValue(record.getProc_id().toString());
		this.fileRecordFields$city.setValue(record.getCity());
		this.fileRecordFields$country.setValue(record.getCountry());
		this.fileRecordFields$abvr_name.setValue(record.getAbvr_name());
		this.fileRecordFields$merchant.setValue(record.getMerchant());
		this.fileRecordFields$mcc_code.setValue(record.getMcc_code());
		this.fileRecordFields$terminal.setValue(record.getTerminal());
		this.fileRecordFields$accnt_amt.setValue(accntAmt);
		this.fileRecordFields$tran_amt.setValue(tranAmount);
		this.fileRecordFields$tran_ccy.setValue(record.getTran_ccy());
		this.fileRecordFields$deb_cred.setValue(record.getDeb_cred());

		ISLogger.getLogger().error(
				"not err onDoubleClick$fileRecordsListbox$fileRecords 002!");

		// }
	}

	// private void fillItem(Listitem item, EmpcExptRecord record) {
	// String tranAmount = FileService.customFormat("###,###,###,###,###.00",
	// record.getTran_amt().doubleValue() / 100.0D);
	// String recordStateName =
	// FileService.getRecordStateName(record.getState_id().intValue());
	// String recrodErrorText = FileService.getRecordErrorText(record.getId());
	// item.setValue(record);
	// item.setStyle("height:40px;");
	// item.appendChild(new Listcell(record.getId().toString()));
	// item.appendChild(new Listcell(record.getSlip_nr()));
	// item.appendChild(new Listcell(tranAmount));
	// item.appendChild(new Listcell(record.getMcc_code()));
	// item.appendChild(new Listcell(record.getTerm_id()));
	// item.appendChild(new Listcell(record.getMerchant()));
	// item.appendChild(new Listcell(recordStateName));
	// item.appendChild(new Listcell(recrodErrorText));
	// }

	private void fillItemHumo(Listitem item, HumoFileRecords record, long empcId) {

		formatSymbols.setDecimalSeparator('.');
		formatSymbols.setGroupingSeparator(' ');
		dcf = new DecimalFormat("0.00##", formatSymbols);

		// String recordStateName =
		// FileService.getRecordStateName(record.getState());
		// String recrodErrorText =
		// FileService.getRecordErrorText(record.getId(),empcId);
		item.setValue(record);
		item.setStyle("height:40px;");
		item.appendChild(new Listcell(record.getId().toString()));
		item.appendChild(new Listcell(record.getAgreement_numver()));
		item.appendChild(new Listcell(record.getDbo_closed_dt()));
		item.appendChild(new Listcell(record.getDoc_fio()));
		item.appendChild(new Listcell(record.getBank_account()));
		item.appendChild(new Listcell(record.getPinfl()));
		item.appendChild(new Listcell(record.getNciid()));
		item.appendChild(new Listcell(record.getErrorText()));
		item.appendChild(new Listcell(record.getState()));

	}

	public void onClick$btn_back() {
		this.onDoubleClick$dataGrid$grd();
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
			this.dataGrid
					.setSelectedIndex(this.dataGrid.getSelectedIndex() - 1);
			this.sendSelEvt();
		}

	}

	public void onClick$btn_next() {
		if (this.dataGrid.getSelectedIndex() != this.model.getSize() - 1) {
			this.dataGrid
					.setSelectedIndex(this.dataGrid.getSelectedIndex() + 1);
			this.sendSelEvt();
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

		SelectEvent evt = new SelectEvent("onSelect", this.dataGrid,
				this.dataGrid.getSelectedItems());
		Events.sendEvent(evt);
	}

	public void onClick$btnSearch() {
		this.filter = new FileFilter();
		String fullFileName = this.fileDirectory
				+ this.searchFileName.getValue().toUpperCase();
		if (!this.searchId.getValue().equals("")) {
			this.filter.setId(Long.valueOf(this.searchId.getValue()));
		}

		if (!this.searchFileName.getValue().equals("")) {
			this.filter.setFile_name(fullFileName);
		}

		if (this.searchDate.getValue() != null) {
			this.filter.setFile_date(this.searchDate.getValue());
		}

		if (this.humoFileId.getValue() != null
				&& !this.humoFileId.getValue().equals("")) {
			this.filter
					.setState_id(Integer.valueOf(this.humoFileId.getValue()));
		}

		this.refreshModel(this._startPageNumber);
	}

	public void onClick$btnClearSearch() {
		this.searchId.setValue("");
		this.searchFileName.setValue("");
		this.searchDate.setValue((Date) null);
		this.humoFileId.setValue("");
	}

	public void onClick$btn_show_acc_filter() {
		this.grd.setVisible(false);
		this.divFilterAccounts.setVisible(true);
	}

	public void onClick$btnAccSearch() {
		// this.grd.setVisible(false);
		// this.divFilterAccounts.setVisible(true);

		refreshModelAcc(_startPageNumberAcc);
	}

	public void onClick$btnAddForClosing() {
		
		int vDays = 0;
		try {
			vDays = Integer.parseInt(asearchDay.getValue());
		} catch (NumberFormatException ex) {
			alert(ex.getMessage());
			return;
		}
        String vCurrDate=df.format(FileService.getInfoDate(alias));
        String vBranch=asearchBranch.getValue();
        String vCodeBal=asearchAccBal.getValue();
        if (vCodeBal==null || vCodeBal.equals("") || vCodeBal==""){
        	alert("Код баланса не выбран");
		    return;
        }
        if ( !FileService.isEnabledAccBal(vCodeBal, alias) )
        {
        	alert("Код баланса не соответствует физическим лицам по справочкнику S_account.");
		    return;
        }
		String vNumDoc = asearchNumDoc.getValue();
        if (vNumDoc==null || vNumDoc.equals("") || vNumDoc==""){
        	alert("Номер распоряжения не заполнен.");
		    return;
        }
        String vPurpose = asearchPurpose.getValue();
        if (vPurpose==null || vPurpose.equals("") || vPurpose==""){
        	alert("Основание на закрытие не заполнен.");
		    return;
        }
        Date vDateDoc = asearchDateDoc.getValue();
        if (vDateDoc==null){
        	alert("Дата распоряжения не заполнен.");
		    return;
        }
		
		ClientaccFilter filter = new ClientaccFilter();
		filter.setBranch(vBranch);
		filter.setBal_code(vCodeBal);
		filter.setDays(vDays);
		filter.setEmp_id(""+uid);
		filter.setNum_doc(vNumDoc);
		filter.setDate_doc(vDateDoc);
		filter.setPurpose(vPurpose);
		filter.setBank_date(FileService.getInfoDate(alias));
		
		Res res = new Res();
		res = FileService.InsertFilterResults(filter, alias);
		if (res.getCode()==0) 
		{
			Res res2 = new Res();
			res2 = FileService.UpdateFilterResults(Integer.parseInt(res.getName()), alias);
			if (res2.getCode()==0) 
			{
				this.refreshModel(this._startPageNumber);
				alert("Успешно!");
				this.divFilterAccounts.setVisible(false);
	  		    this.grd.setVisible(true);
			}
			else
			{
				alert("Error:(2) "+res2.getName());
			}
			//this.divFilterAccounts.setVisible(false);
  		    //this.grd.setVisible(true);
		}
		else
		{
			alert("Error:(1) "+res.getName());
		}
	}

	// public void onTimer$timer(Event e){
	// Connection c=null;
	//
	// try{
	// c = ConnectionPool.getConnection(un,pwd,alias);
	// System.out.println("HUMO timer!");
	// ISLogger.getLogger().error("HUMO timer!");
	// String fileType=FileService.getFileType(fileId).toString();
	// String fileIdStr=fileId.toString();
	//
	//
	// if (fileType.equals("1")){
	//
	// String countState1=FileService.getCountBState1(c, fileIdStr);
	//
	// if (!countState1.equals("0")){
	// filemain$common.setValue(FileService.getCountСommonB(c, fileIdStr));
	// filemain$success.setValue(FileService.getCountSuccessB(c,fileIdStr));
	// filemain$error.setValue(FileService.getCountErrorB(c, fileIdStr));
	// }
	// else {
	//
	// timer.stop();
	// alert("Проводка закончилось");
	// refreshModel(_startPageNumber);
	// }
	// }
	// else {
	//
	// String countState1=FileService.getCountEXPTStat1(c, fileIdStr);
	//
	// if (!countState1.equals("0")){
	//
	// filemain$fileId.setValue(fileId.toString());
	// filemain$fileName.setValue(fileName);
	// filemain$common.setValue(FileService.getCountCommonEXPT(c, fileIdStr));
	// filemain$success.setValue(FileService.getCountSuccessEXPT(c,fileIdStr));
	// filemain$error.setValue(FileService.getCountErrorEXPT(c, fileIdStr));
	// }
	// else {
	//
	// timer.stop();
	// alert("Проводка закончилось");
	//
	// refreshModel(_startPageNumber);
	// }
	// }
	// }catch(Exception ex){
	// ex.printStackTrace();
	// ISLogger.getLogger().error(CheckNull.getPstr(ex));
	//
	// }
	//
	// finally {
	// if(c != null) try {c.close();} catch (SQLException exc)
	// {ISLogger.getLogger().error(exc);}
	//
	// }
	// }

	public void onFocus$customermain() {
		this.refreshModel(this._startPageNumber);
	}
}
