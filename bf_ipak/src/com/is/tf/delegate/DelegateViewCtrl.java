package com.is.tf.delegate;

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
import com.is.utils.Res;
import com.is.utils.refobj.XMLSerializer;
import com.sbs.service.BankServiceProxy;
import com.sbs.service.ContractResult;

public class DelegateViewCtrl extends GenericForwardComposer
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
	private Textbox id, p1t30, p0t30, p2t30, p3t30, p4t30, p6t30, p8t30, p9t30, p10t30, p11t30, p12t30, p13t30, p14t30, p15t30, p16t30, p17t30, p18t30, p19t30, p20t30;
	private Textbox aid, ap1t30, ap0t30, ap2t30, ap3t30, ap4t30, ap6t30, ap8t30, ap9t30, ap10t30, ap11t30, ap12t30, ap13t30, ap14t30, ap15t30, ap16t30, ap17t30, ap18t30, ap19t30, ap20t30;
	private Textbox fid, fp1t30, fp0t30, fp2t30, fp3t30, fp4t30, fp6t30, fp8t30, fp9t30, fp10t30, fp11t30, fp12t30, fp13t30, fp14t30, fp15t30, fp16t30, fp17t30, fp18t30, fp19t30, fp20t30;
	private Datebox p5t30, p7t30, ap5t30, ap7t30, fp5t30, fp7t30;
	private Paging delegatePaging;
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
	
	public DelegateFilter filter = new DelegateFilter();
	
	PagingListModel model = null;
	ListModelList lmodel = null;
	private AnnotateDataBinder binder;
	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	
	private Delegate current = new Delegate();
	
	public DelegateViewCtrl()
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
		
		line1.setValue(Labels.getLabel("delegate.p14t30tab").replaceAll("<br>", "\r\n"));
	    line2.setValue(Labels.getLabel("delegate.p2t30tab").replaceAll("<br>", "\r\n"));
	    line3.setValue(Labels.getLabel("delegate.p10t30tab").replaceAll("<br>", "\r\n"));
	    line4.setValue(Labels.getLabel("delegate.p111t30tab").replaceAll("<br>", "\r\n"));
	    line5.setValue(Labels.getLabel("delegate.p20t30tab").replaceAll("<br>", "\r\n"));
	    line6.setValue(Labels.getLabel("delegate.p27t30tab").replaceAll("<br>", "\r\n"));
	    line7.setValue(Labels.getLabel("delegate.p100t30tab").replaceAll("<br>", "\r\n"));
		
		dataGrid.setItemRenderer(new ListitemRenderer()
		{
			@SuppressWarnings("unchecked")
			public void render(Listitem row, Object data) throws Exception
			{
				Delegate pDelegate = (Delegate) data;
				
				row.setValue(pDelegate);
				
				row.appendChild(new Listcell(pDelegate.getP14t30() + ""));
				row.appendChild(new Listcell(pDelegate.getP2t30() + ""));
				row.appendChild(new Listcell(pDelegate.getP10t30() + ""));
				row.appendChild(new Listcell(""));
				row.appendChild(new Listcell(pDelegate.getP20t30() + ""));
				row.appendChild(new Listcell(pDelegate.getP27t30() + ""));
				row.appendChild(new Listcell(pDelegate.getP100t30() + ""));
				
			}
		});
		
		refreshModel(_startPageNumber);
		
	}
	
	public void onPaging$delegatePaging(ForwardEvent event)
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
		
		delegatePaging.setPageSize(_pageSize);
		filter.setP1t30(idn);
		model = new PagingListModel(activePage, _pageSize, filter, "");
		
		if (_needsTotalSizeUpdate)
		{
			_totalSize = model.getTotalSize();
			_needsTotalSizeUpdate = false;
		}
		
		delegatePaging.setTotalSize(_totalSize);
		
		dataGrid.setModel((ListModel) model);
		if (model.getSize() > 0)
		{
			this.current = (Delegate) model.getElementAt(0);
			sendSelEvt();
		}
	}
	
	// Omitted...
	public Delegate getCurrent()
	{
		return current;
	}
	
	public void setCurrent(Delegate current)
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
		
		p1t30.setDisabled(true); 
		p2t30.setDisabled(true); 
		p3t30.setDisabled(true); 
		p4t30.setDisabled(true); 
		p5t30.setDisabled(true); 
		p6t30.setDisabled(true); 
		p7t30.setDisabled(true); 
		p8t30.setDisabled(true); 
		p9t30.setDisabled(true); 
		p10t30.setDisabled(true);
		p11t30.setDisabled(true);
		p12t30.setDisabled(true);
		p13t30.setDisabled(true);
		p14t30.setDisabled(true);
		p15t30.setDisabled(true);
		p16t30.setDisabled(true);
		p17t30.setDisabled(true);
		p18t30.setDisabled(true);
		p19t30.setDisabled(true);
		p20t30.setDisabled(true);
		
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
				DelegateService.create(new Delegate(

				Long.parseLong(aid.getValue()),
						ap0t30.getValue(),
						ap1t30.getValue(),
						ap2t30.getValue(),
						ap3t30.getValue(),
						ap4t30.getValue(),
						ap5t30.getValue(),
						ap6t30.getValue(),
						ap7t30.getValue(),
						ap8t30.getValue(),
						ap9t30.getValue(),
						ap10t30.getValue(),
						ap11t30.getValue(),
						ap12t30.getValue(),
						ap13t30.getValue(),
						ap14t30.getValue(),
						ap15t30.getValue(),
						ap16t30.getValue(),
						ap17t30.getValue(),
						ap18t30.getValue(),
						ap19t30.getValue(),
						ap20t30.getValue()
						));
				CheckNull.clearForm(addgrd);
				frmgrd.setVisible(true);
				addgrd.setVisible(false);
				fgrd.setVisible(false);
			}
			else if (fgrd.isVisible())
			{
				filter = new DelegateFilter();
				
				Long.parseLong(fid.getValue());
				filter.setP1t30(fp0t30.getValue());
				filter.setP0t30(fp1t30.getValue());
				filter.setP2t30(fp2t30.getValue());
				filter.setP3t30(fp3t30.getValue());
				filter.setP4t30(fp4t30.getValue());
				filter.setP5t30(fp5t30.getValue());
				filter.setP6t30(fp6t30.getValue());
				filter.setP7t30(fp7t30.getValue());
				filter.setP8t30(fp8t30.getValue());
				filter.setP9t30(fp9t30.getValue());
				filter.setP10t30(fp10t30.getValue());
				filter.setP11t30(fp11t30.getValue());
				filter.setP12t30(fp12t30.getValue());
				filter.setP13t30(fp13t30.getValue());
				filter.setP14t30(fp14t30.getValue());
				filter.setP15t30(fp15t30.getValue());
				filter.setP16t30(fp16t30.getValue());
				filter.setP17t30(fp17t30.getValue());
				filter.setP18t30(fp18t30.getValue());
				filter.setP19t30(fp19t30.getValue());
				filter.setP20t30(fp20t30.getValue());
				
			}
			else
			{
				
				Long.parseLong(id.getValue());
				current.setP1t30(p0t30.getValue());
				current.setP0t30(p1t30.getValue());
				current.setP2t30(p2t30.getValue());
				current.setP3t30(p3t30.getValue());
				current.setP4t30(p4t30.getValue());
				current.setP5t30(p5t30.getValue());
				current.setP6t30(p6t30.getValue());
				current.setP7t30(p7t30.getValue());
				current.setP8t30(p8t30.getValue());
				current.setP9t30(p9t30.getValue());
				current.setP10t30(p10t30.getValue());
				current.setP11t30(p11t30.getValue());
				current.setP12t30(p12t30.getValue());
				current.setP13t30(p13t30.getValue());
				current.setP14t30(p14t30.getValue());
				current.setP15t30(p15t30.getValue());
				current.setP16t30(p16t30.getValue());
				current.setP17t30(p17t30.getValue());
				current.setP18t30(p18t30.getValue());
				current.setP19t30(p19t30.getValue());
				current.setP20t30(p20t30.getValue());
				DelegateService.update(current);
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
			filter = new DelegateFilter();
		}
		onClick$btn_back();
		frmgrd.setVisible(true);
		addgrd.setVisible(false);
		fgrd.setVisible(false);
		CheckNull.clearForm(addgrd);
		CheckNull.clearForm(fgrd);
		refreshModel(_startPageNumber);
	}
	
	// ******************** Confirm  *************************** //
	public void onClick$btn_confirm()
	{
		sendConfirm(1, String.valueOf(current.getP20t30()), current);
	}
	
	public void onClick$btn_reject()
	{
		sendConfirm(0, String.valueOf(current.getP20t30()), current);
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
	
	public void onClick$btn_refresh() throws Exception 
	{
		refresh(idn);
	}
	
	public void refresh(String idn) throws Exception
	{
		final BankServiceProxy ws = new BankServiceProxy((String) session.getAttribute("YESVO_URL"));
		ContractResult cr = ws.getContract(((String) session.getAttribute("BankINN")), idn);
		
		com.sbs.service.Delegate[] agre = cr.getContract().getDelegates();
		XMLSerializer.write(agre, "c:/Delegate.xml");
		if (agre != null)
		{
			for (int i = 0; i < agre.length; i++)
			{
				DelegateService.remove(idc);
				
				Res res = DelegateService.create(agre[i], idc);
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
			ISLogger.getLogger().warn("ERROR onSelect$Agreement: Data not found");
		}
		refreshModel(_startPageNumber);
	}
	
}
