package com.is.tf.generalpayments;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Longbox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Row;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;
import org.zkoss.zul.event.PagingEvent;

import com.is.ISLogger;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.RefData;
import com.is.utils.Res;

@SuppressWarnings("serial")
public class GeneralPaymentsViewCtrl extends GenericForwardComposer
{
	private Window generalpaymentsmain, generalpaymentsummawnd;
	private Div generalpaymentsmaindiv;
	private Div accdiv, gpdiv;
	private Toolbarbutton btn_lastgp, btn_lastacc;
	private Toolbarbutton btn_nextgp, btn_nextacc;
	private Toolbarbutton btn_prevgp, btn_prevacc;
	private Toolbarbutton btn_firstgp, btn_firstacc;
	private List<RefData> branches = new ArrayList<RefData>();
	private List<RefData> branchesall = new ArrayList<RefData>();
	
	// Account
	private Listbox accdataGrid;
	private Paging accountPaging;
	private Grid fgrdacc;
	private Textbox faccbranchtext, faccid, faccname;
	private RefCBox faccbranch;
	private int _pageSizeAcc = 7;
	private int _startPageNumberAcc = 0;
	private int _totalSizeAcc = 0;
	public Account currentacc = new Account();
	public AccountFilter filteracc = new AccountFilter();
	AccountPagingListModel modelacc = null;
	// General payments
	private Listbox gpdataGrid;
	private Paging documentPaging;
	private Grid fgrdgp;
	private Datebox fgpv_date_from, fgpv_date_to;
	private Textbox fgpbranchtext, fgpbranchcltext, fgpbranchcotext, fgpacccl, fgpaccco;
	private Longbox fgpid;
	private RefCBox fgpbranch, fgpbranchcl, fgpbranchco;
	private Row row_cl, row_co;
	private Decimalbox generalpaymentsummawnd$gpsumma_idn, generalpaymentsummawnd$gpsumma;
	private Date dtto = new Date();
	private Date dtfrom = GeneralPaymentService.addMonths(dtto, -72);
	private int _pageSizeGP = 7;
	private int _startPageNumberGP = 0;
	private int _totalSizeGP = 0;
	public General currentgp = new General();
	public GeneralFilter filtergp = new GeneralFilter();
	GPPagingListModel modelgp = null;
	
	public Tf_general_payment currenttfgp = new Tf_general_payment();
	
	private AnnotateDataBinder binder;
	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	
	private String alias, branch1;
	private String _idn, _inn, _currency, _acc_type;
	private String _obj_uid = "";
	private String _obj_id = "";
	private String _sub_obj_id = "";
	private HashMap<String, Object> _params = new HashMap<String, Object>();
	
	public GeneralPaymentsViewCtrl()
	{
		super('$', false, false);
	}
	
