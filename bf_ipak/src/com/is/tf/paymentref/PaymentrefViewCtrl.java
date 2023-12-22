package com.is.tf.paymentref;

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
import org.zkoss.zkplus.databind.BindingListModelList;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Decimalbox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
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

import com.is.ISLogger;
import com.is.tf.Accreditiv.Accreditiv;
import com.is.tf.contract.ContractService;
import com.is.tf.fund.Fund;
import com.is.tf.garant.Garant;
import com.is.tf.paymentrefsumchange.Paymentrefsumchange;
import com.is.tf.paymentrefsumchange.PaymentrefsumchangeService;
import com.is.tf.policy.Policy;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.RefData;
import com.is.utils.Res;
import com.is.utils.refobj.RefObjCBox;
import com.is.utils.refobj.RefObjData;
import com.is.utils.refobj.XMLSerializer;
import com.sbs.service.BankServiceProxy;
import com.sbs.service.PaymentRefResult;
import com.sbs.service.PaymentRefSumChangeResult;

public class PaymentrefViewCtrl extends GenericForwardComposer
{
	private Div frm;
	private Listbox dataGrid, SumchangeGrid;
	private Paging contactPaging;
	private Div grd, sum_div, sumg, paymrefsum;
	private Grid grid_sumchange, addgrd, frmgrd, fgrd, sum_grd;
	private Toolbarbutton btn_confirm33, btn_cancel, btn_edit, btn_delete, btn_save, btn_save2, btn_delete2, btn_add2, btn_back2, btn_saveGlBuh, btn_saveUpr, btn_saveGo;
	private Toolbarbutton btn_last, btn_grid;
	private Toolbarbutton btn_next;
	private Toolbarbutton btn_prev;
	private Toolbarbutton btn_first;
	private Toolbarbutton btn_add;
	private Toolbarbutton btn_search;
	private Toolbarbutton btn_confirmM, btn_rejectM, btn_confirm, btn_reject;
	private Toolbarbutton btn_back, btn_summ_change;
	private Toolbarbutton btn_refresh, btn_edit2, btn_confirmSumGl, btn_rejectSumGl, btn_confirmSumUpr, btn_rejectSumUpr, btn_confirmSumGo, btn_rejectSumGo;
	private Toolbar tb, tb1;
	private Textbox p14t38, p6t38, p10t37_img, ap10t37_img, p10t37, p100t37, p21t37, id, p1t37, p0t37, p2t37, p13t37, p14t37, p15t37, p16t37, p17t37, p18t37, p19t37;
	private Textbox ap100t37, ap10t37, ap21t37, aid, ap1t37, ap0t37, ap2t37, ap13t37, ap14t37, ap15t37, ap16t37, ap17t37, ap18t37, ap19t37;
	private Textbox fp100t37, fp24t37, fp21t37, fid, fp1t37, fp0t37, fp2t37, fp4t37, fp5t37, fp6t37, fp7t37, fp8t37, fp13t37, fp14t37, fp15t37, fp16t37, fp17t37, fp18t37, fp19t37;
	private Datebox p25t37, fp10t37, fp25t37, ap25t37, p3t37, p12t37, ap3t37, fp3t37, ap12t37, fp12t37;
	private Decimalbox p4t38, fp11t37, ap11t37, p11t37, p9t37, ap9t37, fp9t37;
	private Row row_p25t37, row_p15t37, row_p17t37, row_p19t37, row_p12t37, row_ap12t37, row_ap5t37, row_ap24t37, row_ap6t37, row_ap7t37, row_ap8t37, row_p5t37, row_p24t37, row_p6t37, row_p7t37, row_p8t37;
	private RefCBox ap4t37, p4t37;
	private RefObjCBox p1t38, p24t37, ap24t37, p8t37, ap8t37, p7t37, ap7t37, p5t37, ap5t37, p6t37, ap6t37;
	private Paging paymentrefPaging;
	private Label val_p4t38, val_ap9t37, val_p9t37;
	private String pol_val, apol_val, agar_val, gar_val, accred_val, aaccred_val, fund_val, afund_val, idn, val1, val2, cu;
	private Date pol_date, apol_date, gar_date, agar_date, accred_date, aaccred_date;
	private Label line1, line2, line3, line4, line5, line6, line7, line8, line9, line10, line11, line12, line13, line14, line15;
	private Label line1a, line2a, line3a, line4a, line5a, line6a, line7a;
	private int _pageSize = 15;
	private int _startPageNumber = 0;
	private int _totalSize = 0;
	private boolean _needsTotalSizeUpdate = true;
	private HashMap<String, String> curr_ = null;
	private List<RefData> exporttype = new ArrayList<RefData>();
	private List<RefObjData> funds = new ArrayList<RefObjData>();
	private List<RefObjData> accred = new ArrayList<RefObjData>();
	private List<RefObjData> garant = new ArrayList<RefObjData>();
	private List<RefObjData> policy = new ArrayList<RefObjData>();
	private List<RefObjData> paymref = new ArrayList<RefObjData>();
	private List<RefObjData> MoveFromEx = new ArrayList<RefObjData>();
	private String alias;
	private Long id_contract, idc;
	private String otn2, aotn, otn, subj, summa1, summa2, val22, val23, sparam1;
	private Tab tab_paymrefsum;
	private String pol_P7T37, pol_P8T37, pol_P6T37, pol_P24T37, pol_P5T37;
	private Listheader list10, list11, list7, list8, list9;
	
	public PaymentrefFilter filter = new PaymentrefFilter();
	
	PagingListModel model = null;
	ListModelList lmodel = null;
	private AnnotateDataBinder binder;
	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	
	private Paymentref current = new Paymentref();
	private Paymentrefsumchange currentSum = new Paymentrefsumchange();
	
	public PaymentrefViewCtrl()
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
		parameter = (String[]) param.get("idc");
		if (parameter != null)
		{
			idc = Long.parseLong(parameter[0]);
			// System.out.println("Garant  cont_val2 "+val2);
		}
		curr_ = com.is.tf.contract.ContractService.getHCurr(alias);
		filter.setP1t37(idn);
		
