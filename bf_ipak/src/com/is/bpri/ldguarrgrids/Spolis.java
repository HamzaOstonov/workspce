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

public class Spolis extends GenericForwardComposer{

	private static final long serialVersionUID = 1L;
	private Div grd,spolisdiv;
	private Toolbarbutton btn_back,btn_saving,btn_del;
	private Textbox id_client,name,massiv,street,home,home_num,doc_num,notarial_doc_numb,name2,insc_inn,insc_num,polis_num;
	private RefCBox region_id,distr_id,currency,klass_o,acomp_name,acomp_curr;
	private Decimalbox amount,acomp_summa;
	private Datebox doc_date,end_date,start_date,insc_date,acomp_date,polis_date;
	private String alias = "",branch = "",clickbtn = "",guar_id = "";
	private LdGuarr current = null;
	private int template;
	private Grid spolisgrd,addgrdl;
	private Paging ldguarrPaging;
	private int _pageSize;
	private LdGuarrPagingListModel model = null;
	private LdGuarrFilter filter = new LdGuarrFilter();
	private int _totalSize;
	private Listbox dataGrid;
	private int state = -1;
	
	public Spolis(){
		super('$',false,false);
	}	
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		getParams();
		refCboxsetModels();
		inputDatas();
		spolisgrd.setVisible(true);
		spolisdiv.setVisible(true);
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
	
	public void onInitRenderLater$region_id(){
		if(clickbtn.equals("double")){
			region_id.setSelecteditem(current.getRegion_id());
			onSelect$region_id();
		}
	}
	
	public void onSelect$region_id(){
		if(!region_id.getValue().equals("")){
			distr_id.setModel(new ListModelList(com.is.bpri.LdGuarrService.getDistr(alias, region_id.getValue())));
		}
	}
	
	public void onInitRenderLater$distr_id(){
		if(clickbtn.equals("double")){
			distr_id.setSelecteditem(current.getDistr_id());
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
	
	public void onChange$id_client(){
		List<RefData> list = com.is.bpri.LdGuarrService.getClientName(id_client.getValue(), branch, alias);
		if(!list.isEmpty()){
			name.setValue(list.get(0).getLabel());
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
		try {
			if(clickbtn.equals("double")){
				id_client.setValue(current.getId_client());
				name.setValue(current.getName());
				massiv.setValue(current.getMassiv());
				street.setValue(current.getStreet());
				home.setValue(current.getHome());
				home_num.setValue(current.getHome_num());
				amount.setValue(BigDecimal.valueOf(current.getAmount()));
				doc_num.setValue(current.getDoc_num());
				doc_date.setValue(current.getDoc_date());
				end_date.setValue(current.getEnd_date());
				name2.setValue(current.getName2());
				acomp_date.setValue(current.getAcomp_date());
				acomp_summa.setValue(BigDecimal.valueOf(current.getAcomp_summa()));
				notarial_doc_numb.setValue(current.getNotarial_doc_numb());
				insc_inn.setValue(current.getInsc_inn());
				insc_num.setValue(current.getInsc_num());
				polis_num.setValue(current.getPolis_num());
				start_date.setValue(current.getStart_date());
				insc_date.setValue(current.getInsc_date());
				polis_date.setValue(current.getPolis_date());
			} else if(clickbtn.equals("add")){
				Utils.clearForm(spolisgrd);
				spolisgrd.setVisible(false);
			}
			btn_saving.setDisabled(false);
		} catch (Exception e) {
			e.printStackTrace();
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
	
	public void onClick$btn_cancel(){
//		frm.setVisible(false);
		grd.setVisible(true);
		addgrdl.setVisible(false);
		spolisdiv.setVisible(false);
		btn_back.setImage("/images/file.png");
		btn_back.setLabel(Labels.getLabel("back"));
//		addgrdl.setVisible(false);
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
			current.setRegion_id(region_id.getValue());
			current.setDistr_id(distr_id.getValue());
			current.setMassiv(massiv.getValue());
			current.setStreet(street.getValue());
			current.setHome(home.getValue());
			current.setHome_num(home_num.getValue());
			if(amount.getValue()!=null){
				current.setAmount(Integer.parseInt(amount.getValue()+""));
			}
			current.setDoc_num(doc_num.getValue());
			current.setDoc_date(doc_date.getValue());
			current.setEnd_date(end_date.getValue());
			current.setName2(name2.getValue());
			current.setAcomp_name(acomp_name.getValue());
			current.setAcomp_date(acomp_date.getValue());
			current.setAcomp_curr(acomp_curr.getValue());
			if(acomp_summa.getValue()!=null){
				current.setAcomp_summa(acomp_summa.getValue().longValue());
			}
			current.setNotarial_doc_numb(notarial_doc_numb.getValue());
			current.setStart_date(start_date.getValue());
			current.setInsc_inn(insc_inn.getValue());
			current.setInsc_num(insc_num.getValue());
			current.setInsc_date(insc_date.getValue());
			current.setPolis_num(polis_num.getValue());
			current.setPolis_date(polis_date.getValue());
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
		region_id.setModel(new ListModelList(com.is.bpri.LdGuarrService.getRegion(alias)));
		acomp_name.setModel(new ListModelList(com.is.bpri.LdGuarrService.getNameCompany(alias)));
		acomp_curr.setModel((new ListModelList(com.is.bpri.LdGuarrService.getCurrency(alias))));
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
