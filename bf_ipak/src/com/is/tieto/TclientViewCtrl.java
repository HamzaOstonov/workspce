package com.is.tieto;

import issuing_v_01_02_xsd.KeyType_Agreement;
//import issuing_v_01_02_xsd.ListType_AccountInfo;
//import issuing_v_01_02_xsd.ListType_CardInfo;
import issuing_v_01_02_xsd.OperationConnectionInfo;
import issuing_v_01_02_xsd.OperationResponseInfo;
import issuing_v_01_02_xsd.RowType_AccountInfo;
import issuing_v_01_02_xsd.RowType_AccountInfo_Additional;
import issuing_v_01_02_xsd.RowType_AccountInfo_Base;
import issuing_v_01_02_xsd.RowType_AddCardToStopList_Request;
import issuing_v_01_02_xsd.RowType_Agreement;
import issuing_v_01_02_xsd.RowType_CardInfo;
import issuing_v_01_02_xsd.RowType_CardInfo_EMV_Data;
import issuing_v_01_02_xsd.RowType_CardInfo_LogicalCard;
import issuing_v_01_02_xsd.RowType_CardInfo_PhysicalCard;
import issuing_v_01_02_xsd.RowType_ExecTransaction_Request;
import issuing_v_01_02_xsd.RowType_RemoveCardFromStop_Request;
import issuing_v_01_02_xsd.holders.ListType_AccountInfoHolder;
import issuing_v_01_02_xsd.holders.ListType_CardInfoHolder;
import issuing_v_01_02_xsd.holders.OperationResponseInfoHolder;
import issuing_v_01_02_xsd.holders.RowType_AgreementHolder;
import issuing_v_01_02_xsd.holders.RowType_ExecTransaction_ResponseHolder;
import static com.programmisty.numerals.Numerals.*;

//import com.fasterxml.jackson.databind.ObjectMapper;
import com.is.ConnectionPool;
import com.is.LtLogger;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.type.OrientationEnum;

import org.zkoss.util.media.AMedia;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zkplus.databind.BindingListModelList;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Decimalbox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Label;
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

import IssuingWS.IssuingPortProxy;