		parameter = (String[]) param.get("idn");
		if (parameter != null)
		{
			idn = (parameter[0]);
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
		parameter = (String[]) param.get("pol_P7T37");
		if (parameter != null)
		{
			pol_P7T37 = (parameter[0]);
		}
		parameter = (String[]) param.get("pol_P6T37");
		if (parameter != null)
		{
			pol_P6T37 = (parameter[0]);
		}
		parameter = (String[]) param.get("pol_P5T37");
		if (parameter != null)
		{
			pol_P5T37 = (parameter[0]);
		}
		parameter = (String[]) param.get("pol_P8T37");
		if (parameter != null)
		{
			pol_P8T37 = (parameter[0]);
		}
		parameter = (String[]) param.get("pol_P24T37");
		if (parameter != null)
		{
			pol_P24T37 = (parameter[0]);
		}
		
		if (pol_P5T37.equals("1"))
		{
			list7.setVisible(true);
		}
		else
		{
			list7.setVisible(false);
		}
		if (pol_P24T37.equals("1"))
		{
			list8.setVisible(true);
		}
		else
		{
			list8.setVisible(false);
		}
		if (pol_P6T37.equals("1"))
		{
			list9.setVisible(true);
		}
		else
		{
			list9.setVisible(false);
		}
		if (pol_P7T37.equals("1"))
		{
			list10.setVisible(true);
		}
		else
		{
			list10.setVisible(false);
		}
		if (pol_P8T37.equals("1"))
		{
			list11.setVisible(true);
		}
		else
		{
			list11.setVisible(false);
		}
		line1.setValue(Labels.getLabel("paymentref.p2t37").replaceAll("<br>", "\r\n"));
		line2.setValue(Labels.getLabel("paymentref.p3t37").replaceAll("<br>", "\r\n"));
		line3.setValue(Labels.getLabel("paymentref.p9t37").replaceAll("<br>", "\r\n"));
		line4.setValue(Labels.getLabel("paymentref.p10t37").replaceAll("<br>", "\r\n"));
		line5.setValue(Labels.getLabel("paymentref.p11t37t").replaceAll("<br>", "\r\n"));
		line6.setValue(Labels.getLabel("paymentref.p4t37").replaceAll("<br>", "\r\n"));
		line7.setValue(Labels.getLabel("paymentref.p5t37t").replaceAll("<br>", "\r\n"));
		line8.setValue(Labels.getLabel("paymentref.p24t37t").replaceAll("<br>", "\r\n"));
		line9.setValue(Labels.getLabel("paymentref.p6t37t").replaceAll("<br>", "\r\n"));
		line10.setValue(Labels.getLabel("paymentref.p7t37t").replaceAll("<br>", "\r\n"));
		line11.setValue(Labels.getLabel("paymentref.p8t37t").replaceAll("<br>", "\r\n"));
		line12.setValue(Labels.getLabel("paymentref.p12t37t").replaceAll("<br>", "\r\n"));
		line13.setValue(Labels.getLabel("paymentref.p21t37t").replaceAll("<br>", "\r\n"));
		line14.setValue(Labels.getLabel("paymentref.p25t37t").replaceAll("<br>", "\r\n"));
		line15.setValue(Labels.getLabel("paymentref.p100t37").replaceAll("<br>", "\r\n"));
		
		SumchangeGrid.setItemRenderer(new ListitemRenderer()
		{
			@SuppressWarnings("unchecked")
			public void render(Listitem row, Object data) throws Exception
			{
				Paymentrefsumchange pPaymentrefsumchange = (Paymentrefsumchange) data;
				
				row.setValue(pPaymentrefsumchange);
				
				row.appendChild(new Listcell(pPaymentrefsumchange.getId() + ""));
				row.appendChild(new Listcell(pPaymentrefsumchange.getP0t38()));
				row.appendChild(new Listcell(pPaymentrefsumchange.getP1t38()));
				row.appendChild(new Listcell(pPaymentrefsumchange.getP2t38() + ""));
				row.appendChild(new Listcell(pPaymentrefsumchange.getP3t38() + ""));
				row.appendChild(new Listcell(pPaymentrefsumchange.getP4t38() + ""));
				row.appendChild(new Listcell(pPaymentrefsumchange.getP5t38()));
				row.appendChild(new Listcell(pPaymentrefsumchange.getP6t38()));
				row.appendChild(new Listcell(pPaymentrefsumchange.getP7t38()));
				row.appendChild(new Listcell(pPaymentrefsumchange.getP8t38()));
				row.appendChild(new Listcell(pPaymentrefsumchange.getP9t38()));
				row.appendChild(new Listcell(pPaymentrefsumchange.getP10t38()));
				row.appendChild(new Listcell(pPaymentrefsumchange.getId_contract() + ""));
				row.appendChild(new Listcell(pPaymentrefsumchange.getP12t38() + ""));
				row.appendChild(new Listcell(pPaymentrefsumchange.getP13t38()));
				row.appendChild(new Listcell(pPaymentrefsumchange.getP14t38()));
				row.appendChild(new Listcell(pPaymentrefsumchange.getP15t38()));
				row.appendChild(new Listcell(pPaymentrefsumchange.getP18t38() + ""));
				row.appendChild(new Listcell(com.is.tf.contract.SPR.getP100Value(String.valueOf(pPaymentrefsumchange.getP100t38()))));
				
			}
		});
		
		refreshModel(_startPageNumber);
		line1a.setValue(Labels.getLabel("paymentrefsumchange.p4t38t").replaceAll("<br>", "\r\n"));
		line2a.setValue(Labels.getLabel("paymentrefsumchange.p6t38t").replaceAll("<br>", "\r\n"));
		line3a.setValue(Labels.getLabel("paymentrefsumchange.p14t38t").replaceAll("<br>", "\r\n"));
		line4a.setValue(Labels.getLabel("paymentrefsumchange.p15t38t").replaceAll("<br>", "\r\n"));
		line5a.setValue(Labels.getLabel("paymentrefsumchange.p18t38t").replaceAll("<br>", "\r\n"));
		line6a.setValue(Labels.getLabel("paymentrefsumchange.p12t38t").replaceAll("<br>", "\r\n"));
		line7a.setValue(Labels.getLabel("paymentrefsumchange.p100t38t").replaceAll("<br>", "\r\n"));
		
		dataGrid.setItemRenderer(new ListitemRenderer()
		{
			@SuppressWarnings("unchecked")
			public void render(Listitem row, Object data) throws Exception
			{
				Paymentref pPaymentref = (Paymentref) data;
				
				row.setValue(pPaymentref);
				row.appendChild(new Listcell(pPaymentref.getP2t37()));
				row.appendChild(new Listcell(pPaymentref.getP3t37() + ""));
				row.appendChild(new Listcell(pPaymentref.getP9t37() + ""));
				row.appendChild(new Listcell(com.is.tf.contract.SPR.getVal(String.valueOf(pPaymentref.getP10t37()))));
				row.appendChild(new Listcell(pPaymentref.getP11t37() + ""));
				row.appendChild(new Listcell(com.is.tf.contract.SPR.getOsnovanie(String.valueOf(pPaymentref.getP4t37() + ""))));
				row.appendChild(new Listcell(pPaymentref.getP5t37()));
				row.appendChild(new Listcell(pPaymentref.getP24t37()));
				row.appendChild(new Listcell(pPaymentref.getP6t37()));
				row.appendChild(new Listcell(pPaymentref.getP7t37()));
				row.appendChild(new Listcell(pPaymentref.getP8t37()));
				row.appendChild(new Listcell(pPaymentref.getP12t37() + ""));
				row.appendChild(new Listcell(pPaymentref.getP21t37()));
				row.appendChild(new Listcell(pPaymentref.getP25t37() + ""));
				row.appendChild(new Listcell(com.is.tf.contract.SPR.getP100Value(String.valueOf(pPaymentref.getP100t37()))));
				
				/*
				 * row.appendChild(new Listcell(pPaymentref.getId()+""));
				 * row.appendChild(new Listcell(pPaymentref.getP1t37()));
				 * row.appendChild(new Listcell(pPaymentref.getP0t37()+""));
				 * row.appendChild(new Listcell(pPaymentref.getP13t37()+""));
				 * row.appendChild(new Listcell(pPaymentref.getP14t37()));
				 * row.appendChild(new Listcell(pPaymentref.getP15t37()));
				 * row.appendChild(new Listcell(pPaymentref.getP16t37()));
				 * row.appendChild(new Listcell(pPaymentref.getP17t37()));
				 * row.appendChild(new Listcell(pPaymentref.getP18t37()));
				 * row.appendChild(new Listcell(pPaymentref.getP19t37()));
				 * row.appendChild(new
				 * Listcell(pPaymentref.getId_contract()+""));
				 * row.appendChild(new Listcell(pPaymentref.getP26t37()));
				 */

			}
		});
		exporttype = ContractService.getExporttype("1,2,3,4", alias);
		funds = com.is.tf.contract.ContractService.getFunds(idn, alias);
		garant = com.is.tf.contract.ContractService.getGarant(idn, alias);
		MoveFromEx = com.is.tf.contract.ContractService.getMoveFromExs2(idn, alias);
		policy = com.is.tf.contract.ContractService.getPolicy(idn, alias);
		paymref = com.is.tf.contract.ContractService.getPaymentrefObj(idn, alias);
		accred = com.is.tf.contract.ContractService.getAccredObj(idn, alias);
		p4t37.setModel((new ListModelList(exporttype)));
		p5t37.setModel((new ListModelList(funds)));
		ap5t37.setModel((new ListModelList(funds)));
		p1t38.setModel((new ListModelList(paymref)));
		p6t37.setModel((new ListModelList(accred)));
		ap6t37.setModel((new ListModelList(accred)));
		p24t37.setModel((new ListModelList(MoveFromEx)));
		ap24t37.setModel((new ListModelList(MoveFromEx)));
		p7t37.setModel((new ListModelList(garant)));
		ap7t37.setModel((new ListModelList(garant)));
		p8t37.setModel((new ListModelList(policy)));
		ap8t37.setModel((new ListModelList(policy)));
		ap4t37.setModel((new ListModelList(exporttype)));
		
		refreshModel(_startPageNumber);
		
		SumchangeGrid.setItemRenderer(new ListitemRenderer()
		{
			@SuppressWarnings("unchecked")
			public void render(Listitem row, Object data) throws Exception
			{
				Paymentrefsumchange pPaymentrefsumchange = (Paymentrefsumchange) data;
				
				row.setValue(pPaymentrefsumchange);
				row.appendChild(new Listcell(pPaymentrefsumchange.getP4t38() + ""));
				row.appendChild(new Listcell(pPaymentrefsumchange.getP6t38()));
				row.appendChild(new Listcell(pPaymentrefsumchange.getP14t38()));
				row.appendChild(new Listcell(pPaymentrefsumchange.getP15t38()));
				row.appendChild(new Listcell(pPaymentrefsumchange.getP18t38() + ""));
				row.appendChild(new Listcell(pPaymentrefsumchange.getP12t38() + ""));
				row.appendChild(new Listcell(com.is.tf.contract.SPR.getP100Value(String.valueOf(pPaymentrefsumchange.getP100t38()))));
				/*
				 * row.appendChild(new
				 * Listcell(pPaymentrefsumchange.getId()+""));
				 * row.appendChild(new
				 * Listcell(pPaymentrefsumchange.getP0t38()));
				 * row.appendChild(new
				 * Listcell(pPaymentrefsumchange.getP1t38()));
				 * row.appendChild(new
				 * Listcell(pPaymentrefsumchange.getP2t38()+""));
				 * row.appendChild(new
				 * Listcell(pPaymentrefsumchange.getP3t38()+""));
				 * row.appendChild(new
				 * Listcell(pPaymentrefsumchange.getP5t38()));
				 * row.appendChild(new
				 * Listcell(pPaymentrefsumchange.getP7t38()));
				 * row.appendChild(new
				 * Listcell(pPaymentrefsumchange.getP8t38()));
				 * row.appendChild(new
				 * Listcell(pPaymentrefsumchange.getP9t38()));
				 * row.appendChild(new
				 * Listcell(pPaymentrefsumchange.getP10t38()));
				 * row.appendChild(new
				 * Listcell(pPaymentrefsumchange.getId_contract()+""));
				 * row.appendChild(new
				 * Listcell(pPaymentrefsumchange.getP13t38()));
				 */

			}
		});
		
		refreshModel(_startPageNumber);
		
	}
	
	public void onPaging$paymentrefPaging(ForwardEvent event)
	{
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModel(_startPageNumber);
	}
	
	private void refreshModel(int activePage)
	{
		paymentrefPaging.setPageSize(_pageSize);
		model = new PagingListModel(activePage, _pageSize, filter, "");
		filter.setP1t37(idn);
		if (_needsTotalSizeUpdate)
		{
			_totalSize = model.getTotalSize(filter, "");
			// _needsTotalSizeUpdate = false;
		}
		
		paymentrefPaging.setTotalSize(_totalSize);
		
		dataGrid.setModel((ListModel) model);
		if (model.getSize() > 0)
		{
			this.current = (Paymentref) model.getElementAt(0);
			sendSelEvt();
		}
	}
	
	// Omitted...
	public Paymentref getCurrent()
	{
		return current;
	}
	
	public void setCurrent(Paymentref current)
	{
		this.current = current;
	}
	
	// Omitted...
	public Paymentrefsumchange getCurrentSum()
	{
		return currentSum;
	}
	
