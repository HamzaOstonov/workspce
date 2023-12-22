package com.is.tieto_globuz.agreements;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.*;
import org.zkoss.zul.event.PagingEvent;

import com.is.tieto_globuz.merchants.MerchantService;
import com.is.tieto_globuz.terminals.Terminal;
import com.is.tieto_globuz.terminals.TerminalFilter;
import com.is.tieto_globuz.terminals.TerminalService;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.Res;

public class AgreementViewCtrl extends GenericForwardComposer
{
    private Div frm;
    private Listbox dataGrid;
    private Paging contactPaging;
    private Div grd;
    private Grid addgrd,frmgrd,fgrd;
    private Toolbarbutton btn_last;
    private Toolbarbutton btn_next;
    private Toolbarbutton btn_prev;
    private Toolbarbutton btn_first;
    private Toolbarbutton btn_add;
    private Toolbarbutton btn_search;
    private Toolbarbutton btn_back;
    private Toolbar tb;
    private Textbox acq_bank,acq_branch,tr_ccy,imprint_fee,imprint_min,imprint_max,pos_fee,pos_min,pos_max,cashback_fee,casback_min,cashback_max,atm_fee,atm_min,atm_max,pos_limit,pos_limit_act,imprint_limit,status,agr_number,algorithm,order_period,bank_pos_fee,bank_pos_min,bank_pos_max,bank_imp_fee,bank_imp_min,bank_imp_max,bank_atm_fee,bank_atm_min,bank_atm_max,bank_algorithm,bank_calc_mode,bank_account;
    private Textbox aacq_bank,aacq_branch,atr_ccy,aimprint_fee,aimprint_min,aimprint_max,apos_fee,apos_min,apos_max,acashback_fee,acasback_min,acashback_max,aatm_fee,aatm_min,aatm_max,apos_limit,apos_limit_act,aimprint_limit,astatus,aagr_number,aalgorithm,aorder_period,abank_pos_fee,abank_pos_min,abank_pos_max,abank_imp_fee,abank_imp_min,abank_imp_max,abank_atm_fee,abank_atm_min,abank_atm_max,abank_algorithm,abank_calc_mode,abank_account;
    private Paging maincurrencyPaging;
    private Datebox agr_date, aagr_date, fagr_date;
    private RefCBox merchant, amerchant, card_type, acard_type, fcard_type;
    private int _pageSize = 15;
    private int _startPageNumber = 0;
    private int _totalSize = 0;
    private boolean _needsTotalSizeUpdate = true;    
    public AgreementFilter filter = new AgreementFilter();
    private String branch, alias;
    private static HashMap<String, String> _actionDesc = new HashMap<String, String>();
    private static HashMap<String, String> _agreementType = new HashMap<String, String>();
    private static HashMap<String, String> _cardType = new HashMap<String, String>();

    PagingListModel model = null;
    ListModelList lmodel = null;
    private AnnotateDataBinder binder;
    SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
    SimpleDateFormat termdf = new SimpleDateFormat("yyyyMMdd");

    private Agreement current = new Agreement();
    private WithMerchant currentWithMerchant = new WithMerchant();
    private On_us currentOn_us = new On_us();
    
    
    private Window agreementSelection;
    private RefCBox agreementSelection$agrSelect; 
    private Toolbarbutton agreementSelection$selectBtn, agreementSelection$cancelBtn;
    private String currentAgrType = null;
    
    private RefCBox fmerchant, fagreement_type, faction;
    private Textbox fid;
    
    private Grid addOnUs;
    private Textbox iss_cmi_onUs, imprint_fee_onUs, imprint_min_onUs, imprint_max_onUs, pos_fee_onUs, pos_min_onUs, pos_max_onUs, cashback_fee_onUs, cashback_min_onUs, cashback_max_onUs, atm_fee_onUs, atm_min_onUs, atm_max_onUs, tr_ccy_onUs, bin_onUs, algorithm_onUs;
    private RefCBox card_type_onUs, merchant_onUs;
    
    private Grid iaddOnUs;
    private Textbox iiss_cmi_onUs, iimprint_fee_onUs, iimprint_min_onUs, iimprint_max_onUs, ipos_fee_onUs, ipos_min_onUs, ipos_max_onUs, icashback_fee_onUs, icashback_min_onUs, icashback_max_onUs, iatm_fee_onUs, iatm_min_onUs, iatm_max_onUs, itr_ccy_onUs, ibin_onUs, ialgorithm_onUs;
    private RefCBox icard_type_onUs, imerchant_onUs;
    
    public AgreementViewCtrl() 
    {
    	super('$', false, false);
    }

