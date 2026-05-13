package com.is.SwiftBuffer;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import net.sourceforge.wife.swift.model.SwiftMessage;
import net.sourceforge.wife.swift.parser.SwiftParser;

import org.zkoss.util.media.Media;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.InputEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zkplus.databind.BindingListModelList;
import org.zkoss.zul.Constraint;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Decimalbox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Longbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;
import org.zkoss.zul.event.PagingEvent;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.account.Account;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.RefData;
import com.is.utils.Res;
import com.prowidesoftware.swift.model.field.Field20;
import com.prowidesoftware.swift.model.field.Field23B;
import com.prowidesoftware.swift.model.field.Field32A;
import com.prowidesoftware.swift.model.field.Field50A;
import com.prowidesoftware.swift.model.field.Field50K;
import com.prowidesoftware.swift.model.field.Field59;
import com.prowidesoftware.swift.model.field.Field70;
import com.prowidesoftware.swift.model.field.Field71A;
import com.prowidesoftware.swift.model.mt.mt1xx.MT103;

public class SwiftBufferViewCtrl extends GenericForwardComposer{
    private Window bicwnd,chacc,ofacwnd;
    private Listbox bicwnd$lbic,chacc$acc,ofacwnd$ofac;
    private Textbox bicwnd$abic_to;
	private Div frm;
    private Listbox dataGrid;
    private Paging contactPaging;
    private Div grd;
    private Div frmgrd,addgrd,fgrd;
    private Grid addgrdl,addgrdr,frmgrdl,frmgrdr,fgrdl;
    private Toolbarbutton btn_last;
    private Toolbarbutton btn_next;
    private Toolbarbutton btn_prev;
    private Toolbarbutton btn_first;
    private Toolbarbutton btn_add,btn_savego,btn_valupr,btn_valuprc,btn_vnctrl,btn_vnctrlc;
    private Toolbarbutton btn_save,saveBic,btn_delete;
    private Toolbarbutton btn_back;
    private Toolbar tb;
    private Textbox branch,message_type,application_id,service_id,bic_from,bic_to,direction,reference,operation_code,order_party,ben_party,narrative,message_text,file_name,order_party_acc,ben_party_acc,chacc$order_party;
    private Textbox abranch,amessage_type,aapplication_id,aservice_id,abic_from,abic_to,adirection,areference,aoperation_code,aorder_party,aben_party,anarrative,amessage_text,afile_name,aorder_party_acc,aben_party_acc,acorr_acc;
    private Textbox fbranch,fmessage_type,fapplication_id,fservice_id,fbic_from,fbic_to,fdirection,freference,foperation_code,forder_party,fben_party,fnarrative,fmessage_text,ffile_name;
    private Decimalbox state,deal_id,parent_group_id,astate,adeal_id,aparent_group_id,fstate,fdeal_id,fparent_group_id;
    private Doublebox aamount,amount,famount;
    private Datebox value_date,avalue_date,fvalue_date,insert_date,ainsert_date,finsert_date;
    private Longbox id,aid,fid, parent_id,batch_id, aparent_id,abatch_id,fparent_id,fbatch_id;
    private RefCBox country_from,country_to,currency,acountry_from,acountry_to,acurrency,fcountry_from,fcountry_to,fcurrency,detailsofcharges,adetailsofcharges,corr_acc;
    private Paging swiftbufferPaging;
    private  int _pageSize = 15;
    private int _startPageNumber = 0;
    private int _totalSize = 0;
    private boolean _needsTotalSizeUpdate = true;
    private String alias;
    private Label abic_from_str,abic_to_str; 

    
    public SwiftBufferFilter filter = new SwiftBufferFilter();

    private PagingListModel model = null;
    private ListModelList lmodel =null;
    private AnnotateDataBinder binder;
    private SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
    private DecimalFormat nf = new DecimalFormat("0.00##");
    private RefData  currentbic = null;
    private SwiftBuffer current = new SwiftBuffer();
    private Account currentacc =new Account();
    private String un,pwd,branch1;
    private HashMap<String, String> hstate_ =null;
    private int typeid=0;

