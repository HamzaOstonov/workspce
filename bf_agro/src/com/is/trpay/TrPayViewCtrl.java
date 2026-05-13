package com.is.trpay;

import globus.IssuingWS.IssuingPortProxy;
import globus.issuing_v_01_02_xsd.OperationConnectionInfo;
import globus.issuing_v_01_02_xsd.RowType_ExecTransaction_Request;
import globus.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder;
import globus.issuing_v_01_02_xsd.holders.RowType_ExecTransaction_ResponseHolder;




import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

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
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModel;
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


import com.is.ConnectionPool;
import com.is.agrotieto.tieto.AccInfo;
import com.is.agrotieto.tieto.TclientService;
import com.is.user.Action;
import com.is.utils.CheckNull;
import com.is.utils.Res;

public class TrPayViewCtrl extends GenericForwardComposer{
	        private Div frm;
	        private Listbox dataGrid,paytmpGrid,documentsGrid;
	        private Paging contactPaging;
	        private Div grd, dfgrd;
	        private Grid addgrd,frmgrd,fgrd;
	        private Toolbarbutton btn_last;
	        private Toolbarbutton btn_next;
	        private Toolbarbutton btn_prev;
	        private Toolbarbutton btn_first;
	        private Toolbarbutton btn_add;
	        private Toolbarbutton btn_search;
	        private Toolbarbutton btn_back, fbt, btn_ref$refwnd, btn_cancel$refwnd;
	        private Toolbar tb;
	        private Textbox id,branch,operation_id,amount,card_acc,cur_acc,parentgroupid,state,account_no,cl_name;
	        private Textbox aid,abranch,aoperation_id,aamount,acard_acc,acur_acc,aparentgroupid,astate,aaccount_no,acl_name ;
	        private Textbox fid,fbranch,foperation_id,famount,fcard_acc,fcur_acc,fparentgroupid,fstate,faccount_no,fcl_name ;
	        private Datebox date_created,adate_created,fdate_created;
	        private Paging trpayPaging;
	        private  int _pageSize = 15;
	        private int _startPageNumber = 0;
	        private int _totalSize = 0;
	        private boolean _needsTotalSizeUpdate = true;
	        private Window refwnd;
	        public TrPayDocs tcurrent ;

	        private DecimalFormat dmf = new DecimalFormat("0.00##");
	        public TrPayFilter filter = new TrPayFilter();
            private String un,pwd;
	        PagingListModel model = null;
	        ListModelList lmodel =null;
	        private AnnotateDataBinder binder;
	        private SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	        private int uid = 0;
	        private Toolbarbutton bt = null;
	        private IssuingPortProxy pp = null;
	        private static HashMap< String,String> _trState = null;
	        //private final int actid=0;
	        private String alias;
	        private Toolbar toolbar_filter;
	        private int access;
	        private String divide = "100";
            private BigDecimal divideBigDecimal = new BigDecimal(divide);
	       
	        public class Cur_cont
	        {
	        	String un;
	        	String pwd;
	        	String alias;
	        	TrPay trpay1;
	        	int actid;
	        	
	        	public Cur_cont (String un, String pwd, String alias, TrPay trpay1, int actid)
	        	{
	        		this.un = un;
	        		this.pwd = pwd;
	        		this.alias = alias;
	        		this.trpay1 = trpay1;
	        		this.actid = actid;
	        	}
	        	
	        }
	        private Cur_cont cur_cont;
	        


	    private TrPay current = new TrPay();