    @Override
    public void doAfterCompose(Component comp) throws Exception 
    {
		super.doAfterCompose(comp);
		binder = new AnnotateDataBinder(comp);
		binder.bindBean("current", this.current);
		binder.bindBean("currentWithMerchant", this.currentWithMerchant);
		binder.bindBean("currentOn_us", this.currentOn_us);
		binder.loadAll();
		
		String[] parameter = (String[]) param.get("ht");
		if (parameter != null)
		{
			_pageSize = Integer.parseInt( parameter[0]) / 44;
			dataGrid.setRows(Integer.parseInt( parameter[0]) / 44);
		}
		
		branch = (String) session.getAttribute("branch");
		alias = (String) session.getAttribute("alias");
		
		_actionDesc = MerchantService.getActionDesc(alias);
		_agreementType = AgreementService.getAgrTypeDesc(alias);

        dataGrid.setItemRenderer(new ListitemRenderer()
        {
        	@SuppressWarnings("unchecked")
        	public void render(Listitem row, Object data) throws Exception 
        	{
                Agreement pAgreement = (Agreement) data;

                row.setValue(pAgreement);
                
                row.appendChild(new Listcell(pAgreement.getId().toString()));
                row.appendChild(new Listcell(pAgreement.getMerchant()));
                row.appendChild(new Listcell(_agreementType.get(pAgreement.getAgreement_type())));
                row.appendChild(new Listcell(_actionDesc.get(pAgreement.getAction())));
                
        	}
        });
        
        agreementSelection$agrSelect.setModel(new ListModelList(AgreementService.getAgrTypes(alias)));
        
        fagreement_type.setModel(new ListModelList(AgreementService.getAgrTypes(alias)));
        
//        merchant.setModel((new ListModelList(AgreementService.getMerchantId(alias))));       
        amerchant.setModel((new ListModelList(AgreementService.getMerchantId(alias))));
        fmerchant.setModel((new ListModelList(AgreementService.getMerchantId(alias))));  
        
//        card_type.setModel((new ListModelList(AgreementService.getCardType(alias))));
        acard_type.setModel((new ListModelList(AgreementService.getCardType(alias)))); 
//        fcard_type.setModel((new ListModelList(AgreementService.getCardType(alias)))); 
        
        faction.setModel(new ListModelList(AgreementService.getGlobuzStates(alias)));
        
        card_type_onUs.setModel((new ListModelList(AgreementService.getCardType(alias)))); 
        merchant_onUs.setModel((new ListModelList(AgreementService.getMerchantId(alias))));
//        
        aagr_date.setValue(new Date());
        

        refreshModel(_startPageNumber);
    }

	public void onPaging$maincurrencyPaging(ForwardEvent event)
	{
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModel(_startPageNumber);
	}


	private void refreshModel(int activePage)
	{
	    maincurrencyPaging.setPageSize(_pageSize);
	    model = new PagingListModel(activePage, _pageSize, filter, "");
	
		if(_needsTotalSizeUpdate) 
		{
	        _totalSize = model.getTotalSize(filter, session.getAttribute("alias").toString());
	        _needsTotalSizeUpdate = false;
		}

		maincurrencyPaging.setTotalSize(_totalSize);
		
		dataGrid.setModel((ListModel) model);
		if (model.getSize()>0)
		{
			this.current =(Agreement) model.getElementAt(0);
			sendSelEvt();
		}
		
		filter = new AgreementFilter();
	}



	//Omitted...
	public Agreement getCurrent() 
	{
		return current;
	}
	
	public void setCurrent(Agreement current) 
	{
		this.current = current;
	}
	
	public WithMerchant getCurrentWithMerchant() 
	{
		return currentWithMerchant;
	}
	
	public void setCurrentWithMerchant(WithMerchant currentWithMerchant) 
	{
		this.currentWithMerchant = currentWithMerchant;
	}
	
	public On_us getCurrentOn_us() 
	{
		return currentOn_us;
	}
	
	public void setCurrentOn_us(On_us currentOn_us) 
	{
		this.currentOn_us = currentOn_us;
	}
	
	
	public void onDoubleClick$dataGrid$grd() 
	{
		
		
		if(current.getAgreement_type().equals("2")) {
			grd.setVisible(false);
			frm.setVisible(true);
			frmgrd.setVisible(true);
			addgrd.setVisible(false);
			fgrd.setVisible(false);
			btn_back.setImage("/images/folder.png");
			btn_back.setLabel(Labels.getLabel("grid"));
			
			currentWithMerchant = AgreementService.getWithMerchant(alias, current.getId().toString());
			card_type.setValue(currentWithMerchant.getCard_type());
			merchant.setValue(currentWithMerchant.getMerchant());
			
			binder.loadAll();
		}
		else if(current.getAgreement_type().equals("1")) {
			grd.setVisible(false);
			frm.setVisible(true);
			iaddOnUs.setVisible(true);
			addgrd.setVisible(false);
			fgrd.setVisible(false);
			btn_back.setImage("/images/folder.png");
			btn_back.setLabel(Labels.getLabel("grid"));
			
			currentOn_us = AgreementService.getOn_us(alias, current.getId().toString());
			icard_type_onUs.setValue(currentOn_us.getCard_type());
			imerchant_onUs.setValue(currentOn_us.getMerchant());
			
			binder.loadAll();
		}
	    
//	    if (current != null) merchant.setSelecteditem(current.getMerchant());
	    //if (current != null) card_type.setSelecteditem(current.getCard_type());
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
	    else 
    	{
	    	onDoubleClick$dataGrid$grd();
    	}	    	
	}

