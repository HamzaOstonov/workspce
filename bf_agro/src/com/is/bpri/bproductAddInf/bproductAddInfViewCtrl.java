package com.is.bpri.bproductAddInf;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.zkoss.util.resource.Labels;
import org.zkoss.web.Attributes;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Caption;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Column;
import org.zkoss.zul.Columns;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Vbox;
import com.is.ISLogger;
import com.is.bpri.bproductAddInf.Parameter;
import com.is.bpri.bproductAddInf.ParameterGroup;
import com.is.bpri.bproductAddInf.bproductAddInfService;
import com.is.bpri.utils.Utils;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.RefData;
import com.is.utils.Res;

@SuppressWarnings("serial")
public class bproductAddInfViewCtrl extends GenericForwardComposer {
	
	private Div frm;
	private Listbox dataGrid;
	private Div grd, frmgrd, addinfo;
	private Grid addgrd, frmgrd1, fgrd;
	private Toolbarbutton btn_back;
	private int click_btn_save = 0;
	private static RefCBox cardbox;
	private String lang, branch1, alias;
	private int template;
	private String cl_id;
	// private Textbox bpr_id, abpr_id;

	public ParameterFilter filter = new ParameterFilter();

	// PagingListModel model = null;
	ListModelList lmodel = null;
	private AnnotateDataBinder binder;
	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");

	// private bproductAddInf current = new bproductAddInf();
	private Parameter current = new Parameter();

	public bproductAddInfViewCtrl() {
		super('$', false, false);

	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		try {
			filter = new ParameterFilter();
			binder = new AnnotateDataBinder(comp);
			binder.bindBean("current", this.current);
			binder.loadAll();
			session.setAttribute("click_btn_save", null);
			lang = (((Locale) session.getAttribute(Attributes.PREFERRED_LOCALE)).getLanguage());
			branch1 = (String) session.getAttribute("branch");
			alias = (String) session.getAttribute("alias");
			cl_id = (String) session.getAttribute("cl_id");
			if (this.param.get("template") != null) {
				template = Integer.parseInt(((String[]) this.param.get("template"))[0]);
			}
			filter.setBpr_type(template);
			if (current != null && (template == 1||template == 4)) {
				createAddInfo();
			} else if (current != null && template == 2) {
				createComboBox();
			}
		} catch (Exception e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
			alert(e.getMessage());
		}

	}

	// Omitted...
	public Parameter getCurrent() {
		return current;
	}

	public void setCurrent(Parameter current) {
		this.current = current;
	}

	public void onDoubleClick$dataGrid$grd() {
		grd.setVisible(false);
		frm.setVisible(true);
		frmgrd.setVisible(true);
		frmgrd1.setVisible(true);
		addgrd.setVisible(false);
		fgrd.setVisible(false);
		btn_back.setImage("/images/folder.png");
		btn_back.setLabel(Labels.getLabel("grid"));
		if (current != null) {
			createAddInfo();
		}
	}

	public void onClick$btn_back() {
		if (frm.isVisible()) {
			frm.setVisible(false);
			grd.setVisible(true);
			frmgrd1.setVisible(true);
			btn_back.setImage("/images/file.png");
			btn_back.setLabel(Labels.getLabel("back"));
		} else
			onDoubleClick$dataGrid$grd();
	}

	public void onClick$btn_first() {
		dataGrid.setSelectedIndex(0);
		createAddInfo();
		// sendSelEvt();
	}

	public void onClick$btn_last() {
		// dataGrid.setSelectedIndex(model.getSize()-1);
		createAddInfo();
		// sendSelEvt();
	}

	public void onClick$btn_prev() {
		if (dataGrid.getSelectedIndex() != 0) {
			dataGrid.setSelectedIndex(dataGrid.getSelectedIndex() - 1);
			createAddInfo();
			// sendSelEvt();
		}
	}

	public void onClick$btn_next() {
		/*
		 * if (dataGrid.getSelectedIndex()!=(model.getSize()-1)){
		 * dataGrid.setSelectedIndex(dataGrid.getSelectedIndex()+1);
		 */
		createAddInfo();
		// sendSelEvt();
	}

	/*
	 * private void sendSelEvt(){ if (dataGrid.getSelectedIndex()==0){
	 * btn_first.setDisabled(true); btn_prev.setDisabled(true); }else{
	 * btn_first.setDisabled(false); btn_prev.setDisabled(false); }
	 * if(dataGrid.getSelectedIndex()==(model.getSize()-1)){
	 * btn_next.setDisabled(true); btn_last.setDisabled(true); }else{
	 * btn_next.setDisabled(false); btn_last.setDisabled(false); } SelectEvent
	 * evt = new SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
	 * 
	 * Events.sendEvent(evt); }
	 */

