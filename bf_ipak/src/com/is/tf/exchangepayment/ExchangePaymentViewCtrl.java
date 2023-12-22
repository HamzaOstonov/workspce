package com.is.tf.exchangepayment;

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
import com.sbs.service.BankServiceProxy;

public class ExchangePaymentViewCtrl extends GenericForwardComposer
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
	private Textbox id, p1t51, p0t51, p8t51;
	private Textbox aid, ap1t51, ap0t51, ap8t51;
	private Textbox fid, fp1t51, fp0t51, fp8t51;
	private Datebox p2t51, ap2t51, fp2t51;
	private Decimalbox p3t51, p4t51, p5t51, p6t51, p7t51, ap3t51, ap4t51, ap5t51, ap6t51, ap7t51, fp3t51, fp4t51, fp5t51, fp6t51, fp7t51;
	private Paging exchangepaymentPaging;
	private int _pageSize = 15;
	private int _startPageNumber = 0;
	private int _totalSize = 0;
	private boolean _needsTotalSizeUpdate = true;
	
	public ExchangePaymentFilter filter;
	
	PagingListModel model = null;
	ListModelList lmodel = null;
	private AnnotateDataBinder binder;
	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	
	private ExchangePayment current = new ExchangePayment();
	
	public ExchangePaymentViewCtrl()
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
				ExchangePayment pExchangePayment = (ExchangePayment) data;
				
				row.setValue(pExchangePayment);
				
				row.appendChild(new Listcell(pExchangePayment.getId() + ""));
				row.appendChild(new Listcell(pExchangePayment.getP1t51()));
				row.appendChild(new Listcell(pExchangePayment.getP0t51()));
				row.appendChild(new Listcell(pExchangePayment.getP2t51() + ""));
				row.appendChild(new Listcell(pExchangePayment.getP3t51() + ""));
				row.appendChild(new Listcell(pExchangePayment.getP4t51() + ""));
				row.appendChild(new Listcell(pExchangePayment.getP5t51() + ""));
				row.appendChild(new Listcell(pExchangePayment.getP6t51() + ""));
				row.appendChild(new Listcell(pExchangePayment.getP7t51() + ""));
				row.appendChild(new Listcell(pExchangePayment.getP8t51()));
				
			}
		});
		
		refreshModel(_startPageNumber);
		
	}
	
	public void onPaging$exchangepaymentPaging(ForwardEvent event)
	{
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModel(_startPageNumber);
	}
	
	private void refreshModel(int activePage)
	{
		exchangepaymentPaging.setPageSize(_pageSize);
		model = new PagingListModel(activePage, _pageSize, filter, "");
		
		if (_needsTotalSizeUpdate)
		{
			_totalSize = model.getTotalSize(filter, "");
			// _needsTotalSizeUpdate = false;
		}
		
		exchangepaymentPaging.setTotalSize(_totalSize);
		
		dataGrid.setModel((ListModel) model);
		if (model.getSize() > 0)
		{
			this.current = (ExchangePayment) model.getElementAt(0);
			sendSelEvt();
		}
	}
	
	// Omitted...
	public ExchangePayment getCurrent()
	{
		return current;
	}
	
	public void setCurrent(ExchangePayment current)
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
	
	public void onClick$btn_save()
	{
		try
		{
			
			final BankServiceProxy ws = new BankServiceProxy("http://91.213.31.234:8892/yeisvo_bank/service");
			com.sbs.service.ExchangePaymentResult Exchp1 = new com.sbs.service.ExchangePaymentResult();
			
			if (addgrd.isVisible())
			{
				ExchangePaymentService.create(new ExchangePayment(

				Long.parseLong(aid.getValue()),
						ap1t51.getValue(),
						ap0t51.getValue(),
						ap2t51.getValue(),
						ap3t51.doubleValue(),
						ap4t51.doubleValue(),
						ap5t51.doubleValue(),
						ap6t51.doubleValue(),
						ap7t51.doubleValue(),
						ap8t51.getValue()
						));
				CheckNull.clearForm(addgrd);
				frmgrd.setVisible(true);
				addgrd.setVisible(false);
				fgrd.setVisible(false);
			}
			else if (fgrd.isVisible())
			{
				filter = new ExchangePaymentFilter();
				
				Long.parseLong(fid.getValue());
				filter.setP1t51(fp1t51.getValue());
				filter.setP0t51(fp0t51.getValue());
				filter.setP2t51(fp2t51.getValue());
				filter.setP3t51(fp3t51.doubleValue());
				filter.setP4t51(fp4t51.doubleValue());
				filter.setP5t51(fp5t51.doubleValue());
				filter.setP6t51(fp6t51.doubleValue());
				filter.setP7t51(fp7t51.doubleValue());
				filter.setP8t51(fp8t51.getValue());
				
			}
			else
			{
				
				Long.parseLong(id.getValue());
				current.setP1t51(p1t51.getValue());
				current.setP0t51(p0t51.getValue());
				current.setP2t51(p2t51.getValue());
				current.setP3t51(p3t51.doubleValue());
				current.setP4t51(p4t51.doubleValue());
				current.setP5t51(p5t51.doubleValue());
				current.setP6t51(p6t51.doubleValue());
				current.setP7t51(p7t51.doubleValue());
				current.setP8t51(p8t51.getValue());
				
				// ExchangePaymentService.update(current);
				
				/*
				 * ExchangePaymentResult exp = ws.saveExchangePayment("nci",
				 * "987", p1t51.getValue() , getExchp(current)); if
				 * (exp.getStatus() == 0) { refreshModel(_startPageNumber);
				 * alert("Сохранение успешно"); } else {
				 * alert("Error:"+exp.getStatus()+"; GTKid:" +exp.getGtkId()+
				 * "; Text:" +exp.getErrorMsg()); }
				 */

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
			filter = new ExchangePaymentFilter();
		}
		onClick$btn_back();
		frmgrd.setVisible(true);
		addgrd.setVisible(false);
		fgrd.setVisible(false);
		CheckNull.clearForm(addgrd);
		CheckNull.clearForm(fgrd);
		refreshModel(_startPageNumber);
	}
	
	private com.sbs.service.ExchangePayment getExchp(ExchangePayment exchp)
	{
		java.util.Calendar cal = java.util.Calendar.getInstance();
		
		com.sbs.service.ExchangePayment res = new com.sbs.service.ExchangePayment();
		// res.setP0T51(Integer.parseInt(exchp.getP0t51()));
		cal.setTime(exchp.getP2t51());
		res.setP2T51(cal);
		res.setP3T51(exchp.getP3t51());
		res.setP4T51(exchp.getP4t51());
		res.setP5T51(exchp.getP5t51());
		res.setP6T51(exchp.getP6t51());
		res.setP7T51(exchp.getP7t51());
		res.setP8T51(exchp.getP8t51());
		// res.setP9T51((String)session.getAttribute("ufn"));
		return res;
	}
	
}
