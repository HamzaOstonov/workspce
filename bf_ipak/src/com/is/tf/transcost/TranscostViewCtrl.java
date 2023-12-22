package com.is.tf.transcost;

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
import org.zkoss.zul.Window;
import org.zkoss.zul.event.PagingEvent;

import com.is.utils.CheckNull;

public class TranscostViewCtrl extends GenericForwardComposer
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
	private Textbox id, p0t11, p1t11, p2t11, p3t11;
	private Textbox aid, ap0t11, ap1t11, ap2t11, ap3t11;
	private Textbox fid, fp0t11, fp1t11, fp2t11, fp3t11;
	private Decimalbox ap4t11, p4t11, fp4t11;
	private Paging transcostPaging;
	private int _pageSize = 15;
	private int _startPageNumber = 0;
	private int _totalSize = 0;
	private boolean _needsTotalSizeUpdate = true;
	private Toolbarbutton btn_confirm, btn_reject, btn_save;
	private Window contractmain = null;
	private String alias, idn, sparam1;
	private long idc;
	
	public TranscostFilter filter = new TranscostFilter();
	
	PagingListModel model = null;
	ListModelList lmodel = null;
	private AnnotateDataBinder binder;
	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	
	private Transcost current = new Transcost();
	
	public TranscostViewCtrl()
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
		if (parameter != null) sparam1 = (parameter[0]);
		
		dataGrid.setItemRenderer(new ListitemRenderer()
		{
			@SuppressWarnings("unchecked")
			public void render(Listitem row, Object data) throws Exception
			{
				Transcost pTranscost = (Transcost) data;
				
				row.setValue(pTranscost);
				
				row.appendChild(new Listcell(pTranscost.getId() + ""));
				row.appendChild(new Listcell(pTranscost.getP0t11()));
				row.appendChild(new Listcell(pTranscost.getP1t11()));
				row.appendChild(new Listcell(pTranscost.getP2t11()));
				row.appendChild(new Listcell(pTranscost.getP3t11()));
				row.appendChild(new Listcell(pTranscost.getP4t11() + ""));
				
			}
		});
		
		refreshModel(_startPageNumber);
		
	}
	
	public void onPaging$transcostPaging(ForwardEvent event)
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
		transcostPaging.setPageSize(_pageSize);
		model = new PagingListModel(activePage, _pageSize, filter, "");
		
		if (_needsTotalSizeUpdate)
		{
			_totalSize = model.getTotalSize();
			_needsTotalSizeUpdate = false;
		}
		
		transcostPaging.setTotalSize(_totalSize);
		
		dataGrid.setModel((ListModel) model);
		if (model.getSize() > 0)
		{
			this.current = (Transcost) model.getElementAt(0);
			sendSelEvt();
		}
	}
	
	// Omitted...
	public Transcost getCurrent()
	{
		return current;
	}
	
	public void setCurrent(Transcost current)
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
				TranscostService.create(new Transcost(

				Long.parseLong(aid.getValue()),
						ap0t11.getValue(),
						ap1t11.getValue(),
						ap2t11.getValue(),
						ap3t11.getValue(),
						ap4t11.doubleValue()
						));
				CheckNull.clearForm(addgrd);
				frmgrd.setVisible(true);
				addgrd.setVisible(false);
				fgrd.setVisible(false);
			}
			else if (fgrd.isVisible())
			{
				filter = new TranscostFilter();
				
				Long.parseLong(fid.getValue());
				filter.setP0t11(fp0t11.getValue());
				filter.setP1t11(fp1t11.getValue());
				filter.setP2t11(fp2t11.getValue());
				filter.setP3t11(fp3t11.getValue());
				filter.setP4t11(fp4t11.doubleValue());
				
			}
			else
			{
				
				Long.parseLong(id.getValue());
				current.setP0t11(p0t11.getValue());
				current.setP1t11(p1t11.getValue());
				current.setP2t11(p2t11.getValue());
				current.setP3t11(p3t11.getValue());
				current.setP4t11(p4t11.doubleValue());
				TranscostService.update(current);
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
			filter = new TranscostFilter();
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
		/*
		 * final BankServiceProxy ws = new BankServiceProxy((String)
		 * session.getAttribute("YESVO_URL")); ContractResult cr =
		 * ws.getContract(((String) session.getAttribute("BankINN")), idn);
		 * com.sbs.service.Act[] act_n = cr.getContract().getActs();
		 * XMLSerializer.write(act_n, "c:/act.xml"); if (act_n != null) { for
		 * (int i = 0; i < act_n.length; i++) { ActService.remove(idc); Res res
		 * = ActService.create(act_n[i], idc); if (res.getCode() == 0)
		 * alert("Îøèáêà:" + res.getName()); } } else {
		 * alert("Data not found, BankINN=" + ((String)
		 * session.getAttribute("BankINN")));
		 * ISLogger.getLogger().warn("ERROR onSelect$Act: Data not found"); }
		 */
		refreshModel(_startPageNumber);
	}
}
