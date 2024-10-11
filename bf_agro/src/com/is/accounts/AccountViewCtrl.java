package com.is.accounts;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
// import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.zkoss.util.resource.Labels;
import org.zkoss.web.Attributes;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Longbox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Panel;
import org.zkoss.zul.Panelchildren;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;
import org.zkoss.zul.event.PagingEvent;

import com.is.ISLogger;
import com.is.bfreport.RepWndViewCtrl;
import com.is.clientcrm.utils.ZkUtils;
// import com.is.user.Role;
import com.is.user.User;
//import com.is.user.UserService;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.RefData;

@SuppressWarnings("serial")
public class AccountViewCtrl extends GenericForwardComposer{
	private Panel accountmain;
	private Panelchildren accmain;
	public Window repWndDialog;
	private Div frm;
  	private Paging contactPaging;
   	private Div grd;
   	private Listbox dataGrid;
   	private Grid addgrd,frmgrd,fgrd;
   	private Toolbarbutton btn_last;
   	private Toolbarbutton btn_next;
   	private Toolbarbutton btn_prev;
   	private Toolbarbutton btn_first;
   	private Toolbarbutton btn_add;
   	private Toolbarbutton btn_search,btn_approve_closed,btn_block,btn_unlock,btn_delete;
   	private Toolbarbutton btn_back,btn_save,btn_cancel,btn_approve,btn_close,btn_find,btn_cancelf;
   	private Toolbar tb;
	private Textbox id, acc_bal, client, id_order, name, sgn, bal;
	private Longbox sign_registr, s_in, s_out, dt, ct, s_in_tmp, s_out_tmp, dt_tmp, ct_tmp;
	private Datebox l_date, date_open, date_close;
	private Longbox acc_group_id, kod_err;
	private Textbox file_name;
	private Textbox aid, aacc_bal,  aclient, aid_order, aname, asgn, abal;
	private Longbox asign_registr, as_in, as_out, adt, act, as_in_tmp, as_out_tmp, adt_tmp, act_tmp;
	private Datebox al_date, adate_open, adate_close;
	private Longbox aacc_group_id, akod_err;
	private Textbox afile_name ;
	private Textbox fid, facc_bal, fcurrency, fclient, fid_order, fname, fsgn, fbal;
	private Longbox fsign_registr, fs_in, fs_out, fdt, fct, fs_in_tmp, fs_out_tmp, fdt_tmp, fct_tmp;
	private Datebox fl_date, fdate_open, fdate_close;
	private Longbox facc_group_id, fkod_err;
	private Textbox ffile_name ;
	private Paging accountPaging;
	private Label ibankname, iusername,	irole;
	private  int _pageSize = 10;
	private int _startPageNumber = 0;
	private int _totalSize = 0;
	private int _oldSelectedIndex = 0;
	private boolean _needsTotalSizeUpdate = true;
	private DecimalFormat nf = new DecimalFormat("0.00##");
	
	
	private RefCBox pagesize;
	private RefCBox state, astate, fstate;
	private RefCBox branch, abranch, fbranch, aibranch,acurrency,currency,aacc_bal_text;
	
	private List<RefData> pagesizes = new ArrayList<RefData>();
	private List<RefData> astates = new ArrayList<RefData>();
	private List<RefData> s_mfoall = new ArrayList<RefData>();
	private List<RefData> s_mfocur = new ArrayList<RefData>();
	private List<RefData> operdates = new ArrayList<RefData>();
	private List<RefData> currencies = new ArrayList<RefData>();
	private List<RefData> s_accounts = new ArrayList<RefData>();
	
	private String alias, un, pw, lang, branch1,uid2,uid4;
	public Account current = new Account();
	public Account currentadd = new Account();
	public AccountFilter filter = new AccountFilter();
	private AccountService accountService;
	private List<SAccount> saccList;
	private AccountDictionaries accountDictionaries;
	private String selectedAccount;
	private int uid,uid3;
	private User curuser = null;
	PagingListModel model = null;
	ListModelList lmodel =null;
	private AnnotateDataBinder binder;
	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");

	public AccountViewCtrl() {
		super('$', false, false);
	}
    
