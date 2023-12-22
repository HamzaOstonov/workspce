package com.is.tf.tax;

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
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.RefData;
import com.sbs.service.BankServiceProxy;
import com.sbs.service.TaxResult;

public class TaxViewCtrl extends GenericForwardComposer
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
	private Toolbarbutton btn_back, btn_delete;
	private Toolbar tb;
	private Textbox id, p0t39, p10t39, p11t39;
	private Textbox aid, ap0t39, ap10t39, ap11t39;
	private Textbox fid, fp0t39, fp10t39, fp11t39;
	private Datebox p2t39, ap2t39, fp2t39;
	private Decimalbox p5t39, p6t39, p7t39, p8t39, p9t39, ap5t39, ap6t39, ap7t39, ap8t39, ap9t39, fp5t39, fp6t39, fp7t39, fp8t39, fp9t39;
	private RefCBox p3t39, p4t39, ap3t39, ap4t39, fp3t39, fp4t39;
	private RefCurrencyBox p6t391, p6t393, p7t391, p7t393, ap6t391, ap6t393, ap7t391, ap7t393;
	private Label p1t39, ap1t39, fp1t39, lp8t39, lp9t39, cbcourse, acbcourse, cbcourse1, acbcourse1;
	private Checkbox checksum, achecksum;
	private Paging taxPaging;
	private Row kur, kur1, akur, akur1;
	private int _pageSize = 15;
	private int _startPageNumber = 0;
	private int _totalSize = 0;
	private boolean _needsTotalSizeUpdate = true;
	private String alias, un, idn, subj, val1, val2, strval1, strval2;
	private List<RefData> countries = new ArrayList<RefData>();
	private List<RefData> currencies = new ArrayList<RefData>();
	private List<RefCurrencyData> coursecurrencies = new ArrayList<RefCurrencyData>();
	private HashMap<String, String> curr_ = null;
	private String aotn, otn, summa2, summa1;
	private Label line1;
	private Label line2;
	private Label line3;
	private Label line4;
	private Label line5;
	private Label line6;
	private Label line7;
	private Label line8;
	
	private Tax current = new Tax();
	public TaxFilter filter = new TaxFilter();
	
	PagingListModel model = null;
	ListModelList lmodel = null;
	private AnnotateDataBinder binder;
	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	
	public TaxViewCtrl()
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
			ap1t39.setValue(idn);
			fp1t39.setValue(idn);
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
		parameter = (String[]) param.get("subj");
		if (parameter != null)
		{
			subj = (parameter[0]);
			// System.out.println("Garant  cont_val2 "+val2);
		}
		parameter = (String[]) param.get("summa1");
		if (parameter != null)
		{
			summa1 = (parameter[0]);
			
		}
		parameter = (String[]) param.get("summa2");
		if (parameter != null)
		{
			summa2 = (parameter[0]);
			System.out.println("Contract summa2 " + summa2);
		}
		alias = (String) session.getAttribute("alias");
		un = (String) session.getAttribute("un");
		countries = com.is.utils.RefDataService.getCountry(alias);
		currencies = com.is.utils.RefDataService.getCurrency(alias);
		curr_ = com.is.tf.contract.ContractService.getHCurr(alias);
		
		strval1 = curr_.get(val1);
		strval2 = curr_.get(val2);
		lp8t39.setValue(strval1);
		lp9t39.setValue(strval2);
		
		p3t39.setModel((new ListModelList(countries)));
		ap3t39.setModel((new ListModelList(countries)));
		fp3t39.setModel((new ListModelList(countries)));
		p4t39.setModel((new ListModelList(currencies)));
		ap4t39.setModel((new ListModelList(currencies)));
		fp4t39.setModel((new ListModelList(currencies)));
		
		line1.setValue(Labels.getLabel("tax.p2t39").replaceAll("<br>", "\r\n"));
		line2.setValue(Labels.getLabel("tax.p5t39").replaceAll("<br>", "\r\n"));
		line3.setValue(Labels.getLabel("tax.p4t39").replaceAll("<br>", "\r\n"));
		line4.setValue(Labels.getLabel("tax.p3t39").replaceAll("<br>", "\r\n"));
		line5.setValue(Labels.getLabel("tax.p11t39t").replaceAll("<br>", "\r\n"));
		line6.setValue(Labels.getLabel("tax.p12t39t").replaceAll("<br>", "\r\n"));
		line7.setValue(Labels.getLabel("tax.p17t39").replaceAll("<br>", "\r\n"));
		line8.setValue(Labels.getLabel("tax.p100t39").replaceAll("<br>", "\r\n"));
		
		dataGrid.setItemRenderer(new ListitemRenderer()
		{
			@SuppressWarnings("unchecked")
			public void render(Listitem row, Object data) throws Exception
			{
				Tax pTax = (Tax) data;
				row.setValue(pTax);
				row.appendChild(new Listcell(pTax.getP2t39() + ""));
				row.appendChild(new Listcell(pTax.getP5t39() + ""));
				row.appendChild(new Listcell(pTax.getP4t39()));
				row.appendChild(new Listcell(pTax.getP3t39()));
				row.appendChild(new Listcell(pTax.getP11t39()));
				row.appendChild(new Listcell(pTax.getP12t39()));
				row.appendChild(new Listcell(pTax.getP17t39() + ""));
				// row.appendChild(new Listcell(pTax.getP100t39()));
				
				row.appendChild(new Listcell(pTax.getId() + ""));
				row.appendChild(new Listcell(pTax.getP1t39()));
				row.appendChild(new Listcell(pTax.getP0t39()));
				row.appendChild(new Listcell(pTax.getP6t39() + ""));
				row.appendChild(new Listcell(pTax.getP7t39() + ""));
				row.appendChild(new Listcell(pTax.getP8t39() + ""));
				row.appendChild(new Listcell(pTax.getP9t39() + ""));
				row.appendChild(new Listcell(pTax.getP10t39()));
				
			}
		});
		
		refreshModel(_startPageNumber);
		
	}
	
	public void onPaging$taxPaging(ForwardEvent event)
	{
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModel(_startPageNumber);
	}
	
	private void refreshModel(int activePage)
	{
		filter.setP1t39(idn);
		taxPaging.setPageSize(_pageSize);
		model = new PagingListModel(activePage, _pageSize, filter, "");
		if (_needsTotalSizeUpdate)
		{
			_totalSize = model.getTotalSize();
			_needsTotalSizeUpdate = false;
		}
		taxPaging.setTotalSize(_totalSize);
		dataGrid.setModel((ListModel) model);
		if (model.getSize() > 0)
		{
			dataGrid.setSelectedIndex(0);
			sendSelEvt();
			this.current = (Tax) model.getElementAt(0);
			sendSelEvt();
		}
	}
	
	// Omitted...
	public Tax getCurrent()
	{
		return current;
	}
	
	public void setCurrent(Tax current)
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
		setCurrent();
	}
	
	public void onClick$btn_add()
	{
		onDoubleClick$dataGrid$grd();
		frmgrd.setVisible(false);
		addgrd.setVisible(true);
		fgrd.setVisible(false);
		ap1t39.setValue(idn);
		ap2t39.setValue(new Date());
		ap3t39.setSelectedIndex(-1);
		ap4t39.setSelectedIndex(-1);
		ap5t39.setValue("0");
		ap6t39.setValue("0");
		ap7t39.setValue("0");
		ap8t39.setValue("0");
		ap9t39.setValue("0");
		ap10t39.setValue("");
		akur.setVisible(true);
		akur1.setVisible(true);
	}
	
	public void onClick$btn_search()
	{
		onDoubleClick$dataGrid$grd();
		frmgrd.setVisible(false);
		addgrd.setVisible(false);
		fgrd.setVisible(true);
		fp1t39.setValue(idn);
	}
	
	public void onClick$btn_delete()
	{
		try
		{
			final BankServiceProxy ws = new BankServiceProxy((String) session.getAttribute("YESVO_URL"));
			com.sbs.service.TaxResult acr = new com.sbs.service.TaxResult();
			current.setP2t39(p2t39.getValue());
			current.setP3t39(p3t39.getValue());
			current.setP4t39(p4t39.getValue());
			current.setP5t39(p5t39.doubleValue());
			current.setP6t39(p6t39.doubleValue());
			current.setP7t39(p7t39.doubleValue());
			current.setP8t39(p8t39.doubleValue());
			current.setP9t39(p9t39.doubleValue());
			current.setP10t39(p10t39.getValue());
			current.setP11t39(p11t39.getValue());
			// TaxService.update(current);
			TaxResult ar = ws.saveTax(((String) (session.getAttribute("BankINN"))), idn, getTaxDel(current));
			if (CheckNull.isEmpty(p5t39.doubleValue()) && (Double.parseDouble(summa1) > p5t39.doubleValue()))
			{
				otn = "0";
			}
			else if (CheckNull.isEmpty(p5t39.doubleValue()) && (Double.parseDouble(summa1) < p5t39.doubleValue()))
			{
				otn = "1";
			}
			if (ar.getStatus() == 0)
			{
				alert("Сохранение успешно");
				onClick$btn_back();
				refreshModel(_startPageNumber);
				SelectEvent evt = new SelectEvent("onSelect", dataGrid, dataGrid.getSelectedItems());
				Events.sendEvent(evt);
			}
			else
			{
				alert("Error:" + ar.getStatus() + "; GTKid:" + ar.getGtkId() + "; Text:" + ar.getErrorMsg());
				ISLogger.getLogger().error(" in correcting Tax:" + ar.getStatus() + "; GTKid:" + ar.getGtkId() + "; Text:" + ar.getErrorMsg());
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
	
	public void onClick$btn_save()
	{
		try
		{
			final BankServiceProxy ws = new BankServiceProxy((String) session.getAttribute("YESVO_URL"));
			com.sbs.service.TaxResult acr = new com.sbs.service.TaxResult();
			if (addgrd.isVisible())
			{
				TaxResult tx = ws.saveTax(((String) (session.getAttribute("BankINN"))), idn, getTaxNew(new Tax(

				// Long.parseLong(aid.getValue()),
				// ap1t39.getValue(),
				// ap0t39.getValue(),
						ap2t39.getValue(),
						ap3t39.getValue(),
						ap4t39.getValue(),
						ap5t39.doubleValue(),
						ap6t39.doubleValue(),
						ap7t39.doubleValue(),
						ap8t39.doubleValue(),
						ap9t39.doubleValue(),
						ap10t39.getValue(),
						ap11t39.getValue()

				)));
				System.out.println("summa1=" + summa1 + " ap5t39.doubleValue()=" + ap5t39.doubleValue() + " aotn=" + aotn);
				if (CheckNull.isEmpty(ap5t39.doubleValue()) && (Double.parseDouble(summa1) > ap5t39.doubleValue()))
				{
					aotn = "0";
				}
				else if (CheckNull.isEmpty(ap5t39.doubleValue()) && (Double.parseDouble(summa1) < ap5t39.doubleValue()))
				{
					aotn = "1";
				}
				if (tx.getStatus() == 0)
				{
					alert("Сохранение успешно");
					onClick$btn_back();
					refreshModel(_startPageNumber);
					SelectEvent evt = new SelectEvent("onSelect", dataGrid, dataGrid.getSelectedItems());
					Events.sendEvent(evt);
					
				}
				else
				{
					alert("Error save New Tax; Status:" + tx.getStatus() + "; GTKid:" + tx.getGtkId() + "; Text:" + tx.getErrorMsg());
					System.out.println("Error save New Tax; Status:" + tx.getStatus() + "; GTKid:" + tx.getGtkId() + "; Text:" + tx.getErrorMsg());
					ISLogger.getLogger().error(" in save New Tax:" + tx.getStatus() + "; GTKid:" + tx.getGtkId() + "; Text:" + tx.getErrorMsg());
					CheckNull.clearForm(addgrd);
					frmgrd.setVisible(true);
					addgrd.setVisible(false);
					fgrd.setVisible(false);
				}
			}
			else if (fgrd.isVisible())
			{
				filter = new TaxFilter();
				if (!CheckNull.isEmpty(fid.getValue()))
				{
					Long.parseLong(fid.getValue());
				}
				filter.setP1t39(fp1t39.getValue());
				filter.setP0t39(fp0t39.getValue());
				filter.setP2t39(fp2t39.getValue());
				filter.setP3t39(fp3t39.getValue());
				filter.setP4t39(fp4t39.getValue());
				if (!CheckNull.isEmpty(fp5t39.doubleValue()) && fp5t39.doubleValue() > new Double("0"))
				{
					filter.setP5t39(fp5t39.doubleValue());
				}
				if (!CheckNull.isEmpty(fp6t39.doubleValue()) && fp6t39.doubleValue() > new Double("0"))
				{
					filter.setP6t39(fp6t39.doubleValue());
				}
				if (!CheckNull.isEmpty(fp7t39.doubleValue()) && fp7t39.doubleValue() > new Double("0"))
				{
					filter.setP7t39(fp7t39.doubleValue());
				}
				if (!CheckNull.isEmpty(fp8t39.doubleValue()) && fp8t39.doubleValue() > new Double("0"))
				{
					filter.setP8t39(fp8t39.doubleValue());
				}
				if (!CheckNull.isEmpty(fp9t39.doubleValue()) && fp9t39.doubleValue() > new Double("0"))
				{
					filter.setP9t39(fp9t39.doubleValue());
				}
				filter.setP10t39(fp10t39.getValue());
				filter.setP11t39(fp11t39.getValue());
			}
			else
			{
				
				// Long.parseLong(id.getValue());
				// current.setP1t39(p1t39.getValue());
				// current.setP0t39(p0t39.getValue());
				current.setP2t39(p2t39.getValue());
				current.setP3t39(p3t39.getValue());
				current.setP4t39(p4t39.getValue());
				current.setP5t39(p5t39.doubleValue());
				current.setP6t39(p6t39.doubleValue());
				current.setP7t39(p7t39.doubleValue());
				current.setP8t39(p8t39.doubleValue());
				current.setP9t39(p9t39.doubleValue());
				current.setP10t39(p10t39.getValue());
				current.setP11t39(p11t39.getValue());
				// TaxService.update(current);
				TaxResult ar = ws.saveTax(((String) (session.getAttribute("BankINN"))), idn, getTaxCorrect(current));
				if (CheckNull.isEmpty(p5t39.doubleValue()) && (Double.parseDouble(summa1) > p5t39.doubleValue()))
				{
					otn = "0";
				}
				else if (CheckNull.isEmpty(p5t39.doubleValue()) && (Double.parseDouble(summa1) < p5t39.doubleValue()))
				{
					otn = "1";
				}
				if (ar.getStatus() == 0)
				{
					alert("Сохранение успешно");
					onClick$btn_back();
					refreshModel(_startPageNumber);
					SelectEvent evt = new SelectEvent("onSelect", dataGrid, dataGrid.getSelectedItems());
					Events.sendEvent(evt);
				}
				else
				{
					alert("Error:" + ar.getStatus() + "; GTKid:" + ar.getGtkId() + "; Text:" + ar.getErrorMsg());
					ISLogger.getLogger().error(" in correcting Tax:" + ar.getStatus() + "; GTKid:" + ar.getGtkId() + "; Text:" + ar.getErrorMsg());
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
			alert("ERROR:" + e.getMessage());
		}
		
	}
	
	public void onClick$btn_cancel()
	{
		if (fgrd.isVisible())
		{
			filter = new TaxFilter();
		}
		onClick$btn_back();
		frmgrd.setVisible(true);
		addgrd.setVisible(false);
		fgrd.setVisible(false);
		CheckNull.clearForm(addgrd);
		CheckNull.clearForm(fgrd);
		refreshModel(_startPageNumber);
	}
	
	private com.sbs.service.Tax getTaxNew(Tax acr) throws Exception
	{
		java.util.Calendar cal = java.util.Calendar.getInstance();
		com.sbs.service.Tax res = new com.sbs.service.Tax();
		// res.setP0T37(Integer.parseInt(acr.getP0t37()));
		res.setP0T39(0);
		cal.setTime(df.parse(df.format(acr.getP2t39())));
		res.setP2T39(cal);
		res.setP3T39(acr.getP3t39());
		res.setP4T39(acr.getP4t39());
		res.setP5T39(acr.getP5t39());
		res.setP6T39(acr.getP6t39());
		if (acr.getP7t39() != null)
		{
			res.setP7T39(acr.getP7t39());
		}
		res.setP8T39(acr.getP8t39());
		if (acr.getP9t39() != null)
		{
			res.setP9T39(acr.getP9t39());
		}
		res.setP10T39(acr.getP10t39());
		// res.setP11T39(Integer.parseInt(acr.getP11t39()));
		res.setP12T39((String) session.getAttribute("un"));
		res.setP15T39(Short.parseShort(aotn));
		
		return res;
	}
	
	private com.sbs.service.Tax getTaxCorrect(Tax acr) throws Exception
	{
		java.util.Calendar cal = java.util.Calendar.getInstance();
		com.sbs.service.Tax res = new com.sbs.service.Tax();
		// res.setP0T37(Integer.parseInt(acr.getP0t37()));
		res.setP0T39(1);
		cal.setTime(df.parse(df.format(acr.getP2t39())));
		res.setP2T39(cal);
		res.setP3T39(acr.getP3t39());
		res.setP4T39(acr.getP4t39());
		res.setP5T39(acr.getP5t39());
		res.setP6T39(acr.getP6t39());
		if (acr.getP7t39() != null)
		{
			res.setP7T39(acr.getP7t39());
		}
		res.setP8T39(acr.getP8t39());
		if (acr.getP9t39() != null)
		{
			res.setP9T39(acr.getP9t39());
		}
		res.setP10T39(acr.getP10t39());
		res.setP11T39(Integer.parseInt(acr.getP11t39()));
		res.setP12T39((String) session.getAttribute("un"));
		res.setP15T39(Short.parseShort(otn));
		
		return res;
	}
	
	private com.sbs.service.Tax getTaxDel(Tax acr) throws Exception
	{
		java.util.Calendar cal = java.util.Calendar.getInstance();
		com.sbs.service.Tax res = new com.sbs.service.Tax();
		// res.setP0T37(Integer.parseInt(acr.getP0t37()));
		res.setP0T39(2);
		cal.setTime(df.parse(df.format(acr.getP2t39())));
		res.setP2T39(cal);
		res.setP3T39(acr.getP3t39());
		res.setP4T39(acr.getP4t39());
		res.setP5T39(acr.getP5t39());
		res.setP6T39(acr.getP6t39());
		if (acr.getP7t39() != null)
		{
			res.setP7T39(acr.getP7t39());
		}
		res.setP8T39(acr.getP8t39());
		if (acr.getP9t39() != null)
		{
			res.setP9T39(acr.getP9t39());
		}
		res.setP10T39(acr.getP10t39());
		res.setP11T39(Integer.parseInt(acr.getP11t39()));
		res.setP12T39((String) session.getAttribute("un"));
		res.setP15T39(Short.parseShort(otn));
		
		return res;
	}
	
	private void setCurrent()
	{
		if (current != null)
		{
			kur.setVisible(true);
			kur1.setVisible(true);
			if (val1.equalsIgnoreCase(current.getP4t39()))
			{
				kur.setVisible(false);
			}
			if (val2.equalsIgnoreCase(current.getP4t39()))
			{
				kur1.setVisible(false);
			}
			coursecurrencies = ContractService.getCourseCurr_18t1_19t1_withOther(idn, df.format(current.getP2t39()), "'" + current.getP4t39() + "'", alias);
			p6t391.setModel((new ListModelList(coursecurrencies)));
			p6t393.setModel((new ListModelList(coursecurrencies)));
			p7t391.setModel((new ListModelList(coursecurrencies)));
			p7t393.setModel((new ListModelList(coursecurrencies)));
		}
	}
	
	public void onChange$p4t39()
	{
		current.setP4t39(p4t39.getValue());
		coursecurrencies = ContractService.getCourseCurr_18t1_19t1_withOther(idn, df.format(p2t39.getValue()), "'" + p4t39.getValue() + "'", alias);
		p6t391.setModel((new ListModelList(coursecurrencies)));
		p6t393.setModel((new ListModelList(coursecurrencies)));
		p7t391.setModel((new ListModelList(coursecurrencies)));
		p7t393.setModel((new ListModelList(coursecurrencies)));
		// setCourse(true);
		kur.setVisible(true);
		kur1.setVisible(true);
		if (val1.equalsIgnoreCase(p4t39.getValue()))
		{
			kur.setVisible(false);
		}
		if (val2.equalsIgnoreCase(p4t39.getValue()))
		{
			kur1.setVisible(false);
		}
		
	}
	
	public void onInitRenderLater$p6t391()
	{
		setCourse(false);
	}
	
	public void onInitRenderLater$p6t393()
	{
		setCourse(false);
	}
	
	public void onInitRenderLater$p7t391()
	{
		setCourse(false);
	}
	
	public void onInitRenderLater$p7t393()
	{
		setCourse(false);
	}
	
	private void setCourse(Boolean getNewCourse)
	{
		if (getNewCourse)
		{
			coursecurrencies = ContractService.getCourseCurr_18t1_19t1_withOther(idn, df.format(p2t39.getValue()), "'" + p4t39.getValue() + "'", alias);
			p6t391.setModel((new ListModelList(coursecurrencies)));
			p6t393.setModel((new ListModelList(coursecurrencies)));
			p7t391.setModel((new ListModelList(coursecurrencies)));
			p7t393.setModel((new ListModelList(coursecurrencies)));
		}
		if (coursecurrencies.size() > 0)
		{
			RefCurrencyData curr1 = TaxService.getRefCurrencyData(p4t39.getValue(), coursecurrencies);
			RefCurrencyData curr2 = TaxService.getRefCurrencyData(val1, coursecurrencies);
			RefCurrencyData curr3 = TaxService.getRefCurrencyData(val2, coursecurrencies);
			if (curr1 != null && curr2 != null && curr3 != null)
			{
				if (curr1.getCourse() > curr2.getCourse())
				{
					p6t391.setSelecteditem(curr1.getKod());
					p6t393.setSelecteditem(curr2.getKod());
				}
				else
				{
					p6t391.setSelecteditem(curr2.getKod());
					p6t393.setSelecteditem(curr1.getKod());
				}
				if (curr1.getCourse() > curr3.getCourse())
				{
					p7t391.setSelecteditem(curr1.getKod());
					p7t393.setSelecteditem(curr3.getKod());
				}
				else
				{
					p7t391.setSelecteditem(curr3.getKod());
					p7t393.setSelecteditem(curr1.getKod());
				}
				countCourse(false);
			}
		}
	}
	
	public void onChange$p5t39()
	{
		countCourse(false);
	}
	
	public void onChange$p6t39()
	{
		countCourse(false);
	}
	
	public void onChange$p7t39()
	{
		countCourse(false);
	}
	
	public void onChange$p8t39()
	{
		countCourse(false);
	}
	
	public void onChange$p9t39()
	{
		countCourse(false);
	}
	
	private void countCourse(Boolean setCourse)
	{
		try
		{
			if (!CheckNull.isEmpty(p6t393.getValue()) && !CheckNull.isEmpty(p6t391.getValue())
					&& !CheckNull.isEmpty(p7t393.getValue()) && !CheckNull.isEmpty(p7t391.getValue()))
			{
				// System.out.println("***"+p15t351.getValue()+" - "+p15t353.getValue());
				if (setCourse)
				{
					p6t39.setValue("" + (p6t391.getCourse() / p6t393.getCourse()));
					current.setP6t39((p6t391.getCourse() / p6t393.getCourse()));
					p7t39.setValue("" + (p7t391.getCourse() / p7t393.getCourse()));
					current.setP7t39((p7t391.getCourse() / p7t393.getCourse()));
				}
				// System.out.println("По курсу ЦБ: "+(p6t391.getCourse()/p6t393.getCourse())+" "+p6t391.getCourse()+" "+p6t393.getCourse());
				// System.out.println("По курсу ЦБ: "+(p7t391.getCourse()/p7t393.getCourse())+" "+p7t391.getCourse()+" "+p7t393.getCourse());
				cbcourse.setValue("По курсу ЦБ: " + (p6t391.getCourse() / p6t393.getCourse()));
				cbcourse1.setValue("По курсу ЦБ: " + (p7t391.getCourse() / p7t393.getCourse()));
				Double p8t39val = null;
				Double p9t39val = null;
				if (p4t39.getValue().equalsIgnoreCase(p6t393.getValue()))
				{
					p8t39val = p8t39.doubleValue() * p6t39.doubleValue();
					// System.out.println(p8t39.doubleValue() + "*" +
					// p6t39.doubleValue() +"="+p8t39val);
				}
				else
				{
					p8t39val = p8t39.doubleValue() / p6t39.doubleValue();
					// System.out.println(p8t39.doubleValue() + "/" +
					// p6t39.doubleValue() +"="+p8t39val);
				}
				if (p4t39.getValue().equalsIgnoreCase(p7t393.getValue()))
				{
					p9t39val = p9t39.doubleValue() * p7t39.doubleValue();
					// System.out.println(p9t39.doubleValue() + "*" +
					// p7t39.doubleValue() +"="+p9t39val);
				}
				else
				{
					p9t39val = p9t39.doubleValue() / p7t39.doubleValue();
					// System.out.println(p9t39.doubleValue() + "/" +
					// p7t39.doubleValue() +"="+p9t39val);
				}
				Double db = p8t39val + p9t39val;
				// System.out.println(p8t39val + "+" + p9t39val +"="+db);
				Boolean bool = (db == p5t39.doubleValue());
				checksum.setChecked(bool);
				checksum.setLabel((bool ? "Сумма гарантии полность соответствует указанному курсу!" : "Сумма гарантии не соответствует указанному курсу!") + "(" + db + ")");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void onChange$ap4t39()
	{
		coursecurrencies = ContractService.getCourseCurr_18t1_19t1_withOther(idn, df.format(ap2t39.getValue()), "'" + ap4t39.getValue() + "'", alias);
		ap6t391.setModel((new ListModelList(coursecurrencies)));
		ap6t393.setModel((new ListModelList(coursecurrencies)));
		ap7t391.setModel((new ListModelList(coursecurrencies)));
		ap7t393.setModel((new ListModelList(coursecurrencies)));
		// setCourse(true);
		akur.setVisible(true);
		akur1.setVisible(true);
		if (val1.equalsIgnoreCase(ap4t39.getValue()))
		{
			akur.setVisible(false);
		}
		if (val2.equalsIgnoreCase(ap4t39.getValue()))
		{
			akur1.setVisible(false);
		}
		
	}
	
	public void onInitRenderLater$ap6t391()
	{
		asetCourse(false);
	}
	
	public void onInitRenderLater$ap6t393()
	{
		asetCourse(false);
	}
	
	public void onInitRenderLater$ap67t391()
	{
		asetCourse(false);
	}
	
	public void onInitRenderLater$ap7t393()
	{
		asetCourse(false);
	}
	
	public void onChange$ap5t39()
	{
		acountCourse(false);
	}
	
	public void onChange$ap6t39()
	{
		acountCourse(false);
	}
	
	public void onChange$ap7t39()
	{
		acountCourse(false);
	}
	
	public void onChange$ap8t39()
	{
		acountCourse(false);
	}
	
	public void onChange$ap9t39()
	{
		acountCourse(false);
	}
	
	private void asetCourse(Boolean getNewCourse)
	{
		if (getNewCourse)
		{
			coursecurrencies = ContractService.getCourseCurr_18t1_19t1_withOther(idn, df.format(ap2t39.getValue()), "'" + ap4t39.getValue() + "'", alias);
			ap6t391.setModel((new ListModelList(coursecurrencies)));
			ap6t393.setModel((new ListModelList(coursecurrencies)));
			ap7t391.setModel((new ListModelList(coursecurrencies)));
			ap7t393.setModel((new ListModelList(coursecurrencies)));
		}
		if (coursecurrencies.size() > 0)
		{
			RefCurrencyData curr1 = TaxService.getRefCurrencyData(ap4t39.getValue(), coursecurrencies);
			RefCurrencyData curr2 = TaxService.getRefCurrencyData(val1, coursecurrencies);
			RefCurrencyData curr3 = TaxService.getRefCurrencyData(val2, coursecurrencies);
			if (curr1 != null && curr2 != null && curr3 != null)
			{
				if (curr1.getCourse() > curr2.getCourse())
				{
					ap6t391.setSelecteditem(curr1.getKod());
					ap6t393.setSelecteditem(curr2.getKod());
				}
				else
				{
					ap6t391.setSelecteditem(curr2.getKod());
					ap6t393.setSelecteditem(curr1.getKod());
				}
				if (curr1.getCourse() > curr3.getCourse())
				{
					ap7t391.setSelecteditem(curr1.getKod());
					ap7t393.setSelecteditem(curr3.getKod());
				}
				else
				{
					ap7t391.setSelecteditem(curr3.getKod());
					ap7t393.setSelecteditem(curr1.getKod());
				}
				acountCourse(true);
			}
		}
	}
	
	private void acountCourse(Boolean setCourse)
	{
		try
		{
			if (!CheckNull.isEmpty(ap6t393.getValue()) && !CheckNull.isEmpty(ap6t391.getValue())
					&& !CheckNull.isEmpty(ap7t393.getValue()) && !CheckNull.isEmpty(ap7t391.getValue()))
			{
				// System.out.println("***"+p15t351.getValue()+" - "+p15t353.getValue());
				if (setCourse)
				{
					ap6t39.setValue("" + (ap6t391.getCourse() / ap6t393.getCourse()));
					// current.setP6t39((ap6t391.getCourse()/ap6t393.getCourse()));
					ap7t39.setValue("" + (ap7t391.getCourse() / ap7t393.getCourse()));
					// current.setP7t39((p7t391.getCourse()/p7t393.getCourse()));
				}
				acbcourse.setValue("По курсу ЦБ: " + (ap6t391.getCourse() / ap6t393.getCourse()));
				acbcourse1.setValue("По курсу ЦБ: " + (ap7t391.getCourse() / ap7t393.getCourse()));
				Double ap8t39val = null;
				Double ap9t39val = null;
				if (ap4t39.getValue().equalsIgnoreCase(ap6t393.getValue()))
				{
					ap8t39val = ap8t39.doubleValue() * ap6t39.doubleValue();
				}
				else
				{
					ap8t39val = ap8t39.doubleValue() / ap6t39.doubleValue();
				}
				if (ap4t39.getValue().equalsIgnoreCase(ap7t393.getValue()))
				{
					ap9t39val = ap9t39.doubleValue() * ap7t39.doubleValue();
				}
				else
				{
					ap9t39val = ap9t39.doubleValue() / ap7t39.doubleValue();
				}
				Double db = ap8t39val + ap9t39val;
				Boolean bool = (db == ap5t39.doubleValue());
				achecksum.setChecked(bool);
				achecksum.setLabel((bool ? "Сумма гарантии полность соответствует указанному курсу!" : "Сумма гарантии не соответствует указанному курсу!") + "(" + db + ")");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
}
