package com.is.tf.Barterform;

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

public class BarterformViewCtrl extends GenericForwardComposer
{
	private Div frm;
	private Listbox dataGrid;
	private Paging contactPaging;
	private Div grd;
	private Grid addgrd, frmgrd, fgrd;
	private Toolbarbutton btn_last, btn_confirm, btn_reject, btn_save;
	private Toolbarbutton btn_next;
	private Toolbarbutton btn_prev;
	private Toolbarbutton btn_first;
	private Toolbarbutton btn_add;
	private Toolbarbutton btn_search;
	private Toolbarbutton btn_back;
	private Toolbar tb;
	private RefCBox p1t17;
	private Textbox id, p0t17, p3t17, p4t17, p5t17, p8t17;
	private Textbox aid, ap0t17, ap1t17, ap3t17, ap4t17, ap5t17, ap8t17;
	private Textbox fid, fp0t17, fp1t17, fp3t17, fp4t17, fp5t17, ap100t17;
	private Datebox p2t17, ap2t17, fp2t17, ap9t17, p9t17;
	private Paging barterformPaging;
	private int _pageSize = 15;
	private int _startPageNumber = 0;
	private int _totalSize = 0;
	private boolean _needsTotalSizeUpdate = true;
	private Label line1;
	private Label line2;
	private Label line3;
	private Label line4;
	private Label line5;
	private Label line6;
	private Label line7;
	private String alias, idn, sparam1;
	private long idc;
	public BarterformFilter filter = new BarterformFilter();
	PagingListModel model = null;
	ListModelList lmodel = null;
	private AnnotateDataBinder binder;
	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	private Window contractmain = null; 
	
	private Barterform current = new Barterform();
	
	public BarterformViewCtrl()
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
		
		line1.setValue(Labels.getLabel("barterform.p1t17").replaceAll("<br>", "\r\n"));
		line2.setValue(Labels.getLabel("barterform.p2t17tab").replaceAll("<br>", "\r\n"));
		line3.setValue(Labels.getLabel("barterform.p3t17tab").replaceAll("<br>", "\r\n"));
		line4.setValue(Labels.getLabel("barterform.p4t17tab").replaceAll("<br>", "\r\n"));
		line5.setValue(Labels.getLabel("barterform.p5t17tab").replaceAll("<br>", "\r\n"));
		line6.setValue(Labels.getLabel("barterform.p9t17tab").replaceAll("<br>", "\r\n"));
		line7.setValue(Labels.getLabel("barterform.p100t17tab").replaceAll("<br>", "\r\n"));
		
		dataGrid.setItemRenderer(new ListitemRenderer()
		{
			@SuppressWarnings("unchecked")
			public void render(Listitem row, Object data) throws Exception
			{
				Barterform pBarterform = (Barterform) data;
				
				row.setValue(pBarterform);
				
				row.appendChild(new Listcell(pBarterform.getP1t17()));
				row.appendChild(new Listcell(pBarterform.getP2t17() + ""));
				row.appendChild(new Listcell(pBarterform.getP3t17()));
				row.appendChild(new Listcell(pBarterform.getP4t17()));
				row.appendChild(new Listcell(pBarterform.getP5t17()));
				row.appendChild(new Listcell(pBarterform.getP9t17() + ""));
				row.appendChild(new Listcell(com.is.tf.contract.SPR.getP100Value(String.valueOf(pBarterform.getP100t17()))));
				
			}
		});
		
		p1t17.setModel((new ListModelList(com.is.utils.RefDataService.getDicPaymentMethod(alias))));
		
		refreshModel(_startPageNumber);
		
	}
	
	public void onPaging$barterformPaging(ForwardEvent event)
	{
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModel(_startPageNumber);
	}
	
	public void onClick$btn_refresh() throws Exception 
	{
		refresh(idn);
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
		barterformPaging.setPageSize(_pageSize);
		filter.setId_contract(idc);
		model = new PagingListModel(activePage, _pageSize, filter, "");
		
		if (_needsTotalSizeUpdate)
		{
			_totalSize = model.getTotalSize(filter, "");
		}
		
		barterformPaging.setTotalSize(_totalSize);
		
		dataGrid.setModel((ListModel) model);
		if (model.getSize() > 0)
		{
			this.current = (Barterform) model.getElementAt(0);
			sendSelEvt();
		}
	}
	
	// Omitted...
	public Barterform getCurrent()
	{
		return current;
	}
	
	public void setCurrent(Barterform current)
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
		
		p1t17.setDisabled(true);
		p2t17.setDisabled(true);
		p3t17.setDisabled(true);
		p4t17.setDisabled(true);
		p5t17.setDisabled(true);
		p8t17.setDisabled(true);
		p9t17.setDisabled(true);
		
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
				BarterformService.create(new Barterform(
						Long.parseLong(id.getValue()),
						ap0t17.getValue(),
						ap1t17.getValue(),
						ap2t17.getValue(),
						ap3t17.getValue(),
						ap4t17.getValue(),
						ap5t17.getValue(),
						ap8t17.getValue(),
						ap9t17.getValue(),
						Short.parseShort(ap100t17.getValue())
						));
				CheckNull.clearForm(addgrd);
				frmgrd.setVisible(true);
				addgrd.setVisible(false);
				fgrd.setVisible(false);
			}
			else if (fgrd.isVisible())
			{
				filter = new BarterformFilter();
				
				Long.parseLong(fid.getValue());
				filter.setP0t17(fp0t17.getValue());
				filter.setP1t17(fp1t17.getValue());
				filter.setP2t17(fp2t17.getValue());
				filter.setP3t17(fp3t17.getValue());
				filter.setP4t17(fp4t17.getValue());
				filter.setP5t17(fp5t17.getValue());
				
			}
			else
			{
				
				Long.parseLong(id.getValue());
				current.setP0t17(p0t17.getValue());
				current.setP1t17(p1t17.getValue());
				current.setP2t17(p2t17.getValue());
				current.setP3t17(p3t17.getValue());
				current.setP4t17(p4t17.getValue());
				current.setP5t17(p5t17.getValue());
				
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
			filter = new BarterformFilter();
		}
		onClick$btn_back();
		frmgrd.setVisible(true);
		addgrd.setVisible(false);
		fgrd.setVisible(false);
		CheckNull.clearForm(addgrd);
		CheckNull.clearForm(fgrd);
		refreshModel(_startPageNumber);
	}
	
	public void refresh(String idn) throws Exception
	{
		final BankServiceProxy ws = new BankServiceProxy((String) session.getAttribute("YESVO_URL"));
		ContractResult cr = ws.getContract(((String) session.getAttribute("BankINN")), idn);
		
		com.sbs.service.BarterForm[] act_n = cr.getContract().getBarterForms();
		XMLSerializer.write(act_n, "c:/Barterform.xml");
		if (act_n != null)
		{
			for (int i = 0; i < act_n.length; i++)
			{
				BarterformService.remove(idc);
				Res res = BarterformService.create(act_n[i], idc);
				if (res.getCode() == 0) alert("Îøèáêà:" + res.getName());
			}
		}
		else
		{
			alert("Data not found, BankINN=" + ((String) session.getAttribute("BankINN")));
			ISLogger.getLogger().warn("ERROR onSelect$Act: Data not found");
		}
		refreshModel(_startPageNumber);
	}
	
	// ******************** Confirm  *************************** //
	public void onClick$btn_confirm()
	{
		sendConfirm(1, String.valueOf(current.getP8t17()), current);
	}
	
	public void onClick$btn_reject()
	{
		sendConfirm(0, String.valueOf(current.getP8t17()), current);
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
