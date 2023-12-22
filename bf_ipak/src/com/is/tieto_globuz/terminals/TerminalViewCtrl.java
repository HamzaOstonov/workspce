package com.is.tieto_globuz.terminals;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
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

import com.is.tieto_globuz.merchants.MerchantService;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.Res;

public class TerminalViewCtrl extends GenericForwardComposer
{
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
	private Textbox terminal_id, point_code, serial_nr, inv_nr, notes, acc;
	private Textbox aterminal_id, apoint_code, aserial_nr, ainv_nr, anotes, aacc;
	private Textbox fterminal_id, fpoint_code, fserial_nr, finv_nr, fnotes, install_date;
	private Datebox finstall_date;
	
	private RefCBox terminal_model, connection_int, connection_host, tmc_server, tmc_host, notes_term_type, application_type;
	private RefCBox aterminal_model, aconnection_int, aconnection_host, atmc_server, atmc_host, anotes_term_type, aapplication_type;	
	
	private Paging terminalPaging;
	private int _pageSize = 15;
	private int _startPageNumber = 0;
	private int _totalSize = 0;
	private boolean _needsTotalSizeUpdate = true;
	public TerminalFilter filter = new TerminalFilter();
	private RefCBox acceptor_id, term_type, aterm_type, fterm_type, status, astatus, fstatus, aacceptor_id, termKind, handbook, facceptor_id;
	
	private String branch, alias;
	private Datebox ainstall_date;
	private static HashMap<String, String> _actionDesc = new HashMap<String, String>();
	PagingListModel model = null;
	ListModelList lmodel = null;
	private AnnotateDataBinder binder;
	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	SimpleDateFormat termdf = new SimpleDateFormat("yyyyMMdd");
	
	private Terminal current = new Terminal();
	
	public TerminalViewCtrl()
	{
		super('$', false, false);
	}
	
	@Override
	public void doAfterCompose(Component comp) throws Exception
	{
		super.doAfterCompose(comp);
		binder = new AnnotateDataBinder(comp);
		binder.bindBean("current", this.current);
		binder.loadAll();
		
		String[] parameter = (String[]) param.get("ht");
		if (parameter != null)
		{
			_pageSize = Integer.parseInt(parameter[0]) / 44;
			dataGrid.setRows(Integer.parseInt(parameter[0]) / 44);
		}
		
		branch = (String) session.getAttribute("branch");
		alias = (String) session.getAttribute("alias");
		
		_actionDesc = MerchantService.getActionDesc(alias);
		
		dataGrid.setItemRenderer(new ListitemRenderer()
		{
			@SuppressWarnings("unchecked")
			public void render(Listitem row, Object data) throws Exception
			{
				Terminal pTerminal = (Terminal) data;
				row.setValue(pTerminal);
				
				row.appendChild(new Listcell(pTerminal.getTerminal_id()));
				row.appendChild(new Listcell(pTerminal.getAcceptor_id().substring(5, 15)));
				row.appendChild(new Listcell(_actionDesc.get(pTerminal.getAction())));
				row.appendChild(new Listcell(pTerminal.getSerial_nr()));
				
			}
		});
		
		ainstall_date.setValue(new Date());
		
		aacceptor_id.setModel((new ListModelList(TerminalService.getMerchantId(alias))));
		acceptor_id.setModel((new ListModelList(TerminalService.getMerchantId(alias))));
		facceptor_id.setModel((new ListModelList(TerminalService.getMerchantId(alias))));
		
		
		term_type.setModel(new ListModelList(TerminalService.getTerminalTypes(alias)));		
		aterm_type.setModel(new ListModelList(TerminalService.getTerminalTypes(alias)));
		fterm_type.setModel(new ListModelList(TerminalService.getTerminalTypes(alias)));
	
		status.setModel(new ListModelList(TerminalService.getTerminalStatus(alias)));
		astatus.setModel(new ListModelList(TerminalService.getTerminalStatus(alias)));
		fstatus.setModel(new ListModelList(TerminalService.getTerminalStatus(alias)));
		
		termKind.setModel(new ListModelList(TerminalService.getTerminalKinds(alias)));
		
		
		aterminal_model.setModel(new ListModelList(TerminalService.getTerminalModel(alias)));
		aconnection_int.setModel(new ListModelList(TerminalService.getConnectionInt(alias)));
		aconnection_host.setModel(new ListModelList(TerminalService.getConnectionHost(alias)));
		atmc_server.setModel(new ListModelList(TerminalService.getTmcServer(alias)));
		atmc_host.setModel(new ListModelList(TerminalService.getTmcHost(alias)));
		anotes_term_type.setModel(new ListModelList(TerminalService.getNotesTermType(alias)));
		aapplication_type.setModel(new ListModelList(TerminalService.getApplicationType(alias)));
		
		terminal_model.setModel(new ListModelList(TerminalService.getTerminalModel(alias)));
		connection_int.setModel(new ListModelList(TerminalService.getConnectionInt(alias)));
		connection_host.setModel(new ListModelList(TerminalService.getConnectionHost(alias)));
		tmc_server.setModel(new ListModelList(TerminalService.getTmcServer(alias)));
		tmc_host.setModel(new ListModelList(TerminalService.getTmcHost(alias)));
		notes_term_type.setModel(new ListModelList(TerminalService.getNotesTermType(alias)));
		application_type.setModel(new ListModelList(TerminalService.getApplicationType(alias)));
		
		refreshModel(_startPageNumber);
	}
	
