package com.is.tf.contract;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Decimalbox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Include;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Row;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;
import org.zkoss.zul.event.PagingEvent;
import org.zkoss.zul.impl.InputElement;

import com.is.ISLogger;
import com.is.tf.Accreditiv.Accreditiv;
import com.is.tf.Act.ActService;
import com.is.tf.Barterform.BarterformService;
import com.is.tf.Goods.GoodsService;
import com.is.tf.Incoterms.IncotermsService;
import com.is.tf.agreement.AgreementService;
import com.is.tf.calcform.CalcformService;
import com.is.tf.confirm.Confirm;
import com.is.tf.confirm.ConfirmwndViewCtrl;
import com.is.tf.credit.Credit;
import com.is.tf.debet.Debet;
import com.is.tf.debetinfo.Debetinfo;
import com.is.tf.declaration.DeclarationService;
import com.is.tf.delegate.DelegateService;
import com.is.tf.endoperation.EndoperationService;
import com.is.tf.expcondition.ExpconditionService;
import com.is.tf.fund.Fund;
import com.is.tf.garant.Garant;
import com.is.tf.generalpayments.GeneralPaymentsViewCtrl;
import com.is.tf.payment.Payment;
import com.is.tf.paymentref.Paymentref;
import com.is.tf.penalty.Penalty;
import com.is.tf.policy.Policy;
import com.is.tf.refundexp.Refundexp;
import com.is.tf.refundimp.Refundimp;
import com.is.tf.sender.SenderService;
import com.is.tf.shipment.ShipmentService;
import com.is.tf.specification.SpecificationService;
import com.is.tf.tax.Tax;
import com.is.tf.tolling.TollingService;
import com.is.tf.transcost.TranscostService;
import com.is.tf.transfer.Transfer;
import com.is.tf.transfer.TransferService;
import com.is.tf.work.WorkService;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.RefData;
import com.is.utils.Res;
import com.is.utils.refobj.RefObjData;
import com.is.utils.refobj.RefObjDataService;
import com.is.utils.refobj.XMLSerializer;
import com.sbs.service.BankServiceProxy;
import com.sbs.service.ContractResult;

public class ContractViewCtrl extends GenericForwardComposer
{
	private Window contractmain;
	private Window confirmmain = null;
	private ConfirmwndViewCtrl cvc = null;
	private Window generalpaymentsmain = null;
	private GeneralPaymentsViewCtrl gpvc = null;
	private Div frm;
	private Listbox dataGrid;
	private Paging contactPaging;
	private Div grd;
	private Div info, fgrddiv;
	private Grid addgrd, frmgrdl, frmgrdr, fgrd;
	private Tabbox frmgrd;
	private Radio grup1, grup2, grup3;
	private Toolbarbutton btn_last;
	private Toolbarbutton btn_next;
	private Toolbarbutton btn_dic;
	private Toolbarbutton btn_refresh;
	private Toolbarbutton btn_prev;
	private Toolbarbutton btn_first;
	private Toolbarbutton btn_add, btn_XozSub;
	private HashMap<String, String> curr_ = null;
	private Toolbarbutton btn_search;
	private Menuitem popinf, detail, add_detail;
	
	private Toolbarbutton btn_back;
	private Toolbar tb;
	private Textbox id, p0t1, p1t1, p3t1, p4t1, p5t1, p7t1, p9t1, p10t1, p11t1, p12t1, p15t1, p16t1, p17t1, p18t1, p19t1, p22t1, p23t1, p24t1, p26t1, p27t1, p29t1, p30t1, p31t1, p33t1, p35t1, p36t1, p37t1, p41t1, p42t1, p45t1,
			p46t1, p47t1, p49t1, p50t1, p51t1, p52t1, p53t1, p54t1, p55t1, p56t1, p57t1, p59t1, p60t1, p61t1, p62t1;
	private Textbox aid, ap0t1, ap1t1, ap3t1, ap4t1, ap5t1, ap7t1, ap9t1, ap10t1, ap11t1, ap12t1, ap13t1, ap14t1, ap15t1, ap16t1, ap17t1, ap18t1, ap19t1, ap22t1, ap23t1, ap24t1, ap25t1, ap26t1, ap27t1, ap28t1, ap29t1, ap30t1, ap31t1, ap33t1, ap34t1,
			ap35t1, ap36t1, ap37t1, ap41t1, ap42t1, ap43t1, ap45t1, ap46t1, ap47t1, ap49t1, ap50t1, ap51t1, ap52t1, ap53t1, ap54t1, ap55t1, ap56t1, ap57t1, ap59t1, ap60t1, ap61t1, ap62t1;
	private Textbox fid, fp0t1, fp1t1, fp3t1, fp4t1, fp5t1, fp7t1, fp9t1, fp10t1, fp11t1, fp12t1, fp13t1, fp14t1, fp15t1, fp16t1, fp17t1, fp18t1, fp19t1, fp22t1, fp23t1, fp24t1, fp25t1, fp26t1, fp27t1, fp28t1, fp29t1, fp30t1, fp31t1, fp33t1, fp34t1,
			fp35t1, fp36t1, fp37t1, fp41t1, fp42t1, fp43t1, fp45t1, fp46t1, fp47t1, fp49t1, fp50t1, fp51t1, fp52t1, fp53t1, fp54t1, fp55t1, fp56t1, fp57t1, fp59t1, fp60t1, fp61t1, fp62t1;
	private Datebox p73t1, fp73t1, ap73t1;
	private RefCBox p34t1, p25t1, p28t1, p13t1, p14t1, p2t1, ap2t1, fp2t1, p100t1, fp100t1, ap100t1, p43t1;
	private RefCBox p39t1, fp39t1, ap39t1;
	private Datebox p6t1, p8t1, p32t1, p38t1, p40t1, p44t1, p48t1, p58t1, ap6t1, ap8t1, ap32t1, ap38t1, ap40t1, ap44t1, ap48t1, ap58t1, fp6t1, fp8t1, fp32t1, fp38t1, fp40t1, fp44t1, fp48t1, fp58t1;
	private Decimalbox p65t1, p66t1, p67t1, p68t1, p69t1, p70t1, p71t1, p72t1, fp65t1, fp66t1, fp67t1, fp68t1, fp69t1, fp70t1, fp71t1, fp72t1, ap65t1, ap66t1, ap67t1, ap68t1, ap69t1, ap70t1, ap71t1, ap72t1, p20t1, p21t1, fp20t1, fp21t1, ap20t1,
			ap21t1;
	private Combobox contract_idn, acontract_idn, fcontract_idn;
	private Paging contractPaging;
	private int _pageSize = 15;
	private int _startPageNumber = 0;
	private int _totalSize = 0;
	private boolean _needsTotalSizeUpdate = true;
	private int itype = 0;
	private Short active;
	private int desktopHeight = 0;
	private String lang, branch1, alias, BankINN1;
	private String currbranch;
	private List<RefObjData> payment = new ArrayList<RefObjData>();
	private List<RefObjData> accreditiv = new ArrayList<RefObjData>();
	private List<RefData> SrokIsp = new ArrayList<RefData>();
	private List<RefData> IndastrialZone = new ArrayList<RefData>();
	private List<RefData> regions = new ArrayList<RefData>();
	private List<RefData> ContrType = new ArrayList<RefData>();
	private List<RefData> YesNo = new ArrayList<RefData>();
	private List<RefData> status = new ArrayList<RefData>();
	private List<RefData> country = new ArrayList<RefData>();
	private List<RefData> AgreementState = new ArrayList<RefData>();
	private List<RefData> yeisvo_docs = new ArrayList<RefData>();
	
	private org.zkoss.zul.Checkbox hoz_sub;
	
	private Include movetoimp_include, movefrmim, movefrmex, refundimp_include, payment_include
			, sale_include, sale_include2, paymentrf, taxexp, taximp, leaseexp, leaseimp, movefromexp
			, refundex, movetoexp, comgnk, comi, compens, fnd, debinf, kre, declaration, delegate,
			deb, gar, gars, gart, acr, pol, pen, transfer, act, agreement, barterform, calcform,
			endoperation, expcondition, goods, incoterms, sender, shipment, specification, tolling, transcost, work;
	
	private Tabpanel id_tab_paymentref;
	private Tabpanel id_tab_payment;
	private Tabpanel tp_gar;
	private Tabpanel tp_acr;
	private Tabpanel tp_pol;
	private Tabpanel id_tab_refundimp;
	private Tabpanel id_tab_movetoimp;
	private Tabpanel tp_pen;
	private Tabpanel tp_deb;
	private Tabpanel tp_kre;
	private Tabpanel tp_debinf;
	private Tabpanel tp_fnd;
	private Tabpanel tp_comi;
	private Tabpanel tab_comgnk;
	private Tabpanel tab_mvtoexp;
	private Tabpanel tab_mvfromex;
	private Tabpanel id_tab_movefromim;
	private Tabpanel tab_refexp;
	private Tabpanel tp_leaseexp;
	private Tabpanel tp_leaseimp;
	private Tabpanel tp_taxexp;
	private Tabpanel tp_taximp;
	private Tabpanel id_tab_sale;
	private Tabpanel id_tab_compensation, id_delegate_tab;
	
	private Tabpanel id_tab_transfer, id_act_tab, id_calcform_tab;
	private Tabpanel id_agreement_tab, id_declaration_tab;
	private Tabpanel id_barterform_tab;
	private Tabpanel id_endoperation_tab;
	private Tabpanel id_expcondition_tab;
	private Tabpanel id_goods_tab;
	private Tabpanel id_incoterms_tab;
	private Tabpanel id_sender_tab;
	private Tabpanel id_shipment_tab;
	private Tabpanel id_specification_tab;
	private Tabpanel id_tolling_tab;
	private Tabpanel id_transcost_tab;
	private Tabpanel id_work_tab;
	
	private Tab paymref;
	private Tab payment_tab;
	private Tab garant;
	private Tab accred;
	private Tab policy;
	private Tab refundimp_tab;
	private Tab movetoim_tab;
	private Tab penalty;
	private Tab debet;
	private Tab kredit;
	private Tab debetinf;
	private Tab fund;
	private Tab comm;
	private Tab commgnk;
	private Tab mvtoex;
	private Tab mvfromex;
	private Tab movefromim;
	private Tab refexp;
	private Tab leaseex;
	private Tab leaseim;
	private Tab taxex;
	private Tab taxim;
	private Tab sale_tab;
	private Tab compens_tab;
	private Tab endoperation_tab;
	private Tab expcondition_tab;
	private Tab goods_tab;
	private Tab incoterms_tab;
	private Tab sender_tab;
	private Tab shipment_tab;
	private Tab specification_tab;
	private Tab tolling_tab;
	private Tab transcost_tab;
	private Tab work_tab;
	
	private Tab transfer_tab, act_tab, calcform_tab, declaration_tab, delegate_tab;
	private Tab agreement_tab;
	private Tab barterform_tab;
	
	private String sparam, pol_P11T32, pol_P12T32, pol_P7T37, pol_P8T37, pol_P6T37, pol_P24T37, pol_P5T37;
	private Row row_p1, row_p2, row_p3, row_p4, row_p5, row_p6, row_p7, row_p8, row_p9, row_p10, row_p11, row_p12, row_p13, row_p14, row_p15, row_p16, row_p17, row_p18, row_p19, row_p20, row_p21, row_p22, row_p23, row_p24, row_p25, row_p26, row_p27,
			row_p28, row_p29, row_p30, row_p31, row_p32, row_p33, row_p34, row_p35, row_p36, row_p37, row_p38, row_p39, row_p40, row_p41, row_p42, row_p43, row_p44, row_p45, row_p46, row_p47, row_p48, row_p49, row_p50, row_p51, row_p52, row_p53,
			row_p54, row_p55, row_p56, row_p57, row_p58, row_p59, row_p60, row_p61, row_p62, row_p63, row_p64, row_p65, row_p66, row_p67, row_p68, row_p69, row_p70, row_p71, row_p72, row_p73, row_p100;
	
	public ContractFilter filter = new ContractFilter();
	
	PagingListModel model = null;
	ListModelList lmodel = null;
	private AnnotateDataBinder binder;
	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	
	private Contract current = new Contract();
	