	@Override
	public void doAfterCompose(Component comp) throws Exception
	{
		super.doAfterCompose(comp);
		binder = new AnnotateDataBinder(comp);
		binder.bindBean("currentacc", this.currentacc);
		binder.bindBean("currentgp", this.currentgp);
		binder.bindBean("currenttfgp", this.currenttfgp);
		binder.loadAll();
		alias = (String) session.getAttribute("alias");
		branch1 = (String) session.getAttribute("branch");
		alias = "bank1066";
		branch1 = "01066";
		
		String[] parameter = (String[]) param.get("ht");
		if (parameter != null)
		{
			// _pageSizeAcc = Integer.parseInt( parameter[0])/36;
			// accdataGrid.setRows(Integer.parseInt( parameter[0])/36);
		}
		parameter = (String[]) param.get("idn");
		if (parameter != null)
		{
			_idn = parameter[0];
			_inn = _idn.substring(9, 18);
		}
		parameter = (String[]) param.get("currency");
		if (parameter != null)
		{
			_currency = parameter[0];
		}
		parameter = (String[]) param.get("acc_type");
		if (parameter != null)
		{
			_acc_type = parameter[0];
		}
		System.out.println(_idn + " --> " + _inn + "; cur = " + _currency + "; acc = " + _acc_type);
		
		branches = GeneralPaymentService.geCurMfo(alias);
		branchesall = GeneralPaymentService.getMfoAll(alias);
		faccbranch.setModel(new ListModelList(branches));
		faccbranchtext.setValue(branch1);
		fgpbranch.setModel(new ListModelList(branches));
		fgpbranchcl.setModel(new ListModelList(branches));
		fgpbranchco.setModel(new ListModelList(branches));
		
		accdataGrid.setItemRenderer(new ListitemRenderer()
		{
			@SuppressWarnings("unchecked")
			public void render(Listitem row, Object data) throws Exception
			{
				Account pAccount = (Account) data;
				row.setValue(pAccount);
				row.appendChild(new Listcell(pAccount.getBranch()));
				row.appendChild(new Listcell(pAccount.getId()));
				/*
				 * row.appendChild(new Listcell(pAccount.getAcc_bal()));
				 * row.appendChild(new Listcell(pAccount.getCurrency()));
				 * row.appendChild(new Listcell(pAccount.getClient()));
				 * row.appendChild(new Listcell(pAccount.getId_order()));
				 */
				row.appendChild(new Listcell(pAccount.getName()));
				/*
				 * row.appendChild(new Listcell(pAccount.getSgn()));
				 * row.appendChild(new Listcell(pAccount.getBal()));
				 * row.appendChild(new Listcell(pAccount.getSign_registr()));
				 * row.appendChild(new Listcell(pAccount.getS_in()));
				 * row.appendChild(new Listcell(pAccount.getS_out()/100+""));
				 * row.appendChild(new Listcell(pAccount.getDt()));
				 * row.appendChild(new Listcell(pAccount.getCt()));
				 * row.appendChild(new Listcell(pAccount.getS_in_tmp()));
				 * row.appendChild(new Listcell(pAccount.getS_out_tmp()));
				 * row.appendChild(new Listcell(pAccount.getDt_tmp()));
				 * row.appendChild(new Listcell(pAccount.getCt_tmp()));
				 * row.appendChild(new Listcell(pAccount.getL_date()));
				 * row.appendChild(new Listcell(pAccount.getDate_open()));
				 * row.appendChild(new Listcell(pAccount.getDate_close()));
				 * row.appendChild(new Listcell(pAccount.getAcc_group_id()));
				 * row.appendChild(new Listcell(pAccount.getState()+""));
				 */
			}
		});
		
		gpdataGrid.setItemRenderer(new ListitemRenderer()
		{
			@SuppressWarnings("unchecked")
			public void render(Listitem row, Object data) throws Exception
			{
				General pGeneral = (General) data;
				row.setValue(pGeneral);
				row.setHeight("40px");
				row.appendChild(new Listcell(pGeneral.getId() + ""));
				row.appendChild(new Listcell(pGeneral.getBranch()));
				row.appendChild(new Listcell(pGeneral.getDoc_num()));
				row.appendChild(new Listcell(df.format(pGeneral.getD_date())));
				row.appendChild(new Listcell(pGeneral.getState_desc()));
				row.appendChild(new Listcell(pGeneral.getBank_cl()));
				row.appendChild(new Listcell(pGeneral.getAcc_cl()));
				row.appendChild(new Listcell(pGeneral.getBank_co()));
				row.appendChild(new Listcell(pGeneral.getAcc_co()));
				row.appendChild(new Listcell(pGeneral.getSumma() + ""));
				row.appendChild(new Listcell(pGeneral.getName_cl()));
				row.appendChild(new Listcell(pGeneral.getName_co()));
				// row.appendChild(new Listcell(pGeneral.getCurrency()));
				// row.appendChild(new Listcell(pGeneral.getType_doc()));
				// row.appendChild(new Listcell(pGeneral.getS_deal_id()+""));
				row.appendChild(new Listcell(CheckNull.isEmpty(pGeneral.getV_date()) ? "" : df.format(pGeneral.getV_date())));
				row.appendChild(new Listcell(pGeneral.getPdc()));
				// row.appendChild(new Listcell(pGeneral.getCash_code()));
				// row.appendChild(new
				// Listcell(pGeneral.getParent_group_id()+""));
				// row.appendChild(new Listcell(pGeneral.getParent_id()+""));
				// row.appendChild(new Listcell(pGeneral.getChild_id()+""));
				// row.appendChild(new Listcell(pGeneral.getKod_err()+""));
				// row.appendChild(new Listcell(pGeneral.getFile_name()));
				// row.appendChild(new Listcell(pGeneral.getErr_general()+""));
				// row.appendChild(new Listcell(pGeneral.getEmp_id()+""));
				// row.appendChild(new Listcell(pGeneral.getId_transh()+""));
				// row.appendChild(new
				// Listcell(pGeneral.getId_transh_purp()+""));
				// row.appendChild(new
				// Listcell(df.format(pGeneral.getVal_date())));
				row.appendChild(new Listcell(pGeneral.getPurpose()));
			}
		});
		
		// init(_idn,"","","", _currency, _acc_type);
	}
	
