package com.is.tieto;

import com.is.tieto_globuz.*;
import capitalBank.IssuingWS.IssuingPortProxy;
import capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo;
import capitalBank.issuing_v_01_02_xsd.OperationResponseInfo;
import capitalBank.issuing_v_01_02_xsd.RowType_AddCardToStopList_Request;
import capitalBank.issuing_v_01_02_xsd.RowType_RemoveCardFromStop_Request;
import com.is.ConnectionPool;
import com.is.customer.Customer;
import com.is.customer.CustomerService;
import com.is.customer.CustomerService.link;
import com.is.customer.TietoCustomer;
import com.is.tieto_globuz.tietoAccount.GlobuzAccountService;
import com.is.trpay.TrPay;
import com.is.trpay.TrPayFilter;
import com.is.trpay.TrPayService;
import com.is.user.Action;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.Res;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.type.OrientationEnum;
import org.zkoss.util.media.AMedia;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.*;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zkplus.databind.BindingListModelList;
import org.zkoss.zul.*;
import org.zkoss.zul.event.PagingEvent;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

//import globus.issuing_v_01_02_xsd.ListType_AccountInfo;
//import glubus.issuing_v_01_02_xsd.ListType_CardInfo;

public class TclientViewCtrl extends GenericForwardComposer{
    
	private Window paywnd,blockwnd,printwnd,addwnd;
	private Iframe printwnd$rpframe;
	private Decimalbox paywnd$amnt;
	private Div frm;
    private Listbox dataGrid,accGrid, paywnd$payGrid;
    private Label paywnd$add_currency_type;
    private Div grd;
    private Grid addgrd,frmgrd,fgrd;
    //private Toolbarbutton btn_last;//,printwnd$btn_print;
    //private Toolbarbutton btn_next;
    //private Toolbarbutton btn_prev;
    //private Toolbarbutton btn_first;
    //private Toolbarbutton btn_add;
    //private Toolbarbutton btn_search;
    //private Toolbarbutton btn_back;
    private Toolbarbutton paywnd$btn_block,paywnd$btn_unblock;
    private Toolbarbutton paywnd$fbt;
    private Toolbar paywnd$pay_tlb;
    //private Toolbar tb;
    private Textbox client,bank_c,client_b,cl_type,cln_cat,rec_date,f_names,surname,title,m_name,b_date,r_street,r_city,r_cntry,usrid,ctime,status,search_name,sex,serial_no,doc_since,issued_by,status_change_date,blockwnd$txtstopcauses;
    private Textbox aclient,abank_c,aclient_b,acl_type,acln_cat,arec_date,af_names,asurname,atitle,am_name,ab_date,ar_street,ar_city,ar_cntry,ausrid,actime,astatus,asearch_name,asex,aserial_no,adoc_since,aissued_by,astatus_change_date, paywnd$curacc;
    private Textbox fclient,fbank_c,fclient_b,fcl_type,fcln_cat,ff_names,fsurname,ftitle,fm_name,fr_street,fr_city,fr_cntry,fusrid,fctime,fstatus,fsearch_name,fsex,fserial_no,fdoc_since,fissued_by,fstatus_change_date ,fcard, paywnd$inc_ord_num;
    private Paging tclientPaging;
    private Datebox fb_date,frec_date;
    private RefCBox paywnd$scurracc,blockwnd$sstopcauses,addwnd$sproduct;
    private  int _pageSize = 15;
    private int _startPageNumber = 0;
    private int _totalSize = 0;
    private boolean _needsTotalSizeUpdate = true;
    private String un,pwd,branch,alias;
    private int uid;
    private TclientFilter filter = new TclientFilter();
    private Tclient current = new Tclient();
    private AccInfo accinfo = new AccInfo();

    PagingListModel model = null;
    ListModelList lmodel =null;
    private AnnotateDataBinder binder;
    private SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
    private DecimalFormat dmf = new DecimalFormat("0.00##");
    private IssuingPortProxy pp = null;
    private static HashMap< String,String> _tstopCauses = null;

    private int pay_card_doc_id;
    private String curip, cur_act;

