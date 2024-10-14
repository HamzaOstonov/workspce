// 
// Decompiled by Procyon v0.5.36
// 

package com.is.tieto_globuz.fileProcessing;

import java.util.Date;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zkplus.databind.BindingListModelList;
import org.zkoss.zul.event.PagingEvent;
import org.zkoss.zk.ui.event.ForwardEvent;
import java.util.List;
import java.util.Collection;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.Button;
import org.zkoss.zul.ListitemRenderer;
import com.is.file_reciever_srv.recievers.EMPC.EXPT.EXPT_record;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import com.is.file_reciever_srv.recievers.EMPC.EXPT.Expt_records_processing;
import java.sql.Connection;
import java.sql.SQLException;
import com.is.utils.CheckNull;
import com.is.ISLogger;
import com.is.ConnectionPool;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import com.is.utils.RefCBox;
import org.zkoss.zul.Timer;
import java.text.SimpleDateFormat;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Div;
import org.zkoss.zul.Listbox;
import org.zkoss.zk.ui.util.GenericForwardComposer;

public class FileViewCtrl extends GenericForwardComposer
{
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
    private Window bfileRecordFields;
    private Window fileRecordFields;
    private Window empcfilesbtransactionsmain;
    private Textbox searchId;
    private Textbox searchFileName;
    private Datebox searchDate;
    private Label fileRecordFields$id;
    private Label bfileRecordFields$id;
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
    private Label bfileRecordFields$tranz_acct;
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
    private Grid fileRecordFields$fieldsGrid;
    private String branch;
    private String un;
    private String pwd;
    private String alias;
    private int _pageSize;
    private int _startPageNumber;
    private int _totalSize;
    private boolean _needsTotalSizeUpdate;
    public FileFilter filter;
    PagingListModel model;
    ListModelList lmodel;
    private AnnotateDataBinder binder;
    SimpleDateFormat df;
    private File current;
    private EmpcExptRecord currentRecord;
    private String fileDirectory;
    private Timer timer;
    private Textbox filemain$common;
    private Textbox filemain$success;
    private Textbox filemain$error;
    private Textbox filemain$fileId;
    private Textbox filemain$fileName;
    Long fileId;
    String fileName;
    private RefCBox humoFileId;
    private EventListener fileButtonHandler;
    private EventListener fileButtonExcel;
    private EventListener recordButtonHandler;
    