    public SwiftBufferViewCtrl() {
            super('$', false, false);
    }
/**
 *
 *
 */
    
private void prepareFilter(){
	switch(typeid) {
    case 1: 
    	btn_add.setVisible(true);
		btn_savego.setVisible(false);
		btn_save.setVisible(false);
		filter.setBranch(branch1);
		break;
	case 2: 
		saveBic.setVisible(true);
		btn_savego.setVisible(true);
		btn_save.setVisible(false);
		break;
	case 3: 
		btn_save.setVisible(true);
		btn_savego.setVisible(false);
		filter.setBranch(branch1);
		break;
	case 4: 
		btn_save.setVisible(false);
		btn_savego.setVisible(false);
		btn_valupr.setVisible(true);
		btn_valuprc.setVisible(true);
		filter.setState(21.0);
		break;
	case 5: 
		btn_save.setVisible(false);
		btn_savego.setVisible(false);
		btn_vnctrl.setVisible(true);
		btn_vnctrlc.setVisible(true);
		filter.setState(22.0);
		break;
		
	default: 
	   // оператор;
	    break;
}
	/*
	if(typeid==1){
		btn_savego.setVisible(false);
		btn_save.setVisible(false);
		filter.setBranch(branch1);
	
	}else if (typeid==2){
		btn_savego.setVisible(true);
		btn_save.setVisible(false);
		
	}else{
		btn_save.setVisible(true);
		btn_savego.setVisible(false);
		filter.setBranch(branch1);
	}
	*/
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
            _pageSize = Integer.parseInt( parameter[0])/26;
            dataGrid.setRows(Integer.parseInt( parameter[0])/26);
    }
    
    parameter = (String[]) param.get("typeid");
    if (parameter!=null){
          typeid =  Integer.parseInt( parameter[0]);
    }    
    
    un = (String) session.getAttribute("un");
    pwd= (String) session.getAttribute("pwd");

    alias = (String) session.getAttribute("alias");
    branch1 = (String) session.getAttribute("branch");
    
    hstate_ = SwiftBufferService.getHState(alias);

    bicwnd$lbic.setItemRenderer(new ListitemRenderer(){
        @SuppressWarnings("unchecked")
        public void render(Listitem row, Object data) throws Exception {
        	RefData pRefData = (RefData) data;
                    row.setValue(pRefData);
                    row.appendChild(new Listcell(pRefData.getData()));
                    row.appendChild(new Listcell(pRefData.getLabel()));

        }});
        dataGrid.setItemRenderer(new ListitemRenderer(){
    @SuppressWarnings("unchecked")
    public void render(Listitem row, Object data) throws Exception {
                SwiftBuffer pSwiftBuffer = (SwiftBuffer) data;

                row.setValue(pSwiftBuffer);
                
                row.appendChild(new Listcell(pSwiftBuffer.getBranch()));
                row.appendChild(new Listcell(pSwiftBuffer.getId()+""));
                row.appendChild(new Listcell(pSwiftBuffer.getMessage_type()));
                row.appendChild(new Listcell(pSwiftBuffer.getDirection()));
                //row.appendChild(new Listcell(pSwiftBuffer.getApplication_id()));
                //row.appendChild(new Listcell(pSwiftBuffer.getService_id()));
                row.appendChild(new Listcell(pSwiftBuffer.getBic_from()));
                row.appendChild(new Listcell(pSwiftBuffer.getBic_to()));
                
                row.appendChild(new Listcell(pSwiftBuffer.getCountry_from()));
                row.appendChild(new Listcell(pSwiftBuffer.getCountry_to()));
                row.appendChild(new Listcell(pSwiftBuffer.getValue_date()!=null?df.format(pSwiftBuffer.getValue_date()):"-----"));
                row.appendChild(new Listcell(nf.format(pSwiftBuffer.getAmount())));
                row.appendChild(new Listcell(pSwiftBuffer.getCurrency()));
                row.appendChild(new Listcell( hstate_.get(pSwiftBuffer.getState()+"")));
/*                
                row.appendChild(new Listcell(pSwiftBuffer.getReference()));
                row.appendChild(new Listcell(pSwiftBuffer.getOperation_code()));
                row.appendChild(new Listcell(pSwiftBuffer.getOrder_party()));
                row.appendChild(new Listcell(pSwiftBuffer.getBen_party()));
                row.appendChild(new Listcell(pSwiftBuffer.getNarrative()));
                row.appendChild(new Listcell(pSwiftBuffer.getMessage_text()));
                row.appendChild(new Listcell(pSwiftBuffer.getInsert_date()+""));
                
                row.appendChild(new Listcell(pSwiftBuffer.getDeal_id()+""));
                row.appendChild(new Listcell(pSwiftBuffer.getParent_group_id()+""));
                row.appendChild(new Listcell(pSwiftBuffer.getParent_id()+""));
                row.appendChild(new Listcell(pSwiftBuffer.getBatch_id()+""));
                row.appendChild(new Listcell(pSwiftBuffer.getFile_name()));
*/

    }});
        
        
        chacc$acc.setItemRenderer(new ListitemRenderer(){
            @SuppressWarnings("unchecked")
            public void render(Listitem row, Object data) throws Exception {
                Account pAccount = (Account) data;
                row.setValue(pAccount);
                row.appendChild(new Listcell(pAccount.getBranch()));
                row.appendChild(new Listcell(pAccount.getId()));
                row.appendChild(new Listcell(pAccount.getName()));

            }});
        ofacwnd$ofac.setItemRenderer(new ListitemRenderer(){
            @SuppressWarnings("unchecked")
            public void render(Listitem row, Object data) throws Exception {
                Ofac pOfac = (Ofac) data;
                row.setValue(pOfac);
                row.appendChild(new Listcell(pOfac.getProgramlist()));
                row.appendChild(new Listcell(pOfac.getFull_name()));
                row.appendChild(new Listcell(pOfac.getPerc()+"%"));
                row.appendChild(new Listcell(pOfac.getSdntype()));

            }});
        