    public TclientViewCtrl() {
            super('$', false, false);
    }
/**
 *
 *
 */
@Override
public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);

	String str = "dfg rte wf dfh.fbdfgd";
	int leng = str.length();

	// TODO Auto-generated method stub
            binder = new AnnotateDataBinder(comp);
            binder.bindBean("current", this.current);
            binder.loadAll();
    String[] parameter = (String[]) param.get("ht");
    if (parameter!=null){
            _pageSize = Integer.parseInt( parameter[0])/36;
            dataGrid.setRows(Integer.parseInt( parameter[0])/36/3);
            _pageSize = 3;
            dataGrid.setRows(3);
    }

    un = (String) session.getAttribute("un");
    pwd= (String) session.getAttribute("pwd");
    uid= (Integer) session.getAttribute("uid");
    branch= (String) session.getAttribute("branch");
    alias = (String) session.getAttribute("alias");
    curip = (String) session.getAttribute("curip");
    _tstopCauses = com.is.utils.RefDataService.getHTstopCauses();

        dataGrid.setItemRenderer(new ListitemRenderer(){
    @SuppressWarnings("unchecked")
    public void render(Listitem row, Object data) throws Exception {
                Tclient pTclient = (Tclient) data;

                row.setValue(pTclient);
                row.appendChild(new Listcell(pTclient.getClient_b()!=null ?  pTclient.getClient_b():"--//--"));
                row.appendChild(new Listcell(pTclient.getF_names()));
                row.appendChild(new Listcell(pTclient.getSurname()));
                row.appendChild(new Listcell(pTclient.getB_date()!=null ?  df.format(pTclient.getB_date()):"--//--"));
    }});
        
        
        accGrid.setItemRenderer(new ListitemRenderer(){
            @SuppressWarnings("unchecked")
            public void render(Listitem row, Object data) throws Exception {
                        AccInfo pAccInfo = (AccInfo) data;

                        row.setValue(pAccInfo);
                        row.appendChild(new Listcell(pAccInfo.getCard()));
                        row.appendChild(new Listcell(df.format(pAccInfo.getAb_expirity())));
                        row.appendChild(new Listcell(pAccInfo.getCard_acct()));
                        row.appendChild(new Listcell(pAccInfo.getTranz_acct()));
                        row.appendChild(new Listcell(_tstopCauses.get(pAccInfo.getStatus1())));
            }});
        
      

    refreshModel(_startPageNumber);

    pp = new IssuingPortProxy(ConnectionPool.getValue("TIETO_HOST", alias),
    		ConnectionPool.getValue("EMPC_TIETO_HOST_USERNAME", alias),
    		ConnectionPool.getValue("EMPC_TIETO_HOST_PASSWORD", alias));

    paywnd$payGrid.setItemRenderer(new ListitemRenderer(){
        @SuppressWarnings("unchecked")
        public void render(Listitem row, Object data) throws Exception {
                    TrPay pTrPay = (TrPay) data;
                 
                    row.setValue(pTrPay);
                   
                    row.appendChild(new Listcell(pTrPay.getBranch()));
                    row.appendChild(new Listcell(pTrPay.getCard_acc()));
                    row.appendChild(new Listcell(df.format(pTrPay.getDate_created())));
                    row.appendChild(new Listcell( dmf.format( pTrPay.getAmount()/100)));
        }});
    
    addwnd$sproduct.setModel((new ListModelList(com.is.utils.RefDataService.getOfrProd(alias))));


}

public void onPaging$tclientPaging(ForwardEvent event){
final PagingEvent pe = (PagingEvent) event.getOrigin();
_startPageNumber = pe.getActivePage();
refreshModel(_startPageNumber);
}


	private void refreshModel(int activePage) {
		tclientPaging.setPageSize(_pageSize);
		model = new PagingListModel(activePage, _pageSize, filter);

		if (_needsTotalSizeUpdate) {
			_totalSize = model.getTotalSize(filter,"");
			// _needsTotalSizeUpdate = false;
		}

		tclientPaging.setTotalSize(_totalSize);

		dataGrid.setModel((ListModel) model);
		/*if (model.getSize() > 0) {
			
			dataGrid.setSelectedIndex(0);
			sendSelEvt();
		}*/
	}



//Omitted...
	public Tclient getCurrent() {
		return current;
	}

	public void setCurrent(Tclient current) {
		this.current = current;
	}

    

public AccInfo getAccinfo() {
		return accinfo;
	}
public void setAccinfo(AccInfo accinfo) {
	this.accinfo = accinfo;
}
public TclientFilter getFilter() {
	return filter;
}
public void setFilter(TclientFilter filter) {
	this.filter = filter;
}
/*public void onDoubleClick$dataGrid$grd() {
	
}*/




