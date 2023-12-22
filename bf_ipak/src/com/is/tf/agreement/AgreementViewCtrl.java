package com.is.tf.agreement;

import java.text.SimpleDateFormat;
import java.util.HashMap;

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
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;
import org.zkoss.zul.event.PagingEvent;

import com.is.utils.CheckNull;
import com.is.utils.RefCBox;

public class AgreementViewCtrl extends GenericForwardComposer
{
	private Div frm;
	private Listbox dataGrid;
	private Paging contactPaging;
	private Window contractmain = null;
	private Div grd;
	private Grid addgrd, frmgrd, fgrd;
	private Toolbarbutton btn_last, btn_confirm, btn_reject, btn_save ;
	private Toolbarbutton btn_next;
	private Toolbarbutton btn_prev;
	private Toolbarbutton btn_first;
	private Toolbarbutton btn_add;
	private Toolbarbutton btn_search;
	private Toolbarbutton btn_back;
	private Toolbar tb;
	private Textbox p100t5, p27t5, p2t5, id, p3t5, p6t5, p13t5, fp27t5, p16t5, p17t5, p18t5, p19t5, p20t5, p21t5, p22t5, p23t5, p28t5, p30t5, p32t5, p33t5, p34t5, p35t5, p36t5, p38t5;
	private Textbox ap2t5, aid, ap3t5, ap6t5, ap7t5, ap13t5, ap15t5, ap16t5, ap17t5, ap18t5, ap19t5, ap20t5, ap21t5, ap22t5, ap23t5, ap28t5, ap30t5, ap32t5, ap33t5, ap34t5, ap35t5, ap36t5, ap38t5;
	private Textbox p11t5, ap11t5, fp11t5, fp2t5, p14t5, ap14t5, fp14t5, fid, fp3t5, fp6t5, fp7t5, fp8t5, fp13t5, fp15t5, fp16t5, fp17t5, fp18t5, fp19t5, fp20t5, fp21t5, fp22t5, fp23t5, fp28t5, fp30t5, fp32t5, fp33t5, fp34t5, fp35t5, fp36t5, fp38t5;
	private Intbox ap8t5, p26t5, ap26t5, fp26t5, p37t5, ap37t5, fp37t5;
	private Datebox p43t5, ap43t5, p4t5, p5t5, ap4t5, ap5t5, fp4t5, fp5t5, ap27t5, p29t5, ap29t5, fp29t5, p31t5, ap31t5, fp31t5;
	private Decimalbox p24t5, p25t5, ap24t5, ap25t5, fp24t5, fp25t5;
	private RefCBox p7t5, p8t5, p9t5, ap9t5, fp9t5, p10t5, ap10t5, fp10t5, p12t5, ap12t5, fp12t5, p15t5;
	private Paging agreementPaging;
	private int _pageSize = 15;
	private int _startPageNumber = 0;
	private int _totalSize = 0;
	private boolean _needsTotalSizeUpdate = true;
	private String alias, idn, sparam1;
	private long idc;
	private Label line1;
	private Label line2;
	private Label line3;
	private Label line4;
	private Label line5;
	private Label line6;
	private Label line7;
	private Label line8;
	
	public AgreementFilter filter = new AgreementFilter();
	
	PagingListModel model = null;
	ListModelList lmodel = null;
	private AnnotateDataBinder binder;
	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	
	private Agreement current = new Agreement();
	
	public AgreementViewCtrl()
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
		if (parameter != null) idn = parameter[0];
				
		parameter = (String[]) param.get("idc");
		if (parameter != null) idc = Long.parseLong(parameter[0]);
		
		parameter = (String[]) param.get("spr");
		if (parameter!=null) sparam1 = (parameter[0]);
		
		line1.setValue(Labels.getLabel("agreement.p3t5").replaceAll("<br>", "\r\n"));
		line2.setValue(Labels.getLabel("agreement.p4t5tab").replaceAll("<br>", "\r\n"));
		line3.setValue(Labels.getLabel("agreement.p6t5tab").replaceAll("<br>", "\r\n"));
		line4.setValue(Labels.getLabel("agreement.p43t5tab").replaceAll("<br>", "\r\n"));
		line5.setValue(Labels.getLabel("agreement.p100t5tab").replaceAll("<br>", "\r\n"));
		
		dataGrid.setItemRenderer(new ListitemRenderer()
		{
			@SuppressWarnings("unchecked")
			public void render(Listitem row, Object data) throws Exception
			{
				Agreement pAgreement = (Agreement) data;
				
				row.setValue(pAgreement);
				
				row.appendChild(new Listcell(pAgreement.getP3t5()));
				row.appendChild(new Listcell(pAgreement.getP4t5() + ""));
				row.appendChild(new Listcell(pAgreement.getP6t5()));
				row.appendChild(new Listcell(pAgreement.getP43t5() + ""));
				row.appendChild(new Listcell(com.is.tf.contract.SPR.getP100Value(String.valueOf(pAgreement.getP100t5()))));
				
			}
		});
		
