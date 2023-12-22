package com.is.tf.specification;

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

public class SpecificationViewCtrl extends GenericForwardComposer
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
	private Textbox id, p0t3, p1t3, p2t3, p5t3, p8t3, p11t3,p12t3,p13t3,p100t3;
	private Textbox aid, ap0t3, ap1t3, ap2t3, ap5t3, ap8t3;
	private Textbox fid, fp0t3, fp1t3, fp2t3, fp5t3, fp8t3;
	private RefCBox p6t3, ap6t3, fp6t3;
	private Datebox p3t3, p7t3, ap3t3, ap7t3, fp3t3, fp7t3, p14t3;
	private Decimalbox p4t3, ap4t3, fp4t3;
	private Paging specificationPaging;
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
    private Label line9;
    private Label line10;
	
	public SpecificationFilter filter = new SpecificationFilter();
	
	PagingListModel model = null;
	ListModelList lmodel = null;
	private AnnotateDataBinder binder;
	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	
	private Specification current = new Specification();
	
	public SpecificationViewCtrl()
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
		
		line1.setValue(Labels.getLabel("specification.p11t3tab").replaceAll("<br>", "\r\n"));
	    line2.setValue(Labels.getLabel("specification.p3t3tab").replaceAll("<br>", "\r\n"));
	    line3.setValue(Labels.getLabel("specification.p4t3tab").replaceAll("<br>", "\r\n"));
	    line4.setValue(Labels.getLabel("specification.p6t3tab").replaceAll("<br>", "\r\n"));
	    line5.setValue(Labels.getLabel("specification.p102t3tab").replaceAll("<br>", "\r\n"));
	    line6.setValue(Labels.getLabel("specification.p12t3tab").replaceAll("<br>", "\r\n"));
	    line7.setValue(Labels.getLabel("specification.p8t3tab").replaceAll("<br>", "\r\n"));
	    line8.setValue(Labels.getLabel("specification.p13t3tab").replaceAll("<br>", "\r\n"));
	    line9.setValue(Labels.getLabel("specification.p14t3tab").replaceAll("<br>", "\r\n"));
	    line10.setValue(Labels.getLabel("specification.p100t3tab").replaceAll("<br>", "\r\n"));
		
		dataGrid.setItemRenderer(new ListitemRenderer()
		{
			@SuppressWarnings("unchecked")
			public void render(Listitem row, Object data) throws Exception
			{
				Specification pSpecification = (Specification) data;
				
				row.setValue(pSpecification);
				
				row.appendChild(new Listcell(pSpecification.getP11t3()));
				row.appendChild(new Listcell(pSpecification.getP3t3()+""));
				row.appendChild(new Listcell(pSpecification.getP4t3()+""));
				row.appendChild(new Listcell(pSpecification.getP6t3()));
				row.appendChild(new Listcell(""));
				row.appendChild(new Listcell(pSpecification.getP12t3()+""));
				row.appendChild(new Listcell(pSpecification.getP8t3()));
				row.appendChild(new Listcell(pSpecification.getP13t3()+""));
				row.appendChild(new Listcell(pSpecification.getP14t3()+""));
				row.appendChild(new Listcell(com.is.tf.contract.SPR.getP100Value(String.valueOf(pSpecification.getP100t3()))));
				
			}
		});
		
		p6t3.setModel((new ListModelList(com.is.utils.RefDataService.getCurrency(alias))));
		fp6t3.setModel((new ListModelList(com.is.utils.RefDataService.getCurrency(alias))));
		
		refreshModel(_startPageNumber);
		
	}
	
	public void onPaging$specificationPaging(ForwardEvent event)
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
		
		specificationPaging.setPageSize(_pageSize);
		filter.setId_contract(idc);
		model = new PagingListModel(activePage, _pageSize, filter, "");
		
		if (_needsTotalSizeUpdate)
		{
			_totalSize = model.getTotalSize();
			_needsTotalSizeUpdate = false;
		}
		
		specificationPaging.setTotalSize(_totalSize);
		
		dataGrid.setModel((ListModel) model);
		if (model.getSize() > 0)
		{
			this.current = (Specification) model.getElementAt(0);
			sendSelEvt();
		}
	}
	
	// Omitted...
	public Specification getCurrent()
	{
		return current;
	}
	
	public void setCurrent(Specification current)
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
		
		p1t3.setDisabled(true);  
		p2t3.setDisabled(true);  
		p3t3.setDisabled(true);  
		p4t3.setDisabled(true);  
		p5t3.setDisabled(true);  
		p6t3.setDisabled(true);  
		p7t3.setDisabled(true);  
		p8t3.setDisabled(true);  
		p11t3.setDisabled(true); 
		p12t3.setDisabled(true); 
		p13t3.setDisabled(true); 
		p14t3.setDisabled(true); 
		p100t3.setDisabled(true);

		
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
				/*SpecificationService.create(new Specification(

				Long.parseLong(aid.getValue()),
						ap0t3.getValue(),
						ap1t3.getValue(),
						ap2t3.getValue(),
						ap3t3.getValue(),
						ap4t3.doubleValue(),
						ap5t3.getValue(),
						ap6t3.getValue(),
						ap7t3.getValue(),
						ap8t3.getValue()
						));*/
				CheckNull.clearForm(addgrd);
				frmgrd.setVisible(true);
				addgrd.setVisible(false);
				fgrd.setVisible(false);
			}
			else if (fgrd.isVisible())
			{
				filter = new SpecificationFilter();
				
				Long.parseLong(fid.getValue());
				filter.setP0t3(fp0t3.getValue());
				filter.setP1t3(fp1t3.getValue());
				filter.setP2t3(fp2t3.getValue());
				filter.setP3t3(fp3t3.getValue());
				filter.setP4t3(fp4t3.doubleValue());
				filter.setP5t3(fp5t3.getValue());
				filter.setP6t3(fp6t3.getValue());
				filter.setP7t3(fp7t3.getValue());
				filter.setP8t3(fp8t3.getValue());
				
			}
			else
			{
				
				Long.parseLong(id.getValue());
				current.setP0t3(p0t3.getValue());
				current.setP1t3(p1t3.getValue());
				current.setP2t3(p2t3.getValue());
				current.setP3t3(p3t3.getValue());
				current.setP4t3(p4t3.doubleValue());
				current.setP5t3(p5t3.getValue());
				current.setP6t3(p6t3.getValue());
				current.setP7t3(p7t3.getValue());
				current.setP8t3(p8t3.getValue());
				SpecificationService.update(current);
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
			filter = new SpecificationFilter();
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
		sendConfirm(1, String.valueOf(current.getP13t3()), current);
	}
	
	public void onClick$btn_reject()
	{
		sendConfirm(0, String.valueOf(current.getP13t3()), current);
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
	public void onClick$btn_refresh() throws Exception 
	{
		refresh(idn);
	}
	
	public void refresh(String idn) throws Exception
	{
		final BankServiceProxy ws = new BankServiceProxy((String) session.getAttribute("YESVO_URL"));
		ContractResult cr = ws.getContract(((String) session.getAttribute("BankINN")), idn);
		
		com.sbs.service.Specification[] agre = cr.getContract().getSpecifications();
		XMLSerializer.write(agre, "c:/Specification.xml");
		if (agre != null)
		{
			for (int i = 0; i < agre.length; i++)
			{
				SpecificationService.remove(idc);
				
				Res res = SpecificationService.create(agre[i], idn ,  idc);
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
		refreshModel(_startPageNumber);
	}
}