    @Override
    public void doAfterCompose(Component comp) throws Exception {
    	super.doAfterCompose(comp);
    	// TODO Auto-generated method stub
    	binder = new AnnotateDataBinder(comp);
    	binder.bindBean("current", this.current);
    	binder.loadAll();
        String[] parameter = (String[]) param.get("ht");
        if (parameter!=null){
        	_pageSize = (Integer.parseInt( parameter[0])-210)/40;
        	dataGrid.setRows((Integer.parseInt( parameter[0])-210)/40);
        }
        
        un=(String)session.getAttribute("un");
        pw=(String)session.getAttribute("pwd");
        alias=(String)session.getAttribute("alias");
        lang = ((Locale) session.getAttribute(Attributes.PREFERRED_LOCALE)).getLanguage();
        branch1=(String)session.getAttribute("branch");
        uid2=String.valueOf(session.getAttribute("uid"));
        uid3=Integer.valueOf(branch1);
		 uid4=String.valueOf(uid3);
		 ISLogger.getLogger().warn("uid3: "+uid3);
		 if(uid2.startsWith(uid4)){
			 int n=uid4.length();
			 uid=(uid2.length()>uid4.length())?Integer.valueOf(uid2.substring(n)):Integer.valueOf(uid2);
			 ISLogger.getLogger().warn("Account_emp_id: "+uid);
			 
		 }
		 else {
			 uid=Integer.valueOf(uid2);
		 }
		 ISLogger.getLogger().warn("branch: "+branch);
        System.out.println("uid2: "+uid2);
        init();
        initServices();
        
        
        parameter = (String[]) param.get("search_clients");
		if (parameter != null) {
			System.out.println("Client "+parameter[0]);
			filter.setClient(parameter[0]);
			filter.setBranch(branch1);
		}
        
        dataGrid.setItemRenderer(new ListitemRenderer(){
        @SuppressWarnings("unchecked")
        public void render(Listitem row, Object data) throws Exception {
        	Account pAccount = (Account) data;
        	row.setValue(pAccount);
        	row.appendChild(new Listcell(pAccount.getBranch()));
        	row.appendChild(new Listcell(pAccount.getId()));
        	//row.appendChild(new Listcell(pAccount.getAcc_bal()));
        	row.appendChild(new Listcell(pAccount.getCurrency()));
        	//row.appendChild(new Listcell(pAccount.getClient()));
        	//row.appendChild(new Listcell(pAccount.getId_order()));
        	row.appendChild(new Listcell(pAccount.getName()));
        	//row.appendChild(new Listcell(pAccount.getSgn()));
        	//row.appendChild(new Listcell(pAccount.getBal()));
        	//row.appendChild(new Listcell(pAccount.getSign_registr()+""));
        	//row.appendChild(new Listcell(pAccount.getS_in()+""));
        	row.appendChild(new Listcell((nf.format(Double.parseDouble(pAccount.getS_out()+"")/100)+"")));
        	//row.appendChild(new Listcell(pAccount.getDt()+""));
        	//row.appendChild(new Listcell(pAccount.getCt()+""));
        	//row.appendChild(new Listcell(pAccount.getS_in_tmp()+""));
        	//row.appendChild(new Listcell(pAccount.getS_out_tmp()+""));
        	//row.appendChild(new Listcell(pAccount.getDt_tmp()+""));
        	//row.appendChild(new Listcell(pAccount.getCt_tmp()+""));
        	//row.appendChild(new Listcell(df.format(pAccount.getL_date())));
        	//row.appendChild(new Listcell(df.format(pAccount.getDate_open())));
        	//row.appendChild(new Listcell(df.format(pAccount.getDate_close())));
        	//row.appendChild(new Listcell(pAccount.getAcc_group_id()+""));
        	row.appendChild(new Listcell(pAccount.getState_desc()));
        	//row.appendChild(new Listcell(pAccount.getKod_err()+""));
        	//row.appendChild(new Listcell(pAccount.getFile_name()));
        	
        	Listcell lc = new Listcell();
        	Toolbarbutton tb = new Toolbarbutton();
        	tb.setId("tb_"+row.getIndex());
        	tb.setImage("/images/printer.png");
        	tb.setTooltiptext(Labels.getLabel("account.reportyesterday"));
        	tb.setAttribute("rowIndex", row.getIndex());
        	tb.addEventListener(Events.ON_CLICK, new EventListener() {
        		public void onEvent(Event event) throws Exception {
        			Toolbarbutton tb = (Toolbarbutton) event.getTarget();
        			dataGrid.setSelectedIndex((Integer)tb.getAttribute("rowIndex"));
        			current = (Account) dataGrid.getItemAtIndex((Integer)tb.getAttribute("rowIndex")).getValue();
        			//ch.setLabel((current.getCard_pay() == 1 ? "Разрешено":"Запрещено"));
        			if (operdates.size() > 0) {
        				getReport(0, 6, 1, current, operdates.get(0).getLabel(), null, Labels.getLabel("account.reportyesterday"));
        			}
        			_oldSelectedIndex = dataGrid.getSelectedIndex();
        			//refreshModelAcc(_startPageNumber);
        			
        		}
        	});
        	lc.appendChild(tb);
        	row.appendChild(lc);
        	lc = new Listcell();
        	tb = new Toolbarbutton();
        	tb.setId("tb1_"+row.getIndex());
        	tb.setImage("/images/printer.png");
        	tb.setTooltiptext(Labels.getLabel("account.reporttoday"));
        	tb.setAttribute("rowIndex", row.getIndex());
        	tb.addEventListener(Events.ON_CLICK, new EventListener() {
        		public void onEvent(Event event) throws Exception {
        			Toolbarbutton tb = (Toolbarbutton) event.getTarget();
        			dataGrid.setSelectedIndex((Integer)tb.getAttribute("rowIndex"));
        			current = (Account) dataGrid.getItemAtIndex((Integer)tb.getAttribute("rowIndex")).getValue();
        			//ch.setLabel((current.getCard_pay() == 1 ? "Разрешено":"Запрещено"));
        			if (operdates.size() > 1) {
        				getReport(0, 6, 1, current, operdates.get(1).getLabel(), null, Labels.getLabel("account.reporttoday"));
        			}
        			_oldSelectedIndex = dataGrid.getSelectedIndex();
        			//refreshModelAcc(_startPageNumber);
        		}
        	});
        	lc.appendChild(tb);
        	row.appendChild(lc);
        	lc = new Listcell();
        	tb = new Toolbarbutton();
        	tb.setId("tb2_"+row.getIndex());
        	tb.setImage("/images/printer.png");
        	tb.setTooltiptext(Labels.getLabel("account.reportperiod"));
        	tb.setAttribute("rowIndex", row.getIndex());
        	tb.addEventListener(Events.ON_CLICK, new EventListener() {
        		public void onEvent(Event event) throws Exception {
        			SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        			Toolbarbutton tb = (Toolbarbutton) event.getTarget();
        			dataGrid.setSelectedIndex((Integer)tb.getAttribute("rowIndex"));
        			current = (Account) dataGrid.getItemAtIndex((Integer)tb.getAttribute("rowIndex")).getValue();
        			//ch.setLabel((current.getCard_pay() == 1 ? "Разрешено":"Запрещено"));
        			if (operdates.size() > 1) {
        				java.util.Date dateto = df.parse(operdates.get(1).getLabel());
        				java.util.Date datefrom = AccountService.addMonths(df.parse(operdates.get(1).getLabel()), -1);
        				getReport(0, 8, 1, current, df.format(datefrom), df.format(dateto), Labels.getLabel("account.reportperiod"));
        			}
        			_oldSelectedIndex = dataGrid.getSelectedIndex();
        			//refreshModelAcc(_startPageNumber);
        		}
        	});
        	lc.appendChild(tb);
        	row.appendChild(lc);
        }});	
        refreshModel(_startPageNumber);
    }
    
