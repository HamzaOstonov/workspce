package com.is.tf.penalty;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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
import org.zkoss.zul.event.PagingEvent;
import org.zkoss.zul.impl.InputElement;

import com.is.tf.contract.ContractService;
import com.is.tf.currency.RefCurrencyData;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.RefData;
import com.is.utils.refobj.XMLSerializer;
import com.sbs.service.BankServiceProxy;
import com.sbs.service.PenaltyResult;

public class PenaltyViewCtrl extends GenericForwardComposer
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
	private Textbox id, p0t26, p6t26, p7t26, p1t26, ap1t26, fp1t26;
	private Textbox aid, ap0t26, ap6t26, ap7t26;
	private Textbox fid, fp0t26, fp6t26, fp7t26;
	private RefCBox p2t26, ap2t26, fp2t26, p4t26, ap4t26, fp4t26;
	private Datebox p3t26, ap3t26, fp3t26;
	private Paging penaltyPaging;
	private Decimalbox ap5t26, fp5t26, p5t26;
	private List<RefData> currencies = new ArrayList<RefData>();
	private List<RefData> penalty_type = new ArrayList<RefData>();
	private List<RefData> agriments = new ArrayList<RefData>();
	private HashMap<String, String> curr_ = null;
	private Label pen_val;
	private List<RefCurrencyData> currenciesg = new ArrayList<RefCurrencyData>();
	private int _pageSize = 15;
	private int _startPageNumber = 0;
	private int _totalSize = 0;
	private boolean _needsTotalSizeUpdate = true;
	private String alias;
	private String idn, val1, val2, cu;
	private long gid, idc;
	private int desktopHeight = 0;
	public PenaltyFilter filter = new PenaltyFilter();
	private Label line1;
	private Label line2;
	private Label line3;
	private Label line4;
	private Label line5;
	private Label line6;
	private Label line7;
	private Label line8;
	
	PagingListModel model = null;
	ListModelList lmodel = null;
	private AnnotateDataBinder binder;
	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	
	private Penalty current = new Penalty();
	
	public PenaltyViewCtrl()
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
		if (parameter != null)
		{
			idn = (parameter[0]);
			// System.out.println("Garant  cont_idn "+idn);
		}
		parameter = (String[]) param.get("idc");
		if (parameter != null)
		{
			idc = Long.parseLong((parameter[0]));
			// System.out.println("ID  "+idc+" idn  "+idn);
			// System.out.println("Garant  cont_idn "+idn);
		}
		parameter = (String[]) param.get("val1");
		if (parameter != null)
		{
			val1 = (parameter[0]);
			// System.out.println("Garant  cont_val1 "+val1);
		}
		parameter = (String[]) param.get("val2");
		if (parameter != null)
		{
			val2 = (parameter[0]);
			// System.out.println("Garant  cont_val2 "+val2);
		}
		curr_ = com.is.tf.contract.ContractService.getHCurr(alias);
		
		filter.setP1t26(idn);
		
		line1.setValue(Labels.getLabel("penalty.p3t26").replaceAll("<br>", "\r\n"));
		line2.setValue(Labels.getLabel("penalty.p5t26").replaceAll("<br>", "\r\n"));
		line3.setValue(Labels.getLabel("penalty.p4t26").replaceAll("<br>", "\r\n"));
		line4.setValue(Labels.getLabel("penalty.p2t26").replaceAll("<br>", "\r\n"));
		line5.setValue(Labels.getLabel("penalty.p7t26t").replaceAll("<br>", "\r\n"));
		line6.setValue(Labels.getLabel("penalty.p8t26t").replaceAll("<br>", "\r\n"));
		line7.setValue(Labels.getLabel("penalty.p11t26t").replaceAll("<br>", "\r\n"));
		line8.setValue(Labels.getLabel("penalty.p100t26t").replaceAll("<br>", "\r\n"));
		
		// filter.setId_contract(idc);
		
		dataGrid.setItemRenderer(new ListitemRenderer()
		{
			@SuppressWarnings("unchecked")
			public void render(Listitem row, Object data) throws Exception
			{
				Penalty pPenalty = (Penalty) data;
				
				row.setValue(pPenalty);
				
				row.appendChild(new Listcell(pPenalty.getP3t26() + ""));
				row.appendChild(new Listcell(String.valueOf(pPenalty.getP5t26())));
				row.appendChild(new Listcell(com.is.tf.contract.SPR.getVal(pPenalty.getP4t26())));
				row.appendChild(new Listcell(com.is.tf.contract.SPR.getPenaltyType(pPenalty.getP2t26())));
				row.appendChild(new Listcell(String.valueOf(pPenalty.getP7t26())));
				row.appendChild(new Listcell(pPenalty.getP8t26()));
				row.appendChild(new Listcell(pPenalty.getP11t26() + ""));
				row.appendChild(new Listcell(com.is.tf.contract.SPR.getP100Value(pPenalty.getP100t26())));
				
				/*
				 * row.appendChild(new Listcell(pPenalty.getId()+""));
				 * row.appendChild(new Listcell(pPenalty.getP1t26()));
				 * row.appendChild(new Listcell(pPenalty.getP0t26()));
				 * row.appendChild(new Listcell(pPenalty.getP6t26()));
				 */

			}
		});
		currencies = com.is.tf.contract.ContractService.getMyCurrency(alias);
		penalty_type = ContractService.getPenalty_type(alias);
		agriments = com.is.tf.contract.ContractService.getAgreement(idn, alias);
		
		p4t26.setModel((new ListModelList(currencies)));
		p2t26.setModel((new ListModelList(penalty_type)));
		ap2t26.setModel((new ListModelList(penalty_type)));
		fp2t26.setModel((new ListModelList(penalty_type)));
		ap4t26.setModel((new ListModelList(currencies)));
		fp4t26.setModel((new ListModelList(currencies)));
		refreshModel(_startPageNumber);
		
	}
	
	public void onPaging$penaltyPaging(ForwardEvent event)
	{
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModel(_startPageNumber);
	}
	
	private void refreshModel(int activePage)
	{
		penaltyPaging.setPageSize(_pageSize);
		model = new PagingListModel(activePage, _pageSize, filter, "");
		
		if (_needsTotalSizeUpdate)
		{
			_totalSize = model.getTotalSize();
			_needsTotalSizeUpdate = false;
		}
		
		penaltyPaging.setTotalSize(_totalSize);
		
		dataGrid.setModel((ListModel) model);
		if (model.getSize() > 0)
		{
			this.current = (Penalty) model.getElementAt(0);
			sendSelEvt();
		}
	}
	
	// Omitted...
	public Penalty getCurrent()
	{
		return current;
	}
	
	public void setCurrent(Penalty current)
	{
		this.current = current;
	}
	
	public void onDoubleClick$dataGrid$grd()
	{
		grd.setVisible(false);
		frm.setVisible(true);
		frmgrd.setVisible(true);
		
		InputElement[] list = { id, p1t26, ap1t26, fp1t26 };
		for (int i = 0; i < list.length; i++)
		{
			if (list[i] != null)
			{
				list[i].setDisabled(true);
			}
		}
		
		addgrd.setVisible(false);
		fgrd.setVisible(false);
		btn_back.setImage("/images/folder.png");
		btn_back.setLabel(Labels.getLabel("grid"));
		pen_val.setValue(curr_.get(val1));
		
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
		ap1t26.setValue(idn);
		ap3t26.setValue(new Date());
		ap4t26.setSelecteditem(val1);
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
			final BankServiceProxy ws = new BankServiceProxy((String) session.getAttribute("YESVO_URL"));
			com.sbs.service.Penalty pnl = new com.sbs.service.Penalty();
			
			if (addgrd.isVisible())
			{
				PenaltyResult ar = ws.savePenalty(
						((String) (session.getAttribute("BankINN"))), idn, getPnt(
								new Penalty(
										ap2t26.getValue(),
										ap3t26.getValue(),
										ap4t26.getValue(),
										ap5t26.doubleValue(),
										ap6t26.getValue()
						))
						);
				
				CheckNull.clearForm(addgrd);
				if (ar.getStatus() == 0)
				{
					refreshModel(_startPageNumber);
					alert("Сохранение успешно");
					refresh(idn);
				}
				else
				{
					alert("Error:" + ar.getStatus() + "; GTKid:" + ar.getGtkId() + "; Text:" + ar.getErrorMsg());
				}
				frmgrd.setVisible(true);
				addgrd.setVisible(false);
				fgrd.setVisible(false);
			}
			else if (fgrd.isVisible())
			{
				filter = new PenaltyFilter();
				
				Long.parseLong(fid.getValue());
				filter.setP1t26(String.valueOf(fp1t26.getValue()));
				filter.setP0t26(fp0t26.getValue());
				filter.setP2t26(fp2t26.getValue());
				filter.setP3t26(fp3t26.getValue());
				filter.setP4t26(fp4t26.getValue());
				filter.setP5t26(Double.valueOf(String.valueOf(fp5t26.getValue())));
				filter.setP6t26(fp6t26.getValue());
				filter.setP7t26(Integer.parseInt(fp7t26.getValue()));
				
			}
			else
			{
				
				Long.parseLong(id.getValue());
				current.setP1t26(String.valueOf(p1t26.getValue()));
				current.setP2t26(p2t26.getValue());
				current.setP3t26(p3t26.getValue());
				current.setP4t26(p4t26.getValue());
				current.setP5t26(Double.valueOf(String.valueOf(p5t26.getValue())));
				current.setP6t26(p6t26.getValue());
				current.setP7t26(Integer.parseInt(p7t26.getValue()));
				
				PenaltyResult ar = ws.savePenalty(((String) (session.getAttribute("BankINN"))), idn, getPntCorr(current));
				if (ar.getStatus() == 0)
				{
					refreshModel(_startPageNumber);
					alert("Сохранение успешно");
					refresh(idn);
				}
				else
				{
					alert("Error:" + ar.getStatus() + "; GTKid:" + ar.getGtkId() + "; Text:" + ar.getErrorMsg());
				}
				
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
			filter = new PenaltyFilter();
		}
		
		onClick$btn_back();
		frmgrd.setVisible(true);
		addgrd.setVisible(false);
		fgrd.setVisible(false);
		CheckNull.clearForm(addgrd);
		CheckNull.clearForm(fgrd);
		refreshModel(_startPageNumber);
	}
	
	private com.sbs.service.Penalty getPnt(Penalty acr)
	{
		java.util.Calendar cal = java.util.Calendar.getInstance();
		com.sbs.service.Penalty res = new com.sbs.service.Penalty();
		
		res.setP0T26(0);
		res.setP2T26(Short.parseShort(acr.getP2t26()));
		cal.setTime(acr.getP3t26());
		res.setP3T26(cal);
		res.setP4T26((acr.getP4t26()));
		res.setP5T26(Double.valueOf(String.valueOf(acr.getP5t26())));
		res.setP6T26(acr.getP6t26());
		// res.setP7T26(acr.getP7t26());
		res.setP8T26((String) session.getAttribute("un"));
		
		return res;
	}
	
	private com.sbs.service.Penalty getPntCorr(Penalty acr)
	{
		java.util.Calendar cal = java.util.Calendar.getInstance();
		com.sbs.service.Penalty res = new com.sbs.service.Penalty();
		
		res.setP0T26(1);
		res.setP2T26(Short.parseShort(acr.getP2t26()));
		cal.setTime(acr.getP3t26());
		res.setP3T26(cal);
		res.setP4T26((acr.getP4t26()));
		res.setP5T26(Double.valueOf(String.valueOf(acr.getP5t26())));
		res.setP6T26(acr.getP6t26());
		res.setP7T26(acr.getP7t26());
		res.setP8T26((String) session.getAttribute("un"));
		
		return res;
	}
	
	public void refresh(String idn) throws Exception
	{
		final BankServiceProxy ws = new BankServiceProxy((String) session.getAttribute("YESVO_URL"));
		com.is.tf.penalty.PenaltyService.remove(new Penalty(), idc);
		com.sbs.service.PenaltiesResult Pnt = ws.getPenalties(((String) (session.getAttribute("BankINN"))), idn);
		XMLSerializer.write(Pnt, "c:/pen1.xml");
		
		for (int i = 0; i < Pnt.getPenalties().length; i++)
		{
			com.is.tf.penalty.PenaltyService.create(Pnt.getPenalties()[i], idn, idc);
		}
		
	}
	
}