	public void setCurrentSum(Paymentrefsumchange currentSum)
	{
		this.currentSum = currentSum;
	}
	
	public void onDoubleClick$dataGrid$grd()
	{
		
		btn_save.setVisible(false);
		btn_delete.setVisible(false);
		if (sparam1 != null)
		{
			if (sparam1.equals("Filial")) // / Филиал
			{
				btn_edit.setVisible(true);
				btn_saveGlBuh.setVisible(false);
				btn_saveUpr.setVisible(false);
				btn_saveGo.setVisible(false);
				btn_confirmM.setVisible(false);
				btn_confirm.setVisible(false);
				btn_rejectM.setVisible(false);
				btn_reject.setVisible(false);
				btn_confirmSumGl.setVisible(false);
				btn_rejectSumGl.setVisible(false);
				btn_confirmSumGo.setVisible(false);
				btn_rejectSumGo.setVisible(false);
				btn_confirmSumUpr.setVisible(false);
				btn_rejectSumUpr.setVisible(false);
				
				sparam1 = "Filial";
				// alert(sparam1);
			}
			else if (sparam1.equals("Go")) // / ГО
			{
				btn_edit.setVisible(false);
				btn_confirmM.setVisible(true);
				btn_confirm.setVisible(false);
				btn_rejectM.setVisible(true);
				btn_reject.setVisible(false);
				btn_saveGlBuh.setVisible(false);
				btn_saveUpr.setVisible(false);
				btn_saveGo.setVisible(true);
				btn_confirmSumGl.setVisible(false);
				btn_rejectSumGl.setVisible(false);
				btn_confirmSumUpr.setVisible(false);
				btn_rejectSumUpr.setVisible(false);
				btn_confirmSumGo.setVisible(false);
				btn_rejectSumGo.setVisible(false);
				sparam1 = "Go";
				// alert(sparam1);
			}
			else if (sparam1.equals("GlBuhg")) // / Гл бухг
			{
				btn_edit.setVisible(false);
				btn_saveGlBuh.setVisible(true);
				btn_saveUpr.setVisible(false);
				btn_saveGo.setVisible(false);
				btn_confirmM.setVisible(false);
				btn_confirm.setVisible(false);
				btn_rejectM.setVisible(false);
				btn_reject.setVisible(false);
				btn_confirmSumGl.setVisible(true);
				btn_rejectSumGl.setVisible(true);
				btn_confirmSumUpr.setVisible(false);
				btn_rejectSumUpr.setVisible(false);
				btn_confirmSumGo.setVisible(false);
				btn_rejectSumGo.setVisible(false);
				
				sparam1 = "GlBuh";
				// alert(sparam1);
			}
			else if (sparam1.equals("Upr")) // / Управляющий филиала
			{
				btn_edit.setVisible(false);
				btn_saveGlBuh.setVisible(false);
				btn_saveUpr.setVisible(true);
				btn_saveGo.setVisible(false);
				btn_confirmM.setVisible(false);
				btn_confirm.setVisible(false);
				btn_rejectM.setVisible(false);
				btn_reject.setVisible(false);
				btn_confirmSumGl.setVisible(false);
				btn_rejectSumGl.setVisible(false);
				btn_confirmSumUpr.setVisible(true);
				btn_rejectSumUpr.setVisible(true);
				btn_confirmSumGo.setVisible(false);
				btn_rejectSumGo.setVisible(false);
				
				sparam1 = "Upr";
				// alert(sparam1);
			}
		}
		grd.setVisible(false);
		frm.setVisible(true);
		frmgrd.setVisible(true);
		paymrefsum.setVisible(true);
		tab_paymrefsum.setVisible(true);
		SumchangeGrid.setVisible(true);
		sum_div.setVisible(false);
		btn_summ_change.setVisible(true);
		tb1.setVisible(true);
		addgrd.setVisible(false);
		grid_sumchange.setVisible(false);
		fgrd.setVisible(false);
		btn_grid.setVisible(false);
		btn_back.setImage("/images/folder.png");
		btn_back.setLabel(Labels.getLabel("grid"));
		p2t37.setDisabled(true);
		p3t37.setDisabled(true);
		p4t37.setDisabled(true);
		p5t37.setDisabled(true);
		p6t37.setDisabled(true);
		p7t37.setDisabled(true);
		p8t37.setDisabled(true);
		p9t37.setDisabled(true);
		p10t37.setDisabled(true);
		p11t37.setReadonly(true);
		p12t37.setDisabled(true);
		p15t37.setDisabled(true);
		p17t37.setDisabled(true);
		p19t37.setDisabled(true);
		p21t37.setDisabled(true);
		p24t37.setDisabled(true);
		p25t37.setDisabled(true);
		
		setCurrent();
	}
	
	public void onClick$btn_refresh() throws Exception
	{
		refresh(idn);
	}
	
	public void onClick$btn_edit()
	{
		
		if (frmgrd.isVisible())
		{
			btn_save.setVisible(true);
			btn_delete.setVisible(true);
			btn_confirm.setVisible(false);
			btn_reject.setVisible(false);
			
			p2t37.setDisabled(false);
			p3t37.setDisabled(false);
			p4t37.setDisabled(false);
			p5t37.setDisabled(false);
			p6t37.setDisabled(false);
			p7t37.setDisabled(false);
			p8t37.setDisabled(false);
			p9t37.setDisabled(false);
			p10t37.setDisabled(false);
			p11t37.setReadonly(false);
			p12t37.setDisabled(false);
			p15t37.setDisabled(false);
			p17t37.setDisabled(false);
			p19t37.setDisabled(false);
			p21t37.setDisabled(false);
			p24t37.setDisabled(false);
			p25t37.setDisabled(false);
			
		}
	}
	
	public void onClick$btn_grid()
	{
		grd.setVisible(false);
		frm.setVisible(true);
		frmgrd.setVisible(true);
		grid_sumchange.setVisible(false);
		addgrd.setVisible(false);
		fgrd.setVisible(false);
		btn_summ_change.setVisible(true);
		btn_grid.setVisible(false);
		btn_back.setImage("/images/folder.png");
		btn_back.setLabel(Labels.getLabel("grid"));
		btn_search.setVisible(true);
		btn_add.setVisible(true);
		btn_first.setVisible(true);
		btn_prev.setVisible(true);
		btn_next.setVisible(true);
		btn_last.setVisible(true);
	}
	
	public void onClick$btn_edit2()
	{
		
		if (SumchangeGrid.isVisible())
		{
			sum_div.setVisible(true);
			sum_grd.setVisible(true);
			btn_save2.setVisible(true);
		}
		
	}
	
	public void onSelect$SumchangeGrid$grd()
	{
		try
		{
			if (sparam1.equals("Go") && (!CheckNull.isEmpty(currentSum.getP12t38())))
			{
				btn_confirm.setVisible(true);
				btn_reject.setVisible(true);
				btn_confirmSumGo.setVisible(false);
				btn_rejectSumGo.setVisible(false);
			}
			else if (sparam1.equals("Go") && (CheckNull.isEmpty(currentSum.getP12t38())))
			{
				btn_confirm.setVisible(false);
				btn_reject.setVisible(false);
				btn_confirmSumGo.setVisible(true);
				btn_rejectSumGo.setVisible(true);
			}
			
			btn_edit.setVisible(false);
			btn_delete2.setVisible(false);
			btn_save2.setVisible(false);
			btn_save.setVisible(true);
			btn_back2.setVisible(false);
			btn_add2.setVisible(false);
			sum_div.setVisible(true);
			sum_grd.setVisible(false);
			btn_edit2.setVisible(true);
			btn_back2.setVisible(true);
			
			// p4t23a.setReadonly(true);
			// p5t23a.setReadonly(true);
			// p6t23a.setReadonly(true);
			// p8t23a.setDisabled(true);
			// p10t23a.setReadonly(true);
			// sum_div.setVisible(true);
			// sum_grd.setVisible(true);
			// sum_grd2.setVisible(true);
			
			setCurrent();
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			alert("onDoubleClick$SumChangeGrid$grd()= " + e.getMessage());
		}
	}
	