    private void init() {
    	astates = AccountService.getAstates(alias);
    	s_mfoall = AccountService.getS_MfoAll(alias);
    	s_mfocur = AccountService.getS_Mfo(alias);
    	operdates = AccountService.getOperdates(alias);
    	currencies = AccountService.getS_currencies(alias);
    	s_accounts = AccountService.getS_accounts(alias);
    	branch.setModel(new ListModelList(s_mfoall)); 
    	abranch.setModel(new ListModelList(s_mfoall)); 
    	fbranch.setModel(new ListModelList(s_mfoall));
    	aibranch.setModel(new ListModelList(s_mfoall));
    	state.setModel(new ListModelList(astates));
    	acurrency.setModel(new ListModelList(currencies));
    	aacc_bal_text.setModel(new ListModelList(s_accounts));
    	
    	currency.setModel(new ListModelList(currencies));
    	
    	//astate.setModel(new ListModelList(gstates));
    	fstate.setModel(new ListModelList(astates));
    }
    	
	public void onPaging$accountPaging(ForwardEvent event){
	    final PagingEvent pe = (PagingEvent) event.getOrigin();
	    _startPageNumber = pe.getActivePage();
	    refreshModel(_startPageNumber);
	}
	
	private void initServices() {
	//	accountDao = AccountDao.getInstance(alias);
		//saccList = accountDictionaries.getSaccList();
		accountService = AccountService.getInstance(branch1, alias, un, pw);
	//	accountDictionaries = AccountDictionaries.instance(alias);
	//	setModels();
	}
	private void refreshModel(int activePage){
		accountPaging.setPageSize(_pageSize);
		//System.out.println(userclient.getClient().getClient_id());
		//filter.setAccounts(ClientService.getAccountsForView(userclient.getClient().getId()));
		model = new PagingListModel(activePage, _pageSize, filter, alias);
		if(_needsTotalSizeUpdate) {
			_totalSize = model.getTotalSize(filter, alias);
			//_needsTotalSizeUpdate = false;
		}
		accountPaging.setTotalSize(_totalSize);
		dataGrid.setModel((ListModel) model);
		if (model.getSize()>0){
			dataGrid.setSelectedIndex(_oldSelectedIndex);
			sendSelEvt();
			this.current =(Account) model.getElementAt(_oldSelectedIndex);
			sendSelEvt();
		}
	}

	public void onDoubleClick$dataGrid$grd() {
		Account act = (Account) dataGrid.getSelectedItem().getValue();
		int state_act = Integer.valueOf(act.getState()+"");
		
		branch.setDisabled(true);		
		currency.setDisabled(true);		
		state.setDisabled(true);
		date_open.setDisabled(true);
		date_close.setDisabled(true);
		l_date.setDisabled(true);		
		
		if(state_act==1){
			btn_save.setVisible(false);
	        btn_cancel.setVisible(false);
	        btn_approve.setVisible(true);
	        btn_close.setVisible(false);
	        btn_delete.setVisible(true);
	        System.out.println("state1: "+state_act);
		}
		else if (state_act==2){
			btn_save.setVisible(false);
	        btn_cancel.setVisible(false);	
	        btn_approve.setVisible(false);
	        btn_close.setVisible(true);
	        btn_block.setVisible(true);
	        System.out.println("state2: "+state_act);
		}
		else if(state_act == 3){
			btn_close.setVisible(false);
			btn_approve_closed.setVisible(true);
			btn_save.setVisible(false);
	        btn_cancel.setVisible(false);
			System.out.println("state3: "+state_act);
		}
		else if(state_act == 4){
			btn_unlock.setVisible(true);	
		}
		grd.setVisible(false);
        frm.setVisible(true);
        frmgrd.setVisible(true);
        addgrd.setVisible(false);
        fgrd.setVisible(false);
        btn_back.setImage("/images/folder.png");
        btn_back.setLabel(Labels.getLabel("grid"));
        accountPaging.setVisible(false);
       
	}