	public void onClick$btn_add() {
		onDoubleClick$dataGrid$grd();
		frmgrd.setVisible(false);
		addgrd.setVisible(true);
		fgrd.setVisible(false);
	}

	public void onClick$btn_search() {
		onDoubleClick$dataGrid$grd();
		frmgrd1.setVisible(false);
		addgrd.setVisible(false);
		fgrd.setVisible(true);
	}

	public void onClick$btn_cancel() {
//		if (fgrd.isVisible()) {
//			filter = new ParameterFilter();
//		}
		onClick$btn_back();
		frmgrd.setVisible(true);
		addgrd.setVisible(false);
		fgrd.setVisible(false);
		Utils.clearForm(addgrd);
		Utils.clearForm(fgrd);
		// refreshModel(_startPageNumber);
	}

	private List<ParameterGroup> parametergroups = new ArrayList<ParameterGroup>();
	private List<Parameter> parameters = new ArrayList<Parameter>();
	private List<Parameter> paramForSelect = new ArrayList<Parameter>();

	String client_code = "";

	public void createAddInfo() {
		if (current == null) {
			current = (Parameter) dataGrid.getSelectedItem().getValue();
		}
		if (current == null) {
			return;
		}
		// System.out.println("getLd_type "+current.getLd_type()+ " "
		// +current.getId());
		addinfo.getChildren().clear();
		parametergroups = bproductAddInfService.getParametergroup(template);
		parameters = bproductAddInfService.getParameters(template);
		paramForSelect = bproductAddInfService.getParameters(template);
		// client_code = bproductAddInfService.getClient_code(alias);
		// System.out.println(parametergroups.size()+" ... "+parameters.size());
		try {
			for (ParameterGroup parametergroup : parametergroups) {
				List<Parameter> params = bproductAddInfService
						.getParametersByGroup(parameters, parametergroup);
				System.out
						.println("select: " + parametergroups.size() + " ... "
								+ parameters.size() + " ... " + params.size());
				System.out.println("!!! "+params.size());
				if (params.size() > 0) {
					Groupbox g = new Groupbox();
					g.setMold("3d");
					g.setOpen(parametergroup.getIs_open() == 1);
					g.setClosable(true);
					g.setWidth("100%");
					Caption caption = new Caption(
							lang.equalsIgnoreCase("en") ? parametergroup
									.getName_en()
									: (lang.equalsIgnoreCase("uz") ? parametergroup
											.getName_uz() : parametergroup
											.getName_ru()));
					g.appendChild(caption);
					Grid grid = new Grid();
					grid.setId("grid_" + parametergroup.getId());
					grid.setWidth("100%");
					Columns columns = new Columns();
					grid.appendChild(columns);
					// grid.getColumns().getChildren().clear();
					Rows rows = new Rows();
					grid.appendChild(rows);
					Column column = new Column();
					column.setAlign("left");
					column.setWidth("30%");
					grid.getColumns().appendChild(column);
					column = new Column();
					column.setAlign("left");
					column.setWidth("70%");
					grid.getColumns().appendChild(column);

					Vbox vb = new Vbox();
					Label lbl = new Label();
					Datebox dtb = new Datebox();
					cardbox = new RefCBox();
					Checkbox ch = new Checkbox();
					Textbox n = new Textbox();
					Textbox t = new Textbox();
					for (int i = 0; i < params.size(); i++) {
						Row row = new Row();
						row.setAttribute("parameter", params.get(i));
						row.setAttribute("group", parametergroup);
						if (!params.get(i).getParam_type()
								.equalsIgnoreCase("PERSONGRID")) {
							row.appendChild(new Label(lang
									.equalsIgnoreCase("en") ? params.get(i)
									.getParam_name_en() : (lang
									.equalsIgnoreCase("uz") ? params.get(i)
									.getParam_name_uz() : params.get(i)
									.getParam_name_ru())));
							vb = new Vbox();
							vb.setWidth("100%");
							if (params.get(i).getParam_mandatory() == 1) {
								lbl = new Label("* Обязательный параметр");
								lbl.setStyle("font-size: 12px; color: grey;");
								vb.appendChild(lbl);
							}
						}
						if (params.get(i).getParam_type().equals("DATE")) {
							dtb = new Datebox();
							dtb.setId("p_" + params.get(i).getParam_id());
							dtb.setWidth("600px");
							dtb.setAttribute("parameter", params.get(i));
							if (params.get(i).getParam_mandatory() == 1) {
								dtb.setConstraint("no empty, no future:  Заполните обязательный параметр! ");
							}
							if (params.get(i).getParam_value() != null) {
								dtb.setValue(df.parse(params.get(i)
										.getParam_value()));
							}

							dtb.addEventListener(Events.ON_BLUR,
									new EventListener() {
										@Override
										public void onEvent(org.zkoss.zk.ui.event.Event event) throws Exception {
//											Datebox dtbox = (Datebox) event.getTarget();
										}
									});

							vb.appendChild(dtb);
						} else if (params.get(i).getParam_type()
								.equals("COMBOBOX")) {
							String select = params.get(i).getParam_select()
									.toUpperCase();

							/*
							 * if (select.indexOf("INFO.GETBRANCH") != -1){
							 * select =
							 * select.replaceAll("INFO.GETBRANCH","'01066'") ; }
							 */
							// select =
							// bproductAddInfService.setParamsInSQL(parameters,
							// select);
							System.out.println("СЕЛЕКТИК Оо "+select);
							select = bproductAddInfService.setParamsInSQL2(
									paramForSelect, select, branch1, cl_id);
							// System.out.println(rowData.getId()+") "+select);
							cardbox = new RefCBox();
							cardbox.setId("p_" + params.get(i).getParam_id());
							cardbox.setAttribute("parameter", params.get(i));
							cardbox.setAttribute("group", parametergroup);
							cardbox.setWidth("600px");
							cardbox.setMold("rounded");
							cardbox.setReadonly(true);
							if (!CheckNull.isEmpty(select)) {
								// c.setModel(new
								// ListModelList(ReportService.getListForCombobox(rowData.getPar_select())));
								// c.setModel(null);
								Res res = new Res();
								res.setName(branch1);
								ListModelList list = new ListModelList(
										bproductAddInfService
												.getListForCombobox(select,
														alias,res));
								if(list.isEmpty()){
									ISLogger.getLogger().error("ЛИСТ ПУСТ");
								}
								if(res.getCode()==1){
									alert(res.getName());
									return;
								}
								cardbox.setModel(list);
								// list.toArray();

								// System.out.println("*** c.getModel().getSize() = "+c.getModel().getSize()+"---"+c.getItemCount()+"---"+c.getItems().isEmpty());
								cardbox.addEventListener("onInitRenderLater",
										new EventListener() {
											public void onEvent(
													org.zkoss.zk.ui.event.Event event)
													throws Exception {
												RefCBox cc = (RefCBox) event
														.getTarget();
												if (cc.getAttribute("parameter") != null) {
													cc.setSelecteditem(((Parameter) cc
															.getAttribute("parameter"))
															.getParam_value());
												} else {
													for (int i = 0; i < cc
															.getModel()
															.getSize(); i++) {
														RefData rd = (RefData) cc
																.getModel()
																.getElementAt(i);
														if (rd.getData() == null) {
															cc.setSelectedIndex(i);
														}
													}
												}
											}
										});
							} else {
								ISLogger.getLogger().error("Произошла ошибка оО");
								cardbox.setModel(null);
							}
							cardbox.addEventListener(Events.ON_SELECT,
									new EventListener() {
										public void onEvent(
												org.zkoss.zk.ui.event.Event event)
												throws Exception {
											RefCBox cc = new RefCBox();
											cc = (RefCBox) event.getTarget();

											/*
											 * String card_number =
											 * bproductAddInfService
											 * .getCardNumberValue(br, cl_id,
											 * alias);
											 * 
											 * //-------------------------
											 * <Added>
											 * --------------------------
											 * ------------
											 * 
											 * for (ParameterGroup
											 * parametergroup : parametergroups)
											 * { List<Parameter> params =
											 * bproductAddInfService
											 * .getParametersByGroup(parameters,
											 * parametergroup); //if(cc.) for
											 * (int i = 1; i <params.size();
											 * i++) { String select =
											 * params.get(
											 * i).getParam_select().toUpperCase
											 * (); select =
											 * bproductAddInfService
											 * .setParamsInSQLForCard( select,
											 * br, cl_id, card_number);
											 * //if(params
											 * .get(0).getParam_name()
											 * =="CardNumChoice") //{ //-------
											 * added -------------- cc = new
											 * RefCBox();
											 * cc.setId("p_"+params.get
											 * (i).getParam_id());
											 * cc.setAttribute("parameter",
											 * params.get(i));
											 * cc.setAttribute("group",
											 * parametergroup);
											 * cc.setWidth("600px");
											 * cc.setMold("rounded");
											 * cc.setReadonly(true); //-------
											 * added -------------- if
											 * (!CheckNull.isEmpty(select)){
											 * //c.setModel(new
											 * ListModelList(ReportService
											 * .getListForCombobox
											 * (rowData.getPar_select())));
											 * //c.setModel(null); ListModelList
											 * list = new
											 * ListModelList(bproductAddInfService
											 * .
											 * getListForCombobox(select,alias))
											 * ; cc.setModel(list);
											 * 
											 * 
											 * //RefCBox rcb = (RefCBox)
											 * event.getTarget(); if
											 * (cc.getAttribute("parameter") !=
											 * null){
											 * cc.setSelecteditem(((Parameter
											 * )cc.getAttribute("parameter")).
											 * getParam_value()); } else {
											 * for(int
											 * j=i;i<cc.getModel().getSize
											 * ();j++){ RefData rd = (RefData)
											 * cc.getModel().getElementAt(j); if
											 * (rd.getData()==null){
											 * cc.setSelectedIndex(j); } } }
											 * //System.out.println(
											 * "*** c.getModel().getSize() = "
											 * +c.getModel().getSize()+"---"+c.
											 * getItemCount
											 * ()+"---"+c.getItems().isEmpty());
											 * cc
											 * .addEventListener("onInitRender",
											 * new EventListener() { public void
											 * onEvent
											 * (org.zkoss.zk.ui.event.Event
											 * event) throws Exception { RefCBox
											 * cc = (RefCBox) event.getTarget();
											 * if (cc.getAttribute("parameter")
											 * != null){
											 * cc.setSelecteditem(((Parameter
											 * )cc.getAttribute("parameter")).
											 * getParam_value()); } else {
											 * for(int
											 * i=0;i<cc.getModel().getSize
											 * ();i++){ RefData rd = (RefData)
											 * cc.getModel().getElementAt(i); if
											 * (rd.getData()==null){
											 * cc.setSelectedIndex(i); } } } }
											 * }); } //} }
											 * //--------------------------
											 * </Added>
											 * --------------------------
											 * ------------------ }
											 */

											System.out.println(cc.getValue());
											/*
											 * Grid paramsGrid =
											 * (Grid)Path.getComponent
											 * ("//clientmain/grid_"
											 * +((ParameterGroup
											 * )cc.getAttribute(
											 * "group")).getId()); Row row;
											 * Boolean bool = true;
											 * List<Parameter> params =
											 * ParameterService
											 * .getParametersByGroup(parameters,
											 * (ParameterGroup)cc.getAttribute(
											 * "group")); for (int i = rowid-1;
											 * i < params.size(); i++){ row =
											 * (Row)
											 * paramsGrid.getRows().getChildren
											 * ().get(i); List<Row> rws =
											 * paramsGrid
											 * .getRows().getChildren();
											 * if(!params.get(i).getPar_name().
											 * equalsIgnoreCase("REP_START") &&
											 * !params.get(i).getPar_name().
											 * equalsIgnoreCase("REP_EXEC")){ if
											 * (
											 * params.get(i).getPar_type().equals
											 * ("DATE")){ /*Datebox dtb1 =
											 * (Datebox)
											 * row.getChildren().get(1); if
											 * (dtb1 == null)
											 * System.out.println(
											 * dtb1.toString());
											 * //System.out.println
											 * (params.get(i)
											 * .getPar_name()+" = "
											 * +dtb1.getValue().toString());
											 * params
											 * .get(i).setPar_value(df.format
											 * (new
											 * java.sql.Date(dtb1.getValue()
											 * .getTime())).toString());
											 */
											/*
											 * } else if
											 * (params.get(i).getPar_type
											 * ().equals("COMBOBOX")){ RefCBox
											 * c1 = (RefCBox)
											 * row.getChildren().get(1);
											 * //System.out.println(c1.getId()+
											 * ".getSelectedIndex() = "
											 * +c1.getSelectedIndex
											 * ()+"==="+c1.getValue()); if
											 * (c1.getId
											 * ().equals(event.getTarget
											 * ().getId())){ bool = false;
											 * //System
											 * .out.println(params.get(i)
											 * .getPar_name
											 * ()+" = "+c1.getValue());
											 * params.get
											 * (i).setPar_value(c1.getValue());
											 * } else { if (bool){
											 * params.get(i).
											 * setPar_value(c1.getValue()); }
											 * else {
											 * params.get(i).setPar_value(null);
											 * ListModelList list = new
											 * ListModelList
											 * (ReportService.getListForCombobox
											 * (
											 * ReportService.setParamsInSQL(params
											 * , params.get(i).getPar_select().
											 * toUpperCase()),alias));
											 * c1.setModel(list); } } } else if
											 * (
											 * params.get(i).getPar_type().equals
											 * ("NUMBER")){ //Intbox n1 =
											 * (Intbox)
											 * row.getChildren().get(1);
											 * /*Textbox n1 = (Textbox)
											 * row.getChildren().get(1);
											 * //System
											 * .out.println(params.get(i)
											 * .getPar_name
											 * ()+" = "+n1.getValue()
											 * .toString());
											 * params.get(i).setPar_value
											 * (n1.getValue().toString());
											 */
											/*
											 * } else {//STRING /*Textbox t1 =
											 * (Textbox)
											 * row.getChildren().get(1);
											 * //System
											 * .out.println(params.get(i)
											 * .getPar_name
											 * ()+" = "+t1.getValue()
											 * .toString());
											 * params.get(i).setPar_value
											 * (t1.getValue().toString());
											 */
											/*
											 * } } }
											 * //repParamsGrid.setModel(new
											 * ListModelList());
											 * //repParamsGrid.setModel(new
											 * ListModelList(params));
											 */
										}
									});
							vb.appendChild(cardbox);
							System.out.println("Нашелся комбик");
							// row.appendChild(c);

						} else if (params.get(i).getParam_type().equals("CHECKBOX")) {
							ch = new Checkbox();
							ch.setId("p_" + params.get(i).getParam_id());
							if (!CheckNull.isEmpty(params.get(i)
									.getParam_value())) {
								ch.setChecked(params.get(i).getParam_value()
										.equalsIgnoreCase("1"));
							} else {
								ch.setChecked(false);
							}
							vb.appendChild(ch);
						} else if (params.get(i).getParam_type().equals("NUMBER")) {
							n = new Textbox();// new Intbox();
							n.setId("p_" + params.get(i).getParam_id());
							n.setWidth("600px");
							// n.setValue(value);

							if (params.get(i).getParam_value() != null) {
								// n.setValue(Integer.parseInt(rowData.getPar_value()));
								n.setValue(params.get(i).getParam_value());// bproductAddInfService.getParamValue(params,
																			// "GRID"));
							}
							n.setConstraint("/^[\\.\\%\\_0-9]+$/:Must be a number");// /%_[0-9]*/
							vb.appendChild(n);
							// row.appendChild(n);
						} else {// STRING
							t = new Textbox();
							t.setId("p_" + params.get(i).getParam_id());
							t.setWidth("600px");
							if (params.get(i).getParam_value() != null) {
								t.setValue(params.get(i).getParam_value());
							}
							vb.appendChild(t);
							// row.appendChild(t);
						}
						if (!params.get(i).getParam_type().equals("PERSONGRID")) {
							row.appendChild(vb);
						}
						grid.getRows().appendChild(row);
					}
					g.appendChild(grid);
					addinfo.appendChild(g);
				}
			}
		} catch (Exception e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
			alert(CheckNull.getPstr(e));
		}

	}

