package com.is.creditanket;

import java.math.BigDecimal;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Cell;
import org.zkoss.zul.Column;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Decimalbox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
//import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Longbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.event.PagingEvent;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.bpri.BproductFilter;
import com.is.bpri.BprLdGr.BprLdGrService;
import com.is.bpri.ldhisrate.LdHisRateService;
import com.is.bpri.utils.Utils;
import com.is.creditanket.grids.GridForGuars;
import com.is.creditanket.table_models.CurrentCredit;
import com.is.creditanket.table_models.Ld_char;
import com.is.creditanket.table_models.Ld_exp;
import com.is.creditanket.table_models.Ld_forms;
import com.is.creditanket.table_models.Ld_gr;
import com.is.creditanket.table_models.Ld_guar_blocks;
import com.is.creditanket.table_models.Ld_guar_cadastre;
import com.is.creditanket.table_models.Ld_guar_car;
import com.is.creditanket.table_models.Ld_guar_equipment;
import com.is.creditanket.table_models.Ld_guarr;
import com.is.creditanket.table_models.Ld_rate;
import com.is.creditanket.table_models.V_ld_account;
import com.is.creditapp.CreditApp;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.Res;

public class CreditViewCtrl extends GenericForwardComposer{

	private static final long serialVersionUID = 1L;
	
	private Listbox dataGrid;
	private Div grid,addgrd;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
	private String alias = null,mfo = null;
	private HashMap<Long, String> statusMap = new HashMap<Long, String>();
	private HashMap<Integer, String> stateMap = new HashMap<Integer, String>();
	private CurrentCredit current = new CurrentCredit();
	private CurrentCredit filter = new CurrentCredit();
	private PagingListModel model = null;
	private Paging creditPaging;
	private int _totalSize = 0,_pageSize = 12,_startPageNumber = 0;
	private Intbox 
//	faza_abr,
	emp_count,cl_num,day,num;
	private RefCBox rate_type,rate_method,pay_method,pay_period,graf_method,type_id,kred_id,kred_id_cb,use_branch,klass_id,klassp_id,is_tax,acceptance,cres,shifr_id,sred_id,term_type,branch,is_sec,acc_state,calc_id,is_inv,currency_inv,is_ld,is_building,currency,t_type,status;
	private Longbox niki_id;
	private Textbox al_num,prod_name,sub_addr,ld_num,crc_num,p_num,sub_name,eq_num,t_author,target;
	private Datebox date_from,date_to,eq_date,p_date,crc_date,t_date,ld_date,date_close,date_fee,date_end;
	private Doublebox amount,amount_inv;
	private Row commision_row
//	,abr_row
	;
	private Div guars;
	private Vbox ld_gr_radio,ld_exp_radio,ld_exp_radio_btn;
	private Rows graf_rows,rate_row,acc_rows;
	private Label ld_amount_gr,ld_graff_amount;
	private Decimalbox ld_amount;
	private Cell period_cell;
	private Column day_column,period_column;
	private String form_id = null,action = "";
	private Toolbarbutton btn_confirm,btn_close;
	private Radiogroup main_radio;
	private Button fill_graf,recall_graf,btn_add_exp,btn_add_rate;
	private Toolbarbutton btn_save;
	private Column btn_save_rate,btn_del_rate;
	private Ld_exp selected_exp = null;
//	private Listheader action_lh;
//	private Ld_exp savingLd_exp = null;
//	private AnnotateDataBinder bind = new AnnotateDataBinder(); 

	public CreditViewCtrl() {
		super('$',false,false);
	}
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		alias = (String) session.getAttribute("alias");
		mfo = (String) session.getAttribute("branch");
		String parametr[] = (String[]) param.get("form_id");
		if(parametr!=null){
			form_id = parametr[0];
			System.out.println("form_id: "+form_id);
		}
		parametr = (String[]) param.get("client_id");
		parametr = (String[]) param.get("search_clients");
		//System.out.println("clientzayavka-----:"+parametr[0]);
		if(parametr!=null){
			if(filter == null) filter = new CurrentCredit();
			Ld_forms forms = new Ld_forms();
			forms.setClient(parametr[0]);
			
			
			//filter.setLd_forms(forms);
			System.out.println("forms----:"+parametr[0]);
			//filter.setLd_forms(parametr[0]);
				
		}		
		