import com.is.ConnectionPool;
import com.is.accountti.AccountService;
import com.is.customer.Customer;
import com.is.customer.CustomerService;
import com.is.customer.CustomerService.link;
import com.is.customer.TietoCustomer;
import com.is.report.DPrint;
import com.is.trpayti.Ti_Duplicate;
import com.is.trpayti.Ti_DuplicateDebit;
import com.is.trpayti.Ti_Duplicate_Other;
import com.is.trpayti.State;
import com.is.trpayti.TrPay;
import com.is.trpayti.TrPayFilter;
import com.is.trpayti.TrPayService;
import com.is.trpayti.TrPayViewCtrl.Cur_cont;
import com.is.userti.Action;
import com.is.userti.UserActionsLog;
import com.is.userti.UserService;
import com.is.utilsti.CheckNull;
import com.is.utilsti.RefCBox;
import com.is.utilsti.RefData;
import com.is.utilsti.Res;
import com.lowagie.text.xml.xmp.DublinCoreSchema;

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
    private Toolbarbutton paywnd$fbt, paywnd$lock, paywnd$application; 
    private Toolbar paywnd$pay_tlb;
    //private Toolbar tb;
    private Textbox client,bank_c,client_b,cl_type,cln_cat,rec_date,f_names,surname,title,m_name,b_date,r_street,r_city,r_cntry,usrid,ctime,status,search_name,sex,serial_no,doc_since,issued_by,status_change_date,blockwnd$txtstopcauses,paywnd$search_name,paywnd$address;
    private Textbox aclient,abank_c,aclient_b,acl_type,acln_cat,arec_date,af_names,asurname,atitle,am_name,ab_date,ar_street,ar_city,ar_cntry,ausrid,actime,astatus,asearch_name,asex,aserial_no,adoc_since,aissued_by,astatus_change_date, paywnd$curacc, paywnd$curr_acc_uzs;
    private Textbox fclient,fbank_c,fclient_b,fcl_type,fcln_cat,ff_names,fsurname,ftitle,fm_name,fr_street,fr_city,fr_cntry,fusrid,fctime,fstatus,fsearch_name,fsex,fserial_no,fdoc_since,fissued_by,fstatus_change_date ,fcard, paywnd$inc_ord_num;
    private Paging tclientPaging;
    private Datebox fb_date,frec_date;
    private RefCBox paywnd$scurracc,blockwnd$sstopcauses,addwnd$sproduct, paywnd$curracc_uzs;
    private  int _pageSize = 5;
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
    private SimpleDateFormat df = new SimpleDateFormat("dd.MMMM.yyyy", new Locale("ru"));
    private SimpleDateFormat df2 = new SimpleDateFormat("dd.MMMM.yyyy hh:mm:ss");
    private DecimalFormat dmf = new DecimalFormat("0.00##");
    private IssuingPortProxy pp = null;
    private static HashMap< String,String> _tstopCauses = null;

    private int pay_card_doc_id;
    private String curip, cur_act;
    private NumberFormat nf = NumberFormat.getInstance();
    String[] course = new String[2];
    private static HashMap<String, String> bank_name = new HashMap<String, String>();
    private static String user_name = null;
    
    
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
    nf.setGroupingUsed(false);
    un = (String) session.getAttribute("un");
    pwd= (String) session.getAttribute("pwd");
    uid= (Integer) session.getAttribute("uid");
    branch= (String) session.getAttribute("branch");
    alias = (String) session.getAttribute("alias");
    curip = (String) session.getAttribute("curip");
    _tstopCauses = com.is.utilsti.RefDataService.getHTstopCauses();

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

    pp = new IssuingPortProxy(
			ConnectionPool.getValue("TIETO_HOST", alias),
			ConnectionPool.getValue("TIETO_HOST_USERNAME", alias),
			ConnectionPool.getValue("TIETO_HOST_PASSWORD", alias)
			);

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
    
    addwnd$sproduct.setModel((new ListModelList(com.is.utilsti.RefDataService.getOfrProd(alias))));


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
    e.printStackTrace();
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
		 System.out.println("current.getClient(): "+ current.getClient());
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
	 
	/* if(accinfo.getTranz_acct().substring(17, 18).equals("9"))
	 {
		paywnd$curacc.setVisible(false);
		paywnd$curacc.setValue("00000000000000000000");
	 }
	 else
	 {
	 	paywnd$curacc.setVisible(true);
	 }*/
	 paywnd$curacc.setValue(lnk.Cur_acc);
	 
	 TrPayFilter trf = new TrPayFilter();
	 trf.setCard_acc(accinfo.getTranz_acct());
	 //if(!CheckNull.isEmpty(accinfo.getTranz_acct())){
	 if(!CheckNull.isEmpty(accinfo.getClient())){

		 //if (accinfo.getTranz_acct()==null)
		 //{
		 //		LtLogger.getLogger().error("Tranz Acc is null for: "+ accinfo.getClient());
		 //		System.out.println("Tranz Acc is null for: "+ accinfo.getClient());
		 //}
		 
		 TietoCustomer tc = CustomerService.getTietoCustomer(accinfo.getClient(), branch, alias);
	     //paywnd$scurracc.setModel((new ListModelList(com.is.utilsti.RefDataService.getCurrAcc(tc.getBank_customer_id(),alias))));
	     paywnd$payGrid.setModel(new ListModelList(TrPayService.getTrPaysFl(0, 3, trf, alias, true)));
	     //paywnd$add_currency_type.setValue(com.is.labels.doc.summa + accinfo.getCcy());
	     String alias_tmp = TrPayService.get_alias(branch, alias);
		 //paywnd$curracc_uzs.setModel((new ListModelList(com.is.utilsti.RefDataService.getCurrAccUZS(branch, tc.getBank_customer_id(), alias_tmp))));
	     List<RefData> lll = com.is.utilsti.RefDataService.getCurrAccUZS(branch, tc.getBank_customer_id(), alias_tmp);
	     if (lll.size()!=0)
	     {
	    	paywnd$curr_acc_uzs.setValue(lll.get(0).getData());
	    	//LtLogger.getLogger().info("not err account :="+lll.get(0).getData());
	     }
	 }
	 
	 List<Action> actions;
		actions = TclientService.getActions(uid, branch, alias);
		paywnd$pay_tlb.getChildren().clear();
	    for (int i=0;i<actions.size();i++)
	    {
	    	paywnd$fbt = new Toolbarbutton();
	    	paywnd$fbt.setLabel(actions.get(i).getName());
	    	paywnd$fbt.setImage(actions.get(i).getIcon());
	    	paywnd$fbt.setAttribute("act_desc", actions.get(i).getName());//Пополнить карту ОПЕРАТОР (Пополнение ФИЛИАЛ)
	    	//paywnd$fbt.setAttribute("state_id", states.get(i).getId());
	    	paywnd$fbt.setAttribute("deal_id", actions.get(i).getDeal_id());
	    	paywnd$fbt.setAttribute("act_id", actions.get(i).getId());
	    	paywnd$fbt.setAttribute("rep_type_id", actions.get(i).getRep_type_id());
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
	    
	 paywnd$search_name.setValue(current.getSearch_name());
	 paywnd$address.setValue(current.getR_street());
	 paywnd$search_name.setReadonly(true);
	 paywnd$address.setReadonly(true);
	 paywnd$lock.setImage("/images/locked.png");
	 paywnd.setVisible(true);
	// paywnd$btn_block.setVisible( accinfo.getStatus1().equals("0"));
	// paywnd$btn_unblock.setVisible( !paywnd$btn_block.isVisible());
 }
 
 public void onClick$btn_cancel$paywnd(){
	 paywnd$amnt.setValue(BigDecimal.ZERO);
	 paywnd.setVisible(false);
 }
 
 TrPay trp_res = null;
 
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
			trp.setAmount(Math.round((paywnd$amnt.getValue().doubleValue())*100));
			
			/*alert("AMOUNT at form:"+paywnd$amnt.getValue()+
					"\ndoubleamount:"+paywnd$amnt.getValue().doubleValue()+
					"\namount *100:"+trp.getAmount()+
					"\namount setparam:"+(long)trp.getAmount());*/
			
			trp.setCard_acc(accinfo.getTranz_acct());
			System.out.println(accinfo.getProduct()+": "+accinfo.getProduct_name()+": "+ accinfo.getTranz_acct());
			//LtLogger.getLogger().info("not err product :="+accinfo.getProduct()+": "+accinfo.getProduct_name()+": "+ accinfo.getTranz_acct());
			trp.setCur_acc(paywnd$curacc.getValue());
			trp.setCl_name(current.getSearch_name());
			trp.setIn_name(paywnd$search_name.getValue());
			trp.setBranch(branch);
			trp.setOperation_id(2);
			trp.setPan(accinfo.getCard());
			trp.setEmp_id(uid);
			trp.setDoc_num(paywnd$inc_ord_num.getValue());
			trp.setIn_address(paywnd$address.getValue());
			//trp.setCard_acc("22618840999177381001");
			//trp.setCur_acc("20206840299177381001");
			trp.setCur_acc_uzs(paywnd$curr_acc_uzs.getValue());

			//String str1 = "";
			//ObjectMapper objectMapper = new ObjectMapper();

            //try {
	           //str1 = objectMapper.writerWithDefaultPrettyPrinter()
			   //.writeValueAsString(trp);
               //} catch (Exception e22) {
	           //str1 = " str1=error trp";
               //} finally {
            //}
            //LtLogger.getLogger().info(
		        //"** not err trp  ************** " + str1);
            //LtLogger.getLogger().info(
		    //   "** not err " + "deal_id:="+deal_id+", rep_type_id:="+rep_type_id+", act_id:="+act_id);
            System.out.println("deal_id:="+deal_id+", rep_type_id:="+rep_type_id+", act_id:="+act_id);
            LtLogger.getLogger().error("deal_id:="+deal_id+", rep_type_id:="+rep_type_id+", act_id:="+act_id);
			LtLogger.getLogger().info("Versiya_1");
			Res rs = TrPayService.doAction(un, pwd, trp, act_id, deal_id, alias);

			if (rs.getCode()<0){
				LtLogger.getLogger().error("alert:11111");
				alert(rs.getName());
			}else {
				 trp_res = TrPayService.getTrPay(Integer.parseInt(rs.getName()), alias);
				trp_res.setCl_name(current.getSearch_name());
				trp_res.setIn_name(paywnd$search_name.getValue());
				trp_res.setIn_address(paywnd$address.getValue());
				if (rep_type_id != 0 && (!(deal_id==1 && (act_id==20 || act_id==21 || act_id==80 || act_id==90 || act_id==104)))) 
				{
					LtLogger.getLogger().error("alert:22222");
					printp(trp_res, TclientService.get_report_file(deal_id, act_id, alias),deal_id, act_id);
				}else if(rep_type_id != 0 && deal_id==1 && (act_id==20 || act_id==21 || act_id==80)) {
					LtLogger.getLogger().error("alert:33333");
					printp2(trp, TclientService.get_report_file(deal_id, act_id, alias),deal_id, act_id);
				}else if(rep_type_id != 0 && deal_id==1 && (act_id==90 || act_id==104)) {
					LtLogger.getLogger().error("alert:44444");
					printp3(trp, TclientService.get_report_file(deal_id, act_id, alias),deal_id, act_id);
				}else if(rep_type_id == 0 && deal_id==2 && (act_id==20 || act_id==85)) {
					LtLogger.getLogger().error("20--85");
					printp2(trp, TclientService.get_report_file(deal_id, act_id, alias),deal_id, act_id);
				}else if(rep_type_id == 0 && deal_id==2 && (act_id==97 || act_id==111)) {
					LtLogger.getLogger().error("97--111");
					printp3(trp, TclientService.get_report_file(deal_id, act_id, alias),deal_id, act_id);
				}
				
				String oper_desc = TrPayService.getOperation_desc(trp_res.getOperation_id(),alias);
				
				String log = "Операция ["+oper_desc+"] id ["+trp_res.getId()+"] действие ["+cur_act+"] подгруппы ["+TrPayService.getDeal_desc(deal_id, alias)+"] No карты ["+trp_res.getPan()+"] счет карты ["+trp_res.getCard_acc()+"] " +
						"(Отправитель ["+paywnd$search_name.getValue()+"] Адрес ["+paywnd$address.getValue()+"])";
				UserService.UsrLog(new UserActionsLog(uid, un, curip, 6, 1, log, branch));

				pay_card_doc_id = Integer.parseInt(rs.getName());
				LtLogger.getLogger().error("new document for s pay id:"+pay_card_doc_id);
				LtLogger.getLogger().error("deal_id="+deal_id+", act_id="+act_id+", uid="+uid+", un="+un+", curip="+curip+", branch="+branch+", log="+log);
			}
			
		} catch (Exception e) {
			alert(e.getMessage());
			LtLogger.getLogger().error("__________________PAY_ERROR(Tclient_VC s 550):\n"+CheckNull.getPstr(e));
			e.printStackTrace();
		}
	 
	 paywnd.setVisible(false);
	 onSelect$dataGrid();
 }
 
 /*public void onClick$btn_pay$paywnd(){
	 
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
			e.printStackTrace();
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
				printp4(trp_res);
				
				pay_card_doc_id = Integer.parseInt(rs.getName());
				System.out.println("new document for card pay id:"+pay_card_doc_id);
			}
			
		} catch (Exception e) {
			alert(e.getMessage());
			e.printStackTrace();
		}
	 
	 paywnd.setVisible(false);
	 onSelect$dataGrid();
 }*/
 
 
 public void onClick$btn_block$paywnd(){
	 blockwnd$sstopcauses.setModel(new ListModelList(com.is.utilsti.RefDataService.getTstopCauses()));
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
			 UserService.UsrLog(new UserActionsLog(uid, un, curip, 6, 1, "Карта No ["+accinfo.getCard()+"] заблокирована", branch));
			
		} catch (Exception e) {
			alert(e.getMessage());
			e.printStackTrace();
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
			 UserService.UsrLog(new UserActionsLog(uid, un, curip, 6, 1, "Карта No ["+accinfo.getCard()+"] разблокирована", branch));	
		} catch (Exception e) {
			alert(e.getMessage());
			e.printStackTrace();
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
 
 
 public void printp(TrPay trp, String report_file,int deal_id, int act_id){
	 
	 int count_seq3 = TclientService.getDuplicateOtherMaxId(alias)+1;
	 System.out.println("count_seq3 = "+count_seq3);
	 LtLogger.getLogger().error("count_seq3 = "+count_seq3);
	 
	 Ti_Duplicate_Other duplicate_other = null;
	 duplicate_other = new Ti_Duplicate_Other(count_seq3, branch, report_file, TclientService.getUser_name(uid, branch, alias), trp.getBranch(),
			 trp.getIn_name(), df2.format(new Date()), deal_id, act_id);
	 
	 LtLogger.getLogger().error("jdem...1");
	 
	 TclientService.createDuplicateOther(duplicate_other, alias);
	 LtLogger.getLogger().error("jdem...2");
	 printwnd.setVisible(true);
	 LtLogger.getLogger().error("jdem...3");
	    AMedia repmd = null;
	    
	    HashMap<String, Object> params = new HashMap<String, Object>();
	    params.put("TR_PAY_ID", trp.getId());
	    params.put("V_DATE", df.format(trp.getDate_created()));
	    
	    params.put("CLIENT_NAME1", trp.getCl_name());
	    params.put("CLIENT_NAME2", "");
	    params.put("CLIENT_NAME3", "");
	    LtLogger.getLogger().error("jdem...4");
	    params.put("INCLIENT_NAME1", trp.getIn_name());
	    params.put("INCLIENT_NAME2", "");
	    params.put("INCLIENT_NAME3", "");
	    params.put("T_CURRENCY", "USD");
	    params.put("TDP_NUM", trp.getDoc_num());
	    params.put("POST_ADDRESS", trp.getIn_address());
	    params.put("SUMMA6", Double.toString(trp.getAmount() / 100L));
	    params.put("ESUMMA6", nf.format(trp.getEqv_amount() / 100L));
	    params.put("PSUMMA6", com.is.utilsti.CheckNull.F2Money(trp.getAmount() / 100L, "доллар", "центов"));
		params.put("PESUMMA6", com.is.utilsti.CheckNull.F2Money(trp.getEqv_amount() / 100L, "сум", "тийин"));	    
	    params.put("TVEOPER", accinfo.getTranz_acct());
	    params.put("TVOPER", accinfo.getTranz_acct());
	    params.put("OPENDOPER", TclientService.get_Curr_acc(branch, alias));
	    params.put("ACCDOPER1", TclientService.getkass_acc(branch, alias));
	    params.put("BRANCH_NAME", (com.is.utilsti.RefDataService.getMfo_name(branch, alias).get(0)).getLabel());
	    
	    LtLogger.getLogger().error("jdem...5");
	    java.sql.Connection c = null;
	    try {
	    	LtLogger.getLogger().error("jdem...6");
	    	c = ConnectionPool.getConnection(alias);
	    	LtLogger.getLogger().error("jdem...61");
	    	LtLogger.getLogger().error("jdem...610:"+report_file);
	    	LtLogger.getLogger().error("jdem...611:"+application.getRealPath(report_file));
	      JasperPrint jasperPrint = net.sf.jasperreports.engine.JasperFillManager.fillReport(application.getRealPath(report_file), params, c);
	      LtLogger.getLogger().error("jdem...62");
	      jasperPrint.setLeftMargin(Integer.valueOf(0));
	      jasperPrint.setRightMargin(Integer.valueOf(0));
	      jasperPrint.setTopMargin(Integer.valueOf(0));
	      jasperPrint.setBottomMargin(Integer.valueOf(0));
	      jasperPrint.setPageHeight(595);
	      jasperPrint.setPageWidth(842);
	      jasperPrint.setOrientation(net.sf.jasperreports.engine.type.OrientationEnum.LANDSCAPE);
	      LtLogger.getLogger().error("jdem...63");
	      byte[] buf = net.sf.jasperreports.engine.JasperRunManager.runReportToPdf(application.getRealPath(report_file), params, c);
	      java.io.InputStream mediais = new java.io.ByteArrayInputStream(buf);
	      LtLogger.getLogger().error("jdem...64");	      
	      repmd = new org.zkoss.util.media.AMedia(current.getF_names() + "_" + current.getM_name() + ".pdf", "pdf", "application/pdf", mediais);
	      LtLogger.getLogger().error("jdem...7");
	    } catch (Exception e) {
	    	LtLogger.getLogger().error("jdem...8");
	      e.printStackTrace();
	      
	    }
	    
	    if (repmd != null) {
	    	LtLogger.getLogger().error("jdem...9");
	      printwnd$rpframe.setContent(repmd);
	    }
	    LtLogger.getLogger().error("jdem...10");
	 System.out.println("metod printp:");
	 System.out.println("trp.getId() = "+trp.getId());
	 System.out.println("trp.getBranch() = "+trp.getBranch());
	 System.out.println("trp.getOperation_id() = "+trp.getOperation_id());
	 System.out.println("trp.getAmount() = "+trp.getAmount());
	 System.out.println("trp.getCard_acc() = "+trp.getCard_acc());
	 System.out.println("trp.getCur_acc() = "+trp.getCur_acc());
	 System.out.println("trp.getDate_created() = "+trp.getDate_created());
	 System.out.println("trp.getParent_group_id() = "+trp.getParent_group_id());
	 System.out.println("trp.getState() = "+trp.getState());
	 System.out.println("trp.getAccount_no() = "+trp.getAccount_no());
	 System.out.println("trp.getCl_name() = "+trp.getCl_name());
	 System.out.println("trp.getEmp_id() = "+trp.getEmp_id());
	 System.out.println("trp.getPan() = "+trp.getPan());
	 System.out.println("trp.getDeal_id() = "+trp.getDeal_id());
	 System.out.println("trp.getDoc_num() = "+trp.getDoc_num());
	 System.out.println("trp.getEqv_amount() = "+trp.getEqv_amount());
	 System.out.println("trp.getOperation() = "+trp.getOperation());
	 System.out.println("trp.getTieto_type() = "+trp.getTieto_type());
	 System.out.println("trp.getAmount_t() = "+trp.getAmount_t());
	 System.out.println("trp.getUid() = "+trp.getUid());
	 System.out.println("trp.getIn_address() = "+trp.getIn_address());
	 System.out.println("trp.getIn_name() = "+trp.getIn_name());
	 System.out.println("getUser_name() = "+TclientService.getUser_name(uid, branch, alias));
	 System.out.println("uid, branch, alias = "+uid+" "+branch+" "+alias);
//	 System.out.println("replaceAll = "+trp.getPan().replaceAll(str, "-"));

	 LtLogger.getLogger().error("jdem...11");
		
 }

 
 public void onClick$btn_cancel$printwnd(){
	 printwnd.setVisible(false); 
 }
/* public void onClick$btn_print$printwnd() {
		Clients.evalJavaScript("window.frames[1].focus(); window.frames[1].print();");
		printwnd.setVisible(false);
	}*/
/* public void onClick$btn_open(){
	 
	 String client_id;
	 client_id = TclientService.check_user(alias, branch, current.getClient());
	 if (client_id=="")
	 {
		 alert("Клиент не объединен");
		 return;
	 }
	 Customer cs;
	 cs = com.is.customer.CustomerService.getCustomerById(client_id, alias);
	 addwnd$sproduct.setModel((new ListModelList(com.is.utilsti.RefDataService.getOfrProd(alias, cs.getCode_subject()))));
	 addwnd.setVisible(true);
 }*/
 
 public void onClick$btn_add$addwnd() {
	 
	// if(!com.is.tieto.TclientService.check_card(addwnd$sproduct.getValue(),TclientService.getAccInfo(current.getClient()), alias))
	// {
	//	 alert("Несовместимые типы карт");
	//	 return;
	// } 
	 
	    TietoCardSetting tcs =  TclientService.getTietoCardSetting(Integer.parseInt(addwnd$sproduct.getValue()), alias);
	    TietoCustomer tc;
	    tc = com.is.customer.CustomerService.getTietoCustomer(current.getClient(), branch, alias);
/*		 String tranzacc = AccountService.getCardAcc(un, pwd, "22618",current.getClient_b(), "840", tcs.getId_order_account(), current.getF_names()+" "+current.getM_name(), alias, ) ;
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
			e.printStackTrace();
		}*/
 }
 
 
 public void onClick$btn_cancel$addwnd() {
	 addwnd.setVisible(false);
	 
 }
 
 public void onClick$application$paywnd()
 {
	 printwnd.setVisible(true); 
		// printwnd$btn_print.setVisible(false);
		 
		 JasperPrint jasperPrint;
		 AMedia repmd = null;
		
		 HashMap< String,Object> params =  new HashMap<String,Object>();
		 Date date = new Date();
		 params.put("DATE", df.format(date));
		
		 params.put("CLIENT_NAME", paywnd$search_name.getValue());
		 params.put("BRANCH_NAME", com.is.utilsti.RefDataService.getMfo_name(branch, alias).get(0).getLabel());
		 params.put("TRANS_ACC", paywnd$curacc.getValue());
		 params.put("TRANS_ACC_NAME", AccountService.get_account(
				 paywnd$curacc.getValue(), branch, alias).getName());
		 params.put("MFO", branch);
		 params.put("CARD_ACC_NAME", AccountService.get_account(
				 accinfo.getTranz_acct(),
				 ConnectionPool.getValue("HO", alias),
				 ConnectionPool.getValue("HO_ALIAS", alias)).getName());
		 params.put("CARD_ACC", accinfo.getTranz_acct());
		 params.put("AMOUNT", paywnd$amnt.getValue().toString());
		 params.put("AMOUNT_TEXT", russian(paywnd$amnt.getValue().intValue()));
		 params.put("CARD_NAME", TclientService.get_tieto_card_name(accinfo.getCard(),alias));
		 
			Connection c = null;
	        try{
	        	c = ConnectionPool.getConnection(alias);
	        	jasperPrint = JasperFillManager.fillReport(application.getRealPath( "reports/TI_APPL_UPAMOUNT.jasper"),  params, c);
				jasperPrint.setLeftMargin(0);
				jasperPrint.setRightMargin(0);
				jasperPrint.setTopMargin(0);
				jasperPrint.setBottomMargin(0);
				jasperPrint.setPageHeight(595);//(461);//
				jasperPrint.setPageWidth(842);//(652);//
				jasperPrint.setOrientation(OrientationEnum.LANDSCAPE);  	       
				final byte[] buf = JasperRunManager.runReportToPdf(application.getRealPath( "reports/TI_APPL_UPAMOUNT.jasper"), params, c);
				final java.io.InputStream mediais = new ByteArrayInputStream(buf);
	  	       
				repmd = new AMedia(current.getF_names()+"_"+current.getM_name()+".pdf", "pdf", "application/pdf", mediais);
	        }catch(Exception e) {
	        	e.printStackTrace();
	        }	 
		 
		 if (repmd!=null){
		    printwnd$rpframe.setContent(repmd);
		 
		 }
 }
 
 public void onClick$lock$paywnd()
 {
	 if (paywnd$lock.getImage().compareTo("/images/opened.png")==0)
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
 
 public void onClick$btn_cancel2$paywnd(){

	 System.out.println("uid = "+uid);
	 System.out.println("un = "+un);
	 System.out.println("branch = "+branch);
	 System.out.println("pwd = "+pwd);
	 System.out.println("alias = "+alias);
	 System.out.println("curip = "+curip);
	 System.out.println("TclientService.getUser_name = "+TclientService.getUser_name(uid, branch, alias));
	 
//	 printp(trp_res, TclientService.get_report_file(1, 20, alias));	 
	 
//	 paywnd$amnt.setValue(BigDecimal.ZERO);
//	 paywnd.setVisible(false);
 }


public void printp2(TrPay trp, String report_file, int deal_id, int act_id){
	 
	 bank_name.put("00421", "Управляющему М.Улугбекского филиала АИКБ «Ипак Йўли»");
	 bank_name.put("01145", "Управляющему Шайхантохурского филиала АИКБ «Ипак Йўли»");
	 bank_name.put("00283", "Управляющему Умаровского филиала АИКБ «Ипак Йўли»");
	 bank_name.put("00999", "Управляющему Урикзарского филиала АИКБ «Ипак Йўли»");
	 bank_name.put("01028", "Управляющему Яккасарайского филиала АИКБ «Ипак Йўли»");
	 bank_name.put("01036", "Управляющему Сагбанского филиала АИКБ «Ипак Йўли»");
	 bank_name.put("01081", "Управляющему Янгийульского филиала АИКБ «Ипак Йўли»");
	 bank_name.put("01101", "Управляющему Мирабадского филиала АИКБ «Ипак Йўли»");
	 bank_name.put("00199", "Управляющему Наваинского филиала АИКБ «Ипак Йўли»");
	 bank_name.put("01048", "Управляющему Шахрисабзского филиала АИКБ «Ипак Йўли»");
	 bank_name.put("01054", "Управляющему Наманганского филиала АИКБ «Ипак Йўли»");
	 bank_name.put("01067", "Управляющему Чиланзарского филиала АИКБ «Ипак Йўли»");
	 bank_name.put("01119", "Управляющему филиала Карши АИКБ «Ипак Йўли»");
	 bank_name.put("01120", "Управляющему Андижанского филиала АИКБ «Ипак Йўли»");
	
	 String filial_name = "";
	 
	 if(trp.getBranch().equals("00444")) {
		 filial_name = "Начальнику Операционного Управления АИКБ «Ипак Йўли»";
	 }else {
	 for(String key : bank_name.keySet()) {
	 if(trp.getBranch().equals(key)) {
		 filial_name = bank_name.get(key);
	 }
	 }
	 }
	 System.out.println("trp.getBranch = "+trp.getBranch());
	 System.out.println("filial_name = "+filial_name);
	 
	 course = TclientService.getCourse(alias);
	 Double esumma6;
	 if(report_file.equals("reports/TI_KREDIT_OPERATOR.jasper")) {
		 esumma6 =  ((trp.getAmount()/100.0)*(Double.parseDouble(course[0])/100));	 
	 }else {
		 esumma6 =  ((trp.getAmount()/100.0)*(Double.parseDouble(course[1])/100));
		 //esumma6 = (double) (Double.parseDouble(trp.getAmount()+"")/100*Double.parseDouble(course[1]));
	 }
	 
	 String str = trp.getPan().substring(6, 12);
	 
	 printwnd.setVisible(true); 
	// printwnd$btn_print.setVisible(false);
	 
//	 String seq = "0";//TclientService.SeqTieto(alias);
	 int count_seq = 0;
	 Ti_Duplicate duplicate = null;
	 duplicate = new Ti_Duplicate(0, branch, filial_name, TclientService.getUser_name(uid, branch, alias),trp.getBranch(),
			 trp.getIn_name(),"","",df2.format(new Date()), trp.getIn_address(), esumma6+"", (trp.getAmount()/100.0)+"",
			 com.is.utilsti.CheckNull.F2Money(trp.getAmount()/100.0, "доллар", "центов"),
			 TclientService.F2MoneyUz(trp.getAmount()/100.0, "dollar", "sent"),
			 TclientService.F2MoneyUz(esumma6, "so'm", "tiyin"), trp.getPan().replaceAll(str, "-"), deal_id, act_id);
	 
	 /*if(trp.getBranch().equals("00444")) {
		 System.out.println("printp2+00444");
		 TclientService.createDuplicate(duplicate, alias);
		 count_seq = TclientService.getDuplicateMaxId(alias, trp.getBranch());
	 }else {*/
		 System.out.println("printp2+other");
		 TclientService.createDuplicateFilial(duplicate, alias);
		 count_seq = TclientService.getDuplicateFilialMaxId(alias, trp.getBranch());
	 /*}*/
	 
	 System.out.println("count_seq = "+count_seq);
	 System.out.println("(((double)trp.getAmount())/100) = "+(trp.getAmount()/100.0));
	 System.out.println("(Double.parseDouble(course[0])/100) = "+(Double.parseDouble(course[0])/100));
	 System.out.println("report_file = "+report_file);
	 System.out.println("esumma6 = "+esumma6);
	 System.out.println("course[0] = "+course[0]);
	 System.out.println("course[1] = "+course[1]);
	 
	 if(duplicate!=null) {
	 
	 JasperPrint jasperPrint;
	 AMedia repmd = null;
	//alert("account: "+accinfo.getTranz_acct());
	 HashMap< String,Object> params =  new HashMap<String,Object>();
//	 params.put("TR_PAY_ID", trp.getId());
	 params.put("V_DATE", df.format(new Date()));//df.format(trp.getDate_created()));
	 //params.put("CLIENT_NAME1", current.getF_names());
	 params.put("CLIENT_NAME1", trp.getCl_name());
	 params.put("CLIENT_NAME2", "");
	 params.put("CLIENT_NAME3", "");
	 //params.put("CLIENT_NAME2", current.getM_name());
	 //params.put("CLIENT_NAME3", current.getSurname());
	 params.put("INCLIENT_NAME1", trp.getIn_name());
	 params.put("INCLIENT_NAME2", "");
	 params.put("INCLIENT_NAME3", "");
	 params.put("T_CURRENCY", "USD");
	 params.put("TDP_NUM", trp.getDoc_num());
	 params.put("POST_ADDRESS", trp.getIn_address());
	 params.put("SUMMA6", (trp.getAmount()/100.0)+"");
	 params.put("ESUMMA6", String.format("%.2f%n", esumma6));
	 params.put("PSUMMA6", com.is.utilsti.CheckNull.F2Money(trp.getAmount()/100.0, "доллар", "центов"));
	 params.put("PESUMMA6", com.is.utilsti.CheckNull.F2Money(esumma6, "сум", "тийин"));
	 params.put("UZPSUMMA6", TclientService.F2MoneyUz(trp.getAmount()/100.0, "dollar", "sent"));
	 params.put("UZPESUMMA6", TclientService.F2MoneyUz(esumma6, "so'm", "tiyin"));
	 params.put("TVEOPER", accinfo.getTranz_acct());
	 params.put("TVOPER", accinfo.getTranz_acct());
	 params.put("OPENDOPER", TclientService.get_Curr_acc(branch, alias));
	 params.put("ACCDOPER1",com.is.tieto.TclientService.getkass_acc(branch, alias));
	 params.put("BRANCH_NAME", com.is.utilsti.RefDataService.getMfo_name(branch, alias).get(0).getLabel());
	 params.put("BANK_NAME", filial_name);
	 params.put("NAME2", TclientService.getUser_name(uid, branch, alias));
	 params.put("KARTA_NUM", trp.getPan().replaceAll(str, "-"));//621045CUTZQH6018
	 params.put("MY_BRANCH", trp.getBranch());//P_IMG_PATH
	 params.put("SEQ", SeqTieto(count_seq));

	 System.out.println("duplicate.getId() = "+duplicate.getId());
	 System.out.println("duplicate.getBranch() = "+duplicate.getBranch());
	 System.out.println("duplicate.getBank_name() = "+duplicate.getBank_name());
	 System.out.println("duplicate.getName2() = "+duplicate.getName2());
	 System.out.println("duplicate.getMy_branch() = "+duplicate.getMy_branch());
	 System.out.println("duplicate.getInclient_name1() = "+duplicate.getInclient_name1());
	 System.out.println("duplicate.getInclient_name2() = "+duplicate.getInclient_name2());
	 System.out.println("duplicate.getInclient_name3() = "+duplicate.getInclient_name3());
	 System.out.println("duplicate.getV_date() = "+duplicate.getV_date());
	 System.out.println("duplicate.getPost_address() = "+duplicate.getPost_address());
	 System.out.println("duplicate.getEsumma6() = "+duplicate.getEsumma6());
	 System.out.println("duplicate.getSumma6() = "+duplicate.getSumma6());
	 System.out.println("duplicate.getPsumma6() = "+duplicate.getPsumma6());
	 System.out.println("duplicate.getUzpsumma6() = "+duplicate.getUzpsumma6());
	 System.out.println("duplicate.getUzpesumma6() = "+duplicate.getUzpesumma6());
	 System.out.println("duplicate.getKarta_num() = "+duplicate.getKarta_num());
	 
	 
	 
//	 create sequence SEQ_TIETO_444
//	 minvalue 1
//	 maxvalue 9999999999
//	 start with 1
//	 increment by 1
//	 cache 2;
//	 SELECT SEQ_TIETO_444.NEXTVAL id FROM DUAL;
//	 --drop sequence SEQ_TIETO_444
	 
	 System.out.println("metod printp:");
	 System.out.println("trp.getId() = "+trp.getId());
	 System.out.println("trp.getBranch() = "+trp.getBranch());
	 System.out.println("trp.getOperation_id() = "+trp.getOperation_id());
	 System.out.println("trp.getAmount() = "+trp.getAmount()+"  "+Double.parseDouble(trp.getAmount()+""));
	 System.out.println("trp.getAmount2() = "+trp.getAmount()+"  "+(double)trp.getAmount());
	 System.out.println("trp.getCard_acc() = "+trp.getCard_acc());
	 System.out.println("trp.getCur_acc() = "+trp.getCur_acc());
	 System.out.println("trp.getDate_created() = "+trp.getDate_created());
	 System.out.println("trp.getParent_group_id() = "+trp.getParent_group_id());
	 System.out.println("trp.getState() = "+trp.getState());
	 System.out.println("trp.getAccount_no() = "+trp.getAccount_no());
	 System.out.println("trp.getCl_name() = "+trp.getCl_name());
	 System.out.println("trp.getEmp_id() = "+trp.getEmp_id());
	 System.out.println("trp.getPan() = "+trp.getPan());
	 System.out.println("trp.getDeal_id() = "+trp.getDeal_id());
	 System.out.println("trp.getDoc_num() = "+trp.getDoc_num());
	 System.out.println("trp.getEqv_amount() = "+trp.getEqv_amount());
	 System.out.println("trp.getOperation() = "+trp.getOperation());
	 System.out.println("trp.getTieto_type() = "+trp.getTieto_type());
	 System.out.println("trp.getAmount_t() = "+trp.getAmount_t());
	 System.out.println("trp.getUid() = "+trp.getUid());
	 System.out.println("trp.getIn_address() = "+trp.getIn_address());
	 System.out.println("trp.getIn_name() = "+trp.getIn_name());
	 System.out.println("getUser_name() = "+TclientService.getUser_name(uid, branch, alias));
	 System.out.println("uid, branch, alias = "+uid+" "+branch+" "+alias);
	 System.out.println("replaceAll = "+trp.getPan().replaceAll(str, "-"));

   
		Connection c = null;
        try{
        	c = ConnectionPool.getConnection(alias);
        	jasperPrint = JasperFillManager.fillReport(application.getRealPath( report_file),  params, c);
			jasperPrint.setLeftMargin(0);
			jasperPrint.setRightMargin(0);
			jasperPrint.setTopMargin(0);
			jasperPrint.setBottomMargin(0);
			jasperPrint.setPageHeight(595);//(461);//
			jasperPrint.setPageWidth(842);//(652);//
			jasperPrint.setOrientation(OrientationEnum.LANDSCAPE);  	       
			final byte[] buf = JasperRunManager.runReportToPdf(application.getRealPath( report_file), params, c);
			final java.io.InputStream mediais = new ByteArrayInputStream(buf);
  	       
			repmd = new AMedia(current.getF_names()+"_"+current.getM_name()+".pdf", "pdf", "application/pdf", mediais);
        }catch(Exception e) {
        	e.printStackTrace();
        }	 
	 
	 if (repmd!=null){
	    printwnd$rpframe.setContent(repmd);
	 
	 }
	 }else {
		 alert("Проверьте данные!");
	 }
 }

@SuppressWarnings("unused")
public void printp3(TrPay trp, String report_file, int deal_id, int act_id){
	 
	 bank_name.put("00421", "Управляющему М.Улугбекского филиала АИКБ «Ипак Йўли»");
	 bank_name.put("01145", "Управляющему Шайхантохурского филиала АИКБ «Ипак Йўли»");
	 bank_name.put("00283", "Управляющему Умаровского филиала АИКБ «Ипак Йўли»");
	 bank_name.put("00999", "Управляющему Урикзарского филиала АИКБ «Ипак Йўли»");
	 bank_name.put("01028", "Управляющему Яккасарайского филиала АИКБ «Ипак Йўли»");
	 bank_name.put("01036", "Управляющему Сагбанского филиала АИКБ «Ипак Йўли»");
	 bank_name.put("01081", "Управляющему Янгийульского филиала АИКБ «Ипак Йўли»");
	 bank_name.put("01101", "Управляющему Мирабадского филиала АИКБ «Ипак Йўли»");
	 bank_name.put("00199", "Управляющему Наваинского филиала АИКБ «Ипак Йўли»");
	 bank_name.put("01048", "Управляющему Шахрисабзского филиала АИКБ «Ипак Йўли»");
	 bank_name.put("01054", "Управляющему Наманганского филиала АИКБ «Ипак Йўли»");
	 bank_name.put("01067", "Управляющему Чиланзарского филиала АИКБ «Ипак Йўли»");
	 bank_name.put("01119", "Управляющему филиала Карши АИКБ «Ипак Йўли»");
	 bank_name.put("01120", "Управляющему Андижанского филиала АИКБ «Ипак Йўли»");
	 	 
	 String filial_name = "";
	 
	 if(trp.getBranch().equals("00444")) {
		 filial_name = "Начальнику Операционного Управления АИКБ «Ипак Йўли»";
	 }else {
	 for(String key : bank_name.keySet()) {
	 if(trp.getBranch().equals(key)) {
		 filial_name = bank_name.get(key);
	 }
	 }
	 }
	 System.out.println("trp.getBranch = "+trp.getBranch());
	 System.out.println("filial_name = "+filial_name);
	 
	 course = TclientService.getCourse(alias);
	 Double esumma6;
	 if(report_file.equals("reports/TI_KREDIT_OPERATOR.jasper")) {
		 esumma6 =  ((trp.getAmount()/100.0)*(Double.parseDouble(course[0])/100));
//		 esumma6 = (double) (Double.parseDouble(trp.getAmount()+"")/100*Double.parseDouble(course[0]));	 
	 }else {
		 esumma6 =  ((trp.getAmount()/100.0)*(Double.parseDouble(course[1])/100));
//		 esumma6 = (double) (Double.parseDouble(trp.getAmount()+"")/100*Double.parseDouble(course[1]));
	 }
	 
	 String str = trp.getPan().substring(6, 12);
	 
	 printwnd.setVisible(true); 
	// printwnd$btn_print.setVisible(false);
//	 String seq = "0";//TclientService.SeqTietoDebit(alias);
	 int count_seq2 = 0;
	 Ti_DuplicateDebit duplicate_debit = null;
	 duplicate_debit = new Ti_DuplicateDebit(0, branch, filial_name, TclientService.getUser_name(uid, branch, alias),trp.getBranch(),
			 trp.getIn_name(),"","",df2.format(new Date()), trp.getIn_address(), esumma6+"", Double.toString(trp.getAmount()/100.0),
			 com.is.utilsti.CheckNull.F2Money(trp.getAmount()/100.0, "доллар", "центов"),
			 TclientService.F2MoneyUz(trp.getAmount()/100.0, "dollar", "sent"),
			 TclientService.F2MoneyUz(esumma6, "so'm", "tiyin"), trp.getPan().replaceAll(str, "-"),deal_id, act_id);
	 
	 /*if(trp.getBranch().equals("00444")) {
		 System.out.println("printp3+00444");
		 TclientService.createDuplicateDebit(duplicate_debit, alias);
		 count_seq2 = TclientService.getDuplicateDebitMaxId(alias, trp.getBranch());
	 }else {*/
		 System.out.println("printp3+other");
		 TclientService.createDuplicateDebitFilial(duplicate_debit, alias);
		 count_seq2 = TclientService.getDuplicateDebitFilialMaxId(alias, trp.getBranch());
	 /*}*/
	 System.out.println("count_seq2 = "+count_seq2);
	 
	 System.out.println("report_file = "+report_file);
	 System.out.println("esumma6 = "+esumma6);
	 System.out.println("course[0] = "+course[0]);
	 System.out.println("course[1] = "+course[1]);
	 
	 //System.out.println("TclientService.getDuplicateDebitMaxId(alias) = "+TclientService.getDuplicateDebitMaxId(alias));
	 
	 if(duplicate_debit!=null) {
	 JasperPrint jasperPrint;
	 AMedia repmd = null;
	//alert("account: "+accinfo.getTranz_acct());
	 HashMap< String,Object> params =  new HashMap<String,Object>();
//	 params.put("TR_PAY_ID", trp.getId());
	 params.put("V_DATE", df.format(new Date()));//df.format(trp.getDate_created()));
	 //params.put("CLIENT_NAME1", current.getF_names());
	 params.put("CLIENT_NAME1", trp.getCl_name());
	 params.put("CLIENT_NAME2", "");
	 params.put("CLIENT_NAME3", "");
	 //params.put("CLIENT_NAME2", current.getM_name());
	 //params.put("CLIENT_NAME3", current.getSurname());
	 params.put("INCLIENT_NAME1", trp.getIn_name());
	 params.put("INCLIENT_NAME2", "");
	 params.put("INCLIENT_NAME3", "");
	 params.put("T_CURRENCY", "USD");
	 params.put("TDP_NUM", trp.getDoc_num());
	 params.put("POST_ADDRESS", trp.getIn_address());
	 params.put("SUMMA6", (trp.getAmount()/100.0)+"");
	 params.put("ESUMMA6", String.format("%.2f%n", esumma6));
	 params.put("PSUMMA6", com.is.utilsti.CheckNull.F2Money(trp.getAmount()/100.0, "доллар", "центов"));
//	 params.put("PESUMMA6", com.is.utilsti.CheckNull.F2Money(esumma6, "сум", "тийин"));
	 params.put("UZPSUMMA6", TclientService.F2MoneyUz(trp.getAmount()/100.0, "dollar", "sent"));
	 params.put("UZPESUMMA6", TclientService.F2MoneyUz(esumma6, "so'm", "tiyin"));
	 params.put("TVEOPER", accinfo.getTranz_acct());
	 params.put("TVOPER", accinfo.getTranz_acct());
	 params.put("OPENDOPER", TclientService.get_Curr_acc(branch, alias));
	 params.put("ACCDOPER1",com.is.tieto.TclientService.getkass_acc(branch, alias));
	 params.put("BRANCH_NAME", com.is.utilsti.RefDataService.getMfo_name(branch, alias).get(0).getLabel());
	 params.put("BANK_NAME", filial_name);
	 params.put("NAME2", TclientService.getUser_name(uid, branch, alias));
	 params.put("KARTA_NUM", trp.getPan().replaceAll(str, "-"));//621045CUTZQH6018
	 params.put("MY_BRANCH", trp.getBranch());//P_IMG_PATH
	 params.put("SEQ", SeqTieto(count_seq2));
	 
	 System.out.println("duplicate_debit.getId() = "+duplicate_debit.getId());
	 System.out.println("duplicate_debit.getBranch() = "+duplicate_debit.getBranch());
	 System.out.println("duplicate_debit.getBank_name() = "+duplicate_debit.getBank_name());
	 System.out.println("duplicate_debit.getName2() = "+duplicate_debit.getName2());
	 System.out.println("duplicate_debit.getMy_branch() = "+duplicate_debit.getMy_branch());
	 System.out.println("duplicate_debit.getInclient_name1() = "+duplicate_debit.getInclient_name1());
	 System.out.println("duplicate_debit.getInclient_name2() = "+duplicate_debit.getInclient_name2());
	 System.out.println("duplicate_debit.getInclient_name3() = "+duplicate_debit.getInclient_name3());
	 System.out.println("duplicate_debit.getV_date() = "+duplicate_debit.getV_date());
	 System.out.println("duplicate_debit.getPost_address() = "+duplicate_debit.getPost_address());
	 System.out.println("duplicate_debit.getEsumma6() = "+duplicate_debit.getEsumma6());
	 System.out.println("duplicate_debit.getSumma6() = "+duplicate_debit.getSumma6());
	 System.out.println("duplicate_debit.getPsumma6() = "+duplicate_debit.getPsumma6());
	 System.out.println("duplicate_debit.getUzpsumma6() = "+duplicate_debit.getUzpsumma6());
	 System.out.println("duplicate_debit.getUzpesumma6() = "+duplicate_debit.getUzpesumma6());
	 System.out.println("duplicate_debit.getKarta_num() = "+duplicate_debit.getKarta_num());
	 
//	 create sequence SEQ_TIETO_444
//	 minvalue 1
//	 maxvalue 9999999999
//	 start with 1
//	 increment by 1
//	 cache 2;
//	 SELECT SEQ_TIETO_444.NEXTVAL id FROM DUAL;
//	 --drop sequence SEQ_TIETO_444
	 
	 System.out.println("metod printp:");
	 System.out.println("trp.getId() = "+trp.getId());
	 System.out.println("trp.getBranch() = "+trp.getBranch());
	 System.out.println("trp.getOperation_id() = "+trp.getOperation_id());
	 System.out.println("trp.getAmount() = "+trp.getAmount()+"  "+Double.parseDouble(trp.getAmount()+""));
	 System.out.println("trp.getCard_acc() = "+trp.getCard_acc());
	 System.out.println("trp.getCur_acc() = "+trp.getCur_acc());
	 System.out.println("trp.getDate_created() = "+trp.getDate_created());
	 System.out.println("trp.getParent_group_id() = "+trp.getParent_group_id());
	 System.out.println("trp.getState() = "+trp.getState());
	 System.out.println("trp.getAccount_no() = "+trp.getAccount_no());
	 System.out.println("trp.getCl_name() = "+trp.getCl_name());
	 System.out.println("trp.getEmp_id() = "+trp.getEmp_id());
	 System.out.println("trp.getPan() = "+trp.getPan());
	 System.out.println("trp.getDeal_id() = "+trp.getDeal_id());
	 System.out.println("trp.getDoc_num() = "+trp.getDoc_num());
	 System.out.println("trp.getEqv_amount() = "+trp.getEqv_amount());
	 System.out.println("trp.getOperation() = "+trp.getOperation());
	 System.out.println("trp.getTieto_type() = "+trp.getTieto_type());
	 System.out.println("trp.getAmount_t() = "+trp.getAmount_t());
	 System.out.println("trp.getUid() = "+trp.getUid());
	 System.out.println("trp.getIn_address() = "+trp.getIn_address());
	 System.out.println("trp.getIn_name() = "+trp.getIn_name());
	 System.out.println("getUser_name() = "+TclientService.getUser_name(uid, branch, alias));
	 System.out.println("uid, branch, alias = "+uid+" "+branch+" "+alias);
	 System.out.println("replaceAll = "+trp.getPan().replaceAll(str, "-"));

  
		Connection c = null;
       try{
       	c = ConnectionPool.getConnection(alias);
       	jasperPrint = JasperFillManager.fillReport(application.getRealPath( report_file),  params, c);
			jasperPrint.setLeftMargin(0);
			jasperPrint.setRightMargin(0);
			jasperPrint.setTopMargin(0);
			jasperPrint.setBottomMargin(0);
			jasperPrint.setPageHeight(595);//(461);//
			jasperPrint.setPageWidth(842);//(652);//
			jasperPrint.setOrientation(OrientationEnum.LANDSCAPE);  	       
			final byte[] buf = JasperRunManager.runReportToPdf(application.getRealPath( report_file), params, c);
			final java.io.InputStream mediais = new ByteArrayInputStream(buf);
 	       
			repmd = new AMedia(current.getF_names()+"_"+current.getM_name()+".pdf", "pdf", "application/pdf", mediais);
       }catch(Exception e) {
       	e.printStackTrace();
       }	 
	 
	 if (repmd!=null){
	    printwnd$rpframe.setContent(repmd);
	 
	 }
	 }else {
		 alert("Проверьте данные!");
	 }
}
 
public static String SeqTieto(int count)  {
	 String seq = "";
    try {
            seq = count+"";
           	 StringBuffer s1 = new StringBuffer();
           		if(seq.length()<8) {
           			for (int i = 0; i < 8-seq.length(); i++) {
           				s1.append("0");
           			}
           			seq = s1.append(seq).toString();
           		} 
    } catch (Exception e) {
            e.printStackTrace();
    }
    return seq;
}

/*public void printp4(TrPay trp, String report_file)
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
  params.put("SUMMA6", Double.toString(trp.getAmount() / 100));
  params.put("ESUMMA6", nf.format(trp.getEqv_amount() / 100));
  params.put("PSUMMA6", com.is.utilsti.CheckNull.F2Money(trp.getAmount() / 100, "доллар", "центов"));
  params.put("TVEOPER", accinfo.getTranz_acct());
  params.put("OPENDOPER", trp.getCur_acc());
  params.put("ACCDOPER1", com.is.tieto_globuz.tieto.TclientService.getkass_acc(branch, alias));
  params.put("BRANCH_NAME", com.is.utilsti.RefDataService.getMfo_name(branch, alias).get(0).getLabel());
  
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
    
    repmd = new AMedia(current.getF_names() + "_" + current.getM_name() + ".pdf", "pdf", "application/pdf", mediais);
  }
  catch (Exception e)
  {
    e.printStackTrace();
  }
  
  if (repmd != null)
  {
    printwnd$rpframe.setContent(repmd);
    
  }
}*/

}
