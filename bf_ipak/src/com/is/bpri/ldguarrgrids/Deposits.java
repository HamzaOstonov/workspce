package com.is.bpri.ldguarrgrids;

import java.math.BigDecimal;
import java.util.List;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Decimalbox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbarbutton;
import com.is.ISLogger;
import com.is.bpri.LdGuarr;
import com.is.bpri.LdGuarrFilter;
import com.is.bpri.LdGuarrPagingListModel;
import com.is.bpri.LdGuarrService;
import com.is.bpri.utils.Utils;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.RefData;
import com.is.utils.Res;

@SuppressWarnings("serial")
public class Deposits extends GenericForwardComposer{
	
	private Div depositdiv,grd;
	private Textbox id_client,name,address,doc_num,notarial_doc_numb,mfo,stock_name,name2,account,niki_inn;
	private RefCBox currency,klass_o,sign_depo,niki_res1,niki_owner,acomp_name,acomp_curr;
	private Datebox doc_date,start_date,end_date,acomp_date;
	private Decimalbox acomp_summa;
	private LdGuarr current = null;
	private String alias = "",branch = "",clickbtn = "",guar_id = "";
	private Grid addgrdl;
	private int template;
	private Paging ldguarrPaging;
	private int _pageSize;
	private LdGuarrPagingListModel model = null;
	private LdGuarrFilter filter = new LdGuarrFilter();
	private int _totalSize;
	private Listbox dataGrid;
	private Toolbarbutton btn_back,btn_saving,btn_del;
	private int state = -1;

