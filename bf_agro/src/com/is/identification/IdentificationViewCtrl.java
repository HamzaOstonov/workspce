package com.is.identification;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.zkoss.bind.annotation.Command;
import org.zkoss.idom.Namespace;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.SuspendNotAllowedException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.ext.Native;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;
import org.zkoss.zul.event.PagingEvent;

import com.is.callcentre.ISLogger;
import com.is.clientcrm.ClientA;
import com.is.clientcrm.ClientAService;
import com.is.clientcrm.ClientAViewCtrl;
import com.is.qr_online.merchant.Merchant;
import com.is.qr_online.merchant.PagingListModel;

@SuppressWarnings("serial")
public class IdentificationViewCtrl extends GenericForwardComposer implements Native {
	
	private Window identificationmain;
	private int _startPageNumber = 0;
	private Paging IdentificationPaging;
	private int _pageSize = 15;
	private Textbox p_sr, p_num, p_pinfl, p_res;
	private Textbox p_Mob_Tel;
	private Toolbarbutton btn_send, btn_adClient;
	private Datebox p_birth;
	private String photo;
	private ClientA clientA;
	protected String alias;
    protected String ses_branch;
    protected String un;
    protected String pw;
	private AnswerMyId answerMyId;
	private AnnotateDataBinder binder;
	private ClientAViewCtrl clientAvCtrl;
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    
    public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
	//	Clients.evalJavaScript("Init_Camera();");
		ses_branch = (String)session.getAttribute("branch");
        alias = (String)session.getAttribute("alias");
        un = (String)session.getAttribute("un");
        pw = (String)session.getAttribute("pwd");
       
        System.out.println("srabotal do After Compose"); 
    
    }
	public void onTakePhoto(Event event) {
		photo = new String (event.getData().toString());
		System.out.println(photo);
		System.out.println("a_Length: "+photo.length());
		ISLogger.getLogger().error("object: "+photo);
	}
	
	public void onClick$btn_initCam() {

	//	Clients.evalJavaScript("Init_Camera();"); // �������� ����� �� .js �����
		System.out.println("otvetTerminala: ");
	}
	
	
