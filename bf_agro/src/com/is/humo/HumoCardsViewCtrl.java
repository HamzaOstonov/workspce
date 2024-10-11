package com.is.humo;

import globus.IssuingWS.IssuingPortProxy;
import globus.issuing_v_01_02_xsd.OperationResponseInfo;
import com.is.utils.Res;
import java.io.IOException;
import java.rmi.RemoteException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.client.ClientProtocolException;
import org.python.constantine.platform.darwin.Sysconf;
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
import com.is.ISLogger;
import com.is.SwiftBuffer.SwiftBufferService;
import com.is.account.Account;
import com.is.humo.Customer;
import com.is.tieto_globuz.HumoService;
import com.is.tieto_globuz.Utils;
import com.is.tieto_globuz.customer.CustomerService;
import com.is.tieto_globuz.fileProcessing.FileService;
import com.is.tieto_globuz.tieto.Tclient;
import com.is.tieto_globuz.tieto.TclientService;
import com.is.tieto_globuz.tietoAccount.GlobuzAccount;

import com.is.utils.CheckNull;
import com.is.utils.RefCBox;

public class HumoCardsViewCtrl extends GenericForwardComposer {
	private Div frm;
	private Listbox dataGrid, acc;
	private Paging contactPaging;
	private Div grd, addgrd, fgrd;
	private Grid addgrdl, addgrdr, fgrdg, frmgrd;
	private Toolbarbutton btn_last;
	private Toolbarbutton btn_next;
	private Toolbarbutton btn_prev;
	private Toolbarbutton btn_first;
	private Toolbarbutton btn_add;
	private Toolbarbutton btn_search;
	private Toolbarbutton btn_back;
	private Toolbar tb;
	private Textbox client, client_b, branch, card, status1, status2, expiry1,
			expirity2, renew, card_name, mc_name, m_name, stop_cause,
			renewed_card, design_id, instant,tranz_acct,schet;
	private Textbox aclient, aclient_b, abranch, acard, astatus1, astatus2,
			aexpiry1, aexpirity2, arenew, acard_name, amc_name, am_name,
			astop_cause, arenewed_card, adesign_id, ainstant;
	private Textbox fclient, fclient_b, fbranch, fcard, fstatus1, fstatus2,
			fexpiry1, fexpirity2, frenew, fcard_name, fmc_name, fm_name,
			fstop_cause, frenewed_card, fdesign_id, finstant,humocardsmain$personCode;
	private Textbox ap_family, ap_first_name, ap_patronymic, ap_code_adr_distr;

	private Datebox renew_date, arenew_date, frenew_date;
	private Paging humocardsPaging;
	private int _pageSize = 15;
	private int _startPageNumber = 0;
	private int _totalSize = 0;
	private boolean _needsTotalSizeUpdate = true;
	private String branch1, search_clients, alias, un, pw,cardType,jcardType,vidJCard;
	private Listbox chacc$acc;
	private Window chacc;
	private RefCBox humocardsmain$cardType,humocardsmain$jcardType,humocardsmain$vidJCard,humocardsmain$nameCard;

	public HumoCardsFilter filter;

	PagingListModel model = null;
	ListModelList lmodel = null;
	private AnnotateDataBinder binder;
	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");

	private HumoCards current = new HumoCards();

	public HumoCardsViewCtrl() {
		super('$', false, false);
	}
	
	 private Account currentacc = new Account();
	    
	    
	    

