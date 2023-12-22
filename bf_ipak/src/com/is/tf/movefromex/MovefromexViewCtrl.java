package com.is.tf.movefromex;

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
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.event.PagingEvent;

import com.is.tf.contract.ContractService;
import com.is.tf.currency.RefCurrencyBox;
import com.is.tf.currency.RefCurrencyData;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.RefData;
import com.is.utils.refobj.RefObjData;

public class MovefromexViewCtrl extends GenericForwardComposer
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
	private Toolbarbutton btn_back, btn_save;
	private Toolbarbutton btn_edit, btn_confirm, btn_reject, btn_delete;
	private Toolbar tb;
	private Textbox id, p0t52, p3t52, p18t52, p19t52, p20t52, p22t52, p23t52;
	private Textbox aid, ap0t52, ap3t52, ap18t52, ap19t52, ap20t52, ap22t52, ap23t52;
	private Textbox fid, fp0t52, fp3t52, fp18t52, fp19t52, fp20t52, fp22t52, fp23t52;
	private Datebox p2t52, p21t52, ap2t52, fp2t52, ap21t52, fp21t52;
	private Decimalbox p10t52, p11t52, p12t52, p13t52, p14t52, p15t52, p16t52, ap10t52, ap11t52, ap12t52, ap13t52, ap14t52, ap15t52, ap16t52, fp10t52, fp11t52, fp12t52, fp13t52, fp14t52, fp15t52, fp16t52;
	private RefCBox p9t52, ap9t52, fp9t52;
	private Paging movefromexPaging;
	private int _pageSize = 15;
	private int _startPageNumber = 0;
	private int _totalSize = 0;
	private boolean _needsTotalSizeUpdate = true;
	private String alias;
	private String sparam, idn, subj, val1, val2, strval1, strval2;
	private Label p1t52, ap1t52, fp1t52, lp13t52, lp14t52, lp15t52, lp16t52;
	private Label line1, line2, line3, line4, line5, line6, line7, line8;
	// ===============================================
	private RefCBox p4t52, ap4t52, fp4t52;
	private RefCBox p5t52, ap5t52, fp5t52;
	private RefCBox p6t52, ap6t52, fp6t52;
	private RefCBox p7t52, ap7t52, fp7t52;
	private RefCBox p8t52, ap8t52, fp8t52;
	private RefCBox p17t52, ap17t52, fp17t52;
	private RefCurrencyBox p12t521, p12t523;
	
	private List<RefData> funddest = new ArrayList<RefData>();
	private List<RefData> conditions = new ArrayList<RefData>();
	private List<RefData> accredetivs = new ArrayList<RefData>();
	private List<RefData> garants = new ArrayList<RefData>();
	private List<RefData> policies = new ArrayList<RefData>();
	private List<RefData> currencies = new ArrayList<RefData>();
	private List<RefData> reasons = new ArrayList<RefData>();
	private List<RefCurrencyData> coursecurrencies = new ArrayList<RefCurrencyData>();
	private List<RefObjData> funds = new ArrayList<RefObjData>();
	private List<RefObjData> movefromexs = new ArrayList<RefObjData>();
	private HashMap<String, String> curr_ = null;
	
	// ===============================================
	
	PagingListModel model = null;
	ListModelList lmodel = null;
	private AnnotateDataBinder binder;
	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	
	private MoveFromEx current = new MoveFromEx();
	private MoveFromExFilter filter = new MoveFromExFilter();
	
	public MovefromexViewCtrl()
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
			filter.setP1t52(idn);
			// p1t52.setValue(idn);
			// ap1t52.setValue(idn);
			fp1t52.setValue(idn);
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
		parameter = (String[]) param.get("spr");
		if (parameter != null)
		{
			sparam = (parameter[0]);
		}
		alias = (String) session.getAttribute("alias");
		funds = ContractService.getFunds(idn, alias);
		movefromexs = ContractService.getMoveFromExs(idn, alias);
		funddest = ContractService.getFundDest("1,2", alias);
		conditions = ContractService.getConditions(alias);
		accredetivs = ContractService.getAccredetivs(idn, alias);
		garants = ContractService.getGarants(idn, alias);
		policies = ContractService.getPolicies(idn, alias);
		currencies = ContractService.getMyCurrency(alias);
		reasons = ContractService.getReasons("1,4,5", alias);
		
		curr_ = com.is.tf.contract.ContractService.getHCurr(alias);
		strval1 = curr_.get(val1);
		strval2 = curr_.get(val2);
		// System.out.println(val1+strval1+" "+val2+strval2);
		lp13t52.setValue(strval1);
		lp14t52.setValue(strval2);
		lp15t52.setValue(strval1);
		lp16t52.setValue(strval2);
		
		p4t52.setModel(new ListModelList(funddest));
		// ap4t36.setModel(new ListModelList(funddest));
		fp4t52.setModel(new ListModelList(funddest));
		
		p5t52.setModel(new ListModelList(conditions));
		// ap4t36.setModel(new ListModelList(funddest));
		fp5t52.setModel(new ListModelList(conditions));
		
		p6t52.setModel(new ListModelList(accredetivs));
		fp6t52.setModel(new ListModelList(accredetivs));
		p7t52.setModel(new ListModelList(garants));
		fp7t52.setModel(new ListModelList(garants));
		p8t52.setModel(new ListModelList(policies));
		fp8t52.setModel(new ListModelList(policies));
		
		p9t52.setModel(new ListModelList(currencies));
		// ap4t36.setModel(new ListModelList(funddest));
		fp9t52.setModel(new ListModelList(currencies));
		
		p17t52.setModel(new ListModelList(reasons));
		// ap4t36.setModel(new ListModelList(reasons));
		fp17t52.setModel(new ListModelList(reasons));
		
		line1.setValue(Labels.getLabel("movefromex.p2t52").replaceAll("<br>", "\r\n"));
		line2.setValue(Labels.getLabel("movefromex.p11t52").replaceAll("<br>", "\r\n"));
		line3.setValue(Labels.getLabel("movefromex.p9t52").replaceAll("<br>", "\r\n"));
		line4.setValue(Labels.getLabel("movefromex.p3t52tab").replaceAll("<br>", "\r\n"));
		line5.setValue(Labels.getLabel("movefromex.p23t52tab").replaceAll("<br>", "\r\n"));
		line6.setValue(Labels.getLabel("movefromex.p24t52tab").replaceAll("<br>", "\r\n"));
		line7.setValue(Labels.getLabel("movefromex.p28t52tab").replaceAll("<br>", "\r\n"));
		line8.setValue(Labels.getLabel("movefromex.p100t52tab").replaceAll("<br>", "\r\n"));
		
		dataGrid.setItemRenderer(new ListitemRenderer()
		{
			@SuppressWarnings("unchecked")
			public void render(Listitem row, Object data) throws Exception
			{
				MoveFromEx pMoveFromEx = (MoveFromEx) data;
				
				row.setValue(pMoveFromEx);
				
				row.appendChild(new Listcell(CheckNull.isEmpty(pMoveFromEx.getP2t52()) ? "" : df.format(pMoveFromEx.getP2t52())));
				row.appendChild(new Listcell(pMoveFromEx.getP11t52() + ""));
				row.appendChild(new Listcell(pMoveFromEx.getP9t52()));
				row.appendChild(new Listcell(pMoveFromEx.getP3t52()));
				row.appendChild(new Listcell(pMoveFromEx.getP23t52()));
				row.appendChild(new Listcell(pMoveFromEx.getP24t52()));
				row.appendChild(new Listcell(pMoveFromEx.getP28t52() + ""));
				row.appendChild(new Listcell(com.is.tf.contract.SPR.getP100Value(String.valueOf(pMoveFromEx.getP100t52()))));
				
				row.appendChild(new Listcell(pMoveFromEx.getId() + ""));
				row.appendChild(new Listcell(pMoveFromEx.getP0t52()));
				row.appendChild(new Listcell(pMoveFromEx.getP1t52()));
				row.appendChild(new Listcell(pMoveFromEx.getP4t52()));
				row.appendChild(new Listcell(pMoveFromEx.getP5t52()));
				row.appendChild(new Listcell(pMoveFromEx.getP6t52()));
				row.appendChild(new Listcell(pMoveFromEx.getP7t52()));
				row.appendChild(new Listcell(pMoveFromEx.getP8t52()));
				row.appendChild(new Listcell(pMoveFromEx.getP10t52() + ""));
				row.appendChild(new Listcell(pMoveFromEx.getP12t52() + ""));
				row.appendChild(new Listcell(pMoveFromEx.getP13t52() + ""));
				row.appendChild(new Listcell(pMoveFromEx.getP14t52() + ""));
				row.appendChild(new Listcell(pMoveFromEx.getP15t52() + ""));
				row.appendChild(new Listcell(pMoveFromEx.getP16t52() + ""));
				row.appendChild(new Listcell(pMoveFromEx.getP17t52()));
				row.appendChild(new Listcell(pMoveFromEx.getP18t52()));
				row.appendChild(new Listcell(pMoveFromEx.getP19t52()));
				row.appendChild(new Listcell(pMoveFromEx.getP20t52()));
				row.appendChild(new Listcell(CheckNull.isEmpty(pMoveFromEx.getP21t52()) ? "" : df.format(pMoveFromEx.getP21t52())));
				row.appendChild(new Listcell(pMoveFromEx.getP22t52()));
				
			}
		});
		
		refreshModel(_startPageNumber);
		
	}
	
	public void onPaging$movefromexPaging(ForwardEvent event)
	{
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModel(_startPageNumber);
	}
	
	private void refreshModel(int activePage)
	{
		filter.setP1t52(idn);
		movefromexPaging.setPageSize(_pageSize);
		model = new PagingListModel(activePage, _pageSize, filter, "");
		if (_needsTotalSizeUpdate)
		{
			_totalSize = model.getTotalSize(filter, "");
			// _needsTotalSizeUpdate = false;
		}
		movefromexPaging.setTotalSize(_totalSize);
		dataGrid.setModel((ListModel) model);
		if (model.getSize() > 0)
		{
			dataGrid.setSelectedIndex(0);
			sendSelEvt();
			this.current = (MoveFromEx) model.getElementAt(0);
			sendSelEvt();
		}
	}
	
	// Omitted...
	public MoveFromEx getCurrent()
	{
		return current;
	}
	
	public void setCurrent(MoveFromEx current)
	{
		this.current = current;
	}
	
	// Omitted...
	public MoveFromExFilter getFilter()
	{
		return filter;
	}
	
	public void setFilter(MoveFromExFilter filter)
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
		btn_save.setVisible(false);
		
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
		if (current != null)
		{
			coursecurrencies = ContractService.getCourseCurr_18t1_19t1_withOther(idn, df.format(current.getP2t52()), "'" + current.getP9t52() + "'", alias);
			p12t521.setModel((new ListModelList(coursecurrencies)));
			p12t523.setModel((new ListModelList(coursecurrencies)));
		}
	}
	
	public void onClick$btn_add()
	{
		onDoubleClick$dataGrid$grd();
		frmgrd.setVisible(false);
		addgrd.setVisible(true);
		fgrd.setVisible(false);
		btn_save.setVisible(false);
		
	}
	
	public void onClick$btn_search()
	{
		onDoubleClick$dataGrid$grd();
		frmgrd.setVisible(false);
		addgrd.setVisible(false);
		fgrd.setVisible(true);
		btn_save.setVisible(true);
	}
	
	/*
	 * public void onClick$btn_save() { try{ final BankServiceProxy ws = new
	 * BankServiceProxy("http://91.213.31.234:8892/yeisvo_bank/service");
	 * com.sbs.service.MoveFromExResult mfe = new
	 * com.sbs.service.MoveFromExResult(); if(addgrd.isVisible()){
	 * MoveFromExService.create(new MoveFromEx( Long.parseLong(aid.getValue()),
	 * ap0t52.getValue(), ap1t52.getValue(), ap2t52.getValue(),
	 * ap3t52.getValue(), ap4t52.getValue(), ap5t52.getValue(),
	 * ap6t52.getValue(), ap7t52.getValue(), ap8t52.getValue(),
	 * ap9t52.getValue(), ap10t52.doubleValue(), ap11t52.doubleValue(),
	 * ap12t52.doubleValue(), ap13t52.doubleValue(), ap14t52.doubleValue(),
	 * ap15t52.doubleValue(), ap16t52.doubleValue(), ap17t52.getValue(),
	 * ap18t52.getValue(), ap19t52.getValue(), ap20t52.getValue(),
	 * ap21t52.getValue(), ap22t52.getValue(), ap23t52.getValue() ));
	 * CheckNull.clearForm(addgrd); frmgrd.setVisible(true);
	 * addgrd.setVisible(false); fgrd.setVisible(false); }else
	 * if(fgrd.isVisible()){ filter = new MoveFromExFilter(); if
	 * (!CheckNull.isEmpty(fid.getValue())) { Long.parseLong(fid.getValue()); }
	 * filter.setP0t52(fp0t52.getValue()); filter.setP1t52(fp1t52.getValue());
	 * filter.setP2t52(fp2t52.getValue()); filter.setP3t52(fp3t52.getValue());
	 * filter.setP4t52(fp4t52.getValue()); filter.setP5t52(fp5t52.getValue());
	 * filter.setP6t52(fp6t52.getValue()); filter.setP7t52(fp7t52.getValue());
	 * filter.setP8t52(fp8t52.getValue()); filter.setP9t52(fp9t52.getValue());
	 * filter.setP10t52(fp10t52.doubleValue());
	 * filter.setP11t52(fp11t52.doubleValue());
	 * filter.setP12t52(fp12t52.doubleValue());
	 * filter.setP13t52(fp13t52.doubleValue());
	 * filter.setP14t52(fp14t52.doubleValue());
	 * filter.setP15t52(fp15t52.doubleValue());
	 * filter.setP16t52(fp16t52.doubleValue());
	 * filter.setP17t52(fp17t52.getValue());
	 * filter.setP18t52(fp18t52.getValue());
	 * filter.setP19t52(fp19t52.getValue());
	 * filter.setP20t52(fp20t52.getValue());
	 * filter.setP21t52(fp21t52.getValue());
	 * filter.setP22t52(fp22t52.getValue());
	 * filter.setP23t52(fp23t52.getValue()); }else{
	 * Long.parseLong(id.getValue()); current.setP0t52(p0t52.getValue());
	 * current.setP1t52(p1t52.getValue()); current.setP2t52(p2t52.getValue());
	 * current.setP3t52(p3t52.getValue()); current.setP4t52(p4t52.getValue());
	 * current.setP5t52(p5t52.getValue()); current.setP6t52(p6t52.getValue());
	 * current.setP7t52(p7t52.getValue()); current.setP8t52(p8t52.getValue());
	 * current.setP9t52(p9t52.getValue());
	 * current.setP10t52(p10t52.doubleValue());
	 * current.setP11t52(p11t52.doubleValue());
	 * current.setP12t52(p12t52.doubleValue());
	 * current.setP13t52(p13t52.doubleValue());
	 * current.setP14t52(p14t52.doubleValue());
	 * current.setP15t52(p15t52.doubleValue());
	 * current.setP16t52(p16t52.doubleValue());
	 * current.setP17t52(p17t52.getValue());
	 * current.setP18t52(p18t52.getValue());
	 * current.setP19t52(p19t52.getValue());
	 * current.setP20t52(p20t52.getValue());
	 * current.setP21t52(p21t52.getValue());
	 * current.setP22t52(p22t52.getValue());
	 * current.setP23t52(p23t52.getValue());
	 * //MoveFromExService.update(current); MoveFromExResult ar =
	 * ws.saveMoveFromEx("nci", "987", p1t52.getValue() , getMfe(current)); if
	 * (ar.getStatus() == 0) { refreshModel(_startPageNumber);
	 * alert("Сохранение успешно"); } else {
	 * alert("Error:"+ar.getStatus()+"; GTKid:" +ar.getGtkId()+ "; Text:"
	 * +ar.getErrorMsg()); } } onClick$btn_back();
	 * refreshModel(_startPageNumber); SelectEvent evt = new
	 * SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
	 * Events.sendEvent(evt); } catch (Exception e) { e.printStackTrace(); } }
	 */
	public void onClick$btn_cancel()
	{
		if (fgrd.isVisible())
		{
			filter = new MoveFromExFilter();
		}
		onClick$btn_back();
		frmgrd.setVisible(true);
		addgrd.setVisible(false);
		fgrd.setVisible(false);
		CheckNull.clearForm(addgrd);
		CheckNull.clearForm(fgrd);
		refreshModel(_startPageNumber);
	}
	
	private com.sbs.service.MoveFromEx getMfe(MoveFromEx acr)
	{
		java.util.Calendar cal = java.util.Calendar.getInstance();
		com.sbs.service.MoveFromEx res = new com.sbs.service.MoveFromEx();
		// res.setP0T52(Integer.parseInt(acr.getP0t52()));
		cal.setTime(acr.getP2t52());
		res.setP2T52(cal);
		res.setP3T52(acr.getP3t52());
		res.setP4T52(Short.parseShort(acr.getP4t52()));
		res.setP5T52(Short.parseShort(acr.getP5t52()));
		res.setP6T52(acr.getP6t52());
		res.setP7T52(acr.getP7t52());
		res.setP8T52(acr.getP8t52());
		res.setP9T52(acr.getP9t52());
		// res.setP10T52(acr.getP10t52());
		res.setP11T52(acr.getP11t52());
		res.setP12T52(acr.getP12t52());
		res.setP13T52(acr.getP13t52());
		res.setP14T52(acr.getP14t52());
		res.setP15T52(acr.getP15t52());
		res.setP16T52(acr.getP16t52());
		res.setP17T52(Short.parseShort(acr.getP17t52()));
		res.setP18T52(acr.getP18t52());
		res.setP19T52(acr.getP19t52());
		res.setP20T52(acr.getP20t52());
		cal.setTime(acr.getP21t52());
		res.setP21T52(cal);
		res.setP22T52(acr.getP22t52());
		res.setP23T52(Integer.parseInt(acr.getP23t52()));
		res.setP24T52((String) session.getAttribute("ufn"));
		return res;
	}
	
	public void onInitRenderLater$p12t523()
	{
		if (coursecurrencies.size() > 0)
		{
			p12t523.setSelectedIndex(coursecurrencies.size() - 1);
		}
	}
	
}