	public ContractViewCtrl()
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
			desktopHeight = Integer.parseInt(parameter[0]);
		}
		
		InputElement[] list = { p1t1, p2t1, p3t1, p4t1, p5t1, p6t1, p7t1, p8t1, p9t1, p10t1, p11t1, p12t1, p13t1, p14t1, p15t1, p16t1, p17t1, p18t1, p19t1, p20t1, p21t1, p22t1, p23t1, p24t1, p25t1, p26t1, p27t1, p28t1, p29t1, p30t1, p31t1, p32t1,
				p33t1, p34t1, p35t1, p36t1, p37t1, p39t1, p40t1, p41t1, p42t1, p43t1, p44t1, p45t1, p46t1, p47t1, p48t1, p49t1, p50t1, p51t1, p52t1, p53t1, p54t1, p55t1, p56t1, p57t1, p58t1, p59t1, p60t1, p61t1, p65t1, p66t1, p67t1, p68t1, p69t1,
				p70t1, p71t1, p72t1 };
		for (int i = 0; i < list.length; i++)
		{
			if (list[i] != null)
			{
				list[i].setReadonly(true);
			}
		}
		if (p2t1.getValue() != null)
		{
			row_p2.setVisible(true);
		}
		else
		{
			row_p2.setVisible(false);
		}
		if (p3t1.getValue() != null)
		{
			row_p3.setVisible(true);
		}
		else
		{
			row_p3.setVisible(false);
		}
		if (p4t1.getValue() != null)
		{
			row_p4.setVisible(true);
		}
		else
		{
			row_p4.setVisible(false);
		}
		if ((p5t1.getValue() != null) && (p5t1.getValue() != ""))
		{
			row_p5.setVisible(true);
		}
		else
		{
			row_p5.setVisible(false);
		}
		if (p6t1.getValue() != null)
		{
			row_p6.setVisible(true);
		}
		else
		{
			row_p6.setVisible(false);
		}
		if ((p7t1.getValue() != null) && (p7t1.getValue() != ""))
		{
			row_p7.setVisible(true);
		}
		else
		{
			row_p7.setVisible(false);
		}
		if (p8t1.getValue() != null)
		{
			row_p8.setVisible(true);
		}
		else
		{
			row_p8.setVisible(false);
		}
		if (p9t1.getValue() != null)
		{
			row_p9.setVisible(true);
		}
		else
		{
			row_p9.setVisible(false);
		}
		if (p10t1.getValue() != null)
		{
			row_p10.setVisible(true);
		}
		else
		{
			row_p10.setVisible(false);
		}
		if (p11t1.getValue() != null)
		{
			row_p11.setVisible(true);
		}
		else
		{
			row_p11.setVisible(false);
		}
		if (p12t1.getValue() != null)
		{
			row_p12.setVisible(true);
		}
		else
		{
			row_p12.setVisible(false);
		}
		if (p13t1.getValue() != null)
		{
			row_p13.setVisible(true);
		}
		else
		{
			row_p13.setVisible(false);
		}
		if (p14t1.getValue() != null)
		{
			row_p14.setVisible(true);
		}
		else
		{
			row_p14.setVisible(false);
		}
		if (p15t1.getValue() != null)
		{
			row_p15.setVisible(true);
		}
		else
		{
			row_p15.setVisible(false);
		}
		if (p16t1.getValue() != null)
		{
			row_p16.setVisible(true);
		}
		else
		{
			row_p16.setVisible(false);
		}
		if (!CheckNull.isEmpty(p17t1.getValue()))
		{
			row_p17.setVisible(true);
		}
		else
		{
			row_p17.setVisible(false);
		}
		if (!CheckNull.isEmpty(p18t1.getValue()))
		{
			row_p18.setVisible(true);
		}
		else
		{
			row_p18.setVisible(false);
		}
		if (p19t1.getValue() != null)
		{
			row_p19.setVisible(true);
		}
		else
		{
			row_p19.setVisible(false);
		}
		if (!CheckNull.isEmpty(p20t1.doubleValue()))
		{
			row_p20.setVisible(true);
		}
		else
		{
			row_p20.setVisible(false);
		}
		if (!CheckNull.isEmpty(p21t1.doubleValue()))
		{
			row_p21.setVisible(true);
		}
		else
		{
			row_p21.setVisible(false);
		}
		if ((p31t1.getValue() != null) && (p31t1.getValue() != ""))
		{
			row_p31.setVisible(true);
		}
		else
		{
			row_p31.setVisible(false);
		}
		if ((p23t1.getValue() != null) && (p23t1.getValue() != ""))
		{
			row_p23.setVisible(true);
		}
		else
		{
			row_p23.setVisible(false);
		}
		if ((p32t1.getValue() != null))
		{
			row_p32.setVisible(true);
		}
		else
		{
			row_p32.setVisible(false);
		}
		
		String YESVO_URL = ContractService.getBF_SETS("YESVO_URL_NEW");
		if (CheckNull.isEmpty(YESVO_URL))
		{
			alert("YESVO_URL is null");
		}
		session.setAttribute("YESVO_URL", YESVO_URL);
		System.out.println(session.getAttribute("YESVO_URL"));
		/*
		 * String BINN=ContractService.getBankINN(); if
		 * (CheckNull.isEmpty(BINN)) { alert ("BankINN is null"); }
		 * session.setAttribute("BankINN", BINN);
		 * System.out.println(session.getAttribute("BankINN"));
		 */
		String BankINN = ContractService.getBankINN("BankINN");
		if (CheckNull.isEmpty(BankINN))
		{
			alert("BANK_INN is null");
		}
		session.setAttribute("BankINN", BankINN);
		System.out.println("BANK_INN=  " + session.getAttribute("BankINN"));
		
		BankINN1 = "200542744";
		// System.out.println("Ipak INN="+BankINN1);
		alias = (String) session.getAttribute("alias");
		yeisvo_docs = ContractService.getYeisvoDocs(alias);
		SrokIsp = ContractService.getSrokIspolneniya();
		
		parameter = (String[]) param.get("fileType");
		if ((parameter != null)) // / Филиал
		{
			if ((parameter[0]).equals("2"))
			{
				sparam = "Filial";
			}
			else if ((parameter[0]).equals("1"))
			{
				sparam = "Go";
			}
			else if ((parameter[0]).equals("3"))
			{
				sparam = "GlBuhg";
			}
			else if ((parameter[0]).equals("4"))
			{
				sparam = "Upr";
			}
		}
		else
		{
			sparam = "Filial";
		}
		
		// alert(sparam);
		
		dataGrid.setItemRenderer(new ListitemRenderer()
		{
			@SuppressWarnings("unchecked")
			public void render(Listitem row, Object data) throws Exception
			{
				Contract pContract = (Contract) data;
				
				row.setValue(pContract);
				
				row.appendChild(new Listcell(pContract.getId() + ""));
				row.appendChild(new Listcell(pContract.getP0t1()));
				row.appendChild(new Listcell(pContract.getP1t1()));
				row.appendChild(new Listcell(pContract.getP2t1()));
				row.appendChild(new Listcell(pContract.getP3t1()));
				row.appendChild(new Listcell(pContract.getP4t1()));
				row.appendChild(new Listcell(pContract.getP5t1()));
				row.appendChild(new Listcell(pContract.getP6t1() + ""));
				row.appendChild(new Listcell(pContract.getP7t1()));
				row.appendChild(new Listcell(pContract.getP8t1() + ""));
				row.appendChild(new Listcell(pContract.getP9t1()));
				row.appendChild(new Listcell(pContract.getP10t1()));
				row.appendChild(new Listcell(pContract.getP11t1()));
				row.appendChild(new Listcell(pContract.getP12t1()));
				row.appendChild(new Listcell(pContract.getP13t1()));
				row.appendChild(new Listcell(pContract.getP14t1()));
				row.appendChild(new Listcell(pContract.getP15t1()));
				row.appendChild(new Listcell(pContract.getP16t1()));
				row.appendChild(new Listcell(pContract.getP17t1()));
				row.appendChild(new Listcell(pContract.getP18t1()));
				row.appendChild(new Listcell(pContract.getP19t1()));
				row.appendChild(new Listcell(pContract.getP20t1() + ""));
				row.appendChild(new Listcell(pContract.getP21t1() + ""));
				row.appendChild(new Listcell(pContract.getP22t1()));
				row.appendChild(new Listcell(pContract.getP23t1()));
				row.appendChild(new Listcell(pContract.getP24t1()));
				row.appendChild(new Listcell(pContract.getP25t1()));
				row.appendChild(new Listcell(pContract.getP26t1()));
				row.appendChild(new Listcell(pContract.getP27t1()));
				row.appendChild(new Listcell(pContract.getP28t1()));
				row.appendChild(new Listcell(pContract.getP29t1()));
				row.appendChild(new Listcell(pContract.getP30t1()));
				row.appendChild(new Listcell(pContract.getP31t1()));
				row.appendChild(new Listcell(pContract.getP32t1() + ""));
				row.appendChild(new Listcell(pContract.getP33t1()));
				row.appendChild(new Listcell(pContract.getP34t1()));
				row.appendChild(new Listcell(pContract.getP35t1()));
				row.appendChild(new Listcell(pContract.getP36t1()));
				row.appendChild(new Listcell(pContract.getP37t1()));
				row.appendChild(new Listcell(pContract.getP38t1() + ""));
				row.appendChild(new Listcell(pContract.getP39t1()));
				row.appendChild(new Listcell(pContract.getP40t1() + ""));
				row.appendChild(new Listcell(pContract.getP41t1()));
				row.appendChild(new Listcell(pContract.getP42t1()));
				row.appendChild(new Listcell(pContract.getP43t1()));
				row.appendChild(new Listcell(pContract.getP44t1() + ""));
				row.appendChild(new Listcell(pContract.getP45t1()));
				row.appendChild(new Listcell(pContract.getP46t1()));
				row.appendChild(new Listcell(pContract.getP47t1()));
				row.appendChild(new Listcell(pContract.getP48t1() + ""));
				row.appendChild(new Listcell(pContract.getP49t1()));
				row.appendChild(new Listcell(pContract.getP50t1()));
				row.appendChild(new Listcell(pContract.getP51t1()));
				row.appendChild(new Listcell(pContract.getP52t1()));
				row.appendChild(new Listcell(pContract.getP53t1()));
				row.appendChild(new Listcell(pContract.getP54t1()));
				row.appendChild(new Listcell(pContract.getP55t1()));
				row.appendChild(new Listcell(pContract.getP56t1()));
				row.appendChild(new Listcell(pContract.getP57t1()));
				row.appendChild(new Listcell(pContract.getP58t1() + ""));
				row.appendChild(new Listcell(pContract.getP59t1()));
				row.appendChild(new Listcell(pContract.getP60t1()));
				row.appendChild(new Listcell(pContract.getP61t1()));
				row.appendChild(new Listcell(pContract.getP62t1()));
				row.appendChild(new Listcell(pContract.getP65t1() + ""));
				row.appendChild(new Listcell(pContract.getP66t1() + ""));
				row.appendChild(new Listcell(pContract.getP67t1() + ""));
				row.appendChild(new Listcell(pContract.getP68t1() + ""));
				row.appendChild(new Listcell(pContract.getP69t1() + ""));
				row.appendChild(new Listcell(pContract.getP70t1() + ""));
				row.appendChild(new Listcell(pContract.getP71t1() + ""));
				row.appendChild(new Listcell(pContract.getP72t1() + ""));
				row.appendChild(new Listcell(pContract.getP73t1() + ""));
				row.appendChild(new Listcell(com.is.tf.contract.SPR.getP100Value(pContract.getP100t1())));
				
			}
		});
		
		SrokIsp = ContractService.getSrokIspolneniya();
		YesNo = ContractService.getYesNo();
		AgreementState = com.is.tf.contract.ContractService.getAgreementState(alias);
		country = com.is.tf.contract.ContractService.getCountry(alias);
		ContrType = com.is.tf.contract.ContractService.getContractType(alias);
		status = com.is.tf.contract.ContractService.getStatusDoc();
		IndastrialZone = com.is.tf.contract.ContractService.getIndastrZone(alias);
		regions = com.is.tf.contract.ContractService.getRegions(alias);
		p39t1.setModel((new ListModelList(SrokIsp)));
		p100t1.setModel((new ListModelList(status)));
		p2t1.setModel((new ListModelList(ContrType)));
		p25t1.setModel((new ListModelList(country)));
		p28t1.setModel((new ListModelList(country)));
		p43t1.setModel((new ListModelList(AgreementState)));
		p13t1.setModel((new ListModelList(regions)));
		p34t1.setModel((new ListModelList(YesNo)));
		p14t1.setModel((new ListModelList(IndastrialZone)));
		
		refreshModel(_startPageNumber);
		
	}
	
	public void onPaging$contractPaging(ForwardEvent event)
	{
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModel(_startPageNumber);
	}
	
	private void refreshModel(int activePage)
	{
		contractPaging.setPageSize(_pageSize);
		model = new PagingListModel(activePage, _pageSize, filter, "");
		_totalSize = model.getTotalSize(filter, "");
		contractPaging.setTotalSize(_totalSize);
		dataGrid.setModel((ListModel) model);
		if (model.getSize() > 0)
		{
			dataGrid.setSelectedIndex(0);
			sendSelEvt(true);
			this.current = (Contract) model.getElementAt(0);
			sendSelEvt(true);
		}
	}
	
	// Omitted...
	public Contract getCurrent()
	{
		return current;
	}
	
	public void setCurrent(Contract current)
	{
		this.current = current;
	}
	
	public void onDoubleClick$dataGrid$grd()
	{
		grd.setVisible(false);
		frm.setVisible(true);
		frmgrd.setVisible(true);
		
		onClick$hoz_sub();
		
		addgrd.setVisible(false);
		fgrddiv.setVisible(false);
		btn_back.setImage("/images/folder.png");
		btn_back.setLabel(Labels.getLabel("grid"));
		curr_ = com.is.tf.contract.ContractService.getHCurr(alias);
		p18t1.setValue(curr_.get(current.getP18t1()));
		p19t1.setValue(curr_.get(current.getP19t1()));
		p22t1.setValue(curr_.get(current.getP22t1()));
		p23t1.setValue(curr_.get(current.getP23t1()));
		
		frmgrd.setSelectedIndex(0);
		
	}
	
	public void onDoubleClick$frm()
	{
		grd.setVisible(false);
		frm.setVisible(true);
		
		frmgrd.setVisible(true);
		addgrd.setVisible(false);
		fgrddiv.setVisible(false);
		btn_back.setImage("/images/folder.png");
		btn_back.setLabel(Labels.getLabel("back"));
		// btn_inf.setImage("/images/statics.png");
		// btn_inf.setLabel(Labels.getLabel("btninf"));
		
	}
	
	public void onClick$hoz_sub()
	{
		// 01 03 04 07 08 09 11
		
		if ((current.getP2t1().equalsIgnoreCase("01") || (current.getP2t1().equalsIgnoreCase("03") || (current.getP2t1().equalsIgnoreCase("04") || (current.getP2t1().equals("07") || (current.getP2t1().equals("08") || (current.getP2t1().equals(
				"09") || (current.getP2t1().equals("11")))))))))
		{
			tab_comgnk.setVisible(true);
			sale_tab.setVisible(true);
			comm.setVisible(true);
			commgnk.setVisible(true);
			penalty.setVisible(true);
			refexp.setVisible(true);
			fund.setVisible(true);
			policy.setVisible(true);
			
		}
		else
		{
			tab_comgnk.setVisible(true);
			sale_tab.setVisible(false);
			comm.setVisible(true);
			commgnk.setVisible(true);
			penalty.setVisible(true);
			refexp.setVisible(false);
			fund.setVisible(false);
			policy.setVisible(false);
			
		}
		// 2 5 12
		if ((current.getP2t1().equalsIgnoreCase("02") || (current.getP2t1().equalsIgnoreCase("05") || (current.getP2t1().equalsIgnoreCase("12")))))
		{
			payment_tab.setVisible(true);
			comm.setVisible(true);
			refundimp_tab.setVisible(true);
			penalty.setVisible(true);
			commgnk.setVisible(true);
			
		}
		else
		{
			payment_tab.setVisible(false);
			comm.setVisible(true);
			refundimp_tab.setVisible(false);
			penalty.setVisible(true);
			commgnk.setVisible(true);
			
		}
		if ((current.getP65t1() != null) || (current.getP67t1() != null))
		{
			debet.setVisible(true);
			debetinf.setVisible(true);
		}
		else
		{
			debet.setVisible(false);
			debetinf.setVisible(false);
		}
		
		if ((current.getP69t1() != null || current.getP71t1() != null))
		{
			kredit.setVisible(true);
			
		}
		else
		{
			kredit.setVisible(false);
			
		}
		
		if ((current.getP69t1() != null && current.getP2t1().equalsIgnoreCase("01")))
		{
			mvtoex.setVisible(true);
			mvfromex.setVisible(true);
		}
		else
		{
			mvtoex.setVisible(false);
			mvfromex.setVisible(false);
		}
		if ((current.getP69t1() != null && current.getP2t1().equalsIgnoreCase("02")))
		{
			movetoim_tab.setVisible(true);
			movefromim.setVisible(true);
		}
		else
		{
			movetoim_tab.setVisible(false);
			movefromim.setVisible(false);
		}
		
		if ((current.getP17t1().equalsIgnoreCase("1") || (current.getP17t1().equalsIgnoreCase("3"))) && (current.getP2t1().equalsIgnoreCase("01") || (current.getP2t1().equalsIgnoreCase("03") || (current.getP2t1().equalsIgnoreCase("04")))))
		{
			paymref.setVisible(true);
		}
		else
		{
			paymref.setVisible(false);
		}
		
		if ((current.getP17t1().equalsIgnoreCase("2") || (current.getP17t1().equalsIgnoreCase("3")) && ((current.getP2t1().equalsIgnoreCase("01") || (current.getP2t1().equalsIgnoreCase("03") || (current.getP2t1().equalsIgnoreCase("04")))))))
		{
			taxex.setVisible(true);
		}
		else
		{
			taxex.setVisible(false);
		}
		
		if ((current.getP17t1().equalsIgnoreCase("2") || (current.getP17t1().equalsIgnoreCase("3")) && ((current.getP2t1().equalsIgnoreCase("02") || (current.getP2t1().equalsIgnoreCase("05"))))))
		{
			taxim.setVisible(true);
		}
		else
		{
			taxim.setVisible(false);
		}
		
		if (current.getP2t1().equalsIgnoreCase("02"))
		{
			leaseim.setVisible(true);
		}
		else
		{
			leaseim.setVisible(false);
		}
		if (current.getP2t1().equalsIgnoreCase("01") || (current.getP2t1().equalsIgnoreCase("03")))
		{
			leaseex.setVisible(true);
		}
		else
		{
			leaseex.setVisible(false);
		}
		
		// Hoz. subekti
		if (hoz_sub.isChecked() == true)
		{
			act_tab.setVisible(true);
			transfer_tab.setVisible(true);
			agreement_tab.setVisible(true);
			barterform_tab.setVisible(true);
			calcform_tab.setVisible(true);
			declaration_tab.setVisible(true);
			endoperation_tab.setVisible(true);
			expcondition_tab.setVisible(true);
			goods_tab.setVisible(true);
			incoterms_tab.setVisible(true);
			sender_tab.setVisible(true);
			shipment_tab.setVisible(true);
			specification_tab.setVisible(true);
			tolling_tab.setVisible(true);
			transcost_tab.setVisible(true);
			work_tab.setVisible(true);
			
			paymref.setVisible(false);
			payment_tab.setVisible(false);
			garant.setVisible(false);
			accred.setVisible(false);
			policy.setVisible(false);
			refundimp_tab.setVisible(false);
			movetoim_tab.setVisible(false);
			penalty.setVisible(false);
			debet.setVisible(false);
			kredit.setVisible(false);
			debetinf.setVisible(false);
			fund.setVisible(false);
			comm.setVisible(false);
			commgnk.setVisible(false);
			mvtoex.setVisible(false);
			mvfromex.setVisible(false);
			movefromim.setVisible(false);
			refexp.setVisible(false);
			leaseex.setVisible(false);
			leaseim.setVisible(false);
			taxex.setVisible(false);
			taxim.setVisible(false);
			sale_tab.setVisible(false);
			compens_tab.setVisible(false);
		}
		else
		{
			act_tab.setVisible(false);
			transfer_tab.setVisible(false);
			agreement_tab.setVisible(false);
			barterform_tab.setVisible(false);
			calcform_tab.setVisible(false);
			declaration_tab.setVisible(false);
			endoperation_tab.setVisible(false);
			expcondition_tab.setVisible(false);
			goods_tab.setVisible(false);
			incoterms_tab.setVisible(false);
			sender_tab.setVisible(false);
			shipment_tab.setVisible(false);
			specification_tab.setVisible(false);
			tolling_tab.setVisible(false);
			transcost_tab.setVisible(false);
			work_tab.setVisible(false);
			
			garant.setVisible(true);
			accred.setVisible(true);
			policy.setVisible(true);
		}
	}
	
	public void onSelect$dataGrid$grd()
	{
		sendSelEvt(false);
	}
	
	public void onDoubleClick$inf()
	{
		grd.setVisible(false);
		frm.setVisible(true);
		
		frmgrd.setVisible(true);
		addgrd.setVisible(false);
		fgrddiv.setVisible(false);
		btn_back.setImage("/images/folder.png");
		btn_back.setLabel(Labels.getLabel("grid"));
	}
	
	// btn_inf.setImage("/images/statics.png");
	// btn_inf.setLabel(Labels.getLabel("homeinf"));}
	public void onClick$contract_idn()
	{
		if (contract_idn.getValue().equals("Введите ИДН контракта"))
		{
			contract_idn.setValue("");
		}
	}
	
	public void onClick$popinf()
	{
		if (grd.isVisible())
		{
			frm.setVisible(false);
			
			frmgrd.setVisible(false);
			grd.setVisible(false);
		}
	}
	
	public void onClick$add_detail()
	{
		onDoubleClick$inf();
		frmgrd.setVisible(false);
		addgrd.setVisible(true);
		fgrddiv.setVisible(false);
	}
	
	public void onClick$btn_XozSub() throws Exception
	{
		
		final BankServiceProxy ws = new BankServiceProxy((String) session.getAttribute("YESVO_URL"));
		long id_contract = current.getId();
		String idn = current.getP1t1();
		ContractResult cr = ws.getContract(((String) session.getAttribute("BankINN")), idn);
		
		com.sbs.service.Declaration[] decl = cr.getContract().getDeclarations();
		
		XMLSerializer.write(decl, "c:/declaration.xml");
		/*
		 * if (decl != null) { for (int i = 0; i < decl.length; i++) {
		 * DeclarationService.remove(new Declaration(), id_contract); Res
		 * res=DeclarationService.create(decl[i], id_contract); if
		 * (res.getCode()==0) { alert("Успешно загружен ГТД!"); } else {
		 * alert("Ошибка:"+res.getName()); } } }
		 */
		com.sbs.service.Transfer[] transf = cr.getContract().getTransfers();
		XMLSerializer.write(transf, "c:/transfer.xml");
		if (transf != null)
		{
			for (int i = 0; i < transf.length; i++)
			{
				Res res = TransferService.create(transf[i], idn, id_contract);
				if (res.getCode() == 0)
				{
					alert("Успешно загружен Перевод контракта!");
				}
				else
				{
					alert("Ошибка:" + res.getName());
				}
			}
		}
	}
	
	public void onSelect$paymref(Event evt)
	{
		try
		{
			if (itype == 1)
			{
				paymentrf.setSrc("tfPaymentref.zul?idn=" + current.getP1t1() + "&pol_P5T37=" + pol_P5T37 + "&pol_P8T37=" + pol_P8T37 + "&pol_P7T37=" + pol_P7T37 + "&pol_P6T37=" + pol_P6T37 + "&pol_P24T37=" + pol_P24T37 + "&spr=" + sparam + "&idc="
						+ current.getId() + "&cont_type=" + current.getP2t1() + "&summa1=" + current.getP20t1() + "&summa2=" + current.getP21t1() + "&subj=" + current.getP17t1() + "&val1=" + current.getP18t1() + "&val2=" + current.getP19t1()
						+ "&val22=" + current.getP22t1() + "&val23=" + current.getP23t1() + "&ht=" + desktopHeight);
				
			}
			else
			{
				final BankServiceProxy ws = new BankServiceProxy((String) session.getAttribute("YESVO_URL"));
				long id_contract = current.getId();
				com.is.tf.paymentref.PaymentrefService.remove(new Paymentref(), id_contract);
				com.sbs.service.PaymentsRefResult Pay = ws.getPaymentsRef((String) (session.getAttribute("BankINN")), current.getP1t1());
				XMLSerializer.write(Pay, "c:/paymref1.xml");
				if (Pay.getStatus() == 0)
				{
					for (int i = 0; i < Pay.getPaymentsRef().length; i++)
					{
						if (Pay.getPaymentsRef()[i].getP24T37() != null)
						{
							pol_P24T37 = "1";
						}
						else
						{
							pol_P24T37 = "0";
						}
						if (Pay.getPaymentsRef()[i].getP6T37() != null)
						{
							pol_P6T37 = "1";
						}
						else
						{
							pol_P6T37 = "0";
						}
						if (Pay.getPaymentsRef()[i].getP7T37() != null)
						{
							pol_P7T37 = "1";
						}
						else
						{
							pol_P7T37 = "0";
						}
						if (Pay.getPaymentsRef()[i].getP8T37() != null)
						{
							pol_P8T37 = "1";
						}
						else
						{
							pol_P8T37 = "0";
						}
						if (Pay.getPaymentsRef()[i].getP5T37() != null)
						{
							pol_P5T37 = "1";
						}
						else
						{
							pol_P5T37 = "0";
						}
						// System.out.println("24="+Pay.getPaymentsRef()[i].getP24T37()+"   6="+Pay.getPaymentsRef()[i].getP6T37()+"  7="+Pay.getPaymentsRef()[i].getP7T37()+"  8="+Pay.getPaymentsRef()[i].getP8T37()+" 5="+Pay.getPaymentsRef()[i].getP5T37());
						com.is.tf.paymentref.PaymentrefService.create(Pay.getPaymentsRef()[i], current.getP1t1(), id_contract);
					}
				}
				else
				{
					alert("ERROR Tab_paymentref:" + Pay.getErrorMsg() + ":  Status=" + Pay.getStatus() + ": GtkId=" + Pay.getGtkId() + ": BankINN=" + ((String) session.getAttribute("BankINN")));
					ISLogger.getLogger().warn("ERROR onSelect$paymentref:" + Pay.getErrorMsg() + ":  Status=" + Pay.getStatus() + ": GtkId=" + Pay.getGtkId());
					
				}
				paymentrf.setSrc("tfPaymentref.zul?idn=" + current.getP1t1() + "&pol_P5T37=" + pol_P5T37 + "&pol_P8T37=" + pol_P8T37 + "&pol_P7T37=" + pol_P7T37 + "&pol_P6T37=" + pol_P6T37 + "&pol_P24T37=" + pol_P24T37 + "&spr=" + sparam + "&idc="
						+ current.getId() + "&cont_type=" + current.getP2t1() + "&summa1=" + current.getP20t1() + "&summa2=" + current.getP21t1() + "&subj=" + current.getP17t1() + "&val1=" + current.getP18t1() + "&val2=" + current.getP19t1()
						+ "&val22=" + current.getP22t1() + "&val23=" + current.getP23t1() + "&ht=" + desktopHeight);
			}
		}
		catch (Exception e)
		{
			ISLogger.getLogger().warn(CheckNull.getPstr(e));
			alert("ERROR:in tab paymentref " + (CheckNull.isEmpty(e.getMessage()) ? CheckNull.getPstr(e) : e.getMessage()));
			e.printStackTrace();
		}
		finally
		{
		}
	}
	
	public void onSelect$payment_tab(Event evt)
	{
		try
		{
			if (itype == 1)
			{
				payment_include.setSrc("tfPayment.zul?idn=" + current.getP1t1() + "&spr=" + sparam + "&idc=" + current.getId() + "&cont_type=" + current.getP2t1() + "&summa1=" + current.getP20t1() + "&summa2=" + current.getP21t1() + "&subj="
						+ current.getP17t1() + "&val1=" + current.getP18t1() + "&val2=" + current.getP19t1() + "&val22=" + current.getP22t1() + "&val23=" + current.getP23t1() + "&ht=" + desktopHeight);
				
			}
			else
			{
				final BankServiceProxy ws = new BankServiceProxy((String) session.getAttribute("YESVO_URL"));
				long id_contract = current.getId();
				com.is.tf.payment.PaymentService.remove(new Payment(), id_contract);
				com.sbs.service.PaymentsResult Pay = ws.getPayments(((String) (session.getAttribute("BankINN"))), current.getP1t1());
				XMLSerializer.write(Pay, "c:/paym1.xml");
				if (Pay.getStatus() == 0)
				{
					for (int i = 0; i < Pay.getPayments().length; i++)
					{
						com.is.tf.payment.PaymentService.create(Pay.getPayments()[i], current.getP1t1(), id_contract);
					}
				}
				else
				{
					alert("ERROR Tab_payment:" + Pay.getErrorMsg() + ":  Status=" + Pay.getStatus() + ": GtkId=" + Pay.getGtkId() + ": BankINN=" + ((String) session.getAttribute("BankINN")));
					ISLogger.getLogger().warn("ERROR onSelect$payment:" + Pay.getErrorMsg() + ":  Status=" + Pay.getStatus() + ": GtkId=" + Pay.getGtkId());
					
				}
				payment_include.setSrc("tfPayment.zul?Contract=" + "&idn=" + current.getP1t1() + "&spr=" + sparam + "&idc=" + current.getId() + "&cont_type=" + current.getP2t1() + "&summa1=" + current.getP20t1() + "&summa2=" + current.getP21t1()
						+ "&subj=" + current.getP17t1() + "&val1=" + current.getP18t1() + "&val2=" + current.getP19t1() + "&val22=" + current.getP22t1() + "&val23=" + current.getP23t1() + "&ht=" + desktopHeight);
			}
			
		}
		catch (Exception e)
		{
			ISLogger.getLogger().warn(CheckNull.getPstr(e));
			alert("ERROR:in tab payment " + (CheckNull.isEmpty(e.getMessage()) ? CheckNull.getPstr(e) : e.getMessage()));
			
			e.printStackTrace();
		}
		finally
		{
			// System.out.println("onSelect$garant  - id_contract  "+current.getId()+" cont_idn=  "+current.getP1t1()+"&val1="+current.getP18t1()+"&val2="+current.getP19t1());
		}
	}
	
	public void onSelect$accred(Event evt)
	{
		/*
		 * try { //acr.setHeight((desktopHeight-130)+"px"); if (itype==1) {
		 * acr.setSrc
		 * ("tfAccreditiv.zul?idn="+current.getP1t1()+"&cont_type="+current
		 * .getP2t1
		 * ()+"&spr="+sparam+"&idc="+current.getId()+"&summa1="+current.getP20t1
		 * (
		 * )+"&summa2="+current.getP21t1()+"&val1="+current.getP18t1()+"&val2="+
		 * current.getP19t1()+"&ht="+desktopHeight); }else { final
		 * BankServiceProxy ws = new
		 * BankServiceProxy((String)session.getAttribute("YESVO_URL")); long
		 * id_contract = current.getId();
		 * com.is.tf.Accreditiv.AccreditivService.remove(new Accreditiv()
		 * ,id_contract); com.sbs.service.AccreditivesResult acr1 =
		 * ws.getAccreditives(((String)session.getAttribute("BankINN")),
		 * current.getP1t1()); XMLSerializer.write(acr1, "c:/acr1.xml"); if
		 * (acr1.getStatus()==0){ for (int
		 * i=0;i<acr1.getAccreditives().length;i++){
		 * com.is.tf.Accreditiv.AccreditivService
		 * .create2(acr1.getAccreditives()[i], current.getP1t1(), id_contract);
		 * } } else {
		 * alert("ERROR:"+acr1.getErrorMsg()+":  Status="+acr1.getStatus
		 * ()+": GtkId="
		 * +acr1.getGtkId()+": BankINN="+((String)session.getAttribute
		 * ("BankINN")));
		 * ISLogger.getLogger().warn("ERROR onSelect$accred:"+acr1
		 * .getErrorMsg()+
		 * ":  Status="+acr1.getStatus()+": GtkId="+acr1.getGtkId()); }
		 * acr.setSrc
		 * ("tfAccreditiv.zul?Contract="+"&idn="+current.getP1t1()+"&spr="
		 * +sparam
		 * +"&cont_type="+current.getP2t1()+"&idc="+current.getId()+"&summa1="
		 * +current
		 * .getP20t1()+"&summa2="+current.getP21t1()+"&val1="+current.getP18t1
		 * ()+"&val2="+current.getP19t1()+"&ht="+desktopHeight); } }catch
		 * (Exception e) { e.printStackTrace();
		 * ISLogger.getLogger().warn(CheckNull.getPstr(e));
		 * alert("ERROR:in tab accreditiv "
		 * +(CheckNull.isEmpty(e.getMessage())?CheckNull
		 * .getPstr(e):e.getMessage())); } finally {
		 * System.out.println("onSelect$accred  - id_contract  "
		 * +current.getId()+
		 * " cont_idn=  "+current.getP1t1()+"&val1="+current.getP18t1
		 * ()+"&val2="+current.getP19t1()); }
		 */

		try
		{
			String link = "tfAccreditiv.zul?idn=" + current.getP1t1() + "&cont_type="
					+ current.getP2t1() + "&spr=" + sparam + "&idc=" + current.getId()
					+ "&summa1=" + current.getP20t1() + "&summa2=" + current.getP21t1()
					+ "&val1=" + current.getP18t1() + "&val2=" + current.getP19t1()
					+ "&ht=" + desktopHeight;
			
			if (itype == 1)
			{
				acr.setSrc(link);
			}
			else
			{
				final BankServiceProxy ws = new BankServiceProxy((String) session.getAttribute("YESVO_URL"));
				long id_contract = current.getId();
				com.is.tf.Accreditiv.AccreditivService.remove(new Accreditiv(), id_contract);
				com.sbs.service.AccreditivesResult acr1 = ws.getAccreditives(((String) session.getAttribute("BankINN")), current.getP1t1());
				XMLSerializer.write(acr1, "c:/acr1.xml");
				
				if (acr1.getStatus() == 0)
				{
					for (int i = 0; i < acr1.getAccreditives().length; i++)
					{
						com.is.tf.Accreditiv.AccreditivService.create2(acr1.getAccreditives()[i], current.getP1t1(), id_contract);
					}
				}
				else
				{
					alert("ERROR:" + acr1.getErrorMsg() + ":  Status=" + acr1.getStatus() + ": GtkId=" + acr1.getGtkId() + ": BankINN=" + ((String) session.getAttribute("BankINN")));
					ISLogger.getLogger().warn("ERROR onSelect$accred:" + acr1.getErrorMsg() + ":  Status=" + acr1.getStatus() + ": GtkId=" + acr1.getGtkId());
				}
				acr.setSrc(link);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			ISLogger.getLogger().warn(CheckNull.getPstr(e));
			alert("ERROR:in tab accreditiv " + (CheckNull.isEmpty(e.getMessage()) ? CheckNull.getPstr(e) : e.getMessage()));
		}
		finally
		{
			System.out.println("onSelect$accred  - id_contract  " + current.getId() + " cont_idn=  " + current.getP1t1() + "&val1=" + current.getP18t1() + "&val2=" + current.getP19t1());
		}
		
	}
	
	public void onSelect$garant(Event evt)
	{
		try
		{
			if (itype == 1)
			{
				gar.setSrc("tfGarant.zul?Contract=" + "&idn=" + current.getP1t1() + "&spr=" + sparam + "&cont_type=" + current.getP2t1() + "&idc=" + current.getId() + "&summa1=" + current.getP20t1() + "&summa2=" + current.getP21t1() + "&val1="
						+ current.getP18t1() + "&val2=" + current.getP19t1() + "&ht=" + desktopHeight);
			}
			else
			{
				final BankServiceProxy ws = new BankServiceProxy((String) session.getAttribute("YESVO_URL"));
				long id_contract = current.getId();
				com.is.tf.garant.GarantService.remove(new Garant(), id_contract);
				com.sbs.service.GarantsResult gar1 = ws.getGarants(((String) session.getAttribute("BankINN")), current.getP1t1());
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
						com.is.tf.garant.GarantService.create(Garants, current.getP1t1(), id_contract);
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
				finally
				{
				}
				gar.setSrc("tfGarant.zul?Contract=" + "&idn=" + current.getP1t1() + "&spr=" + sparam + "&cont_type=" + current.getP2t1() + "&idc=" + current.getId() + "&summa1=" + current.getP20t1() + "&summa2=" + current.getP21t1() + "&val1="
						+ current.getP18t1() + "&val2=" + current.getP19t1() + "&ht=" + desktopHeight);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			ISLogger.getLogger().warn(CheckNull.getPstr(e));
			alert("ERROR: in tab garant 2" + (CheckNull.isEmpty(e.getMessage()) ? CheckNull.getPstr(e) : e.getMessage()));
		}
		finally
		{
		}
	}
	
	public void onSelect$policy(Event evt)
	{
		try
		{
			if (itype == 1)
			{
				pol.setSrc("tfPolicy.zul?Contract=" + "&idn=" + current.getP1t1() + "&spr=" + sparam + "&cont_type=" + current.getP2t1() + "&idc=" + current.getId() + "&summa1=" + current.getP20t1() + "&summa2=" + current.getP21t1() + "&val1="
						+ current.getP18t1() + "&val2=" + current.getP19t1() + "&ht=" + desktopHeight);
				
			}
			else
			{
				final BankServiceProxy ws = new BankServiceProxy((String) session.getAttribute("YESVO_URL"));
				long id_contract = current.getId();
				com.is.tf.policy.PolicyService.remove(new Policy(), id_contract);
				com.sbs.service.PoliciesResult pol1 = ws.getPolicies(((String) (session.getAttribute("BankINN"))), current.getP1t1());
				
				if (pol1.getStatus() == 0)
				{
					for (int i = 0; i < pol1.getPolicies().length; i++)
					{
						if (pol1.getPolicies()[i].getP11T32() != null)
						{
							pol_P11T32 = "1";
						}
						else
						{
							pol_P11T32 = "0";
						}
						if (pol1.getPolicies()[i].getP12T32() != null)
						{
							pol_P12T32 = "1";
						}
						else
						{
							pol_P12T32 = "0";
						}
						com.is.tf.policy.PolicyService.create(pol1.getPolicies()[i], current.getP1t1(), id_contract);
					}
					
				}
				else
				{
					alert("ERROR:" + pol1.getErrorMsg() + ":  Status=" + pol1.getStatus() + ": GtkId=" + pol1.getGtkId());
					ISLogger.getLogger().warn("ERROR onSelect$policy:" + pol1.getErrorMsg() + ":  Status=" + pol1.getStatus() + ": GtkId=" + pol1.getGtkId());
				}
			}
		}
		catch (Exception e)
		{
			
			e.printStackTrace();
			ISLogger.getLogger().warn(CheckNull.getPstr(e));
			alert("ERROR: in tab policy= " + (CheckNull.isEmpty(e.getMessage()) ? CheckNull.getPstr(e) : e.getMessage()));
			
		}
		finally
		{
			// System.out.println("onSelect$garant  - id_contract  "+current.getId()+" cont_idn=  "+current.getP1t1()+"&val1="+current.getP18t1()+"&val2="+current.getP19t1());
			// System.out.println("contract_idn.getValue()  "+current.getId()+" p1t1  "+current.getP1t1());
		}
		pol.setSrc("tfPolicy.zul?Contract=" + "&idn=" + current.getP1t1() + "&spr=" + sparam + "&pol_P11T32=" + pol_P11T32 + "&pol_P12T32=" + pol_P12T32 + "&cont_type=" + current.getP2t1() + "&idc=" + current.getId() + "&summa1="
				+ current.getP20t1() + "&summa2=" + current.getP21t1() + "&val1=" + current.getP18t1() + "&val2=" + current.getP19t1() + "&ht=" + desktopHeight);
		
	}
	
	/* ********* Penalty ************** */
	public void onSelect$penalty(Event evt)
	{
		try
		{
			pen.setHeight((desktopHeight - 130) + "px");
			
			if (itype == 1)
			{
				pen.setSrc("tfPenalty.zul?Contract=" + "&idn=" + current.getP1t1() + "&cont_type="
						+ current.getP2t1()
						+ "&idc=" + current.getId() + "&val1=" + current.getP18t1()
						+ "&val2=" + current.getP19t1() + "&ht=" + desktopHeight);
			}
			else
			{
				final BankServiceProxy ws = new BankServiceProxy((String) session.getAttribute("YESVO_URL"));
				long id_contract = current.getId();
				com.is.tf.penalty.PenaltyService.remove(new Penalty(), id_contract);
				com.sbs.service.PenaltiesResult Pnt = ws.getPenalties(((String) (session.getAttribute("BankINN"))), current.getP1t1());
				XMLSerializer.write(Pnt, "c:/pen1.xml");
				
				if (Pnt.getStatus() == 0)
				{
					for (int i = 0; i < Pnt.getPenalties().length; i++)
					{
						com.is.tf.penalty.PenaltyService.create(Pnt.getPenalties()[i], current.getP1t1(), id_contract);
					}
				}
				else
				{
					alert("ERROR:" + Pnt.getErrorMsg() + ":  Status=" + Pnt.getStatus() + ": GtkId=" + Pnt.getGtkId() + ": BankINN=" + ((String) session.getAttribute("BankINN")));
					ISLogger.getLogger().warn("ERROR onSelect$accred:" + Pnt.getErrorMsg() + ":  Status=" + Pnt.getStatus() + ": GtkId=" + Pnt.getGtkId());
				}
				pen.setSrc("tfPenalty.zul?Contract=" + "&idn=" + current.getP1t1() + "&cont_type=" + current.getP2t1() + "&idc=" + current.getId() + "&summa1=" + current.getP20t1() + "&summa2=" + current.getP21t1() + "&val1=" + current.getP18t1()
						+ "&val2=" + current.getP19t1() + "&ht=" + desktopHeight);
			}
			pen.setSrc("tfPenalty.zul?Contract=" + "&idn=" + current.getP1t1()
					+ "&cont_type=" + current.getP2t1() + "&idc=" + current.getId()
					+ "&val1=" + current.getP18t1() + "&val2=" + current.getP19t1()
					+ "&ht=" + desktopHeight);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			ISLogger.getLogger().warn(CheckNull.getPstr(e));
			alert("ERROR:in tab Penalty " + (CheckNull.isEmpty(e.getMessage()) ? CheckNull.getPstr(e) : e.getMessage()));
		}
		finally
		{
			System.out.println("onSelect$penalt  - id_Penalty  " + current.getId() + " cont_idn=  " + current.getP1t1() + "&val1=" + current.getP18t1() + "&val2=" + current.getP19t1());
		}
	}
	
	public void onSelect$debet(Event evt)
	{
		String link = "tfDebet.zul?Contract=" + "&idn=" + current.getP1t1() + "&spr=" + sparam + "&idc=" + current.getId() +
				"&cont_type=" + current.getP2t1() + "&summa1=" + current.getP20t1() +
				"&summa2=" + current.getP21t1() + "&subj=" + current.getP17t1() + "&val1=" + current.getP18t1() + "&p65t1=" + current.getP65t1() + "&p67t1=" + current.getP67t1() +
				"&val2=" + current.getP19t1() + "&val22=" + current.getP22t1() + "&val23=" + current.getP23t1() + "&ht=" + desktopHeight;
		
		try
		{
			if (itype == 1) deb.setSrc(link);
			else
			{
				final BankServiceProxy ws = new BankServiceProxy((String) session.getAttribute("YESVO_URL"));
				long id_contract = current.getId();
				com.is.tf.debet.DebetService.remove(new Debet(), id_contract);
				com.sbs.service.DebetsResult debt = ws.getDebets(((String) (session.getAttribute("BankINN"))), current.getP1t1());
				
				if (debt.getStatus() == 0)
				{
					for (int i = 0; i < debt.getDebets().length; i++)
					{
						com.is.tf.debet.DebetService.create(debt.getDebets()[i], current.getP1t1(), id_contract);
					}
				}
				else
				{
					alert("ERROR:" + debt.getErrorMsg() + ":  Status=" + debt.getStatus() + ": GtkId=" + debt.getGtkId() + ": BankINN=" + ((String) session.getAttribute("BankINN")));
					ISLogger.getLogger().warn("ERROR onSelect$Debet:" + debt.getErrorMsg() + ":  Status=" + debt.getStatus() + ": GtkId=" + debt.getGtkId());
				}
				
				deb.setSrc(link);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			ISLogger.getLogger().warn(CheckNull.getPstr(e));
			alert("ERROR:in tab Debet " + (CheckNull.isEmpty(e.getMessage()) ? CheckNull.getPstr(e) : e.getMessage()));
		}
		finally
		{
			System.out.println("onSelect$Debet  - id_Debet  " + current.getId() + " cont_idn=  " + current.getP1t1() + "&val1=" + current.getP18t1() + "&val2=" + current.getP19t1());
		}
	}
	
	public void onSelect$kredit(Event evt)
	{
		try
		{
			if (itype == 1)
			{
				kre.setSrc("tfCredit.zul?Contract=" + "&idn=" + current.getP1t1() + "&idc=" + current.getId() + "&p69t1=" + current.getP69t1() + "&cont_type=" + current.getP2t1() + current.getId() + "&val1=" + current.getP18t1() + "&val2="
						+ current.getP19t1() + "&ht=" + desktopHeight);
			}
			else
			{
				final BankServiceProxy ws = new BankServiceProxy((String) session.getAttribute("YESVO_URL"));
				long id_contract = current.getId();
				com.is.tf.credit.CreditService.remove(new Credit(), id_contract);
				com.sbs.service.CreditsResult cre = ws.getCredits(((String) (session.getAttribute("BankINN"))), current.getP1t1());
				XMLSerializer.write(cre, "c:/credit1.xml");
				if (cre.getStatus() == 0)
				{
					for (int i = 0; i < cre.getCredits().length; i++)
					{
						com.is.tf.credit.CreditService.create(cre.getCredits()[i], current.getP1t1(), id_contract);
					}
				}
				else
				{
					alert("ERROR:" + cre.getErrorMsg() + ":  Status=" + cre.getStatus() + ": GtkId=" + cre.getGtkId() + ": BankINN=" + ((String) session.getAttribute("BankINN")));
					ISLogger.getLogger().warn("ERROR onSelect$Credit:" + cre.getErrorMsg() + ":  Status=" + cre.getStatus() + ": GtkId=" + cre.getGtkId());
				}
				kre.setSrc("tfCredit.zul?Contract=" + "&idn=" + current.getP1t1() + "&spr=" + sparam + "&idc=" + current.getId() +
									"&cont_type=" + current.getP2t1() + "&summa1=" + current.getP20t1() +
									"&summa2=" + current.getP21t1() + "&subj=" + current.getP17t1() + "&val1=" + current.getP18t1() + "&p69t1=" + current.getP69t1() +
									"&val2=" + current.getP19t1() + "&val22=" + current.getP22t1() + "&val23=" + current.getP23t1() + "&ht=" + desktopHeight);
				
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			ISLogger.getLogger().warn(CheckNull.getPstr(e));
			alert("ERROR:in tab Credit " + (CheckNull.isEmpty(e.getMessage()) ? CheckNull.getPstr(e) : e.getMessage()));
		}
		finally
		{
			System.out.println("onSelect$Credit  - id_Credit  " + current.getId() + " cont_idn=  " + current.getP1t1() + "&val1=" + current.getP18t1() + "&val2=" + current.getP19t1());
		}
	}
	
	public void onSelect$debetinf(Event evt)
	{
		String link = "tfDebetinfo.zul?Contract=" + "&idn=" + current.getP1t1() + "&spr=" + sparam + "&idc=" + current.getId() +
				"&cont_type=" + current.getP2t1() + "&summa1=" + current.getP20t1() +
				"&summa2=" + current.getP21t1() + "&subj=" + current.getP17t1() + "&val1=" + current.getP18t1() +
				"&val2=" + current.getP19t1() + "&val22=" + current.getP22t1() + "&val23=" + current.getP23t1() + "&ht=" + desktopHeight;
		
		try
		{
			if (itype == 1) debinf.setSrc(link);
			else
			{
				final BankServiceProxy ws = new BankServiceProxy((String) session.getAttribute("YESVO_URL"));
				long id_contract = current.getId();
				com.is.tf.debetinfo.DebetinfoService.remove(new Debetinfo(), id_contract);
				com.sbs.service.DebetsInfoResult debi = ws.getDebetsInfo(((String) (session.getAttribute("BankINN"))), current.getP1t1());
				XMLSerializer.write(debi, "c:/Debetinf1.xml");
				if (debi.getStatus() == 0)
				{
					for (int i = 0; i < debi.getDebetsInfo().length; i++)
					{
						com.is.tf.debetinfo.DebetinfoService.create(debi.getDebetsInfo()[i], current.getP1t1(), id_contract);
					}
				}
				else
				{
					alert("ERROR:" + debi.getErrorMsg() + ":  Status=" + debi.getStatus() + ": GtkId=" + debi.getGtkId() + ": BankINN=" + ((String) session.getAttribute("BankINN")));
					ISLogger.getLogger().warn("ERROR onSelect$accred:" + debi.getErrorMsg() + ":  Status=" + debi.getStatus() + ": GtkId=" + debi.getGtkId());
				}
				debinf.setSrc(link);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			ISLogger.getLogger().warn(CheckNull.getPstr(e));
			alert("ERROR:in tab Debetinfo " + (CheckNull.isEmpty(e.getMessage()) ? CheckNull.getPstr(e) : e.getMessage()));
		}
		finally
		{
			System.out.println("onSelect$Debetinfo  - id_Debetinfo " + current.getId() + " cont_idn=  " + current.getP1t1() + "&val1=" + current.getP18t1() + "&val2=" + current.getP19t1());
		}
	}
	
	public void onSelect$fund(Event evt)
	{
		try
		{
			if (itype == 1)
			{
				fnd.setSrc("tfFund.zul?idn=" + current.getP1t1() + "&spr=" + sparam + "&idc=" + current.getId() + "&cont_type=" + current.getP2t1() + "&summa1=" + current.getP20t1() + "&summa2=" + current.getP21t1() + "&subj=" + current.getP17t1()
						+ "&val1=" + current.getP18t1() + "&val2=" + current.getP19t1() + "&val22=" + current.getP22t1() + "&val23=" + current.getP23t1() + "&ht=" + desktopHeight);
			}
			else
			{
				final BankServiceProxy ws = new BankServiceProxy((String) session.getAttribute("YESVO_URL"));
				long id_contract = current.getId();
				com.is.tf.fund.FundService.remove(new Fund(), id_contract);
				com.sbs.service.FundsResult fund = ws.getFunds(((String) (session.getAttribute("BankINN"))), current.getP1t1());
				XMLSerializer.write(fnd, "c:/fund1.xml");
				if (fund.getStatus() == 0)
				{
					for (int i = 0; i < fund.getFunds().length; i++)
					{
						
						com.is.tf.fund.FundService.create(fund.getFunds()[i], current.getP1t1(), id_contract);
					}
				}
				else
				{
					alert("ERROR Tab_payment:" + fund.getErrorMsg() + ":  Status=" + fund.getStatus() + ": GtkId=" + fund.getGtkId() + ": BankINN=" + ((String) session.getAttribute("BankINN")));
					ISLogger.getLogger().warn("ERROR onSelect$payment:" + fund.getErrorMsg() + ":  Status=" + fund.getStatus() + ": GtkId=" + fund.getGtkId());
				}
				fnd.setSrc("tfFund.zul?idn=" + current.getP1t1() + "&spr=" + sparam + "&idc=" + current.getId() + "&cont_type=" + current.getP2t1() + "&summa1=" + current.getP20t1() + "&summa2=" + current.getP21t1() + "&subj=" + current.getP17t1()
						+ "&val1=" + current.getP18t1() + "&val2=" + current.getP19t1() + "&val22=" + current.getP22t1() + "&val23=" + current.getP23t1() + "&ht=" + desktopHeight);
			}
		}
		catch (Exception e)
		{
			ISLogger.getLogger().warn(CheckNull.getPstr(e));
			alert("ERROR:in tabpanel fund " + (CheckNull.isEmpty(e.getMessage()) ? CheckNull.getPstr(e) : e.getMessage()));
			e.printStackTrace();
		}
		finally
		{
		}
	}
	
	public void onSelect$commgnk(Event evt)
	{
		try
		{
			if (itype == 1)
			{
				comgnk.setSrc("tfCommissiongnk.zul?idn=" + current.getP1t1() + "&cont_type=" + current.getP2t1() + "&idc=" + current.getId() + "&summa1=" + current.getP20t1() + "&summa2=" + current.getP21t1() + "&val1=" + current.getP18t1()
						+ "&val2=" + current.getP19t1() + "&ht=" + desktopHeight);
			}
			else
			{
				final BankServiceProxy ws = new BankServiceProxy((String) session.getAttribute("YESVO_URL"));
				long id_contract = current.getId();
				com.is.tf.Commissiongnk.CommissiongnkService.remove(new com.is.tf.Commissiongnk.Commissiongnk(), id_contract);
				com.sbs.service.CommissionsGNKResult comg = ws.getCommissionsGNK(((String) (session.getAttribute("BankINN"))), current.getP1t1());
				XMLSerializer.write(comg, "c:/commgnk1.xml");
				
				if (comg.getStatus() == 0)
				{
					for (int i = 0; i < comg.getCommissionsGNK().length; i++)
					{
						com.is.tf.Commissiongnk.CommissiongnkService.create(comg.getCommissionsGNK()[i], current.getP1t1(), id_contract);
					}
				}
				else
				{
					alert("ERROR:" + comg.getErrorMsg() + ":  Status=" + comg.getStatus() + ": GtkId=" + comg.getGtkId() + ": BankINN=" + ((String) session.getAttribute("BankINN")));
					ISLogger.getLogger().warn("ERROR onSelect$Commissiongnk:" + comg.getErrorMsg() + ":  Status=" + comg.getStatus() + ": GtkId=" + comg.getGtkId());
				}
				comgnk.setSrc("tfCommissiongnk.zul?Contract=" + "&idn=" + current.getP1t1() + "&spr=" + sparam + "&idc=" + current.getId() +
												"&cont_type=" + current.getP2t1() + "&summa1=" + current.getP20t1() +
												"&summa2=" + current.getP21t1() + "&subj=" + current.getP17t1() + "&val1=" + current.getP18t1() +
												"&val2=" + current.getP19t1() + "&val22=" + current.getP22t1() + "&val23=" + current.getP23t1() + "&ht=" + desktopHeight);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			ISLogger.getLogger().warn(CheckNull.getPstr(e));
			alert("ERROR:in tab Commissiongnk " + (CheckNull.isEmpty(e.getMessage()) ? CheckNull.getPstr(e) : e.getMessage()));
		}
		finally
		{
			System.out.println("onSelect$Commissiongnk  - id_Commissiongnk  " + current.getId() + " cont_idn=  " + current.getP1t1() + "&val1=" + current.getP18t1() + "&val2=" + current.getP19t1());
		}
	}
	
	public void onSelect$comm(Event evt)
	{
		try
		{
			if (itype == 1)
			{
				comi.setSrc("tfCommission.zul?idn=" + current.getP1t1() + "&cont_type=" + current.getP2t1() + "&idc=" + current.getId() + "&summa1=" + current.getP20t1() + "&summa2=" + current.getP21t1() + "&val1=" + current.getP18t1() + "&val2="
						+ current.getP19t1() + "&ht=" + desktopHeight);
			}
			else
			{
				final BankServiceProxy ws = new BankServiceProxy((String) session.getAttribute("YESVO_URL"));
				long id_contract = current.getId();
				com.is.tf.Commission.CommissionService.remove(new com.is.tf.Commission.Commission(), id_contract);
				com.sbs.service.CommissionsResult comm = ws.getCommissions(((String) (session.getAttribute("BankINN"))), current.getP1t1());
				
				if (comm.getStatus() == 0)
				{
					for (int i = 0; i < comm.getCommissions().length; i++)
					{
						com.is.tf.Commission.CommissionService.create(comm.getCommissions()[i], current.getP1t1(), id_contract);
					}
				}
				else
				{
					alert("ERROR:" + comm.getErrorMsg() + ":  Status=" + comm.getStatus() + ": GtkId=" + comm.getGtkId() + ": BankINN=" + ((String) session.getAttribute("BankINN")));
					ISLogger.getLogger().warn("ERROR onSelect$Commission:" + comm.getErrorMsg() + ":  Status=" + comm.getStatus() + ": GtkId=" + comm.getGtkId());
				}
				
				comi.setSrc("tfCommission.zul?Contract=" + "&idn=" + current.getP1t1() + "&spr=" + sparam + "&idc=" + current.getId() +
										"&cont_type=" + current.getP2t1() + "&summa1=" + current.getP20t1() +
										"&summa2=" + current.getP21t1() + "&subj=" + current.getP17t1() + "&val1=" + current.getP18t1() +
										"&val2=" + current.getP19t1() + "&val22=" + current.getP22t1() + "&val23=" + current.getP23t1() + "&ht=" + desktopHeight);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			ISLogger.getLogger().warn(CheckNull.getPstr(e));
			alert("ERROR:in tab Commission " + (CheckNull.isEmpty(e.getMessage()) ? CheckNull.getPstr(e) : e.getMessage()));
		}
		finally
		{
			System.out.println("onSelect$Commission  - id_Commission  " + current.getId() + " cont_idn=  " + current.getP1t1() + "&val1=" + current.getP18t1() + "&val2=" + current.getP19t1());
		}
	}
	
	public void onSelect$compens_tab(Event evt)
	{
		try
		{
			String link = "tfCompensation.zul?Contract=" + "&idn=" + current.getP1t1() + "&spr=" + sparam + "&idc=" + current.getId() +
					"&cont_type=" + current.getP2t1() + "&summa1=" + current.getP20t1() +
					"&summa2=" + current.getP21t1() + "&subj=" + current.getP17t1() + "&val1=" + current.getP18t1() +
					"&val2=" + current.getP19t1() + "&val22=" + current.getP22t1() + "&val23=" + current.getP23t1() + "&ht=" + desktopHeight;
			if (itype == 1)
			{
				compens.setSrc(link);
			}
			else
			{
				final BankServiceProxy ws = new BankServiceProxy((String) session.getAttribute("YESVO_URL"));
				long id_contract = current.getId();
				com.is.tf.compensation.CompensationService.remove(new com.is.tf.compensation.Compensation(), id_contract);
				com.sbs.service.CompensationsResult comg = ws.getCompensations(((String) (session.getAttribute("BankINN"))), current.getP1t1());
				XMLSerializer.write(comg, "c:/commpens1.xml");
				
				if (comg.getStatus() == 0)
				{
					for (int i = 0; i < comg.getCompensations().length; i++)
					{
						com.is.tf.compensation.CompensationService.create(comg.getCompensations()[i], current.getP1t1(), id_contract);
					}
				}
				else
				{
					alert("ERROR:" + comg.getErrorMsg() + ":  Status=" + comg.getStatus() + ": GtkId=" + comg.getGtkId() + ": BankINN=" + ((String) session.getAttribute("BankINN")));
					ISLogger.getLogger().warn("ERROR onSelect$Compensation:" + comg.getErrorMsg() + ":  Status=" + comg.getStatus() + ": GtkId=" + comg.getGtkId());
				}
				compens.setSrc(link);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			ISLogger.getLogger().warn(CheckNull.getPstr(e));
			alert("ERROR:in tab Compensation " + (CheckNull.isEmpty(e.getMessage()) ? CheckNull.getPstr(e) : e.getMessage()));
		}
		finally
		{
			System.out.println("onSelect$Compensation  - id_Compensation  " + current.getId() + " cont_idn=  " + current.getP1t1() + "&val1=" + current.getP18t1() + "&val2=" + current.getP19t1());
		}
	}
	
	public void onSelect$mvtoex(Event evt)
	{
		try
		{
			System.out.println("Contract cont_type " + current.getP2t1());
			movetoexp.setHeight((desktopHeight - 130) + "px");
			if (itype == 1)
			{
				movetoexp.setSrc("tfMovetoex.zul?Contract=" + "&idn=" + current.getP1t1() + "&inn=" + current.getP61t1() + "&spr=" + sparam + "&cont_type=" + current.getP2t1() + "&idc=" + current.getId() + "&summa1=" + current.getP20t1()
						+ "&summa2=" + current.getP21t1() + "&val1=" + current.getP18t1() + "&val2=" + current.getP19t1() + "&ht=" + desktopHeight);
			}
			else
			{
				final BankServiceProxy ws = new BankServiceProxy((String) session.getAttribute("YESVO_URL"));
				long id_contract = current.getId();
				// com.is.tf.penalty.PenaltyService.remove(new Penalty()
				// ,id_contract);
				com.sbs.service.MovesToExResult Mfe = ws.getMovesToEx(((String) (session.getAttribute("BankINN"))), current.getP1t1());
				XMLSerializer.write(Mfe, "c:/Mfe.xml");
				
				if (Mfe.getStatus() == 0)
				{
					for (int i = 0; i < Mfe.getMovesToEx().length; i++)
					{
						com.is.tf.movetoex.MovetoexService.create(Mfe.getMovesToEx()[i], current.getP1t1(), id_contract);
					}
				}
				else
				{
					alert("ERROR:" + Mfe.getErrorMsg() + ":  Status=" + Mfe.getStatus() +
							": GtkId=" + Mfe.getGtkId() + ": BankINN=" +
							((String) session.getAttribute("BankINN")));
					ISLogger.getLogger().warn("ERROR onSelect$accred:" + Mfe.getErrorMsg() + ":  Status=" + Mfe.getStatus() + ": GtkId=" + Mfe.getGtkId());
				}
				movetoexp.setSrc("tfMovetoex.zul?Contract=" + "&idn=" + current.getP1t1() + "&inn=" + current.getP61t1() + "&spr=" + sparam + "&cont_type=" + current.getP2t1() + "&idc=" + current.getId() + "&summa1=" + current.getP20t1()
						+ "&summa2=" + current.getP21t1() + "&val1=" + current.getP18t1() + "&val2=" + current.getP19t1() + "&ht=" + desktopHeight);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			ISLogger.getLogger().warn(CheckNull.getPstr(e));
			alert("ERROR:in tab Movetoex " + (CheckNull.isEmpty(e.getMessage()) ? CheckNull.getPstr(e) : e.getMessage()));
		}
		finally
		{
			System.out.println("onSelect$tfMovetoex - id_Movetoex  " + current.getId() + " cont_idn=  " + current.getP1t1() + "&val1=" + current.getP18t1() + "&val2=" + current.getP19t1());
		}
	}
	
	public void onSelect$mvfromex(Event evt)
	{
		try
		{
			if (itype == 1)
			{
				movefromexp.setSrc("tfMovefromex.zul?Contract=" + "&inn=" + current.getP61t1() + "&cont_type=" + current.getP2t1() + "&idn=" + current.getP1t1() + "&spr=" + sparam + "&idc=" + current.getId() + "&val1=" + current.getP18t1()
						+ "&val2=" + current.getP19t1() + "&ht=" + desktopHeight);
			}
			else
			{
				final BankServiceProxy ws = new BankServiceProxy((String) session.getAttribute("YESVO_URL"));
				long id_contract = current.getId();
				// com.is.tf.penalty.PenaltyService.remove(new Penalty()
				// ,id_contract);
				com.sbs.service.MovesFromExResult Mfe = ws.getMovesFromEx(((String) (session.getAttribute("BankINN"))), current.getP1t1());
				XMLSerializer.write(Mfe, "c:/Mfe.xml");
				
				if (Mfe.getStatus() == 0)
				{
					for (int i = 0; i < Mfe.getMovesFromEx().length; i++)
					{
						com.is.tf.movefromex.MoveFromExService.create(Mfe.getMovesFromEx()[i], current.getP1t1(), id_contract);
					}
				}
				else
				{
					alert("ERROR:" + Mfe.getErrorMsg() + ":  Status=" + Mfe.getStatus() +
							": GtkId=" + Mfe.getGtkId() + ": BankINN=" +
							((String) session.getAttribute("BankINN")));
					ISLogger.getLogger().warn("ERROR onSelect$accred:" + Mfe.getErrorMsg() + ":  Status=" + Mfe.getStatus() + ": GtkId=" + Mfe.getGtkId());
				}
				movefromexp.setSrc("tfMovefromex.zul?Contract=" + "&idn=" + current.getP1t1() + "&spr=" + sparam + "&cont_type=" + current.getP2t1() + "&idc=" + current.getId() + "&summa1=" + current.getP20t1() + "&summa2=" + current.getP21t1()
						+ "&val1=" + current.getP18t1() + "&val2=" + current.getP19t1() + "&ht=" + desktopHeight);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			ISLogger.getLogger().warn(CheckNull.getPstr(e));
			alert("ERROR:in tab Movefromex " + (CheckNull.isEmpty(e.getMessage()) ? CheckNull.getPstr(e) : e.getMessage()));
		}
		finally
		{
			System.out.println("onSelect$tfMovefromex - id_Movefromex  " + current.getId() + " cont_idn=  " + current.getP1t1() + "&val1=" + current.getP18t1() + "&val2=" + current.getP19t1());
		}
	}
	
	public void onSelect$movefromim(Event evt)
	{
		try
		{
			if (itype == 1)
			{
				movefrmim.setSrc("tfMovefromim.zul?idn=" + current.getP1t1() + "&spr=" + sparam + "&idc=" + current.getId() + "&cont_type=" + current.getP2t1() + "&subj=" + current.getP17t1() + "&val1=" + current.getP18t1() + "&val2="
						+ current.getP19t1() + "&ht=" + desktopHeight);
			}
			else
			{
				final BankServiceProxy ws = new BankServiceProxy((String) session.getAttribute("YESVO_URL"));
				long id_contract = current.getId();
				// com.is.tf.penalty.PenaltyService.remove(new Penalty()
				// ,id_contract);
				com.sbs.service.MovesFromImResult Mfi = ws.getMovesFromIm(((String) (session.getAttribute("BankINN"))), current.getP1t1());
				XMLSerializer.write(Mfi, "c:/Mfi.xml");
				
				if (Mfi.getStatus() == 0)
				{
					for (int i = 0; i < Mfi.getMovesFromIm().length; i++)
					{
						com.is.tf.movefromim.MovefromimService.create(Mfi.getMovesFromIm()[i], current.getP1t1(), id_contract);
					}
				}
				else
				{
					alert("ERROR:" + Mfi.getErrorMsg() + ":  Status=" + Mfi.getStatus() +
							": GtkId=" + Mfi.getGtkId() + ": BankINN=" +
							((String) session.getAttribute("BankINN")));
					ISLogger.getLogger().warn("ERROR onSelect$accred:" + Mfi.getErrorMsg() + ":  Status=" + Mfi.getStatus() + ": GtkId=" + Mfi.getGtkId());
				}
				movefrmim.setSrc("tfMovefromim.zul?Contract=" + "&idn=" + current.getP1t1() + "&spr=" + sparam + "&cont_type=" + current.getP2t1() + "&idc=" + current.getId() + "&summa1=" + current.getP20t1() + "&summa2=" + current.getP21t1()
						+ "&val1=" + current.getP18t1() + "&val2=" + current.getP19t1() + "&ht=" + desktopHeight);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			ISLogger.getLogger().warn(CheckNull.getPstr(e));
			alert("ERROR:in tab Movefromim " + (CheckNull.isEmpty(e.getMessage()) ? CheckNull.getPstr(e) : e.getMessage()));
		}
		finally
		{
			System.out.println("onSelect$tfMovefromim - id_Movefromim  " + current.getId() + " cont_idn=  " + current.getP1t1() + "&val1=" + current.getP18t1() + "&val2=" + current.getP19t1());
		}
	}
	
	public void onSelect$movetoim_tab(Event evt)
	{
		try
		{
			movetoimp_include.setHeight((desktopHeight - 130) + "px");
			if (itype == 1)
			{
				movetoimp_include.setSrc("tfMovetoim.zul?idn=" + current.getP1t1() + "&spr=" + sparam + "&inn=" + current.getP61t1() + "&P65=" + current.getP65t1() + "&P67=" + current.getP67t1() + "&idc=" + current.getId() + "&cont_type="
						+ current.getP2t1() + "&subj=" + current.getP17t1() + "&val22=" + current.getP22t1() + "&val23=" + current.getP23t1() + "&val18=" + current.getP18t1() + "&val19=" + current.getP19t1() + "&ht=" + desktopHeight);
			}
			else
			{
				final BankServiceProxy ws = new BankServiceProxy((String) session.getAttribute("YESVO_URL"));
				long id_contract = current.getId();
				// com.is.tf.penalty.PenaltyService.remove(new Penalty()
				// ,id_contract);
				com.sbs.service.MovesToImResult Mti = ws.getMovesToIm(((String) (session.getAttribute("BankINN"))), current.getP1t1());
				XMLSerializer.write(Mti, "c:/Mti.xml");
				
				if (Mti.getStatus() == 0)
				{
					for (int i = 0; i < Mti.getMovesToIm().length; i++)
					{
						com.is.tf.movetoim.MovetoimService.create(Mti.getMovesToIm()[i], "", "", "", "", current.getP1t1(), id_contract);
					}
				}
				else
				{
					alert("ERROR:" + Mti.getErrorMsg() + ":  Status=" + Mti.getStatus() +
							": GtkId=" + Mti.getGtkId() + ": BankINN=" +
							((String) session.getAttribute("BankINN")));
					ISLogger.getLogger().warn("ERROR onSelect$accred:" + Mti.getErrorMsg() + ":  Status=" + Mti.getStatus() + ": GtkId=" + Mti.getGtkId());
				}
				movetoimp_include.setSrc("tfMovetoim.zul?Contract=" + "&idn=" + current.getP1t1() + "&spr=" + sparam + "&cont_type=" + current.getP2t1() + "&idc=" + current.getId() + "&summa1=" + current.getP20t1() + "&summa2=" + current.getP21t1()
						+ "&val1=" + current.getP18t1() + "&val2=" + current.getP19t1() + "&ht=" + desktopHeight);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			ISLogger.getLogger().warn(CheckNull.getPstr(e));
			alert("ERROR:in tab Movetoim " + (CheckNull.isEmpty(e.getMessage()) ? CheckNull.getPstr(e) : e.getMessage()));
		}
		finally
		{
			System.out.println("onSelect$tfMovetoim - id_Movetoim  " + current.getId() + " cont_idn=  " + current.getP1t1() + "&val1=" + current.getP18t1() + "&val2=" + current.getP19t1());
		}
	}
	
	/*
	 * public void onSelect$accred(Event evt) { try {
	 * //acr.setHeight((desktopHeight-130)+"px"); if (itype==1) {
	 * acr.setSrc("tfAccreditiv.zul?idn="
	 * +current.getP1t1()+"&cont_type="+current
	 * .getP2t1()+"&spr="+sparam+"&idc="+
	 * current.getId()+"&summa1="+current.getP20t1
	 * ()+"&summa2="+current.getP21t1(
	 * )+"&val1="+current.getP18t1()+"&val2="+current
	 * .getP19t1()+"&ht="+desktopHeight); }else { final BankServiceProxy ws =
	 * new BankServiceProxy((String)session.getAttribute("YESVO_URL")); long
	 * id_contract = current.getId();
	 * com.is.tf.Accreditiv.AccreditivService.remove(new Accreditiv()
	 * ,id_contract); com.sbs.service.AccreditivesResult acr1 =
	 * ws.getAccreditives(((String)session.getAttribute("BankINN")),
	 * current.getP1t1()); XMLSerializer.write(acr1, "c:/acr1.xml"); if
	 * (acr1.getStatus()==0){ for (int i=0;i<acr1.getAccreditives().length;i++){
	 * com.is.tf.Accreditiv.AccreditivService.create2(acr1.getAccreditives()[i],
	 * current.getP1t1(), id_contract); } } else {
	 * alert("ERROR:"+acr1.getErrorMsg
	 * ()+":  Status="+acr1.getStatus()+": GtkId="
	 * +acr1.getGtkId()+": BankINN="+((String)session.getAttribute("BankINN")));
	 * ISLogger
	 * .getLogger().warn("ERROR onSelect$accred:"+acr1.getErrorMsg()+":  Status="
	 * +acr1.getStatus()+": GtkId="+acr1.getGtkId()); }
	 * acr.setSrc("tfAccreditiv.zul?Contract="
	 * +"&idn="+current.getP1t1()+"&spr="+
	 * sparam+"&cont_type="+current.getP2t1()+
	 * "&idc="+current.getId()+"&summa1="+
	 * current.getP20t1()+"&summa2="+current.getP21t1
	 * ()+"&val1="+current.getP18t1
	 * ()+"&val2="+current.getP19t1()+"&ht="+desktopHeight); } }catch (Exception
	 * e) { e.printStackTrace();
	 * ISLogger.getLogger().warn(CheckNull.getPstr(e));
	 * alert("ERROR:in tab accreditiv "
	 * +(CheckNull.isEmpty(e.getMessage())?CheckNull
	 * .getPstr(e):e.getMessage())); } finally {
	 * System.out.println("onSelect$accred  - id_contract  "
	 * +current.getId()+" cont_idn=  "
	 * +current.getP1t1()+"&val1="+current.getP18t1
	 * ()+"&val2="+current.getP19t1()); } }
	 */
	public void onSelect$refexp(Event evt)
	{
		try
		{
			if (itype == 1)
			{
				refundex.setSrc("tfRefundexp.zul?Contract=" + "&inn=" + current.getP61t1() + "&summa1=" + current.getP20t1() + "&summa2=" + current.getP21t1() + "&cont_type=" + current.getP2t1() + "&idn=" + current.getP1t1() + "&idc="
						+ current.getId() + "&val1=" + current.getP18t1() + "&val2=" + current.getP19t1() + "&ht=" + desktopHeight);
			}
			else
			{
				final BankServiceProxy ws = new BankServiceProxy((String) session.getAttribute("YESVO_URL"));
				long id_contract = current.getId();
				com.is.tf.refundexp.RefundexpService.remove(new Refundexp(), id_contract);
				com.sbs.service.RefundsExpResult refe = ws.getRefundsExp(((String) (session.getAttribute("BankINN"))), current.getP1t1());
				XMLSerializer.write(refe, "c:/refexp.xml");
				if (refe.getStatus() == 0)
				{
					for (int i = 0; i < refe.getRefundsExp().length; i++)
					{
						com.is.tf.refundexp.RefundexpService.create(refe.getRefundsExp()[i], current.getP1t1(), id_contract);
					}
					
				}
				else
				{
					alert("ERROR:" + refe.getErrorMsg() + ":  Status=" + refe.getStatus() + ": GtkId=" + refe.getGtkId() + ": BankINN=" + ((String) session.getAttribute("BankINN")));
					ISLogger.getLogger().warn("ERROR onSelect$accred:" + refe.getErrorMsg() + ":  Status=" + refe.getStatus() + ": GtkId=" + refe.getGtkId());
				}
				refundex.setSrc("tfRefundexp.zul?Contract=" + "&inn=" + current.getP61t1() + "&summa1=" + current.getP20t1() + "&summa2=" + current.getP21t1() + "&cont_type=" + current.getP2t1() + "&idn=" + current.getP1t1() + "&idc="
						+ current.getId() + "&val1=" + current.getP18t1() + "&val2=" + current.getP19t1() + "&ht=" + desktopHeight);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			ISLogger.getLogger().warn(CheckNull.getPstr(e));
			alert("ERROR:in tab RefundExp " + (CheckNull.isEmpty(e.getMessage()) ? CheckNull.getPstr(e) : e.getMessage()));
			
		}
		finally
		{
		}
	}
	
	public void onSelect$leaseex(Event evt)
	{
		String link = "tfLease.zul?Contract=" + "&idn=" + current.getP1t1() + "&cont_type="
				+ current.getP2t1() + "&spr=" + sparam
				+ "&idc=" + current.getId() + "&val1=" + current.getP18t1()
				+ "&val2=" + current.getP19t1() + "&ht=" + desktopHeight;
		
		try
		{
			if (itype == 1)
			{
				leaseexp.setSrc(link);
			}
			else
			{
				final BankServiceProxy ws = new BankServiceProxy((String) session.getAttribute("YESVO_URL"));
				long id_contract = current.getId();
				com.is.tf.lease.LeaseService.remove(new com.is.tf.lease.Lease(), id_contract);
				com.sbs.service.LeasesResult leasExp = ws.getLeases(((String) (session.getAttribute("BankINN"))), current.getP1t1());
				XMLSerializer.write(leasExp, "c:/Lease1.xml");
				
				if (leasExp.getStatus() == 0)
				{
					for (int i = 0; i < leasExp.getLeases().length; i++)
					{
						com.is.tf.lease.LeaseService.create(leasExp.getLeases()[i], current.getP1t1(), id_contract);
					}
				}
				else
				{
					alert("ERROR:" + leasExp.getErrorMsg() + ":  Status=" + leasExp.getStatus() + ": GtkId=" + leasExp.getGtkId() + ": BankINN=" + ((String) session.getAttribute("BankINN")));
					ISLogger.getLogger().warn("ERROR onSelect$LeaseExp:" + leasExp.getErrorMsg() + ":  Status=" + leasExp.getStatus() + ": GtkId=" + leasExp.getGtkId());
				}
				leaseexp.setSrc(link);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			ISLogger.getLogger().warn(CheckNull.getPstr(e));
			alert("ERROR:in tab Lease Exp " + (CheckNull.isEmpty(e.getMessage()) ? CheckNull.getPstr(e) : e.getMessage()));
			
		}
		finally
		{
			// System.out.println("onSelect$garant  - id_contract  "+current.getId()+" cont_idn=  "+current.getP1t1()+"&val1="+current.getP18t1()+"&val2="+current.getP19t1());
		}
	}
	
	public void onSelect$leaseim(Event evt)
	{
		String link = "tfLease.zul?Contract=" + "&idn=" + current.getP1t1() + "&cont_type="
				+ current.getP2t1()
				+ "&idc=" + current.getId() + "&val1=" + current.getP18t1()
				+ "&val2=" + current.getP19t1() + "&ht=" + desktopHeight;
		try
		{
			if (itype == 1)
			{
				leaseimp.setSrc(link);
			}
			else
			{
				final BankServiceProxy ws = new BankServiceProxy((String) session.getAttribute("YESVO_URL"));
				long id_contract = current.getId();
				com.is.tf.lease.LeaseService.remove(new com.is.tf.lease.Lease(), id_contract);
				com.sbs.service.LeasesResult leasExp = ws.getLeases(((String) (session.getAttribute("BankINN"))), current.getP1t1());
				XMLSerializer.write(leasExp, "c:/Lease1.xml");
				
				if (leasExp.getStatus() == 0)
				{
					for (int i = 0; i < leasExp.getLeases().length; i++)
					{
						com.is.tf.lease.LeaseService.create(leasExp.getLeases()[i], current.getP1t1(), id_contract);
					}
				}
				else
				{
					alert("ERROR:" + leasExp.getErrorMsg() + ":  Status=" + leasExp.getStatus() + ": GtkId=" + leasExp.getGtkId() + ": BankINN=" + ((String) session.getAttribute("BankINN")));
					ISLogger.getLogger().warn("ERROR onSelect$LeaseExp:" + leasExp.getErrorMsg() + ":  Status=" + leasExp.getStatus() + ": GtkId=" + leasExp.getGtkId());
				}
				leaseimp.setSrc(link);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			ISLogger.getLogger().warn(CheckNull.getPstr(e));
			alert("ERROR:in tab Lease Imp " + (CheckNull.isEmpty(e.getMessage()) ? CheckNull.getPstr(e) : e.getMessage()));
			
		}
		finally
		{
			// System.out.println("onSelect$garant  - id_contract  "+current.getId()+" cont_idn=  "+current.getP1t1()+"&val1="+current.getP18t1()+"&val2="+current.getP19t1());
		}
	}
	
	public void onSelect$taxex(Event evt)
	{
		try
		{
			if (itype == 1)
			{
				taxexp.setSrc("tfTax.zul?Contract=" + "&idn=" + current.getP1t1() + "&cont_type=" + current.getP2t1() + "&idc=" + current.getId() + "&summa1=" + current.getP20t1() + "&summa2=" + current.getP21t1() + "&val1=" + current.getP18t1()
						+ "&val2=" + current.getP19t1() + "&ht=" + desktopHeight);
			}
			else
			{
				final BankServiceProxy ws = new BankServiceProxy((String) session.getAttribute("YESVO_URL"));
				long id_contract = current.getId();
				com.is.tf.tax.TaxService.remove(new Tax(), id_contract);
				com.sbs.service.TaxesResult tax = ws.getTaxes(((String) (session.getAttribute("BankINN"))), current.getP1t1());
				XMLSerializer.write(tax, "c:/tax1.xml");
				
				if (tax.getStatus() == 0)
				{
					for (int i = 0; i < tax.getTaxes().length; i++)
					{
						com.is.tf.tax.TaxService.create(tax.getTaxes()[i], current.getP1t1(), id_contract);
					}
				}
				else
				{
					alert("ERROR:" + tax.getErrorMsg() + ":  Status=" + tax.getStatus() + ": GtkId=" + tax.getGtkId() + ": BankINN=" + ((String) session.getAttribute("BankINN")));
					ISLogger.getLogger().warn("ERROR onSelect$accred:" + tax.getErrorMsg() + ":  Status=" + tax.getStatus() + ": GtkId=" + tax.getGtkId());
				}
				
				taxexp.setSrc("tfTax.zul?Contract=" + "&idn=" + current.getP1t1() + "&cont_type=" + current.getP2t1() + "&idc=" + current.getId() + "&summa1=" + current.getP20t1() + "&summa2=" + current.getP21t1() + "&val1=" + current.getP18t1()
						+ "&val2=" + current.getP19t1() + "&ht=" + desktopHeight);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			ISLogger.getLogger().warn(CheckNull.getPstr(e));
			alert("ERROR:in tab Tax " + (CheckNull.isEmpty(e.getMessage()) ? CheckNull.getPstr(e) : e.getMessage()));
		}
		finally
		{
			System.out.println("onSelect$Tax  - id_Tax  " + current.getId() + " cont_idn=  " + current.getP1t1() + "&val1=" + current.getP18t1() + "&val2=" + current.getP19t1());
		}
	}
	
	public void onSelect$taxim(Event evt)
	{
		try
		{
			if (itype == 1)
			{
				taximp.setSrc("tfTax.zul?idn=" + current.getP1t1() + "&cont_type=" + current.getP2t1() + "&summa1=" + current.getP20t1() + "&summa2=" + current.getP21t1() + "&subj=" + current.getP17t1() + "&val1=" + current.getP18t1() + "&val2="
						+ current.getP19t1() + "&ht=" + desktopHeight);
			}
			else
			{
				final BankServiceProxy ws = new BankServiceProxy("http://91.213.31.234:8892/yeisvo_bank/service");
				long id_contract = current.getId();
				com.sbs.service.TaxesResult tax = ws.getTaxes(((String) (session.getAttribute("BankINN"))), current.getP1t1());
				for (int i = 0; i < tax.getTaxes().length; i++)
				{
					System.out.println("Tax  " + tax.getTaxes()[i].getP11T39());
					com.is.tf.tax.TaxService.create(tax.getTaxes()[i], current.getP1t1(), id_contract);
				}
				taximp.setSrc("tfTax.zul?idn=" + current.getP1t1() + "&cont_type=" + current.getP2t1() + "&summa1=" + current.getP20t1() + "&summa2=" + current.getP21t1() + "&subj=" + current.getP17t1() + "&val1=" + current.getP18t1() + "&val2="
						+ current.getP19t1() + "&ht=" + desktopHeight);
			}
		}
		catch (Exception e)
		{
			// System.out.println("contract_idn.getValue()  "+current.getId()+" p1t1  "+current.getP1t1());
			e.printStackTrace();
		}
		finally
		{
			// System.out.println("onSelect$garant  - id_contract  "+current.getId()+" cont_idn=  "+current.getP1t1()+"&val1="+current.getP18t1()+"&val2="+current.getP19t1());
		}
	}
	
	/*
	 * public void onSelect$accred(Event evt) { try {
	 * //acr.setHeight((desktopHeight-130)+"px"); if (itype==1) {
	 * acr.setSrc("tfAccreditiv.zul?idn="
	 * +current.getP1t1()+"&cont_type="+current
	 * .getP2t1()+"&idc="+current.getId()
	 * +"&summa1="+current.getP20t1()+"&summa2="
	 * +current.getP21t1()+"&val1="+current
	 * .getP18t1()+"&val2="+current.getP19t1()+"&ht="+desktopHeight); }else {
	 * final BankServiceProxy ws = new
	 * BankServiceProxy((String)session.getAttribute("YESVO_URL")); long
	 * id_contract = current.getId();
	 * com.is.tf.Accreditiv.AccreditivService.remove(new Accreditiv()
	 * ,id_contract); com.sbs.service.AccreditivesResult acr1 =
	 * ws.getAccreditives(((String)session.getAttribute("BankINN")),
	 * current.getP1t1()); XMLSerializer.write(acr1, "c:/acr1.xml"); if
	 * (acr1.getStatus()==0){ for (int i=0;i<acr1.getAccreditives().length;i++){
	 * com.is.tf.Accreditiv.AccreditivService.create2(acr1.getAccreditives()[i],
	 * current.getP1t1(), id_contract); } } else {
	 * alert("ERROR:"+acr1.getErrorMsg
	 * ()+":  Status="+acr1.getStatus()+": GtkId="
	 * +acr1.getGtkId()+": BankINN="+((String)session.getAttribute("BankINN")));
	 * ISLogger
	 * .getLogger().warn("ERROR onSelect$accred:"+acr1.getErrorMsg()+":  Status="
	 * +acr1.getStatus()+": GtkId="+acr1.getGtkId()); }
	 * acr.setSrc("tfAccreditiv.zul?Contract="
	 * +"&idn="+current.getP1t1()+"&cont_type="
	 * +current.getP2t1()+"&idc="+current
	 * .getId()+"&summa1="+current.getP20t1()+"&summa2="
	 * +current.getP21t1()+"&val1="
	 * +current.getP18t1()+"&val2="+current.getP19t1()+"&ht="+desktopHeight); }
	 * }catch (Exception e) { e.printStackTrace();
	 * ISLogger.getLogger().warn(CheckNull.getPstr(e));
	 * alert("ERROR:in tab accreditiv "
	 * +(CheckNull.isEmpty(e.getMessage())?CheckNull
	 * .getPstr(e):e.getMessage())); } finally {
	 * System.out.println("onSelect$accred  - id_contract  "
	 * +current.getId()+" cont_idn=  "
	 * +current.getP1t1()+"&val1="+current.getP18t1
	 * ()+"&val2="+current.getP19t1()); } }
	 */
	
	public void onSelect$sale_tab(Event evt)
	{
		try
		{
			if (itype == 1)
			{
				sale_include.setSrc("tfSale.zul?idn=" + current.getP1t1() + "&cont_type=" + current.getP2t1() + "&idc=" + current.getId() + "&subj=" + current.getP17t1() + "&summa1=" + current.getP20t1() + "&summa2=" + current.getP21t1() + "&val1="
						+ current.getP18t1() + "&val2=" + current.getP19t1() + "&ht=" + desktopHeight);
			}
			else
			{
				final BankServiceProxy ws = new BankServiceProxy((String) session.getAttribute("YESVO_URL"));
				long id_contract = current.getId();
				com.sbs.service.SalesResult sal = ws.getSales(((String) (session.getAttribute("BankINN"))), current.getP1t1());
				XMLSerializer.write(sal, "c:/sale1.xml");
				if (sal.getStatus() == 0)
				{
					for (int i = 0; i < sal.getSales().length; i++)
					{
						System.out.println(sal.getSales()[i].getP26T43());
						com.is.tf.sale.SaleService.create(sal.getSales()[i], current.getP1t1(), id_contract);
					}
					
				}
				else
				{
					alert("ERROR:" + sal.getErrorMsg() + ":  Status=" + sal.getStatus() + ": GtkId=" + sal.getGtkId() + ": BankINN=" + ((String) session.getAttribute("BankINN")));
					ISLogger.getLogger().warn("ERROR onSelect$sale:" + sal.getErrorMsg() + ":  Status=" + sal.getStatus() + ": GtkId=" + sal.getGtkId());
				}
				
				sale_include.setSrc("tfSale.zul?idn=" + current.getP1t1() + "&cont_type=" + current.getP2t1() + "&idc=" + current.getId() + "&subj=" + current.getP17t1() + "&summa1=" + current.getP20t1() + "&summa2=" + current.getP21t1() + "&val1="
						+ current.getP18t1() + "&val2=" + current.getP19t1() + "&ht=" + desktopHeight);
			}
		}
		catch (Exception e)
		{
			// System.out.println("contract_idn.getValue()  "+current.getId()+" p1t1  "+current.getP1t1());
			e.printStackTrace();
			ISLogger.getLogger().warn(CheckNull.getPstr(e));
			alert("ERROR:in tab sale " + (CheckNull.isEmpty(e.getMessage()) ? CheckNull.getPstr(e) : e.getMessage()));
			
		}
		finally
		{
			// System.out.println("onSelect$garant  - id_contract  "+current.getId()+" cont_idn=  "+current.getP1t1()+"&val1="+current.getP18t1()+"&val2="+current.getP19t1());
		}
	}
	
	public void onSelect$refundimp_tab(Event evt)
	{
		try
		{
			if (itype == 1)
			{
				refundimp_include.setSrc("tfRefundimp.zul?idn=" + current.getP1t1() + "&idc=" + current.getId() + "&cont_type=" + current.getP2t1() + "&summa1=" + current.getP20t1() + "&summa2=" + current.getP21t1() + "&subj=" + current.getP17t1()
						+ "&val1=" + current.getP18t1() + "&spr=" + sparam + "&val2=" + current.getP19t1() + "&val22=" + current.getP22t1() + "&val23=" + current.getP23t1() + "&ht=" + desktopHeight);
			}
			else
			{
				final BankServiceProxy ws = new BankServiceProxy((String) session.getAttribute("YESVO_URL"));
				long id_contract = current.getId();
				com.is.tf.refundimp.RefundimpService.remove(new Refundimp(), id_contract);
				com.sbs.service.RefundsImpResult refi = ws.getRefundsImp(((String) (session.getAttribute("BankINN"))), current.getP1t1());
				XMLSerializer.write(refi, "c:/refi1.xml");
				
				if (refi.getStatus() == 0)
				{
					for (int i = 0; i < refi.getRefundsImp().length; i++)
					{
						com.is.tf.refundimp.RefundimpService.create(refi.getRefundsImp()[i], current.getP1t1(), id_contract);
					}
				}
				else
				{
					alert("ERROR:" + refi.getErrorMsg() + ":  Status=" + refi.getStatus() + ": GtkId=" + refi.getGtkId() + ": BankINN=" + ((String) session.getAttribute("BankINN")));
					ISLogger.getLogger().warn("ERROR onSelect$accred:" + refi.getErrorMsg() + ":  Status=" + refi.getStatus() + ": GtkId=" + refi.getGtkId());
				}
				
				refundimp_include.setSrc("tfRefundimp.zul?idn=" + current.getP1t1() + "&idc=" + current.getId() + "&summa1=" + current.getP20t1() + "&summa2=" + current.getP21t1() + "&cont_type=" + current.getP2t1() + "&subj=" + current.getP17t1()
						+ "&val1=" + current.getP18t1() + "&spr=" + sparam + "&val2=" + current.getP19t1() + "&val22=" + current.getP22t1() + "&val23=" + current.getP23t1() + "&ht=" + desktopHeight);
			}
		}
		catch (Exception e)
		{
			// System.out.println("contract_idn.getValue()  "+current.getId()+" p1t1  "+current.getP1t1());
			e.printStackTrace();
		}
		finally
		{
			// System.out.println("onSelect$garant  - id_contract  "+current.getId()+" cont_idn=  "+current.getP1t1()+"&val1="+current.getP18t1()+"&val2="+current.getP19t1());
		}
	}
	
	public void onSelect$transfer_tab(Event evt)
	{
		try
		{
			if (itype == 1)
			{
				transfer.setSrc("tfTransfer.zul?idn=" + current.getP1t1() + "&idc=" + current.getId() + "&cont_type=" + current.getP2t1() + "&summa1=" + current.getP20t1() + "&summa2=" + current.getP21t1() + "&subj=" + current.getP17t1() + "&val1="
						+ current.getP18t1() + "&val2=" + current.getP19t1() + "&val22=" + current.getP22t1() + "&val23=" + current.getP23t1() + "&ht=" + desktopHeight);
			}
			else
			{
				final BankServiceProxy ws = new BankServiceProxy((String) session.getAttribute("YESVO_URL"));
				long id_contract = current.getId();
				String idn = current.getP1t1();
				ContractResult cr = ws.getContract(((String) session.getAttribute("BankINN")), idn);
				
				com.sbs.service.Transfer[] transf = cr.getContract().getTransfers();
				XMLSerializer.write(transf, "c:/transfer.xml");
				if (transf != null)
				{
					for (int i = 0; i < transf.length; i++)
					{
						TransferService.remove(new Transfer(), id_contract);
						Res res = TransferService.create(transf[i], idn, id_contract);
						if (res.getCode() == 0)
						{
							alert("Успешно загружен Перевод контракта!");
						}
						else
						{
							alert("Ошибка:" + res.getName());
						}
					}
				}
				
				transfer.setSrc("tfTransfer.zul?idn=" + current.getP1t1() + "&idc=" + current.getId() + "&summa1=" + current.getP20t1() + "&summa2=" + current.getP21t1() + "&cont_type=" + current.getP2t1() + "&subj=" + current.getP17t1() + "&val1="
						+ current.getP18t1() + "&val2=" + current.getP19t1() + "&val22=" + current.getP22t1() + "&val23=" + current.getP23t1() + "&ht=" + desktopHeight);
			}
			
		}
		catch (Exception e)
		{
			// System.out.println("contract_idn.getValue()  "+current.getId()+" p1t1  "+current.getP1t1());
			e.printStackTrace();
			ISLogger.getLogger().warn(CheckNull.getPstr(e));
			alert("ERROR:in tab Transfer " + (CheckNull.isEmpty(e.getMessage()) ? CheckNull.getPstr(e) : e.getMessage()));
		}
		finally
		{
			// System.out.println("onSelect$garant  - id_contract  "+current.getId()+" cont_idn=  "+current.getP1t1()+"&val1="+current.getP18t1()+"&val2="+current.getP19t1());
		}
	}
	
	public void onSelect$act_tab(Event evt)
	{
		try
		{
			String link = "tfAct.zul?idn=" + current.getP1t1() + "&idc=" + current.getId() + "&cont_type="
					+ current.getP2t1() + "&summa1=" + current.getP20t1() + "&summa2=" + current.getP21t1() + "&subj="
					+ current.getP17t1() + "&spr=" + sparam + "&val1=" + current.getP18t1() + "&val2=" + current.getP19t1() + "&val22="
					+ current.getP22t1() + "&val23=" + current.getP23t1() + "&ht=" + desktopHeight;
			if (itype == 1)
			{
				act.setSrc(link);
			}
			else
			{
				final BankServiceProxy ws = new BankServiceProxy((String) session.getAttribute("YESVO_URL"));
				long id_contract = current.getId();
				String idn = current.getP1t1();
				ContractResult cr = ws.getContract(((String) session.getAttribute("BankINN")), idn);
				
				com.sbs.service.Act[] act_n = cr.getContract().getActs();
				XMLSerializer.write(act_n, "c:/act.xml");
				if (act_n != null)
				{
					for (int i = 0; i < act_n.length; i++)
					{
						ActService.remove(id_contract);
						Res res = ActService.create(act_n[i], id_contract);
						if (res.getCode() == 0) alert("Ошибка:" + res.getName());
					}
				}
				else
				{
					alert("Data not found, BankINN=" + ((String) session.getAttribute("BankINN")));
					ISLogger.getLogger().warn("ERROR onSelect$Act: Data not found");
				}
				
				act.setSrc(link);
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			ISLogger.getLogger().warn(CheckNull.getPstr(e));
			alert("ERROR:in tab Act " + (CheckNull.isEmpty(e.getMessage()) ? CheckNull.getPstr(e) : e.getMessage()));
		}
		finally
		{
			// System.out.println("onSelect$garant  - id_contract  "+current.getId()+" cont_idn=  "+current.getP1t1()+"&val1="+current.getP18t1()+"&val2="+current.getP19t1());
		}
	}
	
	public void onSelect$calcform_tab(Event evt)
	{
		try
		{
			String link = "tfCalcform.zul?idn=" + current.getP1t1() + "&idc=" + current.getId() + "&cont_type="
					+ current.getP2t1() + "&spr=" + sparam + "&summa1=" + current.getP20t1() + "&summa2=" + current.getP21t1() + "&subj="
					+ current.getP17t1() + "&val1=" + current.getP18t1() + "&val2=" + current.getP19t1() + "&val22="
					+ current.getP22t1() + "&val23=" + current.getP23t1() + "&ht=" + desktopHeight;
			if (itype == 1)
			{
				calcform.setSrc(link);
			}
			else
			{
				final BankServiceProxy ws = new BankServiceProxy((String) session.getAttribute("YESVO_URL"));
				long id_contract = current.getId();
				String idn = current.getP1t1();
				ContractResult cr = ws.getContract(((String) session.getAttribute("BankINN")), idn);
				
				com.sbs.service.CalcForm[] agre = cr.getContract().getCalcForms();
				XMLSerializer.write(agre, "c:/Calcform.xml");
				if (agre != null)
				{
					for (int i = 0; i < agre.length; i++)
					{
						CalcformService.remove(id_contract);
						
						Res res = CalcformService.create(agre[i], id_contract);
						if (res.getCode() == 1)
						{
							alert("Успешно загружено!");
						}
						else
						{
							alert("Ошибка:" + res.getName());
						}
					}
				}
				else
				{
					alert("Data not found, BankINN=" + ((String) session.getAttribute("BankINN")));
					ISLogger.getLogger().warn("ERROR onSelect$Calcform: Data not found");
				}
				calcform.setSrc(link);
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			ISLogger.getLogger().warn(CheckNull.getPstr(e));
			alert("ERROR:in tab Calcform " + (CheckNull.isEmpty(e.getMessage()) ? CheckNull.getPstr(e) : e.getMessage()));
		}
		finally
		{
			// System.out.println("onSelect$garant  - id_contract  "+current.getId()+" cont_idn=  "+current.getP1t1()+"&val1="+current.getP18t1()+"&val2="+current.getP19t1());
		}
	}
	
	public void onSelect$barterform_tab(Event evt)
	{
		try
		{
			String link = "tfBarterform.zul?idn=" + current.getP1t1() + "&idc=" + current.getId() + "&cont_type="
					+ current.getP2t1() + "&spr=" + sparam + "&summa1=" + current.getP20t1() + "&summa2=" + current.getP21t1() + "&subj="
					+ current.getP17t1() + "&val1=" + current.getP18t1() + "&val2=" + current.getP19t1() + "&val22="
					+ current.getP22t1() + "&val23=" + current.getP23t1() + "&ht=" + desktopHeight;
			if (itype == 1)
			{
				barterform.setSrc(link);
			}
			else
			{
				final BankServiceProxy ws = new BankServiceProxy((String) session.getAttribute("YESVO_URL"));
				long id_contract = current.getId();
				String idn = current.getP1t1();
				ContractResult cr = ws.getContract(((String) session.getAttribute("BankINN")), idn);
				
				com.sbs.service.BarterForm[] agre = cr.getContract().getBarterForms();
				XMLSerializer.write(agre, "c:/Barterform.xml");
				if (agre != null)
				{
					for (int i = 0; i < agre.length; i++)
					{
						BarterformService.remove(id_contract);
						
						Res res = BarterformService.create(agre[i], id_contract);
						if (res.getCode() == 1)
						{
							alert("Успешно загружено!");
						}
						else
						{
							alert("Ошибка:" + res.getName());
						}
					}
				}
				else
				{
					alert("Data not found, BankINN=" + ((String) session.getAttribute("BankINN")));
					ISLogger.getLogger().warn("ERROR onSelect$Barterform: Data not found");
				}
				barterform.setSrc(link);
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			ISLogger.getLogger().warn(CheckNull.getPstr(e));
			alert("ERROR:in tab Barterform " + (CheckNull.isEmpty(e.getMessage()) ? CheckNull.getPstr(e) : e.getMessage()));
		}
		finally
		{
			// System.out.println("onSelect$garant  - id_contract  "+current.getId()+" cont_idn=  "+current.getP1t1()+"&val1="+current.getP18t1()+"&val2="+current.getP19t1());
		}
	}
	
	public void onSelect$agreement_tab(Event evt)
	{
		try
		{
			String link = "tfAgreement.zul?idn=" + current.getP1t1() + "&idc=" + current.getId() + "&cont_type="
					+ current.getP2t1() + "&spr=" + sparam + "&summa1=" + current.getP20t1() + "&summa2=" + current.getP21t1() + "&subj="
					+ current.getP17t1() + "&val1=" + current.getP18t1() + "&val2=" + current.getP19t1() + "&val22="
					+ current.getP22t1() + "&val23=" + current.getP23t1() + "&ht=" + desktopHeight;
			if (itype == 1)
			{
				agreement.setSrc(link);
			}
			else
			{
				final BankServiceProxy ws = new BankServiceProxy((String) session.getAttribute("YESVO_URL"));
				long id_contract = current.getId();
				String idn = current.getP1t1();
				ContractResult cr = ws.getContract(((String) session.getAttribute("BankINN")), idn);
				
				com.sbs.service.Agreement[] agre = cr.getContract().getAgreements();
				XMLSerializer.write(agre, "c:/Agreement.xml");
				if (agre != null)
				{
					for (int i = 0; i < agre.length; i++)
					{
						AgreementService.remove(id_contract);
						
						Res res = AgreementService.create(agre[i], id_contract);
						if (res.getCode() == 1)
						{
							alert("Успешно загружено!");
						}
						else
						{
							alert("Ошибка:" + res.getName());
							
						}
					}
				}
				else
				{
					alert("Data not found, BankINN=" + ((String) session.getAttribute("BankINN")));
					ISLogger.getLogger().warn("ERROR onSelect$Agreement: Data not found");
				}
				
				agreement.setSrc(link);
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			ISLogger.getLogger().warn(CheckNull.getPstr(e));
			alert("ERROR:in tab Agreement " + (CheckNull.isEmpty(e.getMessage()) ? CheckNull.getPstr(e) : e.getMessage()));
		}
		finally
		{
			// System.out.println("onSelect$garant  - id_contract  "+current.getId()+" cont_idn=  "+current.getP1t1()+"&val1="+current.getP18t1()+"&val2="+current.getP19t1());
		}
	}
	
	public void onSelect$declaration_tab(Event evt)
	{
		try
		{
			String link = "tfDeclaration.zul?idn=" + current.getP1t1() + "&idc=" + current.getId() + "&cont_type="
					+ current.getP2t1() + "&spr=" + sparam + "&summa1=" + current.getP20t1() + "&summa2=" + current.getP21t1() + "&subj="
					+ current.getP17t1() + "&val1=" + current.getP18t1() + "&val2=" + current.getP19t1() + "&val22="
					+ current.getP22t1() + "&val23=" + current.getP23t1() + "&ht=" + desktopHeight;
			if (itype == 1)
			{
				declaration.setSrc(link);
			}
			else
			{
				final BankServiceProxy ws = new BankServiceProxy((String) session.getAttribute("YESVO_URL"));
				long id_contract = current.getId();
				String idn = current.getP1t1();
				ContractResult cr = ws.getContract(((String) session.getAttribute("BankINN")), idn);
				
				com.sbs.service.Declaration[] agre = cr.getContract().getDeclarations();
				XMLSerializer.write(agre, "c:/Declaration.xml");
				if (agre != null)
				{
					for (int i = 0; i < agre.length; i++)
					{
						DeclarationService.remove(id_contract);
						
						Res res = DeclarationService.create(agre[i], id_contract);
						if (res.getCode() == 1)
						{
							alert("Успешно загружено!");
						}
						else
						{
							alert("Ошибка:" + res.getName());
							
						}
					}
				}
				else
				{
					alert("Data not found, BankINN=" + ((String) session.getAttribute("BankINN")));
					ISLogger.getLogger().warn("ERROR onSelect$Declaration: Data not found");
				}
				
				declaration.setSrc(link);
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			ISLogger.getLogger().warn(CheckNull.getPstr(e));
			alert("ERROR:in tab Declaration " + (CheckNull.isEmpty(e.getMessage()) ? CheckNull.getPstr(e) : e.getMessage()));
		}
		finally
		{
			// System.out.println("onSelect$garant  - id_contract  "+current.getId()+" cont_idn=  "+current.getP1t1()+"&val1="+current.getP18t1()+"&val2="+current.getP19t1());
		}
	}
	
	public void onSelect$delegate_tab(Event evt)
	{
		try
		{
			String link = "tfDelegate.zul?idn=" + current.getP1t1() + "&idc=" + current.getId() + "&cont_type="
					+ current.getP2t1() + "&spr=" + sparam + "&summa1=" + current.getP20t1() + "&summa2=" + current.getP21t1() + "&subj="
					+ current.getP17t1() + "&val1=" + current.getP18t1() + "&val2=" + current.getP19t1() + "&val22="
					+ current.getP22t1() + "&val23=" + current.getP23t1() + "&ht=" + desktopHeight;
			if (itype == 1)
			{
				delegate.setSrc(link);
			}
			else
			{
				final BankServiceProxy ws = new BankServiceProxy((String) session.getAttribute("YESVO_URL"));
				long id_contract = current.getId();
				String idn = current.getP1t1();
				ContractResult cr = ws.getContract(((String) session.getAttribute("BankINN")), idn);
				
				com.sbs.service.Delegate[] agre = cr.getContract().getDelegates();
				XMLSerializer.write(agre, "c:/Delegate.xml");
				if (agre != null)
				{
					for (int i = 0; i < agre.length; i++)
					{
						DelegateService.remove(id_contract);
						
						Res res = DelegateService.create(agre[i], id_contract);
						if (res.getCode() == 1)
						{
							alert("Успешно загружено!");
						}
						else
						{
							alert("Ошибка:" + res.getName());
							
						}
					}
				}
				else
				{
					alert("Data not found, BankINN=" + ((String) session.getAttribute("BankINN")));
					ISLogger.getLogger().warn("ERROR onSelect$Delegate: Data not found");
				}
				
				delegate.setSrc(link);
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			ISLogger.getLogger().warn(CheckNull.getPstr(e));
			alert("ERROR:in tab Delegate " + (CheckNull.isEmpty(e.getMessage()) ? CheckNull.getPstr(e) : e.getMessage()));
		}
		finally
		{
			// System.out.println("onSelect$garant  - id_contract  "+current.getId()+" cont_idn=  "+current.getP1t1()+"&val1="+current.getP18t1()+"&val2="+current.getP19t1());
		}
	}
	
	// Проверить
	public void onSelect$endoperation_tab(Event evt)
	{
		try
		{
			String link = "tfEndoperation.zul?idn=" + current.getP1t1() + "&idc=" + current.getId() + "&cont_type="
					+ current.getP2t1() + "&spr=" + sparam + "&summa1=" + current.getP20t1() + "&summa2=" + current.getP21t1() + "&subj="
					+ current.getP17t1() + "&val1=" + current.getP18t1() + "&val2=" + current.getP19t1() + "&val22="
					+ current.getP22t1() + "&val23=" + current.getP23t1() + "&ht=" + desktopHeight;
			if (itype == 1)
			{
				endoperation.setSrc(link);
			}
			else
			{
				final BankServiceProxy ws = new BankServiceProxy((String) session.getAttribute("YESVO_URL"));
				long id_contract = current.getId();
				String idn = current.getP1t1();
				ContractResult cr = ws.getContract(((String) session.getAttribute("BankINN")), idn);
				
				com.sbs.service.EndOperation agre = cr.getContract().getEndOperation();
				XMLSerializer.write(agre, "c:/Endoperation.xml");
				if (agre != null)
				{
					EndoperationService.remove(id_contract);
					Res res = EndoperationService.create(agre, id_contract);
					if (res.getCode() == 1)
					{
						alert("Успешно загружено!");
					}
					else
					{
						alert("Ошибка:" + res.getName());
						
					}
				}
				else
				{
					alert("Data not found, BankINN=" + ((String) session.getAttribute("BankINN")));
					ISLogger.getLogger().warn("ERROR onSelect$Endoperation: Data not found");
				}
				
				endoperation.setSrc(link);
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			ISLogger.getLogger().warn(CheckNull.getPstr(e));
			alert("ERROR:in tab Endoperation " + (CheckNull.isEmpty(e.getMessage()) ? CheckNull.getPstr(e) : e.getMessage()));
		}
		finally
		{
			// System.out.println("onSelect$garant  - id_contract  "+current.getId()+" cont_idn=  "+current.getP1t1()+"&val1="+current.getP18t1()+"&val2="+current.getP19t1());
		}
	}
	
	public void onSelect$expcondition_tab(Event evt)
	{
		try
		{
			String link = "tfExpcondition.zul?idn=" + current.getP1t1() + "&idc=" + current.getId() + "&cont_type="
					+ current.getP2t1() + "&spr=" + sparam + "&summa1=" + current.getP20t1() + "&summa2=" + current.getP21t1() + "&subj="
					+ current.getP17t1() + "&val1=" + current.getP18t1() + "&val2=" + current.getP19t1() + "&val22="
					+ current.getP22t1() + "&val23=" + current.getP23t1() + "&ht=" + desktopHeight;
			if (itype == 1)
			{
				expcondition.setSrc(link);
			}
			else
			{
				final BankServiceProxy ws = new BankServiceProxy((String) session.getAttribute("YESVO_URL"));
				long id_contract = current.getId();
				String idn = current.getP1t1();
				ContractResult cr = ws.getContract(((String) session.getAttribute("BankINN")), idn);
				
				com.sbs.service.ExpCondition[] agre = cr.getContract().getExpConditions();
				XMLSerializer.write(agre, "c:/Expcondition.xml");
				if (agre != null)
				{
					for (int i = 0; i < agre.length; i++)
					{
						ExpconditionService.remove(id_contract);
						
						Res res = ExpconditionService.create(agre[i], id_contract);
						if (res.getCode() == 1)
						{
							alert("Успешно загружено!");
						}
						else
						{
							alert("Ошибка:" + res.getName());
							
						}
					}
				}
				else
				{
					alert("Data not found, BankINN=" + ((String) session.getAttribute("BankINN")));
					ISLogger.getLogger().warn("ERROR onSelect$Expcondition: Data not found");
				}
				
				expcondition.setSrc(link);
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			ISLogger.getLogger().warn(CheckNull.getPstr(e));
			alert("ERROR:in tab Expcondition " + (CheckNull.isEmpty(e.getMessage()) ? CheckNull.getPstr(e) : e.getMessage()));
		}
		finally
		{
			// System.out.println("onSelect$garant  - id_contract  "+current.getId()+" cont_idn=  "+current.getP1t1()+"&val1="+current.getP18t1()+"&val2="+current.getP19t1());
		}
	}
	
	// Проверить
	public void onSelect$goods_tab(Event evt)
	{
		try
		{
			String link = "tfGoods.zul?idn=" + current.getP1t1() + "&idc=" + current.getId() + "&cont_type="
					+ current.getP2t1() + "&spr=" + sparam + "&summa1=" + current.getP20t1() + "&summa2=" + current.getP21t1() + "&subj="
					+ current.getP17t1() + "&val1=" + current.getP18t1() + "&val2=" + current.getP19t1() + "&val22="
					+ current.getP22t1() + "&val23=" + current.getP23t1() + "&ht=" + desktopHeight;
			if (itype == 1)
			{
				goods.setSrc(link);
			}
			else
			{
				final BankServiceProxy ws = new BankServiceProxy((String) session.getAttribute("YESVO_URL"));
				long id_contract = current.getId();
				String idn = current.getP1t1();
				ContractResult cr = ws.getContract(((String) session.getAttribute("BankINN")), idn);
				
				com.sbs.service.Specification[] spec = cr.getContract().getSpecifications();
				if (spec != null)
				{
					for (int ii = 0; ii < spec.length; ii++)
					{
						com.sbs.service.Goods[] agre = spec[ii].getGoods();
						XMLSerializer.write(agre, "c:/Goods.xml");
						if (agre != null)
						{
							for (int i = 0; i < agre.length; i++)
							{
								GoodsService.remove(id_contract);
								Res res = GoodsService.create(agre[i], id_contract);
								if (res.getCode() == 1)
								{
									alert("Успешно загружено!");
								}
								else
								{
									alert("Ошибка:" + res.getName());
									
								}
							}
						}
						else
						{
							alert("Data not found, BankINN=" + ((String) session.getAttribute("BankINN")));
							ISLogger.getLogger().warn("ERROR onSelect$Goods: Data not found");
						}
					}
				}
				
				goods.setSrc(link);
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			ISLogger.getLogger().warn(CheckNull.getPstr(e));
			alert("ERROR:in tab Goods " + (CheckNull.isEmpty(e.getMessage()) ? CheckNull.getPstr(e) : e.getMessage()));
		}
		finally
		{
			// System.out.println("onSelect$garant  - id_contract  "+current.getId()+" cont_idn=  "+current.getP1t1()+"&val1="+current.getP18t1()+"&val2="+current.getP19t1());
		}
	}
	
	public void onSelect$incoterms_tab(Event evt)
	{
		try
		{
			String link = "tfIncoterms.zul?idn=" + current.getP1t1() + "&idc=" + current.getId() + "&cont_type="
					+ current.getP2t1() + "&spr=" + sparam + "&summa1=" + current.getP20t1() + "&summa2=" + current.getP21t1() + "&subj="
					+ current.getP17t1() + "&val1=" + current.getP18t1() + "&val2=" + current.getP19t1() + "&val22="
					+ current.getP22t1() + "&val23=" + current.getP23t1() + "&ht=" + desktopHeight;
			if (itype == 1)
			{
				incoterms.setSrc(link);
			}
			else
			{
				final BankServiceProxy ws = new BankServiceProxy((String) session.getAttribute("YESVO_URL"));
				long id_contract = current.getId();
				String idn = current.getP1t1();
				ContractResult cr = ws.getContract(((String) session.getAttribute("BankINN")), idn);
				
				com.sbs.service.Incoterms[] agre = cr.getContract().getIncotermses();
				XMLSerializer.write(agre, "c:/Incoterms.xml");
				if (agre != null)
				{
					for (int i = 0; i < agre.length; i++)
					{
						IncotermsService.remove(id_contract);
						
						Res res = IncotermsService.create(agre[i], id_contract);
						if (res.getCode() == 1)
						{
							alert("Успешно загружено!");
						}
						else
						{
							alert("Ошибка:" + res.getName());
							
						}
					}
				}
				else
				{
					alert("Data not found, BankINN=" + ((String) session.getAttribute("BankINN")));
					ISLogger.getLogger().warn("ERROR onSelect$Incoterms: Data not found");
				}
				
				incoterms.setSrc(link);
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			ISLogger.getLogger().warn(CheckNull.getPstr(e));
			alert("ERROR:in tab Incoterms " + (CheckNull.isEmpty(e.getMessage()) ? CheckNull.getPstr(e) : e.getMessage()));
		}
		finally
		{
			// System.out.println("onSelect$garant  - id_contract  "+current.getId()+" cont_idn=  "+current.getP1t1()+"&val1="+current.getP18t1()+"&val2="+current.getP19t1());
		}
	}
	
	public void onSelect$sender_tab(Event evt)
	{
		try
		{
			String link = "tfSender.zul?idn=" + current.getP1t1() + "&idc=" + current.getId() + "&cont_type="
					+ current.getP2t1() + "&spr=" + sparam + "&summa1=" + current.getP20t1() + "&summa2=" + current.getP21t1() + "&subj="
					+ current.getP17t1() + "&val1=" + current.getP18t1() + "&val2=" + current.getP19t1() + "&val22="
					+ current.getP22t1() + "&val23=" + current.getP23t1() + "&ht=" + desktopHeight;
			if (itype == 1)
			{
				sender.setSrc(link);
			}
			else
			{
				final BankServiceProxy ws = new BankServiceProxy((String) session.getAttribute("YESVO_URL"));
				long id_contract = current.getId();
				String idn = current.getP1t1();
				ContractResult cr = ws.getContract(((String) session.getAttribute("BankINN")), idn);
				
				com.sbs.service.Sender[] agre = cr.getContract().getSenders();
				XMLSerializer.write(agre, "c:/tfSender.xml");
				if (agre != null)
				{
					for (int i = 0; i < agre.length; i++)
					{
						SenderService.remove(id_contract);
						
						Res res = SenderService.create(agre[i], id_contract);
						if (res.getCode() == 1)
						{
							alert("Успешно загружено!");
						}
						else
						{
							alert("Ошибка:" + res.getName());
							
						}
					}
				}
				else
				{
					alert("Data not found, BankINN=" + ((String) session.getAttribute("BankINN")));
					ISLogger.getLogger().warn("ERROR onSelect$tfSender: Data not found");
				}
				
				sender.setSrc(link);
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			ISLogger.getLogger().warn(CheckNull.getPstr(e));
			alert("ERROR:in tab tfSender " + (CheckNull.isEmpty(e.getMessage()) ? CheckNull.getPstr(e) : e.getMessage()));
		}
		finally
		{
			// System.out.println("onSelect$garant  - id_contract  "+current.getId()+" cont_idn=  "+current.getP1t1()+"&val1="+current.getP18t1()+"&val2="+current.getP19t1());
		}
	}
	
	public void onSelect$shipment_tab(Event evt)
	{
		try
		{
			String link = "tfShipment.zul?idn=" + current.getP1t1() + "&idc=" + current.getId() + "&cont_type="
					+ current.getP2t1() + "&spr=" + sparam + "&summa1=" + current.getP20t1() + "&summa2=" + current.getP21t1() + "&subj="
					+ current.getP17t1() + "&val1=" + current.getP18t1() + "&val2=" + current.getP19t1() + "&val22="
					+ current.getP22t1() + "&val23=" + current.getP23t1() + "&ht=" + desktopHeight;
			if (itype == 1)
			{
				shipment.setSrc(link);
			}
			else
			{
				final BankServiceProxy ws = new BankServiceProxy((String) session.getAttribute("YESVO_URL"));
				long id_contract = current.getId();
				String idn = current.getP1t1();
				ContractResult cr = ws.getContract(((String) session.getAttribute("BankINN")), idn);
				
				com.sbs.service.Shipment[] agre = cr.getContract().getShipments();
				XMLSerializer.write(agre, "c:/Shipment.xml");
				if (agre != null)
				{
					for (int i = 0; i < agre.length; i++)
					{
						ShipmentService.remove(id_contract);
						
						Res res = ShipmentService.create(agre[i], id_contract);
						if (res.getCode() == 1)
						{
							alert("Успешно загружено!");
						}
						else
						{
							alert("Ошибка:" + res.getName());
							
						}
					}
				}
				else
				{
					alert("Data not found, BankINN=" + ((String) session.getAttribute("BankINN")));
					ISLogger.getLogger().warn("ERROR onSelect$Shipment: Data not found");
				}
				
				shipment.setSrc(link);
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			ISLogger.getLogger().warn(CheckNull.getPstr(e));
			alert("ERROR:in tab Shipment " + (CheckNull.isEmpty(e.getMessage()) ? CheckNull.getPstr(e) : e.getMessage()));
		}
		finally
		{
			// System.out.println("onSelect$garant  - id_contract  "+current.getId()+" cont_idn=  "+current.getP1t1()+"&val1="+current.getP18t1()+"&val2="+current.getP19t1());
		}
	}
	
	public void onSelect$specification_tab(Event evt)
	{
		try
		{
			String link = "tfSpecification.zul?idn=" + current.getP1t1() + "&idc=" + current.getId() + "&cont_type="
					+ current.getP2t1() + "&spr=" + sparam + "&summa1=" + current.getP20t1() + "&summa2=" + current.getP21t1() + "&subj="
					+ current.getP17t1() + "&val1=" + current.getP18t1() + "&val2=" + current.getP19t1() + "&val22="
					+ current.getP22t1() + "&val23=" + current.getP23t1() + "&ht=" + desktopHeight;
			if (itype == 1)
			{
				specification.setSrc(link);
			}
			else
			{
				final BankServiceProxy ws = new BankServiceProxy((String) session.getAttribute("YESVO_URL"));
				long id_contract = current.getId();
				String idn = current.getP1t1();
				ContractResult cr = ws.getContract(((String) session.getAttribute("BankINN")), idn);
				
				com.sbs.service.Specification[] agre = cr.getContract().getSpecifications();
				XMLSerializer.write(agre, "c:/Specification.xml");
				if (agre != null)
				{
					for (int i = 0; i < agre.length; i++)
					{
						SpecificationService.remove(id_contract);
						
						Res res = SpecificationService.create(agre[i], current.getP1t1(), id_contract);
						if (res.getCode() == 1)
						{
							alert("Успешно загружено!");
						}
						else
						{
							alert("Ошибка:" + res.getName());
							
						}
					}
				}
				else
				{
					alert("Data not found, BankINN=" + ((String) session.getAttribute("BankINN")));
					ISLogger.getLogger().warn("ERROR onSelect$Specification: Data not found");
				}
				
				specification.setSrc(link);
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			ISLogger.getLogger().warn(CheckNull.getPstr(e));
			alert("ERROR:in tab Specification " + (CheckNull.isEmpty(e.getMessage()) ? CheckNull.getPstr(e) : e.getMessage()));
		}
		finally
		{
			// System.out.println("onSelect$garant  - id_contract  "+current.getId()+" cont_idn=  "+current.getP1t1()+"&val1="+current.getP18t1()+"&val2="+current.getP19t1());
		}
	}
	
	public void onSelect$tolling_tab(Event evt)
	{
		try
		{
			String link = "tfTolling.zul?idn=" + current.getP1t1() + "&idc=" + current.getId() + "&cont_type="
					+ current.getP2t1() + "&spr=" + sparam + "&summa1=" + current.getP20t1() + "&summa2=" + current.getP21t1() + "&subj="
					+ current.getP17t1() + "&val1=" + current.getP18t1() + "&val2=" + current.getP19t1() + "&val22="
					+ current.getP22t1() + "&val23=" + current.getP23t1() + "&ht=" + desktopHeight;
			if (itype == 1)
			{
				tolling.setSrc(link);
			}
			else
			{
				final BankServiceProxy ws = new BankServiceProxy((String) session.getAttribute("YESVO_URL"));
				long id_contract = current.getId();
				String idn = current.getP1t1();
				ContractResult cr = ws.getContract(((String) session.getAttribute("BankINN")), idn);
				
				com.sbs.service.Tolling[] agre = cr.getContract().getTollings();
				XMLSerializer.write(agre, "c:/Tolling.xml");
				if (agre != null)
				{
					for (int i = 0; i < agre.length; i++)
					{
						TollingService.remove(id_contract);
						
						Res res = TollingService.create(agre[i], id_contract);
						if (res.getCode() == 1)
						{
							alert("Успешно загружено!");
						}
						else
						{
							alert("Ошибка:" + res.getName());
							
						}
					}
				}
				else
				{
					alert("Data not found, BankINN=" + ((String) session.getAttribute("BankINN")));
					ISLogger.getLogger().warn("ERROR onSelect$Tolling: Data not found");
				}
				
				tolling.setSrc(link);
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			ISLogger.getLogger().warn(CheckNull.getPstr(e));
			alert("ERROR:in tab Tolling " + (CheckNull.isEmpty(e.getMessage()) ? CheckNull.getPstr(e) : e.getMessage()));
		}
		finally
		{
			// System.out.println("onSelect$garant  - id_contract  "+current.getId()+" cont_idn=  "+current.getP1t1()+"&val1="+current.getP18t1()+"&val2="+current.getP19t1());
		}
	}
	
	// Проверить
	public void onSelect$transcost_tab(Event evt)
	{
		try
		{
			String link = "tfTranscost.zul?idn=" + current.getP1t1() + "&idc=" + current.getId() + "&cont_type="
					+ current.getP2t1() + "&spr=" + sparam + "&summa1=" + current.getP20t1() + "&summa2=" + current.getP21t1() + "&subj="
					+ current.getP17t1() + "&val1=" + current.getP18t1() + "&val2=" + current.getP19t1() + "&val22="
					+ current.getP22t1() + "&val23=" + current.getP23t1() + "&ht=" + desktopHeight;
			if (itype == 1)
			{
				transcost.setSrc(link);
			}
			else
			{
				final BankServiceProxy ws = new BankServiceProxy((String) session.getAttribute("YESVO_URL"));
				long id_contract = current.getId();
				String idn = current.getP1t1();
				ContractResult cr = ws.getContract(((String) session.getAttribute("BankINN")), idn);
				
				com.sbs.service.Specification[] spec = cr.getContract().getSpecifications();
				if (spec != null)
				{
					for (int ii = 0; ii < spec.length; ii++)
					{
						com.sbs.service.TransCost agre = spec[ii].getTransCosts();
						XMLSerializer.write(agre, "c:/Goods.xml");
						if (agre != null)
						{
							TranscostService.remove(id_contract);
							Res res = TranscostService.create(agre, id_contract);
							if (res.getCode() == 1)
							{
								alert("Успешно загружено!");
							}
							else
							{
								alert("Ошибка:" + res.getName());
								
							}
							
						}
						else
						{
							alert("Data not found, BankINN=" + ((String) session.getAttribute("BankINN")));
							ISLogger.getLogger().warn("ERROR onSelect$Goods: Data not found");
						}
					}
				}
				
				transcost.setSrc(link);
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			ISLogger.getLogger().warn(CheckNull.getPstr(e));
			alert("ERROR:in tab Transcost " + (CheckNull.isEmpty(e.getMessage()) ? CheckNull.getPstr(e) : e.getMessage()));
		}
		finally
		{
			// System.out.println("onSelect$garant  - id_contract  "+current.getId()+" cont_idn=  "+current.getP1t1()+"&val1="+current.getP18t1()+"&val2="+current.getP19t1());
		}
	}
	
	// Проверить
	public void onSelect$work_tab(Event evt)
	{
		try
		{
			String link = "tfWork.zul?idn=" + current.getP1t1() + "&idc=" +
					current.getId() + "&cont_type=" + current.getP2t1() + "&spr=" +
					sparam + "&summa1=" + current.getP20t1() + "&summa2=" +
					current.getP21t1() + "&subj=" + current.getP17t1() + "&val1=" +
					current.getP18t1() + "&val2=" + current.getP19t1() + "&val22=" +
					current.getP22t1() + "&val23=" + current.getP23t1() + "&ht=" +
					desktopHeight;
			if (itype == 1)
			{
				work.setSrc(link);
			}
			else
			{
				final BankServiceProxy ws = new BankServiceProxy((String) session.getAttribute("YESVO_URL"));
				long id_contract = current.getId();
				String idn = current.getP1t1();
				ContractResult cr = ws.getContract(((String) session.getAttribute("BankINN")), idn);
				
				com.sbs.service.Specification[] spec = cr.getContract().getSpecifications();
				if (spec != null)
				{
					for (int ii = 0; ii < spec.length; ii++)
					{
						com.sbs.service.Work agre = spec[ii].getWorks(ii);
						XMLSerializer.write(agre, "c:/Work.xml");
						if (agre != null)
						{
							WorkService.remove(id_contract);
							Res res = WorkService.create(agre, id_contract);
							if (res.getCode() == 1)
							{
								alert("Успешно загружено!");
							}
							else
							{
								alert("Ошибка:" + res.getName());
								
							}
							
						}
						else
						{
							alert("Data not found, BankINN=" + ((String) session.getAttribute("BankINN")));
							ISLogger.getLogger().warn("ERROR onSelect$Work: Data not found");
						}
					}
				}
				work.setSrc(link);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			ISLogger.getLogger().warn(CheckNull.getPstr(e));
			alert("ERROR:in tab Work " + (CheckNull.isEmpty(e.getMessage()) ? CheckNull.getPstr(e) : e.getMessage()));
		}
		finally
		{ //
			System.out.println("onSelect$garant  - id_contract  " + current.getId() + " cont_idn=  " + current.getP1t1() + "&val1=" + current.getP18t1() + "&val2=" + current.getP19t1());
		}
		 
	}
	
	public void onClick$detail()
	{
		if (grd.isVisible())
		{
			frm.setVisible(true);
			
			grd.setVisible(false);
			frmgrd.setVisible(true);
		}
		// btn_inf.setImage("/images/statics.png");
		// btn_inf.setLabel(Labels.getLabel("homeinf"));
		
		// btn_back.setImage("/images/folder.png");
		// btn_back.setLabel(Labels.getLabel("back"));
	}
	
	public void onClick$btn_refresh()
	{
		refreshModel(_startPageNumber);
	}
	
	public void onClick$btn_inf()
	{
		if (frm.isVisible())
		{
			frm.setVisible(false);
			frmgrd.setVisible(false);
			
			grd.setVisible(true);
			// btn_inf.setImage("/images/statics.png");
			// btn_inf.setLabel(Labels.getLabel("homeinf"));
			// btn_back.setImage("/images/folder.png");
			// btn_back.setLabel(Labels.getLabel("back"));
		}
		else onDoubleClick$frm();
	}
	
	public void onClick$btn_back()
	{
		if (frm.isVisible())
		{
			frm.setVisible(false);
			frmgrd.setVisible(false);
			addgrd.setVisible(false);
			fgrddiv.setVisible(false);
			
			grd.setVisible(true);
			// btn_inf.setImage("/images/statics.png");
			// btn_inf.setLabel(Labels.getLabel("homeinf"));
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
	}
	
	public void onClick$btn_add()
	{
		onDoubleClick$inf();
		frmgrd.setVisible(false);
		addgrd.setVisible(true);
		fgrddiv.setVisible(false);
	}
	
	public void onClick$btn_search()
	{
		onDoubleClick$inf();
		frmgrd.setVisible(false);
		addgrd.setVisible(false);
		fgrddiv.setVisible(true);
	}
	
	public void onClick$btn_save()
	{
		try
		{
			if (addgrd.isVisible())
			{
				ContractService.create(new Contract(

				Long.parseLong(aid.getValue()),
						ap0t1.getValue(),
						ap1t1.getValue(),
						ap2t1.getValue(),
						ap3t1.getValue(),
						ap4t1.getValue(),
						ap5t1.getValue(),
						ap6t1.getValue(),
						ap7t1.getValue(),
						ap8t1.getValue(),
						ap9t1.getValue(),
						ap10t1.getValue(),
						ap11t1.getValue(),
						ap12t1.getValue(),
						ap13t1.getValue(),
						ap14t1.getValue(),
						ap15t1.getValue(),
						ap16t1.getValue(),
						ap17t1.getValue(),
						ap18t1.getValue(),
						ap19t1.getValue(),
						ap20t1.doubleValue(),
						ap21t1.doubleValue(),
						ap22t1.getValue(),
						ap23t1.getValue(),
						ap24t1.getValue(),
						ap25t1.getValue(),
						ap26t1.getValue(),
						ap27t1.getValue(),
						ap28t1.getValue(),
						ap29t1.getValue(),
						ap30t1.getValue(),
						ap31t1.getValue(),
						ap32t1.getValue(),
						ap33t1.getValue(),
						ap34t1.getValue(),
						ap35t1.getValue(),
						ap36t1.getValue(),
						ap37t1.getValue(),
						ap38t1.getValue(),
						ap39t1.getValue(),
						ap40t1.getValue(),
						ap41t1.getValue(),
						ap42t1.getValue(),
						ap43t1.getValue(),
						ap44t1.getValue(),
						ap45t1.getValue(),
						ap46t1.getValue(),
						ap47t1.getValue(),
						ap48t1.getValue(),
						ap49t1.getValue(),
						ap50t1.getValue(),
						ap51t1.getValue(),
						ap52t1.getValue(),
						ap53t1.getValue(),
						ap54t1.getValue(),
						ap55t1.getValue(),
						ap56t1.getValue(),
						ap57t1.getValue(),
						ap58t1.getValue(),
						ap59t1.getValue(),
						ap60t1.getValue(),
						ap61t1.getValue(),
						ap62t1.getValue(),
						ap65t1.doubleValue(),
						ap66t1.doubleValue(),
						ap67t1.doubleValue(),
						ap68t1.doubleValue(),
						ap69t1.doubleValue(),
						ap70t1.doubleValue(),
						ap71t1.doubleValue(),
						ap72t1.doubleValue(),
						ap73t1.getValue(),
						ap100t1.getValue()
						));
				CheckNull.clearForm(addgrd);
				frmgrd.setVisible(true);
				addgrd.setVisible(false);
				fgrddiv.setVisible(false);
			}
			else if (fgrddiv.isVisible())
			{
				filter = new ContractFilter();
				if (!CheckNull.isEmpty(fid.getValue()))
				{
					filter.setId(Long.parseLong(fid.getValue()));
				}
				filter.setP0t1(fp0t1.getValue());
				filter.setP1t1(fp1t1.getValue());
				filter.setP2t1(fp2t1.getValue());
				filter.setP3t1(fp3t1.getValue());
				filter.setP4t1(fp4t1.getValue());
				filter.setP5t1(fp5t1.getValue());
				filter.setP6t1(fp6t1.getValue());
				filter.setP7t1(fp7t1.getValue());
				filter.setP8t1(fp8t1.getValue());
				filter.setP9t1(fp9t1.getValue());
				filter.setP10t1(fp10t1.getValue());
				filter.setP11t1(fp11t1.getValue());
				filter.setP12t1(fp12t1.getValue());
				filter.setP13t1(fp13t1.getValue());
				filter.setP14t1(fp14t1.getValue());
				filter.setP15t1(fp15t1.getValue());
				filter.setP16t1(fp16t1.getValue());
				filter.setP17t1(fp17t1.getValue());
				filter.setP18t1(fp18t1.getValue());
				filter.setP19t1(fp19t1.getValue());
				if (!CheckNull.isEmpty(fp20t1.doubleValue()) && fp20t1.doubleValue() > 0)
				{
					filter.setP20t1(fp20t1.doubleValue());
					// System.out.println(fp20t1.doubleValue());
				}
				if (!CheckNull.isEmpty(fp21t1.doubleValue()) && fp20t1.doubleValue() > 0)
				{
					filter.setP21t1(fp21t1.doubleValue());
					// System.out.println(fp21t1.doubleValue());
				}
				filter.setP22t1(fp22t1.getValue());
				filter.setP23t1(fp23t1.getValue());
				filter.setP24t1(fp24t1.getValue());
				filter.setP25t1(fp25t1.getValue());
				filter.setP26t1(fp26t1.getValue());
				filter.setP27t1(fp27t1.getValue());
				filter.setP28t1(fp28t1.getValue());
				filter.setP29t1(fp29t1.getValue());
				filter.setP30t1(fp30t1.getValue());
				filter.setP31t1(fp31t1.getValue());
				filter.setP32t1(fp32t1.getValue());
				filter.setP33t1(fp33t1.getValue());
				filter.setP34t1(fp34t1.getValue());
				filter.setP35t1(fp35t1.getValue());
				filter.setP36t1(fp36t1.getValue());
				filter.setP37t1(fp37t1.getValue());
				filter.setP38t1(fp38t1.getValue());
				filter.setP39t1(fp39t1.getValue());
				filter.setP40t1(fp40t1.getValue());
				filter.setP41t1(fp41t1.getValue());
				filter.setP42t1(fp42t1.getValue());
				filter.setP43t1(fp43t1.getValue());
				filter.setP44t1(fp44t1.getValue());
				filter.setP45t1(fp45t1.getValue());
				filter.setP46t1(fp46t1.getValue());
				filter.setP47t1(fp47t1.getValue());
				filter.setP48t1(fp48t1.getValue());
				filter.setP49t1(fp49t1.getValue());
				filter.setP50t1(fp50t1.getValue());
				filter.setP51t1(fp51t1.getValue());
				filter.setP52t1(fp52t1.getValue());
				filter.setP53t1(fp53t1.getValue());
				filter.setP54t1(fp54t1.getValue());
				filter.setP55t1(fp55t1.getValue());
				filter.setP56t1(fp56t1.getValue());
				filter.setP57t1(fp57t1.getValue());
				filter.setP58t1(fp58t1.getValue());
				filter.setP59t1(fp59t1.getValue());
				filter.setP60t1(fp60t1.getValue());
				filter.setP61t1(fp61t1.getValue());
				filter.setP62t1(fp62t1.getValue());
				/*
				 * filter.setP65t1(fp65t1.doubleValue());
				 * filter.setP66t1(fp66t1.doubleValue());
				 * filter.setP67t1(fp67t1.doubleValue());
				 * filter.setP68t1(fp68t1.doubleValue());
				 * filter.setP69t1(fp69t1.doubleValue());
				 * filter.setP70t1(fp70t1.doubleValue());
				 * filter.setP71t1(fp71t1.doubleValue());
				 * filter.setP72t1(fp72t1.doubleValue());
				 * filter.setP73t1(fp73t1.getValue());
				 * filter.setP100t1(fp100t1.getValue());
				 */

			}
			else
			{
				
				Long.parseLong(id.getValue());
				current.setP0t1(p0t1.getValue());
				current.setP1t1(p1t1.getValue());
				current.setP2t1(p2t1.getValue());
				current.setP3t1(p3t1.getValue());
				current.setP4t1(p4t1.getValue());
				current.setP5t1(p5t1.getValue());
				current.setP6t1(p6t1.getValue());
				current.setP7t1(p7t1.getValue());
				current.setP8t1(p8t1.getValue());
				current.setP9t1(p9t1.getValue());
				current.setP10t1(p10t1.getValue());
				current.setP11t1(p11t1.getValue());
				current.setP12t1(p12t1.getValue());
				current.setP13t1(p13t1.getValue());
				current.setP14t1(p14t1.getValue());
				current.setP15t1(p15t1.getValue());
				current.setP16t1(p16t1.getValue());
				current.setP17t1(p17t1.getValue());
				current.setP18t1(p18t1.getValue());
				current.setP19t1(p19t1.getValue());
				current.setP20t1(p20t1.doubleValue());
				current.setP21t1(p21t1.doubleValue());
				current.setP22t1(p22t1.getValue());
				current.setP23t1(p23t1.getValue());
				current.setP24t1(p24t1.getValue());
				current.setP25t1(p25t1.getValue());
				current.setP26t1(p26t1.getValue());
				current.setP27t1(p27t1.getValue());
				current.setP28t1(p28t1.getValue());
				current.setP29t1(p29t1.getValue());
				current.setP30t1(p30t1.getValue());
				current.setP31t1(p31t1.getValue());
				current.setP32t1(p32t1.getValue());
				current.setP33t1(p33t1.getValue());
				current.setP34t1(p34t1.getValue());
				current.setP35t1(p35t1.getValue());
				current.setP36t1(p36t1.getValue());
				current.setP37t1(p37t1.getValue());
				current.setP38t1(p38t1.getValue());
				current.setP39t1(p39t1.getValue());
				current.setP40t1(p40t1.getValue());
				current.setP41t1(p41t1.getValue());
				current.setP42t1(p42t1.getValue());
				current.setP43t1(p43t1.getValue());
				current.setP44t1(p44t1.getValue());
				current.setP45t1(p45t1.getValue());
				current.setP46t1(p46t1.getValue());
				current.setP47t1(p47t1.getValue());
				current.setP48t1(p48t1.getValue());
				current.setP49t1(p49t1.getValue());
				current.setP50t1(p50t1.getValue());
				current.setP51t1(p51t1.getValue());
				current.setP52t1(p52t1.getValue());
				current.setP53t1(p53t1.getValue());
				current.setP54t1(p54t1.getValue());
				current.setP55t1(p55t1.getValue());
				current.setP56t1(p56t1.getValue());
				current.setP57t1(p57t1.getValue());
				current.setP58t1(p58t1.getValue());
				current.setP59t1(p59t1.getValue());
				current.setP60t1(p60t1.getValue());
				current.setP61t1(p61t1.getValue());
				current.setP62t1(p62t1.getValue());
				current.setP65t1(p65t1.doubleValue());
				current.setP66t1(p66t1.doubleValue());
				current.setP67t1(p67t1.doubleValue());
				current.setP68t1(p68t1.doubleValue());
				current.setP69t1(p69t1.doubleValue());
				current.setP70t1(p70t1.doubleValue());
				current.setP71t1(p71t1.doubleValue());
				current.setP72t1(p72t1.doubleValue());
				current.setP73t1(p73t1.getValue());
				current.setP100t1(p100t1.getValue());
				ContractService.update(current);
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
		if (fgrddiv.isVisible())
		{
			filter = new ContractFilter();
		}
		onClick$btn_back();
		frmgrd.setVisible(true);
		addgrd.setVisible(false);
		
		fgrddiv.setVisible(false);
		CheckNull.clearForm(addgrd);
		CheckNull.clearForm(fgrd);
		refreshModel(_startPageNumber);
		
		onClick$btn_inf();
		grd.setVisible(true);
		frmgrd.setVisible(false);
		
		addgrd.setVisible(false);
		fgrddiv.setVisible(false);
		CheckNull.clearForm(addgrd);
		CheckNull.clearForm(fgrd);
		refreshModel(_startPageNumber);
	}
	
	public void onClick$btn_dic()
	{
		DicService.getDic();
		alert("Обновление справочников прошло успешно!");
	}
	
	/*
	 * public void onCheck$grup1() { grup2.setChecked(false);
	 * grup3.setChecked(false); } public void onCheck$grup2() {
	 * grup1.setChecked(false); grup3.setChecked(false); } public void
	 * onCheck$grup3() { if (grup3.isChecked()) { grup2.setChecked(false);
	 * grup1.setChecked(false); } }
	 */
	public void onClick$btn_get()
	{
		// alert("test");
		final BankServiceProxy ws = new BankServiceProxy((String) session.getAttribute("YESVO_URL"));
		
		try
		{
			/*
			 * if (grup1.isChecked()) { active=0; } else if (grup2.isChecked())
			 * { active=1; } else if (grup3.isChecked()) { active=2; }
			 */
			// alert("YESVO_URL"+(String)session.getAttribute("YESVO_URL"));
			if (contract_idn.getValue().isEmpty())
			{
				alert("Поле не должно быть пустым");
				
			}
			else
			{
				
				// alert("test3");
				ContractResult cr = ws.getContract(((String) session.getAttribute("BankINN")), contract_idn.getValue());
				
				// /System.out.println(cr.getContract().getAccreditives().toString());
				// alert("BankINN="+(String)(session.getAttribute("BankINN"))+"cr.getContractStatus()=   "+cr.getStatus()+"  Error="+cr.getErrorMsg());
				ISLogger.getLogger().warn("cr.getContractStatus()=   " + cr.getStatus() + "    YESVO_URL=" + (String) session.getAttribute("YESVO_URL") + "   BankINN=" + (String) (session.getAttribute("BankINN")));
				long id_contract = com.is.tf.contract.ContractService.create(cr.getContract(), "0");
				
				filter.setId(id_contract);
				_startPageNumber = 0;
				contractPaging.setActivePage(0);
				refreshModel(_startPageNumber);
				
				// alert("BankINN"+(String)(session.getAttribute("BankINN"))+"  id_contract="+id_contract);
				ISLogger.getLogger().warn("id_contract=" + id_contract);
				// System.out.println("id_contract=");
				// System.out.println("id_contract="+id_contract);
				// alert(cr.getContract().getAccreditives()[1].toString());
				
				// 24 //----------работает-----Транспортные расходы к
				// товару--------------------------------
				
				System.out.println("getTransfers()==" + cr.getContract().getTransfers());
				
				/*
				 * if
				 * (!CheckNull.isEmpty(cr.getContract().getTransfers().length)){
				 * for (int i=0;i<cr.getContract().getTransfers().length;i++){
				 * //com.sbs.service.TransferResult trc = ws.getTransfer("nci",
				 * "987", contract_idn.getValue(),
				 * cr.getContract().getTransfers(i));
				 * System.out.println(cr.getContract
				 * ().getTransfers()[i].getP10T29());
				 * //com.is.tf.transfer.TransferService
				 * .create(cr.getContract().getTransfers()[i],
				 * contract_idn.getValue(), id_contract) ;} } } //1
				 * //-------------Аккредитив
				 * работает+----------------------------------------
				 * //com.sbs.service.AccreditivesResult
				 * acres=ws.getAccreditives("nci", "987",
				 * contract_idn.getValue()) ; com.sbs.service.AccreditivesResult
				 * acr = ws.getAccreditives("nci", "987",
				 * contract_idn.getValue()); for (int
				 * i=0;i<acr.getAccreditives().length;i++){
				 * System.out.println(acr.getAccreditives()[i].getP2T21());
				 * com.is
				 * .tf.Accreditiv.AccreditivService.create2(acr.getAccreditives
				 * ()[i],contract_idn.getValue(), id_contract); } //16
				 * //------------- работает+-----Гарантия иностранного банка к
				 * контракту---------------------------------
				 * //com.sbs.service.GarantResult gar = ws.getGarant("nci",
				 * "987", contract_idn.getValue(),
				 * cr.getContract().getGarants(i));
				 * com.sbs.service.GarantsResult gar = ws.getGarants("nci",
				 * "987", contract_idn.getValue()); for (int
				 * i=0;i<gar.getGarants().length;i++){
				 * System.out.println(gar.getGarants()[i].getP3T18());
				 * com.is.tf.garant.GarantService.create(gar.getGarants()[i],
				 * contract_idn.getValue(), id_contract ) ;} //2
				 * //-------------Полис
				 * работает+---------------------------------------- for (int
				 * i=0;i<cr.getContract().getPolicies().length;i++){
				 * com.sbs.service.PoliciesResult pol = ws.getPolicies("nci",
				 * "987", contract_idn.getValue());
				 * System.out.println(pol.getPolicies()[i].getP3T32());
				 * com.is.tf
				 * .policy.PolicyService.create(pol.getPolicies()[i],contract_idn
				 * .getValue(), id_contract); } //11 //-------------Дебет
				 * работает+---------------------------------------- for (int
				 * i=0;i<cr.getContract().getDebets().length;i++){
				 * com.sbs.service.DebetsResult debt = ws.getDebets("nci",
				 * "987", contract_idn.getValue());
				 * System.out.println(debt.getDebets()[i].getP13T24());
				 * com.is.tf
				 * .debet.DebetService.create(debt.getDebets()[i],contract_idn
				 * .getValue(),id_contract ); } //10 //-------------Кредит
				 * работает+---------------------------------------- for (int
				 * i=0;i<cr.getContract().getCredits().length;i++){
				 * com.sbs.service.CreditsResult cre = ws.getCredits("nci",
				 * "987", contract_idn.getValue());
				 * System.out.println(cre.getCredits()[i].getP13T25());
				 * com.is.tf
				 * .credit.CreditService.create(cre.getCredits()[i],contract_idn
				 * .getValue(), id_contract); } //30
				 * //----------работает+-----Налог на доход
				 * нерезидента-------------------------------- for (int
				 * i=0;i<cr.getContract().getTaxes().length;i++){
				 * com.sbs.service.TaxesResult tax = ws.getTaxes("nci", "987",
				 * contract_idn.getValue());
				 * System.out.println("Tax  "+tax.getTaxes()[i].getP11T39());
				 * com.is.tf.tax.TaxService.create(tax.getTaxes()[i],
				 * contract_idn.getValue(), id_contract) ; } //7
				 * //-------------Commision
				 * работает+---------------------------------------- for (int
				 * i=0;i<cr.getContract().getCommissions().length;i++){
				 * com.sbs.service.CommissionsResult comm =
				 * ws.getCommissions("nci", "987", contract_idn.getValue());
				 * System
				 * .out.println("Comm  "+comm.getCommissions()[i].getP2T27());
				 * com
				 * .is.tf.Commission.CommissionService.create(comm.getCommissions
				 * ()[i],contract_idn.getValue(), id_contract);} //8
				 * //-------------CommisionGNK
				 * работает---------------------------------------- for (int
				 * i=0;i<cr.getContract().getCommissionsGNK().length;i++){
				 * com.sbs.service.CommissionsGNKResult comg =
				 * ws.getCommissionsGNK("nci", "987", contract_idn.getValue());
				 * //System.out.println(comg.getCommissionGNK().getP2T28());
				 * com.is.tf.Commissiongnk.CommissiongnkService.create(comg.
				 * getCommissionsGNK()[i],contract_idn.getValue(),
				 * id_contract);} //12 //-------------Дебет инфо
				 * работает+---------------------------------------- for (int
				 * i=0;i<cr.getContract().getDebetsInfo().length;i++){
				 * com.sbs.service.DebetsInfoResult debi =
				 * ws.getDebetsInfo("nci", "987", contract_idn.getValue());
				 * //System.out.println(debi.getDebetsInfo()[i].getP7T31());
				 * com.
				 * is.tf.debetinfo.DebetinfoService.create(debi.getDebetsInfo
				 * ()[i], id_contract,contract_idn.getValue()); } //15
				 * //-------------Поступление средств ЭК БТ
				 * работает+---------------------------------------- for (int
				 * i=0;i<cr.getContract().getFunds().length;i++){
				 * com.sbs.service.FundsResult fund = ws.getFunds("nci", "987",
				 * contract_idn.getValue());
				 * System.out.println(fund.getFunds()[i].getP2T35());
				 * com.is.tf.fund
				 * .FundService.create(fund.getFunds()[i],contract_idn
				 * .getValue(), id_contract);} //25
				 * //------------работает-----Возврат средств
				 * ЭКСп+-------------------------------- for (int
				 * i=0;i<cr.getContract().getRefundsExp().length;i++){
				 * com.sbs.service.RefundsExpResult refe =
				 * ws.getRefundsExp("nci", "987", contract_idn.getValue());
				 * System.out.println(refe.getRefundsExp()[i].getP13T36());
				 * com.is
				 * .tf.refundexp.RefundexpService.create(refe.getRefundsExp
				 * ()[i], contract_idn.getValue(), "", "", id_contract) ;} //27
				 * //----------работает-----Обязательная
				 * продажа+-------------------------------- for (int
				 * i=0;i<cr.getContract().getSales().length;i++){
				 * com.sbs.service.SalesResult sal = ws.getSales("nci", "987",
				 * contract_idn.getValue());
				 * System.out.println(sal.getSales()[i].getP26T43());
				 * com.is.tf.sale.SaleService.create(sal.getSales()[i],
				 * contract_idn.getValue(), id_contract) ;} //32
				 * //-----------не-
				 * работает-----Оплата-------------------------------- for (int
				 * i=0;i<cr.getContract().getPayments().length;i++){
				 * com.sbs.service.PaymentsResult Pay = ws.getPayments("nci",
				 * "987", contract_idn.getValue());
				 * System.out.println(Pay.getPayments()[i].getP23T44());
				 * com.is.tf.payment.PaymentService.create(Pay.getPayments()[i],
				 * contract_idn.getValue(), "", id_contract) ;} try{
				 * com.sbs.service.CalcForm[] calc =
				 * cr.getContract().getCalcForms(); if (calc != null) { for (int
				 * i = 0; i < calc.length; i++) {
				 * //CalcformService.create(calc[i], "1", id_contract);}}
				 * System.out.println("форма расчетов="+calc[i].getP1T16()); } }
				 * } catch (Exception e) { // TODO Auto-generated catch block
				 * e.printStackTrace();
				 * ISLogger.getLogger().warn(CheckNull.getPstr(e));
				 * alert("ERROR: "
				 * +(CheckNull.isEmpty(e.getMessage())?CheckNull.getPstr
				 * (e):e.getMessage())); }
				 */

				/*
				 * //26 //------------работает-----Возврат средств
				 * Imp-------------------------------- for (int
				 * i=0;i<cr.getContract().getRefundsImp().length;i++){
				 * com.sbs.service.RefundsImpResult refi =
				 * ws.getRefundsImp("nci", "987", contract_idn.getValue());
				 * System.out.println(refi.getRefundsImp()[i].getP14T45());
				 * com.is
				 * .tf.refundimp.RefundimpService.create(refi.getRefundsImp
				 * ()[i], contract_idn.getValue(), "", "", id_contract) ;} //9
				 * //-------------Компенсейшн
				 * работает---------------------------------------- for (int
				 * i=0;i<cr.getContract().getCompensations().length;i++){
				 * com.sbs.service.CompensationsResult comg =
				 * ws.getCompensations("nci", "987", contract_idn.getValue());
				 * System.out.println(comg.getCompensations()[i].getP4T42());
				 * com.is.tf.compensation.CompensationService.create(comg.
				 * getCompensations()[i],contract_idn.getValue(), id_contract);}
				 * //23 //------------работает-----Справка о
				 * расчетах-------------------------------- for (int
				 * i=0;i<cr.getContract().getPaymentRefs().length;i++){
				 * com.sbs.service.PaymentsRefResult Pref =
				 * ws.getPaymentsRef("nci", "987", contract_idn.getValue());
				 * System.out.println(Pref.getPaymentsRef()[i].getP2T37());
				 * com.is
				 * .tf.paymentref.PaymentrefService.create(Pref.getPaymentsRef
				 * ()[i], contract_idn.getValue(), id_contract) ;} //3
				 * //-------------CalcForm
				 * работает----------------------------------------
				 * com.sbs.service.CalcForm[] calc =
				 * cr.getContract().getCalcForms(); if (calc != null) { for (int
				 * i = 0; i < calc.length; i++) {
				 * CalcformService.create(calc[i], "1", id_contract);}} //4
				 * //-------------Бартер
				 * работает----------------------------------------
				 * com.sbs.service.BarterForm[] bar =
				 * cr.getContract().getBarterForms(); if (bar != null) { for
				 * (int i = 0; i < bar.length; i++) {
				 * BarterformService.create(bar[i], "1", id_contract );}}
				 */

				/*
				 * //6 //-------------Specification
				 * работает----------------------------------------
				 * com.sbs.service.Specification[] spec =
				 * cr.getContract().getSpecifications(); if (spec != null) { for
				 * (int i = 0; i < spec.length; i++) {
				 * SpecificationService.create(spec[i], "1",
				 * contract_idn.getValue(), id_contract);}} //13
				 * //-------------Делегате
				 * работает---------------------------------------- for (int
				 * s=0;s<cr.getContract().getDelegates().length;s++){
				 * com.sbs.service.DelegateResult del = ws.getDelegate("nci",
				 * "987", contract_idn.getValue(),
				 * cr.getContract().getDelegates(s));
				 * //System.out.println(del.getDelegate().getP20T30());
				 * com.is.delegate.DelegateService.create(del.getDelegate(),
				 * contract_idn.getValue(), id_contract);} //14
				 * //-------------Agreement
				 * работает----------------------------------------
				 * com.sbs.service.Agreement[] agr =
				 * cr.getContract().getAgreements(); if (agr != null) { for (int
				 * i = 0; i < agr.length; i++) { System.out.println(agr [i]);
				 * AgreementService.create(agr[i], contract_idn.getValue(),
				 * id_contract );}} //17 //работает--Поступление процентов по
				 * лизинговому договору» (ЭК) или «Оплата процентов по договору
				 * займа» (ИМ) for (int
				 * i=0;i<cr.getContract().getLeases().length;i++){
				 * com.sbs.service.LeaseResult le = ws.getLease("nci", "987",
				 * contract_idn.getValue(),cr.getContract().getLeases(i) );
				 * System.out.println(le.getLease().getP5T50());
				 * com.is.lease.LeaseService
				 * .create(le.getLease(),contract_idn.getValue(), id_contract)
				 * ;} //18 // работает-------MovefromEx-------------------------
				 * for (int i=0;i<cr.getContract().getMovesFromEx().length;i++){
				 * com.sbs.service.MoveFromExResult mfe =
				 * ws.getMoveFromEx("nci", "987",
				 * contract_idn.getValue(),cr.getContract().getMovesFromEx(i) );
				 * System.out.println(mfe.getMoveFromEx().getP23T52());
				 * com.is.movefromex
				 * .MoveFromExService.create(mfe.getMoveFromEx()
				 * ,contract_idn.getValue(), id_contract) ;} //19 //
				 * работает-------MovefromEx------------------------- for (int
				 * i=0;i<cr.getContract().getMovesFromIm().length;i++){
				 * com.sbs.service.MoveFromImResult mfi =
				 * ws.getMoveFromIm("nci", "987", contract_idn.getValue() ,
				 * cr.getContract().getMovesFromIm(i) );
				 * System.out.println(mfi.getMoveFromIm().getP29T53());
				 * com.is.movefromim
				 * .MovefromimService.create(mfi.getMoveFromIm(),
				 * contract_idn.getValue(),id_contract) ;} //20 //
				 * работает-------MoveToEx------------------------- for (int
				 * i=0;i<cr.getContract().getMovesToEx().length;i++){
				 * com.sbs.service.MoveToExResult mte = ws.getMoveToEx("nci",
				 * "987",
				 * contract_idn.getValue(),cr.getContract().getMovesToEx(i) );
				 * System.out.println(mte.getMoveToEx().getP33T40());
				 * com.is.movetoex
				 * .MovetoexService.create(mte.getMoveToEx(),contract_idn
				 * .getValue(), id_contract) ; } //21 //
				 * работает-------MoveToim------------------------- for (int
				 * i=0;i<cr.getContract().getMovesToIm().length;i++){
				 * com.sbs.service.MoveToImResult mti = ws.getMoveToIm("nci",
				 * "987",
				 * contract_idn.getValue(),cr.getContract().getMovesToIm(i) );
				 * System.out.println(mti.getMoveToIm().getP36T47());
				 * com.is.movetoim
				 * .MovetoimService.create(mti.getMoveToIm(),contract_idn
				 * .getValue(), "0","0","0","0", id_contract) ;} //22
				 * //-------------работает-----Оплата по контракту через биржу»
				 * (ИМ--------------------------------- for (int
				 * i=0;i<cr.getContract().getExchangePayments().length;i++){
				 * com.sbs.service.ExchangePaymentResult exchp =
				 * ws.getExchangePayment("nci", "987",
				 * contract_idn.getValue(),cr
				 * .getContract().getExchangePayments(i) );
				 * //System.out.println(exchp.Ex().getP4T51());
				 * com.is.exchangepayment
				 * .ExchangePaymentService.create(exchp.getExchangePayment
				 * (),contract_idn.getValue(), id_contract) ;} //28
				 * //-------------Sender
				 * работает----------------------------------------
				 * com.sbs.service.Sender[] send =
				 * cr.getContract().getSenders(); if (send != null) { for (int i
				 * = 0; i < send.length; i++) { System.out.println(send [i]);
				 * SenderService.create(send[i], contract_idn.getValue(),
				 * id_contract );}} //29 //-------------Sipment
				 * работает----------------------------------------
				 * com.sbs.service.Shipment[] shpm =
				 * cr.getContract().getShipments(); if (shpm != null) { for (int
				 * i = 0; i < shpm.length; i++) { System.out.println(shpm [i]);
				 * ShipmentService.create(shpm[i], "", id_contract );}} //31
				 * //-------------tolling
				 * работает----------------------------------------
				 * com.sbs.service.Tolling[] toll =
				 * cr.getContract().getTollings(); if (toll != null) { for (int
				 * i = 0; i < toll.length; i++) { System.out.println(toll [i]);
				 * TollingService.create(toll[i], "1", id_contract );}} //33
				 * -------------Deklaratiom не
				 * работает----------------------------------------
				 * com.sbs.service.Declaration[] decl =
				 * cr.getContract().getDeclarations(); if (decl != null) { for
				 * (int i = 0; i < decl.length; i++) { System.out.println(decl
				 * [i]); DeclarationService.create(decl[i], id_contract); }} /*
				 * //34 //-------------EndOperation не
				 * работает----------------------------------------
				 * com.sbs.service.EndOperation[]
				 * eop=cr.getContract().getEndOperation(); if (eop != null) {
				 * for (int i = 0; i < eop.length; i++) { System.out.println(eop
				 * [i]); EndoperationService.create(eop[i],"1", id_contract);}}
				 * //35
				 * //-----------не-работает-----Штраф------------------------
				 * -------- for (int
				 * i=0;i<cr.getContract().getPenaltiesCount().length;i++){
				 * com.sbs.service.PenaltyResult Pnt = ws.getPenalty("nci",
				 * "987", contract_idn.getValue(), getPenalty().getP7T26() );
				 * System.out.println(Pnt.getPenalty().getP7T26());
				 * com.is.penalty.PenaltyService.create(Pnt.getPenalty(),
				 * contract_idn.getValue(), "", id_contract) ;}
				 */}
			
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			ISLogger.getLogger().warn(CheckNull.getPstr(e));
			alert("ERROR: " + (CheckNull.isEmpty(e.getMessage()) ? CheckNull.getPstr(e) : e.getMessage()));
		}
	}
	
	// public void alert(Agreement agreement) {
	// TODO Auto-generated method stub
	
	public void onConfirm(Event evt)
	{
		try
		{
			Confirm c = (Confirm) evt.getData();
			if (c.getConfirm().equalsIgnoreCase("0"))
			{
				alert("Отклонение прошло успешно");
			}
			else
			{
				alert("Подтверждение прошло успешно");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			alert(e.getMessage());
		}
	}
	
	public void onConfirmDocument(Event evt)
	{
		try
		{
			HashMap<String, Object> params = (HashMap<String, Object>) evt.getData();
			Iterator<String> keys = params.keySet().iterator();
			/*
			 * while (keys.hasNext()) { String key = keys.next();
			 * System.out.println(key+" = "+params.get(key)); }
			 */
			String doctype = RefObjDataService.getObjectUID(params.get("obj"), yeisvo_docs);
			System.out.println("doctype = " + doctype);
			
			if (confirmmain == null)
			{
				confirmmain = (Window) Executions.createComponents("tfConfirmwnd.zul", contractmain, null);
				confirmmain.addEventListener("onConfirm", this);
				cvc = (ConfirmwndViewCtrl) confirmmain.getAttribute("confirmmain$composer", false);
			}
			if (cvc == null)
			{
				cvc = (ConfirmwndViewCtrl) confirmmain.getAttribute("confirmmain$composer", false);
			}
			cvc.init(
					(String) params.get("inn"),
					(String) params.get("idn"),
					(String) params.get("action"),
					doctype,
					(String) params.get("docnum"),
					(String) params.get("chdocnum"));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			alert(e.getMessage());
		}
	}
	
	public void onGP(Event evt)
	{
		try
		{
			HashMap<String, Object> params = (HashMap<String, Object>) evt.getData();
			/*
			 * Iterator<String> keys = params.keySet().iterator(); while
			 * (keys.hasNext()) { String key = keys.next();
			 * System.out.println(key+" = "+params.get(key)); }
			 */
			String objtype = RefObjDataService.getObjectUID(params.get("obj"), yeisvo_docs);
			System.out.println("doctype = " + objtype);
			params.put("object_type", objtype);
			
			if (generalpaymentsmain == null)
			{
				generalpaymentsmain = (Window) Executions.createComponents("tfGeneralpaymentswnd.zul", contractmain, null);
				generalpaymentsmain.addEventListener("onAddGP", this);
				gpvc = (GeneralPaymentsViewCtrl) generalpaymentsmain.getAttribute("generalpaymentsmain$composer", false);
			}
			if (gpvc == null)
			{
				gpvc = (GeneralPaymentsViewCtrl) generalpaymentsmain.getAttribute("generalpaymentsmain$composer", false);
			}
			gpvc.init(params);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			alert(e.getMessage());
		}
	}
	
	public void onAddGP(Event evt)
	{
		try
		{
			HashMap<String, Object> params = (HashMap<String, Object>) evt.getData();
			// Tf_general_payment currenttfgp = (Tf_general_payment)
			// evt.getData();
			Window wnd = (Window) execution.getDesktop().getPage("fund").getFellow("fundmain");
			alert("onAddGP!!!");
			Events.sendEvent("onGP", wnd, params);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			alert(e.getMessage());
		}
	}
	
}