	    public Account getCurrentacc() {
			return currentacc;
		}
		public void setCurrentacc(Account currentacc) {
			this.currentacc = currentacc;
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
		//humocardsmain$nameCard.setVisible(false);
		if (parameter != null) {
			_pageSize = Integer.parseInt(parameter[0]);
			dataGrid.setRows(Integer.parseInt(parameter[0]));
			// System.out.println("_pageSize "+_pageSize);
			// System.out.println("dataGrid rows "+dataGrid.getRows());

		}

		// if (parameter!=null){
		// _pageSize = Integer.parseInt( parameter[0])/36;
		// dataGrid.setRows(Integer.parseInt( parameter[0])/36);
		// System.out.println("_pageSize "+_pageSize);
		// System.out.println("dataGrid rows "+dataGrid.getRows());
		//
		// }
		parameter = (String[]) param.get("search_clients");
		
		
		search_clients = parameter[0];
		
		System.out.println("search_clients77:"+ search_clients);
		branch1 = (String) session.getAttribute("branch");
		un = (String) session.getAttribute("un");
		pw = (String) session.getAttribute("pwd");
		alias = (String) session.getAttribute("alias");
	    
		System.out.println("search_clients:"+search_clients);
		System.out.println("branch1:"+branch1);
		System.out.println("un:"+un);
		System.out.println("pw:"+pw);
		System.out.println("alias:"+alias);
		
		//Customer customerJ=HumoCardsService.getCustomerJ(un, pw, alias, branch1, search_clients.toString());
		
		//System.out.println(customerJ.toString());
	
//		ISLogger.getLogger().info("search_clients:"+search_clients);
//		ISLogger.getLogger().info("branch1:"+branch1);
//		ISLogger.getLogger().info("un:"+un);
//		ISLogger.getLogger().info("pw:"+pw);
//		ISLogger.getLogger().info("alias:"+alias);
		
		chacc$acc.setItemRenderer(new ListitemRenderer(){
	        @SuppressWarnings("unchecked")
	        public void render(Listitem row, Object data) throws Exception {
	            Account pAccount = (Account) data;
	            row.setValue(pAccount);
	            row.appendChild(new Listcell(pAccount.getBranch()));
	            row.appendChild(new Listcell(pAccount.getId()));
	            row.appendChild(new Listcell(pAccount.getName()));

	        }});
		
		filter = new HumoCardsFilter();
		if (parameter != null) {
			filter.setClient_b(parameter[0]);
		}

		dataGrid.setItemRenderer(new ListitemRenderer() {
			@SuppressWarnings("unchecked")
			public void render(Listitem row, Object data) throws Exception {
				HumoCards pHumoCards = (HumoCards) data;

				row.setValue(pHumoCards);
				
		        
				row.appendChild(new Listcell(pHumoCards.getClient()));
				row.appendChild(new Listcell(pHumoCards.getReal_card()));
				
				
/////////////////////////////////////////////////////////////////////////////////////////////////			

			
				
				Listcell listcell5 = new Listcell();
                Button button5 = new Button();
                button5.setImage("/images/file.png");
                button5.setLabel("Перевыпустить");
                button5.setAttribute("card", pHumoCards.getReal_card());
                button5.setAttribute("client", pHumoCards.getClient());
                button5.setAttribute("row", row);
                button5.addEventListener("onClick", new EventListener() {
        
        @Override
        public void onEvent(Event event) throws Exception {
        	Button btn = (Button) event.getTarget();
        	Connection c = ConnectionPool.getConnection(alias);
        String message=HumoCardsService.replaceCard(c,btn.getAttribute("card").toString(),btn.getAttribute("client").toString(),alias);
        ConnectionPool.close(c);
        alert (message);
        	
        refreshModel(_startPageNumber);
        
				
        }
        
                });
				
		
	/////////////////////////////////////////////////////////////////////////////////////			
				
				
				
				
				Listcell listcell = new Listcell();
                Button button = new Button();
                button.setImage("/images/file.png");
                button.setLabel("Блокировать");
                button.setAttribute("card", pHumoCards.getReal_card());
                button.setAttribute("row", row);
                button.addEventListener("onClick", new EventListener() {
        
        @Override
        public void onEvent(Event event) throws Exception {
          Button btn = (Button) event.getTarget();
         // Long id = Long.parseLong(btn.getAttribute("fileId")+"");
          
          IssuingPortProxy issuingPortProxy = new IssuingPortProxy(
                  ConnectionPool.getValue("HUMO_HOST"),
                  ConnectionPool.getValue("HUMO_USERNAME"),
                  ConnectionPool.getValue("HUMO_PASSWORD"));

          String bankC = ConnectionPool.getValue("HUMO_BANK_C");
          String groupC = ConnectionPool.getValue("HUMO_GROUPC");
          
          OperationResponseInfo response=HumoCardsService.addStopList(btn.getAttribute("card")+"", issuingPortProxy, bankC, groupC);
         
      
          if(response.getResponse_code().toString().equals("0")){
        	  HumoCardsService.updateStatus(btn.getAttribute("card").toString(),alias,"2","2");  
        	alert("Карта заблокирован!");  
          }
          else{
        	  
           alert(response.getError_description());
        	  
          }
        }
                });
                
                
                
    			Listcell listcell2 = new Listcell();
                Button button2 = new Button();
                button2.setImage("/images/file.png");
                button2.setLabel("Сброс пароля");
                button2.setAttribute("card", pHumoCards.getReal_card());
                button2.setAttribute("row", row);
                button2.addEventListener("onClick", new EventListener() {
        
        @Override
        public void onEvent(Event event) throws Exception {
          Button btn = (Button) event.getTarget();
         // Long id = Long.parseLong(btn.getAttribute("fileId")+"");
          
          IssuingPortProxy issuingPortProxy = new IssuingPortProxy(
                  ConnectionPool.getValue("HUMO_HOST"),
                  ConnectionPool.getValue("HUMO_USERNAME"),
                  ConnectionPool.getValue("HUMO_PASSWORD"));

          String bankC = ConnectionPool.getValue("HUMO_BANK_C");
          String groupC = ConnectionPool.getValue("HUMO_GROUPC");
          
          OperationResponseInfo response=HumoCardsService.deActivateCard(btn.getAttribute("card")+"", issuingPortProxy, bankC, groupC);
         
          if(response.getResponse_code().equals("0")){
        	  
        	alert("Пароль сброшен!");
          }
          else{
        	  
        	  alert(response.getError_description());
        	  
          }
        }
                });
                
                
                
                Listcell listcell3 = new Listcell();
                Button button3 = new Button();
                button3.setImage("/images/file.png");
                button3.setLabel("Разблокировать карту");
                button3.setAttribute("card", pHumoCards.getReal_card());
                button3.setAttribute("row", row);
                button3.addEventListener("onClick", new EventListener() {
        
        @Override
        public void onEvent(Event event) throws Exception {
          Button btn = (Button) event.getTarget();
         // Long id = Long.parseLong(btn.getAttribute("fileId")+"");
          
          IssuingPortProxy issuingPortProxy = new IssuingPortProxy(
                  ConnectionPool.getValue("HUMO_HOST"),
                  ConnectionPool.getValue("HUMO_USERNAME"),
                  ConnectionPool.getValue("HUMO_PASSWORD"));

          String bankC = ConnectionPool.getValue("HUMO_BANK_C");
          String groupC = ConnectionPool.getValue("HUMO_GROUPC");
          
          OperationResponseInfo response=HumoCardsService.deleteStopList(btn.getAttribute("card")+"", issuingPortProxy, bankC, groupC);
         
          if(response.getError_description()==null){
        	HumoCardsService.updateStatus(btn.getAttribute("card").toString(),alias,"1","0");  
        	alert("Карта разблокирован!");  
          }
          else{
        	  
        	  alert(response.getError_description());
        	  
          }
        }
                });
                
                
                Listcell listcell4 = new Listcell();
                Button button4 = new Button();
                button4.setImage("/images/file.png");
                button4.setLabel("Обнулить ПИН");
                button4.setAttribute("card", pHumoCards.getReal_card());
                button4.setAttribute("row", row);
                button4.addEventListener("onClick", new EventListener() {
        
        @Override
        public void onEvent(Event event) throws Exception {
          Button btn = (Button) event.getTarget();
         // Long id = Long.parseLong(btn.getAttribute("fileId")+"");
          
          IssuingPortProxy issuingPortProxy = new IssuingPortProxy(
                  ConnectionPool.getValue("HUMO_HOST"),
                  ConnectionPool.getValue("HUMO_USERNAME"),
                  ConnectionPool.getValue("HUMO_PASSWORD"));

          String bankC = ConnectionPool.getValue("HUMO_BANK_C");
          String groupC = ConnectionPool.getValue("HUMO_GROUPC");
          
          OperationResponseInfo response=HumoCardsService.resetPINCounter(btn.getAttribute("card")+"",  issuingPortProxy, bankC, groupC,un,pw,alias);
         
          if(response.getResponse_code().equals("0")){
        	  
       	alert("Счетчик ПИНа обнулен!");  
          }
          else{
        	  
        	  alert(response.getError_description());
        	  
         }
        }
                });
                
//                Listcell listcellSMS = new Listcell();
//                Button buttonSMS = new Button();
//                final Textbox testBoxSMS=new Textbox();
//                testBoxSMS.setValue(HumoCardsService.getTelNomer(pHumoCards.getReal_card()));
//                buttonSMS.setImage("/images/file.png");
//                buttonSMS.setLabel("Подключить СМС");
//                buttonSMS.setAttribute("card", pHumoCards.getReal_card());
//                buttonSMS.setAttribute("client", pHumoCards.getClient());
//                buttonSMS.setAttribute("row", row);
//                buttonSMS.addEventListener("onClick", new EventListener() {
//        
//        @Override
//        public void onEvent(Event event) throws Exception {
//          Button btn = (Button) event.getTarget();
// 
//          
//         String result=HumoCardsService.setSMS2(btn.getAttribute("client").toString(), btn.getAttribute("card").toString(), testBoxSMS.getValue(),"on");
//          alert(result);
//          
// 
//        }
//                });          
//                
//                
//                //Listcell listcellSMSOtk = new Listcell();
//                Button buttonSMSOtk = new Button();
//                //final Textbox testBoxSMSOtk=new Textbox();
//                buttonSMSOtk.setImage("/images/file.png");
//                buttonSMSOtk.setLabel("Отключить СМС");
//                buttonSMSOtk.setAttribute("card", pHumoCards.getReal_card());
//                buttonSMSOtk.setAttribute("client", pHumoCards.getClient());
//                buttonSMSOtk.setAttribute("row", row);
//                buttonSMSOtk.addEventListener("onClick", new EventListener() {
//        
//        @Override
//        public void onEvent(Event event) throws Exception {
//          Button btn = (Button) event.getTarget();
// 
//          System.out.println("TEL:"+testBoxSMS.getValue());
//         String result=HumoCardsService.setSMS2(btn.getAttribute("client").toString(), btn.getAttribute("card").toString(), testBoxSMS.getValue(),"off");
//          alert(result);
//          
// 
//        }
//                });  
                
                listcell.appendChild(button);
                row.appendChild(listcell);
                
                listcell3.appendChild(button3);
                row.appendChild(listcell3);
                
                listcell2.appendChild(button2);
                row.appendChild(listcell2);
                
                listcell4.appendChild(button4);
                row.appendChild(listcell4);
                
                listcell5.appendChild(button5);
                row.appendChild(listcell5);
                
//                listcellSMS.appendChild(testBoxSMS);
//                listcellSMS.appendChild(buttonSMS);
//                listcellSMS.appendChild(buttonSMSOtk);
                
                //row.appendChild(listcellSMS);
                		
				
				/*
				 * row.appendChild(new Listcell(pHumoCards.getBranch()));
				 * row.appendChild(new Listcell(pHumoCards.getClient_b()));
				 * row.appendChild(new Listcell(pHumoCards.getStatus1()));
				 * row.appendChild(new Listcell(pHumoCards.getStatus2()));
				 * row.appendChild(new Listcell(pHumoCards.getExpiry1()));
				 * row.appendChild(new Listcell(pHumoCards.getExpirity2()));
				 * row.appendChild(new Listcell(pHumoCards.getRenew()));
				 * row.appendChild(new Listcell(pHumoCards.getRenew_date()));
				 * row.appendChild(new Listcell(pHumoCards.getCard_name()));
				 * row.appendChild(new Listcell(pHumoCards.getMc_name()));
				 * row.appendChild(new Listcell(pHumoCards.getM_name()));
				 * row.appendChild(new Listcell(pHumoCards.getStop_cause()));
				 * row.appendChild(new Listcell(pHumoCards.getRenewed_card()));
				 * row.appendChild(new Listcell(pHumoCards.getDesign_id()));
				 * row.appendChild(new Listcell(pHumoCards.getInstant()));
				 */

			}
			
		    
		});

//		chacc$acc.setItemRenderer(new ListitemRenderer() {
//			@SuppressWarnings("unchecked")
//			public void render(Listitem row, Object data) throws Exception {
//				AccountHumo accountHumo = (AccountHumo) data;
//				row.setValue(accountHumo);
//				row.appendChild(new Listcell(accountHumo.getBranch()));
//				row.appendChild(new Listcell(accountHumo.getId()));
//				row.appendChild(new Listcell(accountHumo.getName()));

//			}
//		});
		

		String clientType=HumoCardsService.getClientType(branch1,search_clients.toString(),un,pw,alias);
		
		
//		if(clientType.equals("J")){
//			
//			humocardsmain$cardType.setModel((new ListModelList(HumoCardsService.getJCardType(alias))));
//			humocardsmain$vidJCard.setModel((new ListModelList(HumoCardsService.getVidCard(alias))));
//		}
//		
//		else{
//			
//			humocardsmain$cardType.setModel((new ListModelList(HumoCardsService.getCardType(alias))));
//		}

		refreshModel(_startPageNumber);

	}

