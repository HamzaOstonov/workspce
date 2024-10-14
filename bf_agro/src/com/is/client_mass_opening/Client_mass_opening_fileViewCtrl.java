package com.is.client_mass_opening;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;
import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.zkoss.util.media.AMedia;
import org.zkoss.util.media.Media;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Div;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Separator;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;
import org.zkoss.zul.event.PagingEvent;
import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.utils.CheckNull;
import com.is.utils.Res;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;


public class Client_mass_opening_fileViewCtrl extends GenericForwardComposer{
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
    private Toolbar tb;
    private Intbox id,aid,fid;
    private Textbox file_name,sender,v_date,status;
    private Textbox afile_name,asender,av_date,astatus ;
    private Textbox ffile_name,fsender,fv_date,fstatus ;
    private Label modalDialog$name_file,modalDialog$date_file,modalDialog$status_file,modalDialogNE$name_file,modalDialogNE$date_file,modalDialogNE$status_file;
    private Window modalDialog,windowMessageConfirm,windowMessageReject,modalDialogNE,windowMessageConfirmNE,windowMessageRejectNE,windowFileClientResident;
    private Label label;
    private Label label1;
    private Separator separator;
    private Separator separator1;
    private Toolbarbutton btn_cancel1;
     
    private List<ClientResident> clientResidents = new ArrayList<ClientResident>();
    private List<ClientNotResident> clientNotResidents = new ArrayList<ClientNotResident>();
  
 
    private Paging client_mass_opening_filePaging;
    private String id_file;
    private String un, pw,alias,branch;
    private  int _pageSize = 51;
    private int _startPageNumber = 0;
    private int _totalSize = 0;
    private boolean _needsTotalSizeUpdate = true;
    private Window historyPanel,historyPanelNE,client_mass_opening;
   
    


    
    public Client_mass_opening_fileFilter filter;

    PagingListModel model = null;
    ListModelList lmodel =null;
    private AnnotateDataBinder binder;
    SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");


private Client_mass_opening_file current = new Client_mass_opening_file();