	public void onClick$btn_back() {
        if (frm.isVisible()){
            frm.setVisible(false);
            grd.setVisible(true);
            btn_back.setImage("/images/file.png");
            btn_back.setLabel(Labels.getLabel("back"));
            accountPaging.setVisible(true);
            pagesize.setVisible(true);
            btn_approve.setVisible(false);
            btn_cancel.setVisible(true);	
            btn_save.setVisible(true);
            btn_close.setVisible(false);
            btn_approve_closed.setVisible(false);
            btn_block.setVisible(false);
            btn_unlock.setVisible(false);
            btn_delete.setVisible(false);
            btn_find.setVisible(false);
      	  btn_cancelf.setVisible(false);
            System.out.println("btn_back_worked");
        }else onDoubleClick$dataGrid$grd();
       
	}

	public void onClick$btn_first() {
        dataGrid.setSelectedIndex(0);
        sendSelEvt();
	}
	
	public void onClick$btn_last() {
        dataGrid.setSelectedIndex(model.getSize()-1);
        sendSelEvt();
	}
	
	public void onClick$btn_prev() {
        if (dataGrid.getSelectedIndex()!=0){
	        dataGrid.setSelectedIndex(dataGrid.getSelectedIndex()-1);
	        sendSelEvt();
        }
	}
	
	public void onClick$btn_next() {
        if (dataGrid.getSelectedIndex()!=(model.getSize()-1)){
	        dataGrid.setSelectedIndex(dataGrid.getSelectedIndex()+1);
	        sendSelEvt();
        }
	}
   
	private void sendSelEvt(){
        if (dataGrid.getSelectedIndex()==0){
                btn_first.setDisabled(true);
                btn_prev.setDisabled(true);
        }else{
                btn_first.setDisabled(false);
                btn_prev.setDisabled(false);
        }
        if(dataGrid.getSelectedIndex()==(model.getSize()-1)){
                btn_next.setDisabled(true);
                btn_last.setDisabled(true);
        }else{
                btn_next.setDisabled(false);
                btn_last.setDisabled(false);
        }
        SelectEvent evt = new SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
        Events.sendEvent(evt);
	}

	public void onClick$btn_add() {			
		ZkUtils.clearForm(addgrd);
		currentadd = new Account();
		
		btn_cancel.setVisible(true);	
		btn_save.setVisible(true);
		
		currentadd.setCt(Long.valueOf("0"));
		currentadd.setS_in(Long.valueOf("0"));
		System.out.println("as_in :"+Long.valueOf("0"));		
		currentadd.setDt(Long.valueOf("0"));
        currentadd.setDt_tmp(Long.valueOf("0"));
        currentadd.setS_in_tmp(Long.valueOf("0"));
        currentadd.setS_out(Long.valueOf("0"));
        currentadd.setS_out_tmp(Long.valueOf("0"));
        currentadd.setCt_tmp(Long.valueOf("0"));
       
        grd.setVisible(false);
        frm.setVisible(true);
        frmgrd.setVisible(true);
        addgrd.setVisible(false);
        fgrd.setVisible(false);
        btn_back.setImage("/images/folder.png");
        btn_back.setLabel(Labels.getLabel("grid"));
        accountPaging.setVisible(false);
        
        frmgrd.setVisible(false);
        addgrd.setVisible(true);
        fgrd.setVisible(false);
        abal.setReadonly(true);
        asgn.setReadonly(true);
        asign_registr.setReadonly(true);
        aclient.setReadonly(true);
        aacc_bal.setReadonly(true);
        btn_approve.setVisible(false);
        btn_delete.setVisible(false);
        btn_close.setVisible(false);
        btn_block.setVisible(false);
        btn_unlock.setVisible(false);
        btn_find.setVisible(false);
    	  btn_cancelf.setVisible(false);
      //  aacc_group_id.setReadonly(true);
        
	}

	public void onClick$btn_search() {
        onDoubleClick$dataGrid$grd();
        frmgrd.setVisible(false);
        addgrd.setVisible(false);
        fgrd.setVisible(true);
        ZkUtils.clearForm(fgrd);
        btn_close.setVisible(false);
        btn_block.setVisible(false);
        btn_approve.setVisible(false);
        btn_delete.setVisible(false);
        btn_unlock.setVisible(false);
        btn_find.setVisible(true);
  	  btn_cancelf.setVisible(true);
  	  prepareFilter();
        System.out.println("search");
	}
	
	private void accBalSets(){
		if (addgrd.isVisible()){
		currentadd.setSgn(AccountUtil.getSgn(currentadd.getAcc_bal(), saccList));	
		currentadd.setBal(AccountUtil.getBal(currentadd.getAcc_bal()));
		currentadd.setSign_registr(Long.valueOf((AccountUtil.getSignRegistr(currentadd.getAcc_bal(), saccList)+"")));
				
		}
				
	}
	
