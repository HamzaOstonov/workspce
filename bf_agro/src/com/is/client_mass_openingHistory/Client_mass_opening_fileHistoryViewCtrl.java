package com.is.client_mass_openingHistory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import com.is.utils.CheckNull;


public class Client_mass_opening_fileHistoryViewCtrl extends GenericForwardComposer {
	private Div frm;
	private Listbox dataGrid;
	private Listbox frm$frmgrdlist;
	private Paging contactPaging;
	private Div grd;
	private Grid addgrd, frmgrd, fgrd;
	private Toolbarbutton btn_last;
	private Toolbarbutton btn_next;
	private Toolbarbutton btn_prev;
	private Toolbarbutton btn_first;
	private Toolbarbutton btn_add;
	private Toolbarbutton btn_search;
	private Toolbarbutton btn_back;
	private Toolbar tb;
	private Intbox id, aid, fid;
	private Textbox file_name, sender, v_date, status;
	private Textbox pinfl, last_name, date_birth, code_organization, card_type, phone, statuscl, responce,pinfll, last_namee, date_birthh, code_organizationn, card_typee, phonee, statuscll, responcee;
	private Textbox afile_name, asender, av_date, astatus;
	private Textbox ffile_name, fsender, fv_date, fstatus;
	private Paging client_mass_opening_filePaging;
	private int _pageSize = 15;
	private int _startPageNumber = 0;
	private int _totalSize = 0;
	private boolean _needsTotalSizeUpdate = true;
	private HashMap<String, String> Hstate;
	private String alias;
	public Client_mass_opening_fileFilter filter;
private Window window;
	PagingListModel model = null;
	ListModelList lmodel = null;
	private AnnotateDataBinder binder;
	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");

	private Client_mass_opening_file current = new Client_mass_opening_file();

	public Client_mass_opening_fileHistoryViewCtrl() {
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
		Hstate = Client_mass_opening_fileHistoryService.getHState(alias);
		if (parameter != null) {
			_pageSize = Integer.parseInt(parameter[0]) / 36;
			dataGrid.setRows(Integer.parseInt(parameter[0]) / 36);
		}
		
		refreshModel(_startPageNumber);
		dataGrid.setItemRenderer(new ListitemRenderer() {
			@SuppressWarnings("unchecked")
			public void render(Listitem row, Object data) throws Exception {
				Client_mass_opening_file pClient_mass_opening_file = (Client_mass_opening_file) data;
				Button buttonAction = new Button();
				buttonAction.setImage("/images/binoculars.png");
				Listcell lc = new Listcell();
				buttonAction.setAttribute("rowIndex", row.getIndex());
				buttonAction.setVisible(true);
				row.setValue(pClient_mass_opening_file);

				buttonAction.addEventListener(Events.ON_CLICK, new EventListener() {
					public void onEvent(Event event) throws Exception {
						Button buttonAction = (Button) event.getTarget();
						dataGrid.setSelectedIndex((Integer) buttonAction.getAttribute("rowIndex"));
                   	current = (Client_mass_opening_file) dataGrid.getItemAtIndex((Integer)buttonAction.getAttribute("rowIndex")).getValue();
                   
						onDoubleClick$dataGrid$grd();
						//onDoubleClick$dataGridNE$grdNE();
					}
				});
				

				row.appendChild(new Listcell(pClient_mass_opening_file.getFile_name()));
				row.appendChild(new Listcell(pClient_mass_opening_file.getV_date()));
				row.appendChild(new Listcell(Hstate.get(pClient_mass_opening_file.getStatus())));
				lc.appendChild(buttonAction);
				row.appendChild(lc);

			}
		});
    

		refreshModel(_startPageNumber);

	}

	public void onPaging$client_mass_opening_filePaging(ForwardEvent event) {
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModel(_startPageNumber);
	}

	private void refreshModel(int activePage) {
		client_mass_opening_filePaging.setPageSize(_pageSize);
		
		model = new PagingListModel(activePage, _pageSize, filter, "");

		if (_needsTotalSizeUpdate) {
			_totalSize = model.getTotalSize();
			_needsTotalSizeUpdate = false;
		}

		client_mass_opening_filePaging.setTotalSize(_totalSize);

		dataGrid.setModel((ListModel) model);
		if (model.getSize() > 0) {
			this.current = (Client_mass_opening_file) model.getElementAt(0);
			sendSelEvt();
		}
	}

//Omitted...
	public Client_mass_opening_file getCurrent() {
		return current;
	}

