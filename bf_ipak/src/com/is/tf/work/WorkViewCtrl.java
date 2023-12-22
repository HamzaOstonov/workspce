package com.is.tf.work;

import java.text.SimpleDateFormat;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
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

import com.is.utils.CheckNull;
import com.is.utils.RefCBox;

public class WorkViewCtrl extends GenericForwardComposer
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
	private RefCBox p3t6, p4t6;
	private Textbox id, p0t6, p1t6, p2t6, p5t6, p6t6, p7t6, p8t6, p9t6;
	private Textbox aid, ap0t6, ap1t6, ap2t6, ap3t6, ap4t6, ap5t6, ap6t6, ap7t6, ap8t6, ap9t6;
	private Textbox fid, fp0t6, fp1t6, fp2t6, fp3t6, fp4t6, fp5t6, fp6t6, fp7t6, fp8t6, fp9t6;
	private Paging workPaging;
	private int _pageSize = 15;
	private int _startPageNumber = 0;
	private int _totalSize = 0;
	private boolean _needsTotalSizeUpdate = true;
	private Label line1;
    private Label line2;
    private Label line3;
    private Label line4;
    private Label line5;
	
	public WorkFilter filter = new WorkFilter();
	
	PagingListModel model = null;
	ListModelList lmodel = null;
	private AnnotateDataBinder binder;
	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	private Toolbarbutton btn_confirm, btn_reject, btn_save;
    private Window contractmain = null; 
    private String alias, idn, sparam1;
	private long idc;
	
	private Work current = new Work();
	
	public WorkViewCtrl()
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
		
		line1.setValue(Labels.getLabel("work.p5t6tab").replaceAll("<br>", "\r\n"));
	    line2.setValue(Labels.getLabel("work.p6t6tab").replaceAll("<br>", "\r\n"));
	    line3.setValue(Labels.getLabel("work.p4t6tab").replaceAll("<br>", "\r\n"));
	    line4.setValue(Labels.getLabel("work.p8t6tab").replaceAll("<br>", "\r\n"));
	    line5.setValue(Labels.getLabel("work.p7t6tab").replaceAll("<br>", "\r\n"));
		
	    p3t6.setModel((new ListModelList(com.is.utils.RefDataService.getCountry(alias))));
	    p4t6.setModel((new ListModelList(com.is.utils.RefDataService.getCountry(alias))));
	    
		dataGrid.setItemRenderer(new ListitemRenderer()
		{
			@SuppressWarnings("unchecked")
			public void render(Listitem row, Object data) throws Exception
			{
				Work pWork = (Work) data;
				
				row.setValue(pWork);
				
				row.appendChild(new Listcell(pWork.getP5t6()));
				row.appendChild(new Listcell(pWork.getP6t6()));
				row.appendChild(new Listcell(pWork.getP4t6()));
				row.appendChild(new Listcell(pWork.getP8t6()));
				row.appendChild(new Listcell(pWork.getP7t6()));
				
				
			}
		});
		
		refreshModel(_startPageNumber);
		
	}
	
	public void onPaging$workPaging(ForwardEvent event)
	{
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModel(_startPageNumber);
	}
	
	private void refreshModel(int activePage)
	{
		workPaging.setPageSize(_pageSize);
		filter.setId_contact(idc);
		model = new PagingListModel(activePage, _pageSize, filter, "");
		
		if (_needsTotalSizeUpdate)
		{
			_totalSize = model.getTotalSize();
			_needsTotalSizeUpdate = false;
		}
		
		workPaging.setTotalSize(_totalSize);
		
		dataGrid.setModel((ListModel) model);
		if (model.getSize() > 0)
		{
			this.current = (Work) model.getElementAt(0);
			sendSelEvt();
		}
	}
	
	// Omitted...
	public Work getCurrent()
	{
		return current;
	}
	
	public void setCurrent(Work current)
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
				/*WorkService.create(new Work(

				Long.parseLong(aid.getValue()),
						ap0t6.getValue(),
						ap1t6.getValue(),
						ap2t6.getValue(),
						ap3t6.getValue(),
						ap4t6.getValue(),
						ap5t6.getValue(),
						ap6t6.getValue(),
						ap7t6.getValue(),
						ap8t6.getValue(),
						ap9t6.getValue(),
						
						
						
						));*/
				CheckNull.clearForm(addgrd);
				frmgrd.setVisible(true);
				addgrd.setVisible(false);
				fgrd.setVisible(false);
			}
			else if (fgrd.isVisible())
			{
				filter = new WorkFilter();
				
				Long.parseLong(fid.getValue());
				filter.setP0t6(fp0t6.getValue());
				filter.setP1t6(fp1t6.getValue());
				filter.setP2t6(fp2t6.getValue());
				filter.setP3t6(fp3t6.getValue());
				filter.setP4t6(fp4t6.getValue());
				filter.setP5t6(fp5t6.getValue());
				filter.setP6t6(fp6t6.getValue());
				filter.setP7t6(fp7t6.getValue());
				filter.setP8t6(fp8t6.getValue());
				filter.setP9t6(fp9t6.getValue());
				
			}
			else
			{
				
				Long.parseLong(id.getValue());
				current.setP0t6(p0t6.getValue());
				current.setP1t6(p1t6.getValue());
				current.setP2t6(p2t6.getValue());
				current.setP3t6(p3t6.getValue());
				current.setP4t6(p4t6.getValue());
				current.setP5t6(p5t6.getValue());
				current.setP6t6(p6t6.getValue());
				current.setP7t6(p7t6.getValue());
				current.setP8t6(p8t6.getValue());
				current.setP9t6(p9t6.getValue());
				WorkService.update(current);
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
			filter = new WorkFilter();
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