  public void fillForm(){
	  if(aacc_bal.getValue()!=null){
			String mark_sqn = "S";
			String mark_bal = "T";
			String mark_group = "G";
			String needSgn = AccountService.getMark(alias, aacc_bal.getValue(),mark_sqn);
			String needBal = AccountService.getMark(alias, aacc_bal.getValue(),mark_bal);
			String needGroup = AccountService.getMark(alias, aacc_bal.getValue(),mark_group);
			String clientName = AccountService.getNameClient(branch1, current.getClient(), alias);
			abal.setValue(needSgn);
			currentadd.setSgn(needSgn);
			asgn.setValue(needBal);
			currentadd.setBal(needBal);
			asign_registr.setValue(Long.valueOf(needGroup));
			currentadd.setSign_registr(Long.valueOf(needGroup));
		//	aacc_group_id.setValue(Long.valueOf(uid+""));
		//	currentadd.setAcc_group_id(Long.valueOf(uid+""));

			if(current !=null){
				System.out.println("client_account: "+current.getClient());
				aclient.setValue(current.getClient());
				currentadd.setClient(current.getClient());				
				aname.setValue(clientName);
				currentadd.setName(clientName);
				System.out.println("onchange_baltext");
				}
			}  
	  
  }
  
  public void onClick$btn_block(){
	  Account ac_block = (Account) dataGrid.getSelectedItem().getValue();
		System.out.println("btn_close: "+ac_block.getState());
		try {
		 Res res = accountService.doAction(current, 4);
			if (res.getCode() != 0) {
				alert(res.getName());
				return;
			}
			else{
				alert("Счет успешно заблокирован"+"\n"+res.getName());
			    }
			selectedAccount = res.getName();
			   CheckNull.clearForm(addgrd);
		       frmgrd.setVisible(true);
		       addgrd.setVisible(false);
		       fgrd.setVisible(false);
		       onClick$btn_back();
			    refreshModel(_startPageNumber);
			    SelectEvent evt = new SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
			    Events.sendEvent(evt);
		   } catch (Exception e) {
		       e.printStackTrace();
		       ISLogger.getLogger().error(CheckNull.getPstr(e));
		   }    
	  
	  
  }
  
  public void onClick$btn_unlock(){
	  Account ac_unlock = (Account) dataGrid.getSelectedItem().getValue();
		System.out.println("btn_close: "+ac_unlock.getState());
		try {
		 Res res = accountService.doAction(current, 5);
			if (res.getCode() != 0) {
				alert(res.getName());
				return;
			}
			else{
				alert("Счет разблокирован успешно"+"\n"+res.getName());
			    }
			selectedAccount = res.getName();
			   CheckNull.clearForm(addgrd);
		       frmgrd.setVisible(true);
		       addgrd.setVisible(false);
		       fgrd.setVisible(false);
		       onClick$btn_back();
			    refreshModel(_startPageNumber);
			    SelectEvent evt = new SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
			    Events.sendEvent(evt);
		   } catch (Exception e) {
		       e.printStackTrace();
		       ISLogger.getLogger().error(CheckNull.getPstr(e));
		   }      
	  
	  
  }
  public void onClick$btn_delete(){
	  Account ac_delete = (Account) dataGrid.getSelectedItem().getValue();
		System.out.println("btn_close: "+ac_delete.getState());
		try {
		 Res res = accountService.doAction(current, 6);
			if (res.getCode() != 0) {
				alert(res.getName());
				return;
			}
			else{
				alert("Счет удален успешно"+"\n"+res.getName());
			    }
			selectedAccount = res.getName();
			   CheckNull.clearForm(addgrd);
		       frmgrd.setVisible(true);
		       addgrd.setVisible(false);
		       fgrd.setVisible(false);
		       onClick$btn_back();
			    refreshModel(_startPageNumber);
			    SelectEvent evt = new SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
			    Events.sendEvent(evt);
		   } catch (Exception e) {
		       e.printStackTrace();
		       ISLogger.getLogger().error(CheckNull.getPstr(e));
		   }      
	    
	  
  }
  private void prepareFilter(){
	  
	  filter.setBranch(branch1);
	  filter.setClient(current.getClient());
	  
  }
	
