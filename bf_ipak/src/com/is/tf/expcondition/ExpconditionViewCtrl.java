package com.is.tf.expcondition;

import java.text.SimpleDateFormat;
import java.util.HashMap;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Datebox;
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
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;
import org.zkoss.zul.event.PagingEvent;

import com.is.ISLogger;
import com.is.utils.CheckNull;
import com.is.utils.Res;
import com.is.utils.refobj.XMLSerializer;
import com.sbs.service.BankServiceProxy;
import com.sbs.service.ContractResult;

public class ExpconditionViewCtrl extends GenericForwardComposer
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
	private Intbox p10t13;
	private Textbox id, p0t13, p1t13, p3t13, p4t13, p6t13, p7t13 ;
	private Textbox aid, ap0t13, ap1t13, ap3t13, ap4t13, ap6t13, ap7t13, p11t13;
	private Textbox fid, fp0t13, fp1t13, fp3t13, fp4t13, fp6t13, fp7t13, p100t13;
	private Datebox p2t13, p5t13, ap2t13, ap5t13, fp2t13, fp5t13, p12t13;
	private Paging expconditionPaging;
	private int _pageSize = 15;
	private int _startPageNumber = 0;
	private int _totalSize = 0;
	private boolean _needsTotalSizeUpdate = true;
	private Toolbarbutton btn_confirm, btn_reject, btn_save;
    private Window contractmain = null; 
    private String alias, idn, sparam1;
	private long idc;
	private Label line1;
    private Label line2;
    private Label line3;
    private Label line4;
    private Label line5;
    private Label line6;
    private Label line7;
    private Label line8;
    private Label line9;
    private Label line10;
	
	public ExpconditionFilter filter = new ExpconditionFilter();
	
	PagingListModel model = null;
	ListModelList lmodel = null;
	private AnnotateDataBinder binder;
	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	
	private Expcondition current = new Expcondition();
	
	public ExpconditionViewCtrl()
	{
		super('$', false, false);
	}
	
	/**
 *
 *
 */
	@Override
	public void doAfterCompose(Component comp) throws Exception
	{
		super.doAfterCompose(comp);
		// TODO Auto-generated method stub
		binder = new AnnotateDataBinder(comp);
		binder.bindBean("current", this.current);
		binder.loadAll();
		String[] parameter = (String[]) param.get("ht");
		if (parameter != null)
		{
			_pageSize = Integer.parseInt(parameter[0]) / 36;
			dataGrid.setRows(Integer.parseInt(parameter[0]) / 36);
		}
		
		parameter = (String[]) param.get("idn");
		if (parameter != null) idn = parameter[0];
				
		parameter = (String[]) param.get("idc");
		if (parameter != null) idc = Long.parseLong(parameter[0]);
		
		parameter = (String[]) param.get("spr");
		if (parameter!=null) sparam1 = (parameter[0]);
    
		line1.setValue(Labels.getLabel("expcondition.p1t13tab").replaceAll("<br>", "\r\n"));
	    line2.setValue(Labels.getLabel("expcondition.p2t13tab").replaceAll("<br>", "\r\n"));
	    line3.setValue(Labels.getLabel("expcondition.p3t13tab").replaceAll("<br>", "\r\n"));
	    line4.setValue(Labels.getLabel("expcondition.p10t13tab").replaceAll("<br>", "\r\n"));
	    line5.setValue(Labels.getLabel("expcondition.p4t13tab").replaceAll("<br>", "\r\n"));
	    line6.setValue(Labels.getLabel("expcondition.p5t13tab").replaceAll("<br>", "\r\n"));
	    line7.setValue(Labels.getLabel("expcondition.p6t13tab").replaceAll("<br>", "\r\n"));
	    line8.setValue(Labels.getLabel("expcondition.p7t13tab").replaceAll("<br>", "\r\n"));
	    line9.setValue(Labels.getLabel("expcondition.p12t13tab").replaceAll("<br>", "\r\n"));
	   line10.setValue(Labels.getLabel("expcondition.p100t13tab").replaceAll("<br>", "\r\n"));
		
		dataGrid.setItemRenderer(new ListitemRenderer()
		{
			@SuppressWarnings("unchecked")
			public void render(Listitem row, Object data) throws Exception
			{
				Expcondition pExpcondition = (Expcondition) data;
				
				row.setValue(pExpcondition);
				
				row.appendChild(new Listcell(pExpcondition.getP1t13()));
				row.appendChild(new Listcell(pExpcondition.getP2t13()+""));
				row.appendChild(new Listcell(pExpcondition.getP3t13()));
				row.appendChild(new Listcell(pExpcondition.getP10t13()));
				row.appendChild(new Listcell(pExpcondition.getP4t13()));
				row.appendChild(new Listcell(pExpcondition.getP5t13()+""));
				row.appendChild(new Listcell(pExpcondition.getP6t13()));
				row.appendChild(new Listcell(pExpcondition.getP7t13()));
				row.appendChild(new Listcell(pExpcondition.getP12t13()+""));
				row.appendChild(new Listcell(com.is.tf.contract.SPR.getP100Value(String.valueOf(pExpcondition.getP100t13()))));
				
			}
		});
		
		refreshModel(_startPageNumber);
		
	}
	
	public void onPaging$expconditionPaging(ForwardEvent event)
	{
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModel(_startPageNumber);
	}
	
	private void refreshModel(int activePage)
	{
		
		if (sparam1 != null)
		{
			if (sparam1.equals("Filial")) 
			{
				btn_confirm.setVisible(true);
				btn_reject.setVisible(true);
			}
			else
			{
				btn_confirm.setVisible(false);
				btn_reject.setVisible(false);
			}
		}
		
		expconditionPaging.setPageSize(_pageSize);
		filter.setId_contract(idc);
		model = new PagingListModel(activePage, _pageSize, filter, "");
		
		if (_needsTotalSizeUpdate)
		{
			_totalSize = model.getTotalSize();
			_needsTotalSizeUpdate = false;
		}
		
		expconditionPaging.setTotalSize(_totalSize);
		
		dataGrid.setModel((ListModel) model);
		if (model.getSize() > 0)
		{
			this.current = (Expcondition) model.getElementAt(0);
			sendSelEvt();
		}
	}
	
	// Omitted...
	public Expcondition getCurrent()
	{
		return current;
	}
	
	public void setCurrent(Expcondition current)
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
		
		p1t13.setDisabled(true); 
		p2t13.setDisabled(true); 
		p3t13.setDisabled(true); 
		p4t13.setDisabled(true); 
		p5t13.setDisabled(true); 
		p6t13.setDisabled(true); 
		p7t13.setDisabled(true); 
		p10t13.setDisabled(true);
		p11t13.setDisabled(true);
		p12t13.setDisabled(true);
		p100t13.setDisabled(true);
		
		btn_confirm.setVisible(true);
		btn_reject.setVisible(true);
		btn_save.setVisible(false);
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
		try
		{
			if (addgrd.isVisible())
			{
				/*ExpconditionService.create(new Expcondition(

				Long.parseLong(aid.getValue()),
						ap0t13.getValue(),
						ap1t13.getValue(),
						ap2t13.getValue(),
						ap3t13.getValue(),
						ap4t13.getValue(),
						ap5t13.getValue(),
						ap6t13.getValue(),
						ap7t13.getValue()
						));*/
				CheckNull.clearForm(addgrd);
				frmgrd.setVisible(true);
				addgrd.setVisible(false);
				fgrd.setVisible(false);
			}
			else if (fgrd.isVisible())
			{
				filter = new ExpconditionFilter();
				
				Long.parseLong(fid.getValue());
				filter.setP0t13(fp0t13.getValue());
				filter.setP1t13(fp1t13.getValue());
				filter.setP2t13(fp2t13.getValue());
				filter.setP3t13(fp3t13.getValue());
				filter.setP4t13(fp4t13.getValue());
				filter.setP5t13(fp5t13.getValue());
				filter.setP6t13(fp6t13.getValue());
				filter.setP7t13(fp7t13.getValue());
				
			}
			else
			{
				
				Long.parseLong(id.getValue());
				current.setP0t13(p0t13.getValue());
				current.setP1t13(p1t13.getValue());
				current.setP2t13(p2t13.getValue());
				current.setP3t13(p3t13.getValue());
				current.setP4t13(p4t13.getValue());
				current.setP5t13(p5t13.getValue());
				current.setP6t13(p6t13.getValue());
				current.setP7t13(p7t13.getValue());
				ExpconditionService.update(current);
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
			filter = new ExpconditionFilter();
		}
		onClick$btn_back();
		frmgrd.setVisible(true);
		addgrd.setVisible(false);
		fgrd.setVisible(false);
		CheckNull.clearForm(addgrd);
		CheckNull.clearForm(fgrd);
		refreshModel(_startPageNumber);
	}
	
	public void onClick$btn_refresh() throws Exception 
	{
		refresh(idn);
	}
	
	public void refresh(String idn) throws Exception
	{
		final BankServiceProxy ws = new BankServiceProxy((String) session.getAttribute("YESVO_URL"));
		ContractResult cr = ws.getContract(((String) session.getAttribute("BankINN")), idn);
		
		com.sbs.service.ExpCondition[] agre = cr.getContract().getExpConditions();
		XMLSerializer.write(agre, "c:/Expcondition.xml");
		if (agre != null)
		{
			for (int i = 0; i < agre.length; i++)
			{
				ExpconditionService.remove(idc);
				
				Res res = ExpconditionService.create(agre[i], idc);
				if (res.getCode() == 1)
				{
					alert("Успешно загружено!");
				}
				else
				{
					alert("Ошибка:" + res.getName());
					
				}
			}
		}
		else
		{
			alert("Data not found, BankINN=" + ((String) session.getAttribute("BankINN")));
			ISLogger.getLogger().warn("ERROR onSelect$Expcondition: Data not found");
		}
		refreshModel(_startPageNumber);
	}

	// ******************** Confirm  *************************** //
	public void onClick$btn_confirm()
	{
		sendConfirm(1, String.valueOf(current.getP11t13()), current);
	}
	
	public void onClick$btn_reject()
	{
		sendConfirm(0, String.valueOf(current.getP11t13()), current);
	}
	
	private void sendConfirm(int action, String docnum, Object obj)
	{
		if (contractmain == null)
		{
			contractmain = (Window) execution.getDesktop().getPage("contract").getFellow("contractmain");
		}
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("inn", ((String) session.getAttribute("BankINN")));
		params.put("idn", idn);
		params.put("action", action + "");
		params.put("docnum", docnum);
		params.put("obj", obj);
		Events.sendEvent("onConfirmDocument", contractmain, params);
	}
}