	public Boolean saveAddInfo() {
		Boolean res = false;
		try {
			List<Parameter> params = parameters;
			if (parameters.size() > 0) {
				Datebox dtb = new Datebox();
				Combobox c = new Combobox();
				Checkbox ch = new Checkbox();
				Textbox n = new Textbox();
				Textbox t = new Textbox();
				for (int i = 0; i < params.size(); i++) {
					if (params.get(i).getParam_type().equals("DATE")) {
						dtb = (Datebox) addinfo.getFellow("p_"
								+ params.get(i).getParam_id());
						if (params.get(i).getParam_mandatory() == 1
								&& CheckNull.isEmpty(dtb.getValue())) {
							alert("Не заполнен обязательный параметр: "
									+ (lang.equalsIgnoreCase("en") ? params
											.get(i).getParam_name_en() : (lang
											.equalsIgnoreCase("uz") ? params
											.get(i).getParam_name_uz() : params
											.get(i).getParam_name_ru())));
							return false;
						}
						params.get(i).setParam_value(
								CheckNull.isEmpty(dtb.getValue()) ? null : df
										.format(dtb.getValue()));
					} else if (params.get(i).getParam_type().equals("COMBOBOX")) {
						c = (RefCBox) addinfo.getFellow("p_"
								+ params.get(i).getParam_id());
						if (params.get(i).getParam_mandatory() == 1
								&& CheckNull.isEmpty(c.getValue())) {
							alert("Не заполнен обязательный параметр: "
									+ (lang.equalsIgnoreCase("en") ? params
											.get(i).getParam_name_en() : (lang
											.equalsIgnoreCase("uz") ? params
											.get(i).getParam_name_uz() : params
											.get(i).getParam_name_ru())));
							return false;
						}
						params.get(i).setParam_value(
								CheckNull.isEmpty(c.getValue()) ? null : c
										.getValue());
					} else if (params.get(i).getParam_type().equals("CHECKBOX")) {
						ch = (Checkbox) addinfo.getFellow("p_"
								+ params.get(i).getParam_id());
						params.get(i)
								.setParam_value(ch.isChecked() ? "1" : "0");
					} else if (params.get(i).getParam_type().equals("NUMBER")) {
						n = (Textbox) addinfo.getFellow("p_"
								+ params.get(i).getParam_id());
						if (params.get(i).getParam_mandatory() == 1
								&& CheckNull.isEmpty(n.getValue())) {
							alert("Не заполнен обязательный параметр: "
									+ (lang.equalsIgnoreCase("en") ? params
											.get(i).getParam_name_en() : (lang
											.equalsIgnoreCase("uz") ? params
											.get(i).getParam_name_uz() : params
											.get(i).getParam_name_ru())));
							return false;
						}
						params.get(i).setParam_value(
								CheckNull.isEmpty(n.getValue()) ? null : n
										.getValue());
					} else if (params.get(i).getParam_type()
							.equals("PERSONGRID")) {
						Listbox listbox = (Listbox) addinfo.getFellow("p_"
								+ params.get(i).getParam_id());
						if (params.get(i).getParam_mandatory() == 1
								&& listbox.getModel().getSize() > 0) {
							alert("Не заполнен обязательный параметр: "
									+ (lang.equalsIgnoreCase("en") ? params
											.get(i).getParam_name_en() : (lang
											.equalsIgnoreCase("uz") ? params
											.get(i).getParam_name_uz() : params
											.get(i).getParam_name_ru())));
							return false;
						}
					} else {// STRING
						t = (Textbox) addinfo.getFellow("p_"
								+ params.get(i).getParam_id());
						if (params.get(i).getParam_mandatory() == 1
								&& CheckNull.isEmpty(t.getValue())) {
							alert("Не заполнен обязательный параметр: "
									+ (lang.equalsIgnoreCase("en") ? params
											.get(i).getParam_name_en() : (lang
											.equalsIgnoreCase("uz") ? params
											.get(i).getParam_name_uz() : params
											.get(i).getParam_name_ru())));
							return false;
						}
						params.get(i).setParam_value(
								CheckNull.isEmpty(t.getValue()) ? null : t
										.getValue());
					}
				}
			}
			parameters = params;
			res = true;
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			alert(e.getMessage());
			res = false;
		}
		return res;
	}

