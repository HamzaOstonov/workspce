package com.is.client_mass_openingCheckNotResident;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

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
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Separator;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;
import org.zkoss.zul.event.PagingEvent;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.is.ConnectionPool;
import com.is.ISLogger;
//import com.is.Rates.RatesService;
import com.is.utils.CheckNull;
import com.is.utils.Res;

public class Client_mass_opening_not_residentViewCtrl extends GenericForwardComposer{
    private Div frm;
    private Listbox dataGrid;
    private Paging contactPaging;
    private Div grd;
    private Grid addgrd,frmgrd,fgrd;
    private Toolbarbutton btn_last;
    private Toolbarbutton btn_next;
    private Toolbarbutton btn_prev;
    private Toolbarbutton btn_first;
    private Toolbarbutton btn_add;
    private Toolbarbutton btn_search;
    private Toolbarbutton btn_back;
    private Toolbarbutton  btn_send,btn_backk;
    private Window client_mass_opening_residentmain;
    private Toolbar tb;
    
    private Textbox id,file_id,lastname,date_birth,code_organization,card_type,phone,pinfl,status,responce,acc_group;
    private Textbox aid,afile_id,alastname,adate_birth,acode_organization,acard_type,aphone,apinfl,astatus,aresponce,aacc_group ;
    private Textbox fid,ffile_id,flastname,fstatus,fresponce,fdate_birth,fcode_organization,fcard_type,fphone,fpinfl,facc_group ;
    private Paging client_mass_opening_residentPaging;
    private Textbox id_message;
    private  int _pageSize = 15;
    private int _startPageNumber = 0;
    private int _totalSize = 0;
    private boolean _needsTotalSizeUpdate = true;
    private String un, pw,alias,branch;
    private Window window;
private String   id_file;
private   GetFromNibbd getFromNibbd = new GetFromNibbd();	
    private Textbox id_test;
    public Client_mass_opening_not_residentFilter filter;

    PagingListModel model = null;
    ListModelList lmodel =null;
    private AnnotateDataBinder binder;
    SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	Client_mass_opening_not_residentService service =new Client_mass_opening_not_residentService();	
private List<Client_mass_opening_not_resident> client_mass_opening_residentsAllClick = new ArrayList<Client_mass_opening_not_resident>();
private Client_mass_opening_not_resident current = new Client_mass_opening_not_resident();

