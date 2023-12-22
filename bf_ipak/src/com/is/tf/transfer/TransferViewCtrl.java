package com.is.tf.transfer;

import java.rmi.RemoteException;
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
import org.zkoss.zul.Combobox;
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

import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.RefData;
import com.sbs.service.BankServiceProxy;
import com.sbs.service.Result;

public class TransferViewCtrl extends GenericForwardComposer
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
	private Toolbarbutton btn_back, btn_confirm, btn_reject;
	private Toolbar tb;
	private Textbox id, p1t29, p0t29, p4t29, p5t29, p6t29, p7t29, p8t29, p13t29, p14t29, p16t29;
	private Textbox aid, ap1t29, ap0t29, ap4t29, ap5t29, ap6t29, ap7t29, ap8t29, ap13t29, ap14t29, ap16t29, ap100t29;
	private Textbox fid, fp1t29, fp0t29, fp4t29, fp5t29, fp6t29, fp7t29, fp8t29, fp11t29, fp12t29, fp13t29, fp14t29, fp16t29, fp100t29;
	private Textbox p9t29, ap9t29, fp9t29, p10t29, ap10t29, fp10t29;
	private Datebox p15t29, ap15t29, fp15t29;
	private RefCBox fp3t29, p3t29, ap3t29, fp2t29, p2t29, p11t29, p12t29, p100t29, ap2t29, ap11t29, ap12t29;
	private Label line1, line2, line3, line4, line5, line6, line7, line8;
	private Decimalbox decim_box;
	private Row row_ap5t29, row_ap6t29, row_ap13t29, row_p5t29, row_p6t29, row_p13t29;
	private Paging transferPaging;
	private int _pageSize = 15;
	private int _startPageNumber = 0;
	private int _totalSize = 0;
	private String alias;
	private boolean _needsTotalSizeUpdate = true;
	private List<RefData> TransType = new ArrayList<RefData>();
	private List<RefData> confirm = new ArrayList<RefData>();
	private List<RefData> status = new ArrayList<RefData>();
	private List<RefData> agreement = new ArrayList<RefData>();
	private Combobox contract_idn;
	private Long idc;
	private String idn;
	
	public TransferFilter filter = new TransferFilter();
	
	PagingListModel model = null;
	ListModelList lmodel = null;
	private AnnotateDataBinder binder;
	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	
	private Transfer current = new Transfer();
	
	public TransferViewCtrl()
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
			// System.out.println("ID  "+idc+" idn  "+idn);
			// System.out.println("Garant  cont_idn "+idn);
		}
		
		line1.setValue(Labels.getLabel("transfer.p2t29t").replaceAll("<br>", "\r\n"));
		line2.setValue(Labels.getLabel("transfer.p5t29t").replaceAll("<br>", "\r\n"));
		line3.setValue(Labels.getLabel("transfer.bank").replaceAll("<br>", "\r\n"));
		line4.setValue(Labels.getLabel("transfer.p6t29t").replaceAll("<br>", "\r\n"));
		line5.setValue(Labels.getLabel("transfer.p13t29t").replaceAll("<br>", "\r\n"));
		line6.setValue(Labels.getLabel("transfer.p8t29t").replaceAll("<br>", "\r\n"));
		line7.setValue(Labels.getLabel("transfer.p16t29t").replaceAll("<br>", "\r\n"));
		line8.setValue(Labels.getLabel("transfer.p100t29t").replaceAll("<br>", "\r\n"));
		
		dataGrid.setItemRenderer(new ListitemRenderer()
		{
			@SuppressWarnings("unchecked")
			public void render(Listitem row, Object data) throws Exception
			{
				Transfer pTransfer = (Transfer) data;
				
				row.setValue(pTransfer);
				row.appendChild(new Listcell(com.is.tf.contract.SPR.getTransferType(pTransfer.getP2t29())));
				row.appendChild(new Listcell(pTransfer.getP5t29() + ""));
				row.appendChild(new Listcell(pTransfer.getP0t29()));
				row.appendChild(new Listcell(pTransfer.getP6t29()));
				row.appendChild(new Listcell(pTransfer.getP13t29()));
				row.appendChild(new Listcell(pTransfer.getP8t29()));
				row.appendChild(new Listcell(pTransfer.getP16t29()));
				row.appendChild(new Listcell(com.is.tf.contract.SPR.getP100Value(pTransfer.getP100t29())));
				/*
				 * row.appendChild(new Listcell(pTransfer.getId()+""));
				 * row.appendChild(new Listcell(pTransfer.getP1t29()));
				 * row.appendChild(new Listcell(pTransfer.getP0t29()));
				 * row.appendChild(new Listcell(pTransfer.getP3t29()));
				 * row.appendChild(new Listcell(pTransfer.getP4t29()));
				 * row.appendChild(new Listcell(pTransfer.getP5t29()+""));
				 * row.appendChild(new Listcell(pTransfer.getP7t29()));
				 * row.appendChild(new Listcell(pTransfer.getP9t29()+""));
				 * row.appendChild(new Listcell(pTransfer.getP10t29()+""));
				 */

			}
		});
		
		TransType = com.is.tf.contract.ContractService.getTransferType(alias);
		confirm = com.is.tf.contract.ContractService.getConfirm();
		status = com.is.tf.contract.ContractService.getStatusDoc();
		agreement = com.is.tf.contract.ContractService.getAgreement(idn, alias);
		p2t29.setModel((new ListModelList(TransType)));
		ap2t29.setModel((new ListModelList(TransType)));
		p3t29.setModel((new ListModelList(agreement)));
		ap3t29.setModel((new ListModelList(agreement)));
		fp2t29.setModel((new ListModelList(TransType)));
		p11t29.setModel((new ListModelList(confirm)));
		p12t29.setModel((new ListModelList(confirm)));
		ap11t29.setModel((new ListModelList(confirm)));
		ap12t29.setModel((new ListModelList(confirm)));
		p100t29.setModel((new ListModelList(status)));
		
		refreshModel(_startPageNumber);
		
	}
	
	public void onPaging$transferPaging(ForwardEvent event)
	{
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModel(_startPageNumber);
	}
	
	private void refreshModel(int activePage)
	{
		transferPaging.setPageSize(_pageSize);
		model = new PagingListModel(activePage, _pageSize, filter, "");
		
		if (_needsTotalSizeUpdate)
		{
			_totalSize = model.getTotalSize(filter, "");
			// _needsTotalSizeUpdate = false;
		}
		
		transferPaging.setTotalSize(_totalSize);
		
		dataGrid.setModel((ListModel) model);
		if (model.getSize() > 0)
		{
			this.current = (Transfer) model.getElementAt(0);
			sendSelEvt();
		}
	}
	
	// Omitted...
	public Transfer getCurrent()
	{
		return current;
	}
	
	public void setCurrent(Transfer current)
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
	
	/*
	 * public void onClick$btn_get() { final BankServiceProxy ws = new
	 * BankServiceProxy((String)session.getAttribute("YESVO_URL")); try { if
	 * (contract_idn.getValue().isEmpty()){ alert("Поле не должно быть пустым");
	 * } else { long id_contract = idc; ContractResult cr =
	 * ws.getContract(((String)session.getAttribute("BankINN")), idn);
	 * com.sbs.service.Transfer[] transf = cr.getContract().getTransfers();
	 * com.sbs.service.TransferResult tr =
	 * ws.getT(((String)(session.getAttribute("BankINN"))), current.getP1t1());
	 * XMLSerializer.write(transf, "c:/transfer.xml"); if (transf != null) { for
	 * (int i = 0; i < transf.length; i++) { TransferService.remove(new
	 * Transfer(), id_contract); Res res=TransferService.create(transf[i], idn,
	 * id_contract); if (res.getCode()==0) {
	 * alert("Успешно загружен Перевод контракта!"); } else {
	 * alert("Ошибка:"+res.getName()); } } } } }
	 */
	public void onClick$btn_confirm() throws Exception, RemoteException
	{
		Short action = 1;
		final BankServiceProxy ws = new BankServiceProxy((String) session.getAttribute("YESVO_URL"));
		Result r = ws.confirmTransfer(((String) (session.getAttribute("BankINN"))), current.getP16t29(), Integer.parseInt(current.getP8t29()), action, (String) session.getAttribute("un"));
		if (r.getStatus() != 0)
		{
			alert("Ошибка: " + r.getGtkId() + " - " + r.getErrorMsg());
			System.out.println("ERROR: GtkId:" + r.getGtkId() + " - " + r.getErrorMsg());
		}
		else
		{
			alert("Контракт Подтвержден для перевода! GtkId:" + r.getGtkId() + " - " + r.getErrorMsg());
		}
	}
	
	public void onClick$btn_reject() throws Exception, RemoteException
	{
		Short action = 0;
		final BankServiceProxy ws = new BankServiceProxy((String) session.getAttribute("YESVO_URL"));
		Result r = ws.confirmTransfer(((String) (session.getAttribute("BankINN"))), current.getP16t29(), Integer.parseInt(current.getP8t29()), action, (String) session.getAttribute("un"));
		if (r.getStatus() != 0)
		{
			alert("Ошибка: " + r.getGtkId() + " - " + r.getErrorMsg());
			System.out.println("ERROR: GtkId:" + r.getGtkId() + " - " + r.getErrorMsg());
		}
		else
		{
			alert("Отклонено! GtkId:" + r.getGtkId() + " - " + r.getErrorMsg());
		}
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
		ap1t29.setValue(idn);
		ap9t29.setValue((String) session.getAttribute("un"));
		frmgrd.setVisible(false);
		addgrd.setVisible(true);
		fgrd.setVisible(false);
		agreement = com.is.tf.contract.ContractService.getAgreement(alias, idn);
		ap3t29.setModel((new ListModelList(agreement)));
	}
	
	public void onClick$btn_search()
	{
		onDoubleClick$dataGrid$grd();
		frmgrd.setVisible(false);
		addgrd.setVisible(false);
		fgrd.setVisible(true);
	}
	
	public void onSelect$p2t29()
	{
		if (p2t29.getValue().equals("1"))
		{
			row_p5t29.setVisible(true);
			row_p6t29.setVisible(false);
			row_p13t29.setVisible(false);
		}
		else if (p2t29.getValue().equals("2"))
		{
			row_p5t29.setVisible(false);
			row_p6t29.setVisible(true);
			row_p13t29.setVisible(true);
		}
	}
	
	public void onSelect$ap2t29()
	{
		if (ap2t29.getValue().equals("1"))
		{
			row_ap5t29.setVisible(true);
			row_ap6t29.setVisible(false);
			row_ap13t29.setVisible(false);
		}
		else if (ap2t29.getValue().equals("2"))
		{
			row_ap5t29.setVisible(false);
			row_ap6t29.setVisible(true);
			row_ap13t29.setVisible(true);
		}
	}
	
	public void onClick$btn_save()
	{
		try
		{
			final BankServiceProxy ws = new BankServiceProxy("http://91.213.31.234:8892/yeisvo_bank/service");
			com.sbs.service.Transfer trf = new com.sbs.service.Transfer();
			
			if (addgrd.isVisible())
			{
				TransferService.create(new Transfer(

				// Long.parseLong(aid.getValue()),
				// ap1t29.getValue(),
				// ap0t29.getValue(),
						ap2t29.getValue(),
						ap3t29.getValue(),
						ap4t29.getValue(),
						ap5t29.getValue(),
						ap6t29.getValue(),
						ap7t29.getValue(),
						ap8t29.getValue(),
						ap9t29.getValue(),
						ap10t29.getValue(),
						ap11t29.getValue(),
						ap12t29.getValue(),
						ap13t29.getValue(),
						ap14t29.getValue(),
						ap15t29.getValue(),
						ap16t29.getValue(),
						ap100t29.getValue()
						));
				CheckNull.clearForm(addgrd);
				frmgrd.setVisible(true);
				addgrd.setVisible(false);
				fgrd.setVisible(false);
			}
			else if (fgrd.isVisible())
			{
				filter = new TransferFilter();
				
				Long.parseLong(fid.getValue());
				filter.setP1t29(fp1t29.getValue());
				filter.setP0t29(fp0t29.getValue());
				filter.setP2t29(fp2t29.getValue());
				filter.setP3t29(fp3t29.getValue());
				filter.setP4t29(fp4t29.getValue());
				filter.setP5t29(fp5t29.getValue());
				filter.setP6t29(fp6t29.getValue());
				filter.setP7t29(fp7t29.getValue());
				filter.setP8t29(fp8t29.getValue());
				filter.setP9t29(fp9t29.getValue());
				filter.setP10t29(fp10t29.getValue());
				
			}
			else
			{
				
				Long.parseLong(id.getValue());
				current.setP1t29(p1t29.getValue());
				current.setP0t29(p0t29.getValue());
				current.setP2t29(p2t29.getValue());
				current.setP3t29(p3t29.getValue());
				current.setP4t29(p4t29.getValue());
				current.setP5t29(p5t29.getValue());
				current.setP6t29(p6t29.getValue());
				current.setP7t29(p7t29.getValue());
				current.setP8t29(p8t29.getValue());
				current.setP9t29(p9t29.getValue());
				current.setP10t29(p10t29.getValue());
				// TransferService.update(current);
				/*
				 * TransferResult ar =
				 * ws.saveTransfer(((String)(session.getAttribute("BankINN"))),
				 * p1t29.getValue() , getTrs(current)); if (ar.getStatus() == 0)
				 * { refreshModel(_startPageNumber);
				 * alert("Сохранение успешно"); } else {
				 * alert("Error:"+ar.getStatus()+"; GTKid:" +ar.getGtkId()+
				 * "; Text:" +ar.getErrorMsg()); }
				 */
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
			filter = new TransferFilter();
		}
		onClick$btn_back();
		frmgrd.setVisible(true);
		addgrd.setVisible(false);
		fgrd.setVisible(false);
		CheckNull.clearForm(addgrd);
		CheckNull.clearForm(fgrd);
		refreshModel(_startPageNumber);
	}
	
	private com.sbs.service.Transfer getTrs(Transfer acr)
	{
		java.util.Calendar cal = java.util.Calendar.getInstance();
		com.sbs.service.Transfer res = new com.sbs.service.Transfer();
		// res.setP0T37(Integer.parseInt(acr.getP0t37()));
		res.setP2T29(Short.parseShort(acr.getP2t29()));
		res.setP3T29(acr.getP3t29());
		// res.setP4T29(acr.getP4t29());
		res.setP5T29((acr.getP5t29()));
		res.setP6T29(acr.getP6t29());
		res.setP7T29(acr.getP7t29());
		res.setP8T29(Integer.parseInt(acr.getP8t29()));
		// res.setP9T29(Short.parseShort("", acr.getP9t29()));
		// res.setP10T29(Integer.parseInt("", acr.getP10t29()));
		
		return res;
	}
	
	private void setCurrent()
	{
		if (current != null)
		{
			if (current.getP2t29().equals("1"))
			{
				row_p5t29.setVisible(true);
				row_p6t29.setVisible(false);
				row_p13t29.setVisible(false);
			}
			else if (current.getP2t29().equals("2"))
			{
				row_p5t29.setVisible(false);
				row_p6t29.setVisible(true);
				row_p13t29.setVisible(true);
			}
		}
	}
	
}