	public void onPaging$terminalPaging(ForwardEvent event)
	{
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModel(_startPageNumber);
	}
	
	private void refreshModel(int activePage)
	{
		terminalPaging.setPageSize(_pageSize);
		model = new PagingListModel(activePage, _pageSize, filter, "");
		
		if (_needsTotalSizeUpdate)
		{
			_totalSize = model.getTotalSize(filter, session.getAttribute("alias").toString());
			_needsTotalSizeUpdate = false;
		}
		
		terminalPaging.setTotalSize(_totalSize);
		
		dataGrid.setModel((ListModel) model);
		if (model.getSize() > 0)
		{
			this.current = (Terminal) model.getElementAt(0);
			sendSelEvt();
		}
	}
	
	// Omitted...
	public Terminal getCurrent()
	{
		return current;
	}
	
	public void setCurrent(Terminal current)
	{
		this.current = current;
	}
	
	public void onDoubleClick$dataGrid$grd()
	{
		grd.setVisible(false);
		frm.setVisible(true);
		frmgrd.setVisible(true);
		addgrd.setVisible(false);
		fgrd.setVisible(false);
		btn_back.setImage("/images/folder.png");
		btn_back.setLabel(Labels.getLabel("grid"));
		

		
		if(current != null)
		if(current.getAcceptor_id() != null) {
			acceptor_id.setSelecteditem(current.getAcceptor_id());
			term_type.setSelecteditem(current.getTerm_type());
			status.setSelecteditem(current.getStatus());
			application_type.setSelecteditem(current.getAction());
			
			String notes = current.getNotes();
			
			if(notes.length() > 16) {
				terminal_model.setSelecteditem(notes.substring(0, 3));
				connection_int.setSelecteditem(notes.substring(3, 6));
				connection_host.setSelecteditem(notes.substring(6, 9));
				tmc_server.setSelecteditem(notes.substring(9, 12));
				tmc_host.setSelecteditem(notes.substring(12, 15));
				notes_term_type.setSelecteditem(notes.substring(15, 18));
			}
			else {
				terminal_model.setSelecteditem(null);
				connection_int.setSelecteditem(null);
				connection_host.setSelecteditem(null);
				tmc_server.setSelecteditem(null);
				tmc_host.setSelecteditem(null);
				notes_term_type.setSelecteditem(null);
			}
			
			acc.setValue(current.getAcc());
		}
	}
	
	public void onClick$btn_back()
	{
		if (frm.isVisible())
		{
			frm.setVisible(false);
			grd.setVisible(true);
			btn_back.setImage("/images/file.png");
			btn_back.setLabel(Labels.getLabel("back"));
		}
		else onDoubleClick$dataGrid$grd();
	}
	
	public void onClick$btn_first()
	{
		dataGrid.setSelectedIndex(0);
		sendSelEvt();
	}
	
	public void onClick$btn_last()
	{
		dataGrid.setSelectedIndex(model.getSize() - 1);
		sendSelEvt();
	}
	
	public void onClick$btn_prev()
	{
		if (dataGrid.getSelectedIndex() != 0)
		{
			dataGrid.setSelectedIndex(dataGrid.getSelectedIndex() - 1);
			sendSelEvt();
		}
	}
	
	public void onClick$btn_next()
	{
		if (dataGrid.getSelectedIndex() != (model.getSize() - 1))
		{
			dataGrid.setSelectedIndex(dataGrid.getSelectedIndex() + 1);
			sendSelEvt();
		}
	}
	
	private void sendSelEvt()
	{
		if (dataGrid.getSelectedIndex() == 0)
		{
			btn_first.setDisabled(true);
			btn_prev.setDisabled(true);
		}
		else
		{
			btn_first.setDisabled(false);
			btn_prev.setDisabled(false);
		}
		if (dataGrid.getSelectedIndex() == (model.getSize() - 1))
		{
			btn_next.setDisabled(true);
			btn_last.setDisabled(true);
		}
		else
		{
			btn_next.setDisabled(false);
			btn_last.setDisabled(false);
		}
		
		SelectEvent evt = new SelectEvent("onSelect", dataGrid, dataGrid.getSelectedItems());
		Events.sendEvent(evt);
	}
	
	public void onClick$btn_add()
	{
		onDoubleClick$dataGrid$grd();
		frmgrd.setVisible(false);
		addgrd.setVisible(true);
		fgrd.setVisible(false);
		ainstall_date.setValue(new Date());
		
	}
	
	public void onClick$btn_search()
	{
		onDoubleClick$dataGrid$grd();
		frmgrd.setVisible(false);
		addgrd.setVisible(false);
		fgrd.setVisible(true);
	}
	
