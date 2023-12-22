package com.is.sd_books;

import java.math.BigDecimal;
//import java.sql.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.zkoss.zul.*;
import org.zkoss.zul.event.PagingEvent;

import com.is.sd_books.model.SD_Book;
import com.is.sd_books.model.SD_BookFilter;
import com.ibm.db2.jcc.am.s;
import com.is.ISLogger;
import com.is.clientcrm.utils.ZkUtils;
//import com.is.bpri.Bproduct;
import com.is.sd_books.model.Circulate;
import com.is.sd_books.model.Clerk_Book;
import com.is.sd_books.model.Credentials;
import com.is.sd_books.model.Deposit;
import com.is.sd_books.model.Percentage;
import com.is.sd_books.model.SD_Error;
import com.is.sd_books.model.Sd_priv_Cond;
import com.is.sd_books.paging.PagingListModel;
import com.is.sd_books.report.ApplDog;
import com.is.sd_books.report.ApplSum;
import com.is.sd_books.report.ApplVal;
import com.is.sd_books.report.CashOrder2;
import com.is.sd_books.report.CashOrder3;
import com.is.sd_books.report.CredOrS;
import com.is.sd_books.report.CredOrV;
import com.is.sd_books.report.CreditOrder;
import com.is.sd_books.report.DebitOrder;
import com.is.sd_books.report.SdBook_prn1;
import com.is.sd_books.report.SdBook_prn2;
import com.is.sd_books.report.Shahsiy2015;
import com.is.sd_books.report.basic.ReportBuilder;
import com.is.sd_books.service.AssistantService;
import com.is.sd_books.service.CirculateService;
import com.is.sd_books.service.ClerkService;
import com.is.sd_books.service.DepositService;
import com.is.sd_books.service.ErrorService;
import com.is.sd_books.service.FrmService;
import com.is.sd_books.service.OperationService;
import com.is.sd_books.service.PrcService;
import com.is.sd_books.service.RefService;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.RefData;
import com.is.utils.Res;
import com.lowagie.text.ListItem;

public class Composer extends GenericForwardComposer {
	private Toolbarbutton btn_back,btn_add_row,btn_refresh;
	private Div grd, frm,checkGrid;
	private Grid ad_PrivCond,pr_cond_Grid,search_grd;
	private Paging depositListboxPaging;
	private Listbox dataGrid, ad_prCond,add_PrivCond$ad_prCond;
	
	private RefCBox filial, dep, f_oper, state;
	private RefCBox c_type_document, c_resident_code, c_code_citizenship;
	private Textbox f_operdate, f_sum;
	private Label f_sum_text,add_PrivCond$id_date_action;
	private Listbox circulateListbox;

	private Window new_depositWnd;
	private RefCBox new_depositWnd$n_filial, new_depositWnd$n_dep,
			new_depositWnd$n_oper,new_depositWnd$p_num,add_PrivCond$id_prCond_desc,add_PrivCond$id_prCond_val;
	private Textbox new_depositWnd$n_sum, new_depositWnd$dep_text,new_depositWnd$p_ser;
	private Datebox new_depositWnd$operDate;

	private Window searchWnd;
	private RefCBox searchWnd$s_states;

	private Window errorWnd;
	private Listbox errorWnd$error_listbox;

	private Window prcWnd;
	private Listbox prcWnd$prc_listbox;

	private Window docWnd;
	private Include docWnd$doc;
	
	private Window check_SDclerk,add_PrivCond;
	private Checkbox check_SDclerk$id_check_torn,check_SDclerk$id_check_clerk;
	private Toolbarbutton check_SDclerk$btn_save_chk,check_SDclerk$btn_chk_cancel,add_PrivCond$btn_add_row,add_PrivCond$btn_save_chk,
	add_PrivCond$btn_chk_exit,new_depositWnd$btn_add_prCondDep,new_depositWnd$btn_open,add_PrivCond$btn_chk_del,add_PrivCond$btn_interrupt;
	private Textbox id_kod,add_PrivCond$id_prCond_Date,add_PrivCond$pr_id_par,add_PrivCond$pr_id_desc,add_PrivCond$pr_id_value,add_PrivCond$id_prCond_txt;
	private Textbox add_PrivCond$pr_id_par1,add_PrivCond$pr_id_desc1,add_PrivCond$pr_id_value1,new_depositWnd$filial_text;
	private Textbox add_PrivCond$pr_id_par2,add_PrivCond$pr_id_desc2,add_PrivCond$pr_id_value2;
	//private boolean ClickRes=false;

	private int _pageSize = 15;
	private int _totalSize = 0;
	private int _startPageNumber = 0;
	private int _oldSelectedIndex = 0;
	private boolean _needsTotalSizeUpdate = true;
	private SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	private SD_Book current = new SD_Book();
	private Percentage current_pc=new Percentage();
	private SD_BookFilter filter = new SD_BookFilter();
	private Deposit new_dep = new Deposit();
	private AnnotateDataBinder binder;
	private Credentials cr;
	private Date operDate;
	private Sd_priv_Cond sd_pr_cond;
	private int book_id=0;
	private int uid,uid3;
	private int count=0;
	private String uid2,uid4;
	private boolean chbook_id=true,isClick_btn_interrupt=false;
	private boolean isClicked_btn_del=false;
	int generalId_ = 0;
	
	private List<Sd_priv_Cond> prCondList = new ArrayList<Sd_priv_Cond>();
	

	private static final long serialVersionUID = 1L;

	public Composer() {
		super('$', false, false);
	}

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		

		binder = new AnnotateDataBinder(comp);
		binder.bindBean("current", this.current);
		binder.bindBean("filter", this.filter);
		binder.bindBean("new_dep", this.new_dep);
		binder.loadAll();

		String[] parameters = (String[]) param.get("ht");
		if (parameters != null) {
			_pageSize = Integer.parseInt(parameters[0]) / 36;
			dataGrid.setRows(Integer.parseInt(parameters[0]) / 36);
		}

		String login = String.valueOf(session.getAttribute("un"));
		String password = String.valueOf(session.getAttribute("pwd"));
		String branch = String.valueOf(session.getAttribute("branch"));
		String alias = String.valueOf(session.getAttribute("alias"));
		
		uid2=String.valueOf(session.getAttribute("uid"));
		 uid3=Integer.valueOf(branch);
		 uid4=String.valueOf(uid3);
		 ISLogger.getLogger().warn("uid3: "+uid3);
		 if(uid2.startsWith(uid4)){
			 int n=uid4.length();
			 uid=(uid2.length()>uid4.length())?Integer.valueOf(uid2.substring(n)):Integer.valueOf(uid2);
			 ISLogger.getLogger().warn("1212Dep_emp_id: "+uid);
			 
		 }
		 else {
			 uid=Integer.valueOf(uid2);
		 }
		 ISLogger.getLogger().warn("branch: "+branch);
		//uid1=((uid2.length()>3)?uid2.substring(3):uid2);
		ISLogger.getLogger().warn("--Dep_emp_id22_uid2: "+uid2);
		//uid = Integer.valueOf(session.getAttribute("uid").toString());
		//uid=Integer.valueOf(uid2);
		ISLogger.getLogger().warn("Dep_emp_id: "+uid);
		//uid=Integer.valueOf("99");
		cr = Credentials.newInstance(login, password, alias, branch);
		operDate = AssistantService.getOperDate(cr.getAlias());
		
		dataGrid.setItemRenderer(new ListitemRenderer() {
			@Override	
			public void render(Listitem row, Object data) throws Exception {
				SD_Book book = (SD_Book) data;
				
				List<Listcell> list = new ArrayList<Listcell>();
				list.add(new Listcell(book.getDep()));
				list.add(new Listcell(book.getFilial()));
				list.add(new Listcell(book.getName()));
				list.add(new Listcell(book.getSaldo()
						.setScale(2, BigDecimal.ROUND_UNNECESSARY).toString()));
				list.add(new Listcell((book.getDate_open() == null) ? "" : df
						.format(book.getDate_open())));
				list.add(new Listcell(book.getNum() + ""));
				list.add(new Listcell(book.getAccount()));
				list.add(new Listcell((book.getDate_close() == null ? "" : df
						.format(book.getDate_close()))));
				list.add(new Listcell((book.getDeadline() == null ? "" : df
						.format(book.getDeadline()))));

				for (Listcell cell : list) {
					if (book.getState() == 1)
						;
					if (book.getState() == 2)
						cell.setStyle("color:red");
					if (book.getState() == 3)
						cell.setStyle("color:blue");
					if (book.getState() == 4)
						cell.setStyle("color:green");
					if (book.getState() == 5)
						cell.setStyle("color:yellow");
					if (book.getState() == 6)
						cell.setStyle("color:violet");
					row.appendChild(cell);
				}
			}
		});

		refreshModel(_startPageNumber);