    public Client_mass_opening_fileViewCtrl() {
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
    un=(String)session.getAttribute("un");
    pw=(String)session.getAttribute("pwd");
    alias=(String)session.getAttribute("alias");
    branch=(String)session.getAttribute("branch");
    
//  if (parameter!=null){
//            _pageSize = Integer.parseInt( parameter[0])/36;
//            dataGrid.setRows(Integer.parseInt( parameter[0])/36);
//    }



       /* dataGrid.setItemRenderer(new ListitemRenderer(){
    @SuppressWarnings("unchecked")
    public void render(Listitem row, Object data) throws Exception {
                Client_mass_opening_file pClient_mass_opening_file = (Client_mass_opening_file) data;

                row.setValue(pClient_mass_opening_file);
                
                row.appendChild(new Listcell(pClient_mass_opening_file.getId()+""));
                row.appendChild(new Listcell(pClient_mass_opening_file.getFile_name()));
                row.appendChild(new Listcell(pClient_mass_opening_file.getSender()));
                row.appendChild(new Listcell(pClient_mass_opening_file.getV_date()));
                row.appendChild(new Listcell(pClient_mass_opening_file.getStatus()));


    }});*/

    //refreshModel(_startPageNumber);

}

public void onPaging$client_mass_opening_filePaging(ForwardEvent event){
final PagingEvent pe = (PagingEvent) event.getOrigin();
_startPageNumber = pe.getActivePage();
refreshModel(_startPageNumber);
}


private void refreshModel(int activePage){
    client_mass_opening_filePaging.setPageSize(_pageSize);
model = new PagingListModel(activePage, _pageSize, filter, "");

if(_needsTotalSizeUpdate) {
        _totalSize = model.getTotalSize();
        _needsTotalSizeUpdate = false;
}

client_mass_opening_filePaging.setTotalSize(_totalSize);

dataGrid.setModel((ListModel) model);
if (model.getSize()>0){
this.current =(Client_mass_opening_file) model.getElementAt(0);
//sendSelEvt();
}
}



//Omitted...
public Client_mass_opening_file getCurrent() {
return current;
}

public void setCurrent(Client_mass_opening_file current) {
this.current = current;
}
public void onClick$btn_history() {
	
	historyPanel.setVisible(true);
	
	
}

public void onClick$btn_historyNE() {
	historyPanelNE.setVisible(true);
}

public void onUpload$uploadXLSX(UploadEvent event) throws InterruptedException{ 
	clientResidents = new ArrayList<ClientResident>();
	Media media = event.getMedia();
	String newline = System.getProperty("line.separator");
	Client_mass_opening_fileService client_mass_opening_fileService = new Client_mass_opening_fileService(); 
	ArrayList<ErrorMessages> errorMessage = new ArrayList<ErrorMessages>();
	 id_file=   client_mass_opening_fileService.insertFile(media.getName(),"1");//инсерт в таблицу client_mass_opening_file 1 резидент 2 нерезидент
	 
	 System.out.println("client_mass_opening_file 211 == id in old zul: "+id_file);
	 
	 SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
	   InputStream in = null;
	    try {
	    	if(!media.getFormat().equalsIgnoreCase("xlsx")&&!media.getFormat().equalsIgnoreCase("csv")){
	    		alert("Not a XLSX or CSV!");
	        	return;
		    }if (media.getFormat().equalsIgnoreCase("xlsx")) {
    	    	DataFormatter formatter = new DataFormatter(); 
    	    in = media.getStreamData();
    		XSSFWorkbook book = new XSSFWorkbook(in);
    		XSSFSheet sh = book.getSheetAt(0);
    		int starRow = 1;
    		
    		String date_birthh = null;
    	//    int endRow = sh.getLastRowNum();	    	    
    		
    	    for (int i = starRow ; i < sh.getPhysicalNumberOfRows(); i++) {    	    	    	    	
    	    	Cell 	pinfl=sh.getRow(i).getCell(0); 
    	    	Cell    passport_series=sh.getRow(i).getCell(1);
    	    	Cell    passport_number=sh.getRow(i).getCell(2);
    	    	Cell 	code_organization =sh.getRow(i).getCell(3);
    	    	Cell	type_card=sh.getRow(i).getCell(4);
    	    	Cell	phone=sh.getRow(i).getCell(5);
    	    	Cell 	firstname=sh.getRow(i).getCell(6); 
    	    	Cell 	date_birth=sh.getRow(i).getCell(7);
    	    	 if(HSSFDateUtil.isCellDateFormatted(date_birth))//без этой проверки дата в таком виде 2/21/22 а должна 21/02/2022
    	         {
    	             DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
    	             Date date = date_birth.getDateCellValue();
    	             date_birthh = df.format(date);
    	          }else {
    	        	  
    	          }	    	
    	    	Cell	acc_group=sh.getRow(i).getCell(8);
    	    	
    	    	
    	    	 String pinfll = formatter.formatCellValue(pinfl);
    	    	 String passport_series1 = formatter.formatCellValue(passport_series);
    	    	 String passport_number1 = formatter.formatCellValue(passport_number);
    	    	 String	code_organizationn=formatter.formatCellValue(code_organization);
    	    	 String	type_cardd =formatter.formatCellValue(type_card);
    	    	 String phonee=formatter.formatCellValue(phone);//пишу в лист и пото

    	    	 String firstnamee= formatter.formatCellValue(firstname);    	    	   
    	    	 String acc_groupp=formatter.formatCellValue(acc_group);
    	    	 
    	    	 
    	    	 String extractedString = "10";
    	    	clientResidents.add((i-1), new ClientResident(pinfll,firstnamee,date_birthh,code_organizationn,type_cardd,phonee,extractedString,passport_series1,passport_number1));   
    	    	System.out.println(passport_series1);

    	    }
    	    book.close();//добавил закрытие потока
    	    Client_mass_opening_fileService сlientResidentInsert = new Client_mass_opening_fileService();     	    	
	    	сlientResidentInsert.insertClientResident(clientResidents,id_file);
	    	client_mass_opening.detach();
	    	HashMap<String, Object> arguments = new HashMap<String, Object>();
	    	arguments.put("id_test", id_file); 
	    	

	    	String template = "/MassOpenHistoryResidents.zul";
	    	Window window = (Window) Executions.createComponents(template, null, arguments);
	    	try {
	    		
	    		window.setPosition("center");
	      
	    			window.setMode("modal");
	    		
	            window.setAction("show: slideDown;hide: slideUp");
	         
	    	} catch (InterruptedException e) {
	    		 //TODO Auto-generated catch block
	    		e.printStackTrace();
	    	}
	    	window.setClosable(false);
	    	window.setVisible(true);
	    	
	    	
    	    for (int j = 0; j < clientResidents.size(); j++) {
    	    	
    	    	if(clientResidents.get(j).getPinfl().isEmpty()) {//pinfl
    	    		errorMessage.add( new ErrorMessages("В записи №"+j+"содержится ошибка (код ошибки 1: Поле не может быть пустым ПИНФЛ )."+ " Исправьте данные или удалите запись.» "+ newline));
 	    	}
    	    	if((!Pattern.matches("^[0-9]+",clientResidents.get(j).getPinfl()))) {
                    errorMessage.add( new ErrorMessages("В записи №"+j+"содержится ошибка (код ошибки 2: Неверный формат поля ПИНФЛ )."
                        + " Исправьте данные или удалите запись.» "+ newline));
        	}
        	if(clientResidents.get(j).getPinfl().length()!=14){
        		errorMessage.add( new ErrorMessages("В записи №"+j+"содержится ошибка (код ошибки 3: Неверный размер поля ПИНФЛ )."+ " Исправьте данные или удалите запись.» "+ newline));
        	}
    	    //-----fio
        	if(clientResidents.get(j).getLastname().isEmpty()) {
        		errorMessage.add( new ErrorMessages("В записи №"+j+"содержится ошибка (код ошибки 1 : Поле не может быть пустым поля ФИО )."+ " Исправьте данные или удалите запись.» "+ newline));
        	} if((!Pattern.matches("^[a-z A-Z , . ']+",clientResidents.get(j).getLastname()))) {
                    errorMessage.add( new ErrorMessages("В записи №"+j+"содержится ошибка (код ошибки 2: Неверный формат поля ФИО )."
                        + " Исправьте данные или удалите запись.» "+ newline));
        	}
        	if(clientResidents.get(j).getLastname().length()<2){
        		errorMessage.add( new ErrorMessages("В записи №"+j+"содержится ошибка (код ошибки 3: Неверный размер поля ФИО )."+ " Исправьте данные или удалите запись.» "+ newline));
        	}
        	
        	//-----PHONE-------
        	if(clientResidents.get(j).getPhone().isEmpty()) {
        		
        		errorMessage.add( new ErrorMessages("В записи №"+j+"содержится ошибка (код ошибки 1 : Поле не может быть пустым поля ТЕЛЕФОН )."+ " Исправьте данные или удалите запись.» "+ newline));
        	} if((!Pattern.matches("^[0-9]+",clientResidents.get(j).getPhone()))) {
                    errorMessage.add( new ErrorMessages("В записи №"+j+"содержится ошибка (код ошибки 2: Неверный формат поля ТЕЛЕФОН )."
                        + " Исправьте данные или удалите запись.» "+ newline));
        	}
        	if(clientResidents.get(j).getPhone().length()!=12){
        		errorMessage.add( new ErrorMessages("В записи №"+j+"содержится ошибка (код ошибки 3: Неверный размер поля ТЕЛЕФОН )."+ " Исправьте данные или удалите запись.» "+ newline));
        	}        	
        	//-----Код организации-----        	        	
        	
    if(clientResidents.get(j).getCode_organization().isEmpty()) {
        		
        		errorMessage.add( new ErrorMessages("В записи №"+j+"содержится ошибка (код ошибки 1: Поле не может быть пустым поля Код организации )."+ " Исправьте данные или удалите запись.» "+ newline));
        	} if((!Pattern.matches("^[0-9]+",clientResidents.get(j).getCode_organization()))) {
                    errorMessage.add( new ErrorMessages("В записи №"+j+"содержится ошибка (код ошибки 2: Неверный формат поля Код организации )."
                        + " Исправьте данные или удалите запись.» "+ newline));
        	}
        	if(clientResidents.get(j).getCode_organization().length()!=8){
        		errorMessage.add( new ErrorMessages("В записи №"+j+"содержится ошибка (код ошибки 3: Неверный размер поля Код организации )."+ " Исправьте данные или удалите запись.» "+ newline));
        	}
        	
        	//-----ВИД КАРТЫ-----
        	
    if(clientResidents.get(j).getCard_type().isEmpty()) {	    		
        		errorMessage.add( new ErrorMessages("В записи №"+j+"содержится ошибка (код ошибки 1: Поле не может быть пустым поля ВИД КАРТЫ )."+ " Исправьте данные или удалите запись.» "+ newline));
        	} 
   
    if(!clientResidents.get(j).getCard_type().equals("1")&&!clientResidents.get(j).getCard_type().equals("2")&&!clientResidents.get(j).getCard_type().equals("3")) {
        		
        		errorMessage.add( new ErrorMessages("В записи №"+j+"содержится ошибка (код ошибки 4: Неверное содержание поля  ВИД КАРТЫ )."+ " Исправьте данные или удалите запись.» "+ newline));
        	
        		
        	}//---ДАТА
        	if(clientResidents.get(j).getDate_birth().isEmpty()) {	    	
        		
        		errorMessage.add( new ErrorMessages("В записи №"+j+"содержится ошибка (код ошибки 1: Поле не может быть пустым поля ДАТА )."+ " Исправьте данные или удалите запись.» "+ newline));
        	}
        	 if((!Pattern.matches("^\\d{2}.\\d{2}.\\d{4}$",clientResidents.get(j).getDate_birth()))) {
                 errorMessage.add( new ErrorMessages("В записи №"+j+"содержится ошибка (код ошибки 2: Неверный формат поля ДАТА )."
                     + " Исправьте данные или удалите запись.» "+ newline));
        	}
        	 
        	//код группы        	 
        	 if(clientResidents.get(j).getAcc_group().isEmpty()) {//getAcc_group
 	    		errorMessage.add( new ErrorMessages("В записи №"+j+"содержится ошибка (код ошибки 1: Поле не может быть пустым КОД ГРУППЫ )."+ " Исправьте данные или удалите запись.» "+ newline));
	    	}
//        	 else if(clientResidents.get(j).getAcc_group().length()>3) {
//        		 System.out.println(j+"da 3dan uzun bolgan acc group bor");
//        	 }
 	    	if((!Pattern.matches("^[0-9]+",clientResidents.get(j).getAcc_group()))) {
                 errorMessage.add( new ErrorMessages("В записи №"+j+"содержится ошибка (код ошибки 2: Неверный формат поля КОД ГРУППЫ )."
                     + " Исправьте данные или удалите запись.» "+ newline));
     	}
 	    	System.out.println("Tekshiryapdi: "+clientResidents.get(j).getAcc_group());
    	    
    	    }    	    	
    	    	  label = new Label();
                 label.setValue("");
                 label.setStyle("font-weight:bold");
                 label.setWidth("30px");
                 label.setStyle("font-weight:bold");
                 label.setMultiline(true);
                  separator = new Separator();
                 
    	    	if(errorMessage.size()>0) {
    	    		String fullError="";
    	    for (int i = 0; i < errorMessage.size(); i++) {
				fullError+=errorMessage.get(i).getError_message();
			}
    	    label.setValue(fullError);
    	   
    	    }else {
    	    	 label.setValue("Все "+clientResidents.size()+" записей внесены верно. Подтвердите загрузку файла в модуль.");
			}    	    		      
    	    	
    	    						   
    	    		                   modalDialog.appendChild(label);  	    		                   
    	    		                   modalDialog.appendChild(separator);
    	    		                   modalDialog.setVisible(true);
    	     		        
    	    		                    
    	    modalDialog.setVisible(false);
    //	    modalDialog$name_file.setValue(media.getName());
    	//    modalDialog$date_file.setValue(sdf.format(System.currentTimeMillis()));
    	 //   modalDialog$status_file.setValue("Hовый");
    	  
    	   // refreshModel(_startPageNumber);
  	    }
    	  

    	    else { 	
    	    System.out.println("csv");
    		byte[] bytes = IOUtils.toByteArray(in);

    	    File in_file = new File(bytes.toString());
    		String line;
    		FileReader fileReader;
    		fileReader = new FileReader(in_file);
		    BufferedReader bufferedReader = new BufferedReader(fileReader);
		    String[] lines;
			int i = 0;
			while ((line = bufferedReader.readLine()) != null){
				lines = line.split(",");
				System.out.println(Arrays.toString(lines));
				
			
			}
			fileReader.close();
			}
        
} catch (Exception e) {
	e.printStackTrace();
	ISLogger.getLogger().error(e);
}
	
}


////////////////////////////////////////////////////





//public void onUpload$uploadNEXLSX(UploadEvent event) throws InterruptedException{
//	Media media = event.getMedia();
//	clientNotResidents = new ArrayList<ClientNotResident>();
//	Client_mass_opening_fileService client_mass_opening_fileService = new Client_mass_opening_fileService(); 
//	 id_file=   client_mass_opening_fileService.insertFile(media.getName(),"2");//инсерт в таблицу client_mass_opening_file  1 резидент 2 нерезидент
//	
//
//	 
//	 SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
//	   ArrayList<ErrorMessages> errorMessage = new ArrayList<ErrorMessages>();
//	   InputStream in = null;
//	    try {
//	    	if(!media.getFormat().equalsIgnoreCase("xlsx")&&!media.getFormat().equalsIgnoreCase("csv")){
//	    		alert("Not a XLSX !");
//	        	return;
//		    }if (media.getFormat().equalsIgnoreCase("xlsx")) {
//    	    	DataFormatter formatter = new DataFormatter(); 
//    	    in = media.getStreamData();
//    		XSSFWorkbook book = new XSSFWorkbook(in);
//    		XSSFSheet sh = book.getSheetAt(0);
//    		int starRow = 1;
//    		
//    		
//			String	date_birthh = null;
//			String date_issuee=null;
//			String	validity_expiree=null;
//    	        
//    	    for (int i = starRow ; i < sh.getPhysicalNumberOfRows(); i++) { 
//    	    
//    	    	 Cell pinfl=sh.getRow(i).getCell(0);
//    	    	 Cell firstname =sh.getRow(i).getCell(1);
//    	    	 Cell lastname =sh.getRow(i).getCell(2);
//    	    	 Cell patronymic =sh.getRow(i).getCell(3);
//    	    	 Cell nationality =sh.getRow(i).getCell(4);
//    	    	 Cell citizenship=sh.getRow(i).getCell(5);
//    	    	 Cell passport_serial =sh.getRow(i).getCell(6);
//    	    	 Cell passport_number =sh.getRow(i).getCell(7);    	    		
//    	    	 Cell date_birth=sh.getRow(i).getCell(8);
//    	    	 Cell code_gender=sh.getRow(i).getCell(9);
//    	    	 Cell birth_place =sh.getRow(i).getCell(10);
//    	    	 if(HSSFDateUtil.isCellDateFormatted(date_birth))//без этой проверки дата в таком виде 2/21/22 а должна 21/02/2022
//    	         {
//    	             DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
//    	             Date date = date_birth.getDateCellValue();
//    	             date_birthh = df.format(date);
//    	          }
//    	          
//    	    	 Cell issued_by=sh.getRow(i).getCell(11);
//    	    	 Cell date_issue=sh.getRow(i).getCell(12);
//    	    	 Cell validity_expire=sh.getRow(i).getCell(13);
//    	    	 if(HSSFDateUtil.isCellDateFormatted(date_issue))//без этой проверки дата в таком виде 2/21/22 а должна 21/02/2022
//    	         {
//    	             DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
//    	             Date date = date_issue.getDateCellValue();
//    	             date_issuee = df.format(date);
//    	          } 
//    	    	 if(HSSFDateUtil.isCellDateFormatted(validity_expire))//без этой проверки дата в таком виде 2/21/22 а должна 21/02/2022
//    	         {
//    	             DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
//    	             Date date = validity_expire.getDateCellValue();
//    	             validity_expiree = df.format(date);
//    	          }
//    	    	 
//    	    	 Cell organization_code=sh.getRow(i).getCell(14) ;
//    	    	 Cell type_card=sh.getRow(i).getCell(15);
//    	    	 Cell phone =sh.getRow(i).getCell(16);
//    	    	 Cell region =sh.getRow(i).getCell(17);
//    	    	 Cell district=sh.getRow(i).getCell(18);
//    	    	 Cell address=sh.getRow(i).getCell(19);
//    	    	 Cell acc_group=sh.getRow(i).getCell(20);
//    	    	
//    	    	 String	pinfll= formatter.formatCellValue(pinfl);   
//    	    	 String firstnamee= formatter.formatCellValue(firstname) ;
//    	    	 String	lastnamee = formatter.formatCellValue(lastname);
//    	    	 String	patronymicc = formatter.formatCellValue(patronymic);
//    	    	 String	nationalityy = formatter.formatCellValue(nationality);
//    	    	 String	citizenshipp= formatter.formatCellValue(citizenship);
//    	    	 String	passport_seriall= formatter.formatCellValue(passport_serial); 
//    	    	 String	passport_numberr = formatter.formatCellValue(passport_number);    	    	
//    	    	 String code_genderr= formatter.formatCellValue(code_gender);
//    	    	 String birth_placee= formatter.formatCellValue(birth_place) ;
//    	    	 String issued_byy= formatter.formatCellValue(issued_by);    	 
//    	    	 String	organization_codee= formatter.formatCellValue(organization_code) ;
//    	    	 String	type_cardd= formatter.formatCellValue(type_card);
//    	    	 String	phonee = formatter.formatCellValue(phone);
//    	    	 String regionn= formatter.formatCellValue(region) ;
//    	    	 String	districtt= formatter.formatCellValue(district);
//    	    	 String	aaddress= formatter.formatCellValue(address);
//    	    	 String	 acc_groupp= formatter.formatCellValue(acc_group);
//    	    	 
//    	    	 clientNotResidents.add((i-1), new ClientNotResident(
//	    			 		pinfll, 
//	    				 	firstnamee, 
//	    					lastnamee,
//	    					patronymicc, 
//	    					nationalityy, 
//	    				citizenshipp,
//	    				passport_seriall, 
//	    			passport_numberr, 
//	    					date_birthh,
//	    					code_genderr,
//	    			 	birth_placee, 
//	    				 	issued_byy,
//	    				 	date_issuee,
//	    				validity_expiree,
//	    					organization_codee, 
//	    					type_cardd,
//	    					phonee, 
//	    				 	regionn,
//	    					districtt,
//	    					aaddress,acc_groupp));	   
//    	    }
//    	    Client_mass_opening_fileService сlientNotResidentInsert = new Client_mass_opening_fileService();
//            сlientNotResidentInsert.insertClientNotResident(clientNotResidents, id_file);
//            
//    	   
//    	    for (int j = 0; j < clientNotResidents.size(); j++) {
//    	    	//----PINFL---- 
//   	    	 
//   	    	
//   	    	 if(clientNotResidents.get(j).getPinfl().isEmpty()) {	    	   	     		
//   	     		errorMessage.add( new ErrorMessages("В записи №"+j+"содержится ошибка (код ошибки 1: Поле не может быть пустым поля ДАТА )."
//   	     		+ " Исправьте данные или удалите запись.» "));   
//   	    	 }
//   	    	 if((!Pattern.matches("^[0-9]+",clientNotResidents.get(j).getPinfl()))) {
//                 errorMessage.add( new ErrorMessages("В записи №"+j+"содержится ошибка (код ошибки 2 : Неверный формат поля ПИНФЛ )."
//                         + " Исправьте данные или удалите запись.» "));
//         	}
//         	if(clientNotResidents.get(j).getPinfl().length()!=14){
//         		errorMessage.add( new ErrorMessages("В записи №"+j+"содержится ошибка (код ошибки 3: Неверный размер поля ПИНФЛ )."+ " Исправьте данные или удалите запись.» "));
//         	}
//         	//----fio
// 	    	 
//         	if(clientNotResidents.get(j).getFirstname().isEmpty()) {
//         		errorMessage.add( new ErrorMessages("В записи №"+j+"содержится ошибка (код ошибки 1: Поле не может быть пустым поля ФИО )."+ " Исправьте данные или удалите запись.» "));
//         	} if((!Pattern.matches("^[a-z A-Z]+",clientNotResidents.get(j).getFirstname()))) {
//                     errorMessage.add( new ErrorMessages("В записи №"+j+"содержится ошибка (код ошибки 2: Неверный формат поля ФИО )."
//                         + " Исправьте данные или удалите запись.» "));
//         	}
//         	if(clientNotResidents.get(j).getFirstname().length()<2){
//         		errorMessage.add( new ErrorMessages("В записи №"+j+"содержится ошибка (код ошибки 3: Неверный размер поля ФИО )."+ " Исправьте данные или удалите запись.» "));
//         	}
//         	//----Last name----
//         	
//         	if(clientNotResidents.get(j).getLastname().isEmpty()) {
//         		errorMessage.add( new ErrorMessages("В записи №"+j+"содержится ошибка (код ошибки 1: Поле не может быть пустым поля Имя )."+ " Исправьте данные или удалите запись.» "));
//         	} if((!Pattern.matches("^[a-z A-Z]+",clientNotResidents.get(j).getLastname()))) {
//                     errorMessage.add( new ErrorMessages("В записи №"+j+"содержится ошибка (код ошибки 2: Неверный формат поля Имя )."
//                         + " Исправьте данные или удалите запись.» "));
//         	}
//         	if(clientNotResidents.get(j).getLastname().length()<2){
//         		errorMessage.add( new ErrorMessages("В записи №"+j+"содержится ошибка (код ошибки 3: Неверный размер поля Имя )."+ " Исправьте данные или удалите запись.» "));
//         	}
//         	
//         	//-----Nationality
//         	if(clientNotResidents.get(j).getNationality().isEmpty()) {
//         		errorMessage.add( new ErrorMessages("В записи №"+j+"содержится ошибка (код ошибки 1: Поле не может быть пустым поля Национальность )."+ " Исправьте данные или удалите запись.» "));
//         	} if((!Pattern.matches("^[0-9]+",clientNotResidents.get(j).getNationality()))) {
//                     errorMessage.add( new ErrorMessages("В записи №"+j+"содержится ошибка (код ошибки 2: Неверный формат поля Национальность )."
//                         + " Исправьте данные или удалите запись.» "));
//         	}
//         	if(clientNotResidents.get(j).getNationality().length()>4){
//         		errorMessage.add( new ErrorMessages("В записи №"+j+"содержится ошибка (код ошибки 3: Неверный размер поля Национальность )."+ " Исправьте данные или удалите запись.» "));
//         	}
//         	//------Citizenship
//         	if(clientNotResidents.get(j).getCitizenship().isEmpty()) {
//         		errorMessage.add( new ErrorMessages("В записи №"+j+"содержится ошибка (код ошибки 1: Поле не может быть пустым поля Гражданство )."+ " Исправьте данные или удалите запись.» "));
//         	} if((!Pattern.matches("^[0-9]+",clientNotResidents.get(j).getCitizenship()))) {
//                     errorMessage.add( new ErrorMessages("В записи №"+j+"содержится ошибка (код ошибки 2: Неверный формат поля Гражданство )."
//                         + " Исправьте данные или удалите запись.» "));
//         	}
//         	//if(clientNotResidents.get(j).getCitizenship().length()<){
//         		//errorMessage.add( new ErrorMessages("В записи №"+j+"содержится ошибка (код ошибки 3: Неверный размер поля Гражданство )."+ " Исправьте данные или удалите запись.» "));
//         	//}
//         	//------Passport_serial
//         	if(clientNotResidents.get(j).getPassport_serial().isEmpty()) {
//         		errorMessage.add( new ErrorMessages("В записи №"+j+"содержится ошибка (код ошибки 1: Поле не может быть пустым поля Серия паспорта )."+ " Исправьте данные или удалите запись.» "));
//         	} if((!Pattern.matches("^[a-z A-Z]+",clientNotResidents.get(j).getPassport_serial()))) {
//                     errorMessage.add( new ErrorMessages("В записи №"+j+"содержится ошибка (код ошибки 2: Неверный формат поля Серия паспорта )."
//                         + " Исправьте данные или удалите запись.» "));
//         	}
//         	if(clientNotResidents.get(j).getPassport_serial().length()>3){
//         		errorMessage.add( new ErrorMessages("В записи №"+j+"содержится ошибка (код ошибки 3: Неверный размер поля Серия паспорта )."+ " Исправьте данные или удалите запись.» "));
//         	}        	
//         	//---------Passport_number            	
//         	if(clientNotResidents.get(j).getPassport_number().isEmpty()) {
//         		errorMessage.add( new ErrorMessages("В записи №"+j+"содержится ошибка (код ошибки 1: Поле не может быть пустым поля Номер паспорта )."+ " Исправьте данные или удалите запись.» "));
//         	} if((!Pattern.matches("^[0-9]+",clientNotResidents.get(j).getPassport_number()))) {
//                     errorMessage.add( new ErrorMessages("В записи №"+j+"содержится ошибка (код ошибки 2: Неверный формат поля Номер паспорта )."
//                         + " Исправьте данные или удалите запись.» "));
//         	}
//         	if(clientNotResidents.get(j).getPassport_number().length()>9){
//         		System.out.println("Passport number: - "+clientNotResidents.get(j).getPassport_number());
//         		errorMessage.add( new ErrorMessages("В записи №"+j+"содержится ошибка (код ошибки 3: Неверный размер поля Номер паспорта )."+ " Исправьте данные или удалите запись.» "));
//         	}          	            	
//         	//--------Date_birth
//         	if(clientNotResidents.get(j).getDate_birth().isEmpty()) {
//         		errorMessage.add( new ErrorMessages("В записи №"+j+"содержится ошибка (код ошибки 1: Поле не может быть пустым поля Дата рождения )."+ " Исправьте данные или удалите запись.» "));
//         	}
//         	 if((!Pattern.matches("^\\d{2}.\\d{2}.\\d{4}$",clientNotResidents.get(j).getDate_birth()))) {
// 	             errorMessage.add( new ErrorMessages("В записи №"+j+"содержится ошибка (код ошибки 2: Неверный формат поля Дата рождения )."
// 	                 + " Исправьте данные или удалите запись.» "));
// 	    	}
//         	 //---------Code_gender
//         	 if(clientNotResidents.get(j).getCode_gender().isEmpty()) {
//          		errorMessage.add( new ErrorMessages("В записи №"+j+"содержится ошибка (код ошибки 1: Поле не может быть пустым поля Пол )."+ " Исправьте данные или удалите запись.» "));
//          	}
//         	 if(!clientNotResidents.get(j).getCode_gender().equals("1")&&!clientNotResidents.get(j).getCode_gender().equals("2")) {
//          		
//          		errorMessage.add( new ErrorMessages("В записи №"+j+"содержится ошибка (код ошибки 4: Неверное содержание поля  Пол )."+ " Исправьте данные или удалите запись.» "));
//         	 }
//         	 //--------Birth_place
//         	 if(clientNotResidents.get(j).getBirth_place().isEmpty()) {
//          		errorMessage.add( new ErrorMessages("В записи №"+j+"содержится ошибка (код ошибки 1: Поле не может быть пустым поля Место рождения )."+ " Исправьте данные или удалите запись.» "));
//          	} 
//          	if(clientNotResidents.get(j).getBirth_place().length()<4){
//          		errorMessage.add( new ErrorMessages("В записи №"+j+"содержится ошибка (код ошибки 3: Неверный размер поля Место рождения )."+ " Исправьте данные или удалите запись.» "));
//          	} 
//         	//--------Issued_by
//          	if(clientNotResidents.get(j).getIssued_by().isEmpty()) {
//          		errorMessage.add( new ErrorMessages("В записи №"+j+"содержится ошибка (код ошибки 1: Поле не может быть пустым поля Кем выдано )."+ " Исправьте данные или удалите запись.» "));
//          	} 
//          
// 	    	//------------Date_issue
//          	
//          	if(clientNotResidents.get(j).getDate_issue().isEmpty()) {
//         		errorMessage.add( new ErrorMessages("В записи №"+j+"содержится ошибка (код ошибки 1: Поле не может быть пустым поля Дата выдачи )."+ " Исправьте данные или удалите запись.» "));
//         	}
//         	 if((!Pattern.matches("^\\d{2}.\\d{2}.\\d{4}$",clientNotResidents.get(j).getDate_issue()))) {
// 	             errorMessage.add( new ErrorMessages("В записи №"+j+"содержится ошибка (код ошибки 2: Неверный формат поля Дата выдачи )."
// 	                 + " Исправьте данные или удалите запись.» "));
// 	    	}
////---------Validity_expire
//
//           	if(clientNotResidents.get(j).getValidity_expire().isEmpty()) {
//          		errorMessage.add( new ErrorMessages("В записи №"+j+"содержится ошибка (код ошибки 1: Поле не может быть пустым поля Срок действия )."+ " Исправьте данные или удалите запись.» "));
//          	}
//          	 if((!Pattern.matches("^\\d{2}.\\d{2}.\\d{4}$",clientNotResidents.get(j).getValidity_expire()))) {
//  	             errorMessage.add( new ErrorMessages("В записи №"+j+"содержится ошибка (код ошибки 2: Неверный формат поля Срок действия )."
//  	                 + " Исправьте данные или удалите запись.» "));
//  	    	} 
//          	//------ Organization_code----
//          	 if(clientNotResidents.get(j).getOrganization_code().isEmpty()) {	    	
// 	     		
// 	     		errorMessage.add( new ErrorMessages("В записи №"+j+"содержится ошибка (код ошибки 1: Поле не может быть пустым поля Код организации в АБС )."+ " Исправьте данные или удалите запись.» "));
// 	     		
// 	     	}if((!Pattern.matches("^[0-9]+",clientNotResidents.get(j).getOrganization_code()))) {
//                 errorMessage.add( new ErrorMessages("В записи №"+j+"содержится ошибка (код ошибки 2: Неверный формат поля Код организации в АБС )."
//                         + " Исправьте данные или удалите запись.» "));
//         	}
//         	if(clientNotResidents.get(j).getOrganization_code().length()!=8){
//         		errorMessage.add( new ErrorMessages("В записи №"+j+"содержится ошибка (код ошибки 3: Неверный размер поля Код организации в АБС )."+ " Исправьте данные или удалите запись.» "));
//         	}
//         	//-----ВИД КАРТЫ-----
//         	
//         	if(clientNotResidents.get(j).getType_card().isEmpty()) {	    		
//         	    		errorMessage.add( new ErrorMessages("В записи №"+j+"содержится ошибка (код ошибки 1: Поле не может быть пустым поля ВИД КАРТЫ )."+ " Исправьте данные или удалите запись.» "));
//         	    	}
//         	
//         	if(!clientNotResidents.get(j).getType_card().equals("1")&&!clientNotResidents.get(j).getType_card().equals("2")&&!clientNotResidents.get(j).getType_card().equals("3")) {
//         	    		
//         	    		errorMessage.add( new ErrorMessages("В записи №"+j+"содержится ошибка (код ошибки 4: Неверное содержание поля  ВИД КАРТЫ- )."+ " Исправьте данные или удалите запись.» "));
//         	}
//         	 
//         	//-----PHONE-------
//         	if(clientNotResidents.get(j).getPhone().isEmpty()) {
//         		
//         		errorMessage.add( new ErrorMessages("В записи №"+j+"содержится ошибка (код ошибки 1: Поле не может быть пустым поля ТЕЛЕФОН )."+ " Исправьте данные или удалите запись.» "));
//         	}
//         	if((!Pattern.matches("^[0-9]+",clientNotResidents.get(j).getPhone()))) {
//                     errorMessage.add( new ErrorMessages("В записи №"+j+"содержится ошибка (код ошибки 2: Неверный формат поля ТЕЛЕФОН )."
//                         + " Исправьте данные или удалите запись.» "+clientNotResidents.get(j).getPhone()+""));
//         	}
//         	if(clientNotResidents.get(j).getPhone().length()!=12){
//         		errorMessage.add( new ErrorMessages("В записи №"+j+"содержится ошибка (код ошибки 3: Неверный размер поля ТЕЛЕФОН )."+ " Исправьте данные или удалите запись.» "));
//         	}
// 	    	 //-------Region
//         	if(clientNotResidents.get(j).getRegion().isEmpty()) {
//         		
//         		errorMessage.add( new ErrorMessages("В записи №"+j+"содержится ошибка (код ошибки 1: Поле не может быть пустым поля Регион )."+ " Исправьте данные или удалите запись.» "+clientNotResidents.get(j).getRegion()+""));
//         	}
//         	if((!Pattern.matches("^[0-9]+",clientNotResidents.get(j).getRegion()))) {
//                 errorMessage.add( new ErrorMessages("В записи №"+j+"содержится ошибка (код ошибки 2: Неверный формат поля Регион )."
//                     + " Исправьте данные или удалите запись.» "+clientNotResidents.get(j).getRegion()+""));
//     	}
//
//         	//------district
//         	
//         	if(clientNotResidents.get(j).getDistrict().isEmpty()) {            		
//         		errorMessage.add( new ErrorMessages("В записи №"+j+"содержится ошибка (код ошибки 1: Поле не может быть пустым поля Район )."+ " Исправьте данные или удалите запись.» "));
//         	}
//         	if((!Pattern.matches("^[0-9]+",clientNotResidents.get(j).getDistrict()))) {
//                 errorMessage.add( new ErrorMessages("В записи №"+j+"содержится ошибка (код ошибки 2: Неверный формат поля Район )."
//                     + " Исправьте данные или удалите запись.» "));
//     	}
//         //-Address
//         	if(clientNotResidents.get(j).getAddress().isEmpty()) {
//          		errorMessage.add( new ErrorMessages("В записи №"+j+"содержится ошибка (код ошибки 1: Поле не может быть пустым поля Адрес )."+ " Исправьте данные или удалите запись.» "));
//          	}
//         	//--КОД ГРУППЫ
//         	if(clientNotResidents.get(j).getAcc_group().isEmpty()) {	    	   	     		
//   	     		errorMessage.add( new ErrorMessages("В записи №"+j+"содержится ошибка (код ошибки 1: Поле не может быть пустым поля КОД ГРУППЫ )."
//   	     		+ " Исправьте данные или удалите запись.» "));   
//   	    	 }
//   	    	 if((!Pattern.matches("^[0-9]+",clientNotResidents.get(j).getAcc_group()))) {
//                 errorMessage.add( new ErrorMessages("В записи №"+j+"содержится ошибка (код ошибки 2 : Неверный формат поля КОД ГРУППЫ )."
//                         + " Исправьте данные или удалите запись.» "));
//         	}
//          	 	
//    	    }	
//    	    	
//    	    	 label = new Label();
//                  label.setValue("");
//                  label.setStyle("font-weight:bold");
//                  label.setWidth("30px");
//                  label.setStyle("font-weight:bold");	
//                  label.setMultiline(true);
//                  separator = new Separator();	
//                  if(errorMessage.size()>0) {
//      	    		String fullErrorNotResident="";
//      	    for (int i = 0; i < errorMessage.size(); i++) {
//      	    	fullErrorNotResident+=errorMessage.get(i).getError_message()+"\n";
//  			}
//      	    label.setValue(fullErrorNotResident);
//      	    }else {
//      	    	 label.setValue("Все "+clientNotResidents.size()+" записей внесены верно. Подтвердите загрузку файла в модуль.");
//  			}    	    		      
//            
//                  modalDialogNE.appendChild(label);  	    		                   
//                  modalDialogNE.appendChild(separator);
//                  modalDialogNE.setVisible(true);
//                  
//modalDialogNE$name_file.setValue(media.getName());
//modalDialogNE$date_file.setValue(sdf.format(System.currentTimeMillis()));
//modalDialogNE$status_file.setValue("Hовый");
////refreshModel(_startPageNumber);
//}
//
//
//else { 	
//System.out.println("csv");
//byte[] bytes = IOUtils.toByteArray(in);
//
//File in_file = new File(bytes.toString());
//String line;
//FileReader fileReader;
//fileReader = new FileReader(in_file);
//BufferedReader bufferedReader = new BufferedReader(fileReader);
//String[] lines;
//int i = 0;
//while ((line = bufferedReader.readLine()) != null){
//lines = line.split(",");
//System.out.println(Arrays.toString(lines));
//
//
//}
//fileReader.close();
//}			 
//    	
//		    }catch (Exception e) {
//				e.printStackTrace();
//			}
//}

public void onClick$btn_ignore$modalDialog() {
	windowMessageReject.setVisible(true);
	
	}
	


public void onClick$btn_accept$modalDialog() {
	windowMessageConfirm.setVisible(true);
	}


public void onClick$btn_ignore$modalDialogNE() {
	windowMessageRejectNE.setVisible(true);
	}
	


public void onClick$btn_accept$modalDialogNE() {
	windowMessageConfirmNE.setVisible(true);
	
	}

public void onClick$windowMessage_confirm$windowMessageConfirm() {//резиденты
	windowMessageConfirm.setVisible(false);
	
	/*	
	try {
		 List< CheckAccountResident> checkAccountResidents ;
	Client_mass_opening_fileService service =new Client_mass_opening_fileService();	
	 List<ClientResident> clientResident = new ArrayList<ClientResident>();	
	 ResponceFromNibbd responceNibbd = new ResponceFromNibbd();
	 boolean init ;
	 String resultCode ;
	 Res action= new Res();
	 service.update(Integer.parseInt(id_file),"2");//меняем статус файла на в обработке
	clientResident=service.getClient_mass_opening_clientResident(id_file);//проверяем валидность полей в банке,потом выбираем все валидные и подставляем в проверку
	for (ClientResident clientResiden : clientResident) {
		//System.out.println("Last Name: "+clientResiden.getLastname());
		checkAccountResidents=service.checkAccountResident("1","08",clientResiden.getPinfl());
	if(checkAccountResidents==null) {
		ISLogger.getLogger().error("КЛИЕНТ НАЙДЕН  С ПИНФЛ: "+clientResiden.getPinfl()+ " И ИМЯ: "+clientResiden.getLastname());
		
		
		//вызов процедуры открытия карты 
		
	//	вытащить селектом нужныЕ и после записать в лист и дальше передать 
	}else {
		service.getUzCard(un,pw,alias,branch,clientResiden);
		ISLogger.getLogger().error("КЛИЕНТ НЕ НАЙДЕН С  ПИНФЛ: "+clientResiden.getPinfl()+ "  И ИМЯ: "+clientResiden.getLastname());
	//тут вызываем метод идентификации для открытия чтобы получить данные и подставить в процедуру  
		responceNibbd=getRestFromNibbd(clientResiden.getPinfl(),"014",branch,"1");
		
		//if(responceNibbd.getResultCode().equals("0")) {
			String[] parts = responceNibbd.getResult_sql().split(" ");
			String resulSql = parts[7];
			getFromNibbd =service.getSsi_physical(resulSql);//берем из таблицы Ssi_physical от сюда получаем id и вытаскиваем нужных клиентов и ложим в процедуру
	
		//}
			action=	service.doAction(un,pw,alias,branch,getFromNibbd);//процедура открытия клиента
			if(action.getCode()==0){
				service.doActions(un,pw,alias,branch,getFromNibbd);
				
			}else {
				alert(getFromNibbd.getPin()+" \n "+action.getName()+"!");
			}
		}
	
	}
	
	
	
	label.setValue("");
	modalDialog.removeChild(label);
	modalDialog.removeChild(separator);
	modalDialog.setVisible(false);
	alert("Успешно!");
	

	}catch (Exception e) {
        e.printStackTrace();
        ISLogger.getLogger().error("MASSOPEN: residents -  "+e);
	}
	*/
}



public void onClick$windowMessage_confirmNE$windowMessageConfirmNE() {//нерезиденты
	windowMessageConfirmNE.setVisible(false);
	/*	
	try {
	Client_mass_opening_fileService service =new Client_mass_opening_fileService();	
	 List<ClientNotResident> clientNoResident = new ArrayList<ClientNotResident>();	

	 List< CheckAccountResident> checkAccountResidents = new ArrayList<CheckAccountResident>() ;
	 Res action= new Res();
	 Res actions= new Res();
	 service.update(Integer.parseInt(id_file),"2");//меняем статус файла на в обработке(2)
	 clientNoResident=service.getClient_mass_opening_clientNotResident(id_file);//проверяем валидность полей в банке,потом выбираем все валидные и подставляем в проверку
	  if(!clientNoResident.isEmpty()) {
	 
	for (ClientNotResident clientNotResiden : clientNoResident) {
		checkAccountResidents=service.checkAccountResident("2","08",clientNotResiden.getPinfl());
	
	if(checkAccountResidents==null) {
		ISLogger.getLogger().error("КЛИЕНТ НЕРЕЗИДЕНТ НАЙДЕН:  C ПИНФЛ- "+clientNotResiden.getPinfl()+" ИМЯ "+clientNotResiden.getFirstname());
		if(clientNotResiden.getType_card().equals("1")) {//определяем если это узкард  1
			//номер счета узнать
			
		service.getUzCardNE(un,pw,alias,branch,clientNotResiden);
		
		}else {
			//тут должен быть метод открытия карты в humo с типом карты 2
		}
	} else {
	
		ISLogger.getLogger().error("КЛИЕНТ НЕРЕЗИДЕНТ НЕ НАЙДЕН:  C ПИНФЛ- "+clientNotResiden.getPinfl()+" ИМЯ "+clientNotResiden.getFirstname());
	//тут вызываем метод идентификации для открытия чтобы получить данные и подставить в процедуру  
		
	action=	service.doActionNE(un,pw,alias,branch,clientNotResiden);//процедура открытия клиентa
	ISLogger.getLogger().error("Action not client mass: "+action.getCode());
	
	if(action.getCode()==0){
		actions=service.doActionsNE(un,pw,alias,branch,clientNotResiden);
		ISLogger.getLogger().error("Actions not client mass: "+actions.getCode());
	if(actions.getCode()==0) {
		if(clientNotResiden.getType_card().equals("1")) {//определяем если это узкард  1
		service.getUzCardNE(un,pw,alias,branch,clientNotResiden);
		}else {	
			//тут должен быть метод открытия карты в humo с типом карты 2
		}
	}else {
		String text="File_id: "+id_file+"\n"+"ОТКРЫТИЕ СЧЕТА ОШИБКА: "+actions.getCode()+"! \n"+" КЛИЕНТ С ПИНФЛОМ: "+clientNotResiden.getPinfl()+" \n"+actions.getName()+"!";
		ISLogger.getLogger().error(text);
		alert(text);
		
	}
	}else {
		String text="File_id: "+id_file+"\n"+"ОТКРЫТИЕ КЛИЕНТА ОШИБКА:  "+action.getCode()+"! \n"+" КЛИЕНТ С ПИНФЛОМ: "+clientNotResiden.getPinfl()+" \n"+action.getName()+"!";
		ISLogger.getLogger().error(text);
		alert(text);
	}
	}//
	}//for
	  }else {
		  
		  label.setValue("");
			modalDialogNE.removeChild(label);
			modalDialogNE.removeChild(separator);
			modalDialogNE.setVisible(false);
			
			alert("Файл не принят так как не прошел проверку контроля полей! \n "+"Смотреть- список файлов"); 
			return;
	  }
	  
	  label.setValue("");
		modalDialogNE.removeChild(label);
		modalDialogNE.removeChild(separator);
		modalDialogNE.setVisible(false);
		
		alert("Нерезиденты.Файл успешно принят!");

	}catch (Exception e) {
		ISLogger.getLogger().error("MASSOPEN: notresidents -  "+e);
        e.printStackTrace();
	}
	*/
}
public void onClick$windowMessage_reject$windowMessageConfirm() {
	windowMessageConfirm.setVisible(false);
	// код при подтверждении
}

public void onClick$windowMessage_reject$windowMessageReject() {
	// код при отклоении
	windowMessageReject.setVisible(false);
}

public void onClick$windowMessage_confirm$windowMessageReject() {
	windowMessageReject.setVisible(false);
	onClick$windowMessage_confirm$windowMessageConfirm();
	
}

public void onClick$windowMessage_rejectNE$windowMessageConfirmNE() {
	windowMessageConfirmNE.setVisible(false);
	System.out.println("Message_rejectNE$windowMessageConfirmNE");
	
	// код при подтверждении
}

public void onClick$windowMessage_rejectNE$windowMessageRejectNE() {
	windowMessageRejectNE.setVisible(false);
	// код при отклоении
	
}

public void onClick$windowMessage_confirmNE$windowMessageRejectNE() {
	windowMessageRejectNE.setVisible(false);
	
	onClick$windowMessage_confirmNE$windowMessageConfirmNE();
	
	
	
}



public void onClick$btn_cancel1$modalDialog() {

	label.setValue("");
	modalDialog.removeChild(label);
	modalDialog.removeChild(separator);
	modalDialog.setVisible(false);
	
}
public void onClick$btn_cancel1$modalDialogNE() {

	label.setValue("");
	modalDialogNE.removeChild(label);
    modalDialogNE.removeChild(separator);
	modalDialogNE.setVisible(false);
	
	
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

public void onClick$btn_report() throws IOException {
	  String inputFile =application.getRealPath("/reports/Example.xlsx");
	  AMedia repmd = Client_mass_opening_fileService.getExcel(inputFile);
	  Filedownload.save(repmd);
	}


public void onClick$btn_reportNE()throws IOException {
	  String inputFile =application.getRealPath("/reports/Example.xlsx");
	  AMedia repmd = Client_mass_opening_fileService.getExcel(inputFile);
	  Filedownload.save(repmd);
	}
public void onClick$btn_back() {
    if (frm.isVisible()){
        frm.setVisible(false);
        grd.setVisible(true);
        btn_back.setImage("/images/file.png");
        btn_back.setLabel(Labels.getLabel("back"));
    }else onDoubleClick$dataGrid$grd();
}
}