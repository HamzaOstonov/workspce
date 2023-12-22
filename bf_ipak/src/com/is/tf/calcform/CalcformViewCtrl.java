package com.is.tf.calcform;

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
import com.is.utils.RefCBox;
import com.is.utils.Res;
import com.is.utils.refobj.XMLSerializer;
import com.sbs.service.BankServiceProxy;
import com.sbs.service.ContractResult;

public class CalcformViewCtrl extends GenericForwardComposer
{
	private Div frm;
	private Listbox dataGrid;
	private Paging contactPaging;
	private Div grd;
	private Grid addgrd, frmgrd, fgrd;
	private Toolbarbutton btn_last, btn_save;
	private Toolbarbutton btn_next;
	private Toolbarbutton btn_prev;
	private Toolbarbutton btn_first;
	private Toolbarbutton btn_add;
	private Toolbarbutton btn_search;
	private Toolbarbutton btn_back;
	private Toolbarbutton btn_confirm, btn_reject;
    private Window contractmain = null; 
	private Toolbar tb;
	private Textbox id, p0t16, p2t16, p4t16, p6t16, p10t16;
	private Textbox aid, ap0t16, ap1t16, ap2t16, ap4t16, ap6t16, ap10t16;
	private Textbox fid, fp0t16, fp1t16, fp2t16, fp4t16, fp6t16;
	private Datebox p3t16, p5t16, ap3t16, ap5t16, fp3t16, fp5t16, p11t16;
	private Paging calcformPaging;
	private int _pageSize = 15;
	private int _startPageNumber = 0;
	private int _totalSize = 0;
	private boolean _needsTotalSizeUpdate = true;
	private String idn, sparam1, alias;
	private long idc;
	private Label line1;
	private Label line2;
	private Label line3;
	private Label line4;
	private Label line5;
	private Label line6;
	private Label line7;
	private Label line8;
	private RefCBox p1t16;
	
	public CalcformFilter filter = new CalcformFilter();
	
	PagingListModel model = null;
	ListModelList lmodel = null;
	private AnnotateDataBinder binder;
	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	
	private Calcform current = new Calcform();
	
	public CalcformViewCtrl()
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
    
	    line1.setValue(Labels.getLabel("calcform.p1t16").replaceAll("<br>", "\r\n"));
		line2.setValue(Labels.getLabel("calcform.p2t16tab").replaceAll("<br>", "\r\n"));
		line3.setValue(Labels.getLabel("calcform.p3t16tab").replaceAll("<br>", "\r\n"));
		line4.setValue(Labels.getLabel("calcform.p4t16tab").replaceAll("<br>", "\r\n"));
		line5.setValue(Labels.getLabel("calcform.p5t16tab").replaceAll("<br>", "\r\n"));
		line6.setValue(Labels.getLabel("calcform.p6t16tab").replaceAll("<br>", "\r\n"));
		line7.setValue(Labels.getLabel("calcform.p11t16tab").replaceAll("<br>", "\r\n"));
		line8.setValue(Labels.getLabel("calcform.p100t16tab").replaceAll("<br>", "\r\n"));
		
		dataGrid.setItemRenderer(new ListitemRenderer()
		{
			@SuppressWarnings("unchecked")
			public void render(Listitem row, Object data) throws Exception
			{
				Calcform pCalcform = (Calcform) data;
				
				row.setValue(pCalcform);
				
				row.appendChild(new Listcell(pCalcform.getP1t16()));
				row.appendChild(new Listcell(pCalcform.getP2t16()));
				row.appendChild(new Listcell(pCalcform.getP3t16() + ""));
				row.appendChild(new Listcell(pCalcform.getP4t16()));
				row.appendChild(new Listcell(pCalcform.getP5t16() + ""));
				row.appendChild(new Listcell(pCalcform.getP6t16()));
				row.appendChild(new Listcell(pCalcform.getP11t16() + ""));
				row.appendChild(new Listcell(com.is.tf.contract.SPR.getP100Value(String.valueOf(pCalcform.getP100t16()))));
			}
		});
		
		p1t16.setModel((new ListModelList(com.is.utils.RefDataService.getDicPaymentMethod(alias))));
		
		refreshModel(_startPageNumber);
		
	}
	
	public void onPaging$calcformPaging(ForwardEvent event)
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
		calcformPaging.setPageSize(_pageSize);
		filter.setId_contract(idc);
		model = new PagingListModel(activePage, _pageSize, filter, "");
		
		if (_needsTotalSizeUpdate)
		{
			_totalSize = model.getTotalSize();
			_needsTotalSizeUpdate = false;
		}
		
		calcformPaging.setTotalSize(_totalSize);
		
		dataGrid.setModel((ListModel) model);
		if (model.getSize() > 0)
		{
			this.current = (Calcform) model.getElementAt(0);
			sendSelEvt();
		}
	}
	
	// Omitted...
	public Calcform getCurrent()
	{
		return current;
	}
	
	public void setCurrent(Calcform current)
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
		
		btn_confirm.setVisible(true);
		btn_reject.setVisible(true);
		btn_save.setVisible(false);
		
		p1t16.setDisabled(true);
		p2t16.setDisabled(true);  
		p3t16.setDisabled(true);  
		p4t16.setDisabled(true);  
		p5t16.setDisabled(true);  
		p6t16.setDisabled(true);  
		p10t16.setDisabled(true); 
		p11t16.setDisabled(true); 
		
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
				CalcformService.create(new Calcform(
						ap1t16.getValue(),
						ap2t16.getValue(),
						ap3t16.getValue(),
						ap4t16.getValue(),
						ap5t16.getValue(),
						ap6t16.getValue(),
						ap10t16.getValue()
						));
				CheckNull.clearForm(addgrd);
				frmgrd.setVisible(true);
				addgrd.setVisible(false);
				fgrd.setVisible(false);
			}
			else if (fgrd.isVisible())
			{
				filter = new CalcformFilter();
				
				Long.parseLong(fid.getValue());
				filter.setP0t16(fp0t16.getValue());
				filter.setP1t16(fp1t16.getValue());
				filter.setP2t16(fp2t16.getValue());
				filter.setP3t16(fp3t16.getValue());
				filter.setP4t16(fp4t16.getValue());
				filter.setP5t16(fp5t16.getValue());
				filter.setP6t16(fp6t16.getValue());
				
			}
			else
			{
				current.setP0t16(p0t16.getValue());
				current.setP1t16(p1t16.getValue());
				current.setP2t16(p2t16.getValue());
				current.setP3t16(p3t16.getValue());
				current.setP4t16(p4t16.getValue());
				current.setP5t16(p5t16.getValue());
				current.setP6t16(p6t16.getValue());
				CalcformService.update(current);
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
			filter = new CalcformFilter();
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
		
		com.sbs.service.CalcForm[] agre = cr.getContract().getCalcForms();
		XMLSerializer.write(agre, "c:/Calcform.xml");
		if (agre != null)
		{
			for (int i = 0; i < agre.length; i++)
			{
				CalcformService.remove(idc);
				
				Res res = CalcformService.create(agre[i], idc);
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
			ISLogger.getLogger().warn("ERROR onSelect$Calcform: Data not found");
		}
		refreshModel(_startPageNumber);
	}
	
	// ******************** Confirm  *************************** //
	public void onClick$btn_confirm()
	{
		sendConfirm(1, String.valueOf(current.getP10t16()), current);
	}
	
	public void onClick$btn_reject()
	{
		sendConfirm(0, String.valueOf(current.getP10t16()), current);
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
	// ********************************************************************************//
	
}
