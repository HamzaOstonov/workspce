package com.is.tf.movetoim;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.zkoss.zul.event.PagingEvent;

import com.is.tf.Accreditiv.Accreditiv;
import com.is.tf.contract.ContractService;
import com.is.tf.currency.RefCurrencyBox;
import com.is.tf.currency.RefCurrencyData;
import com.is.tf.payment.Payment;
import com.is.tf.payment.PaymentService;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.RefData;
import com.is.utils.refobj.RefObjCBox;
import com.is.utils.refobj.RefObjData;
import com.sbs.service.BankServiceProxy;
import com.sbs.service.MoveToImResult;

public class MovetoimViewCtrl extends GenericForwardComposer
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
	private Toolbarbutton btn_edit, btn_confirm, btn_reject, btn_delete;
	private Toolbar tb;
	private Label line1, line2, line3, line4, line5, line6, line7, line8, line9;
	private Textbox id, p43t47, p46t47, p47t47, p48t47, p100t47, p1t47, p0t47, p32t47, p33t47, p35t47, p36t47, p39t47, p40t47, p41t47;
	private Textbox ap43t47, ap46t47, ap47t47, ap48t47, ap100t47, aid, ap1t47, ap0t47, ap32t47, ap33t47, ap35t47, ap36t47, ap39t47, ap40t47, ap41t47;
	private Textbox fp43t47, fp46t47, fp47t47, fp48t47, fp100t47, fid, fp1t47, fp0t47, fp12t47, fp13t47, fp14t47, fp15t47, fp16t47, fp17t47, fp18t47, fp29t47, fp30t47, fp31t47, fp32t47, fp33t47, fp35t47, fp36t47, fp39t47, fp40t47, fp41t47;
	private Datebox p49t47, fp49t47, ap49t47, p34t47, p37t47, p42t47, ap34t47, ap37t47, ap42t47, fp34t47, fp37t47, fp42t47;
	private Decimalbox p3t47, p4t47, p5t47, p6t47, p7t47, p8t47, p9t47, p10t47, p20t47, p21t47, p22t47, p23t47, p24t47, p25t47, p26t47, p27t47, p28t47, ap3t47, ap4t47, ap5t47, ap6t47, ap7t47, ap8t47, ap9t47, ap10t47, ap20t47, ap21t47, ap22t47,
			ap23t47, ap24t47, ap25t47, ap26t47, ap27t47, ap28t47, fp3t47, fp4t47, fp5t47, fp6t47, fp7t47, fp8t47, fp9t47, fp10t47, fp20t47, fp21t47, fp22t47, fp23t47, fp24t47, fp25t47, fp26t47, fp27t47, fp28t47;
	private Intbox p38t47, ap38t47, fp38t47;
	private RefCBox ap31t47, p31t47, ap30t47, p30t47, p29t47, ap29t47, p18t47, ap18t47, p16t47, ap16t47, p13t47, p14t47, p15t47, ap13t47, ap14t47, ap15t47, p12t47, ap12t47, p11t47, ap11t47, fp11t47, p19t47, ap19t47, fp19t47;
	private RefObjCBox p17t47, ap17t47, p2t47, ap2t47, fp2t47;
	private RefCurrencyBox p4t471, p4t473, ap4t471, ap4t473, p20t471, p20t473, ap20t471, ap20t473, p22t471, p22t473, ap22t471, ap22t473;
	private Label acbcourse, acbcourse2, cbcourse, cbcourse2, cbcourse3, acbcourse3;
	private Row row_p35t47, row_ap35t47, row_p34t47, row_p33t47, row_p32t47, row_ap34t47, row_ap33t47, row_ap32t47, row_p30t47, row_p31t47, row_ap30t47, row_ap31t47, row_p17t47, row_p18t47, row_ap17t47, row_ap18t47, kur, akur3, kur3, kur2, akur,
			akur2, row_p5t47, row_p6t47, row_p7t47, row_p8t47, row_ap5t47, row_ap6t47, row_ap7t47, row_ap8t47;
	private List<RefObjData> payment = new ArrayList<RefObjData>();
	private List<RefObjData> accreditiv = new ArrayList<RefObjData>();
	private List<RefCurrencyData> coursecurrencies = new ArrayList<RefCurrencyData>();
	private List<RefCurrencyData> coursecurrencies2 = new ArrayList<RefCurrencyData>();
	private List<RefCurrencyData> coursecurrencies3 = new ArrayList<RefCurrencyData>();
	private List<RefData> idn_client = new ArrayList<RefData>();
	private List<RefData> getCurr_22t1_23t1 = new ArrayList<RefData>();
	private List<RefData> kod = new ArrayList<RefData>();
	private List<RefData> reasons = new ArrayList<RefData>();
	private List<RefData> agreement = new ArrayList<RefData>();
	private List<RefData> akod = new ArrayList<RefData>();
	private List<RefData> paymentSS = new ArrayList<RefData>();
	private List<RefData> Fundtype = new ArrayList<RefData>();
	private List<RefData> YesNo = new ArrayList<RefData>();
	private List<RefData> Loantype = new ArrayList<RefData>();
	private List<RefData> istochnik_sredstv = new ArrayList<RefData>();
	private List<RefData> Conditions = new ArrayList<RefData>();
	private List<RefData> Accredetivs = new ArrayList<RefData>();
	private List<RefData> Garants = new ArrayList<RefData>();
	
	private Paging movetoimPaging;
	private int _pageSize = 15;
	private int _startPageNumber = 0;
	private int _totalSize = 0;
	private boolean _needsTotalSizeUpdate = true;
	private String sparam, aAcred_val, Acred_val, inn, aPayment_val, Payment_val, Mfromimp_val, aMfromimp_val, alias, un, idn, subj, val18, val19, val22, val23, strval18, strval2;
	private Long idc;
	private Double P65, P67;
	
	public MovetoimFilter filter = new MovetoimFilter();
	
	PagingListModel model = null;
	ListModelList lmodel = null;
	private AnnotateDataBinder binder;
	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	
	private Movetoim current = new Movetoim();
	
	public MovetoimViewCtrl()
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
			// System.out.println("Garant  cont_idn "+idn);
			
		}
		parameter = (String[]) param.get("val18");
		if (parameter != null)
		{
			val18 = (parameter[0]);
			// System.out.println("Garant  cont_val1 "+val1);
		}
		parameter = (String[]) param.get("val19");
		if (parameter != null)
		{
			val19 = (parameter[0]);
			// System.out.println("Garant  cont_val2 "+val2);
		}
		parameter = (String[]) param.get("val23");
		if (parameter != null)
		{
			val23 = (parameter[0]);
			// System.out.println("Garant  cont_val1 "+val1);
		}
		parameter = (String[]) param.get("val22");
		if (parameter != null)
		{
			val22 = (parameter[0]);
			// System.out.println("Garant  cont_val2 "+val2);
		}
		parameter = (String[]) param.get("subj");
		if (parameter != null)
		{
			subj = (parameter[0]);
			// System.out.println("Garant  cont_val2 "+val2);
		}
		parameter = (String[]) param.get("inn");
		if (parameter != null)
		{
			inn = (parameter[0]);
			// System.out.println("Garant  cont_val2 "+val2);
		}
		parameter = (String[]) param.get("P65");
		if (parameter != null)
		{
			P65 = Double.parseDouble(parameter[0]);
			// System.out.println("Garant  cont_val1 "+val1);
		}
		parameter = (String[]) param.get("P67");
		if (parameter != null)
		{
			P67 = Double.parseDouble(parameter[0]);
			// System.out.println("Garant  cont_val1 "+val1);
		}
		parameter = (String[]) param.get("spr");
		if (parameter != null)
		{
			sparam = (parameter[0]);
		}
		filter.setP1t47(idn);
		payment = ContractService.getPaymentObj(idn, alias);
		paymentSS = ContractService.getPaymentSourse("1,2,3,4,5,6,7", alias);
		istochnik_sredstv = ContractService.getFundSourse("1,2,3", alias);
		Fundtype = ContractService.getFundtype("1,2,3,4", alias);
		reasons = ContractService.getReasons("1,4,5", alias);
		Loantype = ContractService.getLoantype("1,2,3,4,5,6", alias);
		YesNo = ContractService.getYesNo();
		agreement = ContractService.getAgreement(idn, alias);
		
		Conditions = ContractService.getConditions("1,2,7,9,10", alias);
		// Accredetivs =
		// ContractService.getAccredetivs(current.getP11t47(),alias);
		
		idn_client = ContractService.getIDN(inn, alias);
		
		p2t47.setModel(new ListModelList(payment));
		ap2t47.setModel(new ListModelList(payment));
		p11t47.setModel(new ListModelList(idn_client));
		ap11t47.setModel(new ListModelList(idn_client));
		p12t47.setModel(new ListModelList(paymentSS));
		ap12t47.setModel(new ListModelList(paymentSS));
		p13t47.setModel(new ListModelList(Fundtype));
		ap13t47.setModel(new ListModelList(Fundtype));
		p14t47.setModel(new ListModelList(Loantype));
		ap14t47.setModel(new ListModelList(Loantype));
		p15t47.setModel(new ListModelList(YesNo));
		ap15t47.setModel(new ListModelList(YesNo));
		p16t47.setModel(new ListModelList(Conditions));
		ap16t47.setModel(new ListModelList(Conditions));
		p29t47.setModel(new ListModelList(reasons));
		ap29t47.setModel(new ListModelList(reasons));
		p30t47.setModel(new ListModelList(agreement));
		ap30t47.setModel(new ListModelList(agreement));
		// p17t47.setModel(new ListModelList(Accredetivs));
		// ap17t47.setModel(new ListModelList(aAccredetivs));
		line1.setValue(Labels.getLabel("movetoim.p37t47").replaceAll("<br>", "\r\n"));
		line2.setValue(Labels.getLabel("movetoim.p3t47").replaceAll("<br>", "\r\n"));
		line3.setValue(Labels.getLabel("movetoim.val").replaceAll("<br>", "\r\n"));
		line4.setValue(Labels.getLabel("movetoim.p2t47tab").replaceAll("<br>", "\r\n"));
		line5.setValue(Labels.getLabel("movetoim.p11t47tab").replaceAll("<br>", "\r\n"));
		line6.setValue(Labels.getLabel("movetoim.p36t47tab").replaceAll("<br>", "\r\n"));
		line7.setValue(Labels.getLabel("movetoim.p43t47tab").replaceAll("<br>", "\r\n"));
		line8.setValue(Labels.getLabel("movetoim.p49t47tab").replaceAll("<br>", "\r\n"));
		line9.setValue(Labels.getLabel("movetoim.p100t47tab").replaceAll("<br>", "\r\n"));
		
		dataGrid.setItemRenderer(new ListitemRenderer()
		{
			@SuppressWarnings("unchecked")
			public void render(Listitem row, Object data) throws Exception
			{
				Movetoim pMovetoim = (Movetoim) data;
				
				row.setValue(pMovetoim);
				
				row.appendChild(new Listcell(pMovetoim.getId() + ""));
				row.appendChild(new Listcell(pMovetoim.getP1t47()));
				row.appendChild(new Listcell(pMovetoim.getP0t47()));
				row.appendChild(new Listcell(pMovetoim.getP2t47()));
				row.appendChild(new Listcell(pMovetoim.getP3t47() + ""));
				row.appendChild(new Listcell(pMovetoim.getP4t47() + ""));
				row.appendChild(new Listcell(pMovetoim.getP5t47() + ""));
				row.appendChild(new Listcell(pMovetoim.getP6t47() + ""));
				row.appendChild(new Listcell(pMovetoim.getP7t47() + ""));
				row.appendChild(new Listcell(pMovetoim.getP8t47() + ""));
				row.appendChild(new Listcell(pMovetoim.getP9t47() + ""));
				row.appendChild(new Listcell(pMovetoim.getP10t47() + ""));
				row.appendChild(new Listcell(pMovetoim.getP11t47()));
				row.appendChild(new Listcell(pMovetoim.getP12t47()));
				row.appendChild(new Listcell(pMovetoim.getP13t47()));
				row.appendChild(new Listcell(pMovetoim.getP14t47()));
				row.appendChild(new Listcell(pMovetoim.getP15t47()));
				row.appendChild(new Listcell(pMovetoim.getP16t47()));
				row.appendChild(new Listcell(pMovetoim.getP17t47()));
				row.appendChild(new Listcell(pMovetoim.getP18t47()));
				row.appendChild(new Listcell(pMovetoim.getP19t47()));
				row.appendChild(new Listcell(pMovetoim.getP20t47() + ""));
				row.appendChild(new Listcell(pMovetoim.getP21t47() + ""));
				row.appendChild(new Listcell(pMovetoim.getP22t47() + ""));
				row.appendChild(new Listcell(pMovetoim.getP23t47() + ""));
				row.appendChild(new Listcell(pMovetoim.getP24t47() + ""));
				row.appendChild(new Listcell(pMovetoim.getP25t47() + ""));
				row.appendChild(new Listcell(pMovetoim.getP26t47() + ""));
				row.appendChild(new Listcell(pMovetoim.getP27t47() + ""));
				row.appendChild(new Listcell(pMovetoim.getP28t47() + ""));
				row.appendChild(new Listcell(pMovetoim.getP29t47()));
				row.appendChild(new Listcell(pMovetoim.getP30t47()));
				row.appendChild(new Listcell(pMovetoim.getP31t47()));
				row.appendChild(new Listcell(pMovetoim.getP32t47()));
				row.appendChild(new Listcell(pMovetoim.getP33t47()));
				row.appendChild(new Listcell(pMovetoim.getP34t47() + ""));
				row.appendChild(new Listcell(pMovetoim.getP35t47()));
				row.appendChild(new Listcell(pMovetoim.getP36t47()));
				row.appendChild(new Listcell(pMovetoim.getP37t47() + ""));
				row.appendChild(new Listcell(pMovetoim.getP38t47() + ""));
				row.appendChild(new Listcell(pMovetoim.getP39t47()));
				row.appendChild(new Listcell(pMovetoim.getP40t47()));
				row.appendChild(new Listcell(pMovetoim.getP41t47()));
				row.appendChild(new Listcell(pMovetoim.getP42t47() + ""));
				row.appendChild(new Listcell(pMovetoim.getP43t47()));
				row.appendChild(new Listcell(pMovetoim.getP46t47()));
				row.appendChild(new Listcell(pMovetoim.getP47t47()));
				row.appendChild(new Listcell(pMovetoim.getP48t47()));
				row.appendChild(new Listcell(pMovetoim.getP49t47() + ""));
				row.appendChild(new Listcell(pMovetoim.getP100t47()));
				
			}
		});
		
		refreshModel(_startPageNumber);
		
	}
	
	public void onPaging$movetoimPaging(ForwardEvent event)
	{
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModel(_startPageNumber);
	}
	
	private void refreshModel(int activePage)
	{
		movetoimPaging.setPageSize(_pageSize);
		model = new PagingListModel(activePage, _pageSize, filter, "");
		
		if (_needsTotalSizeUpdate)
		{
			_totalSize = model.getTotalSize(filter, "");
			// _needsTotalSizeUpdate = false;
		}
		
		movetoimPaging.setTotalSize(_totalSize);
		
		dataGrid.setModel((ListModel) model);
		if (model.getSize() > 0)
		{
			this.current = (Movetoim) model.getElementAt(0);
			sendSelEvt();
		}
	}
	
	// Omitted...
	public Movetoim getCurrent()
	{
		return current;
	}
	
	public void setCurrent(Movetoim current)
	{
		this.current = current;
	}
	
	public void onDoubleClick$dataGrid$grd()
	{
		if (sparam != null)
		{
			if (sparam.equals("Filial")) // / Филиал
			
			{
				btn_edit.setVisible(true);
				btn_confirm.setVisible(false);
				btn_reject.setVisible(false);
				sparam = "Filial";
				// alert(sparam1);
			}
			else if (sparam.equals("Go")) // / ГО
			{
				btn_edit.setVisible(false);
				btn_confirm.setVisible(true);
				btn_reject.setVisible(true);
				sparam = "Go";
				// alert(sparam1);
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
			com.sbs.service.MoveToImResult mti = new com.sbs.service.MoveToImResult();
			
			if (addgrd.isVisible())
			{
				
				MoveToImResult ar = ws.saveMoveToIm(
							((String) (session.getAttribute("BankINN")))
						, idn
						, getMti(new Movetoim(
								// Long.parseLong(aid.getValue()),
								// ap1t47.getValue(),
								// ap0t47.getValue(),
								ap2t47.getValue(),
								ap3t47.doubleValue(),
								ap4t47.doubleValue(),
								ap5t47.doubleValue(),
								ap6t47.doubleValue(),
								ap7t47.doubleValue(),
								ap8t47.doubleValue(),
								ap9t47.doubleValue(),
								ap10t47.doubleValue(),
								ap11t47.getValue(),
								ap12t47.getValue(),
								ap13t47.getValue(),
								ap14t47.getValue(),
								ap15t47.getValue(),
								ap16t47.getValue(),
								ap17t47.getValue(),
								ap18t47.getValue(),
								ap19t47.getValue(),
								ap20t47.doubleValue(),
								ap21t47.doubleValue(),
								ap22t47.doubleValue(),
								ap23t47.doubleValue(),
								ap24t47.doubleValue(),
								ap25t47.doubleValue(),
								ap26t47.doubleValue(),
								ap27t47.doubleValue(),
								ap28t47.doubleValue(),
								ap29t47.getValue(),
								ap30t47.getValue(),
								ap31t47.getValue(),
								ap32t47.getValue(),
								ap33t47.getValue(),
								ap34t47.getValue(),
								ap35t47.getValue(),
								ap36t47.getValue(),
								ap37t47.getValue(),
								ap38t47.getValue(),
								ap39t47.getValue(),
								ap40t47.getValue(),
								ap41t47.getValue(),
								ap42t47.getValue(),
								ap43t47.getValue(),
								ap46t47.getValue(),
								ap47t47.getValue(),
								ap48t47.getValue(),
								ap49t47.getValue(),
								ap100t47.getValue()
						)));
				CheckNull.clearForm(addgrd);
				if (ar.getStatus() == 0)
				{
					refreshModel(_startPageNumber);
					alert("Сохранение успешно");
					
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
				filter = new MovetoimFilter();
				
				Long.parseLong(fid.getValue());
				filter.setP1t47(fp1t47.getValue());
				filter.setP0t47(fp0t47.getValue());
				filter.setP2t47(fp2t47.getValue());
				filter.setP3t47(fp3t47.doubleValue());
				filter.setP4t47(fp4t47.doubleValue());
				filter.setP5t47(fp5t47.doubleValue());
				filter.setP6t47(fp6t47.doubleValue());
				filter.setP7t47(fp7t47.doubleValue());
				filter.setP8t47(fp8t47.doubleValue());
				filter.setP9t47(fp9t47.doubleValue());
				filter.setP10t47(fp10t47.doubleValue());
				filter.setP11t47(fp11t47.getValue());
				filter.setP12t47(fp12t47.getValue());
				filter.setP13t47(fp13t47.getValue());
				filter.setP14t47(fp14t47.getValue());
				filter.setP15t47(fp15t47.getValue());
				filter.setP16t47(fp16t47.getValue());
				filter.setP17t47(fp17t47.getValue());
				filter.setP18t47(fp18t47.getValue());
				filter.setP19t47(fp19t47.getValue());
				filter.setP20t47(fp20t47.doubleValue());
				filter.setP21t47(fp21t47.doubleValue());
				filter.setP22t47(fp22t47.doubleValue());
				filter.setP23t47(fp23t47.doubleValue());
				filter.setP24t47(fp24t47.doubleValue());
				filter.setP25t47(fp25t47.doubleValue());
				filter.setP26t47(fp26t47.doubleValue());
				filter.setP27t47(fp27t47.doubleValue());
				filter.setP28t47(fp28t47.doubleValue());
				filter.setP29t47(fp29t47.getValue());
				filter.setP30t47(fp30t47.getValue());
				filter.setP31t47(fp31t47.getValue());
				filter.setP32t47(fp32t47.getValue());
				filter.setP33t47(fp33t47.getValue());
				filter.setP34t47(fp34t47.getValue());
				filter.setP35t47(fp35t47.getValue());
				filter.setP36t47(fp36t47.getValue());
				filter.setP37t47(fp37t47.getValue());
				filter.setP38t47(fp38t47.getValue());
				filter.setP39t47(fp39t47.getValue());
				filter.setP40t47(fp40t47.getValue());
				filter.setP41t47(fp41t47.getValue());
				filter.setP42t47(fp42t47.getValue());
				filter.setP43t47(fp43t47.getValue());
				filter.setP46t47(fp46t47.getValue());
				filter.setP47t47(fp47t47.getValue());
				filter.setP48t47(fp48t47.getValue());
				filter.setP49t47(fp49t47.getValue());
				filter.setP100t47(fp100t47.getValue());
				
			}
			else
			{
				
				Long.parseLong(id.getValue());
				current.setP1t47(p1t47.getValue());
				current.setP0t47(p0t47.getValue());
				current.setP2t47(p2t47.getValue());
				current.setP3t47(p3t47.doubleValue());
				current.setP4t47(p4t47.doubleValue());
				current.setP5t47(p5t47.doubleValue());
				current.setP6t47(p6t47.doubleValue());
				current.setP7t47(p7t47.doubleValue());
				current.setP8t47(p8t47.doubleValue());
				current.setP9t47(p9t47.doubleValue());
				current.setP10t47(p10t47.doubleValue());
				current.setP11t47(p11t47.getValue());
				current.setP12t47(p12t47.getValue());
				current.setP13t47(p13t47.getValue());
				current.setP14t47(p14t47.getValue());
				current.setP15t47(p15t47.getValue());
				current.setP16t47(p16t47.getValue());
				current.setP17t47(p17t47.getValue());
				current.setP18t47(p18t47.getValue());
				current.setP19t47(p19t47.getValue());
				current.setP20t47(p20t47.doubleValue());
				current.setP21t47(p21t47.doubleValue());
				current.setP22t47(p22t47.doubleValue());
				current.setP23t47(p23t47.doubleValue());
				current.setP24t47(p24t47.doubleValue());
				current.setP25t47(p25t47.doubleValue());
				current.setP26t47(p26t47.doubleValue());
				current.setP27t47(p27t47.doubleValue());
				current.setP28t47(p28t47.doubleValue());
				current.setP29t47(p29t47.getValue());
				current.setP30t47(p30t47.getValue());
				current.setP31t47(p31t47.getValue());
				current.setP32t47(p32t47.getValue());
				current.setP33t47(p33t47.getValue());
				current.setP34t47(p34t47.getValue());
				current.setP35t47(p35t47.getValue());
				current.setP36t47(p36t47.getValue());
				current.setP37t47(p37t47.getValue());
				current.setP38t47(p38t47.getValue());
				current.setP39t47(p39t47.getValue());
				current.setP40t47(p40t47.getValue());
				current.setP41t47(p41t47.getValue());
				current.setP42t47(p42t47.getValue());
				current.setP43t47(p43t47.getValue());
				current.setP46t47(p46t47.getValue());
				current.setP47t47(p47t47.getValue());
				current.setP48t47(p48t47.getValue());
				current.setP49t47(p49t47.getValue());
				current.setP100t47(p100t47.getValue());
				// MovetoimService.update(current);
				MoveToImResult ar = ws.saveMoveToIm(((String) (session.getAttribute("BankINN"))), idn, getMti(current));
				if (ar.getStatus() == 0)
				{
					refreshModel(_startPageNumber);
					alert("Сохранение успешно");
					
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
			filter = new MovetoimFilter();
		}
		onClick$btn_back();
		frmgrd.setVisible(true);
		addgrd.setVisible(false);
		fgrd.setVisible(false);
		CheckNull.clearForm(addgrd);
		CheckNull.clearForm(fgrd);
		refreshModel(_startPageNumber);
	}
	
	private com.sbs.service.MoveToIm getMti(Movetoim acr)
	{
		java.util.Calendar cal = java.util.Calendar.getInstance();
		com.sbs.service.MoveToIm res = new com.sbs.service.MoveToIm();
		res.setP0T47(Integer.parseInt(acr.getP0t47()));
		res.setP2T47(Integer.parseInt(acr.getP2t47()));
		res.setP3T47(acr.getP3t47());
		res.setP4T47(acr.getP4t47());
		res.setP5T47(acr.getP5t47());
		res.setP6T47(acr.getP6t47());
		res.setP7T47(acr.getP7t47());
		res.setP8T47(acr.getP8t47());
		// res.setP9T47(acr.getP9t47());
		// res.setP10T47(acr.getP10t47());
		res.setP11T47(acr.getP11t47());
		res.setP12T47(Short.parseShort(acr.getP12t47()));
		res.setP13T47(Short.parseShort(acr.getP13t47()));
		res.setP14T47(Short.parseShort(acr.getP14t47()));
		res.setP15T47(Short.parseShort(acr.getP15t47()));
		res.setP16T47(Short.parseShort(acr.getP16t47()));
		res.setP17T47(acr.getP17t47());
		res.setP18T47(acr.getP18t47());
		res.setP19T47(acr.getP19t47());
		res.setP20T47(acr.getP20t47());
		res.setP21T47(acr.getP21t47());
		res.setP22T47(acr.getP22t47());
		res.setP23T47(acr.getP23t47());
		res.setP24T47(acr.getP24t47());
		res.setP25T47(acr.getP25t47());
		res.setP26T47(acr.getP26t47());
		// res.setP27T47(acr.getP27t47());
		// res.setP28T47(acr.getP28t47());
		res.setP29T47(Short.parseShort(acr.getP29t47()));
		res.setP30T47(acr.getP30t47());
		res.setP31T47(acr.getP31t47());
		res.setP32T47(acr.getP32t47());
		res.setP33T47(acr.getP33t47());
		cal.setTime(acr.getP34t47());
		res.setP34T47(cal);
		res.setP35T47(acr.getP35t47());
		res.setP36T47(Integer.parseInt(acr.getP36t47()));
		cal.setTime(acr.getP37t47());
		res.setP37T47(cal);
		// res.setP38T47(acr.getP38t47());
		res.setP39T47(acr.getP39t47());
		res.setP40T47(acr.getP40t47());
		res.setP41T47(acr.getP41t47());
		cal.setTime(acr.getP42t47());
		res.setP42T47(cal);
		res.setP43T47((String) session.getAttribute("ufn"));
		return res;
	}
	
	public void onSelect$p2t47()
	{
		Payment pm = ((Payment) p2t47.getObject());
		
		if (pm != null)
		{
			Payment_val = pm.getP15t44();
		}
		if (pm.getP17t44() != null)
		{
			kur.setVisible(true);
		}
		else
		{
			kur.setVisible(false);
		}
		coursecurrencies = ContractService.getCourseCurr_18t1_19t1_withOther2(idn, idc, df.format(p37t47.getValue()), Payment_val, alias);
		p4t471.setModel((new ListModelList(coursecurrencies)));
		p4t473.setModel((new ListModelList(coursecurrencies)));
		// System.out.println("onSelect$p2t45()-  Payment_val "+Payment_val+"pm.getP17t44()="+pm.getP17t44());
		setCourse(false);
		// setCurrent();
	}
	
	public void onSelect$ap2t47()
	{
		Payment pm = ((Payment) ap2t47.getObject());
		
		if ((pm != null) && (ap37t47.getValue() != null))
		{
			aPayment_val = pm.getP15t44();
			
			if (pm.getP17t44() != null)
			{
				akur.setVisible(true);
			}
			else
			{
				akur.setVisible(false);
			}
			coursecurrencies = ContractService.getCourseCurr_18t1_19t1_withOther2(idn, idc, df.format(ap37t47.getValue()), aPayment_val, alias);
			ap4t471.setModel((new ListModelList(coursecurrencies)));
			ap4t473.setModel((new ListModelList(coursecurrencies)));
			// System.out.println("onSelect$p2t45()-  Payment_val "+Payment_val+"pm.getP17t44()="+pm.getP17t44());
			setCourse(false);
			// setCurrent();
		}
	}
	
	public void onChange$p3t47()
	{
		
		Payment pm = ((Payment) p2t47.getObject());
		// System.out.println("pm.getP18t44()="+pm.getP18t44()+"current.getP8t45()="+current.getP8t45());
		if ((current.getP3t47() > P65) && (P65 != 0))
		{
			alert("Сумма переброски должна быть равна или меньше суммы=" + P65);
		}
		else if ((current.getP3t47() > P67) && (P67 != 0))
		{
			alert("Сумма переброски должна быть равна или меньше суммы=" + P67);
		}
	}
	
	public void onChange$ap3t47()
	{
		
		Payment pm = ((Payment) ap2t47.getObject());
		// System.out.println("pm.getP18t44()="+pm.getP18t44()+"current.getP8t45()="+current.getP8t45());
		if ((ap3t47.doubleValue() > P65) && (P65 != 0))
		{
			alert("Сумма переброски должна быть равна или меньше суммы=" + P65);
		}
		else if ((ap3t47.doubleValue() > P67) && (P67 != 0))
		{
			alert("Сумма переброски должна быть равна или меньше суммы=" + P67);
		}
	}
	
	public void onChange$p5t47()
	{
		Payment pm = ((Payment) p2t47.getObject());
		// System.out.println("pm.getP18t44()="+pm.getP18t44()+"current.getP8t45()="+current.getP8t45());
		if ((current.getP5t47() > pm.getP18t44()) && (pm.getP18t44() != 0))
		{
			alert("Сумма переброски за товар в валюте контракта 1 должна быть равна или меньше суммы=" + pm.getP18t44());
		}
	}
	
	public void onChange$ap5t47()
	{
		Payment pm = ((Payment) p2t47.getObject());
		// System.out.println("pm.getP18t44()="+pm.getP18t44()+"current.getP8t45()="+current.getP8t45());
		if ((ap5t47.doubleValue() > pm.getP18t44()) && (pm.getP18t44() != 0))
		{
			alert("Сумма переброски за товар в валюте контракта 1 должна быть равна или меньше суммы=" + pm.getP18t44());
		}
	}
	
	public void onChange$p6t47()
	{
		Payment pm = ((Payment) p2t47.getObject());
		// System.out.println("pm.getP18t44()="+pm.getP18t44()+"current.getP8t45()="+current.getP8t45());
		if ((current.getP6t47() > pm.getP19t44()) && (pm.getP19t44() != 0))
		{
			alert("Сумма переброски за товар в валюте контракта 2 должна быть равна или меньше суммы=" + pm.getP19t44());
		}
	}
	
	public void onChange$ap6t47()
	{
		Payment pm = ((Payment) p2t47.getObject());
		// System.out.println("pm.getP18t44()="+pm.getP18t44()+"current.getP8t45()="+current.getP8t45());
		if ((ap6t47.doubleValue() > pm.getP19t44()) && (pm.getP19t44() != 0))
		{
			alert("Сумма переброски за товар в валюте контракта 2 должна быть равна или меньше суммы=" + pm.getP19t44());
		}
	}
	
	public void onChange$p7t47()
	{
		Payment pm = ((Payment) p2t47.getObject());
		// System.out.println("pm.getP18t44()="+pm.getP18t44()+"current.getP8t45()="+current.getP8t45());
		if ((current.getP7t47() > pm.getP20t44()) && (pm.getP20t44() != 0))
		{
			alert("Сумма переброски за услуги (работы) в валюте контракта 1 должна быть равна или меньше суммы=" + pm.getP20t44());
		}
	}
	
	public void onChange$ap7t47()
	{
		Payment pm = ((Payment) p2t47.getObject());
		// System.out.println("pm.getP18t44()="+pm.getP18t44()+"current.getP8t45()="+current.getP8t45());
		if ((ap7t47.doubleValue() > pm.getP20t44()) && (pm.getP20t44() != 0))
		{
			alert("Сумма переброски за услуги (работы) в валюте контракта 1 должна быть равна или меньше суммы=" + pm.getP20t44());
		}
	}
	
	public void onChange$p8t47()
	{
		Payment pm = ((Payment) p2t47.getObject());
		// System.out.println("pm.getP18t44()="+pm.getP18t44()+"current.getP8t45()="+current.getP8t45());
		if ((current.getP8t47() > pm.getP21t44()) && (pm.getP21t44() != 0))
		{
			alert("Сумма переброски за услуги (работы) в валюте контракта 2 должна быть равна или меньше суммы=" + pm.getP21t44());
		}
	}
	
	public void onChange$ap8t47()
	{
		Payment pm = ((Payment) p2t47.getObject());
		// System.out.println("pm.getP18t44()="+pm.getP18t44()+"current.getP8t45()="+current.getP8t45());
		if ((ap8t47.doubleValue() > pm.getP21t44()) && (pm.getP21t44() != 0))
		{
			alert("Сумма переброски за услуги (работы) в валюте контракта 2 должна быть равна или меньше суммы=" + pm.getP21t44());
		}
	}
	
	public void onSelect$p11t47()
	{
		
		if (p11t47.getValue() != null)
		{
			accreditiv = ContractService.getAccredObj(p11t47.getValue(), alias);
			Garants = ContractService.getGarants(p11t47.getValue(), alias);
			p17t47.setModel(new ListModelList(accreditiv));
			p18t47.setModel(new ListModelList(Garants));
			
			// System.out.println("p11t47.getValue()="+p11t47.getValue()+"current.getP11t47="+current.getP11t47());
		}
	}
	
	public void onSelect$ap11t47()
	{
		if (ap11t47.getValue() != null)
		{
			accreditiv = ContractService.getAccredObj(ap11t47.getValue(), alias);
			Garants = ContractService.getGarants(ap11t47.getValue(), alias);
			ap17t47.setModel(new ListModelList(accreditiv));
			ap18t47.setModel(new ListModelList(Garants));
			
			// System.out.println("p11t47.getValue()="+p11t47.getValue()+"current.getP11t47="+current.getP11t47());
		}
	}
	
	public void onSelect$p16t47()
	{
		
		if (p16t47.getValue().equals("2"))
		{ // Аккредитив
			row_p17t47.setVisible(true);
		}
		else
		{
			row_p17t47.setVisible(false);
		}
		if (p16t47.getValue().equals("10"))
		{ // Гарантия
			getCurr_22t1_23t1 = ContractService.getCurr_22t1_23t1(idn, alias);
			p19t47.setModel(new ListModelList(getCurr_22t1_23t1));
			p19t47.setButtonVisible(true);
			row_p18t47.setVisible(true);
		}
		else
		{
			row_p18t47.setVisible(false);
		}
		if (p16t47.getValue().equals("1") || (p16t47.getValue().equals("7") || (p16t47.getValue().equals("10") || (p16t47.getValue().equals("9")))))
		{ // Предоплата
			getCurr_22t1_23t1 = ContractService.getCurr_22t1_23t1(idn, alias);
			p19t47.setButtonVisible(true);
			p19t47.setReadonly(true);
			p19t47.setModel(new ListModelList(getCurr_22t1_23t1));
			
			Payment pm = ((Payment) p2t47.getObject());
			if (pm != null)
			{
				Payment_val = pm.getP15t44();
				coursecurrencies2 = ContractService.getMyCurr_2_only(df.format(p37t47.getValue()), Payment_val, p19t47.getValue(), alias);
				p20t471.setModel((new ListModelList(coursecurrencies2)));
				p20t473.setModel((new ListModelList(coursecurrencies2)));
				setCourse(false);
			}
			if (Payment_val.equalsIgnoreCase(p19t47.getValue()))
			{
				kur2.setVisible(false);
				p21t47.setValue(p3t47.getValue());
				p21t47.setReadonly(true);
			}
			else
			{
				kur2.setVisible(true);
			}
			if ((p19t47.getValue() != val18 && val18.equals(val19)) || (p19t47.getValue() != val18 && val19.equals(null)))
			{
				coursecurrencies3 = ContractService.getMyCurr_2_only(df.format(p37t47.getValue()), val18, p19t47.getValue(), alias);
				p22t471.setModel((new ListModelList(coursecurrencies3)));
				p22t473.setModel((new ListModelList(coursecurrencies3)));
				// System.out.println("onChange$p19t47-  Payment_val "+Payment_val+"current.getP19t47()="+current.getP19t47()+"df.format(p37t47.getValue())="+df.format(p37t47.getValue()));
				setCourse(false);
			}
			if (p19t47.getValue().equals(val18) && p19t47.getValue() != val19 && val19 != null)
			{
				coursecurrencies3 = ContractService.getMyCurr_2_only(df.format(p37t47.getValue()), val19, p19t47.getValue(), alias);
				p22t471.setModel((new ListModelList(coursecurrencies3)));
				p22t473.setModel((new ListModelList(coursecurrencies3)));
				// System.out.println("onChange$p19t47-  Payment_val "+Payment_val+"current.getP19t47()="+current.getP19t47()+"df.format(p37t47.getValue())="+df.format(p37t47.getValue()));
				setCourse(false);
			}
			if (p19t47.getValue().equalsIgnoreCase(val18))
			{
				kur3.setVisible(false);
				
			}
			else
			{
				kur3.setVisible(true);
			}
			// System.out.println("onSelect$p16t47() ----  p19t47.getValue()= "+p19t47.getValue()+" - current.getP19t47()="+current.getP19t47());
		}
	}
	
	public void onSelect$ap16t47()
	{
		if (ap16t47.getValue().equals("2"))
		{
			row_ap17t47.setVisible(true);
		}
		else
		{
			row_ap17t47.setVisible(false);
		}
		if (ap16t47.getValue().equals("10"))
		{
			getCurr_22t1_23t1 = ContractService.getCurr_22t1_23t1(idn, alias);
			ap19t47.setModel(new ListModelList(getCurr_22t1_23t1));
			ap19t47.setButtonVisible(true);
			row_ap18t47.setVisible(true);
		}
		else
		{
			row_ap18t47.setVisible(false);
		}
		if (ap16t47.getValue().equals("1") || (ap16t47.getValue().equals("7") || (ap16t47.getValue().equals("10") || (ap16t47.getValue().equals("9")))))
		{ // Предоплата, инкассо
			getCurr_22t1_23t1 = ContractService.getCurr_22t1_23t1(idn, alias);
			ap19t47.setButtonVisible(true);
			ap19t47.setReadonly(true);
			ap19t47.setModel(new ListModelList(getCurr_22t1_23t1));
			Payment pm = ((Payment) ap2t47.getObject());
			if (pm != null)
			{
				aPayment_val = pm.getP15t44();
				coursecurrencies2 = ContractService.getMyCurr_2_only(df.format(ap37t47.getValue()), aPayment_val, ap19t47.getValue(), alias);
				ap20t471.setModel((new ListModelList(coursecurrencies2)));
				ap20t473.setModel((new ListModelList(coursecurrencies2)));
				// System.out.println("onChange$p19t47-  Payment_val "+Payment_val+"current.getP19t47()="+current.getP19t47()+"df.format(p37t47.getValue())="+df.format(p37t47.getValue()));
				asetCourse(false);
			}
			if (aPayment_val.equalsIgnoreCase(ap19t47.getValue()))
			{
				akur2.setVisible(false);
				ap21t47.setValue(ap3t47.getValue());
				ap21t47.setReadonly(true);
			}
			else
			{
				akur2.setVisible(true);
				ap21t47.setReadonly(false);
				ap21t47.setValue("");
			}
			if ((ap19t47.getValue() != val18 && val18.equals(val19)) || (ap19t47.getValue() != val18 && val19.equals(null)))
			{
				coursecurrencies3 = ContractService.getMyCurr_2_only(df.format(ap37t47.getValue()), val18, ap19t47.getValue(), alias);
				ap22t471.setModel((new ListModelList(coursecurrencies3)));
				ap22t473.setModel((new ListModelList(coursecurrencies3)));
				// System.out.println("onChange$p19t47-  Payment_val "+Payment_val+"current.getP19t47()="+current.getP19t47()+"df.format(p37t47.getValue())="+df.format(p37t47.getValue()));
				asetCourse(false);
			}
			if (ap19t47.getValue().equals(val18) && ap19t47.getValue() != val19 && val19 != null)
			{
				coursecurrencies3 = ContractService.getMyCurr_2_only(df.format(ap37t47.getValue()), val19, ap19t47.getValue(), alias);
				ap22t471.setModel((new ListModelList(coursecurrencies3)));
				ap22t473.setModel((new ListModelList(coursecurrencies3)));
				// System.out.println("onChange$p19t47-  Payment_val "+Payment_val+"current.getP19t47()="+current.getP19t47()+"df.format(p37t47.getValue())="+df.format(p37t47.getValue()));
				asetCourse(false);
			}
			if (ap19t47.getValue().equalsIgnoreCase(val18))
			{
				akur3.setVisible(false);
				
			}
			else
			{
				akur3.setVisible(true);
			}
			
		}
	}
	
	public void onSelect$p17t47()
	{
		Accreditiv am = ((Accreditiv) p17t47.getObject());
		if (am != null)
		{
			Acred_val = am.getP4t21();
			kod = com.is.tf.contract.ContractService.getMyCurrency2(Acred_val, alias);
			// p19t47.setSelecteditem(kod);
			p19t47.setButtonVisible(false);
			p19t47.setReadonly(true);
			p19t47.setModel((new ListModelList(kod)));
			coursecurrencies2 = ContractService.getMyCurr_2_only(df.format(p37t47.getValue()), Payment_val, Acred_val, alias);
			p20t471.setModel((new ListModelList(coursecurrencies2)));
			p20t473.setModel((new ListModelList(coursecurrencies2)));
			setCourse(false);
			System.out.println("onSelect$p17t47() -----p19t47.getValue()= " + p19t47.getValue() + " - current.getP19t47()=" + current.getP19t47() + "Acred_val=" + Acred_val + " val18=" + val18 + " val19=" + val19);
		}
		if (Payment_val.equalsIgnoreCase(Acred_val))
		{
			kur2.setVisible(false);
			p21t47.setReadonly(true);
			p21t47.setValue(p3t47.getValue());
		}
		else
		{
			kur2.setVisible(true);
			p21t47.setReadonly(false);
		}
		if ((Acred_val != val18 && val18.equals(val19)) || (Acred_val != val18 && val19.equals(null)))
		{
			coursecurrencies3 = ContractService.getMyCurr_2_only(df.format(p37t47.getValue()), val18, Acred_val, alias);
			p22t471.setModel((new ListModelList(coursecurrencies3)));
			p22t473.setModel((new ListModelList(coursecurrencies3)));
			// System.out.println("onChange$p19t47-  Payment_val "+Payment_val+"current.getP19t47()="+current.getP19t47()+"df.format(p37t47.getValue())="+df.format(p37t47.getValue()));
			setCourse(false);
		}
		if (Acred_val.equals(val18) && Acred_val != val19 && val19 != null)
		{
			coursecurrencies3 = ContractService.getMyCurr_2_only(df.format(p37t47.getValue()), val19, Acred_val, alias);
			p22t471.setModel((new ListModelList(coursecurrencies3)));
			p22t473.setModel((new ListModelList(coursecurrencies3)));
			// System.out.println("onChange$p19t47-  Payment_val "+Payment_val+"current.getP19t47()="+current.getP19t47()+"df.format(p37t47.getValue())="+df.format(p37t47.getValue()));
			setCourse(false);
		}
		if (Acred_val.equalsIgnoreCase(val18))
		{
			kur3.setVisible(false);
			
		}
		else
		{
			kur3.setVisible(true);
		}
		
	}
	
	public void onSelect$ap17t47()
	{
		Accreditiv am = ((Accreditiv) ap17t47.getObject());
		Payment pm = ((Payment) ap2t47.getObject());
		if (pm != null)
		{
			aPayment_val = pm.getP15t44();
		}
		if (am != null)
		{
			aAcred_val = am.getP4t21();
			akod = com.is.tf.contract.ContractService.getMyCurrency2(aAcred_val, alias);
			// p19t47.setSelecteditem(kod);
			ap19t47.setButtonVisible(false);
			ap19t47.setReadonly(true);
			ap19t47.setModel((new ListModelList(akod)));
			coursecurrencies2 = ContractService.getMyCurr_2_only(df.format(ap37t47.getValue()), aPayment_val, aAcred_val, alias);
			ap20t471.setModel((new ListModelList(coursecurrencies2)));
			ap20t473.setModel((new ListModelList(coursecurrencies2)));
			asetCourse(false);
		}
		if (aPayment_val.equalsIgnoreCase(aAcred_val))
		{
			akur2.setVisible(false);
			ap21t47.setReadonly(true);
			ap21t47.setValue(ap3t47.getValue());
		}
		else
		{
			akur2.setVisible(true);
			ap21t47.setReadonly(false);
			ap21t47.setValue("");
		}
		if ((aAcred_val != val18 && val18.equals(val19)) || (aAcred_val != val18 && val19.equals(null)))
		{
			coursecurrencies3 = ContractService.getMyCurr_2_only(df.format(ap37t47.getValue()), val18, aAcred_val, alias);
			ap22t471.setModel((new ListModelList(coursecurrencies3)));
			ap22t473.setModel((new ListModelList(coursecurrencies3)));
			// System.out.println("onChange$p19t47-  Payment_val "+Payment_val+"current.getP19t47()="+current.getP19t47()+"df.format(p37t47.getValue())="+df.format(p37t47.getValue()));
			asetCourse(false);
		}
		if (aAcred_val.equals(val18) && aAcred_val != val19 && val19 != null)
		{
			coursecurrencies3 = ContractService.getMyCurr_2_only(df.format(ap37t47.getValue()), val19, aAcred_val, alias);
			ap22t471.setModel((new ListModelList(coursecurrencies3)));
			ap22t473.setModel((new ListModelList(coursecurrencies3)));
			// System.out.println("onChange$p19t47-  Payment_val "+Payment_val+"current.getP19t47()="+current.getP19t47()+"df.format(p37t47.getValue())="+df.format(p37t47.getValue()));
			setCourse(false);
		}
		if (aAcred_val.equalsIgnoreCase(val18))
		{
			akur3.setVisible(false);
			
		}
		else
		{
			akur3.setVisible(true);
		}
	}
	
	/*
	 * public void onChange$p19t47() { coursecurrencies3 =
	 * ContractService.getCourseCurr_18t1_19t1_withOther2(idn, idc,
	 * df.format(p37t47.getValue()) , p19t47.getValue(), alias);
	 * p22t471.setModel((new ListModelList(coursecurrencies3)));
	 * p22t473.setModel((new ListModelList(coursecurrencies3)));
	 * //System.out.println
	 * ("onChange$p19t47-  Payment_val "+Payment_val+"current.getP19t47()="
	 * +current
	 * .getP19t47()+"df.format(p37t47.getValue())="+df.format(p37t47.getValue
	 * ())); setCourse(false); //setCurrent(); }
	 */
	public void onSelect$p29t47()
	{
		if (p29t47.getValue().equals("4"))
		{
			row_p30t47.setVisible(true);
			row_p31t47.setVisible(true);
			row_p32t47.setVisible(false);
			row_p33t47.setVisible(false);
			row_p34t47.setVisible(false);
			row_p35t47.setVisible(false);
			agreement = ContractService.getAgreement(p11t47.getValue(), alias);
			p31t47.setModel(new ListModelList(agreement));
		}
		else if (p29t47.getValue().equals("1"))
		{
			row_p30t47.setVisible(false);
			row_p31t47.setVisible(false);
			row_p32t47.setVisible(true);
			row_p33t47.setVisible(true);
			row_p34t47.setVisible(true);
			row_p35t47.setVisible(false);
		}
		else if (p29t47.getValue().equals("5"))
		{
			row_p30t47.setVisible(false);
			row_p31t47.setVisible(false);
			row_p32t47.setVisible(false);
			row_p33t47.setVisible(false);
			row_p34t47.setVisible(false);
			row_p35t47.setVisible(true);
		}
	}
	
	public void onSelect$ap29t47()
	{
		if (ap29t47.getValue().equals("4"))
		{
			row_ap30t47.setVisible(true);
			row_ap31t47.setVisible(true);
			row_ap32t47.setVisible(false);
			row_ap33t47.setVisible(false);
			row_ap34t47.setVisible(false);
			row_ap35t47.setVisible(false);
			agreement = ContractService.getAgreement(ap11t47.getValue(), alias);
			ap31t47.setModel(new ListModelList(agreement));
		}
		else if (ap29t47.getValue().equals("1"))
		{
			row_ap30t47.setVisible(false);
			row_ap31t47.setVisible(false);
			row_ap32t47.setVisible(true);
			row_ap33t47.setVisible(true);
			row_ap34t47.setVisible(true);
			row_ap35t47.setVisible(false);
		}
		else if (ap29t47.getValue().equals("5"))
		{
			row_ap30t47.setVisible(false);
			row_ap31t47.setVisible(false);
			row_ap32t47.setVisible(false);
			row_ap33t47.setVisible(false);
			row_ap34t47.setVisible(false);
			row_ap35t47.setVisible(true);
		}
	}
	
	public void onInitRenderLater$p4t471()
	{
		setCourse(false);
	}
	
	public void onInitRenderLater$p4t473()
	{
		setCourse(false);
	}
	
	public void onInitRenderLater$ap4t471()
	{
		asetCourse(false);
	}
	
	public void onInitRenderLater$ap4t473()
	{
		asetCourse(false);
	}
	
	public void onInitRenderLater$p20t471()
	{
		setCourse(false);
	}
	
	public void onInitRenderLater$p20t473()
	{
		setCourse(false);
	}
	
	public void onInitRenderLater$ap20t471()
	{
		asetCourse(false);
	}
	
	public void onInitRenderLater$ap20t473()
	{
		asetCourse(false);
	}
	
	public void onInitRenderLater$p22t471()
	{
		setCourse(false);
	}
	
	public void onInitRenderLater$p22t473()
	{
		setCourse(false);
	}
	
	public void onInitRenderLater$ap22t471()
	{
		asetCourse(false);
	}
	
	public void onInitRenderLater$ap22t473()
	{
		asetCourse(false);
	}
	
	private void setCourse(Boolean getNewCourse)
	{
		Payment pm = ((Payment) p2t47.getObject());
		
		if (pm != null)
		{
			Payment_val = pm.getP15t44();
		}
		if (getNewCourse)
		{
			coursecurrencies = ContractService.getCourseCurr_18t1_19t1_withOther2(idn, idc, df.format(p37t47.getValue()), Payment_val, alias);
			coursecurrencies2 = ContractService.getMyCurr_2_only(df.format(p37t47.getValue()), Payment_val, p19t47.getValue(), alias);
			p4t471.setModel((new ListModelList(coursecurrencies)));
			p4t473.setModel((new ListModelList(coursecurrencies)));
			p20t471.setModel((new ListModelList(coursecurrencies2)));
			p20t473.setModel((new ListModelList(coursecurrencies2)));
			coursecurrencies3 = ContractService.getCourseCurr_18t1_19t1_withOther2(idn, idc, df.format(p37t47.getValue()), p19t47.getValue(), alias);
			p22t471.setModel((new ListModelList(coursecurrencies3)));
			p22t473.setModel((new ListModelList(coursecurrencies3)));
		}
		if ((coursecurrencies.size() > 0) || (coursecurrencies2.size() > 0) || (coursecurrencies3.size() > 0))
		{
			
			RefCurrencyData curr1 = PaymentService.getRefCurrencyData(Payment_val, coursecurrencies);
			RefCurrencyData curr2 = PaymentService.getRefCurrencyData(val18, coursecurrencies);
			RefCurrencyData curr3 = PaymentService.getRefCurrencyData(val19, coursecurrencies);
			RefCurrencyData curr21 = PaymentService.getRefCurrencyData(Payment_val, coursecurrencies2);
			RefCurrencyData curr22 = PaymentService.getRefCurrencyData(p19t47.getValue(), coursecurrencies2);
			RefCurrencyData curr31 = PaymentService.getRefCurrencyData(val18, coursecurrencies3);
			RefCurrencyData curr32 = PaymentService.getRefCurrencyData(val19, coursecurrencies3);
			RefCurrencyData curr33 = PaymentService.getRefCurrencyData(p19t47.getValue(), coursecurrencies3);
			if (curr1 != null && curr2 != null && curr3 != null)
			{
				
				if (curr1.getCourse() > curr2.getCourse())
				{
					p4t471.setSelecteditem(curr1.getKod());
					p4t473.setSelecteditem(curr2.getKod());
				}
				else
				{
					p4t471.setSelecteditem(curr2.getKod());
					p4t473.setSelecteditem(curr1.getKod());
				}
				countCourse(false);
			}
			if (curr21 != null && curr22 != null)
			{
				
				if (curr21.getCourse() > curr22.getCourse())
				{
					p20t471.setSelecteditem(curr21.getKod());
					p20t473.setSelecteditem(curr22.getKod());
				}
				else
				{
					p20t471.setSelecteditem(curr22.getKod());
					p20t473.setSelecteditem(curr21.getKod());
				}
				countCourse(false);
			}
			if (curr31 != null && curr33 != null)
			{
				
				if (curr31.getCourse() > curr33.getCourse())
				{
					p22t471.setSelecteditem(curr31.getKod());
					p22t473.setSelecteditem(curr33.getKod());
				}
				else
				{
					p22t471.setSelecteditem(curr33.getKod());
					p22t473.setSelecteditem(curr31.getKod());
				}
				countCourse(false);
				System.out.println(" curr31.getCourse()=" + curr31.getCourse() + " curr32.getCourse()=" + curr32.getCourse() + " curr33.getCourse()=" + curr33.getCourse());
			}
		}
	}
	
	private void countCourse(Boolean setCourse)
	{
		try
		{
			
			if (!CheckNull.isEmpty(p4t473.getValue()) && !CheckNull.isEmpty(p4t471.getValue()))
			{
				if (setCourse)
				{
					p4t47.setValue("" + (p4t471.getCourse() / p4t473.getCourse()));
					current.setP4t47((p4t471.getCourse() / p4t473.getCourse()));
				}
				p4t47.setValue("" + (p4t471.getCourse() / p4t473.getCourse()));
				
				// System.out.println("countCourse-  По курсу ЦБ: "+(p4t471.getCourse()/p4t473.getCourse())+" "+p4t471.getCourse()+" "+p4t473.getCourse());
				cbcourse.setValue("По курсу ЦБ: " + (p4t471.getCourse() / p4t473.getCourse()));
			}
			
			if (!CheckNull.isEmpty(p20t473.getValue()) && !CheckNull.isEmpty(p20t471.getValue()))
			{
				if (setCourse)
				{
					p20t47.setValue("" + (p20t471.getCourse() / p20t473.getCourse()));
					current.setP20t47((p20t471.getCourse() / p20t473.getCourse()));
				}
				p20t47.setValue("" + (p20t471.getCourse() / p20t473.getCourse()));
				
				// System.out.println("countCourse- p20t471 По курсу ЦБ: "+(p20t471.getCourse()/p20t473.getCourse())+" "+p20t471.getCourse()+" "+p20t473.getCourse());
				cbcourse2.setValue("По курсу ЦБ: " + (p20t471.getCourse() / p20t473.getCourse()));
			}
			if (!CheckNull.isEmpty(p22t473.getValue()) && !CheckNull.isEmpty(p22t471.getValue()))
			{
				if (setCourse)
				{
					p22t47.setValue("" + (p22t471.getCourse() / p22t473.getCourse()));
					current.setP22t47((p22t471.getCourse() / p22t473.getCourse()));
				}
				p22t47.setValue("" + (p22t471.getCourse() / p22t473.getCourse()));
				
				// System.out.println("countCourse- p20t471 По курсу ЦБ: "+(p20t471.getCourse()/p20t473.getCourse())+" "+p20t471.getCourse()+" "+p20t473.getCourse());
				cbcourse3.setValue("По курсу ЦБ: " + (p22t471.getCourse() / p22t473.getCourse()));
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void asetCourse(Boolean getNewCourse)
	{
		Payment pm = ((Payment) ap2t47.getObject());
		
		if (pm != null)
		{
			aPayment_val = pm.getP15t44();
			// System.out.println("Payment_val="+Payment_val);
		}
		if (getNewCourse)
		{
			coursecurrencies = ContractService.getCourseCurr_18t1_19t1_withOther2(idn, idc, df.format(ap37t47.getValue()), aPayment_val, alias);
			// System.out.println("setCourse - df.format(p37t47.getValue()="+df.format(p37t47.getValue()+"Payment_val="+Payment_val+"idc="+idc+"idn="+idn));
			ap4t471.setModel((new ListModelList(coursecurrencies)));
			ap4t473.setModel((new ListModelList(coursecurrencies)));
			coursecurrencies2 = ContractService.getMyCurr_2_only(df.format(ap37t47.getValue()), aPayment_val, ap19t47.getValue(), alias);
			ap20t471.setModel((new ListModelList(coursecurrencies2)));
			ap20t473.setModel((new ListModelList(coursecurrencies2)));
			coursecurrencies3 = ContractService.getCourseCurr_18t1_19t1_withOther2(idn, idc, df.format(ap37t47.getValue()), ap19t47.getValue(), alias);
			ap22t471.setModel((new ListModelList(coursecurrencies3)));
			ap22t473.setModel((new ListModelList(coursecurrencies3)));
		}
		if ((coursecurrencies.size() > 0) || (coursecurrencies2.size() > 0) || (coursecurrencies3.size() > 0))
		{
			
			RefCurrencyData curr1 = PaymentService.getRefCurrencyData(aPayment_val, coursecurrencies);
			RefCurrencyData curr2 = PaymentService.getRefCurrencyData(val18, coursecurrencies);
			RefCurrencyData curr3 = PaymentService.getRefCurrencyData(val19, coursecurrencies);
			RefCurrencyData curr21 = PaymentService.getRefCurrencyData(aPayment_val, coursecurrencies2);
			RefCurrencyData curr22 = PaymentService.getRefCurrencyData(ap19t47.getValue(), coursecurrencies2);
			RefCurrencyData curr31 = PaymentService.getRefCurrencyData(val18, coursecurrencies3);
			RefCurrencyData curr32 = PaymentService.getRefCurrencyData(val19, coursecurrencies3);
			RefCurrencyData curr33 = PaymentService.getRefCurrencyData(ap19t47.getValue(), coursecurrencies3);
			if (curr1 != null && curr2 != null && curr3 != null)
			{
				
				if (curr1.getCourse() > curr2.getCourse())
				{
					ap4t471.setSelecteditem(curr1.getKod());
					ap4t473.setSelecteditem(curr2.getKod());
				}
				else
				{
					ap4t471.setSelecteditem(curr2.getKod());
					ap4t473.setSelecteditem(curr1.getKod());
				}
				acountCourse(false);
			}
			if (curr21 != null && curr22 != null)
			{
				
				if (curr21.getCourse() > curr22.getCourse())
				{
					ap20t471.setSelecteditem(curr21.getKod());
					ap20t473.setSelecteditem(curr22.getKod());
				}
				else
				{
					ap20t471.setSelecteditem(curr22.getKod());
					ap20t473.setSelecteditem(curr21.getKod());
				}
				acountCourse(false);
			}
			if (curr31 != null && curr33 != null)
			{
				
				if (curr31.getCourse() > curr33.getCourse())
				{
					ap22t471.setSelecteditem(curr31.getKod());
					ap22t473.setSelecteditem(curr33.getKod());
				}
				else
				{
					ap22t471.setSelecteditem(curr33.getKod());
					ap22t473.setSelecteditem(curr31.getKod());
				}
				acountCourse(false);
			}
		}
	}
	
	private void acountCourse(Boolean setCourse)
	{
		try
		{
			
			if (!CheckNull.isEmpty(ap4t473.getValue()) && !CheckNull.isEmpty(ap4t471.getValue()))
			{
				if (setCourse)
				{
					ap4t47.setValue("" + (ap4t471.getCourse() / ap4t473.getCourse()));
				}
				ap4t47.setValue("" + (ap4t471.getCourse() / ap4t473.getCourse()));
				acbcourse.setValue("По курсу ЦБ: " + (ap4t471.getCourse() / ap4t473.getCourse()));
			}
			if (!CheckNull.isEmpty(ap20t473.getValue()) && !CheckNull.isEmpty(ap20t471.getValue()))
			{
				if (setCourse)
				{
					ap20t47.setValue("" + (ap20t471.getCourse() / ap20t473.getCourse()));
				}
				ap20t47.setValue("" + (ap20t471.getCourse() / ap20t473.getCourse()));
				acbcourse2.setValue("По курсу ЦБ: " + (ap20t471.getCourse() / ap20t473.getCourse()));
			}
			if (!CheckNull.isEmpty(ap22t473.getValue()) && !CheckNull.isEmpty(ap22t471.getValue()))
			{
				if (setCourse)
				{
					ap22t47.setValue("" + (ap22t471.getCourse() / ap22t473.getCourse()));
				}
				ap22t47.setValue("" + (ap22t471.getCourse() / ap22t473.getCourse()));
				acbcourse3.setValue("По курсу ЦБ: " + (ap22t471.getCourse() / ap22t473.getCourse()));
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void setCurrent()
	{
		Payment pm = ((Payment) p2t47.getObject());
		if (current != null)
		{
			if (pm != null)
			{
				if (pm.getP17t44() != null)
				{
					kur.setVisible(true);
				}
				else
				{
					kur.setVisible(false);
				}
				Payment_val = pm.getP15t44();
				
			}
			coursecurrencies = ContractService.getCourseCurr_18t1_19t1_withOther2(idn, idc, df.format(p37t47.getValue()), Payment_val, alias);
			p4t471.setModel((new ListModelList(coursecurrencies)));
			p4t473.setModel((new ListModelList(coursecurrencies)));
			coursecurrencies2 = ContractService.getMyCurr_2_only(df.format(p37t47.getValue()), Payment_val, p19t47.getValue(), alias);
			p20t471.setModel((new ListModelList(coursecurrencies2)));
			p20t473.setModel((new ListModelList(coursecurrencies2)));
			coursecurrencies3 = ContractService.getCourseCurr_18t1_19t1_withOther2(idn, idc, df.format(p37t47.getValue()), current.getP19t47(), alias);
			p22t471.setModel((new ListModelList(coursecurrencies3)));
			p22t473.setModel((new ListModelList(coursecurrencies3)));
			
			if (pm.getP18t44() != 0)
			{
				row_p5t47.setVisible(true);
				row_ap5t47.setVisible(true);
			}
			else
			{
				row_p5t47.setVisible(false);
				row_ap5t47.setVisible(false);
			}
			if (pm.getP19t44() != 0)
			{
				row_p6t47.setVisible(true);
				row_ap6t47.setVisible(true);
			}
			else
			{
				row_p6t47.setVisible(false);
				row_p6t47.setVisible(false);
			}
			if (pm.getP20t44() != 0)
			{
				row_p7t47.setVisible(true);
				row_ap7t47.setVisible(true);
			}
			else
			{
				row_p7t47.setVisible(false);
				row_ap7t47.setVisible(false);
			}
			if (pm.getP21t44() != 0)
			{
				row_p8t47.setVisible(true);
				row_ap8t47.setVisible(true);
			}
			else
			{
				row_p8t47.setVisible(false);
				row_ap8t47.setVisible(false);
			}
			if (pm.getP3t44() != null)
			{
				current.setP12t47(pm.getP3t44());
				ap12t47.setSelecteditem((pm.getP3t44()));
			}
			if (pm.getP4t44() != null)
			{
				p41t47.setValue(pm.getP4t44());
				ap41t47.setValue(pm.getP4t44());
			}
			if (pm.getP5t44() != null)
			{
				p42t47.setValue(pm.getP5t44());
				ap42t47.setValue(pm.getP5t44());
			}
			if (pm.getP6t44() != null)
			{
				p39t47.setValue(pm.getP6t44());
				ap39t47.setValue(pm.getP6t44());
			}
			if (pm.getP8t44() != null)
			{
				current.setP13t47(pm.getP8t44());
				ap13t47.setSelecteditem(pm.getP8t44());
			}
			if (pm.getP9t44() != null)
			{
				current.setP14t47(pm.getP9t44());
				ap14t47.setSelecteditem(pm.getP9t44());
			}
			if (pm.getP10t44() != null)
			{
				current.setP15t47(pm.getP10t44());
				ap15t47.setSelecteditem(pm.getP10t44());
			}
			if (current.getP11t47() != null)
			{
				accreditiv = ContractService.getAccredObj(current.getP11t47(), alias);
				p17t47.setModel(new ListModelList(accreditiv));
				Garants = ContractService.getGarants(current.getP11t47(), alias);
				p18t47.setModel(new ListModelList(Garants));
			}
			if (Payment_val.equalsIgnoreCase(current.getP19t47()))
			{
				kur2.setVisible(false);
				current.setP21t47(current.getP3t47());
			}
			else
			{
				kur2.setVisible(true);
			}
			
			if (current.getP29t47().equals("4"))
			{
				row_p30t47.setVisible(true);
				row_p31t47.setVisible(true);
				row_p32t47.setVisible(false);
				row_p33t47.setVisible(false);
				row_p34t47.setVisible(false);
				row_p35t47.setVisible(false);
				agreement = ContractService.getAgreement(current.getP11t47(), alias);
				p31t47.setModel(new ListModelList(agreement));
			}
			else if (current.getP29t47().equals("1"))
			{
				row_p30t47.setVisible(false);
				row_p31t47.setVisible(false);
				row_p32t47.setVisible(true);
				row_p33t47.setVisible(true);
				row_p34t47.setVisible(true);
				row_p35t47.setVisible(false);
			}
			else if (current.getP29t47().equals("5"))
			{
				row_p30t47.setVisible(false);
				row_p31t47.setVisible(false);
				row_p32t47.setVisible(false);
				row_p33t47.setVisible(false);
				row_p34t47.setVisible(false);
				row_p35t47.setVisible(true);
			}
		}
	}
}