	public void init(HashMap<String, Object> params)
	{
		try
		{
			_params = params;
			_idn = (String) params.get("idn");
			_inn = _idn.substring(9, 18);
			_obj_uid = (String) params.get("obgect_type");
			_obj_id = (String) params.get("obgect_id");
			_sub_obj_id = (String) params.get("sub_obgect_id");
			_currency = (String) params.get("currency");
			_acc_type = (String) params.get("acc_type");
			if (CheckNull.isEmpty((Date) params.get("date_from")))
			{
				fgpv_date_from.setValue(dtfrom);
			}
			else
			{
				fgpv_date_from.setValue((Date) params.get("date_from"));
			}
			if (CheckNull.isEmpty((Date) params.get("date_to")))
			{
				fgpv_date_to.setValue(dtto);
			}
			else
			{
				fgpv_date_to.setValue((Date) params.get("date_to"));
			}
			
			System.out.println(_idn + " --> " + _inn + "; cur = " + _currency);
			refreshAccModel(_startPageNumberAcc);
			accdiv.setVisible(true);
			gpdiv.setVisible(false);
			generalpaymentsmain.setVisible(true);
			generalpaymentsummawnd.setVisible(false);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			alert(e.getMessage());
		}
	}
	
	public void onPaging$accountPaging(ForwardEvent event)
	{
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumberAcc = pe.getActivePage();
		refreshAccModel(_startPageNumberAcc);
	}
	
	private void refreshAccModel(int activePage)
	{
		filteracc.setInn(_inn);
		filteracc.setCurrency(_currency);
		accountPaging.setPageSize(_pageSizeAcc);
		modelacc = new AccountPagingListModel(activePage, _pageSizeAcc, filteracc, alias);
		_totalSizeAcc = modelacc.getTotalSize(filteracc, alias);
		accountPaging.setTotalSize(_totalSizeAcc);
		accdataGrid.setModel((ListModel) modelacc);
		if (modelacc.getSize() > 0)
		{
			accdataGrid.setSelectedIndex(0);
			sendSelEvtAcc(true);
			this.currentacc = (Account) modelacc.getElementAt(0);
			sendSelEvtAcc(true);
		}
	}
	
	public void onSelect$accdataGrid()
	{
		// currentacc = (Account) accdataGrid.getSelectedItem().getValue();
		sendSelEvtAcc(false);
	}
	
	// Omitted...
	public Account getCurrentacc()
	{
		return currentacc;
	}
	
	public void setCurrentacc(Account currentacc)
	{
		this.currentacc = currentacc;
	}
	
	public void onDoubleClick$accdataGrid()
	{
		if (currentacc != null && !CheckNull.isEmpty(currentacc.getId()))
		{
			accdiv.setVisible(false);
			gpdiv.setVisible(true);
			generalpaymentsmain.setPosition("center,center");
			_startPageNumberGP = 0;
			documentPaging.setActivePage(0);
			refreshGPModel(_startPageNumberGP);
		}
	}
	
	public void onClick$btn_findacc()
	{
		filteracc = new AccountFilter();
		if (!CheckNull.isEmpty(faccbranch.getValue()))
		{
			filteracc.setBranch(faccbranch.getValue());
		}
		if (!CheckNull.isEmpty(faccid.getValue()))
		{
			filteracc.setId(faccid.getValue());
		}
		if (!CheckNull.isEmpty(faccname.getValue()))
		{
			filteracc.setName(faccname.getValue());
		}
		_startPageNumberAcc = 0;
		refreshAccModel(_startPageNumberAcc);
	}
	
	public void onClick$btn_find_cancelacc()
	{
		faccid.setValue("");
		faccname.setValue("");
		onClick$btn_findacc();
	}
	
	public void onClick$btn_find_clearacc()
	{
		faccid.setValue("");
		faccname.setValue("");
	}
	
	public void onClick$btn_firstacc()
	{
		accdataGrid.setSelectedIndex(0);
		sendSelEvtAcc(true);
	}
	
	public void onClick$btn_lastacc()
	{
		accdataGrid.setSelectedIndex(modelacc.getSize() - 1);
		sendSelEvtAcc(true);
	}
	
