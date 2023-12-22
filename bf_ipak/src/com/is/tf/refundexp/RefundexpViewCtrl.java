package com.is.tf.refundexp;

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
import org.zkoss.zul.event.PagingEvent;

import com.is.ISLogger;
import com.is.tf.contract.ContractService;
import com.is.tf.currency.RefCurrencyBox;
import com.is.tf.currency.RefCurrencyData;
import com.is.tf.fund.Fund;
import com.is.tf.movefromex.MoveFromEx;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.RefData;
import com.is.utils.refobj.RefObjCBox;
import com.is.utils.refobj.RefObjData;
import com.is.utils.refobj.XMLSerializer;
import com.sbs.service.BankServiceProxy;
import com.sbs.service.RefundExpResult;

public class RefundexpViewCtrl extends GenericForwardComposer
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
	private Textbox p15t36, p18t36, p19t36, p20t36, p21t36, p22t36, p100t36, id, p0t36, p13t36;
	private Textbox ap15t36, ap18t36, ap19t36, ap20t36, ap21t36, ap22t36, ap100t36, aid, ap0t36, ap13t36;
	private Textbox fp15t36, fp18t36, fp19t36, fp20t36, fp21t36, fp22t36, fp100t36, fid, fp0t36, fp13t36;
	private Datebox p23t36, fp23t36, ap23t36, p2t36, ap2t36, fp2t36;
	private Decimalbox p5t36, p6t36, p7t36, p8t36, p9t36, p10t36, p11t36, p12t36, ap5t36, ap6t36, ap7t36, ap8t36, ap9t36, ap10t36, ap11t36, ap12t36, fp5t36, fp6t36, fp7t36, fp8t36, fp9t36, fp10t36, fp11t36, fp12t36;
	private Label p1t36, ap1t36, fp1t36;
	private Paging refundexpPaging;
	private Row rowP4t36, arowP4t36, frowP4t36, rowP14t36, arowP14t36, frowP14t36, kur, akur;
	private Row rowP7t36, arowP7t36, rowP8t36, arowP8t36, rowP9t36, arowP9t36, rowP10t36, arowP10t36, rowP11t36, arowP11t36, rowP12t36, arowP12t36, rowP13t36, arowP13t36;
	private int _pageSize = 15;
	private int _startPageNumber = 0;
	private int _totalSize = 0;
	private boolean _needsTotalSizeUpdate = true;
	private String alias, idc, idn, subj, otn1, val1, val2, strval1, strval2, refundexpcurrency, arefundexpcurrency, summa1;
	private RefCBox p3t36, ap3t36, fp3t36;
	private RefObjCBox p4t36, ap4t36, fp4t36;
	private RefCurrencyBox p6t361, ap6t361, p6t363, ap6t363;
	private RefObjCBox p14t36, ap14t36, fp14t36;
	private List<RefData> funddest = new ArrayList<RefData>();
	private List<RefData> funddest2 = new ArrayList<RefData>();
	private List<RefCurrencyData> currencies = new ArrayList<RefCurrencyData>();
	private List<RefCurrencyData> acurrencies = new ArrayList<RefCurrencyData>();
	private List<RefObjData> funds = new ArrayList<RefObjData>();
	private List<RefObjData> movefromexs = new ArrayList<RefObjData>();
	private HashMap<String, String> curr_ = null;
	private Fund fund, afund;
	private MoveFromEx movefromex, amovefromex;
	private Label cbcourse, acbcourse;
	private Checkbox checksum, achecksum;
	private Label lp7t36, lp8t36, lp9t36, lp10t36, lp11t36, lp12t36;
	private Label alp7t36, alp8t36, alp9t36, alp10t36, alp11t36, alp12t36;
	private Checkbox chp7t36, chp8t36, chp9t36, chp10t36, chp11t36, chp12t36;
	private Checkbox achp7t36, achp8t36, achp9t36, achp10t36, achp11t36, achp12t36;
	
	PagingListModel model = null;
	ListModelList lmodel = null;
	private AnnotateDataBinder binder;
	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	
	private Refundexp current = new Refundexp();
	private RefundexpFilter filter = new RefundexpFilter();
	
	public RefundexpViewCtrl()
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
		binder.bindBean("filter", this.filter);
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
			ap1t36.setValue(idn);
			fp1t36.setValue(idn);
		}
		parameter = (String[]) param.get("idc");
		if (parameter != null)
		{
			idc = (parameter[0]);
			// System.out.println("Garant  cont_idn "+idn);
			
		}
		parameter = (String[]) param.get("val1");
		if (parameter != null)
		{
			val1 = (parameter[0]);
			// System.out.println(" cont_val1 "+val1);
		}
		parameter = (String[]) param.get("val2");
		if (parameter != null)
		{
			val2 = (parameter[0]);
			// System.out.println(" cont_val2 "+val2);
		}
		parameter = (String[]) param.get("subj");
		if (parameter != null)
		{
			subj = (parameter[0]);
			// System.out.println("Garant  cont_val2 "+val2);
		}
		
		parameter = (String[]) param.get("summa1");
		if (parameter != null) summa1 = (parameter[0]);
		
		alias = (String) session.getAttribute("alias");
		funds = ContractService.getFunds(idn, alias);
		movefromexs = ContractService.getMoveFromExs(idn, alias);
		funddest = ContractService.getFundDest("3,4", alias);
		funddest2 = ContractService.getFundDest("2", alias);
		curr_ = com.is.tf.contract.ContractService.getHCurr(alias);
		strval1 = curr_.get(val1);
		strval2 = curr_.get(val2);
		// System.out.println(val1+strval1+" "+val2+strval2);
		lp7t36.setValue(strval1);
		lp8t36.setValue(strval2);
		lp9t36.setValue(strval1);
		lp10t36.setValue(strval2);
		lp11t36.setValue(strval1);
		lp12t36.setValue(strval2);
		alp7t36.setValue(strval1);
		alp8t36.setValue(strval2);
		alp9t36.setValue(strval1);
		alp10t36.setValue(strval2);
		alp11t36.setValue(strval1);
		alp12t36.setValue(strval2);
		
		p4t36.setModel(new ListModelList(funds));
		ap4t36.setModel(new ListModelList(funds));
		fp4t36.setModel(new ListModelList(funds));
		
		p14t36.setModel(new ListModelList(movefromexs));
		ap14t36.setModel(new ListModelList(movefromexs));
		fp14t36.setModel(new ListModelList(movefromexs));
		
		p3t36.setModel(new ListModelList(funddest));
		ap3t36.setModel(new ListModelList(funddest));
		fp3t36.setModel(new ListModelList(funddest));
		
		dataGrid.setItemRenderer(new ListitemRenderer()
		{
			@SuppressWarnings("unchecked")
			public void render(Listitem row, Object data) throws Exception
			{
				Refundexp pRefundexp = (Refundexp) data;
				row.setValue(pRefundexp);
				row.appendChild(new Listcell(pRefundexp.getId() + ""));
				row.appendChild(new Listcell(pRefundexp.getP1t36()));
				row.appendChild(new Listcell(pRefundexp.getP0t36()));
				row.appendChild(new Listcell(pRefundexp.getP2t36() + ""));
				row.appendChild(new Listcell(pRefundexp.getP3t36()));
				row.appendChild(new Listcell(pRefundexp.getP4t36()));
				row.appendChild(new Listcell(pRefundexp.getP5t36() + ""));
				row.appendChild(new Listcell(pRefundexp.getP6t36() + ""));
				row.appendChild(new Listcell(pRefundexp.getP7t36() + ""));
				row.appendChild(new Listcell(pRefundexp.getP8t36() + ""));
				row.appendChild(new Listcell(pRefundexp.getP9t36() + ""));
				row.appendChild(new Listcell(pRefundexp.getP10t36() + ""));
				row.appendChild(new Listcell(pRefundexp.getP11t36() + ""));
				row.appendChild(new Listcell(pRefundexp.getP12t36() + ""));
				row.appendChild(new Listcell(pRefundexp.getP13t36()));
				row.appendChild(new Listcell(pRefundexp.getP14t36()));
			}
		});
		
		refreshModel(_startPageNumber);
		
	}
	
	public void onPaging$refundexpPaging(ForwardEvent event)
	{
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModel(_startPageNumber);
	}
	
	private void refreshModel(int activePage)
	{
		filter.setP1t36(idn);
		refundexpPaging.setPageSize(_pageSize);
		model = new PagingListModel(activePage, _pageSize, filter, "");
		if (_needsTotalSizeUpdate)
		{
			_totalSize = model.getTotalSize(filter, "");
			// _needsTotalSizeUpdate = false;
		}
		refundexpPaging.setTotalSize(_totalSize);
		dataGrid.setModel((ListModel) model);
		if (model.getSize() > 0)
		{
			dataGrid.setSelectedIndex(0);
			sendSelEvt();
			this.current = (Refundexp) model.getElementAt(0);
			sendSelEvt();
		}
	}
	
	// Omitted...
	public Refundexp getCurrent()
	{
		return current;
	}
	
	public void setCurrent(Refundexp current)
	{
		this.current = current;
	}
	
	// Omitted...
	public RefundexpFilter getFilter()
	{
		return filter;
	}
	
	public void setFilter(RefundexpFilter filter)
	{
		this.filter = filter;
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
		setCurrent();
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
	
	public void onClick$btn_refresh() throws Exception
	{
		refresh(idn);
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
		arowP4t36.setVisible(true);
		arowP14t36.setVisible(true);
		ap2t36.setValue(new Date());
		ap3t36.setSelectedIndex(-1);
		ap4t36.setSelectedIndex(-1);
		// ap14t36.setSelectedIndex(-1);
		ap5t36.setValue("0");
		ap6t36.setValue("0");
		arowP7t36.setVisible(false);
		arowP8t36.setVisible(false);
		arowP9t36.setVisible(false);
		arowP10t36.setVisible(false);
		arowP11t36.setVisible(false);
		arowP12t36.setVisible(false);
		ap7t36.setValue("0");
		ap8t36.setValue("0");
		ap9t36.setValue("0");
		ap10t36.setValue("0");
		ap11t36.setValue("0");
		ap12t36.setValue("0");
		// onChange$ap5t36();
		// ap20t36.setValue((String)session.getAttribute("un"));
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
			com.sbs.service.RefundExp refe = new com.sbs.service.RefundExp();
			
			if (addgrd.isVisible())
			{
				if (ap5t36.doubleValue() == 0)
				{
					alert("Сумма возврата не может быть равна 0");
					return;
				}
				
				RefundExpResult ar = ws.saveRefundExp(
						((String) (session.getAttribute("BankINN"))), idn,
						getRefe(new Refundexp(
								ap2t36.getValue(),
								ap3t36.getValue(),
								ap4t36.getValue(),
								ap5t36.doubleValue(),
								ap6t36.doubleValue(),
								ap7t36.doubleValue(),
								ap8t36.doubleValue(),
								ap9t36.doubleValue(),
								ap10t36.doubleValue(),
								ap11t36.doubleValue(),
								ap12t36.doubleValue(),
								ap14t36.getValue(),
								// ap19t36.getValue(),
								// ap20t36.getValue(),
								ap3t36.getValue()
						// ap22t36.getValue()
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
				filter = new RefundexpFilter();
				
				Long.parseLong(fid.getValue());
				filter.setP1t36(fp1t36.getValue());
				filter.setP0t36(fp0t36.getValue());
				filter.setP2t36(fp2t36.getValue());
				filter.setP3t36(fp3t36.getValue());
				filter.setP4t36(fp4t36.getValue());
				filter.setP5t36(fp5t36.doubleValue());
				filter.setP6t36(fp6t36.doubleValue());
				filter.setP7t36(fp7t36.doubleValue());
				filter.setP8t36(fp8t36.doubleValue());
				filter.setP9t36(fp9t36.doubleValue());
				filter.setP10t36(fp10t36.doubleValue());
				filter.setP11t36(fp11t36.doubleValue());
				filter.setP12t36(fp12t36.doubleValue());
				filter.setP13t36(fp13t36.getValue());
				filter.setP14t36(fp14t36.getValue());
				
			}
			else
			{
				
				// Long.parseLong(id.getValue());
				current.setP1t36(p1t36.getValue());
				// current.setP0t36(p0t36.getValue());
				current.setP2t36(p2t36.getValue());
				// current.setP3t36(p3t36.getValue());
				current.setP4t36(p4t36.getValue());
				current.setP5t36(p5t36.doubleValue());
				current.setP6t36(p6t36.doubleValue());
				current.setP7t36(p7t36.doubleValue());
				current.setP8t36(p8t36.doubleValue());
				current.setP9t36(p9t36.doubleValue());
				current.setP10t36(p10t36.doubleValue());
				current.setP11t36(p11t36.doubleValue());
				current.setP12t36(p12t36.doubleValue());
				current.setP13t36(p13t36.getValue());
				current.setP14t36(p14t36.getValue());
				current.setP15t36(p15t36.getValue());
				current.setP18t36(p18t36.getValue());
				current.setP19t36(p19t36.getValue());
				current.setP20t36(p20t36.getValue());
				current.setP21t36(p21t36.getValue());
				current.setP22t36(p22t36.getValue());
				current.setP23t36(p23t36.getValue());
				current.setP100t36(p100t36.getValue());
				// RefundexpService.update(current);
				RefundExpResult ar = ws.saveRefundExp(((String) (session.getAttribute("BankINN"))), idn, getRefe(current));
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
			filter = new RefundexpFilter();
		}
		onClick$btn_back();
		frmgrd.setVisible(true);
		addgrd.setVisible(false);
		fgrd.setVisible(false);
		CheckNull.clearForm(addgrd);
		CheckNull.clearForm(fgrd);
		refreshModel(_startPageNumber);
	}
	
	private com.sbs.service.RefundExp getRefe(Refundexp acr) throws Exception
	{
		java.util.Calendar cal = java.util.Calendar.getInstance();
		com.sbs.service.RefundExp res = new com.sbs.service.RefundExp();
		
		res.setP0T36(0);
		
		cal.setTime(df.parse(df.format(acr.getP2t36())));
		res.setP2T36(cal);
		
		// res.setP3T36(Short.parseShort(acr.getP3t36())); Надо разобратся
		// res.setP4T36((acr.getP4t36()));
		
		System.out.println("acr.getP5t36()->" + acr.getP5t36());
		res.setP5T36(acr.getP5t36());
		
		res.setP6T36(acr.getP6t36());
		res.setP7T36(acr.getP7t36());
		res.setP8T36(acr.getP8t36());
		res.setP9T36(acr.getP9t36());
		res.setP10T36(acr.getP10t36());
		// res.setP11T36(acr.getP11t36());
		// res.setP12T36(acr.getP12t36());
		// res.setP13T36(Integer.parseInt(acr.getP13t36()));
		if (!CheckNull.isEmpty(acr.getP14t36())) res.setP14T36(Integer.parseInt(acr.getP14t36()));
		
		res.setP15T36((String) session.getAttribute("un"));
		
		res.setP18T36(Short.parseShort(otn1));
		// if (!CheckNull.isEmpty(acr.getP19t36())) res.setP
		// (Short.parseShort(acr.getP19t36()));
		res.setP20T36((String) session.getAttribute("un"));
		if (!CheckNull.isEmpty(acr.getP21t36())) res.setP21T36(Short.parseShort(acr.getP3t36()));
		if (!CheckNull.isEmpty(acr.getP22t36())) res.setP22T36(acr.getP22t36());
		
		return res;
	}
	
	private void setCurrent()
	{
		if (current != null)
		{
			if (!CheckNull.isEmpty(current.getP4t36()))
			{
				rowP4t36.setVisible(true);
				rowP14t36.setVisible(false);
				p4t36.setSelecteditem(current.getP4t36());
				onSelect$p4t36();
			}
			else if (!CheckNull.isEmpty(current.getP14t36()))
			{
				rowP4t36.setVisible(false);
				rowP14t36.setVisible(true);
				p14t36.setSelecteditem(current.getP14t36());
				onSelect$p14t36();
			}
			else
			{
				rowP4t36.setVisible(true);
				rowP14t36.setVisible(true);
			}
			
		}
	}
	
	public void onSelect$p4t36()
	{
		fund = ((Fund) p4t36.getObject());
		movefromex = null;
		if (fund != null)
		{
			// System.out.println(fund);
			refundexpcurrency = fund.getP13t35();
			if (fund.getP5t35().equalsIgnoreCase("2"))
			{
				p3t36.setModel(new ListModelList(funddest2));
				p3t36.setDisabled(true);
				if (funddest2.size() > 0)
				{
					p3t36.setSelectedIndex(0);
					current.setP3t36(funddest2.get(0).getData());
				}
			}
			else
			{
				p3t36.setModel(new ListModelList(funddest));
				p3t36.setDisabled(false);
				if (funddest.size() > 0)
				{
					p3t36.setSelectedIndex(0);
					current.setP3t36(funddest.get(0).getData());
				}
			}
			if (!CheckNull.isEmpty(fund.getP15t35()))
			{
				// System.out.println("fund.getP15t35() = "+fund.getP15t35());
				kur.setVisible(true);
				currencies = ContractService.getCourseCurr_18t1_19t1_withOther(idn, df.format(fund.getP4t35()), "'" + refundexpcurrency + "'", alias);
				System.out.println("currencies.size = " + currencies.size());
				p6t361.setModel(new ListModelList(currencies));
				p6t363.setModel(new ListModelList(currencies));
				p6t36.setValue("" + fund.getP15t35());
				current.setP6t36(fund.getP15t35());
			}
			
			rowP7t36.setVisible(false);
			rowP8t36.setVisible(false);
			rowP9t36.setVisible(false);
			rowP10t36.setVisible(false);
			rowP11t36.setVisible(false);
			rowP12t36.setVisible(false);
			if (!CheckNull.isEmpty(fund.getP16t35()) && fund.getP16t35() > Double.parseDouble("0.000"))
			{
				rowP7t36.setVisible(true);
				chp7t36.setLabel("" + fund.getP16t35());
				chp7t36.setChecked(current.getP7t36() <= fund.getP16t35());
			}
			else
			{
				chp7t36.setLabel("");
				chp7t36.setChecked(true);
			}
			if (!CheckNull.isEmpty(fund.getP17t35()) && fund.getP17t35() > Double.parseDouble("0.000"))
			{
				rowP8t36.setVisible(true);
				chp8t36.setLabel("" + fund.getP17t35());
				chp8t36.setChecked(current.getP8t36() <= fund.getP17t35());
			}
			else
			{
				chp8t36.setLabel("");
				chp8t36.setChecked(true);
			}
			if (!CheckNull.isEmpty(fund.getP18t35()) && fund.getP18t35() > Double.parseDouble("0.000"))
			{
				rowP9t36.setVisible(true);
				chp9t36.setLabel("" + fund.getP18t35());
				chp9t36.setChecked(current.getP9t36() <= fund.getP18t35());
			}
			else
			{
				chp9t36.setLabel("");
				chp9t36.setChecked(true);
			}
			if (!CheckNull.isEmpty(fund.getP19t35()) && fund.getP19t35() > Double.parseDouble("0.000"))
			{
				rowP10t36.setVisible(true);
				chp10t36.setLabel("" + fund.getP19t35());
				chp10t36.setChecked(current.getP10t36() <= fund.getP19t35());
			}
			else
			{
				chp10t36.setLabel("");
				chp10t36.setChecked(true);
			}
			/*
			 * if (!CheckNull.isEmpty(fund.getP20t35()) && fund.getP20t35() >
			 * Double.parseDouble("0.000")) { rowP11t36.setVisible(true);
			 * chp11t36.setLabel(""+fund.getP20t35());
			 * chp11t36.setChecked(current.getP11t36() <= fund.getP20t35()); }
			 * else { chp11t36.setLabel(""); chp11t36.setChecked(true); }
			 */
			if (!CheckNull.isEmpty(fund.getP21t35()) && fund.getP21t35() > Double.parseDouble("0.000"))
			{
				rowP12t36.setVisible(true);
				chp12t36.setLabel("" + fund.getP21t35());
				chp12t36.setChecked(current.getP12t36() <= fund.getP21t35());
			}
			else
			{
				chp12t36.setLabel("");
				chp12t36.setChecked(true);
			}
			rowP14t36.setVisible(false);
		}
		else
		{
			rowP14t36.setVisible(true);
		}
	}
	
	public void onSelect$p14t36()
	{
		movefromex = ((MoveFromEx) p14t36.getObject());
		fund = null;
		if (movefromex != null)
		{
			refundexpcurrency = movefromex.getP9t52();
			if (movefromex.getP5t52().equalsIgnoreCase("2"))
			{
				p3t36.setModel(new ListModelList(funddest2));
				p3t36.setDisabled(true);
				if (funddest2.size() > 0)
				{
					p3t36.setSelectedIndex(0);
					current.setP3t36(funddest2.get(0).getData());
				}
			}
			else
			{
				p3t36.setModel(new ListModelList(funddest));
				p3t36.setDisabled(false);
				if (funddest.size() > 0)
				{
					p3t36.setSelectedIndex(0);
					current.setP3t36(funddest.get(0).getData());
				}
			}
			if (!CheckNull.isEmpty(movefromex.getP12t52()))
			{
				kur.setVisible(true);
				currencies = ContractService.getCourseCurr_18t1_19t1_withOther(idn, df.format(movefromex.getP2t52()), "'" + refundexpcurrency + "'", alias);
				p6t361.setModel(new ListModelList(currencies));
				p6t363.setModel(new ListModelList(currencies));
				p6t36.setValue("" + movefromex.getP12t52());
				current.setP6t36(movefromex.getP12t52());
			}
			rowP7t36.setVisible(false);
			rowP8t36.setVisible(false);
			rowP9t36.setVisible(false);
			rowP10t36.setVisible(false);
			rowP11t36.setVisible(false);
			rowP12t36.setVisible(false);
			if (!CheckNull.isEmpty(movefromex.getP13t52()) && movefromex.getP13t52() > 0)
			{
				rowP7t36.setVisible(true);
				chp7t36.setLabel("" + movefromex.getP13t52());
				chp7t36.setChecked(current.getP7t36() <= movefromex.getP13t52());
			}
			else
			{
				chp7t36.setLabel("");
				chp7t36.setChecked(true);
			}
			if (!CheckNull.isEmpty(movefromex.getP14t52()) && movefromex.getP14t52() > 0)
			{
				rowP8t36.setVisible(true);
				chp8t36.setLabel("" + movefromex.getP14t52());
				chp8t36.setChecked(current.getP8t36() <= movefromex.getP14t52());
			}
			else
			{
				chp8t36.setLabel("");
				chp8t36.setChecked(true);
			}
			if (!CheckNull.isEmpty(movefromex.getP15t52()) && movefromex.getP15t52() > 0)
			{
				rowP9t36.setVisible(true);
				chp9t36.setLabel("" + movefromex.getP15t52());
				chp9t36.setChecked(current.getP9t36() <= movefromex.getP15t52());
			}
			else
			{
				chp9t36.setLabel("");
				chp9t36.setChecked(true);
			}
			if (!CheckNull.isEmpty(movefromex.getP16t52()) && movefromex.getP16t52() > 0)
			{
				rowP10t36.setVisible(true);
				chp10t36.setLabel("" + movefromex.getP16t52());
				chp10t36.setChecked(current.getP10t36() <= movefromex.getP16t52());
			}
			else
			{
				chp10t36.setLabel("");
				chp10t36.setChecked(true);
			}
			chp11t36.setLabel("");
			chp11t36.setChecked(true);
			chp12t36.setLabel("");
			chp12t36.setChecked(true);
			/*
			 * if (!CheckNull.isEmpty(movefromex.getP20t35()) &&
			 * movefromex.getP20t35() > 0) { rowP11t36.setVisible(true); } if
			 * (!CheckNull.isEmpty(movefromex.getP21t35()) &&
			 * movefromex.getP21t35() > 0) { rowP12t36.setVisible(true); }
			 */
			rowP4t36.setVisible(false);
		}
		else
		{
			rowP4t36.setVisible(true);
		}
	}
	
	public void onSelect$ap4t36()
	{
		afund = ((Fund) ap4t36.getObject());
		amovefromex = null;
		if (afund != null)
		{
			// System.out.println(fund);
			arefundexpcurrency = afund.getP13t35();
			if (afund.getP5t35().equalsIgnoreCase("2"))
			{
				ap3t36.setModel(new ListModelList(funddest2));
				ap3t36.setDisabled(true);
				if (funddest2.size() > 0)
				{
					ap3t36.setSelectedIndex(0);
					// current.setP3t36(funddest2.get(0).getData());
				}
			}
			else
			{
				ap3t36.setModel(new ListModelList(funddest));
				ap3t36.setDisabled(false);
				if (funddest.size() > 0)
				{
					ap3t36.setSelectedIndex(0);
					// current.setP3t36(funddest.get(0).getData());
				}
			}
			if (!CheckNull.isEmpty(afund.getP15t35()))
			{
				// System.out.println("fund.getP15t35() = "+fund.getP15t35());
				akur.setVisible(true);
				acurrencies = ContractService.getCourseCurr_18t1_19t1_withOther(idn, df.format(afund.getP4t35()), "'" + arefundexpcurrency + "'", alias);
				// System.out.println("currencies.size = "+acurrencies.size());
				ap6t361.setModel(new ListModelList(acurrencies));
				ap6t363.setModel(new ListModelList(acurrencies));
				ap6t36.setValue("" + afund.getP15t35());
			}
			
			arowP7t36.setVisible(false);
			arowP8t36.setVisible(false);
			arowP9t36.setVisible(false);
			arowP10t36.setVisible(false);
			arowP11t36.setVisible(false);
			arowP12t36.setVisible(false);
			if (!CheckNull.isEmpty(afund.getP16t35()) && afund.getP16t35() > Double.parseDouble("0.000"))
			{
				arowP7t36.setVisible(true);
				achp7t36.setLabel("" + afund.getP16t35());
				achp7t36.setChecked(ap7t36.doubleValue() <= afund.getP16t35());
			}
			else
			{
				achp7t36.setLabel("");
				achp7t36.setChecked(true);
			}
			if (!CheckNull.isEmpty(afund.getP17t35()) && afund.getP17t35() > Double.parseDouble("0.000"))
			{
				arowP8t36.setVisible(true);
				achp8t36.setLabel("" + afund.getP17t35());
				achp8t36.setChecked(ap8t36.doubleValue() <= afund.getP17t35());
			}
			else
			{
				achp8t36.setLabel("");
				achp8t36.setChecked(true);
			}
			if (!CheckNull.isEmpty(afund.getP18t35()) && afund.getP18t35() > Double.parseDouble("0.000"))
			{
				arowP9t36.setVisible(true);
				achp9t36.setLabel("" + afund.getP18t35());
				achp9t36.setChecked(ap9t36.doubleValue() <= afund.getP18t35());
			}
			else
			{
				achp9t36.setLabel("");
				achp9t36.setChecked(true);
			}
			if (!CheckNull.isEmpty(afund.getP19t35()) && afund.getP19t35() > Double.parseDouble("0.000"))
			{
				arowP10t36.setVisible(true);
				achp10t36.setLabel("" + afund.getP19t35());
				achp10t36.setChecked(ap10t36.doubleValue() <= afund.getP19t35());
			}
			else
			{
				achp10t36.setLabel("");
				achp10t36.setChecked(true);
			}
			/*
			 * if (!CheckNull.isEmpty(afund.getP20t35()) && afund.getP20t35() >
			 * Double.parseDouble("0.000")) { arowP11t36.setVisible(true);
			 * achp11t36.setLabel(""+afund.getP20t35());
			 * achp11t36.setChecked(ap11t36.doubleValue() <= afund.getP20t35());
			 * } else { achp11t36.setLabel(""); achp11t36.setChecked(true); }
			 */
			if (!CheckNull.isEmpty(afund.getP21t35()) && afund.getP21t35() > Double.parseDouble("0.000"))
			{
				arowP12t36.setVisible(true);
				achp12t36.setLabel("" + afund.getP21t35());
				achp12t36.setChecked(ap12t36.doubleValue() <= afund.getP21t35());
			}
			else
			{
				achp12t36.setLabel("");
				achp12t36.setChecked(true);
			}
			arowP14t36.setVisible(false);
		}
		else
		{
			arowP14t36.setVisible(true);
		}
		if (ap4t36.getValue() != null) ;
		{
			arowP14t36.setVisible(false);
		}
		acountCourse(true);
	}
	
	public void onSelect$ap14t36()
	{
		System.out.println("p14t36.getValue() Select = " + ap14t36.getValue());
		amovefromex = ((MoveFromEx) ap14t36.getObject());
		afund = null;
		if (amovefromex != null)
		{
			arefundexpcurrency = amovefromex.getP9t52();
			if (amovefromex.getP5t52().equalsIgnoreCase("2"))
			{
				ap3t36.setModel(new ListModelList(funddest2));
				ap3t36.setDisabled(true);
				if (funddest2.size() > 0)
				{
					ap3t36.setSelectedIndex(0);
					// current.setP3t36(funddest2.get(0).getData());
				}
			}
			else
			{
				ap3t36.setModel(new ListModelList(funddest));
				ap3t36.setDisabled(false);
				if (funddest.size() > 0)
				{
					ap3t36.setSelectedIndex(0);
					// current.setP3t36(funddest.get(0).getData());
				}
			}
			if (!CheckNull.isEmpty(amovefromex.getP12t52()))
			{
				akur.setVisible(true);
				acurrencies = ContractService.getCourseCurr_18t1_19t1_withOther(idn, df.format(amovefromex.getP2t52()), "'" + arefundexpcurrency + "'", alias);
				ap6t361.setModel(new ListModelList(acurrencies));
				ap6t363.setModel(new ListModelList(acurrencies));
				ap6t36.setValue("" + amovefromex.getP12t52());
			}
			arowP7t36.setVisible(false);
			arowP8t36.setVisible(false);
			arowP9t36.setVisible(false);
			arowP10t36.setVisible(false);
			arowP11t36.setVisible(false);
			arowP12t36.setVisible(false);
			if (!CheckNull.isEmpty(amovefromex.getP13t52()) && amovefromex.getP13t52() > 0)
			{
				arowP7t36.setVisible(true);
				achp7t36.setLabel("" + amovefromex.getP13t52());
				achp7t36.setChecked(ap7t36.doubleValue() <= amovefromex.getP13t52());
			}
			else
			{
				achp7t36.setLabel("");
				achp7t36.setChecked(true);
			}
			if (!CheckNull.isEmpty(amovefromex.getP14t52()) && amovefromex.getP14t52() > 0)
			{
				arowP8t36.setVisible(true);
				achp8t36.setLabel("" + amovefromex.getP14t52());
				achp8t36.setChecked(ap8t36.doubleValue() <= amovefromex.getP14t52());
			}
			else
			{
				achp8t36.setLabel("");
				achp8t36.setChecked(true);
			}
			if (!CheckNull.isEmpty(amovefromex.getP15t52()) && amovefromex.getP15t52() > 0)
			{
				arowP9t36.setVisible(true);
				achp9t36.setLabel("" + amovefromex.getP15t52());
				achp9t36.setChecked(ap9t36.doubleValue() <= amovefromex.getP15t52());
			}
			else
			{
				achp9t36.setLabel("");
				achp9t36.setChecked(true);
			}
			if (!CheckNull.isEmpty(amovefromex.getP16t52()) && amovefromex.getP16t52() > 0)
			{
				arowP10t36.setVisible(true);
				achp10t36.setLabel("" + amovefromex.getP16t52());
				achp10t36.setChecked(ap10t36.doubleValue() <= amovefromex.getP16t52());
			}
			else
			{
				achp10t36.setLabel("");
				achp10t36.setChecked(true);
			}
			achp11t36.setLabel("");
			achp11t36.setChecked(true);
			achp12t36.setLabel("");
			achp12t36.setChecked(true);
			/*
			 * if (!CheckNull.isEmpty(amovefromex.getP20t35()) &&
			 * amovefromex.getP20t35() > 0) { rowP11t36.setVisible(true); } if
			 * (!CheckNull.isEmpty(amovefromex.getP21t35()) &&
			 * amovefromex.getP21t35() > 0) { rowP12t36.setVisible(true); }
			 */
			arowP4t36.setVisible(false);
		}
		else
		{
			arowP4t36.setVisible(true);
		}
		acountCourse(true);
	}
	
	public void onSelect$p3t36()
	{
		if (p3t36.getValue().equalsIgnoreCase("4"))
		{
			rowP13t36.setVisible(false);
		}
		else
		{
			rowP13t36.setVisible(true);
		}
	}
	
	public void onInitRenderAfter$p3t36()
	{
		if (!CheckNull.isEmpty(current.getP3t36()))
		{
			p3t36.setSelecteditem(current.getP3t36());
			if (current.getP3t36().equalsIgnoreCase("4"))
			{
				rowP13t36.setVisible(false);
			}
			else
			{
				rowP13t36.setVisible(true);
			}
		}
	}
	
	public void onSelect$ap3t36()
	{
		/*
		 * if (ap3t36.getValue().equalsIgnoreCase("4")) {
		 * arowP13t36.setVisible(false); } else { arowP13t36.setVisible(true); }
		 */
	}
	
	private void countCourse(Boolean setp6t36)
	{
		try
		{
			if (!CheckNull.isEmpty(p6t363.getValue()) && !CheckNull.isEmpty(p6t361.getValue()))
			{
				// System.out.println("***"+p6t361.getValue()+" - "+p6t363.getValue());
				if (setp6t36)
				{
					p6t36.setValue("" + (p6t361.getCourse() / p6t363.getCourse()));
					current.setP6t36((p6t361.getCourse() / p6t363.getCourse()));
				}
				cbcourse.setValue("По курсу ЦБ: " + (p6t361.getCourse() / p6t363.getCourse()));
				Boolean bool = false;
				Double db = null;
				if (refundexpcurrency.equalsIgnoreCase(p6t361.getValue()))
				{
					if (val1.equalsIgnoreCase(p6t361.getValue()))
					{
						db = (p7t36.doubleValue() + (p8t36.doubleValue() / p6t36.doubleValue()) +
								p9t36.doubleValue() + (p10t36.doubleValue() / p6t36.doubleValue()) +
								p11t36.doubleValue() + (p12t36.doubleValue() / p6t36.doubleValue()));
						bool = (p5t36.doubleValue() == db);
						checksum.setChecked(bool);
						checksum.setLabel((bool ? "Сумма гарантии полность соответствует указанному курсу!" : "Сумма гарантии не соответствует указанному курсу!") + "(" + db + ")");
					}
					else if (val2.equalsIgnoreCase(p6t361.getValue()))
					{
						db = (p8t36.doubleValue() + (p7t36.doubleValue() / p6t36.doubleValue()) +
								p10t36.doubleValue() + (p9t36.doubleValue() / p6t36.doubleValue()) +
								p12t36.doubleValue() + (p11t36.doubleValue() / p6t36.doubleValue()));
						bool = (p5t36.doubleValue() == db);
						checksum.setChecked(bool);
						checksum.setLabel((bool ? "Сумма гарантии полность соответствует указанному курсу!" : "Сумма гарантии не соответствует указанному курсу!") + "(" + db + ")");
					}
					else
					{
						checksum.setChecked(false);
						checksum.setLabel(("Сумма гарантии не соответствует указанному курсу!"));
					}
				}
				else if (refundexpcurrency.equalsIgnoreCase(p6t363.getValue()))
				{
					if (val1.equalsIgnoreCase(p6t363.getValue()))
					{
						db = (p7t36.doubleValue() + (p8t36.doubleValue() * p6t36.doubleValue()) +
								p9t36.doubleValue() + (p10t36.doubleValue() * p6t36.doubleValue()) +
								p11t36.doubleValue() + (p12t36.doubleValue() * p6t36.doubleValue()));
						bool = (p5t36.doubleValue() == db);
						checksum.setChecked(bool);
						checksum.setLabel((bool ? "Сумма гарантии полность соответствует указанному курсу!" : "Сумма гарантии не соответствует указанному курсу!") + "(" + db + ")");
					}
					else if (val2.equalsIgnoreCase(p6t363.getValue()))
					{
						db = (p8t36.doubleValue() + (p7t36.doubleValue() * p6t36.doubleValue()) +
								p10t36.doubleValue() + (p9t36.doubleValue() * p6t36.doubleValue()) +
								p12t36.doubleValue() + (p11t36.doubleValue() * p6t36.doubleValue()));
						bool = (p5t36.doubleValue() == db);
						checksum.setChecked(bool);
						checksum.setLabel((bool ? "Сумма гарантии полность соответствует указанному курсу!" : "Сумма гарантии не соответствует указанному курсу!") + "(" + db + ")");
					}
					else
					{
						checksum.setChecked(false);
						checksum.setLabel(("Сумма гарантии не соответствует указанному курсу!"));
					}
				}
				else
				{
					checksum.setChecked(false);
				}
				
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void acountCourse(Boolean setap6t36)
	{
		try
		{
			if (!CheckNull.isEmpty(ap6t363.getValue()) && !CheckNull.isEmpty(ap6t361.getValue()))
			{
				// System.out.println("***"+ap6t361.getValue()+" - "+ap6t363.getValue());
				if (setap6t36)
				{
					ap6t36.setValue("" + (ap6t361.getCourse() / ap6t363.getCourse()));
					// current.setP14t35((ap6t361.getCourse()/ap6t363.getCourse()));
				}
				acbcourse.setValue("По курсу ЦБ: " + (ap6t361.getCourse() / ap6t363.getCourse()));
				Boolean bool = false;
				Double db = null;
				if (arefundexpcurrency.equalsIgnoreCase(ap6t361.getValue()))
				{
					if (val1.equalsIgnoreCase(ap6t361.getValue()))
					{
						db = (ap7t36.doubleValue() + (ap8t36.doubleValue() / ap6t36.doubleValue()) +
								ap9t36.doubleValue() + (ap10t36.doubleValue() / ap6t36.doubleValue()) +
								ap11t36.doubleValue() + (ap12t36.doubleValue() / ap6t36.doubleValue()));
						bool = (ap5t36.doubleValue() == db);
						achecksum.setChecked(bool);
						achecksum.setLabel((bool ? "Сумма гарантии полность соответствует указанному курсу!" : "Сумма гарантии не соответствует указанному курсу!") + "(" + db + ")");
					}
					else if (val2.equalsIgnoreCase(ap6t361.getValue()))
					{
						db = (ap8t36.doubleValue() + (ap7t36.doubleValue() / ap6t36.doubleValue()) +
								ap10t36.doubleValue() + (ap9t36.doubleValue() / ap6t36.doubleValue()) +
								ap12t36.doubleValue() + (ap11t36.doubleValue() / ap6t36.doubleValue()));
						bool = (ap5t36.doubleValue() == db);
						achecksum.setChecked(bool);
						achecksum.setLabel((bool ? "Сумма гарантии полность соответствует указанному курсу!" : "Сумма гарантии не соответствует указанному курсу!") + "(" + db + ")");
					}
					else
					{
						achecksum.setChecked(false);
						achecksum.setLabel(("Сумма гарантии не соответствует указанному курсу!"));
					}
				}
				else if (arefundexpcurrency.equalsIgnoreCase(ap6t363.getValue()))
				{
					if (val1.equalsIgnoreCase(ap6t363.getValue()))
					{
						db = (ap7t36.doubleValue() + (ap8t36.doubleValue() * ap6t36.doubleValue()) +
								ap9t36.doubleValue() + (ap10t36.doubleValue() * ap6t36.doubleValue()) +
								ap11t36.doubleValue() + (ap12t36.doubleValue() * ap6t36.doubleValue()));
						bool = (ap5t36.doubleValue() == db);
						achecksum.setChecked(bool);
						achecksum.setLabel((bool ? "Сумма гарантии полность соответствует указанному курсу!" : "Сумма гарантии не соответствует указанному курсу!") + "(" + db + ")");
					}
					else if (val2.equalsIgnoreCase(ap6t363.getValue()))
					{
						db = (ap8t36.doubleValue() + (ap7t36.doubleValue() * ap6t36.doubleValue()) +
								ap10t36.doubleValue() + (ap9t36.doubleValue() * ap6t36.doubleValue()) +
								ap12t36.doubleValue() + (ap11t36.doubleValue() * ap6t36.doubleValue()));
						bool = (ap5t36.doubleValue() == db);
						achecksum.setChecked(bool);
						achecksum.setLabel((bool ? "Сумма гарантии полность соответствует указанному курсу!" : "Сумма гарантии не соответствует указанному курсу!") + "(" + db + ")");
					}
					else
					{
						achecksum.setChecked(false);
						achecksum.setLabel(("Сумма гарантии не соответствует указанному курсу!"));
					}
				}
				else
				{
					achecksum.setChecked(false);
				}
				
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void onChange$p5t36()
	{
		countCourse(false);
	}
	
	public void onChange$p6t36()
	{
		countCourse(false);
	}
	
	public void onChange$p6t363()
	{
		countCourse(false);
	}
	
	public void onInitRenderLater$p6t363()
	{
		// System.out.println("currencies.size() = "+currencies.size());
		if (currencies.size() > 0)
		{
			p6t363.setSelectedIndex(currencies.size() - 1);
			countCourse(false);
		}
	}
	
	public void onChange$p6t361()
	{
		countCourse(false);
	}
	
	public void onChange$p7t36()
	{
		if (fund != null) chp7t36.setChecked(p7t36.doubleValue() <= fund.getP16t35());
		if (movefromex != null) chp7t36.setChecked(p7t36.doubleValue() <= movefromex.getP13t52());
		countCourse(false);
	}
	
	public void onChange$p8t36()
	{
		if (fund != null) chp8t36.setChecked(p8t36.doubleValue() <= fund.getP17t35());
		if (movefromex != null) chp8t36.setChecked(p8t36.doubleValue() <= movefromex.getP14t52());
		countCourse(false);
	}
	
	public void onChange$p9t36()
	{
		if (fund != null) chp9t36.setChecked(p9t36.doubleValue() <= fund.getP18t35());
		if (movefromex != null) chp9t36.setChecked(p9t36.doubleValue() <= movefromex.getP15t52());
		countCourse(false);
	}
	
	public void onChange$p10t36()
	{
		if (fund != null) chp10t36.setChecked(p10t36.doubleValue() <= fund.getP19t35());
		if (movefromex != null) chp10t36.setChecked(p10t36.doubleValue() <= movefromex.getP16t52());
		countCourse(false);
	}
	
	/*
	 * public void onChange$p11t36() { if (fund != null)
	 * chp11t36.setChecked(p11t36.doubleValue() <= fund.getP20t35()); if
	 * (movefromex != null) chp11t36.setChecked(true);//p11t36.doubleValue() <=
	 * movefromex.getP17t52()); countCourse(false); }
	 */
	public void onChange$p12t36()
	{
		if (fund != null) chp12t36.setChecked(p12t36.doubleValue() <= fund.getP21t35());
		if (movefromex != null) chp12t36.setChecked(true);// p11t36.doubleValue()
															// <=
															// movefromex.getP17t52());
		countCourse(false);
	}
	
	public void onChange$ap5t36()
	{
		if (!CheckNull.isEmpty(ap5t36.doubleValue()))
		{
			{
				alert("Summa1=" + summa1 + " ap5t36.doubleValue()=" + ap5t36.doubleValue());
				ap7t36.setValue(String.valueOf(ap5t36.doubleValue()));
				
				acountCourse(false);
				if (Double.parseDouble(summa1) > ap5t36.doubleValue())
				{
					otn1 = ("0");
				}
				else if (Double.parseDouble(summa1) < ap5t36.doubleValue())
				{
					otn1 = ("1");
				}
			}
		}
	}
	
	public void onChange$ap6t36()
	{
		acountCourse(false);
	}
	
	public void onChange$ap6t363()
	{
		acountCourse(false);
	}
	
	public void onInitRenderLater$ap6t363()
	{
		// System.out.println("currencies.size() = "+currencies.size());
		if (acurrencies.size() > 0)
		{
			ap6t363.setSelectedIndex(acurrencies.size() - 1);
			acountCourse(false);
		}
	}
	
	public void onChange$ap6t361()
	{
		acountCourse(false);
	}
	
	public void onChange$ap7t36()
	{
		if (afund != null) achp7t36.setChecked(ap7t36.doubleValue() <= afund.getP16t35());
		if (amovefromex != null) achp7t36.setChecked(ap7t36.doubleValue() <= amovefromex.getP13t52());
		acountCourse(false);
	}
	
	public void onChange$ap8t36()
	{
		if (afund != null) achp8t36.setChecked(ap8t36.doubleValue() <= afund.getP17t35());
		if (amovefromex != null) achp8t36.setChecked(ap8t36.doubleValue() <= amovefromex.getP14t52());
		acountCourse(false);
	}
	
	public void onChange$ap9t36()
	{
		if (afund != null) achp9t36.setChecked(ap9t36.doubleValue() <= afund.getP18t35());
		if (amovefromex != null) achp9t36.setChecked(ap9t36.doubleValue() <= amovefromex.getP15t52());
		acountCourse(false);
	}
	
	public void onChange$ap10t36()
	{
		if (afund != null) achp10t36.setChecked(ap10t36.doubleValue() <= afund.getP19t35());
		if (amovefromex != null) achp10t36.setChecked(ap10t36.doubleValue() <= amovefromex.getP16t52());
		acountCourse(false);
	}
	
	/*
	 * public void onChange$ap11t36() { if (afund != null)
	 * achp11t36.setChecked(ap11t36.doubleValue() <= afund.getP20t35()); if
	 * (amovefromex != null) achp11t36.setChecked(true);//ap11t36.doubleValue()
	 * <= amovefromex.getP17t52()); acountCourse(false); }
	 */
	public void onChange$ap12t36()
	{
		if (afund != null) achp12t36.setChecked(ap12t36.doubleValue() <= afund.getP21t35());
		if (amovefromex != null) achp12t36.setChecked(true);// ap10t36.doubleValue()
															// <=
															// amovefromex.getP18t52());
		acountCourse(false);
	}
	
	public void refresh(String idn) throws Exception
	{
		long id_contract = Long.parseLong(idc);
		final BankServiceProxy ws = new BankServiceProxy((String) session.getAttribute("YESVO_URL"));
		com.is.tf.refundexp.RefundexpService.remove(new Refundexp(), id_contract);
		com.sbs.service.RefundsExpResult Pay = ws.getRefundsExp(((String) (session.getAttribute("BankINN"))), idn);
		XMLSerializer.write(Pay, "c:/refexp.xml");
		try
		{
			if (Pay.getStatus() == 0)
			{
				for (int i = 0; i < Pay.getRefundsExp().length; i++)
				{
					com.is.tf.refundexp.RefundexpService.create(Pay.getRefundsExp()[i], idn, id_contract);
					
				}
			}
			else
			{
				alert("Ошибка при обновлении:" + Pay.getErrorMsg() + ":  Status=" + Pay.getStatus() + ": GtkId=" + Pay.getGtkId() + ": BankINN=" + ((String) session.getAttribute("BankINN")));
				ISLogger.getLogger().warn("ERROR refresh:" + Pay.getErrorMsg() + ":  Status=" + Pay.getStatus() + ": GtkId=" + Pay.getGtkId());
				
			}
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
}