	public void setCurrent(Client_mass_opening_file current) {
		this.current = current;
	}

	public void onDoubleClick$dataGrid$grd() {
		
		//grd.setVisible(false);
		Client_mass_opening_fileHistoryService service= new Client_mass_opening_fileHistoryService();
		List<СlientResident> cl= new ArrayList<СlientResident>();
		List<ClientNotResident> clientNotResident = new ArrayList<ClientNotResident>();	
		cl= service.getClient_mass_opening_clientResident(current.getId());
		clientNotResident= service.getClient_mass_opening_clientNotResident(current.getId());
		
		String template = "/ClientResidentHistory.zul";
        HashMap<String, Object> arguments = new HashMap<String, Object>();
        Client_mass_opening_file clientFile= new Client_mass_opening_file(); 
        System.out.println("Current: "+current.getId().toString());
        clientFile= service.getClient_mass_opening_file(current.getId());

         window = (Window) Executions.createComponents(template, null, arguments);
        window.setClosable(false);
        
          window.setTitle("Клиенты");
          window.setBorder("none");
          
          if(clientFile.getSender().equals("2")) {
          window.setHeight("600px");
          window.setWidth("1200px");
          }
          else {
        	  window.setHeight("600px");
              window.setWidth("1200px");
          }
          window.setPosition("center");
          try {
			window.setMode("popup");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
          window.setAction("show: slideDown;hide: slideUp");
          window.setVisible(true);
          
          
          if(clientFile.getSender().equals("2")) {//нерезеденты
        	  for(ClientNotResident clientNotResidents : clientNotResident) {
        		  
        		  Label labelLimitId = new Label();
      	        Textbox LimitID = new Textbox();
      	        
      	        if(clientNotResidents.getStatus().equals("1")) {
      	        	labelLimitId.setStyle("font-weight:bold;color:green");
      	        	LimitID.setStyle("font-weight:bold;color:green");
      	        }else {
      	        labelLimitId.setStyle("font-weight:bold;color:red");
      	        LimitID.setStyle("font-weight:bold;color:red");
      	        
      	        }
      	        LimitID.setReadonly(true);
      	        LimitID.setWidth("110px");  
        		//firstname
      	      Label labelFirstname = new Label();
    	        Textbox firstname = new Textbox();
    	        if(clientNotResidents.getStatus().equals("1")) {
    	        	labelFirstname.setStyle("font-weight:bold;color:green");
    	        	firstname.setStyle("font-weight:bold;color:green");
    	        }else {
    	        labelFirstname.setStyle("font-weight:bold;color:red");
    	        firstname.setStyle("font-weight:bold;color:red");
    	        
    	        }
    	        firstname.setReadonly(true);
    	        firstname.setWidth("130px"); 
        	  
        	  
    	      //lastname
        	      Label labelLastname = new Label();
      	        Textbox lastname = new Textbox();
      	        if(clientNotResidents.getStatus().equals("1")) {
      	        	labelLastname.setStyle("font-weight:bold;color:green");
      	        	lastname.setStyle("font-weight:bold;color:green");
      	        }else {
      	        	labelLastname.setStyle("font-weight:bold;color:red");
      	        	lastname.setStyle("font-weight:bold;color:red");
      	        
      	        }
      	      lastname.setReadonly(true);
      	    lastname.setWidth("130px"); 
        	  
    	        //patronymic
      	  Label labelPatronymic = new Label();
	        Textbox patronymic = new Textbox();
	        if(clientNotResidents.getStatus().equals("1")) {
	        	labelPatronymic.setStyle("font-weight:bold;color:green");
	        	patronymic.setStyle("font-weight:bold;color:green");
	        }else {
	        	labelPatronymic.setStyle("font-weight:bold;color:red");
	        	patronymic.setStyle("font-weight:bold;color:red");
	        
	        }
	        patronymic.setReadonly(true);
	      patronymic.setWidth("130px"); 
      	 // nationality
	      Label labelNationality = new Label();
	        Textbox nationality = new Textbox();
	        if(clientNotResidents.getStatus().equals("1")) {
	        	labelNationality.setStyle("font-weight:bold;color:green");
	        	nationality.setStyle("font-weight:bold;color:green");
	        }else {
	        	labelNationality.setStyle("font-weight:bold;color:red");
	        	nationality.setStyle("font-weight:bold;color:red");
	        
	        }
	        nationality.setReadonly(true);
	        nationality.setWidth("90px"); 
      	    //citizenship
	        Label labelCitizenship = new Label();
	        Textbox citizenship = new Textbox();
	        if(clientNotResidents.getStatus().equals("1")) {
	        	labelCitizenship.setStyle("font-weight:bold;color:green");
	        	citizenship.setStyle("font-weight:bold;color:green");
	        }else {
	        	labelCitizenship.setStyle("font-weight:bold;color:red");
	        	citizenship.setStyle("font-weight:bold;color:red");
	        
	        }
	        citizenship.setReadonly(true);
	        citizenship.setWidth("100px"); 
      	  //passport_serial
      	  
	        Label labelPassport_serial = new Label();
	        Textbox passport_serial = new Textbox();
	        if(clientNotResidents.getStatus().equals("1")) {
	        	labelPassport_serial.setStyle("font-weight:bold;color:green");
	        	passport_serial.setStyle("font-weight:bold;color:green");
	        }else {
	        	labelPassport_serial.setStyle("font-weight:bold;color:red");
	        	passport_serial.setStyle("font-weight:bold;color:red");
	        
	        }
	        passport_serial.setReadonly(true);
	        passport_serial.setWidth("100px");
      //	passport_number
      	
	        Label labelPassport_number = new Label();
	        Textbox passport_number = new Textbox();
	        if(clientNotResidents.getStatus().equals("1")) {
	        	labelPassport_number.setStyle("font-weight:bold;color:green");
	        	passport_number.setStyle("font-weight:bold;color:green");
	        }else {
	        	labelPassport_number.setStyle("font-weight:bold;color:red");
	        	passport_number.setStyle("font-weight:bold;color:red");
	        
	        }
	        passport_number.setReadonly(true);
	        passport_number.setWidth("90px");
      //	date_birth
	        Label labelDate_birth = new Label();
	        Textbox date_birth = new Textbox();
	        if(clientNotResidents.getStatus().equals("1")) {
	        	labelDate_birth.setStyle("font-weight:bold;color:green");
	        	date_birth.setStyle("font-weight:bold;color:green");
	        }else {
	        	labelDate_birth.setStyle("font-weight:bold;color:red");
	        	date_birth.setStyle("font-weight:bold;color:red");
	        
	        }
	        date_birth.setReadonly(true);
	        date_birth.setWidth("50px");
     // 	code_gender
	        Label labelCode_gender = new Label();
	        Textbox code_gender = new Textbox();
	        if(clientNotResidents.getStatus().equals("1")) {
	        	labelCode_gender.setStyle("font-weight:bold;color:green");
	        	code_gender.setStyle("font-weight:bold;color:green");
	        }else {
	        	labelCode_gender.setStyle("font-weight:bold;color:red");
	        	code_gender.setStyle("font-weight:bold;color:red");
	        
	        }
	        code_gender.setReadonly(true);
	        code_gender.setWidth("0px");
	        
//	     	birth_place
	        Label labelBirth_place = new Label();
	        Textbox birth_place = new Textbox();
	        if(clientNotResidents.getStatus().equals("1")) {
	        	labelBirth_place.setStyle("font-weight:bold;color:green");
	        	birth_place.setStyle("font-weight:bold;color:green");
	        }else {
	        	labelBirth_place.setStyle("font-weight:bold;color:red");
	        	birth_place.setStyle("font-weight:bold;color:red");
	        
	        }
	        birth_place.setReadonly(true);
	        birth_place.setWidth("80px");
	        
	        //issued_by
	        
	        Label labellissued_by = new Label();
	        Textbox issued_by = new Textbox();
	        if(clientNotResidents.getStatus().equals("1")) {
	        	labellissued_by.setStyle("font-weight:bold;color:green");
	        	issued_by.setStyle("font-weight:bold;color:green");
	        }else {
	        	labellissued_by.setStyle("font-weight:bold;color:red");
	        	issued_by.setStyle("font-weight:bold;color:red");
	        
	        }
	        issued_by.setReadonly(true);
	        issued_by.setWidth("80px");
	        
	        //date_issue
	        Label labelDate_issue = new Label();
	        Textbox date_issue = new Textbox();
	        if(clientNotResidents.getStatus().equals("1")) {
	        	labelDate_issue.setStyle("font-weight:bold;color:green");
	        	date_issue.setStyle("font-weight:bold;color:green");
	        }else {
	        	labelDate_issue.setStyle("font-weight:bold;color:red");
	        	date_issue.setStyle("font-weight:bold;color:red");
	 
	        }
	        date_issue.setReadonly(true);
	        date_issue.setWidth("80px");
	        
	        //validity_expire
	        Label labelValidity_expire = new Label();
	        Textbox validity_expire = new Textbox();
	        if(clientNotResidents.getStatus().equals("1")) {
	        	labelValidity_expire.setStyle("font-weight:bold;color:green");
	        	validity_expire.setStyle("font-weight:bold;color:green");
	        }else {
	        	labelValidity_expire.setStyle("font-weight:bold;color:red");
	        	validity_expire.setStyle("font-weight:bold;color:red");
	        
	        }
	        validity_expire.setReadonly(true);
	        validity_expire.setWidth("80px");
	        
	        //organization_code
	        Label labelOrganization_code = new Label();
	        Textbox organization_code = new Textbox();
	        if(clientNotResidents.getStatus().equals("1")) {
	        	labelOrganization_code.setStyle("font-weight:bold;color:green");
	        	organization_code.setStyle("font-weight:bold;color:green");
	        }else {
	        	labelOrganization_code.setStyle("font-weight:bold;color:red");
	        	organization_code.setStyle("font-weight:bold;color:red");
	        
	        }
	        organization_code.setReadonly(true);
	        organization_code.setWidth("50px");
	        
	        //type_card
	        Label labelType_card = new Label();
	        Textbox type_card = new Textbox();
	        if(clientNotResidents.getStatus().equals("1")) {
	        	labelType_card.setStyle("font-weight:bold;color:green");
	        	type_card.setStyle("font-weight:bold;color:green");
	        }else {
	        	labelType_card.setStyle("font-weight:bold;color:red");
	        	type_card.setStyle("font-weight:bold;color:red");
	        
	        }
	        date_issue.setReadonly(true);
	        date_issue.setWidth("150px");
	        
	        //phone
	        Label labelPhone = new Label();
	        Textbox phone = new Textbox();
	        if(clientNotResidents.getStatus().equals("1")) {
	        	labelPhone.setStyle("font-weight:bold;color:green");
	        	phone.setStyle("font-weight:bold;color:green");
	        }else {
	        	labelPhone.setStyle("font-weight:bold;color:red");
	        	phone.setStyle("font-weight:bold;color:red");
	        
	        }
	        phone.setReadonly(true);
	        phone.setWidth("80px");
	        
	        //region
	        Label labelRegion = new Label();
	        Textbox region = new Textbox();
	        if(clientNotResidents.getStatus().equals("1")) {
	        	labelRegion.setStyle("font-weight:bold;color:green");
	        	region.setStyle("font-weight:bold;color:green");
	        }else {
	        	labelRegion.setStyle("font-weight:bold;color:red");
	        	region.setStyle("font-weight:bold;color:red");
	        
	        }
	        region.setReadonly(true);
	        region.setWidth("80px");
	        
	        //district
	        Label labelDistrict = new Label();
	        Textbox district = new Textbox();
	        if(clientNotResidents.getStatus().equals("1")) {
	        	labelDistrict.setStyle("font-weight:bold;color:green");
	        	district.setStyle("font-weight:bold;color:green");
	        }else {
	        	labelDistrict.setStyle("font-weight:bold;color:red");
	        	district.setStyle("font-weight:bold;color:red");
	        
	        }
	        district.setReadonly(true);
	        district.setWidth("80px");
	        
	        //address
	        Label labelAddress = new Label();
	        Textbox address = new Textbox();
	        if(clientNotResidents.getStatus().equals("1")) {
	        	labelAddress.setStyle("font-weight:bold;color:green");
	        	address.setStyle("font-weight:bold;color:green");
	        }else {
	        	labelAddress.setStyle("font-weight:bold;color:red");
	        	address.setStyle("font-weight:bold;color:red");
	        
	        }
	        address.setReadonly(true);
	        address.setWidth("80px");
	        
	        //status
	        Label labelStatus = new Label();
	        Textbox status = new Textbox();
	        if(clientNotResidents.getStatus().equals("1")) {
	        	labelStatus.setStyle("font-weight:bold;color:green");
	        	status.setStyle("font-weight:bold;color:green");
	        }else {
	        	labelStatus.setStyle("font-weight:bold;color:red");
	        	status.setStyle("font-weight:bold;color:red");
	        
	        }
	        status.setReadonly(true);
	        status.setWidth("40px");
	        
	        //responce
	        Label labelResponce = new Label();
	        Textbox responce = new Textbox();
	        if(clientNotResidents.getStatus().equals("1")) {
	        	labelResponce.setStyle("font-weight:bold;color:green");
	        	responce.setStyle("font-weight:bold;color:green");
	        }else {
	        	labelResponce.setStyle("font-weight:bold;color:red");
	        	responce.setStyle("font-weight:bold;color:red");
	        
	        }
	        responce.setReadonly(true);
	        responce.setWidth("80px");
	        //=======================================================
      	      Separator separator = new Separator();
  	        labelLimitId.setValue(" Пинфл ");
  	        LimitID.setValue(clientNotResidents.getPinfl());
  	        window.appendChild(labelLimitId);
	        window.appendChild(LimitID);
	        
	        labelFirstname.setValue(" ИМЯ ");
	        firstname.setValue(clientNotResidents.getFirstname());
  	        window.appendChild(labelFirstname);
	        window.appendChild(firstname);
	        
	        labelLastname.setValue(" Фамилия ");
	        lastname.setValue(clientNotResidents.getLastname());
  	        window.appendChild(labelLastname);
	        window.appendChild(lastname);
	        
	        labelPatronymic.setValue(" Отчество ");
	        patronymic.setValue(clientNotResidents.getPatronymic());
  	        window.appendChild(labelPatronymic);
	        window.appendChild(patronymic);
	        
	        labelNationality.setValue(" Национальность ");
	        nationality.setValue(clientNotResidents.getNationality());
  	        window.appendChild(labelNationality);
	        window.appendChild(nationality);
	        
	        labelCitizenship.setValue(" Гражданство ");
	        citizenship.setValue(clientNotResidents.getCitizenship());
  	        window.appendChild(labelCitizenship);
	        window.appendChild(citizenship);
	        
	        labelPassport_serial.setValue(" Пасспорт серия ");
	        passport_serial.setValue(clientNotResidents.getPassport_serial());
  	        window.appendChild(labelPassport_serial);
	        window.appendChild(passport_serial);
	        
	        
	        labelPassport_number.setValue(" Номер пасспорта ");
	        passport_number.setValue(clientNotResidents.getPassport_number());
  	        window.appendChild(labelPassport_number);
	        window.appendChild(passport_number);
	        
	        labelDate_birth.setValue(" Дата рождения ");
	        date_birth.setValue(clientNotResidents.getDate_birth());
  	        window.appendChild(labelDate_birth);
	        window.appendChild(date_birth);
	        
	        labelCode_gender.setValue(" Пол ");
	        code_gender.setValue(clientNotResidents.getCode_gender());
  	        window.appendChild(labelCode_gender);
	        window.appendChild(code_gender);
	        
	        labelBirth_place.setValue(" Место рождения ");
	        birth_place.setValue(clientNotResidents.getBirth_place());
  	        window.appendChild(labelBirth_place);
	        window.appendChild(birth_place);
	        
	        labellissued_by.setValue(" Кем выдано " );
	        issued_by.setValue(clientNotResidents.getIssued_by());
  	        window.appendChild(labellissued_by);
	        window.appendChild(issued_by);
	        
	        labelDate_issue.setValue(" Дата выдачи ");
	        date_issue.setValue(clientNotResidents.getDate_issue());
  	        window.appendChild(labelDate_issue);
	        window.appendChild(date_issue);
	        
	        labelValidity_expire.setValue(" Срок действия ");
	        validity_expire.setValue(clientNotResidents.getValidity_expire());
  	        window.appendChild(labelValidity_expire);
	        window.appendChild(validity_expire);
	        
	        labelOrganization_code.setValue(" Код организации ");
	        organization_code.setValue(clientNotResidents.getOrganization_code());
  	        window.appendChild(labelOrganization_code);
	        window.appendChild(organization_code);
	        
	        labelType_card.setValue(" Тип карты ");
	        type_card.setValue(clientNotResidents.getType_card());
  	        window.appendChild(labelType_card);
	        window.appendChild(type_card);
	        
	        labelPhone.setValue(" Телефон ");
	        phone.setValue(clientNotResidents.getPhone());
  	        window.appendChild(labelPhone);
	        window.appendChild(phone);
	        
	        labelRegion.setValue(" Регион ");
	        region.setValue(clientNotResidents.getRegion());
  	        window.appendChild(labelRegion);
	        window.appendChild(region);
	        
	        labelDistrict.setValue(" Район ");
	        district.setValue(clientNotResidents.getDistrict());
  	        window.appendChild(labelDistrict);
	        window.appendChild(district);
	        
	        labelAddress.setValue(" Адресс ");
	        address.setValue(clientNotResidents.getAddress());
  	        window.appendChild(labelAddress);
	        window.appendChild(address);
	        
	        
	        labelStatus.setValue(" Статус ");
	        status.setValue(clientNotResidents.getStatus());
  	        window.appendChild(labelStatus);
	        window.appendChild(status);
	        
	        labelResponce.setValue(" Ответ ");
	        responce.setValue(clientNotResidents.getResponce());
  	        window.appendChild(labelResponce);
	        window.appendChild(responce);
	        
	        
	        window.setClosable(true);
	        window.appendChild(separator);
	        window.setVisible(true);    
	        
	        
        	  }//for
        	  
        	  
          }else {
        	  
        	  
          
          
          //резиденты
		for (СlientResident сlientResident : cl) {
			
			
			Label labelLimitId = new Label();
	        Textbox LimitID = new Textbox();
	        if(сlientResident.getStatus().equals("1")) {
	        	labelLimitId.setStyle("font-weight:bold;color:green");
	        	LimitID.setStyle("font-weight:bold;color:green");
	        }else {
	        labelLimitId.setStyle("font-weight:bold;color:red");
	        LimitID.setStyle("font-weight:bold;color:red");
	        
	        }
	        LimitID.setReadonly(true);
	        LimitID.setWidth("130px");
	        
	        
	        //
	        Label labelDescription = new Label();
	        Textbox Description = new Textbox();
	        if(сlientResident.getStatus().equals("1")) {
	        labelDescription.setStyle("font-weight:bold;color:green");
	        Description.setStyle("font-weight:bold;color:green");
	        }else {
	        	  Description.setStyle("font-weight:bold;color:red");
	        	  labelDescription.setStyle("font-weight:bold;color:red");
	        }
	        Description.setReadonly(true);
	        Description.setWidth("90px");
	      
	        
	        Label labelCode_organization = new Label();
	        Textbox code_organization = new Textbox();
	        if(сlientResident.getStatus().equals("1")) {
	        labelCode_organization.setStyle("font-weight:bold;color:green");
	        code_organization.setStyle("font-weight:bold;color:green");
	        }else {
	        	labelCode_organization.setStyle("font-weight:bold;color:red");
		        code_organization.setStyle("font-weight:bold;color:red");
	        }
	        code_organization.setReadonly(true);
	        code_organization.setWidth("130px");
	        
	        
	        Label labelCard_type = new Label();
	        Textbox card_type = new Textbox();
	        if(сlientResident.getStatus().equals("1")) {
	        labelCard_type.setStyle("font-weight:bold;color:green");
	        card_type.setStyle("font-weight:bold;color:green");
	        }else {
	        	labelCard_type.setStyle("font-weight:bold;color:red");
		        card_type.setStyle("font-weight:bold;color:red");
	        }
	        card_type.setReadonly(true);
	        card_type.setWidth("40px");
	   
	        
	        
	        Label labelPhone = new Label();
	        Textbox phone = new Textbox();
	        if(сlientResident.getStatus().equals("1")) {
	        labelPhone.setStyle("font-weight:bold;color:green");
	        phone.setStyle("font-weight:bold;color:green");
	        }else {
	        	labelPhone.setStyle("font-weight:bold;color:red");
		        phone.setStyle("font-weight:bold;color:red");
	        	
	        }
	        phone.setReadonly(true);
	        phone.setWidth("90px");
	        
	        
	       
	        Label labelStatus = new Label();
	        Textbox status = new Textbox();
	        if(сlientResident.getStatus().equals("1")) {
	        labelStatus.setStyle("font-weight:bold;color:green");
	        status.setStyle("font-weight:bold;color:green");
	        }else {
	        	 labelStatus.setStyle("font-weight:bold;color:red");
	        	status.setStyle("font-weight:bold;color:red");
	        	
	        	
	        }
	        status.setReadonly(true);
	        status.setWidth("40px");
	        
	        Label labelPassport_series = new Label();
	        Textbox passport_series = new Textbox();
	        if(сlientResident.getStatus().equals("1")) {
	        	labelPassport_series.setStyle("font-weight:bold;color:green");
	        	passport_series.setStyle("font-weight:bold;color:green");
	        }else {
	        	labelPassport_series.setStyle("font-weight:bold;color:red");
	        	passport_series.setStyle("font-weight:bold;color:red");
	        }
	        passport_series.setReadonly(true);
	        passport_series.setWidth("130px");
	      
	        Label labelPassport_number = new Label();
	        Textbox passport_number = new Textbox();
	        if(сlientResident.getStatus().equals("1")) {
	        labelPassport_number.setStyle("font-weight:bold;color:green");
	        passport_number.setStyle("font-weight:bold;color:green");
	        }
	        else {
	        	labelPassport_number.setStyle("font-weight:bold;color:red");
	        	passport_number.setStyle("font-weight:bold;color:red");
	        	
	        }
	        passport_number.setReadonly(true);
	        passport_number.setWidth("130px");
	        
	        Label labelResponce = new Label();
	        Textbox responce = new Textbox();
	        if(сlientResident.getStatus().equals("1")) {
	        labelResponce.setStyle("font-weight:bold;color:green");
	        responce.setStyle("font-weight:bold;color:green");
	        }
	        else {
	        	labelResponce.setStyle("font-weight:bold;color:red");
		        responce.setStyle("font-weight:bold;color:red");
	        	
	        }
	        responce.setReadonly(true);
	        responce.setWidth("130px");
	        
	        //
	        Separator separator = new Separator();
	        labelLimitId.setValue(" Пинфл- ");
	        LimitID.setValue(сlientResident.getPinfl());
	        labelDescription.setValue(" Фамилия- ");
	        Description.setValue(сlientResident.getLastname());
	       
	        labelCode_organization.setValue(" Код организации-  ");
	        code_organization.setValue(сlientResident.getCode_organization());
	        labelCard_type.setValue("Тип карты- ");
	        card_type.setValue(сlientResident.getCard_type());
	        labelPhone.setValue(" Телефон- ");
	        phone.setValue(сlientResident.getPhone());
	        labelStatus.setValue(" Статус- ");
	        status.setValue(сlientResident.getStatus());
	        labelResponce.setValue(" Ответ- ");
	        responce.setValue(сlientResident.getResponce());
	        labelPassport_series.setValue(" П серия ");
	        passport_series.setValue(сlientResident.getPassport_series());
	        labelPassport_number.setValue(" П номер ");
	        passport_series.setValue(сlientResident.getPassport_number());
	        
	  
	       
	        window.appendChild(labelLimitId);
	        window.appendChild(LimitID);
	        window.appendChild(labelDescription);
	        window.appendChild(Description);
	        window.appendChild(labelCode_organization);
	        window.appendChild(code_organization);
	        window.appendChild(labelCard_type);
	        window.appendChild( card_type);
	        window.appendChild( labelPhone);
	        window.appendChild( phone);
	        window.appendChild(   labelPassport_series);
	        window.appendChild(   passport_series);
	        window.appendChild(   labelPassport_number);
	        window.appendChild(   passport_number);
	        window.appendChild(  labelStatus);
	        window.appendChild(   status);
	        window.appendChild( labelResponce);
	        window.appendChild(   responce);
	        window.setClosable(true);
	        window.appendChild(separator);
	        window.setVisible(true);
	        		
		}//--for
	}

		frm.setVisible(false);
		frmgrd.setVisible(false);
		addgrd.setVisible(false);
		//id.setValue(id.getValue());
		fgrd.setVisible(false);
		btn_back.setImage("/images/folder.png");
		btn_back.setLabel(Labels.getLabel("grid"));
		//refreshModel(_startPageNumber);
		refreshModel(_startPageNumber);
	}
	public void onClose$window() {	
	}

//	public void onDoubleClick$dataGridNE$grdNE() {
//		
//
//		
//		pinfll.setValue("1");
//		last_namee.setValue("FADEEV");
//		date_birthh.setValue("16.09.2020");
//		code_organizationn.setValue("45435435");
//		card_typee.setValue("1");
//		phonee.setValue("334534543");
//		statuscll.setValue("Ошибки есть ");
//		responcee.setValue("Карта не открылась ");
//		
//		frm.setVisible(true);
//		frmgrd.setVisible(true);
//		addgrd.setVisible(false);
//		//id.setValue(id.getValue());
//		fgrd.setVisible(false);
//		btn_back.setImage("/images/folder.png");
//		btn_back.setLabel(Labels.getLabel("grid"));
//	}

	
	public void onClick$btn_cancel1() {

		alert("добавить обновление для файла как отменненый( статус 3)");
		Client_mass_opening_fileHistoryService fileHistoryService = new Client_mass_opening_fileHistoryService();
		//fileHistoryService.updateStatus(current.getId(),"4");
		refreshModel(_startPageNumber);//добавить обновление для файла как отменненый статус 3
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
		SelectEvent evt = new SelectEvent("onSelect", dataGrid, dataGrid.getSelectedItems());
		Events.sendEvent(evt);
	}

	public void onClick$btn_add() {
		onDoubleClick$dataGrid$grd();
		frmgrd.setVisible(false);
		addgrd.setVisible(true);
		fgrd.setVisible(false);
	}

	public void onClick$btn_search() {
		onDoubleClick$dataGrid$grd();
		frmgrd.setVisible(false);
		addgrd.setVisible(false);
		fgrd.setVisible(true);
	}

	public void onClick$btn_save() {
		try {
			if (addgrd.isVisible()) {
				Client_mass_opening_fileHistoryService.create(new Client_mass_opening_file(

						aid.getValue(), afile_name.getValue(), asender.getValue(), av_date.getValue(),
						astatus.getValue()));
				CheckNull.clearForm(addgrd);
				frmgrd.setVisible(true);
				addgrd.setVisible(false);
				fgrd.setVisible(false);
			} else if (fgrd.isVisible()) {
				filter = new Client_mass_opening_fileFilter();

				filter.setId(fid.getValue());
				filter.setFile_name(ffile_name.getValue());
				filter.setSender(fsender.getValue());
				filter.setV_date(fv_date.getValue());
				filter.setStatus(fstatus.getValue());

			} else {

				current.setId(id.getValue());
				current.setFile_name(file_name.getValue());
				current.setSender(sender.getValue());
				current.setV_date(v_date.getValue());
				current.setStatus(status.getValue());
				Client_mass_opening_fileHistoryService.update(current);
			}
			onClick$btn_back();
			refreshModel(_startPageNumber);
			SelectEvent evt = new SelectEvent("onSelect", dataGrid, dataGrid.getSelectedItems());
			Events.sendEvent(evt);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void onClick$btn_cancel() {
		if (fgrd.isVisible()) {
			filter = new Client_mass_opening_fileFilter();
		}
		onClick$btn_back();
		frmgrd.setVisible(true);
		addgrd.setVisible(false);
		fgrd.setVisible(false);
		CheckNull.clearForm(addgrd);
		CheckNull.clearForm(fgrd);
		refreshModel(_startPageNumber);
	}

}