	public void onClick$btn_prevacc()
	{
		if (accdataGrid.getSelectedIndex() != 0)
		{
			accdataGrid.setSelectedIndex(accdataGrid.getSelectedIndex() - 1);
			sendSelEvtAcc(true);
		}
	}
	
	public void onClick$btn_nextacc()
	{
		if (accdataGrid.getSelectedIndex() != (modelacc.getSize() - 1))
		{
			accdataGrid.setSelectedIndex(accdataGrid.getSelectedIndex() + 1);
			sendSelEvtAcc(true);
		}
	}
	
	private void sendSelEvtAcc(Boolean sendEvt)
	{
		if (accdataGrid.getSelectedIndex() == 0)
		{
			btn_firstacc.setDisabled(true);
			btn_prevacc.setDisabled(true);
		}
		else
		{
			btn_firstacc.setDisabled(false);
			btn_prevacc.setDisabled(false);
		}
		if (accdataGrid.getSelectedIndex() == (modelacc.getSize() - 1))
		{
			btn_nextacc.setDisabled(true);
			btn_lastacc.setDisabled(true);
		}
		else
		{
			btn_nextacc.setDisabled(false);
			btn_lastacc.setDisabled(false);
		}
		if (sendEvt)
		{
			SelectEvent evt = new SelectEvent("onSelect", accdataGrid, accdataGrid.getSelectedItems());
			Events.sendEvent(evt);
		}
	}
	
	public void onInitRenderLater$faccbranch()
	{
		faccbranch.setSelecteditem(branch1);
	}
	
	public void onPaging$documentPaging(ForwardEvent event)
	{
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumberGP = pe.getActivePage();
		refreshGPModel(_startPageNumberGP);
	}
	
	private void refreshGPModel(int activePage)
	{
		prepareFilter();
		/*
		 * filtergp.setBranch(branch1);
		 * filtergp.setV_date_from(fgpv_date_from.getValue());
		 * filtergp.setV_date_to(fgpv_date_to.getValue()); if
		 * (_acc_type.toUpperCase().equalsIgnoreCase("CL")) {
		 * filtergp.setBank_cl(currentacc.getBranch());
		 * filtergp.setAcc_cl(currentacc.getId()); } else {
		 * filtergp.setBank_co(currentacc.getBranch());
		 * filtergp.setAcc_co(currentacc.getId()); }
		 */
		documentPaging.setPageSize(_pageSizeGP);
		modelgp = new GPPagingListModel(activePage, _pageSizeGP, filtergp, alias);
		_totalSizeGP = modelgp.getTotalSize(filtergp, alias);
		documentPaging.setTotalSize(_totalSizeGP);
		gpdataGrid.setModel((ListModel) modelgp);
		if (modelgp.getSize() > 0)
		{
			gpdataGrid.setSelectedIndex(0);
			sendSelEvtGP(true);
			this.currentgp = (General) modelgp.getElementAt(0);
			sendSelEvtGP(true);
		}
	}
	
	public void onSelect$gpdataGrid()
	{
		// currentgp = (General) gpdataGrid.getSelectedItem().getValue();
		sendSelEvtGP(false);
	}
	
	// Omitted...
	public General getCurrentgp()
	{
		return currentgp;
	}
	
	public void setCurrentgp(General currentgp)
	{
		this.currentgp = currentgp;
	}
	
	public void onDoubleClick$gpdataGrid()
	{
		if (currentgp != null)
		{
			try
			{
				currenttfgp = new Tf_general_payment();
				currenttfgp.setIdn(_idn);
				currenttfgp.setObject_type(_obj_uid);
				currenttfgp.setObject_id(_obj_id);
				currenttfgp.setSub_object_id(_sub_obj_id);
				currenttfgp.setBranch(currentgp.getBranch());
				currenttfgp.setGeneral_id(currentgp.getId());
				currenttfgp.setSumma(currentgp.getSumma());
				currenttfgp.setSumma_idn(currentgp.getSumma());
				currenttfgp.setClient_id(currentacc.getClient());
				currenttfgp.setAccount(currentacc.getId());
				currenttfgp.setInn(_inn);
				currenttfgp.setState(0L);
				binder.loadAll();
			}
			catch (Exception e)
			{
				e.printStackTrace();
				ISLogger.getLogger().equals(CheckNull.getPstr(e));
				alert(e.getMessage());
			}
			// generalpaymentsummawnd$gpsumma_idn.setValue(currentgp.getSumma());
			generalpaymentsummawnd.setVisible(true);
			generalpaymentsummawnd$gpsumma_idn.focus();
			generalpaymentsummawnd$gpsumma_idn.select();
		}
	}
	
