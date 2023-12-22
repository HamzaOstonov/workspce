package com.is.tieto_globuz.fileProcessing;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.text.SimpleDateFormat;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zkplus.databind.BindingListModelList;
import org.zkoss.zul.Button;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;
import org.zkoss.zul.Grid;
import org.zkoss.zul.event.PagingEvent;

import com.is.ConnectionPool;
import com.is.file_reciever_srv.recievers.EMPC.EXPT.Expt_records_processing;


public class FileViewCtrl extends GenericForwardComposer {
    private Listbox dataGrid, fileRecords$fileRecordsListbox;
    private Div grd, fileRecords$record;
    private Toolbarbutton btn_last;
    private Toolbarbutton btn_next;
    private Toolbarbutton btn_prev;
    private Toolbarbutton btn_first;
    private Toolbarbutton btn_back;
    private Paging filePaging;
    private Window fileRecords, fileRecordFields;    
    private Textbox searchId, searchFileName;
    private Datebox searchDate;
    private Label fileRecordFields$id, fileRecordFields$empc_file_id, fileRecordFields$state_id,
    	fileRecordFields$record_type, fileRecordFields$line_number, fileRecordFields$client, 
    	fileRecordFields$card_acct, fileRecordFields$accnt_ccy, fileRecordFields$card, fileRecordFields$slip_nr,
    	fileRecordFields$ref_number, fileRecordFields$tran_date_time, fileRecordFields$rec_date,
    	fileRecordFields$post_date, fileRecordFields$deal_desc, fileRecordFields$tran_type,
    	fileRecordFields$tranz_acct, fileRecordFields$term_id, fileRecordFields$iss_mfo, 
    	fileRecordFields$product, fileRecordFields$internal_no, fileRecordFields$proc_id, 
    	fileRecordFields$city, fileRecordFields$country, fileRecordFields$abvr_name, 
    	fileRecordFields$merchant, fileRecordFields$mcc_code, fileRecordFields$terminal, 
    	fileRecordFields$accnt_amt, fileRecordFields$tran_amt, fileRecordFields$tran_ccy, 
    	fileRecordFields$deb_cred;
    private Grid fileRecordFields$fieldsGrid;
    
    private String branch;
    
    private int _pageSize = 15;
    private int _startPageNumber = 0;
    private int _totalSize = 0;
    private boolean _needsTotalSizeUpdate = true;
    
    public FileFilter filter;

    PagingListModel model = null;
    ListModelList lmodel = null;
    private AnnotateDataBinder binder;
    SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");

    private File current = new File();
    private EmpcExptRecord currentRecord = new EmpcExptRecord();
    
    private String fileDirectory;

    public FileViewCtrl() {
        super('$', false, false);
    }
    
