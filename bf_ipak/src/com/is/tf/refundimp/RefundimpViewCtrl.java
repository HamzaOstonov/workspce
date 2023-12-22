package com.is.tf.refundimp;

import java.text.DecimalFormat;
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
import org.zkoss.zul.Row;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.event.PagingEvent;

import com.is.ISLogger;
import com.is.tf.contract.ContractService;
import com.is.tf.currency.RefCurrencyBox;
import com.is.tf.currency.RefCurrencyData;
import com.is.tf.movefromim.Movefromim;
import com.is.tf.payment.Payment;
import com.is.tf.payment.PaymentService;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.RefData;
import com.is.utils.Res;
import com.is.utils.refobj.RefObjCBox;
import com.is.utils.refobj.RefObjData;
import com.is.utils.refobj.XMLSerializer;
import com.sbs.service.BankServiceProxy;
import com.sbs.service.RefundImpResult;

public class RefundimpViewCtrl extends GenericForwardComposer
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
	private Toolbarbutton btn_add, btn_save;
	private Toolbarbutton btn_search;
	private Toolbarbutton btn_back, btn_confirmSumGl, btn_rejectSumGl;
	private Toolbar tb;
	private String otn, otn2, summa1, summa2;
	private Textbox p12t45, ap12t45, fp12t45, p23t45, p21t45, p19t45, p18t45, id, p1t45, p0t45;
	private Textbox ap23t45, ap21t45, ap19t45, ap18t45, aid, ap1t45, ap0t45;
	private Textbox fp23t45, fp21t45, fp19t45, fp18t45, fid, fp1t45, fp0t45;
	private Datebox p24t45, ap24t45, p2t45, ap2t45, fp2t45;
	private Textbox p15t45, ap15t45, fp15t45;
	private Decimalbox p22t45, current_idn, ap22t45, p14t45, fp14t45, ap14t45, p6t45, p7t45, p8t45, p9t45, p10t45, p11t45, ap6t45, ap7t45, ap8t45, ap9t45, ap10t45, ap11t45, fp6t45, fp7t45, fp8t45, fp9t45, fp10t45, fp11t45, fp13t45, p16t45, p17t45,
			ap16t45, ap17t45, fp16t45, fp17t45;
	private Double P18t44, P19t44, P20t44, P21t44;
	private Double P19t53, P20t53, P21t53, P22t53;
	private RefCBox p3t45, fp3t45, ap3t45, p5t45, ap5t45, fp5t45;
	private RefObjCBox p4t45, ap4t45, p13t45, ap13t45, fp4t45;
	private RefCurrencyBox p7t451, p7t453, p14t451, p14t453, ap7t451, ap7t453, ap14t451, ap14t453;
	private Label acbcourse, acbcourse2, cbcourse, cbcourse2;
	private Paging refundimpPaging;
	private int _pageSize = 15;
	private int _startPageNumber = 0;
	private int _totalSize = 0;
	private HashMap<String, String> curr_ = null;
	private boolean _needsTotalSizeUpdate = true;
	private List<RefObjData> payment = new ArrayList<RefObjData>();
	private List<RefObjData> Movefromimp = new ArrayList<RefObjData>();
	private List<RefData> PaymentSourse = new ArrayList<RefData>();
	private List<RefCurrencyData> coursecurrencies = new ArrayList<RefCurrencyData>();
	private List<RefCurrencyData> coursecurrencies2 = new ArrayList<RefCurrencyData>();
	private Row row_p19t45, row_p12t45, row_p24t45, row_p8t45, row_p9t45, row_p10t45, row_p11t45, row_ap8t45, row_ap9t45, row_ap10t45, row_ap11t45, kur, kur2, akur, akur2;
	private String aPayment_val, Payment_val, Mfromimp_val, aMfromimp_val, alias, un, idn, subj, val1, val2, strval1, strval2;
	private Long idc;
	private Label line1;
	private Label line2;
	private Label line3;
	private Label line4;
	private Label line5;
	private Label line6;
	private Label line7;
	private Label line8;
	private Label conr_val1, conr_val2, conr_val1a, conr_val2a, val_vozv;
	private Label aconr_val1, aconr_val2, aconr_val1a, aconr_val2a, aval_vozv, val_opl, aval_opl;
	private String sparam1;
	public RefundimpFilter filter = new RefundimpFilter();
	private String a;
	PagingListModel model = null;
	ListModelList lmodel = null;
	private AnnotateDataBinder binder;
	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	private static DecimalFormat df2 = new DecimalFormat(".###");
	
	private Refundimp current = new Refundimp();
	
	public RefundimpViewCtrl()
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
			idc = Long.parseLong(parameter[0]);
			System.out.println("idc = " + idc);
			
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
			// System.out.println("Contract summa2 "+summa2);
		}
		parameter = (String[]) param.get("spr");
		if (parameter != null)
		{
			sparam1 = (parameter[0]);
		}
		curr_ = com.is.tf.contract.ContractService.getHCurr(alias);
		
		conr_val2.setValue(curr_.get(val2));
		conr_val1.setValue(curr_.get(val1));
		conr_val2a.setValue(curr_.get(val2));
		conr_val1a.setValue(curr_.get(val1));
		
		filter.setP1t45(idn);
		payment = ContractService.getPaymentObj(idn, alias);
		Movefromimp = ContractService.getMoveFromIm(idn, alias);
		PaymentSourse = ContractService.getPaymentSourse("5,6,7", alias);
		
		current_idn.setValue(idn);
		p4t45.setModel(new ListModelList(payment));
		ap4t45.setModel(new ListModelList(payment));
		fp4t45.setModel(new ListModelList(payment));
		p13t45.setModel(new ListModelList(Movefromimp));
		ap13t45.setModel(new ListModelList(Movefromimp));
		p3t45.setModel(new ListModelList(PaymentSourse));
		ap3t45.setModel(new ListModelList(PaymentSourse));
		
		line1.setValue(Labels.getLabel("refundimp.p2t45t").replaceAll("<br>", "\r\n"));
		line2.setValue(Labels.getLabel("refundimp.p6t45t").replaceAll("<br>", "\r\n"));
		line3.setValue(Labels.getLabel("refundimp.p5t45t").replaceAll("<br>", "\r\n"));
		line4.setValue(Labels.getLabel("refundimp.p23t45t").replaceAll("<br>", "\r\n"));
		line5.setValue(Labels.getLabel("refundimp.p12t45t").replaceAll("<br>", "\r\n"));
		line6.setValue(Labels.getLabel("refundimp.p15t45t").replaceAll("<br>", "\r\n"));
		line7.setValue(Labels.getLabel("refundimp.p24t45t").replaceAll("<br>", "\r\n"));
		line8.setValue(Labels.getLabel("refundimp.p100t45t").replaceAll("<br>", "\r\n"));
		
		dataGrid.setItemRenderer(new ListitemRenderer()
		{
			@SuppressWarnings("unchecked")
			public void render(Listitem row, Object data) throws Exception
			{
				Refundimp pRefundimp = (Refundimp) data;
				
				row.setValue(pRefundimp);
				row.appendChild(new Listcell(pRefundimp.getP2t45() + ""));
				row.appendChild(new Listcell(pRefundimp.getP6t45() + ""));
				row.appendChild(new Listcell(com.is.tf.contract.SPR.getVal(pRefundimp.getP5t45())));
				row.appendChild(new Listcell(pRefundimp.getP23t45()));
				row.appendChild(new Listcell(pRefundimp.getP12t45() + ""));
				row.appendChild(new Listcell(pRefundimp.getP15t45() + ""));
				row.appendChild(new Listcell(pRefundimp.getP24t45() + ""));
				row.appendChild(new Listcell(com.is.tf.contract.SPR.getP100Value(String.valueOf(pRefundimp.getP100t45()))));
				
				/*
				 * row.appendChild(new Listcell(pRefundimp.getId()+""));
				 * row.appendChild(new Listcell(pRefundimp.getP1t45()));
				 * row.appendChild(new Listcell(pRefundimp.getP0t45()));
				 * row.appendChild(new Listcell(pRefundimp.getP3t45()));
				 * row.appendChild(new Listcell(pRefundimp.getP4t45()));
				 * row.appendChild(new Listcell(pRefundimp.getP7t45()+""));
				 * row.appendChild(new Listcell(pRefundimp.getP8t45()+""));
				 * row.appendChild(new Listcell(pRefundimp.getP9t45()+""));
				 * row.appendChild(new Listcell(pRefundimp.getP10t45()+""));
				 * row.appendChild(new Listcell(pRefundimp.getP11t45()+""));
				 * row.appendChild(new Listcell(pRefundimp.getP12t45()+""));
				 * row.appendChild(new Listcell(pRefundimp.getP13t45()+""));
				 * row.appendChild(new Listcell(pRefundimp.getP14t45()));
				 * row.appendChild(new Listcell(pRefundimp.getP16t45()+""));
				 * row.appendChild(new Listcell(pRefundimp.getP17t45()+""));
				 */

			}
		});
		p5t45.setModel((new ListModelList(ContractService.getMyCurrency(alias))));
		ap5t45.setModel((new ListModelList(ContractService.getMyCurrency(alias))));
		fp5t45.setModel((new ListModelList(ContractService.getMyCurrency(alias))));
		
		refreshModel(_startPageNumber);
		
	}
	
	public void onPaging$refundimpPaging(ForwardEvent event)
	{
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModel(_startPageNumber);
	}
	
	private void refreshModel(int activePage)
	{
		refundimpPaging.setPageSize(_pageSize);
		model = new PagingListModel(activePage, _pageSize, filter, "");
		filter.setP1t45(idn);
		if (_needsTotalSizeUpdate)
		{
			_totalSize = model.getTotalSize(filter, "");
			// _needsTotalSizeUpdate = false;
		}
		
		refundimpPaging.setTotalSize(_totalSize);
		
		dataGrid.setModel((ListModel) model);
		if (model.getSize() > 0)
		{
			this.current = (Refundimp) model.getElementAt(0);
			sendSelEvt();
		}
	}
	
	// Omitted...
	public Refundimp getCurrent()
	{
		return current;
	}
	
	public void setCurrent(Refundimp current)
	{
		this.current = current;
	}
	
	public void onDoubleClick$dataGrid$grd()
	{
		
		if (sparam1 != null)
		{
			if (sparam1.equals("Filial")) // / Филиал
			{
				btn_save.setVisible(true);
				btn_confirmSumGl.setVisible(false);
				btn_rejectSumGl.setVisible(false);
				
			}
			else if (sparam1.equals("Go")) // / ГО
			{
				btn_save.setVisible(false);
				btn_confirmSumGl.setVisible(false);
				btn_rejectSumGl.setVisible(false);
			}
			else if (sparam1.equals("GlBuhg")) // / ГО
			{
				btn_confirmSumGl.setVisible(true);
				btn_rejectSumGl.setVisible(true);
				btn_save.setVisible(false);
				
			}
		}
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
		
		aconr_val2.setValue(curr_.get(val2));
		aconr_val1.setValue(curr_.get(val1));
		aconr_val2a.setValue(curr_.get(val2));
		aconr_val1a.setValue(curr_.get(val1));
		onDoubleClick$dataGrid$grd();
		ap1t45.setValue(idn);
		ap15t45.setValue((String) session.getAttribute("un"));
		ap2t45.setValue(new Date());
		frmgrd.setVisible(false);
		addgrd.setVisible(true);
		fgrd.setVisible(false);
		payment = ContractService.getPaymentObj(idn, alias);
		ap4t45.setModel(new ListModelList(payment));
		Payment pm = ((Payment) p4t45.getObject());
		
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
				
				Res res2 = RefundimpService.create(new Refundimp(

				ap2t45.getValue(),
						ap3t45.getValue(),
						ap4t45.getValue(),
						ap5t45.getValue(),
						ap6t45.doubleValue(),
						ap7t45.doubleValue(),
						ap8t45.doubleValue(),
						ap9t45.doubleValue(),
						ap10t45.doubleValue(),
						ap11t45.doubleValue(),
						ap13t45.getValue(),
						ap14t45.doubleValue(),
						ap15t45.getValue(),
						ap22t45.doubleValue(),
						ap23t45.getValue()), idn, idc);
				if (res2.getCode() == 0)
				{
					alert("Сохранение успешно!");
					
				}
				else
				{
					alert("Ошибка сохранении:" + res2.getName() + ":" + res2.getCode());
				}
			}
			else if (fgrd.isVisible())
			{
				filter = new RefundimpFilter();
				
				Long.parseLong(fid.getValue());
				filter.setP1t45(fp1t45.getValue());
				filter.setP0t45(fp0t45.getValue());
				filter.setP2t45(fp2t45.getValue());
				filter.setP3t45(fp3t45.getValue());
				filter.setP4t45(fp4t45.getValue());
				filter.setP5t45(fp5t45.getValue());
				filter.setP6t45(fp6t45.doubleValue());
				filter.setP7t45(fp7t45.doubleValue());
				filter.setP8t45(fp8t45.doubleValue());
				filter.setP9t45(fp9t45.doubleValue());
				filter.setP10t45(fp10t45.doubleValue());
				filter.setP11t45(fp11t45.doubleValue());
				// filter.setP12t45(fp12t45.getValue());
				filter.setP13t45(fp13t45.doubleValue());
				// filter.setP14t45(fp14t45.getValue());
				// .setP15t45(fp15t45.intValue());
				filter.setP16t45(fp16t45.doubleValue());
				filter.setP17t45(fp17t45.doubleValue());
				
			}
			else
			{
				
				Long.parseLong(id.getValue());
				// current.setP1t45(p1t45.getValue());
				// current.setP0t45(p0t45.getValue());
				current.setP2t45(p2t45.getValue());
				current.setP3t45(p3t45.getValue());
				current.setP4t45(p4t45.getValue());
				current.setP5t45(p5t45.getValue());
				current.setP6t45(p6t45.doubleValue());
				current.setP7t45(p7t45.doubleValue());
				current.setP8t45(p8t45.doubleValue());
				current.setP9t45(p9t45.doubleValue());
				current.setP10t45(p10t45.doubleValue());
				current.setP11t45(p11t45.doubleValue());
				current.setP12t45(p12t45.getValue());
				current.setP13t45(p13t45.getValue());
				current.setP14t45(p14t45.doubleValue());
				current.setP15t45((String) session.getAttribute("un"));
				current.setP19t45(null);
				current.setP22t45(p6t45.doubleValue() / p7t45.doubleValue());
				current.setP100t45("9");
				// RefundimpService.update(current);
				Res res = RefundimpService.update(current);
				if (res.getCode() == 0)
				{
					alert("Корректировка произошла успешно");
					
				}
				else
				{
					alert("Ошибка:" + res.getName());
					System.out.println("Ошибка:" + res.getName());
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
	
	public void onClick$btn_confirmSumGl()
	{
		Movefromim mi = ((Movefromim) p13t45.getObject());
		Payment pm = ((Payment) p4t45.getObject());
		try
		{
			if (current.getP15t45() != null)
			{
				
				if (current.getP100t45().equals("9") || (current.getP100t45().equals("b")))
				{
					RefCurrencyData curr1 = PaymentService.getRefCurrencyData(pm.getP15t44(), coursecurrencies);
					RefCurrencyData curr2 = PaymentService.getRefCurrencyData(current.getP5t45(), coursecurrencies);
					RefCurrencyData curr3 = PaymentService.getRefCurrencyData(val1, coursecurrencies);
					RefCurrencyData curr4 = PaymentService.getRefCurrencyData(val2, coursecurrencies);
					
					if (pm.getP15t44().equalsIgnoreCase(val1) || pm.getP15t44().equalsIgnoreCase(val2))
					{
						if (curr1.getCourse() < curr3.getCourse() || curr1.getCourse() < curr4.getCourse())
						{
							otn2 = ("0");
						}
						else
						{
							otn2 = ("1");
						}
					}
					else
					{
						otn2 = null;
					}
					
					if (curr1.getCourse() > curr2.getCourse())
					{
						otn = ("0");
					}
					else
					{
						otn = ("1");
					}
					// alert("curr2.getCourse()="+curr2.getCourse()+" curr1.getCourse()="+curr1.getCourse());
					final BankServiceProxy ws = new BankServiceProxy((String) session.getAttribute("YESVO_URL"));
					com.sbs.service.RefundImp refimp = new com.sbs.service.RefundImp();
					
					Long.parseLong(id.getValue());
					current.setP2t45(p2t45.getValue());
					current.setP3t45(p3t45.getValue());
					current.setP4t45(p4t45.getValue());
					current.setP5t45(p5t45.getValue());
					current.setP6t45(p6t45.doubleValue());
					current.setP7t45(p7t45.doubleValue());
					current.setP8t45(p8t45.doubleValue());
					current.setP9t45(p9t45.doubleValue());
					current.setP10t45(p10t45.doubleValue());
					current.setP11t45(p11t45.doubleValue());
					current.setP12t45(p12t45.getValue());
					current.setP13t45(p13t45.getValue());
					current.setP14t45(p14t45.doubleValue());
					current.setP15t45(p15t45.getValue());
					current.setP19t45((String) session.getAttribute("un"));
					current.setP22t45(p22t45.doubleValue());
					current.setP100t45(null);
					RefundImpResult ar = ws.saveRefundImp((String) (session.getAttribute("BankINN")), idn, getRfiNew(current));
					// alert("current="+current.getP0t37()+" 0="+p0t37.getValue());
					if (ar.getStatus() == 0)
					{
						RefundimpService.remove1(current);
						alert("Сохранение успешно");
						onClick$btn_back();
						refreshModel(_startPageNumber);
						SelectEvent evt = new SelectEvent("onSelect", dataGrid, dataGrid.getSelectedItems());
						Events.sendEvent(evt);
						
					}
					else
					{
						alert("Error:" + ar.getStatus() + "; GTKid:" + ar.getGtkId() + "; Text:" + ar.getErrorMsg());
						System.out.println("Error:" + ar.getStatus() + "; GTKid:" + ar.getGtkId() + "; Text:" + ar.getErrorMsg());
						onClick$btn_back();
					}
					
				}
			}
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
			filter = new RefundimpFilter();
		}
		onClick$btn_back();
		frmgrd.setVisible(true);
		addgrd.setVisible(false);
		fgrd.setVisible(false);
		CheckNull.clearForm(addgrd);
		CheckNull.clearForm(fgrd);
		refreshModel(_startPageNumber);
	}
	
	private com.sbs.service.RefundImp getRfiNew(Refundimp acr) throws Exception
	{
		java.util.Calendar cal = java.util.Calendar.getInstance();
		com.sbs.service.RefundImp res = new com.sbs.service.RefundImp();
		// res.setP0T45(Integer.parseInt(acr.getP0t45()));
		res.setP0T45(0);
		cal.setTime(df.parse(df.format(acr.getP2t45())));
		res.setP2T45(cal);
		res.setP3T45(Short.parseShort(acr.getP3t45()));
		res.setP4T45(Integer.parseInt(acr.getP4t45()));
		res.setP5T45(acr.getP5t45());
		res.setP6T45(acr.getP6t45());
		res.setP7T45(acr.getP7t45());
		if (acr.getP8t45() != null)
		{
			res.setP8T45(acr.getP8t45());
		}
		if (acr.getP9t45() != null)
		{
			res.setP9T45(acr.getP9t45());
		}
		if (acr.getP10t45() != null)
		{
			res.setP10T45(acr.getP10t45());
		}
		if (acr.getP11t45() != null)
		{
			res.setP11T45(acr.getP11t45());
		}
		if (!CheckNull.isEmpty(acr.getP13t45()) && acr.getP13t45() != null && acr.getP13t45() != "")
		{
			res.setP13T45(Integer.parseInt(acr.getP13t45()));
		}
		if (acr.getP14t45() != null)
		{
			res.setP14T45(acr.getP14t45());
		}
		
		res.setP15T45(acr.getP15t45());
		res.setP18T45(Short.parseShort(otn));
		res.setP19T45((String) session.getAttribute("un"));
		if (otn2 != null)
		{
			res.setP21T45(Short.parseShort(otn2));
		}
		if (acr.getP22t45() != null)
		{
			res.setP22T45(acr.getP22t45());
		}
		
		return res;
	}
	
	/*
	 * public void onSelect$p13t45() { //current.setP15t44(p15t44.getValue());
	 * PaymentRequest pm=(PaymentRequest)p4t45.getObject(); Payment_val=pm.getP15t44();
	 * Movefromim mi=(Movefromim)p13t45.getObject();
	 * Mfromimp_val=mi.getP16t53(); coursecurrencies =
	 * ContractService.getMyCurr_2_only
	 * (df.format(p2t45.getValue()),"'"+Payment_val+"'","'"+Mfromimp_val+"'",
	 * alias); p7t451.setModel((new ListModelList(coursecurrencies)));
	 * p7t453.setModel((new ListModelList(coursecurrencies))); setCourse(false);
	 * kur.setVisible(true); if (Payment_val.equalsIgnoreCase(Mfromimp_val)){
	 * kur.setVisible(false);
	 * //System.out.println("onChange$p15t44()     val1= "
	 * +val1+"="+" p15t44.getValue()="+p15t44.getValue()); } }
	 */
	public void onInitRenderLater$p7t451()
	{
		setCourse(false);
	}
	
	public void onInitRenderLater$p7t453()
	{
		setCourse(false);
	}
	
	public void onInitRenderLater$ap7t451()
	{
		asetCourse(false);
	}
	
	public void onInitRenderLater$ap7t453()
	{
		asetCourse(false);
	}
	
	public void onInitRenderLater$p14t451()
	{
		setCourse(false);
	}
	
	public void onInitRenderLater$p14t453()
	{
		setCourse(false);
	}
	
	public void onInitRenderLater$ap14t451()
	{
		asetCourse(false);
	}
	
	public void onInitRenderLater$ap14t453()
	{
		asetCourse(false);
	}
	
	private void setCourse(Boolean getNewCourse)
	{
		Payment pm = ((Payment) p4t45.getObject());
		Movefromim mi = (Movefromim) p13t45.getObject();
		if (pm != null)
		{
			Payment_val = pm.getP15t44();
			// System.out.println("Payment_val="+Payment_val);
		}
		if (getNewCourse)
		{
			
			// System.out.println("Payment_val="+Payment_val);
			
			coursecurrencies = ContractService.getMyCurr_2_only(df.format(p2t45.getValue()), current.getP5t45(), Payment_val, alias);
			coursecurrencies2 = ContractService.getCourseCurr_18t1_19t1_withOther2(idn, idc, df.format(p2t45.getValue()), Payment_val, alias);
			p7t451.setModel((new ListModelList(coursecurrencies)));
			p7t453.setModel((new ListModelList(coursecurrencies)));
			p14t451.setModel((new ListModelList(coursecurrencies2)));
			p14t453.setModel((new ListModelList(coursecurrencies2)));
			
		}
		if (coursecurrencies.size() > 0)
		{
			
			RefCurrencyData curr1 = PaymentService.getRefCurrencyData(Payment_val, coursecurrencies);
			RefCurrencyData curr2 = PaymentService.getRefCurrencyData(current.getP5t45(), coursecurrencies);
			// RefCurrencyData curr3 = PaymentService.getRefCurrencyData(val2,
			// coursecurrencies);
			if (curr1 != null && curr2 != null)
			{
				
				if (curr1.getCourse() > curr2.getCourse())
				{
					p7t451.setSelecteditem(curr1.getKod());
					p7t453.setSelecteditem(curr2.getKod());
				}
				else
				{
					p7t451.setSelecteditem(curr2.getKod());
					p7t453.setSelecteditem(curr1.getKod());
				}
				System.out.println("p2t45.getValue()" + p2t45.getValue() + " current.getP5t45()=" + current.getP5t45() + " curr1.getCourse()=" + curr1.getCourse() + " curr2.getCourse()=" + curr2.getCourse());
				
				countCourse(false);
			}
		}
		if (coursecurrencies2.size() > 0)
		{
			
			RefCurrencyData curr11 = PaymentService.getRefCurrencyData(Payment_val, coursecurrencies2);
			RefCurrencyData curr12 = PaymentService.getRefCurrencyData(val1, coursecurrencies2);
			RefCurrencyData curr13 = PaymentService.getRefCurrencyData(val2, coursecurrencies2);
			if (curr11 != null && curr12 != null && curr13 != null)
			{
				
				if (curr11.getCourse() > curr12.getCourse())
				{
					p14t451.setSelecteditem(curr11.getKod());
					p14t453.setSelecteditem(curr12.getKod());
				}
				else
				{
					p14t451.setSelecteditem(curr12.getKod());
					p14t453.setSelecteditem(curr11.getKod());
				}
				
				countCourse(true);
			}
		}
	}
	
	private void countCourse(Boolean setCourse)
	{
		try
		{
			
			if (!CheckNull.isEmpty(p7t453.getValue()) && !CheckNull.isEmpty(p7t451.getValue()))
			{
				if (setCourse)
				{
					p7t45.setValue("" + (p7t451.getCourse() / p7t453.getCourse()));
					current.setP7t45((p7t451.getCourse() / p7t453.getCourse()));
				}
				p7t45.setValue("" + (p7t451.getCourse() / p7t453.getCourse()));
				
				System.out.println("По курсу ЦБ: " + (p7t451.getCourse() / p7t453.getCourse()) + " " + p7t451.getCourse() + " " + p7t453.getCourse());
				cbcourse.setValue("По курсу ЦБ: " + (p7t451.getCourse() / p7t453.getCourse()));
				
				// cbcourse1.setValue("По курсу ЦБ: "+(p7t391.getCourse()/p7t393.getCourse()));
				/*
				 * Double p4t27val = null; Double p5t27val = null; if
				 * (p15t44.getValue().equalsIgnoreCase(p17t443.getValue())) {
				 * p4t27val = p4t27.doubleValue() * p10t27.doubleValue();
				 * System.out.println(p4t27.doubleValue() + "*" +
				 * p10t27.doubleValue() +"="+p4t27val); } else { p4t27val =
				 * p4t27.doubleValue() / p10t27.doubleValue();
				 * System.out.println(p4t27.doubleValue() + "/" +
				 * p10t27.doubleValue() +"="+p4t27val); } Double db = p4t27val +
				 * p5t27val; //System.out.println(p4t27val + "+" + p5t27val
				 * +"="+db); Boolean bool = (db == p3t27.doubleValue());
				 * checksum.setChecked(bool); checksum.setLabel((bool?
				 * "Сумма комиссии полностью соответствует указанному курсу!"
				 * :"Сумма комиссии не соответствует указанному курсу!"
				 * )+"("+db+")");
				 */
			}
			if (!CheckNull.isEmpty(p14t453.getValue()) && !CheckNull.isEmpty(p14t451.getValue()))
			{
				if (setCourse)
				{
					p14t45.setValue("" + (p14t451.getCourse() / p14t453.getCourse()));
					current.setP14t45((p14t451.getCourse() / p14t453.getCourse()));
				}
				p14t45.setValue("" + (p14t451.getCourse() / p14t453.getCourse()));
				
				System.out.println("По курсу ЦБ: " + (p14t451.getCourse() / p14t453.getCourse()) + " " + p14t451.getCourse() + " " + p14t453.getCourse());
				cbcourse2.setValue("По курсу ЦБ: " + (p14t451.getCourse() / p14t453.getCourse()));
				
				// cbcourse1.setValue("По курсу ЦБ: "+(p7t391.getCourse()/p7t393.getCourse()));
				/*
				 * Double p4t27val = null; Double p5t27val = null; if
				 * (p15t44.getValue().equalsIgnoreCase(p17t443.getValue())) {
				 * p4t27val = p4t27.doubleValue() * p10t27.doubleValue();
				 * System.out.println(p4t27.doubleValue() + "*" +
				 * p10t27.doubleValue() +"="+p4t27val); } else { p4t27val =
				 * p4t27.doubleValue() / p10t27.doubleValue();
				 * System.out.println(p4t27.doubleValue() + "/" +
				 * p10t27.doubleValue() +"="+p4t27val); } Double db = p4t27val +
				 * p5t27val; //System.out.println(p4t27val + "+" + p5t27val
				 * +"="+db); Boolean bool = (db == p3t27.doubleValue());
				 * checksum.setChecked(bool); checksum.setLabel((bool?
				 * "Сумма комиссии полностью соответствует указанному курсу!"
				 * :"Сумма комиссии не соответствует указанному курсу!"
				 * )+"("+db+")");
				 */
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void asetCourse(Boolean getNewCourse)
	{
		Payment pm = ((Payment) ap4t45.getObject());
		Movefromim mi = (Movefromim) ap13t45.getObject();
		if (pm != null)
		{
			aPayment_val = pm.getP15t44();
			// System.out.println("asetCourse1:   aPayment_val="+aPayment_val+"ap5t45.getValue()"+ap5t45.getValue());
			
			if (getNewCourse)
			{
				coursecurrencies = ContractService.getMyCurr_2_only(df.format(ap2t45.getValue()), ap5t45.getValue(), aPayment_val, alias);
				coursecurrencies2 = ContractService.getCourseCurr_18t1_19t1_withOther2(idn, idc, df.format(ap2t45.getValue()), aPayment_val, alias);
				ap7t451.setModel((new ListModelList(coursecurrencies)));
				ap7t453.setModel((new ListModelList(coursecurrencies)));
				ap14t451.setModel((new ListModelList(coursecurrencies2)));
				ap14t453.setModel((new ListModelList(coursecurrencies2)));
			}
			
			if (coursecurrencies.size() > 0)
			{
				RefCurrencyData curr1 = PaymentService.getRefCurrencyData(aPayment_val, coursecurrencies);
				RefCurrencyData curr2 = PaymentService.getRefCurrencyData(ap5t45.getValue(), coursecurrencies);
				// RefCurrencyData curr3 =
				// PaymentService.getRefCurrencyData(val2, coursecurrencies);
				if (curr1 != null && curr2 != null)
				{
					
					if (curr1.getCourse() > curr2.getCourse())
					{
						ap7t451.setSelecteditem(curr1.getKod());
						ap7t453.setSelecteditem(curr2.getKod());
					}
					else
					{
						ap7t451.setSelecteditem(curr2.getKod());
						ap7t453.setSelecteditem(curr1.getKod());
					}
					System.out.println("asetCourse1:   aPayment_val=" + aPayment_val + " ap2t45.getValue()" + ap2t45.getValue() + " ap5t45.getValue()=" + ap5t45.getValue() + " curr1.getCourse()=" + curr1.getCourse() + " curr2.getCourse()="
							+ curr2.getCourse());
				}
				
			}
			
			if (coursecurrencies2.size() > 0)
			{
				RefCurrencyData acurr1 = PaymentService.getRefCurrencyData(val1, coursecurrencies2);
				RefCurrencyData acurr2 = PaymentService.getRefCurrencyData(val2, coursecurrencies2);
				RefCurrencyData acurr3 = PaymentService.getRefCurrencyData(aPayment_val, coursecurrencies2);
				// RefCurrencyData curr3 =
				// PaymentService.getRefCurrencyData(val2, coursecurrencies);
				if ((acurr3 != null && acurr1 != null) || (acurr2 != null && acurr3 != null))
				{
					
					if (acurr3.getCourse() > acurr1.getCourse())
					{
						ap14t451.setSelecteditem(acurr3.getKod());
						ap14t453.setSelecteditem(acurr1.getKod());
					}
					else
					{
						ap14t451.setSelecteditem(acurr1.getKod());
						ap14t453.setSelecteditem(acurr3.getKod());
						// System.out.println("asetCourse2:   aPayment_val="+aPayment_val+" val1-"+val1+" val2="+val2+" acurr1.getCourse()="+acurr1.getCourse()+" acurr2.getCourse()="+acurr2.getCourse()+" acurr3.getCourse()="+acurr3.getCourse());
					}
				}
			}
			
			acountCourse(true);
		}
		
	}
	
	private void acountCourse(Boolean setCourse)
	{
		try
		{
			
			if (!CheckNull.isEmpty(ap7t453.getValue()) && !CheckNull.isEmpty(ap7t451.getValue()))
			{
				if (setCourse)
				{
					ap7t45.setValue("" + (ap7t451.getCourse() / ap7t453.getCourse()));
					// ap7t45.getValue(""+(ap7t451.getCourse()/ap7t453.getCourse()));
				}
				ap7t45.setValue("" + (ap7t451.getCourse() / ap7t453.getCourse()));
				System.out.println("По курсу ЦБ: " + (ap7t451.getCourse() / ap7t453.getCourse()) + " " + ap7t451.getCourse() + " " + ap7t453.getCourse());
				acbcourse.setValue("По курсу ЦБ: " + (ap7t451.getCourse() / ap7t453.getCourse()));
				
				// cbcourse1.setValue("По курсу ЦБ: "+(p7t391.getCourse()/p7t393.getCourse()));
				/*
				 * Double p4t27val = null; Double p5t27val = null; if
				 * (p15t44.getValue().equalsIgnoreCase(p17t443.getValue())) {
				 * p4t27val = p4t27.doubleValue() * p10t27.doubleValue();
				 * System.out.println(p4t27.doubleValue() + "*" +
				 * p10t27.doubleValue() +"="+p4t27val); } else { p4t27val =
				 * p4t27.doubleValue() / p10t27.doubleValue();
				 * System.out.println(p4t27.doubleValue() + "/" +
				 * p10t27.doubleValue() +"="+p4t27val); } Double db = p4t27val +
				 * p5t27val; //System.out.println(p4t27val + "+" + p5t27val
				 * +"="+db); Boolean bool = (db == p3t27.doubleValue());
				 * checksum.setChecked(bool); checksum.setLabel((bool?
				 * "Сумма комиссии полностью соответствует указанному курсу!"
				 * :"Сумма комиссии не соответствует указанному курсу!"
				 * )+"("+db+")");
				 */
			}
			if (!CheckNull.isEmpty(ap14t453.getValue()) && !CheckNull.isEmpty(ap14t451.getValue()))
			{
				if (setCourse)
				{
					ap14t45.setValue("" + (ap14t451.getCourse() / ap14t453.getCourse()));
					// ap7t45.getValue(""+(ap7t451.getCourse()/ap7t453.getCourse()));
				}
				ap14t45.setValue("" + (ap14t451.getCourse() / ap14t453.getCourse()));
				System.out.println("По курсу ЦБ: " + (ap14t451.getCourse() / ap14t453.getCourse()) + " " + ap14t451.getCourse() + " " + ap14t453.getCourse());
				acbcourse.setValue("По курсу ЦБ: " + (ap14t451.getCourse() / ap14t453.getCourse()));
				
				// cbcourse1.setValue("По курсу ЦБ: "+(p7t391.getCourse()/p7t393.getCourse()));
				/*
				 * Double p4t27val = null; Double p5t27val = null; if
				 * (p15t44.getValue().equalsIgnoreCase(p17t443.getValue())) {
				 * p4t27val = p4t27.doubleValue() * p10t27.doubleValue();
				 * System.out.println(p4t27.doubleValue() + "*" +
				 * p10t27.doubleValue() +"="+p4t27val); } else { p4t27val =
				 * p4t27.doubleValue() / p10t27.doubleValue();
				 * System.out.println(p4t27.doubleValue() + "/" +
				 * p10t27.doubleValue() +"="+p4t27val); } Double db = p4t27val +
				 * p5t27val; //System.out.println(p4t27val + "+" + p5t27val
				 * +"="+db); Boolean bool = (db == p3t27.doubleValue());
				 * checksum.setChecked(bool); checksum.setLabel((bool?
				 * "Сумма комиссии полностью соответствует указанному курсу!"
				 * :"Сумма комиссии не соответствует указанному курсу!"
				 * )+"("+db+")");
				 */
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void onSelect$p4t45()
	{
		Payment pm = ((Payment) p4t45.getObject());
		
		if (pm != null)
		{
			Payment_val = pm.getP15t44();
			P18t44 = pm.getP18t44();
			P19t44 = pm.getP19t44();
			P20t44 = pm.getP20t44();
			P21t44 = pm.getP21t44();
			// System.out.println("Payment_val="+Payment_val);
		}
		if (Payment_val != null && Payment_val.equalsIgnoreCase(val1) || Payment_val != null && val2 != null && Payment_val.equalsIgnoreCase(val2))
		{
			kur2.setVisible(true);
		}
		else
		{
			kur2.setVisible(false);
		}
		
		coursecurrencies = ContractService.getMyCurr_2_only(df.format(p2t45.getValue()), current.getP5t45(), Payment_val, alias);
		coursecurrencies2 = ContractService.getCourseCurr_18t1_19t1_withOther2(idn, idc, df.format(p2t45.getValue()), Payment_val, alias);
		p7t451.setModel((new ListModelList(coursecurrencies)));
		p7t453.setModel((new ListModelList(coursecurrencies)));
		p14t451.setModel((new ListModelList(coursecurrencies2)));
		p14t453.setModel((new ListModelList(coursecurrencies2)));
		setCourse(false);
		kur.setVisible(true);
	}
	
	public void onSelect$ap4t45()
	{
		
		Payment pm = ((Payment) ap4t45.getObject());
		
		if (pm != null)
		{
			P18t44 = pm.getP18t44();
			P19t44 = pm.getP19t44();
			P20t44 = pm.getP20t44();
			P21t44 = pm.getP21t44();
			
			aval_opl.setValue(curr_.get(pm.getP15t44()));
			aPayment_val = pm.getP15t44();
			System.out.println("Payment_val=" + aPayment_val + "  pm.getP19t44()=" + pm.getP19t44());
			if (pm.getP18t44() != null || !CheckNull.isEmpty(pm.getP18t44()))
			{
				row_ap8t45.setVisible(true);
			}
			else
			{
				row_ap8t45.setVisible(false);
			}
			if (pm.getP19t44() > 0 || pm.getP19t44() != 0.000 || pm.getP19t44() != null || !CheckNull.isEmpty(pm.getP19t44()))
			{
				row_ap9t45.setVisible(true);
			}
			else
			{
				row_ap9t45.setVisible(false);
			}
			if (pm.getP20t44() != null || !CheckNull.isEmpty(pm.getP20t44()))
			{
				row_ap10t45.setVisible(true);
			}
			else
			{
				row_ap10t45.setVisible(false);
			}
			if (pm.getP21t44() != null || !CheckNull.isEmpty(pm.getP21t44()))
			{
				row_ap11t45.setVisible(true);
			}
			else
			{
				row_ap11t45.setVisible(false);
			}
			
		}
		if (aPayment_val != null && aPayment_val.equalsIgnoreCase(val1) || aPayment_val != null && val2 != null && aPayment_val.equalsIgnoreCase(val2))
		{
			akur2.setVisible(true);
		}
		else
		{
			akur2.setVisible(false);
		}
		
		if (ap2t45.getValue() != null && ap5t45.getValue() != null)
		{
			coursecurrencies = ContractService.getMyCurr_2_only(df.format(ap2t45.getValue()), ap5t45.getValue(), aPayment_val, alias);
			coursecurrencies2 = ContractService.getCourseCurr_18t1_19t1_withOther2(idn, idc, df.format(ap2t45.getValue()), aPayment_val, alias);
			ap7t451.setModel((new ListModelList(coursecurrencies)));
			ap7t453.setModel((new ListModelList(coursecurrencies)));
			ap14t451.setModel((new ListModelList(coursecurrencies2)));
			ap14t453.setModel((new ListModelList(coursecurrencies2)));
			asetCourse(false);
			akur.setVisible(true);
			// System.out.println("onSelect$ap4t45():   ap4t45.getValue()="+ap4t45.getValue()+" ap7t45.getValue="+ap7t45.getValue()+" ap7t451.getCourse()="+ap7t451.getCourse()+" ap7t453.getCourse()="+ap7t453.getCourse());
		}
	}
	
	public void onSelect$ap5t45()
	{
		Payment pm = ((Payment) ap4t45.getObject());
		
		if (pm != null)
		{
			aPayment_val = pm.getP15t44();
			System.out.println("aPayment_val=" + aPayment_val);
		}
		if (ap2t45.getValue() != null && ap4t45.getValue() != null)
		{
			coursecurrencies = ContractService.getMyCurr_2_only(df.format(ap2t45.getValue()), ap5t45.getValue(), aPayment_val, alias);
			
			ap7t451.setModel((new ListModelList(coursecurrencies)));
			ap7t453.setModel((new ListModelList(coursecurrencies)));
			
			asetCourse(true);
			akur.setVisible(true);
		}
		if (ap5t45.getValue() != null)
		{
			aval_vozv.setValue(curr_.get(ap5t45.getValue()));
		}
		
		System.out.println("onSelect$ap5t45:   ap4t45.getValue()=" + ap4t45.getValue() + " ap5t45.getValue()=" + ap5t45.getValue() + " ap7t45.getValue=" + ap7t45.getValue() + " ap7t451.getCourse()=" + ap7t451.getCourse() + " ap7t453.getCourse()="
				+ ap7t453.getCourse());
	}
	
	public void onSelect$p13t45()
	{
		Movefromim mi = (Movefromim) p13t45.getObject();
		
		if (mi != null)
		{
			Mfromimp_val = mi.getP16t53();
			// System.out.println("p2t45.getValue() "+df.format(p2t45.getValue())+"Mfromimp_val="+Mfromimp_val);
		}
		if (Mfromimp_val.equalsIgnoreCase(val1) && val2.equals(null))
		{
			kur2.setVisible(false);
		}
		else if (Mfromimp_val.equalsIgnoreCase(val1) || Mfromimp_val.equalsIgnoreCase(val2))
		{
			kur2.setVisible(false);
		}
		
		coursecurrencies = ContractService.getMyCurr_2_only(df.format(p2t45.getValue()), current.getP5t45(), Mfromimp_val, alias);
		coursecurrencies2 = ContractService.getCourseCurr_18t1_19t1_withOther2(idn, idc, df.format(p2t45.getValue()), Mfromimp_val, alias);
		p7t451.setModel((new ListModelList(coursecurrencies)));
		p7t453.setModel((new ListModelList(coursecurrencies)));
		p14t451.setModel((new ListModelList(coursecurrencies2)));
		p14t453.setModel((new ListModelList(coursecurrencies2)));
		setCourse(false);
		kur.setVisible(true);
		
		if (Mfromimp_val.equalsIgnoreCase(p5t45.getValue()))
		{
			kur.setVisible(false);
			// System.out.println("onChange$p15t44()     val1= "+val1+"="+" p15t44.getValue()="+p15t44.getValue());
		}
	}
	
	public void onSelect$ap13t45()
	{
		Movefromim mi = (Movefromim) ap13t45.getObject();
		
		if (mi != null)
		{
			aMfromimp_val = mi.getP16t53();
			
		}
		if (aMfromimp_val.equalsIgnoreCase(val1) && val2.equals(null))
		{
			akur2.setVisible(false);
		}
		else if (aMfromimp_val.equalsIgnoreCase(val1) || aMfromimp_val.equalsIgnoreCase(val2))
		{
			akur2.setVisible(false);
		}
		coursecurrencies = ContractService.getMyCurr_2_only(df.format(ap2t45.getValue()), ap5t45.getValue(), aMfromimp_val, alias);
		ap7t451.setModel((new ListModelList(coursecurrencies)));
		ap7t453.setModel((new ListModelList(coursecurrencies)));
		// asetCourse(false);
		akur.setVisible(true);
		
		if (aMfromimp_val.equalsIgnoreCase(ap5t45.getValue()))
		{
			kur.setVisible(false);
			// System.out.println("onChange$p15t44()     val1= "+val1+"="+" p15t44.getValue()="+p15t44.getValue());
		}
	}
	
	public void onChange$p6t45()
	{
		p22t45.setValue("" + p6t45.doubleValue() / p7t45.doubleValue());
		
	}
	
	public void onChange$ap6t45()
	{
		ap22t45.setValue("" + ap6t45.doubleValue() / ap7t45.doubleValue());
		
	}
	
	public void onChange$p8t45()
	{
		Movefromim mi = ((Movefromim) p13t45.getObject());
		Payment pm = ((Payment) p4t45.getObject());
		// System.out.println("pm.getP18t44()="+pm.getP18t44()+"current.getP8t45()="+current.getP8t45());
		if (P18t44 != null && (current.getP8t45() > P18t44)) ;
		{
			alert("Сумма возврата за товар в валюте контракта 1 должна быть меньше или равна сумме=" + P18t44);
		}
	}
	
	public void onChange$ap8t45()
	{
		// Movefromim mi=((Movefromim)p13t45.getObject());
		// PaymentRequest pm = ((PaymentRequest)p4t45.getObject());
		
		if (P18t44 != null && (ap8t45.doubleValue() > P18t44)) ;
		{
			alert("Сумма возврата за товар в валюте контракта 1 должна быть меньше или равна сумме=" + P18t44);
		}
	}
	
	public void onChange$p9t45()
	{
		// PaymentRequest pm = ((PaymentRequest)p4t45.getObject());
		// System.out.println("pm.getP18t44()="+pm.getP18t44()+"current.getP8t45()="+current.getP8t45());
		if (P19t44 != null && (current.getP9t45() > P19t44)) ;
		{
			alert("Сумма возврата за товар в валюте контракта 2 должна быть меньше или равна сумме=" + P19t44);
		}
	}
	
	public void onChange$ap9t45()
	{
		// PaymentRequest pm = ((PaymentRequest)p4t45.getObject());
		// System.out.println("pm.getP18t44()="+pm.getP18t44()+"current.getP8t45()="+current.getP8t45());
		if (P19t44 != null && (ap9t45.doubleValue() > P19t44)) ;
		{
			alert("Сумма возврата за товар в валюте контракта 2 должна быть меньше или равна сумме=" + P19t44);
		}
	}
	
	public void onChange$p10t45()
	{
		// PaymentRequest pm = ((PaymentRequest)p4t45.getObject());
		// System.out.println("pm.getP18t44()="+pm.getP18t44()+"current.getP8t45()="+current.getP8t45());
		if (P20t44 != null && (current.getP10t45() > P20t44)) ;
		{
			alert("Сумма возврата за услуги  в валюте контракта 1 должна быть меньше или равна сумме=" + P20t44);
		}
	}
	
	public void onChange$ap10t45()
	{
		// PaymentRequest pm = ((PaymentRequest)p4t45.getObject());
		// System.out.println("pm.getP18t44()="+pm.getP18t44()+"current.getP8t45()="+current.getP8t45());
		if (P20t44 != null && (ap10t45.doubleValue() > P20t44)) ;
		{
			alert("Сумма возврата за услуги  в валюте контракта 1 должна быть меньше или равна сумме=" + P20t44);
		}
	}
	
	public void onChange$p11t45()
	{
		// PaymentRequest pm = ((PaymentRequest)p4t45.getObject());
		// System.out.println("pm.getP18t44()="+pm.getP18t44()+"current.getP8t45()="+current.getP8t45());
		if (P21t44 != null && (current.getP11t45() > P21t44)) ;
		{
			alert("Сумма возврата за услуги  в валюте контракта 2 должна быть меньше или равна сумме=" + P21t44);
		}
	}
	
	public void onChange$ap11t45()
	{
		// PaymentRequest pm = ((PaymentRequest)p4t45.getObject());
		// System.out.println("pm.getP18t44()="+pm.getP18t44()+"current.getP8t45()="+current.getP8t45());
		if (P21t44 != null && (ap11t45.doubleValue() > P21t44)) ;
		{
			alert("Сумма возврата за услуги  в валюте контракта 2 должна быть меньше или равна сумме=" + P21t44);
		}
	}
	
	private void setCurrent()
	{
		Movefromim mi = ((Movefromim) p13t45.getObject());
		Payment pm = ((Payment) p4t45.getObject());
		if (current != null)
		{
			if (pm != null)
			{
				p22t45.setValue("" + p6t45.doubleValue() / p7t45.doubleValue());
				val_opl.setValue(curr_.get(pm.getP15t44()));
				
				if (pm.getP15t44().equalsIgnoreCase(val1) || pm.getP15t44().equalsIgnoreCase(val2))
				{
					kur2.setVisible(true);
				}
				if (pm.getP18t44() != null || (mi.getP19t53() != null))
				{
					row_p8t45.setVisible(true);
					row_ap8t45.setVisible(true);
				}
				else
				{
					row_p8t45.setVisible(false);
					row_ap8t45.setVisible(false);
				}
				if (pm.getP19t44() != null || (mi.getP20t53() != null))
				{
					row_p9t45.setVisible(true);
					row_ap9t45.setVisible(true);
				}
				if (pm.getP20t44() != null || (mi.getP21t53() != null))
				{
					row_p10t45.setVisible(true);
					row_ap10t45.setVisible(true);
				}
				if (pm.getP21t44() != null || (mi.getP22t53() != null))
				{
					row_p11t45.setVisible(true);
					row_ap11t45.setVisible(true);
				}
				
				Payment_val = pm.getP15t44();
				System.out.println("Payment_val=" + Payment_val + "val1=" + val1 + "val2=" + val2);
				
				coursecurrencies = ContractService.getMyCurr_2_only(df.format(p2t45.getValue()), current.getP5t45(), Payment_val, alias);
				coursecurrencies2 = ContractService.getCourseCurr_18t1_19t1_withOther2(idn, idc, df.format(p2t45.getValue()), Payment_val, alias);
			}
			
			if (Payment_val != null && val1 != null && Payment_val.equalsIgnoreCase(val1) || (Payment_val != null && val2 != null && Payment_val.equalsIgnoreCase(val2)))
			{
				kur2.setVisible(true);
			}
			else
			{
				kur2.setVisible(false);
			}
			if (Mfromimp_val != null && Mfromimp_val.equalsIgnoreCase(val1) && val2.equals(null))
			{
				kur2.setVisible(false);
			}
			else if ((Mfromimp_val != null && Mfromimp_val.equalsIgnoreCase(val1)) || (Mfromimp_val != null && Mfromimp_val.equalsIgnoreCase(val2)))
			{
				kur2.setVisible(false);
			}
			
			p7t451.setModel((new ListModelList(coursecurrencies)));
			p7t453.setModel((new ListModelList(coursecurrencies)));
			p14t451.setModel((new ListModelList(coursecurrencies2)));
			p14t453.setModel((new ListModelList(coursecurrencies2)));
			if (current.getP24t45() != null)
			{
				row_p24t45.setVisible(true);
			}
			else
			{
				row_p24t45.setVisible(false);
			}
			if (current.getP12t45() != null)
			{
				row_p12t45.setVisible(true);
			}
			else
			{
				row_p12t45.setVisible(false);
			}
			if (current.getP19t45() != null)
			{
				row_p19t45.setVisible(true);
			}
			else
			{
				row_p19t45.setVisible(false);
			}
			if (current.getP5t45() != null)
			{
				val_vozv.setValue(curr_.get(current.getP5t45()));
			}
			
			if (current.getP4t45() != null)
			{
				p4t45.setModel(new ListModelList(payment));
			}
		}
	}
	
	public void refresh(String idn) throws Exception
	{
		
		final BankServiceProxy ws = new BankServiceProxy((String) session.getAttribute("YESVO_URL"));
		com.is.tf.refundimp.RefundimpService.remove(new Refundimp(), idc);
		com.sbs.service.RefundsImpResult Pay = ws.getRefundsImp(((String) (session.getAttribute("BankINN"))), idn);
		XMLSerializer.write(Pay, "c:/refi1.xml");
		try
		{
			if (Pay.getStatus() == 0)
			{
				for (int i = 0; i < Pay.getRefundsImp().length; i++)
				{
					com.is.tf.refundimp.RefundimpService.create(Pay.getRefundsImp()[i], idn, idc);
					
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