	public void onClick$btn_findgp()
	{
		filtergp = new GeneralFilter();
		prepareFilter();
		if (!CheckNull.isEmpty(fgpv_date_from.getValue()))
		{
			filtergp.setV_date_from(fgpv_date_from.getValue());
		}
		if (!CheckNull.isEmpty(fgpv_date_to.getValue()))
		{
			filtergp.setV_date_to(fgpv_date_to.getValue());
		}
		if (!CheckNull.isEmpty(fgpbranch.getValue()))
		{
			filtergp.setBranch(fgpbranch.getValue());
		}
		if (!CheckNull.isEmpty(fgpid.getValue()))
		{
			filtergp.setId(fgpid.getValue());
		}
		if (!CheckNull.isEmpty(fgpbranchcl.getValue()))
		{
			filtergp.setBank_cl(fgpbranchcl.getValue());
		}
		if (!CheckNull.isEmpty(fgpacccl.getValue()))
		{
			filtergp.setAcc_cl(fgpacccl.getValue());
		}
		if (!CheckNull.isEmpty(fgpbranchco.getValue()))
		{
			filtergp.setBank_co(fgpbranchco.getValue());
		}
		if (!CheckNull.isEmpty(fgpaccco.getValue()))
		{
			filtergp.setAcc_co(fgpaccco.getValue());
		}
		_startPageNumberAcc = 0;
		documentPaging.setActivePage(0);
		refreshGPModel(_startPageNumberAcc);
	}
	
	private void prepareFilter()
	{
		if (!CheckNull.isEmpty(fgpv_date_from.getValue()))
		{
			filtergp.setV_date_from(fgpv_date_from.getValue());
		}
		else
		{
			filtergp.setV_date_from(dtfrom);
			fgpv_date_from.setValue(dtfrom);
		}
		if (!CheckNull.isEmpty(fgpv_date_to.getValue()))
		{
			filtergp.setV_date_to(fgpv_date_to.getValue());
		}
		else
		{
			filtergp.setV_date_to(dtto);
			fgpv_date_to.setValue(dtto);
		}
		if (!CheckNull.isEmpty(fgpbranch.getValue()))
		{
			filtergp.setBranch(fgpbranch.getValue());
		}
		else
		{
			filtergp.setBranch(currentacc.getBranch());
			fgpbranch.setSelecteditem(currentacc.getBranch());
			fgpbranchtext.setValue(currentacc.getBranch());
			fgpbranch.setDisabled(true);
			fgpbranchtext.setDisabled(true);
		}
		if (_acc_type.toUpperCase().equalsIgnoreCase("CL"))
		{
			row_cl.setVisible(false);
			row_co.setVisible(true);
			fgpbranchcltext.setValue(currentacc.getBranch());
			fgpbranchcl.setSelecteditem(currentacc.getBranch());
			fgpacccl.setValue(currentacc.getId());
			fgpbranchcltext.setDisabled(true);
			fgpbranchcl.setDisabled(true);
			fgpacccl.setDisabled(true);
			fgpbranchcotext.setDisabled(false);
			fgpbranchco.setDisabled(false);
			fgpaccco.setDisabled(false);
			filtergp.setBank_cl(currentacc.getBranch());
			filtergp.setAcc_cl(currentacc.getId());
		}
		else
		{
			row_cl.setVisible(true);
			row_co.setVisible(false);
			fgpbranchcotext.setValue(currentacc.getBranch());
			fgpbranchco.setSelecteditem(currentacc.getBranch());
			fgpaccco.setValue(currentacc.getId());
			fgpbranchcltext.setDisabled(false);
			fgpbranchcl.setDisabled(false);
			fgpacccl.setDisabled(false);
			fgpbranchcotext.setDisabled(true);
			fgpbranchco.setDisabled(true);
			fgpaccco.setDisabled(true);
			filtergp.setBank_co(currentacc.getBranch());
			filtergp.setAcc_co(currentacc.getId());
		}
	}
	