  public void onChange$aacc_bal_text(){
	  System.out.println("Zarabotal");
	aacc_bal.setValue(aacc_bal_text.getValue());
	currentadd.setAcc_bal(aacc_bal_text.getValue());
	fillForm();
  }
  public void onClick$btn_approve(){
	Account ac = (Account) dataGrid.getSelectedItem().getValue();
	System.out.println("btn_approve: "+ac.getState());
	try{
	
	if (CheckNull.isEmpty(acc_bal.getValue()) || CheckNull.isEmpty(client.getValue())
			|| CheckNull.isEmpty(currency.getValue()) || CheckNull.isEmpty(id_order.getValue())
			|| CheckNull.isEmpty(name.getValue())) {
		alert("Заполните все поля");
		return;
	}
   Res res = accountService.doAction(current, 2);
	if (res.getCode() != 0) {
		alert(res.getName());
		return;
	}
	else{
			alert("Счет успешно утвержден"+"\n"+res.getName());
		    }
	selectedAccount = res.getName();
	   CheckNull.clearForm(addgrd);
       frmgrd.setVisible(true);
       addgrd.setVisible(false);
       fgrd.setVisible(false);
       onClick$btn_back();
	    refreshModel(_startPageNumber);
	    SelectEvent evt = new SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
	    Events.sendEvent(evt);
   } catch (Exception e) {
       e.printStackTrace();
       ISLogger.getLogger().error(CheckNull.getPstr(e));
   }  
	  
  }
  public void onClick$btn_close(){
	Account acnt = (Account) dataGrid.getSelectedItem().getValue();
	System.out.println("btn_close: "+acnt.getState());
	try {
	 Res res = accountService.doAction(current, 3);
		if (res.getCode() != 0) {
			alert(res.getName());
			return;
		}
		else{
			alert("Счет успешно закрыт"+"\n"+res.getName());
		    }
		selectedAccount = res.getName();
		   CheckNull.clearForm(addgrd);
	       frmgrd.setVisible(true);
	       addgrd.setVisible(false);
	       fgrd.setVisible(false);
	       onClick$btn_back();
		    refreshModel(_startPageNumber);
		    SelectEvent evt = new SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
		    Events.sendEvent(evt);
	   } catch (Exception e) {
	       e.printStackTrace();
	       ISLogger.getLogger().error(CheckNull.getPstr(e));
	   }  
  }
  
  public void onClick$btn_approve_closed(){
	  Account act = (Account) dataGrid.getSelectedItem().getValue();
		System.out.println("btn_approve_closed: "+act.getState());
		try {
		 Res res = accountService.doAction(current, 20);
			if (res.getCode() != 0) {
				alert(res.getName());
				return;
			}
			else{
				alert("Счет успешно утвержден"+"\n"+res.getName());
			    }
			selectedAccount = res.getName();
			   CheckNull.clearForm(addgrd);
		       frmgrd.setVisible(true);
		       addgrd.setVisible(false);
		       fgrd.setVisible(false);
		       onClick$btn_back();
			    refreshModel(_startPageNumber);
			    SelectEvent evt = new SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
			    Events.sendEvent(evt);
		   } catch (Exception e) {
		       e.printStackTrace();
		       ISLogger.getLogger().error(CheckNull.getPstr(e));
		   }  
		       
  }
  public void onClick$btn_find(){
	  
	  try {
	  if(fgrd.isVisible()){
          filter = new AccountFilter();
           prepareFilter();
			if (CheckNull.isEmpty(branch.getValue())) {
				filter.setBranch(fbranch.getValue());
			}
			if (CheckNull.isEmpty(id.getValue())) {
				filter.setId(fid.getValue());
			}
			if (CheckNull.isEmpty(acc_bal.getValue())) {
				filter.setAcc_bal(facc_bal.getValue());
			}
			if (CheckNull.isEmpty(currency.getValue())) {
				filter.setCurrency(fcurrency.getValue());
			}
			if (CheckNull.isEmpty(client.getValue())) {
				filter.setClient(fclient.getValue());
			}
			if (CheckNull.isEmpty(id_order.getValue())) {
				filter.setId_order(fid_order.getValue());
			}
			if (CheckNull.isEmpty(name.getValue())) {
				filter.setName(fname.getValue());
			}
			if (CheckNull.isEmpty(sgn.getValue())) {
				filter.setSgn(fsgn.getValue());
			}
			if (CheckNull.isEmpty(bal.getValue())) {
				filter.setBal(fbal.getValue());
			}
			if (CheckNull.isEmpty(sign_registr.getValue())) {
				filter.setSign_registr(fsign_registr.getValue());
			}
			
			if (CheckNull.isEmpty(date_open.getValue())) {
				filter.setDate_open(fdate_open.getValue());
			}
			if (CheckNull.isEmpty(date_close.getValue())) {
				filter.setDate_close(fdate_close.getValue());
			}
			if (CheckNull.isEmpty(acc_group_id.getValue())) {
				filter.setAcc_group_id(facc_group_id.getValue());
			}
			if (CheckNull.isEmpty(state.getValue())) {
				filter.setState(Long.parseLong(fstate.getValue()));
			}
			if (CheckNull.isEmpty(kod_err.getValue())) {
				filter.setKod_err(fkod_err.getValue());
			}
			 if (CheckNull.isEmpty(file_name.getValue())) {
				filter.setFile_name(ffile_name.getValue());
			}
	     }             
	       //     CheckNull.clearForm(fgrd);	  
			    
			//    _needsTotalSizeUpdate = true;
	  System.out.println("filter_branch:" +filter.getBranch()+"filter_client: "+filter.getClient());
			    refreshModel(0);
			    SelectEvent evt = new SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
			    Events.sendEvent(evt);
			    onClick$btn_back();
		    } catch (Exception e) {
		        e.printStackTrace();
		        ISLogger.getLogger().error(CheckNull.getPstr(e));
		    }	  
  }
  
  public void onClick$btn_cancelf(){
	  if (frm.isVisible()){
          frm.setVisible(false);
          grd.setVisible(true);
          accountPaging.setVisible(true);
          pagesize.setVisible(true);
          btn_approve.setVisible(false);
          btn_cancel.setVisible(true);	
          btn_save.setVisible(true);
          btn_close.setVisible(false);
          btn_approve_closed.setVisible(false);
          btn_block.setVisible(false);
          btn_unlock.setVisible(false);
          btn_delete.setVisible(false);
          btn_find.setVisible(false);
    	  btn_cancelf.setVisible(false);
	  }
	  
  }
  