	public void onCtrlKey$account(Event event) throws SQLException {
		chacc$acc.setModel(new BindingListModelList(HumoCardsService
				.getAccount(alias, branch1, search_clients), false));
		chacc.setVisible(true);

	}
	
	public void onPaging$humocardsPaging(ForwardEvent event) {
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModel(_startPageNumber);
	}

	private void refreshModel(int activePage) {
		humocardsPaging.setPageSize(_pageSize);
		model = new PagingListModel(activePage, _pageSize, filter, "");

		if (_needsTotalSizeUpdate) {
			_totalSize = model.getTotalSize();
			_needsTotalSizeUpdate = false;
		}

		humocardsPaging.setTotalSize(_totalSize);

		dataGrid.setModel((ListModel) model);
		if (model.getSize() > 0) {
			this.current = (HumoCards) model.getElementAt(0);
			sendSelEvt();
		}
	}

	// Omitted...
	public HumoCards getCurrent() {
		return current;
	}

	public void setCurrent(HumoCards current) {
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
	}

	public void onClick$btn_back() {
		if (frm.isVisible()) {
			frm.setVisible(false);
			grd.setVisible(true);
			btn_back.setImage("/images/file.png");
			btn_back.setLabel(Labels.getLabel("back"));
		} else
			onDoubleClick$dataGrid$grd();
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
		if (dataGrid.getSelectedIndex() != 0) {
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
		if (dataGrid.getSelectedIndex() == 0) {
			btn_first.setDisabled(true);
			btn_prev.setDisabled(true);
		} else {
			btn_first.setDisabled(false);
			btn_prev.setDisabled(false);
		}
		if (dataGrid.getSelectedIndex() == (model.getSize() - 1)) {
			btn_next.setDisabled(true);
			btn_last.setDisabled(true);
		} else {
			btn_next.setDisabled(false);
			btn_last.setDisabled(false);
		}
		SelectEvent evt = new SelectEvent("onSelect", dataGrid,
				dataGrid.getSelectedItems());
		Events.sendEvent(evt);
	}

	public void onClick$btn_add() {
		onDoubleClick$dataGrid$grd();
		frmgrd.setVisible(false);
		addgrd.setVisible(true);
		fgrd.setVisible(false);

		
		// Customer bclient =CustomerService.getCustomerById(search_clients,
		// branch1,alias);
		// Customer custemer =HumoCardsService.getCustomer(un,pw,alias,
		// branch1,search_clients);
		// ap_family.setValue(custemer.getP_family());

		// if (custemer.getP_passport_number() != null)
		// ap_passport_number.setValue(base_cl.getP_passport_number());
		// if (base_cl.getP_type_document() != null)
		// addCustomer$ap_type_document.setSelecteditem(base_cl.getP_type_document());
		// if (base_cl.getP_passport_serial() != null)
		// addCustomer$ap_passport_serial.setValue(base_cl.getP_passport_serial());
		// if (base_cl.getP_passport_date_registration() != null)
		// addCustomer$ap_passport_date_registration.setValue(base_cl.getP_passport_date_registration());
		// if (base_cl.getP_passport_place_registration() != null)
		// addCustomer$ap_passport_place_registration.setValue(base_cl.getP_passport_place_registration());
		// if (base_cl.getP_passport_date_expiration() != null)
		// addCustomer$ap_passport_date_expiration.setValue(base_cl.getP_passport_date_expiration());
		//
		// if (base_cl.getP_family() != null)
		// addCustomer$ap_family.setValue(base_cl.getP_family());
		// if (base_cl.getP_first_name() != null)
		// addCustomer$ap_first_name.setValue(base_cl.getP_first_name());
		// if (base_cl.getP_patronymic() != null)
		// addCustomer$ap_patronymic.setValue(base_cl.getP_patronymic());
		//
		// if (base_cl.getP_birthday() != null)
		// addCustomer$ap_birthday.setValue(base_cl.getP_birthday());
		// if (base_cl.getP_code_birth_region() != null)
		// addCustomer$ap_code_birth_region.setSelecteditem(base_cl.getP_code_birth_region());
		// if (base_cl.getP_code_birth_distr() != null)
		// addCustomer$ap_code_birth_distr.setSelecteditem(base_cl.getP_code_birth_distr());
		// if (base_cl.getP_birth_place() != null)
		// addCustomer$ap_birth_place.setValue(base_cl.getP_birth_place());
		// if (base_cl.getP_code_gender() != null)
		// addCustomer$ap_code_gender.setSelecteditem(base_cl.getP_code_gender());
		// if (base_cl.getP_code_nation() != null)
		// addCustomer$ap_code_nation.setSelecteditem(base_cl.getP_code_nation());
		// if (base_cl.getP_code_adr_region() != null)
		// addCustomer$ap_code_adr_region.setSelecteditem(base_cl.getP_code_adr_region());
		// if (base_cl.getP_code_adr_distr() != null)
		// addCustomer$ap_code_adr_distr.setSelecteditem(base_cl.getP_code_adr_distr());
		// if (base_cl.getP_code_tax_org() != null)
		// addCustomer$ap_code_tax_org.setSelecteditem(base_cl.getP_code_tax_org());
		// if (base_cl.getP_number_tax_registration() != null)
		// addCustomer$ap_number_tax_registration.setValue(base_cl.getP_number_tax_registration());
		// if (base_cl.getP_code_citizenship() != null)
		// addCustomer$ap_code_citizenship.setSelecteditem(base_cl.getP_code_citizenship());
		// if (base_cl.getCode_country() != null)
		// addCustomer$acode_country.setSelecteditem(base_cl.getCode_country());
		// if (base_cl.getCode_resident() != null)
		// addCustomer$acode_resident.setSelecteditem(base_cl.getCode_resident());
		// if (base_cl.getP_phone_mobile() != null)
		// addCustomer$ap_phone_mobile.setValue(base_cl.getP_phone_mobile());
		// if (base_cl.getP_email_address() != null)
		// addCustomer$ap_email_address.setValue(base_cl.getP_email_address());
		// if (base_cl.getP_phone_home() != null)
		// addCustomer$ap_phone_home.setValue(base_cl.getP_phone_home());
		// if (base_cl.getP_inps() != null)
		// addCustomer$ap_inps.setValue(base_cl.getP_inps());
		// if (base_cl.getP_post_address() != null)
		// addCustomer$ap_post_address.setValue(base_cl.getP_post_address());
		//
		//

	}

	public void onClick$btn_search() {
		onDoubleClick$dataGrid$grd();
		frmgrd.setVisible(false);
		addgrd.setVisible(false);
		fgrd.setVisible(true);
	}

	private static globus.IssuingWS.IssuingPortProxy initNp(String alias)
			throws ClientProtocolException, IOException,
			NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
		globus.IssuingWS.IssuingPortProxy issuingPortProxy;

		final TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {

			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			public void checkClientTrusted(
					java.security.cert.X509Certificate[] certs, String authType) {
			}

			public void checkServerTrusted(
					java.security.cert.X509Certificate[] certs, String authType) {
			}
		} };
		try {
			SSLContext sc = SSLContext.getInstance("TLSv1.2");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			HostnameVerifier allHostsValid = new HostnameVerifier() {
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			};
			HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

		} catch (Exception ex) {
		}