		p7t5.setModel((new ListModelList(com.is.utils.RefDataService.getCurrency(alias))));
		p8t5.setModel((new ListModelList(com.is.utils.RefDataService.getCurrency(alias))));
		p9t5.setModel((new ListModelList(com.is.utils.RefDataService.getCurrency(alias))));
		fp9t5.setModel((new ListModelList(com.is.utils.RefDataService.getCurrency(alias))));
		p10t5.setModel((new ListModelList(com.is.utils.RefDataService.getCurrency(alias))));
		fp10t5.setModel((new ListModelList(com.is.utils.RefDataService.getCurrency(alias))));
		p12t5.setModel((new ListModelList(com.is.utils.RefDataService.getCountry(alias))));
		p15t5.setModel((new ListModelList(com.is.utils.RefDataService.getCountry(alias))));
		fp12t5.setModel((new ListModelList(com.is.utils.RefDataService.getCurrency(alias))));
		
		
		refreshModel(_startPageNumber);
		
	}
	
	public void onPaging$agreementPaging(ForwardEvent event)
	{
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModel(_startPageNumber);
	}
	
	private void refreshModel(int activePage)
	{
		if (sparam1 != null)
		{
			if (sparam1.equals("Filial")) 
			{
				btn_confirm.setVisible(true);
				btn_reject.setVisible(true);
			}
			else
			{
				btn_confirm.setVisible(false);
				btn_reject.setVisible(false);
			}
		}
		agreementPaging.setPageSize(_pageSize);
		model = new PagingListModel(activePage, _pageSize, filter, "");
		
		if (_needsTotalSizeUpdate)
		{
			_totalSize = model.getTotalSize();
			_needsTotalSizeUpdate = false;
		}
		
		agreementPaging.setTotalSize(_totalSize);
		
		dataGrid.setModel((ListModel) model);
		if (model.getSize() > 0)
		{
			this.current = (Agreement) model.getElementAt(0);
			sendSelEvt();
		}
	}
	
	// Omitted...
	public Agreement getCurrent()
	{
		return current;
	}
	
	public void setCurrent(Agreement current)
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
		p2t5.setValue(idn);
		
		disableFields(true);
		btn_confirm.setVisible(true);
		btn_reject.setVisible(true);
		btn_save.setVisible(false);
	}
	
	public void disableFields(boolean pr)
	{
		p2t5.setDisabled(pr);
		p3t5.setDisabled(pr);
		p4t5.setDisabled(pr);
		p5t5.setDisabled(pr);
		p6t5.setDisabled(pr);
		p7t5.setDisabled(pr);
		p8t5.setDisabled(pr);
		p9t5.setDisabled(pr);
		p10t5.setDisabled(pr);
		p11t5.setDisabled(pr);
		p12t5.setDisabled(pr);
		p13t5.setDisabled(pr);
		p14t5.setDisabled(pr);
		p15t5.setDisabled(pr);
		p16t5.setDisabled(pr);
		p17t5.setDisabled(pr);
		p18t5.setDisabled(pr);
		p20t5.setDisabled(pr);
		p22t5.setDisabled(pr);
		p24t5.setDisabled(pr);
		p25t5.setDisabled(pr);
		p26t5.setDisabled(pr);
		p27t5.setDisabled(pr);
		p28t5.setDisabled(pr);
		p29t5.setDisabled(pr);
		p30t5.setDisabled(pr);
		p33t5.setDisabled(pr);
		p34t5.setDisabled(pr);
		p43t5.setDisabled(pr);
		p100t5.setDisabled(pr);
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
			if (addgrd.isVisible())
			{
				/*
				 * AgreementService.create(new Agreement(
				 * Long.parseLong(aid.getValue()), ap3t5.getValue(),
				 * ap4t5.getValue(), ap5t5.getValue(), ap6t5.getValue(),
				 * ap7t5.getValue(), ap8t5.intValue(), ap9t5.getValue(),
				 * ap10t5.getValue(), ap11t5.getValue(), ap12t5.getValue(),
				 * ap13t5.getValue(), ap14t5.intValue(), ap15t5.getValue(),
				 * ap16t5.getValue(), ap17t5.getValue(), ap18t5.getValue(),
				 * ap20t5.getValue(), ap22t5.getValue(), ap24t5.doubleValue(),
				 * ap25t5.doubleValue(), ap26t5.intValue(), ap27t5.getValue(),
				 * ap28t5.getValue(), ap29t5.getValue(), ap30t5.getValue(),
				 * Integer.parseInt(ap34t5.getValue()), ap43t5.getValue() ));
				 */
				CheckNull.clearForm(addgrd);
				frmgrd.setVisible(true);
				addgrd.setVisible(false);
				fgrd.setVisible(false);
			}
			else if (fgrd.isVisible())
			{
				filter = new AgreementFilter();
				
				Long.parseLong(fid.getValue());
				filter.setP2t5(fp2t5.getValue());
				filter.setP3t5(fp3t5.getValue());
				filter.setP4t5(fp4t5.getValue());
				filter.setP5t5(fp5t5.getValue());
				filter.setP6t5(fp6t5.getValue());
				filter.setP7t5(fp7t5.getValue());
				filter.setP8t5(fp8t5.getValue());
				filter.setP9t5(fp9t5.getValue());
				filter.setP10t5(fp10t5.getValue());
				filter.setP11t5(fp11t5.getValue());
				filter.setP12t5(fp12t5.getValue());
				filter.setP13t5(fp13t5.getValue());
				filter.setP14t5(fp14t5.getValue());
				filter.setP15t5(fp15t5.getValue());
				filter.setP16t5(fp16t5.getValue());
				filter.setP17t5(fp17t5.getValue());
				filter.setP18t5(fp18t5.getValue());
				filter.setP19t5(fp19t5.getValue());
				filter.setP20t5(fp20t5.getValue());
				filter.setP21t5(fp21t5.getValue());
				filter.setP22t5(fp22t5.getValue());
				filter.setP23t5(fp23t5.getValue());
				filter.setP24t5(fp24t5.doubleValue());
				filter.setP25t5(fp25t5.doubleValue());
				filter.setP26t5(fp26t5.getValue());
				filter.setP27t5(fp27t5.getValue());
				filter.setP28t5(fp28t5.getValue());
				filter.setP29t5(fp29t5.getValue());
				filter.setP30t5(fp30t5.getValue());
				filter.setP31t5(fp31t5.getValue());
				filter.setP32t5(fp32t5.getValue());
				filter.setP33t5(fp33t5.getValue());
				filter.setP34t5(Integer.parseInt(fp34t5.getValue()));
				
			}
			else
			{
				current.setP3t5(p3t5.getValue());
				current.setP4t5(p4t5.getValue());
				current.setP5t5(p5t5.getValue());
				current.setP6t5(p6t5.getValue());
				current.setP7t5(p7t5.getValue());
				current.setP8t5(p8t5.getValue());
				current.setP9t5(p9t5.getValue());
				current.setP10t5(p10t5.getValue());
				current.setP11t5(p11t5.getValue());
				current.setP12t5(p12t5.getValue());
				current.setP13t5(p13t5.getValue());
				current.setP14t5(p14t5.getValue());
				current.setP15t5(p15t5.getValue());
				current.setP16t5(p16t5.getValue());
				current.setP17t5(p17t5.getValue());
				current.setP18t5(p18t5.getValue());
				current.setP20t5(p20t5.getValue());
				current.setP22t5(p22t5.getValue());
				current.setP24t5(p24t5.doubleValue());
				current.setP25t5(p25t5.doubleValue());
				current.setP26t5(p26t5.getValue());
				current.setP27t5(p27t5.getValue());
				current.setP28t5(p28t5.getValue());
				current.setP29t5(p29t5.getValue());
				current.setP30t5(p30t5.getValue());
				current.setP33t5(p33t5.getValue());
				current.setP34t5(Integer.parseInt(p34t5.getValue()));
				AgreementService.update(current);
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
			filter = new AgreementFilter();
		}
		onClick$btn_back();
		frmgrd.setVisible(true);
		addgrd.setVisible(false);
		fgrd.setVisible(false);
		CheckNull.clearForm(addgrd);
		CheckNull.clearForm(fgrd);
		refreshModel(_startPageNumber);
	}
	
	// ******************** Confirm  *************************** //
	public void onClick$btn_confirm()
	{
		sendConfirm(1, String.valueOf(current.getP34t5()), current);
	}
	
	public void onClick$btn_reject()
	{
		sendConfirm(0, String.valueOf(current.getP34t5()), current);
	}
	
	private void sendConfirm(int action, String docnum, Object obj)
	{
		if (contractmain == null)
		{
			contractmain = (Window) execution.getDesktop().getPage("contract").getFellow("contractmain");
		}
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("inn", ((String) session.getAttribute("BankINN")));
		params.put("idn", idn);
		params.put("action", action + "");
		params.put("docnum", docnum);
		params.put("obj", obj);
		Events.sendEvent("onConfirmDocument", contractmain, params);
	}
	// ********************************************************************************//
	
}
