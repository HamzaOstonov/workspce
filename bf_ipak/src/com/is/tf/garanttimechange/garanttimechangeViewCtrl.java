package com.is.tf.garanttimechange;

import java.text.SimpleDateFormat;

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

public class garanttimechangeViewCtrl extends GenericForwardComposer
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
	private Textbox id, p0t19, p1t19, p3t19, p5t19, p6t19, p7t19, p8t19;
	private Textbox aid, ap0t19, ap1t19, ap3t19, ap5t19, ap6t19, ap7t19, ap8t19;
	private Textbox fid, fp0t19, fp1t19, fp3t19, fp5t19, fp6t19, fp7t19, fp8t19;
	private Datebox p2t19, p4t19, ap2t19, ap4t19, fp2t19, fp4t19;
	private Paging garanttimechangePaging;
	private int _pageSize = 15;
	private int _startPageNumber = 0;
	private int _totalSize = 0;
	private boolean _needsTotalSizeUpdate = true;
	
	public garanttimechangeFilter filter;
	
	PagingListModel model = null;
	ListModelList lmodel = null;
	private AnnotateDataBinder binder;
	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	
	private garanttimechange current = new garanttimechange();
	
	public garanttimechangeViewCtrl()
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
				garanttimechange pgaranttimechange = (garanttimechange) data;
				
				row.setValue(pgaranttimechange);
				
				row.appendChild(new Listcell(pgaranttimechange.getId() + ""));
				row.appendChild(new Listcell(pgaranttimechange.getP0t19()));
				row.appendChild(new Listcell(pgaranttimechange.getP1t19()));
				row.appendChild(new Listcell(pgaranttimechange.getP2t19() + ""));
				row.appendChild(new Listcell(pgaranttimechange.getP3t19()));
				row.appendChild(new Listcell(pgaranttimechange.getP4t19() + ""));
				row.appendChild(new Listcell(pgaranttimechange.getP5t19()));
				row.appendChild(new Listcell(pgaranttimechange.getP6t19()));
				row.appendChild(new Listcell(pgaranttimechange.getP7t19()));
				row.appendChild(new Listcell(pgaranttimechange.getP8t19()));
				
			}
		});
		
		refreshModel(_startPageNumber);
		
	}
	
	public void onPaging$garanttimechangePaging(ForwardEvent event)
	{
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModel(_startPageNumber);
	}
	
	private void refreshModel(int activePage)
	{
		garanttimechangePaging.setPageSize(_pageSize);
		model = new PagingListModel(activePage, _pageSize, filter, "");
		
		if (_needsTotalSizeUpdate)
		{
			_totalSize = model.getTotalSize();
			_needsTotalSizeUpdate = false;
		}
		
		garanttimechangePaging.setTotalSize(_totalSize);
		
		dataGrid.setModel((ListModel) model);
		if (model.getSize() > 0)
		{
			this.current = (garanttimechange) model.getElementAt(0);
			sendSelEvt();
		}
	}
	
	// Omitted...
	public garanttimechange getCurrent()
	{
		return current;
	}
	
	public void setCurrent(garanttimechange current)
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
	 * garanttimechangeService.create(new garanttimechange(
	 * Long.parseLong(aid.getValue()), ap0t19.getValue(), ap1t19.getValue(),
	 * ap2t19.getValue(), ap3t19.getValue(), ap4t19.getValue(),
	 * ap5t19.getValue(), ap6t19.getValue(), ap7t19.getValue(),
	 * ap8t19.getValue() )); CheckNull.clearForm(addgrd);
	 * frmgrd.setVisible(true); addgrd.setVisible(false);
	 * fgrd.setVisible(false); }else if(fgrd.isVisible()){ filter = new
	 * garanttimechangeFilter(); Long.parseLong(fid.getValue());
	 * filter.setP0t19(fp0t19.getValue()); filter.setP1t19(fp1t19.getValue());
	 * filter.setP2t19(fp2t19.getValue()); filter.setP3t19(fp3t19.getValue());
	 * filter.setP4t19(fp4t19.getValue()); filter.setP5t19(fp5t19.getValue());
	 * filter.setP6t19(fp6t19.getValue()); filter.setP7t19(fp7t19.getValue());
	 * filter.setP8t19(fp8t19.getValue()); }else{ Long.parseLong(id.getValue());
	 * current.setP0t19(p0t19.getValue()); current.setP1t19(p1t19.getValue());
	 * current.setP2t19(p2t19.getValue()); current.setP3t19(p3t19.getValue());
	 * current.setP4t19(p4t19.getValue()); current.setP5t19(p5t19.getValue());
	 * current.setP6t19(p6t19.getValue()); current.setP7t19(p7t19.getValue());
	 * current.setP8t19(p8t19.getValue());
	 * garanttimechangeService.update(current); } onClick$btn_back();
	 * refreshModel(_startPageNumber); SelectEvent evt = new
	 * SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
	 * Events.sendEvent(evt); } catch (Exception e) { e.printStackTrace(); } }
	 */
	public void onClick$btn_cancel()
	{
		if (fgrd.isVisible())
		{
			filter = new garanttimechangeFilter();
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
