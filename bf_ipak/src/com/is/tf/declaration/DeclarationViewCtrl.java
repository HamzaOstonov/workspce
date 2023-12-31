package com.is.tf.declaration;

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

import com.is.ISLogger;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.Res;
import com.is.utils.refobj.XMLSerializer;
import com.sbs.service.BankServiceProxy;
import com.sbs.service.ContractResult;

public class DeclarationViewCtrl extends GenericForwardComposer
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
	private Toolbar tb;
	private RefCBox p5t48, p19t48, p17t48;
	private Textbox id, p1t48, p2t48, p3t48,  p6t48, p9t48, p11t48, p12t48, p13t48, p15t48, p16t48;
	private Textbox aid, ap1t48, ap2t48, ap3t48, ap5t48, ap6t48, ap9t48, ap11t48, ap12t48, ap13t48, ap15t48, ap16t48, ap17t48, ap19t48;
	private Textbox fid, fp1t48, fp2t48, fp3t48, fp5t48, fp6t48, fp9t48, fp11t48, fp12t48, fp13t48, fp15t48, fp16t48, fp17t48, fp19t48;
	private Datebox p4t48, p7t48, p8t48, p14t48, ap4t48, ap7t48, ap8t48, ap14t48, fp14t48, fp7t48, fp8t48, fp4t48;
	private Decimalbox p10t48, p18t48, ap10t48, ap18t48, fp10t48, fp18t48;
	private Paging declarationPaging;
	private int _pageSize = 15;
	private int _startPageNumber = 0;
	private int _totalSize = 0;
	private boolean _needsTotalSizeUpdate = true;
	private Toolbarbutton btn_confirm, btn_reject, btn_save;
    private Window contractmain = null; 
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
	
	public DeclarationFilter filter = new DeclarationFilter();
	
	PagingListModel model = null;
	ListModelList lmodel = null;
	private AnnotateDataBinder binder;
	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	
	private Declaration current = new Declaration();
	
	public DeclarationViewCtrl()
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
    	
		line1.setValue(Labels.getLabel("declaration.p2t48tab").replaceAll("<br>", "\r\n"));
	    line2.setValue(Labels.getLabel("declaration.p7t48tab").replaceAll("<br>", "\r\n"));
	    line3.setValue(Labels.getLabel("declaration.p3t48tab").replaceAll("<br>", "\r\n"));
	    line4.setValue(Labels.getLabel("declaration.p16t48tab").replaceAll("<br>", "\r\n"));
	    line5.setValue(Labels.getLabel("declaration.p18t48tab").replaceAll("<br>", "\r\n"));
	    line6.setValue(Labels.getLabel("declaration.p17t48tab").replaceAll("<br>", "\r\n"));
	    line7.setValue(Labels.getLabel("declaration.p1t48tab").replaceAll("<br>", "\r\n"));
	    line8.setValue(Labels.getLabel("declaration.p20t48tab").replaceAll("<br>", "\r\n"));
		
		dataGrid.setItemRenderer(new ListitemRenderer()
		{
			@SuppressWarnings("unchecked")
			public void render(Listitem row, Object data) throws Exception
			{
				Declaration pDeclaration = (Declaration) data;
				
				row.setValue(pDeclaration);
				
				row.appendChild(new Listcell(pDeclaration.getP2t48()+""));
				row.appendChild(new Listcell(pDeclaration.getP7t48()+""));
				row.appendChild(new Listcell(pDeclaration.getP3t48()));
				row.appendChild(new Listcell(pDeclaration.getP16t48()));
				row.appendChild(new Listcell(pDeclaration.getP18t48()+""));
				row.appendChild(new Listcell(pDeclaration.getP17t48() + " ( " + com.is.tf.contract.SPR.getVal(String.valueOf(pDeclaration.getP17t48())) + " ) "));
				row.appendChild(new Listcell(pDeclaration.getP1t48()));
				row.appendChild(new Listcell(""));
				
			}
		});
		p5t48.setModel((new ListModelList(com.is.utils.RefDataService.getCountry(alias))));
		p17t48.setModel((new ListModelList(com.is.utils.RefDataService.getCurrency(alias))));
		p19t48.setModel((new ListModelList(com.is.utils.RefDataService.getCountry(alias))));
		refreshModel(_startPageNumber);
		
	}
	
	public void onPaging$declarationPaging(ForwardEvent event)
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
		
		declarationPaging.setPageSize(_pageSize);
		filter.setId_contract(idc);
		model = new PagingListModel(activePage, _pageSize, filter, "");
		
		if (_needsTotalSizeUpdate)
		{
			_totalSize = model.getTotalSize();
			_needsTotalSizeUpdate = false;
		}
		
		declarationPaging.setTotalSize(_totalSize);
		
		dataGrid.setModel((ListModel) model);
		if (model.getSize() > 0)
		{
			this.current = (Declaration) model.getElementAt(0);
			sendSelEvt();
		}
	}
	
	// Omitted...
	public Declaration getCurrent()
	{
		return current;
	}
	
	public void setCurrent(Declaration current)
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
		
		p2t48.setDisabled(true);  
		p3t48.setDisabled(true);  
		p4t48.setDisabled(true);  
		p5t48.setDisabled(true);  
		p6t48.setDisabled(true);  
		p7t48.setDisabled(true);  
		p8t48.setDisabled(true);  
		p9t48.setDisabled(true);  
		p10t48.setDisabled(true); 
		p11t48.setDisabled(true); 
		p12t48.setDisabled(true); 
		p13t48.setDisabled(true); 
		p14t48.setDisabled(true); 
		p15t48.setDisabled(true); 
		p16t48.setDisabled(true); 
		p17t48.setDisabled(true); 
		p18t48.setDisabled(true); 
		p19t48.setDisabled(true); 
		
		btn_confirm.setVisible(true);
		btn_reject.setVisible(true);
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
				DeclarationService.create(new Declaration(

				Long.parseLong(aid.getValue()),
						ap2t48.getValue(),
						ap3t48.getValue(),
						ap4t48.getValue(),
						ap5t48.getValue(),
						ap6t48.getValue(),
						ap7t48.getValue(),
						ap8t48.getValue(),
						ap9t48.getValue(),
						ap10t48.doubleValue(),
						ap11t48.getValue(),
						ap12t48.getValue(),
						ap16t48.getValue(),
						ap17t48.getValue(),
						ap18t48.doubleValue(),
						ap19t48.getValue()
						));
				CheckNull.clearForm(addgrd);
				frmgrd.setVisible(true);
				addgrd.setVisible(false);
				fgrd.setVisible(false);
			}
			else if (fgrd.isVisible())
			{
				filter = new DeclarationFilter();
				
				Long.parseLong(fid.getValue());
				filter.setP1t48(fp1t48.getValue());
				filter.setP2t48(fp2t48.getValue());
				filter.setP3t48(fp3t48.getValue());
				filter.setP4t48(fp4t48.getValue());
				filter.setP5t48(fp5t48.getValue());
				filter.setP6t48(fp6t48.getValue());
				filter.setP7t48(fp7t48.getValue());
				filter.setP8t48(fp8t48.getValue());
				filter.setP9t48(fp9t48.getValue());
				filter.setP10t48(fp10t48.doubleValue());
				filter.setP11t48(fp11t48.getValue());
				filter.setP12t48(fp12t48.getValue());
				filter.setP13t48(fp13t48.getValue());
				filter.setP14t48(fp14t48.getValue());
				filter.setP15t48(fp15t48.getValue());
				filter.setP16t48(fp16t48.getValue());
				filter.setP17t48(fp17t48.getValue());
				filter.setP18t48(fp18t48.doubleValue());
				filter.setP19t48(fp19t48.getValue());
				
			}
			else
			{
				
				Long.parseLong(id.getValue());
				current.setP1t48(p1t48.getValue());
				current.setP2t48(p2t48.getValue());
				current.setP3t48(p3t48.getValue());
				current.setP4t48(p4t48.getValue());
				current.setP5t48(p5t48.getValue());
				current.setP6t48(p6t48.getValue());
				current.setP7t48(p7t48.getValue());
				current.setP8t48(p8t48.getValue());
				current.setP9t48(p9t48.getValue());
				current.setP10t48(p10t48.doubleValue());
				current.setP11t48(p11t48.getValue());
				current.setP12t48(p12t48.getValue());
				current.setP13t48(p13t48.getValue());
				current.setP14t48(p14t48.getValue());
				current.setP15t48(p15t48.getValue());
				current.setP16t48(p16t48.getValue());
				current.setP17t48(p17t48.getValue());
				current.setP18t48(p18t48.doubleValue());
				current.setP19t48(p19t48.getValue());
				DeclarationService.update(current);
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
			filter = new DeclarationFilter();
		}
		onClick$btn_back();
		frmgrd.setVisible(true);
		addgrd.setVisible(false);
		fgrd.setVisible(false);
		CheckNull.clearForm(addgrd);
		CheckNull.clearForm(fgrd);
		refreshModel(_startPageNumber);
	}
	
	public void onClick$btn_refresh() throws Exception 
	{
		refresh(idn);
	}
	
	public void refresh(String idn) throws Exception
	{
		final BankServiceProxy ws = new BankServiceProxy((String) session.getAttribute("YESVO_URL"));
		ContractResult cr = ws.getContract(((String) session.getAttribute("BankINN")), idn);
		
		com.sbs.service.Declaration[] agre = cr.getContract().getDeclarations();
		XMLSerializer.write(agre, "c:/Declaration.xml");
		if (agre != null)
		{
			for (int i = 0; i < agre.length; i++)
			{
				DeclarationService.remove(idc);
				
				Res res = DeclarationService.create(agre[i], idc);
				if (res.getCode() == 1)
				{
					alert("������� ���������!");
				}
				else
				{
					alert("������:" + res.getName());
					
				}
			}
		}
		else
		{
			alert("Data not found, BankINN=" + ((String) session.getAttribute("BankINN")));
			ISLogger.getLogger().warn("ERROR onSelect$Agreement: Data not found");
		}
		
		refreshModel(_startPageNumber);
	}
	
	// ******************** Confirm  *************************** //
	public void onClick$btn_confirm()
	{
		sendConfirm(1, String.valueOf(current.getP1t48()), current);
	}
	
	public void onClick$btn_reject()
	{
		sendConfirm(0, String.valueOf(current.getP1t48()), current);
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
