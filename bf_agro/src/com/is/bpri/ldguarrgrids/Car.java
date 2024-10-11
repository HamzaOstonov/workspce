package com.is.bpri.ldguarrgrids;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Decimalbox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Tabpanels;
import org.zkoss.zul.Tabs;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbarbutton;

import com.is.ISLogger;
import com.is.bpri.LdGuarr;
import com.is.bpri.LdGuarrFilter;
import com.is.bpri.LdGuarrPagingListModel;
import com.is.bpri.utils.Utils;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.RefData;
import com.is.utils.Res;

@SuppressWarnings("serial")
public class Car extends GenericForwardComposer{
	
	private Div cardiv,grd;
	private Textbox id_client,name,street,massiv,home,home_num,doc_num,inn_reestr,notarial_doc_numb,
					name2,insc_inn,insc_num,polis_num,ser_eval_company,lis_num,eval_report_num;
	private RefCBox region_id,distr_id,currency,klass_o,code_subject,niki_res1,notarial_office_num,insc_name,
					acomp_name,acomp_curr;
	private Datebox doc_date,start_date,insc_date,polis_date,insc_date_cl,lis_date,acomp_date;
	private Decimalbox amount,acomp_summa;
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
	private List<CarModel> car = null;
	private int state = -1;
	private Tabbox tabbox;
	private Tabs tabs;
	private Tabpanels tabpanels;
	private Tabpanel tabpanel;
	private String label = "Дополнительные поля";
	private String image = "/images/+.png";
	private int index = 0;
	private int size = 0;

