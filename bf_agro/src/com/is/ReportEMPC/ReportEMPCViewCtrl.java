package com.is.ReportEMPC;

import java.io.ByteArrayOutputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;


import com.is.utils.CheckNull;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.zkoss.util.media.AMedia;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Filedownload;
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


import com.is.ReportEMPC.*;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.RefData;
import com.is.utils.RefRenderer;

@SuppressWarnings("serial")
public class ReportEMPCViewCtrl extends GenericForwardComposer{
        private Div frm;
        private Paging contactPaging;
        private Div grd;
        private Listbox dataGrid;
        private Grid addgrd,frmgrd,fgrd;
        private Toolbarbutton btn_last;
        private Toolbarbutton btn_next;
        private Toolbarbutton btn_prev;
        private Toolbarbutton btn_first;
        private Toolbarbutton btn_add;
        private Toolbarbutton btn_search;
        private Toolbarbutton btn_back;
        private Toolbar tb;
        private Textbox branch,id,acc_bal,currency,client,id_order,name,sgn,bal,sign_registr,s_in,s_out,dt,ct,s_in_tmp,s_out_tmp,dt_tmp,ct_tmp,l_date,date_open,date_close,acc_group_id,state;
        private Textbox abranch,aid,aacc_bal,acurrency,aclient,aid_order,aname,asgn,abal,asign_registr,as_in,as_out,adt,act,as_in_tmp,as_out_tmp,adt_tmp,act_tmp,al_date,adate_open,adate_close,aacc_group_id,astate ;
        private Textbox fbranch,fid,facc_bal,fcurrency,fclient,fid_order,fname,fsgn,fbal,fsign_registr,fs_in,fs_out,fdt,fct,fs_in_tmp,fs_out_tmp,fdt_tmp,fct_tmp,fl_date,fdate_open,fdate_close,facc_group_id,fstate ;
        private Paging accountPaging;
        private  int _pageSize = 15;
        private int _startPageNumber = 0;
        private int _totalSize = 0;
        private boolean _needsTotalSizeUpdate = true;

        private Textbox bank, terminal, terminal_name, tran_name;
        private Datebox period_begin, period_end;  
        
        public ReportEMPC filter = null;

        PagingListModel model = null;
        ListModelList lmodel =null;
        private AnnotateDataBinder binder;
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        private String alias, branch1;


    public ReportEMPC current = new ReportEMPC();

