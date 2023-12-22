package com.is.tf.movetoex;

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

import com.is.tf.Accreditiv.Accreditiv;
import com.is.tf.contract.Contract;
import com.is.tf.contract.ContractService;
import com.is.tf.currency.RefCurrencyBox;
import com.is.tf.currency.RefCurrencyData;
import com.is.tf.fund.Fund;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.RefData;
import com.is.utils.refobj.RefObjCBox;
import com.is.utils.refobj.RefObjData;
import com.sbs.service.BankServiceProxy;
import com.sbs.service.MoveToExResult;

public class MovetoexViewCtrl extends GenericForwardComposer
{
	private Div frm;
	private Listbox dataGrid;
	private Paging contactPaging;
	private Div grd;
	private Grid addgrd, frmgrd, fgrd;
	private Toolbarbutton btn_last, btn_save;
	private Toolbarbutton btn_next;
	private Toolbarbutton btn_prev;
	private Toolbarbutton btn_first;
	private Toolbarbutton btn_add;
	
	private Toolbarbutton btn_search;
	private Toolbarbutton btn_back;
	private Toolbarbutton btn_edit, btn_confirm, btn_reject, btn_delete;
	private Toolbar tb;
	private Textbox id, p1t40, p0t40, p28t40, p29t40, p31t40, p33t40;
	private Textbox aid, ap1t40, ap0t40, ap28t40, ap29t40, ap31t40, ap33t40;
	private Textbox fid, fp1t40, fp0t40, fp2t40, fp10t40, fp11t40, fp12t40, fp13t40, fp14t40, fp15t40, fp16t40, fp17t40, fp25t40, fp26t40, fp27t40, fp28t40, fp29t40, fp31t40, fp33t40;
	private Datebox p30t40, p32t40, ap30t40, ap32t40, fp30t40, fp32t40;
	private Decimalbox ap20t402, p20t402, p18t402, ap18t402, p3t40, p5t40, ap3t40, ap5t40, fp3t40, ap6t40, fp5t40, fp6t40, fp7t40, fp8t40, fp9t40, ap7t40, ap8t40, ap9t40, p6t40, p7t40, fp18t40, fp19t40, fp20t40, fp21t40, fp22t40, fp23t40, fp24t40,
			p8t40, p9t40, p18t40, p19t40, p20t40, p21t40, p22t40, p23t40, p24t40, ap18t40, ap19t40, ap20t40, ap21t40, ap22t40, ap23t40, ap24t40;
	private RefCBox p27t40, ap27t40, p26t40, ap26t40, p25t40, ap25t40, p17t40, ap17t40, ap16t40, p16t40, p15t40, ap15t40, p13t40, ap13t40, p12t40, ap12t40, ap10t40, p10t40, p4t40, ap4t40, fp4t40;
	private RefObjCBox p11t40, ap11t40, p14t40, ap14t40, p2t40, ap2t40;
	private RefCurrencyBox ap20t401, ap20t403, p20t401, p20t403, p18t401, p18t403, ap18t401, ap18t403;
	private Row row_p20t40, row_p6t40, row_p7t40, row_p8t40, row_p9t40, row_ap6t40, row_ap7t40, row_ap8t40, row_ap9t40, row_ap28t40, row_ap29t40, row_ap30t40, row_ap31t40, row_p26t40, row_p27t40, row_p28t40, row_p29t40, row_p30t40, row_p31t40,
			row_ap26t40, row_ap27t40, row_p18t40, row_ap18t40, row_ap14t40, row_ap15t40, row_ap16t40, row_p14t40, row_p15t40, row_p16t40;
	private Paging movetoexPaging;
	private String aidnexp, idnexp, ret, i1, i2, afund_val2, fund_val, fund_val2, AcrCurr;
	private Double rr, rr2;
	private Long idc;
	private Label line1, line2, line3, line4, line5, line6, line7, line8, line9;
	private String sparam, p6, p7, p8, p9, ap6, ap7, ap8, ap9, dest2, dest, ss, alias, id_contract, idn, val1, val2, cu, inn;
	private Label aconr_val_2, aconr_val_11, aconr_val_1, aconr_val_22, conr_val0, aconr_val0, conr_val_1, conr_val_2, conr_val_11, conr_val_22, acbcourse2, cbcourse2, conr_val11, conr_val22, conr_val2, aconr_val1, aconr_val2, aconr_val11,
			aconr_val22, conr_val1, conr_val1a, conr_val2a, gar_val, cbcourse, acbcourse;
	private int _pageSize = 15;
	private int _startPageNumber = 0;
	private HashMap<String, String> curr_ = null;
	private List<RefObjData> funds = new ArrayList<RefObjData>();
	private List<RefObjData> idn_exp = new ArrayList<RefObjData>();
	private List<RefData> agreement_curr = new ArrayList<RefData>();
	private List<RefData> agreement2 = new ArrayList<RefData>();
	private List<RefData> agreement2a = new ArrayList<RefData>();
	private List<RefData> conditions = new ArrayList<RefData>();
	private List<RefData> idnc = new ArrayList<RefData>();
	private List<RefData> garant = new ArrayList<RefData>();
	private List<RefData> reason = new ArrayList<RefData>();
	private List<RefData> policy = new ArrayList<RefData>();
	private List<RefData> Curr_22t1_23t1 = new ArrayList<RefData>();
	private List<RefData> destt = new ArrayList<RefData>();
	private List<RefData> accred = new ArrayList<RefData>();
	private List<RefObjData> Accred_obj = new ArrayList<RefObjData>();
	private List<RefData> prodaja = new ArrayList<RefData>();
	private List<RefCurrencyData> currenciesg = new ArrayList<RefCurrencyData>();
	private List<RefCurrencyData> currenciesg2 = new ArrayList<RefCurrencyData>();
	private int _totalSize = 0;
	private boolean _needsTotalSizeUpdate = true;
	private long gid, gidc;
	
	public MovetoexFilter filter = new MovetoexFilter();
	
	PagingListModel model = null;
	ListModelList lmodel = null;
	private AnnotateDataBinder binder;
	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	
	private Movetoex current = new Movetoex();
	