        currency.setModel((new ListModelList(com.is.SwiftBuffer.SwiftBufferService.getCurrency(alias))));
        acurrency.setModel((new ListModelList(com.is.SwiftBuffer.SwiftBufferService.getCurrency(alias))));
        fcurrency.setModel((new ListModelList(com.is.SwiftBuffer.SwiftBufferService.getCurrency(alias))));
        country_from.setModel((new ListModelList(com.is.SwiftBuffer.SwiftBufferService.getCountry2(alias))));
        acountry_from.setModel((new ListModelList(com.is.SwiftBuffer.SwiftBufferService.getCountry2(alias))));
        fcountry_from.setModel((new ListModelList(com.is.SwiftBuffer.SwiftBufferService.getCountry2(alias))));
        country_to.setModel((new ListModelList(com.is.SwiftBuffer.SwiftBufferService.getCountry2(alias))));
        acountry_to.setModel((new ListModelList(com.is.SwiftBuffer.SwiftBufferService.getCountry2(alias))));
        fcountry_to.setModel((new ListModelList(com.is.SwiftBuffer.SwiftBufferService.getCountry2(alias))));
        
        adetailsofcharges.setModel((new ListModelList(com.is.SwiftBuffer.SwiftBufferService.getDetailsOfCharges(alias))));
        detailsofcharges.setModel((new ListModelList(com.is.SwiftBuffer.SwiftBufferService.getDetailsOfCharges(alias))));
        corr_acc.setModel((new ListModelList(com.is.SwiftBuffer.SwiftBufferService.getCorAcc(alias))));
        
    refreshModel(_startPageNumber);

}



public Account getCurrentacc() {
	return currentacc;
}
public void setCurrentacc(Account currentacc) {
	this.currentacc = currentacc;
}
public RefData getCurrentbic() {
	return currentbic;
}
public void setCurrentbic(RefData currentbic) {
	this.currentbic = currentbic;
}
public void onPaging$swiftbufferPaging(ForwardEvent event){
final PagingEvent pe = (PagingEvent) event.getOrigin();
_startPageNumber = pe.getActivePage();
refreshModel(_startPageNumber);
}


public void onClick$btn_refresh(){
	 refreshModel(_startPageNumber);
	
}

private void refreshModel(int activePage){
    swiftbufferPaging.setPageSize(_pageSize);
    prepareFilter();
model = new PagingListModel(activePage, _pageSize, filter, alias);

//f(_needsTotalSizeUpdate) {
        _totalSize = model.getTotalSize(filter, alias);
     //   _needsTotalSizeUpdate = false;
//}

swiftbufferPaging.setTotalSize(_totalSize);

dataGrid.setModel((ListModel) model);
if (model.getSize()>0){
	dataGrid.setSelectedIndex(0);
//this.current =(SwiftBuffer) model.getElementAt(0);
sendSelEvt();
}
}



//Omitted...
public SwiftBuffer getCurrent() {
return current;
}

public void setCurrent(SwiftBuffer current) {
this.current = current;
}

public void onDoubleClick$dataGrid$grd() {
            grd.setVisible(false);
            frm.setVisible(true);
            frmgrd.setVisible(true);
            addgrd.setVisible(false);
            fgrd.setVisible(false);
            btn_back.setImage("/images/folder.png");
            btn_back.setLabel(Labels.getLabel("grid"));
            sendSelEvt();
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
    if(current!=null){
       btn_save.setDisabled(!(current.getState()==1));
       btn_delete.setDisabled(!(current.getState()==1));
       btn_savego.setDisabled(!(current.getState()==2));
    }
}


public void onClick$btn_add() {
    onDoubleClick$dataGrid$grd();
    frmgrd.setVisible(false);
    addgrd.setVisible(true);
    fgrd.setVisible(false);
    CheckNull.clearForm(addgrdr);
    CheckNull.clearForm(addgrdl);
    
    
    abic_from.setValue(ConnectionPool.getValue("SWIFT_BIC", alias));
    abic_from_str.setValue(SwiftBufferService.getBicName(abic_from.getValue()));
    //adirection.setValue("I");
    acountry_from.setSelecteditem("UZ");
    aoperation_code.setValue("CRED");
    acountry_to.setFocus(true);
    acurrency.setSelecteditem("USD");
    
}

