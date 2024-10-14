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

public class Realestate extends GenericForwardComposer{

	private static final long serialVersionUID = 1L;
	
	private Div realestatediv,grd;
	private Textbox id_client,name,massiv,street,home,home_num,doc_num,inn_reestr,notarial_doc_numb,name2,
	insc_inn,polis_num,ser_eval_company,lis_num,eval_report_num,economical_zone;
	private Datebox doc_date,insc_date,polis_date,insc_date_cl,lis_date,acomp_date,date_operation;
	private RefCBox region_id,distr_id,currency,klass_o,code_subject,niki_res1,notarial_office_num,insc_name,
	acomp_name,acomp_curr,cadastr_org_region,cadastr_org_distr;
	private Decimalbox amount,acomp_summa;
	private String alias = "",branch = "",clickbtn = "",guar_id = "";
	private int template;
	private Paging ldguarrPaging;
	private int _pageSize;
	private LdGuarrPagingListModel model = null;
	private LdGuarrFilter filter = new LdGuarrFilter();
	private int _totalSize;
	private Listbox dataGrid;
	private LdGuarr current = null;
	private List<RealestateCadastr> cadastrlist = null;
	private Grid addgrdl;
	private Toolbarbutton btn_back,btn_saving,btn_del;
	private Tabpanel tabpanel;
	private Tabs tabs;
	private Tabpanels tabpanels;
	private Tabbox tabbox;
	private String label = "Дополнительные поля";
	private String image = "/images/+.png";
	private int state = -1;
	
