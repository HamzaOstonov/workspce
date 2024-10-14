package com.is.tieto_capital.accApproval;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;

import com.is.tieto_globuz.tietoAccount.GlobuzAccount;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.*;
import org.zkoss.zul.event.PagingEvent;

import com.is.tieto_globuz.tietoAccount.GlobuzAccountService;
import com.is.tieto_capital.Constants;
import com.is.utils.CheckNull;

public class AccApprovalViewCtrl extends GenericForwardComposer{
    private Div frm;
    private Listbox dataGrid;
    private Paging contactPaging;
    private Div grd;
    private Grid fgrd;
    private Toolbarbutton btn_last;
    private Toolbarbutton btn_next;
    private Toolbarbutton btn_prev;
    private Toolbarbutton btn_first;
    private Toolbarbutton btn_search;
    private Toolbar tb;
    private Textbox fid,faccount,fbranch,fstate_id,ffullname,fpassport_serial,fpassport_number,fbank_id,ftieto_id, fdeal_group_id,fdealid,faction_id;
    private Datebox fbirthday;
    private Paging accapprovalPaging;
    private  int _pageSize = 15;
    private int _startPageNumber = 0;
    private int _totalSize = 0;
    private boolean _needsTotalSizeUpdate = true;

    
    public AccApprovalFilter filter = new AccApprovalFilter();

    PagingListModel model = null;
    ListModelList lmodel =null;
    private AnnotateDataBinder binder;
    SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
    
    private static String result = null;
    private static String alias;
    private String[] access;
    private static String login;
    private static String password;


    private AccApproval current = new AccApproval();

    public AccApprovalViewCtrl() {
        super('$', false, false);
    }

    
	@Override
	public void doAfterCompose(Component comp) throws Exception {
	        
		super.doAfterCompose(comp);

        binder = new AnnotateDataBinder(comp);
        binder.bindBean("current", this.current);
        binder.loadAll();
        
	    String[] parameter = (String[]) param.get("ht");
	    
        access = (String[]) this.param.get("access");
	    
	    if (parameter!=null) {
            _pageSize = Integer.parseInt( parameter[0])/36;
            dataGrid.setRows(Integer.parseInt( parameter[0])/36);
	    }	
	    
        alias = (String) session.getAttribute("alias");
        login = (String) session.getAttribute("un");
        password = (String) session.getAttribute("pwd");
        
//        Reports report = new Reports();
//        report.getRepotClient("C:\\test\\tmp.docx", "C:\\test\\1.docx");
        

        dataGrid.setItemRenderer(new ListitemRenderer() {
        	@SuppressWarnings("unchecked")
        	public void render(Listitem row, Object data) throws Exception {
	            AccApproval pAccApproval = (AccApproval) data;
	
	            row.setValue(pAccApproval);
	            
	            row.appendChild(new Listcell(pAccApproval.getId().toString()));
	            row.appendChild(new Listcell(pAccApproval.getAccount()));
	            row.appendChild(new Listcell(pAccApproval.getBranch()));
	            row.appendChild(new Listcell(pAccApproval.getState_id().toString()));
	            row.appendChild(new Listcell(pAccApproval.getFullname()));
	            row.appendChild(new Listcell(df.format(pAccApproval.getBirthday())));
	            row.appendChild(new Listcell(pAccApproval.getPassport_serial()));
	            row.appendChild(new Listcell(pAccApproval.getPassport_number()));
	            row.appendChild(new Listcell(pAccApproval.getBank_id()));
	            row.appendChild(new Listcell(pAccApproval.getTieto_id()));
	            
	            Listcell listcell = new Listcell();
	            
                if((access[0].equals("approve") && (pAccApproval.getState_id() == Constants.APPROVAL_STATE_CONFIRM)) || 
                		(access[0].equals("confirm") && (pAccApproval.getState_id() == Constants.APPROVAL_STATE_OPEN))) {
	            
		            TiAccApprovalAction[] action = AccApprovalService.getActions(pAccApproval.getState_id());
		            
					for(int i = 0; (action[i] != null) && (i < action.length); )
					{						
						Button button = new Button();
						
						button.setImage("");
						button.setLabel(action[i].getName());
						button.setAttribute("actionMethod", action[i].getAction_method());
						button.setAttribute("methodArgument", pAccApproval.getId());
						button.addEventListener(Events.ON_CLICK, dynamicButton);
		                listcell.appendChild(button);
		                
		                i++;
					}
                }
                
				row.appendChild(listcell);	
        	}
        });
	
	    refreshModel(_startPageNumber);
	
	}
	
	  private EventListener dynamicButton = new EventListener() {
			
			@Override
			public void onEvent(Event event) throws Exception {
				String actionMethod = (String) event.getTarget().getAttribute("actionMethod");
				
				String className = actionMethod.substring(0, actionMethod.lastIndexOf("."));
				String methodName = actionMethod.substring(actionMethod.lastIndexOf(".") + 1);
				
				@SuppressWarnings("rawtypes")
				Class reflectionClass = Class.forName(className);
				
				@SuppressWarnings("unchecked")
				Method method = reflectionClass.getMethod(methodName, Long.class);
				
				method.invoke(null, (Long) event.getTarget().getAttribute("methodArgument"));
				
				alert(result);
				refreshModel(_startPageNumber);
			}
	    	
	    };
	    
	    
	    
	    public static void openAcc(Long id) {
	    	
	    }
	    
