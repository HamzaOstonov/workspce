package com.is.tieto_visa.fileProcessing;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;

import org.apache.log4j.Logger;
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
import org.zkoss.zul.Filedownload;
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
//import com.is.file_reciever_srv.recievers.visa.onus.Onus_records_processing;
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

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import javax.security.cert.X509Certificate;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.util.media.AMedia;
import org.zkoss.util.resource.Labels;
import org.zkoss.zul.Button;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

import sun.security.action.GetLongAction;

public class FileViewCtrl extends GenericForwardComposer {

	private Listbox dataGrid;
	private Listbox generalDataGrid;
	private Listbox fileRecords$fileRecordsListbox;
	private Div grd;
	private Div fileRecords$record;
	private Toolbarbutton btn_last;
	private Toolbarbutton btn_next;
	private Toolbarbutton btn_prev;
	private Toolbarbutton btn_first;
	private Toolbarbutton btn_back;
	private Paging filePaging;
	private Window fileRecords;
	
	private Window fileRecordFields;
	private Window reportWindow;
	private Window empcfilesbtransactionsmain;
	private Textbox searchId;
	private Textbox searchFileName;
	private Datebox searchDate;
	private Datebox reportWindow$in_date;
	private Textbox reportWindow$rep_acc;
	private Label fileRecordFields$id;
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
	private Label fileRecordFields$tranz_acct; 
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
	private Label fileRecordFields$settl_cmi       ;
	private Label fileRecordFields$settl_date      ;  
	private Label fileRecordFields$send_cmi        ; 
	private Label fileRecordFields$tran_name_uk    ;  
	private Label fileRecordFields$counterparty    ;    
	private Label fileRecordFields$country_code    ;   
	private Label fileRecordFields$amount_acc      ;   
	private Label fileRecordFields$tr_fee_code     ;      
	private Label fileRecordFields$tr_fee_amt      ;   
	private Label fileRecordFields$i_amount        ;
	private Label fileRecordFields$sb_amount       ;
	private Label fileRecordFields$bank_code       ;
	private Label fileRecordFields$branch          ;
	private Label fileRecordFields$pointcode       ;
	private Listheader fileRecords$listheader_merchant;            
	private Listheader fileRecords$listheader_abvr_name;           
	private Listheader fileRecords$listheader_card      ;          
	private Listheader fileRecords$listheader_tranz_acct ;         
	private Listheader fileRecords$listheader_terminal    ;        
	private Listheader fileRecords$listheader_mcc_code    ;        
	private Listheader fileRecords$listheader_tran_type   ;        
	private Listheader fileRecords$listheader_accnt_amt   ;        
	private Listheader fileRecords$listheader_tran_amt    ;        
	private Listheader fileRecords$listheader_tran_amt2   ;        
	private Listheader fileRecords$listheader_amount      ;        
	private Listheader fileRecords$listheader_fee         ;        
	private Listheader fileRecords$listheader_tr_fee      ;        
	private Listheader fileRecords$listheader_surcharge;
	private Listheader fileRecords$listheader_tran_date_time;
	private Listheader fileRecords$listheader_error;
	private Listheader fileRecords$listheader_action;
	
	private Grid fileRecordFields$fieldsGrid;
	private String branch, un, pwd, alias;
	private int _pageSize = 15;
	private int _startPageNumber = 0;
	private int _totalSize = 0;
	private boolean _needsTotalSizeUpdate = true;
	public FileFilter filter;
	PagingListModel model = null;
	ListModelList lmodel = null;
	private AnnotateDataBinder binder;
	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	DecimalFormat dcf;
	DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols(Locale.getDefault());
	