/*	@Command("calculate")
	public void onCalculate() {
	    //your calculations
	    Clients.evalJavaScript("Init_Camera();");
	} */
	
	public void onClick$btn_cancel() {
		identificationmain.detach();
	//	Clients.evalJavaScript("window.close()");
	/*	String url_full = Executions.getCurrent().getServerName()+":" + Executions.getCurrent().getServerPort() + Executions.getCurrent().getContextPath() +  Executions.getCurrent().getDesktop().getRequestPath();
		int ind = url_full.indexOf("/identification.zul");
		String new_url = url_full.substring(0, ind);*/
		String template = "/clientCRM.zul";
		System.out.println("template: "+template);
	//	Execution exec = Executions.getCurrent();
	//	Executions.sendRedirect("/clientCRM.zul");
	//	exec.setVoided(false);
	//	Window win=("win.zul", null, null);
		Window window = (Window) Executions.getCurrent().createComponents(template, null, null);
		try {
			window.doModal();
			window.setVisible(true);
	//		binder = new AnnotateDataBinder(window);
	  //      binder.loadAll();
		} catch (SuspendNotAllowedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();																				
		}
		window.setClosable(true);		
	//	Clients.evalJavaScript("window.close()");
		
	}
	
	public void onClick$btn_send() {
		answerMyId = new AnswerMyId();
		Date birthdate = null;
		MyId_RequestObj req = null;
		String p_passSr =  p_sr.getValue();
		String p_passNum = p_num.getValue();
		String typeDoc = null;
		String value = null;
		if(p_sr.getValue().equals("") || p_num.getValue().equals("")) {
			if(p_pinfl.getValue().equals("")) {
				alert("Пинфл или Паспорт серия и номер должен быть заполнен!");
				return;
			}
		}
		if(photo == null || photo.equals("")) {
			alert("Необходимо сфотографировать!");
			return;
		}
		if (p_Mob_Tel.getValue().length() != 12) {
            alert("Моб тел номер клиента должен быть 12 цифр!");
            return;
        }
		
		if(p_birth.getValue()==null) {
			alert("Дата рождения клиента не заполнен!");
            return;
		}
		
		if(p_passNum==null || p_passNum.equals("")) {
			typeDoc = "pinfl";
			value = p_pinfl.getValue();
			birthdate = p_birth.getValue();
		}
		else {
			typeDoc = "passport";
			value = p_passSr + p_passNum;
			birthdate = p_birth.getValue();
		}
		
		String date_birth = df.format(birthdate);
		System.out.println("data_MYID: "+date_birth);
		System.out.println("MYID_typeDoc: "+typeDoc);
		System.out.println("MYID_value: "+value);
		System.out.println("MYID_photo: "+photo);
		req = new MyId_RequestObj(typeDoc, value, date_birth, photo);
		answerMyId = sendReqMyId.getData(req);
		ISLogger.getLogger().error("answer_MYID_viewCtrl: "+answerMyId.toString());
		p_res.setValue(answerMyId.getResultNote());
			
	/*	HashMap<String, Object> arguments = new HashMap<String, Object>();
		ISLogger.getLogger().error("onDoubleClick$dataGrid$grd: " + "sub_type: ");
		if(answerMyId.getProfile() != null) {
		String f_name = answerMyId.getProfile().getCommonData().getFirstName();
		String f_name1 = f_name.equals("") ? "" : f_name;
		String l_name = answerMyId.getProfile().getCommonData().getLastName();
		String l_name1 = l_name.equals("")? "" : l_name;
		String m_name = answerMyId.getProfile().getCommonData().getMiddleName();
		String m_name1 = m_name.equals("")? "" : m_name;
		String lfm = l_name1 + " " + f_name1 + m_name1;
		String myID_pinfl = answerMyId.getProfile().getCommonData().getPinfl();
		String myID_pinfl1 = myID_pinfl.equals("")? "" : myID_pinfl;
		String doc_type_cbu = answerMyId.getProfile().getCommonData().getDocTypeIdCbu();
		String MyId_Sr_Pas = answerMyId.getProfile().getDocData().getPassData().substring(0, 2);
		int length = answerMyId.getProfile().getDocData().getPassData().length();
		String MyId_PasSr_Num = answerMyId.getProfile().getDocData().getPassData().substring(2, length);
		String MyId_Nat =  answerMyId.getProfile().getCommonData().getNationalityIdCbu();
		String MyId_DateBirth = answerMyId.getProfile().getCommonData().getBirthDate();
		String MyId_IssDate = answerMyId.getProfile().getDocData().getIssuedDate();
		String MyId_ExpDate = answerMyId.getProfile().getDocData().getExpiryDate();
		String MyId_Gender = answerMyId.getProfile().getCommonData().getGender();
		String MyId_IssBy = answerMyId.getProfile().getDocData().getIssuedBy();
		String MyId_birth_place = answerMyId.getProfile().getCommonData().getBirthPlace();
		String MyId_reg = answerMyId.getProfile().getAddress().getPermanentRegistration().getRegionIdCbu();
		String MyId_distr = answerMyId.getProfile().getAddress().getPermanentRegistration().getDistrictIdCbu();
        String MyId_PermAdress = answerMyId.getProfile().getAddress().getPermanentAddress();
        String MyId_Inn =  answerMyId.getProfile().getCommonData().getInn();
       			
		arguments.put("f_name", f_name1);
		arguments.put("l_name", l_name1);
		arguments.put("m_name", m_name1);
		arguments.put("lfm", lfm);
		arguments.put("myID_pinfl", myID_pinfl1);
		arguments.put("doc_type_cbu", doc_type_cbu);
		arguments.put("MyId_Sr_Pas", MyId_Sr_Pas);
		arguments.put("MyId_PasSr_Num", MyId_PasSr_Num);
		arguments.put("MyId_Nat", MyId_Nat);
		arguments.put("MyId_DateBirth", MyId_DateBirth);
		arguments.put("MyId_IssDate", MyId_IssDate);
		arguments.put("MyId_ExpDate", MyId_ExpDate);
		arguments.put("MyId_Gender", MyId_Gender);
		arguments.put("MyId_IssBy", MyId_IssBy);
		arguments.put("MyId_birth_place", MyId_birth_place);
		arguments.put("MyId_reg", MyId_reg);
		arguments.put("MyId_distr", MyId_distr);
		arguments.put("MyId_PermAdress", MyId_PermAdress);
		arguments.put("MyId_Inn", MyId_Inn);
		
		ISLogger.getLogger().error("argumnts: " + arguments.size());
		}
		
		System.out.println("sessionID_CRMIdentification: "+ Sessions.getCurrent().getNativeSession());
		
		
		String template = "/clientCRM.zul";
		System.out.println("template: "+template);
		Execution exec = Executions.getCurrent();
	//	Executions.sendRedirect("/clientCRM.zul");
		exec.setVoided(false);		

		Window window = (Window) Executions.createComponents(template, null, arguments);
		try {
			window.doModal();
			binder = new AnnotateDataBinder(window);
	        binder.loadAll();
		} catch (SuspendNotAllowedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();																				
		}
		
		window.setClosable(true);
				
		identificationmain.detach();
	//	Clients.evalJavaScript("window.close()"); */
		
	}
	
	public void onClick$btn_adClient() {
	//	identificationmain.detach();
		
		HashMap<String, Object> arguments = new HashMap<String, Object>();
		ISLogger.getLogger().error("onDoubleClick$dataGrid$grd: " + "sub_type: ");
		if(answerMyId.getProfile() != null) {
		String f_name = answerMyId.getProfile().getCommonData().getFirstName();
		String f_name1 = f_name.equals("") ? "" : f_name;
		String l_name = answerMyId.getProfile().getCommonData().getLastName();
		String l_name1 = l_name.equals("")? "" : l_name;
		String m_name = answerMyId.getProfile().getCommonData().getMiddleName();
		String m_name1 = m_name.equals("")? "" : m_name;
		String lfm = l_name1 + " " + f_name1 + m_name1;
		String myID_pinfl = answerMyId.getProfile().getCommonData().getPinfl();
		String myID_pinfl1 = myID_pinfl.equals("")? "" : myID_pinfl;
		String doc_type_cbu = answerMyId.getProfile().getCommonData().getDocTypeIdCbu();
		String MyId_Sr_Pas = answerMyId.getProfile().getDocData().getPassData().substring(0, 2);
		int length = answerMyId.getProfile().getDocData().getPassData().length();
		String MyId_PasSr_Num = answerMyId.getProfile().getDocData().getPassData().substring(2, length);
		String MyId_Nat =  answerMyId.getProfile().getCommonData().getNationalityIdCbu();
		String MyId_DateBirth = answerMyId.getProfile().getCommonData().getBirthDate();
		String MyId_IssDate = answerMyId.getProfile().getDocData().getIssuedDate();
		String MyId_ExpDate = answerMyId.getProfile().getDocData().getExpiryDate();
		String MyId_Gender = answerMyId.getProfile().getCommonData().getGender();
		String MyId_IssBy = answerMyId.getProfile().getDocData().getIssuedBy();
		String MyId_birth_place = answerMyId.getProfile().getCommonData().getBirthPlace();
		String MyId_reg = answerMyId.getProfile().getAddress().getPermanentRegistration().getRegionIdCbu();
		String MyId_distr = answerMyId.getProfile().getAddress().getPermanentRegistration().getDistrictIdCbu();
        String MyId_PermAdress = answerMyId.getProfile().getAddress().getPermanentAddress();
        String MyId_Inn =  answerMyId.getProfile().getCommonData().getInn();
        String mob = p_Mob_Tel.getValue();
       			
		arguments.put("f_name", f_name1);
		arguments.put("l_name", l_name1);
		arguments.put("m_name", m_name1);
		arguments.put("lfm", lfm);
		arguments.put("myID_pinfl", myID_pinfl1);
		arguments.put("doc_type_cbu", doc_type_cbu);
		arguments.put("MyId_Sr_Pas", MyId_Sr_Pas);
		arguments.put("MyId_PasSr_Num", MyId_PasSr_Num);
		arguments.put("MyId_Nat", MyId_Nat);
		arguments.put("MyId_DateBirth", MyId_DateBirth);
		arguments.put("MyId_IssDate", MyId_IssDate);
		arguments.put("MyId_ExpDate", MyId_ExpDate);
		arguments.put("MyId_Gender", MyId_Gender);
		arguments.put("MyId_IssBy", MyId_IssBy);
		arguments.put("MyId_birth_place", MyId_birth_place);
		arguments.put("MyId_reg", MyId_reg);
		arguments.put("MyId_distr", MyId_distr);
		arguments.put("MyId_PermAdress", MyId_PermAdress);
		arguments.put("MyId_Inn", MyId_Inn);
		arguments.put("Mob_Client", mob);
		
		ISLogger.getLogger().error("argumnts: " + arguments.size());
		}
		
		System.out.println("sessionID_CRMIdentification: "+ Sessions.getCurrent().getNativeSession());
		
		
		String template = "/clientCRM.zul";
		System.out.println("template: "+template);
	//	Execution exec = Executions.getCurrent();
	//	Executions.sendRedirect("/clientCRM.zul");
	//	exec.setVoided(false);		

		Window window = (Window) Executions.createComponents(template, null, arguments);
		try {
			window.doModal();
			window.setVisible(true);
			binder = new AnnotateDataBinder(window);
	        binder.loadAll();
		} catch (SuspendNotAllowedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();																				
		}
		
		window.setClosable(true);
						
		identificationmain.detach();
	//	Clients.evalJavaScript("identificationmain.close()");
	//	clientAvCtrl.onClick$btn_addMyId();
	//	clientAvCtrl.onClick$btn_new();

	}
	
	 public void onClick$btn_search_Bd() {
	        clientA = new ClientA();
	        String p_passSr1 = p_sr.getValue();
	        String p_passNum1 = p_num.getValue();
	        if ((p_sr.getValue().equals("") || p_num.getValue().equals("")) && p_pinfl.getValue().equals("")) {
	             alert("Пинфл или Паспорт серия и номер должен быть заполнен!");
	            return;
	        }
	        
	        if (p_Mob_Tel.getValue().length() != 12) {
	            alert("Моб тел номер клиента должен быть 12 цифр!");
	            return;
	        }
	        if(p_birth.getValue()==null) {
				alert("Дата рождения клиента не заполнен!");
	            return;
			}
	        
	        clientA = ClientAService.getItemByPassport(ses_branch, p_passSr1, p_passNum1, un, pw, alias);
	      //  ISLogger.getLogger().error("clientA: " + clientA.toString());
	        if (clientA != null) {
	            if (clientA.getP_birthday() != null || !clientA.getP_first_name().equals("")) {
	                p_res.setValue("Клиент найден в филиале");
	                
	            }
	        }
	        else {
	            p_res.setValue("Клиент не найден в филиале");
	            btn_send.setVisible(true);
	            btn_adClient.setVisible(true);
	        }
	    }
	

	@Override
	public void addDeclaredNamespace(Namespace arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public List getDeclaredNamespaces() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getEpilogContent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Helper getHelper() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPrologContent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setEpilogContent(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setPrologContent(String arg0) {
		// TODO Auto-generated method stub

	}
	
}