  public void onChange$abranch(){
	  abranch.setValue(abranch.getText());
	  currentadd.setBranch(branch1);
  }
  public void onChange$acurrency(){
	  acurrency.setValue(acurrency.getText());
	  currentadd.setCurrency(acurrency.getValue());
  }
  	public void onClick$btn_save() {		
	    try{
	        if(addgrd.isVisible()){	
	        	System.out.println("acc_bal :"+aacc_bal.getValue());
	        	System.out.println("aclient :"+aclient.getValue());
	        	System.out.println("acc_cur :"+acurrency.getValue());
	        	System.out.println("acc_idOrder :"+aid_order.getValue());
	        	System.out.println("acc_name :"+aname.getValue());
	        	System.out.println("branch : "+abranch.getValue());
	        	currentadd.setCurrency(acurrency.getValue());
	        	
	           if (CheckNull.isEmpty(aacc_bal.getValue()) || CheckNull.isEmpty(aclient.getValue())
	    				|| CheckNull.isEmpty(acurrency.getValue()) || CheckNull.isEmpty(aid_order.getValue())
	    				|| CheckNull.isEmpty(aname.getValue())) {
	    			alert("Заполните все поля");
	    			return;
	    		}
	           Res res = accountService.doAction(currentadd, 1);
	   		if (res.getCode() != 0) {
	   			alert(res.getName());
	   			return;
	   		}
	   		else{
	   			alert("Счет успешно открыт"+"\n"+res.getName());
	   		    }
	   		selectedAccount = res.getName();
	   		 CheckNull.clearForm(addgrd);
	            frmgrd.setVisible(true);
	            addgrd.setVisible(false);
	            fgrd.setVisible(false);	        	
	        	/*AccountService.create(new Account(
		               currentadd.getBranch(),currentadd.getId(),currentadd.getAcc_bal(),currentadd.getCurrency(),currentadd.getClient(),
		               currentadd.getId_order(),currentadd.getName(),currentadd.getSgn(),currentadd.getBal(),currentadd.getSign_registr(),
		               currentadd.getS_in(),currentadd.getS_out(),currentadd.getDt(),currentadd.getCt(),currentadd.getS_in_tmp(),
		               currentadd.getS_out_tmp(),currentadd.getDt_tmp(),currentadd.getCt_tmp(),currentadd.getL_date(),
		               currentadd.getDate_open(),currentadd.getDate_close(),currentadd.getAcc_group_id(),currentadd.getState(),
		               currentadd.getKod_err(),currentadd.getFile_name()));
	            CheckNull.clearForm(addgrd);
	            frmgrd.setVisible(true);
	            addgrd.setVisible(false);
	            fgrd.setVisible(false);*/
	        }else if(fgrd.isVisible()){
	            filter = new AccountFilter();
				if (CheckNull.isEmpty(branch.getValue())) {
					filter.setBranch(fbranch.getValue());
				}
				if (CheckNull.isEmpty(id.getValue())) {
					filter.setId(fid.getValue());
				}
				if (CheckNull.isEmpty(acc_bal.getValue())) {
					filter.setAcc_bal(facc_bal.getValue());
				}
				if (CheckNull.isEmpty(currency.getValue())) {
					filter.setCurrency(fcurrency.getValue());
				}
				if (CheckNull.isEmpty(client.getValue())) {
					filter.setClient(fclient.getValue());
				}
				if (CheckNull.isEmpty(id_order.getValue())) {
					filter.setId_order(fid_order.getValue());
				}
				if (CheckNull.isEmpty(name.getValue())) {
					filter.setName(fname.getValue());
				}
				if (CheckNull.isEmpty(sgn.getValue())) {
					filter.setSgn(fsgn.getValue());
				}
				if (CheckNull.isEmpty(bal.getValue())) {
					filter.setBal(fbal.getValue());
				}
				if (CheckNull.isEmpty(sign_registr.getValue())) {
					filter.setSign_registr(fsign_registr.getValue());
				}
				if (CheckNull.isEmpty(s_in.getValue())) {
					filter.setS_in(fs_in.getValue());
				}
				if (CheckNull.isEmpty(s_out.getValue())) {
					filter.setS_out(fs_out.getValue());
				}
				if (CheckNull.isEmpty(dt.getValue())) {
					filter.setDt(fdt.getValue());
				}
				if (CheckNull.isEmpty(ct.getValue())) {
					filter.setCt(fct.getValue());
				}
				if (CheckNull.isEmpty(s_in_tmp.getValue())) {
					filter.setS_in_tmp(fs_in_tmp.getValue());
				}
				if (CheckNull.isEmpty(s_out_tmp.getValue())) {
					filter.setS_out_tmp(fs_out_tmp.getValue());
				}
				if (CheckNull.isEmpty(dt_tmp.getValue())) {
					filter.setDt_tmp(fdt_tmp.getValue());
				}
				if (CheckNull.isEmpty(ct_tmp.getValue())) {
					filter.setCt_tmp(fct_tmp.getValue());
				}
				if (CheckNull.isEmpty(l_date.getValue())) {
					filter.setL_date(fl_date.getValue());
				}
				if (CheckNull.isEmpty(date_open.getValue())) {
					filter.setDate_open(fdate_open.getValue());
				}
				if (CheckNull.isEmpty(date_close.getValue())) {
					filter.setDate_close(fdate_close.getValue());
				}
				if (CheckNull.isEmpty(acc_group_id.getValue())) {
					filter.setAcc_group_id(facc_group_id.getValue());
				}
				if (CheckNull.isEmpty(state.getValue())) {
					filter.setState(Long.parseLong(fstate.getValue()));
				}
				if (CheckNull.isEmpty(kod_err.getValue())) {
					filter.setKod_err(fkod_err.getValue());
				}
				if (CheckNull.isEmpty(file_name.getValue())) {
					filter.setFile_name(ffile_name.getValue());
				}

	        }else{
		        	  current.setBranch(branch.getValue());
		        	  current.setId(id.getValue());
		        	  current.setAcc_bal(acc_bal.getValue());
		        	  current.setCurrency(currency.getValue());
		        	  current.setClient(client.getValue());
		        	  current.setId_order(id_order.getValue());
		        	  current.setName(name.getValue());
		        	  current.setSgn(sgn.getValue());
		        	  current.setBal(bal.getValue());
		        	  current.setSign_registr(sign_registr.getValue());
		        	  current.setS_in(s_in.getValue());
		        	  current.setS_out(s_out.getValue()/100);
		        	  current.setDt(dt.getValue());
		        	  current.setCt(ct.getValue());
		        	  current.setS_in_tmp(s_in_tmp.getValue());
		        	  current.setS_out_tmp(s_out_tmp.getValue());
		        	  current.setDt_tmp(dt_tmp.getValue());
		        	  current.setCt_tmp(ct_tmp.getValue());
		        	  current.setL_date(l_date.getValue());
		        	  current.setDate_open(date_open.getValue());
		        	  current.setDate_close(date_close.getValue());
		        	  current.setAcc_group_id(acc_group_id.getValue());
		        	  current.setState(Long.parseLong(state.getValue()));
		        	  current.setKod_err(kod_err.getValue());
		        	  current.setFile_name(file_name.getValue());

	            AccountService.update(current);
	        }
		    onClick$btn_back();
		    refreshModel(_startPageNumber);
		    SelectEvent evt = new SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
		    Events.sendEvent(evt);
	    } catch (Exception e) {
	        e.printStackTrace();
	        ISLogger.getLogger().error(CheckNull.getPstr(e));
	    }
	}
	