	public Realestate() {
		super('$',false,false);
	}
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		try {
			getParams();
			refCBoxModel();
			tabpanel.appendChild(createGrid());
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
	        createTabs();
			inputDatas();
			realestatediv.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void inputDatas(){
		if(clickbtn.equals("double")){
			cadastrlist = RealestateService.getList(current.getBpr_id(),current.getId(),alias);
			for (int i = 0; i < cadastrlist.size(); i++) {
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
			polis_num.setValue(current.getPolis_num());
			ser_eval_company.setValue(current.getSer_eval_company());
			lis_num.setValue(current.getLis_num());
			eval_report_num.setValue(current.getEval_report_num());
			economical_zone.setValue(current.getEconomical_zone());
			insc_date.setValue(current.getInsc_date());
			polis_date.setValue(current.getPolis_date());
			insc_date_cl.setValue(current.getInsc_date_cl());
			lis_date.setValue(current.getLis_date());
			date_operation.setValue(current.getDate_operation());
		} else if(clickbtn.equals("add")){
			Utils.clearForm(realestatediv);
			realestatediv.setVisible(false);
		}
		btn_saving.setDisabled(false);
	}
	
	private Grid createGrid(){
		Grid grd = new Grid();
		Rows rows = new Rows();
		Row row = new Row();
		Row row1 = new Row();
		Row row2 = new Row();
		Row row3 = new Row();
		Row row4 = new Row();
		Row row5 = new Row();
		Label label = new Label("Тип кадастра");
		Label label1 = new Label("Залоговая стоимость");
		Label label2 = new Label("Свидетельство о гос. Регистрации");
		Label label3 = new Label("Дата свидетельства о гос. Регистрации");
		Label label4 = new Label("№ кадастра");
		Label label5 = new Label("№ реестра объекта в кадастре");
		Label label6 = new Label("Площадь в кв.м");
		Label label7 = new Label("Право на собственность по кадастру");
		Label label8 = new Label("№");
		Label label9 = new Label("Блоки");
		Label label10 = new Label("Описание по кадастру");
		Label label11 = new Label("Площадь");
		Textbox txt = new Textbox();
		txt.setMaxlength(3);
		txt.setWidth("100%");
		txt.setAttribute("cadastre_type","cadastre_type");
		Textbox txt1 = new Textbox();
		txt1.setMaxlength(6);
		txt1.setWidth("100%");
		txt1.setAttribute("cost","cost");
		Textbox txt2 = new Textbox();
		txt2.setMaxlength(50);
		txt2.setWidth("100%");
		txt2.setAttribute("certificate_num","certificate_num");
		Textbox txt3 = new Textbox();
		txt3.setMaxlength(50);
		txt3.setWidth("100%");
		txt3.setAttribute("cadastre_num","cadastre_num");
		Textbox txt4 = new Textbox();
		txt4.setMaxlength(20);
		txt4.setWidth("100%");
		txt4.setAttribute("reyestr_num","reyestr_num");
		Textbox txt5 = new Textbox();
		txt5.setMaxlength(6);
		txt5.setWidth("100%");
		txt5.setAttribute("square","square");
		Textbox txt6 = new Textbox();
		txt6.setMaxlength(10);
		txt6.setWidth("100%");
		txt6.setAttribute("id_nn","id_nn");
		Textbox txt7 = new Textbox();
		txt7.setMaxlength(20);
		txt7.setWidth("100%");
		txt7.setAttribute("block_name","block_name");
		Textbox txt8 = new Textbox();
		txt8.setMaxlength(100);
		txt8.setWidth("100%");
		txt8.setAttribute("description","description");
		Textbox txt9 = new Textbox();
		txt9.setMaxlength(6);
		txt9.setWidth("100%");
		txt9.setAttribute("ld_square","ld_square");
		Datebox dbox = new Datebox();
		dbox.setFormat("dd.MM.yyyy");
		dbox.setMaxlength(10);
		dbox.setWidth("100%");
		RefCBox rbox = new RefCBox();
		rbox.setWidth("100%");
		rbox.setModel(new ListModelList(com.is.bpri.LdGuarrService.getOvnership(alias)));
		row.appendChild(label);
		row.appendChild(txt);
		row.appendChild(label1);
		row.appendChild(txt1);
		row1.appendChild(label2);
		row1.appendChild(txt2);
		row1.appendChild(label3);
		row1.appendChild(dbox);
		row2.appendChild(label4);
		row2.appendChild(txt3);
		row2.appendChild(label5);
		row2.appendChild(txt4);
		row3.appendChild(label6);
		row3.appendChild(txt5);
		row3.appendChild(label7);
		row3.appendChild(rbox);
		row4.appendChild(label8);
		row4.appendChild(txt6);
		row4.appendChild(label9);
		row4.appendChild(txt7);
		row5.appendChild(label10);
		row5.appendChild(txt8);
		row5.appendChild(label11);
		row5.appendChild(txt9);
		rows.appendChild(row);
		rows.appendChild(row1);
		rows.appendChild(row2);
		rows.appendChild(row3);
		rows.appendChild(row4);
		rows.appendChild(row5);
		grd.appendChild(rows);
		return grd;
	}
	
	private void createTabs(){
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
	}
	
	public void onClick$btn_saving(){
		try {
			if(current==null){
				current = new LdGuarr();
			}
			current.setNiki_res1(niki_res1.getValue());
			current.setInsc_name(insc_name.getValue());
			current.setNotarial_office_num(notarial_office_num.getValue());
			current.setCadastr_org_region(cadastr_org_region.getValue());
			current.setCadastr_org_distr(cadastr_org_distr.getValue());
			current.setNotarial_doc_numb(notarial_doc_numb.getValue());
			current.setInsc_inn(insc_inn.getValue());
			current.setPolis_num(polis_num.getValue());
			current.setSer_eval_company(ser_eval_company.getValue());
			current.setLis_num(lis_num.getValue());
			current.setEval_report_num(eval_report_num.getValue());
			current.setEconomical_zone(economical_zone.getValue());
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
			current.setDate_operation(date_operation.getValue());
			current.setLis_date(lis_date.getValue());
			current.setInsc_date(insc_date.getValue());
			current.setInsc_date_cl(insc_date_cl.getValue());
			current.setPolis_date(polis_date.getValue());
			current.setDoc_num(doc_num.getValue());
			current.setDoc_date(doc_date.getValue());
			current.setCode_subject(code_subject.getValue());
			current.setInn_reestr(inn_reestr.getValue());
			current.setName2(name2.getValue());
			current.setAcomp_name(acomp_name.getValue());
			current.setAcomp_date(acomp_date.getValue());
			current.setAcomp_curr(acomp_curr.getValue());
			if(acomp_summa.getValue()!=null){
				current.setAcomp_summa(Integer.parseInt(acomp_summa.getValue()+""));
			}
			Res res = new Res();
			List<RealestateCadastr> list = new ArrayList<RealestateCadastr>();
			if(clickbtn.equals("add")){
				for (int i = 0; i < tabbox.getTabsApi().getChildren().size()-1; i++) {
					list.add(getCadastre(i));
				}
				RealestateService.create(current,list,alias,res);
			} else if (clickbtn.equals("double")){
				for (int i = 0; i < tabbox.getTabsApi().getChildren().size()-1; i++) {
					list.add(getCadastre(i));
				}
				RealestateService.update(current,cadastrlist,list,alias,res);
			}
			if(res.getCode()!=1){
				alert("Данные успешно сохраненны");
				refreshModel(0);
				btn_saving.setDisabled(true);
				onClick$btn_cancel();
				current = new LdGuarr();
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
	private RealestateCadastr getCadastre(int i){
		RealestateCadastr cadastr = new RealestateCadastr();
		tabbox.setSelectedIndex(i);
		Tabpanel tabpanel = tabbox.getSelectedPanel();
		Grid grid = (Grid)tabpanel.getChildren().get(0);
		cadastr.setBool(Utils.isEmpty(grid));
		Utils.setAllsize(0);
		Utils.setSize(0);
		Rows rows = grid.getRows();
		List<Row> row = rows.getChildren();
		for (int j = 0; j < row.size(); j++) {
			for (int j2 = 0; j2 < row.get(j).getChildren().size(); j2++) {
				if(row.get(j).getChildren().get(j2) instanceof Textbox){
					Textbox txt = (Textbox) row.get(j).getChildren().get(j2);
					if(txt.getAttribute("ld_square")!=null&&txt.getAttribute("ld_square").equals("ld_square")){
						cadastr.setLd_square(txt.getValue());
					} else if(txt.getAttribute("description")!=null&&txt.getAttribute("description").equals("description")){
						cadastr.setDescription(txt.getValue());
					} else if(txt.getAttribute("block_name")!=null&&txt.getAttribute("block_name").equals("block_name")){
						cadastr.setBlock_name(txt.getValue());
					} else if(txt.getAttribute("id_nn")!=null&&txt.getAttribute("id_nn").equals("id_nn")){
						cadastr.setId_nn(txt.getValue());
					} else if(txt.getAttribute("square")!=null&&txt.getAttribute("square").equals("square")){
						cadastr.setSquare(txt.getValue());
					} else if(txt.getAttribute("reyestr_num")!=null&&txt.getAttribute("reyestr_num").equals("reyestr_num")){
						cadastr.setReyestr_num(txt.getValue());
					} else if(txt.getAttribute("cadastre_num")!=null&&txt.getAttribute("cadastre_num").equals("cadastre_num")){
						cadastr.setCadastre_num(txt.getValue());
					} else if(txt.getAttribute("certificate_num")!=null&&txt.getAttribute("certificate_num").equals("certificate_num")){
						cadastr.setCertificate_num(txt.getValue());
					} else if(txt.getAttribute("cadastre_type")!=null&&txt.getAttribute("cadastre_type").equals("cadastre_type")){
						cadastr.setCadastre_type(txt.getValue());
					} else if(txt.getAttribute("cost")!=null&&txt.getAttribute("cost").equals("cost")){
						cadastr.setCost(txt.getValue());
					}
				} else if(row.get(j).getChildren().get(j2) instanceof Datebox){
					Datebox dbox = (Datebox) row.get(j).getChildren().get(j2);
					cadastr.setCertificate_date(dbox.getValue());
				} else if(row.get(j).getChildren().get(j2) instanceof RefCBox){
					RefCBox rbox = (RefCBox) row.get(j).getChildren().get(j2);
					cadastr.setOvnership(rbox.getValue());
				}
			}
		}
		cadastr.setBpr_id(current.getBpr_id()+"");
		return cadastr;
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
		Grid grid = (Grid)tabpanel.getChildren().get(0);
		Rows rows = grid.getRows();
		List<Row> row = rows.getChildren();
		for (int j = 0; j < row.size(); j++) {
			for (int j2 = 0; j2 < row.get(j).getChildren().size(); j2++) {
				if(row.get(j).getChildren().get(j2) instanceof Textbox){
					Textbox txt = (Textbox) row.get(j).getChildren().get(j2);
					if(txt.getAttribute("ld_square")!=null&&txt.getAttribute("ld_square").equals("ld_square")){
						txt.setValue(cadastrlist.get(i).getLd_square());
					} else if(txt.getAttribute("description")!=null&&txt.getAttribute("description").equals("description")){
						txt.setValue(cadastrlist.get(i).getDescription());
					} else if(txt.getAttribute("block_name")!=null&&txt.getAttribute("block_name").equals("block_name")){
						txt.setValue(cadastrlist.get(i).getBlock_name());
					} else if(txt.getAttribute("id_nn")!=null&&txt.getAttribute("id_nn").equals("id_nn")){
						txt.setValue(cadastrlist.get(i).getId_nn());
					} else if(txt.getAttribute("square")!=null&&txt.getAttribute("square").equals("square")){
						txt.setValue(cadastrlist.get(i).getSquare());
					} else if(txt.getAttribute("reyestr_num")!=null&&txt.getAttribute("reyestr_num").equals("reyestr_num")){
						txt.setValue(cadastrlist.get(i).getReyestr_num());
					} else if(txt.getAttribute("cadastre_num")!=null&&txt.getAttribute("cadastre_num").equals("cadastre_num")){
						txt.setValue(cadastrlist.get(i).getCadastre_num());
					} else if(txt.getAttribute("certificate_num")!=null&&txt.getAttribute("certificate_num").equals("certificate_num")){
						txt.setValue(cadastrlist.get(i).getCertificate_num());
					} else if(txt.getAttribute("cadastre_type")!=null&&txt.getAttribute("cadastre_type").equals("cadastre_type")){
						txt.setValue(cadastrlist.get(i).getCadastre_type());
					} else if(txt.getAttribute("cost")!=null&&txt.getAttribute("cost").equals("cost")){
						txt.setValue(cadastrlist.get(i).getCost());
					}
				} else if(row.get(j).getChildren().get(j2) instanceof Datebox){
					Datebox dbox = (Datebox) row.get(j).getChildren().get(j2);
					dbox.setValue(cadastrlist.get(i).getCertificate_date());
				} else if(row.get(j).getChildren().get(j2) instanceof RefCBox){
					RefCBox rbox = (RefCBox) row.get(j).getChildren().get(j2);
					rbox.setSelecteditem(cadastrlist.get(i).getOvnership());
				}
			}
		}
	}
	
	public void onClick$btn_del(){
		Res res = new Res();
		RealestateService.remove(current,cadastrlist,alias,res);
		if(res.getCode()!=1){
			alert("Данные успешно удалены");
			refreshModel(0);
			onClick$btn_cancel();
		} else {
			alert(res.getName());
		}
	}
	
	public void onClick$btn_cancel(){
		grd.setVisible(true);
		addgrdl.setVisible(false);
		realestatediv.setVisible(false);
		btn_back.setImage("/images/file.png");
		btn_back.setLabel(Labels.getLabel("back"));
	}
	
	public void onChange$id_client(){
		List<RefData> list = com.is.bpri.LdGuarrService.getClientName(id_client.getValue(), branch, alias);
		if(!list.isEmpty()){
			name.setValue(list.get(0).getLabel());
			code_subject.setSelecteditem(list.get(0).getData());
		}
	}
	
	public void onInitRenderLater$cadastr_org_region(){
		if(clickbtn.equals("double")){
			cadastr_org_region.setSelecteditem(current.getCadastr_org_region());
			onSelect$cadastr_org_region();
		}
	}
	
	public void onInitRenderLater$cadastr_org_distr(){
		if(clickbtn.equals("double")){
			cadastr_org_distr.setSelecteditem(current.getCadastr_org_distr());
		}
	}
	
	public void onInitRenderLater$insc_name(){
		if(clickbtn.equals("double")){
			insc_name.setSelecteditem(current.getInsc_name());
		}
	}
	
	public void onInitRenderLater$notarial_office_num(){
		if(clickbtn.equals("double")){
			notarial_office_num.setSelecteditem(current.getNotarial_office_num());
		}
	}
	
	public void onInitRenderLater$niki_res1(){
		if(clickbtn.equals("double")){
			niki_res1.setSelecteditem(current.getNiki_res1());
		}
	}
	
	public void onInitRenderLater$region_id(){
		if(clickbtn.equals("double")){
			region_id.setSelecteditem(current.getRegion_id());
			onSelect$region_id();
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
	
	private void refCBoxModel(){
		region_id.setModel(new ListModelList(com.is.bpri.LdGuarrService.getRegion(alias)));
		currency.setModel(new ListModelList(com.is.bpri.LdGuarrService.getCurrency(alias)));
		klass_o.setModel(new ListModelList(com.is.bpri.LdGuarrService.getKlass_o(alias)));
		code_subject.setModel(new ListModelList(com.is.bpri.LdGuarrService.getTypeClient(alias)));
		cadastr_org_region.setModel(new ListModelList(com.is.bpri.LdGuarrService.getRegion(alias)));
		niki_res1.setModel(new ListModelList(com.is.bpri.LdGuarrService.getResident(alias)));
		notarial_office_num.setModel(new ListModelList(com.is.bpri.LdGuarrService.getNotarialNumb(alias)));
		insc_name.setModel(new ListModelList(com.is.bpri.LdGuarrService.getInscName(alias)));
		acomp_name.setModel(new ListModelList(com.is.bpri.LdGuarrService.getNameCompany(alias)));
		acomp_curr.setModel((new ListModelList(com.is.bpri.LdGuarrService.getCurrency(alias))));
	}
	
	public void onSelect$region_id(){
		if(!region_id.getValue().equals("")){
			distr_id.setModel(new ListModelList(com.is.bpri.LdGuarrService.getDistr(alias, region_id.getValue())));
		}
	}
	
	public void onSelect$cadastr_org_region(){
		if(!cadastr_org_region.getValue().equals("")){
			cadastr_org_distr.setModel(new ListModelList(com.is.bpri.LdGuarrService.getDistr(alias, cadastr_org_region.getValue())));
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
}