	private File current = new File();
	private HumoFileRecords currentRecord = new HumoFileRecords();
	private String fileDirectory;
	private Timer timer;
	private Textbox filemain$common;
	private Textbox filemain$success;
	private Textbox filemain$error;
	private Textbox filemain$fileId;
	private Textbox filemain$fileName;
	Long fileId = null;
	String fileName = null;
	private RefCBox humoFileId;

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
					alert("Проводка для файла " + fileName + " начался!");
					// alert("Проводка для файла " +fileName+" закончелся");

				}

				else {

					// c.rollback();
			        if (branch.equals("00444")) 
			        	alert("Проводка для файла " + fileName + " начался");
			        else	
					    alert(err);
					// timer.start();

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
			//Onus_records_processing processing = new Onus_records_processing();
			c = ConnectionPool.getConnection(un, pwd, alias);
			// processing.load_config_map(un,pwd,alias, c);
			// processing.load_branch(c);
			// Expt_records_processing.alter_session_init(c, branch);
			// processing.init(c);
			// processing.process_record(c,record,un,pwd,alias);
			//processing.close();
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
		this.binder.loadAll();
		String[] parameter = (String[]) this.param.get("ht");
		this.branch = (String) this.session.getAttribute("branch");
		un = (String) session.getAttribute("un");
		pwd = (String) session.getAttribute("pwd");
		alias = (String) session.getAttribute("alias");
		this.fileDirectory = FileService
				.getTietoDirectory(Constants.ID_TIETO_DIRECTORY);
		if (parameter != null) {
			this._pageSize = Integer.parseInt(parameter[0]) / 44;
			this.dataGrid.setRows(Integer.parseInt(parameter[0]) / 44);

		}

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
					button.setLabel("Обработка/");
					button.setAttribute("fileId", pFile.getId());
					button.setAttribute(
							"fileName",
							pFile.getFile_name().substring(
									pFile.getFile_name().lastIndexOf("\\") + 1));
					button.setAttribute("row", row);
					button.addEventListener("onClick", fileButtonHandler);

					listcell.appendChild(button);

					row.appendChild(listcell);
				} else if (fileState == Constants.FILE_PROCESSED) {
					Listcell listcell = new Listcell();
					Button button = new Button();
					button.setImage("/images/file.png");
					button.setLabel("Клиринг/");
					button.setAttribute("fileId", pFile.getId());
					button.setAttribute("row", row);
					button.addEventListener("onClick", new EventListener() {

						@Override
						public void onEvent(Event event) throws Exception {
							Button btn = (Button) event.getTarget();
							Long id = Long.parseLong(btn.getAttribute("fileId")
									+ "");
							String fileName = (String) event.getTarget()
									.getAttribute("fileName");
							// String error =
							// FileService.getAmountAgro(id,un,pwd,alias,fileName);
							// // AGRO
							String error = FileService.getAmount(id, un, pwd,
									alias, fileName); // Boshqa bankla

							alert(error);
							alert(fileName);
							System.out.println("fileName: " + fileName);

							refreshModel(_startPageNumber);
						}
					});
					listcell.appendChild(button);
					row.appendChild(listcell);
				} else {
					row.appendChild(new Listcell(""));
				}

			}

		}

		);

		java.util.List<String> str = new ListModelList();
		str.add("");
		this.humoFileId.setModel((ListModel) str);
		this.humoFileId.setModel((ListModel) new ListModelList(
				(Collection) FileService.getState(this.alias)));

		this.refreshModel(this._startPageNumber);

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

	public File getCurrent() {
		return this.current;
	}

	public void setCurrent(File current) {
		this.current = current;
	}

	public HumoFileRecords getCurrentRecord() {
		return this.currentRecord;
	}

	public void setCurrentRecord(HumoFileRecords currentRecord) {
		this.currentRecord = currentRecord;
	}

	public void onDoubleClick$dataGrid$grd() {
		this.fileRecords.setVisible(true);
		final Long empcId = this.current.getId();
		//ISLogger.getLogger().error("not err 001!");
		ISLogger.getLogger().error("not err empcId,  ="+empcId);
		ISLogger.getLogger().error("not err branch,  ="+branch);
		
		final Long fileType1=FileService.getFileType(empcId);
		if (branch.equals("00444") && ( fileType1!=4L || fileType1!=4 ) ) {
		  fileRecords$listheader_tran_amt.setVisible(false);
		}
        if (branch.equals("00444")) {
		 fileRecords$listheader_amount.setVisible(false);
		 fileRecords$listheader_fee.setVisible(false);
		 fileRecords$listheader_tr_fee.setVisible(false);
		 fileRecords$listheader_surcharge.setVisible(false);
        }

        if (/*branch.equals("00394") ||*/ branch.equals("01142"))/*madadbank*/ {
   		    fileRecords$listheader_fee.setVisible(false);
		    fileRecords$listheader_tr_fee.setVisible(false);
		    fileRecords$listheader_surcharge.setVisible(false);
		    fileRecords$listheader_merchant.setLabel("grouprefn");
		    fileRecords$listheader_abvr_name.setLabel("synthrefn");
		    fileRecords$listheader_card.setLabel("synthcode");  
		    fileRecords$listheader_terminal.setLabel("AnalyticRefN");
		    fileRecords$listheader_mcc_code.setLabel("cre_analyticaccount");
		    fileRecords$listheader_accnt_amt.setLabel("cre_amount");
		    fileRecords$listheader_tranz_acct.setLabel("deb_analyticaccount");
		    fileRecords$listheader_amount.setLabel("deb_amount");
		    fileRecords$listheader_tran_date_time.setLabel("PostingDate");
		    fileRecords$listheader_tran_amt.setLabel("amountdata_amount");
        }
        
		this.fileRecords$fileRecordsListbox.setModel(new BindingListModelList(
				FileService.getFileRecords(empcId,
                //FileService.getFileType(empcId)), true));
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

					}
				});
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
		
		//ISLogger.getLogger().error("not err onDoubleClick$fileRecordsListbox$fileRecords 001!");
		
		if (FileService.getFileType(this.current.getId())==1L){
			EmpcExptRecord record = null;
			record = FileService.getVisaOnusRecord(this.currentRecord.getId());
			String accntAmt = "0";
			if (record.getAccnt_amt()!=null)
				accntAmt= FileService.customFormat("###,###,###,###,###.00",
					record.getAccnt_amt().doubleValue() / 100.0D);
			String tranAmount = "0";
			if (record.getTran_amt()!=null)
				tranAmount=FileService.customFormat("###,###,###,###,###.00",
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
			this.fileRecordFields$rec_date.setValue((record.getRec_date()!=null)? record.getRec_date().toString() : "");
			this.fileRecordFields$post_date.setValue((record.getPost_date()!=null)? record.getPost_date().toString() : "");
			this.fileRecordFields$deal_desc.setValue(record.getDeal_desc());
			this.fileRecordFields$tran_type.setValue(record.getTran_type());
			this.fileRecordFields$tranz_acct.setValue(record.getTranz_acct());
			this.fileRecordFields$term_id.setValue(record.getTerm_id());
			this.fileRecordFields$iss_mfo.setValue(record.getIss_mfo());
			this.fileRecordFields$product.setValue(record.getProduct());
			this.fileRecordFields$internal_no.setValue(record.getInternal_no().toString());
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
		} else if (FileService.getFileType(this.current.getId())==2L || FileService.getFileType(this.current.getId())==6L || FileService.getFileType(this.current.getId())==9L) {
			EmpcExptRecord record = null;
			record = FileService.getEmpcExptRecord(this.currentRecord.getId());
			String accntAmt = "0";
			if (record.getAccnt_amt()!=null)
				accntAmt= FileService.customFormat("###,###,###,###,###.00",
					record.getAccnt_amt().doubleValue() / 100.0D);
			String tranAmount = "0";
			if (record.getTran_amt()!=null)
				tranAmount=FileService.customFormat("###,###,###,###,###.00",
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
			this.fileRecordFields$rec_date.setValue( (record.getRec_date()!=null) ?  record.getRec_date().toString() : "");
			this.fileRecordFields$post_date.setValue(record.getPost_date().toString());
			this.fileRecordFields$deal_desc.setValue(record.getDeal_desc());
			this.fileRecordFields$tran_type.setValue(record.getTran_type());
			this.fileRecordFields$tranz_acct.setValue(record.getTranz_acct());
			this.fileRecordFields$term_id.setValue(record.getTerm_id());
			this.fileRecordFields$iss_mfo.setValue(record.getIss_mfo());
			this.fileRecordFields$product.setValue(record.getProduct());
			this.fileRecordFields$internal_no.setValue(record.getInternal_no().toString());
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
			
			this.fileRecordFields$settl_cmi.setValue(record.getSettl_cmi());
			this.fileRecordFields$settl_date.setValue( (record.getSettl_date()!=null) ?  record.getSettl_date().toString() : "" );
			this.fileRecordFields$send_cmi.setValue(record.getSend_cmi());
			this.fileRecordFields$tran_name_uk.setValue(record.getTran_name_uk());
			this.fileRecordFields$counterparty.setValue(record.getCounterparty());
			this.fileRecordFields$country_code.setValue(record.getCountry_code());
			this.fileRecordFields$amount_acc.setValue(record.getAmount_acc().toString());
			this.fileRecordFields$tr_fee_code.setValue(record.getTr_fee_code());
			this.fileRecordFields$tr_fee_amt.setValue(record.getTr_fee_amt().toString());
			this.fileRecordFields$i_amount.setValue(record.getI_amount().toString());
			this.fileRecordFields$sb_amount.setValue(record.getSb_amount().toString());
			this.fileRecordFields$bank_code.setValue(record.getBank_code());
			this.fileRecordFields$branch.setValue(record.getBranch());
			this.fileRecordFields$pointcode.setValue(record.getPointcode());
		} else if (FileService.getFileType(this.current.getId())==7L) {
			//madadbank transfers file
		
			EmpcExptRecord record = null;
			
			record = FileService.getTransfersFileRecord(this.currentRecord.getId());
			String accntAmt = "0";
			if (record.getAccnt_amt()!=null)
				accntAmt= FileService.customFormat("###,###,###,###,###.00",
					record.getAccnt_amt().doubleValue() / 100.0D);
			String tranAmount = "0";
			if (record.getTran_amt()!=null)
				tranAmount=FileService.customFormat("###,###,###,###,###.00",
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
			this.fileRecordFields$rec_date.setValue( (record.getRec_date()!=null) ?  record.getRec_date().toString() : "");
			//this.fileRecordFields$post_date.setValue(record.getPost_date().toString());
			this.fileRecordFields$deal_desc.setValue(record.getDeal_desc());
			this.fileRecordFields$tran_type.setValue(record.getTran_type());
			this.fileRecordFields$tranz_acct.setValue(record.getTranz_acct());
			this.fileRecordFields$term_id.setValue(record.getTerm_id());
			this.fileRecordFields$iss_mfo.setValue(record.getIss_mfo());
			this.fileRecordFields$product.setValue(record.getProduct());
			//this.fileRecordFields$internal_no.setValue(record.getInternal_no().toString());
			this.fileRecordFields$proc_id.setValue(record.getProc_id().toString());
			//this.fileRecordFields$city.setValue(record.getCity());
			//this.fileRecordFields$country.setValue(record.getCountry());
			this.fileRecordFields$abvr_name.setValue(record.getAbvr_name());
			this.fileRecordFields$merchant.setValue(record.getMerchant());
			this.fileRecordFields$mcc_code.setValue(record.getMcc());
			this.fileRecordFields$terminal.setValue(record.getTerminal());
			this.fileRecordFields$accnt_amt.setValue(accntAmt);
			this.fileRecordFields$tran_amt.setValue(tranAmount);
			this.fileRecordFields$tran_ccy.setValue(record.getTran_ccy());
			//this.fileRecordFields$deb_cred.setValue(record.getDeb_cred());
		}
		
		
		ISLogger.getLogger().error("not err onDoubleClick$fileRecordsListbox$fileRecords 002!");
		
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
		item.appendChild(new Listcell(record.getMerchant()));
		item.appendChild(new Listcell(record.getAbvr_name()));
		item.appendChild(new Listcell(record.getCard()));
		item.appendChild(new Listcell(record.getTranz_acct()));
		item.appendChild(new Listcell(record.getTermId()));
		item.appendChild(new Listcell(record.getMcc()));
		item.appendChild(new Listcell(record.getTran_type()));
		if (record.getSumma()!=null)
		    item.appendChild(new Listcell(dcf.format((new BigDecimal(record.getSumma())).divide(new BigDecimal("100")) ) ));
		else
			item.appendChild(new Listcell("") ) ;
		if (record.getTran_amt()!=null)
		   item.appendChild(new Listcell(dcf.format((new BigDecimal(record.getTran_amt()))) ) );
		else
			item.appendChild(new Listcell("") ) ;
		if (record.getTran_amt2()!=null)
		  item.appendChild(new Listcell(dcf.format((new BigDecimal(record.getTran_amt2()))) ) );
		else
			item.appendChild(new Listcell("") ) ;	
		if (record.getAmount()!=null)
		  item.appendChild(new Listcell(dcf.format((new BigDecimal(record.getAmount())).divide(new BigDecimal("100"))) ) );
		else
			item.appendChild(new Listcell("") ) ;
		if (record.getFee()!=null)
		  item.appendChild(new Listcell(dcf.format((new BigDecimal(record.getFee())).divide(new BigDecimal("100"))) ) );
		else
			item.appendChild(new Listcell("") ); 
		if (record.getTr_fee()!=null)
			item.appendChild(new Listcell(dcf.format((new BigDecimal(record.getTr_fee())).divide(new BigDecimal("100"))) ) );
		else
			item.appendChild(new Listcell("") ) ;
		if (record.getSurcharge()!=null)
		  item.appendChild(new Listcell(dcf.format((new BigDecimal(record.getSurcharge())).divide(new BigDecimal("100"))) ) );
		else
			item.appendChild(new Listcell("") ); 
		item.appendChild(new Listcell(record.getTran_date_time()));
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
	
	public void onClick$btn_report() {
		if (reportWindow$in_date.getValue()==null)
			reportWindow$in_date.setValue(new Date());
		
		this.reportWindow.setVisible(true);
		
	}
	
	public static void send_soap_request() {
		try {
			
			/*for test purposes*/
			
			/*try {
			    SSLContext ssl_ctx = SSLContext.getInstance("TLS");
			        TrustManager[ ] trust_mgr = new TrustManager[ ] {
			    new X509TrustManager() {
			       public java.security.cert.X509Certificate[] getAcceptedIssuers() { return null; }
			       public void checkClientTrusted(X509Certificate[] certs, String t) { }
			       public void checkServerTrusted(X509Certificate[] certs, String t) { }
				@Override
				public void checkClientTrusted(
						java.security.cert.X509Certificate[] arg0, String arg1)
						throws CertificateException {
					// TODO Auto-generated method stub
					
				}
				@Override
				public void checkServerTrusted(
						java.security.cert.X509Certificate[] arg0, String arg1)
						throws CertificateException {
					// TODO Auto-generated method stub
					
				}
			     }
			  };
			        ssl_ctx.init(null,                // key manager
			                     trust_mgr,           // trust manager
			                     new SecureRandom()); // random number generator
			        HttpsURLConnection.setDefaultSSLSocketFactory(ssl_ctx.getSocketFactory());
			    } catch(NoSuchAlgorithmException e) {
			        e.printStackTrace();
			    } catch(KeyManagementException e) {
			        e.printStackTrace();
			    }*/
			
			String url = "https://128.10.10.210:48443/issuingws_x14/services/Issuing";
			URL obj = new URL(url);
			HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type","application/soap+xml; charset=utf-8");
			String xml = "<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:bin=\"urn:IssuingWS/binding\">   <soapenv:Header/>   <soapenv:Body>      <bin:listCustomers soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">         <ConnectionInfo xsi:type=\"urn:OperationConnectionInfo\" xmlns:urn=\"urn:issuing_v_01_02_xsd\">            <BANK_C xsi:type=\"xsd:string\">01</BANK_C>            <GROUPC xsi:type=\"xsd:string\">01</GROUPC>            <!--Optional:-->            <FAULT_MODE xsi:type=\"xsd:decimal\">0</FAULT_MODE>            <!--Optional:-->            <EXTERNAL_SESSION_ID xsi:type=\"xsd:string\">345345345</EXTERNAL_SESSION_ID>         </ConnectionInfo>         <Parameters xsi:type=\"urn:RowType_ListCustomers_Request\" xmlns:urn=\"urn:issuing_v_01_02_xsd\">            <F_NAMES xsi:type=\"xsd:string\">JAMSHID</F_NAMES>            <!--Optional:-->            <SURNAME xsi:type=\"xsd:string\">ACHILOV</SURNAME>            <!--Optional:-->            <BANK_C xsi:type=\"xsd:string\">01</BANK_C>         </Parameters>      </bin:listCustomers>   </soapenv:Body></soapenv:Envelope>";
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(xml);
			wr.flush();
			wr.close();
			String responseStatus = con.getResponseMessage();
			System.out.println(responseStatus);
			BufferedReader in = new BufferedReader(new InputStreamReader(
			con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				 response.append(inputLine);
			}
			in.close();
			System.out.println("response:" + response.toString());
		 } catch (Exception e) {
			System.out.println(e);
		 }
	}


	public void to_excel_() {
	
		List<HumoFileRecords> list = FileService.getReport1(reportWindow$in_date.getValue(), reportWindow$rep_acc.getValue()!=null ? reportWindow$rep_acc.getValue() : "%" , "");
		
		Workbook workbook = new HSSFWorkbook();
		Sheet listSheet = workbook.createSheet("list_1");
		Row row = null;
		CellStyle style;
		style = workbook.createCellStyle();
		style.setDataFormat((short)0x4); // built-in number format
		Cell cell;
		Double vSumma = 0D;
		int rowIndex = 0;
		int cellIndex = 0;
		row = listSheet.createRow(rowIndex++);
		row.createCell(0).setCellValue("МФО:00444");
		row = listSheet.createRow(rowIndex++);
		row.createCell(0).setCellValue("Бош офис");
		rowIndex++;
		row = listSheet.createRow(rowIndex++);
		row.createCell(0).setCellValue("САЛЬДОВАЯ ВЕДОМОСТЬ");
		row = listSheet.createRow(rowIndex++);
		row.createCell(0).setCellValue("за "+df.format(reportWindow$in_date.getValue()));
		rowIndex++;		
		row = listSheet.createRow(rowIndex++);
		row.createCell(0).setCellValue("Ед.Изм.:");

		rowIndex = 9;
		row = listSheet.createRow(rowIndex++);
		cellIndex = 0;
		row.createCell(cellIndex++).setCellValue("СЧЕТ");
		row.createCell(cellIndex++).setCellValue("Номер карты");
		row.createCell(cellIndex++).setCellValue("Сумма овердарфта");
		row.createCell(cellIndex++).setCellValue("Филиал выпускающий карту");

		for (int i = 0; i < list.size(); i++) {
			row = listSheet.createRow(rowIndex++);
			cellIndex = 0;
			row.createCell(cellIndex).setCellValue(list.get(i).getTranz_acct());
			cellIndex++;
			row.createCell(cellIndex).setCellValue(list.get(i).getCard());
			cellIndex++;
			//row.createCell(cellIndex).setCellValue(list.get(i).getAccnt_amt());
			cell = row.createCell(cellIndex);
			//cell.setCellValue(list.get(i).getAccnt_amt());
			try {
				cell.setCellValue(Double.valueOf(list.get(i).getAccnt_amt()));
				vSumma=vSumma+Double.valueOf(list.get(i).getAccnt_amt());
			}
			catch (Exception e) {
				try {
					cell.setCellValue(Double.valueOf(list.get(i).getAccnt_amt().trim().replace(".", ",")));
					vSumma=vSumma+Double.valueOf(list.get(i).getAccnt_amt().trim().replace(".", ","));
					}
					catch (Exception e1) {
						//try {
							cell.setCellValue(Double.valueOf(list.get(i).getAccnt_amt().trim().replace(",", ".")));
							vSumma=vSumma+Double.valueOf(list.get(i).getAccnt_amt().trim().replace(",", "."));
						//	}
						//	catch (Exception e2) {
						//		
						//	}
					}
			}
			
			cell.setCellStyle(style);
			
			cellIndex++;
			row.createCell(cellIndex).setCellValue(list.get(i).getIss_mfo());
		}
		// itogo
		row = listSheet.createRow(rowIndex++);
		cellIndex = 0;
		row.createCell(cellIndex++).setCellValue("Итого");
		row.createCell(cellIndex++).setCellValue("");
		//row.createCell(cellIndex++).setCellValue(vSumma);
		cell = row.createCell(cellIndex++);
		cell.setCellValue(vSumma);
		cell.setCellStyle(style);
		row.createCell(cellIndex++).setCellValue("");

		listSheet.autoSizeColumn(0);
		listSheet.autoSizeColumn(1);
		listSheet.autoSizeColumn(2);
		listSheet.autoSizeColumn(3);		
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			workbook.write(baos);
			AMedia amedia = new AMedia("visa_overdraft_saldovka.xls", "xls", "application/file", baos.toByteArray());
			Filedownload.save(amedia);
			baos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onClick$btnReportExcel$reportWindow() {
		to_excel_();
		reportWindow.setVisible(false);
		//send_soap_request();
	}

}
