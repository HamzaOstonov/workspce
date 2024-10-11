package com.is.tieto_globuz.tieto;

import globus.issuing_v_01_02_xsd.OperationConnectionInfo;
import globus.issuing_v_01_02_xsd.OperationResponseInfo;
import globus.issuing_v_01_02_xsd.RowType_AddCardToStopList_Request;
import globus.issuing_v_01_02_xsd.RowType_RemoveCardFromStop_Request;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.type.OrientationEnum;

import org.python.antlr.PythonParser.else_clause_return;
import org.python.antlr.PythonParser.return_stmt_return;
import org.zkoss.util.media.AMedia;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zkplus.databind.BindingListModelList;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Decimalbox;
import org.zkoss.zul.Div;
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
import org.zkoss.zul.Paging;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;
import org.zkoss.zul.event.PagingEvent;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.tieto_globuz.Utils;
import com.is.tieto_globuz.customer.Customer;
import com.is.tieto_globuz.customer.CustomerFilter;
import com.is.tieto_globuz.customer.CustomerService;
import com.is.tieto_globuz.customer.CustomerService.link;
import com.is.trpay.TrPay;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import com.is.utils.NilProvider;
import com.is.utils.RefCBox;
import com.is.utils.Res;

public class TclientViewCtrl extends GenericForwardComposer
{

	private static final long serialVersionUID = 7867644115197743649L;
	private globus.IssuingWS.IssuingPortProxy issuingPortProxy;
	private static NilProvider np = null;
	private GeneralInfo generalInfo = new GeneralInfo();
	private Window paywnd, blockwnd, printwnd, addwnd, packagePayments;
	private Iframe printwnd$rpframe;
	private Textbox paywnd$amnt;
	private Div frm;
	private Listbox dataGrid, accGrid, paywnd$payGrid, uploadData, packagePayments$paymentListBox;
//	private Label paywnd$add_currency_type;
	private Div grd;
	private Grid addgrd, frmgrd, fgrd;
//	private Grid lfrmgrd, mfrmgrd, rfrmgrd;
//	private Toolbarbutton paywnd$btn_block, paywnd$btn_unblock;
	private Toolbarbutton paywnd$lock, paywnd$btn_pay, packagePayments$btn_close_packagePayments, tbtn_add_excel;
//	paywnd$fbt, , paywnd$application;
	
//	private Toolbar paywnd$pay_tlb;
//	private Textbox client, bank_c, client_b, cl_type, cln_cat, rec_date, f_names, surname, title, m_name, b_date, r_street, r_city, r_cntry, usrid, ctime, status, search_name, sex, serial_no, doc_since, issued_by, status_change_date,
    private Textbox blockwnd$txtstopcauses, paywnd$search_name, paywnd$address,
            packagePayments$employeeId, packagePayments$account, packagePayments$cardNumber,
            packagePayments$amount;
	private Decimalbox acc, document_id;
	private Textbox kod_org,
//		aclient, abank_c, aclient_b, acl_type, acln_cat, arec_date, af_names, asurname, atitle, am_name, ab_date, ar_street, ar_city, ar_cntry, ausrid, actime, astatus, asearch_name, asex, aserial_no, adoc_since, aissued_by,
//		astatus_change_date,
		paywnd$curacc;
	private Textbox fclient, purpose_txt, txt_branch, fclient_b,
//		fbank_c, fclient_b, fcl_type, fcln_cat, 
		ff_names, fsurname,
//		ftitle, fm_name, fr_street, fr_city, fr_cntry, fusrid, fctime, fstatus, fsearch_name, fsex, fserial_no, fdoc_since, fissued_by, fstatus_change_date, fcard,
		paywnd$inc_ord_num;
//	private Paging tclientPaging;
//	private Datebox fb_date, frec_date;
	private RefCBox paywnd$scurracc, blockwnd$sstopcauses, addwnd$sproduct, paywnd$list_amnt;
	private Combobox packagePayments$excelList, packagePayments$stateList;
	private int _pageSize = 10;
	private Paging paging;
	private int _startPageNumber = 0;
	public PaymentFilter paymentFilter;
	private Datebox packagePayments$dateSince;
	private Datebox packagePayments$dateTill;
//	private int _totalSize = 0;
//	private boolean _needsTotalSizeUpdate = true;
	private String 
//		un, pwd,
		branch, alias;
	private int uid;
	private CustomerFilter filter = new CustomerFilter();
	private Customer current = new Customer();
	private Customer customer = new Customer();
	private CardInfo cardInfo = new CardInfo();
	Res result = null;
//	private CustomerFilter customerFilter = new CustomerFilter();
	private AccInfo accinfo = new AccInfo();
//	private com.is.tieto_globuz.customer.PagingListModel CustPagingListModel;
	
	private com.is.tieto_globuz.customer.PagingListModel model = null;
	private com.is.tieto_globuz.tieto.PagingListModel paymentsModel = null;
	ListModelList lmodel = null;
	private AnnotateDataBinder binder;
	private SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy", new Locale("ru"));
//	private SimpleDateFormat expdf = new SimpleDateFormat("yyyy-MM-dd", new Locale("ru"));
    private Button packagePayments$btn_deleteExcel, packagePayments$btnReplenishCards, packagePayments$btn_payAgain,
            packagePayments$btn_paymentSearch, packagePayments$btn_payWriteOff, packagePayments$btnRefresh;
//	private DecimalFormat dmf = new DecimalFormat("0.00##");
	private Label packagePayments$doneCounter;
	private globus.IssuingWS.IssuingPortProxy pp = null;
	private static HashMap<String, String> _tstopCauses = new HashMap<String, String>();
	private String divide = "100";
    private BigDecimal divideBigDecimal = new BigDecimal(divide);
    private AccrualEmployee currentPayment = new AccrualEmployee();
    private PacketPayment currentExcel = new PacketPayment();
    private String filename;
    private String selectedState;
    private List<String> list = new ArrayList<String>();
    private Label uploadCounter;
	private String un;
	private String pwd;
	private String curip;
	private HashMap<String, Integer> stateMap = new HashMap<String, Integer>();
    private HashMap<Integer, String> stateMapText = new HashMap<Integer, String>();
	private boolean paymentState, packetPaymentState;
	private boolean payWriteOffState;
	private Checkbox packagePayments$executedCheck;
	private Listbox packagePayments$uploadedExcels;
	private NumberFormat nf = NumberFormat.getInstance();
	private ObjectMapper objectMapper = new ObjectMapper();
//	private String EMPC_BANK_C, EMPC_GROUPC, EMPC_BINCOD;
	