	public Boolean saveAddInfoFromCb() {
		Boolean res = false;
		bproductAddInfService.remove(alias, template);
		try {
			int quantity = getComboboxQuantity(combobox);
			if (template == 2) {
				List<Parameter> params = bproductAddInfService
						.getParameters(template);
				if (parameters.size() > 0) {

					Combobox c = new Combobox();

					for (int i = 0; i < quantity; i++) {

						if (params.get(i).getParam_type().equals("COMBOBOX")) {
							c = (RefCBox) addinfo.getFellow("p_"
									+ params.get(i).getParam_id());
							if (params.get(i).getParam_mandatory() == 1
									&& CheckNull.isEmpty(c.getValue())) {
								alert("Не заполнен обязательный параметр: "
										+ (lang.equalsIgnoreCase("en") ? params
												.get(i).getParam_name_en()
												: (lang.equalsIgnoreCase("uz") ? params
														.get(i)
														.getParam_name_uz()
														: params.get(i)
																.getParam_name_ru())));
								return false;
							}
							params.get(i).setParam_value(
									CheckNull.isEmpty(c.getValue()) ? null : c
											.getValue());
						}
					}
				}
				parameters = params;
				res = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			alert(e.getMessage());
			res = false;
		}
		return res;
	}

	public void onClick$btn_save() {
		try {
			Boolean result = false;
			// parameters = bproductAddInfService.getParameters(template);
			if (template == 1 || template == 4) {
				if (current != null) {
					if (!saveAddInfo()) {return;}
					List<RefData> list = bproductAddInfService.getCardNumber(parameters.get(0).getParam_value(), alias);
					if(!parameters.isEmpty()&&!list.isEmpty()){
						if(parameters.get(0).getParam_value().equals(list.get(0).getLabel())&&list.get(0).getData().equals("2")){
							alert("На эту карточку уже оформлен овердрафт "+list.get(0).getLabel());
							return;
						}	
					}
					click_btn_save = 1;
					session.setAttribute("click_btn_save", click_btn_save);
					result = bproductAddInfService.saveAddInfo(parameters, cl_id);
					// ("Данные успешно сохранены");
				}
			} else if (template == 2) {
				if (current != null) {
					if (!saveAddInfoFromCb())
						return;
					result = bproductAddInfService.saveAddInfo(parameters, cl_id);
					// ("Данные успешно сохранены");
				}
			}
			// Res res = bproductAddInfService.saveAddInfo(parameters);
			if (result == true) {
				alert("Данные успешно сохранены");
			} else if (result == false)
				alert("Ошибка! Данные не сохранены");
		} catch (Exception e) {
			e.printStackTrace();
			alert(CheckNull.getPstr(e));
			ISLogger.getLogger().error("Сохранение в аддинфо");
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		}

	}

	public static int getComboboxQuantity(List<RefCBox> list) {
		int quantity = 0;
		quantity = list.size();
		return quantity;
	}

	final List<RefCBox> combobox = new ArrayList<RefCBox>();

	public void createComboBox() {
		try {
			parametergroups = bproductAddInfService.getParametergroup(template);
			parameters = bproductAddInfService.getParameters(template);
			paramForSelect = bproductAddInfService.getParameters(template);
			// client_code = bproductAddInfService.getClient_code(alias);

			Button btnAddCb = new Button("Добавить поля");// -------- Кнопка для
															// создания коммбиков
															// динамически -------
			btnAddCb.setHeight("25px");
			btnAddCb.setWidth("100px");

			final List<Label> labForRefCBox = new ArrayList<Label>();
			final List<Label> labForRefCBoxSum = new ArrayList<Label>();

			addinfo.getChildren().clear();
			int number = combobox.size() + 1;
			Groupbox g = new Groupbox();
			g.setMold("3d");
			g.setOpen(true);
			g.setClosable(true);
			g.setWidth("100%");
			Caption caption = new Caption("Кредит под залог депозита");
			caption.setHeight("30px");
			g.appendChild(caption);
			caption.appendChild(btnAddCb);
			// g.appendChild(btnAddCb);
			Grid grid = new Grid();
			grid.setId("grid_" + number);
			grid.setWidth("100%");
			Columns columns = new Columns();
			grid.appendChild(columns);
			// grid.getColumns().getChildren().clear();
			Rows rows = new Rows();
			grid.appendChild(rows);
			Column column = new Column();
			column.setAlign("left");
			column.setWidth("30%");
			grid.getColumns().appendChild(column);
			column = new Column();
			column.setAlign("left");
			column.setWidth("70%");
			grid.getColumns().appendChild(column);

			final Vbox vb = new Vbox();
			Label lbl = new Label();

			final Row row = new Row();
			row.setAttribute("parameter", 2);
			row.setAttribute("group", 2);
			row.appendChild(new Label("Вклады"));
			// vb = new Vbox();
			vb.setWidth("100%");
			// vb.setHeight("400px");
			lbl = new Label("* Обязательный параметр");
			lbl.setStyle("font-size: 12px; color: grey;");
			vb.appendChild(lbl);

			btnAddCb.addEventListener(Events.ON_CLICK, new EventListener() {
				public void onEvent(org.zkoss.zk.ui.event.Event event)
						throws Exception {
					// ---------------- Счетчики для двух комбиков (1-ый депозит,
					// 2-ой сумма вклада)---------------------

					int number = combobox.size() + 1;
					int number2 = combobox.size() + 2;
					// -----------------------------------------------------------------------------

					// -------------------- Счетчик для меток для каждого из
					// комбиков ----------------------------
					int counter = labForRefCBox.size() + 1;
					// ---------------------------------------------------------------------------------------

					for (ParameterGroup parametergroup : parametergroups) {
						List<Parameter> params = bproductAddInfService
								.getParametersByGroup(parameters, parametergroup);
						for (int i = 0; i < params.size(); i++) {
							// ----------------------------- Депозит
							// -------------------------------------------
							if (params.get(i).getParam_type().equals("COMBOBOX")
									&& params.get(i).getParam_name()
											.equals("DepositChoice1")) {
								String select = params.get(i).getParam_select()
										.toUpperCase();

								select = bproductAddInfService.setParamsInSQL2(
										paramForSelect, select, branch1, cl_id);

								// --------------------- Создание метки для комбика
								// вклада --------------------------
								Label labelDep = new Label("Вклад № " + counter);
								labForRefCBox.add(labelDep);

								vb.appendChild(labelDep);
								// ----------------------------------------------------------------------------------

								// ------------------------- Создание комбика для
								// заполнения депозита -------------------------
								RefCBox refbox = new RefCBox();
								refbox.setId("p_" + number);
								refbox.setAttribute("parameter", params.get(i));
								refbox.setAttribute("group", parametergroup);
								refbox.setWidth("600px");
								refbox.setMold("rounded");
								refbox.setReadonly(true);

								combobox.add(refbox);

								if (!CheckNull.isEmpty(select)) {
									Res res = new Res();
									ListModelList list = new ListModelList(
											bproductAddInfService
													.getListForCombobox(select,
															alias,res));
									if(res.getCode()==1){
										alert(res.getName());
										return;
									}
									refbox.setModel(list);

									refbox.addEventListener("onInitRenderLater",
											new EventListener() {
												public void onEvent(
														org.zkoss.zk.ui.event.Event event)
														throws Exception {
													RefCBox cc = (RefCBox) event
															.getTarget();
													if (cc.getAttribute("parameter") != null) {
														cc.setSelecteditem(((Parameter) cc
																.getAttribute("parameter"))
																.getParam_value());
													} else {
														for (int i = 0; i < cc
																.getModel()
																.getSize(); i++) {
															RefData rd = (RefData) cc
																	.getModel()
																	.getElementAt(i);
															if (rd.getData() == null) {
																cc.setSelectedIndex(i);
															}
														}
													}
												}
											});
								} else {
									refbox.setModel(null);
								}
								vb.appendChild(refbox);
								// ---------------------------------------------------------------------------------------------
							}

							// ------------------------- Создание комбика для
							// заполнения суммы вклада -------------------------
							if (params.get(i).getParam_type().equals("COMBOBOX")
									&& params.get(i).getParam_name()
											.equals("SummChoice1")) {
								// ----------------------- Селект для комбика
								// ---------------------
								String select = params.get(i).getParam_select()
										.toUpperCase();
								System.out.println(select);
								select = bproductAddInfService.setParamsInSQL2(
										paramForSelect, select, branch1, cl_id);
								// ----------------------------------------------------------------

								// -------------------------- Создание метки для
								// Суммы вклада ----------------------------------
								Label labelSum = new Label("Сумма вклада № "
										+ counter);
								labForRefCBoxSum.add(labelSum);

								vb.appendChild(labelSum);
								// ---------------------------------------------------------------------------------------------

								// refboxSum.setName("Вклад № "+number+"");
								RefCBox refboxSum = new RefCBox();
								refboxSum.setId("p_" + number2);
								refboxSum.setAttribute("parameter", params.get(i));
								refboxSum.setAttribute("group", parametergroup);
								refboxSum.setWidth("600px");
								refboxSum.setMold("rounded");
								refboxSum.setReadonly(true);
								// refboxSum.g

								combobox.add(refboxSum);

								if (!CheckNull.isEmpty(select)) {
									Res res = new Res();
									ListModelList list = new ListModelList(
											bproductAddInfService
													.getListForCombobox(select,
															alias,res));
									if(res.getCode()==1){
										alert(res.getName());
									}
									refboxSum.setModel(list);

									refboxSum.addEventListener("onInitRenderLater",
											new EventListener() {
												public void onEvent(
														org.zkoss.zk.ui.event.Event event)
														throws Exception {
													RefCBox cc = (RefCBox) event
															.getTarget();
													if (cc.getAttribute("parameter") != null) {
														cc.setSelecteditem(((Parameter) cc
																.getAttribute("parameter"))
																.getParam_value());
													} else {
														for (int i = 0; i < cc
																.getModel()
																.getSize(); i++) {
															RefData rd = (RefData) cc
																	.getModel()
																	.getElementAt(i);
															if (rd.getData() == null) {
																cc.setSelectedIndex(i);
															}
														}
													}
												}
											});
								} else {
									refboxSum.setModel(null);
								}
								vb.appendChild(refboxSum);
								// ------------------------------------------------------------------------------------------------
							}

						}

					}
				}
			});
			row.appendChild(vb);
			grid.getRows().appendChild(row);

			g.appendChild(grid);
			addinfo.appendChild(g);
			
		} catch (Exception e) {
			e.printStackTrace();
			alert(CheckNull.getPstr(e));
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
	}
	
	public static boolean selectedCard(){
		boolean temp = false;
		if(cardbox!=null&&cardbox.getSelectedIndex()>=0){
			temp = true;
		}
		return temp;
	}

}