		CreditService.getStatus(statusMap, alias);
		CreditService.getState(stateMap, alias);
		setModels();
		dataGrid.setItemRenderer(new ListitemRenderer() {
			
			@Override
			public void render(Listitem row, Object data) throws Exception {
				CurrentCredit curr = (CurrentCredit) data;
				row.setValue(curr);
				row.appendChild(new Listcell(curr.getLd_char().getBranch()));
				row.appendChild(new Listcell(curr.getLd_char().getId()+""));
				row.appendChild(new Listcell(curr.getLd_char().getNiki_id()+""));
				row.appendChild(new Listcell(sdf.format(curr.getLd_forms().getD_date())));
				row.appendChild(new Listcell(curr.getLd_forms().getClient()));
				System.out.println("creditanket------:"+curr.getLd_forms().getClient());
				row.appendChild(new Listcell(CreditService.getClientName(curr.getLd_forms().getClient(), curr.getLd_char().getBranch(), Utils.getAlias(curr.getLd_char().getBranch()))));
				row.appendChild(new Listcell(curr.getLd_char().getCurrency()));
				String formattedDouble = new DecimalFormat("#0.00").format(curr.getLd_char().getLd_amount());
				row.appendChild(new Listcell(formattedDouble));
				row.appendChild(new Listcell(stateMap.get(curr.getLd_forms().getState())));
				row.appendChild(new Listcell(statusMap.get(curr.getLd_char().getStatus())));
				row.appendChild(new Listcell(CreditService.getNikiStateName(curr.getLd_char().getId()+"", curr.getLd_char().getBranch(), alias)));
				Listcell lc = new Listcell();
				Button confirm_btn = new Button();
				if(curr.getLd_forms().getState()!=1) confirm_btn.setVisible(false);
				if(curr.getLd_forms().getState()==1) confirm_btn.setTooltiptext("Утвердить Анкету №"+curr.getLd_char().getId());
				confirm_btn.setAttribute("state", curr.getLd_forms().getState());
				confirm_btn.setAttribute("curr", curr);
				confirm_btn.setImage("/images/approved.png");
				confirm_btn.addEventListener(Events.ON_CLICK, new EventListener() {
					
					@Override
					public void onEvent(Event evt) throws Exception {
						Button btn = (Button) evt.getTarget();
						Integer state = (Integer) btn.getAttribute("state");
						current = (CurrentCredit) btn.getAttribute("curr");
						if(state==1) onClick$btn_confirm();
						else { alert("Почти работает"); }
					}
				});
				lc.appendChild(confirm_btn);
				Button cancel_btn = new Button();
				String cancel_btn_text = "";
				if(curr.getLd_forms().getState()!=1 && curr.getLd_forms().getState()!=2) cancel_btn.setVisible(false);
				if(curr.getLd_forms().getState()==1) cancel_btn_text = "Удаление Анкеты №"+curr.getLd_char().getId();
				else if(curr.getLd_forms().getState()==2) cancel_btn_text = "Закрытие Анкеты №"+curr.getLd_char().getId();
				cancel_btn.setTooltiptext(cancel_btn_text);
				cancel_btn.setAttribute("state", curr.getLd_forms().getState());
				cancel_btn.setAttribute("curr", curr);
				cancel_btn.setImage("/images/deletered3.png");
				cancel_btn.addEventListener(Events.ON_CLICK, new EventListener() {
					
					@Override
					public void onEvent(Event evt) throws Exception {
						Button btn = (Button) evt.getTarget();
						Integer state = (Integer) btn.getAttribute("state");
						current = (CurrentCredit) btn.getAttribute("curr");
						if(state==1) CreditService.deleteForm(current.getLd_char().getId(), current.getLd_char().getBranch(), Utils.getAlias(current.getLd_char().getBranch()));
						else if(state==2) onClick$btn_close();
						else alert("Нельзя кликать на красные кнопки!");
					}
				});
				lc.appendChild(cancel_btn);
				row.appendChild(lc);
				row.addEventListener(Events.ON_CLICK, new EventListener() {
					
					@Override
					public void onEvent(Event evt) throws Exception {
						Listitem item = (Listitem) evt.getTarget();
						current = (CurrentCredit) item.getValue();
					}
				});
				row.addEventListener(Events.ON_DOUBLE_CLICK, new EventListener() {
					
					@Override
					public void onEvent(Event evt) throws Exception {
						try {
							action = "double";
							Listitem item = (Listitem) evt.getTarget();
							current = (CurrentCredit) item.getValue();
							current = CreditService.getCurrentCredit(current.getLd_char().getId(), current.getLd_char().getBranch(), alias);
							clearForms();
							System.out.println("CB_ID = "+current.getLd_char().getKred_id_cb());
							setBorrower(current.getLd_char(), current.getLd_forms());
							setChar(current.getLd_char());
							setGuarr(current.getLd_guarr());
							setExp(current.getLd_exp());
							setGr(current.getLd_gr());
							setAcc(current.getV_ld_account());
							String[] parameter = (String[]) param.get("gtype");
							if(parameter!=null&&(parameter[0].equals("3")||parameter[0]==null||parameter[0].equals(""))&&current.getLd_forms()!=null){
								if(current.getLd_forms().getState()==1) {
									btn_confirm.setVisible(true);
									btn_close.setVisible(false);
								} else if(current.getLd_forms().getState()==2){
									btn_confirm.setVisible(false);
									btn_close.setVisible(true);
								} else {
									btn_confirm.setVisible(false);
									btn_close.setVisible(false);
								}
							} else {
								btn_confirm.setVisible(false);
								btn_close.setVisible(false);
							}
							grid.setVisible(false);
							addgrd.setVisible(true);
							recall_graf.setVisible(true);
							fill_graf.setVisible(true);
							btn_save.setVisible(true);
							btn_add_exp.setVisible(true);
							btn_add_rate.setVisible(false);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		setEventsForExps();
		refreshModel(_startPageNumber);
	}
	
	private void setModels(){
		branch.setModel(new ListModelList(CreditService.getBranch(alias)));
		is_sec.setModel(new ListModelList(CreditService.getSs_type_ans(alias)));
		acc_state.setModel(new ListModelList(CreditService.getSs_ent_acc(alias)));
		is_ld.setModel(new ListModelList(CreditService.getSs_type_ans(alias)));
		is_building.setModel(new ListModelList(CreditService.getSs_type_ans(alias)));
		currency.setModel(new ListModelList(CreditService.getCurrency(alias)));
		t_type.setModel(new ListModelList(CreditService.getSs_type_ans(alias)));
		status.setModel(new ListModelList(CreditService.getStatus(alias)));
		calc_id.setModel(new ListModelList(CreditService.getCalc_method(alias)));
		is_inv.setModel(new ListModelList(CreditService.getSs_type_ans(alias)));
		currency_inv.setModel(new ListModelList(CreditService.getCurrency(alias)));
		shifr_id.setModel(new ListModelList(CreditService.getShifr_id(alias)));
		sred_id.setModel(new ListModelList(CreditService.getSred_id(alias)));
		term_type.setModel(new ListModelList(CreditService.getTerm_type(alias)));
		type_id.setModel(new ListModelList(CreditService.getType_id(alias)));
		kred_id.setModel(new ListModelList(CreditService.getKred_id(alias)));
		kred_id_cb.setModel(new ListModelList(CreditService.getKred_id_cb(alias)));
		use_branch.setModel(new ListModelList(CreditService.getUse_branch(alias)));
		klass_id.setModel(new ListModelList(CreditService.getKlass_id(alias)));
		klassp_id.setModel(new ListModelList(CreditService.getKlassp_id(alias)));
		is_tax.setModel(new ListModelList(CreditService.getSs_type_ans(alias)));
		acceptance.setModel(new ListModelList(CreditService.getSs_type_ans(alias)));
		cres.setModel(new ListModelList(CreditService.getCres(alias)));
		graf_method.setModel(new ListModelList(BprLdGrService.getGrafMethod("")));
		pay_period.setModel(new ListModelList(BprLdGrService.getPayPeriod("")));
		pay_method.setModel(new ListModelList(LdHisRateService.getPay_method("")));
		rate_method.setModel(new ListModelList(LdHisRateService.getRate_method("")));
		rate_type.setModel(new ListModelList(LdHisRateService.getRate_type("")));
	}
	
	public void onInitRenderLater$kred_id_cb(){
		if(action.equals("double")){
			kred_id_cb.setSelecteditem(current.getLd_char().getKred_id_cb());
		}
	}
	
	private void setVisible_commision(){
		if(type_id.getValue().equals("2")&&kred_id.getValue().equals("02")) commision_row.setVisible(true);
		else commision_row.setVisible(false);
	}
	
	public void onSelect$type_id(){
		setVisible_commision();
	}
	
	public void onSelect$kred_id(){
		setVisible_commision();
	}
	
    public void onPaging$creditPaging(ForwardEvent event){
    	final PagingEvent pe = (PagingEvent) event.getOrigin();
    	_startPageNumber = pe.getActivePage();
    	refreshModel(_startPageNumber);
    }
	
	private void refreshModel(int activePage){
		String[] parametr = (String[]) param.get("client_id");
		if(parametr!=null){
			if(filter == null) filter = new CurrentCredit();
			Ld_forms forms = new Ld_forms();
			forms.setClient(parametr[0]);
			filter.setLd_forms(forms);
		}
		parametr = (String[]) param.get("id_client");
		if(parametr!=null){
			if(filter == null) filter = new CurrentCredit();
			Ld_forms forms = new Ld_forms();
			forms.setClient(parametr[0]);
			filter.setLd_forms(forms);
		}
		if(form_id!=null&&!form_id.equals("")){
			if(filter==null)filter = new CurrentCredit();
			Ld_char ld_char = null;
			if(filter.getLd_char()==null) ld_char = new Ld_char();
			else ld_char = filter.getLd_char();
			ld_char.setId(Long.parseLong(form_id));
			filter.setLd_char(ld_char);
		}
		creditPaging.setPageSize(_pageSize);
    	model = new PagingListModel(activePage, _pageSize, filter, alias);
    	_totalSize = model.getTotalSize(filter,alias);
    	creditPaging.setTotalSize(_totalSize);
    	dataGrid.setModel((ListModel) model);
    	if (model.getSize()>0){
    		this.current =(CurrentCredit) model.getElementAt(0);
    		sendSelEvt();
    	}
    }
	
	private void sendSelEvt(){
    	try {
    		SelectEvent evt = new SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
        	Events.sendEvent(evt);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	public void onClick$btn_add(){
		clearForms();
		grid.setVisible(false);
		btn_save.setVisible(false);
		addgrd.setVisible(true);
		recall_graf.setVisible(false);
		fill_graf.setVisible(false);
		btn_add_exp.setVisible(true);
		btn_add_rate.setVisible(false);
	}

	public void onClick$btn_cancel(){
		grid.setVisible(true);
		addgrd.setVisible(false);
	}
	
	public void onClick$add_guarr(){
		createGuarrForm(null);
	}
	
	private void setBorrower(Ld_char ld_char,Ld_forms ld_form){
		branch.setSelecteditem(ld_char.getBranch());
		niki_id.setValue(ld_char.getNiki_id());
		sub_name.setValue(ld_form.getSub_name());
		sub_addr.setValue(ld_form.getSub_addr());
		emp_count.setValue(ld_form.getEmp_count()==null?null:ld_form.getEmp_count());
		is_sec.setSelecteditem(ld_form.getIs_sec()==null?"":ld_form.getIs_sec()+"");
		acc_state.setSelecteditem(ld_form.getAcc_state()==null?"":ld_form.getAcc_state()+"");
		is_ld.setSelecteditem(ld_form.getIs_ld()==null?"":ld_form.getIs_ld()+"");
		is_building.setSelecteditem(ld_form.getIs_building()==null?"":ld_form.getIs_building()+"");
	}
	
	private void setChar(Ld_char ld){
		eq_num.setValue(ld.getEq_num());
		eq_date.setValue(ld.getEq_date());
		currency.setSelecteditem(ld.getCurrency());
		amount.setValue(ld.getAmount());
		p_num.setValue(ld.getP_num());
		p_date.setValue(ld.getP_date());
		crc_num.setValue(ld.getCrc_num());
		crc_date.setValue(ld.getCrc_date());
		t_author.setValue(ld.getT_author());
		t_date.setValue(ld.getT_date());
		t_type.setSelecteditem(ld.getT_type()==null?null:ld.getT_type()+"");
		status.setSelecteditem(ld.getStatus()==null?"":ld.getStatus()+"");
		ld_num.setValue(ld.getLd_num());
		ld_date.setValue(ld.getLd_date());
		date_close.setValue(ld.getDate_close());
		ld_amount.setValue(new BigDecimal(ld.getLd_amount()));
		cl_num.setValue(ld.getCl_num());
		calc_id.setSelecteditem(ld.getCalc_id()==null?null:ld.getCalc_id()+"");
		is_inv.setSelecteditem(ld.getIs_inv());
		currency_inv.setSelecteditem(ld.getCurrency_inv());
		amount_inv.setValue(ld.getAmount_inv());
		target.setValue(ld.getTarget());
		shifr_id.setSelecteditem(ld.getShifr_id());
		sred_id.setSelecteditem(ld.getSred_id());
		term_type.setSelecteditem(ld.getTerm_type()==null?null:ld.getTerm_type()+"");
		al_num.setValue(ld.getAl_num());
		prod_name.setValue(ld.getProd_name());
		type_id.setSelecteditem(ld.getType_id()==null?"":ld.getType_id()+"");
		kred_id.setSelecteditem(ld.getKred_id());
		System.out.println("ID_CB = !!!"+current.getLd_char().getKred_id_cb()+"!!!");
		kred_id_cb.setSelecteditem(ld.getKred_id_cb());
		use_branch.setSelecteditem(ld.getUse_branch());
		klass_id.setSelecteditem(ld.getKlass_id());
		klassp_id.setSelecteditem(ld.getKlassp_id());
		is_tax.setSelecteditem(ld.getIs_tax()==null?null:ld.getIs_tax()+"");
		acceptance.setSelecteditem(ld.getAcceptance()==null?"":ld.getAcceptance()+"");
		cres.setSelecteditem(ld.getCres()==null?"":ld.getCres()+"");
		date_fee.setValue(ld.getDate_fee());
		date_end.setValue(ld.getDate_end());
	}
	
	private void setGuarr(Ld_guarr[] guarr){
		if(guarr!=null){
			for (int i = 0; i < guarr.length; i++) {
				createGuarrForm(guarr[i]);
			}
		}
	}
	
	private void setGr(Ld_gr[] grs){
		if(grs!=null){
			clearGrRows();
			for (int i = 0; i < grs.length; i++) {
				Radio radio = new Radio(Utils.getData("select t.name from ld_gr gr,v_ld_gr_type t where gr.oper_id=t.oper_id and gr.exp_id=t.exp_id and gr.oper_id = "+grs[i].getOper_id()+" and gr.exp_id = "+grs[i].getExp_id()+" and id = "+grs[i].getId(),""));
				radio.setAttribute("oper_id", grs[i].getOper_id());
				radio.setAttribute("gr", grs[i]);
				radio.addEventListener(Events.ON_CHECK, new EventListener() {
					
					@Override
					public void onEvent(Event evt) throws Exception {
						try {
							Radio radio = (Radio) evt.getTarget();
							Ld_gr gr = (Ld_gr) radio.getAttribute("gr");
							if(gr!=null&&gr.getLdgrafs()!=null){
								clearGrafRows();
								for (int j = 0; j < gr.getLdgrafs().length; j++) {
									Row row = new Row();
									Datebox date = new Datebox();
									date.setFormat("dd.MM.yyyy");
									date.setReadonly(true);
									date.setButtonVisible(false);
									Decimalbox dbox = new Decimalbox();
									dbox.setFormat("#0.00");
									dbox.setReadonly(true);
									Longbox lbox = new Longbox();
									lbox.setReadonly(true);
									date.setValue(gr.getLdgrafs()[j].getV_date());
									dbox.setValue(new BigDecimal(gr.getLdgrafs()[j].getSumma()/100));
									lbox.setValue(gr.getLdgrafs()[j].getNum());
									row.appendChild(date);
									row.appendChild(dbox);
									row.appendChild(lbox);
									if(gr.getOper_id()==1&&gr.getExp_id().equals("0")){
										period_column.setVisible(false);
										day_column.setVisible(false);
										period_cell.setVisible(false);
										day.setVisible(false);
									} else {
										period_column.setVisible(true);
										day_column.setVisible(true);
										period_cell.setVisible(true);
										day.setVisible(true);
									}
									ld_graff_amount.setValue(gr.getLdgrafs()[j].getSum()+"");
									graf_rows.appendChild(row);
								}
							}
							graf_method.setSelecteditem(gr.getGraf_method()+"");
							date_from.setValue(gr.getDate_from());
							date_to.setValue(gr.getDate_to());
							ld_amount_gr.setValue(ld_amount.getValue()+"");
							day.setValue(gr.getDay());
							num.setValue(gr.getNum());
							pay_period.setSelecteditem(gr.getPay_period()+"");
						} catch (Exception e) {
							e.printStackTrace();
							ISLogger.getLogger().error(CheckNull.getPstr(e));
						}
					}
				});
				ld_gr_radio.appendChild(radio);
			}
		}
	}
	
	private void setEventsForExps(){
		pay_method.addEventListener(Events.ON_CHANGE, new EventListener() {
			
			@Override
			public void onEvent(Event evt) throws Exception {
				RefCBox rbox = (RefCBox) evt.getTarget();
				Ld_exp exp = (Ld_exp) rbox.getAttribute("exp");
				if(exp!=null){
					exp.setPay_method(Long.parseLong(rbox.getValue()));
				}
			}
		});
		rate_method.addEventListener(Events.ON_CHANGE, new EventListener() {
			
			@Override
			public void onEvent(Event evt) throws Exception {
				RefCBox rbox = (RefCBox) evt.getTarget();
				Ld_exp exp = (Ld_exp) rbox.getAttribute("exp");
				if(exp!=null){
					exp.setRate_method(Long.parseLong(rbox.getValue()));
				}
			}
		});
		rate_type.addEventListener(Events.ON_CHANGE, new EventListener() {
			
			@Override
			public void onEvent(Event evt) throws Exception {
				RefCBox rbox = (RefCBox) evt.getTarget();
				Ld_exp exp = (Ld_exp) rbox.getAttribute("exp");
				if(exp!=null){
					exp.setRate_type(Long.parseLong(rbox.getValue()));
				}
			}
		});
	}
	
	private void setExp(Ld_exp[] exp){
		if(exp!=null){
			clearExpRows();
			for (int i = 0; i < exp.length; i++) {
				Radio radio = new Radio(Utils.getData("select name from ss_ld_exp_type where id="+exp[i].getExp_id(),""));
				radio.setAttribute("exp_id", exp[i].getExp_id());
				radio.setAttribute("exp", exp[i]);
				radio.addEventListener(Events.ON_CHECK, new EventListener() {
					
					@Override
					public void onEvent(Event evt) throws Exception {
						Radio radio = (Radio) evt.getTarget();
						Ld_exp exp = (Ld_exp) radio.getAttribute("exp");
						boolean edit = current.getLd_forms().getState()==1;
						selected_exp = exp;
						pay_method.setAttribute("exp", exp);
						pay_method.setSelecteditem(exp.getPay_method()+"");
						pay_method.setReadonly(!edit);
						pay_method.setButtonVisible(edit);
						rate_type.setAttribute("exp", exp);
						rate_type.setSelecteditem(exp.getRate_type()+"");
						rate_type.setReadonly(!edit);
						rate_type.setButtonVisible(edit);
						rate_method.setAttribute("exp", exp);
						rate_method.setSelecteditem(exp.getRate_method()+"");
						rate_method.setReadonly(!edit);
						rate_method.setButtonVisible(edit);
						if(exp.getLd_rates()!=null){
							clearRateRows();
							for (int j = 0; j < exp.getLd_rates().length; j++) {
								Ld_rate ld_rate = exp.getLd_rates()[j];
								ld_rate.setNew(false);
								addRate_row(ld_rate, edit);
							}
						}
						btn_add_rate.setVisible(true);
					}
				});
				Button btn = new Button();
				btn.setLabel("Удалить");
				btn.setAttribute("exp", exp[i]);
				btn.setAttribute("radio", radio);
				btn.addEventListener(Events.ON_CLICK, new EventListener() {
					
					@Override
					public void onEvent(Event evt) throws Exception {
						try {
							final Ld_exp exp = (Ld_exp) ((Button)evt.getTarget()).getAttribute("exp");
							Messagebox.show("Вы уверены что хотите выполнить удаление, действие необратимо?", "Удаление", Messagebox.YES | Messagebox.NO, Messagebox.EXCLAMATION, new EventListener() {
								
								@Override
								public void onEvent(Event evt) throws Exception {
									Connection c = null;
									try {
										if(evt.getName().equals("onYes")){
											c = ConnectionPool.getConnection(alias);
											CreditService.deleteLdExp(exp,c);
											clearExpRows();
											clearRateRows();
											current.setLd_exp(CreditService.getLdExp(current.getLd_char().getId(), current.getLd_char().getBranch(), c));
											setExp(current.getLd_exp());
											c.commit();
										}
									} catch (Exception e) {
										Utils.rollback(c);
										if(e.getMessage().contains("ORA-02292")){
											alert("Удаление невозможно");
											return;
										}
										ISLogger.getLogger().error(CheckNull.getPstr(e));
										e.printStackTrace();
									} finally {
										ConnectionPool.close(c);
									}
								}
							});
						} catch (Exception e) {
							e.printStackTrace();
							ISLogger.getLogger().error(CheckNull.getPstr(e));
						}
					}
				});
				ld_exp_radio.appendChild(radio);
				ld_exp_radio_btn.appendChild(btn);
			}
		}
	}
	
	private void addRate_row(Ld_rate ld_rate,boolean edit) throws Exception{
		Row row = new Row();
		RefCBox rbox = new RefCBox();
		rbox.setAttribute("rate", ld_rate);
		rbox.setReadonly(!edit);
		rbox.setButtonVisible(edit);
		rbox.setModel(new ListModelList(LdHisRateService.getRate_id("")));
		rbox.addEventListener("onInitRenderLater", new EventListener() {
			
			@Override
			public void onEvent(Event evt) throws Exception {
				RefCBox rbox = (RefCBox) evt.getTarget();
				Ld_rate rate = (Ld_rate) rbox.getAttribute("rate");
				rbox.setSelecteditem(rate.getRate_id()+"");
			}
		});
		rbox.addEventListener(Events.ON_CHANGE, new EventListener() {
			
			@Override
			public void onEvent(Event evt) throws Exception {
				RefCBox rbox = (RefCBox) evt.getTarget();
				Ld_rate rate = (Ld_rate) rbox.getAttribute("rate");
				if(rate!=null){
					rate.setRate_id(Long.parseLong(rbox.getValue()));
				}
			}
		});
		Decimalbox rate = new Decimalbox();
		rate.setMaxlength(10);
		rate.setFormat("#0.#######");
		rate.setReadonly(!edit);
		rate.setAttribute("rate", ld_rate);
		rate.setValue(ld_rate.getRate()==null||ld_rate.getRate()==0?null:new BigDecimal(ld_rate.getRate()));
		rate.addEventListener(Events.ON_CHANGE, new EventListener() {
			
			@Override
			public void onEvent(Event evt) throws Exception {
				Decimalbox dbox = (Decimalbox) evt.getTarget();
				Ld_rate rate = (Ld_rate) dbox.getAttribute("rate");
				if(rate!=null){
					rate.setRate(dbox.getValue().doubleValue());
				}
			}
		});
		Decimalbox coeff = new Decimalbox();
		coeff.setMaxlength(5);
		coeff.setFormat("#0.##");
		coeff.setReadonly(!edit);
		coeff.setAttribute("rate", ld_rate);
		coeff.addEventListener(Events.ON_CHANGE, new EventListener() {
			
			@Override
			public void onEvent(Event evt) throws Exception {
				Decimalbox dbox = (Decimalbox) evt.getTarget();
				Ld_rate rate = (Ld_rate) dbox.getAttribute("rate");
				if(rate!=null){
					rate.setCoeff(dbox.getValue().doubleValue());
				}
			}
		});
		coeff.setValue(ld_rate.getCoeff()==null||ld_rate.getCoeff()==0?null:new BigDecimal(ld_rate.getCoeff()));
		Datebox date_from = new Datebox();
		date_from.setFormat("dd.MM.yyyy");
		date_from.setButtonVisible(edit);
		date_from.setReadonly(!edit);
		date_from.setValue(ld_rate.getDate_open());
		date_from.setAttribute("rate", ld_rate);
		date_from.addEventListener(Events.ON_CHANGE, new EventListener() {
			
			@Override
			public void onEvent(Event evt) throws Exception {
				Datebox date = (Datebox) evt.getTarget();
				Ld_rate rate = (Ld_rate) date.getAttribute("rate");
				if(rate!=null){
					rate.setDate_open(date.getValue());
				}
			}
		});
		Datebox date_close = new Datebox();
		date_close.setFormat("dd.MM.yyyy");
		date_close.setReadonly(!edit);
		date_close.setButtonVisible(edit);
		date_close.setAttribute("rate", ld_rate);
		date_close.addEventListener(Events.ON_CHANGE, new EventListener() {
			
			@Override
			public void onEvent(Event evt) throws Exception {
				Datebox date = (Datebox) evt.getTarget();
				Ld_rate rate = (Ld_rate) date.getAttribute("rate");
				if(rate!=null){
					rate.setDate_close(date.getValue());
				}
			}
		});
		date_close.setValue(ld_rate.getDate_close());
		Button save_rate = new Button();
		save_rate.setImage("images/save.png");
		save_rate.setAttribute("rate", ld_rate);
		save_rate.addEventListener(Events.ON_CLICK, new EventListener() {
			
			@Override
			public void onEvent(Event evt) throws Exception {
				Ld_rate[] rates = getValuesRate();
				Connection c = null;
				try {
					c = ConnectionPool.getConnection(alias);
					for (int i = 0; i < rates.length; i++) {
						if(selected_exp==null)System.out.println("NULL");
						CreditService.saveLdRate(selected_exp, rates[i], c);
					}
					clearRateRows();
					current.setLd_exp(CreditService.getLdExp(current.getLd_char().getId(), mfo, c));
					setExp(current.getLd_exp());
					selected_exp = null;
					c.commit();
				} catch (Exception e) {
					e.printStackTrace();
					Utils.rollback(c);
				} finally {
					ConnectionPool.close(c);
				}
			}
		});
		Button del_rate = new Button();
		del_rate.setImage("images/delete.png");
		del_rate.addEventListener(Events.ON_CLICK, new EventListener() {
			
			@Override
			public void onEvent(Event evt) throws Exception {
				Messagebox.show("Вы уверены что хотите удалить ставку?", "Предупреждение", Messagebox.YES|Messagebox.NO, Messagebox.EXCLAMATION, new EventListener() {
					
					@Override
					public void onEvent(Event evt) throws Exception {
						if(evt.getName().equals("onYes")){
							Connection c = null;
							try {
								c = ConnectionPool.getConnection(alias);
								CreditService.deleteLdRate(selected_exp, c);
								alert("Ставка удалена");
								c.commit();
							} catch (Exception e) {
								e.printStackTrace();
								Utils.rollback(c);
								ISLogger.getLogger().error(CheckNull.getPstr(e));
							} finally {
								ConnectionPool.close(c);
							}
						}
					}
				});
			}
		});
		if(selected_exp==null&&!ld_rate.isNew()){
			save_rate.setVisible(false);
			btn_save_rate.setVisible(false);
			if(ld_rate.getAct().equals("A")){
				btn_del_rate.setVisible(true);	
			} else {
				btn_del_rate.setVisible(false);
				del_rate.setVisible(false);
			}
		} else if (selected_exp!=null&&ld_rate.isNew()){
			del_rate.setVisible(false);
			btn_del_rate.setVisible(false);
			btn_save_rate.setVisible(true);
		} else if (selected_exp!=null&&!ld_rate.isNew()){
			btn_save_rate.setVisible(true);
			btn_save_rate.setWidth("4%");
			btn_del_rate.setWidth("4%");
			if(ld_rate.getAct().equals("A")){
				btn_del_rate.setVisible(true);	
			} else {
				btn_del_rate.setVisible(false);
				del_rate.setVisible(false);
			}
		}
		row.appendChild(rbox);
		row.appendChild(rate);
		row.appendChild(coeff);
		row.appendChild(date_from);
		row.appendChild(date_close);
		row.appendChild(save_rate);
		row.appendChild(del_rate);
		rate_row.appendChild(row);
	}
	
	private void setAcc(V_ld_account[] acc){
		if(acc!=null){
			clearAccRows();
			for (int i = 0; i < acc.length; i++) {
				Row row = new Row();
				Label ord = new Label(acc[i].getOrd()+"");
				row.appendChild(ord);
				Label name = new Label(acc[i].getName());
				row.appendChild(name);
				Label bal = new Label(acc[i].getBal());
				row.appendChild(bal);
				Label account = new Label(acc[i].getAccount());
				row.appendChild(account);
				Label nstate = new Label(acc[i].getNstate());
				row.appendChild(nstate);
				Label saldo1 = new Label(acc[i].getSaldo1());
				row.appendChild(saldo1);
				Label saldo = new Label(acc[i].getSaldo());
				row.appendChild(saldo);
				Label acc_type_id = new Label(acc[i].getAcc_type_id());
				row.appendChild(acc_type_id);
				acc_rows.appendChild(row);
			}
		}
	}
	
	private void createGuarrForm(Ld_guarr guarr){
		GridForGuars grid = new GridForGuars(guarr);
		guars.appendChild(grid.getBox());
	}
	
	@SuppressWarnings("unchecked")
	private void clearGuarrForm(){
		List<Component> list = guars.getChildren();
		int size = list.size()-1;
		for (int i = size; i >= 0; i--) {
			guars.removeChild(list.get(i));
		}
	}
	
	private void clearForms(){
		clearGuarrForm();
		clearGrafRows();
		clearGrRows();
		clearExpRows();
		clearRateRows();
		clearAccRows();
		Utils.clearForm(addgrd);
	}

	@SuppressWarnings("unchecked")
	private void clearGrafRows(){
		List<Component> list = graf_rows.getChildren();
		int size = list.size()-1;
		for (int i = size; i >= 0; i--) {
			graf_rows.removeChild(list.get(i));
		}
	}
	
	@SuppressWarnings("unchecked")
	private void clearGrRows(){
		List<Component> list = ld_gr_radio.getChildren();
		int size = list.size()-1;
		for (int i = size; i >= 0; i--) {
			if(!(list.get(i) instanceof Grid)){
				ld_gr_radio.removeChild(list.get(i));
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private void clearExpRows(){
		List<Component> list = ld_exp_radio.getChildren();
		int size = list.size()-1;
		for (int i = size; i >= 0; i--) {
			if(!(list.get(i) instanceof Grid)){
				ld_exp_radio.removeChild(list.get(i));
				System.out.println("del i = "+i);
			}
		}
		List<Component> list_btn = ld_exp_radio_btn.getChildren();
		int size_btn = list_btn.size()-1;
		for (int i = size_btn; i >= 0; i--) {
			if(!(list_btn.get(i) instanceof Grid)){
				ld_exp_radio_btn.removeChild(list_btn.get(i));
				System.out.println("del ii = "+i);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private void clearRateRows(){
		List<Component> list = rate_row.getChildren();
		int size = list.size()-1;
		for (int i = size; i >= 0; i--) {
			rate_row.removeChild(list.get(i));
		}
	}
	
	@SuppressWarnings("unchecked")
	private void clearAccRows(){
		List<Component> list = acc_rows.getChildren();
		int size = list.size()-1;
		for (int i = size; i >= 0; i--) {
			acc_rows.removeChild(list.get(i));
		}
	}
	
	public void onClick$btn_confirm(){
		try {
			Messagebox.show("Утвердить анкету?", "Предупреждение", Messagebox.YES|Messagebox.NO, Messagebox.EXCLAMATION, new EventListener() {
				
				@Override
				public void onEvent(Event evt) throws Exception {
					if(evt.getName().equals("onYes")){
						try {
							CreditService.confirmCredit(current, alias,"2");
							refreshModel(_startPageNumber);
						} catch (Exception e) {
							e.printStackTrace();
							ISLogger.getLogger().error(CheckNull.getPstr(e));
							if(e.getMessage()!=null&&e.getMessage().contains("ORA-20000")){
								alert(e.getMessage());
							}
						}
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
	}
	
	public void onClick$btn_close(){
		try {
			Messagebox.show("Закрыть анкету?", "Предупреждение", Messagebox.YES|Messagebox.NO, Messagebox.EXCLAMATION, new EventListener() {
				
				@Override
				public void onEvent(Event evt) throws Exception {
					if(evt.getName().equals("onYes")){
						try {
							CreditService.confirmCredit(current, alias,"7");
							refreshModel(_startPageNumber);
						} catch (Exception e) {
							e.printStackTrace();
							ISLogger.getLogger().error(CheckNull.getPstr(e));
							if(e.getMessage()!=null&&e.getMessage().contains("ORA-20000")){
								alert(e.getMessage());
							}
						}
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
	}
	
	public void onClick$btn_save(){
		Ld_char ld_char = null;
		if(action.equals("double")){
			ld_char = current.getLd_char();
		}
		if(ld_char==null){
			ld_char = new Ld_char();
		}
		System.out.println("id = "+ld_char.getId());
		ld_char.setAcceptance(acceptance.getValue()==null||acceptance.getValue().equals("")?null:Integer.parseInt(acceptance.getValue()));
		ld_char.setAl_num(al_num.getValue());
		ld_char.setAmount(amount.getValue());
		ld_char.setAmount_inv(amount_inv.getValue());
		ld_char.setBranch(branch.getValue());
		ld_char.setCalc_id(calc_id.getValue()==null||calc_id.getValue().equals("")?null:Long.parseLong(calc_id.getValue()));
		ld_char.setCl_num(cl_num.getValue());
		ld_char.setCrc_date(crc_date.getValue());
		ld_char.setCrc_num(crc_num.getValue());
		ld_char.setCres(cres.getValue()==null||cres.getValue().equals("")?null:Integer.parseInt(cres.getValue()));
		ld_char.setCurrency(currency.getValue());
		ld_char.setCurrency_inv(currency_inv.getValue());
		ld_char.setDate_close(date_close.getValue());
		ld_char.setDate_end(date_end.getValue());
		ld_char.setDate_fee(date_fee.getValue());
		ld_char.setEq_date(eq_date.getValue());
		ld_char.setEq_num(eq_num.getValue());
//		ld_char.setFaza_abr(faza_abr.getValue());
//		ld_char.setForeign_means(foreign_means.)
		ld_char.setIs_inv(is_inv.getValue());
		ld_char.setIs_tax(is_tax.getValue()==null||is_tax.getValue().equals("")?null:Integer.parseInt(is_tax.getValue()));
		ld_char.setKlass_id(klass_id.getValue());
		ld_char.setKlassp_id(klassp_id.getValue());
//		ld_char.setKod_fin(kod_fin.getVa)
//		ld_char.setKod_int(kod_int.)
		ld_char.setKred_id(kred_id.getValue());
		ld_char.setKred_id_cb(kred_id_cb.getValue());
		ld_char.setLd_amount(ld_amount.doubleValue());
		ld_char.setLd_date(ld_date.getValue());
		ld_char.setLd_num(ld_num.getValue());
//		ld_char.setLd_period(ld_period.)
//		ld_char.setLd_type(ld_type.)
//		ld_char.setLdays(ldays.)
		ld_char.setNiki_id(niki_id.getValue());
		ld_char.setP_date(p_date.getValue());
		ld_char.setP_num(p_num.getValue());
//		ld_char.setPk(pk.)
		ld_char.setProd_name(prod_name.getValue());
		ld_char.setShifr_id(shifr_id.getValue());
		ld_char.setSred_id(sred_id.getValue());
		ld_char.setStatus(status.getValue()==null||status.getValue().equals("")?null:Long.parseLong(status.getValue()));
//		ld_char.setStatus_prc(status_prc)
		ld_char.setT_author(t_author.getValue());
		ld_char.setT_date(t_date.getValue());
		ld_char.setT_type(t_type.getValue()==null||t_type.getValue().equals("")?null:Integer.parseInt(t_type.getValue()));
		ld_char.setTarget(target.getValue());
//		ld_char.setTax_rate(tax_rate)
		ld_char.setTerm_type(term_type.getValue()==null||term_type.getValue().equals("")?null:Long.parseLong(term_type.getValue()));
		ld_char.setType_id(type_id.getValue()==null||type_id.getValue().equals("")?null:Long.parseLong(type_id.getValue()));
		ld_char.setUse_branch(use_branch.getValue());
		current.setLd_char(ld_char);
		Ld_guarr[] array_guarrs = new Ld_guarr[guars.getChildren().size()];
		List<?> guarrs = guars.getChildren();
		for (int i = 0; i < guarrs.size(); i++) {
			Ld_guarr guar = new Ld_guarr();
			Ld_guar_cadastre cadastre = null;
			Ld_guar_blocks blocks = null;
			Ld_guar_car car = null;
			Ld_guar_equipment equipment = null;
			if(guarrs.get(i) instanceof Groupbox){
				Groupbox groupbox = (Groupbox) guarrs.get(i);
				if(groupbox.getAttribute("PK")!=null){
					guar.setPk(Long.parseLong(groupbox.getAttribute("PK")+""));
				}
				if(groupbox.getAttribute("guarr_id")!=null){
					guar.setGuar_id(groupbox.getAttribute("guarr_id")+"");
				}
				List<?> childs = groupbox.getChildren();
				for (int j = 0; j < childs.size(); j++) {
					if(childs.get(j) instanceof Grid){
						Grid grid = (Grid) childs.get(j);
						Boolean isGrid = (Boolean) grid.getAttribute("isGrid");
						if(isGrid!=null&&isGrid){
							List<?> grid_childs = grid.getChildren();
							for (int k = 0; k < grid_childs.size(); k++) {
								if(grid_childs.get(k) instanceof Rows){
									Rows rows = (Rows) grid_childs.get(k);
									for (int l = 0; l < rows.getChildren().size(); l++) {
										if(rows.getChildren().get(l) instanceof Row){
											Row row = (Row) rows.getChildren().get(l);
											for (int m = 0; m < row.getChildren().size(); m++) {
												if(row.getChildren().get(m) instanceof Component){
													Component component = (Component) row.getChildren().get(m);
													if(component.getAttribute("table")!=null){
														String table_name = (String) component.getAttribute("table");
														if(table_name.equalsIgnoreCase("LD_GUARR")){
															if(component.getAttribute("param")!=null){
																String param = (String) component.getAttribute("param");
																if(component instanceof RefCBox){
																	RefCBox rbox = (RefCBox) component;
																	if(param.equalsIgnoreCase("ACOMP_CURR")){
																		guar.setAcomp_curr(rbox.getValue());
																	} else if(param.equalsIgnoreCase("ACOMP_NAME")){
																		guar.setAcomp_name(rbox.getValue());
																	} else if(param.equalsIgnoreCase("CADASTR_ORG_DISTR")){
																		guar.setCadastr_org_distr(rbox.getValue());
																	} else if(param.equalsIgnoreCase("CADASTR_ORG_REGION")){
																		guar.setCadastr_org_region(rbox.getValue());
																	} else if(param.equalsIgnoreCase("CODE_SUBJECT")){
																		guar.setCode_subject(rbox.getValue());
																	} else if(param.equalsIgnoreCase("CURRENCY")){
																		guar.setCurrency(rbox.getValue());
																	} else if(param.equalsIgnoreCase("DISTR_ID")){
																		guar.setDistr_id(rbox.getValue());
																	} else if(param.equalsIgnoreCase("INSC_NAME")){
																		guar.setInsc_name(rbox.getValue());
																	} else if(param.equalsIgnoreCase("KLASS_O")){
																		guar.setKlass_o(rbox.getValue());
																	} else if(param.equalsIgnoreCase("NIKI_GR_BRANCH")){
																		guar.setNiki_gr_branch(rbox.getValue());
																	} else if(param.equalsIgnoreCase("NIKI_GR_CODE_TYPE")){
																		guar.setNiki_gr_code_type(rbox.getValue());
																	} else if(param.equalsIgnoreCase("NIKI_OWNER")){
																		guar.setNiki_owner(rbox.getValue());
																	} else if(param.equalsIgnoreCase("NIKI_RES1")){
																		guar.setNiki_res1(rbox.getValue());
																	} else if(param.equalsIgnoreCase("NIKI_RES2")){
																		guar.setNiki_res2(rbox.getValue());
																	} else if(param.equalsIgnoreCase("NIKI_SOOGUN")){
																		guar.setNiki_soogun(rbox.getValue());
																	} else if(param.equalsIgnoreCase("NOTARIAL_OFFICE_NUM")){
																		guar.setNotarial_office_num(rbox.getValue());
																	} else if(param.equalsIgnoreCase("REGION_ID")){
																		guar.setRegion_id(rbox.getValue());
																	} else if(param.equalsIgnoreCase("SIGN_DEPO")){
																		guar.setSign_depo(rbox.getValue());
																	}
																} else if(component instanceof Textbox){
																	Textbox txt = (Textbox) component;
																	if(param.equalsIgnoreCase("ACCOUNT")){
																		guar.setAccount(txt.getValue());
																	} else if(param.equalsIgnoreCase("DEPOSITARY")){
																		guar.setDepositary(txt.getValue());
																	} else if(param.equalsIgnoreCase("DEPOSITARY_ACCOUNT")){
																		guar.setDepositary_account(txt.getValue());
																	} else if(param.equalsIgnoreCase("DOC_NUM")){
																		guar.setDoc_num(txt.getValue());
																	} else if(param.equalsIgnoreCase("ECONOMICAL_ZONE")){
																		guar.setEconomical_zone(txt.getValue());
																	} else if(param.equalsIgnoreCase("EVAL_REPORT_NUM")){
																		guar.setEval_report_num(txt.getValue());
																	} else if(param.equalsIgnoreCase("HOME")){
																		guar.setHome(txt.getValue());
																	} else if(param.equalsIgnoreCase("HOME_NUM")){
																		guar.setHome_num(txt.getValue());
																	} else if(param.equalsIgnoreCase("ID_CLIENT")){
																		guar.setId_client(txt.getValue());
																	} else if(param.equalsIgnoreCase("INN")){
																		guar.setInn(txt.getValue());
																	} else if(param.equalsIgnoreCase("INN_REESTR")){
																		guar.setInn_reestr(txt.getValue());
																	} else if(param.equalsIgnoreCase("INSC_INN")){
																		guar.setInsc_inn(txt.getValue());
																	} else if(param.equalsIgnoreCase("INSC_NUM")){
																		guar.setInsc_num(txt.getValue());
																	} else if(param.equalsIgnoreCase("LIS_NUM")){
																		guar.setLis_num(txt.getValue());
																	} else if(param.equalsIgnoreCase("MASSIV")){
																		guar.setMassiv(txt.getValue());
																	} else if(param.equalsIgnoreCase("MFO")){
																		guar.setMfo(txt.getValue());
																	} else if(param.equalsIgnoreCase("NAME")){
																		guar.setName(txt.getValue());
																	} else if(param.equalsIgnoreCase("NAME2")){
																		guar.setName2(txt.getValue());
																	} else if(param.equalsIgnoreCase("NIKI_INN")){
																		guar.setNiki_inn(txt.getValue());
																	} else if(param.equalsIgnoreCase("NOTARIAL_DOC_NUMB")){
																		guar.setNotarial_doc_numb(txt.getValue());
																	} else if(param.equalsIgnoreCase("POLIS_NUM")){
																		guar.setPolis_num(txt.getValue());
																	} else if(param.equalsIgnoreCase("SER_EVAL_COMPANY")){
																		guar.setSer_eval_company(txt.getValue());
																	} else if(param.equalsIgnoreCase("SERTIFICATE_NUM")){
																		guar.setSertificate_num(txt.getValue());
																	} else if(param.equalsIgnoreCase("SERTIFICATE_SER")){
																		guar.setSertificate_ser(txt.getValue());
																	} else if(param.equalsIgnoreCase("STOCK_NAME")){
																		guar.setStock_name(txt.getValue());
																	} else if(param.equalsIgnoreCase("STREET")){
																		guar.setStreet(txt.getValue());
																	}
																} else if(component instanceof Datebox){
																	Datebox date = (Datebox) component;
																	if(param.equalsIgnoreCase("ACOMP_DATE")){
																		guar.setAcomp_date(date.getValue());
																	} else if(param.equalsIgnoreCase("DATE_OPERATION")){
																		guar.setDate_operation(date.getValue());
																	} else if(param.equalsIgnoreCase("DOC_DATE")){
																		guar.setDoc_date(date.getValue());
																	} else if(param.equalsIgnoreCase("END_DATE")){
																		guar.setEnd_date(date.getValue());
																	} else if(param.equalsIgnoreCase("INSC_DATE")){
																		guar.setInsc_date(date.getValue());
																	} else if(param.equalsIgnoreCase("INSC_DATE_CL")){
																		guar.setInsc_date_cl(date.getValue());
																	} else if(param.equalsIgnoreCase("LIS_DATE")){
																		guar.setLis_date(date.getValue());
																	} else if(param.equalsIgnoreCase("POLIS_DATE")){
																		guar.setPolis_date(date.getValue());
																	} else if(param.equalsIgnoreCase("START_DATE")){
																		guar.setStart_date(date.getValue());
																	}
																} else if(component instanceof Decimalbox){
																	Decimalbox dbox = (Decimalbox) component;
																	if(param.equalsIgnoreCase("ACOMP_SUMMA")){
																		if(dbox.getValue()!=null&&!dbox.getValue().equals("")){
																			guar.setAcomp_summa(dbox.getValue().doubleValue());
																		}
																	} else if(param.equalsIgnoreCase("AMOUNT")){
																		if(dbox.getValue()!=null&&!dbox.getValue().equals("")){
																			guar.setAmount(dbox.getValue().doubleValue());
																		}
																	} else if(param.equalsIgnoreCase("MASSA")){
																		if(dbox.getValue()!=null&&!dbox.getValue().equals("")){
																			guar.setMassa(dbox.getValue().doubleValue());
																		}
																	} else if(param.equalsIgnoreCase("SERTIFICATE_RATE")){
																		if(dbox.getValue()!=null&&!dbox.getValue().equals("")){
																			guar.setSertificate_rate(dbox.getValue().doubleValue());
																		}
																	} else if(param.equalsIgnoreCase("SOWING_AREA")){
																		if(dbox.getValue()!=null&&!dbox.getValue().equals("")){
																			guar.setSowing_area(dbox.getValue().intValue());
																		}
																	} else if(param.equalsIgnoreCase("STOCK_COUNT")){
																		if(dbox.getValue()!=null&&!dbox.getValue().equals("")){
																			guar.setStock_count(dbox.getValue().longValue());
																		}
																	} else if(param.equalsIgnoreCase("STOCK_DISKONT")){
																		if(dbox.getValue()!=null&&!dbox.getValue().equals("")){
																			guar.setStock_diskont(dbox.getValue().longValue());
																		}
																	} else if(param.equalsIgnoreCase("STOCK_NOMINAL_VALUE")){
																		if(dbox.getValue()!=null&&!dbox.getValue().equals("")){
																			guar.setStock_nominal_value(dbox.getValue().longValue());
																		}
																	}
																}
															}
														} else if(table_name.equalsIgnoreCase("LD_GUAR_CADASTRE")){
															if(cadastre==null){
																cadastre = new Ld_guar_cadastre();
															}
															if(component.getAttribute("param")!=null){
																String param = (String) component.getAttribute("param");
																if(component instanceof Datebox){
																	Datebox date = (Datebox) component;
																	if(param.equalsIgnoreCase("CERTIFICATE_DATE")){
																		cadastre.setCertificate_date(date.getValue());
																	}
																} else if(component instanceof RefCBox){
																	RefCBox rbox = (RefCBox) component;
																	if(param.equalsIgnoreCase("OVNERSHIP")){
																		cadastre.setOvnership(rbox.getValue());
																	}
																} else if(component instanceof Textbox){
																	Textbox txt = (Textbox) component;
																	if(param.equalsIgnoreCase("CADASTRE_NUM")){
																		cadastre.setCadastre_num(txt.getValue());
																	} else if(param.equalsIgnoreCase("CERTIFICATE_NUM")){
																		cadastre.setCertificate_num(txt.getValue());
																	} else if(param.equalsIgnoreCase("REYESTR_NUM")){
																		cadastre.setReyestr_num(txt.getValue());
																	}
																} else if(component instanceof Decimalbox){
																	Decimalbox dbox = (Decimalbox) component;
																	if(param.equalsIgnoreCase("CADASTRE_TYPE")){
																		if(dbox.getValue()!=null&&!dbox.getValue().equals("")){
																			cadastre.setCadastre_type(dbox.getValue().intValue());
																		} 
																	} else if(param.equalsIgnoreCase("SQUARE")){
																		if(dbox.getValue()!=null&&!dbox.getValue().equals("")){
																			cadastre.setSquare(dbox.getValue().doubleValue());
																		} 
																	}
																}
																
															}
														} else if(table_name.equalsIgnoreCase("LD_GUAR_BLOCKS")){
															if(blocks==null){
																blocks = new Ld_guar_blocks();
															}
															if(component.getAttribute("param")!=null){
																String param = (String) component.getAttribute("param");
																if(component instanceof Textbox){
																	Textbox txt = (Textbox) component;
																	if(param.equalsIgnoreCase("BLOCK_NAME")){
																		blocks.setBlock_name(txt.getValue());
																	} else if(param.equalsIgnoreCase("DESCRIPTION")){
																		blocks.setDescription(txt.getValue());
																	} 
																} else if(component instanceof Decimalbox){
																	Decimalbox dbox = (Decimalbox) component;
																	if(param.equalsIgnoreCase("COST")){
																		if(dbox.getValue()!=null&&!dbox.getValue().equals("")){
																			blocks.setCost(dbox.getValue().longValue());
																		}
																	} else if(param.equalsIgnoreCase("ID_NN")){
																		if(dbox.getValue()!=null&&!dbox.getValue().equals("")){
																			blocks.setId_nn(dbox.getValue().longValue());
																		}
																	} else if(param.equalsIgnoreCase("SQUARE")){
																		if(dbox.getValue()!=null&&!dbox.getValue().equals("")){
																			blocks.setSquare(dbox.getValue().doubleValue());
																		}
																	}
																}
															}
														} else if(table_name.equalsIgnoreCase("LD_GUAR_EQUIPMENT")){
															if(equipment==null){
																equipment = new Ld_guar_equipment();
															}
															if(component.getAttribute("param")!=null){
																String param = (String) component.getAttribute("param");
																if(component instanceof Textbox){
																	Textbox txt = (Textbox) component;
																	if(param.equalsIgnoreCase("DOC_NUM")){
																		equipment.setDoc_num(txt.getValue());
																	} else if(param.equalsIgnoreCase("INVENT_NUM")){
																		equipment.setInvent_num(txt.getValue());
																	} else if(param.equalsIgnoreCase("MANUFACTURER")){
																		equipment.setManufacturer(txt.getValue());
																	} else if(param.equalsIgnoreCase("NAME")){
																		equipment.setName(txt.getValue());
																	} else if(param.equalsIgnoreCase("REASON")){
																		equipment.setReason(txt.getValue());
																	} 
																} else if(component instanceof Datebox){
																	Datebox date = (Datebox) component;
																	if(param.equalsIgnoreCase("DATE_OPERATION")){
																		equipment.setDate_operation(date.getValue());
																	} else if(param.equalsIgnoreCase("DOC_DATE")){
																		equipment.setDoc_date(date.getValue());
																	}
																} else if(component instanceof RefCBox){
																	RefCBox rbox = (RefCBox) component;
																	if(param.equalsIgnoreCase("COUNTRY")){
																		equipment.setCountry(rbox.getValue());
																	} else if(param.equalsIgnoreCase("DATE_MADE")){
																		equipment.setDate_made(rbox.getValue());
																	} else if(param.equalsIgnoreCase("EQ_TYPE")){
																		equipment.setEq_type(rbox.getValue());
																	}
																} else if(component instanceof Decimalbox){
																	Decimalbox dbox = (Decimalbox) component;
																	if(param.equalsIgnoreCase("ID_NN")){
																		if(dbox.getValue()!=null&&!dbox.getValue().equals("")){
																			equipment.setId_nn(dbox.getValue().longValue());
																		}
																	} else if(param.equalsIgnoreCase("PRICE_MARKET")){
																		if(dbox.getValue()!=null&&!dbox.getValue().equals("")){
																			equipment.setPrice_market(dbox.getValue().doubleValue());
																		}
																	} else if(param.equalsIgnoreCase("PRICE_ZALOG")){
																		if(dbox.getValue()!=null&&!dbox.getValue().equals("")){
																			equipment.setPrice_zalog(dbox.getValue().doubleValue());
																		}
																	}
																}
															}
														} else if(table_name.equalsIgnoreCase("LD_GUAR_CAR")){
															if(car==null){
																car = new Ld_guar_car();
															}
															if(component.getAttribute("param")!=null){
																String param = (String) component.getAttribute("param");
																if(component instanceof Textbox){
																	Textbox txt = (Textbox) component;
																	if(param.equalsIgnoreCase("ENGINE_NUM")){
																		car.setEngine_num(txt.getValue());
																	} else if(param.equalsIgnoreCase("BODY_NUM")){
																		car.setBody_num(txt.getValue());
																	} else if(param.equalsIgnoreCase("CHASSIS_NUMBER")){
																		car.setChassis_number(txt.getValue());
																	} else if(param.equalsIgnoreCase("DOC_SER_NUM")){
																		car.setDoc_ser_num(txt.getValue());
																	} else if(param.equalsIgnoreCase("STATE_NUMBER")){
																		car.setState_number(txt.getValue());
																	}
																} else if(component instanceof Datebox){
																	Datebox date = (Datebox) component;
																	if(param.equalsIgnoreCase("DOC_DATE")){
																		car.setDoc_date(date.getValue());
																	}
																} else if(component instanceof RefCBox){
																	RefCBox rbox = (RefCBox) component;
																	if(param.equalsIgnoreCase("CAR_MARKA")){
																		car.setCar_marka(rbox.getValue());
																	} else if(param.equalsIgnoreCase("CAR_MODEL")){
																		car.setCar_model(rbox.getValue());
																	} else if(param.equalsIgnoreCase("CAR_TYPE")){
																		car.setCar_type(rbox.getValue());
																	} else if(param.equalsIgnoreCase("CODE_COUNTRY")){
																		car.setCode_country(rbox.getValue());
																	} else if(param.equalsIgnoreCase("COLOR")){
																		car.setColor(rbox.getValue());
																	} else if(param.equalsIgnoreCase("DATE_MADE")){
																		car.setDate_made(rbox.getValue());
																	}
																} else if(component instanceof Decimalbox){
																	Decimalbox dbox = (Decimalbox) component;
																	if(param.equalsIgnoreCase("MILEAGE")){
																		if(dbox.getValue()!=null&&!dbox.getValue().equals("")){
																			car.setMileage(dbox.getValue().longValue());
																		}
																	} else if(param.equalsIgnoreCase("POSITION")){
																		if(dbox.getValue()!=null&&!dbox.getValue().equals("")){
																			car.setPosition(dbox.getValue().intValue());
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}	
						}
					}
				}
			}
			if(car!=null){
				guar.setLd_guar_car(car);
			} if(equipment!=null){
				guar.setLd_guar_equipment(equipment);
			} if(cadastre!=null){
				guar.setLd_guar_cadastre(cadastre);
			} if(blocks!=null){
				guar.setLd_guar_blocks(blocks);
			}
			array_guarrs[i] = guar;
		}
		current.setLd_guarr(array_guarrs);
		Res res = new Res();
		CreditService.saveCredit(current, res, alias);
		if(res.getCode()==1){
			alert(res.getName());
		} else {
			onClick$btn_cancel();
			refreshModel(_startPageNumber);
		}
	}
	
	public void onClick$btn_add_exp(){
		try {
			clearExpRows();
			clearRateRows();
			pay_method.setValue(null);
			rate_method.setValue(null);
			rate_type.setValue(null);
			Ld_exp exp = new Ld_exp();
			exp.setBranch(mfo);
			RefCBox exp_id = new RefCBox();
			exp_id.setAttribute("exp", exp);
			exp_id.setModel(new ListModelList(CreditService.getLdExpType()));
			exp_id.addEventListener(Events.ON_CHANGE, new EventListener() {
				
				@Override
				public void onEvent(Event evt) throws Exception {
					RefCBox rbox = (RefCBox) evt.getTarget();
					Ld_exp exp = (Ld_exp) rbox.getAttribute("exp");
					pay_method.setReadonly(false);
					pay_method.setButtonVisible(true);
					rate_method.setReadonly(false);
					rate_method.setButtonVisible(true);
					rate_type.setReadonly(false);
					rate_type.setButtonVisible(true);
					exp.setExp_id(Long.parseLong(rbox.getValue()));
				}
			});
			Button btn = new Button();
			btn.setLabel("Сохранить");
			btn.setAttribute("exp_id", exp_id);
			btn.setAttribute("exp", exp);
			btn.addEventListener(Events.ON_CLICK, new EventListener() {
				
				@Override
				public void onEvent(Event evt) throws Exception {
					Connection c = null;
					try {
						Button btn = (Button) evt.getTarget();
						Ld_exp exp = (Ld_exp) btn.getAttribute("exp");
						if((rate_method.getValue()!=null&&!rate_method.getValue().equals(""))&&
								(rate_type.getValue()!=null&&!rate_type.getValue().equals(""))&&
								(pay_method.getValue()!=null&&!pay_method.getValue().equals(""))){
							exp.setRate_method(Long.parseLong(rate_method.getValue()));
							exp.setRate_type(Long.parseLong(rate_type.getValue()));
							exp.setPay_method(Long.parseLong(pay_method.getValue()));
						} else {
							alert("Не заполнены параметры описания ставки");
							return;
						}
						Ld_rate[] rates = getValuesRate();
						exp.setLd_rates(rates);
						if(current.getLd_exp()!=null){
							Ld_exp[] exps = new Ld_exp[current.getLd_exp().length+1];
							for (int i = 0; i < exps.length; i++) {
								if(current.getLd_exp().length!=i){
									exps[i] = current.getLd_exp()[i];
								} else {
									exps[i] = exp;
								}
							}
//							current.setLd_exp(exps);
//							Res res = new Res();
							c = ConnectionPool.getConnection(alias);
							exp.setId(current.getLd_char().getId());
							CreditService.saveLd_exp(exp, c);
//							CreditService.saveCredit(current, res, alias);
//							if(res.getCode()==1){
//								alert(res.getName());
//								return;
//							}
							clearExpRows();
							clearRateRows();
							current.setLd_exp(CreditService.getLdExp(current.getLd_char().getId(), mfo, c));
							setExp(current.getLd_exp());
							btn_add_exp.setVisible(true);
							btn_add_rate.setVisible(false);
							c.commit();
						}
					} catch (Exception e) {
						e.printStackTrace();
						Utils.rollback(c);
						ISLogger.getLogger().error(CheckNull.getPstr(e));
					} finally {
						ConnectionPool.close(c);
					}
				}
			});
			ld_exp_radio.appendChild(exp_id);
			ld_exp_radio_btn.appendChild(btn);
			btn_add_exp.setVisible(false);
			btn_add_rate.setVisible(true);
			selected_exp = null;
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
	}
	
	private Ld_rate[] getValuesRate(){
		List<?> rate = rate_row.getChildren();
		Ld_rate ld_rate = new Ld_rate();
		Ld_rate[] rates = new Ld_rate[1];
		if(rate!=null&&rate.size()>0){
			for (int i = 0; i < rate.size(); i++) {
				if(rate.get(i) instanceof Row){
					Row row = (Row) rate.get(i);
					List<?> items = row.getChildren();
					RefCBox rate_id = (RefCBox) items.get(0);
					Decimalbox drate = (Decimalbox) items.get(1);
					Decimalbox dcoeff = (Decimalbox) items.get(2);
					Datebox date_begin = (Datebox) items.get(3);
					Datebox date_end = (Datebox) items.get(4);
					if(rate_id.getValue()!=null&&!rate_id.getValue().equals("")){
						ld_rate.setRate_id(Long.parseLong(rate_id.getValue()));
					} if(drate.getValue()!=null&&!drate.getValue().equals("")){
						ld_rate.setRate(drate.getValue().doubleValue());
					} if(dcoeff.getValue()!=null&&!dcoeff.getValue().equals("")){
						ld_rate.setCoeff(dcoeff.getValue().doubleValue());
					} if(date_begin.getValue()!=null&&!date_begin.getValue().equals("")){
						ld_rate.setDate_open(date_begin.getValue());
					} if(date_end.getValue()!=null&&!date_end.getValue().equals("")){
						ld_rate.setDate_close(date_end.getValue());
					}
					ld_rate.setNew(true);
				}
			}
			rates[0] = ld_rate;
		} else {
			alert("Не заполнены параметры ставки");
		}
		return rates;
	}
	
	public void onClick$btn_add_rate(){
		try {
			clearRateRows();
			Ld_rate rate = new Ld_rate();
			rate.setNew(true);
			addRate_row(rate, true);
			btn_add_rate.setVisible(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void onChange$niki_id(){
		changeNiki_id();
	}
	
	public void onOk$niki_id(){
		changeNiki_id();
	}
	
	public void onClick$fill_graf(){
		if(main_radio.getSelectedIndex()>=0){
			Radio radio = main_radio.getSelectedItem();
			Ld_gr gr = (Ld_gr) radio.getAttribute("gr");
			CreditService.fillGraf(current.getLd_char().getId(), gr.getOper_id(), gr.getExp_id(), alias);
		}
	}
	
	public void onClick$recall_graf(){
		if(main_radio.getSelectedIndex()>=0){
			CreditService.recallGraf(current.getLd_char().getId(), alias);
		}
	}
	
	private void changeNiki_id(){
		if(branch.getValue()!=null&&!branch.getValue().equals("")&&niki_id.getValue()!=null){
			CreditApp ni_req = CreditService.getNi_req(niki_id.getValue(), branch.getValue(), alias);
			if(ni_req!=null){
				currency.setSelecteditem(ni_req.getCurrency());
				setRboxReadOnly(currency, true);
				shifr_id.setSelecteditem(ni_req.getShifr_id());
				setRboxReadOnly(shifr_id, true);
				amount.setValue(Double.parseDouble(ni_req.getAmount()));
				use_branch.setSelecteditem(ni_req.getType_zm());
				setRboxReadOnly(use_branch, true);
				kred_id.setSelecteditem(ni_req.getKred_id());
				setRboxReadOnly(kred_id, true);
			} else {
				alert("Заявки с номером - "+niki_id.getValue()+" не существует!");
			}
		}
	}
	
	private void setRboxReadOnly(RefCBox rbox,boolean bool){
		rbox.setReadonly(bool);
		rbox.setButtonVisible(!bool);
	}
}