	public TclientViewCtrl()
	{
		super('$', false, false);
		paymentFilter = new PaymentFilter();
		
		list.add(" ");
		list.add("Доступно к зачислению на счёт");
		list.add("Зачислено на счёт. Доступно к зачислению на карту");
		list.add("Ошибка пополнения");
		list.add("Успешно зачислено на карту");
		list.add("Успешно списано");
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception
	{
		super.doAfterCompose(comp);
		binder = new AnnotateDataBinder(comp);
	
		binder.bindBean("current", this.current);
		binder.bindBean("cardinfo", this.cardInfo);
		binder.bindBean("currentPayment", currentPayment);
		binder.bindBean("currentExcel", currentExcel);
		binder.loadAll();
		
		String[] parameter = (String[]) param.get("ht");
		if (parameter != null)
		{
			_pageSize = Integer.parseInt(parameter[0]) / 60;
			//dataGrid.setRows(Integer.parseInt(parameter[0]) / 60);
		}
		
		nf.setGroupingUsed(false);
//		un = (String) session.getAttribute("un");
//		pwd = (String) session.getAttribute("pwd");
		uid = (Integer) session.getAttribute("uid");
		
		this.uid = (Integer) this.session.getAttribute("uid");
		this.un = (String) this.session.getAttribute("un");
		this.pwd = (String) this.session.getAttribute("pwd");
		this.branch = (String) this.session.getAttribute("branch");
		this.alias = (String) this.session.getAttribute("alias");
		this.curip = (String) session.getAttribute("curip");
		
//		EMPC_BANK_C = Utils.getValue("EMPC_BANK_C");
//		EMPC_GROUPC = Utils.getValue("EMPC_GROUPC");
//		EMPC_BINCOD = Utils.getValue("EMPC_BINCOD");
		try { 
			TclientService.initSettings(); 
		} catch (Exception e) { 
			if(e.getMessage()!=null && !e.getMessage().equals("")){
				alert(e.getMessage());
				return;
			} else {
				alert(CheckNull.getPstr(e));
			}
		}			
//		curip = (String) session.getAttribute("curip");
		_tstopCauses = TclientService.getHTstopCauses(alias);
		if(!TclientService.getSettings().get(branch).getBank_c().equals("25")){
			packagePayments$btn_payWriteOff.setVisible(false); }	
		try
		{
			issuingPortProxy = initNp(issuingPortProxy, alias);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			Messagebox.show("[Coonection Problem]=>" + e.getLocalizedMessage(),
				e.getMessage(), Messagebox.RETRY | Messagebox.CANCEL, Messagebox.ERROR,
				new EventListener()
					{
						@Override
						public void onEvent(Event event) throws Exception
						{
							int answer = (Integer) event.getData();
							if (answer == Messagebox.RETRY)
							{
								issuingPortProxy = initNp(issuingPortProxy, alias);
							}
							else return;
						}
					}
				);
			
		}
		
		addwnd$sproduct.setModel((new ListModelList(com.is.utils.RefDataService.getOfrProd(alias))));
		System.out.println("1");
  		stateMap.put("Доступно к зачислению на счёт", 0);
  		stateMap.put("Доступно к зачислению на счёт", 1);
  		stateMap.put("Доступно к зачислению на счёт", 2);
  		stateMap.put("Зачислено на счёт. Доступно к зачислению на карту", 3);
  		stateMap.put("Ошибка пополнения", 5);
  		stateMap.put("Успешно зачислено на карту", 6);
  		stateMap.put("Успешно списано", 7);
        stateMapText.put(0, "Доступно к зачислению на счёт");
        stateMapText.put(1, "Доступно к зачислению на счёт");
        stateMapText.put(2, "Доступно к зачислению на счёт");
        stateMapText.put(3, "Зачислено на счёт. Доступно к зачислению на карту");
        stateMapText.put(5, "Ошибка пополнения");
        stateMapText.put(6, "Успешно зачислено на карту");
        stateMapText.put(7, "Успешно списано");
		dataGrid.setItemRenderer(new ListitemRenderer()
		{
//			@SuppressWarnings("unchecked")
			public void render(Listitem row, Object data) throws Exception
			{
				Customer pTclient = (Customer) data;
				
				String client_b = pTclient.getId_client();
				if(client_b != null && !client_b.equals("")){
					if(client_b.length() == 8 || client_b.length() == 13){
						if(client_b.length()==13) client_b = client_b.substring(5);
//						TclientService.synTietoCustomers(pTclient.getTieto_customer_id(), client_b, branch, null);
					}
				}
				row.setValue(pTclient);
				row.appendChild(new Listcell(client_b));
				row.appendChild(new Listcell(pTclient.getTieto_customer_id()));
				row.appendChild(new Listcell(pTclient.getP_first_name()));
				row.appendChild(new Listcell(pTclient.getP_family()));
				
				String listDate = pTclient.getP_birthday() == null ? "" : (df.format(pTclient.getP_birthday())).toString() ;
				row.appendChild(new Listcell(listDate));
				
			}
		});
		System.out.println("2");
		accGrid.setItemRenderer(new ListitemRenderer()
		{
//			@SuppressWarnings("unchecked")
			public void render(Listitem row, Object data) throws Exception
			{
				CardInfo cardInfo = (CardInfo) data;
				
				row.setValue(cardInfo);
				row.appendChild(new Listcell(TclientService.getRealCard(alias, cardInfo.getCARD())));
				row.appendChild(new Listcell(cardInfo.getEXPIRY()==null?"":cardInfo.getEXPIRY().substring(0, 10)));
				row.appendChild(new Listcell(cardInfo.getCARD_ACCT()));
				row.appendChild(new Listcell(cardInfo.getBank_account()));
				row.appendChild(new Listcell(cardInfo.getSTATUS().equals("0") ? "Active"
						: "Not active"));
				//row.appendChild(new Listcell(_tstopCauses.get(cardInfo.getBank_account_status())));
				System.out.println(cardInfo.getBank_account_Ccy());
				Double amt = Double.valueOf(0);
				amt = cardInfo.getACCOUNT_AVAIL_AMOUNT().doubleValue()/100 ;
				
				row.appendChild(new Listcell(amt == 0 ? "0" : customFormat("###,###,###,###,###.00", amt )));
				
			}
		});
		System.out.println("3");
		paywnd$payGrid.setItemRenderer(new ListitemRenderer()
		{
//			@SuppressWarnings("unchecked")
			public void render(Listitem row, Object data) throws Exception
			{
				Bf_globuz_trans bf_globuz_trans = (Bf_globuz_trans) data;
				
				row.setValue(bf_globuz_trans);
				
				row.appendChild(new Listcell(bf_globuz_trans.getBRANCH()));
				row.appendChild(new Listcell(bf_globuz_trans.getAccount()));
				row.appendChild(new Listcell(df.format(bf_globuz_trans.getTRDATE())));
				row.appendChild(new Listcell(TclientService.getStateDesc(bf_globuz_trans.getSTATE(), alias)));
				row.appendChild(new Listcell(bf_globuz_trans.getSUMMA()+""));
			}
		});
		
		
		packagePayments$paymentListBox.setItemRenderer((ListitemRenderer) new ListitemRenderer(){
			//@SuppressWarnings("unchecked")
			public void render(final Listitem row, final Object data) throws Exception {
				final AccrualEmployee accrualEmployee = (AccrualEmployee)data;				
				row.setValue((accrualEmployee == null) ? "" : accrualEmployee);
                row.appendChild((Component)new Listcell((accrualEmployee.getBranch() == null) ? "" : accrualEmployee.getBranch()));
                row.appendChild((Component)new Listcell((accrualEmployee.getEmployeeId() == null) ? "" : accrualEmployee.getEmployeeId()));
                row.appendChild((Component)new Listcell((accrualEmployee.getEmployeeName() == null) ? "" : accrualEmployee.getEmployeeName()));
                row.appendChild((Component)new Listcell((accrualEmployee.getEmployeeAccount() == null) ? "" : accrualEmployee.getEmployeeAccount()));
                row.appendChild((Component)new Listcell((accrualEmployee.getAmount() == null) ? "" : accrualEmployee.getAmount()));
                row.appendChild((Component)new Listcell((accrualEmployee.getOperDate() == null) ? "" : accrualEmployee.getOperDate()));
                row.appendChild((Component)new Listcell(accrualEmployee.getStateText() == null ? "" : accrualEmployee.getStateText()));
                row.appendChild((Component)new Listcell((String.valueOf(accrualEmployee.getGeneralId()) == null) ? "" : String.valueOf(accrualEmployee.getGeneralId())));
                row.appendChild((Component)new Listcell((accrualEmployee.getFileName() == null) ? "" : accrualEmployee.getFileName()));
                row.appendChild((Component)new Listcell((accrualEmployee.getReport() == null) ? "" : accrualEmployee.getReport()));
                row.appendChild((Component)new Listcell((accrualEmployee.getAccountNo() == null) ? "" : accrualEmployee.getAccountNo()));
                row.appendChild((Component)new Listcell((accrualEmployee.getRealCard() == null) ? "" : accrualEmployee.getRealCard()));
                row.appendChild((Component)new Listcell((accrualEmployee.getClient() == null) ? "" : accrualEmployee.getClient()));
                row.appendChild((Component)new Listcell((accrualEmployee.getCustomerId() == null) ? "" : accrualEmployee.getCustomerId()));
                row.appendChild((Component)new Listcell((String.valueOf(accrualEmployee.getGeneralPay()) == null) ? "" : String.valueOf(accrualEmployee.getGeneralPay())));
			}
			});
		
		packagePayments$uploadedExcels.setItemRenderer((ListitemRenderer) new ListitemRenderer() {		 
		    
            public void render(Listitem row, Object data) throws Exception {
                final PacketPayment packetPayment = (PacketPayment) data;
                row.setValue((packetPayment == null) ? "" : packetPayment);
                row.appendChild((Component) new Listcell(packetPayment.getName()));
                row.appendChild((Component) new Listcell(packetPayment.getStateText()));
               if(packetPayment.getState() == 1) {
                   row.setStyle("background-color: #808080");
                }else if(packetPayment.getState() == 4) {
                    row.setStyle("background-color: #FFFF00");
                }else if(packetPayment.getState() == 5) {
                    row.setStyle("background-color: #FF0000");
                }else if(packetPayment.getState() == 6) {
                    row.setStyle("background-color: #00FF00");
                }
            }
        });

		
		
		refreshModel(_startPageNumber);
	}
	
	public void onPaging$paging(ForwardEvent event)
	{
		PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModel(_startPageNumber);
	}
	
	private void refreshModel(int activePage)
	{
		//tclientPaging.setPageSize(_pageSize);
		//_pageSize = TclientService.getCount(filter, alias, issuingPortProxy);
		if(filter == null) filter = new CustomerFilter();
		filter.setTietoIdIsNotNull(1);
		System.out.println("cl = "+filter.getId_client());
		model = new com.is.tieto_globuz.customer.PagingListModel(activePage, _pageSize, filter, alias);
		
		dataGrid.setModel((ListModel) model);
		paging.setTotalSize(model.getTotalSize(filter, alias));
		
		/*if (_needsTotalSizeUpdate)
		{
			_totalSize = model.getTotalSize(filter, alias);
		}
		*/
		//tclientPaging.setTotalSize(_totalSize);
		if (model.getSize() > 0)
		{
			sendSelEvt();
		}
	}
	
/*	private void refreshModelPayments(int activePage)
	{
		//tclientPaging.setPageSize(_pageSize);
		//_pageSize = TclientService.getCount(filter, alias, issuingPortProxy);
		if(paymentFilter == null) paymentFilter = new PaymentFilter();
		//paymentFilter.setTietoIdIsNotNull(1);
		System.out.println("cl = "+filter.getId_client());
		paymentsModel = new com.is.tieto_globuz.tieto.PagingListModel(activePage, _pageSize, paymentFilter, alias);
		
		packagePayments$paymentListBox.setModel((ListModel) model);
		paging.setTotalSize(model.getTotalSize(paymentFilter, alias));
		
		if (model.getSize() > 0)
		{
			sendSelEvt();
		}
	}*/
	
    private static String getCond(final List<FilterField> flfields) {
        if (flfields.size() > 0) {
            return " and ";
        }
        return " where ";
    }
	
	private List<FilterField> getFilterFields(final PaymentFilter filter) {
        final List<FilterField> flfields = new ArrayList<FilterField>();
        if (!CheckNull.isEmpty(paymentFilter.getAccount())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "employee_account='"+paymentFilter.getAccount()+"'", (Object)paymentFilter.getAccount()));
        }
        if (!CheckNull.isEmpty(paymentFilter.getAmount())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "summa='"+paymentFilter.getAmount()+"'", (Object)paymentFilter.getAmount()));
        }
        if (!CheckNull.isEmpty(paymentFilter.getCardNumber())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "real_card='"+paymentFilter.getCardNumber()+"'", (Object)paymentFilter.getCardNumber()));
        }
        if (!CheckNull.isEmpty(paymentFilter.getDate())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "date_oper='"+paymentFilter.getDate()+"'", (Object)paymentFilter.getDate()));
        }
        if (!CheckNull.isEmpty(paymentFilter.getEmployee_id())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "employee_id='"+paymentFilter.getEmployee_id()+"'", (Object)paymentFilter.getEmployee_id()));
        }
        if (!CheckNull.isEmpty(paymentFilter.getState()) && !(paymentFilter.getState().equals(" "))) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "state='"+paymentFilter.getState()+"'", (Object)paymentFilter.getState()));
        }
        
        return flfields;
    }
	
	// Omitted...
	public Customer getCurrent()
	{
		return current;
	}
	
	public Customer getCustomer()
	{
		return customer;
	}
	
	public void setCustomer(Customer customer)
	{
		this.customer = customer;
	}
	
	public void setCurrent(Customer current)
	{
		this.current = current;
	}
	
	public AccInfo getAccinfo()
	{
		return accinfo;
	}
	
	public void setAccinfo(AccInfo accinfo)
	{
		this.accinfo = accinfo;
	}
	
	public CustomerFilter getFilter()
	{
		return filter;
	}
	
	public void setFilter(CustomerFilter filter)
	{
		this.filter = filter;
	}
	
	/*
	 * public void onDoubleClick$dataGrid$grd() { }
	 */

	public void onClick$btn_back()
	{
		if (frm.isVisible())
		{
			frm.setVisible(false);
			grd.setVisible(true);
			// btn_back.setImage("/images/file.png");
			// btn_back.setLabel(Labels.getLabel("back"));
		}// else onDoubleClick$dataGrid$grd();
	}
	
	public void onClick$btn_first()
	{
		dataGrid.setSelectedIndex(0);
		sendSelEvt();
	}
	
	public void onClick$btn_last()
	{
		dataGrid.setSelectedIndex(model.getSize() - 1);
		sendSelEvt();
	}
	
	public void onClick$btn_prev()
	{
		if (dataGrid.getSelectedIndex() != 0)
		{
			dataGrid.setSelectedIndex(dataGrid.getSelectedIndex() - 1);
			sendSelEvt();
		}
	}
	
	public void onClick$btn_next()
	{
		if (dataGrid.getSelectedIndex() != (model.getSize() - 1))
		{
			dataGrid.setSelectedIndex(dataGrid.getSelectedIndex() + 1);
			sendSelEvt();
		}
	}
	
	private void sendSelEvt()
	{
		/*
		 * if (dataGrid.getSelectedIndex()==0){ btn_first.setDisabled(true);
		 * btn_prev.setDisabled(true); }else{ btn_first.setDisabled(false);
		 * btn_prev.setDisabled(false); }
		 * if(dataGrid.getSelectedIndex()==(model.getSize()-1)){
		 * btn_next.setDisabled(true); btn_last.setDisabled(true); }else{
		 * btn_next.setDisabled(false); btn_last.setDisabled(false); }
		 * SelectEvent evt = new SelectEvent("onSelect",
		 * dataGrid,dataGrid.getSelectedItems()); Events.sendEvent(evt);
		 */
	}
	
	public void onClick$btn_add()
	{
		// onDoubleClick$dataGrid$grd();
		frmgrd.setVisible(false);
		addgrd.setVisible(true);
		fgrd.setVisible(false);
	}
	
	public void onClick$btn_search()
	{
		// onDoubleClick$dataGrid$grd();
		frmgrd.setVisible(false);
		addgrd.setVisible(false);
		fgrd.setVisible(true);
	}
	
	public void onClick$btn_save()
	{
		try
		{
			filter = new CustomerFilter();
			filter.setTieto_customer_id(fclient.getValue());
			filter.setP_first_name(ff_names.getValue());
			filter.setP_family(fsurname.getValue());
			filter.setId_client(fclient_b.getValue());
			filter.setBranch(branch);
			refreshModel(_startPageNumber);
			SelectEvent evt = new SelectEvent("onSelect", dataGrid, dataGrid.getSelectedItems());
			Events.sendEvent(evt);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public void onClick$btn_cancel()
	{
		
		filter = new CustomerFilter();
		filter.setBranch(branch);
		CheckNull.clearForm(fgrd);
		refreshModel(_startPageNumber);
	}
	
	
/*	public void onSelect$paymentListBox$excelList(){
		if(currentPayment != null){
			System.out.println("current filename: "+currentPayment.getFilename());
			filename = currentPayment.getFilename();
		}
	}*/
	
	
/*	public void onSelect$paymentListBox$packagePayments() throws JsonProcessingException{
		ObjectMapper mapper = new ObjectMapper();
        System.out.println("CurrentPayment: "+mapper.writerWithDefaultPrettyPrinter().writeValueAsString(currentPayment));
		//System.out.println("getState: "+currentPayment.getState());
        //packagePayments$excelList.setSelectedIndex(-1);
        currentPayment.setState(stateMap.get(currentPayment.getState()));
        if(currentExcel != null) {
        //0 - ещё не созданы проводки
        //1, 2 - ошибка при создании проводок
		if(currentPayment.getState() == 0 || 
		        currentPayment.getState() == 1 || 
		                currentPayment.getState() == 2){
			 packagePayments$btn_deleteExcel.setDisabled(true);
	         packagePayments$btnReplenishCards.setDisabled(true);
			 packagePayments$btn_payWriteOff.setDisabled(true);
		 }
		
		//Проводки созданы
		 else if(currentPayment.getState() == 3){
			 packagePayments$btn_deleteExcel.setDisabled(true);
	         packagePayments$btnReplenishCards.setDisabled(true);
			 packagePayments$btn_payWriteOff.setDisabled(true);
		 }
		
		//Ошибка зачисления на карту
		 else if(currentPayment.getState() == 5){
				 packagePayments$btn_deleteExcel.setDisabled(true);
		         packagePayments$btnReplenishCards.setDisabled(true);
				 packagePayments$btn_payWriteOff.setDisabled(true);
		 }
		
		//Успешно зачислено на карту
		 else if(currentPayment.getState() == 6){
			 packagePayments$btn_deleteExcel.setDisabled(true);
	         packagePayments$btnReplenishCards.setDisabled(true);
			 packagePayments$btn_payWriteOff.setDisabled(true);
			 packagePayments$btn_payWriteOff.setDisabled(false);
			 //}
		 }
		 else if(currentPayment.getState() == 1 || currentPayment.getState() == 2){
			 packagePayments$btn_deleteExcel.setDisabled(true);
	         packagePayments$btnReplenishCards.setDisabled(true);
			 packagePayments$btn_payWriteOff.setDisabled(true);
		 }
		
		//Успешно списано
		 else if(currentPayment.getState() == 7){
			 packagePayments$btn_deleteExcel.setDisabled(true);
	         packagePayments$btnReplenishCards.setDisabled(true);
			 packagePayments$btn_payWriteOff.setDisabled(true);
	 }
        } else {
            packagePayments$btn_deleteExcel.setDisabled(true);
            packagePayments$btnReplenishCards.setDisabled(true);
            packagePayments$btn_payWriteOff.setDisabled(true);
            alert("Выберите ведомость.");
        }
	}*/
	
	public void onSelect$dataGrid() {
		if (current != null) {
			Res res = new Res();

			String F_names = current.getP_first_name() == null ? ""
					: current.getP_first_name();
			String Surname = current.getP_family() == null ? ""
					: current.getP_family();
			String M_name = current.getP_patronymic() == null ? ""
					: current.getP_patronymic();
			String Full_name = F_names + " " + Surname + " " + M_name;
			if (current.getName() == null)
				current.setName(Full_name);
			res = TclientService.check_user(alias, branch, current.getTieto_customer_id());

			if (res.getCode() == 0)
				alert(res.getName());

			List<AccInfo> accInfos = TclientService.getAccInfo(branch, current.getTieto_customer_id(), alias, issuingPortProxy);
			List<CardInfo> cardInfos = new ArrayList<CardInfo>();
			for (int i = 0; i < accInfos.size(); i++) {
				AccInfo acc = accInfos.get(i);
				for (int l = 0; l < acc.getCardlist().size(); l++) {
					CardInfo card = acc.getCardlist().get(l);
					cardInfos.add(card);
				}
			}
			CustomerService.checkCard(accInfos, issuingPortProxy, branch, alias, TclientService.getSettings());
			accGrid.setModel(new BindingListModelList(cardInfos, false));
		} else {
			ISLogger.getLogger().error("CURRENT IS NULL logger");
		}
	}
	
	public void onSelect$accGrid()
	{		
		this.cardInfo = (CardInfo) accGrid.getSelectedItem().getValue();
	}
	
	public void onDoubleClick$accGrid()
	{
		try {
			CardInfo selectedCard = (CardInfo) accGrid.getSelectedItem().getValue();
			if(!selectedCard.getSTATUS().equals("0")){
				alert("Карта неактивна!");
				return;
			}
			Res res = new Res();
			res = TclientService.check_user(alias, branch, current.getTieto_customer_id());
			if (res.getCode() == 0)
			{
				alert(res.getName());
				List<CardInfo> cardInfos = new ArrayList<CardInfo>();
				if (current.getTieto_customer_id() != null) 
				//	cardInfos = TclientService.getAccInfo(current.getClient(),alias, issuingPortProxy).get(0).getCardlist();
				accGrid.setModel(new BindingListModelList(cardInfos , false));
				return;
			}
			
			link lnk = CustomerService.get_link_tieto(current.getTieto_customer_id(), branch, alias);
			ISLogger.getLogger().error("Client = "+current.getTieto_customer_id());
			ISLogger.getLogger().error("branch = "+branch);
			if(lnk == null) {
				ISLogger.getLogger().error("lnk is null");
				alert("Не найден клиент с номером '"+current.getTieto_customer_id()+"' в филиале '"+branch+"'");
			}
			if(paywnd$curacc == null) ISLogger.getLogger().error("paywnd$curacc is null");
			paywnd$curacc.setValue(lnk.Cur_acc);
			

			System.out.println("card = "+selectedCard.getBank_account());
			ISLogger.getLogger().error("CARD FOR PAYMENT = "+selectedCard.getBank_account());
			paywnd$payGrid.setModel(new ListModelList(TclientService.getBf_globuz_trans(selectedCard.getBank_account(), branch, alias)));
			paywnd$list_amnt.setSelectedIndex(-1);
			paywnd$list_amnt.setModel((new ListModelList(TclientService.getPayments4GlobUz(branch, selectedCard.getBank_account(), alias))));
			paywnd$search_name.setValue(current.getName());
			paywnd$address.setValue(current.getP_post_address());
			paywnd$search_name.setReadonly(true);
			paywnd$address.setReadonly(true);
			paywnd$lock.setImage("/images/locked.png");
			paywnd.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			alert(CheckNull.getPstr(e));
		}
	}
	
	public void onClick$btn_cancel$paywnd()
	{
		paywnd$amnt.setValue("0.00");
		paywnd.setVisible(false);
	}
	
	public void onClick$tbtn_uploaded() throws Exception{
		
		 packagePayments.setVisible(true);
		 packagePayments$btnReplenishCards.setDisabled(true);
		 packagePayments$btn_deleteExcel.setDisabled(true);
		 packagePayments$dateSince.setValue(null);
		 packagePayments$employeeId.setValue("");
		 packagePayments$account.setValue("");
		 packagePayments$cardNumber.setValue("");
		 packagePayments$amount.setValue("");
		 packagePayments$stateList.setSelectedIndex(-1);
		 packagePayments$paymentListBox.setModel( new BindingListModelList(TclientService.getUploadedNewOnly(alias, branch),true));
		 packagePayments$uploadedExcels.setModel(new BindingListModelList(TclientService.getPacketPaymentsNewOnly(branch, alias), true));
		 packagePayments$stateList.setModel(new ListModelList(list));
	}
	
	public void onClick$btn_close_packagePayments$packagePayments(){
		packagePayments$executedCheck.setChecked(false);
		packagePayments.setVisible(false);
	}

/*	public void getUploaded(){
		Connection c = null;
		try {
			c = ConnectionPool.getConnection(alias);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	public void onClick$btn_close_report$report() {
        System.out.println("tap");
        this.packagePayments.setVisible(false);
        CheckNull.clearForm(this.addgrd);
        CheckNull.clearForm(this.fgrd);
        packagePayments$paymentListBox.getItems().clear();
    }
	
    public void onSelect$stateList$packagePayments() {
        selectedState = packagePayments$stateList.getValue();
        if (selectedState.equals(" ")) {
            packagePayments$stateList.setSelectedIndex(-1);
        }
    }

    public void onSelect$uploadedExcels$packagePayments() throws JsonProcessingException {
        System.out.println("current excel: " + objectMapper.writeValueAsString(currentExcel));
        /*
         * if(currentExcel.getState() == 0){
         * packagePayments$btn_deleteExcel.setDisabled(false);
         * packagePayments$btnReplenishCards.setDisabled(false);
         * packagePayments$btn_payWriteOff.setDisabled(true); }else
         * if(currentExcel.getState() == 1 || currentExcel.getState() == 2) {
         * packagePayments$btn_deleteExcel.setDisabled(true);
         * packagePayments$btnReplenishCards.setDisabled(false);
         * packagePayments$btn_payWriteOff.setDisabled(true); } else
         * if(currentExcel.getState() == 3){
         * packagePayments$btn_deleteExcel.setDisabled(true);
         * packagePayments$btnReplenishCards.setDisabled(false);
         * packagePayments$btn_payWriteOff.setDisabled(true); } else
         * if(currentExcel.getState() == 5 || currentExcel.getState() == 6){
         * packagePayments$btn_deleteExcel.setDisabled(true);
         * packagePayments$btnReplenishCards.setDisabled(true);
         * packagePayments$btn_payWriteOff.setDisabled(true);
         * if(currentExcel.getState() == 5){
         * packagePayments$btn_payAgain.setDisabled(false);
         * packagePayments$btn_payWriteOff.setDisabled(true); } }
         */

        if (currentExcel.getState() != 6) {
            if (currentExcel.getState() == 0) {
                packagePayments$btn_deleteExcel.setDisabled(false);
                packagePayments$btnReplenishCards.setDisabled(false);
            } else if (currentExcel.getState() == 1 || currentExcel.getState() == 19) {
                packagePayments$btn_deleteExcel.setDisabled(true);
                packagePayments$btnReplenishCards.setDisabled(true);
            } else {
                packagePayments$btn_deleteExcel.setDisabled(true);
                packagePayments$btnReplenishCards.setDisabled(false);
                packagePayments$btn_payWriteOff.setDisabled(true);
            }
        }else {
            packagePayments$btn_deleteExcel.setDisabled(true);
            packagePayments$btnReplenishCards.setDisabled(true);
        }
        packagePayments$paymentListBox.setModel(new BindingListModelList(TclientService.getUploaded(alias, branch, currentExcel.getName()),true));
    }
	
/*	public void onSelect$excelList$packagePayments() throws SQLException{
		 selectedExcel = packagePayments$uploadedExcels.getSelectedIndex();
		 if(!(selectedExcel.equals(" "))){
		 System.out.println("selectedExcel: "+selectedExcel);
		 String stateString = TclientService.checkTableState(branch, selectedExcel);
		 ISLogger.getLogger().error("state: "+stateString);
		 if(!(stateString.equals("different"))){
			int state = Integer.parseInt(stateString);
			
		 System.out.println("state: "+state);
		 if(state == 0){
			 packagePayments$btn_deleteExcel.setDisabled(false);
			 packagePayments$btn_fillAccounts.setDisabled(false);
			 packagePayments$btn_fillCards.setDisabled(true);
			 packagePayments$btn_payWriteOff.setDisabled(true);
		 }else if(state == 1 || state == 2) {
		     packagePayments$btn_deleteExcel.setDisabled(true);
             packagePayments$btn_fillAccounts.setDisabled(false);
             packagePayments$btn_fillCards.setDisabled(true);
             packagePayments$btn_payWriteOff.setDisabled(true);
		 }
		 else if(state == 3){
			 packagePayments$btn_deleteExcel.setDisabled(true);
			 packagePayments$btn_fillAccounts.setDisabled(true);
			 packagePayments$btn_fillCards.setDisabled(false);
			 packagePayments$btn_payWriteOff.setDisabled(true);
		 }
		 else if(state == 5 || state == 6){
			 packagePayments$btn_deleteExcel.setDisabled(true);
			 packagePayments$btn_fillAccounts.setDisabled(true);
			 packagePayments$btn_fillCards.setDisabled(true);
			 packagePayments$btn_payWriteOff.setDisabled(true);
			 if(state == 5){
			 packagePayments$btn_payAgain.setDisabled(false);
			 packagePayments$btn_payWriteOff.setDisabled(true);
			 }
		 }
		 }
		 else {
			 packagePayments$btn_deleteExcel.setDisabled(true);
			 packagePayments$btn_fillAccounts.setDisabled(true);
			 packagePayments$btn_fillCards.setDisabled(true);
			 packagePayments$btn_payAgain.setDisabled(true);
			 packagePayments$btn_payWriteOff.setDisabled(true);
			 if(TclientService.checkTableState(3, branch, selectedExcel)){
				 packagePayments$btn_fillCards.setDisabled(false);
			 }
		 }
		 packagePayments$paymentListBox.setModel(new BindingListModelList(TclientService.getUploaded(alias, branch, selectedExcel),true));
		 }
		 else{
			 packagePayments$excelList.setSelectedIndex(-1);
			 packagePayments$btn_deleteExcel.setDisabled(true);
			 packagePayments$btn_fillAccounts.setDisabled(true);
			 packagePayments$btn_fillCards.setDisabled(true);
			 packagePayments$btn_payAgain.setDisabled(true);
			 packagePayments$btn_payWriteOff.setDisabled(true);
			if (packagePayments$executedCheck.isChecked()) {
				packagePayments$paymentListBox.setModel(new BindingListModelList(TclientService.getUploadedFull(alias, branch), true));
			} else {
				packagePayments$paymentListBox.setModel(new BindingListModelList(TclientService.getUploadedNewOnly(alias, branch), true));
			}
		 }
		 
		 
		 
	}*/
	
	public void onClick$btn_deleteExcel$packagePayments(){
		TclientService.deleteTable(branch, currentExcel.getName());
		TclientService.deletePacketPayment(branch, currentExcel.getName());
        packagePayments$btnReplenishCards.setDisabled(true);
		packagePayments$btn_deleteExcel.setDisabled(true);
        if (packagePayments$executedCheck.isChecked()) {
            System.out.println("executedCheck checked!");
            packagePayments$uploadedExcels.setModel(new BindingListModelList(
                    TclientService.getPacketPaymentsAll(branch, alias), true));
            packagePayments$paymentListBox.setModel(new BindingListModelList(
                    TclientService.getUploadedFull(alias, branch), true));
        } else {
            System.out.println("executedCheck unchecked!");
            packagePayments$uploadedExcels.setModel(new BindingListModelList(
                    TclientService.getPacketPaymentsNewOnly(branch, alias), true));
            packagePayments$paymentListBox.setModel(new BindingListModelList(
                    TclientService.getUploadedNewOnly(alias, branch), true));
        }
	}
	
/*	public void onClick$btn_payAgain$packagePayments(){
		packagePayments$doneCounter.setValue("0");
		if(kod_org.getValue().isEmpty() || document_id.getValue() == null || txt_branch.getValue().isEmpty()){
			alert("Заполните все поля на главной странице");
			return;
		}
		if(currentExcel != null){
			TclientService.payAgain(currentExcel.getName());
			result = TclientService.sendFillToHumo(currentExcel, packagePayments$doneCounter, uid, alias, un, pwd);
			//result = TclientService.sendFillToHumo(packagePayments$doneCounter, uid, kod_org.getValue(), document_id.getValue().toString(), alias, issuingPortProxy, txt_branch.getValue(), selectedExcel, un, pwd);
			 packagePayments$paymentListBox.setModel( new BindingListModelList(TclientService.getUploaded(alias, branch, currentExcel.getName()),true));
		}
		else if(currentPayment != null){
			TclientService.payAgain(currentExcel.getName(), currentPayment.getEmployeeId(), currentPayment.getEmployeeAccount());
			result = TclientService.sendFillToHumo(currentExcel, packagePayments$doneCounter, uid, alias, issuingPortProxy, currentPayment, un, pwd);
			//result = TclientService.sendFillToHumo(packagePayments$doneCounter, uid, kod_org.getValue(), document_id.getValue().toString(), alias, issuingPortProxy, txt_branch.getValue(), currentPayment, un, pwd);
			if (packagePayments$executedCheck.isChecked()) {
				packagePayments$paymentListBox.setModel(new BindingListModelList(TclientService.getUploadedFull(alias, branch), true));
			} else {
				packagePayments$paymentListBox.setModel(new BindingListModelList(TclientService.getUploadedNewOnly(alias, branch), true));
			}
		}
		if(result.getCode() == -4) {
			alert("Нет карт для зачисления");
			return;
		}
		if(result.getCode() != -1){
			alert("Успешно");
		} else {
			alert(result.getName());
		}
	}*/
	
	public void onClick$btn_payWriteOff$packagePayments() throws JsonProcessingException, SQLException{
		if(payWriteOffState == false){
			payWriteOffState = true;
			paywnd$btn_pay.setDisabled(true);
			Res res = new Res(); 
			res = TclientService.payWriteOff(currentPayment, res, branch, uid, un).getRs();
			alert(res.getName());
			payWriteOffState = false;
		}else{
			ISLogger.getLogger().error("одиночное списание double");
			////двойной быстрый неуловимый клик
		}
	}
	
	public void onSelect$list_amnt$paywnd()
	{
		try {
			long genid = Long.parseLong(paywnd$list_amnt.getValue());
			
			if (CheckNull.isEmpty(genid) || genid == 0) 
			{
				alert("Выберите документ для оплаты!");
				return;
			}
			else
			{
				Connection c = null;
				c = ConnectionPool.getConnection();
				generalInfo = TclientService.getSummOfPayment(alias, genid, c);
				ConnectionPool.close(c);
				if (generalInfo.getID() > 0)
				{
					ISLogger.getLogger().error("GENERAL AMOUNT: "+generalInfo.getSUMMA());
					//String amount = nf.format(Long.parseLong(generalInfo.getSUMMA().toString().substring(0, generalInfo.getSUMMA().toString().length() -2)));
					BigDecimal bdv = generalInfo.getSUMMA().divide(new BigDecimal(100));
					System.out.println("generalInfo.getSUMMA().toString(): "+generalInfo.getSUMMA().toString().substring(0, generalInfo.getSUMMA().toString().length() -2));
					paywnd$amnt.setValue(bdv.toString());
					paywnd$amnt.setReadonly(true);
					ISLogger.getLogger().error("SELECT AMOUNT: "+paywnd$amnt.getValue());
					paywnd$inc_ord_num.setValue(generalInfo.getDOC_NUM());
				}
				else 
				{
					alert("Сумма не указана!");
					return;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			alert(CheckNull.getPstr(e));
		}
	}
	
	public void onDoubleClick$btn_pay_paywnd(){
		
	}
	
	public void onClick$btn_pay$paywnd() throws InterruptedException
	{
		if(paymentState == false){
			paymentState = true;
			paywnd$btn_pay.setDisabled(true);
			pay();
			paymentState = false;
		}else{
			ISLogger.getLogger().error("одиночное пополнение double");
			////двойной быстрый неуловимый клик
		}		
	}
	
	public void pay(){
		ISLogger.getLogger().error("Одиночное пополнение: accountNo: "+cardInfo.getACCOUNT_NO()+" bankAccount: "+cardInfo.getBank_account()
				+" cardNumber: "+cardInfo.getCARD()+" userId: "+uid);
		//long genid = Long.parseLong(paywnd$list_amnt.getValue());
/*		Messagebox.show("Пополнение на сумму "+ paywnd$amnt.getValue() +", вы уверены?", "Предупреждение", Messagebox.OK | Messagebox.CANCEL, Messagebox.EXCLAMATION, new EventListener() {
			
			@Override
			public void onEvent(Event event) throws Exception {
				if(event.getName().equals("onOK")){*/
		Res res = TclientService.check_user(alias, branch, current.getTieto_customer_id());
		if (res.getCode() == 0)
		{
			alert("Клиент не объединён");			
			return;
		}
		Long GLOBID = 0l;
		int STATE = 2;
		try
		{
			if (paywnd$amnt.getValue().equals("0")) 
			{
				alert("Сумма должна быть больше 0, выберите другой документ для пополнения");
				paywnd$btn_pay.setDisabled(false);
				return;
			}
			if(paywnd$list_amnt.getValue() == null || paywnd$list_amnt.getValue().equals("")){
				alert("Выберите документ для оплаты!");
				paywnd$btn_pay.setDisabled(false);
				return;
			}
			TrPay trp = new TrPay();
			Connection c = null;
			c = ConnectionPool.getConnection();
			trp.setAccount_no(cardInfo.getACCOUNT_NO() + "");
			DecimalFormat nf = new DecimalFormat("0.00##");
			nf.format(generalInfo.getSUMMA());
			trp.setAmount(generalInfo.getSUMMA());
			trp.setCard_acc(cardInfo.getBank_account());
			trp.setCur_acc(paywnd$scurracc.getValue());
			trp.setCl_name(current.getName());
			trp.setBranch(branch);
			trp.setOperation_id(1);
			trp.setPan(cardInfo.getCARD());
			trp.setEmp_id(uid);
			trp.setDoc_num(generalInfo.getDOC_NUM());
			ObjectMapper mapper = new ObjectMapper();
			ISLogger.getLogger().error("trp popolnenie: "+mapper.writeValueAsString(trp));
			res = TclientService.checkBfGlobuzTrans(c);
			if (res.getCode() == 1)
			{
				res = TclientService.sendPayment(branch, trp, alias, cardInfo.getBank_account_Ccy(), issuingPortProxy, paywnd$list_amnt.getValue(), uid, un);
				if ( res.getCode() != 0)
				{
					alert("Операция не выполнена, INTERNAL_NO = > " +  res.getName());
					ISLogger.getLogger().error("Ошибка пополнения:"+res.getName());
					paywnd$btn_pay.setDisabled(false);
					return;
				}
				else
				{
					GLOBID = Long.parseLong(res.getName()); 
					STATE = 1;
				}
				paywnd$amnt.setReadonly(false);
				Bf_globuz_trans bf_globuz_trans = new Bf_globuz_trans();
				bf_globuz_trans.setEMPID(String.valueOf(uid));
				bf_globuz_trans.setGENID(generalInfo.getID());
				bf_globuz_trans.setGLOBID(GLOBID);
				bf_globuz_trans.setBRANCH(branch);
				bf_globuz_trans.setSTATE(STATE);
				
				res = TclientService.insertIntoBfGlobuzTrans(bf_globuz_trans, c);
				ConnectionPool.close(c);
				if (res.getCode() == 0)
				{
					alert(res.getName());
					// ВАЖНО ! Необходимо отменить пред транз
					System.out.println("4");
					paywnd$btn_pay.setDisabled(false);
					return;
				}
				else {
					alert(res.getName());
				}
				alert("Произведено пополнение на сумму " + paywnd$amnt.getValue() + " [" + cardInfo.getBank_account_Ccy() + "] Номер транзакции = > " +  GLOBID);

			}
			else {
				alert(res.getName());
				return;
			}
		}
		catch (Exception e)
		{
			ISLogger.getLogger().error("Error single payment message : "+CheckNull.getPstr(e));
			System.out.println("3");
			alert(e.getMessage());
		}
		paywnd.setVisible(false);
		paywnd$btn_pay.setDisabled(false);
		onSelect$dataGrid();
	}

	
	
	public void onClick$btn_block$paywnd()
	{
		blockwnd$sstopcauses.setModel(new ListModelList(TclientService.getTstopCauses(alias)));
		// blockwnd$txtstopcauses.setValue("");
		blockwnd.setVisible(true);
	}
	
	public void onClick$btn_cancel$blockwnd()
	{
		blockwnd.setVisible(false);
	}
	
	public void onClick$btn_block$blockwnd()
	{
		OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
		OperationResponseInfo orInfo = null;
		RowType_AddCardToStopList_Request parameters = new RowType_AddCardToStopList_Request();
		
		try
		{
			connectionInfo.setBANK_C(ConnectionPool.getValue("BANK_C", alias));
			connectionInfo.setGROUPC(ConnectionPool.getValue("GROUPC", alias));
			
			parameters.setBANK_C(ConnectionPool.getValue("BANK_C", alias));
			parameters.setGROUPC(ConnectionPool.getValue("GROUPC", alias));
			parameters.setSTOP_CAUSE(blockwnd$sstopcauses.getValue());// parameters.setSTOP_CAUSE("1");
			parameters.setTEXT(blockwnd$txtstopcauses.getValue());
			parameters.setCARD(accinfo.getCard());
			
			orInfo = pp.addCardToStop(connectionInfo, parameters);
			if (orInfo.getResponse_code().intValue() != 0)
			{
				alert(orInfo.getError_action() + "\r\n" + orInfo.getError_description());
			}
			// UserService.UsrLog(new UserActionsLog(uid, un, curip, 6, 1,
			// "Карта No ["+accinfo.getCard()+"] заблокирована", branch));
			
		}
		catch (Exception e)
		{
			alert(e.getMessage());
			e.printStackTrace();
		}
		
		paywnd.setVisible(false);
		blockwnd.setVisible(false);
		onSelect$dataGrid();
	}
	
	public void onClick$btn_unblock$paywnd()
	{
		OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
		OperationResponseInfo orInfo = null;
		RowType_RemoveCardFromStop_Request parameters = new RowType_RemoveCardFromStop_Request();
		
		try
		{
			connectionInfo.setBANK_C("01");
			connectionInfo.setGROUPC("02");
			
			parameters.setBANK_C("01");
			parameters.setGROUPC("02");
			parameters.setTEXT("Card UnBlocked!!!!");
			parameters.setCARD(accinfo.getCard());
			
			orInfo = pp.removeCardFromStop(connectionInfo, parameters);
			if (orInfo.getResponse_code().intValue() != 0)
			{
				alert(orInfo.getError_action() + "\r\n" + orInfo.getError_description());
			}
			// UserService.UsrLog(new UserActionsLog(uid, un, curip, 6, 1,
			// "Карта No ["+accinfo.getCard()+"] разблокирована", branch));
		}
		catch (Exception e)
		{
			alert(e.getMessage());
			e.printStackTrace();
		}
		
		paywnd.setVisible(false);
		onSelect$dataGrid();
	}
	
	public void onOK$fclient()
	{
		onClick$btn_save();
	}
	
	public void onOK$fcard()
	{
		onClick$btn_save();
	}
	
	public void onOK$fbank_c()
	{
		onClick$btn_save();
	}
	
	public void onOK$fclient_b()
	{
		onClick$btn_save();
	}
	
	public void onOK$fcl_type()
	{
		onClick$btn_save();
	}
	
	public void onOK$fcln_cat()
	{
		onClick$btn_save();
	}
	
	public void onOK$frec_date()
	{
		onClick$btn_save();
	}
	
	public void onOK$ff_names()
	{
		onClick$btn_save();
	}
	
	public void onOK$fsearch_name()
	{
		onClick$btn_save();
	}
	
	public void onOK$fsurname()
	{
		onClick$btn_save();
	}
	
	public void onOK$fb_date()
	{
		onClick$btn_save();
	}
	
	public void onOK$fserial_no()
	{
		onClick$btn_save();
	}
	
	public void printp(TrPay trp, String report_file)
	{
		printwnd.setVisible(true);
		// printwnd$btn_print.setVisible(false);
		
		JasperPrint jasperPrint;
		AMedia repmd = null;
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("TR_PAY_ID", trp.getId());
		params.put("V_DATE", df.format(trp.getDate_created()));
		// params.put("CLIENT_NAME1", current.getF_names());
		params.put("CLIENT_NAME1", trp.getCl_name());
		params.put("CLIENT_NAME2", "");
		params.put("CLIENT_NAME3", "");
		// params.put("CLIENT_NAME2", current.getM_name());
		// params.put("CLIENT_NAME3", current.getSurname());
		// params.put("INCLIENT_NAME1", trp.getIn_name());
		params.put("INCLIENT_NAME2", "");
		params.put("INCLIENT_NAME3", "");
		params.put("T_CURRENCY", "USD");
		params.put("TDP_NUM", trp.getDoc_num());
		// params.put("POST_ADDRESS", trp.getIn_address());
		params.put("SUMMA6", trp.getAmount().divide(divideBigDecimal).toString());
		params.put("ESUMMA6", nf.format(trp.getEqv_amount() / 100));
		params.put("PSUMMA6", com.is.utils.CheckNull.F2Money(Double.parseDouble(trp.getAmount().divide(divideBigDecimal).toString()), "доллар", "центов"));
		params.put("TVEOPER", accinfo.getTranz_acct());
		params.put("OPENDOPER", trp.getCur_acc());
		params.put("ACCDOPER1", com.is.tieto_globuz.tieto.TclientService.getkass_acc(branch, alias));
		params.put("BRANCH_NAME", com.is.utils.RefDataService.getMfo_name(branch, alias).get(0).getLabel());
		
		Connection c = null;
		try
		{
			c = ConnectionPool.getConnection(alias);
			jasperPrint = JasperFillManager.fillReport(application.getRealPath(report_file), params, c);
			jasperPrint.setLeftMargin(0);
			jasperPrint.setRightMargin(0);
			jasperPrint.setTopMargin(0);
			jasperPrint.setBottomMargin(0);
			jasperPrint.setPageHeight(595);// (461);//
			jasperPrint.setPageWidth(842);// (652);//
			jasperPrint.setOrientation(OrientationEnum.LANDSCAPE);
			final byte[] buf = JasperRunManager.runReportToPdf(application.getRealPath(report_file), params, c);
			final java.io.InputStream mediais = new ByteArrayInputStream(buf);
			
			repmd = new AMedia(current.getP_first_name() + "_" + current.getP_patronymic() + ".pdf", "pdf", "application/pdf", mediais);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		if (repmd != null)
		{
			printwnd$rpframe.setContent(repmd);
			
		}
	}
	
	public void onClick$btn_cancel$printwnd()
	{
		printwnd.setVisible(false);
	}
	
	public void onClick$btn_cancel$addwnd()
	{
		addwnd.setVisible(false);
		
	}
	
	public void onClick$lock$paywnd()
	{
		if (paywnd$lock.getImage().compareTo("/images/opened.png") == 0)
		{
			paywnd$search_name.setReadonly(true);
			paywnd$address.setReadonly(true);
			paywnd$lock.setImage("/images/locked.png");
			return;
		}
		paywnd$search_name.setReadonly(false);
		paywnd$address.setReadonly(false);
		paywnd$lock.setImage("/images/opened.png");
	}
	
	private static globus.IssuingWS.IssuingPortProxy initNp(globus.IssuingWS.IssuingPortProxy issuingPortProxy, String alias)
	{
		if (np == null)
		{
			np = new NilProvider();
			np.init();
		}
		
		return issuingPortProxy = new globus.IssuingWS.IssuingPortProxy(
			Utils.getValue("EMPC_TIETO_HOST")
//			"http://128.10.10.142:7777/HumoGV/services/Issuing",
//			ConnectionPool.getValue("EMPC_TIETO_HOST_USERNAME", alias),
//			ConnectionPool.getValue("EMPC_TIETO_HOST_PASSWORD", alias)
			);
		
	}
	
	public String customFormat(String pattern, double value)
	{
		DecimalFormat myFormatter = new DecimalFormat(pattern);
		String output = myFormatter.format(value);
		return output;
	}
	
    public void onUpload$tbtn_add_excel(UploadEvent event) {
        final Media media = event.getMedia();
        InputStream in = null;
        try {
            if (kod_org.getValue() == null || kod_org.getValue().equals("")
                    || kod_org.getValue().length() != 8) {
                alert("Не заполнено поле код организации или введёно некорректное значение");
                return;
            }
            if (txt_branch.getValue() == null || txt_branch.getValue().equals("")
                    || txt_branch.getValue().length() != 5) {
                alert("Не заполнено поле филиал или введёно некорректное значение");
                return;
            }
            if (acc.getValue() == null || acc.getValue().equals("")
                    || acc.getValue().toString().length() != 20) {
                alert("Не заполнено поле транзитный счёт или введёно некорректное значение");
                return;
            }
            if (purpose_txt.getValue() == null || purpose_txt.getValue().equals("")) {
                alert("Не заполнено поле назначение платежа или введёно некорректное значение");
                return;
            }
            if (document_id.getValue() == null || document_id.getValue().equals("")) {
                alert("Не заполнено поле № документа или введёно некорректное значение");
                return;
            }
            if (!media.getFormat().equalsIgnoreCase("xlsx")) {
                alert("Поддерживаемый формат xlsx а не " + media.getFormat());
                return;
            }
            PacketPayment uploadedExcel = new PacketPayment();
            uploadedExcel.setBranch(txt_branch.getValue());
            uploadedExcel.setAccount(acc.getValue().toString());
            uploadedExcel.setCodeOrganization(kod_org.getValue());
            uploadedExcel.setPaymentPurpose(purpose_txt.getValue());
            uploadedExcel.setGeneralPay(document_id.getValue().longValue());
            uploadedExcel.setState(0);
            uploadedExcel.setName(media.getName());
            uploadedExcel.setDate(new Date());
            if (TclientService.checkHumoAccrualEmployee(branch, kod_org.getValue())) {
                if (!TclientService.checkSpecialAccIsBlocked(txt_branch.getValue(),
                        acc.getValue().toString())) {
                    if (!TclientService.getUsedFile(media.getName())) {
                        in = media.getStreamData();
                        ////Сохранение ЭКСЕЛЯ EXCEL ЭКСЕЛЬ ПАКЕТНОЕ ПОПОЛНЕНИЕ
                        Res res = TclientService.saveExcel(uploadedExcel, uploadCounter, in, alias, un, pwd);
                        if (res.getCode() == -1) {
                            alert(res.getName());
                        } else {
                            packagePayments$paymentListBox.setModel(new BindingListModelList(
                                    TclientService.getUploaded(alias, branch, media.getName()),
                                    true));
                            //packagePayments$excelList.setValue(media.getName());
                            packagePayments$uploadedExcels.setModel(new BindingListModelList(
                                    TclientService.getPacketPaymentsNewOnly(branch, alias), true));
                            packagePayments$btnReplenishCards.setDisabled(false);
                            packagePayments$btn_deleteExcel.setDisabled(false);
                            packagePayments.setVisible(true);
                        }
                    } else {
                        alert("В базе есть необработанные записи или имя файла повторяется");
                    }
                } else {
                    alert("Счёт " + acc.getValue() + " заблокирован!");
                    return;
                }
            } else {
                alert("В базе есть необработанные записи или имя файла повторяется");
            }
        } catch (Exception e) {
            e.printStackTrace();
            alert(CheckNull.getPstr(e));
            ISLogger.getLogger().error("ON UPLOAD EXCEL: " + CheckNull.getPstr(e));
        } finally {
            try {
                if (in != null)
                    in.close();
            } catch (Exception e) {
            }
        }
    }
	
	
	
/*	public void onClick$tbtn_fillCards(){
//		alert("Кнопка временно не работает");
//		return;
		if (kod_org.getValue() == null || kod_org.getValue().equals("") || kod_org.getValue().length() != 8){
			alert("Не заполнено поле код организации или введёно не корректное значение");
			return;
		}
		if(txt_branch.getValue() == null || txt_branch.getValue().equals("") || txt_branch.getValue().length() != 5){
			alert("Не заполнено поле филиал или введёно не корректное значение");
			return;
		}
		if(acc.getValue() == null || acc.getValue().toString().equals("") || acc.getValue().toString().length() != 20){
			alert("Не заполнено поле транзитный счёт или введёно не корректное значение");
			return;
		}
		if(purpose_txt.getValue() == null || purpose_txt.getValue().equals("")){
			alert("Не заполнено поле назначение платежа или введёно не корректное значение");
			return;
		}
		if(document_id.getValue() == null || document_id.getValue().toString().equals("")){
			alert("Не заполнено поле № документа или введёно не корректное значение");
			return;
		}
		Res result = TclientService.fillCardsInBranch(txt_branch.getValue(), kod_org.getValue(), document_id.getValue().toString(), alias);
		if(result.getCode() != -1) {
			result = TclientService.sendFillToHumo(uid, kod_org.getValue(), document_id.getValue().toString(), alias, issuingPortProxy, branch);
			if(result.getCode() == -4) {
				alert("Нет карт для зачисления");
				return;
			}
			if(result.getCode() != -1){
				alert("Успешно");
			} else {
				alert(result.getName());
			}
		} else {
			alert(result.getName());
		}
	}*/
/*	public void onClick$btn_fillAccounts$packagePayments(){
		if(kod_org.getValue().isEmpty() || document_id.getValue() == null || txt_branch.getValue().isEmpty()){
			alert("Заполните все поля на главной странице");
			return;
		}
		packagePayments$btn_fillAccounts.setDisabled(true);
		packagePayments$btn_fillCards.setDisabled(true);
		packagePayments$btn_payAgain.setDisabled(true);
		packagePayments$btn_deleteExcel.setDisabled(true);
		onClick$tbtn_fillAccounts();
	}
	
	public void onClick$btn_fillCards$packagePayments(){
		if(kod_org.getValue().isEmpty() || document_id.getValue() == null || txt_branch.getValue().isEmpty()){
			alert("Заполните все поля на главной странице");
			return;
		}
		onClick$tbtn_fillCards();
		packagePayments$btn_fillAccounts.setDisabled(true);
		packagePayments$btn_fillCards.setDisabled(true);
		packagePayments$btn_payAgain.setDisabled(true);
		packagePayments$btn_deleteExcel.setDisabled(true);
		 packagePayments$paymentListBox.setModel( new BindingListModelList(TclientService.getUploaded(alias, branch, currentExcel.getName()),true));
	}*/
    
    //ПАКЕТНОЕ ПОПОЛНЕНИЕ
    public void onClick$btnReplenishCards$packagePayments() {
        if (!packetPaymentState) {
            packetPaymentState = true;
            alert("Ведомость обрабатывается, это может занять до нескольких минут в зависимости от её размера. Проверьте статус пополнения позже.");
            packagePayments$btnReplenishCards.setDisabled(true);
            packagePayments$btn_deleteExcel.setDisabled(true);
            packagePayments$btn_payWriteOff.setDisabled(true);
            int state = TclientService.checkPacketPaymentState(currentExcel);
            ISLogger.getLogger().error("packetPayment state: "+state);
            if (state == 0 || state == 5 || state == 4 || state == 3) {
                TclientService.updateExcelStateProcessing(currentExcel);
                try {
                    ISLogger.getLogger().error("state is good, start payment "+objectMapper.writeValueAsString(currentExcel));
                } catch (JsonProcessingException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                PaymentThread paymentThread = new PaymentThread(currentExcel,
                        packagePayments$doneCounter, uid, alias, un, pwd);
                Thread pay = new Thread(paymentThread);
                pay.start();
                packagePayments$uploadedExcels.setModel(new BindingListModelList(
                        TclientService.getPacketPaymentsNewOnly(branch, alias), true));
                packetPaymentState = false;
                refreshModel(_startPageNumber);
                alert("Ведомость обрабатывается, это может занять до нескольких минут в зависимости от её размера. Проверьте статус пополнения позже.");
            }
        } else {
            ISLogger.getLogger().error("Packet payment double click");
        }
    }
    
    public void onDoubleClick$btnReplenishCards$packagePayments() {
        ISLogger.getLogger().error("Packet payment double click");
    }
    
    public void onClick$btnRefresh$packagePayments() {
        if (packagePayments$executedCheck.isChecked()) {
            System.out.println("executedCheck checked!");
            packagePayments$uploadedExcels.setModel(new BindingListModelList(
                    TclientService.getPacketPaymentsAll(branch, alias), true));
            packagePayments$paymentListBox.setModel(new BindingListModelList(
                    TclientService.getUploadedFull(alias, branch), true));
        } else {
            System.out.println("executedCheck unchecked!");
            packagePayments$uploadedExcels.setModel(new BindingListModelList(
                    TclientService.getPacketPaymentsNewOnly(branch, alias), true));
            packagePayments$paymentListBox.setModel(new BindingListModelList(
                    TclientService.getUploadedNewOnly(alias, branch), true));
        }
    }

	
/*	public void onClick$tbtn_fillAccounts(){
		packagePayments$btn_fillAccounts.setDisabled(true);
		if (kod_org.getValue() == null || kod_org.getValue().equals("") || kod_org.getValue().length() != 8){
			alert("Не заполнено поле код организации или введёно не корректное значение");
			return;
		}
		if(txt_branch.getValue() == null || txt_branch.getValue().equals("") || txt_branch.getValue().length() != 5){
			alert("Не заполнено поле филиал или введёно не корректное значение");
			return;
		}
		if(acc.getValue() == null || acc.getValue().toString().equals("") || acc.getValue().toString().length() != 20){
			alert("Не заполнено поле транзитный счёт или введёно не корректное значение");
			return;
		}
		if(purpose_txt.getValue() == null || purpose_txt.getValue().equals("")){
			alert("Не заполнено поле назначение платежа или введёно не корректное значение");
			return;
		}
		if(document_id.getValue() == null || document_id.getValue().toString().equals("")){
			alert("Не заполнено поле № документа или введёно не корректное значение");
			return;
		}
		result = TclientService.fillCardsInBranch(TclientService.checkTableState(branch, currentExcel.getName()), txt_branch.getValue(), kod_org.getValue(), document_id.getValue().toString(), alias, un, pwd);
		 packagePayments$paymentListBox.setModel( new BindingListModelList(TclientService.getUploaded(alias, branch, currentExcel.getName()),true));
		if(result.getCode() != -1) {
			packagePayments$btn_fillCards.setDisabled(false);
			alert("Счета успешно пополнены. Можно пополнять карты.");
		} else {
			alert(result.getName());
		}
	}
	
	public void onClick$tbtn_fillCards(){
		packagePayments$doneCounter.setValue("0");
		if(currentExcel != null){
			result = TclientService.sendFillToHumo(currentExcel, packagePayments$doneCounter, uid, alias, issuingPortProxy, un, pwd);
		}
		else if(currentPayment != null){
			result = TclientService.sendFillToHumo(currentExcel, packagePayments$doneCounter, uid, alias, issuingPortProxy, currentPayment, un, pwd);
		}
			if(result.getCode() == -4) {
				alert("Нет карт для зачисления либо введены неверные данные");
				return;
			}
			 packagePayments$paymentListBox.setModel( new BindingListModelList(TclientService.getUploaded(alias, branch, currentExcel.getName()),true));
			 packagePayments$btn_fillAccounts.setDisabled(true);
			 packagePayments$btn_fillCards.setDisabled(true);
			 packagePayments$btn_payAgain.setDisabled(true);
			 packagePayments$btn_deleteExcel.setDisabled(true);
			if(result.getCode() == 0){
				alert("Успешно");
			} else {
				alert(result.getName());
			}
	}*/
	
	public void onClick$btn_paymentSearch$packagePayments(){
		paymentFilter = new PaymentFilter();
		System.out.println("selectedState: "+selectedState);
		if (!selectedState.equals(" ")) {
			paymentFilter.setState(stateMap.get(selectedState).toString());
		}
		paymentFilter.setAccount(packagePayments$account.getValue());
		paymentFilter.setEmployee_id(packagePayments$employeeId.getValue());
		paymentFilter.setAmount(packagePayments$amount.getValue());
		paymentFilter.setCardNumber(packagePayments$cardNumber.getValue());
		if(!(packagePayments$dateSince.getValue() == null)){
			paymentFilter.setDate(df.format(packagePayments$dateSince.getValue()));
		}
		List<FilterField> filterFields = getFilterFields(paymentFilter);
		final StringBuffer sql = new StringBuffer();
        sql.append("select * from humo_accrual_employee");
        if (filterFields.size() > 0) {
            for (int i = 0; i < filterFields.size(); ++i) {
                sql.append(filterFields.get(i).getSqlwhere());
            }
        sql.append(" and branch = '"+branch+"'");
        }else{
        	sql.append(" where branch = '"+branch+"'");
        }
        ISLogger.getLogger().error("paymentListBox sql: "+sql.toString());
		 packagePayments$paymentListBox.setModel(new BindingListModelList(TclientService.getUploadedFilter(sql.toString(), alias, branch), true));
	}
	
	public void onCheck$executedCheck$packagePayments(){
		if(packagePayments$executedCheck.isChecked()){
			System.out.println("executedCheck checked!");
		      //packagePayments$uploadedExcels.setModel(new ListModelList(TclientService.getUploadedExcelFull(branch, alias)));
			 //packagePayments$excelList.setModel(new ListModelList(TclientService.getUploadedExcelFull(branch, alias)));
		      packagePayments$uploadedExcels.setModel(new BindingListModelList(TclientService.getPacketPaymentsAll(branch, alias), true));
			 packagePayments$paymentListBox.setModel( new BindingListModelList(TclientService.getUploadedFull(alias, branch),true));
		}else{
			System.out.println("executedCheck unchecked!");
	           packagePayments$uploadedExcels.setModel(new BindingListModelList(TclientService.getPacketPaymentsNewOnly(branch, alias), true));
			 packagePayments$paymentListBox.setModel( new BindingListModelList(TclientService.getUploadedNewOnly(alias, branch),true));
		}
	}
	
	public Res checkFormFill() {
	    Res res = new Res();
	    res.setCode(0);
	       if (kod_org.getValue() == null || kod_org.getValue().equals("") || kod_org.getValue().length() != 8){
	            res.setCode(-1);
	            res.setName("Не заполнено поле код организации или введёно не корректное значение");
	            return res;
	        }
	        if(txt_branch.getValue() == null || txt_branch.getValue().equals("") || txt_branch.getValue().length() != 5){
	            res.setCode(-1);
                res.setName("Не заполнено поле филиал или введёно не корректное значение");
	            return res;
	        }
	        if(acc.getValue() == null || acc.getValue().toString().equals("") || acc.getValue().toString().length() != 20){
	            res.setCode(-1);
                res.setName("Не заполнено поле транзитный счёт или введёно не корректное значение");
	            return res;
	        }
	        if(purpose_txt.getValue() == null || purpose_txt.getValue().equals("")){
	            res.setCode(-1);
                res.setName("Не заполнено поле назначение платежа или введёно не корректное значение");
	            return res;
	        }
	        if(document_id.getValue() == null || document_id.getValue().toString().equals("")){
	            res.setCode(-1);
                res.setName("Не заполнено поле № документа или введёно не корректное значение");
	            return res;
	        }
	        return res;
	}
	
	
	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFilename() {
		return filename;
	}
	
	public void setUploadCounter(Label uploadCounter) {
		this.uploadCounter = uploadCounter;
	}

	public Label getUploadCounter() {
		return uploadCounter;
	}

    public AccrualEmployee getCurrentPayment() {
        return currentPayment;
    }

    public PacketPayment getCurrentExcel() {
        return currentExcel;
    }

    public void setCurrentPayment(AccrualEmployee currentPayment) {
        this.currentPayment = currentPayment;
    }

    public void setCurrentExcel(PacketPayment currentExcel) {
        this.currentExcel = currentExcel;
    }
}
