package com.is.tf.Commission;

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
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Decimalbox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Row;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;
import org.zkoss.zul.event.PagingEvent;

import com.is.tf.contract.ContractService;
import com.is.tf.currency.RefCurrencyBox;
import com.is.tf.currency.RefCurrencyData;
import com.is.tf.fund.Fund;
import com.is.utils.CheckNull;
import com.is.utils.RefData;
import com.is.utils.refobj.RefObjCBox;
import com.is.utils.refobj.RefObjData;
import com.is.utils.refobj.XMLSerializer;
import com.sbs.service.BankServiceProxy;
import com.sbs.service.CommissionResult;

public class CommissionViewCtrl extends GenericForwardComposer
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
	private Textbox id, p1t27, p8t27, p11t27, p12t27, p13t27;
	private Textbox ap8t27, ap11t27, ap12t27, ap13t27, aid, ap1t27;
	private Textbox fid, fp1t27, fp2t27;
	private Datebox p7t27, ap7t27, fp7t27, p14t27, ap14t27;
	private Decimalbox p10t272, ap10t272, p10t27, ap10t27, p3t27, p4t27, p5t27, ap3t27, ap4t27, ap5t27, fp3t27, fp4t27, fp5t27;
	private RefObjCBox p2t27, p9t27, ap9t27, ap2t27;
	private List<RefData> postuplenie = new ArrayList<RefData>();
	private List<RefData> oplata = new ArrayList<RefData>();
	private List<RefObjData> oplata2 = new ArrayList<RefObjData>();
	private HashMap<String, String> curr_ = null;
	private Intbox p0t27, p6t27, ap0t27, ap6t27, fp0t27, fp6t27;
	private Paging commissionPaging;
	private Checkbox checksum, achecksum;
	private RefCurrencyBox ap10t271, ap10t273, p10t271, p10t273;
	private Row row_p5t27, row_p4t27, row_fp5t27, row_fp4t27, row_ap5t27, row_ap4t27, row_ap2t27, row_ap9t27, row_p2t27, row_p9t27, kur, akur;
	private int _pageSize = 15;
	private Label cbcourse, acbcourse, conr_val2;
	private Label aconr_val2, aconr_val1, conr_val1, conr_val1a, conr_val2a, gar_val;
	private int _startPageNumber = 0;
	private int _totalSize = 0;
	private boolean _needsTotalSizeUpdate = true;
	private String afund_val, fund_val, cont_type, idn, val1, val2, cu, apayment_val;
	private long gid, idc;
	private String alias, rc, rr, aotn, summa1, summa2, otn, sparam1;
	private Date rdate;
	private int desktopHeight = 0;
	private RefObjCBox p4t35, ap4t36, fp4t36;
	private RefObjCBox p13t35, ap14t36, fp14t36;
	private List<RefObjData> funds = new ArrayList<RefObjData>();
	private List<RefObjData> movefromexs = new ArrayList<RefObjData>();
	private List<RefCurrencyData> currenciesg = new ArrayList<RefCurrencyData>();
	private List<RefCurrencyData> coursecurrencies = new ArrayList<RefCurrencyData>();
	private List<RefCurrencyData> acoursecurrencies = new ArrayList<RefCurrencyData>();
	public CommissionFilter filter = new CommissionFilter();
	
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
	
	private Commission current = new Commission();
	
	public CommissionViewCtrl()
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
		
		parameter = (String[]) param.get("summa1");
		if (parameter != null) summa1 = (parameter[0]);
		
		parameter = (String[]) param.get("spr");
		if (parameter != null) sparam1 = (parameter[0]);
		
		parameter = (String[]) param.get("idn");
		if (parameter != null)
		{
			idn = (parameter[0]);
		}
		parameter = (String[]) param.get("idc");
		if (parameter != null)
		{
			idc = Long.parseLong((parameter[0]));
		}
		parameter = (String[]) param.get("val1");
		if (parameter != null)
		{
			val1 = (parameter[0]);
			
		}
		parameter = (String[]) param.get("cont_type");
		if (parameter != null)
		{
			cont_type = (parameter[0]);
			System.out.println("cont_type=" + cont_type);
		}
		curr_ = com.is.tf.contract.ContractService.getHCurr(alias);
		
		parameter = (String[]) param.get("val2");
		if (parameter != null) val2 = (parameter[0]);
		
		if (CheckNull.isEmpty(val2) || val2.equals("null"))
		{
			row_p5t27.setVisible(false);
			row_ap5t27.setVisible(false);
			row_fp5t27.setVisible(false);
		}
		else
		{
			row_p5t27.setVisible(true);
			row_ap5t27.setVisible(true);
			row_fp5t27.setVisible(true);
		}
		
		conr_val1.setValue(curr_.get(val1));
		conr_val2.setValue(curr_.get(val2));
		
		aconr_val1.setValue(curr_.get(val1));
		aconr_val2.setValue(curr_.get(val2));
		
		filter.setP1t27(idn);
		filter.setId_contract(idc);
		
		line1.setValue(Labels.getLabel("commission.p3t27tab").replaceAll("<br>", "\r\n"));
		// line2.setValue(Labels.getLabel("commission.p9t27").replaceAll("<br>",
		// "\r\n"));
		line2.setValue("Валюта".replaceAll("<br>", "\r\n"));
		line3.setValue(Labels.getLabel("commission.p2t27tab").replaceAll("<br>", "\r\n"));
		line4.setValue(Labels.getLabel("commission.p9t27tab").replaceAll("<br>", "\r\n"));
		line5.setValue(Labels.getLabel("commission.p6t27tab").replaceAll("<br>", "\r\n"));
		line6.setValue(Labels.getLabel("commission.p8t27tab").replaceAll("<br>", "\r\n"));
		line7.setValue(Labels.getLabel("commission.p14t27tab").replaceAll("<br>", "\r\n"));
		line8.setValue(Labels.getLabel("commission.p100t27tab").replaceAll("<br>", "\r\n"));
		
		// System.out.println("doAfterCompose  cont_type1  "+cont_type+" idn "+idn+" idc "+idc);
		dataGrid.setItemRenderer(new ListitemRenderer()
		{
			@SuppressWarnings("unchecked")
			public void render(Listitem row, Object data) throws Exception
			{
				Commission pCommission = (Commission) data;
				
				row.setValue(pCommission);
				
				/*
				 * row.appendChild(new Listcell(pCommission.getId()+""));
				 * row.appendChild(new Listcell(pCommission.getP0t27()+""));
				 * row.appendChild(new Listcell(pCommission.getP1t27()));
				 * row.appendChild(new Listcell(pCommission.getP2t27()));
				 * row.appendChild(new Listcell(pCommission.getP3t27()+""));
				 * row.appendChild(new Listcell(pCommission.getP4t27()+""));
				 * row.appendChild(new Listcell(pCommission.getP5t27()+""));
				 * row.appendChild(new Listcell(pCommission.getP6t27()+""));
				 * row.appendChild(new Listcell(pCommission.getP7t27()+""));
				 * row.appendChild(new
				 * Listcell(pCommission.getId_contract()+""));
				 * row.appendChild(new Listcell(pCommission.getP100t27()));
				 * row.appendChild(new Listcell(pCommission.getP8t27()));
				 * row.appendChild(new Listcell(pCommission.getP10t27()+""));
				 * row.appendChild(new Listcell(pCommission.getP11t27()));
				 * row.appendChild(new Listcell(pCommission.getP12t27()));
				 * row.appendChild(new Listcell(pCommission.getP13t27()));
				 * row.appendChild(new Listcell(pCommission.getP14t27()+""));
				 * row.appendChild(new Listcell(pCommission.getP9t27()));
				 */

				row.appendChild(new Listcell(pCommission.getId() + ""));
				row.appendChild(new Listcell(pCommission.getP0t27() + ""));
				row.appendChild(new Listcell(pCommission.getP1t27()));
				
				row.appendChild(new Listcell(pCommission.getP3t27() + ""));
				row.appendChild(new Listcell(com.is.tf.contract.SPR.getVal(String.valueOf(val2))));
				row.appendChild(new Listcell(pCommission.getP2t27()));
				row.appendChild(new Listcell(pCommission.getP9t27()));
				row.appendChild(new Listcell(pCommission.getP6t27() + ""));
				row.appendChild(new Listcell(pCommission.getP8t27()));
				row.appendChild(new Listcell(pCommission.getP14t27() + ""));
				row.appendChild(new Listcell(com.is.tf.contract.SPR.getP100Value(String.valueOf(pCommission.getP100t27()))));
				
				System.out.println("pCommission.getP10t27()->" + pCommission.getP10t27());
				row.appendChild(new Listcell(pCommission.getP10t27() + ""));
				
				/*
				 * row.appendChild(new Listcell(pCommission.getP4t27()+""));
				 * row.appendChild(new Listcell(pCommission.getP5t27()+""));
				 * row.appendChild(new Listcell(pCommission.getP7t27()+""));
				 * row.appendChild(new
				 * Listcell(pCommission.getId_contract()+""));
				 * 
				 * row.appendChild(new Listcell(pCommission.getP11t27()));
				 * row.appendChild(new Listcell(pCommission.getP12t27()));
				 * row.appendChild(new Listcell(pCommission.getP13t27()));
				 */
			}
		});
		
		postuplenie = com.is.tf.contract.ContractService.getPostup(idn, alias);
		funds = com.is.tf.contract.ContractService.getFunds(idn, alias);
		oplata = com.is.tf.contract.ContractService.getOplata(idn, alias);
		oplata2 = com.is.tf.contract.ContractService.getOplataRef(idn, alias);
		
		p2t27.setModel((new ListModelList(oplata2)));
		ap2t27.setModel((new ListModelList(oplata2)));
		p9t27.setModel((new ListModelList(funds)));
		ap9t27.setModel((new ListModelList(funds)));
		
		System.out.println("dataGrid$  cont_type  " + cont_type);
		if ((cont_type.equalsIgnoreCase("01") || (cont_type.equalsIgnoreCase("03") || (cont_type.equalsIgnoreCase("04") || (cont_type.equals("08") || (cont_type.equals("09") || (cont_type.equals("11"))))))))
		{
			row_ap2t27.setVisible(false);
			row_ap9t27.setVisible(true);
		}
		else if (cont_type.equalsIgnoreCase("02") || (cont_type.equals("05")))
		{
			row_ap2t27.setVisible(true);
			row_ap9t27.setVisible(false);
		}
		
		if (sparam1 != null)
		{
			if (sparam1.equals("Go")) // / ГО
			{
				btn_add.setVisible(false);
			}
		}
		
		refreshModel(_startPageNumber);
		
	}
	
	public void onPaging$commissionPaging(ForwardEvent event)
	{
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModel(_startPageNumber);
	}
	
	private void refreshModel(int activePage)
	{
		commissionPaging.setPageSize(_pageSize);
		filter.setP1t27(idn);
		model = new PagingListModel(activePage, _pageSize, filter, "");
		
		if (_needsTotalSizeUpdate)
		{
			_totalSize = model.getTotalSize(filter, "");
			// _needsTotalSizeUpdate = false;
		}
		
		commissionPaging.setTotalSize(_totalSize);
		
		dataGrid.setModel((ListModel) model);
		if (model.getSize() > 0)
		{
			this.current = (Commission) model.getElementAt(0);
			sendSelEvt();
		}
		
	}
	
	// Omitted...
	public Commission getCurrent()
	{
		return current;
	}
	
	public void setCurrent(Commission current)
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
				btn_delete.setVisible(false);
				btn_reject.setVisible(false);
				btn_confirm.setVisible(false);
				btn_edit.setVisible(true);
				setFields(false);
			}
			else if (sparam1.equals("Go")) // / ГО
			{
				btn_confirm.setVisible(true);
				btn_reject.setVisible(true);
				btn_delete.setVisible(false);
				btn_cancel.setVisible(true);
				btn_save.setVisible(false);
				btn_edit.setVisible(false);
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
		setCurrent();
	}
	
	public void onClick$btn_add()
	{
		onDoubleClick$dataGrid$grd();
		frmgrd.setVisible(false);
		addgrd.setVisible(true);
		fgrd.setVisible(false);
		ap1t27.setValue(idn);
		ap8t27.setValue((String) session.getAttribute("un"));
		
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
				// btn_edit2.setVisible(false);
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
				// btn_edit2.setVisible(false);
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
				current.setP2t27(p2t27.getValue());
				current.setP9t27(p9t27.getValue());
				current.setP3t27(p3t27.doubleValue());
				current.setP4t27(p4t27.doubleValue());
				current.setP5t27(p5t27.doubleValue());
				current.setP6t27(p6t27.intValue());
				current.setP10t27(Double.parseDouble(String.valueOf(p10t272.getValue())));
				current.setP13t27(p13t27.getValue());
				current.setP14t27(p14t27.getValue());
				
				final BankServiceProxy ws = new BankServiceProxy((String) session.getAttribute("YESVO_URL"));
				CommissionResult ar = ws.saveCommission(((String) (session.getAttribute("BankINN"))), idn, delComm(current));
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
	
	public void refresh(String idn) throws Exception
	{
		final BankServiceProxy ws = new BankServiceProxy((String) session.getAttribute("YESVO_URL"));
		com.is.tf.Commission.CommissionService.remove(new Commission(), idc);
		com.sbs.service.CommissionsResult debi = ws.getCommissions(((String) (session.getAttribute("BankINN"))), idn);
		try
		{
			XMLSerializer.write(debi, "c:/Comm1.xml");
			for (int i = 0; i < debi.getCommissions().length; i++)
			{
				com.is.tf.Commission.CommissionService.create(debi.getCommissions()[i], idn, idc);
			}
		}
		catch (Exception e)
		{
			alert("Записи отсутствуют!");
			e.printStackTrace();
		}
		refreshModel(_startPageNumber);
		
	}
	
	public void onClick$btn_refresh() throws Exception
	{
		refresh(idn);
	}
	
	public void onClick$btn_save()
	{
		try
		{
			final BankServiceProxy ws = new BankServiceProxy((String) session.getAttribute("YESVO_URL"));
			com.sbs.service.CommissionResult comm = new com.sbs.service.CommissionResult();
			
			if (addgrd.isVisible())
			{
				// Временно из за val2="null"
				String temp = String.valueOf(ap10t272.getValue());
				double temp_s;
				if (temp.equals("null")) temp_s = 0;
				else temp_s = Double.parseDouble(temp);
				
				CommissionResult ar = ws.saveCommission(((String) (session.getAttribute("BankINN"))), idn, getComm(new Commission(ap2t27.getValue(), ap3t27.doubleValue(), ap4t27.doubleValue(), ap5t27.doubleValue(), ap8t27.getValue(),
				// Double.parseDouble(temp),
				        temp_s, ap9t27.getValue())));
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
				filter = new CommissionFilter();
				
				Long.parseLong(fid.getValue());
				filter.setP0t27(fp0t27.intValue());
				filter.setP1t27(fp1t27.getValue());
				filter.setP2t27(fp2t27.getValue());
				filter.setP3t27(fp3t27.doubleValue());
				filter.setP4t27(fp4t27.doubleValue());
				filter.setP5t27(fp5t27.doubleValue());
				filter.setP6t27(fp6t27.intValue());
				filter.setP7t27(fp7t27.getValue());
				
			}
			else
			{
				
				System.out.println("p10t272.doubleValue()->" + p10t272.doubleValue());
				
				Long.parseLong(id.getValue());
				current.setP2t27(p2t27.getValue());
				current.setP9t27(p9t27.getValue());
				current.setP3t27(p3t27.doubleValue());
				current.setP4t27(p4t27.doubleValue());
				current.setP5t27(p5t27.doubleValue());
				current.setP6t27(p6t27.intValue());
				current.setP10t27(Double.parseDouble(String.valueOf(p10t272.getValue())));
				current.setP13t27(p13t27.getValue());
				current.setP14t27(p14t27.getValue());
				
				CommissionResult comm1 = ws.saveCommission(((String) (session.getAttribute("BankINN"))), idn, getCommCorr(current));
				
				if (comm1.getStatus() == 0) alert("Сохранение успешно");
				else alert("Error:" + comm1.getStatus() + "; GTKid:" + comm1.getGtkId() + "; Text:" + comm1.getErrorMsg());
				
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
			filter = new CommissionFilter();
		}
		onClick$btn_back();
		frmgrd.setVisible(true);
		addgrd.setVisible(false);
		fgrd.setVisible(false);
		CheckNull.clearForm(addgrd);
		CheckNull.clearForm(fgrd);
		refreshModel(_startPageNumber);
	}
	
	private com.sbs.service.Commission getComm(Commission comm)
	{
		java.util.Calendar cal = java.util.Calendar.getInstance();
		com.sbs.service.Commission res = new com.sbs.service.Commission();
		
		res.setP0T27(0);
		res.setP13T27(Short.parseShort(aotn));
		
		System.out.println("comm.getP2t27()->" + comm.getP2t27());
		System.out.println("comm.getP9t27()->" + comm.getP9t27());
		
		if (!CheckNull.isEmpty(comm.getP2t27())) res.setP2T27(Integer.parseInt(comm.getP2t27()));
		if (!CheckNull.isEmpty(comm.getP9t27())) res.setP9T27(Integer.parseInt(comm.getP9t27()));
		res.setP3T27(comm.getP3t27());
		res.setP4T27(comm.getP4t27());
		res.setP5T27(comm.getP5t27());
		res.setP6T27(comm.getP6t27());
		res.setP8T27((String) session.getAttribute("un"));
		
		if (!CheckNull.isEmpty(comm.getP10t27()) || !comm.getP10t27().equals("null") || comm.getP10t27() != 0) res.setP10T27(comm.getP10t27());
		return res;
	}
	
	private com.sbs.service.Commission getCommCorr(Commission comm)
	{
		java.util.Calendar cal = java.util.Calendar.getInstance();
		com.sbs.service.Commission res = new com.sbs.service.Commission();
		
		res.setP0T27(1);
		if (!CheckNull.isEmpty(comm.getP2t27())) res.setP2T27(Integer.parseInt(comm.getP2t27()));
		if (!CheckNull.isEmpty(comm.getP9t27())) res.setP9T27(Integer.parseInt(comm.getP9t27()));
		res.setP13T27(Short.parseShort(otn));
		res.setP3T27(comm.getP3t27());
		res.setP4T27(comm.getP4t27());
		res.setP5T27(comm.getP5t27());
		res.setP6T27(comm.getP6t27());
		
		res.setP8T27((String) session.getAttribute("un"));
		
		if (!CheckNull.isEmpty(comm.getP10t27())) res.setP10T27(comm.getP10t27());
		return res;
	}
	
	private com.sbs.service.Commission delComm(Commission comm)
	{
		java.util.Calendar cal = java.util.Calendar.getInstance();
		com.sbs.service.Commission res = new com.sbs.service.Commission();
		
		res.setP0T27(2);
		
		onChange$p3t27();
		res.setP13T27(Short.parseShort(otn));
		
		if (!CheckNull.isEmpty(comm.getP2t27())) res.setP2T27(Integer.parseInt(comm.getP2t27()));
		if (!CheckNull.isEmpty(comm.getP9t27())) res.setP9T27(Integer.parseInt(comm.getP9t27()));
		
		res.setP3T27(comm.getP3t27());
		res.setP4T27(comm.getP4t27());
		res.setP5T27(comm.getP5t27());
		res.setP6T27(comm.getP6t27());
		
		res.setP8T27((String) session.getAttribute("un"));
		
		if (!CheckNull.isEmpty(comm.getP10t27())) res.setP10T27(comm.getP10t27());
		return res;
	}
	
	public void onSelect$p9t27()
	{
		Fund rr = (Fund) p9t27.getObject();
		fund_val = rr.getP13t35();
		rdate = ((Fund) p9t27.getObject()).getP4t35();
		
		setCurrent();
		
	}
	
	// ///////////////////Current/////////////////////
	private void setCurrent()
	{
		if (current != null)
		{
			kur.setVisible(true);
			
			if (val1.equalsIgnoreCase(fund_val))
			{
				kur.setVisible(false);
			}
			
			coursecurrencies = ContractService.getCourseCurr_18t1_19t1_withOther_sysdate(idn, idc, "'" + fund_val + "'", alias);
			p10t271.setModel((new ListModelList(coursecurrencies)));
			p10t273.setModel((new ListModelList(coursecurrencies)));
			
		}
	}
	
	public void onChange$p9t27()
	{
		Fund rr = (Fund) p9t27.getObject();
		fund_val = rr.getP13t35();
		rdate = ((Fund) p9t27.getObject()).getP4t35();
		
		coursecurrencies = ContractService.getCourseCurr_18t1_19t1_withOther_sysdate(idn, idc, "'" + fund_val + "'", alias);
		p10t271.setModel((new ListModelList(coursecurrencies)));
		p10t273.setModel((new ListModelList(coursecurrencies)));
		
		setCourse1(false);
		if (val1.equalsIgnoreCase(afund_val))
		{
			kur.setVisible(false);
			if (val1.equals("840"))
			{
				row_p4t27.setVisible(true);
				row_p5t27.setVisible(false);
			}
			else
			{
				row_p4t27.setVisible(false);
				row_p5t27.setVisible(true);
			}
		}
		else
		{
			kur.setVisible(true);
			row_p4t27.setVisible(true);
			row_p5t27.setVisible(true);
		}
	}
	
	public void onInitRenderLater$p10t271()
	{
		setCourse(false);
	}
	
	public void onInitRenderLater$p10t273()
	{
		setCourse(false);
	}
	
	private void setCourse(Boolean getNewCourse)
	{
		if (getNewCourse)
		{
			coursecurrencies = ContractService.getCourseCurr_18t1_19t1_withOther_sysdate(idn, idc, "'" + fund_val + "'", alias);
			p10t271.setModel((new ListModelList(coursecurrencies)));
			p10t273.setModel((new ListModelList(coursecurrencies)));
			
		}
		if (coursecurrencies.size() > 0)
		{
			RefCurrencyData curr1 = CommissionService.getRefCurrencyData(fund_val, coursecurrencies);
			RefCurrencyData curr2 = CommissionService.getRefCurrencyData(val1, coursecurrencies);
			RefCurrencyData curr3 = CommissionService.getRefCurrencyData(val2, coursecurrencies);
			if (curr1 != null && curr2 != null && curr3 != null)
			{
				if (curr1.getCourse() > curr2.getCourse())
				{
					p10t271.setSelecteditem(curr1.getKod());
					p10t273.setSelecteditem(curr2.getKod());
				}
				else
				{
					p10t271.setSelecteditem(curr2.getKod());
					p10t273.setSelecteditem(curr1.getKod());
				}
				System.out.println("curr1.getCourse()=" + curr1.getCourse() + "curr2.getCourse()=" + curr2.getCourse());
				
				countCourse(true);
			}
		}
	}
	
	private void countCourse(Boolean setCourse)
	{
		try
		{
			Fund rr = (Fund) p9t27.getObject();
			fund_val = rr.getP13t35();
			if (!CheckNull.isEmpty(p10t273.getValue()) && !CheckNull.isEmpty(p10t271.getValue()))
			{
				// System.out.println("***"+p15t351.getValue()+" - "+p15t353.getValue());
				if (setCourse)
				{
					p10t27.setValue("" + (p10t271.getCourse() / p10t273.getCourse()));
					current.setP10t27((p10t271.getCourse() / p10t273.getCourse()));
					
				}
				// System.out.println("По курсу ЦБ: "+(p10t271.getCourse()/p10t273.getCourse())+" "+p10t271.getCourse()+" "+p10t273.getCourse());
				cbcourse.setValue("По курсу ЦБ: " + (p10t271.getCourse() / p10t273.getCourse()));
				p10t27.setValue("" + (p10t271.getCourse() / p10t273.getCourse()));
				current.setP10t27((p10t271.getCourse() / p10t273.getCourse()));
				// cbcourse1.setValue("По курсу ЦБ: "+(p7t391.getCourse()/p7t393.getCourse()));
				
				Double p4t27val = null;
				Double p5t27val = null;
				if (fund_val.equalsIgnoreCase(p10t273.getValue()))
				{
					p4t27val = p4t27.doubleValue() * p10t27.doubleValue();
					// System.out.println(p4t27.doubleValue() + "*" +
					// p10t27.doubleValue() +"="+p4t27val);
				}
				else
				{
					p4t27val = p4t27.doubleValue() / p10t27.doubleValue();
					// System.out.println(p4t27.doubleValue() + "/" +
					// p10t27.doubleValue() +"="+p4t27val);
				}
				
				Double db = p4t27val + p5t27val;
				// System.out.println(p4t27val + "+" + p5t27val +"="+db);
				Boolean bool = (db == p3t27.doubleValue());
				checksum.setChecked(bool);
				checksum.setLabel((bool ? "Сумма комиссии полностью соответствует указанному курсу!" : "Сумма комиссии не соответствует указанному курсу!") + "(" + db + ")");
				
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void onChange$ap9t27()
	{
		Fund rr = (Fund) ap9t27.getObject();
		afund_val = rr.getP13t35();
		
		System.out.println("onChange$ap9t27()******** val1->[" + val1 + "]******* afund_val->[" + afund_val + "]");
		
		acoursecurrencies = ContractService.getCourseCurr_18t1_19t1_withOther_sysdate(idn, idc, "'" + afund_val + "'", alias);
		ap10t271.setModel((new ListModelList(acoursecurrencies)));
		ap10t273.setModel((new ListModelList(acoursecurrencies)));
		
		setCourse1(false);
		if (val1.equalsIgnoreCase(afund_val))
		{
			akur.setVisible(false);
			if (val1.equals("840"))
			{
				row_ap4t27.setVisible(true);
				row_ap5t27.setVisible(false);
			}
			else
			{
				row_ap4t27.setVisible(false);
				row_ap5t27.setVisible(true);
			}
		}
		else
		{
			akur.setVisible(true);
			row_ap4t27.setVisible(true);
			row_ap5t27.setVisible(true);
		}
		
	}
	
	public void onChange$ap2t27()
	{
		com.is.tf.payment.Payment pay = (com.is.tf.payment.Payment) ap2t27.getObject();
		afund_val = pay.getP15t44();
		
		acoursecurrencies = ContractService.getCourseCurr_18t1_19t1_withOther_sysdate(idn, idc, "'" + afund_val + "'", alias);
		ap10t271.setModel((new ListModelList(acoursecurrencies)));
		ap10t273.setModel((new ListModelList(acoursecurrencies)));
		
		setCourse1(false);
		if (val1.equalsIgnoreCase(afund_val))
		{
			akur.setVisible(false);
			if (val1.equals("840"))
			{
				row_ap4t27.setVisible(true);
				row_ap5t27.setVisible(false);
			}
			else
			{
				row_ap4t27.setVisible(false);
				row_ap5t27.setVisible(true);
			}
		}
		else
		{
			akur.setVisible(true);
			row_ap4t27.setVisible(true);
			row_ap5t27.setVisible(true);
		}
	}
	
	public void onChange$p2t27()
	{
		com.is.tf.payment.Payment pay = (com.is.tf.payment.Payment) p2t27.getObject();
		afund_val = pay.getP15t44();
		
		acoursecurrencies = ContractService.getCourseCurr_18t1_19t1_withOther_sysdate(idn, idc, "'" + afund_val + "'", alias);
		p10t271.setModel((new ListModelList(acoursecurrencies)));
		p10t273.setModel((new ListModelList(acoursecurrencies)));
		
		setCourse1(false);
		if (val1.equalsIgnoreCase(afund_val))
		{
			kur.setVisible(false);
			if (val1.equals("840"))
			{
				row_p4t27.setVisible(true);
				row_p5t27.setVisible(false);
			}
			else
			{
				row_p4t27.setVisible(false);
				row_p5t27.setVisible(true);
			}
		}
		else
		{
			kur.setVisible(true);
			row_p4t27.setVisible(true);
			row_p5t27.setVisible(true);
		}
	}
	
	public void onInitRenderLater$ap10t271()
	{
		setCourse1(false);
	}
	
	public void onInitRenderLater$ap10t273()
	{
		setCourse1(false);
	}
	
	public void onChange$ap4t27()
	{
		acountCourse(false);
	}
	
	public void onChange$ap5t27()
	{
		acountCourse(false);
	}
	
	public void onChange$ap10t27()
	{
		acountCourse(false);
	}
	
	private void setCourse1(Boolean getNewCourse)
	{
		if (getNewCourse)
		{
			acoursecurrencies = ContractService.getCourseCurr_18t1_19t1_withOther_sysdate(idn, idc, "'" + afund_val + "'", alias);
			ap10t271.setModel((new ListModelList(acoursecurrencies)));
			ap10t273.setModel((new ListModelList(acoursecurrencies)));
			
		}
		if (acoursecurrencies.size() > 0)
		{
			RefCurrencyData curr1 = CommissionService.getRefCurrencyData(afund_val, acoursecurrencies);
			RefCurrencyData curr2 = CommissionService.getRefCurrencyData(val1, acoursecurrencies);
			RefCurrencyData curr3 = CommissionService.getRefCurrencyData(val2, acoursecurrencies);
			if (curr1 != null && curr2 != null && curr3 != null)
			{
				if (curr1.getCourse() > curr2.getCourse())
				{
					ap10t271.setSelecteditem(curr1.getKod());
					ap10t273.setSelecteditem(curr2.getKod());
				}
				else
				{
					ap10t271.setSelecteditem(curr2.getKod());
					ap10t273.setSelecteditem(curr1.getKod());
				}
				System.out.println("curr1.getCourse()=" + curr1.getCourse() + "curr2.getCourse()=" + curr2.getCourse());
				
				acountCourse(true);
			}
		}
	}
	
	public void onChange$ap3t27()
	{
		acountCourse(false);
		
		if (Double.parseDouble(summa1) > ap3t27.doubleValue())
		{
			aotn = ("0");
		}
		else if (Double.parseDouble(summa1) < ap3t27.doubleValue())
		{
			aotn = ("1");
		}
		
	}
	
	public void onChange$p3t27()
	{
		acountCourse(false);
		
		if (Double.parseDouble(summa1) > p3t27.doubleValue())
		{
			otn = ("0");
		}
		else if (Double.parseDouble(summa1) < p3t27.doubleValue())
		{
			otn = ("1");
		}
		
	}
	
	private void acountCourse(Boolean setCourse)
	{
		try
		{
			if (!CheckNull.isEmpty(ap10t273.getValue()) && !CheckNull.isEmpty(ap10t271.getValue()) && !ap10t273.getValue().equals("null") && !ap10t271.getValue().equals("null"))
			{
				System.out.println("ap10t271.getValue()->" + ap10t271.getValue());
				System.out.println("ap10t273.getValue()->" + ap10t273.getValue());
				
				System.out.println("ap10t271.getCourse()->" + ap10t271.getCourse());
				System.out.println("ap10t273.getCourse()->" + ap10t273.getCourse());
				
				Double diff = Double.parseDouble(String.valueOf(ap10t271.getCourse())) / Double.parseDouble(String.valueOf(ap10t273.getCourse()));
				
				System.out.println("diff->" + diff);
				acbcourse.setValue("По курсу ЦБ: " + (diff));
				
				// ap10t27.setValue(""+(diff));
				ap10t272.setValue(String.valueOf(diff));
				// ap10t27.setValue(a);
				
				Double ap4t27val = null;
				Double ap5t27val = null;
				
				if (afund_val.equalsIgnoreCase(ap10t273.getValue()))
				{
					ap4t27val = ap4t27.doubleValue() * ap10t272.doubleValue();
					// System.out.println(p4t27.doubleValue() + "*" +
					// p10t27.doubleValue() +"="+p4t27val);
				}
				else
				{
					ap4t27val = ap4t27.doubleValue() / ap10t272.doubleValue();
					// System.out.println(p4t27.doubleValue() + "/" +
					// p10t27.doubleValue() +"="+p4t27val);
				}
				
				// Double db = ap4t27val + ap5t27val;
				Double db = ap4t27val;
				Boolean bool = (db == ap3t27.doubleValue());
				checksum.setChecked(bool);
				checksum.setLabel((bool ? "Сумма комиссии полностью соответствует указанному курсу!" : "Сумма комиссии не соответствует указанному курсу!") + "(" + db + ")");
				
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	// *********************************** Confirm
	// **************************************
	private Window contractmain = null;
	
	public void onClick$btn_confirm()
	{
		sendConfirm(1, String.valueOf(current.getP6t27()), current);
	}
	
	public void onClick$btn_reject()
	{
		sendConfirm(0, String.valueOf(current.getP6t27()), current);
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
		p2t27.setDisabled(param);
		p3t27.setDisabled(param);
		p4t27.setDisabled(param);
		p5t27.setDisabled(param);
		p9t27.setDisabled(param);
	}
	
}