	public void onClick$btn_first() 
	{
	    dataGrid.setSelectedIndex(0);
	    sendSelEvt();
	}
	
	public void onClick$btn_last() 
	{
	    dataGrid.setSelectedIndex(model.getSize()-1);
	    sendSelEvt();
	}
	
	public void onClick$btn_prev() 
	{
	    if (dataGrid.getSelectedIndex()!=0)
	    {
	    	dataGrid.setSelectedIndex(dataGrid.getSelectedIndex()-1);
	    	sendSelEvt();
	    }
	}
	
	public void onClick$btn_next() 
	{
	    if (dataGrid.getSelectedIndex()!=(model.getSize()-1))
	    {
		    dataGrid.setSelectedIndex(dataGrid.getSelectedIndex()+1);
		    sendSelEvt();
	    }
	}



	private void sendSelEvt()
	{
	    if (dataGrid.getSelectedIndex()==0)
	    {
            btn_first.setDisabled(true);
            btn_prev.setDisabled(true);
	    }
	    else
	    {
            btn_first.setDisabled(false);
            btn_prev.setDisabled(false);
	    }
	    if(dataGrid.getSelectedIndex()==(model.getSize()-1))
	    {
            btn_next.setDisabled(true);
            btn_last.setDisabled(true);
	    }
	    else
	    {
            btn_next.setDisabled(false);
            btn_last.setDisabled(false);
	    }
	    
	    SelectEvent evt = new SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
	    Events.sendEvent(evt);
	}


	public void onClick$btn_add() 
	{
//	    onDoubleClick$dataGrid$grd();
//	    frmgrd.setVisible(false);
//	    addgrd.setVisible(true);
//	    fgrd.setVisible(false);
		
		agreementSelection.setVisible(true);
	}

	public void onClick$btn_search() 
	{
//	    onDoubleClick$dataGrid$grd();
//	    frmgrd.setVisible(false);
//	    addgrd.setVisible(false);
//	    fgrd.setVisible(true);
		
		grd.setVisible(false);
		frm.setVisible(true);
		fgrd.setVisible(true);
		
	}