	public void onClick$btn_save()
	{
		ainstall_date.setValue(new Date());
		// install_date.setValue(new Date());
		//finstall_date.setValue(new Date());
		try
		{
			if (addgrd.isVisible())
			{
				if(aterminal_model.getValue().equals("") || aconnection_int.getValue().equals("") || aconnection_host.getValue().equals("") || atmc_server.getValue().equals("") || atmc_host.getValue().equals("") || anotes_term_type.getValue().equals("") || aapplication_type.getValue().equals("")) {
					alert("«аполните все пол€");
					return;
				}
				
				Res res = TerminalService.genTerminalId(alias, termKind.getValue(), handbook.getValue());
				
				if (res.getCode() == 1)
				{
					String notes = aterminal_model.getValue() + aconnection_int.getValue() + aconnection_host.getValue() + atmc_server.getValue() + atmc_host.getValue() + anotes_term_type.getValue() + aapplication_type.getValue();
					
					TerminalService.create(new Terminal(
						res.getName(),
						aacceptor_id.getValue(),
						aterm_type.getValue(),
						apoint_code.getValue(),
						termdf.format(ainstall_date.getValue()),
						astatus.getValue(),
						aserial_nr.getValue(),
						ainv_nr.getValue(),
						notes,
						"I",
						aacc.getValue()
						));
					
					CheckNull.clearForm(addgrd);
					frmgrd.setVisible(true);
					addgrd.setVisible(false);
					fgrd.setVisible(false);
				}
				else
				{
					alert(res.getName());
					return;
				}
			}

			else if (fgrd.isVisible())
			{
				filter = new TerminalFilter();
				
				//filter.setTerminal_id(fterminal_id.getValue());
				filter.setAcceptor_id(facceptor_id.getValue());
				filter.setTerm_type(fterm_type.getValue());
				filter.setPoint_code(fpoint_code.getValue());
				if(finstall_date.getValue() != null) {
					filter.setInstall_date(termdf.format(finstall_date.getValue()));
				}
				filter.setStatus(fstatus.getValue());
				filter.setSerial_nr(fserial_nr.getValue());
				filter.setInv_nr(finv_nr.getValue());
			}
			else
			{
				if(terminal_model.getValue().equals("") || connection_int.getValue().equals("") || connection_host.getValue().equals("") || tmc_server.getValue().equals("") || tmc_host.getValue().equals("") || notes_term_type.getValue().equals("") || application_type.getValue().equals("")) {
					alert("«аполните все пол€");
					return;
				}
				

					if (!current.getAction().equals("I"))
					{
						current.setAction("U");
					}
					
					String notes = terminal_model.getValue() + connection_int.getValue() + connection_host.getValue() + tmc_server.getValue() + tmc_host.getValue() + notes_term_type.getValue() + application_type.getValue();
										
					current.setTerm_type(term_type.getValue());
					current.setStatus(status.getValue());
					current.setSerial_nr(serial_nr.getValue());
					current.setInv_nr(inv_nr.getValue());
					current.setNotes(notes);
					current.setAction(application_type.getValue());
					
					current.setAcc(acc.getValue());
					
					TerminalService.update(current);
					
					alert("»нформаци€ сохранена.");

				
			}
			onClick$btn_back();
			refreshModel(_startPageNumber);
			SelectEvent evt = new SelectEvent("onSelect", dataGrid, dataGrid.getSelectedItems());
			Events.sendEvent(evt);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void onClick$btn_cancel()
	{
		if (fgrd.isVisible())
		{
			filter = new TerminalFilter();
		}
		
		onClick$btn_back();
		frmgrd.setVisible(true);
		addgrd.setVisible(false);
		fgrd.setVisible(false);
		CheckNull.clearForm(addgrd);
		CheckNull.clearForm(fgrd);
		refreshModel(_startPageNumber);
	}
	
	
	public void onSelect$termKind(Event event) 
	{
		/*
		 * ѕосле того, как будет известно, сколько типов (видов) терминалов существует,
		 * какие справочники дл€ каждого из видов, сделать годную архитектуру.
		 * */
		
		if(termKind.getValue().equals("1"))
		{
			handbook.setSelectedIndex(-1);
			handbook.setModel(new ListModelList(TerminalService.getSsPos(alias)));
			handbook.setDisabled(false);
		}
		else if(termKind.getValue().equals("2")) 
		{
			handbook.setSelectedIndex(-1);
			handbook.setModel(new ListModelList(TerminalService.getSsBankCashbox(alias)));
			handbook.setDisabled(false);
		}
		else if(termKind.getValue().equals("5")) 
		{
			handbook.setSelectedIndex(-1);
			handbook.setModel(new ListModelList(TerminalService.getSsPinAssignment(alias)));
			handbook.setDisabled(false);
		}
		else 
		{
			handbook.setSelectedIndex(-1);
			handbook.setDisabled(true);
		}
	}
	
	public void onSelect$aterm_type() {
		apoint_code.setValue(TerminalService.getPointCode(aterm_type.getValue()));
	}
	
	public void onSelect$term_type() {
		point_code.setValue(TerminalService.getPointCode(term_type.getValue()));
	}
}
