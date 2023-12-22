package com.is.tf.sender;

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
import com.is.utils.RefCBox;
import com.is.utils.Res;
import com.is.utils.refobj.XMLSerializer;
import com.sbs.service.BankServiceProxy;
import com.sbs.service.ContractResult;

public class SenderViewCtrl extends GenericForwardComposer
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
	private RefCBox p2t10, p5t10;
	private Textbox id, p0t10, p1t10, p3t10, p4t10, p6t10, p7t10, p8t10, p9t10, p100t10;
	private Textbox aid, ap0t10, ap1t10, ap2t10, ap3t10, ap4t10, ap5t10, ap6t10, ap7t10;
	private Textbox fid, fp0t10, fp1t10, fp2t10, fp3t10, fp4t10, fp5t10, fp6t10, fp7t10;
	private Datebox p10t10;
	private Paging senderPaging;
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
	
	public SenderFilter filter = new SenderFilter();
	
	PagingListModel model = null;
	ListModelList lmodel = null;
	private AnnotateDataBinder binder;
	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	
	private Sender current = new Sender();
	
	public SenderViewCtrl()
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
		
		line1.setValue(Labels.getLabel("sender.p9t10tab").replaceAll("<br>", "\r\n"));
		line2.setValue(Labels.getLabel("sender.p1t10tab").replaceAll("<br>", "\r\n"));
		line3.setValue(Labels.getLabel("sender.p2t10tab").replaceAll("<br>", "\r\n"));
		line4.setValue(Labels.getLabel("sender.p4t10tab").replaceAll("<br>", "\r\n"));
		line5.setValue(Labels.getLabel("sender.p5t10tab").replaceAll("<br>", "\r\n"));
		line6.setValue(Labels.getLabel("sender.p10t10tab").replaceAll("<br>", "\r\n"));
		line7.setValue(Labels.getLabel("sender.p100t10tab").replaceAll("<br>", "\r\n"));
		
		dataGrid.setItemRenderer(new ListitemRenderer()
		{
			@SuppressWarnings("unchecked")
			public void render(Listitem row, Object data) throws Exception
			{
				Sender pSender = (Sender) data;
				
				row.setValue(pSender);
				
				row.appendChild(new Listcell(pSender.getP9t10()));
				row.appendChild(new Listcell(pSender.getP1t10()));
				row.appendChild(new Listcell(pSender.getP2t10()));
				row.appendChild(new Listcell(pSender.getP4t10()));
				row.appendChild(new Listcell(pSender.getP5t10()));
				row.appendChild(new Listcell(pSender.getP10t10()+""));
				row.appendChild(new Listcell(com.is.tf.contract.SPR.getP100Value(String.valueOf(pSender.getP100t10()))));
			}
		});
		p2t10.setModel((new ListModelList(com.is.utils.RefDataService.getCountry(alias))));
		p5t10.setModel((new ListModelList(com.is.utils.RefDataService.getCountry(alias))));
		refreshModel(_startPageNumber);
		
	}
	
	public void onPaging$senderPaging(ForwardEvent event)
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
		senderPaging.setPageSize(_pageSize);
		filter.setId_contract(idc);
		model = new PagingListModel(activePage, _pageSize, filter, "");
		
		if (_needsTotalSizeUpdate)
		{
			_totalSize = model.getTotalSize();
			_needsTotalSizeUpdate = false;
		}
		
		senderPaging.setTotalSize(_totalSize);
		
		dataGrid.setModel((ListModel) model);
		if (model.getSize() > 0)
		{
			this.current = (Sender) model.getElementAt(0);
			sendSelEvt();
		}
	}
	
	// Omitted...
	public Sender getCurrent()
	{
		return current;
	}
	
	public void setCurrent(Sender current)
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
		
		p1t10.setDisabled(true);   
		p2t10.setDisabled(true);   
		p3t10.setDisabled(true);   
		p4t10.setDisabled(true);   
		p5t10.setDisabled(true);   
		p6t10.setDisabled(true);   
		p7t10.setDisabled(true);   
		p8t10.setDisabled(true);   
		p9t10.setDisabled(true);   
		p10t10.setDisabled(true);  
		p100t10.setDisabled(true); 
		
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
				/*SenderService.create(new Sender(

				Long.parseLong(aid.getValue()),
						ap0t10.getValue(),
						ap1t10.getValue(),
						ap2t10.getValue(),
						ap3t10.getValue(),
						ap4t10.getValue(),
						ap5t10.getValue(),
						ap6t10.getValue(),
						ap7t10.getValue()
						));*/
				CheckNull.clearForm(addgrd);
				frmgrd.setVisible(true);
				addgrd.setVisible(false);
				fgrd.setVisible(false);
			}
			else if (fgrd.isVisible())
			{
				filter = new SenderFilter();
				
				Long.parseLong(fid.getValue());
				filter.setP0t10(fp0t10.getValue());
				filter.setP1t10(fp1t10.getValue());
				filter.setP2t10(fp2t10.getValue());
				filter.setP3t10(fp3t10.getValue());
				filter.setP4t10(fp4t10.getValue());
				filter.setP5t10(fp5t10.getValue());
				filter.setP6t10(fp6t10.getValue());
				filter.setP7t10(fp7t10.getValue());
				
			}
			else
			{
				
				Long.parseLong(id.getValue());
				current.setP0t10(p0t10.getValue());
				current.setP1t10(p1t10.getValue());
				current.setP2t10(p2t10.getValue());
				current.setP3t10(p3t10.getValue());
				current.setP4t10(p4t10.getValue());
				current.setP5t10(p5t10.getValue());
				current.setP6t10(p6t10.getValue());
				current.setP7t10(p7t10.getValue());
				SenderService.update(current);
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
			filter = new SenderFilter();
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
		final BankServiceProxy ws = new BankServiceProxy((String) session.getAttribute("YESVO_URL"));
		ContractResult cr = ws.getContract(((String) session.getAttribute("BankINN")), idn);
		
		com.sbs.service.Sender[] agre = cr.getContract().getSenders();
		XMLSerializer.write(agre, "c:/tfSender.xml");
		if (agre != null)
		{
			for (int i = 0; i < agre.length; i++)
			{
				SenderService.remove(idc);
				
				Res res = SenderService.create(agre[i], idc);
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
			ISLogger.getLogger().warn("ERROR onSelect$tfSender: Data not found");
		}
		refreshModel(_startPageNumber);
	}
	
	// ******************** Confirm  *************************** //
	public void onClick$btn_confirm()
	{
		sendConfirm(1, String.valueOf(current.getP9t10()), current);
	}
	
	public void onClick$btn_reject()
	{
		sendConfirm(0, String.valueOf(current.getP9t10()), current);
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
	
}