	public void onClick$btn_back()
	{
		if (frm.isVisible())
		{
			frm.setVisible(false);
			grd.setVisible(true);
			btn_summ_change.setVisible(true);
			grid_sumchange.setVisible(false);
			btn_grid.setVisible(false);
			btn_back.setImage("/images/file.png");
			btn_back.setLabel(Labels.getLabel("back"));
			btn_search.setVisible(true);
			btn_add.setVisible(true);
			btn_first.setVisible(true);
			btn_prev.setVisible(true);
			btn_next.setVisible(true);
			btn_last.setVisible(true);
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
		btn_save.setVisible(true);
		btn_cancel.setVisible(true);
		
		ap1t37.setValue(idn);
		ap3t37.setValue(new java.util.Date());
		ap21t37.setValue((String) session.getAttribute("un"));
		
		grid_sumchange.setVisible(false);
		btn_grid.setVisible(true);
		btn_summ_change.setVisible(true);
		
		frmgrd.setVisible(false);
		addgrd.setVisible(true);
		fgrd.setVisible(false);
		// ap2t37.setValue(""+alias+new java.util.Date());
	}
	
	public void onClick$btn_search()
	{
		onDoubleClick$dataGrid$grd();
		frmgrd.setVisible(false);
		btn_summ_change.setVisible(true);
		grid_sumchange.setVisible(false);
		addgrd.setVisible(false);
		btn_grid.setVisible(true);
		fgrd.setVisible(true);
	}
	
	public void onClick$btn_summ_change()
	{
		onDoubleClick$dataGrid$grd();
		frmgrd.setVisible(false);
		btn_summ_change.setVisible(false);
		btn_search.setVisible(false);
		btn_add.setVisible(false);
		btn_first.setVisible(false);
		btn_prev.setVisible(false);
		btn_next.setVisible(false);
		btn_last.setVisible(false);
		
		grid_sumchange.setVisible(true);
		addgrd.setVisible(false);
		btn_grid.setVisible(true);
		fgrd.setVisible(false);
		btn_edit.setVisible(false);
		btn_delete2.setVisible(false);
		btn_save2.setVisible(false);
		btn_save.setVisible(true);
		btn_back2.setVisible(false);
		btn_add2.setVisible(false);
		p14t38.setValue((String) session.getAttribute("un"));
		
	}
	
	public void onClick$btn_delete()
	{
		id_contract = idc;
		try
		{
			if (current != null)
			{
				
				// /((CheckNull.isEmpty(current.getP19t37()))&&(current.getP100t37()!=null)&&((current.getP100t37().equals("9")))){
				// alert("статус 9--->"+current.getP100t37());
				Long.parseLong(id.getValue());
				current.setP1t37(p1t37.getValue());
				current.setP2t37(p2t37.getValue());
				current.setP3t37(p3t37.getValue());
				current.setP4t37(Long.parseLong(p4t37.getValue()));
				current.setP5t37(p5t37.getValue());
				current.setP6t37(p6t37.getValue());
				current.setP7t37(p7t37.getValue());
				current.setP8t37(p8t37.getValue());
				current.setP9t37(p9t37.doubleValue());
				current.setP10t37(p10t37.getValue());
				// current.setP11t37(p11t37.doubleValue());
				current.setP12t37(p12t37.getValue());
				// current.setP13t37(Long.parseLong(p13t37.getValue()));
				// current.setP14t37(p14t37.getValue());
				current.setP15t37(null);
				// current.setP16t37(p16t37.getValue());
				current.setP17t37(null);
				current.setP19t37(null);
				current.setP21t37(p21t37.getValue());
				current.setP24t37(p24t37.getValue());
				current.setP25t37(p25t37.getValue());
				// current.setP100t37(p100t37.getValue());
				Res res = PaymentrefService.remove1(current);
				if (res.getCode() == 0)
				{
					alert("Удалено1!");
					onClick$btn_back();
					refreshModel(_startPageNumber);
					SelectEvent evt = new SelectEvent("onSelect", dataGrid, dataGrid.getSelectedItems());
					Events.sendEvent(evt);
					
				}
				else
				{
					alert("Ошибка:" + res.getName());
				}
			}
			else
			{
				// alert("статус не 9--->"+current.getP100t37());
				if (!CheckNull.isEmpty(p9t37.doubleValue()) && (Double.parseDouble(summa1) > p9t37.doubleValue()))
				{
					otn = "0";
				}
				else if (!CheckNull.isEmpty(p9t37.doubleValue()) && (Double.parseDouble(summa1) < p9t37.doubleValue()))
				{
					otn = "1";
				}
				Long.parseLong(id.getValue());
				current.setP1t37(p1t37.getValue());
				current.setP2t37(p2t37.getValue());
				current.setP3t37(p3t37.getValue());
				current.setP4t37(Long.parseLong(p4t37.getValue()));
				current.setP5t37(p5t37.getValue());
				current.setP6t37(p6t37.getValue());
				current.setP7t37(p7t37.getValue());
				current.setP8t37(p8t37.getValue());
				current.setP9t37(p9t37.doubleValue());
				current.setP10t37(p10t37.getValue());
				current.setP11t37(p11t37.doubleValue());
				current.setP12t37(p12t37.getValue());
				current.setP13t37(Long.parseLong(p13t37.getValue()));
				current.setP14t37(p14t37.getValue());
				current.setP15t37(p15t37.getValue());
				current.setP16t37(p16t37.getValue());
				current.setP17t37(p17t37.getValue());
				current.setP18t37(p18t37.getValue());
				current.setP21t37(p21t37.getValue());
				current.setP24t37(p24t37.getValue());
				current.setP25t37(p25t37.getValue());
				current.setP100t37(p100t37.getValue());
				Res res = PaymentrefService.remove1(current);
				if (res.getCode() == 0)
				{
					alert("Удалено22");
					onClick$btn_back();
					refreshModel(_startPageNumber);
					SelectEvent evt = new SelectEvent("onSelect", dataGrid, dataGrid.getSelectedItems());
					Events.sendEvent(evt);
				}
				else
				{
					alert("Ошибка:" + res.getName());
				}
				
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("Error save   " + e + "id_contract " + id_contract);
		}
	}
	
	public void onClick$btn_save()
	{
		id_contract = idc;
		try
		{
			if (addgrd.isVisible())
			{
				
				Res res2 = PaymentrefService.createFilial(new Paymentref(
						// ap2t37.getValue(),
						ap3t37.getValue(),
						Long.parseLong(ap4t37.getValue()),
						ap5t37.getValue(),
						ap6t37.getValue(),
						ap7t37.getValue(),
						ap8t37.getValue(),
						ap9t37.doubleValue(),
						ap10t37.getValue(),
						// ap11t37.doubleValue(),
						ap12t37.getValue(),
						ap21t37.getValue()
						), idn, id_contract);
				if (res2.getCode() == 0)
				{
					alert("Сохранение успешно");
					onClick$btn_back();
					refreshModel(_startPageNumber);
					SelectEvent evt = new SelectEvent("onSelect", dataGrid, dataGrid.getSelectedItems());
					Events.sendEvent(evt);
				}
				else
				{
					alert("Ошибка сохранении:" + res2.getName() + ":" + res2.getCode());
				}
				
			}
			else if (grid_sumchange.isVisible())
			{
				Paymentrefsumchange paymr = new Paymentrefsumchange();
				paymr.setP0t38("0");
				paymr.setP2t38(current.getP9t37());
				paymr.setP4t38(p4t38.doubleValue());
				paymr.setP6t38(p6t38.getValue());
				paymr.setP14t38((String) session.getAttribute("un"));
				Res res = PaymentrefsumchangeService.create(paymr, idc, current.getP2t37());
				
				if (res.getCode() == 0)
				{
					alert("Cумма уменшена успешно");
					onClick$btn_back();
					refreshModel(_startPageNumber);
					SelectEvent evt = new SelectEvent("onSelect", dataGrid, dataGrid.getSelectedItems());
					Events.sendEvent(evt);
					
				}
				else
				{
					alert("Ошибка:" + res.getName());
				}
				
			}
			else if ((CheckNull.isEmpty(current.getP19t37())) && (current.getP100t37() != null) && ((current.getP100t37().equals("9"))))
			{
				// alert("статус 9--->"+current.getP100t37());
				if (!CheckNull.isEmpty(p9t37.doubleValue()) && (Double.parseDouble(summa1) > p9t37.doubleValue()))
				{
					otn = "0";
				}
				else if (!CheckNull.isEmpty(p9t37.doubleValue()) && (Double.parseDouble(summa1) < p9t37.doubleValue()))
				{
					otn = "1";
				}
				Long.parseLong(id.getValue());
				current.setP1t37(p1t37.getValue());
				current.setP2t37(p2t37.getValue());
				current.setP3t37(p3t37.getValue());
				current.setP4t37(Long.parseLong(p4t37.getValue()));
				current.setP5t37(p5t37.getValue());
				current.setP6t37(p6t37.getValue());
				current.setP7t37(p7t37.getValue());
				current.setP8t37(p8t37.getValue());
				current.setP9t37(p9t37.doubleValue());
				current.setP10t37(p10t37.getValue());
				// current.setP11t37(p11t37.doubleValue());
				current.setP12t37(p12t37.getValue());
				// current.setP13t37(Long.parseLong(p13t37.getValue()));
				// current.setP14t37(p14t37.getValue());
				current.setP15t37(null);
				// current.setP16t37(p16t37.getValue());
				current.setP17t37(null);
				current.setP19t37(null);
				current.setP21t37(p21t37.getValue());
				current.setP24t37(p24t37.getValue());
				current.setP25t37(p25t37.getValue());
				// current.setP100t37(p100t37.getValue());
				Res res = PaymentrefService.updateFilial(current);
				if (res.getCode() == 0)
				{
					alert("Корректировка произошла успешно");
					onClick$btn_back();
					refreshModel(_startPageNumber);
					SelectEvent evt = new SelectEvent("onSelect", dataGrid, dataGrid.getSelectedItems());
					Events.sendEvent(evt);
					
				}
				else
				{
					alert("Ошибка:" + res.getName());
					System.out.println("Ошибка:" + res.getName());
				}
			}
			else
			{
				// alert("статус не 9--->"+current.getP100t37());
				if (!CheckNull.isEmpty(p9t37.doubleValue()) && (Double.parseDouble(summa1) > p9t37.doubleValue()))
				{
					otn = "0";
				}
				else if (!CheckNull.isEmpty(p9t37.doubleValue()) && (Double.parseDouble(summa1) < p9t37.doubleValue()))
				{
					otn = "1";
				}
				Long.parseLong(id.getValue());
				current.setP1t37(p1t37.getValue());
				current.setP2t37(p2t37.getValue());
				current.setP3t37(p3t37.getValue());
				current.setP4t37(Long.parseLong(p4t37.getValue()));
				current.setP5t37(p5t37.getValue());
				current.setP6t37(p6t37.getValue());
				current.setP7t37(p7t37.getValue());
				current.setP8t37(p8t37.getValue());
				current.setP9t37(p9t37.doubleValue());
				current.setP10t37(p10t37.getValue());
				current.setP11t37(p11t37.doubleValue());
				current.setP12t37(p12t37.getValue());
				// current.setP13t37(Long.parseLong(p13t37.getValue()));
				// current.setP14t37(p14t37.getValue());
				current.setP15t37(p15t37.getValue());
				// current.setP16t37(p16t37.getValue());
				current.setP17t37(p17t37.getValue());
				// current.setP18t37(p18t37.getValue());
				current.setP21t37(p21t37.getValue());
				current.setP24t37(p24t37.getValue());
				current.setP25t37(p25t37.getValue());
				// current.setP100t37(p100t37.getValue());
				Res res = PaymentrefService.updateFilial(current);
				if (res.getCode() == 0)
				{
					alert("Корректировка произошла успешно");
					onClick$btn_back();
					refreshModel(_startPageNumber);
					SelectEvent evt = new SelectEvent("onSelect", dataGrid, dataGrid.getSelectedItems());
					Events.sendEvent(evt);
				}
				else
				{
					alert("Ошибка:" + res.getName());
					System.out.println("Ошибка:" + res.getName());
				}
				
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("Error save   " + e + "id_contract " + id_contract);
		}
	}
	
	public void onClick$btn_confirmSumGl()
	{
		try
		{
			if (paymrefsum.isVisible())
			{
				if (currentSum.getP100t38().equals("9") || currentSum.getP100t38().equals("u") || currentSum.getP100t38().equals("b"))
				{
					Paymentrefsumchange paymr = new Paymentrefsumchange();
					paymr.setId(currentSum.getId());
					paymr.setP0t38(currentSum.getP0t38());
					paymr.setP12t38(currentSum.getP12t38());
					paymr.setP4t38(currentSum.getP4t38());
					paymr.setP6t38(currentSum.getP6t38());
					paymr.setP8t38((String) session.getAttribute("un"));
					paymr.setP10t38(null);
					paymr.setP14t38(currentSum.getP14t38());
					paymr.setP15t38(null);
					paymr.setP100t38("8");
					Res res = PaymentrefsumchangeService.updateGlBuh(paymr, idc, current.getP2t37());
					if (res.getCode() == 0)
					{
						alert("Подтверждено");
					}
					else
					{
						alert("Ошибка:" + res.getName() + res.toString());
					}
					
				}
				else
				{
					alert("Нельзя подтвердить!");
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		finally
		{
			
		}
		
		onClick$btn_back();
		refreshModel(_startPageNumber);
		SelectEvent evt = new SelectEvent("onSelect", dataGrid, dataGrid.getSelectedItems());
		Events.sendEvent(evt);
	}
	
	public void onClick$btn_confirmSumUpr()
	{
		try
		{
			if (paymrefsum.isVisible())
			{
				if (currentSum.getP100t38().equals("8") || currentSum.getP100t38().equals("u") || currentSum.getP100t38().equals("g"))
				{
					Paymentrefsumchange paymr = new Paymentrefsumchange();
					paymr.setId(currentSum.getId());
					paymr.setP0t38(currentSum.getP0t38());
					paymr.setP12t38(currentSum.getP12t38());
					paymr.setP4t38(currentSum.getP4t38());
					paymr.setP6t38(currentSum.getP6t38());
					paymr.setP8t38(currentSum.getP8t38());
					paymr.setP10t38((String) session.getAttribute("un"));
					paymr.setP14t38(currentSum.getP14t38());
					paymr.setP15t38(null);
					paymr.setP100t38("7");
					Res res = PaymentrefsumchangeService.updateGlBuh(paymr, idc, current.getP2t37());
					if (res.getCode() == 0)
					{
						alert("Подтверждено");
					}
					else
					{
						alert("Ошибка:" + res.getName() + res.toString());
					}
					
				}
				else
				{
					alert("Главный бухгалтер должен подтвердить документ!");
				}
				
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
			
		}
		
		onClick$btn_back();
		refreshModel(_startPageNumber);
		SelectEvent evt = new SelectEvent("onSelect", dataGrid, dataGrid.getSelectedItems());
		Events.sendEvent(evt);
	}
	
	public void onClick$btn_rejectSumUpr()
	{
		try
		{
			if (paymrefsum.isVisible())
			{
				if (currentSum.getP100t38().equals("8") || currentSum.getP100t38().equals("g"))
				{
					Paymentrefsumchange paymr = new Paymentrefsumchange();
					paymr.setId(currentSum.getId());
					paymr.setP0t38(currentSum.getP0t38());
					paymr.setP12t38(currentSum.getP12t38());
					paymr.setP4t38(currentSum.getP4t38());
					paymr.setP6t38(currentSum.getP6t38());
					paymr.setP8t38(currentSum.getP8t38());
					paymr.setP10t38((String) session.getAttribute("un"));
					paymr.setP14t38(currentSum.getP14t38());
					paymr.setP15t38(null);
					paymr.setP100t38("u");
					Res res = PaymentrefsumchangeService.updateGlBuh(paymr, idc, current.getP2t37());
					if (res.getCode() == 0)
					{
						alert("Отклонено");
					}
					else
					{
						alert("Ошибка:" + res.getName() + res.toString());
					}
				}
			}
			else
			{
				alert("Нельзя отклонить!");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		finally
		{
			
		}
		
		onClick$btn_back();
		refreshModel(_startPageNumber);
		SelectEvent evt = new SelectEvent("onSelect", dataGrid, dataGrid.getSelectedItems());
		Events.sendEvent(evt);
	}
	
	public void onClick$btn_rejectSumGl()
	{
		try
		{
			if (paymrefsum.isVisible())
			{
				if (currentSum.getP100t38().equals("9") || currentSum.getP100t38().equals("u"))
				{
					Paymentrefsumchange paymr = new Paymentrefsumchange();
					paymr.setId(currentSum.getId());
					paymr.setP0t38(currentSum.getP0t38());
					paymr.setP12t38(currentSum.getP12t38());
					paymr.setP4t38(currentSum.getP4t38());
					paymr.setP6t38(currentSum.getP6t38());
					paymr.setP8t38((String) session.getAttribute("un"));
					paymr.setP10t38(null);
					paymr.setP14t38(currentSum.getP14t38());
					paymr.setP15t38(null);
					paymr.setP100t38("b");
					Res res = PaymentrefsumchangeService.updateGlBuh(paymr, idc, current.getP2t37());
					if (res.getCode() == 0)
					{
						alert("Отклонено");
					}
					else
					{
						alert("Ошибка:" + res.getName() + res.toString());
					}
				}
			}
			else
			{
				alert("Нельзя отклонить!");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		finally
		{
			
		}
		
		onClick$btn_back();
		refreshModel(_startPageNumber);
		SelectEvent evt = new SelectEvent("onSelect", dataGrid, dataGrid.getSelectedItems());
		Events.sendEvent(evt);
	}
	
	public void onClick$btn_confirmSumGo()
	{
		try
		{
			if (currentSum.getP10t38() != null && ((currentSum.getP100t38().equals("7") || currentSum.getP100t38().equals("g"))))
			{
				
				// if
				// ((current.getP2t37()!=null)&&((current.getP100t37().equals("7")))){
				
				final BankServiceProxy ws = new BankServiceProxy((String) session.getAttribute("YESVO_URL"));
				com.sbs.service.PaymentRefSumChange pmtrSum = new com.sbs.service.PaymentRefSumChange();
				
				PaymentRefSumChangeResult ar = ws.savePaymentRefSumChange((String) (session.getAttribute("BankINN")), idn, current.getP2t37(), getPmtrSumNew(currentSum));
				// alert("current="+current.getP0t37()+" 0="+p0t37.getValue());
				if (ar.getStatus() == 0)
				{
					PaymentrefsumchangeService.remove(currentSum);
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
				
				// }
				
			}
			else
			{
				alert("Управляющий филиала не подтвердил документ!");
				onClick$btn_back();
				
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
	
	public void onClick$btn_rejectSumGo()
	{
		try
		{
			if (paymrefsum.isVisible())
			{
				if (currentSum.getP100t38().equals("7") || currentSum.getP100t38().equals("g"))
				{
					Paymentrefsumchange paymr = new Paymentrefsumchange();
					paymr.setId(currentSum.getId());
					paymr.setP0t38(currentSum.getP0t38());
					paymr.setP12t38(currentSum.getP12t38());
					paymr.setP4t38(currentSum.getP4t38());
					paymr.setP6t38(currentSum.getP6t38());
					paymr.setP8t38(currentSum.getP8t38());
					paymr.setP10t38(currentSum.getP10t38());
					paymr.setP14t38(currentSum.getP14t38());
					paymr.setP15t38((String) session.getAttribute("un"));
					paymr.setP100t38("g");
					Res res = PaymentrefsumchangeService.updateGlBuh(paymr, idc, current.getP2t37());
					if (res.getCode() == 0)
					{
						alert("Отклонено");
					}
					else
					{
						alert("Ошибка:" + res.getName() + res.toString());
					}
				}
			}
			else
			{
				alert("Нельзя отклонить!");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		finally
		{
			
		}
		
		onClick$btn_back();
		refreshModel(_startPageNumber);
		SelectEvent evt = new SelectEvent("onSelect", dataGrid, dataGrid.getSelectedItems());
		Events.sendEvent(evt);
	}
	
	public void onClick$btn_saveGlBuh()
	{
		id_contract = idc;
		try
		{
			if (current.getP21t37() != null)
			{
				if ((current.getP100t37() != null) && ((current.getP100t37().equals("9"))))
				{
					// alert("статус 9--->"+current.getP100t37());
					if (!CheckNull.isEmpty(p9t37.doubleValue()) && (Double.parseDouble(summa1) > p9t37.doubleValue()))
					{
						otn = "0";
					}
					else if (!CheckNull.isEmpty(p9t37.doubleValue()) && (Double.parseDouble(summa1) < p9t37.doubleValue()))
					{
						otn = "1";
					}
					Long.parseLong(id.getValue());
					current.setP1t37(p1t37.getValue());
					current.setP2t37(p2t37.getValue());
					current.setP3t37(p3t37.getValue());
					current.setP4t37(Long.parseLong(p4t37.getValue()));
					current.setP5t37(p5t37.getValue());
					current.setP6t37(p6t37.getValue());
					current.setP7t37(p7t37.getValue());
					current.setP8t37(p8t37.getValue());
					current.setP9t37(p9t37.doubleValue());
					current.setP10t37(p10t37.getValue());
					// current.setP11t37(p11t37.doubleValue());
					current.setP12t37(p12t37.getValue());
					// current.setP13t37(Long.parseLong(p13t37.getValue()));
					// current.setP14t37(p14t37.getValue());
					current.setP15t37((String) session.getAttribute("un"));
					// current.setP16t37(p16t37.getValue());
					current.setP17t37(p17t37.getValue());
					current.setP19t37(p19t37.getValue());
					current.setP21t37(p21t37.getValue());
					current.setP24t37(p24t37.getValue());
					current.setP25t37(p25t37.getValue());
					// current.setP100t37(p100t37.getValue());
					Res res = PaymentrefService.updateGlbuh(current);
					if (res.getCode() == 0)
					{
						alert("Корректировка произошла успешно");
						onClick$btn_back();
						refreshModel(_startPageNumber);
						SelectEvent evt = new SelectEvent("onSelect", dataGrid, dataGrid.getSelectedItems());
						Events.sendEvent(evt);
						
					}
					else
					{
						alert("Ошибка:" + res.getName());
					}
				}
				
				else
				{
					// alert("статус не 9--->"+current.getP100t37());
					if (!CheckNull.isEmpty(p9t37.doubleValue()) && (Double.parseDouble(summa1) > p9t37.doubleValue()))
					{
						otn = "0";
					}
					else if (!CheckNull.isEmpty(p9t37.doubleValue()) && (Double.parseDouble(summa1) < p9t37.doubleValue()))
					{
						otn = "1";
					}
					Long.parseLong(id.getValue());
					current.setP1t37(p1t37.getValue());
					current.setP2t37(p2t37.getValue());
					current.setP3t37(p3t37.getValue());
					current.setP4t37(Long.parseLong(p4t37.getValue()));
					current.setP5t37(p5t37.getValue());
					current.setP6t37(p6t37.getValue());
					current.setP7t37(p7t37.getValue());
					current.setP8t37(p8t37.getValue());
					current.setP9t37(p9t37.doubleValue());
					current.setP10t37(p10t37.getValue());
					current.setP11t37(p11t37.doubleValue());
					current.setP12t37(p12t37.getValue());
					// current.setP13t37(Long.parseLong(p13t37.getValue()));
					// current.setP14t37(p14t37.getValue());
					current.setP15t37((String) session.getAttribute("un"));
					current.setP16t37(p16t37.getValue());
					current.setP17t37(p17t37.getValue());
					current.setP18t37(p18t37.getValue());
					current.setP21t37(p21t37.getValue());
					current.setP24t37(p24t37.getValue());
					current.setP25t37(p25t37.getValue());
					current.setP100t37(p100t37.getValue());
					Res res = PaymentrefService.updateGlbuh(current);
					if (res.getCode() == 0)
					{
						alert("Корректировка произошла успешно");
						onClick$btn_back();
						refreshModel(_startPageNumber);
						SelectEvent evt = new SelectEvent("onSelect", dataGrid, dataGrid.getSelectedItems());
						Events.sendEvent(evt);
					}
					else
					{
						alert("Ошибка:" + res.getName());
					}
					
				}
			}
			else
			{
				alert("Сотрудник банка не подтвердил документ!");
				
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("Error save   " + e + "id_contract " + id_contract);
		}
		onClick$btn_back();
		refreshModel(_startPageNumber);
		SelectEvent evt = new SelectEvent("onSelect", dataGrid, dataGrid.getSelectedItems());
		Events.sendEvent(evt);
	}
	
	public void onClick$btn_saveUpr()
	{
		id_contract = idc;
		try
		{
			if (current.getP15t37() != null)
			{
				
				if ((current.getP100t37() != null) && ((current.getP100t37().equals("8"))))
				{
					// alert("статус 9--->"+current.getP100t37());
					if (!CheckNull.isEmpty(p9t37.doubleValue()) && (Double.parseDouble(summa1) > p9t37.doubleValue()))
					{
						otn = "0";
					}
					else if (!CheckNull.isEmpty(p9t37.doubleValue()) && (Double.parseDouble(summa1) < p9t37.doubleValue()))
					{
						otn = "1";
					}
					Long.parseLong(id.getValue());
					current.setP1t37(p1t37.getValue());
					current.setP2t37(p2t37.getValue());
					current.setP3t37(p3t37.getValue());
					current.setP4t37(Long.parseLong(p4t37.getValue()));
					current.setP5t37(p5t37.getValue());
					current.setP6t37(p6t37.getValue());
					current.setP7t37(p7t37.getValue());
					current.setP8t37(p8t37.getValue());
					current.setP9t37(p9t37.doubleValue());
					current.setP10t37(p10t37.getValue());
					// current.setP11t37(p11t37.doubleValue());
					current.setP12t37(p12t37.getValue());
					// current.setP13t37(Long.parseLong(p13t37.getValue()));
					// current.setP14t37(p14t37.getValue());
					current.setP15t37(p15t37.getValue());
					// current.setP16t37(p16t37.getValue());
					current.setP17t37((String) session.getAttribute("un"));
					current.setP19t37(p19t37.getValue());
					current.setP21t37(p21t37.getValue());
					current.setP24t37(p24t37.getValue());
					current.setP25t37(p25t37.getValue());
					// current.setP100t37(p100t37.getValue());
					Res res = PaymentrefService.updateUpr(current);
					if (res.getCode() == 0)
					{
						alert("Корректировка произошла успешно");
						onClick$btn_back();
						refreshModel(_startPageNumber);
						SelectEvent evt = new SelectEvent("onSelect", dataGrid, dataGrid.getSelectedItems());
						Events.sendEvent(evt);
						
					}
					else
					{
						alert("Ошибка:" + res.getName());
					}
				}
				else
				{
					// alert("статус не 9--->"+current.getP100t37());
					if (!CheckNull.isEmpty(p9t37.doubleValue()) && (Double.parseDouble(summa1) > p9t37.doubleValue()))
					{
						otn = "0";
					}
					else if (!CheckNull.isEmpty(p9t37.doubleValue()) && (Double.parseDouble(summa1) < p9t37.doubleValue()))
					{
						otn = "1";
					}
					Long.parseLong(id.getValue());
					current.setP1t37(p1t37.getValue());
					current.setP2t37(p2t37.getValue());
					current.setP3t37(p3t37.getValue());
					current.setP4t37(Long.parseLong(p4t37.getValue()));
					current.setP5t37(p5t37.getValue());
					current.setP6t37(p6t37.getValue());
					current.setP7t37(p7t37.getValue());
					current.setP8t37(p8t37.getValue());
					current.setP9t37(p9t37.doubleValue());
					current.setP10t37(p10t37.getValue());
					current.setP11t37(p11t37.doubleValue());
					current.setP12t37(p12t37.getValue());
					// current.setP13t37(Long.parseLong(p13t37.getValue()));
					current.setP14t37(p14t37.getValue());
					current.setP15t37(p15t37.getValue());
					current.setP17t37((String) session.getAttribute("un") + " :(Upr.Banka-podtverdil)");
					current.setP18t37(p18t37.getValue());
					current.setP21t37(p21t37.getValue());
					current.setP24t37(p24t37.getValue());
					current.setP25t37(p25t37.getValue());
					current.setP100t37(p100t37.getValue());
					Res res = PaymentrefService.updateUpr(current);
					if (res.getCode() == 0)
					{
						alert("Корректировка произошла успешно");
						onClick$btn_back();
						refreshModel(_startPageNumber);
						SelectEvent evt = new SelectEvent("onSelect", dataGrid, dataGrid.getSelectedItems());
						Events.sendEvent(evt);
					}
					else
					{
						alert("Ошибка:" + res.getName());
					}
					
				}
				
			}
			else
			{
				alert("Главный Бухгалтер не подтвердил");
				
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("Error save   " + e + "id_contract " + id_contract);
		}
		onClick$btn_back();
		refreshModel(_startPageNumber);
		SelectEvent evt = new SelectEvent("onSelect", dataGrid, dataGrid.getSelectedItems());
		Events.sendEvent(evt);
	}
	
	public void onClick$btn_saveGo()
	{
		try
		{
			if (current.getP17t37() != null)
			{
				
				if ((current.getP2t37() != null) && ((current.getP100t37().equals("7"))))
				{
					
					final BankServiceProxy ws = new BankServiceProxy((String) session.getAttribute("YESVO_URL"));
					com.sbs.service.PaymentRef pmtr = new com.sbs.service.PaymentRef();
					
					Long.parseLong(id.getValue());
					current.setP1t37(p1t37.getValue());
					// current.setP0t37(Long.parseLong(p0t37.getValue()));
					current.setP3t37(new java.util.Date());
					current.setP4t37(Long.parseLong(p4t37.getValue()));
					current.setP5t37(p5t37.getValue());
					current.setP6t37(p6t37.getValue());
					current.setP7t37(p7t37.getValue());
					current.setP8t37(p8t37.getValue());
					current.setP9t37(p9t37.doubleValue());
					current.setP10t37(p10t37.getValue());
					// current.setP11t37(p11t37.doubleValue());
					current.setP12t37(p12t37.getValue());
					// current.setP14t37(p14t37.getValue());
					current.setP15t37(p15t37.getValue());
					// current.setP16t37(p16t37.getValue());
					current.setP17t37(p17t37.getValue());
					// current.setP18t37(p18t37.getValue());
					current.setP21t37(p21t37.getValue());
					current.setP24t37(p24t37.getValue());
					// current.setP25t37(p25t37.getValue());
					// current.setP100t37(p100t37.getValue());
					// PaymentrefService.update(current);
					PaymentRefResult ar = ws.savePaymentRef((String) (session.getAttribute("BankINN")), idn, getPmtrCorrect(current));
					// alert("current="+current.getP0t37()+" 0="+p0t37.getValue());
					if (ar.getStatus() == 0)
					{
						PaymentrefService.remove1(current);
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
				else
				{
					
					final BankServiceProxy ws = new BankServiceProxy((String) session.getAttribute("YESVO_URL"));
					com.sbs.service.PaymentRef pmtr = new com.sbs.service.PaymentRef();
					
					Long.parseLong(id.getValue());
					// current.setP1t37(p1t37.getValue());
					// current.setP0t37(Long.parseLong(p0t37.getValue()));
					current.setP3t37(new java.util.Date());
					current.setP4t37(Long.parseLong(p4t37.getValue()));
					current.setP5t37(p5t37.getValue());
					current.setP6t37(p6t37.getValue());
					current.setP7t37(p7t37.getValue());
					current.setP8t37(p8t37.getValue());
					current.setP9t37(p9t37.doubleValue());
					current.setP10t37(p10t37.getValue());
					// current.setP11t37(p11t37.doubleValue());
					current.setP12t37(p12t37.getValue());
					// current.setP14t37(p14t37.getValue());
					current.setP15t37(p15t37.getValue());
					// current.setP16t37(p16t37.getValue());
					current.setP17t37(p17t37.getValue());
					// current.setP18t37(p18t37.getValue());
					current.setP21t37(p21t37.getValue());
					current.setP24t37(p24t37.getValue());
					// current.setP25t37(p25t37.getValue());
					// current.setP100t37(p100t37.getValue());
					// PaymentrefService.update(current);
					PaymentRefResult ar = ws.savePaymentRef((String) (session.getAttribute("BankINN")), idn, getPmtrNew(current));
					// alert("current="+current.getP0t37()+" 0="+p0t37.getValue());
					if (ar.getStatus() == 0)
					{
						PaymentrefService.remove1(current);
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
					}
				}
			}
			else
			{
				alert("Управляющий филиала не подтвердил документ!");
				onClick$btn_back();
				
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
			filter = new PaymentrefFilter();
		}
		onClick$btn_back();
		frmgrd.setVisible(true);
		addgrd.setVisible(false);
		fgrd.setVisible(false);
		CheckNull.clearForm(addgrd);
		CheckNull.clearForm(fgrd);
		refreshModel(_startPageNumber);
	}
	
	private com.sbs.service.PaymentRef getPmtrNew(Paymentref acr) throws Exception
	{
		java.util.Calendar cal = java.util.Calendar.getInstance();
		java.util.Calendar cal2 = java.util.Calendar.getInstance();
		com.sbs.service.PaymentRef res = new com.sbs.service.PaymentRef();
		
		res.setP0T37(0);
		// res.setP2T37(acr.getP2t37());
		cal.setTime(df.parse(df.format(acr.getP3t37())));
		res.setP3T37(cal);
		res.setP4T37(Short.parseShort("" + acr.getP4t37()));
		if ((acr.getP5t37() != null) && (!CheckNull.isEmpty(acr.getP5t37())))
		{
			res.setP5T37(Integer.parseInt(acr.getP5t37()));
		}
		if ((acr.getP6t37() != null) && (!CheckNull.isEmpty(acr.getP6t37())))
		{
			res.setP6T37(acr.getP6t37());
		}
		if ((acr.getP7t37() != null) && (!CheckNull.isEmpty(acr.getP7t37())))
		{
			res.setP7T37(acr.getP7t37());
		}
		if ((acr.getP8t37() != null) && (!CheckNull.isEmpty(acr.getP8t37())))
		{
			res.setP8T37(acr.getP8t37());
		}
		res.setP9T37(acr.getP9t37());
		res.setP10T37(acr.getP10t37());
		// res.setP11T37(Double.parseDouble(""+acr.getP11t37()));
		if (acr.getP12t37() != null)
		{
			cal2.setTime(df.parse(df.format(acr.getP12t37())));
			res.setP12T37(cal2);
		}
		
		res.setP15T37((acr.getP15t37()));
		res.setP17T37((acr.getP17t37()));
		res.setP19T37((String) session.getAttribute("un"));
		res.setP21T37((acr.getP21t37()));
		if ((acr.getP24t37() != null) && (!CheckNull.isEmpty(acr.getP24t37())))
		{
			res.setP24T37(Integer.parseInt(acr.getP24t37()));
		}
		
		return res;
	}
	
	private com.sbs.service.PaymentRef getPmtrCorrect(Paymentref acr) throws Exception
	{
		java.util.Calendar cal = java.util.Calendar.getInstance();
		java.util.Calendar cal2 = java.util.Calendar.getInstance();
		com.sbs.service.PaymentRef res = new com.sbs.service.PaymentRef();
		res.setP0T37(1);
		res.setP2T37(acr.getP2t37());
		cal.setTime(df.parse(df.format(acr.getP3t37())));
		res.setP3T37(cal);
		res.setP4T37(Short.parseShort("" + acr.getP4t37()));
		if ((acr.getP5t37() != null) && (!CheckNull.isEmpty(acr.getP5t37())))
		{
			res.setP5T37(Integer.parseInt(acr.getP5t37()));
		}
		if ((acr.getP6t37() != null) && (!CheckNull.isEmpty(acr.getP6t37())))
		{
			res.setP6T37(acr.getP6t37());
		}
		if ((acr.getP7t37() != null) && (!CheckNull.isEmpty(acr.getP7t37())))
		{
			res.setP7T37(acr.getP7t37());
		}
		if ((acr.getP8t37() != null) && (!CheckNull.isEmpty(acr.getP8t37())))
		{
			res.setP8T37(acr.getP8t37());
		}
		res.setP9T37(acr.getP9t37());
		res.setP10T37(acr.getP10t37());
		// res.setP11T37(Double.parseDouble(""+acr.getP11t37()));
		if (acr.getP12t37() != null)
		{
			cal2.setTime(df.parse(df.format(acr.getP12t37())));
			res.setP12T37(cal2);
		}
		
		res.setP15T37((acr.getP15t37()));
		res.setP17T37((acr.getP17t37()));
		res.setP19T37((String) session.getAttribute("un"));
		res.setP21T37((acr.getP21t37()));
		if ((acr.getP24t37() != null) && (!CheckNull.isEmpty(acr.getP24t37())))
		{
			res.setP24T37(Integer.parseInt(acr.getP24t37()));
		}
		
		return res;
	}
	
	private com.sbs.service.PaymentRefSumChange getPmtrSumNew(Paymentrefsumchange acr) throws Exception
	{
		com.sbs.service.PaymentRefSumChange res = new com.sbs.service.PaymentRefSumChange();
		res.setP0T38(0);
		res.setP4T38(acr.getP4t38());
		res.setP6T38(acr.getP6t38());
		res.setP8T38(acr.getP8t38());
		res.setP10T38(acr.getP10t38());
		res.setP14T38(acr.getP14t38());
		res.setP15T38((String) session.getAttribute("un"));
		return res;
	}
	
	public void onSelect$p4t37()
	{
		if (p4t37.getValue().equalsIgnoreCase("1"))
		{
			row_p5t37.setVisible(true);
			row_p24t37.setVisible(true);
			row_p6t37.setVisible(false);
			row_p7t37.setVisible(false);
			row_p8t37.setVisible(false);
			row_p12t37.setVisible(false);
			
		}
		else if (p4t37.getValue().equalsIgnoreCase("2"))
		{
			row_p5t37.setVisible(false);
			row_p24t37.setVisible(false);
			row_p6t37.setVisible(true);
			row_p7t37.setVisible(false);
			row_p12t37.setVisible(true);
			row_p8t37.setVisible(false);
		}
		else if (p4t37.getValue().equalsIgnoreCase("3"))
		{
			row_p5t37.setVisible(false);
			row_p24t37.setVisible(false);
			row_p6t37.setVisible(false);
			row_p12t37.setVisible(true);
			row_p7t37.setVisible(true);
			row_p8t37.setVisible(false);
		}
		else if (p4t37.getValue().equalsIgnoreCase("4"))
		{
			row_p5t37.setVisible(false);
			row_p24t37.setVisible(false);
			row_p12t37.setVisible(true);
			row_p6t37.setVisible(false);
			row_p7t37.setVisible(false);
			row_p8t37.setVisible(true);
			
		}
	}
	
	public void onSelect$p5t37()
	{
		Fund fund = ((Fund) p5t37.getObject());
		p24t37.setValue("");
		p6t37.setValue("");
		p7t37.setValue("");
		p8t37.setValue("");
		
		fund_val = fund.getP13t35().toString();
		p10t37.setValue(fund.getP13t35());
		p10t37_img.setValue(curr_.get(fund_val));
		val_p9t37.setValue(curr_.get(fund_val));
		val_p4t38.setValue(curr_.get(fund_val));
		// alert("p10t37="+current.getP10t37());
		// System.out.println("onSelect$p5t37()   "+"p24t37.getValue()="+p24t37.getValue()+" p5t37.getValue()= "
		// +p5t37.getValue()+" p6t37.getValue()"+p6t37.getValue());
	}
	
	public void onSelect$ap4t37()
	{
		if (ap4t37.getValue().equalsIgnoreCase("1"))
		{
			
			row_ap5t37.setVisible(true);
			row_ap24t37.setVisible(true);
			row_ap6t37.setVisible(false);
			row_ap7t37.setVisible(false);
			p12t37.setValue(null);
			row_ap12t37.setVisible(false);
			row_ap8t37.setVisible(false);
			
		}
		else if (ap4t37.getValue().equalsIgnoreCase("2"))
		{
			row_ap5t37.setVisible(false);
			row_ap24t37.setVisible(false);
			row_ap6t37.setVisible(true);
			row_ap7t37.setVisible(false);
			row_ap12t37.setVisible(true);
			row_ap8t37.setVisible(false);
		}
		else if (ap4t37.getValue().equalsIgnoreCase("3"))
		{
			row_ap5t37.setVisible(false);
			row_ap24t37.setVisible(false);
			row_ap6t37.setVisible(false);
			row_ap12t37.setVisible(true);
			row_ap7t37.setVisible(true);
			row_ap8t37.setVisible(false);
		}
		else if (ap4t37.getValue().equalsIgnoreCase("4"))
		{
			row_ap5t37.setVisible(false);
			row_ap24t37.setVisible(false);
			row_ap6t37.setVisible(false);
			row_ap12t37.setVisible(true);
			row_ap7t37.setVisible(false);
			row_ap8t37.setVisible(true);
			
		}
	}
	
	public void onSelect$ap5t37()
	{
		Fund fund = ((Fund) ap5t37.getObject());
		ap24t37.setValue("");
		ap6t37.setValue("");
		ap7t37.setValue("");
		ap8t37.setValue("");
		
		afund_val = fund.getP13t35().toString();
		ap10t37.setValue(fund.getP13t35());
		ap10t37_img.setValue(curr_.get(afund_val));
		val_ap9t37.setValue(curr_.get(afund_val));
		System.out.println("ap10t37_img.getValue()=" + ap10t37_img.getValue() + " ap10t37.= " + ap10t37.getValue());
		// System.out.println("	afund_val "+afund_val+"ap10t37 "+ap10t37.getValue());
	}
	
	public void onSelect$p6t37()
	{
		Accreditiv arred = ((Accreditiv) p6t37.getObject());
		p24t37.setValue("");
		p5t37.setValue("");
		p7t37.setValue("");
		p8t37.setValue("");
		
		accred_val = arred.getP4t21().toString();
		accred_date = arred.getP3t21();
		p10t37.setValue(arred.getP4t21());
		p10t37_img.setValue(curr_.get(accred_val));
		p12t37.setValue(accred_date);
		val_p9t37.setValue(curr_.get(accred_val));
		// System.out.println("onSelect$p6t37()   "+" p24t37.getValue()= "+p24t37.getValue()+"  p5t37.getValue()= "
		// +p5t37.getValue()+"  p6t37.getValue()="+p6t37.getValue());
	}
	
	public void onSelect$ap6t37()
	{
		Accreditiv arred = ((Accreditiv) ap6t37.getObject());
		ap24t37.setValue("");
		ap5t37.setValue("");
		ap7t37.setValue("");
		ap8t37.setValue("");
		aaccred_date = arred.getP3t21();
		aaccred_val = arred.getP4t21().toString();
		ap10t37.setValue(arred.getP4t21());
		ap10t37_img.setValue(curr_.get(aaccred_val));
		ap12t37.setValue(aaccred_date);
		val_ap9t37.setValue(curr_.get(aaccred_val));
		
	}
	
	public void onSelect$p7t37()
	{
		Garant gar = ((Garant) p7t37.getObject());
		p24t37.setValue("");
		p5t37.setValue("");
		p6t37.setValue("");
		p8t37.setValue("");
		gar_val = gar.getP5t18().toString();
		gar_date = gar.getP4t18();
		p10t37_img.setValue(curr_.get(gar_val));
		p10t37.setValue(gar.getP5t18());
		p12t37.setValue(gar_date);
		val_p9t37.setValue(curr_.get(gar_val));
		// System.out.println("onSelect$p6t37()   "+" p24t37.getValue()= "+p24t37.getValue()+"  p5t37.getValue()= "
		// +p5t37.getValue()+"  p6t37.getValue()="+p6t37.getValue());
	}
	
	public void onSelect$ap7t37()
	{
		Garant agar = ((Garant) ap7t37.getObject());
		ap24t37.setValue("");
		ap5t37.setValue("");
		ap6t37.setValue("");
		ap8t37.setValue("");
		agar_date = agar.getP4t18();
		agar_val = agar.getP5t18().toString();
		ap12t37.setValue(agar_date);
		ap10t37.setValue(agar.getP5t18());
		ap10t37_img.setValue(curr_.get(agar_val));
		val_ap9t37.setValue(curr_.get(agar_val));
		
	}
	
	public void onSelect$p8t37()
	{
		Policy pol = ((Policy) p8t37.getObject());
		p24t37.setValue("");
		p5t37.setValue("");
		p6t37.setValue("");
		p7t37.setValue("");
		pol_date = pol.getP5t32();
		pol_val = pol.getP6t32().toString();
		p12t37.setValue(pol_date);
		p10t37_img.setValue(curr_.get(pol_val));
		p10t37.setValue(pol.getP6t32());
		val_p9t37.setValue(curr_.get(apol_val));
		// System.out.println("onSelect$p6t37()   "+" p24t37.getValue()= "+p24t37.getValue()+"  p5t37.getValue()= "
		// +p5t37.getValue()+"  p6t37.getValue()="+p6t37.getValue());
	}
	
	public void onSelect$ap8t37()
	{
		Policy apol = ((Policy) ap8t37.getObject());
		ap24t37.setValue("");
		ap5t37.setValue("");
		ap6t37.setValue("");
		apol_date = apol.getP5t32();
		ap7t37.setValue("");
		apol_val = apol.getP6t32().toString();
		ap12t37.setValue(apol_date);
		ap10t37.setValue(apol.getP6t32());
		ap10t37_img.setValue(curr_.get(apol_val));
		val_ap9t37.setValue(curr_.get(apol_val));
		
	}
	
	private void setCurrent()
	{
		if (current != null)
		{
			// alert("current.getP4t37()="+current.getP4t37());
			SumchangeGrid.setModel(new BindingListModelList(current.getSumchanges(), true));
			
			if (current.getP4t37().equals(1))
			{
				row_p5t37.setVisible(true);
				row_p24t37.setVisible(true);
				row_p6t37.setVisible(false);
				row_p7t37.setVisible(false);
				row_p8t37.setVisible(false);
				row_p12t37.setVisible(false);
				
			}
			else if (current.getP4t37().equals(2))
			{
				row_p5t37.setVisible(false);
				row_p24t37.setVisible(false);
				row_p6t37.setVisible(true);
				row_p7t37.setVisible(false);
				row_p12t37.setVisible(true);
				row_p8t37.setVisible(false);
			}
			else if (current.getP4t37().equals(3))
			{
				row_p5t37.setVisible(false);
				row_p24t37.setVisible(false);
				row_p6t37.setVisible(false);
				row_p12t37.setVisible(true);
				row_p7t37.setVisible(true);
				row_p8t37.setVisible(false);
			}
			else if (current.getP4t37().equals(4))
			{
				row_p5t37.setVisible(false);
				row_p24t37.setVisible(false);
				row_p12t37.setVisible(true);
				row_p6t37.setVisible(false);
				row_p7t37.setVisible(false);
				row_p8t37.setVisible(true);
			}
			if (current.getP12t37() != null)
			{
				row_p12t37.setVisible(true);
			}
			else
			{
				row_p12t37.setVisible(false);
			}
			if (current.getP10t37() != null)
			{
				p10t37_img.setValue(curr_.get(current.getP10t37()));
			}
			if (current.getP7t37() != null)
			{
				row_p7t37.setVisible(true);
			}
			else
			{
				row_p7t37.setVisible(false);
			}
			if (current.getP24t37() != null)
			{
				row_p24t37.setVisible(true);
			}
			else
			{
				row_p24t37.setVisible(false);
			}
			if (current.getP8t37() != null)
			{
				row_p8t37.setVisible(true);
			}
			else
			{
				row_p8t37.setVisible(false);
			}
			if (current.getP6t37() != null)
			{
				row_p6t37.setVisible(true);
			}
			else
			{
				row_p6t37.setVisible(false);
			}
			
			if (current.getP15t37() != null)
			{
				row_p15t37.setVisible(true);
			}
			if (current.getP17t37() != null)
			{
				row_p17t37.setVisible(true);
			}
			if (current.getP19t37() != null)
			{
				row_p19t37.setVisible(true);
			}
			if (current.getP25t37() != null)
			{
				row_p25t37.setVisible(true);
			}
			
		}
		
	}
	
	public void refresh(String idn) throws Exception
	{
		
		final BankServiceProxy ws = new BankServiceProxy((String) session.getAttribute("YESVO_URL"));
		com.is.tf.paymentref.PaymentrefService.remove(new Paymentref(), idc);
		com.sbs.service.PaymentsRefResult Pay = ws.getPaymentsRef((String) (session.getAttribute("BankINN")), idn);
		XMLSerializer.write(Pay, "c:/paymref1.xml");
		try
		{
			if (Pay.getStatus() == 0)
			{
				for (int i = 0; i < Pay.getPaymentsRef().length; i++)
				{
					com.is.tf.paymentref.PaymentrefService.create(Pay.getPaymentsRef()[i], idn, idc);
					
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
	
	private Window contractmain = null;
	
	public void onClick$btn_confirmM()
	{
		sendConfirm(1, current.getP2t37(), "", current);
	}
	
	public void onClick$btn_rejectM()
	{
		sendConfirm(0, current.getP2t37(), "", current);
	}
	
	public void onClick$btn_confirm()
	{
		if (currentSum.getP12t38() != null)
		{
			sendConfirm(1, current.getP2t37(), currentSum.getP12t38() + "", currentSum);
		}
		else
		{
			sendConfirm(1, current.getP2t37(), "", currentSum);
		}
	}
	
	public void onClick$btn_reject()
	{
		sendConfirm(0, current.getP2t37(), currentSum.getP12t38() + "", currentSum);
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
	
}
