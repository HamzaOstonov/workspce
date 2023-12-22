package com.is.tf.policysumchange;

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

public class PolicysumchangeViewCtrl extends GenericForwardComposer
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
	private Textbox id, p0t34, p1t34, p2t34, p4t34, p9t34, p10t34, p11t34;
	private Textbox aid, ap0t34, ap1t34, ap2t34, ap4t34, ap9t34, ap10t34, ap11t34;
	private Textbox fid, fp0t34, fp1t34, fp2t34, fp4t34, fp9t34, fp10t34, fp11t34;
	private Datebox ap3t34, fp3t34;
	private Decimalbox p3t34, p5t34, p6t34, p7t34, p8t34, ap5t34, ap6t34, ap7t34, ap8t34, fp5t34, fp6t34, fp7t34, fp8t34;
	private Paging policysumchangePaging;
	private int _pageSize = 15;
	private int _startPageNumber = 0;
	private int _totalSize = 0;
	private boolean _needsTotalSizeUpdate = true;
	
	public PolicysumchangeFilter filter;
	
	PagingListModel model = null;
	ListModelList lmodel = null;
	private AnnotateDataBinder binder;
	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	
	private Policysumchange current = new Policysumchange();
	
	public PolicysumchangeViewCtrl()
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
				Policysumchange pPolicysumchange = (Policysumchange) data;
				
				row.setValue(pPolicysumchange);
				
				row.appendChild(new Listcell(pPolicysumchange.getId() + ""));
				row.appendChild(new Listcell(pPolicysumchange.getP0t34()));
				row.appendChild(new Listcell(pPolicysumchange.getP1t34()));
				row.appendChild(new Listcell(pPolicysumchange.getP2t34()));
				row.appendChild(new Listcell(pPolicysumchange.getP3t34() + ""));
				row.appendChild(new Listcell(pPolicysumchange.getP4t34()));
				row.appendChild(new Listcell(pPolicysumchange.getP5t34() + ""));
				row.appendChild(new Listcell(pPolicysumchange.getP6t34() + ""));
				row.appendChild(new Listcell(pPolicysumchange.getP7t34() + ""));
				row.appendChild(new Listcell(pPolicysumchange.getP8t34() + ""));
				row.appendChild(new Listcell(pPolicysumchange.getP9t34()));
				row.appendChild(new Listcell(pPolicysumchange.getP10t34()));
				row.appendChild(new Listcell(pPolicysumchange.getP11t34()));
				
			}
		});
		
		refreshModel(_startPageNumber);
		
	}
	
	public void onPaging$policysumchangePaging(ForwardEvent event)
	{
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModel(_startPageNumber);
	}
	
	private void refreshModel(int activePage)
	{
		policysumchangePaging.setPageSize(_pageSize);
		model = new PagingListModel(activePage, _pageSize, filter, "");
		
		if (_needsTotalSizeUpdate)
		{
			_totalSize = model.getTotalSize();
			_needsTotalSizeUpdate = false;
		}
		
		policysumchangePaging.setTotalSize(_totalSize);
		
		dataGrid.setModel((ListModel) model);
		if (model.getSize() > 0)
		{
			this.current = (Policysumchange) model.getElementAt(0);
			sendSelEvt();
		}
	}
	
	// Omitted...
	public Policysumchange getCurrent()
	{
		return current;
	}
	
	public void setCurrent(Policysumchange current)
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
			if (addgrd.isVisible())
			{
				/*
				 * PolicysumchangeService.create(new Policysumchange(
				 * Long.parseLong(aid.getValue()), ap0t34.getValue(),
				 * ap1t34.getValue(), ap2t34.getValue(), ap3t34.getValue(),
				 * ap4t34.getValue(), ap5t34.doubleValue(),
				 * ap6t34.doubleValue(), ap7t34.doubleValue(),
				 * ap8t34.doubleValue(), ap9t34.getValue(), ap10t34.getValue(),
				 * ap11t34.getValue() )); CheckNull.clearForm(addgrd);
				 * frmgrd.setVisible(true); addgrd.setVisible(false);
				 * fgrd.setVisible(false);
				 */
			}
			else if (fgrd.isVisible())
			{
				filter = new PolicysumchangeFilter();
				
				Long.parseLong(fid.getValue());
				filter.setP0t34(fp0t34.getValue());
				filter.setP1t34(fp1t34.getValue());
				filter.setP2t34(fp2t34.getValue());
				// filter.setP3t34(fp3t34.getValue());
				filter.setP4t34(fp4t34.getValue());
				filter.setP5t34(fp5t34.doubleValue());
				filter.setP6t34(fp6t34.doubleValue());
				filter.setP7t34(fp7t34.doubleValue());
				filter.setP8t34(fp8t34.doubleValue());
				filter.setP9t34(fp9t34.getValue());
				filter.setP10t34(fp10t34.getValue());
				filter.setP11t34(fp11t34.getValue());
				
			}
			else
			{
				
				Long.parseLong(id.getValue());
				current.setP0t34(p0t34.getValue());
				current.setP1t34(p1t34.getValue());
				current.setP2t34(p2t34.getValue());
				current.setP3t34(p3t34.doubleValue());
				current.setP4t34(p4t34.getValue());
				current.setP5t34(p5t34.doubleValue());
				current.setP6t34(p6t34.doubleValue());
				current.setP7t34(p7t34.doubleValue());
				current.setP8t34(p8t34.doubleValue());
				current.setP9t34(p9t34.getValue());
				current.setP10t34(p10t34.getValue());
				current.setP11t34(p11t34.getValue());
				PolicysumchangeService.update(current);
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
			filter = new PolicysumchangeFilter();
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