	public void onClick$btn_cancel() {
        if(fgrd.isVisible()){
        	filter = new AccountFilter();
        }
	    onClick$btn_back();
	    frmgrd.setVisible(true);
	    addgrd.setVisible(false);
	    fgrd.setVisible(false);
	    CheckNull.clearForm(addgrd);
	    CheckNull.clearForm(fgrd);
	    refreshModel(_startPageNumber);
	}
	
	public void onSelect$pagesize() {
		_pageSize = Integer.parseInt(pagesize.getValue());
		//CookieUtil.setCookie("pagesize", ""+_pageSize);
        dataGrid.setRows(_pageSize+1);
        _oldSelectedIndex = 0;
        _startPageNumber = 0;
        accountPaging.setActivePage(_startPageNumber);
        refreshModel(_startPageNumber);
	}
	
	public void onClick$btn_refresh() {
		//_oldSelectedIndex = 0;
        //_startPageNumber = 0;
        //documentPaging.setActivePage(_startPageNumber);
        refreshModel(_startPageNumber);
	}
	/*
	public void getReport(Account acc, String date) {
		List<Parameter> params = ReportService.createParameters(0,6,1,userclient.getClient().getSchema());
		//System.out.println("params.size() "+params.size());
		for (int i = 0; i < params.size(); i++) {
			if (params.get(i).getPar_name().equalsIgnoreCase("P_Date")) {
				params.get(i).setPar_value(date);
			} else if (params.get(i).getPar_name().equalsIgnoreCase("P_Acc")) {
				params.get(i).setPar_value(acc.getId());
			} else if (params.get(i).getPar_name().equalsIgnoreCase("P_Branch")) {
				params.get(i).setPar_value(acc.getBranch());
			}
			//System.out.println(params.get(i).getPar_name());
		}
		ReportService.getStaticJasperReport(params, "pdf", userclient.getClient().getSchema());
	}
	*/
	public void getReport(int deal_id, int report_id, int is_jasper, Account account, String date_from, String date_to, String title) {
        try {
			Date dt_from = (CheckNull.isEmpty(date_from)?null:df.parse(date_from));
	        Date dt_to = (CheckNull.isEmpty(date_to)?null:df.parse(date_to));
			//onClick$btn_back();
		    if(repWndDialog == null) {
		    	repWndDialog = (Window)Executions.createComponents("bfrepwnd.zul", accmain, null);
		    }
		    repWndDialog.setVisible(true);
		    RepWndViewCtrl rvc =(RepWndViewCtrl)repWndDialog.getVariable("reportMain$composer", false);
		    rvc.prepareWindow(deal_id, report_id, is_jasper, account, dt_from, dt_to, title, lang);
        } catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
