package com.is.tf.tolling;

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
import org.zkoss.zul.Decimalbox;
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

public class TollingViewCtrl extends GenericForwardComposer
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
	private Textbox id, p0t15, p1t15, p2t15;
	private Textbox aid, ap0t15, ap1t15, ap2t15;
	private Textbox fid, fp0t15, fp1t15, fp2t15, p10t15, p11t15, p12t15, p13t15, p100t15;
	private Datebox p7t15, ap7t15, fp7t15, p14t15;
	private Decimalbox p3t15, p4t15, p5t15, p6t15, ap3t15, ap4t15, ap5t15, ap6t15, fp3t15, fp4t15, fp5t15, fp6t15;
	private Paging tollingPaging;
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
	
	public TollingFilter filter = new TollingFilter();
	
	PagingListModel model = null;
	ListModelList lmodel = null;
	private AnnotateDataBinder binder;
	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	
	private Tolling current = new Tolling();
	
	public TollingViewCtrl()
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
		
		line1.setValue(Labels.getLabel("tolling.p13t15tab").replaceAll("<br>", "\r\n"));
		line2.setValue(Labels.getLabel("tolling.p7t15tab").replaceAll("<br>", "\r\n"));
		line3.setValue(Labels.getLabel("tolling.p1t15tab").replaceAll("<br>", "\r\n"));
		line4.setValue(Labels.getLabel("tolling.p12t15tab").replaceAll("<br>", "\r\n"));
		line5.setValue(Labels.getLabel("tolling.p2t15tab").replaceAll("<br>", "\r\n"));
		line6.setValue(Labels.getLabel("tolling.p14t15tab").replaceAll("<br>", "\r\n"));
		line7.setValue(Labels.getLabel("tolling.p100t15tab").replaceAll("<br>", "\r\n"));
		
		dataGrid.setItemRenderer(new ListitemRenderer()
		{
			@SuppressWarnings("unchecked")
			public void render(Listitem row, Object data) throws Exception
			{
				Tolling pTolling = (Tolling) data;
				
				row.setValue(pTolling);
				
				row.appendChild(new Listcell(pTolling.getP13t15() + ""));
				row.appendChild(new Listcell(pTolling.getP7t15() + ""));
				row.appendChild(new Listcell(pTolling.getP1t15() + ""));
				row.appendChild(new Listcell(pTolling.getP12t15() + ""));
				row.appendChild(new Listcell(pTolling.getP2t15() + ""));
				row.appendChild(new Listcell(pTolling.getP14t15() + ""));
				row.appendChild(new Listcell(pTolling.getP100t15() + ""));
				
			}
		});
		
		refreshModel(_startPageNumber);
		
	}
	
	public void onPaging$tollingPaging(ForwardEvent event)
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
		tollingPaging.setPageSize(_pageSize);
		filter.setId_contract(idc);
		model = new PagingListModel(activePage, _pageSize, filter, "");
		
		if (_needsTotalSizeUpdate)
		{
			_totalSize = model.getTotalSize();
			_needsTotalSizeUpdate = false;
		}
		
		tollingPaging.setTotalSize(_totalSize);
		
		dataGrid.setModel((ListModel) model);
		if (model.getSize() > 0)
		{
			this.current = (Tolling) model.getElementAt(0);
			sendSelEvt();
		}
	}
	
	// Omitted...
	public Tolling getCurrent()
	{
		return current;
	}
	
	public void setCurrent(Tolling current)
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
		
		p1t15.setDisabled(true);
		p2t15.setDisabled(true);
		p3t15.setDisabled(true);
		p4t15.setDisabled(true);
		p5t15.setDisabled(true);
		p6t15.setDisabled(true);
		p7t15.setDisabled(true);
		p10t15.setDisabled(true);
		p11t15.setDisabled(true);
		p12t15.setDisabled(true);
		p13t15.setDisabled(true);
		p14t15.setDisabled(true);
		p100t15.setDisabled(true);
		
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
				/*
				 * TollingService.create(new Tolling(
				 * Long.parseLong(aid.getValue()), ap0t15.getValue(),
				 * ap1t15.getValue(), ap2t15.getValue(), ap3t15.doubleValue(),
				 * ap4t15.doubleValue(), ap5t15.doubleValue(),
				 * ap6t15.doubleValue(), ap7t15.getValue() ));
				 */
				CheckNull.clearForm(addgrd);
				frmgrd.setVisible(true);
				addgrd.setVisible(false);
				fgrd.setVisible(false);
			}
			else if (fgrd.isVisible())
			{
				filter = new TollingFilter();
				
				Long.parseLong(fid.getValue());
				filter.setP0t15(fp0t15.getValue());
				filter.setP1t15(fp1t15.getValue());
				filter.setP2t15(fp2t15.getValue());
				filter.setP3t15(fp3t15.doubleValue());
				filter.setP4t15(fp4t15.doubleValue());
				filter.setP5t15(fp5t15.doubleValue());
				filter.setP6t15(fp6t15.doubleValue());
				filter.setP7t15(fp7t15.getValue());
				
			}
			else
			{
				
				Long.parseLong(id.getValue());
				current.setP0t15(p0t15.getValue());
				current.setP1t15(p1t15.getValue());
				current.setP2t15(p2t15.getValue());
				current.setP3t15(p3t15.doubleValue());
				current.setP4t15(p4t15.doubleValue());
				current.setP5t15(p5t15.doubleValue());
				current.setP6t15(p6t15.doubleValue());
				current.setP7t15(p7t15.getValue());
				TollingService.update(current);
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
			filter = new TollingFilter();
		}
		onClick$btn_back();
		frmgrd.setVisible(true);
		addgrd.setVisible(false);
		fgrd.setVisible(false);
		CheckNull.clearForm(addgrd);
		CheckNull.clearForm(fgrd);
		refreshModel(_startPageNumber);
	}
	
	// ******************** Confirm *************************** //
	public void onClick$btn_confirm()
	{
		sendConfirm(1, String.valueOf(current.getP13t15()), current);
	}
	
	public void onClick$btn_reject()
	{
		sendConfirm(0, String.valueOf(current.getP13t15()), current);
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
		
		com.sbs.service.Tolling[] agre = cr.getContract().getTollings();
		XMLSerializer.write(agre, "c:/Tolling.xml");
		if (agre != null)
		{
			for (int i = 0; i < agre.length; i++)
			{
				TollingService.remove(idc);
				
				Res res = TollingService.create(agre[i], idc);
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
			ISLogger.getLogger().warn("ERROR onSelect$Tolling: Data not found");
		}
		refreshModel(_startPageNumber);
	}
}