		String[] idFromSearch = (String[]) param.get("search_clients");
		if (idFromSearch != null) {
			filter = new SD_BookFilter();
			filter.setClient_code(idFromSearch[0]);
			_needsTotalSizeUpdate = true;
			refreshModel(_startPageNumber);
		}
	}

	public void onPaging$depositListboxPaging(ForwardEvent event) {
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModel(_startPageNumber);
	}

	private void refreshModel(int activePage) {
		depositListboxPaging.setPageSize(_pageSize);
		PagingListModel model = new PagingListModel(activePage, _pageSize,
				filter, cr.getAlias());
		//izmineniya 25.01.20
	//	_totalSize = model.getTotalSize(filter, cr.getAlias());
		
		if (_needsTotalSizeUpdate) {
			_totalSize = model.getTotalSize(filter, cr.getAlias());
		//	_needsTotalSizeUpdate = false;
		}
		depositListboxPaging.setTotalSize(_totalSize);
		dataGrid.setModel((ListModel) model);
		if (model.getSize() > 0) {
			dataGrid.setSelectedIndex(_oldSelectedIndex);
			sendSelEvt();
			System.out.println("UTTTTT");
			this.current = (SD_Book) model.getElementAt(_oldSelectedIndex);
			sendSelEvt();
		//	SelectEvent evt = new SelectEvent("onSelect",dataGrid,dataGrid.getSelectedItems());
		//	Events.sendEvent(evt);
		}
	}
	
	/*public void onClick$btn_refresh() {
		//_oldSelectedIndex = 0;
        //_startPageNumber = 0;
        //documentPaging.setActivePage(_startPageNumber);
        refreshModel(_startPageNumber);
	}*/
	public void onDoubleClick$dataGrid() {
		btn_back.setImage("/images/folder.png");
		btn_back.setLabel(Labels.getLabel("grid"));
		grd.setVisible(false);
		frm.setVisible(true);
		init();
		initCirculate();
		btn_refresh.setVisible(false);
		System.out.println("fitch1");
		}

	private void init() {
		f_operdate.setValue((operDate == null ? "" : df.format(operDate)));

		f_oper.setModel(new ListModelList(FrmService.getOperTurn(
				current.getDep(), cr.getAlias())));
		
		List<RefData> list = FrmService.getOperTurn(
				current.getDep(), cr.getAlias());
		for (int i = 0; i < list.size(); i++) {
			RefData ref = list.get(i);
			System.out.println(ref.getData());
			
			
			
		}
		
		state.setModel(new ListModelList(FrmService.getStates(cr.getAlias())));
		filial.setModel(new ListModelList(FrmService.getFilials(cr.getAlias(),uid)));
		dep.setModel(new ListModelList(FrmService.getDeps(cr.getAlias())));

		sendOnInit(f_oper);
		sendOnInit(filial);
		sendOnInit(dep);
		sendOnInit(state);

		filial.setSelecteditem(current.getFilial());
		dep.setSelecteditem(current.getDep());
		state.setSelecteditem(String.valueOf(current.getState_id()));

	//	filial.setSelecteditem(filter.getFilial());
	//	dep.setSelecteditem(filter.getDep());
	//	state.setSelecteditem(String.valueOf(filter.getState_id()));

		
		
		c_type_document.setModel(new ListModelList(RefService
				.getTypeDocuments(cr.getAlias())));
		c_resident_code.setModel(new ListModelList(RefService.getResidents(cr
				.getAlias())));
		c_code_citizenship.setModel(new ListModelList(RefService
				.getCountries(cr.getAlias())));

		sendOnInit(c_type_document);
		sendOnInit(c_resident_code);
		sendOnInit(c_code_citizenship);
		c_type_document.setSelecteditem(current.getType_document());
		c_resident_code.setSelecteditem(current.getResident_code());
		c_code_citizenship.setSelecteditem(current.getCode_citizenship());
	}

	private void initCirculate() {
		circulateListbox.setItemRenderer(new ListitemRenderer() {
			@Override
			public void render(Listitem row, Object data) throws Exception {
				final Circulate circulate = (Circulate) data;

				List<Listcell> list = new ArrayList<Listcell>();

				list.add(new Listcell((circulate.getOper_date() == null) ? "": df.format(circulate.getOper_date())));
				
				list.add(new Listcell(circulate.getCirculate_cr()
						.setScale(2, BigDecimal.ROUND_UNNECESSARY).toString()));
				list.add(new Listcell(circulate.getCirculate_db()
						.setScale(2, BigDecimal.ROUND_UNNECESSARY).toString()));
				list.add(new Listcell(circulate.getSaldo()
						.setScale(2, BigDecimal.ROUND_UNNECESSARY).toString()));
				list.add(new Listcell(circulate.getPc().toString()));
				list.add(new Listcell(circulate.getPrc_circulate_cr()
						.setScale(2, BigDecimal.ROUND_UNNECESSARY).toString()));
				list.add(new Listcell(circulate.getPrc_circulate_db()
						.setScale(2, BigDecimal.ROUND_UNNECESSARY).toString()));
				list.add(new Listcell(circulate.getPrc_saldo()
						.setScale(2, BigDecimal.ROUND_UNNECESSARY).toString()));
				list.add(new Listcell(Integer.toString(circulate.getFx_code())));
				list.add(new Listcell(circulate.getNote()));
				list.add(new Listcell(
						(circulate.getGeneral_id() != null) ? circulate
								.getGeneral_id().toString() : " "));
				list.add(new Listcell(AssistantService.getUserById(
						circulate.getEmp(), cr.getAlias())));

				for (Listcell cell : list) {
					if (circulate.getGeneral_id() == null)
						cell.setStyle("color:red");
					if (circulate.getGeneral_id() != null
							&& circulate.getGeneral_id() > 0)
						cell.setStyle("color:blue");
					row.appendChild(cell);
				}
				row.setValue(circulate);

				row.addEventListener(Events.ON_CLICK, new EventListener() {

					@Override
					public void onEvent(Event event) throws Exception {
						if (circulate.getGeneral_id() != null)
							generalId_ = circulate.getGeneral_id().intValue();
					}
				});
				row.addEventListener(Events.ON_DOUBLE_CLICK,
						new EventListener() {
							@Override
							public void onEvent(Event event) throws Exception {
								if (generalId_ != 0) {
									docWnd.setVisible(true);
									docWnd$doc.setSrc("doc.zul?showDoc="
											+ generalId_);
								}
							}
						});
			}
		});
		refreshCirculate();
	}

	private void refreshCirculate() {
		circulateListbox.setModel(new ListModelList(CirculateService
				.getCirculate(current.getB_id(), cr)));
		
		
		System.out.println("param:"+circulateListbox.getModel());
	}

	private void sendOnInit(Component comp) {
		Events.sendEvent(comp, new Event("onInitRender"));
	}
	
	private void sendSelEvt(){
        SelectEvent evt = new SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
        Events.sendEvent(evt);
	}

	

	public void onClick$btn_back() {
		if (frm.isVisible()) {
			frm.setVisible(false);
			grd.setVisible(true);
			btn_back.setImage("/images/file.png");
			btn_back.setLabel(Labels.getLabel("back"));
			btn_refresh.setVisible(true);
		} else {
			onDoubleClick$dataGrid();
		}
	}

	// ------------ Операции --------------
	// Открытие
	public void onClick$btn_save_operation() {
		if (CheckNull.isEmpty(f_oper.getValue())) {
			alert("Поле Операции - Не Может Быть Пустым");
			return;
		}
		if (CheckNull.isEmpty(f_sum.getValue())) {
			alert("Поле Сумма - Не Может Быть Пустым");
			return;
		}
		if (f_sum.getValue() != null && !(f_sum.getValue().matches("^-?\\d*\\.\\d+$|^-?\\d+$"))) {
			alert("Поле Сумма - Только Числовые Значения");
			return;
		}
		Integer operationCode = Integer.valueOf(f_oper.getValue());
		Double sum = Double.valueOf(f_sum.getValue());
		java.sql.Date date = CheckNull.d2sql(operDate);
		OperationService.getInstance(cr).openOperation(current.getB_id(), date,
				operationCode, sum, uid);
		System.out.println("emp_id:"+uid);
		ISLogger.getLogger().warn("Dep_emp_id_btn_save_operation: "+uid);
		refreshCirculate();
		refreshModel(0);
	}
	
	public void onClick$saveDepp() {
		//alert("Fitch");
				
		if (CheckNull.isEmpty(f_oper.getValue())) {
			alert("Поле Операции - Не Может Быть Пустым");
			return;
		}
			
		if(f_oper.getValue().contains("4")||f_oper.getValue().equals("54")){
			
			
			f_sum.setValue("0");
			System.out.println("f_sum = "+f_sum.getValue()); 		       
			if (CheckNull.isEmpty(f_sum.getValue())) {
				alert("Поле Сумма - Не Может Быть Пустым");
				return;
			}			 
		 
			if (f_sum.getValue() != null && !(f_sum.getValue().matches("^-?\\d*\\.\\d+$|^-?\\d+$"))) {
				alert("Поле Сумма - Только Числовые Значения");
				return;	
			}
		}
		
		
		else if(f_oper.getValue().contains("31")||f_oper.getValue().equals("150")){
			Integer opercode1 = Integer.valueOf(f_oper.getValue());
			java.sql.Date operdate1 = CheckNull.d2sql(operDate);
	    double f=OperationService.getInstance(cr).offerSD(current.getB_id(), operdate1, opercode1);
	    double s=Double.valueOf(f_sum.getValue());
	        if(f<s){
	        	alert("Сумма процентов введенные больше чем разрешенные клиента");
				return;	
	        }
			
		}
			
		else {
			
			if (CheckNull.isEmpty(f_sum.getValue())) {
				alert("Поле Сумма - Не Может Быть Пустым");
				return;
			}
			
			if (f_sum.getValue() != null && !(f_sum.getValue().matches("^-?\\d*\\.\\d+$|^-?\\d+$"))) {
				alert("Поле Сумма - Только Числовые Значения");
				return;
			}
		}
				
		Integer operationCode = Integer.valueOf(f_oper.getValue());
		System.out.println(operationCode);
		Double sum = Double.valueOf(f_sum.getValue());
		System.out.println(sum);
		java.sql.Date date = CheckNull.d2sql(operDate);
		System.out.println(date);
		String result=OperationService.getInstance(cr).checkCloseDep(current.getDep(), operationCode, current.getB_id());
		if(result.equals("tt")){
			OperationService.getInstance(cr).openOperation(current.getB_id(), date,
					operationCode, sum, uid);
		}
		else {
			alert(result);
			return;
			 }
			
		

	//	OperationService.getInstance(cr).openOperation(current.getB_id(), date,
	//			operationCode, sum, uid);
		
		ISLogger.getLogger().warn("Dep_emp_id_saveDepp: "+uid);
		
		refreshCirculate();
		refreshModel(0);
	}
	

	// Проводка
	public void onClick$btn_lead() {
		OperationService.getInstance(cr).leadOperation(current);
		refreshCirculate();
		refreshModel(0);
		System.out.println("111111");
	}
	
	
	////////////////////////////////////////////////////////////////////////////////
	public void onClick$btn_lead_win() {
		OperationService.getInstance(cr).leadOperation(current);
		refreshCirculate();
		refreshModel(0);
	}

	// Удаление операции
	public void onClick$btn_delete_operation() {
		boolean cir4=(boolean)circulateListbox.getSelectedItem().isSelected();
		
		if(cir4){
		System.out.println("777777");
		check_SDclerk$id_check_torn.setChecked(false);
		check_SDclerk$id_check_clerk.setChecked(false);
		
		boolean res=CirculateService.isAttachedBook(cr.getAlias(),current.getB_id(),
				current.getBranch());
		Circulate cir3 = (Circulate)circulateListbox.getSelectedItem().getValue();
		System.out.println("get_Oper_Code: "+cir3.getFx_code());
		int opCode=cir3.getFx_code();
		System.out.println("Book_id_del_oper: "+current.getB_id().longValue());
		Long genId3=((cir3.getGeneral_id()!=null)?cir3.getGeneral_id():0);
		System.out.println("genID333333: "+genId3);
		
		if(genId3==0){
			
			//check_SDclerk$
			if(res &&(opCode==1)||(opCode==50)){
				
				System.out.println("trtrtrtrt252525: ");
				check_SDclerk.setVisible(true);
			
				check_SDclerk$btn_save_chk.addEventListener(Events.ON_CLICK, new EventListener() {
             
					@Override
					public void onEvent(Event evt) throws Exception {
					//	evt.getEvent(check_SDclerk$btn_save_chk);
						boolean checkTorn = check_SDclerk$id_check_torn.isChecked();
						boolean checkClerk = check_SDclerk$id_check_clerk.isChecked();
						
						if((checkClerk && checkTorn) || (!checkClerk && !checkTorn)){
							alert("Нельзя выбрать Два пункта одновременно");
							
						}
						
						else{
							retSdBook();
							System.out.println("trutruth:");
							
						String result= CirculateService.deleteOperation(cr, current.getBranch(),
									current.getB_id());
						if(!result.equals("ok")){
							alert(result);
							return;
							}
							check_SDclerk.setVisible(false);
							
						//	initCirculate();
							refreshCirculate();
							refreshModel(0); 		
						}
					}
				});
	
				check_SDclerk$btn_chk_cancel.addEventListener(Events.ON_CLICK, new EventListener() {
		                 
					@Override
					public void onEvent(Event evt) throws Exception {
						check_SDclerk.setVisible(false);
						System.out.println("onClose");
					}
				});
				
			}
			
			else{
				String res1 = CirculateService.deleteOperation(cr, current.getBranch(),
						current.getB_id());
				if(!res1.equals("ok")){
					alert(res1);
					return;
					}
				  
				refreshCirculate();
				refreshModel(0); 		
			}
			System.out.println("refresh//////");
			
		}
				
		else{
			alert("нажмите на кнопку удаления проводка !!!");
		}
		}
		else {
			alert("Выделите операцие на удаления !!!");
		}
		refreshCirculate();
		// Если удаленная операция была операцией закрытия вклада, то вклад
		// должен вновь стать активным
		 //* 	 
	}
	
	/*public boolean isClicked(){
		
		check_SDclerk$btn_chk_cancel.addEventListener(Events.ON_CLICK, new EventListener() {
            
			@Override
			public void onEvent(Event evt) throws Exception {
				ClickRes=true;
				check_SDclerk.setVisible(false);
				System.out.println("Clicked Cancel");
				//Button btn = (Button) evt.getTarget();
				//Bproduct bproduct = (Bproduct) btn.getAttribute("current");
			//getCreditContract(bproduct);
			}
		});
		return ClickRes;
		
	}*/
	 //////////////////////////////////////////////////////////////////////////////
	
	public void retSdBook(){
		int actionId=2;
		boolean checkTorn1=check_SDclerk$id_check_torn.isChecked();
		boolean checkClerk1=check_SDclerk$id_check_clerk.isChecked();
		if(checkTorn1==true){
			actionId=0;
		}
		else if(checkClerk1==true){
			actionId=1;
		}
		ISLogger.getLogger().error("action_id_clerk_book: "+actionId);
		CirculateService.retClerkBook(cr.getAlias(), current.getB_id(), current.getBranch(),
				actionId,current.getFilial());
	  }
	
	public void onClick$btn_delete_operation_win() {
		
		Circulate cir2 = (Circulate)circulateListbox.getSelectedItem().getValue();
		Long genId2=((cir2.getGeneral_id()!=null)?cir2.getGeneral_id():0);
		System.out.println("genID2222: "+genId2);
		if(genId2==0){
		CirculateService.deleteOperation(cr, current.getBranch(),
				current.getB_id());
		refreshCirculate();
		refreshModel(0);
		}
		else{
			alert("нажмите на кнопку удаления проводка");
		}
		// Если удаленная операция была операцией закрытия вклада, то вклад
		// должен вновь стать активным
	}

	// Удаление проводки
	public void onClick$btn_delete_lead() {
		Circulate cir2 = (Circulate) circulateListbox.getSelectedItem().getValue();
		Long genId1 = ((cir2.getGeneral_id() != null) ? cir2.getGeneral_id() : 0);
		cir2.getBank_date();
		int operCode = cir2.getFx_code();
		System.out.println("operCode: " + operCode);
		ISLogger.getLogger().warn("operCode: "+operCode);
		long id_Circulate = cir2.getId().longValue();
		System.out.println("id_Circulate :"+id_Circulate);
		ISLogger.getLogger().warn("id_Circulate: "+id_Circulate);
		int new_type1 = 6;
		// System.out.println("cir2_date: "+cir2.getBank_date());
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		String opDate = sdf.format(operDate);
		String opDate2 = sdf.format(cir2.getBank_date());
		System.out.println("cir2_date---: " + opDate2);
		System.out.println("Date_oper :" + opDate);
		int gen_Id = Integer.valueOf(genId1 + "");
		System.out.println("genID544554: " + genId1);
		ISLogger.getLogger().warn("genID544554: : "+genId1);
	//	Double sum1 = Double.valueOf(f_sum.getValue());
	//	System.out.println(sum1);
		java.sql.Date date1 = CheckNull.d2sql(operDate);
		System.out.println(date1);

		if (genId1 != 0) {
			if (opDate.equals(opDate2)) {
				/*
				 * CirculateService.deleteLead(cr, current.getBranch(),
				 * current.getB_id() .intValue(), generalId_);
				 */
              System.out.println("Delete: ");
				String res = OperationService.getInstance(cr).checkOnDelete(current.getDep(), operCode,
						current.getB_id(), id_Circulate, new_type1);
				ISLogger.getLogger().warn("res : "+res);
				if (res.equals("tt")) {
					// OperationService.getInstance(cr).openOperation(current.getB_id(),
					// date,operationCode, sum, uid);
					CirculateService.deleteLead(cr, current.getBranch(), current.getB_id().intValue(), gen_Id);

					refreshCirculate();
				} else {
					alert(res);
					return;
				}
			} else {

				alert("Сторнировать проводки невозможно за предыдущие дни!");

			}
			// refreshModel(0);
		} else {
			alert("нажмите на кнопку удаления операции");
		}
		// refreshModel(0);
	}

	////////////////////////////////////////////////////////////////////////////////////////////////
	public void onClick$btn_delete_lead_win() {
		CirculateService.deleteLead(cr, current.getBranch(), current.getB_id()
				.intValue(), generalId_);
		refreshCirculate();
		refreshModel(0);
	}

	// --------------------------------------
	public void onClick$btn_refresh(){
		if(filter.getClient_code() !=null){
			System.out.println("updated :454545");
			refreshModel(0);	
			refreshCirculate();
		}
						
	}
	
	public void onClick$btn_new_dep() {
		
		if (filter.getClient_code() != null) {
			/*if (!DepositService.isClientConfirmed(filter.getClient_code(),
					cr.getAlias())){
				
				return;
			}*/
			initNewDep();
			new_depositWnd.setVisible(true);
			new_depositWnd$p_ser.setReadonly(true);
		}
		new_depositWnd$n_filial.setValue("");
		new_depositWnd$filial_text.setValue("");
		
		new_depositWnd$n_dep.setValue("");
		new_depositWnd$dep_text.setValue("");
		new_depositWnd$n_oper.setValue("");
		new_depositWnd$p_num.setValue("");
		new_depositWnd$p_ser.setValue("");
		new_depositWnd$n_sum.setValue("");
		
	}

	private void initNewDep() {
		new_depositWnd$n_filial.setModel(new ListModelList(DepositService
				.getFilials(cr,uid)));
		new_depositWnd$n_dep.setModel(new ListModelList(DepositService
				.getDeposits(cr)));
		new_depositWnd$p_num.setModel(new ListModelList(DepositService.getSdClerkBooksforOpen(cr, uid)));
		new_dep.setP_num(new_depositWnd$p_num.getText());
			
		new_depositWnd$operDate.setValue(operDate);
		new_dep.setOperDate(operDate);
		new_dep.setEmp_id(uid);
		new_dep.setId_client(filter.getClient_code());
	}
	
	//select * from h where name_ru = '%%

	public void onChange$n_dep$new_depositWnd() {
		new_depositWnd$n_oper.setModel(new ListModelList(DepositService
				.getOperationsForOpen(new_dep.getDep(), cr)));
		new_dep.setDep(new_dep.getDep());
		new_depositWnd$dep_text.setValue(new_depositWnd$n_dep.getValue());
		new_depositWnd$n_oper.setModel(new ListModelList(DepositService
				.getOperationsForOpen(new_depositWnd$n_dep.getValue(), cr)));
		new_depositWnd$p_ser.setValue(new_depositWnd$p_num.getValue());
		new_depositWnd$p_num.setValue(new_depositWnd$p_num.getText());
		new_dep.setP_ser(new_depositWnd$p_num.getValue());
		new_dep.setDep(new_depositWnd$n_dep.getValue());
		boolean prCheck=DepositService.checkPrivCond(cr.getAlias(), new_dep.getDep());
		System.out.println("Check_Dep :"+prCheck);
		ISLogger.getLogger().error("Check_Dep :"+prCheck+", branch: "+cr.getBranch());
		if(prCheck){
			System.out.println("zashel:");
			
			new_depositWnd$btn_open.setVisible(false);
			new_depositWnd$btn_add_prCondDep.setVisible(true);
		}
		else {
			new_depositWnd$btn_add_prCondDep.setVisible(false);
			new_depositWnd$btn_open.setVisible(true);
			
		}
		
	}

	public void onChange$dep_text$new_depositWnd() {
		new_depositWnd$n_oper.setModel(new ListModelList(DepositService
				.getOperationsForOpen(new_dep.getDep(), cr)));
		new_dep.setDep(new_dep.getDep());
		
		
		//new_depositWnd$p_num.setModel(new ListModelList(DepositService.getSdClerkBooksforOpen(cr, uid)));
	}
  public void onChange$p_num$new_depositWnd(){
	  //System.out.println("trtrtr: "+new_depositWnd$p_num.getText());
	  new_depositWnd$p_ser.setValue(new_depositWnd$p_num.getValue());
	  new_depositWnd$p_num.setModel(new ListModelList(DepositService.getSdClerkBooksforOpen(cr, uid)));
	  new_dep.setP_num(new_depositWnd$p_num.getText());  
	  
  }
  public void onChange$id_prCond_desc$add_PrivCond(){
	  
	  add_PrivCond$id_prCond_desc.setModel(new ListModelList(FrmService.getPrivCondParms1(cr.getAlias(), new_dep.getDep())));
	  add_PrivCond$id_prCond_desc.setValue(add_PrivCond$id_prCond_desc.getText());
	  add_PrivCond$pr_id_par.setValue(add_PrivCond$id_prCond_desc.getValue());
	  add_PrivCond$pr_id_desc.setValue(add_PrivCond$id_prCond_desc.getText());
	  System.out.println("id_prCond_desc: "+add_PrivCond$id_prCond_desc.getText());
	  System.out.println("pr_id_par: "+add_PrivCond$id_prCond_desc.getValue());
	  
	  String str2 = add_PrivCond$id_prCond_desc.getValue();
		//sd_pr_cond.setPar(add_PrivCond$id_prCond_desc.getValue());
	  String dep_Name=new_depositWnd$n_dep.getValue();
		System.out.println("fdsfsfsfsds2222: "+str2);
	  System.out.println("id_prCond_val3333: ");
	  System.out.println("count_pered_BookId: "+count);
	  ISLogger.getLogger().error("count_pered_BookId: "+count+", branch: "+cr.getBranch());
	  if(chbook_id){
		   book_id = DepositService.getBookId(cr, new_dep);
		   chbook_id=false;
		   System.out.println("chbook_id_Sd: "+chbook_id);
		   ISLogger.getLogger().error("chBook_id_SD: "+chbook_id+", branch: "+cr.getBranch()+", user_ID : "+uid+",Book_id_OnChange_id_prCond_desc: "+book_id);
		 System.out.println("Book_id_new_888: "+book_id); 
	  }
	  if(count==1){
	add_PrivCond$pr_id_par1.setValue(add_PrivCond$id_prCond_desc.getValue());	  
	add_PrivCond$pr_id_desc.setValue(add_PrivCond$id_prCond_desc.getText());	  
		  
	  }
	  ISLogger.getLogger().error("pars1111: "+cr.getAlias()+" , 2--2222: "+str2+"3-3333_Book_id: "+book_id+", UserId: "+uid);
	  System.out.println(" pars1111 : "+cr.getAlias()+" , 2--2222"+str2+"3-3333_Book_id"+book_id);
	  List<RefData> list_values =FrmService.getSdPrCondValues(cr.getAlias(), str2, book_id,dep_Name);
	  if(list_values.size()==0){
		  add_PrivCond$id_prCond_val.setVisible(false);
		  add_PrivCond$id_prCond_txt.setVisible(true);
		  add_PrivCond$id_prCond_txt.setValue("");
	  }
	  else{
	  add_PrivCond$id_prCond_val.setVisible(true);
	  add_PrivCond$id_prCond_txt.setVisible(false);
	  add_PrivCond$id_prCond_val.setModel(new ListModelList(FrmService.getSdPrCondValues(cr.getAlias(), str2, book_id,dep_Name)));
	  String str3=add_PrivCond$id_prCond_val.getText();
	  String str4=add_PrivCond$id_prCond_val.getValue();
	  System.out.println("str4----: "+str4);
	  System.out.println("str3  :"+str3);
	  
	  add_PrivCond$pr_id_value.setValue(str3);
	  add_PrivCond$pr_id_value.setValue(str4);
	  	  
	  }
	  //add_PrivCond$id_prCond_val.setValue(add_PrivCond$id_prCond_val.getText());
	  //add_PrivCond$id_prCond_val.setValue(add_PrivCond$id_prCond_val.getValue());
	  if(!add_PrivCond$id_prCond_val.getItems().isEmpty()){
		  for (int i=0;i<add_PrivCond$id_prCond_val.getItems().size();i++){
			  System.out.println("child: "+add_PrivCond$id_prCond_val.getItemAtIndex(i));
		  
		  }
		  
	  }
	 
	  
	  	  
  }  
  public void onChange$id_prCond_txt$add_PrivCond(){
	  
	String str7= add_PrivCond$id_prCond_txt.getValue();
	  	  
	add_PrivCond$pr_id_value.setValue(str7);
	  
		  
	  }
	    
  
  public void onChange$id_prCond_val$add_PrivCond(){
	  add_PrivCond$id_prCond_desc
		.setModel(new ListModelList(FrmService.getPrivCondParms1(cr.getAlias(), new_dep.getDep())));
	  add_PrivCond$id_prCond_desc.setValue(add_PrivCond$id_prCond_desc.getText());
		String str1 = add_PrivCond$id_prCond_desc.getText();
		add_PrivCond$pr_id_par.setValue(add_PrivCond$id_prCond_desc.getValue());
		String str2 = add_PrivCond$id_prCond_desc.getValue();
		
		//add_PrivCond$id_prCond_val.setValue(add_PrivCond$id_prCond_val.getText());
		if(add_PrivCond$id_prCond_val instanceof RefCBox &&count==0||count==1||count==2){
			add_PrivCond$id_prCond_val.setValue(add_PrivCond$id_prCond_val.getText());
					
		};
		if(add_PrivCond$id_prCond_val instanceof Textbox &&count==0||count==1||count==2){
			add_PrivCond$id_prCond_val.setValue(add_PrivCond$id_prCond_val.getText());
						
		}
		
		
		//System.out.println("7878: "+add_PrivCond$id_prCond_val.getText());
		// add_PrivCond$id_prCond_val.setValue(add_PrivCond$id_prCond_val.getValue());
		 //System.out.println("7979--: "+add_PrivCond$id_prCond_val.getValue());
		  if(!add_PrivCond$id_prCond_val.getItems().isEmpty()){
			  for (int i=0;i<add_PrivCond$id_prCond_val.getItems().size();i++){
				  System.out.println("child: "+add_PrivCond$id_prCond_val.getItemAtIndex(i).getValue());
			  
			  }
			  
		  }
		 
		  String str3=add_PrivCond$id_prCond_val.getValue();
		  
		  System.out.println("str3  6 :"+str3);
		  
		  add_PrivCond$pr_id_value.setValue(str3);
		  
  }
  
  public void onClick$btn_add_prCondDep$new_depositWnd(){
	  if (CheckNull.isEmpty(new_depositWnd$n_filial.getValue())) {
			alert("Поле - Филиал не может быть пустым");
			return;
		}
		if (CheckNull.isEmpty(new_depositWnd$n_dep.getValue())) {
			alert("Поле - Вид Вклада не может быть пустым");
			return;
		}
		if (CheckNull.isEmpty(new_depositWnd$n_oper.getValue())) {
			alert("Поле - Список Операций не может быть пустым");
			return;
		}
		if (CheckNull.isEmpty(new_depositWnd$n_sum.getValue())) {
			alert("Поле - Сумма не может быть пустым");
			return;
		}
		add_PrivCond.setVisible(true);
		int num=DepositService.getCount(cr.getAlias(), new_dep.getDep());
		add_PrivCond$id_prCond_desc.setValue("");
		add_PrivCond$id_prCond_val.setValue("");
		add_PrivCond$id_prCond_txt.setValue("");
		add_PrivCond$id_date_action.setVisible(false);
		add_PrivCond$id_prCond_Date.setVisible(false);
		add_PrivCond$pr_id_par.setValue("");
		add_PrivCond$pr_id_desc.setValue("");
		add_PrivCond$pr_id_value.setValue("");

		
		add_PrivCond$pr_id_par1.setVisible(false);
		add_PrivCond$pr_id_desc1.setVisible(false);
		add_PrivCond$pr_id_value1.setVisible(false);
		add_PrivCond$pr_id_par1.setValue("");
		add_PrivCond$pr_id_desc1.setValue("");
		add_PrivCond$pr_id_value1.setValue("");
		add_PrivCond$pr_id_par2.setVisible(false);
		add_PrivCond$pr_id_desc2.setVisible(false);
		add_PrivCond$pr_id_value2.setVisible(false);
		add_PrivCond$pr_id_par2.setValue("");
		add_PrivCond$pr_id_desc2.setValue("");
		add_PrivCond$pr_id_value2.setValue("");
		
		add_PrivCond$btn_chk_del.setVisible(false);
		if(prCondList.size()>0){
		prCondList.clear();
		}
		System.out.println("btn_prCondDep_size_list: "+prCondList.size());
        ISLogger.getLogger().error("btn_prCondDep_size_list: "+prCondList.size());
		
		count=0;
		chbook_id=true;
		System.out.println("count_btn_add_prCondDep :"+count);
		ISLogger.getLogger().error("count_btn_add_prCondDep :"+count);
		add_PrivCond$btn_add_row.setVisible(true);
		add_PrivCond$btn_save_chk.setVisible(false);
		System.out.println("new_dep_1212 : "+new_dep.getDep());
		}
        
  
  public void onClick$btn_chk_del$add_PrivCond(){
	  
	  	 System.out.println("onClick$btn_chk_del$add_PrivCond()");
	  	isClicked_btn_del =true;
	  	if(count>0){
	  		count--;
	  		System.out.println("btn_del_Count : "+count);
	  	}
	  	
	  	String str = ((Textbox)((Listcell)(add_PrivCond$ad_prCond.getSelectedItem().getChildren().get(0))).getFirstChild()).getValue();
	  	String str1 = ((Textbox)((Listcell)(add_PrivCond$ad_prCond.getSelectedItem().getChildren().get(1))).getFirstChild()).getValue();
	  	String str4 = ((Textbox)((Listcell)(add_PrivCond$ad_prCond.getSelectedItem().getChildren().get(2))).getFirstChild()).getValue();
	  	System.out.println("asdas: "+str);
	  	System.out.println("asdas12: "+str1);	
	  	String str2 = ((Textbox)((Listcell)(add_PrivCond$ad_prCond.getSelectedItem().getChildren().get(0))).getFirstChild()).getName();
	  	String str3 = ((Textbox)((Listcell)(add_PrivCond$ad_prCond.getSelectedItem().getChildren().get(0))).getFirstChild()).getId();
	  	//	System.out.println("elemnets "+listPl.getId());
	  		System.out.println("asdas13: "+str4);
	  		System.out.println("asdas14: "+str3);
		if (str3.equalsIgnoreCase("pr_id_par1")) {
			add_PrivCond$pr_id_par1.setVisible(false);
			add_PrivCond$pr_id_desc1.setVisible(false);
			add_PrivCond$pr_id_value1.setVisible(false);
		} else if (str3.equalsIgnoreCase("pr_id_par")) {
			add_PrivCond$pr_id_par.setVisible(false);
			add_PrivCond$pr_id_desc.setVisible(false);
			add_PrivCond$pr_id_value.setVisible(false);

		}
		else if(str3.equalsIgnoreCase("pr_id_par2")){
			add_PrivCond$pr_id_par2.setVisible(false);
			add_PrivCond$pr_id_desc2.setVisible(false);
			add_PrivCond$pr_id_value2.setVisible(false);			
		}
		for (int i = 0; i < prCondList.size(); i++) {
			System.out.println("size_first:" + prCondList.size());
			System.out.println("elements_prCondList: " + i + " " + prCondList.get(i).getPar());
			System.out.println("elements_prCondList: " + i + " " + prCondList.get(i).getName_par());
			System.out.println("elements_prCondList: " + i + " " + prCondList.get(i).getValue());
			if (prCondList.get(i).getName_par().equalsIgnoreCase(str1)) {
				prCondList.remove(i);
				System.out.println("size_after:" + prCondList.size());
			}
		}

	}

  public void onClick$btn_chk_exit$add_PrivCond(){
	 
	  int num=DepositService.getCount(cr.getAlias(), new_dep.getDep());
	  System.out.println("prCondList :"+prCondList.size());
	  System.out.println("Num :"+num);
	  if(num==prCondList.size()){
		  add_PrivCond.setVisible(false);
		  Double sum = Double.valueOf(new_depositWnd$n_sum.getValue());
	      new_dep.setSum(sum);
	    ISLogger.getLogger().error("chBook_id_SD: "+chbook_id+", branch: "+cr.getBranch()+", user_ID : "+uid+",Book_id_On_btn_chk_exit: "+book_id);  
		Res result = DepositService.openDepNew(cr, new_dep,book_id,prCondList);
		 if (result.getName() != null && !(result.getName().isEmpty())) {
				alert(result.getName());
				return;
			}
		  alert("вклад успешно открыт");
		 
		  onClick$btn_refresh();
		  
		//  _needsTotalSizeUpdate = true;
			
			refreshModel(0);
		//	init();
			initCirculate();
			refreshCirculate();
			
			
			new_depositWnd.setVisible(false);
			frm.setVisible(false);
			grd.setVisible(true);
			onDoubleClick$dataGrid();
			
		   }
	  else{
		  alert("не все индивидулные параметри заполнены !");
	  }
	  
	    
  }
  public void onClick$btn_interrupt$add_PrivCond(){
	  System.out.println("bookId_delete_prv_Cond: "+book_id);
	  
	  isClick_btn_interrupt=true;
	  add_PrivCond.setVisible(false);
	  new_depositWnd.setVisible(false);
	  CirculateService.deleteOperation(cr, current.getBranch(),
				book_id);
		refreshCirculate();
		refreshModel(0);
		System.out.println("btn_interrupt_size : "+prCondList.size());
		ISLogger.getLogger().error("btn_interrupt_size : "+prCondList.size()+", User_Id: "+uid+", branch: "+cr.getBranch());
	  
  }

	public void onClick$btn_add_row$add_PrivCond() {
		System.out.println("proshel-----12");
		System.out.println("Count++ :"+count);
		count++;
		
		System.out.println("After_Count++ :"+count);
     /*   add_PrivCond$btn_add_row.addEventListener(Events.ON_CLICK, new EventListener(){
             
					@Override
					public void onEvent(Event evt) throws Exception {
					count++;				
				
					}
		});     */
		add_PrivCond$btn_save_chk.setVisible(true);
		add_PrivCond$btn_add_row.setVisible(false);
		add_PrivCond$id_date_action.setVisible(true);
		System.out.println("sssssssss");
		System.out.println("count_add_rws : "+count);
		add_PrivCond$id_prCond_Date.setVisible(true);
		String oper_date = df.format(operDate);

		System.out.println("rererer: " + cr.getAlias() + "  , trtr: " + new_dep.getDep());
		add_PrivCond$id_prCond_Date.setValue(oper_date);
		System.out.println("trtrtrtrtrt1");
		System.out.println("CountUp: "+count);
		if(count==2){
			add_PrivCond$pr_id_par1.setVisible(true);  
			add_PrivCond$pr_id_desc1.setVisible(true);  
			add_PrivCond$pr_id_value1.setVisible(true);
			System.out.println("aggsgsgsdgsg:");
			add_PrivCond$pr_id_par1.setValue(prCondList.get(0).getPar());
			add_PrivCond$pr_id_desc1.setValue(prCondList.get(0).getName_par());
			add_PrivCond$pr_id_value1.setValue(prCondList.get(0).getValue());
			
			add_PrivCond$pr_id_par.setValue("");
			add_PrivCond$pr_id_desc.setValue("");
			add_PrivCond$pr_id_value.setValue("");
		  }
        if(count==3){
        	add_PrivCond$pr_id_par2.setVisible(true);
        	add_PrivCond$pr_id_desc2.setVisible(true);
        	add_PrivCond$pr_id_value2.setVisible(true);
        	
        	add_PrivCond$pr_id_par2.setValue(prCondList.get(1).getPar());
        	add_PrivCond$pr_id_desc2.setValue(prCondList.get(1).getName_par());
        	add_PrivCond$pr_id_value2.setValue(prCondList.get(1).getValue());
        	
        	add_PrivCond$pr_id_par.setValue("");
			add_PrivCond$pr_id_desc.setValue("");
			add_PrivCond$pr_id_value.setValue("");
        	        }
		
		add_PrivCond$id_prCond_desc.setModel(new ListModelList(FrmService.getPrivCondParms1(cr.getAlias(), new_dep.getDep())));
		
		
	//	String txt4 = ((Textbox)((Listcell)((Listitem)(add_PrivCond$ad_prCond.getItems().get(0))).getChildren().get(0)).getFirstChild()).getValue();
	//	String txt5 = ((Textbox)((Listcell)((Listitem)(add_PrivCond$ad_prCond.getItems().get(1))).getChildren().get(0)).getFirstChild()).getValue();
	//	String txt6 = ((Textbox)((Listcell)((Listitem)(add_PrivCond$ad_prCond.getItems().get(2))).getChildren().get(0)).getFirstChild()).getValue();

	//	String txt7 = ((Textbox)((Listcell)(add_PrivCond$ad_prCond.getSelectedItem().getChildren().get(0))).getFirstChild()).getId();
		
		String id1= ((Textbox)((Listcell)((Listitem)(add_PrivCond$ad_prCond.getItems().get(0))).getChildren().get(0)).getFirstChild()).getId();
		String id2= ((Textbox)((Listcell)((Listitem)(add_PrivCond$ad_prCond.getItems().get(1))).getChildren().get(0)).getFirstChild()).getId();
		String id3= ((Textbox)((Listcell)((Listitem)(add_PrivCond$ad_prCond.getItems().get(2))).getChildren().get(0)).getFirstChild()).getId();
		
		
		if(isClicked_btn_del &&!isClick_btn_interrupt){
		if(!add_PrivCond$pr_id_par.isVisible()){
			System.out.println("pr_id_par:----- "+id1);
			add_PrivCond$pr_id_par.setVisible(true);
        	add_PrivCond$pr_id_desc.setVisible(true);
        	add_PrivCond$pr_id_value.setVisible(true);
			add_PrivCond$pr_id_par.setValue(prCondList.get(2).getPar());
        	add_PrivCond$pr_id_desc.setValue(prCondList.get(2).getName_par());
        	add_PrivCond$pr_id_value.setValue(prCondList.get(2).getValue());
		}
		else if(!add_PrivCond$pr_id_par1.isVisible()){
			System.out.println("pr_id_par1:----- "+id2);
			add_PrivCond$pr_id_par1.setVisible(true);
        	add_PrivCond$pr_id_desc1.setVisible(true);
        	add_PrivCond$pr_id_value1.setVisible(true);
			add_PrivCond$pr_id_par1.setValue(prCondList.get(0).getPar());
        	add_PrivCond$pr_id_desc1.setValue(prCondList.get(0).getName_par());
        	add_PrivCond$pr_id_value1.setValue(prCondList.get(0).getValue());
			
		}
		else if(!add_PrivCond$pr_id_par2.isVisible()){
			System.out.println("pr_id_par:------ "+id3);

			add_PrivCond$pr_id_par2.setVisible(true);
        	add_PrivCond$pr_id_desc2.setVisible(true);
        	add_PrivCond$pr_id_value2.setVisible(true);
			add_PrivCond$pr_id_par2.setValue(prCondList.get(1).getPar());
        	add_PrivCond$pr_id_desc2.setValue(prCondList.get(1).getName_par());
        	add_PrivCond$pr_id_value2.setValue(prCondList.get(1).getValue());
			
			
		};
		System.out.println("id1_par: "+id1);
		System.out.println("id2_par: "+id2);
		System.out.println("id3_par: "+id3);
		System.out.println("comes to this line");
	
		 }
		System.out.println("proshel-----");
		
		
		
	}

	private void setParent(Listbox ad_prCond2) {
		// TODO Auto-generated method stub
		
	}

	public void onClick$btn_save_chk$add_PrivCond() {
		add_PrivCond$id_prCond_desc
				.setModel(new ListModelList(FrmService.getPrivCondParms1(cr.getAlias(), new_dep.getDep())));
		add_PrivCond$id_prCond_desc.setValue(add_PrivCond$id_prCond_desc.getText());
		String str1 = add_PrivCond$id_prCond_desc.getText();
		add_PrivCond$pr_id_par.setValue(add_PrivCond$id_prCond_desc.getValue());
		String str2 = add_PrivCond$id_prCond_desc.getValue();

		new_dep.setDep(new_depositWnd$n_dep.getValue());
		new_dep.setP_ser(new_depositWnd$p_num.getValue());
		new_dep.setP_num(new_depositWnd$p_num.getText());

		if (str1 != null && str2 != null) {
			System.out.println("erererere: " + add_PrivCond$id_prCond_desc.getText());
			System.out.println("tteffvhjvfjf: " + add_PrivCond$id_prCond_desc.getValue());
			//sd_pr_cond.setName_par(str1);
			//sd_pr_cond.setPar(str2);
		}

		int num = DepositService.getCount(cr.getAlias(), new_dep.getDep());
		
		String str7=add_PrivCond$id_prCond_desc.getValue();
		System.out.println("111----"+str7);
		String str8=add_PrivCond$id_prCond_desc.getText();
		System.out.println("112----"+str8);
		//sd_pr_cond.setValue(add_PrivCond$pr_id_value.getValue());
		String str9=add_PrivCond$pr_id_value.getText();
			
		System.out.println("113----"+str9);
		//System.out.println("Book_id_{}: " + book_id);
		//add_PrivCond$id_prCond_val.setValue(add_PrivCond$id_prCond_val.getValue());

		System.out.println("trtrtrtrtrt2");
		
		
		
		//sd_pr_cond.setValue(add_PrivCond$id_prCond_val.getValue());
		 int size = prCondList.size();
		 System.out.println("size_prCond_btn_save: "+size);
        ISLogger.getLogger().error("size_prCond_btn_save: "+size);
		 //String str = ((Textbox)((Listcell)(add_PrivCond$ad_prCond.getSelectedItem().getChildren().get(0))).getFirstChild()).getValue();
					 
		String txt1 = ((Textbox)((Listcell)((Listitem)(add_PrivCond$ad_prCond.getItems().get(0))).getChildren().get(0)).getFirstChild()).getValue();
		String txt2 = ((Textbox)((Listcell)((Listitem)(add_PrivCond$ad_prCond.getItems().get(1))).getChildren().get(0)).getFirstChild()).getValue();
		String txt3 = ((Textbox)((Listcell)((Listitem)(add_PrivCond$ad_prCond.getItems().get(2))).getChildren().get(0)).getFirstChild()).getValue();
		
		
			if(size>=1) {
				int par1 = (Integer.parseInt((String) (txt1==""?"0":txt1)));
				int par2 = (Integer.parseInt((String)(txt2==""?"1":txt2)));
				int par3 = (Integer.parseInt((String)(txt3==""?"2":txt3)));
				ISLogger.getLogger().error("par1: "+par1);
				System.out.println("par1: "+par1);
				ISLogger.getLogger().error("par2: "+par2);
				System.out.println("par2: "+par2);
				ISLogger.getLogger().error("par3: "+par3);
				System.out.println("par3: "+par3);
			if(par1!=par2 && par2!=par3 &&par1!=par3){
			 prCondList.add(new Sd_priv_Cond(str7, str8, str9));
			 System.out.println("Confirm1: "+txt1);
			 ISLogger.getLogger().error("Confirm1: "+txt1);
			 System.out.println("Confirm2: "+txt2);
			 ISLogger.getLogger().error("Confirm2: "+txt2);
			 System.out.println("Confirm3: "+txt3);
			 ISLogger.getLogger().error("Confirm3: "+txt3);
			 System.out.println("sert: ");
			 }
			 else{
				 alert("Нельзя вводить одинаковые параметры! ");
				 return;				 
			 }
			}
			
			if(size==0)
			{
			prCondList.add(new Sd_priv_Cond(str7, str8, str9));
			System.out.println("Elements_first_added: "+txt1);
			ISLogger.getLogger().error("Elements_first_added: "+txt1);
		};
		 for(int i=0;i<=size;i++){
		System.out.println("---1"+i+ " : "+prCondList.get(i).getName_par());
		System.out.println("---2"+i+"  : "+prCondList.get(i).getPar());
		System.out.println("---3"+i+"  : "+prCondList.get(i).getValue());
		int size1 = prCondList.size();
		System.out.println("size1_prCond: "+size1);
		ISLogger.getLogger().error("size1_prCond: "+size1);
		}
		
		add_PrivCond$btn_add_row.setVisible(true);
		add_PrivCond$btn_save_chk.setVisible(false);
		int num1=DepositService.getCount(cr.getAlias(), new_dep.getDep());
		if(num1==prCondList.size()&& count==3){
			System.out.println("NUM1_DEP_HIDE: ");
			ISLogger.getLogger().error("NUM1_DEP_HIDE: ");
			add_PrivCond$btn_add_row.setVisible(false);			
			add_PrivCond$btn_save_chk.setVisible(false);
			add_PrivCond$btn_chk_del.setVisible(false);
		}  
	}
	public void onClick$btn_open$new_depositWnd() {
		if (CheckNull.isEmpty(new_depositWnd$n_filial.getValue())) {
			alert("Поле - Филиал не может быть пустым");
			return;
		}
		if (CheckNull.isEmpty(new_depositWnd$n_dep.getValue())) {
			alert("Поле - Вид Вклада не может быть пустым");
			return;
		}
		if (CheckNull.isEmpty(new_depositWnd$n_oper.getValue())) {
			alert("Поле - Список Операций не может быть пустым");
			return;
		}
		if (CheckNull.isEmpty(new_depositWnd$n_sum.getValue())) {
			alert("Поле - Сумма не может быть пустым");
			return;
		}
		Double sum = Double.valueOf(new_depositWnd$n_sum.getValue());

		new_dep.setSum(sum);
		new_dep.setDep(new_depositWnd$n_dep.getValue());
		new_dep.setP_ser(new_depositWnd$p_num.getValue());
		new_dep.setP_num(new_depositWnd$p_num.getText());
		System.out.println("Dep_name_priOtkritiye :"+new_dep.getDep());
		System.out.println("Dep_name_alias :"+cr.getAlias());
		// &&prCondList.size()!=num
		boolean resCheck=DepositService.checkPrivCond(cr.getAlias(), new_dep.getDep());
		int num=DepositService.getCount(cr.getAlias(), new_dep.getDep());
		if(resCheck){
			add_PrivCond.setVisible(true);
			int num1=DepositService.getCount(cr.getAlias(), new_dep.getDep());
			
			  System.out.println("prCondList :"+prCondList.size());
			  System.out.println("Num1 :"+num1);
			  if(num1==prCondList.size()){
				  System.out.println("Book_id_opened_456: "+book_id);
				Res result = DepositService.openDepNew(cr, new_dep,book_id,prCondList);
				if (result.getName() != null && !(result.getName().isEmpty())) {
					alert(result.getName());
					return;
				}
				   }
			  else{
				  alert("не все индивидулные параметри заполнены !");
				  return;
			  }
			
			/*
			System.out.println("Dep_Priv_Cond :------- ");
			add_PrivCond$id_prCond_desc.setModel(new ListModelList(FrmService.getPrivCondParms1(cr.getAlias(), new_dep.getDep())));
			add_PrivCond$id_prCond_desc.setValue(add_PrivCond$id_prCond_desc.getText());
			add_PrivCond$pr_id_par.setValue(add_PrivCond$id_prCond_desc.getValue());
			alert("Индивидуальные параметры вклада должны быть введены!");
			*/
		}
		else  {
			Res result = DepositService.openDep(cr, new_dep);
			if (result.getName() != null && !(result.getName().isEmpty())) {
				alert(result.getName());
				return;
			}
			
		}
			
			//add_PrivCond.setVisible(true);
		//	int num=DepositService.getCount(cr.getAlias(), new_dep.getDep());
			
	/*	if(prCondList.size()==0){
			alert("Индивидулные параметри вклада не введен!");
			return;
			}  */
			
		 
		 
		// for(int i=0;i<=num;i++){
		
		
		//add_PrivCond$btn_add_row.addEventListener(Events.ON_CLICK, new EventListener() {});
			/*    
			
				@Override
					public void onEvent(Event evt) throws Exception {
						
						add_PrivCond$btn_save_chk.setVisible(true);
					add_PrivCond$btn_add_row.setVisible(false);
						add_PrivCond$id_date_action.setVisible(true);
						System.out.println("sssssssss");
						add_PrivCond$id_prCond_Date.setVisible(true);
						String oper_date=df.format(operDate);
						
						System.out.println("rererer: "+cr.getAlias()+"  , trtr: "+new_dep.getDep());
						add_PrivCond$id_prCond_Date.setValue(oper_date);
						System.out.println("trtrtrtrtrt1");
						//sd_pr_cond.setName_par(add_PrivCond$id_prCond_desc.getText());
						//sd_pr_cond.setPar(add_PrivCond$id_prCond_desc.getValue());
						
						System.out.println("trtrtrtrtrt2"); */
						//sd_pr_cond.setValue(add_PrivCond$id_prCond_val.getValue());
						// prCondList.add(new Sd_priv_Cond(sd_pr_cond.getPar(),sd_pr_cond.getName_par(),null /*sd_pr_cond.getValue()*/)); 
						
						/*
						System.out.println("trtrtrtrtrt3");
						add_PrivCond$pr_id_par.setValue(add_PrivCond$id_prCond_desc.getValue());
						sd_pr_cond.setPar(add_PrivCond$id_prCond_desc.getValue());
						sd_pr_cond.setName_par(add_PrivCond$id_prCond_desc.getText());
						add_PrivCond$id_prCond_val.setModel(new ListModelList(FrmService.getSdPrCondValues(cr.getAlias(), sd_pr_cond.getPar(), book_id)));
						System.out.println("Book_id_{}: "+book_id);
						add_PrivCond$id_prCond_val.setValue(add_PrivCond$id_prCond_val.getValue());
						
						System.out.println("4545454  : "+add_PrivCond$id_prCond_desc.getText());		            		
					/*	row.appendChild(new Listcell());
						row.appendChild(new Listcell());
						row.appendChild(new Listcell());
						System.out.println("onClose");  */
					  
				
		  
		
	//	} 
	//}
                  
 
		//Res result = DepositService.openDep(cr, new_dep);
		
		//System.out.println("Book_id: "+book_id);
		//System.out.println("book_id_pred :"+book_id);
		//Res result = DepositService.openDepNew(cr, new_dep,book_id);
		//if (result.getName() != null && !(result.getName().isEmpty())) {
		//	alert(result.getName());
		//	return;
		//} 
		_needsTotalSizeUpdate = true;
		
		refreshModel(0);
	//	initCirculate();
		refreshCirculate();
		
		
		new_depositWnd.setVisible(false);
		frm.setVisible(false);
		grd.setVisible(true);
		onDoubleClick$dataGrid();
		
	}
	public void onClick$btn_add_row(){
		id_kod.setVisible(true);
		
	}

	public void onClick$btn_filter() {
		searchWnd$s_states.setModel(new ListModelList(AssistantService
				.getDepStates(cr.getAlias())));
		searchWnd.setVisible(true);
	}

	public void onClick$btn_ok$searchWnd() {
		filter.setState_id((searchWnd$s_states.getValue().isEmpty()) ? 0
				: Integer.valueOf(searchWnd$s_states.getValue()));
		_needsTotalSizeUpdate = true;
		refreshModel(0);
		searchWnd.setVisible(false);
	}
	public void onClick$btn_clear$searchWnd(){
	 //  filter.clearFilterFields();
	//	filter = new SD_BookFilter();
		System.out.println("filial: "+filter.getFilial());
		filter.clearFilterFields();
		
	   ZkUtils.clearForm(search_grd);
	   ZkUtils.clearForm(searchWnd);
	 //  System.out.println("filial_cleared: "+filter.getFilial());
	 //  System.out.println("clearSearch_Grd");
	   }

	public void onClick$btn_errors() {
		Double b_id = current.getB_id();
		errorWnd$error_listbox.setModel(new ListModelList(ErrorService
				.getErrors(b_id, cr.getAlias())));
		errorWnd$error_listbox.setItemRenderer(new ListitemRenderer() {
			@Override
			public void render(Listitem item, Object data) throws Exception {
				SD_Error error = (SD_Error) data;
				item.appendChild(new Listcell(df.format(error.getV_date())));
				item.appendChild(new Listcell(error.getNote()));
			}
		});
		errorWnd.setVisible(true);
	}

	public void onClick$btn_prc() {
		//alert("РС га кириш");
		prcWnd.setVisible(true);
		prcWnd$prc_listbox.setModel(new ListModelList(PrcService
				.getAccruedPercentage(current.getB_id(), cr)));
		prcWnd$prc_listbox.setItemRenderer(new ListitemRenderer() {
			@Override
			public void render(Listitem row, Object data) throws Exception {
				Percentage nightCharge = (Percentage) data;

				row.appendChild(new Listcell(nightCharge.getV_date().toString()));
				row.appendChild(new Listcell(nightCharge.getTurn_cr() + ""));
				row.appendChild(new Listcell(nightCharge.getTurn_db() + ""));
				row.appendChild(new Listcell(nightCharge.getSaldo() + ""));
				row.appendChild(new Listcell(nightCharge.getPc() + " "));
				row.appendChild(new Listcell(nightCharge.getGeneral_id() + " "));
				row.appendChild(new Listcell(nightCharge.getFx_code() + " "));
				row.appendChild(new Listcell(nightCharge.getNote()));
				// row.appendChild(new
				// Listcell(generalDao.getUser(nightCharge.getEmp())));
				// row.appendChild(new
				// Listcell(generalDao.getClerk(nightCharge.getBook_id())));
			}
		});
	}

	public void onClick$btn_capdate() {
		Date date = AssistantService.getLookBookDate(cr, current.getBranch(),
				current.getB_id());
		alert("Дата ближайшей капитализации = "
				+ (date != null ? df.format(date) : ""));
	}

	public void onClick$btn_save_changed$clerkWnd() {
		//alert("clerk got in");
		ClerkService.updateClerk(current.getBranch(), current.getB_id(),
				current.getP_ser(), current.getP_num(), cr.getAlias());
		
		
	}
	
	public void onClick$btn_clerk(){
		List<Clerk_Book> list=ClerkService.showClerkbook(current.getBranch(), current.getB_id(),cr.getAlias());
				
		current.setP_ser(list.size()==0?"":String.valueOf(list.get(0).getB_ser()));
		System.out.println("ser_book :"+(list.size()==0?"":String.valueOf(list.get(0).getB_ser())));
		current.setP_num(String.valueOf(list.size()==0?"":list.get(0).getB_num()));
		System.out.println("num_book :"+String.valueOf(list.size()==0?"":list.get(0).getB_num()));
		
	}

	// ---------------Шаблоны------------------
	// Инициализация параметров для шаблонов
	private Map<String, Object> initParamsForReport() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("un", cr.getLogin());
		params.put("pwd", cr.getPassword());
		params.put("branch", cr.getBranch());
		params.put("alias", cr.getAlias());
		params.put("book_id", current.getB_id());
		
	
		System.out.println("------MAP:"+params.get("un").toString());
		return params;
		
	}

	// Заявление на открытие счета
	public void onClick$btn_appl() {
		//alert("clerk got in Zayavlen");
		//Circulate cir2 = (Circulate)circulateListbox.getSelectedItem().getValue();
		Map<String, Object> params = initParamsForReport();
		
		//System.out.println("------MAP------:"+params.get("un").toString());

		if (AssistantService.isDepMonetary(current.getDep())) {
			ApplVal applVal = new ApplVal();
			applVal.getReport("com.is.sd_books.report.ApplVal",
					"/reports/ApplVal.doc", "new file", params);
		} else {
			ApplSum applSum = new ApplSum();
			//System.out.println("Applsum:");
			applSum.getReport("com.is.sd_books.report.ApplSum",
					"/reports/ApplSum.doc", "new file", params);
		}
	}

	// Кассовый Ордер
	public void onClick$btn_cashorder() {
		Map<String, Object> params = initParamsForReport();

		String account = current.getAccount();
		if (account.charAt(0) == 49 && account.charAt(1) == 48) {
			if (AssistantService.isDepMonetary(current.getDep())) {

			}
		} else {
			if (AssistantService.isDepMonetary(current.getDep())) {
				CredOrV.getInstance().getReport(
						"com.is.sd_books.report.CredOrV",
						"/reports/CredOrV.xls", "CredOrV", params);
			} else {
				CredOrS.getInstance().getReport(
						"com.is.sd_books.report.CredOrS",
						"/reports/CredOrS.xls", "new file", params);
			}
		}
	}

	public void onClick$btn_shahsiy2015() {
		Map<String, Object> params = initParamsForReport();
		Shahsiy2015.getInstance().getReport(
				"com.is.sd_books.report.Shahsiy2015",
				"/reports/04_Shahsiy_2015.xls", "shahsiy2015", params);
	}

	public void onClick$btn_creditorder() {
		Circulate cir1 = (Circulate)circulateListbox.getSelectedItem().getValue();
		Long genId=((cir1.getGeneral_id()!=null)?cir1.getGeneral_id():0);
		ISLogger.getLogger().error("gen_id_cred_order: "+genId);
		if(genId!=0){
		if(cir1.getCirculate_cr().doubleValue()>0.00){
			
			
			System.out.println(cir1.getCirculate_cr());
			System.out.println(cir1.getId().longValue());
		String path = Executions.getCurrent().getDesktop().getWebApp()
				.getRealPath("/reports/Credit_order.xls");
		Integer id = current.getB_id().intValue();
		String branch = current.getBranch();
		Long gen_id=cir1.getGeneral_id();

		new ReportBuilder(new CreditOrder(cr.getLogin(), cr.getPassword(),
				cr.getAlias(),cir1,gen_id)).setBranch(branch).setFileName("Credit_Order")
				.setId(id).setName("Credit_order.xls").setPath(path).build().getReport();
		}
		else{
			alert("Вы не выбрали приходное поле");			
			}
		}
		else {
			alert ("Сначала проведите операцию!");
		}
	}

	// Кассовый Ордер Часть 2
	public void onClick$btn_cashorder_part2() {
		String path = getRealPath("/reports/Cash_order_2part.xls");
		Integer id = current.getB_id().intValue();
		String branch = current.getBranch();

		new ReportBuilder(new CashOrder2(cr)).setBranch(branch)
				.setFileName("CashOrder2").setId(id).setPath(path).setName("")
				.build().getReport();
	}

	// Кассовый Ордер Часть 3
	public void onClick$btn_cashorder_part3() {
		String path = getRealPath("/reports/Cash_order_3part.xls");
		Integer id = current.getB_id().intValue();
		String branch = current.getBranch();

		new ReportBuilder(new CashOrder3(cr)).setBranch(branch)
				.setFileName("CashOrder2").setId(id).setPath(path).setName("")
				.setUid(uid).build().getReport();
	}

	// Расходный Ордер
	public void onClick$btn_debit_order() {
		Circulate cir = (Circulate)circulateListbox.getSelectedItem().getValue();
		Long generalId=((cir.getGeneral_id()!=null)?cir.getGeneral_id():0);
		ISLogger.getLogger().error("gen_id_Debit_order: "+generalId);
		if(generalId!=0){
		if(cir.getCirculate_db().doubleValue()>0.00){
		String path = getRealPath("/reports/Debit_order.xls");
		Integer id = current.getB_id().intValue();
		String branch = current.getBranch();
		
		//Circulate cir = (Circulate)circulateListbox.getSelectedItem().getValue();
		System.out.println(cir.getCirculate_db());
		System.out.println(cir.getId().longValue());
		
		circulateListbox.getSelectedItem().getValue();
		circulateListbox.getItemRenderer().toString();
		circulateListbox.getSelectedItem().getValue().toString();
				
		System.out.println("TTTTTT---"+circulateListbox.getSelectedItem().getChildren().toArray().toString().length());
		
		//System.out.println("---789:--"+circulateListbox.getItemRenderer().toString());
		//System.out.println("param----:"+circulateListbox.getModel().getElementAt(circulateListbox.getSelectedIndex()).toString());
		//System.out.println("param----790--:"+circulateListbox.getModel().getElementAt(circulateListbox.getSelectedItem().));
	     System.out.println();	
		new ReportBuilder(new DebitOrder(cr,cir)).setBranch(branch)
				.setFileName("DebitOrder").setId(id).setPath(path).setName("Debit_Order.xls")
				.setUid(uid).build().getReport();
		}
		else{
		alert("Вы не выбрали расходное поле");			
		}
		}
		else {
			alert("Сначала проведите операцию!");
		}
	}
	
	//Распечатка книжка 1
	public void onClick$btn_sdBook_btn1(){
	Circulate cir = (Circulate)circulateListbox.getSelectedItem().getValue();
	cir.getTurn_code();
	ISLogger.getLogger().error("Turn_code: "+cir.getTurn_code());
	Long generalId=((cir.getGeneral_id()!=null)?cir.getGeneral_id():0);
	ISLogger.getLogger().error("gen_id_sdBook_btn1: "+generalId);
	
	if(generalId!=0){
		if(cir.getCirculate_cr().doubleValue()>0.00){
		String path = getRealPath("/reports/02_omonat1_hlk_s.xls");
		Integer id = current.getB_id().intValue();
		String branch = current.getBranch();
		
		//Circulate cir = (Circulate)circulateListbox.getSelectedItem().getValue();
		System.out.println(cir.getCirculate_db());
		System.out.println(cir.getId().longValue());
		
		circulateListbox.getSelectedItem().getValue();
		circulateListbox.getItemRenderer().toString();
		circulateListbox.getSelectedItem().getValue().toString();
				
		System.out.println("TTTTTT---"+circulateListbox.getSelectedItem().getChildren().toArray().toString().length());
		
		//System.out.println("---789:--"+circulateListbox.getItemRenderer().toString());
		//System.out.println("param----:"+circulateListbox.getModel().getElementAt(circulateListbox.getSelectedIndex()).toString());
		//System.out.println("param----790--:"+circulateListbox.getModel().getElementAt(circulateListbox.getSelectedItem().));
	     System.out.println();	
		new ReportBuilder(new SdBook_prn1(cr,cir)).setBranch(branch)
				.setFileName("OmonatSdBook1").setId(id).setPath(path).setName("02_omonat1_hlk_s.xls")
				.setUid(uid).build().getReport();
		}
		else{
		alert("Вы не выбрали приходное поле");			
		}
		}
		else {
			alert("Сначала проведите операцию!");
		}
	}
	
	public void onClick$btn_sdBook_btn2(){
		Circulate cir = (Circulate)circulateListbox.getSelectedItem().getValue();
		Long generalId=((cir.getGeneral_id()!=null)?cir.getGeneral_id():0);
		ISLogger.getLogger().error("gen_id_sdBook_btn2: "+generalId);
				
		ISLogger.getLogger().error("Turn_code: "+cir.getFx_code());
		if(generalId!=0){
			if(cir.getCirculate_cr().doubleValue()>0.00){
			String path = getRealPath("/reports/03_omonat2_2015_agro.xls");
			Integer id = current.getB_id().intValue();
			String branch = current.getBranch();
			
			//Circulate cir = (Circulate)circulateListbox.getSelectedItem().getValue();
			System.out.println(cir.getCirculate_db());
			System.out.println(cir.getId().longValue());
			
			circulateListbox.getSelectedItem().getValue();
			circulateListbox.getItemRenderer().toString();
			circulateListbox.getSelectedItem().getValue().toString();
					
			System.out.println("TTTTTT---"+circulateListbox.getSelectedItem().getChildren().toArray().toString().length());
			
			//System.out.println("---789:--"+circulateListbox.getItemRenderer().toString());
			//System.out.println("param----:"+circulateListbox.getModel().getElementAt(circulateListbox.getSelectedIndex()).toString());
			//System.out.println("param----790--:"+circulateListbox.getModel().getElementAt(circulateListbox.getSelectedItem().));
		     System.out.println();	
			new ReportBuilder(new SdBook_prn2(cr,cir)).setBranch(branch)
					.setFileName("OmonatSdBook2").setId(id).setPath(path).setName("03_omonat2_2015_agro.xls")
					.setUid(uid).build().getReport();
			}
			else{
			alert("Вы не выбрали приходное поле");			
			}
			}
			else {
				alert("Сначала проведите операцию!");
			}
		
	}
	
	public void onClick$btn_dep() {
		//alert("clerk got in Dog");
		Map<String, Object> params = initParamsForReport();
		//System.out.println("------MAP------:"+params.get("un").toString());

		/*if (AssistantService.isDepMonetary(current.getDep())) {
			ApplVal applVal = new ApplVal();
			applVal.getReport("com.is.sd_books.report.ApplVal",
					"/reports/SD_Dep_val.doc", "new file", params);
		} else {}*/
			ApplDog applDog = new ApplDog();
			//System.out.println("Applsum:");
			applDog.getReport("com.is.sd_books.report.ApplDog",
					"/reports/SD_Dep_sum.doc", "new file", params);
		
	}
	
	

	// ----------------------------------------

	// --------------Utility Methods ------------------
	public String getRealPath(String path) {
		return Executions.getCurrent().getDesktop().getWebApp()
				.getRealPath(path);
	}

	// -------------------------------------------------
	// -------------Getters and Setters --------------
	public SD_Book getCurrent() {
		return current;
	}

	public void setCurrent(SD_Book current) {
		this.current = current;
	}

	public SD_BookFilter getFilter() {
		return filter;
	}

	public void setFilter(SD_BookFilter filter) {
		this.filter = filter;
	}

	public Deposit getNew_dep() {
		return new_dep;
	}

	public void setNew_dep(Deposit new_dep) {
		this.new_dep = new_dep;
	}
	public void onChange$f_oper(){
		
		if(f_oper.getValue().equals("4") || f_oper.getValue().equals("54"))
		{
			f_sum_text.setVisible(false);
			f_sum.setVisible(false);
		}
		else {
			f_sum_text.setVisible(true);
			f_sum.setVisible(true);
		};
		
		if(f_oper.getValue().equals("31")||f_oper.getValue().equals("150")){
			double per=0.0d;
			Integer operCode = Integer.valueOf(f_oper.getValue());
			System.out.println(operCode);
			java.sql.Date operdate = CheckNull.d2sql(operDate);
			System.out.println(operdate);
			per=OperationService.getInstance(cr).offerSD(current.getB_id(), operdate, operCode);
			f_sum.setValue(String.valueOf(per));		
		}else{
			f_sum.setValue("");
		}
		
	}
}
