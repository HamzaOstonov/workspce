package com.is.tf.payment;

import java.text.DecimalFormat;
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
import org.zkoss.zul.Row;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;
import org.zkoss.zul.event.PagingEvent;

import com.is.ISLogger;
import com.is.tf.Accreditiv.Accreditiv;
import com.is.tf.contract.ContractService;
import com.is.tf.currency.RefCurrencyBox;
import com.is.tf.currency.RefCurrencyData;
import com.is.tf.garant.Garant;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.RefData;
import com.is.utils.Res;
import com.is.utils.refobj.RefObjCBox;
import com.is.utils.refobj.RefObjData;
import com.sbs.service.BankServiceProxy;
import com.sbs.service.PaymentResult;

public class PaymentViewCtrl
		extends GenericForwardComposer
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
	private Toolbarbutton btn_back, btn_edit, btn_edit2, btn_delete, btn_confirm, btn_reject, btn_save, btn_confirmBuhg, btn_rejectBuhg;
	private Toolbar tb;
	private Long id_contract;
	private Textbox ap23t44, fp23t44, p23t44, p22t44, fp22t44, ap22t44, p27t44, p28t44, p29t44, p100t44, id, p1t44, p0t44, p4t44, p6t44, p11t44, p24t44;
	private Textbox ap1t44, aid_contract, ap27t44, ap28t44, ap29t44, ap100t44, aid, ap0t44, ap4t44, ap6t44, ap11t44, ap24t44;
	private Textbox fid_contract, fp27t44, fp28t44, fp29t44, fp100t44, fid, fp1t44, fp0t44, fp4t44, fp6t44, fp8t44, fp9t44, fp10t44, fp11t44, fp12t44, fp13t44, fp14t44, fp24t44;
	private Datebox p30t44, ap30t44, fp30t44, p2t44, p5t44, ap2t44, ap5t44, fp2t44, fp5t44;
	private Decimalbox ap17t442, p17t442, p16t44, p17t44, p18t44, p19t44, p20t44, p21t44, ap16t44, ap17t44, ap18t44, ap19t44, ap20t44, ap21t44, fp16t44, fp17t44, fp18t44, fp19t44, fp20t44, fp21t44;
	private RefCBox p12t44, ap12t44, p10t44, ap10t44, p9t44, ap9t44, p8t44, ap8t44, fp7t44, ap7t44, p7t44, p3t44, fp3t44, ap3t44, p15t44, ap15t44, fp15t44;
	private RefObjCBox p13t44, p14t44, ap13t44, ap14t44;
	private RefCurrencyBox p17t441, p17t443, ap17t441, ap17t443;
	private Label acbcourse, cbcourse, conr_val1, conr_val2, aconr_val2, aconr_val1, conr_val21, conr_val22, conr_aval21, conr_aval22;
	private Label line1, line2, line3, line4, line5, line6, line7, line8, line9, line10;
	private Row row_p30t44, row_p22t44, row_p4t44, row_p5t44, row_p6t44, row_p8t44, row_p9t44, row_p11t44, row_p10t44, row_p13t44, row_p14t44, row_p17t44, row_p18t44, row_p19t44, row_p20t44, row_p21t44, row_ap4t44, row_ap5t44, row_ap6t44,
			row_ap8t44, row_ap9t44, row_ap11t44, row_ap10t44, row_ap13t44, row_ap14t44, row_ap17t44, row_ap18t44, row_ap19t44, row_ap20t44, row_ap21t44;
	private Paging paymentPaging;
	private int _pageSize = 15;
	private int _startPageNumber = 0;
	private int _totalSize = 0;
	private boolean _needsTotalSizeUpdate = true;
	private HashMap<String, String> curr_ = null;
	private List<RefData> oplata = new ArrayList<RefData>();
	private List<RefData> Fundtype = new ArrayList<RefData>();
	private List<RefData> YesNo = new ArrayList<RefData>();
	private List<RefData> kod = new ArrayList<RefData>();
	private List<RefData> currencies = new ArrayList<RefData>();
	private List<RefData> gkod = new ArrayList<RefData>();
	private List<RefData> akod = new ArrayList<RefData>();
	private List<RefData> Conditions = new ArrayList<RefData>();
	private List<RefData> getCurr_22t1_23t1 = new ArrayList<RefData>();
	private List<RefData> getCurr_18t1_19t1 = new ArrayList<RefData>();
	private List<RefData> agetCurr_22t1_23t1 = new ArrayList<RefData>();
	private List<RefData> agetCurr_18t1_19t1 = new ArrayList<RefData>();
	private List<RefData> Loantype = new ArrayList<RefData>();
	private List<RefData> istochnik_sredstv = new ArrayList<RefData>();
	private List<RefCurrencyData> coursecurrencies = new ArrayList<RefCurrencyData>();
	private List<RefCurrencyData> acoursecurrencies = new ArrayList<RefCurrencyData>();
	private List<RefObjData> accred = new ArrayList<RefObjData>();
	private List<RefObjData> garant = new ArrayList<RefObjData>();
	private String otn2, aotn, otn, summa1, summa2, subj, fund_val, afund_val, idn, val1, val2, val22, val23, Acred_val, Acred_aval, Acred, aAcred_val, Gar_val, Gar_aval;
	private String alias;
	private String sparam1, sparam;
	private long gid, idc;
	
	public PaymentFilter filter = new PaymentFilter();
	
	PagingListModel model = null;
	ListModelList lmodel = null;
	private AnnotateDataBinder binder;
	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	private DecimalFormat nf = new DecimalFormat("####.###");
	
	private Payment current = new Payment();
	
	public PaymentViewCtrl()
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
		curr_ = com.is.tf.contract.ContractService.getHCurr(alias);
		parameter = (String[]) param.get("idn");
		if (parameter != null)
		{
			idn = (parameter[0]);
			
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
		parameter = (String[]) param.get("val22");
		if (parameter != null)
		{
			val22 = (parameter[0]);
			// System.out.println("Garant  cont_val2 "+val2);
		}
		parameter = (String[]) param.get("val23");
		if (parameter != null)
		{
			val23 = (parameter[0]);
			// System.out.println("Garant  cont_val2 "+val2);
		}
		parameter = (String[]) param.get("subj");
		if (parameter != null)
		{
			subj = (parameter[0]);
			// System.out.println("Garant  cont_val2 "+val2);
		}
		parameter = (String[]) param.get("idc");
		if (parameter != null)
		{
			idc = Long.parseLong((parameter[0]));
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
		filter.setP1t44(idn);
		filter.setId_contract(idc);
		conr_val2.setValue(curr_.get(val2));
		conr_val1.setValue(curr_.get(val1));
		aconr_val2.setValue(curr_.get(val2));
		aconr_val1.setValue(curr_.get(val1));
		conr_val21.setValue(curr_.get(val1));
		conr_val22.setValue(curr_.get(val2));
		conr_aval21.setValue(curr_.get(val1));
		conr_aval22.setValue(curr_.get(val2));
		
		// alert("sparam1= "+sparam1+"sparam= "+sparam);
		line1.setValue(Labels.getLabel("payment.p2t44").replaceAll("<br>", "\r\n"));
		line2.setValue(Labels.getLabel("payment.p7t44").replaceAll("<br>", "\r\n"));
		line3.setValue(Labels.getLabel("payment.p12t44").replaceAll("<br>", "\r\n"));
		line4.setValue(Labels.getLabel("payment.p16t44").replaceAll("<br>", "\r\n"));
		line5.setValue(Labels.getLabel("payment.p15t44").replaceAll("<br>", "\r\n"));
		line6.setValue(Labels.getLabel("payment.p23t44tab").replaceAll("<br>", "\r\n"));
		line7.setValue(Labels.getLabel("payment.p22t44tab").replaceAll("<br>", "\r\n"));
		line8.setValue(Labels.getLabel("payment.p24t44tab").replaceAll("<br>", "\r\n"));
		line9.setValue(Labels.getLabel("payment.p30t44tab").replaceAll("<br>", "\r\n"));
		line10.setValue(Labels.getLabel("payment.p100t44tab").replaceAll("<br>", "\r\n"));
		
		dataGrid.setItemRenderer(new ListitemRenderer()
		{
			@SuppressWarnings("unchecked")
			public void render(Listitem row, Object data) throws Exception
			{
				Payment pPayment = (Payment) data;
				
				row.setValue(pPayment);
				row.appendChild(new Listcell(pPayment.getP2t44() + ""));
				row.appendChild(new Listcell(com.is.tf.contract.SPR.getIstochn_sredstv(String.valueOf(pPayment.getP7t44()))));
				row.appendChild(new Listcell(com.is.tf.contract.SPR.getConditions(String.valueOf(pPayment.getP12t44()))));
				row.appendChild(new Listcell(pPayment.getP16t44() + ""));
				row.appendChild(new Listcell(com.is.tf.contract.SPR.getVal(String.valueOf(pPayment.getP15t44()))));
				row.appendChild(new Listcell(pPayment.getP23t44() + ""));
				row.appendChild(new Listcell(pPayment.getP22t44() + ""));
				row.appendChild(new Listcell(pPayment.getP24t44()));
				row.appendChild(new Listcell(pPayment.getP30t44() + ""));
				row.appendChild(new Listcell(com.is.tf.contract.SPR.getP100Value(String.valueOf(pPayment.getP100t44()))));
				
				/*
				 * row.appendChild(new Listcell(pPayment.getId()+""));
				 * row.appendChild(new Listcell(pPayment.getP1t44()));
				 * row.appendChild(new Listcell(pPayment.getP0t44()));
				 * row.appendChild(new Listcell(pPayment.getP3t44()));
				 * row.appendChild(new Listcell(pPayment.getP4t44()));
				 * row.appendChild(new Listcell(pPayment.getP5t44()+""));
				 * row.appendChild(new Listcell(pPayment.getP6t44()));
				 * row.appendChild(new Listcell(pPayment.getP8t44()));
				 * row.appendChild(new Listcell(pPayment.getP9t44()));
				 * row.appendChild(new Listcell(pPayment.getP10t44()));
				 * row.appendChild(new Listcell(pPayment.getP11t44()));
				 * row.appendChild(new Listcell(pPayment.getP13t44()));
				 * row.appendChild(new Listcell(pPayment.getP14t44()));
				 * row.appendChild(new Listcell(pPayment.getP17t44()+""));
				 * row.appendChild(new Listcell(pPayment.getP18t44()+""));
				 * row.appendChild(new Listcell(pPayment.getP19t44()+""));
				 * row.appendChild(new Listcell(pPayment.getP20t44()+""));
				 * row.appendChild(new Listcell(pPayment.getP21t44()+""));
				 * row.appendChild(new Listcell(pPayment.getId_contract()+""));
				 * row.appendChild(new Listcell(pPayment.getP27t44()));
				 * row.appendChild(new Listcell(pPayment.getP28t44()));
				 * row.appendChild(new Listcell(pPayment.getP29t44()));
				 */
			}
		});
		oplata = ContractService.getPaymentSourse("1,2,4", alias);
		istochnik_sredstv = ContractService.getFundSourse("1,2,3", alias);
		Fundtype = ContractService.getFundtype("1,2,3,4", alias);
		YesNo = ContractService.getYesNo();
		Loantype = ContractService.getLoantype("1,2,3,4,5,6", alias);
		Conditions = ContractService.getConditions("1,2,7,9,10", alias);
		accred = com.is.tf.contract.ContractService.getAccredObj(idn, alias);
		garant = com.is.tf.contract.ContractService.getGarant(idn, alias);
		currencies = com.is.tf.contract.ContractService.getMyCurr(idn, alias);
		
		p3t44.setModel((new ListModelList(oplata)));
		ap3t44.setModel((new ListModelList(oplata)));
		p7t44.setModel((new ListModelList(istochnik_sredstv)));
		ap7t44.setModel((new ListModelList(istochnik_sredstv)));
		p8t44.setModel((new ListModelList(Fundtype)));
		ap8t44.setModel((new ListModelList(Fundtype)));
		p9t44.setModel((new ListModelList(Loantype)));
		ap9t44.setModel((new ListModelList(Loantype)));
		p12t44.setModel((new ListModelList(Conditions)));
		ap12t44.setModel((new ListModelList(Conditions)));
		p10t44.setModel((new ListModelList(YesNo)));
		ap10t44.setModel((new ListModelList(YesNo)));
		p13t44.setModel((new ListModelList(accred)));
		ap13t44.setModel((new ListModelList(accred)));
		p14t44.setModel((new ListModelList(garant)));
		ap14t44.setModel((new ListModelList(garant)));
		p15t44.setModel((new ListModelList(currencies)));
		ap15t44.setModel((new ListModelList(currencies)));
		
		refreshModel(_startPageNumber);
		
	}
	
	public void onPaging$paymentPaging(ForwardEvent event)
	{
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModel(_startPageNumber);
	}
	
	private void refreshModel(int activePage)
	{
		paymentPaging.setPageSize(_pageSize);
		model = new PagingListModel(activePage, _pageSize, filter, "");
		
		if (_needsTotalSizeUpdate)
		{
			_totalSize = model.getTotalSize(filter, "");
			// _needsTotalSizeUpdate = false;
		}
		
		paymentPaging.setTotalSize(_totalSize);
		
		dataGrid.setModel((ListModel) model);
		if (model.getSize() > 0)
		{
			this.current = (Payment) model.getElementAt(0);
			sendSelEvt();
		}
	}
	
	// Omitted...
	public Payment getCurrent()
	{
		return current;
	}
	
	public void setCurrent(Payment current)
	{
		this.current = current;
	}
	
	public void onDoubleClick$dataGrid$grd()
	{
		
		if (sparam1 != null)
		{
			if (sparam1.equals("Filial")) // / ������
			
			{
				btn_edit.setVisible(true);
				btn_edit2.setVisible(false);
				btn_confirmBuhg.setVisible(false);
				btn_rejectBuhg.setVisible(false);
				sparam1 = "Filial";
				
				// alert(sparam1);
			}
			else if (sparam1.equals("Go")) // / ��
			{
				btn_edit.setVisible(false);
				btn_edit2.setVisible(true);
				btn_confirmBuhg.setVisible(false);
				btn_rejectBuhg.setVisible(false);
				sparam1 = "Go";
				// alert(sparam1);
			}
			else if (sparam1.equals("GlBuhg")) // / �� ����
			{
				btn_edit.setVisible(false);
				btn_edit2.setVisible(false);
				btn_confirmBuhg.setVisible(true);
				btn_rejectBuhg.setVisible(true);
				sparam1 = "GlBuh";
				// alert(sparam1);
			}
		}
		grd.setVisible(false);
		frm.setVisible(true);
		frmgrd.setVisible(true);
		addgrd.setVisible(false);
		fgrd.setVisible(false);
		// btn_edit.setVisible(true);
		// btn_edit2.setVisible(true);
		// btn_confirmBuhg.setVisible(true);
		// btn_rejectBuhg.setVisible(true);
		btn_back.setImage("/images/folder.png");
		btn_back.setLabel(Labels.getLabel("grid"));
		// setCurrent();
		p2t44.setDisabled(true);
		p3t44.setDisabled(true);
		p4t44.setReadonly(true);
		p5t44.setDisabled(true);
		p6t44.setReadonly(true);
		p7t44.setDisabled(true);
		p8t44.setDisabled(true);
		p9t44.setDisabled(true);
		p10t44.setDisabled(true);
		p11t44.setReadonly(true);
		p12t44.setDisabled(true);
		p13t44.setDisabled(true);
		p14t44.setDisabled(true);
		p15t44.setDisabled(true);
		p16t44.setReadonly(true);
		p17t442.setReadonly(true);
		p18t44.setReadonly(true);
		p19t44.setReadonly(true);
		p20t44.setReadonly(true);
		p21t44.setReadonly(true);
		p23t44.setReadonly(true);
		setCurrentAdd();
		setCurrent();
	}
	
	public void onClick$btn_edit()
	{
		
		if (frmgrd.isVisible())
		{
			btn_save.setVisible(true);
			btn_delete.setVisible(true);
			btn_confirm.setVisible(false);
			btn_reject.setVisible(false);
			// btn_edit2.setVisible(false);
			p2t44.setDisabled(false);
			p3t44.setDisabled(false);
			p4t44.setReadonly(false);
			p5t44.setDisabled(false);
			p6t44.setReadonly(false);
			p7t44.setDisabled(false);
			p8t44.setDisabled(false);
			p9t44.setDisabled(false);
			p10t44.setDisabled(false);
			p11t44.setReadonly(false);
			p12t44.setDisabled(false);
			p13t44.setDisabled(false);
			p14t44.setDisabled(false);
			p15t44.setDisabled(false);
			p16t44.setReadonly(false);
			p17t442.setReadonly(false);
			p18t44.setReadonly(false);
			p19t44.setReadonly(false);
			p20t44.setReadonly(false);
			p21t44.setReadonly(false);
			p23t44.setReadonly(false);
		}
		else if (addgrd.isVisible())
		{
			btn_save.setVisible(true);
			btn_delete.setVisible(true);
			btn_confirm.setVisible(false);
			btn_reject.setVisible(false);
		}
	}
	
	public void onClick$btn_edit2()
	{
		
		if (frmgrd.isVisible())
		{
			btn_save.setVisible(false);
			btn_delete.setVisible(false);
			btn_confirm.setVisible(true);
			btn_reject.setVisible(true);
			btn_edit.setVisible(false);
			p2t44.setDisabled(true);
			p3t44.setDisabled(true);
			p4t44.setReadonly(true);
			p5t44.setDisabled(true);
			p6t44.setReadonly(true);
			p7t44.setDisabled(true);
			p8t44.setDisabled(true);
			p9t44.setDisabled(true);
			p10t44.setDisabled(true);
			p11t44.setReadonly(true);
			p12t44.setDisabled(true);
			p13t44.setDisabled(true);
			p14t44.setDisabled(true);
			p15t44.setDisabled(true);
			p16t44.setReadonly(true);
			p17t442.setReadonly(true);
			p18t44.setReadonly(true);
			p19t44.setReadonly(true);
			p20t44.setReadonly(true);
			p21t44.setReadonly(true);
			p23t44.setReadonly(true);
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
		ap1t44.setValue(idn);
		ap24t44.setValue((String) session.getAttribute("un"));
		// ap29t44.setValue((String)session.getAttribute("un"));
		frmgrd.setVisible(false);
		addgrd.setVisible(true);
		fgrd.setVisible(false);
		btn_save.setVisible(true);
		btn_edit.setVisible(false);
		btn_edit2.setVisible(false);
	}
	
	public void onClick$btn_search()
	{
		onDoubleClick$dataGrid$grd();
		frmgrd.setVisible(false);
		addgrd.setVisible(false);
		fgrd.setVisible(true);
		btn_save.setVisible(true);
		btn_edit.setVisible(false);
		btn_edit2.setVisible(false);
	}
	
	public void onClick$btn_delete()
	{
		try
		{
			final BankServiceProxy ws = new BankServiceProxy((String) session.getAttribute("YESVO_URL"));
			com.sbs.service.PaymentResult pay = new com.sbs.service.PaymentResult();
			if (!CheckNull.isEmpty(p16t44.doubleValue()) && (Double.parseDouble(summa1) > p16t44.doubleValue()))
			{
				otn = "0";
			}
			else if (!CheckNull.isEmpty(p16t44.doubleValue()) && (Double.parseDouble(summa1) < p16t44.doubleValue()))
			{
				otn = "1";
			}
			current.setP2t44(p2t44.getValue());
			current.setP3t44(p3t44.getValue());
			current.setP4t44(p4t44.getValue());
			current.setP5t44(p5t44.getValue());
			current.setP6t44(p6t44.getValue());
			current.setP7t44(p7t44.getValue());
			current.setP8t44(p8t44.getValue());
			current.setP9t44(p9t44.getValue());
			current.setP10t44(p10t44.getValue());
			current.setP11t44(p11t44.getValue());
			current.setP12t44(p12t44.getValue());
			current.setP13t44(p13t44.getValue());
			current.setP14t44(p14t44.getValue());
			current.setP15t44(p15t44.getValue());
			current.setP16t44(p16t44.doubleValue());
			current.setP17t44(p17t442.doubleValue());
			current.setP18t44(p18t44.doubleValue());
			current.setP19t44(p19t44.doubleValue());
			current.setP20t44(p20t44.doubleValue());
			current.setP21t44(p21t44.doubleValue());
			current.setP22t44(p22t44.getValue());
			current.setP23t44(p23t44.getValue());
			// current.setP24t44((String)session.getAttribute("un"));
			if (current.getP100t44().equals("9"))
			{
				Res res = PaymentService.removeBugh(current, idc);
				if (res.getCode() == 0)
				{
					alert("�������� ��������� �������");
					refreshModel(_startPageNumber);
				}
				else
				{
					alert("������ ��������:" + res.getName() + ":" + res.getCode());
				}
			}
			else
			{
				Res res = PaymentService.updateDel(current);
				
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
		id_contract = idc;
		try
		{
			if (addgrd.isVisible())
			{
				
				PaymentService.createBuhg(new Payment(
						ap2t44.getValue(),
						ap3t44.getValue(),
						ap4t44.getValue(),
						ap5t44.getValue(),
						ap6t44.getValue(),
						ap7t44.getValue(),
						ap8t44.getValue(),
						ap9t44.getValue(),
						ap10t44.getValue(),
						ap11t44.getValue(),
						ap12t44.getValue(),
						ap13t44.getValue(),
						ap14t44.getValue(),
						ap15t44.getValue(),
						ap16t44.doubleValue(),
						ap17t442.doubleValue(),
						ap18t44.doubleValue(),
						ap19t44.doubleValue(),
						ap20t44.doubleValue(),
						ap21t44.doubleValue(),
						// ap22t44.doubleValue(),
						ap23t44.getValue(),
						ap24t44.getValue()
						// Long.parseLong(aid_contract.getValue()),
						// ap27t44.getValue()
						// ap28t44.getValue(),
						// ap29t44.getValue()
						), idn, id_contract);
				
				refreshModel(_startPageNumber);
				alert("���������� �������");
				
				frmgrd.setVisible(true);
				addgrd.setVisible(false);
				fgrd.setVisible(false);
				
			}
			else
			{
				if (!CheckNull.isEmpty(p16t44.doubleValue()) && (Double.parseDouble(summa1) > p16t44.doubleValue()))
				{
					otn = "0";
				}
				else if (!CheckNull.isEmpty(p16t44.doubleValue()) && (Double.parseDouble(summa1) < p16t44.doubleValue()))
				{
					otn = "1";
				}
				// alert("summa1="+summa1+"  p16t44.doubleValue()= "+p16t44.doubleValue()+"  otn= "+otn+" p15t44.getValue()="+current.getP15t44());
				Long.parseLong(id.getValue());
				// current.setP1t44(p1t44.getValue());
				// current.setP0t44(p0t44.getValue());
				current.setP2t44(p2t44.getValue());
				current.setP3t44(p3t44.getValue());
				current.setP4t44(p4t44.getValue());
				current.setP5t44(p5t44.getValue());
				current.setP6t44(p6t44.getValue());
				current.setP7t44(p7t44.getValue());
				current.setP8t44(p8t44.getValue());
				current.setP9t44(p9t44.getValue());
				current.setP10t44(p10t44.getValue());
				current.setP11t44(p11t44.getValue());
				current.setP12t44(p12t44.getValue());
				current.setP13t44(p13t44.getValue());
				current.setP14t44(p14t44.getValue());
				current.setP15t44(p15t44.getValue());
				current.setP16t44(p16t44.doubleValue());
				current.setP17t44(p17t442.doubleValue());
				current.setP18t44(p18t44.doubleValue());
				current.setP19t44(p19t44.doubleValue());
				current.setP20t44(p20t44.doubleValue());
				current.setP21t44(p21t44.doubleValue());
				current.setP22t44(p22t44.getValue());
				current.setP23t44(p23t44.getValue());
				current.setP24t44((String) session.getAttribute("un"));
				Res res = PaymentService.update(current);
				if (res.getCode() == 0)
				{
					alert("������������� ��������� �������");
					paymentPaging.setActivePage(0);
					_startPageNumber = 0;
					refreshModel(_startPageNumber);
					SelectEvent evt = new SelectEvent("onSelect", dataGrid, dataGrid.getSelectedItems());
					Events.sendEvent(evt);
				}
				else
				{
					alert("������:" + res.getName());
				}
				
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("Error save   " + e + "id_contract " + id_contract);
		}
	}
	
	public void onClick$btn_confirmBuhg()
	{
		try
		{
			final BankServiceProxy ws = new BankServiceProxy((String) session.getAttribute("YESVO_URL"));
			com.sbs.service.PaymentResult pay = new com.sbs.service.PaymentResult();
			/*
			 * if(addgrd.isVisible()){ PaymentResult pm =
			 * ws.savePayment(((String)(session.getAttribute("BankINN"))), idn ,
			 * getAddPaym(new PaymentRequest( //Long.parseLong(aid.getValue()),
			 * //ap1t44.getValue(), //ap0t44.getValue(), ap2t44.getValue(),
			 * ap3t44.getValue(), ap4t44.getValue(), ap5t44.getValue(),
			 * ap6t44.getValue(), ap7t44.getValue(), ap8t44.getValue(),
			 * ap9t44.getValue(), ap10t44.getValue(), ap11t44.getValue(),
			 * ap12t44.getValue(), ap13t44.getValue(), ap14t44.getValue(),
			 * ap15t44.getValue(), ap16t44.doubleValue(),
			 * ap17t442.doubleValue(), ap18t44.doubleValue(),
			 * ap19t44.doubleValue(), ap20t44.doubleValue(),
			 * ap21t44.doubleValue(), //ap22t44.doubleValue(),
			 * //ap23t44.doubleValue(), ap24t44.getValue(),
			 * //Long.parseLong(aid_contract.getValue()), //ap27t44.getValue(),
			 * ap28t44.getValue() //ap29t44.getValue() //ap30t44.getValue(),
			 * //ap100t44.getValue() ))); if
			 * (CheckNull.isEmpty(ap16t44.doubleValue
			 * ())&&(Double.parseDouble(summa1)>ap16t44.doubleValue())){
			 * aotn="0"; } else if
			 * (CheckNull.isEmpty(ap16t44.doubleValue())&&(Double
			 * .parseDouble(summa1)<ap16t44.doubleValue())){ aotn="1"; } if
			 * (pm.getStatus() == 0) { refreshModel(_startPageNumber);
			 * alert("���������� �������"); } else {
			 * alert("Error save New payment; Status:"+pm.getStatus()+"; GTKid:"
			 * +pm.getGtkId()+ "; Text:" +pm.getErrorMsg());
			 * System.out.println("Error save New payment; Status:"
			 * +pm.getStatus()+"; GTKid:" +pm.getGtkId()+ "; Text:"
			 * +pm.getErrorMsg());
			 * ISLogger.getLogger().error(" in save New payment:"
			 * +pm.getStatus()+"; GTKid:" +pm.getGtkId()+ "; Text:"
			 * +pm.getErrorMsg()); } CheckNull.clearForm(addgrd);
			 * frmgrd.setVisible(true); addgrd.setVisible(false);
			 * fgrd.setVisible(false); } else if(fgrd.isVisible()){ filter = new
			 * PaymentFilter(); Long.parseLong(fid.getValue());
			 * filter.setP1t44(fp1t44.getValue());
			 * filter.setP0t44(fp0t44.getValue());
			 * filter.setP2t44(fp2t44.getValue());
			 * filter.setP3t44(fp3t44.getValue());
			 * filter.setP4t44(fp4t44.getValue());
			 * filter.setP5t44(fp5t44.getValue());
			 * filter.setP6t44(fp6t44.getValue());
			 * filter.setP7t44(fp7t44.getValue());
			 * filter.setP8t44(fp8t44.getValue());
			 * filter.setP9t44(fp9t44.getValue());
			 * filter.setP10t44(fp10t44.getValue());
			 * filter.setP11t44(fp11t44.getValue());
			 * filter.setP12t44(fp12t44.getValue());
			 * filter.setP13t44(fp13t44.getValue());
			 * filter.setP14t44(fp14t44.getValue());
			 * filter.setP15t44(fp15t44.getValue());
			 * filter.setP16t44(fp16t44.doubleValue());
			 * filter.setP17t44(fp17t44.doubleValue());
			 * filter.setP18t44(fp18t44.doubleValue());
			 * filter.setP19t44(fp19t44.doubleValue());
			 * filter.setP20t44(fp20t44.doubleValue());
			 * filter.setP21t44(fp21t44.doubleValue());
			 * filter.setP22t44(fp22t44.getValue());
			 * //filter.setP23t44(fp23t44.getValue());
			 * filter.setP24t44(fp24t44.getValue());
			 * //filter.setId_contract(fid_contract.getValue());
			 * filter.setP27t44(fp27t44.getValue());
			 * filter.setP28t44(fp28t44.getValue());
			 * filter.setP29t44(fp29t44.getValue());
			 * filter.setP30t44(fp30t44.getValue());
			 * filter.setP100t44(fp100t44.getValue());
			 */
			if (frmgrd.isVisible() && (current.getP22t44() != null))
			{
				System.out.println("������������� ������");
				if (!CheckNull.isEmpty(p16t44.doubleValue()) && (Double.parseDouble(summa1) > p16t44.doubleValue()))
				{
					otn = "0";
				}
				else if (!CheckNull.isEmpty(p16t44.doubleValue()) && (Double.parseDouble(summa1) < p16t44.doubleValue()))
				{
					otn = "1";
				}
				// alert("summa1="+summa1+"  p16t44.doubleValue()= "+p16t44.doubleValue()+"  otn= "+otn+" p15t44.getValue()="+current.getP15t44());
				Long.parseLong(id.getValue());
				// current.setP1t44(p1t44.getValue());
				// current.setP0t44(p0t44.getValue());
				current.setP2t44(p2t44.getValue());
				current.setP3t44(p3t44.getValue());
				current.setP4t44(p4t44.getValue());
				current.setP5t44(p5t44.getValue());
				current.setP6t44(p6t44.getValue());
				current.setP7t44(p7t44.getValue());
				current.setP8t44(p8t44.getValue());
				current.setP9t44(p9t44.getValue());
				current.setP10t44(p10t44.getValue());
				current.setP11t44(p11t44.getValue());
				current.setP12t44(p12t44.getValue());
				current.setP13t44(p13t44.getValue());
				current.setP14t44(p14t44.getValue());
				current.setP15t44(p15t44.getValue());
				current.setP16t44(p16t44.doubleValue());
				current.setP17t44(p17t442.doubleValue());
				current.setP18t44(p18t44.doubleValue());
				current.setP19t44(p19t44.doubleValue());
				current.setP20t44(p20t44.doubleValue());
				current.setP21t44(p21t44.doubleValue());
				current.setP22t44(p22t44.getValue());
				current.setP23t44(p23t44.getValue());
				current.setP24t44(p24t44.getValue());
				// PaymentService.update(current);
				PaymentResult ar = ws.savePayment(((String) (session.getAttribute("BankINN"))), idn, getCorrectPaymBuhg(current));
				if (ar.getStatus() == 0)
				{
					refreshModel(_startPageNumber);
					alert("���������� ������������� �������");
					com.is.tf.payment.PaymentService.removeBugh(new Payment(), id_contract);
					
				}
				else
				{
					alert("Error correcting : " + ar.getStatus() + "; GTKid:" + ar.getGtkId() + "; Text:" + ar.getErrorMsg());
					System.out.println("Error correct payment; Status:" + ar.getStatus() + "; GTKid:" + ar.getGtkId() + "; Text:" + ar.getErrorMsg());
					ISLogger.getLogger().error(" in correct payment:" + ar.getStatus() + "; GTKid:" + ar.getGtkId() + "; Text:" + ar.getErrorMsg());
					
				}
			}
			else if (frmgrd.isVisible() && (current.getP22t44() != null) && (current.getP0t44().equals(2)))
			{
				System.out.println("�������� ������");
				if (!CheckNull.isEmpty(p16t44.doubleValue()) && (Double.parseDouble(summa1) > p16t44.doubleValue()))
				{
					otn = "0";
				}
				else if (!CheckNull.isEmpty(p16t44.doubleValue()) && (Double.parseDouble(summa1) < p16t44.doubleValue()))
				{
					otn = "1";
				}
				// alert("summa1="+summa1+"  p16t44.doubleValue()= "+p16t44.doubleValue()+"  otn= "+otn+" p15t44.getValue()="+current.getP15t44());
				Long.parseLong(id.getValue());
				// current.setP1t44(p1t44.getValue());
				// current.setP0t44(p0t44.getValue());
				current.setP2t44(p2t44.getValue());
				current.setP3t44(p3t44.getValue());
				current.setP4t44(p4t44.getValue());
				current.setP5t44(p5t44.getValue());
				current.setP6t44(p6t44.getValue());
				current.setP7t44(p7t44.getValue());
				current.setP8t44(p8t44.getValue());
				current.setP9t44(p9t44.getValue());
				current.setP10t44(p10t44.getValue());
				current.setP11t44(p11t44.getValue());
				current.setP12t44(p12t44.getValue());
				current.setP13t44(p13t44.getValue());
				current.setP14t44(p14t44.getValue());
				current.setP15t44(p15t44.getValue());
				current.setP16t44(p16t44.doubleValue());
				current.setP17t44(p17t442.doubleValue());
				current.setP18t44(p18t44.doubleValue());
				current.setP19t44(p19t44.doubleValue());
				current.setP20t44(p20t44.doubleValue());
				current.setP21t44(p21t44.doubleValue());
				current.setP22t44(p22t44.getValue());
				current.setP23t44(p23t44.getValue());
				current.setP24t44(p24t44.getValue());
				// PaymentService.update(current);
				PaymentResult ar = ws.savePayment(((String) (session.getAttribute("BankINN"))), idn, getCurrPaymDelete(current));
				if (ar.getStatus() == 0)
				{
					refreshModel(_startPageNumber);
					alert("���������� ������������� �������");
					com.is.tf.payment.PaymentService.removeBugh(new Payment(), id_contract);
					
				}
				else
				{
					alert("Error correcting : " + ar.getStatus() + "; GTKid:" + ar.getGtkId() + "; Text:" + ar.getErrorMsg());
					System.out.println("Error correct payment; Status:" + ar.getStatus() + "; GTKid:" + ar.getGtkId() + "; Text:" + ar.getErrorMsg());
					ISLogger.getLogger().error(" in correct payment:" + ar.getStatus() + "; GTKid:" + ar.getGtkId() + "; Text:" + ar.getErrorMsg());
					
				}
				
			}
			else
			{
				alert("���������� ������" + " ������ ������=" + current.getP15t44());
				if (!CheckNull.isEmpty(p16t44.doubleValue()) && (Double.parseDouble(summa1) > p16t44.doubleValue()))
				{
					otn = "0";
				}
				else if (!CheckNull.isEmpty(p16t44.doubleValue()) && (Double.parseDouble(summa1) < p16t44.doubleValue()))
				{
					otn = "1";
				}
				// alert("summa1="+summa1+"  p16t44.doubleValue()= "+p16t44.doubleValue()+"  otn= "+otn+" p15t44.getValue()="+current.getP15t44());
				Long.parseLong(id.getValue());
				// current.setP1t44(p1t44.getValue());
				// current.setP0t44(p0t44.getValue());
				current.setP2t44(p2t44.getValue());
				current.setP3t44(p3t44.getValue());
				current.setP4t44(p4t44.getValue());
				current.setP5t44(p5t44.getValue());
				current.setP6t44(p6t44.getValue());
				current.setP7t44(p7t44.getValue());
				current.setP8t44(p8t44.getValue());
				current.setP9t44(p9t44.getValue());
				current.setP10t44(p10t44.getValue());
				current.setP11t44(p11t44.getValue());
				current.setP12t44(p12t44.getValue());
				current.setP13t44(p13t44.getValue());
				current.setP14t44(p14t44.getValue());
				current.setP15t44(p15t44.getValue());
				current.setP16t44(p16t44.doubleValue());
				current.setP17t44(p17t442.doubleValue());
				current.setP18t44(p18t44.doubleValue());
				current.setP19t44(p19t44.doubleValue());
				current.setP20t44(p20t44.doubleValue());
				current.setP21t44(p21t44.doubleValue());
				// current.setP22t44(p22t44.getValue());
				current.setP23t44(p23t44.getValue());
				current.setP24t44(p24t44.getValue());
				// PaymentService.update(current);
				PaymentResult ar = ws.savePayment(((String) (session.getAttribute("BankINN"))), idn, getCurrPaymBuhg(current));
				if (ar.getStatus() == 0)
				{
					refreshModel(_startPageNumber);
					alert("���������� �������");
					com.is.tf.payment.PaymentService.removeBugh(new Payment(), id_contract);
					
				}
				else
				{
					alert("Error payment : " + ar.getStatus() + "; GTKid:" + ar.getGtkId() + "; Text:" + ar.getErrorMsg());
					System.out.println("Error  payment; Status:" + ar.getStatus() + "; GTKid:" + ar.getGtkId() + "; Text:" + ar.getErrorMsg());
					ISLogger.getLogger().error(" in  payment:" + ar.getStatus() + "; GTKid:" + ar.getGtkId() + "; Text:" + ar.getErrorMsg());
					
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
			filter = new PaymentFilter();
		}
		onClick$btn_back();
		frmgrd.setVisible(true);
		addgrd.setVisible(false);
		fgrd.setVisible(false);
		CheckNull.clearForm(addgrd);
		CheckNull.clearForm(fgrd);
		refreshModel(_startPageNumber);
	}
	
	private com.sbs.service.Payment getCurrPaymBuhg(Payment acr) throws Exception
	{
		java.util.Calendar cal = java.util.Calendar.getInstance();
		java.util.Calendar cal2 = java.util.Calendar.getInstance();
		com.sbs.service.Payment res = new com.sbs.service.Payment();
		res.setP0T44(0);
		cal.setTime(df.parse(df.format(acr.getP2t44())));
		res.setP2T44(cal);
		res.setP3T44(Short.parseShort(acr.getP3t44()));
		if ((acr.getP4t44() != null) && (!CheckNull.isEmpty(acr.getP4t44())))
		{
			res.setP4T44(acr.getP4t44());
		}
		if (acr.getP5t44() != null)
		{
			cal2.setTime(df.parse(df.format(acr.getP5t44())));
			res.setP5T44(cal2);
		}
		if ((acr.getP6t44() != null) && (!CheckNull.isEmpty(acr.getP6t44())))
		{
			res.setP6T44(acr.getP6t44());
		}
		if ((acr.getP7t44() != null) && (!CheckNull.isEmpty(acr.getP7t44())))
		{
			res.setP7T44(Short.parseShort(acr.getP7t44()));
		}
		if ((acr.getP8t44() != null) && (!CheckNull.isEmpty(acr.getP8t44())))
		{
			res.setP8T44(Short.parseShort(acr.getP8t44()));
		}
		if ((acr.getP9t44() != null) && (!CheckNull.isEmpty(acr.getP9t44())))
		{
			res.setP9T44(Short.parseShort(acr.getP9t44()));
		}
		if ((acr.getP10t44() != null) && (!CheckNull.isEmpty(acr.getP10t44())))
		{
			res.setP10T44(Short.parseShort(acr.getP10t44()));
		}
		if ((acr.getP11t44() != null) && (!CheckNull.isEmpty(acr.getP11t44())))
		{
			res.setP11T44(acr.getP11t44());
		}
		if ((acr.getP12t44() != null) && (!CheckNull.isEmpty(acr.getP12t44())))
		{
			res.setP12T44(Short.parseShort(acr.getP12t44()));
		}
		if ((acr.getP13t44() != null) && (!CheckNull.isEmpty(acr.getP13t44())))
		{
			res.setP13T44(acr.getP13t44());
		}
		
		if ((acr.getP14t44() != null) && (!CheckNull.isEmpty(acr.getP14t44())))
		{
			res.setP14T44(acr.getP14t44());
		}
		
		res.setP15T44(acr.getP15t44());
		
		if ((acr.getP16t44() != null) && (!CheckNull.isEmpty(acr.getP16t44())))
		{
			res.setP16T44(acr.getP16t44());
		}
		if (acr.getP17t44() != null)
		{
			res.setP17T44(acr.getP17t44());
		}
		if (acr.getP18t44() != null)
		{
			res.setP18T44(acr.getP18t44());
		}
		if (acr.getP6t44() != null)
		{
			res.setP19T44(acr.getP19t44());
		}
		if (acr.getP20t44() != null)
		{
			res.setP20T44(acr.getP20t44());
		}
		if (acr.getP21t44() != null)
		{
			res.setP21T44(acr.getP21t44());
		}
		// res.setP22T44(Integer.parseInt(acr.getP22t44()));
		res.setP23T44(acr.getP23t44());
		res.setP24T44(acr.getP24t44());
		res.setP27T44(Short.parseShort(otn));
		res.setP29T44((String) session.getAttribute("un"));
		alert("getP15t44()=" + acr.getP15t44() + " current.getP15t44()=" + current.getP15t44());
		return res;
		
	}
	
	private com.sbs.service.Payment getCorrectPaymBuhg(Payment acr) throws Exception
	{
		java.util.Calendar cal = java.util.Calendar.getInstance();
		java.util.Calendar cal2 = java.util.Calendar.getInstance();
		com.sbs.service.Payment res = new com.sbs.service.Payment();
		res.setP0T44(1);
		cal.setTime(df.parse(df.format(acr.getP2t44())));
		res.setP2T44(cal);
		res.setP3T44(Short.parseShort(acr.getP3t44()));
		if ((acr.getP4t44() != null) && (!CheckNull.isEmpty(acr.getP4t44())))
		{
			res.setP4T44(acr.getP4t44());
		}
		if (acr.getP5t44() != null)
		{
			cal2.setTime(acr.getP5t44());
			res.setP5T44(cal2);
		}
		if ((acr.getP6t44() != null) && (!CheckNull.isEmpty(acr.getP6t44())))
		{
			res.setP6T44(acr.getP6t44());
		}
		if ((acr.getP7t44() != null) && (!CheckNull.isEmpty(acr.getP7t44())))
		{
			res.setP7T44(Short.parseShort(acr.getP7t44()));
		}
		if ((acr.getP8t44() != null) && (!CheckNull.isEmpty(acr.getP8t44())))
		{
			res.setP8T44(Short.parseShort(acr.getP8t44()));
		}
		if ((acr.getP9t44() != null) && (!CheckNull.isEmpty(acr.getP9t44())))
		{
			res.setP9T44(Short.parseShort(acr.getP9t44()));
		}
		if ((acr.getP10t44() != null) && (!CheckNull.isEmpty(acr.getP10t44())))
		{
			res.setP10T44(Short.parseShort(acr.getP10t44()));
		}
		if ((acr.getP11t44() != null) && (!CheckNull.isEmpty(acr.getP11t44())))
		{
			res.setP11T44(acr.getP11t44());
		}
		if ((acr.getP12t44() != null) && (!CheckNull.isEmpty(acr.getP12t44())))
		{
			res.setP12T44(Short.parseShort(acr.getP12t44()));
		}
		if ((acr.getP13t44() != null) && (!CheckNull.isEmpty(acr.getP13t44())))
		{
			res.setP13T44(acr.getP13t44());
		}
		
		if ((acr.getP14t44() != null) && (!CheckNull.isEmpty(acr.getP14t44())))
		{
			res.setP14T44(acr.getP14t44());
		}
		
		if ((acr.getP15t44() != null) && (!CheckNull.isEmpty(acr.getP15t44())))
		{
			res.setP15T44(acr.getP15t44());
		}
		
		if ((acr.getP16t44() != null) && (!CheckNull.isEmpty(acr.getP16t44())))
		{
			res.setP16T44(acr.getP16t44());
		}
		if (acr.getP17t44() != null)
		{
			res.setP17T44(acr.getP17t44());
		}
		if (acr.getP18t44() != null)
		{
			res.setP18T44(acr.getP18t44());
		}
		if (acr.getP6t44() != null)
		{
			res.setP19T44(acr.getP19t44());
		}
		if (acr.getP20t44() != null)
		{
			res.setP20T44(acr.getP20t44());
		}
		if (acr.getP21t44() != null)
		{
			res.setP21T44(acr.getP21t44());
		}
		res.setP22T44(Integer.parseInt(acr.getP22t44()));
		res.setP23T44(acr.getP23t44());
		res.setP24T44(acr.getP24t44());
		res.setP27T44(Short.parseShort(otn));
		res.setP29T44((String) session.getAttribute("un"));
		return res;
	}
	
	private com.sbs.service.Payment getCurrPaymDelete(Payment acr) throws Exception
	{
		java.util.Calendar cal2 = java.util.Calendar.getInstance();
		java.util.Calendar cal = java.util.Calendar.getInstance();
		com.sbs.service.Payment res = new com.sbs.service.Payment();
		res.setP0T44(2);
		cal.setTime(df.parse(df.format(acr.getP2t44())));
		res.setP2T44(cal);
		res.setP3T44(Short.parseShort(acr.getP3t44()));
		if ((acr.getP4t44() != null) && (acr.getP4t44() != ""))
		{
			res.setP4T44(acr.getP4t44());
		}
		if (acr.getP5t44() != null)
		{
			cal2.setTime(acr.getP5t44());
			res.setP5T44(cal2);
		}
		if ((acr.getP6t44() != null) && (acr.getP6t44() != ""))
		{
			res.setP6T44(acr.getP6t44());
		}
		res.setP7T44(Short.parseShort(acr.getP7t44()));
		if ((acr.getP8t44() != null) && (acr.getP8t44() != ""))
		{
			res.setP8T44(Short.parseShort(acr.getP8t44()));
		}
		if ((acr.getP9t44() != null) && (acr.getP9t44() != ""))
		{
			res.setP9T44(Short.parseShort(acr.getP9t44()));
		}
		if ((acr.getP10t44() != null) && (acr.getP10t44() != ""))
		{
			res.setP10T44(Short.parseShort(acr.getP10t44()));
		}
		if ((acr.getP11t44() != null) && (acr.getP11t44() != ""))
		{
			res.setP11T44(acr.getP11t44());
		}
		if ((acr.getP12t44() != null) && (acr.getP12t44() != ""))
		{
			res.setP12T44(Short.parseShort(acr.getP12t44()));
		}
		if ((acr.getP13t44() != null) && (acr.getP13t44() != ""))
		{
			res.setP13T44(acr.getP13t44());
		}
		if ((acr.getP14t44() != null) && (acr.getP14t44() != ""))
		{
			res.setP14T44(acr.getP14t44());
		}
		if ((acr.getP15t44() != null) && (!CheckNull.isEmpty(acr.getP15t44())))
		{
			res.setP15T44(acr.getP15t44());
		}
		res.setP16T44(acr.getP16t44());
		if (acr.getP17t44() != null)
		{
			res.setP17T44(acr.getP17t44());
		}
		if (acr.getP18t44() != null)
		{
			res.setP18T44(acr.getP18t44());
		}
		if (acr.getP6t44() != null)
		{
			res.setP19T44(acr.getP19t44());
		}
		if (acr.getP20t44() != null)
		{
			res.setP20T44(acr.getP20t44());
		}
		if (acr.getP21t44() != null)
		{
			res.setP21T44(acr.getP21t44());
		}
		res.setP22T44(Integer.parseInt(acr.getP22t44()));
		// res.setP23T44(acr.getP23t44());
		res.setP24T44((String) session.getAttribute("un"));
		res.setP27T44(Short.parseShort(otn));
		return res;
	}
	
	private com.sbs.service.Payment getAddPaym(Payment acr) throws Exception
	{
		java.util.Calendar cal = java.util.Calendar.getInstance();
		java.util.Calendar cal2 = java.util.Calendar.getInstance();
		com.sbs.service.Payment res = new com.sbs.service.Payment();
		res.setP0T44(0);
		cal.setTime(df.parse(df.format(acr.getP2t44())));
		res.setP2T44(cal);
		res.setP3T44(Short.parseShort(acr.getP3t44()));
		if (acr.getP4t44() != null)
		{
			res.setP4T44(acr.getP4t44());
		}
		if (acr.getP5t44() != null)
		{
			cal2.setTime(acr.getP5t44());
			res.setP5T44(cal2);
		}
		if (acr.getP6t44() != null)
		{
			res.setP6T44(acr.getP6t44());
		}
		res.setP7T44(Short.parseShort(acr.getP7t44()));
		if (acr.getP8t44() != null)
		{
			res.setP8T44(Short.parseShort(acr.getP8t44()));
		}
		if (acr.getP9t44() != null)
		{
			res.setP9T44(Short.parseShort(acr.getP9t44()));
		}
		if (acr.getP10t44() != null)
		{
			res.setP10T44(Short.parseShort(acr.getP10t44()));
		}
		if (acr.getP11t44() != null)
		{
			res.setP11T44(acr.getP11t44());
		}
		if (acr.getP12t44() != null)
		{
			res.setP12T44(Short.parseShort(acr.getP12t44()));
		}
		res.setP13T44(acr.getP13t44());
		if (acr.getP14t44() != null)
		{
			res.setP14T44(acr.getP14t44());
		}
		if ((acr.getP15t44() != null) && (!CheckNull.isEmpty(acr.getP15t44())))
		{
			res.setP15T44(acr.getP15t44());
		}
		res.setP16T44(acr.getP16t44());
		if (acr.getP17t44() != null)
		{
			res.setP17T44(acr.getP17t44());
		}
		if (acr.getP18t44() != null)
		{
			res.setP18T44(acr.getP18t44());
		}
		if (acr.getP6t44() != null)
		{
			res.setP19T44(acr.getP19t44());
		}
		if (acr.getP20t44() != null)
		{
			res.setP20T44(acr.getP20t44());
		}
		if (acr.getP21t44() != null)
		{
			res.setP21T44(acr.getP21t44());
		}
		// res.setP22T44(acr.getP22t44());
		res.setP24T44(acr.getP24t44());
		res.setP29T44((String) session.getAttribute("un"));
		res.setP27T44(Short.parseShort(aotn));
		return res;
	}
	
	public void onSelect$p3t44()
	{
		if (p3t44.getValue().equals("1"))
		{
			istochnik_sredstv = ContractService.getFundSourse("1,2,3", alias);
			p7t44.setModel((new ListModelList(istochnik_sredstv)));
			row_p4t44.setVisible(false);
			row_p5t44.setVisible(false);
			row_p6t44.setVisible(false);
			
		}
		else if (p3t44.getValue().equals("2"))
		{
			istochnik_sredstv = ContractService.getFundSourse("1,3", alias);
			p7t44.setModel((new ListModelList(istochnik_sredstv)));
			row_p4t44.setVisible(true);
			row_p5t44.setVisible(true);
			row_p6t44.setVisible(true);
		}
		else if (p3t44.getValue().equals("4"))
		{
			istochnik_sredstv = ContractService.getFundSourse("3", alias);
			p7t44.setModel((new ListModelList(istochnik_sredstv)));
			row_p4t44.setVisible(false);
			row_p5t44.setVisible(false);
			row_p6t44.setVisible(true);
		}
		setCurrent();
		
	}
	
	public void onSelect$ap3t44()
	{
		if (ap3t44.getValue().equals("1"))
		{
			istochnik_sredstv = ContractService.getFundSourse("1,2,3", alias);
			ap7t44.setModel((new ListModelList(istochnik_sredstv)));
			row_ap4t44.setVisible(false);
			row_ap5t44.setVisible(false);
			row_ap6t44.setVisible(false);
			
		}
		else if (ap3t44.getValue().equals("2"))
		{
			istochnik_sredstv = ContractService.getFundSourse("1,3", alias);
			ap7t44.setModel((new ListModelList(istochnik_sredstv)));
			Fundtype = ContractService.getFundSourse("1,2", alias);
			p8t44.setModel((new ListModelList(Fundtype)));
			row_ap4t44.setVisible(true);
			row_ap5t44.setVisible(true);
			row_ap6t44.setVisible(true);
		}
		else if (ap3t44.getValue().equals("4"))
		{
			istochnik_sredstv = ContractService.getFundSourse("3", alias);
			ap7t44.setModel((new ListModelList(istochnik_sredstv)));
			Fundtype = ContractService.getFundSourse("1,2", alias);
			p8t44.setModel((new ListModelList(Fundtype)));
			row_ap4t44.setVisible(false);
			row_ap5t44.setVisible(false);
			row_ap6t44.setVisible(true);
		}
	}
	
	public void onSelect$p7t44()
	{
		if (p7t44.getValue().equals("3"))
		{
			row_p8t44.setVisible(true);
			row_p9t44.setVisible(true);
		}
		else
		{
			row_p8t44.setVisible(false);
			row_p9t44.setVisible(false);
		}
		setCurrent();
	}
	
	public void onSelect$ap7t44()
	{
		if (ap7t44.getValue().equals("3"))
		{
			row_ap8t44.setVisible(true);
			row_ap9t44.setVisible(true);
		}
		else
		{
			row_ap8t44.setVisible(false);
			row_ap9t44.setVisible(false);
		}
	}
	
	public void onSelect$p8t44()
	{
		if (p8t44.getValue().equals("1"))
		{
			Loantype = ContractService.getLoantype("2,3,4,5,6", alias);
			p9t44.setModel((new ListModelList(Loantype)));
			row_p10t44.setVisible(false);
		}
		else if (p8t44.getValue().equals("2"))
		{
			Loantype = ContractService.getLoantype("2,3,4,5,6", alias);
			p9t44.setModel((new ListModelList(Loantype)));
			row_p10t44.setVisible(false);
		}
		else if (p8t44.getValue().equals("3"))
		{
			Loantype = ContractService.getLoantype("1,6", alias);
			p9t44.setModel((new ListModelList(Loantype)));
			row_p10t44.setVisible(true);
		}
		else if (p8t44.getValue().equals("4"))
		{
			Loantype = ContractService.getLoantype("1,6", alias);
			p9t44.setModel((new ListModelList(Loantype)));
			row_p10t44.setVisible(true);
		}
		setCurrent();
	}
	
	public void onSelect$ap8t44()
	{
		if (ap8t44.getValue().equals("1"))
		{
			Loantype = ContractService.getLoantype("2,3,4,5,6", alias);
			ap9t44.setModel((new ListModelList(Loantype)));
			row_ap10t44.setVisible(false);
		}
		else if (ap8t44.getValue().equals("2"))
		{
			Loantype = ContractService.getLoantype("2,3,4,5,6", alias);
			ap9t44.setModel((new ListModelList(Loantype)));
			row_ap10t44.setVisible(false);
		}
		else if (ap8t44.getValue().equals("3"))
		{
			Loantype = ContractService.getLoantype("1,6", alias);
			ap9t44.setModel((new ListModelList(Loantype)));
			row_ap10t44.setVisible(true);
		}
		else if (ap8t44.getValue().equals("4"))
		{
			Loantype = ContractService.getLoantype("1,6", alias);
			ap9t44.setModel((new ListModelList(Loantype)));
			row_ap10t44.setVisible(true);
		}
	}
	
	public void onSelect$p9t44()
	{
		if (p9t44.getValue().equals("6"))
		{
			row_p11t44.setVisible(true);
		}
		else
		{
			row_p11t44.setVisible(false);
		}
		setCurrent();
	}
	
	public void onSelect$ap9t44()
	{
		if (ap9t44.getValue().equals("6"))
		{
			row_ap11t44.setVisible(true);
		}
		else
		{
			row_ap11t44.setVisible(false);
		}
	}
	
	public void onSelect$p12t44()
	{
		if (p12t44.getValue().equals("2"))
		{ // ����������
		
			row_p13t44.setVisible(true);
			row_p14t44.setVisible(false);
			
		}
		else if (p12t44.getValue().equals("10"))
		{ // ������ ��������
			getCurr_22t1_23t1 = com.is.tf.contract.ContractService.getCurr_22t1_23t1(idn, alias);
			p15t44.setModel((new ListModelList(getCurr_22t1_23t1)));
			row_p13t44.setVisible(false);
			row_p14t44.setVisible(true);
		}
		else if (p12t44.getValue().equals("1"))
		{ // ����������
			getCurr_22t1_23t1 = com.is.tf.contract.ContractService.getMyCurrency2("val1,val2", alias);
			p15t44.setModel((new ListModelList(getCurr_22t1_23t1)));
			row_p13t44.setVisible(false);
			row_p14t44.setVisible(false);
			
			// System.out.println("onSelect$p12t44()  ����������    current.getP15t44() = "+current.getP15t44());
		}
		else if (p12t44.getValue().equals("9"))
		{ // �� �����
			getCurr_22t1_23t1 = com.is.tf.contract.ContractService.getCurr_22t1_23t1(idn, alias);
			p15t44.setModel((new ListModelList(getCurr_22t1_23t1)));
			row_p13t44.setVisible(false);
			row_p14t44.setVisible(false);
		}
		else if (ap12t44.getValue().equals("7"))
		{ // �������
			getCurr_22t1_23t1 = com.is.tf.contract.ContractService.getCurr_22t1_23t1(idn, alias);
			p15t44.setModel((new ListModelList(getCurr_22t1_23t1)));
			row_p13t44.setVisible(false);
			row_p14t44.setVisible(false);
			setCurrent();
		}
	}
	
	public void onSelect$ap12t44()
	{
		if (ap12t44.getValue().equals("2"))
		{ // ����������
			row_ap13t44.setVisible(true);
			row_ap14t44.setVisible(false);
			
		}
		else if (ap12t44.getValue().equals("10"))
		{ // ������ ��������
			agetCurr_22t1_23t1 = com.is.tf.contract.ContractService.getCurr_22t1_23t1(idn, alias);
			ap15t44.setModel((new ListModelList(agetCurr_22t1_23t1)));
			row_ap13t44.setVisible(false);
			row_ap14t44.setVisible(true);
		}
		else if (ap12t44.getValue().equals("1"))
		{ // ����������
			agetCurr_22t1_23t1 = com.is.tf.contract.ContractService.getCurr_22t1_23t1(idn, alias);
			ap15t44.setModel((new ListModelList(agetCurr_22t1_23t1)));
			row_ap13t44.setVisible(false);
			row_ap14t44.setVisible(false);
		}
		else if (ap12t44.getValue().equals("9"))
		{ // �� �����
			agetCurr_22t1_23t1 = com.is.tf.contract.ContractService.getCurr_22t1_23t1(idn, alias);
			ap15t44.setModel((new ListModelList(agetCurr_22t1_23t1)));
			row_ap13t44.setVisible(false);
			row_ap14t44.setVisible(false);
		}
		else if (ap12t44.getValue().equals("7"))
		{ // �������
			agetCurr_22t1_23t1 = com.is.tf.contract.ContractService.getCurr_22t1_23t1(idn, alias);
			ap15t44.setModel((new ListModelList(agetCurr_22t1_23t1)));
			row_ap13t44.setVisible(false);
			row_ap14t44.setVisible(false);
			
		}
	}
	
	public void onSelect$p13t44()
	{
		Accreditiv rr = (Accreditiv) p13t44.getObject();
		Acred_val = rr.getP4t21();
		current.setP15t44(Acred_val);
		kod = com.is.tf.contract.ContractService.getMyCurrency2(current.getP15t44(), alias);
		
		// /p15t44.setSelecteditem(kod);
		p15t44.setModel((new ListModelList(kod)));
		
		setCurrent();
		// System.out.println("Acred_val= "+Acred_val+"current.getP15t44() = "+current.getP15t44());
		
	}
	
	public void onSelect$ap13t44()
	{
		Accreditiv rr = (Accreditiv) ap13t44.getObject();
		aAcred_val = rr.getP4t21();
		akod = com.is.tf.contract.ContractService.getMyCurrency2(aAcred_val, alias);
		
		ap15t44.setModel((new ListModelList(akod)));
		
	}
	
	public void onSelect$p14t44()
	{
		Garant gr = (Garant) p14t44.getObject();
		Gar_val = gr.getP5t18();
		current.setP15t44(Gar_val);
		gkod = com.is.tf.contract.ContractService.getMyCurrency2(current.getP15t44(), alias);
		p15t44.setModel((new ListModelList(gkod)));
		setCurrent();
		System.out.println("Gar_val= " + Gar_val + "current.getP15t44() = " + current.getP15t44());
	}
	
	public void onSelect$ap14t44()
	{
		Garant agr = (Garant) ap14t44.getObject();
		Gar_aval = agr.getP5t18();
		akod = com.is.tf.contract.ContractService.getMyCurrency2(Gar_aval, alias);
		ap15t44.setModel((new ListModelList(akod)));
		
		// System.out.println("Acred_val= "+Acred_val+"current.getP15t44() = "+current.getP15t44());
	}
	
	public void onChange$p15t44()
	{
		// current.setP15t44(p15t44.getValue());
		coursecurrencies = ContractService.getCourseCurr_18t1_19t1_withOther(idn, df.format(p2t44.getValue()), "'" + p15t44.getValue() + "'", alias);
		p17t441.setModel((new ListModelList(coursecurrencies)));
		p17t443.setModel((new ListModelList(coursecurrencies)));
		setCourse(false);
		row_p17t44.setVisible(true);
		
		if (val1.equalsIgnoreCase(p15t44.getValue()))
		{
			row_p17t44.setVisible(false);
			// System.out.println("onChange$p15t44()     val1= "+val1+"="+" p15t44.getValue()="+p15t44.getValue());
		}
		
	}
	
	public void onChange$ap15t44()
	{
		// current.setP15t44(p15t44.getValue());
		coursecurrencies = ContractService.getCourseCurr_18t1_19t1_withOther(idn, df.format(ap2t44.getValue()), "'" + ap15t44.getValue() + "'", alias);
		ap17t441.setModel((new ListModelList(coursecurrencies)));
		ap17t443.setModel((new ListModelList(coursecurrencies)));
		asetCourse(true);
		row_ap17t44.setVisible(true);
		
		if (val1.equalsIgnoreCase(ap15t44.getValue()))
		{
			row_ap17t44.setVisible(false);
			// System.out.println("onChange$p15t44()     val1= "+val1+"="+" p15t44.getValue()="+p15t44.getValue());
		}
		
	}
	
	public void onChange$ap16t44()
	{
		acountCourse(false);
		if (Double.parseDouble(summa1) > ap16t44.doubleValue())
		{
			aotn = ("0");
		}
		else if (Double.parseDouble(summa1) < ap16t44.doubleValue())
		{
			aotn = ("1");
		}
		// System.out.println("summa1="+summa1+"  aotn="+aotn+"   ap16t44.doubleValue()="+ap16t44.doubleValue());
	}
	
	public void onChange$p16t44()
	{
		countCourse(false);
		if (Double.parseDouble(summa1) > p16t44.doubleValue())
		{
			otn = ("0");
		}
		else if (Double.parseDouble(summa1) < p16t44.doubleValue())
		{
			otn = ("1");
		}
		// System.out.println("summa1="+summa1+"  otn="+otn+"   p16t44.doubleValue()="+p16t44.doubleValue());
	}
	
	public void onInitRenderLater$p15t44()
	{
		setCourse(false);
	}
	
	public void onInitRenderLater$ap17t441()
	{
		asetCourse(false);
	}
	
	public void onInitRenderLater$ap17t443()
	{
		asetCourse(false);
	}
	
	public void onInitRenderLater$p17t441()
	{
		setCourse(false);
	}
	
	public void onInitRenderLater$p17t443()
	{
		setCourse(false);
	}
	
	private void setCourse(Boolean getNewCourse)
	{
		if (getNewCourse)
		{
			coursecurrencies = ContractService.getCourseCurr_18t1_19t1_withOther(idn, df.format(p2t44.getValue()), "'" + current.getP15t44() + "'", alias);
			p17t441.setModel((new ListModelList(coursecurrencies)));
			p17t443.setModel((new ListModelList(coursecurrencies)));
			
		}
		if (coursecurrencies.size() > 0)
		{
			RefCurrencyData curr1 = PaymentService.getRefCurrencyData(current.getP15t44(), coursecurrencies);
			RefCurrencyData curr2 = PaymentService.getRefCurrencyData(val1, coursecurrencies);
			RefCurrencyData curr3 = PaymentService.getRefCurrencyData(val2, coursecurrencies);
			if (curr1 != null && curr2 != null && curr3 != null)
			{
				
				if (curr1.getCourse() > curr2.getCourse())
				{
					p17t441.setSelecteditem(curr1.getKod());
					p17t443.setSelecteditem(curr2.getKod());
				}
				else
				{
					p17t441.setSelecteditem(curr2.getKod());
					p17t443.setSelecteditem(curr1.getKod());
				}
				// System.out.println("p2t44.getValue()"+p2t44.getValue()+"val1="+val1+"val2="+val2+" current.getP15t44()="+current.getP15t44()+" curr1.getCourse()="+curr1.getCourse()+" curr2.getCourse()="+curr2.getCourse()+"curr3.getCourse()="+curr3.getCourse());
				
				countCourse(false);
			}
		}
	}
	
	private void countCourse(Boolean setCourse)
	{
		try
		{
			
			if (!CheckNull.isEmpty(p17t443.getValue()) && !CheckNull.isEmpty(p17t441.getValue()))
			{
				// System.out.println("***"+p15t351.getValue()+" - "+p15t353.getValue());
				if (setCourse)
				{
					p17t442.setValue("" + (p17t441.getCourse() / p17t443.getCourse()));
					current.setP17t44((p17t441.getCourse() / p17t443.getCourse()));
					
				}
				// System.out.println("�� ����� ��: "+(p17t441.getCourse()/p17t443.getCourse())+" "+p17t441.getCourse()+" "+p17t443.getCourse());
				cbcourse.setValue("�� ����� ��: " + nf.format(p17t441.getCourse() / p17t443.getCourse()));
				p17t442.setValue("" + (p17t441.getCourse() / p17t443.getCourse()));
				current.setP17t44((p17t441.getCourse() / p17t443.getCourse()));
				// cbcourse1.setValue("�� ����� ��: "+(p7t391.getCourse()/p7t393.getCourse()));
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
				 * "����� �������� ��������� ������������� ���������� �����!"
				 * :"����� �������� �� ������������� ���������� �����!"
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
		if (getNewCourse)
		{
			coursecurrencies = ContractService.getCourseCurr_18t1_19t1_withOther(idn, df.format(ap2t44.getValue()), "'" + ap15t44.getValue() + "'", alias);
			ap17t441.setModel((new ListModelList(coursecurrencies)));
			ap17t443.setModel((new ListModelList(coursecurrencies)));
			
		}
		if (coursecurrencies.size() > 0)
		{
			RefCurrencyData curr1 = PaymentService.getRefCurrencyData(ap15t44.getValue(), coursecurrencies);
			RefCurrencyData curr2 = PaymentService.getRefCurrencyData(val1, coursecurrencies);
			RefCurrencyData curr3 = PaymentService.getRefCurrencyData(val2, coursecurrencies);
			if (curr1 != null && curr2 != null && curr3 != null)
			{
				
				if (curr1.getCourse() > curr2.getCourse())
				{
					ap17t441.setSelecteditem(curr1.getKod());
					ap17t443.setSelecteditem(curr2.getKod());
				}
				else
				{
					ap17t441.setSelecteditem(curr2.getKod());
					ap17t443.setSelecteditem(curr1.getKod());
				}
				// System.out.println("p2t44.getValue()"+p2t44.getValue()+"val1="+val1+"val2="+val2+" current.getP15t44()="+current.getP15t44()+" curr1.getCourse()="+curr1.getCourse()+" curr2.getCourse()="+curr2.getCourse()+"curr3.getCourse()="+curr3.getCourse());
				
				acountCourse(false);
			}
		}
	}
	
	private void acountCourse(Boolean asetCourse)
	{
		try
		{
			
			if (!CheckNull.isEmpty(ap17t443.getValue()) && !CheckNull.isEmpty(ap17t441.getValue()))
			{
				// System.out.println("***"+p15t351.getValue()+" - "+p15t353.getValue());
				if (asetCourse)
				{
					ap17t442.setValue("" + nf.format(ap17t441.getCourse() / ap17t443.getCourse()));
					// ap17t44.setValue(""+(ap17t441.getCourse()/ap17t443.getCourse()));
					
				}
				// System.out.println("�� ����� ��: "+(p17t441.getCourse()/p17t443.getCourse())+" "+p17t441.getCourse()+" "+p17t443.getCourse());
				ap17t442.setValue("" + nf.format(ap17t441.getCourse() / ap17t443.getCourse()));
				acbcourse.setValue("�� ����� ��: " + nf.format(ap17t441.getCourse() / ap17t443.getCourse()));
				// cbcourse1.setValue("�� ����� ��: "+(p7t391.getCourse()/p7t393.getCourse()));
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
				 * "����� �������� ��������� ������������� ���������� �����!"
				 * :"����� �������� �� ������������� ���������� �����!"
				 * )+"("+db+")");
				 */
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void setCurrent()
	{
		if (current != null)
		{
			
			coursecurrencies = ContractService.getCourseCurr_18t1_19t1_withOther2(idn, idc, df.format(p2t44.getValue()), "'" + current.getP15t44() + "'", alias);
			p17t441.setModel((new ListModelList(coursecurrencies)));
			p17t443.setModel((new ListModelList(coursecurrencies)));
			
			// setCourse(true);
			row_p17t44.setVisible(true);
			
			if (val1.equalsIgnoreCase(current.getP15t44()))
			{
				row_p17t44.setVisible(false);
			}
			
			if (current.getP3t44().equals("1"))
			{
				istochnik_sredstv = ContractService.getFundSourse("1,2,3", alias);
				p7t44.setModel((new ListModelList(istochnik_sredstv)));
				row_p4t44.setVisible(false);
				row_p5t44.setVisible(false);
				row_p10t44.setVisible(false);
				row_p6t44.setVisible(false);
			}
			else if (current.getP3t44().equals("2"))
			{
				istochnik_sredstv = ContractService.getFundSourse("1,3", alias);
				p7t44.setModel((new ListModelList(istochnik_sredstv)));
				Fundtype = ContractService.getFundSourse("1,2", alias);
				p8t44.setModel((new ListModelList(Fundtype)));
				row_p4t44.setVisible(true);
				row_p5t44.setVisible(true);
				row_p6t44.setVisible(true);
				row_p10t44.setVisible(true);
			}
			else if (current.getP3t44().equals("4"))
			{
				istochnik_sredstv = ContractService.getFundSourse("3", alias);
				p7t44.setModel((new ListModelList(istochnik_sredstv)));
				Fundtype = ContractService.getFundSourse("1,2", alias);
				p8t44.setModel((new ListModelList(Fundtype)));
				row_p4t44.setVisible(false);
				row_p5t44.setVisible(false);
				row_p6t44.setVisible(true);
				row_p10t44.setVisible(false);
			}
			
			if (current.getP7t44().equals("3"))
			{
				row_p8t44.setVisible(true);
				row_p9t44.setVisible(true);
			}
			else
			{
				row_p8t44.setVisible(false);
				row_p9t44.setVisible(false);
			}
			
			if (!CheckNull.isEmpty(current.getP8t44()) && (current.getP8t44().equals("1")))
			{
				Loantype = ContractService.getLoantype("2,3,4,5,6", alias);
				p9t44.setModel((new ListModelList(Loantype)));
				row_p10t44.setVisible(false);
			}
			else if (!CheckNull.isEmpty(current.getP8t44()) && current.getP8t44().equals("2"))
			{
				Loantype = ContractService.getLoantype("2,3,4,5,6", alias);
				p9t44.setModel((new ListModelList(Loantype)));
				row_p10t44.setVisible(false);
			}
			else if (!CheckNull.isEmpty(current.getP8t44()) && current.getP8t44().equals("3"))
			{
				Loantype = ContractService.getLoantype("1,6", alias);
				p9t44.setModel((new ListModelList(Loantype)));
				row_p10t44.setVisible(true);
			}
			else if (!CheckNull.isEmpty(current.getP8t44()) && current.getP8t44().equals("4"))
			{
				Loantype = ContractService.getLoantype("1,6", alias);
				p9t44.setModel((new ListModelList(Loantype)));
				row_p10t44.setVisible(true);
			}
			
			if (!CheckNull.isEmpty(current.getP9t44()) && (current.getP9t44().equals("6")))
			{
				row_p11t44.setVisible(true);
			}
			else
			{
				row_p11t44.setVisible(false);
			}
			if (!CheckNull.isEmpty(current.getP15t44()) && current.getP15t44() != null)
			{
				p15t44.setSelecteditem(current.getP15t44());
				if (current.getP12t44().equals("2"))
				{ // ����������
					// Accreditiv rr=(Accreditiv)p13t44.getObject();
					// Acred_val=rr.getP4t21();
					kod = com.is.tf.contract.ContractService.getMyCurrency2(current.getP15t44(), alias);
					p15t44.setModel((new ListModelList(kod)));
					row_p13t44.setVisible(true);
					row_p14t44.setVisible(false);
				}
				else if (!CheckNull.isEmpty(current.getP12t44()) && current.getP12t44().equals("1"))
				{ // ����������
					// getCurr_22t1_23t1 =
					// com.is.tf.contract.ContractService.getCurr_22t1_23t1(idn,
					// alias);
					// p15t44.setModel((new ListModelList(getCurr_22t1_23t1)));
					kod = com.is.tf.contract.ContractService.getMyCurrency2(current.getP15t44(), alias);
					p15t44.setModel((new ListModelList(kod)));
					row_p13t44.setVisible(false);
					row_p14t44.setVisible(false);
				}
				else if (!CheckNull.isEmpty(current.getP12t44()) && current.getP12t44().equals("9"))
				{ // �� �����
					// getCurr_22t1_23t1 =
					// com.is.tf.contract.ContractService.getCurr_22t1_23t1(idn,
					// alias);
					// p15t44.setModel((new ListModelList(getCurr_22t1_23t1)));
					kod = com.is.tf.contract.ContractService.getMyCurrency2(current.getP15t44(), alias);
					p15t44.setModel((new ListModelList(kod)));
					row_p13t44.setVisible(false);
					row_p14t44.setVisible(false);
				}
				else if (!CheckNull.isEmpty(current.getP12t44()) && current.getP12t44().equals("7"))
				{ // �������
					// getCurr_22t1_23t1 =
					// com.is.tf.contract.ContractService.getCurr_22t1_23t1(idn,
					// alias);
					// p15t44.setModel((new ListModelList(getCurr_22t1_23t1)));
					kod = com.is.tf.contract.ContractService.getMyCurrency2(current.getP15t44(), alias);
					p15t44.setModel((new ListModelList(kod)));
					row_p13t44.setVisible(false);
					row_p14t44.setVisible(false);
					
				}
				else if (!CheckNull.isEmpty(current.getP12t44()) && current.getP12t44().equals("10"))
				{ // ��������
					// getCurr_22t1_23t1 =
					// com.is.tf.contract.ContractService.getCurr_22t1_23t1(idn,
					// alias);
					// p15t44.setModel((new ListModelList(getCurr_22t1_23t1)));
					kod = com.is.tf.contract.ContractService.getMyCurrency2(current.getP15t44(), alias);
					p15t44.setModel((new ListModelList(kod)));
					row_p13t44.setVisible(false);
					row_p14t44.setVisible(true);
				}
			}
			/*
			 * if
			 * (val1.equalsIgnoreCase(p15t44.getValue())||(val2.equalsIgnoreCase
			 * (p15t44.getValue()))){ row_p17t44.setVisible(false);
			 * //System.out.
			 * println("val1="+val1+"val2="+val2+"p15t44.getValue()="
			 * +p15t44.getValue()); } if (current.getP13t44()!=null) { kod =
			 * com.
			 * is.tf.contract.ContractService.getMyCurrency2(current.getP15t44
			 * (), alias); p15t44.setModel((new ListModelList(kod))); } if
			 * (current.getP14t44()!=null) { kod =
			 * com.is.tf.contract.ContractService
			 * .getMyCurrency2(current.getP15t44(), alias); p15t44.setModel((new
			 * ListModelList(kod))); }
			 */

			// if ((subj.equals(1))&&(subj.equals(3))){
			if ((subj.equals("1")) && (val2.equals(null)))
			{
				row_p18t44.setVisible(true);
				row_p19t44.setVisible(false);
				row_ap18t44.setVisible(true);
				row_ap19t44.setVisible(false);
			}
			else if (((subj.equals("1")) && (val2.equals(null)) || ((subj.equals("3")) && (val2.equals(null)))))
			{
				row_p18t44.setVisible(true);
				row_p19t44.setVisible(false);
				row_ap18t44.setVisible(true);
				row_ap19t44.setVisible(false);
				
			}
			else if (((subj.equals("3")) && (val2 != null)))
			{
				row_p18t44.setVisible(true);
				row_p19t44.setVisible(true);
				row_ap18t44.setVisible(true);
				row_ap19t44.setVisible(true);
				row_p20t44.setVisible(true);
				row_p21t44.setVisible(true);
				row_ap20t44.setVisible(true);
				row_ap21t44.setVisible(true);
			}
			else if (((subj.equals("1")) && (val2 != null)))
			{
				row_p18t44.setVisible(true);
				row_p19t44.setVisible(true);
				row_ap18t44.setVisible(true);
				row_ap19t44.setVisible(true);
				row_p20t44.setVisible(false);
				row_p21t44.setVisible(false);
				row_ap20t44.setVisible(false);
				row_ap21t44.setVisible(false);
			}
			else if ((subj.equals("3")) && (val2.equals(null)))
			{
				row_p18t44.setVisible(true);
				row_p19t44.setVisible(true);
				row_ap18t44.setVisible(true);
				row_ap19t44.setVisible(true);
				row_p20t44.setVisible(true);
				row_p21t44.setVisible(true);
				row_ap20t44.setVisible(true);
				row_ap21t44.setVisible(true);
			}
			else if ((subj.equals("2")) && (val2.equals(null)))
			{
				row_p18t44.setVisible(false);
				row_p19t44.setVisible(false);
				row_p20t44.setVisible(true);
				row_p21t44.setVisible(false);
				row_ap18t44.setVisible(false);
				row_ap19t44.setVisible(false);
				row_ap20t44.setVisible(true);
				row_ap21t44.setVisible(false);
			}
			else if ((subj.equals("2")) && (val2 != null))
			{
				row_p18t44.setVisible(false);
				row_p19t44.setVisible(false);
				row_p20t44.setVisible(true);
				row_p21t44.setVisible(true);
				row_ap18t44.setVisible(false);
				row_ap19t44.setVisible(false);
				row_ap20t44.setVisible(true);
				row_ap21t44.setVisible(true);
			}
			// System.out.println("val1="+val1+"val2="+val2+"subj="+subj);
			
			if ((current.getP16t44() != null) && (Double.parseDouble(summa1) > current.getP16t44()))
			{
				otn2 = ("0");
			}
			else if ((current.getP16t44() != null) && (Double.parseDouble(summa1) < current.getP16t44()))
			{
				otn2 = ("1");
			}
			if (!CheckNull.isEmpty(current.getP22t44()) && current.getP22t44() != null)
			{
				row_p22t44.setVisible(true);
			}
			else
			{
				row_p22t44.setVisible(false);
			}
			if (!CheckNull.isEmpty(current.getP30t44()) && current.getP30t44() != null)
			{
				row_p30t44.setVisible(true);
			}
			else
			{
				row_p30t44.setVisible(false);
			}
		}
		
	}
	
	private void setCurrentAdd()
	{
		CheckNull.clearForm(addgrd);
		ap2t44.setValue(new java.util.Date());
		ap15t44.setSelecteditem(ap15t44.getValue());
		if (ap2t44.getValue() != null && ap15t44.getValue() != null)
		{
			coursecurrencies = ContractService.getCourseCurr_18t1_19t1_withOther2(idn, idc, df.format(ap2t44.getValue()), "'" + ap15t44.getValue() + "'", alias);
			ap17t441.setModel((new ListModelList(coursecurrencies)));
			ap17t443.setModel((new ListModelList(coursecurrencies)));
			if (coursecurrencies.size() > 0)
			{
				row_ap17t44.setVisible(true);
				
				if (val1.equalsIgnoreCase(ap15t44.getValue()))
				{
					row_ap17t44.setVisible(false);
				}
			}
			
			/*
			 * if (!CheckNull.isEmpty(ap9t21.getValue())) {
			 * agar_date2.setVisible(false); } else {
			 * agar_date2.setVisible(true); }
			 */
		}
	}
	
	private Window contractmain = null;
	
	public void onClick$btn_confirm()
	{
		sendConfirm(1, current.getP22t44(), current);
	}
	
	public void onClick$btn_reject()
	{
		sendConfirm(0, current.getP22t44(), current);
	}
	
	private void sendConfirm(int action, String docnum, Object obj)
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