public void onClick$btn_search() {
    onDoubleClick$dataGrid$grd();
    frmgrd.setVisible(false);
    addgrd.setVisible(false);
    fgrd.setVisible(true);
}


public void onClick$btn_new(){
		List<Ofac> of = SwiftBufferService.getOfac(aben_party.getValue());
		if (of.size() > 0) {

			try {
				Messagebox.show("Платеж может попасть под санкции.\r\n"
						+ of.get(0).getProgramlist() + "  "
						+ of.get(0).getFull_name() + " " + of.get(0).getPerc()
						+ "%\r\n Вы уверены что хотите продолжить?",
						"OFAC Black List!!!!!!",
						Messagebox.YES | Messagebox.NO, Messagebox.QUESTION,
						new org.zkoss.zk.ui.event.EventListener() {
							public void onEvent(Event evt)
									throws InterruptedException {
								if (evt.getName().equals("onYes")) {
									action_btn_new();
								}
							}
						});
			} catch (Exception e) {
				alert(e.getMessage());
				com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
			}

		}else{
		action_btn_new();
		}

	}



public void action_btn_new(){
	Connection c = null;
	int actid = 1;
	try{
		
		c = ConnectionPool.getConnection(un, pwd, alias);

		
		
		SwiftBuffer sw = new SwiftBuffer();


		
          //sw.setBranch(branch.getValue());
          //sw.setId(id.getValue());
		sw.setMessage_type("103");//sw.setMessage_type(message_type.getValue());
          //sw.setApplication_id(application_id.getValue());
          //sw.setService_id(service_id.getValue());
		  System.out.println("abic_from "+abic_from.getValue());
		  System.out.println("areference "+areference.getValue());
          sw.setBic_from(abic_from.getValue());
          sw.setBic_to(abic_to.getValue());
          //sw.setDirection(adirection.getText());
          sw.setCountry_from(acountry_from.getValue());
          sw.setCountry_to(acountry_to.getValue());
          sw.setValue_date(avalue_date.getValue());
          sw.setAmount(aamount.doubleValue());
          sw.setCurrency(acurrency.getValue());
          sw.setReference(areference.getValue());
          sw.setOperation_code(aoperation_code.getValue());
          sw.setOrder_party(aorder_party.getValue());
          sw.setBen_party(aben_party.getValue());
          sw.setNarrative(anarrative.getValue());
          sw.setDetailsOfCharges(adetailsofcharges.getValue());
          sw.setOrder_party_acc(aorder_party_acc.getValue());
          sw.setBen_party_acc(aben_party_acc.getValue());
          //sw.setCorr_acc(corr_acc.getValue());
          
          
          Res res = SwiftBufferService.doAction( sw, actid,c) ; 
          System.out.println("Return Id "+res.getCode());
          if(res.getCode()==-1){
        	  alert(res.getName());
        	  c.rollback();
        	  return;
          }
          c.commit();
		
		
		/*
		
		SwiftBufferService.create(new SwiftBuffer(
            
            abranch.getValue(),
            aid.getValue(),
            amessage_type.getValue(),
            aapplication_id.getValue(),
            aservice_id.getValue(),
            abic_from.getValue(),
            abic_to.getValue(),
            adirection.getValue(),
            acountry_from.getValue(),
            acountry_to.getValue(),
            avalue_date.getValue(),
            aamount.doubleValue(),
            acurrency.getValue(),
            areference.getValue(),
            aoperation_code.getValue(),
            aorder_party.getValue(),
            aben_party.getValue(),
            anarrative.getValue(),
            amessage_text.getValue(),
            ainsert_date.getValue(),
            astate.doubleValue(),
            adeal_id.doubleValue(),
            aparent_group_id.doubleValue(),
            aparent_id.getValue(),
            abatch_id.getValue(),
            afile_name.getValue()));
            */
        CheckNull.clearForm(addgrdr);
        CheckNull.clearForm(addgrdl);
        frmgrd.setVisible(true);
        addgrd.setVisible(false);
        fgrd.setVisible(false);	
        
        onClick$btn_back();
        refreshModel(_startPageNumber);
        SelectEvent evt = new SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
        Events.sendEvent(evt);
        } catch (Exception e) {
        	alert(e.getMessage());
            com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
        }
	
}

