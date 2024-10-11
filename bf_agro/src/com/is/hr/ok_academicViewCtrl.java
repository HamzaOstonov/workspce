package com.is.hr;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
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
import org.zkoss.zul.event.PagingEvent;

import com.is.utils.CheckNull;

public class ok_academicViewCtrl extends GenericForwardComposer {
	private Div frm;
	private Listbox dataGrid;
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
	private Textbox id, branch, personal_code, academic_code, academic_date, emp_code, ins_date, emp_code_name;
	private Textbox abranch, aemp_code_name;
	private Textbox aid, apersonal_code, aacademic_code, aemp_code, aacademic_date;
	private Datebox ains_date;
	private Textbox fid, fbranch, fpersonal_code, facademic_code, facademic_date, femp_code, fins_date, femp_code_name;
	private Combobox code;
	private String alias, un, pwd;
	private Paging ok_academicPaging;
	private int _pageSize = 15;
	private int _startPageNumber = 0;
	private int _totalSize = 0;
	private boolean _needsTotalSizeUpdate = true;
	public ok_academicFilter filter;
	DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
	Date date = new Date();
	PagingListModel_ok_academic model = null;
	ListModelList lmodel = null;
	private AnnotateDataBinder binder;
	private int ppid=0;

	private ok_academic current = new ok_academic();

	public ok_academicViewCtrl() {
		super('$', false, false);
	}
	/**
	 *
	 *
	 */
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		alias = ((String) session.getAttribute("alias"));
		un = ((String) session.getAttribute("un"));
		pwd = ((String) session.getAttribute("pwd"));
		binder = new AnnotateDataBinder(comp);
		binder.bindBean("current", this.current);
		binder.loadAll();
		String[] parameter = (String[]) param.get("ht");
		if (parameter != null) {
			_pageSize = Integer.parseInt(parameter[0]) / 36;
			dataGrid.setRows(Integer.parseInt(parameter[0]) / 36);
		}
		filter = new ok_academicFilter();
		parameter = (String[]) param.get("ppid");
	    if (parameter!=null){
	      ppid = Integer.parseInt(parameter[0]);
	      filter.setPersonal_code(ppid);
	    }
	    
	    filter.setPersonal_code(ppid);

		dataGrid.setItemRenderer(new ListitemRenderer() {
			@SuppressWarnings("unchecked")
			public void render(Listitem row, Object data) throws Exception {
				ok_academic pok_academic = (ok_academic) data;

				row.setValue(pok_academic);

				//row.appendChild(new Listcell(pok_academic.getId() + ""));
				row.appendChild(new Listcell(pok_academic.getBranch()));
				row.appendChild(new Listcell(pok_academic.getPersonal_code() + ""));
				row.appendChild(new Listcell(pok_academic.getAcademic_code() + ""));
				row.appendChild(new Listcell(pok_academic.getAcademic_date() + ""));
				row.appendChild(new Listcell(pok_academic.getEmp_code() + ""));
				row.appendChild(new Listcell(pok_academic.getIns_date() + ""));
				row.appendChild(new Listcell(pok_academic.getEmp_code_name()));

			}
		});

		refreshModel(_startPageNumber);
		dataGrid.setRows(dataGrid.getModel().getSize());

	}

	public void onPaging$ok_academicPaging(ForwardEvent event) {
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModel(_startPageNumber);
	}

	private void refreshModel(int activePage) {
		filter.setPersonal_code(ppid);
		ok_academicPaging.setPageSize(_pageSize);
		model = new PagingListModel_ok_academic(activePage, _pageSize, filter, "");

		if (_needsTotalSizeUpdate) {
			_totalSize = model.getTotalSize();
			_needsTotalSizeUpdate = false;
		}

		ok_academicPaging.setTotalSize(_totalSize);

		dataGrid.setModel((ListModel) model);
		if (model.getSize() > 0) {
			this.current = (ok_academic) model.getElementAt(0);
			sendSelEvt();
		}
	}

	public ok_academic getCurrent() {
		return current;
	}

	public void setCurrent(ok_academic current) {
		this.current = current;
	}

	public void onDoubleClick$dataGrid$grd() {
		filter.setPersonal_code(ppid);
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

	public void onClick$btn_save() throws Exception {
		 try{
		        if(addgrd.isVisible()){
		        	int a = code.getSelectedIndex() + 1;
		                ok_academicService.create(new ok_academic(
		                
		                Integer.parseInt(aid.getValue()),
		                abranch.getValue(),
		                Integer.parseInt(apersonal_code.getValue()),
		                a,
		                Integer.parseInt(aacademic_date.getValue()),
		                Integer.parseInt(aemp_code.getValue()),
		                ains_date.getValue(),
		                aemp_code_name.getValue()
		                ));
		            CheckNull.clearForm(addgrd);
		            frmgrd.setVisible(true);
		            addgrd.setVisible(false);
		            fgrd.setVisible(false);
		        }else if(fgrd.isVisible()){
		            filter = new ok_academicFilter();
		            if(!fid.getValue().equals("")) 
		            	filter.setId(Integer.parseInt(fid.getValue()));
		            
		            if(!fbranch.getValue().equals(""))
		              filter.setBranch(fbranch.getValue());
		            
		              if(!facademic_code.getValue().equals("")) 
		            	  filter.setPersonal_code(Integer.parseInt(fpersonal_code.getValue()));
		              
		              if(!facademic_code.getValue().equals("")) 
		            	  filter.setAcademic_code(Integer.parseInt(facademic_code.getValue())); 
		              
		              if(!facademic_date.getValue().equals("")) 
		            	  filter.setAcademic_date(Integer.parseInt(facademic_date.getValue()));
		              
		              
		              if(!femp_code.getValue().equals("")) 
		            	  filter.setEmp_code(Integer.parseInt(femp_code.getValue())); 
		              
		              try {
		            	  if (!fins_date.getValue().equals("")) {
		                      String date_str = df.format(fins_date.getValue());
		                      Date fdate = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse(date_str);
		                      filter.setIns_date(fdate);
		                  }
		              } catch (Exception e) {
		                  e.printStackTrace();
		              }
			             
		              	              
		              if(femp_code_name==null) {
		            	  filter.setEmp_code_name(femp_code_name.getValue());
		              }
		        }
		              

		        else{
		              current.setId(Integer.parseInt(id.getValue()));
		              current.setBranch(branch.getValue());
		              current.setPersonal_code(Integer.parseInt(personal_code.getValue()));
		              current.setAcademic_code(Integer.parseInt(academic_code.getValue()));
		              current.setAcademic_date(Integer.parseInt(academic_date.getValue()));
		              current.setEmp_code(Integer.parseInt(emp_code.getValue()));
//		              current.setIns_date(new Date(ins_date.getValue()));
		              current.setEmp_code_name(emp_code_name.getValue());
		            ok_academicService.update(current);
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
			filter = new ok_academicFilter();
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
