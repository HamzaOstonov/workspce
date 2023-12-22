package com.is.tf.movefromim;

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

public class MovefromimViewCtrl extends GenericForwardComposer
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
	private Textbox id, p0t53, p3t53, p4t53, p5t53, p7t53, p8t53, p9t53, p10t53, p11t53, p12t53, p13t53, p14t53, p15t53, p23t53, p24t53, p25t53, p26t53, p28t53, p29t53, p30t53;
	private Textbox aid, ap0t53, ap3t53, ap4t53, ap5t53, ap7t53, ap8t53, ap9t53, ap10t53, ap11t53, ap12t53, ap13t53, ap14t53, ap15t53, ap23t53, ap24t53, ap25t53, ap26t53, ap28t53, ap29t53, ap30t53;
	private Textbox fid, fp0t53, fp3t53, fp4t53, fp5t53, fp7t53, fp8t53, fp9t53, fp10t53, fp11t53, fp12t53, fp13t53, fp14t53, fp15t53, fp23t53, fp24t53, fp25t53, fp26t53, fp28t53, fp29t53, fp30t53;
	private Datebox p2t53, p6t53, p27t53, ap2t53, ap6t53, fp2t53, fp6t53, ap27t53, fp27t53, p31t53, ap31t53, fp31t53, p33t53, ap33t53, fp33t53;
	private Decimalbox p17t53, p18t53, p19t53, p20t53, p21t53, p22t53, ap17t53, ap18t53, ap19t53, ap20t53, ap21t53, ap22t53, fp17t53, fp18t53, fp19t53, fp20t53, fp21t53, fp22t53;
	private RefCBox p16t53, ap16t53, fp16t53;
	private Paging movefromimPaging;
	private int _pageSize = 15;
	private int _startPageNumber = 0;
	private int _totalSize = 0;
	private boolean _needsTotalSizeUpdate = true;
	private String alias;
	private List<RefCurrencyData> coursecurrencies = new ArrayList<RefCurrencyData>();
	private List<RefData> currencies = new ArrayList<RefData>();
	private RefCurrencyBox p17t531, p17t533;
	private HashMap<String, String> curr_ = null;
	private String idn, subj, val1, val2, strval1, strval2;
	private Label p1t53, ap1t53, fp1t53, lp19t53, lp20t53, lp21t53, lp22t53, flp19t53, flp20t53, flp21t53, flp22t53;
	
	private Movefromim current = new Movefromim();
	public MovefromimFilter filter = new MovefromimFilter();
	
	PagingListModel model = null;
	ListModelList lmodel = null;
	private AnnotateDataBinder binder;
	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	
	public MovefromimViewCtrl()
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
			filter.setP1t53(idn);
			// p1t52.setValue(idn);
			// ap1t52.setValue(idn);
			fp1t53.setValue(idn);
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
		alias = (String) session.getAttribute("alias");
		
		currencies = ContractService.getMyCurrency(alias);
		
		curr_ = com.is.tf.contract.ContractService.getHCurr(alias);
		strval1 = curr_.get(val1);
		strval2 = curr_.get(val2);
		// System.out.println(val1+strval1+" "+val2+strval2);
		lp19t53.setValue(strval1);
		lp20t53.setValue(strval2);
		lp21t53.setValue(strval1);
		lp22t53.setValue(strval2);
		flp19t53.setValue(strval1);
		flp20t53.setValue(strval2);
		flp21t53.setValue(strval1);
		flp22t53.setValue(strval2);
		
		p16t53.setModel(new ListModelList(currencies));
		
		dataGrid.setItemRenderer(new ListitemRenderer()
		{
			@SuppressWarnings("unchecked")
			public void render(Listitem row, Object data) throws Exception
			{
				Movefromim pMovefromim = (Movefromim) data;
				
				row.setValue(pMovefromim);
				
				row.appendChild(new Listcell(pMovefromim.getId() + ""));
				row.appendChild(new Listcell(pMovefromim.getP0t53()));
				row.appendChild(new Listcell(pMovefromim.getP1t53()));
				row.appendChild(new Listcell(pMovefromim.getP2t53() + ""));
				row.appendChild(new Listcell(pMovefromim.getP3t53()));
				row.appendChild(new Listcell(pMovefromim.getP4t53()));
				row.appendChild(new Listcell(pMovefromim.getP5t53()));
				row.appendChild(new Listcell(pMovefromim.getP6t53() + ""));
				row.appendChild(new Listcell(pMovefromim.getP7t53()));
				row.appendChild(new Listcell(pMovefromim.getP8t53()));
				row.appendChild(new Listcell(pMovefromim.getP9t53()));
				row.appendChild(new Listcell(pMovefromim.getP10t53()));
				row.appendChild(new Listcell(pMovefromim.getP11t53()));
				row.appendChild(new Listcell(pMovefromim.getP12t53()));
				row.appendChild(new Listcell(pMovefromim.getP13t53()));
				row.appendChild(new Listcell(pMovefromim.getP14t53()));
				row.appendChild(new Listcell(pMovefromim.getP15t53()));
				row.appendChild(new Listcell(pMovefromim.getP16t53()));
				row.appendChild(new Listcell(pMovefromim.getP17t53() + ""));
				row.appendChild(new Listcell(pMovefromim.getP18t53() + ""));
				row.appendChild(new Listcell(pMovefromim.getP19t53() + ""));
				row.appendChild(new Listcell(pMovefromim.getP20t53() + ""));
				row.appendChild(new Listcell(pMovefromim.getP21t53() + ""));
				row.appendChild(new Listcell(pMovefromim.getP22t53() + ""));
				row.appendChild(new Listcell(pMovefromim.getP23t53()));
				row.appendChild(new Listcell(pMovefromim.getP24t53()));
				row.appendChild(new Listcell(pMovefromim.getP25t53()));
				row.appendChild(new Listcell(pMovefromim.getP26t53()));
				row.appendChild(new Listcell(pMovefromim.getP27t53() + ""));
				row.appendChild(new Listcell(pMovefromim.getP28t53()));
				row.appendChild(new Listcell(pMovefromim.getP29t53()));
				
			}
		});
		p16t53.setModel((new ListModelList(com.is.utils.RefDataService.getCurrency(alias))));
		ap16t53.setModel((new ListModelList(com.is.utils.RefDataService.getCurrency(alias))));
		fp16t53.setModel((new ListModelList(com.is.utils.RefDataService.getCurrency(alias))));
		
		refreshModel(_startPageNumber);
		
	}
	
	public void onPaging$movefromimPaging(ForwardEvent event)
	{
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModel(_startPageNumber);
	}
	
	private void refreshModel(int activePage)
	{
		movefromimPaging.setPageSize(_pageSize);
		model = new PagingListModel(activePage, _pageSize, filter, "");
		if (_needsTotalSizeUpdate)
		{
			_totalSize = model.getTotalSize(filter, "");
			// _needsTotalSizeUpdate = false;
		}
		movefromimPaging.setTotalSize(_totalSize);
		dataGrid.setModel((ListModel) model);
		if (model.getSize() > 0)
		{
			dataGrid.setSelectedIndex(0);
			sendSelEvt(true);
			this.current = (Movefromim) model.getElementAt(0);
			sendSelEvt(true);
		}
	}
	
	public void onSelect$dataGrid$grd()
	{
		sendSelEvt(false);
	}
	
	// Omitted...
	public Movefromim getCurrent()
	{
		return current;
	}
	
	public void setCurrent(Movefromim current)
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
	
	private void sendSelEvt(Boolean senEvt)
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
		if (senEvt)
		{
			SelectEvent evt = new SelectEvent("onSelect", dataGrid, dataGrid.getSelectedItems());
			Events.sendEvent(evt);
		}
		if (current != null)
		{
			coursecurrencies = ContractService.getCourseCurr_18t1_19t1_withOther(idn, df.format(current.getP2t53()), "'" + current.getP16t53() + "'", alias);
			p17t531.setModel((new ListModelList(coursecurrencies)));
			p17t533.setModel((new ListModelList(coursecurrencies)));
		}
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
		btn_save.setVisible(true);
	}
	
	/*
	 * public void onClick$btn_save() { try{ final BankServiceProxy ws = new
	 * BankServiceProxy((String)session.getAttribute("YESVO_URL"));
	 * com.sbs.service.MoveFromImResult mfi = new
	 * com.sbs.service.MoveFromImResult(); if(addgrd.isVisible()) {
	 * MoveFromImResult ar =
	 * ws.saveMoveFromIm(((String)(session.getAttribute("BankINN"))) , idn,
	 * getMfi(new Movefromim( Long.parseLong(aid.getValue()), ap0t53.getValue(),
	 * ap1t53.getValue(), ap2t53.getValue(), ap3t53.getValue(),
	 * ap4t53.getValue(), ap5t53.getValue(), ap6t53.getValue(),
	 * ap7t53.getValue(), ap8t53.getValue(), ap9t53.getValue(),
	 * ap10t53.getValue(), ap11t53.getValue(), ap12t53.getValue(),
	 * ap13t53.getValue(), ap14t53.getValue(), ap15t53.getValue(),
	 * ap16t53.getValue(), ap17t53.doubleValue(), ap18t53.doubleValue(),
	 * ap19t53.doubleValue(), ap20t53.doubleValue(), ap21t53.doubleValue(),
	 * ap22t53.doubleValue(), ap23t53.getValue(), ap24t53.getValue(),
	 * ap25t53.getValue(), ap26t53.getValue(), ap27t53.getValue(),
	 * ap28t53.getValue(), ap29t53.getValue(), ap30t53.getValue(),
	 * ap31t53.getValue(), ap33t53.getValue() ))); CheckNull.clearForm(addgrd);
	 * frmgrd.setVisible(true); addgrd.setVisible(false);
	 * fgrd.setVisible(false); }else if(fgrd.isVisible()){ filter = new
	 * MovefromimFilter(); Long.parseLong(fid.getValue());
	 * filter.setP0t53(fp0t53.getValue()); filter.setP1t53(fp1t53.getValue());
	 * filter.setP2t53(fp2t53.getValue()); filter.setP3t53(fp3t53.getValue());
	 * filter.setP4t53(fp4t53.getValue()); filter.setP5t53(fp5t53.getValue());
	 * filter.setP6t53(fp6t53.getValue()); filter.setP7t53(fp7t53.getValue());
	 * filter.setP8t53(fp8t53.getValue()); filter.setP9t53(fp9t53.getValue());
	 * filter.setP10t53(fp10t53.getValue());
	 * filter.setP11t53(fp11t53.getValue());
	 * filter.setP12t53(fp12t53.getValue());
	 * filter.setP13t53(fp13t53.getValue());
	 * filter.setP14t53(fp14t53.getValue());
	 * filter.setP15t53(fp15t53.getValue());
	 * filter.setP16t53(fp16t53.getValue());
	 * filter.setP17t53(fp17t53.doubleValue());
	 * filter.setP18t53(fp18t53.doubleValue());
	 * filter.setP19t53(fp19t53.doubleValue());
	 * filter.setP20t53(fp20t53.doubleValue());
	 * filter.setP21t53(fp21t53.doubleValue());
	 * filter.setP22t53(fp22t53.doubleValue());
	 * filter.setP23t53(fp23t53.getValue());
	 * filter.setP24t53(fp24t53.getValue());
	 * filter.setP25t53(fp25t53.getValue());
	 * filter.setP26t53(fp26t53.getValue());
	 * filter.setP27t53(fp27t53.getValue());
	 * filter.setP28t53(fp28t53.getValue());
	 * filter.setP29t53(fp29t53.getValue()); } else {
	 * Long.parseLong(id.getValue()); current.setP0t53(p0t53.getValue());
	 * current.setP1t53(p1t53.getValue()); current.setP2t53(p2t53.getValue());
	 * current.setP3t53(p3t53.getValue()); current.setP4t53(p4t53.getValue());
	 * current.setP5t53(p5t53.getValue()); current.setP6t53(p6t53.getValue());
	 * current.setP7t53(p7t53.getValue()); current.setP8t53(p8t53.getValue());
	 * current.setP9t53(p9t53.getValue()); current.setP10t53(p10t53.getValue());
	 * current.setP11t53(p11t53.getValue());
	 * current.setP12t53(p12t53.getValue());
	 * current.setP13t53(p13t53.getValue());
	 * current.setP14t53(p14t53.getValue());
	 * current.setP15t53(p15t53.getValue());
	 * current.setP16t53(p16t53.getValue());
	 * current.setP17t53(p17t53.doubleValue());
	 * current.setP18t53(p18t53.doubleValue());
	 * current.setP19t53(p19t53.doubleValue());
	 * current.setP20t53(p20t53.doubleValue());
	 * current.setP21t53(p21t53.doubleValue());
	 * current.setP22t53(p22t53.doubleValue());
	 * current.setP23t53(p23t53.getValue());
	 * current.setP24t53(p24t53.getValue());
	 * current.setP25t53(p25t53.getValue());
	 * current.setP26t53(p26t53.getValue());
	 * current.setP27t53(p27t53.getValue());
	 * current.setP28t53(p28t53.getValue());
	 * current.setP29t53(p29t53.getValue()); PenaltyResult ar =
	 * ws.saveMoveToIm(((String)(session.getAttribute("BankINN"))), idn ,
	 * getMfi(current)); if (ar.getStatus() == 0) {
	 * refreshModel(_startPageNumber); alert("Сохранение успешно"); } else {
	 * alert("Error:"+ar.getStatus()+"; GTKid:" +ar.getGtkId()+ "; Text:"
	 * +ar.getErrorMsg()); } } } onClick$btn_back();
	 * refreshModel(_startPageNumber); SelectEvent evt = new
	 * SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
	 * Events.sendEvent(evt); } catch (Exception e) { e.printStackTrace(); } }
	 */

	public void onClick$btn_cancel()
	{
		if (fgrd.isVisible())
		{
			filter = new MovefromimFilter();
		}
		onClick$btn_back();
		frmgrd.setVisible(true);
		addgrd.setVisible(false);
		fgrd.setVisible(false);
		CheckNull.clearForm(addgrd);
		CheckNull.clearForm(fgrd);
		refreshModel(_startPageNumber);
	}
	
	private com.sbs.service.MoveFromIm getMfi(Movefromim acr)
	{
		java.util.Calendar cal = java.util.Calendar.getInstance();
		com.sbs.service.MoveFromIm res = new com.sbs.service.MoveFromIm();
		res.setP0T53(Integer.parseInt(acr.getP0t53()));
		cal.setTime(acr.getP2t53());
		res.setP2T53(cal);
		res.setP3T53(acr.getP3t53());
		res.setP4T53(Short.parseShort(acr.getP4t53()));
		res.setP5T53(acr.getP5t53());
		cal.setTime(acr.getP6t53());
		res.setP6T53(cal);
		res.setP7T53(acr.getP7t53());
		res.setP8T53(Short.parseShort(acr.getP8t53()));
		res.setP9T53(Short.parseShort(acr.getP9t53()));
		res.setP10T53(Short.parseShort(acr.getP10t53()));
		res.setP11T53(acr.getP11t53());
		res.setP12T53(Short.parseShort(acr.getP12t53()));
		res.setP13T53(Short.parseShort(acr.getP13t53()));
		res.setP14T53(acr.getP14t53());
		res.setP15T53(acr.getP15t53());
		res.setP16T53(acr.getP16t53());
		res.setP17T53(acr.getP17t53());
		res.setP18T53(acr.getP18t53());
		res.setP19T53(acr.getP19t53());
		res.setP20T53(acr.getP20t53());
		res.setP21T53(acr.getP21t53());
		res.setP22T53(acr.getP22t53());
		res.setP23T53(Short.parseShort(acr.getP23t53()));
		res.setP24T53(acr.getP24t53());
		res.setP25T53(acr.getP25t53());
		res.setP26T53(acr.getP26t53());
		cal.setTime(acr.getP27t53());
		res.setP27T53(cal);
		res.setP28T53(acr.getP28t53());
		res.setP29T53(Integer.parseInt(acr.getP29t53()));
		res.setP30T53((String) session.getAttribute("ufn"));
		return res;
	}
	
	public void onInitRenderLater$p17t533()
	{
		if (coursecurrencies.size() > 0)
		{
			p17t533.setSelectedIndex(coursecurrencies.size() - 1);
		}
	}
	
}
