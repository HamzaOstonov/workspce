package com.is.bpri.scoring;

import java.text.SimpleDateFormat;
import java.util.List;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Decimalbox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbarbutton;

import com.is.ISLogger;
import com.is.bpri.utils.Utils;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.Res;

@SuppressWarnings("serial")
public class ScoringViewControll extends GenericForwardComposer{
	
	private Toolbarbutton btn_add,btn_save;
	private Div grd,scorediv;
	private Rows scoreRows;
	private Listbox dataGrid;
	private AnnotateDataBinder binder;
	private Scoring current = new Scoring();
	private SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	private String alias;
	private int bpr_type = 1,template = -1,scoring_type = 0;
	private List<Scoring> params = null;
	private List<Scoring> insParams = null;
	private com.is.bpri.scoring.PagingListModel model = null;
	private int state = -1;
	private int btnclick = -1; // если 1 то нажата кнопка add если 2 то onDoubleClick
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		binder = new AnnotateDataBinder(comp);
		binder.bindBean("current", this.current);
		binder.loadAll();
		alias = (String) session.getAttribute("alias");
		if (session.getAttribute("bpr_id") != null) {
			template = Integer.parseInt(session.getAttribute("bpr_id")+"");
		}
		if (session.getAttribute("bpr_state") != null) {
			state = Integer.parseInt(session.getAttribute("bpr_state")+"");
		}
		if(session.getAttribute("scoring_type") != null) {
			scoring_type = Integer.parseInt(session.getAttribute("scoring_type")+"");
		}
		listboxrender();
		if(state==1||state==2){
			btn_add.setVisible(false);
			btn_save.setVisible(false);
		} else if (state==0){
			btn_add.setVisible(true);
			btn_save.setVisible(true);
		}
	}
	
	public void onClick$btn_add(){
		btnclick = 1;
		grd.setVisible(false);
		createScoringGrd(bpr_type);
		scorediv.setVisible(true);
	}
	
	private void listboxrender(){
		try {
			dataGrid.setItemRenderer(new ListitemRenderer() {
				
				@Override
				public void render(Listitem item, Object obj) throws Exception {
					try {
						Scoring score = (Scoring) obj;
						item.setValue(score);
						item.addEventListener(Events.ON_CLICK, new EventListener() {
							
							@SuppressWarnings("unchecked")
							@Override
							public void onEvent(Event evt) throws Exception {
								Listitem listitem = dataGrid.getSelectedItem();
								List<Listcell> listcell = listitem.getChildren();
								if(current==null){
									current = new Scoring();
								}
								current.setId(Integer.parseInt(listcell.get(0).getLabel()));
								current.setBpr_id(listcell.get(1).getLabel());
								current.setDef_value(listcell.get(2).getLabel());
								current.setName_ru(listcell.get(3).getLabel());
							}
						});
						item.addEventListener(Events.ON_DOUBLE_CLICK, new EventListener() {
							
							@Override
							public void onEvent(Event evt) throws Exception {
								btnclick = 2;
								createScoringGrd(bpr_type);
								grd.setVisible(false);
								scorediv.setVisible(true);
							}
						});
						item.appendChild(new Listcell(score.getId()+""));
						item.appendChild(new Listcell(score.getBpr_id()));
						item.appendChild(new Listcell(score.getDef_value()));
						item.appendChild(new Listcell(score.getName_ru()));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			model = new com.is.bpri.scoring.PagingListModel(template, 1, null, alias);
			dataGrid.setModel((ListModel) model);
			if(model.getSize()>0){
				btn_add.setVisible(false);
				grd.setVisible(true);
				dataGrid.setSelectedIndex(0);
				sendSelEvt();
			} else {
				btn_add.setVisible(true);
				grd.setVisible(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void sendSelEvt() {
		try {
			SelectEvent evt = new SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
			Events.sendEvent(evt);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void onClick$btn_cancel(){
		listboxrender();
		scorediv.setVisible(false);
		grd.setVisible(true);
	}
	
	@SuppressWarnings("unchecked")
	public void onClick$btn_save(){
		List<Row> list = scoreRows.getChildren();
		for (int i = 0; i < list.size(); i++) {
			List<?> tempcomp = list.get(i).getChildren();
			for (int j = 0; j < tempcomp.size(); j++) {
				if(tempcomp.get(j) instanceof RefCBox){
					RefCBox rbox = (RefCBox) tempcomp.get(j);
					for (int k = 0; k < insParams.size(); k++) {
						if(insParams.get(k).getName().equals(rbox.getAttribute("name"))){
							insParams.get(k).setBpr_id(template+"");
							insParams.get(k).setDef_value(rbox.getValue());
						}
					}
				} else if(tempcomp.get(j) instanceof Decimalbox){
					Decimalbox d = (Decimalbox) tempcomp.get(j);
					for (int k = 0; k < insParams.size(); k++) {
						if(insParams.get(k).getName().equals(d.getAttribute("name"))){
							insParams.get(k).setBpr_id(template+"");
							insParams.get(k).setDef_value(d.getValue()+"");
						}
					}
				} else if(tempcomp.get(j) instanceof Textbox){
					Textbox txt = (Textbox) tempcomp.get(j);
					for (int k = 0; k < insParams.size(); k++) {
						if(insParams.get(k).getName().equals(txt.getAttribute("name"))){
							insParams.get(k).setBpr_id(template+"");
							insParams.get(k).setDef_value(txt.getValue());
						}
					}
				}
			}
		}
		Res res = new Res();
		if(btnclick==1){
			ScoringService.create(insParams, template, scoring_type, alias, res);
		} else if(btnclick==2){
			ScoringService.update(insParams, template, scoring_type, alias, res);
		}
		if(res.getCode()!=1){
			scorediv.setVisible(false);
			listboxrender();
			grd.setVisible(true);
		} else {
			alert(res.getName());
		}
	}
	
	private Row getRow(Component label,Component input,Component label1,Component input1){
		Row row = new Row();
		if(label!=null){
			row.appendChild(label);
		} if(input!=null){
			row.appendChild(input);
		} if(label1!=null){
			row.appendChild(label1);
		} if(input1!=null){
			row.appendChild(input1);
		}
		return row;
	}
	
	private Label getLabel(String value){
		Label label = new Label();
		label.setValue(value);
		return label;
	}
	
	private void initRenderLater(final RefCBox rbox,final String value){
		rbox.addEventListener("onInitRenderLater", new EventListener() {
			
			@Override
			public void onEvent(Event evt) throws Exception {
				rbox.setSelecteditem(value);
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	private void createScoringGrd(int bpr_type){
		try {
			List<Row> listrow = scoreRows.getChildren();
			int size = listrow.size()-1;
			for (int i = size; i >= 0; i--) {
				scoreRows.removeChild(listrow.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
			alert(CheckNull.getPstr(e));
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
		RefCBox rbox1 = null;
		RefCBox rbox2 = null;
		Decimalbox d1 = null;
		Decimalbox d2 = null;
		Textbox t1 = null;
		Textbox t2 = null;
		String lr1 = null;
		String lr2 = null;
		String ld1 = null;
		String ld2 = null;
		String lt1 = null;
		String lt2 = null;
		List<Scoring> scoreList = null;
		if(btnclick==2){
			scoreList = ScoringService.getModel(template, alias);
		}
		try {
			params = ScoringService.getParams(bpr_type, alias);
			insParams = params;
			if(params.size()>0){
				for (int i = 0; i < params.size(); i++) {
					if(params.get(i).getParam_type().equals("INPUT")){
						if(params.get(i).getType().equalsIgnoreCase("rbox")){
							if(rbox1==null){
								rbox1 = new RefCBox();
								rbox1.setWidth("100%");
								if(params.get(i).getMandatory().equals("0")){
									rbox1.setReadonly(true);
									rbox1.setButtonVisible(false);
									rbox1.setAttribute("formula", params.get(i).getSelects());
									rbox1.setAttribute("name", params.get(i).getName());
								}
								rbox1.setAttribute("order", params.get(i).getOrd());
								rbox1.setModel(new ListModelList(ScoringService.getList(params.get(i).getSelects(),alias)));
								if(btnclick==2){
									String value = "";
									for (int j = 0; j < scoreList.size(); j++) {
										if(scoreList.get(j).getId()==params.get(i).getId()){
											value = scoreList.get(i).getDef_value();
										}
									}
									initRenderLater(rbox1, value);
								}
								lr1 = params.get(i).getName_ru();
							} else {
								rbox2 = new RefCBox();
								if(params.get(i).getMandatory().equals("0")){
									rbox2.setReadonly(true);
									rbox2.setButtonVisible(false);
									rbox2.setAttribute("formula", params.get(i).getSelects());
									rbox2.setAttribute("name", params.get(i).getName());
								}
								rbox2.setAttribute("order", params.get(i).getOrd());
								rbox2.setWidth("100%");
								rbox2.setModel(new ListModelList(ScoringService.getList(params.get(i).getSelects(),alias)));
								if(btnclick==2){
									String value = "";
									for (int j = 0; j < scoreList.size(); j++) {
										if(scoreList.get(j).getId()==params.get(i).getId()){
											value = scoreList.get(i).getDef_value();
										}
									}
									initRenderLater(rbox2, value);
								}
								lr2 = params.get(i).getName_ru();
							}
						} else {
							if(params.get(i).getType().equalsIgnoreCase("number")){
								if(d1==null){
									d1 = new Decimalbox();
									if(params.get(i).getMandatory().equals("0")){
										d1.setReadonly(true);
										d1.setAttribute("formula", params.get(i).getSelects());
									}
									d1.setAttribute("order", params.get(i).getOrd());
									d1.setWidth("100%");
									d1.setAttribute("name", params.get(i).getName());
									if(btnclick==2){
										String value = "";
										for (int j = 0; j < scoreList.size(); j++) {
											if(scoreList.get(j).getId()==params.get(i).getId()){
												value = scoreList.get(i).getDef_value();
											}
										}
										if(value==null||value.equals("")) value = "0";
										d1.setValue(value);
									}
									 
									ld1 = params.get(i).getName_ru();
								} else {
									d2 = new Decimalbox();
									if(params.get(i).getMandatory().equals("0")){
										d2.setReadonly(true);
										d2.setAttribute("formula", params.get(i).getSelects());
									}
									d2.setAttribute("order", params.get(i).getOrd());
									d2.setWidth("100%");
									d2.setAttribute("name", params.get(i).getName());
									ld2 = params.get(i).getName_ru();
									if(btnclick==2){
										String value = "";
										for (int j = 0; j < scoreList.size(); j++) {
											if(scoreList.get(j).getId()==params.get(i).getId()){
												value = scoreList.get(i).getDef_value();
											}
										}
										if(value==null||value.equals("")) value = "0";
										d2.setValue(value);
									}
								}
							} else if(params.get(i).getType().equalsIgnoreCase("string")){
								if(t1==null){
									t1 = new Textbox();
									if(params.get(i).getMandatory().equals("0")){
										t1.setReadonly(true);
										t1.setAttribute("formula", params.get(i).getSelects());
									}
									t1.setAttribute("order", params.get(i).getOrd());
									t1.setWidth("100%");
									t1.setAttribute("name", params.get(i).getName());
									String tip = "";
									if(params.get(i).getOrd().equals("7")){
										tip = " Формат ввода ххххх-ххх,ххххх";
									}
									lt1 = params.get(i).getName_ru()+tip;
									if(btnclick==2){
										String value = "";
										for (int j = 0; j < scoreList.size(); j++) {
											if(scoreList.get(j).getId()==params.get(i).getId()){
												value = scoreList.get(i).getDef_value();
											}
										}
										t1.setValue(value);
									}
								} else {
									t2 = new Textbox();
									if(params.get(i).getMandatory().equals("0")){
										t2.setReadonly(true);
										t2.setAttribute("formula", params.get(i).getSelects());
									}
									t2.setAttribute("order", params.get(i).getOrd());
									t2.setWidth("100%");
									t2.setAttribute("name", params.get(i).getName());
									String tip = "";
									if(params.get(i).getOrd().equals("7")){
										tip = " Формат ввода ххххх-ххх,ххххх";
									}
									lt2 = params.get(i).getName_ru()+tip;
									if(btnclick==2){
										String value = "";
										for (int j = 0; j < scoreList.size(); j++) {
											if(scoreList.get(j).getId()==params.get(i).getId()){
												value = scoreList.get(i).getDef_value();
											}
										}
										t2.setValue(value);
									}
								}
							}
						}
						if(i>0){
							int index = i+1;
							if(index%2==0||params.size()-1==i){
								String label1 = null;
								String label2 = null;
								Component comp1 = null;
								Component comp2 = null;
								if(lr1!=null){
									label1 = lr1;
									if(comp1==null){
										comp1 = rbox1;
									} 
								} if(ld1!=null){
									if(comp1==null){
										comp1 = d1;
										label1 = ld1;
									} else {
										comp2 = d1;
										label2 = ld1;
									}
								} if(lt1!=null){
									if(comp1==null){
										comp1 = t1;
										label1 = lt1;
									} else {
										comp2 = t1;
										label2 = lt1;
									}
								} 
								if(lr2!=null){
									if(comp1==null){
										comp1 = rbox2;
										label1 = lr2;
									} else {
										comp2 = rbox2;
										label2 = lr2;
									}
								} if(ld2!=null){
									if(comp1==null){
										comp1 = d2;
										label1 = ld2;
									} else {
										comp2 = d2;
										label2 = ld2;
									}
								} if(lt2!=null){
									if(comp1==null){
										comp1 = t2;
										label1 = lt2;
									} else {
										comp2 = t2;
										label2 = lt2;
									}
								}
								scoreRows.appendChild(getRow(getLabel(label1), comp1, getLabel(label2), comp2));
								if(rbox1!=null){
									rbox1 = null;
									lr1 = null;
								} if(rbox2!=null){
									rbox2 = null;
									lr2 = null;
								} if(d1!=null){
									d1 = null;
									ld1 = null;
								} if(d2!=null){
									d2 = null;
									ld2 = null;
								} if(t1!=null){
									t1 = null;
									lt1 = null;
								} if(t2!=null){
									t2 = null;
									lt2 = null;
								}
							}
						}
					} else if(params.get(i).getParam_type().equals("PARAM")){
						if(params.get(i).getType().equalsIgnoreCase("DATE")){
							Res res = new Res();
							scoreRows.setAttribute("dateValue", df.format(Utils.getInfoDate(alias, res)));
							scoreRows.setAttribute("dateOrder", params.get(i).getOrd());
						}
					}
				}
			} else {
				alert("Не найдены параметры для Скоринга");
			}
		} catch (Exception e) {
			e.printStackTrace();
			alert(CheckNull.getPstr(e));
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
	}
	
}