public void onClick$btn_back() {
    if (frm.isVisible()){
        frm.setVisible(false);
        grd.setVisible(true);
      //  btn_back.setImage("/images/file.png");
      //  btn_back.setLabel(Labels.getLabel("back"));
    }//else onDoubleClick$dataGrid$grd();
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
   /* if (dataGrid.getSelectedIndex()==0){
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
    Events.sendEvent(evt);*/
}


public void onClick$btn_add() {
    //onDoubleClick$dataGrid$grd();
    frmgrd.setVisible(false);
    addgrd.setVisible(true);
    fgrd.setVisible(false);
}

public void onClick$btn_search() {
   // onDoubleClick$dataGrid$grd();
    frmgrd.setVisible(false);
    addgrd.setVisible(false);
    fgrd.setVisible(true);
}


public void onClick$btn_save() {
try{
        filter = new TclientFilter();
        
          filter.setClient(fclient.getValue());
          filter.setBank_c(fbank_c.getValue());
          filter.setClient_b(fclient_b.getValue());
          filter.setCl_type(fcl_type.getValue());
          filter.setCln_cat(fcln_cat.getValue());
          filter.setRec_date(frec_date.getValue());
          filter.setF_names(ff_names.getValue());
          filter.setSurname(fsurname.getValue());
//          filter.setTitle(ftitle.getValue());
//          filter.setM_name(fm_name.getValue());
          filter.setB_date(fb_date.getValue()!=null ?  fb_date.getValue() : null);
          filter.setCard(fcard.getValue());
          filter.setSearch_name(fsearch_name.getValue());
          filter.setSerial_no(fserial_no.getValue());
/*
          filter.setR_street(fr_street.getValue());
          filter.setR_city(fr_city.getValue());
          filter.setR_cntry(fr_cntry.getValue());
          filter.setUsrid(fusrid.getValue());
          filter.setCtime(fctime.getValue());
          filter.setStatus(fstatus.getValue());
          
          filter.setSex(fsex.getValue());
          
          filter.setDoc_since(fdoc_since.getValue());
          filter.setIssued_by(fissued_by.getValue());
          filter.setStatus_change_date(fstatus_change_date.getValue());
*/
refreshModel(_startPageNumber);
SelectEvent evt = new SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
Events.sendEvent(evt);
} catch (Exception e) {
    com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
}

}
public void onClick$btn_cancel() {
	
		filter = new TclientFilter();
		
		CheckNull.clearForm(fgrd);
		refreshModel(_startPageNumber);
}

 public void onSelect$dataGrid(){
	 if (current!=null){
		 if (TclientService.check_user(alias, branch, current.getClient())=="")
		 {
			 alert("Клиент не объединен");
			 return;
		 }
	 accGrid.setModel(new BindingListModelList(TclientService.getAccInfo(current.getClient()),false));
	 }
 }
 
 
 public void onDoubleClick$accGrid() {
	 
	 String client_id;
	 client_id = TclientService.check_user(alias, branch, current.getClient());
	 if (client_id=="")
	 {
		 alert("Клиент не объединен");
		 return;
	 }
	 
	 link lnk = CustomerService.get_link_tieto(current.getClient(), branch, alias);
	 paywnd$curacc.setValue(lnk.Cur_acc);
	
	 TrPayFilter trf = new TrPayFilter();
	 trf.setCard_acc(accinfo.getTranz_acct());
	 if(!CheckNull.isEmpty(accinfo.getTranz_acct())){
		 TietoCustomer tc = CustomerService.getTietoCustomer(accinfo.getClient(), branch, alias);
	// paywnd$scurracc.setModel((new ListModelList(com.is.utils.RefDataService.getCurrAcc(tc.getBank_customer_id(),alias))));
	 paywnd$payGrid.setModel(new ListModelList(TrPayService.getTrPaysFl(0, 3,trf ,alias)));
	// paywnd$add_currency_type.setValue(com.is.labels.doc.summa + accinfo.getCcy());
	 }
	 
	 List<Action> actions;
		actions = TclientService.getActions(uid, branch, alias);
		paywnd$pay_tlb.getChildren().clear();
	    for ( int i=0;i<actions.size();i++)
	    {
	    	paywnd$fbt = new Toolbarbutton();
	    	paywnd$fbt.setLabel(actions.get(i).getName());
	    	paywnd$fbt.setImage(actions.get(i).getIcon());
	    	paywnd$fbt.setAttribute("act_desc", actions.get(i).getName());
	    	//paywnd$fbt.setAttribute("state_id", states.get(i).getId());
	    	paywnd$fbt.setAttribute("deal_id", actions.get(i).getDeal_id());
	    	paywnd$fbt.setAttribute("act_id", actions.get(i).getId());
//	    	paywnd$fbt.setAttribute("rep_type_id", actions.get(i).getRep_type_id());
	    	paywnd$fbt.setWidth("1000px");
	    	paywnd$fbt.addEventListener(Events.ON_CLICK, new EventListener() {
				@Override
				public void onEvent(Event event)
				{
					cur_act = (String)event.getTarget().getAttribute("act_desc");
					pay((Integer)event.getTarget().getAttribute("deal_id"),
							(Integer)event.getTarget().getAttribute("rep_type_id"),
							(Integer)event.getTarget().getAttribute("act_id"));
				}
	    	 });
	    	paywnd$pay_tlb.appendChild(paywnd$fbt);
	    }
	 
	 paywnd.setVisible(true);
	// paywnd$btn_block.setVisible( accinfo.getStatus1().equals("0"));
	// paywnd$btn_unblock.setVisible( !paywnd$btn_block.isVisible());
 }
 
 public void onClick$btn_cancel$paywnd(){
	 paywnd$amnt.setValue(BigDecimal.ZERO);
	 paywnd.setVisible(false);
 }
 
 private void pay(int deal_id, int rep_type_id, int act_id)
 {
	 String client_id;
	 client_id = TclientService.check_user(alias, branch, current.getClient());
	 if (client_id=="")
	 {
		 alert("Клиент не объединен");
		 return;
	 }
	 
	 Res res = TclientService.check_allowed_card_action(144, deal_id, act_id, accinfo.getProduct(), alias);
	 if (res.getCode() != 0)
	 {
		 alert(res.getName());
		 return;
	 }
				 
		/*OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
		RowType_ExecTransaction_Request parameters = new RowType_ExecTransaction_Request();
		OperationResponseInfoHolder responseInfo = new OperationResponseInfoHolder();
		RowType_ExecTransaction_ResponseHolder details = new RowType_ExecTransaction_ResponseHolder();
		*/
		try {
						
			TrPay trp = new TrPay();
			trp.setAccount_no(accinfo.getAccount_no()+"");
			trp.setAmount(paywnd$amnt.getValue().intValue()*100);
			trp.setCard_acc(accinfo.getTranz_acct());
			//System.out.println(accinfo.getProduct()+": "+accinfo.getProduct_name());
			trp.setCur_acc(paywnd$curacc.getValue());
			trp.setCl_name(current.getSearch_name());
			trp.setBranch(branch);
			trp.setOperation_id(1);
			trp.setPan(accinfo.getCard());
			trp.setEmp_id(uid);
			trp.setDoc_num(paywnd$inc_ord_num.getValue());
			
			//trp.setCard_acc("22618840999177381001");
			//trp.setCur_acc("20206840299177381001");
			
			Res rs = TrPayService.doAction(un, pwd, trp,act_id, deal_id,alias);
			if (rs.getCode()<0){
				alert(rs.getName());
			}else {
				TrPay trp_res = TrPayService.getTrPay(Integer.parseInt(rs.getName()), alias);
				if (rep_type_id != 0) printp(trp_res);
				
				String oper_desc = TrPayService.getOperation_desc(trp_res.getOperation_id(),alias);
				
				String log = "Операция ["+oper_desc+"] id ["+trp_res.getId()+"] действие ["+cur_act+"] подгруппы ["+TrPayService.getDeal_desc(deal_id, alias)+"] No карты ["+trp_res.getPan()+"] счет карты ["+trp_res.getCard_acc()+"]";
				//UserService.UsrLog(new UserActionsLog(uid, un, curip, 6, 1, log, branch));
				
				pay_card_doc_id = Integer.parseInt(rs.getName());
				System.out.println("new document for card pay id:"+pay_card_doc_id);
			}
			
		} catch (Exception e) {
			alert(e.getMessage());
			com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
		}
	 
	 paywnd.setVisible(false);
	 onSelect$dataGrid();
 }
 /*
 public void onClick$btn_pay$paywnd(){
	 
	 String client_id;
	 client_id = TclientService.check_user(alias, branch, current.getClient());
	 if (client_id=="")
	 {
		 alert("Клиент не объединен");
		 return;
	 }
	 
		OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
		RowType_ExecTransaction_Request parameters = new RowType_ExecTransaction_Request();
		OperationResponseInfoHolder responseInfo = new OperationResponseInfoHolder();
		RowType_ExecTransaction_ResponseHolder details = new RowType_ExecTransaction_ResponseHolder();
		
		try {
						
			TrPay trp = new TrPay();
			trp.setAccount_no(accinfo.getAccount_no()+"");
			trp.setAmount(paywnd$amnt.getValue().intValue()*100);
			trp.setCard_acc(accinfo.getTranz_acct());
			trp.setCur_acc(paywnd$scurracc.getValue());
			trp.setCl_name(current.getSearch_name());
			trp.setBranch(branch);
			trp.setOperation_id(1);
			trp.setPan(accinfo.getCard());
			trp.setEmp_id(uid);
			trp.setDoc_num(paywnd$inc_ord_num.getValue());
			
			//trp.setCard_acc("22618840999177381001");
			//trp.setCur_acc("20206840299177381001");
			
			Res rs = TrPayService.doAction(un, pwd, trp,1, 1,alias);
			if (rs.getCode()<0){
				alert(rs.getName());
			}else {
				TrPay trp_res = TrPayService.getTrPay(Integer.parseInt(rs.getName()), alias);
				printp(trp_res);
				
				pay_card_doc_id = Integer.parseInt(rs.getName());
				System.out.println("new document for card pay id:"+pay_card_doc_id);
			}
			
		} catch (Exception e) {
			alert(e.getMessage());
			com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
		}
	 
	 paywnd.setVisible(false);
	 onSelect$dataGrid();
 }
 
 
 
public void onClick$btn_pay_branch$paywnd(){
	 
	 String client_id;
	 client_id = TclientService.check_user(alias, branch, current.getClient());
	 if (client_id=="")
	 {
		 alert("Клиент не объединен");
		 return;
	 }
	 
		OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
		RowType_ExecTransaction_Request parameters = new RowType_ExecTransaction_Request();
		OperationResponseInfoHolder responseInfo = new OperationResponseInfoHolder();
		RowType_ExecTransaction_ResponseHolder details = new RowType_ExecTransaction_ResponseHolder();
		
		try {
						
			TrPay trp = new TrPay();
			trp.setAccount_no(accinfo.getAccount_no()+"");
			trp.setAmount(paywnd$amnt.getValue().intValue()*100);
			trp.setCard_acc(accinfo.getTranz_acct());
			trp.setCur_acc(paywnd$scurracc.getValue());
			trp.setCl_name(current.getSearch_name());
			trp.setBranch(branch);
			trp.setOperation_id(1);
			trp.setPan(accinfo.getCard());
			trp.setEmp_id(uid);
			trp.setDoc_num(paywnd$inc_ord_num.getValue());
			
			//trp.setCard_acc("22618840999177381001");
			//trp.setCur_acc("20206840299177381001");
			
			Res rs = TrPayService.doAction(un, pwd, trp,1, 2,alias);
			if (rs.getCode()<0){
				alert(rs.getName());
			}else {
				TrPay trp_res = TrPayService.getTrPay(Integer.parseInt(rs.getName()), alias);
				printp(trp_res);
				
				pay_card_doc_id = Integer.parseInt(rs.getName());
				System.out.println("new document for card pay id:"+pay_card_doc_id);
			}
			
		} catch (Exception e) {
			alert(e.getMessage());
			com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
		}
	 
	 paywnd.setVisible(false);
	 onSelect$dataGrid();
 }
 */
 
 public void onClick$btn_block$paywnd(){
	 blockwnd$sstopcauses.setModel(new ListModelList(com.is.utils.RefDataService.getTstopCauses()));
	// blockwnd$txtstopcauses.setValue("");
	 blockwnd.setVisible(true); 
 }
 public void onClick$btn_cancel$blockwnd(){
	 blockwnd.setVisible(false); 
 } 
 
 public void onClick$btn_block$blockwnd(){
		OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
		OperationResponseInfo orInfo = null;
		RowType_AddCardToStopList_Request parameters = new RowType_AddCardToStopList_Request();

		try {
			connectionInfo.setBANK_C(ConnectionPool.getValue("BANK_C", alias));
			connectionInfo.setGROUPC(ConnectionPool.getValue("GROUPC", alias));

			
			parameters.setBANK_C(ConnectionPool.getValue("BANK_C", alias));
			parameters.setGROUPC(ConnectionPool.getValue("GROUPC", alias));
			parameters.setSTOP_CAUSE(blockwnd$sstopcauses.getValue());//parameters.setSTOP_CAUSE("1");
			parameters.setTEXT(blockwnd$txtstopcauses.getValue());
			parameters.setCARD(accinfo.getCard());
			
			
			orInfo = pp.addCardToStop(connectionInfo, parameters);
			 if(orInfo.getResponse_code().intValue()!=0){
				 alert(orInfo.getError_action()+"\r\n"+orInfo.getError_description());
			 }		 
			// UserService.UsrLog(new UserActionsLog(uid, un, curip, 6, 1, "Карта No ["+accinfo.getCard()+"] заблокирована", branch));
			
		} catch (Exception e) {
			alert(e.getMessage());
			com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
		}
	 
	 paywnd.setVisible(false);
	 blockwnd.setVisible(false);
	 onSelect$dataGrid();
}

 public void onClick$btn_unblock$paywnd(){
		OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
		OperationResponseInfo orInfo = null;
		RowType_RemoveCardFromStop_Request parameters = new RowType_RemoveCardFromStop_Request();

		try {
			connectionInfo.setBANK_C("01");
			connectionInfo.setGROUPC("02");

			
			parameters.setBANK_C("01");
			parameters.setGROUPC("02");
			parameters.setTEXT("Card UnBlocked!!!!");
			parameters.setCARD(accinfo.getCard());
			
			
			orInfo = pp.removeCardFromStop(connectionInfo, parameters) ;
			 if(orInfo.getResponse_code().intValue()!=0){
				 alert(orInfo.getError_action()+"\r\n"+orInfo.getError_description());
			 }
			// UserService.UsrLog(new UserActionsLog(uid, un, curip, 6, 1, "Карта No ["+accinfo.getCard()+"] разблокирована", branch));	
		} catch (Exception e) {
			alert(e.getMessage());
			com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
		}
	 
	 paywnd.setVisible(false);
	 onSelect$dataGrid();
}
 
 
 public void onOK$fclient() {
	 onClick$btn_save();
}
 public void onOK$fcard() {
	 onClick$btn_save();
}
 public void onOK$fbank_c() {
	 onClick$btn_save();
}
 public void onOK$fclient_b() {
	 onClick$btn_save();
}
 public void onOK$fcl_type() {
	 onClick$btn_save();
}
 public void onOK$fcln_cat() {
	 onClick$btn_save();
}
 public void onOK$frec_date() {
	 onClick$btn_save();
}
 public void onOK$ff_names() {
	 onClick$btn_save();
}
 public void onOK$fsearch_name() {
	 onClick$btn_save();
}
 public void onOK$fsurname() {
	 onClick$btn_save();
}
 public void onOK$fb_date() {
	 onClick$btn_save();
}
 public void onOK$fserial_no() {
	 onClick$btn_save();
}
 
 
 public void printp(TrPay trp){
	 printwnd.setVisible(true); 
	// printwnd$btn_print.setVisible(false);
	 
	 JasperPrint jasperPrint;
	 AMedia repmd = null;
	
	 HashMap< String,Object> params =  new HashMap<String,Object>();
	 params.put("V_DATE", df.format(trp.getDate_created()));
	 params.put("CLIENT_NAME1", current.getF_names());
	 params.put("CLIENT_NAME2", current.getM_name());
	 params.put("CLIENT_NAME3", current.getSurname());
	 params.put("T_CURRENCY", "USD");
	 params.put("TDP_NUM", trp.getDoc_num());
	 params.put("POST_ADDRESS", current.getR_street());
	 params.put("SUMMA6", Double.toString(trp.getAmount()/100));
	 params.put("ESUMMA6", Long.toString(trp.getEqv_amount()/100));
	 params.put("PSUMMA6", com.is.utils.CheckNull.F2Money(trp.getAmount()/100, "доллар", "центов"));
	 params.put("TVEOPER", accinfo.getTranz_acct());
	 params.put("OPENDOPER", trp.getCur_acc());
	 params.put("ACCDOPER1",com.is.tieto.TclientService.getkass_acc(branch, alias));
	 params.put("BRANCH_NAME", com.is.utils.RefDataService.getMfo_name(branch, alias).get(0).getLabel());
	 
	 
		Connection c = null;
        try{
        	c = ConnectionPool.getConnection(alias);
        	jasperPrint = JasperFillManager.fillReport(application.getRealPath( "reports/TI_PRIHOD_ORDER.jasper"),  params, c);
			jasperPrint.setLeftMargin(0);
			jasperPrint.setRightMargin(0);
			jasperPrint.setTopMargin(0);
			jasperPrint.setBottomMargin(0);
			jasperPrint.setPageHeight(595);//(461);//
			jasperPrint.setPageWidth(842);//(652);//
			jasperPrint.setOrientation(OrientationEnum.LANDSCAPE);  	       
			final byte[] buf = JasperRunManager.runReportToPdf(application.getRealPath( "reports/TI_PRIHOD_ORDER.jasper"), params, c);
			final java.io.InputStream mediais = new ByteArrayInputStream(buf);
  	       
			repmd = new AMedia(current.getF_names()+"_"+current.getM_name()+".pdf", "pdf", "application/pdf", mediais);
        }catch(Exception e) {
        	com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
        }	 
	 
	 if (repmd!=null){
	    printwnd$rpframe.setContent(repmd);
	 
	 }
 }

 
 public void onClick$btn_cancel$printwnd(){
	 printwnd.setVisible(false); 
 }
