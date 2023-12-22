package com.is.tf.sale;

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

import com.is.tf.contract.ContractService;
import com.is.user.User;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.RefData;
import com.is.utils.refobj.RefObjCBox;
import com.is.utils.refobj.RefObjData;
import com.sbs.service.BankServiceProxy;
import com.sbs.service.SaleResult;

public class SaleViewCtrl extends GenericForwardComposer
{
	private User currusr;
	private Div frm;
	private Listbox dataGrid;
	private Paging contactPaging;
	private Div grd;
	private Grid addgrd, addgrd2, frmgrd, fgrd;
	private Toolbarbutton btn_last;
	private Toolbarbutton btn_next;
	private Toolbarbutton btn_prev;
	private Toolbarbutton btn_first;
	private Toolbarbutton btn_add;
	private Toolbarbutton btn_search;
	private Toolbarbutton btn_back, btn_sale;
	private Toolbar tb;
	private Textbox p27t43, id, p1t43, p0t43, p3t43, p10t43, p15t43, p18t43, p19t43, p21t43, p26t43;
	private Textbox aap27t43, ap27t43, aid, ap1t43, ap0t43, ap3t43, ap10t43, ap15t43, ap18t43, ap19t43, ap21t43, ap26t43;
	private Textbox fid, fp1t43, fp0t43, fp2t43, fp3t43, fp7t43, fp10t43, fp11t43, fp12t43, fp13t43, fp14t43, fp15t43, fp16t43, fp17t43, fp18t43, fp19t43, fp21t43, fp26t43;
	private Datebox p4t43, p20t43, p25t43, ap4t43, fp4t43, ap20t43, ap25t43, fp20t43, fp25t43;
	private Decimalbox p6t43, p9t43, p22t43, p23t43, p24t43, ap6t43, ap9t43, ap22t43, ap23t43, ap24t43, fp6t43, fp9t43, fp22t43, fp23t43, fp24t43;
	private RefCBox aap13t43, p17t43, ap17t43, p7t43, ap7t43, p16t43, ap16t43, p14t43, ap14t43, p13t43, ap13t43, p11t43, ap11t43, p2t43, ap2t43, p5t43, p8t43, ap5t43, ap8t43, fp5t43, fp8t43;
	private RefObjCBox aap12t43, p12t43, ap12t43;
	private Row row_ap3t43, row_ap4t43, row_ap5t43, row_ap6t43, row_ap7t43, row_ap11t43, row_ap13t43, row_ap14t43, row_ap15t43, row_ap16t43, row_ap17t43, row_ap18t43, row_ap19t43, row_ap21t43, row_ap22t43, row_ap23t43, row_ap24t43, row_p3t43,
			row_p4t43, row_p5t43, row_p6t43, row_p7t43, row_p11t43, row_p13t43, row_p14t43, row_p15t43, row_p16t43, row_p17t43, row_p18t43, row_p19t43, row_p21t43, row_p22t43, row_p23t43, row_p24t43;
	private Paging salePaging;
	private int _pageSize = 15;
	private int _startPageNumber = 0;
	private int _totalSize = 0;
	private boolean _needsTotalSizeUpdate = true;
	private String fund_val, afund_val, idn, idc, val1, val2;
	private HashMap<String, String> curr_ = null;
	private List<RefObjData> funds = new ArrayList<RefObjData>();
	private List<RefObjData> afunds = new ArrayList<RefObjData>();
	private List<RefObjData> aafunds = new ArrayList<RefObjData>();
	private List<RefObjData> accred = new ArrayList<RefObjData>();
	private List<RefObjData> garant = new ArrayList<RefObjData>();
	private List<RefObjData> policy = new ArrayList<RefObjData>();
	private List<RefObjData> apolicy = new ArrayList<RefObjData>();
	private List<RefObjData> paymref = new ArrayList<RefObjData>();
	private List<RefObjData> MoveFromEx = new ArrayList<RefObjData>();
	private List<RefObjData> aMoveFromEx = new ArrayList<RefObjData>();
	private List<RefData> PrSourse = new ArrayList<RefData>();
	private List<RefData> Salereason = new ArrayList<RefData>();
	private List<RefData> benefitinfo = new ArrayList<RefData>();
	private List<RefData> benefitinfo2 = new ArrayList<RefData>();
	private List<RefData> basic = new ArrayList<RefData>();
	private List<RefData> gnk = new ArrayList<RefData>();
	private List<RefData> benefittype = new ArrayList<RefData>();
	private List<RefData> prodaga_type = new ArrayList<RefData>();
	private String alias;
	private Label line1;
	private Label line2;
	private Label line3;
	private Label line4;
	private Label line5;
	private Label line6;
	private Label line7;
	private Label line8, line9, line10, line11, line12;
	private String val;
	
