package com.is.tieto_capital.cardApproval;

import java.lang.reflect.Method;
import java.sql.Date;
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
import org.zkoss.zul.*;
import org.zkoss.zul.event.PagingEvent;

import com.is.tieto_capital.Constants;
import com.is.tieto_capital.tieto.TclientService;
import com.is.tieto_globuz.agreements.AgreementService;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.Res;

public class CardApprovalViewCtrl extends GenericForwardComposer{
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
    private Textbox fid, fcard_type, fuser_id, fbranch, fnew_card_account;
    private RefCBox fapproval_type_id, fstate_id;
    private Paging cardapprovalPaging;
    private  int _pageSize = 15;
    private int _startPageNumber = 0;
    private int _totalSize = 0;
    private boolean _needsTotalSizeUpdate = true;
    private Datebox fv_date;
    private Boolean smart;
    
    public CardApprovalFilter filter = new CardApprovalFilter();

    PagingListModel model = null;
    ListModelList lmodel = null;
    private AnnotateDataBinder binder;
    SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");


    private CardApproval current = new CardApproval();
    
    private static String alias;
    private String branch;
    String[] access;

    static Res result = new Res();

    public CardApprovalViewCtrl() {
        super('$', false, false);
    }

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);

        binder = new AnnotateDataBinder(comp);
        binder.bindBean("current", this.current);
        binder.loadAll();
        String[] parameter = (String[]) param.get("ht");
        smart = param.containsKey("smart")&&((String[]) param.get("smart"))[0].equals("true");
        
        access = (String[]) this.param.get("access");
        
        if (parameter != null) {
            _pageSize = Integer.parseInt( parameter[0]) / 36;
            dataGrid.setRows(Integer.parseInt( parameter[0]) / 36);
        }
        
        alias = (String) session.getAttribute("alias");
        branch = (String) session.getAttribute("branch");
        
        fapproval_type_id.setModel((new ListModelList(CardApprovalService.getApprovalTypes(alias))));
        fstate_id.setModel((new ListModelList(CardApprovalService.getApprovalStates(alias))));

        dataGrid.setItemRenderer(
    		new ListitemRenderer() {
	        	@SuppressWarnings("unchecked")
	        	public void render(Listitem row, Object data) throws Exception {
	                CardApproval pCardApproval = (CardApproval) data;
	
	                row.setValue(pCardApproval);
	                
	                row.appendChild(new Listcell(pCardApproval.getId().toString()));
	                row.appendChild(new Listcell(pCardApproval.getCard_type()));
	                row.appendChild(new Listcell(CardApprovalService.getApprovalType(alias, String.valueOf(pCardApproval.getApproval_type_id()))));
	                row.appendChild(new Listcell(CardApprovalService.getClientName(alias, pCardApproval.getUser_id(), branch)));
	                row.appendChild(new Listcell(pCardApproval.getBranch()));
	                row.appendChild(new Listcell(CardApprovalService.getApprovalState(alias, String.valueOf(pCardApproval.getState_id()))));
	                row.appendChild(new Listcell(pCardApproval.getV_date().toString()));
	                row.appendChild(new Listcell(pCardApproval.getNew_card_account()));
	                row.appendChild(new Listcell(pCardApproval.getHolder_name()));
	                
	                Listcell listcell = new Listcell();
	                
	                if((access[0].equals("approve") && (pCardApproval.getState_id() == Constants.APPROVAL_STATE_CONFIRM)) || 
	                		(access[0].equals("confirm") && (pCardApproval.getState_id() == Constants.APPROVAL_STATE_OPEN))) {
	                	
		                // Buttons
		                TiCardApprovalAction[] action = CardApprovalService.getActions(pCardApproval.getState_id());
							
						
						for(int i = 0; (action[i] != null) && (i < action.length); )
						{						
							Button button = new Button();
							
							button.setImage("");
							button.setLabel(action[i].getName());
							button.setAttribute("actionMethod", action[i].getAction_method());
							button.setAttribute("methodArgument", pCardApproval.getId());
							button.addEventListener(Events.ON_CLICK, dynamicButton);
			                listcell.appendChild(button);
			                
			                i++;
						}
	                }
					row.appendChild(listcell);	
	        	}
    		}
		);

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
			Method method = reflectionClass.getMethod(methodName, Long.class, Boolean.class);
			
			method.invoke(null, (Long) event.getTarget().getAttribute("methodArgument"), smart);
			
			alert(result.getName());
			refreshModel(_startPageNumber);
		}
    	
    };
    
    public static void openCard(Long id, Boolean smart) {
    	
    }
    
    public static void confirmCard(Long id, Boolean smart) {

    	result = CardApprovalService.confirmCard(id);    	
    }
    
    public static void approveCard(Long id, Boolean smart) {
    	
    	result = TclientService.openCard(alias, CardApprovalService.getCardApproval(id), smart);
    	if(result.getCode() == 0) {
    		CardApprovalService.approveCard(id);
    	}
    }
    
    public static void closeCard(Long id, Boolean smart) {
    	
    	result = CardApprovalService.closeCard(id);
    }

	public void onPaging$cardapprovalPaging(ForwardEvent event) {
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModel(_startPageNumber);
	}


	private void refreshModel(int activePage) {
		cardapprovalPaging.setPageSize(_pageSize);
		
		if(smart) {
			filter.setCard_type("Smart Money");
		}
		else {
			filter.setCard_type("NOSMART");
		}
		
		filter.setAccess(access[0]);
		
		
		model = new PagingListModel(activePage, _pageSize, filter, "");

		if(_needsTotalSizeUpdate) {
	        _totalSize = model.getTotalSize(filter, alias);
	        _needsTotalSizeUpdate = false;
		}

		cardapprovalPaging.setTotalSize(_totalSize);

		dataGrid.setModel((ListModel) model);
		if (model.getSize() > 0) {
			this.current =(CardApproval) model.getElementAt(0);
			sendSelEvt();
		}
	}



	//Omitted...
	public CardApproval getCurrent() {
		return current;
	}
	
	public void setCurrent(CardApproval current) {
		this.current = current;
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
	    }
	    else {
            btn_first.setDisabled(false);
            btn_prev.setDisabled(false);
	    }
	    if(dataGrid.getSelectedIndex() == (model.getSize() - 1)) {
            btn_next.setDisabled(true);
            btn_last.setDisabled(true);
	    }
	    else {
            btn_next.setDisabled(false);
            btn_last.setDisabled(false);
	    }
	    SelectEvent evt = new SelectEvent("onSelect", dataGrid, dataGrid.getSelectedItems());
	    Events.sendEvent(evt);
	}


	
	public void onClick$btn_search() {
		grd.setVisible(false);
		frm.setVisible(true);
		fgrd.setVisible(true);
	}


	public void onClick$btn_save() {
	try{
	    if(fgrd.isVisible()){
	        filter = new CardApprovalFilter();
	        
	          filter.setId(fid.getValue().equals("") ? null : Long.parseLong(fid.getValue()));
	          filter.setCard_type(fcard_type.getValue());
	          filter.setApproval_type_id(fapproval_type_id.getValue().equals("") ? 0 : Integer.parseInt(fapproval_type_id.getValue()));
	          filter.setUser_id(fuser_id.getValue());
	          filter.setBranch(fbranch.getValue());
	          filter.setState_id(fstate_id.getValue().equals("") ? 0 : Integer.parseInt(fstate_id.getValue()));
	               
	          
	          filter.setV_date(fv_date.getValue());

	    }
	    
        frm.setVisible(false);
        fgrd.setVisible(false);
        grd.setVisible(true);
        
	refreshModel(_startPageNumber);
	SelectEvent evt = new SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
	Events.sendEvent(evt);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	
	}
	
	public void onClick$btn_cancel() {
	    if(fgrd.isVisible()) {
            filter = new CardApprovalFilter();
	    }
		fgrd.setVisible(false);
		grd.setVisible(true);
		CheckNull.clearForm(fgrd);
		refreshModel(_startPageNumber);
	}



}
