package com.is.tf.paymentrefsumchange;

import java.text.SimpleDateFormat;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Decimalbox;
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

public class PaymentrefsumchangeViewCtrl extends GenericForwardComposer
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
	private Textbox id, p0t38, p1t38, p5t38, p6t38, p7t38, p8t38, p9t38, p10t38, id_contract, p13t38, p14t38, p15t38, p100t38;
	private Textbox aid, ap0t38, ap1t38, ap5t38, ap6t38, ap7t38, ap8t38, ap9t38, ap10t38, aid_contract, ap13t38, ap14t38, ap15t38, ap100t38;
	private Textbox fid, fp0t38, fp1t38, fp5t38, fp6t38, fp7t38, fp8t38, fp9t38, fp10t38, fid_contract, fp13t38, fp14t38, fp15t38, fp100t38;
	private Datebox fp18t38, ap18t38, p18t38;
	private Textbox fp12t38, ap12t38, p12t38;
	private Decimalbox fp2t38, fp3t38, fp4t38, ap2t38, ap3t38, ap4t38, p2t38, p3t38, p4t38;
	private Paging paymentrefsumchangePaging;
	private int _pageSize = 15;
	private int _startPageNumber = 0;
	private int _totalSize = 0;
	private boolean _needsTotalSizeUpdate = true;
	
	public PaymentrefsumchangeFilter filter;
	
	PagingListModel model = null;
	ListModelList lmodel = null;
	private AnnotateDataBinder binder;
	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	
	private Paymentrefsumchange current = new Paymentrefsumchange();
	
	public PaymentrefsumchangeViewCtrl()
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
		
		dataGrid.setItemRenderer(new ListitemRenderer()
		{
			@SuppressWarnings("unchecked")
			public void render(Listitem row, Object data) throws Exception
			{
				Paymentrefsumchange pPaymentrefsumchange = (Paymentrefsumchange) data;
				
				row.setValue(pPaymentrefsumchange);
				
				row.appendChild(new Listcell(pPaymentrefsumchange.getId() + ""));
				row.appendChild(new Listcell(pPaymentrefsumchange.getP0t38()));
				row.appendChild(new Listcell(pPaymentrefsumchange.getP1t38()));
				row.appendChild(new Listcell(pPaymentrefsumchange.getP2t38() + ""));
				row.appendChild(new Listcell(pPaymentrefsumchange.getP3t38() + ""));
				row.appendChild(new Listcell(pPaymentrefsumchange.getP4t38() + ""));
				row.appendChild(new Listcell(pPaymentrefsumchange.getP5t38()));
				row.appendChild(new Listcell(pPaymentrefsumchange.getP6t38()));
				row.appendChild(new Listcell(pPaymentrefsumchange.getP7t38()));
				row.appendChild(new Listcell(pPaymentrefsumchange.getP8t38()));
				row.appendChild(new Listcell(pPaymentrefsumchange.getP9t38()));
				row.appendChild(new Listcell(pPaymentrefsumchange.getP10t38()));
				row.appendChild(new Listcell(pPaymentrefsumchange.getId_contract() + ""));
				row.appendChild(new Listcell(pPaymentrefsumchange.getP12t38() + ""));
				row.appendChild(new Listcell(pPaymentrefsumchange.getP13t38()));
				row.appendChild(new Listcell(pPaymentrefsumchange.getP14t38()));
				row.appendChild(new Listcell(pPaymentrefsumchange.getP15t38()));
				row.appendChild(new Listcell(pPaymentrefsumchange.getP18t38() + ""));
				row.appendChild(new Listcell(pPaymentrefsumchange.getP100t38()));
				
			}
		});
		
		refreshModel(_startPageNumber);
		
	}
	
	public void onPaging$paymentrefsumchangePaging(ForwardEvent event)
	{
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModel(_startPageNumber);
	}
	
	private void refreshModel(int activePage)
	{
		paymentrefsumchangePaging.setPageSize(_pageSize);
		model = new PagingListModel(activePage, _pageSize, filter, "");
		
		if (_needsTotalSizeUpdate)
		{
			_totalSize = model.getTotalSize(filter, "");
			// _needsTotalSizeUpdate = false;
		}
		
		paymentrefsumchangePaging.setTotalSize(_totalSize);
		
		dataGrid.setModel((ListModel) model);
		if (model.getSize() > 0)
		{
			this.current = (Paymentrefsumchange) model.getElementAt(0);
			sendSelEvt();
		}
	}
	
	// Omitted...
	public Paymentrefsumchange getCurrent()
	{
		return current;
	}
	
	public void setCurrent(Paymentrefsumchange current)
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
	
	/*
	 * public void onClick$btn_save() { try{ if(addgrd.isVisible()){
	 * PaymentrefsumchangeService.create(new Paymentrefsumchange(
	 * Long.parseLong(aid.getValue()), ap0t38.getValue(), ap1t38.getValue(),
	 * ap2t38.doubleValue(), ap3t38.doubleValue(), ap4t38.doubleValue(),
	 * ap5t38.getValue(), ap6t38.getValue(), ap7t38.getValue(),
	 * ap8t38.getValue(), ap9t38.getValue(), ap10t38.getValue(),
	 * Long.parseLong(aid_contract.getValue()), ap12t38.doubleValue(),
	 * ap13t38.getValue(), ap14t38.getValue(), ap15t38.getValue(),
	 * ap18t38.getValue(), ap100t38.getValue() )); CheckNull.clearForm(addgrd);
	 * frmgrd.setVisible(true); addgrd.setVisible(false);
	 * fgrd.setVisible(false); }else if(fgrd.isVisible()){ filter = new
	 * PaymentrefsumchangeFilter(); Long.parseLong(fid.getValue());
	 * filter.setP0t38(fp0t38.getValue()); filter.setP1t38(fp1t38.getValue());
	 * filter.setP2t38(fp2t38.doubleValue());
	 * filter.setP3t38(fp3t38.doubleValue());
	 * filter.setP4t38(fp4t38.doubleValue());
	 * filter.setP5t38(fp5t38.getValue()); filter.setP6t38(fp6t38.getValue());
	 * filter.setP7t38(fp7t38.getValue()); filter.setP8t38(fp8t38.getValue());
	 * filter.setP9t38(fp9t38.getValue()); filter.setP10t38(fp10t38.getValue());
	 * Long.parseLong(fid_contract.getValue());
	 * filter.setP12t38(fp12t38.doubleValue());
	 * filter.setP13t38(fp13t38.getValue());
	 * filter.setP14t38(fp14t38.getValue());
	 * filter.setP15t38(fp15t38.getValue());
	 * filter.setP18t38(fp18t38.getValue());
	 * filter.setP100t38(fp100t38.getValue()); }else{
	 * Long.parseLong(id.getValue()); current.setP0t38(p0t38.getValue());
	 * current.setP1t38(p1t38.getValue());
	 * current.setP2t38(p2t38.doubleValue());
	 * current.setP3t38(p3t38.doubleValue());
	 * current.setP4t38(p4t38.doubleValue());
	 * current.setP5t38(p5t38.getValue()); current.setP6t38(p6t38.getValue());
	 * current.setP7t38(p7t38.getValue()); current.setP8t38(p8t38.getValue());
	 * current.setP9t38(p9t38.getValue()); current.setP10t38(p10t38.getValue());
	 * Long.parseLong(id_contract.getValue());
	 * current.setP12t38(p12t38.doubleValue());
	 * current.setP13t38(p13t38.getValue());
	 * current.setP14t38(p14t38.getValue());
	 * current.setP15t38(p15t38.getValue());
	 * current.setP18t38(p18t38.getValue());
	 * current.setP100t38(p100t38.getValue());
	 * PaymentrefsumchangeService.update(current); } onClick$btn_back();
	 * refreshModel(_startPageNumber); SelectEvent evt = new
	 * SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
	 * Events.sendEvent(evt); } catch (Exception e) { e.printStackTrace(); } }
	 */
	public void onClick$btn_cancel()
	{
		if (fgrd.isVisible())
		{
			filter = new PaymentrefsumchangeFilter();
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