	        public TrPayViewCtrl() {
	                super('$', false, false);
	        }
	    /**
	     *
	     *
	     */
	    @Override
	    public void doAfterCompose(Component comp) throws Exception {
	            super.doAfterCompose(comp);
	            // TODO Auto-generated method stub
	                binder = new AnnotateDataBinder(comp);
	                binder.bindBean("current", this.current);
	                binder.loadAll();
	        String[] parameter = (String[]) param.get("ht");
	        alias = (String) session.getAttribute("alias");
	        if (parameter!=null){
	                _pageSize = Integer.parseInt( parameter[0])/36;
	                dataGrid.setRows(Integer.parseInt( parameter[0])/36);
	        }

	        uid = (Integer) session.getAttribute("uid");
	        un = (String) session.getAttribute("un");
	        pwd= (String) session.getAttribute("pwd");
	        _trState = com.is.utils.RefDataService.getHTrState(alias);
	        
	        this.access = com.is.user.UserService.getUser_Access(alias, uid);
	        
	        List<State> states;
	        states = TrPayService.getStates(alias);
	        
	        for ( int i=0;i<states.size();i++)
	        {
		        fbt = new Toolbarbutton();
		        fbt.setLabel(states.get(i).getName());
		        fbt.setImage("/images/filter.png");
		        fbt.setAttribute("state_id", states.get(i).getId());
		        fbt.setAttribute("deal_id", states.get(i).getDeal_id());
		        fbt.addEventListener(Events.ON_CLICK, new EventListener() {
					@Override
					public void onEvent(Event event)
					{
						filter.setState((Integer)event.getTarget().getAttribute("state_id"));
						filter.setDeal_id((Integer)event.getTarget().getAttribute("deal_id"));
						if (access>0) filter.setBranch((String)session.getAttribute("branch"));
						refreshModel(_startPageNumber);
					}
            	 });
		        toolbar_filter.appendChild(fbt);
	        }
	        fbt = new Toolbarbutton();
	        fbt.setLabel("Очистить фильтр");
	        fbt.setImage("/images/filter(delete).png");
	        fbt.setLeft("100px");
	        fbt.addEventListener(Events.ON_CLICK, new EventListener() {
				@Override
				public void onEvent(Event event)
				{
					filter = new TrPayFilter();
					if (access>0) filter.setBranch((String)session.getAttribute("branch"));
					refreshModel(_startPageNumber);
				}
        	 });
	        toolbar_filter.appendChild(fbt);
	        
	        if (access>0) filter.setBranch((String)session.getAttribute("branch"));
	            dataGrid.setItemRenderer(new ListitemRenderer(){
	        @SuppressWarnings("unchecked")
	        public void render(Listitem row, Object data) throws Exception {
	                    TrPay pTrPay = (TrPay) data;
	                    Listcell lc = new Listcell();
	                    List<Action> la = TrPayService.getActions(pTrPay.getState(), uid, (String)session.getAttribute("branch"), pTrPay.getDeal_id(), alias);
	                   // if (la.size()==0) row.setVisible(false);
	                    for ( int i=0;i<la.size();i++){
	                    
	                    	bt = new Toolbarbutton();
	                    	bt.setLabel(la.get(i).getName());
	                    	bt.setImage(la.get(i).getIcon());
	                    	bt.setAttribute("actid", la.get(i).getId());
	                    	
	                    	bt.setAttribute("deal_id", pTrPay.getDeal_id());
	                    	bt.setAttribute("pTrPay", pTrPay);
	                    	bt.addEventListener(Events.ON_CLICK, new EventListener() {

								@Override
								public void onEvent(Event event)
										throws Exception 
								{
									cur_cont = new Cur_cont(un,pwd,alias,(TrPay)event.getTarget().getAttribute("pTrPay"),(Integer)event.getTarget().getAttribute("actid"));
									
									if (false)
									{
										refwnd.setVisible(true);
									}
									else authomat_step(cur_cont);
									
									
									/*Connection c = null;
									c = ConnectionPool.getConnection(un,pwd,alias);
									c.setAutoCommit(false);
									
									Res rs = TrPayService.doAction(un, pwd, (TrPay)event.getTarget().getAttribute("pTrPay"),(Integer)event.getTarget().getAttribute("actid"),alias,c);
									if (rs.getCode()<0)
										{
											alert(rs.getName());
										}
									else
										{
											TrPay tp1 = (TrPay)event.getTarget().getAttribute("pTrPay");
											if(TrPayService.isEndState(tp1.getId(),	alias, c))
											{
											    if (send2t((TrPay)event.getTarget().getAttribute("pTrPay"))) {c.commit();}
											    else c.rollback();
											}
											else c.commit();
											
										   refreshModel(_startPageNumber);
										}
									ConnectionPool.close(c);*/
								}
	                    		 
	                    	 });
	                    	lc.appendChild(bt);
	                    }
	                    
	                    row.setValue(pTrPay);
	                    row.setCheckable(true);
	                    row.appendChild(new Listcell(pTrPay.getBranch()));
	                    row.appendChild(new Listcell(pTrPay.getCard_acc()));
	                    row.appendChild(new Listcell(pTrPay.getCur_acc()));
	                    row.appendChild(new Listcell(pTrPay.getPan()));
	                    row.appendChild(new Listcell( dmf.format(pTrPay.getAmount().divide(divideBigDecimal))));
	                    row.appendChild(new Listcell(df.format(pTrPay.getDate_created())));
	                    row.appendChild(lc);
	                    row.appendChild(new Listcell(pTrPay.getCl_name()));
	                    row.appendChild(new Listcell(_trState.get(  pTrPay.getState()+"")));
	                   
	        }});
	           // pp = new IssuingPortProxy(ConnectionPool.getValue("TIETO_HOST", alias),"test","test");
	            pp = new IssuingPortProxy("");
	        refreshModel(_startPageNumber);
	        
	        paytmpGrid.setItemRenderer(new ListitemRenderer(){
	            @SuppressWarnings("unchecked")
	            public void render(Listitem row, Object data) throws Exception {
	                        TrPayDocs pTrPayDocs = (TrPayDocs) data;

	                        row.setValue(pTrPayDocs);
	                     
	                        row.appendChild(new Listcell(pTrPayDocs.getPay_id()+""));
	                        row.appendChild(new Listcell(pTrPayDocs.getBranch()));
	                        row.appendChild(new Listcell(df.format(pTrPayDocs.getD_date())));
	                        row.appendChild(new Listcell(pTrPayDocs.getBank_cl()));
	                        row.appendChild(new Listcell(pTrPayDocs.getAcc_cl()));
	                        row.appendChild(new Listcell(pTrPayDocs.getName_cl()));
	                        row.appendChild(new Listcell(pTrPayDocs.getBank_co()));
	                        row.appendChild(new Listcell(pTrPayDocs.getAcc_co()));
	                        row.appendChild(new Listcell(pTrPayDocs.getName_co()));
	                        row.appendChild(new Listcell(dmf.format(pTrPayDocs.getSumma())));
	                       
	            }});
	        
	        documentsGrid.setItemRenderer(new ListitemRenderer(){
	            @SuppressWarnings("unchecked")
	            public void render(Listitem row, Object data) throws Exception {
	            			Paydoc paydoc = (Paydoc) data;
	            			
	                        row.setValue(paydoc);
	                        
	                        row.appendChild(new Listcell(Integer.toString(paydoc.getORD())));
	                        row.appendChild(new Listcell(paydoc.getBRANCH()));
	                        row.appendChild(new Listcell(df.format(paydoc.getD_DATE())));
	                        row.appendChild(new Listcell(paydoc.getBANK_CL()));
	                        row.appendChild(new Listcell(paydoc.getACC_CL()));
	        				row.appendChild(new Listcell(paydoc.getNAME_CL()));
	        				row.appendChild(new Listcell(paydoc.getBANK_CO()));
	        				row.appendChild(new Listcell(paydoc.getACC_CO()));
	        				row.appendChild(new Listcell(paydoc.getNAME_CO()));
	        				row.appendChild(new Listcell(paydoc.getPURPOSE()));
	        				row.appendChild(new Listcell(Long.toString(paydoc.getSUMMA())));
	        				row.appendChild(new Listcell(paydoc.getTYPEDOC()));
	        				row.appendChild(new Listcell(paydoc.getPDC()));
	        				row.appendChild(new Listcell(paydoc.getCASH_CODE()));
	        				row.appendChild(new Listcell(Integer.toString(paydoc.getID_TRANSH_PURP())));
	        				row.appendChild(new Listcell(paydoc.getG_BRANCH()));
	        				row.appendChild(new Listcell(Integer.toString(paydoc.getG_DOCID())));
	            }});

	    }
	    