		return issuingPortProxy = new globus.IssuingWS.IssuingPortProxy(
				ConnectionPool.getValue("HUMO_HOST", alias),
				ConnectionPool.getValue("HUMO_USERNAME", alias),
				ConnectionPool.getValue("HUMO_PASSWORD", alias));
	}

	public void onCtrlKey$acc(Event event) {

		// chacc$acc.setModel(new BindingListModelList(******,false));
		// chacc.setVisible(true);

	}

	public void onClick$btn_save() throws KeyManagementException, ClientProtocolException, NoSuchAlgorithmException, KeyStoreException, IOException, SQLException {
		
	//if (branch1.toString().equals("00394")){// agro uchun
	System.out.println("DOBAVIT");
	ISLogger.getLogger().error("DOBAVIT");
	//HumoCardsService.addCard(search_clients.toString(), branch1.toString(), humocardsmain$cardType.getValue(), "",schet.getValue(), alias.toString(), humocardsmain$vidJCard.getValue());
	
	Connection c=ConnectionPool.getConnection();
	ResultSet rs=null;
	try{
	Customer customer=HumoCardsService.getCustomerJ(c, branch1.toString(), search_clients.toString());
    //c.close();
    String cl_cat=null;
    if (customer.getCode_type().equals("11")){
    	cl_cat="007";
    }
    else if (customer.getCode_type().equals("09")){
    	cl_cat="006";
    }
    else if (customer.getCode_type().equals("07")){
    	cl_cat="008";
    }
    else{
    	ISLogger.getLogger().error("тип клиента не определен");
    }
    System.out.println("cardType "+cl_cat);
	System.out.println("vidCard "+"1");
	
	if (!schet.getValue().isEmpty()){
	String mes=HumoCardsService.addCard(c,rs,search_clients.toString(), branch1.toString(), cl_cat, "",schet.getValue(), alias.toString(), "1");
	onClick$btn_psevdapan();
	
	if (mes!= null){
		alert(mes);
	}
	else{
		alert("Mijoz uchun korporativ karta ochildi!");
	}
	}
	else{
		
		ISLogger.getLogger().error("Счет не выбрен");
		
}
//	}
//	else {
//		ISLogger.getLogger().error("Нет доступа");
//	}
	
	}catch(Exception e){
		
	}
finally {
	
    Utils.close(rs);
    if (c!=null){c.close();};
}
	}

	public void onClick$btn_cancel() {
		if (fgrd.isVisible()) {
			filter = new HumoCardsFilter();
		}
		onClick$btn_back();
		frmgrd.setVisible(true);
		addgrd.setVisible(false);
		fgrd.setVisible(false);
		CheckNull.clearForm(addgrdl);
		CheckNull.clearForm(addgrdr);
		CheckNull.clearForm(fgrdg);
		refreshModel(_startPageNumber);
	}
	