	public MovetoexViewCtrl()
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
			idc = Long.parseLong((parameter[0]));
			// System.out.println("Garant  cont_idn "+idn);
		}
		parameter = (String[]) param.get("val1");
		if (parameter != null)
		{
			val1 = (parameter[0]);
			// System.out.println("Garant  cont_val1 "+val1);
		}
		parameter = (String[]) param.get("inn");
		if (parameter != null)
		{
			inn = (parameter[0]);
			// System.out.println("Garant  cont_val1 "+val1);
		}
		
		parameter = (String[]) param.get("val2");
		if (parameter != null)
		{
			val2 = (parameter[0]);
			// System.out.println("Garant  cont_val2 "+val2);
		}
		parameter = (String[]) param.get("spr");
		if (parameter != null)
		{
			sparam = (parameter[0]);
		}
		curr_ = com.is.tf.contract.ContractService.getHCurr(alias);
		
		conr_val2.setValue(curr_.get(val2));
		conr_val1.setValue(curr_.get(val1));
		conr_val22.setValue(curr_.get(val2));
		conr_val11.setValue(curr_.get(val1));
		conr_val_1.setValue(curr_.get(val1));
		conr_val_11.setValue(curr_.get(val1));
		conr_val_2.setValue(curr_.get(val2));
		conr_val_22.setValue(curr_.get(val2));
		aconr_val2.setValue(curr_.get(val2));
		aconr_val1.setValue(curr_.get(val1));
		aconr_val22.setValue(curr_.get(val2));
		aconr_val11.setValue(curr_.get(val1));
		aconr_val_1.setValue(curr_.get(val1));
		aconr_val_11.setValue(curr_.get(val1));
		aconr_val_2.setValue(curr_.get(val2));
		aconr_val_22.setValue(curr_.get(val2));
		
		line1.setValue(Labels.getLabel("movetoex.p32t40").replaceAll("<br>", "\r\n"));
		line2.setValue(Labels.getLabel("movetoex.p3t40").replaceAll("<br>", "\r\n"));
		line3.setValue(Labels.getLabel("movetoex.p4t40").replaceAll("<br>", "\r\n"));
		line4.setValue(Labels.getLabel("movetoex.p2t40tab").replaceAll("<br>", "\r\n"));
		line5.setValue(Labels.getLabel("movetoex.p11t40tab").replaceAll("<br>", "\r\n"));
		line6.setValue(Labels.getLabel("movetoex.p33t40").replaceAll("<br>", "\r\n"));
		line7.setValue(Labels.getLabel("movetoex.p34t40tab").replaceAll("<br>", "\r\n"));
		line8.setValue(Labels.getLabel("movetoex.p40t40tab").replaceAll("<br>", "\r\n"));
		line9.setValue(Labels.getLabel("movetoex.p100t40").replaceAll("<br>", "\r\n"));
		
		dataGrid.setItemRenderer(new ListitemRenderer()
		{
			@SuppressWarnings("unchecked")
			public void render(Listitem row, Object data) throws Exception
			{
				Movetoex pMovetoex = (Movetoex) data;
				
				row.setValue(pMovetoex);
				
				row.appendChild(new Listcell(pMovetoex.getP32t40() + ""));
				row.appendChild(new Listcell(pMovetoex.getP3t40() + ""));
				row.appendChild(new Listcell(pMovetoex.getP4t40()));
				row.appendChild(new Listcell(pMovetoex.getP2t40()));
				row.appendChild(new Listcell(pMovetoex.getP11t40()));
				row.appendChild(new Listcell(pMovetoex.getP33t40()));
				row.appendChild(new Listcell(pMovetoex.getP34t40()));
				row.appendChild(new Listcell(pMovetoex.getP40t40() + ""));
				row.appendChild(new Listcell(pMovetoex.getP100t40()));
				
				row.appendChild(new Listcell(pMovetoex.getId() + ""));
				row.appendChild(new Listcell(pMovetoex.getP1t40()));
				row.appendChild(new Listcell(pMovetoex.getP0t40()));
				row.appendChild(new Listcell(pMovetoex.getP5t40() + ""));
				row.appendChild(new Listcell(pMovetoex.getP6t40() + ""));
				row.appendChild(new Listcell(pMovetoex.getP7t40() + ""));
				row.appendChild(new Listcell(pMovetoex.getP8t40() + ""));
				row.appendChild(new Listcell(pMovetoex.getP9t40() + ""));
				row.appendChild(new Listcell(pMovetoex.getP10t40()));
				row.appendChild(new Listcell(pMovetoex.getP12t40()));
				row.appendChild(new Listcell(pMovetoex.getP13t40()));
				row.appendChild(new Listcell(pMovetoex.getP14t40()));
				row.appendChild(new Listcell(pMovetoex.getP15t40()));
				row.appendChild(new Listcell(pMovetoex.getP16t40()));
				row.appendChild(new Listcell(pMovetoex.getP17t40()));
				row.appendChild(new Listcell(pMovetoex.getP18t40() + ""));
				row.appendChild(new Listcell(pMovetoex.getP19t40() + ""));
				row.appendChild(new Listcell(pMovetoex.getP20t40() + ""));
				row.appendChild(new Listcell(pMovetoex.getP21t40() + ""));
				row.appendChild(new Listcell(pMovetoex.getP22t40() + ""));
				row.appendChild(new Listcell(pMovetoex.getP23t40() + ""));
				row.appendChild(new Listcell(pMovetoex.getP24t40() + ""));
				row.appendChild(new Listcell(pMovetoex.getP25t40()));
				row.appendChild(new Listcell(pMovetoex.getP26t40()));
				row.appendChild(new Listcell(pMovetoex.getP27t40()));
				row.appendChild(new Listcell(pMovetoex.getP28t40()));
				row.appendChild(new Listcell(pMovetoex.getP29t40()));
				row.appendChild(new Listcell(pMovetoex.getP30t40() + ""));
				row.appendChild(new Listcell(pMovetoex.getP31t40()));
				
			}
		});
		
		funds = com.is.tf.contract.ContractService.getFunds(idn, alias);
		agreement_curr = com.is.tf.contract.ContractService.getAgreement(idn, alias);
		
		conditions = ContractService.getConditions("1,2,3,4,5,6", alias);
		reason = ContractService.getReasons("1,4,5", alias);
		destt = com.is.tf.contract.ContractService.getFundDest("1,2", alias);
		idn_exp = com.is.tf.contract.ContractService.getIDNexpObj(inn, alias);
		accred = com.is.tf.contract.ContractService.getAccredetivs(idn, alias);
		Accred_obj = com.is.tf.contract.ContractService.getAccredObj(idn, alias);
		policy = com.is.tf.contract.ContractService.getPolicies(idn, alias);
		Curr_22t1_23t1 = com.is.tf.contract.ContractService.getCurr_22t1_23t1(idn, alias);
		garant = com.is.tf.contract.ContractService.getGarants(idn, alias);
		prodaja = com.is.tf.contract.ContractService.getProdaja();
		p2t40.setModel((new ListModelList(funds)));
		ap2t40.setModel((new ListModelList(funds)));
		p26t40.setModel((new ListModelList(agreement_curr)));
		ap26t40.setModel((new ListModelList(agreement_curr)));
		
		p25t40.setModel((new ListModelList(reason)));
		ap25t40.setModel((new ListModelList(reason)));
		p12t40.setModel((new ListModelList(destt)));
		ap12t40.setModel((new ListModelList(destt)));
		p13t40.setModel((new ListModelList(conditions)));
		ap13t40.setModel((new ListModelList(conditions)));
		p11t40.setModel((new ListModelList(idn_exp)));
		ap11t40.setModel((new ListModelList(idn_exp)));
		p14t40.setModel((new ListModelList(Accred_obj)));
		ap14t40.setModel((new ListModelList(Accred_obj)));
		p15t40.setModel((new ListModelList(garant)));
		ap15t40.setModel((new ListModelList(garant)));
		p16t40.setModel((new ListModelList(policy)));
		ap16t40.setModel((new ListModelList(policy)));
		p10t40.setModel((new ListModelList(prodaja)));
		ap10t40.setModel((new ListModelList(prodaja)));
		// ap2t40.setModel((new ListModelList(funds)));
		// p4t40.setModel((new
		// ListModelList(com.is.utils.RefDataService.getCurrency(alias))));
		// ap4t40.setModel((new
		// ListModelList(com.is.utils.RefDataService.getCurrency(alias))));
		// fp4t40.setModel((new
		// ListModelList(com.is.utils.RefDataService.getCurrency(alias))));
		System.out.println("render    fund_val " + fund_val + " = ret " + ret + "current.getP17t40() " + current.getP17t40() + " idnexp " + idnexp);
		filter.setP1t40(idn);
		refreshModel(_startPageNumber);
		
	}
	
	public void onSelect$p2t40()
	{
		Fund fund = ((Fund) p2t40.getObject());
		rr = fund.getP15t35();
		p6 = fund.getP16t35().toString();
		p7 = fund.getP17t35().toString();
		p8 = fund.getP18t35().toString();
		p9 = fund.getP19t35().toString();
		dest = fund.getP5t35();
		fund_val = fund.getP13t35();
		conr_val0.setValue(curr_.get(fund_val));
		current.setP5t40(rr);
		current.setP12t40(dest);
		{
			if (p6.equalsIgnoreCase(null) || (p6.equalsIgnoreCase("     0.00") || (p6.equalsIgnoreCase("0.00") || (p6.equalsIgnoreCase("0")))))
			{
				row_p6t40.setVisible(false);
			}
			else if (p7.equalsIgnoreCase(null) || (p7.equalsIgnoreCase("     0.00")))
			{
				row_p7t40.setVisible(false);
			}
			else if (p8.equalsIgnoreCase(null) || (p8.equalsIgnoreCase("     0.00")))
			{
				row_p8t40.setVisible(false);
			}
			else if (p9.equalsIgnoreCase(null) || (p9.equalsIgnoreCase("     0.00")))
			{
				row_p9t40.setVisible(false);
			}
			
		}
		System.out.println("onSelect$p2t40() __fund_val=" + fund_val + "current.getP17t40()=" + current.getP17t40());
		setCurrent();
	}
	
	public void onSelect$ap2t40()
	{
		Fund afund = ((Fund) ap2t40.getObject());
		rr2 = afund.getP15t35();
		dest2 = afund.getP5t35();
		afund_val2 = afund.getP13t35();
		ap5t40.setValue(rr2.toString());
		ap12t40.setValue(dest2.toString());
		ap6 = afund.getP16t35().toString();
		ap7 = afund.getP17t35().toString();
		ap8 = afund.getP18t35().toString();
		ap9 = afund.getP19t35().toString();
		ap5t40.setValue(afund.getP15t35().toString());
		aconr_val0.setValue(curr_.get(afund_val2));
		System.out.println("ap6=" + ap6 + " ap7=" + ap7 + "  ap8=" + ap8);
		ap12t40.setValue("");
		ap12t40.setValue(afund.getP5t35().toString());
		if (ap6.equalsIgnoreCase(null) || (ap6.equalsIgnoreCase("0")))
		{
			row_ap6t40.setVisible(true);
		}
		else if ((ap7 != null) && (ap7.equalsIgnoreCase("0.0")))
		{
			row_ap7t40.setVisible(true);
		}
		else if ((ap8 != null) && (CheckNull.isEmpty(ap8)))
		{
			row_ap8t40.setVisible(true);
		}
		else if ((ap9 != null) && (ap9.equalsIgnoreCase("0.0")))
		{
			row_ap9t40.setVisible(true);
		}
	}
	
	public void onSelect$p11t40()
	{
		Contract idn_exp = ((Contract) p11t40.getObject());
		idnexp = idn_exp.getP1t1();
		agreement2 = com.is.tf.contract.ContractService.getAgreement(idnexp, alias);
		p27t40.setModel((new ListModelList(agreement2)));
		System.out.println("onSelect$p11t40()     dest " + dest + "current.setP12t40  " + current.getP12t40() + "idnexp " + idnexp);
	}
	
	public void onSelect$ap11t40()
	{
		Contract aidn_exp = ((Contract) ap11t40.getObject());
		aidnexp = aidn_exp.getP1t1();
		agreement2a = com.is.tf.contract.ContractService.getAgreement(aidnexp, alias);
		ap27t40.setModel((new ListModelList(agreement2a)));
		setCurrent();
		// System.out.println("456456   "+((Fund)p2t40.getObject()).getP15t35()+"current "+current.getP2t40()+"rr "+rr);
	}
	
	public void onPaging$movetoexPaging(ForwardEvent event)
	{
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModel(_startPageNumber);
	}
	
	private void refreshModel(int activePage)
	{
		movetoexPaging.setPageSize(_pageSize);
		model = new PagingListModel(activePage, _pageSize, filter, "");
		
		if (_needsTotalSizeUpdate)
		{
			_totalSize = model.getTotalSize(filter, "");
			// _needsTotalSizeUpdate = false;
		}
		
		movetoexPaging.setTotalSize(_totalSize);
		
		dataGrid.setModel((ListModel) model);
		if (model.getSize() > 0)
		{
			this.current = (Movetoex) model.getElementAt(0);
			sendSelEvt();
		}
		
	}
	
	// Omitted...
	public Movetoex getCurrent()
	{
		return current;
	}
	
	public void setCurrent(Movetoex current)
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
				btn_save.setVisible(false);
				btn_reject.setVisible(false);
				sparam = "Filial";
				// alert(sparam1);
			}
			else if (sparam.equals("Go")) // / ГО
			{
				btn_edit.setVisible(false);
				btn_confirm.setVisible(true);
				btn_reject.setVisible(true);
				btn_save.setVisible(false);
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
		if (sparam.equals("Go")) // / ГО
		{
			btn_edit.setVisible(false);
			btn_save.setVisible(false);
			btn_confirm.setVisible(false);
			btn_reject.setVisible(false);
		}
		onDoubleClick$dataGrid$grd();
		frmgrd.setVisible(false);
		addgrd.setVisible(true);
		fgrd.setVisible(false);
		ap1t40.setValue(idn);
		ap32t40.setValue(new Date());
		idn_exp = com.is.tf.contract.ContractService.getIDNexpObj(inn, alias);
		ap11t40.setModel((new ListModelList(idn_exp)));
		
		btn_save.setVisible(true);
		btn_edit.setVisible(false);
		
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
			com.sbs.service.MoveToExResult mte = new com.sbs.service.MoveToExResult();
			
			if (addgrd.isVisible())
			{
				MoveToExResult ar = ws.saveMoveToEx(
						((String) (session.getAttribute("BankINN"))), idn
						, getMte(new Movetoex(
								ap2t40.getValue(),
								ap3t40.doubleValue(),
								// ap4t40.getValue(),
								ap5t40.doubleValue(),
								ap6t40.doubleValue(),
								ap7t40.doubleValue(),
								ap8t40.doubleValue(),
								ap9t40.doubleValue(),
								ap10t40.getValue(),
								ap11t40.getValue(),
								ap12t40.getValue(),
								ap13t40.getValue(),
								ap14t40.getValue(),
								ap15t40.getValue(),
								ap16t40.getValue(),
								ap17t40.getValue(),
								ap18t402.doubleValue(),
								ap19t40.doubleValue(),
								ap20t402.doubleValue(),
								ap21t40.doubleValue(),
								ap22t40.doubleValue(),
								ap23t40.doubleValue(),
								ap24t40.doubleValue(),
								ap25t40.getValue(),
								ap26t40.getValue(),
								ap27t40.getValue(),
								ap28t40.getValue(),
								ap29t40.getValue(),
								ap30t40.getValue(),
								ap31t40.getValue(),
								ap32t40.getValue()
						// ap33t40.getValue()
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
					System.out.println("Error:" + ar.getStatus() + "; GTKid:" + ar.getGtkId() + "; Text:" + ar.getErrorMsg());
				}
				frmgrd.setVisible(true);
				addgrd.setVisible(false);
				fgrd.setVisible(false);
				
			}
			else if (fgrd.isVisible())
			{
				filter = new MovetoexFilter();
				
				Long.parseLong(fid.getValue());
				filter.setP1t40(fp1t40.getValue());
				filter.setP0t40(fp0t40.getValue());
				filter.setP2t40(fp2t40.getValue());
				filter.setP3t40(fp3t40.doubleValue());
				filter.setP4t40(fp4t40.getValue());
				filter.setP5t40(fp5t40.doubleValue());
				filter.setP6t40(fp6t40.doubleValue());
				filter.setP7t40(fp7t40.doubleValue());
				filter.setP8t40(fp8t40.doubleValue());
				filter.setP9t40(fp9t40.doubleValue());
				filter.setP10t40(fp10t40.getValue());
				filter.setP11t40(fp11t40.getValue());
				filter.setP12t40(fp12t40.getValue());
				filter.setP13t40(fp13t40.getValue());
				filter.setP14t40(fp14t40.getValue());
				filter.setP15t40(fp15t40.getValue());
				filter.setP16t40(fp16t40.getValue());
				filter.setP17t40(fp17t40.getValue());
				filter.setP18t40(fp18t40.doubleValue());
				filter.setP19t40(fp19t40.doubleValue());
				filter.setP20t40(fp20t40.doubleValue());
				filter.setP21t40(fp21t40.doubleValue());
				filter.setP22t40(fp22t40.doubleValue());
				filter.setP23t40(fp23t40.doubleValue());
				filter.setP24t40(fp24t40.doubleValue());
				filter.setP25t40(fp25t40.getValue());
				filter.setP26t40(fp26t40.getValue());
				filter.setP27t40(fp27t40.getValue());
				filter.setP28t40(fp28t40.getValue());
				filter.setP29t40(fp29t40.getValue());
				filter.setP30t40(fp30t40.getValue());
				filter.setP31t40(fp31t40.getValue());
				filter.setP32t40(fp32t40.getValue());
				filter.setP33t40(fp33t40.getValue());
				
			}
			else
			{
				
				Long.parseLong(id.getValue());
				current.setP1t40(p1t40.getValue());
				current.setP0t40(p0t40.getValue());
				current.setP2t40(p2t40.getValue());
				current.setP3t40(p3t40.doubleValue());
				current.setP4t40(p4t40.getValue());
				current.setP5t40(p5t40.doubleValue());
				current.setP6t40(p6t40.doubleValue());
				current.setP7t40(p7t40.doubleValue());
				current.setP8t40(p8t40.doubleValue());
				current.setP9t40(p9t40.doubleValue());
				current.setP10t40(p10t40.getValue());
				current.setP11t40(p11t40.getValue());
				current.setP12t40(p12t40.getValue());
				current.setP13t40(p13t40.getValue());
				current.setP14t40(p14t40.getValue());
				current.setP15t40(p15t40.getValue());
				current.setP16t40(p16t40.getValue());
				current.setP17t40(p17t40.getValue());
				current.setP18t40(p18t40.doubleValue());
				current.setP19t40(p19t40.doubleValue());
				current.setP20t40(p20t40.doubleValue());
				current.setP21t40(p21t40.doubleValue());
				current.setP22t40(p22t40.doubleValue());
				current.setP23t40(p23t40.doubleValue());
				current.setP24t40(p24t40.doubleValue());
				current.setP25t40(p25t40.getValue());
				current.setP26t40(p26t40.getValue());
				current.setP27t40(p27t40.getValue());
				current.setP28t40(p28t40.getValue());
				current.setP29t40(p29t40.getValue());
				current.setP30t40(p30t40.getValue());
				current.setP31t40(p31t40.getValue());
				current.setP32t40(p32t40.getValue());
				current.setP33t40(p33t40.getValue());
				// MovetoexService.update(current);
				MoveToExResult ar = ws.saveMoveToEx(((String) (session.getAttribute("BankINN"))), idn, getMte(current));
				if (ar.getStatus() == 0)
				{
					refreshModel(_startPageNumber);
					alert("Сохранение успешно");
					
				}
				else
				{
					alert("Error:" + ar.getStatus() + "; GTKid:" + ar.getGtkId() + "; Text:" + ar.getErrorMsg());
					System.out.println("Error:" + ar.getStatus() + "; GTKid:" + ar.getGtkId() + "; Text:" + ar.getErrorMsg());
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
			System.out.println(e);
		}
		
	}
	
	public void onClick$btn_cancel()
	{
		if (fgrd.isVisible())
		{
			filter = new MovetoexFilter();
		}
		onClick$btn_back();
		frmgrd.setVisible(true);
		addgrd.setVisible(false);
		fgrd.setVisible(false);
		CheckNull.clearForm(addgrd);
		CheckNull.clearForm(fgrd);
		refreshModel(_startPageNumber);
	}
	
	private com.sbs.service.MoveToEx getMte(Movetoex acr) throws Exception
	{
		java.util.Calendar cal = java.util.Calendar.getInstance();
		java.util.Calendar cal2 = java.util.Calendar.getInstance();
		com.sbs.service.MoveToEx res = new com.sbs.service.MoveToEx();
		
		res.setP0T40(0);
		res.setP2T40(Integer.parseInt(acr.getP2t40()));
		res.setP3T40(acr.getP3t40());
		res.setP5T40(acr.getP5t40());
		res.setP6T40(acr.getP6t40());
		res.setP7T40(acr.getP7t40());
		res.setP8T40(acr.getP8t40());
		res.setP9T40(acr.getP9t40());
		res.setP10T40(Short.parseShort(acr.getP10t40()));
		res.setP11T40(acr.getP11t40());
		if ((acr.getP12t40() != null) && (!CheckNull.isEmpty(acr.getP12t40())))
		{
			res.setP12T40(Short.parseShort(acr.getP12t40()));
		}
		if ((acr.getP13t40() != null) && (!CheckNull.isEmpty(acr.getP13t40())))
		{
			res.setP13T40(Short.parseShort(acr.getP13t40()));
		}
		if ((acr.getP14t40() != null) && (!CheckNull.isEmpty(acr.getP14t40())))
		{
			res.setP14T40(acr.getP14t40());
		}
		if ((acr.getP15t40() != null) && (!CheckNull.isEmpty(acr.getP15t40())))
		{
			res.setP15T40(acr.getP15t40());
		}
		if ((acr.getP16t40() != null) && (!CheckNull.isEmpty(acr.getP16t40())))
		{
			res.setP16T40(acr.getP16t40());
		}
		if ((acr.getP17t40() != null) && (!CheckNull.isEmpty(acr.getP17t40())))
		{
			res.setP17T40(acr.getP17t40());
		}
		if ((acr.getP18t40() != null) && (!CheckNull.isEmpty(acr.getP18t40())))
		{
			res.setP18T40(acr.getP18t40());
		}
		if ((acr.getP19t40() != null) && (!CheckNull.isEmpty(acr.getP19t40())))
		{
			res.setP19T40(acr.getP19t40());
		}
		if ((acr.getP20t40() != null) && (!CheckNull.isEmpty(acr.getP20t40())))
		{
			res.setP20T40(acr.getP20t40());
		}
		res.setP21T40(acr.getP21t40());
		res.setP22T40(acr.getP22t40());
		res.setP23T40(acr.getP23t40());
		res.setP24T40(acr.getP24t40());
		if ((acr.getP25t40() != null) && (!CheckNull.isEmpty(acr.getP25t40())))
		{
			res.setP25T40(Short.parseShort(acr.getP25t40()));
		}
		if ((acr.getP26t40() != null) && (!CheckNull.isEmpty(acr.getP26t40())))
		{
			res.setP26T40(acr.getP26t40());
		}
		if ((acr.getP14t40() != null) && (!CheckNull.isEmpty(acr.getP14t40())))
		{
			res.setP27T40(acr.getP27t40());
		}
		if ((acr.getP28t40() != null) && (!CheckNull.isEmpty(acr.getP28t40())))
		{
			res.setP28T40(acr.getP28t40());
		}
		if ((acr.getP29t40() != null) && (!CheckNull.isEmpty(acr.getP29t40())))
		{
			res.setP29T40(acr.getP29t40());
		}
		if ((acr.getP30t40() != null) && (!CheckNull.isEmpty(acr.getP30t40())))
		{
			cal.setTime(df.parse(df.format(acr.getP30t40())));
			res.setP30T40(cal);
		}
		res.setP31T40(acr.getP31t40());
		cal2.setTime(df.parse(df.format(acr.getP32t40())));
		res.setP32T40(cal2);
		// res.setP33T40(Integer.parseInt(acr.getP33t40()));
		res.setP34T40((String) session.getAttribute("un"));
		res.setP37T40(Short.parseShort("0"));
		return res;
		
	}
	
	private com.sbs.service.MoveToEx getMteCorrect(Movetoex acr) throws Exception
	{
		java.util.Calendar cal = java.util.Calendar.getInstance();
		java.util.Calendar cal2 = java.util.Calendar.getInstance();
		com.sbs.service.MoveToEx res = new com.sbs.service.MoveToEx();
		
		res.setP0T40(1);
		res.setP2T40(Integer.parseInt(acr.getP2t40()));
		res.setP3T40(acr.getP3t40());
		res.setP5T40(acr.getP5t40());
		res.setP6T40(acr.getP6t40());
		res.setP7T40(acr.getP7t40());
		res.setP8T40(acr.getP8t40());
		res.setP9T40(acr.getP9t40());
		res.setP10T40(Short.parseShort(acr.getP10t40()));
		res.setP11T40(acr.getP11t40());
		if ((acr.getP12t40() != null) && (!CheckNull.isEmpty(acr.getP12t40())))
		{
			res.setP12T40(Short.parseShort(acr.getP12t40()));
		}
		if ((acr.getP13t40() != null) && (!CheckNull.isEmpty(acr.getP13t40())))
		{
			res.setP13T40(Short.parseShort(acr.getP13t40()));
		}
		if ((acr.getP14t40() != null) && (!CheckNull.isEmpty(acr.getP14t40())))
		{
			res.setP14T40(acr.getP14t40());
		}
		if ((acr.getP15t40() != null) && (!CheckNull.isEmpty(acr.getP15t40())))
		{
			res.setP15T40(acr.getP15t40());
		}
		if ((acr.getP16t40() != null) && (!CheckNull.isEmpty(acr.getP16t40())))
		{
			res.setP16T40(acr.getP16t40());
		}
		if ((acr.getP17t40() != null) && (!CheckNull.isEmpty(acr.getP17t40())))
		{
			res.setP17T40(acr.getP17t40());
		}
		if ((acr.getP18t40() != null) && (!CheckNull.isEmpty(acr.getP18t40())))
		{
			res.setP18T40(acr.getP18t40());
		}
		if ((acr.getP19t40() != null) && (!CheckNull.isEmpty(acr.getP19t40())))
		{
			res.setP19T40(acr.getP19t40());
		}
		if ((acr.getP20t40() != null) && (!CheckNull.isEmpty(acr.getP20t40())))
		{
			res.setP20T40(acr.getP20t40());
		}
		res.setP21T40(acr.getP21t40());
		res.setP22T40(acr.getP22t40());
		res.setP23T40(acr.getP23t40());
		res.setP24T40(acr.getP24t40());
		if ((acr.getP25t40() != null) && (!CheckNull.isEmpty(acr.getP25t40())))
		{
			res.setP25T40(Short.parseShort(acr.getP25t40()));
		}
		if ((acr.getP26t40() != null) && (!CheckNull.isEmpty(acr.getP26t40())))
		{
			res.setP26T40(acr.getP26t40());
		}
		if ((acr.getP14t40() != null) && (!CheckNull.isEmpty(acr.getP14t40())))
		{
			res.setP27T40(acr.getP27t40());
		}
		if ((acr.getP28t40() != null) && (!CheckNull.isEmpty(acr.getP28t40())))
		{
			res.setP28T40(acr.getP28t40());
		}
		if ((acr.getP29t40() != null) && (!CheckNull.isEmpty(acr.getP29t40())))
		{
			res.setP29T40(acr.getP29t40());
		}
		if ((acr.getP30t40() != null) && (!CheckNull.isEmpty(acr.getP30t40())))
		{
			cal.setTime(df.parse(df.format(acr.getP30t40())));
			res.setP30T40(cal);
		}
		res.setP31T40(acr.getP31t40());
		cal2.setTime(df.parse(df.format(acr.getP32t40())));
		res.setP32T40(cal2);
		res.setP33T40(Integer.parseInt(acr.getP33t40()));
		res.setP34T40((String) session.getAttribute("un"));
		res.setP37T40(Short.parseShort("0"));
		return res;
		
	}
	
	public void onSelect$p13t40()
	{
		if (p13t40.getValue().equalsIgnoreCase("2"))
		{
			
			row_p14t40.setVisible(true);
			row_p15t40.setVisible(false);
			row_p16t40.setVisible(false);
		}
		else if (p13t40.getValue().equalsIgnoreCase("3"))
		{
			p17t40.setModel((new ListModelList(Curr_22t1_23t1)));
			// p17t40.setValue("");
			// p14t40.setValue("");
			row_p14t40.setVisible(false);
			row_p15t40.setVisible(true);
			row_p16t40.setVisible(false);
		}
		else if (p13t40.getValue().equalsIgnoreCase("4"))
		{
			p17t40.setModel((new ListModelList(Curr_22t1_23t1)));
			// p17t40.setValue("");
			// p14t40.setValue("");
			row_p14t40.setVisible(false);
			row_p15t40.setVisible(false);
			row_p16t40.setVisible(true);
		}
		else if (p13t40.getValue().equalsIgnoreCase("1"))
		{
			p17t40.setModel((new ListModelList(Curr_22t1_23t1)));
			// p17t40.setValue("");
			// p14t40.setValue("");
			p17t40.setDisabled(false);
			row_p14t40.setVisible(false);
			row_p15t40.setVisible(false);
			row_p16t40.setVisible(false);
		}
		else if (p13t40.getValue().equalsIgnoreCase("5"))
		{
			p17t40.setModel((new ListModelList(Curr_22t1_23t1)));
			// p17t40.setValue("");
			// p14t40.setValue("");
			p17t40.setDisabled(false);
			row_p14t40.setVisible(false);
			row_p15t40.setVisible(false);
			row_p16t40.setVisible(false);
		}
		else if (p13t40.getValue().equalsIgnoreCase("6"))
		{
			p17t40.setModel((new ListModelList(Curr_22t1_23t1)));
			// p17t40.setValue("");
			// p14t40.setValue("");
			p17t40.setDisabled(false);
			row_p14t40.setVisible(false);
			row_p15t40.setVisible(false);
			row_p16t40.setVisible(false);
		}
	}
	
	public void onSelect$ap13t40()
	{
		if (ap13t40.getValue().equalsIgnoreCase("2"))
		{
			row_ap14t40.setVisible(true);
			row_ap15t40.setVisible(false);
			row_ap16t40.setVisible(false);
		}
		else if (ap13t40.getValue().equalsIgnoreCase("3"))
		{
			ap17t40.setModel((new ListModelList(Curr_22t1_23t1)));
			row_ap14t40.setVisible(false);
			row_ap15t40.setVisible(true);
			row_ap16t40.setVisible(false);
		}
		else if (ap13t40.getValue().equalsIgnoreCase("4"))
		{
			ap17t40.setModel((new ListModelList(Curr_22t1_23t1)));
			row_ap14t40.setVisible(false);
			row_ap15t40.setVisible(false);
			row_ap16t40.setVisible(true);
		}
		else if (ap13t40.getValue().equalsIgnoreCase("1"))
		{
			ap17t40.setModel((new ListModelList(Curr_22t1_23t1)));
			ap17t40.setValue("");
			ap14t40.setValue("");
			ap17t40.setDisabled(false);
			row_ap14t40.setVisible(false);
			row_ap15t40.setVisible(false);
			row_ap16t40.setVisible(false);
		}
		else if (ap13t40.getValue().equalsIgnoreCase("5"))
		{
			ap17t40.setModel((new ListModelList(Curr_22t1_23t1)));
			ap17t40.setValue("");
			ap14t40.setValue("");
			ap17t40.setDisabled(false);
			row_ap14t40.setVisible(false);
			row_ap15t40.setVisible(false);
			row_ap16t40.setVisible(false);
		}
		else if (ap13t40.getValue().equalsIgnoreCase("6"))
		{
			ap17t40.setModel((new ListModelList(Curr_22t1_23t1)));
			ap17t40.setValue("");
			ap14t40.setValue("");
			ap17t40.setDisabled(false);
			row_ap14t40.setVisible(false);
			row_ap15t40.setVisible(false);
			row_ap16t40.setVisible(false);
		}
	}
	
	public void onSelect$p25t40()
	{
		if (p25t40.getValue().equalsIgnoreCase("1"))
		{
			row_p26t40.setVisible(false);
			row_p27t40.setVisible(false);
			row_p28t40.setVisible(true);
			row_p29t40.setVisible(true);
			row_p30t40.setVisible(true);
			row_p31t40.setVisible(false);
			
		}
		else if (p25t40.getValue().equalsIgnoreCase("4"))
		{
			row_p26t40.setVisible(true);
			row_p27t40.setVisible(true);
			row_p28t40.setVisible(false);
			row_p29t40.setVisible(false);
			row_p30t40.setVisible(false);
			row_p31t40.setVisible(false);
		}
		else if (p25t40.getValue().equalsIgnoreCase("5"))
		{
			row_p26t40.setVisible(false);
			row_p27t40.setVisible(false);
			row_p28t40.setVisible(false);
			row_p29t40.setVisible(false);
			row_p30t40.setVisible(false);
			row_p31t40.setVisible(true);
		}
	}
	
	public void onSelect$ap25t40()
	{
		if (ap25t40.getValue().equalsIgnoreCase("1"))
		{
			row_ap26t40.setVisible(false);
			row_ap27t40.setVisible(false);
			row_ap28t40.setVisible(true);
			row_ap29t40.setVisible(true);
			row_ap30t40.setVisible(true);
			row_ap31t40.setVisible(false);
			
		}
		else if (ap25t40.getValue().equalsIgnoreCase("4"))
		{
			row_ap26t40.setVisible(true);
			row_ap27t40.setVisible(true);
			row_ap28t40.setVisible(false);
			row_ap29t40.setVisible(false);
			row_ap30t40.setVisible(false);
			row_ap31t40.setVisible(false);
		}
		else if (ap25t40.getValue().equalsIgnoreCase("5"))
		{
			row_ap26t40.setVisible(false);
			row_ap27t40.setVisible(false);
			row_ap28t40.setVisible(false);
			row_ap29t40.setVisible(false);
			row_ap30t40.setVisible(false);
			row_ap31t40.setVisible(true);
		}
	}
	
	public void onSelect$p14t40()
	{
		AcrCurr = ((Accreditiv) p14t40.getObject()).getP4t21();
		if (p13t40.getValue().equalsIgnoreCase("2"))
		{
			// p17t40.setValue(AcrCurr);
			p17t40.setDisabled(true);
			p17t40.setValue(curr_.get(AcrCurr));
			System.out.println("onSelect$p14t40()  fund_val=" + fund_val + " - " + "current.getP17t40()=" + current.getP17t40() + "  AcrCurr =" + (curr_.get(AcrCurr)));
		}
	}
	
	public void onSelect$ap14t40()
	{
		AcrCurr = ((Accreditiv) ap14t40.getObject()).getP4t21();
		if (ap13t40.getValue().equalsIgnoreCase("2"))
		{
			// p17t40.setValue(AcrCurr);
			ap17t40.setDisabled(true);
			ap17t40.setValue(curr_.get(AcrCurr));
			// System.out.println("1 fund_val "+fund_val+"="+"current.getP17t40() "+current.getP17t40()+
			// " ++" +p17t40.getValue());
		}
	}
	
	public void onChange$p17t40()
	{
		// System.out.println("2 fund_val "+fund_val+"="+"current.getP17t40() "+current.getP17t40()+
		// "++ " +p17t40.getValue());
		current.setP17t40(p17t40.getValue());
		if (current.getP17t40().equalsIgnoreCase(fund_val))
		{
			row_p18t40.setVisible(true);
		}
		else
		{
			row_p18t40.setVisible(true);
		}
		System.out.println("onChange$p17t40() fund_val= " + fund_val + " p17t40.getValue()= " + p17t40.getValue());
		
	}
	
	private void setCurrent()
	{
		if (current != null)
		{
			
			ret = current.getP17t40();
			System.out.println("setCurrent() fund_val= " + fund_val + "  ret= " + ret + " current.getP17t40()= " + current.getP17t40());
			currenciesg = com.is.tf.contract.ContractService.getMyCurr2only(fund_val, ret, alias);
			currenciesg2 = com.is.tf.contract.ContractService.getCourseCurr_18t1_19t1_withOther_sysdate(idn, idc, current.getP17t40(), alias);
			p18t401.setModel((new ListModelList(currenciesg)));
			ap18t401.setModel((new ListModelList(currenciesg)));
			p18t403.setModel((new ListModelList(currenciesg)));
			ap18t403.setModel((new ListModelList(currenciesg)));
			p20t401.setModel((new ListModelList(currenciesg2)));
			ap20t401.setModel((new ListModelList(currenciesg2)));
			p20t403.setModel((new ListModelList(currenciesg2)));
			ap20t403.setModel((new ListModelList(currenciesg2)));
			
			// p5t201.setModel((new ListModelList(currenciesg)));
			// p5t203.setModel((new ListModelList(currenciesg)));
			if (currenciesg.size() > 0)
			{
				if (CheckNull.isEmpty(current.getP17t40())
						|| (val1.equalsIgnoreCase("000") ? "860" : val1).equalsIgnoreCase(val2.equalsIgnoreCase("000") ? "860" : val2))
				{
					// ||
					// current.getP5t18().equalsIgnoreCase(currenciesg.get(0).getKod())
					row_p18t40.setVisible(false);
				}
				else
				{
					row_p18t40.setVisible(true);
					// p18t403.setSelecteditem(current.getP5t18());
					// onSelect$p5t18();
				}
			}
			if (currenciesg2.size() > 0)
			{
				if (CheckNull.isEmpty(current.getP17t40())
						|| (val1.equalsIgnoreCase("000") ? "860" : val1).equalsIgnoreCase(val2.equalsIgnoreCase("000") ? "860" : val2))
				{
					// ||
					// current.getP5t18().equalsIgnoreCase(currenciesg.get(0).getKod())
					row_p20t40.setVisible(false);
				}
				else
				{
					row_p20t40.setVisible(true);
					// p18t403.setSelecteditem(current.getP5t18());
					// onSelect$p5t18();
				}
			}
		}
	}
	
	public void onInitRenderLater$p18t403()
	{
		p18t403.setSelectedIndex(currenciesg.size() - 1);
		// p5t201.setSelecteditem(p18t401.getValue());
		// p5t203.setSelecteditem(p18t403.getValue());
		countCourse(false);
	}
	
	public void onInitRenderLater$p20t403()
	{
		p20t403.setSelectedIndex(currenciesg.size() - 1);
		// p5t201.setSelecteditem(p18t401.getValue());
		// p5t203.setSelecteditem(p18t403.getValue());
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
	
	public void onSelect$p17t40()
	{
		countCourse(true);
	}
	
	public void onSelect$p18t401()
	{
		countCourse(true);
	}
	
	public void onClick$btn_recount()
	{
		countCourse(true);
	}
	
	private void countCourse(Boolean setp18t402)
	{
		try
		{
			if (!CheckNull.isEmpty(p18t403.getValue()) && !CheckNull.isEmpty(p18t401.getValue()))
			{
				System.out.println("***" + p18t401.getValue() + " - " + p18t403.getValue());
				if (setp18t402)
				{
					p18t402.setValue("" + (p18t401.getCourse() / p18t403.getCourse()));
					current.setP18t40((p18t401.getCourse() / p18t403.getCourse()));
				}
				cbcourse.setValue("По курсу ЦБ: " + (p18t401.getCourse() / p18t403.getCourse()));
				Boolean bool = false;
				Double db = null;
				/*
				 * if (p5t18.getValue().equalsIgnoreCase(p18t401.getValue())) {
				 * if (val1.equalsIgnoreCase(p18t401.getValue())) { db =
				 * (p8t18.doubleValue() +
				 * (p9t18.doubleValue()/p18t402.doubleValue())); bool =
				 * (p6t18.doubleValue() == db); checksum.setChecked(bool);
				 * checksum.setLabel((bool?
				 * "Сумма гарантии полность соответствует указанному курсу!"
				 * :"Сумма гарантии не соответствует указанному курсу!"
				 * )+"("+db+")"); } else if
				 * (val2.equalsIgnoreCase(p18t401.getValue())) { db =
				 * (p9t18.doubleValue() +
				 * (p8t18.doubleValue()/p18t402.doubleValue())); bool =
				 * (p6t18.doubleValue() == db); checksum.setChecked(bool);
				 * checksum.setLabel((bool?
				 * "Сумма гарантии полность соответствует указанному курсу!"
				 * :"Сумма гарантии не соответствует указанному курсу!"
				 * )+"("+db+")"); } else { checksum.setChecked(false);
				 * checksum.setLabel
				 * (("Сумма гарантии не соответствует указанному курсу!")); } }
				 * else if
				 * (p5t18.getValue().equalsIgnoreCase(p18t403.getValue())) { if
				 * (val1.equalsIgnoreCase(p18t403.getValue())) { db =
				 * (p8t18.doubleValue() +
				 * (p9t18.doubleValue()*p18t402.doubleValue())); bool =
				 * (p6t18.doubleValue() == db); checksum.setChecked(bool);
				 * checksum.setLabel((bool?
				 * "Сумма гарантии полность соответствует указанному курсу!"
				 * :"Сумма гарантии не соответствует указанному курсу!"
				 * )+"("+db+")"); } else if
				 * (val2.equalsIgnoreCase(p18t403.getValue())) { db =
				 * (p9t18.doubleValue() +
				 * (p8t18.doubleValue()*p18t402.doubleValue())); bool =
				 * (p6t18.doubleValue() == db); checksum.setChecked(bool);
				 * checksum.setLabel((bool?
				 * "Сумма гарантии полность соответствует указанному курсу!"
				 * :"Сумма гарантии не соответствует указанному курсу!"
				 * )+"("+db+")"); } else { checksum.setChecked(false);
				 * checksum.setLabel
				 * (("Сумма гарантии не соответствует указанному курсу!")); } }
				 * else { checksum.setChecked(false); }
				 */

			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void countCourse2(Boolean setp20t402)
	{
		try
		{
			if (!CheckNull.isEmpty(p20t403.getValue()) && !CheckNull.isEmpty(p20t401.getValue()))
			{
				System.out.println("***" + p20t401.getValue() + " - " + p20t403.getValue());
				if (setp20t402)
				{
					p20t402.setValue("" + (p20t401.getCourse() / p20t403.getCourse()));
					current.setP20t40((p20t401.getCourse() / p20t403.getCourse()));
				}
				cbcourse.setValue("По курсу ЦБ: " + (p20t401.getCourse() / p20t403.getCourse()));
				Boolean bool = false;
				Double db = null;
				
				/*
				 * if (p5t18.getValue().equalsIgnoreCase(p18t401.getValue())) {
				 * if (val1.equalsIgnoreCase(p18t401.getValue())) { db =
				 * (p8t18.doubleValue() +
				 * (p9t18.doubleValue()/p18t402.doubleValue())); bool =
				 * (p6t18.doubleValue() == db); checksum.setChecked(bool);
				 * checksum.setLabel((bool?
				 * "Сумма гарантии полность соответствует указанному курсу!"
				 * :"Сумма гарантии не соответствует указанному курсу!"
				 * )+"("+db+")"); } else if
				 * (val2.equalsIgnoreCase(p18t401.getValue())) { db =
				 * (p9t18.doubleValue() +
				 * (p8t18.doubleValue()/p18t402.doubleValue())); bool =
				 * (p6t18.doubleValue() == db); checksum.setChecked(bool);
				 * checksum.setLabel((bool?
				 * "Сумма гарантии полность соответствует указанному курсу!"
				 * :"Сумма гарантии не соответствует указанному курсу!"
				 * )+"("+db+")"); } else { checksum.setChecked(false);
				 * checksum.setLabel
				 * (("Сумма гарантии не соответствует указанному курсу!")); } }
				 * else if
				 * (p5t18.getValue().equalsIgnoreCase(p18t403.getValue())) { if
				 * (val1.equalsIgnoreCase(p18t403.getValue())) { db =
				 * (p8t18.doubleValue() +
				 * (p9t18.doubleValue()*p18t402.doubleValue())); bool =
				 * (p6t18.doubleValue() == db); checksum.setChecked(bool);
				 * checksum.setLabel((bool?
				 * "Сумма гарантии полность соответствует указанному курсу!"
				 * :"Сумма гарантии не соответствует указанному курсу!"
				 * )+"("+db+")"); } else if
				 * (val2.equalsIgnoreCase(p18t403.getValue())) { db =
				 * (p9t18.doubleValue() +
				 * (p8t18.doubleValue()*p18t402.doubleValue())); bool =
				 * (p6t18.doubleValue() == db); checksum.setChecked(bool);
				 * checksum.setLabel((bool?
				 * "Сумма гарантии полность соответствует указанному курсу!"
				 * :"Сумма гарантии не соответствует указанному курсу!"
				 * )+"("+db+")"); } else { checksum.setChecked(false);
				 * checksum.setLabel
				 * (("Сумма гарантии не соответствует указанному курсу!")); } }
				 * else { checksum.setChecked(false); }
				 */

			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
}
