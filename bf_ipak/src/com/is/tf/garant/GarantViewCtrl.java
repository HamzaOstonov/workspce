package com.is.tf.garant;

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
import org.zkoss.zkplus.databind.BindingListModelList;
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
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Row;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;
import org.zkoss.zul.event.PagingEvent;
import org.zkoss.zul.impl.InputElement;

import com.is.ISLogger;
import com.is.tf.contract.ContractService;
import com.is.tf.currency.RefCurrencyBox;
import com.is.tf.currency.RefCurrencyData;
import com.is.tf.garantsumchange.Garantsumchange;
import com.is.tf.garanttimechange.garanttimechange;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.RefData;
import com.is.utils.refobj.XMLSerializer;
import com.sbs.service.BankServiceProxy;
import com.sbs.service.GarantResult;
import com.sbs.service.GarantSumChangeResult;
import com.sbs.service.GarantTimeChangeResult;

public class GarantViewCtrl extends GenericForwardComposer
{
	private Div frm;
	private Listbox dataGrid, SumchangeGrid, TimechangeGrid;
	private Paging contactPaging;
	private Div grd, sum_div, verh_div;
	private Grid gar_sum, gar_time, addgrd, frmgrd, fgrd, sum_grd, time_grd;
	private Toolbarbutton btn_last, btn_save2, btn_delete2, btn_add2, btn_back2;
	private Toolbarbutton btn_next;
	private Toolbarbutton btn_prev;
	private Toolbarbutton btn_first, btn_confirm33, btn_confirmM, btn_rejectM;
	private Toolbarbutton btn_add;
	private Toolbarbutton btn_search;
	private Toolbarbutton btn_back, gar1, gar2, gar;
	private Toolbarbutton btn_refresh;
	private Toolbarbutton btn_confirm, btn_reject, btn_edit, btn_delete, btn_save;
	private Toolbarbutton btn_edit2, btn_confirmBuhg, btn_rejectBuhg, btn_cancel;
	
	private Toolbar tb;
	private Listheader list_p2t19a, list_p4t19a, list_p5t19a, list_p3t19a;
	private Textbox p5t19a, p3t19a, p8t19a, p10t19, p12t20, p15t20, id, p1t18, p2t18, p3t18, p11t18, p3t19;
	private Textbox p10t19a, ap12t18, ap13t18, ap14t18, ap15t18, ap16t18, ap100t18, aid, ap1t18, ap2t18, ap3t18, ap11t18;
	private Textbox fid, fp1t18, fp2t18, fp3t18, fp11t18;
	private Datebox p2t19a, p4t19a, p99t19a, p4t18, ap4t18, fp4t18, p10t18, ap10t18, fp10t18, p2t19, p4t19;
	private Decimalbox p5t20a, p7t20a, p6t20a, p4t20a, p4t20, p3t20, p5t20, p6t20, p7t20, p6t18, p7t18, p7t182, p8t18, p9t18, ap6t18, ap7t18, ap7t182, ap8t18, ap9t18, fp6t18, fp7t18, fp8t18, fp9t18;
	private RefCBox p6t19a, p7t19a, p5t18, ap5t18, fp5t18, p2t20, p9t20, p9t20a, p7t19, p6t19, p8t20, p8t20a;
	private RefCurrencyBox p7t181, ap7t181, p7t183, ap7t183, p5t201, p5t203;
	private Intbox p9t19, p0t18, ap0t18, fp0t18;
	private Label aconr_val2, aconr_val1, conr_val1, conr_val2, conr_val1a, conr_val2a, p5t1833, pp5t182, gar_val, cbcourse, acbcourse, p3t201;
	private Checkbox checksum, achecksum;
	private Paging garantPaging;
	private int _pageSize = 15;
	private int _startPageNumber = 0;
	private int _totalSize = 0;
	private HashMap<String, String> curr_ = null;
	private boolean _needsTotalSizeUpdate = true;
	private String alias, idn, val1, val2, cu;
	private String otn2, summa1, summa2, sparam1;
	private Long id_contract;
	private String aotn, otn;
	private Row kur, sum_gar2, gar_date1, gar_date2;
	private Row akur, asum_gar2, agar_date1, agar_date2, agar_date5_19a, agar_date4_19a;
	private Row row_p7t19a, row_p8t19a, row_p7t20a, row_p9t20a, row_p10t20a, agar_date1_19, agar_date2_19, agar_date3_19, agar_date4_19, row_p7t19, row_p8t19, row_p9t20, row_p10t20;
	private Textbox p5t19, p8t19, p10t20, p10t20a;
	private long gid, gidc, idc;
	private List<RefData> currencies = new ArrayList<RefData>();
	private List<RefData> reasons = new ArrayList<RefData>();
	private List<RefData> agriments = new ArrayList<RefData>();
	private List<RefCurrencyData> currenciesg = new ArrayList<RefCurrencyData>();
	private int desktopHeight = 0;
	private Tab tab_garsum, tab_gartime;
	private Label line1;
	private Label line2;
	private Label line3;
	private Label line4;
	private Label line5;
	private Label line6;
	private Label line7;
	private Label line8;
	private Label line9;
	
	private Garant current = new Garant();
	private Garantsumchange currentSum = new Garantsumchange();
	private garanttimechange currentTime = new garanttimechange();
	public GarantFilter filter = new GarantFilter();
	