    public Client_mass_opening_not_residentViewCtrl() {
            super('$', false, false);
    }
/**
 *
 *
 */
@Override
public void doAfterCompose(Component comp) throws Exception {
	
        super.doAfterCompose(comp);
       id_file= id_test.getValue().toString();
        System.out.println("ID FILE IN NEW MODULE: "+id_file);
        service.update(Integer.parseInt(id_file),"2");//меняем статус файла на в обработке(2)
        service.getClient_mass_opening_clientResident(id_file);
        filter=new Client_mass_opening_not_residentFilter();
        filter.setFile_id(id_file);
     
        // TODO Auto-generated method stub
            binder = new AnnotateDataBinder(comp);
            binder.bindBean("current", this.current);
            binder.loadAll();
    String[] parameter = (String[]) param.get("ht");
    un=(String)session.getAttribute("un");
    pw=(String)session.getAttribute("pwd");
    alias=(String)session.getAttribute("alias");
    branch=(String)session.getAttribute("branch");
    if (parameter!=null){
            _pageSize = Integer.parseInt( parameter[0])/500;
            dataGrid.setRows(Integer.parseInt( parameter[0])/500);
    }



        dataGrid.setItemRenderer(new ListitemRenderer(){
    @SuppressWarnings("unchecked")
    public void render(Listitem row, Object data) throws Exception {
    	client_mass_opening_residentsAllClick.clear();
         
    	 Client_mass_opening_not_resident pClient_mass_opening_not_resident = (Client_mass_opening_not_resident) data;
    	 
    	Listcell lc = new Listcell();
    	Checkbox ch = new Checkbox(); 
        ch.setId("ch_"+row.getIndex());
        ch.setAttribute("rowIndex", row.getIndex());
        ch.setChecked(pClient_mass_opening_not_resident.getStatus()==1);
        btn_send.setTooltiptext("Отправить");
        
        
        ch.addEventListener(Events.ON_CHECK, new EventListener() {
            public void onEvent(Event event) throws Exception {
              Checkbox ch = (Checkbox) event.getTarget();
              dataGrid.setSelectedIndex((Integer)ch.getAttribute("rowIndex"));
              current = (Client_mass_opening_not_resident) dataGrid.getItemAtIndex((Integer)ch.getAttribute("rowIndex")).getValue();
              if (current.getStatus() == 1) {
                  
                  current.setStatus(1);
                } else {
                  
                  current.setStatus(0);
                }
              client_mass_opening_residentsAllClick.add(current);
    
//              _oldSelectedIndex = dataGrid.getSelectedIndex();
            }
          });  
   
        if(pClient_mass_opening_not_resident.getStatus() == 0 || pClient_mass_opening_not_resident.getStatus() == 0 ){
              //
            }else if(pClient_mass_opening_not_resident.getStatus() == 1){
              row.setStyle("background-color:#aaeeaa;");
            }else if(pClient_mass_opening_not_resident.getStatus() == 0){
              row.setStyle("background-color:#eeaaaa;");
            }
        row.setValue(pClient_mass_opening_not_resident);  
        if(pClient_mass_opening_not_resident.getStatus() == 1){
        	lc.appendChild(ch);
            row.appendChild(lc);
        }else {
        	row.appendChild(new Listcell());
        }
                
              //  row.appendChild(new Listcell(pClient_mass_opening_resident.getId()));
                //row.appendChild(new Listcell(pClient_mass_opening_resident.getFile_id()));
                row.appendChild(new Listcell(pClient_mass_opening_not_resident.getName()));
                row.appendChild(new Listcell(pClient_mass_opening_not_resident.getBirthdate()));
                row.appendChild(new Listcell(pClient_mass_opening_not_resident.getCode_organization()));
                row.appendChild(new Listcell(pClient_mass_opening_not_resident.getType_card()));
                row.appendChild(new Listcell(pClient_mass_opening_not_resident.getPhone_number()));
                row.appendChild(new Listcell(pClient_mass_opening_not_resident.getPinfl()));
                row.appendChild(new Listcell(pClient_mass_opening_not_resident.getGroup_code()));//.setStyle(""));
                //row.appendChild(new Listcell().getStyle();
              
    }});
       
    refreshModel(_startPageNumber);

}

public void onPaging$client_mass_opening_residentPaging(ForwardEvent event){
final PagingEvent pe = (PagingEvent) event.getOrigin();
_startPageNumber = pe.getActivePage();
refreshModel(_startPageNumber);
}


private void refreshModel(int activePage){
    client_mass_opening_residentPaging.setPageSize(_pageSize);
model = new PagingListModel(activePage, _pageSize, filter, "");

if(_needsTotalSizeUpdate) {
        _totalSize = model.getTotalSize();
        _needsTotalSizeUpdate = false;
}

client_mass_opening_residentPaging.setTotalSize(_totalSize);

dataGrid.setModel((ListModel) model);
if (model.getSize()>0){
this.current =(Client_mass_opening_not_resident) model.getElementAt(0);
//sendSelEvt();
}
}



//Omitted...
public Client_mass_opening_not_resident getCurrent() {
return current;
}

public void setCurrent(Client_mass_opening_not_resident current) {
this.current = current;
}

//public void onDoubleClick$dataGrid$grd() {
//            //grd.setVisible(false);
//            //frm.setVisible(true);
//            //frmgrd.setVisible(true);
//            //addgrd.setVisible(false);
//            /fgrd.setVisible(false);
//            //btn_back.setImage("/images/folder.png");
//            btn_back.setLabel(Labels.getLabel("grid"));
//}




public void onClick$btn_backk() {
	
	client_mass_opening_residentmain.detach();
	HashMap<String, Object> arguments = new HashMap<String, Object>();
	String template = "/MassOpenClients1.zul";
	Window window = (Window) Executions.createComponents(template, null, arguments);
	window.setClosable(false);
//	window.setVisible(true);
	
	
}


public void onClick$btn_send() {
	 System.out.println("client_mass_opening_residentsAllClick: btn_send  "+       client_mass_opening_residentsAllClick.toString());
	Client_mass_opening_not_residentService service =new Client_mass_opening_not_residentService();	
	 List<ClientResident> clientResident = new ArrayList<ClientResident>();	
	 ResponceFromNibbd responceNibbd = new ResponceFromNibbd();

	 List<CheckAccountNotResident> checkAccountResidents = new ArrayList<CheckAccountNotResident>() ;
	 Res action= new Res();
	 Res actions= new Res();
	 System.out.println("current.getPinfl() "+current.getPinfl());
	 for(Client_mass_opening_not_resident client_mass_opening_resident :client_mass_opening_residentsAllClick) {
	 clientResident=service.getClient_mass_opening_clientResidentCheck(client_mass_opening_resident.getPinfl());
	 for (ClientResident clientResiden : clientResident) {
		 if(clientResiden.getStatus().equals("0")){
		 String s="У текушего клиента с таким  пинфл - "+  clientResiden.getPinfl() +"неправильно заполнены поля в файле!";
		 alert(s);
		 return;
	 }else {
		 
		 checkAccountResidents=service.checkAccountNotResident("1","08",clientResiden.getPinfl());
		 
		 if(checkAccountResidents.isEmpty()) {//если при проверки в филиалах пусто то бежим в ниббд
			 ISLogger.getLogger().info("КЛИЕНТ РЕЗИДЕНТ НЕ НАЙДЕН:  C ПИНФЛ- "+clientResiden.getPinfl()+" ИМЯ "+clientResiden.getLastname());
			 
				responceNibbd=getRestFromNibbd(clientResiden.getPinfl(),"014",branch,"1");
				ISLogger.getLogger().info("Responce from nibbd: "+ responceNibbd.getResult_sql()+"/n"+responceNibbd.getResultCode()+" /n"+responceNibbd.getResultDesc() );
				String[] parts = responceNibbd.getResult_sql().split(" ");
				  String resulSql = parts[7];
				  
				if(!responceNibbd.getResultCode().equals("0")) {
					alert("По каким-то причинам не удалось отправить запрос в nibbd!"+" \n" +  "id запроса "+resulSql );
					return;
		}else {

			getFromNibbd =service.getSsi_physical(resulSql);//берем из таблицы Ssi_physical от сюда получаем id и вытаскиваем нужных клиентов и ложим в процедуру
			//тут вызываем метод идентификации для открытия чтобы получить данные и подставить в процедуру  
			action=	openClientOpenWindow();
			
			
			
				
				if(action.getCode()!=0) {
					alert("По каким-то причинам не удалось открыть клиента: "+" \n" +  "id запроса "+action.getCode()+" \n"+action.getName() );
					return;
				}else {
					actions=	openClientOpenScoreWindow();
					if(actions.getCode()!=0) {
						alert("По каким-то причинам не удалось открыть счет: "+" \n" +  "id запроса "+actions.getCode()+" \n"+actions.getName() );
						return;}
					
					
					
					if(clientResiden.getCard_type().equals("1")) {//определяем если это узкард  1
						service.getUzCard(un,pw,alias,branch,clientResiden,getFromNibbd);
						
						}if(clientResiden.getCard_type().equals("2")) {
						alert("Открыть в хумо: ");
						}else {//и там и там
							service.getUzCard(un,pw,alias,branch,clientResiden,getFromNibbd);
							
						}
					
				}
				
			
			
		}//проверка от nibbd 	 
		 }//если записей нету в таблицах  client_p p, client
		 else {
			
			 //grd.setVisible(false);
			 
			 ISLogger.getLogger().info("КЛИЕНТ РЕЗИДЕНТ НАЙДЕН:  C ПИНФЛ- "+clientResiden.getPinfl()+" ИМЯ "+clientResiden.getLastname()); 
			 String s="КЛИЕНТ РЕЗИДЕНТ НАЙДЕН:  C ПИНФЛ- "+clientResiden.getPinfl()+" ИМЯ "+clientResiden.getLastname();
			// alert(s);
			 String template = "/ClientResidentResponce.zul";
		        HashMap<String, Object> arguments = new HashMap<String, Object>();
		        window = (Window) Executions.createComponents(template, null, arguments);
		        window.setClosable(false);
		        window.setTitle("Открытие клиента");
		        window.setBorder("none");
		        window.setHeight("600px");
		        window.setWidth("1600px");
		        window.setPosition("center");
		        try {
					window.setMode("modal");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        window.setAction("show: slideDown;hide: slideUp");
		          window.setVisible(true);
		          Button btn_back= new Button();  
		      	btn_back.setImage("/images/folder.png");
		      	btn_back.setLabel(" Назад ");
		      	//btn_back.setHeight("100");
		      	//btn_back.setWidth("100");
		      	
		      	
		      	for(Client_mass_opening_not_resident clientok :client_mass_opening_residentsAllClick) {
		      
		  			Label labelLimitId = new Label();
		  	        Textbox LimitID = new Textbox();
		  	  
		  	        	labelLimitId.setStyle("font-weight:bold;color:green");
		  	        	LimitID.setStyle("font-weight:bold;color:green");
		  	        
		  	        LimitID.setReadonly(true);
		  	        LimitID.setWidth("130px");
		  	        
		  	        
		  	        //
		  	        Label labelDescription = new Label();
		  	        Textbox Description = new Textbox();
		  	       
		  	        labelDescription.setStyle("font-weight:bold;color:green");
		  	        Description.setStyle("font-weight:bold;color:green");
		  	      
		  	        Description.setReadonly(true);
		  	        Description.setWidth("90px");
		  	      
		  	        
		  	        Label labelCode_organization = new Label();
		  	        Textbox code_organization = new Textbox();
		  	        
		  	        labelCode_organization.setStyle("font-weight:bold;color:green");
		  	        code_organization.setStyle("font-weight:bold;color:green");
		  	        
		  	        code_organization.setReadonly(true);
		  	        code_organization.setWidth("130px");
		  	        
		  	        
		  	        Label labelCard_type = new Label();
		  	        Textbox card_type = new Textbox();
		  	       
		  	        labelCard_type.setStyle("font-weight:bold;color:green");
		  	        card_type.setStyle("font-weight:bold;color:green");
		  	       
		  	        card_type.setReadonly(true);
		  	        card_type.setWidth("90px");
		  	   
		  	        
		  	        
		  	        Label labelPhone = new Label();
		  	        Textbox phone = new Textbox();
		  	      
		  	        	labelPhone.setStyle("font-weight:bold;color:green");
		  		        phone.setStyle("font-weight:bold;color:green");
		  	        	
		  	        
		  	        phone.setReadonly(true);
		  	        phone.setWidth("90px");
		  	        
		  	        
		  	       
		  	        Label labelStatus = new Label();
		  	        Textbox status = new Textbox();
		  	       
		  	        labelStatus.setStyle("font-weight:bold;color:green");
		  	        status.setStyle("font-weight:bold;color:green");
		  	     
		  	        status.setReadonly(true);
		  	        status.setWidth("200px");
		  	        
		  	      
		  	        Label labelResponce = new Label();
		  	        Textbox responce = new Textbox();
		  	       
		  	        	labelResponce.setStyle("font-weight:bold;color:green");
		  		        responce.setStyle("font-weight:bold;color:green");
		  	        	
		  	        
		  	        responce.setReadonly(true);
		  	        responce.setWidth("200px");
		  	        
		  	        //
		  	        Separator separator = new Separator();
		  	        labelLimitId.setValue(" Пинфл ");
		  	        LimitID.setValue(clientok.getPinfl());
		  	        labelDescription.setValue(" Фамилия ");
		  	        Description.setValue(clientok.getName());
		  	       
		  	        labelCode_organization.setValue(" Код организации  ");
		  	        code_organization.setValue(clientok.getCode_organization());
		  	        labelCard_type.setValue("Тип карты ");
		  	        card_type.setValue(clientok.getType_card());
		  	        labelPhone.setValue(" Телефон ");
		  	        phone.setValue(clientok.getPhone_number());
		  	        labelStatus.setValue(" Статус ");
		  	        status.setValue(String.valueOf(clientok.getStatus()));
		  	        labelResponce.setValue(" Ответ ");
		  	        responce.setValue(" Клиент уже есть в банке ");
		  	        
		  	       
		  	       
		  	        window.appendChild(labelLimitId);
		  	        window.appendChild(LimitID);
		  	        window.appendChild(labelDescription);
		  	        window.appendChild(Description);
		  	        window.appendChild(labelCode_organization);
		  	        window.appendChild(code_organization);
		  	        		 window.appendChild(labelCard_type);
		  	        				 window.appendChild(card_type);
		  	        						 window.appendChild( labelPhone);
		  	        								 window.appendChild( phone);
		  	        window.appendChild(  labelStatus);
		  	        		 window.appendChild(   status);
		  	        				 window.appendChild( labelResponce);
		  	        window.appendChild(   responce);
		  	        
		  	        
		  	        
		  	        window.appendChild(separator);
		      	}
		      	window.setClosable(true);
		  	        window.setVisible(true);
		  	      window.appendChild(btn_back);
		  	    btn_back.addEventListener(Events.ON_CLICK, new EventListener() {
		            public void onEvent(Event event) throws Exception {
		            	window.detach();
		            }
		          });
		  	      
		 }
	 }
	 }//for ClientResident
	 }

}


public Res openClientOpenWindow() {
	 Res action= new Res();
	 
	// alert(s);
	 String template = "/ClientResidentResponce.zul";
       HashMap<String, Object> arguments = new HashMap<String, Object>();
       window = (Window) Executions.createComponents(template, null, arguments);
       window.setClosable(false);
       window.setTitle("Открытие клиента");
       window.setBorder("none");
       window.setHeight("600px");
       window.setWidth("1600px");
       window.setPosition("center");
       try {
			window.setMode("modal");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       window.setAction("show: slideDown;hide: slideUp");
         window.setVisible(true);
         Button btn_back= new Button();  
         Button btn_openC= new Button();  
         
     	btn_back.setImage("/images/folder.png");
     	btn_back.setLabel(" Назад ");
     	
     	btn_openC.setLabel(" Далее ");
     	//btn_back.setHeight("100");
     	//btn_back.setWidth("100");
     	
     	action=	service.doActionResident(un,pw,alias,branch,getFromNibbd);//процедура открытия клиентa
		
		ISLogger.getLogger().info("doActionResident  client mass: "+action.getCode()+" \n"+action.getName());
		
//		
		
		
		
		Label labelLimitId = new Label();
	        Textbox LimitID = new Textbox();
	  
	        	labelLimitId.setStyle("font-weight:bold;color:green");
	        	LimitID.setStyle("font-weight:bold;color:green");
	        
	        LimitID.setReadonly(true);
	        LimitID.setWidth("130px");
	        
	        
	        //
	        Label labelDescription = new Label();
	        Textbox Description = new Textbox();
	       
	        labelDescription.setStyle("font-weight:bold;color:green");
	        Description.setStyle("font-weight:bold;color:green");
	      
	        Description.setReadonly(true);
	        Description.setWidth("90px");
	      
	        
	        Label labelCode_organization = new Label();
	        Textbox code_organization = new Textbox();
	        
	        labelCode_organization.setStyle("font-weight:bold;color:green");
	        code_organization.setStyle("font-weight:bold;color:green");
	        
	        code_organization.setReadonly(true);
	        code_organization.setWidth("130px");
	        
	        
	        Label labelCard_type = new Label();
	        Textbox card_type = new Textbox();
	       
	        labelCard_type.setStyle("font-weight:bold;color:green");
	        card_type.setStyle("font-weight:bold;color:green");
	       
	        card_type.setReadonly(true);
	        card_type.setWidth("90px");
	   
	        
	        
	        Label labelPhone = new Label();
	        Textbox phone = new Textbox();
	      
	        	labelPhone.setStyle("font-weight:bold;color:green");
		        phone.setStyle("font-weight:bold;color:green");
	        	
	        
	        phone.setReadonly(true);
	        phone.setWidth("90px");
	        
	        
	       
	        Label labelStatus = new Label();
	        Textbox status = new Textbox();
	       
	        labelStatus.setStyle("font-weight:bold;color:green");
	        status.setStyle("font-weight:bold;color:green");
	     
	        status.setReadonly(true);
	        status.setWidth("200px");
	        
	      
	        Label labelResponce = new Label();
	        Textbox responce = new Textbox();
	       
	        	labelResponce.setStyle("font-weight:bold;color:green");
		        responce.setStyle("font-weight:bold;color:green");
	        	
	        
	        responce.setReadonly(true);
	        responce.setWidth("200px");
	        
		
     	for(Client_mass_opening_not_resident clientok :client_mass_opening_residentsAllClick) {
     	
     	
 			
 	        //
 	        Separator separator = new Separator();
 	        labelLimitId.setValue(" Пинфл ");
 	        LimitID.setValue(clientok.getPinfl());
 	        labelDescription.setValue(" Фамилия ");
 	        Description.setValue(clientok.getName());
 	       
 	        labelCode_organization.setValue(" Код организации  ");
 	        code_organization.setValue(clientok.getCode_organization());
 	        labelCard_type.setValue("Тип карты ");
 	        card_type.setValue(clientok.getType_card());
 	        labelPhone.setValue(" Телефон ");
 	        phone.setValue(clientok.getPhone_number());
 	        labelStatus.setValue(" Статус ");
 	        status.setValue(String.valueOf(clientok.getStatus()));
 	        labelResponce.setValue(" Ответ ");
 	        
 	       if(action.getCode()!=0){
 	    	  responce.setValue("По каким-то причинам не удалось открыть клиента: "+" \n" +  "id запроса "+action.getCode()+" \n"+action.getName() );
 				
 			}else {
 				  responce.setValue(action.getCode()+ "/n"+action.getName());
 			}
 	      
 	        
 	       
 	      window.appendChild(labelLimitId);
	        window.appendChild(LimitID);
	        window.appendChild(labelDescription);
	        window.appendChild(Description);
	        window.appendChild(labelCode_organization);
	        window.appendChild(code_organization);
	        		 window.appendChild(labelCard_type);
	        				 window.appendChild(card_type);
	        						 window.appendChild( labelPhone);
	        								 window.appendChild( phone);
	        window.appendChild(  labelStatus);
	        		 window.appendChild(   status);
	        				 window.appendChild( labelResponce);
	        window.appendChild(responce);
	        
	        
	        
	        window.appendChild(separator); 
 	       
     	}
     	
     	window.setClosable(true);
 	        window.setVisible(true);
 	      window.appendChild(btn_back);
 	      window.appendChild(btn_openC);
 	    btn_back.addEventListener(Events.ON_CLICK, new EventListener() {
           public void onEvent(Event event) throws Exception {
           	window.detach();
           }
         });
 	    return action;
 	    
}

public  Res openClientOpenScoreWindow() {
	
	 Res actions= new Res();
	// alert(s);
	 String template = "/ClientResidentResponce.zul";
      HashMap<String, Object> arguments = new HashMap<String, Object>();
      window = (Window) Executions.createComponents(template, null, arguments);
      window.setClosable(false);
      window.setTitle("Открытие счета");
      window.setBorder("none");
      window.setHeight("600px");
      window.setWidth("1600px");
      window.setPosition("center");
      try {
			window.setMode("modal");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      window.setAction("show: slideDown;hide: slideUp");
        window.setVisible(true);
        Button btn_back= new Button();  
        Button btn_openC= new Button();  
        
    	btn_back.setImage("/images/folder.png");
    	btn_back.setLabel(" Назад ");
    	
    	btn_openC.setLabel(" Далее ");
    	//btn_back.setHeight("100");
    	//btn_back.setWidth("100");
    	
    	
		
	
		
//		
    	for(Client_mass_opening_not_resident clientok :client_mass_opening_residentsAllClick) {
    	
    		actions=service.doActions(un,pw,alias,branch,clientok,getFromNibbd);
    		ISLogger.getLogger().info("Actions not client mass: "+actions.getCode()+" \n"+actions.getName());
    		
			Label labelLimitId = new Label();
	        Textbox LimitID = new Textbox();
	  
	        	labelLimitId.setStyle("font-weight:bold;color:green");
	        	LimitID.setStyle("font-weight:bold;color:green");
	        	
	        LimitID.setReadonly(true);
	        LimitID.setWidth("130px");
	        
	        
	        //
	        Label labelDescription = new Label();
	        Textbox Description = new Textbox();
	       
	        labelDescription.setStyle("font-weight:bold;color:green");
	        Description.setStyle("font-weight:bold;color:green");
	      
	        Description.setReadonly(true);
	        Description.setWidth("90px");
	      
	        
	        Label labelCode_organization = new Label();
	        Textbox code_organization = new Textbox();
	        
	        labelCode_organization.setStyle("font-weight:bold;color:green");
	        code_organization.setStyle("font-weight:bold;color:green");
	        
	        code_organization.setReadonly(true);
	        code_organization.setWidth("130px");
	        
	        
	        Label labelCard_type = new Label();
	        Textbox card_type = new Textbox();
	       
	        labelCard_type.setStyle("font-weight:bold;color:green");
	        card_type.setStyle("font-weight:bold;color:green");
	       
	        card_type.setReadonly(true);
	        card_type.setWidth("90px");
	   
	        
	        
	        Label labelPhone = new Label();
	        Textbox phone = new Textbox();
	      
	        	labelPhone.setStyle("font-weight:bold;color:green");
		        phone.setStyle("font-weight:bold;color:green");
	        	
	        
	        phone.setReadonly(true);
	        phone.setWidth("90px");
	        
	        
	       
	        Label labelStatus = new Label();
	        Textbox status = new Textbox();
	       
	        labelStatus.setStyle("font-weight:bold;color:green");
	        status.setStyle("font-weight:bold;color:green");
	     
	        status.setReadonly(true);
	        status.setWidth("200px");
	        
	      
	        Label labelResponce = new Label();
	        Textbox responce = new Textbox();
	       
	        	labelResponce.setStyle("font-weight:bold;color:green");
		        responce.setStyle("font-weight:bold;color:green");
	        	
	        
	        responce.setReadonly(true);
	        responce.setWidth("200px");
	        
	        //
	        Separator separator = new Separator();
	        labelLimitId.setValue(" Пинфл ");
	        LimitID.setValue(clientok.getPinfl());
	        labelDescription.setValue(" Фамилия ");
	        Description.setValue(clientok.getName());
	       
	        labelCode_organization.setValue(" Код организации  ");
	        code_organization.setValue(clientok.getCode_organization());
	        labelCard_type.setValue("Тип карты ");
	        card_type.setValue(clientok.getType_card());
	        labelPhone.setValue(" Телефон ");
	        phone.setValue(clientok.getPhone_number());
	        labelStatus.setValue(" Статус ");
	        status.setValue(String.valueOf(clientok.getStatus()));
	        labelResponce.setValue(" Ответ ");
	        
	       if(actions.getCode()!=0){
	    	  responce.setValue("По каким-то причинам не удалось открыть клиента: "+" \n" +  "id запроса "+actions.getCode()+" \n"+actions.getName() );
				
			}else {
				  responce.setValue(actions.getCode()+ "/n"+actions.getName());
			}
	      
	        
	       
	       
	        window.appendChild(labelLimitId);
	        window.appendChild(LimitID);
	        window.appendChild(labelDescription);
	        window.appendChild(Description);
	        window.appendChild(labelCode_organization);
	        window.appendChild(code_organization);
	        		 window.appendChild(labelCard_type);
	        				 window.appendChild(card_type);
	        						 window.appendChild( labelPhone);
	        								 window.appendChild( phone);
	        window.appendChild(  labelStatus);
	        		 window.appendChild(   status);
	        				 window.appendChild( labelResponce);
	        window.appendChild(responce);
	        
	        
	        
	        window.appendChild(separator);
    	}
    	window.setClosable(true);
	        window.setVisible(true);
	      window.appendChild(btn_back);
	      window.appendChild(btn_openC);
	    btn_back.addEventListener(Events.ON_CLICK, new EventListener() {
          public void onEvent(Event event) throws Exception {
          	window.detach();
          }
        });
	    
	   return actions; 
}

public void onDoubleClick$dataGrid$grd() {
    grd.setVisible(false);
    frm.setVisible(true);
    List<String> client_mass_opening_c = new ArrayList<String>();
    String newline = System.getProperty("line.separator");
    if(current.getStatus()==0) {
    	
   	 if(current.getPinfl().isEmpty()) {//pinfl
 		 id_message.setValue("В записи содержится ошибка (код ошибки 1: Поле не может быть пустым ПИНФЛ )."+ " Исправьте данные в файле.»  "+ newline);
 		client_mass_opening_c.add(id_message.getValue());
}
    	 if((!Pattern.matches("^[0-9]+",current.getPinfl()))) {
    		 id_message.setValue("В записи содержится ошибка (код ошибки 2: Неверный формат поля ПИНФЛ )."
                 + " Исправьте данные в файле.»  "+newline); 
    		 client_mass_opening_c.add(id_message.getValue());
    		 
    	 }
    	 if(current.getPinfl().length()!=14){
    		 id_message.setValue("В записи содержится ошибка (код ошибки 3: Неверный размер поля ПИНФЛ )."+ " Исправьте данные в файле.»  "+newline);
    		 client_mass_opening_c.add(id_message.getValue());
     	}//-----fio
 	if(current.getName().isEmpty()) {
 		 id_message.setValue("В записи содержится ошибка (код ошибки 1 : Поле не может быть пустым поля ФИО )."+ " Исправьте данные в файле.»  "+ newline);
 		client_mass_opening_c.add(id_message.getValue());
 		
 	} if((!Pattern.matches("^[a-z A-Z , . ']+",current.getName()))) {
              id_message.setValue("В записи содержится ошибка (код ошибки 2: Неверный формат поля ФИО )."
                 + " Исправьте данные в файле.»  "+ newline);
              client_mass_opening_c.add(id_message.getValue());
 	}
 	if(current.getName().length()<2){
 		 id_message.setValue("В записи содержится ошибка (код ошибки 3: Неверный размер поля ФИО )."+ " Исправьте данные в файле.»  "+ newline);
 		client_mass_opening_c.add(id_message.getValue());
 	}
 	
 	//-----PHONE-------
 	if(current.getPhone_number().isEmpty()) {
 		
 		 id_message.setValue("В записи содержится ошибка (код ошибки 1 : Поле не может быть пустым поля ТЕЛЕФОН )."+ " Исправьте данные в файле.»  "+ newline);
 		client_mass_opening_c.add(id_message.getValue());
 	} if((!Pattern.matches("^[0-9]+",current.getPhone_number()))) {
              id_message.setValue("В записи содержится ошибка (код ошибки 2: Неверный формат поля ТЕЛЕФОН )."
                 + " Исправьте данные в файле.»  "+ newline);
              client_mass_opening_c.add(id_message.getValue());
 	}
 	if(current.getPhone_number().length()!=12){
 		 id_message.setValue("В записи содержится ошибка (код ошибки 3: Неверный размер поля ТЕЛЕФОН )."+ " Исправьте данные в файле.»  "+ newline);
 		client_mass_opening_c.add(id_message.getValue());
 	}        	
 	//-----Код организации-----        	        	
 	
if(current.getCode_organization().isEmpty()) {
 		
 		 id_message.setValue("В записи содержится ошибка (код ошибки 1: Поле не может быть пустым поля Код организации )."+ " Исправьте данные в файле.»  "+ newline);
 		client_mass_opening_c.add(id_message.getValue());
 	} if((!Pattern.matches("^[0-9]+",current.getCode_organization()))) {
              id_message.setValue("В записи содержится ошибка (код ошибки 2: Неверный формат поля Код организации )."
                 + " Исправьте данные в файле.»  "+ newline);
              client_mass_opening_c.add(id_message.getValue());
 	}
 	if(current.getCode_organization().length()!=8){
 		 id_message.setValue("В записи содержится ошибка (код ошибки 3: Неверный размер поля Код организации )."+ " Исправьте данные в файле.»  "+ newline);
 		 client_mass_opening_c.add(id_message.getValue());
 	}
 	
 	//-----ВИД КАРТЫ-----
 	
if(current.getType_card().isEmpty()) {	    		
 		 id_message.setValue("В записи содержится ошибка (код ошибки 1: Поле не может быть пустым поля ВИД КАРТЫ )."+ " Исправьте данные в файле.»  "+ newline);
 		client_mass_opening_c.add(id_message.getValue());
 	} 

if(!current.getType_card().equals("1")&&!current.getType_card().equals("2")&&!current.getType_card().equals("3")) {
 		
 		 id_message.setValue("В записи содержится ошибка (код ошибки 4: Неверное содержание поля  ВИД КАРТЫ )."+ " Исправьте данные в файле.»  "+ newline);
 		client_mass_opening_c.add(id_message.getValue());
 		
 	}//---ДАТА
 	if(current.getBirthdate().isEmpty()) {	    	
 		
 		 id_message.setValue("В записи содержится ошибка (код ошибки 1: Поле не может быть пустым поля ДАТА )."+ " Исправьте данные в файле.»  "+ newline);
 		client_mass_opening_c.add(id_message.getValue());
 	}
 	 if((!Pattern.matches("^\\d{2}.\\d{2}.\\d{4}$",current.getBirthdate()))) {
           id_message.setValue("В записи содержится ошибка (код ошибки 2: Неверный формат поля ДАТА )."
              + " Исправьте данные в файле.» "+ newline);
           client_mass_opening_c.add(id_message.getValue());
 	}
 	 
 	//код группы        	 
 	 if(current.getGroup_code().isEmpty()) {//getAcc_group
  		 id_message.setValue("В записи содержится ошибка (код ошибки 1: Поле не может быть пустым КОД ГРУППЫ )."+ " Исправьте данные в файле.»  "+ newline);
  		client_mass_opening_c.add(id_message.getValue());
 	}
  	if((!Pattern.matches("^[0-9]+",current.getGroup_code()))) {
           id_message.setValue("В записи содержится ошибка (код ошибки 2: Неверный формат поля КОД ГРУППЫ )."
              + " Исправьте данные в файле.»  "+ newline);
           client_mass_opening_c.add(id_message.getValue());
	}
 
  	id_message.setValue(client_mass_opening_c.toString());
	
	 }else {
		 id_message.setValue("Все поля заполнены правильно!");
	 }
    
    
    
//    frmgrd.setVisible(true);
//    addgrd.setVisible(false);
//    fgrd.setVisible(false);
}
public void onClick$btn_cancel() {

	  grd.setVisible(true);
	  frm.setVisible(false);
  }


public ResponceFromNibbd getRestFromNibbd(String pinfl,String bank,String branch,String lang) {
	System.out.println("PINFL: "+pinfl);
	ResponceFromNibbd responceNibbd = new ResponceFromNibbd();
    Connection c = null;
    String fromNibbd = null;
    String idN;
    Client_mass_opening_not_residentService service = new Client_mass_opening_not_residentService();
    idN= service.getIdFromSSi_physiclal();
    System.out.println("id from nibbd: "+idN);
    ObjectMapper objectMapper=  new ObjectMapper();
    StringBuilder content = new StringBuilder();
    try {
    	
// sign = new Sign(1,1,"Hello World");
        String data = null;
        String url_ = ConnectionPool.getValue("NIBBD_PHYSICAL");
        
        URL url = new URL(url_+"/Servlet?bank="+bank+"&branch="+branch+"&lang="+lang+"&id="+idN+"&pin="+pinfl);
        ISLogger.getLogger().error("Адрес ниббд: "+url);
        HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
        httpConnection.setDoOutput(true);
        httpConnection.setRequestMethod("GET");
        httpConnection.setRequestProperty("Accept", "application/json");
       
    
        Integer responseCode = httpConnection.getResponseCode();
        BufferedReader bufferedReader = null;
        if (responseCode > 199 && responseCode < 300) {
            bufferedReader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
        } else {
            bufferedReader = new BufferedReader(new InputStreamReader(httpConnection.getErrorStream()));
        }
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            content.append(new String(line.getBytes("cp1251"), "UTF-8"));
        }
       String result = bufferedReader.toString();
      
       responceNibbd=  objectMapper.readValue(result,ResponceFromNibbd.class );
        ISLogger.getLogger().error("MASSOPEN: RESPONSE FROM NIBBD"+responseCode);
        ISLogger.getLogger().error("MASSOPEN Responce: "+responceNibbd.getResult_sql()+" - "+responceNibbd.getResultCode() +" - "+responceNibbd.getResultDesc()); 
    } catch (Exception e) {
        ISLogger.getLogger().error(CheckNull.getPstr(e));
    } finally {
        ConnectionPool.close(c);
    }
    return responceNibbd;
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


//public void onClick$btn_save() {
//try{
//    if(addgrd.isVisible()){
//            Client_mass_opening_residentService.create(new Client_mass_opening_resident(
//            
//            aid.getValue(),
//            afile_id.getValue(),
//            alastname.getValue(),
//            adate_birth.getValue(),
//            acode_organization.getValue(),
//            acard_type.getValue(),
//            aphone.getValue(),
//            apinfl.getValue(),
//            astatus.getValue(),
//            aresponce.getValue(),
//            aacc_group.getValue()
//            ));
//        CheckNull.clearForm(addgrd);
//        frmgrd.setVisible(true);
//        addgrd.setVisible(false);
//        fgrd.setVisible(false);
//    }else if(fgrd.isVisible()){
//        filter = new Client_mass_opening_residentFilter();
//        
//          filter.setId(fid.getValue());
//          filter.setFile_id(ffile_id.getValue());
//          filter.setLastname(flastname.getValue());
//          filter.setDate_birth(fdate_birth.getValue());
//          filter.setCode_organization(Integer.parseInt(fcode_organization.getValue()));
//          filter.setCard_type(fcard_type.getValue());
//          filter.setPhone(fphone.getValue());
//          filter.setPinfl(fpinfl.getValue());
//          filter.setStatus(fstatus.getValue());
//          filter.setResponce(fresponce.getValue());
//          filter.setAcc_group(facc_group.getValue());
//
//    }else{
//        
//          current.setId(id.getValue());
//          current.setFile_id(file_id.getValue());
//          current.setLastname(lastname.getValue());
//          current.setDate_birth(date_birth.getValue());
//          current.setCode_organization(code_organization.getValue());
//          current.setCard_type(card_type.getValue());
//          current.setPhone(phone.getValue());
//          current.setPinfl(pinfl.getValue());
//          current.setStatus(status.getValue());
//          current.setResponce(responce.getValue());
//          current.setAcc_group(acc_group.getValue());
//        //Client_mass_opening_residentService.update(current);
//    }
////onClick$btn_back();
//refreshModel(_startPageNumber);
//SelectEvent evt = new SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
//Events.sendEvent(evt);
//} catch (Exception e) {
//    e.printStackTrace();
//}
//
//}
//public void onClick$btn_cancel() {
//    if(fgrd.isVisible()){
//            filter = new Client_mass_opening_residentFilter();
//    }
////onClick$btn_back();
//frmgrd.setVisible(true);
//addgrd.setVisible(false);
//fgrd.setVisible(false);
//CheckNull.clearForm(addgrd);
//CheckNull.clearForm(fgrd);
//refreshModel(_startPageNumber);
//}



}


