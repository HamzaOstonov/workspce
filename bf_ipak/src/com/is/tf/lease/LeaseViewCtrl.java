package com.is.tf.lease;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.zkoss.zul.Window;
import org.zkoss.zul.event.PagingEvent;

import com.is.ISLogger;
import com.is.tf.contract.ContractService;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.RefData;
import com.is.utils.refobj.XMLSerializer;
import com.sbs.service.BankServiceProxy;
import com.sbs.service.LeaseResult;

public class LeaseViewCtrl extends GenericForwardComposer
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
	private Toolbarbutton btn_confirm, btn_reject, btn_edit, btn_delete, btn_save;
	private Toolbarbutton btn_edit2, btn_confirmBuhg, btn_rejectBuhg, btn_cancel;
	private Toolbar tb;
	private Textbox id, p0t50, p5t50, p6t50, p100t50;
	private Textbox aid, ap0t50, ap5t50, ap6t50, ap100t50;
	private Textbox fid, fp0t50, fp5t50, fp6t50, fp100t50;
	private RefCBox p3t50, ap3t50, fp3t50;
	private Datebox p2t50, ap2t50, fp2t50, p9t50, ap9t50, fpt50;
	private Decimalbox p4t50, ap4t50, fp4t50;
	private Label p1t50, ap1t50, fp1t50;
	private Paging leasePaging;
	private int _pageSize = 15;
	private int _startPageNumber = 0;
	private int _totalSize = 0;
	private boolean _needsTotalSizeUpdate = true;
	private Lease current = new Lease();
	public LeaseFilter filter = new LeaseFilter();
	private List<RefData> currencies = new ArrayList<RefData>();
	private Label line1;
	private Label line2;
	private Label line3;
	private Label line4;
	private Label line5;
	private Label line6;
	private Label line7;
	private Label line8;
	private Window contractmain = null;
	private String sparam1;
	private long idc;
	
	PagingListModel model = null;
	ListModelList lmodel = null;
	private AnnotateDataBinder binder;
	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	private String alias, idn, subj, val1, val2, strval1, strval2;
	
	public LeaseViewCtrl()
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
			ap1t50.setValue(idn);
			fp1t50.setValue(idn);
		}
		parameter = (String[]) param.get("val1");
		if (parameter != null)
		{
			val1 = (parameter[0]);
			// System.out.println("Garant  cont_val1 "+val1);
		}
		
		parameter = (String[]) param.get("idc");
		if (parameter != null) idc = Long.parseLong(parameter[0]);
		
		parameter = (String[]) param.get("spr");
		if (parameter != null) sparam1 = parameter[0];
		System.out.println("sparam1=" + sparam1);
		
		parameter = (String[]) param.get("val2");
		if (parameter != null)
		{
			val2 = (parameter[0]);
			// System.out.println("Garant  cont_val2 "+val2);
		}
		parameter = (String[]) param.get("subj");
		if (parameter != null)
		{
			subj = (parameter[0]);
			// System.out.println("Garant  cont_val2 "+val2);
		}
		alias = (String) session.getAttribute("alias");
		
		currencies = ContractService.getCurr_22t1_23t1(idn, alias);
		p3t50.setModel((new ListModelList(currencies)));
		ap3t50.setModel((new ListModelList(currencies)));
		fp3t50.setModel((new ListModelList(currencies)));
		
		line1.setValue(Labels.getLabel("lease.p2t50tab").replaceAll("<br>", "\r\n"));
		line2.setValue(Labels.getLabel("lease.p4t50tab").replaceAll("<br>", "\r\n"));
		line3.setValue(Labels.getLabel("lease.p3t50tab").replaceAll("<br>", "\r\n"));
		line4.setValue(Labels.getLabel("lease.p5t50tab").replaceAll("<br>", "\r\n"));
		line5.setValue(Labels.getLabel("lease.p6t50tab").replaceAll("<br>", "\r\n"));
		line6.setValue(Labels.getLabel("lease.p9t50tab").replaceAll("<br>", "\r\n"));
		line7.setValue(Labels.getLabel("lease.p100t50tab").replaceAll("<br>", "\r\n"));
		
		dataGrid.setItemRenderer(new ListitemRenderer()
		{
			@SuppressWarnings("unchecked")
			public void render(Listitem row, Object data) throws Exception
		{
			Lease pLease = (Lease) data;
			
			row.setValue(pLease);
			row.appendChild(new Listcell(pLease.getP2t50() + ""));
			row.appendChild(new Listcell(pLease.getP4t50() + ""));
			row.appendChild(new Listcell(pLease.getP3t50()));
			row.appendChild(new Listcell(pLease.getP5t50()));
			row.appendChild(new Listcell(pLease.getP6t50()));
			row.appendChild(new Listcell(String.valueOf(pLease.getP9t50())));
			row.appendChild(new Listcell(com.is.tf.contract.SPR.getP100Value(String.valueOf(pLease.getP100t50()))));
			
		}
		});
		
		if (sparam1 != null)
		{
			if (sparam1.equals("Go")) // / ГО
			{
				btn_add.setVisible(false);
			}
		}
		refreshModel(_startPageNumber);
		
	}
	
	public void onPaging$leasePaging(ForwardEvent event)
	{
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModel(_startPageNumber);
	}
	
	private void refreshModel(int activePage)
	{
		leasePaging.setPageSize(_pageSize);
		filter.setP1t50(idn);
		model = new PagingListModel(activePage, _pageSize, filter, "");
		
		if (_needsTotalSizeUpdate)
		{
			_totalSize = model.getTotalSize(filter, "");
		}
		
		leasePaging.setTotalSize(_totalSize);
		
		dataGrid.setModel((ListModel) model);
		if (model.getSize() > 0)
		{
			this.current = (Lease) model.getElementAt(0);
			sendSelEvt();
		}
	}
	
	// Omitted...
	public Lease getCurrent()
	{
		return current;
	}
	
	public void setCurrent(Lease current)
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
		
		if (sparam1 != null)
		{
			if (sparam1.equals("Filial")) // / Филиал
			{
				btn_save.setVisible(false);
				btn_cancel.setVisible(true);
				btn_reject.setVisible(false);
				btn_confirm.setVisible(false);
				btn_edit.setVisible(true);
				btn_delete.setVisible(false);
				// btn_confirmBuhg.setVisible(false);
				// btn_rejectBuhg.setVisible(false);
				// setFields(false);
			}
			else if (sparam1.equals("Go")) // / ГО
			{
				btn_confirm.setVisible(true);
				btn_reject.setVisible(true);
				btn_cancel.setVisible(true);
				btn_save.setVisible(false);
				btn_edit.setVisible(false);
				btn_delete.setVisible(false);
				// btn_confirmBuhg.setVisible(false);
				// btn_rejectBuhg.setVisible(false);
				// setFields(true);
			}
		}
	}
	
	public void onClick$btn_edit()
	{
		
		if (frmgrd.isVisible())
		{
			btn_save.setVisible(true);
			btn_delete.setVisible(true);
			btn_confirm.setVisible(true);
			btn_reject.setVisible(true);
			setFields(false);
			
		}
		else if (addgrd.isVisible())
		{
			btn_save.setVisible(true);
			btn_delete.setVisible(true);
			btn_confirm.setVisible(true);
			btn_reject.setVisible(true);
		}
	}
	
	public void setFields(boolean param)
	{
		p2t50.setDisabled(param);
		p3t50.setDisabled(param);
		p4t50.setDisabled(param);
		p6t50.setDisabled(param);
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
		ap1t50.setValue(idn);
		ap6t50.setValue((String) session.getAttribute("un"));
		frmgrd.setVisible(false);
		addgrd.setVisible(true);
		fgrd.setVisible(false);
		
		if (sparam1 != null)
		{
			if (sparam1.equals("Filial")) // / Филиал
			{
				btn_save.setVisible(true);
				btn_cancel.setVisible(true);
				btn_reject.setVisible(false);
				btn_confirm.setVisible(false);
				btn_edit.setVisible(false);
				btn_delete.setVisible(false);
				// btn_confirmBuhg.setVisible(false);
				// btn_rejectBuhg.setVisible(false);
			}
			else if (sparam1.equals("Go")) // / ГО
			{
				btn_cancel.setVisible(true);
				btn_save.setVisible(false);
				btn_reject.setVisible(false);
				btn_confirm.setVisible(false);
				btn_edit.setVisible(false);
				btn_delete.setVisible(false);
				// btn_confirmBuhg.setVisible(false);
				// btn_rejectBuhg.setVisible(false);
			}
		}
	}
	
	public void onClick$btn_search()
	{
		onDoubleClick$dataGrid$grd();
		frmgrd.setVisible(false);
		addgrd.setVisible(false);
		fgrd.setVisible(true);
	}
	
	public void onClick$btn_refresh() throws Exception
	{
		refresh(idn);
	}
	
	public void onClick$btn_delete()
	{
		try
		{
			
			final BankServiceProxy ws = new BankServiceProxy((String) session.getAttribute("YESVO_URL"));
			if (frmgrd.isVisible())
			{
				current.setP2t50(p2t50.getValue());
				current.setP3t50(p3t50.getValue());
				current.setP4t50(p4t50.doubleValue());
				current.setP5t50(p5t50.getValue());
				
				LeaseResult ar = ws.saveLease(((String) (session.getAttribute("BankINN"))), idn, delLesCorr(current));
				if (ar.getStatus() == 0)
				{
					refreshModel(_startPageNumber);
					alert("Запрос на удаление передано в ГО");
					refresh(idn);
					
				}
				else
				{
					alert("Ошибка при удалении1!  Error:" + ar.getStatus() + ";  GTKid:" + ar.getGtkId() + ";  Text:" + ar.getErrorMsg());
					refreshModel(_startPageNumber);
				}
				
			}
			onClick$btn_back();
			
			SelectEvent evt = new SelectEvent("onSelect", dataGrid, dataGrid.getSelectedItems());
			Events.sendEvent(evt);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void onClick$btn_save()
	{
		try
		{
			final BankServiceProxy ws = new BankServiceProxy((String) session.getAttribute("YESVO_URL"));
			com.sbs.service.LeaseResult les = new com.sbs.service.LeaseResult();
			
			if (addgrd.isVisible())
			{
				LeaseResult leas = ws.saveLease(((String) (session.getAttribute("BankINN"))), idn, getLeaseNew(new Lease(

				ap2t50.getValue(),
						ap3t50.getValue(),
						ap4t50.doubleValue(),
						ap6t50.getValue()
						)));
				
				CheckNull.clearForm(addgrd);
				if (leas.getStatus() == 0)
				{
					refreshModel(_startPageNumber);
					alert("Сохранение успешно");
					refresh(idn);
				}
				else
				{
					alert("Error save New LeaseExp; Status:" + leas.getStatus() + "; GTKid:" + leas.getGtkId() + "; Text:" + leas.getErrorMsg());
					ISLogger.getLogger().error(" in save New LeaseExp:" + leas.getStatus() + "; GTKid:" + leas.getGtkId() + "; Text:" + leas.getErrorMsg());
					
					CheckNull.clearForm(addgrd);
					frmgrd.setVisible(true);
					addgrd.setVisible(false);
					fgrd.setVisible(false);
					
				}
			}
			else if (fgrd.isVisible())
			{
				filter = new LeaseFilter();
				
				Long.parseLong(fid.getValue());
				filter.setP1t50(fp1t50.getValue());
				filter.setP0t50(fp0t50.getValue());
				filter.setP2t50(fp2t50.getValue());
				filter.setP3t50(fp3t50.getValue());
				filter.setP4t50(fp4t50.doubleValue());
				filter.setP5t50(fp5t50.getValue());
				
			}
			else
			{
				
				Long.parseLong(id.getValue());
				current.setP2t50(p2t50.getValue());
				current.setP3t50(p3t50.getValue());
				current.setP4t50(p4t50.doubleValue());
				current.setP5t50(p5t50.getValue());
				
				LeaseResult ar = ws.saveLease(((String) (session.getAttribute("BankINN"))), p1t50.getValue(), getLesCorr(current));
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
			filter = new LeaseFilter();
		}
		onClick$btn_back();
		frmgrd.setVisible(true);
		addgrd.setVisible(false);
		fgrd.setVisible(false);
		CheckNull.clearForm(addgrd);
		CheckNull.clearForm(fgrd);
		refreshModel(_startPageNumber);
	}
	
	private com.sbs.service.Lease getLeaseNew(Lease acr) throws Exception
	{
		
		java.util.Calendar cal = java.util.Calendar.getInstance();
		com.sbs.service.Lease res = new com.sbs.service.Lease();
		
		res.setP0T50(0);
		
		cal.setTime(df.parse(df.format(acr.getP2t50())));
		res.setP2T50(cal);
		
		res.setP3T50(acr.getP3t50());
		res.setP4T50(acr.getP4t50());
		res.setP6T50((String) session.getAttribute("un"));
		
		return res;
	}
	
	private com.sbs.service.Lease getLesCorr(Lease acr) throws Exception
	{
		java.util.Calendar cal = java.util.Calendar.getInstance();
		com.sbs.service.Lease res = new com.sbs.service.Lease();
		
		res.setP0T50(1);
		
		cal.setTime(df.parse(df.format(acr.getP2t50())));
		res.setP2T50(cal);
		
		res.setP3T50(acr.getP3t50());
		res.setP4T50(acr.getP4t50());
		res.setP5T50(Integer.parseInt(acr.getP5t50()));
		res.setP6T50((String) session.getAttribute("un"));
		return res;
	}
	
	private com.sbs.service.Lease delLesCorr(Lease acr) throws Exception
	{
		java.util.Calendar cal = java.util.Calendar.getInstance();
		com.sbs.service.Lease res = new com.sbs.service.Lease();
		
		res.setP0T50(2);
		
		cal.setTime(df.parse(df.format(acr.getP2t50())));
		res.setP2T50(cal);
		
		res.setP3T50(acr.getP3t50());
		res.setP4T50(acr.getP4t50());
		res.setP5T50(Integer.parseInt(acr.getP5t50()));
		res.setP6T50((String) session.getAttribute("un"));
		return res;
	}
	
	public void refresh(String idn) throws Exception
	{
		final BankServiceProxy ws = new BankServiceProxy((String) session.getAttribute("YESVO_URL"));
		com.is.tf.lease.LeaseService.remove(new Lease(), idc);
		com.sbs.service.LeasesResult debi = ws.getLeases(((String) (session.getAttribute("BankINN"))), idn);
		try
		{
			XMLSerializer.write(debi, "c:/Lease2.xml");
			for (int i = 0; i < debi.getLeases().length; i++)
			{
				com.is.tf.lease.LeaseService.create(debi.getLeases()[i], idn, idc);
			}
		}
		catch (Exception e)
		{
			alert("Записи отсутствуют!");
			e.printStackTrace();
		}
		refreshModel(_startPageNumber);
	}
	
	// ******************** Confirm *************************** //
	public void onClick$btn_confirm()
	{
		sendConfirm(1, String.valueOf(current.getP5t50()), current);
	}
	
	public void onClick$btn_reject()
	{
		sendConfirm(0, String.valueOf(current.getP5t50()), current);
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