	    public static void confirmAcc(Long id) {

	    	result = AccApprovalService.accAction(id, alias, Constants.APPROVAL_TYPE_APPROVE, Constants.APPROVAL_STATE_CONFIRM);    	
	    }
	    
	    public static void approveAcc(Long id) {
	    	
	    	AccApproval accApproval = new AccApproval();
	    	GlobuzAccount globuzAccount = new GlobuzAccount();
	    	int deal_group_id, deal_id, actionid, doActionResult;
	    	
	    	
	    	accApproval = AccApprovalService.getAccApproval(id);
	    		    	
	    	globuzAccount.setBranch(accApproval.getBranch());
	    	globuzAccount.setId(accApproval.getAccount());
	    	
	    	deal_group_id = Integer.valueOf(accApproval.getDeal_group_id());
	    	deal_id = Integer.valueOf(accApproval.getDeal_id());
	    	actionid = Integer.valueOf(accApproval.getAction_id());	    	
			
	    	doActionResult = GlobuzAccountService.doAction_acc(login, password, deal_group_id, deal_id, globuzAccount, actionid, alias, true).getCode();
	    	
			if(doActionResult == 0) {
				result = AccApprovalService.accAction(id, alias, Constants.APPROVAL_INDETERMINATELY, Constants.APPROVAL_STATE_APPROVE);
			}
			else {
				result = "—чет не утвержден.\nAccApprovalViewCtrl doAction_acc error!";
			}
	    }
	    
	    public static void closeAcc(Long id) {
	    	
	    	result = AccApprovalService.accAction(id, alias, Constants.APPROVAL_INDETERMINATELY, Constants.APPROVAL_STATE_CLOSE);
	    }
	    

	public void onPaging$accapprovalPaging(ForwardEvent event) {
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModel(_startPageNumber);
	}


	private void refreshModel(int activePage) {		
	    accapprovalPaging.setPageSize(_pageSize);
	    
		filter.setAccess(access[0]);
	    
		model = new PagingListModel(activePage, _pageSize, filter, "");
		
		if(_needsTotalSizeUpdate) {
	        _totalSize = model.getTotalSize();
	        _needsTotalSizeUpdate = false;
		}
		
		accapprovalPaging.setTotalSize(_totalSize);
		
		dataGrid.setModel((ListModel) model);
		if(model.getSize() > 0) {
			this.current = (AccApproval) model.getElementAt(0);
			sendSelEvt();
		}
	}



	//Omitted...
	public AccApproval getCurrent() {
		return current;
	}
	
	public void setCurrent(AccApproval current) {
		this.current = current;
	}

	public void onDoubleClick$dataGrid$grd() {
        grd.setVisible(false);
        frm.setVisible(true);
        fgrd.setVisible(false);
	}

	public void onClick$btn_back() {
	    if (frm.isVisible()) {
	        frm.setVisible(false);
	        grd.setVisible(true);
	    }
	    else {
	    	onDoubleClick$dataGrid$grd();
	    }
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
	    if (dataGrid.getSelectedIndex() != (model.getSize() - 1)) {
		    dataGrid.setSelectedIndex(dataGrid.getSelectedIndex() + 1);
		    sendSelEvt();
	    }
	}



	private void sendSelEvt() {
	    if(dataGrid.getSelectedIndex() == 0) {
            btn_first.setDisabled(true);
            btn_prev.setDisabled(true);
	    }
	    else {
            btn_first.setDisabled(false);
            btn_prev.setDisabled(false);
	    }
	    if(dataGrid.getSelectedIndex() == (model.getSize() - 1)) {
            btn_next.setDisabled(true);
            btn_last.setDisabled(true);
	    }
	    else{
            btn_next.setDisabled(false);
            btn_last.setDisabled(false);
	    }
	    SelectEvent evt = new SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
	    Events.sendEvent(evt);
	}


	public void onClick$btn_add() {
	    onDoubleClick$dataGrid$grd();
	    fgrd.setVisible(false);
	}
	
	public void onClick$btn_search() {
	    onDoubleClick$dataGrid$grd();
	    fgrd.setVisible(true);
	}


	public void onClick$btn_save() {
		try{
		    if(fgrd.isVisible()) {
				filter = new AccApprovalFilter();
				    
				filter.setId(fid.getValue().equals("") ? null : Long.valueOf(fid.getValue()));
				filter.setAccount(faccount.getValue());
				filter.setBranch(fbranch.getValue());
				filter.setState_id(fstate_id.getValue().equals("") ? null : Long.valueOf(fstate_id.getValue()));
				filter.setFullname(ffullname.getValue());
				filter.setBirthday(fbirthday.getValue());
				filter.setPassport_serial(fpassport_serial.getValue());
				filter.setPassport_number(fpassport_number.getValue());
				filter.setBank_id(fbank_id.getValue());
				filter.setTieto_id(ftieto_id.getValue());
		    }
			onClick$btn_back();
			refreshModel(_startPageNumber);
			SelectEvent evt = new SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
			Events.sendEvent(evt);
		} 
		catch (Exception e) {
			alert(CheckNull.getPstr(e));
		    
		}
	
	}


	public void onClick$btn_cancel() {
	    if(fgrd.isVisible()) {
            filter = new AccApprovalFilter();
	    }
		onClick$btn_back();
		fgrd.setVisible(false);
		CheckNull.clearForm(fgrd);
		refreshModel(_startPageNumber);
	}

}