    public FileViewCtrl() {
        super('$', false, false);
        this._pageSize = 15;
        this._startPageNumber = 0;
        this._totalSize = 0;
        this._needsTotalSizeUpdate = true;
        this.model = null;
        this.lmodel = null;
        this.df = new SimpleDateFormat("dd.MM.yyyy");
        this.current = new File();
        this.currentRecord = new EmpcExptRecord();
        this.fileId = null;
        this.fileName = null;
        this.fileButtonHandler = (EventListener)new EventListener() {
            public void onEvent(final Event event) throws Exception {
                Connection c = null;
                String err = null;
                try {
                    FileViewCtrl.this.fileId = (Long)event.getTarget().getAttribute("fileId");
                    FileViewCtrl.this.fileName = (String)event.getTarget().getAttribute("fileName");
                    c = ConnectionPool.getConnection(FileViewCtrl.this.un, FileViewCtrl.this.pwd, FileViewCtrl.this.alias);
                    err = FileService.HUMO_PROCESSING(c, FileViewCtrl.this.fileId.toString());
                    if (err == null) {
                        FileViewCtrl.this.timer.start();
                        FileViewCtrl.access$4(FileViewCtrl.this, "\u041f\u0440\u043e\u0432\u043e\u0434\u043a\u0430 \u0434\u043b\u044f \u0444\u0430\u0439\u043b\u0430 " + FileViewCtrl.this.fileName + " \u043d\u0430\u0447\u0430\u043b\u0441\u044f!");
                    }
                    else {
                        FileViewCtrl.this.timer.start();
                        FileViewCtrl.access$4(FileViewCtrl.this, "\u041f\u0440\u043e\u0432\u043e\u0434\u043a\u0430 \u0434\u043b\u044f \u0444\u0430\u0439\u043b\u0430 " + FileViewCtrl.this.fileName + " \u043d\u0430\u0447\u0430\u043b\u0441\u044f");
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                    ISLogger.getLogger().error((Object)CheckNull.getPstr(e));
                    if (c != null) {
                        try {
                            c.close();
                        }
                        catch (SQLException e2) {
                            ISLogger.getLogger().error((Object)e2);
                        }
                    }
                    return;
                }
                finally {
                    if (c != null) {
                        try {
                            c.close();
                        }
                        catch (SQLException e2) {
                            ISLogger.getLogger().error((Object)e2);
                        }
                    }
                }
                if (c != null) {
                    try {
                        c.close();
                    }
                    catch (SQLException e2) {
                        ISLogger.getLogger().error((Object)e2);
                    }
                }
            }
        };
        this.fileButtonExcel = (EventListener)new EventListener() {
            public void onEvent(final Event event) throws Exception {
                FileViewCtrl.this.timer.start();
            }
        };
        this.recordButtonHandler = (EventListener)new EventListener() {
            public void onEvent(final Event event) throws Exception {
                Connection c = null;
                EXPT_record record = null;
                EmpcExptRecord rec = null;
                final Long recordId = (Long)event.getTarget().getAttribute("recordId");
                record = FileService.getEXPT_record(recordId);
                final Expt_records_processing processing = new Expt_records_processing();
                c = ConnectionPool.getConnection(FileViewCtrl.this.un, FileViewCtrl.this.pwd, FileViewCtrl.this.alias);
                processing.load_config_map(FileViewCtrl.this.un, FileViewCtrl.this.pwd, FileViewCtrl.this.alias, c);
                processing.load_branch(c);
                processing.close();
                ((Listitem)event.getTarget().getAttribute("item")).getChildren().clear();
                rec = FileService.getEmpcExptRecord(record.getId());
                ((Listitem)event.getTarget().getAttribute("item")).setValue((Object)rec);
                ((Listitem)event.getTarget().getAttribute("item")).appendChild((Component)new Listcell(""));
                ((Listitem)event.getTarget().getAttribute("item")).setStyle("background-color:#99CC33; height:40px;");
            }
        };
    }
    
    private EventListener fileButtonHandler1 = new EventListener() {

		public void onEvent(Event event) throws Exception {
			Connection c = null;
			String err = null;
			try {
				fileId = (Long) event.getTarget().getAttribute("fileId");
				fileName = (String) event.getTarget().getAttribute("fileName");
				c = ConnectionPool.getConnection(un, pwd, alias);
				err = FileService.HUMO_PROCESSING1(c, fileId.toString());
				System.out.println(un + " " + " " + pwd + " " + alias);

				if (err == null) {
					alert("Проводка для файла " + fileName + " начался!");
				} else {
					alert(err);
				}

			} catch (Exception e) {
				e.printStackTrace();
				ISLogger.getLogger().error(CheckNull.getPstr(e));
			} finally {
				if (c != null)
					try {
						c.close();
					} catch (SQLException e) {
						ISLogger.getLogger().error(e);
					}
			}
			refreshModel(_startPageNumber);
		}
	};

	private EventListener fileButtonHandler2 = new EventListener() {

		public void onEvent(Event event) throws Exception {
			Connection c = null;
			String err = null;
			try {
				fileId = (Long) event.getTarget().getAttribute("fileId");
				fileName = (String) event.getTarget().getAttribute("fileName");
				c = ConnectionPool.getConnection(un, pwd, alias);
				err = FileService.HUMO_PROCESSING2(c, fileId.toString());
				System.out.println(un + " " + " " + pwd + " " + alias);

				if (err == null) {
					alert("Проводка для файла " + fileName + " начался!");
				} else {
					alert(err);
				}

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
			refreshModel(_startPageNumber);
		}
	};

	private EventListener fileButtonHandler3 = new EventListener() {

		public void onEvent(Event event) throws Exception {
			Connection c = null;
			String err = null;
			try {
				fileId = (Long) event.getTarget().getAttribute("fileId");
				fileName = (String) event.getTarget().getAttribute("fileName");
				c = ConnectionPool.getConnection(un, pwd, alias);
				err = FileService.HUMO_PROCESSING3(c, fileId.toString());
				System.out.println(un + " " + " " + pwd + " " + alias);

				if (err == null) {
					alert("Проводка для файла " + fileName + " начался!");
				} else {
					alert(err);
				}
			} catch (Exception e) {
				e.printStackTrace();
				ISLogger.getLogger().error(CheckNull.getPstr(e));
			} finally {
				if (c != null)
					try {
						c.close();
					} catch (SQLException e) {
						ISLogger.getLogger().error(e);
					}
			}
			refreshModel(_startPageNumber);
		}
	};

	
    public void doAfterCompose(final Component comp) throws Exception {
        super.doAfterCompose(comp);
        (this.binder = new AnnotateDataBinder(comp)).bindBean("current", (Object)this.current);
        this.binder.bindBean("currentRecord", (Object)this.currentRecord);
        this.binder.loadAll();
        final String[] parameter = (String[])this.param.get("ht");
        this.branch = (String)this.session.getAttribute("branch");
        this.un = (String)this.session.getAttribute("un");
        this.pwd = (String)this.session.getAttribute("pwd");
        this.alias = (String)this.session.getAttribute("alias");
        this.fileDirectory = FileService.getTietoDirectory(Constants.ID_TIETO_DIRECTORY);
        if (parameter != null) {
            this._pageSize = Integer.parseInt(parameter[0]) / 44;
            this.dataGrid.setRows(Integer.parseInt(parameter[0]) / 44);
        }
        this.dataGrid.setItemRenderer((ListitemRenderer)new ListitemRenderer() {
            public void render(final Listitem row, final Object data) throws Exception {
                final File pFile = (File)data;
                row.setValue((Object)pFile);
                FileViewCtrl.this.fillRow(row, pFile);
                final long fileState = FileService.getFileState(pFile.getId());
                final Listcell listcell = new Listcell();
                
                /*if (fileState == Constants.FILE_ACCEPTED || fileState == Constants.FILE_PROCESSED_WITH_ERRORS) {
                    
                    final Button button = new Button();
                    button.setImage("/images/file.png");
                    button.setLabel("\u041e\u0431\u0440\u0430\u0431\u043e\u0442\u043a\u0430/");
                    button.setAttribute("fileId", (Object)pFile.getId());
                    button.setAttribute("fileName", (Object)pFile.getFile_name().substring(pFile.getFile_name().lastIndexOf("\\") + 1));
                    button.setAttribute("row", (Object)row);
                    button.addEventListener("onClick", FileViewCtrl.this.fileButtonHandler);
                    listcell.appendChild((Component)button);

                }*/
                
                if (FileService.getFileType(pFile.getId()) == 2L) // expt
				{
					
					if (fileState == Constants.FILE_ACCEPTED
							|| fileState == Constants.FILE_PROCESSED_WITH_ERRORS
							|| fileState == Constants.FILE_FIRST_STEP_WITH_ERRORS
							|| fileState == Constants.FILE_SECOND_PARTIALLY_PROCESSED) {

						Button button = new Button();
						button.setImage("/images/file.png");
						button.setLabel("Обработка");
						button.setAttribute("fileId", pFile.getId());
						button.setAttribute(
								"fileName",
								pFile.getFile_name()
										.substring(
												pFile.getFile_name()
														.lastIndexOf("\\") + 1));
						button.setAttribute("row", row);
						button.addEventListener("onClick", fileButtonHandler1);

						listcell.appendChild(button);
					}
					
				} else if (FileService.getFileType(pFile.getId()) == 1L) // b-fayl
				{
					
					if (fileState == Constants.FILE_ACCEPTED) {

						Button button = new Button();
						button.setImage("/images/file.png");
						button.setLabel("Обработка-1");
						button.setAttribute("fileId", pFile.getId());
						button.setAttribute( "fileName",	pFile.getFile_name().substring(	pFile.getFile_name().lastIndexOf("\\") + 1));
						button.setAttribute("row", row);
						button.addEventListener("onClick", fileButtonHandler1);

						listcell.appendChild(button);

					} else if (fileState == Constants.FILE_PROCESSED_FIRST_STEP) {

						Button button = new Button();
						button.setImage("/images/file.png");
						button.setLabel("Обработка-2");
						button.setAttribute("fileId", pFile.getId());
						button.setAttribute( "fileName",	pFile.getFile_name().substring(	pFile.getFile_name().lastIndexOf("\\") + 1));
						button.setAttribute("row", row);
						button.addEventListener("onClick", fileButtonHandler2);

						listcell.appendChild(button);

					} else if (fileState == Constants.FILE_FIRST_STEP_WITH_ERRORS) {

						Button button = new Button();
						button.setImage("/images/file.png");
						button.setLabel("Обработка-1");
						button.setAttribute("fileId", pFile.getId());
						button.setAttribute( "fileName",	pFile.getFile_name().substring(	pFile.getFile_name().lastIndexOf("\\") + 1));
						button.setAttribute("row", row);
						button.addEventListener("onClick", fileButtonHandler1);
						listcell.appendChild(button);

						Label lab = new Label("   ___   ");
						listcell.appendChild(lab);
                    	
                    	Button button2 = new Button();
						button2.setImage("/images/file.png");
						button2.setLabel("Обработка-2");
						button2.setAttribute("fileId", pFile.getId());
						button2.setAttribute("fileName",pFile.getFile_name().substring(	pFile.getFile_name().lastIndexOf("\\") + 1));
						button2.setAttribute("row", row);
						button2.addEventListener("onClick", fileButtonHandler2);
						listcell.appendChild(button2);
						
						Label lab2 = new Label("   ___   ");
						listcell.appendChild(lab2);

						Button button3 = new Button();
						button3.setImage("/images/file.png");
						button3.setLabel("Обработка-3");
						button3.setAttribute("fileId", pFile.getId());
						button3.setAttribute("fileName",	pFile.getFile_name().substring(	pFile.getFile_name().lastIndexOf("\\") + 1));
						button3.setAttribute("row", row);
						button3.addEventListener("onClick", fileButtonHandler3);
						listcell.appendChild(button3);

						
					} else if (  fileState == Constants.FILE_PROCESSED_SECOND_STEP ) {

						Button button3 = new Button();
						button3.setImage("/images/file.png");
						button3.setLabel("Обработка-3");
						button3.setAttribute("fileId", pFile.getId());
						button3.setAttribute("fileName",	pFile.getFile_name().substring(	pFile.getFile_name().lastIndexOf("\\") + 1));
						button3.setAttribute("row", row);
						button3.addEventListener("onClick", fileButtonHandler3);
						listcell.appendChild(button3);

					} else if (fileState == Constants.FILE_SECOND_PARTIALLY_PROCESSED	) {

						Button button = new Button();
						button.setImage("/images/file.png");
						button.setLabel("Обработка-1");
						button.setAttribute("fileId", pFile.getId());
						button.setAttribute( "fileName",	pFile.getFile_name().substring(	pFile.getFile_name().lastIndexOf("\\") + 1));
						button.setAttribute("row", row);
						button.addEventListener("onClick", fileButtonHandler1);
						listcell.appendChild(button);

						Label lab = new Label("   ___   ");
						listcell.appendChild(lab);
                    	
						
						Button button2 = new Button();
						button2.setImage("/images/file.png");
						button2.setLabel("Обработка-2");
						button2.setAttribute("fileId", pFile.getId());
						button2.setAttribute("fileName",pFile.getFile_name().substring(	pFile.getFile_name().lastIndexOf("\\") + 1));
						button2.setAttribute("row", row);
						button2.addEventListener("onClick", fileButtonHandler2);
						listcell.appendChild(button2);
						
						Label lab2 = new Label("   ___   ");
						listcell.appendChild(lab2);

						Button button3 = new Button();
						button3.setImage("/images/file.png");
						button3.setLabel("Обработка-3");
						button3.setAttribute("fileId", pFile.getId());
						button3.setAttribute("fileName",	pFile.getFile_name().substring(	pFile.getFile_name().lastIndexOf("\\") + 1));
						button3.setAttribute("row", row);
						button3.addEventListener("onClick", fileButtonHandler3);
						listcell.appendChild(button3);


					} else if (fileState == Constants.FILE_PROCESSED_WITH_ERRORS	) {

						Button button = new Button();
						button.setImage("/images/file.png");
						button.setLabel("Обработка-1");
						button.setAttribute("fileId", pFile.getId());
						button.setAttribute( "fileName",	pFile.getFile_name().substring(	pFile.getFile_name().lastIndexOf("\\") + 1));
						button.setAttribute("row", row);
						button.addEventListener("onClick", fileButtonHandler1);
						listcell.appendChild(button);

						Label lab = new Label("   ___   ");
						listcell.appendChild(lab);
                    						
						Button button2 = new Button();
						button2.setImage("/images/file.png");
						button2.setLabel("Обработка-2");
						button2.setAttribute("fileId", pFile.getId());
						button2.setAttribute("fileName",pFile.getFile_name().substring(	pFile.getFile_name().lastIndexOf("\\") + 1));
						button2.setAttribute("row", row);
						button2.addEventListener("onClick", fileButtonHandler2);
						listcell.appendChild(button2);
						
						Label lab2 = new Label("   ___   ");
						listcell.appendChild(lab2);

						Button button3 = new Button();
						button3.setImage("/images/file.png");
						button3.setLabel("Обработка-3");
						button3.setAttribute("fileId", pFile.getId());
						button3.setAttribute("fileName",	pFile.getFile_name().substring(	pFile.getFile_name().lastIndexOf("\\") + 1));
						button3.setAttribute("row", row);
						button3.addEventListener("onClick", fileButtonHandler3);

						listcell.appendChild(button3);
							
					};

				} // end b-file
                
                if (fileState == Constants.FILE_PROCESSED) {
                   
                    final Button button = new Button();
                    button.setImage("/images/file.png");
                    button.setLabel("\u041a\u043b\u0438\u0440\u0438\u043d\u0433/");
                    button.setAttribute("fileId", (Object)pFile.getId());
                    button.setAttribute("row", (Object)row);
                    button.addEventListener("onClick", (EventListener)new EventListener() {
                        public void onEvent(final Event event) throws Exception {
                            final Button btn = (Button)event.getTarget();
                            final Long id = Long.parseLong(new StringBuilder().append(btn.getAttribute("fileId")).toString());
                            final String fileName = (String)event.getTarget().getAttribute("fileName");
                            String error="";
                            //final String error = FileService.getAmountAgro(id, FileViewCtrl.this.un, FileViewCtrl.this.pwd, FileViewCtrl.this.alias, fileName);
                            if (FileService.getBankType(branch).equals("004") )
							{	
								error = FileService.getAmountAgro(id,un,pwd,alias,fileName);  //AGRO
							} else {
							    error = FileService.getAmount(id, un, pwd,	alias, fileName); // Boshqa bankla
							}
                            
                            FileViewCtrl.access$4(FileViewCtrl.this, error);
                            FileViewCtrl.access$4(FileViewCtrl.this, fileName);
                            System.out.println("fileName: " + fileName);
                            FileViewCtrl.this.refreshModel(FileViewCtrl.this._startPageNumber);
                        }
                    });
                    listcell.appendChild((Component)button);

                }
                
                row.appendChild(listcell);
                
            }
        });
        final List<String> str = (List<String>)new ListModelList();
        str.add("");
        this.humoFileId.setModel((ListModel)str);
        this.humoFileId.setModel((ListModel)new ListModelList((Collection)FileService.getState(this.alias)));
        this.refreshModel(this._startPageNumber);
    }
    
    private void fillRow(final Listitem row, final File pFile) {
        String fileName = pFile.getFile_name();
        fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
        row.setValue((Object)pFile);
        row.setStyle("height:40px;");
        row.appendChild((Component)new Listcell(pFile.getId().toString()));
        row.appendChild((Component)new Listcell(fileName));
        row.appendChild((Component)new Listcell(this.df.format(pFile.getFile_date())));
        row.appendChild((Component)new Listcell(pFile.getFile_state_name()));
        row.appendChild((Component)new Listcell(""));
    }
    
    public void onPaging$filePaging(final ForwardEvent event) {
        final PagingEvent pe = (PagingEvent)event.getOrigin();
        this.refreshModel(this._startPageNumber = pe.getActivePage());
    }
    
    private void refreshModel(final int activePage) {
        this.filePaging.setPageSize(this._pageSize);
        this.model = new PagingListModel(activePage, this._pageSize, this.filter, "");
        this._totalSize = this.model.getTotalSize(this.filter, this.session.getAttribute("alias").toString());
        this.filePaging.setTotalSize(this._totalSize);
        this.dataGrid.setModel((ListModel)this.model);
        if (this.model.getSize() > 0) {
            this.current = (File)this.model.getElementAt(0);
            this.sendSelEvt();
        }
    }
    
    public File getCurrent() {
        return this.current;
    }
    
    public void setCurrent(final File current) {
        this.current = current;
    }
    
    public EmpcExptRecord getCurrentRecord() {
        return this.currentRecord;
    }
    
    public void setCurrentRecord(final EmpcExptRecord currentRecord) {
        this.currentRecord = currentRecord;
    }
    
    public void onDoubleClick$dataGrid$grd() {
        this.fileRecords.setVisible(true);
        final Long empcId = this.current.getId();
        this.fileRecords$fileRecordsListbox.setModel((ListModel)new BindingListModelList((List)FileService.getFileRecords(empcId, FileService.getFileType(empcId)), true));
        this.fileRecords$fileRecordsListbox.setItemRenderer((ListitemRenderer)new ListitemRenderer() {
            public void render(final Listitem item, final Object data) throws Exception {
                final HumoFileRecords record = (HumoFileRecords)data;
                long recordState = 1L;
                item.setValue((Object)record);
                recordState = FileService.getRecordState(record.getId());
                FileViewCtrl.this.fillItemHumo(item, record, empcId);
                if (recordState == Constants.RECORD_PROCESSED) {
                    item.appendChild((Component)new Listcell(""));
                }
            }
        });
    }
    
    public void onDoubleClick$fileRecordsListbox$fileRecords() {
        EmpcExptRecord record = null;
        record = FileService.getEmpcExptRecord(this.currentRecord.getId());
        final String accntAmt = FileService.customFormat("###,###,###,###,###.00", record.getAccnt_amt() / 100.0);
        final String tranAmount = FileService.customFormat("###,###,###,###,###.00", record.getTran_amt() / 100.0);
        this.fileRecordFields.setVisible(true);
        this.fileRecordFields$id.setValue(record.getId().toString());
        this.fileRecordFields$empc_file_id.setValue(record.getEmpc_file_id().toString());
        this.fileRecordFields$state_id.setValue(record.getState_id().toString());
        this.fileRecordFields$record_type.setValue(record.getRecord_type());
        this.fileRecordFields$line_number.setValue(record.getLine_number().toString());
        this.fileRecordFields$client.setValue(record.getClient());
        this.fileRecordFields$card_acct.setValue(record.getCard_acct());
        this.fileRecordFields$accnt_ccy.setValue(record.getAccnt_ccy());
        this.fileRecordFields$card.setValue(record.getCard());
        this.fileRecordFields$slip_nr.setValue(record.getSlip_nr());
        this.fileRecordFields$ref_number.setValue(record.getRef_number());
        this.fileRecordFields$tran_date_time.setValue(record.getTran_date_time().toString());
        this.fileRecordFields$rec_date.setValue(record.getRec_date().toString());
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
    }
    
    private void fillItemHumo(final Listitem item, final HumoFileRecords record, final long empcId) {
        item.setValue((Object)record);
        item.setStyle("height:40px;");
        item.appendChild((Component)new Listcell(record.getId().toString()));
        item.appendChild((Component)new Listcell(record.getMerchant()));
        item.appendChild((Component)new Listcell(record.getAbvr_name()));
        item.appendChild((Component)new Listcell(record.getCard()));
        item.appendChild((Component)new Listcell(record.getTranz_acct()));
        item.appendChild((Component)new Listcell(record.getTermId()));
        item.appendChild((Component)new Listcell(record.getMcc()));
        item.appendChild((Component)new Listcell(record.getTran_type()));
        item.appendChild((Component)new Listcell(record.getSumma()));
        item.appendChild((Component)new Listcell(record.getTran_amt2()));
        item.appendChild((Component)new Listcell(record.getTran_date_time()));
        item.appendChild((Component)new Listcell(record.getErrorText()));
        item.appendChild((Component)new Listcell(record.getState()));
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
    
    public void onClick$btnSearch() {
        this.filter = new FileFilter();
        final String fullFileName = String.valueOf(this.fileDirectory) + this.searchFileName.getValue().toUpperCase();
        if (!this.searchId.getValue().equals("")) {
            this.filter.setId(Long.valueOf(this.searchId.getValue()));
        }
        if (!this.searchFileName.getValue().equals("")) {
            this.filter.setFile_name(fullFileName);
        }
        if (this.searchDate.getValue() != null) {
            this.filter.setFile_date(this.searchDate.getValue());
        }
        if (this.humoFileId.getValue() != null && !this.humoFileId.getValue().equals("")) {
            this.filter.setState_id(Integer.valueOf(this.humoFileId.getValue()));
        }
        this.refreshModel(this._startPageNumber);
    }
    
    public void onClick$btnClearSearch() {
        this.searchId.setValue("");
        this.searchFileName.setValue("");
        this.searchDate.setValue((Date)null);
        this.humoFileId.setValue("");
    }
    
    public void onTimer$timer(final Event e) {
        Connection c = null;
        try {
            c = ConnectionPool.getConnection(this.un, this.pwd, this.alias);
            System.out.println("HUMO timer!");
            ISLogger.getLogger().error((Object)"HUMO timer!");
            final String fileType = FileService.getFileType(this.fileId).toString();
            final String fileIdStr = this.fileId.toString();
            if (fileType.equals("1")) {
                final String countState1 = FileService.getCountBState1(c, fileIdStr);
                if (!countState1.equals("0")) {
                    this.filemain$common.setValue(FileService.getCount\u0421ommonB(c, fileIdStr));
                    this.filemain$success.setValue(FileService.getCountSuccessB(c, fileIdStr));
                    this.filemain$error.setValue(FileService.getCountErrorB(c, fileIdStr));
                }
                else {
                    this.timer.stop();
                    this.alert("\u041f\u0440\u043e\u0432\u043e\u0434\u043a\u0430 \u0437\u0430\u043a\u043e\u043d\u0447\u0438\u043b\u043e\u0441\u044c");
                    this.refreshModel(this._startPageNumber);
                }
            }
            else {
                final String countState1 = FileService.getCountEXPTStat1(c, fileIdStr);
                if (!countState1.equals("0")) {
                    this.filemain$fileId.setValue(this.fileId.toString());
                    this.filemain$fileName.setValue(this.fileName);
                    this.filemain$common.setValue(FileService.getCountCommonEXPT(c, fileIdStr));
                    this.filemain$success.setValue(FileService.getCountSuccessEXPT(c, fileIdStr));
                    this.filemain$error.setValue(FileService.getCountErrorEXPT(c, fileIdStr));
                }
                else {
                    this.timer.stop();
                    this.alert("\u041f\u0440\u043e\u0432\u043e\u0434\u043a\u0430 \u0437\u0430\u043a\u043e\u043d\u0447\u0438\u043b\u043e\u0441\u044c");
                    this.refreshModel(this._startPageNumber);
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            ISLogger.getLogger().error((Object)CheckNull.getPstr(ex));
            if (c != null) {
                try {
                    c.close();
                }
                catch (SQLException exc) {
                    ISLogger.getLogger().error((Object)exc);
                }
            }
            return;
        }
        finally {
            if (c != null) {
                try {
                    c.close();
                }
                catch (SQLException exc) {
                    ISLogger.getLogger().error((Object)exc);
                }
            }
        }
        if (c != null) {
            try {
                c.close();
            }
            catch (SQLException exc) {
                ISLogger.getLogger().error((Object)exc);
            }
        }
    }
    
    public void onFocus$customermain() {
        this.refreshModel(this._startPageNumber);
    }
    
    static /* synthetic */ void access$4(final FileViewCtrl fileViewCtrl, final String s) {
        fileViewCtrl.alert(s);
    }
}