	public void onClick$btn_save() 
	{
		aagr_date.setValue(new Date());
		boolean needClear = true;

		try
		{
			if (addgrd.isVisible())
			{
				if(acard_type.getValue().equals("") || 
					amerchant.getValue().equals("") ||
					aacq_bank.getValue().equals("") ||
					aacq_branch.getValue().equals("") ||
					atr_ccy.getValue().equals("") ||
					aimprint_fee.getValue().equals("") ||
					aimprint_min.getValue().equals("") ||
					aimprint_max.getValue().equals("") ||
					apos_fee.getValue().equals("") ||
					apos_min.getValue().equals("") || 
					apos_max.getValue().equals("") ||
					acashback_fee.getValue().equals("") ||
					acasback_min.getValue().equals("") ||
					acashback_max.getValue().equals("") ||
					aatm_fee.getValue().equals("") ||
					aatm_min.getValue().equals("") ||
					aatm_max.getValue().equals("") ||
					apos_limit.getValue().equals("") || 
					apos_limit_act.getValue().equals("") ||
					aimprint_limit.getValue().equals("") ||
					astatus.getValue().equals("") ||
					aagr_number.getValue().equals("") ||
					aagr_date.getValue().equals("") ||
					aalgorithm.getValue().equals("") ||
					aorder_period.getValue().equals("") ||
					abank_account.getValue().equals(""))
				{
					needClear = false;
					alert("Заполните все обязательные поля.");
				}
				else if(AgreementService.checkForUniqueness(acard_type.getValue(), amerchant.getValue(), aacq_bank.getValue(), aacq_branch.getValue(), atr_ccy.getValue()))
				{	
					AgreementService.addAgreement(new Agreement(
							null, 
							amerchant.getValue(),
							currentAgrType,
							"I"
							));
					
					AgreementService.create(new WithMerchant(
						acard_type.getValue(),
						amerchant.getValue(),
						aacq_bank.getValue(),
						aacq_branch.getValue(),
						atr_ccy.getValue(),
						aimprint_fee.getValue(),
						aimprint_min.getValue(),
						aimprint_max.getValue(),
						apos_fee.getValue(), 
						apos_min.getValue(), 
						apos_max.getValue(), 
						acashback_fee.getValue(), 
						acasback_min.getValue(), 
						acashback_max.getValue(),
						aatm_fee.getValue(), 
						aatm_min.getValue(), 
						aatm_max.getValue(),
						apos_limit.getValue(), 
						apos_limit_act.getValue(),
						aimprint_limit.getValue(), 
						astatus.getValue(), 
						aagr_number.getValue(),
						aagr_date.getValue(),
						aalgorithm.getValue(), 
						aorder_period.getValue(),
						abank_pos_fee.getValue(), 
						abank_pos_min.getValue(), 
						abank_pos_max.getValue(), 
						abank_imp_fee.getValue(), 
						abank_imp_min.getValue(), 
						abank_imp_max.getValue(), 
						abank_atm_fee.getValue(), 
						abank_atm_min.getValue(), 
						abank_atm_max.getValue(), 
						abank_algorithm.getValue(), 
						abank_calc_mode.getValue(), 
						abank_account.getValue(),					
						"I",
						null
						));
					
//					frmgrd.setVisible(true);
//					fgrd.setVisible(false);
					
					onClick$btn_cancel();
				}
				else
				{
					needClear = false;
					alert("Такой договор уже существует.");
				}
			}
			else if (fgrd.isVisible())
			{
				filter = new AgreementFilter();
				
				filter.setId(fid.getValue().equals("") ? null : Long.valueOf(fid.getValue()));
				filter.setMerchant(fmerchant.getValue());
				filter.setAgreement_type(fagreement_type.getValue());
				filter.setAction(faction.getValue());
				
				CheckNull.clearForm(fgrd);
				grd.setVisible(true);
				fgrd.setVisible(false);
				frm.setVisible(false);
				addgrd.setVisible(false);
				refreshModel(_startPageNumber);
				return;
			}
			else if(addOnUs.isVisible()) {
				AgreementService.addAgreement(new Agreement(
						null, 
						merchant_onUs.getValue(),
						currentAgrType,
						"I"
						));
				
				AgreementService.createOnUs(alias, new On_us(
						null,
						card_type_onUs.getValue(),
						merchant_onUs.getValue(),
						iss_cmi_onUs.getValue(),
						imprint_fee_onUs.getValue(),
						imprint_min_onUs.getValue(),
						imprint_max_onUs.getValue(),
						pos_fee_onUs.getValue(),
						pos_min_onUs.getValue(),
						pos_max_onUs.getValue(),
						cashback_fee_onUs.getValue(),
						cashback_min_onUs.getValue(),
						cashback_max_onUs.getValue(),
						atm_fee_onUs.getValue(),
						atm_min_onUs.getValue(),
						atm_max_onUs.getValue(),
						tr_ccy_onUs.getValue(),
						bin_onUs.getValue(),
						algorithm_onUs.getValue(),
						"I",
						null
						));
				
				onClick$btn_cancel();
			}
			else
			{				
//				if (!current.getAction().equals("S"))
//				{
//					if (!current.getAction().equals("I"))
//					{
//						current.setAction("U");
//					}
//					//AgreementService.update(current);
//				}
				alert("Изменение пока не работает.\n Скоро будет ;)");
				
			}
			if(needClear)
			{
				//onClick$btn_back();
				//refreshModel(_startPageNumber);
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
	    if(fgrd.isVisible()) {
            filter = new AgreementFilter();
	    }
//		onClick$btn_back();
//		frmgrd.setVisible(true);
//		addgrd.setVisible(false);
//		fgrd.setVisible(false);
//		CheckNull.clearForm(addgrd);
//		CheckNull.clearForm(fgrd);
//		refreshModel(_startPageNumber);
		
		grd.setVisible(true);
		frm.setVisible(false);
		addgrd.setVisible(false);
		addOnUs.setVisible(false);
		fgrd.setVisible(false);
		CheckNull.clearForm(addgrd);
		CheckNull.clearForm(fgrd);
		CheckNull.clearForm(addOnUs);
		refreshModel(_startPageNumber);
	}


	public void onClick$cancelBtn$agreementSelection() {
		agreementSelection.setVisible(false);
	}
	
	public void onClick$selectBtn$agreementSelection() {
		grd.setVisible(false);
		frm.setVisible(true);
		currentAgrType = agreementSelection$agrSelect.getValue();
		agreementSelection.setVisible(false);
		
		if(agreementSelection$agrSelect.getValue().equals("2")) {

			addgrd.setVisible(true);			
		}		
		else if(agreementSelection$agrSelect.getValue().equals("1")) {

			addOnUs.setVisible(true);
		}
	}
	
	
	
}
