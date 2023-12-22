package com.is.tf.policytimechange;

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

public class PolicytimechangeViewCtrl extends GenericForwardComposer
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
	private Textbox id, p0t33, p1t33, p3t33, p5t33, p6t33, p7t33, p8t33;
	private Textbox aid, ap0t33, ap1t33, ap3t33, ap5t33, ap6t33, ap7t33, ap8t33;
	private Textbox fid, fp0t33, fp1t33, fp3t33, fp5t33, fp6t33, fp7t33, fp8t33;
	private Datebox p2t33, p4t33, ap2t33, ap4t33, fp2t33, fp4t33;
	private Paging policytimechangePaging;
	private int _pageSize = 15;
	private int _startPageNumber = 0;
	private int _totalSize = 0;
	private boolean _needsTotalSizeUpdate = true;
	
	public PolicytimechangeFilter filter;
	
	PagingListModel model = null;
	ListModelList lmodel = null;
	private AnnotateDataBinder binder;
	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	
	private Policytimechange current = new Policytimechange();
	
	public PolicytimechangeViewCtrl()
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
				Policytimechange pPolicytimechange = (Policytimechange) data;
				
				row.setValue(pPolicytimechange);
				
				row.appendChild(new Listcell(pPolicytimechange.getId() + ""));
				row.appendChild(new Listcell(pPolicytimechange.getP0t33()));
				row.appendChild(new Listcell(pPolicytimechange.getP1t33()));
				row.appendChild(new Listcell(pPolicytimechange.getP2t33() + ""));
				row.appendChild(new Listcell(pPolicytimechange.getP3t33()));
				row.appendChild(new Listcell(pPolicytimechange.getP4t33() + ""));
				row.appendChild(new Listcell(pPolicytimechange.getP5t33()));
				row.appendChild(new Listcell(pPolicytimechange.getP6t33()));
				row.appendChild(new Listcell(pPolicytimechange.getP7t33()));
				row.appendChild(new Listcell(pPolicytimechange.getP8t33()));
				
			}
		});
		
		refreshModel(_startPageNumber);
		
	}
	
	public void onPaging$policytimechangePaging(ForwardEvent event)
	{
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModel(_startPageNumber);
	}
	
	private void refreshModel(int activePage)
	{
		policytimechangePaging.setPageSize(_pageSize);
		model = new PagingListModel(activePage, _pageSize, filter, "");
		
		if (_needsTotalSizeUpdate)
		{
			_totalSize = model.getTotalSize();
			_needsTotalSizeUpdate = false;
		}
		
		policytimechangePaging.setTotalSize(_totalSize);
		
		dataGrid.setModel((ListModel) model);
		if (model.getSize() > 0)
		{
			this.current = (Policytimechange) model.getElementAt(0);
			sendSelEvt();
		}
	}
	
	// Omitted...
	public Policytimechange getCurrent()
	{
		return current;
	}
	
	public void setCurrent(Policytimechange current)
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
	 * PolicytimechangeService.create(new Policytimechange(
	 * Long.parseLong(aid.getValue()), ap0t33.getValue(), ap1t33.getValue(),
	 * ap2t33.getValue(), ap3t33.getValue(), ap4t33.getValue(),
	 * ap5t33.getValue(), ap6t33.getValue(), ap7t33.getValue(),
	 * ap8t33.getValue(), ap9t33.getValue(), ap10t33.getValue(),
	 * ap11t33.getValue(), ap12t33.getValue(), ap13t33.getValue(),
	 * ap14t33.getValue() )); CheckNull.clearForm(addgrd);
	 * frmgrd.setVisible(true); addgrd.setVisible(false);
	 * fgrd.setVisible(false); }else if(fgrd.isVisible()){ filter = new
	 * PolicytimechangeFilter(); Long.parseLong(fid.getValue());
	 * filter.setP0t33(fp0t33.getValue()); filter.setP1t33(fp1t33.getValue());
	 * filter.setP2t33(fp2t33.getValue()); filter.setP3t33(fp3t33.getValue());
	 * filter.setP4t33(fp4t33.getValue()); filter.setP5t33(fp5t33.getValue());
	 * filter.setP6t33(fp6t33.getValue()); filter.setP7t33(fp7t33.getValue());
	 * filter.setP8t33(fp8t33.getValue()); }else{ Long.parseLong(id.getValue());
	 * current.setP0t33(p0t33.getValue()); current.setP1t33(p1t33.getValue());
	 * current.setP2t33(p2t33.getValue()); current.setP3t33(p3t33.getValue());
	 * current.setP4t33(p4t33.getValue()); current.setP5t33(p5t33.getValue());
	 * current.setP6t33(p6t33.getValue()); current.setP7t33(p7t33.getValue());
	 * current.setP8t33(p8t33.getValue());
	 * PolicytimechangeService.update(current); } onClick$btn_back();
	 * refreshModel(_startPageNumber); SelectEvent evt = new
	 * SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
	 * Events.sendEvent(evt); } catch (Exception e) { e.printStackTrace(); } }
	 */
	public void onClick$btn_cancel()
	{
		if (fgrd.isVisible())
		{
			filter = new PolicytimechangeFilter();
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
