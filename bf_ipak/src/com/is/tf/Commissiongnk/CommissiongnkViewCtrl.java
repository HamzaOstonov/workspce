package com.is.tf.Commissiongnk;

import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.refobj.XMLSerializer;
import com.sbs.service.BankServiceProxy;
import com.sbs.service.CommissionGNKResult;

public class CommissiongnkViewCtrl extends GenericForwardComposer
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
	private Textbox id, p1t28, p0t28, p6t28, p7t28, p9t28;
	private Textbox aid, ap1t28, ap0t28, ap6t28, ap7t28, ap9t28, ap10t28;
	private Textbox fid, fp1t28, fp0t28, fp6t28, fp7t28, fp9t28;
	private Datebox p2t28, p8t28, ap2t28, ap8t28, fp2t28, fp8t28;
	private Decimalbox p3t28, p5t28, ap3t28, ap5t28, fp3t28, fp5t28;
	private RefCBox p4t28, ap4t28, fp4t28;
	private Paging commissiongnkPaging;
	private int _pageSize = 15;
	private int _startPageNumber = 0;
	private int _totalSize = 0;
	private boolean _needsTotalSizeUpdate = true;
	private String alias;
	private String idn, val1;
	private long idc;
	private String sparam1;
	
	private Label line1;
	private Label line2;
	private Label line3;
	private Label line4;
	private Label line5;
	private Label line6;
	
	public CommissiongnkFilter filter = new CommissiongnkFilter();
	
	PagingListModel model = null;
	ListModelList lmodel = null;
	private AnnotateDataBinder binder;
	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	
	private Commissiongnk current = new Commissiongnk();
	
	public CommissiongnkViewCtrl()
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
		if (parameter != null) idn = (parameter[0]);
		
		parameter = (String[]) param.get("val1");
		if (parameter != null) val1 = (parameter[0]);
		System.out.println("val1->" + val1);
		
		parameter = (String[]) param.get("idc");
		if (parameter != null) idc = Long.parseLong((parameter[0]));
		
		parameter = (String[]) param.get("spr");
		if (parameter != null) sparam1 = (parameter[0]);
		
		line1.setValue(Labels.getLabel("commissiongnk.p2t28").replaceAll("<br>", "\r\n"));
		line2.setValue(Labels.getLabel("commissiongnk.p3t28tab").replaceAll("<br>", "\r\n"));
		line3.setValue(Labels.getLabel("commissiongnk.p9t28tab").replaceAll("<br>", "\r\n"));
		line4.setValue(Labels.getLabel("commissiongnk.p10t28tab").replaceAll("<br>", "\r\n"));
		line5.setValue(Labels.getLabel("commissiongnk.p13t28tab").replaceAll("<br>", "\r\n"));
		line6.setValue(Labels.getLabel("commissiongnk.p100t28tab").replaceAll("<br>", "\r\n"));
		
		dataGrid.setItemRenderer(new ListitemRenderer()
		{
			@SuppressWarnings("unchecked")
			public void render(Listitem row, Object data) throws Exception
			{
				Commissiongnk pCommissiongnk = (Commissiongnk) data;
				
				row.setValue(pCommissiongnk);
				
				row.appendChild(new Listcell(pCommissiongnk.getP2t28() + ""));
				row.appendChild(new Listcell(pCommissiongnk.getP3t28() + ""));
				row.appendChild(new Listcell(pCommissiongnk.getP9t28()));
				row.appendChild(new Listcell(pCommissiongnk.getP10t28()));
				row.appendChild(new Listcell(String.valueOf(pCommissiongnk.getP13t28())));
				row.appendChild(new Listcell(com.is.tf.contract.SPR.getP100Value(String.valueOf(pCommissiongnk.getP100t28()))));
				
				row.appendChild(new Listcell(pCommissiongnk.getId() + ""));
				row.appendChild(new Listcell(pCommissiongnk.getP1t28()));
				row.appendChild(new Listcell(pCommissiongnk.getP0t28()));
				row.appendChild(new Listcell(pCommissiongnk.getP4t28()));
				row.appendChild(new Listcell(pCommissiongnk.getP5t28() + ""));
				row.appendChild(new Listcell(pCommissiongnk.getP6t28()));
				row.appendChild(new Listcell(pCommissiongnk.getP7t28()));
				row.appendChild(new Listcell(pCommissiongnk.getP8t28() + ""));
			}
		});
		p4t28.setModel((new ListModelList(com.is.utils.RefDataService.getCurrency(alias))));
		ap4t28.setModel((new ListModelList(com.is.utils.RefDataService.getCurrency(alias))));
		fp4t28.setModel((new ListModelList(com.is.utils.RefDataService.getCurrency(alias))));
		
		if (sparam1 != null)
		{
			if (sparam1.equals("Go")) // / ГО
			{
				btn_add.setVisible(false);
			}
		}
		refreshModel(_startPageNumber);
		
	}
	
	public void onPaging$commissiongnkPaging(ForwardEvent event)
	{
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModel(_startPageNumber);
	}
	
	private void refreshModel(int activePage)
	{
		commissiongnkPaging.setPageSize(_pageSize);
		filter.setP1t28(idn);
		model = new PagingListModel(activePage, _pageSize, filter, "");
		
		if (_needsTotalSizeUpdate)
		{
			_totalSize = model.getTotalSize(filter, "");
			// _needsTotalSizeUpdate = false;
		}
		
		commissiongnkPaging.setTotalSize(_totalSize);
		
		dataGrid.setModel((ListModel) model);
		if (model.getSize() > 0)
		{
			this.current = (Commissiongnk) model.getElementAt(0);
			sendSelEvt();
		}
	}
	
	// Omitted...
	public Commissiongnk getCurrent()
	{
		return current;
	}
	
	public void setCurrent(Commissiongnk current)
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
				setFields(false);
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
				setFields(true);
			}
		}
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
	
	public void onClick$btn_refresh() throws Exception
	{
		refresh(idn);
	}
	
	public void onClick$btn_add()
	{
		onDoubleClick$dataGrid$grd();
		ap1t28.setValue(idn);
		ap2t28.setValue(new Date());
		ap8t28.setValue(new Date());
		ap4t28.setSelecteditem(val1);
		ap10t28.setValue((String) session.getAttribute("un"));
		
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
	
	public void onClick$btn_delete()
	{
		try
		{
			
			if (frmgrd.isVisible())
			{
				current.setP0t28("2");
				Long.parseLong(id.getValue());
				current.setP2t28(p2t28.getValue());
				current.setP3t28(p3t28.doubleValue());
				current.setP4t28(p4t28.getValue());
				current.setP5t28(p5t28.doubleValue());
				current.setP6t28(p6t28.getValue());
				current.setP7t28(p7t28.getValue());
				current.setP8t28(p8t28.getValue());
				current.setP9t28(p9t28.getValue());
				
				final BankServiceProxy ws = new BankServiceProxy((String) session.getAttribute("YESVO_URL"));
				CommissionGNKResult ar = ws.saveCommissionGNK(((String) (session.getAttribute("BankINN"))), idn, delCommGNK(current));
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
			com.sbs.service.CommissionGNKResult commgnk = new com.sbs.service.CommissionGNKResult();
			
			if (addgrd.isVisible())
			{
				
				CommissionGNKResult ar = ws.saveCommissionGNK(
						((String) (session.getAttribute("BankINN"))), idn,
						getCommGNK(new Commissiongnk(
								ap2t28.getValue(),
								ap3t28.doubleValue(),
								ap4t28.getValue(),
								ap5t28.doubleValue(),
								ap6t28.getValue(),
								ap7t28.getValue(),
								ap8t28.getValue()
						)));
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
				filter = new CommissiongnkFilter();
				
				Long.parseLong(fid.getValue());
				filter.setP1t28(fp1t28.getValue());
				filter.setP0t28(fp0t28.getValue());
				filter.setP2t28(fp2t28.getValue());
				filter.setP3t28(fp3t28.doubleValue());
				filter.setP4t28(fp4t28.getValue());
				filter.setP5t28(fp5t28.doubleValue());
				filter.setP6t28(fp6t28.getValue());
				filter.setP7t28(fp7t28.getValue());
				filter.setP8t28(fp8t28.getValue());
				filter.setP9t28(fp9t28.getValue());
				
			}
			else
			{
				
				Long.parseLong(id.getValue());
				current.setP2t28(p2t28.getValue());
				current.setP3t28(p3t28.doubleValue());
				current.setP4t28(p4t28.getValue());
				current.setP5t28(p5t28.doubleValue());
				current.setP6t28(p6t28.getValue());
				current.setP7t28(p7t28.getValue());
				current.setP8t28(p8t28.getValue());
				current.setP9t28(p9t28.getValue());
				
				CommissionGNKResult commgnk1 = ws.saveCommissionGNK(((String) (session.getAttribute("BankINN"))), idn, getCommGNKCorr(current));
				
				if (commgnk1.getStatus() == 0)
				{
					alert("Сохранение успешно");
					refresh(idn);
				}
				else
				{
					alert("Error:" + commgnk1.getStatus() + " - " + commgnk1.getErrorMsg());
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
			filter = new CommissiongnkFilter();
		}
		onClick$btn_back();
		frmgrd.setVisible(true);
		addgrd.setVisible(false);
		fgrd.setVisible(false);
		CheckNull.clearForm(addgrd);
		CheckNull.clearForm(fgrd);
		refreshModel(_startPageNumber);
	}
	
	private com.sbs.service.CommissionGNK getCommGNK(Commissiongnk commgnk) throws Exception
	{
		
		java.util.Calendar cal = java.util.Calendar.getInstance();
		com.sbs.service.CommissionGNK res = new com.sbs.service.CommissionGNK();
		
		res.setP0T28(0);
		
		cal.setTime(df.parse(df.format(commgnk.getP2t28())));
		res.setP2T28(cal);
		
		res.setP3T28(commgnk.getP3t28());
		res.setP4T28(commgnk.getP4t28());
		res.setP5T28(commgnk.getP5t28());
		res.setP6T28(commgnk.getP6t28());
		res.setP7T28(commgnk.getP7t28());
		
		cal.setTime(df.parse(df.format(commgnk.getP8t28())));
		res.setP8T28(cal);
		
		res.setP10T28(((String) (session.getAttribute("un"))));
		return res;
	}
	
	private com.sbs.service.CommissionGNK getCommGNKCorr(Commissiongnk commgnk)
	{
		
		java.util.Calendar cal = java.util.Calendar.getInstance();
		com.sbs.service.CommissionGNK res = new com.sbs.service.CommissionGNK();
		
		res.setP0T28(1);
		res.setP2T28(cal);
		cal.setTime(commgnk.getP2t28());
		cal.setTime(commgnk.getP8t28());
		res.setP3T28(commgnk.getP3t28());
		res.setP4T28(commgnk.getP4t28());
		res.setP5T28(commgnk.getP5t28());
		res.setP6T28(commgnk.getP6t28());
		res.setP7T28(commgnk.getP7t28());
		res.setP8T28(cal);
		res.setP9T28(Integer.parseInt(commgnk.getP9t28()));
		res.setP10T28(((String) (session.getAttribute("un"))));
		return res;
	}
	
	private com.sbs.service.CommissionGNK delCommGNK(Commissiongnk commgnk)
	{
		
		java.util.Calendar cal = java.util.Calendar.getInstance();
		com.sbs.service.CommissionGNK res = new com.sbs.service.CommissionGNK();
		
		res.setP0T28(2);
		res.setP2T28(cal);
		cal.setTime(commgnk.getP2t28());
		cal.setTime(commgnk.getP8t28());
		res.setP3T28(commgnk.getP3t28());
		res.setP4T28(commgnk.getP4t28());
		res.setP5T28(commgnk.getP5t28());
		res.setP6T28(commgnk.getP6t28());
		res.setP7T28(commgnk.getP7t28());
		res.setP8T28(cal);
		res.setP9T28(Integer.parseInt(commgnk.getP9t28()));
		res.setP10T28(((String) (session.getAttribute("un"))));
		return res;
	}
	
	public void refresh(String idn) throws Exception
	{
		final BankServiceProxy ws = new BankServiceProxy((String) session.getAttribute("YESVO_URL"));
		com.is.tf.Commissiongnk.CommissiongnkService.remove(new Commissiongnk(), idc);
		com.sbs.service.CommissionsGNKResult Pnt = ws.getCommissionsGNK(((String) (session.getAttribute("BankINN"))), idn);
		try
		{
			XMLSerializer.write(Pnt, "c:/commgnk1.xml");
			for (int i = 0; i < Pnt.getCommissionsGNK().length; i++)
			{
				com.is.tf.Commissiongnk.CommissiongnkService.create(Pnt.getCommissionsGNK()[i], idn, idc);
			}
		}
		catch (Exception e)
		{
			alert("Записи отсутствуют!");
			e.printStackTrace();
		}
		refreshModel(_startPageNumber);
	}
	
	// *********************************** Confirm
	// **************************************
	private Window contractmain = null;
	
	public void onClick$btn_confirm()
	{
		sendConfirm(1, String.valueOf(current.getP9t28()), current);
	}
	
	public void onClick$btn_reject()
	{
		sendConfirm(0, String.valueOf(current.getP9t28()), current);
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
		p2t28.setDisabled(param);
		p3t28.setDisabled(param);
		p4t28.setDisabled(param);
		p5t28.setDisabled(param);
		p6t28.setDisabled(param);
		p7t28.setDisabled(param);
		p8t28.setDisabled(param);
	}
}