	PagingListModel model = null;
	ListModelList lmodel = null;
	private AnnotateDataBinder binder;
	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	
	public GarantViewCtrl()
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
		binder.bindBean("currentSum", this.currentSum);
		binder.bindBean("currentTime", this.currentTime);
		// binder.bindBean("currentadd", this.currentadd);
		binder.loadAll();
		String[] parameter = (String[]) param.get("ht");
		if (parameter != null)
		{
			_pageSize = Integer.parseInt(parameter[0]) / 36;
			dataGrid.setRows(Integer.parseInt(parameter[0]) / 36);
			// desktopHeight= Integer.parseInt( parameter[0]);
			
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
			idc = Long.parseLong(parameter[0]);
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
		
		parameter = (String[]) param.get("spr");
		if (parameter != null) sparam1 = (parameter[0]);
		
		curr_ = com.is.tf.contract.ContractService.getHCurr(alias);
		filter.setP1t18(idn);
		// conr_val1.setValue(val1);
		conr_val2.setValue(curr_.get(val2));
		conr_val1.setValue(curr_.get(val1));
		conr_val2a.setValue(curr_.get(val2));
		aconr_val2.setValue(curr_.get(val2));
		aconr_val1.setValue(curr_.get(val1));
		conr_val1a.setValue(curr_.get(val1));
		
		tab_garsum.setVisible(false);
		tab_gartime.setVisible(false);
		sum_div.setVisible(false);
		time_grd.setVisible(false);
		sum_grd.setVisible(false);
		verh_div.setVisible(false);
		
		// filter.set(cont_idn);
		SumchangeGrid.setItemRenderer(new ListitemRenderer()
		{
			@SuppressWarnings("unchecked")
			public void render(Listitem row, Object data) throws Exception
			{
				Garantsumchange pGarantsumchange = (Garantsumchange) data;
				
				row.setValue(pGarantsumchange);
				
				row.appendChild(new Listcell(pGarantsumchange.getP11t20() + ""));
				row.appendChild(new Listcell(pGarantsumchange.getId() + ""));
				row.appendChild(new Listcell(pGarantsumchange.getP0t20()));
				row.appendChild(new Listcell(pGarantsumchange.getP1t20()));
				row.appendChild(new Listcell(pGarantsumchange.getP3t20() + ""));
				row.appendChild(new Listcell(pGarantsumchange.getP4t20() + ""));
				row.appendChild(new Listcell(pGarantsumchange.getP5t20() + ""));
				row.appendChild(new Listcell(pGarantsumchange.getP6t20() + ""));
				row.appendChild(new Listcell(pGarantsumchange.getP7t20() + ""));
				row.appendChild(new Listcell(pGarantsumchange.getP8t20()));
				row.appendChild(new Listcell(pGarantsumchange.getP9t20()));
				row.appendChild(new Listcell(pGarantsumchange.getP10t20()));
				row.appendChild(new Listcell(pGarantsumchange.getP12t20()));
				row.appendChild(new Listcell(pGarantsumchange.getP15t20()));
				row.appendChild(new Listcell(pGarantsumchange.getP99t20() + ""));
				row.appendChild(new Listcell(pGarantsumchange.getP100t20()));
				// row.appendChild(new Listcell(pGarantsumchange.getP101t20()));
				
			}
		});
		refreshModel(_startPageNumber);
		
		TimechangeGrid.setItemRenderer(new ListitemRenderer()
		{
			@SuppressWarnings("unchecked")
			public void render(Listitem row, Object data) throws Exception
			{
				garanttimechange pGaranttimechange = (garanttimechange) data;
				
				row.appendChild(new Listcell(pGaranttimechange.getId() + ""));
				row.appendChild(new Listcell(pGaranttimechange.getP9t19() + ""));
				row.appendChild(new Listcell(pGaranttimechange.getP0t19()));
				row.appendChild(new Listcell(pGaranttimechange.getP1t19()));
				row.appendChild(new Listcell(pGaranttimechange.getP2t19() + ""));
				row.appendChild(new Listcell(pGaranttimechange.getP3t19()));
				row.appendChild(new Listcell(pGaranttimechange.getP4t19() + ""));
				row.appendChild(new Listcell(pGaranttimechange.getP5t19()));
				row.appendChild(new Listcell(pGaranttimechange.getP6t19()));
				row.appendChild(new Listcell(pGaranttimechange.getP7t19()));
				row.appendChild(new Listcell(pGaranttimechange.getP8t19()));
				row.appendChild(new Listcell(pGaranttimechange.getP10t19()));
				row.appendChild(new Listcell(pGaranttimechange.getP100t19()));
				
			}
		});
		
		line1.setValue(Labels.getLabel("garant.p3t18tab").replaceAll("<br>", "\r\n"));
		line2.setValue(Labels.getLabel("garant.p4t18tab").replaceAll("<br>", "\r\n"));
		line3.setValue(Labels.getLabel("garant.p6t18tab").replaceAll("<br>", "\r\n"));
		line4.setValue(Labels.getLabel("garant.p5t18tab").replaceAll("<br>", "\r\n"));
		line5.setValue(Labels.getLabel("garant.p10t18tab").replaceAll("<br>", "\r\n"));
		line6.setValue(Labels.getLabel("garant.p11t18tab").replaceAll("<br>", "\r\n"));
		line7.setValue(Labels.getLabel("garant.p12t18tab").replaceAll("<br>", "\r\n"));
		line8.setValue(Labels.getLabel("garant.p16t18tab").replaceAll("<br>", "\r\n"));
		line9.setValue(Labels.getLabel("garant.p100t18tab").replaceAll("<br>", "\r\n"));
		
		dataGrid.setItemRenderer(new ListitemRenderer()
		{
			@SuppressWarnings("unchecked")
			public void render(Listitem row, Object data) throws Exception
			{
				Garant pGarant = (Garant) data;
				row.setValue(pGarant);
				
				row.appendChild(new Listcell(pGarant.getP3t18() + ""));
				row.appendChild(new Listcell(pGarant.getP4t18() + ""));
				row.appendChild(new Listcell(pGarant.getP6t18() + ""));
				row.appendChild(new Listcell(com.is.tf.contract.SPR.getVal(String.valueOf(pGarant.getP5t18()))));
				row.appendChild(new Listcell(pGarant.getP10t18() + ""));
				row.appendChild(new Listcell(pGarant.getP11t18()));
				row.appendChild(new Listcell(pGarant.getP12t18()));
				row.appendChild(new Listcell(pGarant.getP16t18() + ""));
				row.appendChild(new Listcell(com.is.tf.contract.SPR.getP100Value(String.valueOf(pGarant.getP100t18()))));
				
				/*
				 * row.appendChild(new Listcell(pGarant.getId()+ ""));
				 * row.appendChild(new Listcell(pGarant.getP0t18()+""));
				 * row.appendChild(new Listcell(pGarant.getP1t18()));
				 * row.appendChild(new Listcell(pGarant.getP2t18()));
				 * row.appendChild(new Listcell(pGarant.getP3t18()+""));
				 * row.appendChild(new Listcell(pGarant.getP4t18()+ ""));
				 * row.appendChild(new Listcell(pGarant.getP5t18()));
				 * row.appendChild(new Listcell(pGarant.getP6t18()+ ""));
				 * row.appendChild(new Listcell(pGarant.getP7t18()+""));
				 * row.appendChild(new Listcell(pGarant.getP8t18()+""));
				 * row.appendChild(new Listcell(pGarant.getP9t18()+""));
				 * row.appendChild(new Listcell(pGarant.getP10t18()+""));
				 * row.appendChild(new Listcell(pGarant.getP11t18()));
				 * row.appendChild(new Listcell(pGarant.getP12t18()));
				 * row.appendChild(new Listcell(pGarant.getP15t18()));
				 * row.appendChild(new Listcell(pGarant.getP16t18()+""));
				 * row.appendChild(new Listcell(pGarant.getP100t18()));
				 */
			}
		});
		
		System.out.println("idn,alias " + idn + "  " + alias);
		currencies = com.is.tf.contract.ContractService.getMyCurr(idn, alias);
		reasons = ContractService.getReasons("4,5", alias);;
		if (agriments != null)
		{
			agriments = com.is.tf.contract.ContractService.getAgreement(idn, alias);
			p7t19.setModel((new ListModelList(agriments)));
			p9t20.setModel((new ListModelList(agriments)));
		}
		p5t18.setModel((new ListModelList(currencies)));
		ap5t18.setModel((new ListModelList(currencies)));
		fp5t18.setModel((new ListModelList(currencies)));
		// p2t20.setModel((new ListModelList(currencies)));
		// p9t20.setModel((new ListModelList(currencies)));
		p6t19.setModel((new ListModelList(reasons)));
		p8t20.setModel((new ListModelList(reasons)));
		p8t20a.setModel((new ListModelList(reasons)));
		p6t19a.setModel((new ListModelList(reasons)));
		
		refreshModel(_startPageNumber);
		p3t201.setValue(curr_.get(current.getP5t18()));
		
	}
	
	public void onPaging$garantPaging(ForwardEvent event)
	{
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModel(_startPageNumber);
	}
	
	private void refreshModel(int activePage)
	{
		filter.setP1t18(idn);
		garantPaging.setPageSize(_pageSize);
		model = new PagingListModel(activePage, _pageSize, filter, "");
		if (_needsTotalSizeUpdate)
		{
			_totalSize = model.getTotalSize(filter, "");
			// _needsTotalSizeUpdate = false;
		}
		garantPaging.setTotalSize(_totalSize);
		dataGrid.setModel((ListModel) model);
		if (model.getSize() > 0)
		{
			dataGrid.setSelectedIndex(0);
			sendSelEvt(true);
			this.current = (Garant) model.getElementAt(0);
			sendSelEvt(true);
		}
		
	}
	
	public void onSelect$dataGrid$grd()
	{
		sendSelEvt(false);
	}
	
	// Omitted...
	public Garant getCurrent()
	{
		return current;
	}
	
	public void setCurrent(Garant current)
	{
		this.current = current;
	}
	
	public Garantsumchange getCurrentSum()
	{
		return currentSum;
	}
	
	public void setCurrentSum(Garantsumchange currentSum)
	{
		this.currentSum = currentSum;
	}
	
	public garanttimechange getCurrentTime()
	{
		return currentTime;
	}
	
	public void setCurrentTime(garanttimechange currentTime)
	{
		this.currentTime = currentTime;
	}
	
	public void onDoubleClick$dataGrid$grd()
	{
		try
		{
			grd.setVisible(false);
			frm.setVisible(true);
			gar_sum.setVisible(false);
			gar_time.setVisible(false);
			frmgrd.setVisible(true);
			addgrd.setVisible(false);
			fgrd.setVisible(false);
			btn_back.setImage("/images/folder.png");
			btn_back.setLabel(Labels.getLabel("grid"));
			tab_garsum.setVisible(true);
			tab_gartime.setVisible(true);
			SumchangeGrid.setVisible(true);
			tab_garsum.setVisible(true);
			tab_gartime.setVisible(true);
			sum_div.setVisible(true);
			time_grd.setVisible(true);
			sum_grd.setVisible(true);
			verh_div.setVisible(true);
			TimechangeGrid.setVisible(true);
			btn_edit.setVisible(true);
			
			setCurrent();
			setCurrentAdd();
			// System.out.println("current.getP5t18() "+current.getP5t18()+"val1 "+val1+"val2 "+val2+"current.getP5t18() "+current.getP5t18());
			gid = idc;
			// cu=current.getP5t18();
			// p5t183.setModel((new ListModelList(currenciesg)));
			// gar_val.setValue(curr_.get(cu));
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			alert("onDoubleClick$dataGrid$grd()=" + e.getMessage());
		}
	}
	
	public void onClick$btn_refresh() throws Exception
	{
		refresh(idn);
	}
	
	public void onClick$btn_back()
	{
		if (frm.isVisible())
		{
			frm.setVisible(false);
			gar_time.setVisible(false);
			grd.setVisible(true);
			btn_back.setImage("/images/file.png");
			btn_back.setLabel(Labels.getLabel("back"));
		}
		else onDoubleClick$dataGrid$grd();
	}
	
	public void onClick$btn_first()
	{
		dataGrid.setSelectedIndex(0);
		sendSelEvt(true);
	}
	
	public void onClick$btn_last()
	{
		dataGrid.setSelectedIndex(model.getSize() - 1);
		sendSelEvt(true);
	}
	
	public void onClick$btn_prev()
	{
		if (dataGrid.getSelectedIndex() != 0)
		{
			dataGrid.setSelectedIndex(dataGrid.getSelectedIndex() - 1);
			sendSelEvt(true);
		}
	}
	
	public void onClick$btn_next()
	{
		if (dataGrid.getSelectedIndex() != (model.getSize() - 1))
		{
			dataGrid.setSelectedIndex(dataGrid.getSelectedIndex() + 1);
			sendSelEvt(true);
		}
	}
	
