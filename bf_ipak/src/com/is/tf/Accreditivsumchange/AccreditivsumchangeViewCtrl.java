package com.is.tf.Accreditivsumchange;

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
import com.is.utils.RefCBox;

public class AccreditivsumchangeViewCtrl extends GenericForwardComposer
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
	private Textbox id, p0t23, p1t23, p8t23, p9t23, p10t23, p12t23, p15t23, p100t23;
	private Textbox aid, ap0t23, ap1t23, ap8t23, ap9t23, ap10t23, ap12t23, ap15t23, ap100t23;
	private Textbox fid, fp0t23, fp1t23, fp8t23, fp9t23, fp10t23;
	private RefCBox p2t23, ap2t23, fp2t23;
	private Datebox p16t23, ap16t23;
	private Decimalbox p3t23, p4t23, p5t23, p6t23, p7t23, ap3t23, ap4t23, ap5t23, ap6t23, ap7t23, fp3t23, fp4t23, fp5t23, fp6t23, fp7t23;
	private Paging accreditivsumchangePaging;
	private int _pageSize = 15;
	private int _startPageNumber = 0;
	private int _totalSize = 0;
	private boolean _needsTotalSizeUpdate = true;
	private String alias;
	
	public AccreditivsumchangeFilter filter = new AccreditivsumchangeFilter();
	
	PagingListModel model = null;
	ListModelList lmodel = null;
	private AnnotateDataBinder binder;
	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	
	private Accreditivsumchange current = new Accreditivsumchange();
	
	public AccreditivsumchangeViewCtrl()
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
				Accreditivsumchange pAccreditivsumchange = (Accreditivsumchange) data;
				
				row.setValue(pAccreditivsumchange);
				
				row.appendChild(new Listcell(pAccreditivsumchange.getId() + ""));
				row.appendChild(new Listcell(pAccreditivsumchange.getP0t23()));
				row.appendChild(new Listcell(pAccreditivsumchange.getP1t23()));
				row.appendChild(new Listcell(pAccreditivsumchange.getP2t23()));
				row.appendChild(new Listcell(pAccreditivsumchange.getP3t23() + ""));
				row.appendChild(new Listcell(pAccreditivsumchange.getP4t23() + ""));
				row.appendChild(new Listcell(pAccreditivsumchange.getP5t23() + ""));
				row.appendChild(new Listcell(pAccreditivsumchange.getP6t23() + ""));
				row.appendChild(new Listcell(pAccreditivsumchange.getP7t23() + ""));
				row.appendChild(new Listcell(pAccreditivsumchange.getP8t23()));
				row.appendChild(new Listcell(pAccreditivsumchange.getP9t23()));
				row.appendChild(new Listcell(pAccreditivsumchange.getP10t23()));
				row.appendChild(new Listcell(pAccreditivsumchange.getP11t23()));
				row.appendChild(new Listcell(pAccreditivsumchange.getP12t23()));
				row.appendChild(new Listcell(pAccreditivsumchange.getP15t23()));
				row.appendChild(new Listcell(pAccreditivsumchange.getP16t23() + ""));
				row.appendChild(new Listcell(pAccreditivsumchange.getP100t23()));
				
			}
		});
		
		p2t23.setModel((new ListModelList(com.is.utils.RefDataService.getCurrency(alias))));
		ap2t23.setModel((new ListModelList(com.is.utils.RefDataService.getCurrency(alias))));
		fp2t23.setModel((new ListModelList(com.is.utils.RefDataService.getCurrency(alias))));
		
		refreshModel(_startPageNumber);
		
	}
	
	public void onPaging$accreditivsumchangePaging(ForwardEvent event)
	{
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModel(_startPageNumber);
	}
	
	private void refreshModel(int activePage)
	{
		accreditivsumchangePaging.setPageSize(_pageSize);
		model = new PagingListModel(activePage, _pageSize, filter, "");
		
		if (_needsTotalSizeUpdate)
		{
			_totalSize = model.getTotalSize();
			_needsTotalSizeUpdate = false;
		}
		
		accreditivsumchangePaging.setTotalSize(_totalSize);
		
		dataGrid.setModel((ListModel) model);
		if (model.getSize() > 0)
		{
			this.current = (Accreditivsumchange) model.getElementAt(0);
			sendSelEvt();
		}
	}
	
	// Omitted...
	public Accreditivsumchange getCurrent()
	{
		return current;
	}
	
	public void setCurrent(Accreditivsumchange current)
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
				AccreditivsumchangeService.create(new Accreditivsumchange(
						ap0t23.getValue(),
						ap4t23.doubleValue(),
						ap5t23.doubleValue(),
						ap6t23.doubleValue(),
						ap7t23.doubleValue(),
						ap8t23.getValue(),
						ap9t23.getValue(),
						ap10t23.getValue(),
						ap12t23.getValue(),
						ap15t23.getValue()
						));
				CheckNull.clearForm(addgrd);
				frmgrd.setVisible(true);
				addgrd.setVisible(false);
				fgrd.setVisible(false);
			}
			else if (fgrd.isVisible())
			{
				filter = new AccreditivsumchangeFilter();
				
				Long.parseLong(fid.getValue());
				filter.setP0t23(fp0t23.getValue());
				filter.setP1t23(fp1t23.getValue());
				filter.setP2t23(fp2t23.getValue());
				filter.setP3t23(fp3t23.doubleValue());
				filter.setP4t23(fp4t23.doubleValue());
				filter.setP5t23(fp5t23.doubleValue());
				filter.setP6t23(fp6t23.doubleValue());
				filter.setP7t23(fp7t23.doubleValue());
				filter.setP8t23(fp8t23.getValue());
				filter.setP9t23(fp9t23.getValue());
				filter.setP10t23(fp10t23.getValue());
				
			}
			else
			{
				
				Long.parseLong(id.getValue());
				current.setP0t23(p0t23.getValue());
				current.setP1t23(p1t23.getValue());
				current.setP2t23(p2t23.getValue());
				current.setP3t23(p3t23.doubleValue());
				current.setP4t23(p4t23.doubleValue());
				current.setP5t23(p5t23.doubleValue());
				current.setP6t23(p6t23.doubleValue());
				current.setP7t23(p7t23.doubleValue());
				current.setP8t23(p8t23.getValue());
				current.setP9t23(p9t23.getValue());
				current.setP10t23(p10t23.getValue());
				AccreditivsumchangeService.update(current);
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
			filter = new AccreditivsumchangeFilter();
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
