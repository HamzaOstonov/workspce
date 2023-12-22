package com.is.tf.garantsumchange;

import java.text.SimpleDateFormat;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
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

public class GarantsumchangeViewCtrl extends GenericForwardComposer
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
	private Textbox id, p0t20, p1t20, p2t20, p8t20, pt20, p10t20;
	private Textbox aid, ap0t20, ap1t20, ap2t20, ap8t20, apt20, ap10t20;
	private Textbox fid, fp0t20, fp1t20, fp2t20, fp8t20, fpt20, fp10t20;
	private Decimalbox p3t20, p4t20, p5t20, p6t20, p7t20, ap3t20, ap4t20, ap5t20, ap6t20, ap7t20, fp3t20, fp4t20, fp5t20, fp6t20, fp7t20;
	private Paging garantsumchangePaging;
	private int _pageSize = 15;
	private int _startPageNumber = 0;
	private int _totalSize = 0;
	private boolean _needsTotalSizeUpdate = true;
	
	public GarantsumchangeFilter filter;
	
	PagingListModel model = null;
	ListModelList lmodel = null;
	private AnnotateDataBinder binder;
	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	
	private Garantsumchange current = new Garantsumchange();
	
	public GarantsumchangeViewCtrl()
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
				Garantsumchange pGarantsumchange = (Garantsumchange) data;
				
				row.setValue(pGarantsumchange);
				
				row.appendChild(new Listcell(pGarantsumchange.getId() + ""));
				row.appendChild(new Listcell(pGarantsumchange.getP0t20()));
				row.appendChild(new Listcell(pGarantsumchange.getP1t20()));
				row.appendChild(new Listcell(pGarantsumchange.getP3t20() + ""));
				row.appendChild(new Listcell(pGarantsumchange.getP4t20() + ""));
				row.appendChild(new Listcell(pGarantsumchange.getP5t20() + ""));
				row.appendChild(new Listcell(pGarantsumchange.getP6t20() + ""));
				row.appendChild(new Listcell(pGarantsumchange.getP7t20() + ""));
				row.appendChild(new Listcell(pGarantsumchange.getP8t20()));
				// row.appendChild(new
				// Listcell(pGarantsumchange.getId_contract()+""));
				row.appendChild(new Listcell(pGarantsumchange.getP9t20()));
				row.appendChild(new Listcell(pGarantsumchange.getP10t20()));
				row.appendChild(new Listcell(pGarantsumchange.getP11t20() + ""));
				row.appendChild(new Listcell(pGarantsumchange.getP12t20()));
				row.appendChild(new Listcell(pGarantsumchange.getP15t20()));
				row.appendChild(new Listcell(pGarantsumchange.getP99t20() + ""));
				row.appendChild(new Listcell(pGarantsumchange.getP100t20()));
				
			}
		});
		
		refreshModel(_startPageNumber);
		
	}
	
	public void onPaging$garantsumchangePaging(ForwardEvent event)
	{
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModel(_startPageNumber);
	}
	
	private void refreshModel(int activePage)
	{
		garantsumchangePaging.setPageSize(_pageSize);
		model = new PagingListModel(activePage, _pageSize, filter, "");
		
		if (_needsTotalSizeUpdate)
		{
			_totalSize = model.getTotalSize();
			_needsTotalSizeUpdate = false;
		}
		
		garantsumchangePaging.setTotalSize(_totalSize);
		
		dataGrid.setModel((ListModel) model);
		if (model.getSize() > 0)
		{
			this.current = (Garantsumchange) model.getElementAt(0);
			sendSelEvt();
		}
	}
	
	// Omitted...
	public Garantsumchange getCurrent()
	{
		return current;
	}
	
	public void setCurrent(Garantsumchange current)
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
	 * GarantsumchangeService.create(new Garantsumchange(
	 * Long.parseLong(aid.getValue()), ap0t20.getValue(), ap1t20.getValue(),
	 * ap2t20.getValue(), ap3t20.doubleValue(), ap4t20.doubleValue(),
	 * ap5t20.doubleValue(), ap6t20.doubleValue(), ap7t20.doubleValue(),
	 * ap8t20.getValue(), apt20.getValue(), ap10t20.getValue() ));
	 * CheckNull.clearForm(addgrd); frmgrd.setVisible(true);
	 * addgrd.setVisible(false); fgrd.setVisible(false); }else
	 * if(fgrd.isVisible()){ filter = new GarantsumchangeFilter();
	 * Long.parseLong(fid.getValue()); filter.setP0t20(fp0t20.getValue());
	 * filter.setP1t20(fp1t20.getValue()); filter.setP2t20(fp2t20.getValue());
	 * filter.setP3t20(fp3t20.doubleValue());
	 * filter.setP4t20(fp4t20.doubleValue());
	 * filter.setP5t20(fp5t20.doubleValue());
	 * filter.setP6t20(fp6t20.doubleValue());
	 * filter.setP7t20(fp7t20.doubleValue());
	 * filter.setP8t20(fp8t20.getValue()); filter.setPt20(fpt20.getValue());
	 * filter.setP10t20(fp10t20.getValue()); }else{
	 * Long.parseLong(id.getValue()); current.setP0t20(p0t20.getValue());
	 * current.setP1t20(p1t20.getValue()); current.setP2t20(p2t20.getValue());
	 * current.setP3t20(p3t20.doubleValue());
	 * current.setP4t20(p4t20.doubleValue());
	 * current.setP5t20(p5t20.doubleValue());
	 * current.setP6t20(p6t20.doubleValue());
	 * current.setP7t20(p7t20.doubleValue());
	 * current.setP8t20(p8t20.getValue()); current.setPt20(pt20.getValue());
	 * current.setP10t20(p10t20.getValue());
	 * GarantsumchangeService.update(current); } onClick$btn_back();
	 * refreshModel(_startPageNumber); SelectEvent evt = new
	 * SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
	 * Events.sendEvent(evt); } catch (Exception e) { e.printStackTrace(); } }
	 */
	public void onClick$btn_cancel()
	{
		if (fgrd.isVisible())
		{
			filter = new GarantsumchangeFilter();
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