	public Deposits() {
		super('$',false,false);
	}
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		getParams();
		refCboxsetModels();
		inputDatas();
		depositdiv.setVisible(true);
		if(this.param.get("state")!=null){
        	state = Integer.parseInt(((String[]) this.param.get("state"))[0]);
        }
        if(state==1||state==2){
        	btn_saving.setVisible(false);
        	btn_del.setVisible(false);
        } else if(state==0){
        	btn_saving.setVisible(true);
        	btn_del.setVisible(true);
        }
	}
	
	public void onInitRenderLater$currency(){
		if(clickbtn.equals("double")){
			currency.setSelecteditem(current.getCurrency());
		}
	}
	
	public void onInitRenderLater$klass_o(){
		if(clickbtn.equals("double")){
			klass_o.setSelecteditem(current.getKlass_o());
		}
	}
	
	public void onInitRenderLater$niki_res1(){
		if(clickbtn.equals("double")){
			niki_res1.setSelecteditem(current.getNiki_res1());
		}
	}
	
	public void onInitRenderLater$acomp_name(){
		if(clickbtn.equals("double")){
			acomp_name.setSelecteditem(current.getAcomp_name());
		}
	}
	
	public void onInitRenderLater$acomp_curr(){
		if(clickbtn.equals("double")){
			acomp_curr.setSelecteditem(current.getAcomp_curr());
		}
	}
	
	public void onInitRenderLater$sign_depo(){
		if(clickbtn.equals("double")){
			sign_depo.setSelecteditem(current.getSign_depo());
		}
	}
	
	public void onInitRenderLater$niki_owner(){
		if(clickbtn.equals("double")){
			niki_owner.setSelecteditem(current.getNiki_owner());
		}
	}
	
	private void getParams(){
		if (this.param.get("alias") != null) {
			alias = ((String[]) this.param.get("alias"))[0];
		}
		if (this.param.get("branch") != null) {
			branch = ((String[]) this.param.get("branch"))[0];
		}
		if (session.getAttribute("clickbtn") != null) {
			clickbtn = session.getAttribute("clickbtn")+"";
		}
		if (this.param.get("guar_id") != null) {
			guar_id = ((String[]) this.param.get("guar_id"))[0];
		}
		if (this.param.get("bprid") != null) {
			template = Integer.parseInt(((String[]) this.param.get("bprid"))[0]);
		}
		if(session.getAttribute("current")!=null){
			current = (LdGuarr) session.getAttribute("current");
		}
		if(session.getAttribute("addgrdl")!=null){
			addgrdl = (Grid) session.getAttribute("addgrdl");
		}
		if(session.getAttribute("grd")!=null){
			grd = (Div) session.getAttribute("grd");
		}
		if(session.getAttribute("btn_back")!=null){
			btn_back = (Toolbarbutton)session.getAttribute("btn_back");
		}
		if(session.getAttribute("ldguarrPaging")!=null){
			ldguarrPaging = (Paging) session.getAttribute("ldguarrPaging");
		}
		if(session.getAttribute("_pageSize")!=null){
			_pageSize = (Integer) session.getAttribute("_pageSize");
		}
		if(session.getAttribute("filter")!=null){
			filter = (LdGuarrFilter) session.getAttribute("filter");
		}
		if(session.getAttribute("_totalSize")!=null){
			_totalSize = (Integer) session.getAttribute("_totalSize");
		}
		if(session.getAttribute("dataGrid")!=null){
			dataGrid = (Listbox) session.getAttribute("dataGrid");
		}
	}
	
	private void refreshModel(int activePage) {
		ldguarrPaging.setPageSize(_pageSize);
		model = new LdGuarrPagingListModel(activePage, _pageSize, filter, alias);
		_totalSize = model.getTotalSize();
		ldguarrPaging.setTotalSize(_totalSize);
		dataGrid.setModel((ListModel) model);
		if (model.getSize() > 0) {
			current = (LdGuarr) model.getElementAt(0);
			sendSelEvt();
		}
	}
	
	private void sendSelEvt() {
		SelectEvent evt = new SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
		Events.sendEvent(evt);
	}
	
	private void inputDatas(){
		if(clickbtn.equals("double")){
			id_client.setValue(current.getId_client());
			name.setValue(current.getName());
			doc_num.setValue(current.getDoc_num());
			doc_date.setValue(current.getDoc_date());
			mfo.setValue(current.getMfo());
			account.setValue(current.getAccount());
			end_date.setValue(current.getEnd_date());
			name2.setValue(current.getName2());
			acomp_date.setValue(current.getAcomp_date());
			acomp_summa.setValue(BigDecimal.valueOf(current.getAcomp_summa()));
			address.setValue(current.getAddress());
			notarial_doc_numb.setValue(current.getNotarial_doc_numb());
			stock_name.setValue(current.getStock_name());
			niki_inn.setValue(current.getNiki_inn());
			start_date.setValue(current.getStart_date());
		} else if(clickbtn.equals("add")){
			Utils.clearForm(depositdiv);
			depositdiv.setVisible(false);
		}
		btn_saving.setDisabled(false);
	}
	
	public void onChange$id_client(){
		List<RefData> list = com.is.bpri.LdGuarrService.getClientName(id_client.getValue(), branch, alias);
		if(!list.isEmpty()){
			name.setValue(list.get(0).getLabel());
			niki_res1.setSelecteditem(com.is.bpri.LdGuarrService.getResidentResult(id_client.getValue(), branch, alias));
		}
	}
	
	public void onClick$btn_cancel(){
		grd.setVisible(true);
		addgrdl.setVisible(false);
		depositdiv.setVisible(false);
		btn_back.setImage("/images/file.png");
		btn_back.setLabel(Labels.getLabel("back"));
	}
	
	public void onClick$btn_saving(){
		try {
			if(current==null){
				current = new LdGuarr();
			}
			current.setBpr_id(template);
			current.setId_client(id_client.getValue());
			current.setGuar_id(guar_id);
			current.setKlass_o(klass_o.getValue());
			current.setCurrency(currency.getValue());
			current.setName(name.getValue());
			current.setDoc_num(doc_num.getValue());
			current.setDoc_date(doc_date.getValue());
			current.setMfo(mfo.getValue());
			current.setAccount(account.getValue());
			current.setEnd_date(end_date.getValue());
			current.setName2(name2.getValue());
			current.setNiki_res1(niki_res1.getValue());
			current.setAcomp_name(acomp_name.getValue());
			current.setAcomp_date(acomp_date.getValue());
			current.setAcomp_curr(acomp_curr.getValue());
			current.setAddress(address.getValue());
			current.setNiki_inn(niki_inn.getValue());
			current.setNotarial_doc_numb(notarial_doc_numb.getValue());
			current.setStock_name(stock_name.getValue());
			current.setStart_date(start_date.getValue());
			current.setSign_depo(sign_depo.getValue());
			current.setNiki_owner(niki_owner.getValue());
			if(acomp_summa.getValue()!=null){
				current.setAcomp_summa(Integer.parseInt(acomp_summa.getValue()+""));
			}
			Res res = new Res();
			if(clickbtn.equals("add")){
				LdGuarrService.create(current,res,alias);
			} else if (clickbtn.equals("double")){
				LdGuarrService.update(current,res,alias);
			}
			if(res.getCode()!=1){
				alert("������ ������� ����������");
				refreshModel(0);
				btn_saving.setDisabled(true);
				onClick$btn_cancel();
			} else {
				alert(res.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			alert(CheckNull.getPstr(e));
		}
	}
	
	private void refCboxsetModels(){
		currency.setModel((new ListModelList(com.is.bpri.LdGuarrService.getCurrency(alias))));
		klass_o.setModel(new ListModelList(com.is.bpri.LdGuarrService.getKlass_o(alias)));
		niki_res1.setModel(new ListModelList(com.is.bpri.LdGuarrService.getResident(alias)));
		acomp_name.setModel(new ListModelList(com.is.bpri.LdGuarrService.getNameCompany(alias)));
		acomp_curr.setModel((new ListModelList(com.is.bpri.LdGuarrService.getCurrency(alias))));
		sign_depo.setModel(new ListModelList(com.is.bpri.LdGuarrService.getSing_Depo(alias)));
		niki_owner.setModel(new ListModelList(com.is.bpri.LdGuarrService.getTypeClient(alias)));
	}
	
	public void onClick$btn_del(){
		Res res = new Res();
		LdGuarrService.remove(current, res, alias);
		if(res.getCode()!=1){
			alert("������ ������� �������");
			refreshModel(0);
			onClick$btn_cancel();
		} else {
			alert(res.getName());
		}
	}
}