	public void onPaging$trpayPaging(ForwardEvent event){
	    final PagingEvent pe = (PagingEvent) event.getOrigin();
	    _startPageNumber = pe.getActivePage();
	    refreshModel(_startPageNumber);
	}


	private void refreshModel(int activePage){
	        trpayPaging.setPageSize(_pageSize);
	    model = new PagingListModel(activePage, _pageSize, filter,alias);


	            _totalSize = model.getTotalSize(filter,alias);


	    trpayPaging.setTotalSize(_totalSize);

	    dataGrid.setModel((ListModel) model);
	    if (model.getSize()>0){
	    	dataGrid.setSelectedIndex(0);
	    sendSelEvt();
	    }
	}



	// Omitted...
	public TrPay getCurrent() {
	    return current;
	}

	public void setCurrent(TrPay current) {
	    this.current = current;
	}
	
	
	public void authomat_step(Cur_cont cur_cont)
	{
		
		un = cur_cont.un;
		pwd = cur_cont.pwd;
		alias = cur_cont.alias;
		TrPay trpay1 = cur_cont.trpay1;
		int actid = cur_cont.actid;
			
		Connection c = null;
		try 
		{
					c = ConnectionPool.getConnection(un,pwd,alias);
					c.setAutoCommit(false);
					
					Res rs = TrPayService.doAction(un, pwd, trpay1,actid,alias,c);
					if (rs.getCode()<0)
						{
							alert(rs.getName());
						}
					else
						{
							TrPay tp1 = trpay1;
							if(TrPayService.isEndState(tp1.getId(),	alias, c))
							{
							    if (send2t(trpay1)) {c.commit();}
							    else c.rollback();
							}
							else c.commit();
							
						   refreshModel(_startPageNumber);
						}
		
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
	    } 
		finally
		{
	        ConnectionPool.close(c);
	    }
	}

	
	public void onDoubleClick$dataGrid$grd() {
	                grd.setVisible(false);
	                frm.setVisible(true);
	                frmgrd.setVisible(true);
	                addgrd.setVisible(false);
	                dfgrd.setVisible(false);
	                btn_back.setImage("/images/folder.png");
	                btn_back.setLabel(Labels.getLabel("grid"));
	                amount.setValue((current.getAmount().divide(divideBigDecimal)).toString());
	                documentsGrid.setModel(new BindingListModelList(TrPayService.getPaydocs(current.getId(), alias),false));
	}