/* public void onClick$btn_print$printwnd() {
		Clients.evalJavaScript("window.frames[1].focus(); window.frames[1].print();");
		printwnd.setVisible(false);
	}*/
 public void onClick$btn_open(){
	 
	 String client_id;
	 client_id = TclientService.check_user(alias, branch, current.getClient());
	 if (client_id=="")
	 {
		 alert("Клиент не объединен");
		 return;
	 }
	 Customer cs;
	 cs = com.is.customer.CustomerService.getCustomerById(client_id, alias);
	 addwnd$sproduct.setModel((new ListModelList(com.is.utils.RefDataService.getOfrProd(alias, cs.getCode_subject()))));
	 addwnd.setVisible(true);
 }
 
 public void onClick$btn_add$addwnd() {
	 
	// if(!com.is.tieto.TclientService.check_card(addwnd$sproduct.getValue(),TclientService.getAccInfo(current.getClient()), alias))
	// {
	//	 alert("Несовместимые типы карт");
	//	 return;
	// } 
	 
	    TietoCardSetting tcs =  TclientService.getTietoCardSetting(Integer.parseInt(addwnd$sproduct.getValue()), alias);
	    TietoCustomer tc;
	    tc = com.is.customer.CustomerService.getTietoCustomer(current.getClient(), branch, alias);
/*		 String tranzacc = GlobuzAccountService.getCardAcc(un, pwd, "22618",current.getClient_b(), "840", tcs.getId_order_account(), current.getF_names()+" "+current.getM_name(), alias, ) ;
	   if(tranzacc.equals("")){
		   alert("Не удалось открыть счёт для клиента "+current.getClient_b());
		   return;
	   }
		 
		 
		BigDecimal agrnum;
		OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
		OperationResponseInfo orInfo = null;
		KeyType_Agreement mainAgreementInfo = new KeyType_Agreement();
		ListType_CardInfoHolder cardsListInfo = new ListType_CardInfoHolder();
		RowType_CardInfo rtci = new RowType_CardInfo();
		RowType_CardInfo_LogicalCard logicalCard = new RowType_CardInfo_LogicalCard();
		RowType_CardInfo_PhysicalCard physicalCard = new RowType_CardInfo_PhysicalCard();
		RowType_CardInfo_EMV_Data EMV_data = new RowType_CardInfo_EMV_Data();
		
		RowType_AgreementHolder agreementInfo = new RowType_AgreementHolder();
		RowType_Agreement avalue = new RowType_Agreement();
		
		ListType_AccountInfoHolder accountsListInfo = new ListType_AccountInfoHolder();
		RowType_AccountInfo_Base base_info = new RowType_AccountInfo_Base();
		RowType_AccountInfo rtai;
		
		Calendar cal = Calendar.getInstance();
		
		
		try {
			
			connectionInfo.setBANK_C(tcs.getBank_c());
			connectionInfo.setGROUPC(tcs.getGroup_c());
			
			avalue.setBINCOD(tcs.getBin());
			avalue.setBANK_CODE("01");
			avalue.setBRANCH("101");//?
			avalue.setCITY(!CheckNull.isEmpty(current.getR_city()) ? current.getR_city(): "UZB");
			avalue.setPRODUCT("00"+tcs.getCode());
			avalue.setREP_LANG("1");
			avalue.setRISK_LEVEL(tcs.getRisk_level());
			avalue.setSTREET(!CheckNull.isEmpty(current.getR_street()) ? current.getR_street(): "STREET");
			avalue.setSTATUS("10");
			avalue.setCONTRACT("123456");//tcs.getFinancial_conditions());
			avalue.setENROLLED(cal);
			avalue.setCLIENT(current.getClient());
			avalue.setCOUNTRY(!CheckNull.isEmpty(current.getR_cntry()) ? current.getR_cntry(): "UZB");
			//avalue.setGROUPC(ConnectionPool.getValue("GROUPC", alias));
			agreementInfo.value = avalue;
			
			
			base_info.setC_ACCNT_TYPE("00");
			base_info.setCCY("USD");
			base_info.setSTAT_CHANGE("1");
			base_info.setMIN_BAL( tcs.getMinimum_balance());
			base_info.setTRANZ_ACCT(accinfo.getTranz_acct());
			String a = tranzacc;
			//base_info.setTRANZ_ACCT(accinfo.getTranz_acct());
			base_info.setACC_PRTY("1");
			base_info.setCOND_SET(tcs.getFinancial_conditions());
			base_info.setCYCLE("000");
			base_info.setCRD(BigDecimal.valueOf(0));
			base_info.setNON_REDUCE_BAL(BigDecimal.valueOf(50));
			base_info.setSTATUS("0");
			base_info.setLIM_INTR(BigDecimal.valueOf(0));
			rtai = new RowType_AccountInfo(base_info,new RowType_AccountInfo_Additional());
			RowType_AccountInfo[] rtaia = new  RowType_AccountInfo[]{rtai};
			 ListType_AccountInfo ltai = new  ListType_AccountInfo();
			 ltai.setRow(rtaia);

			
			accountsListInfo = new ListType_AccountInfoHolder(ltai);
			
			
			logicalCard.setCOND_SET(tcs.getCard_condition());
			logicalCard.setRISK_LEVEL(tcs.getRisk_level());
			logicalCard.setBASE_SUPP("1");
			logicalCard.setF_NAMES(current.getF_names());
			logicalCard.setSURNAME(current.getSurname());
			physicalCard.setCARD_NAME(current.getF_names()+ " "+ current.getSurname());
			//physicalCard.setEXPIRY1(cal);
			physicalCard.setSTATUS1("0");
			physicalCard.setDESIGN_ID(tcs.getDesign_id());
			//physicalCard.
			//physicalCard.s
			EMV_data.setCHIP_APP_ID(tcs.getId_chip_app());
			
			
			rtci.setLogicalCard(logicalCard);
			rtci.setPhysicalCard(physicalCard);
			rtci.setEMV_Data(EMV_data);
			RowType_CardInfo[] rtcia = {rtci};
			ListType_CardInfo lgtci = new ListType_CardInfo();
			lgtci.setRow(rtcia);
			
			cardsListInfo = new ListType_CardInfoHolder(lgtci);

			orInfo = pp.newAgreement(connectionInfo, agreementInfo, accountsListInfo, cardsListInfo);
			
			System.out.println("Tieto response:"+orInfo.getResponse_code().intValue()+"||agre response:"+agreementInfo.value+"||");
			
			 if(orInfo.getResponse_code().intValue()!=0){
				 alert(orInfo.getError_action()+"\r\n"+orInfo.getError_description());
			 }
			if(agreementInfo.value!=null){
			 agrnum =  agreementInfo.value.getAGRE_NOM();
			 System.out.println("AGRE_NOM : "+  agreementInfo.value.getAGRE_NOM());
			} 
			
		} catch (Exception e) {
			alert(e.getMessage());
			com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
		}*/
 }
 
 
 public void onClick$btn_cancel$addwnd() {
	 addwnd.setVisible(false);
	 
 }
 
 
 
 
}