public void onClick$btn_find(){
	try{
		
    filter = new SwiftBufferFilter();
    prepareFilter();
   // filter.setBranch(fbranch.getValue());
   // filter.setId(fid.getValue());
   // filter.setMessage_type(fmessage_type.getValue());
   // filter.setApplication_id(fapplication_id.getValue());
  //  filter.setService_id(fservice_id.getValue());
    filter.setBic_from(fbic_from.getValue());
    filter.setBic_to(fbic_to.getValue());
    filter.setDirection(fdirection.getValue());
    filter.setCountry_from(fcountry_from.getValue());
    filter.setCountry_to(fcountry_to.getValue());
    filter.setValue_date(fvalue_date.getValue());
    filter.setAmount(famount.doubleValue());
    filter.setCurrency(fcurrency.getValue());
   // filter.setReference(freference.getValue());
   // filter.setOperation_code(foperation_code.getValue());
   // filter.setOrder_party(forder_party.getValue());
   // filter.setBen_party(fben_party.getValue());
   // filter.setNarrative(fnarrative.getValue());
  //  filter.setMessage_text(fmessage_text.getValue());
  //  filter.setInsert_date(finsert_date.getValue());
   // filter.setState(fstate.doubleValue());
   // filter.setDeal_id(fdeal_id.doubleValue());
  //  filter.setParent_group_id(fparent_group_id.doubleValue());
 //   filter.setParent_id(fparent_id.getValue());
  //  filter.setBatch_id(fbatch_id.getValue());
 //   filter.setFile_name(ffile_name.getValue());
    CheckNull.clearForm(frmgrdr);
    onClick$btn_back();
    refreshModel(_startPageNumber);
    SelectEvent evt = new SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
    Events.sendEvent(evt);
    } catch (Exception e) {
        com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
    }
	
}

public void onClick$btn_cancelf(){
	try{
		
	    filter = new SwiftBufferFilter();
	    prepareFilter();
	    CheckNull.clearForm(frmgrdr);
    onClick$btn_back();
    refreshModel(_startPageNumber);
    SelectEvent evt = new SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
    Events.sendEvent(evt);
    } catch (Exception e) {
        com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
    }	
	
}

public void onClick$btn_savego(){
			List<Ofac> of = SwiftBufferService.getOfac(ben_party.getValue());
			if (of.size() > 0) {
				try {
					Messagebox.show("Платеж может попасть под санкции.\r\n"
							+ of.get(0).getProgramlist() + "  "
							+ of.get(0).getFull_name() + " " + of.get(0).getPerc()
							+ "%\r\n Вы уверены что хотите продолжить?",
							"OFAC Black List!!!!!!",
							Messagebox.YES | Messagebox.NO, Messagebox.QUESTION,
							new org.zkoss.zk.ui.event.EventListener() {
								public void onEvent(Event evt)
										throws InterruptedException {
									if (evt.getName().equals("onYes")) {
										action_savego();
									}
								}
							});
				} catch (Exception e) {
					alert(e.getMessage());
					com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
				}

			}else{
			action_savego();
			}
	
}