	public void onClick$btn_back() {
	        if (frm.isVisible()){
	            frm.setVisible(false);
	            grd.setVisible(true);
	            btn_back.setImage("/images/file.png");
	            btn_back.setLabel(Labels.getLabel("back"));
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
	        paytmpGrid.setModel(new BindingListModelList(TrPayService.getTrPayDocs(current.getId(),alias),false));
	}


	public void onClick$btn_add() {
	        onDoubleClick$dataGrid$grd();
	        frmgrd.setVisible(false);
	        addgrd.setVisible(true);
	        dfgrd.setVisible(false);
	}

	public void onClick$btn_search() {
	        onDoubleClick$dataGrid$grd();
	        frmgrd.setVisible(false);
	        addgrd.setVisible(false);
	        dfgrd.setVisible(true);
	}


	public void onClick$btn_save() {
	    try{
	              filter.setId(Long.parseLong( fid.getValue()));
	              filter.setBranch(fbranch.getValue());
	              filter.setOperation_id(Integer.parseInt( foperation_id.getValue()));
	              filter.setAmount(Integer.parseInt(famount.getValue()));
	              filter.setCard_acc(fcard_acc.getValue());
	              filter.setCur_acc(fcur_acc.getValue());
	              filter.setDate_created(fdate_created.getValue());
	              filter.setParent_group_id(Integer.parseInt(fparentgroupid.getValue()));
	              filter.setState(Integer.parseInt(fstate.getValue()));
	              filter.setAccount_no(faccount_no.getValue());
	              filter.setCl_name(fcl_name.getValue());

	    onClick$btn_back();
	    refreshModel(_startPageNumber);
	    SelectEvent evt = new SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
	    Events.sendEvent(evt);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	}
	public void onClick$btn_cancel() {
	        if(dfgrd.isVisible()){
	                filter = new TrPayFilter();
	        }
	    onClick$btn_back();
	    frmgrd.setVisible(true);
	    addgrd.setVisible(false);
	    dfgrd.setVisible(false);
	    CheckNull.clearForm(addgrd);
	    CheckNull.clearForm(fgrd);
	    refreshModel(_startPageNumber);
	}
	
	public void onClick$btn_ref$refwnd()
	{
		refwnd.setVisible(false);
		authomat_step(cur_cont);
	}
	public void onClick$btn_cancel$refwnd()
	{
		refwnd.setVisible(false);
	}
	
	private boolean send2t(TrPay tp){
		OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
		RowType_ExecTransaction_Request parameters = new RowType_ExecTransaction_Request();
		OperationResponseInfoHolder responseInfo = new OperationResponseInfoHolder();
		RowType_ExecTransaction_ResponseHolder details = new RowType_ExecTransaction_ResponseHolder();
		
		try {
			
			connectionInfo.setBANK_C("01");
			connectionInfo.setGROUPC("02");
			parameters.setBANK_C("01");
			parameters.setGROUPC("02");
			parameters.setTRAN_TYPE(Integer.toString(TrPayService.get_tieto_operation_code(tp.getOperation_id(), alias)));
			parameters.setTRAN_CCY("USD");
			parameters.setPAYMENT_MODE("0");
			parameters.setTRAN_AMNT(tp.getAmount());
			parameters.setACCOUNT_NO(BigDecimal.valueOf(Integer.parseInt(tp.getAccount_no())));
			parameters.setCARD_ACCT(tp.getCard_acc());
			 pp.executeTransaction(connectionInfo, parameters, responseInfo, details);
			 if(responseInfo.value.getResponse_code().intValue()!=0){
				 alert(responseInfo.value.getError_action()+"\r\n"+responseInfo.value.getError_description());
				 return false;
			 }
		} catch (Exception e) {
			alert(e.getMessage());
			e.printStackTrace();
			return false;
		}	
		return true;
	}

}