	/**
	 *
	 *
	 */
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);

		binder = new AnnotateDataBinder(comp);
		binder.bindBean("current", this.current);
		
		binder.bindBean("currentRecord", this.currentRecord);
		
		binder.loadAll();		
		String[] parameter = (String[]) param.get("ht");
		
		branch = (String) session.getAttribute("branch");
		fileDirectory = FileService.getTietoDirectory(Constants.ID_TIETO_DIRECTORY);
		
		if(parameter != null) {
			_pageSize = Integer.parseInt(parameter[0]) / 44;
			dataGrid.setRows(Integer.parseInt(parameter[0]) / 44);
		}

		dataGrid.setItemRenderer(new ListitemRenderer() {
			@SuppressWarnings("unchecked")
			public void render(Listitem row, Object data) throws Exception {
				File pFile = (File) data;		
				long fileState;
				
				fillRow(row, pFile);
				
                //----------Button File Processing----------
				fileState = FileService.getFileState(pFile.getId());
				if(fileState != Constants.FILE_PROCESSED) {				
					Listcell listcell = new Listcell();	
	
					Button button = new Button();
					
					button.setImage(Constants.BUTTON_HANDLER_IMAGE);
					button.setLabel(Labels.getLabel("file.fileHandle"));
					
					button.setAttribute("fileId", pFile.getId());
					button.setAttribute("row", row);
	
					button.addEventListener(Events.ON_CLICK, fileButtonHandler);
	                listcell.appendChild(button);			
				
					row.appendChild(listcell);	 
				}
				else {
					row.appendChild(new Listcell(""));
				}
			}
		});

		refreshModel(_startPageNumber);
	}
	
	private void fillRow(Listitem row, File pFile) {
		String fileName = pFile.getFile_name();				
		fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);	

		row.setValue(pFile);
		row.setStyle("height:40px;");
		row.appendChild(new Listcell(pFile.getId().toString()));				
		row.appendChild(new Listcell(fileName));
		row.appendChild(new Listcell(df.format(pFile.getFile_date())));				
		row.appendChild(new Listcell(pFile.getFile_state_name()));
	}
	
    private EventListener fileButtonHandler = new EventListener() {		
		@Override
		public void onEvent(Event event) throws Exception {
			File file = null;
			Listitem row = (Listitem) event.getTarget().getAttribute("row");
			
			Long fileId = (Long) event.getTarget().getAttribute("fileId");
			
			Expt_records_processing processing = new Expt_records_processing();
			processing.process_file(Long.valueOf(fileId));
			
			
			row.getChildren().clear();
			file = FileService.getFile(fileId);
			fillRow(row, file);
			row.appendChild(new Listcell(""));
			
		}    	
    };
	

	public void onPaging$filePaging(ForwardEvent event) {
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModel(_startPageNumber);
	}
	

	private void refreshModel(int activePage) {
	    filePaging.setPageSize(_pageSize);
	    model = new PagingListModel(activePage, _pageSize, filter, "");
	
		if(_needsTotalSizeUpdate) {
			_totalSize = model.getTotalSize(filter, session.getAttribute("alias").toString());
			_needsTotalSizeUpdate = false;
		}

		filePaging.setTotalSize(_totalSize);
		
		dataGrid.setModel((ListModel) model);
		if(model.getSize() > 0) {
			this.current = (File) model.getElementAt(0);
			sendSelEvt();
		}
	}


	//Omitted...
	public File getCurrent() {
		return current;
	}

	public void setCurrent(File current) {
		this.current = current;
	}
	
	public EmpcExptRecord getCurrentRecord() {
		return currentRecord;
	}

	public void setCurrentRecord(EmpcExptRecord currentRecord) {
		this.currentRecord = currentRecord;
	}

	public void onDoubleClick$dataGrid$grd() {
		fileRecords.setVisible(true);
		
		fileRecords$fileRecordsListbox.setModel(
				new BindingListModelList(FileService.getFileRecords(current.getId()), true));
			//	new ListModelList(FileService.getFileRecords(current.getId())));
		
		fileRecords$fileRecordsListbox.setItemRenderer(
			new ListitemRenderer() {

				@SuppressWarnings("unchecked")
				public void render(Listitem item, Object data) throws Exception {

					EmpcExptRecord record = (EmpcExptRecord) data;
					long recordState = 1l;
					
					fillItem(item, record);
					
	                //----------Button Record Processing----------
					recordState = FileService.getRecordState(record.getId());
					if(recordState != Constants.RECORD_PROCESSED) {
						Listcell listcell = new Listcell();	
						
						Button button = new Button();
						
						button.setImage(Constants.BUTTON_HANDLER_IMAGE);
						button.setLabel(Labels.getLabel("file.recordHandle"));
						button.setAttribute("item", item);
						button.setAttribute("recordId", record.getId());
		
						button.addEventListener(Events.ON_CLICK, recordButtonHandler);
		                listcell.appendChild(button);			
					
						item.appendChild(listcell);	
					}
					else {
						item.appendChild(new Listcell(""));
					}
				}

			}
		);
	}
	
	public void onDoubleClick$fileRecordsListbox$fileRecords() {
		EmpcExptRecord record = null;
		record = FileService.getEmpcExptRecord(currentRecord.getId());
		
		String accntAmt = FileService.customFormat(
				"###,###,###,###,###.00", 
				(record.getAccnt_amt().doubleValue() / 100));	
		
		String tranAmount = FileService.customFormat(
				"###,###,###,###,###.00", 
				(record.getTran_amt().doubleValue() / 100));	
		
		
		fileRecordFields.setVisible(true);
		fileRecordFields$id.setValue(record.getId().toString());
		fileRecordFields$empc_file_id.setValue(record.getEmpc_file_id().toString());
		fileRecordFields$state_id.setValue(record.getState_id().toString());
		fileRecordFields$record_type.setValue(record.getRecord_type());
		fileRecordFields$line_number.setValue(record.getLine_number().toString());
		fileRecordFields$client.setValue(record.getClient());
		fileRecordFields$card_acct.setValue(record.getCard_acct());
		fileRecordFields$accnt_ccy.setValue(record.getAccnt_ccy());
		fileRecordFields$card.setValue(record.getCard());
		fileRecordFields$slip_nr.setValue(record.getSlip_nr());
		fileRecordFields$ref_number.setValue(record.getRef_number());
		fileRecordFields$tran_date_time.setValue(record.getTran_date_time().toString());
		fileRecordFields$rec_date.setValue(record.getRec_date().toString());
		fileRecordFields$post_date.setValue(record.getPost_date().toString());
		fileRecordFields$deal_desc.setValue(record.getDeal_desc());
		fileRecordFields$tran_type.setValue(record.getTran_type());
		fileRecordFields$tranz_acct.setValue(record.getTranz_acct());
		fileRecordFields$term_id.setValue(record.getTerm_id());
		fileRecordFields$iss_mfo.setValue(record.getIss_mfo());
		fileRecordFields$product.setValue(record.getProduct());
		fileRecordFields$internal_no.setValue(record.getInternal_no().toString());
		fileRecordFields$proc_id.setValue(record.getProc_id().toString());
		fileRecordFields$city.setValue(record.getCity());
		fileRecordFields$country.setValue(record.getCountry());
		fileRecordFields$abvr_name.setValue(record.getAbvr_name());
		fileRecordFields$merchant.setValue(record.getMerchant());
		fileRecordFields$mcc_code.setValue(record.getMcc_code());
		fileRecordFields$terminal.setValue(record.getTerminal());
		fileRecordFields$accnt_amt.setValue(accntAmt);
		fileRecordFields$tran_amt.setValue(tranAmount);
		fileRecordFields$tran_ccy.setValue(record.getTran_ccy());
		fileRecordFields$deb_cred.setValue(record.getDeb_cred());
	}
	
	private void fillItem(Listitem item, EmpcExptRecord record) {
		
		String tranAmount = FileService.customFormat(
				"###,###,###,###,###.00", 
				(record.getTran_amt().doubleValue() / 100));					
		String recordStateName = FileService.getRecordStateName(record.getState_id().intValue());
		String recrodErrorText = FileService.getRecordErrorText(record.getId());
		
		item.setValue(record);
		item.setStyle("height:40px;");
		item.appendChild(new Listcell(record.getId().toString()));
		item.appendChild(new Listcell(record.getSlip_nr()));
		item.appendChild(new Listcell(tranAmount));
		item.appendChild(new Listcell(record.getMcc_code()));
		item.appendChild(new Listcell(record.getTerm_id()));
		item.appendChild(new Listcell(record.getMerchant()));
		item.appendChild(new Listcell(recordStateName));
		item.appendChild(new Listcell(recrodErrorText));
	}
	
    private EventListener recordButtonHandler = new EventListener() {		
		@Override
		public void onEvent(Event event) throws Exception {
			
			Connection c = null;
			com.is.file_reciever_srv.recievers.EMPC.EXPT.EXPT_record record = null;	
			EmpcExptRecord rec = null;			
			Long recordId = (Long) event.getTarget().getAttribute("recordId");			
			record = FileService.getEXPT_record(recordId);					
			
			Expt_records_processing processing = new Expt_records_processing();
			c = ConnectionPool.getConnection();
			processing.load_config_map();
			processing.alter_session_init(c, branch);
			processing.init(c);
			processing.process_record(record);
			processing.close();			

			Expt_records_processing.updateFileState(record.getEmpc_file_id());
					
			((Listitem)event.getTarget().getAttribute("item")).getChildren().clear();
			
			rec = FileService.getEmpcExptRecord(record.getId());
			fillItem((Listitem)event.getTarget().getAttribute("item"), rec);
			

			((Listitem)event.getTarget().getAttribute("item")).appendChild(new Listcell(""));
			((Listitem)event.getTarget().getAttribute("item")).setStyle("background-color:#99CC33; height:40px;");
			
		}    	
    };

	public void onClick$btn_back() {
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
	    if(dataGrid.getSelectedIndex() != 0) {
	    	dataGrid.setSelectedIndex(dataGrid.getSelectedIndex() - 1);
	    	sendSelEvt();
	    }
	}
	
	public void onClick$btn_next() {
	    if(dataGrid.getSelectedIndex() != (model.getSize() - 1)) {
	    	dataGrid.setSelectedIndex(dataGrid.getSelectedIndex() + 1);
	    	sendSelEvt();
	    }
	}


	private void sendSelEvt() {
	    if(dataGrid.getSelectedIndex() == 0) {
            btn_first.setDisabled(true);
            btn_prev.setDisabled(true);
	    } else {
            btn_first.setDisabled(false);
            btn_prev.setDisabled(false);
	    }
	    if(dataGrid.getSelectedIndex() == (model.getSize() - 1)) {
            btn_next.setDisabled(true);
            btn_last.setDisabled(true);
	    } else {
            btn_next.setDisabled(false);
            btn_last.setDisabled(false);
	    }
	    SelectEvent evt = new SelectEvent("onSelect", dataGrid, dataGrid.getSelectedItems());
	    Events.sendEvent(evt);
	}
	
	public void onClick$btnSearch() {
		filter = new FileFilter();
		String fullFileName = fileDirectory + searchFileName.getValue().toUpperCase();
		
		if(!searchId.getValue().equals("")) {
			filter.setId(Long.valueOf(searchId.getValue()));
		}
		if(!searchFileName.getValue().equals("")) {
			filter.setFile_name(fullFileName);
		}
		if(searchDate.getValue() != null) {
			filter.setFile_date(searchDate.getValue());
		}
		
		refreshModel(_startPageNumber);
	}
	
	public void onClick$btnClearSearch() {
		searchId.setValue("");
		searchFileName.setValue("");
		searchDate.setValue(null);
	}

}