public void action_savego(){
	Connection c = null;
	
	try {
		
		c = ConnectionPool.getConnection(un, pwd, alias);
		
		

		
		
	current.setCorr_acc(corr_acc.getValue());
	if(current.getCorr_acc().length()==20){
    Res res = SwiftBufferService.doAction( current, 3,  c) ;  
    if(res.getCode()==-1){
  	  alert(res.getName());
  	  c.rollback();
  	  return;
    }	
    System.out.println("SWIFT_COR_ACC "+current.getCorr_acc());
    current.setBranch(ConnectionPool.getValue("SWIFT_BRANCH"));
    Res rs = SwiftBufferService.crDoc(192l,current, c);
	if(rs.getCode()!=0){
		c.rollback();
		alert("Ошибка  "+rs.getName());
		return;
	}
    
	onClick$btn_back();
	refreshModel(_startPageNumber);
	SelectEvent evt = new SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
	Events.sendEvent(evt);    
    
	}else{
		alert("Не установлен коррсчет!!!!!!");
	}
	c.commit();
	
	
	
	

	
	
	} catch (Exception e) {
		try {
			c.rollback();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		alert(e.getMessage());
	com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
	} finally {
		ConnectionPool.close(c);
	}
	
}

public void onClick$btn_save() {
	
	List<Ofac> of = SwiftBufferService.getOfac(ben_party.getValue());
	if (of.size() > 0) {
		
		try {
			Messagebox.show("Платеж может попасть под санкции.\r\n"
					+ of.get(0).getProgramlist() + "  "
					+ of.get(0).getFull_name() + " " + of.get(0).getPerc()
					+ "%\r\n Вы уверены что хотите продолжить?",
					"OFAC Black List!!!!!!",
					Messagebox.YES | Messagebox.NO, Messagebox.QUESTION,
					new org.zkoss.zk.ui.event.EventListener() {
						public void onEvent(Event evt)
								throws InterruptedException {
							if (evt.getName().equals("onYes")) {
								action_save();
							}
						}
					});
		} catch (InterruptedException e) {
			alert(e.getMessage());
			com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
		}

	}	else{
		action_save();
	}
}



public void action_save() {
	Connection c = null;
try{
	c = ConnectionPool.getConnection(un, pwd, alias);
	
	
	

	
	
          //current.setBranch(branch.getValue());
          //current.setId(id.getValue());
          //current.setMessage_type(message_type.getValue());
          //current.setApplication_id(application_id.getValue());
          //current.setService_id(service_id.getValue());
          //current.setBic_from(bic_from.getValue());
          current.setBic_to(bic_to.getValue());
         // current.setDirection(direction.getValue());
         // current.setCountry_from(country_from.getValue());
          current.setCountry_to(country_to.getValue());
          current.setValue_date(value_date.getValue());
          current.setAmount(amount.doubleValue());
          current.setCurrency(currency.getValue());
          current.setReference(reference.getValue());
          current.setOperation_code(operation_code.getValue());
          current.setOrder_party(order_party.getValue());
          current.setBen_party(ben_party.getValue());
          current.setNarrative(narrative.getValue());
          current.setDetailsOfCharges(detailsofcharges.getValue());
         // current.setOrder_party_acc(order_party_acc.getValue());
        //  current.setBen_party_acc(ben_party_acc.getValue());
        //  current.setCorr_acc(corr_acc.getValue());

//          current.setMessage_text(message_text.getValue());
//          current.setInsert_date(insert_date.getValue());
//          current.setState(state.doubleValue());
//          current.setDeal_id(deal_id.doubleValue());
//          current.setParent_group_id(parent_group_id.doubleValue());
//          current.setParent_id(parent_id.getValue());
//          current.setBatch_id(batch_id.getValue());
//          current.setFile_name(file_name.getValue());
           
          Res res = SwiftBufferService.doAction( current, 2, c) ; 
          System.out.println("After res in view"+c.isClosed());          
          if(res.getCode()==-1){
        	  c.rollback();
        	  alert(res.getName());
        	  return;
          }
            if(current.getBranch().equals(ConnectionPool.getValue("SWIFT_BRANCH"))){
              res = SwiftBufferService.crDoc(191l, current, c);
          }else{
        	  res = SwiftBufferService.crDoc(193l, current, c);  
          }
          
          if(res.getCode()!=0){
        	  c.rollback();
        	  alert(res.getName()); 
        	  return;
          }
          c.commit();
          
       // SwiftBufferService.update(current);
   
onClick$btn_back();
refreshModel(_startPageNumber);
SelectEvent evt = new SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
Events.sendEvent(evt);
} catch (Exception e) {
    com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
} finally {
	ConnectionPool.close(c);
}

}




public void onClick$btn_cancel() {
    if(fgrd.isVisible()){
            filter = new SwiftBufferFilter();
            prepareFilter();
    }
onClick$btn_back();
frmgrd.setVisible(true);
addgrd.setVisible(false);
fgrd.setVisible(false);
CheckNull.clearForm(addgrdl);
CheckNull.clearForm(addgrdr);
CheckNull.clearForm(fgrdl);
refreshModel(_startPageNumber);
}

public void onUpload$loadFile(UploadEvent event){
	 try {
         Media media = event.getMedia();
         
         SwiftParser sw = new  SwiftParser(media.getStreamData());
         SwiftMessage sm =sw.message();
         alert(sm.unparsedTextGetText(0));
         //media.getStreamData()
	 } catch(Exception ioe) {
         alert(ioe.toString());
     ISLogger.getLogger().error(CheckNull.getPstr(ioe));;
 }
         
         
	
}
public void onChange$abic_to(InputEvent event) {
		String txt = ((Textbox) event.getTarget()).getValue();
		if (txt.length() > 5) {
			abic_to_str.setValue(SwiftBufferService.getBicName(txt));
			acountry_to.setSelecteditem(txt.substring(4, 6));
		}
}

public void onCtrlKey$abic_to(Event event){
	if (bicwnd$lbic!=null){
	bicwnd$lbic.setModel(new BindingListModelList(SwiftBufferService.getBic(acountry_to.getValue(),alias),false));
	}
	bicwnd.setVisible(true);
}
public void onDoubleClick$lbic$bicwnd() {
	System.out.println("bic  "+currentbic.getData());
	abic_to.setText(currentbic.getData());
	bicwnd.setVisible(false);
	abic_to_str.setValue(SwiftBufferService.getBicName(currentbic.getData()));
	//abic_to_str.setValue(SwiftBufferService.getBicName(currentbic.getData().substring(4,6)));
}

public void nChanging$abic_to$bicwnd(InputEvent event){
	System.out.println("bic  "+currentbic.getData());
		bicwnd$lbic.setModel(new BindingListModelList(SwiftBufferService.getBic(event.getValue(),alias),true));
	
		
}

public void onCtrlKey$aorder_party(Event event){
	chacc$acc.setModel(new BindingListModelList(SwiftBufferService.getAccount("202__840%",alias),false));
	chacc.setVisible(true);
}
public void onDoubleClick$acc$chacc() {
	Constraint ct = aorder_party.getConstraint();
	aorder_party.setConstraint("");
	aorder_party_acc.setValue(currentacc.getId());
	aorder_party.setValue(Transliterator.transliterate(currentacc.getName()));
	aorder_party.setConstraint(ct);
	//acc_mfo.setText(currentacc.getBranch());
	//acc_name.setText(currentacc.getName());
	chacc.setVisible(false);
}

public void onChange$aben_party(InputEvent event) {
	String txt = ((Textbox) event.getTarget()).getValue();
	if (txt.length() > 5) {
		List<Ofac> of =	SwiftBufferService.getOfac(txt);
		if (of.size()>0){
			ofacwnd$ofac.setModel(new ListModelList(of));
			ofacwnd.setVisible(true);
		}
	}
}

public void onClick$saveFile(){
	try {
		/*
	    SwiftMessage msgMT103 = new 	SwiftMessage();

	    SwiftBlock4 bl4 = new SwiftBlock4();
	    net.sourceforge.wife.swift.model.Tag tg =new net.sourceforge.wife.swift.model.Tag("32A");
	    bl4.addTag( tg);
	    
	    
	    
	    
	    PrintWriter writer = new PrintWriter("c:\\testswift.txt", "UTF-8");
	    SwiftWriter wrmsg = new SwiftWriter();
	    
		wrmsg.writeMessage(msgMT103, writer);
		*/
		final MT103 m = new MT103();
		m.setSender(current.getBic_from());
		m.setReceiver(current.getBic_to());
		m.addField(new Field20(current.getReference()));
		m.addField(new Field23B("CRED"));
		Field32A f32A = new Field32A()
		.setDate(Calendar.getInstance())
		.setCurrency(current.getCurrency())
		.setAmount(current.getAmount());
		m.addField(f32A);
		/* 
		Field50A f50A = new Field50A()
		.setAccount("12345678901234567890")
		.setBIC("FOOBANKXXXXX");
		m.addField(f50A);
		 */
		Field50K f50K = new Field50K();
		f50K.setAccount(current.getOrder_party_acc());
		f50K.setNameAndAddress(current.getOrder_party());
		m.addField(f50K);
		
		Field59 f59 = new Field59()
		.setAccount(current.getBen_party_acc())
		.setNameAndAddress(current.getBen_party());
		m.addField(f59);
		
		m.addField(new Field70(current.getNarrative()));
		 
		m.addField(new Field71A(current.getDetailsOfCharges()));
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		out.write(m.message().getBytes());
		out.close();
	    Filedownload.save(out.toByteArray(), "text/plain","Mt"+current.getId()+".txt");

		//PrintWriter writer = new PrintWriter("c:\\testswift.txt", "UTF-8");
		//writer.write(m.message());
		//writer.flush();
		//writer.close();
		
		
		
	} catch (Exception e) {
		
		com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
	} 

	
	
}


public void onClick$crDoc1(){
	Connection c = null;
		try {
/*
			c = ConnectionPool.getConnection(un, pwd, alias);
			Res rs = SwiftBufferService.crDoc(191l, current, c);
			if (rs.getCode() != 0) {
				alert("Ошибка  " + rs.getName());
			}
			c.commit();
*/			
		} catch (Exception e) {
            // c.rollback();
			alert(e.getMessage());
			com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
}

public void onClick$okbtn$chacc(){
	
	chacc$acc.setModel(new BindingListModelList(SwiftBufferService.getAccount(chacc$order_party.getValue(),alias),false));
}

public void onClick$btn_valupr(){
	buttonAction(22);
}
public void onClick$btn_valuprc(){
	buttonAction(24);
}
public void onClick$btn_vnctrl(){
	buttonAction(23);
}
public void onClick$btn_vnctrlc(){
	buttonAction(25);
}

	public void buttonAction(int action) {
		Connection c = null;

		try {

			c = ConnectionPool.getConnection(un, pwd, alias);

			Res res = SwiftBufferService.doAction(current, action, c);
			if (res.getCode() == -1) {
				alert(res.getName());
				c.rollback();
				return;
			}

			SelectEvent evt = new SelectEvent("onSelect", dataGrid,	dataGrid.getSelectedItems());
			Events.sendEvent(evt);
			onClick$btn_back();
			refreshModel(_startPageNumber);

			c.commit();

		} catch (Exception e) {
			try {
				c.rollback();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			alert(e.getMessage());
			com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}

	}
	
	
	
	
	
	
	public void onUpload$saveBic2(UploadEvent event) {

		Connection cc = null;

		try {
			Media media = event.getMedia();
			cc = ConnectionPool.getConnection();
			BufferedReader br = new BufferedReader(media.getReaderData());

			PreparedStatement pss = cc.prepareStatement("delete from swift_base");
			pss.executeUpdate();
			pss = cc.prepareStatement("insert into swift_base values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

			
			String s;
			String[] slf;

			while ((s = br.readLine()) != null) {
				
				
				slf = s.split("\t");

				for (int i = 0; i < 17; i++) {
					pss.setString(i + 1, slf[i]);
				}
				
				pss.executeUpdate();
				
				
				
				
			}

			cc.commit();

			// media.getStreamData()
		} catch (Exception ioe) {

			alert(ioe.toString());
			ISLogger.getLogger().error(CheckNull.getPstr(ioe));

		} finally {
			ConnectionPool.close(cc);
		}

	}
	
	
	public void onUpload$saveBic(UploadEvent event) {
        Connection c = null;
        int zzz =0;
        String s="error";
        try {
        	Media media = event.getMedia();
        	BufferedReader br = new BufferedReader(media.getReaderData());    
        	
        	c = ConnectionPool.getConnection();
                
                PreparedStatement ps = c.prepareStatement("delete from SWIFT_BUFFER_BASE");
                ps.executeUpdate();
               // ps = c.prepareStatement("INSERT INTO account (tag, modification_flag, bic_code, branch_code, institution_name, branch_information, city_heading, subtype_indication, value_added_services, extra_info, physical_address_1, physical_address_2, physical_address_3, physical_address_4, location, country_name ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                //ps = c.prepareStatement("INSERT INTO account  VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                ps = c.prepareStatement("INSERT INTO SWIFT_BUFFER_BASE (tag, modification_flag, bic_code, branch_code, institution_name, branch_information, city_heading, subtype_indication, value_added_services, extra_info, physical_address_1, physical_address_2, physical_address_3, physical_address_4, location, country_name, pob_number, pob_location, pob_country_name ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                
                s = br.readLine();
                String[] str = null;
                
                while ((s = br.readLine()) != null) {
                        str = s.split("\t");
                        zzz++;
                       // System.out.println("Load "+s);
                        /*
                        System.out.println("Load "+str.length);
                        for (int i = 0; i < str.length; i++) {
                                ps.setString(i+1, str[i]);
                                System.out.println(i+" fld : "+str[i]);
                        }
                        */
                        ps.setString(1,str[0]);
                        ps.setString(2,str[1]);
                        ps.setString(3,str[2]);
                        ps.setString(4,str[3]);
                        ps.setString(5,str.length>4?str[4]:"");
                        ps.setString(6,str.length>5?str[5]:"");
                        ps.setString(7,str.length>6?str[6]:"");
                        ps.setString(8,str.length>7?str[7]:"");
                        ps.setString(9,str.length>8?str[8]:"");
                        ps.setString(10,str.length>9?str[9]:"");
                        ps.setString(11,str.length>10?str[10]:"");
                        ps.setString(12,str.length>11?str[11]:"");
                        ps.setString(13,str.length>12?str[12]:"");
                        ps.setString(14,str.length>13?str[13]:"");
                        ps.setString(15,str.length>14?str[14]:"");
                        ps.setString(16,str.length>15?str[15]:"");
                        ps.setString(17,str.length>16?str[16]:"");
                        ps.setString(18,str.length>17?str[17]:"");
                        ps.setString(19,str.length>18?str[18]:"");
                        
                        
                        ps.executeUpdate();
                }
                c.commit();
                alert("Файл успешно загружен");
        } catch (Exception ioe) {
        	//iocom.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
                try {c.rollback(); } catch (Exception e) {com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));}
                alert(zzz+"  "+ioe.toString()+s);
                ISLogger.getLogger().error(s+"\r\n"+CheckNull.getPstr(ioe));
        } finally {
                ConnectionPool.close(c);
        }
}	
	
	public void onClick$btn_delete(){
		if (current.getState()==1){
		buttonAction(6);
		}else{
			alert("Документ "+current.getOrder_party()+" Не может быть удален");
		}
			
	}
	
	
	public void onUpload$btn_recv(UploadEvent event) {

		Media media = event.getMedia();
		System.out.println("file "+media.getName());
		String content = media.getStringData();
		PrintWriter writer;
		try {
			File tf = new File("c:/fds");
			if(!tf.exists()){
				tf.mkdirs();
			}
			tf = new File("c:/fds/"+media.getName());
			tf.createNewFile();
			writer = new PrintWriter("c:/fds/"+media.getName(), "UTF-8");
			writer.println(content);
			
			writer.close();
			FileReader.Recieve_file("c:/fds/"+media.getName(), 1);
			

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

}