//	public void onChange$aj_soato() {
//		
////		humocardsmain$nameCard.setModel();
////		
////		humocardsmain$nameCard.setVisible(true);
//		
//		
//	}

	public void onCtrlKey$schet(Event event) throws ClassNotFoundException, SQLException{
		  //chacc$acc.setModel(new BindingListModelList(SwiftBufferService.getAccount("202%",alias),false)); //agro uchun 29810
		  //chacc$acc.setModel(new BindingListModelList(SwiftBufferService.getAccount("29810%",alias),false)); // Anorbank uchun
		  chacc$acc.setModel(new BindingListModelList(HumoCardsService.getallAccounts(alias, branch1, search_clients),false));
		  chacc.setVisible(true);
		
		}
	public void onDoubleClick$acc$chacc() {
		schet.setText(currentacc.getId());
		
		chacc.setVisible(false);
	}
	
	public void onClick$btn_psevdapan() throws SQLException, RemoteException {
		ISLogger.getLogger().error("OnClick$btn_psevdapan");
		try {
			IssuingPortProxy issuingPortProxy = new IssuingPortProxy(
					ConnectionPool.getValue("HUMO_HOST"),
					ConnectionPool.getValue("HUMO_USERNAME"),
					ConnectionPool.getValue("HUMO_PASSWORD"));

			String bankC = ConnectionPool.getValue("HUMO_BANK_C");
			String groupC = ConnectionPool.getValue("HUMO_GROUPC");

			Map<String, String> hashMap = HumoCardsService
					.getPsevdaPANInHashmap();

			
			ISLogger.getLogger().error("issuingPortProxy:" + issuingPortProxy);
			ISLogger.getLogger().error("bankC:" + bankC);
			ISLogger.getLogger().error("groupC:" + groupC);
			
			for (Map.Entry entry : hashMap.entrySet()) {
				ISLogger.getLogger().error("entry.getKey().toString():" + entry.getKey().toString());
				String realCardNumber = HumoCardsService.getRealCardNumber(
						entry.getKey().toString(), issuingPortProxy, bankC,
						groupC);
				
				
				ISLogger.getLogger().error("realcard:" + realCardNumber);
				System.out.println("realcard:" + realCardNumber);
				insertRealCard(entry.getKey().toString(), realCardNumber);

			}

		} catch (Exception e) {
			ISLogger.getLogger().error("REAL_CARD:"+ CheckNull.getPstr(e));
			System.out.println("REAL_CARD:" + e);

		}

	}
	
	public static int insertRealCard(String psevdaPAN, String realcard) {
		Connection c = null;
		int res = 0;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("update humo_cards set real_card=? where card=?");
			// ps =
			// c.prepareStatement("update WRONG_HUMO_CARDS set real_card=? where pan=?");

			ps.setString(1, realcard);
			ps.setString(2, psevdaPAN);

			//

			res = ps.executeUpdate();
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				if (c != null)
					c.rollback();
			} catch (Exception ex) {
			}
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			try {
				if (ps != null)
					ps.close();
			} catch (Exception e) {
			}
			ConnectionPool.close(c);
		}

		return res;
	}
	
	public void onClick$addDobKart() throws SQLException{
		
		Connection c=null;
		PreparedStatement ps=null;
		ResultSet rs=null;	
		String schet777="";
	try{	
		//if (branch1.toString().equals("00394")){// agro uchun
		System.out.println("DOBAVIT");
		ISLogger.getLogger().error("DOBAVIT");
		//schet777="7777";
		schet777=schet.getValue();
		
		
		//HumoCardsService.addCard(search_clients.toString(), branch1.toString(), humocardsmain$cardType.getValue(), "",schet.getValue(), alias.toString(), humocardsmain$vidJCard.getValue());
		
		 c=ConnectionPool.getConnection();
		Customer customer=HumoCardsService.getCustomerJ(c, branch1.toString(), search_clients.toString());
	    c.close();
	    String cl_cat=null;
	    if (customer.getCode_type().equals("11")){
	    	cl_cat="007";
	    }
	    else if (customer.getCode_type().equals("09")){
	    	cl_cat="006";
	    }
	    else if (customer.getCode_type().equals("07")){
	    	cl_cat="008";
	    }
	    else{
	    	ISLogger.getLogger().error("тип клиента не определен");
	    }
	    System.out.println("cardType "+cl_cat);
		System.out.println("vidCard "+"1");
		
		if (!schet777.isEmpty()){
			 c=ConnectionPool.getConnection();
			
			
			
		String clientInHumo=HumoCardsService.getClientIdHUMOForClientB(c, ps, rs, search_clients.toString());
		
		ISLogger.getLogger().error("clientInHumo:"+clientInHumo);
		//String mes=HumoCardsService.addCard(search_clients.toString(), branch1.toString(), cl_cat, "",schet.getValue(), alias.toString(), "1");
		//String err=HumoCardsService.addDobKorpKarta(c,ps,rs,"1", branch1.toString(), customer,clientInHumo, search_clients.toString(),schet777, cl_cat);
		Res err=HumoCardsService.addNewAgreement(c, ps, rs, "1", branch1.toString(), customer, schet777, clientInHumo);
		onClick$btn_psevdapan();
		
		if (err!= null){
			alert(err.getName());
		}
		else{
			alert("Mijoz uchun korporativ karta ochildi!");
		
		}
		}
		else{
			
			ISLogger.getLogger().error("Счет не выбрен");
			alert("Счет не выбрен");
		}
		}catch(Exception e){
			e.printStackTrace();
			ISLogger.getLogger().error(e);
		}
	
		finally{
	
		if (rs!=null){rs.close();}
		if (ps!=null){ps.close();}
		if (c!=null){c.close();}
	}
		
		

		
	
	
}}