	private void sendSelEvt(Boolean sendEvt)
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
		if (sendEvt)
		{
			SelectEvent evt = new SelectEvent("onSelect", dataGrid, dataGrid.getSelectedItems());
			Events.sendEvent(evt);
		}
		if (frm.isVisible())
		{
			onDoubleClick$dataGrid$grd();
		}
		if ((ap6t18.doubleValue() != 0) && ((Double.parseDouble(summa1) > ap6t18.doubleValue())))
		{
			ap15t18.setValue("0");
		}
		else if ((ap6t18.doubleValue() != 0) && ((Double.parseDouble(summa1) < ap6t18.doubleValue())))
		{
			ap15t18.setValue("1");
		}
		
	}
	
	public void onClick$gar1()
	{
		onDoubleClick$dataGrid$grd();
		frmgrd.setVisible(false);
		addgrd.setVisible(false);
		fgrd.setVisible(false);
		gar_sum.setVisible(true);
		{
			if (gar_sum.isVisible())
			{
				InputElement[] list = { p2t20, p5t20, p3t20 };
				for (int i = 0; i < list.length; i++)
				{
					if (list[i] != null)
					{
						list[i].setDisabled(true);
					}
				}
			}
		}
		gar_time.setVisible(false);
		gar.setVisible(true);
		p5t201.setSelecteditem(p7t181.getValue());
		p5t203.setSelecteditem(p7t183.getValue());
		p12t20.setValue((String) session.getAttribute("un"));
		// alert("current.getP5t18()="+current.getP5t18()+" p3t201="+p3t201.getValue());
		p3t201.setValue(curr_.get(current.getP5t18()));
	}
	
	public void onClick$gar()
	{
		
		onDoubleClick$dataGrid$grd();
		frmgrd.setVisible(true);
		addgrd.setVisible(false);
		fgrd.setVisible(false);
		gar_sum.setVisible(false);
		gar_time.setVisible(false);
		gar.setVisible(false);
		
	}
	
	public void onClick$btn_edit()
	{
		btn_delete2.setVisible(true);
		btn_save2.setVisible(true);
		btn_back2.setVisible(true);
		btn_add2.setVisible(true);
		
		if (SumchangeGrid.isVisible())
		{
			p4t20a.setReadonly(false);
			p5t20a.setReadonly(false);
			p6t20a.setReadonly(false);
			p7t20a.setReadonly(false);
			p8t20a.setDisabled(false);
			p9t20a.setReadonly(false);
			p10t20a.setReadonly(false);
		}
		if (TimechangeGrid.isVisible())
		{
			p4t19a.setDisabled(false);
			p2t19a.setDisabled(false);
			p5t19a.setReadonly(false);
			p6t19a.setDisabled(false);
			p7t19a.setReadonly(false);
			p8t19a.setDisabled(false);
		}
		
		if (frmgrd.isVisible())
		{
			btn_save.setVisible(true);
			btn_delete.setVisible(true);
			btn_confirm.setVisible(true);
			btn_reject.setVisible(true);
			
			// setFields(false);
			
		}
		else if (addgrd.isVisible())
		{
			btn_save.setVisible(true);
			btn_delete.setVisible(true);
			btn_confirm.setVisible(true);
			btn_reject.setVisible(true);
		}
	}
	
	public void onSelect$SumchangeGrid$grd()
	{
		try
		{
			btn_edit.setVisible(true);
			btn_delete2.setVisible(false);
			btn_save2.setVisible(false);
			btn_back2.setVisible(false);
			btn_add2.setVisible(false);
			p4t20a.setReadonly(true);
			p5t20a.setReadonly(true);
			p6t20a.setReadonly(true);
			p7t20a.setReadonly(true);
			p8t20a.setDisabled(true);
			p9t20a.setReadonly(true);
			p10t20a.setReadonly(true);
			sum_div.setVisible(true);
			sum_grd.setVisible(true);
			time_grd.setVisible(false);
			setCurrent();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			alert("onDoubleClick$SumchangeGrid$grd()= " + e.getMessage());
		}
	}
	
	public void onSelect$TimechangeGrid$grd()
	{
		try
		{
			time_grd.setVisible(true);
			btn_edit.setVisible(true);
			btn_delete2.setVisible(false);
			btn_save2.setVisible(false);
			btn_back2.setVisible(false);
			btn_add2.setVisible(false);
			p4t19a.setDisabled(true);
			p2t19a.setDisabled(true);
			p5t19a.setReadonly(true);
			p6t19a.setDisabled(true);
			p7t19a.setReadonly(true);
			p8t19a.setDisabled(true);
			p99t19a.setDisabled(true);
			sum_div.setVisible(true);
			sum_grd.setVisible(false);
			
			if (p5t19a != null)
			{
				p5t19a.setReadonly(true);
			}
			if (p3t19a != null)
			{
				p3t19a.setReadonly(true);
			}
			
			setCurrent();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			alert("onDoubleClick$TimechangeGrid$grd= " + e.getMessage());
		}
	}
	
	public void onClick$gar2()
	{
		onDoubleClick$dataGrid$grd();
		frmgrd.setVisible(false);
		addgrd.setVisible(false);
		fgrd.setVisible(false);
		gar.setVisible(true);
		gar_sum.setVisible(false);
		gar_time.setVisible(true);
		{
			if (gar_time.isVisible())
			{
				InputElement[] list = { p2t19, p3t19 };
				for (int i = 0; i < list.length; i++)
				{
					if (list[i] != null)
					{
						list[i].setDisabled(true);
					}
				}
				
			}
			/*
			 * if (!CheckNull.isEmpty(current.getP10t18())) {
			 * agar_date2_19.setVisible(false); // p11t18.setValue(""); } else {
			 * agar_date2_19.setVisible(true); agar_date1_19.setVisible(false);
			 * }
			 */
		}
		p10t19.setValue((String) session.getAttribute("un"));
		
	}
	
	public void onChange$p4t19a()
	{
		if (CheckNull.isEmpty(p4t19a.getValue()))
		{
			agar_date4_19a.setVisible(false);
			agar_date5_19a.setVisible(true);
		}
		else
		{
			agar_date4_19a.setVisible(true);
			agar_date5_19a.setVisible(false);
		}
	}
	
	public void onChange$p5t19a()
	{
		if (CheckNull.isEmpty(p5t19.getValue()))
		{
			agar_date4_19a.setVisible(true);
			agar_date5_19a.setVisible(false);
		}
		else
		{
			agar_date4_19a.setVisible(false);
			agar_date5_19a.setVisible(true);
		}
	}
	
	public void onClick$btn_add()
	{
		try
		{
			onDoubleClick$dataGrid$grd();
			ap12t18.setValue((String) session.getAttribute("un"));
			ap1t18.setValue(idn);
			frmgrd.setVisible(false);
			addgrd.setVisible(true);
			fgrd.setVisible(false);
			gar_time.setVisible(false);
			gar_sum.setVisible(false);
			tab_garsum.setVisible(false);
			tab_gartime.setVisible(false);
			sum_div.setVisible(false);
			time_grd.setVisible(false);
			sum_grd.setVisible(false);
			verh_div.setVisible(false);
			
			if ((!CheckNull.isEmpty(val2)) && (val2.equals("0")))
			{
				ap9t18.setDisabled(true);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			alert("onClick$btn_add()=" + e.getMessage());
		}
		
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
		try
		{
			onDoubleClick$dataGrid$grd();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		frmgrd.setVisible(false);
		addgrd.setVisible(false);
		fgrd.setVisible(true);
		gar_sum.setVisible(false);
		
	}
	
	public void onClick$btn_save2()
	{
		try
		{
			
			final BankServiceProxy ws = new BankServiceProxy((String) session.getAttribute("YESVO_URL"));
			com.sbs.service.GarantResult gar = new com.sbs.service.GarantResult();
			if (!CheckNull.isEmpty(p4t20.doubleValue()) && (Double.parseDouble(summa1) > p4t20.doubleValue()))
			{
				otn2 = ("0");
			}
			else if (!CheckNull.isEmpty(p4t20.doubleValue()) && (Double.parseDouble(summa1) < p4t20.doubleValue()))
			{
				otn2 = ("1");
				System.out.println("  otn2=" + otn2);
			}
			if (sum_grd.isVisible())
			{
				
				Garantsumchange gars = new Garantsumchange();
				// gars.setP0t20("0");
				gars.setP3t20(currentSum.getP3t20());
				gars.setP4t20(currentSum.getP4t20());
				gars.setP5t20(currentSum.getP5t20());
				gars.setP6t20(currentSum.getP6t20());
				gars.setP7t20(currentSum.getP7t20());
				gars.setP8t20(currentSum.getP8t20());
				gars.setP9t20(currentSum.getP9t20());
				gars.setP10t20(currentSum.getP10t20());
				gars.setP11t20(currentSum.getP11t20());
				gars.setP12t20((String) session.getAttribute("un"));
				gars.setP15t20(otn2);
				GarantSumChangeResult grs = ws.saveGarantSumChange(((String) (session.getAttribute("BankINN"))), idn, current.getP3t18(), getGarSumCorrect(gars));
				if (grs.getStatus() == 0)
				{
					refreshModel(_startPageNumber);
					alert("Сохранение успешно");
					refresh(idn);
					System.out.println("Correct GarSumChange  Status: " + grs.getStatus() + ";  GTKid:" + grs.getGtkId() + ";  Text:" + grs.getErrorMsg());
					
				}
				else
				{
					alert("Ошибка при корректировке суммы гарантии!   Error:" + grs.getStatus() + ";  GTKid:" + grs.getGtkId() + ";  Text:" + grs.getErrorMsg());
					System.out.println("Ошибка при корректировке суммы гарантии!  Status: " + grs.getStatus() + ";  GTKid:" + grs.getGtkId() + ";  Text:" + grs.getErrorMsg());
				}
				
			}
			else if (time_grd.isVisible())
			{
				garanttimechange gart = new garanttimechange();
				// gart.setP0t19("0");
				gart.setP2t19(currentTime.getP2t19());
				gart.setP3t19(currentTime.getP3t19());
				gart.setP4t19(currentTime.getP4t19());
				gart.setP5t19(currentTime.getP5t19());
				gart.setP6t19(currentTime.getP6t19());
				gart.setP7t19(currentTime.getP7t19());
				gart.setP8t19(currentTime.getP8t19());
				gart.setP9t19(currentTime.getP9t19());
				gart.setP10t19((String) session.getAttribute("un"));
				GarantTimeChangeResult grt = ws.saveGarantTimeChange(((String) (session.getAttribute("BankINN"))), idn, current.getP3t18(), getGarTimeCorrect(gart));
				if (grt.getStatus() == 0)
				{
					refreshModel(_startPageNumber);
					alert("Сохранение успешно");
					refresh(idn);
					System.out.println("Correcting GarTimeChange  Status: " + grt.getStatus() + ";  GTKid:" + grt.getGtkId() + ";  Text:" + grt.getErrorMsg());
					
				}
				else
				{
					alert("Ошибка при коректировки даты гарантии!   Error:" + grt.getStatus() + ";  GTKid:" + grt.getGtkId() + ";  Text:" + grt.getErrorMsg());
					System.out.println("Ошибка при коректировки даты гарантии!  Status: " + grt.getStatus() + ";  GTKid:" + grt.getGtkId() + ";  Text:" + grt.getErrorMsg());
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
			alert("ERROR:in correcting  garant" + e.getMessage());
			ISLogger.getLogger().error("ERROR correct garant:" + e.getMessage());
		}
		
	}
	
	public void onClick$btn_delete2()
	{
		try
		{
			
			final BankServiceProxy ws = new BankServiceProxy((String) session.getAttribute("YESVO_URL"));
			com.sbs.service.GarantResult garr = new com.sbs.service.GarantResult();
			
			if (!CheckNull.isEmpty(p4t20.doubleValue()) && (Double.parseDouble(summa1) > p4t20.doubleValue()))
			{
				otn2 = ("0");
			}
			else if (!CheckNull.isEmpty(p4t20.doubleValue()) && (Double.parseDouble(summa1) < p4t20.doubleValue()))
			{
				otn2 = ("1");
				
			}
			// alert("test0");
			
			if (sum_grd.isVisible())
			{
				
				Garantsumchange gars = new Garantsumchange();
				
				gars.setP3t20(currentSum.getP3t20());
				gars.setP4t20(currentSum.getP4t20());
				gars.setP5t20(currentSum.getP5t20());
				gars.setP6t20(currentSum.getP6t20());
				gars.setP7t20(currentSum.getP7t20());
				gars.setP8t20(currentSum.getP8t20());
				gars.setP9t20(currentSum.getP9t20());
				gars.setP10t20(currentSum.getP10t20());
				gars.setP11t20(currentSum.getP11t20());
				gars.setP12t20((String) session.getAttribute("un"));
				gars.setP15t20(otn2);
				GarantSumChangeResult grs = ws.saveGarantSumChange(((String) (session.getAttribute("BankINN"))), idn, current.getP3t18(), getGarSumDelete(gars));
				if (grs.getStatus() == 0)
				{
					refreshModel(_startPageNumber);
					alert("Запрос на удаление передано в ГО");
					System.out.println("Delete GarSumChange  Status: " + grs.getStatus() + ";  GTKid:" + grs.getGtkId() + ";  Text:" + grs.getErrorMsg());
					
				}
				else
				{
					alert("Ошибка при Удалении суммы гарантии!   Error:" + grs.getStatus() + ";  GTKid:" + grs.getGtkId() + ";  Text:" + grs.getErrorMsg());
					System.out.println("Ошибка при Удалении суммы гарантии!  Status: " + grs.getStatus() + ";  GTKid:" + grs.getGtkId() + ";  Text:" + grs.getErrorMsg());
				}
			}
			else if (time_grd.isVisible())
			{
				garanttimechange gart = new garanttimechange();
				// gart.setP0t19("0");
				gart.setP2t19(currentTime.getP2t19());
				gart.setP3t19(currentTime.getP3t19());
				gart.setP4t19(currentTime.getP4t19());
				gart.setP5t19(currentTime.getP5t19());
				gart.setP6t19(currentTime.getP6t19());
				gart.setP7t19(currentTime.getP7t19());
				gart.setP8t19(currentTime.getP8t19());
				gart.setP9t19(currentTime.getP9t19());
				gart.setP10t19((String) session.getAttribute("un"));
				GarantTimeChangeResult grt = ws.saveGarantTimeChange(((String) (session.getAttribute("BankINN"))), idn, current.getP3t18(), getGarTimeDelete(gart));
				if (grt.getStatus() == 0)
				{
					refreshModel(_startPageNumber);
					alert("Запрос на удаление передано в ГО");
					System.out.println("Delete GarTimeChange  Status: " + grt.getStatus() + ";  GTKid:" + grt.getGtkId() + ";  Text:" + grt.getErrorMsg());
					
				}
				else
				{
					alert("Ошибка при удалении даты гарантии!   Error:" + grt.getStatus() + ";  GTKid:" + grt.getGtkId() + ";  Text:" + grt.getErrorMsg());
					System.out.println("Ошибка при удалении даты гарантии!  Status: " + grt.getStatus() + ";  GTKid:" + grt.getGtkId() + ";  Text:" + grt.getErrorMsg());
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
			alert("Error: Ошибка при удалении 2 " + e.getMessage());
			ISLogger.getLogger().error(" in delete garant :" + e.getMessage());
			
		}
		
	}
	
	public void onClick$btn_delete()
	{
		try
		{
			
			final BankServiceProxy ws = new BankServiceProxy((String) session.getAttribute("YESVO_URL"));
			com.sbs.service.GarantResult garr = new com.sbs.service.GarantResult();
			// alert("test0");
			
			if (btn_delete.isVisible())
			{
				
				current.setP0t18(2);
				current.setP1t18(p1t18.getValue());
				current.setP2t18(p2t18.getValue());
				current.setP3t18(p3t18.getValue());
				current.setP4t18(p4t18.getValue());
				current.setP5t18(p5t18.getValue());
				current.setP6t18(p6t18.doubleValue());
				current.setP7t18(p7t182.doubleValue());
				current.setP8t18(p8t18.doubleValue());
				current.setP9t18(p9t18.doubleValue());
				current.setP10t18(p10t18.getValue());
				current.setP11t18(p11t18.getValue());
				current.setP12t18((String) session.getAttribute("un"));
				current.setP15t18(otn);
				setCurrent();
				
				// System.out.println("session.getAttribute(ufn)="+session.getAttribute("ufn")+"session.getAttribute(un)="+p10t21.getAttribute("un").toString());
				
				GarantResult ga = ws.saveGarant(((String) (session.getAttribute("BankINN"))), p1t18.getValue(), delGar(current));
				if (ga.getStatus() == 0)
				{
					refreshModel(_startPageNumber);
					alert("Удален успешно");
					refresh(idn);
					
				}
				else
				{
					alert("Error: Ошибка при удалении 1 " + ga.getStatus() + "; GTKid:" + ga.getGtkId() + "; Text:" + ga.getErrorMsg());
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
			alert("Error: Ошибка при удалении 2 " + e.getMessage());
			ISLogger.getLogger().error(" in delete garant :" + e.getMessage());
			
		}
		
	}
	
	public void onClick$btn_save()
	{
		try
		{
			
			final BankServiceProxy ws = new BankServiceProxy((String) session.getAttribute("YESVO_URL"));
			com.sbs.service.GarantResult gar = new com.sbs.service.GarantResult();
			
			if (addgrd.isVisible())
			{
				
				GarantResult gr = ws.saveGarant(((String) (session.getAttribute("BankINN"))), idn, getGar1(new Garant(

				ap2t18.getValue(),
						ap3t18.getValue(),
						ap4t18.getValue(),
						ap5t18.getValue(),
						ap6t18.doubleValue(),
						ap7t182.doubleValue(),
						ap8t18.doubleValue(),
						ap9t18.doubleValue(),
						ap10t18.getValue(),
						ap11t18.getValue(),
						ap12t18.getValue()
						)));
				
				CheckNull.clearForm(addgrd);
				if (gr.getStatus() == 0)
				{
					refreshModel(_startPageNumber);
					alert("Сохранение успешно");
					refresh(idn);
					
				}
				else
				{
					alert("Error save New garant; Status:" + gr.getStatus() + "; GTKid:" + gr.getGtkId() + "; Text:" + gr.getErrorMsg());
					ISLogger.getLogger().error(" in save New garant:" + gr.getStatus() + "; GTKid:" + gr.getGtkId() + "; Text:" + gr.getErrorMsg());
					
					frmgrd.setVisible(true);
					addgrd.setVisible(false);
					fgrd.setVisible(false);
				}
				
			}
			else if (gar_sum.isVisible())
			{
				if (!CheckNull.isEmpty(p4t20.doubleValue()) && (Double.parseDouble(summa1) > p4t20.doubleValue()))
				{
					otn2 = ("0");
				}
				else if (!CheckNull.isEmpty(p4t20.doubleValue()) && (Double.parseDouble(summa1) < p4t20.doubleValue()))
				{
					otn2 = ("1");
					System.out.println("  otn2=" + otn2);
				}
				Garantsumchange gars = new Garantsumchange();
				gars.setP0t20("0");
				gars.setP3t20(p6t18.doubleValue());
				gars.setP4t20(p4t20.doubleValue());
				gars.setP5t20(ap7t182.doubleValue());
				gars.setP6t20(p6t20.doubleValue());
				gars.setP7t20(p7t20.doubleValue());
				gars.setP8t20(p8t20.getValue());
				gars.setP9t20(p9t20.getValue());
				gars.setP10t20(p10t20.getValue());
				gars.setP12t20((String) session.getAttribute("un"));
				gars.setP15t20(otn2);
				GarantSumChangeResult grs = ws.saveGarantSumChange(((String) (session.getAttribute("BankINN"))), idn, current.getP3t18(), getGarSum(gars));
				if (grs.getStatus() == 0)
				{
					refreshModel(_startPageNumber);
					alert("Сохранение успешно");
					refresh(idn);
					// System.out.println("Save GarSumChange  Status: "+grs.getStatus()+";  GTKid:"
					// +grs.getGtkId()+ ";  Text:" +grs.getErrorMsg());
					
				}
				else
				{
					alert("Ошибка при сохранении суммы гарантии!   Error:" + grs.getStatus() + ";  GTKid:" + grs.getGtkId() + ";  Text:" + grs.getErrorMsg());
					System.out.println("Ошибка при сохранении суммы гарантии!  Status: " + grs.getStatus() + ";  GTKid:" + grs.getGtkId() + ";  Text:" + grs.getErrorMsg());
				}
				
			}
			else if (gar_time.isVisible())
			{
				garanttimechange gart = new garanttimechange();
				gart.setP0t19("0");
				gart.setP2t19(current.getP10t18());
				gart.setP3t19(current.getP11t18());
				gart.setP4t19(p4t19.getValue());
				gart.setP5t19(p5t19.getValue());
				gart.setP6t19(p6t19.getValue());
				gart.setP7t19(p7t19.getValue());
				gart.setP8t19(p8t19.getValue());
				gart.setP10t19((String) session.getAttribute("un"));
				GarantTimeChangeResult grt = ws.saveGarantTimeChange(((String) (session.getAttribute("BankINN"))), idn, current.getP3t18(), getGarTime(gart));
				if (grt.getStatus() == 0)
				{
					refreshModel(_startPageNumber);
					alert("Сохранение успешно");
					refresh(idn);
					// System.out.println("Save GarTimeChange  Status: "+grt.getStatus()+";  GTKid:"
					// +grt.getGtkId()+ ";  Text:" +grt.getErrorMsg());
					
				}
				else
				{
					alert("Ошибка при сохранении даты гарантии!   Error:" + grt.getStatus() + ";  GTKid:" + grt.getGtkId() + ";  Text:" + grt.getErrorMsg());
					System.out.println("Ошибка при сохранении даты гарантии!  Status: " + grt.getStatus() + ";  GTKid:" + grt.getGtkId() + ";  Text:" + grt.getErrorMsg());
				}
				
			}
			else if (fgrd.isVisible())
			{
				filter = new GarantFilter();
				if (!CheckNull.isEmpty(fid.getValue()))
				{
					filter.setId(Long.parseLong(fid.getValue()));
				}
				
				if (!CheckNull.isEmpty(fp0t18.getValue()))
				{
					filter.setP0t18(fp0t18.getValue());
				}
				
				/*
				 * if (!CheckNull.isEmpty(fp1t18.getValue())) {
				 * filter.setP1t18(fp1t18.getValue()); } if
				 * (!CheckNull.isEmpty(fp2t18.getValue())) {
				 * filter.setP2t18(fp2t18.getValue()); } if
				 * (!CheckNull.isEmpty(fp3t18.getValue())) {
				 * filter.setP3t18(Integer.parseInt(fp3t18.getValue())); } if
				 * (!CheckNull.isEmpty(fp4t18.getValue())) {
				 * filter.setP4t18(fp4t18.getValue()); } if
				 * (!CheckNull.isEmpty(fp5t18.getValue())) {
				 * filter.setP5t18(fp5t18.getValue()); } if
				 * (!CheckNull.isEmpty(fp6t18.doubleValue())) {
				 * filter.setP6t18(fp6t18.doubleValue()); } if
				 * (!CheckNull.isEmpty(fp7t18.doubleValue())) {
				 * filter.setP7t18(fp7t18.doubleValue()); } if
				 * (!CheckNull.isEmpty(fp8t18.doubleValue())) {
				 * filter.setP8t18(fp8t18.doubleValue()); } if
				 * (!CheckNull.isEmpty(fp9t18.doubleValue())) {
				 * filter.setP9t18(fp9t18.doubleValue()); } if
				 * (!CheckNull.isEmpty(fp10t18.getValue())) {
				 * filter.setP10t18(fp10t18.getValue()); } if
				 * (!CheckNull.isEmpty(fp11t18.getValue())) {
				 * filter.setP11t18(fp11t18.getValue()); }
				 */
			}
			else
			{
				
				// Long.parseLong(id.getValue());
				current.setP0t18(1);
				current.setP1t18(p1t18.getValue());
				current.setP2t18(p2t18.getValue());
				current.setP3t18(p3t18.getValue());
				current.setP4t18(p4t18.getValue());
				current.setP5t18(p5t18.getValue());
				current.setP6t18(p6t18.doubleValue());
				current.setP7t18(p7t182.doubleValue());
				current.setP8t18(p8t18.doubleValue());
				current.setP9t18(p9t18.doubleValue());
				current.setP10t18(p10t18.getValue());
				current.setP11t18(p11t18.getValue());
				current.setP12t18((String) session.getAttribute("un"));
				current.setP15t18(otn);
				// GarantService.update(current);
				setCurrent();
				alert("otn=" + otn);
				GarantResult ga = ws.saveGarant(((String) (session.getAttribute("BankINN"))), p1t18.getValue(), getGar(current));
				if (ga.getStatus() == 0)
				{
					refreshModel(_startPageNumber);
					alert("Сохранение успешно");
					
				}
				else
				{
					alert("Error:" + ga.getStatus() + "; GTKid:" + ga.getGtkId() + "; Text:" + ga.getErrorMsg());
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
			alert("ERROR:in save " + e.getMessage());
			ISLogger.getLogger().error("ERROR Save garant:" + e.getMessage());
		}
		
	}
	
	public void onClick$btn_cancel()
	{
		if (fgrd.isVisible())
		{
			filter = new GarantFilter();
		}
		onClick$btn_back();
		frmgrd.setVisible(true);
		addgrd.setVisible(false);
		fgrd.setVisible(false);
		CheckNull.clearForm(addgrd);
		CheckNull.clearForm(fgrd);
		refreshModel(_startPageNumber);
	}
	
	private com.sbs.service.Garant getGar(Garant gar) throws Exception
	{
		java.util.Calendar cal = java.util.Calendar.getInstance();
		java.util.Calendar cal2 = java.util.Calendar.getInstance();
		com.sbs.service.Garant res = new com.sbs.service.Garant();
		res.setP0T18(gar.getP0t18());
		res.setP2T18(gar.getP2t18());
		res.setP3T18(gar.getP3t18());
		cal.setTime(df.parse(df.format(gar.getP4t18())));
		res.setP4T18(cal);
		res.setP5T18(gar.getP5t18());
		res.setP6T18(gar.getP6t18());
		res.setP7T18(gar.getP7t18());
		res.setP8T18(gar.getP8t18());
		res.setP9T18(gar.getP9t18());
		cal2.setTime(df.parse(df.format(gar.getP10t18())));
		res.setP10T18(cal2);
		if (CheckNull.isEmpty(gar.getP11t18()))
		{
			res.setP11T18(null);
		}
		else
		{
			res.setP11T18(Integer.parseInt(gar.getP11t18()));
		}
		res.setP12T18((String) session.getAttribute("un"));
		res.setP15T18(Short.parseShort(otn));
		return res;
	}
	
	private com.sbs.service.Garant getGar1(Garant gar) throws Exception
	{
		java.util.Calendar cal = java.util.Calendar.getInstance();
		java.util.Calendar cal2 = java.util.Calendar.getInstance();
		com.sbs.service.Garant res = new com.sbs.service.Garant();
		
		res.setP0T18(0);
		res.setP2T18(gar.getP2t18());
		res.setP3T18(gar.getP3t18());
		cal.setTime(df.parse(df.format(gar.getP4t18())));
		res.setP4T18(cal);
		res.setP5T18(gar.getP5t18());
		res.setP6T18(gar.getP6t18());
		res.setP7T18(gar.getP7t18());
		res.setP8T18(gar.getP8t18());
		res.setP9T18(gar.getP9t18());
		cal2.setTime(df.parse(df.format((CheckNull.isEmpty(gar.getP10t18()) ? null : gar.getP10t18()))));
		res.setP10T18(cal2);
		if (CheckNull.isEmpty(gar.getP11t18()))
		{
			res.setP11T18(null);
		}
		else
		{
			res.setP11T18(Integer.parseInt(gar.getP11t18()));
		}
		res.setP12T18((String) session.getAttribute("un"));
		res.setP15T18(Short.parseShort(aotn));
		
		// res.setP15T18(Short.parseShort(gar.getP15t18()));
		// alert("res.getP15T18() ="+res.getP15T18());
		return res;
		
	}
	
	private com.sbs.service.GarantSumChange getGarSum(Garantsumchange garSum)
	{
		java.util.Calendar cal = java.util.Calendar.getInstance();
		com.sbs.service.GarantSumChange res = new com.sbs.service.GarantSumChange();
		
		res.setP0T20(0);
		res.setP3T20(garSum.getP3t20());
		res.setP4T20(garSum.getP4t20());
		res.setP5T20(garSum.getP5t20());
		res.setP6T20(garSum.getP6t20());
		res.setP7T20(garSum.getP7t20());
		res.setP8T20(Short.parseShort(garSum.getP8t20()));
		if (!CheckNull.isEmpty(garSum.getP9t20()))
		{
			res.setP9T20(garSum.getP9t20());
		}
		if (!CheckNull.isEmpty(garSum.getP10t20()))
		{
			res.setP10T20(garSum.getP10t20());
		}
		res.setP12T20((String) session.getAttribute("un"));
		res.setP15T20(Short.parseShort(aotn));
		return res;
	}
	
	private com.sbs.service.GarantSumChange getGarSumCorrect(Garantsumchange garSum)
	{
		java.util.Calendar cal = java.util.Calendar.getInstance();
		com.sbs.service.GarantSumChange res = new com.sbs.service.GarantSumChange();
		
		res.setP0T20(1);
		res.setP3T20(garSum.getP3t20());
		res.setP4T20(garSum.getP4t20());
		res.setP5T20(garSum.getP5t20());
		res.setP6T20(garSum.getP6t20());
		res.setP7T20(garSum.getP7t20());
		res.setP8T20(Short.parseShort(garSum.getP8t20()));
		if (!CheckNull.isEmpty(garSum.getP9t20()))
		{
			res.setP9T20(garSum.getP9t20());
		}
		if (!CheckNull.isEmpty(garSum.getP10t20()))
		{
			res.setP10T20(garSum.getP10t20());
		}
		res.setP11T20(garSum.getP11t20());
		res.setP12T20((String) session.getAttribute("un"));
		res.setP15T20(Short.parseShort(otn2));
		return res;
	}
	
	private com.sbs.service.GarantSumChange getGarSumDelete(Garantsumchange garSum)
	{
		java.util.Calendar cal = java.util.Calendar.getInstance();
		com.sbs.service.GarantSumChange res = new com.sbs.service.GarantSumChange();
		
		res.setP0T20(2);
		res.setP3T20(garSum.getP3t20());
		res.setP4T20(garSum.getP4t20());
		res.setP5T20(garSum.getP5t20());
		res.setP6T20(garSum.getP6t20());
		res.setP7T20(garSum.getP7t20());
		res.setP8T20(Short.parseShort(garSum.getP8t20()));
		if (!CheckNull.isEmpty(garSum.getP9t20()))
		{
			res.setP9T20(garSum.getP9t20());
		}
		if (!CheckNull.isEmpty(garSum.getP10t20()))
		{
			res.setP10T20(garSum.getP10t20());
		}
		res.setP11T20(garSum.getP11t20());
		res.setP12T20((String) session.getAttribute("un"));
		res.setP15T20(Short.parseShort(otn2));
		return res;
	}
	
	private com.sbs.service.GarantTimeChange getGarTimeCorrect(garanttimechange garTime) throws Exception
	{
		java.util.Calendar cal = java.util.Calendar.getInstance();
		java.util.Calendar cal2 = java.util.Calendar.getInstance();
		com.sbs.service.GarantTimeChange res = new com.sbs.service.GarantTimeChange();
		res.setP0T19(1);
		if (!CheckNull.isEmpty(garTime.getP2t19()))
		{
			cal.setTime(df.parse(df.format(garTime.getP2t19())));
			res.setP2T19(cal);
		}
		if (!CheckNull.isEmpty(garTime.getP3t19()))
		{
			res.setP3T19(Integer.parseInt(garTime.getP3t19()));
		}
		cal2.setTime(df.parse(df.format(garTime.getP4t19())));
		res.setP4T19(cal2);
		if (!CheckNull.isEmpty(garTime.getP5t19()))
		{
			res.setP5T19(Integer.parseInt(garTime.getP5t19()));
		}
		res.setP6T19(Short.parseShort(garTime.getP6t19()));
		if (!CheckNull.isEmpty(garTime.getP7t19()))
		{
			res.setP7T19(garTime.getP7t19());
		}
		if (!CheckNull.isEmpty(garTime.getP8t19()))
		{
			res.setP8T19(garTime.getP8t19());
		}
		res.setP9T19(garTime.getP9t19());
		res.setP10T19((String) session.getAttribute("un"));
		return res;
	}
	
	private com.sbs.service.GarantTimeChange getGarTimeDelete(garanttimechange garTime) throws Exception
	{
		java.util.Calendar cal = java.util.Calendar.getInstance();
		java.util.Calendar cal2 = java.util.Calendar.getInstance();
		com.sbs.service.GarantTimeChange res = new com.sbs.service.GarantTimeChange();
		res.setP0T19(2);
		if (!CheckNull.isEmpty(garTime.getP2t19()))
		{
			cal2.setTime(df.parse(df.format(garTime.getP2t19())));
			res.setP2T19(cal2);
		}
		if (!CheckNull.isEmpty(garTime.getP3t19()))
		{
			res.setP3T19(Integer.parseInt(garTime.getP3t19()));
		}
		cal.setTime(df.parse(df.format(garTime.getP4t19())));
		res.setP4T19(cal);
		if (!CheckNull.isEmpty(garTime.getP5t19()))
		{
			res.setP5T19(Integer.parseInt(garTime.getP5t19()));
		}
		res.setP6T19(Short.parseShort(garTime.getP6t19()));
		if (!CheckNull.isEmpty(garTime.getP7t19()))
		{
			res.setP7T19(garTime.getP7t19());
		}
		if (!CheckNull.isEmpty(garTime.getP8t19()))
		{
			res.setP8T19(garTime.getP8t19());
		}
		res.setP9T19(garTime.getP9t19());
		res.setP10T19((String) session.getAttribute("un"));
		return res;
	}
	
	private com.sbs.service.GarantTimeChange getGarTime(garanttimechange garTime) throws Exception
	{
		java.util.Calendar cal = java.util.Calendar.getInstance();
		java.util.Calendar cal2 = java.util.Calendar.getInstance();
		com.sbs.service.GarantTimeChange res = new com.sbs.service.GarantTimeChange();
		res.setP0T19(0);
		if (!CheckNull.isEmpty(garTime.getP2t19()))
		{
			cal.setTime(df.parse(df.format(garTime.getP2t19())));
			res.setP2T19(cal);
		}
		if (!CheckNull.isEmpty(garTime.getP3t19()))
		{
			res.setP3T19(Integer.parseInt(garTime.getP3t19()));
		}
		cal2.setTime(df.parse(df.format(garTime.getP4t19())));
		res.setP4T19(cal2);
		if (!CheckNull.isEmpty(garTime.getP5t19()))
		{
			res.setP5T19(Integer.parseInt(garTime.getP5t19()));
		}
		res.setP6T19(Short.parseShort(garTime.getP6t19()));
		if (!CheckNull.isEmpty(garTime.getP7t19()))
		{
			res.setP7T19(garTime.getP7t19());
		}
		if (!CheckNull.isEmpty(garTime.getP8t19()))
		{
			res.setP8T19(garTime.getP8t19());
		}
		res.setP10T19((String) session.getAttribute("un"));
		return res;
	}
	
	private com.sbs.service.Garant delGar(Garant gar) throws Exception
	{
		java.util.Calendar cal = java.util.Calendar.getInstance();
		java.util.Calendar cal2 = java.util.Calendar.getInstance();
		com.sbs.service.Garant res = new com.sbs.service.Garant();
		
		res.setP0T18(2);
		res.setP2T18(gar.getP2t18());
		res.setP3T18(gar.getP3t18());
		cal.setTime(df.parse(df.format(gar.getP4t18())));
		res.setP4T18(cal);
		res.setP5T18(gar.getP5t18());
		res.setP6T18(gar.getP6t18());
		res.setP7T18(gar.getP7t18());
		res.setP8T18(gar.getP8t18());
		res.setP9T18(gar.getP9t18());
		cal2.setTime(df.parse(df.format((CheckNull.isEmpty(gar.getP10t18()) ? null : gar.getP10t18()))));
		res.setP10T18(cal2);
		if (CheckNull.isEmpty(gar.getP11t18()))
		{
			res.setP11T18(null);
		}
		else
		{
			res.setP11T18(Integer.parseInt(gar.getP11t18()));
		}
		res.setP12T18(gar.getP12t18());
		res.setP15T18(Short.parseShort(otn));
		return res;
		
	}
	
	// ///////////////////////////////////////////////////////////////////////////
	private void setCurrent()
	{
		if (current != null)
		{
			// System.out.println(val1+" = "+val2);
			currenciesg = com.is.tf.contract.ContractService.getMyCurrG(idn, df.format(current.getP4t18()), alias);
			p7t181.setModel((new ListModelList(currenciesg)));
			ap7t181.setModel((new ListModelList(currenciesg)));
			p7t183.setModel((new ListModelList(currenciesg)));
			ap7t183.setModel((new ListModelList(currenciesg)));
			p5t201.setModel((new ListModelList(currenciesg)));
			p5t203.setModel((new ListModelList(currenciesg)));
			if (currenciesg.size() > 0)
			{
				if (CheckNull.isEmpty(val2)
						|| (val1.equalsIgnoreCase("000") ? "860" : val1).equalsIgnoreCase(val2.equalsIgnoreCase("000") ? "860" : val2))
				{
					// ||
					// current.getP5t18().equalsIgnoreCase(currenciesg.get(0).getKod())
					kur.setVisible(false);
				}
				else
				{
					kur.setVisible(true);
					// p7t183.setSelecteditem(current.getP5t18());
					// onSelect$p5t18();
				}
			}
			if (CheckNull.isEmpty(val2))
			{
				sum_gar2.setVisible(false);
			}
			else
			{
				sum_gar2.setVisible(true);
			}
			
			if (Double.parseDouble(summa1) > current.getP6t18())
			{
				otn = "0";
			}
			else if (Double.parseDouble(summa1) < current.getP6t18())
			{
				otn = "1";
			}
			if (!CheckNull.isEmpty(current.getP10t18()))
			{
				gar_date2.setVisible(false);
				current.setP11t18(null);
			}
			else
			{
				gar_date2.setVisible(true);
			}
			if (!CheckNull.isEmpty(current.getP11t18()))
			{
				gar_date1.setVisible(false);
				current.setP10t18(null);
			}
			else
			{
				gar_date1.setVisible(true);
			}
		}
		SumchangeGrid.setModel(new BindingListModelList(current.getSumchanges(), true));
		TimechangeGrid.setModel(new BindingListModelList(current.getTimechanges(), true));
		if (!CheckNull.isEmpty(currentSum.getP8t20()))
		{
			p8t20a.setValue(currentSum.getP8t20());
		}
		if ((!CheckNull.isEmpty(currentSum.getP8t20()) || (p8t20a.getValue().equals("5"))))
		{
			row_p9t20a.setVisible(false);
			row_p10t20a.setVisible(true);
		}
		else if ((!CheckNull.isEmpty(currentSum.getP8t20()) || (p8t20a.getValue().equals("4"))))
		{
			row_p9t20a.setVisible(true);
			row_p10t20a.setVisible(false);
		}
		if ((CheckNull.isEmpty(currentSum.getP7t20())) || (currentSum.getP7t20().equals(0.0)))
		{
			row_p7t20a.setVisible(false);
		}
		else
		{
			row_p7t20a.setVisible(true);
		}
		
		if ((CheckNull.isEmpty(currentTime.getP3t19())) || (currentTime.getP3t19().equals(0.0)))
		{
			list_p3t19a.setWidth("0px");
		}
		else
		{
			list_p3t19a.setWidth("100px");
		}
		if ((CheckNull.isEmpty(currentTime.getP5t19())) || (currentTime.getP5t19().equals(0.0)))
		{
			list_p5t19a.setWidth("0px");
		}
		else
		{
			list_p5t19a.setWidth("100px");
		}
		if (!CheckNull.isEmpty(currentTime.getP6t19()))
		{
			p6t19a.setValue(currentTime.getP6t19());
		}
		if ((!CheckNull.isEmpty(currentTime.getP6t19()) || (p6t19a.getValue().equals("5"))))
		{
			row_p7t19a.setVisible(false);
			row_p8t19a.setVisible(true);
		}
		else if ((!CheckNull.isEmpty(currentTime.getP6t19()) || (p6t19a.getValue().equals("4"))))
		{
			row_p7t19a.setVisible(true);
			row_p8t19a.setVisible(false);
		}
		
		if (CheckNull.isEmpty(currentTime.getP4t19()))
		{
			agar_date4_19a.setVisible(false);
			// agar_date5_19a.setVisible(true);
		}
		else
		{
			agar_date4_19a.setVisible(true);
		}
		
		if (CheckNull.isEmpty(currentTime.getP5t19()))
		{
			// agar_date4_19a.setVisible(true);
			agar_date5_19a.setVisible(false);
		}
		else
		{
			agar_date5_19a.setVisible(true);
		}
		
		/*
		 * if (!CheckNull.isEmpty(currentTime.getP2t19())){
		 * list_p2t19a.setWidth("100px"); } else { list_p2t19a.setWidth("0px");
		 * } if (!CheckNull.isEmpty(currentTime.getP4t19())){
		 * list_p4t19a.setWidth("100px"); } else { list_p4t19a.setWidth("0px");
		 * }
		 */

	}
	
	public void onBlur$p10t18()
	{
		onChange$p10t18();
	}
	
	public void onOk$p10t18()
	{
		onChange$p10t18();
	}
	
	public void onChange$p10t18()
	{
		if (!CheckNull.isEmpty(current.getP10t18()))
		{
			gar_date2.setVisible(false);
			p11t18.setValue("");
		}
		else
		{
			gar_date2.setVisible(true);
			
		}
	}
	
	public void onInitRenderLater$p7t183()
	{
		p7t183.setSelectedIndex(currenciesg.size() - 1);
		p5t201.setSelecteditem(p7t181.getValue());
		p5t203.setSelecteditem(p7t183.getValue());
		countCourse(false);
	}
	
	public void onChange$p6t18()
	{
		countCourse(false);
	}
	
	public void onChange$p8t18()
	{
		countCourse(false);
	}
	
	public void onChange$p9t18()
	{
		countCourse(false);
	}
	
	public void onSelect$p5t18()
	{
		countCourse(true);
	}
	
	public void onSelect$p7t181()
	{
		countCourse(true);
	}
	
	public void onClick$btn_recount()
	{
		countCourse(true);
	}
	
	private void countCourse(Boolean setp7t182)
	{
		try
		{
			if (!CheckNull.isEmpty(p7t183.getValue()) && !CheckNull.isEmpty(p7t181.getValue()))
			{
				System.out.println("***" + p7t181.getValue() + " - " + p7t183.getValue());
				if (setp7t182)
				{
					p7t182.setValue("" + (p7t181.getCourse() / p7t183.getCourse()));
					current.setP7t18((p7t181.getCourse() / p7t183.getCourse()));
				}
				cbcourse.setValue("По курсу ЦБ: " + (p7t181.getCourse() / p7t183.getCourse()));
				Boolean bool = false;
				Double db = null;
				if (p5t18.getValue().equalsIgnoreCase(p7t181.getValue()))
				{
					if (val1.equalsIgnoreCase(p7t181.getValue()))
					{
						db = (p8t18.doubleValue() + (p9t18.doubleValue() / p7t182.doubleValue()));
						bool = (p6t18.doubleValue() == db);
						checksum.setChecked(bool);
						checksum.setLabel((bool ? "Сумма гарантии полность соответствует указанному курсу!" : "Сумма гарантии не соответствует указанному курсу!") + "(" + db + ")");
					}
					else if (val2.equalsIgnoreCase(p7t181.getValue()))
					{
						db = (p9t18.doubleValue() + (p8t18.doubleValue() / p7t182.doubleValue()));
						bool = (p6t18.doubleValue() == db);
						checksum.setChecked(bool);
						checksum.setLabel((bool ? "Сумма гарантии полность соответствует указанному курсу!" : "Сумма гарантии не соответствует указанному курсу!") + "(" + db + ")");
					}
					else
					{
						checksum.setChecked(false);
						checksum.setLabel(("Сумма гарантии не соответствует указанному курсу!"));
					}
				}
				else if (p5t18.getValue().equalsIgnoreCase(p7t183.getValue()))
				{
					if (val1.equalsIgnoreCase(p7t183.getValue()))
					{
						db = (p8t18.doubleValue() + (p9t18.doubleValue() * p7t182.doubleValue()));
						bool = (p6t18.doubleValue() == db);
						checksum.setChecked(bool);
						checksum.setLabel((bool ? "Сумма гарантии полность соответствует указанному курсу!" : "Сумма гарантии не соответствует указанному курсу!") + "(" + db + ")");
					}
					else if (val2.equalsIgnoreCase(p7t183.getValue()))
					{
						db = (p9t18.doubleValue() + (p8t18.doubleValue() * p7t182.doubleValue()));
						bool = (p6t18.doubleValue() == db);
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
	
	private void setCurrentAdd()
	{
		CheckNull.clearForm(addgrd);
		/*
		 * current = new Garant(); current.setP0t18(0); current.setP1t18(idn);
		 * current.setP4t18(new java.util.Date()); //current.setP6t18(new
		 * Double("0.00")); //current.setP8t18(new Double("0.00"));
		 * //current.setP9t18(new Double("0.00")); current.setP10t18(null);
		 * //System.out.println(val1+" = "+val2);
		 */
		currenciesg = com.is.tf.contract.ContractService.getMyCurrG(idn, df.format(new java.util.Date()), alias);
		ap7t181.setModel((new ListModelList(currenciesg)));
		ap7t183.setModel((new ListModelList(currenciesg)));
		if (currenciesg.size() > 0)
		{
			if (CheckNull.isEmpty(val2)
					|| (val1.equalsIgnoreCase("000") ? "860" : val1).equalsIgnoreCase(val2.equalsIgnoreCase("000") ? "860" : val2))
			{
				// ||
				// current.getP5t18().equalsIgnoreCase(currenciesg.get(0).getKod())
				akur.setVisible(false);
			}
			else
			{
				akur.setVisible(true);
				// p7t183.setSelecteditem(current.getP5t18());
				// onSelect$p5t18();
			}
		}
		if (CheckNull.isEmpty(val2))
		{
			asum_gar2.setVisible(false);
		}
		else
		{
			asum_gar2.setVisible(true);
		}
		if (!CheckNull.isEmpty(current.getP10t18()))
		{
			agar_date2.setVisible(false);
		}
		else
		{
			agar_date2.setVisible(true);
		}
		if (Double.parseDouble(summa1) > ap6t18.doubleValue())
		{
			aotn = "0";
		}
		else if (Double.parseDouble(summa1) < ap6t18.doubleValue())
		{
			aotn = "1";
		}
		
	}
	
	public void onBlur$ap10t18()
	{
		onChange$ap10t18();
	}
	
	public void onOk$ap10t18()
	{
		onChange$ap10t18();
	}
	
	public void onChange$ap10t18()
	{
		if (!CheckNull.isEmpty(ap10t18.getValue()))
		{
			agar_date2.setVisible(false);
			ap11t18.setValue(null);
		}
		else
		{
			agar_date2.setVisible(true);
		}
	}
	
	public void onChange$ap11t18()
	{
		if (!CheckNull.isEmpty(ap11t18.getValue()))
		{
			agar_date1.setVisible(false);
			ap10t18.setValue(null);
		}
		else
		{
			agar_date1.setVisible(true);
		}
	}
	
	public void onInitRenderLater$ap7t183()
	{
		ap7t183.setSelectedIndex(currenciesg.size() - 1);
		acountCourse(false);
	}
	
	public void onChange$ap6t18()
	{
		acountCourse(false);
		
		if (Double.parseDouble(summa1) > ap6t18.doubleValue())
		{
			aotn = ("0");
		}
		else if (Double.parseDouble(summa1) < ap6t18.doubleValue())
		{
			aotn = ("1");
		}
		// alert("summa1="+summa1+"  aotn="+aotn+"   ap6t18.doubleValue()="+ap6t18.doubleValue());
	}
	
	public void onChange$ap8t18()
	{
		acountCourse(false);
	}
	
	public void onChange$ap9t18()
	{
		acountCourse(false);
	}
	
	public void onSelect$ap5t18()
	{
		acountCourse(true);
	}
	
	public void onSelect$ap7t181()
	{
		acountCourse(true);
	}
	
	public void onClick$abtn_recount()
	{
		acountCourse(true);
	}
	
	private void acountCourse(Boolean setap7t182)
	{
		try
		{
			if (!CheckNull.isEmpty(ap7t183.getValue()) && !CheckNull.isEmpty(ap7t181.getValue()))
			{
				System.out.println("***" + ap7t181.getValue() + " - " + ap7t183.getValue());
				if (setap7t182)
				{
					ap7t182.setValue("" + (ap7t181.getCourse() / ap7t183.getCourse()));
					current.setP7t18((ap7t181.getCourse() / ap7t183.getCourse()));
				}
				acbcourse.setValue("По курсу ЦБ: " + (ap7t181.getCourse() / ap7t183.getCourse()));
				Boolean bool = false;
				Double db = null;
				if (ap5t18.getValue().equalsIgnoreCase(ap7t181.getValue()))
				{
					if (val1.equalsIgnoreCase(ap7t181.getValue()))
					{
						db = (ap8t18.doubleValue() + (ap9t18.doubleValue() / ap7t182.doubleValue()));
						bool = (ap6t18.doubleValue() == db);
						achecksum.setChecked(bool);
						achecksum.setLabel((bool ? "Сумма гарантии полность соответствует указанному курсу!" : "Сумма гарантии не соответствует указанному курсу!") + "(" + db + ")");
					}
					else if (val2.equalsIgnoreCase(ap7t181.getValue()))
					{
						db = (ap9t18.doubleValue() + (ap8t18.doubleValue() / ap7t182.doubleValue()));
						bool = (ap6t18.doubleValue() == db);
						achecksum.setChecked(bool);
						achecksum.setLabel((bool ? "Сумма гарантии полность соответствует указанному курсу!" : "Сумма гарантии не соответствует указанному курсу!") + "(" + db + ")");
					}
					else
					{
						achecksum.setChecked(false);
						achecksum.setLabel(("Сумма гарантии не соответствует указанному курсу!"));
					}
				}
				else if (ap5t18.getValue().equalsIgnoreCase(ap7t183.getValue()))
				{
					if (val1.equalsIgnoreCase(ap7t183.getValue()))
					{
						db = (ap8t18.doubleValue() + (ap9t18.doubleValue() * ap7t182.doubleValue()));
						bool = (ap6t18.doubleValue() == db);
						achecksum.setChecked(bool);
						achecksum.setLabel((bool ? "Сумма гарантии полность соответствует указанному курсу!" : "Сумма гарантии не соответствует указанному курсу!") + "(" + db + ")");
					}
					else if (val2.equalsIgnoreCase(ap7t183.getValue()))
					{
						db = (ap9t18.doubleValue() + (ap8t18.doubleValue() * ap7t182.doubleValue()));
						bool = (ap6t18.doubleValue() == db);
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
	
	public void onBlur$ap10t19()
	{
		onChange$ap10t18();
	}
	
	public void onOk$ap10t19()
	{
		onChange$ap10t18();
	}
	
	public void onChange$p4t19()
	{
		if (!CheckNull.isEmpty(p4t19.getValue()))
		{
			agar_date4_19.setVisible(false);
		}
		else
		{
			agar_date4_19.setVisible(true);
		}
	}
	
	public void onChange$p5t19()
	{
		if (!CheckNull.isEmpty(p5t19.getValue()))
		{
			agar_date3_19.setVisible(false);
		}
		else
		{
			agar_date3_19.setVisible(true);
		}
	}
	
	public void onSelect$p6t19()
	{
		if (p6t19.getValue().equalsIgnoreCase("4"))
		{
			row_p7t19.setVisible(true);
			row_p8t19.setVisible(false);
		}
		else
		{
			row_p7t19.setVisible(false);
			row_p8t19.setVisible(true);
		}
		p7t19.setSelectedIndex(-1);
		p8t19.setValue("");
	}
	
	public void onSelect$p8t20()
	{
		if (p8t20.getValue().equalsIgnoreCase("4"))
		{
			row_p9t20.setVisible(true);
			row_p10t20.setVisible(false);
		}
		else
		{
			row_p9t20.setVisible(false);
			row_p10t20.setVisible(true);
		}
		p9t20.setSelectedIndex(-1);
		p10t20.setValue("");
	}
	
	public void refresh(String idn) throws Exception
	{
		final BankServiceProxy ws = new BankServiceProxy((String) session.getAttribute("YESVO_URL"));
		com.is.tf.garant.GarantService.remove(new Garant(), id_contract);
		com.sbs.service.GarantsResult gar1 = ws.getGarants(((String) session.getAttribute("BankINN")), String.valueOf(id_contract));
		XMLSerializer.write(gar1, "c:/gar1.xml");
		
		ArrayList<com.sbs.service.Garant> Garants = new ArrayList<com.sbs.service.Garant>();
		
		try
		{
			if (gar1.getStatus() == 0)
			{
				for (int i = 0; i < gar1.getGarants().length; i++)
				{
					Garants.add(gar1.getGarants()[i]);
				}
				com.is.tf.garant.GarantService.create(Garants, idn, id_contract);
			}
			else
			{
				alert("ERROR:" + gar1.getErrorMsg() + ":  Status=" + gar1.getStatus() + ": GtkId=" + gar1.getGtkId());
				ISLogger.getLogger().warn("ERROR onSelect$garant:" + gar1.getErrorMsg() + ":  Status=" + gar1.getStatus() + ": GtkId=" + gar1.getGtkId());
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			ISLogger.getLogger().warn(CheckNull.getPstr(e));
			alert("ERROR: in tab garant 1 " + (CheckNull.isEmpty(e.getMessage()) ? CheckNull.getPstr(e) : e.getMessage()));
		}
		refreshModel(_startPageNumber);
	}
	
	private Window contractmain = null;
	
	public void onClick$btn_confirm()
	{
		sendConfirm(1, current.getP3t18(), currentSum.getP11t20() + "", currentSum);
	}
	
	public void onClick$btn_reject()
	{
		sendConfirm(0, current.getP3t18(), currentSum.getP11t20() + "", currentSum);
	}
	
	public void onClick$btn_confirm2()
	{
		sendConfirm(1, current.getP3t18(), currentTime.getP9t19() + "", currentTime);
	}
	
	public void onClick$btn_reject2()
	{
		sendConfirm(0, current.getP3t18(), currentTime.getP9t19() + "", currentTime);
	}
	
	public void onClick$btn_confirmM()
	{
		sendConfirm(1, current.getP3t18(), "", current);
	}
	
	public void onClick$btn_rejectM()
	{
		sendConfirm(0, current.getP3t18(), "", current);
	}
	
	private void sendConfirm(int action, String docnum, String chdocnum, Object obj)
	{
		if (contractmain == null)
		{
			contractmain = (Window) execution.getDesktop().getPage("contract").getFellow("contractmain");
			
			// contractmain = (Window) session.getAttribute("contractmain");
		}
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("inn", ((String) session.getAttribute("BankINN")));
		params.put("idn", idn);
		params.put("action", action + "");
		params.put("docnum", docnum);
		params.put("chdocnum", chdocnum);
		params.put("obj", obj);
		Events.sendEvent("onConfirmDocument", contractmain, params);
	}
	
	private void sendConfirm2(int action, String docnum, Object obj)
	{
		if (contractmain == null)
		{
			contractmain = (Window) execution.getDesktop().getPage("contract").getFellow("contractmain");
			
			// contractmain = (Window) session.getAttribute("contractmain");
		}
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("inn", ((String) session.getAttribute("BankINN")));
		params.put("idn", idn);
		params.put("action", action + "");
		params.put("docnum", docnum);
		params.put("obj", obj);
		Events.sendEvent("onConfirmDocument", contractmain, params);
	}
	
}