	public SaleFilter filter = new SaleFilter();
	
	PagingListModel model = null;
	ListModelList lmodel = null;
	private AnnotateDataBinder binder;
	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	
	private Sale current = new Sale();
	
	public SaleViewCtrl()
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
			System.out.println("Garant  cont_idn " + idn);
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
			idc = (parameter[0]);
			// System.out.println("Garant  cont_val2 "+val2);
		}
		curr_ = com.is.tf.contract.ContractService.getHCurr(alias);
		filter.setP1t43(idn);
		
		// alias= (String) session.getAttribute("alias");
		val = val1;
		line1.setValue(Labels.getLabel("sale.p25t43t").replaceAll("<br>", "\r\n"));
		line2.setValue(Labels.getLabel("sale.p12t43t").replaceAll("<br>", "\r\n"));
		line3.setValue(Labels.getLabel("sale.p28t43t").replaceAll("<br>", "\r\n"));
		line4.setValue(Labels.getLabel("sale.p29t43t").replaceAll("<br>", "\r\n"));
		line5.setValue(Labels.getLabel("sale.p2t43t").replaceAll("<br>", "\r\n"));
		line6.setValue(Labels.getLabel("sale.p13t43t").replaceAll("<br>", "\r\n"));
		line7.setValue(Labels.getLabel("sale.p24t43t").replaceAll("<br>", "\r\n"));
		line8.setValue(Labels.getLabel("sale.val").replaceAll("<br>", "\r\n"));
		line9.setValue(Labels.getLabel("sale.p26t43t").replaceAll("<br>", "\r\n"));
		line10.setValue(Labels.getLabel("sale.p27t43t").replaceAll("<br>", "\r\n"));
		line11.setValue(Labels.getLabel("sale.p32t43t").replaceAll("<br>", "\r\n"));
		line12.setValue(Labels.getLabel("sale.p100t43t").replaceAll("<br>", "\r\n"));
		
		dataGrid.setItemRenderer(new ListitemRenderer()
		{
			@SuppressWarnings("unchecked")
			public void render(Listitem row, Object data) throws Exception
			{
				Sale pSale = (Sale) data;
				
				row.setValue(pSale);
				row.appendChild(new Listcell(pSale.getP25t43() + ""));
				row.appendChild(new Listcell(pSale.getP12t43()));
				row.appendChild(new Listcell(pSale.getP28t43() + ""));
				row.appendChild(new Listcell(pSale.getP29t43()));
				row.appendChild(new Listcell(pSale.getP2t43()));
				row.appendChild(new Listcell(pSale.getP13t43()));
				row.appendChild(new Listcell(pSale.getP24t43() + ""));
				row.appendChild(new Listcell(com.is.tf.contract.SPR.getVal(val + "")));
				row.appendChild(new Listcell(pSale.getP26t43()));
				row.appendChild(new Listcell(pSale.getP27t43()));
				row.appendChild(new Listcell(pSale.getP32t43() + ""));
				row.appendChild(new Listcell(com.is.tf.contract.SPR.getP100Value(pSale.getP100t43())));
				/*
				 * row.appendChild(new Listcell(pSale.getId()+""));
				 * row.appendChild(new Listcell(pSale.getP1t43()));
				 * row.appendChild(new Listcell(pSale.getP0t43()));
				 * row.appendChild(new Listcell(pSale.getP3t43()));
				 * row.appendChild(new Listcell(pSale.getP4t43()+""));
				 * row.appendChild(new Listcell(pSale.getP5t43()));
				 * row.appendChild(new Listcell(pSale.getP6t43()+""));
				 * row.appendChild(new Listcell(pSale.getP7t43()));
				 * row.appendChild(new Listcell(pSale.getP8t43()));
				 * row.appendChild(new Listcell(pSale.getP9t43()+""));
				 * row.appendChild(new Listcell(pSale.getP10t43()));
				 * row.appendChild(new Listcell(pSale.getP11t43()));
				 * row.appendChild(new Listcell(pSale.getP14t43()));
				 * row.appendChild(new Listcell(pSale.getP15t43()));
				 * row.appendChild(new Listcell(pSale.getP16t43()));
				 * row.appendChild(new Listcell(pSale.getP17t43()));
				 * row.appendChild(new Listcell(pSale.getP18t43()));
				 * row.appendChild(new Listcell(pSale.getP19t43()));
				 * row.appendChild(new Listcell(pSale.getP20t43()+""));
				 * row.appendChild(new Listcell(pSale.getP21t43()));
				 * row.appendChild(new Listcell(pSale.getP22t43()+""));
				 * row.appendChild(new Listcell(pSale.getP23t43()+""));
				 */

			}
		});
		prodaga_type = ContractService.getSaletype("3,4,5,7,8", alias);
		PrSourse = ContractService.getPrSourse();
		benefitinfo = ContractService.getBenefitsinfo(alias);
		benefitinfo2 = ContractService.getBenefitsinfo("1,5", alias);
		basic = ContractService.getBasis(alias);
		Salereason = ContractService.getSalereason(alias);
		gnk = ContractService.getGnk(alias);
		benefittype = ContractService.getBenefitstype(alias);
		p2t43.setModel(new ListModelList(prodaga_type));
		ap2t43.setModel(new ListModelList(prodaga_type));
		funds = com.is.tf.contract.ContractService.getFunds(idn, alias);
		aafunds = com.is.tf.contract.ContractService.getFunds(idn, alias);
		MoveFromEx = com.is.tf.contract.ContractService.getMoveFromExs2(idn, alias);
		policy = com.is.tf.contract.ContractService.getPolicy(idn, alias);
		p13t43.setModel((new ListModelList(benefitinfo)));
		aap12t43.setModel((new ListModelList(aafunds)));
		ap13t43.setModel((new ListModelList(benefitinfo)));
		aap13t43.setModel((new ListModelList(benefitinfo2)));
		p14t43.setModel((new ListModelList(benefittype)));
		ap14t43.setModel((new ListModelList(benefittype)));
		p17t43.setModel((new ListModelList(Salereason)));
		ap17t43.setModel((new ListModelList(Salereason)));
		p7t43.setModel((new ListModelList(gnk)));
		ap7t43.setModel((new ListModelList(gnk)));
		p16t43.setModel((new ListModelList(basic)));
		ap16t43.setModel((new ListModelList(basic)));
		p11t43.setModel((new ListModelList(PrSourse)));
		ap11t43.setModel((new ListModelList(PrSourse)));
		p5t43.setModel((new ListModelList(com.is.utils.RefDataService.getCurrency(alias))));
		ap5t43.setModel((new ListModelList(com.is.utils.RefDataService.getCurrency(alias))));
		
		refreshModel(_startPageNumber);
		
	}
	
	public void onPaging$salePaging(ForwardEvent event)
	{
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModel(_startPageNumber);
	}
	
	private void refreshModel(int activePage)
	{
		salePaging.setPageSize(_pageSize);
		model = new PagingListModel(activePage, _pageSize, filter, "");
		
		if (_needsTotalSizeUpdate)
		{
			_totalSize = model.getTotalSize(filter, "");
			// _needsTotalSizeUpdate = false;
		}
		
		salePaging.setTotalSize(_totalSize);
		
		dataGrid.setModel((ListModel) model);
		if (model.getSize() > 0)
		{
			this.current = (Sale) model.getElementAt(0);
			sendSelEvt();
		}
	}
	
	// Omitted...
	public Sale getCurrent()
	{
		
		return current;
	}
	
	public void setCurrent(Sale current)
	{
		this.current = current;
	}
	
	public void onDoubleClick$dataGrid$grd()
	{
		grd.setVisible(false);
		frm.setVisible(true);
		frmgrd.setVisible(true);
		addgrd2.setVisible(false);
		addgrd.setVisible(false);
		fgrd.setVisible(false);
		btn_back.setImage("/images/folder.png");
		btn_back.setLabel(Labels.getLabel("grid"));
		// setCurrent();
		
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
			setCurrent();
			
		}
		
		SelectEvent evt = new SelectEvent("onSelect", dataGrid, dataGrid.getSelectedItems());
		Events.sendEvent(evt);
	}
	
	public void onClick$btn_add()
	{
		onDoubleClick$dataGrid$grd();
		frmgrd.setVisible(false);
		addgrd.setVisible(true);
		fgrd.setVisible(false);
		addgrd2.setVisible(false);
		ap1t43.setValue(idn);
		ap25t43.setValue(new Date());
		ap27t43.setValue(session.getAttribute("un").toString());
		
	}
	
	public void onClick$btn_sale()
	{
		onDoubleClick$dataGrid$grd();
		frmgrd.setVisible(false);
		addgrd2.setVisible(true);
		addgrd.setVisible(false);
		fgrd.setVisible(false);
		
		// aap27t43.setValue(session.getAttribute("ufn").toString());
		
	}
	
	public void onClick$btn_search()
	{
		onDoubleClick$dataGrid$grd();
		frmgrd.setVisible(false);
		addgrd2.setVisible(false);
		addgrd.setVisible(false);
		fgrd.setVisible(true);
	}
	
	public void onClick$btn_save()
	{
		try
		{
			final BankServiceProxy ws = new BankServiceProxy((String) session.getAttribute("YESVO_URL"));
			com.sbs.service.SaleResult sal1 = new com.sbs.service.SaleResult();
			if (addgrd.isVisible())
			{
				SaleService.create(new Sale(

				// Long.parseLong(aid.getValue()),
				// ap1t43.getValue(),
				// ap0t43.getValue(),
						ap2t43.getValue(),
						ap3t43.getValue(),
						ap4t43.getValue(),
						ap5t43.getValue(),
						ap6t43.doubleValue(),
						ap7t43.getValue(),
						ap8t43.getValue(),
						ap9t43.doubleValue(),
						ap10t43.getValue(),
						ap11t43.getValue(),
						ap12t43.getValue(),
						ap13t43.getValue(),
						ap14t43.getValue(),
						ap15t43.getValue(),
						ap16t43.getValue(),
						ap17t43.getValue(),
						ap18t43.getValue(),
						ap19t43.getValue(),
						ap20t43.getValue(),
						ap21t43.getValue(),
						ap22t43.doubleValue(),
						ap23t43.doubleValue(),
						ap24t43.doubleValue(),
						ap25t43.getValue(),
						ap26t43.getValue()
						));
				CheckNull.clearForm(addgrd);
				frmgrd.setVisible(true);
				addgrd.setVisible(false);
				fgrd.setVisible(false);
			}
			else if (fgrd.isVisible())
			{
				filter = new SaleFilter();
				
				Long.parseLong(fid.getValue());
				filter.setP1t43(fp1t43.getValue());
				filter.setP0t43(fp0t43.getValue());
				filter.setP2t43(fp2t43.getValue());
				filter.setP3t43(fp3t43.getValue());
				filter.setP4t43(fp4t43.getValue());
				filter.setP5t43(fp5t43.getValue());
				filter.setP6t43(fp6t43.doubleValue());
				filter.setP7t43(fp7t43.getValue());
				filter.setP8t43(fp8t43.getValue());
				filter.setP9t43(fp9t43.doubleValue());
				filter.setP10t43(fp10t43.getValue());
				filter.setP11t43(fp11t43.getValue());
				filter.setP12t43(fp12t43.getValue());
				filter.setP13t43(fp13t43.getValue());
				filter.setP14t43(fp14t43.getValue());
				filter.setP15t43(fp15t43.getValue());
				filter.setP16t43(fp16t43.getValue());
				filter.setP17t43(fp17t43.getValue());
				filter.setP18t43(fp18t43.getValue());
				filter.setP19t43(fp19t43.getValue());
				filter.setP20t43(fp20t43.getValue());
				filter.setP21t43(fp21t43.getValue());
				filter.setP22t43(fp22t43.doubleValue());
				filter.setP23t43(fp23t43.doubleValue());
				filter.setP24t43(fp24t43.doubleValue());
				filter.setP25t43(fp25t43.getValue());
				filter.setP26t43(fp26t43.getValue());
				
			}
			else
			{
				
				Long.parseLong(id.getValue());
				current.setP1t43(p1t43.getValue());
				current.setP0t43(p0t43.getValue());
				current.setP2t43(p2t43.getValue());
				current.setP3t43(p3t43.getValue());
				current.setP4t43(p4t43.getValue());
				current.setP5t43(p5t43.getValue());
				current.setP6t43(p6t43.doubleValue());
				current.setP7t43(p7t43.getValue());
				current.setP8t43(p8t43.getValue());
				current.setP9t43(p9t43.doubleValue());
				current.setP10t43(p10t43.getValue());
				current.setP11t43(p11t43.getValue());
				current.setP12t43(p12t43.getValue());
				current.setP13t43(p13t43.getValue());
				current.setP14t43(p14t43.getValue());
				current.setP15t43(p15t43.getValue());
				current.setP16t43(p16t43.getValue());
				current.setP17t43(p17t43.getValue());
				current.setP18t43(p18t43.getValue());
				current.setP19t43(p19t43.getValue());
				current.setP20t43(p20t43.getValue());
				current.setP21t43(p21t43.getValue());
				current.setP22t43(p22t43.doubleValue());
				current.setP23t43(p23t43.doubleValue());
				current.setP24t43(p24t43.doubleValue());
				current.setP25t43(p25t43.getValue());
				current.setP26t43(p26t43.getValue());
				// current.setP27t43((String)session.getAttribute("ufn"));
				// p27t43.setValue((String)session.getAttribute("ufn"));
				// SaleService.update(current);
				SaleResult sal = ws.saveSale(((String) (session.getAttribute("BankINN"))), idn, getSal(current));
				if (sal.getStatus() == 0)
				{
					refreshModel(_startPageNumber);
					alert("Сохранение успешно");
					
				}
				else
				{
					alert("Error:" + sal.getStatus() + "; GTKid:" + sal.getGtkId() + "; Text:" + sal.getErrorMsg());
					
				}
				onClick$btn_back();
				refreshModel(_startPageNumber);
				SelectEvent evt = new SelectEvent("onSelect", dataGrid, dataGrid.getSelectedItems());
				Events.sendEvent(evt);
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
			filter = new SaleFilter();
		}
		onClick$btn_back();
		frmgrd.setVisible(true);
		addgrd.setVisible(false);
		fgrd.setVisible(false);
		CheckNull.clearForm(addgrd);
		CheckNull.clearForm(fgrd);
		refreshModel(_startPageNumber);
	}
	
	private com.sbs.service.Sale getSal(Sale sal) throws Exception
	{
		java.util.Calendar cal = java.util.Calendar.getInstance();
		java.util.Calendar cal2 = java.util.Calendar.getInstance();
		java.util.Calendar cal3 = java.util.Calendar.getInstance();
		com.sbs.service.Sale res = new com.sbs.service.Sale();
		res.setP0T43(Integer.parseInt(sal.getP0t43()));
		res.setP2T43(Short.parseShort(sal.getP2t43()));
		res.setP3T43(sal.getP3t43());
		cal.setTime(df.parse(df.format(sal.getP4t43())));
		res.setP4T43(cal);
		res.setP5T43(sal.getP5t43());
		res.setP6T43(sal.getP6t43());
		res.setP7T43(sal.getP7t43());
		// res.setP8T43(sal.getP8t43());
		// res.setP9T43(sal.getP9t43());
		// res.setP10T43(sal.getP10t43());
		res.setP11T43(Short.parseShort(sal.getP11t43()));
		res.setP12T43(Integer.parseInt(sal.getP12t43()));
		res.setP13T43(Short.parseShort(sal.getP13t43()));
		res.setP14T43(Short.parseShort(sal.getP14t43()));
		res.setP15T43(sal.getP15t43());
		// res.setP16T43 (Short.parseShort(sal.getP16t43()));
		res.setP17T43(Short.parseShort(sal.getP17t43()));
		res.setP18T43(sal.getP18t43());
		res.setP19T43(sal.getP19t43());
		cal2.setTime(df.parse(df.format(sal.getP20t43())));
		res.setP20T43(cal2);
		res.setP21T43(sal.getP21t43());
		res.setP22T43(sal.getP22t43());
		res.setP23T43(sal.getP23t43());
		res.setP24T43(sal.getP24t43());
		cal3.setTime(df.parse(df.format(sal.getP25t43())));
		res.setP25T43(cal3);
		res.setP26T43(Integer.parseInt(sal.getP26t43()));
		res.setP27T43((String) session.getAttribute("ufn"));
		
		return res;
	}
	
	public void onSelect$p11t43()
	{
		funds = com.is.tf.contract.ContractService.getFunds(idn, alias);
		MoveFromEx = com.is.tf.contract.ContractService.getMoveFromExs2(idn, alias);
		policy = com.is.tf.contract.ContractService.getPolicy(idn, alias);
		if (p11t43.getValue().equalsIgnoreCase("1"))
		{
			p12t43.setModel(new ListModelList(funds));
		}
		else if (p11t43.getValue().equalsIgnoreCase("2"))
		{
			p12t43.setModel(new ListModelList(MoveFromEx));
			
		}
		else if (p11t43.getValue().equalsIgnoreCase("3"))
		{
			p12t43.setModel(new ListModelList(policy));
		}
	}
	
	public void onSelect$ap11t43()
	{
		afunds = com.is.tf.contract.ContractService.getFunds(idn, alias);
		aMoveFromEx = com.is.tf.contract.ContractService.getMoveFromExs2(idn, alias);
		apolicy = com.is.tf.contract.ContractService.getPolicy(idn, alias);
		if (ap11t43.getValue().equalsIgnoreCase("1"))
		{
			ap12t43.setModel(new ListModelList(afunds));
		}
		else if (ap11t43.getValue().equalsIgnoreCase("2"))
		{
			ap12t43.setModel(new ListModelList(aMoveFromEx));
			
		}
		else if (ap11t43.getValue().equalsIgnoreCase("3"))
		{
			ap12t43.setModel(new ListModelList(apolicy));
		}
	}
	
	public void onSelect$p2t43()
	{
		if (p2t43.getValue().equalsIgnoreCase("5"))
		{ // / по требовиню налоговой
			row_p3t43.setVisible(true);
			row_p4t43.setVisible(true);
			row_p5t43.setVisible(true);
			row_p6t43.setVisible(true);
			row_p7t43.setVisible(true);
			row_p11t43.setVisible(false);
			row_p13t43.setVisible(false);
			row_p23t43.setVisible(false);
			
		}
		else if ((p2t43.getValue().equalsIgnoreCase("3")))
		{ // /50%
			row_p3t43.setVisible(false);
			row_p4t43.setVisible(false);
			row_p5t43.setVisible(false);
			row_p6t43.setVisible(false);
			row_p7t43.setVisible(false);
			row_p13t43.setVisible(true);
			row_p11t43.setVisible(true);
			row_p23t43.setVisible(false);
		}
		else if ((p2t43.getValue().equalsIgnoreCase("4")))
		{ // / 100%
			row_p3t43.setVisible(false);
			row_p4t43.setVisible(false);
			row_p5t43.setVisible(false);
			row_p6t43.setVisible(false);
			row_p7t43.setVisible(false);
			row_p11t43.setVisible(true);
			row_p13t43.setVisible(true);
			row_p23t43.setVisible(true);
		}
		else if ((p2t43.getValue().equalsIgnoreCase("7")))
		{ // / 25%
			row_p3t43.setVisible(false);
			row_p4t43.setVisible(false);
			row_p5t43.setVisible(false);
			row_p6t43.setVisible(false);
			row_p7t43.setVisible(false);
			row_p11t43.setVisible(true);
			row_p13t43.setVisible(false);
			row_p23t43.setVisible(false);
			
		}
		else if ((p2t43.getValue().equalsIgnoreCase("8")))
		{ // /сверх пологаемой суммы
			row_p3t43.setVisible(false);
			row_p4t43.setVisible(false);
			row_p5t43.setVisible(false);
			row_p6t43.setVisible(false);
			row_p7t43.setVisible(false);
			row_p11t43.setVisible(false);
			row_p13t43.setVisible(false);
			row_p23t43.setVisible(false);
			
		}
	}
	
	public void onSelect$ap2t43()
	{
		if (ap2t43.getValue().equalsIgnoreCase("5"))
		{ // / по требовиню налоговой
			row_ap3t43.setVisible(true);
			row_ap4t43.setVisible(true);
			row_ap5t43.setVisible(true);
			row_ap6t43.setVisible(true);
			row_ap7t43.setVisible(true);
			row_ap11t43.setVisible(false);
			row_ap13t43.setVisible(false);
			row_ap23t43.setVisible(false);
			
		}
		else if ((ap2t43.getValue().equalsIgnoreCase("3")))
		{ // /50%
			row_ap3t43.setVisible(false);
			row_ap4t43.setVisible(false);
			row_ap5t43.setVisible(false);
			row_ap6t43.setVisible(false);
			row_ap7t43.setVisible(false);
			row_ap13t43.setVisible(true);
			row_ap11t43.setVisible(true);
			row_ap23t43.setVisible(false);
		}
		else if ((ap2t43.getValue().equalsIgnoreCase("4")))
		{ // / 100%
			row_ap3t43.setVisible(false);
			row_ap4t43.setVisible(false);
			row_ap5t43.setVisible(false);
			row_ap6t43.setVisible(false);
			row_ap7t43.setVisible(false);
			row_ap11t43.setVisible(true);
			row_ap13t43.setVisible(true);
			row_ap23t43.setVisible(true);
		}
		else if ((ap2t43.getValue().equalsIgnoreCase("7")))
		{ // / 25%
			row_ap3t43.setVisible(false);
			row_ap4t43.setVisible(false);
			row_ap5t43.setVisible(false);
			row_ap6t43.setVisible(false);
			row_ap7t43.setVisible(false);
			row_ap11t43.setVisible(true);
			row_ap13t43.setVisible(false);
			row_ap23t43.setVisible(false);
			
		}
		else if ((ap2t43.getValue().equalsIgnoreCase("8")))
		{ // /сверх пологаемой суммы
			row_ap3t43.setVisible(false);
			row_ap4t43.setVisible(false);
			row_ap5t43.setVisible(false);
			row_ap6t43.setVisible(false);
			row_ap7t43.setVisible(false);
			row_ap11t43.setVisible(false);
			row_ap13t43.setVisible(false);
			row_ap23t43.setVisible(false);
			
		}
	}
	
	public void onSelect$p13t43()
	{
		if (p13t43.getValue().equalsIgnoreCase("1"))
		{ // / освобожден
			row_p14t43.setVisible(true);
			row_p15t43.setVisible(false);
			row_p16t43.setVisible(false);
			row_p17t43.setVisible(false);
			row_p22t43.setVisible(false);
			row_p24t43.setVisible(false);
		}
		else if ((p13t43.getValue().equalsIgnoreCase("2")))
		{ // / частично осв.
			row_p14t43.setVisible(true);
			row_p15t43.setVisible(true);
			row_p16t43.setVisible(false);
			row_p17t43.setVisible(false);
			row_p22t43.setVisible(true);
			row_p24t43.setVisible(true);
		}
		else if ((p13t43.getValue().equalsIgnoreCase("3")))
		{ // / c уменшением
			row_p14t43.setVisible(false);
			row_p15t43.setVisible(false);
			row_p16t43.setVisible(true);
			row_p17t43.setVisible(false);
			row_p24t43.setVisible(true);
			row_p22t43.setVisible(true);
		}
		else if ((p13t43.getValue().equalsIgnoreCase("4")))
		{ // / частично или временно
			row_p14t43.setVisible(false);
			row_p15t43.setVisible(false);
			row_p16t43.setVisible(false);
			row_p24t43.setVisible(true);
			row_p17t43.setVisible(true);
			row_p22t43.setVisible(true);
		}
		else if ((p13t43.getValue().equalsIgnoreCase("5")))
		{ // / не освобожден
			row_p14t43.setVisible(false);
			row_p15t43.setVisible(false);
			row_p16t43.setVisible(false);
			row_p24t43.setVisible(true);
			row_p17t43.setVisible(false);
			row_p22t43.setVisible(false);
			
		}
		
	}
	
	public void onSelect$ap13t43()
	{
		if (ap13t43.getValue().equalsIgnoreCase("1"))
		{ // / освобожден
			row_ap14t43.setVisible(true);
			row_ap15t43.setVisible(false);
			row_ap16t43.setVisible(false);
			row_ap17t43.setVisible(false);
			row_ap22t43.setVisible(false);
			row_ap24t43.setVisible(false);
		}
		else if ((ap13t43.getValue().equalsIgnoreCase("2")))
		{ // / частично осв.
			row_ap14t43.setVisible(true);
			row_ap15t43.setVisible(true);
			row_ap16t43.setVisible(false);
			row_ap17t43.setVisible(false);
			row_ap22t43.setVisible(true);
			row_ap24t43.setVisible(true);
		}
		else if ((ap13t43.getValue().equalsIgnoreCase("3")))
		{ // / c уменшением
			row_ap14t43.setVisible(false);
			row_ap15t43.setVisible(false);
			row_ap16t43.setVisible(true);
			row_ap17t43.setVisible(false);
			row_ap24t43.setVisible(true);
			row_ap22t43.setVisible(true);
		}
		else if ((ap13t43.getValue().equalsIgnoreCase("4")))
		{ // / частично или временно
			row_ap14t43.setVisible(false);
			row_ap15t43.setVisible(false);
			row_ap16t43.setVisible(false);
			row_ap24t43.setVisible(true);
			row_ap17t43.setVisible(true);
			row_ap22t43.setVisible(true);
		}
		else if ((ap13t43.getValue().equalsIgnoreCase("5")))
		{ // / не освобожден
			row_ap14t43.setVisible(false);
			row_ap15t43.setVisible(false);
			row_ap16t43.setVisible(false);
			row_ap24t43.setVisible(true);
			row_ap17t43.setVisible(false);
			row_ap22t43.setVisible(false);
			
		}
		
	}
	
	public void onSelect$p14t43()
	{
		if (p14t43.getValue().equalsIgnoreCase("3"))
		{ // / освобожден
			row_p18t43.setVisible(true);
		}
		else
		{
			row_p18t43.setVisible(false);
		}
	}
	
	public void onSelect$ap14t43()
	{
		if (ap14t43.getValue().equalsIgnoreCase("3"))
		{ // / освобожден
			row_ap18t43.setVisible(true);
		}
		else
		{
			row_ap18t43.setVisible(false);
		}
	}
	
	public void onVisible$p18t43()
	{
		if (p18t43.setVisible(true))
		{ // / освобожден
			row_p19t43.setVisible(true);
		}
		else
		{
			row_p19t43.setVisible(false);
		}
	}
	
	public void onVisible$ap18t43()
	{
		if (ap18t43.setVisible(true))
		{ // / освобожден
			row_ap19t43.setVisible(true);
		}
		else
		{
			row_ap19t43.setVisible(false);
		}
	}
	
	private void setCurrent()
	{
		if (current != null)
		{
			// session.setAttribute("ufn", currusr.getFull_name());
			
			if (current.getP2t43() != null && current.getP2t43().equals("5"))
			{
				row_p3t43.setVisible(true);
				row_p4t43.setVisible(true);
				row_p5t43.setVisible(true);
				row_p6t43.setVisible(true);
				row_p7t43.setVisible(true);
				row_p11t43.setVisible(false);
				row_p13t43.setVisible(false);
				row_p23t43.setVisible(false);
			}
			else if (current.getP2t43() != null && current.getP2t43().equals("3"))
			{
				row_p3t43.setVisible(false);
				row_p4t43.setVisible(false);
				row_p5t43.setVisible(false);
				row_p6t43.setVisible(false);
				row_p7t43.setVisible(false);
				row_p13t43.setVisible(true);
				row_p11t43.setVisible(true);
				row_p23t43.setVisible(false);
			}
			else if (current.getP2t43() != null && current.getP2t43().equals("4"))
			{
				row_p3t43.setVisible(false);
				row_p4t43.setVisible(false);
				row_p5t43.setVisible(false);
				row_p6t43.setVisible(false);
				row_p7t43.setVisible(false);
				row_p11t43.setVisible(true);
				row_p13t43.setVisible(true);
				row_p23t43.setVisible(true);
			}
			else if (current.getP2t43() != null && current.getP2t43().equals("7"))
			{
				row_p3t43.setVisible(false);
				row_p4t43.setVisible(false);
				row_p5t43.setVisible(false);
				row_p6t43.setVisible(false);
				row_p7t43.setVisible(false);
				row_p11t43.setVisible(true);
				row_p13t43.setVisible(false);
				row_p23t43.setVisible(false);
			}
			else if (current.getP2t43() != null && current.getP2t43().equals("8"))
			{
				row_p3t43.setVisible(false);
				row_p4t43.setVisible(false);
				row_p5t43.setVisible(false);
				row_p6t43.setVisible(false);
				row_p7t43.setVisible(false);
				row_p11t43.setVisible(false);
				row_p13t43.setVisible(false);
				row_p23t43.setVisible(false);
			}
			
			PrSourse = ContractService.getPrSourse();
			funds = com.is.tf.contract.ContractService.getFunds(idn, alias);
			MoveFromEx = com.is.tf.contract.ContractService.getMoveFromExs2(idn, alias);
			policy = com.is.tf.contract.ContractService.getPolicy(idn, alias);
			// current.getP11t43().setModel(new ListModelList(PrSourse));
			if (current.getP11t43().equalsIgnoreCase("1"))
			{
				p12t43.setModel(new ListModelList(funds));
			}
			else if (current.getP11t43().equalsIgnoreCase("2"))
			{
				p12t43.setModel(new ListModelList(MoveFromEx));
				
			}
			else if (current.getP11t43().equalsIgnoreCase("3"))
			{
				p12t43.setModel(new ListModelList(policy));
			}
			if (current.getP13t43().equals("1"))
			{
				row_p14t43.setVisible(true);
				row_p15t43.setVisible(false);
				row_p16t43.setVisible(false);
				row_p17t43.setVisible(false);
				row_p22t43.setVisible(false);
				row_p24t43.setVisible(false);
				
			}
			else if (current.getP13t43().equals("2"))
			{ // / частично осв.
				row_p14t43.setVisible(true);
				row_p15t43.setVisible(true);
				row_p16t43.setVisible(false);
				row_p17t43.setVisible(false);
				row_p22t43.setVisible(true);
				row_p24t43.setVisible(true);
			}
			else if (current.getP13t43().equals("3"))
			{ // / c уменшением
				row_p14t43.setVisible(false);
				row_p15t43.setVisible(false);
				row_p16t43.setVisible(true);
				row_p17t43.setVisible(false);
				row_p24t43.setVisible(true);
				row_p22t43.setVisible(true);
			}
			else if (current.getP13t43().equals("4"))
			{ // / частично или временно
				row_p14t43.setVisible(false);
				row_p15t43.setVisible(false);
				row_p16t43.setVisible(false);
				row_p24t43.setVisible(true);
				row_p17t43.setVisible(true);
				row_p22t43.setVisible(true);
			}
			else if (current.getP13t43().equals("5"))
			{ // / не освобожден
				row_p14t43.setVisible(false);
				row_p15t43.setVisible(false);
				row_p16t43.setVisible(false);
				row_p24t43.setVisible(true);
				row_p17t43.setVisible(false);
				row_p22t43.setVisible(false);
			}
			
			if (current.getP14t43().equals("3"))
			{ // / освобожден
				row_p18t43.setVisible(true);
			}
			else
			{
				row_p18t43.setVisible(false);
			}
			if (current.getP14t43().equals("5"))
			{ // / освобожден
				row_p21t43.setVisible(true);
			}
			else
			{
				row_p21t43.setVisible(false);
			}
			
			if (p18t43.setVisible(true))
			{
				row_p19t43.setVisible(true);
			}
			else
			{
				row_p19t43.setVisible(false);
			}
			
		}
	}
}