	public void onClick$btn_find_cancelgp()
	{
		fgpv_date_from.setValue(dtfrom);
		fgpv_date_to.setValue(dtto);
		fgpid.setValue(null);
		fgpbranchcltext.setValue("");
		fgpbranchcl.setValue("");
		fgpacccl.setValue("");
		fgpbranchcotext.setValue("");
		fgpbranchco.setValue("");
		fgpaccco.setValue("");
		onClick$btn_findgp();
	}
	
	public void onClick$btn_find_cleargp()
	{
		fgpv_date_from.setValue(dtfrom);
		fgpv_date_to.setValue(dtto);
		fgpid.setValue(null);
		fgpbranchcltext.setValue("");
		fgpbranchcl.setValue("");
		fgpacccl.setValue("");
		fgpbranchcotext.setValue("");
		fgpbranchco.setValue("");
		fgpaccco.setValue("");
	}
	
	public void onClick$btn_firstgp()
	{
		gpdataGrid.setSelectedIndex(0);
		sendSelEvtGP(true);
	}
	
	public void onClick$btn_lastgp()
	{
		gpdataGrid.setSelectedIndex(modelgp.getSize() - 1);
		sendSelEvtGP(true);
	}
	
	public void onClick$btn_prevgp()
	{
		if (gpdataGrid.getSelectedIndex() != 0)
		{
			gpdataGrid.setSelectedIndex(gpdataGrid.getSelectedIndex() - 1);
			sendSelEvtGP(true);
		}
	}
	
	public void onClick$btn_nextgp()
	{
		if (gpdataGrid.getSelectedIndex() != (modelgp.getSize() - 1))
		{
			gpdataGrid.setSelectedIndex(gpdataGrid.getSelectedIndex() + 1);
			sendSelEvtGP(true);
		}
	}
	
	private void sendSelEvtGP(Boolean sendEvt)
	{
		if (gpdataGrid.getSelectedIndex() == 0)
		{
			btn_firstgp.setDisabled(true);
			btn_prevgp.setDisabled(true);
		}
		else
		{
			btn_firstgp.setDisabled(false);
			btn_prevgp.setDisabled(false);
		}
		if (gpdataGrid.getSelectedIndex() == (modelgp.getSize() - 1))
		{
			btn_nextgp.setDisabled(true);
			btn_lastgp.setDisabled(true);
		}
		else
		{
			btn_nextgp.setDisabled(false);
			btn_lastgp.setDisabled(false);
		}
		if (sendEvt)
		{
			SelectEvent evt = new SelectEvent("onSelect", gpdataGrid, gpdataGrid.getSelectedItems());
			Events.sendEvent(evt);
		}
	}
	
	public void onClick$btn_save$generalpaymentsummawnd()
	{
		if (generalpaymentsummawnd$gpsumma_idn.getValue().doubleValue() <= 0)
		{
			alert("Сумма отщепления не может быть равной нулю или отрицательной!");
			return;
		}
		if (generalpaymentsummawnd$gpsumma_idn.getValue().doubleValue() > generalpaymentsummawnd$gpsumma.getValue().doubleValue())
		{
			alert("Сумма отщепления не может быть больше указанной в документе!");
			return;
		}
		currenttfgp.setSumma(currenttfgp.getSumma().multiply(new BigDecimal("100")));
		currenttfgp.setSumma_idn(generalpaymentsummawnd$gpsumma_idn.getValue().multiply(new BigDecimal("100")));
		Res res = Tf_general_paymentService.create(currenttfgp);
		if (res.getCode() == 0)
		{
			currenttfgp.setId(Long.parseLong(res.getName()));
			generalpaymentsummawnd.setVisible(false);
			generalpaymentsmain.setVisible(false);
			_params.put("objectgp", currenttfgp);
			Events.sendEvent("onAddGP", self, _params);
		}
		else
		{
			alert(res.getName());
		}
	}
	
	public void onClick$btn_cancel$generalpaymentsummawnd()
	{
		generalpaymentsummawnd.setVisible(false);
		generalpaymentsmain.setVisible(false);
	}
	
	public void onClick$btn_cancelgp()
	{
		generalpaymentsmain.setVisible(false);
	}
	
	public void onClick$btn_cancelacc()
	{
		generalpaymentsmain.setVisible(false);
	}
	
	public void onClick$btn_backtoacc()
	{
		gpdiv.setVisible(false);
		accdiv.setVisible(true);
		generalpaymentsmain.setPosition("center,center");
	}
	
}