	public Car() {
		super('$',false,false);
	}
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		getParams();
		refCboxsetModels();
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
        tabpanel.appendChild(createGrid());
        createTabs();
		inputDatas();
		cardiv.setVisible(true);
	}
	
	public void onInitRenderLater$region_id(){
		if(clickbtn.equals("double")){
			region_id.setSelecteditem(current.getRegion_id());
			onSelect$region_id();
		}
	}
	
	public void onInitRenderLater$notarial_office_num(){
		if(clickbtn.equals("double")){
			notarial_office_num.setSelecteditem(current.getNotarial_office_num());
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
	
	public void onInitRenderLater$code_subject(){
		if(clickbtn.equals("double")){
			code_subject.setSelecteditem(current.getCode_subject());
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
	
	public void onInitRenderLater$insc_name(){
		if(clickbtn.equals("double")){
			insc_name.setSelecteditem(current.getInsc_name());
		}
	}
	
	public void onEvent(Event evt){
		if(evt.getName().equals("onInitRenderLater")){
			if(evt.getTarget() instanceof RefCBox){
				RefCBox rbox = (RefCBox) evt.getTarget();
				onInitrender(rbox);
			}
		} else if(evt.getName().equals("onClick$btn_saving")){
			onClick$btn_saving();
		} else if(evt.getName().equals("onClick$btn_del")){
			onClick$btn_del();
		} else if(evt.getName().equals("onClick$btn_cancel")){
			onClick$btn_cancel();
		} else if(evt.getName().equals("onChange$id_client")){
			onChange$id_client();
		} else if(evt.getName().equals("onSelect$region_id")){
			onSelect$region_id();
		} else if(evt.getName().equals("onInitRenderLater$insc_name")){
			onInitRenderLater$insc_name();
		} else if(evt.getName().equals("onInitRenderLater$acomp_curr")){
			onInitRenderLater$acomp_curr();
		} else if(evt.getName().equals("onInitRenderLater$acomp_name")){
			onInitRenderLater$acomp_name();
		} else if(evt.getName().equals("onInitRenderLater$niki_res1")){
			onInitRenderLater$niki_res1();
		} else if(evt.getName().equals("onInitRenderLater$code_subject")){
			onInitRenderLater$code_subject();
		} else if(evt.getName().equals("onInitRenderLater$klass_o")){
			onInitRenderLater$klass_o();
		} else if(evt.getName().equals("onInitRenderLater$currency")){
			onInitRenderLater$currency();
		} else if(evt.getName().equals("onInitRenderLater$distr_id")){
			onInitRenderLater$distr_id();
		} else if(evt.getName().equals("onInitRenderLater$notarial_office_num")){
			onInitRenderLater$notarial_office_num();
		} else if(evt.getName().equals("onInitRenderLater$region_id")){
			onInitRenderLater$region_id();
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
			car = CarService.getCarModels(current.getBpr_id()+"",current.getId(),alias);
			for (int i = 0; i < car.size(); i++) {
				setValues(i);
			}
			id_client.setValue(current.getId_client());
			name.setValue(current.getName());
			massiv.setValue(current.getMassiv());
			street.setValue(current.getStreet());
			home.setValue(current.getHome());
			home_num.setValue(current.getHome_num());
			amount.setValue(BigDecimal.valueOf(current.getAmount()));
			doc_num.setValue(current.getDoc_num());
			doc_date.setValue(current.getDoc_date());
			inn_reestr.setValue(current.getInn_reestr());
			name2.setValue(current.getName2());
			acomp_date.setValue(current.getAcomp_date());
			acomp_summa.setValue(BigDecimal.valueOf(current.getAcomp_summa()));
			notarial_doc_numb.setValue(current.getNotarial_doc_numb());
			insc_inn.setValue(current.getInsc_inn());
			insc_num.setValue(current.getInsc_num());
			polis_num.setValue(current.getPolis_num());
			ser_eval_company.setValue(current.getSer_eval_company());
			lis_num.setValue(current.getLis_num());
			eval_report_num.setValue(current.getEval_report_num());
			start_date.setValue(current.getStart_date());
			insc_date.setValue(current.getInsc_date());
			polis_date.setValue(current.getPolis_date());
			insc_date_cl.setValue(current.getInsc_date_cl());
			lis_date.setValue(current.getLis_date());
//			mileage.setValue(car.getMileage());
//			engine_num.setValue(car.getEngine_num());
//			body_num.setValue(car.getBody_num());
//			state_number.setValue(car.getState_number());
//			doc_ser_num.setValue(car.getDoc_ser_num());
//			position.setValue(car.getPosition());
//			chassis_number.setValue(car.getChassis_number());
//			car_doc_date.setValue(car.getDoc_date());
		} else if(clickbtn.equals("add")){
			Utils.clearForm(cardiv);
			cardiv.setVisible(false);
		}
		btn_saving.setDisabled(false);
	}
	
	@SuppressWarnings("unchecked")
	private void setValues(int i){
		tabbox.setSelectedIndex(i);
		if(i>0){
			Tab tab = tabbox.getSelectedTab();
			tab.setLabel(label);
			tab.setImage(null);
			Tabpanel tabpanel = tabbox.getSelectedPanel();
			tabpanel.appendChild(createGrid());
			createTabs();
			tab.setAttribute("add", "notadd");
		}
		Tabpanel tabpanel = tabbox.getSelectedPanel();
		Grid grid = (Grid) tabpanel.getChildren().get(0);
		Rows rows = grid.getRows();
		List<Row> row = rows.getChildren();
		for (int j = 0; j < row.size(); j++) {
			for (int j2 = 0; j2 < row.get(j).getChildren().size(); j2++) {
				if(row.get(j).getChildren().get(j2) instanceof RefCBox){
					RefCBox rbox = (RefCBox) row.get(j).getChildren().get(j2);
					if(rbox.getAttribute("car_type")!=null&&rbox.getAttribute("car_type").equals("car_type")){
						rbox.setSelecteditem(car.get(i).getCar_type());
					} else if(rbox.getAttribute("car_marka")!=null&&rbox.getAttribute("car_marka").equals("car_marka")){
						rbox.setModel(new ListModelList(CarService.getCarMarka(car.get(i).getCar_type(), alias)));
					} else if(rbox.getAttribute("car_model")!=null&&rbox.getAttribute("car_model").equals("car_model")){
						rbox.setModel(new ListModelList(CarService.getCarModel(car.get(i).getCar_type(), car.get(i).getCar_marka(), alias)));
					} else if(rbox.getAttribute("code_country")!=null&&rbox.getAttribute("code_country").equals("code_country")){
						rbox.setSelecteditem(car.get(i).getCode_country());
					} else if(rbox.getAttribute("date_made")!=null&&rbox.getAttribute("date_made").equals("date_made")){
						rbox.setSelecteditem(car.get(i).getDate_made());
					} else if(rbox.getAttribute("color")!=null&&rbox.getAttribute("color").equals("color")){
						rbox.setSelecteditem(car.get(i).getColor());
					}
				} else if(row.get(j).getChildren().get(j2) instanceof Textbox){
					Textbox txt = (Textbox) row.get(j).getChildren().get(j2);
					if(txt.getAttribute("mileage")!=null&&txt.getAttribute("mileage").equals("mileage")){
						txt.setValue(car.get(i).getMileage());
					} else if(txt.getAttribute("engine_num")!=null&&txt.getAttribute("engine_num").equals("engine_num")){
						txt.setValue(car.get(i).getEngine_num());
					} else if(txt.getAttribute("body_num")!=null&&txt.getAttribute("body_num").equals("body_num")){
						txt.setValue(car.get(i).getBody_num());
					} else if(txt.getAttribute("state_number")!=null&&txt.getAttribute("state_number").equals("state_number")){
						txt.setValue(car.get(i).getState_number());
					} else if(txt.getAttribute("doc_ser_num")!=null&&txt.getAttribute("doc_ser_num").equals("doc_ser_num")){
						txt.setValue(car.get(i).getDoc_ser_num());
					} else if(txt.getAttribute("position")!=null&&txt.getAttribute("position").equals("position")){
						txt.setValue(car.get(i).getPosition());
					} else if(txt.getAttribute("chassis_number")!=null&&txt.getAttribute("chassis_number").equals("chassis_number")){
						txt.setValue(car.get(i).getChassis_number());
					}
				} else if(row.get(j).getChildren().get(j2) instanceof Datebox){
					Datebox dbox = (Datebox) row.get(j).getChildren().get(j2);
					if(dbox.getAttribute("car_doc_date")!=null&&dbox.getAttribute("car_doc_date").equals("car_doc_date")){
						dbox.setValue(car.get(i).getDoc_date());
					}
				}
			}
		}
	}
	
	public void onSelect$region_id(){
		if(!region_id.getValue().equals("")){
			distr_id.setModel(new ListModelList(com.is.bpri.LdGuarrService.getDistr(alias, region_id.getValue())));
		}
	}
	
	public void onChange$id_client(){
		List<RefData> list = com.is.bpri.LdGuarrService.getClientName(id_client.getValue(), branch, alias);
		if(!list.isEmpty()){
			name.setValue(list.get(0).getLabel());
			code_subject.setSelecteditem(list.get(0).getData());
			niki_res1.setSelecteditem(com.is.bpri.LdGuarrService.getResidentResult(id_client.getValue(), branch, alias));
		}
	}
	
	public void onClick$btn_cancel(){
		grd.setVisible(true);
		addgrdl.setVisible(false);
		cardiv.setVisible(false);
		btn_back.setImage("/images/file.png");
		btn_back.setLabel(Labels.getLabel("back"));
	}
	
	public void onClick$btn_saving(){
		try {
			if(current==null){
				current = new LdGuarr();
			}
			System.out.println("Привет");
//			car = new CarModel();
//			car.setId(car_id);
//			car.setMileage(mileage.getValue());
//			car.setEngine_num(engine_num.getValue());
//			car.setBody_num(body_num.getValue());
//			car.setState_number(state_number.getValue());
//			car.setDoc_ser_num(doc_ser_num.getValue());
//			car.setPosition(position.getValue());
//			car.setChassis_number(chassis_number.getValue());
//			car.setDoc_date(car_doc_date.getValue());
//			car.setCar_type(car_type.getValue());
//			car.setCar_marka(car_marka.getValue());
//			car.setCar_model(car_model.getValue());
//			car.setCode_country(code_country.getValue());
//			car.setDate_made(date_made.getValue());
//			car.setColor(color.getValue());
			
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
			current.setCode_subject(code_subject.getValue());
			current.setInn_reestr(inn_reestr.getValue());
			current.setName2(name2.getValue());
			current.setNiki_res1(niki_res1.getValue());
			current.setAcomp_name(acomp_name.getValue());
			current.setAcomp_date(acomp_date.getValue());
			current.setAcomp_curr(acomp_curr.getValue());
			current.setNotarial_doc_numb(notarial_doc_numb.getValue());
			current.setInsc_inn(insc_inn.getValue());
			current.setInsc_num(insc_num.getValue());
			current.setPolis_num(polis_num.getValue());
			current.setSer_eval_company(ser_eval_company.getValue());
			current.setLis_num(lis_num.getValue());
			current.setEval_report_num(eval_report_num.getValue());
			current.setStart_date(start_date.getValue());
			current.setInsc_date(insc_date.getValue());
			current.setPolis_date(polis_date.getValue());
			current.setInsc_date_cl(insc_date_cl.getValue());
			current.setLis_date(lis_date.getValue());
			current.setNotarial_office_num(notarial_office_num.getValue());
			current.setInsc_name(insc_name.getValue());
			if(acomp_summa.getValue()!=null){
				current.setAcomp_summa(Integer.parseInt(acomp_summa.getValue()+""));
			}
			Res res = new Res();
			List<CarModel> list = new ArrayList<CarModel>();
			for (int i = 0; i < tabbox.getTabsApi().getChildren().size()-1; i++) {
				list.add(getCars(i));
			}
			if(clickbtn.equals("add")){
				CarService.create(current,list,res,alias);
			} else if (clickbtn.equals("double")){
				CarService.update(current,car,list,res,alias);
			}
			if(res.getCode()!=1){
				alert("Данные успешно сохраненны");
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
	
	@SuppressWarnings("unchecked")
	private CarModel getCars(int i){
		CarModel car = new CarModel();
		tabbox.setSelectedIndex(i);
		Tabpanel tabpanel = tabbox.getSelectedPanel();
		Grid grid = (Grid)tabpanel.getChildren().get(0);
		Rows rows = grid.getRows();
		List<Row> row = rows.getChildren();
		for (int j = 0; j < row.size(); j++) {
			for (int j2 = 0; j2 < row.get(j).getChildren().size(); j2++) {
				if(row.get(j).getChildren().get(j2) instanceof RefCBox){
					RefCBox rbox = (RefCBox) row.get(j).getChildren().get(j2);
					if(rbox.getAttribute("car_type")!=null&&rbox.getAttribute("car_type").equals("car_type")){
						car.setCar_type(rbox.getValue());
					} else if(rbox.getAttribute("car_marka")!=null&&rbox.getAttribute("car_marka").equals("car_marka")){
						car.setCar_marka(rbox.getValue());
					} else if(rbox.getAttribute("car_model")!=null&&rbox.getAttribute("car_model").equals("car_model")){
						car.setCar_model(rbox.getValue());
					} else if(rbox.getAttribute("code_country")!=null&&rbox.getAttribute("code_country").equals("code_country")){
						car.setCode_country(rbox.getValue());
					} else if(rbox.getAttribute("date_made")!=null&&rbox.getAttribute("date_made").equals("date_made")){
						car.setDate_made(rbox.getValue());
					} else if(rbox.getAttribute("color")!=null&&rbox.getAttribute("color").equals("color")){
						car.setColor(rbox.getValue());
					}
				} else if(row.get(j).getChildren().get(j2) instanceof Textbox){
					Textbox txt = (Textbox) row.get(j).getChildren().get(j2);
					if(txt.getAttribute("mileage")!=null&&txt.getAttribute("mileage").equals("mileage")){
						car.setMileage(txt.getValue());
					} else if(txt.getAttribute("engine_num")!=null&&txt.getAttribute("engine_num").equals("engine_num")){
						car.setEngine_num(txt.getValue());
					} else if(txt.getAttribute("body_num")!=null&&txt.getAttribute("body_num").equals("body_num")){
						car.setBody_num(txt.getValue());
					} else if(txt.getAttribute("state_number")!=null&&txt.getAttribute("state_number").equals("state_number")){
						car.setState_number(txt.getValue());
					} else if(txt.getAttribute("doc_ser_num")!=null&&txt.getAttribute("doc_ser_num").equals("doc_ser_num")){
						car.setDoc_ser_num(txt.getValue());
					} else if(txt.getAttribute("position")!=null&&txt.getAttribute("position").equals("position")){
						car.setPosition(txt.getValue());
					} else if(txt.getAttribute("chassis_number")!=null&&txt.getAttribute("chassis_number").equals("chassis_number")){
						car.setChassis_number(txt.getValue());
					}
				} else if(row.get(j).getChildren().get(j2) instanceof Datebox){
					Datebox dbox = (Datebox) row.get(j).getChildren().get(j2);
					if(dbox.getAttribute("car_doc_date")!=null&&dbox.getAttribute("car_doc_date").equals("car_doc_date")){
						car.setDoc_date(dbox.getValue());
					}
				}
			}
		}
		car.setBpr_id(template);
		return car;
	}
	
	private void refCboxsetModels(){
		currency.setModel((new ListModelList(com.is.bpri.LdGuarrService.getCurrency(alias))));
		klass_o.setModel(new ListModelList(com.is.bpri.LdGuarrService.getKlass_o(alias)));
		region_id.setModel(new ListModelList(com.is.bpri.LdGuarrService.getRegion(alias)));
		code_subject.setModel(new ListModelList(com.is.bpri.LdGuarrService.getTypeClient(alias)));
		niki_res1.setModel(new ListModelList(com.is.bpri.LdGuarrService.getResident(alias)));
		acomp_name.setModel(new ListModelList(com.is.bpri.LdGuarrService.getNameCompany(alias)));
		acomp_curr.setModel((new ListModelList(com.is.bpri.LdGuarrService.getCurrency(alias))));
		notarial_office_num.setModel(new ListModelList(com.is.bpri.LdGuarrService.getNotarialNumb(alias)));
		insc_name.setModel(new ListModelList(com.is.bpri.LdGuarrService.getInscName(alias)));
	}
	
	public void onClick$btn_del(){
		Res res = new Res();
		CarService.remove(current,car,res,alias);
		if(res.getCode()!=1){
			alert("Данные успешно удалены");
			refreshModel(0);
			onClick$btn_cancel();
		} else {
			alert(res.getName());
		}
	}
	
	private void createTabs(){
		try {
			Tab tab = new Tab();
			tab.setLabel("Добавить таб");
			tab.setImage(image);
			tab.setAttribute("add", "add");
			tab.addEventListener(Events.ON_CLICK, new EventListener() {
				
				@Override
				public void onEvent(Event event) throws Exception {
					Tab tab = (Tab) event.getTarget();
					if(state==0){
						if(tab.getAttribute("add").equals("add")){
							tab.setLabel(label);
							tab.setImage(null);
							Tabpanel tabpanel = tabbox.getSelectedPanel();
							tabpanel.appendChild(createGrid());
							createTabs();
							tab.setAttribute("add", "notadd");
						} 
					} else {
						alert("Нельзя добавить в утверждённом шаблоне!");
						tabbox.setSelectedIndex(tabbox.getSelectedIndex()-1);
					}
				}
			});
			tab.addEventListener(Events.ON_DOUBLE_CLICK, new EventListener() {
				
				@Override
				public void onEvent(Event event) throws Exception {
					Tab tab = (Tab) event.getTarget();
					if(tab.getAttribute("add").equals("notadd")){
						Tabpanel tabpanel = tabbox.getSelectedPanel();
						tabbox.setSelectedIndex(tabbox.getSelectedIndex()-1);
						tabs.removeChild(tab);
						tabpanels.removeChild(tabpanel);
					}
				}
			});
			Tabpanel tabpanel = new Tabpanel();
			tabs.appendChild(tab);
			tabpanels.appendChild(tabpanel);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void onInitrender(RefCBox rbox){
		if(clickbtn.equals("double")){
			if(index>=car.size()){
				index = 0;
			}
			if(rbox.getAttribute("car_type")!=null&&rbox.getAttribute("car_type").equals("car_type")){
				rbox.setSelecteditem(car.get(index).getCar_type());
				size++;
			} else if(rbox.getAttribute("car_marka")!=null&&rbox.getAttribute("car_marka").equals("car_marka")){
				rbox.setSelecteditem(car.get(index).getCar_marka());
				size++;
			} else if(rbox.getAttribute("car_model")!=null&&rbox.getAttribute("car_model").equals("car_model")){
				rbox.setSelecteditem(car.get(index).getCar_model());
				size++;
			} else if(rbox.getAttribute("code_country")!=null&&rbox.getAttribute("code_country").equals("code_country")){
				rbox.setSelecteditem(car.get(index).getCode_country());
				size++;
			} else if(rbox.getAttribute("date_made")!=null&&rbox.getAttribute("date_made").equals("date_made")){
				rbox.setSelecteditem(car.get(index).getDate_made());
				size++;
			} else if(rbox.getAttribute("color")!=null&&rbox.getAttribute("color").equals("color")){
				rbox.setSelecteditem(car.get(index).getColor());
				size++;
			}
		}
		if(size%6==0){
			index++;
		}
	}
	
	private Grid createGrid(){
		Grid grid = new Grid();
		Rows rows = new Rows();
		Row row1 = new Row();
		Row row2 = new Row();
		Row row3 = new Row();
		Row row4 = new Row();
		Row row5 = new Row();
		Row row6 = new Row();
		Row row7 = new Row();
		Label label1 = new Label();
		label1.setValue("Тип автомобиля");
		Label label2 = new Label();
		label2.setValue("Марка автомобиля");
		final RefCBox rcbox1 = new RefCBox();
		rcbox1.setWidth("100%");
		rcbox1.setAttribute("car_type", "car_type");
		rcbox1.setModel(new ListModelList(CarService.getCarType(alias)));
		final RefCBox rcbox2 = new RefCBox();
		rcbox2.setAttribute("car_marka", "car_marka");
		rcbox2.setWidth("100%");
		rcbox1.addEventListener(Events.ON_SELECT, new EventListener() {
			
			@Override
			public void onEvent(Event evt) throws Exception {
				RefCBox temp = (RefCBox) evt.getTarget();
				rcbox2.setModel(new ListModelList(CarService.getCarMarka(temp.getValue(), alias)));
			}
		});
		rcbox1.addEventListener("onInitRenderLater", this);
		rcbox2.addEventListener("onInitRenderLater", this);
		row1.appendChild(label1);
		row1.appendChild(rcbox1);
		row1.appendChild(label2);
		row1.appendChild(rcbox2);
		Label label3 = new Label();
		label3.setValue("Модель автомобиля");
		Label label4 = new Label();
		label4.setValue("Страна производителя");
		final RefCBox rcbox3 = new RefCBox();
		rcbox3.setAttribute("car_model", "car_model");
		rcbox3.setWidth("100%");
		rcbox3.addEventListener("onInitRenderLater", this);
		rcbox2.addEventListener(Events.ON_SELECT, new EventListener() {
			
			@Override
			public void onEvent(Event evt) throws Exception {
				RefCBox temp = (RefCBox) evt.getTarget();
				rcbox3.setModel(new ListModelList(CarService.getCarModel(rcbox1.getValue(), temp.getValue(), alias)));
			}
		});
		RefCBox rcbox4 = new RefCBox();
		rcbox4.setAttribute("code_country", "code_country");
		rcbox4.setWidth("100%");
		rcbox4.setModel(new ListModelList(CarService.getCodeStr(alias)));
		rcbox4.addEventListener("onInitRenderLater", this);
		row2.appendChild(label3);
		row2.appendChild(rcbox3);
		row2.appendChild(label4);
		row2.appendChild(rcbox4);
		Label label5 = new Label();
		label5.setValue("Год выпуска");
		Label label6 = new Label();
		label6.setValue("Пробег");
		RefCBox rcbox5 = new RefCBox();
		rcbox5.addEventListener("onInitRenderLater", this);
		rcbox5.setAttribute("date_made", "date_made");
		rcbox5.setWidth("100%");
		rcbox5.setModel(new ListModelList(CarService.getYears(alias)));
		Textbox txt6 = new Textbox();
		txt6.setMaxlength(20);
		txt6.setAttribute("mileage", "mileage");
		txt6.setWidth("100%");
		row3.appendChild(label5);
		row3.appendChild(rcbox5);
		row3.appendChild(label6);
		row3.appendChild(txt6);
		Label label7 = new Label();
		label7.setValue("№ двигателя");
		Label label8 = new Label();
		label8.setValue("№ кузова");
		Textbox txt7 = new Textbox();
		txt7.setAttribute("engine_num", "engine_num");
		txt7.setMaxlength(30);
		txt7.setWidth("100%");
		Textbox txt8 = new Textbox();
		txt8.setAttribute("body_num", "body_num");
		txt8.setMaxlength(30);
		txt8.setWidth("100%");
		row4.appendChild(label7);
		row4.appendChild(txt7);
		row4.appendChild(label8);
		row4.appendChild(txt8);
		Label label9 = new Label();
		label9.setValue("Цвет");
		Label label10 = new Label();
		label10.setValue("Гос номер");
		RefCBox rcbox9 = new RefCBox();
		rcbox9.setAttribute("color", "color");
		rcbox9.setWidth("100%");
		rcbox9.setModel(new ListModelList(CarService.getColors(alias)));
		rcbox9.addEventListener("onInitRenderLater", this);
		Textbox txt10 = new Textbox();
		txt10.setAttribute("state_number", "state_number");
		txt10.setMaxlength(30);
		txt10.setWidth("100%");
		row5.appendChild(label9);
		row5.appendChild(rcbox9);
		row5.appendChild(label10);
		row5.appendChild(txt10);
		Label label11 = new Label();
		label11.setValue("Серия и номер тех паспорта");
		Label label12 = new Label();
		label12.setValue("Дата тех паспорта");
		Textbox txt11 = new Textbox();
		txt11.setAttribute("doc_ser_num", "doc_ser_num");
		txt11.setMaxlength(100);
		txt11.setWidth("100%");
		Datebox dbox12 = new Datebox();
		dbox12.setAttribute("car_doc_date", "car_doc_date");
		dbox12.setMaxlength(10);
		dbox12.setFormat("dd.MM.yyyy");
		dbox12.setWidth("100%");
		row6.appendChild(label11);
		row6.appendChild(txt11);
		row6.appendChild(label12);
		row6.appendChild(dbox12);
		Label label13 = new Label();
		label13.setValue("Позиция");
		Label label14 = new Label();
		label14.setValue("Номер шосси");
		Textbox txt13 = new Textbox();
		txt13.setAttribute("position", "position");
		txt13.setMaxlength(3);
		txt13.setWidth("100%");
		Textbox txt14 = new Textbox();
		txt14.setAttribute("chassis_number", "chassis_number");
		txt14.setMaxlength(30);
		txt14.setWidth("100%");
		row7.appendChild(label13);
		row7.appendChild(txt13);
		row7.appendChild(label14);
		row7.appendChild(txt14);
		rows.appendChild(row1);
		rows.appendChild(row2);
		rows.appendChild(row3);
		rows.appendChild(row4);
		rows.appendChild(row5);
		rows.appendChild(row6);
		rows.appendChild(row7);
		grid.appendChild(rows);
		return grid;
	}
}