        public ReportEMPCViewCtrl() {
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
        branch1 = (String) session.getAttribute("branch");
        if (parameter!=null){
                _pageSize = Integer.parseInt( parameter[0])/36;
                //dataGrid.setRows(Integer.parseInt( parameter[0])/36);
        }

        filter = new ReportEMPC();
        parameter = (String[]) param.get("search_clients");
		if (parameter != null) {
			//filter.setClient(parameter[0]);
		}
           /* dataGrid.setItemRenderer(new ListitemRenderer(){
        @SuppressWarnings("unchecked")
        public void render(Listitem row, Object data) throws Exception {
                    ReportEMPC pAccount = (Movie) data;

                    row.setValue(pAccount);
                    
                    //row.appendChild(new Listcell(pAccount.getBranch()));
                    //row.appendChild(new Listcell(pAccount.getId()));
                    //row.appendChild(new Listcell(pAccount.getName()));
                    //row.appendChild(new Listcell(pAccount.getS_out()/100+""));
                    //row.appendChild(new Listcell(pAccount.getState()+""));


        }});*/

        refreshModel(_startPageNumber);

    }

public void onPaging$accountPaging(ForwardEvent event){
    final PagingEvent pe = (PagingEvent) event.getOrigin();
    _startPageNumber = pe.getActivePage();
    refreshModel(_startPageNumber);
}


private void refreshModel(int activePage){
    //accountPaging.setPageSize(_pageSize);
    model = new PagingListModel(activePage, _pageSize,filter,alias);

    if(_needsTotalSizeUpdate) {
            _totalSize = model.getTotalSize(filter, alias);
           // _needsTotalSizeUpdate = false;
    }

    //accountPaging.setTotalSize(_totalSize);

    //dataGrid.setModel((ListModel) model);
    //if (model.getSize()>0){
    //this.current =(Movie) model.getElementAt(0);
    //sendSelEvt();
    //}
}



// Omitted...
public ReportEMPC getCurrent() {
    return current;
}

public void onClick$qoshgin_endi() {
	//List<AccountForm> list = FileService.getReport1(reportWindow$in_date.getValue(), reportWindow$rep_acc.getValue()!=null ? reportWindow$rep_acc.getValue() : "%" , "");
	List<ReportEMPC> maningRuyxatim = ReportEMPCService.getRuyxatReportEMPC(alias);
	
	//alert("Hello World");
	Workbook workbook = new HSSFWorkbook();
	Sheet listSheet = workbook.createSheet("list_1");
	Row row = null;
	CellStyle style;
	style = workbook.createCellStyle();
	style.setDataFormat((short)0x4); // built-in number format
	Cell cell;
	Double vSumma = 0D;
	int rowIndex = 0;
	int cellIndex = 0;
	row = listSheet.createRow(rowIndex);
	//String res= AccountFormService.select_info(current);
	//row = listSheet.createRow(rowIndex++);//row = listSheet.createRow(2);
	
	row.createCell(0).setCellValue("1:1");
	row = listSheet.createRow(rowIndex++);//row = listSheet.createRow(2);
	row.createCell(0).setCellValue("2:1");
	rowIndex++;////rowIndex = 3;
	row = listSheet.createRow(rowIndex++);//row = listSheet.createRow(4);
	row.createCell(0).setCellValue("4:1");
	row = listSheet.createRow(rowIndex++);//row = listSheet.createRow(5);
	row.createCell(0).setCellValue("5:1");
	rowIndex++;//rowIndex = 6;		
	row = listSheet.createRow(rowIndex++);//row = listSheet.createRow(7);
	row.createCell(0).setCellValue("7:1");

	rowIndex = 9;
	row = listSheet.createRow(rowIndex++);//row = listSheet.createRow(10);
	cellIndex = 0;
	row.createCell(cellIndex++).setCellValue("10:1");//rowIndex=10, cellIndex=1;
	row.createCell(cellIndex++).setCellValue("10:2");//rowIndex=10, cellIndex=2;
	row.createCell(cellIndex++).setCellValue("10:3");//rowIndex=10, cellIndex=3;
	row.createCell(cellIndex++).setCellValue("10:4");//rowIndex=10, cellIndex=4;

	
	for (int i = 0; i < maningRuyxatim.size(); i++) {
		row = listSheet.createRow(rowIndex++);
		cellIndex = 0;
		row.createCell(cellIndex).setCellValue(maningRuyxatim.get(i).getTran_name());
		cellIndex++;
		row.createCell(cellIndex).setCellValue(maningRuyxatim.get(i).getTerminal_name());
		
	}
	


	listSheet.autoSizeColumn(0);
	listSheet.autoSizeColumn(1);
	listSheet.autoSizeColumn(2);
	listSheet.autoSizeColumn(3);		
	
	try {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		workbook.write(baos);
		AMedia amedia = new AMedia("report_empc.xls", "xls", "application/file", baos.toByteArray());
		Filedownload.save(amedia);
		baos.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
}

public void setCurrent(ReportEMPC current) {
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
    try{
            filter = new ReportEMPC();
            
              /*filter.setBranch(fbranch.getValue());
              filter.setId(fid.getValue());
              filter.setAcc_bal(facc_bal.getValue());
              filter.setCurrency(fcurrency.getValue());
              filter.setClient(fclient.getValue());
              filter.setId_order(fid_order.getValue());
              filter.setName(fname.getValue());*/
              
    onClick$btn_back();
    refreshModel(_startPageNumber);
    SelectEvent evt = new SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
    Events.sendEvent(evt);
    
    } catch (Exception e) {
        e.printStackTrace();
    }

}
public void onClick$btn_cancel() {
        if(fgrd.isVisible()){
                filter = new ReportEMPC();
        }
    onClick$btn_back();
    frmgrd.setVisible(true);
    addgrd.setVisible(false);
    fgrd.setVisible(false);
    CheckNull.clearForm(addgrd);
    CheckNull.clearForm(fgrd);
    refreshModel(_startPageNumber);
}

public void onClick$btn_blk() {
	//String res =
	//HabibService.doAction(session.getAttribute("un").toString(), session.getAttribute("pwd").toString(), current.getBranch(), current.getId(), 4, alias,  branch1.compareTo("00444")==0);
	//if(!res.equals("")){
	//	alert(res);
	//}else
	//refreshModel(_startPageNumber);
}
public void onClick$btn_ublk() {
	//String res =
	//HabibService.doAction(session.getAttribute("un").toString(), session.getAttribute("pwd").toString(), current.getBranch(), current.getId(), 5, alias, branch1.compareTo("00444")==0);
	//if(!res.equals("")){
//		alert(res);
//	}else
//	refreshModel(_startPageNumber);
}

public void onClick$show() {
  ReportEMPC mov = new ReportEMPC();
  mov.setBank(bank.getValue());
  mov.setTerminal(terminal.getValue());
  mov.setTerminal_name(terminal_name.getValue());
  mov.setTran_name(tran_name.getValue());
  mov.setPeriod_begin(period_begin.getValue());
  mov.setPeriod_end(period_end.getValue());
  //mov.setTitle(kino_nom.getValue());
}

}
